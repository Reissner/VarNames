package eu.simuline.namecheck;

import eu.simuline.util.GifResource;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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

import java.util.Set;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;

import org.javalobby.icons20x20.ExecuteProject;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.util.ArrayList;

/**
 * Describe class CheckerFrame here.
 *
 *
 * Created: Tue Apr 15 19:49:36 2008
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class CheckerFrame extends JFrame {

    private static final long serialVersionUID = -2479143000061671589L;

    private final static String STATE_COMPLETE = "Complete";
    private final static String STATE_NOT_COMPLETE = "Not Yet Complete";


    DefaultComboBoxModel combCat;
    DefaultComboBoxModel combComp;
    CatGrammar catGr;
    // null if none is selected 
    Category currCat;
    String partialName;
    JTextField nameField;

    JLabel statusLabel;


    /**
     * Creates a new <code>CheckerFrame</code> instance.
     *
     */
    public CheckerFrame(CatGrammar catGr) {
	super("Checker Frame");
	this.catGr = catGr;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setMenuBar();//actions

	this.currCat = null;


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
			    CheckerFrame.this.setCat((Category)e.getItem());
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
			    CheckerFrame.this.setComp
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
	menubar.add(new JMenuItem(new StartAction()));
	setJMenuBar(menubar);
    }


    class StartAction extends AbstractAction {

	private static final long serialVersionUID = -589L;
	StartAction() {
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
	this.partialName = "";
	this.nameField.setText(this.partialName);
	setCats(this.catGr.starts);
	if (this.catGr.stops.containsAll(this.catGr.starts)) {
	    this.statusLabel.setText(STATE_COMPLETE);
	} else {
	    this.statusLabel.setText(STATE_NOT_COMPLETE);
	}


    }

    void setCat(Category newCat) {
	this.currCat = newCat;
	List<Compartment> compsOfCat = this.catGr.cat2comps(this.currCat);
	this.setComps(compsOfCat);
// 	if (this.catGr.isStop(this.currCat)) {
// 	    this.statusLabel.setText(STATE_COMPLETE);
// 	} else {
// 	    this.statusLabel.setText(STATE_NOT_COMPLETE);
// 	}

    }

    void setComp(Compartment comp) {
	this.partialName += comp.shortName();
	this.nameField.setText(this.partialName);

System.out.println("setcomp:       currCat: "+this.currCat);
//System.out.println("setcomp:       catGr: "+this.catGr);
	if (this.catGr.isStop(this.currCat)) {
	    this.statusLabel.setText(STATE_COMPLETE);
	} else {
	    this.statusLabel.setText(STATE_NOT_COMPLETE);
	}


	System.out.println("nextCats: "+this.catGr.nextCats(this.currCat));
	Collection<Category> nextCats = this.catGr.nextCats(this.currCat);
	if (nextCats.isEmpty()) {
	    // must be end-category 
	    if (!this.catGr.isStop(this.currCat)) {
		// **** this shall be tested earlier. 
		throw new IllegalStateException
		    ("Found category whithout successors which is no stopcat");
	    }

	    setCats(new HashSet<Category>());
	    setComps(new ArrayList<Compartment>());
	    return;
	}

	this.setCats(nextCats);
//this.currCat = null;
//this.currCat = (Category)e.getItem();
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


}
