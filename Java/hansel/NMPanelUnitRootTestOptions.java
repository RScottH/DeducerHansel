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
  GLMExplorerPlots.java, GLMExplorerPostHoc.java, and CorDialog.java, found in the Deducer package
 and
  ExampleDialog.java, found in the DeducerPlugInExample package. 

* The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.

 */



package hansel;

import java.awt.BorderLayout;
import java.awt.Font;
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
import org.rosuda.deducer.widgets.ButtonGroupWidget;




public class NMPanelUnitRootTestOptions extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
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
        protected ButtonGroupWidget deterministicComponent;
        protected ButtonGroupWidget ersDeterministicComponent;
        protected ButtonGroupWidget ppDeterministicComponent;
        protected ButtonGroupWidget kpssDeterministicComponent;
        protected ButtonGroupWidget typeSP;
        protected ButtonGroupWidget zaBreakControl;      
        protected ButtonGroupWidget differencing;
        protected ButtonGroupWidget lagDetermination;
        protected ButtonGroupWidget ersType;
        protected ButtonGroupWidget ppType;
        protected ButtonGroupWidget spPolynDegree;
     
        protected JLabel maxLagLabel;
        protected JLabel maxLagLabelShort;
        protected JLabel lagLabel;
        protected JComboBox maxLag;
        protected JComboBox ersMaxLag; 
        protected JComboBox kpssMaxLag;
        protected JComboBox ppLag;
        protected ButtonGroupWidget spSig; 
        protected JComboBox zaMaxLag; 
        
        protected String differencingChoice = "";
        protected String deterministicComponentChoice = "";
        protected String lagDeterminationChoice = "";
        protected String maxLagChoice = "";
        protected String ersDeterministicComponentChoice = "";
        protected String ersTypeChoice = "";
        protected String ersMaxLagChoice = "";
        protected String kpssDeterministicComponentChoice = "";
        protected String kpssMaxLagChoice = "";
        protected String ppDeterministicComponentChoice = "";
        protected String ppTypeChoice = "";
        protected String ppLagChoice = "";
        protected String typeSPChoice = "";
        protected String spPolynDegreeChoice = "";
        protected String spSigChoice = "";
        protected String zaBreakControlChoice = "";
        protected String zaMaxLagChoice = "";
        protected Boolean unitRootTestOptionsOkay = false;
        protected JCheckBox truncated;
        protected ButtonGroupWidget cipsModelType;
        protected String cipsModelTypeChoice="";
        protected Boolean truncatedChoice=false;
        
        private String givenTest;
                protected static DefaultComboBoxModel maxLags  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel kpssMaxLags  = new DefaultComboBoxModel(
				new String[] { "short","long","nil","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel ppLags  = new DefaultComboBoxModel(
				new String[] { "short","long","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel zaMaxLags  = new DefaultComboBoxModel(
				new String[] { "NULL","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               
	public NMPanelUnitRootTestOptions(JFrame frame,GMModel mod,RModel rmod, String specificURTest) {
		super(frame);
                givenTest = specificURTest;
		initGUI();
		setModel(mod,rmod);
		this.setModal(true);
	}
	
	protected void initGUI() {
		try {
			{
                        AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(205, 260, 207, 36);
                                        okayCancel.setFont(font12);

                                {
                                        deterministicComponent = new ButtonGroupWidget("Deterministic component",
						new String[]{"no deterministic component",
                                                             "drift","trend"});
                                        deterministicComponent.setSelected("no deterministic component");
                                        deterministicComponent.setFont(font12);
                                        getContentPane().add(deterministicComponent, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        deterministicComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                }

                                
                                {
                                        differencing = new ButtonGroupWidget("Differencing",
						new String[]{"Use level (no differencing)",
                                                             "Use 1st difference","Use 2nd difference"});
                                        differencing.setSelected("Use level (no differencing)");
                                        differencing.setFont(font12);
                                        getContentPane().add(differencing, new AnchorConstraint(11, 931, 805, 500, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        differencing.setPreferredSize(new java.awt.Dimension(260, 100));
                                }
                                
 
                                {
                                        cipsModelType = new ButtonGroupWidget("Type of CIPS Test",
						new String[]{"cmg",
                                                             "mg","dmg"});
                                        cipsModelType.setSelected("cmg");
                                        cipsModelType.setFont(font12);
                                        getContentPane().add(cipsModelType, new AnchorConstraint(400, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cipsModelType.setPreferredSize(new java.awt.Dimension(260, 100));
                                }  
                                {
					truncated = new JCheckBox();
					getContentPane().add(truncated, new AnchorConstraint(850, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					truncated.setText("Truncated");
                                        truncated.setFont(font12);
					truncated.setPreferredSize(new java.awt.Dimension(115, 18));
					truncated.addActionListener(this);
                                        truncated.setSelected(false);
				}

                                {
					lagLabel = new JLabel();
					getContentPane().add(lagLabel, new AnchorConstraint(400, 487, 982, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					lagLabel.setText("Lag for DF Augmentation");
                                        lagLabel.setFont(font12);
					lagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                
                                
                               {
					maxLag = new JComboBox();
					getContentPane().add(maxLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					maxLag.setModel(maxLags);
                                        maxLag.setFont(font12);
					maxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					maxLag.addActionListener(this);
				}
                               
				}
			}
			this.setTitle(givenTest+ " test");
                        this.setFont(font12);
			this.setSize(632, 360);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){

                model.unitRootTestOptions.differencing = differencing.getSelectedItemText();
                model.unitRootTestOptions.adfMaxLag = maxLag.getSelectedItem().toString();
                
                differencingChoice = differencing.getSelectedItemText();
                    
                   if (deterministicComponent.getSelectedItemText()=="no deterministic component"){
                      deterministicComponentChoice="none";
                   }else 
                       deterministicComponentChoice = deterministicComponent.getSelectedItemText();
                       cipsModelTypeChoice = cipsModelType.getSelectedItemText();   
                       maxLagChoice = maxLag.getSelectedItem().toString();
                       truncatedChoice = truncated.isSelected();
               
                
	}
        
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.efeaeplots.moreDiagnosticPlots = "None";
          
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    
                    unitRootTestOptionsOkay = true;
			updateModel();
			this.dispose();
		}
		
	}

}