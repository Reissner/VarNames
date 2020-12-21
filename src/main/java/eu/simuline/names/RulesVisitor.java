// Generated from eu/simuline/names/Rules.g4 by ANTLR 4.7
package eu.simuline.names;

    import eu.simuline.names.Category;
    import eu.simuline.names.CatGrammar;
    import eu.simuline.names.Compartment;

    import java.util.Collection;
    import java.util.HashSet;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RulesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RulesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RulesParser#parseRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRules(RulesParser.ParseRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#parseRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRule(RulesParser.ParseRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#parseTargets}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseTargets(RulesParser.ParseTargetsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#parseCategory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseCategory(RulesParser.ParseCategoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RulesParser#parseCompartment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseCompartment(RulesParser.ParseCompartmentContext ctx);
}