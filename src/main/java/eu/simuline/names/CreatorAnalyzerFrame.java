package eu.simuline.names;

import eu.simuline.names.parser.ParseException;

import eu.simuline.util.GifResource;

import org.javalobby.icons20x20.New;
import org.javalobby.icons20x20.Magnify;
import org.javalobby.icons20x20.RotCCLeft;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JSeparator;

/**
 * Describe class CreatorAnalyzerFrame here.
 *
 *
 * Created: Tue Apr 15 19:49:36 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class CreatorAnalyzerFrame extends JFrame {

    private static final long serialVersionUID = -2479143000061671589L;

    private final static String STATE_COMPLETE = "Complete";
    private final static String STATE_NOT_COMPLETE = "Not Yet Complete";


    DefaultComboBoxModel combCat;
    DefaultComboBoxModel combComp;


    NameCreator  creator;
    NameAnalyzer analyzer;

    Files files;

    // null if none is selected 
    JTextField nameField;

    JLabel creatorStatusLabel;

    JLabel structLabel;
    JLabel linearLabel;

    JLabel analyzerStatusLabel;


    /**
     * Creates a new <code>CreatorAnalyzerFrame</code> instance.
     *
     */
    public CreatorAnalyzerFrame(Files files) {
	super("Checker Frame");
	this.files = files;
	reloadGrammar();
// // **** here, a popup must provide the error messages. 
// 	this.files.reload();
// 	this.creator  = new NameCreator (this.files.catGr);
// 	this.analyzer = new NameAnalyzer(this.files.catGr);


	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setMenuBar();//actions



	Container content = getContentPane();
	content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));


	add(new JSeparator());
	Box partialNameB = Box.createHorizontalBox();
	partialNameB.add(new JLabel("partial name: "));
	// setText(this.partialName)
	this.nameField = new JTextField();
	partialNameB.add(this.nameField);
	add(partialNameB);
	add(Box.createVerticalGlue());
	add(Box.createVerticalStrut(40));



	add(new JSeparator());
	Box headlineC = Box.createHorizontalBox();
	headlineC.add(new JLabel("Creator"));
	headlineC.add(Box.createHorizontalGlue());
	add(headlineC);


	Box catsCmptms = Box.createHorizontalBox();
	Box categories = Box.createVerticalBox();
	categories.add(new JLabel("categories"));

	this.combCat = new DefaultComboBoxModel();
	JComboBox comboBox = new JComboBox(this.combCat);

	comboBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    switch (e.getStateChange()) {
			case ItemEvent.SELECTED:
			    System.out.println("1selected: "+e.getItem());
			    CreatorAnalyzerFrame.this
				.setCat((Category)e.getItem());
			    break;
			case ItemEvent.DESELECTED:
			    System.out.println("1deselected: ");
			    break;
			default:
			    throw new IllegalStateException();
		    }
		    System.out.println(": "+e.getItem());

		    
		}
	    });
	categories.add(comboBox);
	catsCmptms.add(categories);


	Box compartments = Box.createVerticalBox();
	compartments.add(new JLabel("compartments"));

	this.combComp = new DefaultComboBoxModel();
	comboBox = new JComboBox(this.combComp);
	comboBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    switch (e.getStateChange()) {
			case ItemEvent.SELECTED:
			    if (e.getItem() instanceof String) {
				return;
			    }

			    System.out.println("2selected-: "+e.getItem());
			    CreatorAnalyzerFrame.this.setComp
				((Compartment)e.getItem());
			    break;
			case ItemEvent.DESELECTED:
			    System.out.println("3deselected: ");
			    break;
			default:
			    throw new IllegalStateException();
		    }
		    //System.out.println(": "+e.getItem());
		}
	    });

	compartments.add(comboBox);
	catsCmptms.add(compartments);

	add(catsCmptms);
	add(Box.createVerticalGlue());
	add(Box.createVerticalStrut(40));


this.creatorStatusLabel = new JLabel();
	add(this.creatorStatusLabel);


	add(new JSeparator());



	Box headlineA = Box.createHorizontalBox();
	headlineA.add(new JLabel("Analyzer"));
	headlineA.add(Box.createHorizontalGlue());
	add(headlineA);


	Box structure = Box.createHorizontalBox();
	structure.add(new JLabel("Structure: "));
	this.structLabel = new JLabel();
	structure.add(this.structLabel);
	structure.add(Box.createHorizontalGlue());
	add(structure);

	Box linear = Box.createHorizontalBox();
	linear.add(new JLabel("Linear: "));
	this.linearLabel = new JLabel();
	linear.add(this.linearLabel);
	linear.add(Box.createHorizontalGlue());
	add(linear);


	Box analyzerStatus = Box.createHorizontalBox();
	analyzerStatus.add(new JLabel("Status: "));
	this.analyzerStatusLabel = new JLabel();
	analyzerStatus.add(this.analyzerStatusLabel);
	analyzerStatus.add(Box.createHorizontalGlue());
	add(analyzerStatus);


	resetCreator();

	setSize(800,300);
	//this.frame.pack();
	setVisible(true);
    }


    void setMenuBar() {//Actions actions
	JMenuBar menubar = new JMenuBar();
	menubar.add(new JMenuItem(new ActionCreate ()));
	menubar.add(new JMenuItem(new ActionAnalyze()));
	menubar.add(new JMenuItem(new ActionReLoadGrammar()));
	setJMenuBar(menubar);
    }

    class ActionReLoadGrammar extends AbstractAction {

	private static final long serialVersionUID = -589L;
	ActionReLoadGrammar() {
	    super("Reload Grammar",GifResource.getIcon(RotCCLeft.class));
	    putValue(SHORT_DESCRIPTION, "reloads the grammar. ");
            putValue(MNEMONIC_KEY, KeyEvent.VK_R);
            putValue(ACCELERATOR_KEY,
		     KeyStroke.getKeyStroke(KeyEvent.VK_R,
					    ActionEvent.CTRL_MASK));
	}
	public void actionPerformed(ActionEvent event) {
System.out.println("(re)load grammar");
	    reloadGrammar();
	    
	}
    } // class ActionReLoadGrammar 

    class ActionCreate extends AbstractAction {

	private static final long serialVersionUID = -589L;
	ActionCreate() {
	    super("Create",GifResource.getIcon(New.class));
	    putValue(SHORT_DESCRIPTION, "creates new name. ");
            putValue(MNEMONIC_KEY, KeyEvent.VK_C);
            putValue(ACCELERATOR_KEY,
		     KeyStroke.getKeyStroke(KeyEvent.VK_C,
					    ActionEvent.CTRL_MASK));
	}
	public void actionPerformed(ActionEvent event) {
System.out.println("create new name");
	    resetCreator();
	    
	}
    } // class ActionCreate 

    class ActionAnalyze extends AbstractAction {

	private static final long serialVersionUID = -589L;
	ActionAnalyze() {
	    super("Analyze",GifResource.getIcon(Magnify.class));
	    putValue(SHORT_DESCRIPTION, "analyzes given name. ");
            putValue(MNEMONIC_KEY, KeyEvent.VK_A);
            putValue(ACCELERATOR_KEY,
		     KeyStroke.getKeyStroke(KeyEvent.VK_A,
					    ActionEvent.CTRL_MASK));
	}
	public void actionPerformed(ActionEvent event) {
System.out.println("analyze new name");
	    analyze();
	}
    } // class ActionAnalyze 

    void reloadGrammar() {
// **** here, a popup must provide the error messages. 
	this.files.reload();
	this.creator  = new NameCreator (this.files.catGr);
	this.analyzer = new NameAnalyzer(this.files.catGr);
    }

    void resetCreator() {
	this.nameField.setText(this.creator.reset());
	setCats(this.files.catGr.starts);
// 	if (this.catGr.stops.containsAll(this.catGr.starts)) {
// 	    this.creatorStatusLabel.setText(STATE_COMPLETE);
// 	} else {
 	    this.creatorStatusLabel.setText(STATE_NOT_COMPLETE);
// 	}

	this.structLabel.setText("");
	this.linearLabel.setText("");
	this.analyzerStatusLabel.setText("");
    }

    void analyze() {
	NameAnalyzer.State state = 
	    this.analyzer.analyze(this.nameField.getText());
	if (state != NameAnalyzer.State.Matches) {
	    this.structLabel.setText("");
	    this.linearLabel.setText("");
	    this.analyzerStatusLabel.setText(state.toString());
	    return;
 	}

	this.structLabel.setText(this.analyzer.structure());
	if (this.analyzer.isLinear()) {
	    this.linearLabel.setText(this.analyzer.linStructure());
	} else {
	    this.linearLabel.setText("");
	}

	this.analyzerStatusLabel.setText((this.analyzer.isLinear() 
					  ? "linear " : "       ") + 
					 (this.analyzer.isAllComplete() 
					  ? "  complete" : "incomplete"));
    }

    void setCat(Category newCat) {
	List<Compartment> compsOfCat = this.creator.setNewCat(newCat);
	this.setComps(compsOfCat);
    }

    void setComp(Compartment comp) {
	this.nameField.setText(this.creator.add(comp));

	if (this.creator.isStop()) {
	    this.creatorStatusLabel.setText(STATE_COMPLETE);
	} else {
	    this.creatorStatusLabel.setText(STATE_NOT_COMPLETE);
	}

	Collection<Category> nextCats = this.creator.nextCats();
	if (nextCats.isEmpty()) {
	    // must be end-category 
	    if (!this.creator.isStop()) {
		// **** this shall be tested earlier. 
		throw new IllegalStateException
		    ("Found category whithout successors which is no stopcat");
	    }

	    setCats(new HashSet<Category>());
	    setComps(new ArrayList<Compartment>());
	    return;
	}

	this.setCats(nextCats);
    }

    void setCats(Collection<Category> cats) {
System.out.println("/////////////setCats(: "+cats);
	this.combCat.removeAllElements();
	for (Category cand : cats) {	    
System.out.println("add: "+cand);
	    this.combCat.addElement(cand);
	}
//	this.combCat.addElement("");
    }

    void setComps(List<Compartment> comps) {
//System.out.println("/////////////setComps: "+comps);
	this.combComp.removeAllElements();
	this.combComp.addElement("");
	for (Compartment cand : comps) {
	    this.combComp.addElement(cand);
	}
    }

    public static void main(String[] args) 
	throws FileNotFoundException, IOException,
	       ParseException {
	if (args.length != 1) {
	   throw new IllegalArgumentException
	       ("Usage: the name of the rules file. ");
	}

	Files files = new Files(new File(args[0]));
	//CatGrammar catGr = files.catGr;
	CreatorAnalyzerFrame frame = new CreatorAnalyzerFrame(files);
System.out.println("finished");

    }

}
