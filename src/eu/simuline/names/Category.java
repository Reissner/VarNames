package eu.simuline.namecheck;

/**
 * Describe class Category here.
 *
 *
 * Created: Mon Apr 14 19:27:31 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class Category {

    private String name;

    public Category(String name) {
	this.name = name;
    }
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

    public int hashCode() {
	return this.name.hashCode();
    }
 
} // class Category 
