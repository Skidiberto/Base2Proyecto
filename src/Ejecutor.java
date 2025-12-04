import java.util.*;

public class Ejecutor {
    // Esta es la "memoria RAM" de tu máquina virtual.
    // Guarda el valor de las variables temporales (t1 -> 6.0, t2 -> 11.0)
    private Map<String, Double> memoria = new HashMap<>();

    // Método principal que ejecuta el código intermedio
    public Double ejecutar(List<String> instrucciones) {
        memoria.clear(); // Limpiar memoria antes de empezar
        Double ultimoResultado = 0.0;

        for (String linea : instrucciones) {
            // Analizar la instrucción: "t1 = 5 + 3"
            // Dividimos por " = " para separar el destino de la operación
            String[] partes = linea.split(" = ");
            String variableDestino = partes[0].trim();
            String expresion = partes[1].trim();

            Double valor = evaluarExpresion(expresion);
            
            // Guardamos en memoria: t1 vale X
            memoria.put(variableDestino, valor);
            
            // Asumimos que la última instrucción calculada es el resultado final
            ultimoResultado = valor;
        }
        
        return ultimoResultado;
    }

    private Double evaluarExpresion(String expr) {
        // Caso 1: Llamada a función (ej: "call sin, t1")
        if (expr.startsWith("call ")) {
            String sinCall = expr.substring(5); // Quitar "call "
            String[] partes = sinCall.split(", ");
            String funcion = partes[0];
            
            // Obtener argumentos (recursivo si es necesario, pero aquí son variables)
            Double arg1 = getValor(partes[1]);
            Double arg2 = partes.length > 2 ? getValor(partes[2]) : null;

            return ejecutarFuncion(funcion, arg1, arg2);
        }

        // Dividir la expresión por espacios para ver si es binaria o unaria
        String[] tokens = expr.split(" ");

        // Caso 2: Asignación directa o número (ej: "5" o "t1")
        if (tokens.length == 1) {
            return getValor(tokens[0]);
        }

        // Caso 3: Operación Unaria (ej: "- t1")
        if (tokens.length == 2 && tokens[0].equals("-")) {
            return -getValor(tokens[1]);
        }

        // Caso 4: Operación Binaria (ej: "t1 + t2")
        if (tokens.length == 3) {
            Double izquierdo = getValor(tokens[0]);
            String operador = tokens[1];
            Double derecho = getValor(tokens[2]);

            switch (operador) {
                case "+": return izquierdo + derecho;
                case "-": return izquierdo - derecho;
                case "*": return izquierdo * derecho;
                case "/": return izquierdo / derecho;
                case "^": return Math.pow(izquierdo, derecho);
                default: throw new RuntimeException("Operador desconocido: " + operador);
            }
        }

        throw new RuntimeException("Instrucción mal formada: " + expr);
    }

    // Helper: Obtiene el valor real de un token (ya sea un número literal o una variable de memoria)
    private Double getValor(String token) {
        // Constantes
        if (token.equals("pi")) return Math.PI;
        if (token.equals("e")) return Math.E;

        // Si es una variable temporal (t1, t2...), búscala en memoria
        if (memoria.containsKey(token)) {
            return memoria.get(token);
        }

        // Si no, intenta parsearlo como número directo
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Variable no encontrada o número inválido: " + token);
        }
    }

    // Helper: Ejecuta las funciones matemáticas
    private Double ejecutarFuncion(String func, Double a, Double b) {
        switch (func) {
            case "sin": return Math.sin(a);
            case "cos": return Math.cos(a);
            case "tan": return Math.tan(a);
            case "log": return Math.log10(a);
            case "ln": return Math.log(a);
            case "sqrt": return Math.sqrt(a);
            case "abs": return Math.abs(a);
            case "exp": return Math.exp(a);
            case "floor": return Math.floor(a);
            case "ceil": return Math.ceil(a);
            case "round": return (double) Math.round(a);
            case "min": return Math.min(a, b);
            case "max": return Math.max(a, b);
            default: throw new RuntimeException("Función no soportada: " + func);
        }
    }
}
