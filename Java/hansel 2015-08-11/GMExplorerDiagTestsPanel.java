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
 
 The current file notably uses code from GLMExplorerPlots.java and GLMExplorerPostHoc.java in the Deducer package.
 
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


public class GMExplorerDiagTestsPanel extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel diagPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
        protected JLabel breuschPaganLabel;
        protected JLabel locallyRobustLabel;
        protected JLabel baltagiLiLabel ;  
        protected JLabel crossSectionDependenceLabel;
        protected JLabel lmLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
        protected JCheckBox breuschPaganTest;
        protected JCheckBox breuschPaganTestEffectsOnly;
        protected JCheckBox whiteTest;
        protected JCheckBox durbinWatsonTest;
        protected JCheckBox BreuschGodfreyTest;
        protected JCheckBox woolridgeUnobservedEffectsTest;       
        protected JCheckBox woolridgeTestShortFE;
        protected JCheckBox woolridgeTest1stDiff;
        protected JCheckBox localRobustJointTest;
        protected JCheckBox localRobustAR1Test;
        protected JCheckBox localRobustRETest;
        protected JCheckBox baltagiLiTest1sided;
        protected JCheckBox baltagiLiTest2sided;
        protected JCheckBox crossSectionDepTestBP;
        protected JCheckBox crossSectionDepTestScaledBP;
        protected JCheckBox crossSectionDepTestPesaran;
        protected JCheckBox arellanoBondTest;
        protected JCheckBox LMTestBP;        
         protected JCheckBox LMTestGhm;       
        protected JCheckBox LMTestHonda;
        protected JCheckBox LMTestKw;        
        protected JCheckBox pFTest;        
        protected JCheckBox poolabilityTest;
      	protected JCheckBox influence;
        protected JCheckBox archLM;
        protected JCheckBox jarqueBeraTest;
        protected JCheckBox overIDRestrictionsTest;
        protected JCheckBox hausmanTest;
        protected JCheckBox spHausmanTest;
        protected JCheckBox bsjkTest;
        protected JCheckBox bskTest;
        protected JCheckBox resvFitted;
        protected JCheckBox termPlots;
	protected JList terms;
	protected JPanel meansPanel;
	protected JPanel okayCancel;
	protected JCheckBox confInt;
	protected JCheckBox scaled;
	protected JPanel optionsPanel;
       	protected JPanel serialcorrPanel;
        protected JPanel effectTestsPanel;
       	protected JPanel heteroscPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
	protected JScrollPane termScroller;
	protected GMModel  model;
        protected Boolean setonly;
        protected JLabel bsjkTestypeLabel;
        protected JComboBox bsjktestType;
                protected static DefaultComboBoxModel bsjktestTypeList  = new DefaultComboBoxModel(
				new String[] { "C.1","C.2", "C.3", "J"
                                               });  
       protected JLabel bsktestTypeLabel;
       protected JComboBox bsktestType;
                protected static DefaultComboBoxModel bsktestTypeList  = new DefaultComboBoxModel(
				new String[] { "LMH","LM1", "LM2", "CLMlambda","CLMmu"
                                               });
                
                
	public GMExplorerDiagTestsPanel(JFrame frame,GMModel mod,RModel rmod) {
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
				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(205, 600, 207, 36);
                                        okayCancel.setFont(font12);
                                      {
						heteroscPanel = new JPanel();
						getContentPane().add(heteroscPanel);
						heteroscPanel.setBounds(12, 12, 425, 70);
						heteroscPanel.setLayout(thisLayout);
						heteroscPanel.setBorder(BorderFactory.createTitledBorder("Heteroscedasticity tests"));
                                                heteroscPanel.setFont(font12);
                                                {                                                                
                                                breuschPaganLabel = new JLabel();
                                                                heteroscPanel.add(breuschPaganLabel, new AnchorConstraint(200, 200, 60, 50, 
							              AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							              AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                breuschPaganLabel.setText("Breusch-Pagan test:");
                                                                breuschPaganLabel.setFont(font12);
                                                        	breuschPaganLabel.setPreferredSize(new java.awt.Dimension(300, 20));
                                                                breuschPaganLabel.setVisible(true);
                                                }  
                                                
                                                
						{
							breuschPaganTest = new JCheckBox();
							heteroscPanel.add(breuschPaganTest);
							breuschPaganTest.setText("all regressors in test");
                                                        breuschPaganTest.setFont(font12);
							breuschPaganTest.setBounds(30, 35, 170, 19);
						}
                                                {
							breuschPaganTestEffectsOnly = new JCheckBox();
							heteroscPanel.add(breuschPaganTestEffectsOnly);
							breuschPaganTestEffectsOnly.setText("effect regressors only in test");
                                                        breuschPaganTestEffectsOnly.setFont(font12);
							breuschPaganTestEffectsOnly.setBounds(220, 35, 170, 19);
						}

					}   
{
						serialcorrPanel = new JPanel();
						getContentPane().add(serialcorrPanel);
						serialcorrPanel.setBounds(12, 90, 425, 185);
						serialcorrPanel.setLayout(thisLayout);
						serialcorrPanel.setBorder(BorderFactory.createTitledBorder("Serial correlation tests"));
                                                serialcorrPanel.setFont(font12);
						{
							durbinWatsonTest = new JCheckBox();
							serialcorrPanel.add(durbinWatsonTest);
							durbinWatsonTest.setText("Durbin-Watson test");
                                                        durbinWatsonTest.setFont(font12);
							durbinWatsonTest.setBounds(17, 19, 200, 19);
						}
                                                {
							BreuschGodfreyTest = new JCheckBox();
							serialcorrPanel.add(BreuschGodfreyTest);
							BreuschGodfreyTest.setText("Breusch-Godfrey/Woolridge");
                                                        BreuschGodfreyTest.setFont(font12);
							BreuschGodfreyTest.setBounds(220, 19, 200, 19);
						}
                                                
                                                               
              
 						{
							woolridgeTestShortFE = new JCheckBox();
							serialcorrPanel.add(woolridgeTestShortFE);
							woolridgeTestShortFE.setText("Woolridge, in \"short\" FE panels");
                                                        woolridgeTestShortFE.setFont(font12);
							woolridgeTestShortFE.setBounds(17, 39, 200, 19);
						}
                                                {
							woolridgeUnobservedEffectsTest = new JCheckBox();
							serialcorrPanel.add(woolridgeUnobservedEffectsTest);
							woolridgeUnobservedEffectsTest.setText("Woolridge Unobserved Effects Test");
                                                        woolridgeUnobservedEffectsTest.setFont(font12);
							woolridgeUnobservedEffectsTest.setBounds(220, 39, 200, 19);
						}  

                                                {
							woolridgeTest1stDiff = new JCheckBox();
							serialcorrPanel.add(woolridgeTest1stDiff);
							woolridgeTest1stDiff.setText("Woolridge 1st-Difference based");
                                                        woolridgeTest1stDiff.setFont(font12);
							woolridgeTest1stDiff.setBounds(17, 59, 200, 19);
						}
                                                {
							arellanoBondTest = new JCheckBox();
							serialcorrPanel.add(arellanoBondTest);
							arellanoBondTest.setText("Arellano-Bond");
                                                        arellanoBondTest.setFont(font12);
							arellanoBondTest.setBounds(220, 59, 200, 19);
						}
                                                {                                                                
                                                locallyRobustLabel = new JLabel();
                                                                serialcorrPanel.add(locallyRobustLabel, new AnchorConstraint(430, 200, 60, 50, 
							              AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							              AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                locallyRobustLabel.setText("Locally robust tests (Bera, Sosa-Escudero, and Yoon; Baltalgi & Li):");
                                                                locallyRobustLabel.setFont(font12);
                                                        	locallyRobustLabel.setPreferredSize(new java.awt.Dimension(400, 20));
                                                                locallyRobustLabel.setVisible(true);
                                                }  
                                                {
							localRobustAR1Test = new JCheckBox();
							serialcorrPanel.add(localRobustAR1Test);
							localRobustAR1Test.setText("AR(1)");
                                                        localRobustAR1Test.setFont(font12);
							localRobustAR1Test.setBounds(30, 99, 80, 19);
						}
                                                {
							localRobustRETest = new JCheckBox();
							serialcorrPanel.add(localRobustRETest);
							localRobustRETest.setText("random effects");
                                                        localRobustRETest.setFont(font12);
                                                        localRobustRETest.setBounds(125, 99, 120, 19);
						} 
         
                                                {
							localRobustJointTest = new JCheckBox();
							serialcorrPanel.add(localRobustJointTest);
							localRobustJointTest.setText("joint");
                                                        localRobustJointTest.setFont(font12);
							localRobustJointTest.setBounds(270, 99, 150, 19);
						}
                                                {       
                                                }    
                                                
                                                baltagiLiLabel = new JLabel();
                                                                serialcorrPanel.add(baltagiLiLabel, new AnchorConstraint(650, 200, 60, 50, 
							              AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							              AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                baltagiLiLabel.setText("Baltagi and Li conditional LM test:");
                                                                baltagiLiLabel.setFont(font12);
                                                        	baltagiLiLabel.setPreferredSize(new java.awt.Dimension(300, 20));
                                                                baltagiLiLabel.setVisible(true);
                                                {
							baltagiLiTest2sided = new JCheckBox();
							serialcorrPanel.add(baltagiLiTest2sided);
							baltagiLiTest2sided.setText("2-sided");
                                                        baltagiLiTest2sided.setFont(font12);
							baltagiLiTest2sided.setBounds(190, 122, 79, 19);
						} 
                                                {
							baltagiLiTest1sided = new JCheckBox();
							serialcorrPanel.add(baltagiLiTest1sided);
							baltagiLiTest1sided.setText("1-sided");
                                                        baltagiLiTest1sided.setFont(font12);
							baltagiLiTest1sided.setBounds(270, 122, 80, 19);
						} 

                                                
                                                {       
                                                    
                                                crossSectionDependenceLabel = new JLabel();
                                                                serialcorrPanel.add(crossSectionDependenceLabel, new AnchorConstraint(750, 200, 60, 50, 
							              AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							              AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                crossSectionDependenceLabel.setText("Cross-sectional dependence tests:");
                                                                crossSectionDependenceLabel.setFont(font12);
                                                        	crossSectionDependenceLabel.setPreferredSize(new java.awt.Dimension(300, 20));
                                                                crossSectionDependenceLabel.setVisible(true);
                                                }   
                                                {
							crossSectionDepTestBP = new JCheckBox();
							serialcorrPanel.add(crossSectionDepTestBP);
							crossSectionDepTestBP.setText("Breusch-Pagan");
                                                        crossSectionDepTestBP.setFont(font12);
							crossSectionDepTestBP.setBounds(30, 159, 130, 19);
						} 
                                                {
							crossSectionDepTestScaledBP = new JCheckBox();
							serialcorrPanel.add(crossSectionDepTestScaledBP);
							crossSectionDepTestScaledBP.setText("scaled Breusch-Pagan");
                                                        crossSectionDepTestScaledBP.setFont(font12);
							crossSectionDepTestScaledBP.setBounds(170, 159, 140, 19);
						} 
                                                {
							crossSectionDepTestPesaran = new JCheckBox();
							serialcorrPanel.add(crossSectionDepTestPesaran);
							crossSectionDepTestPesaran.setText("Pesaran");
                                                        crossSectionDepTestPesaran.setFont(font12);
							crossSectionDepTestPesaran.setBounds(320, 159, 90, 19);
						} 
                                                

					}       
{
						effectTestsPanel = new JPanel();
						getContentPane().add(effectTestsPanel);
						effectTestsPanel.setBounds(12, 275, 425, 120);
						effectTestsPanel.setLayout(thisLayout);
		 				effectTestsPanel.setBorder(BorderFactory.createTitledBorder("Poolability and effects tests"));
                                                effectTestsPanel.setFont(font12);
                                                                
                                                {
							poolabilityTest = new JCheckBox();
							effectTestsPanel.add(poolabilityTest);
							poolabilityTest.setText("Poolability test");
                                                        poolabilityTest.setFont(font12);
							poolabilityTest.setBounds(17, 19, 200, 19);

						}
                                                
                                                {
							pFTest = new JCheckBox();
							effectTestsPanel.add(pFTest);
							pFTest.setText("F test for individual effects");
                                                        pFTest.setFont(font12);
							pFTest.setBounds(220, 19, 200, 19);
						}
                                                {                                                                
                                                lmLabel = new JLabel();
                                                                effectTestsPanel.add(lmLabel, new AnchorConstraint(320, 200, 60, 50, 
							              AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							              AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lmLabel.setText("Lagrange Multiplier Effects Tests:");
                                                                lmLabel.setFont(font12);
                                                        	lmLabel.setPreferredSize(new java.awt.Dimension(300, 20));
                                                                lmLabel.setVisible(true);
                                                }    
						{
							LMTestBP = new JCheckBox();
							effectTestsPanel.add(LMTestBP );
							LMTestBP.setText("Breusch-Pagan");
                                                        LMTestBP.setFont(font12);
							LMTestBP.setBounds(30, 59, 170, 19);
						}
                                                {
							LMTestGhm= new JCheckBox();
							effectTestsPanel.add(LMTestGhm);
							LMTestGhm.setText("Gourieroux, Holly, and Montfort");
                                                        LMTestGhm.setFont(font12);
							LMTestGhm.setBounds(220, 59, 200, 19); 
						}
 						{
							LMTestHonda = new JCheckBox();
							effectTestsPanel.add(LMTestHonda);
							LMTestHonda.setText("Honda");
                                                        LMTestHonda.setFont(font12);
							LMTestHonda.setBounds(30, 79, 170, 19);
                                                        
						}
                                                
                                                {
							LMTestKw = new JCheckBox();
							effectTestsPanel.add(LMTestKw);
							LMTestKw.setText("King and Wu");
                                                        LMTestKw.setFont(font12);
							LMTestKw.setBounds(220, 79, 200, 19); 
						}
                                                

					}      
					{   
						optionsPanel = new JPanel();
						getContentPane().add(optionsPanel);
						optionsPanel.setBounds(12, 400, 425, 190);
						optionsPanel.setLayout(thisLayout);
						optionsPanel.setBorder(BorderFactory.createTitledBorder("Other diagnostic statistics and tests"));
                                                optionsPanel.setFont(font12);

                                                {
							hausmanTest = new JCheckBox();
							optionsPanel.add(hausmanTest );
							hausmanTest.setText("Hausman Test: random effects vs fixed effects");
                                                        hausmanTest.setFont(font12);
							hausmanTest.setBounds(17, 19, 400, 18);

						}
                                                {
							overIDRestrictionsTest  = new JCheckBox();
							optionsPanel.add(overIDRestrictionsTest);
							overIDRestrictionsTest.setText("Overidentifying Restrictions Test");
                                                        overIDRestrictionsTest.setFont(font12);
							overIDRestrictionsTest.setBounds(17, 39, 400, 18);
                                                         
						}
						{
							jarqueBeraTest  = new JCheckBox();
							optionsPanel.add(jarqueBeraTest);
							jarqueBeraTest.setText("Non-Normality: Jarque-Bera Test");
                                                        jarqueBeraTest.setFont(font12);
							jarqueBeraTest.setBounds(17, 59, 400, 18);
						}
                                                {
							spHausmanTest  = new JCheckBox();
							optionsPanel.add(spHausmanTest);
							spHausmanTest.setText("Hausman test for spatial panel models: random effects vs fixed effects");
                                                        spHausmanTest.setFont(font12);
							spHausmanTest.setBounds(17, 79, 400, 18);
						}
                                                {
							bsjkTest  = new JCheckBox();
							optionsPanel.add(bsjkTest);
							bsjkTest.setText("Baltagi, Song, Jung and Koh LM test for spatial panels");
                                                        bsjkTest.setFont(font12);
							bsjkTest.setBounds(17, 99, 400, 18);
						}
                                                                                                {
                                                        bsjkTestypeLabel = new JLabel();
					                optionsPanel.add(bsjkTestypeLabel, new AnchorConstraint(620, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        bsjkTestypeLabel.setText("test type:");
                                                        bsjkTestypeLabel.setFont(font12);
					                bsjkTestypeLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        bsjkTestypeLabel.setVisible(true);
                                                        
                                                        bsjktestType = new JComboBox();
                                                        optionsPanel.add(bsjktestType, new AnchorConstraint(630, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        bsjktestType.setModel(bsjktestTypeList);
                                                        bsjktestType.setFont(font12);
                                                        bsjktestType.setPreferredSize(new java.awt.Dimension(200, 21));
                                                        bsjktestType.addActionListener(this);
                                                        bsjktestType.setVisible(true);
                                                } 
                                                
                                                {
							bskTest  = new JCheckBox();
							optionsPanel.add(bskTest);
							bskTest.setText("Baltagi, Song and Koh LM test for spatial panels");
                                                        bskTest.setFont(font12);
							bskTest.setBounds(17, 139, 400, 18);
						}
                                                
                                                                                                {
                                                        bsktestTypeLabel = new JLabel();
					                optionsPanel.add(bsktestTypeLabel, new AnchorConstraint(840, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        bsktestTypeLabel.setText("test type:");
                                                        bsktestTypeLabel.setFont(font12);
					                bsktestTypeLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        bsktestTypeLabel.setVisible(true);                                            
                                                                                                    
                                                        bsktestType = new JComboBox();
                                                        optionsPanel.add(bsktestType, new AnchorConstraint(850, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        bsktestType.setModel(bsktestTypeList);
                                                        bsktestType.setFont(font12);
                                                        bsktestType.setPreferredSize(new java.awt.Dimension(140, 21));
                                                        bsktestType.addActionListener(this);
                                                        bsktestType.setVisible(true);
                                                } 
 
					}
				}
			}
                      }
			this.setTitle("Diagnostic Statistics and Tests");
                        this.setFont(font12);
			this.setSize(460, 700);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
                model.efeaetext.breuschPaganTest=breuschPaganTest.isSelected();
                model.efeaetext.breuschPaganTestEffectsOnly=breuschPaganTestEffectsOnly.isSelected(); 
                model.efeaetext.durbinWatsonTest=durbinWatsonTest.isSelected();
                model.efeaetext.BreuschGodfreyTest=BreuschGodfreyTest.isSelected();                
                model.efeaetext.woolridgeUnobservedEffectsTest =woolridgeUnobservedEffectsTest.isSelected();       
                model.efeaetext.woolridgeTestShortFE =woolridgeTestShortFE.isSelected();
                model.efeaetext.woolridgeTest1stDiff =woolridgeTest1stDiff.isSelected();
                model.efeaetext.localRobustJointTest=localRobustJointTest.isSelected();
                model.efeaetext.localRobustAR1Test =localRobustAR1Test.isSelected();
                model.efeaetext.localRobustRETest =localRobustRETest.isSelected();
                model.efeaetext.baltagiLiTest1sided =baltagiLiTest1sided.isSelected();
                model.efeaetext.baltagiLiTest2sided =baltagiLiTest2sided.isSelected();
                model.efeaetext.crossSectionDepTestBP =crossSectionDepTestBP.isSelected();
                model.efeaetext.crossSectionDepTestScaledBP =crossSectionDepTestScaledBP.isSelected();
                model.efeaetext.crossSectionDepTestPesaran =crossSectionDepTestPesaran.isSelected();
                model.efeaetext.arellanoBondTest=arellanoBondTest.isSelected();
                model.efeaetext.LMTestBP=LMTestBP.isSelected();                
                model.efeaetext.LMTestGhm=LMTestGhm.isSelected();                
                model.efeaetext.LMTestHonda=LMTestHonda.isSelected();                
                model.efeaetext.LMTestKw=LMTestKw.isSelected();
                model.efeaetext.pFTest=pFTest.isSelected();                 
                model.efeaetext.poolabilityTest=poolabilityTest.isSelected();
                model.efeaetext.jarqueBeraTest=jarqueBeraTest.isSelected();
                model.efeaetext.overIDRestrictionsTest=overIDRestrictionsTest.isSelected();
                model.efeaetext.hausmanTest=hausmanTest.isSelected();
                model.efeaetext.spHausmanTest=spHausmanTest.isSelected();
                model.efeaetext.bsjkTest=bsjkTest.isSelected();
                model.efeaetext.bskTest=bskTest.isSelected();
                model.efeaetext.bsjktestType = bsjktestType.getSelectedItem().toString();
                model.efeaetext.bsktestType = bsktestType.getSelectedItem().toString();
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.efeaetext.breuschPaganTest = false;
                model.efeaetext.breuschPaganTestEffectsOnly = false;
                model.efeaetext.whiteTest = false;
                model.efeaetext.durbinWatsonTest = false;
                model.efeaetext.BreuschGodfreyTest = false;
                model.efeaetext.ljungBoxTest = false;                
                model.efeaetext.woolridgeUnobservedEffectsTest = false;        
                model.efeaetext.woolridgeTestShortFE = false;
                model.efeaetext.woolridgeTest1stDiff = false;
                model.efeaetext.localRobustJointTest = false;
                model.efeaetext.localRobustAR1Test = false;
                model.efeaetext.localRobustRETest = false;
                model.efeaetext.baltagiLiTest1sided = false;
                model.efeaetext.baltagiLiTest2sided = false;
                model.efeaetext.crossSectionDepTestBP = false;
                model.efeaetext.crossSectionDepTestScaledBP = false;
                model.efeaetext.crossSectionDepTestPesaran = false;
                model.efeaetext.arellanoBondTest = false;
                model.efeaetext.LMTestBP = false;
                model.efeaetext.LMTestGhm = false;                
                model.efeaetext.LMTestHonda = false;
                model.efeaetext.LMTestKw= false;
                model.efeaetext.pFTest = false;                
                model.efeaetext.poolabilityTest = false;
                model.efeaetext.influence = false;
                model.efeaetext.jarqueBeraTest = false;
                model.efeaetext.ramseyRESET = false;
                model.efeaetext.overIDRestrictionsTest = false;
                model.efeaetext.hausmanTest = false;
                model.efeGLMOptions.confIntervals = false;
                bsjktestType.setSelectedItem(model.efeaetext.bsjktestType);
                bsktestType.setSelectedItem(model.efeaetext.bsktestType);
                
                spHausmanTest.setEnabled(false); //currently not working correctly
                if (model.listwString.equals("")) { 
                    bsjkTest.setEnabled(false);
                    bsjkTestypeLabel.setEnabled(false);
                    bsjktestType.setEnabled(false);
                    bskTest.setEnabled(false);     
                    bsktestTypeLabel.setEnabled(false);
                    bsktestType.setEnabled(false);
                }
                

                if (!(model.method.equals("plm")&
                     (model.plmPanelType.equals("pooled OLS")||
                     model.plmPanelType.equals("within (\"fixed\") effects")||
                     model.plmPanelType.equals("random effects: Swar")||
                     model.plmPanelType.equals("random effects: Walhus")||
                     model.plmPanelType.equals("random effects: Amemiya")||
                     model.plmPanelType.equals("random effects: Nerlove"))
                    )){ 
                     localRobustAR1Test.setEnabled(false);
                     durbinWatsonTest.setEnabled(false);
                     BreuschGodfreyTest.setEnabled(false);
                } else {

                }
                
                if ((!model.plmPanelType.equals("within (\"fixed\") effects"))||
                    model.method.equals("pgmm")){ 
                     woolridgeTestShortFE.setEnabled(false);
                }
                
                if (model.method.equals("pgmm")){ 
                 breuschPaganTest.setVisible(false);
                 breuschPaganTestEffectsOnly.setVisible(false);
                 woolridgeTestShortFE.setEnabled(false);
                 crossSectionDepTestBP.setEnabled(false);
                 crossSectionDepTestScaledBP.setEnabled(false);
                 crossSectionDepTestPesaran.setEnabled(false);
                 poolabilityTest.setEnabled(false);
                }
                
                if (!(model.method.equals("plm")&
                     model.plmPanelType.equals("within (\"fixed\") effects")||
                     model.plmPanelType.equals("first-differences")
                        
                    )){ 
                     woolridgeTest1stDiff.setEnabled(false);
                }
                 if (!((model.method.equals("plm")||
                       model.method.equals("pggls")||model.method.equals("pcce")) &
                      (model.plmPanelType.equals("pooled OLS")||model.plmPanelType.equals("pooling")||
                     model.plmPanelType.equals("standard residuals"))                     
                    )){ 
                    localRobustAR1Test.setEnabled(false);
                }
                 if (!((model.method.equals("plm")||
                       model.method.equals("pvcm")||
                       model.method.equals("pggls")||model.method.equals("pcce")) &
                      (model.plmPanelType.equals("pooled OLS")||model.plmPanelType.equals("pooling")||
                     model.plmPanelType.equals("random effects")||
                     model.plmPanelType.equals("random effects: Swar")||
                     model.plmPanelType.equals("random effects: Walhus")||
                     model.plmPanelType.equals("random effects: Amemiya")||
                     model.plmPanelType.equals("random effects: Nerlove")||
                     model.plmPanelType.equals("standard residuals"))                     
                    )){ 
                     localRobustRETest.setEnabled(false);
                     localRobustJointTest.setEnabled(false);
                }
                 
                   if (!((model.method.equals("plm")||
                       model.method.equals("pvcm")||
                       model.method.equals("pggls")) &
                      (
                     model.plmPanelType.equals("random effects")||
                     model.plmPanelType.equals("random effects: Swar")||
                     model.plmPanelType.equals("random effects: Walhus")||
                     model.plmPanelType.equals("random effects: Amemiya")||
                     model.plmPanelType.equals("random effects: Nerlove"))                     
                    )){ 
                     baltagiLiTest1sided.setEnabled(false);
                     baltagiLiTest2sided.setEnabled(false);
                }              
                 
                 

                
                 if (!((model.method.equals("plm")&
                     model.plmPanelType.equals("pooled OLS")||
                     model.plmPanelType.equals("within (\"fixed\") effects"))
                     || (model.method.equals("pvcm")&
                     model.plmPanelType.equals("within (\"fixed\") effects"))   
                         )
                    ){ 
                     poolabilityTest.setEnabled(false);
                }
                 if (!((model.method.equals("plm")&
                     (model.plmPanelType.equals("pooled OLS")||
                     model.plmPanelType.equals("within (\"fixed\") effects")))  
                         )
                    ){ 
                     
                     pFTest.setEnabled(false);
                }
                if (!((model.method.equals("plm")&
                     model.plmPanelType.equals("pooled OLS"))
                         )
                    ){ 
                     LMTestBP.setEnabled(false);
                     LMTestGhm.setEnabled(false);
                     LMTestHonda.setEnabled(false);
                     LMTestKw.setEnabled(false);
                }
               
                 
                if (!(model.method.equals("plm")&
                     (model.plmPanelType.equals("within (\"fixed\") effects")||
                     model.plmPanelType.equals("random effects: Swar")||
                     model.plmPanelType.equals("random effects: Walhus")||
                     model.plmPanelType.equals("random effects: Amemiya")||
                     model.plmPanelType.equals("random effects: Nerlove"))
                    )){ 
                     hausmanTest.setEnabled(false);
                }

                hausmanTest.setSelected(model.efeaetext.hausmanTest);
                
                if ((model.effect.equals("individual"))||(model.effect.equals("time"))) {
                        LMTestGhm.setVisible(false);
                         LMTestKw.setVisible(false);
                        }
                 if (!(model.modeltype.equals("pooling"))){
                        woolridgeUnobservedEffectsTest.setVisible(false);
                        }           
                if (!(model.method.equals("pgmm")
                    )){
                     arellanoBondTest.setEnabled(false);
                     overIDRestrictionsTest.setEnabled(false);
                }
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