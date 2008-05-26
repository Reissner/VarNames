package eu.simuline.names;

import eu.simuline.names.parser.RulesParser;
import eu.simuline.names.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Describe class Files here.
 *
 *
 * Created: Tue Apr 15 19:39:39 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class Files {

    private final static String END_CAT  = ".cat";
    private final static String END_CATD = ".cat$";

    File rules;
    List<File> catFiles;
    CatGrammar catGr;

    /**
     * Creates a new <code>Files</code> instance.
     *
     */
    public Files(File rules) 
	throws FileNotFoundException, IOException, ParseException {
	this.rules = rules;

	if (!(this.rules.isFile() && this.rules.canRead())) {
	    throw new IllegalArgumentException
		("File \"" + "\" is no file or not readable. ");
	}

	File dir = this.rules.getParentFile();
	assert dir.isDirectory();


	this.catFiles = Arrays.asList(dir.listFiles());
	File catFile;
	String name;
	Category cat;
	Map<File,Category> catFile2cat = new HashMap<File,Category>();
	Iterator<File> itFiles = this.catFiles.iterator();
	while (itFiles.hasNext()) {
	    catFile = itFiles.next();
	    name = catFile.getName();
	    if (!name.endsWith(END_CAT)) {
		continue;
	    }
	    name = name.replaceFirst(END_CATD,"");
	    catFile2cat.put(catFile,new Category(name));
	}

System.out.println("catFile2cat: "+catFile2cat);



	FileReader rd = new FileReader(this.rules);

	RulesParser rParser = new RulesParser((Reader)null);
	rParser.ReInit(rd);
	this.catGr = rParser.parseRules(catFile2cat.values());



	List<Compartment> lComp;
	for (File cand : catFile2cat.keySet()) {
System.out.println("cand: "+cand);
	    rd = new FileReader(cand);
	    rParser.ReInit(rd);
	    lComp = rParser.parseCategory();
System.out.println("lComp: "+lComp);
//****new cat: bad 
	    this.catGr.map(catFile2cat.get(cand),lComp);
	}

/*


	RulesParser rParser = new RulesParser((Reader)null);
	FileReader rd;
	List<Category> cats = new ArrayList<Category>();
	Iterator<File> itFiles = this.catFiles.iterator();
	List<Compartment> lComp;
	while (itFiles.hasNext()) {
	    catFile = itFiles.next();
	    name = catFile.getName();
	    if (!name.endsWith(END_CAT)) {
		itFiles.remove();
		continue;
	    }
	    name = name.replaceFirst(END_CATD,"");
	    cat = new Category(name);
	    cats.add(cat);

	    rd = new FileReader(catFile);
	    rParser.ReInit(rd);
	    lComp = rParser.parseCategory();
System.out.println("lComp: "+lComp);
//****new cat: bad 
	    this.catGr.map(cat,lComp);
	}
	System.out.println("categories: "+cats);


	rd = new FileReader(this.rules);

	rParser = new RulesParser((Reader)null);
	rParser.ReInit(rd);
	this.catGr = rParser.parseRules(cats);
	System.out.println("grammar: "+catGr);

*/
// 	List<Compartment> lComp;
// 	for (File cand : this.catFiles) {
// 	    name = cand.getName();
// System.out.println("cand: "+cand);
// 	    rd = new FileReader(cand);
// 	    rParser.ReInit(rd);
// 	    lComp = rParser.parseCategory();
// System.out.println("lComp: "+lComp);
// //****new cat: bad 
// 	    this.catGr.map(new Category(name),lComp);
// 	}


    }

}
