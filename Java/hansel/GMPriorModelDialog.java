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
  GLMExplorerPlots.java and GLMExplorerPostHoc.java found in the Deducer package
 and
  ExampleDialog.java, found in the DeducerPlugInExample package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Component; 
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;

import org.rosuda.deducer.toolkit.SingletonDJList;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.menu.SubsetDialog;
import org.rosuda.deducer.menu.SubsetPanel;
import org.rosuda.REngine.JRI.JRIEngine;
import org.rosuda.REngine.REXP;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.print.DocFlavor.INPUT_STREAM;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.widgets.*;


public class GMPriorModelDialog  extends JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        protected JPanel modelPanel;
        protected JScrollPane modelScroller;
        protected JList modelList;
        protected JPanel dataPanel;
        protected JScrollPane dataScroller;
        protected JList dataList;
        public DefaultListModel modelListTerms;
    
	public VariableSelectorWidget variableSelector;
	private OkayCancelPanel okayCancelPanel;
        private JCheckBox interceptIncluded ;
        protected SingleVariableWidget outcomePanel;
	private HelpButton helpButton;
        protected DJList numericList;
        protected DJList endogVarsList;
        protected DJList cointTestVarsList;
        protected DJList exogVarsList;
        protected DJList instrumentsList;
        protected DJList genericNewList;
        protected KeepVariableListWidget instrumentPanel;
        protected KeepVariableListWidget exogVarsPanel;
        protected SingletonDJList outcome;
        protected SingletonDJList weights;
        
        public static String lastDataName;
        protected ListModel lastOutcomeModel;
        public DefaultListModel termsMF; 
        
        protected JButton addExplanatory;
        protected JButton removeExplanatory;
        protected JButton addInstrument;
        protected JButton removeInstrument;
        
        protected JButton modelBuilder;
        protected JButton manualModelButton;
       	protected JPanel subsetPanel;
        protected SubsetPanel subset;
        protected JPanel panelIndexPanel;
        private JTextArea panelIndexText;
        protected JPanel startPanel;
        private JTextArea startText;
        protected JPanel endPanel;
        private JTextArea endText;       
        protected JLabel NoteOnData;
        protected JComboBox newVariables;
        protected JLabel lagVariablesPanelLabel;
        protected JComboBox lagVariablesPanel;
        protected JLabel typeLabel;
        protected JComboBox plmtype;
        protected JComboBox pvcmtype;
        protected JComboBox pgglstype;
        protected JComboBox pgmmtype;
        protected JComboBox pmgtype;
        protected JComboBox cipsURTtype;
        protected JLabel effectLabel;
        protected JComboBox plmeffect;
        protected JComboBox pvcmeffect;
        protected JComboBox pgglseffect;
        protected JComboBox pgmmeffect;
        protected JLabel vcovtypeLabel;        
        protected JComboBox glmvcovtype;
        protected JComboBox plmvcovtype;
        protected JComboBox pgmmvcovtype;
        public String currentDataName;
        protected JLabel cointTypeLabel;
        protected JRadioButton typeEigen;
        protected JRadioButton typeTrace;
 
        
        
        protected JLabel constantTrendLabel;
        protected JRadioButton includeNone;
        protected JRadioButton includeConstant;
        protected JRadioButton includeTrend;
        protected JRadioButton includeBoth;
        

        protected JLabel lrTransitoryLabel;
        protected JRadioButton longRun;
        protected JRadioButton transitory;
        
        
        protected JLabel varMaxLagLabel;
        protected JComboBox varMaxLag;
        protected JComboBox varMaxLagEG;
        
        protected JLabel infoCriterionLabel;
        protected JComboBox infoCriterion;
        protected JComboBox infoCriterion2;
        protected Boolean oldAction = false;
        public String startLagDiag = "0";
        public String endLagDiag = "0";
        public Boolean lagVariablesRun = false;
        
        
        protected JLabel cointOptionsLabel;
        protected JComboBox cointOption;
	private static GMPriorModelDialog  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;
 
        public static void runit(String EstMethod, String[] models ,String[] dataFrames ) {
            
                EstimationMethodDialog = EstMethod;

			theDialog = new GMPriorModelDialog();
                        theDialog.setVisible(true);

                            theDialog.modelList.setListData(models);
                            theDialog.dataList.setListData(dataFrames);
          
                   
                   theDialog.setTitle(EstimationMethodDialog);

                
		WindowTracker.addWindow(theDialog);

	}
          

        	public GMPriorModelDialog() {
		super();
		initGUI();
                resetModel();
	}      

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                        {
					modelPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					modelPanel.setLayout(varPanelLayout);
					getContentPane().add(modelPanel, new AnchorConstraint(101, 375, 725, 200, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					modelPanel.setBorder(BorderFactory.createTitledBorder("Highlight one model"));
					modelPanel.setFont(font12);
                                        modelPanel.setPreferredSize(new java.awt.Dimension(215, 130));
					{
						modelScroller = new JScrollPane();
						modelPanel.add(modelScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							modelList = new JList();
							modelScroller.setViewportView(modelList);
							modelList.setModel(varListModel);
						}
					}
				} 
                                
                                {
					NoteOnData = new JLabel();
					getContentPane().add(NoteOnData, new AnchorConstraint(500, 487, 822, 100,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					NoteOnData.setText("Selecting dataset below needed only if you wish to export to it.");
					NoteOnData.setFont(font12);
                                        NoteOnData.setPreferredSize(new java.awt.Dimension(400, 20));
				} 

                               {
					dataPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					dataPanel.setLayout(varPanelLayout);
					getContentPane().add(dataPanel, new AnchorConstraint(550, 375, 725, 200, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					dataPanel.setBorder(BorderFactory.createTitledBorder("Highlight associated dataset"));
					dataPanel.setFont(font12);
                                        dataPanel.setPreferredSize(new java.awt.Dimension(215, 100));
					{
						dataScroller = new JScrollPane();
						dataPanel.add(dataScroller, BorderLayout.CENTER);
						{
							ListModel varListData = 
								new DefaultListModel();
							dataList = new JList();
							dataScroller.setViewportView(dataList);
							dataList.setModel(varListData);
						}
					}
				} 
			
                        

			{
				
				helpButton = new HelpButton("pmwiki.php?n=Main.ExampleDialog");
				getContentPane().add(helpButton, new AnchorConstraint(940, 77, 980, 23, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				helpButton.setPreferredSize(new java.awt.Dimension(32, 32));
			}
                        
			{
				
				okayCancelPanel = new OkayCancelPanel(true, true, this);
				getContentPane().add(okayCancelPanel, new AnchorConstraint(926, 978, 980, 402, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
				okayCancelPanel.setPreferredSize(new java.awt.Dimension(307, 32));
                                okayCancelPanel.setFont(font12);
			}
                        

			
			this.setSize(500, 400);
                        this.setFont(font12);
                        this.setTitle(EstimationMethodDialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
        

	private void reset(){
                variableSelector.refreshDataNames();  
                interceptIncluded.setSelected(true);
                panelIndexText.setText("");
                startText.setText("");
                endText.setText("");   
	}

        
        
        public void reset(boolean resetOptions){
                outcome.setModel(new DefaultListModel());
		numericList.setModel(new DefaultListModel());
                endogVarsList.setModel(new DefaultListModel());
                cointTestVarsList.setModel(new DefaultListModel());
                exogVarsList.setModel(new DefaultListModel());
                instrumentsList.setModel(new DefaultListModel());
	}

    
	public void resetModel(){
            
		setModel(new GMModel());
	}
	
 
	public void setModel(GMModel mod){
            		model = mod;                
	}
	
	public void updateModel(){
            String familylink;
             model.efeglmextra.PriorModelName = modelList.getSelectedValue().toString();
             model.frequency_of_original_data ="1";
             model.start_of_original_data = "1";
             if (!dataList.isSelectionEmpty()){
                   model.data = dataList.getSelectedValue().toString();
                   String existsTSasString = new String();
                   try {
                    existsTSasString = Deducer.eval("as.character(exists(\"T.S."+model.data+"\"))").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                if (existsTSasString.equals("TRUE")) {
                    try {
                        model.frequency_of_original_data = Deducer.eval("as.character(frequency(T.S."+model.data+"))").asString();
                        model.start_of_original_data = Deducer.eval("as.character(start(T.S."+model.data+"))").asString();

                        }catch(Exception e){
                        new ErrorMsg(e);
                    }                 
                } 

                   
             }else {
                 String modelNameCall = "";
                   try {
                    modelNameCall = Deducer.eval(model.efeglmextra.PriorModelName+"$call").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                   if (modelNameCall.contains("data = ")){
                      modelNameCall = modelNameCall.split("data = ")[1];
                      if (modelNameCall.contains(","))
                          modelNameCall = modelNameCall.split(",")[0];
                      model.data = modelNameCall.replace(")","");
                      model.dName = model.data;
                   } else
                     model.data = "No dataframe given";
             }
                   
             try{
                model.method = Deducer.eval("class("+model.efeglmextra.PriorModelName+")[1]").asString();
               }catch(Exception e){
             new ErrorMsg(e);
             }
             if (model.method.equals("lm")){
                 Deducer.eval("suppressMessages(library(lmtest))\nsuppressMessages(library(xtable))\nsuppressMessages(library(sandwich))");
             } else if (model.method.equals("glm")){
                 String model_link = "";
                 try{
                    model_link = Deducer.eval("family("+model.efeglmextra.PriorModelName+")$link").asString();
                   }catch(Exception e){
                 new ErrorMsg(e);
                 }
               if (model_link.equals("logit")||model_link.equals("probit")){
                 Deducer.eval("suppressMessages(library(tonymisc))\nsuppressMessages(library(pscl))\nsuppressMessages(library(xtable))\nsuppressMessages(library(ROCR))");

               }
             } else if (model.method.equals("tobit")){
                 
                Deducer.eval("suppressMessages(library(AER))");   

               
             } else if (model.method.equals("ivreg")){
                 
                Deducer.eval("suppressMessages(library(AER))\nsuppressMessages(library(xtable))");   
                          
             } else if (model.method.equals("VAR")){
                 
                Deducer.eval("suppressMessages(library(vars))");
                          
             } else if (model.method.equals("ca.jo")||model.method.equals("ca.po") ){
                 
                 Deducer.eval("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                 
             } else if (model.method.equals("ca.EG")){
                 
                Deducer.eval("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                Deducer.eval("suppressMessages(library(dynlm))\nsuppressMessages(library(plm))");
                
             }else if (model.method.equals("plm")){
                Deducer.eval("suppressMessages(library(plm)\nsuppressMessages(library(xtable))\nsuppressMessages(library(AER))"); 
                          
             } else if (model.method.equals("pggls")||model.method.equals("pgmm")||model.method.equals("pcce")||model.method.equals("pmg")){
                 
                Deducer.eval("suppressMessages(library(plm))"); 
                          
             }              
             
             
             ;
             model.dependentVar="";
             if (model.method.equals("lm"))
                 model.EstimationMethod = "Ordinary Least Squares"; /* this will be switched to weighted least squares in HanselModel if weight exists*/
             else if (model.method.equals("ivreg"))
                 model.EstimationMethod = "Two-stage Least Squares";
             else if (model.method.equals("glm")){
                       familylink =  "";
                       try{
                         familylink = Deducer.eval(""+model.efeglmextra.PriorModelName+"$family$link").asString();
                       }catch(Exception e){
                         new ErrorMsg(e);
                        }
                       if (familylink.equals("logit"))
                           model.EstimationMethod = "Binary Logit";
                       else if (familylink.equals("probit"))
                           model.EstimationMethod = "Binary Probit";          
             } 
             else if (model.method.equals("varest")){
                 model.method = "VAR";
                 model.EstimationMethod = "Vector Autoregression";
             }
             else if (model.method.equals("ca.jo"))
                 model.EstimationMethod = "Cointegration Test - Johansen";
             else if (model.method.equals("ca.po"))
                 model.EstimationMethod = "Cointegration Test - Philips & Ouliaris";
             else if (model.method.equals("plm"))
                 model.EstimationMethod = "Panel - Basic";
             else if (model.method.equals("pvcm"))
                 model.EstimationMethod = "Panel - Variable Coefficients";
             else if (model.method.equals("pggls"))
                 model.EstimationMethod = "Panel - General Feasible GLS	";
             else if (model.method.equals("pgmm"))
                 model.EstimationMethod = "Panel - GMM (e.g. for dynamic equations)";             
             else if (model.method.equals("pcce"))
                 model.EstimationMethod = "Panel - Common Correlated Effects Pooled Estimator";
             else if (model.method.equals("pmg"))
                 model.EstimationMethod = "Panel - Mean Groups"; 
                 
             
             model.efeglmextra.previousEstimation = true;
             model.efeglmextra.classicView = false;

                

                if (model.EstimationMethod.equals("Two-Stage Least Squares")){
                        model.method="ivreg";
                        model.family ="";

                 } else if ((model.EstimationMethod.equals("Vector Autoregression"))||
                            (model.EstimationMethod.equals("Cointegration Test - Johansen"))||
                            (model.EstimationMethod.equals("Cointegration Test - Philips & Ouliaris"))||
                           (model.EstimationMethod.equals("Cointegration Test - Engle & Granger"))){
  
                        model.efeglmextra.classicView = true;
                   } 
	}
	public void setDataName(String dataName){
		if(!dataName.equals(variableSelector.getSelectedData())){
			variableSelector.setSelectedData(dataName);
		}
	}
	public boolean valid(){

		return true;
	}

	private void completed(){

	}

        public void continueClicked(){
            	updateModel();
		if(!valid())
			return;
	
                this.dispose();
                GMExplorer exp = new GMExplorer(model,"GLM",1f);
		exp.setLocationRelativeTo(this);
		exp.setVisible(true);
		WindowTracker.addWindow(exp);
		this.dispose();
	}
	public class ManualModelInput{
		public String input = "";
                public boolean keep = false;
                public boolean runregression = false;
	}

        
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

               if (cmd=="Run") {
                        continueClicked();
		}else if(cmd=="Cancel"){
			this.setVisible(false);
                }else if(cmd=="Reset"){
			reset();
	}
    }

}