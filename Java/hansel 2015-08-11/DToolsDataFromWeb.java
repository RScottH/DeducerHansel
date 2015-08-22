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
  GLMDialog.java, found in the Deducer package
 and
  ExampleDialog.java,found in the DeducerPlugInExample package.
  
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-22.
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


public class DToolsDataFromWeb  extends JDialog implements ActionListener {
        public Font font14 = new Font("serif", Font.TRUETYPE_FONT, 14);
        public Font font13 = new Font("serif", Font.TRUETYPE_FONT, 13);
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        protected JPanel googlePanel;
        protected JScrollPane googleScroller;
        protected JList googleList;
    
        protected JPanel yahooPanel;
        protected JScrollPane yahooScroller;
        protected JList yahooList;
        
        protected JPanel oandaPanel;
        protected JScrollPane oandaScroller;
        protected JList oandaList;
        
        protected JPanel stLouisFREDPanel;
        protected JScrollPane stLouisFREDScroller;
        protected JList stLouisFREDList;
        
        protected JPanel googleManualPanel;
        private JTextField googleManualText;
        
        protected JPanel yahooManualPanel;
        private JTextField yahooManualText;
        
        protected JPanel oandaManualPanel;
        private JTextField oandaManualText;
        
        protected JPanel stLouisFREDManualPanel;
        private JTextField stLouisFREDManualText;
        
        protected JPanel multiNamePanel;
        protected JTextField multiNameText;
        protected String multiVariablesDataName;
        
        protected JPanel dataPanel;
        protected JScrollPane dataScroller;
        protected JList dataList;
        public DefaultListModel modelListTerms;
    
	public VariableSelectorWidget variableSelector;
	private OkayCancelPanel okayCancelPanel;
	private SliderWidget slider;
	private ComboBoxWidget comboBox;
        private JCheckBox doNotMerge ;
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
        
        private JScrollPane  startScroller;

        protected JPanel endPanel;
        private JScrollPane  endScroller;
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
        protected ButtonGroupWidget mirrorType;
        
        protected JLabel cointOptionsLabel;
        protected JComboBox cointOption;

	private static DToolsDataFromWeb  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;

        
                protected static DefaultComboBoxModel googlePanelElements  = new DefaultComboBoxModel(
				new String[] { "MMM: 3M Co.","ABT: Abbott Laboratories","ABBV: AbbVie","ANF: Abercrombie & Fitch Company A","ACE: ACE Limited","ACN: Accenture","ACT: Actavis Inc","ibm","aapl"});
                protected static DefaultComboBoxModel yahooPanelElements  = new DefaultComboBoxModel(
				new String[] { "MMM: 3M Co.","ABT: Abbott Laboratories","ABBV: AbbVie","ANF: Abercrombie & Fitch Company A","ACE: ACE Limited","ACN: Accenture","ACT: Actavis Inc","ibm","aapl"});
                protected static DefaultComboBoxModel oandaPanelElements  = new DefaultComboBoxModel(
				new String[] { "EUR/USD","GBP/USD","CAD/USD","AUD/USD","JPY/USD","CNY/USD","CHF/USD","SEK/USD"});
                protected static DefaultComboBoxModel stLouisFREDPanelElements  = new DefaultComboBoxModel(
				new String[] { "[CPIAUCSL] U.S. Consumer Price Index for All Urban Consumers: All Items",    
                                                "[UNRATE] U.S. Civilian Unemployment Rate",
                                                "[BASE] St. Louis Adjusted Monetary Base ",
                                                "[M1] U.S. M1 Money Stock",
                                                "[M2] U.S. M2 Money Stock",
                                                "[GDP] U.S. Gross Domestic Product, 1 Decimal",
                                                "[GDPC1] U.S. Real Gross Domestic Product, 1 Decimal",
                                                "[DGS10] U.S. 10-Year Treasury Constant Maturity Rate ",
                                                "[TB3MS] U.S. 3-Month Treasury Bill: Secondary Market Rate"});
        
                
                        String[] stockSymbols = {/*"[^DJA] Dow Jones Composite Average",*/
                                                "[DIA] Dow Jones Industrial Average",
                                                /*"[^DJT] Dow Jones Transportation Average",
                                                "[^DJU] Dow Jones Utility Average",*/
                                                "[^NYA] NYSE Composite Index (New Method)",
                                                "[NIN] NYSE International 100",
                                                "[NTM] NYSE TMT",
                                                "[NUS] NYSE US 100",
                                                "[NWL] NYSE World Leaders",
                                                "[NDX] NASDAQ-100",
                                                "[IXBK] NASDAQBank",
                                                "[NBI] NASDAQBiotechnology",
                                                "[IXIC] NASDAQComposite",
                                                "[IXK] NASDAQComputer",
                                                "[IXF] NASDAQFinancial100",
                                                "[IXID] NASDAQIndustrial",
                                                "[IXIS] NASDAQInsurance",
                                                "[IXFN] NASDAQOtherFinance",
                                                "[^IXUT] NASDAQTelecommunications",
                                                "[IXTR] NASDAQTransportation",
                                             /*   "[^TV.O] NASDAQ's Volumne in 000's",*/
                                                "[MID] S&P 400 MIDCAP INDEX",
                                                "[GSPC] S&P 500",
                                                "[SML] S&P 600",
                                                "[SPSUPX] S&P Composite 1500 Index",
                                                "[OEX] S&P 1000 Index",
                                                "[VIX] Chicago Board Options Exchange Market Volatilty Index",
                                                "[ABT] Abbott Laboratories","[ABBV] AbbVie","[ANF] Abercrombie & Fitch Company A","[ACE] ACE Limited","[ACN] Accenture","[ACT] Actavis Inc","[IBM] International Bus. Machines","[AAPL] Apple Inc.","[MMM] 3M Co."}; 
                        

                
                String[] exchangeRatesList = { "EUR/USD","GBP/USD","CAD/USD","AUD/USD","JPY/USD","CNY/USD","CHF/USD","SEK/USD"};
                        String[] seriesFromFREDList =
                                              { "[CPIAUCSL]U.S. Consumer Price Index for All Urban Consumers: All Items",    
                                                "[UNRATE]U.S. Civilian Unemployment Rate",
                                                "[BASE] St. Louis Adjusted Monetary Base ",
                                                "[M1] U.S. M1 Money Stock",
                                                "[M2] U.S. M2 Money Stock",
                                                "[GDP] U.S. Gross Domestic Product, 1 Decimal",
                                                "[GDPC1] U.S. Real Gross Domestic Product, 1 Decimal",
                                                "[DGS10] U.S. 10-Year Treasury Constant Maturity Rate ",
                                                "[TB3MS] U.S. 3-Month Treasury Bill: Secondary Market Rate"};
	/**
	 * Creates the dialog (if necessary) and
	 * makes it visible.
	 */

        
        public static void runit(String EstMethod) {
            
                EstimationMethodDialog = EstMethod;

			theDialog = new DToolsDataFromWeb();
                        theDialog.setVisible(true);
                        
                   theDialog.setTitle(EstimationMethodDialog);

                
		WindowTracker.addWindow(theDialog);

	}
          
        
        
        


        	public DToolsDataFromWeb() {
		super();
		initGUI();
                resetModel();
	}      

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                                 {
                                        mirrorType = new ButtonGroupWidget("partial or full mirror",
						new String[]{"partial mirror (names only in data frame)",
                                                             "full mirror"});
                                        mirrorType.setSelected("partial mirror (names only in data frame)");
                                        mirrorType.setFont(font12);
                                        getContentPane().add(mirrorType, new AnchorConstraint(750, 375, 725, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        mirrorType.setPreferredSize(new java.awt.Dimension(350, 70));
                                }
                        {
					yahooPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					yahooPanel.setLayout(varPanelLayout);
					getContentPane().add(yahooPanel, new AnchorConstraint(50, 375, 725, 100, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					yahooPanel.setBorder(BorderFactory.createTitledBorder("Yahoo data-highlight"));
                                        yahooPanel.setFont(font12);
					yahooPanel.setPreferredSize(new java.awt.Dimension(500, 300));
					{
						yahooScroller = new JScrollPane();
						yahooPanel.add(yahooScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							yahooList = new JList();
							yahooScroller.setViewportView(yahooList);
							yahooList.setModel(varListModel);
                                                        yahooList.setFont(font12);
						}
					}
				} 
                                yahooPanel.setVisible(false);

                                {
					oandaPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					oandaPanel.setLayout(varPanelLayout);
					getContentPane().add(oandaPanel, new AnchorConstraint(50, 375, 725, 400, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					oandaPanel.setBorder(BorderFactory.createTitledBorder("OANDA data-highlight"));
                                        oandaPanel.setFont(font12);
					oandaPanel.setPreferredSize(new java.awt.Dimension(150, 300));
					{
						oandaScroller = new JScrollPane();
						oandaPanel.add(oandaScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							oandaList = new JList();
							oandaScroller.setViewportView(oandaList);
							oandaList.setModel(varListModel);
                                                        oandaList.setFont(font12);
						}
					}
				} 
                                oandaPanel.setVisible(false);
                                {
					stLouisFREDPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					stLouisFREDPanel.setLayout(varPanelLayout);
					getContentPane().add(stLouisFREDPanel, new AnchorConstraint(50, 375, 725, 50, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					stLouisFREDPanel.setBorder(BorderFactory.createTitledBorder("FRED data-highlight"));
                                        stLouisFREDPanel.setFont(font12);
					stLouisFREDPanel.setPreferredSize(new java.awt.Dimension(500, 300));
					{
						stLouisFREDScroller = new JScrollPane();
						stLouisFREDPanel.add(stLouisFREDScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							stLouisFREDList = new JList();
							stLouisFREDScroller.setViewportView(stLouisFREDList);
							stLouisFREDList.setModel(varListModel);
                                                        stLouisFREDList.setFont(font12);
						}
					}
				} 
                                stLouisFREDPanel.setVisible(false);
                                 {
                                    yahooManualPanel  = new JPanel();
                                    getContentPane().add(yahooManualPanel, new AnchorConstraint(650, 375, 725, 100, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    yahooManualPanel.setLayout(modelFormulaLayout);
                                    yahooManualPanel.setBorder(BorderFactory.createTitledBorder("Yahoo symbols"));
                                    yahooManualPanel.setFont(font12);
                                    yahooManualPanel.setPreferredSize(new java.awt.Dimension(250, 42));
                                 }                                          
                                    {
                                        yahooManualText = new JTextField();
                                        yahooManualPanel.add(yahooManualText,BorderLayout.CENTER);
                                        yahooManualText.setEditable(true);
                                        yahooManualText.setFocusable(true);
                                        yahooManualText.setFont(font12);
                                        
                                    }  
                                    yahooManualPanel.setVisible(false);
                                 {
                                    oandaManualPanel  = new JPanel();
                                    getContentPane().add(oandaManualPanel, new AnchorConstraint(650, 375, 725, 400, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    oandaManualPanel.setLayout(modelFormulaLayout);
                                    oandaManualPanel.setBorder(BorderFactory.createTitledBorder("OANDA symbols"));
                                    oandaManualPanel.setFont(font12);
                                    oandaManualPanel.setPreferredSize(new java.awt.Dimension(150, 42));
                                 }                                   
                                 
                                    {
                                        oandaManualText = new JTextField();
                                        oandaManualPanel.add(oandaManualText,BorderLayout.CENTER);
                                        oandaManualText.setEditable(true);
                                        oandaManualText.setFocusable(true);
                                        oandaManualText.setFont(font12);
                                        
                                    } 
                                    oandaManualPanel.setVisible(false);
                                 {
                                    stLouisFREDManualPanel  = new JPanel();
                                    getContentPane().add(stLouisFREDManualPanel, new AnchorConstraint(650, 375, 725, 50, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    stLouisFREDManualPanel.setLayout(modelFormulaLayout);
                                    stLouisFREDManualPanel.setBorder(BorderFactory.createTitledBorder("FRED symbols"));
                                    stLouisFREDManualPanel.setFont(font12);
                                    stLouisFREDManualPanel.setPreferredSize(new java.awt.Dimension(250, 42));
                                 }                                          
                                    {
                                        stLouisFREDManualText = new JTextField();
                                        stLouisFREDManualPanel.add(stLouisFREDManualText,BorderLayout.CENTER);
                                        stLouisFREDManualText.setEditable(true);
                                        stLouisFREDManualText.setFocusable(true);
                                        stLouisFREDManualText.setFont(font12);
                                        
                                    } 
                                  stLouisFREDManualPanel.setVisible(false);
                                
                                  {
                                    multiNamePanel  = new JPanel();
                                    getContentPane().add(multiNamePanel, new AnchorConstraint(750, 375, 825, 50, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    multiNamePanel.setLayout(modelFormulaLayout);
                                    multiNamePanel.setBorder(BorderFactory.createTitledBorder("if multiple series, name for merged data"));
                                    multiNamePanel.setFont(font12);
                                    multiNamePanel.setPreferredSize(new java.awt.Dimension(280, 42));
                                 }                                          
                                    {
                                        multiNameText = new JTextField();
                                        multiNamePanel.add(multiNameText ,BorderLayout.CENTER);
                                        multiNameText.setEditable(true);
                                        multiNameText.setFocusable(true);
                                        multiNameText.setFont(font12);
                                        
                                    } 
                                  {
					doNotMerge = new JCheckBox();
					getContentPane().add(doNotMerge, new AnchorConstraint(850, 375, 925, 50, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					doNotMerge.setText("do not merge mulitiple data series");
                                        doNotMerge.setFont(font13);
					doNotMerge.setPreferredSize(new java.awt.Dimension(240, 18));
					doNotMerge.addActionListener(this);
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
                        

			
			this.setSize(700, 550);
                        this.setFont(font12);
                        this.setTitle(EstimationMethodDialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
        

	private void reset(){
                variableSelector.refreshDataNames();  
                doNotMerge.setSelected(false);
                panelIndexText.setText("");
                googleManualText.setText("");
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
                     
                        if (EstimationMethodDialog.equals("Download data from OANDA")){
                            oandaList.setListData(exchangeRatesList);
                            oandaPanel.setVisible(true);
                            oandaManualPanel.setVisible(true);
                            multiVariablesDataName = Deducer.getUniqueName("OANDAData");
                            multiNameText.setText(multiVariablesDataName); 
                        } else if (EstimationMethodDialog.equals("Download data from St. Louis FRED")){
                            stLouisFREDList.setListData(seriesFromFREDList);
                            stLouisFREDPanel.setVisible(true);
                            stLouisFREDManualPanel.setVisible(true);
                            multiVariablesDataName = Deducer.getUniqueName("FREDData");
                            multiNameText.setText(multiVariablesDataName); 
                        } else if (EstimationMethodDialog.equals("Download data from Yahoo!")){
                           yahooList.setListData(stockSymbols);
                           yahooPanel.setVisible(true);
                           yahooManualPanel.setVisible(true);
                           multiVariablesDataName = Deducer.getUniqueName("YahooData");
                           multiNameText.setText(multiVariablesDataName); 
                           
                        }
                   
	}
	
	public void updateModel(){
            int [] yahooListChoices = {-1};
            int [] oandaListChoices = {-1};
            int [] stLouisFREDListChoices = {-1};
            String yahooManualTextGiven="";
            String oandaManualTextGiven="";
            String stLouisFREDManualTextGiven="";
            yahooListChoices = null;
            oandaListChoices = null;
            stLouisFREDListChoices = null;
            yahooListChoices= yahooList.getSelectedIndices(); 
            oandaListChoices= oandaList.getSelectedIndices(); 
            
            stLouisFREDListChoices=stLouisFREDList.getSelectedIndices(); 
            yahooManualTextGiven= yahooManualText.getText();
            oandaManualTextGiven= oandaManualText.getText();
            stLouisFREDManualTextGiven= stLouisFREDManualText.getText();
            
            multiVariablesDataName = multiNameText.getText();
            
            Deducer.eval("options(\"getSymbols.warning4.0\"=FALSE)");
            String[] symbolsManual= null;
            String[] symbolsHighlighted = null;
            Integer numberSymbolsHighlighted = 0;
            Integer numberSymbolsManual = 0;
            String symbolsCollection = "";

            if (!yahooList.isSelectionEmpty()){
                numberSymbolsHighlighted = yahooListChoices.length;
                symbolsHighlighted = new String[numberSymbolsHighlighted];
                for(int i=0;i<numberSymbolsHighlighted;i++){
                    symbolsHighlighted[i] = stockSymbols[yahooListChoices[i]].toString().split("]")[0].substring(1);
                    Deducer.eval("getSymbols(\""+symbolsHighlighted[i]+"\",src=\"yahoo\")");
                    Deducer.execute("\"getSymbols used to retrieve "+symbolsHighlighted[i]+" from Yahoo!\"");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsHighlighted[i];
                }
            } 
          
             
            if (!yahooManualTextGiven.equals("")){
                symbolsManual = yahooManualTextGiven.split(",");
                numberSymbolsManual = symbolsManual.length;
                for(int i=0;i<(symbolsManual.length);i++){
                    Deducer.eval("getSymbols(\""+symbolsManual[i]+"\",src=\"yahoo\")");
                    Deducer.execute("\"getSymbols used to retrieve "+symbolsManual[i]+" from Yahoo!\"");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsManual[i];
                }
            }
            
            if (!oandaList.isSelectionEmpty()){
                numberSymbolsHighlighted = oandaListChoices.length;
                symbolsHighlighted = new String[numberSymbolsHighlighted];
                for(int i=0;i<numberSymbolsHighlighted;i++){
                    symbolsHighlighted[i] = exchangeRatesList[oandaListChoices[i]].toString();
                    Deducer.eval("getSymbols(\""+symbolsHighlighted[i]+"\",src=\"oanda\")");
                    Deducer.execute("\"getSymbols used to retrieve "+symbolsHighlighted[i]+" from OANDA\"");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsHighlighted[i];
                }
                
            }

             
            if (!oandaManualTextGiven.equals("")){
                symbolsManual = oandaManualTextGiven.split(",");
                numberSymbolsManual = symbolsManual.length;
                for(int i=0;i<(symbolsManual.length);i++){
                    Deducer.eval("getSymbols(\""+symbolsManual[i]+"\",src=\"oanda\")");
                    Deducer.execute("\"getSymbols used to retrieve "+symbolsManual[i]+" from OANDA\"");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsManual[i];
                }
            }
            
            if (!stLouisFREDList.isSelectionEmpty()){
                numberSymbolsHighlighted = stLouisFREDListChoices.length;
                
                symbolsHighlighted= new String[numberSymbolsHighlighted];
                for(int i=0;i<numberSymbolsHighlighted;i++){
                    symbolsHighlighted[i] = seriesFromFREDList[stLouisFREDListChoices[i]].toString().split("]")[0].substring(1);
                    Deducer.eval("getSymbols('"+symbolsHighlighted[i]+"',src='FRED')");
                    Deducer.execute("\"getSymbols('"+symbolsHighlighted[i]+"',src='FRED') used to retrieve "+symbolsHighlighted[i]+" from St. Louis FRED\"");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsHighlighted[i];
                }
                
            }
            if (!stLouisFREDManualTextGiven.equals("")){
                symbolsManual = stLouisFREDManualTextGiven.split(",");
                numberSymbolsManual = symbolsManual.length;
                for(int i=0;i<(symbolsManual.length);i++){
                    Deducer.eval("getSymbols(\""+symbolsManual[i]+"\",src=\"FRED\")");
                    symbolsCollection = (symbolsCollection.equals("")?"":symbolsCollection+",")+symbolsManual[i];
                    Deducer.execute("\"getSymbols(\""+symbolsManual[i]+"\",src=\"FRED\") used to retrieve "+symbolsManual[i]+" from St. Louis FRED\"");
                }
            }
            if (symbolsCollection.contains(",") & !doNotMerge.isSelected()) {
                String[] symbolsCollectionList = symbolsCollection.split(",");
                String classOfDataCurrent = new String();
                String periodicityOfDataCurrent = new String();
                String periodicityOfDataLast = new String();
                Boolean samePeriodicities = true;
                for(int i=0;i<(symbolsCollectionList.length);i++){
                    try {
                         periodicityOfDataCurrent = Deducer.eval("capture.output(periodicity("+symbolsCollectionList[i]+"))").asString().split(" ")[0];  
                        }catch(Exception e){
                        new ErrorMsg(e);
                        }
                    if (i>0) { 
                       if (!(periodicityOfDataCurrent.equals(periodicityOfDataLast))) {
                         samePeriodicities = false;
                       }
                    }
                    periodicityOfDataLast = new String(periodicityOfDataCurrent);
                 }              
                if (samePeriodicities) {   
                    Deducer.eval(multiVariablesDataName+"<- merge("+symbolsCollection+")");
                    String[] multiVariablesDataNameStringList = null;
                    multiVariablesDataNameStringList = (multiVariablesDataName+",").split(","); /*there is never a comma, so this results in one element.*/
                    makeMirrors(multiVariablesDataNameStringList);
                    Deducer.eval("rm("+symbolsCollection+")");
                } else {
                    JOptionPane.showMessageDialog(null, "Merge cannot be performed due to different periodicities.");
                }
            } else {
              if (numberSymbolsHighlighted>0)
                 makeMirrors(symbolsHighlighted);
              if (numberSymbolsManual>0)
                 makeMirrors(symbolsManual);
            }
        }
        
        public void makeMirrors(String[] symbolsList){
            String downloadObject;
            int j = 1;
            while (j <= symbolsList.length) {
                    downloadObject = symbolsList[j-1].replace("^","").replace("/","");
                String cmd ="";
                String classOfData = new String();
                      try {
                        classOfData = Deducer.eval("class("+downloadObject+")[1]").asString();  
                        }catch(Exception e){
                        new ErrorMsg(e);
                        }
                if (classOfData.equals("ts")||classOfData.equals("mts")||classOfData.equals("xts")||classOfData.equals("zoo")||classOfData.equals("zooreg")){
                
                    if (mirrorType.getSelectedItemText()=="full mirror"){
                        /* if (classOfData.equals("xts")) {
                         cmd = "\n "+downloadObject+(classOfData.equals("xts")?"<-"+downloadObject:"<-zoo("+downloadObject+")")+
                               "\n "+downloadObject+"__<-data.frame("+downloadObject+")";
                         } */
                         cmd = "\n "+downloadObject+"__<-data.frame("+downloadObject+")";
                        }else 
                        cmd = "\n if (is.null(colnames("+downloadObject+") )) {"+
                                  "colnames("+downloadObject+") <- \""+downloadObject+"\""+
                              "\n .hansel.temp.dimnamesToUse <-colnames("+downloadObject+")"+
                              "\n "+downloadObject+"__<- data.frame(rbind(.hansel.temp.dimnamesToUse))"+
                              "\n dimnames("+downloadObject+"__)[[2]] <-   .hansel.temp.dimnamesToUse"+
                              "\n "+downloadObject+(classOfData.equals("xts")?"<-"+downloadObject:"<-zoo("+downloadObject+")")+
                              "}else {"+
                         "\n "+downloadObject+(classOfData.equals("xts")?"<-"+downloadObject:"<-zoo("+downloadObject+")")+
                         "\n .hansel.temp.dimnamesToUse <-colnames("+downloadObject+")"+
                         "\n "+downloadObject+"__<- data.frame(rbind(.hansel.temp.dimnamesToUse))"+
                         "\n dimnames("+downloadObject+"__)[[2]] <-   .hansel.temp.dimnamesToUse}"+
                         "\n rm(.hansel.temp.dimnamesToUse)";
                }
                Deducer.execute(cmd);
                Deducer.eval(cmd);  
            j++;
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