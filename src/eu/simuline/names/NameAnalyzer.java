package eu.simuline.names;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.IOException;
import eu.simuline.names.parser.ParseException;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.List;

/**
 * Analyzes the structure of a name in view of a grammar. 
 * This includes all possible interpretations of the name in the grammar. 
 * In particular it can be seen if the name is correct 
 * (if there is an interpretation) and if it can be interpreted uniquely. 
 * The different interpretations also include free suffixes. 
 *
 *
 * Created: Mon May 26 20:18:35 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class NameAnalyzer {

    class Pointer {
	private int index;
	private Category cat;
	private Compartment comp;
	private Set<Pointer> successors;
	private Pointer predecessor;

	Pointer(int index, 
		Category cat, 
		Compartment comp,
		Pointer predecessor) {
	    this.index = index;
	    this.cat = cat;
	    this.comp = comp;
	    this.successors = new HashSet<Pointer>();
	    this.predecessor = predecessor;
	}

	Pointer() {
	    this(0, null, null, null);
	}

	void evolve() {
	    boolean matches;
	    int newIndex;
	    Pointer newPointer;

	    Collection<Category> possCats;
	    if (this.cat == null) {
		possCats = NameAnalyzer.this.catGr.starts;
	    } else {
		possCats = NameAnalyzer.this.catGr.rules.get(this.cat);
	    }

	    // try all categories 
	    for (Category catCand : possCats) {
		List<Compartment> comps = 
		    NameAnalyzer.this.catGr.cat2comps.get(catCand);

		// try all compartments of the category catCand 
		for (Compartment comp : comps) {
		    matches = NameAnalyzer.this.name
			.startsWith(comp.shortName(), this.index);
		    if (matches) {
			newIndex = this.index + comp.shortName().length();
			newPointer = new Pointer(newIndex, 
						 catCand, 
						 comp, 
						 this);
			newPointer.evolve();
			this.successors.add(newPointer);
		    }
		} // for comps 

// 		if (NameAnalyzer.this.catGr.stops.contains(cat)) {
// 		    // Here, the name may be finished. 
// 		}

	    } // for this.possCats
	}

	private Pointer getSingleSuccessor() {
	    return this.successors.toArray(new Pointer[1])[0];
	}

	boolean isLinear() {
	    switch (this.successors.size()) {
		case 0:
		    return true;
		case 1:
		    getSingleSuccessor().isLinear();
		    return true;
		default:
		    return false;
	    }
	}

	boolean isAllComplete() {

	    if (this.successors.size() == 0) {
		return NameAnalyzer.this.catGr.stops.contains(this.cat);
	    }

	    assert this.successors.size() > 0;
	    for (Pointer succ : this.successors) {
		if (!succ.isAllComplete()) {
		    return false;
		}
	    }
	    return true;

	}

	String linString() {
	    StringBuilder res = new StringBuilder();
	    if (this.cat != null) {
		assert this.predecessor != null;
		res.append("-");
		res.append(this.comp.shortName());
		if (NameAnalyzer.this.catGr.stops.contains(this.cat)) {
		    res.append("|");
		}
	    } else {
		assert this.predecessor == null;
		res.append(">-");
		// Here, the string representation is empty. 
	    }
	    switch (this.successors.size()) {
		case 0:
		    // append nothing
		    break;
		case 1:
		    res.append(getSingleSuccessor().linString());
		    break;
		default:
		    throw new IllegalStateException();
	    }

	    return res.toString();
	}

	public String toString() {
	    StringBuilder res = new StringBuilder();
	    if (this.cat != null) {
		assert this.predecessor != null;
		res.append("(");
		res.append(this.cat);
		res.append(":");
		res.append(this.comp.shortName());
		res.append(")");
		if (NameAnalyzer.this.catGr.stops.contains(this.cat)) {
		    res.append("|");
		}
	    } else {
		assert this.predecessor == null;
		// Here, the string representation is empty. 
	    }
	    res.append(this.successors);
	    return res.toString();
	}
    } // class Pointer 


    private CatGrammar catGr;
    private String name;
    private Pointer startPointer;

    /**
     * Creates a new <code>NameAnalyzer</code> instance.
     *
     */
    public NameAnalyzer(File rules, String name) 
	throws FileNotFoundException, IOException, ParseException {
	this.catGr = new Files(rules).catGr;
	this.name  = name;
	this.startPointer = new Pointer();
    }

    public static void main(String[] args) throws Exception {
	if (args.length != 2) {
	   throw new IllegalArgumentException
	       ("Usage: the name of the rules file " + 
		"and the name to be checked. ");
	}

	NameAnalyzer nCheck = new NameAnalyzer(new File(args[0]), args[1]);
	CatGrammar catGr = nCheck.catGr;
	nCheck.startPointer.evolve();
System.out.println("structure: "+nCheck.startPointer);
System.out.println("isLinear: "+nCheck.startPointer.isLinear());
System.out.println("isLinear: "+nCheck.startPointer.linString());
System.out.println("isAllComplete: "+nCheck.startPointer.isAllComplete());


System.out.println("finished");

    }
}
