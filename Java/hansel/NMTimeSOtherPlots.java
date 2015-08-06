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
 
 The file notably uses code from GLMExplorerPlots.java, corDialog.java, and GLMExplorerPostHoc.java in the Deducer package.
 The current file made adjustments to that earlier java file on 2015-03-13 to work with the DeducerHansel package,
 with subsequent revision on  2015-07-29.
 
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




public class NMTimeSOtherPlots extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel termPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
        protected JLabel termSelectionLabel;
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
        protected JRadioButton histogram;
        protected JRadioButton normalQQ;
        protected JRadioButton autocorrelationGiven;
        protected JRadioButton autoCovarianceGiven;
        protected JRadioButton autocorrelationSquared;
        protected JRadioButton partialAutocorrelationSquared;
        protected JRadioButton plotVsLag;
        protected JRadioButton fittedVsExplanatorySimple;
        protected JRadioButton fittedVsExplanatory;
        protected JRadioButton fittedVsActual;
        protected JRadioButton actualVsFitted;
        protected JRadioButton actualAndFittedvsTimeOrObs;
        protected JRadioButton confidenceEllipseInt;
        protected JRadioButton confidenceEllipseBothInt;
        protected JRadioButton accuracyVsCutoff;
        protected JRadioButton spinogramOne;
        protected JRadioButton spinogramChoose;
        protected JRadioButton confidenceEllipse;
        protected JRadioButton confidenceEllipseBoth;
        protected JRadioButton effectPlotsAll;
        protected JRadioButton effecPlotOne;
        protected JRadioButton effecPlotSimple;
        protected JRadioButton partialRegressionPlots;
        protected JRadioButton partialRegressionPlot;
        protected JRadioButton simpleRegressionPlot;
        protected JRadioButton partiallResidualPlots;
        protected JRadioButton partiallResidualPlot;
        protected JRadioButton scatterPlotsWithResponse;
        protected JRadioButton scatterPlotsWithoutResponse;
        protected JRadioButton scatterPlotsBothExplanatory;
        protected JRadioButton scatterPlotsWithOnlyResponse;
        protected JRadioButton regression3D;
        protected ButtonGroup plotOptionButtons;
        
        protected JRadioButton partialAutocorrelationGiven;
        protected JRadioButton autocovarianceSquared;
        protected JRadioButton responseTimeIndivStrat;
        protected JRadioButton simpleRegPlotByIndiv;
        protected JRadioButton rocCurve;
        protected JRadioButton partiallRegPlotsByIndiv;
        
        protected ButtonGroupWidget plotOptions;
        
        protected JLabel maxLagLabel;
        protected JComboBox maxLag;
        protected static DefaultComboBoxModel maxLagList  = new DefaultComboBoxModel(
				new String[] { "default","1", "2", "3","4","5","6","7","8","9",
                                               "10","11", "12", "13","14","15","16","17","18","19",
                                               "20","21", "22", "23","24","25","26","27","28","29",
                                               "30","31", "32", "33","34","35","36","37","38","39",
                                               "40","41", "42", "43","44","45","46","47","48","49",
                                               "50","51", "52", "53","54","55","56","57","58","59",
                                               "60","61", "62", "63","64","65","66","67","68","69",
                                               "70","71", "72", "73","74","75","76","77","78","79",
                                               "80","81", "82", "83","84","85","86","87","88","89",
                                               "90","91", "92", "93","94","95","96","97","98","99",
                                               "100"
                                               });  
        protected JLabel minLagLabel;
        protected JComboBox minLag;
        protected static DefaultComboBoxModel minLagList  = new DefaultComboBoxModel(
				new String[] { "max lag","1", "2", "3","4","5","6","7","8","9",
                                               "10","11", "12", "13","14","15","16","17","18","19",
                                               "20","21", "22", "23","24","25","26","27","28","29",
                                               "30","31", "32", "33","34","35","36","37","38","39",
                                               "40","41", "42", "43","44","45","46","47","48","49",
                                               "50","51", "52", "53","54","55","56","57","58","59",
                                               "60","61", "62", "63","64","65","66","67","68","69",
                                               "70","71", "72", "73","74","75","76","77","78","79",
                                               "80","81", "82", "83","84","85","86","87","88","89",
                                               "90","91", "92", "93","94","95","96","97","98","99",
                                               "100"
                                               });  
	
	public NMTimeSOtherPlots(JFrame frame,GMModel mod,RModel rmod) {
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
					okayCancel.setBounds(205, 390, 207, 36);
                               
                                {
					histogram = new JRadioButton();
                                        getContentPane().add(histogram , new AnchorConstraint(31, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					histogram.setText("Histogram");
                                        histogram.setFont(font12);
				}
				{
					normalQQ = new JRadioButton();
                                        getContentPane().add(normalQQ , new AnchorConstraint(81, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					normalQQ.setText("Normal Q-Q");
                                        normalQQ.setFont(font12);
				}
				{
					autocorrelationGiven = new JRadioButton();
                                        getContentPane().add(autocorrelationGiven , new AnchorConstraint(181, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					autocorrelationGiven.setText("autocorrelation function for given series");
                                        autocorrelationGiven.setFont(font12);
				}

                                {
					autoCovarianceGiven = new JRadioButton();
                                        getContentPane().add(autoCovarianceGiven , new AnchorConstraint(231, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					autoCovarianceGiven.setText("autocovariance function for given series");
                                        autoCovarianceGiven.setFont(font12);
                                        
				}
                                {
					partialAutocorrelationGiven = new JRadioButton();
                                        getContentPane().add(partialAutocorrelationGiven, new AnchorConstraint(281, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partialAutocorrelationGiven.setText("partial autocorrelation for given series");
                                        partialAutocorrelationGiven.setFont(font12);
				}
				{
					autocorrelationSquared = new JRadioButton();
                                        getContentPane().add(autocorrelationSquared , new AnchorConstraint(331, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					autocorrelationSquared.setText("autocorrelation function for given series squared");
                                        autocorrelationSquared.setFont(font12);
				}
                                {
					autocovarianceSquared = new JRadioButton();
                                        getContentPane().add(autocovarianceSquared , new AnchorConstraint(381, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					autocovarianceSquared.setText("autocovariance function for given series squared");
                                        autocovarianceSquared.setFont(font12);
				}
				{
					partialAutocorrelationSquared = new JRadioButton();
                                        getContentPane().add(partialAutocorrelationSquared , new AnchorConstraint(431, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partialAutocorrelationSquared.setText("partial autocorrelation for given series squared");
                                        partialAutocorrelationSquared.setFont(font12);
				}
                                {
					plotVsLag = new JRadioButton();
                                        getContentPane().add(plotVsLag , new AnchorConstraint(531, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					plotVsLag.setText("Given Series vs Lag");
                                        plotVsLag.setFont(font12);
				}
                                
                                 {
                                        minLagLabel = new JLabel();
                                        getContentPane().add(minLagLabel, new AnchorConstraint(571, 487, 782, 61, 
                                                               AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                               AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        minLagLabel.setText("with min lag equal to");
                                        minLagLabel.setFont(font12);
                                        minLagLabel.setPreferredSize(new java.awt.Dimension(110, 30));
                                        minLagLabel.setVisible(true);

                                        minLag = new JComboBox();
                                        getContentPane().add(minLag, new AnchorConstraint(591, 487, 782, 351, 
                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        minLag.setModel(minLagList);
                                        minLag.setFont(font12);
                                        minLag.setPreferredSize(new java.awt.Dimension(70, 21));
                                        minLag.addActionListener(this);
                                        minLag.setVisible(true);
                                } 
                                
                                
                                 {
                                        maxLagLabel = new JLabel();
                                        getContentPane().add(maxLagLabel, new AnchorConstraint(181, 487, 782, 661, 
                                                               AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                               AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        maxLagLabel.setText("max lag:");
                                        maxLagLabel.setFont(font12);
                                        maxLagLabel.setPreferredSize(new java.awt.Dimension(60, 30));
                                        maxLagLabel.setVisible(true);

                                        maxLag = new JComboBox();
                                        getContentPane().add(maxLag, new AnchorConstraint(191, 487, 782, 791, 
                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        maxLag.setModel(maxLagList);
                                        maxLag.setFont(font12);
                                        maxLag.setPreferredSize(new java.awt.Dimension(70, 21));
                                        maxLag.addActionListener(this);
                                        maxLag.setVisible(true);
                                } 
                                 
                                 

                                {
                                	ButtonGroup plotOptionButtons =new ButtonGroup();
                                        plotOptionButtons.add(histogram);
                                        plotOptionButtons.add(normalQQ);
                                        plotOptionButtons.add(autocorrelationGiven);
                                        plotOptionButtons.add(autoCovarianceGiven);
                                        plotOptionButtons.add(partialAutocorrelationGiven);
                                        plotOptionButtons.add(autocorrelationSquared);
                                        plotOptionButtons.add(partialAutocorrelationSquared);
                                        plotOptionButtons.add(autocovarianceSquared);
                                        plotOptionButtons.add(plotVsLag);   

                                }

				}
			}
			this.setTitle("Other Plots");
			this.setSize(432, 480);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
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
                    String plotSelection = "";                       
                    if (histogram.isSelected()) plotSelection = histogram.getText();
                    else if (normalQQ.isSelected()) plotSelection = normalQQ.getText();
                    else if (autocorrelationGiven.isSelected()) plotSelection = autocorrelationGiven.getText();
                    else if (autoCovarianceGiven.isSelected()) plotSelection = autoCovarianceGiven.getText();
                    else if (partialAutocorrelationGiven.isSelected()) plotSelection = partialAutocorrelationGiven.getText();
                    else if (autocorrelationSquared.isSelected()) plotSelection = autocorrelationSquared.getText();
                    else if (autocovarianceSquared.isSelected()) plotSelection = autocovarianceSquared.getText();
                    else if (partialAutocorrelationSquared.isSelected()) plotSelection = partialAutocorrelationSquared.getText();
                    else if (plotVsLag.isSelected()) plotSelection = plotVsLag.getText();
                    
                    model.UTSOtherPlotsOptions.maxLag = maxLag.getSelectedItem().toString();
                    model.UTSOtherPlotsOptions.minLag = minLag.getSelectedItem().toString();
                     if (       (plotSelection.equals("Residuals vs explanatory variable*"))||
                                (plotSelection.equals("spinogram-one variable to right*"))||
                                (plotSelection.equals("confidence ellipse for intercept & one highlighted variable*"))||
                                (plotSelection.equals("confidence ellipse for 2 highlighted variables*"))||
                                (plotSelection.equals("Simple regression plots, by individual*"))||
                                (plotSelection.equals("Effect (prediction) plot-one variable to right*"))||
                                (plotSelection.equals("Partial regression plots*"))||
                                (plotSelection.equals("Partial regression plot*"))||
                                (plotSelection.equals("Partial regression plots, by individual*"))|| 
                                (plotSelection.equals("Partial residual plots*"))||
                                (plotSelection.equals("Scatter plots with response variable*"))||
                                (plotSelection.equals("Scatter plots without response variable*")
                                )){ 
             	       Object[] plotTerms = termsList.getSelectedValuesList().toArray();
		       if ((plotTerms.equals(null) || plotTerms.length<1)&&(
                                
                                (plotSelection.equals("Partial regression plots*"))||
                                (plotSelection.equals("Partial residual plots*"))||
                                (plotSelection.equals("Scatter plots with response variable*"))
                                ))
                         {
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			 }
                        if ((plotTerms.equals(null) || !(plotTerms.length==1))&&(
                                (plotSelection.equals("Residuals vs explanatory variable*"))||
                                (plotSelection.equals("spinogram-one variable to right*"))||
                                (plotSelection.equals("Simple regression plots, by individual*"))||
                                (plotSelection.equals("confidence ellipse for intercept & one highlighted variable*"))||
                                (plotSelection.equals("Effect (prediction) plot-one variable to right*"))||
                                (plotSelection.equals("Partial regression plot*"))||
                                (plotSelection.equals("Partial regression plots, by individual*"))
                                ))
                         {
				JOptionPane.showMessageDialog(null, "Please select one variable");
				return;
			 }
                        
                        
                        if ((plotTerms.equals(null) || !(plotTerms.length==2))&&(
                                (plotSelection.equals("confidence ellipse for 2 highlighted variables*"))
                                ))
                         {
				JOptionPane.showMessageDialog(null, "Please select two variables");
				return;
			 }
                        
			if ((plotTerms.equals(null) || plotTerms.length<2)&&(
                                plotSelection.equals("Scatter plots without response variable*")
                                ))
                         {
				JOptionPane.showMessageDialog(null, "Please select at least two variables");
				return;
			 }
                        if (plotSelection.equals("confidence ellipse for intercept & one highlighted variable*")) {
                            model.efeaeplots.termsToPlot = "c(\"(Intercept)\",\""+(String) plotTerms[0]+"\")";
                        } else if (plotSelection.equals("confidence ellipse for 2 highlighted variables*")) {
                            model.efeaeplots.termsToPlot = "c(\""+(String) plotTerms[0]+"\"";
                            for(int i =1;i<plotTerms.length;i++){
                                model.efeaeplots.termsToPlot+=",\""+plotTerms[i]+"\"";
                            }
                            model.efeaeplots.termsToPlot+=")";
                        } else {
                            model.efeaeplots.termsToPlot = (String) plotTerms[0];
                            for(int i =1;i<plotTerms.length;i++){
                                model.efeaeplots.termsToPlot+="+"+plotTerms[i];
                           }  
                        }    
                     }
                        model.efeaeplots.moreDiagnosticPlots = plotSelection;
                        
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