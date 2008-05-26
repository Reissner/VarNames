package eu.simuline.namecheck;

import eu.simuline.namecheck.parser.ParseException;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Describe class CatGrammar here.
 *
 *
 * Created: Mon Apr 14 20:13:15 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class CatGrammar {

    Collection<Category> cats;
    Map<Category,Collection<Category>> rules;
    Set<Category         > starts;
    Set<Category         > stops;
    Map<Category,List<Compartment>> cat2comps;

    /**
     * Creates a new <code>CatGrammar</code> instance.
     *
     */
    public CatGrammar(Collection<Category> cats) {
	this.cats = cats;
	this.rules  = new HashMap<Category,Collection<Category>>();
	this.starts = new HashSet<Category>();
	this.stops  = new HashSet<Category>();
	this.cat2comps = new HashMap<Category,List<Compartment>>();
    }

    public void addStart(Category targetC) throws ParseException {

	if (!this.cats.contains(targetC)) {
	    throw new ParseException
		("Target \"" + targetC + "\"is no category. ");
	}

	boolean added = this.starts.add(targetC);
	if (!added) {
	    throw new ParseException
		("Added start category \"" + targetC + "\" more than once. ");
	}
    }

    public void addStop(Category sourceC) throws ParseException {
	if (!this.cats.contains(sourceC)) {
	    throw new ParseException
		("Source \"" + sourceC + "\"is no category. ");
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
    public boolean isStop(Category cat) {
	return this.stops.contains(cat);
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
