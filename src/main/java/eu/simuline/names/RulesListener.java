// Generated from eu/simuline/names/Rules.g4 by ANTLR 4.7
package eu.simuline.names;

    import eu.simuline.names.Category;
    import eu.simuline.names.CatGrammar;
    import eu.simuline.names.Compartment;

    import java.util.Collection;
    import java.util.HashSet;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RulesParser}.
 */
public interface RulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RulesParser#parseRules}.
	 * @param ctx the parse tree
	 */
	void enterParseRules(RulesParser.ParseRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#parseRules}.
	 * @param ctx the parse tree
	 */
	void exitParseRules(RulesParser.ParseRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void enterParseRule(RulesParser.ParseRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void exitParseRule(RulesParser.ParseRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#parseTargets}.
	 * @param ctx the parse tree
	 */
	void enterParseTargets(RulesParser.ParseTargetsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#parseTargets}.
	 * @param ctx the parse tree
	 */
	void exitParseTargets(RulesParser.ParseTargetsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#parseCategory}.
	 * @param ctx the parse tree
	 */
	void enterParseCategory(RulesParser.ParseCategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#parseCategory}.
	 * @param ctx the parse tree
	 */
	void exitParseCategory(RulesParser.ParseCategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RulesParser#parseCompartment}.
	 * @param ctx the parse tree
	 */
	void enterParseCompartment(RulesParser.ParseCompartmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RulesParser#parseCompartment}.
	 * @param ctx the parse tree
	 */
	void exitParseCompartment(RulesParser.ParseCompartmentContext ctx);
}