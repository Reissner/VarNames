package eu.simuline.names;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * A container for all files defining the grammar and for the grammar itself.
 * The grammar is described by a rules file <code>xxx.rls</code> 
 * in terms of categories. 
 * Each category <code>yyy</code> 
 * is defined by an according category file <code>yyy.cat</code> 
 * which is looked up in the folder in which the rules file resides. 
 * Since when searching for categories 
 * only files with the according ending are taken into account, 
 * essentially two or more rules files may share categories 
 * by placing them in a common folder. 
 * A much cleaner approach is to place each rules file in a separate folder 
 * and to share categories using links (not use windoofs).
 *
 * Created: Tue Apr 15 19:39:39 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class Files {

    private final static String END_CAT  = ".cat";
    private final static String END_CATD = ".cat$";

    final File rules;
    List<File> catFiles;
    CatGrammar catGr;

    /**
     * Creates a new <code>Files</code> instance.
     *
     */
    public Files(File rules) {
	this.rules = rules;
	reload();
    }

    private static RulesParser getParser(File file) throws IOException {
	ANTLRFileStream input = new ANTLRFileStream(file.getPath());
	RulesLexer lexer = new RulesLexer(input);
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	return new RulesParser(tokens);
    }

    /**
     * Based on the rules file, 
     * load the categories from the category files 
     * found in the same directory as the rules file. 
     * Then read the rules file into {@link #catGr}. 
     */
    @SuppressWarnings("PMD.NPathComplexity")
    List<String> reload() {

	// ensure that the rules file is readable... 
	if (!(this.rules.isFile() && this.rules.canRead())) {
	    throw new IllegalArgumentException
		("File \"" + "\" is no file or not readable. ");
	}
	// ... and determine the enclosing folder 
	File dir = this.rules.getParentFile();
	assert dir.isDirectory();

	// read all categories from the folder into catFile2cat 
	this.catFiles = Arrays.asList(dir.listFiles());
	String name;
	Map<File,Category> catFile2cat = new HashMap<File,Category>();
	for (File catFile : this.catFiles) {
	    name = catFile.getName();
	    if (!name.endsWith(END_CAT)) {
		// ignore files with the wrong ending 
		continue;
	    }
	    name = name.replaceFirst(END_CATD,"");
	    catFile2cat.put(catFile,new Category(name));
	}

System.out.println("catFile2cat: "+catFile2cat);

	// read the rules file 
	List<String> excMsgs = new ArrayList<String>();
	RulesParser rParser;
	try {
	    rParser = getParser(this.rules);
	    this.catGr = rParser.setCats(catFile2cat.values());
	    rParser.parseRules();
	    assert this.catGr != null;
	} catch (IOException e) {// NOPMD 
	    excMsgs.add(e.getMessage());
	} catch (RecognitionException e) {// NOPMD 
	    excMsgs.add(e.getMessage());
	}

	


	// read the category files 
	List<Compartment> lComp;
	for (File cand : catFile2cat.keySet()) {
System.out.println("cand: "+cand);
	    try {
		rParser = getParser(cand);
		rParser.parseCategory();
		lComp = rParser.getCategory();
System.out.println("lComp: "+lComp);
	    } catch (IOException e) {// NOPMD 
		excMsgs.add(e.getMessage());
		continue;
	    } catch (RecognitionException e) {// NOPMD 
		excMsgs.add(e.getMessage());
		continue;
	    }
//****new cat: bad 
	    try {
		this.catGr.map(catFile2cat.get(cand),lComp);
	    } catch (IllegalStateException e) {
		excMsgs.add(e.getMessage());
	    }
	} // fpr 

	// check the categories 
	try {
	    this.catGr.check();
	} catch (IllegalStateException e) {
	    excMsgs.add(e.getMessage());
	}

	if (excMsgs.isEmpty()) {
	    System.out.println("Successfully parsed grammar. ");
	} else {
	    System.out.println("Parsing the grammar found failures: \n"
			       +excMsgs);
	}

	return excMsgs;

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
