package eu.simuline.names;

import eu.simuline.util.GifResource;

import org.javalobby.icons20x20.ExecuteProject;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import java.io.File;

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


    NameCreator creator;
    NameAnalyzer analyzer;

    CatGrammar catGr;

    // null if none is selected 
    JTextField nameField;

    JLabel statusLabel;


    /**
     * Creates a new <code>CreatorAnalyzerFrame</code> instance.
     *
     */
    public CreatorAnalyzerFrame(CatGrammar catGr) {
	super("Checker Frame");
	this.catGr = catGr;
	this.creator = new NameCreator(catGr);


	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setMenuBar();//actions



	Container content = getContentPane();
	content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

	Box partialNameB = Box.createHorizontalBox();
	partialNameB.add(new JLabel("partial name: "));
	// setText(this.partialName)
	this.nameField = new JTextField();
	partialNameB.add(this.nameField);
	add(partialNameB);
	add(Box.createVerticalGlue());
	add(Box.createVerticalStrut(40));

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


this.statusLabel = new JLabel();
	add(this.statusLabel);
	reset();

	setSize(800,300);
	//this.frame.pack();
	setVisible(true);
    }


    void setMenuBar() {//Actions actions
	JMenuBar menubar = new JMenuBar();
	menubar.add(new JMenuItem(new ActionCreate()));
	setJMenuBar(menubar);
    }


    class ActionCreate extends AbstractAction {

	private static final long serialVersionUID = -589L;
	ActionCreate() {
	    super("Run",GifResource.getIcon(ExecuteProject.class));
	    putValue(SHORT_DESCRIPTION, "prepares new name. ");
            putValue(MNEMONIC_KEY, KeyEvent.VK_R);
            putValue(ACCELERATOR_KEY,
		     KeyStroke.getKeyStroke(KeyEvent.VK_R,
					    ActionEvent.CTRL_MASK));
	}
	public void actionPerformed(ActionEvent event) {
System.out.println("new name");
reset();
	    
	}
    } // class StartAction 

    void reset() {
	this.nameField.setText(this.creator.reset());
	setCats(this.catGr.starts);
	if (this.catGr.stops.containsAll(this.catGr.starts)) {
	    this.statusLabel.setText(STATE_COMPLETE);
	} else {
	    this.statusLabel.setText(STATE_NOT_COMPLETE);
	}
    }

    void setCat(Category newCat) {
	List<Compartment> compsOfCat = this.creator.setNewCat(newCat);
	this.setComps(compsOfCat);
    }

    void setComp(Compartment comp) {
	this.nameField.setText(this.creator.add(comp));

	if (this.creator.isStop()) {
	    this.statusLabel.setText(STATE_COMPLETE);
	} else {
	    this.statusLabel.setText(STATE_NOT_COMPLETE);
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

    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	   throw new IllegalArgumentException
	       ("Usage: the name of the rules file. ");
	}

	Files files = new Files(new File(args[0]));
	//NameCreator nCreator = new NameCreator(new File(args[0]));
	CatGrammar catGr = files.catGr;
	CreatorAnalyzerFrame frame = new CreatorAnalyzerFrame(catGr);
	frame.setCats(catGr.starts);
	frame.setVisible(true);
System.out.println("finished");

    }



}
