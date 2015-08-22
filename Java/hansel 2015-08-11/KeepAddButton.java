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
 
 The current file notably uses a substantial amount of code from AddButton.java from the Deducer package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.
 */
package hansel;
        
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.TransferHandler;
import org.rosuda.deducer.widgets.*;
import org.rosuda.deducer.toolkit.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;


import java.io.IOException;
import java.util.*;


public class KeepAddButton extends IconButton implements ActionListener{

	JList leftList;
	JList rightList;
	public KeepAddButton(String name,VariableSelector var,JList list){
		super("/icons/1rightarrow_32.png", name, null,name);
		this.addActionListener(this);
		rightList=list;
		leftList = var.getJList();
	}
	public KeepAddButton(String name,JList left,JList right){
		super("/icons/1rightarrow_32.png", name, null,name);
		this.addActionListener(this);
		rightList=right;
		leftList = left;
	}
	
	public void actionPerformed(ActionEvent arg0) { 
            //Object[] objs=leftList.getSelectedValuesList().toArray();
		Object[] objs=leftList.getSelectedValues();
                //The next line ses the selection to next variable not chosen
                leftList.setSelectedIndex(leftList.getSelectedIndices()[leftList.getSelectedIndices().length-1]+1);
		for(int i=0;i<objs.length;i++){
			if(!(leftList instanceof HanselDJList && ((HanselDJList)leftList).getTransferMode()==TransferHandler.COPY))
			if(!(rightList instanceof HanselDJList && ((HanselDJList)rightList).getTransferMode()==TransferHandler.COPY))
				if(objs[i]!=null)
                                    if(!((DefaultListModel)rightList.getModel()).contains(objs[i]))
					((DefaultListModel)rightList.getModel()).addElement((String)objs[i]);
                                    
		}
		
	}

}
