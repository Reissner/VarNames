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
 * Describe class NameChecker here.
 *
 *
 * Created: Mon May 26 20:18:35 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class NameChecker {

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
		possCats = NameChecker.this.catGr.starts;
	    } else {
		possCats = NameChecker.this.catGr.rules.get(this.cat);
	    }

	    // try all categories 
	    for (Category catCand : possCats) {
		List<Compartment> comps = 
		    NameChecker.this.catGr.cat2comps.get(catCand);

		// try all compartments of the category catCand 
		for (Compartment comp : comps) {
		    matches = NameChecker.this.name
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

// 		if (NameChecker.this.catGr.stops.contains(cat)) {
// 		    // Here, the name may be finished. 
// 		}

	    } // for this.possCats
	}

	public String toString() {
	    StringBuilder res = new StringBuilder();
	    if (this.cat != null) {
		assert this.predecessor != null;
		res.append("{");
		res.append(this.cat);
		res.append("}");
		res.append("(");
		res.append(this.comp.shortName());
		res.append(")");
		if (NameChecker.this.catGr.stops.contains(this.cat)) {
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
     * Creates a new <code>NameChecker</code> instance.
     *
     */
    public NameChecker(File rules, String name) 
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

	NameChecker nCheck = new NameChecker(new File(args[0]), args[1]);
	CatGrammar catGr = nCheck.catGr;
	nCheck.startPointer.evolve();
System.out.println("structure: "+nCheck.startPointer);
	


// 	NameCreator nCheck = new NameCreator(new File(args[0]));
// 	CatGrammar catGr = nCheck.files.catGr;
// 	CheckerFrame frame = new CheckerFrame(catGr);
// 	frame.setCats(catGr.starts);
// 	frame.setVisible(true);
System.out.println("finished");

    }
}
