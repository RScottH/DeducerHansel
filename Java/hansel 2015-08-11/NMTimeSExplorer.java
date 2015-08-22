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
  ModelExplorer.java and GLMExplorer.java, GLMExplorerPostHoc.java and PlotBuilder.java, found in the Deducer package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-22.
 */

package hansel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.util.ArrayList;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import org.rosuda.JGR.JGR;
import org.rosuda.deducer.GDPreviewJPanel;
import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
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
import org.rosuda.deducer.plots.PlotBuilder;
import org.rosuda.deducer.plots.PlottingElement;



public class NMTimeSExplorer extends NMBasicExplorer implements WindowListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
    
	protected GMModel model = new GMModel();
	protected RModel pre;
        protected String[] ae;
        
        protected Boolean singleTimeSeries = false;

        protected String specificDecomposition;
        protected String specificMethod ;
        protected String specificArimaGarch;
        protected String urTestText;        
        protected String specificURTest;
        protected String specificSBTest;
        protected String analysisVariablesList;
        protected String[] s;
        protected Boolean[] efeplotchoices;
	protected ModelPlotPanel strucchangeTab;
      	protected ModelPlotPanel scatterTab;
        protected ModelPlotPanel predictionTab;
	protected ModelPlotPanel addedTab;
        protected ModelPlotPanel decompPlot;
        protected ModelPlotPanel arimaGarchPanelPlot1;
        protected ModelPlotPanel arimaGarchPanelPlot2;
        protected ModelPlotPanel addexpTab;
        protected ModelPlotPanel distributionTab;
        protected ModelPlotPanel requestedPlot;

	protected IconButton assumpHomo;
	protected IconButton assumpFunc;
	protected IconButton assumpN;
        protected JPanel correlogramTab;
        protected JLabel resultsViewLabel;

        protected JPanel levelRelationsPanel;
        protected ModelPlotPanel levelRelationsPanelcontent;
        protected JPanel squaredRelationsPanel;
        protected ModelPlotPanel squaredRelationsPanelcontent;
        protected JPanel plotvslagsTab;
        protected JPanel plotvslagsPanel;
        protected ModelPlotPanel plotvslagscontent;

        
        protected Boolean modelTSdata;
        protected JButton cancelClear;
        protected JButton adf;
        protected JButton adfGLS;
        protected JButton kpss;
        protected JButton philperr;
        protected JButton movingAverage;
        protected JButton polynomialFourier;
        protected JButton arima;
        protected JButton garch;
        protected JButton loess;
        protected JButton expSmoothing;
        protected JButton more;
        protected JButton wavelet;
        protected JButton exportButton;
        
        protected JButton zivotAndrews;
        protected JButton SchmidtPhillips;
        
        protected JButton gefpMTest;
        
        protected JPanel unitRootTestTab;
        protected JPanel unitRootTextPanel;
	protected JScrollPane unitRootTextScroller;
        protected JTextArea unitRootText;
        protected ModelPlotPanel unitRootTestcontent;
        protected JButton unitRootTextPlot; 
        
        protected JPanel structuralBreakTestTab;
        protected JPanel structuralBreakTextPanel;
	protected JScrollPane structuralBreakTextScroller;
        protected JTextArea structuralBreakText;
        protected ModelPlotPanel structuralBreakTestcontent;
        protected JButton structuralBreakTextPlot;
        
        
        protected JTextArea decompText;
        protected JPanel filterTab;
        protected JPanel filterTextPanel;
        protected JTextArea filterText;
        protected JScrollPane filterTextScroller;
        protected JPanel forecastFilterPlotPanel1;
        protected JPanel forecastFilterPlotPanel2;
        protected JPanel forecastFilterPlotPanel3;
        protected JPanel forecastFilterPlotPanel4;
        protected JPanel forecastFilterPlotPanel5;
        protected ModelPlotPanel forecastFilterPanelPlot1;
        protected ModelPlotPanel forecastFilterPanelPlot2;
        protected ModelPlotPanel forecastFilterPanelPlot3;
        protected ModelPlotPanel forecastFilterPanelPlot4;
        protected ModelPlotPanel forecastFilterPanelPlot5;
        protected ModelPlotPanel unitRootPlot;
        protected ModelPlotPanel structuralBreakPlot;
        protected JPanel decompositionTab;
        protected JPanel decompositionPanel;
        protected JPanel decompositionPlotPanel;
	protected JScrollPane decompositionScroller;
        protected JTextArea decompositionText;
        protected JButton forecastFilterPlot1; 
        protected JButton forecastFilterPlot2; 
        protected JButton forecastFilterPlot3; 
        protected JButton forecastFilterPlot4; 
        protected JButton arimaGarchTextView; 
        protected JPanel unitRootPlotPanel;
        protected JPanel structuralBreakPlotPanel;
        public Boolean SeenLatestFilterPlotPanel1;
        public Boolean SeenLatestFilterPlotPanel2;
        public boolean[] seenPlotPanels = new boolean[14];
        public String d$vName;
        public String vNameAdjusted;
        public String vNameAdjustedZoo;
        public String vName;
        protected JPanel commandsLogTab0;
        protected JPanel commandsLogTab;  
        protected JPanel commandsLogPanel;
        protected JScrollPane commandsLogScroller;
        protected JTextArea commandsLogText;
        
        protected JPanel GeneralPlotsPanel1;
        protected JPanel GeneralPlotsPanel2;
        protected JPanel GeneralPlotsPanel3;
        protected JPanel GeneralPlotsPanel4;
        protected JPanel GeneralPlotsPanel5;
        protected JPanel GeneralPlotsPanel6;
        protected JButton plotvsTimeButton;
        protected JButton distributionButton;
        protected JButton correlogramButton;
        protected JButton plotvsLagsButton;
        protected JButton structuralBreaksButton;
        protected String plotPanelsDevNumsName;
        protected String decompositionPrepCall = "";
        protected String decompositionCall = "";
        protected String decompositionLabelsCmd = "";
        protected String morePlotscall1 = "";
        protected String morePlotscall2 = "";
        protected String diagnosticscall = "";
        protected String callURPlot = "";
        protected String callSBPlot = "";
        
        protected String filterOutputResiduals = "";
        protected String filterOutput = "";
        protected String filterFittedValues = "";
        protected String decompTemp = "";
    
        public Boolean existsTS;
        public Boolean colnameIsNull;

        public JCheckBox labelPoints;
        
        protected JButton changeTitle;
        protected JButton changeYLabel;
        protected JButton changeXLabel;
        protected JButton largerPointsButton;  
        protected JButton smallerPointsButton;  
        protected JButton editButton;
        protected JButton idLabelButton;
        
        public float pointSizePanel1 = 0;
        public float lineThicknessDataPanel11 = 1;
        public float lineThicknessFitPanel11 = 1;
        public String lineColorDataPanel11 = "blue";
        public String lineColorFitPanel11 = "red";
        public String lineTypeDataPanel11 = "solid";
        public String lineTypeFitPanel11 = "solid";     
        
        public String mainTitleDataPanel1= "";
        public String yLabelDataPanel1= "";
        public String xLabelDataPanel1 = "";
        public String mainTitleFitPanel1 = "";
        public String yLabelFitPanel1 =  "";
        public String xLabelFitPanel1 = "";
        public String dataNameKey = "";
        public String fitNameKey = "fitted curve";
        
        public String panelFunction= "";
        
        public float pointSizePanel5 = 2;
        public String plotTitlePanel5 = "";
        public String YLabelPanel5 = "No Y-Label";
        public String XLabelPanel5 = "No X-Label";
        
        protected JLabel filterLabel;
        protected JComboBox filtertype;
        
        protected JLabel resultsAreaViewLabel;
        protected JComboBox resultsAreaViewType;
     
        public String plotObject;
        public String filterChoice = " ";
        public boolean noReactiontoAction = false;
        public String resultsAreaViewChosen = "Plot: Fit/Forecasts";
        public String plotType;
        public String ltext= "";
        
        public boolean variableTransformation = false;
        
        public String nameAdjustedForTS = "";
        public Boolean nullDimension = false;
        public Boolean nullColNames = false;
        public String vNameSimplifiedCharacters = "";
        public String vNameOrTSLabel = "";
        
        protected DefaultComboBoxModel filtersExplorer  = new DefaultComboBoxModel(
                				new String[] {"Choose change below","No Smoother/Filter/Forecast","Moving Average",
                                               "Polynomial, Fourier",
                                               "ARIMA",
                                               "Exponential Smoothing",
                                               "Hodrick-Prescott",
                                              /* "GARCH",*/
                                               "Lowess/Loess",

                                               "Wavelet"
                                              });
        
        protected DefaultComboBoxModel resultsAreaViewsExplorer  = new DefaultComboBoxModel(
                				new String[] {"Plot: Fit/Forecasts","Plot: Decomposition","Plot: Diagnostics",
                                               "Plot: Other 1","Plot: Other 2",
                                               "Text",
                                              });
        
        
        
        protected ActionListener generalListener = new ModelListener();
        
        NMTimeSExplorer(GMModel mod, Boolean modelTSInput){
		super();
                if (mod.terms.size()==1){
                   singleTimeSeries = true;
		   this.setTitle("Univariate Time Series Explorer");
                   this.setFont(font12);
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                   help.setEnabled(false);
                }
                else{
                  singleTimeSeries = false;
                  this.setTitle("Multivariate Time Series Explorer");
                  this.setFont(font12);
		  help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                  help.setEnabled(false);
                }    
                modelTSdata = modelTSInput;
		setModel(mod);
		initTabs();
		this.addWindowListener(this);
	}
	
	public void initTabs(){
                String call;
                String callCommandsLog ="";
                model.dName = model.data;
		try{    
                               model.dName = model.dName.replace("__","");
                               d$vName = model.dName+"$"+model.terms.get(0).toString();
                               vName = model.terms.get(0).toString();
                String existsTSasString = new String();
                  try {
                    existsTSasString = Deducer.eval("as.character(exists(\"T.S."+model.dName+"\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                existsTS = existsTSasString.equals("TRUE");
                if (existsTS) {
                    model.dName = "T.S." +model.dName;
                }
                  
                String colnameIsNullString = new String();
                  try {
                    colnameIsNullString = Deducer.eval("as.character(is.null(colnames("+model.dName+")))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                colnameIsNull = colnameIsNullString.equals("TRUE");
                
                 String dimdName = new String();
                           Boolean multivariableData = true;
                              try {
                                model.dataClassInR  = Deducer.eval("as.character(class("+model.dName+")[1])").asString();  
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                              try {
                                dimdName  = Deducer.eval("dim("+model.dName+")[2]").asString();
                                if (dimdName.equals("NULL")||dimdName.equals("1")) {
                                    multivariableData = false;
                                }
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                        analysisVariablesList = "\""+model.terms.get(0).toString()+ "\"";
                        String analysisVariables =   model.terms.get(0).toString();
                        
                        variableTransformation = false; 
                        for(int i=0;i<model.terms.getSize();i++){
                            if (model.terms.get(i).toString().contains("(")||model.terms.get(i).toString().contains("*")||model.terms.get(i).toString().contains("-"))
                               variableTransformation = true;
                            }
                         String[] availableVariables = new String[]{};
                         if (variableTransformation) {
                              try {
                                availableVariables =  Deducer.eval("colnames("+model.data+")").asStrings();    
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                                        
                           String vNameAddition = new String(model.terms.get(0).toString());
                           if (vNameAddition.contains("(")||vNameAddition.contains("*")||vNameAddition.contains("-") ){
                             for(int i=0;i<availableVariables.length;i++){
                                                    vNameAddition= vNameAddition.replace(availableVariables[i], model.dName+(colnameIsNull?"":"[,"+"\""+availableVariables[i]+"\""+"]"));
                                                    }
                           } else {
                             vNameAddition =     
                                             (colnameIsNull?model.terms.get(0).toString():
                                                            model.dName+"[,"+"\""+model.terms.get(0).toString()+"\""+"]");
                           }
                           vNameAdjusted =(model.dataClassInR.equals("data.frame")?"ts("+ vNameAddition + ")":vNameAddition); 
                               
                            }

                call="";       
                
                
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
			cancelClear = new JButton();
			bottomPanel.add(cancelClear, new AnchorConstraint(30, 980, 780, 539, 
			AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
			AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			cancelClear.setText(" Cancel ");
                        cancelClear.setFont(font12);
			cancelClear.setPreferredSize(new java.awt.Dimension(200, 35));
			cancelClear.addActionListener(generalListener);
                       }
                      
                      
                                                          {

                                                                GeneralPlotsPanel1 = new JPanel(); 
								generalTab.add(GeneralPlotsPanel1, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel1.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel1.setBorder(BorderFactory.createTitledBorder("Plot vs Time"));	
                                                                GeneralPlotsPanel1.setFont(font11);
                                                                GeneralPlotsPanel1.setVisible(true);

							
                                                                forecastFilterPlotPanel1 = new JPanel();
								generalTab.add(forecastFilterPlotPanel1, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot1Layout = new BorderLayout();
								forecastFilterPlotPanel1.setLayout(forecastFilterPlot1Layout);
								forecastFilterPlotPanel1.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                forecastFilterPlotPanel1.setFont(font11);
								forecastFilterPlotPanel1.setPreferredSize(new java.awt.Dimension(700, 675));
                                                                forecastFilterPlotPanel1.setVisible(false);
                                                                
                                                                
                                                                forecastFilterPlotPanel2 = new JPanel();
								generalTab.add(forecastFilterPlotPanel2, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot2Layout = new BorderLayout();
								forecastFilterPlotPanel2.setLayout(forecastFilterPlot2Layout);
								forecastFilterPlotPanel2.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                forecastFilterPlotPanel2.setFont(font11);
								forecastFilterPlotPanel2.setPreferredSize(new java.awt.Dimension(500, 475));
                                                                forecastFilterPlotPanel2.setVisible(false);
                                                                
                                                                forecastFilterPlotPanel3 = new JPanel();
								generalTab.add(forecastFilterPlotPanel3, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot3Layout = new BorderLayout();
								forecastFilterPlotPanel3.setLayout(forecastFilterPlot3Layout);
								forecastFilterPlotPanel3.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                forecastFilterPlotPanel3.setFont(font11);
								forecastFilterPlotPanel3.setPreferredSize(new java.awt.Dimension(500, 475));
                                                                forecastFilterPlotPanel3.setVisible(false);
                                                                
                                                                forecastFilterPlotPanel4 = new JPanel();
								generalTab.add(forecastFilterPlotPanel4, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot4Layout = new BorderLayout();
								forecastFilterPlotPanel4.setLayout(forecastFilterPlot4Layout);
								forecastFilterPlotPanel4.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                forecastFilterPlotPanel4.setFont(font11);
								forecastFilterPlotPanel4.setPreferredSize(new java.awt.Dimension(500, 475));
                                                                forecastFilterPlotPanel4.setVisible(false);  
                                                                
                                                                forecastFilterPlotPanel5 = new JPanel();
								generalTab.add(forecastFilterPlotPanel5, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot5Layout = new BorderLayout();
								forecastFilterPlotPanel5.setLayout(forecastFilterPlot5Layout);
								forecastFilterPlotPanel5.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                forecastFilterPlotPanel5.setFont(font11);
								forecastFilterPlotPanel5.setPreferredSize(new java.awt.Dimension(500, 475));
                                                                forecastFilterPlotPanel5.setVisible(false);  
                                                                                                                  
                                                                                                                    
                                                                filterTextPanel = new JPanel();
								generalTab.add(filterTextPanel, new AnchorConstraint(60, 800, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout filterTextPanelLayout = new BorderLayout();
								filterTextPanel.setLayout(filterTextPanelLayout);
                                                                filterTextPanel.setFont(font11);
								filterTextPanel.setBorder(BorderFactory.createTitledBorder("Results Area"));
								filterTextPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									filterTextScroller = new JScrollPane();
									filterTextPanel.add(filterTextScroller, BorderLayout.CENTER);
                                                                        
									{
										filterText = new JTextArea();
                                                                                filterText.setText("No Text given");
										filterTextScroller.setViewportView(filterText);
										filterText.setFont(new java.awt.Font("Monospaced",0,11));
										filterText.setEditable(false);
									}
								}
                                                                filterTextPanel.setVisible(false);
                                                          }        
                                                                  
                                                         {
                                                                filterLabel = new JLabel();
                                                                generalTab.add(filterLabel, new AnchorConstraint(100, 970, 117, 800, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                filterLabel.setText("Smoother,Filter,Forecast:");
                                                                filterLabel.setFont(font11);
                                                        	filterLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	filterLabel.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       }
                                                      {
                                                        	filtertype = new JComboBox();
                                                                generalTab.add(filtertype, new AnchorConstraint(130, 970, 117, 800, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                filtertype.setFont(font12);
                                                                filtertype.setModel(filtersExplorer);
                                                                filtertype.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                filtertype.addActionListener(generalListener);
                                                        }
                                                       {
                                                                resultsAreaViewLabel = new JLabel();
                                                                generalTab.add(resultsAreaViewLabel, new AnchorConstraint(200, 970, 117, 800, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                resultsAreaViewLabel.setText("Results Area View");
                                                                resultsAreaViewLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                                resultsAreaViewLabel.setFont(font11);
                                                        	resultsAreaViewLabel.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       }
                                                      {
                                                        	resultsAreaViewType = new JComboBox();
                                                                generalTab.add(resultsAreaViewType, new AnchorConstraint(230, 970, 117, 800, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                resultsAreaViewType.setFont(font12);
                                                                resultsAreaViewType.setModel(resultsAreaViewsExplorer);
                                                                resultsAreaViewType.setPreferredSize(new java.awt.Dimension(180, 21));
                                                                resultsAreaViewType.addActionListener(generalListener);
                                                                resultsAreaViewType.setEnabled(false);
                                                        } 
                                                      
                                                       {
                                                            editButton = new IconButton("/icons/edit_16.png","Plot Edit",generalListener,"Plot Edit");
                                                                editButton.setContentAreaFilled(false);
                                                                generalTab.add(editButton, new AnchorConstraint(840, 970, 117, 930, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                editButton.setPreferredSize(new java.awt.Dimension(21, 21));
                                                        } 
                                                      
                                                         {
								idLabelButton = new JButton();
								generalTab.add(idLabelButton, new AnchorConstraint(890, 970, 117, 895, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
								idLabelButton.setText("id-label");
                                                                idLabelButton.setFont(font12);
								idLabelButton.setPreferredSize(new java.awt.Dimension(110, 26));
								idLabelButton.addActionListener(generalListener);
                                                                idLabelButton.setVisible(true);
							}   
                                                             {
								exportButton = new JButton();
								generalTab.add(exportButton, new AnchorConstraint(950, 995, 980, 776,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								exportButton.setText("Export");
                                                                exportButton.setFont(font12);
								exportButton.setPreferredSize(new java.awt.Dimension(80, 35));
								exportButton.addActionListener(generalListener);
                                                             } 
                                                          
                                                        
                        
                        if (model.terms.getSize()>1) {
                           for(int i=1;i<model.terms.getSize();i++){
                                analysisVariablesList = analysisVariablesList + ",\""+ model.terms.get(i).toString()+"\"";
                                analysisVariables = analysisVariables + ","+ model.terms.get(i).toString();
                                if (variableTransformation) {
                                    
                                   String vNameAddition = new String(model.terms.get(i).toString());
                                   if (vNameAddition.contains("(")||vNameAddition.contains("*")||vNameAddition.contains("-") ){
                                     for(int j=0;j<availableVariables.length;j++){
                                                              vNameAddition= vNameAddition.replace(availableVariables[j], model.dName+(colnameIsNull?"":"[,"+"\""+availableVariables[j]+"\""+"]"));
                                                            }
                                   } else {
                                     vNameAddition = 
                                     (colnameIsNull?model.terms.get(i).toString():
                                                    model.dName+"[,"+"\""+model.terms.get(i).toString()+"\""+"]");
                                   }
                                   vNameAdjusted =vNameAdjusted + ","+ (model.dataClassInR.equals("data.frame")?"ts("+ vNameAddition + ")":vNameAddition); 

                                }
                            }
                            
                            if (model.dataClassInR.equals("zoo")) {
                            call="\n EFEUTSExplorer_temp <- merge.zoo("+analysisVariables+")\n colnames(EFEUTSExplorer_temp)<-c("+
                                     (modelTSdata?model.efeglmextra.TSlabels:analysisVariablesList)+")";
                            Deducer.eval(call);
                            }
                        }

                           vNameAdjustedZoo = new String();
                           vNameAdjustedZoo = vName; 
                           if (!modelTSdata) { /*we already know start and frequency with modelTSdata input*/
                               if (model.dataClassInR.equals("zoo")||model.dataClassInR.equals("xts")||model.dataClassInR.equals("zooreg")||model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts")){
                                   existsTS = true;
                                       try {
                                        model.start_of_original_data  = Deducer.eval("as.character(start("+model.dName+")[1])").asString();
                                        }catch(Exception e){
                                        new ErrorMsg(e);
                                        }
                                   try {
                                    model.frequency_of_original_data  = Deducer.eval("as.character(frequency("+model.dName+")[1])").asString(); 
                                    }catch(Exception e){
                                    new ErrorMsg(e);
                                    }
                               } else 
                                   existsTS = false;
                           } 

                            Boolean obsFound = (model.start.contains("obs")||model.end.contains("obs"));              
                            String asTime ="";
                            if (existsTS) {
                                if (model.frequency_of_original_data.equals("4"))
                                    asTime = "as.yearqtr";
                                else if (model.frequency_of_original_data.equals("12"))
                                    asTime = "as.yearmon";
                                else 
                                    asTime = "as.Date";
                            }
                           if ((model.dataClassInR.equals("zoo")||model.dataClassInR.equals("xts"))&(!modelTSdata)){
                               if (variableTransformation) {
                                  if (model.terms.getSize()>1){
                                        vNameAdjusted = "merge("+ vNameAdjusted+")";
                                  }
                                 } else {  
                               
                                   if (model.terms.getSize()>1) {
                                      vNameAdjusted= model.dName+"[,c("+analysisVariablesList+")]";
                                   } else  {
                                      vNameAdjusted= model.dName+(multivariableData?"$"+vName:"");  
                                      } 
                                }
                               if (!(model.start.equals("")&model.end.equals(""))){
                                   vNameAdjusted = "window("+vNameAdjusted+(model.start.equals("")?"":",start="+asTime+"(\""+model.start+"\")")+
                                                                           (model.end.equals("")?"":",end="+asTime+"(\""+model.end+"\")")+")";};
                               vNameAdjustedZoo = vNameAdjusted;
                          } else if (model.dataClassInR.equals("zooreg")||(model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts"))&(!modelTSdata)) {
                             if (variableTransformation) {
                                  if (model.terms.getSize()>1){
                                        vNameAdjusted = "ts.union("+ vNameAdjusted+")";
                                  }
                                 } else {       
                                       vNameAdjusted= (colnameIsNull?analysisVariables:model.dName+"[,"+(model.terms.getSize()>1?"c("+analysisVariablesList+")":"\""+vName+"\"")+"]");
                                 }
                             if (!(model.start.equals("")&model.end.equals(""))){
                              vNameAdjusted = "window("+vNameAdjusted+(model.start.equals("")?"":",start=c("+model.start.replace(":",",").replace("-",",")+")")+
                                                                      (model.end.equals("")?"":",end=c("+model.end.replace(":",",")+")").replace("-",",")+")";};
                                   vNameAdjustedZoo = "zoo("+vNameAdjusted+")";
                                   
                             
                           }else if (modelTSdata&model.data.toString().contains("HanselAddOns")&!model.panelModel){
                               vNameAdjusted= model.dName + "$" + vName;
                           } else {
                               if (variableTransformation) {
                                  if (model.terms.getSize()>1){
                                        vNameAdjusted = "ts.union("+ vNameAdjusted+")";
                                  }
                                 } else {       
                                       vNameAdjusted= model.dName+(colnameIsNull?"":"[,"+(model.terms.getSize()>1?"c("+analysisVariablesList+")":"\""+vName+"\"")+"]");
                               }
                               if (model.dName.contains("data.frame("))
                                  vNameAdjusted= model.dName;
                               else if (model.terms.getSize()>1) { 
                                   if (model.panelModel)
                                       vNameAdjusted= "with("+model.dName+",cbind("+analysisVariables+"))";
                               } else {
                                   if (analysisVariablesList.contains("resid("))
                                       vNameAdjusted= analysisVariables;
                                   else if (model.panelModel)
                                       vNameAdjusted= model.data+"$"+analysisVariables;
                               }


                                   if (modelTSdata) {
                                       vNameAdjusted = vNameAdjusted+
                                                      (model.start_of_original_data.equals("1")&model.frequency_of_original_data.equals("1")?
                                                          ""
                                                        :",start="+model.start_of_original_data+",frequency="+model.frequency_of_original_data)
                                                       ;
                                   } else{ 
                                       if (!(model.start.equals("")&model.end.equals(""))){
                                         vNameAdjusted = "window("+vNameAdjusted+(model.start.equals("")?"":",start="+model.start)+
                                                                              (model.end.equals("")?"":",end="+model.end)+")";

                                       }

                                   }
                                   if ((vNameAdjusted.contains("resid(")||
                                           (!(vNameAdjusted.contains("ts(")||
                                          model.dataClassInR.equals("zooreg")||
                                          model.dataClassInR.equals("ts")||
                                          model.dataClassInR.equals("mts")))
                                           )&!model.panelModel)
                                             vNameAdjusted = "ts("+vNameAdjusted+")"; 

                                   vNameAdjustedZoo = "zoo("+vNameAdjusted+")";
                           }      
                   if (model.terms.getSize()==1){
                       String startNA = "";
                       String endNA = "";
                       
                       try {
                            startNA  = Deducer.eval("is.na("+vNameAdjusted+")[1]").asString();
                            endNA  = Deducer.eval("is.na("+vNameAdjusted+")[length("+vNameAdjusted+")]").asString();
                            nullDimension  = Deducer.eval("is.null(dim("+vNameAdjusted+"))").asString().equals("TRUE");
                            nullColNames  = Deducer.eval("is.null(colnames("+vNameAdjusted+"))" ).asString().equals("TRUE");
                            }catch(Exception e){
                            new ErrorMsg(e);
                            }
                       if (startNA.equals("TRUE")||endNA.equals("TRUE")){
                               if (model.dataClassInR.equals("xts")||model.dataClassInR.equals("zoo")||model.dataClassInR.equals("zooreg"))
                                 vNameAdjusted= "na.trim("+vNameAdjusted+")";
                               else {
                                 vNameAdjusted= "na.trim(zoo("+vNameAdjusted+"))"; 
                               }
                          }
                     if (!existsTS&!(vNameAdjusted.contains("ts(")||vNameAdjusted.contains("zoo("))) {
                         nameAdjustedForTS= "as.ts("+vNameAdjusted+")";
                         nullDimension = true;
                         nullColNames =true;
                     }
                     else
                         nameAdjustedForTS = vNameAdjusted;
                       
                   }
                    vNameOrTSLabel = (modelTSdata?model.efeglmextra.TSlabels:vName);
                    vNameSimplifiedCharacters = vNameOrTSLabel.replace(" ","_").replace("-","_").replace("(","_").replace(")","").replace("^","_pwr").replace(":","").replace("\"", "");       
                           
                           
                           
                           
                         if (existsTS){
                             if (model.frequency_of_original_data.equals("12"))
                                model.rowNamesOrDatesLabel = "as.yearmon(time("+vNameAdjusted+"))"; 
                             else if (model.frequency_of_original_data.equals("4"))
                                model.rowNamesOrDatesLabel = "as.yearqtr(time("+vNameAdjusted+"))";
                             else 
                                 model.rowNamesOrDatesLabel = "time("+vNameAdjusted+")";
                        }else {
                                 model.rowNamesOrDatesLabel = "time("+(vNameAdjusted.contains("ts(")?vNameAdjusted.replace("ts(","").replace("$"+vName,""):vNameAdjusted+")");
                        } 
                         
                         
                         if (existsTS&!model.start.equals("")){ /*There should be a more elegant way of handling this.*/
                                   try {
                                    model.start_of_original_data  = Deducer.eval("as.character(start("+vNameAdjusted+")[1])").asString();
                                    }catch(Exception e){
                                    new ErrorMsg(e);
                                    }
                                }

                                /* Note: the need to include a print statement here is an oddity of ggplot plot statement interacting
                                   with Deducer statements. Apparently in Deducer's DeviceInterface.java, which is the analogue of
                                  the ModelPlotPanel.java used for ggplot statements, the print statement is used which is not in
                                  ModelPlotPanel.java.
                                   */
                            if (modelTSdata&model.data.toString().contains("HanselAddOns")&!model.panelModel)
                                vNameAdjusted = vNameAdjusted+model.efeglmextra.TSMainTitle;
                                model.plotControl.traditionalGraphicsAvailable[1] = true;
                                model.plotControl.latticeGraphicsAvailable[1] = true;
                                
                                if(model.dataClassInR.equals("zoo")||model.dataClassInR.equals("xts"))
                                    model.plotControl.traditionalPlotFormula[1] = "as.ts("+vName +")";
                                else
                                    model.plotControl.traditionalPlotFormula[1] = vNameAdjusted;
                                model.plotControl.preCommand[1] = "";
                                if (model.terms.getSize()>1){
                                    model.plotControl.singleGraphMulti[1] = false;
                                    model.plotControl.singleGraphMultiShow[1] = true;
                                } else {
                                    model.plotControl.singleGraphMulti[1] = null;
                                    model.plotControl.singleGraphMultiShow[1] = true;
                                }
                                if (model.terms.getSize()>1 & variableTransformation) {
                                    model.plotControl.preCommand[1] = "temp_MTS_Data <-"+ vNameAdjusted+"\n"+
                                                   "colnames(temp_MTS_Data) <- c("+analysisVariablesList+")"+"\n";
                                    model.plotControl.traditionalPlotBeginning[1] = "plot(temp_MTS_Data";
                                    model.plotControl.latticePlotBeginning[1] = "xyplot(temp_MTS_Data";
                                    model.plotControl.postCommand[1] = "\nrm(temp_MTS_Data)";
                                } else {
                                    model.plotControl.traditionalPlotBeginning[1] = "plot("+vNameAdjusted;  
                                    model.plotControl.latticePlotBeginning[1] = "xyplot("+vNameAdjusted;  
                                }
                               
                                
                                model.plotControl.mainTitle[1] = model.efeglmextra.TSMainTitle;
                                model.plotControl.showTickMarks[1] = true;
                                model.plotControl.showGrid[1] = true;
                                model.plotControl.singleGraphMulti[1] = false;
                                model.plotControl.yAxisTitle[1] = new String(model.efeglmextra.TSlabels).replace("\"", "");
                                if (singleTimeSeries){
                                  model.plotControl.pointLabels[1] = "row names";
                                  model.plotControl.whichPointsToLabel[1] = "none";
                                  model.plotControl.numExtremeValues[1] = "1";
                                  model.plotControl.extremenessBasis[1] = "|y – mean(y)|";
                                  model.plotControl.extremenessScrollChoices[1] = "1";
                                  model.plotControl.mostExtremeEnabled[1] = true;
                                  model.plotControl.timeOrObsHorizontalAxis[1] = true;
                                    yLabelDataPanel1=(modelTSdata?model.efeglmextra.TSMainTitle+ model.efeglmextra.TSlabels:
                                      vName.toString());
                                    model.plotControl.yAxisTitle[1] = yLabelDataPanel1;
                                    
                                    model.plotControl.plotType1[1] = "l: line";
                                    model.plotControl.lineType1[1] = "solid";
                                    model.plotControl.linePoints1linewidth[1]="1";
                                    model.plotControl.linePoints1color[1] = "black";
                                    model.plotControl.linePoints1Symbol[1]="";
                                    model.plotControl.legendText2[1] = "fitted";
                                    model.plotControl.linePoints2linewidth[1]="";
                                    model.plotControl.linePoints2color[1] = "";  
                                    
                                } else {
                                    model.plotControl.keyPosition[1] = "top";
                                    model.plotControl.lineType1[1] = "solid";
                                    model.plotControl.linePoints1linewidth[1] = "1";
                                    model.plotControl.linePoints1color[1] = "black";
                                    model.plotControl.lineType2[1] = "dashed";
                                    model.plotControl.linePoints2linewidth[1] = "1";
                                    model.plotControl.linePoints2color[1] = "blue";
                                    if (model.terms.getSize()>=3) {
                                    model.plotControl.lineType3[1] = "dotdash";
                                    model.plotControl.linePoints3linewidth[1] = "1";
                                    model.plotControl.linePoints3color[1] = "green";
                                    }
                                    if (model.terms.getSize()>=4) {
                                    model.plotControl.lineType4[1] = "longdash";
                                    model.plotControl.linePoints4linewidth[1] = "1";
                                    model.plotControl.linePoints4color[1] = "purple";
                                    } 
                                    if (model.terms.getSize()>=5) {
                                    model.plotControl.lineType5[1] = "twodash";
                                    model.plotControl.linePoints5linewidth[1] = "1";
                                    model.plotControl.linePoints5color[1] = "brown";
                                    } 
                                    if (model.terms.getSize()>=6) {
                                    model.plotControl.lineType6[1] = "dotted";
                                    model.plotControl.linePoints6linewidth[1] = "1";
                                    model.plotControl.linePoints6color[1] = "red";
                                    } 
                                }
                               
                                if ((model.data.contains("@R0")||model.data.contains("@RK")||model.data.contains("@res")
                                    ||model.efeglmextra.TSMainTitle.equals("Cointegrating Relationship")||model.dName.contains("data.frame("))&model.terms.getSize()>1) {
                                    model.plotControl.preCommand[1] = "GMExplorer_temp <-"+vNameAdjusted+
                                                                      "\ncolnames(GMExplorer_temp)<-c(\""+model.efeglmextra.TSlabels.replace(",","\",\"")+"\")";
                                    model.plotControl.traditionalPlotBeginning[1] = "plot(GMExplorer_temp";
                                    model.plotControl.latticePlotBeginning[1] = "xyplot(GMExplorer_temp";
                                    model.plotControl.postCommand[1] = "\nrm(GMExplorer_temp)";
                                    model.plotControl.yAxisTitle[1] = "";
                                            }   
                                if (model.plotControl.mainTitle[1].contains("Cointegrating"))
                                        model.plotControl.yAxisTitle[1] = "  ";
                                if (model.terms.getSize()>1&modelTSdata)
                                    model.plotControl.yAxisTitle[1] = "";
                                model.plotControl.manualyAxisRange[1]=false;
                                model.plotControl.xAxisTitle[1] = (existsTS?"":"observation");
                                model.plotControl.manualxAxisRange[1]=false;
                                model.plotControl.legendText1[1] = (modelTSdata?model.efeglmextra.TSMainTitle+ model.efeglmextra.TSlabels:
                                 model.terms.get(0).toString());  
                                
                                if (model.terms.getSize()>=2)
                                    model.plotControl.legendText2[1] = model.terms.get(1).toString();
                                if (model.terms.getSize()>=3)
                                    model.plotControl.legendText3[1] = model.terms.get(2).toString();
                                if (model.terms.getSize()>=4)
                                    model.plotControl.legendText4[1] = model.terms.get(3).toString();
                                if (model.terms.getSize()>=5)
                                    model.plotControl.legendText5[1] = model.terms.get(4).toString();
                                if (model.terms.getSize()>=6)
                                    model.plotControl.legendText6[1] = model.terms.get(5).toString();
                               if (!singleTimeSeries) {

                                 model.plotControl.graphicSystem[1] = "lattice";
                                   call = model.plotControl.latticePlotBeginning[1]+
                                    (model.plotControl.mainTitle[1].equals("")?"":",main=\""+ model.plotControl.mainTitle[1]+"\"")+
                                     (model.plotControl.yAxisTitle[1].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[1]+"\"")+
                                      ",xlab=\""+ model.plotControl.xAxisTitle[1]+"\""+
                                    ",grid=TRUE)";
                               } else {
                                  model.plotControl.graphicSystem[1] = "traditional graphics";
                                  call =  (model.dataClassInR.equals("zoo")||model.dataClassInR.equals("zooreg")||model.dataClassInR.equals("xts")?
                                                    model.plotControl.traditionalPlotBeginning[1].replace("plot","plot.zoo")
                                                     :
                                                    model.plotControl.traditionalPlotBeginning[1]
                                                    )
                                          +",col=\""+model.plotControl.linePoints1color[1]+"\""+
                                    (model.plotControl.mainTitle[1].equals("")?"":",main=\""+ model.plotControl.mainTitle[1]+"\"")+
                                    (model.plotControl.yAxisTitle[1].equals("")?"":",ylab=\""+ model.plotControl.yAxisTitle[1]+"\"")+
                                    ",xlab=\""+ model.plotControl.xAxisTitle[1]+"\""+
                                    ")\ngrid(lwd = 2)";
                               }

                               plotType = "plotvstime single";                        
                            callCommandsLog += "\n\n#The following commands result in the output found in the General tab:\n";
                            callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                            callCommandsLog += 
                                           (model.plotControl.preCommand[1].equals("")?"":model.plotControl.preCommand[1]+"\n")+
                                            call+
                                            (model.plotControl.postCommand[1].equals("")?"":model.plotControl.postCommand[1])
                                            ;
                          if (!model.plotControl.preCommand[1].equals(""))
                              Deducer.eval(model.plotControl.preCommand[1]);
                          
                          addedTab = new ModelPlotPanel((model.plotControl.graphicSystem[1].equals("lattice")?"print("+call+")":call));
                          
                          if (!model.plotControl.postCommand[1].equals(""))
                              Deducer.eval(model.plotControl.postCommand[1]);    
                          
                        GeneralPlotsPanel1.add(addedTab);
                        Deducer.eval(plotPanelsDevNumsName+"[1] <- dev.cur()");
                        seenPlotPanels[1] = true;
                                                       {

                                                                GeneralPlotsPanel2 = new JPanel();
								generalTab.add(GeneralPlotsPanel2, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel2.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel2.setBorder(BorderFactory.createTitledBorder("Distribution"));	
                                                                GeneralPlotsPanel2.setFont(font11);
                                                                GeneralPlotsPanel2.setVisible(false);
							}
                                                        {

                                                                GeneralPlotsPanel3 = new JPanel();
								generalTab.add(GeneralPlotsPanel3, new AnchorConstraint(60, 950, 535, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel3.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel3.setFont(font11);
								GeneralPlotsPanel3.setBorder(BorderFactory.createTitledBorder("Lag relationships for " + vNameOrTSLabel));							
                                                                GeneralPlotsPanel3.setVisible(false);
							}
                                                        
                                                      
                                                        
                                                        {

                                                                GeneralPlotsPanel4 = new JPanel();
								generalTab.add(GeneralPlotsPanel4, new AnchorConstraint(545, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel4.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel4.setFont(font11);
								GeneralPlotsPanel4.setBorder(BorderFactory.createTitledBorder("Lag relationships for the square of "+ vNameOrTSLabel));							
                                                                GeneralPlotsPanel4.setVisible(false);
							}
                                                        {

                                                                GeneralPlotsPanel5 = new JPanel();
								generalTab.add(GeneralPlotsPanel5, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel5.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel5.setFont(font11);
                                                                GeneralPlotsPanel5.setBorder(BorderFactory.createTitledBorder("Plot vs Lags"));							
                                                                GeneralPlotsPanel5.setVisible(false);
							}
                                                        {

                                                                GeneralPlotsPanel6 = new JPanel();
								generalTab.add(GeneralPlotsPanel6, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel6.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel6.setFont(font11);
                                                                GeneralPlotsPanel6.setBorder(BorderFactory.createTitledBorder("Structural Breaks"));							
                                                                GeneralPlotsPanel6.setVisible(false);
							}

                                                        {
								plotvsTimeButton = new JButton();
								generalTab.add(plotvsTimeButton, new AnchorConstraint(10, 140, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotvsTimeButton.setText("Plot vs time");
								plotvsTimeButton.setFont(font12);
                                                                plotvsTimeButton.setPreferredSize(new java.awt.Dimension(90, 26));
								plotvsTimeButton.addActionListener(generalListener);
                                                                plotvsTimeButton.setSelected(true);
							}   
                                                        
                                                        {
								distributionButton = new JButton();
								generalTab.add(distributionButton, new AnchorConstraint(10, 350, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								distributionButton.setText("Distribution");
                                                                distributionButton.setFont(font12);
								distributionButton.setPreferredSize(new java.awt.Dimension(100, 26));
								distributionButton.addActionListener(generalListener);
							}  
                                                        {
								correlogramButton = new JButton();
								generalTab.add(correlogramButton, new AnchorConstraint(10, 580, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								correlogramButton.setText("Correlograms");
                                                                correlogramButton.setFont(font12);
								correlogramButton.setPreferredSize(new java.awt.Dimension(100, 26));
								correlogramButton.addActionListener(generalListener);
							}  
                
                
                                                        {
								plotvsLagsButton = new JButton();
								generalTab.add(plotvsLagsButton, new AnchorConstraint(10, 780, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotvsLagsButton.setText("Plot vs Lags");
                                                                plotvsLagsButton.setFont(font12);
								plotvsLagsButton.setPreferredSize(new java.awt.Dimension(100, 26));
								plotvsLagsButton.addActionListener(generalListener);
							}  
        
        
                                                        {
								structuralBreaksButton = new JButton();
								generalTab.add(structuralBreaksButton, new AnchorConstraint(10, 980, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								structuralBreaksButton.setText("Other Plots");
                                                                structuralBreaksButton.setFont(font12);
								structuralBreaksButton.setPreferredSize(new java.awt.Dimension(130, 26));
								structuralBreaksButton.addActionListener(generalListener);
							}  

                           AnchorLayout generalTabLayout = new AnchorLayout();                   

                     if (singleTimeSeries){   
                        unitRootTestTab = new JPanel();
			tabs.addTab("Unit Root Tests", null, unitRootTestTab, null);
			unitRootTestTab.setLayout(generalTabLayout);
			unitRootTestTab.setPreferredSize(new java.awt.Dimension(695, 475));
                        {
								unitRootTextPanel = new JPanel();
								unitRootTestTab.add(unitRootTextPanel, new AnchorConstraint(10, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								unitRootTextPanel.setLayout(previewPanelLayout);
								unitRootTextPanel.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                unitRootTextPanel.setFont(font11);
								unitRootTextPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									unitRootTextScroller = new JScrollPane();
									unitRootTextPanel.add(unitRootTextScroller, BorderLayout.CENTER);
                                                                        
									{
										unitRootText = new JTextArea();
                                                                                unitRootText.setText("Please Choose a test to the right.");
										unitRootTextScroller.setViewportView(unitRootText);
										unitRootText.setFont(new java.awt.Font("Monospaced",0,11));
										unitRootText.setEditable(false);
									}
								}
                                                                unitRootPlotPanel = new JPanel();
								unitRootTestTab.add(unitRootPlotPanel, new AnchorConstraint(10, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout forecastFilterPlot1Layout = new BorderLayout();
								unitRootPlotPanel.setLayout(forecastFilterPlot1Layout);
								unitRootPlotPanel.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                unitRootPlotPanel.setFont(font11);
								unitRootPlotPanel.setPreferredSize(new java.awt.Dimension(700, 675));
                                                              
                                                                unitRootPlotPanel.setVisible(false);
                                                              {
								unitRootTextPlot = new JButton();
								unitRootTestTab.add(unitRootTextPlot, new AnchorConstraint(800, 995, 980, 776,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								unitRootTextPlot.setText("Text/Plot Toggle");
                                                                unitRootTextPlot.setFont(font12);
								unitRootTextPlot.setPreferredSize(new java.awt.Dimension(144, 35));
								unitRootTextPlot.addActionListener(generalListener);
                                                              }
                        }                                     
                                                              
                        structuralBreakTestTab = new JPanel();
			tabs.addTab("Structural Breaks Tests", null, structuralBreakTestTab, null);
			structuralBreakTestTab.setLayout(generalTabLayout);
			structuralBreakTestTab.setPreferredSize(new java.awt.Dimension(695, 475));
                        {
								structuralBreakTextPanel = new JPanel();
								structuralBreakTestTab.add(structuralBreakTextPanel, new AnchorConstraint(10, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								structuralBreakTextPanel.setLayout(previewPanelLayout);
								structuralBreakTextPanel.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                structuralBreakTextPanel.setFont(font11);
								structuralBreakTextPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									structuralBreakTextScroller = new JScrollPane();
									structuralBreakTextPanel.add(structuralBreakTextScroller, BorderLayout.CENTER);
                                                                        
									{
										structuralBreakText = new JTextArea();
                                                                                structuralBreakText.setText("Please Choose a test to the right.");
										structuralBreakTextScroller.setViewportView(structuralBreakText);
										structuralBreakText.setFont(new java.awt.Font("Monospaced",0,11));
										structuralBreakText.setEditable(false);
									}
								}
                                                                structuralBreakPlotPanel = new JPanel();
								structuralBreakTestTab.add(structuralBreakPlotPanel, new AnchorConstraint(10, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout structuralBreakPlotLayout = new BorderLayout();
								structuralBreakPlotPanel.setLayout(structuralBreakPlotLayout);
								structuralBreakPlotPanel.setBorder(BorderFactory.createTitledBorder("Results Area"));
                                                                structuralBreakPlotPanel.setFont(font11);
								structuralBreakPlotPanel.setPreferredSize(new java.awt.Dimension(700, 675));
                                                              
                                                                structuralBreakPlotPanel.setVisible(false);
                                                              {
								structuralBreakTextPlot = new JButton();
								structuralBreakTestTab.add(structuralBreakTextPlot, new AnchorConstraint(800, 995, 980, 776,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								structuralBreakTextPlot.setText("Text/Plot Toggle ");
                                                                structuralBreakTextPlot.setFont(font12);
								structuralBreakTextPlot.setPreferredSize(new java.awt.Dimension(144, 35));
								structuralBreakTextPlot.addActionListener(generalListener);
                                                              }                                     
                                                              
                        }       
							
                     } else { // not single series 
                          plotvsTimeButton.setEnabled(false);
                          distributionButton.setVisible(false);
                          correlogramButton.setVisible(false);
                          plotvsLagsButton.setVisible(false);
                          structuralBreaksButton.setVisible(false);
                          filterLabel.setVisible(false);
                          filtertype.setVisible(false);
                          resultsAreaViewLabel.setVisible(false);
                          resultsAreaViewType.setVisible(false);
                          idLabelButton.setEnabled(false);
                          exportButton.setEnabled(false);
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
                                                                                commandsLogText.setText("");
                                                                                commandsLogText.setText(callCommandsLog);
                                                                                commandsLogText.setCaretPosition(0);
										commandsLogScroller.setViewportView(commandsLogText);
										commandsLogText.setFont(new java.awt.Font("Monospaced",0,11));
										commandsLogText.setEditable(false);
									}
								}
							}
                                      
                        

                        
                        {
			adf = new JButton();
			unitRootTestTab.add(adf, new AnchorConstraint(50, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			adf.setText("Augmented Dickey-Fuller");
                        adf.setFont(font12);
			adf.setPreferredSize(new java.awt.Dimension(160, 35));
			adf.addActionListener(generalListener);
			}

                        {
			adfGLS = new JButton();
			unitRootTestTab.add(adfGLS, new AnchorConstraint(120, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			adfGLS.setText("ADF-GLS");
                        adfGLS.setFont(font12);
			adfGLS.setPreferredSize(new java.awt.Dimension(160, 35));
			adfGLS.addActionListener(generalListener);
			}
                        {
			kpss = new JButton();
			unitRootTestTab.add(kpss, new AnchorConstraint(190, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			kpss.setText("KPSS");
                        kpss.setFont(font12);
			kpss.setPreferredSize(new java.awt.Dimension(160, 35));
			kpss.addActionListener(generalListener);
			}
                        {
			philperr = new JButton();
			unitRootTestTab.add(philperr , new AnchorConstraint(260, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			philperr.setText("Phillips and Perron");
                        philperr.setFont(font12);
			philperr.setPreferredSize(new java.awt.Dimension(160, 35));
			philperr.addActionListener(generalListener);
			}
                        {
			SchmidtPhillips = new JButton();
			unitRootTestTab.add(SchmidtPhillips , new AnchorConstraint(330, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			SchmidtPhillips.setText("Schmidt and Phillips");
                        SchmidtPhillips.setFont(font12);
			SchmidtPhillips.setPreferredSize(new java.awt.Dimension(160, 35));
			SchmidtPhillips.addActionListener(generalListener);
			}
                        {
			zivotAndrews = new JButton();
			unitRootTestTab.add(zivotAndrews , new AnchorConstraint(400, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			zivotAndrews.setText("Zivot and Andrews");
                        zivotAndrews.setFont(font12);
			zivotAndrews.setPreferredSize(new java.awt.Dimension(160, 35));
			zivotAndrews.addActionListener(generalListener);
			}
                        
                        
                        
                        {
			gefpMTest = new JButton();
			structuralBreakTestTab.add(gefpMTest, new AnchorConstraint(50, 995, 980, 776,
					AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
					AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			gefpMTest.setText("Gen. Emp. fluct: M-test");
                        gefpMTest.setFont(font12);
			gefpMTest.setPreferredSize(new java.awt.Dimension(160, 35));
			gefpMTest.addActionListener(generalListener);
			}
                     
		} catch(Exception e){
			new ErrorMsg(e);
		}
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
	
	public void setModel(GMModel mod){
		model = mod;
                
                String existsHanselWorkingEnv = new String();
                try {
                    existsHanselWorkingEnv = Deducer.eval("as.character(exists(\".hansel.working.env\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                if (existsHanselWorkingEnv.equals("TRUE")) {
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsTS";
                }  else {
                    Deducer.eval(Hansel.hanselEnv+"<- new.env(parent=emptyenv())\n");
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsTS";
                }   
                        
                
                Deducer.eval("library(forecast)\n library(urca)\n library(mFilter)");
                Deducer.eval(plotPanelsDevNumsName+" <-c(rep(\"\",13))");
                
                
	}

        public void arimaGarchPlotandText(GMModel mod){
		model = mod;
                String aeCall;
                String urTestText;
                String[] out = new String[]{};
                ArrayList tmp = new ArrayList();
                String timeSeriesObserved = (existsTS||vNameAdjusted.contains("ts(")?vNameAdjusted:"as.ts("+vNameAdjusted+")");
                String filterCall = "";
                String filterTextOutput = "";
                String fitFilterCall = "";
                String fitDeterminationCall = "";
                String centreSimpleMAText = "";
                String polynFourierReg = "";
                
               filterText.removeAll();

               
               filterOutput = ".hansel.working.env$"+Deducer.getUniqueName("filterOutput");
               filterFittedValues = ".hansel.working.env$"+Deducer.getUniqueName("filterFittedValues");
               decompTemp = ".hansel.working.env$"+Deducer.getUniqueName("decompTemp");
               
               morePlotscall1 = "";
               morePlotscall2 = ""; 
               
                if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Simple"))) {
                         if (model.filterOptions.centreSimpleMA.TRUE) 
                              centreSimpleMAText = "TRUE"; 
                         else 
                              centreSimpleMAText = "FALSE";
                         String temp = Deducer.getUniqueName("tmp");
                         filterCall= filterOutput + " <- ma("+timeSeriesObserved+",order=as.numeric("+model.filterOptions.simpleMAOrder+
                                                     "),centre="+ centreSimpleMAText  +")";                        
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = ""+timeSeriesObserved+"-"+ filterOutput;
                         fitDeterminationCall = filterFittedValues + " <- "+filterOutput; 
  
                        plotType = "simple ma";
                        model.plotControl.mainTitle[1] = "Given Series with Simple Moving Average";
                        dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));               
                                
                         fitFilterCall = model.plotControl.latticePlotBeginning[1] +
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",    lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",    lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\""+model.plotControl.keyPosition[1]+"\",columns=2))";  
                                
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+",filterFittedValues), superpose=TRUE"+ 
                                      ",    main= \""+mainTitleFitPanel1+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",    lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",    lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  
                         
                         
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterFittedValues+","+filterOutputResiduals+")\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+",xlab=\"\")";
                      
               } else if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Additive Seasonal"))) {                     
                          filterCall=  filterOutput +" <- decompose(as.ts("+timeSeriesObserved+"),type=\"additive\")"; 
                          filterTextOutput = "summary("+filterOutput+")";
                          filterOutputResiduals = filterOutput+"$random";
                          fitDeterminationCall = filterFittedValues+" <-as.ts("+timeSeriesObserved+")-" + filterOutputResiduals;
                          plotType = "additive seasonal ma";
                          model.plotControl.mainTitle[1] = "Given Series with Moving Average";
                          dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+mainTitleFitPanel1+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",  lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",  grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",space=\"bottom\",columns=2))";  

                          decompositionCall = "plot(filterOutput,col=\"blue\")";
                          
               } else if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Multiplicative Seasonal"))) {
                          filterCall= filterOutput +" <- decompose(as.ts("+timeSeriesObserved+"),type=\"multiplicative\")"; 
                          filterTextOutput = "summary("+filterOutput+")";
                          filterOutputResiduals = filterOutput+"$random";
                          fitDeterminationCall = filterFittedValues+" <- "+filterOutput+"$trend*"+filterOutput+"$seasonal";
                          plotType = "multiplicative seasonal ma";
                          model.plotControl.mainTitle[1] = "Given Series with Moving Average";
                          dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall= "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+mainTitleFitPanel1+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",  lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  

                          decompositionCall = "plot("+filterOutput+",col=\"blue\")";                       
                                                   
               } else if ((specificMethod=="Polynomial, Fourier")) {        
                   polynFourierReg = "lm("+timeSeriesObserved +"~";
                   for(int x = 1; x <= Integer.parseInt(model.filterOptions.polynomialOrder); x = x+1){
                       polynFourierReg = polynFourierReg + "I(time("+
                               (model.dataClassInR.equals("ts")?vNameAdjusted:"ts("+vNameAdjusted+")")
                                + ")^"+ x + ")";
                       if (x<=(Integer.parseInt(model.filterOptions.polynomialOrder)-1)) 
                           polynFourierReg = polynFourierReg + "+";
                    } 
                   if (Integer.parseInt(model.filterOptions.fourierOrder) > 0) {
                      if (Integer.parseInt(model.filterOptions.polynomialOrder) > 0);
                         polynFourierReg = polynFourierReg;
                         
                       String periodicity;
                       if (model.filterOptions.fourierPeriodicity.contains("length of data"))
                          periodicity = model.filterOptions.fourierPeriodicity.replace("length of data","length("+vNameAdjusted+")");
                       else
                          periodicity = model.filterOptions.fourierPeriodicity;
                       
                       for(int x = 1; x <= Integer.parseInt(model.filterOptions.fourierOrder); x = x+1){
                       polynFourierReg = polynFourierReg + "+sin(("+x+"*2*pi/("+periodicity+"))*time("+
                                                          (model.dataClassInR.equals("ts")?vNameAdjusted:"ts("+vNameAdjusted+")") +"))"
                                                         + "+cos(("+x+"*2*pi/("+periodicity+"))*time("+
                                                          (model.dataClassInR.equals("ts")?vNameAdjusted:"ts("+vNameAdjusted+")")+"))\n";   
                    }  
                      }   
                   polynFourierReg = polynFourierReg + ", na.action=NULL)";
                         filterCall = filterOutput +" <- " + polynFourierReg; 
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = filterOutput +"$residuals";
                         fitDeterminationCall = filterFittedValues+" <- "+filterOutput+"$fit";
                         
                         plotType = "polyn Fourier";
                          model.plotControl.mainTitle[1] = "Given Series with"+
                          (Integer.parseInt(model.filterOptions.polynomialOrder) > 0?" Polynomial":"")+
                          (Integer.parseInt(model.filterOptions.fourierOrder)> 0?" Fourier":"")+ " Fit";
                          dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",  lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  

                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterFittedValues+","+filterOutput+"$residuals)\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+",,xlab=\"\")";
               } else if (specificMethod=="ARIMA"){
                         filterCall= filterOutput +" <- Arima("+timeSeriesObserved+",order=c("+model.arimaOptions.arOrder+
                                          ","+model.arimaOptions.integrationOrder+","+model.arimaOptions.maOrder+"))";
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = filterOutput +"$residuals";
                         fitDeterminationCall = filterFittedValues+" <- fitted("+filterOutput+")";
                         model.plotControl.mainTitle[1]= "Given Series with ARIMA("+model.arimaOptions.arOrder+
                                          ","+model.arimaOptions.integrationOrder+","+model.arimaOptions.maOrder+") fit";
                         if (!model.arimaOptions.predictAhead.equals("0"))
                           model.plotControl.traditionalPlotBeginning[1] = "plot(forecast("+filterOutput+",h="+model.arimaOptions.predictAhead+")";
                         fitFilterCall = "plot(forecast("+filterOutput+",h="+model.arimaOptions.predictAhead+"))\n"+
                                      "   lines("+filterFittedValues+", col=\"red\")";
                         
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+",\n"+
                                         "zoo("+filterFittedValues+",order.by=index("+timeSeriesObserved+")),zoo("+filterOutput+"$residuals,order.by=index("+timeSeriesObserved+")))\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+",xlab=\"\")";
                         
                         morePlotscall1 = "tsdiag("+filterOutput+")";
               
               } else if (specificMethod.equals("Exponential Smoothing")&model.expSmoothingOptions.procedure.equals("Holt-Winters")) {
                         filterCall= filterOutput +" <- HoltWinters("+timeSeriesObserved+
                                                      (model.expSmoothingOptions.alpha.equals("automatic")?"":",alpha="+model.expSmoothingOptions.alpha)+
                                                      (model.expSmoothingOptions.beta.equals("automatic")?"":",beta="+model.expSmoothingOptions.beta)+
                                                      (model.expSmoothingOptions.seasonalType.equals("N")?",gamma=FALSE":
                                                         (model.expSmoothingOptions.gamma.equals("automatic")?"":",gamma="+model.expSmoothingOptions.gamma)+
                                                         (model.expSmoothingOptions.seasonalType.equals("A")?"":",seasonal=\"multiplicative\"")
                                                       )
                                                      +")";
                         filterTextOutput = filterOutput;
                         filterOutputResiduals =  filterOutput +"$x - "+filterOutput+"$fitted[,\"xhat\"]";
                         fitDeterminationCall = "";
                         filterFittedValues = filterOutput +"$fitted[,\"xhat\"]";
                         plotType = "Holt-Winters";
                         model.plotControl.mainTitle[1] = "Given Series with Exponential Smoothing (Holt-Winters)";
                         dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                         
                         
                         if (!model.expSmoothingOptions.predictAhead.equals("0"))
                            model.plotControl.traditionalPlotBeginning[1] = "plot("+filterOutput+",predict("+filterOutput+",n.ahead="+model.expSmoothingOptions.predictAhead+",prediction.interval=TRUE)";
                        
                         
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+ filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",l  wd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  

                         decompositionPrepCall =  decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterFittedValues+","+filterOutputResiduals+")\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+")";        
               } else if (specificMethod.equals("Exponential Smoothing")&model.expSmoothingOptions.procedure.equals("Hyndman")){

                         filterCall= filterOutput +" <- ets("+timeSeriesObserved+
                                          ", model=\""+
                                          model.expSmoothingOptions.errorType+model.expSmoothingOptions.basictrendType+model.expSmoothingOptions.seasonalType+"\""+
                                          (model.expSmoothingOptions.damped.equals("TRUE")?",damped=TRUE":"")+
                                          (model.expSmoothingOptions.alpha.equals("automatic")?"":",alpha="+model.expSmoothingOptions.alpha)+
                                                      (model.expSmoothingOptions.beta.equals("automatic")?"":",beta="+model.expSmoothingOptions.beta)+
                                                      (model.expSmoothingOptions.seasonalType.equals("N")?"":
                                                         (model.expSmoothingOptions.gamma.equals("automatic")?"":",gamma="+model.expSmoothingOptions.gamma)
                                                       )+
                                          ",opt.crit=\""+model.expSmoothingOptions.optimizationCriterion +
                                          "\",ic=\""+ model.expSmoothingOptions.infoCriterion+"\")";
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = filterOutput +"$residuals";
                         fitDeterminationCall = filterFittedValues+" <- fitted("+filterOutput+")";
                         model.plotControl.mainTitle[1] = "Given Series with Exponential Smoothing (Hyndman)";
                         if (!model.expSmoothingOptions.predictAhead.equals("0"))
                            model.plotControl.traditionalPlotBeginning[1] = "plot(forecast("+filterOutput+",h="+model.expSmoothingOptions.predictAhead+")";
                         fitFilterCall = "plot(forecast("+filterOutput+",h="+model.expSmoothingOptions.predictAhead+"))\n"+
                                      "    lines("+filterFittedValues+", col=\"red\")";  
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+",\n"+
                                         "zoo("+filterFittedValues+",order.by=index("+timeSeriesObserved+")),\n"+
                                         "zoo("+filterOutput+"$residuals,order.by=index("+timeSeriesObserved+")))\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+")";
                         decompositionLabelsCmd = vNameAdjusted+","+filterFittedValues+","+filterOutput+" residuals";
                         morePlotscall1 = "tsdiag("+filterOutput+")";
                         morePlotscall2 = "plot("+filterOutput+")";
               
                } else if (specificMethod=="GARCH"){
                         Deducer.eval("library(tseries)");
                         filterCall= filterOutput +" <- garch("+timeSeriesObserved+"-mean("+timeSeriesObserved+"),order=c("+model.filterOptions.garchOrder+
                                          ","+model.filterOptions.archOrder+"))";
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = filterOutput +"$residuals";
                         fitDeterminationCall = filterFittedValues+" <-mean("+timeSeriesObserved+")+" + filterOutputResiduals;
                         plotType = "GARCH";
                         model.plotControl.mainTitle[1]= "Given Series with GARCH fit";
                         dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",  lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";                          
                         
                         
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterFittedValues+","+filterOutputResiduals+")\n"+
                                                  "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+",,xlab=\"\")";
                 } else if ((specificMethod=="Hodrick-Prescott")) {
                         if (model.filterOptions.centreSimpleMA.TRUE) 
                              centreSimpleMAText = "TRUE"; 
                         else 
                              centreSimpleMAText = "FALSE";
                         filterCall= filterOutput +" <- hpfilter("+timeSeriesObserved+
                                                      (model.filterOptions.hpFrequency.equals("NULL")?"":",freq="+model.filterOptions.hpFrequency)+
                                                      (model.filterOptions.hpFilterType.equals("lambda")?"":",type=c(\""+model.filterOptions.hpFilterType+"\")")+
                                                      (model.filterOptions.hpDrift.equals(false)?"":",drift=TRUE")+")";
                         filterTextOutput = filterOutput;
                         filterOutputResiduals = filterOutput +"$cycle";
                         fitDeterminationCall = ""; 
                         if (model.filterOptions.hpDrift.equals(false))
                            filterFittedValues = filterOutput +"$trend";
                         else
                            filterFittedValues = timeSeriesObserved + "- "+filterOutput+"$cycle"; 
                         plotType = "Hockrick-Prescott";
                         model.plotControl.mainTitle[1] = "Given Series with Hodrick-Prescott Fit";
                         dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterOutput+"$trend), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",l  wd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  
                         
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterOutput+"$trend,"+filterOutputResiduals+")\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+")";        
               } else if ((specificMethod=="Lowess/Loess")&&(model.filterOptions.loessType.equals("Simple Lowess"))) {
                         if (model.filterOptions.centreSimpleMA.TRUE) 
                              centreSimpleMAText = "TRUE"; 
                         else 
                              centreSimpleMAText = "FALSE";
                         filterCall= filterOutput +" <- lowess("+timeSeriesObserved+",f="+model.filterOptions.lowessSpan+
                                                     ",iter="+model.filterOptions.lowessIter+")";
                         filterTextOutput = "summary("+filterOutput+")";
                         filterOutputResiduals = ""+timeSeriesObserved+"- "+filterOutput+"$y";
                         fitDeterminationCall = filterFittedValues+" <- "+filterOutput+"$y";
                         plotType = "simple lowess/loess";
                         model.plotControl.mainTitle[1] = "Given Series with Simple Lowess/Loess Fit";
                         dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",l  wd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  
                         
                         decompositionPrepCall = decompTemp + " <- merge.zoo("+timeSeriesObserved+","+filterFittedValues+","+filterOutputResiduals+")\n"+
                                         "colnames("+decompTemp+")<-c(\""+vName+"\",\"fitted values\",\"residuals\")\n";
                         decompositionCall = "xyplot("+decompTemp+")";
               } else if ((specificMethod=="Lowess/Loess")&&(model.filterOptions.loessType.equals("Seasonal Decomposition by Loess (STL)"))) {                     
                          filterCall= filterOutput +" <- stl("+model.terms.firstElement().toString()+"[,1],\"periodic\")"; 
                          filterTextOutput = "summary("+filterOutput+")";
                          filterOutputResiduals = filterOutput +"$time.series[,3]";
                          fitDeterminationCall = filterFittedValues+" <-"+timeSeriesObserved+"-" + filterOutputResiduals;
                         plotType = "seasonal lowess/loess";
                         model.plotControl.mainTitle[1]= "Given Series with Seasonal Decomp. Lowess/Loess Fit";
                         dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                         fitFilterCall = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+ 
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  
                          decompositionCall = "   plot("+filterOutput+")";
                 
               } else if ((specificMethod=="Wavelet")) {
                          Deducer.eval("library(wmtsa)");
                          String filterOutputZerod1 = ".hansel.working.env$"+Deducer.getUniqueName("filterOutputZerod1");
                          filterCall= filterOutput +" <- wav"+model.waveletOptions.waveletPresentation+"("+timeSeriesObserved+
                                    ",wavelet=\""+model.waveletOptions.waveletType+"\""+
                                     ",n.levels="+model.waveletOptions.waveletLevels+",title.data=\""+(modelTSdata?model.efeglmextra.TSlabels.replaceAll("\"",""):vName)+"\") \n"+
                                      "    "+ filterOutputZerod1 + " <- "+filterOutput+" \n"+
                                      "    " + filterOutputZerod1+"[\"d1\"] <- 0";

                          
                          filterTextOutput = filterOutput +", summary("+filterOutput+")";
                          fitDeterminationCall =  filterFittedValues+" <- reconstruct("+filterOutputZerod1+")";
                          filterOutputResiduals = "("+timeSeriesObserved+"-"+filterFittedValues+")";
                          plotType = "wavelet";
                          model.plotControl.mainTitle[1] = "Given Series with Wavelet Fit ("+model.waveletOptions.waveletPresentation+", "+model.waveletOptions.waveletType+")";
                          dataNameKey = (modelTSdata?model.efeglmextra.TSMainTitle+","+ model.efeglmextra.TSlabels:
                                      vName.toString().replace("(","_").replace(")", "").replace("[","").replace("]",""));
                        
                          fitFilterCall= "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+"), superpose=TRUE"+ 
                                      ",    main= \""+model.plotControl.mainTitle[1]+"\""+  
                                      ",    ylab=\"\",xlab=\"\",col=c(\""+lineColorDataPanel11+"\",\""+lineColorFitPanel11+"\")"+
                                     ((lineThicknessDataPanel11==1)?"":",  lwd=c("+lineThicknessDataPanel11+",1)")+
                                     ((lineTypeDataPanel11.equals("solid"))?"":",  lty=c(\""+lineTypeDataPanel11+"\",\"solid\")")+ 
                                     ((pointSizePanel1==0)?"":",type=c(\"o\"),pch=16,cex=c("+pointSizePanel1+",0)")+
                                      ",    grid=TRUE,auto.key=list(text=c(\""+dataNameKey+"\",\""+fitNameKey+"\")"+
                                      ",    space=\"bottom\",columns=2))";  
                          decompositionCall = "par(mfrow = c(1, 1),mar=c(5,4,2,2))\n"+"   eda.plot("+filterOutput+")";
                          decompositionLabelsCmd = 

                          morePlotscall1 = "xyplot(zoo(ts(wavMRD("+filterOutput+"),"+(existsTS?"start=start("+model.dName+"),frequency=frequency("+model.dName+")":"")+")),col=c(4),  main=\"MRA for "+model.waveletOptions.waveletPresentation+" of "+
                                          (modelTSdata?model.efeglmextra.TSlabels.replaceAll("\"",""):vName) +" using "+model.waveletOptions.waveletType+" filter\",,xlab=\"\")";
               }  
                
                
                if (existsTS & (specificMethod.equals("Lowess/Loess")||specificMethod.equals("Wavelet")))
                    model.plotControl.traditionalPlotBeginning2[1] = "\nlines(ts("+filterFittedValues+",start="+model.start_of_original_data+",freq="+model.frequency_of_original_data+")";
                else
                    model.plotControl.traditionalPlotBeginning2[1] = "\nlines("+filterFittedValues;

                if (!(((specificMethod=="ARIMA") & (!model.arimaOptions.predictAhead.equals("0"))) ||
                                              (((specificMethod.equals("Exponential Smoothing")) & (!model.expSmoothingOptions.predictAhead.equals("0")))))) {
                       model.plotControl.traditionalPlotBeginning[1]= "plot(cbind("+vNameAdjusted+","+filterFittedValues+")";   
                }
                model.plotControl.latticePlotBeginning[1] = "xyplot(cbind("+vNameAdjusted+","+filterFittedValues+")";       
                
                model.plotControl.singleGraphMulti[1] = true;
                model.plotControl.keyPosition[1]="top";
                
                model.plotControl.lineType2[1] = "solid";
                model.plotControl.linePoints2linewidth[1]="1";
                model.plotControl.linePoints2color[1] = "red";
                        
                         Deducer.eval(filterCall);
                         try {
                                model.dataClassInR  = Deducer.eval("as.character(class("+filterOutput+")[1])").asString();  
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                        /* Deducer.execute(filterCall); */   //Debugging statement
                         String callCommandsLog = "\n\n###### Commands resulting in initial plot of fitted curve: "+specificMethod+" ####";
                         callCommandsLog += "\n   #The initial call for determing the fit is the following:";
                         callCommandsLog += "\n   " + filterCall.replace(".hansel.working.env$", ""); 
                                aeCall = filterTextOutput;
                                callCommandsLog += "\n\n   #A text summary of the output and saving is given by the following command:";
                                callCommandsLog += "\n   "+ aeCall.replace(".hansel.working.env$", "");    
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				tmp.add("\n>"+aeCall.replace(".hansel.working.env$", "")+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                if (!fitDeterminationCall.equals("")) {
                                   Deducer.eval(fitDeterminationCall);
                                   callCommandsLog += "\n\n   #Saving of fitted values is done  by the following command:";
                                   callCommandsLog += "\n   "+ fitDeterminationCall.replace(".hansel.working.env$", "");
                                }
                                  
                               callCommandsLog += "\n\n   #The rest of the commands result in graphic output\n   ";
                               commandsLogText.setText((commandsLogText.getText()+callCommandsLog).replace(".hansel.working.env$", ""));
                               alterPlot("forecastFilterPanelPlot1");
                               callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output for \"Plot: Decomposition\"";
                               callCommandsLog += "\n   "+decompositionPrepCall.replace(".hansel.working.env$", "");
                               callCommandsLog += "\n   "+decompositionCall.replace(".hansel.working.env$", "");
                               if (morePlotscall1.length()>0){
                                  callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output for \"Plot: Other 1\"";
                                  callCommandsLog += "\n   "+morePlotscall1.replace("    ","\n   ").replace(".hansel.working.env$", "");
                               }
                               if (morePlotscall2.length()>0){
                                  callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output for \"Plot: Other 2\"";
                                  callCommandsLog += "\n   "+morePlotscall2.replace("    ","\n   ").replace(".hansel.working.env$", "");
                               }
                               
                                seenPlotPanels[7] = false;
           
                                for(int i=2;i<=13;i++){
                                      if (seenPlotPanels[i]&(!( ((i==1)& plotvsTimeButton.isSelected())||((i==2)& distributionButton.isSelected())
                                                              ||(((i==3)||(i==4))& correlogramButton.isSelected())||((i==5)& plotvsLagsButton.isSelected())
                                                              ||((i==6)& structuralBreaksButton.isSelected())||((i==11)& unitRootPlotPanel.isVisible())
                                                              ||((i==12)& structuralBreakPlotPanel.isVisible())
                                                              ) 
                                              )) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                SeenLatestFilterPlotPanel1 = false;
                                Deducer.eval(plotPanelsDevNumsName+"[1] <- dev.cur()");
                                seenPlotPanels[1] = true;
                                
                                   diagnosticscall="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n"+
                                    "    hist("+filterOutputResiduals+", freq=FALSE, col= \"light blue\", main=\"Histogram for Residuals\",xlab=\"residuals\")\n"+
                                    "    curve(dnorm(x, mean=mean(as.numeric(na.omit("+filterOutputResiduals+"))),sd=sd(as.numeric(na.omit("+filterOutputResiduals+")))), add=TRUE, lty=2, col=\"red\")\n"+
                                    "    lines(density(as.numeric(na.omit("+filterOutputResiduals+"))), lwd=1,col=\"blue\")\n"+      
                                    "    qqnorm(as.numeric(na.omit(as.numeric("+filterOutputResiduals+"))),ylab=\"Residual Quantiles\")\n"+
                                    "    qqline(as.numeric(na.omit("+filterOutputResiduals+")) ,col=2)\n"+
                                    "    plot(as.numeric("+filterFittedValues+"),as.numeric("+filterOutputResiduals+"),xlab=\"Fitted values\",ylab=\"Residuals\")\n"+
                                    "    abline(0,0)\n"+
                                    "    lines(lowess(as.numeric(na.omit("+filterFittedValues+")) ,as.numeric(na.omit("+filterOutputResiduals+"))), col = 2)\n";
                                   callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next lines result in output found for \"Plot: Diagnostics\"";
                                   callCommandsLog += "\n   "+diagnosticscall.replace("    ","\n   ").replace(".hansel.working.env$", "");

                commandsLogText.setText(commandsLogText.getText()+callCommandsLog);
                String textout = "";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                filterText.setText(textout);
		filterText.setCaretPosition(0); 
                filterText.setVisible(false);
                filterTextPanel.setVisible(false);
                forecastFilterPlotPanel2.setVisible(true);
                forecastFilterPlotPanel3.setVisible(true);
                forecastFilterPlotPanel4.setVisible(true);
                forecastFilterPlotPanel5.setVisible(true);
                //The next statements erase any problem from an overshadowing previous window
                GeneralPlotsPanel1.setVisible(false);
                GeneralPlotsPanel1.setVisible(true);

                return;   
  
	}
        
        
        
             public void setdecompOptionsText(GMModel mod){
		model = mod;
                String aeCall;
                String urTestText;
                String[] out = new String[]{};
                ArrayList tmp = new ArrayList();
                 if(specificDecomposition=="Wavelet"){
                     decompositionText.removeAll();
                     decompositionPlotPanel.removeAll();
                         Deducer.eval("waveletOutput <- wav"+model.waveletOptions.waveletPresentation+"("+model.terms.firstElement().toString()+
                                    ",wavelet=\""+model.waveletOptions.waveletType+"\""+
                                     ",n.levels="+model.waveletOptions.waveletLevels+")");
                         aeCall = "waveletOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                        
                        if (model.waveletOptions.waveletPlotType.equals("Standard")){                                        
                           String call = "try(plot(wavMRD(waveletOutput)))";
                           decompPlot = new ModelPlotPanel(call); 
                           decompositionPlotPanel.add(decompPlot);
                       }
                        else{
                           String call = "try(eda.plot(waveletOutput))";
                           decompPlot = new ModelPlotPanel(call);
                           decompositionPlotPanel.add(decompPlot);
                       }
                                
                                
		  }
                 else if (specificDecomposition=="Exponential Smoothing"){

                         decompositionText.removeAll();
                         decompositionPlotPanel.removeAll();
                         Deducer.eval("exponentialSmoothingOutput <- HoltWinters("+model.terms.firstElement().toString()+",gamma=FALSE,, beta = FALSE)");
                                aeCall = "exponentialSmoothingOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                String call = "par(mfrow = c(2, 1),mar=c(5,4,2,2))\n plot(exponentialSmoothingOutput,main=\"Exponential Smoothing\") \n plot(("+model.terms.firstElement().toString()+" - exponentialSmoothingOutput$fitted[,1]),ylab=\"Difference\")";
                                Deducer.eval(call);
                                decompPlot = new ModelPlotPanel(call);
                                decompositionPlotPanel.add(decompPlot);              
                 }
                 else if (specificDecomposition=="Seasonal Holt-Winters"){

                         decompositionText.removeAll();
                         decompositionPlotPanel.removeAll();
                         Deducer.eval("seasonalHoltWintersOutput <- HoltWinters("+model.terms.firstElement().toString()+")");
                                aeCall = "seasonalHoltWintersOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                String call = "par(mfrow = c(3, 1),mar=c(5,4,2,2))\n plot(seasonalHoltWintersOutput,main=\"Seasonal Holt-Winters Filtering\") \n plot(seasonalHoltWintersOutput$fitted[,3],ylab=\"trend\")"+
                                              "\n plot(seasonalHoltWintersOutput$fitted[,4],ylab=\"Seasonal\")";
                                decompPlot = new ModelPlotPanel(call);
                                decompositionPlotPanel.add(decompPlot);
                               
                 }
                 else if (specificDecomposition=="Non-seasonal Holt-Winters"){

                         decompositionText.removeAll();
                         decompositionPlotPanel.removeAll();
                         Deducer.eval("nonSeasonalHoltWintersOutput <- HoltWinters("+model.terms.firstElement().toString()+",gamma=FALSE)");
                                aeCall = "nonSeasonalHoltWintersOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                String call = "par(mfrow = c(2, 1),mar=c(5,4,2,2))\n plot(nonSeasonalHoltWintersOutput,main=\"Non-seasonal Holt-Winters Filtering\") \n plot(nonSeasonalHoltWintersOutput$fitted[,3],ylab=\"Difference\")";
                                decompPlot = new ModelPlotPanel(call);
                                decompositionPlotPanel.add(decompPlot);
                               
                 }
                 else if (specificDecomposition=="Moving Average, Additive"){

                         decompositionText.removeAll();
                         decompositionPlotPanel.removeAll();
                         Deducer.eval("additivedecompositionsOutput <- decompose("+model.terms.firstElement().toString()+",type=\"additive\")");
                                aeCall = "additivedecompositionsOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                String call = "par(mfrow = c(3, 1),mar=c(5,4,2,2))\n plot.ts(ts.union("+model.terms.firstElement().toString()+
                                              ",additivedecompositionsOutput$trend),main=\"Moving Average Filtering, Additive Seasonal Component\",ylab=\"Observed/Trend\",plot.type=\"single\",col=1:2)\n" +
                                              "plot.ts(additivedecompositionsOutput$seasonal,ylab=\"Seasonal\") \n plot.ts(additivedecompositionsOutput$random,,ylab=\"Random\")";
                                decompPlot = new ModelPlotPanel(call);
                                decompositionPlotPanel.add(decompPlot);                             
                 }
                 
                 else if (specificDecomposition=="Moving Average, Multiplicative"){

                         decompositionText.removeAll();
                         decompositionPlotPanel.removeAll();
                         Deducer.eval("multiplicativeDecompositionsOutput <- decompose("+model.terms.firstElement().toString()+",type=\"multiplicative\")");
                                aeCall = "multiplicativeDecompositionsOutput";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                             
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                String call = "par(mfrow = c(3, 1),mar=c(5,4,2,2))\n plot.ts(ts.union("+model.terms.firstElement().toString()+
                                              ",multiplicativeDecompositionsOutput$trend),main=\"Moving Average Filtering, Multipliciative Seasonal Component\",ylab=\"Observed/Trend\",plot.type=\"single\",col=1:2)\n" +
                                              "plot.ts(multiplicativeDecompositionsOutput$seasonal,ylab=\"Seasonal\") \n plot.ts(multiplicativeDecompositionsOutput$random,,ylab=\"Random\")";
                                decompPlot = new ModelPlotPanel(call);
                                decompositionPlotPanel.add(decompPlot);                           
                 }

                String textout = "";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                decompositionText.setText(textout);
		decompositionText.setCaretPosition(0); 
                decompositionPanel.setVisible(false);
                //The next statements erase any problem from an overshadowing previous window
                decompositionPlotPanel.setVisible(false);
                decompositionPlotPanel.setVisible(true);
 
                return;           
	}

             public void moreDiagnosticPlotsClicked(){
                 model.coefList = model.terms;
		NMTimeSOtherPlots rr = new NMTimeSOtherPlots(this,model,pre);
                rr.setLocationRelativeTo(this);
		rr.setVisible(true);
		setaddexpplots(model);
                rr.setModel(model, pre);/*This resets choices to false*/

	}
             
          public void setUnitRootTestText(){
                String call ="";
                String aeCall="";
                String urTestText;
                String[] out = new String[]{};
                ArrayList tmp = new ArrayList();
                String timeSeriesObserved =  vNameAdjusted.toString();
                
                String existsurtOutput = new String();
                  try {
                    existsurtOutput = Deducer.eval("as.character(exists(\".hansel.working.env$urtOutput\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                if (existsurtOutput.equals("TRUE"))
                    Deducer.eval("rm(urtOutput,envir="+Hansel.hanselEnv+")");   
                
                String urtOutputName = Hansel.hanselEnv+"$urtOutput";
                
                 if(specificURTest=="Augmented Dickey-Fuller"){
                         call=   urtOutputName+ " <- ur.df("+
                                 (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                 (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",type = \""
                                  +(model.unitRootTestOptions.adfDeterministicComponent.equals("no deterministic component")?"none":
                                    model.unitRootTestOptions.adfDeterministicComponent)
                                  +"\",lags="+model.unitRootTestOptions.adfMaxLag
                                  +",selectlags = \""+model.unitRootTestOptions.adfLagDetermination
                                  +"\")";
		} else if(specificURTest=="ADF-GLS"){
                         call=    urtOutputName+" <- ur.ers("+
                                  (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                  (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",type = \""
                                  +model.unitRootTestOptions.ersType
                                  +"\", model=\""+model.unitRootTestOptions.ersDeterministicComponent
                                  +"\",lag.max="+model.unitRootTestOptions.ersMaxLag
                                  +")";
                } else if(specificURTest=="KPSS"){
                         call=    urtOutputName + " <- ur.kpss("+
                                  (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                  (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",type = \""
                                  +model.unitRootTestOptions.kpssDeterministicComponent.split(":")[0] +"\""
                                  +((model.unitRootTestOptions.kpssMaxLag.equals("short")||model.unitRootTestOptions.kpssMaxLag.equals("long")||model.unitRootTestOptions.kpssMaxLag.equals("nil"))?
                                      ",lags=c(\""+model.unitRootTestOptions.kpssMaxLag+"\")":", use.lag="+ model.unitRootTestOptions.kpssMaxLag)
                                  +")";
                } else if(specificURTest=="Phillips and Perron"){
                         call=    urtOutputName + " <- ur.pp("+
                                  (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                  (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",type = \""
                                  +model.unitRootTestOptions.ppType
                                  +"\", model=\""+model.unitRootTestOptions.ppDeterministicComponent+"\""
                                 +((model.unitRootTestOptions.ppLag.equals("short")||model.unitRootTestOptions.ppLag.equals("long"))?
                                      ",lags=c(\""+model.unitRootTestOptions.ppLag+"\")":", use.lag="+ model.unitRootTestOptions.ppLag)
                                  +")";
               } else if(specificURTest=="Schmidt and Phillips"){
                         call=    urtOutputName + "<- ur.sp("+
                                  (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                  (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",type = \""
                                  +model.unitRootTestOptions.spType
                                  +"\", pol.deg="+model.unitRootTestOptions.spPolynDegree
                                  +",signif="+model.unitRootTestOptions.spSig
                                  +")";
               } else /*if(specificURTest=="Zivot and Andrews")*/{
                         call=    urtOutputName + " <- ur.za("+
                                  (model.unitRootTestOptions.differencing.equals("Use 1st difference")?"diff("+timeSeriesObserved+")":
                                  (model.unitRootTestOptions.differencing.equals("Use 2nd difference")?"diff("+timeSeriesObserved+",differences = 2)":
                                  timeSeriesObserved))   
                                  +",model = \""
                                  +model.unitRootTestOptions.zaBreakControl
                                  +"\",lag="+model.unitRootTestOptions.zaMaxLag
                                  +")";
                }     
                
                unitRootText.removeAll();
                unitRootPlotPanel.removeAll();
                seenPlotPanels[12]=false;
                Deducer.eval(".hansel.working.env$"+call);
                        aeCall = "summary(.hansel.working.env$"+urtOutputName+")";
                        try {    
                             out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                        } catch (REXPMismatchException e) {
                        new ErrorMsg(e);
                        }

                        for(int i=0;i<out.length;i++)
                                tmp.add(out[i]);
                         
                 
                                
                 callURPlot = "par(mfrow = c(1, 1),mar=c(5,4,2,2))\n   plot(.hansel.working.env$"+urtOutputName+")";
                 
                 String callCommandsLog = "\n\n######Commands resulting in output in Unit Root Tests tab:"+specificURTest+"####";
                 callCommandsLog += "   #\n   #The initial unit root test call and saving of resulting object is the following:";
                 callCommandsLog += "\n   " + call;
                 callCommandsLog += "\n\n   #A text summary of the output is given by the following command:";
                 callCommandsLog += "\n   "+ aeCall.replace(".hansel.working.env$","");;
                 callCommandsLog += "\n\n   #The rest of the commands result in graphic output";
                 callCommandsLog += "\n   JavaGD() #Opens a new graphic device; the next line results in the plotting of residuals, ACF, and PACF";
                 callCommandsLog += "\n   "+ callURPlot.replace(".hansel.working.env$","");
                 commandsLogText.setText(commandsLogText.getText()+callCommandsLog);               

                String textout = "";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                this.unitRootText.setText(textout);
                this.setFont(font12);
		this.unitRootText.setCaretPosition(0);
                unitRootPlotPanel.setVisible(false);
                unitRootTextPanel.setVisible(false);
                unitRootTextPanel.setVisible(true);
                return;                             
                                
	}
      
        public void setStructuralBreakTestText(){
                String call ="";
                String aeCall1="";
                String aeCall2="";
                String urTestText;
                String[] out1 = new String[]{};
                String[] out2 = new String[]{};
                ArrayList tmp = new ArrayList();
                String timeSeriesObserved =  vNameAdjusted.toString();
                
                String strBreakOutputName = Deducer.getUniqueName("strBreakOutputName").toString();
                 if(specificSBTest.equals("Gen. Emp. fluct: M-test")){
                      call=   strBreakOutputName+ " <- gefp("+ vName +
                                 " ~ 1, fit = lm, vcov = kernHAC,data="+model.dName+")"; 
		} 
          
                structuralBreakText.removeAll();
                structuralBreakPlotPanel.removeAll();
                seenPlotPanels[13]=false;
                Deducer.eval(call);
                        aeCall1 = strBreakOutputName;
                        aeCall2 = "sctest("+strBreakOutputName+")";
                        try {    
                             out1 = Deducer.eval("capture.output("+aeCall1+")").asStrings();
                             out2 = Deducer.eval("capture.output("+aeCall2+")").asStrings();
                        } catch (REXPMismatchException e) {
                        new ErrorMsg(e);
                        }
       
                        for(int i=0;i<out1.length;i++)
                                tmp.add(out1[i]);
                        for(int i=0;i<out2.length;i++)
                                tmp.add(out2[i]);
                          
                 callSBPlot = "par(mfrow = c(1, 1),mar=c(5,4,2,2))\n   plot("+strBreakOutputName+")";
                 
                 String callCommandsLog = "\n\n######Commands resulting in output in Structrual Breaks Tests tab:"+specificURTest+"####";
                 callCommandsLog += "   #\n   #The initial structural break test call and saving of resulting object is the following:";
                 callCommandsLog += "\n   " + call;
                 callCommandsLog += "\n\n   #A text summary of the output is given by the following command:";
                 callCommandsLog += "\n   "+ aeCall1;
                 callCommandsLog += "\n\n   #The test with text-output is given by the following command:";
                 callCommandsLog += "\n   "+ aeCall2;
                 callCommandsLog += "\n\n   #The following commands result in graphic output";
                 callCommandsLog += "\n   JavaGD() #Opens a new graphic device; the next line results in the plotting of residuals, ACF, and PACF";
                 callCommandsLog += "\n   "+ callSBPlot;
                 callCommandsLog += "\n\n   #The following command removes the objecte created in the first step.";
                 callCommandsLog += "\n   rm("+strBreakOutputName+")";
                 commandsLogText.setText(commandsLogText.getText()+callCommandsLog);    
                
           
                String textout = "";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                
                if (!seenPlotPanels[13]){ 
                     structuralBreakPlot = new ModelPlotPanel(callSBPlot);
                     structuralBreakPlotPanel.add(structuralBreakPlot);
                     Deducer.eval(plotPanelsDevNumsName+"[13] <- dev.cur()");
                     seenPlotPanels[13] = true;
                }
                     
                Deducer.eval("rm("+strBreakOutputName+")");
                
                
                if (structuralBreakPlotPanel.isVisible()) {
                                structuralBreakPlotPanel.repaint();
                                structuralBreakTextPanel.repaint();
                            }
                this.structuralBreakText.setText(textout);
                this.setFont(font12);
		this.structuralBreakText.setCaretPosition(0);
                structuralBreakPlotPanel.setVisible(true);
                structuralBreakTextPanel.setVisible(false);
                return;                             
                                
	}
          
        public void setaddexp(GMModel mod){
		model = mod;
                ae[1] ="I'm here!";
		ae =model.runae(true,pre); 
                ae[1] = ae[1] + "********************************\n" + addexp.getText();
                addexp.setText(ae[1]);
		addexp.setCaretPosition(0);   
	}
               public void setaddexpplots(GMModel mod){
		model = mod;
                
                if (model.efeaeplots.moreDiagnosticPlots.equals("Histogram")) {
                  
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="hist(as.numeric("+vNameAdjusted+"), freq=FALSE, col= \"light blue\", main=\"Histogram\",xlab=\""+vNameOrTSLabel+"\")\n"+
                                    "curve(dnorm(x, mean=mean(as.numeric(na.omit("+vNameAdjusted+"))),sd=sd(as.numeric(na.omit("+vNameAdjusted+")))), add=TRUE, lty=2, col=\"red\")\n"+
                                    "lines(density(as.numeric(na.omit("+vNameAdjusted+"))), lwd=1,col=\"blue\")\n";       
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Histogram *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Normal Q-Q")) {
                  
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="qqnorm(as.numeric("+vNameAdjusted+"),ylab=\"Quantiles\");qqline(as.ts(as.numeric("+vNameAdjusted+"),col=2))\n";
                                                              
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("autocorrelation function for given series")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);

                        
                        String call="acf("+nameAdjustedForTS+
                                          (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")
                                         +"\"correlation\",main=\""+vName+"\",col=2)\n";
 
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("autocovariance function for given series")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="acf("+nameAdjustedForTS+
                                (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")+
                                "\"covariance\",main=\""+vName+"\",col=6)\n";

                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               }  else if (model.efeaeplots.moreDiagnosticPlots.equals("partial autocorrelation for given series")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="acf("+nameAdjustedForTS+
                                     (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")+
                                      "\"partial\",main=\""+vName+"\",col=3)\n";

                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("autocorrelation function for given series squared")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="acf(("+nameAdjustedForTS+")^2"+
                                   (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")+
                                    "\"correlation\",main=\"("+vName+")^2\",col=2)\n";
                        
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("autocovariance function for given series squared")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="acf(("+nameAdjustedForTS+")^2"+
                                (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")+
                                "\"covariance\",main=\"("+vName+")^2\",col=6)\n";
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("partial autocorrelation for given series squared")) {
                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String call="acf(("+nameAdjustedForTS+")^2"+
                                     (model.UTSOtherPlotsOptions.maxLag.equals("default")?",,":",lag.max="+model.UTSOtherPlotsOptions.maxLag+",")+
                                     "\"partial\",main=\"("+vName+")^2\",col=3)\n";
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               } else if (model.efeaeplots.moreDiagnosticPlots.equals("Given Series vs Lag")) {

                  {try{
                        GeneralPlotsPanel6.removeAll();
                        GeneralPlotsPanel6.setVisible(false);
                        String lagsFormula = "";
                        if (model.UTSOtherPlotsOptions.minLag.equals("1"))
                            if (model.UTSOtherPlotsOptions.maxLag.equals("default"))
                                lagsFormula = ",,";
                            else 
                                lagsFormula = ",lags="+model.UTSOtherPlotsOptions.maxLag+",";
                        else if (model.UTSOtherPlotsOptions.minLag.equals("max lag"))
                            if (model.UTSOtherPlotsOptions.maxLag.equals("default"))
                                lagsFormula = ",,";
                            else 
                                lagsFormula = ",set.lags="+model.UTSOtherPlotsOptions.maxLag;
                        else
                            if (model.UTSOtherPlotsOptions.maxLag.equals("default"))
                                lagsFormula = ",set.lags="+model.UTSOtherPlotsOptions.minLag;
                            else 
                                lagsFormula = ",set.lags="+model.UTSOtherPlotsOptions.minLag+":"+model.UTSOtherPlotsOptions.maxLag;
                            
                        String call = "";
                        
                        if (vNameSimplifiedCharacters.equals(nameAdjustedForTS)&!nullDimension&!nullColNames) {
                            call = "lag.plot("+nameAdjustedForTS+","+lagsFormula + ",do.lines=FALSE,diag.col=\"gold\",col=4)";
                        } else {
                            //tempName is created to work-around the fact that lag.plot does not allow renaming of y-axis. 
                            String tempName = Deducer.getUniqueName("."+vNameSimplifiedCharacters+".");
                            call= tempName +"<-"+nameAdjustedForTS+
                                    (nullDimension?"\ndim("+tempName+") <- c(length("+tempName+"),1)":"")+
                                    ((nullColNames||nameAdjustedForTS.contains("("))?"\ncolnames("+tempName+")<-\""+vNameOrTSLabel+"\"":"")+
                                    "\nlag.plot("+tempName+","+lagsFormula + ",do.lines=FALSE,diag.col=\"gold\",col=4)"+
                                    "\nrm("+tempName+")"; 
                        }
                        
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Normal Q-Q *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
                        GeneralPlotsPanel6.setVisible(true);
                   }catch(Exception e){
			new ErrorMsg(e);
		    }    
                  }
               }
                
	}
	public void run(){
                model.run(false,pre);
		this.dispose();
		GMDialog.setLastModel(model);
		Deducer.eval("rm('"+pre.data.split("\\$")[1]+"','"+pre.modelName.split("\\$")[1]+"',envir="+Deducer.guiEnv+")");
	}
	
	public void updateClicked(){
		GLMBuilder bld = new GLMBuilder(model);
		bld.setLocationRelativeTo(this);
		bld.setVisible(true);
		WindowTracker.addWindow(bld);
		this.dispose();

	}
	
	public void optionsClicked(){
		GLMExplorerOptions opt = new GLMExplorerOptions(this,model);
		opt.setLocationRelativeTo(this);
		opt.setVisible(true);
		setaddexp(model);
                
	}
        public void decompositionClicked(String specificDecomp){
                specificDecomposition = specificDecomp;
                if(specificDecomposition=="Wavelet"){
		   NMTimeSWaveletOptions decompOptions = new NMTimeSWaveletOptions(this,model,pre,specificDecomposition);
		   decompOptions.setLocationRelativeTo(this);
		   decompOptions.setVisible(true);
                   decompOptions.setModel(model, pre);//This resets choices to false.
                }
		setdecompOptionsText(model);
                
	}
        public void forecastFilterClicked(String specificMeth){
                noReactiontoAction = true;
                specificMethod = specificMeth;
                if(specificMethod=="Moving Average"){
		   NMTimeSMovingAvgOptions movingAvgOptions = new NMTimeSMovingAvgOptions(this,model,pre,specificMethod);
		   movingAvgOptions.setLocationRelativeTo(this);
		   movingAvgOptions.setVisible(true);
                   movingAvgOptions.setModel(model, pre);//This resets choices to false.
                }
                if(specificMethod=="Polynomial, Fourier"){
		   NMTimeSPolynFourierOptions polynFourierOptions = new NMTimeSPolynFourierOptions(this,model,pre,specificMethod);
		   polynFourierOptions.setLocationRelativeTo(this);
		   polynFourierOptions.setVisible(true);
                   polynFourierOptions.setModel(model, pre);//This resets choices to false.
                }
                 else if(specificMethod=="Exponential Smoothing"){
		   NMTimeSExpSmoothingOptions expSmoothOptions = new NMTimeSExpSmoothingOptions(this,model,pre,specificMethod);
		   expSmoothOptions.setLocationRelativeTo(this);
		   expSmoothOptions.setVisible(true);
                   expSmoothOptions.setModel(model, pre);//This resets choices to false.
                }
                else if(specificMethod=="ARIMA"){
		   NMTimeSARIMAOptions arimaOptions = new NMTimeSARIMAOptions(this,model,pre,specificMethod);
		   arimaOptions.setLocationRelativeTo(this);
		   arimaOptions.setVisible(true);
                   arimaOptions.setModel(model, pre);//This resets choices to false.
                }
              /* else if(specificMethod=="GARCH"){
		   NMTimeSGARCHOptions garchOptions = new NMTimeSGARCHOptions(this,model,pre,specificMethod);
		   garchOptions.setLocationRelativeTo(this);
		   garchOptions.setVisible(true);
                   garchOptions.setModel(model, pre);//This resets choices to false.
                } */
               else if(specificMethod=="Hodrick-Prescott"){
		   NMTimeSHodrickPrescottOptions hodrickPrescottOptions = new NMTimeSHodrickPrescottOptions(this,model,pre,specificMethod);
		   hodrickPrescottOptions.setLocationRelativeTo(this);
		   hodrickPrescottOptions.setVisible(true);
                   hodrickPrescottOptions.setModel(model, pre);//This resets choices to false.
                }
                else if(specificMethod=="Lowess/Loess"){
		   NMTimeSLoessOptions loessOptions = new NMTimeSLoessOptions(this,model,pre,specificMethod);
		   loessOptions.setLocationRelativeTo(this);
		   loessOptions.setVisible(true);
                   loessOptions.setModel(model, pre);//This resets choices to false.
                }
                
                else if(specificMethod=="Wavelet"){
		   NMTimeSWaveletOptions decompOptions = new NMTimeSWaveletOptions(this,model,pre,specificDecomposition);
		   decompOptions.setLocationRelativeTo(this);
		   decompOptions.setVisible(true);
                   decompOptions.setModel(model, pre);
                }
                

                if (model.filterOptions.filterRun) { 
                     for(int i=7;i<=11;i++){
                              Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"");
                              seenPlotPanels[i] = false; 
                     }
		   arimaGarchPlotandText(model);
                   model.filterOptions.filterRun = false;
                }
                else
                    if (GeneralPlotsPanel1.isVisible()) {
                        GeneralPlotsPanel1.setVisible(false);
                        GeneralPlotsPanel1.repaint();
                        GeneralPlotsPanel1.setVisible(true);
                      }
               
              filterTextPanel.setVisible(false);
              filterText.setVisible(false); 
              forecastFilterPlotPanel2.setVisible(true);
              forecastFilterPlotPanel3.setVisible(true);
              forecastFilterPlotPanel4.setVisible(true);
              forecastFilterPlotPanel5.setVisible(true);
              GeneralPlotsPanel1.setVisible(false);
              forecastFilterPlotPanel2.setVisible(false);
              forecastFilterPlotPanel3.setVisible(false);
              forecastFilterPlotPanel4.setVisible(false);
              forecastFilterPlotPanel5.setVisible(false);
              GeneralPlotsPanel1.setVisible(true);
              noReactiontoAction = false;
	}
        public void unitRootTestClicked(String specificTest){
                specificURTest = specificTest;
		NMTimeSUnitRootTestOptions urOptions = new NMTimeSUnitRootTestOptions(this,model,pre,specificURTest);
		urOptions.setLocationRelativeTo(this);
		urOptions.setVisible(true);	
                urOptions.setModel(model, pre);//This resets choices to false.               
                
                if (urOptions.unitRootTestOptionsOkay) { 
                   setUnitRootTestText(); 
                   urOptions.unitRootTestOptionsOkay = false;
                }
                else
                    if (unitRootPlotPanel.isVisible()) {
                        unitRootPlotPanel.repaint();
                        unitRootTextPanel.repaint();
                      }

	}
        public void diagnosticTestsClicked(){
		GMExplorerDiagTestsMain dt = new GMExplorerDiagTestsMain(this,model,pre);
		dt.setLocationRelativeTo(this);
		dt.setVisible(true);
		setaddexp(model);
	}
        
        
	public void functionalFormClicked(){
		GMExplorerOtherPlots rr = new GMExplorerOtherPlots(this,model,pre);
		rr.setLocationRelativeTo(this);
		rr.setVisible(true);
		setaddexpplots(model);	
	}
	public void postHocClicked(){
		GLMExplorerPostHoc post = new GLMExplorerPostHoc(this,model,pre);
		post.setLocationRelativeTo(this);
		post.setVisible(true);
		setaddexp(model);
	}
        
        
	public void meansClicked(){
		GLMExplorerMeans m = new GLMExplorerMeans(this,model,pre);
		m.setLocationRelativeTo(this);
		m.setVisible(true);
		setaddexp(model);		
	}
	
	
	public void testsClicked(){
                GMExplorerTestCoeffsMain testCoeffDialog = new GMExplorerTestCoeffsMain(this,model,pre);
		testCoeffDialog.setLocationRelativeTo(this);
		testCoeffDialog.setVisible(true);
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
	}

	public void windowActivated(WindowEvent arg0) {}

	public void windowClosed(WindowEvent arg0) {
		if(strucchangeTab!=null)
			strucchangeTab.executeDevOff();
		if(addedTab!=null)
			addedTab.executeDevOff();
	}

	public void windowClosing(WindowEvent arg0) {}

	public void windowDeactivated(WindowEvent arg0) {}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}
        
        public void distributionClicked(){
                    
                   String call="par(mfrow = c(1, 2),mar=c(5,4,2,2))\n"+
                                    "hist(as.numeric("+vNameAdjusted+"), freq=FALSE, col= \"light blue\", main=\"Histogram\",xlab=\""+vNameOrTSLabel+"\")\n"+
                                    "curve(dnorm(x, mean=mean(as.numeric(na.omit("+vNameAdjusted+"))),sd=sd(as.numeric(na.omit("+vNameAdjusted+")))), add=TRUE, lty=2, col=\"red\")\n"+
                                    "lines(density(as.numeric(na.omit("+vNameAdjusted+"))), lwd=1,col=\"blue\")\n"+
                                    "qqnorm(as.numeric("+vNameAdjusted+"),ylab=\"Quantiles\");qqline(as.ts(as.numeric("+vNameAdjusted+"),col=2))\n"; 
                   
                       commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Distribution *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
			distributionTab = new ModelPlotPanel(call);
			GeneralPlotsPanel2.add(distributionTab);
                        Deducer.eval(plotPanelsDevNumsName+"[2] <- dev.cur()");
	}
        
            public void correlogramsClicked(){
                       String call="par(mfrow = c(1, 3),mar=c(5,4,2,2))\n"+
                             "acf("+nameAdjustedForTS+",,\"correlation\",main=\"\",col=2)\n"+
                             "acf("+nameAdjustedForTS+",,\"covariance\",main=\"\",col=6)\n"+
                             "acf("+nameAdjustedForTS+",,\"partial\",main=\"\",col=3)";
                       commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Correlograms for Given Series *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			levelRelationsPanelcontent = new ModelPlotPanel(call);
			GeneralPlotsPanel3.add(levelRelationsPanelcontent);
                        Deducer.eval(plotPanelsDevNumsName+"[3] <- dev.cur()");
                 
                        call="par(mfrow = c(1, 3),mar=c(5,4,2,2))\n"+
                             "acf(("+nameAdjustedForTS+")^2,,\"correlation\",main=\"\",col=2)\n"+
                             "acf(("+nameAdjustedForTS+")^2,,\"covariance\",main=\"\",col=6)\n"+
                             "acf(("+nameAdjustedForTS+")^2,,\"partial\",main=\"\",col=3)";
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Correlograms for Given Series Squared*************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			squaredRelationsPanelcontent = new ModelPlotPanel(call);
			GeneralPlotsPanel4.add(squaredRelationsPanelcontent);
                        Deducer.eval(plotPanelsDevNumsName+"[4] <- dev.cur()");
 
	}    
       public void plotvsLagsClicked(){
                                         /* JOptionPane.showMessageDialog(null, "debug2 vName="+vName);
                                          JOptionPane.showMessageDialog(null, "debug3 model.efeglmextra.TSlabels="+model.efeglmextra.TSlabels);*/

                        String call = "";
                        
                        if (vNameSimplifiedCharacters.equals(nameAdjustedForTS)&!nullDimension&!nullColNames) {
                            call = "lag.plot("+nameAdjustedForTS+",4,do.lines=FALSE,diag.col=\"gold\",col=4)";
                        } else {
                            //tempName is created to work-around the fact that lag.plot does not allow renaming of y-axis. 
                            String tempName = Deducer.getUniqueName("."+vNameSimplifiedCharacters+".");
                            call= tempName +"<-"+nameAdjustedForTS+
                                    (nullDimension?"\ndim("+tempName+") <- c(length("+tempName+"),1)":"")+
                                    ((nullColNames||nameAdjustedForTS.contains("("))?"\ncolnames("+tempName+")<-\""+vNameOrTSLabel+"\"":"")+
                                    "\nlag.plot("+tempName+",4,do.lines=FALSE,diag.col=\"gold\",col=4)"+
                                    "\nrm("+tempName+")"; 
                        }     
                        commandsLogText.setText(commandsLogText.getText() + 
                                        "\n\n#*************plot commands for Plot vs Lags *************\n"+
                                         "JavaGD() #Opens a new graphic device\n"+ call);
                        
			plotvslagscontent = new ModelPlotPanel(call);
			GeneralPlotsPanel5.add(plotvslagscontent);
                        Deducer.eval(plotPanelsDevNumsName+"[5] <- dev.cur()");
                          
	}

     
    public void PlotEditClicked(){
    int panelDealtWith = 0;
    if(plotvsTimeButton .isSelected()) {
         panelDealtWith = 1;
    } else if(distributionButton.isSelected()) {
         panelDealtWith = 2;
    } else if(correlogramButton.isSelected()) {
         panelDealtWith = 3;
    } else if(plotvsLagsButton.isSelected()) {
         panelDealtWith = 6;
    }
    HanselPlotEdit plotEditDialog = new HanselPlotEdit(this,model,"EFEUTSExplorer",pre,panelDealtWith);
    plotEditDialog.setLocationRelativeTo(this);
    plotEditDialog.setVisible(true);

   } 
     
        public void interactiveClicked(String interactiveType){
            int panelDealtWith = 0;
            if(plotvsTimeButton .isSelected()) {
                 panelDealtWith = 1;
            } else if(distributionButton.isSelected()) {
                 panelDealtWith = 2;
            } else if(correlogramButton.isSelected()) {
                 panelDealtWith = 3;
            } else if(plotvsLagsButton.isSelected()) {
                 panelDealtWith = 6;
            }
            GMExplorerLMInteractive plotEditDialog = new GMExplorerLMInteractive(this,model,"EFEUTSExplorer",pre,panelDealtWith,interactiveType);
            plotEditDialog.setLocationRelativeTo(this);
            plotEditDialog.setVisible(true);

   } 
           public void alterPlot(String cmd){
                       int panelDealtWith = 0;
                    if(plotvsTimeButton.isSelected()||cmd.equals("forecastFilterPanelPlot1")) {
                         panelDealtWith = 1;
                    } else if(distributionButton.isSelected()) {
                         panelDealtWith = 2;
                    } else if(correlogramButton.isSelected()) {
                         panelDealtWith = 3;
                    } else if(plotvsLagsButton.isSelected()) {
                         panelDealtWith = 6;
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
                        
                        String legendText4 = model.plotControl.legendText4[panelDealtWith];
                        String lineType4 = model.plotControl.lineType4[panelDealtWith];
                        String linePoints4linewidth = model.plotControl.linePoints4linewidth[panelDealtWith];
                        String linePoints4color = model.plotControl.linePoints4color[panelDealtWith];
                        
                        String legendText5 = model.plotControl.legendText5[panelDealtWith];
                        String lineType5 = model.plotControl.lineType5[panelDealtWith];
                        String linePoints5linewidth = model.plotControl.linePoints5linewidth[panelDealtWith];
                        String linePoints5color = model.plotControl.linePoints5color[panelDealtWith];
                        
                        String legendText6 = model.plotControl.legendText6[panelDealtWith];
                        String lineType6 = model.plotControl.lineType6[panelDealtWith];
                        String linePoints6linewidth = model.plotControl.linePoints6linewidth[panelDealtWith];
                        String linePoints6color = model.plotControl.linePoints6color[panelDealtWith];
       
                        String xLabels = model.plotControl.xLabels[panelDealtWith];
                        String yLabels = model.plotControl.yLabels[panelDealtWith];
                        
                        
              Boolean xyplotDealtWith = true;
 
                       if (panelDealtWith==1) {
                          
                         if (cmd.equals("forecastFilterPanelPlot1")) {
                             if (GeneralPlotsPanel1.getComponentCount()>0)
                                    GeneralPlotsPanel1.remove(0);
                               GeneralPlotsPanel1.setVisible(false);
                         } else {
                             GeneralPlotsPanel1.removeAll();
                          GeneralPlotsPanel1.setVisible(false);
                         } 
                      } else if (panelDealtWith==2) {
                          GeneralPlotsPanel2.removeAll();
                          GeneralPlotsPanel2.setVisible(false);
                      } else if (panelDealtWith==3) {
                          GeneralPlotsPanel3.removeAll();
                          GeneralPlotsPanel3.setVisible(false);
                      } else if (panelDealtWith==4) {
                          GeneralPlotsPanel4.removeAll();
                          GeneralPlotsPanel4.setVisible(false);
                      }
                       else if (panelDealtWith==5) {
                          GeneralPlotsPanel5.removeAll();
                          GeneralPlotsPanel5.setVisible(false);
                      }
                       else if (panelDealtWith==6) {
                          GeneralPlotsPanel6.removeAll();
                          GeneralPlotsPanel6.setVisible(false);
                      }
                       
                   
                      String callCommandsLog ="";
                      String callforLog ="";
                      String toEvalFirst ="";
                      String call = "";
                      
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
                                          extremenessBasislabelFormula = "abs("+filterOutputResiduals+")";
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
                                          extremenessBasisFormula = "abs("+filterOutputResiduals+")";
                                      }
                                  }
                                  String labels = "";
                                  if    (pointLabels.equals("row names"))
                                      labels = model.rowNamesOrDatesLabel;
                                  else if (pointLabels.equals("x values"))
                                      labels = xLabels;
                                  else if (pointLabels.equals("y values"))
                                      labels = yLabels;
                                  else if (pointLabels.equals("extremeness measure"))
                                      labels = "round("+extremenessBasislabelFormula+",3)";    
                      
                      
                      //Note that this is the same as in GMExplorer except (1) commented-out confidence-interval stuff & 
                      // (2) altered what is covered in x-axis-based point labels and y-axis-based point labels. 

                          String lwdWording = "";
                          String colWording = "";
                          String ltyWording = "";
                          if (/*singleGraphMulti*/ !linePoints2color.equals("")) {

                              lwdWording =(
                                   (linePoints1linewidth.equals(""))&
                                   (linePoints2linewidth.equals(""))&
                                   (linePoints3linewidth.equals(""))&
                                   (linePoints4linewidth.equals(""))&   
                                   (linePoints5linewidth.equals("")) & 
                                   (linePoints6linewidth.equals(""))
                                      ?"":
                                    "lwd=c(\""+(plotType1.equals("p")||plotType1.equals("n")?"NA":linePoints1linewidth)+"\",\""+linePoints2linewidth+"\""+
                                    (linePoints3linewidth.equals("")?"":",\""+linePoints3linewidth+"\"")+
                                    (linePoints4linewidth.equals("")?"":",\""+linePoints4linewidth+"\"")+  
                                    (linePoints5linewidth.equals("")?"":",\""+linePoints5linewidth+"\"")+
                                    (linePoints6linewidth.equals("")?"":",\""+linePoints6linewidth+"\"")
                                      +")");

                              colWording =(
                                   (linePoints1linewidth.equals(""))&
                                   (linePoints2linewidth.equals(""))&
                                   (linePoints3linewidth.equals(""))&
                                   (linePoints4linewidth.equals(""))&
                                   (linePoints5linewidth.equals(""))&
                                   (linePoints6linewidth.equals(""))
                                      ?"":
                                    "col=c(\""+linePoints1color+"\",\""+linePoints2color+"\""+
                                    (linePoints3color.equals("")?"":",\""+linePoints3color+"\"")+
                                    (linePoints4color.equals("")?"":",\""+linePoints4color+"\"")+ 
                                    (linePoints5color.equals("")?"":",\""+linePoints5color+"\"")+  
                                    (linePoints6color.equals("")?"":",\""+linePoints6color+"\"")  
                                      +")");

                              ltyWording = 
                                   ((lineType1.equals(""))&
                                    (lineType2.equals(""))& 
                                    (lineType3.equals(""))&
                                    (lineType4.equals(""))&
                                    (lineType5.equals(""))&
                                    (lineType6.equals(""))
                                      
                                      ?"":  
                                         "lty=c(\""+lineType1+"\",\""+lineType2+"\""+
                                    (lineType3.equals("")?"":",\""+lineType3+"\"")+
                                    (lineType4.equals("")?"":",\""+lineType4+"\"")+ 
                                    (lineType5.equals("")?"":",\""+lineType5+"\"")+
                                    (lineType6.equals("")?"":",\""+lineType6+"\"")
                                      +")");
                          }
                                  
                                  
                                  
                                  
                           if (graphicSystem.equals("traditional graphics")) {

                                   callforLog = "";
                                   callforLog = 
                                           
                                           
                                           (singleGraphMulti&
                                            ((model.dataClassInR.equals("data.frame")||model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts")))&
                                            !(((specificMethod=="ARIMA") & (!model.arimaOptions.predictAhead.equals("0"))) ||
                                              (((specificMethod.equals("Exponential Smoothing")) & (!model.expSmoothingOptions.predictAhead.equals("0")))))
                                               ? "ts.":"")  
                                                   
                                               +

                                           (model.dataClassInR.equals("zoo")||model.dataClassInR.equals("zooreg")||model.dataClassInR.equals("xts")?
                                            traditionalPlotBeginning.replace("plot","plot.zoo")
                                             :
                                            traditionalPlotBeginning
                                            )+

                                                (singleGraphMulti&
                                                 !(model.dataClassInR.equals("data.frame")||model.dataClassInR.equals("ts")||model.dataClassInR.equals("mts"))?
                                                  ",plot.type=\"single\""
                                                 :"")+

                                      ((mainTitle.equals("")||mainTitle.equals("_na_"))&singleTimeSeries?"":",main=\""+ mainTitle+"\"")+
                                      (yAxisTitle.equals("")
                                       &!(singleGraphMulti&
                                              (model.dataClassInR.equals("zoo")||model.dataClassInR.equals("zooreg")||model.dataClassInR.equals("xts")))  
                                           ||yAxisTitle.equals("_na_")?"":",ylab=\""+ yAxisTitle+"\"")+
                                      (xAxisTitle.equals("_na_")?"":",xlab=\""+ xAxisTitle+"\"")+
                                      
                                      (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                      ",xlim=c("+manualxAxisMinText+","+manualxAxisMaxText+")":"")+
                                      (manualyAxisRange&(!manualyAxisMinText.equals(""))&(!manualyAxisMinText.equals(""))?
                                      ",ylim=c("+manualyAxisMinText+","+manualyAxisMaxText+")":"")+
                                      (showTickMarks?"":",tck=0")+    
                                     (plotType1.equals("")?"":",type=\""+plotType1.split(":")[0]+"\"")+
                                     (linePoints1Symbol.equals("")?"":",pch="+linePoints1Symbol.split(":")[0])+
                                      
                                     (singleGraphMulti?
                                          (lineType1.equals("solid")&lineType2.equals("solid")&(lineType3.equals("solid")|lineType3.equals(""))
                                           &(lineType4.equals("solid")|lineType4.equals(""))&(lineType5.equals("solid")|lineType5.equals(""))
                                           &(lineType6.equals("solid")|lineType6.equals(""))?"":
                                                      ",lty=c(\""+lineType1+"\",\""+lineType2+"\""+
                                                      (lineType3.equals("")?"":",\""+lineType3+"\"")+
                                                      (lineType4.equals("")?"":",\""+lineType4+"\"")+
                                                      (lineType5.equals("")?"":",\""+lineType5+"\"")+
                                                      (lineType6.equals("")?"":",\""+lineType6+"\"")+
                                           ")")+

                                          ((linePoints1linewidth.equals("")||linePoints1linewidth.equals("1"))&
                                           (linePoints2linewidth.equals("")||linePoints2linewidth.equals("1"))&
                                           (linePoints3linewidth.equals("")||linePoints3linewidth.equals("1"))&
                                           (linePoints4linewidth.equals("")||linePoints4linewidth.equals("1"))&
                                           (linePoints5linewidth.equals("")||linePoints5linewidth.equals("1"))&
                                           (linePoints6linewidth.equals("")||linePoints6linewidth.equals("1")
                                            )?"":
                                            ",lwd=c(\""+linePoints1linewidth+"\",\""+linePoints2linewidth+"\""+
                                            (linePoints3linewidth.equals("")?"":",\""+linePoints3linewidth+"\"")+")")+

                                          ((linePoints1linewidth.equals("")||linePoints1color.equals("black"))&
                                           (linePoints2linewidth.equals("")||linePoints2color.equals("black"))&
                                           (linePoints3linewidth.equals("")||linePoints3color.equals("black"))&
                                           (linePoints4linewidth.equals("")||linePoints4color.equals("black"))&
                                           (linePoints5linewidth.equals("")||linePoints5color.equals("black"))&
                                           (linePoints6linewidth.equals("")||linePoints6color.equals("black")
                                           
                                           )?"":
                                            ",col=c(\""+linePoints1color+"\",\""+linePoints2color+"\""+
                                            (linePoints3color.equals("")?"":",\""+linePoints3color+"\"")+
                                            (linePoints4color.equals("")?"":",\""+linePoints4color+"\"")+
                                            (linePoints5color.equals("")?"":",\""+linePoints5color+"\"")+
                                            (linePoints6color.equals("")?"":",\""+linePoints6color+"\"")
                                           
                                           +")") 
                                      :  
                                         ",col=\""+linePoints1color+"\""+
                                         (linePoints1linewidth.equals("1.0")||linePoints1linewidth.equals("")?"":",lwd="+linePoints1linewidth)+
                                         (lineType1.equals("solid")||lineType1.equals("")?"":",lty=\""+lineType1+"\"")+
                                         ((symbolSize1.equals("1.0")||symbolSize1.equals(""))?",cex=2":",cex=2*"+symbolSize1)+

                                          (traditionalPlotBeginning2.equals("in avPlots")||traditionalPlotBeginning2.equals("in crPlots")?
                                            (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",lwd="+linePoints2linewidth)+
                                            (linePoints3color.equals("")?
                                               (linePoints2color.equals("red")?"":",col.lines=\""+linePoints2color+"\")")
                                              :
                                               ((linePoints2color.equals("red")&linePoints3color.equals("green"))?"":
                                                ",col.lines=c(\""+linePoints2color+"\",\""+linePoints3color+"\")")
                                               )+
                                            (showGrid?"":",grid=FALSE")+
                                               (whichPointsToLabel.equals("none")?"":
                                                   (whichPointsToLabel.equals("all")?
                                                      "\ntext("+traditionalPlotFormula+",labels="+labels+",pos=4,cex="+pointLabelTextSize+",offset=0.5+"+(symbolSize1.equals("")?"1":symbolSize1)+"/4)"
                                                    : 
                                                      "\nshowLabels("+ xvalue +","+ yvalue +
                                                               ",labels="+labels+",id.method="+ extremenessBasisFormula + 
                                                                (whichPointsToLabel.equals("Pearson2")?"":",id.n ="+numExtremeValues)+")"

                                                     )) 

                                            :"")
                                       )+
                                      ")"+
                                      traditionalPlotEnd+            
                                           
                                       (traditionalPlotBeginning2.equals("")||singleGraphMulti||traditionalPlotBeginning2.equals("in avPlots")||
                                           traditionalPlotBeginning2.equals("in crPlots")?"":
                                           model.plotControl.traditionalPlotBeginning2[panelDealtWith]+",col=\""+linePoints2color+"\""+
                                           (lineType2.equals("solid")||lineType2.equals("")?"":",lty=\""+lineType2+"\"")+ 
                                           (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",lwd="+linePoints2linewidth)+")")+
                                           
                                      (traditionalPlotBeginning3.equals("")?"":
                                           model.plotControl.traditionalPlotBeginning3[panelDealtWith]+",col=\""+linePoints3color+"\""+
                                           (lineType3.equals("solid")||lineType3.equals("")?"":",lty=\""+lineType3+"\"")+ 
                                           (linePoints3linewidth.equals("1.0")||linePoints3linewidth.equals("")?"":",lwd="+linePoints3linewidth)+")")+
                                      
                                       (!linePoints1linewidth.equals("")&!linePoints2linewidth.equals("")&(singleGraphMulti||model.terms.getSize()==1)&
                                           !(((specificMethod=="ARIMA") & (!model.arimaOptions.predictAhead.equals("0"))) ||
                                              (((specificMethod.equals("Exponential Smoothing")&model.expSmoothingOptions.procedure.equals("Hyndman")) & (!model.expSmoothingOptions.predictAhead.equals("0")))))
                                           ?
                                                 "\nlegend(\""+keyPosition+"\",c(\""+legendText1+"\",\""+legendText2+"\""+
                                                 (model.terms.getSize()>=3?",\""+legendText3+"\"":"")+
                                                 (model.terms.getSize()>=4?",\""+legendText4+"\"":"")+
                                                 (model.terms.getSize()>=5?",\""+legendText5+"\"":"")+
                                                 (model.terms.getSize()>=6?",\""+legendText6+"\"":"")+
                                                 "  )"+
                                                (plotType1.equals("p")||plotType1.equals("b")||plotType1.equals("o")?
                                                         ",pch=c("+linePoints1Symbol.split(":")[0]+",NA)" /*)*/:"")+
                                                 
                                                  (ltyWording.equals("")?"":","+ltyWording)+
                                                  (lwdWording.equals("")?"":","+lwdWording)+
                                                  (colWording.equals("")?"":","+colWording)+")"
                                           :"")+    
                                           
                                       (traditionalPlotBeginning2.equals("in avPlots")||traditionalPlotBeginning2.equals("in crPlots")?"":
                                         (showGrid?"\ngrid(lwd = 2)":"")+ 
                                            (whichPointsToLabel.equals("none")?"":
                                               (whichPointsToLabel.equals("all")?
                                                  "\ntext(as.ts("+vNameAdjusted+"),labels="+labels+",pos=4,cex="+pointLabelTextSize+",offset=0.5+"+(symbolSize1.equals("")?"1":symbolSize1)+"/4)"
                                                : 
                                                  "\nshowLabels("+ xvalue +","+ yvalue +
                                                           ",labels="+labels+",id.method="+ extremenessBasisFormula + 
                                                            (whichPointsToLabel.equals("Pearson2")?"":",id.n ="+numExtremeValues)+")"
                                                 ))   
                                         
                                           );
                                
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

                              callforLog = latticePlotBeginning+
                                      
                                      (singleGraphMulti?",superpose=TRUE"+
                                          (mainTitle.equals("")||mainTitle.equals("_na_")?"":",main=\""+ mainTitle+"\"")+
                                          (yAxisTitle.equals("")||yAxisTitle.equals("_na_")?"":",ylab=\""+ yAxisTitle+"\"")+
                                          (xAxisTitle.equals("_na_")?"":",xlab=\""+ xAxisTitle+"\"")+
                                      (ltyWording.equals("")?"":","+ltyWording)+
                                      (lwdWording.equals("")?"":","+lwdWording)+
                                      (colWording.equals("")?"":","+colWording)+

                                         ",par.settings=list(superpose.line = list("+
                                         (ltyWording.equals("")?"":ltyWording)+
                                         (lwdWording.equals("")?"":(ltyWording.equals("")?"":",")+lwdWording)+
                                         (colWording.equals("")?"":","+colWording)
                                         
                                         +"))"+
                                         ",auto.key=list(text=c(\""+legendText1+"\",\""+legendText2+"\""+ 
                                         (model.terms.getSize()>=3?",\""+legendText3+"\"":"")+
                                         (model.terms.getSize()>=4?",\""+legendText4+"\"":"")+
                                         (model.terms.getSize()>=5?",\""+legendText5+"\"":"")+
                                         (model.terms.getSize()>=6?",\""+legendText6+"\"":"")
                                      
                                          +")"+",space=\""+keyPosition+"\""+
                                         (keyPosition.equals("top")||keyPosition.equals("bottom")?",columns=2":"")+
                                         (plotType1.equals("")?"":",type=c(\""+plotType1+"\",\"l\")")
                                         +")"
                                      
                                      : //*singleGraphMulti==false 
                                      (mainTitle.equals("")||mainTitle.equals("_na_")?"":",main=\""+ mainTitle+"\"")+
                                      (yAxisTitle.equals("")||yAxisTitle.equals("_na_")?"":",ylab=\""+ yAxisTitle+"\"")+
                                      (xAxisTitle.equals("_na_")?"":",xlab=\""+ xAxisTitle+"\"")+
                                      
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

                                         (model.terms.size()==1?
                                               (!linePoints1linewidth.equals("")&!linePoints2linewidth.equals("")?
                                                 ",par.settings=list(superpose.line = list(col = c(\""+linePoints1color+"\",\""+linePoints2color+"\")"+
                                                (linePoints1linewidth.equals("1")&linePoints2linewidth.equals("1")?"":
                                                      (plotType1.equals("")?"":",type=c(\""+plotType1.split(":")[0]+"\",\"l\")")+
                                                      ",lwd=c(\""+linePoints1linewidth+"\",\""+linePoints2linewidth+"\")")+
                                                (lineType1.equals("solid")&lineType2.equals("solid")?"":
                                                      ",lty=c(\""+lineType1+"\",\""+lineType2+"\")")
                                                 +"))"+
                                                 ",auto.key=list(text=c(\""+legendText1+"\",\""+legendText2+"\""+"),space=\""+keyPosition+"\""+
                                                 (keyPosition.equals("top")||keyPosition.equals("bottom")?",columns=2":"")+
                                                 (plotType1.equals("")?"":",type=c(\""+plotType1+"\",\"l\")")
                                                 +")"
                                              :
                                                      (linePoints1linewidth.equals("")?"":
                                                         (lineType1.equals("solid")||lineType1.equals("")?"":",lty=\""+lineType1+"\"")+ 
                                                         (linePoints1linewidth.equals("1.0")?"":",lwd="+linePoints1linewidth)
                                                       )+
                                                     (!linePoints1linewidth.equals("")&!linePoints1color.equals("")?",col.line=\""+linePoints1color+"\"":"")
                                               )
                                          :"")
                                      )+
                                      
                                      (manualxAxisRange&(!manualxAxisMinText.equals(""))&(!manualxAxisMinText.equals(""))?
                                      ",xlim=c("+manualxAxisMinText+","+manualxAxisMaxText+")":"")+
                                      (manualyAxisRange&(!manualyAxisMinText.equals(""))&(!manualyAxisMinText.equals(""))?
                                      ",ylim=c("+manualyAxisMinText+","+manualyAxisMaxText+")":"")+
                                      (showTickMarks?"":",scales = list(tck = c(-1,0))")+   
                                      (showGrid?",grid=TRUE":"")+
                                     (ltext.equals("")?"":",\n panel=function(...) {panel.xyplot(...)"+ltext/*+confIntvl95Text*/+"\n}")+
                                      ")";
                              call = "try(print("+callforLog+"))"; 
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
                                            (linePoints2linewidth.equals("1.0")||linePoints2linewidth.equals("")?"":",size="+linePoints2linewidth)+
                                             ")"
                                            
                                             ;
                            call = "try(print("+callforLog+"))"; 
                          }    

                  if (!cmd.equals("forecastFilterPanelPlot1"))
                  
                  callCommandsLog += "\n#*************altered plot ************************\n";
                  callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                  callCommandsLog += ((model.plotControl.preCommand[panelDealtWith].equals("")?"":model.plotControl.preCommand[panelDealtWith]+"\n")+
                                            callforLog+
                                      (model.plotControl.postCommand[panelDealtWith].equals("")?"":model.plotControl.postCommand[panelDealtWith])).replace(".hansel.working.env$", "");
                  
                  commandsLogText.setText(commandsLogText.getText() + callCommandsLog);
                  
                  if (!model.plotControl.preCommand[panelDealtWith].equals(""))
                              Deducer.eval(model.plotControl.preCommand[panelDealtWith]);
                  
                  if (cmd.equals("forecastFilterPanelPlot1"))
                      forecastFilterPanelPlot1 = new ModelPlotPanel(call); 
                  else
                      addedTab = new ModelPlotPanel(call); 
                  
                  if (!model.plotControl.postCommand[panelDealtWith].equals(""))
                              Deducer.eval(model.plotControl.postCommand[panelDealtWith]);
                  
                  
                   if (panelDealtWith==1) {
                     if (cmd.equals("forecastFilterPanelPlot1")) {
                       GeneralPlotsPanel1.add(forecastFilterPanelPlot1 );
                     } else {
                        GeneralPlotsPanel1.add(addedTab);
                     } 
                     GeneralPlotsPanel1.setVisible(true);
                  } else if (panelDealtWith==2) {
                     GeneralPlotsPanel2.add(addedTab);
                     GeneralPlotsPanel2.setVisible(true);
                  } else if (panelDealtWith==3) {
                     GeneralPlotsPanel3.add(addedTab);
                     GeneralPlotsPanel3.setVisible(true);
                   } else if (panelDealtWith==4) {
                     GeneralPlotsPanel4.add(addedTab);
                     GeneralPlotsPanel4.setVisible(true);
                   } else if (panelDealtWith==5) {
                     GeneralPlotsPanel5.add(addedTab);
                     GeneralPlotsPanel5.setVisible(true);
                   } else if (panelDealtWith==6) {
                     GeneralPlotsPanel6.add(addedTab);
                     GeneralPlotsPanel6.setVisible(true);
                      } 
        };       
        
        
     
        public void specifyClicked() {
                   NMTimeSDialog.runit();
                   this.dispose();
                  }
      
        public void exportClicked(){
            
		NMTimeSExplorerExport exp = new NMTimeSExplorerExport(this);
		exp.setLocationRelativeTo(this);
		exp.setVisible(true); 
                if (exp.hanselUTSExportOkay){
                  String cmd ="";
                 String temp = ".hansel.working.env$export.temp";
                 String namesString="";
                 String namesCmd;
                 boolean anyExport=false;
                 cmd = "";
                  if (exp.exportFitted) {
                      anyExport=true;       
                      cmd+="\n"+temp+"<-"+filterFittedValues;
                                namesCmd="rev(make.unique(c(names("+model.data+"),\"fitted_"+vName+"\")))[1]";
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                      
                      cmd+="\n if (nrow("+model.data+")>1){\n"+
                                         model.data+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+model.data+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                      if (existsTS){
                         cmd+=   "\n {"+model.dName+"<-merge("+model.dName+","+temp+")\n"+
                                         "colnames("+model.dName+")[ncol("+model.dName+")]<-"+"\""+namesString+"\"}";
                      }
                                
                  } 
                  if (exp.exportResiduals) {
                      anyExport=true;

                      cmd+="\n"+temp+"<-"+ filterOutputResiduals;
                                namesCmd="rev(make.unique(c(names("+model.data+"),\"residuals_"+vName+"\")))[1]";
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                      
                      cmd+="\n if (nrow("+model.data+")>1){\n"+
                                         model.data+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+model.data+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                      if (existsTS){
                        cmd+=   "\n "+model.dName+"<-merge("+model.dName+","+temp+")\n"+
                                         "colnames("+model.dName+")[ncol("+model.dName+")]<-"+"\""+namesString+"\"";
                      }          
                  }     
                  if (exp.exportMRAseries) {
                      anyExport=true;
                      namesCmd="c(paste(colnames("+temp+"),\"_\",\""+vName+"\",sep=\"\"))";
                      if (existsTS){
                       cmd+="\n"+temp+"<-ts(wavMRD("+filterOutput+"),start=start("+model.data+"__),frequency=frequency("+model.data+"__))";
                          
                      } else {
                       cmd+="\n"+temp+"<-ts(wavMRD("+filterOutput+"))";
                      }
                      cmd+= "\n if (nrow("+model.data+")>1){\n"+
                                         model.data+"[,"+namesCmd+"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+model.data+"[,"+namesCmd+"]<-"+""+namesCmd+"";
                      if (existsTS){
                      cmd+="\n colnames("+temp+")<-"+namesCmd+
                           "\n "+model.dName+"<-merge("+model.dName+","+temp+")}";
                      }
                   }     
                           
                  
                  
                  Deducer.execute(cmd);     
                }   
                
	}
        
        
       	class ModelListener implements ActionListener{
        		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
                        
                        if (!(filtersExplorer.getSelectedItem().equals("Choose change below"))&!noReactiontoAction){
                              Deducer.execute("library(latticeExtra)");
                              cmd=(String)filtersExplorer.getSelectedItem();
                              if (cmd.equals("No Smoother/Filter/Forecast")){
                                resultsAreaViewType.setEnabled(false);
                                editButton.setEnabled(true);
                                idLabelButton.setEnabled(true);
                              }else {
                                resultsAreaViewType.setEnabled(true);  
                              }
                              filtersExplorer.setSelectedItem("Choose change below");
                              resultsAreaViewChosen = "Plot: Fit/Forecasts";
                              resultsAreaViewsExplorer.setSelectedItem("Plot: Fit/Forecasts");            
                              filtertype.setPopupVisible(false);
                              
                        }
                        if (!resultsAreaViewChosen.equals((String)resultsAreaViewsExplorer.getSelectedItem())&!noReactiontoAction){
                              resultsAreaViewChosen = (String)resultsAreaViewsExplorer.getSelectedItem();
                              cmd=(String)resultsAreaViewsExplorer.getSelectedItem();
                              if (cmd.equals("Plot: Fit/Forecasts")){
                                editButton.setEnabled(true);
                                idLabelButton.setEnabled(true);  
                              }else {
                                editButton.setEnabled(false);
                                idLabelButton.setEnabled(false);
                              }
                        }
                        if(cmd==" Cancel "){  
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsTS,envir="+Hansel.hanselEnv+")");   
				cancel();
                        }else if(cmd=="Initial Selections Page"){
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsTS,envir="+Hansel.hanselEnv+")");   
				  cancel();
				specifyClicked();
			}else if(cmd=="Update Model"){
				updateClicked();
                        }else if(cmd=="No Smoother/Filter/Forecast"){
                            model.plotControl.traditionalPlotBeginning[1] = "plot("+vNameAdjusted;
                            model.plotControl.traditionalPlotBeginning2[1] = "";
                            model.plotControl.latticePlotBeginning[1] = "xyplot("+vNameAdjusted; 
                            
                            model.plotControl.pointLabels[1] = "row names";
                            model.plotControl.whichPointsToLabel[1] = "none";
                            model.plotControl.numExtremeValues[1] = "1";
                            model.plotControl.extremenessBasis[1] = "|y – mean(y)|";
                            model.plotControl.extremenessScrollChoices[1] = "1";
                            model.plotControl.extremenessScrollChoices[1] = "1";
                            model.plotControl.linePoints2linewidth[1] = "";
                            alterPlot(cmd);
			}else if ((cmd == "Polynomial, Fourier")||(cmd == "Moving Average")||
                                 (cmd ==  "Exponential Smoothing")||(cmd == "ARIMA")||(cmd == "GARCH")
                                ||(cmd == "Hodrick-Prescott")||(cmd == "Lowess/Loess")
                                ||(cmd == "Additive Seasonal")||(cmd == "Multiplicative Seasonal")
                                ||(cmd=="Kalman Filter")||(cmd=="Wavelet")) {
				forecastFilterClicked(cmd);
                                  model.plotControl.extremenessScrollChoices[1] = "3";
                        }else if(cmd == "Diagnostic Statistics & Tests"){
				diagnosticTestsClicked();
                        }else if ((cmd == "Text")){
                                  filterTextPanel.setVisible(true);
                                  GeneralPlotsPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);
                                  filterText.setVisible(true);
                        } else if ((cmd == "Plot vs time")){
                                filterLabel.setVisible(true);filtertype.setVisible(true);
                                resultsAreaViewLabel.setVisible(true);resultsAreaViewType.setVisible(true);
                                editButton.setVisible(true);
                                idLabelButton.setVisible(true);
                                exportButton.setVisible(true);
                                plotvsTimeButton.setSelected(true);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel4.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                if (resultsAreaViewChosen.equals("Plot: Fit/Forecasts"))
                                    GeneralPlotsPanel1.setVisible(true);
                                else if (resultsAreaViewChosen.equals("Plot: Decomposition"))
                                    forecastFilterPlotPanel2.setVisible(true);
                                else if (resultsAreaViewChosen.equals("Plot: Diagnostics"))
                                    forecastFilterPlotPanel3.setVisible(true);
                                else if (resultsAreaViewChosen.equals("Plot: Other 1"))
                                    forecastFilterPlotPanel4.setVisible(true);
                                else if (resultsAreaViewChosen.equals("Plot: Other 2"))
                                    forecastFilterPlotPanel5.setVisible(true);
                                else if (resultsAreaViewChosen.equals("Text"))
                                    filterTextPanel.setVisible(true);
                                
                                
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[1]){
                                  plotvsLagsClicked();
                                  seenPlotPanels[1] = true;   
                                }
                                  
                        } else if ((cmd == "Distribution")){
                                filterLabel.setVisible(false);filtertype.setVisible(false);
                                resultsAreaViewLabel.setVisible(false);resultsAreaViewType.setVisible(false);
                                editButton.setVisible(false);idLabelButton.setVisible(false);exportButton.setVisible(false);
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(true);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(true);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel4.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[2]){
                                  distributionClicked();
                                  seenPlotPanels[2] = true;   
                                }
                         
                        } else if ((cmd == "Correlograms")){
                                filterLabel.setVisible(false);filtertype.setVisible(false);
                                resultsAreaViewLabel.setVisible(false);resultsAreaViewType.setVisible(false);
                                editButton.setVisible(false);idLabelButton.setVisible(false);exportButton.setVisible(false);
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(true);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(true);
                                GeneralPlotsPanel4.setVisible(true);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[3]){
                                  correlogramsClicked();
                                  seenPlotPanels[3] = true;
                                  seenPlotPanels[4] = true;
                                }
                                  
                                  
                          
                         } else if ((cmd == "Plot vs Lags")){
                                filterLabel.setVisible(false);filtertype.setVisible(false);
                                resultsAreaViewLabel.setVisible(false);resultsAreaViewType.setVisible(false);
                                editButton.setVisible(false);idLabelButton.setVisible(false);exportButton.setVisible(false);
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(true);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel4.setVisible(false);
                                GeneralPlotsPanel5.setVisible(true);
                                GeneralPlotsPanel6.setVisible(false);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[5]){
                                  plotvsLagsClicked();
                                  seenPlotPanels[5] = true;   
                                }
                                
                                
                          } else if ((cmd == "Other Plots")){
                                filterLabel.setVisible(false);filtertype.setVisible(false);
                                resultsAreaViewLabel.setVisible(false);resultsAreaViewType.setVisible(false);
                                editButton.setVisible(false);idLabelButton.setVisible(false);exportButton.setVisible(false);
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(true);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel4.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(true);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                moreDiagnosticPlotsClicked();
                                seenPlotPanels[6] = true;                    
                                
                         } else if ((cmd == "Gen. Emp. fluct: M-test")){
                                filterLabel.setVisible(false);filtertype.setVisible(false);
                                resultsAreaViewLabel.setVisible(false);resultsAreaViewType.setVisible(false);
                                editButton.setVisible(false);idLabelButton.setVisible(false);exportButton.setVisible(false);
                                Deducer.eval("library(strucchange)"); 
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(true);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel4.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(true);
                                forecastFilterPlotPanel1.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel4.setVisible(false);
                                forecastFilterPlotPanel5.setVisible(false);
                                filterTextPanel.setVisible(false);
                                  for(int i=1;i<=12;i++){//Note that device 13, the one for this plot, is excluded.
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                specificSBTest="Gen. Emp. fluct: M-test";
                                if (!seenPlotPanels[13]){
                                  setStructuralBreakTestText();
                                  seenPlotPanels[13] = true; 
                                }
                         
                          } else if(cmd.equals("Plot Edit")){
                            PlotEditClicked();    
                          }else if(cmd.equals("main title")||cmd.equals("y-axis title")||
                                 cmd.equals("x-axis title")||cmd.equals("point labels-row names")||cmd.equals("point labels-values")||cmd.equals("point labels-none")||
                                  cmd.equals("data name in key")||cmd.equals("larger points")||cmd.equals("smaller points")||cmd.equals("no points")||
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
                                  cmd.equals("fit line longdash")||cmd.equals("fit line twodash"))
                                  {
				alterPlot(cmd);
                          }else if(cmd == "id-label"){
                              interactiveClicked("id-label");
                                
                         } else if ((cmd == "Plot: Fit/Forecasts")){
                             
                                  noReactiontoAction = true;
                                  filterTextPanel.setVisible(false);
                                  filterText.setVisible(false); 
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);
                                  GeneralPlotsPanel1.setVisible(false);
                                  GeneralPlotsPanel1.setVisible(true);
                            
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }                
                                  seenPlotPanels[7] = true; 
                                  noReactiontoAction = false;
                          }else if ((cmd == "Plot: Decomposition")){
                                  noReactiontoAction = true;
                                  filterTextPanel.setVisible(false);
                                  filterText.setVisible(false); 
                                  GeneralPlotsPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);

                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[8]){   
                                    if (!decompositionPrepCall.equals(""))
                                        Deducer.eval(decompositionPrepCall);
                                    if (decompositionCall.contains("xyplot("))
                                       forecastFilterPanelPlot2 = new ModelPlotPanel("print("+decompositionCall+")"); 
                                    else
                                       forecastFilterPanelPlot2 = new ModelPlotPanel(decompositionCall); 
                                    
                                    if (forecastFilterPlotPanel2.getComponentCount()>0)
                                        forecastFilterPlotPanel2.remove(0);
                                    forecastFilterPlotPanel2.add(forecastFilterPanelPlot2);
                                    Deducer.eval(plotPanelsDevNumsName+"[8] <- dev.cur()");
                                    seenPlotPanels[8] = true;
                                }
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(true);                 
                                noReactiontoAction = false;
                          }else if ((cmd == "Plot: Diagnostics")){
                                  noReactiontoAction = true;
                                  filterTextPanel.setVisible(false);
                                  filterText.setVisible(false); 
                                  GeneralPlotsPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);

                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                  
                                if (!seenPlotPanels[9]){  
                                    forecastFilterPanelPlot3 = new ModelPlotPanel(diagnosticscall.replace("    ","\n   "));
                                    if (forecastFilterPlotPanel3.getComponentCount()>0)
                                        forecastFilterPlotPanel3.remove(0);
                                    forecastFilterPlotPanel3.add(forecastFilterPanelPlot3);
                                    Deducer.eval(plotPanelsDevNumsName+"[9] <- dev.cur()");
                                    seenPlotPanels[9] = true;
                                }
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(true);
                                noReactiontoAction = false;
                          }else if ((cmd == "Plot: Other 1")){
                                  noReactiontoAction = true;
                                  filterTextPanel.setVisible(false);
                                  filterText.setVisible(false); 
                                  GeneralPlotsPanel1.setVisible(true);
                                  forecastFilterPlotPanel2.setVisible(true);
                                  forecastFilterPlotPanel3.setVisible(true);
                                  forecastFilterPlotPanel4.setVisible(true);
                                  forecastFilterPlotPanel5.setVisible(true);
                                  forecastFilterPlotPanel5.setVisible(true);
                                  GeneralPlotsPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);

                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[10]){ 
                                    if (forecastFilterPlotPanel4.getComponentCount()>0)
                                        forecastFilterPlotPanel4.remove(0);
                                    if (morePlotscall1.length()>0){
                                       forecastFilterPanelPlot4 = new ModelPlotPanel("\nprint("+morePlotscall1.replace("    ","\n   ")+")");
                                       forecastFilterPlotPanel4.add(forecastFilterPanelPlot4);
                                    }
                                    Deducer.eval(plotPanelsDevNumsName+"[10] <- dev.cur()");
                                    seenPlotPanels[10] = true;
                                }
                                 forecastFilterPlotPanel4.setVisible(false);
                                 forecastFilterPlotPanel4.setVisible(true); 
                                 noReactiontoAction = false;
                            }else if ((cmd == "Plot: Other 2")){
                                  noReactiontoAction = true;
                                  filterTextPanel.setVisible(false);
                                  filterText.setVisible(false); 
                                  GeneralPlotsPanel1.setVisible(true);
                                  forecastFilterPlotPanel2.setVisible(true);
                                  forecastFilterPlotPanel3.setVisible(true);
                                  forecastFilterPlotPanel4.setVisible(true);
                                  forecastFilterPlotPanel5.setVisible(false);
                                  GeneralPlotsPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel5.setVisible(false);

                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[11]){
                                    if (forecastFilterPlotPanel5.getComponentCount()>0)
                                        forecastFilterPlotPanel5.remove(0);
                                    if (morePlotscall2.length()>0){
                                       forecastFilterPanelPlot5 = new ModelPlotPanel("\nprint("+morePlotscall2.replace("    ","\n   ")+")");                               
                                       forecastFilterPlotPanel5.add(forecastFilterPanelPlot5);
                                    }
                                    Deducer.eval(plotPanelsDevNumsName+"[11] <- dev.cur()");
                                    seenPlotPanels[11] = true;
                                }
                                 forecastFilterPlotPanel5.setVisible(false);
                                 forecastFilterPlotPanel5.setVisible(true); 
                                 noReactiontoAction = false;
                       
                          }else if ((cmd == "Text/Plot Toggle")){//for unit roots test panel
                            noReactiontoAction = true;
                            for(int i=1;i<=13;i++){
                                      if (i==12||seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                            if (unitRootPlotPanel.isVisible()){
                                  unitRootPlotPanel.setVisible(false);
                                  unitRootTextPanel.setVisible(true);
                                  unitRootTextPanel.setVisible(true);
                            }else{
                                  unitRootTextPanel.setVisible(false);
                                  if (!seenPlotPanels[12]){  
                                   unitRootPlot = new ModelPlotPanel(callURPlot);
                                   unitRootPlotPanel.add(unitRootPlot);
                                   Deducer.eval(plotPanelsDevNumsName+"[12] <- dev.cur()");
                                   seenPlotPanels[12] = true;
                                  }
                                  unitRootPlotPanel.setVisible(true);            
                            }
                            noReactiontoAction = false;
                            
                            
                        }else if ((cmd == "Text/Plot Toggle ")){//for structural break test panel
                            noReactiontoAction = true;
                            if (structuralBreakPlotPanel.isVisible()){
                                  structuralBreakPlotPanel.setVisible(false);
                                  structuralBreakTextPanel.setVisible(true);
                                  structuralBreakTextPanel.setVisible(true);
                            }else{
                                  structuralBreakTextPanel.setVisible(false);
                                  for(int i=1;i<=13;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                  if (!seenPlotPanels[13]){  
                                   structuralBreakPlot = new ModelPlotPanel(callSBPlot);
                                   structuralBreakPlotPanel.add(structuralBreakPlot);
                                   Deducer.eval(plotPanelsDevNumsName+"[13] <- dev.cur()");
                                   seenPlotPanels[13] = true;
                                  }
                                  structuralBreakPlotPanel.setVisible(true);
                                  
                            }
                            if (structuralBreakPlotPanel.isVisible()) {
                                structuralBreakPlotPanel.repaint();
                                structuralBreakTextPanel.repaint();
                            }
                            noReactiontoAction = false;
                            
                            
                        }else if((cmd == "Augmented Dickey-Fuller")||(cmd=="ADF-GLS")||(cmd=="KPSS")||
                                 (cmd=="Phillips and Perron")||(cmd=="Schmidt and Phillips")||(cmd=="Zivot and Andrews")){
				unitRootTestClicked(cmd);
			}else if(cmd == "Post Hoc"){
				postHocClicked();
			}else if(cmd == "Prediction (Effects)"){
				meansClicked();
			}else if(cmd == "Additional Plots"){
				functionalFormClicked();
			}else if(cmd == "Tests on Coefficients"){
				testsClicked();
			}else if(cmd == "Export"){
				exportClicked();
			}else if(cmd =="Help"){
				helpClicked();
			}
			
		}
        }
}
