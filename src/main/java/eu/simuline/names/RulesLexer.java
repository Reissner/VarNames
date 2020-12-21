// Generated from eu/simuline/names/Rules.g4 by ANTLR 4.7
package eu.simuline.names;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RulesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WS=5, BlockComment=6, LinesComment=7, 
		NL=8, Package=9, Startup=10, Finish=11, Trans=12, Colon=13, Name=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "WS", "BlockComment", "LinesComment", 
		"NL", "Package", "Startup", "Finish", "Trans", "Colon", "Name", "Identifier", 
		"Letter", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", null, null, null, null, "'Package'", 
		"'|->'", "'->|'", "'-->'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "WS", "BlockComment", "LinesComment", "NL", 
		"Package", "Startup", "Finish", "Trans", "Colon", "Name"
	};
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


	public RulesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rules.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20w\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\7\78\n\7\f\7\16\7;\13\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\bD\n\b\f\b\16"+
		"\bG\13\b\3\b\3\b\3\b\3\b\3\t\5\tN\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\7\17j\n\17\f\17\16\17m\13\17\3\20\3\20\3\20\5\20r\n\20\3\21"+
		"\3\21\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\2!\2#\2\3\2\b\5\2\13\f\16\17\"\"\3\2,,\3\2\61"+
		"\61\3\2\f\f\4\2//aa\4\2C\\c|\2z\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\3%\3\2\2\2\5\'\3\2\2\2\7)\3\2\2\2\t+\3\2\2\2\13-\3\2\2\2\r\61\3\2\2\2"+
		"\17?\3\2\2\2\21M\3\2\2\2\23Q\3\2\2\2\25Y\3\2\2\2\27]\3\2\2\2\31a\3\2\2"+
		"\2\33e\3\2\2\2\35g\3\2\2\2\37q\3\2\2\2!s\3\2\2\2#u\3\2\2\2%&\7}\2\2&\4"+
		"\3\2\2\2\'(\7\177\2\2(\6\3\2\2\2)*\7*\2\2*\b\3\2\2\2+,\7+\2\2,\n\3\2\2"+
		"\2-.\t\2\2\2./\3\2\2\2/\60\b\6\2\2\60\f\3\2\2\2\61\62\7\61\2\2\62\63\7"+
		",\2\2\639\3\2\2\2\648\n\3\2\2\65\66\7,\2\2\668\n\4\2\2\67\64\3\2\2\2\67"+
		"\65\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7,\2"+
		"\2=>\7\61\2\2>\16\3\2\2\2?@\7\61\2\2@A\7\61\2\2AE\3\2\2\2BD\n\5\2\2CB"+
		"\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7\f\2\2I"+
		"J\3\2\2\2JK\b\b\2\2K\20\3\2\2\2LN\7\17\2\2ML\3\2\2\2MN\3\2\2\2NO\3\2\2"+
		"\2OP\7\f\2\2P\22\3\2\2\2QR\7R\2\2RS\7c\2\2ST\7e\2\2TU\7m\2\2UV\7c\2\2"+
		"VW\7i\2\2WX\7g\2\2X\24\3\2\2\2YZ\7~\2\2Z[\7/\2\2[\\\7@\2\2\\\26\3\2\2"+
		"\2]^\7/\2\2^_\7@\2\2_`\7~\2\2`\30\3\2\2\2ab\7/\2\2bc\7/\2\2cd\7@\2\2d"+
		"\32\3\2\2\2ef\7<\2\2f\34\3\2\2\2gk\5!\21\2hj\5\37\20\2ih\3\2\2\2jm\3\2"+
		"\2\2ki\3\2\2\2kl\3\2\2\2l\36\3\2\2\2mk\3\2\2\2nr\5!\21\2or\5#\22\2pr\t"+
		"\6\2\2qn\3\2\2\2qo\3\2\2\2qp\3\2\2\2r \3\2\2\2st\t\7\2\2t\"\3\2\2\2uv"+
		"\4\62;\2v$\3\2\2\2\t\2\679EMkq\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}