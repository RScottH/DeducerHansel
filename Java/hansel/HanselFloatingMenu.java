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

* The code from those packages used in this one most notably come from
  GLMDialog.java and Deducer.java found in the Deducer package.

The current file made adjustments to that earlier java code on 2013-07-04 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Component; 

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
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.widgets.*;
        
import org.rosuda.deducer.toolkit.SingletonDJList;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.menu.SubsetPanel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import javax.swing.ListModel;


public class HanselFloatingMenu  extends JDialog {
        
        ConsoleListener cListener =  new ConsoleListener();
        protected JPanel modelPanel;
        protected JScrollPane modelScroller;
        protected JList modelList;
        protected JPanel dataPanel;
        protected JScrollPane dataScroller;
        protected JList dataList;
        public DefaultListModel modelListTerms;
    
	public VariableSelectorWidget variableSelector;
        private JCheckBox interceptIncluded ;
        protected SingleVariableWidget outcomePanel;
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
        protected JPanel manualDataPanel;
        private JTextField manualDataText;
        private JTextField manualPackageText;
        protected JPanel manualPackagePanel;
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
        protected ButtonGroup cointTypeButtons;
        protected ButtonGroupWidget cointEGComponent;
        protected ButtonGroupWidget cointPOType;
        protected ButtonGroupWidget cointPOComponent;
        protected ButtonGroupWidget cointPOLag;
        
        
        protected JLabel constantTrendLabel;
        protected JRadioButton includeNone;
        protected JRadioButton includeConstant;
        protected JRadioButton includeTrend;
        protected JRadioButton includeBoth;
        protected ButtonGroup constantTrendButtons;
        

        protected JLabel lrTransitoryLabel;
        protected JRadioButton longRun;
        protected JRadioButton transitory;
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
        
        
        protected JLabel cointOptionsLabel;
        protected JComboBox cointOption;

	private static HanselFloatingMenu  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;

        public JMenuBar floatingMenuBar;
        
        public static void runit(String EstMethod,String[] packageData ) {
            
                EstimationMethodDialog = EstMethod;

			theDialog = new HanselFloatingMenu();
                        theDialog.setVisible(true);

                            theDialog.dataList.setListData(packageData);
          
                   
                   theDialog.setTitle(EstimationMethodDialog);

                
		WindowTracker.addWindow(theDialog);

	}
          
      public static void runit() {
            
			theDialog = new HanselFloatingMenu();
                        theDialog.setVisible(true);
                
		WindowTracker.addWindow(theDialog);

	} 
        
        


        	public HanselFloatingMenu() {
		super();
		initGUI();
                resetModel();
                Deducer.execute("options(device = \"JavaGD\")");
	}      

	private void initGUI() {
		try {
			
                    
                        AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			
                        JMenu submenu, deducerBasics,menuData, menuAnalysis, menuPlots, menuHanselAnalysis;
                        
                        JMenuItem flMenuItem;
                            
                       
                            
floatingMenuBar = new JMenuBar();

deducerBasics = new JMenu("Deducer");
floatingMenuBar.add(deducerBasics);
flMenuItem = new JMenuItem("Open Data");
flMenuItem.addActionListener(cListener);
deducerBasics.add(flMenuItem);
flMenuItem = new JMenuItem("Save Data");
flMenuItem.addActionListener(cListener);
deducerBasics.add(flMenuItem);
flMenuItem = new JMenuItem("Data Viewer");
flMenuItem.addActionListener(cListener);
deducerBasics.add(flMenuItem);
flMenuItem = new JMenuItem("Deducer Online Help");
flMenuItem.addActionListener(cListener);
deducerBasics.add(flMenuItem);

menuData = new JMenu("Data");
floatingMenuBar.add(menuData);
flMenuItem = new JMenuItem("Edit Factor");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
flMenuItem = new JMenuItem("Recode Variables");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
flMenuItem = new JMenuItem("Transform");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
menuData.addSeparator();
flMenuItem = new JMenuItem("Reset Row Names");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
flMenuItem = new JMenuItem("Sort");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
flMenuItem = new JMenuItem("Transpose");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
menuData.addSeparator();
flMenuItem = new JMenuItem("Merge Data");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);
flMenuItem = new JMenuItem("Subset");
flMenuItem.addActionListener(cListener);
menuData.add(flMenuItem);


menuAnalysis = new JMenu("Analysis");
floatingMenuBar.add(menuAnalysis);
flMenuItem = new JMenuItem("Frequencies");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("Contingency Tables");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
menuAnalysis.addSeparator();
flMenuItem = new JMenuItem("One Sample Test");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("Two Sample Test");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("K-Sample Test");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
menuAnalysis.addSeparator();
flMenuItem = new JMenuItem("Paired Test");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("Correlation");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
menuAnalysis.addSeparator();
flMenuItem = new JMenuItem("Linear Model");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("Logistic Model");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);
flMenuItem = new JMenuItem("Generalized Linear Model");
flMenuItem.addActionListener(cListener);
menuAnalysis.add(flMenuItem);


menuPlots = new JMenu("Plots");
floatingMenuBar.add(menuPlots);
flMenuItem = new JMenuItem("Plot Builder");
flMenuItem.addActionListener(cListener);
menuPlots.add(flMenuItem);
flMenuItem = new JMenuItem("Import Template");
flMenuItem.addActionListener(cListener);
menuPlots.add(flMenuItem);
flMenuItem = new JMenuItem("Open Plot");
flMenuItem.addActionListener(cListener);
menuPlots.add(flMenuItem);

submenu = new JMenu("Quick");
flMenuItem = new JMenuItem("bar");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("grouped bar");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("histogram");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("density");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("grouped density");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("simple dotplot");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("grouped dotplot");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("mean");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("line");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("grouped line");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("simple boxplot");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("grouped boxplot");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("scatter");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("scatter smooth");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("histogram 2d");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("bubble");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuPlots.add(submenu);


menuHanselAnalysis = new JMenu("Hansel Analysis");
floatingMenuBar.add(menuHanselAnalysis);

flMenuItem = new JMenuItem("Ordinary Least Squares");
flMenuItem.addActionListener(cListener);
menuHanselAnalysis.add(flMenuItem);

flMenuItem = new JMenuItem("Two-Stage Least Squares");
flMenuItem.addActionListener(cListener);
menuHanselAnalysis.add(flMenuItem);

flMenuItem = new JMenuItem("Weighted Least Squares");
flMenuItem.addActionListener(cListener);
menuHanselAnalysis.add(flMenuItem);

submenu = new JMenu("Non-linear Models");

flMenuItem = new JMenuItem("Binary Logit");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Binary Probit");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Tobit");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);


submenu = new JMenu("Cross-Sectional Spatial Data");

flMenuItem = new JMenuItem("Explore Cross-Sectional Spatial Data");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial simultaneous autoregressive error model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Error Durbin model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial simultaneous autoregressive error model - GMM");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial simultaneous autoregressive lag model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial Durbin model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("generalized spatial two-stage least squares");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);


flMenuItem = new JMenuItem("Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("SAC/SARAR Durbin model - maximum likelihood");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial simultaneous autoregressive SAC model - GMM");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Spatial conditional and simultaneous autoregression");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Matrix exponential spatial lag model");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

menuHanselAnalysis.add(submenu);


submenu = new JMenu("Time Series");

flMenuItem = new JMenuItem("Explore Time Series");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
flMenuItem = new JMenuItem("Chart Financial Time Series");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Vector Autoregression");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Cointegration Test - Johansen");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Cointegration Test - Philips & Ouliaris");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Cointegration Test - Engle & Granger");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);


submenu = new JMenu("Panel");

flMenuItem = new JMenuItem("Explore Panel Series");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Panel Linear Model");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Variable Coefficients");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("General Feasible GLS");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("GMM (e.g. for dynamic equations)");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Common Correlated Effects Pooled Estimator");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Mean Groups");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

menuHanselAnalysis.add(submenu);

menuHanselAnalysis.addSeparator();

flMenuItem = new JMenuItem("Explore previously estimated model");
flMenuItem.addActionListener(cListener);
menuHanselAnalysis.add(flMenuItem);

menuHanselAnalysis.addSeparator();



submenu = new JMenu("Data Retrieval");

flMenuItem = new JMenuItem("Open data");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Clipboard -> data frame");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Download data from OANDA");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Download data from St. Louis FRED");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Download data from Yahoo!");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Load sample dataset");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);

submenu = new JMenu("Data Tools");

flMenuItem = new JMenuItem("Data frame -> time series (T.S.) mirror");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);


flMenuItem = new JMenuItem("Zoo, ts object -> data frame mirror");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);

flMenuItem = new JMenuItem("Spatial object -> data frame mirror");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);

flMenuItem = new JMenuItem("Spatial File Associations");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);

menuHanselAnalysis.addSeparator();

submenu = new JMenu("About DeducerHansel");

flMenuItem = new JMenuItem("General information");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("Warranty");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);

flMenuItem = new JMenuItem("License");
flMenuItem.addActionListener(cListener);
submenu.add(flMenuItem);
menuHanselAnalysis.add(submenu);

/**************** end Hansel menus ********/



this.setJMenuBar(floatingMenuBar);    

			this.setSize(300, 60);
                        this.setTitle(EstimationMethodDialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
 
        class ConsoleListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
			Hansel.runCmdThreaded(cmd);
		}
	}


	private void reset(){
                variableSelector.refreshDataNames();  
                interceptIncluded.setSelected(true);
                panelIndexText.setText("");
                manualDataText.setText("");
                manualPackageText.setText("");   
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

	}
	
 
	public void setModel(GMModel mod){
            		model = mod;                
	}
	
	public void updateModel(){ 

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
	        }  //else if (cmd=="Both text and icon")
                    //EFEGLMDialog.runit("OLS");
    }

}