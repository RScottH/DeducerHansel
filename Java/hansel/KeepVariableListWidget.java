/** Copyright (C) 2015 R. Scott H/\ker, with "/\" replaced by "ac". 
This file is part of the DeducerHansel package by the above copyright holder,
and is covered by the GNU General Public License version 2 (GPLv2).
See the README.md and LICENSE files in the main DeducerHansel folder for this package
for copyright and warranty details. The GPLv2 license is also is also
available at http://www.gnu.org/licenses/gpl-2.0.html, or from the
Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 
 DeducerHansel java files have liberally borrowed code from many java files from the following packages by Ian Fellows:
     Deducer (0.7-6 version, dated 2012-12-05)
     DeducerPlugInExample (0.2-0 version, dated 2012-03-16)
The code in the files of the above packages is covered by the GPLv2 licenses for those packages.
 
 The code from those packages used in this file most notably come from
  VariableListWidget.java, found in the Deducer package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Vector;

import javax.swing.BorderFactory;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionListener;
import org.rosuda.deducer.widgets.*;

/**
 * A widget for selecting a number of variables from a data frame
 * 
 * @author Ian Fellows (for the original VariableListWidget.java file)
 *
 */
public class KeepVariableListWidget extends javax.swing.JPanel implements DeducerWidget, ActionListener{
	private KeepAddButton addKeepButton;
	private JScrollPane listScrollPane;
	private DJList varList;
	private JPanel buttonPanel;
	private JPanel listPanel;
	private KeepRemoveButton removeKeepButton;
	private VariableSelectorWidget selector;
	private String title;
	private DefaultListModel initialModel;
	private DefaultListModel lastModel;
	
	/**
	 * new VariableListWidget
	 * @param panelTitle title
	 * @param varSel the VariableSelector to link
	 */
	public KeepVariableListWidget(String panelTitle, VariableSelectorWidget varSel) {
		super();
		selector = varSel;
		title = panelTitle;
		selector.getJComboBox().addActionListener(this);
		initGUI();
	}
	
	/**
	 * untitled VariableListWidget
	 * @param varSel the VariableSelector to link
	 */
	public KeepVariableListWidget(VariableSelectorWidget varSel) {
		this(null,varSel);
	}
	
	/**
	 * Set-up GUI components
	 */
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(239, 136));
			
			listPanel = new JPanel();
			BorderLayout listPanelLayout = new BorderLayout();
			this.add(listPanel, new AnchorConstraint(0, 1002, 1003, 64, 
					AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
			listPanel.setPreferredSize(new java.awt.Dimension(184, 136));
			listPanel.setLayout(listPanelLayout);
			if(title!=null)
				listPanel.setBorder(BorderFactory.createTitledBorder(title));

			listScrollPane = new JScrollPane();
			listPanel.add(listScrollPane, BorderLayout.CENTER);
			varList = new DJList();
			varList.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			listScrollPane.setViewportView(varList);
			varList.setModel(new DefaultListModel());
			
			
			buttonPanel = new JPanel();
			this.add(buttonPanel, new AnchorConstraint(275, 140, 768, 2, 
				AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
				AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
			buttonPanel.setPreferredSize(new java.awt.Dimension(37, 81));
			buttonPanel.setLayout(null);
			
			addKeepButton = new KeepAddButton("Add",selector,varList);
			buttonPanel.add(addKeepButton);
			addKeepButton.setBounds(0, 0, 32, 34);
			
			removeKeepButton = new KeepRemoveButton("Remove",selector,varList);
			buttonPanel.add(removeKeepButton);
			removeKeepButton.setBounds(0, 42, 32, 34);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * monitor changes in the variableSelector
	 */
	public void actionPerformed(ActionEvent act) {
		String cmd = act.getActionCommand();
		
		if(cmd == "comboBoxChanged"){
			if(initialModel == null)
				setModel(new DefaultListModel());
			else
				setModel(initialModel);
		}
	}
	
	/**
	 * gets items in list
	 * @return items
	 */
	public String[] getVariables(){
		DefaultListModel model = (DefaultListModel) getModel();
		Object[] tmp = model.toArray();
		String[] items = new String[tmp.length];
		for(int i =0;i<tmp.length;i++)
			if(tmp[i] instanceof String)
				items[i] = (String) tmp[i];
		return items;
	}
	
	/**
	 * Set the default variables
	 * @param items
	 */
	public void setDefaultVariables(String[] items){
		initialModel = new DefaultListModel();
		for(int i=0;i<items.length;i++)
			initialModel.addElement(items[i]);
	}

	/**
	 * Sets the items in the list
	 * @param items an array containing the items
	 * @param removeFromVariableSelector should the items be removed from the VariableSelector
	 */
	public void setModel(String[] items,boolean removeFromVariableSelector){
		DefaultListModel newModel = new DefaultListModel();
		for(int i=0;i<items.length;i++)
			newModel.addElement(items[i]);
		setModel(newModel,removeFromVariableSelector);
	}
	

	/**
	 * Sets the items in the list
	 * @param mod a DefaultListModel containing the items
	 * @param removeFromVariableSelector should the items be removed from the VariableSelector
	 */
	public void setModel(DefaultListModel mod, boolean removeFromVariableSelector){
		if(removeFromVariableSelector && selector==null)
			setModel(mod,false);
		else{
			if(selector!=null && selector.getSelectedData()!=null)
				try {
					Vector rNames = new Vector();
					String[] names = Deducer.eval("names("+selector.getSelectedData()+")").asStrings();
					for(int i=0;i<names.length;i++)
						rNames.add(names[i]);
					DefaultListModel selLis = (DefaultListModel) selector.getJList().getModel();
					DefaultListModel curModel = (DefaultListModel) varList.getModel();
					for(int i=0; i<curModel.size(); i++){
						Object var = curModel.get(i);
						if(rNames.contains(var) && !selLis.contains(var))
							selLis.addElement(var);
					}
				} catch (Exception e) {
					new ErrorMsg(e);
				}
			
			
			if(mod==null)
				mod = new DefaultListModel();
			DefaultListModel newModel = new DefaultListModel();
			boolean exists;
			for(int i=0;i<mod.size();i++){
				if(removeFromVariableSelector){
					exists = selector.remove(mod.get(i));
					if(exists)
						newModel.addElement(mod.get(i));
				}else
					newModel.addElement(mod.get(i));
			}
			varList.setModel(newModel);
		}
	}
	
	
	/**
	 * adds either an action, mouse or list selection listener 
	 * @param lis
	 */
	public void addListener(EventListener lis) {
		if(lis instanceof ActionListener){
			removeKeepButton.addActionListener((ActionListener) lis);
			addKeepButton.addActionListener((ActionListener) lis);
		}
		if(lis instanceof ListSelectionListener)
			varList.addListSelectionListener((ListSelectionListener) lis);
		if(lis instanceof MouseListener)
			varList.addMouseListener((MouseListener) lis);
	}

	/*
	 * Start DeducerWidget methods
	 * 
	 * The state (or model) is a DefaultListModel
	 */
	
	public Object getModel() {
		return varList.getModel();
	}

	public String getRModel() {
		String rcall = Deducer.makeRCollection(varList.getModel(),"c",true);
		return rcall;
	}

	public String getTitle() {
		return title;
	}

	public void reset() {
		setModel(initialModel);
	}

	public void resetToLast() {
		setModel(lastModel);
	}

	public void setDefaultModel(Object model){
		initialModel = (DefaultListModel) model;
		if(lastModel==null)
			lastModel = (DefaultListModel) model;
	}

	public void setLastModel(Object model){
		lastModel = (DefaultListModel) model;
	}

	public void setModel(Object model){
		setModel((DefaultListModel) model,true);
	}

	public void setTitle(String t, boolean show) {
		title=t;
		if(t==null)
			listPanel.setBorder(BorderFactory.createEmptyBorder());
		else if(show)
			listPanel.setBorder(BorderFactory.createTitledBorder(title));
		
	}

	public void setTitle(String t) {
		setTitle(t,false);
	}

	/*
	 * End DeducerWidget methods
	 */

}
