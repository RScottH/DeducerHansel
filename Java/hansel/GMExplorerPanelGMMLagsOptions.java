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
 
The current file most notably uses the code from GLMExplorerPlots.java and GLMExplorerPostHoc.java in the Deducer package.  

The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.

 */

package hansel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;

import org.rosuda.deducer.models.RModel;




public class GMExplorerPanelGMMLagsOptions extends javax.swing.JDialog implements ActionListener{
	protected JPanel termPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
	protected JCheckBox multi2;
       	protected JCheckBox resvFitted;
        protected JCheckBox termPlots;
        protected JCheckBox ramseyRESET;
	protected JList terms;
	protected JPanel meansPanel;
	protected JPanel okayCancel;
        protected JPanel termsPanel;
	protected JCheckBox confInt;
	protected JCheckBox scaled;
	protected JPanel optionsPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
        protected JList termsList;
	protected JScrollPane termsScroller;
	protected GMModel  model;
        protected JCheckBox centreSimpleMA;

        
        protected JLabel startingLagLabel;
        protected JComboBox startingLag;
        protected String startLagHere;
        
        protected JLabel endingLagLabel;
        protected JComboBox endingLag;
        protected String endLagHere;
        protected Boolean lagVariablesRunHere;
        
                            protected static DefaultComboBoxModel startLags = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40",
                                               "41","42","43","44","45","46","47","48","49","50",
                                               "51","52","53","54","55","56","57","58","59","60",
                                               "61","62","63","64","65","66","67","68","69","70",
                                               "71","72","73","74","75","76","77","78","79","80",
                                               "81","82","83","84","85","86","87","88","89","90",
                                               "91","92","93","94","95","96","97","98","99","100",
                                               "101","102","103","104","105","106","107","108","109","110",
                                               "111","112","113","114","115","116","117","118","119","120",
                                               "121","122","123","124","125","126","127","128","129","130",
                                               "131","132","133","134","135","136","137","138","139","140",
                                               "141","142","143","144","145","146","147","148","149","150",
                                               "151","152","153","154","155","156","157","158","159","160",
                                               "161","162","163","164","165","166","167","168","169","170",
                                               "171","172","173","174","175","176","177","178","179","180",
                                               "181","182","183","184","185","186","187","188","189","190",
                                               "191","192","193","194","195","196","197","198","199","200"});
                           protected static DefaultComboBoxModel endLags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40",
                                               "41","42","43","44","45","46","47","48","49","50",
                                               "51","52","53","54","55","56","57","58","59","60",
                                               "61","62","63","64","65","66","67","68","69","70",
                                               "71","72","73","74","75","76","77","78","79","80",
                                               "81","82","83","84","85","86","87","88","89","90",
                                               "91","92","93","94","95","96","97","98","99","100",
                                               "101","102","103","104","105","106","107","108","109","110",
                                               "111","112","113","114","115","116","117","118","119","120",
                                               "121","122","123","124","125","126","127","128","129","130",
                                               "131","132","133","134","135","136","137","138","139","140",
                                               "141","142","143","144","145","146","147","148","149","150",
                                               "151","152","153","154","155","156","157","158","159","160",
                                               "161","162","163","164","165","166","167","168","169","170",
                                               "171","172","173","174","175","176","177","178","179","180",
                                               "181","182","183","184","185","186","187","188","189","190",
                                               "191","192","193","194","195","196","197","198","199","200"});
                
	public GMExplorerPanelGMMLagsOptions(JDialog d, GMModel mod,String startLagHere, String endLag,Boolean lagVariablesRunHere) {
		super(d);
                model=mod;
                String endLagHere = endLag;
                startLagHere = "3";
                endLagHere = "6";
                if (!lagVariablesRunHere) {
                    initGUI();
                    this.setModal(true);
                    return;
                } else
                    return;
	}
	
	protected void initGUI() {
		try {
			{
                        AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);

				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(380, 320, 207, 36);
  
                                {
					startingLagLabel = new JLabel();
					getContentPane().add(startingLagLabel, new AnchorConstraint(11, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					startingLagLabel.setText("Start Lag");
					startingLagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                                                {
					startingLag = new JComboBox();
					getContentPane().add(startingLag, new AnchorConstraint(61, 931, 805, 11,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					startingLag.setModel(startLags);
                                        startingLag.setSelectedItem("0");
					startingLag.setPreferredSize(new java.awt.Dimension(60, 21));
					startingLag.addActionListener(this);
				}
                                {
					endingLagLabel = new JLabel();
					getContentPane().add(endingLagLabel, new AnchorConstraint(11, 931, 805, 410,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					endingLagLabel.setText("End Lag");
					endingLagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                                                {
					endingLag = new JComboBox();
					getContentPane().add(endingLag, new AnchorConstraint(61, 931, 805, 410,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					endingLag.setModel(endLags);
                                        endingLag.setSelectedItem("0");
					endingLag.setPreferredSize(new java.awt.Dimension(60, 21));
					endingLag.addActionListener(this);
				}                                                                
                                                                
				}
			}
			this.setTitle("Choice of lags for panel variable");
			this.setSize(632, 400);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
                        return;
		}else if(cmd=="OK"){
                    
                    this.startLagHere = startingLag.getSelectedItem().toString();
                    this.endLagHere = endingLag.getSelectedItem().toString();                    
                    lagVariablesRunHere = true;
                    model.efeglmextra.panelVariableLagStart = startingLag.getSelectedItem().toString();
                    model.efeglmextra.panelVariableLagEnd = endingLag.getSelectedItem().toString();
                    model.efeglmextra.panelVariableLagRun = true;
                
	            this.dispose();
                    return;
		}else if(cmd=="Add"){
			Object[] objs=terms.getSelectedValuesList().toArray();
			for(int i=0;i<objs.length;i++){
				((DefaultListModel)terms.getModel()).removeElement(objs[i]);
				if(objs[i]!=null)
					((DefaultListModel)effects.getModel()).addElement(objs[i]);
			}
		}else if(cmd=="Remove"){
			Object[] objs=effects.getSelectedValuesList().toArray();
			for(int i=0;i<objs.length;i++){
				((DefaultListModel)effects.getModel()).removeElement(objs[i]);
				if(objs[i]!=null)
					((DefaultListModel)terms.getModel()).addElement(objs[i]);
			}
		}
		
	}

}