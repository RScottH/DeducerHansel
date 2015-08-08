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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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




public class NMTimeSExpSmoothingOptions extends javax.swing.JDialog implements ActionListener{
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
        
        protected JRadioButton holtWintersButton;
        protected JRadioButton hyndmanButton;
        protected ButtonGroup procedureButtons;
        protected ButtonGroupWidget deterministicComponent;
        protected ButtonGroupWidget differencing;
        protected ButtonGroupWidget lagDetermination;
        protected ButtonGroupWidget trendType;
        protected ButtonGroupWidget hwSeasonalType;
        protected ButtonGroupWidget   seasonalType;
        protected ButtonGroupWidget errorType;
        protected ButtonGroupWidget optimizationCriterion;
        protected ButtonGroupWidget infoCriterion;
        protected JLabel predictAheadLabel;
        protected JComboBox predictAhead ;
        
        
        protected JLabel alphaLabel;
        protected JComboBox alpha;
        protected static DefaultComboBoxModel alphaChoices  = new DefaultComboBoxModel(
				new String[] {"automatic","0.1", "0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1"});
        protected JLabel betaLabel;
        protected JComboBox beta;
        protected static DefaultComboBoxModel betaChoices  = new DefaultComboBoxModel(
				new String[] {"automatic","0","0.1", "0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1"});
        protected JLabel gammaLabel;
        protected JComboBox gamma;
        protected static DefaultComboBoxModel gammaChoices  = new DefaultComboBoxModel(
				new String[] {"automatic","0","0.1", "0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1"});
        
        private String givenDecomposition;
                protected static DefaultComboBoxModel arOrderlags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                                protected static DefaultComboBoxModel intOrderLags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                               protected static DefaultComboBoxModel maOrderlags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                               protected static DefaultComboBoxModel lagsforBoxTest  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                                protected static DefaultComboBoxModel predictPeriods  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
	
	public NMTimeSExpSmoothingOptions(JFrame frame,GMModel mod,RModel rmod, String specificDecomposition) {
		super(frame);
                givenDecomposition = specificDecomposition;
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
					okayCancel.setBounds(380, 420, 207, 36);
                                        okayCancel.setFont(font12);
                                        
                                 {
					holtWintersButton = new JRadioButton();
                                        getContentPane().add(holtWintersButton , new AnchorConstraint(10, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					holtWintersButton.setText("Holt-Winters");
                                        holtWintersButton.setFont(font12);
                                        holtWintersButton.addActionListener(this);
                                        
				}
                                        
                                {
					hyndmanButton = new JRadioButton();
                                        getContentPane().add(hyndmanButton , new AnchorConstraint(10, 487, 782, 301, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					hyndmanButton.setText("Hyndman");
                                        hyndmanButton.setFont(font12);
                                        hyndmanButton.addActionListener(this);
				}
                                {
                                        procedureButtons =new ButtonGroup();
                                        procedureButtons.add(holtWintersButton);
                                        procedureButtons.add(hyndmanButton);
                                        
                                }
                                       
                                 {
					alphaLabel = new JLabel();
					getContentPane().add(alphaLabel, new AnchorConstraint(101, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					alphaLabel.setText("alpha");
                                        alphaLabel.setFont(font12);
					alphaLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					alpha = new JComboBox();
					getContentPane().add(alpha , new AnchorConstraint(161, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					alpha.setModel(alphaChoices);
                                        alpha.setSelectedItem("automatic");
                                        alpha.setFont(font12);
					alpha .setPreferredSize(new java.awt.Dimension(90, 21));
					alpha .addActionListener(this);
				}  
                                {
					betaLabel = new JLabel();
					getContentPane().add(betaLabel, new AnchorConstraint(221, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					betaLabel.setText("beta (for trend component)");
                                        betaLabel.setFont(font12);
					betaLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					beta = new JComboBox();
					getContentPane().add(beta , new AnchorConstraint(271, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					beta.setModel(betaChoices);
                                        beta.setSelectedItem("automatic");
                                        beta.setFont(font12);
					beta .setPreferredSize(new java.awt.Dimension(90, 21));
					beta .addActionListener(this);
				}  
                                {
					gammaLabel = new JLabel();
					getContentPane().add(gammaLabel, new AnchorConstraint(331, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					gammaLabel.setText("gamma (for seasonal component)");
                                        gammaLabel.setFont(font12);
					gammaLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					gamma = new JComboBox();
					getContentPane().add(gamma , new AnchorConstraint(381, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					gamma.setModel(gammaChoices);
                                        gamma.setSelectedItem("automatic");
                                        gamma.setFont(font12);
					gamma .setPreferredSize(new java.awt.Dimension(90, 21));
					gamma .addActionListener(this);
				} 
                               
                                {
                                        trendType = new ButtonGroupWidget("Trend Type",
						new String[]{"None","Additive","Additive damped","Multiplicative","Automatic",
                                                             "Automatic, damping any trend"});
                                        trendType.setSelected("None");
                                        trendType.setFont(font12);
                                        getContentPane().add(trendType, new AnchorConstraint(510, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        trendType.setPreferredSize(new java.awt.Dimension(240, 180));
                                }    
                                
                                {
                                        hwSeasonalType = new ButtonGroupWidget("Seasonal Component",
						new String[]{"None","Additive","Multiplicative"});
                                        hwSeasonalType.setSelected("None");
                                        hwSeasonalType.setFont(font12);
                                        getContentPane().add(hwSeasonalType, new AnchorConstraint(111, 931, 805, 310, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        hwSeasonalType.setPreferredSize(new java.awt.Dimension(160, 140));
                                }  
                                
                                  {
                                        seasonalType = new ButtonGroupWidget("Seasonal Component",
						new String[]{"None","Additive","Multiplicative","Automatic"});
                                        seasonalType.setSelected("None");
                                        seasonalType.setFont(font12);
                                        getContentPane().add(seasonalType, new AnchorConstraint(111, 931, 805, 310, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        seasonalType.setPreferredSize(new java.awt.Dimension(160, 140));
                                }  
                               {
					predictAheadLabel = new JLabel();
					getContentPane().add(predictAheadLabel, new AnchorConstraint(106, 931, 805, 700,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					predictAheadLabel.setText("Number of Periods Predicting Ahead");
                                        predictAheadLabel.setFont(font12);
					predictAheadLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}           
                               {
					predictAhead = new JComboBox();
					getContentPane().add(predictAhead , new AnchorConstraint(156, 931, 805, 700, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					predictAhead.setModel(predictPeriods);
                                        predictAhead.setSelectedItem("0");
                                        predictAhead.setFont(font12);
					predictAhead .setPreferredSize(new java.awt.Dimension(60, 21));
					predictAhead .addActionListener(this);
				}  
                                {
                                        errorType = new ButtonGroupWidget("Error Type (for prediction intevals)",
						new String[]{"Additive","Multiplicative","Automatic"});
                                        errorType.setSelected("Additive");
                                        errorType.setFont(font12);
                                        getContentPane().add(errorType, new AnchorConstraint(220, 931, 805, 600, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        errorType.setPreferredSize(new java.awt.Dimension(240, 100));
                                }                                
                                {
                                        optimizationCriterion = new ButtonGroupWidget("Optimization Criterion",
						new String[]{"Mean Square Error","Avg MSE over 1st nsme forec hor",
                                                             "Standard deviation of residuals","Mean of absoulute residuals","Log-likelihood"});
                                        optimizationCriterion.setSelected("Log-likelihood");
                                        optimizationCriterion.setFont(font12);
                                        getContentPane().add(optimizationCriterion, new AnchorConstraint(540, 931, 805, 410, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        optimizationCriterion.setPreferredSize(new java.awt.Dimension(240, 150));
                                }             
                                
                                {
                                        infoCriterion = new ButtonGroupWidget("Info Criterion",
						new String[]{"aic","aicc","bic"});
                                        infoCriterion.setSelected("aic");
                                        infoCriterion.setFont(font12);
                                        getContentPane().add(infoCriterion, new AnchorConstraint(600, 931, 805, 800, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        infoCriterion.setPreferredSize(new java.awt.Dimension(260, 100));
                                }  
				}
			}
			this.setTitle(givenDecomposition);
                        this.setFont(font12);
			this.setSize(632, 550);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
               model.expSmoothingOptions.alpha = alpha.getSelectedItem().toString();
               model.expSmoothingOptions.beta= beta.getSelectedItem().toString();
               model.expSmoothingOptions.gamma = gamma.getSelectedItem().toString();
               model.expSmoothingOptions.predictAhead =predictAhead.getSelectedItem().toString();

               if (holtWintersButton.isSelected()){
                   model.expSmoothingOptions.procedure = "Holt-Winters";  
                   if (hwSeasonalType.getSelectedItemText()=="None")           model.expSmoothingOptions.seasonalType="N"; 
                   else if (hwSeasonalType.getSelectedItemText()=="Additive")  model.expSmoothingOptions.seasonalType="A"; 
                   else if (hwSeasonalType.getSelectedItemText()=="Multiplicative")  model.expSmoothingOptions.seasonalType="M"; 
               } else {
                   model.expSmoothingOptions.procedure = "Hyndman"; 
                   if (seasonalType.getSelectedItemText()=="None")           model.expSmoothingOptions.seasonalType="N"; 
                   else if (seasonalType.getSelectedItemText()=="Additive")  model.expSmoothingOptions.seasonalType="A"; 
                   else if (seasonalType.getSelectedItemText()=="Multiplicative")  model.expSmoothingOptions.seasonalType="M"; 
                   else if (seasonalType.getSelectedItemText()=="Automatic")  model.expSmoothingOptions.seasonalType="Z";

                
                   if (trendType.getSelectedItemText()=="None")           {model.expSmoothingOptions.basictrendType="N"; model.expSmoothingOptions.damped="NULL";}
                   else if (trendType.getSelectedItemText()=="Additive")  {model.expSmoothingOptions.basictrendType="A"; model.expSmoothingOptions.damped="NULL"; }
                   else if (trendType.getSelectedItemText()=="Additive damped")  {model.expSmoothingOptions.basictrendType="A"; model.expSmoothingOptions.damped="TRUE";}
                   else if (trendType.getSelectedItemText()=="Multiplicative")  {model.expSmoothingOptions.basictrendType="M"; model.expSmoothingOptions.damped="NULL";}
                   else if (trendType.getSelectedItemText()=="Multiplicative damped")  {model.expSmoothingOptions.basictrendType="M"; model.expSmoothingOptions.damped="TRUE";}
                   else if (trendType.getSelectedItemText()=="Automatic")  {model.expSmoothingOptions.basictrendType="Z"; model.expSmoothingOptions.damped="NULL";}
                   else if (trendType.getSelectedItemText()=="Automatic, damping any trend")  {model.expSmoothingOptions.basictrendType="Z"; model.expSmoothingOptions.damped="TRUE";}



                   if (errorType.getSelectedItemText()=="Additive")  model.expSmoothingOptions.errorType="A"; 
                   else if (errorType.getSelectedItemText()=="Multiplicative")  model.expSmoothingOptions.errorType="M"; 
                   else if (errorType.getSelectedItemText()=="Automatic")  model.expSmoothingOptions.errorType="Z";

                   if (optimizationCriterion.getSelectedItemText()=="Mean Square Error")  model.expSmoothingOptions.optimizationCriterion="mse"; 
                   else if (optimizationCriterion.getSelectedItemText()=="Avg MSE over 1st nsme forec hor")  model.expSmoothingOptions.optimizationCriterion="amse"; 
                   else if (optimizationCriterion.getSelectedItemText()=="Standard deviation of residuals")  model.expSmoothingOptions.optimizationCriterion="sigma";
                   else if (optimizationCriterion.getSelectedItemText()=="Mean of absoulute residuals")  model.expSmoothingOptions.optimizationCriterion="mae";
                   else if (optimizationCriterion.getSelectedItemText()=="Log-likelihood")  model.expSmoothingOptions.optimizationCriterion="lik";

                    model.expSmoothingOptions.infoCriterion = infoCriterion.getSelectedItemText();
               }

                
                
	}      
                
	

	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                if (model.expSmoothingOptions.procedure.equals("Holt-Winters"))
                    holtWintersButton.setSelected(true);
                else 
                    hyndmanButton.setSelected(true);
                alpha.setSelectedItem(model.expSmoothingOptions.alpha);
                beta.setSelectedItem(model.expSmoothingOptions.beta);
                gamma.setSelectedItem(model.expSmoothingOptions.gamma);
                if (model.expSmoothingOptions.seasonalType.equals("N")){
                   hwSeasonalType.setSelected("None");
                   seasonalType.setSelected("None");
                }else if (model.expSmoothingOptions.seasonalType.equals("A")){
                   hwSeasonalType.setSelected("Additive");
                   seasonalType.setSelected("Additive");
                }else if (model.expSmoothingOptions.seasonalType.equals("M")){
                   hwSeasonalType.setSelected("Multiplicative");
                   seasonalType.setSelected("Multiplicative");
                }else if (model.expSmoothingOptions.seasonalType.equals("Z")){
                   hwSeasonalType.setSelected("None");
                   seasonalType.setSelected("Automatic");
                }
               
               if  (model.expSmoothingOptions.basictrendType.equals("N"))
                   trendType.setSelected("None");
               else if (model.expSmoothingOptions.damped.equals("NULL"))
                  if (model.expSmoothingOptions.basictrendType.equals("A"))
                    trendType.setSelected("Additive");
                  else if (model.expSmoothingOptions.basictrendType.equals("M"))
                    trendType.setSelected("Multiplicative");
                  else if (model.expSmoothingOptions.basictrendType.equals("Z"))
                    trendType.setSelected("Automatic");
               else if (model.expSmoothingOptions.damped.equals("TRUE")) 
                  if (model.expSmoothingOptions.basictrendType.equals("A"))
                    trendType.setSelected("Additive damped");
                  else if (model.expSmoothingOptions.basictrendType.equals("M"))
                    trendType.setSelected("Multiplicative damped");
                  else if (model.expSmoothingOptions.basictrendType.equals("Z"))
                    trendType.setSelected("Automatic, damping any trend");
               
               if (model.expSmoothingOptions.errorType.equals("A"))
                   errorType.setSelected("Additive");
                else if (model.expSmoothingOptions.errorType.equals("M"))
                   errorType.setSelected("Multiplicative");
                else if (model.expSmoothingOptions.errorType.equals("Z"))
                   errorType.setSelected("Automatic");
               
               
               if (model.expSmoothingOptions.optimizationCriterion.equals("mse"))
                   optimizationCriterion.setSelected("Mean Square Error");
                else if (model.expSmoothingOptions.optimizationCriterion.equals("amse"))
                   optimizationCriterion.setSelected("Avg MSE over 1st nsme forec hor");
                else if (model.expSmoothingOptions.optimizationCriterion.equals("sigma"))
                   optimizationCriterion.setSelected("Standard deviation of residuals");
               else if (model.expSmoothingOptions.optimizationCriterion.equals("mae"))
                   optimizationCriterion.setSelected("Mean of absoulute residuals");
               else if (model.expSmoothingOptions.optimizationCriterion.equals("\"lik"))
                   optimizationCriterion.setSelected("Log-likelihood");

               predictAhead.setSelectedItem(model.expSmoothingOptions.predictAhead);  
               infoCriterion.setSelected(model.expSmoothingOptions.infoCriterion);                  
                
               model.efeaeplots.moreDiagnosticPlots = "None";

		
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    model.filterOptions.filterRun = true;

			updateModel();
			this.dispose();
		}
                if (hyndmanButton.isSelected()){
                  trendType.setVisible(true);
                  hwSeasonalType.setVisible(false);
                  seasonalType.setVisible(true);
                  errorType.setVisible(true);
                  optimizationCriterion.setVisible(true);
                  infoCriterion.setVisible(true);
                } else {
                  trendType.setVisible(false);
                  hwSeasonalType.setVisible(true);
                  seasonalType.setVisible(false);
                  errorType.setVisible(false);
                  optimizationCriterion.setVisible(false);
                  infoCriterion.setVisible(false);
                }
	}

}