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
  ModelExplorer.java and GLMExplorer.java (both dated 2012-12-05), found in the Deducer package
 and
  ExampleDialog.java (dated 2010-03-12), found in the DeducerPlugInExample package.
 
 The current file made adjustments to the Deducer and DeducerPlugInExample java code on 2015-03-13
 to work with the DeducerHansel package, with subsequent revision on  2015-07-30, 2015-08-22.
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



public class NMFinancialSExplorer extends NMBasicExplorer implements WindowListener{
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

        protected ActionListener generalListener = new ModelListener();
        
        NMFinancialSExplorer(GMModel mod, Boolean modelTSInput){
		super();
                if (mod.terms.size()==1){
		   this.setTitle("Univariate Time Series Explorer");
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                }
                else{
                  this.setTitle("Financial Time Series Explorer");
		   help.setUrl("pmwiki.php?n=Main.GeneralizedLinearModel");
                }    
                modelTSdata = modelTSInput;
		setModel(mod);
		initTabs();
		initAssumptions();
		this.addWindowListener(this);
	}
	
	public void initTabs(){
                String call;
                String callCommandsLog ="";
                
		try{
                                                          {

                                                                GeneralPlotsPanel1 = new JPanel(); 
								generalTab.add(GeneralPlotsPanel1, new AnchorConstraint(60, 950, 992, 50, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout morePlotsPanelLayout = new BorderLayout();
								GeneralPlotsPanel1.setLayout(morePlotsPanelLayout);
								GeneralPlotsPanel1.setBorder(BorderFactory.createTitledBorder("Chart"));
                                                                GeneralPlotsPanel1.setFont(font12);
                                                                GeneralPlotsPanel1.setVisible(true);

							}
               
                             call = "chartSeries("+model.data +")";
                             callCommandsLog += "\n\n#The following commands result in the output found in the General tab:\n";
                             callCommandsLog += "JavaGD() #Opens a new graphic device\n";
                             callCommandsLog += call;


                        addedTab = new ModelPlotPanel(call);
                        GeneralPlotsPanel1.add(addedTab);
                        Deducer.eval(plotPanelsDevNumsName+"[1] <- dev.cur()");
                        seenPlotPanels[1] = true;                                               

                                                        {
								plotvsTimeButton = new JButton();
								generalTab.add(plotvsTimeButton, new AnchorConstraint(10, 140, 117, 800, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
								plotvsTimeButton.setText("Chart");
                                                                plotvsTimeButton.setFont(font12);
								plotvsTimeButton.setPreferredSize(new java.awt.Dimension(90, 26));
								plotvsTimeButton.addActionListener(generalListener);
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
                                                                commandsLogPanel.setFont(font12);
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
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsFS";
                }  else {
                    Deducer.eval(Hansel.hanselEnv+"<- new.env(parent=emptyenv())\n");
                    plotPanelsDevNumsName = Hansel.hanselEnv+"$plotPanelsDevNumsFS";
                }
                Deducer.eval("library(forecast)\n library(urca)");
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
       
        public void specifyClicked() {
                   NMFinancialSDialog.runit();
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

                        }else if(cmd == "Diagnostic Statistics & Tests"){
				diagnosticTestsClicked();
                                 
			}else if(cmd =="Help"){
				helpClicked();
			}
			
		}
        }
}
