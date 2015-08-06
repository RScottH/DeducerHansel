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
  GLMDialog.java, GLMExplorerPostHoc.java, and ModelBuilder.java, found in the Deducer package
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
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;*/
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
/*import javax.swing.JFrame;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.widgets.*;*/
import org.rosuda.deducer.toolkit.SingletonDJList;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.menu.SubsetDialog;
import org.rosuda.deducer.menu.SubsetPanel;
import org.rosuda.REngine.JRI.JRIEngine;
import org.rosuda.REngine.REXP;
import org.rosuda.deducer.models.*;


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


public class GMDialog  extends JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	public VariableSelectorWidget variableSelector;
        private JCheckBox interceptIncluded ;
        private JCheckBox spatialLagDepIncluded;
        private JCheckBox panelTrendIncluded ;
        protected SingleVariableWidget outcomePanel;
	private KeepSingleVariableWidget weightPanel;
	private HelpButton helpButton;
	private KeepVariableListWidget contPanel;
        private KeepVariableListWidget endogVarsPanel;
        private KeepVariableListWidget cointTestVarsPanel;
        protected DJList numericList;
        protected DJList endogVarsList;
        protected DJList cointTestVarsList;
        protected DJList instrumentsList;
        protected DJList genericNewList;
        protected KeepVariableListWidget instrumentPanel;
        protected KeepVariableListWidget exogVarsPanel;
        protected KeepVariableListWidget cointExogVarsPanel;
        protected SingletonDJList outcome;
        protected SingletonDJList weights;
      	private ManualModelInput manModInput;
        
        public static String lastDataName;
        protected ListModel lastOutcomeModel;
        public DefaultListModel termsMF; 
        
        protected JButton addExplanatory;
        protected JButton removeExplanatory;
        protected JButton addInstrument;
        protected JButton removeInstrument;
        
        protected JButton resetButton;
        protected JButton listwButton;
        protected JButton runButton;
        protected JButton cancelButton;
        protected JButton modelBuilder;
        protected JButton manualModelButton;
       	protected JPanel subsetPanel;
        protected SubsetPanel subset;
        protected JPanel panelIndexPanel;
        private JScrollPane  panelIndexScroller;
        private JTextArea panelIndexText;
        
        protected JPanel manualPanel;
        private JScrollPane  manualScroller;
        private JTextArea manualText; 
                                            

        protected JPanel startPanel;
        private JScrollPane  startScroller;
        private JTextArea startText;
        protected JPanel endPanel;
        private JScrollPane  endScroller;
        private JTextArea endText;       
        protected JLabel newVariablesLabel;
        protected JComboBox newVariables;
        protected JLabel lagVariablesPanelLabel;
        protected JComboBox lagVariablesPanel;
        
        protected JLabel spremlErrorsLabel;
        protected JComboBox spremlErrors;
        protected JLabel spgmMomentsLabel;
        protected JComboBox spgmMoments;
        protected JLabel spatialMethodLabel;
        protected JComboBox spatialMethodType;
        protected JComboBox spgmMethodType;
        
        protected JLabel spatialErrorPanelLabel;
        protected JComboBox spatialErrorPanel;
        protected JLabel zeroPolicyLabel;
        protected JComboBox zeroPolicyType;
        
        private JCheckBox zerosInitval;
        private JCheckBox hess;
        private JCheckBox spatialError;
        private JCheckBox quiet;
        private JCheckBox notQuiet;
        private JCheckBox verbose;
        
        protected JLabel typeLabel;
        protected JComboBox plmtype;
        protected JComboBox pvcmtype;
        protected JComboBox pgglstype;
        protected JComboBox pgmmtype;
        protected JComboBox pccetype;
        protected JComboBox pmgtype;
        protected JComboBox spmltype;
        protected JComboBox spgmtype;
        protected JComboBox cipsURTtype;
        protected JLabel effectLabel;
        protected JComboBox plmeffect;
        protected JComboBox pvcmeffect;
        protected JComboBox pgglseffect;
        protected JComboBox pgmmeffect;
        protected JComboBox spmleffect;
        protected JLabel vcovtypeLabel;        
        protected JComboBox glmvcovtype;
        protected JComboBox tobitvcovtype;
        protected JComboBox plmvcovtype;
        protected JComboBox pgmmvcovtype;
        protected JComboBox stslsvcovtype;
        protected JComboBox gstslsvcovtype;
        public String currentDataName;
        protected JLabel cointTypeLabel;
        protected JRadioButton typeEigen;
        protected JRadioButton typeTrace;
        protected ButtonGroup cointTypeButtons;
        protected ButtonGroupWidget cointEGComponent;
        protected ButtonGroupWidget cointPOType;
        protected ButtonGroupWidget cointPOComponent;
        protected ButtonGroupWidget cointPOLag;
        
        protected JPanel leftLimitPanel;
        private JTextField leftLimitText;
        protected JPanel rightLimitPanel;
        private JTextField rightLimitText;
        
        
        protected JLabel constantTrendLabel;
        protected JRadioButton includeNone;
        protected JRadioButton includeConstant;
        protected JRadioButton includeTrend;
        protected JRadioButton includeBoth;
        protected ButtonGroup constantTrendButtons;
        
        protected ButtonGroup lrTransitoryButtons;
        
        protected JLabel seasonalLabel;
        protected JRadioButton noSeasonalDummies;
        protected JRadioButton quarterlyDummies;
        protected JRadioButton monthlyDummies;
        protected ButtonGroup seasonalButtons;
        
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
        public String libraryStatement = "";
        public Boolean libsNeedLoading_glm = true;
        public Boolean libsNeedLoading_ivreg = true;
        public Boolean libsNeedLoading_binaryResponse = true;
        public Boolean libsNeedLoading_tobit = true;
        public Boolean libsNeedLoading_errorsarlm = true;
        public Boolean libsNeedLoading_VAR = true;
        public Boolean libsNeedLoading_cajocapo = true;
        public Boolean libsNeedLoading_caEG = true;
        public Boolean libsNeedLoading_plm = true;
        public Boolean libsNeedLoading_panel = true;
        public float sizeAdjuster = 1;
        
        
        protected static DefaultComboBoxModel transforms  = new DefaultComboBoxModel(
				new String[] { "Highlight then choose","transform variables page","log", "square","cube","square root",
						"inverse","inverse of square","negative","lag 1 period","lags 1 to ..","1st difference","multiply 2 variables","2-way",
						"3-way","interaction","nested","as factor","as numeric","split","manual entry"});
        protected static DefaultComboBoxModel transformsPanel  = new DefaultComboBoxModel(
				new String[] { "Highlight then choose","lag(_,1)","lag(_,0:1)", "lag(_,1:2)","lag(_,start:end)"});
        protected static DefaultComboBoxModel plmeffects  = new DefaultComboBoxModel(
				new String[] { "individual","time","twoways"});
        protected static DefaultComboBoxModel pvcmeffects  = new DefaultComboBoxModel(
				new String[] { "individual","time"});
        protected static DefaultComboBoxModel pgglseffects  = new DefaultComboBoxModel(
				new String[] { "individual","time"});
        protected static DefaultComboBoxModel pgmmeffects  = new DefaultComboBoxModel(
				new String[] { "individual","twoways"});
        protected static DefaultComboBoxModel spmleffects  = new DefaultComboBoxModel(
				new String[] { "individual","time","twoways"});
       
       protected static DefaultComboBoxModel spremlErrorsList  = new DefaultComboBoxModel(
				new String[] { "semsrre", "semsr", "srre", "semre",
                                               "re", "sr", "sem","ols", "sem2srre", "sem2re"});
         protected static DefaultComboBoxModel spgmMomentsList  = new DefaultComboBoxModel(
				new String[] {"initial", "weights", "fullweights"});
        
       protected static DefaultComboBoxModel spatialMethodTypeList  = new DefaultComboBoxModel(
				new String[] { "eigen", "spam","Matrix_J","spam_update",
                                               "Matrix", "random effects: Amemiya","LU","Chebyshev","MC","moments",
                                               "SE_classic","SE_whichMin","SE_interp"
                			     });
       protected static DefaultComboBoxModel spgmMethodTypeList  = new DefaultComboBoxModel(
				new String[] { "w2sls", "b2sls", "g2sls", "ec2sls"
                			     });
               protected static DefaultComboBoxModel zeroPolicyTypeList  = new DefaultComboBoxModel(
                				new String[] { "NULL",
                                               "TRUE",
                                               "FALSE"
                                              });
        
        protected static DefaultComboBoxModel plmPanelTypes  = new DefaultComboBoxModel(
				new String[] { "pooled OLS", "within (\"fixed\") effects","random effects: Swar",
                                               "random effects: Walhus", "random effects: Amemiya",
                                               "random effects: Nerlove",
                				"first-differences","between"});
       protected static DefaultComboBoxModel pvcmPanelTypes  = new DefaultComboBoxModel(
				new String[] {"within (\"fixed\") effects","random effects"});
       protected static DefaultComboBoxModel spatialErrorPanelList  = new DefaultComboBoxModel(
				new String[] {"none","b","kkp"});
       protected static DefaultComboBoxModel spmlPanelTypes  = new DefaultComboBoxModel(
				new String[] {"pooling","within (\"fixed\") effects","random effects"});
       protected static DefaultComboBoxModel spgmPanelTypes  = new DefaultComboBoxModel(
				new String[] {"within (\"fixed\") effects","random effects"});
       protected static DefaultComboBoxModel pgglsPanelTypes  = new DefaultComboBoxModel(
				new String[] {"pooling","within (\"fixed\") effects","random effects","first-differences"});
       protected static DefaultComboBoxModel pgmmPanelTypes  = new DefaultComboBoxModel(
				new String[] {"1-step, difference GMM",
                                              "1-step, system GMM",
                                             "2-step, difference GMM","2-step, system GMM"});
       protected static DefaultComboBoxModel pccePanelTypes  = new DefaultComboBoxModel(
				new String[] {"standard residuals","cce residuals","ccemg residuals"});
       protected static DefaultComboBoxModel pmgPanelTypes  = new DefaultComboBoxModel(
				new String[] {"mean groups","demeaned mean groups","cce mean groups"});
      protected static DefaultComboBoxModel cipsURTPanelTypes  = new DefaultComboBoxModel(
				new String[] {"mean groups","demeaned mean groups","cce mean groups"});
        protected static DefaultComboBoxModel glmvcovsDialog  = new DefaultComboBoxModel(
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
        protected static DefaultComboBoxModel tobitvcovsDialog  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0",
                                               "HAC"
                                              });
       protected static DefaultComboBoxModel plmvcovsDialog  = new DefaultComboBoxModel(
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
      protected static DefaultComboBoxModel stslsvcovsDialog  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0",
                                               "HC1"
                                              });
      protected static DefaultComboBoxModel gstslsvcovsDialog  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                               "HC0"
                                              });

              
                 protected static DefaultComboBoxModel pgmmvcovsDialog  = new DefaultComboBoxModel(
                				new String[] { "No correction",
                                                "robust inference",       
                                              });
	
       protected static DefaultComboBoxModel varMaxLags  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
              protected static DefaultComboBoxModel cointMaxLagsEG  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
      protected static DefaultComboBoxModel cointMaxLags  = new DefaultComboBoxModel(
				new String[] { "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
       protected static DefaultComboBoxModel infoCriterions  = new DefaultComboBoxModel(
				new String[] {"None, just use given lag","AIC","HQ","SC","FPE",""});
        protected static DefaultComboBoxModel infoCriterions2  = new DefaultComboBoxModel(
				new String[] {"None, just use given lag","AIC","BIC",""});      
       
        protected JLabel cointOptionsLabel;
        protected JComboBox cointOption;
        protected static DefaultComboBoxModel cointOptionsAvailable  = new DefaultComboBoxModel(
				new String[] { "no constant or trend, transitory",
                                               "constant, transitory",
                                               "trend, transitory",
                                               "constant, long-run",
                                               "trend, long-run",
                                               "level-shift, transitory (no use of exogenous variables)",
                                               "level-shift with trend, transitory (no use of exog. variables)"});


	
	private Vector widgets;									//Collection of all the DeducerWidgets

	private static GMDialog  theDialog;
        private GMModel modelD = new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;


	public static void runit() {
		if(theDialog == null){
			theDialog = new GMDialog();
                        theDialog.setVisible(true);
		}


                theDialog.setVisible(true);

		WindowTracker.addWindow(theDialog);

	}

        
        public static void runit(String EstMethod, boolean isJGR) {
                EstimationMethodDialog = EstMethod; 
		if(theDialog == null){  
			theDialog = new GMDialog(isJGR);
                        theDialog.setVisible(true);
		}
                //Note: After the 1st time EFEGLMDialg is run, "theDialog" always exists,
                //so selections from the last time GMDialog is run are used unless ....reset() is used.

                else{
		   theDialog.setVisible(true);
                   theDialog.contPanel.setVisible(true);
                   theDialog.endogVarsPanel.setVisible(false);
                   theDialog.cointTestVarsPanel.setVisible(false);
                   theDialog.exogVarsPanel.setVisible(false); 
                   theDialog.cointExogVarsPanel.setVisible(false); 
                   theDialog.instrumentPanel.setVisible(false);
                   theDialog.panelIndexPanel.setVisible(false);
                   theDialog.infoCriterionLabel.setVisible(false);theDialog.infoCriterion.setVisible(false);theDialog.infoCriterion2.setVisible(false);
                   theDialog.cointOptionsLabel.setVisible(false);theDialog.cointOption.setVisible(false);
                   theDialog.cointPOType.setVisible(false);
                   theDialog.cointPOComponent.setVisible(false);
                   theDialog.cointPOLag.setVisible(false);
                   theDialog.cointEGComponent.setVisible(false);
                  /* theDialog.rankRestrictionLabel.setVisible(false);theDialog.rankRestriction.setVisible(false);*/
                   theDialog.varMaxLagLabel.setVisible(false); theDialog.varMaxLag.setVisible(false);  theDialog.varMaxLagEG.setVisible(false);                
                   theDialog.interceptIncluded.setVisible(false);
                   theDialog.spatialLagDepIncluded.setVisible(false);
                   theDialog.outcomePanel.setVisible(true);
                   theDialog.contPanel.setTitle("Explanatory Variables");
                   theDialog.cointTypeLabel.setVisible(false);theDialog.typeEigen.setVisible(false);theDialog.typeTrace.setVisible(false);
                   theDialog.constantTrendLabel.setVisible(false);
                      theDialog.includeNone.setVisible(false); theDialog.includeConstant.setVisible(false);
                      theDialog.includeTrend.setVisible(false);theDialog.includeBoth.setVisible(false);
                   theDialog.lagVariablesPanelLabel.setVisible(false);theDialog.lagVariablesPanel.setVisible(false);
                   theDialog.spatialErrorPanelLabel.setVisible(false);theDialog.spatialErrorPanel.setVisible(false);
                   theDialog.panelTrendIncluded.setVisible(false);

                   theDialog.seasonalLabel.setVisible(false);
                   theDialog.noSeasonalDummies.setVisible(false);theDialog.quarterlyDummies.setVisible(false);theDialog.monthlyDummies.setVisible(false);                                            
                   theDialog.vcovtypeLabel.setVisible(false); theDialog.glmvcovtype.setVisible(false); theDialog.tobitvcovtype.setVisible(false); theDialog.plmvcovtype.setVisible(false);theDialog.pgmmvcovtype.setVisible(false);
                   theDialog.stslsvcovtype.setVisible(false);theDialog.gstslsvcovtype.setVisible(false);
                   
                   theDialog.zeroPolicyLabel.setVisible(false); theDialog.zeroPolicyType.setVisible(false); 
                   theDialog.spatialMethodLabel.setVisible(false); theDialog.spatialMethodType.setVisible(false);theDialog.spgmMethodType.setVisible(false);
                   theDialog.spremlErrorsLabel.setVisible(false); theDialog.spremlErrors.setVisible(false); 
                   theDialog.spgmMomentsLabel.setVisible(false); theDialog.spgmMoments.setVisible(false); 
                   theDialog.zerosInitval.setVisible(false);theDialog.hess.setVisible(false);theDialog.spatialError.setVisible(false);
                   theDialog.quiet.setVisible(false);theDialog.notQuiet.setVisible(false);theDialog.verbose.setVisible(false);
                   
                   theDialog.effectLabel.setVisible(false);
                   theDialog.plmeffect.setVisible(false);theDialog.pvcmeffect.setVisible(false);theDialog.pgglseffect.setVisible(false);theDialog.pgmmeffect.setVisible(false);theDialog.spmleffect.setVisible(false);
                   theDialog.typeLabel.setVisible(false);
                   theDialog.plmtype.setVisible(false);theDialog.pvcmtype.setVisible(false);
                   theDialog.pgglstype.setVisible(false);theDialog.pgmmtype.setVisible(false);
                   theDialog.pccetype.setVisible(false);theDialog.pmgtype.setVisible(false);
                   theDialog.spmltype.setVisible(false);theDialog.spgmtype.setVisible(false);
                   theDialog.cipsURTtype.setVisible(false);
                   theDialog.listwButton.setVisible(false);
                   theDialog.startPanel.setVisible(true);theDialog.endPanel.setVisible(true);
                   theDialog.leftLimitPanel.setVisible(false);theDialog.leftLimitText.setVisible(false);theDialog.rightLimitPanel.setVisible(false);theDialog.rightLimitText.setVisible(false);
                   
                   if ((EstimationMethodDialog.equals("Ordinary Least Squares"))){    
                        /*theDialog.instrumentPanel.reset(); */
                        theDialog.weightPanel.setVisible(false);
                        theDialog.modelD.factorVars.removeAllElements();
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        if (theDialog.libsNeedLoading_glm){
                          Deducer.execute("suppressMessages(library(lmtest))\nsuppressMessages(library(xtable))\nsuppressMessages(library(sandwich))");
                          theDialog.libsNeedLoading_glm=false;
                        }  
                   } else if ((EstimationMethodDialog.equals("Two-Stage Least Squares"))){ 
                       theDialog.instrumentPanel.setVisible(true);
                       theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                       if (theDialog.libsNeedLoading_ivreg){
                          Deducer.execute("suppressMessages(library(AER))\nsuppressMessages(library(xtable))");
                          theDialog.libsNeedLoading_ivreg=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Weighted Least Squares")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        if (theDialog.libsNeedLoading_glm){
                          Deducer.execute("suppressMessages(library(lmtest))\nsuppressMessages(library(xtable))\nsuppressMessages(library(sandwich))");
                          theDialog.libsNeedLoading_glm=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Binary Logit")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        if (theDialog.libsNeedLoading_binaryResponse){
                          Deducer.execute("\nsuppressMessages(library(mfx))\nsuppressMessages(library(lmtest))\nsuppressMessages(library(pscl))\nsuppressMessages(library(xtable))\nsuppressMessages(library(ROCR))");
                          theDialog.libsNeedLoading_binaryResponse=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Binary Probit")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        if (theDialog.libsNeedLoading_binaryResponse){
                          Deducer.execute("\nsuppressMessages(library(mfx))\nsuppressMessages(library(lmtest))\nsuppressMessages(library(pscl))\nsuppressMessages(library(xtable))\nsuppressMessages(library(ROCR))");
                          theDialog.libsNeedLoading_binaryResponse=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Tobit")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.tobitvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.leftLimitPanel.setVisible(true);theDialog.leftLimitText.setVisible(true);theDialog.rightLimitPanel.setVisible(true);theDialog.rightLimitText.setVisible(true);
                        if (theDialog.libsNeedLoading_tobit){
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_tobit=false;
                        }      
                   } else if (EstimationMethodDialog.equals("Explore Cross-Sectional Spatial Data")) {
                        theDialog.weightPanel.setVisible(true);
                       // theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }
                     } else if (EstimationMethodDialog.equals("Spatial simultaneous autoregressive error model - maximum likelihood")){

                        theDialog.weightPanel.setVisible(true);
                       // theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }   
                     } else if (EstimationMethodDialog.equals("Error Durbin model - maximum likelihood")){
                        theDialog.weightPanel.setVisible(true);
                       // theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }   
                    } else if (EstimationMethodDialog.equals("Spatial simultaneous autoregressive error model - GMM")){
                        theDialog.modelD.method="GMerrorsar";
                        theDialog.modelD.family ="";
                        theDialog.weightPanel.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.verbose.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }
                    } else if (EstimationMethodDialog.equals("Spatial simultaneous autoregressive lag model - maximum likelihood")){
                        theDialog.modelD.method="lagsarlm";
                        theDialog.modelD.family ="";
                        theDialog.weightPanel.setVisible(true);
                      //  theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }     
                        
                     } else if (EstimationMethodDialog.equals("Spatial Durbin model - maximum likelihood")){
                        theDialog.weightPanel.setVisible(true);
                       // theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }  

                  } else if (EstimationMethodDialog.equals("Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood")){
                        theDialog.weightPanel.setVisible(true);
                      //  theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }
                   } else if (EstimationMethodDialog.equals("SAC/SARAR Durbin model - maximum likelihood")){
                        theDialog.weightPanel.setVisible(true);
                      //  theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.notQuiet.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }
                    } else if (EstimationMethodDialog.equals("Spatial simultaneous autoregressive SAC model - GMM")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.gstslsvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.verbose.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }     
                        
                   } else if (EstimationMethodDialog.equals("generalized spatial two-stage least squares")){
                        theDialog.weightPanel.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.stslsvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        } 
                    } else if (EstimationMethodDialog.equals("Spatial conditional and simultaneous autoregression")){
                        theDialog.weightPanel.setVisible(true);
                       // theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        } 
                  } else if (EstimationMethodDialog.equals("Matrix exponential spatial lag model")){
                        theDialog.weightPanel.setVisible(true);
                      /*  theDialog.vcovtypeLabel.setVisible(true); theDialog.glmvcovtype.setVisible(true);*/
                        theDialog.interceptIncluded.setVisible(true);
                        theDialog.listwButton.setVisible(true);
                        theDialog.zeroPolicyLabel.setVisible(true); theDialog.zeroPolicyType.setVisible(true); 
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spatialMethodType.setVisible(true);
                        theDialog.verbose.setVisible(true);
                        if (theDialog.libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          theDialog.libsNeedLoading_errorsarlm=false;
                        }                     
                  }   else if ((EstimationMethodDialog.equals("Vector Autoregression"))){
                        theDialog.modelD.method="VAR";
                        theDialog.outcomePanel.setVisible(false);
                        theDialog.contPanel.setVisible(false);
                        theDialog.endogVarsPanel.setVisible(true);
                        theDialog.exogVarsPanel.setVisible(true);
                        theDialog.infoCriterionLabel.setVisible(true);theDialog.infoCriterion.setVisible(true);
                        theDialog.varMaxLagLabel.setVisible(true);theDialog.varMaxLag.setVisible(true);
                        theDialog.varMaxLag.setModel(varMaxLags);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.cointTypeLabel.setVisible(true);theDialog.typeEigen.setVisible(true);theDialog.typeTrace.setVisible(true);
                        theDialog.constantTrendLabel.setVisible(true);
                           theDialog.includeNone.setVisible(true); theDialog.includeConstant.setVisible(true);
                           theDialog.includeTrend.setVisible(true);theDialog.includeBoth.setVisible(true);
                        theDialog.seasonalLabel.setVisible(true);
                        theDialog.noSeasonalDummies.setVisible(true);theDialog.quarterlyDummies.setVisible(true);theDialog.monthlyDummies.setVisible(true);                                            
                        if (theDialog.libsNeedLoading_VAR){
                          Deducer.execute("suppressMessages(library(vars))");
                          theDialog.libsNeedLoading_VAR=false;
                        }  
                        
                   }
                        else if ((EstimationMethodDialog.equals("Cointegration Test - Johansen"))){
                        theDialog.modelD.method="ca.jo";
                        theDialog.outcomePanel.setVisible(false);
                        theDialog.contPanel.setVisible(false);
                        theDialog.cointTestVarsPanel.setVisible(true);
                        theDialog.cointExogVarsPanel.setVisible(true);
                        theDialog.varMaxLagLabel.setVisible(true);
                        theDialog.varMaxLag.setModel(cointMaxLags); 
                        theDialog.varMaxLag.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.cointOptionsLabel.setVisible(true);theDialog.cointOption.setVisible(true);
                        theDialog.seasonalLabel.setVisible(true);
                        theDialog.noSeasonalDummies.setVisible(true);theDialog.quarterlyDummies.setVisible(true);theDialog.monthlyDummies.setVisible(true); 
                        if (theDialog.libsNeedLoading_cajocapo){
                          Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                          theDialog.libsNeedLoading_cajocapo=false;
                        }  
                        
                   }
                        else if ((EstimationMethodDialog.equals("Cointegration Test - Philips & Ouliaris"))){
                        theDialog.modelD.method="ca.po";
                        theDialog.outcomePanel.setVisible(false);
                        theDialog.contPanel.setVisible(false);
                        theDialog.cointTestVarsPanel.setVisible(true);
                        theDialog.exogVarsPanel.setVisible(false);
                        theDialog.cointExogVarsPanel.setVisible(false);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.cointOptionsLabel.setVisible(false);theDialog.cointOption.setVisible(false);
                        theDialog.cointPOType.setVisible(true);
                        theDialog.cointPOComponent.setVisible(true);
                        theDialog.cointPOLag.setVisible(true);
                        theDialog.seasonalLabel.setVisible(false);
                        theDialog.noSeasonalDummies.setVisible(false);theDialog.quarterlyDummies.setVisible(false);theDialog.monthlyDummies.setVisible(false);   
                        if (theDialog.libsNeedLoading_cajocapo){
                          Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                          theDialog.libsNeedLoading_cajocapo=false;
                        }  
                      } else if ((EstimationMethodDialog.equals("Cointegration Test - Engle & Granger"))){
                       theDialog.modelD.method="ca.EG";
                        theDialog.outcomePanel.setVisible(false);
                        theDialog.contPanel.setVisible(false);
                        theDialog.cointTestVarsPanel.setVisible(true);
                        theDialog.exogVarsPanel.setVisible(false);
                        theDialog.cointExogVarsPanel.setVisible(false);
                        theDialog.infoCriterionLabel.setVisible(true);theDialog.infoCriterion2.setVisible(true);
                        theDialog.varMaxLagLabel.setVisible(true);
                        theDialog.varMaxLagEG.setModel(cointMaxLagsEG);
                        theDialog.varMaxLagEG.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.cointOptionsLabel.setVisible(false);theDialog.cointOption.setVisible(false);
                        theDialog.cointEGComponent.setVisible(true);
                        theDialog.seasonalLabel.setVisible(false);
                        theDialog.noSeasonalDummies.setVisible(false);theDialog.quarterlyDummies.setVisible(false);theDialog.monthlyDummies.setVisible(false);   
                        if (theDialog.libsNeedLoading_caEG){
                          if (theDialog.libsNeedLoading_cajocapo){
                             Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                             theDialog.libsNeedLoading_cajocapo=false;
                          }  
                          Deducer.execute("suppressMessages(library(dynlm))\nsuppressMessages(library(plm))");
                          theDialog.libsNeedLoading_caEG=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Panel - Panel Linear Model")){
                        theDialog.modelD.method="plm";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.effectLabel.setVisible(true);
                        theDialog.plmeffect.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.plmtype.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.plmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        theDialog.libraryStatement="";
                        if (theDialog.libsNeedLoading_plm){
                          if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                          Deducer.execute("suppressMessages(library(xtable))\nsuppressMessages(library(AER))");
                          theDialog.libsNeedLoading_plm=false;
                        }  
                   } else if (EstimationMethodDialog.equals("Panel - Variable Coefficients")){
                        theDialog.modelD.method="pvcm";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.effectLabel.setVisible(true);
                        theDialog.pvcmeffect.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.pvcmtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                     if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                    } else if (EstimationMethodDialog.equals("Panel - General Feasible GLS")){
                        theDialog.modelD.method="pggls";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.effectLabel.setVisible(true);
                        theDialog.pgglseffect.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.pgglstype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                    } else if (EstimationMethodDialog.equals("Panel - GMM (e.g. for dynamic equations)")){
                        theDialog.modelD.method="pgmm";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.lagVariablesPanelLabel.setVisible(true);theDialog.lagVariablesPanel.setVisible(true);
                        theDialog.effectLabel.setVisible(true);
                        theDialog.pgmmeffect.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.pgmmtype.setVisible(true);
                        theDialog.vcovtypeLabel.setVisible(true); theDialog.pgmmvcovtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                   } else if (EstimationMethodDialog.equals("Panel - Common Correlated Effects Pooled Estimator")){
                        theDialog.modelD.method="pcce";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.panelTrendIncluded.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.pccetype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                    } else if (EstimationMethodDialog.equals("Panel - Mean Groups")){
                        theDialog.modelD.method="pmg";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.panelTrendIncluded.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.pmgtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }                     
                     } else if (EstimationMethodDialog.equals("Spatial panel model - maximum likelihood")){
                        theDialog.modelD.method="spml";
                        theDialog.modelD.family ="";
                        theDialog.weightPanel.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.spmltype.setVisible(true);
                        theDialog.effectLabel.setVisible(true);
                        theDialog.spmleffect.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.listwButton.setVisible(true);
                        theDialog.hess.setVisible(true);
                        theDialog.zerosInitval.setVisible(true);
                        theDialog.quiet.setVisible(true);
                        theDialog.spatialLagDepIncluded.setVisible(true);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                       } else if (EstimationMethodDialog.equals("Spatial panel model w/ random effects & serial error correlation- max likelihood")){
                        theDialog.modelD.method="spreml";
                        theDialog.modelD.family ="";
                        theDialog.weightPanel.setVisible(true);
                        theDialog.spremlErrorsLabel.setVisible(true);
                        theDialog.spremlErrors.setVisible(true);
                        theDialog.hess.setVisible(true);
                        theDialog.zerosInitval.setVisible(true);
                        theDialog.quiet.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.listwButton.setVisible(true);
                        theDialog.spatialLagDepIncluded.setVisible(true);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }                       
                      } else if (EstimationMethodDialog.equals("Spatial panel model - GM")){
                        theDialog.modelD.method="spgm";
                        theDialog.modelD.family ="";
                        theDialog.weightPanel.setVisible(true);
                        theDialog.spgmMomentsLabel.setVisible(true);
                        theDialog.spgmMoments.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.listwButton.setVisible(true);
                        theDialog.spatialError.setVisible(true);
                        theDialog.spatialMethodLabel.setVisible(true); theDialog.spgmMethodType.setVisible(true);
                        theDialog.verbose.setVisible(true);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                        if (theDialog.libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             theDialog.libsNeedLoading_panel=false;
                          }
                    } else if (EstimationMethodDialog.equals("Panel - CIPS unit root test")){
                        theDialog.modelD.method="cipstest";
                        theDialog.modelD.family ="";
                        theDialog.instrumentPanel.setVisible(true);
                        theDialog.weightPanel.setVisible(true);
                        theDialog.typeLabel.setVisible(true);
                        theDialog.cipsURTtype.setVisible(true);
                        theDialog.interceptIncluded.setVisible(false);
                        theDialog.startPanel.setVisible(false);theDialog.endPanel.setVisible(false);
                        theDialog.panelIndexPanel.setVisible(true);
                   }
                   else{
                       theDialog.weightPanel.setVisible(true);
                   }

                   theDialog.setTitle(EstimationMethodDialog);

                }
		WindowTracker.addWindow(theDialog);

	}
      
        
        
        
	public static void runitagain(GMModel mod) {
		if(theDialog == null){
			theDialog = new GMDialog(mod);
		}

		theDialog.setToLast();
		theDialog.setVisible(true);
		
		WindowTracker.addWindow(theDialog);
	}

	public GMDialog(JFrame frame) {
		super(frame);
		widgets = new Vector();
		initGUI(1);
                resetModel();
                manModInput = new ManualModelInput();
	}  
        public GMDialog(JFrame frame, String EstMethod, boolean isJGR) {
		super(frame);
                runit(EstMethod, isJGR);

	}
        

	public GMDialog(GMModel mod) {
		super();
		mod.copyInto(modelOnOpen);
                widgets = new Vector();
		initGUI(1);
                resetModel();     
                this.setToLast();

            	ManualModelDialog manMod = new ManualModelDialog(this,manModInput);
			manMod.setLocationRelativeTo(this);
			manMod.setVisible(true);
	}
        	public GMDialog() {
		super();
		widgets = new Vector();
		initGUI(1);
                manModInput = new ManualModelInput();
                resetModel();
	}      
            public GMDialog(boolean isJGR) {
		super();
		widgets = new Vector();
                sizeAdjuster = 1f;
                if (!isJGR) {
                  sizeAdjuster = 0.9f;
                }
		initGUI(sizeAdjuster);
                manModInput = new ManualModelInput();
                resetModel();
	}      

	private void initGUI(float sizeAdj) {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        variableSelector = new VariableSelectorWidget();
                        getContentPane().add(variableSelector, new AnchorConstraint(20, 431, 669, 22, 
				AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
				AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			variableSelector.setPreferredSize(new java.awt.Dimension(222, 529));
			variableSelector.setTitle("unused title");
			{
                                {
					resetButton = new JButton();
					getContentPane().add(resetButton, new AnchorConstraint(20, 550, 55, 440,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					resetButton.setText("Reset");
					resetButton.setPreferredSize(new java.awt.Dimension(100, 46));
					resetButton.addActionListener(this);
				}
                            
                            
              			{
					outcomePanel = new SingleVariableWidget("Response variable",variableSelector);
					getContentPane().add(outcomePanel, new AnchorConstraint(45, 978, 135, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					outcomePanel.setPreferredSize(new java.awt.Dimension(223, 51));
				}
                                {
					interceptIncluded = new JCheckBox();
					getContentPane().add(interceptIncluded, new AnchorConstraint(140, 800, 160, 560, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					interceptIncluded.setText("Include intercept");
                                        interceptIncluded.setFont(font11);
					interceptIncluded.setPreferredSize(new java.awt.Dimension(115, 18));
					interceptIncluded.addActionListener(this);
				}
                                {
                                        spatialLagDepIncluded = new JCheckBox();
					getContentPane().add(spatialLagDepIncluded, new AnchorConstraint(140, 950, 160, 550, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					spatialLagDepIncluded.setText("Include spatial lag of response variable");
                                        spatialLagDepIncluded.setFont(font11);
					spatialLagDepIncluded.setPreferredSize(new java.awt.Dimension(320, 18));
					spatialLagDepIncluded.addActionListener(this);
                                }
                                {
					panelTrendIncluded = new JCheckBox();
					getContentPane().add(panelTrendIncluded, new AnchorConstraint(140, 990, 160, 810, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					panelTrendIncluded.setText("Individual trend included");
                                        panelTrendIncluded.setFont(font11);
					panelTrendIncluded.setPreferredSize(new java.awt.Dimension(145, 18));
					panelTrendIncluded.addActionListener(this);
				}
                                
                                {
					contPanel = new KeepVariableListWidget("Explanatory variables",variableSelector);
                                	getContentPane().add(contPanel, new AnchorConstraint(165, 978, 355, 467, 
                                                     AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                      AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                        contPanel.setPreferredSize(new java.awt.Dimension(323, 122));
				}    
                                
                                {
					endogVarsPanel = new KeepVariableListWidget("Endogenous variables",variableSelector);
                                	getContentPane().add(endogVarsPanel, new AnchorConstraint(165, 978, 355, 467, 
                                                     AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                      AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                        endogVarsPanel.setPreferredSize(new java.awt.Dimension(323, 122));
				}   
                         	{
					cointTestVarsPanel = new KeepVariableListWidget("Variables to be tested",variableSelector);
                                	getContentPane().add(cointTestVarsPanel, new AnchorConstraint(165, 978, 355, 467, 
                                                     AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                      AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                        cointTestVarsPanel.setPreferredSize(new java.awt.Dimension(323, 122));
				}   
                                
                                

				{
					instrumentPanel = new KeepVariableListWidget("Instrumental variables",variableSelector);
					getContentPane().add(instrumentPanel, new AnchorConstraint(375, 978, 540, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					instrumentPanel.setPreferredSize(new java.awt.Dimension(323, 143));
                                        instrumentPanel.setVisible(false);

				} 
                                {
					exogVarsPanel = new KeepVariableListWidget("Exogenous variables",variableSelector);
					getContentPane().add(exogVarsPanel, new AnchorConstraint(375, 978, 535, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					exogVarsPanel.setPreferredSize(new java.awt.Dimension(323, 143));
                                        exogVarsPanel.setVisible(false);

				} 
                                {
					cointExogVarsPanel = new KeepVariableListWidget("Exogenous Variables",variableSelector);
					getContentPane().add(cointExogVarsPanel, new AnchorConstraint(375, 978, 535, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					cointExogVarsPanel.setPreferredSize(new java.awt.Dimension(323, 143));
                                        cointExogVarsPanel.setVisible(false);

				}                            
                                
                                {
				weightPanel = new KeepSingleVariableWidget("Weights",variableSelector);
				getContentPane().add(weightPanel, new AnchorConstraint(546, 978, 630, 467, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				weightPanel.setPreferredSize(new java.awt.Dimension(223, 52));
                                weightPanel.setVisible(true);
                                }
                                
                               {
                                    manualPanel  = new JPanel();
                                    getContentPane().add(manualPanel, new AnchorConstraint(640, 978, 730, 467, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    manualPanel.setLayout(modelFormulaLayout);
                                    manualPanel.setBorder(BorderFactory.createTitledBorder("Manual alternative for entering variables"));
                                    manualPanel.setPreferredSize(new java.awt.Dimension(110, 52));
                                 }       
                                
                                {
                                        manualScroller = new JScrollPane();
                                        manualPanel.add(manualScroller, BorderLayout.CENTER);
                                            {
                                                    manualText = new JTextArea();
                                                    manualScroller.setViewportView(manualText);
                                                    manualText.setFont(font12);
                                                    manualText.setEditable(true);

                                            }
                                    }
                               
                                {
					listwButton = new JButton();
					getContentPane().add(listwButton, new AnchorConstraint(740, 580, 770, 440,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					listwButton.setText("listw");
                                        listwButton.setFont(font12);
					listwButton.setPreferredSize(new java.awt.Dimension(100, 46));
					listwButton.addActionListener(this);
				}
                                {
					zeroPolicyLabel = new JLabel();
					getContentPane().add(zeroPolicyLabel, new AnchorConstraint(780, 600, 800, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					zeroPolicyLabel.setText("zero policy:");
                                        zeroPolicyLabel.setFont(font11);
/*					typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);*/
					zeroPolicyLabel.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					zeroPolicyType = new JComboBox();                         
					getContentPane().add(zeroPolicyType, new AnchorConstraint(805, 580, 835, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
		                        zeroPolicyType.setFont(font12);
                                        zeroPolicyType.setModel(zeroPolicyTypeList);
                                        
					zeroPolicyType.setPreferredSize(new java.awt.Dimension(105, 21));
					zeroPolicyType.addActionListener(this);
				}
                                {
					zerosInitval = new JCheckBox();
					getContentPane().add(zerosInitval, new AnchorConstraint(850, 600, 870, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					zerosInitval.setText("zeros initval");
                                        zerosInitval.setFont(font11);
					zerosInitval.setPreferredSize(new java.awt.Dimension(160, 18));
					zerosInitval.addActionListener(this);
				}
                                {
					hess = new JCheckBox();
					getContentPane().add(hess, new AnchorConstraint(880, 550, 900, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					hess.setText("hess");
                                        hess.setFont(font11);
					hess.setPreferredSize(new java.awt.Dimension(115, 18));
					hess.addActionListener(this);
				}
                                {
                                	spatialError = new JCheckBox();
					getContentPane().add(spatialError, new AnchorConstraint(880, 600, 900, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					spatialError.setText("spatial error");
                                        spatialError.setFont(font11);
					spatialError.setPreferredSize(new java.awt.Dimension(160, 18));
					spatialError.addActionListener(this);
				}
                                {
					quiet = new JCheckBox();
					getContentPane().add(quiet, new AnchorConstraint(910, 590, 930, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					quiet.setText("quiet");
                                        quiet.setFont(font11);
					quiet.setPreferredSize(new java.awt.Dimension(140, 18));
					quiet.addActionListener(this);
				}
                                {
					notQuiet = new JCheckBox();
					getContentPane().add(notQuiet, new AnchorConstraint(910, 590, 930, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					notQuiet.setText("not quiet");
                                        notQuiet.setFont(font11);
					notQuiet.setPreferredSize(new java.awt.Dimension(140, 18));
					notQuiet.addActionListener(this);
				}
                                {
					verbose = new JCheckBox();
					getContentPane().add(verbose, new AnchorConstraint(910, 590, 930, 440, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					verbose.setText("verbose");
                                        verbose.setFont(font11);
					verbose.setPreferredSize(new java.awt.Dimension(140, 18));
					verbose.addActionListener(this);
				}
                                {
				subsetPanel = new JPanel();
				BorderLayout subsetPanelLayout = new BorderLayout();
				subsetPanel.setLayout(subsetPanelLayout);
				getContentPane().add(subsetPanel, new AnchorConstraint(740, 978, 840, 580, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				subsetPanel.setBorder(BorderFactory.createTitledBorder("Subset"));
				subsetPanel.setPreferredSize(new java.awt.Dimension(110, 79));
                                }
				{
					subset = new SubsetPanel(variableSelector.getJComboBox());
					subsetPanel.add(subset, BorderLayout.CENTER);
					subset.setPreferredSize(new java.awt.Dimension(213, 53));
                                        subset.setFont(font11);
				}

                                {
                                    panelIndexPanel  = new JPanel();
                                    getContentPane().add(panelIndexPanel, new AnchorConstraint(840, 978, 920, 610, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    panelIndexPanel.setLayout(modelFormulaLayout);
                                    panelIndexPanel.setBorder(BorderFactory.createTitledBorder("Index (if not 1st 2 variables)"));
                                    panelIndexPanel.setPreferredSize(new java.awt.Dimension(100, 52));
                                 }                                          
                                    {
                                        panelIndexScroller = new JScrollPane();
                                        panelIndexPanel.add(panelIndexScroller, BorderLayout.CENTER);
                                            {
                                                    panelIndexText = new JTextArea();
                                                    panelIndexText.setText(""); 
                                                    panelIndexScroller.setViewportView(panelIndexText);
                                                    panelIndexText.setFont(font12);
                                                    panelIndexText.setEditable(true);

                                            }
                                    }
                                
                                 {
                                    leftLimitPanel  = new JPanel();
                                    getContentPane().add(leftLimitPanel, new AnchorConstraint(800, 487, 930, 21, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    leftLimitPanel.setLayout(modelFormulaLayout);
                                    leftLimitPanel.setBorder(BorderFactory.createTitledBorder("left limit (default:0; -Inf=none)"));
                                    leftLimitPanel.setFont(font11);
                                    leftLimitPanel.setPreferredSize(new java.awt.Dimension(200, 42));
                                 }                                          
                                    {
                                        leftLimitText = new JTextField();
                                        leftLimitPanel.add(leftLimitText,BorderLayout.CENTER);
                                        leftLimitText.setEditable(true);
                                        leftLimitText.setFocusable(true);
                                    }
                                                                  {
                                    rightLimitPanel  = new JPanel();
                                    getContentPane().add(rightLimitPanel, new AnchorConstraint(800, 487, 930, 340
                                            , 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    rightLimitPanel.setLayout(modelFormulaLayout);
                                    rightLimitPanel.setBorder(BorderFactory.createTitledBorder("right limit (default:Inf)"));
                                    rightLimitPanel.setFont(font11);
                                    rightLimitPanel.setPreferredSize(new java.awt.Dimension(150, 42));
                                 }                                          
                                    {
                                        rightLimitText = new JTextField();
                                        rightLimitPanel.add(rightLimitText,BorderLayout.CENTER);
                                        rightLimitText.setEditable(true);
                                        rightLimitText.setFocusable(true);
                                    }   
                                    
                                    
                                    
                                    
                                 {
                                    startPanel  = new JPanel();
                                    getContentPane().add(startPanel, new AnchorConstraint(840, 778, 920, 580, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    startPanel.setLayout(modelFormulaLayout);
                                    startPanel.setBorder(BorderFactory.createTitledBorder("Data start"));
                                    startPanel.setFont(font11);
                                    startPanel.setPreferredSize(new java.awt.Dimension(110, 52));
                                 }                                          
                                    {
                                        startScroller = new JScrollPane();
                                        startPanel.add(startScroller, BorderLayout.CENTER);
                                            {
                                                    startText = new JTextArea();
                                                    startScroller.setViewportView(startText);
                                                    startText.setFont(font12);
                                                    startText.setEditable(true);

                                            }
                                    }
                                        
                                        
                                        
                                 {
                                    endPanel  = new JPanel();
                                    getContentPane().add(endPanel, new AnchorConstraint(840, 978, 920, 780, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    endPanel.setLayout(modelFormulaLayout);
                                    endPanel.setBorder(BorderFactory.createTitledBorder("Data end"));
                                    endPanel.setFont(font11);
                                    endPanel.setPreferredSize(new java.awt.Dimension(110, 52));
                                 }                                          
                                    {
                                        endScroller = new JScrollPane();
                                        endPanel.add(endScroller, BorderLayout.CENTER);
                                            {
                                                    endText = new JTextArea();
                                                    endScroller.setViewportView(endText);
                                                    endText.setFont(font12);
                                                    endText.setEditable(true);


                                            }
                                    }                                
                                
                                
                                {
					newVariablesLabel = new JLabel();
					getContentPane().add(newVariablesLabel, new AnchorConstraint(689, 487, 822, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					newVariablesLabel.setText("New Variable:");
                                        newVariablesLabel.setFont(font11);
					newVariablesLabel.setPreferredSize(new java.awt.Dimension(76, 20));
				}      
                                
				{
					newVariables = new JComboBox();
					getContentPane().add(newVariables, new AnchorConstraint(689, 431, 824, 204,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					newVariables.setModel(transforms);
                                        newVariables.setFont(font12);
					newVariables.setPreferredSize(new java.awt.Dimension(60, 21));
					newVariables.addActionListener(this);
				}
                                {
					lagVariablesPanelLabel = new JLabel();
					getContentPane().add(lagVariablesPanelLabel, new AnchorConstraint(731, 487, 782, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					lagVariablesPanelLabel.setText("Lag Panel Variables:");
                                        lagVariablesPanelLabel.setFont(font11);
					lagVariablesPanelLabel.setPreferredSize(new java.awt.Dimension(96, 20));
				}      
                                
				{
					lagVariablesPanel = new JComboBox();
					getContentPane().add(lagVariablesPanel, new AnchorConstraint(731, 431, 824, 204,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					lagVariablesPanel.setModel(transformsPanel);
                                        lagVariablesPanel.setFont(font12);
					lagVariablesPanel.setPreferredSize(new java.awt.Dimension(60, 21));
					lagVariablesPanel.addActionListener(this);
				}
                                {
					spatialErrorPanelLabel = new JLabel();
					getContentPane().add(spatialErrorPanelLabel, new AnchorConstraint(731, 487, 782, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spatialErrorPanelLabel.setText("Spatial Error Type:");
                                        spatialErrorPanelLabel.setFont(font11);
					spatialErrorPanelLabel.setPreferredSize(new java.awt.Dimension(96, 20));
				}      
                                
				{
					spatialErrorPanel = new JComboBox();
					getContentPane().add(spatialErrorPanel, new AnchorConstraint(731, 431, 824, 204,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spatialErrorPanel.setModel(spatialErrorPanelList);
                                        spatialErrorPanel.setFont(font12);
					spatialErrorPanel.setPreferredSize(new java.awt.Dimension(60, 21));
					spatialErrorPanel.addActionListener(this);
				} 
                                {
					spremlErrorsLabel = new JLabel();
					getContentPane().add(spremlErrorsLabel, new AnchorConstraint(731, 487, 782, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spremlErrorsLabel.setText("Errors:");
                                        spremlErrorsLabel.setFont(font11);
					spremlErrorsLabel.setPreferredSize(new java.awt.Dimension(96, 20));
				}      
                                
				{
					spremlErrors = new JComboBox();
					getContentPane().add(spremlErrors, new AnchorConstraint(731, 431, 824, 204,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spremlErrors.setModel(spremlErrorsList);
                                        spremlErrors.setFont(font12);
					spremlErrors.setPreferredSize(new java.awt.Dimension(60, 21));
					spremlErrors.addActionListener(this);
				} 
                                {
					spgmMomentsLabel = new JLabel();
					getContentPane().add(spgmMomentsLabel, new AnchorConstraint(731, 487, 782, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spgmMomentsLabel.setText("Moments:");
                                        spgmMomentsLabel.setFont(font11);
					spgmMomentsLabel.setPreferredSize(new java.awt.Dimension(96, 20));
				}      
                                
				{
					spgmMoments = new JComboBox();
					getContentPane().add(spgmMoments, new AnchorConstraint(731, 431, 824, 204,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spgmMoments.setModel(spgmMomentsList);
                                        spgmMoments.setFont(font12);
					spgmMoments.setPreferredSize(new java.awt.Dimension(60, 21));
					spgmMoments.addActionListener(this);
				} 
                                
                                
                                
                                {
                                        cointPOType = new ButtonGroupWidget("type",
						new String[]{"Pu",
                                                             "Pz"});
                                        cointPOType.setSelected("Pz");
                                        getContentPane().add(cointPOType, new AnchorConstraint(731, 487, 822, 21,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cointPOType.setPreferredSize(new java.awt.Dimension(60, 100));
                                }
                                {
                                        cointPOComponent = new ButtonGroupWidget("Demean",
						new String[]{"none",
                                                             "constant",
                                                             "trend"});
                                        cointPOComponent.setSelected("none");
                                        cointPOComponent.setFont(font12);
                                        getContentPane().add(cointPOComponent, new AnchorConstraint(731, 487, 822, 114,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cointPOComponent.setPreferredSize(new java.awt.Dimension(90, 100));
                                }
                                {
                                        cointPOLag = new ButtonGroupWidget("Lag window",
						new String[]{"short",
                                                             "long"});
                                        cointPOLag.setSelected("short");
                                        cointPOLag.setFont(font12);
                                        getContentPane().add(cointPOLag , new AnchorConstraint(731, 487, 822, 304,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cointPOLag.setPreferredSize(new java.awt.Dimension(80, 100));
                                }
                                {
                                        cointEGComponent = new ButtonGroupWidget("Trend structure in 1st step",
						new String[]{"no trend",
                                                             "linear trend",
                                                             "quadratic trend"});
                                        cointEGComponent.setSelected("no trend");
                                        cointEGComponent.setFont(font12);
                                        getContentPane().add(cointEGComponent, new AnchorConstraint(731, 487, 822, 21,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        cointEGComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                }
                                
                                {
                                	cointOptionsLabel = new JLabel();
					getContentPane().add(cointOptionsLabel, new AnchorConstraint(731, 487, 822, 21,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					cointOptionsLabel.setText("Deterministic Elements; transitory or long run");
                                        cointOptionsLabel.setFont(font11);
					cointOptionsLabel.setPreferredSize(new java.awt.Dimension(300, 20));
				}      
                                
                                {
					cointOption = new JComboBox();
					getContentPane().add(cointOption, new AnchorConstraint(771, 487, 782, 31,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					cointOption.setModel(cointOptionsAvailable);
                                        cointOption.setFont(font12);
					cointOption.setPreferredSize(new java.awt.Dimension(30, 20));
					cointOption.addActionListener(this);
				}
                                
                                
                                {     
					cointTypeLabel = new JLabel();
					getContentPane().add(cointTypeLabel, new AnchorConstraint(731, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));              
					cointTypeLabel.setText("Type:");
                                        cointTypeLabel.setFont(font11);
					cointTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					typeEigen = new JRadioButton();
                                        getContentPane().add(typeEigen , new AnchorConstraint(726, 487, 782, 91, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					typeEigen.setText("eigen");
                                        typeEigen.setFont(font11);
				}
				{
					typeTrace = new JRadioButton();
                                        getContentPane().add(typeTrace, new AnchorConstraint(726, 487, 782, 176, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					typeTrace.setText("trace");
                                        typeTrace.setFont(font11);
				}
                                {
                                	ButtonGroup cointTypeButtons =new ButtonGroup();
                                        cointTypeButtons.add(typeEigen);
                                        cointTypeButtons.add(typeTrace);
                                        typeEigen.setSelected(true);
                                        typeEigen.setFont(font11);
                                }


                                {     
					constantTrendLabel = new JLabel();
					getContentPane().add(constantTrendLabel, new AnchorConstraint(771, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));              
					constantTrendLabel.setText("Constant or Trend:");
                                        constantTrendLabel.setFont(font11);
					constantTrendLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
                                {
					includeNone = new JRadioButton();
                                        getContentPane().add(includeNone , new AnchorConstraint(766, 487, 782, 191, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					includeNone.setText("none");
                                        includeNone.setFont(font11);
				}
				{
					includeConstant = new JRadioButton();
                                        getContentPane().add(includeConstant , new AnchorConstraint(766, 487, 782, 271,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					includeConstant.setText("constant");
                                        includeConstant.setFont(font11);
				}
				{
					includeTrend = new JRadioButton();
                                        getContentPane().add(includeTrend, new AnchorConstraint(766, 487, 782, 381,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					includeTrend.setText("trend");
                                        includeTrend.setFont(font11);
				}
                                {
					includeBoth = new JRadioButton();
                                        getContentPane().add(includeBoth, new AnchorConstraint(766, 487, 782, 461,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					includeBoth.setText("both");
                                        includeBoth.setFont(font11);
				}
                                {
                                	ButtonGroup constantTrendButtons =new ButtonGroup();
                                        constantTrendButtons.add(includeNone);
                                        constantTrendButtons.add(includeConstant);
                                        constantTrendButtons.add(includeTrend);
                                        constantTrendButtons.add(includeBoth);
                                        includeNone.setSelected(true);
                                }
 
                                
                                
                                {     
					seasonalLabel = new JLabel();
					getContentPane().add(seasonalLabel, new AnchorConstraint(851, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));              
					seasonalLabel.setText("Seasonal Dummies:");
                                        seasonalLabel.setFont(font11);
					seasonalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
                                {
					noSeasonalDummies = new JRadioButton();
                                        getContentPane().add(noSeasonalDummies , new AnchorConstraint(846, 487, 782, 181, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					noSeasonalDummies.setText("none");
                                        noSeasonalDummies.setFont(font11);
				}
				{
					quarterlyDummies = new JRadioButton();
                                        getContentPane().add(quarterlyDummies , new AnchorConstraint(846, 487, 782, 261, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					quarterlyDummies.setText("quarterly");
                                        quarterlyDummies.setFont(font11);
				}
				{
					monthlyDummies = new JRadioButton();
                                        getContentPane().add(monthlyDummies, new AnchorConstraint(846, 487, 782, 381, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
					monthlyDummies.setText("monthly");
                                        monthlyDummies.setFont(font11);
				}
                                {
                                	ButtonGroup seasonalButtons =new ButtonGroup();
                                        seasonalButtons.add(noSeasonalDummies);
                                        seasonalButtons.add(quarterlyDummies);
                                        seasonalButtons.add(monthlyDummies);
                                        noSeasonalDummies.setSelected(true);
                                }
                                
                                
                                
                                
                                {
					effectLabel = new JLabel();
					getContentPane().add(effectLabel, new AnchorConstraint(784, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					effectLabel.setText("Effect");
                                        effectLabel.setFont(font11);
					effectLabel.setPreferredSize(new java.awt.Dimension(100, 20));
				}   
                                {
					plmeffect = new JComboBox();
					getContentPane().add(plmeffect, new AnchorConstraint(784, 431, 804, 204,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					plmeffect.setModel(plmeffects);
                                        plmeffect.setFont(font12);
					plmeffect.setPreferredSize(new java.awt.Dimension(60, 21));
					plmeffect.addActionListener(this);
				}
                                {
					pvcmeffect = new JComboBox();
					getContentPane().add(pvcmeffect, new AnchorConstraint(784, 431, 804, 204,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pvcmeffect.setModel(pvcmeffects);
                                        pvcmeffect.setFont(font12);
					pvcmeffect.setPreferredSize(new java.awt.Dimension(60, 21));
					pvcmeffect.addActionListener(this);
				}

                                {
					pgglseffect = new JComboBox();
					getContentPane().add(pgglseffect, new AnchorConstraint(784, 431, 804, 204,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pgglseffect.setModel(pgglseffects);
                                        pgglseffect.setFont(font12);
					pgglseffect.setPreferredSize(new java.awt.Dimension(60, 21));
					pgglseffect.addActionListener(this);
				}
                                {
					pgmmeffect = new JComboBox();
					getContentPane().add(pgmmeffect, new AnchorConstraint(784, 431, 804, 204,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pgmmeffect.setModel(pgmmeffects);
                                        pgmmeffect.setFont(font12);
					pgmmeffect.setPreferredSize(new java.awt.Dimension(60, 21));
					pgmmeffect.addActionListener(this);
				}
                                {
					spmleffect = new JComboBox();
					getContentPane().add(spmleffect, new AnchorConstraint(784, 431, 804, 204,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spmleffect.setModel(spmleffects);
                                        spmleffect.setFont(font12);
					spmleffect.setPreferredSize(new java.awt.Dimension(60, 21));
					spmleffect.addActionListener(this);
				}
                                {
					varMaxLagLabel = new JLabel();
                                        getContentPane().add(varMaxLagLabel, new AnchorConstraint(25, 978, 105, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					varMaxLagLabel.setText("Maximum Lag");
                                        varMaxLagLabel.setFont(font12);
					varMaxLagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}  
                                
                                
                               {
					varMaxLag = new JComboBox();
					getContentPane().add(varMaxLag, new AnchorConstraint(75, 978, 105, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        varMaxLag.setFont(font12);
					varMaxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					varMaxLag.addActionListener(this);
				}
                                {
					varMaxLagEG = new JComboBox();
					getContentPane().add(varMaxLagEG, new AnchorConstraint(75, 978, 105, 467, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        varMaxLagEG.setFont(font12);
					varMaxLagEG.setPreferredSize(new java.awt.Dimension(60, 21));
					varMaxLagEG.addActionListener(this);
				}
                                {
					infoCriterionLabel = new JLabel();
                                        getContentPane().add(infoCriterionLabel, new AnchorConstraint(25, 978, 105, 667, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					infoCriterionLabel.setText("Info criterion for lag choice");
                                        infoCriterionLabel.setFont(font11);
					infoCriterionLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}  
                               {
					infoCriterion = new JComboBox();
					getContentPane().add(infoCriterion, new AnchorConstraint(75, 978, 105, 667, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					infoCriterion.setModel(infoCriterions);
                                        infoCriterion.setFont(font12);
					infoCriterion.setPreferredSize(new java.awt.Dimension(180, 21));
					infoCriterion.addActionListener(this);
				}                              
                               {
					infoCriterion2 = new JComboBox();
					getContentPane().add(infoCriterion2, new AnchorConstraint(75, 978, 105, 667, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					infoCriterion2.setModel(infoCriterions2);
                                        infoCriterion2.setFont(font12);
					infoCriterion2.setPreferredSize(new java.awt.Dimension(180, 21));
					infoCriterion2.addActionListener(this);
				}                                
                                   
                                {
					spatialMethodLabel = new JLabel();
					getContentPane().add(spatialMethodLabel, new AnchorConstraint( 784, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spatialMethodLabel.setText("Method:");
                                        spatialMethodLabel.setFont(font11);
					spatialMethodLabel.setPreferredSize(new java.awt.Dimension(100, 20));
				}   
				{
					spatialMethodType = new JComboBox();
					getContentPane().add(spatialMethodType, new AnchorConstraint(784, 431, 804, 204,   
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spatialMethodType.setModel(spatialMethodTypeList);
                                        spatialMethodType.setFont(font12);
					spatialMethodType.setPreferredSize(new java.awt.Dimension(230, 21));
					spatialMethodType.addActionListener(this);
				}  
                                
                                {
					spgmMethodType = new JComboBox();
					getContentPane().add(spgmMethodType, new AnchorConstraint(784, 431, 804, 204,   
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spgmMethodType.setModel(spgmMethodTypeList);
                                        spgmMethodType.setFont(font12);
					spgmMethodType.setPreferredSize(new java.awt.Dimension(230, 21));
					spgmMethodType.addActionListener(this);
				}  
                                
                                
                                {
					typeLabel = new JLabel();
					getContentPane().add(typeLabel, new AnchorConstraint(829, 487, 782, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					typeLabel.setText("Model Type:");
                                        typeLabel.setFont(font11);
					typeLabel.setPreferredSize(new java.awt.Dimension(100, 20));
				}   
				{
					plmtype = new JComboBox();
					getContentPane().add(plmtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					plmtype.setModel(plmPanelTypes);
                                        plmtype.setFont(font12);
					plmtype.setPreferredSize(new java.awt.Dimension(230, 21));
					plmtype.addActionListener(this);
				}  
                                {
					pvcmtype = new JComboBox();
					getContentPane().add(pvcmtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pvcmtype.setModel(pvcmPanelTypes);
                                        pvcmtype.setFont(font12);
					pvcmtype.setPreferredSize(new java.awt.Dimension(230, 21));
					pvcmtype.addActionListener(this);
				}  
                                {
					pgglstype = new JComboBox();
					getContentPane().add(pgglstype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pgglstype.setModel(pgglsPanelTypes);
                                        pgglstype.setFont(font12);
					pgglstype.setPreferredSize(new java.awt.Dimension(230, 21));
					pgglstype.addActionListener(this);
				} 
                                {
					pgmmtype = new JComboBox();
					getContentPane().add(pgmmtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pgmmtype.setModel(pgmmPanelTypes);
                                        pgmmtype.setFont(font12);
					pgmmtype.setPreferredSize(new java.awt.Dimension(230, 21));
					pgmmtype.addActionListener(this);
				} 
                                {
					pccetype = new JComboBox();
					getContentPane().add(pccetype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pccetype.setModel(pccePanelTypes);
                                        pccetype.setFont(font12);
					pccetype.setPreferredSize(new java.awt.Dimension(230, 21));
					pccetype.addActionListener(this);
				} 
                                {
					pmgtype = new JComboBox();
					getContentPane().add(pmgtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pmgtype.setModel(pmgPanelTypes);
                                        pmgtype.setFont(font12);
					pmgtype.setPreferredSize(new java.awt.Dimension(230, 21));
					pmgtype.addActionListener(this);
				} 
                                {
					spmltype = new JComboBox();
					getContentPane().add(spmltype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spmltype.setModel(spmlPanelTypes);
                                        spmltype.setFont(font12);
					spmltype.setPreferredSize(new java.awt.Dimension(230, 21));
					spmltype.addActionListener(this);
				} 
                                {
					spgmtype = new JComboBox();
					getContentPane().add(spgmtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					spgmtype.setModel(spgmPanelTypes);
                                        spgmtype.setFont(font12);
					spgmtype.setPreferredSize(new java.awt.Dimension(230, 21));
					spgmtype.addActionListener(this);
				} 
                                
                                
                                {
					cipsURTtype = new JComboBox();
					getContentPane().add(cipsURTtype, new AnchorConstraint(829, 431, 804, 160,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					cipsURTtype.setModel(cipsURTPanelTypes);
                                        cipsURTtype.setFont(font12);
					cipsURTtype.setPreferredSize(new java.awt.Dimension(230, 21));
					cipsURTtype.addActionListener(this);
				} 
                                
                                {
					vcovtypeLabel = new JLabel();
					getContentPane().add(vcovtypeLabel, new AnchorConstraint(860, 287, 923, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					vcovtypeLabel.setText("Robust standard error:");
                                        vcovtypeLabel.setFont(font11);
					vcovtypeLabel.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					glmvcovtype = new JComboBox();                         
					getContentPane().add(glmvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        glmvcovtype.setFont(font12);
                                        glmvcovtype.setModel(glmvcovsDialog);
                                        
					glmvcovtype.setPreferredSize(new java.awt.Dimension(105, 21));
					glmvcovtype.addActionListener(this);
				}
                                {
					tobitvcovtype = new JComboBox();                         
					getContentPane().add(tobitvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        tobitvcovtype.setFont(font12);
                                        tobitvcovtype.setModel(tobitvcovsDialog);
                                        
					tobitvcovtype.setPreferredSize(new java.awt.Dimension(105, 21));
					tobitvcovtype.addActionListener(this);
				}
                                
                                {
					plmvcovtype = new JComboBox();
					getContentPane().add(plmvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					plmvcovtype.setModel(plmvcovsDialog);
                                        plmvcovtype.setFont(font12);
					plmvcovtype.setPreferredSize(new java.awt.Dimension(150, 21));
					plmvcovtype.addActionListener(this);
				}
                                {
					pgmmvcovtype = new JComboBox();
					getContentPane().add(pgmmvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					pgmmvcovtype.setModel(pgmmvcovsDialog);
                                        pgmmvcovtype.setFont(font12);
					pgmmvcovtype.setPreferredSize(new java.awt.Dimension(150, 21));
					pgmmvcovtype.addActionListener(this);
				}
                                {
					stslsvcovtype = new JComboBox();                         
					getContentPane().add(stslsvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        stslsvcovtype.setFont(font12);
                                        stslsvcovtype.setModel(stslsvcovsDialog);
                                        
					stslsvcovtype.setPreferredSize(new java.awt.Dimension(105, 21));
					stslsvcovtype.addActionListener(this);
				}
                                {
					gstslsvcovtype = new JComboBox();                         
					getContentPane().add(gstslsvcovtype, new AnchorConstraint(890, 431, 925, 31, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        gstslsvcovtype.setFont(font12);
                                        gstslsvcovtype.setModel(gstslsvcovsDialog);
                                        
					gstslsvcovtype.setPreferredSize(new java.awt.Dimension(105, 21));
					gstslsvcovtype.addActionListener(this);
				} 

			}

                        
			{
				
				helpButton = new HelpButton("pmwiki.php?n=Main.ExampleDialog");
				getContentPane().add(helpButton, new AnchorConstraint(940, 77, 980, 23, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				helpButton.setPreferredSize(new java.awt.Dimension(32, 32));
                                helpButton.setEnabled(false);
			}
                        
                         {
                                runButton = new JButton();
                                getContentPane().add(runButton, new AnchorConstraint(940, 780, 980, 600, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                                runButton.setText("Run");
                                runButton.setFont(font12);
                                runButton.setPreferredSize(new java.awt.Dimension(110, 40));
                                runButton.addActionListener(this);
                         }
                         {
                                cancelButton = new JButton();
                                getContentPane().add(cancelButton, new AnchorConstraint(940, 978, 980, 800, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                                cancelButton.setText("Cancel");
                                cancelButton.setFont(font12);
                                cancelButton.setPreferredSize(new java.awt.Dimension(110, 40));
                                cancelButton.addActionListener(this);
                         }
                        
                        outcome = new SingletonDJList();
                        outcome.setModel(new DefaultListModel());
                        
                        numericList = new DJList();
			numericList.setModel(new DefaultListModel());
                        
                        endogVarsList = new DJList();
			endogVarsList.setModel(new DefaultListModel());
                        
                        cointTestVarsList = new DJList();
			cointTestVarsList.setModel(new DefaultListModel()); 
                        
                        instrumentsList = new DJList();
			instrumentsList.setModel(new DefaultListModel());
			
			this.setSize(Math.round(650), Math.round(750*sizeAdj));
                        
                        this.setFont(font12);
                        this.setTitle(EstimationMethodDialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
       
	private void reset(){
                variableSelector.refreshDataNames();  
                interceptIncluded.setSelected(true);
                spatialLagDepIncluded.setSelected(false);
                this.manualText.setText("");
                panelIndexText.setText("");
                startText.setText("");
                endText.setText("");
                leftLimitText.setText("");
                rightLimitText.setText("");   
	}

        
	public void saveToLast(){
	}
        
        public void reset(boolean resetOptions){
                outcome.setModel(new DefaultListModel());
		numericList.setModel(new DefaultListModel());
                endogVarsList.setModel(new DefaultListModel());
                cointTestVarsList.setModel(new DefaultListModel());
                instrumentsList.setModel(new DefaultListModel());
	}

        	protected void setToLast(){
                    saveToLast();
	}

        
	public static void setLastModel(GMModel lm){
		lastModel = lm;
	}
	
        
        
	public void resetModel(){
            
		setModel(new GMModel());
	}
	
        

        
        
        
        
	public void setModel(GMModel mod){
                DefaultListModel newExplanatoryList = new DefaultListModel();
                newExplanatoryList.addElement("(constant)");
		variableSelector.setSelectedData(mod.data);
                
		boolean valid = variableSelector.removeAll(mod.outcomeVars);
		if(!valid){
			resetModel();
			return;
		}
		outcomePanel.setModel(mod.outcomeVars);
		
		valid = variableSelector.removeAll(mod.numericVars);
                
		if(!valid){
			resetModel();
			return;
		}
		
                valid = variableSelector.removeAll(mod.efeglmextra.endogVarsD);
		if(!valid){
			resetModel();
			return;
		}
 
                valid = variableSelector.removeAll(mod.efeglmextra.cointTestVarsD);
		if(!valid){
			resetModel();
			return;
		}                
                
                valid = variableSelector.removeAll(mod.efeglmextra.exogVarsD);
		if(!valid){
			resetModel();
			return;
		}
                
                valid = variableSelector.removeAll(mod.efeglmextra.cointExogVarsD);
		if(!valid){
			resetModel();
			return;
		}
		valid = variableSelector.removeAll(mod.efeglmextra.instrumentsD);
		if(!valid){
			resetModel();
			return;
		}
		
		valid = variableSelector.removeAll(mod.weights);
		if(!valid){
			resetModel();
			return;
		}
		weightPanel.setModel(mod.weights);
		if(SubsetDialog.isValidSubsetExp(mod.subset, mod.data))
			subset.setText(mod.subset);

		if(glmvcovsDialog.getIndexOf(mod.glmvcov)>=0)
			glmvcovsDialog.setSelectedItem(mod.glmvcov);
		else
			glmvcovsDialog.insertElementAt(mod.glmvcov, glmvcovsDialog.getSize()-1);
                
                if(plmvcovsDialog.getIndexOf(mod.plmvcov)>=0)
			plmvcovsDialog.setSelectedItem(mod.plmvcov);
		else
			plmvcovsDialog.insertElementAt(mod.plmvcov, plmvcovsDialog.getSize()-1); 
                
                if(pgmmvcovsDialog.getIndexOf(mod.pgmmvcov)>=0)
			pgmmvcovsDialog.setSelectedItem(mod.pgmmvcov);
		else
			pgmmvcovsDialog.insertElementAt(mod.pgmmvcov, pgmmvcovsDialog.getSize()-1); 
                
                if(stslsvcovsDialog.getIndexOf(mod.stslsvcov)>=0)
			stslsvcovsDialog.setSelectedItem(mod.stslsvcov);
		else
			stslsvcovsDialog.insertElementAt(mod.stslsvcov, stslsvcovsDialog.getSize()-1);
                
                if(gstslsvcovsDialog.getIndexOf(mod.gstslsvcov)>=0)
			gstslsvcovsDialog.setSelectedItem(mod.gstslsvcov);
		else
			gstslsvcovsDialog.insertElementAt(mod.gstslsvcov, gstslsvcovsDialog.getSize()-1);
                
                if(varMaxLags.getIndexOf(modelD.varOptions.maxLag)>=0)
			varMaxLags.setSelectedItem(modelD.varOptions.maxLag);
		else
			varMaxLags.insertElementAt(modelD.varOptions.maxLag, varMaxLags.getSize()-1); 
                
                
                if(cointMaxLagsEG.getIndexOf(modelD.unitRootTestOptions.maxLagEG)>=0)
			cointMaxLagsEG.setSelectedItem(modelD.unitRootTestOptions.maxLagEG);
		else
			cointMaxLagsEG.insertElementAt(modelD.unitRootTestOptions.maxLagEG, cointMaxLagsEG.getSize()-1); 
                
                if(infoCriterions.getIndexOf(modelD.varOptions.infoCriterion)>=0)
			infoCriterions.setSelectedItem(modelD.varOptions.infoCriterion);
		else
			infoCriterions.insertElementAt(modelD.varOptions.infoCriterion, infoCriterions.getSize()-1);
                
                if(infoCriterions2.getIndexOf(modelD.unitRootTestOptions.infoCriterion2)>=0)
			infoCriterions2.setSelectedItem(modelD.unitRootTestOptions.infoCriterion2);
		else
			infoCriterions2.insertElementAt(modelD.unitRootTestOptions.infoCriterion2, infoCriterions2.getSize()-1);
                
                if(cointOptionsAvailable.getIndexOf(modelD.varOptions.cointOption)>=0)
			cointOptionsAvailable.setSelectedItem(modelD.varOptions.cointOption);
		else
			cointOptionsAvailable.insertElementAt(modelD.varOptions.cointOption, cointOptionsAvailable.getSize()-1);
                
                panelTrendIncluded.setSelected(modelD.efeglmextra.panelTrendIncluded);
                interceptIncluded.setSelected(modelD.efeglmextra.interceptIncluded);
                zerosInitval.setSelected(modelD.efeglmextra.zerosInitval);
                spatialLagDepIncluded.setSelected(modelD.efeglmextra.spatialLagDepIncluded);
                
               if(plmeffects.getIndexOf(mod.plmeffect)>=0)
			plmeffects.setSelectedItem(mod.plmeffect);
		else
			plmeffects.insertElementAt(mod.plmeffect, plmeffects.getSize()-1);
               
               if(pvcmeffects.getIndexOf(mod.pvcmeffect)>=0)
			pvcmeffects.setSelectedItem(mod.pvcmeffect);
		else
			pvcmeffects.insertElementAt(mod.pvcmeffect, pvcmeffects.getSize()-1);
                             
               if(pgglseffects.getIndexOf(mod.pgglseffect)>=0)
			pgglseffects.setSelectedItem(mod.pgglseffect);
		else
			pgglseffects.insertElementAt(mod.pgglseffect, pgglseffects.getSize()-1);
               
               if(pgmmeffects.getIndexOf(mod.pgmmeffect)>=0)
			pgmmeffects.setSelectedItem(mod.pgmmeffect);
		else
			pgmmeffects.insertElementAt(mod.pgmmeffect, pgmmeffects.getSize()-1); 
               
                if(spatialErrorPanelList.getIndexOf(mod.spatialErrorPanel)>=0)
			spatialErrorPanelList.setSelectedItem(mod.spatialErrorPanel);
		else
			spatialErrorPanelList.insertElementAt(mod.spatialErrorPanel, spatialErrorPanelList.getSize()-1);   
               
               if(spmleffects.getIndexOf(mod.spmleffect)>=0)
			spmleffects.setSelectedItem(mod.spmleffect);
		else
			spmleffects.insertElementAt(mod.spmleffect, spmleffects.getSize()-1);
               
                if(spremlErrorsList.getIndexOf(mod.spremlErrors)>=0)
			spremlErrorsList.setSelectedItem(mod.spremlErrors);
		else
			spremlErrorsList.insertElementAt(mod.spremlErrors, spremlErrorsList.getSize()-1);
                
                if(spgmMomentsList.getIndexOf(mod.spgmMoments)>=0)
			spgmMomentsList.setSelectedItem(mod.spgmMoments);
		else
			spgmMomentsList.insertElementAt(mod.spgmMoments, spgmMomentsList.getSize()-1);
               
                if(spmlPanelTypes.getIndexOf(mod.spmlPanelType)>=0)
			spmlPanelTypes.setSelectedItem(mod.spmlPanelType);
		else
			spmlPanelTypes.insertElementAt(mod.spmlPanelType, spmlPanelTypes.getSize()-1);
                           
                if(spgmPanelTypes.getIndexOf(mod.spgmPanelType)>=0)
			spgmPanelTypes.setSelectedItem(mod.spgmPanelType);
		else
			spgmPanelTypes.insertElementAt(mod.spgmPanelType, spgmPanelTypes.getSize()-1);
                
                if(plmPanelTypes.getIndexOf(mod.plmPanelType)>=0)
			plmPanelTypes.setSelectedItem(mod.plmPanelType);
		else
			plmPanelTypes.insertElementAt(mod.plmPanelType, plmPanelTypes.getSize()-1);
                
                if(pvcmPanelTypes.getIndexOf(mod.pvcmPanelType)>=0)
			pvcmPanelTypes.setSelectedItem(mod.pvcmPanelType);
		else
			pvcmPanelTypes.insertElementAt(mod.pvcmPanelType, pvcmPanelTypes.getSize()-1);               
                
                if(pgglsPanelTypes.getIndexOf(mod.pgglsPanelType)>=0)
			pgglsPanelTypes.setSelectedItem(mod.pgglsPanelType);
		else
			pgglsPanelTypes.insertElementAt(mod.pgglsPanelType, pgglsPanelTypes.getSize()-1);
                
                if(pgmmPanelTypes.getIndexOf(mod.pgmmPanelType)>=0)
			pgmmPanelTypes.setSelectedItem(mod.pgmmPanelType);
		else
			pgmmPanelTypes.insertElementAt(mod.pgmmPanelType, pgmmPanelTypes.getSize()-1);
                
                if(pccePanelTypes.getIndexOf(mod.pccePanelType)>=0)
			pccePanelTypes.setSelectedItem(mod.pccePanelType);
		else
			pccePanelTypes.insertElementAt(mod.pccePanelType, pccePanelTypes.getSize()-1);
                
                if(pmgPanelTypes.getIndexOf(mod.pmgPanelType)>=0)
			pmgPanelTypes.setSelectedItem(mod.pmgPanelType);
		else
			pmgPanelTypes.insertElementAt(mod.pmgPanelType, pmgPanelTypes.getSize()-1);
                
                if(cipsURTPanelTypes.getIndexOf(mod.cipsURTPanelType)>=0)
			cipsURTPanelTypes.setSelectedItem(mod.cipsURTPanelType);
		else
			cipsURTPanelTypes.insertElementAt(mod.cipsURTPanelType, cipsURTPanelTypes.getSize()-1);
                
                varMaxLagLabel.setVisible(false); varMaxLag.setVisible(false); varMaxLagEG.setVisible(false);
                outcomePanel.setVisible(true);
                contPanel.setVisible(true);
                endogVarsPanel.setVisible(false);
                cointTestVarsPanel.setVisible(false);
                exogVarsPanel.setVisible(false); 
                cointExogVarsPanel.setVisible(false);   
                instrumentPanel.setVisible(false);
                panelIndexPanel.setVisible(false);
                weightPanel.setVisible(false);
                modelD.factorVars.removeAllElements();
                lagVariablesPanelLabel.setVisible(false);lagVariablesPanel.setVisible(false);
                spatialErrorPanelLabel.setVisible(false);spatialErrorPanel.setVisible(false);
                effectLabel.setVisible(false);
                plmeffect.setVisible(false);pvcmeffect.setVisible(false);pgglseffect.setVisible(false);pgmmeffect.setVisible(false);spmleffect.setVisible(false);
                typeLabel.setVisible(false);
                plmtype.setVisible(false);pvcmtype.setVisible(false);pgglstype.setVisible(false);pgmmtype.setVisible(false);
                pccetype.setVisible(false);pmgtype.setVisible(false);
                spmltype.setVisible(false);spgmtype.setVisible(false);
                cipsURTtype.setVisible(false);
                interceptIncluded.setVisible(false);
                spatialLagDepIncluded.setVisible(false);
                panelTrendIncluded.setVisible(false);
                infoCriterionLabel.setVisible(false); infoCriterion.setVisible(false);infoCriterion2.setVisible(false);
                cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                cointPOType.setVisible(false);
                cointPOComponent.setVisible(false);
                cointPOLag.setVisible(false);
                cointEGComponent.setVisible(false);             
                vcovtypeLabel.setVisible(false); glmvcovtype.setVisible(false); tobitvcovtype.setVisible(false); plmvcovtype.setVisible(false); pgmmvcovtype.setVisible(false);
                stslsvcovtype.setVisible(false);gstslsvcovtype.setVisible(false);
                         
                zeroPolicyLabel.setVisible(false); zeroPolicyType.setVisible(false); 
                spatialMethodLabel.setVisible(false); spatialMethodType.setVisible(false);spgmMethodType.setVisible(false);
                spremlErrorsLabel.setVisible(false); spremlErrors.setVisible(false); 
                spgmMomentsLabel.setVisible(false); spgmMoments.setVisible(false); 
                zerosInitval.setVisible(false);hess.setVisible(false);spatialError.setVisible(false);
                quiet.setVisible(false); notQuiet.setVisible(false); verbose.setVisible(false);
                
                cointTypeLabel.setVisible(false);typeEigen.setVisible(false);typeTrace.setVisible(false);
                constantTrendLabel.setVisible(false);
                      includeNone.setVisible(false); includeConstant.setVisible(false);
                      includeTrend.setVisible(false); includeBoth.setVisible(false);
                seasonalLabel.setVisible(false);
                      noSeasonalDummies.setVisible(false);   quarterlyDummies.setVisible(false);  monthlyDummies.setVisible(false);
                modelD.EstimationMethod = EstimationMethodDialog;
                startPanel.setVisible(true);endPanel.setVisible(true);
                leftLimitPanel.setVisible(false);leftLimitText.setVisible(false);rightLimitPanel.setVisible(false);rightLimitText.setVisible(false);
                listwButton.setVisible(true);


                
                if (modelD.EstimationMethod.equals("Ordinary Least Squares")){
                           modelD.method="lm";
                        modelD.family="";
                        modelD.instruments="";
                        vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        if (libsNeedLoading_glm){
                          Deducer.execute("suppressMessages(library(lmtest))\nsuppressMessages(library(xtable))\nsuppressMessages(library(sandwich))");
                          libsNeedLoading_glm=false;
                        }  
                } else if (modelD.EstimationMethod.equals("Weighted Least Squares")){
                           modelD.method="lm";
                        modelD.family="";
                        modelD.instruments="";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        if (libsNeedLoading_glm){
                          Deducer.execute("suppressMessages(library(lmtest))\nsuppressMessages(library(xtable))\nsuppressMessages(library(sandwich))");
                          libsNeedLoading_glm=false;
                        }
                } else if (modelD.EstimationMethod.equals("Two-Stage Least Squares")){
                        modelD.method="ivreg";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        if (libsNeedLoading_ivreg){
                          Deducer.execute("suppressMessages(library(AER))\nsuppressMessages(library(xtable))");
                          libsNeedLoading_ivreg=false;
                        }        
                } else if (modelD.EstimationMethod.equals("Binary Logit")){
                        modelD.method="glm";
                        modelD.family =",family=binomial(link=\"logit\")";
                        modelD.instruments="";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        if (libsNeedLoading_binaryResponse){
                          Deducer.execute("suppressMessages(library(mfx))\nsuppressMessages(library(lmtest))\nsuppressMessages(library(pscl))\nsuppressMessages(library(xtable))\nsuppressMessages(library(ROCR))");
                          libsNeedLoading_binaryResponse=false;
                        }  
                } else if (modelD.EstimationMethod.equals("Binary Probit")){
                        modelD.method="glm";
                        modelD.family =",family=binomial(link=\"probit\")";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        if (libsNeedLoading_binaryResponse){
                          Deducer.execute("suppressMessages(library(mfx))\nsuppressMessages(library(lmtest))\nsuppressMessages(library(pscl))\nsuppressMessages(library(xtable))\nsuppressMessages(library(ROCR))");
                          libsNeedLoading_binaryResponse=false;
                        }  
                 } else if (modelD.EstimationMethod.equals("Tobit")){
                        modelD.method="tobit";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); tobitvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        leftLimitPanel.setVisible(true);leftLimitText.setVisible(true);rightLimitPanel.setVisible(true);rightLimitText.setVisible(true);
                        if (libsNeedLoading_tobit){
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_tobit=false;
                        }  
                 } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive error model - maximum likelihood")){
                        modelD.method="errorsarlm";
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                     //   vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }
                    } else if (modelD.EstimationMethod.equals("Error Durbin model - maximum likelihood")){
                        modelD.method="errorsarlm";
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                       // vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }      
                        
                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive error model - GMM")){
                        modelD.method="GMerrorsar";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        verbose.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }
                   
                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive lag model - maximum likelihood")){
                        modelD.method="lagsarlm";
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                      //  vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        notQuiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }  
                        
                  } else if (modelD.EstimationMethod.equals("Spatial Durbin model - maximum likelihood")){
                        modelD.method="lagsarlm";
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                      //  vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }  

                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood")){
                        modelD.method="sacsarlm";
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                       // vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }
                   } else if (modelD.EstimationMethod.equals("SAC/SARAR Durbin model - maximum likelihood")){
                        modelD.method="sacsarlm";
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.family ="";
                        weightPanel.setVisible(true);
                       // vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }
                    } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive SAC model - GMM")){
                        modelD.method="gstsls";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); gstslsvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        verbose.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }     
                        
                   } else if (modelD.EstimationMethod.equals("generalized spatial two-stage least squares")){
                        modelD.method="stsls";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                        vcovtypeLabel.setVisible(true); stslsvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }     
                    } else if (modelD.EstimationMethod.equals("Spatial conditional and simultaneous autoregression")){
                        modelD.method="spautolm";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                      //  vcovtypeLabel.setVisible(true); glmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }           
                        
                  } else if (modelD.EstimationMethod.equals("Matrix exponential spatial lag model")){
                        modelD.method="lagmess";
                        modelD.family ="";
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        verbose.setVisible(true);
                        if (libsNeedLoading_errorsarlm){
                          Deducer.execute("suppressMessages(library(splm))");
                          Deducer.execute("suppressMessages(library(AER))");
                          libsNeedLoading_errorsarlm=false;
                        }  


  
                 } else if (modelD.EstimationMethod.equals("Panel - Panel Linear Model")){
                        modelD.method="plm";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        plmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        plmtype.setVisible(true);
                        vcovtypeLabel.setVisible(true); plmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_plm){
                          if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                          Deducer.execute("suppressMessages(library(xtable))\nsuppressMessages(library(AER))");
                          libsNeedLoading_plm=false;
                        }  
                  } else if (modelD.EstimationMethod.equals("Panel - Variable Coefficients")){
                        modelD.method="pvcm";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pvcmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pvcmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                  } else if (modelD.EstimationMethod.equals("Panel - General Feasible GLS")){
                        modelD.method="pggls";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pgglseffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pgglstype.setVisible(true);
                        interceptIncluded.setVisible(true);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                   } else if (modelD.EstimationMethod.equals("Panel - GMM (e.g. for dynamic equations)")){
                        modelD.method="pgmm";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        lagVariablesPanelLabel.setVisible(true);lagVariablesPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pgmmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pgmmtype.setVisible(true);
                        vcovtypeLabel.setVisible(true); pgmmvcovtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                  } else if (modelD.EstimationMethod.equals("Panel - Common Correlated Effects Pooled Estimator")){
                        modelD.method="pcce";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        pccetype.setVisible(true);
                        panelTrendIncluded.setVisible(true);
                        interceptIncluded.setVisible(false);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                   } else if (modelD.EstimationMethod.equals("Panel - Mean Groups")){
                        modelD.method="pmg";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        pmgtype.setVisible(true);
                        panelTrendIncluded.setVisible(true);
                        interceptIncluded.setVisible(false);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                    } else if (modelD.EstimationMethod.equals("Spatial panel model - maximum likelihood")){
                        modelD.method="spml";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        spatialErrorPanelLabel.setVisible(true);spatialErrorPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        effectLabel.setVisible(true);
                        spmleffect.setVisible(true);
                        typeLabel.setVisible(true);
                        spmltype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        zerosInitval.setVisible(true);
                        hess.setVisible(true);
                        quiet.setVisible(true);
                        spatialLagDepIncluded.setVisible(true);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }
                        } else if (modelD.EstimationMethod.equals("Spatial panel model w/ random effects & serial error correlation- max likelihood")){
                        modelD.method="spreml";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        spremlErrorsLabel.setVisible(true);
                        spremlErrors.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        zerosInitval.setVisible(true);
                        hess.setVisible(true);
                        quiet.setVisible(true);
                        spatialLagDepIncluded.setVisible(true);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }    
                        
                    } else if (modelD.EstimationMethod.equals("Spatial panel model - GM")){
                        modelD.method="spgm";
                        modelD.family ="";
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        spgmMomentsLabel.setVisible(true);
                        spgmMoments.setVisible(true);
                        typeLabel.setVisible(true);
                        spgmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        spatialError.setVisible(true);
                        spatialMethodLabel.setVisible(true); spgmMethodType.setVisible(true);
                        verbose.setVisible(true);
                        startPanel.setVisible(false);endPanel.setVisible(false);
                        panelIndexPanel.setVisible(true);
                        if (libsNeedLoading_panel){
                             Deducer.execute("suppressMessages(library(plm))\nsuppressMessages(library(lmtest))");
                             libsNeedLoading_panel=false;
                          }

                  } else if (modelD.EstimationMethod.equals("Vector Autoregression")){
                        modelD.method="VAR";
                        modelD.family ="";
                        modelD.irfOptions.impulseSelection[0] = -1 ; 
                        
                        typeEigen.setSelected(true);
                        includeNone.setSelected(true);
                        noSeasonalDummies.setSelected(true);
                        infoCriterionLabel.setVisible(true);infoCriterion.setVisible(true);
                        varMaxLagLabel.setVisible(true); varMaxLag.setVisible(true);
                        varMaxLag.setModel(varMaxLags);
                        outcomePanel.setVisible(false);
                        contPanel.setVisible(false);
                        endogVarsPanel.setVisible(true);
                        exogVarsPanel.setVisible(true);
                         cointTypeLabel.setVisible(true);typeEigen.setVisible(true);typeTrace.setVisible(true);
                        constantTrendLabel.setVisible(true);
                          includeNone.setVisible(true); includeConstant.setVisible(true);
                          includeTrend.setVisible(true);includeBoth.setVisible(true);
                        seasonalLabel.setVisible(true);
                        noSeasonalDummies.setVisible(true);   quarterlyDummies.setVisible(true);  monthlyDummies.setVisible(true);
                        if (libsNeedLoading_VAR){
                          Deducer.execute("suppressMessages(library(vars))");
                          libsNeedLoading_VAR=false;
                        }  
                   } else if (modelD.EstimationMethod.equals("Cointegration Test - Johansen")){
                        modelD.method="ca.jo";
                        modelD.family ="";
                        typeEigen.setSelected(true);
                        includeNone.setSelected(true);
                        noSeasonalDummies.setSelected(true);
                        varMaxLagLabel.setVisible(true); varMaxLag.setVisible(true);
                        varMaxLag.setModel(cointMaxLags);
                        outcomePanel.setVisible(false);
                        contPanel.setVisible(false);
                        cointTestVarsPanel.setVisible(true);
                        cointExogVarsPanel.setVisible(true);
                        cointTestVarsPanel.setTitle("Variables being tested");
                        cointOptionsLabel.setVisible(true);cointOption.setVisible(true);
                        seasonalLabel.setVisible(true);
                        noSeasonalDummies.setVisible(true);   quarterlyDummies.setVisible(true);  monthlyDummies.setVisible(true);
                        if (libsNeedLoading_cajocapo){
                          Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                          libsNeedLoading_cajocapo=false;
                        } 
                } else if (modelD.EstimationMethod.equals("Cointegration Test - Philips & Ouliaris")){
                        modelD.method="ca.po";
                        modelD.family="";
                        outcomePanel.setVisible(false);
                        contPanel.setVisible(false);
                        cointTestVarsPanel.setVisible(true);
                        exogVarsPanel.setVisible(false);
                        cointTestVarsPanel.setTitle("Variables being tested (1st dependent in 1st-stage)");
                        cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                        cointPOType.setVisible(true);
                        cointPOComponent.setVisible(true);
                        cointPOLag.setVisible(true);
                        seasonalLabel.setVisible(false);
                        noSeasonalDummies.setVisible(false);   quarterlyDummies.setVisible(false);  monthlyDummies.setVisible(false);
                        if (libsNeedLoading_cajocapo){
                          Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                          libsNeedLoading_cajocapo=false;
                        } 
                } else if (modelD.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                        modelD.method="ca.EG";
                        modelD.family="";
                        infoCriterionLabel.setVisible(true);infoCriterion2.setVisible(true);
                        varMaxLagLabel.setVisible(true); varMaxLagEG.setVisible(true);
                        varMaxLagEG.setModel(cointMaxLagsEG);
                        outcomePanel.setVisible(false);
                        contPanel.setVisible(false);
                        cointTestVarsPanel.setVisible(true);
                        exogVarsPanel.setVisible(false);
                        cointTestVarsPanel.setTitle("Variables being tested (1st dependent in 1st-stage)");
                        cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                        cointEGComponent.setVisible(true);
                        seasonalLabel.setVisible(false);
                        noSeasonalDummies.setVisible(false);   quarterlyDummies.setVisible(false);  monthlyDummies.setVisible(false);
                        if (libsNeedLoading_caEG){
                          if (libsNeedLoading_cajocapo){
                             Deducer.execute("suppressMessages(library(urca))\nsuppressMessages(library(zoo))");
                             libsNeedLoading_cajocapo=false;
                          }  
                          Deducer.execute("suppressMessages(library(dynlm))\nsuppressMessages(library(plm))");
                          libsNeedLoading_caEG=false;
                        }  
                } 
                
		modelD = mod;
                
	}
	
	public void updateModel(){
                manModInput.input = manualText.getText();
                modelD.efeglmextra.manualVariablesInput=manModInput.input;
                DefaultListModel termsMPostBar = new DefaultListModel();
                String postBarVariables = "";
                if (manModInput.input.length()==0) { 
                    
                    modelD.numericVars=(DefaultListModel)contPanel.getModel();
                modelD.efeglmextra.endogVarsD=(DefaultListModel)endogVarsPanel.getModel();
                modelD.efeglmextra.cointTestVarsD=(DefaultListModel)cointTestVarsPanel.getModel();
                modelD.efeglmextra.exogVarsD=(DefaultListModel)exogVarsPanel.getModel();
                modelD.efeglmextra.cointExogVarsD=(DefaultListModel)cointExogVarsPanel.getModel();
                    modelD.outcomeVars = (DefaultListModel)outcomePanel.getModel();
                modelD.EstimationMethod = EstimationMethodDialog;
                

                
                 if ((!modelD.EstimationMethod.equals("Vector Autoregression"))&&
                    (!modelD.EstimationMethod.equals("Cointegration Test - Johansen"))&&
                    (!modelD.EstimationMethod.equals("Cointegration Test - Philips & Ouliaris"))&&
                    (!modelD.EstimationMethod.equals("Cointegration Test - Engle & Granger"))
                         )    
		   if(modelD.outcomes.size()==0)
			modelD.outcomes = (DefaultListModel) outcome.getModel();
	   	   else if(!modelD.outcomes.getElementAt(0).toString().contains((String)modelD.outcomeVars.get(0)))
			modelD.outcomes = (DefaultListModel) outcome.getModel();
                } 
                else {
                    Integer lastcut = 0;
                    Boolean tildafound = false;
                    String outcomesMF = "";
                    termsMF = new DefaultListModel();
                    String[] manBarSplit;
                    String preBarVariables ="";
                    
                    if (manModInput.input.contains("|")){
                       manBarSplit = manModInput.input.split("\\|");
                       if (manBarSplit[0].length()>0)
                          preBarVariables = manBarSplit[0].replaceAll(" ", "");
                       if (manBarSplit[1].length()>0)
                          postBarVariables = manBarSplit[1].replaceAll(" ", "");           
                    } else {
                       preBarVariables = manModInput.input.replaceAll(" ","");
                    }
                    
                    if (preBarVariables.contains("~")){
                        for (int i=0;i<preBarVariables.length();i++){
                            if (!tildafound){
                                if (preBarVariables.substring(i,i+1).equals("~"))
                                {
                                   tildafound = true;
                                   outcomesMF = preBarVariables.substring(0,i);
                                   lastcut = i;
                                }
                            }
                            else {

                                if (preBarVariables.substring(i,i+1).equals("+")||(preBarVariables.substring(i,i+1).equals(",")&!preBarVariables.contains("lag"))) {
                                    termsMF.addElement(preBarVariables.substring(lastcut+1,i));
                                    lastcut = i;
                                }
                                else if ((i+1)==preBarVariables.length()) {
                                    termsMF.addElement(preBarVariables.substring(lastcut+1,i+1));
                                }
                            }
                        }
                    } else {
                            lastcut = -1;
                            for (int i=0;i<preBarVariables.length();i++){
                                if (preBarVariables.substring(i,i+1).equals("+")||preBarVariables.substring(i,i+1).equals(",")) {
                                    termsMF.addElement(preBarVariables.substring(lastcut+1,i));
                                    lastcut = i;
                                }
                                else if ((i+1)==preBarVariables.length()) {
                                    termsMF.addElement(preBarVariables.substring(lastcut+1,i+1));
                                }
                            }
                    }
                    
                     lastcut = -1;
                     for (int i=0;i<postBarVariables.length();i++){
                            if (postBarVariables.substring(i,i+1).equals("+")||(postBarVariables.substring(i,i+1).equals(",")&!postBarVariables.contains("lag"))) {
                                termsMPostBar.addElement(postBarVariables.substring(lastcut+1,i));
                                lastcut = i;
                            }
                            else if ((i+1)==postBarVariables.length()) {
                                termsMPostBar.addElement(postBarVariables.substring(lastcut+1,i+1));
                            }
                        
                    }
                    
                    modelD.outcomeVars.clear();
                    modelD.outcomeVars.addElement(outcomesMF);
                   /* modelD.outcomeVars.addElement("young");*/
                    modelD.numericVars = termsMF;
                    modelD.efeglmextra.endogVarsD = termsMF;
                    modelD.efeglmextra.cointTestVarsD= termsMF;
                    if (termsMPostBar.getSize()>0){
                        modelD.efeglmextra.exogVarsD=termsMPostBar;
                        modelD.efeglmextra.cointExogVarsD=termsMPostBar;  
                    } else { 
                        modelD.efeglmextra.exogVarsD=(DefaultListModel)exogVarsPanel.getModel();
                        modelD.efeglmextra.cointExogVarsD=(DefaultListModel)cointExogVarsPanel.getModel();
                    }
                                      
                }    

		modelD.data = variableSelector.getSelectedData();
                modelD.subset = "";
		modelD.subset = subset.getText();
                modelD.panelIndex = panelIndexText.getText();
                modelD.start = startText.getText();
                modelD.end = endText.getText();
                modelD.weights = new DefaultListModel();
                if (!(EstimationMethodDialog.equals("Ordinary Least Squares")))
		   modelD.weights = (DefaultListModel) weightPanel.getModel();
                modelD.plmeffect =(String)plmeffects.getSelectedItem();
                modelD.pvcmeffect =(String)pvcmeffects.getSelectedItem();
                modelD.pgglseffect =(String)pgglseffects.getSelectedItem();
                modelD.pgmmeffect =(String)pgmmeffects.getSelectedItem();
                modelD.spatialErrorPanel =(String)spatialErrorPanelList.getSelectedItem();
                modelD.spmleffect =(String)spmleffects.getSelectedItem();
                modelD.spremlErrors =(String)spremlErrorsList.getSelectedItem();
                modelD.spgmMoments = (String)spgmMomentsList.getSelectedItem();
                modelD.spmlPanelType =(String)spmlPanelTypes.getSelectedItem();
                modelD.plmPanelType =(String)plmPanelTypes.getSelectedItem();
                modelD.pvcmPanelType =(String)pvcmPanelTypes.getSelectedItem();
                modelD.pgglsPanelType =(String)pgglsPanelTypes.getSelectedItem();
                modelD.pgmmPanelType =(String)pgmmPanelTypes.getSelectedItem();
                modelD.pccePanelType =(String)pccePanelTypes.getSelectedItem();
                modelD.pmgPanelType =(String)pmgPanelTypes.getSelectedItem();
                modelD.cipsURTPanelType =(String)cipsURTPanelTypes.getSelectedItem();
                modelD.EstimationMethod = EstimationMethodDialog;
                infoCriterionLabel.setVisible(false);infoCriterion.setVisible(false);infoCriterion2.setVisible(false);
                cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                varMaxLagLabel.setVisible(false); varMaxLag.setVisible(false); varMaxLagEG.setVisible(false);
                contPanel.setVisible(true);
                endogVarsPanel.setVisible(false);
                cointTestVarsPanel.setVisible(false);
                exogVarsPanel.setVisible(false);
                cointExogVarsPanel.setVisible(false); 
                instrumentPanel.setVisible(false);
                weightPanel.setVisible(false);
                modelD.factorVars.removeAllElements();
                effectLabel.setVisible(false);
                plmeffect.setVisible(false);pvcmeffect.setVisible(false);pgglseffect.setVisible(false);pgmmeffect.setVisible(false);
                spmleffect.setVisible(false);spremlErrors.setVisible(false);spgmMoments.setVisible(false);
                typeLabel.setVisible(false);
                theDialog.plmtype.setVisible(false);theDialog.pvcmtype.setVisible(false);
                theDialog.pgglstype.setVisible(false);theDialog.pgmmtype.setVisible(false);
                theDialog.pccetype.setVisible(false);theDialog.pmgtype.setVisible(false);
                theDialog.spmltype.setVisible(false);theDialog.spgmtype.setVisible(false);
                theDialog.cipsURTtype.setVisible(false);
                modelD.efeglmextra.classicView = false;
                panelTrendIncluded.setVisible(false);
                interceptIncluded.setVisible(false);
                spatialLagDepIncluded.setVisible(false);
                cointTypeLabel.setVisible(false);typeEigen.setVisible(false);typeTrace.setVisible(false);
                constantTrendLabel.setVisible(false);
                      includeNone.setVisible(false); includeConstant.setVisible(false);
                      includeTrend.setVisible(false);includeBoth.setVisible(false);
                seasonalLabel.setVisible(false);
                      noSeasonalDummies.setVisible(false);   quarterlyDummies.setVisible(false);  monthlyDummies.setVisible(false);
                
                      modelD.numericVars.toArray();
                modelD.efeglmextra.previousEstimation = false; 
                modelD.PanelEstMethod = false;
                leftLimitPanel.setVisible(false);leftLimitText.setVisible(false);rightLimitPanel.setVisible(false);rightLimitText.setVisible(false);
                listwButton.setVisible(true);
                
                if (modelD.EstimationMethod.equals("Ordinary Least Squares")){
                        if ((modelD.outcomeVars.toString().contains("lag(")||modelD.outcomeVars.toString().contains("diff("))||
                             (modelD.numericVars.toString().contains("lag(")||modelD.numericVars.toString().contains("diff("))
                                ){
                           modelD.method="dynlm";
                        } else {
                           modelD.method="lm";
                        }
                        modelD.family="";
                        modelD.terms = modelD.numericVars;

                         
                        modelD.instruments="";
                        interceptIncluded.setVisible(true);
                } else if (modelD.EstimationMethod.equals("Weighted Least Squares")){  
                        if ((modelD.outcomeVars.toString().contains("lag(")||modelD.outcomeVars.toString().contains("diff("))||
                             (modelD.numericVars.toString().contains("lag(")||modelD.numericVars.toString().contains("diff("))
                                ){
                           modelD.method="dynlm";
                        } else {
                           modelD.method="lm";
                        }
                        modelD.family="";
                        modelD.terms = modelD.numericVars;
                        modelD.instruments="";
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                } else if (modelD.EstimationMethod.equals("Binary Logit")){
                        modelD.method="glm";
                        modelD.family =",family=binomial(link=\"logit\")";
                        modelD.terms = modelD.numericVars;
                        modelD.instruments="";
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                } else if (modelD.EstimationMethod.equals("Binary Probit")){
                        modelD.method="glm";
                        modelD.family =",family=binomial(link=\"probit\")";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                } else if (modelD.EstimationMethod.equals("Tobit")){
                        modelD.efeglmextra.leftLimitText = leftLimitText.getText();
                        modelD.efeglmextra.rightLimitText = rightLimitText.getText();
                        modelD.end = endText.getText();
                        modelD.method="tobit";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                        leftLimitPanel.setVisible(true);leftLimitText.setVisible(true);rightLimitPanel.setVisible(true);rightLimitText.setVisible(true);
                 } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive error model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.end = endText.getText();
                        modelD.method="errorsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                 } else if (modelD.EstimationMethod.equals("Error Durbin model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.end = endText.getText();
                        modelD.method="errorsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive error model - GMM")){
                        modelD.end = endText.getText();
                        modelD.method="GMerrorsar";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true); 
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        verbose.setVisible(true);
                        spatialLagDepIncluded.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                     } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive lag model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.end = endText.getText();
                        modelD.method="lagsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        notQuiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("Spatial Durbin model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.end = endText.getText();
                        modelD.method="lagsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive SAC model - GMM")){
                        modelD.end = endText.getText();
                        modelD.method="gstsls";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        verbose.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = false;
                        modelD.end = endText.getText();
                        modelD.method="sacsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("SAC/SARAR Durbin model - maximum likelihood")){
                        modelD.efeglmextra.SpatialDurbin = true;
                        modelD.end = endText.getText();
                        modelD.method="sacsarlm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        quiet.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                  } else if (modelD.EstimationMethod.equals("generalized spatial two-stage least squares")){
                        modelD.end = endText.getText();
                        modelD.method="stsls";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                } else if (modelD.EstimationMethod.equals("Spatial conditional and simultaneous autoregression")){
                        modelD.end = endText.getText();
                        modelD.method="spautolm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                } else if (modelD.EstimationMethod.equals("Matrix exponential spatial lag model")){
                        modelD.end = endText.getText();
                        modelD.method="lagmess";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                        listwButton.setVisible(true);
                        zeroPolicyLabel.setVisible(true); zeroPolicyType.setVisible(true); 
                        spatialMethodLabel.setVisible(true); spatialMethodType.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                } else if (modelD.EstimationMethod.equals("Two-Stage Least Squares")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="ivreg";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        interceptIncluded.setVisible(true);
                 } else if (modelD.EstimationMethod.equals("Panel - Panel Linear Model")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="plm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        plmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        plmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                  } else if (modelD.EstimationMethod.equals("Panel - Variable Coefficients")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="pvcm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pvcmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pvcmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                   } else if (modelD.EstimationMethod.equals("Panel - General Feasible GLS")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="pggls";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pgglseffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pgglstype.setVisible(true);
                        interceptIncluded.setVisible(false);
                    } else if (modelD.EstimationMethod.equals("Panel - GMM (e.g. for dynamic equations)")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="pgmm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        pgmmeffect.setVisible(true);
                        typeLabel.setVisible(true);
                        pgmmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                     } else if (modelD.EstimationMethod.equals("Panel - Common Correlated Effects Pooled Estimator")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="pcce";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        pccetype.setVisible(true);
                        panelTrendIncluded.setVisible(true);
                        interceptIncluded.setVisible(false);
                     } else if (modelD.EstimationMethod.equals("Panel - Mean Groups")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="pmg";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        pmgtype.setVisible(true);
                        panelTrendIncluded.setVisible(true);
                        interceptIncluded.setVisible(false);
                      } else if (modelD.EstimationMethod.equals("Spatial panel model - maximum likelihood")){                         
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="spml";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        listwButton.setVisible(true);
                        weightPanel.setVisible(true);
                        effectLabel.setVisible(true);
                        spmleffect.setVisible(true);
                        typeLabel.setVisible(true);
                        spmltype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        zerosInitval.setVisible(true);
                        hess.setVisible(true);
                        quiet.setVisible(true);
                        spatialLagDepIncluded.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                      } else if (modelD.EstimationMethod.equals("Spatial panel model w/ random effects & serial error correlation- max likelihood")){                         
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="spreml";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        listwButton.setVisible(true);
                        weightPanel.setVisible(true);
                        spremlErrors.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        zerosInitval.setVisible(true);
                        hess.setVisible(true);
                        quiet.setVisible(true);
                        spatialLagDepIncluded.setVisible(true); 
                        modelD.efeglmextra.classicView = true;
                        
                      } else if (modelD.EstimationMethod.equals("Spatial panel model - GM")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="spgm";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        modelD.PanelEstMethod = true;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        spgmMoments.setVisible(true);
                        spgmtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                        listwButton.setVisible(true);
                        spatialError.setVisible(true);
                        spatialMethodLabel.setVisible(true); spgmMethodType.setVisible(true);
                        verbose.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                      } else if (modelD.EstimationMethod.equals("Panel - CIPS unit root test")){
                        if (termsMPostBar.getSize()>0)
                           modelD.efeglmextra.instrumentsD = termsMPostBar;
                        else 
                           modelD.efeglmextra.instrumentsD=(DefaultListModel)instrumentPanel.getModel();
                        modelD.method="cipstest";
                        modelD.family ="";
                        modelD.terms = modelD.numericVars;
                        instrumentPanel.setVisible(true);
                        weightPanel.setVisible(true);
                        typeLabel.setVisible(true);
                        cipsURTtype.setVisible(true);
                        interceptIncluded.setVisible(false);
                 } else if (modelD.EstimationMethod.equals("Vector Autoregression")){
                        modelD.method="VAR";
                        modelD.family ="";        
                        modelD.terms = modelD.efeglmextra.endogVarsD;
                        contPanel.setVisible(false);
                        endogVarsPanel.setVisible(true);
                        exogVarsPanel.setVisible(true);
                        infoCriterionLabel.setVisible(true);infoCriterion.setVisible(true);
                        varMaxLagLabel.setVisible(true); varMaxLag.setVisible(true);
                        varMaxLag.setModel(varMaxLags);
                        theDialog.outcomePanel.setVisible(false);
                        theDialog.contPanel.setTitle("Variables being tested");
                         cointTypeLabel.setVisible(true);typeEigen.setVisible(true);typeTrace.setVisible(true);
                        constantTrendLabel.setVisible(true);
                        includeNone.setVisible(true); includeConstant.setVisible(true);includeTrend.setVisible(true);
                        /*lrTransitoryLabel.setVisible(true); longRun.setVisible(true); transitory.setVisible(true);*/
                        seasonalLabel.setVisible(true);
                        noSeasonalDummies.setVisible(true);   quarterlyDummies.setVisible(true);  monthlyDummies.setVisible(true);
                        modelD.varOptions.maxLag = (String)varMaxLags.getSelectedItem().toString();
                        modelD.varOptions.infoCriterion = (String)infoCriterions.getSelectedItem().toString();
                        modelD.efeglmextra.classicView = true;
                   } else if (modelD.EstimationMethod.equals("Cointegration Test - Johansen")){
                        String cointOptionChosen = (String)cointOption.getSelectedItem();    
                        if ((cointOptionChosen.substring(0,3).equals("lev"))) {
                           modelD.method="cajolst";
                           }
                        else {
                           modelD.method="ca.jo";
                        }
                        modelD.family ="";
                        modelD.terms = modelD.efeglmextra.cointTestVarsD;
                        cointTestVarsPanel.setVisible(true);
                        cointExogVarsPanel.setVisible(true);
                        contPanel.setTitle("Variables being tested");
                        varMaxLagLabel.setVisible(true); varMaxLag.setVisible(true);
                        varMaxLag.setModel(cointMaxLags);
                        outcomePanel.setVisible(false);
                        contPanel.setTitle("Variables being tested");
                        cointOptionsLabel.setVisible(true);cointOption.setVisible(true);
                        seasonalLabel.setVisible(true);
                        noSeasonalDummies.setVisible(true);   quarterlyDummies.setVisible(true);  monthlyDummies.setVisible(true);
                        modelD.varOptions.maxLag = (String)cointMaxLags.getSelectedItem().toString();
                        modelD.efeglmextra.classicView = true;
                } else if (modelD.EstimationMethod.equals("Cointegration Test - Philips & Ouliaris")){
                        modelD.efeglmextra.cointPOType = cointPOType.getSelectedItemText();
                        modelD.efeglmextra.cointPOOptionChosen = cointPOComponent.getSelectedItemText();
                        modelD.efeglmextra.cointPOLagChosen = cointPOLag.getSelectedItemText();
                        modelD.method="ca.po";
                        modelD.family ="";
                        modelD.terms = modelD.efeglmextra.cointTestVarsD;
                        cointTestVarsPanel.setVisible(true);
                        contPanel.setVisible(false);
                        outcomePanel.setVisible(false);
                        cointTestVarsPanel.setTitle("Variables being tested (1st dependent in 1st-stage)");
                        cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                        cointPOType.setVisible(true);
                        cointPOComponent.setVisible(true);
                        cointPOLag.setVisible(true);
                        modelD.efeglmextra.classicView = true;
                } else if (modelD.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                        modelD.efeglmextra.cointEGOptionChosen = cointEGComponent.getSelectedItemText();
                        modelD.method="ca.EG";
                        modelD.family ="";
                        modelD.terms = modelD.efeglmextra.cointTestVarsD;
                        cointTestVarsPanel.setVisible(true);
                        contPanel.setVisible(false);
                        infoCriterionLabel.setVisible(true);infoCriterion2.setVisible(true);
                        varMaxLagLabel.setVisible(true); varMaxLagEG.setVisible(true);
                        varMaxLagEG.setModel(cointMaxLagsEG);
                        outcomePanel.setVisible(false);
                        cointTestVarsPanel.setTitle("Variables being tested (1st dependent in 1st-stage)");
                        cointOptionsLabel.setVisible(false);cointOption.setVisible(false);
                        cointEGComponent.setVisible(true);
                        modelD.unitRootTestOptions.maxLagEG = (String)cointMaxLagsEG.getSelectedItem().toString();
                        modelD.unitRootTestOptions.infoCriterion2 = (String)infoCriterions2.getSelectedItem().toString();
                        modelD.efeglmextra.classicView = true;
                } 
                modelD.efeglmextra.zeroPolicyType=(String)zeroPolicyTypeList.getSelectedItem();
                modelD.efeglmextra.spatialMethodType=(String)spatialMethodTypeList.getSelectedItem();
                modelD.efeglmextra.spgmMethodType=(String)spgmMethodTypeList.getSelectedItem();
                modelD.spremlErrors=(String)spremlErrorsList.getSelectedItem();
                modelD.spgmMoments=(String)spgmMomentsList.getSelectedItem();
                modelD.efeglmextra.zerosInitval = zerosInitval.isSelected();
                modelD.efeglmextra.hess = hess.isSelected();
                modelD.efeglmextra.spatialError = spatialError.isSelected();
                modelD.efeglmextra.quiet = quiet.isSelected();
                modelD.efeglmextra.notQuiet = notQuiet.isSelected();
                modelD.efeglmextra.verbose = verbose.isSelected();
                
          	modelD.glmvcov =(String)glmvcovsDialog.getSelectedItem();
                modelD.tobitvcov =(String)tobitvcovsDialog.getSelectedItem();
                modelD.plmvcov =(String)plmvcovsDialog.getSelectedItem();
                modelD.pgmmvcov =(String)pgmmvcovsDialog.getSelectedItem();
                modelD.stslsvcov =(String)stslsvcovsDialog.getSelectedItem();
                modelD.gstslsvcov =(String)gstslsvcovsDialog.getSelectedItem();
                modelD.efeglmextra.glmvcovOrig = modelD.glmvcov;
                modelD.efeglmextra.plmvcovOrig = modelD.plmvcov;
                modelD.efeglmextra.pgmmvcovOrig = modelD.pgmmvcov;
                modelD.efeglmextra.stslsvcovOrig = modelD.stslsvcov;
                modelD.efeglmextra.gstslsvcovOrig = modelD.gstslsvcov;
                modelD.outcomes = modelD.outcomeVars;
                modelD.efeglmextra.origDepVar=modelD.outcomes.toString();
                modelD.efeglmextra.origDepVarMod =modelD.outcomes;
                
                modelD.efeglmextra.endogVariables = modelD.efeglmextra.endogVars;
                modelD.efeglmextra.cointTestVariables = modelD.efeglmextra.cointTestVars;
                modelD.efeglmextra.interceptIncluded = interceptIncluded.isSelected();
                modelD.efeglmextra.spatialLagDepIncluded = spatialLagDepIncluded.isSelected();
                modelD.efeglmextra.panelTrendIncluded = panelTrendIncluded.isSelected();
                modelD.varOptions.cointOption=(String)cointOption.getSelectedItem();
                
                if (typeTrace.isSelected()) modelD.varOptions.cointType = "trace"; 
                else modelD.varOptions.cointType = "eigen";
                
                if (includeNone.isSelected()) modelD.varOptions.constantTrend = "none"; 
                else if (includeConstant.isSelected()) modelD.varOptions.constantTrend = "const";
                else if (includeBoth.isSelected()) modelD.varOptions.constantTrend = "both";
                else modelD.varOptions.constantTrend = "trend";
                
                if (noSeasonalDummies.isSelected()) modelD.varOptions.seasonalDummies = ""; 
                else if (quarterlyDummies.isSelected()) modelD.varOptions.seasonalDummies = ",season=4";
                else modelD.varOptions.seasonalDummies = ",season=12";                    

	}
	public void setDataName(String dataName){
		if(!dataName.equals(variableSelector.getSelectedData())){
			variableSelector.setSelectedData(dataName);
		}
	}
      
        protected String makeformulaparam(DefaultListModel outcomes, DefaultListModel terms, Boolean intercept) {
                String xx;
                if (intercept) {
                   xx = outcomes.get(0) +" = b(0)";
                   if (terms.getSize()>0) {
                      xx = xx + " + b(1)*"+terms.get(0);
                  }
                }
                else{
                   xx = outcomes.get(0) +" = ";
                   if (terms.getSize()>0) {
                      xx = xx + "b(1)*"+terms.get(0);
                   }
                  }
                if (terms.getSize()>1) {
                   for(int i=1;i<terms.getSize();i++)
			xx = xx + " + b("+(i+1)+")*"+terms.get(i);
                }
                return xx;
        };
        
        
	public boolean valid(){
             if (manModInput.input.length()==0){
		if((modelD.outcomeVars.size()<1)&&!((EstimationMethodDialog.equals("Cointegration Test - Johansen"))||
                                                   (EstimationMethodDialog.equals("Cointegration Test - Philips & Ouliaris"))||
                                                   (EstimationMethodDialog.equals("Cointegration Test - Engle & Granger"))||
                                                   (EstimationMethodDialog.equals("Vector Autoregression"))
                                                   ))  {
			JOptionPane.showMessageDialog(this, "Please specify a response variable");
			return false;
		}
		if(modelD.factorVars.size()< 1 && modelD.numericVars.size() <1 && !(modelD.efeglmextra.interceptIncluded)){
			JOptionPane.showMessageDialog(this, "Please specify a predictor variable (numeric or factor).");
			return false;
		}
             } 
		return true;
	}

	private void completed(){
	}

        public void continueClicked(){
            	updateModel();
		if(!valid())
			return;
	
                this.dispose();
		GMExplorer exp = new GMExplorer(modelD,"GLM",sizeAdjuster);
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
        
        
        public class ManualModelDialog extends JDialog implements ActionListener{
		private JCheckBox keep;
		private OkayCancelPanel okcan;
		private JTextField resultName;
		private JLabel name;
                private JPanel modelManual;
                private JScrollPane  modelManualScroller;
                private JTextArea modelManualText;
                private JButton runRegButton;
		
		public ManualModelDialog(JDialog d,ManualModelInput opt) {
			super(d);
			initGUI();
			this.setOptions(opt);
		}
		
		public void setOptions(ManualModelInput opt){
			if(opt.input=="")
				modelManualText.setText("");
			else
				modelManualText.setText(opt.input);
			keep.setSelected(opt.keep);
		}
		
		public ManualModelInput getOptions(){
			ManualModelInput opt = new ManualModelInput();
			if(modelManualText.getText()!="")
				opt.input = modelManualText.getText();
			else
				opt.input="";
			opt.keep = keep.isSelected();
			return opt;
		}
		
		private void initGUI() {
			try {
				{
                                        AnchorLayout thisLayout = new AnchorLayout();
                                        getContentPane().setLayout(thisLayout);
					{
						keep = new JCheckBox();
					}

                                        {
                                                {
                                                modelManual = new JPanel();
                                                getContentPane().add(modelManual , new AnchorConstraint(14, 994, 410, 20,
                                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE));
                                                BorderLayout modelManualLayout = new BorderLayout();
						modelManual.setLayout(modelManualLayout);
                                                modelManual.setBorder(BorderFactory.createTitledBorder("Manual alternative: provide linear equation below, e.g. log(salary) ~ educ + I(educ^2) + log(age) + age*educ"));
                                                modelManual.setPreferredSize(new java.awt.Dimension(580, 75));
                                                }                                           
                                                {
                                                   modelManualScroller = new JScrollPane();
                                                   modelManual.add(modelManualScroller, BorderLayout.CENTER);
							{
                                                        	modelManualText = new JTextArea();
								modelManualScroller.setViewportView(modelManualText);
								modelManualText.setFont(new java.awt.Font("Monospaced",0,11));
								modelManualText.setEditable(true);
							}
						}  
                                        
                                        }
					{
						resultName = new JTextField();
					}
					{
						okcan = new OkayCancelPanel(false,false,this);
						getContentPane().add(okcan);
						okcan.setBounds(297, 95, 160, 40);
					}
				}
				this.setTitle("Manual Formula Entry");
                                this.setFont(font12);
				this.setResizable(false);
				this.setSize(588, 165);
			} catch (Exception e) {
				new ErrorMsg(e);
			}
		}

		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
			if(cmd =="Cancel"){
				this.dispose();
			}else if(cmd == "OK"){
				manModInput.input = modelManualText.getText();
				this.dispose();                     
			}
			
		}
	}

        
        
	public void actionPerformed(ActionEvent e) {
              Deducer.eval("if (!exists(\".hansel.working.env\")) {\n" +
                 "    .hansel.working.env <- new.env(parent=emptyenv())\n" +
                     "}");
              //The following code creates the tryCatch.W.E function provided in a demo in base-R.
              //Deducer.eval(
              //  "eval(parse(text=paste(gsub('>','',gsub('\t','',gsub('[+]','\n',capture.output(demo(error.catching,ask=FALSE))[24:34]))),collapse='')))"
              //  );
		String cmd = e.getActionCommand();
               if (cmd=="Run") {
                        continueClicked();
                }else if(cmd == "Manual Formula Entry"){
			ManualModelDialog manMod = new ManualModelDialog(this,manModInput);
			manMod.setLocationRelativeTo(this);
			manMod.setVisible(true);
		}else if(cmd=="Cancel"){
			this.setVisible(false);
                }else if(cmd=="Reset"){
			reset();
                }else if(cmd=="listw"){
		 Deducer.eval("J('hansel.SpatialFileAssociations')$runit(\"spatial file associations\","+
                 "c(Filter( function(x)  ('data.frame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),\" \"),"+
                 
                 "c(Filter( function(x)  ('listw' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('matrix' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('nb' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                   "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() )," 
                                + "\" \"),"+
                                
                 "c(Filter( function(x)  ('listw' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('matrix' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('nb' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                   "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() )," 
                                + "\" \")"
                                + "   ) "); 
                }else if(((String)newVariables.getSelectedItem()=="transform variables page")){
                                            /* needsRLocked=true;*/
                       String currentDataName = variableSelector.getSelectedData();
                     
			HanselTransformDialog trans =new HanselTransformDialog(currentDataName,this); 
			trans.setLocationRelativeTo(this);
			trans.setVisible(true);
			WindowTracker.addWindow(trans);
                        
                        variableSelector.repaint();

                      newVariables.setSelectedIndex(0);    
                }else if(((String)newVariables.getSelectedItem()=="log")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "log("+(String) sel[i]+ ")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="square")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "I("+(String) sel[i]+ "^2)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
               }else if(((String)newVariables.getSelectedItem()=="cube")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "I("+(String) sel[i]+ "^3)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                 }else if(((String)newVariables.getSelectedItem()=="square root")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "sqrt("+(String) sel[i]+")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="inverse")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "I(1/"+(String) sel[i]+")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                 }else if(((String)newVariables.getSelectedItem()=="inverse of square")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "I(1/"+(String) sel[i]+"^2)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                                 }else if(((String)newVariables.getSelectedItem()=="negative")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "-"+(String) sel[i];
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="lag 1 period")||
                        ((String)newVariables.getSelectedItem()=="lags 1 to ..") ||
                        ((String)newVariables.getSelectedItem()=="1st difference")
                        ){
                    
                    String data = variableSelector.getSelectedData();
                    String dName = data;
                    dName = dName.replace("__","");
                    Boolean existsTS = false;
                  
                    String existsTSasString = new String();
                    if (dName.equals(data)) {
                        try {
                          existsTSasString = Deducer.eval("as.character(exists(\"T.S."+dName+"\"))").asString();  
                        }catch(Exception err){
                          new ErrorMsg(err);
                        }
                       existsTS = existsTSasString.equals("TRUE");
                    } else if ((dName+"__").equals(data)) 
                       existsTS = true;
                    
                    if (!existsTS){
                             JOptionPane.showMessageDialog(null, "Need mirror to time series object (e.g. ts, zoo) for dynamic variable ");
                             return;
			}
                           
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        
                        if(((String)newVariables.getSelectedItem()=="lag 1 period")){
                        String term;
                           for(int i =0;i<sel.length;i++){
                               term = "lag("+(String) sel[i]+ ",-1)";
                               if(!variableSelector.toString().contains(term))
				   variableSelector.add(term);
                           }
                        }
                        if(((String)newVariables.getSelectedItem()=="lags 1 to ..")){
                        String maxlag = JOptionPane.showInputDialog(null, "lags 1 ... (enter max lag)"); 
                        String term;
                           for(int i =0;i<sel.length;i++){
                              for(int j =1;j<=Integer.parseInt(maxlag);j++){ 
                                  term = "lag("+(String) sel[i]+ ",-"+j+")";
                                  if(!variableSelector.toString().contains(term))
				      variableSelector.add(term);
                              }
                           }
                        }
                        if(((String)newVariables.getSelectedItem()=="1st difference")){
                        String term;
                           for(int i =0;i<sel.length;i++){
                               term = "diff("+(String) sel[i]+ ")";
                               if(!variableSelector.toString().contains(term))
				   variableSelector.add(term);
                           }
                        }

                      Deducer.execute("suppressMessages(library(dynlm))");
                      newVariables.setSelectedIndex(0);      
                }else if(((String)newVariables.getSelectedItem()=="multiply 2 variables")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<2){
				JOptionPane.showMessageDialog(null, "Factorial interaction (\"*\") requires at least two variables.\nPlease select two or more variables");
				return;
			}
			String term = (String) sel[0];
			for(int i =1;i<sel.length;i++){
				term+="*"+sel[i];
			}
                        if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                        newVariables.setSelectedIndex(0);
                 }else if(((String)newVariables.getSelectedItem()=="2-way")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<2){
				JOptionPane.showMessageDialog(null, "Two way interactions requires at least two variables.\nPlease select two or more variables");
				return;
			}
                        String term = (String) sel[0];
			for(int i =1;i<sel.length;i++){
				term+="+"+sel[i];
			}
			term="("+term+")^2";
                        if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                        newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="3-way")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<3){
				JOptionPane.showMessageDialog(null, "Three way interactions requires at least three variables.\nPlease select three or more variables");
				return;
			}
                        String term = (String) sel[0];
			for(int i =1;i<sel.length;i++){
				term+="+"+sel[i];
			}
			term="("+term+")^3";
                        if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                        newVariables.setSelectedIndex(0);
		
               }else if(((String)newVariables.getSelectedItem()=="interaction")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<2){
				JOptionPane.showMessageDialog(null, "interaction (\":\") requires at least two variables.\nPlease select two or more variables");
				return;
			}
			String term = (String) sel[0];
			for(int i =1;i<sel.length;i++){
				term+=":"+sel[i];
			}
                        if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                        newVariables.setSelectedIndex(0);
               }else if(((String)newVariables.getSelectedItem()=="nested")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length!=2){
				JOptionPane.showMessageDialog(null, "Please select two variables");
				return;
			}           
                        int result = JOptionPane.showOptionDialog(null, "Nesting", "Nesting", JOptionPane.OK_CANCEL_OPTION, 
										JOptionPane.QUESTION_MESSAGE, null, new String[]{sel[0]+" %in% "+sel[1],sel[1]+" %in% "+sel[0]}, null);
                        String term;
			if(result==JOptionPane.OK_OPTION)
				term=sel[0]+" %in% "+sel[1];
			else
				term=sel[1]+" %in% "+sel[0];
                        if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                        newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="as factor")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "as.factor("+(String) sel[i]+ ")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="as numeric")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "as.numeric("+(String) sel[i]+ ")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                }else if(((String)newVariables.getSelectedItem()=="split")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "I("+(String) sel[i]+ ">median("+(String) sel[i]+"))";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                 }else if(((String)lagVariablesPanel.getSelectedItem()=="lag(_,1)")){
                        
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "lag("+(String) sel[i]+ ",1)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      lagVariablesPanel.setSelectedIndex(0);
                        
                 }else if(((String)lagVariablesPanel.getSelectedItem()=="lag(_,0:1)")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "lag("+(String) sel[i]+ ",0:1)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      lagVariablesPanel.setSelectedIndex(0);
                 }else if(((String)lagVariablesPanel.getSelectedItem()=="lag(_,1:2)")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "lag("+(String) sel[i]+ ",1:2)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      lagVariablesPanel.setSelectedIndex(0);
                 }else if(((String)lagVariablesPanel.getSelectedItem()=="lag(_,start:end)")){
                     if (!oldAction) {                     
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                       GMExplorerPanelGMMLagsOptions panelLagsOptions = new GMExplorerPanelGMMLagsOptions(this,modelD,startLagDiag,endLagDiag,lagVariablesRun);
                       oldAction = true;
                       panelLagsOptions.setLocationRelativeTo(this);
                       panelLagsOptions.setVisible(true);
                       lagVariablesPanel.setSelectedIndex(0);

                         if (modelD.efeglmextra.panelVariableLagRun) {
                                String term;
                                for(int i =0;i<sel.length;i++){
                                    term = "lag("+(String) sel[i]+ ","+modelD.efeglmextra.panelVariableLagStart+":"+modelD.efeglmextra.panelVariableLagEnd+")";
                                    if(!variableSelector.toString().contains(term))
                                        variableSelector.add(term);
                                lagVariablesRun = false;
                               }
                                
                           }
                            oldAction = false;
                            return;
                        }
                     
                     
                 } else if(((String)newVariables.getSelectedItem()=="manual entry")){                  
                        String term = JOptionPane.showInputDialog(null, "Enter new variable's formula"); 
                            term = "I("+(String) term+ ")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                      newVariables.setSelectedIndex(0);     
                }
	}

}