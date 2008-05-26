
package eu.simuline.names;

import eu.simuline.names.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


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


    private Files files;

    /**
     * Creates a new <code>NameCreator</code> instance.
     *
     */
    public NameCreator(File rules) 
	throws FileNotFoundException, IOException, ParseException {
	this.files = new Files(rules);	    
    }



    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	   throw new IllegalArgumentException
	       ("Usage: the name of the rules file. ");
	}

	NameCreator nCreator = new NameCreator(new File(args[0]));
	CatGrammar catGr = nCreator.files.catGr;
	CheckerFrame frame = new CheckerFrame(catGr);
	frame.setCats(catGr.starts);
	frame.setVisible(true);
System.out.println("finished");

    }

}
