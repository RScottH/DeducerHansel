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
 
 The code from those packages used in this file most notably come from
  GLMExplorerPlots.java and GLMExplorerPostHoc.java, found in the Deducer package
 and
  ExampleDialog.java (dated 2010-03-12), found in the DeducerPlugInExample package.
 
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
import javax.swing.JTextField;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class DToolsMirrorsfromDframe  extends JDialog implements ActionListener  {
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
        private JTextField startText;
        protected JPanel endPanel;
        private JTextField endText;       
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

	private static DToolsMirrorsfromDframe  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;
        
        protected ButtonGroupWidget mirrorType;
        
        
        protected JLabel yearLabel;
        protected JComboBox year;
        protected static DefaultComboBoxModel yearChoices  = new DefaultComboBoxModel(
				new String[] {"1950","1951","1952","1953","1954","1955","1956","1957","1958","1959",
                                              "1960","1961","1962","1963","1964","1965","1966","1967","1968","1969",
                                              "1970","1971","1972","1973","1974","1975","1976","1977","1978","1979",
                                              "1980","1981","1982","1983","1984","1985","1986","1987","1988","1989",
                                              "1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
                                              "2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
                                              "2010","2011","2012","2013","2014","2015","2016","2017","2018","2019",
                                              "2020","2021","2022","2023","2024","2025","2026","2027","2028","2029", 
                                              "2030"});
        protected JLabel quarterLabel;
        protected JComboBox quarter;
        protected static DefaultComboBoxModel quarterChoices  = new DefaultComboBoxModel(
				new String[] {"not quarterly","1","2","3","4"});
        
        protected JLabel monthLabel;
        protected JComboBox month;
        protected static DefaultComboBoxModel monthChoices  = new DefaultComboBoxModel(
				new String[] {"not monthly","1","2","3","4","5","6",
                                              "7","8","9","10","11","12"});
        protected JLabel weekLabel;
        protected JComboBox week;
        protected static DefaultComboBoxModel weekChoices  = new DefaultComboBoxModel(
				new String[] {"not weekly",
                                              "1","2","3","4","5","6","7","8","9",
                                              "10","11","12","13","14","15","16","17","18","19",
                                              "20","21","22","23","24","25","26","27","28","29",
                                              "30","31","32","33","34","35","36","37","38","39",
                                              "40","41","42","43","44","45","46","47","48","49",
                                              "50","51","52"});
        protected JLabel alternativeLabel;
        public Boolean noReactionToAction = true;
        
        public static void runit(String EstMethod, String[] dataFrames ) {
            
                EstimationMethodDialog = EstMethod;

			theDialog = new DToolsMirrorsfromDframe();
                        theDialog.setVisible(true);

                            theDialog.dataList.setListData(dataFrames);
                
		WindowTracker.addWindow(theDialog);

	}


        	public DToolsMirrorsfromDframe() {
		super();
		initGUI();
                resetModel();
                Deducer.execute("suppressMessages(library(zoo))");
	}      

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                               {
					dataPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					dataPanel.setLayout(varPanelLayout);
					getContentPane().add(dataPanel, new AnchorConstraint(10, 375, 725, 300, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					dataPanel.setBorder(BorderFactory.createTitledBorder("Highlight one data frame"));
					dataPanel.setFont(font12);
                                        dataPanel.setPreferredSize(new java.awt.Dimension(215, 130));
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
					yearLabel = new JLabel();
					getContentPane().add(yearLabel, new AnchorConstraint(400, 375, 725, 110,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					yearLabel.setText("starting year");
                                        yearLabel.setFont(font12);
					yearLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					year = new JComboBox();
					getContentPane().add(year , new AnchorConstraint(450, 375, 725, 110,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					year.setModel(yearChoices);
                                        year.setSelectedItem("2000");
                                        year.setFont(font12);
					year .setPreferredSize(new java.awt.Dimension(90, 21));
					year .addActionListener(this);
				}  
                               
                               {
					quarterLabel = new JLabel();
					getContentPane().add(quarterLabel, new AnchorConstraint(400, 375, 725, 310,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					quarterLabel.setText("starting quarter");
                                        quarterLabel.setFont(font12);
					quarterLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					quarter = new JComboBox();
					getContentPane().add(quarter , new AnchorConstraint(450, 375, 725, 310,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					quarter.setModel(quarterChoices);
                                        quarter.setSelectedItem("not quarterly");
                                        quarter.setFont(font12);
					quarter .setPreferredSize(new java.awt.Dimension(90, 21));
					quarter .addActionListener(this);
				} 
                                {
					monthLabel = new JLabel();
					getContentPane().add(monthLabel, new AnchorConstraint(400, 375, 725, 510,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					monthLabel.setText("starting month");
                                        monthLabel.setFont(font12);
					monthLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					month = new JComboBox();
					getContentPane().add(month , new AnchorConstraint(450, 375, 725, 510,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					month.setModel(monthChoices);
                                        month.setSelectedItem("not monthly");
                                        month.setFont(font12);
					month .setPreferredSize(new java.awt.Dimension(90, 21));
					month .addActionListener(this);
				}  
                                {
					weekLabel = new JLabel();
					getContentPane().add(weekLabel, new AnchorConstraint(400, 375, 725, 710,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					weekLabel.setText("starting week");
                                        weekLabel.setFont(font12);
					weekLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					week = new JComboBox();
					getContentPane().add(week , new AnchorConstraint(450, 375, 725, 710,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					week.setModel(weekChoices);
                                        week.setSelectedItem("not weekly");
                                        week.setFont(font12);
					week .setPreferredSize(new java.awt.Dimension(90, 21));
					week.addActionListener(this);
				}  
                                {
					alternativeLabel = new JLabel();
					getContentPane().add(alternativeLabel, new AnchorConstraint(530, 375, 725, 110,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					alternativeLabel.setText("Alternatively, you can type below the start date & frequency below (overrides start date above).");
                                        alternativeLabel.setFont(font12);
					alternativeLabel.setPreferredSize(new java.awt.Dimension(900, 20));
				} 
                                
                                  {
                                    startPanel  = new JPanel();
                                    getContentPane().add(startPanel, new AnchorConstraint(580, 375, 725, 110, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    startPanel.setLayout(modelFormulaLayout);
                                    startPanel.setFont(font12);
                                    startPanel.setBorder(BorderFactory.createTitledBorder("starting date, e.g. 1974:2"));
                                    startPanel.setPreferredSize(new java.awt.Dimension(200, 42));
                                 }                                          
                                    {
                                        startText = new JTextField();
                                        startPanel.add(startText,BorderLayout.CENTER);
                                        startText.setEditable(true);
                                        startText.setFocusable(true);
                                        
                                    }
                                        
                                        
                                        
                                 {
                                    endPanel  = new JPanel();
                                    getContentPane().add(endPanel, new AnchorConstraint(580, 375, 725, 550, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    endPanel.setLayout(modelFormulaLayout);
                                    endPanel.setBorder(BorderFactory.createTitledBorder("frequency, e.g. 4 for quarterly"));
                                    endPanel.setFont(font12);
                                    endPanel.setPreferredSize(new java.awt.Dimension(200, 42));
                                 }  
                                 
                                    endText = new JTextField();
                                    endPanel.add(endText, BorderLayout.CENTER);
                                    endText.setEditable(true);
                                    endText.setFocusable(true);                                    
                                
                              {
                                        mirrorType = new ButtonGroupWidget("partial or full mirror",
						new String[]{"partial mirror (replaces original data frame with names-only version)",
                                                             "full mirror"});
                                        mirrorType.setSelected("full mirror");
                                        mirrorType.setFont(font12);
                                        getContentPane().add(mirrorType, new AnchorConstraint(700, 375, 725, 100, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        mirrorType.setPreferredSize(new java.awt.Dimension(500, 65));
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
                        

			
			this.setSize(700, 400);
                        this.setFont(font12);
                        this.setTitle("Data mirroring for an existing data frame (creates T.S.-prefixed ts object)");
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
            String chosenDataFrame = dataList.getSelectedValue().toString();
            String chosenStartDate = "";
            String chosenFrequency = "";
            if (startText.getText().equals("")){
                chosenStartDate = "c("+year.getSelectedItem().toString() + 
                      (quarter.getSelectedItem().toString().equals("not quarterly")?"":","+quarter.getSelectedItem().toString())
                    + (month.getSelectedItem().toString().equals("not monthly")?"":","+month.getSelectedItem().toString())        
                    + (week.getSelectedItem().toString().equals("not weekly")?"":","+week.getSelectedItem().toString())+ ")";
                if (!quarter.getSelectedItem().toString().equals("not quarterly"))
                   chosenFrequency = "4";
                else if (!month.getSelectedItem().toString().equals("not monthly"))
                    chosenFrequency = "12";
                else if (!week.getSelectedItem().toString().equals("not weekly"))
                    chosenFrequency = "52";
                else
                    chosenFrequency = "1";
                        
            } else {
                chosenStartDate = startText.getText();
                if (chosenStartDate.contains("-")||chosenStartDate.contains(":")||chosenStartDate.contains(",")) 
                     chosenStartDate = "c("+startText.getText().replace("-",",").replace(":",",") + ")";
                chosenFrequency = endText.getText();
            }
            
            String cmd ="";
            if (!chosenStartDate.isEmpty())
                if (!chosenFrequency.isEmpty())
                    cmd = "T.S."+chosenDataFrame+"<- ts("+chosenDataFrame+",start="+chosenStartDate+",frequency="+chosenFrequency+")";
                else
                    cmd = "T.S."+chosenDataFrame+"<- ts("+chosenDataFrame+",start="+chosenStartDate+",frequency=1)";
            else
                cmd = "T.S."+chosenDataFrame+"<- ts("+chosenDataFrame+",start=1,frequency=1)";  
            
            if (mirrorType.getSelectedItemText()=="partial mirror (replace data frame with names only)"){
                cmd+= 
                 "\n dimnamesToUse <-colnames(T.S."+chosenDataFrame+")"+
                 "\n"+chosenDataFrame+"<- data.frame(rbind(dimnamesToUse))"+
                 "\n dimnames("+chosenDataFrame+")[[2]] <-   dimnamesToUse";
            }
            
            Deducer.execute(cmd);
 
	}
	public void setDataName(String dataName){
		if(!dataName.equals(variableSelector.getSelectedData())){
			variableSelector.setSelectedData(dataName);
		}
	}
	public boolean valid(){
           if (!(dataList.getSelectedValuesList().size()==1)) {
			JOptionPane.showMessageDialog(this, "Please choose one data frame");
			return false;
		}
		return true;
	}

	private void completed(){

	}

        public void continueClicked(){
            	
		if(!valid())
			return;
                updateModel();
	
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
                if ((!month.getSelectedItem().toString().equals("not monthly"))
                    ||(!week.getSelectedItem().toString().equals("not weekly")))
                  quarter.setEnabled(false);
                else
                  quarter.setEnabled(true);
                
                if ((!quarter.getSelectedItem().toString().equals("not quarterly"))
                    ||(!week.getSelectedItem().toString().equals("not weekly")))
                  month.setEnabled(false);
                else
                  month.setEnabled(true);
                
                if ((!quarter.getSelectedItem().toString().equals("not quarterly"))
                    ||(!month.getSelectedItem().toString().equals("not monthly")))
                  week.setEnabled(false);
                else
                  week.setEnabled(true);
                
                    
    }

}