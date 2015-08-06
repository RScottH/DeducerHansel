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
 
The current file made adjustments to that earlier java code on 2013-06-29 to work with the DeducerHansel package.
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
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.widgets.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class DToolsSpatialFileAssociations  extends JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        protected JPanel dfPanel;
        protected JScrollPane dfScroller;
        protected JList dfsList;
        
        protected JButton confirm1Button;
        protected JButton confirm2Button;
        protected JButton exitButton;
        
        public JTextArea nblistwAssociation;
        public JTextArea nblistw2Association;
        
        protected JPanel nblistw1AssociationPanel;
        protected JScrollPane nblistw1AssociationScroller;
        protected JPanel newnblistwPanel;
        protected JScrollPane newnblistwScroller;
        protected JList newnblistw1List;
        
        
        protected JPanel nblistw2AssociationPanel;
        protected JScrollPane nblistw2AssociationScroller;
        protected JPanel newnblistw2Panel;
        protected JScrollPane newnblistw2Scroller;
        protected JList newnblistw2List;
        
        protected ButtonGroupWidget nbFileUsage;
        protected ButtonGroupWidget listWFileUsage;
        
        protected JButton nblistwAssociationsConfirm2;
        protected JButton Exit;
        
        protected ListModel varListModel = new DefaultListModel();
        
        protected JPanel dataPanel;
        protected JScrollPane dataScroller;
        protected JList dataList;
        public DefaultListModel modelListTerms;
    
	public VariableSelectorWidget variableSelector;
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
        protected JLabel NoteOnRenaming1;
        protected JLabel NoteOnRenaming2;
        protected JLabel NoteOnRenaming3;
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
        
        protected JLabel seasonalLabel;
        protected JRadioButton noSeasonalDummies;
        protected JRadioButton quarterlyDummies;
        protected JRadioButton monthlyDummies;
        
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

	private static DToolsSpatialFileAssociations  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;
        
        protected JButton nblistwAssociationsConfirm1;

        protected JLabel zeroPolicyLabel1;
        protected JComboBox zeroPolicyType1;
        protected static DefaultComboBoxModel zeroPolicyTypeList1  = new DefaultComboBoxModel(
                				new String[] { "NULL",
                                               "TRUE",
                                               "FALSE"
                                              });
        
        protected JLabel listwstyleLabel1;
        protected JComboBox listwstyleType1;
        protected static DefaultComboBoxModel listwstyleTypeList1  = new DefaultComboBoxModel(
                				new String[] { "row standardised",
                                               "basic binary coding","globally standardised",
                                               "globally stand./# neighbors",
                                               "minmax",
                                               "variance stabilizing"
                                              });
        
        protected JLabel zeroPolicyLabel2;
        protected JComboBox zeroPolicyType2;
        protected static DefaultComboBoxModel zeroPolicyTypeList2  = new DefaultComboBoxModel(
                				new String[] { "NULL",
                                               "TRUE",
                                               "FALSE"
                                              });
        
        protected JLabel listwstyleLabel2;
        protected JComboBox listwstyleType2;
        protected static DefaultComboBoxModel listwstyleTypeList2  = new DefaultComboBoxModel(
                				new String[] { "row standardised",
                                               "basic binary coding","globally standardised",
                                               "variance stabilizing"
                                              });
        
        public static void runit(String EstMethod, String[] dfobjects, String[] nbobjects, String[] listWobjects) {
            
            Deducer.eval("if (!exists(\".hansel.working.env\")) {\n" +
                 "    .hansel.working.env <- new.env(parent=emptyenv())\n" +
                     "}");
            
                EstimationMethodDialog = EstMethod;

			theDialog = new DToolsSpatialFileAssociations();
                        theDialog.setVisible(true);

                            theDialog.dfsList.setListData(dfobjects);
                            if (dfobjects.length<=2) 
                               theDialog.dfsList.setSelectedIndex(0);
                            
                            theDialog.newnblistw1List.setListData(nbobjects);
                            if (nbobjects.length<=2) 
                               theDialog.newnblistw1List.setSelectedIndex(0);
                            
                            theDialog.newnblistw2List.setListData(listWobjects);
                            if (listWobjects.length<=2)  
                               theDialog.newnblistw2List.setSelectedIndex(0);
      
		WindowTracker.addWindow(theDialog);

	}

        	public DToolsSpatialFileAssociations() {
		super();
		initGUI();
                resetModel();
	}      

                
                
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                        {
					dfPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					dfPanel.setLayout(varPanelLayout);
					getContentPane().add(dfPanel, new AnchorConstraint(51, 375, 725, 20, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					dfPanel.setBorder(BorderFactory.createTitledBorder("Highlight data frame"));
                                        dfPanel.setFont(font12);
					dfPanel.setPreferredSize(new java.awt.Dimension(215, 120));
					{
						dfScroller = new JScrollPane();
						dfPanel.add(dfScroller, BorderLayout.CENTER);
						{
                                                        
                                                        dfsList = new JList(varListModel);
                                                        dfScroller.setViewportView(dfsList);
							dfsList.setModel(varListModel);
                                                        MouseListener mouseListener = new MouseAdapter() {
                                                            public void mouseClicked(MouseEvent e) {
                                                                if (e.getClickCount() == 1) {
                                                                    if (!model.efeglmextra.Spatialdf.equals(dfsList.getSelectedValue().toString()))
                                                                                            refreshCurrent();

                                                                 }
                                                            }
                                                        };
                                                        dfsList.addMouseListener(mouseListener);
                                                        
						}
					}
				} 

                        
                        
                                {
					nblistw1AssociationPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					nblistw1AssociationPanel.setLayout(varPanelLayout);
					getContentPane().add(nblistw1AssociationPanel, new AnchorConstraint(51, 375, 725, 300, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					nblistw1AssociationPanel.setBorder(BorderFactory.createTitledBorder("Current primary associations"));
					nblistw1AssociationPanel.setFont(font12);
                                        nblistw1AssociationPanel.setPreferredSize(new java.awt.Dimension(290, 90));
					{
						nblistw1AssociationScroller = new JScrollPane();
						nblistw1AssociationPanel.add(nblistw1AssociationScroller, BorderLayout.CENTER);
						{                                                        
                                                        nblistwAssociation = new JTextArea();
                                                        nblistwAssociation.setText("<None>");
                                                        nblistw1AssociationScroller.setViewportView(nblistwAssociation);
                                                        nblistwAssociation.setFont(new java.awt.Font("Monospaced",0,11));
                                                        nblistwAssociation.setEditable(false);
                                                        
                                                        
						}
					}
				} 
                                
                             
                                {
					newnblistwPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					newnblistwPanel.setLayout(varPanelLayout);
					getContentPane().add(newnblistwPanel, new AnchorConstraint(300, 375, 725, 300, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					newnblistwPanel.setBorder(BorderFactory.createTitledBorder("change primary associations using..."));
					newnblistwPanel.setFont(font12);
                                        newnblistwPanel.setPreferredSize(new java.awt.Dimension(290, 100));
					{
						newnblistwScroller = new JScrollPane();
						newnblistwPanel.add(newnblistwScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							newnblistw1List = new JList();
                                                        newnblistw1List.setFont(font12);
							newnblistwScroller.setViewportView(newnblistw1List);
							newnblistw1List.setModel(varListModel);
						}
					}
				} 
                        

                               {
					nblistw2AssociationPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					nblistw2AssociationPanel.setLayout(varPanelLayout);
					getContentPane().add(nblistw2AssociationPanel, new AnchorConstraint(51, 375, 725, 640, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					nblistw2AssociationPanel.setBorder(BorderFactory.createTitledBorder("Current secondary associations"));
					nblistw2AssociationPanel.setPreferredSize(new java.awt.Dimension(290, 90));
					{
						nblistw2AssociationScroller = new JScrollPane();
						nblistw2AssociationPanel.add(nblistw2AssociationScroller, BorderLayout.CENTER);
						{                                                       
                                                        nblistw2Association = new JTextArea();
                                                        nblistw2Association.setText("<None>");
                                                        nblistw2AssociationScroller.setViewportView(nblistw2Association);
                                                        nblistw2Association.setFont(new java.awt.Font("Monospaced",0,11));
                                                        nblistw2Association.setEditable(false);
                                                        
						}
					}
				} 

                            
                               
                               
                                 {
					newnblistw2Panel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					newnblistw2Panel.setLayout(varPanelLayout);
					getContentPane().add(newnblistw2Panel, new AnchorConstraint(300, 375, 725, 640, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					newnblistw2Panel.setBorder(BorderFactory.createTitledBorder("change secondary associations using..."));
					newnblistw2Panel.setFont(font12);
                                        newnblistw2Panel.setPreferredSize(new java.awt.Dimension(290, 100));
					{
						newnblistw2Scroller = new JScrollPane();
						newnblistw2Panel.add(newnblistw2Scroller, BorderLayout.CENTER);
                                                newnblistw2Panel.setFont(font12);
						{
							ListModel varListModel = 
								new DefaultListModel();
							newnblistw2List = new JList();
							newnblistw2Scroller.setViewportView(newnblistw2List);
							newnblistw2List.setModel(varListModel);
                                                        
						}
					}
				} 
                                 
                                 {
					zeroPolicyLabel1 = new JLabel();
					getContentPane().add(zeroPolicyLabel1, new AnchorConstraint(580, 600, 800, 300, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					zeroPolicyLabel1.setText("zero policy");
                                        zeroPolicyLabel1.setFont(font12);
                                        zeroPolicyLabel1.setFont(font12);
					zeroPolicyLabel1.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					zeroPolicyType1 = new JComboBox();                         
					getContentPane().add(zeroPolicyType1, new AnchorConstraint(630, 580, 835, 300, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        zeroPolicyType1.setFont(new java.awt.Font("Tahoma",0,12));
                                        zeroPolicyType1.setFont(font12);
                                        zeroPolicyType1.setModel(zeroPolicyTypeList1);
                                        
					zeroPolicyType1.setPreferredSize(new java.awt.Dimension(105, 21));
					zeroPolicyType1.addActionListener(this);
				}
                                
                                {
					listwstyleLabel1 = new JLabel();
					getContentPane().add(listwstyleLabel1, new AnchorConstraint(580, 600, 800, 450, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					listwstyleLabel1.setText("style");
                                        listwstyleLabel1.setFont(font12);
                                        listwstyleLabel1.setFont(font12);
					listwstyleLabel1.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					listwstyleType1 = new JComboBox();                         
					getContentPane().add(listwstyleType1, new AnchorConstraint(630, 580, 835, 450, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        listwstyleType1.setFont(new java.awt.Font("Tahoma",0,12));
                                        listwstyleType1.setFont(font12);
                                        listwstyleType1.setModel(listwstyleTypeList1);
                                        
					listwstyleType1.setPreferredSize(new java.awt.Dimension(150, 21));
					listwstyleType1.addActionListener(this);
				}
                                {
					confirm1Button = new JButton();
					getContentPane().add(confirm1Button, new AnchorConstraint(710, 580, 770, 300,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					confirm1Button.setText("Confirm changes to primary associations");
                                        confirm1Button.setFont(font12);
					confirm1Button.setPreferredSize(new java.awt.Dimension(290, 46));
					confirm1Button.addActionListener(this);
				}
 
                                  {
					zeroPolicyLabel2 = new JLabel();
					getContentPane().add(zeroPolicyLabel2, new AnchorConstraint(580, 600, 800, 640, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					zeroPolicyLabel2.setText("zero policy");
                                        zeroPolicyLabel2.setFont(font12);
					zeroPolicyLabel2.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					zeroPolicyType2 = new JComboBox();                         
					getContentPane().add(zeroPolicyType2, new AnchorConstraint(630, 580, 835, 640, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        zeroPolicyType2.setFont(new java.awt.Font("Tahoma",0,12));
                                         zeroPolicyType2.setFont(font12);
                                        zeroPolicyType2.setModel(zeroPolicyTypeList2);
                                        
					zeroPolicyType2.setPreferredSize(new java.awt.Dimension(105, 21));
					zeroPolicyType2.addActionListener(this);
				}
                                
                                {
					listwstyleLabel2 = new JLabel();
					getContentPane().add(listwstyleLabel2, new AnchorConstraint(580, 600, 800, 790, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					listwstyleLabel2.setText("style");
                                        listwstyleLabel2.setFont(font12);
					listwstyleLabel2.setPreferredSize(new java.awt.Dimension(160, 20));
				}
                                {
					listwstyleType2 = new JComboBox();                         
					getContentPane().add(listwstyleType2, new AnchorConstraint(630, 580, 835, 790, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
		                        listwstyleType2.setFont(new java.awt.Font("Tahoma",0,12));
                                        listwstyleType2.setModel(listwstyleTypeList2);
                                        
					listwstyleType2.setPreferredSize(new java.awt.Dimension(150, 21));
					listwstyleType2.addActionListener(this);
				}
                                {
					confirm2Button = new JButton();
					getContentPane().add(confirm2Button, new AnchorConstraint(710, 580, 770, 640,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					confirm2Button.setText("Confirm changes to secondary associations");
                                        confirm2Button.setFont(font12);
					confirm2Button.setPreferredSize(new java.awt.Dimension(290, 46));
					confirm2Button.addActionListener(this);
				}
 

			{
				
				helpButton = new HelpButton("pmwiki.php?n=Main.ExampleDialog");
				getContentPane().add(helpButton, new AnchorConstraint(940, 77, 980, 23, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				helpButton.setPreferredSize(new java.awt.Dimension(32, 32));
			}
                        
                        {
                                exitButton = new JButton();
                                getContentPane().add(exitButton, new AnchorConstraint(926, 978, 980, 402, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                                exitButton.setPreferredSize(new java.awt.Dimension(120, 32));
                                exitButton.setText("Exit");
                                exitButton.setFont(font12);
                                exitButton.addActionListener(this);
                        }
                        
			
			this.setSize(900, 400);
                        this.setFont(font12);
                        this.setTitle("Spatial File Associations");
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
                      Deducer.eval("if (!exists(\".hansel.working.env\")) {\n" +
                       "    .hansel.working.env <- new.env(parent=emptyenv())\n" +
                        "}");
            		model = mod;                
	}
	
	public void updateModel(String cmd){
            String dfChosen = dfsList.getSelectedValue().toString();
            if (!(dfsList.isSelectionEmpty())){
                String objectChosen ="";
                String nbFile="";
                String listwFile="";
                String oneOrTwo ="";
                if (cmd.equals("Confirm changes to primary associations")) {
                   objectChosen = newnblistw1List.getSelectedValue().toString();
                   oneOrTwo = "1";
                } else{
                   objectChosen = newnblistw2List.getSelectedValue().toString();
                   oneOrTwo = "2";
                }
                
                  String classObject = new String();
                  try {
                    classObject = Deducer.eval("class("+objectChosen+")").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
                if (classObject.equals("nb")){
                     nbFile = objectChosen;
                     if (cmd.equals("Confirm changes to primary associations"))
                     listwFile = "nb2listw("+objectChosen+ ",style=\\\""+
                             (listwstyleType1.getSelectedItem().toString().equals("row standardised")?"W":
                              (listwstyleType1.getSelectedItem().toString().equals("basic binary coding")?"B":
                               (listwstyleType1.getSelectedItem().toString().equals("globally standardised")?"C":
                                (listwstyleType1.getSelectedItem().toString().equals("globally stand./# neighbors")?"U":
                                 (listwstyleType1.getSelectedItem().toString().equals("minmax")?"minmax":
                                  (listwstyleType1.getSelectedItem().toString().equals("variance stabilizing")?"S":""))))))
                             +"\\\")";
                     else
                         listwFile = "nb2listw("+objectChosen+ ",style=\\\""+
                             (listwstyleType2.getSelectedItem().toString().equals("row standardised")?"W":
                              (listwstyleType2.getSelectedItem().toString().equals("basic binary coding")?"B":
                               (listwstyleType2.getSelectedItem().toString().equals("globally standardised")?"C":
                                (listwstyleType2.getSelectedItem().toString().equals("globally stand./# neighbors")?"U":
                                 (listwstyleType2.getSelectedItem().toString().equals("minmax")?"minmax":
                                  (listwstyleType2.getSelectedItem().toString().equals("variance stabilizing")?"S":""))))))
                             +"\\\")";
                         
                } else if (classObject.equals("listw")){
                     nbFile = "none";
                     listwFile = objectChosen;
                } else if (classObject.equals("matrix")){
                     nbFile = "none";
                     listwFile = "mat2listw("+objectChosen+")";
                }  else if (classObject.equals("SpatialPolygonsDataFrame")){
                    nbFile = "poly2nb("+objectChosen+")";       
                    if (cmd.equals("Confirm changes to primary associations"))
                             listwFile = "nb2listw(poly2nb("+objectChosen+"),style=\\\""+ 
                                     (listwstyleType1.getSelectedItem().toString().equals("row standardised")?"W":
                                      (listwstyleType1.getSelectedItem().toString().equals("basic binary coding")?"B":
                                       (listwstyleType1.getSelectedItem().toString().equals("globally standardised")?"C":
                                        (listwstyleType1.getSelectedItem().toString().equals("globally stand./# neighbors")?"U":
                                         (listwstyleType1.getSelectedItem().toString().equals("minmax")?"minmax":
                                          (listwstyleType1.getSelectedItem().toString().equals("variance stabilizing")?"S":""))))))
                                     +"\\\" "+ 
                                     (zeroPolicyType1.getSelectedItem().toString().equals("NULL") ? "" :",zero.policy="+zeroPolicyType1.getSelectedItem().toString()) 
                                     +")";
                        else
                            listwFile = "nb2listw(poly2nb("+objectChosen+"),style=\\\""+ (listwstyleType2.getSelectedItem().toString().equals("row standardised")?"W":
                                      (listwstyleType2.getSelectedItem().toString().equals("basic binary coding")?"B":
                                       (listwstyleType2.getSelectedItem().toString().equals("globally standardised")?"C":
                                        (listwstyleType2.getSelectedItem().toString().equals("globally stand./# neighbors")?"U":
                                         (listwstyleType2.getSelectedItem().toString().equals("minmax")?"minmax":
                                          (listwstyleType2.getSelectedItem().toString().equals("variance stabilizing")?"S":""))))))
                                     +"\\\" "+ 
                                     (zeroPolicyType2.getSelectedItem().toString().equals("NULL") ? "" :",zero.policy="+zeroPolicyType2.getSelectedItem().toString()) 
                                     +")";
                }
                
                
               Deducer.execute("if (exists(\"SPDrefs\",env =.hansel.working.env)){\n"+
                                        ".hansel.working.env$SPDrefs$"+dfChosen+"$nbFile"+oneOrTwo+" = \""+nbFile+"\"\n"+
                                        ".hansel.working.env$SPDrefs$"+dfChosen+"$listwFile"+oneOrTwo+"=\""+listwFile+"\"\n"+
                               "} else .hansel.working.env$SPDrefs <- list("+dfChosen+"=list(nbFile"+oneOrTwo+"= \""+nbFile+"\", listwFile"+oneOrTwo+"=\""+listwFile+"\"))");

            }

         
            if (!(newnblistw2List.isSelectionEmpty())) {
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


        
        public void refreshCurrent(){
                 model.efeglmextra.Spatialdf = dfsList.getSelectedValue().toString();
                 String primaryAssociationString = "<None>";
                 try {                      
                     primaryAssociationString = "nb call:    "+Deducer.eval(".hansel.working.env$SPDrefs[[\""+model.efeglmextra.Spatialdf+"\"]]$nbFile1").asString()
                                             +"\nlistw call: "+Deducer.eval(".hansel.working.env$SPDrefs[[\""+model.efeglmextra.Spatialdf+"\"]]$listwFile1").asString();
                    }catch(Exception err){
                   new ErrorMsg(err);
                   } 
                 nblistwAssociation.setText(primaryAssociationString);
                 nblistwAssociation.setCaretPosition(0);

                 String secondaryAssociationString = "<None>";
                 try {
                     secondaryAssociationString  = "nb call:    "+Deducer.eval(".hansel.working.env$SPDrefs[[\""+model.efeglmextra.Spatialdf+"\"]]$nbFile2").asString()
                                                +"\nlistw call: "+Deducer.eval(".hansel.working.env$SPDrefs[[\""+model.efeglmextra.Spatialdf+"\"]]$listwFile2").asString();                                          
                    }catch(Exception err){
                   new ErrorMsg(err);
                   } 
                 nblistw2Association.setText(secondaryAssociationString);
                 nblistw2Association.setCaretPosition(0);
        }
        
        public void continueClicked(String cmd){
            	updateModel(cmd);
	        refreshCurrent();

	}
	public class ManualModelInput{
		public String input = "";
                public boolean keep = false;
                public boolean runregression = false;
	}
        
           public void cancel(){

               
                this.setVisible(false);
		this.dispose();
	}
       
        
	   public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

               if ((cmd=="Confirm changes to primary associations")||(cmd=="Confirm changes to secondary associations")) {
                        continueClicked(cmd);
                        refreshCurrent();//This is done twice since once does not always completely work (oddly inconsistent).
		}else if(cmd=="Exit"){
			cancel();
                }else   // if(cmd=="Confirm changed highlight above"){ 
                    if (!model.efeglmextra.Spatialdf.equals(dfsList.getSelectedValue().toString())){
                        refreshCurrent();
    }
   }
}