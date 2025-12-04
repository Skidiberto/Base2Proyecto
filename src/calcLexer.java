// Generated from src/calc.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class calcLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PI=9, 
		E=10, FUNCION=11, NUMERO=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "PI", 
			"E", "FUNCION", "NUMERO", "DIGITO", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'^'", "'*'", "'/'", "'+'", "'('", "')'", "','", "'pi'", 
			"'e'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "PI", "E", "FUNCION", 
			"NUMERO", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public calcLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "calc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\r\u008f\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\nm\b\n\u0001\u000b\u0004\u000bp\b\u000b\u000b"+
		"\u000b\f\u000bq\u0001\u000b\u0001\u000b\u0004\u000bv\b\u000b\u000b\u000b"+
		"\f\u000bw\u0003\u000bz\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b~\b"+
		"\u000b\u0001\u000b\u0004\u000b\u0081\b\u000b\u000b\u000b\f\u000b\u0082"+
		"\u0003\u000b\u0085\b\u000b\u0001\f\u0001\f\u0001\r\u0004\r\u008a\b\r\u000b"+
		"\r\f\r\u008b\u0001\r\u0001\r\u0000\u0000\u000e\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\u0000\u001b\r\u0001\u0000\u0004\u0002\u0000"+
		"EEee\u0002\u0000++--\u0001\u000009\u0003\u0000\t\n\r\r  \u00a4\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0001\u001d\u0001\u0000\u0000\u0000\u0003\u001f\u0001\u0000"+
		"\u0000\u0000\u0005!\u0001\u0000\u0000\u0000\u0007#\u0001\u0000\u0000\u0000"+
		"\t%\u0001\u0000\u0000\u0000\u000b\'\u0001\u0000\u0000\u0000\r)\u0001\u0000"+
		"\u0000\u0000\u000f+\u0001\u0000\u0000\u0000\u0011-\u0001\u0000\u0000\u0000"+
		"\u00130\u0001\u0000\u0000\u0000\u0015l\u0001\u0000\u0000\u0000\u0017o"+
		"\u0001\u0000\u0000\u0000\u0019\u0086\u0001\u0000\u0000\u0000\u001b\u0089"+
		"\u0001\u0000\u0000\u0000\u001d\u001e\u0005-\u0000\u0000\u001e\u0002\u0001"+
		"\u0000\u0000\u0000\u001f \u0005^\u0000\u0000 \u0004\u0001\u0000\u0000"+
		"\u0000!\"\u0005*\u0000\u0000\"\u0006\u0001\u0000\u0000\u0000#$\u0005/"+
		"\u0000\u0000$\b\u0001\u0000\u0000\u0000%&\u0005+\u0000\u0000&\n\u0001"+
		"\u0000\u0000\u0000\'(\u0005(\u0000\u0000(\f\u0001\u0000\u0000\u0000)*"+
		"\u0005)\u0000\u0000*\u000e\u0001\u0000\u0000\u0000+,\u0005,\u0000\u0000"+
		",\u0010\u0001\u0000\u0000\u0000-.\u0005p\u0000\u0000./\u0005i\u0000\u0000"+
		"/\u0012\u0001\u0000\u0000\u000001\u0005e\u0000\u00001\u0014\u0001\u0000"+
		"\u0000\u000023\u0005s\u0000\u000034\u0005i\u0000\u00004m\u0005n\u0000"+
		"\u000056\u0005c\u0000\u000067\u0005o\u0000\u00007m\u0005s\u0000\u0000"+
		"89\u0005t\u0000\u00009:\u0005a\u0000\u0000:m\u0005n\u0000\u0000;<\u0005"+
		"c\u0000\u0000<=\u0005o\u0000\u0000=m\u0005t\u0000\u0000>?\u0005s\u0000"+
		"\u0000?@\u0005e\u0000\u0000@m\u0005c\u0000\u0000AB\u0005c\u0000\u0000"+
		"BC\u0005s\u0000\u0000Cm\u0005c\u0000\u0000DE\u0005l\u0000\u0000EF\u0005"+
		"o\u0000\u0000Fm\u0005g\u0000\u0000GH\u0005l\u0000\u0000Hm\u0005n\u0000"+
		"\u0000IJ\u0005s\u0000\u0000JK\u0005q\u0000\u0000KL\u0005r\u0000\u0000"+
		"Lm\u0005t\u0000\u0000MN\u0005a\u0000\u0000NO\u0005b\u0000\u0000Om\u0005"+
		"s\u0000\u0000PQ\u0005e\u0000\u0000QR\u0005x\u0000\u0000Rm\u0005p\u0000"+
		"\u0000ST\u0005f\u0000\u0000TU\u0005l\u0000\u0000UV\u0005o\u0000\u0000"+
		"VW\u0005o\u0000\u0000Wm\u0005r\u0000\u0000XY\u0005c\u0000\u0000YZ\u0005"+
		"e\u0000\u0000Z[\u0005i\u0000\u0000[m\u0005l\u0000\u0000\\]\u0005r\u0000"+
		"\u0000]^\u0005o\u0000\u0000^_\u0005u\u0000\u0000_`\u0005n\u0000\u0000"+
		"`m\u0005d\u0000\u0000ab\u0005t\u0000\u0000bc\u0005r\u0000\u0000cd\u0005"+
		"u\u0000\u0000de\u0005n\u0000\u0000em\u0005c\u0000\u0000fg\u0005m\u0000"+
		"\u0000gh\u0005i\u0000\u0000hm\u0005n\u0000\u0000ij\u0005m\u0000\u0000"+
		"jk\u0005a\u0000\u0000km\u0005x\u0000\u0000l2\u0001\u0000\u0000\u0000l"+
		"5\u0001\u0000\u0000\u0000l8\u0001\u0000\u0000\u0000l;\u0001\u0000\u0000"+
		"\u0000l>\u0001\u0000\u0000\u0000lA\u0001\u0000\u0000\u0000lD\u0001\u0000"+
		"\u0000\u0000lG\u0001\u0000\u0000\u0000lI\u0001\u0000\u0000\u0000lM\u0001"+
		"\u0000\u0000\u0000lP\u0001\u0000\u0000\u0000lS\u0001\u0000\u0000\u0000"+
		"lX\u0001\u0000\u0000\u0000l\\\u0001\u0000\u0000\u0000la\u0001\u0000\u0000"+
		"\u0000lf\u0001\u0000\u0000\u0000li\u0001\u0000\u0000\u0000m\u0016\u0001"+
		"\u0000\u0000\u0000np\u0003\u0019\f\u0000on\u0001\u0000\u0000\u0000pq\u0001"+
		"\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"ry\u0001\u0000\u0000\u0000su\u0005.\u0000\u0000tv\u0003\u0019\f\u0000"+
		"ut\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000"+
		"\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000ys\u0001\u0000"+
		"\u0000\u0000yz\u0001\u0000\u0000\u0000z\u0084\u0001\u0000\u0000\u0000"+
		"{}\u0007\u0000\u0000\u0000|~\u0007\u0001\u0000\u0000}|\u0001\u0000\u0000"+
		"\u0000}~\u0001\u0000\u0000\u0000~\u0080\u0001\u0000\u0000\u0000\u007f"+
		"\u0081\u0003\u0019\f\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u0085\u0001\u0000\u0000\u0000\u0084{\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0018\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0007\u0002\u0000\u0000\u0087\u001a\u0001"+
		"\u0000\u0000\u0000\u0088\u008a\u0007\u0003\u0000\u0000\u0089\u0088\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u0089\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0006\r\u0000\u0000\u008e\u001c\u0001\u0000"+
		"\u0000\u0000\t\u0000lqwy}\u0082\u0084\u008b\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}