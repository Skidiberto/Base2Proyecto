import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.Scanner;
import java.util.List;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Compilador de Calculadora Científica ===");
        System.out.println("Fases: Análisis -> Código Intermedio -> Ejecución");
        System.out.println("Escribe 'salir' para terminar.");

        while (true) {
            System.out.print("\nIngrese expresión: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                // 1. FASE DE ANÁLISIS LÉXICO Y SINTÁCTICO
                CharStream stream = CharStreams.fromString(input);
                calcLexer lexer = new calcLexer(stream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                calcParser parser = new calcParser(tokens);
                
                parser.removeErrorListeners();
                parser.addErrorListener(new BaseErrorListener() {
                    @Override
                    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e) {
                        throw new RuntimeException("Error sintáctico: " + msg);
                    }
                });
                
                ParseTree tree = parser.prog();

                // 2. FASE DE GENERACIÓN DE CÓDIGO INTERMEDIO
                CompilerVisitor compiler = new CompilerVisitor();
                compiler.visit(tree);
                List<String> codigoTAC = compiler.getCodigoIntermedio();
                
                System.out.println("\n[1] Código Intermedio Generado (TAC):");
                System.out.println("-------------------------------------");
                for (String instruccion : codigoTAC) {
                    System.out.println(instruccion);
                }
                System.out.println("-------------------------------------");

                // 3. FASE DE EJECUCIÓN (MÁQUINA VIRTUAL)
                Ejecutor maquinaVirtual = new Ejecutor();
                Double resultadoFinal = maquinaVirtual.ejecutar(codigoTAC);

                System.out.println("[2] Resultado de Ejecución: " + resultadoFinal);
                
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                // e.printStackTrace(); // Descomentar para depurar
            }
        }
        scanner.close();
    }
}
