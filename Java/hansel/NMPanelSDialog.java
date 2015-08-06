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
  GLMDialog.java, ModelBuilder.java and GLMExplorerPostHoc.java, found in the Deducer package
 and
  ExampleDialog.java, found in the DeducerPlugInExample package.
 
The current file made adjustments to that earlier java code on 2013-07-05 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
  */

package hansel;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import org.rosuda.deducer.toolkit.SingletonDJList;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.menu.SubsetDialog;
import org.rosuda.deducer.menu.SubsetPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.widgets.*;


public class NMPanelSDialog  extends JDialog implements ActionListener {
     
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	private VariableSelectorWidget variableSelector;
        private JCheckBox interceptIncluded ;
        protected SingleVariableWidget outcomePanel;
	private KeepSingleVariableWidget weightPanel;
	private HelpButton helpButton;
	private KeepVariableListWidget contPanel;
        protected DJList numericList;
       	protected KeepVariableListWidget factPanel;
        protected SingletonDJList outcome;
        protected SingletonDJList weights;
      	private ManualModelInput manModInput;
        
        private static String lastDataName;
        protected ListModel lastOutcomeModel;
        private static DefaultListModel lastNumericModel;
        public DefaultListModel termsMF; 
        
        protected JButton resetButton;
        protected JButton runButton;
        protected JButton cancelButton;
        
        protected JButton addExplanatory;
        protected JButton removeExplanatory;
        protected JButton addInstrument;
        protected JButton removeInstrument;
        
        protected JButton modelBuilder;
        protected JButton manualModelButton;
       	protected JPanel subsetPanel;
        protected SubsetPanel subset;
        protected JPanel startPanel;
        private JScrollPane  startScroller;
        private JTextArea startText;
        protected JPanel endPanel;
        private JScrollPane  endScroller;
        private JTextArea endText;  
        protected JLabel newVariablesLabel;
        protected JComboBox newVariables;
        protected JLabel typeLabel;
        protected JComboBox type;
        protected JComboBox vcovtype;
        protected static DefaultComboBoxModel transforms  = new DefaultComboBoxModel(
				new String[] { "Highlight then choose","log", "square","cube","square root",
						"inverse","inverse of square","negative","lag","multiply 2 variables","2-way",
						"3-way","interaction","nested","as factor","as numeric","split","manual entry here"});
        
        protected static DefaultComboBoxModel vcovs  = new DefaultComboBoxModel(
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
        

	
	private Vector widgets;			//Collection of all the DeducerWidgets

	private static NMPanelSDialog  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;

	public static void runit() {
		if(theDialog == null){
			theDialog = new NMPanelSDialog();
                        theDialog.setVisible(true);
		}

                else{
                   theDialog.considerManMod();
                }
                theDialog.setVisible(true);
		WindowTracker.addWindow(theDialog);

	}
        public static void runit(String EstMethod) {
                EstimationMethodDialog = EstMethod;     
		if(theDialog == null){
                        
			theDialog = new NMPanelSDialog();
                        theDialog.setVisible(true);
		}
                else{
                   theDialog.considerManMod();
		   theDialog.setVisible(true);

                   if ((EstimationMethodDialog.equals("Two-Stage Least Squares"))){
                       theDialog.factPanel.setVisible(true);
                   }
                   else{
                       theDialog.factPanel.setVisible(false);
                   }
                   if ((EstimationMethodDialog.equals("Ordinary Least Squares"))){
                        theDialog.factPanel.reset();                      
                   }
                   theDialog.setTitle(EstimationMethodDialog);

                }
		WindowTracker.addWindow(theDialog);

	}
        
        
	public static void runitagain(GMModel mod) {
		if(theDialog == null){
			theDialog = new NMPanelSDialog(mod);
		}

		theDialog.setToLast();
		theDialog.setVisible(true);
		
		WindowTracker.addWindow(theDialog);
	}

	public NMPanelSDialog(JFrame frame) {
		super(frame);
		widgets = new Vector();
		initGUI();
                resetModel();
                manModInput = new ManualModelInput();
	}        
  
	public NMPanelSDialog(GMModel mod) {
		super();
		mod.copyInto(modelOnOpen);
                widgets = new Vector();
		initGUI();
                resetModel();     
                this.setToLast();
 
            	ManualModelDialog manMod = new ManualModelDialog(this,manModInput);
			manMod.setLocationRelativeTo(this);
			manMod.setVisible(true);

	}
        	public NMPanelSDialog() {
		super();
		widgets = new Vector();
		initGUI();
                manModInput = new ManualModelInput();
                resetModel();
	}      
        

	private void initGUI() {
		try {
                        Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
                        Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
                        Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);

			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			
			variableSelector = new VariableSelectorWidget();
                        getContentPane().add(variableSelector, new AnchorConstraint(20, 431, 669, 22, 
				AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
				AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			variableSelector.setPreferredSize(new java.awt.Dimension(222, 529));
			variableSelector.setTitle("unused title");
                        
                                {
					resetButton = new JButton();
					getContentPane().add(resetButton, new AnchorConstraint(20, 550, 55, 440,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					resetButton.setText("Reset");
					resetButton.setFont(font12);
                                        resetButton.setPreferredSize(new java.awt.Dimension(100, 46));
					resetButton.addActionListener(this);
				}
                         
			{
              			{
					outcomePanel = new SingleVariableWidget("Explained variable",variableSelector);
				} 
				{
					contPanel = new KeepVariableListWidget("Variables for analysis",variableSelector);
                                	getContentPane().add(contPanel, new AnchorConstraint(115, 978, 355, 467, 
                                                     AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                      AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                        contPanel.setPreferredSize(new java.awt.Dimension(323, 122));
                                        contPanel.setFont(font12);
				} 

				{
					factPanel = new KeepVariableListWidget("Instrumental variables",variableSelector);


				} 
                                                          
                                
                                {
				weightPanel = new KeepSingleVariableWidget("Weights",variableSelector);
				getContentPane().add(weightPanel, new AnchorConstraint(584, 978, 670, 467, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				weightPanel.setPreferredSize(new java.awt.Dimension(223, 52));
                                weightPanel.setFont(font12);
                                weightPanel.setVisible(false);
                                }

                                                                {
				subsetPanel = new JPanel();
				BorderLayout subsetPanelLayout = new BorderLayout();
				subsetPanel.setLayout(subsetPanelLayout);
				getContentPane().add(subsetPanel, new AnchorConstraint(631, 978, 770, 580, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				subsetPanel.setBorder(BorderFactory.createTitledBorder("Subset"));
                                subsetPanel.setFont(font12);
				subsetPanel.setPreferredSize(new java.awt.Dimension(110, 79));
                                }
				{
					subset = new SubsetPanel(variableSelector.getJComboBox());
					subsetPanel.add(subset, BorderLayout.CENTER);
					subset.setPreferredSize(new java.awt.Dimension(213, 53));
                                        subset.setFont(new java.awt.Font("Monospaced",0,8));
				}
                                {
                                    startPanel  = new JPanel();
                                    getContentPane().add(startPanel, new AnchorConstraint(771, 778, 860, 580, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    startPanel.setLayout(modelFormulaLayout);
                                    startPanel.setBorder(BorderFactory.createTitledBorder("Data start"));
                                    startPanel.setFont(font12);
                                    startPanel.setPreferredSize(new java.awt.Dimension(110, 52));
                                 }                                          
                                    {
                                        startScroller = new JScrollPane();
                                        startPanel.add(startScroller, BorderLayout.CENTER);
                                            {
                                                    startText = new JTextArea();
                                                    startScroller.setViewportView(startText);
                                                    startText.setFont(new java.awt.Font("Monospaced",0,11));
                                                    startText.setEditable(true);

                                            }
                                    }
                                        
                                        
                                        
                                 {
                                    endPanel  = new JPanel();
                                    getContentPane().add(endPanel, new AnchorConstraint(771, 978, 860, 780, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    endPanel.setLayout(modelFormulaLayout);
                                    endPanel.setBorder(BorderFactory.createTitledBorder("Data end"));
                                    endPanel.setFont(font12);
                                    endPanel.setPreferredSize(new java.awt.Dimension(110, 52));
                                 }                                          
                                    {
                                        endScroller = new JScrollPane();
                                        endPanel.add(endScroller, BorderLayout.CENTER);
                                            {
                                                    endText = new JTextArea();
                                                    endScroller.setViewportView(endText);
                                                    endText.setFont(new java.awt.Font("Monospaced",0,11));
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
/*					newVariablesLabel.setHorizontalAlignment(SwingConstants.RIGHT);*/
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
					typeLabel = new JLabel();

				}
                                {
					vcovtype = new JComboBox();

				}

                                {
					interceptIncluded = new JCheckBox();

				}
                                {
					manualModelButton = new JButton();

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
                                getContentPane().add(runButton, new AnchorConstraint(926, 780, 980, 600, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                                runButton.setText("Run");
                                runButton.setFont(font12);
                                runButton.setFont(font12);
                                runButton.setPreferredSize(new java.awt.Dimension(110, 40));
                                runButton.addActionListener(this);
                         }
                         {
                                cancelButton = new JButton();
                                getContentPane().add(cancelButton, new AnchorConstraint(926, 978, 980, 800, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                                cancelButton.setText("Cancel");
                                cancelButton.setFont(font12);
                                cancelButton.setFont(font12);
                                cancelButton.setPreferredSize(new java.awt.Dimension(110, 40));
                                cancelButton.addActionListener(this);
                         }
                        
                        
 		/* Adds Deducer widgets to collection*/
			widgets.add(variableSelector);
                        widgets.add(outcomePanel);
			widgets.add(contPanel);
                        widgets.add(factPanel);
                        widgets.add(weightPanel);
                        outcome = new SingletonDJList();
                        outcome.setModel(new DefaultListModel());
 
                        
                        numericList = new DJList();
			numericList.setModel(new DefaultListModel());
                        
                        weights = new SingletonDJList();
                        weights = weightPanel.getList();

			this.setSize(600, 634);
                        this.setTitle(EstimationMethodDialog);
                        this.setFont(font12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
        

	private void reset(){
                variableSelector.refreshDataNames();
	}

        
	public void saveToLast(){
		lastDataName = variableSelector.getSelectedData();
                lastOutcomeModel = (DefaultListModel)outcome.getModel();
		lastNumericModel = (DefaultListModel) numericList.getModel();
	}
        
        public void reset(boolean resetOptions){
                outcome.setModel(new DefaultListModel());
		numericList.setModel(new DefaultListModel());
                startText.setText("");
                endText.setText("");  
	}
        

        	protected void setToLast(){
                    saveToLast();
                    variableSelector.refreshDataNames();
                    variableSelector.setSelectedData(lastDataName);
                    outcomePanel.setModel(lastOutcomeModel);
                    contPanel.setModel(lastNumericModel);
	}

        
	public static void setLastModel(GMModel lm){
		lastModel = lm;
	}
	
        public void considerManMod(){
                   if (manModInput.input.length()>0){
                         ManualModelDialog manMod = new ManualModelDialog(this,manModInput);
                         manMod.dispose();
                         manMod = new ManualModelDialog(this,manModInput);
                         manMod.setLocationRelativeTo(this);
                         manMod.setVisible(true); 
                   }
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
		
		valid = variableSelector.removeAll(mod.factorVars);
		if(!valid){
			resetModel();
			return;
		}
                factPanel.setModel(mod.factorVars);
		
		valid = variableSelector.removeAll(mod.weights);
		if(!valid){
			resetModel();
			return;
		}
		weightPanel.setModel(mod.weights);
		if(SubsetDialog.isValidSubsetExp(mod.subset, mod.data))
			subset.setText(mod.subset);
		if(vcovs.getIndexOf(mod.vcov)>=0)
			vcovs.setSelectedItem(mod.vcov);
		else
			vcovs.insertElementAt(mod.vcov, vcovs.getSize()-1); 
                
                interceptIncluded.setSelected(model.efeglmextra.interceptIncluded);
                
                model.EstimationMethod = EstimationMethodDialog;
                if (model.EstimationMethod.equals("Ordinary Least Squares")){
                        model.method="lm";
                        model.family="";
                        model.instruments="";
                        factPanel.setVisible(false);
                        model.factorVars.removeAllElements();
                } else if (model.EstimationMethod.equals("Logit")){
                        model.method="glm";
                        model.family =",family=binomial(link=\"logit\")";
                        factPanel.setVisible(false);
                } else if (model.EstimationMethod.equals("Probit")){
                        model.method="glm";
                        model.family =",family=binomial(link=\"probit\")";
                        factPanel.setVisible(false);
                } else if (model.EstimationMethod.equals("Two-Stage Least Squares")){
                        model.method="tsls";
                        model.family ="";
                        factPanel.setVisible(true);
                }
                
                
		model = mod;
                
	}
	
	public void updateModel(){
                    model.factorVars=(DefaultListModel)factPanel.getModel();
                if (manModInput.input.length()==0) { 
                    
                    model.numericVars=(DefaultListModel)contPanel.getModel();
                    model.data = variableSelector.getSelectedData();
                } 
                else {
                    Integer lastcut = 0;
                    Integer termsnum = 0; 
                    Boolean tildafound = false;
                    String outcomesMF = "";
                    termsMF = new DefaultListModel();
                    for (int i=0;i<manModInput.input.length();i++){
                        if (!tildafound){
                            if (manModInput.input.substring(i,i+1).equals("~"))
                            {
                               tildafound = true;
                               outcomesMF = manModInput.input.substring(0,i);
                               lastcut = i;
                            }
                        }
                        else {

                            if (manModInput.input.substring(i,i+1).equals("+")) {
                                termsnum = termsnum + 1;
                                termsMF.addElement(manModInput.input.substring(lastcut+1,i));
                             
                                lastcut = i;
                            }
                            else if ((i+1)==manModInput.input.length()) {
                                termsMF.addElement(manModInput.input.substring(lastcut+1,i+1));
                            }
                        }
                    }
                    
                    model.numericVars = termsMF;                   
                }    
                    
		model.data = variableSelector.getSelectedData();
		model.subset = subset.getText();
                model.start = startText.getText();
                model.end = endText.getText();
		model.weights = (DefaultListModel) weightPanel.getModel();
                model.EstimationMethod = EstimationMethodDialog;
          	model.vcov =(String)vcovs.getSelectedItem();
                model.terms = model.numericVars;
                model.efeglmextra.interceptIncluded = interceptIncluded.isSelected();
     
                
		boolean allIn=true;
                
 		for(int i=0;i<modelOnOpen.numericVars.size();i++){
			if(!model.numericVars.contains(modelOnOpen.numericVars.get(i)))
				allIn= false;
		}               
		for(int i=0;i<modelOnOpen.factorVars.size();i++){
			if(!model.factorVars.contains(modelOnOpen.factorVars.get(i)))
				allIn= false;
		}
		if(!allIn)
			model.terms=new DefaultListModel();
	}
	public void setDataName(String dataName){
		if(!dataName.equals(variableSelector.getSelectedData())){
			variableSelector.setSelectedData(dataName);
		}
	}
	public boolean valid(){
             if (manModInput.input.length()==0){
		if(model.numericVars.size() <1){
			JOptionPane.showMessageDialog(this, "Please specify a predictor variable (numeric or factor).");
			return false;
		}
             } 
		return true;
	}
        
	private void completed(){
		for(int i=0;i<widgets.size();i++){
			DeducerWidget wid = (DeducerWidget)widgets.get(i);
			wid.setLastModel(wid.getModel());
		}
	}

        public void continueClicked(){
            	updateModel();
		if(!valid())
			return;
                this.dispose();

                NMPanelSExplorer exp = new NMPanelSExplorer(model,false);
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
				this.setTitle("Manual Model Entry");
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
			}
			
		}
	}

        
        
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

               if (cmd=="Run") {
                        continueClicked();
                }else if(cmd == "Manual Model Entry"){
			ManualModelDialog manMod = new ManualModelDialog(this,manModInput);
			manMod.setLocationRelativeTo(this);
			manMod.setVisible(true);
		}else if(cmd=="Cancel"){
			this.setVisible(false);
                }else if(cmd=="Reset"){
			reset();
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
                }else if(((String)newVariables.getSelectedItem()=="lag")){
			Object[] sel = variableSelector.getSelectedVariables().toArray();
			if(sel==null || sel.length<1){
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			}
                        String term;
                        for(int i =0;i<sel.length;i++){
                            term = "Lag("+(String) sel[i]+ ",1)";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
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
                            term = "I("+(String) sel[i]+ ">mean("+(String) sel[i]+"))";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
			}
                      newVariables.setSelectedIndex(0);
                 }else if(((String)newVariables.getSelectedItem()=="manual entry here")){
                        String term = JOptionPane.showInputDialog(null, "Enter new variable's formula now"); 
                            term = "I("+(String) term+ ")";
                            if(!variableSelector.toString().contains(term))
				variableSelector.add(term);
                      newVariables.setSelectedIndex(0);     
                }
	}

}