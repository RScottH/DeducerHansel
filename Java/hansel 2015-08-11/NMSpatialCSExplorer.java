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
 
The current file made adjustments to that earlier java code on 2013-07-04 to work with the DeducerHansel package.
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
import javax.swing.JTextPane;


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



public class NMSpatialCSExplorer extends NMBasicExplorer implements WindowListener{
	
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
        protected String analysisVariablesQuotesList;
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
        protected JButton moranPlotButton;
        protected JButton distributionButton;
        protected JButton correlogramButton;
        protected JButton mapButton;
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
        public String listwString="";

        public JTextPane previewTP;
        public Spplot spplotrun;
        public Panel5Visible panel5Visiblerun;
        public PreviewPanelVisible previewPanelVisiblerun;
     
        
        protected ActionListener generalListener = new ModelListener();
        
        NMSpatialCSExplorer(GMModel mod, Boolean modelTSInput){
		super();          
                if (mod.terms.size()==1){
		   this.setTitle("Univariate Spatial Data Explorer");
                   this.setFont(font12);
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                }
                else{
                  this.setTitle("Multivariate Spatial Data Explorer");
                  this.setFont(font12);
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
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
                
		try{    String dName = model.data;
                               dName = dName.replace("Time.Series.","T.S.");
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
                        analysisVariablesQuotesList = "\""+ model.terms.get(0).toString() + "\"";
                        
                        callCommandsLog += "\n# Attach statement to indicate dataset used (allows us to avoid repeating it as prefix):\n";
                        
                                                          {

                                                                GeneralPlotsPanel1 = new JPanel(); 
								generalTab.add(GeneralPlotsPanel1, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel1.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel1.setBorder(BorderFactory.createTitledBorder("Moran Plot"));
                                                                GeneralPlotsPanel1.setFont(font12);

							}

                         addedTab = new ModelPlotPanel(""); // this command is provided at this point because the following trellis command
                                                            //   requires a device to work with; otherwise it would open up JavaGD() 
                        String classOfspdfForModel= new String("");
                          try {
                            classOfspdfForModel = Deducer.eval("substring(class("+model.efeglmextra.spdfForModel+")[1],1,7)").asString();  
                            }catch(Exception e){
                            new ErrorMsg(e);
                            }
                         
                        Deducer.eval("trellis.par.set(sp.theme())" +
                         "\n arrow = list(\"SpatialPolygonsRescale\", layout.north.arrow(),offset = c(-76,34), scale = 0.5, which = 2)");
                    
                       if (classOfspdfForModel.equals("Spatial")){ 
         
                                                                   {
								previewPanel = new JPanel();
                                                                generalTab.add(previewPanel, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								previewPanel.setLayout(previewPanelLayout);
								previewPanel.setBorder(BorderFactory.createTitledBorder("map"));
								previewPanel.setPreferredSize(new java.awt.Dimension(500, 950));
								{
									previewScroller = new JScrollPane();
									previewPanel.add(previewScroller, BorderLayout.CENTER);
									{
                                                                            
										preview = new JTextArea();


                                                                               
                                                                        {
									        previewTP = new JTextPane();	
                                                                                previewTP.setContentType("text/html");

                                                                                String message = "Plotting...<br>"
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
							}
                       }
           
                      listwString = new String();
                      try {
                        listwString = Deducer.eval(".hansel.working.env$SPDrefs[[\""+model.data+"\"]]$listwFile1").asString();  
                        }catch(Exception e){
                        new ErrorMsg(e);
                        } 
                       call="print(moran.plot("+model.efeglmextra.spdfForModel+"$"+vName+","+listwString+",labels=as.character("+model.efeglmextra.spdfForModel+"$"+vName+"), pch=19))";  

                           
                             callCommandsLog += "\n\n#The following commands result in the output found in the General tab:\n";
                             callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                             callCommandsLog += call;                         
                    /*    Deducer.execute(call)*/ //debugging
                    
                            
                        addedTab = new ModelPlotPanel(call);
                        addedTab = new ModelPlotPanel("print("+call+")");
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
								GeneralPlotsPanel3.setBorder(BorderFactory.createTitledBorder("Lag relationships: Original Series"));							
                                                                GeneralPlotsPanel3.setFont(font12);
                                                                GeneralPlotsPanel3.setVisible(false);
							}
                                                        
                                                      
                                                        
                                                        {

                                                                GeneralPlotsPanel4 = new JPanel();
								generalTab.add(GeneralPlotsPanel4, new AnchorConstraint(545, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel4.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel4.setBorder(BorderFactory.createTitledBorder("Lag relationships: Original Series Squared"));							
                                                                GeneralPlotsPanel4.setFont(font12);
                                                                GeneralPlotsPanel4.setVisible(false);
							} 
                                                        {

                                                                GeneralPlotsPanel5 = new JPanel();
								generalTab.add(GeneralPlotsPanel5, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel5.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel5.setBorder(BorderFactory.createTitledBorder("map"));							
                                                                GeneralPlotsPanel5.setFont(font12);
                                                                GeneralPlotsPanel5.setVisible(false);
							}
                                                        
                                                        {

                                                                GeneralPlotsPanel6 = new JPanel();
								generalTab.add(GeneralPlotsPanel6, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel6.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel6.setBorder(BorderFactory.createTitledBorder("B&W per Time"));							
                                                                GeneralPlotsPanel6.setFont(font12);
                                                                GeneralPlotsPanel6.setVisible(false);
							}
                                                        

                                                        {
								moranPlotButton = new JButton();
								generalTab.add(moranPlotButton, new AnchorConstraint(10, 250, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								moranPlotButton.setText("Moran Plot");
                                                                moranPlotButton.setFont(font12);
								moranPlotButton.setPreferredSize(new java.awt.Dimension(120, 26));
								moranPlotButton.addActionListener(generalListener);
							}   
                                                        {
								distributionButton = new JButton();
								generalTab.add(distributionButton, new AnchorConstraint(10, 350, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								distributionButton.setText("Trellis free scale");
								distributionButton.setPreferredSize(new java.awt.Dimension(120, 26));
								distributionButton.addActionListener(generalListener);
                                                                distributionButton.setFont(font12);
                                                                distributionButton.setVisible(false);
							}  
                                                        {
								correlogramButton = new JButton();
								generalTab.add(correlogramButton, new AnchorConstraint(10, 580, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								correlogramButton.setText("plot vs time, 1 figure");
								correlogramButton.setPreferredSize(new java.awt.Dimension(150, 26));
								correlogramButton.addActionListener(generalListener);
                                                                correlogramButton.setFont(font12);
                                                                correlogramButton.setVisible(false);
							}  
                
                
                                                        {
								mapButton = new JButton();
								generalTab.add(mapButton, new AnchorConstraint(10, 780, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								mapButton.setText("map");
                                                                mapButton.setFont(font12);
								mapButton.setPreferredSize(new java.awt.Dimension(120, 26));
								mapButton.addActionListener(generalListener);
                                                                if (!(classOfspdfForModel.equals("Spatial"))) 
                                                                    mapButton.setEnabled(false);
							}  
        
        
                                                        {
								structuralBreaksButton = new JButton();
								generalTab.add(structuralBreaksButton, new AnchorConstraint(10, 980, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								structuralBreaksButton.setText("B&W per Time");
								structuralBreaksButton.setPreferredSize(new java.awt.Dimension(130, 26));
								structuralBreaksButton.addActionListener(generalListener);
                                                                structuralBreaksButton.setFont(font12);
                                                                structuralBreaksButton.setVisible(false);
							}  
                                                        if (model.terms.getSize()>1) {
                                                            correlogramButton.setVisible(false);
                                                            structuralBreaksButton.setVisible(false);
                                                        }
                        AnchorLayout generalTabLayout = new AnchorLayout();                   
                     
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
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsSP";
                }  else {
                    Deducer.eval(Hansel.hanselEnv+"<- new.env(parent=emptyenv())\n");
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsSP";
                }   

                Deducer.eval("library(plm)");
                Deducer.eval(plotPanelsDevNumsName+" <-c(rep(\"\",11))"); 
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
                       String call = "print(xyplot("+analysisVariablesPlusList+"~"+timeName+"|"+memberName+",type=\"a\",panel=function(x,y,...){panel.xyplot(x,y,...); panel.lmline(x,y,...)}"+
                                  ",scales=list(y=list(relation=\"free\"),alternating=c(1,1),tck=c(1,0))"+
                                  ", strip = strip.custom(strip.names = TRUE, strip.levels = TRUE,style = 4),layout=c(4,3),par.strip.text = list(cex = 0.75)))";
                            
			distributionTab = new ModelPlotPanel(call);
			GeneralPlotsPanel2.add(distributionTab);
                        Deducer.eval(plotPanelsDevNumsName+"[2] <- dev.cur()");
	}
        
            public void correlogramsClicked(){

                       String call="trellis.par.set(superpose.symbol=list(pch=c(1:10)),superpose.line=list(lty=c(1:10)))"+
                                   "\n print(xyplot("+vName+"~"+timeName+",groups="+memberName+",type=c(\"smooth\",\"g\",\"p\"),main=\""+vName+" by "+ memberName+", with smooth curve\","+
                                   "auto.key = list(space = \"right\",title=\""+memberName+"\")))";
			levelRelationsPanelcontent = new ModelPlotPanel(call);
			GeneralPlotsPanel3.add(levelRelationsPanelcontent);
                        Deducer.eval(plotPanelsDevNumsName+"[3] <- dev.cur()");
	}
            
       public class Spplot extends Thread {
           public void run(){
              plotvsLagsClicked();
              GeneralPlotsPanel5.setVisible(true);
              GeneralPlotsPanel5.repaint();     
           };
       }   
       
      public class Panel5Visible extends Thread {
           public void run(){

              GeneralPlotsPanel5.setVisible(true);
           };
       }    
      
      
              
      public class PreviewPanelVisible extends Thread {
           public void run(){
              GeneralPlotsPanel5.setVisible(false);
              previewPanel.setVisible(true);
           };
       }           
              
       public void plotvsLagsClicked(){
                               String call;
                    if (model.terms.getSize()>1) {
                            for(int i=1;i<model.terms.getSize();i++){
                            analysisVariablesPlusList = analysisVariablesPlusList + "+"+model.terms.get(i).toString();
                            analysisVariablesQuotesList = analysisVariablesQuotesList + ",\""+ model.terms.get(i).toString()+"\"";
                            }
                          call= "spplot("+model.efeglmextra.spdfForModel+",c("+analysisVariablesQuotesList+"), scales = list(draw = TRUE),sp.layout = list(arrow), as.table = TRUE)";
                        }else{  
                          call = "brks <- quantile("+model.efeglmextra.spdfForModel+"$"+vName+", seq(0,1,1/5))\n";
                          call+= "dens <- (2:length(brks))*3\n"+
                                  model.efeglmextra.spdfForModel+"@data$Hansel_quantiles <- dens[findInterval("+model.efeglmextra.spdfForModel+"@data$"+vName+", brks, all.inside=TRUE)]\n";
                          call+= "colorkey_list <- list(height = 0.4, width = 0.6,at=as.numeric(c(1,0.8,0.6,0.4,0.2,0)))";
                          Deducer.eval(call);
                          call="print(spplot("+model.efeglmextra.spdfForModel+", \""+vName+"\", main = \""+vName+"\",formula=dens[findInterval("+vName+", brks, all.inside=TRUE)]~"+vName+"+0,"+
                                 "key.space=list(x=0.2,y=0.9,corner=c(0,1)),colorkey=list(height = 0.4, width = 0.6,at=as.numeric(c(1,0.8,0.6,0.4,0.2,0))," +
                                 "labels=as.character(round(brks,2))),col.regions =rev(topo.colors(6))," +
                                 "scales = list(draw = FALSE),sp.layout = list(arrow), as.table = TRUE))";
                        }
      
			plotvslagscontent = new ModelPlotPanel("print("+call+")");
			GeneralPlotsPanel5.add(plotvslagscontent);
                        Deducer.eval(plotPanelsDevNumsName+"[5] <- dev.cur()");
                        GeneralPlotsPanel5.setVisible(true);
                          
	}
       
        
        public void specifyClicked() {
                   NMSpatialCSDialog.runit();
                   this.dispose();
                  }

        
       	class ModelListener implements ActionListener{
        		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
                        if(cmd==" Cancel "){  
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsCS,envir="+Hansel.hanselEnv+")");   
				cancel();
                        }else if(cmd=="Initial Selections Page"){
                                for(int i=1;i<=11;i++)
                                          Deducer.eval("dev.off("+plotPanelsDevNumsName+"["+i+"])");
                                Deducer.eval("rm(plotPanelsDevNumsCS,envir="+Hansel.hanselEnv+")");   
				  cancel();
				specifyClicked();
			}else if(cmd=="Update Model"){
				updateClicked();
                        } else if ((cmd == "Moran Plot")){
                                moranPlotButton.setSelected(true);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                mapButton.setSelected(false);
                                structuralBreaksButton.setSelected(false);
                                GeneralPlotsPanel1.setVisible(true);
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                previewPanel.setVisible(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                if (!seenPlotPanels[1]){
                                  plotvsLagsClicked();
                                  seenPlotPanels[1] = true;   
                                }
                                  
                          
                         } else if ((cmd == "map")){
                                mapButton.setSelected(true);
                                previewPanel.setVisible(false);
                                GeneralPlotsPanel1.setVisible(false); 
                                GeneralPlotsPanel2.setVisible(false);
                                GeneralPlotsPanel3.setVisible(false);
                                GeneralPlotsPanel5.setVisible(false);
                                GeneralPlotsPanel6.setVisible(false);
                                moranPlotButton.setSelected(false);
                                distributionButton.setSelected(false);
                                correlogramButton.setSelected(false);
                                
                                structuralBreaksButton.setSelected(false);
                                  for(int i=1;i<=11;i++){
                                      if (seenPlotPanels[i]) {
                                          Deducer.eval("if (!"+plotPanelsDevNumsName+"["+i+"]==\"\") {dev.off("+plotPanelsDevNumsName+"["+i+"]);"+plotPanelsDevNumsName+"["+i+"]<-\"\"}"); 
                                      }
                                  }
                                  
                                if (!seenPlotPanels[5]){
                                    GeneralPlotsPanel5.setVisible(false);
                                    previewPanel.setVisible(true);
                                    spplotrun = new Spplot();
                                    spplotrun.start();
                                    seenPlotPanels[5] = true; 
                                } else {
                                     GeneralPlotsPanel5.setVisible(true);
                                }
                                

			}else if(cmd =="Help"){
				helpClicked();
			}
			
		}
        }
}
