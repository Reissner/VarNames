package eu.simuline.names;

/**
 * Describe class Category here.
 *
 *
 * Created: Mon Apr 14 19:27:31 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public final class Category {

    /* -------------------------------------------------------------------- *
     * attributes.                                                          *
     * -------------------------------------------------------------------- */

    // must be final to guarantee a valid {@link #hashCode()}. 
    private final String name;

    /* -------------------------------------------------------------------- *
     * constructors.                                                        *
     * -------------------------------------------------------------------- */

    public Category(String name) {
	this.name = name;
    }

    /* -------------------------------------------------------------------- *
     * methods.                                                             *
     * -------------------------------------------------------------------- */

    public String toString() {
	return this.name;
    }

    public boolean equals(Object obj) {
	Category other = (Category)obj;
	if (other == null) {
	    return false;
	}

	return this.toString().equals(other.toString());
    }

    // makes it addable to a Hash Set/Map 
    public int hashCode() {
	return this.name.hashCode();
    }
 
} // class Category 
