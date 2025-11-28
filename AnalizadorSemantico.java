import java.io.*;
import java.util.*;

public class AnalizadorSemantico {
    private Map<String, String> tablaSimbolos = new HashMap<>();
    private Set<String> funciones = new HashSet<>();
    private Set<String> ignorar = new HashSet<>(Arrays.asList("print", "range", "input", "len"));

    public AnalizadorSemantico() {
        // Constructor vacío
    }

    public void analizar(String archivoTokens) throws IOException {
        List<Token> tokens = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivoTokens));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(": ");
            if (partes.length == 2) {
                tokens.add(new Token(partes[0], partes[1]));
            }
        }
        br.close();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            // Declaración de variable (asignación)
            if (token.getTipo().equals("IDENTIFICADOR")) {
                if (i + 1 < tokens.size() && tokens.get(i + 1).getValor().equals("=")) {
                    Token nombreToken = token;
                    Token valorToken = tokens.get(i + 2);
                    String tipo = "int";
                    if (valorToken.getTipo().equals("CADENA")) {
                        tipo = "string";
                    } else if (valorToken.getTipo().equals("NUMERO")) {
                        tipo = "int";
                    }
                    tablaSimbolos.put(nombreToken.getValor(), tipo);
                }
            }

            // Declaración de función
            if (token.getTipo().equals("PALABRA_CLAVE") && token.getValor().equals("def")) {
                if (i + 1 < tokens.size()) {
                    Token nombreFuncion = tokens.get(i + 1);
                    funciones.add(nombreFuncion.getValor());
                    // Agregar parámetros a tabla de símbolos temporal
                    Set<String> parametros = new HashSet<>();
                    int j = i + 3; // Salta "def", nombre, "("
                    while (j < tokens.size() && !tokens.get(j).getValor().equals(")")) {
                        if (tokens.get(j).getTipo().equals("IDENTIFICADOR")) {
                            parametros.add(tokens.get(j).getValor());
                        }
                        j++;
                    }
                    // Analizar cuerpo de la función (por indentación)
                    int cuerpoInicio = j + 2; // Salta ")", ":"
                    int cuerpoFin = cuerpoInicio;
                    int indentNivel = 1;
                    // Tabla de símbolos local para la función
                    Set<String> simbolosLocales = new HashSet<>(parametros);
                    while (cuerpoFin < tokens.size() && indentNivel > 0) {
                        Token t = tokens.get(cuerpoFin);
                        if (t.getTipo().equals("INDENT")) indentNivel++;
                        if (t.getTipo().equals("DEDENT")) indentNivel--;
                        cuerpoFin++;
                    }
                    // Analizar cuerpo y registrar variables locales
                    for (int k = cuerpoInicio; k < cuerpoFin - 1; k++) {
                        Token t = tokens.get(k);
                        // Registrar variables locales en asignación
                        if (t.getTipo().equals("IDENTIFICADOR")) {
                            if (k + 1 < tokens.size() && tokens.get(k + 1).getValor().equals("=")) {
                                simbolosLocales.add(t.getValor());
                            }
                        }
                    }
                    // Analizar cuerpo y reportar solo si no es local, global, función o ignorada
                    for (int k = cuerpoInicio; k < cuerpoFin - 1; k++) {
                        Token t = tokens.get(k);
                        if (t.getTipo().equals("IDENTIFICADOR")) {
                            String nombre = t.getValor();
                            if (!simbolosLocales.contains(nombre) && !tablaSimbolos.containsKey(nombre) && !funciones.contains(nombre) && !ignorar.contains(nombre)) {
                                System.out.println("Error semántico: variable '" + nombre + "' no declarada.");
                            }
                        }
                    }
                    i = cuerpoFin - 1; // Saltar cuerpo de la función
                }
            }

            // Uso de variable fuera de función
            if (token.getTipo().equals("IDENTIFICADOR")) {
                String nombre = token.getValor();
                if (!tablaSimbolos.containsKey(nombre) && !funciones.contains(nombre) && !ignorar.contains(nombre)) {
                    // Evitar marcar parámetros de función como error fuera de función
                    boolean esParametro = false;
                    if (i > 0 && tokens.get(i - 1).getValor().equals("(")) esParametro = true;
                    if (!esParametro)
                        System.out.println("Error semántico: variable '" + nombre + "' no declarada.");
                }
            }
        }
    }

    // Clase interna Token
    public static class Token {
        private String tipo;
        private String valor;

        public Token(String tipo, String valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    // Método main para ejecutar el analizador
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java AnalizadorSemantico <archivo_tokens>");
            return;
        }
        AnalizadorSemantico analizador = new AnalizadorSemantico();
        try {
            analizador.analizar(args[0]);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
