grammar calc;

prog: expr EOF;

expr
    : '-' expr                            # NegExpr
    | expr '^' expr                       # PowExpr
    | expr op=('*' | '/') expr            # MulDivExpr
    | expr op=('+' | '-') expr            # AddSubExpr
    | funcion                             # FuncionExpr
    | NUMERO                              # NumeroExpr
    | PI                                  # PiExpr
    | E                                   # EExpr
    | '(' expr ')'                        # ParenExpr
    ;

funcion
    : FUNCION '(' expr (',' expr)* ')'    # FuncionCall
    ;

PI      : 'pi';
E       : 'e';

// FUNCIONES PERMITIDAS
FUNCION :
      'sin' | 'cos' | 'tan' | 'cot' | 'sec' | 'csc'
    | 'log' | 'ln' | 'sqrt' | 'abs' | 'exp'
    | 'floor' | 'ceil' | 'round' | 'trunc'
    | 'min' | 'max'
    ;

// TOKENS BÁSICOS

NUMERO  : DIGITO+ ('.' DIGITO+)? ([eE] [-+]? DIGITO+)? ;
fragment DIGITO : [0-9];

// OTROS

WS : [ \t\r\n]+ -> skip ;

