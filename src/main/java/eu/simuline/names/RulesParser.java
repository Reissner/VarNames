// Generated from eu/simuline/names/Rules.g4 by ANTLR 4.7
package eu.simuline.names;

    import eu.simuline.names.Category;
    import eu.simuline.names.CatGrammar;
    import eu.simuline.names.Compartment;

    import java.util.Collection;
    import java.util.HashSet;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RulesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WS=5, BlockComment=6, LinesComment=7, 
		NL=8, Package=9, Startup=10, Finish=11, Trans=12, Colon=13, Name=14;
	public static final int
		RULE_parseRules = 0, RULE_parseRule = 1, RULE_parseTargets = 2, RULE_parseCategory = 3, 
		RULE_parseCompartment = 4;
	public static final String[] ruleNames = {
		"parseRules", "parseRule", "parseTargets", "parseCategory", "parseCompartment"
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

	@Override
	public String getGrammarFileName() { return "Rules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    private List<Compartment> comps;
	    private CatGrammar carGr;
	    Collection<Category> targets;

	    CatGrammar setCats(Collection<Category> cats) {
	       return this.carGr = new CatGrammar(cats);
	    }

	    List<Compartment> getCategory() {
	        return this.comps;
	    }

	public RulesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseRulesContext extends ParserRuleContext {
		public TerminalNode Package() { return getToken(RulesParser.Package, 0); }
		public TerminalNode EOF() { return getToken(RulesParser.EOF, 0); }
		public List<ParseRuleContext> parseRule() {
			return getRuleContexts(ParseRuleContext.class);
		}
		public ParseRuleContext parseRule(int i) {
			return getRuleContext(ParseRuleContext.class,i);
		}
		public ParseRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterParseRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitParseRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RulesVisitor ) return ((RulesVisitor<? extends T>)visitor).visitParseRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseRulesContext parseRules() throws RecognitionException {
		ParseRulesContext _localctx = new ParseRulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseRules);

		    assert this.comps == null;// parser shall not be used for category files  

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(Package);
			setState(11);
			match(T__0);
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Startup || _la==Name) {
				{
				{
				setState(12);
				parseRule();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(18);
			match(T__1);
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseRuleContext extends ParserRuleContext {
		public Token Startup;
		public Token Name;
		public Token Finish;
		public TerminalNode Name() { return getToken(RulesParser.Name, 0); }
		public TerminalNode Trans() { return getToken(RulesParser.Trans, 0); }
		public ParseTargetsContext parseTargets() {
			return getRuleContext(ParseTargetsContext.class,0);
		}
		public TerminalNode Startup() { return getToken(RulesParser.Startup, 0); }
		public TerminalNode Finish() { return getToken(RulesParser.Finish, 0); }
		public ParseRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterParseRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitParseRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RulesVisitor ) return ((RulesVisitor<? extends T>)visitor).visitParseRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseRuleContext parseRule() throws RecognitionException {
		ParseRuleContext _localctx = new ParseRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parseRule);

		   this.targets = new HashSet<Category>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Startup) {
				{
				setState(21);
				((ParseRuleContext)_localctx).Startup = match(Startup);
				}
			}

			setState(24);
			((ParseRuleContext)_localctx).Name = match(Name);
			{
			setState(25);
			match(Trans);
			setState(26);
			match(T__2);
			setState(27);
			parseTargets();
			setState(28);
			match(T__3);
			}
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Finish) {
				{
				setState(30);
				((ParseRuleContext)_localctx).Finish = match(Finish);
				}
			}


			            Category cat = new Category((((ParseRuleContext)_localctx).Name!=null?((ParseRuleContext)_localctx).Name.getText():null));
			            if ((((ParseRuleContext)_localctx).Startup!=null?((ParseRuleContext)_localctx).Startup.getText():null) != null) {
			                this.carGr.addStart(cat);
			            }
			            if ((((ParseRuleContext)_localctx).Finish!=null?((ParseRuleContext)_localctx).Finish.getText():null) != null) {
			                this.carGr.addStop(cat);
			            }
			            this.carGr.addRule(cat, targets);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseTargetsContext extends ParserRuleContext {
		public Token Name;
		public TerminalNode Name() { return getToken(RulesParser.Name, 0); }
		public ParseTargetsContext parseTargets() {
			return getRuleContext(ParseTargetsContext.class,0);
		}
		public ParseTargetsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseTargets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterParseTargets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitParseTargets(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RulesVisitor ) return ((RulesVisitor<? extends T>)visitor).visitParseTargets(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseTargetsContext parseTargets() throws RecognitionException {
		ParseTargetsContext _localctx = new ParseTargetsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_parseTargets);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Name) {
				{
				setState(35);
				((ParseTargetsContext)_localctx).Name = match(Name);
				setState(36);
				parseTargets();
				}
			}

			this.targets.add(new Category((((ParseTargetsContext)_localctx).Name!=null?((ParseTargetsContext)_localctx).Name.getText():null)));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseCategoryContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RulesParser.EOF, 0); }
		public List<ParseCompartmentContext> parseCompartment() {
			return getRuleContexts(ParseCompartmentContext.class);
		}
		public ParseCompartmentContext parseCompartment(int i) {
			return getRuleContext(ParseCompartmentContext.class,i);
		}
		public ParseCategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseCategory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterParseCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitParseCategory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RulesVisitor ) return ((RulesVisitor<? extends T>)visitor).visitParseCategory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseCategoryContext parseCategory() throws RecognitionException {
		ParseCategoryContext _localctx = new ParseCategoryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parseCategory);

		    this.comps = new ArrayList<Compartment>();
		    assert this.carGr == null;// parser shall not be used for rules files  

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(41);
				parseCompartment();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseCompartmentContext extends ParserRuleContext {
		public Token shortName;
		public Token name;
		public TerminalNode Colon() { return getToken(RulesParser.Colon, 0); }
		public List<TerminalNode> Name() { return getTokens(RulesParser.Name); }
		public TerminalNode Name(int i) {
			return getToken(RulesParser.Name, i);
		}
		public ParseCompartmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseCompartment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterParseCompartment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitParseCompartment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RulesVisitor ) return ((RulesVisitor<? extends T>)visitor).visitParseCompartment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseCompartmentContext parseCompartment() throws RecognitionException {
		ParseCompartmentContext _localctx = new ParseCompartmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parseCompartment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((ParseCompartmentContext)_localctx).shortName = match(Name);
			setState(50);
			match(Colon);
			setState(51);
			((ParseCompartmentContext)_localctx).name = match(Name);
			this.comps.add(new Compartment((((ParseCompartmentContext)_localctx).shortName!=null?((ParseCompartmentContext)_localctx).shortName.getText():null),(((ParseCompartmentContext)_localctx).name!=null?((ParseCompartmentContext)_localctx).name.getText():null),""));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\209\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\2"+
		"\3\2\3\2\3\3\5\3\31\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\"\n\3\3\3\3\3"+
		"\3\4\3\4\5\4(\n\4\3\4\3\4\3\5\7\5-\n\5\f\5\16\5\60\13\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\2\28\2\f\3\2\2\2\4\30\3\2\2\2\6\'"+
		"\3\2\2\2\b.\3\2\2\2\n\63\3\2\2\2\f\r\7\13\2\2\r\21\7\3\2\2\16\20\5\4\3"+
		"\2\17\16\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\24\3\2\2"+
		"\2\23\21\3\2\2\2\24\25\7\4\2\2\25\26\7\2\2\3\26\3\3\2\2\2\27\31\7\f\2"+
		"\2\30\27\3\2\2\2\30\31\3\2\2\2\31\32\3\2\2\2\32\33\7\20\2\2\33\34\7\16"+
		"\2\2\34\35\7\5\2\2\35\36\5\6\4\2\36\37\7\6\2\2\37!\3\2\2\2 \"\7\r\2\2"+
		"! \3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\b\3\1\2$\5\3\2\2\2%&\7\20\2\2&(\5\6"+
		"\4\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\b\4\1\2*\7\3\2\2\2+-\5\n\6\2,+"+
		"\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62"+
		"\7\2\2\3\62\t\3\2\2\2\63\64\7\20\2\2\64\65\7\17\2\2\65\66\7\20\2\2\66"+
		"\67\b\6\1\2\67\13\3\2\2\2\7\21\30!\'.";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}