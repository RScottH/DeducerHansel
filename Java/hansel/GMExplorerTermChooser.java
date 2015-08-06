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
      GLMExplorerPlots.java and ModelBuilder.java, found in the Deducer package
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
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




public class GMExplorerTermChooser extends javax.swing.JDialog implements ActionListener{
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
        protected JPanel impulsePanel;
        protected JScrollPane impulseScroller;
        protected JList impulseList;
        protected JPanel termsPanel;
        protected JScrollPane termsScroller;
        protected JList termsList;
        protected JPanel termsPanel2;
        protected JScrollPane termsScroller2;
        protected JList termsList2;
        protected JLabel ActionWithHiglightLabel;
        protected JLabel ActionWithHiglightLabel2;
        protected JLabel ActionWithHiglightLabel3;
        protected JLabel ActionWithHiglightLabelVARJo;
        protected JLabel ActionWithHiglightLabelOLSforEG;
        protected JLabel ActionWithHiglightLabelExportPoOrEG1;
        protected JLabel ActionWithHiglightLabelExportPoOrEG2;
        
        
	protected JCheckBox scaled;
        protected JCheckBox residualsCheckBox;
        protected JCheckBox predictionsCheckBox;
	protected JPanel optionsPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
        protected ButtonGroupWidget diffOrLevelJoExport;
        protected ButtonGroupWidget diffOrLevelEG;
        protected ButtonGroupWidget diffOrLevelJo;
        protected ButtonGroupWidget varOrCointRel;
	protected GMModel  model;
        protected String givenCmd;

	public GMExplorerTermChooser(JFrame frame,GMModel mod,RModel rmod, String cmd) {
		super(frame);
                givenCmd = cmd;
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
					okayCancel.setBounds(205, 140, 207, 36);
                                        okayCancel.setFont(font12);
                                }        
                               	{
					termsPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					termsPanel.setLayout(varPanelLayout);
					getContentPane().add(termsPanel, new AnchorConstraint(11, 375, 725, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					termsPanel.setBorder(BorderFactory.createTitledBorder("Associated variable(highlight)"));
					termsPanel.setFont(font12);
                                        termsPanel.setPreferredSize(new java.awt.Dimension(215, 130));
					{
						termsScroller = new JScrollPane();
						termsPanel.add(termsScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							termsList = new JList();
							termsScroller.setViewportView(termsList);
							termsList.setModel(varListModel);
                                                        termsList.setFont(font12);
						}
					}
                                }
                                {
					termsPanel2 = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					termsPanel2.setLayout(varPanelLayout);
					getContentPane().add(termsPanel2, new AnchorConstraint(11, 375, 725, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					termsPanel2.setBorder(BorderFactory.createTitledBorder("Cross-Section Name, level, or category "));
					termsPanel2.setPreferredSize(new java.awt.Dimension(215, 130));
                                        termsPanel2.setFont(font12);
					{
						termsScroller2 = new JScrollPane();
						termsPanel2.add(termsScroller2, BorderLayout.CENTER);
						{
							ListModel levelListModel = 
								new DefaultListModel();
							termsList2 = new JList();
							termsScroller2.setViewportView(termsList2);
							termsList2.setModel(levelListModel);
                                                        termsList2.setFont(font12);
						}
					}
                                }
                                
                               {
                                        ActionWithHiglightLabel = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabel, new AnchorConstraint(21, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabel.setText("Highlighted variable is the variable for which residuals sent.");
                                        ActionWithHiglightLabel.setFont(font12);
                                        ActionWithHiglightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                                        ActionWithHiglightLabel.setPreferredSize(new java.awt.Dimension(350, 30));
                               }
                                {
                                        ActionWithHiglightLabel2 = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabel2, new AnchorConstraint(21, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabel2.setText("Highlighted variable is the left-side variable in model to be sent.");
                                        ActionWithHiglightLabel2.setFont(font12);
                                        ActionWithHiglightLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
                                        ActionWithHiglightLabel2.setPreferredSize(new java.awt.Dimension(350, 30));
                               }
                               {
                                        ActionWithHiglightLabelVARJo = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabelVARJo, new AnchorConstraint(21, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabelVARJo.setText("Highlighted variable is the variable for which data type below is sent.");
                                        ActionWithHiglightLabelVARJo.setFont(font12);
                                        ActionWithHiglightLabelVARJo.setHorizontalAlignment(SwingConstants.RIGHT);
                                        ActionWithHiglightLabelVARJo.setPreferredSize(new java.awt.Dimension(350, 30));
                               } 
                              
                                 {
                                        residualsCheckBox = new JCheckBox();
                                        getContentPane().add(residualsCheckBox , new AnchorConstraint(161, 981, 805, 430, 
                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        residualsCheckBox.setText("residuals");
                                        residualsCheckBox.setFont(font12);
                                        residualsCheckBox.setPreferredSize(new java.awt.Dimension(115, 18));
                                 }
                                {
                                        predictionsCheckBox = new JCheckBox();
                                        getContentPane().add(predictionsCheckBox , new AnchorConstraint(261, 981, 805, 430, 
                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        predictionsCheckBox.setText("predicted values");
                                        predictionsCheckBox.setFont(font12);
                                        predictionsCheckBox.setPreferredSize(new java.awt.Dimension(115, 18));
                                 }
                               
                                {
                                        ActionWithHiglightLabelOLSforEG = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabelOLSforEG , new AnchorConstraint(21, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabelOLSforEG.setText("The cointegrating regression to export is the one using the highlighted");
                                        ActionWithHiglightLabelOLSforEG.setFont(font12);
                                        ActionWithHiglightLabelOLSforEG.setHorizontalAlignment(SwingConstants.LEFT);
                                        ActionWithHiglightLabelOLSforEG.setPreferredSize(new java.awt.Dimension(350, 30));
                               }
                               {
                                        ActionWithHiglightLabelExportPoOrEG1 = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabelExportPoOrEG1, new AnchorConstraint(21, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabelExportPoOrEG1.setText("Data to export is the cointegrating relation using the highlighted");
                                        ActionWithHiglightLabelExportPoOrEG1.setFont(font12);
                                        ActionWithHiglightLabelExportPoOrEG1.setHorizontalAlignment(SwingConstants.LEFT);
                                        ActionWithHiglightLabelExportPoOrEG1.setPreferredSize(new java.awt.Dimension(350, 30));
                               }
                                      {
                                        ActionWithHiglightLabelExportPoOrEG2 = new JLabel();
                                        getContentPane().add(ActionWithHiglightLabelExportPoOrEG2, new AnchorConstraint(101, 981, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ActionWithHiglightLabelExportPoOrEG2.setText("variable on the left side");
                                        ActionWithHiglightLabelExportPoOrEG2.setFont(font12);
                                        ActionWithHiglightLabelExportPoOrEG2.setHorizontalAlignment(SwingConstants.LEFT);
                                        ActionWithHiglightLabelExportPoOrEG2.setPreferredSize(new java.awt.Dimension(350, 30));
                               }
                                 {
                                       diffOrLevelEG = new ButtonGroupWidget("Data to be explored, based on the highlighted variable",
						new String[]{"the variable itself",
                                                             "the cointegrating relation using the variable on the left-side"});
                                        diffOrLevelEG.setSelected("the cointegrating relation using the variable on the left-side");
                                        diffOrLevelEG.setFont(font12);
                                        getContentPane().add(diffOrLevelEG, new AnchorConstraint(11, 931, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        diffOrLevelEG.setPreferredSize(new java.awt.Dimension(360, 80));
                                }
                                 
                                 {
                                       diffOrLevelJo = new ButtonGroupWidget("Data to be explored, based on the highlighted variable",
						new String[]{"the variable itself",
                                                             "residuals associated with the differenced variable",
                                                             "residuals associated with the variable's lagged levels"});
                                        diffOrLevelJo.setSelected("residuals associated with the differenced variable");
                                        diffOrLevelJo.setFont(font12);
                                        getContentPane().add(diffOrLevelJo, new AnchorConstraint(11, 931, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        diffOrLevelJo.setPreferredSize(new java.awt.Dimension(360, 100));
                                }
                                {
                                       diffOrLevelJoExport = new ButtonGroupWidget("Data to be exported, based on the highlighted variable",
						new String[]{"residuals associated with the differenced variable",
                                                             "residuals associated with the variable's lagged levels"});
                                                  diffOrLevelJoExport.setFont(font12);
                                        getContentPane().add(diffOrLevelJoExport, new AnchorConstraint(11, 931, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        diffOrLevelJoExport.setPreferredSize(new java.awt.Dimension(360, 80));
                                }  

                                 {
                                       varOrCointRel = new ButtonGroupWidget("Data to be explored, based on highlighted variable(s)",
						new String[]{"the variable itself",
                                                             "the cointegrating relation using the variable on the left-side"});
                                        varOrCointRel.setSelected("the cointegrating relation using the variable on the left-side");
                                        varOrCointRel.setFont(font12);
                                        getContentPane().add(varOrCointRel, new AnchorConstraint(11, 931, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        varOrCointRel.setPreferredSize(new java.awt.Dimension(360, 80));
                                }
			}
                        if (givenCmd.equals("export"))
		   	   this.setTitle("Choice of data to export");
                        else
                           this.setTitle("Choice of data to send for further exploration"); 
                        this.setFont(font12);
			this.setSize(632, 230);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){  
            model.irfOptions.responseSelection = termsList.getSelectedIndices();
            if (model.method.equals("ca.jo")||model.method.equals("cajolst")) {
                if (givenCmd.equals("export")){
                    model.hanselExport.residForDiff = diffOrLevelJoExport.getSelectedItemText().equals("residuals associated with the differenced variable");
                   model.hanselExport.residForLag = diffOrLevelJoExport.getSelectedItemText().equals("residuals associated with the variable's lagged levels");
                } else
                    model.efeglmextra.diffOrLevelJo = diffOrLevelJo.getSelectedItemText();
            } else if (model.method.equals("ca.po"))
                   model.efeglmextra.varOrCointRelpo = varOrCointRel.getSelectedItemText();
            else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger"))
                model.efeglmextra.diffOrLevelEG = diffOrLevelEG.getSelectedItemText();
            model.hanselExport.resid = residualsCheckBox.isSelected();
            model.hanselExport.pred = predictionsCheckBox.isSelected();
            if (givenCmd.equals("export")) {
               model.hanselExport.okay=true;
               if (model.method.equals("ca.po")||model.EstimationMethod.equals("Cointegration Test - Engle & Granger"))
                  model.hanselExport.cointRel = true;               
	    }
        }
        
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.hanselExport.okay=false;
                termsPanel.setVisible(true);
                termsPanel2.setVisible(false);
                diffOrLevelEG.setVisible(false);
                diffOrLevelJo.setVisible(false);
                varOrCointRel.setVisible(false);
                ActionWithHiglightLabelVARJo.setVisible(false);
                residualsCheckBox.setVisible(false);
                predictionsCheckBox.setVisible(false);
                ActionWithHiglightLabel.setVisible(false);
                ActionWithHiglightLabel2.setVisible(false);
                ActionWithHiglightLabelOLSforEG.setVisible(false);
                ActionWithHiglightLabelExportPoOrEG1.setVisible(false);
                ActionWithHiglightLabelExportPoOrEG2.setVisible(false);
                diffOrLevelJoExport.setVisible(false); 
                if (model.panelModel){
                termsList2.setModel(model.modelPanelCSLevelsList);
                } 
                else{
                termsList.setModel(model.termsFinalStage);
                }model.efeaeplots.moreDiagnosticPlots = "None";
      
                if ((model.method.equals("VAR")||model.method.equals("vec2var"))&(givenCmd.equals("Residuals -> Time Series Explorer")||givenCmd.equals("plotsvsObsOrTime")||givenCmd.equals("export"))){
                        if(givenCmd.equals("export")) {
                            ActionWithHiglightLabelVARJo.setVisible(true);
                            residualsCheckBox.setVisible(true);
                            predictionsCheckBox.setVisible(true);   
                        }else 
                        ActionWithHiglightLabel.setVisible(true);
                        
                } else if (model.method.equals("ca.jo")||model.method.equals("cajolst")){
                        if(givenCmd.equals("export")) {
                            diffOrLevelJoExport.setVisible(true);
                        }else {
                           diffOrLevelJo.setVisible(true);
                        }    
                 } else if (model.method.equals("ca.po")){
                     if(givenCmd.equals("export")) {
                     ActionWithHiglightLabelExportPoOrEG1.setVisible(true);
                     ActionWithHiglightLabelExportPoOrEG2.setVisible(true);
                     } else {
                          varOrCointRel.setVisible(true);
                     }
                } else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger") ){
                     if(givenCmd.equals("export")) {
                     ActionWithHiglightLabelExportPoOrEG1.setVisible(true);
                     ActionWithHiglightLabelExportPoOrEG2.setVisible(true);
                     } else if (givenCmd.equals("Output -> Time Series Explorer")) {
                        diffOrLevelEG.setVisible(true);
                     } else if (givenCmd.equals("Model -> OLS Explorer")) {
                        ActionWithHiglightLabelOLSforEG.setVisible(true);
                        ActionWithHiglightLabelExportPoOrEG2.setVisible(true);  
                     } 
                }else if (model.panelModel&(givenCmd.equals("Residuals -> Time Series Explorer")||givenCmd.equals("plotsvsObsOrTime")||givenCmd.equals("export"))){
                        if(givenCmd.equals("export")) {
                            termsPanel.setVisible(false);
                            residualsCheckBox.setVisible(true);
                            predictionsCheckBox.setVisible(true);
                        } else {
                            termsPanel.setVisible(false);
                            termsPanel2.setVisible(true);
                            ActionWithHiglightLabel.setVisible(true);
                        }
                }else if (model.method.equals("tobit")&givenCmd.equals("export")){
                            termsPanel.setVisible(false);
                            residualsCheckBox.setVisible(true);
                            predictionsCheckBox.setVisible(true);              
                } else if (givenCmd.equals("Model -> OLS Explorer")) {
                        ActionWithHiglightLabel2.setVisible(true);
                } 
                

        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                       if (model.panelModel){
                           Object[] newTerm = termsList2.getSelectedValuesList().toArray();
                            if (newTerm==null)
                             {
                                    JOptionPane.showMessageDialog(null, "Please select at least one name, level, or category");
                                    return;
                             }
                            model.efeglmextra.chosenTerms = new String[newTerm.length];
                            for(int i=0;i<(newTerm.length);i++){
                                model.efeglmextra.chosenTerms[i] = (String) newTerm[i];
                            }
                       }
                       else {
                              Object[] newTerm = termsList.getSelectedValuesList().toArray();
                         if (newTerm==null || !(newTerm.length==1))
                            if (newTerm==null)
                             {
                                    JOptionPane.showMessageDialog(null, "Please select at least one variable");
                                    return;
                             }
                            model.efeglmextra.chosenTerms = new String[newTerm.length];
                            for(int i=0;i<(newTerm.length);i++){
                                model.efeglmextra.chosenTerms[i] = (String) newTerm[i];
                            }
                            
                       }
                       
                        updateModel();
			this.dispose();
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