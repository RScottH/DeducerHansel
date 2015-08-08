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
      GLMExplorerPlots.java, found in the Deducer package
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.
 */
package hansel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;

import org.rosuda.deducer.models.RModel;




public class GMExplorerTestCoeffsMain extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel diagPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
	protected JCheckBox vif;
      	protected JCheckBox influence;
       	protected JCheckBox resvFitted;
        protected JCheckBox termPlots;
        protected JCheckBox ramseyRESET;
        protected JButton coeffTestsGUI;
        protected JButton confIntervals;
        protected JButton postHocGUI;
	protected JList terms;
	protected JPanel meansPanel;
	protected JPanel okayCancel;
	protected JCheckBox confInt;
	protected JCheckBox scaled;
	protected JPanel optionsPanel;
        protected JPanel showModelPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
	protected JScrollPane termScroller;
        protected JPanel coefTestsManual;
        protected JScrollPane coefTestsManualScroller;
        protected JTextArea coefTestsManualText;
        protected JPanel coefTestsManualcajo;
        protected JScrollPane coefTestsManualScrollercajo;
        protected JTextArea coefTestsManualTextcajo;
        protected JPanel coefTestsManualVAR;
        protected JScrollPane coefTestsManualScrollerVAR;
        protected JTextArea coefTestsManualTextVAR;
        protected JPanel coefTestsManualtsls;
        protected JScrollPane coefTestsManualScrollertsls;
        protected JTextArea coefTestsManualTexttsls;
        protected JPanel coefTestsManualSpatial;
        protected JScrollPane coefTestsManualScrollerSpatial;
        protected JTextArea coefTestsManualTextSpatial;
	protected GMModel  model;
        protected JPanel modelFormulaParamaterized;
        protected JScrollPane modelFormulaScroller;
        protected JTextArea modelFormulaText;
        protected JPanel modelFormulaParamaterizedcajo;
        protected JScrollPane modelFormulaScrollercajo;
        protected JTextArea modelFormulaTextcajo;
        protected JPanel modelFormulaVAR;
        protected JScrollPane modelFormulaScrollerVAR;
        protected JTextArea modelFormulaTextVAR;
        protected JPanel modelFormulatsls;
        protected JScrollPane modelFormulaScrollertsls;
        protected JTextArea modelFormulaTexttsls;
        protected JLabel alphaRestrictionsLabel1;
        protected JLabel alphaRestrictionsLabel2;
        protected JLabel alphaRestrictionsLabel3;

	
	public GMExplorerTestCoeffsMain(JFrame frame,GMModel mod,RModel rmod) {
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
    
                                             {
                                                modelFormulaParamaterized = new JPanel();
                                                getContentPane().add(modelFormulaParamaterized, new AnchorConstraint(12, 989, 240, 12,
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                                                BorderLayout modelFormulaLayout = new BorderLayout();
						modelFormulaParamaterized.setLayout(modelFormulaLayout);
                                                modelFormulaParamaterized.setBorder(BorderFactory.createTitledBorder("Theoretical Model with Coefficients"));
                                                modelFormulaParamaterized.setFont(font12);
                                                modelFormulaParamaterized.setPreferredSize(new java.awt.Dimension(200, 232));
                                             }                                          
                                                {
                                                    modelFormulaScroller = new JScrollPane();
                                                    modelFormulaParamaterized.add(modelFormulaScroller, BorderLayout.CENTER);
							{
                                                        	modelFormulaText = new JTextArea();
								modelFormulaScroller.setViewportView(modelFormulaText);
								modelFormulaText.setFont(new java.awt.Font("Monospaced",0,11));
								modelFormulaText.setEditable(false);
                                                               
                                                                
							}
                                                }
                                            {
                                                modelFormulatsls = new JPanel();
                                                getContentPane().add(modelFormulatsls , new AnchorConstraint(12, 989, 440, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                                                BorderLayout modelFormulaLayout = new BorderLayout();
						modelFormulatsls.setLayout(modelFormulaLayout);
                                                modelFormulatsls.setBorder(BorderFactory.createTitledBorder("R formula for unrestricted model (with coefficients supressed as usual) and list of insturments "));
                                                modelFormulatsls.setPreferredSize(new java.awt.Dimension(200, 232));
                                                modelFormulatsls.setFont(font12);
                                             }                                          
                                                {
                                                    modelFormulaScrollertsls  = new JScrollPane();
                                                    modelFormulatsls.add(modelFormulaScrollertsls , BorderLayout.CENTER);
							{
                                                        	modelFormulaTexttsls  = new JTextArea();
								modelFormulaScrollertsls.setViewportView(modelFormulaTexttsls );
								modelFormulaTexttsls .setFont(new java.awt.Font("Monospaced",0,11));
								modelFormulaTexttsls .setEditable(false);
                                                               

							}
                                                }    
                                                
                                                
                                            {
                                                modelFormulaVAR = new JPanel();
                                                getContentPane().add(modelFormulaVAR , new AnchorConstraint(12, 989, 440, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                                                BorderLayout modelFormulaLayout = new BorderLayout();
						modelFormulaVAR.setLayout(modelFormulaLayout);
                                                modelFormulaVAR.setBorder(BorderFactory.createTitledBorder("Equations in unrestricted model with coefficients supressed"));
                                                modelFormulaVAR.setFont(font12);
                                                modelFormulaVAR.setPreferredSize(new java.awt.Dimension(200, 232));
                                             }                                          
                                                {
                                                    modelFormulaScrollerVAR  = new JScrollPane();
                                                    modelFormulaVAR.add(modelFormulaScrollerVAR , BorderLayout.CENTER);
							{
                                                        	modelFormulaTextVAR  = new JTextArea();
								modelFormulaScrollerVAR.setViewportView(modelFormulaTextVAR );
								modelFormulaTextVAR.setFont(new java.awt.Font("Monospaced",0,11));
								modelFormulaTextVAR.setEditable(false);

							}
                                                }    
 
                                                
                                             {
                                                modelFormulaParamaterizedcajo = new JPanel();
                                                getContentPane().add(modelFormulaParamaterizedcajo , new AnchorConstraint(12, 989, 340, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                                                BorderLayout modelFormulaLayout = new BorderLayout();
						modelFormulaParamaterizedcajo.setLayout(modelFormulaLayout);
                                                modelFormulaParamaterizedcajo.setBorder(BorderFactory.createTitledBorder("Model of each cointegrating relation, CR"));
                                                modelFormulaParamaterizedcajo.setFont(font12);
                                                modelFormulaParamaterizedcajo.setPreferredSize(new java.awt.Dimension(200, 232));
                                             }                                          
                                                {
                                                    modelFormulaScrollercajo  = new JScrollPane();
                                                    modelFormulaParamaterizedcajo .add(modelFormulaScrollercajo , BorderLayout.CENTER);
							{
                                                        	modelFormulaTextcajo  = new JTextArea();
								modelFormulaScrollercajo.setViewportView(modelFormulaTextcajo );
								modelFormulaTextcajo.setFont(new java.awt.Font("Monospaced",0,11));
								modelFormulaTextcajo.setEditable(false);

							}
                                                }                                                     
                                        
                                        }
                                    
                                    
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(455, 260, 217, 36);
                                        okayCancel.setFont(font12);
                                                
					{
                                                
                                                {
                                                alphaRestrictionsLabel1 = new JLabel();
                                                getContentPane().add(alphaRestrictionsLabel1, new AnchorConstraint(460, 950, 105, 50, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                alphaRestrictionsLabel1.setText("Note: a(i) represents the effect of each cointegrating relation on the ith right-side variable above (excluding any deterministic one),");
                                                alphaRestrictionsLabel1.setFont(font12);
                                                alphaRestrictionsLabel1.setPreferredSize(new java.awt.Dimension(650, 20));
                                                }
                                                {
                                                alphaRestrictionsLabel2 = new JLabel();
                                                getContentPane().add(alphaRestrictionsLabel2, new AnchorConstraint(520, 950, 105, 50, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                alphaRestrictionsLabel2.setText("where a(i) may be different for different cointegrating relations. A test of exogeneity of a variable may be perfomed by setting the");
                                                alphaRestrictionsLabel2.setFont(font12);
                                                alphaRestrictionsLabel2.setPreferredSize(new java.awt.Dimension(650, 20));
                                                }
                                                {
                                                alphaRestrictionsLabel3 = new JLabel();
                                                getContentPane().add(alphaRestrictionsLabel3, new AnchorConstraint(580, 950, 105, 50, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                alphaRestrictionsLabel3.setText("variable's associated a(i) term equal to zero. ");
                                                alphaRestrictionsLabel3.setFont(font12);
                                                alphaRestrictionsLabel3.setPreferredSize(new java.awt.Dimension(650, 20));
                                                }
                                                 {
						coeffTestsGUI = new JButton();
                                                getContentPane().add(coeffTestsGUI, new AnchorConstraint(164, 390, 179, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                coeffTestsGUI.setText("Set Hypotheses through Clicking");
                                                coeffTestsGUI.setFont(font12);
						coeffTestsGUI.setPreferredSize(new java.awt.Dimension(200, 22));
                                                coeffTestsGUI.addActionListener(this);                                            
						}
                                                 {
						postHocGUI = new JButton();
                                                getContentPane().add(postHocGUI, new AnchorConstraint(164, 920, 179, 312,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                postHocGUI.setText("Test Factor-variable Dummy Coefficient Equalities (Post Hoc)");
                                                postHocGUI.setFont(font12);
						postHocGUI.setPreferredSize(new java.awt.Dimension(344, 22));
                                                postHocGUI.addActionListener(this);                                            
						}
                                                {
                                                coefTestsManual = new JPanel();
                                                getContentPane().add(coefTestsManual, new AnchorConstraint(200, 989, 240, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                BorderLayout coefTestsManualLayout = new BorderLayout();
						coefTestsManual.setLayout(coefTestsManualLayout);
                                                coefTestsManual.setFont(font12);
                                                coefTestsManual.setBorder(BorderFactory.createTitledBorder("Manual alternative: provide restrictions, i.e b(1)=1, b(1)=b(2), or 3*b(1)+b(2)=0; separate restrictions with comma"));
                                                coefTestsManual.setPreferredSize(new java.awt.Dimension(710, 52));
                                                }                                           
                                                {
                                                    coefTestsManualScroller = new JScrollPane();
                                                    coefTestsManual.add(coefTestsManualScroller, BorderLayout.CENTER);
							{
                                                        	coefTestsManualText = new JTextArea();
								coefTestsManualScroller.setViewportView(coefTestsManualText);
								coefTestsManualText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                coefTestsManualText.setFont(font12);
								coefTestsManualText.setEditable(true);
							}
						}   
                                                
                                                {
                                                coefTestsManualtsls = new JPanel();
                                                getContentPane().add(coefTestsManualtsls , new AnchorConstraint(200, 920, 200, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                BorderLayout coefTestsManualLayout = new BorderLayout();
						coefTestsManualtsls.setLayout(coefTestsManualLayout);
                                                coefTestsManualtsls.setFont(font12);
                                                coefTestsManualtsls.setBorder(BorderFactory.createTitledBorder("The above R formula is repeated here. Edit the right-side for the restricted model."));
                                                coefTestsManualtsls.setPreferredSize(new java.awt.Dimension(600, 52));
                                                }                                           
                                                {
                                                    coefTestsManualScrollertsls  = new JScrollPane();
                                                    coefTestsManualtsls.add(coefTestsManualScrollertsls , BorderLayout.CENTER);
							{
                                                        	coefTestsManualTexttsls  = new JTextArea();
								coefTestsManualScrollertsls .setViewportView(coefTestsManualTexttsls );
								coefTestsManualTexttsls.setFont(new java.awt.Font("Monospaced",0,11));
                                                                coefTestsManualTexttsls.setFont(font12);
								coefTestsManualTexttsls.setEditable(true);
							}
						}   
                                                                                           
          
                                                {
                                                coefTestsManualVAR = new JPanel();
                                                getContentPane().add(coefTestsManualVAR, new AnchorConstraint(450, 920, 200, 100,
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                BorderLayout coefTestsManualLayoutcajo = new BorderLayout();
						coefTestsManualVAR.setLayout(coefTestsManualLayoutcajo);
                                                coefTestsManualVAR.setBorder(BorderFactory.createTitledBorder("To restrict a variable's coefficient to 0, replace the \"1\" corresponding positionally to that variable above with a \"0\"."));
                                                coefTestsManualVAR.setFont(font12);
                                                coefTestsManualVAR.setPreferredSize(new java.awt.Dimension(600, 120));
                                                }                                           
                                                {
                                                    coefTestsManualScrollerVAR = new JScrollPane();
                                                    coefTestsManualVAR.add(coefTestsManualScrollerVAR, BorderLayout.CENTER);
							{
                                                        	coefTestsManualTextVAR = new JTextArea();
								coefTestsManualScrollerVAR.setViewportView(coefTestsManualTextVAR);
								coefTestsManualTextVAR.setFont(new java.awt.Font("Monospaced",0,11));
								coefTestsManualTextVAR.setEditable(true);
							}
						}  
                                                {
                                                coefTestsManualcajo = new JPanel();
                                                getContentPane().add(coefTestsManualcajo, new AnchorConstraint(200, 920, 200, 12,
								AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                BorderLayout coefTestsManualLayoutcajo = new BorderLayout();
						coefTestsManualcajo.setLayout(coefTestsManualLayoutcajo);
                                                coefTestsManualcajo.setBorder(BorderFactory.createTitledBorder("Provide restrictions, e.g. b(1) = b(2), 3*b(2) + 2*b(3) = 0, a(1) = 0, a(2) + a(3)=0; separate restrictions with comma"));
                                                coefTestsManualcajo.setFont(font12);
                                                coefTestsManualcajo.setPreferredSize(new java.awt.Dimension(600, 52));
                                                }                                           
                                                {
                                                    coefTestsManualScrollercajo = new JScrollPane();
                                                    coefTestsManualcajo.add(coefTestsManualScrollercajo, BorderLayout.CENTER);
							{
                                                        	coefTestsManualTextcajo = new JTextArea();
								coefTestsManualScrollercajo.setViewportView(coefTestsManualTextcajo);
								coefTestsManualTextcajo.setFont(new java.awt.Font("Monospaced",0,11));
								coefTestsManualTextcajo.setEditable(true);
							}
						}  
					}
				}
			}
			this.setTitle("Coefficient Tests");
                        this.setFont(font12);
			this.setSize(752, 345);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){

	}
	public void setModel(GMModel mod,RModel rmod){
                model = mod;
                                                      
                model.efeGLMOptions.coeffTestsGUI=false;
                model.efeGLMOptions.postHocGUI=false;
                model.efeaetext.constraints = "";
                
                modelFormulaParamaterized.setVisible(false);
                modelFormulatsls.setVisible(false);
                modelFormulaVAR.setVisible(false);
                modelFormulaParamaterizedcajo.setVisible(false);
                
                
                coefTestsManual.setVisible(false);
                coefTestsManualtsls.setVisible(false);
                coefTestsManualVAR.setVisible(false);
                coefTestsManualcajo.setVisible(false);
                
                postHocGUI.setVisible(false);
                coeffTestsGUI.setVisible(false);
                alphaRestrictionsLabel1.setVisible(false);
                alphaRestrictionsLabel2.setVisible(false);
                alphaRestrictionsLabel3.setVisible(false);
                                
                if (model.method.equals("ivreg")){
                    modelFormulatsls.setVisible(true);
                    coefTestsManualtsls.setVisible(true);
                    modelFormulaTexttsls.setText("R formula:\n  "+model.formula+"\n\nInstruments:\n  "+
                                                 model.instruments.replace("|", "").replace("+", ","));
                    coefTestsManualTexttsls.setText(model.formula);
                }

                else if (model.method.equals("VAR")){
                    modelFormulaVAR.setVisible(true);
                    coefTestsManualVAR.setVisible(true);
                    modelFormulaTextVAR.setText(model.efeaetext.paramformula);
                    coefTestsManualTextVAR.setText(model.efeaetext.unrestrictedVARMatrix);
                }
                else if (model.method.equals("ca.jo")){
                    modelFormulaParamaterizedcajo.setVisible(true);
                    coefTestsManualcajo.setVisible(true);
                    alphaRestrictionsLabel1.setVisible(true);
                    alphaRestrictionsLabel2.setVisible(true);
                    alphaRestrictionsLabel3.setVisible(true);                    
                    modelFormulaTextcajo.setText(model.efeaetext.paramformula);
                }
                else
                {
                modelFormulaParamaterized.setVisible(true);
                coefTestsManual.setVisible(true);
                postHocGUI.setVisible(true);
                coeffTestsGUI.setVisible(true);    
                modelFormulaText.setText(model.efeaetext.paramformula);

                }    
 
                coeffTestsGUI.setSelected(model.efeGLMOptions.coeffTestsGUI);
                postHocGUI.setSelected(model.efeGLMOptions.postHocGUI);
                coefTestsManualText.setText("");
                coefTestsManualTextcajo.setText("");
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
                        model.efeGLMOptions.coeffTest = false;
			this.dispose();
		}else if((cmd=="OK")||(cmd==coefTestsManualText.getText())||(cmd==coefTestsManualTextcajo.getText())){
			/*updateModel();*/
                        model.efeGLMOptions.coeffTest = true;
                        if (model.method.equals("VAR")){                            
                          model.efeaetext.constraints =coefTestsManualTextVAR.getText();
                        } 
                        else if (model.method.equals("ivreg")){                            
                          model.efeaetext.constraints =coefTestsManualTexttsls.getText();
                        } 
                        else if (model.method.equals("ca.jo")){                            
                          model.efeaetext.constraints =coefTestsManualTextcajo.getText();
                        }
                        else {
                          model.efeaetext.constraints =coefTestsManualText.getText();
                        }
			this.dispose();
                }else if(cmd=="Set Hypotheses through Clicking"){
                    model.efeGLMOptions.coeffTest = true;
                    model.efeGLMOptions.coeffTestsGUI=true;	
		    this.dispose();
                }else if(cmd=="Test Factor-variable Dummy Coefficient Equalities (Post Hoc)"){
                    model.efeGLMOptions.coeffTest = true;
                    model.efeGLMOptions.postHocGUI=true;	
		    this.dispose();  
		}
		
	}

}