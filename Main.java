import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java Main <archivo_entrada> <archivo_salida>"); // Mensaje de uso
            return;
        }

        String archivoEntrada = args[0];
        String archivoSalida = args[1];

        try (
            BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))
        ) {
            Lexer lexer = new Lexer(reader, writer);
            while (true) {
                lexer.yylex();
                if (lexer.isEOF()) break;
            }
            System.out.println("Análisis léxico completado. Tokens guardados en " + archivoSalida); // Léxico
            System.out.println(); // Espacio

            // Analizador semántico
            AnalizadorSemantico analizadorSemantico = new AnalizadorSemantico();
            analizadorSemantico.analizar(archivoSalida);
            System.out.println("Análisis semántico completado."); // Semántico
            System.out.println(); // Espacio

                        // Generación de código intermedio
            System.out.println("Generación de código intermedio:");
            GeneradorIntermedio.generarDesdeArchivo(archivoSalida);
            System.out.println(); // Espacio

            // Llamada a la API de Hugging Face
            System.out.println("Hugging Face API llamada. \n Dice: \n"); // Semántico

            StringBuilder codigoFuente = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    codigoFuente.append(linea).append("\n");
                }
            }
            try {
                String[] respuesta = callapi.llamarApiHuggingFace(codigoFuente.toString());
                System.out.println("🧪 Sintaxis:\n" + respuesta[0]); // Sintaxis
                System.out.println(); // Espacio
                System.out.println("📘 Análisis lógico:\n" + respuesta[1]); // Hugging Face - análisis lógico
                System.out.println(); // Espacio
            } catch (IOException e) {
                System.out.println("Error al llamar a la API de Hugging Face:"); // Error en Hugging Face
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
