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
 
 The current file most notably uses the code from SingletonAddRemoveButton.java in the Deducer package. 
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.
 */

package hansel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.rosuda.deducer.widgets.*;
import org.rosuda.deducer.toolkit.*;

import org.rosuda.JGR.util.ErrorMsg;

public class KeepSingletonAddRemoveButton extends IconButton implements ActionListener, ListDataListener{
	private SingletonDJList singList;
	private String[] cmdText;
	private String[] tooltipText;
	private KeepSingletonAddRemoveButton theButton=this;
	private JList leftList;
	public KeepSingletonAddRemoveButton(String[] tooltip, ActionListener al,
			String[] cmd,SingletonDJList lis){
		super("/icons/1rightarrow_32.png", tooltip[0], al,cmd[0]);
		singList=lis;
		tooltipText=tooltip;
		cmdText=cmd;
		singList.getModel().addListDataListener(this);
	}
	
	public KeepSingletonAddRemoveButton(String[] tooltip,String[] cmd,SingletonDJList lis,VariableSelector var){
		super("/icons/1rightarrow_32.png", tooltip[0], null,cmd[0]);
		this.addActionListener(this);
		singList=lis;
		tooltipText=tooltip;
		cmdText=cmd;
		leftList = var.getJList();
		singList.getModel().addListDataListener(this);
	}
	
	public KeepSingletonAddRemoveButton(String[] tooltip,String[] cmd,SingletonDJList lis,JList leftList){
		super("/icons/1rightarrow_32.png", tooltip[0], null,cmd[0]);
		this.addActionListener(this);
		singList=lis;
		tooltipText=tooltip;
		cmdText=cmd;
		this.leftList = leftList;
		singList.getModel().addListDataListener(this);
	}
	
	public void setList(SingletonDJList list){
		singList.getModel().removeListDataListener(this);
		singList = list;
		singList.getModel().addListDataListener(this);
	}
	
	public void refreshListListener(){
		singList.getModel().addListDataListener(this);
		refresh();
	}
	
	
	public void refresh(){
		if(singList.getModel().getSize()>0){
			theButton.setToolTipText(tooltipText[1]);
			theButton.setActionCommand(cmdText[1]);
			ImageIcon icon = new ImageIcon(getClass().getResource("/icons/1leftarrow_32.png"));
			theButton.setIcon(icon);
		}else{
			theButton.setToolTipText(tooltipText[0]);
			theButton.setActionCommand(cmdText[0]);
			ImageIcon icon = new ImageIcon(getClass().getResource("/icons/1rightarrow_32.png"));
			theButton.setIcon(icon);
		}
	}


	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals(cmdText[0])){
                      //Object[] objs=leftList.getSelectedValuesList().toArray();
			Object[] objs=leftList.getSelectedValues();
			if(objs.length>1){
				leftList.setSelectedIndex(leftList.getSelectedIndex());
			}else if(objs.length==1 && singList.getModel().getSize()==0){
				((DefaultListModel)singList.getModel()).addElement(objs[0]);
			}
		}else{
			DefaultListModel tmpModel =(DefaultListModel)singList.getModel();
			if(tmpModel.getSize()>0){
                            tmpModel.remove(0);
			}
		}
		
	}
     
         
	public void intervalAdded(ListDataEvent e) {
		refresh();
	}

	public void intervalRemoved(ListDataEvent e) {
		refresh();
	}

	public void contentsChanged(ListDataEvent e) {
		refresh();
	}
	
}
