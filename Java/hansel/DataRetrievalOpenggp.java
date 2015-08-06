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
 
 The file itself uses code from many java files from the following packages by Ian Fellows:
     Deducer (0.7-6 version, dated 2012-12-05)
     DeducerPlugInExample (0.2-0 version, dated 2012-03-16)
 and from java code found in the DeducerSpatial package (0.7 version, dated 2013-04-12) by Ian Fellow and Alex Rickett with contributions from Neal Fultz.
 and from java code from the JGR package (1.7 -18 version, downloaded 2015-07-15) by Markus Helbig, Simon Urbanek and Ian Fellows, 
 specifically code found in DataLoader(http://svn.rforge.net/org/trunk/rosuda/JGR/DataLoader.java).

 The code in the files of the above packages is covered by the GPLv2 licenses for those packages.
  
 The code from those packages used in this one most notably comes from
  ShapeFileLoader.java, found in the DeducerSpatial package
 
 The code in the files of those packages are covered by the GPL2 licenses for those packages.
 
The current file made adjustments to that earlier java code on 2015-07-17 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-08-06.
 */

package hansel;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Component; 
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;

import org.rosuda.deducer.toolkit.SingletonDJList;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.menu.SubsetDialog;
import org.rosuda.deducer.menu.SubsetPanel;
import org.rosuda.REngine.JRI.JRIEngine;
import org.rosuda.REngine.REXP;

import org.rosuda.JGR.TxtTableLoader;
import org.rosuda.JGR.toolkit.FileSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.print.DocFlavor.INPUT_STREAM;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.widgets.*;
import org.rosuda.deducer.plots.PlotBuilder;
import org.rosuda.deducer.plots.PlotBuilderModel;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.rosuda.deducer.WindowTracker;
import org.rosuda.JGR.RController;
import org.rosuda.JGR.toolkit.FileSelector;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.HelpButton;

public class DataRetrievalOpenggp extends FileSelector{
	
	private static final String HELP_URL = "index.php?n=Main.SpatialLoadData";
	private JTextField rDataNameField;
	JTextField proj ;
        protected static String FileSelectorDialog;
        private static DataRetrievalOpenggp theDialog;
	public DataRetrievalOpenggp(Frame f) {
		super(f, "Open data", FileSelector.LOAD, null, true);
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(new JLabel("Set name: "));
		rDataNameField = new JTextField(12);
		namePanel.add(rDataNameField);
		namePanel.add(new JLabel("      Proj: "));

		proj = new JTextField(10);
		proj.setText("+proj=longlat +datum=WGS84");
		namePanel.add(proj);
		namePanel.add(new HelpButton(HELP_URL));
		
		if (this.isSwing())
		{
			this.getJFileChooser().addChoosableFileFilter(new FileNameExtensionFilter("ggplot2 PlotBuilder file (*.ggp)", "ggp"));

		} //TODO handle the non-swing case
		
	}
		
	
	public DataRetrievalOpenggp() {
		this(null);
	}
	
        
          public static void runit() {
            
                       Deducer.execute("hello! Welcome to HanselLoad!");
			theDialog = new DataRetrievalOpenggp();
                        theDialog.setVisible(true);
                
		WindowTracker.addWindow(theDialog);

	}
        
	public boolean runInR(){
		if (this.getFile() == null)
			return false;
		String rName = rDataNameField.getText();
		if (rName.length() == 0)
			rName = (this.getFile().indexOf(".") <= 0 ? Deducer.getUniqueName(this.getFile()) : 
					Deducer.getUniqueName(this.getFile().substring(0, this.getFile().indexOf("."))));
		rName = RController.makeValidVariableName(rName);
		String fileName = Deducer.addSlashes(this.getFile());
		String path =this.getDirectory().replace('\\', '/');
		
		String fileNoExt = fileName.substring(0, fileName.length()-4);
		String command = "";
                if(fileName.toLowerCase().endsWith(".ggp")){

                    PlotBuilderModel newModel = new PlotBuilderModel();
                    newModel.setFromFile(this.getSelectedFile());
                    PlotBuilder d = new PlotBuilder(newModel);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                    WindowTracker.addWindow(d);	
                } else 	{
                    JOptionPane.showMessageDialog(null, "This does not appear to be a"
							+" ggplot2 PlotBuilder file (extension .ggp)");
                }	

		Deducer.execute(command);

		return true;
	}
        
        public void loadTxtFile(String fileName, String directory, String rName) {
		DToolsTxtTableLoader.run(directory.replace('\\','/') + fileName, rName); 
                
	}
        
        public void actionListener(ActionEvent al) {
		String cmd = al.getActionCommand();

               if (cmd=="Open"||cmd=="OPEN"||cmd=="Load"||cmd=="LOAD") {
                       runInR();
                       this.dispose();
		}else if(cmd=="Cancel"){
                         this.dispose();
	        }
        }
}

