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
  GLMDialog.java (dated 2012-12-05), found in the Deducer package
 and
  ExampleDialog.java (dated 2010-03-12), found in the DeducerPlugInExample package.
 
 The current file made adjustments to the Deducer and DeducerPlugInExample java code on 2015-03-13
 to work with the DeducerHansel package, with subsequent revision on  2015-07-29.
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
import org.rosuda.REngine.RList;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.widgets.*;


public class NMFinancialSDialog  extends JDialog implements ActionListener {
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
	private SliderWidget slider;
	private ComboBoxWidget comboBox;
        private JCheckBox interceptIncluded ;
        protected SingleVariableWidget outcomePanel;
	private HelpButton helpButton;
        private VariableListWidget variableList;
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
        private JScrollPane  panelIndexScroller;
        private JTextArea panelIndexText;
        protected JPanel manualDataPanel;
        private JScrollPane  startScroller;
        private JTextField manualDataText;
        private JTextField manualPackageText;
        protected JPanel manualPackagePanel;
        private JScrollPane  endScroller;     
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
	private static NMFinancialSDialog  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;

        
        public static void runit() {
		if(theDialog == null){
			theDialog = new NMFinancialSDialog();
                        theDialog.setVisible(true);
		}

                else{
                   //theDialog.considerManMod();
                }
                theDialog.setVisible(true);
		WindowTracker.addWindow(theDialog);
        }
        
        public static void runit(String EstMethod,String[] packageData ) {
            
                EstimationMethodDialog = EstMethod;

			theDialog = new NMFinancialSDialog();
                        theDialog.setVisible(true);

                            theDialog.dataList.setListData(packageData);
          
                   
                   theDialog.setTitle(EstimationMethodDialog);

                
		WindowTracker.addWindow(theDialog);

	}
          

        	public NMFinancialSDialog() {
		super();
		initGUI();
                resetModel();
	}      

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                        {
					dataPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					dataPanel.setLayout(varPanelLayout);
					getContentPane().add(dataPanel, new AnchorConstraint(101, 375, 725, 100, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					dataPanel.setBorder(BorderFactory.createTitledBorder("dataset to chart"));
                                        dataPanel.setFont(font12);
					dataPanel.setPreferredSize(new java.awt.Dimension(900, 130));
					{
						dataScroller = new JScrollPane();
						dataPanel.add(dataScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							dataList = new JList();
							dataScroller.setViewportView(dataList);
							dataList.setModel(varListModel);
                                                        dataList.setFont(font12);
						}
					}
				} 

                                 {
                                    manualDataPanel  = new JPanel();
                                    getContentPane().add(manualDataPanel, new AnchorConstraint(460, 375, 725, 100, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    manualDataPanel.setLayout(modelFormulaLayout);
                                    manualDataPanel.setBorder(BorderFactory.createTitledBorder("alternative: manual entry of dataset name"));
                                    manualDataPanel.setPreferredSize(new java.awt.Dimension(200, 42));
                                    manualDataPanel.setFont(font12);
                                 }                                          
                                    {
                                        manualDataText = new JTextField();
                                        manualDataPanel.add(manualDataText,BorderLayout.CENTER);
                                        manualDataText.setFont(font12);
                                        manualDataText.setEditable(true);
                                        manualDataText.setFocusable(true);
                                        
                                    }
                                        
                                        
                                        
                                 {
                                    manualPackagePanel  = new JPanel();
                                    getContentPane().add(manualPackagePanel, new AnchorConstraint(460, 375, 725, 510, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    manualPackagePanel.setLayout(modelFormulaLayout);
                                    manualPackagePanel.setBorder(BorderFactory.createTitledBorder("if manual, enter package if needed to avoid ambiguity"));
                                    manualPackagePanel.setFont(font12);
                                    manualPackagePanel.setPreferredSize(new java.awt.Dimension(200, 42));
                                 }  
                                 
                                    manualPackageText = new JTextField();
                                    manualPackageText.setFont(font12);
                                    manualPackagePanel.add(manualPackageText, BorderLayout.CENTER);
                                    manualPackageText.setEditable(true);
                                    manualPackageText.setFocusable(true);                                    


                        

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
            
		setModel(new GMModel());
	}
	
 
	public void setModel(GMModel mod){
            		model = mod;
                        Deducer.eval("library(quantmod)");
	}
	
	public void updateModel(){
            String familylink;
             String manualDataTextEntry="";
             String manualPackageTextEntry="";
             manualDataTextEntry = manualDataText.getText();
                
             if (manualDataTextEntry.equals("")){
                     
                 if (!dataList.isSelectionEmpty()){
                      model.data= dataList.getSelectedValue().toString();  
                 } else {
                     // error message 
                 }
             } 
               NMFinancialSExplorer exp = new NMFinancialSExplorer(model,false);
		exp.setLocationRelativeTo(this);
		exp.setVisible(true);
		WindowTracker.addWindow(exp);
		this.dispose();
             

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