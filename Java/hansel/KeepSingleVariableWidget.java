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
 
 The current file most notably uses the code from SingleVariableWidget.java in the Deducer package. 
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.SingletonAddRemoveButton;
import org.rosuda.deducer.toolkit.SingletonDJList;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionListener;
import org.rosuda.deducer.widgets.*;
/**
 * Creates a widget for selecting a single variable from a VariableSelector
 * @author Ian ,edited by R. Scott H/\ker (replace "/\" with "ac")
 *
 */
public class KeepSingleVariableWidget extends JPanel implements DeducerWidget, ActionListener{
	private KeepSingletonAddRemoveButton addRemoveKeepButton;
	private SingletonDJList singleList;
	private JPanel listPanel;
	private VariableSelectorWidget selector;
	private String title;
	private DefaultListModel initialModel;
	private DefaultListModel lastModel;
	
	/**
	 * Create a new SingleVariableWidget
	 * @param panelTitle title
	 * @param varSel VariableSelector to link
	 */
	public KeepSingleVariableWidget(String panelTitle, VariableSelectorWidget varSel) {
		super();
		selector = varSel;
		title = panelTitle;
		selector.getJComboBox().addActionListener(this);
		initGUI();
	}
	/**
	 * create an untitled SingleVariableWidget
	 * @param varSel VariableSelector to link
	 */
	public KeepSingleVariableWidget( VariableSelectorWidget varSel) {
		this(null,varSel);
	}
	
	/**
	 * set-up GUI components
	 */
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(239, 65));
			{
				listPanel = new JPanel();
				BorderLayout listPanelLayout = new BorderLayout();
				this.add(listPanel, new AnchorConstraint(5, 1002, 940, 64, 
						AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				listPanel.setPreferredSize(new java.awt.Dimension(195, 50));
				listPanel.setLayout(listPanelLayout);
				if(title!=null)
					listPanel.setBorder(BorderFactory.createTitledBorder(title));
				{
					singleList = new SingletonDJList();
					listPanel.add(singleList, BorderLayout.CENTER);
					singleList.setModel(new DefaultListModel());
					singleList.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				}
			}
			{
				addRemoveKeepButton = new KeepSingletonAddRemoveButton(new String[]{"Add","Remove"},
						new String[]{"Add","Remove"},singleList,selector);
				this.add(addRemoveKeepButton, new AnchorConstraint(16, 156, 845, 0, 
						AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS));
				addRemoveKeepButton.setPreferredSize(new java.awt.Dimension(32, 34));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * handles changes to the variableSelector
	 */
	public void actionPerformed(ActionEvent act) {
		String cmd = act.getActionCommand();
		if(initialModel == null)
			setModel(new DefaultListModel());
		else
			setModel(initialModel);
		
	}
	
	/**
	 * 
	 * @return the variable list
	 */
	public SingletonDJList getList(){
		return singleList;
	}
	
	/**
	 * 
	 * @return the add remove button
	 */
	public KeepSingletonAddRemoveButton getButton(){
		return addRemoveKeepButton;
	}
	
	public String getSelectedVariable(){
		DefaultListModel mod = (DefaultListModel) singleList.getModel();
		if(mod.size()==0)
			return null;
		else
			return mod.get(0).toString();
	}
	
	public void setSelectedVariable(String var){
		DefaultListModel mod = new DefaultListModel();
		if(var!=null)
			mod.addElement(var);
		setModel(mod);

	}
	
	public void setDefaultVariable(String var){
		initialModel = new DefaultListModel();
		if(var!=null)
			initialModel.addElement(var);
	}
	
	/**
	 * adds either an action, mouse or list selection listener 
	 * @param lis
	 */
	public void addListener(EventListener lis) {
		if(lis instanceof ActionListener)
			addRemoveKeepButton.addActionListener((ActionListener) lis);
		if(lis instanceof ListSelectionListener)
			singleList.addListSelectionListener((ListSelectionListener) lis);
		if(lis instanceof MouseListener)
			singleList.addMouseListener((MouseListener) lis);
	}
	
	/*
	 * Start DeducerWidget methods
	 * 
	 * The state (or model) is a DefaultListModel
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
					DefaultListModel curModel = (DefaultListModel) singleList.getModel();
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
			singleList.getModel().removeListDataListener(addRemoveKeepButton);
			singleList.setModel(newModel);
			addRemoveKeepButton.refreshListListener();
		}
	}

	public Object getModel() {
		return singleList.getModel();
	}

	public String getRModel() {
		String rcall = Deducer.makeRCollection(singleList.getModel(),"c",true);
		return rcall;
	}

	public String getTitle() {
		return title;
	}

	public void reset() {
		String var = getSelectedVariable();
		if(var!=null)
			selector.add(var);
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
