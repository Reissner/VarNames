package eu.simuline.names;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    /* -------------------------------------------------------------------- *
     * inner classes.                                                       *
     * -------------------------------------------------------------------- */

    enum State {
	Matches, Incomplete, NoMatch;
    } // enum State 

    final class Pointer {
	private final int index;
	private final Category cat;
	private final Compartment comp;
	private final Set<Pointer> successors;
	private final Pointer predecessor;

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

	// returns success 
	State evolve() {
	    boolean matches;
	    int newIndex;
	    Pointer newPointer;

	    Collection<Category> possCats;
	    if (this.cat == null) {
		// the first compartment 
		possCats = NameAnalyzer.this.catGr.starts;
	    } else {
		possCats = NameAnalyzer.this.catGr.rules.get(this.cat);
	    }

	    // try all categories 
	    for (Category catCand : possCats) {
		assert catCand != null;// for creating new Pointer 
		List<Compartment> comps = 
		    NameAnalyzer.this.catGr.comps(catCand);

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
			if (State.Matches == newPointer.evolve()) {
			    this.successors.add(newPointer);
			}
		    }
		} // for comps 

// 		if (NameAnalyzer.this.catGr.stops.contains(cat)) {
// 		    // Here, the name may be finished. 
// 		}

	    } // for this.possCats

	    if (this.successors.isEmpty()) {
		// Here, no category was found at all. 
		if (this.cat == null) {
		    // did not find a start category 
		    return State.NoMatch;
		}
		if (!NameAnalyzer.this.catGr.isStop(this.cat)) {
		    // cannot add a free suffix: allowed only after end's 
		    return State.NoMatch;
		}


		newPointer = new Pointer(NameAnalyzer.this.name.length(), 
					 CatGrammar.FREE_CAT, 
					 null, 
					 this);
		this.successors.add(newPointer);
	    }
	    return State.Matches;
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

	private boolean isStop() {
	    return NameAnalyzer.this.catGr.isStop(this.cat); 
	}

	private boolean isFree() {
	    return this.cat == CatGrammar.FREE_CAT;
	}

	boolean isAllComplete() {
	    if (this.successors.size() == 0) {
		return isStop() || 
		    (isFree() 
		     && this.predecessor != null 
		     && this.predecessor.isStop());
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
	    if (this.cat != null) {// NOPMD 
		assert this.predecessor != null;
		res.append('-');
		res.append(NameAnalyzer.this.name
			   .substring(this.predecessor.index,this.index));
//		res.append(this.comp.shortName());
		if (NameAnalyzer.this.catGr.isStop(this.cat)) {
		    res.append('|');
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
		    throw new IllegalStateException
			("Potential structure is not linear. ");
	    }

	    return res.toString();
	}

	public String toString() {
	    StringBuilder res = new StringBuilder();
	    if (this.cat != null) {// NOPMD 
		assert this.predecessor != null;
		res.append('(');
		res.append(this.cat);
		res.append(':');

		res.append(NameAnalyzer.this.name
			   .substring(this.predecessor.index,this.index));
//		res.append(this.comp.shortName());
		res.append(')');
		if (NameAnalyzer.this.catGr.isStop(this.cat)) {
		    res.append('|');
		}
	    } else {
		assert this.predecessor == null;
		// Here, the string representation is empty. 
	    }
	    res.append(this.successors);
	    return res.toString();
	}

	// prevent overwriting: enable for (Weak)HashSet/Map 
	public boolean equals(Object obj) {
	    return super.equals(obj);
	}

	// prevent overwriting: enable for (Weak)HashSet/Map 
	public int hashCode() {
	    return super.hashCode();
	}

    } // class Pointer 

    /* -------------------------------------------------------------------- *
     * fields.                                                              *
     * -------------------------------------------------------------------- */


    private final CatGrammar catGr;
    private String name;
    private Pointer startPointer;

    /* -------------------------------------------------------------------- *
     * constructors.                                                        *
     * -------------------------------------------------------------------- */

    /**
     * Creates a new <code>NameAnalyzer</code> instance.
     *
     */
    public NameAnalyzer(CatGrammar catGr) {
	this.catGr = catGr;
	//analyze(name);
    }

    /* -------------------------------------------------------------------- *
     * methods.                                                             *
     * -------------------------------------------------------------------- */

   State analyze(String name) {
	this.name  = name;
	this.startPointer = new Pointer();
	return this.startPointer.evolve();
    }

    String structure() {
	return this.startPointer.toString();
    }

    String linStructure() {
	return this.startPointer.linString();
    }

    boolean isLinear() {
	return this.startPointer.isLinear();
    }

    boolean isAllComplete() {
	return this.startPointer.isAllComplete();
    }

    public static void main(String[] args) 
	throws FileNotFoundException, IOException {
	if (args.length != 2) {
	   throw new IllegalArgumentException
	       ("Usage: the name of the rules file " + 
		"and the name to be checked. ");
	}

	Files files = new Files(new File(args[0]));
	CatGrammar catGr = files.catGr;

	NameAnalyzer nCheck = new NameAnalyzer(catGr);
	nCheck.analyze(args[1]);
System.out.println("structure: "+nCheck.structure());
System.out.println("isLinear: "+nCheck.isLinear());
System.out.println("isLinear: "+nCheck.linStructure());
System.out.println("isAllComplete: "+nCheck.isAllComplete());


System.out.println("finished");

    }
}
