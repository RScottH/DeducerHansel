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
  ModelExplorer.java and GLMExplorer.java found in the Deducer package.
 
The current file made adjustments to that earlier java code on 2013-06-23 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-22.
 */

package hansel;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JLabel;


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



public class NMPanelSExplorer extends NMBasicExplorer implements WindowListener{
	
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected GMModel model = new GMModel();
	protected RModel pre;
        protected String[] ae;
        protected String specificDecomposition;
        protected String specificMethod ;
        protected String specificArimaGarch;
        protected String urTestText;        
        protected String specificURTest;
        protected String analysisVariablesPlusList;
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

        protected ModelPlotPanel unitRootTestcontent;
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
        protected JButton textPlot; 
        protected JButton zivotAndrews;
        protected JButton SchmidtPhillips;
        protected JPanel unitRootTestTab;
        protected JPanel unitRootTextPanel;
	protected JScrollPane unitRootTextScroller;
        protected JTextArea unitRootText;
        protected JTextArea decompText;
        protected JPanel filterTab;
        protected JPanel filterTextPanel;
        protected JTextArea arimaGarchText;
        protected JScrollPane arimaScroller;
        protected JPanel forecastFilterPlotPanel1;
        protected JPanel forecastFilterPlotPanel2;
        protected JPanel forecastFilterPlotPanel3;
        protected JPanel forecastFilterPlotPanel4;
        protected ModelPlotPanel forecastFilterPanelPlot1;
        protected ModelPlotPanel forecastFilterPanelPlot2;
        protected ModelPlotPanel forecastFilterPanelPlot3;
        protected ModelPlotPanel forecastFilterPanelPlot4;
        protected ModelPlotPanel unitRootPlot;
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
        public Boolean SeenLatestFilterPlotPanel1;
        public Boolean SeenLatestFilterPlotPanel2;
        public boolean[] seenPlotPanels = new boolean[12];
        public String d$vName;
        public String vName;
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
        protected String decompositionCall = "";
        protected String morePlotscall = "";
        protected String diagnosticscall = "";
        protected String callURPlot = "";
        
        protected String filterOutputResiduals = "";
        public Boolean existsTS;
        
        public String timeName ="";
        public String memberName="";
        public String dName="";
     
        
        protected ActionListener generalListener = new ModelListener();
        
        NMPanelSExplorer(GMModel mod, Boolean modelTSInput){
		super();
                if (mod.terms.size()==1){
		   this.setTitle("Univariate Panel Series Explorer");
                   this.setFont(font12);
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                   help.setEnabled(false);
                }
                else{
                  this.setTitle("Multivariate Panel Series Explorer");
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
                
                
                String[] memberAndTimeNames = new String[]{};
                  try {
                    memberAndTimeNames = Deducer.eval("names("+model.data+")").asStrings();
                    memberName = memberAndTimeNames[0];
                    timeName = memberAndTimeNames[1];
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }

                dName = model.data;
		try{
                               d$vName = dName+"$"+model.terms.get(0).toString();
                               vName = model.terms.get(0).toString();
                               
                String existsTSasString = new String();
                  try {
                    existsTSasString = Deducer.eval("as.character(exists(\"T.S."+dName+"\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                existsTS = existsTSasString.equals("TRUE");
                               
                        analysisVariablesPlusList = model.terms.get(0).toString();
                        String analysisVariables =   model.terms.get(0).toString();
                                 Integer numDashes = 0;
                if (model.start.contains("-"))
                    numDashes = model.start.split("-").length-1;
                if (model.end.contains("-"))
                    numDashes = Math.max(numDashes,model.end.split("-").length-1);
                Boolean obsFound = (model.start.contains("obs")||model.end.contains("obs"));              
                String asTime ="";
                if (numDashes==1){
                    if (model.start.split("-")[1].length()==1)
                    asTime = "as.yearqtr";
                    else
                    asTime = "as.yearmon";
                }
                else if (numDashes==2) 
                    asTime = "as.Date";
                                                          {

                                                                GeneralPlotsPanel1 = new JPanel(); 
								generalTab.add(GeneralPlotsPanel1, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel1.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel1.setBorder(BorderFactory.createTitledBorder("Trellis same scale"));							
                                                                GeneralPlotsPanel1.setVisible(true);

							}
               
                        
                        if (model.terms.getSize()>1) {
                            for(int i=1;i<model.terms.getSize();i++){
                            analysisVariablesPlusList = analysisVariablesPlusList + "+"+model.terms.get(i).toString();
                            analysisVariables = analysisVariables + ","+ model.terms.get(i).toString();
                            }
                           call = "print(xyplot("+analysisVariablesPlusList+"~"+timeName+"|"+memberName+",data="+dName+ ",type=\"a\",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)}"+
                                  ",scales=list(alternating=c(1,1),tck=c(1,0))"+
                                  ", strip = strip.custom(strip.names = TRUE, strip.levels = TRUE,style = 4),layout=c(4,3),par.strip.text = list(cex = 0.75)))";
                        
                        }else{
                           call = "print(xyplot("+vName+"~"+timeName+"|"+memberName+",data="+dName+",type=\"a\",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)}"+
                                  ",scales=list(alternating=c(1,1),tck=c(1,0))"+
                                  ", strip = strip.custom(strip.names = TRUE, strip.levels = TRUE,style = 4),layout=c(4,3),par.strip.text = list(cex = 0.75)))";
                            callCommandsLog += "\n\n#The following commands result in the output found in the General tab:\n";
                             callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                             callCommandsLog += call;                         
                        }
                    /*    Deducer.execute(call)*/ //debugging
                        addedTab = new ModelPlotPanel(call);
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
								GeneralPlotsPanel2.setBorder(BorderFactory.createTitledBorder("Trellis free scale"));
                                                                GeneralPlotsPanel2.setFont(font12);
                                                                GeneralPlotsPanel2.setVisible(false);
							}
                                                        {

                                                                GeneralPlotsPanel3 = new JPanel();
								generalTab.add(GeneralPlotsPanel3, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel3.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel3.setFont(font12);
								GeneralPlotsPanel3.setBorder(BorderFactory.createTitledBorder("Lag relationships: Original Series"));							
                                                                GeneralPlotsPanel3.setVisible(false);
							}
                                                        
                                                      
                                                        
                                                        {

                                                                GeneralPlotsPanel4 = new JPanel();
								generalTab.add(GeneralPlotsPanel4, new AnchorConstraint(545, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel4.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel4.setFont(font12);
								GeneralPlotsPanel4.setBorder(BorderFactory.createTitledBorder("Lag relationships: Original Series Squared"));							
                                                                GeneralPlotsPanel4.setVisible(false);
							} 
                                                        {

                                                                GeneralPlotsPanel5 = new JPanel();
								generalTab.add(GeneralPlotsPanel5, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel5.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel5.setFont(font12);
								GeneralPlotsPanel5.setBorder(BorderFactory.createTitledBorder("B&W per member"));							
                                                                GeneralPlotsPanel5.setVisible(false);
							}
                                                        {

                                                                GeneralPlotsPanel6 = new JPanel();
								generalTab.add(GeneralPlotsPanel6, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel6.setLayout(morePlotsPanelLayout);
                                                                GeneralPlotsPanel6.setFont(font12);
								GeneralPlotsPanel6.setBorder(BorderFactory.createTitledBorder("B&W per Time"));							
                                                                GeneralPlotsPanel6.setVisible(false);
							}

                                                        {
								plotvsTimeButton = new JButton();
								generalTab.add(plotvsTimeButton, new AnchorConstraint(10, 170, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotvsTimeButton.setText("Trellis same scale");
                                                                plotvsTimeButton.setFont(font12);
								plotvsTimeButton.setPreferredSize(new java.awt.Dimension(120, 26));
								plotvsTimeButton.addActionListener(generalListener);
							}   
                                                        {
								distributionButton = new JButton();
								generalTab.add(distributionButton, new AnchorConstraint(10, 350, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								distributionButton.setText("Trellis free scale");
                                                                distributionButton.setFont(font12);
								distributionButton.setPreferredSize(new java.awt.Dimension(120, 26));
								distributionButton.addActionListener(generalListener);
							}  
                                                        {
								correlogramButton = new JButton();
								generalTab.add(correlogramButton, new AnchorConstraint(10, 580, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								correlogramButton.setText("plot vs time, 1 figure");
                                                                correlogramButton.setFont(font12);
								correlogramButton.setPreferredSize(new java.awt.Dimension(150, 26));
								correlogramButton.addActionListener(generalListener);
							}  
                
                
                                                        {
								plotvsLagsButton = new JButton();
								generalTab.add(plotvsLagsButton, new AnchorConstraint(10, 780, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotvsLagsButton.setText("B&W per member");
                                                                plotvsLagsButton.setFont(font12);
								plotvsLagsButton.setPreferredSize(new java.awt.Dimension(120, 26));
								plotvsLagsButton.addActionListener(generalListener);
							}  
        
        
                                                        {
								structuralBreaksButton = new JButton();
								generalTab.add(structuralBreaksButton, new AnchorConstraint(10, 980, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								structuralBreaksButton.setText("B&W per Time");
                                                                structuralBreaksButton.setFont(font12);
								structuralBreaksButton.setPreferredSize(new java.awt.Dimension(130, 26));
								structuralBreaksButton.addActionListener(generalListener);
							}  
                                                        if (model.terms.getSize()>1) {
                                                            correlogramButton.setVisible(false);
                                                            plotvsLagsButton.setVisible(false);
                                                            structuralBreaksButton.setVisible(false);
                                                        }
                           AnchorLayout generalTabLayout = new AnchorLayout();                   
                        
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
                                                                unitRootTextPanel.setFont(font12);
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
                                                                unitRootPlotPanel.setFont(font12);
								unitRootPlotPanel.setPreferredSize(new java.awt.Dimension(700, 675));
                                                                unitRootPlotPanel.setVisible(false);
                                                                
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
			adf.setText("Cross-sect. Augmented IPS");
                        adf.setFont(font12);
			adf.setPreferredSize(new java.awt.Dimension(180, 35));
			adf.addActionListener(generalListener);
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
			cancelClear = new JButton();
			bottomPanel.add(cancelClear, new AnchorConstraint(30, 980, 780, 539, 
			AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
			AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
			cancelClear.setText(" Cancel ");
                        cancelClear.setFont(font12);
			cancelClear.setPreferredSize(new java.awt.Dimension(200, 35));
			cancelClear.addActionListener(generalListener);
                       }
                                
		}catch(Exception e){
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
                Deducer.eval("if (!exists(\".hansel.working.env\")) {\n" +
                 "    .hansel.working.env <- new.env(parent=emptyenv())\n" +
                     "}");
                try {
                    existsHanselWorkingEnv = Deducer.eval("as.character(exists(\".hansel.working.env\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                if (existsHanselWorkingEnv.equals("TRUE")) {
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsPS";
                }  else {
                    Deducer.eval(Hansel.hanselEnv+"<- new.env(parent=emptyenv())\n");
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsPS";
                }
                Deducer.eval("library(plm)");
                Deducer.eval(plotPanelsDevNumsName+" <-c(rep(\"\",11))"); 

                
	}

        public void arimaGarchPlotandText(GMModel mod){
		model = mod;
                String aeCall;
                String urTestText;
                String[] out = new String[]{};
                ArrayList tmp = new ArrayList();
                String timeSeriesObserved =  vName.toString();
                String filterCall = "";
                String filterTextOutput = "";
                String fitFilterCall = "";
                String fitDeterminationCall = "";
                String centreSimpleMAText = "";
                String polynFourierReg = "";
                
               arimaGarchText.removeAll();
               
             
               
               if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Simple"))) {
                         if (model.filterOptions.centreSimpleMA.TRUE) 
                              centreSimpleMAText = "TRUE"; 
                         else 
                              centreSimpleMAText = "FALSE";
                         String temp = Deducer.getUniqueName("tmp");
                         filterCall= "filterOutput <- ma("+timeSeriesObserved+",order=as.numeric("+model.filterOptions.simpleMAOrder+
                                                     "),centre="+ centreSimpleMAText  +")";                        
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = ""+timeSeriesObserved+"- filterOutput";
                         fitDeterminationCall = "filterFittedValues <- filterOutput";
                         
                         fitFilterCall = "plot.zoo( cbind("+timeSeriesObserved+",filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";
                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",filterFittedValues,"+filterOutputResiduals+")\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp,xlab=\"\"))";
               } else if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Additive Seasonal"))) {                     
                          filterCall= "filterOutput <- decompose(as.ts("+timeSeriesObserved+"),type=\"additive\")"; 
                          filterTextOutput = "summary(filterOutput)";
                          filterOutputResiduals = "filterOutput$random";
                          fitDeterminationCall = "filterFittedValues <-as.ts("+timeSeriesObserved+")-" + filterOutputResiduals;
                          fitFilterCall = "plot( cbind(as.ts("+timeSeriesObserved+"),filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";
                          decompositionCall = "plot(filterOutput,col=\"blue\")";
               } else if ((specificMethod=="Moving Average")&&(model.filterOptions.movingAverageType.equals("Multiplicative Seasonal"))) {
                          filterCall= "filterOutput <- decompose(as.ts("+timeSeriesObserved+"),type=\"multiplicative\")"; 
                          filterTextOutput = "summary(filterOutput)";
                          filterOutputResiduals = "filterOutput$random";
                          fitDeterminationCall = "filterFittedValues <- filterOutput$trend*filterOutput$seasonal";
                          fitFilterCall = "plot( cbind(as.ts("+timeSeriesObserved+"),filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";
                          decompositionCall = "plot(filterOutput,col=\"blue\")";
               } else if ((specificMethod=="Polynomial, Fourier")) {        
                   polynFourierReg = "lm("+timeSeriesObserved +"~";
                   for(int x = 1; x <= Integer.parseInt(model.filterOptions.polynomialOrder); x = x+1){
                       polynFourierReg = polynFourierReg + "I(time(ts("+timeSeriesObserved +"))^"+ x + ")";
                       if (x<=(Integer.parseInt(model.filterOptions.polynomialOrder)-1)) 
                           polynFourierReg = polynFourierReg + "+";
                    } 
                   if (Integer.parseInt(model.filterOptions.fourierOrder) > 0) {
                      if (Integer.parseInt(model.filterOptions.polynomialOrder) > 0);
                         polynFourierReg = polynFourierReg + "+";
                       for(int x = 1; x <= Integer.parseInt(model.filterOptions.fourierOrder); x = x+1){
                       polynFourierReg = polynFourierReg + "sin("+x+"*2*pi*time(ts("+timeSeriesObserved +")))"
                                                         + "+cos("+x+"*2*pi*time(ts("+timeSeriesObserved +")))";   
                       if (x<=(Integer.parseInt(model.filterOptions.polynomialOrder)-1)) 
                           polynFourierReg = polynFourierReg + "+";
                    }  
                      }   
                   polynFourierReg = polynFourierReg + ", na.action=NULL)";
                         filterCall = "filterOutput <- " + polynFourierReg; 
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = "filterOutput$residuals";
                         fitDeterminationCall = "filterFittedValues <- filterOutput$fit";                 
                         
                         fitFilterCall = "plot.zoo( cbind("+timeSeriesObserved+",filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";
                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",filterFittedValues,filterOutput$residuals)\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp,,xlab=\"\"))";

               } else if (specificMethod=="Exponential Smoothing"){
                         filterCall= "filterOutput <- ets( (if (is.ts("+timeSeriesObserved+")) "+timeSeriesObserved+" else ts("+timeSeriesObserved+")), model=\""+
                                          model.expSmoothingOptions.errorType+model.expSmoothingOptions.basictrendType+model.expSmoothingOptions.seasonalType+"\","+
                                          "damped="+model.expSmoothingOptions.damped+",opt.crit=\""+model.expSmoothingOptions.optimizationCriterion +
                                          "\",ic=\""+ model.expSmoothingOptions.infoCriterion+"\")";
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = "filterOutput$residuals";
                         fitDeterminationCall = "filterFittedValues <- fitted(filterOutput)";
                         fitFilterCall = "plot(forecast(filterOutput,h="+model.expSmoothingOptions.predictAhead+"))\n"+
                                      "   lines(filterFittedValues, col=\"red\")";  
                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",zoo(filterFittedValues,order.by=index("+timeSeriesObserved+")),zoo(filterOutput$residuals,order.by=index("+timeSeriesObserved+")))\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp))";
                         morePlotscall = "tsdiag(filterOutput)";
               } else if (specificMethod=="ARIMA"){
                         filterCall= "filterOutput <- Arima("+timeSeriesObserved+",order=c("+model.arimaOptions.arOrder+
                                          ","+model.arimaOptions.integrationOrder+","+model.arimaOptions.maOrder+"))";
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = "filterOutput$residuals";
                         fitDeterminationCall = "filterFittedValues <- fitted(filterOutput)";
                         fitFilterCall = "plot(forecast(filterOutput,h="+model.arimaOptions.predictAhead+"))\n"+
                                      "   lines(filterFittedValues, col=\"red\")";
                         
                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",zoo(filterFittedValues,order.by=index("+timeSeriesObserved+")),zoo(filterOutput$residuals,order.by=index("+timeSeriesObserved+")))\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp,xlab=\"\"))";
                         morePlotscall = "tsdiag(filterOutput)";
                } else if (specificMethod=="GARCH"){
                         filterCall= "filterOutput <- garch("+timeSeriesObserved+"-mean("+timeSeriesObserved+"),order=c("+model.filterOptions.garchOrder+
                                          ","+model.filterOptions.archOrder+"))";
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = "filterOutput$residuals";
                         fitDeterminationCall = "filterFittedValues <-mean("+timeSeriesObserved+")+" + filterOutputResiduals;
                         fitFilterCall = "plot.zoo( cbind("+timeSeriesObserved+",filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";

                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",filterFittedValues,"+filterOutputResiduals+")\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp,,xlab=\"\"))";
               } else if ((specificMethod=="Lowess/Loess")&&(model.filterOptions.loessType.equals("Simple Lowess"))) {
                         if (model.filterOptions.centreSimpleMA.TRUE) 
                              centreSimpleMAText = "TRUE"; 
                         else 
                              centreSimpleMAText = "FALSE";
                         filterCall= "filterOutput <- lowess("+timeSeriesObserved+",f="+model.filterOptions.lowessSpan+
                                                     ",iter="+model.filterOptions.lowessIter+")";
                         filterTextOutput = "summary(filterOutput)";
                         filterOutputResiduals = ""+timeSeriesObserved+"- filterOutput$y";
                         fitDeterminationCall = "filterFittedValues <- filterOutput$y";
                         fitFilterCall = "plot.zoo( cbind("+timeSeriesObserved+",filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";

                         decompositionCall = "HanselUTSExplorer_decomp_temp <- merge.zoo("+timeSeriesObserved+",filterFittedValues,filterOutput$residuals)\n"+
                                         "colnames(HanselUTSExplorer_decomp_temp)<-c(\""+timeSeriesObserved+"\",\"fitted values\",\"residuals\")\n"+
                                         "print(xyplot(HanselUTSExplorer_decomp_temp))";
               } else if ((specificMethod=="Lowess/Loess")&&(model.filterOptions.loessType.equals("Seasonal Decomposition by Loess (STL)"))) {                     
                          filterCall= "filterOutput <- stl("+model.terms.firstElement().toString()+"[,1],\"periodic\")"; 
                          filterTextOutput = "summary(filterOutput)";
                          filterOutputResiduals = "filterOutput$time.series[,3]";
                          fitDeterminationCall = "filterFittedValues <-"+timeSeriesObserved+"-" + filterOutputResiduals;
                          fitFilterCall = "plot("+timeSeriesObserved+")\n   lines(filterFittedValues, col=\"red\")";
                          decompositionCall = "   plot(filterOutput)";
       
               } else if ((specificMethod=="Wavelet")) {                     
                          filterCall= "filterOutput <- wav"+model.waveletOptions.waveletPresentation+"("+timeSeriesObserved+
                                    ",wavelet=\""+model.waveletOptions.waveletType+"\""+
                                     ",n.levels="+model.waveletOptions.waveletLevels+") \n"+
                                      "   filterOutputZerod1 <- filterOutput \n"+
                                      "   filterOutputZerod1[\"d1\"] <- 0";

                          
                          filterTextOutput = "filterOutput, summary(filterOutput)";
                          fitDeterminationCall =  "filterFittedValues <- reconstruct(filterOutputZerod1)";
                          filterOutputResiduals = "("+timeSeriesObserved+"-filterFittedValues)";
                          fitFilterCall = "plot.zoo( cbind("+timeSeriesObserved+",filterFittedValues) ,plot.type=\"single\",ylab=\""+(modelTSdata?model.efeglmextra.TSlabels:vName)+", fitted values\",xlab=\"\",col=c(3,2))";
                          decompositionCall = "par(mfrow = c(1, 1),mar=c(5,4,2,2))\n"+"   eda.plot(filterOutput)";

                          morePlotscall = "print(xyplot(zoo(ts(wavMRD(filterOutput),"+(existsTS?"start=start(T.S."+model.data+"),frequency=frequency(T.S."+model.data+")":"")+")),col=c(4),main=\"MRA for "+model.waveletOptions.waveletPresentation+" of "+vName +" using "+model.waveletOptions.waveletType+" filter\",,xlab=\"\"))";
               }  
                         Deducer.eval(filterCall);
                         String callCommandsLog = "\n\n###### Commands resulting in output in Smoothers,Filters,Forecasts tab: "+specificMethod+" ####";
                         callCommandsLog += "\n   #The initial smoother,filter,forecast call is the following:";
                         callCommandsLog += "\n   " + filterCall;  
                                aeCall = filterTextOutput;
                                callCommandsLog += "\n\n   #A text summary of the output and saving is given by the following command:";
                                callCommandsLog += "\n   "+ aeCall;      
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();                                    
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				tmp.add("\n>"+aeCall+"\n");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                Deducer.eval(fitDeterminationCall);
                                callCommandsLog += "\n\n   #Saving of filter fitted values is done  by the following command:";
                                callCommandsLog += "\n   "+ fitDeterminationCall;
                                                          
                                
                              forecastFilterPanelPlot1 = new ModelPlotPanel(fitFilterCall);
                               callCommandsLog += "\n\n   #The rest of the commands result in graphic output";
                               callCommandsLog += "\n   JavaGD() #Opens a new graphic device; the next line results in output found in \"Plot:Fit/Forecasts\"";
                               callCommandsLog += "\n   "+fitFilterCall;
                               callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output found in \"Decomposition\"";
                               callCommandsLog += "\n   "+decompositionCall;
                               if (morePlotscall.length()>0){
                                  callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output found in \"Plot Diagnostics 1\"";
                                  callCommandsLog += "\n   "+morePlotscall;
                               }
                               
                                seenPlotPanels[7] = false;
           
                                for(int i=2;i<=11;i++){
                                      if (seenPlotPanels[i]&(!( ((i==1)& plotvsTimeButton.isSelected())||((i==2)& distributionButton.isSelected())
                                                              ||(((i==3)||(i==4))& correlogramButton.isSelected())||((i==5)& plotvsLagsButton.isSelected())
                                                              ||((i==6)& structuralBreaksButton.isSelected())||((i==11)& unitRootPlotPanel.isVisible())
                                                              ) 
                                              )) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (forecastFilterPlotPanel1.getComponentCount()>0)
                                    forecastFilterPlotPanel1.remove(0);
                                forecastFilterPlotPanel1.add(forecastFilterPanelPlot1);
                                SeenLatestFilterPlotPanel1 = false;
                                Deducer.eval(plotPanelsDevNumsName+"[7] <- dev.cur()");
                                seenPlotPanels[7] = true;

                                   diagnosticscall="par(mfrow = c(2, 2),mar=c(5,4,2,2))\n"+
                                    "   hist("+filterOutputResiduals+", freq=FALSE, col= \"light blue\", main=\"Residual\",xlab=\"Residuals\")\n"+
                                    "   curve(dnorm(x, mean=mean(as.numeric(na.omit("+filterOutputResiduals+"))),sd=sd(as.numeric(na.omit("+filterOutputResiduals+")))), add=TRUE, lty=2, col=\"red\")\n"+
                                    "   qqnorm(as.numeric("+filterOutputResiduals+"),ylab=\"Residual Quantiles\");qqline(as.numeric(filterFittedValues) ,col=2)\n"+
                                    "   plot(as.numeric(filterFittedValues ),as.numeric("+filterOutputResiduals+"),xlab=\"Fitted values\",ylab=\"Residuals\"); abline(0,0)\n"+
                                    "   lines(lowess(as.numeric(na.omit(filterFittedValues)) ,as.numeric(na.omit("+filterOutputResiduals+"))), col = 2)";
                                   callCommandsLog += "\n\n   JavaGD() #Opens a new graphic device; the next line results in output found in \"Plot Diagnostics 2\"";
                                   callCommandsLog += "\n   "+diagnosticscall;

                commandsLogText.setText(commandsLogText.getText()+callCommandsLog);
                String textout = "";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                arimaGarchText.setText(textout);
		arimaGarchText.setCaretPosition(0); 
                arimaGarchText.setVisible(false);
                filterTextPanel.setVisible(false);
                forecastFilterPlotPanel2.setVisible(true);
                forecastFilterPlotPanel3.setVisible(true);
                forecastFilterPlotPanel4.setVisible(true);
                //The next statements erase any problem from an overshadowing previous window
                forecastFilterPlotPanel1.setVisible(false);
                forecastFilterPlotPanel1.setVisible(true);

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

        public void setUnitRootTestText(NMPanelUnitRootTestOptions purTestOptions){
                String call ="";
                String aeCall="";
                String urTestText;
                String[] out = new String[]{};
                ArrayList tmp = new ArrayList();
                String timeSeriesObserved =  vName.toString();
                
                String urtOutputName = Deducer.getUniqueName("urtOutputPS");
                
                
                 callURPlot = "par(mfrow = c(1, 1),mar=c(5,4,2,2))\n   plot(.hansel.working.env$"+urtOutputName+")";
                 
                 String callCommandsLog = "\n\n######Commands resulting in output in Unit Root Tests tab:"+specificURTest+"####";
                 callCommandsLog += "   #\n   #The initial unit root test call and saving of resulting object is the following:";
                 
                 if(specificURTest=="Cross-sect. Augmented IPS"){

                     
                     unitRootText.removeAll();
                     unitRootPlotPanel.removeAll();
                     seenPlotPanels[11]=false;
                     for(int i=0;i<model.terms.getSize();i++){

                         call=    urtOutputName + " <- cipstest("+
                                 (purTestOptions.differencingChoice.equals("Use 1st difference")?"diff(pdata.frame("+model.data+")$"+model.terms.get(i).toString()+")":
                                  (purTestOptions.differencingChoice.equals("Use 2nd difference")?"diff(pdata.frame("+model.data+")$"+model.terms.get(i).toString()+",differences = 2)":
                                  "pdata.frame("+model.data+")$"+model.terms.get(i).toString()))   
                                  +",type = \""
                                  +purTestOptions.deterministicComponentChoice
                                  +"\",lags="+purTestOptions.maxLagChoice
                                  +",model = \""+purTestOptions.cipsModelTypeChoice
                                  +"\""+((purTestOptions.truncatedChoice)?",truncated=TRUE":"")+")";
                

                Deducer.eval(".hansel.working.env$"+call);
                        aeCall = urtOutputName;
                        try {    
                             out = Deducer.eval("capture.output(.hansel.working.env$"+urtOutputName+ ")").asStrings();                                    
                        } catch (REXPMismatchException e) {
                        new ErrorMsg(e);
                        }

                        tmp.add("\n>"+aeCall+"\n");
                        for(int j=0;j<out.length;j++)
                                tmp.add(out[j]);       

                 callCommandsLog += "\n   " + call;
                 callCommandsLog += "\n\n   #A text summary of the output is given by the following command:";
                 callCommandsLog += "\n   "+ aeCall;
                }   
                /* callCommandsLog += "\n\n   #The rest of the commands result in graphic output";
                 callCommandsLog += "\n   JavaGD() #Opens a new graphic device; the next line results in the plotting of residuals, ACF, and PACF";
                 callCommandsLog += "\n   "+ callURPlot.replace(".hansel.working.env$", "");*/
                 commandsLogText.setText(commandsLogText.getText()+callCommandsLog);
                 }

                String textout = "!!";
		for(int i =0;i<tmp.size();i++)
			textout+=tmp.get(i)+"\n";
                this.unitRootText.setText(textout);
		this.unitRootText.setCaretPosition(0);
                unitRootPlotPanel.setVisible(false);
                unitRootTextPanel.setVisible(false);
                unitRootTextPanel.setVisible(true);
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
                if (model.efeaeplots.resvFitted == true)
                {try{

                    String call = "try(plot("+pre.modelName+",1,sub.captions=\"\"))";
			addexpTab = new ModelPlotPanel(call);
			tabs.setComponentAt(3,addexpTab);
                        tabs.setFont(font12);
                        tabs.setSelectedComponent(addexpTab);
                 
		}catch(Exception e){
			new ErrorMsg(e);
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

        public void unitRootTestClicked(String specificTest){
                specificURTest = specificTest;
		NMPanelUnitRootTestOptions purOptions = new NMPanelUnitRootTestOptions(this,model,pre,specificURTest);
		purOptions.setLocationRelativeTo(this);
		purOptions.setVisible(true);	
                purOptions.setModel(model, pre);//This resets choices to false.
      
                if (purOptions.unitRootTestOptionsOkay) { 
		   setUnitRootTestText(purOptions); 
                   purOptions.unitRootTestOptionsOkay = false;
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
                   String call = "print(xyplot("+analysisVariablesPlusList+"~"+timeName+"|"+memberName+",data="+dName+ ",type=\"a\",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)}"+
                                  ",scales=list(y=list(relation=\"free\"),alternating=c(1,1),tck=c(1,0))"+
                                  ", strip = strip.custom(strip.names = TRUE, strip.levels = TRUE,style = 4),layout=c(4,3),par.strip.text = list(cex = 0.75)))";
     
			distributionTab = new ModelPlotPanel(call);
			GeneralPlotsPanel2.add(distributionTab);
                        Deducer.eval(plotPanelsDevNumsName+"[2] <- dev.cur()");
	}
        
            public void correlogramsClicked(){

                       String call="trellis.par.set(superpose.symbol=list(pch=c(1:10)),superpose.line=list(lty=c(1:10)))"+
                                   "\n print(xyplot("+vName+"~"+timeName+",data="+dName+ ",groups="+memberName+",type=c(\"smooth\",\"g\",\"p\"),main=\""+vName+" by "+ memberName+", with smooth curve\","+
                                   "auto.key = list(space = \"right\",title=\""+memberName+"\")))";
			levelRelationsPanelcontent = new ModelPlotPanel(call);
			GeneralPlotsPanel3.add(levelRelationsPanelcontent);
                        Deducer.eval(plotPanelsDevNumsName+"[3] <- dev.cur()");
 
	}    
       public void plotvsLagsClicked(){
                       String call="print(bwplot("+vName+"~"+"factor("+memberName+"),xlab=\""+memberName+"\",data="+dName+"))";  
			plotvslagscontent = new ModelPlotPanel(call);
			GeneralPlotsPanel5.add(plotvslagscontent);
                        Deducer.eval(plotPanelsDevNumsName+"[5] <- dev.cur()");
                          
	}
       
     public void structuralBreaksPlotClicked(){
                        String call="print(bwplot("+vName+"~"+"factor("+timeName+"),xlab=\""+timeName+"\",data="+dName+"))";                   
			strucchangeTab = new ModelPlotPanel(call);
			GeneralPlotsPanel6.add(strucchangeTab);
                        Deducer.eval(plotPanelsDevNumsName+"[6] <- dev.cur()");
	}
        
        public void specifyClicked() {
                   NMPanelSDialog.runit();
                   this.dispose();
                  }
      
        public void exportClicked(){
		NMTimeSExplorerExport exp = new NMTimeSExplorerExport(this);
		exp.setLocationRelativeTo(this);
		exp.setVisible(true);
                if (exp.hanselUTSExportOkay){
                  String cmd ="";
                  String temp = Deducer.getUniqueName("tmp");
                 String namesString="";
                 String namesCmd;
                 boolean anyExport=false;
                 cmd = "";
                  if (exp.exportFitted) {
                      anyExport=true;       
                      cmd+="\n"+temp+"<-filterFittedValues";
                                namesCmd="rev(make.unique(c(names("+model.data+"),\"fitted_"+vName+"\")))[1]";
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                      
                      cmd+="\n if (nrow("+model.data+")>1){\n"+
                                         model.data+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+model.data+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                      cmd+="\n if (exists(\"T.S."+model.data+"\"))"+
                           "\n {T.S."+model.data+"<-merge(T.S."+model.data+","+temp+")\n"+
                                         "colnames(T.S."+model.data+")[ncol(T.S."+model.data+")]<-"+"\""+namesString+"\"}";
                                
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
                      cmd+="\n if (exists(\"T.S."+model.data+"\"))"+
                           "\n {T.S."+model.data+"<-merge(T.S."+model.data+","+temp+")\n"+
                                         "colnames(T.S."+model.data+")[ncol(T.S."+model.data+")]<-"+"\""+namesString+"\"}";
                                
                  }     
                  if (exp.exportMRAseries) {
                      anyExport=true;
                      namesCmd="c(paste(colnames("+temp+"),\"_\",\""+vName+"\",sep=\"\"))";
                      cmd+="\n if (exists(\"T.S."+model.data+"\")){"+
                           "\n"+temp+"<-ts(wavMRD(filterOutput),start=start(T.S."+model.data+"),frequency=frequency(T.S."+model.data+"))"+
                           "\n } else \n"+temp+"<-ts(wavMRD(filterOutput))"+ 
                      "\n if (nrow("+model.data+")>1){\n"+
                                         model.data+"[,"+namesCmd+"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+model.data+"[,"+namesCmd+"]<-"+""+namesCmd+"";
                      cmd+="\n if (exists(\"T.S."+model.data+"\")){"+
                           "\n colnames("+temp+")<-"+namesCmd+
                           "\n T.S."+model.data+"<-merge(T.S."+model.data+","+temp+")}";
                  }     
                           
                  
                  
                  Deducer.execute(cmd);     
                }   
                
	}
        
        
       	class ModelListener implements ActionListener{
        		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
                        if(cmd==" Cancel "){  
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsPS,envir="+Hansel.hanselEnv+")");   
				cancel();
                        }else if(cmd=="Initial Selections Page"){
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsPS,envir="+Hansel.hanselEnv+")");   
				  cancel();
				specifyClicked();
			}else if(cmd=="Update Model"){
				updateClicked();
                        }else if(cmd == "Diagnostic Statistics & Tests"){
				diagnosticTestsClicked();
                        }else if ((cmd == "Text")){
                                  filterTextPanel.setVisible(true);
                                  forecastFilterPlotPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  arimaGarchText.setVisible(true);
                        } else if ((cmd == "Trellis same scale")){
                                plotvsTimeButton.setSelected(true);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(true);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[1]){
                                  plotvsLagsClicked();
                                  seenPlotPanels[1] = true;   
                                }
                                  
                        } else if ((cmd == "Trellis free scale")){
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(true);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(true);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[2]){
                                  distributionClicked();
                                  seenPlotPanels[2] = true;   
                                }
                         
                        } else if ((cmd == "plot vs time, 1 figure")){
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(true);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(true);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  } 
                                if (!seenPlotPanels[3]){
                                  correlogramsClicked();
                                  seenPlotPanels[3] = true;
                                  seenPlotPanels[4] = true;
                                }
                                  
                          
                         } else if ((cmd == "B&W per member")){
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(true);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(true);
                                GeneralPlotsPanel6.setVisible(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[5]){
                                  plotvsLagsClicked();
                                  seenPlotPanels[5] = true;   
                                }
                          
                         } else if ((cmd == "B&W per Time")){
                                plotvsTimeButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                plotvsLagsButton.setSelected(false);
                                structuralBreaksButton.setSelected(true);
                                GeneralPlotsPanel1.setVisible(false);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(true);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[6]){
                                  structuralBreaksPlotClicked();
                                  seenPlotPanels[6] = true; 
                                }
      
                                  
                                  
                         } else if ((cmd == "Plot: Fit/Forecasts")){
                                  filterTextPanel.setVisible(false);
                                  arimaGarchText.setVisible(false); 
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);
                                  forecastFilterPlotPanel1.setVisible(false);
                                  forecastFilterPlotPanel1.setVisible(true);                          
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                  
                                  seenPlotPanels[7] = true; 
                          }else if ((cmd == "Plot: Decomposition")){
                                  filterTextPanel.setVisible(false);
                                  arimaGarchText.setVisible(false); 
                                  forecastFilterPlotPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);

                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[8]){    
                                    forecastFilterPanelPlot2 = new ModelPlotPanel(decompositionCall);
                                    if (forecastFilterPlotPanel2.getComponentCount()>0)
                                        forecastFilterPlotPanel2.remove(0);
                                    forecastFilterPlotPanel2.add(forecastFilterPanelPlot2);
                                    Deducer.eval(plotPanelsDevNumsName+"[8] <- dev.cur()");
                                    seenPlotPanels[8] = true;
                                }
                                forecastFilterPlotPanel2.setVisible(false);
                                forecastFilterPlotPanel2.setVisible(true);                 

                          }else if ((cmd == "Plot: Diagnostics")){
                                  filterTextPanel.setVisible(false);
                                  arimaGarchText.setVisible(false); 
                                  forecastFilterPlotPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);

                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                  
                                if (!seenPlotPanels[9]){  
                                    forecastFilterPanelPlot3 = new ModelPlotPanel(diagnosticscall);
                                    if (forecastFilterPlotPanel3.getComponentCount()>0)
                                        forecastFilterPlotPanel3.remove(0);
                                    forecastFilterPlotPanel3.add(forecastFilterPanelPlot3);
                                    Deducer.eval(plotPanelsDevNumsName+"[9] <- dev.cur()");
                                    seenPlotPanels[9] = true;
                                }
                                forecastFilterPlotPanel3.setVisible(false);
                                forecastFilterPlotPanel3.setVisible(true);
                                
                          }else if ((cmd == "More Plots")){
 
                                  filterTextPanel.setVisible(false);
                                  arimaGarchText.setVisible(false); 
                                  forecastFilterPlotPanel1.setVisible(true);
                                  forecastFilterPlotPanel2.setVisible(true);
                                  forecastFilterPlotPanel3.setVisible(true);
                                  forecastFilterPlotPanel4.setVisible(true);
                                  forecastFilterPlotPanel1.setVisible(false);
                                  forecastFilterPlotPanel2.setVisible(false);
                                  forecastFilterPlotPanel3.setVisible(false);
                                  forecastFilterPlotPanel4.setVisible(false);

                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }

                                if (!seenPlotPanels[10]){  
                                    forecastFilterPanelPlot4 = new ModelPlotPanel(morePlotscall);
                                    if (forecastFilterPlotPanel4.getComponentCount()>0)
                                        forecastFilterPlotPanel4.remove(0);
                                    forecastFilterPlotPanel4.add(forecastFilterPanelPlot4);
                                    Deducer.eval(plotPanelsDevNumsName+"[10] <- dev.cur()");
                                    seenPlotPanels[10] = true;
                                }
                                 forecastFilterPlotPanel4.setVisible(false);
                                 forecastFilterPlotPanel4.setVisible(true); 
                                 
                       }else if ((cmd == "Text/Plot Toggle")){
                            if (unitRootPlotPanel.isVisible()){
                                  unitRootPlotPanel.setVisible(false);
                                  unitRootTextPanel.setVisible(true);
                                  unitRootTextPanel.setVisible(true);
                            }else{
                                  unitRootTextPanel.setVisible(false);
                                  
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                                 
                                  if (!seenPlotPanels[11]){  
                                   unitRootPlot = new ModelPlotPanel(callURPlot);
                                   unitRootPlotPanel.add(unitRootPlot);
                                   Deducer.eval(plotPanelsDevNumsName+"[11] <- dev.cur()");
                                   seenPlotPanels[11] = true;
                                  }
                                  unitRootPlotPanel.setVisible(true);
                            }
                        }else if((cmd == "Cross-sect. Augmented IPS")||(cmd=="ADF-GLS")||(cmd=="KPSS")||
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
