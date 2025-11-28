import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorIntermedio {
    private static int tempCount = 1;
    private static List<CodigoIntermedio> instrucciones = new ArrayList<>();

    private static String nuevoTemporal() {
        return "t" + (tempCount++);
    }

    public static void generarDesdeArchivo(String archivoTokens) throws IOException {
        tempCount = 1;
        instrucciones = new ArrayList<>();

        // Lee los tokens generados por el lexer
        List<String[]> tokens = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoTokens))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ");
                if (partes.length == 2) {
                    tokens.add(partes);
                }
            }
        }

        for (int i = 0; i < tokens.size(); i++) {
            String tipo = tokens.get(i)[0];
            String valor = tokens.get(i)[1];

            // Asignación: IDENTIFICADOR = EXPRESION
            if (tipo.equals("IDENTIFICADOR") && i + 2 < tokens.size()
                && tokens.get(i + 1)[0].equals("OPERADOR") && tokens.get(i + 1)[1].equals("=")) {
            String var = valor;
            // Asignación directa
            if (tokens.get(i + 2)[0].equals("NÚMERO") || tokens.get(i + 2)[0].equals("LITERAL") || tokens.get(i + 2)[0].equals("IDENTIFICADOR")) {
                instrucciones.add(new CodigoIntermedio("=", tokens.get(i + 2)[1], null, var));
                i += 2;
            }
            // Operación aritmética simple: a = b OP c
            else if ((tokens.get(i + 2)[0].equals("IDENTIFICADOR") || tokens.get(i + 2)[0].equals("NÚMERO"))
                && i + 4 < tokens.size()
                && tokens.get(i + 3)[0].equals("OPERADOR")
                && (tokens.get(i + 3)[1].matches("\\+|-|\\*\\*|\\*|//|/|%|==|!=|<=|>=|<|>|\\+=|-=|\\*=|/=|//=|%=|\\^|\\|\\||&&|&|~|<<|>>|\\.")) 
                && (tokens.get(i + 4)[0].equals("IDENTIFICADOR") || tokens.get(i + 4)[0].equals("NÚMERO"))) {
                String temp = nuevoTemporal();
                instrucciones.add(new CodigoIntermedio(tokens.get(i + 3)[1], tokens.get(i + 2)[1], tokens.get(i + 4)[1], temp));
                instrucciones.add(new CodigoIntermedio("=", temp, null, var));
                i += 4;
            }
            }
            // Llamada a función: IDENTIFICADOR ( PARAMETROS )
            else if (tipo.equals("IDENTIFICADOR") && i + 1 < tokens.size()
                && tokens.get(i + 1)[0].equals("DELIMITADOR") && tokens.get(i + 1)[1].equals("(")) {
            String funcion = valor;
            List<String> parametros = new ArrayList<>();
            i += 2; // Saltar al '('
            while (i < tokens.size() && !(tokens.get(i)[0].equals("DELIMITADOR") && tokens.get(i)[1].equals(")"))) {
                if (tokens.get(i)[0].equals("IDENTIFICADOR") || tokens.get(i)[0].equals("NÚMERO") || tokens.get(i)[0].equals("LITERAL")) {
                parametros.add(tokens.get(i)[1]);
                }
                i++;
            }
            instrucciones.add(new CodigoIntermedio("CALL", funcion, String.join(", ", parametros), null));
            }
            // Return: return EXPRESION
            else if (tipo.equals("PALABRA_CLAVE") && valor.equals("return")) {
            if (i + 1 < tokens.size() && (tokens.get(i + 1)[0].equals("IDENTIFICADOR") || tokens.get(i + 1)[0].equals("NÚMERO") || tokens.get(i + 1)[0].equals("LITERAL"))) {
                instrucciones.add(new CodigoIntermedio("RETURN", tokens.get(i + 1)[1], null, null));
                i += 1;
            }
            }
            // If: if ( CONDICION ):
            else if (tipo.equals("PALABRA_CLAVE") && valor.equals("if") && i + 1 < tokens.size()
                && tokens.get(i + 1)[0].equals("DELIMITADOR") && tokens.get(i + 1)[1].equals("(")) {
            i += 2; // Saltar al '('
            StringBuilder condicion = new StringBuilder();
            while (i < tokens.size() && !(tokens.get(i)[0].equals("DELIMITADOR") && tokens.get(i)[1].equals(")"))) {
                condicion.append(tokens.get(i)[1]).append(" ");
                i++;
            }
            String etiquetaTrue = "L" + tempCount++;
            String etiquetaFalse = "L" + tempCount++;
            instrucciones.add(new CodigoIntermedio("IF", condicion.toString().trim(), null, etiquetaTrue));
            instrucciones.add(new CodigoIntermedio("GOTO", null, null, etiquetaFalse));
            instrucciones.add(new CodigoIntermedio("LABEL", null, null, etiquetaTrue));
            // Aquí podrías procesar instrucciones internas del if si lo deseas
            instrucciones.add(new CodigoIntermedio("LABEL", null, null, etiquetaFalse));
            }
            // While: while ( CONDICION ):
            else if (tipo.equals("PALABRA_CLAVE") && valor.equals("while") && i + 1 < tokens.size()
                && tokens.get(i + 1)[0].equals("DELIMITADOR") && tokens.get(i + 1)[1].equals("(")) {
            String etiquetaInicio = "L" + tempCount++;
            String etiquetaFin = "L" + tempCount++;
            instrucciones.add(new CodigoIntermedio("LABEL", null, null, etiquetaInicio));
            i += 2;
            StringBuilder condicion = new StringBuilder();
            while (i < tokens.size() && !(tokens.get(i)[0].equals("DELIMITADOR") && tokens.get(i)[1].equals(")"))) {
                condicion.append(tokens.get(i)[1]).append(" ");
                i++;
            }
            instrucciones.add(new CodigoIntermedio("IF", condicion.toString().trim(), null, etiquetaInicio + "_BODY"));
            instrucciones.add(new CodigoIntermedio("GOTO", null, null, etiquetaFin));
            instrucciones.add(new CodigoIntermedio("LABEL", null, null, etiquetaInicio + "_BODY"));
            // Procesar cuerpo del while si se desea
            instrucciones.add(new CodigoIntermedio("GOTO", null, null, etiquetaInicio));
            instrucciones.add(new CodigoIntermedio("LABEL", null, null, etiquetaFin));
            }
            // Definición de función: def IDENTIFICADOR ( PARAMS ):
            else if (tipo.equals("PALABRA_CLAVE") && valor.equals("def")
                && i + 1 < tokens.size() && tokens.get(i + 1)[0].equals("IDENTIFICADOR")) {
            String nombreFuncion = tokens.get(i + 1)[1];
            instrucciones.add(new CodigoIntermedio("FUNC_BEGIN", nombreFuncion, null, null));
            // Aquí podrías procesar los parámetros si lo deseas
            // Saltar hasta el final del bloque de la función (si tienes tokens de indentación)
            // Si no, simplemente continúa
            }
        }

        // Imprime el código intermedio generado
        System.out.println("Código intermedio generado:");
        for (CodigoIntermedio instr : instrucciones) {
            System.out.println(instr);
        }
    }
}
