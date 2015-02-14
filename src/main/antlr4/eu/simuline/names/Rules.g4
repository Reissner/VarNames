/**
 * Provides parser for the description files of a variable name grammar. 
 * Two kinds of files are required: 
 * <ul>
 * <li>
 * one rules file <code>xxx.rls</code> 
 * parsed by {@link #parseRules(Collection)} 
 * <li>
 * the according category files <code>yyy.cat</code> 
 * parsed by {@link #parseCategory()}. 
 * </ul>
 *
 * This grammer is a translation 
 * from javacc into antlr 3.4 and further into antlr 4. 
 *
 * @see Files
 */
grammar Rules;


@parser::header {
    import eu.simuline.names.Category;
    import eu.simuline.names.CatGrammar;
    import eu.simuline.names.Compartment;

    import java.util.Collection;
    import java.util.HashSet;
} // @header 

@parser::members {

    private List<Compartment> comps;
    private CatGrammar carGr;
    Collection<Category> targets;

    CatGrammar setCats(Collection<Category> cats) {
       return this.carGr = new CatGrammar(cats);
    }

    List<Compartment> getCategory() {
        return this.comps;
    }
} // @parser::members

// ========================================================================== *
// Whitespace, newline and Comments                                           *
// ========================================================================== *\

WS : (' ' | '\n' | '\t' | '\r' | '\f') -> skip;

BlockComment : '/*' (~'*' | '*' ~'/')* '*/';
LinesComment : '//' (~'\n')* '\n' -> skip;//{$type=NL;};

NL : ('\r'? '\n');

// ========================================================================== *
// Tokens                                                                     *
// ========================================================================== *\

Package : 'Package';
Startup : '|->';// denotes the start of a variable name
Finish : '->|'; // denotes the possible end of a variable name 
Trans : '-->';  // denotes that the variable name has to go on 
Colon : ':';
//Text : Name+
Name : Letter (Identifier)*;

fragment Identifier : Letter | Digit | '_' | '-';
fragment Letter : 'a'..'z' | 'A'..'Z';
fragment Digit : '0'..'9';

//test [String loc] : 
//        Package '}' EOF;

/**
 * Parses a rules file <code>xxx.rls</code> which has the following shape: 
 * Package {yyy} with comments //... 
 * Here, 'yyy' is a (possibly empty) set of rules. 
 * Rules are based on a set of categories defined by category files. 
 * Thus the categories must be parsed first 
 * and the categories must be passed to the rules parser. 
 *
 * @see #parseCategory()
 */
parseRules 
@init {
    assert this.comps == null;// parser shall not be used for category files  
}
    :         Package '{' parseRule* '}' EOF;

parseRule 
@init {
   this.targets = new HashSet<Category>();
} // init 
    : Startup? Name ( Trans '(' parseTargets ')' ) Finish?
        {
            Category cat = new Category($Name.text);
            if ($Startup.text != null) {
                this.carGr.addStart(cat);
            }
            if ($Finish.text != null) {
                this.carGr.addStop(cat);
            }
            this.carGr.addRule(cat, targets);
        }
    ;

parseTargets : (Name parseTargets)? 
        {this.targets.add(new Category($Name.text));};

/**
 * Parses a category file <code>xxx.cat</code> which has the following shape: 
 * It may contain C-style line comments of the form //... 
 * Disregarding comments it consists in lines of the form 
 * <code>shortName : name</code>, 
 * where the short name occurs in the variable name 
 * and the (long) name is more intuitive. 
 * Besides the long name, more explanation can be given as comment. 
 */
parseCategory 
@init {
    this.comps = new ArrayList<Compartment>();
    assert this.carGr == null;// parser shall not be used for rules files  
}
    : parseCompartment* EOF ;

parseCompartment : shortName = Name Colon name=Name
        {this.comps.add(new Compartment($shortName.text,$name.text,""));};



