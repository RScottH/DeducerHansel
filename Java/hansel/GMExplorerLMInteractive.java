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
 
 The current file notably uses code from PlotBuilderSubFrame.java and GLMExplorerPostHoc.java in the Deducer package.
 
The current file made adjustments to that earlier java code on 2014-08-21 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;
import org.rosuda.deducer.toolkit.SideWindow;

import org.rosuda.deducer.models.RModel;


public class GMExplorerLMInteractive extends SideWindow implements ActionListener{
        public Font font16 = new Font("serif", Font.TRUETYPE_FONT, 16);
        public Font font14 = new Font("serif", Font.TRUETYPE_FONT, 14);
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        
        protected ActionListener generalListener = new ModelListener();
        public JCheckBox immediate;
        private OkayCancelPanel okayCancel;
        public String theParentHere;
        public String interactiveType;
        public Boolean editButtonCameEnabled;
        public Boolean iplotButtonCameEnabled;
        
        public GMExplorerLMInteractive(Window theParent) {
		super(theParent);
		initGUI();
		updateLocation();
		updateSize();
	}

        protected GMModel  model;
        protected int panelDealtWith;
        
        protected JLabel MenuTitle;
        protected JButton labelsButton;
        protected JButton cancelButton;
        protected JButton applyButton;

        public JPanel middlePanel;
        protected JTabbedPane tabs;
        public JPanel mainTab;
        
        protected JPanel userInputMainTitle;
        protected JPanel userInputxAxisTitle;
        protected JPanel userInputyAxisTitle;
        
        protected JScrollPane userInputMainTitleScroller;
        protected JTextArea userInputMainTitleText;
        protected JScrollPane userInputxAxisTitleScroller;
        protected JTextArea userInputxAxisTitleText;
        protected JScrollPane userInputyAxisTitleScroller;
        protected JTextArea userInputyAxisTitleText;
        

        public JCheckBox showTickMarks;
        public JCheckBox showGrid;
        
        
        protected JRadioButton traditionalGraphicsButton; 
        protected JRadioButton latticeButton; 
        protected JRadioButton ggplot2Button;
        protected ButtonGroup graphicSystemsButtons;
        
        public JCheckBox ggplot2GrayTheme;
        
        public JPanel yAxisTab;
        public JPanel xAxisTab;
        
        public JCheckBox manualxAxisRange;
        protected JPanel manualxAxisMinTitle;
        protected JScrollPane manualxAxisMinScroller;
        protected JTextArea  manualxAxisMinText;
        protected JPanel manualxAxisMaxTitle;
        protected JScrollPane manualxAxisMaxScroller;
        protected JTextArea  manualxAxisMaxText;
        
        public JCheckBox manualyAxisRange;
        protected JPanel manualyAxisMinTitle;
        protected JScrollPane manualyAxisMinScroller;
        protected JTextArea  manualyAxisMinText;
        protected JPanel manualyAxisMaxTitle;
        protected JScrollPane manualyAxisMaxScroller;
        protected JTextArea  manualyAxisMaxText;
        
                                                          
        
        
        public JPanel linesPointsTab;
        public JCheckBox includeConfIntvl95;
        protected JLabel pointLabelsLabel;
        protected JComboBox pointLabelsType;
        
        protected JLabel linePointsLabel1;
        protected JPanel legendTitle1;
        protected JScrollPane legendScroller1;
        protected JTextArea legendText1;
        protected JLabel keyPositionLabel;
        protected JComboBox keyPositionType;
        protected DefaultComboBoxModel keyPositionChoices  = new DefaultComboBoxModel(
                				new String[] {
                                               "top","bottom", "left", "right",
                                              });      

        protected JLabel symbolTextSizeLabel;
        protected JComboBox symbolTextSizeType;
        protected DefaultComboBoxModel symbolTextSizeChoices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel lineTypeLabel1;
        protected JComboBox lineType1;
        
        protected DefaultComboBoxModel lineType1Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        
        
        protected JLabel lineWidthLabel1;
        protected JComboBox lineWidth1;
        
        protected DefaultComboBoxModel lineWidth1Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        
        protected JLabel linePointsColorLabel1;
        protected JComboBox linePointsColorType1;
        protected DefaultComboBoxModel linePointsColorType1Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        
        protected JLabel symbolLabel1;
        protected JComboBox symbolType1;
        protected DefaultComboBoxModel linePoints1SymbolChoices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "0: square","1: o", "2: triangle point up","3: +","4: x","5: diamond",
                                                     "6: triangle point down","7: square & x","8: *","9: diamond & +",
                                                     "10: circle & +","11: intertwined triangles","12: square & +",
                                                     "13: o & x","14: square and triangle point up","15: solid square",
                                                     "16: solid circle","17: solid triangle point up","18: solid diamond",
                                                     "19: solid circle bordered","20: bullet",
                                              });
                
        
        protected JLabel separator1;
        protected JLabel linePointsLabel2;
        protected JPanel legendTitle2;
        protected JScrollPane legendScroller2;
        protected JTextArea legendText2;
        protected JLabel lineTypeLabel2;
        protected JComboBox lineType2;
        
        protected DefaultComboBoxModel lineType2Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        protected JLabel lineWidthLabel2;
        protected JComboBox lineWidthType2;
        protected DefaultComboBoxModel lineWidthType2Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel2;
        protected JComboBox linePointsColorType2;
        protected DefaultComboBoxModel linePointsColorType2Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        protected JLabel separator2;
        protected JLabel linePointsLabel3;
        protected JPanel legendTitle3;
        protected JScrollPane legendScroller3;
        protected JTextArea legendText3;
        protected JLabel lineTypeLabel3;
        protected JComboBox lineType3;
        
        protected DefaultComboBoxModel lineType3Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        protected JLabel lineWidthLabel3;
        protected JComboBox lineWidthType3;
        protected DefaultComboBoxModel lineWidthType3Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel3;
        protected JComboBox linePointsColorType3;
        protected DefaultComboBoxModel linePointsColorType3Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
                
                
        
        protected DefaultComboBoxModel pointLabelsChoices  = new DefaultComboBoxModel(
                				new String[] {"row names","x values","y values"
                                              });
	
	public GMExplorerLMInteractive(JFrame frame,GMModel mod,String parent,RModel rmod,int inputPanelDealtWith,String interactiveTypeGiven) {
		super(frame);
		initGUI();
                panelDealtWith = inputPanelDealtWith;
                theParentHere = parent;
                interactiveType = interactiveTypeGiven;
		setModel(mod,rmod);
  
	}
	
	protected void initGUI() {
		try {
			{
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
     
                        
                        {
                        MenuTitle = new JLabel();
                        getContentPane().add(MenuTitle, new AnchorConstraint(10, 863, 38, 250, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                        MenuTitle.setText("Interactive");
                        MenuTitle.setFont(font16);
                        MenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
                        MenuTitle.setPreferredSize(new java.awt.Dimension(150, 20));
                      } 
                        
                        
                       {
                        cancelButton = new JButton();
                        getContentPane().add(cancelButton , new AnchorConstraint(925, 970, 117, 510, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                        cancelButton .setText("Cancel");
                        cancelButton .setFont(font12);
                        cancelButton .setPreferredSize(new java.awt.Dimension(70, 35));
                        cancelButton .addActionListener(generalListener);
                        cancelButton .setVisible(true);
                        cancelButton .addActionListener(this);
                        }    
                       {
                        applyButton = new JButton();
                        getContentPane().add(applyButton, new AnchorConstraint(925, 970, 117, 750, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                        applyButton.setText("Apply");
                        applyButton.setFont(font12);
                        applyButton.setPreferredSize(new java.awt.Dimension(70, 35));
                        applyButton.addActionListener(generalListener);
                        applyButton.setVisible(true);
                        applyButton.addActionListener(this);
                        }   
                        

                         {
                                userInputMainTitle = new JPanel();
                                getContentPane().add(userInputMainTitle, new AnchorConstraint(100, 520, 200, 100,
                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                userInputMainTitle.setLayout(coefTestsManualLayout);
                                userInputMainTitle.setFont(font12);
                                userInputMainTitle.setBorder(BorderFactory.createTitledBorder("Instructions"));
                                userInputMainTitle.setPreferredSize(new java.awt.Dimension(500, 350));
                                }                                           
                                {
                                    userInputMainTitleScroller = new JScrollPane();
                                    userInputMainTitle.add(userInputMainTitleScroller, BorderLayout.CENTER);
                                        {
                                                userInputMainTitleText = new JTextArea();
                                                userInputMainTitleScroller.setViewportView(userInputMainTitleText);
                                                userInputMainTitleText.setFont(new java.awt.Font("Monospaced",0,11));
                                                userInputMainTitleText.setFont(font12);
                                                userInputMainTitleText.setEditable(true);
                                               
                                        }
                              }   
                            {
                                    pointLabelsLabel = new JLabel();
                                    getContentPane().add(pointLabelsLabel, new AnchorConstraint(800, 863, 38, 100, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                    AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    pointLabelsLabel.setText("point labels");
                                    pointLabelsLabel.setFont(font12);
                                    pointLabelsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                                    pointLabelsLabel.setPreferredSize(new java.awt.Dimension(80, 20));
                                    pointLabelsLabel.setVisible(false);
                           } 
                            {
                                    pointLabelsType = new JComboBox();
                                    getContentPane().add(pointLabelsType, new AnchorConstraint(800, 990, 38, 400, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                    AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    pointLabelsType.setFont(font12);
                                    pointLabelsType.setModel(pointLabelsChoices);
                                    pointLabelsType.setPreferredSize(new java.awt.Dimension(90, 21));
                                    pointLabelsType.setVisible(false);
                            }
	
			}
			this.setTitle("Plot Controls");
                        this.setFont(font12);
			this.setSize(350, 500);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
        public void enableParentButtons(){
            if (theParentHere.equals("GMExplorer")){
                   ((GMExplorer)parent).diagnosticsButton.setEnabled(true);
                   ((GMExplorer)parent).regressionFunctionPlotButton.setEnabled(true);
                   ((GMExplorer)parent).partialregressionPlotButton.setEnabled(true);
                   ((GMExplorer)parent).residualsVsFitButton.setEnabled(true);
                   ((GMExplorer)parent).plotsvsObsButton.setEnabled(true);
                   ((GMExplorer)parent).otherPlotsButton.setEnabled(true);
                   
                   ((GMExplorer)parent).editButton.setEnabled(editButtonCameEnabled);         
                   ((GMExplorer)parent).idLabelButton.setEnabled(true);
                   ((GMExplorer)parent).iplotButton.setEnabled(iplotButtonCameEnabled);      
                   
                } else if (theParentHere.equals("EFEUTSExplorer")) {
                    ((NMTimeSExplorer)parent).plotvsTimeButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).distributionButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).correlogramButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).plotvsLagsButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).structuralBreaksButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).filtertype.setEnabled(true);
                    ((NMTimeSExplorer)parent).resultsAreaViewType.setEnabled(true);
                    ((NMTimeSExplorer)parent).idLabelButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).editButton.setEnabled(true); 
                }
        }
	public void updateModel(){
                  
	}
        
       
        
	public void setModel(GMModel mod,RModel rmod){
              model = mod;  
              if (theParentHere.equals("GMExplorer")){
                  ((GMExplorer)parent).generalTab.setEnabled(false);
                   ((GMExplorer)parent).diagnosticsButton.setEnabled(false);
                   ((GMExplorer)parent).regressionFunctionPlotButton.setEnabled(false);
                   ((GMExplorer)parent).partialregressionPlotButton.setEnabled(false);
                   ((GMExplorer)parent).residualsVsFitButton.setEnabled(false);
                   ((GMExplorer)parent).plotsvsObsButton.setEnabled(false);
                   ((GMExplorer)parent).otherPlotsButton.setEnabled(false);
                   editButtonCameEnabled = ((GMExplorer)parent).editButton.isEnabled();
                   iplotButtonCameEnabled = ((GMExplorer)parent).iplotButton.isEnabled();  
                   ((GMExplorer)parent).idLabelButton.setEnabled(false);
                   ((GMExplorer)parent).iplotButton.setEnabled(false);
                   ((GMExplorer)parent).editButton.setEnabled(false);       
                } else if (theParentHere.equals("EFEUTSExplorer")) {
                    ((NMTimeSExplorer)parent).plotvsTimeButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).distributionButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).correlogramButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).plotvsLagsButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).structuralBreaksButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).filtertype.setEnabled(false);
                    ((NMTimeSExplorer)parent).resultsAreaViewType.setEnabled(false);
                    ((NMTimeSExplorer)parent).idLabelButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).editButton.setEnabled(false); 
                }
              
              
        if (interactiveType.equals("id-label")) {    
            userInputMainTitleText.setText("1. Click \"Apply\" to execute command to\n"+
                                       "   show labels in response to mouse clicks,"+
                                       "   which activates a loop in R;\n"+
                                       "   no additonal R commands can be excuted\n"+
                                       "   while the loop is active.\n"+
                                       "2. Left click on plot to identify point\n"+
                                       "   (sometimes a double-click is needed).\n"+
                                       "3. Right click on same plot to deactivate the\n"+
                                       "   loop.\n\n"+
                                       "Click \"Cancel\" only if you have\n"+
                                       "deactivated the loop, or never activated it.\n"+
                                       "(otherwise the program may crash later). \n\n"+
                                       "To have printable version with interactive\n"+
                                       "additions, click \"Pop out\" in Plot Area first\n"+
                                       "and left click in steps 2 and 3 on the popped-out plot");
           pointLabelsLabel.setVisible(true);
           pointLabelsType.setVisible(true);
           pointLabelsChoices.setSelectedItem("row names");
        } else if (interactiveType.equals("iplot"))  
            userInputMainTitleText.setText("1. Click \"Apply\" to bring up an iplot frame \n"+
                                       "   and execute a command to show mouse-responsive labels,"+
                                       "   which activates a loop in R;\n"+
                                       "   no additonal R commands can be excuted\n"+
                                       "   while the loop is active.\n"+
                                       "2. Highlight on plot to identify points\n"+
                                       "   and click on empty area to erase them.\n"+
                                       "3. To deactivate loop, choose File>Break in iplot frame\n"+
                                       "   or press CNTRL+SHIFT+B with cursor over iplot frame.\n\n"+
                                       "DO NOT close the iplot frame through the upper-right X\n\n"+
                                       "prior to deactivating the loop, because then you won't be able\n"+
                                       "to deactivate the loop afterwards.\n\n"+
                                       "DO NOT close the iplot frame through File>Quit (CNTRL+Q) in the\n"+
                                       "iplot frame, as that typically closes all of JGR!\n\n"+
                                       "Click \"Cancel\" only if you have\n"+
                                       "deactivated the loop, or never activated it.\n"+
                                       "(otherwise the program may crash later)");
       
        
        
        }
         public void okay (){
            updateModel();
            if (theParentHere.equals("GMExplorer")){
                ((GMExplorer)parent).alterPlot("Plot Edit");
            }else if (theParentHere.equals("EFEUTSExplorer")){
                 ((NMTimeSExplorer)parent).alterPlot("Plot Edit");
            }
            enableParentButtons();
            model.plotControl3.PlotControlsTab = 0;
            this.dispose();
        }
        public void cancel(){
            enableParentButtons();
           this.dispose();
        }
        public void apply() {
           String call = new String();
           String functionName = new String();
           String labelsStatement = new String();
           if (pointLabelsChoices.getSelectedItem().toString().equals("row names")){
               labelsStatement = model.rowNamesOrDatesLabelModel;
           } else if (pointLabelsChoices.getSelectedItem().toString().equals("x values")){
               labelsStatement = model.plotControl.xLabels[panelDealtWith];
           } else if (pointLabelsChoices.getSelectedItem().toString().equals("y values")){
               labelsStatement = model.plotControl.yLabels[panelDealtWith];
           }

           
           if (interactiveType.equals("id-label")) { 
                    if (model.plotControl.graphicSystem[panelDealtWith].equals("traditional graphics")){
                       if (theParentHere.equals("GMExplorer"))
                          call = "identify("+model.plotControl.traditionalPlotFormula[panelDealtWith]+",labels="+labelsStatement+")";
                        else if (theParentHere.equals("EFEUTSExplorer"))
                          call = "identify(as.ts("+((NMTimeSExplorer)parent).vNameAdjusted+"),labels="+labelsStatement+")";  
                    } else if (model.plotControl.graphicSystem[panelDealtWith].equals("lattice"))
                        call = "trellis.focus()\n" +
                               "panel.identify(labels="+labelsStatement+")";
            } else if (interactiveType.equals("iplot"))     
                 call = "labels <-mapply(\"itext\","+
                 "\n"+model.data+"[,\""+model.termsArray[0]+"\"],"+model.data+"[,\""+model.dependentVar+"\"],"+model.rowNamesOrDatesLabel+","+
                 "\n MoreArgs=list(visible=FALSE),SIMPLIFY=FALSE)"+ 
                 "\n olds <- NULL"+
                 "\n while(!is.null(ievent.wait())) {"+
                 "\n            if (iset.sel.changed()){"+
                 "\n               s <- iset.selected()"+
                 "\n             if (length(s) > 0) {"+
                 "\n                 lapply(labels[s],iobj.opt, visible = TRUE)"+
                 "\n             }"+
                 "\n              if (length(olds) > 1) {"+
                 "\n                 lapply(labels[olds],iobj.opt, visible = FALSE)"+
                 "\n              }"+
                 "\n              olds <- s"+
                 "\n             }"+
                 "\n        }";

           
         if (Hansel.isJGR()) {
           Deducer.execute(call);
           } else {
               String functionCalled = (interactiveType.equals("iplot")?".iplotLabel":".idLabel");
               Deducer.execute("suppressMessages(library(rgl))\n"+
                         functionCalled+"<- function(){\n"+
                           "    "+call+"\n"+
                           "}\n"+
                       "#Enter \" "+functionCalled+"() \" to execute the interactive loop.\"\n"
                          ); 
           }   
        }
        
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
                    cancel();

                }else if(cmd=="Apply"){
                   apply();
		}else if(cmd=="OK"){
                   
                    okay();
                }else if(cmd=="Labels"){
                    okay();   
                } else if (cmd.equals("Activate"))  {
                    manualxAxisMinText.setEnabled(!manualxAxisMinText.isEnabled());
                    manualxAxisMinText.setBackground((manualxAxisMinText.isEnabled())?Color.WHITE:Color.lightGray);
                    manualxAxisMaxText.setEnabled(!manualxAxisMaxText.isEnabled());
                    manualxAxisMaxText.setBackground((manualxAxisMaxText.isEnabled())?Color.WHITE:Color.lightGray);
                } else if (cmd.equals("manual y-axis range"))  {
                    manualyAxisMinText.setEnabled(!manualyAxisMinText.isEnabled());
                    manualyAxisMinText.setBackground((manualyAxisMinText.isEnabled())?Color.WHITE:Color.lightGray);
                    manualyAxisMaxText.setEnabled(!manualyAxisMaxText.isEnabled()); 
                    manualyAxisMaxText.setBackground((manualyAxisMaxText.isEnabled())?Color.WHITE:Color.lightGray);
                }

        }
      class ModelListener implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
        }
    }

}