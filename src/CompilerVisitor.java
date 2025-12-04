import java.util.*;

// Cambiamos <Double> por <String> porque retornaremos nombres de variables (t1, t2...)
public class CompilerVisitor extends calcBaseVisitor<String> {
    
    // Lista para guardar las líneas de código intermedio generadas
    private List<String> codigoIntermedio = new ArrayList<>();
    
    // Contador para las variables temporales (t1, t2, t3...)
    private int tempCounter = 1;

    // Método para obtener el código final
    public List<String> getCodigoIntermedio() {
        return codigoIntermedio;
    }

    // Generador de nuevas variables temporales
    private String newTemp() {
        return "t" + (tempCounter++);
    }

    // Método auxiliar para agregar instrucciones a la lista
    private void emit(String instruccion) {
        codigoIntermedio.add(instruccion);
    }

    // --- VISITOR METHODS ---

    @Override
    public String visitProg(calcParser.ProgContext ctx) {
        // Visitamos la expresión principal
        return visit(ctx.expr());
    }

    @Override
    public String visitNumeroExpr(calcParser.NumeroExprContext ctx) {
        // En lugar de convertir a número, devolvemos el texto tal cual (ej: "5")
        return ctx.NUMERO().getText();
    }

    @Override
    public String visitPiExpr(calcParser.PiExprContext ctx) {
        return "3.141592"; // O podrías usar una constante global
    }

    @Override
    public String visitEExpr(calcParser.EExprContext ctx) {
        return "2.718281";
    }

    @Override
    public String visitParenExpr(calcParser.ParenExprContext ctx) {
        return visit(ctx.expr()); // Los paréntesis solo afectan el orden de visita
    }

    @Override
    public String visitNegExpr(calcParser.NegExprContext ctx) {
        String right = visit(ctx.expr());
        String temp = newTemp();
        emit(temp + " = - " + right); // Genera: t1 = - 5
        return temp;
    }

    @Override
    public String visitPowExpr(calcParser.PowExprContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String temp = newTemp();
        // Genera: t1 = 5 ^ 2
        emit(temp + " = " + left + " ^ " + right); 
        return temp;
    }

    @Override
    public String visitMulDivExpr(calcParser.MulDivExprContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        String temp = newTemp();
        
        // Genera: t1 = 3 * 2
        emit(temp + " = " + left + " " + op + " " + right);
        return temp;
    }

    @Override
    public String visitAddSubExpr(calcParser.AddSubExprContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        String temp = newTemp();
        
        // Genera: t2 = t1 + 5
        emit(temp + " = " + left + " " + op + " " + right);
        return temp;
    }

    @Override
    public String visitFuncionCall(calcParser.FuncionCallContext ctx) {
        String funcName = ctx.FUNCION().getText();
        List<calcParser.ExprContext> args = ctx.expr();
        
        // Procesamos los argumentos primero
        List<String> argTemps = new ArrayList<>();
        for (calcParser.ExprContext arg : args) {
            argTemps.add(visit(arg));
        }

        String temp = newTemp();
        
        // Construimos la llamada. Ej: t3 = call sin, t2
        StringBuilder sb = new StringBuilder();
        sb.append(temp).append(" = call ").append(funcName);
        for (String arg : argTemps) {
            sb.append(", ").append(arg);
        }
        
        emit(sb.toString());
        return temp;
    }
}
