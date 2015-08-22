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
  GLMExplorerPlots.java, found in the Deducer package
 and
  ExampleDialog.java (dated 2010-03-12), found in the DeducerPlugInExample package.
 
The current file made adjustments to that earlier java code on 2013-06-27 to work with the DeducerHansel package.
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




public class DToolsMirrorsFromSpatialData  extends JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        protected JPanel modelPanel;
        protected JScrollPane modelScroller;
        protected JList zootsList;
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
	private static DToolsMirrorsFromSpatialData  theDialog;
        protected GMModel model= new GMModel();
	protected GMModel modelOnOpen = new GMModel();
	protected static GMModel lastModel;
        protected static String EstimationMethodDialog;

        
        protected ButtonGroupWidget mirrorType;
        
        public static void runit(String EstMethod, String[] zootsobjects) {
            
                   EstimationMethodDialog = EstMethod;
 
  	 	   theDialog = new DToolsMirrorsFromSpatialData();
                   theDialog.setVisible(true);

                 
                   theDialog.zootsList.setListData(zootsobjects);
                            
                   theDialog.zootsList.setSelectedIndex(0);
                
		   WindowTracker.addWindow(theDialog);

	}


        	public DToolsMirrorsFromSpatialData() {
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
					getContentPane().add(modelPanel, new AnchorConstraint(51, 375, 725, 200, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					modelPanel.setBorder(BorderFactory.createTitledBorder(" Highlight one spatial data object"));
					modelPanel.setFont(font12);
                                        modelPanel.setPreferredSize(new java.awt.Dimension(215, 120));
					{
						modelScroller = new JScrollPane();
						modelPanel.add(modelScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							zootsList = new JList();
							modelScroller.setViewportView(zootsList);
							zootsList.setModel(varListModel);

						}
					}
				} 
                                
                                                        {
                                        mirrorType = new ButtonGroupWidget("partial or full mirror",
						new String[]{"partial mirror (names only in data frame)",
                                                             "full mirror"});
                                        mirrorType.setSelected("partial mirror (names only in data frame)");
                                        mirrorType.setFont(font12);
                                        getContentPane().add(mirrorType, new AnchorConstraint(401, 375, 725, 200, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        mirrorType.setPreferredSize(new java.awt.Dimension(260, 70));
                                }
                        
			
                        {
                                NoteOnRenaming1 = new JLabel();
                                getContentPane().add(NoteOnRenaming1, new AnchorConstraint(650, 487, 822, 150,  
                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                NoteOnRenaming1.setText("! Note: Upon \"Run\" your original data set will be replaced by");
                                NoteOnRenaming1.setFont(font12);
                                NoteOnRenaming1.setPreferredSize(new java.awt.Dimension(400, 20));
                        } 
                        {
                                NoteOnRenaming2 = new JLabel();
                                getContentPane().add(NoteOnRenaming2, new AnchorConstraint(680, 487, 822, 150,  
                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                NoteOnRenaming2.setText("  a zoo version with \"T.S\" prefixed to the original object name");
                                NoteOnRenaming2.setFont(font12);
                                NoteOnRenaming2.setPreferredSize(new java.awt.Dimension(400, 20));
                        } 
                                                {
                                NoteOnRenaming3 = new JLabel();
                                getContentPane().add(NoteOnRenaming3, new AnchorConstraint(710, 487, 822, 150,  
                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                NoteOnRenaming3.setText("  and the original object name will refer to a data frame.");
                                NoteOnRenaming3.setFont(font12);
                                NoteOnRenaming3.setPreferredSize(new java.awt.Dimension(400, 20));
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
			}       okayCancelPanel.setFont(font12);
                        

			
			this.setSize(500, 400);
                        this.setFont(font12);
                        this.setTitle("Create Data Mirroring for a object of class zoo, mts, or ts");
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
	
	public void updateModel(){
            String ChosenObject = zootsList.getSelectedValue().toString();
            String cmd="";
            String classOfData = new String();
                  try {
                    classOfData = Deducer.eval("class("+ChosenObject+")[1]").asString();  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }
            
            if (mirrorType.getSelectedItemText()=="full mirror"){
                 cmd = "\n "+ChosenObject+"__<-data.frame("+ChosenObject+")";

                }else 
                cmd = 
                 "\n dimnamesToUse <-colnames(data.frame("+ChosenObject+"))"+
                 "\n"+ChosenObject+"__<- data.frame(rbind(dimnamesToUse))"+
                 "\n dimnames("+ChosenObject+"__)[[2]] <-   dimnamesToUse";
            
            
            
            Deducer.execute(cmd);  

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
                String ChosenObject = zootsList.getSelectedValue().toString();
    
                            
	         Deducer.eval("J('hansel.SpatialFileAssociations')$runit(\"spatial file associations\","+
                 "c(\""+zootsList.getSelectedValue().toString()+"__\",\" \""+
                   "),"+
                 
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