// Generated from src/calc.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calcParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(calcParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PiExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPiExpr(calcParser.PiExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumeroExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeroExpr(calcParser.NumeroExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(calcParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncionExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncionExpr(calcParser.FuncionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEExpr(calcParser.EExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PowExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(calcParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(calcParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(calcParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(calcParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncionCall}
	 * labeled alternative in {@link calcParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncionCall(calcParser.FuncionCallContext ctx);
}