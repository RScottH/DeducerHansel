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
 
 * The code from those packages used in this one most notably come from
      GLMExplorerPlots.java, GLMExplorerPostHoc.java, and GLMExplorerOptions.java, found in the Deducer package.

The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.
 */

package hansel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
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

import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;

import org.rosuda.deducer.models.RModel;




public class GMExplorerDiagTestsVAR extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel diagPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
        protected JCheckBox archTestMulti;
        protected JLabel archTestMultiLagsMLabel;
        protected JComboBox archTestMultiLagsM;
        protected JLabel archTestMultiLagsSLabel;
        protected JComboBox archTestMultiLagsS;
        protected JLabel breuschGodfreyMLagsLabel;
        protected JComboBox breuschGodfreyMLags;
        protected JLabel portmanteauMLagsLabel;
        protected JComboBox portmanteauMLags;

        protected JCheckBox BreuschGodfreyTest;
        protected JRadioButton breuschGodfreyMAsymptotic;
        protected JRadioButton breuschGodfreyMEdgertonShukur;
        protected ButtonGroup breuschGodfreyButtons;
        protected JCheckBox ljungBoxTest;
        protected JRadioButton portmanteauMAsymptotic;
        protected JRadioButton portmanteauMAdjusted;
        protected ButtonGroup portmanteauMButtons;
        
        protected JRadioButton normalityTestMultiMonly;
        protected JCheckBox eigenvaluesVARcompanion;
        protected JRadioButton coeffsMArepOfVAROrthog;
        protected JLabel coeffsMArepOfVARnstepsLabel;
        protected JComboBox coeffsMArepOfVARnstep;
        protected JCheckBox coeffsMArepOfVAR;
        protected JComboBox coeffsMArepOfVARnsteps;
        protected JCheckBox blanchardQuahSVAR;

        protected JCheckBox archLM;
        protected JCheckBox jarqueBeraTest;
        protected JCheckBox ramseyRESET;
        protected JCheckBox hausmanTest;
        protected JCheckBox classificationTable;
        protected JCheckBox resvFitted;
        protected JCheckBox termPlots;
	protected JList terms;
	protected JPanel meansPanel;
	protected JPanel okayCancel;
	protected JCheckBox confInt;
	protected JCheckBox scaled;
	protected JPanel optionsPanel;
       	protected JPanel autocorrPanel;
       	protected JPanel heteroscPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
	protected JScrollPane termScroller;
	protected GMModel  model;
        protected Boolean setonly;
        protected JLabel classTableCutoffLabel;
        protected JComboBox classTableCutoff; 

                            protected static DefaultComboBoxModel classTableCutoffs  = new DefaultComboBoxModel(
				new String[] { "0.1", "0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1",
                                               });
                            
        protected static DefaultComboBoxModel archTestMultiLagsMAvailable  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"}); 
       protected static DefaultComboBoxModel archTestMultiLagsSAvailable  = new DefaultComboBoxModel(
				new String[] { "None: Multivariate only","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
       protected static DefaultComboBoxModel breuschGodfreyMLagsAvailable  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"}); 
       protected static DefaultComboBoxModel portmanteauMLagsAvailable  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"}); 
        protected static DefaultComboBoxModel coeffsMArepOfVARnstepsAvailable  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});       
           
	
	public GMExplorerDiagTestsVAR(JFrame frame,GMModel mod,RModel rmod) {
		super(frame);
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
					okayCancel.setBounds(205, 500, 207, 36);
                                        okayCancel.setFont(font12);
                                      {
						heteroscPanel = new JPanel();
						getContentPane().add(heteroscPanel);
						heteroscPanel.setBounds(12, 12, 358, 100);
						heteroscPanel.setLayout(thisLayout);
						heteroscPanel.setBorder(BorderFactory.createTitledBorder("Heteroscedasticity tests"));
                                                heteroscPanel.setFont(font12);
                                                {
							archTestMulti = new JCheckBox();
							heteroscPanel.add(archTestMulti);
							archTestMulti.setText("ARCH test");
                                                        archTestMulti.setFont(font12);
							archTestMulti.setBounds(17, 19, 400, 19);
						}

                                                {
                                                    
					         archTestMultiLagsMLabel = new JLabel();
                                                  heteroscPanel.add(archTestMultiLagsMLabel, new AnchorConstraint(330, 978, 105, 150, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					         archTestMultiLagsMLabel.setText("Lags Multivariate");
                                                  archTestMultiLagsMLabel.setFont(font12);
				                 archTestMultiLagsMLabel.setPreferredSize(new java.awt.Dimension(400, 30));
				                }  
                                
                                                                            
                                               {
                                                 archTestMultiLagsM = new JComboBox();
                                                 heteroscPanel.add(archTestMultiLagsM, new AnchorConstraint(550, 978, 105, 150, 
                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                             AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                 archTestMultiLagsM.setModel(archTestMultiLagsMAvailable);
                                                 archTestMultiLagsM.setFont(font12);
                                                 archTestMultiLagsM.setPreferredSize(new java.awt.Dimension(60, 18));
                                                 archTestMultiLagsM.addActionListener(this);
                                                }
                                                {
                                                    
					         archTestMultiLagsSLabel = new JLabel();
                                                  heteroscPanel.add(archTestMultiLagsSLabel, new AnchorConstraint(380, 978, 105,  500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					         archTestMultiLagsSLabel.setText("Lags for Single Equations");
                                                 archTestMultiLagsSLabel.setFont(font12);
				                 archTestMultiLagsSLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				                }  
                                
                                
                                               {
                                                 archTestMultiLagsS = new JComboBox();
                                                 heteroscPanel.add(archTestMultiLagsS, new AnchorConstraint(550, 978, 105, 500, 
                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                             AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                 archTestMultiLagsS.setModel(archTestMultiLagsSAvailable);
                                                 archTestMultiLagsS.setFont(font12);
                                                 archTestMultiLagsS.setPreferredSize(new java.awt.Dimension(150, 18));
                                                 archTestMultiLagsS.addActionListener(this);
                                                }

					}   
{
						autocorrPanel = new JPanel();
						getContentPane().add(autocorrPanel);
						autocorrPanel.setBounds(12, 125, 358, 110);
						autocorrPanel.setLayout(thisLayout);
						autocorrPanel.setBorder(BorderFactory.createTitledBorder("Autocorrelation tests"));
                                                autocorrPanel.setFont(font12);

                                                
                                                {
							BreuschGodfreyTest = new JCheckBox();
							autocorrPanel.add(BreuschGodfreyTest);
							BreuschGodfreyTest.setText("Breusch-Godfrey test");
                                                        BreuschGodfreyTest.setFont(font12);
							BreuschGodfreyTest.setBounds(17, 19, 400, 19);
						}
                                                {
							breuschGodfreyMAsymptotic = new JRadioButton();
							autocorrPanel.add(breuschGodfreyMAsymptotic);
							breuschGodfreyMAsymptotic.setText("Asymptotic");
                                                        breuschGodfreyMAsymptotic.setFont(font12);
							breuschGodfreyMAsymptotic.setBounds(27, 39, 95, 19);
						}
                                                {
							breuschGodfreyMEdgertonShukur = new JRadioButton();
							autocorrPanel.add(breuschGodfreyMEdgertonShukur);
							breuschGodfreyMEdgertonShukur.setText("Edgerton & Shukur");
                                                        breuschGodfreyMEdgertonShukur.setFont(font12);
							breuschGodfreyMEdgertonShukur.setBounds(120, 39, 120, 19);
						}
                                                {
                                                        ButtonGroup breuschGodfreyButtons =new ButtonGroup();
                                                        breuschGodfreyButtons.add(breuschGodfreyMAsymptotic);
                                                        breuschGodfreyButtons.add(breuschGodfreyMEdgertonShukur);
                                                }
                                                {
                                                    
					         breuschGodfreyMLagsLabel = new JLabel();
                                                  autocorrPanel.add(breuschGodfreyMLagsLabel, new AnchorConstraint(320, 978, 105, 700, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					         breuschGodfreyMLagsLabel.setText("Lags:");
                                                 breuschGodfreyMLagsLabel.setFont(font12);
				                 breuschGodfreyMLagsLabel.setPreferredSize(new java.awt.Dimension(400, 30));
				                }  
                                
                                                                            
                                               {
                                                 breuschGodfreyMLags = new JComboBox();
                                                 autocorrPanel.add(breuschGodfreyMLags, new AnchorConstraint(340, 978, 105, 800, 
                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                             AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                 breuschGodfreyMLags.setModel(breuschGodfreyMLagsAvailable);
                                                 breuschGodfreyMLags.setFont(font12);
                                                 breuschGodfreyMLags.setPreferredSize(new java.awt.Dimension(60, 18));
                                                 breuschGodfreyMLags.addActionListener(this);
                                                }
                                               
                                                {
							ljungBoxTest = new JCheckBox();
							autocorrPanel.add(ljungBoxTest);
							ljungBoxTest.setText("Ljung-Box (Portmanteau) test");
                                                        ljungBoxTest.setFont(font12);
							ljungBoxTest.setBounds(17, 59, 400, 19);
						}
                                               
                                                {
							portmanteauMAsymptotic = new JRadioButton();
							autocorrPanel.add(portmanteauMAsymptotic);
							portmanteauMAsymptotic.setText("Asymptotic");
                                                        portmanteauMAsymptotic.setFont(font12);
							portmanteauMAsymptotic.setBounds(27, 79, 95, 19);
						}
                                                {
							portmanteauMAdjusted = new JRadioButton();
							autocorrPanel.add(portmanteauMAdjusted);
							portmanteauMAdjusted.setText("Adjusted");
                                                        portmanteauMAdjusted.setFont(font12);
							portmanteauMAdjusted.setBounds(120, 79, 120, 19);
						}
                                                {
                                                        ButtonGroup portmanteauMButtons =new ButtonGroup();
                                                        portmanteauMButtons.add(portmanteauMAsymptotic);
                                                        portmanteauMButtons.add(portmanteauMAdjusted);
                                                }
                                                {
                                                    
					         portmanteauMLagsLabel = new JLabel();
                                                  autocorrPanel.add(portmanteauMLagsLabel, new AnchorConstraint(670, 978, 105, 700, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					         portmanteauMLagsLabel.setText("Lags:");
                                                 portmanteauMLagsLabel.setFont(font12);
				                 portmanteauMLagsLabel.setPreferredSize(new java.awt.Dimension(400, 30));
				                }  
                                
                                                                            
                                               {
                                                 portmanteauMLags = new JComboBox();
                                                 autocorrPanel.add(portmanteauMLags, new AnchorConstraint(690, 978, 105, 800, 
                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                             AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                 portmanteauMLags.setModel(portmanteauMLagsAvailable);
                                                 portmanteauMLags.setFont(font12);
                                                 portmanteauMLags.setPreferredSize(new java.awt.Dimension(60, 18));
                                                 portmanteauMLags.addActionListener(this);
                                                }

					}                                                
					{
						optionsPanel = new JPanel();
						getContentPane().add(optionsPanel);
						optionsPanel.setBounds(12, 250, 358, 250);
						optionsPanel.setLayout(thisLayout);
						optionsPanel.setBorder(BorderFactory.createTitledBorder("Other diagnostic statistics and tests"));
                                                optionsPanel.setFont(font12);

						{
							jarqueBeraTest  = new JCheckBox();
							optionsPanel.add(jarqueBeraTest);
							jarqueBeraTest.setText("Non-Normality: Jarque-Bera Test");
                                                        jarqueBeraTest.setFont(font12);
							jarqueBeraTest.setBounds(17, 19, 400, 18);
						}
                                               {
							normalityTestMultiMonly = new JRadioButton();
							optionsPanel.add(normalityTestMultiMonly);
							normalityTestMultiMonly.setText("Multivariate Only");
                                                        normalityTestMultiMonly.setFont(font12);
							normalityTestMultiMonly.setBounds(27, 39, 160, 19);
						}
                                                {
							eigenvaluesVARcompanion = new JCheckBox();
							optionsPanel.add(eigenvaluesVARcompanion);
							eigenvaluesVARcompanion.setText("Eigenvalues of companion coefficient matrix");
                                                        eigenvaluesVARcompanion.setFont(font12);
							eigenvaluesVARcompanion.setBounds(17, 69, 400, 18);
						}
                                                {
							coeffsMArepOfVAR = new JCheckBox();
							optionsPanel.add(coeffsMArepOfVAR);
							coeffsMArepOfVAR.setText("Coefficient matrices of the MA represention");
                                                        coeffsMArepOfVAR.setFont(font12);
							coeffsMArepOfVAR.setBounds(17, 99, 400, 18);
						}
                                                
                                                
                                                {
							coeffsMArepOfVAROrthog = new JRadioButton();
							optionsPanel.add(coeffsMArepOfVAROrthog);
							coeffsMArepOfVAROrthog.setText("Orthogonalized MA");
                                                        coeffsMArepOfVAROrthog.setFont(font12);
							coeffsMArepOfVAROrthog.setBounds(27, 119, 160, 19);
						}

                                                {
                                                    
					         coeffsMArepOfVARnstepsLabel = new JLabel();
                                                  optionsPanel.add(coeffsMArepOfVARnstepsLabel, new AnchorConstraint(462, 978, 105, 600, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					         coeffsMArepOfVARnstepsLabel.setText("Steps Ahead:");
                                                 coeffsMArepOfVARnstepsLabel.setFont(font12);
				                 coeffsMArepOfVARnstepsLabel.setPreferredSize(new java.awt.Dimension(90, 30));
				                }  
                                
                                                                            
                                               {
                                                 coeffsMArepOfVARnsteps = new JComboBox();
                                                 optionsPanel.add(coeffsMArepOfVARnsteps, new AnchorConstraint(470, 978, 105, 800, 
                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                             AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                 coeffsMArepOfVARnsteps.setModel(coeffsMArepOfVARnstepsAvailable);
                                                 coeffsMArepOfVARnsteps.setFont(font12);
                                                 coeffsMArepOfVARnsteps.setPreferredSize(new java.awt.Dimension(60, 18));
                                                 coeffsMArepOfVARnsteps.addActionListener(this);
                                                }
                                                {
							blanchardQuahSVAR = new JCheckBox();
							optionsPanel.add(blanchardQuahSVAR);
							blanchardQuahSVAR.setText("Blanchard-Quah type SVAR");
                                                        blanchardQuahSVAR.setFont(font12);
							blanchardQuahSVAR.setBounds(17, 139, 400, 18);
						} 
                                                
					}
				}
			}
			this.setTitle("Diagnostic Statistics and Tests");
                        this.setFont(font12);
			this.setSize(432, 600);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){           
                model.efeaetext.archTestMulti=archTestMulti.isSelected();
                model.efeaetext.archTestMultiLagsM=archTestMultiLagsM.getSelectedItem().toString();
                model.efeaetext.archTestMultiLagsS=archTestMultiLagsS.getSelectedItem().toString();
                model.efeaetext.breuschGodfreyMulti=BreuschGodfreyTest.isSelected();
                model.efeaetext.breuschGodfreyMAsymptotic=breuschGodfreyMAsymptotic.isSelected();
                model.efeaetext.breuschGodfreyMEdgertonShukur=breuschGodfreyMEdgertonShukur.isSelected();
                model.efeaetext.breuschGodfreyMLags=breuschGodfreyMLags.getSelectedItem().toString();
                model.efeaetext.portmanteauMulti=ljungBoxTest.isSelected();
                model.efeaetext.portmanteauMAsymptotic=portmanteauMAsymptotic.isSelected();
                model.efeaetext.portmanteauMAdjusted=portmanteauMAdjusted.isSelected();
                model.efeaetext.portmanteauMLags=portmanteauMLags.getSelectedItem().toString();
                model.efeaetext.normalityTestMulti=jarqueBeraTest.isSelected();
                model.efeaetext.normalityTestMultiMonly = normalityTestMultiMonly.isSelected();
                model.efeaetext.eigenvaluesVARcompanion = eigenvaluesVARcompanion.isSelected();
                model.efeaetext.coeffsMArepOfVAR = coeffsMArepOfVAR.isSelected();
                model.efeaetext.coeffsMArepOfVAROrthog = coeffsMArepOfVAROrthog.isSelected();
                model.efeaetext.coeffsMArepOfVARnsteps = coeffsMArepOfVARnsteps.getSelectedItem().toString();
                model.efeaetext.blanchardQuahSVAR = blanchardQuahSVAR.isSelected();     
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                archTestMulti.setSelected(false);
                archTestMultiLagsM.setSelectedItem(model.efeaetext.archTestMultiLagsM);
                archTestMultiLagsS.setSelectedItem(model.efeaetext.archTestMultiLagsS);
                BreuschGodfreyTest.setSelected(false);
                breuschGodfreyMAsymptotic.setSelected(model.efeaetext.breuschGodfreyMAsymptotic);
                breuschGodfreyMEdgertonShukur.setSelected(model.efeaetext.breuschGodfreyMEdgertonShukur);
                breuschGodfreyMLags.setSelectedItem(model.efeaetext.breuschGodfreyMLags);
                ljungBoxTest.setSelected(false);
                portmanteauMAsymptotic.setSelected(model.efeaetext.portmanteauMAsymptotic);
                portmanteauMAdjusted.setSelected(model.efeaetext.portmanteauMAdjusted);
                portmanteauMLags.setSelectedItem(model.efeaetext.portmanteauMLags);
                jarqueBeraTest.setSelected(false);
                normalityTestMultiMonly.setSelected(false);
                eigenvaluesVARcompanion.setSelected(false);
                coeffsMArepOfVAR.setSelected(false);
                coeffsMArepOfVAROrthog.setSelected(false);
                coeffsMArepOfVARnsteps.setSelectedItem(model.efeaetext.coeffsMArepOfVARnsteps);
                blanchardQuahSVAR.setSelected(false);           
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
			updateModel();
			this.dispose();
                 }else if(cmd=="Set Restrictions through Clicking"){
                         model.efeGLMOptions.coeffTestsGUI=true;	
                         this.dispose();
		}
		
	}

}