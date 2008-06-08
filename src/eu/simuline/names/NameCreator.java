
package eu.simuline.names;

import eu.simuline.names.parser.ParseException;

import java.util.List;
import java.util.Collection;


/**
 * Describe class NameCreator here.
 *
 *
 * Created: Mon Apr 14 18:02:24 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class NameCreator {


    private final CatGrammar catGr;
    private Category currCat;
    private String partialName;

    /**
     * Creates a new <code>NameCreator</code> instance.
     *
     */
    public NameCreator(CatGrammar catGr) {
	this.catGr = catGr;
	this.currCat = null;
	reset();
    }


    List<Compartment> setNewCat(Category newCat) {
	this.currCat = newCat;
	return this.catGr.cat2comps(this.currCat);
    }

    boolean isStop() {
System.out.println("setcomp:       currCat: "+this.currCat);
	return this.catGr.isStop(this.currCat);
    }

    Collection<Category> nextCats() {
	System.out.println("nextCats: "+this.catGr.nextCats(this.currCat));
	return this.catGr.nextCats(this.currCat);
    }

    String reset() {
	return this.partialName = "";
    }

    String add(Compartment comp) {
	return this.partialName += comp.shortName();
    }

}
