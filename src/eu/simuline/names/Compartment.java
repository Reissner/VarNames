package eu.simuline.names;

/**
 * Describe class Compartment here.
 *
 *
 * Created: Mon Apr 14 20:48:12 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class Compartment {

    String shortName;

    String name;

    String desc;

    /**
     * Creates a new <code>Compartment</code> instance.
     *
     */
    public Compartment(String shortName, String name, String desc) {
	this.shortName = shortName;
	this.name = name;
	this.desc = desc;
    }


    public String shortName() {
	return this.shortName;
    }

    public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("\n");
	buf.append(this.shortName + " \t " + this.name);
	buf.append("");
	return buf.toString();
    }

}
