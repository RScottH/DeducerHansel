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
 
The current file most notably uses the code from  GLMExplorerPlots.java and CorDialog.java in the Deducer package.  

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




public class GMExplorerOtherPlots extends javax.swing.JDialog implements ActionListener{
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
        protected JRadioButton residualsHistogram;
        protected JRadioButton normalQQResiduals;
        protected JRadioButton residualsVsFitted;
        protected JRadioButton scaleLocation;
        protected JRadioButton cooksDistance;
        protected JRadioButton residualsVsLeverage;
        protected JRadioButton residualsVsResponse;
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
        
        protected JRadioButton indivEffectsHistogram;
        protected JRadioButton indivEffectsNormalQQ;
        protected JRadioButton responseTimeIndivStrat;
        protected JRadioButton simpleRegPlotByIndiv;
        protected JRadioButton rocCurve;
        protected JRadioButton partiallRegPlotsByIndiv;

        protected ButtonGroupWidget plotOptions;
	
	public GMExplorerOtherPlots(JFrame frame,GMModel mod,RModel rmod) {
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
					okayCancel.setBounds(455, 390, 207, 36);
                                 
                                {
					residualsHistogram = new JRadioButton();
                                        getContentPane().add(residualsHistogram , new AnchorConstraint(31, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					residualsHistogram.setText("Residuals histogram");
                                        residualsHistogram.setFont(font12);
				}
				{
					normalQQResiduals = new JRadioButton();
                                        getContentPane().add(normalQQResiduals , new AnchorConstraint(81, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					normalQQResiduals.setText("Normal Q-Q for residuals");
                                        normalQQResiduals.setFont(font12);
				}
				{
					residualsVsFitted = new JRadioButton();
                                        getContentPane().add(residualsVsFitted , new AnchorConstraint(231, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					residualsVsFitted.setText("Residuals vs fitted");
                                        residualsVsFitted.setFont(font12);
				}

                                {
					scaleLocation = new JRadioButton();
                                        getContentPane().add(scaleLocation , new AnchorConstraint(281, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					scaleLocation.setText("Scale-location");
                                        scaleLocation.setFont(font12);
                                        
				}
                                {
					indivEffectsHistogram = new JRadioButton();
                                        getContentPane().add(indivEffectsHistogram, new AnchorConstraint(281, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					indivEffectsHistogram.setText("Individual effects histogram");
                                        indivEffectsHistogram.setFont(font12);
                                        indivEffectsHistogram.setVisible(false);
				}
				{
					cooksDistance = new JRadioButton();
                                        getContentPane().add(cooksDistance , new AnchorConstraint(331, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					cooksDistance.setText("Cook's distance");
                                        cooksDistance.setFont(font12);
				}
                                {
					indivEffectsNormalQQ = new JRadioButton();
                                        getContentPane().add(indivEffectsNormalQQ , new AnchorConstraint(331, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					indivEffectsNormalQQ.setText("Normal Q-Q for individual effects");
                                        indivEffectsNormalQQ.setFont(font12);
				}
				{
					residualsVsLeverage = new JRadioButton();
                                        getContentPane().add(residualsVsLeverage , new AnchorConstraint(381, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					residualsVsLeverage.setText("Residuals vs leverage");
                                        residualsVsLeverage.setFont(font12);
				}
                                {
					residualsVsResponse = new JRadioButton();
                                        getContentPane().add(residualsVsResponse , new AnchorConstraint(431, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					residualsVsResponse.setText("Residuals vs response variable");
                                        residualsVsResponse.setFont(font12);
				}
                                {
					fittedVsExplanatorySimple = new JRadioButton();
                                        getContentPane().add(fittedVsExplanatorySimple , new AnchorConstraint(481, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                          fittedVsExplanatorySimple.setText("Residuals vs explanatory variable");
                                        fittedVsExplanatorySimple.setFont(font12);
				}
                                {
					fittedVsExplanatory = new JRadioButton();
                                        getContentPane().add(fittedVsExplanatory , new AnchorConstraint(481, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					
                                        fittedVsExplanatory.setText("Residuals vs explanatory variable*");
                                        fittedVsExplanatory.setFont(font12);
				}
                                
                                {
					fittedVsActual = new JRadioButton();
                                        getContentPane().add(fittedVsActual , new AnchorConstraint(581, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					fittedVsActual.setText("Fitted vs actual");
                                        fittedVsActual.setFont(font12);
				}
                                {
					actualVsFitted = new JRadioButton();
                                        getContentPane().add(actualVsFitted , new AnchorConstraint(631, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					actualVsFitted.setText("Actual vs fitted");
                                        actualVsFitted.setFont(font12);
				}
                                {
					actualAndFittedvsTimeOrObs = new JRadioButton();
                                        getContentPane().add(actualAndFittedvsTimeOrObs , new AnchorConstraint(681, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					actualAndFittedvsTimeOrObs.setText("Actual & fitted vs time or observation");
                                        actualAndFittedvsTimeOrObs.setFont(font12);
				}
                                {
					regression3D = new JRadioButton();
                                        getContentPane().add(regression3D , new AnchorConstraint(781, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					regression3D.setText("Create on your console .run3D, a function for making 3D regression plot");
                                        regression3D.setFont(font12);
				}
                                
                                
                                {
					confidenceEllipseInt = new JRadioButton();
                                        getContentPane().add(confidenceEllipseInt , new AnchorConstraint(31, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					confidenceEllipseInt.setText("confidence ellipse for intercept & one highlighted variable*");
                                        confidenceEllipseInt.setFont(font12);
				}
                                {
					confidenceEllipseBothInt = new JRadioButton();
                                        getContentPane().add(confidenceEllipseBothInt , new AnchorConstraint(31, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					confidenceEllipseBothInt.setText("confidence ellipse for intercept & explanatory variable");
                                        confidenceEllipseBothInt.setFont(font12);
				}
                              
                                {
					responseTimeIndivStrat = new JRadioButton();
                                        getContentPane().add(responseTimeIndivStrat , new AnchorConstraint(31, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					responseTimeIndivStrat.setText("Response by time, individual stratification");
                                        responseTimeIndivStrat.setFont(font12);
				}
                                
                                
				{
					confidenceEllipse = new JRadioButton();
                                        getContentPane().add(confidenceEllipse , new AnchorConstraint(81, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					confidenceEllipse.setText("confidence ellipse for 2 highlighted variables*");
                                        confidenceEllipse.setFont(font12);
				}
                                {
					confidenceEllipseBoth = new JRadioButton();
                                        getContentPane().add(confidenceEllipseBoth , new AnchorConstraint(81, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					confidenceEllipseBoth.setText("confidence ellipse for both variables to the right");
                                        confidenceEllipseBoth.setFont(font12);
				}
                                
                                {
					simpleRegPlotByIndiv = new JRadioButton();
                                        getContentPane().add(simpleRegPlotByIndiv , new AnchorConstraint(81, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					simpleRegPlotByIndiv.setText("Simple regression plots, by individual*");
                                        simpleRegPlotByIndiv.setFont(font12);
				}

                                                                
				{
					effectPlotsAll = new JRadioButton();
                                        getContentPane().add(effectPlotsAll , new AnchorConstraint(181, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					effectPlotsAll.setText("Effect (prediction) plots-all variables to right");
                                        effectPlotsAll.setFont(font12);
				}
				{
					effecPlotOne = new JRadioButton();
                                        getContentPane().add(effecPlotOne , new AnchorConstraint(231, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					effecPlotOne.setText("Effect (prediction) plot-one variable to right*");
                                        effecPlotOne.setFont(font12);
				}
                                {
					effecPlotSimple = new JRadioButton();
                                        getContentPane().add(effecPlotSimple , new AnchorConstraint(231, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					effecPlotSimple.setText("Effect (prediction) plot");
                                        effecPlotSimple.setFont(font12);
				}
                                
				{
					partialRegressionPlots = new JRadioButton();
                                        getContentPane().add(partialRegressionPlots , new AnchorConstraint(331, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partialRegressionPlots.setText("Partial regression plots*");
                                        partialRegressionPlots.setFont(font12);
				}
                                {
					partialRegressionPlot = new JRadioButton();
                                        getContentPane().add(partialRegressionPlot , new AnchorConstraint(331, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partialRegressionPlot.setText("Partial regression plot*");
                                        partialRegressionPlot.setFont(font12);
                                        partialRegressionPlot.setVisible(false);
				}
                                
                               {
					simpleRegressionPlot = new JRadioButton();
                                        getContentPane().add(simpleRegressionPlot , new AnchorConstraint(331, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					simpleRegressionPlot.setText("Simple regression function plot");
                                        simpleRegressionPlot.setFont(font12);
				}
                                
				{
					partiallResidualPlots = new JRadioButton();
                                        getContentPane().add(partiallResidualPlots , new AnchorConstraint(381, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partiallResidualPlots.setText("Partial residual plots*");
                                        partiallResidualPlots.setFont(font12);
				}
                                
                                {
					partiallResidualPlot = new JRadioButton();
                                        getContentPane().add(partiallResidualPlot , new AnchorConstraint(381, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partiallResidualPlot.setText("Partial residual plot");
                                        partiallResidualPlot.setFont(font12);
				}
                                {
					partiallRegPlotsByIndiv = new JRadioButton();
                                        getContentPane().add(partiallRegPlotsByIndiv , new AnchorConstraint(381, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					partiallRegPlotsByIndiv.setText("Partial regression plots, by individual*");
                                        partiallRegPlotsByIndiv.setFont(font12);
				}
                                
				{
					scatterPlotsWithResponse = new JRadioButton();
                                        getContentPane().add(scatterPlotsWithResponse , new AnchorConstraint(481, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					scatterPlotsWithResponse.setText("Scatter plots with response variable*");
                                        scatterPlotsWithResponse.setFont(font12);
				}
                                
                                {
					scatterPlotsWithOnlyResponse = new JRadioButton();
                                        getContentPane().add(scatterPlotsWithOnlyResponse , new AnchorConstraint(481, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					scatterPlotsWithOnlyResponse.setText("Scatter plot: response variable vs explanatory variable");
                                        scatterPlotsWithOnlyResponse.setFont(font12);
				}
                                
				{
					scatterPlotsWithoutResponse = new JRadioButton();
                                        getContentPane().add(scatterPlotsWithoutResponse , new AnchorConstraint(531, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					scatterPlotsWithoutResponse.setText("Scatter plots without response variable*");
                                        scatterPlotsWithoutResponse.setFont(font12);
				}
				{
					scatterPlotsBothExplanatory = new JRadioButton();
                                        getContentPane().add(scatterPlotsBothExplanatory , new AnchorConstraint(531, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					scatterPlotsBothExplanatory.setText("Scatter plots, both explanatory variables");
                                        scatterPlotsBothExplanatory.setFont(font12);
				}
                                
                                  {
					accuracyVsCutoff = new JRadioButton();
                                        getContentPane().add(accuracyVsCutoff , new AnchorConstraint(631, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					accuracyVsCutoff.setText("accuracy vs cutoff");
                                        accuracyVsCutoff.setFont(font12);
				}
                                 {
					spinogramOne = new JRadioButton();
                                        getContentPane().add(spinogramOne , new AnchorConstraint(631, 487, 782, 470, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					spinogramOne.setText("spinogram");
                                        spinogramOne.setFont(font12);
				}
                                {
					spinogramChoose = new JRadioButton();
                                        getContentPane().add(spinogramChoose , new AnchorConstraint(631, 487, 782, 470, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					spinogramChoose.setText("spinogram-one variable to right*");
                                        spinogramChoose.setFont(font12);
				}
                                {
					rocCurve = new JRadioButton();
                                        getContentPane().add(rocCurve , new AnchorConstraint(681, 487, 782, 311, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					rocCurve.setText("ROC curve");
                                        rocCurve.setFont(font12);
				}
                                {
                                	ButtonGroup plotOptionButtons =new ButtonGroup();
                                        plotOptionButtons.add(residualsHistogram);
                                        plotOptionButtons.add(normalQQResiduals);
                                        plotOptionButtons.add(residualsVsFitted);
                                        plotOptionButtons.add(scaleLocation);
                                        plotOptionButtons.add(cooksDistance);
                                        plotOptionButtons.add(residualsVsLeverage);
                                        plotOptionButtons.add(residualsVsResponse);
                                        plotOptionButtons.add(fittedVsExplanatorySimple);
                                        plotOptionButtons.add(fittedVsExplanatory);
                                        plotOptionButtons.add(fittedVsActual);
                                        plotOptionButtons.add(actualVsFitted);
                                        plotOptionButtons.add(actualAndFittedvsTimeOrObs);
                                        plotOptionButtons.add(confidenceEllipseInt);
                                        plotOptionButtons.add(confidenceEllipseBothInt);
                                        plotOptionButtons.add(accuracyVsCutoff);
                                        plotOptionButtons.add(spinogramOne);
                                        plotOptionButtons.add(spinogramChoose);
                                        plotOptionButtons.add(confidenceEllipse);
                                        plotOptionButtons.add(confidenceEllipseBoth);
                                        plotOptionButtons.add(effectPlotsAll);
                                        plotOptionButtons.add(effecPlotOne);
                                        plotOptionButtons.add(effecPlotSimple);
                                        plotOptionButtons.add(partialRegressionPlots);
                                        plotOptionButtons.add(partialRegressionPlot);
                                        plotOptionButtons.add(simpleRegressionPlot);
                                        plotOptionButtons.add(partiallResidualPlots);
                                        plotOptionButtons.add(partiallResidualPlot);
                                        plotOptionButtons.add(scatterPlotsWithResponse);
                                        plotOptionButtons.add(scatterPlotsWithOnlyResponse);
                                        plotOptionButtons.add(scatterPlotsWithoutResponse);
                                        plotOptionButtons.add(scatterPlotsBothExplanatory);
                                        plotOptionButtons.add(regression3D);
                                        plotOptionButtons.add(indivEffectsHistogram);
                                        plotOptionButtons.add(indivEffectsNormalQQ);
                                        plotOptionButtons.add(responseTimeIndivStrat);
                                        plotOptionButtons.add(simpleRegPlotByIndiv);
                                        plotOptionButtons.add(rocCurve);
                                        plotOptionButtons.add(partiallRegPlotsByIndiv);




                                }
                                
                                       {     
					termSelectionLabel = new JLabel();
					getContentPane().add(termSelectionLabel, new AnchorConstraint(850, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));              
					termSelectionLabel.setText("\"*\" indicates 1 or more variables need to be chosen.");
                                        termSelectionLabel.setFont(font11);
					termSelectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
                                
                                
                               	{
					termsPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					termsPanel.setLayout(varPanelLayout);
					getContentPane().add(termsPanel, new AnchorConstraint(11, 375, 725, 750, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					termsPanel.setBorder(BorderFactory.createTitledBorder("Explanatory Variables"));
					termsPanel.setPreferredSize(new java.awt.Dimension(150, 270));
					{
						termsScroller = new JScrollPane();
						termsPanel.add(termsScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							termsList = new JList();
							termsScroller.setViewportView(termsList);
							termsList.setModel(varListModel);
						}
					}
				} 
                                
				}
			}
			this.setTitle("Other Plots");
			this.setSize(732, 480);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
	}
        
	public void setModel(GMModel mod,RModel rmod){
		model = mod; 

                termsList.setModel(model.coefList);
                model.efeaeplots.moreDiagnosticPlots = "None";
                
                residualsVsFitted.setVisible(false);
                
                indivEffectsHistogram.setVisible(false);
                indivEffectsNormalQQ.setVisible(false);
                responseTimeIndivStrat.setVisible(false);
                simpleRegressionPlot.setVisible(false); 
                simpleRegPlotByIndiv.setVisible(false);
                accuracyVsCutoff.setVisible(false);
                spinogramOne.setVisible(false);
                spinogramChoose.setVisible(false);
                rocCurve.setVisible(false);
                partiallRegPlotsByIndiv.setVisible(false);
              
                if ((model.method.equals("lm")||model.method.equals("dynlm")
                        || model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess") 
                        )&model.numExplanatoryVariables==0) {
                    fittedVsExplanatorySimple.setVisible(false);
                    fittedVsExplanatory.setVisible(false);
                    confidenceEllipseInt.setVisible(false);
                    confidenceEllipseBothInt.setVisible(false);
                    accuracyVsCutoff.setVisible(false);
                    spinogramChoose.setVisible(false);
                    confidenceEllipse.setVisible(false);
                    confidenceEllipseBoth.setVisible(false);
                    effectPlotsAll.setVisible(false);
                    effecPlotOne.setVisible(false);
                    effecPlotSimple.setVisible(false);
                    partialRegressionPlots.setVisible(false);
                    partiallResidualPlots.setVisible(false);
                    partiallResidualPlot.setVisible(false);
                    scatterPlotsWithResponse.setVisible(false);
                    scatterPlotsWithOnlyResponse.setVisible(false);
                    scatterPlotsWithoutResponse.setVisible(false);
                    scatterPlotsBothExplanatory.setVisible(false);
                    termsPanel.setEnabled(false);
                    termsScroller.setEnabled(false);
                    termsList.setEnabled(false);
                    termSelectionLabel.setVisible(false);
                } else if ((model.method.equals("lm")||model.method.equals("dynlm")
                        || model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess") 
                        )&model.numExplanatoryVariables==1) {
                    fittedVsExplanatorySimple.setVisible(true);
                    fittedVsExplanatory.setVisible(false);
                    confidenceEllipseInt.setVisible(false);
                    confidenceEllipse.setVisible(false);
                    accuracyVsCutoff.setVisible(false);
                    spinogramChoose.setVisible(false);
                    confidenceEllipseBoth.setVisible(false);
                    effectPlotsAll.setVisible(false);
                    effecPlotOne.setVisible(false);
                    effecPlotSimple.setVisible(true);
                    effecPlotSimple.setEnabled(model.method.equals("lm"));
                    partialRegressionPlots.setVisible(false);
                    partiallResidualPlots.setVisible(false);
                    partiallResidualPlot.setVisible(true);
                    partiallResidualPlot.setEnabled(model.method.equals("lm"));
                    scatterPlotsWithResponse.setVisible(false);
                    scatterPlotsWithOnlyResponse.setVisible(true);
                    scatterPlotsWithoutResponse.setVisible(false);
                    scatterPlotsBothExplanatory.setVisible(false);
                    termsPanel.setEnabled(false);
                    termsScroller.setEnabled(false);
                    termsList.setEnabled(false);
                    termSelectionLabel.setVisible(false);
                } else {
                    if (model.numExplanatoryVariables==1){
                       fittedVsExplanatorySimple.setVisible(true);
                       fittedVsExplanatory.setVisible(false);
                    } else {
                       fittedVsExplanatorySimple.setVisible(false);
                       fittedVsExplanatory.setVisible(true);
                    }
                    fittedVsExplanatory.setVisible(true);
                    confidenceEllipseBothInt.setVisible(false);
                    effectPlotsAll.setEnabled(model.method.equals("lm")||model.method.equals("glm"));
                    effecPlotOne.setEnabled(model.method.equals("lm")||model.method.equals("glm"));
                    effecPlotSimple.setVisible(false);
                    partiallResidualPlots.setEnabled(model.method.equals("lm")||model.method.equals("glm"));
                    partiallResidualPlot.setVisible(false);
                    scatterPlotsWithOnlyResponse.setVisible(false);
                    if ((model.method.equals("lm")||model.method.equals("dynlm"))&model.termsFinalStage.getSize()==2){
                        confidenceEllipse.setVisible(false);
                        scatterPlotsWithoutResponse.setVisible(false);
                        scatterPlotsBothExplanatory.setVisible(true);
                    } else {
                        confidenceEllipseBoth.setVisible(false);
                    }
                   
                    
                    if (((model.numExplanatoryVariables==2)&((model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm"))&model.efeglmextra.SpatialDurbin == true))
                            ||model.method.equals("GMerrorsar") ||model.method.equals("gstsls")||model.method.equals("spautolm") ){ //In these cases there is an additional unchoosable coefficient variable, such as a lag
                        fittedVsExplanatorySimple.setVisible(true);
                        fittedVsExplanatory.setVisible(false);
                        partialRegressionPlots.setVisible(false);
                        scatterPlotsWithResponse.setVisible(false);
                        scatterPlotsWithOnlyResponse.setVisible(true);
                        scatterPlotsWithoutResponse.setVisible(false);
                        scatterPlotsBothExplanatory.setVisible(false);
                    }
                    
                           
                    
                    
                }
                
                if(model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess")){
                    scaleLocation.setEnabled(false);
                    cooksDistance.setEnabled(false);
                    residualsVsLeverage.setEnabled(false);
                    partialRegressionPlots.setEnabled(false);
                    confidenceEllipseInt.setEnabled(false);
                    confidenceEllipseBothInt.setEnabled(false);
                    confidenceEllipse.setEnabled(false);
                    confidenceEllipseBoth.setEnabled(false);
                }
               
                 if (!((model.method.equals("lm")&model.numExplanatoryVariables==2))) {
                   regression3D.setVisible(false);
               }
                
               if ((model.EstimationMethod.equals("Binary Logit"))||model.EstimationMethod.equals("Binary Probit")){
                 
                 residualsHistogram.setEnabled(false); 
                 normalQQResiduals.setEnabled(false);
                 scaleLocation.setEnabled(false);
                 residualsVsFitted.setVisible(true);
                 confidenceEllipseInt.setVisible(false);
                 confidenceEllipseBothInt.setVisible(false);
                 accuracyVsCutoff.setVisible(true);
                 rocCurve.setVisible(true);
                 if(model.numExplanatoryVariables==1){
                     spinogramOne.setVisible(true);
                     effectPlotsAll.setVisible(false);
                     effecPlotOne.setVisible(false);
                     effecPlotSimple.setVisible(true);
                     partialRegressionPlots.setVisible(false);
                     partiallResidualPlots.setVisible(false);
                     partiallResidualPlot.setVisible(true);
                     termsPanel.setEnabled(false);
                     termsScroller.setEnabled(false);
                     termsList.setEnabled(false);
                     termSelectionLabel.setVisible(false);
                     confidenceEllipseBothInt.setVisible(true);
                     confidenceEllipseInt.setVisible(false);
                     confidenceEllipseBoth.setVisible(false);
                     confidenceEllipse.setVisible(false);
                     scatterPlotsWithOnlyResponse.setVisible(true);
                     scatterPlotsWithResponse.setVisible(false);
                     scatterPlotsBothExplanatory.setVisible(false);
                     scatterPlotsWithoutResponse.setVisible(false);
                 } else if (model.numExplanatoryVariables==2){
                     spinogramChoose.setVisible(true);           
                     confidenceEllipseBothInt.setVisible(false);
                     confidenceEllipseInt.setVisible(true);
                     confidenceEllipseBoth.setVisible(true);
                     confidenceEllipse.setVisible(false);
                     scatterPlotsWithOnlyResponse.setVisible(false);
                     scatterPlotsWithResponse.setVisible(true);
                     scatterPlotsBothExplanatory.setVisible(true);
                     scatterPlotsWithoutResponse.setVisible(false);
                 } else {
                     spinogramChoose.setVisible(true);
                     confidenceEllipseBothInt.setVisible(false);
                     confidenceEllipseInt.setVisible(true);
                     confidenceEllipseBoth.setVisible(false);
                     confidenceEllipse.setVisible(true);
                     scatterPlotsWithOnlyResponse.setVisible(false);
                     scatterPlotsWithResponse.setVisible(true);
                     scatterPlotsBothExplanatory.setVisible(false);
                     scatterPlotsWithoutResponse.setVisible(true);
                 }
                 
               }
                if (model.method.equals("tobit")) {
                scaleLocation.setEnabled(false);
                cooksDistance.setEnabled(false);
                residualsVsLeverage.setEnabled(false);
                if(model.numExplanatoryVariables==1){
                    confidenceEllipseBothInt.setVisible(true);
                     confidenceEllipseInt.setVisible(false);
                     confidenceEllipseBoth.setVisible(false);
                     confidenceEllipse.setVisible(false);
                     scatterPlotsWithOnlyResponse.setVisible(true);
                     scatterPlotsWithResponse.setVisible(false);
                     scatterPlotsBothExplanatory.setVisible(false);
                     scatterPlotsWithoutResponse.setVisible(false);
                    termsPanel.setEnabled(false);
                    termsScroller.setEnabled(false);
                    termsList.setEnabled(false);
                    termSelectionLabel.setVisible(false);
                 } else if (model.numExplanatoryVariables==2){
                     confidenceEllipseBothInt.setVisible(false);
                     confidenceEllipseInt.setVisible(true);
                     confidenceEllipseBoth.setVisible(true);
                     confidenceEllipse.setVisible(false);
                     scatterPlotsWithOnlyResponse.setVisible(false);
                     scatterPlotsWithResponse.setVisible(true);
                     scatterPlotsBothExplanatory.setVisible(true);
                     scatterPlotsWithoutResponse.setVisible(false);
                } else {
                     confidenceEllipseBothInt.setVisible(false);
                     confidenceEllipseInt.setVisible(true);
                     confidenceEllipseBoth.setVisible(false);
                     confidenceEllipse.setVisible(true);
                     scatterPlotsWithOnlyResponse.setVisible(false);
                     scatterPlotsWithResponse.setVisible(true);
                     scatterPlotsBothExplanatory.setVisible(false);
                     scatterPlotsWithoutResponse.setVisible(true);
                }
                effectPlotsAll.setEnabled(false);
                effecPlotOne.setEnabled(false);
                partialRegressionPlots.setEnabled(false);
                partiallResidualPlots.setEnabled(false); 
                }
                
                if (model.method.equals("plm")||model.method.equals("pvcm")||model.method.equals("pggls")||
                          model.method.equals("pgmm")||model.method.equals("pcce")||model.method.equals("pmg")
                          ||model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm")
                        ) {
                scaleLocation.setVisible(false);
                cooksDistance.setVisible(false);
                residualsVsLeverage.setVisible(false);
                confidenceEllipseInt.setVisible(false);
                confidenceEllipseBothInt.setVisible(false);
                confidenceEllipse.setVisible(false);
                confidenceEllipseBoth.setVisible(false);
                effectPlotsAll.setVisible(false);
                effecPlotOne.setVisible(false);
                partiallResidualPlots.setVisible(false);
                partialRegressionPlots.setVisible(false);
                
                responseTimeIndivStrat.setVisible(true);
                simpleRegPlotByIndiv.setVisible(true);
                
                if (model.method.equals("plm")) {
                  partialRegressionPlot.setVisible(true);
                  indivEffectsHistogram.setVisible(true);
                  indivEffectsNormalQQ.setVisible(true);
                  partiallRegPlotsByIndiv.setVisible(true);
                  }
                else if (model.method.equals("pgmm")) {
                  fittedVsExplanatory.setVisible(false);
                  simpleRegPlotByIndiv.setVisible(false);
                  scatterPlotsWithResponse.setVisible(false);
                  scatterPlotsWithoutResponse.setVisible(false);
                  scatterPlotsBothExplanatory.setVisible(false);
                  termSelectionLabel.setVisible(false);
                  }
                }
                
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    String plotSelection = "";                       
                    if (residualsHistogram.isSelected()) plotSelection = residualsHistogram.getText();
                    else if (normalQQResiduals.isSelected()) plotSelection = normalQQResiduals.getText();
                    else if (residualsVsFitted.isSelected()) plotSelection = residualsVsFitted.getText();
                    else if (scaleLocation.isSelected()) plotSelection = scaleLocation.getText();
                    else if (cooksDistance.isSelected()) plotSelection = cooksDistance.getText();
                    else if (residualsVsLeverage.isSelected()) plotSelection = residualsVsLeverage.getText();
                    else if (residualsVsResponse.isSelected()) plotSelection = residualsVsResponse.getText();
                    else if (fittedVsExplanatorySimple.isSelected()) plotSelection = fittedVsExplanatorySimple.getText();
                    else if (fittedVsExplanatory.isSelected()) plotSelection = fittedVsExplanatory.getText();
                    else if (fittedVsActual.isSelected()) plotSelection = fittedVsActual.getText();
                    else if (actualVsFitted.isSelected()) plotSelection = actualVsFitted.getText();
                    else if (actualAndFittedvsTimeOrObs.isSelected()) plotSelection = actualAndFittedvsTimeOrObs.getText();
                    else if (regression3D.isSelected()) plotSelection = regression3D.getText();
                    else if (confidenceEllipseInt.isSelected()) plotSelection = confidenceEllipseInt.getText();
                    else if (confidenceEllipseBothInt.isSelected()) plotSelection = confidenceEllipseBothInt.getText();
                    else if (accuracyVsCutoff.isSelected()) plotSelection = accuracyVsCutoff.getText();
                    else if (spinogramOne.isSelected()) plotSelection = spinogramOne.getText();
                    else if (spinogramChoose.isSelected()) plotSelection = spinogramChoose.getText();
                    else if (confidenceEllipse.isSelected()) plotSelection = confidenceEllipse.getText();
                    else if (confidenceEllipseBoth.isSelected()) plotSelection = confidenceEllipseBoth.getText();
                    else if (effectPlotsAll.isSelected()) plotSelection = effectPlotsAll.getText();
                    else if (effecPlotOne.isSelected()) plotSelection = effecPlotOne.getText();
                    else if (effecPlotSimple.isSelected()) plotSelection = effecPlotSimple.getText(); 
                    else if (partialRegressionPlots.isSelected()) plotSelection = partialRegressionPlots.getText();
                    else if (partialRegressionPlot.isSelected()) plotSelection = partialRegressionPlot.getText();
                    else if (simpleRegressionPlot.isSelected()) plotSelection = simpleRegressionPlot.getText();
                    else if (partiallResidualPlots.isSelected()) plotSelection = partiallResidualPlots.getText();
                    else if (partiallResidualPlot.isSelected()) plotSelection = partiallResidualPlot.getText();
                    else if (scatterPlotsWithResponse.isSelected()) plotSelection = scatterPlotsWithResponse.getText();
                    else if (scatterPlotsWithOnlyResponse.isSelected()) plotSelection = scatterPlotsWithOnlyResponse.getText();
                    else if (scatterPlotsWithoutResponse.isSelected()) plotSelection = scatterPlotsWithoutResponse.getText();
                    else if (scatterPlotsBothExplanatory.isSelected()) plotSelection = scatterPlotsBothExplanatory.getText();
                    else if (indivEffectsHistogram.isSelected()) plotSelection = indivEffectsHistogram.getText();
                    else if (indivEffectsNormalQQ.isSelected()) plotSelection = indivEffectsNormalQQ.getText();
                    else if (responseTimeIndivStrat.isSelected()) plotSelection = responseTimeIndivStrat.getText();
                    else if (simpleRegPlotByIndiv.isSelected()) plotSelection = simpleRegPlotByIndiv.getText();
                    else if (rocCurve.isSelected()) plotSelection = rocCurve.getText();
                    else if (partiallRegPlotsByIndiv.isSelected()) plotSelection = partiallRegPlotsByIndiv.getText();
                
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
                       //Object[] plotTerms = termsList.getSelectedValuesList().toArray();
             	       Object[] plotTerms = termsList.getSelectedValues();
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
		}
		
	}

}