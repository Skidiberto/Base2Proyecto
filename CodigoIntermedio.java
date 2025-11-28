public class CodigoIntermedio {
    public String operador;
    public String arg1;
    public String arg2;
    public String resultado;

    public CodigoIntermedio(String operador, String arg1, String arg2, String resultado) {
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        if (arg2 == null || arg2.isEmpty()) {
            return resultado + " = " + operador + " " + arg1;
        }
        return resultado + " = " + arg1 + " " + operador + " " + arg2;
    }
}