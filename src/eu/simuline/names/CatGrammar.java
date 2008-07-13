package eu.simuline.names;

import eu.simuline.names.parser.ParseException;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Describes the grammar of a namespace. 
 * The atoms of the grammar are the categories collected in {@link #cats}. 
 * Essentially, a category is given by a list/collection of compartments 
 * allowed for this category. 
 * The corresponding mapping is given by {@link #cat2comps}. 
 * <p>
 * The rules of the grammar given by {@link #rules} are the transitions 
 * between the categories. 
 * Certain categories are distinguished in that a name may start with it 
 * (the ones registered in {@link #starts}) 
 * and that a name may end with such a category 
 * (the ones registered in {@link #stops}). 
 * For the latter note that an appendix after an end-category 
 * is always allowed. 
 *
 *
 * Created: Mon Apr 14 20:13:15 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class CatGrammar {

    public final static Category FREE_CAT = new Category("FREE_CAT");

    /* --------------------------------------------------------------------- *
     * fields.                                                               *
     * --------------------------------------------------------------------- */

    private Collection<Category> cats;
    Map<Category,Collection<Category>> rules;

    /**
     * The subset of {@link #cats} which may serve as start categories. 
     * These are the categories a name may start with. 
     * When this grammar is created, this set is empty 
     * and only {@link #addStart(Category)} allows to add a start category. 
     * It also keeps track of validity. 
     */
            Set<Category         > starts;
    /**
     * The subset of {@link #cats} which may serve as stop categories. 
     * These are the categories a name may stop with. 
     * Also only stop categories may be followed by a free suffix. 
     * When this grammar is created, this set is empty 
     * and only {@link #addStop(Category)} allows to add a start category. 
     * It also keeps track of validity. 
     */
    private Set<Category         > stops;
    private Map<Category,List<Compartment>> cat2comps;

    /* --------------------------------------------------------------------- *
     * constructors.                                                         *
     * --------------------------------------------------------------------- */

    /**
     * Creates a new <code>CatGrammar</code> instance 
     * with the given categories but without rules, 
     * without starts and without stops. 
     * For each category the set of compartments is empty. 
     *
     * @param cats 
     *    the collection of categories 
     *    which shall serve as foundation for this grammar. 
     */
    public CatGrammar(Collection<Category> cats) {
	this.cats = cats;
	this.rules  = new HashMap<Category,Collection<Category>>();
	this.starts = new HashSet<Category>();
	this.stops  = new HashSet<Category>();
	this.cat2comps = new HashMap<Category,List<Compartment>>();
    }

    /* --------------------------------------------------------------------- *
     * methods                                                               *
     * --------------------------------------------------------------------- */

    /**
     * Adds <code>targetC</code> 
     * to the set {@link #starts} of start categories. 
     *
     * @param targetC 
     *    a <code>Category</code> which is supposed to be in {@link #cats}. 
     * @throws ParseException 
     *    if 
     *    <ul>
     *    <li>
     *    either <code>targetC</code> is not in {@link #cats}. 
     *    <li>
     *    either <code>targetC</code> was tried to be added more than once. 
     *    </ul>
     */
    public void addStart(Category targetC) throws ParseException {

	if (!this.cats.contains(targetC)) {
	    throw new ParseException
		("Target \"" + targetC + "\"is no valid category. ");
	}

	boolean added = this.starts.add(targetC);
	if (!added) {
	    throw new ParseException
		("Added start category \"" + targetC + "\" more than once. ");
	}
    }

    /**
     * Adds <code>sourceC</code> 
     * to the set {@link #starts} of stop categories. 
     *
     * @param sourceC 
     *    a <code>Category</code> which is supposed to be in {@link #cats}. 
     * @throws ParseException 
     *    if 
     *    <ul>
     *    <li>
     *    either <code>sourceC</code> is not in {@link #cats}. 
     *    <li>
     *    either <code>sourceC</code> was tried to be added more than once. 
     *    </ul>
     */
    public void addStop(Category sourceC) throws ParseException {
	if (!this.cats.contains(sourceC)) {
	    throw new ParseException
		("Source \"" + sourceC + "\"is no valid category. ");
	}

	boolean added = this.stops.add(sourceC);
	if (!added) {
	    throw new ParseException
		("Added stop category \"" + sourceC + "\" more than once. ");
	}
    }

    public void addRule(Category sourceC, 
			Collection<Category> targets) throws ParseException {

	if (!this.cats.contains(sourceC)) {
	    throw new ParseException
		("Source or target is no category. ");
	}
	if (!this.cats.containsAll(targets)) {
	    throw new ParseException
		("At least one target is no category. ");//****
	}
	Object overwritten = this.rules.put(sourceC,targets);
	if (overwritten != null) {
	    throw new ParseException
		("Overwritten rule for source \"" + sourceC +"\". ");
	}

    }

    void check() throws ParseException {
	Collection<Category> allTargets = new HashSet<Category>();
	for (Category source : this.rules.keySet()) {
	    allTargets.addAll(this.rules.get(source));
	}
	allTargets.removeAll(this.rules.keySet());
	// Here, allTargets contains the categories without rules. 

	// for all categories occurring, a rule must be present. 
	if (!allTargets.isEmpty()) {
	    throw new ParseException
		("Found no rule for categries" + allTargets + ". ");
	}
    }

/*
    public void addRule(Category sourceC, 
			Category targetC) throws ParseException {

	if (!(this.cats.contains(sourceC) && this.cats.contains(targetC))) {
	    throw new ParseException
		("Either source or target is/are no category. ");
	}
	Collection<Category> targetList = this.rules.get(sourceC);
	if (targetList == null) {
	    targetList = new ArrayList<Category>();
	    this.rules.put(sourceC,targetList);
	}
	if (targetList.contains(targetC)) {
	    throw new ParseException// **** not correct 
		("Overwritten successors of category \"" + sourceC + "\". ");
	}
	targetList.add(targetC);
    }

*/
    boolean isStart(Category cat) {
	return this.starts.contains(cat);
    }

    boolean isStop(Category cat) {
	return this.stops.contains(cat);
    }

    List<Compartment> comps(Category cat) {
	return this.cat2comps.get(cat);
    }

    public void map(Category cat, 
		    List<Compartment> comps) throws ParseException {
	List<Compartment> overwritten = this.cat2comps.put(cat,comps);
	if (overwritten != null) {
	    throw new ParseException
		("Reassigned compartments of category \"" + cat + "\". ");
	}
    }

    public List<Compartment> cat2comps(Category cat) {
	return this.cat2comps.get(cat);
    }

    public Collection<Category> nextCats(Category curr) {
	return this.rules.get(curr);
    }

    public String toString() {
	StringBuffer res = new StringBuffer();
	res.append("<CatGr>");
	res.append("<Starts>");
	res.append(this.starts);
	res.append("</Starts>");
	res.append("<Rules>");
	res.append(this.rules);
	res.append("</Rules>");
	res.append("<Cat2comps>");
	res.append(this.cat2comps);
	res.append("</Cat2comps>");
	res.append("<Stops>");
	res.append(this.stops);
	res.append("</Stops>");
	res.append("</CatGr>");
	return res.toString();
    }

}
