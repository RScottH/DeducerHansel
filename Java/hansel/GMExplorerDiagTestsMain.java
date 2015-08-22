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
 
The code from those packages used in this one most notably come from
     GLMExplorerPlots.java and GLMExplorerPostHoc.java, found in the Deducer package
 
The current file made adjustments to that earlier java code on 2013-07-04 to work with the DeducerHansel package.
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
import javax.swing.JOptionPane;
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




public class GMExplorerDiagTestsMain extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel diagPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
        protected JCheckBox breuschPaganTest;
        protected JCheckBox whiteTest;
        protected JCheckBox durbinWatsonTest;
        protected JCheckBox BreuschGodfreyTest;
        protected JCheckBox ljungBoxTest;
        protected JCheckBox LMSpatialDep;
        protected JCheckBox MoransIorIiTest;
        protected JCheckBox paramCorr;
	protected JCheckBox vif;
      	protected JCheckBox influence;
        protected JCheckBox archLM;
        protected JCheckBox jarqueBeraTest;
        protected JCheckBox ramseyRESET;
        protected JCheckBox weakInstrumentsTestivreg;
        protected JCheckBox sarganTestivreg;
        protected JCheckBox hausmanTestivreg;
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
        protected JPanel spatialErrorCorrPanel;
       	protected JPanel heteroscPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
	protected JScrollPane termScroller;
	protected GMModel  model;
        protected Boolean setonly;
        
        protected JLabel pAdjustMethodLabel;
        protected JComboBox pAdjustMethod;
                protected static DefaultComboBoxModel pAdjustMethodList  = new DefaultComboBoxModel(
				new String[] { "none","bonferroni", "holm", "hochberg","hommel",
                                               "fdr"});
        protected JLabel moransITestypeLabel;
        protected JComboBox moransITestType;
                protected static DefaultComboBoxModel moransITestTypeList  = new DefaultComboBoxModel(
				new String[] { "original","exact global",
                                               "saddlepoint approx of global",
                                               });        
                
        protected JLabel moransIaltHypothLabel;
        protected JComboBox moransIaltHypoth;
                protected static DefaultComboBoxModel moransIaltHypothList  = new DefaultComboBoxModel(
				new String[] { "greater","less", "two.sided", 
                                               });          

       protected JLabel moransIaltResFunLabel;
       protected JComboBox moransIaltResFun;
                protected static DefaultComboBoxModel moransIaltResFunList  = new DefaultComboBoxModel(
				new String[] { "residuals","weighted.residuals", "rstandard", "rstudent"
                                               });
        
        protected JLabel classTableCutoffLabel;      
        protected JComboBox classTableCutoff; 

                            protected static DefaultComboBoxModel classTableCutoffs  = new DefaultComboBoxModel(
				new String[] { "0.1", "0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1",
                                               });
	
	public GMExplorerDiagTestsMain(JFrame frame,GMModel mod,RModel rmod) {
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
					okayCancel.setBounds(205, 620, 207, 36);
                                        okayCancel.setFont(font12);
                                      {
						heteroscPanel = new JPanel();
						getContentPane().add(heteroscPanel);
						heteroscPanel.setBounds(12, 12, 358, 60);
						heteroscPanel.setLayout(null);
						heteroscPanel.setBorder(BorderFactory.createTitledBorder("Heteroscedasticity tests"));
						heteroscPanel.setFont(font12);
                                                {
							breuschPaganTest = new JCheckBox();
							heteroscPanel.add(breuschPaganTest);
							breuschPaganTest.setText("Breusch-Pagan test");
                                                        breuschPaganTest.setFont(font12);
							breuschPaganTest.setBounds(17, 19, 150, 19);
						} 
                                                {
							whiteTest = new JCheckBox();
							heteroscPanel.add(whiteTest);
							whiteTest.setText("White test");
                                                        whiteTest.setFont(font12);
							whiteTest.setBounds(200, 19, 100, 19);
						}                  

					}   
                                      
                                      
                                        {
						autocorrPanel = new JPanel();
						getContentPane().add(autocorrPanel);
						autocorrPanel.setBounds(12, 85, 358, 110);
						autocorrPanel.setLayout(null);
						autocorrPanel.setBorder(BorderFactory.createTitledBorder("Autocorrelation tests"));
						autocorrPanel.setFont(font12);
                                                {
							durbinWatsonTest = new JCheckBox();
							autocorrPanel.add(durbinWatsonTest);
							durbinWatsonTest.setText("Durbin-Watson test");
                                                        durbinWatsonTest.setFont(font12);
							durbinWatsonTest.setBounds(17, 19, 400, 19);
						}
                                                {
							BreuschGodfreyTest = new JCheckBox();
							autocorrPanel.add(BreuschGodfreyTest);
							BreuschGodfreyTest.setText("Breusch-Godfrey test");
                                                        BreuschGodfreyTest.setFont(font12);
							BreuschGodfreyTest.setBounds(17, 49, 400, 19);
						}
 						{
							ljungBoxTest = new JCheckBox();
							autocorrPanel.add(ljungBoxTest);
							ljungBoxTest.setText("Ljung-Box (Portmanteau) test");
                                                        ljungBoxTest.setFont(font12);
							ljungBoxTest.setBounds(17, 79, 400, 19);
						}

					}     
                                         {
						spatialErrorCorrPanel = new JPanel();
						getContentPane().add(spatialErrorCorrPanel);
						spatialErrorCorrPanel.setBounds(12, 200, 358, 180);
						spatialErrorCorrPanel.setLayout(thisLayout);
						spatialErrorCorrPanel.setBorder(BorderFactory.createTitledBorder("Spatial tests"));
						spatialErrorCorrPanel.setFont(font12);
                                                {
							LMSpatialDep = new JCheckBox();
							spatialErrorCorrPanel.add(LMSpatialDep);
							LMSpatialDep.setText("LM diagnostics for spatial dependence");
                                                        LMSpatialDep.setFont(font12);
							LMSpatialDep.setBounds(17, 19, 400, 19);
						}
                                                
                                                {
                                                        pAdjustMethodLabel = new JLabel();
					                spatialErrorCorrPanel.add(pAdjustMethodLabel, new AnchorConstraint(240, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        pAdjustMethodLabel.setText("p adjustment:");
                                                        pAdjustMethodLabel.setFont(font12);
					                pAdjustMethodLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        pAdjustMethodLabel.setVisible(true);
                                                    
                                                    
                                                        pAdjustMethod = new JComboBox();
                                                        spatialErrorCorrPanel.add(pAdjustMethod, new AnchorConstraint(250, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        pAdjustMethod.setModel(pAdjustMethodList);
                                                        pAdjustMethod.setFont(font12);
                                                        pAdjustMethod.setPreferredSize(new java.awt.Dimension(140, 21));
                                                        pAdjustMethod.addActionListener(this);
                                                        pAdjustMethod.setVisible(true);
                                                } 
                                                            
                                                
                                                
 						{
							MoransIorIiTest = new JCheckBox();
							spatialErrorCorrPanel.add(MoransIorIiTest);
							MoransIorIiTest.setText("Moran's I test");
                                                        MoransIorIiTest.setFont(font12);
							MoransIorIiTest.setBounds(17, 69, 400, 19);
						}                                              
                                                                                                 
                                                
                                                {
                                                        moransITestypeLabel = new JLabel();
					                spatialErrorCorrPanel.add(moransITestypeLabel, new AnchorConstraint(500, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransITestypeLabel.setText("test type:");
                                                        moransITestypeLabel.setFont(font12);
					                moransITestypeLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        moransITestypeLabel.setVisible(true);
                                                        
                                                        moransITestType = new JComboBox();
                                                        spatialErrorCorrPanel.add(moransITestType, new AnchorConstraint(510, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransITestType.setModel(moransITestTypeList);
                                                        moransITestType.setFont(font12);
                                                        moransITestType.setPreferredSize(new java.awt.Dimension(200, 21));
                                                        moransITestType.addActionListener(this);
                                                        moransITestType.setVisible(true);
                                                } 
                                                {
                                                        moransIaltHypothLabel = new JLabel();
					                spatialErrorCorrPanel.add(moransIaltHypothLabel, new AnchorConstraint(640, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransIaltHypothLabel.setText("alt. hypothesis:");
                                                        moransIaltHypothLabel.setFont(font12);
					                moransIaltHypothLabel.setPreferredSize(new java.awt.Dimension(600, 30));
                                                        moransIaltHypothLabel.setVisible(true);
                                                    
                                                        moransIaltHypoth = new JComboBox();
                                                        spatialErrorCorrPanel.add(moransIaltHypoth, new AnchorConstraint(650, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransIaltHypoth.setModel(moransIaltHypothList);
                                                        moransIaltHypoth.setFont(font12);
                                                        moransIaltHypoth.setPreferredSize(new java.awt.Dimension(90, 21));
                                                        moransIaltHypoth.addActionListener(this);
                                                        moransIaltHypoth.setVisible(true);
                                                } 
                                                {
                                                        moransIaltResFunLabel = new JLabel();
					                spatialErrorCorrPanel.add(moransIaltResFunLabel, new AnchorConstraint(790, 931, 805, 150,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransIaltResFunLabel.setText("residuals function:");
                                                        moransIaltResFunLabel.setFont(font12);
					                moransIaltResFunLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        moransIaltResFunLabel.setVisible(true);                                            
                                                                                                    
                                                        moransIaltResFun = new JComboBox();
                                                        spatialErrorCorrPanel.add(moransIaltResFun, new AnchorConstraint(800, 431, 804, 400,
                                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        moransIaltResFun.setModel(moransIaltResFunList);
                                                        moransIaltResFun.setFont(font12);
                                                        moransIaltResFun.setPreferredSize(new java.awt.Dimension(140, 21));
                                                        moransIaltResFun.addActionListener(this);
                                                        moransIaltResFun.setVisible(true);
                                                } 

					}    
                                        
                                        
                                        
                                        
					{
						optionsPanel = new JPanel();
						getContentPane().add(optionsPanel);
						optionsPanel.setBounds(12, 390, 358, 230);
						optionsPanel.setLayout(thisLayout);
						optionsPanel.setBorder(BorderFactory.createTitledBorder("Other diagnostic statistics and tests"));
                                                optionsPanel.setFont(font12);
                                               {
							paramCorr = new JCheckBox();
							optionsPanel.add(paramCorr);
							paramCorr.setText("Parameter Correlation Matrix");
                                                        paramCorr.setFont(font12);
							paramCorr.setBounds(17, 20, 400, 19);
						}
                                                
                                                {
							vif = new JCheckBox();
							optionsPanel.add(vif);
							vif.setText("Multicollinearity: Variance Inflation Factors");
                                                        vif.setFont(font12);
							vif.setBounds(17, 45, 400, 19);
						}
 						{
							influence = new JCheckBox();
							optionsPanel.add(influence);
							influence.setText("Influentential Observations: Influence Measures");
                                                        influence.setFont(font12);
							influence.setBounds(17, 70, 400, 19);
						}

						{
							jarqueBeraTest  = new JCheckBox();
							optionsPanel.add(jarqueBeraTest);
							jarqueBeraTest.setText("Non-Normality: Jarque-Bera Test");
                                                        jarqueBeraTest.setFont(font12);
							jarqueBeraTest.setBounds(17, 95, 400, 18);
						}
                                                {
							ramseyRESET = new JCheckBox();
							optionsPanel.add(ramseyRESET );
							ramseyRESET.setText("Model Misspecification: Ramsey's RESET Test");
                                                        ramseyRESET.setFont(font12);
							ramseyRESET.setBounds(17, 120, 400, 18);
						}
                                                { 
                                                            weakInstrumentsTestivreg = new JCheckBox();
                                                            optionsPanel.add(weakInstrumentsTestivreg);
                                                            weakInstrumentsTestivreg.setText("Weak instruments test");
                                                            weakInstrumentsTestivreg.setFont(font12);
                                                            weakInstrumentsTestivreg.setBounds(17, 145, 400, 18);
                                                            weakInstrumentsTestivreg.setVisible(false);
                                                           /* hausmanTest.setEnabled(false);*/
                                                 }
                                                                                                { 
                                                            hausmanTestivreg = new JCheckBox();
                                                            optionsPanel.add(hausmanTestivreg);
                                                            hausmanTestivreg.setText("Wu-Hausman Test of OLS-estimates consistency");
                                                            hausmanTestivreg.setFont(font12);
                                                            hausmanTestivreg.setBounds(17, 170, 400, 18);
                                                            hausmanTestivreg.setVisible(false);
                                                 }
                                                { 
                                                            sarganTestivreg = new JCheckBox();
                                                            optionsPanel.add(sarganTestivreg);
                                                            sarganTestivreg.setText("Sargan test of overidentification");
                                                            sarganTestivreg.setFont(font12);
                                                            sarganTestivreg.setBounds(17, 195, 400, 18);
                                                            sarganTestivreg.setVisible(false);
                                                 }

                                                {
                                                
							classificationTable = new JCheckBox();
							optionsPanel.add(classificationTable );
							classificationTable.setText("Expectation-Prediction (Classification) Table");
                                                        classificationTable.setFont(font12);
							classificationTable.setBounds(17, 145, 400, 18);
                                                        classificationTable.setVisible(false);

					                classTableCutoffLabel = new JLabel();
					                optionsPanel.add(classTableCutoffLabel, new AnchorConstraint(800, 931, 805, 500,  
							                       AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							                       AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                        classTableCutoffLabel.setText("Cutoff:");
                                                        classTableCutoffLabel.setFont(font12);
					                classTableCutoffLabel.setPreferredSize(new java.awt.Dimension(400, 30));
                                                        classTableCutoffLabel.setVisible(false);
                                                        
					                classTableCutoff = new JComboBox();
					                optionsPanel.add(classTableCutoff, new AnchorConstraint(800, 931, 805, 710,
							          AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							          AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					                classTableCutoff.setModel(classTableCutoffs);
                                                        classTableCutoff.setFont(font12);
  					                classTableCutoff.setPreferredSize(new java.awt.Dimension(60, 31));
					                classTableCutoff.addActionListener(this);
                                                        classTableCutoff.setVisible(false);
				}


					}
				}
			}
			this.setTitle("Diagnostic Statistics and Tests");
                        this.setFont(font12);
			this.setSize(432, 700);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
                model.efeaetext.breuschPaganTest=breuschPaganTest.isSelected();
                model.efeaetext.whiteTest=whiteTest.isSelected();
                model.efeaetext.durbinWatsonTest=durbinWatsonTest.isSelected();
                model.efeaetext.BreuschGodfreyTest=BreuschGodfreyTest.isSelected();
                model.efeaetext.ljungBoxTest=ljungBoxTest.isSelected();
                model.efeaetext.paramCorr=paramCorr.isSelected();
                model.efeaetext.vif=vif.isSelected();
                model.efeaetext.influence=influence.isSelected();
                model.efeaetext.jarqueBeraTest=jarqueBeraTest.isSelected();
                model.efeaetext.ramseyRESET=ramseyRESET.isSelected();
                model.efeaetext.weakInstrumentsTestivreg=weakInstrumentsTestivreg.isSelected();
                model.efeaetext.sarganTestivreg=sarganTestivreg.isSelected();
                model.efeaetext.hausmanTestivreg=hausmanTestivreg.isSelected();
                model.efeaetext.classificationTable=classificationTable.isSelected();
                model.efeaetext.classTableCutoff = classTableCutoff.getSelectedItem().toString();
                model.efeaetext.LMSpatialDep = LMSpatialDep.isSelected();
                model.efeaetext.pAdjustMethod = pAdjustMethod.getSelectedItem().toString();
                model.efeaetext.moransIorIitest = MoransIorIiTest.isSelected();
                model.efeaetext.moransITestType = moransITestType.getSelectedItem().toString();
                model.efeaetext.moransIaltHypoth = moransIaltHypoth.getSelectedItem().toString();
                model.efeaetext.moransIaltResFun = moransIaltResFun.getSelectedItem().toString();
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                
                if (model.method.equals("ivreg")) {                   
                   weakInstrumentsTestivreg.setVisible(true);
                   sarganTestivreg.setVisible(true);
                   hausmanTestivreg.setVisible(true);
                }
                if (model.EstimationMethod.equals("Binary Logit")||model.EstimationMethod.equals("Binary Probit")){
                    classificationTable.setVisible(true);
                    classTableCutoffLabel.setVisible(true);
                    classTableCutoff.setVisible(true);
                }
                
                
                model.efeaetext.breuschPaganTest = false;
                model.efeaetext.whiteTest = false;
                model.efeaetext.durbinWatsonTest = false;
                model.efeaetext.BreuschGodfreyTest = false;
                model.efeaetext.ljungBoxTest = false;
                model.efeaetext.paramCorr = false;
                model.efeaetext.vif = false;
                model.efeaetext.influence = false;
                model.efeaetext.jarqueBeraTest = false;
                model.efeaetext.ramseyRESET = false;
                model.efeaetext.weakInstrumentsTestivreg = false;
                model.efeaetext.sarganTestivreg = false;
                model.efeaetext.hausmanTestivreg = false;
                model.efeaetext.classificationTable = false;
                model.efeGLMOptions.confIntervals = false;
                model.efeaetext.anova = false;
                classTableCutoff.setSelectedItem(model.efeaetext.classTableCutoff);
                moransITestType.setSelectedItem(model.efeaetext.moransITestType);
                moransIaltHypoth.setSelectedItem(model.efeaetext.moransIaltHypoth);
                moransIaltResFun.setSelectedItem(model.efeaetext.moransIaltResFun);
                
                
                if ((model.dependentVar.contains("lag("))||(model.dependentVar.contains("diff("))
                   ||(model.VariablesList.contains("lag("))||(model.VariablesList.contains("diff(")))
                    whiteTest.setEnabled(false);
                        
                
                if (model.listwString.isEmpty()||(!(model.method.equals("lm")||model.method.equals("ivreg")))) { 
                    spatialErrorCorrPanel.setEnabled(false);
                    LMSpatialDep.setEnabled(false);
                    pAdjustMethodLabel.setEnabled(false);
                    pAdjustMethod.setEnabled(false);
                    MoransIorIiTest.setEnabled(false);
                    moransITestypeLabel.setEnabled(false);
                    moransITestType.setEnabled(false);
                    moransIaltHypothLabel.setEnabled(false);
                    moransIaltHypoth.setEnabled(false);
                    moransIaltResFunLabel.setEnabled(false);
                    moransIaltResFun.setEnabled(false);
                }
               if (model.method.equals("tobit")){
                    heteroscPanel.setEnabled(false);
                    breuschPaganTest.setEnabled(false);
                    whiteTest.setEnabled(false);
                    durbinWatsonTest.setEnabled(false);
                    BreuschGodfreyTest .setEnabled(false);
                    vif.setEnabled(false);
                    influence.setEnabled(false);
                    jarqueBeraTest.setEnabled(true);
                    ramseyRESET.setEnabled(false);
               }  
                paramCorr.setSelected(model.efeaetext.paramCorr);
                vif.setSelected(model.efeaetext.vif);
                influence.setSelected(model.efeaetext.influence);
                ramseyRESET.setSelected(model.efeaetext.ramseyRESET);                
                weakInstrumentsTestivreg.setSelected(model.efeaetext.weakInstrumentsTestivreg);
                sarganTestivreg.setSelected(model.efeaetext.sarganTestivreg);
                hausmanTestivreg.setSelected(model.efeaetext.hausmanTestivreg);
                classificationTable.setSelected(model.efeaetext.classificationTable);
                
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