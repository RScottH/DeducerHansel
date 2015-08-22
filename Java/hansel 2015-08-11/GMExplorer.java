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
  ModelExplorer.java, GLMExplorerPostHoc.java, GLMExplorer.java, and PlotBuilder.java, found in the Deducer package
 and
  ExampleDialog.java, found in the DeducerPlugInExample package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-22.
 */

package hansel;

import java.awt.Window;
import java.awt.Frame;
import javax.swing.JFrame;
import org.rosuda.REngine.JRI.JRIEngine;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JList; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.models.ModelExplorer;
import org.rosuda.deducer.models.GLMModel;
import org.rosuda.deducer.models.RModel;
import org.rosuda.deducer.models.ModelPlotPanel; 
import org.rosuda.deducer.models.GLMBuilder;
import org.rosuda.deducer.models.GLMExplorerOptions;
import org.rosuda.deducer.models.GLMExplorerPostHoc;
import org.rosuda.deducer.models.GLMExplorerExport;
import org.rosuda.deducer.models.GLMExplorerMeans;
import org.rosuda.deducer.models.GLMExplorerPlots;
import org.rosuda.deducer.models.GLMExplorerTests;
import org.rosuda.deducer.widgets.ButtonGroupWidget;

import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import org.rosuda.deducer.widgets.TextAreaWidget;

public class GMExplorer extends JFrame implements WindowListener{
        public Font font14 = new Font("serif", Font.TRUETYPE_FONT, 14);
        public Font font13 = new Font("serif", Font.TRUETYPE_FONT, 13);
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        public boolean noReactiontoAction;
	protected JPanel topPanel;
	protected JPanel bottomPanel;
        protected JPanel modelFormulaParamaterized;
        protected JScrollPane modelFormulaScroller;
        protected JTextArea modelFormulaText;
	protected JTextArea preview; 
        public JTextArea previewClassic;
        public JTextArea previewClassicSmall;
        public JTextPane previewTP;
        public JTextPane previewSmallTP;
        protected JTextArea previewSmall;
        public JCheckBox classicView ;
        protected JTextArea addexp;
	public JPanel generalTab;
        protected JButton changeLSvariable;
	protected JButton plots;
        protected JButton diagnosticsButton;
        protected JButton cointegrationVariable1Button;
        protected JButton regressionFunctionPlotButton;
        protected JButton partialregressionPlotButton;
        protected JButton regressionPlotsButton;
        protected JButton spinogramsButton;
        protected JButton cointRelButton;
        protected JButton cointRelsButton;
        protected JButton cointRelSCButton;
        protected JButton accuracyAndROCcurvesButton;
        protected Boolean multipleExplVars;
        protected JButton residualsVsFitButton;
        protected JButton residualsMapButton;
        protected JButton plotsvsObsButton;
        protected JButton plotsvsTimeButton;
        protected JButton plotsvsTimeExtendedButton;
   	protected JButton otherPlotsButton;
        protected JButton PlotEdit;


                                                      
        protected JButton editButton;
        protected JButton iplotButton;
        protected JButton idLabelButton;
        protected Boolean idLAbelButtonEnabled = false;
	protected JButton postHoc;
	protected JButton means;
	protected JButton export;
        protected JButton more;
        protected JButton residTimeExplorer;
        protected JButton outputTimeExplorer;
        protected JButton modelOLSExplorer;
        protected JButton cancelRemove;
        protected JButton cancelKeep;
	protected JButton update;
        protected JLabel typeLabel;
        protected JComboBox glmvcovtype;
        protected JComboBox tobitvcovtype;
        protected JComboBox plmvcovtype;
        protected JComboBox stslsvcovtype;
        protected JComboBox gstslsvcovtype;
	protected JLabel modelFormula;
        protected TextAreaWidget modelFormula2;
	protected JPanel okayCancelPanel;
	protected HelpButton help;
        protected JButton confint;
	protected JButton tests;
       // protected JButton spatialTests;
        protected JButton causalityTests;
	protected JButton options;
        protected JButton anova;
        protected JButton analysisOfDeviance;
        protected JButton diagnosticTests;
        
        protected JButton textView;
	protected JTabbedPane tabs;
	protected JScrollPane previewScroller;
        protected JScrollPane previewScrollerSmall;
        protected JScrollPane previewClassicScroller;
        protected JScrollPane previewClassicScrollerSmall;
        protected JScrollPane addexpScroller;
	public JPanel previewPanel;
        public JPanel previewPanelSmall;
        public JPanel previewClassicPanel;
        public JPanel previewClassicPanelSmall;
        
        protected JTextArea previewProcessing; 
        public JPanel previewPanelProcessing;
        protected JScrollPane previewScrollerProcessing;
        public JTextPane previewTPProcessing;     
        
        public JPanel previewPanelSmallProcessing;
        
        public JPanel previewClassicPanelProcessing;
        protected JScrollPane previewClassicScrollerProcessing;
        public JTextArea previewClassicProcessing;
        
        protected JPanel addexpPanel;
	public JPanel middlePanel;
        public String generalTabTitle="General";
        public String previewPanelTitle = "Initial Estimation Results";
        public String fittedValues;
        public String actualValues;
        public String residualsText;
        
	protected ActionListener generalListener = new ModelListener();
    
    
    /***********/
	protected GMModel model;
        protected GMModel model2 = new GMModel();
	protected RModel pre;
        protected String[] ae;
        protected String[] s;
        protected Boolean[] efeplotchoices;
	protected ModelPlotPanel diagnosticTab;
        protected ModelPlotPanel requestedPlot;
        protected ModelPlotPanel requestedPlotTab;
        protected JPanel irfTab;
        protected JPanel irfPanel;
      	protected JPanel PlotsTab;
        protected JPanel PlotsPanel2;
        protected JPanel PlotsPanel3;
        protected JPanel PlotsPanel4;
        protected JPanel PlotsPanel5;
        protected JPanel PlotsPanel6;
        protected JPanel cointRelsTab;
        protected JPanel cointRelsPanel;
        protected JPanel cointRelsPanelShort;
        protected JPanel termsPanel;
        
        protected ModelPlotPanel predictionTab;
	protected ModelPlotPanel termTab;
        protected ModelPlotPanel plotvsTimeORObsPanel;
	protected ModelPlotPanel addedTab;
        protected ModelPlotPanel addexpTab;
        protected ModelPlotPanel cointRelscontent;
        protected ModelPlotPanel cointRelscontentShort;
	protected IconButton assumpHomo;
	protected IconButton assumpFunc;
	protected IconButton assumpN;
        protected String firstFourPlotTerms;
        protected String modelMethodCall;
        protected String depvarOld;
        protected String NameSuffix="";
        protected JPanel commandsLogTab;       
        protected JPanel commandsLogPanel;
        protected JScrollPane commandsLogScroller;
        protected JScrollPane termsScroller;
        protected JTextArea commandsLogText;
        protected JTextArea termsTestText;
        protected JButton irfButton; 
        protected ModelPlotPanel irfPanelcontent;
        
        public JPanel vecUnrestrictedTab;
        public JPanel vecUnrestrictedClassicPanel;
        public JScrollPane  vecUnrestrictedClassicScroller;
        public JTextArea vecUnrestrictedClassic;
        
        public JPanel marginalEffectsTab;
        public JPanel marginalEffectsPanel;
        public JScrollPane marginalEffectsScroller;
        public JTextArea marginalEffects;
        
        public JPanel oddsRatiosTab;
        public JPanel oddsRatiosPanel;
        public JScrollPane oddsRatiosScroller;
        public JTextArea oddsRatios;
        
        public JPanel vecRestrictedTab;
        public JPanel vecRestrictedClassicPanel;
        public JScrollPane  vecRestrictedClassicScroller;
        public JTextArea vecRestrictedClassic;
        
        public JPanel vecRestrictedClassicPanelSmall;
        public JScrollPane  vecRestrictedClassicScrollerSmall;
        public JTextArea vecRestrictedClassicSmall;
        
        public JPanel vecRestrictedAddexpPanel;
        public JScrollPane  vecRestrictedAddexpScroller;
        public JTextArea vecRestrictedAddexp;
        
        
        protected JLabel rankRestrictionLabel;
         protected JLabel rankRestrictionLabel2;
        protected JComboBox rankRestriction;
        public JButton vecExploreVAR;
        public JButton vecTests;
        
        protected ButtonGroupWidget cointTestType;
        
        
        public String[] preClassicAndNot;
        protected JList termsList;
        
        public String hanselEnvAndModelName;
        
        public String firstFourTerms ="";
        protected Boolean regFnPlotPreviouslyClicked = false;
        protected Boolean partialResidsPreviouslyClicked = false;
        protected Boolean plotsvsObsOrTimePreviouslyClicked = false;
        
        protected GMModel threadMod;
        protected Boolean threadUseCurrentModel;
        
        
        public ThreadRunModel threadRunModel;
        
        
        public String plotType3;
        
        public float sizeAdjuster = 1;
        
        public float pointSymbolSizePanel3 = 0.8f;
        public String mainTitlePanel3 = "No Title Given";
        public String yLabelPanel3 = "No Y-Label Given";
        public String xLabelPanel3 = "No X-Label Given";
        
        public float pointSizePanel5 = 0.0f;
        public String mainTitlePanel5 = "No Title Given";
        public String yLabelDataPanel5 = "No Y-Label Given";
        public String xLabelDataPanel5 = "No X-Label Given";
        
        public String rowNamesOrDatesLabel = "";
        
        public String ltext= "";
        public String confIntvl95="";

         protected DefaultComboBoxModel rankRestrictionsAvailable  = new DefaultComboBoxModel(
				new String[] {"choose",
                                              "1 equation",
                                              "2 equations",
                                              "3 equations",
                                              "4 equations",
                                              "5 equations",
                                              "6 equations",
                                              "7 equations",
                                              "8 equations",
                                              "9 equations",
                                              "10 equations"});
        
        protected DefaultComboBoxModel tobitvcovsExplorer  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0",
                                               "HAC"
                                              });
        protected DefaultComboBoxModel glmvcovsExplorer  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0",
                                               "HC1",
                                               "HC2",
                                               "HC3",
                                               "HC4",
                                               "HC4m",
                                               "HC5",
                                               "HAC"
                                              });
        protected DefaultComboBoxModel plmvcovsExplorer = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                                "arellano,HC0,group",
                                                "arellano,HC1,group",
                                                "arellano,HC2,group",
                                                "arellano,HC3,group",  
                                                "arellano,HC4,group", 
                                                "arellano,HC0,time",
                                                "arellano,HC1,time",
                                                "arellano,HC2,time",
                                                "arellano,HC3,time",  
                                                "arellano,HC4,time",
                                                "Beck & K,HC0,group",
                                                "Beck & K,HC1,group",
                                                "Beck & K,HC2,group",
                                                "Beck & K,HC3,group",  
                                                "Beck & K,HC4,group", 
                                                "Beck & K,HC0,time",
                                                "Beck & K,HC1,time",
                                                "Beck & K,HC2,time",
                                                "Beck & K,HC3,time",  
                                                "Beck & K,HC4,time",
                                                "Beck&K,HC0,group,diag",
                                                "Beck&K,HC1,group,diag",
                                                "Beck&K,HC2,group,diag",
                                                "Beck&K,HC3,group,diag",  
                                                "Beck&K,HC4,group,diag", 
                                                "Beck&K,HC0,time,diag",
                                                "Beck&K,HC1,time,diag",
                                                "Beck&K,HC2,time,diag",
                                                "Beck&K,HC3,time,diag",  
                                                "Beck&K,HC4,time,diag",
                                                "Driscoll&K,HC0", 
                                                "Driscoll&K,HC1", 
                                                "Driscoll&K,HC2", 
                                                "Driscoll&K,HC3", 
                                                "Driscoll&K,HC4", 
                                                "Driscoll&K,HC0,maxlag=1",
                                                "Driscoll&K,HC1,maxlag=1",
                                                "Driscoll&K,HC2,maxlag=1",
                                                "Driscoll&K,HC3,maxlag=1",
                                                "Driscoll&K,HC4,maxlag=1",
                                                "Driscoll&K,HC0,maxlag=2",
                                                "Driscoll&K,HC1,maxlag=2",
                                                "Driscoll&K,HC2,maxlag=2",
                                                "Driscoll&K,HC3,maxlag=2",
                                                "Driscoll&K,HC4,maxlag=2",
                                                "Driscoll&K,HC0,maxlag=3",
                                                "Driscoll&K,HC1,maxlag=3",
                                                "Driscoll&K,HC2,maxlag=3",
                                                "Driscoll&K,HC3,maxlag=3",
                                                "Driscoll&K,HC4,maxlag=3",
                                                "Driscoll&K,HC0,maxlag=4",
                                                "Driscoll&K,HC1,maxlag=4",
                                                "Driscoll&K,HC2,maxlag=4",
                                                "Driscoll&K,HC3,maxlag=4",
                                                "Driscoll&K,HC4,maxlag=4",
                                                "white1,HC0,group",
                                                "white1,HC1,group",
                                                "white1,HC2,group",
                                                "white1,HC3,group",
                                                "white1,HC4,group", 
                                                "white1,HC0,time",
                                                "white1,HC1,time",
                                                "white1,HC2,time",
                                                "white1,HC3,time", 
                                                "white1,HC4,time",
                                                "white2,HC0,group",
                                                "white2,HC1,group",
                                                "white2,HC2,group",
                                                "white2,HC3,group", 
                                                "white2,HC4,group", 
                                                "white2,HC0,time",
                                                "white2,HC1,time",
                                                "white2,HC2,time",
                                                "white2,HC3,time",  
                                                "white2,HC4,time"          
                                              });
      protected DefaultComboBoxModel stslsvcovsExplorer  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0",
                                               "HC1",
                                              });
       protected DefaultComboBoxModel gstslsvcovsExplorer  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0"
                                              });
        protected DefaultComboBoxModel titlesExplorer  = new DefaultComboBoxModel(
                				new String[] {"Choose change below","main title",
                                               "y-axis title",
                                               "x-axis title"
                                              });
        protected DefaultComboBoxModel dataPresentationsExplorer  = new DefaultComboBoxModel(
                				new String[] {"Choose change below",
                                               "larger point symbols","smaller point symbols",
                                               "point labels-row names","point labels-y values","point labels-x values","point labels-none",
                                               "color black","color gray","color red","color orange","color yellow","color green","color blue",
                                               "color purple","color violet","color brown",
                                              });

        protected DefaultComboBoxModel fittedPresentationsExplorer  = new DefaultComboBoxModel(
                				new String[] {"Choose change below","95% confidence interval","no 95% confidence interval","fit line-thicker","fit line-thinner",
                                                  "fit color black", "fit color gray","fit color red", "fit color orange","fit color yellow",
                                                  "fit color green","fit color blue","fit color purple","fit color violet","fit color brown",                                                
                                                  "fit line solid","fit line dashed","fit line dotdash","fit line longdash","fit line twodash"
                                              });
        
	GMExplorer(GMModel mod, String caller,float sizeAdj){
                super();
                sizeAdjuster = sizeAdj;
                initGUI(sizeAdjuster);
		this.setTitle("Explorer: "+mod.EstimationMethod);
                this.setFont(font12);
                modelMethodCall = caller;
  
                setModel(mod,false);
                initTabs(mod);

		this.addWindowListener(this);

                noReactiontoAction = false; 

                threadMod =mod;
                threadRunModel = new ThreadRunModel();
                threadRunModel.start();
	}
		protected void initGUI(float sizeAdj) {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				{
					topPanel = new JPanel();
                                        BorderLayout topPanelLayout = new BorderLayout();
					getContentPane().add(topPanel, new AnchorConstraint(0, 989, 58, 0, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					topPanel.setLayout(null);
					topPanel.setPreferredSize(new java.awt.Dimension(700, 35));
					{
						modelFormula = new JLabel();
						topPanel.add(modelFormula);
						modelFormula.setBounds(282, -1, 418, 36);
						modelFormula.setFont(font10);
					}
                                                              
                                        
				}
				{
					middlePanel = new JPanel();
					BorderLayout middlePanelLayout = new BorderLayout();
					getContentPane().add(middlePanel, new AnchorConstraint(35, 989, 62, 0, AnchorConstraint.ANCHOR_ABS, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL));
					middlePanel.setLayout(middlePanelLayout);
					middlePanel.setPreferredSize(new java.awt.Dimension(700, 506));
					{
						tabs = new JTabbedPane ();
						middlePanel.add(tabs, BorderLayout.CENTER);
						tabs.setPreferredSize(new java.awt.Dimension(700, 501));
						{
							generalTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab(generalTabTitle, null, generalTab, null);
                                                        
                                                        
							generalTab.setLayout(generalTabLayout);
							generalTab.setPreferredSize(new java.awt.Dimension(695, 475));
                                                        
                                                        {
								previewPanelProcessing = new JPanel();
								generalTab.add(previewPanelProcessing, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								previewPanelProcessing.setLayout(previewPanelLayout);
								previewPanelProcessing.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewPanelProcessing.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									previewScrollerProcessing = new JScrollPane();
									previewPanelProcessing.add(previewScrollerProcessing, BorderLayout.CENTER);
									
										previewProcessing = new JTextArea();                                                 
                                                                        {
									        previewTPProcessing = new JTextPane();	
                                                                                previewTPProcessing.setContentType("text/html");
                                                                                String message = "Processing...<br>"
                                                                                                + "<a href=\"" + "\">"
                                                                                                + "</a>"; 
                                                                                previewTPProcessing.setFont(new java.awt.Font("MS Sans Serif",1,14));
                                                                                previewTPProcessing.setText(message); 
										previewScrollerProcessing.setViewportView(previewTPProcessing);
										previewTPProcessing.setFont(new java.awt.Font("Monospaced",0,11));
										previewTPProcessing.setEditable(true);
									}
     
								}
                                                                previewPanelProcessing.setVisible(true);
							}
                                                        
							{
								previewPanel = new JPanel();
								generalTab.add(previewPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								previewPanel.setLayout(previewPanelLayout);
								previewPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									previewScroller = new JScrollPane();
									previewPanel.add(previewScroller, BorderLayout.CENTER);
									{
                                                                            
										preview = new JTextArea();
                                                                                
                                                                        {
									        previewTP = new JTextPane();	
                                                                                previewTP.setContentType("text/html");
                                                                                String message = "Starting text<br>"
                                                                                                + "Copyright 2007-2010 Yohann Martineau<br>"
                                                                                                + "<a href=\"" + "\">"
                                                                                                + "</a>"; 
                                                                                previewTP.setFont(new java.awt.Font("MS Sans Serif",1,14));
                                                                                previewTP.setText(message); 
										previewScroller.setViewportView(previewTP);
										previewTP.setFont(new java.awt.Font("Monospaced",0,11));
										previewTP.setEditable(true);
									}
                                                                                
									}
                                                                        
								}
                                                                previewPanel.setVisible(false);
							}
                                                        {
								previewPanelSmall = new JPanel();
								generalTab.add(previewPanelSmall, new AnchorConstraint(1, 731, 500, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelSmallLayout = new BorderLayout();
								previewPanelSmall.setLayout(previewPanelSmallLayout);
								previewPanelSmall.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewPanelSmall.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									previewScrollerSmall = new JScrollPane();
									previewPanelSmall.add(previewScrollerSmall, BorderLayout.CENTER);
									{
										previewSmall = new JTextArea();
                                                                                
                                                                                previewSmallTP = new JTextPane();
                                                                                previewSmallTP.setContentType("text/html");
                                                                                String message = "starting text<br>"
                                                                                                + "<a href=\"" + "\">"
                                                                                                + "</a>"; 
                                                                                previewSmallTP.setFont(new java.awt.Font("MS Sans Serif",1,14));
                                                                                previewSmallTP.setText(message); 
										previewScrollerSmall.setViewportView(previewSmallTP);
										previewSmallTP.setFont(new java.awt.Font("Monospaced",0,11));
										previewSmallTP.setEditable(true);
                                                                                
									}
								}
                                                                previewPanelSmall.setVisible(false);
							}
                                                        
                                                        {
								previewClassicPanelProcessing = new JPanel();
								generalTab.add(previewClassicPanelProcessing, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayoutProcessing = new BorderLayout();
								previewClassicPanelProcessing.setLayout(previewPanelLayoutProcessing);
								previewClassicPanelProcessing.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewClassicPanelProcessing.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									previewClassicScrollerProcessing = new JScrollPane();
									previewClassicPanelProcessing.add(previewClassicScrollerProcessing, BorderLayout.CENTER);
									{
                                                                            
										previewClassicProcessing = new JTextArea();
                                                                                previewClassicProcessing.setText("hello!");
										previewClassicScrollerProcessing.setViewportView(previewClassicProcessing);
										previewClassicProcessing.setFont(new java.awt.Font("Monospaced",0,11));
										previewClassicProcessing.setEditable(false);
									}

								}
                                                                previewClassicPanelProcessing.setVisible(true);
                                                                
							}
                                                                                                                {
								previewClassicPanel = new JPanel();
								generalTab.add(previewClassicPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								previewClassicPanel.setLayout(previewPanelLayout);
								previewClassicPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewClassicPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									previewClassicScroller = new JScrollPane();
									previewClassicPanel.add(previewClassicScroller, BorderLayout.CENTER);
									{
                                                                            
										previewClassic = new JTextArea();
                                                                                previewClassic.setText("hello!");
										previewClassicScroller.setViewportView(previewClassic);
										previewClassic.setFont(new java.awt.Font("Monospaced",0,11));
										previewClassic.setEditable(false);
									}

								}
                                                                previewClassicPanel.setVisible(false);
                                                                
							}
                                                        {
								previewClassicPanelSmall = new JPanel();
								generalTab.add(previewClassicPanelSmall, new AnchorConstraint(1, 731, 500, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelSmallLayout = new BorderLayout();
								previewClassicPanelSmall.setLayout(previewPanelSmallLayout);
								previewClassicPanelSmall.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								previewClassicPanelSmall.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									previewClassicScrollerSmall = new JScrollPane();
									previewClassicPanelSmall.add(previewClassicScrollerSmall, BorderLayout.CENTER);
									{
										previewClassicSmall = new JTextArea();
                                                                         	previewClassicScrollerSmall.setViewportView(previewClassicSmall);
										previewClassicSmall.setFont(new java.awt.Font("Monospaced",0,11));
										previewClassicSmall.setEditable(false);
                                                                               
                                                                                
									}
								}
                                                                previewClassicPanelSmall.setVisible(false);
							}
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        {
								addexpPanel = new JPanel();
								generalTab.add(addexpPanel, new AnchorConstraint(510, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								addexpPanel.setLayout(previewPanelLayout);
								addexpPanel.setBorder(BorderFactory.createTitledBorder("Results from additional exploration"));
								addexpPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									addexpScroller = new JScrollPane();
									addexpPanel.add(addexpScroller, BorderLayout.CENTER);
                                                                        
									{
										addexp = new JTextArea();
                                                                                addexp.setText(" ");
										addexpScroller.setViewportView(addexp);
										addexp.setFont(new java.awt.Font("Monospaced",0,11));
										addexp.setEditable(false);
									}
								}
                                                                addexpPanel.setVisible(false);
							}
                                                        {
                                                                classicView = new JCheckBox();
                                                                generalTab.add(classicView, new AnchorConstraint(1, 895, 38, 770, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                classicView.setText("Classic View");
                                                                classicView.setFont(font12);
                                                                classicView.setPreferredSize(new java.awt.Dimension(115, 18));
                                                                classicView.addActionListener(generalListener);
                                                        }
			                               {
                                                                typeLabel = new JLabel();
                                                                generalTab.add(typeLabel, new AnchorConstraint(31, 863, 38, 800, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                typeLabel.setText("Robust standard error:");
                                                                typeLabel.setFont(font11);
                                                        	typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	typeLabel.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                      {
                                                        	glmvcovtype = new JComboBox();
                                                                generalTab.add(glmvcovtype, new AnchorConstraint(61, 990, 38, 800, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                glmvcovtype.setFont(font12);
                                                                glmvcovtype.setModel(glmvcovsExplorer);
                                                                glmvcovtype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                glmvcovtype.addActionListener(generalListener);
                                                        }
                                                       {
                                                        	tobitvcovtype = new JComboBox();
                                                                generalTab.add(tobitvcovtype, new AnchorConstraint(61, 990, 38, 800, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                tobitvcovtype.setFont(font12);
                                                                tobitvcovtype.setModel(tobitvcovsExplorer);
                                                                tobitvcovtype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                tobitvcovtype.addActionListener(generalListener);
                                                        }

                                                        {
                                                                plmvcovtype = new JComboBox();
                                                                generalTab.add(plmvcovtype, new AnchorConstraint(61, 990, 38, 800, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                plmvcovtype.setModel(plmvcovsExplorer);
                                                                plmvcovtype.setFont(font12);
                                                                plmvcovtype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                plmvcovtype.addActionListener(generalListener);
                                                        }
                                                         {
                                                        	stslsvcovtype = new JComboBox();
                                                                generalTab.add(stslsvcovtype, new AnchorConstraint(61, 990, 38, 800, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                stslsvcovtype.setFont(font12);
                                                                stslsvcovtype.setModel(stslsvcovsExplorer);
                                                                stslsvcovtype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                stslsvcovtype.addActionListener(generalListener);
                                                        }
                                                        {
                                                        	gstslsvcovtype = new JComboBox();
                                                                generalTab.add(gstslsvcovtype, new AnchorConstraint(61, 990, 38, 800, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                                gstslsvcovtype.setFont(font12);
                                                                gstslsvcovtype.setModel(gstslsvcovsExplorer);
                                                                gstslsvcovtype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                gstslsvcovtype.addActionListener(generalListener);
                                                        }
 
                                                      
  							{
								diagnosticTests = new JButton();
								generalTab.add(diagnosticTests, new AnchorConstraint(70, 990, 201, 820,
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
 
                                                                diagnosticTests.setFont(font12);
								diagnosticTests.setText("Diagnostic Statistics & Tests");
								diagnosticTests.setPreferredSize(new java.awt.Dimension(180, 26));
								diagnosticTests.addActionListener(generalListener);
							}                                                      
			
							{
								confint = new JButton();
								generalTab.add(confint, new AnchorConstraint(101, 990, 266, 800, 
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								confint.setText("Confidence Intervals");
                                                                confint.setFont(font12);
								confint.setPreferredSize(new java.awt.Dimension(180, 26));
								confint.addActionListener(generalListener);
							}
                                                        {
								tests = new JButton();
								generalTab.add(tests, new AnchorConstraint(131, 990, 266, 800, 
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								tests.setText("Coefficient Tests");
                                                                tests.setFont(font12);
								tests.setPreferredSize(new java.awt.Dimension(180, 26));
								tests.addActionListener(generalListener);
							}
                                                      //  {
							//	spatialTests = new JButton();
							//	generalTab.add(spatialTests, new AnchorConstraint(161, 990, 266, 800, 
							//			AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
							//			AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
							//	spatialTests.setText("Spatial Tests");
                                                        //        spatialTests.setFont(font12);
							//	spatialTests.setPreferredSize(new java.awt.Dimension(180, 26));
							//	spatialTests.addActionListener(generalListener);
							//}
                                                        
                                                        {
								causalityTests = new JButton();
								generalTab.add(causalityTests, new AnchorConstraint(161, 990, 266, 800, 
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								causalityTests.setText("Causality Tests");
                                                                causalityTests.setFont(font12);
								causalityTests.setPreferredSize(new java.awt.Dimension(180, 26));
								causalityTests.addActionListener(generalListener);
							}
							{
								means = new JButton();
							} 
                                                        {
								anova = new JButton();
								generalTab.add(anova, new AnchorConstraint(194, 990, 460, 800,
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								anova.setText("ANOVA");
                                                                anova.setFont(font12);
								anova.setPreferredSize(new java.awt.Dimension(180, 26));
								anova.addActionListener(generalListener);
							}
                                                         {
								analysisOfDeviance = new JButton();
								generalTab.add(analysisOfDeviance, new AnchorConstraint(194, 990, 460, 800,
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								analysisOfDeviance.setText("Analysis of Deviance");
                                                                analysisOfDeviance.setFont(font12);
								analysisOfDeviance.setPreferredSize(new java.awt.Dimension(180, 26));
								analysisOfDeviance.addActionListener(generalListener);
							}
							{
								export = new JButton();
								generalTab.add(export, new AnchorConstraint(225, 990, 80, 800, 
										AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								export.setText("Export");
                                                                export.setFont(font12);
								export.setPreferredSize(new java.awt.Dimension(180, 26));
								export.addActionListener(generalListener);
							}

                                                        {
								textView = new JButton();
								generalTab.add(textView, new AnchorConstraint(295, 990, -1, -1,
                                                                                AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								textView.setText("Text View Toggle");
                                                                textView.setFont(font12);
								textView.setPreferredSize(new java.awt.Dimension(180, 26));
								textView.addActionListener(generalListener);
                                                        }
						} 
					}
				}
				{
					bottomPanel = new JPanel();
					AnchorLayout bottomPanelLayout = new AnchorLayout();
					getContentPane().add(bottomPanel, new AnchorConstraint(898, 989, 1000, 0, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					bottomPanel.setLayout(bottomPanelLayout);
					bottomPanel.setPreferredSize(new java.awt.Dimension(700, 62));
                                        {
						help = new HelpButton("");
						bottomPanel.add(help, new AnchorConstraint(379, 66, 600, 17, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						help.setPreferredSize(new java.awt.Dimension(32, 32));
						help.addActionListener(generalListener);
                                                help.setEnabled(false);
					}
                                        {
                                                more = new JButton();
                                                bottomPanel.add(more, new AnchorConstraint(30, 350, 814, 539, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                more.setText("Initial Selections Page");
                                                more.setFont(font12);
                                                more.setPreferredSize(new java.awt.Dimension(144, 35));
                                                more.addActionListener(generalListener);
                                        }

                                        {
                                                residTimeExplorer = new JButton();
                                                bottomPanel.add(residTimeExplorer, new AnchorConstraint(30, 650, 814, 539, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                residTimeExplorer.setText("Residuals -> Time Series Explorer");
                                                residTimeExplorer.setFont(font12);
                                                residTimeExplorer.setPreferredSize(new java.awt.Dimension(200, 25));
                                                residTimeExplorer.addActionListener(generalListener);
                                        }
                                        {
                                                outputTimeExplorer = new JButton();
                                                bottomPanel.add(outputTimeExplorer, new AnchorConstraint(30, 650, 814, 539, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                outputTimeExplorer.setText("Output -> Time Series Explorer");
                                                outputTimeExplorer.setFont(font12);
                                                outputTimeExplorer.setPreferredSize(new java.awt.Dimension(200, 25));
                                                outputTimeExplorer.addActionListener(generalListener);
                                        }
                                        {
                                                modelOLSExplorer = new JButton();
                                                bottomPanel.add(modelOLSExplorer, new AnchorConstraint(500, 650, 814, 539, 
                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                modelOLSExplorer.setText("Model -> OLS Explorer");
                                                modelOLSExplorer.setFont(font12);
                                                modelOLSExplorer.setPreferredSize(new java.awt.Dimension(200, 25));
                                                modelOLSExplorer.addActionListener(generalListener);
                                        }
                                       {
                                        cancelKeep = new JButton();
                                        bottomPanel.add(cancelKeep, new AnchorConstraint(30, 840, 780, 539, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                        cancelKeep.setText("Cancel:Keep");
                                        cancelKeep.setFont(font12);
                                        cancelKeep.setPreferredSize(new java.awt.Dimension(100, 35));
                                        cancelKeep.addActionListener(generalListener);
                                       }
                                       {
                                        cancelRemove = new JButton();
                                        bottomPanel.add(cancelRemove, new AnchorConstraint(30, 998, 780, 539, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                        cancelRemove.setText("Cancel:Remove");
                                        cancelRemove.setFont(font12);
                                        cancelRemove.setPreferredSize(new java.awt.Dimension(110, 35));
                                        cancelRemove.addActionListener(generalListener);
                                       }

                                       
                        PlotsTab = new JPanel();
			tabs.addTab("Plots", null, PlotsTab, null);
			PlotsTab.setLayout(thisLayout);
			PlotsTab.setPreferredSize(new java.awt.Dimension(695, 475));
                                                        {
								diagnosticsButton = new JButton();
								PlotsTab.add(diagnosticsButton, new AnchorConstraint(10, 150, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								diagnosticsButton.setText("Diagnostics");
                                                                diagnosticsButton.setFont(font12);
								diagnosticsButton.setPreferredSize(new java.awt.Dimension(90, 26));
								diagnosticsButton.addActionListener(generalListener);
							}  

                                                        {
								regressionPlotsButton = new JButton();
								PlotsTab.add(regressionPlotsButton , new AnchorConstraint(10, 380, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								regressionPlotsButton .setText("Regression Plots");
                                                                regressionPlotsButton .setFont(font12);
								regressionPlotsButton .setPreferredSize(new java.awt.Dimension(160, 26));
								regressionPlotsButton .addActionListener(generalListener);
                                                                regressionPlotsButton .setVisible(false);
							}  
                                                        
                                                         
                                                        {
                                                                cointRelButton = new JButton();
								PlotsTab.add(cointRelButton, new AnchorConstraint(10, 380, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								cointRelButton.setText("Cointegrating Relation");
                                                                cointRelButton.setFont(font12);
								cointRelButton.setPreferredSize(new java.awt.Dimension(160, 26));
								cointRelButton.addActionListener(generalListener);
                                                                cointRelButton.setVisible(false);

							} 
                                                        {
                                                                cointRelsButton = new JButton();
								PlotsTab.add(cointRelsButton, new AnchorConstraint(10, 380, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								cointRelsButton.setText("Cointegrating Relation(s)");
                                                                cointRelsButton.setFont(font12);
								cointRelsButton.setPreferredSize(new java.awt.Dimension(160, 26));
								cointRelsButton.addActionListener(generalListener);
                                                                cointRelsButton.setVisible(false);

							} 
                                                        {
                                                                cointRelSCButton = new JButton();
								PlotsTab.add(cointRelSCButton, new AnchorConstraint(10, 650, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								cointRelSCButton.setText("Coint. Rel. Short-term correct");
                                                                cointRelSCButton.setFont(font12);
								cointRelSCButton.setPreferredSize(new java.awt.Dimension(190, 26));
								cointRelSCButton.addActionListener(generalListener);
                                                                cointRelSCButton.setVisible(false);
							} 
                                                        {
								accuracyAndROCcurvesButton = new JButton();
								PlotsTab.add(accuracyAndROCcurvesButton, new AnchorConstraint(10, 600, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								accuracyAndROCcurvesButton.setText("Accuracy & ROC curves");
                                                                accuracyAndROCcurvesButton.setFont(font12);
								accuracyAndROCcurvesButton.setPreferredSize(new java.awt.Dimension(155, 26));
								accuracyAndROCcurvesButton.addActionListener(generalListener);
                                                                accuracyAndROCcurvesButton.setVisible(false);
							}  
                                                        {
								spinogramsButton = new JButton();
								PlotsTab.add(spinogramsButton, new AnchorConstraint(10, 550, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								spinogramsButton.setText("Spinograms");
                                                                spinogramsButton.setFont(font12);
								spinogramsButton.setPreferredSize(new java.awt.Dimension(100, 26));
								spinogramsButton.addActionListener(generalListener);
                                                                spinogramsButton.setVisible(false);
							} 

                                                        {
								regressionFunctionPlotButton = new JButton();
								PlotsTab.add(regressionFunctionPlotButton, new AnchorConstraint(10, 380, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								regressionFunctionPlotButton.setText("Regression Function Plot");
                                                                regressionFunctionPlotButton.setFont(font12);
								regressionFunctionPlotButton.setPreferredSize(new java.awt.Dimension(160, 26));
								regressionFunctionPlotButton.addActionListener(generalListener);
                                                                regressionFunctionPlotButton.setVisible(false);
							}  
                                                      
                                                        {
								partialregressionPlotButton = new JButton();
								PlotsTab.add(partialregressionPlotButton, new AnchorConstraint(10, 380, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								partialregressionPlotButton.setText("Partial Regression Plot");
                                                                partialregressionPlotButton.setFont(font12);
								partialregressionPlotButton.setPreferredSize(new java.awt.Dimension(160, 26));
								partialregressionPlotButton.addActionListener(generalListener);
                                                                partialregressionPlotButton.setVisible(false);
							}   

                                                        {
								residualsVsFitButton = new JButton();
								PlotsTab.add(residualsVsFitButton, new AnchorConstraint(10, 600, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								residualsVsFitButton.setText("Residuals vs Fitted");
                                                                residualsVsFitButton.setFont(font12);
								residualsVsFitButton.setPreferredSize(new java.awt.Dimension(150, 26));
								residualsVsFitButton.addActionListener(generalListener);
                                                                residualsVsFitButton.setVisible(false);
							}   
                                                        {
								residualsMapButton = new JButton();
								PlotsTab.add(residualsMapButton, new AnchorConstraint(10, 600, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								residualsMapButton.setText("Residuals Map");
                                                                residualsMapButton.setFont(font12);
								residualsMapButton.setPreferredSize(new java.awt.Dimension(150, 26));
								residualsMapButton.addActionListener(generalListener);
                                                                residualsMapButton.setVisible(false);
							}   
                                                        {
								plotsvsObsButton = new JButton();
								PlotsTab.add(plotsvsObsButton, new AnchorConstraint(10, 840, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotsvsObsButton.setText("Residuals vs Time or Obs");
                                                                plotsvsObsButton.setFont(font12);
								plotsvsObsButton.setPreferredSize(new java.awt.Dimension(170, 26));
								plotsvsObsButton.addActionListener(generalListener);
                                                                plotsvsObsButton.setVisible(false);
							}
                                                        {
								plotsvsTimeButton = new JButton();
								PlotsTab.add(plotsvsTimeButton, new AnchorConstraint(10, 840, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotsvsTimeButton.setText("Plots vs Time");
                                                                plotsvsTimeButton.setFont(font12);
								plotsvsTimeButton.setPreferredSize(new java.awt.Dimension(120, 26));
								plotsvsTimeButton.addActionListener(generalListener);
                                                                plotsvsTimeButton.setVisible(false);
							} 
                                                        {
								plotsvsTimeExtendedButton = new JButton();
								PlotsTab.add(plotsvsTimeExtendedButton, new AnchorConstraint(10, 840, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotsvsTimeExtendedButton.setText("More Plots vs Time, incl. Cointegrating Relations");
                                                                plotsvsTimeExtendedButton.setFont(font12);
								plotsvsTimeExtendedButton.setPreferredSize(new java.awt.Dimension(320, 26));
								plotsvsTimeExtendedButton.addActionListener(generalListener);
                                                                plotsvsTimeExtendedButton.setVisible(false);
							} 
                                                        {
								otherPlotsButton = new JButton();
								PlotsTab.add(otherPlotsButton, new AnchorConstraint(10, 976, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								otherPlotsButton.setText("Other Plots");
                                                                otherPlotsButton.setFont(font12);
								otherPlotsButton.setPreferredSize(new java.awt.Dimension(90, 26));
								otherPlotsButton.addActionListener(generalListener);
                                                                otherPlotsButton.setVisible(false);
							}        
                                                     
                                                         {
								PlotEdit = new JButton();
								PlotsTab.add(PlotEdit, new AnchorConstraint(100, 970, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								PlotEdit.setText("Plot Edit");
                                                                PlotEdit.setFont(font12);
								PlotEdit.setPreferredSize(new java.awt.Dimension(120, 26));
								PlotEdit.addActionListener(generalListener);
                                                                PlotEdit.setVisible(false);
							}  
                                                         

                                                        
                                                        {
                                                            editButton = new IconButton("/icons/edit_16.png","Plot Edit",generalListener,"Plot Edit");
                                                                editButton.setContentAreaFilled(false);
                                                                PlotsTab.add(editButton, new AnchorConstraint(840, 970, 117, 930, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                editButton.setPreferredSize(new java.awt.Dimension(21, 21));
                                                                editButton.setVisible(false);
                                                        } 
                                                        {
								idLabelButton = new JButton();
								PlotsTab.add(idLabelButton, new AnchorConstraint(890, 970, 117, 895, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
								idLabelButton.setText("id-label");
                                                                idLabelButton.setFont(font12);
								idLabelButton.setPreferredSize(new java.awt.Dimension(110, 26));
								idLabelButton.addActionListener(generalListener);
                                                                idLabelButton.setVisible(false); 
                                                               
							}  
                                                        {
								iplotButton = new JButton();
								PlotsTab.add(iplotButton, new AnchorConstraint(940, 970, 117, 910, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
								iplotButton.setText("iplot");
                                                                iplotButton.setFont(font12);
								iplotButton.setPreferredSize(new java.awt.Dimension(60, 26));
								iplotButton.addActionListener(generalListener);
                                                                iplotButton.setVisible(false);
							}  
                                                        
                                                        
                                                        {

                                                                PlotsPanel2 = new JPanel();
								PlotsTab.add(PlotsPanel2, new AnchorConstraint(60, 992, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								PlotsPanel2.setLayout(morePlotsPanelLayout);
								PlotsPanel2.setBorder(BorderFactory.createTitledBorder("Plot Area"));							
                                                                PlotsPanel2.setFont(font12);
                                                                PlotsPanel2.setVisible(true);
							}
                                                        
                                                       {

                                                                PlotsPanel3 = new JPanel();
								PlotsTab.add(PlotsPanel3, new AnchorConstraint(60, 900, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								PlotsPanel3.setLayout(morePlotsPanelLayout);
								PlotsPanel3.setBorder(BorderFactory.createTitledBorder("Plot Area"));							
                                                                PlotsPanel3.setFont(font12);
                                                                PlotsPanel3.setVisible(false);
							}
                                                        {

                                                                PlotsPanel4 = new JPanel();
								PlotsTab.add(PlotsPanel4, new AnchorConstraint(60, 900, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								PlotsPanel4.setLayout(morePlotsPanelLayout);
								PlotsPanel4.setBorder(BorderFactory.createTitledBorder("Plot Area"));							
                                                                PlotsPanel4.setFont(font12);
                                                                PlotsPanel4.setVisible(false);
							}
                                                        
                                                        
                                                        {

                                                                PlotsPanel5 = new JPanel();
								PlotsTab.add(PlotsPanel5, new AnchorConstraint(60, 900, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								PlotsPanel5.setLayout(morePlotsPanelLayout);
								PlotsPanel5.setBorder(BorderFactory.createTitledBorder("Plot Area"));							
                                                                PlotsPanel5.setFont(font12);
                                                                PlotsPanel5.setVisible(false);
							}
                                                       {

                                                                PlotsPanel6 = new JPanel();
								PlotsTab.add(PlotsPanel6, new AnchorConstraint(60, 900, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								PlotsPanel6.setLayout(morePlotsPanelLayout);
								PlotsPanel6.setBorder(BorderFactory.createTitledBorder("Plot Area"));							
                                                                PlotsPanel6.setFont(font12);
                                                                PlotsPanel6.setVisible(false);
							}
                    
                                                        


			                                
                                                        {
								irfButton = new JButton();
								PlotsTab.add(irfButton, new AnchorConstraint(10, 576, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								irfButton.setText("Impulse Response Functions");
                                                                irfButton.setFont(font12);
								irfButton.setPreferredSize(new java.awt.Dimension(200, 26));
								irfButton.addActionListener(generalListener);
							        irfButton.setVisible(false);
                                                        }
                                           
				}
			}
			this.setSize(750, Math.round(725*sizeAdj));
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
        
        
        
        
        public void initTabs(GMModel mod){
                AnchorLayout generalTabLayout = new AnchorLayout();
		try{    
                    if (model.EstimationMethod.equals("Binary Logit")||model.EstimationMethod.equals("Binary Probit")) {
                        marginalEffectsTab = new JPanel();
			tabs.addTab("Marginal Effects", null, marginalEffectsTab, null);
			marginalEffectsTab.setLayout(generalTabLayout);
			marginalEffectsTab.setPreferredSize(new java.awt.Dimension(695, 475));
        
                                                        {
								marginalEffectsPanel = new JPanel();
								marginalEffectsTab.add(marginalEffectsPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout vecUnrestrictedClassicPanelLayout = new BorderLayout();
								marginalEffectsPanel.setLayout(vecUnrestrictedClassicPanelLayout);
								marginalEffectsPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								marginalEffectsPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									marginalEffectsScroller = new JScrollPane();
									marginalEffectsPanel.add(marginalEffectsScroller, BorderLayout.CENTER);
									{
                                                                            
										marginalEffects = new JTextArea();
                                                                                marginalEffects.setText("hello!");
										marginalEffectsScroller.setViewportView(marginalEffects);
										marginalEffects.setFont(new java.awt.Font("Monospaced",0,12));
										marginalEffects.setEditable(false);
									}

								}
                                                                marginalEffectsPanel.setVisible(true);
							}
                        if (model.EstimationMethod.equals("Binary Logit")) {                                
                                            oddsRatiosTab = new JPanel();
                                            tabs.addTab("Odds Ratios", null, oddsRatiosTab, null);
                                            oddsRatiosTab.setLayout(generalTabLayout);
                                            oddsRatiosTab.setPreferredSize(new java.awt.Dimension(695, 475));
                  

                                                        
                                                        {
								oddsRatiosPanel = new JPanel();
								oddsRatiosTab.add(oddsRatiosPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout vecUnrestrictedClassicPanelLayout = new BorderLayout();
								oddsRatiosPanel.setLayout(vecUnrestrictedClassicPanelLayout);
								oddsRatiosPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								oddsRatiosPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									oddsRatiosScroller = new JScrollPane();
									oddsRatiosPanel.add(oddsRatiosScroller, BorderLayout.CENTER);
									{
                                                                            
										oddsRatios = new JTextArea();
                                                                                oddsRatios.setText("hello!");
										oddsRatiosScroller.setViewportView(oddsRatios);
										oddsRatios.setFont(new java.awt.Font("Monospaced",0,12));
										oddsRatios.setEditable(false);
									}

								}
                                                                oddsRatiosPanel.setVisible(true);
							} 
                        }

                    }
                          if (model.efeglmextra.previousEstimation){
                              
                         String modelName = model.efeglmextra.PriorModelName;  
                            String modelvarNames = "";
                            String[] modelvarNamesSplit;
                            
                            try {
                            if (model.method.equals("VAR")||model.method.equals("vec2var") )
                                modelvarNames = Deducer.eval("paste(colnames("+modelName+"$y)[1:length(colnames("+modelName+"$y))],\";\",collapse=\"\")").asString();
                            else if (model.method.equals("ca.jo"))
                                modelvarNames = Deducer.eval("paste(colnames("+modelName+"@x)[1:length(colnames("+modelName+"@x))],\";\",collapse=\"\")").asString();
                            else
                                modelvarNames = Deducer.eval("paste(names("+modelName+"$model)[2:length(names("+modelName+"$model))],\";\",collapse=\"\")").asString();
                            modelvarNamesSplit =  modelvarNames.split(" ;");
                            model.termsArray = new String[modelvarNamesSplit.length];
                            model.termsFinalStage = new DefaultListModel();
                            for(int i=0;i<(modelvarNamesSplit.length);i++){
                                model.termsArray[i] = modelvarNamesSplit[i];
 			    model.termsFinalStage.addElement(model.termsArray[i]);
                             }
                                }catch(Exception e){
                            new ErrorMsg(e);
                            }
                            }
                  if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")){
                          plotsvsObsButton.setVisible(true);
                          otherPlotsButton.setVisible(true);
                  } else if (model.method.equals("tobit")) {
                          plotsvsObsButton.setVisible(true);
                          otherPlotsButton.setVisible(true);
                  } else if (model.method.equals("VAR")||model.method.equals("vec2var")){
                          irfButton.setVisible(true);
                          plotsvsTimeButton.setVisible(true);
                          classicView.setVisible(false);
                          /***u1u1*/
                  } else  if (model.method.equals("ca.jo")){
                           cointRelsButton.setVisible(true);
                           cointRelSCButton.setVisible(true);
                           plotsvsTimeButton.setVisible(true);
                  }else if (model.method.equals("ca.po")||model.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                           plotsvsTimeExtendedButton.setVisible(true);
                  }else if (model.method.equals("cajolst")) {
                            plotsvsTimeButton.setVisible(true);
                   
                   }

                  if (model.method.equals("VAR")||model.method.equals("vec2var")){
                           plotsvsTimeButton.setVisible(true);
                  
                  } else if (model.method.equals("plm")||model.method.equals("pvcm")||model.method.equals("pggls")||
                      model.method.equals("pgmm")||model.method.equals("pcce")||model.method.equals("pmg")
                      ||model.method.equals("spml")||model.method.equals("spgm")||model.method.equals("spreml")) {

                           plotsvsTimeButton.setVisible(true);
                           otherPlotsButton.setVisible(true);

                  } else if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                    ||model.method.equals("GMerrorsar")
                    ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                    ||model.method.equals("lagmess"))   {
                           /*regressionFunctionPlotButton.setVisible(true);*/
                           plotsvsObsButton.setVisible(true);
                           residualsVsFitButton.setVisible(true);
                           otherPlotsButton.setVisible(true);
                  } 
                   
                   if ((model.method.equals("ca.jo")||model.method.equals("cajolst"))) {
                                                     {
                                        cointTestType = new ButtonGroupWidget("Test Type",
						new String[]{"Trace test","Eigenvalue test"});
                                        cointTestType.setSelected("Trace test");
                                        if (model.method.equals("cajolst")){
                                                cointTestType.setVisible(false);
                                        }
                                        generalTab.add(cointTestType, new AnchorConstraint(11, 931, 805, 780, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cointTestType.setPreferredSize(new java.awt.Dimension(150, 90));
                                        cointTestType.addListener(generalListener);
                                        classicView.setVisible(false);
                                }   
          
                        vecUnrestrictedTab = new JPanel();
			tabs.addTab("Unrestricted VECM", null, vecUnrestrictedTab, null);
			vecUnrestrictedTab.setLayout(generalTabLayout);
			vecUnrestrictedTab.setPreferredSize(new java.awt.Dimension(695, 475));
                                                        {
								vecUnrestrictedClassicPanel = new JPanel();
								vecUnrestrictedTab.add(vecUnrestrictedClassicPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout vecUnrestrictedClassicPanelLayout = new BorderLayout();
								vecUnrestrictedClassicPanel.setLayout(vecUnrestrictedClassicPanelLayout);
								vecUnrestrictedClassicPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								vecUnrestrictedClassicPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									vecUnrestrictedClassicScroller = new JScrollPane();
									vecUnrestrictedClassicPanel.add(vecUnrestrictedClassicScroller, BorderLayout.CENTER);
									{
                                                                            
										vecUnrestrictedClassic = new JTextArea();
                                                                                /*addexp = new JTextArea();*/
                                                                                vecUnrestrictedClassic.setText("hello!");
										vecUnrestrictedClassicScroller.setViewportView(vecUnrestrictedClassic);
										vecUnrestrictedClassic.setFont(font11);
										vecUnrestrictedClassic.setEditable(false);
									}

								}
                                                                vecUnrestrictedClassicPanel.setVisible(true);
							}
                   }                                 
                   if (model.method.equals("ca.jo")) {                                        
                        vecRestrictedTab = new JPanel();
			tabs.addTab("VECM with given number of cointegrating vectors", null, vecRestrictedTab, null);
			vecRestrictedTab.setLayout(generalTabLayout);
			vecRestrictedTab.setPreferredSize(new java.awt.Dimension(695, 475));
                  
                                                        
                                                        {
								vecRestrictedClassicPanel = new JPanel();
								vecRestrictedTab.add(vecRestrictedClassicPanel, new AnchorConstraint(1, 731, 975, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout vecRestrictedClassicPanelLayout = new BorderLayout();
								vecRestrictedClassicPanel.setLayout(vecRestrictedClassicPanelLayout);
								vecRestrictedClassicPanel.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								vecRestrictedClassicPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									vecRestrictedClassicScroller = new JScrollPane();
									vecRestrictedClassicPanel.add(vecRestrictedClassicScroller, BorderLayout.CENTER);
									{
                                                                            
										vecRestrictedClassic = new JTextArea();
                                                                                addexp = new JTextArea();
                                                                                vecRestrictedClassic.setText("Please choose the number of assumed cointegrating vectors to the right.");
										vecRestrictedClassicScroller.setViewportView(vecRestrictedClassic);
										vecRestrictedClassic.setFont(font12);
										vecRestrictedClassic.setEditable(false);
									}

								}
                                                                vecRestrictedClassicPanel.setVisible(true);
							}   
                                                                                                                {
								vecRestrictedClassicPanelSmall = new JPanel();
								vecRestrictedTab.add(vecRestrictedClassicPanelSmall, new AnchorConstraint(1, 731, 500, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelSmallLayout = new BorderLayout();
								vecRestrictedClassicPanelSmall.setLayout(previewPanelSmallLayout);
								vecRestrictedClassicPanelSmall.setBorder(BorderFactory.createTitledBorder(previewPanelTitle));
								vecRestrictedClassicPanelSmall.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									vecRestrictedClassicScrollerSmall = new JScrollPane();
									vecRestrictedClassicPanelSmall.add(vecRestrictedClassicScrollerSmall, BorderLayout.CENTER);
									{
										vecRestrictedClassicSmall = new JTextArea();
                                                                         	vecRestrictedClassicScrollerSmall.setViewportView(vecRestrictedClassicSmall);
										vecRestrictedClassicSmall.setFont(font12);
										vecRestrictedClassicSmall.setEditable(false);     
									}
								}
                                                                vecRestrictedClassicPanelSmall.setVisible(false);
							}
                                                        
                                                        {
								vecRestrictedAddexpPanel = new JPanel();
								vecRestrictedTab.add(vecRestrictedAddexpPanel, new AnchorConstraint(510, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								vecRestrictedAddexpPanel.setLayout(previewPanelLayout);
								vecRestrictedAddexpPanel.setBorder(BorderFactory.createTitledBorder("Results from additional exploration"));
								vecRestrictedAddexpPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									vecRestrictedAddexpScroller = new JScrollPane();
									vecRestrictedAddexpPanel.add(vecRestrictedAddexpScroller, BorderLayout.CENTER);
                                                                        
									{
										vecRestrictedAddexp = new JTextArea();
                                                                                vecRestrictedAddexp.setText("hello!");
										vecRestrictedAddexpScroller.setViewportView(vecRestrictedAddexp);
										vecRestrictedAddexp.setFont(font12);
										vecRestrictedAddexp.setEditable(false);
									}
								}
                                                                vecRestrictedAddexpPanel.setVisible(false);
							}
                                                       
                                {
					rankRestrictionLabel = new JLabel();
                                        vecRestrictedTab.add(rankRestrictionLabel, new AnchorConstraint(1, 990, 86, 830, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					rankRestrictionLabel.setText("Number of");
					rankRestrictionLabel.setPreferredSize(new java.awt.Dimension(300, 20));
				}  
                                
                                {
					rankRestrictionLabel2 = new JLabel();
                                        vecRestrictedTab.add(rankRestrictionLabel2, new AnchorConstraint(19, 990, 106, 780, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					rankRestrictionLabel2.setText("cointegrating equations");
					rankRestrictionLabel2.setPreferredSize(new java.awt.Dimension(300, 20));
				} 
                               {
					rankRestriction = new JComboBox();
					vecRestrictedTab.add(rankRestriction, new AnchorConstraint(80, 950, 105, 800, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					rankRestriction.setModel(rankRestrictionsAvailable);
                                        rankRestriction.setSelectedIndex(0);
					rankRestriction.setPreferredSize(new java.awt.Dimension(100, 21));
					rankRestriction.addActionListener(generalListener);
                                        this.model.varOptions.rankRestriction = (String)this.rankRestriction.getSelectedItem().toString();
				}    
                                {
                                        vecExploreVAR = new JButton();
                                        vecRestrictedTab.add(vecExploreVAR , new AnchorConstraint(80, 999, 211, 750,
                                                        AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                        vecExploreVAR .setText("Associated VAR Model");
                                        vecExploreVAR .setPreferredSize(new java.awt.Dimension(190, 26));
                                        vecExploreVAR .addActionListener(generalListener);
				} 
                               {
                                        vecTests = new JButton();
                                        vecRestrictedTab.add(vecTests, new AnchorConstraint(131, 976, 266, 800, 
                                                        AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, 
                                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                        vecTests.setText("Coefficient Tests");
                                        vecTests.setPreferredSize(new java.awt.Dimension(170, 26));
                                        vecTests.addActionListener(generalListener);
                                }  
                 } 
                                       
                        commandsLogTab = new JPanel();
			tabs.addTab("Commands Log", null, commandsLogTab, null);
			commandsLogTab.setLayout(generalTabLayout);
			commandsLogTab.setPreferredSize(new java.awt.Dimension(995, 475));
                        {
								commandsLogPanel = new JPanel();
								commandsLogTab.add(commandsLogPanel, new AnchorConstraint(10, 990, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								commandsLogPanel.setLayout(previewPanelLayout);
                                                                commandsLogPanel.setSize(975, 975);
								{
									commandsLogScroller = new JScrollPane();
									commandsLogPanel.add(commandsLogScroller, BorderLayout.CENTER);
                                                                        
									{
										commandsLogText = new JTextArea();
										commandsLogScroller.setViewportView(commandsLogText);
										commandsLogText.setFont(font14); 
										commandsLogText.setEditable(false);
									}
								}
							}
		}catch(Exception e){
			new ErrorMsg(e);
		}
                previewPanelProcessing.setVisible(true);
	}
	
	protected void initAssumptions(){
		{
			assumpN =  new IconButton("/icons/N_assump.png","Large Sample",null,"Large Sample");
			topPanel.add(assumpN);
			assumpN.setBounds(12, 8, 27, 27);
		}
		{
			assumpFunc = new IconButton("/icons/func_assump.png","Correct Functional Form",
					null,"Correct Functional Form");
			topPanel.add(assumpFunc);
			assumpFunc.setBounds(44, 8, 27, 27);
		}
		{
			assumpHomo = new IconButton("/icons/outlier_assump.png","No Outliers",null,"No Outliers");
			topPanel.add(assumpHomo);
			assumpHomo.setBounds(76, 8, 27, 27);
		}
	}
	
	public void setModel(GMModel mod, boolean useCurrentModel){
          if (useCurrentModel) {
              
                this.model = mod;
                runModel(mod,useCurrentModel);
          }else
          { 
              this.model = new GMModel();
          GMModel modelD = mod;
            this.model.efeglmextra.previousEstimation=mod.efeglmextra.previousEstimation.booleanValue();
            this.model.method = new String(mod.method);
            this.model.EstimationMethod = new String(mod.EstimationMethod);
            this.model.efeglmextra.classicView = mod.efeglmextra.classicView.booleanValue();
            this.model.data = new String(mod.data);
            if (mod.efeglmextra.previousEstimation){           
                this.model.dName = new String(mod.dName);
                if (this.model.method.equals("plm")) {
                   this.model.panelModel = true;
                   this.model.existsTS = false;
                } else {
                   this.model.panelModel.equals(false);
                   this.model.existsTS = new Boolean(mod.existsTS);
                }
                this.model.efeglmextra.PriorModelName = new String(mod.efeglmextra.PriorModelName);
                this.model.dependentVar = new String(mod.dependentVar);
                this.model.glmvcov = "No correction";
                this.model.tobitvcov = "No correction";
                this.model.plmvcov = "No correction";
                this.model.stslsvcov = "No correction";
                this.model.gstslsvcov = "No correction";
                this.model.start_of_original_data = new String(mod.start_of_original_data);
                this.model.frequency_of_original_data = new String(mod.frequency_of_original_data);
            } else {
                this.model.termsArray = new String[mod.terms.size()];
                        for(int i=0;i<mod.terms.size();i++){
                            this.model.termsArray[i] = mod.terms.get(i).toString();
                        }
                this.model.outcomeVars = model2.outcomeVars; 
                this.model.subset =new String(mod.subset);
                
                
                this.model.PanelEstMethod = mod.PanelEstMethod.booleanValue();
                this.model.efeglmextra.vecExploreVAR = mod.efeglmextra.vecExploreVAR.booleanValue();
                this.model.efeglmextra.classicView = mod.efeglmextra.classicView.booleanValue();
                if (this.model.PanelEstMethod)
                   this.model.panelIndex = new String(mod.panelIndex);
                else {
                   this.model.start = new String(mod.start);
                   this.model.end = new String(mod.end);             
                }
                if (mod.method.equals("lm")||(mod.method.equals("dynlm"))||mod.method.equals("glm")){
                   this.model.family = new String(mod.family);
                }
                 if (mod.method.equals("ivreg")||this.model.PanelEstMethod){    
                this.model.instrumentsArray = new String[mod.efeglmextra.instrumentsD.size()];
                        for(int i=0;i<mod.efeglmextra.instrumentsD.size();i++){
                            this.model.instrumentsArray[i] = mod.efeglmextra.instrumentsD.get(i).toString();
                        }
                 }   
                 if (mod.method.equals("GMerrorsar")
                           ||mod.method.equals("lagmess"))  {     
                    this.model.formula=Deducer.makeFormula(mod.outcomeVars, mod.terms);
                    this.model.efeglmextra.SpatialDurbin= new Boolean(mod.efeglmextra.SpatialDurbin);
                    
                    this.model.efeglmextra.zeroPolicyType=new String(mod.efeglmextra.zeroPolicyType);
                    this.model.efeglmextra.spatialMethodType=new String(mod.efeglmextra.spatialMethodType);
                    this.model.efeglmextra.notQuiet = new Boolean(mod.efeglmextra.notQuiet);
                    this.model.efeglmextra.verbose = new Boolean(mod.efeglmextra.verbose);
                    
                 }
                  if (mod.method.equals("errorsarlm")||mod.method.equals("lagsarlm")||mod.method.equals("sacsarlm")
                           ||mod.method.equals("spautolm"))  {     
                    this.model.formula=Deducer.makeFormula(mod.outcomeVars, mod.terms);
                    this.model.efeglmextra.SpatialDurbin= new Boolean(mod.efeglmextra.SpatialDurbin);
                    this.model.glmvcov = new String(mod.glmvcov);
                    this.glmvcovsExplorer.setSelectedItem(this.model.glmvcov);
                    
                    this.model.efeglmextra.zeroPolicyType=new String(mod.efeglmextra.zeroPolicyType);
                    this.model.efeglmextra.spatialMethodType=new String(mod.efeglmextra.spatialMethodType);
                    this.model.efeglmextra.zerosInitval = new Boolean(mod.efeglmextra.zerosInitval);
                    this.model.efeglmextra.hess = new Boolean(mod.efeglmextra.hess);
                    this.model.efeglmextra.quiet = new Boolean(mod.efeglmextra.quiet);
                    this.model.efeglmextra.notQuiet = new Boolean(mod.efeglmextra.notQuiet);
                    this.model.efeglmextra.verbose = new Boolean(mod.efeglmextra.verbose);
                    
                 } else  if (mod.method.equals("stsls")||mod.method.equals("gstsls"))  {     
                    this.model.formula=Deducer.makeFormula(mod.outcomeVars, mod.terms);
                    this.model.efeglmextra.SpatialDurbin= new Boolean(mod.efeglmextra.SpatialDurbin);
                    if (mod.method.equals("stsls"))
                      this.model.stslsvcov = new String(mod.stslsvcov);
                    else
                      this.model.gstslsvcov = new String(mod.gstslsvcov);  
                    this.stslsvcovsExplorer.setSelectedItem(this.model.stslsvcov);
                    
                    this.model.efeglmextra.zeroPolicyType=new String(mod.efeglmextra.zeroPolicyType);
                    this.model.efeglmextra.spatialMethodType=new String(mod.efeglmextra.spatialMethodType);
                    this.model.efeglmextra.zerosInitval = new Boolean(mod.efeglmextra.zerosInitval);
                    this.model.efeglmextra.hess = new Boolean(mod.efeglmextra.hess);
                    this.model.efeglmextra.quiet = new Boolean(mod.efeglmextra.quiet);
                    this.model.efeglmextra.notQuiet = new Boolean(mod.efeglmextra.notQuiet);
                    this.model.efeglmextra.verbose = new Boolean(mod.efeglmextra.verbose);
                 } else if (mod.method.equals("spml")||mod.method.equals("spreml")||mod.method.equals("spgm"))  {     
                    this.model.formula=Deducer.makeFormula(mod.outcomeVars, mod.terms);
                    this.model.efeglmextra.zerosInitval = new Boolean(mod.efeglmextra.zerosInitval);
                    this.model.efeglmextra.hess = new Boolean(mod.efeglmextra.hess);
                    this.model.efeglmextra.quiet = new Boolean(mod.efeglmextra.quiet);            
                 }
                 
                 
                if (mod.method.equals("lm")||(mod.method.equals("dynlm"))||mod.method.equals("glm")||mod.method.equals("ivreg")||mod.method.equals("tobit")||this.model.PanelEstMethod){
                if (mod.weights.isEmpty())
                    this.model.modelWeights = "";
                else
                   this.model.modelWeights = mod.weights.getElementAt(0).toString();
                if (mod.method.equals("tobit"))
                    this.model.tobitvcov = new String(mod.tobitvcov);
                else
                    this.model.glmvcov = new String(mod.glmvcov);
                this.glmvcovsExplorer.setSelectedItem(this.model.glmvcov);
                this.model.efeglmextra.interceptIncluded = mod.efeglmextra.interceptIncluded.booleanValue();
                this.model.formula=Deducer.makeFormula(mod.outcomeVars, mod.terms);
                if (mod.efeglmextra.interceptIncluded){
                                if ( mod.terms.size()==0) { 
                                  this.model.formula =  this.model.formula + "1";  
                                }
                              }            
                              else {
                                   this.model.formula =  this.model.formula + "-1";
                                 }
                
                if (mod.method.equals("spml")){
                     this.model.efeglmextra.spatialLagDepIncluded = mod.efeglmextra.spatialLagDepIncluded.booleanValue();
                   }
                
                
                
                
                if (mod.method.equals("tobit")){
                     this.model.efeglmextra.rightLimitText =new String(mod.efeglmextra.rightLimitText);
                     this.model.efeglmextra.leftLimitText =new String(mod.efeglmextra.leftLimitText);
                   }
                }
                
                
                
                if ((mod.method.equals("VAR"))) {
                    this.model.varOptions.maxLag = new String(mod.varOptions.maxLag);
                    this.model.varOptions.seasonalDummies = new String(mod.varOptions.seasonalDummies);
                     this.model.efeglmextra.exogVars= new String[mod.efeglmextra.exogVarsD.size()];
                        for(int i=0;i<mod.efeglmextra.exogVarsD.size();i++){
                            this.model.efeglmextra.exogVars[i] = mod.efeglmextra.exogVarsD.get(i).toString();
                        }                 
                }
                if (mod.method.equals("ca.jo")||model.method.equals("cajolst")) {
                    this.model.varOptions.maxLag = new String(mod.varOptions.maxLag);
                    this.model.varOptions.seasonalDummies = new String(mod.varOptions.seasonalDummies);
                     this.model.efeglmextra.cointExogVars= new String[mod.efeglmextra.cointExogVarsD.size()];
                        for(int i=0;i<mod.efeglmextra.cointExogVarsD.size();i++){
                            this.model.efeglmextra.cointExogVars[i] = mod.efeglmextra.cointExogVarsD.get(i).toString();
                        }                 
                }
    
                if  (mod.method.equals("VAR")){
                    this.model.varOptions.constantTrend = new String(mod.varOptions.constantTrend);
                    this.model.varOptions.infoCriterion = new String(mod.varOptions.infoCriterion);
                    this.model.efeglmextra.manualVariablesInput = new String(mod.efeglmextra.manualVariablesInput);              
                }else if (mod.method.equals("ca.jo")||mod.method.equals("cajolst")){
                    this.model.varOptions.cointOption = new String(mod.varOptions.cointOption);
                    this.model.varOptions.seasonalDummies = new String(mod.varOptions.seasonalDummies);
                    this.model.efeglmextra.manualVariablesInput = new String(mod.efeglmextra.manualVariablesInput); 
                }else if (mod.method.equals("ca.po")){
                    this.model.efeglmextra.cointPOOptionChosen = new String(mod.efeglmextra.cointPOOptionChosen);
                    this.model.efeglmextra.cointPOLagChosen = new String(mod.efeglmextra.cointPOLagChosen);
                    this.model.efeglmextra.cointPOType = new String(mod.efeglmextra.cointPOType);
                    this.model.efeglmextra.manualVariablesInput = new String(mod.efeglmextra.manualVariablesInput); 
                }else if (mod.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                    this.model.efeglmextra.cointEGOptionChosen = new String(mod.efeglmextra.cointEGOptionChosen);
                    this.model.unitRootTestOptions.maxLagEG   = new String(mod.unitRootTestOptions.maxLagEG);
                    this.model.unitRootTestOptions.infoCriterion2 = new String(mod.unitRootTestOptions.infoCriterion2);
                    this.model.efeglmextra.manualVariablesInput = new String(mod.efeglmextra.manualVariablesInput); 
                }else if ((this.model.PanelEstMethod)||(mod.method.equals("cipstest"))) { 
                    
                    this.model.modeltype = new String(mod.modeltype);
                    
                    if (mod.method.equals("plm")){
                      this.model.plmPanelType = new String(mod.plmPanelType);
                      this.model.plmeffect = new String(mod.plmeffect);
                      this.model.panelIndex = new String(mod.panelIndex);
                      this.model.plmvcov = new String(mod.plmvcov);
                      this.plmvcovsExplorer.setSelectedItem(this.model.plmvcov);
                    }else if (mod.method.equals("pvcm")){
                      this.model.pvcmPanelType = new String(mod.pvcmPanelType);
                      this.model.pvcmeffect = new String(mod.pvcmeffect);
                    }else if (mod.method.equals("pggls")){
                      this.model.pgglsPanelType = new String(mod.pgglsPanelType);
                      this.model.pgglseffect = new String(mod.pgglseffect);
                    }else if (mod.method.equals("pgmm")){
                      this.model.pgmmPanelType = new String(mod.pgmmPanelType);
                      this.model.pgmmeffect = new String(mod.pgmmeffect);       
                    }else if (mod.method.equals("pcce")){
                      this.model.pgmmPanelType = new String(mod.pgmmPanelType);
                      this.model.efeglmextra.panelTrendIncluded = mod.efeglmextra.panelTrendIncluded.booleanValue();  
                    }else if (mod.method.equals("pmg")){                     
                      this.model.pgmmeffect = new String(mod.pgmmeffect);
                    }else if (mod.method.equals("spml")){  
                      this.model.spatialErrorPanel = new String(mod.spatialErrorPanel);
                      this.model.spmlPanelType = new String(mod.spmlPanelType);
                      this.model.spmleffect = new String(mod.spmleffect);
                      this.model.efeglmextra.zerosInitval = new Boolean(mod.efeglmextra.zerosInitval);
                      this.model.efeglmextra.hess = new Boolean(mod.efeglmextra.hess);
                      this.model.efeglmextra.quiet = new Boolean(mod.efeglmextra.quiet);
                      this.model.panelIndex = new String(mod.panelIndex);
                      this.plmvcovsExplorer.setSelectedItem(this.model.plmvcov);
                    }else if (mod.method.equals("spreml")){  
                      this.model.spatialErrorPanel = new String(mod.spatialErrorPanel);
                      this.model.spmlPanelType = new String(mod.spmlPanelType);
                      this.model.spremlErrors = new String(mod.spremlErrors);
                      this.model.efeglmextra.zerosInitval = new Boolean(mod.efeglmextra.zerosInitval);
                      this.model.efeglmextra.hess = new Boolean(mod.efeglmextra.hess);
                      this.model.efeglmextra.quiet = new Boolean(mod.efeglmextra.quiet);
                      this.model.panelIndex = new String(mod.panelIndex);
                    }else if (mod.method.equals("spgm")){
                      this.model.efeglmextra.spgmMethodType = new String(mod.efeglmextra.spgmMethodType);
                      this.model.spgmPanelType = new String(mod.spgmPanelType);
                      this.model.panelIndex = new String(mod.panelIndex);
                      this.model.spgmMoments = new String(mod.spgmMoments);
                      this.model.efeglmextra.spatialError = new Boolean(mod.efeglmextra.spatialError);
                      this.plmvcovsExplorer.setSelectedItem(this.model.plmvcov);
                    }else if (mod.method.equals("cipstest")){
                      this.model.cipsURTPanelType = new String(mod.cipsURTPanelType);
                 }
                }              
            }
          }
  
            if (!(useCurrentModel)) {
               previewPanelProcessing.setVisible(true);
            }
	}

       public class ThreadRunModel extends Thread {
           public void run(){
               runModel(threadMod,false); 
                if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")||model.method.equals("tobit")
                        ||model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                    ||model.method.equals("GMerrorsar")
                    ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                    ||model.method.equals("lagmess")){
                         
                    /*
                     * 
                     * if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")||model.method.equals("tobit")
                    ||model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                    ||model.method.equals("GMerrorsar")
                    ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                    ||model.method.equals("lagmess")){
                     */
                    
                    
                           if ((model.numExplanatoryVariables > 1)||model.interactionTerm) {
                               if ((model.EstimationMethod.equals("Binary Logit")||(model.EstimationMethod.equals("Binary Probit")||model.method.equals("tobit")))&!model.interactionTerm){
                                   regressionPlotsButton.setVisible(true);
                               } else {
                               multipleExplVars = true;
                               partialregressionPlotButton.setVisible(true);
                               if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                   ||model.method.equals("GMerrorsar")
                                   ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                   ||model.method.equals("lagmess"))  
                                     partialregressionPlotButton.setEnabled(false);
                               }
                               
                               
                           }else{
                               multipleExplVars = false;
                               regressionFunctionPlotButton.setVisible(true);
                               if ((model.numExplanatoryVariables == 0))
                                  regressionFunctionPlotButton.setEnabled(false);
                               }
                          
                          if (model.EstimationMethod.equals("Binary Logit")||(model.EstimationMethod.equals("Binary Probit"))){
                               accuracyAndROCcurvesButton.setVisible(true);
                          } else //if (!model.method.equals("tobit"))
                              residualsVsFitButton.setVisible(true);
                          
                          plotsvsObsButton.setVisible(true);
                          otherPlotsButton.setVisible(true);
                          
                  } 

           };
       }  
       
        public void runModel(GMModel mod,boolean useCurrentModel){
                  pre =model.run(true,pre,useCurrentModel);  // Here is the connection to GMModel 
                   preClassicAndNot =pre.preview.split("End Classic");
                   hanselEnvAndModelName =  Hansel.hanselEnv+ "$"+ pre.modelName;               
                this.typeLabel.setVisible(false);
                this.glmvcovtype.setVisible(false);
                this.tobitvcovtype.setVisible(false);
                this.plmvcovtype.setVisible(false);
                this.stslsvcovtype.setVisible(false);
                this.gstslsvcovtype.setVisible(false);
                this.residTimeExplorer.setVisible(true);
                this.outputTimeExplorer.setVisible(false);
                this.modelOLSExplorer.setVisible(false);
                //spatialTests.setVisible(false); //spatialTests currently not used at all.
                causalityTests.setVisible(false);
                
                if ((model.method.equals("lm")||(model.method.equals("dynlm")))&(!model.EstimationMethod.equals("Cointegration Test - Engle & Granger"))
                  ||model.method.equals("glm")||model.method.equals("ivreg")){
                   this.typeLabel.setVisible(true);
                   this.glmvcovtype.setVisible(true);
                   this.residTimeExplorer.setVisible(true);
                }
                else if (mod.method.equals("tobit")){
                   this.typeLabel.setVisible(true);
                   this.tobitvcovtype.setVisible(true);
                   this.residTimeExplorer.setVisible(true);
                }
                else if (mod.method.equals("errorsarlm")||mod.method.equals("lagsarlm")||mod.method.equals("sacsarlm")
                           ){
                   //this.typeLabel.setVisible(true);
                   //this.glmvcovtype.setVisible(true);
                   this.residTimeExplorer.setVisible(true);
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                }else if (mod.method.equals("GMerrorsar")||mod.method.equals("spautolm")||mod.method.equals("lagmess")
                           ){
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                 } else if (mod.method.equals("stsls")
                           ){
                   this.typeLabel.setVisible(true);
                   this.stslsvcovtype.setVisible(true);
                   this.residTimeExplorer.setVisible(true);
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                }
                 else if (mod.method.equals("gstsls")
                           ){
                   this.typeLabel.setVisible(true);
                   this.gstslsvcovtype.setVisible(true);
                   this.residTimeExplorer.setVisible(true);
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                }
                else if (model.method.equals("plm")){
                   this.typeLabel.setVisible(true);
                   this.plmvcovtype.setVisible(true);
                   if (model.plmPanelType.equals("pooled OLS")||model.plmPanelType.equals("within (\"fixed\") effects"))
                     this.modelOLSExplorer.setVisible(true);
                }
                 else if (model.method.equals("spml")){
                   //this.typeLabel.setVisible(true);
                   //this.plmvcovtype.setVisible(true);
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                   }
                 else if (model.method.equals("spreml ")||model.method.equals("spgm")){
                   this.confint.setVisible(false);
                   this.tests.setVisible(false);
                }
                else if (model.method.equals("vec2var")) {
                     this.outputTimeExplorer.setVisible(false);
                }
                else if (model.method.equals("VAR")) {
                    confint.setVisible(false);
                    causalityTests.setVisible(true);
                     this.outputTimeExplorer.setVisible(false);
                     this.modelOLSExplorer.setVisible(true); 
                }
                else if (model.method.equals("ca.jo")||model.method.equals("ca.po")) {
                    causalityTests.setVisible(false);
                     this.residTimeExplorer.setVisible(false);
                     this.outputTimeExplorer.setVisible(true);
                     this.modelOLSExplorer.setVisible(false); 
                }
                else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger")) {
                     this.residTimeExplorer.setVisible(false);
                     this.outputTimeExplorer.setVisible(true);
                     this.modelOLSExplorer.setVisible(true);  
                }
                
                if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("ivreg")){
                    anova.setVisible(true);
                    analysisOfDeviance.setVisible(false);
                } else if (model.method.equals("glm")||model.method.equals("tobit")){
                    anova.setVisible(false);
                    analysisOfDeviance.setVisible(true);
                } else {
                    anova.setVisible(false);
                    analysisOfDeviance.setVisible(false);
                }
                if (model.method.equals("ca.jo")||model.method.equals("ca.po")||model.method.equals("cajolst")
                        ||(model.EstimationMethod.equals("Cointegration Test - Engle & Granger")) ){
                    classicView.setVisible(false);
                    diagnosticTests.setVisible(false);
                    confint.setVisible(false);
                    tests.setVisible(false);
                    export.setVisible(true);
                    textView.setVisible(false);
                
                }
                else if ((model.method.equals("plm")&(!((model.modeltype.equals("pooling"))||(model.modeltype.equals("within")))))
                        ||model.method.equals("pvcm")||model.method.equals("pggls")||model.method.equals("pgmm")
                        ||model.method.equals("pcce")||model.method.equals("pmg")||model.method.equals("cipstest")
                        ||model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                        ||model.method.equals("GMerrorsar")
                        ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                        ||model.method.equals("lagmess") 
                        ||model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm")
                        ) {       
                    classicView.setVisible(false);
                    model.efeglmextra.classicView = true;
                    modelFormula.setText(pre.formula);
                    if (model.method.equals("pvcm")||model.method.equals("pgmm")){
                      confint.setVisible(false);
                      tests.setVisible(false);
                      if (model.method.equals("pgmm")){
                        export.setVisible(false); //removed since pgmm has lags, which makes export to original
                                                  //dataset complicated.
                      }
                    } 
                    
                }        
                else{  
                    modelFormula.setText(this.model.formula);
                }
       		previewTP.setText(preClassicAndNot[1]);
                
		previewTP.setCaretPosition(0);
                previewSmallTP.setText(preClassicAndNot[1]);
		previewSmallTP.setCaretPosition(0);
                if ((model.method.equals("ca.jo")||model.method.equals("cajolst"))) {
                  previewClassicPanel.setBorder(BorderFactory.createTitledBorder("Initial Results"));   
                }
                else { 
                  previewClassicPanel.setBorder(BorderFactory.createTitledBorder("Initial Estimation Results"));
                  tabs.setFont(font12);
                }
		this.previewClassic.setText(preClassicAndNot[0]);
                this.previewClassicPanel.setVisible(false);
                this.previewClassicPanel.setVisible(false);
                this.previewClassicPanel.isShowing();
		previewClassic.setCaretPosition(0);
       		this.previewClassicSmall.setText(preClassicAndNot[0]);
		this.previewClassicSmall.setCaretPosition(0);
                previewPanelProcessing.setVisible(false);
                previewClassicPanelProcessing.setVisible(false);
               
                
                if (model.efeglmextra.classicView){
                previewPanel.setVisible(false);
                previewPanelSmall.setVisible(false);
                previewClassicPanel.setVisible(true);
                previewClassicPanelSmall.setVisible(true);
                }
                else {
                previewPanel.setVisible(true);
                previewPanelSmall.setVisible(true);
                previewClassicPanel.setVisible(false);
                previewClassicPanelSmall.setVisible(false);
                };
                
                
                addexp.setCaretPosition(0);      
            
              previewPanelProcessing.setVisible(false);
              previewClassicPanelProcessing.setVisible(false);

              actualValues = "";
                if (model.method.equals("pgmm")) {
                   actualValues = "rapply("+model.modelName+"$model, function(x) x[,1])";
                } else {
                    if (model.method.equals("lm")||model.method.equals("plm")||model.method.equals("pvcm")||
                        model.method.equals("pggls")||model.method.equals("pcce")||model.method.equals("pmg")){
                         actualValues =   model.modelName+"$model$\""+model.dependentVar+"\"";
                   } else if (model.method.equals("dynlm")){
                         actualValues =   "as.numeric("+model.modelName+"$model$\""+model.dependentVar+"\")";
                   } else if (model.method.equals("gstsls")){
                         actualValues =   model.modelName+"$fitted +"+model.modelName+"$resid" ;
                   } else if (model.method.equals("GMerrorsar")){ 
                         actualValues =   model.modelName+"$lm.model$model$y";
                   } else if (model.method.equals("stsls")){
                         actualValues =   model.dName+"$"+model.dependentVar;
                   } else if (model.method.equals("tobit")){
                         actualValues =   model.modelName+"$y[,1]";
                    }else{
                         actualValues =   model.modelName+"$y";
                    } 
                }
                        
                   fittedValues = new String();
                    if (model.method.equals("lm")||model.method.equals("pgmm")||
                             model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                      ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                      ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                      ||model.method.equals("lagmess")||model.method.equals("spreml")){
                                    fittedValues ="fitted("+pre.modelName+")";
                                    if (model.method.equals("pgmm"))
                                       residualsText = "unlist(residuals("+model.modelName+"))";
                                    else
                                        residualsText = "residuals("+model.modelName+")";
                    } else if  (model.method.equals("stsls")||model.method.equals("lagmess")) {
                               fittedValues = actualValues+ "-" + "resid("+pre.modelName+NameSuffix+")";
                               residualsText = "residuals("+model.modelName+")";
                    } else if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                              ||model.method.equals("GMerrorsar")||model.method.equals("gstsls")||model.method.equals("spautolm")){ 
                               fittedValues = pre.modelName+NameSuffix+(model.method.equals("spautolm")?"$fit":"")+ "$fitted";
                               residualsText = "residuals("+model.modelName+")";
                    }else if (model.method.equals("dynlm")) {
                              fittedValues ="as.numeric(fitted("+pre.modelName+"))";
                              residualsText = "as.numeric(residuals("+model.modelName+"))";
                    } else if (model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm")){
                               fittedValues = "("+pre.modelName+NameSuffix+
                                     "$model$y-resid("+pre.modelName+NameSuffix+"))";
                               residualsText = "residuals("+model.modelName+")";
                    }else if (model.method.equals("tobit")) {
                              fittedValues = "predict("+pre.modelName+NameSuffix+")";
                                      if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                          if (model.efeglmextra.leftLimitText.equals(""))
                                            fittedValues = "pmax("+fittedValues+",0)";
                                          else
                                            fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                      if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                            fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";
                                      residualsText = "residuals("+model.modelName+")";
                                      residualsText = actualValues +"-" + fittedValues;
                           } else {
                                fittedValues = "("+pre.modelName+NameSuffix+
                                        "$model$\""+model.dependentVar+"\""+
                                         "-resid("+pre.modelName+NameSuffix+",type=\"response\"))";
                                residualsText = "residuals("+model.modelName+")";
                           }
              /***/
   
                      /***/
              
                String call="";
                String callCommandsLog=callCommandsLog = "\n\n#************* commands for Diagnostics plot ************************\n"+
                                         "JavaGD() #Opens a new graphic device\n"; 
                     if (model.method.equals("ca.jo")||model.method.equals("ca.po")||model.method.equals("cajolst")||model.EstimationMethod.equals("Cointegration Test - Engle & Granger")){             
                        if (model.method.equals("ca.jo")||model.method.equals("cajolst")){
                            call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n";
                            for(int i=0;i<Math.min(model.termsArray.length,6);i++){
                                  call+= "qqnorm("+pre.modelName+"@R0[,\"R0."+model.termsArray[i]+".d\"]"+
                                          ",ylab=\"Residual Quantiles\",main=\"Normal Q-Q Plot, dif "+model.termsArray[i]+"\")"+
                                          ";qqline("+pre.modelName+"@R0[,\"R0."+model.termsArray[i]+".d\"],col=2)\n";
                            }
                            ModelPlotPanel panel3Panel = new ModelPlotPanel(call);
                            PlotsPanel2.setVisible(false);
                            PlotsPanel2.add(panel3Panel);
                            Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[2] <- dev.cur()");
                            PlotsPanel2.setVisible(true);
                        } else /*deals with ca.po:pz or Engle Granger*/ {
                                model.plotControl.traditionalGraphicsAvailable[5] = true;
                                model.plotControl.latticeGraphicsAvailable[5] = true;
                                model.plotControl.mainTitle[5] = "cointegrating relation: "+model.termsArray[0].toString()+" on left side";
                                model.plotControl.showTickMarks[5] = true;
                                model.plotControl.showGrid[5] = true;
                                model.plotControl.pointLabels[5] = "row names"; 
                                model.plotControl.whichPointsToLabel[5] = "none";
                                model.plotControl.numExtremeValues[5] = "1";
                                model.plotControl.extremenessBasis[5] = "|y – mean(y)|";
                                model.plotControl.extremenessScrollChoices[5] = "1";
                                model.plotControl.mostExtremeEnabled[5] = true;
                                model.plotControl.timeOrObsHorizontalAxis[5] = true;
                                model.plotControl.yAxisTitle[5] = " ";
                                model.plotControl.manualyAxisRange[5]=false;
                                model.plotControl.xAxisTitle[5] = (model.existsTS?"":"Observation");
                                model.plotControl.manualxAxisRange[5]=false;
                                model.plotControl.legendText1[5] = "";
                                model.plotControl.plotType1[5] = "l: line";
                                model.plotControl.lineType1[5] = "solid";
                                model.plotControl.linePoints1linewidth[5]="1";
                                model.plotControl.linePoints1color[5] = "blue";
                                model.plotControl.linePoints1Symbol[5]="";
                                model.plotControl.legendText2[5] = "";
                                model.plotControl.linePoints2linewidth[5]="";
                                model.plotControl.linePoints2color[5] = "";
                               /* dataPanel5 = vName;*/
                                
                                String vName = new String();
                                if (model.method.equals("ca.po"))
                                    if (model.efeglmextra.cointPOType.equals("Pu")) 
                                           vName =pre.modelName+"$"+model.termsArray[0].toString().replace("(","_").replace(")", "")+"@res"; 
                                    else //(model.efeglmextra.cointPOType.equals("Pz")) 
                                           vName = pre.modelName+"@res[,\""+model.termsArray[0].toString()+"\"]";
                                else 
                                    vName = "resid("+pre.modelName+"$"+model.termsArray[0].toString().replace("(","_").replace(")", "")+")";
                                
                                model.plotControl.xLabels[5] = model.rowNamesOrDatesLabel;
                                model.plotControl.yLabels[5] =  "round("+(model.existsTS?"ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")":vName)+",3)";
                                model.plotControl.traditionalPlotBeginning[5] = "plot("+  
                                         (model.existsTS?"ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")":vName);
                                model.plotControl.traditionalPlotFormula[5] = vName;
                                model.plotControl.latticePlotBeginning[5] = "xyplot("+(model.existsTS?"zoo(ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+"))":"ts("+vName+")");
                            call = model.plotControl.traditionalPlotBeginning[5]+",type=\"l\""+",col=\""+model.plotControl.linePoints1color[5]+"\""+
                                    ",main=\""+ model.plotControl.mainTitle[5]+"\""+
                                    ",ylab=\""+ model.plotControl.yAxisTitle[5]+"\""+
                                    ",xlab=\""+ model.plotControl.xAxisTitle[5]+"\""+
                                    ")\ngrid(lwd = 2)";
                            requestedPlot = new ModelPlotPanel(call);
                            PlotsPanel5.add(requestedPlot);
                            Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[5] <- dev.cur()");
                            PlotsPanel5.setVisible(true);
                            editButton.setVisible(true);editButton.setEnabled(true);  
                            idLabelButton.setVisible(true);
                        }
                   }
                if ((model.method.equals("ca.jo")||model.method.equals("cajolst"))) {
                                    
                String outputCall = "capture.output(cajools("+pre.modelName+"))";

                ArrayList tmp = new ArrayList();
                       try{
                       String[] out  = Deducer.eval(outputCall).asStrings();

                      tmp.add("Coefficient estimates for unrestricted VECM: \n");
                               for(int i=5;i<out.length;i++){
                                tmp.add(out[i]+"\n");
                                 }
                         }catch(Exception e){
                            new ErrorMsg(e);
                        }
                 String cajoolsPrev = "";
                 for(int i =0;i<tmp.size();i++)
                    cajoolsPrev+=tmp.get(i);   
                 vecUnrestrictedClassic.setText(cajoolsPrev);
                 vecUnrestrictedClassic.setCaretPosition(0);  
                }       
              {
                        String expvar = model.termsArray.toString().substring(1,model.terms.toString().length()-1);
                        if (model.method.equals("ivreg")) 
                              NameSuffix ="$SecondStage";
                                                                       
                        if (model.method.equals("plm")||model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm")){
                         /*   String fittedValues ="";
                            if (model.method.equals("spreml"))
                                fittedValues = "fitted("+pre.modelName+NameSuffix+")";
                            else
                                fittedValues = "("+pre.modelName+NameSuffix+
                                     "$model$\""+
                                     ((model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm"))?"y":model.dependentVar)+"\"-resid("+pre.modelName+NameSuffix+"))";*/
                            String effectValues = "("+fittedValues+"-"+pre.modelName+NameSuffix+"$HanselAddOns$fittedValuesExclEffects)";
                            call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n"+
                            "hist(resid("+pre.modelName+NameSuffix+"), freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                            "curve(dnorm(x, mean=mean("+pre.modelName+NameSuffix+"$residuals),sd=sd("+pre.modelName+NameSuffix+"$residuals)), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density(resid("+pre.modelName+NameSuffix+")), lwd=1,col=\"blue\")\n"+
                            "qqnorm(resid("+pre.modelName+NameSuffix+"),ylab=\"Residual Quantiles\");qqline(resid("+pre.modelName+NameSuffix+"),col=2)\n";
       
                            if (model.method.equals("plm")||model.method.equals("spml")||model.method.equals("spreml")||model.method.equals("spgm")  ){
                            call+="plot("+fittedValues+",resid("+pre.modelName+NameSuffix+"),xlab=\"Fitted values\",ylab=\"Residuals\"); abline(0,0)\n"+
                            "lines(lowess("+fittedValues+",resid("+pre.modelName+NameSuffix+")), col = \"green\")\n"+
                                    
                            "hist("+effectValues+", freq=FALSE, col= \"light blue\", main=\"Histogram for Effects Values\",xlab=\"Effects values\")\n"+
                            "curve(dnorm(x, mean=mean("+effectValues+"),sd=sd("+effectValues+")), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density("+effectValues+"), lwd=1,col=\"blue\")\n"+
                            "qqnorm("+effectValues+",ylab=\"Effects Quantiles\");qqline("+effectValues+",col=2)\n"+ 
                            "plot("+effectValues+",resid("+pre.modelName+NameSuffix+"),xlab=\"Effects values\",ylab=\"Residuals\"); abline(0,0)\n"+
                            "lines(lowess("+effectValues+",resid("+pre.modelName+NameSuffix+")), col = \"green\")"; 
                            }
                        } else if  ((model.panelModel/*note: plm already covered above*/)){
                            call="par(mfrow = c(1, 2),mar=c(5,4,2,2))\n"+
                            "hist(unlist(resid("+pre.modelName+NameSuffix+")), freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                            "curve(dnorm(x, mean=mean(unlist("+pre.modelName+NameSuffix+"$residuals)),sd=sd(unlist("+pre.modelName+NameSuffix+"$residuals))), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density(unlist(resid("+pre.modelName+NameSuffix+"))), lwd=1,col=\"blue\")\n"+
                            "qqnorm(unlist(resid("+pre.modelName+NameSuffix+")),ylab=\"Residual Quantiles\");qqline(unlist(resid("+pre.modelName+NameSuffix+")),col=2)\n";                                                                                                   
                        } else if (model.method.equals("tobit")) { 
                            /* String actualValues = pre.modelName+NameSuffix+
                                        (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"");
                             String fittedValues = actualValues+"-resid("+pre.modelName+NameSuffix+",type=\"response\")";*/

                           /*       if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                      if (model.efeglmextra.leftLimitText.equals(""))
                                        fittedValues = "pmax("+fittedValues+",0)";
                                      else
                                        fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                  if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                        fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";*/
                            String residValues = actualValues +"-"+ fittedValues;
                             
                            call="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n"+
                            "hist(resid("+pre.modelName+NameSuffix+"), freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                            "curve(dnorm(x, mean=mean(resid("+pre.modelName+NameSuffix+")),sd=sd(resid("+pre.modelName+NameSuffix+"))), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density(resid("+pre.modelName+NameSuffix+")), lwd=1,col=\"blue\")\n"+
                            "qqnorm(resid("+pre.modelName+NameSuffix+"),ylab=\"Residual Quantiles\");qqline(resid("+pre.modelName+NameSuffix+"),col=2)\n"+
                            "plot("+fittedValues+","+residValues+ ",xlab=\"Fitted values\",ylab=\"Residuals\"); abline(0,0)\n"+
                                "lines(lowess("+fittedValues+","+residValues+ "), col = \"green\")\n";         
                        } else if (model.EstimationMethod.equals("Binary Logit")||(model.EstimationMethod.equals("Binary Probit"))) { 
                                    call="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n"+
                               "plot("+pre.modelName+NameSuffix+", c(1,4,5),pch=4,sub.caption=\"\")";    
                                                
                        }else if (model.method.equals("glm")||model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("ivreg")) { 
                            call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n"+
                               "hist(resid("+pre.modelName+NameSuffix+"), freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                               "curve(dnorm(x, mean=mean(resid("+pre.modelName+NameSuffix+")),sd=sd(resid("+pre.modelName+NameSuffix+"))), add=TRUE, lty=2, col=\"red\")\n"+
                               "lines(density(resid("+pre.modelName+NameSuffix+")), lwd=1,col=\"blue\")\n"+
                               "plot("+pre.modelName+NameSuffix+",2,pch=4,sub.caption=\"\")\n"+
                               "plot("+pre.modelName+NameSuffix+", c(1,4,3,5),pch=4,sub.caption=\"\")";     
                            
                      }else if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                              ||model.method.equals("GMerrorsar")
                              ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                              ||model.method.equals("lagmess") 
                              ){ 
                           
                           String residValues = "resid("+pre.modelName+NameSuffix+")";
                          /*  String fittedValues ="";         
                            if (model.method.equals("stsls")||model.method.equals("lagmess")) 
                               fittedValues = model.dependentVar+"-" + residValues;    
                            else 
                               fittedValues = pre.modelName+NameSuffix+(model.method.equals("spautolm")?"$fit":"")+ "$fitted";*/
                            
                             call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n"+
                            "hist("+residValues+", freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                            "curve(dnorm(x, mean=mean("+residValues+"),sd=sd("+residValues+")), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density("+residValues+"), lwd=1,col=\"blue\")\n"+
                            "qqnorm("+residValues+",ylab=\"Residual Quantiles\");qqline("+residValues+",col=2)\n"+
                            "plot("+fittedValues+","+residValues+",xlab=\"Fitted values\",ylab=\"Residuals\"); abline(0,0)\n"+
                            "lines(lowess("+fittedValues+","+residValues+"), col = \"green\")\n";   
                        }else if (model.method.equals("VAR")){
                            call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n";
                            for(int i=0;i<Math.min(model.termsArray.length,6);i++){
                                  call+= "qqnorm(resid("+pre.modelName+"$varresult$"+model.termsArray[i].replace("(","_").replace(")", "").replace("[","").replace("]","")+"),ylab=\"Residual Quantiles\",main=\"Normal Q-Q Plot, "+
                                          model.termsArray[i]+"\");qqline(resid("+pre.modelName+"$varresult$"+model.termsArray[i].replace("(","_").replace(")", "").replace("[","").replace("]","")+"),col=2)\n";
                            }
                        }else if (model.method.equals("vec2var")){
                            call="par(mfrow = c(2, 3),mar=c(5,4,2,2))\n";
                            for(int i=0;i<Math.min(model.termsArray.length,6);i++){
                                  call+= "qqnorm(resid("+pre.modelName+")[,\"resids of "+model.termsArray[i]+"\"],ylab=\"Residual Quantiles\",main=\"Normal Q-Q Plot, "+model.termsArray[i]+"\");qqline(resid("+pre.modelName+")[,\"resids of "+model.termsArray[i]+"\"],col=2)\n";
                            }
                        }
                        
                     if (mod.method.equals("ca.po")||mod.EstimationMethod.equals("Cointegration Test - Engle & Granger")) {
                         diagnosticsButton.setVisible(false);
                     } else  {
                         diagnosticsButton.setSelected(true);
                        diagnosticTab = new ModelPlotPanel(call);
                        PlotsPanel2.add(diagnosticTab);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[2] <- dev.cur()");
                    }
                     
                      if (model.method.equals("VAR")||model.method.equals("vec2var")||
                          model.method.equals("plm")||model.method.equals("pvcm")||model.method.equals("pggls")||
                          model.method.equals("pgmm")||model.method.equals("pcce")||model.method.equals("pmg")
                          ||model.method.equals("spml")||model.method.equals("spgm")||model.method.equals("spreml")){
                               if (model.method.equals("spml")||model.method.equals("spgm")){
                                  /*residualsVsFitButton.setVisible(false); 
                                  residualsMapButton.setVisible(true);*/ //I can consider including the residuals map
                                                                         //under Other Plots.
                                  String existsSPDFString = "FALSE";
                                  try {
                                    if (model.dName.substring(model.dName.length()-3,model.dName.length()-1).equals("__"))
                                       existsSPDFString = Deducer.eval("as.character(exists(\""+model.dName.replace("__","")+"\"))").asString();  
                                    }catch(Exception e){
                                    new ErrorMsg(e);
                                    }
                                  if (existsSPDFString.equals("TRUE")) {
                                     model.efeglmextra.spdfForModel = model.dName.replace("__","");
                                     residualsMapButton.setEnabled(true);
                                  } else
                                      residualsMapButton.setEnabled(false);
                              }    
                              
                      } else if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                        ||model.method.equals("GMerrorsar")
                        ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                        ||model.method.equals("lagmess"))   {
                                residualsMapButton.setVisible(true);
                                  String existsSPDFString = "FALSE";      
                                  try {
                                    if (model.dName.substring(model.dName.length()-2,model.dName.length()).equals("__"))
                                       existsSPDFString = Deducer.eval("as.character(exists(\""+model.dName.replace("__","")+"\"))").asString();  
                                    }catch(Exception e){
                                    new ErrorMsg(e);
                                    }
                                  if (existsSPDFString.equals("TRUE")) {
                                     model.efeglmextra.spdfForModel = model.dName.replace("__","");
                                     residualsMapButton.setEnabled(true); 
                                  } else
                                      residualsMapButton.setEnabled(false); 
                               
                      }
                 commandsLogText.setText("\n"+pre.call+"\n"+callCommandsLog+call+"\n");
                 
                 
                 
                 if (model.EstimationMethod.equals("Binary Logit")||(model.EstimationMethod.equals("Binary Probit"))) { 
                                    String outputCall = new String();
                                    if (model.EstimationMethod.equals("Binary Logit"))
                                       outputCall = "capture.output(logitmfx("+pre.modelName+",data="+model.dName+"))";
                                    else //must be Binary Probit
                                       outputCall = "capture.output(probitmfx("+pre.modelName+",data="+model.dName+"))";
                                    ArrayList tmp = new ArrayList();
                                           try{
                                           String[] out  = Deducer.eval(outputCall).asStrings();
                                                   for(int i=0;i<out.length;i++){
                                                    tmp.add(out[i]);
                                                     }
                                             }catch(Exception e){
                                                new ErrorMsg(e);
                                            }
                                     String marginalEff = "";
                                     for(int i =2;i<tmp.size();i++)
                                        marginalEff+=tmp.get(i)+"\n";  
                                     marginalEffects.setText(marginalEff);

                                     
                             callCommandsLog = "#************* commands for Marginal Effects text ************************\n";
                             commandsLogText.setCaretPosition(0);      
                          commandsLogText.setText("\n"+commandsLogText.getText()+"\n"+callCommandsLog+outputCall+"\n");
                                    
                                    outputCall = "capture.output(logitor("+pre.modelName+",data="+model.dName+"))";
                                    tmp = new ArrayList();
                                           try{
                                           String[] out  = Deducer.eval(outputCall).asStrings();
                                                   for(int i=0;i<out.length;i++){
                                                    tmp.add(out[i]);
                                                     }
                                             }catch(Exception e){
                                                new ErrorMsg(e);
                                            }
                                     String oddsRat = "";
                                     for(int i =2;i<tmp.size();i++)
                                        oddsRat+=tmp.get(i)+"\n";  
                                     oddsRatios.setText(oddsRat);

                                     
                             callCommandsLog = "#************* commands for Odds Ratios text ************************\n";
                             commandsLogText.setCaretPosition(0);      
                          commandsLogText.setText("\n"+commandsLogText.getText()+"\n"+callCommandsLog+outputCall+"\n");

                        }
                 commandsLogText.setCaretPosition(0);
                 } 
 
	}
       
       
       
        public void setaddClassicCommand(GMModel mod){
		model = mod;
		String addToLogcommands =model.runaddd(true, pre); 
                ae =model.runae(true,pre);
                commandsLogText.setText(commandsLogText.getText() + "\n*************************************\n"+ae[0]+ae[1]+ae[2]);
                
        }        
        
        public void setaddexp(GMModel mod){
		model = mod;

		ae =model.runae(true,pre);
                commandsLogText.setText(commandsLogText.getText() + "\n\n#****Command(s) leading to additional text output*********"+ae[0]+ae[1]+ae[2]);
                if ((model.method.equals("ca.jo")||model.method.equals("cajolst"))) {
                    vecRestrictedAddexp.setText(ae[3]);
                    vecRestrictedClassicPanelSmall.setVisible(true);
                    vecRestrictedAddexpPanel.setVisible(true);
                    vecRestrictedClassicPanel.setVisible(false);
                }
                    else {
                    addexp.setText(ae[3]);
                    if (model.efeglmextra.classicView) {
                    previewClassicPanelSmall.setVisible(true);
                    addexpPanel.setVisible(true);
                    previewClassicPanel.setVisible(false);
                    }
                    else{
                    previewPanelSmall.setVisible(true);
                    addexpPanel.setVisible(true);
                    previewPanel.setVisible(false);
                    }
                }
		addexp.setCaretPosition(0);
	}
               public void setaddexpplots(GMModel mod){
		model = mod;
                commandsLogText.setText(commandsLogText.getText() + 
                                         "\n\n#*************plot commands ************************\n"+
                                         "JavaGD() #Opens a new graphic device\n");
                if (model.efeaeplots.plotImpulseResponseFunction){
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String impulseResponseFunctionDefault = Deducer.getUniqueName("impulseResponseFunctionDefault");
                        String cmd2 = "if (exists(\"T.S."+model.data+"\")) attach(as.list(T.S."+model.data+")) else attach("+model.data+")\n"+
                                           impulseResponseFunctionDefault+ " <- irf("+pre.modelName+
                                           ",impulse=\""+model.efeaeplots.impulseToPlot.replace("(","_").replace(")", "").replace("[","").replace("]","")+
                                           "\",response=c("+model.efeaeplots.responsesToPlot.replace("(","_").replace(")", "").replace("[","").replace("]","")+")"+
                                           ",n.ahead="+model.irfOptions.nahead+
                                           (model.irfOptions.orthogonalized ? "": ",ortho=FALSE")+
                                           (model.irfOptions.cumulative ? "": ",cumulative=FALSE")+
                                           (model.irfOptions.bootstrap ? "": ",boot=FALSE")+
                                           ",ci="+model.irfOptions.confIntCov+
                                           "  )\n"+
                                       "if (exists(\"T.S."+model.data+"\")) detach(as.list(T.S."+model.data+")) else detach("+model.data+")";
                        Deducer.eval(cmd2);
                              
                              
                         String call="plot("+impulseResponseFunctionDefault+",col=c(3,1,2))";
                         String callrm = "\nrm("+impulseResponseFunctionDefault+")";
                         commandsLogText.setText(commandsLogText.getText() + cmd2 + call + callrm);
                        requestedPlot = new ModelPlotPanel(call);
                        Deducer.eval(callrm);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                        model.efeaeplots.plotImpulseResponseFunction = false;
                    
                }else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals histogram")) {
                  
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String residuals ="";
                        if (model.method.equals("pgmm"))
                            residuals = "unlist(resid("+pre.modelName+NameSuffix+"))";
                        else
                            residuals = "resid("+pre.modelName+NameSuffix+")"; 
                        String call="par(mar=c(5,4,2,2))\n"+		   
                           "hist("+residuals+", freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"Residuals\")\n"+
                           "curve(dnorm(x, mean=mean("+residuals+"),sd=sd("+residuals+")), add=TRUE, lty=2, col=\"red\")\n"+
                           "lines(density("+residuals+"), lwd=1,col=\"blue\")\n";
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel("try("+call+",silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Normal Q-Q for residuals")) {
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String residuals ="";
                        if (model.method.equals("pgmm"))
                            residuals = "unlist(resid("+pre.modelName+NameSuffix+"))";
                        else
                            residuals = "resid("+pre.modelName+NameSuffix+")"; 
                         String call= "qqnorm("+residuals+",ylab=\"Residual Quantiles\")\n"+
                                      "qqline("+residuals+",col=2)";
                        requestedPlot = new ModelPlotPanel(call);
                        commandsLogText.setText(commandsLogText.getText() + call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("Fitted vs actual")) {
                   editButton.setEnabled(true);idLabelButton.setEnabled(true);idLAbelButtonEnabled = true;
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");

                     /*   String actualValues = "";
                        if (model.method.equals("pgmm")) {
                           actualValues = "rapply("+model.modelName+"$model, function(x) x[,1])";
                        } else {
                            if (model.method.equals("lm")){
                                 actualValues =   model.modelName+"$model[,\""+model.dependentVar+"\"]";
                            }  else if (model.method.equals("dynlm")){
                                 actualValues =   "as.numeric("+model.modelName+"$model[,\""+model.dependentVar+"\"])";
                            }else{
                                 actualValues =   model.modelName+"$y";
                            } 
                        }*/


                     /*   String fittedValues = new String();
                         if (model.method.equals("lm")||model.method.equals("pgmm")||
                                 model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess") 
                                 ){
                                        fittedValues ="fitted("+pre.modelName+")";
                         }else if (model.method.equals("dynlm")) {
                                  fittedValues ="as.numeric(fitted("+pre.modelName+"))";
                             } else if (model.method.equals("tobit")){
                                  fittedValues = "predict("+pre.modelName+NameSuffix+")";
                                          if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                              if (model.efeglmextra.leftLimitText.equals(""))
                                                fittedValues = "pmax("+fittedValues+",0)";
                                              else
                                                fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                          if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                                fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";
                                } else if(model.method.equals("pgmm")) {
                                    fittedValues = actualValues+
                                             "-unlist(resid("+pre.modelName+NameSuffix+",type=\"response\")))";
                               } else
                                    fittedValues = "("+pre.modelName+NameSuffix+
                                            "$model$\""+model.dependentVar+"\""+
                                             "-resid("+pre.modelName+NameSuffix+",type=\"response\"))";*/
                        
                        
                         if (model.method.equals("tobit")){
                           /*   if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                  if (model.efeglmextra.leftLimitText.equals(""))
                                    fittedValues = "pmax("+fittedValues+",0)";
                                  else
                                    fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                              if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                    fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";*/
                        }        
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.xLabels[6] = "round("+actualValues+",3)";
                                model.plotControl.yLabels[6] = "round("+fittedValues+",3)";
                                model.plotControl.traditionalPlotBeginning[6] = "plot("+actualValues+","+fittedValues;
                                model.plotControl.traditionalPlotFormula[6]= actualValues+","+fittedValues;
                                model.plotControl.traditionalPlotEnd[6] = "\nabline(0,0)";
                                model.plotControl.traditionalPlotBeginning2[6]="\nlines(lowess("+actualValues+","+fittedValues+")";
                                model.plotControl.traditionalPlotBeginning3[6]="\nabline(coef=c(0,1)";
                                model.plotControl.latticePlotBeginning[6]="plot("+fittedValues+","+actualValues;
                                model.plotControl.ggplot2Beginning[6]="plot("+fittedValues+","+actualValues;
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[6] = "4";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.pointLabelTextSize[6] = "1.0";
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "Fitted vs Actual w/ Lowess Curve & Fitted=Actual Line";
                                model.plotControl.xAxisTitle[6] = "Actual values";
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.yAxisTitle[6] = "Fitted values";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                                model.plotControl.symbolSize1[6] = "0.5";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.legendText2[6] = "";
                                model.plotControl.lineType2[6] = "solid";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "green";
                                model.plotControl.lineType3[6] = "dotted";
                                model.plotControl.linePoints3linewidth[6]="1";
                                model.plotControl.linePoints3color[6] = "red";
                        
                        String call= model.plotControl.traditionalPlotBeginning[6]+",main=\""+model.plotControl.mainTitle[6]+"\""+
                                     ",xlab=\""+model.plotControl.xAxisTitle[6]+"\",ylab=\""+model.plotControl.yAxisTitle[6]+
                                     "\",pch=16,col=\""+model.plotControl.linePoints1color[6]+"\")"+
                                     model.plotControl.traditionalPlotEnd[6]+
                                     model.plotControl.traditionalPlotBeginning2[6]+",col=\""+model.plotControl.linePoints2color[6] +"\")"+
                                     model.plotControl.traditionalPlotBeginning3[6]+",lty=\""+model.plotControl.lineType3[6]+"\""
                                      +",col=\""+model.plotControl.linePoints3color[6] +"\")";        
                        
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }     
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("Actual vs fitted")) {
                   editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                         
                     /*  String actualValues = "";

                        if (model.method.equals("pgmm")) {
                           actualValues = "rapply("+model.modelName+"$model, function(x) x[,1])";
                        } else {
                            if (model.method.equals("lm")){
                                 actualValues =   model.modelName+"$model[,\""+model.dependentVar+"\"]";
                           } else if (model.method.equals("dynlm")){
                                 actualValues =   "as.numeric("+model.modelName+"$model[,\""+model.dependentVar+"\"])";
                           } else if (model.method.equals("gstsls")){
                                 actualValues =   model.modelName+"$fitted +"+model.modelName+"$resid" ;
                           } else if (model.method.equals("GMerrorsar")){ 
                                 actualValues =   model.modelName+"$lm.model$model$y";
                           } else if (model.method.equals("stsls")){
                                 actualValues =   model.dName+"$"+model.dependentVar;
                            }else{
                                 actualValues =   model.modelName+"$y";
                            } 
                        }
                        
                        String fittedValues = new String();
                        if (model.method.equals("lm")||model.method.equals("pgmm")||
                                 model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess")){
                                        fittedValues ="fitted("+pre.modelName+")";
                        }else if (model.method.equals("dynlm")) {
                                  fittedValues ="as.numeric(fitted("+pre.modelName+"))";
                        }else if (model.method.equals("tobit")) {
                                  fittedValues = "predict("+pre.modelName+NameSuffix+")";
                                          if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                              if (model.efeglmextra.leftLimitText.equals(""))
                                                fittedValues = "pmax("+fittedValues+",0)";
                                              else
                                                fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                          if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                                fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";
                               } else
                                    fittedValues = "("+pre.modelName+NameSuffix+
                                            "$model$\""+model.dependentVar+"\""+
                                             "-resid("+pre.modelName+NameSuffix+",type=\"response\"))";*/
                                          
                               if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.xLabels[6] = "round("+fittedValues+",3)";
                                model.plotControl.yLabels[6] = "round("+actualValues+",3)";
                                model.plotControl.traditionalPlotBeginning[6] = "plot("+fittedValues+","+actualValues;
                                model.plotControl.traditionalPlotFormula[6]= fittedValues+","+actualValues;
                                model.plotControl.traditionalPlotEnd[6] = "\nabline(0,0)";
                                model.plotControl.traditionalPlotBeginning2[6]="\nlines(lowess("+fittedValues+","+actualValues+")";
                                model.plotControl.traditionalPlotBeginning3[6]="\nabline(coef=c(0,1)";
                                model.plotControl.latticePlotBeginning[6]="plot("+actualValues+"~"+fittedValues;
                                model.plotControl.ggplot2Beginning[6]="plot("+actualValues+"~"+fittedValues;
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[6] = "4";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.pointLabelTextSize[6] = "1.0";
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "Actual vs Fitted w/ Lowess Curve & Actual=Fitted Line";
                                model.plotControl.xAxisTitle[6] = "Fitted values";
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.yAxisTitle[6] = "Actual values";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                                model.plotControl.symbolSize1[6] = "0.5";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.legendText2[6] = "";
                                model.plotControl.lineType2[6] = "solid";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "green";
                                model.plotControl.lineType3[6] = "dotted";
                                model.plotControl.linePoints3linewidth[6]="1";
                                model.plotControl.linePoints3color[6] = "red";
                        
                        String call= model.plotControl.traditionalPlotBeginning[6]+",main=\""+model.plotControl.mainTitle[6]+"\""+
                                     ",xlab=\""+model.plotControl.xAxisTitle[6]+"\",ylab=\""+model.plotControl.yAxisTitle[6]+
                                     "\",pch=16,col=\""+model.plotControl.linePoints1color[6]+"\")"+
                                     model.plotControl.traditionalPlotEnd[6]+
                                     model.plotControl.traditionalPlotBeginning2[6]+",col=\""+model.plotControl.linePoints2color[6] +"\")"+
                                     model.plotControl.traditionalPlotBeginning3[6]+",lty=\""+model.plotControl.lineType3[6]+"\""
                                      +",col=\""+model.plotControl.linePoints3color[6] +"\")";
                        
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }  
                  
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("Create on your console .run3D, a function for making 3D regression plot")) {
                    Deducer.execute("suppressMessages(library(rgl))\n"+
                                                  ".run3d <- function(){plot3d("+model.dName+"[,c("+
                                       model.VariablesListWithQuotes+",\""+this.model.dependentVar+"\")],box=TRUE, col=\"steelblue\", type=\"s\", size=1,alpha=0.75)\n"+
                                   "planes3d(a=coef("+model.modelName+")["+model.VariablesListWithQuotes.split(",")[0]+"], b=coef("+model.modelName+")["+model.VariablesListWithQuotes.split(",")[1]+"], -1, coef("+model.modelName+")[\"(Intercept)\"], alpha=0.50, col=\"plum2\")}\n"+
                                   "#Enter \".run3d()\" to get interactive 3d diagram.\"\n"+
                                   "#Note that on the Windows version of JGR the interactive element does not work. \n"+
                                   "#If rgl device is not responding, you can enter \"rgl.quit()\" to remove it."
                                      ); 
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs fitted")) {
                                editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();

                                if (model.method.equals("tobit")){
                                    /* String actualValues = pre.modelName+NameSuffix+
                                                (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"");*/
                                     /*String fittedValues = actualValues+"-resid("+pre.modelName+NameSuffix+",type=\"response\")";

                                          if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                              if (model.efeglmextra.leftLimitText.equals(""))
                                                fittedValues = "pmax("+fittedValues+",0)";
                                              else
                                                fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                          if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                                fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";*/
                                     String residValues = actualValues +"-"+ fittedValues;
                                     model.plotControl.xLabels[6] = "round("+fittedValues+",3)";
                                     model.plotControl.yLabels[6] = "round("+residValues+",3)";
                                     model.plotControl.traditionalPlotBeginning[6] =              
                                      "plot("+fittedValues+","+residValues;
                                     model.plotControl.traditionalPlotBeginning2[6]="\nlines(lowess("+fittedValues+","+residValues+ ")";
                                     model.plotControl.traditionalPlotFormula[6] =fittedValues+","+residValues;
                                }   
                                else {
                                   /* String fittedValues = "("+pre.modelName+NameSuffix+
                                                (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"")+"-resid("+pre.modelName+NameSuffix+",type=\"response\"))";*/
                                    model.plotControl.xLabels[6] = "round("+fittedValues+",3)";
                                    model.plotControl.yLabels[6] = "round(resid("+pre.modelName+NameSuffix+",type=\"response\")"+",3)";
                                    model.plotControl.traditionalPlotBeginning[6] =                      
                                      "plot("+fittedValues+",resid("+pre.modelName+NameSuffix+",type=\"response\")";
                                    model.plotControl.traditionalPlotBeginning2[6]= "\nlines(lowess("+fittedValues+",resid("+pre.modelName+NameSuffix+",type=\"response\"))";
                                    model.plotControl.traditionalPlotFormula[6] = fittedValues+"~resid("+pre.modelName+NameSuffix+",type=\"response\")";
                                }
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.traditionalPlotEnd[6] = "\nabline(0,0)";
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "|y|";
                                model.plotControl.extremenessScrollChoices[6] = "5";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.pointLabelTextSize[6] = "1.0";
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "";
                                model.plotControl.xAxisTitle[6] = "fitted values";
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.yAxisTitle[6] = "residuals";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                                model.plotControl.symbolSize1[6] = "0.5";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.legendText2[6] = "";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "green";
                                model.plotControl.lineType2[6]="solid";
                                
                                
                                String call = model.plotControl.traditionalPlotBeginning[6]+
                                      (model.plotControl.mainTitle[6].equals("")?"":",main=\""+ model.plotControl.mainTitle[6]+"\"")+
                                      (model.plotControl.yAxisTitle[6].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[6]+"\"")+
                                      (model.plotControl.xAxisTitle[6].equals("")?"":",xlab=\""+ model.plotControl.xAxisTitle[6]+"\"")+
                                      ",pch="+model.plotControl.linePoints1Symbol[6].split(":")[0]+
                                      ",col=\""+model.plotControl.linePoints1color[6]+"\""+
                                     (model.plotControl.linePoints2linewidth[6].equals("1.0")?"":",lwd="+model.plotControl3.linePoints2linewidth)+
                                     (model.plotControl.lineType2[6].equals("solid")||model.plotControl.lineType2[6].equals("")?"":",lty=\""+model.plotControl.lineType2[6]+"\"")+ 
                                     ((model.plotControl.symbolSize1[6].equals("0.5"))?"":",cex="+model.plotControl.symbolSize1[6])+")"+
                                        model.plotControl.traditionalPlotEnd[6]+
                                      model.plotControl.traditionalPlotBeginning2[6]+
                                      ",col=\""+model.plotControl.linePoints2color[6]+"\")"+
                                      (model.plotControl.showGrid[6]?"\n grid(lwd = 2)":""); 
                                                              
                                
                                commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel("par(mar=c(5,4,2,2))\n"+call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);
   
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Scale-location")) {
                   
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call1 ="par(mar=c(5,4,2,2))\n";
			String call2 ="plot("+pre.modelName+NameSuffix+", c(3),sub.caption=\"\")";
                        commandsLogText.setText(commandsLogText.getText() + call1 + call2);
                        requestedPlot = new ModelPlotPanel(call1 + "try("+call2+",silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }  
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Cook's distance")) {
                   editButton.setEnabled(true);
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                         model.plotControl.previousPlot6 = true;
                         model.plotControl.traditionalGraphicsAvailable[6] = true;
                         model.plotControl.traditionalPlotBeginning[6] = "plot("+pre.modelName+NameSuffix+", c(4),sub.caption=\"\""; 
                         model.plotControl.traditionalPlotFormula[6] = model.formula;
                         model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "_na_";
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = false;
                                model.plotControl.yAxisTitle[6] = "_na_";
                                model.plotControl.xAxisTitle[6] = "_na_";
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1linewidth[6]="1";
                                model.plotControl.linePoints1color[6] = "black";
                                model.plotControl.legendText2[6] = "";

                           
			String call =  model.plotControl.traditionalPlotBeginning[6] +")";
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel("try("+call+",silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }                   
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Individual effects histogram")) {
                   
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        /*String fittedValues = "("+pre.modelName+NameSuffix+
                                    (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"")+"-resid("+pre.modelName+NameSuffix+"))";*/
                        String effectValues = "("+fittedValues+"-"+pre.modelName+NameSuffix+"$HanselAddOns$fittedValuesExclEffects)";
                        String call= "hist("+effectValues+", freq=FALSE, col= \"light blue\", main=\"Histogram for Effects Values\",xlab=\"Effects values\")\n"+
                            "curve(dnorm(x, mean=mean("+effectValues+"),sd=sd("+effectValues+")), add=TRUE, lty=2, col=\"red\")\n"+
                            "lines(density("+effectValues+"), lwd=1,col=\"blue\")\n";    
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                        
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Normal Q-Q for individual effects")) {
                   
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       /* String fittedValues = "("+pre.modelName+NameSuffix+
                                    (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"")+"-resid("+pre.modelName+NameSuffix+"))";*/
                        String effectValues = "("+fittedValues+"-"+pre.modelName+NameSuffix+"$HanselAddOns$fittedValuesExclEffects)";
                         String call= "qqnorm("+effectValues+",ylab=\"Effects Quantiles\");qqline("+effectValues+",col=2)\n";
                         commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs individual effects")) {
                   
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       /* String fittedValues = "("+pre.modelName+NameSuffix+"$model$\""+model.dependentVar+"\""+"-resid("+pre.modelName+NameSuffix+"))"*/
                        String effectValues = "("+fittedValues+"-"+pre.modelName+NameSuffix+"$HanselAddOns$fittedValuesExclEffects)";
                        String call=                     
                          "plot(resid("+effectValues+","+pre.modelName+NameSuffix+"),xlab=\"Effects values\",ylab=\"Residuals\"); abline(0,0)\n"+
                            "lines(lowess("+effectValues+",resid("+pre.modelName+NameSuffix+")), col = \"green\")"; 
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }     
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Response by time, individual stratification")) {
                 
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String xAVariable = Deducer.eval("names("+pre.data+"[2])").asString();
                        String stratVariable = Deducer.eval("names("+pre.data+"[1])").asString();
                             String call = "try(plot(xyplot("+model.dependentVar+"~"+xAVariable+"|"+stratVariable+",data="+pre.data+",type=\"l\"),new=FALSE),silent=TRUE)";
                             commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  } 
                  
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Simple regression plots, by individual*")) {
                 
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String stratVariable = Deducer.eval("names("+pre.data+"[1])").asString();
                         String call = "try(plot(xyplot("+
                             model.dependentVar + "~" + model.efeaeplots.termsToPlot + "|" + stratVariable +
                                     ",data="+pre.data+",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)}),new=FALSE),silent=TRUE)";
                             commandsLogText.setText(commandsLogText.getText() + call);   
                             
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }                   
               }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Partial regression plots, by individual*")) {
                  editButton.setEnabled(true);
                  { 
                     try{ 
                    PlotsPanel6.removeAll();
                    PlotsPanel6.setVisible(false);
                    Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                    String formulaforDepVarGivenOthers = pre.formula + "-" + model.efeaeplots.termsToPlot.toString();
                    String formulaforExplVarGivenOthers = model.efeaeplots.termsToPlot.toString() + "~" + Deducer.eval("strsplit(\""+pre.formula+"\",\"~\")[[1]][2]").asString() + "-" + model.efeaeplots.termsToPlot.toString();
               
                    String call2  = model.efeaeplots.termsToPlot.toString();
                    String toEvalFirst = 

                                pre.data + "$depVarGivenOthers <- resid("+model.method+"(formula="+formulaforDepVarGivenOthers+model.instruments+",data="+pre.data+
				(model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
				((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
				",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))" + "\n" +
                                
                                pre.data + "$explVarGivenOthers <- resid("+model.method+"(formula="+formulaforExplVarGivenOthers+model.instruments+",data="+pre.data+
				(model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
				((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
				",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))";

                        Deducer.eval(toEvalFirst);
                        String stratVariable = Deducer.eval("names("+pre.data+"[1])").asString();
			 
                        String call = "try(plot(xyplot(depVarGivenOthers~explVarGivenOthers|" + stratVariable +
                                     ",data="+pre.data+",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)},ylab=\""+ model.dependentVar +"|others\",xlab=\""+ 
                                                                                        model.efeaeplots.termsToPlot.toString()+"|others\" ),new=FALSE),silent=TRUE)";
                        commandsLogText.setText(commandsLogText.getText() + toEvalFirst + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }                   
               }      
               else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs leverage")) {
                   
                  {try{
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call1 ="par(mar=c(5,4,2,2))\n";
			String call2 =   "plot("+pre.modelName+NameSuffix+", c(5),sub.caption=\"\")";
                        commandsLogText.setText(commandsLogText.getText() +call1 + call2);
                        requestedPlot = new ModelPlotPanel(call1+"try("+call2+",silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
                  }      
               else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs response variable")) {
                   editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                    PlotsPanel6.removeAll();
                    PlotsPanel6.setVisible(false);
                    Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                    if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                    model.plotControl.traditionalGraphicsAvailable[6] = true;
                    model.plotControl.latticeGraphicsAvailable[6] = true;
                    model.plotControl.ggplot2GraphicsAvailable[6] = true;
                   
                    String dependentVarText = actualValues;
              //      String residualsText = "";
             //       if (model.method.equals("pgmm")) {
             //          dependentVarText = "rapply("+model.modelName+"$model, function(x) x[,1])";
              //         residualsText = "unlist(residuals("+model.modelName+"))";
              //      } else {
                        dependentVarText = actualValues;
              //          if (model.method.equals("lm")){
               //              /*dependentVarText =   model.modelName+"$model[,\""+model.dependentVar+"\"]";*/
              //               residualsText = "residuals("+model.modelName+")";
             //           }else if (model.method.equals("dynlm")){
              //               /*dependentVarText =   "as.numeric("+model.modelName+"$model[,\""+model.dependentVar+"\"])";*/
             //                residualsText = "as.numeric(residuals("+model.modelName+"))";
             //           }else{
             //                /*dependentVarText =   model.modelName+"$y";*/
             //                residualsText = "residuals("+model.modelName+")";
             //           } 
                        
              //      }
                     
                    model.plotControl.traditionalPlotBeginning[6] = "plot("+residualsText+"~"+dependentVarText +",type=c(\"p\")";
                    model.plotControl.traditionalPlotFormula[6] =residualsText+"~"+dependentVarText ;
                    model.plotControl.traditionalPlotBeginning2[6]="\nabline(lm("+residualsText+"~"+dependentVarText+")";
                    model.plotControl.latticePlotBeginning[6] = "xyplot("+residualsText+"~"+dependentVarText+",type=c(\"p\",\"r\")";
                    model.plotControl.ggplot2Beginning[6] = "ggplot(data.frame("+residualsText+"), aes(x="+dependentVarText+", y="+residualsText; 
                    model.plotControl.showTickMarks[6] = true;
                    model.plotControl.showGrid[6] = true;
                    model.plotControl.ggplot2GrayTheme[6] = true;
                    model.plotControl.xLabels[6] = "round("+dependentVarText +",3)";
                    model.plotControl.yLabels[6] = "round("+residualsText+",3)"; 
                    model.plotControl.pointLabels[6] = "row names";
                    model.plotControl.whichPointsToLabel[6] = "none";
                    model.plotControl.numExtremeValues[6] = "1";
                    model.plotControl.extremenessBasis[6] = "|y|";
                    model.plotControl.extremenessScrollChoices[6] = "5";
                    model.plotControl.mostExtremeEnabled[6] = true;
                    model.plotControl.timeOrObsHorizontalAxis[6] = false;
                    model.plotControl.pointLabelTextSize[6] = "1.0";
                    model.plotControl.singleGraphMulti[6] = false;
                    model.plotControl.mainTitle[6] = "residuals vs "+ model.dependentVar+" with OLS fitted line"; 
                    model.plotControl.xAxisTitle[6] = new String(model.dependentVar);
                    model.plotControl.manualyAxisRange[6]=false;
                    model.plotControl.yAxisTitle[6] = "residuals";
                    model.plotControl.manualxAxisRange[6]=false;
                    model.plotControl.legendText1[6] = "";
                    model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                    model.plotControl.symbolSize1[6] = "0.5";
                    model.plotControl.linePoints1color[6] = "blue";
                    model.plotControl.legendText2[6] = "";
                    model.plotControl.linePoints2linewidth[6]="1";
                    model.plotControl.linePoints2color[6] = "red";
                    model.plotControl.lineType2[6]="dashed";


                    String call = model.plotControl.traditionalPlotBeginning[6]+
                          (model.plotControl.mainTitle[6].equals("")?"":",main=\""+ model.plotControl.mainTitle[6]+"\"")+
                          (model.plotControl.yAxisTitle[6].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[6]+"\"")+
                          (model.plotControl.xAxisTitle[6].equals("")?"":",xlab=\""+ model.plotControl.xAxisTitle[6]+"\"")+
                          ",pch="+model.plotControl.linePoints1Symbol[6].split(":")[0]+
                          ",col=\""+model.plotControl.linePoints1color[6]+"\""+
                         (model.plotControl.linePoints2linewidth[6].equals("1.0")?"":",lwd="+model.plotControl3.linePoints2linewidth)+
                         ((model.plotControl.symbolSize1[6].equals("0.5"))?"":",cex="+model.plotControl.symbolSize1[6])+")"+
                          model.plotControl.traditionalPlotBeginning2[6]+
                          ",col=\""+model.plotControl.linePoints2color[6]+"\""+
                            (model.plotControl.lineType2[6].equals("solid")||model.plotControl.lineType2[6].equals("")?"":",lty=\""+model.plotControl.lineType2[6]+"\"")+ 
                          ")"+
                          (model.plotControl.showGrid[6]?"\ngrid(lwd = 2)":""); 

                    commandsLogText.setText(commandsLogText.getText() +call);
                    requestedPlot = new ModelPlotPanel(call);
                    PlotsPanel6.add(requestedPlot);
                    Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                    PlotsPanel6.setVisible(true); 
               }      
               else if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs explanatory variable")||
                       model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs explanatory variable*")) {
                                editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                String explanatoryVariable = "";
                                if (model.efeaeplots.moreDiagnosticPlots.equals("Residuals vs explanatory variable"))
                                  explanatoryVariable = model.termsArray[0];
                                else
                                  explanatoryVariable = model.efeaeplots.termsToPlot;
                                
                                String explanatoryVarText = "";
                               /*  String residualsText = "";*/
                                if (model.method.equals("pgmm")) {
                                   explanatoryVarText = ""; 
                                  /*  residualsText = "unlist(residuals("+model.modelName+"))";*/
                                } else {
                                    if (model.method.equals("lm")){
                                         explanatoryVarText =   model.modelName+"$model[,\""+explanatoryVariable+"\"]";
                                       /*   residualsText = "residuals("+model.modelName+")";*/
                                    }else if (model.method.equals("glm")||model.method.equals("plm")){
                                         explanatoryVarText =   model.modelName+"$mod[,\""+explanatoryVariable+"\"]";
                                      /*    residualsText = "residuals("+model.modelName+")";*/
                                    }else if (model.method.equals("dynlm")){
                                         explanatoryVarText =   "as.numeric("+model.modelName+"$model[,\""+explanatoryVariable+"\"])";
                                        /*  residualsText = "as.numeric(residuals("+model.modelName+"))";*/
                                    }else{
                                         explanatoryVarText =   explanatoryVarText =   (model.dataClassInR.equals(".data.frame")?model.dName:"as.data.frame("+model.dName+")")
                                                             +"[,\""+model.termsArray[0]+"\"]";
                                       /*  residualsText = "residuals("+model.modelName+")";*/
                                    }
                                }
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.latticeGraphicsAvailable[6] = true;
                                model.plotControl.ggplot2GraphicsAvailable[6] = true;
                                model.plotControl.xLabels[6] = "round("+explanatoryVarText +",3)";
                                model.plotControl.yLabels[6] = "round("+residualsText+",3)";  
                                model.plotControl.traditionalPlotBeginning[6] = "plot("+residualsText+"~"+explanatoryVarText+",type=c(\"p\")";
                                model.plotControl.traditionalPlotFormula[6] = residualsText+"~"+""+explanatoryVarText+"";
                                model.plotControl.traditionalPlotBeginning2[6]="\n abline(0,0";
                                model.plotControl.latticePlotBeginning[6] = "xyplot("+residualsText+"~"+explanatoryVarText+",type=c(\"p\",\"r\")";
                                model.plotControl.ggplot2Beginning[6] = "ggplot(data.frame("+residualsText+"), aes(x="+explanatoryVarText+", y= "+residualsText+"";
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.ggplot2GrayTheme[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "|y|";
                                model.plotControl.extremenessScrollChoices[6] = "5";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.pointLabelTextSize[6] = "1.0";
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "residuals vs "+ explanatoryVariable +" with OLS fitted line"; 
                                model.plotControl.xAxisTitle[6] = new String(model.termsArray[0]);
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.yAxisTitle[6] = "residuals";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                                model.plotControl.symbolSize1[6] = "0.5";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.legendText2[6] = "";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "red";
                                model.plotControl.lineType2[6]="dashed";
                                
                                
                                String call = model.plotControl.traditionalPlotBeginning[6]+
                                      (model.plotControl.mainTitle[6].equals("")?"":",main=\""+ model.plotControl.mainTitle[6]+"\"")+
                                      (model.plotControl.yAxisTitle[6].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[6]+"\"")+
                                      (model.plotControl.xAxisTitle[6].equals("")?"":",xlab=\""+ model.plotControl.xAxisTitle[6]+"\"")+
                                      ",pch="+model.plotControl.linePoints1Symbol[6].split(":")[0]+
                                      ",col=\""+model.plotControl.linePoints1color[6]+"\""+
                                     (model.plotControl.linePoints2linewidth[6].equals("1.0")?"":",lwd="+model.plotControl3.linePoints2linewidth)+
                                    
                                     ((model.plotControl.symbolSize1[6].equals("0.5"))?"":",cex="+model.plotControl.symbolSize1[6])+")"+
                                      model.plotControl.traditionalPlotBeginning2[6]+
                                      ",col=\""+model.plotControl.linePoints2color[6]+"\""+
                                       (model.plotControl.lineType2[6].equals("solid")||model.plotControl.lineType2[6].equals("")?"":",lty=\""+model.plotControl.lineType2[6]+"\"")+
                                      ")"+
                                      (model.plotControl.showGrid[6]?"\n grid(lwd = 2)":""); 

                                commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel(call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);

               } else if (model.efeaeplots.moreDiagnosticPlots.equals("accuracy vs cutoff")){
                                editButton.setEnabled(true);
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.graphicSystem[6] = "traditional graphics";
                                model.plotControl.traditionalPlotBeginning[6] =
                                        "plot(performance(prediction(fitted("+model.modelNameFinalStage+"),"+model.modelNameFinalStage+"$model[\""+model.dependentVar+"\"]),\"acc\")"; 
                                model.plotControl.latticePlotBeginning[6] = 
                                        "xyplot(performance(prediction(fitted("+model.modelNameFinalStage+"),"+model.modelNameFinalStage+"$model[\""+model.dependentVar+"\"]),\"acc\")"; 
                                model.plotControl.plotType1[6] = "l: line";
                                
                                model.plotControl.lineType2[6] = "solid";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "green";
                
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.latticeGraphicsAvailable[6] = true;
                                /*model.plotControl.mainTitle[6] = "";*/
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.yAxisTitle[6] = "Accuracy";
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.xAxisTitle[6] = "Cutoff";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = model.dependentVar;
                                model.plotControl.lineType1[6] = "solid";
                                model.plotControl.linePoints1linewidth[6]="1";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.linePoints1Symbol[6]="";
                             
                            String call = model.plotControl.traditionalPlotBeginning[6] +",col=\"blue\",type=\"l\""+
                                    ",ylab=\""+ model.plotControl.yAxisTitle[6]+"\""+
                                    ",main=\""+model.plotControl.mainTitle[6]+"\""+
                                    ",xlab=\""+ model.plotControl.xAxisTitle[6]+"\""+
                                    ",col.symbol=\""+model.plotControl.linePoints1color[6]+"\""+                         
                                    ")\n grid(lwd = 2) " ;
   
                            commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel(call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);    
                                
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("spinogram")){
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;                                
                             
                            String call = "plot(factor("+model.dependentVar+") ~"+model.VariablesList+",data="+model.dName+",ylevels=2:1,col=c(3,5))";    
   
                            commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel(call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);    
                                                        
                    
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("spinogram-one variable to right*")){
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;                                
                             
                            String call = "plot(factor("+model.dependentVar+") ~"+model.efeaeplots.termsToPlot+",data="+model.dName+",ylevels=2:1,col=c(3,5))";    
   
                            commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel(call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);    
                                                        
                            
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("ROC curve")){
                               editButton.setEnabled(true);
                               PlotsPanel6.removeAll();
                               Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                PlotsPanel6.setVisible(false);
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.graphicSystem[6] = "traditional graphics";
                                 model.plotControl.traditionalPlotBeginning[6] =
                                        "plot(performance(prediction(fitted("+model.modelNameFinalStage+"),"+model.modelNameFinalStage+"$model[\""+model.dependentVar+"\"]),\"tpr\",\"fpr\")\n"; 
                                model.plotControl.traditionalPlotBeginning2[6] = "\nabline(0,1";
                                                                                
                                model.plotControl.keyPosition[6]="top";
                                
                                model.plotControl.plotType1[6] = "l: line";
                                
                                model.plotControl.lineType2[6] = "solid";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "blue";
                
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.latticeGraphicsAvailable[6] = false;
                                model.plotControl.mainTitle[6] = "ROC curve";
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.yAxisTitle[6] = "true positive rate";
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.xAxisTitle[6] = "false positive rate";
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = "ROC curve";
                                model.plotControl.lineType1[6] = "solid";
                                model.plotControl.linePoints1linewidth[6]="1";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.linePoints1Symbol[6]="";
                                model.plotControl.legendText2[6] = "45 degree line";
                                model.plotControl.lineType2[6] = "dashed";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "red";
                                model.plotControl.keyPosition[6] = "bottom";
                                
                            String call=
                               "plot(performance(prediction(fitted("+model.modelNameFinalStage+"), with("+model.dName+",factor("+model.dependentVar+"))) ,\"tpr\",\"fpr\"),col=\"blue\")\n"+                         
                               "abline(0, 1,lty=2,col=\"red\")\n"+
                               "legend(\"bottom\",c(\""+model.plotControl.legendText1[6]+"\",\""+model.plotControl.legendText2[6]+"\"),lwd=c(1,1),lty=c(\"solid\",\"dashed\"),col=c(\"blue\",\"red\"))\n"+
                                "grid(lwd = 2)";

                                commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel("par(mfrow = c(1, 1),mar=c(5,4,2,2))\n"+call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);
                                
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Actual & fitted vs time or observation")){
                                editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.graphicSystem[6] = "lattice";
                                                                
                               /* String actualValues = "";
                                 if (model.method.equals("pgmm")) {
                                       actualValues = "rapply("+model.modelName+"$model, function(x) x[,1])";
                                    } else {
                                        if (model.method.equals("lm")){
                                             actualValues =   model.modelName+"$model[,\""+model.dependentVar+"\"]";
                                       } else if (model.method.equals("dynlm")){
                                             actualValues =   "as.numeric("+model.modelName+"$model[,\""+model.dependentVar+"\"])";
                                        }else{
                                             actualValues =   model.modelName+"$y";
                                        } 
                                    }        */
                                
                                String vNameAdjustedTraditional =  actualValues;
                                String vNameAdjustedLattice =     (model.existsTS?actualValues:"ts("+actualValues+")");
                              /*  String fittedValues = new String();
                                if (model.method.equals("lm")||(model.method.equals("dynlm")||
                                         model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess"))) {
                                        fittedValues ="fitted("+pre.modelName+")";
                                } else if (model.method.equals("pgmm")) {
                                        fittedValues ="as.numeric(fitted("+pre.modelName+"))";
                                } else if (model.method.equals("tobit")) {
                                  fittedValues = "predict("+pre.modelName+NameSuffix+")";
                                          if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                              if (model.efeglmextra.leftLimitText.equals(""))
                                                fittedValues = "pmax("+fittedValues+",0)";
                                              else
                                                fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                          if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                                fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";
                               } else
                                    fittedValues = "("+pre.modelName+NameSuffix+
                                            "$model$\""+model.dependentVar+"\""+
                                             "-resid("+pre.modelName+NameSuffix+",type=\"response\"))";
                                */
                                model.plotControl.xLabels[6] = model.rowNamesOrDatesLabel;
                                model.plotControl.yLabels[6] = "round("+vNameAdjustedTraditional +",3)";
                                model.plotControl.traditionalPlotBeginning[6] = "plot(cbind("+vNameAdjustedTraditional+","+fittedValues+")";
                                model.plotControl.traditionalPlotFormula[6] =   vNameAdjustedTraditional;
                                
                                model.plotControl.latticePlotBeginning[6] = "xyplot(cbind("+vNameAdjustedLattice+","+fittedValues+"),superpose=TRUE"; 
                                model.plotControl.keyPosition[6]="top";
                                
                                model.plotControl.plotType1[6] = "l: line";
                                
                                model.plotControl.lineType2[6] = "solid";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "red";
                
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                model.plotControl.latticeGraphicsAvailable[6] = true;
                                model.plotControl.mainTitle[6] = "actual and fitted "+ model.dependentVar;
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[6] = "4";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = true;
                                model.plotControl.singleGraphMulti[6] = true;
                                model.plotControl.singleGraphMultiShow[6] = false;
                                model.plotControl.yAxisTitle[6] = model.dependentVar;
                                model.plotControl.manualyAxisRange[6]=false;
                                model.plotControl.xAxisTitle[6] = (model.existsTS?" ":"Observation");
                                model.plotControl.manualxAxisRange[6]=false;
                                model.plotControl.legendText1[6] = model.dependentVar;
                                model.plotControl.lineType1[6] = "solid";
                                model.plotControl.linePoints1linewidth[6]="1";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.linePoints1Symbol[6]="";
                                model.plotControl.legendText2[6] = "fitted";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints2color[6] = "red";
                                
                             
                            String call = model.plotControl.latticePlotBeginning[6] +",type=\"l\""+
                                    ",ylab=\""+ model.plotControl.yAxisTitle[6]+"\""+
                                    ",main=\""+model.plotControl.mainTitle[6]+"\""+
                                    ",xlab=\""+ model.plotControl.xAxisTitle[6]+"\""+
                                    ",col.symbol=\""+model.plotControl.linePoints1color[6]+"\""+
                                    ",par.settings=list(superpose.line = list(col = c(\""+model.plotControl.linePoints1color[6]+"\",\""+model.plotControl.linePoints2color[6]+"\")"+
                                    "))"+
                                    ",auto.key=list(text=c(\""+model.dependentVar+"\",\"fitted\"),space=\"top\",columns=2)"+
                                    ")" ;
   
                            commandsLogText.setText(commandsLogText.getText() +call);
                                requestedPlot = new ModelPlotPanel("print("+call+")");
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);
                                
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Partial regression plot*")) {
                    editButton.setEnabled(true);
                     try{ 
                    PlotsPanel6.removeAll();
                    PlotsPanel6.setVisible(false); 
                    Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                    String formulaforDepVarGivenOthers = pre.formula + "-" + model.efeaeplots.termsToPlot.toString();
                    String formulaforExplVarGivenOthers = model.efeaeplots.termsToPlot.toString() + "~" + Deducer.eval("strsplit(\""+pre.formula+"\",\"~\")[[1]][2]").asString() + "-" + model.efeaeplots.termsToPlot.toString();
               
                    String call2  = model.efeaeplots.termsToPlot.toString();
                    String toEvalFirst = 

                                pre.data + "$depVarGivenOthers <- resid("+model.method+"(formula="+formulaforDepVarGivenOthers+model.instruments+",data="+pre.data+
				(model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
				((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
				",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))" + "\n" +
                                
                                pre.data + "$explVarGivenOthers <- resid("+model.method+"(formula="+formulaforExplVarGivenOthers+model.instruments+",data="+pre.data+
				(model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
				((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
				",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))\n";
                        Deducer.eval(toEvalFirst);
		 
                        String call = "try(plot(xyplot(depVarGivenOthers~explVarGivenOthers"+
                                     ",data="+pre.data+",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)},ylab=\""+ model.dependentVar +"|others\",xlab=\""+ 
                                                                                        model.efeaeplots.termsToPlot.toString()+"|others\" ),new=FALSE),silent=TRUE)";
                        commandsLogText.setText(commandsLogText.getText() + toEvalFirst + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  
                }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Effect (prediction) plots-all variables to right")) {
                    Deducer.eval("suppressMessages(library(effects))");
                    Deducer.execute("suppressMessages(library(effects))");
                 try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="par(mar=c(5,4,2,2))\n"+
                                   "plot(allEffects("+pre.modelName+NameSuffix+",defaults.levels=50),ask=FALSE,rescale=FALSE)\n";                       
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel("try("+call+",silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
               
                }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Effect (prediction) plot-one variable to right*")) {
                    Deducer.eval("suppressMessages(library(effects))");
                    Deducer.execute("suppressMessages(library(effects))");
                 try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="plot(effect(term=\""+model.efeaeplots.termsToPlot +"\","+pre.modelName+NameSuffix+"),rescale=FALSE)";
                       commandsLogText.setText(commandsLogText.getText() + call);
                       requestedPlot = new ModelPlotPanel("plot("+call+")");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }      
                 
                }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Effect (prediction) plot")) {
                 try{
                     Deducer.eval("suppressMessages(library(effects))");
                     Deducer.execute("suppressMessages(library(effects))");
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");  
                       String call="plot(effect(term=\""+model.VariablesList+"\","+pre.modelName+NameSuffix+"),rescale=FALSE)"; 
                       commandsLogText.setText(commandsLogText.getText() + call);
                       requestedPlot = new ModelPlotPanel("plot("+call+")");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }      
                 
                 
               }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Partial regression plots*")
                       ) {
                   
                 editButton.setEnabled(true);
                   
                 if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")) {
                           PlotsPanel6.removeAll();
                           PlotsPanel6.setVisible(false);
                           Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                           if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6();
                                model.plotControl.previousPlot6 = true;
                            model.plotControl.traditionalGraphicsAvailable[6] = true;
                            model.plotControl.traditionalPlotBeginning[6] = /*"par(mar=c(5,4,2,2))\n"+*/
                                 "avPlots("+pre.modelName+NameSuffix+",terms=.~"+model.efeaeplots.termsToPlot;
                            model.plotControl.traditionalPlotFormula[6] = model.formula;
                            model.plotControl.traditionalPlotBeginning2[6]="in avPlots";
                            model.plotControl.showTickMarks[6] = true;
                            model.plotControl.showGrid[6] = true;
                            model.plotControl.pointLabels[6] = "row names";
                            model.plotControl.whichPointsToLabel[6] = "none";
                            model.plotControl.numExtremeValues[6] = "1";
                            model.plotControl.extremenessBasis[6] = "Cook's distance";
                            model.plotControl.extremenessScrollChoices[6] = "4";
                            model.plotControl.mostExtremeEnabled[6] = true;
                            model.plotControl.timeOrObsHorizontalAxis[6] = false;
                            model.plotControl.mainTitle[6] = "Added-Variable Plots";
                            model.plotControl.singleGraphMulti[6] = false;
                            model.plotControl.yAxisTitle[6] = "";
                            model.plotControl.xAxisTitle[6] = "";
                            model.plotControl.manualyAxisRange[6]=false;
                            model.plotControl.manualxAxisRange[6]=false;
                            model.plotControl.pointLabelTextSize[6] = "1.0";
                            model.plotControl.legendText1[6] = "_na_";
                            model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                            model.plotControl.symbolSize1[6] = "0.5";
                            model.plotControl.linePoints1color[6] = "blue";
                            model.plotControl.legendText2[6] = "_na_";
                            model.plotControl.linePoints2linewidth[6]="1";
                            model.plotControl.linePoints2color[6] = "red";
                            String call=model.plotControl.traditionalPlotBeginning[6]+",ask=F,pch=16,col=4)";
                                  
                            commandsLogText.setText(commandsLogText.getText() + call);
                            requestedPlot = new ModelPlotPanel("try("+call+",silent=TRUE)");
                            PlotsPanel6.add(requestedPlot);
                            Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                            PlotsPanel6.setVisible(true);
                  } else {
                     try{ 
                        PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String formulaforDepVarGivenOthers = pre.formula + "-" + model.efeaeplots.termsToPlot.toString();
                        String formulaforExplVarGivenOthers = model.efeaeplots.termsToPlot.toString() + "~" + Deducer.eval("strsplit(\""+pre.formula+"\",\"~\")[[1]][2]").asString() + "-" + model.efeaeplots.termsToPlot.toString();

                        String call2  = model.efeaeplots.termsToPlot.toString();
                        String toEvalFirst = 

                                    pre.data + "$depVarGivenOthers <- resid("+model.method+"(formula="+formulaforDepVarGivenOthers+model.instruments+",data="+pre.data+
                                    (model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
                                    ((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
                                    ",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))" + "\n" +

                                    pre.data + "$explVarGivenOthers <- resid("+model.method+"(formula="+formulaforExplVarGivenOthers+model.instruments+",data="+pre.data+
                                    (model.weights.getSize()==0 ? "" : ",weights="+model.weights.get(0))+
                                    ((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+",effect=c(\""+model.plmeffect+"\")"+
                                    ",model=c(\""+model.modeltype+"\"),na.action=na.omit"+"))\n";
                            Deducer.eval(toEvalFirst);
       JOptionPane.showInputDialog(null, "toEvalFirst="+toEvalFirst); 
                            String call = "try(plot(xyplot(depVarGivenOthers~explVarGivenOthers"+
                                         ",data="+pre.data+",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)},ylab=\""+ model.dependentVar +"|others\",xlab=\""+ 
                                                                                            model.efeaeplots.termsToPlot.toString()+"|others\" ),new=FALSE),silent=TRUE)";
                            commandsLogText.setText(commandsLogText.getText() + toEvalFirst + call);
                            requestedPlot = new ModelPlotPanel(call);
                            PlotsPanel6.add(requestedPlot);
                            Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                            PlotsPanel6.setVisible(true);
                       }catch(Exception e){
                            new ErrorMsg(e);
                        }   
                     }
                }  else if (model.efeaeplots.moreDiagnosticPlots.equals("Simple regression function plot")) {
                 editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                 try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="xyplot("+model.dependentVar+"~"+model.termsArray[0]+", data="+model.dName+",type=c(\"p\",\"r\"),col.line=2,col.symbol=1)";     
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel("try(print("+call+"),silent=TRUE)");
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                         
                  
                  
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Partial residual plots*")||
                          model.efeaeplots.moreDiagnosticPlots.equals("Partial residual plot")) {         
                                editButton.setEnabled(true);
                                PlotsPanel6.removeAll();
                                PlotsPanel6.setVisible(false);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                if (model.plotControl.previousPlot6) 
                                     model.refreshPlotControl6(); 
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[6] = true;
                                if (model.efeaeplots.moreDiagnosticPlots.equals("Partial residual plot")){
                                   model.plotControl.traditionalPlotBeginning[6] = "crPlots("+pre.modelName+NameSuffix+",terms=.~"+model.VariablesList +",ask=F";
                                } else {
                                   model.plotControl.traditionalPlotBeginning[6] = "crPlots("+pre.modelName+NameSuffix+",terms=.~"+model.efeaeplots.termsToPlot +",ask=F";
                                }
                                model.plotControl.showGrid[6] = true;
                                model.plotControl.showTickMarks[6] = true;
                                model.plotControl.pointLabels[6] = "row names";
                                model.plotControl.whichPointsToLabel[6] = "none";
                                model.plotControl.numExtremeValues[6] = "1";
                                model.plotControl.extremenessBasis[6] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[6] = "4";
                                model.plotControl.mostExtremeEnabled[6] = true;
                                model.plotControl.timeOrObsHorizontalAxis[6] = false;
                                model.plotControl.singleGraphMulti[6] = false;
                                model.plotControl.mainTitle[6] = "Component + Residual Plots"+(model.termsArray.length>4?",1st four":"");
                                model.plotControl.legendText1[6] = "";
                                model.plotControl.linePoints1Symbol[6] = "16: solid circle";
                                model.plotControl.symbolSize1[6] = "0.5";
                                model.plotControl.linePoints1color[6] = "blue";
                                model.plotControl.traditionalPlotBeginning2[6]="in crPlots";
                                model.plotControl.legendText2[6] = "";
                                model.plotControl.linePoints2color[6] = "red";
                                model.plotControl.linePoints2linewidth[6]="1";
                                model.plotControl.linePoints3color[6] = "green";    
                                
                                
                                String call = model.plotControl.traditionalPlotBeginning[6]+",col=\""+model.plotControl.linePoints1color[6]+"\")"+
                                         (model.termsArray.length>4?",main=\""+model.plotControl.mainTitle[6]+"\"":"")+
                                         model.plotControl.traditionalPlotEnd[6];
                                
                                commandsLogText.setText(commandsLogText.getText() + call);
                                requestedPlot = new ModelPlotPanel(call);
                                PlotsPanel6.add(requestedPlot);
                                Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                                PlotsPanel6.setVisible(true);
                 
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Scatter plots with response variable*")) {
                 
                  {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="pairs(~"+ model.dependentVar + "+" +
                                              model.efeaeplots.termsToPlot +",data="+pre.data+",diag.panel=function(x,...)"+
		              "{ par(new = TRUE);"+
  		              "  hist(x, col = \"light blue\", probability = TRUE, axes = FALSE, main = \"\");"+
		              "  lines(density(x), col = \"red\", lwd = 3);  rug(x)})";
                       commandsLogText.setText(commandsLogText.getText() + call.replaceAll(";","\\\n"));
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }
                 
                  }  
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Scatter plot: response variable vs explanatory variable")) {
                 
                  {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call="pairs(~"+ model.dependentVar + "+" +
                                    model.termsFinalStage.firstElement()+",data="+pre.data+",diag.panel=function(x,...)"+
		              "{ par(new = TRUE);"+
  		              "  hist(x, col = \"light blue\", probability = TRUE, axes = FALSE, main = \"\");"+
		              "  lines(density(x), col = \"red\", lwd = 3);  rug(x)})";	

                       commandsLogText.setText(commandsLogText.getText() + call.replaceAll(";","\\\n"));
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }
                  
                  }     
                }else if (model.efeaeplots.moreDiagnosticPlots.equals("Scatter plots, both explanatory variables")){
                  {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");

                       String call="pairs(~"+model.VariablesList.replace(",","+") +",data="+pre.data+",diag.panel=function(x,...)"+
		              "{ par(new = TRUE);"+
  		              "  hist(x, col = \"light blue\", probability = TRUE, axes = FALSE, main = \"\");"+
		              "  lines(density(x), col = \"red\", lwd = 3);  rug(x)})";	

                       commandsLogText.setText(commandsLogText.getText() + call.replaceAll(";","\\\n"));
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
                 } else if (model.efeaeplots.moreDiagnosticPlots.equals("confidence ellipse for intercept & one highlighted variable*")) {
                 {try{
                       PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call="confidenceEllipse("+pre.modelName+NameSuffix+",which.coef="+model.efeaeplots.termsToPlot+",Scheffe=TRUE,main=\"95% Confidence Ellipse\")";
                      
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }        

               }else if (model.efeaeplots.moreDiagnosticPlots.equals("Scatter plots without response variable*")){
                  {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="pairs(~"+model.efeaeplots.termsToPlot +",data="+pre.data+",diag.panel=function(x,...)"+
		              "{ par(new = TRUE);"+
  		              "  hist(x, col = \"light blue\", probability = TRUE, axes = FALSE, main = \"\");"+
		              "  lines(density(x), col = \"red\", lwd = 3);  rug(x)})";	

                       commandsLogText.setText(commandsLogText.getText() + call.replaceAll(";","\\\n"));
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
                 } else if (model.efeaeplots.moreDiagnosticPlots.equals("confidence ellipse for intercept & one highlighted variable*")) {
                 {try{
                       PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call="confidenceEllipse("+pre.modelName+NameSuffix+",which.coef="+model.efeaeplots.termsToPlot+",Scheffe=TRUE,main=\"95% Confidence Ellipse\")";
                      
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }     
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("confidence ellipse for intercept & explanatory variable")) {
                 {try{
                       PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                        String call="confidenceEllipse("+pre.modelName+NameSuffix+",which.coef=c(\"(Intercept)\",\""+model.VariablesList+"\"),Scheffe=TRUE,main=\"95% Confidence Ellipse\")";
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }  
                  
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("confidence ellipse for 2 highlighted variables*")) {
                 {try{
                       PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="confidenceEllipse("+pre.modelName+NameSuffix+",which.coef="+model.efeaeplots.termsToPlot+",Scheffe=TRUE,main=\"95% Confidence Ellipse\")";
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }   
                 } else if (model.efeaeplots.moreDiagnosticPlots.equals("confidence ellipse for both variables to the right")) {
                
                 try{
                       PlotsPanel6.removeAll();
                        PlotsPanel6.setVisible(false);
                        Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="confidenceEllipse("+pre.modelName+NameSuffix+",Scheffe=TRUE,main=\"95% Confidence Ellipse\")";
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                   
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("all variables to the right")) {
                 {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       String call="par(mar=c(5,4,2,2))\n"+
                                "try(plot(allEffects("+pre.modelName+NameSuffix+",defaults.levels=50),ask=FALSE),silent=TRUE)\n";
                        commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }   
                } else if (model.efeaeplots.moreDiagnosticPlots.equals("one highlighted variable to the right")) {
                 {try{
                       PlotsPanel6.removeAll();
                       PlotsPanel6.setVisible(false);
                       Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                       Deducer.execute("plotEffect <- plot(effect(term=\""+model.efeaeplots.termsToPlot +"\","+pre.modelName+NameSuffix+"))");
                       String call="plot(plotEffect);";
                       commandsLogText.setText(commandsLogText.getText() + call);
                        requestedPlot = new ModelPlotPanel(call);
                        PlotsPanel6.add(requestedPlot);
                        Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[6] <- dev.cur()");
                        PlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }   
                }
                
                if (model.efeaeplots.resvFitted == true)
                {try{

                    String call = "try(plot("+pre.modelName+NameSuffix+",1,sub.captions=\"\"))";
			addexpTab = new ModelPlotPanel(call);
			tabs.setComponentAt(4,addexpTab);
                        tabs.setSelectedComponent(addexpTab);
                    commandsLogText.setText(commandsLogText.getText() + call);
		}catch(Exception e){
			new ErrorMsg(e);
		}
                }
                
                     

	}
            public void setHanselExport(GMModel mod){
		model = mod;
                if (model.hanselExport.okay){
                  String cmd ="";
                  cmd =model.runHanselExport(cmd,model.modelName,false,model.data,false);
                  Deducer.eval(cmd);
                  Deducer.eval("if (exists('.hansel.working.env$export.tmp')) {rm(.hansel.working.env$export.tmp)}");
                } 
	}           

               
        	public void specifyClicked() {

                  GMDialog.runit(model.EstimationMethod,(sizeAdjuster==1f));  
                    
		this.dispose();
                }
                                
	public void run(){
                model.run(false,pre,true);
		this.dispose();
		GMDialog.setLastModel(model);
		Deducer.eval("rm('"+pre.data.split("\\$")[1]+"','"+pre.modelName.split("\\$")[1]+"',envir="+Deducer.guiEnv+")");
	}

        public void residTimeClicked(String cmd){
                GMModel modelTS = new GMModel();
                modelTS.efeglmextra.TSMainTitle = "";
                modelTS.data = model.data;
                modelTS.dName = model.dName;
                modelTS.existsTS = model.existsTS;
                modelTS.start_of_original_data = model.start_of_original_data;
                modelTS.frequency_of_original_data = model.frequency_of_original_data;
                modelTS.panelModel = false;

                if (model.method.equals("vec2var")) {
                            { 
                             changeTermClicked(cmd);
                             modelTS.terms.addElement(pre.modelName+"$resid[,\"resids of "+model.termsArray[0]+"\"]");
                             modelTS.efeglmextra.TSlabels = "\""+model.efeglmextra.chosenTerms[0]+" residuals\"";
                             if (model.efeglmextra.chosenTerms.length>1){
                                 editButton.setEnabled(false);
                                 for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                                        modelTS.terms.addElement(pre.modelName+"$resid[,\"resids of "+model.termsArray[i]+"\"]");
                                        modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ",\""+model.efeglmextra.chosenTerms[i]+" residuals\""; 
                                 }
                             }
                             modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels;
                            }
                
                 }else if (model.method.equals("VAR")) {
                        if (cmd.equals("Residuals -> Time Series Explorer")||cmd.equals("plotsvsObsOrTime")){
                            { 
                             modelTS.data = "data.frame(";
                             changeTermClicked(cmd);
                             modelTS.terms.addElement(pre.modelName+"$varresult$"+model.efeglmextra.chosenTerms[0].replace("(","_").replace(")", "").replace("[","").replace("]","")+"$resid");
                             modelTS.data +=          pre.modelName+"$varresult$"+model.efeglmextra.chosenTerms[0].replace("(","_").replace(")", "").replace("[","").replace("]","")+"$resid";
                             modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+" residuals";
                             if (model.efeglmextra.chosenTerms.length>1){
                                 editButton.setEnabled(false);
                                 for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                                        modelTS.terms.addElement(pre.modelName+"$varresult$"+model.efeglmextra.chosenTerms[i].replace("(",".").replace(")",".")+"$resid");
                                        modelTS.data += ","+     pre.modelName+"$varresult$"+model.efeglmextra.chosenTerms[i].replace("(",".").replace(")",".")+"$resid";
                                        modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ","+model.efeglmextra.chosenTerms[i]+" residuals";
                                 }
                             }
                             modelTS.data += ")";
                             modelTS.dName = modelTS.data;
                            }
                        } else {
                            changeTermClicked(cmd);
                            modelTS.efeglmextra.PriorModelName= pre.modelName+"$varresult$"+model.efeglmextra.chosenTerms[0].replace("(",".").replace(")",".");
                            modelTS.method = "lm";
                            modelTS.EstimationMethod = "Ordinary Least Squares";
                            modelTS.efeglmextra.previousEstimation = true;
                            modelTS.efeglmextra.classicView = false;
                            modelTS.dependentVar = model.efeglmextra.chosenTerms[0];
                        }
                           
                   }else if (model.method.equals("ca.jo")||model.method.equals("cajolst")) {
                     changeTermClicked(cmd);    
                     String prefix ="";
                     String suffix ="";
                     String labelsSuffix = "";
                     if (model.efeglmextra.diffOrLevelJo.equals("the variable itself")){
                            modelTS.data = pre.modelName + "@x";
                            modelTS.dName = modelTS.data;
                            prefix = "";
                            suffix = "";
                     }
                     else if (model.efeglmextra.diffOrLevelJo.equals("residuals associated with the differenced variable")){
                            modelTS.data = pre.modelName + "@R0";
                            modelTS.dName = modelTS.data;
                            prefix = "R0.";
                            suffix = ".d";
                            labelsSuffix = " dif residuals";
                     }
                     else if (model.efeglmextra.diffOrLevelJo.equals("residuals associated with the variable's lagged levels")){
                            modelTS.data = pre.modelName + "@RK";
                            modelTS.dName = modelTS.data;
                            prefix = "RK.";
                            suffix = ".l1";
                            labelsSuffix = " level (one-lag) residuals";
                     } 
                     modelTS.terms.addElement(/*pre.modelName+*/prefix+model.efeglmextra.chosenTerms[0]+suffix);
                     modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+labelsSuffix; 
                             if (model.efeglmextra.chosenTerms.length>1){
                                 editButton.setEnabled(false);
                                 for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                                        modelTS.terms.addElement(prefix+model.efeglmextra.chosenTerms[i]+suffix);
                                        modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ","+model.efeglmextra.chosenTerms[i]+labelsSuffix+""; 
                                 }
                             }        
                             
                } else if (model.method.equals("ca.po")) {
                    changeTermClicked(cmd);
                    String prefix ="";
                    String suffix ="";
                    String labelsSuffix ="";
                    if (model.efeglmextra.cointPOType.equals("Pu")){
                        if (model.efeglmextra.varOrCointRelpo.equals("the variable itself")){
                                modelTS.data = pre.modelName + "$"+model.efeglmextra.chosenTerms[0]+"_left@z";
                                modelTS.dName = modelTS.data;
                                prefix ="$"+model.efeglmextra.chosenTerms[0].replace("(","_").replace(")", "").replace("[","").replace("]","")+"_left@z[,\"";
                                suffix ="\"]";
                        } else {
                                modelTS.efeglmextra.TSMainTitle = "Cointegrating Relationship";
                                prefix ="$";
                                suffix ="_left@res";
                                labelsSuffix= " on left side";
                        }     
                    } else /* type is Pz */{
                        if (model.efeglmextra.varOrCointRelpo.equals("the variable itself")){
                                    modelTS.data = pre.modelName +"@z";
                                    prefix ="";
                                    suffix ="";
                        } else{ 
                                     modelTS.efeglmextra.TSMainTitle = "Cointegrating Relationship";
                                     modelTS.data = pre.modelName +"@res";
                                     prefix ="";
                                     suffix ="";
                                     labelsSuffix= " on left side";
                                     modelTS.efeglmextra.TSlabels = "  "; 
                        }
                    }
                       if (model.efeglmextra.cointPOType.equals("Pu"))
                          modelTS.data = "data.frame("+pre.modelName+prefix+
                                  (prefix.contains("[")?model.efeglmextra.chosenTerms[0]:model.efeglmextra.chosenTerms[0].replace("(","_").replace(")", ""))
                                  +suffix;
                       modelTS.terms.addElement((model.efeglmextra.cointPOType.equals("Pu")?pre.modelName:"")+prefix+model.efeglmextra.chosenTerms[0]+suffix);   
                       
                       if (model.efeglmextra.chosenTerms.length>1){
                            editButton.setEnabled(false);
                            modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+labelsSuffix; 
                            for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                              modelTS.terms.addElement((model.efeglmextra.cointPOType.equals("Pu")?pre.modelName:"")+prefix+model.efeglmextra.chosenTerms[i]+suffix);
                              if (model.efeglmextra.cointPOType.equals("Pu"))
                                  modelTS.data += ","+pre.modelName+prefix+model.efeglmextra.chosenTerms[i]+suffix;
                              modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ","+model.efeglmextra.chosenTerms[i]+labelsSuffix; ; 
                             }
                         }
                       else {
                             if (modelTS.efeglmextra.TSMainTitle.equals("Cointegrating Relationship"))
                                 modelTS.efeglmextra.TSMainTitle = "Cointegrating relation: "+ model.efeglmextra.chosenTerms[0]+" on left side";
                             else 
                                modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+labelsSuffix; 

                         }
                       if (model.efeglmextra.cointPOType.equals("Pu"))
                          modelTS.data += ")";     
                        
                         modelTS.dName = modelTS.data;
                 } else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                    if (cmd.equals("Output -> Time Series Explorer")||cmd.equals("plotsvsObsOrTime")){
                        { 
                        changeTermClicked(cmd);
                        String prefix ="";
                        String suffix ="";
                        String labelsPrefix = "";
                        String labelsSuffix = "";
                         if (model.efeglmextra.diffOrLevelEG.equals("the variable itself")){
                               prefix ="$model[,\"";
                                suffix ="\"]";
                         }
                         else if (model.efeglmextra.diffOrLevelEG.equals("the cointegrating relation using the variable on the left-side")){
                                prefix ="";
                                suffix ="$residuals";
                                labelsSuffix = " on left side";
                                modelTS.efeglmextra.TSMainTitle = "Cointegrating Relationship";
                         }    

                         
                         modelTS.data = "data.frame("+ pre.modelName+"$"+model.efeglmextra.chosenTerms[0].toString().replace("(","_").replace(")", "")
                                                  +prefix+(model.efeglmextra.diffOrLevelEG.equals("the variable itself")?model.efeglmextra.chosenTerms[0]:"")+suffix;
                         modelTS.terms.addElement(pre.modelName+"$"+model.efeglmextra.chosenTerms[0].toString().replace("(","_").replace(")", "")
                                                  +prefix+(model.efeglmextra.diffOrLevelEG.equals("the variable itself")?model.efeglmextra.chosenTerms[0]:"")+suffix);
                             if (model.efeglmextra.chosenTerms.length>1){
                                 editButton.setEnabled(false);
                                 modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+labelsSuffix; 
                                 for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                                        modelTS.terms.addElement(pre.modelName+"$"+model.efeglmextra.chosenTerms[i].toString().replace("(","_").replace(")", "")
                                                  +prefix+(model.efeglmextra.diffOrLevelEG.equals("the variable itself")?model.efeglmextra.chosenTerms[i]:"")+suffix);
                                        modelTS.data += ","+pre.modelName+"$"+model.efeglmextra.chosenTerms[i].toString().replace("(","_").replace(")", "")
                                                  +prefix+(model.efeglmextra.diffOrLevelEG.equals("the variable itself")?model.efeglmextra.chosenTerms[i]:"")+suffix;
                                        modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ","+model.efeglmextra.chosenTerms[i]+labelsSuffix; ; 
                                 }
                             }
                             else {
                                 if (modelTS.efeglmextra.TSMainTitle.equals("Cointegrating Relationship")){
                                     modelTS.efeglmextra.TSMainTitle = "Cointegrating relation: "+ model.efeglmextra.chosenTerms[0].toString()+" on left side";
                                    /* modelTS.efeglmextra.TSlabels = "residuals"; */
                                     modelTS.efeglmextra.TSlabels = "  ";
                                 }else 
                                    modelTS.efeglmextra.TSlabels = model.efeglmextra.chosenTerms[0]+labelsSuffix; 
                             }
                         modelTS.data += ")";
                         modelTS.dName = modelTS.data;
                        }
                    } else {
                        changeTermClicked(cmd);
                        modelTS.efeglmextra.PriorModelName= pre.modelName+"$"+model.efeglmextra.chosenTerms[0];
                       /* modelTS.data = model.data;*/
                        modelTS.method = "lm";
                        modelTS.EstimationMethod = "Ordinary Least Squares";
                        modelTS.efeglmextra.previousEstimation = true;
                        modelTS.efeglmextra.classicView = false;
                        modelTS.depvar = model.efeglmextra.chosenTerms[0].toString();
                        modelTS.dependentVar = model.efeglmextra.chosenTerms[0].toString();
                    }
                 } else if (model.method.equals("plm")&(cmd.equals("Model -> OLS Explorer"))){
                        String lmCmd =
                        model.modelName+"$HanselAddOns$equivalentModel.lm <- lm(formula="+model.formula+
                        ((model.plmPanelType.equals("within (\"fixed\") effects")) ?
                                ((model.plmeffect.equals("individual")) ? "+as.factor("+model.modelPanelCSVar+")," : 
                                ((model.plmeffect.equals("time")) ? "+as.factor("+model.modelPanelTimeVar+"),":"+as.factor("+model.modelPanelCSVar+")+as.factor("+model.modelPanelTimeVar+"),"))
                                :",")+
                        "data= "+model.dName+                       
                        (model.modelWeights.length()==0 ? "" : ", weights="+model.modelWeights)+
				((model.subset==null || model.subset.length()==0) ? "" : ",subset = "+model.subset)+
				",na.action=na.omit"
                                +")";
                        Deducer.eval(lmCmd);
                        modelTS.efeglmextra.PriorModelName= model.modelName+"$HanselAddOns$equivalentModel.lm";
                        modelTS.method = "lm";
                        modelTS.dependentVar = model.dependentVar;
                        modelTS.EstimationMethod = "Ordinary Least Squares";
                        modelTS.efeglmextra.previousEstimation = true;
                        modelTS.efeglmextra.classicView = false;

                }else if (model.panelModel& /*note: not!*/ !model.method.equals("cipstest")) {
                        if (cmd.equals("Residuals -> Time Series Explorer")||cmd.equals("plotsvsObsOrTime")){
                               changeTermClicked(cmd);
                               
                               if (model.method.equals("pgmm")){
                                   for(int i=0;i<model.efeglmextra.chosenTerms.length;i++){
                                         Deducer.eval(pre.modelName+"$HanselAddOns$temp<-data.frame("+pre.modelName+"$resid$\""+model.efeglmextra.chosenTerms[i]+"\")"+
                                                "\ncolnames("+pre.modelName+"$HanselAddOns$temp)<-\""+model.efeglmextra.chosenTerms[i]+"\""+
                                                "\n eval(parse(text=paste(\""+pre.modelName+"$HanselAddOns$"+model.modelPanelCSVar+"_"+model.efeglmextra.chosenTerms[i]+"_residuals <-zoo(ts("+pre.modelName+"$HanselAddOns$temp,start=\",rownames("+pre.modelName+"$HanselAddOns$temp)[1],\"))\")))");   
                                   }
                               } else {
                                   for(int i=0;i<model.efeglmextra.chosenTerms.length;i++){
                                         Deducer.eval(pre.modelName+"$HanselAddOns$temp<-subset(cbind("+model.dName+"[1:2],"+pre.modelName+"$resid),"+model.modelPanelCSVar+"==\""+model.efeglmextra.chosenTerms[i]+"\")"+
                                                "\n "+pre.modelName+"$HanselAddOns$"+model.modelPanelCSVar+"_"+model.efeglmextra.chosenTerms[i]+"_residuals <-zoo(ts("+pre.modelName+"$HanselAddOns$temp,start="+pre.modelName+"$HanselAddOns$temp[1,2]))[,3]");               
                                 }
                               }
                               if (model.efeglmextra.chosenTerms.length > 1) {
                                   editButton.setEnabled(false);
                                   modelTS.efeglmextra.TSMainTitle = "  ";
                               }
                               modelTS.attachment=pre.modelName+"$HanselAddOns";
                               modelTS.data =pre.modelName+"$HanselAddOns";
                               modelTS.dName = modelTS.data;
                               modelTS.panelModel = true;
                               modelTS.terms.addElement(model.modelPanelCSVar+"_"+model.efeglmextra.chosenTerms[0]+"_residuals");
                               modelTS.efeglmextra.TSlabels = model.modelPanelCSVar+" "+model.efeglmextra.chosenTerms[0]+" residuals"; 
                               if (model.efeglmextra.chosenTerms.length>1){
                                 for(int i=1;i<model.efeglmextra.chosenTerms.length;i++){
                                        modelTS.terms.addElement(model.modelPanelCSVar+"_"+model.efeglmextra.chosenTerms[i]+"_residuals");
                                        modelTS.efeglmextra.TSlabels = modelTS.efeglmextra.TSlabels + ",\""+model.modelPanelCSVar+" "+model.efeglmextra.chosenTerms[i]+" residuals\""; 
                                 }
                             }
                            }
                    
                }else if (model.method.equals("tobit")) {
                    
                    /* String actualValues = pre.modelName+NameSuffix+
                                        (model.method.equals("tobit")? "$y[,1]" : "$model$\""+model.dependentVar+"\"");*/
                            /* String fittedValues = actualValues+"-resid("+pre.modelName+NameSuffix+",type=\"response\")";

                                  if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                      if (model.efeglmextra.leftLimitText.equals(""))
                                        fittedValues = "pmax("+fittedValues+",0)";
                                      else
                                        fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                  if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                        fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";*/
                             String residValues = actualValues +"-"+ fittedValues;
                             modelTS.terms.addElement(residValues);
                }else
                    {
                    modelTS.terms.addElement("resid("+pre.modelName+",type=\"respons\")");
                    modelTS.efeglmextra.TSlabels = "residuals"; 
                    }
                if (cmd.equals("plotsvsObsOrTime")){
                    varvsObsOrTimeClicked(modelTS);
                }else if (cmd.equals("Model -> OLS Explorer")){
                    Deducer.eval("library(lmtest)\n library(xtable)\n library(sandwich)");
                    GMExplorer exp = new GMExplorer(modelTS,"GLM",sizeAdjuster);
                    exp.setLocation(20, 20);
                    exp.setVisible(true);
                    WindowTracker.addWindow(exp);
                }else{
              	    NMTimeSExplorer exp = new NMTimeSExplorer(modelTS,true);
                    exp.setLocation(20, 20);
                    exp.setVisible(true);
                    WindowTracker.addWindow(exp);
                }
	}
        
        public void eigenvalueTestClicked(){
            String outputCall = "capture.output(summary("+pre.modelName+".eigen))";
            ArrayList tmp = new ArrayList();
                   try{
                   String[] out  = Deducer.eval(outputCall).asStrings();
                           for(int i=0;i<out.length;i++){
                            tmp.add(out[i]+"\n");
                             }
                     }catch(Exception e){
                        new ErrorMsg(e);
                    }
             String typePrev = "";
             for(int i =0;i<tmp.size();i++)
                typePrev+=tmp.get(i);   
             previewClassic.setText(typePrev);
             previewClassic.setCaretPosition(0);  
             previewClassicPanel.setVisible(false);
             previewClassicPanel.setVisible(true);
        };
        public void traceTestClicked(){
            String outputCall = "capture.output(summary("+pre.modelName+"))";
            ArrayList tmp = new ArrayList();
                   try{
                   String[] out  = Deducer.eval(outputCall).asStrings();
                           for(int i=0;i<out.length;i++){
                            tmp.add(out[i]+"\n");
                             }
                     }catch(Exception e){
                        new ErrorMsg(e);
                    }
             String typePrev = "";
             for(int i =0;i<tmp.size();i++)
                typePrev+=tmp.get(i);   
             previewClassic.setText(typePrev);
             previewClassic.setCaretPosition(0);  
             previewClassicPanel.setVisible(false);
             previewClassicPanel.setVisible(true);
        };
        
        
        public void rankRestrictionsClicked(){
            this.noReactiontoAction = true;
            if (!this.model.varOptions.rankRestriction.equals((String)this.rankRestriction.getSelectedItem().toString())) {
                 this.model.varOptions.rankRestriction = (String)this.rankRestriction.getSelectedItem().toString();
                 String numCointEqtns = this.model.varOptions.rankRestriction.substring(0,1);
               {                                      
                String outputCall = "capture.output(cajorls("+this.model.modelName+",r="+numCointEqtns+"))";
                ArrayList tmp = new ArrayList();
                       try{
                       String[] out  = Deducer.eval(outputCall).asStrings();

                      tmp.add("Coefficient estimates for VECM, number of cointegrating vector ="+numCointEqtns+"\n");
                               for(int i=5;i<out.length;i++){
                                tmp.add(out[i]+"\n");
                                 }
                         }catch(Exception e){
                            new ErrorMsg(e);
                        }
                 String cajorlsPrev = "";
                 for(int i =0;i<tmp.size();i++)
                    cajorlsPrev+=tmp.get(i);   
                 vecRestrictedClassic.setText(cajorlsPrev);
                 vecRestrictedClassic.setCaretPosition(0); 
                 vecRestrictedClassicSmall.setText(cajorlsPrev);
                 vecRestrictedClassicSmall.setCaretPosition(0); 
                 vecRestrictedClassicPanel.setVisible(false);
                 vecRestrictedClassicPanel.setVisible(true);

                }

                 setModel(this.model,true);
                 this.noReactiontoAction = false;
            }
        };
        
        public void robustSEChangeGLMClicked(){
             this.noReactiontoAction = true;
             this.model.glmvcov =(String)this.glmvcovsExplorer.getSelectedItem();
             addexp.setText("");
             setModel(this.model,true);
             setaddClassicCommand(this.model);
             if (pre.call.contains("statements for including robust standard error")) {
                 commandsLogText.setText(commandsLogText.getText()+"\n"
                                     +"#***"+pre.call.substring(pre.call.indexOf("statements for including robust standard error")));
                 }
             commandsLogText.setCaretPosition(0);
             this.noReactiontoAction = false;     
        };
        
         public void robustSEChangetobitClicked(){
             this.noReactiontoAction = true;
             this.model.tobitvcov =(String)this.tobitvcovsExplorer.getSelectedItem();
             addexp.setText("");
             setModel(this.model,true);
             setaddClassicCommand(this.model);
             if (pre.call.contains("statements for including robust standard error")) {
             commandsLogText.setText(commandsLogText.getText()+"\n"
                                     +"#***"+pre.call.substring(pre.call.indexOf("statements for including robust standard error")));
             }
             commandsLogText.setCaretPosition(0);
             this.noReactiontoAction = false;
             
        };
        
        public void robustSEChangeplmClicked(){
             this.noReactiontoAction = true;
             this.model.plmvcov =(String)this.plmvcovsExplorer.getSelectedItem();
             addexp.setText("");
             setModel(this.model,true);
             setaddClassicCommand(this.model);
             if (pre.call.contains("statements for including robust standard error")) {
             commandsLogText.setText(commandsLogText.getText()+"\n"
                                     +"#***"+pre.call.substring(pre.call.indexOf("statements for including robust standard error")));
             }
             commandsLogText.setCaretPosition(0);
             this.noReactiontoAction = false;
        };
        
        public void robustSEChangestslsClicked(){
             this.noReactiontoAction = true;
             this.model.stslsvcov =(String)this.stslsvcovsExplorer.getSelectedItem();
             addexp.setText("");
             setModel(this.model,true);
             setaddClassicCommand(this.model);
             if (pre.call.contains("statements for including robust standard error")) {
             commandsLogText.setText(commandsLogText.getText()+"\n"
                                     +"#***"+pre.call.substring(pre.call.indexOf("statements for including robust standard error")));
             }
             commandsLogText.setCaretPosition(0);
             this.noReactiontoAction = false;
        };
        
        public void robustSEChangegstslsClicked(){
             this.noReactiontoAction = true;
             this.model.gstslsvcov =(String)this.gstslsvcovsExplorer.getSelectedItem();
             addexp.setText("");
             setModel(this.model,true);
             setaddClassicCommand(this.model);
             if (pre.call.contains("statements for including robust standard error")) {
             commandsLogText.setText(commandsLogText.getText()+"\n"
                                     +"#***"+pre.call.substring(pre.call.indexOf("statements for including robust standard error")));
             }
             commandsLogText.setCaretPosition(0);
             this.noReactiontoAction = false;
        };
        
	public void anovaClicked(String cmd){
		GMExplorerAnova opt = new GMExplorerAnova(this,model,pre,cmd);
		opt.setLocationRelativeTo(this);
		opt.setVisible(true);
		setaddexp(model);
                opt.setModel(model,pre);//This resets choices to false
		model.efeaetext.anova=false;
                
	}
        public void vecExploreVARClicked(){
                GMModel modelvec2var = new GMModel();
                modelvec2var.efeglmextra.TSMainTitle = "";
                modelvec2var.data = model.data;
                modelvec2var.start_of_original_data = model.start_of_original_data;
                modelvec2var.frequency_of_original_data = model.frequency_of_original_data;
                String numCointEqtns = this.model.varOptions.rankRestriction.substring(0,1);
                Deducer.eval("library(vars)");
                Deducer.eval(this.model.modelName+"r"+numCointEqtns+"_vec2var <-vec2var("+this.model.modelName+",r="+numCointEqtns+")");
                modelvec2var.efeglmextra.PriorModelName= this.model.modelName+"r"+numCointEqtns+"_vec2var";
                modelvec2var.method = "vec2var";
                modelvec2var.EstimationMethod = "VAR for VEC of rank "+numCointEqtns;
                modelvec2var.efeglmextra.previousEstimation = true;
                modelvec2var.efeglmextra.classicView = true;
                modelvec2var.existsTS = model.existsTS;

                GMExplorer exp = new GMExplorer(modelvec2var,"vec2var",sizeAdjuster);
                exp.setLocation(20, 20);
                exp.setVisible(true);
                WindowTracker.addWindow(exp);
	}
        
        
        public void changeTermClicked(String cmd){
		GMExplorerTermChooser termChooser = new GMExplorerTermChooser(this,model,pre,cmd);
                termChooser.setLocationRelativeTo(this);
		termChooser.setVisible(true);
                if (cmd.equals("export"))
                      setHanselExport(model);
                termChooser.setModel(model, pre);//This resets choices to false 
            }         
                
        public void diagnosticTestsClicked(){
                if (model.method.equals("VAR")||model.method.equals("vec2var")) {
                   GMExplorerDiagTestsVAR dt = new GMExplorerDiagTestsVAR(this,model,pre);
                   dt.setLocationRelativeTo(this);
		   dt.setVisible(true);
		   setaddexp(model);
                   dt.setModel(model, pre);//This resets choices to false
                } else if ((model.method.equals("plm")||model.method.equals("pvcm")||model.method.equals("pggls")||
                            model.method.equals("pgmm"))||model.method.equals("pcce")||model.method.equals("pmg")
                           ||model.method.equals("spml")||model.method.equals("spgm")
                          ){
                   GMExplorerDiagTestsPanel dt = new GMExplorerDiagTestsPanel(this,model,pre);
                   dt.setLocationRelativeTo(this);
		   dt.setVisible(true);
		   setaddexp(model);
                   dt.setModel(model, pre);//This resets choices to false

                }
                else {
                   GMExplorerDiagTestsMain dt = new GMExplorerDiagTestsMain(this,model,pre);
                   dt.setLocationRelativeTo(this);
		   dt.setVisible(true);
                   setaddexp(model);
                   dt.setModel(model, pre);//This resets choices to false
                }

	}
         /*       public void spatialTestsClicked(){
                String[] nbObjects = {""};
                DefaultListModel nbObjectsList = new DefaultListModel();
                try{
                    nbObjects= Deducer.eval(
                     "c(Filter( function(x) ('nb' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ) ,"+
                     "Filter( function(x)  ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),"+
                     "Filter( function(x)  ('xxxxxxxx' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),"+
                     "Filter( function(x)  ('yyyyyyyy' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),\" \",\" \")    ").asStrings();
                     for(int i=0;i<(nbObjects.length);i++){
                     nbObjectsList.addElement(nbObjects[i]);    
                     }    
                }catch(Exception e){
                     new ErrorMsg(e);
                    } 
                this.model.efeglmextra.nbList = nbObjectsList;
                        
                   GMSpatialLMTests dt = new GMSpatialLMTests(this,model,pre,nbObjectsList);
                   dt.setLocationRelativeTo(this);
		   dt.setVisible(true);
                   setaddexp(model);
                   dt.setModel(model, pre);//This resets choices to false
	}
               public void spatialTestsPanelClicked(){
                String[] nbObjects = {""};
                DefaultListModel nbObjectsList = new DefaultListModel();
                this.model.efeglmextra.nbList = nbObjectsList;
                        
                   GMSpatialPanelTests dt = new GMSpatialPanelTests(this,model,pre);
                   dt.setLocationRelativeTo(this);
		   dt.setVisible(true);
                   setaddexp(model);
                   dt.setModel(model, pre);//This resets choices to false
	}*/
               
               
        public void irfClicked(){
		GMExplorerIRFOptions irfoptions = new GMExplorerIRFOptions(this,model,pre);
                irfoptions.setLocationRelativeTo(this);
		irfoptions.setVisible(true);
		setaddexpplots(model);
                irfoptions.setModel(model, pre);//This resets choices to false

	}
        
        public void regressionFunctionPlotClicked(String cmd){
                    String call ="";         
                       if ((!(model.EstimationMethod.equals("Panel - Linear Model")))){             
                            if (cmd.equals("Regression Function Plot")){
                                
                                String dependentVarText="";
                                String explanatoryVarText ="";
                                dependentVarText = actualValues;
                                if (model.method.equals("lm")){
                                    /* dependentVarText =   model.modelName+"$model[,\""+model.dependentVar+"\"]";*/
                                     explanatoryVarText =   model.modelName+"$model[,\""+model.termsArray[0]+"\"]";
                                }else if (model.method.equals("glm")||model.method.equals("plm")){
                                     explanatoryVarText =   model.modelName+"$mod[,\""+model.termsArray[0]+"\"]";
                                }else if (model.method.equals("dynlm")){
                                     /*dependentVarText =   "as.numeric("+model.modelName+"$model[,\""+model.dependentVar+"\"])";*/
                                     explanatoryVarText =   "as.numeric("+model.modelName+"$model[,\""+model.termsArray[0]+"\"])";
                                }else {
                                     /*dependentVarText =   model.modelName+"$y";*/
                                     explanatoryVarText =   (model.dataClassInR.equals(".data.frame")?model.dName:"as.data.frame("+model.dName+")")
                                                             +"[,\""+model.termsArray[0]+"\"]";
                                }
                                
                                plotType3 = "simple regression";
                                model.plotControl.traditionalGraphicsAvailable[3] = true;
                                model.plotControl.xLabels[3] =  "round("+explanatoryVarText+",3)";
                                model.plotControl.yLabels[3] =  "round("+dependentVarText+",3)";
                                
                                model.plotControl.traditionalPlotBeginning[3] = "plot("+model.dependentVar+"~"+model.termsArray[0]+", data="+model.dName;
                                
                                model.plotControl.traditionalPlotFormula[3] =dependentVarText+"~"+explanatoryVarText;
                                
                                if (model.EstimationMethod.equals("Binary Logit")){
                                 model.plotControl.traditionalPlotBeginning2[3]="\ncurve(1/(1+exp(-(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x))),"
                                                                               +"add=TRUE,from=min("+model.modelName+"$model[2]),to=max("+model.modelName+"$model[2])";
                                model.plotControl.latticeGraphicsAvailable[3] = false;
                                model.plotControl.ggplot2GraphicsAvailable[3] = false;
                                }
                                else if (model.EstimationMethod.equals("Binary Probit")){
                                model.plotControl.traditionalPlotBeginning2[3]="\ncurve(pnorm(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x),"
                                                                               +"add=TRUE,from=min("+model.modelName+"$model[2]),to=max("+model.modelName+"$model[2])";
                                model.plotControl.latticeGraphicsAvailable[3] = false;
                                model.plotControl.ggplot2GraphicsAvailable[3] = false;
                                } else if (model.method.equals("tobit")){
                                model.plotControl.traditionalPlotBeginning2[3]="\ncurve("+
                                     (model.efeglmextra.leftLimitText.equals("-Inf")&model.efeglmextra.rightLimitText.equals("")?
                                         "(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x)" /*no censoring at all!*/
                                        :
                                        ((model.efeglmextra.leftLimitText.equals("")||model.efeglmextra.leftLimitText.equals("0"))&model.efeglmextra.rightLimitText.equals("")?
                                           "(0*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x<=0)+"+
                                           "(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x>0)"
                                        : (model.efeglmextra.rightLimitText.equals("")?
                                           "("+model.efeglmextra.leftLimitText+"+0*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x<="+model.efeglmextra.leftLimitText+")+"+
                                           "(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x>"+model.efeglmextra.leftLimitText+")"
                                           :(model.efeglmextra.leftLimitText.equals("-Inf")?
                                             "("+model.efeglmextra.rightLimitText+"+0*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x>="+model.efeglmextra.rightLimitText+")+" +
                                             "(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x<"+model.efeglmextra.rightLimitText+")"
                                             :
                                              "("+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+"+0*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x<="+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+")+"+
                                             "("+model.efeglmextra.rightLimitText+"+0*x)*(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x>="+model.efeglmextra.rightLimitText+")+" +
                                              "(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x)*((coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x>"+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+")&(coef("+model.modelName+")[1]+coef("+model.modelName+")[2]*x<"+model.efeglmextra.rightLimitText+"))"
                                           ))))
                                  +",add=TRUE"
                                  ;
                      
                                model.plotControl.latticeGraphicsAvailable[3] = false;
                                model.plotControl.ggplot2GraphicsAvailable[3] = false;
                                }else{
                                   if (model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess") 
                                          ){
                                            model.plotControl.traditionalPlotBeginning2[3]="\nabline(coef="+model.modelName+"$coefficients";
                                    } else {
                                            model.plotControl.traditionalPlotBeginning2[3]="\nabline("+model.modelName;
                                   }
                                 model.plotControl.latticeGraphicsAvailable[3] = true;
                                 model.plotControl.ggplot2GraphicsAvailable[3] = true;
                                }
                                if (model.existsTS)
                                   model.plotControl.latticePlotBeginning[3] = "with(as.data.frame("+model.dName+"),\nxyplot("+model.dependentVar+"~"+model.termsArray[0];
                                else
                                   model.plotControl.latticePlotBeginning[3] = "xyplot("+model.dependentVar+"~"+model.termsArray[0]+", data="+model.dName; /*+",type=c(\"p\",\"r\")";*/
                                
                                if (model.method.equals("dynlm"))
                                  model.plotControl.ggplot2Beginning[3] = "ggplot(data.frame("+dependentVarText+"), aes(x="+explanatoryVarText+", y="+dependentVarText;
                                else
                                  model.plotControl.ggplot2Beginning[3] = "ggplot("+model.dName+", aes(x="+model.termsArray[0]+", y="+model.dependentVar;
                                
                                model.plotControl.showTickMarks[3] = true;
                                model.plotControl.showGrid[3] = true;
                                model.plotControl.ggplot2GrayTheme[3] = true;
                                model.plotControl.pointLabels[3] = "row names";
                                model.plotControl.pointLabelTextSize[3] = "1.0";
                                
                                model.plotControl.whichPointsToLabel[3] = "none";
                                model.plotControl.numExtremeValues[3] = "1";
                                model.plotControl.extremenessBasis[3] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[3] = "4";
                                model.plotControl.mostExtremeEnabled[3] = true;
                                model.plotControl.timeOrObsHorizontalAxis[3] = false;
                                
                                model.plotControl.mainTitle[3] = "Scatter Plot with Regression Line";
                                model.plotControl.xAxisTitle[3] = new String(model.termsArray[0]);
                                model.plotControl.manualyAxisRange[3]=false;
                                model.plotControl.yAxisTitle[3] = new String(model.dependentVar);
                                model.plotControl.manualxAxisRange[3]=false;
                                model.plotControl.legendText1[3] = "";
                                model.plotControl.plotType1[3] = "p: points";
                                model.plotControl.linePoints1Symbol[3] = "16: solid circle";
                                model.plotControl.symbolSize1[3] = "0.5";
                                model.plotControl.linePoints1color[3] = "blue";
                                model.plotControl.confIntvl95[3] = false;
                                model.plotControl.legendText2[3] = "";
                                model.plotControl.plotType2[3] = "r";
                                model.plotControl.linePoints2linewidth[3]="1";
                                model.plotControl.linePoints2color[3] = "red";
                                model.plotControl.lineType2[3]="solid";
                                
                                
                                 call = model.plotControl.traditionalPlotBeginning[3]+
                                      (model.plotControl.mainTitle[3].equals("")?"":",main=\""+ model.plotControl.mainTitle[3]+"\"")+
                                      (model.plotControl.yAxisTitle[3].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[3]+"\"")+
                                      (model.plotControl.xAxisTitle[3].equals("")?"":",xlab=\""+ model.plotControl.xAxisTitle[3]+"\"")+
                                      ",type=c(\""+model.plotControl.plotType1[3].split(":")[0]+"\")"+
                                      ",pch="+model.plotControl.linePoints1Symbol[3].split(":")[0]+
                                      ",col=\""+model.plotControl.linePoints1color[3]+"\""+ 
                                     (model.plotControl.linePoints2linewidth[3].equals("1.0")?"":",lwd="+model.plotControl3.linePoints2linewidth)+
                                     (model.plotControl.lineType2[3].equals("solid")||model.plotControl.lineType2[3].equals("")?"":",lty=\""+model.plotControl.lineType2[3]+"\"")+ 
                                     ",cex=2"+")"+
                                      model.plotControl.traditionalPlotBeginning2[3]+
                                      ",col=\""+model.plotControl.linePoints2color[3]+"\")"+
                                      (model.plotControl.showGrid[3]?"\ngrid(lwd = 2)":""); 
                                
                                commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                            } else if (cmd.equals("Partial Regression Plot")) {
                                model.plotControl.traditionalGraphicsAvailable[3] = true;
                                model.plotControl.traditionalPlotBeginning[3] = /*"par(mar=c(5,4,2,2))\n"+*/
                                     "avPlots("+pre.modelName+NameSuffix+",terms=.~"+firstFourTerms;
                                model.plotControl.traditionalPlotFormula[3] = model.formula;
                                model.plotControl.traditionalPlotBeginning2[3]="in avPlots";
                                model.plotControl.showTickMarks[3] = true;
                                model.plotControl.showGrid[3] = true;
                                model.plotControl.pointLabels[3] = "row names";
                                
                                model.plotControl.whichPointsToLabel[3] = "none";
                                model.plotControl.numExtremeValues[3] = "1";
                                model.plotControl.extremenessBasis[3] = "|y – mean(y)|";
                                model.plotControl.extremenessScrollChoices[3] = "1";
                                model.plotControl.mostExtremeEnabled[3] = true;
                                model.plotControl.timeOrObsHorizontalAxis[3] = false;
                                
                                model.plotControl.mainTitle[3] = "Added-Variable Plots";
                                model.plotControl.yAxisTitle[3] = "";
                                model.plotControl.xAxisTitle[3] = "";
                                model.plotControl.manualyAxisRange[3]=false;
                                model.plotControl.manualxAxisRange[3]=false;
                                model.plotControl.pointLabelTextSize[3] = "1.0";
                                model.plotControl.legendText1[3] = "_na_";
                                model.plotControl.linePoints1Symbol[3] = "16: solid circle";
                                model.plotControl.symbolSize1[3] = "0.5";
                                model.plotControl.linePoints1color[3] = "blue";
                                model.plotControl.legendText2[3] = "_na_";
                                model.plotControl.linePoints2linewidth[3]="1";
                                model.plotControl.linePoints2color[3] = "red";
                                  call=model.plotControl.traditionalPlotBeginning[3]+",ask=F,pch=16,col=4)";
                                  commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call); 
                           } else if (cmd.equals("Regression Plots")) {
                               if (model.EstimationMethod.equals("Binary Logit")){
                                  call="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n";
                                  for(int i=0;i<(Math.min(model.termsFinalStage.size(),4));i++){
                                     call+= "plot("+model.dependentVar+"~"+model.termsArray[i]+", data="+model.dName+
                                            ",xlab=\""+model.termsArray[i]+" (others at mean) \""+ 
                                            ",ylab=\""+model.dependentVar+"\""+
                                            ",type=\"p\",pch=16,col=\"blue\",cex=2"+
                                            ")"+
                                            "\ncurve(1/(1+exp(-(coef("+model.modelName+")[-1]%*%colMeans("+model.modelName+"$model[-1])-coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*colMeans("+model.modelName+"$model[\""+model.termsArray[i]+"\"])"
                                             +"+coef("+model.modelName+")[1]+coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*x))),"
                                             +"add=TRUE,from=min("+model.modelName+"$model[\""+model.termsArray[i]+"\"]),to=max("+model.modelName+"$model[\""+model.termsArray[i]+"\"]),col=\"red\")"+
                                             "\ngrid(lwd = 2)\n";
                                  }
                               } else if (model.EstimationMethod.equals("Binary Probit")){
                                  call="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n";
                                  for(int i=0;i<(Math.min(model.termsFinalStage.size(),4));i++){
                                     call+= "plot("+model.dependentVar+"~"+model.termsArray[i]+", data="+model.dName+
                                            ",xlab=\""+model.termsArray[i]+" (others at mean) \""+ 
                                            ",ylab=\""+model.dependentVar+"\""+
                                            ",type=\"p\",pch=16,col=\"blue\",cex=2"+
                                            ")"+
                                            "\ncurve(pnorm(coef("+model.modelName+")[-1]%*%colMeans("+model.modelName+"$model[-1])-coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*colMeans("+model.modelName+"$model[\""+model.termsArray[i]+"\"])"
                                             +"+coef("+model.modelName+")[1]+coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*x),"
                                             +"add=TRUE,from=min("+model.modelName+"$model[\""+model.termsArray[i]+"\"]),to=max("+model.modelName+"$model[\""+model.termsArray[i]+"\"]),col=\"red\")"+
                                             "\ngrid(lwd = 2)\n";
                                   
                                  }
                               } else if (model.method.equals("tobit")){
                                   if (model.termsFinalStage.size()==2)
                                     call="par(mfrow = c(2, 1))\n";
                                   else
                                     call="par(mfrow = c(2, 2))\n";  
                                  for(int i=0;i<(Math.min(model.termsFinalStage.size(),4));i++){
                                      
                                     String nonCensoredLineEqtn = "coef("+model.modelName+")[-1]%*%"+model.modelName+"$means[-1]-coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*"+model.modelName+"$means[\""+model.termsArray[i]+"\"]"
                                             +"+coef("+model.modelName+")[1]+coef("+model.modelName+")[\""+model.termsArray[i]+"\"]*x";  
                                     call+= "plot("+model.dependentVar+"~"+model.termsArray[i]+", data="+model.dName+
                                            ",xlab=\""+model.termsArray[i]+" (others at mean) \""+ 
                                            ",ylab=\""+model.dependentVar+"\""+
                                            ",type=\"p\",pch=16,col=\"blue\",cex=2"+
                                            ")"+       
                                             "\ncurve("+
                                     (model.efeglmextra.leftLimitText.equals("-Inf")&model.efeglmextra.rightLimitText.equals("")?
                                         nonCensoredLineEqtn /*no censoring at all!*/
                                        :
                                        ((model.efeglmextra.leftLimitText.equals("")||model.efeglmextra.leftLimitText.equals("0"))&model.efeglmextra.rightLimitText.equals("")?
                                           "(0*x)*("+nonCensoredLineEqtn+"<=0)+\n"+
                                           "("+nonCensoredLineEqtn+")*(("+nonCensoredLineEqtn+")>0)"
                                        : (model.efeglmextra.rightLimitText.equals("")?
                                           "("+model.efeglmextra.leftLimitText+"+0*x)*(("+nonCensoredLineEqtn+")<="+model.efeglmextra.leftLimitText+")+\n"+
                                           "("+nonCensoredLineEqtn+")*(("+nonCensoredLineEqtn+")>"+model.efeglmextra.leftLimitText+")"
                                           :(model.efeglmextra.leftLimitText.equals("-Inf")?
                                             "("+model.efeglmextra.rightLimitText+"+0*x)*(("+nonCensoredLineEqtn+")>="+model.efeglmextra.rightLimitText+")+\n" +
                                             "("+nonCensoredLineEqtn+")*(("+nonCensoredLineEqtn+")<"+model.efeglmextra.rightLimitText+")"
                                             :
                                              "("+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+"+0*x)*(("+nonCensoredLineEqtn+")<="+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+")+\n"+
                                             "("+model.efeglmextra.rightLimitText+"+0*x)*(("+nonCensoredLineEqtn+")>="+model.efeglmextra.rightLimitText+")+\n" +
                                              "("+nonCensoredLineEqtn+")*((("+nonCensoredLineEqtn+")>"+(model.efeglmextra.leftLimitText.equals("")?"0":model.efeglmextra.leftLimitText)+")&(("+nonCensoredLineEqtn+")<"+model.efeglmextra.rightLimitText+"))"
                                           ))))
                                            
                                             +",add=TRUE,col=\"red\")"+
                                             "\ngrid(lwd = 2)\n";
                                  }
                                     
                                     
                                     
                               }
                                  commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call); 
                                  
                            } else if (cmd.equals("Cointegrating Relation(s)")) {
                               if (model.termsArray.length > 2) { 
                                          call="print(xyplot(zoo(ts("+model.modelName+".HanselAddOns$CointRel[,2:"+(model.termsArray.length)+"],start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")),xlab=\"\",main=\"Considered cointegrating relationships\"))";    
                                       editButton.setVisible(true);editButton.setEnabled(false);  
                                      idLabelButton.setVisible(true);idLabelButton.setEnabled(false); idLAbelButtonEnabled = false;
                               } else {
                                        model.plotControl.traditionalGraphicsAvailable[3] = true;
                                        model.plotControl.latticeGraphicsAvailable[3] = true;
                                        model.plotControl.mainTitle[3] = "Cointegrating Relation";
                                        model.plotControl.showTickMarks[3] = true;
                                        model.plotControl.showGrid[3] = true;
                                        model.plotControl.pointLabels[3] = "row names";
                                        
                                        model.plotControl.whichPointsToLabel[3] = "none";
                                        model.plotControl.numExtremeValues[3] = "1";
                                        model.plotControl.extremenessBasis[3] = "|y – mean(y)|";
                                        model.plotControl.extremenessScrollChoices[3] = "1";
                                        model.plotControl.mostExtremeEnabled[3] = true;
                                        model.plotControl.timeOrObsHorizontalAxis[3] = true;
                                        model.plotControl.yAxisTitle[3] = " ";
                                        model.plotControl.manualyAxisRange[3]=false;
                                        model.plotControl.xAxisTitle[3] = (model.existsTS?"":"Observation");
                                        model.plotControl.manualxAxisRange[3]=false;
                                        model.plotControl.legendText1[3] = "";
                                        model.plotControl.plotType1[3] = "l: line";
                                        model.plotControl.lineType1[3] = "solid";
                                        model.plotControl.linePoints1linewidth[3]="1";
                                        model.plotControl.linePoints1color[3] = "blue";
                                        model.plotControl.linePoints1Symbol[3]="";
                                        model.plotControl.legendText2[3] = "";
                                        model.plotControl.linePoints2linewidth[3]="";
                                        model.plotControl.linePoints2color[3] = "";

                                        String vName = model.modelName+".HanselAddOns$CointRel[,2:"+(model.termsArray.length)+"]";
                                        
                                        model.plotControl.traditionalPlotBeginning[3] = "plot("+  
                                                 (model.existsTS?"ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")":vName);
                                        model.plotControl.traditionalPlotFormula[3] = vName;
                                        model.plotControl.latticePlotBeginning[3] = "xyplot("+(model.existsTS?"zoo(ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+"))":"ts("+vName+")");
                                        /*model.plotControl5.latticePlotBeginning = "xyplot("+"zoo(ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+"))";*/
                                    call = model.plotControl.traditionalPlotBeginning[3]+",type=\"l\""+",col=\""+model.plotControl.linePoints1color[3]+"\""+
                                            ",main=\""+ model.plotControl.mainTitle[3]+"\""+ 
                                            ",ylab=\""+ model.plotControl.yAxisTitle[3]+"\""+
                                            ",xlab=\""+ model.plotControl.xAxisTitle[3]+"\""+
                                            ")\ngrid(lwd = 2)";
                                     
                                      editButton.setVisible(true);editButton.setEnabled(true);  
                                      idLabelButton.setVisible(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                               }
                               commandsLogText.setText(commandsLogText.getText() + 
                                            "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                             "JavaGD() #Opens a new graphic device\n"+ call);
                            }else { //Spinograms
                                  call="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n";
                                  for(int i=0;i<(Math.min(model.termsFinalStage.size(),4));i++)
                                     call+="try(plot(factor("+model.dependentVar+") ~"+model.termsFinalStage.get(i)+",data="+model.dName+",ylevels=2:1,col=c(3,5)))\n";        
                               commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                            } 
                        } else {
                           
                           if (model.interactionTerm) {
                                   call="par(mar=c(5,4,2,2))\n"+
			           "try(avPlots("+pre.modelName+NameSuffix+",ask=F,pch=16,col=4),silent=TRUE)";
                           }
                           else {
                               if (model.efeglmextra.previousEstimation)
                               call = "try(plot(cbind("+model.modelName+"$model["+model.dependentVar+"],"+model.modelName+"$model["+model.terms.getElementAt(0).toString() +
                                           "])),silent=TRUE)\n regLine("+pre.modelName+NameSuffix+")" ;
                               else{
                                String expvar = model.termsArray.toString().substring(1,model.termsArray.toString().length()-1);
                                call = "try(plot("+model.dependentVar+"~"+expvar+", data="+pre.data+"),silent=TRUE)\n regLine("+pre.modelName+NameSuffix+")" ;
                               } 
                           }
                       }

                       addedTab = new ModelPlotPanel(call);
                       PlotsPanel3.add(addedTab); 
                       if (cmd.equals("Regression Plots")&model.method.equals("tobit")){
                           //This is rough fix for an oddity that with multiple regression plot under tobit,
                           //  the red line for the regression plot only shows up with a Deducer.execute call;
                           //  it is not sufficient with the addTab & PlotPanel3.add calls above,
                           //  which under the circumstances of the fix simply makes PlotsPanel3 the active
                           //  plotting place. This involves the inefficiency of plotting twice though.
                           Deducer.execute(call);
                       }
                       Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[3] <- dev.cur()");
	}
        
   public void PlotEditClicked(){
    int panelDealtWith = 0;
    if(regressionFunctionPlotButton.isSelected()||partialregressionPlotButton.isSelected()||cointRelsButton.isSelected()) {
         panelDealtWith = 3;
    } else if(residualsVsFitButton.isSelected()||cointRelSCButton.isSelected()) {
         panelDealtWith = 4;
    } else if(plotsvsObsButton.isSelected()||plotsvsTimeButton.isSelected()||plotsvsTimeExtendedButton.isSelected()) {
         panelDealtWith = 5;
    } else if(otherPlotsButton.isSelected()) {
         panelDealtWith = 6;
    } else { // This is meant for the default page of Philips-Ouliaris Pz or Engle-Granger,
           //   where no plot button is intially selected.
         panelDealtWith = 5;
    }
      
    HanselPlotEdit plotEditDialog = new HanselPlotEdit(this,model,"GMExplorer",pre,panelDealtWith);
    plotEditDialog.setLocationRelativeTo(this);
    plotEditDialog.setVisible(true);

   } 
   
   public void interactiveClicked(String interactiveType){
    int panelDealtWith = 0;
    if(regressionFunctionPlotButton.isSelected()||partialregressionPlotButton.isSelected()||cointRelsButton.isSelected()) {
         panelDealtWith = 3;
    } else if(residualsVsFitButton.isSelected()||cointRelSCButton.isSelected()) {
         panelDealtWith = 4;
    } else if(plotsvsObsButton.isSelected()||plotsvsTimeButton.isSelected()||plotsvsTimeExtendedButton.isSelected()) {
         panelDealtWith = 5;
    } else if(otherPlotsButton.isSelected()) {
         panelDealtWith = 6;
    } else { // This is meant for the default page of Philips-Ouliaris Pz or Engle-Granger,
             // where no plot button is intially selected.
         panelDealtWith = 5;
    }   
    GMExplorerLMInteractive plotEditDialog = new GMExplorerLMInteractive(this,model,"GMExplorer",pre,panelDealtWith,interactiveType);
    plotEditDialog.setLocationRelativeTo(this);
    plotEditDialog.setVisible(true);

   } 
   
        
   public void alterPlot(String cmd){
       int panelDealtWith = 0;
              if(regressionFunctionPlotButton.isSelected()||partialregressionPlotButton.isSelected()||cointRelsButton.isSelected()) {
                panelDealtWith = 3;
              } else if(residualsVsFitButton.isSelected()||cointRelSCButton.isSelected()) {
                panelDealtWith = 4;
              } else if(plotsvsObsButton.isSelected()||plotsvsTimeButton.isSelected()||plotsvsTimeExtendedButton.isSelected()) {
                 panelDealtWith = 5;
              } else if(otherPlotsButton.isSelected()) {
                 panelDealtWith = 6;
              } else {// This is meant for the default page of Philips-Ouliaris or Engle-Granger,
                      //where no plot button is intially selected.
                 panelDealtWith = 5;
            }  
                       String graphicSystem = model.plotControl.graphicSystem[panelDealtWith];
                       
                       String traditionalPlotBeginning = model.plotControl.traditionalPlotBeginning[panelDealtWith];
                       String traditionalPlotFormula = model.plotControl.traditionalPlotFormula[panelDealtWith];
                       String traditionalPlotEnd = model.plotControl.traditionalPlotEnd[panelDealtWith];
                       String traditionalPlotBeginning2 = model.plotControl.traditionalPlotBeginning2[panelDealtWith];
                       String traditionalPlotBeginning3 = model.plotControl.traditionalPlotBeginning3[panelDealtWith];  
                       
                       String latticePlotBeginning = model.plotControl.latticePlotBeginning[panelDealtWith];
                       String ggplot2Beginning = model.plotControl.ggplot2Beginning[panelDealtWith];
                       String plotFunction = model.plotControl.plotFunction[panelDealtWith];
                       String plotRelation = model.plotControl.plotRelation[panelDealtWith];

                       Integer PlotControlsTab = model.plotControl.PlotControlsTab[panelDealtWith];

                        String mainTitle = model.plotControl.mainTitle[panelDealtWith];
                        String keyPosition = model.plotControl.keyPosition[panelDealtWith];
                        Boolean showTickMarks = model.plotControl.showTickMarks[panelDealtWith];
                        Boolean showGrid = model.plotControl.showGrid[panelDealtWith];
                        Boolean singleGraphMulti = model.plotControl.singleGraphMulti[panelDealtWith];
                        Boolean ggplot2grayTheme = model.plotControl.ggplot2GrayTheme[panelDealtWith];
                        String pointLabelTextSize = model.plotControl.pointLabelTextSize[panelDealtWith];
                        String pointLabels = model.plotControl.pointLabels[panelDealtWith];
                        
                        String whichPointsToLabel = model.plotControl.whichPointsToLabel[panelDealtWith];
                        String numExtremeValues = model.plotControl.numExtremeValues[panelDealtWith];
                        String extremenessBasis = model.plotControl.extremenessBasis[panelDealtWith]; 
                        Boolean timeOrObsHorizontalAxis = model.plotControl.timeOrObsHorizontalAxis[panelDealtWith]; 
      
                        String xAxisTitle = model.plotControl.xAxisTitle[panelDealtWith];
                        Boolean manualxAxisRange= model.plotControl.manualxAxisRange[panelDealtWith];
                        String manualxAxisMinText = model.plotControl.manualxAxisMinText[panelDealtWith];
                        String manualxAxisMaxText = model.plotControl.manualxAxisMaxText[panelDealtWith];
     
                        String yAxisTitle = model.plotControl.yAxisTitle[panelDealtWith];
                        Boolean manualyAxisRange = model.plotControl.manualyAxisRange[panelDealtWith];
                        String manualyAxisMinText = model.plotControl.manualyAxisMinText[panelDealtWith];
                        String manualyAxisMaxText = model.plotControl.manualyAxisMaxText[panelDealtWith];
                        
                        String legendText1 = model.plotControl.legendText1[panelDealtWith]; 
                        String plotType1 = model.plotControl.plotType1[panelDealtWith].split(":")[0];
                        String lineType1 = model.plotControl.lineType1[panelDealtWith];
                        String symbolSize1 = model.plotControl.symbolSize1[panelDealtWith];
                        String linePoints1linewidth = model.plotControl.linePoints1linewidth[panelDealtWith];
                        String linePoints1color = model.plotControl.linePoints1color[panelDealtWith];
                        String linePoints1Symbol = model.plotControl.linePoints1Symbol[panelDealtWith];    
                        
                        String legendText2 = model.plotControl.legendText2[panelDealtWith];
                        String plotType2 = model.plotControl.plotType2[panelDealtWith];
                        String lineType2 = model.plotControl.lineType2[panelDealtWith];
                        String linePoints2linewidth = model.plotControl.linePoints2linewidth[panelDealtWith];
                        String linePoints2color = model.plotControl.linePoints2color[panelDealtWith];
                        Boolean confIntvl95 = model.plotControl.confIntvl95[panelDealtWith];
                        
                        String legendText3 = model.plotControl.legendText3[panelDealtWith];
                        String lineType3 = model.plotControl.lineType3[panelDealtWith];
                        String linePoints3linewidth = model.plotControl.linePoints3linewidth[panelDealtWith];
                        String linePoints3color = model.plotControl.linePoints3color[panelDealtWith];
                        
                        String xLabels = model.plotControl.xLabels[panelDealtWith];
                        String yLabels = model.plotControl.yLabels[panelDealtWith];
       
              Boolean xyplotDealtWith = true;
 
                       if (panelDealtWith==3) {
                          PlotsPanel3.removeAll();
                          PlotsPanel3.setVisible(false);
                      } else if (panelDealtWith==4) {
                          PlotsPanel4.removeAll();
                          PlotsPanel4.setVisible(false);
                      } else if (panelDealtWith==5) {
                          PlotsPanel5.removeAll();
                          PlotsPanel5.setVisible(false);
                      } else if (panelDealtWith==6) {
                          PlotsPanel6.removeAll();
                          PlotsPanel6.setVisible(false);
                      }
                      Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums["+panelDealtWith+"]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums["+panelDealtWith+"]);"+hanselEnvAndModelName+"_estPlotPanelDevNums["+panelDealtWith+"]<-\"\"}");
   
                      String callCommandsLog ="";
                      String callforLog ="";
                      String toEvalFirst ="";
                      String call = "";
                      String postPlotAdditionExecCmd = "";
                                  String xvalue = "";
                                  String yvalue = "";
                                  if (traditionalPlotFormula.contains("~")) {
                                      xvalue = traditionalPlotFormula.split("~")[1];
                                      yvalue = traditionalPlotFormula.split("~")[0];
                                  } else if (timeOrObsHorizontalAxis){
                                      xvalue = "time("+traditionalPlotFormula+")"; 
                                      yvalue = traditionalPlotFormula;
                                  } else if (traditionalPlotFormula.contains(",")) {
                                      xvalue = traditionalPlotFormula.split(",")[0];
                                      yvalue = traditionalPlotFormula.split(",")[1];
                                  }
                                      
                                  String extremenessBasisFormula = "";
                                  String extremenessBasislabelFormula = "";
                                  
                                  if (pointLabels.equals("extremeness measure")){
                                      if (extremenessBasis.equals("|x – mean(x)|")){
                                          extremenessBasislabelFormula = "abs("+xvalue +"-mean("+xvalue+"))";
                                      }else if (extremenessBasis.equals("|y – mean(y)|")){
                                          extremenessBasislabelFormula = "abs("+yvalue +"-mean("+yvalue+"))";
                                      } else if (extremenessBasis.equals("|y|")){
                                          extremenessBasislabelFormula = "abs("+yvalue +")";
                                      }else if (extremenessBasis.equals("Mahalanobis distance")){
                                          extremenessBasislabelFormula = "mahalanobis(cbind("+xvalue +","+yvalue+"))";
                                      }else if (extremenessBasis.equals("Cook's distance")){
                                          extremenessBasislabelFormula = "cooks.distance("+model.modelName+")";
                                      }else if (extremenessBasis.equals("|residual|")){
                                          extremenessBasislabelFormula = "abs(residuals("+model.modelName+"))";
                                      }
                                  }
                                  
                                  if(whichPointsToLabel.equals("mostExtrm")){
                                      if (extremenessBasis.equals("|x – mean(x)|")){
                                          extremenessBasisFormula = "\"x\"";
                                      }else if (extremenessBasis.equals("|y – mean(y)|")){
                                          extremenessBasisFormula = "\"y\"";
                                      } else if (extremenessBasis.equals("|y|")){
                                          extremenessBasisFormula = "\"r\""; 
                                      }else if (extremenessBasis.equals("Mahalanobis distance")){
                                          extremenessBasisFormula = "\"mahal\"";
                                      }else if (extremenessBasis.equals("Cook's distance")){
                                          extremenessBasisFormula = "cooks.distance("+model.modelName+")";
                                      }else if (extremenessBasis.equals("|residual|")){
                                          extremenessBasisFormula = "abs(residuals("+model.modelName+"))";
                                      }
                                  }
                                  String labels = "";
                                  if    (pointLabels.equals("row names"))
                                      labels = model.rowNamesOrDatesLabelModel;
                                  else if (pointLabels.equals("x values"))
                                      labels = xLabels;
                                  else if (pointLabels.equals("y values"))
                                      labels = yLabels;
                                  else if (pointLabels.equals("extremeness measure"))
                                      labels = "round("+extremenessBasislabelFormula+",3)";    
                      
                      if (graphicSystem.equals("traditional graphics"))
                                  {
  
                                   callforLog = 
                                           (singleGraphMulti&
                                                   ((model.dataClassInR.equals("data.frame")||model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts")))?
                                                     "ts.":"")+

                                           (model.dataClassInR.equals("zoo")||model.dataClassInR.equals("zooreg")||model.dataClassInR.equals("xts")?
                                            traditionalPlotBeginning.replace("plot","plot.zoo")
                                             :
                                            traditionalPlotBeginning
                                            )+
                                           
                                           (singleGraphMulti&
                                                 !(model.dataClassInR.equals("data.frame")||model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts"))?
                                                  ",plot.type=\"single\""
                                                 :"")+
                                      (mainTitle.equals("")||mainTitle.equals("_na_")?"":",main=\""+ mainTitle+"\"")+
                                      (yAxisTitle.equals("")||yAxisTitle.equals("_na_")?"":",ylab=\""+ yAxisTitle+"\"")+
                                      (xAxisTitle.equals("")||xAxisTitle.equals("_na_")?"":",xlab=\""+ xAxisTitle+"\"")+
                                      
                                      (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                      ",xlim=c("+manualxAxisMinText+","+manualxAxisMaxText+")":"")+
                                      (manualyAxisRange&(!manualyAxisMinText.equals(""))&(!manualyAxisMinText.equals(""))?
                                      ",ylim=c("+manualyAxisMinText+","+manualyAxisMaxText+")":"")+
                                      (showTickMarks?"":",tck=0")+    
                                     (plotType1.equals("")?"":",type=\""+plotType1.split(":")[0]+"\"")+
                                      (linePoints1Symbol.equals("")?"":",pch="+linePoints1Symbol.split(":")[0])+
                                     
                                   (singleGraphMulti?
                                          (lineType1.equals("solid")&lineType2.equals("solid")&(lineType3.equals("solid")|lineType3.equals(""))?"":
                                                      ",lty=c(\""+lineType1+"\",\""+lineType2+"\""+
                                                      (lineType3.equals("")?"":",\""+lineType3+"\"")+
                                           ")")+

                                          ((linePoints1linewidth.equals("")||linePoints1linewidth.equals("1"))&
                                           (linePoints2linewidth.equals("")||linePoints2linewidth.equals("1"))&
                                           (linePoints3linewidth.equals("")||linePoints3linewidth.equals("1"))
                                            ?"":
                                            ",lwd=c(\""+linePoints1linewidth+"\",\""+linePoints2linewidth+"\""+
                                            (linePoints3linewidth.equals("")?"":",\""+linePoints3linewidth+"\"")+")")+

                                          ((linePoints1linewidth.equals("")||linePoints1color.equals("black"))&
                                           (linePoints2linewidth.equals("")||linePoints2color.equals("black"))&
                                           (linePoints3linewidth.equals("")||linePoints3color.equals("black"))
                                           ?"":
                                            ",col=c(\""+linePoints1color+"\",\""+linePoints2color+"\""+
                                            (linePoints3color.equals("")?"":",\""+linePoints3color+"\"")
                                           +")") 
                                      :
                                     ",col=\""+linePoints1color+"\""+
                                     (linePoints1linewidth.equals("1.0")||linePoints1linewidth.equals("")?"":",lwd="+linePoints1linewidth)+
                                     (lineType1.equals("solid")||lineType1.equals("")?"":",lty=\""+lineType1+"\"")+
                                     ((symbolSize1.equals("0.5")||symbolSize1.equals(""))?",cex=1":",cex=2*"+symbolSize1)+
                                      (traditionalPlotBeginning2.equals("in avPlots")||traditionalPlotBeginning2.equals("in crPlots")?
                                        (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",lwd="+linePoints2linewidth)+
                                        (linePoints3color.equals("")?
                                           (linePoints2color.equals("red")?"":",col.lines=\""+linePoints2color+"\"")
                                          :
                                           ((linePoints2color.equals("red")&linePoints3color.equals("green"))?"":
                                            ",col.lines=c(\""+linePoints2color+"\",\""+linePoints3color+"\")")
                                           )+

                                        (showGrid?"":",grid=FALSE")+
                                           (whichPointsToLabel.equals("none")?"":
                                            ",labels="+labels+
                                           (whichPointsToLabel.equals("all")?",id.n=Inf":
                                            ",id.method="+ extremenessBasisFormula +(whichPointsToLabel.equals("Pearson2")?"":",id.n ="+numExtremeValues)))
  
                                        :"")
                                       ) +
                                      ")"+
                                                                                      
                                      traditionalPlotEnd+
                                       (traditionalPlotBeginning2.equals("")||traditionalPlotBeginning2.equals("in avPlots")||
                                           traditionalPlotBeginning2.equals("in crPlots")?"":
                                           
                                           (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                           model.plotControl.traditionalPlotBeginning2[panelDealtWith].split(",from=")[0]+
                                             ",from="+manualxAxisMinText+",to="+manualxAxisMaxText:
                                           model.plotControl.traditionalPlotBeginning2[panelDealtWith])
                                            +",col=\""+linePoints2color+"\""+
                                           (lineType2.equals("solid")||lineType2.equals("")?"":",lty=\""+lineType2+"\"")+ 
                                           (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",lwd="+linePoints2linewidth)+")")+
                                           
                                      (traditionalPlotBeginning3.equals("")?"":
                                           model.plotControl.traditionalPlotBeginning3[panelDealtWith]+",col=\""+linePoints3color+"\""+
                                           (lineType3.equals("solid")||lineType3.equals("")?"":",lty=\""+lineType3+"\"")+ 
                                           (linePoints3linewidth.equals("1.0")||linePoints3linewidth.equals("")?"":",lwd="+linePoints3linewidth)+")")+
                                      
                                       (!linePoints1linewidth.equals("")&!linePoints2linewidth.equals("")?
                                                 "\nlegend(\""+keyPosition+"\",c(\""+legendText1+"\",\""+legendText2+"\")"+
                                                (plotType1.equals("p")||plotType1.equals("b")||plotType1.equals("o")?
                                                         ",pch=c("+linePoints1Symbol.split(":")[0]+",NA)" /*)*/:"")+
                                                  ",lwd=c(\""+(plotType1.equals("p")||plotType1.equals("n")?"NA":linePoints1linewidth)+"\",\""+linePoints2linewidth+"\")" /*)*/+
                                                 (lineType1.equals("solid")&lineType2.equals("solid")?"":
                                                      ",lty=c(\""+lineType1+"\",\""+lineType2+"\")")+
                                                      ",col=c(\""+linePoints1color+"\",\""+linePoints2color+"\"))"
                                           :"")+    
                                           
                                       (traditionalPlotBeginning2.equals("in avPlots")||traditionalPlotBeginning2.equals("in crPlots")?"":
                                         (showGrid?"\ngrid(lwd = 2)":"")+
                                             (whichPointsToLabel.equals("none")?"":
                                               (whichPointsToLabel.equals("all")?
                                                  "\ntext("+traditionalPlotFormula+",labels="+labels+",pos=4,cex="+pointLabelTextSize+",offset=0.5+"+(symbolSize1.equals("")?"1":symbolSize1)+"/4)"
                                                : 
                                                  "\nshowLabels("+ xvalue +","+ yvalue +
                                                           ",labels="+labels+",id.method="+ extremenessBasisFormula + 
                                                            (whichPointsToLabel.equals("Pearson2")?"":",id.n ="+numExtremeValues)+")"
     
                                                 )) 
                                           );
                             if (model.plotControl.confIntvl95[panelDealtWith])
                                  postPlotAdditionExecCmd = "\nHanseltemp_newx <- seq(min("+model.dName+"[,\""+model.termsArray[0]+"\"]),max("+model.dName+"[,\""+model.termsArray[0]+"\"]), length.out=100)"+
                                           "\nHanseltemp_preds <- predict("+model.modelName+", newdata = data.frame(young=Hanseltemp_newx )," +
                                           "\n                 interval = 'confidence')"+
                                           "\npolygon(c(rev(Hanseltemp_newx ), Hanseltemp_newx ), c(rev(Hanseltemp_preds[ ,3]),"+
                                           "\n                     Hanseltemp_preds [ ,2]), col=rgb(0, 0, 0,0.2), border = NA)"+
                                           "\nlines(Hanseltemp_newx, Hanseltemp_preds[ ,3], lty = 'dashed', col = 'red')"+
                                           "\nlines(Hanseltemp_newx, Hanseltemp_preds[ ,2], lty = 'dashed', col = 'red')"+
                                           "\nrm(Hanseltemp_newx)"+
                                           "\nrm(Hanseltemp_preds)";
                                   
                              call = callforLog; 
                              
                          }else if (graphicSystem.equals("lattice")){
                              if (pointLabels.equals("none"))      
                                    ltext = "";
                             else {
                              Deducer.execute("library(latticeExtra)");
                              
                              if (whichPointsToLabel.equals("all")) {   
                                            ltext = "\n  ltext(..., labels="+labels+", pos=4, offset=0.4+0.05/"+(symbolSize1.equals("")?"1":symbolSize1) +")";
                              } 
                              
                              }
                              String confIntvl95Text="";
                              if (model.plotControl.confIntvl95[panelDealtWith]==true){
                                    Deducer.execute("library(latticeExtra)"); 
                                    confIntvl95Text = "\n  panel.smoother(..., method = \"lm\", col = \"red\", col.se = \"yellow\",alpha.se = 0.3)"; 
                              }
                              
                              callforLog = latticePlotBeginning+
                                      (mainTitle.equals("")||mainTitle.equals("_na_")?"":",main=\""+ mainTitle+"\"")+
                                      (yAxisTitle.equals("")||yAxisTitle.equals("_na_")?"":",ylab=\""+ yAxisTitle+"\"")+
                                      (xAxisTitle.equals("")||xAxisTitle.equals("_na_")?"":",xlab=\""+ xAxisTitle+"\"")+
                                      
                                      (!linePoints1linewidth.equals("")&!linePoints2linewidth.equals("")?
                                           (plotType1.equals("p")||plotType1.equals("b")||plotType1.equals("o")?
                                                         ",cex=c("+symbolSize1+",NA)" /*)*/+
                                                         ",pch=c("+linePoints1Symbol.split(":")[0]+",NA)" /*)*/:"")+
                                            ",lwd=c("+(plotType1.equals("p")||plotType1.equals("n")?"NA":linePoints1linewidth)+","+linePoints2linewidth+")" /*)*/+
                                            (plotType1.equals("")?"":
                                              ",type=c(\""+plotType1+"\",\"l\")")
                                      :
                                        ((symbolSize1.equals("1.0")||symbolSize1.equals(""))?"":",cex="+symbolSize1)+
                                        (linePoints1Symbol.equals("")?"":",pch="+linePoints1Symbol.split(":")[0])+
                                        (plotType1.equals("")&linePoints2linewidth.equals("")?"":                
                                           (plotType2.equals("")?
                                              (plotType1.equals("")?"":",type=\""+plotType1+"\"")
                                            :",type=c(\""+plotType1+"\",\""+plotType2+"\")"))
                                      )+
                                      ",col.symbol=\""+linePoints1color+"\""+
                                      
                                       (!linePoints1linewidth.equals("")&!linePoints2linewidth.equals("")?
                                         ",par.settings=list(superpose.line = list(col = c(\""+linePoints1color+"\",\""+linePoints2color+"\")"+
                                        (linePoints1linewidth.equals("1")&linePoints2linewidth.equals("1")?"":
                                              (plotType1.equals("")?"":",type=c(\""+plotType1.split(":")[0]+"\",\"l\")")+
                                              ",lwd=c(\""+linePoints1linewidth+"\",\""+linePoints2linewidth+"\")")+
                                        (lineType1.equals("solid")&lineType2.equals("solid")?"":
                                              ",lty=c(\""+lineType1+"\",\""+lineType2+"\")")
                                         +"))"+
                                         ",auto.key=list(text=c(\""+legendText1+"\",\""+legendText2+"\"),space=\""+keyPosition+"\""+
                                         (keyPosition.equals("top")||keyPosition.equals("bottom")?",columns=2":"")+
                                         (plotType1.equals("")?"":",type=c(\""+plotType1+"\",\"l\")")
                                         +")"
                                      :
                                          (linePoints1linewidth.equals("")?"":
                                             (lineType1.equals("solid")||lineType1.equals("")?"":",lty=\""+lineType1+"\"")+ 
                                             (linePoints1linewidth.equals("1.0")?"":",lwd="+linePoints1linewidth)
                                           )+
                                         (!linePoints1linewidth.equals("")&!linePoints1color.equals("")?",col.line=\""+linePoints1color+"\"":"")+

                                         (linePoints2linewidth.equals("")?"":
                                            (lineType2.equals("solid")||lineType2.equals("")?"":",lty=\""+lineType2+"\"")+ 
                                            (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",lwd="+linePoints2linewidth)
                                          )+
                                         (!linePoints2linewidth.equals("")&!linePoints2color.equals("")?",col.line=\""+linePoints2color+"\"":"")
                                       )+
                                      
                                      
                                      (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                      ",xlim=c("+manualxAxisMinText+","+manualxAxisMaxText+")":"")+
                                      (manualyAxisRange&(!manualyAxisMinText.equals(""))&(!manualyAxisMinText.equals(""))?
                                      ",ylim=c("+manualyAxisMinText+","+manualyAxisMaxText+")":"")+
                                      (showTickMarks?"":",scales = list(tck = c(-1,0))")+   
                                      (showGrid?",grid=TRUE":"")+

                                      (ltext.equals("")&confIntvl95Text.equals("")?"":",\n panel=function(...) {panel.xyplot(...);"+ltext+confIntvl95Text+"\n}")+
                                      ")"
                                      +(latticePlotBeginning.contains("with(")?"\n)":"");
                              if (model.plotControl.confIntvl95[panelDealtWith])
                                  postPlotAdditionExecCmd = call;
                              call = "try(print("+callforLog.replace("\n","")+"))"; 
                          } else if (graphicSystem.equals("ggplot2")){
                              callforLog = ggplot2Beginning+
                                             "))"+
                                            (mainTitle.equals("")||mainTitle.equals("_na_")?"":"+ggtitle(\""+ mainTitle+"\")")+
                                            (xAxisTitle.equals("")||xAxisTitle.equals("_na_")?"":"+xlab(\""+ xAxisTitle+"\")")+
                                            (yAxisTitle.equals("")||yAxisTitle.equals("_na_")?"":"+ylab(\""+ yAxisTitle+"\")")+
                                            
                                            (ggplot2grayTheme==false?"+theme_bw()":"")+
                                            (showGrid&showTickMarks?"":"+theme("+
                                            (showTickMarks?"":"axis.ticks=element_blank()"+(showGrid?"":","))+   
                                            (showGrid?"":"\npanel.grid.major = element_blank()" +
                                                         "\n,panel.grid.minor = element_blank()")+
                                            ")")+
                                            (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                            "+ scale_x_continuous(limits=c("+manualxAxisMinText+","+manualxAxisMaxText+"))":"")+
                                            (manualyAxisRange&(!manualyAxisMinText.equals(""))&(!manualyAxisMinText.equals(""))?
                                            "+ scale_y_continuous(limits=c("+manualyAxisMinText+","+manualyAxisMaxText+"))":"")+
                                            "+ geom_point(color=\""+linePoints1color+"\","+
                                            ",shape="+linePoints1Symbol.split(":")[0]+
                                            ((symbolSize1.equals("1.0")||symbolSize1.equals(""))?",size=3":",size=3*"+symbolSize1)+
                                            ",alpha=0.5)"+
                                            (whichPointsToLabel.equals("all")?"+geom_text(aes(label="+labels+"),hjust=-0.5*"+(symbolSize1.equals("")?"1":symbolSize1)+"/"+pointLabelTextSize+",vjust=0.2,size=4*"+pointLabelTextSize+")":"")+
                                            "+ geom_smooth(method = \"lm\",color=\""+linePoints2color+"\""+
                                            (lineType2.equals("solid")||lineType2.equals("")?"":",linetype=\""+lineType2+"\"")+
                                            (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",size=0.5*"+linePoints2linewidth)+
                                            (!confIntvl95?",se = FALSE":"")+
                                             ")"
                                            
                                             ;
                            call = "try(print("+callforLog+"))"; 
                          }            
                      
                      
                  callCommandsLog += "\n#*************altered plot ************************\n";
                  callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                  callCommandsLog += (model.plotControl.preCommand[panelDealtWith].equals("")?"":model.plotControl.preCommand[panelDealtWith]+"\n")+
                                            callforLog+
                                      (model.plotControl.postCommand[panelDealtWith].equals("")?"":model.plotControl.postCommand[panelDealtWith]);
                                           
                  commandsLogText.setText(commandsLogText.getText() + callCommandsLog);
                  
                  if (!model.plotControl.preCommand[panelDealtWith].equals(""))
                              Deducer.eval(model.plotControl.preCommand[panelDealtWith]);
                  
                  addedTab = new ModelPlotPanel(call);
             
                  if (!postPlotAdditionExecCmd.equals("")){
                      Deducer.eval(postPlotAdditionExecCmd);
                      commandsLogText.setText(commandsLogText.getText() + postPlotAdditionExecCmd);
                  }                         
                  
                  if (!model.plotControl.postCommand[panelDealtWith].equals(""))
                              Deducer.eval(model.plotControl.postCommand[panelDealtWith]);
                  
                   if (panelDealtWith==3) {
                     PlotsPanel3.add(addedTab);
                     PlotsPanel3.setVisible(true);
                  } else if (panelDealtWith==4) {
                     PlotsPanel4.add(addedTab);
                     PlotsPanel4.setVisible(true);
                  } else if (panelDealtWith==5) {
                     PlotsPanel5.add(addedTab);
                     PlotsPanel5.setVisible(true);
                   } else if (panelDealtWith==6) {
                     PlotsPanel6.add(addedTab);
                     PlotsPanel6.setVisible(true);
                   } 
                   Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums["+panelDealtWith+"] <- dev.cur()");
        };       
        
        public void residualsVsResponseClicked(String cmd){
            String call ="";
                   if (cmd == "Residuals vs Fitted"){
                                editButton.setEnabled(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;

                                if (model.method.equals("tobit")){
                                   /*  String actualValues = pre.modelName+NameSuffix+"$y[,1]";*/
                                   /*  String fittedValues = "predict("+pre.modelName+NameSuffix+")";
                                          if (!(model.efeglmextra.leftLimitText.equals("-Inf")))
                                              if (model.efeglmextra.leftLimitText.equals(""))
                                                fittedValues = "pmax("+fittedValues+",0)";
                                              else
                                                fittedValues = "pmax("+fittedValues+","+model.efeglmextra.leftLimitText+")";
                                          if (!(model.efeglmextra.rightLimitText.equals("Inf")||model.efeglmextra.rightLimitText.equals("")))
                                                fittedValues = "pmin("+fittedValues+","+model.efeglmextra.rightLimitText+")";*/
                                     String residValues = actualValues +"-"+ fittedValues;
                                     model.plotControl.xLabels[4] =  "round("+fittedValues+",3)";
                                     model.plotControl.yLabels[4] =  "round("+residValues+",3)";
                                     model.plotControl.traditionalPlotBeginning[4] =              
                                      "plot("+fittedValues+","+residValues;
                                     model.plotControl.traditionalPlotBeginning2[4]="\nlines(lowess("+fittedValues+","+residValues+ ")";
                                     model.plotControl.traditionalPlotFormula[4] =fittedValues+","+residValues;
                                }   
                                else {
                                   /* String fittedValues = new String();
                                    if (model.method.equals("lm")||model.method.equals("pgmm")||
                                         model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("GMerrorsar")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")
                                          ||model.method.equals("stsls")||model.method.equals("gstsls")||model.method.equals("spautolm")
                                          ||model.method.equals("lagmess") 
                                    ){
                                        fittedValues ="fitted("+pre.modelName+")";
                                    }  else if  (model.method.equals("dynlm")) {         
                                           fittedValues ="as.numeric(fitted("+pre.modelName+"))";
                                    } else 
                                        fittedValues = "("+pre.modelName+NameSuffix+
                                               "$model$\""+model.dependentVar+"\""+"-resid("+pre.modelName+NameSuffix+",type=\"response\"))";*/
                                    model.plotControl.xLabels[4] =  "round("+fittedValues+",3)";
                                    model.plotControl.yLabels[4] =  "round(resid("+pre.modelName+NameSuffix+",type=\"response\")"+",3)";
                                    model.plotControl.traditionalPlotBeginning[4] =                      
                                      "plot("+fittedValues+",resid("+pre.modelName+NameSuffix+",type=\"response\")";
                                    model.plotControl.traditionalPlotBeginning2[4]= "\nlines(lowess("+fittedValues+",resid("+pre.modelName+NameSuffix+",type=\"response\"))";
                                    model.plotControl.traditionalPlotFormula[4] = "resid("+pre.modelName+NameSuffix+",type=\"response\")~"+fittedValues;
                                }
                                model.plotControl.previousPlot6 = true;
                                model.plotControl.traditionalGraphicsAvailable[4] = true;
                                model.plotControl.traditionalPlotEnd[4] = "\nabline(0,0)";
                                model.plotControl.showTickMarks[4] = true;
                                model.plotControl.showGrid[4] = true;
                                model.plotControl.pointLabels[4] = "row names";
                                model.plotControl.whichPointsToLabel[4] = "none";
                                model.plotControl.numExtremeValues[4] = "1";
                                model.plotControl.extremenessBasis[4] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[4] = "5";
                                model.plotControl.mostExtremeEnabled[4] = true;
                                model.plotControl.timeOrObsHorizontalAxis[4] = false;
                                model.plotControl.pointLabelTextSize[4] = "1.0";
                                model.plotControl.mainTitle[4] = "Residuals vs Fitted Values with Lowess Curve";
                                model.plotControl.xAxisTitle[4] = "fitted values";
                                model.plotControl.manualyAxisRange[4]=false;
                                model.plotControl.yAxisTitle[4] = "residuals";
                                model.plotControl.manualxAxisRange[4]=false;
                                model.plotControl.legendText1[4] = "";
                                model.plotControl.linePoints1Symbol[4] = "16: solid circle";
                                model.plotControl.symbolSize1[4] = "0.5";
                                model.plotControl.linePoints1color[4] = "blue";
                                model.plotControl.legendText2[4] = "";
                                model.plotControl.linePoints2linewidth[4]="1";
                                model.plotControl.linePoints2color[4] = "green";
                                model.plotControl.lineType2[4]="solid";
                                
                                
                                call = model.plotControl.traditionalPlotBeginning[4]+
                                      (model.plotControl.mainTitle[4].equals("")?"":",main=\""+ model.plotControl.mainTitle[4]+"\"")+
                                      (model.plotControl.yAxisTitle[4].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[4]+"\"")+
                                      (model.plotControl.xAxisTitle[4].equals("")?"":",xlab=\""+ model.plotControl.xAxisTitle[4]+"\"")+
                                      ",pch="+model.plotControl.linePoints1Symbol[4].split(":")[0]+
                                      ",col=\""+model.plotControl.linePoints1color[4]+"\""+
                                     (model.plotControl.linePoints2linewidth[4].equals("1.0")?"":",lwd="+model.plotControl3.linePoints2linewidth)+
                                     (model.plotControl.lineType2[4].equals("solid")||model.plotControl.lineType2[4].equals("")?"":",lty=\""+model.plotControl.lineType2[4]+"\"")+ 
                                     ")"+
                                        model.plotControl.traditionalPlotEnd[4]+
                                      model.plotControl.traditionalPlotBeginning2[4]+
                                      ",col=\""+model.plotControl.linePoints2color[4]+"\")"+
                                      (model.plotControl.showGrid[4]?"\n grid(lwd = 2)":""); 
                                 
                              commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                              termTab = new ModelPlotPanel(call);
                              PlotsPanel4.add(termTab);
                            
                 } else if (cmd == "Residuals Map"){
                      termTab = new ModelPlotPanel(""); // this command is provided at this point because the following trellis command
                                                        //       requires a device to work with; otherwise it would open up JavaGD() 
                      Deducer.eval("trellis.par.set(sp.theme())" +
                         "\n arrow = list(\"SpatialPolygonsRescale\", layout.north.arrow(),offset = c(-76,34), scale = 0.5, which = 2)");
                    
                     
                     String cmdPre = "hanseltemp_SPresids <- SpatialPolygonsDataFrame(polygons("+model.efeglmextra.spdfForModel+"),data.frame(residuals("+model.modelName+"),row.names=row.names("+model.efeglmextra.spdfForModel+"@data)))\n"+
                                     "colnames(hanseltemp_SPresids@data)<-\"residuals\"\n";
                     Deducer.eval(cmdPre);
                     call= "spplot(hanseltemp_SPresids, \"residuals\", main = \"residuals\", scales = list(draw = TRUE),sp.layout = list(arrow), as.table = TRUE)";
                     termTab = new ModelPlotPanel("print("+call+")");
                     commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ cmdPre + call);
                     PlotsPanel4.add(termTab);                
                 } else if (cmd == "Coint. Rel. Short-term correct"){
                    
                        if (model.termsArray.length > 2) {
                          if (model.method.equals("ca.jo"))
                            call="print(xyplot(zoo(ts("+model.modelName+".HanselAddOns$CointRelShort[,2:"+(model.termsArray.length)+"]"+
                                    ",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")),xlab=\"\",main=\"Considered cointegrating relationships, corrected for short-term influences\"))";
                               editButton.setVisible(true);editButton.setEnabled(false);  
                              idLabelButton.setVisible(true);idLabelButton.setEnabled(false); idLAbelButtonEnabled = false;
                       } else {
                                model.plotControl.traditionalGraphicsAvailable[4] = true;
                                model.plotControl.latticeGraphicsAvailable[4] = true;
                                model.plotControl.mainTitle[4] = "Considered cointegrating relationship, corrected for short-term influences";
                                model.plotControl.showTickMarks[4] = true;
                                model.plotControl.showGrid[4] = true;
                                model.plotControl.pointLabels[4] = "row names";
                                model.plotControl.whichPointsToLabel[4] = "none";
                                model.plotControl.numExtremeValues[4] = "1";
                                model.plotControl.extremenessBasis[4] = "|y – mean(y)|";
                                model.plotControl.extremenessScrollChoices[4] = "1";
                                model.plotControl.mostExtremeEnabled[4] = true;
                                model.plotControl.timeOrObsHorizontalAxis[4] = false;
                                model.plotControl.yAxisTitle[4] = " ";
                                model.plotControl.manualyAxisRange[4]=false;
                                model.plotControl.xAxisTitle[4] = (model.existsTS?"":"Observation");
                                model.plotControl.manualxAxisRange[4]=false;
                                model.plotControl.legendText1[4] = "";
                                model.plotControl.plotType1[4] = "l: line";
                                model.plotControl.lineType1[4] = "solid";
                                model.plotControl.linePoints1linewidth[4]="1";
                                model.plotControl.linePoints1color[4] = "blue";
                                model.plotControl.linePoints1Symbol[4]="";
                                model.plotControl.legendText2[4] = "";
                                model.plotControl.linePoints2linewidth[4]="";
                                model.plotControl.linePoints2color[4] = "";

                                String vName = model.modelName+".HanselAddOns$CointRelShort[,2:"+(model.termsArray.length)+"]";

                                model.plotControl.traditionalPlotBeginning[4] = "plot("+  
                                         (model.existsTS?"ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")":vName);
                                model.plotControl.traditionalPlotFormula[4] = vName;
                                model.plotControl.latticePlotBeginning[4] = "xyplot("+(model.existsTS?"zoo(ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+"))":"ts("+vName+")");
                                
                                call = model.plotControl.traditionalPlotBeginning[4]+",type=\"l\""+",col=\""+model.plotControl.linePoints1color[4]+"\""+
                                            ",main=\""+ model.plotControl.mainTitle[4]+"\""+ 
                                            ",ylab=\""+ model.plotControl.yAxisTitle[4]+"\""+
                                            ",xlab=\""+ model.plotControl.xAxisTitle[4]+"\""+
                                            ")\ngrid(lwd = 2)";
                                
                               editButton.setVisible(true);editButton.setEnabled(true);  
                               idLabelButton.setVisible(true);idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;

                        }
                     commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                     termTab = new ModelPlotPanel(call);
                     PlotsPanel4.add(termTab);
                     
                 } else { //  Accuracy, ROC curves           
                          String pred = Deducer.getUniqueName("pred");
                          String cmd2 = pred +" <- prediction(fitted("+model.modelNameFinalStage+"),"+model.modelNameFinalStage+"$model[\""+model.dependentVar+"\"]) \n";
                          String cmdrmpred = "rm("+pred+")\n";
                          
                          Deducer.eval(cmd2);  
                          call="par(mfrow = c(2, 2),mar=c(5,4,2,2)) \n"+
                                   "plot(performance("+pred+", \"acc\"),col=\"green\")\n"+
                                   "grid(lwd = 2)\n"+
                                   "plot(performance("+pred+",\"tpr\",\"fpr\"),col=\"blue\") \n"+
                                   "abline(0, 1,lty=2,col=\"red\")\n"+
                                   "grid(lwd = 2)\n";
                          commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for "+ cmd +" *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ cmd2 + call + cmdrmpred);
                          termTab = new ModelPlotPanel(call);
                          Deducer.eval(cmdrmpred);
                          PlotsPanel4.add(termTab);        
                 }  
                         Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[4] <- dev.cur()");

 
	}
        
        public void varvsObsOrTimeClicked(GMModel modelTS){
            String call ="";
            String callCommandsLog ="";
            String vName = new String();
                        String callWarningPrefix = "MssgEW <- capture.output(tryCatch.W.E(";
                        String callWarningSuffix = ")$warning)";
                         callCommandsLog += "\n\n#The following commands result in the output found in the General tab:\n";
                         callCommandsLog += "JavaGD() #Opens a new graphic device\n";


                                
                        String analysisVariablesList = "\""+modelTS.terms.get(0).toString()+ "\"";
                        String analysisVariables =   modelTS.terms.get(0).toString();
                          if (modelTS.terms.getSize()>1) {
                            for(int i=1;i<modelTS.terms.getSize();i++){
                            analysisVariablesList = analysisVariablesList + ",\""+ modelTS.terms.get(i).toString()+"\"";
                            analysisVariables = analysisVariables + ","+ modelTS.terms.get(i).toString();
                            }
                          } 
                          
                          if (modelTS.data.toString().contains("HanselAddOns")&!model.panelModel)
                                    vName = modelTS.data+"$"+ vName+modelTS.efeglmextra.TSMainTitle;
                          else {
                               if (modelTS.dName.contains("data.frame("))
                                  vName= modelTS.dName;
                               else if (modelTS.terms.getSize()>1) 
                                   if (model.panelModel)
                                       vName= "with("+modelTS.dName+",cbind("+analysisVariables+"))";
                                   else
                                       vName= modelTS.dName+"[,c("+analysisVariablesList+")]";
                               else {
                                   if (analysisVariablesList.contains("resid(")||analysisVariablesList.contains("resid[")
                                           ||model.method.equals("tobit"))
                                       vName= analysisVariables;
                                   else if (model.panelModel)
                                       vName= modelTS.data+"$"+analysisVariables;
                                   else
                                       vName= modelTS.dName+"[,"+analysisVariablesList+"]";
                               }
                          }
                                model.plotControl.graphicSystem[5] = "lattice"; 
                                model.plotControl.traditionalGraphicsAvailable[5] = true;
                                model.plotControl.latticeGraphicsAvailable[5] = true;
                                model.plotControl.mainTitle[5] = modelTS.efeglmextra.TSMainTitle;
                                model.plotControl.showTickMarks[5] = true;
                                model.plotControl.showGrid[5] = true;
                                model.plotControl.pointLabels[5] = "row names";
                                model.plotControl.whichPointsToLabel[5] = "none";
                                model.plotControl.numExtremeValues[5] = "1";
                                model.plotControl.extremenessBasis[5] = "Cook's distance";
                                model.plotControl.extremenessScrollChoices[5] = "5";
                                model.plotControl.singleGraphMulti[5] = false;
                                model.plotControl.mostExtremeEnabled[5] = true;
                                model.plotControl.timeOrObsHorizontalAxis[5] = true;
                                model.plotControl.yAxisTitle[5] = new String(modelTS.efeglmextra.TSlabels).replace("\"", "");
                                model.plotControl.manualyAxisRange[5]=false;
                                model.plotControl.xAxisTitle[5] = (model.existsTS||model.panelModel?" ":"Observation");
                                model.plotControl.manualxAxisRange[5]=false;
                                model.plotControl.legendText1[5] = "";
                                model.plotControl.plotType1[5] = "l: line";
                                model.plotControl.lineType1[5] = "solid";
                                model.plotControl.linePoints1linewidth[5]="1";
                                model.plotControl.linePoints1color[5] = "blue";
                                model.plotControl.linePoints1Symbol[5]="";
                                model.plotControl.legendText2[5] = "";
                                model.plotControl.linePoints2linewidth[5]="";
                                model.plotControl.linePoints2color[5] = "";
                                model.plotControl.xLabels[5] = model.rowNamesOrDatesLabel;
                                model.plotControl.yLabels[5] =  "round("+(model.existsTS?"ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")":vName)+",3)";
                                
                                model.plotControl.traditionalPlotBeginning[5] = "plot("+
                                                                                 (model.existsTS?
                                                                                   (model.dataClassInR.equals("xts")?"as.xts("+vName+")":
                                                                                       "ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")")
                                                                                    :(model.panelModel?vName:"ts("+vName+")")
                                                                                    );
                                
                              
                                model.plotControl.traditionalPlotFormula[5] = (model.existsTS?
                                                                                   (model.dataClassInR.equals("xts")?"as.xts("+vName+")":
                                                                                       "ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")")
                                                                                    :(model.panelModel?vName:"ts("+vName+")")
                                                                                    );
                                

                                model.plotControl.latticePlotBeginning[5] = "xyplot("+(model.existsTS?
                                                                                      (model.dataClassInR.equals("xts")?"as.xts("+vName+")":
                                                                                       "ts("+vName+",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data+")")
                                        :(model.panelModel?vName:"ts("+vName+")")
                                        );
                                
                                model.plotControl.preCommand[5]="";
                                model.plotControl.postCommand[5]="";
                                if ((modelTS.data.contains("@R0")||modelTS.data.contains("@RK")||modelTS.data.contains("@res")
                                    ||modelTS.efeglmextra.TSMainTitle.equals("Cointegrating Relationship")||modelTS.dName.contains("data.frame("))&modelTS.terms.getSize()>1) {
                                   /* JOptionPane.showMessageDialog(null, "debug--why am I here?");*/
                                    model.plotControl.preCommand[5] = "GMExplorer_temp <-"+vName+
                                                                      "\ncolnames(GMExplorer_temp)<-c(\""+modelTS.efeglmextra.TSlabels.replace(",","\",\"")+"\")";
                                    model.plotControl.traditionalPlotBeginning[5] = model.plotControl.traditionalPlotBeginning[5].replace(vName, "GMExplorer_temp");
                                    model.plotControl.latticePlotBeginning[5] = model.plotControl.latticePlotBeginning[5].replace(vName, "GMExplorer_temp");
                                    model.plotControl.postCommand[5] = "\nrm(GMExplorer_temp)";
                                            }
                                if (modelTS.terms.getSize()>1) 
                                    model.plotControl.yAxisTitle[5] = "";

                                  call = model.plotControl.latticePlotBeginning[5]+
                                        (model.plotControl.mainTitle[5].equals("")?"":",main=\""+ model.plotControl.mainTitle[5]+"\"")+
                                        (model.plotControl.yAxisTitle[5].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[5]+"\"")+
                                        ",xlab=\""+ model.plotControl.xAxisTitle[5]+"\""+
                                        ",grid=TRUE)";
                                callCommandsLog += call;
                                commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot command for Residuals vs Time or Obs************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ 
                                           (model.plotControl.preCommand[5].equals("")?"":model.plotControl.preCommand[5]+"\n")+
                                            call+
                                            (model.plotControl.postCommand[5].equals("")?"":model.plotControl.postCommand[5])
                                            );
                                
                             
                          PlotsPanel5.removeAll();
                          PlotsPanel5.setVisible(false);      
                                
                          if (!model.plotControl.preCommand[5].equals(""))
                              Deducer.eval(model.plotControl.preCommand[5]);
                          
                          if (model.plotControl.graphicSystem[5].equals("lattice"))
                              plotvsTimeORObsPanel = new ModelPlotPanel("print("+call+")");
                          else
                              plotvsTimeORObsPanel = new ModelPlotPanel(call);  
                          
                          if (!model.plotControl.postCommand[5].equals(""))
                              Deducer.eval(model.plotControl.postCommand[5]);
                          

                          PlotsPanel5.add(plotvsTimeORObsPanel);
                          Deducer.eval(hanselEnvAndModelName+"_estPlotPanelDevNums[5] <- dev.cur()");
                          PlotsPanel5.setVisible(true);
                 }  

       
 
                         
                        
        
        

	public void moreDiagnosticPlotsClicked(){

		GMExplorerOtherPlots rr = new GMExplorerOtherPlots(this,model,pre);
                rr.setLocationRelativeTo(this);
		rr.setVisible(true);
		setaddexpplots(model);
                rr.setModel(model, pre);//This resets choices to false

	}
	public void postHocClicked(){
		GLMExplorerPostHoc post = new GLMExplorerPostHoc(this,model,pre);
		post.setLocationRelativeTo(this);
		post.setVisible(true);
		setaddexp(model);
                post.setModel(model, pre);//This resets choices to false
	}
	public void exportClicked(){
            if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")){
		GMExplorerExport exp = new GMExplorerExport(this,model);
		exp.setLocationRelativeTo(this);
		exp.setVisible(true);
                setHanselExport(model);
                exp.setModel(model); //This resets choices to false
            }
             else {
                  changeTermClicked("export");
                  
	
            }
        }

	public void meansClicked(){
		GLMExplorerMeans m = new GLMExplorerMeans(this,model,pre);
		m.setLocationRelativeTo(this);
		m.setVisible(true);
		setaddexp(model);
                m.setModel(model, pre);//This resets choices to false
	}
	
	
        public void confidenceClicked(){
		GMConfidenceIntervals conf = new GMConfidenceIntervals(this,model,pre);
		conf.setLocationRelativeTo(this);
		conf.setVisible(true);
                if (model.efeGLMOptions.confIntervals)
                   {setaddexp(model);}
                conf.setModel(model, pre);//This resets choices to false
               
	}       
        
        public void classicViewClicked(){
               if (model.efeglmextra.classicView)
                   model.efeglmextra.classicView = false;
               else model.efeglmextra.classicView = true;
	}  
        
        
        
        
	public void testsClicked(){
                GMExplorerTestCoeffsMain testCoeffDialog = new GMExplorerTestCoeffsMain(this,model,pre);
		testCoeffDialog.setLocationRelativeTo(this);
		testCoeffDialog.setVisible(true);
                if (model.efeGLMOptions.coeffTest) {
                    setaddexp(model);
                    if (model.efeGLMOptions.coeffTestsGUI)
                    {
                    try {
                           s =Deducer.eval("names(coef("+pre.modelName+"))").asStrings();
                    } catch (REXPMismatchException e) {
                    new ErrorMsg(e);
                    }
                    Vector trms = new Vector();
                    for(int i=0;i<s.length;i++)
                            trms.add(s[i]);

                    GLMExplorerTests p = new GLMExplorerTests(this,trms,model);
                    p.setLocationRelativeTo(this);
                    p.setVisible(true);
                    setaddexp(model);	
                    }
                    else 
                    {if (model.efeGLMOptions.postHocGUI)
                        {GLMExplorerPostHoc post = new GLMExplorerPostHoc(this,model,pre);
                         post.setLocationRelativeTo(this);
                         post.setVisible(true);
                         setaddexp(model);
                        };
                    };
                    model.efeGLMOptions.coeffTest = false;
                };
	}
	
        public void diagnosticPlotsClicked(){
            
        }
        public void causalityTestsClicked(){
		GMExplorerCausality opt = new GMExplorerCausality(this,model,pre);
		opt.setLocationRelativeTo(this);
		opt.setVisible(true);
		setaddexp(model);
                opt.setModel(model,pre);//This resets choices to false             
	}
	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
                this.dispose();
               }

	public void windowDeactivated(WindowEvent arg0) {}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}
        
        public void findFirsFourTerms() {
            if (model.termsArray.length > 1){
                if (model.method.equals("ivreg")){
                     firstFourTerms = model.termsFinalStage.getElementAt(0)+"+"+model.termsFinalStage.getElementAt(1);
                    for(int i=1;i<(Math.min(model.termsFinalStage.size(),4));i++)
                          firstFourTerms = firstFourTerms + "+" + model.termsFinalStage.getElementAt(i);
                }else{
                    firstFourTerms = model.termsArray[0] +"+"+model.termsArray[1];
                     for(int i=1;i<(Math.min(model.termsArray.length,4));i++)
                           firstFourTerms = firstFourTerms + "+" + model.termsArray[i];
               }
           }
       }
  
        
        public void reset(){}
        public void helpClicked(){}
          public void turnOffDevices(){
                                String libraryStatement ="";                        
                                if (model.EstimationMethod.equals("Ordinary Least Squares")){
                                        libraryStatement="detach(package:lmtest,unload=TRUE);detach(package:xtable,unload=TRUE);detach(package:sandwich,unload=TRUE)";
                                } else if (model.EstimationMethod.equals("Weighted Least Squares")){
                                        libraryStatement="detach(package:lmtest,unload=TRUE);detach(package:xtable,unload=TRUE);detach(package:sandwich,unload=TRUE)";
                                } else if (model.EstimationMethod.equals("Binary Logit")){
                                        libraryStatement="detach(package:tonymisc,unload=TRUE);detach(package:pscl,unload=TRUE);detach(package:xtable,unload=TRUE);detach(package:ROCR,unload=TRUE)";
                                } else if (model.EstimationMethod.equals("Binary Probit")){
                                        libraryStatement="detach(package:tonymisc));detach(package:pscl,unload=TRUE);detach(package:xtable,unload=TRUE);detach(package:ROCR,unload=TRUE)";
                                 } else if (model.EstimationMethod.equals("Tobit")){
                                        libraryStatement="detach(package:AER,unload=TRUE)";
                                } else if (model.EstimationMethod.equals("SAR model - ML")||model.EstimationMethod.equals("SAR model - GM")||
                                   model.EstimationMethod.equals("SAC model - GMM")||model.EstimationMethod.equals("Matrix expontential spatial lag model ")||
                                   model.EstimationMethod.equals("SAR lag model")||model.EstimationMethod.equals("SAC/SARAR model")||
                                   model.EstimationMethod.equals("generalized spatial two-stage least squares")){
                                        libraryStatement="detach(package:AER,unload=TRUE)";
                                 } else if (model.EstimationMethod.equals("Two-Stage Least Squares")){
                                        libraryStatement="detach(package:AER));detach(package:xtable,unload=TRUE))";
                                 } else if (model.EstimationMethod.equals("Panel - Linear Model")){
                                        libraryStatement="detach(package:plm,unload=TRUE);detach(package:xtable,unload=TRUE);detach(package:library(AER,unload=TRUE)";
                                  } else if (model.EstimationMethod.equals("Panel - Variable Coefficients")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                  } else if (model.EstimationMethod.equals("Panel - General Feasible GLS")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("Panel - GMM (e.g. for dynamic equations)")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                  } else if (model.EstimationMethod.equals("Panel - Common Correlated Effects Pooled Estimator")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("Panel - Mean Groups")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("spatial panel model - ML")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("spatial panel model - GM")){
                                        libraryStatement="detach(package:plm,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("Vector Autoregression")){
                                        libraryStatement="detach(package:vars,unload=TRUE)";
                                   } else if (model.EstimationMethod.equals("Cointegration Test - Johansen")){
                                        libraryStatement="detach(package:urca));detach(package:zoo,unload=TRUE)";
                                    } else if (model.EstimationMethod.equals("Cointegration Test - Philips & Ouliaris")){
                                            libraryStatement="detach(package:urca));detach(package:zoo,unload=TRUE)";
                                    } else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                                            libraryStatement="detach(package:urca));detach(package:zoo,unload=TRUE);detach(package:dynlm));detach(package:plm,unload=TRUE);";
                                    } 
                                 Deducer.eval(libraryStatement);

	}
        public void cancel(){
               noReactiontoAction = true; 
        
                this.setVisible(false);
		this.dispose();
	}
        class ModelListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
                        
                        if(cmd=="Cancel:Keep"){
                               /*  Deducer.eval("rm("+pre.modelName.toString()+"_estPlotPanelDevNums,envir="+Deducer.guiEnv+")");
                                 cancel();*/
                               for(int i=1;i<=6;i++)
                                          Deducer.eval("dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums"+"["+i+"])");
                                Deducer.eval("rm("+pre.modelName.toString()+"_estPlotPanelDevNums"+",envir="+Hansel.hanselEnv+")");   
				cancel();
                                 
                        } else if (cmd=="Cancel:Remove"){                    
                                      for(int i=1;i<=6;i++)
                                          Deducer.eval("dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums"+"["+i+"])");
                                      Deducer.eval("rm("+pre.modelName.toString()+"_estPlotPanelDevNums"+",envir="+Hansel.hanselEnv+")");   
				      cancel();
                                       
                                      String cmdivreg="";
                                      if (model.method.equals("ivreg")){
                                         for(int i=0;i<model.termsArray.length;i++){
                                             if (!model.instruments.contains(model.termsArray[i])){
                                                  cmdivreg="\n rm("+model.termsArray[i] +".fit_Stg1)";                            
                                            }
                                         }
                                         Deducer.eval(cmdivreg);
                                      }
                                       if (model.method.equals("ca.jo")){
                                          Deducer.eval(".hansel.working.env$latestModel<-list("+pre.modelName+","+pre.modelName+".eigen,"+pre.modelName+".HanselAddOns)"+
                                                        "\n rm("+pre.modelName+","+pre.modelName+".eigen,"+pre.modelName+".HanselAddOns)");
                                       } else if (model.method.equals("ca.po")){
                                           Deducer.eval(
                                                   "\nif (exists('"+pre.modelName+".MssgEW') ) {\n"+
                                                        ".hansel.working.env$latestModel<-list("+pre.modelName+","+pre.modelName+".MssgEW)\n" +
                                                        "rm("+pre.modelName+")\n"+
                                                        "rm("+pre.modelName+".MssgEW)\n"+
                                                      "} else {\n"+
                                                        ".hansel.working.env$latestModel<-"+pre.modelName+" \n"+ 
                                                        "rm("+pre.modelName+")\n"+
                                                      "}");
                                       } else if (model.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                                           Deducer.eval(".hansel.working.env$latestModel<-"+pre.modelName+"\n rm("+pre.modelName+")");
                                       } else
                                           Deducer.eval(".hansel.working.env$latestModel<-"+pre.modelName+"\n rm("+pre.modelName+")");
                                          
                                 cancel();

                        }else if(cmd=="Initial Selections Page"){
                                for(int i=1;i<=6;i++)
                                          Deducer.eval("dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums"+"["+i+"])");
                                Deducer.eval("rm("+pre.modelName.toString()+"_estPlotPanelDevNums"+",envir="+Hansel.hanselEnv+")");   
				cancel();
				specifyClicked(); 
                        }else if(cmd=="Classic View"){
                            classicViewClicked();
			    if (previewPanel.isVisible()){
                                previewPanel.setVisible(false);
                                previewPanelSmall.setVisible(false);
                                previewClassicPanel.setVisible(true);
                            }else if (previewClassicPanel.isVisible()){
                                previewClassicPanel.setVisible(false);
                                previewClassicPanelSmall.setVisible(false);
                                previewPanel.setVisible(true); 
                            }else if (previewPanelSmall.isVisible()){
                                previewPanel.setVisible(false);
                                previewPanelSmall.setVisible(false);
                                previewClassicPanelSmall.setVisible(true);
                            }else if (previewClassicPanelSmall.isVisible()){
                                previewClassicPanel.setVisible(false);
                                previewClassicPanelSmall.setVisible(false);
                                previewPanelSmall.setVisible(true);
                            }
                        }else if ((cmd == "Text View Toggle")){
                                                                                           
                            if (previewPanel.isVisible()){
                                  previewPanel.setVisible(false);
                                  previewPanelSmall.setVisible(true);
                                  addexpPanel.setVisible(true);
                            }else if (previewPanelSmall.isVisible())
                            {
                                  previewPanelSmall.setVisible(false);
                                  addexpPanel.setVisible(false);
                                  previewPanel.setVisible(true);                                  
                            } else if (previewClassicPanel.isVisible()){
                                  previewClassicPanel.setVisible(false);
                                  previewClassicPanelSmall.setVisible(true);
                                  addexpPanel.setVisible(true);
                            } else {
                                   previewClassicPanelSmall.setVisible(false);
                                  addexpPanel.setVisible(false);
                                  previewClassicPanel.setVisible(true);
                            }
			}else if(cmd.equals("ANOVA")||cmd.equals("Analysis of Deviance")){
				anovaClicked(cmd);
                        }else if(cmd == "Associated VAR Model"){
                            if (rankRestriction.getSelectedItem().toString().equals("choose"))
			     JOptionPane.showMessageDialog(null, "Please choose the number of cointegrating equations first.");
                            else
				vecExploreVARClicked();                           
                        }else if(cmd == "Diagnostic Statistics & Tests"){
				diagnosticTestsClicked();
			}else if(cmd == "Post Hoc"){
				postHocClicked();
			}else if(cmd == "Impulse Response Functions"){
                                PlotsPanel2.setVisible(false);
                                editButton.setVisible(true);editButton.setEnabled(false);
                                idLabelButton.setVisible(true);idLabelButton.setEnabled(false); idLAbelButtonEnabled = false;
                                iplotButton.setVisible(false);
                                
                                irfButton.setSelected(true);
                                diagnosticsButton.setSelected(false);
                                plotsvsTimeButton.setSelected(false);
				irfClicked();
                     	}else if(cmd == "Diagnostics"||cmd=="Cointegrating relation, 1st variable on left"){
                                diagnosticsButton.setSelected(cmd == "Diagnostics");
                                editButton.setVisible(false);idLabelButton.setVisible(false);iplotButton.setVisible(false);
                                if (model.method.equals("VAR")||model.method.equals("vec2var") ) 
                                    irfButton.setSelected(false);
                                else {
                                    regressionFunctionPlotButton.setSelected(false);
                                    iplotButton.setVisible(false);
                                    idLabelButton.setVisible(false); idLAbelButtonEnabled = false;
                                    /*confInt95.setVisible(false);*/
                                    cointRelButton.setSelected(false);cointRelsButton.setSelected(false);
                                    cointRelSCButton.setSelected(false);
                                    accuracyAndROCcurvesButton.setSelected(false);
                                    residualsVsFitButton.setSelected(false);
                                    residualsMapButton.setSelected(false);
                                    plotsvsObsButton.setSelected(false);plotsvsTimeButton.setSelected(false);
                                    partialregressionPlotButton.setSelected(false);
                                    otherPlotsButton.setSelected(false);
                                }
                                PlotsPanel3.setVisible(false);
                                PlotsPanel4.setVisible(false);
                                PlotsPanel5.setVisible(false);
                                PlotsPanel6.setVisible(false);
                                PlotsPanel2.setVisible(true);
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[3]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[4]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[5]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                         }else if((cmd == "Regression Function Plot")||(cmd == "Partial Regression Plot")||(cmd == "Partial Regression Plot;3D")||(cmd=="Regression Plots")||(cmd=="Spinograms")||(cmd=="Cointegrating Relation")||(cmd=="Cointegrating Relation(s)")) {
                                diagnosticsButton.setSelected(false);
                                regressionFunctionPlotButton.setSelected((cmd == "Regression Function Plot"));
                                
                                editButton.setVisible(true);
                                if (cmd.equals("Cointegrating Relation(s)")&(model.termsArray.length > 2)) 
                                    editButton.setEnabled(false);  
                                else 
                                    editButton.setEnabled(true);
                                idLabelButton.setVisible(true); idLAbelButtonEnabled = true;
                                idLabelButton.setEnabled(cmd == "Regression Function Plot");
                                iplotButton.setVisible(cmd == "Regression Function Plot"&model.numExplanatoryVariables==1);
                                
                                if (model.numExplanatoryVariables==1) {
                                  iplotButton.setVisible(true);
                                } else {
                                  iplotButton.setVisible(false);
                                }
                                partialregressionPlotButton.setSelected((cmd == "Partial Regression Plot"));
                                cointRelButton.setSelected((cmd == "Cointegrating Relation"));
                                cointRelsButton.setSelected((cmd == "Cointegrating Relation(s)"));
                                cointRelSCButton.setSelected(false);
                                accuracyAndROCcurvesButton.setSelected(false);
                                regressionPlotsButton.setSelected(cmd == "Regression Plots");
                                spinogramsButton.setSelected(cmd == "Spinograms");
                                residualsVsFitButton.setSelected(false);
                                residualsMapButton.setSelected(false);
                                plotsvsObsButton.setSelected(false);plotsvsTimeButton.setSelected(false);
                                otherPlotsButton.setSelected(false);
                                PlotsPanel2.setVisible(false);
                                PlotsPanel4.setVisible(false);
                                PlotsPanel5.setVisible(false);
                                PlotsPanel6.setVisible(false);
                                PlotsPanel3.setVisible(true);
                                if (!regFnPlotPreviouslyClicked){
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[2]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[4]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[5]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                   if (firstFourTerms.equals("")&(model.termsArray.length > 1))
                                       findFirsFourTerms();
                                   regressionFunctionPlotClicked(cmd);
				   regFnPlotPreviouslyClicked = true;
                                }              
                         }else if((cmd == "Residuals vs Fitted")||(cmd == "Accuracy & ROC curves")
                                   ||(cmd == "Coint. Rel. Short-term correct")||(cmd == "Residuals Map")){
                             if (firstFourTerms.equals("")) findFirsFourTerms();
                                diagnosticsButton.setSelected(false);
                                regressionFunctionPlotButton.setSelected(false);
                                PlotEdit.setVisible(false);
                                
                                editButton.setVisible(true);
                                if (cmd.equals("Coint. Rel. Short-term correct")&(model.termsArray.length > 2)
                                        ||(cmd == "Accuracy & ROC curves")||(cmd == "Residuals Map")) 
                                    editButton.setEnabled(false);  
                                else 
                                    editButton.setEnabled(true);
                                idLabelButton.setVisible(true);
                                idLabelButton.setEnabled(cmd == "Residuals vs Fitted");
                                idLAbelButtonEnabled = idLabelButton.isEnabled();
                                iplotButton.setVisible(false);
                                partialregressionPlotButton.setSelected(false);
                                plotsvsObsButton.setSelected(false);plotsvsTimeButton.setSelected(false);
                                cointRelsButton.setSelected(false);
                                residualsVsFitButton.setSelected((cmd == "Residuals vs Fitted"));
                                residualsMapButton.setSelected((cmd == "Residuals Map"));
                                accuracyAndROCcurvesButton.setSelected((cmd == "Accuracy & ROC curves"));
                                cointRelSCButton.setSelected((cmd == "Coint. Rel. Short-term correct"));                             
                                otherPlotsButton.setSelected(false);
                                PlotsPanel2.setVisible(false);
                                PlotsPanel3.setVisible(false);
                                PlotsPanel5.setVisible(false);
                                PlotsPanel6.setVisible(false);
                                PlotsPanel4.setVisible(true);
                                if (!partialResidsPreviouslyClicked){
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[2]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[3]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[5]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}");
                                   if (firstFourTerms.equals("")&(model.termsArray.length > 1))
                                       findFirsFourTerms();
				   residualsVsResponseClicked(cmd);
                                   partialResidsPreviouslyClicked = true;
                                }
                         }else if((cmd == "Residuals vs Time or Obs")||(cmd == "Plots vs Time")||(cmd == "More Plots vs Time, incl. Cointegrating Relations")){ 
                                diagnosticsButton.setSelected(false);
                                regressionFunctionPlotButton.setSelected(false);
                                editButton.setVisible(true);editButton.setEnabled(true);  
                                idLabelButton.setVisible(true);
                                idLabelButton.setEnabled(true); idLAbelButtonEnabled = true;
                                iplotButton.setVisible(false);
                                partialregressionPlotButton.setSelected(false);
                                residualsVsFitButton.setSelected(false);
                                residualsMapButton.setSelected(false);
                                cointRelButton.setSelected(false); cointRelsButton.setSelected(false);
                                accuracyAndROCcurvesButton.setSelected(false);
                                cointRelSCButton.setSelected(false);
                                plotsvsObsButton.setSelected((cmd == "Residuals vs Time or Obs"));
                                plotsvsTimeButton.setSelected((cmd == "Plots vs Time"));
                                plotsvsTimeExtendedButton.setSelected((cmd =="More Plots vs Time, incl. Cointegrating Relations"));
                                regressionPlotsButton.setSelected(false);
                                spinogramsButton.setSelected(false);
                                irfButton.setSelected(false);
                                otherPlotsButton.setSelected(false);
                                PlotsPanel2.setVisible(false);
                                PlotsPanel3.setVisible(false);
                                PlotsPanel4.setVisible(false);
                                PlotsPanel6.setVisible(false);
                                PlotsPanel5.setVisible(true);  
                                
                               if (!(cmd == "Residuals vs Time or Obs")||!plotsvsObsOrTimePreviouslyClicked){
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[2]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[3]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[4]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]<-\"\"}");
                                   Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[6]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[6]<-\"\"}"); 
                                   residTimeClicked("plotsvsObsOrTime");
                                   plotsvsObsOrTimePreviouslyClicked = true;
                                }       
			}else if(cmd == "Other Plots"){
                                diagnosticsButton.setSelected(false);
                                regressionFunctionPlotButton.setSelected(false);
                                PlotEdit.setVisible(false);
                                editButton.setVisible(true);editButton.setEnabled(false);  
                                idLabelButton.setVisible(true);
                                idLabelButton.setEnabled(false); idLAbelButtonEnabled = false;
                                iplotButton.setVisible(false);
                                partialregressionPlotButton.setSelected(false);
                                residualsVsFitButton.setSelected(false);
                                residualsMapButton.setSelected(false);
                                cointRelButton.setSelected(false);cointRelsButton.setSelected(false);
                                cointRelSCButton.setSelected(false);
                                accuracyAndROCcurvesButton.setSelected(false);
                                plotsvsObsButton.setSelected(false);plotsvsTimeButton.setSelected(false);
                                otherPlotsButton.setSelected(true); 
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[2]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[2]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[3]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[3]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[4]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[4]<-\"\"}");
                                Deducer.eval("if (!"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]==\"\") {dev.off("+hanselEnvAndModelName+"_estPlotPanelDevNums[5]);"+hanselEnvAndModelName+"_estPlotPanelDevNums[5]<-\"\"}");
				moreDiagnosticPlotsClicked();
                                PlotsPanel2.setVisible(false);
                                PlotsPanel3.setVisible(false);
                                PlotsPanel4.setVisible(false);
                                PlotsPanel5.setVisible(false);
                                PlotsPanel6.setVisible(true);
                        } else if(cmd.equals("Plot Edit")){
                            PlotEditClicked();
                        } else if (cmd.equals("main title")||cmd.equals("y-axis title")||
                                 cmd.equals("x-axis title")||
                                 cmd.equals("point labels-row names")||cmd.equals("point labels-y values")||cmd.equals("point labels-x values")||cmd.equals("point labels-none")||
                                  cmd.equals("data name in key")||cmd.equals("larger point symbols")||cmd.equals("smaller point symbols")||cmd.equals("no point symbols")||
                                  cmd.equals("line-thicker")||cmd.equals("line-thinner")||cmd.equals("line-none")||
                                  cmd.equals("fit name in key")||
                                  cmd.equals("fit line-thicker")||cmd.equals("fit line-thinner")||cmd.equals("fit line-none")||
                                  cmd.equals("color black")||cmd.equals("color gray")||cmd.equals("color red")||
                                  cmd.equals("color orange")||cmd.equals("color yellow")||cmd.equals("color green")||  
                                  cmd.equals("color blue")||cmd.equals("color purple")||cmd.equals("color violet")||
                                  cmd.equals("color brown")||
                                  cmd.equals("fit color black")||cmd.equals("fit color gray")||cmd.equals("fit color red")||
                                  cmd.equals("fit color orange")||cmd.equals("fit color yellow")||cmd.equals("fit color green")||  
                                  cmd.equals("fit color blue")||cmd.equals("fit color purple")||cmd.equals("fit color violet")||
                                  cmd.equals("fit color brown")||cmd.equals("line solid")||cmd.equals("line dashed")||cmd.equals("line dotdash")||
                                  cmd.equals("line longdash")||cmd.equals("line twodash")||cmd.equals("fit line solid")||cmd.equals("fit line dashed")||cmd.equals("fit line dotdash")||
                                  cmd.equals("fit line longdash")||cmd.equals("fit line twodash")||cmd.equals("95% confidence interval")||cmd.equals("no 95% confidence interval"))
                                  {
				alterPlot(cmd);
                        } else if(cmd == "iplot"){
                                int panelDealtWith = 0;
                                if(regressionFunctionPlotButton.isSelected()||partialregressionPlotButton.isSelected()||cointRelsButton.isSelected()) {
                                     panelDealtWith = 3;
                                } else if(/*residualsVsExplanatoryButton.isSelected()||*/residualsVsFitButton.isSelected()||cointRelSCButton.isSelected()) {
                                     panelDealtWith = 4;
                                } else if(plotsvsObsButton.isSelected()||plotsvsTimeButton.isSelected()||plotsvsTimeExtendedButton.isSelected()) {
                                     panelDealtWith = 5;
                                } else if(otherPlotsButton.isSelected()) {
                                     panelDealtWith = 6;
                                }
				Deducer.execute("iplot("+model.dName+"[,\""+model.termsArray[0]+"\"],"+model.dName+"[,\""+model.dependentVar+"\"]"+ 
                                                ")\n" +
                                                "iabline("+pre.modelName+")");  
                                interactiveClicked("iplot");
                        } else if(cmd == "id-label"){
                            interactiveClicked("id-label");
                        }else if(cmd == "Confidence Intervals"){
				confidenceClicked();
			}else if(cmd == "Coefficient Tests"){
				testsClicked();
                      /*  }else if(cmd == "Spatial Tests"){
                            if (model.method.equals("lm")||(model.method.equals("dynlm")))
				spatialTestsClicked();
                            else
                               spatialTestsPanelClicked();*/
                        }else if(cmd == "Causality Tests"){
				causalityTestsClicked();
                        }else if((cmd == "Residuals -> Time Series Explorer")
                                 ||(cmd == "Output -> Time Series Explorer")
                                 ||(cmd == "Model -> OLS Explorer")){
				residTimeClicked(cmd);                                                          
			}else if(cmd == "Export"){
				   exportClicked();
			}else if(cmd =="Help"){
				helpClicked();
                        }else if(cmd =="Eigenvalue test"){
				eigenvalueTestClicked();
                        }else if(cmd =="Trace test"){
				traceTestClicked();
                                
                                
                        } else if (model.method.equals("ca.jo")){
                            if (!model.varOptions.rankRestriction.equals((String)rankRestriction.getSelectedItem().toString())&!noReactiontoAction)
				rankRestrictionsClicked();
                        }else if (model.method.equals("lm")||(model.method.equals("dynlm"))||model.method.equals("glm")||model.method.equals("ivreg")
                                ||model.method.equals("errorsarlm")||model.method.equals("lagsarlm")||model.method.equals("sacsarlm")){
                         
                           if (!model.glmvcov.equals((String)glmvcovsExplorer.getSelectedItem())&!noReactiontoAction){
				robustSEChangeGLMClicked(); 
                           }   
                        } else if (model.method.equals("tobit")){
                           if (!model.tobitvcov.equals((String)tobitvcovsExplorer.getSelectedItem())&!noReactiontoAction){
				robustSEChangetobitClicked();     
                           } 
                       } else if (model.method.equals("plm")){
                           if (!model.plmvcov.equals((String)plmvcovsExplorer.getSelectedItem())&!noReactiontoAction){
				robustSEChangeplmClicked();     
                                } 
                       } else if (model.method.equals("stsls") ){
                           if (!model.stslsvcov.equals((String)stslsvcovsExplorer.getSelectedItem())&!noReactiontoAction){
				robustSEChangestslsClicked();    
                                } 
                      } else if ((model.method.equals("gstsls")) ){
                           if (!model.gstslsvcov.equals((String)gstslsvcovsExplorer.getSelectedItem())&!noReactiontoAction){
				robustSEChangestslsClicked();   
                                } 
                       }
		}
		
	} 
        
}

