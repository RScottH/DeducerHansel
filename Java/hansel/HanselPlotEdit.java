/** This file is part of the DeducerHansel package by R. Scott H/\ker (replace "/\" with "ac")
 and is covered by its GPL2 license.
 
 DeducerHansel files have liberally borrowed code from many java files from the following packages by Ian Fellows:
     Deducer (0.7-6 version, dated 2012-12-05)
     DeducerPlugInExample (0.2-0 version, dated 2012-03-16)
 The code in the files of those packages are covered by the GPL2 licenses for those packages.
 
 The current file notably uses code from ModelExplorer.java, GLMExplorerPostHoc.java, and PlotBuilderSubFrame.java in the Deducer package.
 
The current file made adjustments to that earlier java code on 2014-07-23 to work with the DeducerHansel package.
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


public class HanselPlotEdit extends SideWindow implements ActionListener{
        public Font font16 = new Font("serif", Font.TRUETYPE_FONT, 16);
        public Font font14 = new Font("serif", Font.TRUETYPE_FONT, 14);
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
        
        protected ActionListener generalListener = new ModelListener();
        public JCheckBox immediate;
        private OkayCancelPanel okayCancel;
        public String theParentHere;
        public Boolean noReactionToAction = true;
        public Boolean editButtonCameEnabled;
        public Boolean interactiveButtonCameEnabled;
        public Boolean iplotButtonCameEnabled;
        
        public HanselPlotEdit(Window theParent) {
		super(theParent);
		initGUI();
		updateLocation();
		updateSize();
	}

        public Boolean existsTS;
        protected GMModel  model;
        protected int panelDealtWith;
        
        protected JLabel plotControlsLabel;

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
        
        public JCheckBox singleGraph;
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
        public JPanel moreLinesPointsTab;
        public JCheckBox includeConfIntvl95;
        protected JLabel pointLabelsLabel;
        protected JComboBox pointLabelsType;
        protected JComboBox pointLabelsTypeRowNamesOnly;
        
        
        protected JLabel linePointsLabel1;
        protected JPanel legendTitle1;
        protected JScrollPane legendScroller1;
        protected JTextArea legendText1;
        protected JLabel keyPositionLabel;
        protected JComboBox keyPositionTypeTraditional;
        protected DefaultComboBoxModel keyPositionChoicesTraditional  = new DefaultComboBoxModel(
                				new String[] {
                                               "topleft","top","topright", "left", "right","bottomleft","bottom","bottomright"
                                              }); 
        protected JComboBox keyPositionTypeLattice;
        protected DefaultComboBoxModel keyPositionChoicesLattice  = new DefaultComboBoxModel(
                				new String[] {
                                               "top","left","right","bottom"
                                              });      
        protected JLabel pointLabelTextSizeLabel;
        protected JLabel pointLabelTextSizeLabel2;
        protected JComboBox pointLabelTextSizeType;
        protected DefaultComboBoxModel pointLabelTextSizeChoices  = new DefaultComboBoxModel(
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
        protected DefaultComboBoxModel linePointsColor1Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        
        protected JLabel symbolLabel1;
        protected JComboBox symbolType1;
        protected DefaultComboBoxModel plotType1Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "l: line", "p: points", "b: both","c: line-points","o: overplotted","h: histogram-like",
                                                     "s: stair steps","S: other steps","n: none"
                                              });
        
        protected JLabel plotTypeLabel1;
        protected JComboBox plotType1;
        protected DefaultComboBoxModel linePoints1SymbolChoices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "0: square","1: o", "2: triangle point up","3: +","4: x","5: diamond",
                                                     "6: triangle point down","7: square & x","8: *","9: diamond & +",
                                                     "10: circle & +","11: intertwined triangles","12: square & +",
                                                     "13: o & x","14: square and triangle point up","15: solid square",
                                                     "16: solid circle","17: solid triangle point up","18: solid diamond",
                                                     "19: solid circle bordered","20: bullet",
                                                     //"21: filled circle","22: filled square","23: filled diamond",
                                                     //"24: filled triangle point up","25: filled triangle point down",
                                              });
        protected JLabel symbolSizeLabel1;
        protected JComboBox symbolSize1;
        protected DefaultComboBoxModel symbolSize1Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                      "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel symbolSizeEqLineWidth;
        protected JLabel symbolSizeEqLineWidth2;         
        
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
        protected JComboBox lineWidth2;
        protected DefaultComboBoxModel lineWidth2Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel2;
        protected JComboBox linePointsColorType2;
        protected DefaultComboBoxModel linePointsColor2Choices  = new DefaultComboBoxModel(
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
        protected JComboBox lineWidth3;
        protected DefaultComboBoxModel lineWidth3Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel3;
        protected JComboBox linePointsColorType3;
        protected DefaultComboBoxModel linePointsColor3Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });

        protected JLabel linePointsLabel4;
        protected JPanel legendTitle4;
        protected JScrollPane legendScroller4;
        protected JTextArea legendText4;
        protected JLabel lineTypeLabel4;
        protected JComboBox lineType4;
        
        protected DefaultComboBoxModel lineType4Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        protected JLabel lineWidthLabel4;
        protected JComboBox lineWidth4;
        protected DefaultComboBoxModel lineWidth4Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel4;
        protected JComboBox linePointsColorType4;
        protected DefaultComboBoxModel linePointsColor4Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        protected JLabel separator4;
        
        protected JLabel linePointsLabel5;
        protected JPanel legendTitle5;
        protected JScrollPane legendScroller5;
        protected JTextArea legendText5;
        protected JLabel lineTypeLabel5;
        protected JComboBox lineType5;
        
        protected DefaultComboBoxModel lineType5Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        protected JLabel lineWidthLabel5;
        protected JComboBox lineWidth5;
        protected DefaultComboBoxModel lineWidth5Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel5;
        protected JComboBox linePointsColorType5;
        protected DefaultComboBoxModel linePointsColor5Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        protected JLabel separator5;
        
        
        protected JLabel linePointsLabel6;
        protected JPanel legendTitle6;
        protected JScrollPane legendScroller6;
        protected JTextArea legendText6;
        protected JLabel lineTypeLabel6;
        protected JComboBox lineType6;
        
        protected DefaultComboBoxModel lineType6Choices  = new DefaultComboBoxModel(
                				new String[] {"blank", "solid", "dashed", "dotted", "dotdash", "longdash", "twodash"
                                              });
        protected JLabel lineWidthLabel6;
        protected JComboBox lineWidth6;
        protected DefaultComboBoxModel lineWidth6Choices  = new DefaultComboBoxModel(
                				new String[] {
                                               "0.2","0.5", "0.8", "1.0", "1.5","2.0","2.5","3.0","3.5","4.0","4.5","5.0","5.5","6.0"
                                              });
        protected JLabel linePointsColorLabel6;
        protected JComboBox linePointsColorType6;
        protected DefaultComboBoxModel linePointsColor6Choices  = new DefaultComboBoxModel(
                				new String[] {
                                                     "black","gray","red","orange","yellow","green","blue",
                                               "purple","violet","brown"
                                              });
        
        
        
        protected DefaultComboBoxModel pointLabelsChoices  = new DefaultComboBoxModel(
                				new String[] {"row names","x values","y values", "extremeness measure",
                                              });
        
        protected DefaultComboBoxModel pointLabelsChoicesRowNamesOnly  = new DefaultComboBoxModel(
                				new String[] {"row names"
                                              });

        
        protected JLabel whichPointsLabel; 

        protected JRadioButton labelNone;
        protected JRadioButton labelAll;
        protected JRadioButton labelMostExtreme;
        protected JRadioButton labelPearsonResid2;

        protected JComboBox numExtremeValues;

         protected DefaultComboBoxModel numExtremeValuesChoices  = new DefaultComboBoxModel(
                				new String[] {       "1","2","3","4","5","6","7","8","9",
                                                               "10","11","12","13","14","15","16","17","18","19",
                                                               "20","21","22","23","24","25","26","27","28","29",
                                                               "30"
                                              });
         
        protected JLabel extremenessBasisLabel;
        protected JComboBox extremenessBasis1;
        protected JComboBox extremenessBasis2;
        protected JComboBox extremenessBasis3;
        protected JComboBox extremenessBasis4;
        protected JComboBox extremenessBasis5;
        protected DefaultComboBoxModel extremenessBasisChoices1  = new DefaultComboBoxModel(
                				new String[] { "|x – mean(x)|","|y – mean(y)|",
                                              });
        protected DefaultComboBoxModel extremenessBasisChoices2  = new DefaultComboBoxModel(
                				new String[] { "|x – mean(x)|","|y|"
                                              });
        protected DefaultComboBoxModel extremenessBasisChoices3  = new DefaultComboBoxModel(
                				new String[] { "|x – mean(x)|","|y – mean(y)|","|residual|"
                                              });
        protected DefaultComboBoxModel extremenessBasisChoices4  = new DefaultComboBoxModel(
                				new String[] { "|x – mean(x)|","|y – mean(y)|","|residual|", "Cook's distance"
                                              });
        protected DefaultComboBoxModel extremenessBasisChoices5  = new DefaultComboBoxModel(
                				new String[] { "|x – mean(x)|","|y|","Cook's distance"
                                              });
        
       
	public HanselPlotEdit(JFrame frame,GMModel mod,String parent,RModel rmod,int inputPanelDealtWith) {
		super(frame);
		initGUI();
                panelDealtWith = inputPanelDealtWith;
                theParentHere = parent;
		setModel(mod,rmod);
	}
	
	protected void initGUI() {
		try {
			{
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
                        
                        {
                        immediate = new JCheckBox();
                        getContentPane().add(immediate, new AnchorConstraint(890, 895, 38, 600, 
                                        AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                        AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                        immediate.setText("immediate apply");
                        immediate.setFont(font12);
                        immediate.setPreferredSize(new java.awt.Dimension(300, 18));

                        } 
                        
                        
                        okayCancel = new OkayCancelPanel(true,false,this);
				getContentPane().add(okayCancel, new AnchorConstraint(929, 17, 6, 504, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, 
						AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE));
				okayCancel.setPreferredSize(new java.awt.Dimension(240, 38));
				okayCancel.getCancelButton().setText("Apply");
				okayCancel.getResetButton().setText("Cancel");

                                 {
                                    plotControlsLabel = new JLabel();
                                    getContentPane().add(plotControlsLabel, new AnchorConstraint(10, 863, 38, 250, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                    AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    plotControlsLabel.setText("Plot Controls");
                                    plotControlsLabel.setFont(font16);
                                    plotControlsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                    plotControlsLabel.setPreferredSize(new java.awt.Dimension(150, 20));
                                  } 
                                
           				{
					middlePanel = new JPanel();
					BorderLayout middlePanelLayout = new BorderLayout();
					getContentPane().add(middlePanel, new AnchorConstraint(35, 989, 82, 0, AnchorConstraint.ANCHOR_ABS, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL));
					middlePanel.setLayout(middlePanelLayout);
					middlePanel.setPreferredSize(new java.awt.Dimension(800, 306));
					{
						tabs = new JTabbedPane ();
						middlePanel.add(tabs, BorderLayout.CENTER);
						tabs.setPreferredSize(new java.awt.Dimension(880, 501));
						{
							mainTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("Main", null, mainTab, null);
                                                        
                                                        
							mainTab.setLayout(generalTabLayout);
							mainTab.setPreferredSize(new java.awt.Dimension(895, 475));
                                                        
                                                                {
                                                                userInputMainTitle = new JPanel();
                                                                mainTab.add(userInputMainTitle, new AnchorConstraint(50, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                userInputMainTitle.setLayout(coefTestsManualLayout);
                                                                userInputMainTitle.setFont(font12);
                                                                userInputMainTitle.setBorder(BorderFactory.createTitledBorder("Plot Title"));
                                                                userInputMainTitle.setPreferredSize(new java.awt.Dimension(500, 52));
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
                                                                keyPositionLabel = new JLabel();
                                                                mainTab.add(keyPositionLabel, new AnchorConstraint(200, 520, 200, 100,
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                keyPositionLabel.setText("key position");
                                                                keyPositionLabel.setFont(font12);
                                                        	keyPositionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	keyPositionLabel.setPreferredSize(new java.awt.Dimension(80, 20));
                                                       } 
                                                        
                                                        
                                                      {
                                                        	keyPositionTypeTraditional = new JComboBox();
                                                                mainTab.add(keyPositionTypeTraditional, new AnchorConstraint(200, 520, 200, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                keyPositionTypeTraditional.setFont(font12);
                                                                keyPositionTypeTraditional.setModel(keyPositionChoicesTraditional);
                                                                keyPositionTypeTraditional.setPreferredSize(new java.awt.Dimension(90, 21));
                                                                keyPositionTypeTraditional.addActionListener(this);
                                                                keyPositionTypeTraditional.setVisible(false);
                                                        }
                                                      {
                                                        	keyPositionTypeLattice = new JComboBox();
                                                                mainTab.add(keyPositionTypeLattice, new AnchorConstraint(200, 520, 200, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                keyPositionTypeLattice.setFont(font12);
                                                                keyPositionTypeLattice.setModel(keyPositionChoicesLattice);
                                                                keyPositionTypeLattice.setPreferredSize(new java.awt.Dimension(90, 21));
                                                                keyPositionTypeLattice.addActionListener(this);
                                                                keyPositionTypeLattice.setVisible(false);
                                                        }
                                                      {
                                                                showTickMarks = new JCheckBox();
                                                                mainTab.add(showTickMarks, new AnchorConstraint(300, 895, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                showTickMarks.setText("Show tick marks");
                                                                showTickMarks.setFont(font12);
                                                                showTickMarks.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                showTickMarks.addActionListener(this);
                                                        }
                                                      
                                                       {
                                                                showGrid = new JCheckBox();
                                                                mainTab.add(showGrid, new AnchorConstraint(350, 895, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                showGrid.setText("Show grid");
                                                                showGrid.setFont(font12);
                                                                showGrid.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                showGrid.addActionListener(this);
                                                        } 
                                                      
			                               {
                                                                pointLabelsLabel = new JLabel();
                                                                mainTab.add(pointLabelsLabel, new AnchorConstraint(431, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelsLabel.setText("point labels:");
                                                                pointLabelsLabel.setFont(font12);
                                                        	pointLabelsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	pointLabelsLabel.setPreferredSize(new java.awt.Dimension(80, 20));
                                                       } 
                                                      {
                                                        	pointLabelsType = new JComboBox();
                                                                mainTab.add(pointLabelsType, new AnchorConstraint(431, 990, 38, 300, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelsType.setFont(font12);
                                                                
                                                                pointLabelsType.setModel(pointLabelsChoices);
                                                                pointLabelsType.setPreferredSize(new java.awt.Dimension(160, 21));
                                                                pointLabelsType.addActionListener(this);
                                                        }
                                                        {
                                                        	pointLabelsTypeRowNamesOnly = new JComboBox();
                                                                mainTab.add(pointLabelsTypeRowNamesOnly, new AnchorConstraint(431, 990, 38, 300, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelsTypeRowNamesOnly.setFont(font12);
                                                                
                                                                pointLabelsTypeRowNamesOnly.setModel(pointLabelsChoicesRowNamesOnly);
                                                                pointLabelsTypeRowNamesOnly.setPreferredSize(new java.awt.Dimension(160, 21));
                                                                pointLabelsTypeRowNamesOnly.addActionListener(this);
                                                                pointLabelsTypeRowNamesOnly.setVisible(false);
                                                        }
                                                      
                                                      {
                                                                whichPointsLabel = new JLabel();
                                                                mainTab.add(whichPointsLabel, new AnchorConstraint(481, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                whichPointsLabel.setText("which points to label");
                                                                whichPointsLabel.setFont(font12);
                                                        	whichPointsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	whichPointsLabel.setPreferredSize(new java.awt.Dimension(160, 20));
                                                       } 
                                                          
                                                        {
                                                            labelNone = new JRadioButton();
                                                            mainTab.add(labelNone , new AnchorConstraint(511, 863, 38, 100, 
                                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                            AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                            labelNone.setText("none");
                                                            labelNone.setFont(font12);
                                                            labelNone.addActionListener(this);
                                                        }
                                                        {
                                                            labelAll = new JRadioButton();
                                                            mainTab.add(labelAll  , new AnchorConstraint(551, 863, 38, 100, 
                                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                            AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                            labelAll .setText("all");
                                                            labelAll .setFont(font12);
                                                            labelAll.addActionListener(this);
                                                        }
                                                        {
                                                            labelPearsonResid2= new JRadioButton();
                                                            mainTab.add(labelPearsonResid2  , new AnchorConstraint(551, 863, 38, 300, 
                                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                            AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                            labelPearsonResid2.setText("|Pearson residuals|>2");
                                                            labelPearsonResid2.setFont(font12);
                                                            labelPearsonResid2.addActionListener(this);
                                                            labelPearsonResid2.setVisible(false);
                                                        }  
                                                        {
                                                            labelMostExtreme = new JRadioButton();
                                                            mainTab.add(labelMostExtreme  , new AnchorConstraint(591, 863, 38, 100, 
                                                                            AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                            AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                            labelMostExtreme.setText("n most extreme where n is:");
                                                            labelMostExtreme.setFont(font12);
                                                            labelMostExtreme.addActionListener(this);
                                                        }                                                      
                                                         	
                                                         {
                                                            ButtonGroup whichToLabelOptionButtons =new ButtonGroup();
                                                            whichToLabelOptionButtons.add(labelNone);
                                                            whichToLabelOptionButtons.add(labelAll);
                                                            whichToLabelOptionButtons.add(labelMostExtreme);
                                                            whichToLabelOptionButtons.add(labelPearsonResid2);
                                                         }
                                                         
                                                         {
                                                        	numExtremeValues = new JComboBox();
                                                                mainTab.add(numExtremeValues, new AnchorConstraint(601, 990, 38, 570, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                numExtremeValues.setFont(font12);
                                                                numExtremeValues.setModel(numExtremeValuesChoices);
                                                                numExtremeValues.setPreferredSize(new java.awt.Dimension(90, 21));
                                                                numExtremeValues.addActionListener(this);
                                                        }
                                                         
                                                         {
                                                                extremenessBasisLabel = new JLabel();
                                                                mainTab.add(extremenessBasisLabel, new AnchorConstraint(661, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasisLabel.setText("extremeness measure:");
                                                                extremenessBasisLabel.setFont(font12);
                                                        	extremenessBasisLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	extremenessBasisLabel.setPreferredSize(new java.awt.Dimension(230, 20));
                                                       } 
                                                         {
                                                        	extremenessBasis1 = new JComboBox();
                                                                mainTab.add(extremenessBasis1, new AnchorConstraint(651, 990, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasis1.setFont(font12);
                                                                extremenessBasis1.setModel(extremenessBasisChoices1);
                                                                extremenessBasis1.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                extremenessBasis1.addActionListener(this);
                                                        }
                                                        {
                                                        	extremenessBasis2 = new JComboBox();
                                                                mainTab.add(extremenessBasis2, new AnchorConstraint(651, 990, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasis2.setFont(font12);
                                                                extremenessBasis2.setModel(extremenessBasisChoices2);
                                                                extremenessBasis2.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                extremenessBasis2.addActionListener(this);
                                                        }
                                                        {
                                                        	extremenessBasis3 = new JComboBox();
                                                                mainTab.add(extremenessBasis3, new AnchorConstraint(651, 990, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasis3.setFont(font12);
                                                                extremenessBasis3.setModel(extremenessBasisChoices3);
                                                                extremenessBasis3.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                extremenessBasis3.addActionListener(this);
                                                        }
                                                        {
                                                        	extremenessBasis4 = new JComboBox();
                                                                mainTab.add(extremenessBasis4, new AnchorConstraint(651, 990, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasis4.setFont(font12);
                                                                extremenessBasis4.setModel(extremenessBasisChoices4);
                                                                extremenessBasis4.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                extremenessBasis4.addActionListener(this);
                                                        }
                                                        {
                                                        	extremenessBasis5 = new JComboBox();
                                                                mainTab.add(extremenessBasis5, new AnchorConstraint(651, 990, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                extremenessBasis5.setFont(font12);
                                                                extremenessBasis5.setModel(extremenessBasisChoices5);
                                                                extremenessBasis5.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                extremenessBasis5.addActionListener(this);
                                                        }
                                                        {
                                                                pointLabelTextSizeLabel = new JLabel();
                                                                mainTab.add(pointLabelTextSizeLabel, new AnchorConstraint(710, 990, 201, 120,
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelTextSizeLabel.setText("Size of point label text");
                                                                pointLabelTextSizeLabel.setFont(font12);
                                                        	pointLabelTextSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	pointLabelTextSizeLabel.setPreferredSize(new java.awt.Dimension(200, 20));
                                                       } 
                                                        {
                                                        	pointLabelTextSizeType = new JComboBox();
                                                                mainTab.add(pointLabelTextSizeType, new AnchorConstraint(760, 990, 38, 120, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelTextSizeType.setFont(font12);
                                                                pointLabelTextSizeType.setModel(pointLabelTextSizeChoices);
                                                                pointLabelTextSizeType.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                pointLabelTextSizeType.addActionListener(this);
                                                                pointLabelTextSizeType.setVisible(false);
                                                        }
                                                         {
                                                                pointLabelTextSizeLabel2 = new JLabel();
                                                                mainTab.add(pointLabelTextSizeLabel2, new AnchorConstraint(740, 990, 201, 120,
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                pointLabelTextSizeLabel2.setText("-equals to symbol size for line/points 1 in lattice- ");
                                                                pointLabelTextSizeLabel2.setFont(font12);
                                                        	pointLabelTextSizeLabel2.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	pointLabelTextSizeLabel2.setPreferredSize(new java.awt.Dimension(400, 20));
                                                                pointLabelTextSizeLabel2.setVisible(false);
                                                       } 
                                                        
                                                        
                                                        {
                                                                traditionalGraphicsButton = new JRadioButton();
                                                                mainTab.add(traditionalGraphicsButton , new AnchorConstraint(800, 990, 201, 120, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                                traditionalGraphicsButton.setText("traditional graphics");
                                                                traditionalGraphicsButton.setFont(font12);
                                                                traditionalGraphicsButton.addActionListener(this);
                                                        }
                                                        {
                                                                latticeButton = new JRadioButton();
                                                                mainTab.add(latticeButton , new AnchorConstraint(850, 990, 201, 120,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                                latticeButton.setText("lattice");
                                                                latticeButton.setFont(font12);
                                                                latticeButton.addActionListener(this);
                                                        }
                                                        {
                                                                ggplot2Button = new JRadioButton();
                                                                mainTab.add(ggplot2Button , new AnchorConstraint(900, 990, 201, 120,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));  
                                                                ggplot2Button.setText("ggplot2");
                                                                ggplot2Button.setFont(font12);
                                                                ggplot2Button.addActionListener(this);
                                                        }
                                                        
                                                        
                                                        ButtonGroup graphicSystemsButtons =new ButtonGroup();
                                                        graphicSystemsButtons.add(traditionalGraphicsButton);
                                                        graphicSystemsButtons.add(latticeButton);
                                                        graphicSystemsButtons.add( ggplot2Button);
                                                        
                                                        {
                                                                singleGraph = new JCheckBox();
                                                                mainTab.add(singleGraph, new AnchorConstraint(800, 990, 201, 500,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                singleGraph.setText("single graph");
                                                                singleGraph.setFont(font12);
                                                                singleGraph.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                singleGraph.addActionListener(this);
                                                        } 
                                                        
                                                        
                                                        {
                                                                ggplot2GrayTheme = new JCheckBox();
                                                                mainTab.add(ggplot2GrayTheme, new AnchorConstraint(900, 990, 201, 500,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                ggplot2GrayTheme.setText("ggplot2 gray theme");
                                                                ggplot2GrayTheme.setFont(font12);
                                                                ggplot2GrayTheme.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                ggplot2GrayTheme.addActionListener(this);
                                                        } 
                                                     
                                                        
                                                        
							
						}

                                                {
							xAxisTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("X axis", null, xAxisTab, null);
                                                        
                                                        
							xAxisTab.setLayout(generalTabLayout);
							xAxisTab.setPreferredSize(new java.awt.Dimension(695, 475));
								{
                                                                userInputxAxisTitle = new JPanel();
                                                                xAxisTab.add(userInputxAxisTitle, new AnchorConstraint(50, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                userInputxAxisTitle.setLayout(coefTestsManualLayout);
                                                                userInputxAxisTitle.setFont(font12);
                                                                userInputxAxisTitle.setBorder(BorderFactory.createTitledBorder("X-axis Title"));
                                                                userInputxAxisTitle.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }    
                                                                {
                                                                    userInputxAxisTitleScroller = new JScrollPane();
                                                                    userInputxAxisTitle.add(userInputxAxisTitleScroller, BorderLayout.CENTER);
                                                                        {
                                                                                userInputxAxisTitleText = new JTextArea();
                                                                                userInputxAxisTitleScroller.setViewportView(userInputxAxisTitleText);
                                                                                userInputxAxisTitleText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                userInputxAxisTitleText.setFont(font12);
                                                                                userInputxAxisTitleText.setEditable(true);
                                                                        }
                                                                }   
                                                            {
                                                                manualxAxisRange = new JCheckBox();
                                                                xAxisTab.add(manualxAxisRange, new AnchorConstraint(200, 895, 38, 10, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                manualxAxisRange.setText("manual x-axis range");
                                                                manualxAxisRange.setFont(font12);
                                                                manualxAxisRange.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                manualxAxisRange.addActionListener(this);
                                                            }
                                                             {
                                                                manualxAxisMinTitle = new JPanel();
                                                                xAxisTab.add(manualxAxisMinTitle, new AnchorConstraint(250, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                manualxAxisMinTitle.setLayout(coefTestsManualLayout);
                                                                manualxAxisMinTitle.setFont(font12);
                                                                manualxAxisMinTitle.setBorder(BorderFactory.createTitledBorder("minimum"));
                                                                manualxAxisMinTitle.setPreferredSize(new java.awt.Dimension(300, 52));
                                                                } 
                                                            
                                                            {
                                                                    manualxAxisMinScroller = new JScrollPane();
                                                                    manualxAxisMinTitle.add(manualxAxisMinScroller, BorderLayout.CENTER);
                                                                        {
                                                                                manualxAxisMinText = new JTextArea();
                                                                                manualxAxisMinScroller.setViewportView(manualxAxisMinText);
                                                                                manualxAxisMinText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                manualxAxisMinText.setFont(font12);
                                                                                manualxAxisMinText .setEditable(true);
                                                                        }
                                                                    manualxAxisMinText.setEnabled(false);
                                                                    manualxAxisMinText.setBackground(Color.lightGray);
                                                                }                                                     
                                                               
                                                               {
                                                                manualxAxisMaxTitle = new JPanel();
                                                                xAxisTab.add(manualxAxisMaxTitle, new AnchorConstraint(350, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                manualxAxisMaxTitle.setLayout(coefTestsManualLayout);
                                                                manualxAxisMaxTitle.setFont(font12);
                                                                manualxAxisMaxTitle.setBorder(BorderFactory.createTitledBorder("maximum"));
                                                                manualxAxisMaxTitle.setPreferredSize(new java.awt.Dimension(300, 52));
                                                                } 
                                                            
                                                            {
                                                                    manualxAxisMaxScroller = new JScrollPane();
                                                                    manualxAxisMaxTitle.add(manualxAxisMaxScroller, BorderLayout.CENTER);
                                                                        {
                                                                                manualxAxisMaxText = new JTextArea();
                                                                                manualxAxisMaxScroller.setViewportView(manualxAxisMaxText);
                                                                                manualxAxisMaxText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                manualxAxisMaxText.setFont(font12);
                                                                                manualxAxisMaxText .setEditable(true);
                                                                        }
                                                                    manualxAxisMaxText.setEnabled(false);
                                                                    manualxAxisMaxText.setBackground(Color.lightGray);
                                                                }   
                                                                                                                    
						}
                                                                                                 {
							yAxisTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("Y axis", null, yAxisTab, null);
                                                        
                                                        
							yAxisTab.setLayout(generalTabLayout);
							yAxisTab.setPreferredSize(new java.awt.Dimension(695, 475));
							    {
                                                                userInputyAxisTitle = new JPanel();
                                                                yAxisTab.add(userInputyAxisTitle, new AnchorConstraint(50, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                userInputyAxisTitle.setLayout(coefTestsManualLayout);
                                                                userInputyAxisTitle.setFont(font12);
                                                                userInputyAxisTitle.setBorder(BorderFactory.createTitledBorder("Y-axis Title"));
                                                                userInputyAxisTitle.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                } 
                                                            
                                                            {
                                                                    userInputyAxisTitleScroller = new JScrollPane();
                                                                    userInputyAxisTitle.add(userInputyAxisTitleScroller, BorderLayout.CENTER);
                                                                        {
                                                                                userInputyAxisTitleText = new JTextArea();
                                                                                userInputyAxisTitleScroller.setViewportView(userInputyAxisTitleText);
                                                                                userInputyAxisTitleText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                userInputyAxisTitleText.setFont(font12);
                                                                                userInputyAxisTitleText.setEditable(true);
                                                                        }
                                                                    userInputyAxisTitleScroller.setEnabled(false);
                                                                    
                                                                }
                                                            
                                                            {
                                                                manualyAxisRange = new JCheckBox();
                                                                yAxisTab.add(manualyAxisRange, new AnchorConstraint(200, 895, 38, 10, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                manualyAxisRange.setText("manual y-axis range");
                                                                manualyAxisRange.setFont(font12);
                                                                manualyAxisRange.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                manualyAxisRange.addActionListener(this);
                                                            }
                                                             {
                                                                manualyAxisMinTitle = new JPanel();
                                                                yAxisTab.add(manualyAxisMinTitle, new AnchorConstraint(250, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                manualyAxisMinTitle.setLayout(coefTestsManualLayout);
                                                                manualyAxisMinTitle.setFont(font12);
                                                                manualyAxisMinTitle.setBorder(BorderFactory.createTitledBorder("minimum"));
                                                                manualyAxisMinTitle.setPreferredSize(new java.awt.Dimension(300, 52));
                                                                } 
                                                            
                                                            {
                                                                    manualyAxisMinScroller = new JScrollPane();
                                                                    manualyAxisMinTitle.add(manualyAxisMinScroller, BorderLayout.CENTER);
                                                                        {
                                                                                manualyAxisMinText = new JTextArea();
                                                                                manualyAxisMinScroller.setViewportView( manualyAxisMinText);
                                                                                manualyAxisMinText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                manualyAxisMinText.setFont(font12);
                                                                                manualyAxisMinText .setEditable(true);
                                                                        }
                                                                    manualyAxisMinText.setEnabled(false);
                                                                    manualyAxisMinText.setBackground(Color.lightGray);
                                                                }
                                                              {
                                                                manualyAxisMaxTitle = new JPanel();
                                                                yAxisTab.add(manualyAxisMaxTitle, new AnchorConstraint(350, 520, 200, 100,
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                manualyAxisMaxTitle.setLayout(coefTestsManualLayout);
                                                                manualyAxisMaxTitle.setFont(font12);
                                                                manualyAxisMaxTitle.setBorder(BorderFactory.createTitledBorder("maximum"));
                                                                manualyAxisMaxTitle.setPreferredSize(new java.awt.Dimension(300, 52));
                                                                } 
                                                            
                                                            {
                                                                    manualyAxisMaxScroller = new JScrollPane();
                                                                    manualyAxisMaxTitle.add(manualyAxisMaxScroller, BorderLayout.CENTER);
                                                                        {
                                                                                manualyAxisMaxText = new JTextArea();
                                                                                manualyAxisMaxScroller.setViewportView(manualyAxisMaxText);
                                                                                manualyAxisMaxText.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                manualyAxisMaxText.setFont(font12);
                                                                                manualyAxisMaxText .setEditable(true);
                                                                        }
                                                                    manualyAxisMaxText.setEnabled(false);
                                                                    manualyAxisMaxText.setBackground(Color.lightGray);
                                                                }   
						}
      //  ****** linesPointsTab   *******                                                                                         
                                                {
							linesPointsTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("Lines/points", null, linesPointsTab, null);
                                                        
                                                        
							linesPointsTab.setLayout(generalTabLayout);
							linesPointsTab.setPreferredSize(new java.awt.Dimension(695, 475));
							{
                                                                linePointsLabel1 = new JLabel();
                                                                linesPointsTab.add(linePointsLabel1, new AnchorConstraint(25, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel1.setText("Line/points 1:");
                                                                linePointsLabel1.setFont(font12);
                                                        	linePointsLabel1.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel1.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                        {
                                                                plotTypeLabel1 = new JLabel();
                                                                linesPointsTab.add(plotTypeLabel1, new AnchorConstraint(50, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                plotTypeLabel1.setText("plot type");
                                                                plotTypeLabel1.setFont(font12);
                                                        	plotTypeLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	plotTypeLabel1.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	plotType1 = new JComboBox();
                                                                linesPointsTab.add(plotType1, new AnchorConstraint(85, 863, 38, 10, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                plotType1.setFont(font12);
                                                                plotType1.setModel(plotType1Choices);
                                                                plotType1.setPreferredSize(new java.awt.Dimension(120, 21));
                                                                plotType1.addActionListener(this);
                                                        }
                                                        
                                                          {
                                                                legendTitle1 = new JPanel();
                                                                linesPointsTab.add(legendTitle1, new AnchorConstraint(50, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle1.setLayout(coefTestsManualLayout);
                                                                legendTitle1.setFont(font12);
                                                                legendTitle1.setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle1.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller1 = new JScrollPane();
                                                                    legendTitle1.add(legendScroller1, BorderLayout.CENTER);
                                                                        {
                                                                                legendText1 = new JTextArea();
                                                                                legendScroller1.setViewportView(legendText1);
                                                                                legendText1.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText1.setFont(font12);
                                                                                legendText1.setEditable(true);
                                                                        }
                                                        }   
                                                        
                                                         {
                                                                symbolLabel1 = new JLabel();
                                                                linesPointsTab.add(symbolLabel1, new AnchorConstraint(165, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolLabel1.setText("plotting symbol");
                                                                symbolLabel1.setFont(font12);
                                                        	symbolLabel1.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	symbolLabel1.setPreferredSize(new java.awt.Dimension(250, 20));
                                                       } 
                                                      {
                                                        	symbolType1 = new JComboBox();
                                                                linesPointsTab.add(symbolType1, new AnchorConstraint(200, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolType1.setFont(font12);
                                                                symbolType1.setModel(linePoints1SymbolChoices);
                                                                symbolType1.setPreferredSize(new java.awt.Dimension(200, 21));
                                                                symbolType1.addActionListener(this);
                                                        }       
                                                        
                                                      {
                                                                symbolSizeLabel1 = new JLabel();
                                                                linesPointsTab.add(symbolSizeLabel1, new AnchorConstraint(165, 863, 38, 740, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolSizeLabel1.setText("symbol size");
                                                                symbolSizeLabel1.setFont(font12);
                                                        	symbolSizeLabel1.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	symbolSizeLabel1.setPreferredSize(new java.awt.Dimension(70, 20));
                                                       } 
                                                      {
                                                        	symbolSize1 = new JComboBox();
                                                                linesPointsTab.add(symbolSize1, new AnchorConstraint(200, 863, 38, 740, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolSize1.setFont(font12);
                                                                symbolSize1.setModel(symbolSize1Choices);
                                                                symbolSize1.setPreferredSize(new java.awt.Dimension(70, 21));
                                                                symbolSize1.addActionListener(this);
                                                        }
                                                       {
                                                                symbolSizeEqLineWidth = new JLabel();
                                                                linesPointsTab.add(symbolSizeEqLineWidth, new AnchorConstraint(200, 863, 38, 740, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolSizeEqLineWidth.setText("= line width");
                                                                symbolSizeEqLineWidth.setFont(font12);
                                                        	symbolSizeEqLineWidth.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	symbolSizeEqLineWidth.setPreferredSize(new java.awt.Dimension(70, 20));
                                                                symbolSizeEqLineWidth.setVisible(false);
                                                       } 
                                                      {
                                                                symbolSizeEqLineWidth2 = new JLabel();
                                                                linesPointsTab.add(symbolSizeEqLineWidth2, new AnchorConstraint(235, 863, 38, 740, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                symbolSizeEqLineWidth2.setText("for some symbols");
                                                                symbolSizeEqLineWidth2.setFont(font12);
                                                        	symbolSizeEqLineWidth2.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	symbolSizeEqLineWidth2.setPreferredSize(new java.awt.Dimension(120, 20));
                                                                symbolSizeEqLineWidth2.setVisible(false);
                                                       } 
                                                        {
                                                                lineTypeLabel1 = new JLabel();
                                                                linesPointsTab.add(lineTypeLabel1, new AnchorConstraint(265, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel1.setText("line type");
                                                                lineTypeLabel1.setFont(font12);
                                                        	lineTypeLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel1.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType1 = new JComboBox();
                                                                linesPointsTab.add(lineType1, new AnchorConstraint(300, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType1.setFont(font12);
                                                                lineType1.setModel(lineType1Choices);
                                                                lineType1.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType1.addActionListener(this);
                                                        }
                                                        
                                                        
                                                        
                                                        {
                                                                lineWidthLabel1 = new JLabel();
                                                                linesPointsTab.add(lineWidthLabel1, new AnchorConstraint(265, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel1.setText("line width");
                                                                lineWidthLabel1.setFont(font12);
                                                        	lineWidthLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel1.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth1 = new JComboBox();
                                                                linesPointsTab.add(lineWidth1, new AnchorConstraint(300, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth1.setFont(font12);
                                                                lineWidth1.setModel(lineWidth1Choices);
                                                                lineWidth1.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth1.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel1 = new JLabel();
                                                                linesPointsTab.add(linePointsColorLabel1, new AnchorConstraint(265, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel1.setText("color");
                                                                linePointsColorLabel1.setFont(font12);
                                                        	linePointsColorLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel1.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType1 = new JComboBox();
                                                                linesPointsTab.add(linePointsColorType1, new AnchorConstraint(300, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType1.setFont(font12);
                                                                linePointsColorType1.setModel(linePointsColor1Choices);
                                                                linePointsColorType1.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType1.addActionListener(this);
                                                      }
                                                     
                                                      
                                                      {
                                                                separator1 = new JLabel();
                                                                linesPointsTab.add(separator1, new AnchorConstraint(350, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                separator1.setText("---------------------------------------------------------------------------");
                                                                separator1.setFont(font12);
                                                        	separator1.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	separator1.setPreferredSize(new java.awt.Dimension(300, 20));
                                                       } 
                                                      
                                                      	{
                                                                linePointsLabel2 = new JLabel();
                                                                linesPointsTab.add(linePointsLabel2, new AnchorConstraint(375, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel2.setText("Line 2:");
                                                                linePointsLabel2.setFont(font12);
                                                        	linePointsLabel2.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel2.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                          {
                                                                legendTitle2 = new JPanel();
                                                                linesPointsTab.add(legendTitle2, new AnchorConstraint(400, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle2.setLayout(coefTestsManualLayout);
                                                                legendTitle2.setFont(font12);
                                                                legendTitle2.setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle2.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller2 = new JScrollPane();
                                                                    legendTitle2.add(legendScroller2, BorderLayout.CENTER);
                                                                        {
                                                                                legendText2 = new JTextArea();
                                                                                legendScroller2.setViewportView(legendText2);
                                                                                legendText2.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText2.setFont(font12);
                                                                                legendText2.setEditable(true);
                                                                        }
                                                        }   
                                              {
                                                                lineTypeLabel2 = new JLabel();
                                                                linesPointsTab.add(lineTypeLabel2, new AnchorConstraint(515, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel2.setText("line type");
                                                                lineTypeLabel2.setFont(font12);
                                                        	lineTypeLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel2.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType2 = new JComboBox();
                                                                linesPointsTab.add(lineType2, new AnchorConstraint(550, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType2.setFont(font12);
                                                                lineType2.setModel(lineType2Choices);
                                                                lineType2.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType2.addActionListener(this);
                                                        }
                                                          {
                                                                lineWidthLabel2 = new JLabel();
                                                                linesPointsTab.add(lineWidthLabel2, new AnchorConstraint(515, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel2.setText("line width");
                                                                lineWidthLabel2.setFont(font12);
                                                        	lineWidthLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel2.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth2 = new JComboBox();
                                                                linesPointsTab.add(lineWidth2, new AnchorConstraint(550, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth2.setFont(font12);
                                                                lineWidth2.setModel(lineWidth2Choices);
                                                                lineWidth2.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth2.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel2 = new JLabel();
                                                                linesPointsTab.add(linePointsColorLabel2, new AnchorConstraint(515, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel2.setText("color");
                                                                linePointsColorLabel2.setFont(font12);
                                                        	linePointsColorLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel2.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType2 = new JComboBox();
                                                                linesPointsTab.add(linePointsColorType2, new AnchorConstraint(550, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType2.setFont(font12);
                                                                linePointsColorType2.setModel(linePointsColor2Choices);
                                                                linePointsColorType2.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType2.addActionListener(this);
                                                        }
                                                        {
                                                                includeConfIntvl95 = new JCheckBox();
                                                                linesPointsTab.add(includeConfIntvl95, new AnchorConstraint(650, 895, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                includeConfIntvl95.setText("Include 95% Confidence Interval");
                                                                includeConfIntvl95.setFont(font12);
                                                                includeConfIntvl95.setPreferredSize(new java.awt.Dimension(300, 18));
                                                                includeConfIntvl95.addActionListener(this);
                                                        }

                                                        
                                                        {
                                                                separator2 = new JLabel();
                                                                linesPointsTab.add(separator2, new AnchorConstraint(700, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                separator2.setText("---------------------------------------------------------------------------");
                                                                separator2.setFont(font12);
                                                        	separator2.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	separator2.setPreferredSize(new java.awt.Dimension(300, 20));
                                                       } 
                                                      
                                                      	{
                                                                linePointsLabel3 = new JLabel();
                                                                linesPointsTab.add(linePointsLabel3, new AnchorConstraint(725, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel3.setText("Line 3:");
                                                                linePointsLabel3.setFont(font12);
                                                        	linePointsLabel3.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel3.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                          {
                                                                legendTitle3 = new JPanel();
                                                                linesPointsTab.add(legendTitle3 , new AnchorConstraint(750, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle3 .setLayout(coefTestsManualLayout);
                                                                legendTitle3 .setFont(font12);
                                                                legendTitle3 .setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle3 .setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller3 = new JScrollPane();
                                                                    legendTitle3.add(legendScroller3 , BorderLayout.CENTER);
                                                                        {
                                                                                legendText3 = new JTextArea();
                                                                                legendScroller3.setViewportView(legendText3);
                                                                                legendText3.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText3.setFont(font12);
                                                                                legendText3.setEditable(true);
                                                                        }
                                                        }   
                                              {
                                                                lineTypeLabel3 = new JLabel();
                                                                linesPointsTab.add(lineTypeLabel3, new AnchorConstraint(865, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel3.setText("line type");
                                                                lineTypeLabel3.setFont(font12);
                                                        	lineTypeLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel3.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType3 = new JComboBox();
                                                                linesPointsTab.add(lineType3, new AnchorConstraint(900, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType3.setFont(font12);
                                                                lineType3.setModel(lineType3Choices);
                                                                lineType3.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType3.addActionListener(this);
                                                        }
                                                          {
                                                                lineWidthLabel3 = new JLabel();
                                                                linesPointsTab.add(lineWidthLabel3, new AnchorConstraint(865, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel3.setText("line width");
                                                                lineWidthLabel3.setFont(font12);
                                                        	lineWidthLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel3.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth3 = new JComboBox();
                                                                linesPointsTab.add(lineWidth3, new AnchorConstraint(900, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth3.setFont(font12);
                                                                lineWidth3.setModel(lineWidth3Choices);
                                                                lineWidth3.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth3.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel3 = new JLabel();
                                                                linesPointsTab.add(linePointsColorLabel3, new AnchorConstraint(865, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel3.setText("color");
                                                                linePointsColorLabel3.setFont(font12);
                                                        	linePointsColorLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel3.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType3 = new JComboBox();
                                                                linesPointsTab.add(linePointsColorType3, new AnchorConstraint(900, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType3.setFont(font12);
                                                                linePointsColorType3.setModel(linePointsColor3Choices);
                                                                linePointsColorType3.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType3.addActionListener(this);
                                                        }     
                                                        
						}
        //********* morelinesPointsTab *************                                                                                         
                                                {
							moreLinesPointsTab = new JPanel();
							AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("More lines", null, moreLinesPointsTab , null);
                                                        
                                                        
							moreLinesPointsTab.setLayout(generalTabLayout);
							moreLinesPointsTab.setPreferredSize(new java.awt.Dimension(695, 475));
							{
                                                                linePointsLabel4 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsLabel4, new AnchorConstraint(25, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel4.setText("Line 4:");
                                                                linePointsLabel4.setFont(font12);
                                                        	linePointsLabel4.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel4.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                        
                                                          {
                                                                legendTitle4 = new JPanel();
                                                                moreLinesPointsTab.add(legendTitle4, new AnchorConstraint(50, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle4.setLayout(coefTestsManualLayout);
                                                                legendTitle4.setFont(font12);
                                                                legendTitle4.setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle4.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller4 = new JScrollPane();
                                                                    legendTitle4.add(legendScroller4, BorderLayout.CENTER);
                                                                        {
                                                                                legendText4 = new JTextArea();
                                                                                legendScroller4.setViewportView(legendText4);
                                                                                legendText4.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText4.setFont(font12);
                                                                                legendText4.setEditable(true);
                                                                        }
                                                        }   

                                                        {
                                                                lineTypeLabel4 = new JLabel();
                                                                moreLinesPointsTab.add(lineTypeLabel4, new AnchorConstraint(265, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel4.setText("line type");
                                                                lineTypeLabel4.setFont(font12);
                                                        	lineTypeLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel4.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType4 = new JComboBox();
                                                                moreLinesPointsTab.add(lineType4, new AnchorConstraint(300, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType4.setFont(font12);
                                                                lineType4.setModel(lineType4Choices);
                                                                lineType4.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType4.addActionListener(this);
                                                        }
                                                        
                                                        
                                                        
                                                        {
                                                                lineWidthLabel4 = new JLabel();
                                                                moreLinesPointsTab.add(lineWidthLabel4, new AnchorConstraint(265, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel4.setText("line width");
                                                                lineWidthLabel4.setFont(font12);
                                                        	lineWidthLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel4.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth4 = new JComboBox();
                                                                moreLinesPointsTab.add(lineWidth4, new AnchorConstraint(300, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth4.setFont(font12);
                                                                lineWidth4.setModel(lineWidth4Choices);
                                                                lineWidth4.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth4.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel4 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsColorLabel4, new AnchorConstraint(265, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel4.setText("color");
                                                                linePointsColorLabel4.setFont(font12);
                                                        	linePointsColorLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel4.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType4 = new JComboBox();
                                                                moreLinesPointsTab.add(linePointsColorType4, new AnchorConstraint(300, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType4.setFont(font12);
                                                                linePointsColorType4.setModel(linePointsColor4Choices);
                                                                linePointsColorType4.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType4.addActionListener(this);
                                                      }
                                                      
                                                      
                                                      {
                                                                separator4 = new JLabel();
                                                                moreLinesPointsTab.add(separator4, new AnchorConstraint(350, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                separator4.setText("---------------------------------------------------------------------------");
                                                                separator4.setFont(font12);
                                                        	separator4.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	separator4.setPreferredSize(new java.awt.Dimension(300, 20));
                                                       } 
                                                      
                                                      	{
                                                                linePointsLabel5 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsLabel5, new AnchorConstraint(375, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel5.setText("Line 5:");
                                                                linePointsLabel5.setFont(font12);
                                                        	linePointsLabel5.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel5.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                          {
                                                                legendTitle5 = new JPanel();
                                                                moreLinesPointsTab.add(legendTitle5, new AnchorConstraint(400, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle5.setLayout(coefTestsManualLayout);
                                                                legendTitle5.setFont(font12);
                                                                legendTitle5.setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle5.setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller5 = new JScrollPane();
                                                                    legendTitle5.add(legendScroller5, BorderLayout.CENTER);
                                                                        {
                                                                                legendText5 = new JTextArea();
                                                                                legendScroller5.setViewportView(legendText5);
                                                                                legendText5.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText5.setFont(font12);
                                                                                legendText5.setEditable(true);
                                                                        }
                                                        }   
                                              {
                                                                lineTypeLabel5 = new JLabel();
                                                                moreLinesPointsTab.add(lineTypeLabel5, new AnchorConstraint(515, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel5.setText("line type");
                                                                lineTypeLabel5.setFont(font12);
                                                        	lineTypeLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel5.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType5 = new JComboBox();
                                                                moreLinesPointsTab.add(lineType5, new AnchorConstraint(550, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType5.setFont(font12);
                                                                lineType5.setModel(lineType5Choices);
                                                                lineType5.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType5.addActionListener(this);
                                                        }
                                                          {
                                                                lineWidthLabel5 = new JLabel();
                                                                moreLinesPointsTab.add(lineWidthLabel5, new AnchorConstraint(515, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel5.setText("line width");
                                                                lineWidthLabel5.setFont(font12);
                                                        	lineWidthLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel5.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth5 = new JComboBox();
                                                                moreLinesPointsTab.add(lineWidth5, new AnchorConstraint(550, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth5.setFont(font12);
                                                                lineWidth5.setModel(lineWidth5Choices);
                                                                lineWidth5.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth5.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel5 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsColorLabel5, new AnchorConstraint(515, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel5.setText("color");
                                                                linePointsColorLabel5.setFont(font12);
                                                        	linePointsColorLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel5.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType5 = new JComboBox();
                                                                moreLinesPointsTab.add(linePointsColorType5, new AnchorConstraint(550, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType5.setFont(font12);
                                                                linePointsColorType5.setModel(linePointsColor5Choices);
                                                                linePointsColorType5.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType5.addActionListener(this);
                                                        }

                                                        
                                                        {
                                                                separator5 = new JLabel();
                                                                moreLinesPointsTab.add(separator5, new AnchorConstraint(700, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                separator5.setText("---------------------------------------------------------------------------");
                                                                separator5.setFont(font12);
                                                        	separator5.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	separator5.setPreferredSize(new java.awt.Dimension(300, 20));
                                                       } 
                                                      
                                                      	{
                                                                linePointsLabel6 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsLabel6, new AnchorConstraint(725, 863, 38, 10, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsLabel6.setText("Line 6:");
                                                                linePointsLabel6.setFont(font12);
                                                        	linePointsLabel6.setHorizontalAlignment(SwingConstants.LEFT);
                                                        	linePointsLabel6.setPreferredSize(new java.awt.Dimension(120, 20));
                                                       } 
                                                        
                                                          {
                                                                legendTitle6 = new JPanel();
                                                                moreLinesPointsTab.add(legendTitle6 , new AnchorConstraint(750, 863, 38, 400, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                BorderLayout coefTestsManualLayout = new BorderLayout();
                                                                legendTitle6 .setLayout(coefTestsManualLayout);
                                                                legendTitle6 .setFont(font12);
                                                                legendTitle6 .setBorder(BorderFactory.createTitledBorder("Legend"));
                                                                legendTitle6 .setPreferredSize(new java.awt.Dimension(500, 52));
                                                                }                                           
                                                                {
                                                                    legendScroller6 = new JScrollPane();
                                                                    legendTitle6.add(legendScroller6 , BorderLayout.CENTER);
                                                                        {
                                                                                legendText6 = new JTextArea();
                                                                                legendScroller6.setViewportView(legendText6);
                                                                                legendText6.setFont(new java.awt.Font("Monospaced",0,11));
                                                                                legendText6.setFont(font12);
                                                                                legendText6.setEditable(true);
                                                                        }
                                                        }   
                                              {
                                                                lineTypeLabel6 = new JLabel();
                                                                moreLinesPointsTab.add(lineTypeLabel6, new AnchorConstraint(865, 863, 38, 100, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineTypeLabel6.setText("line type");
                                                                lineTypeLabel6.setFont(font12);
                                                        	lineTypeLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineTypeLabel6.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineType6 = new JComboBox();
                                                                moreLinesPointsTab.add(lineType6, new AnchorConstraint(900, 990, 38, 100, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineType6.setFont(font12);
                                                                lineType6.setModel(lineType6Choices);
                                                                lineType6.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                lineType6.addActionListener(this);
                                                        }
                                                          {
                                                                lineWidthLabel6 = new JLabel();
                                                                moreLinesPointsTab.add(lineWidthLabel6, new AnchorConstraint(865, 863, 38, 350, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidthLabel6.setText("line width");
                                                                lineWidthLabel6.setFont(font12);
                                                        	lineWidthLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	lineWidthLabel6.setPreferredSize(new java.awt.Dimension(50, 20));
                                                       } 
                                                      {
                                                        	lineWidth6 = new JComboBox();
                                                                moreLinesPointsTab.add(lineWidth6, new AnchorConstraint(900, 990, 38, 350, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                lineWidth6.setFont(font12);
                                                                lineWidth6.setModel(lineWidth6Choices);
                                                                lineWidth6.setPreferredSize(new java.awt.Dimension(60, 21));
                                                                lineWidth6.addActionListener(this);
                                                        }
                                                      
                                                         {
                                                                linePointsColorLabel6 = new JLabel();
                                                                moreLinesPointsTab.add(linePointsColorLabel6, new AnchorConstraint(865, 863, 38, 550, 
                                                                		AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                        	AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorLabel6.setText("color");
                                                                linePointsColorLabel6.setFont(font12);
                                                        	linePointsColorLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
                                                        	linePointsColorLabel6.setPreferredSize(new java.awt.Dimension(25, 20));
                                                       } 
                                                      {
                                                        	linePointsColorType6 = new JComboBox();
                                                                moreLinesPointsTab.add(linePointsColorType6, new AnchorConstraint(900, 990, 38, 550, 
                                                                                AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                                                AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                                                linePointsColorType6.setFont(font12);
                                                                linePointsColorType6.setModel(linePointsColor6Choices);
                                                                linePointsColorType6.setPreferredSize(new java.awt.Dimension(80, 21));
                                                                linePointsColorType6.addActionListener(this);
                                                        }     
                                                        
						}     

					}
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
                   ((GMExplorer)parent).regressionPlotsButton.setEnabled(true);
                   ((GMExplorer)parent).accuracyAndROCcurvesButton.setEnabled(true);
                   ((GMExplorer)parent).cointRelButton.setEnabled(true);
                   ((GMExplorer)parent).cointRelsButton.setEnabled(true);
                   ((GMExplorer)parent).cointRelSCButton.setEnabled(true);
                   ((GMExplorer)parent).plotsvsTimeButton.setEnabled(true);
                   ((GMExplorer)parent).plotsvsTimeExtendedButton.setEnabled(true);                                           
                   ((GMExplorer)parent).residualsVsFitButton.setEnabled(true);
                   ((GMExplorer)parent).plotsvsObsButton.setEnabled(true);
                   ((GMExplorer)parent).otherPlotsButton.setEnabled(true);
                   ((GMExplorer)parent).editButton.setEnabled(editButtonCameEnabled);           
                   ((GMExplorer)parent).idLabelButton.setEnabled(((GMExplorer)parent).idLAbelButtonEnabled&!ggplot2Button.isSelected());
                   
                   ((GMExplorer)parent).iplotButton.setEnabled(iplotButtonCameEnabled);      
                } else if (theParentHere.equals("EFEUTSExplorer")) {
                    ((NMTimeSExplorer)parent).plotvsTimeButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).distributionButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).correlogramButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).plotvsLagsButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).structuralBreaksButton.setEnabled(true);
                    ((NMTimeSExplorer)parent).filtertype.setEnabled(true);
                    ((NMTimeSExplorer)parent).resultsAreaViewType.setEnabled(true);
                    ((NMTimeSExplorer)parent).editButton.setEnabled(true); 
                }
        }
	public void updateModel(){
                        if (traditionalGraphicsButton.isSelected()) {
                            model.plotControl.graphicSystem[panelDealtWith] =  traditionalGraphicsButton.getText();
                            model.plotControl.keyPosition[panelDealtWith] = keyPositionChoicesTraditional.getSelectedItem().toString();
                        }
                        else if (latticeButton.isSelected()) {
                            
                            model.plotControl.graphicSystem[panelDealtWith] =  latticeButton.getText();
                            model.plotControl.keyPosition[panelDealtWith] = 
                                    keyPositionChoicesLattice.getSelectedItem().toString().
                                      replace("topleft","top").replace("topright","top").replace("bottomleft","bottom").replace("bottomright","bottom");            
                        }
                        else if (ggplot2Button.isSelected()) 
                            model.plotControl.graphicSystem[panelDealtWith] =  ggplot2Button.getText();
                        
                        
                        model.plotControl.mainTitle[panelDealtWith] = userInputMainTitleText.getText();
                        
                        model.plotControl.showTickMarks[panelDealtWith] = showTickMarks.isSelected();
                        model.plotControl.showGrid[panelDealtWith] = showGrid.isSelected();
                        model.plotControl.singleGraphMulti[panelDealtWith] = singleGraph.isSelected();
                        model.plotControl.ggplot2GrayTheme[panelDealtWith] = ggplot2GrayTheme.isSelected();
                        model.plotControl.pointLabelTextSize[panelDealtWith] = pointLabelTextSizeChoices.getSelectedItem().toString();
                        model.plotControl.pointLabels[panelDealtWith] = pointLabelsChoices.getSelectedItem().toString();
                        
                        if (model.plotControl.traditionalPlotBeginning2[panelDealtWith].equals("in avPlots")||
                                model.plotControl.traditionalPlotBeginning2[panelDealtWith].equals("in crPlots"))
                           model.plotControl.pointLabels[panelDealtWith] = "row names";
                        else 
                            model.plotControl.pointLabels[panelDealtWith] = pointLabelsChoices.getSelectedItem().toString(); 
                        
                        if (labelNone.isSelected())
                            model.plotControl.whichPointsToLabel[panelDealtWith] = "none";
                        else if (labelAll.isSelected())
                            model.plotControl.whichPointsToLabel[panelDealtWith] = "all";
                        else if (labelMostExtreme.isSelected())
                            model.plotControl.whichPointsToLabel[panelDealtWith] = "mostExtrm";
                        else if (labelPearsonResid2.isSelected())
                            model.plotControl.whichPointsToLabel[panelDealtWith] = "Pearson2";

                        model.plotControl.numExtremeValues[panelDealtWith] = numExtremeValuesChoices.getSelectedItem().toString();
                        
                        
                    if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("1")){
                        model.plotControl.extremenessBasis[panelDealtWith] = extremenessBasisChoices1.getSelectedItem().toString(); 
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("2")){
                        model.plotControl.extremenessBasis[panelDealtWith] = extremenessBasisChoices2.getSelectedItem().toString(); 
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("3")){
                        model.plotControl.extremenessBasis[panelDealtWith] = extremenessBasisChoices3.getSelectedItem().toString(); 
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("4")){
                        model.plotControl.extremenessBasis[panelDealtWith] = extremenessBasisChoices4.getSelectedItem().toString(); 
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("5")){
                        model.plotControl.extremenessBasis[panelDealtWith] = extremenessBasisChoices5.getSelectedItem().toString(); 
                    }
                        
                                                
                        model.plotControl.xAxisTitle[panelDealtWith] = userInputxAxisTitleText.getText();
                        model.plotControl.manualxAxisRange[panelDealtWith] = manualxAxisRange.isSelected();
                        model.plotControl.manualxAxisMinText[panelDealtWith] =  manualxAxisMinText.getText();
                        model.plotControl.manualxAxisMaxText[panelDealtWith] = manualxAxisMaxText.getText();
                        
                        model.plotControl.yAxisTitle[panelDealtWith] = userInputyAxisTitleText.getText();
                        model.plotControl.manualyAxisRange[panelDealtWith] = manualyAxisRange.isSelected();
                        model.plotControl.manualyAxisMinText[panelDealtWith] =  manualyAxisMinText.getText();
                        model.plotControl.manualyAxisMaxText[panelDealtWith] = manualyAxisMaxText.getText();
                        
                        model.plotControl.legendText1[panelDealtWith] = legendText1.getText();
                        
                        model.plotControl.linePoints1Symbol[panelDealtWith] = linePoints1SymbolChoices.getSelectedItem().toString();
                        model.plotControl.symbolSize1[panelDealtWith] = symbolSize1Choices.getSelectedItem().toString();
                        
                        model.plotControl.lineType1[panelDealtWith] = lineType1Choices.getSelectedItem().toString();
                        model.plotControl.linePoints1linewidth[panelDealtWith] = lineWidth1Choices.getSelectedItem().toString();
                        model.plotControl.linePoints1color[panelDealtWith] = linePointsColor1Choices.getSelectedItem().toString();
                        
                        
                        model.plotControl.legendText2[panelDealtWith] = legendText2.getText();
                        model.plotControl.lineType2[panelDealtWith] = lineType2Choices.getSelectedItem().toString();
                        model.plotControl.linePoints2linewidth[panelDealtWith] = lineWidth2Choices.getSelectedItem().toString();
                        model.plotControl.linePoints2color[panelDealtWith] = linePointsColor2Choices.getSelectedItem().toString();
                        model.plotControl.confIntvl95[panelDealtWith] = includeConfIntvl95.isSelected();
                        
                        model.plotControl.legendText3[panelDealtWith] = legendText3.getText();
                        model.plotControl.lineType3[panelDealtWith] = lineType3Choices.getSelectedItem().toString();
                        model.plotControl.linePoints3linewidth[panelDealtWith] = lineWidth3Choices.getSelectedItem().toString();
                        model.plotControl.linePoints3color[panelDealtWith] = linePointsColor3Choices.getSelectedItem().toString();
                        
                        model.plotControl.legendText4[panelDealtWith] = legendText4.getText();
                        model.plotControl.lineType4[panelDealtWith] = lineType4Choices.getSelectedItem().toString();
                        model.plotControl.linePoints4linewidth[panelDealtWith] = lineWidth4Choices.getSelectedItem().toString();
                        model.plotControl.linePoints4color[panelDealtWith] = linePointsColor4Choices.getSelectedItem().toString();
                        
                        model.plotControl.legendText5[panelDealtWith] = legendText5.getText();
                        model.plotControl.lineType5[panelDealtWith] = lineType5Choices.getSelectedItem().toString();
                        model.plotControl.linePoints5linewidth[panelDealtWith] = lineWidth5Choices.getSelectedItem().toString();
                        model.plotControl.linePoints5color[panelDealtWith] = linePointsColor5Choices.getSelectedItem().toString();
                        
                        model.plotControl.legendText6[panelDealtWith] = legendText6.getText();
                        model.plotControl.lineType6[panelDealtWith] = lineType6Choices.getSelectedItem().toString();
                        model.plotControl.linePoints6linewidth[panelDealtWith] = lineWidth6Choices.getSelectedItem().toString();
                        model.plotControl.linePoints6color[panelDealtWith] = linePointsColor6Choices.getSelectedItem().toString();
	}
        
       
        
	public void setModel(GMModel mod,RModel rmod){
                model = mod;  
                if (theParentHere.equals("GMExplorer")){
                    existsTS = model.existsTS;
                   ((GMExplorer)parent).diagnosticsButton.setEnabled(false);
                   ((GMExplorer)parent).regressionFunctionPlotButton.setEnabled(false);
                   ((GMExplorer)parent).partialregressionPlotButton.setEnabled(false);         
                   ((GMExplorer)parent).regressionPlotsButton.setEnabled(false);
                   ((GMExplorer)parent).accuracyAndROCcurvesButton.setEnabled(false);
                   ((GMExplorer)parent).cointRelButton.setEnabled(false);
                   ((GMExplorer)parent).cointRelsButton.setEnabled(false);
                   ((GMExplorer)parent).cointRelSCButton.setEnabled(false);
                   ((GMExplorer)parent).plotsvsTimeButton.setEnabled(false);
                   ((GMExplorer)parent).plotsvsTimeExtendedButton.setEnabled(false);   
                   ((GMExplorer)parent).residualsVsFitButton.setEnabled(false);
                   ((GMExplorer)parent).plotsvsObsButton.setEnabled(false);
                   ((GMExplorer)parent).otherPlotsButton.setEnabled(false);
                                   
                   editButtonCameEnabled = ((GMExplorer)parent).editButton.isEnabled(); 
                   interactiveButtonCameEnabled = ((GMExplorer)parent).idLabelButton.isEnabled(); 
                   iplotButtonCameEnabled = ((GMExplorer)parent).iplotButton.isEnabled(); 
                   ((GMExplorer)parent).editButton.setEnabled(false); 
                   ((GMExplorer)parent).idLabelButton.setEnabled(false);
                   ((GMExplorer)parent).iplotButton.setEnabled(false);
                   
                   

                } else if (theParentHere.equals("EFEUTSExplorer")) {
                     existsTS = ((NMTimeSExplorer)parent).existsTS;
                    ((NMTimeSExplorer)parent).plotvsTimeButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).distributionButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).correlogramButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).plotvsLagsButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).structuralBreaksButton.setEnabled(false);
                    ((NMTimeSExplorer)parent).filtertype.setEnabled(false);
                    ((NMTimeSExplorer)parent).resultsAreaViewType.setEnabled(false);
                    ((NMTimeSExplorer)parent).editButton.setEnabled(false); 
                }
               traditionalGraphicsButton.setEnabled(model.plotControl.traditionalGraphicsAvailable[panelDealtWith]);
               latticeButton.setEnabled(model.plotControl.latticeGraphicsAvailable[panelDealtWith]);
               ggplot2Button.setEnabled(model.plotControl.ggplot2GraphicsAvailable[panelDealtWith]);            
                userInputMainTitle.setVisible(true);
               /* if (panelDealtWith==3) {*/
                  if (model.plotControl.graphicSystem[panelDealtWith].equals("traditional graphics")) {
                         traditionalGraphicsButton.setSelected(true);
                         pointLabelTextSizeType.setVisible(true);
                  }
                  else if (model.plotControl.graphicSystem[panelDealtWith].equals("lattice")){
                         latticeButton.setSelected(true);
                         pointLabelTextSizeLabel2.setVisible(true);
                  }
                  else if (model.plotControl.graphicSystem[panelDealtWith].equals("ggplot2")) {
                      ggplot2Button.setSelected(true);
                      pointLabelTextSizeType.setVisible(true);
                  }
                userInputMainTitleText.setText(model.plotControl.mainTitle[panelDealtWith]);
                                
                if (model.plotControl.traditionalPlotBeginning2[panelDealtWith].equals("in avPlots")||
                        model.plotControl.traditionalPlotBeginning2[panelDealtWith].equals("in crPlots")){
                    pointLabelsType.setVisible(false);
                    pointLabelsTypeRowNamesOnly.setVisible(true);
                }
                                 
                if (model.plotControl.mainTitle[panelDealtWith].equals("_na_")) 
                     userInputMainTitleText.setEnabled(false);
                userInputMainTitleText.setBackground((userInputMainTitleText.isEnabled())?Color.WHITE:Color.lightGray);
                if (traditionalGraphicsButton.isSelected()) {
                    keyPositionTypeTraditional.setVisible(true);
                    keyPositionChoicesTraditional.setSelectedItem(model.plotControl.keyPosition[panelDealtWith]);
                }
                else if (latticeButton.isSelected()){
                    keyPositionTypeLattice.setVisible(true);
                    keyPositionChoicesLattice.setSelectedItem(model.plotControl.keyPosition[panelDealtWith]);
                }
                pointLabelTextSizeChoices.setSelectedItem(model.plotControl.pointLabelTextSize[panelDealtWith]);    
                pointLabelsChoices.setSelectedItem(model.plotControl.pointLabels[panelDealtWith]);
                
                if (model.plotControl.whichPointsToLabel[panelDealtWith].equals("")){
                    labelNone.setEnabled(false); labelAll.setEnabled(false); 
                    labelMostExtreme.setEnabled(false); labelPearsonResid2.setEnabled(false);
                    numExtremeValues.setEnabled(false);
                    extremenessBasis1.setEnabled(false);extremenessBasis2.setEnabled(false);extremenessBasis3.setEnabled(false);
                    extremenessBasis4.setEnabled(false);extremenessBasis5.setEnabled(false);
                } else {
                    if (model.plotControl.whichPointsToLabel[panelDealtWith].equals("none")){
                       labelNone.setSelected(true); labelAll.setSelected(false);
                       labelMostExtreme.setSelected(false);labelPearsonResid2.setSelected(false);
                    } else if (model.plotControl.whichPointsToLabel[panelDealtWith].equals("all")) {
                       labelNone.setSelected(false); labelAll.setSelected(true);
                       labelMostExtreme.setSelected(false);labelPearsonResid2.setSelected(false);
                    } else if (model.plotControl.whichPointsToLabel[panelDealtWith].equals("mostExtrm")) {
                       labelNone.setSelected(false); labelAll.setSelected(false);
                       labelMostExtreme.setSelected(true);labelPearsonResid2.setSelected(false);
                    } else if (model.plotControl.whichPointsToLabel[panelDealtWith].equals("Pearson2")) {
                       labelNone.setSelected(false); labelAll.setSelected(false);
                       labelMostExtreme.setSelected(false);labelPearsonResid2.setSelected(true);
                    } 
                    if (model.plotControl.mostExtremeEnabled[panelDealtWith]&model.plotControl.graphicSystem[panelDealtWith].equals("traditional graphics")){
                       labelMostExtreme.setEnabled(true);    
                       numExtremeValues.setEnabled(true); 
                    } else {
                        labelMostExtreme.setEnabled(false);    
                        numExtremeValues.setEnabled(false); 
                    }
                        
                    labelPearsonResid2.setEnabled(model.plotControl.timeOrObsHorizontalAxis[panelDealtWith]);
                    numExtremeValuesChoices.setSelectedItem(model.plotControl.numExtremeValues[panelDealtWith]);
                    
                    extremenessBasis1.setVisible(false);extremenessBasis2.setVisible(false);extremenessBasis3.setVisible(false);
                    extremenessBasis4.setVisible(false);extremenessBasis5.setVisible(false);
                    if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("1")){
                        extremenessBasisChoices1.setSelectedItem(model.plotControl.extremenessBasis[panelDealtWith]);
                        extremenessBasis1.setVisible(true);
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("2")){
                        extremenessBasisChoices2.setSelectedItem(model.plotControl.extremenessBasis[panelDealtWith]);
                        extremenessBasis2.setVisible(true);
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("3")){
                        extremenessBasisChoices3.setSelectedItem(model.plotControl.extremenessBasis[panelDealtWith]);
                        extremenessBasis3.setVisible(true);
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("4")){
                        extremenessBasisChoices4.setSelectedItem(model.plotControl.extremenessBasis[panelDealtWith]);
                        extremenessBasis4.setVisible(true);
                    }else if (model.plotControl.extremenessScrollChoices[panelDealtWith].equals("5")){
                        extremenessBasisChoices5.setSelectedItem(model.plotControl.extremenessBasis[panelDealtWith]);
                        extremenessBasis5.setVisible(true);
                    }
                }
                
                
                
                userInputxAxisTitleText.setText(model.plotControl.xAxisTitle[panelDealtWith]);
                if (model.plotControl.xAxisTitle[panelDealtWith].equals("_na_")) 
                     userInputxAxisTitleText.setEnabled(false);
                userInputxAxisTitleText.setBackground((userInputxAxisTitleText.isEnabled())?Color.WHITE:Color.lightGray);
                userInputyAxisTitleText.setText(model.plotControl.yAxisTitle[panelDealtWith]);
                if (model.plotControl.yAxisTitle[panelDealtWith].equals("_na_")) 
                     userInputyAxisTitleText.setEnabled(false);
                userInputyAxisTitleText.setBackground((userInputyAxisTitleText.isEnabled())?Color.WHITE:Color.lightGray);
                legendText1.setText(model.plotControl.legendText1[panelDealtWith]);
                if (model.plotControl.plotType1[panelDealtWith].equals(""))
                    plotType1.setEnabled(false);
                plotType1Choices.setSelectedItem(model.plotControl.plotType1[panelDealtWith]);
                
                lineType1Choices.setSelectedItem(model.plotControl.lineType1[panelDealtWith]);
                if (model.plotControl.linePoints1linewidth[panelDealtWith].equals(""))
                    lineWidth1.setEnabled(false);
                lineWidth1Choices.setSelectedItem(model.plotControl.linePoints1linewidth[panelDealtWith]);
                linePointsColor1Choices.setSelectedItem(model.plotControl.linePoints1color[panelDealtWith]);
                linePoints1SymbolChoices.setSelectedItem(model.plotControl.linePoints1Symbol[panelDealtWith]);
                symbolSize1Choices.setSelectedItem(model.plotControl.symbolSize1[panelDealtWith]);

                legendText2.setText(model.plotControl.legendText2[panelDealtWith]);
                lineType2Choices.setSelectedItem(model.plotControl.lineType2[panelDealtWith]);
                lineWidth2Choices.setSelectedItem(model.plotControl.linePoints2linewidth[panelDealtWith]);
                linePointsColor2Choices.setSelectedItem(model.plotControl.linePoints2color[panelDealtWith]);
                
                legendText3.setText(model.plotControl.legendText3[panelDealtWith]);
                lineType3Choices.setSelectedItem(model.plotControl.lineType3[panelDealtWith]);
                lineWidth3Choices.setSelectedItem(model.plotControl.linePoints3linewidth[panelDealtWith]);
                linePointsColor3Choices.setSelectedItem(model.plotControl.linePoints3color[panelDealtWith]);
                
                legendText4.setText(model.plotControl.legendText4[panelDealtWith]);
                lineType4Choices.setSelectedItem(model.plotControl.lineType4[panelDealtWith]);
                lineWidth4Choices.setSelectedItem(model.plotControl.linePoints4linewidth[panelDealtWith]);
                linePointsColor4Choices.setSelectedItem(model.plotControl.linePoints4color[panelDealtWith]);
                
                legendText5.setText(model.plotControl.legendText5[panelDealtWith]);
                lineType5Choices.setSelectedItem(model.plotControl.lineType5[panelDealtWith]);
                lineWidth5Choices.setSelectedItem(model.plotControl.linePoints5linewidth[panelDealtWith]);
                linePointsColor5Choices.setSelectedItem(model.plotControl.linePoints5color[panelDealtWith]);
                
                legendText6.setText(model.plotControl.legendText6[panelDealtWith]);
                lineType6Choices.setSelectedItem(model.plotControl.lineType6[panelDealtWith]);
                lineWidth6Choices.setSelectedItem(model.plotControl.linePoints6linewidth[panelDealtWith]);
                linePointsColor6Choices.setSelectedItem(model.plotControl.linePoints6color[panelDealtWith]);
                
                if (userInputMainTitleText.equals("_na_")) 
                     userInputMainTitleText.setEnabled(false);
                if (model.plotControl.keyPosition[panelDealtWith].equals(""))  
                    keyPositionTypeTraditional.setEnabled(false);
                     showTickMarks.setSelected(model.plotControl.showTickMarks[panelDealtWith]);
                
                if (model.plotControl.showGrid[panelDealtWith]==null)
                    showGrid.setEnabled(false);
                else
                   showGrid.setSelected(model.plotControl.showGrid[panelDealtWith]);
              /*  JOptionPane.showMessageDialog(null,"debug HanselPlotEdit model.plotControl.singleGraphMulti[panelDealtWith]"+model.plotControl.singleGraphMulti[panelDealtWith]);*/ 
                singleGraph.setSelected(model.plotControl.singleGraphMulti[panelDealtWith]);/*smooth lines may be added, which makes this relevant even if singleGraph not visible */
                if ((model.terms.getSize()==1)||(!model.plotControl.singleGraphMultiShow[panelDealtWith])){       
                    singleGraph.setEnabled(false);
                    singleGraph.setVisible(false);
                }
                if (model.plotControl.ggplot2GrayTheme[panelDealtWith]==null)
                    ggplot2GrayTheme.setEnabled(false);
                else
                   ggplot2GrayTheme.setSelected(model.plotControl.ggplot2GrayTheme[panelDealtWith]);
                if (model.plotControl.pointLabelTextSize[panelDealtWith].equals(""))  
                    pointLabelTextSizeType.setEnabled(false);   
                if (model.plotControl.pointLabels[panelDealtWith].equals("")) { 
                    pointLabelsType.setEnabled(false);
                    pointLabelsTypeRowNamesOnly.setEnabled(false); 
                }
                if (userInputxAxisTitleText.equals("_na_")) 
                     userInputxAxisTitleText.setEnabled(false);
                if (model.plotControl.manualxAxisRange[panelDealtWith]==null){ 
                     manualxAxisRange.setEnabled(false);
                     manualxAxisMinText.setEnabled(false);
                     manualxAxisMaxText.setEnabled(false);
                } else {
                    manualxAxisRange.setSelected(model.plotControl.manualxAxisRange[panelDealtWith]);
                    manualxAxisMinText.setText(model.plotControl.manualxAxisMinText[panelDealtWith]);
                    manualxAxisMaxText.setText(model.plotControl.manualxAxisMaxText[panelDealtWith]);
                }
                if (userInputyAxisTitleText.equals("_na_")) 
                     userInputyAxisTitleText.setEnabled(false);
                if (model.plotControl.manualyAxisRange[panelDealtWith]==null){ 
                     manualyAxisRange.setEnabled(false);
                     manualyAxisMinText.setEnabled(false);
                     manualyAxisMaxText.setEnabled(false);
                } else {
                    manualyAxisRange.setSelected(model.plotControl.manualyAxisRange[panelDealtWith]);
                    manualyAxisMinText.setText(model.plotControl.manualyAxisMinText[panelDealtWith]);
                    manualyAxisMaxText.setText(model.plotControl.manualyAxisMaxText[panelDealtWith]);
                }
                if (model.plotControl.legendText1[panelDealtWith].equals("_na_"))
                    legendText1.setEnabled(false);
                legendText1.setBackground((legendText1.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType1[panelDealtWith].equals(""))  
                    lineType1.setEnabled(false);
                if (model.plotControl.linePoints1linewidth[panelDealtWith].equals(""))
                    lineWidth1.setEnabled(false);
                if (model.plotControl.linePoints1color[panelDealtWith].equals(""))
                    linePointsColorType1.setEnabled(false);
                if (model.plotControl.linePoints1Symbol[panelDealtWith].equals(""))
                    symbolType1.setEnabled(false);
                if (model.plotControl.symbolSize1[panelDealtWith].equals(""))
                    symbolSize1.setEnabled(false);
                if (model.plotControl.legendText2[panelDealtWith].equals("_na_"))  
                    legendText2.setEnabled(false);
                legendText2.setBackground((legendText2.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType2[panelDealtWith].equals(""))  
                    lineType2.setEnabled(false);
                if (model.plotControl.linePoints2linewidth[panelDealtWith].equals(""))
                    lineWidth2.setEnabled(false);
                if (model.plotControl.linePoints2color[panelDealtWith].equals(""))
                    linePointsColorType2.setEnabled(false);
                if (model.plotControl.confIntvl95[panelDealtWith]==null){ 
                     includeConfIntvl95.setEnabled(false);
                } else includeConfIntvl95.setSelected(model.plotControl.confIntvl95[panelDealtWith]);
                
                if (model.plotControl.legendText3[panelDealtWith].equals("_na_"))  
                    legendText3.setEnabled(false);
                legendText3.setBackground((legendText3.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType3[panelDealtWith].equals(""))  
                    lineType3.setEnabled(false);
                if (model.plotControl.linePoints3linewidth[panelDealtWith].equals(""))
                    lineWidth3.setEnabled(false);
                if (model.plotControl.linePoints3color[panelDealtWith].equals(""))
                    linePointsColorType3.setEnabled(false);
                noReactionToAction = false; 
                
                if (model.plotControl.legendText4[panelDealtWith].equals("_na_"))  
                    legendText4.setEnabled(false);
                legendText4.setBackground((legendText4.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType4[panelDealtWith].equals(""))  
                    lineType4.setEnabled(false);
                if (model.plotControl.linePoints4linewidth[panelDealtWith].equals(""))
                    lineWidth4.setEnabled(false);
                if (model.plotControl.linePoints4color[panelDealtWith].equals(""))
                    linePointsColorType4.setEnabled(false);
                noReactionToAction = false; 
                
                if (model.plotControl.legendText5[panelDealtWith].equals("_na_"))  
                    legendText5.setEnabled(false);
                legendText4.setBackground((legendText5.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType5[panelDealtWith].equals(""))  
                    lineType5.setEnabled(false);
                if (model.plotControl.linePoints5linewidth[panelDealtWith].equals(""))
                    lineWidth5.setEnabled(false);
                if (model.plotControl.linePoints5color[panelDealtWith].equals(""))
                    linePointsColorType5.setEnabled(false);
                noReactionToAction = false; 
                
                if (model.plotControl.legendText6[panelDealtWith].equals("_na_"))  
                    legendText6.setEnabled(false);
                legendText6.setBackground((legendText6.isEnabled())?Color.WHITE:Color.lightGray);
                if (model.plotControl.lineType6[panelDealtWith].equals(""))  
                    lineType6.setEnabled(false);
                if (model.plotControl.linePoints6linewidth[panelDealtWith].equals(""))
                    lineWidth6.setEnabled(false);
                if (model.plotControl.linePoints6color[panelDealtWith].equals(""))
                    linePointsColorType6.setEnabled(false);
                noReactionToAction = false; 
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
            updateModel();
             if (theParentHere.equals("GMExplorer")){
                ((GMExplorer)parent).alterPlot("Plot Edit");
            }else if (theParentHere.equals("EFEUTSExplorer")){
                 ((NMTimeSExplorer)parent).alterPlot("Plot Edit");
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
                    
                    
                } else if (cmd.equals("manual x-axis range"))  {
                    manualxAxisMinText.setEnabled(!manualxAxisMinText.isEnabled());
                    manualxAxisMinText.setBackground((manualxAxisMinText.isEnabled())?Color.WHITE:Color.lightGray);
                    manualxAxisMaxText.setEnabled(!manualxAxisMaxText.isEnabled());
                    manualxAxisMaxText.setBackground((manualxAxisMaxText.isEnabled())?Color.WHITE:Color.lightGray);
                } else if (cmd.equals("manual y-axis range"))  {
                    manualyAxisMinText.setEnabled(!manualyAxisMinText.isEnabled());
                    manualyAxisMinText.setBackground((manualyAxisMinText.isEnabled())?Color.WHITE:Color.lightGray);
                    manualyAxisMaxText.setEnabled(!manualyAxisMaxText.isEnabled()); 
                    manualyAxisMaxText.setBackground((manualyAxisMaxText.isEnabled())?Color.WHITE:Color.lightGray);
                 } else if (!noReactionToAction&
                            !(model.plotControl.graphicSystem[panelDealtWith].equals("traditional graphics")&traditionalGraphicsButton.isSelected()||
                              model.plotControl.graphicSystem[panelDealtWith].equals("lattice")&latticeButton.isSelected()||
                              model.plotControl.graphicSystem[panelDealtWith].equals("ggplot2")&ggplot2Button.isSelected())){ 
                     
                                if (traditionalGraphicsButton.isSelected()) {
                                    pointLabelTextSizeType.setVisible(true);
                                    pointLabelTextSizeLabel2.setVisible(false);
                                    keyPositionTypeTraditional.setVisible(true);
                                    keyPositionTypeLattice.setVisible(false);
                                    keyPositionChoicesTraditional.setSelectedItem(model.plotControl.keyPosition[panelDealtWith]);
                                    if ((model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("p")||
                                        model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("b")||
                                        model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("o"))&existsTS){ 
                                        symbolSize1.setVisible(false);
                                        symbolSizeEqLineWidth.setVisible(true);
                                        symbolSizeEqLineWidth2.setVisible(true);
                                        lineWidth1.setEnabled(true);
                                    }
                                    labelMostExtreme.setEnabled(model.plotControl.mostExtremeEnabled[panelDealtWith]);
                                    numExtremeValues.setEnabled(model.plotControl.mostExtremeEnabled[panelDealtWith]);
                                }
                                else if (latticeButton.isSelected()){
                                    pointLabelTextSizeType.setVisible(false);
                                    pointLabelTextSizeLabel2.setVisible(true);
                                    keyPositionTypeTraditional.setVisible(false);
                                    keyPositionTypeLattice.setVisible(true);
                                    if (model.plotControl.keyPosition[panelDealtWith].equals("topleft")||
                                        model.plotControl.keyPosition[panelDealtWith].equals("topright")||
                                        model.plotControl.keyPosition[panelDealtWith].equals("bottomleft")||
                                        model.plotControl.keyPosition[panelDealtWith].equals("bottomright"))
                                            model.plotControl.keyPosition[panelDealtWith] = 
                                                model.plotControl.keyPosition[panelDealtWith].replace("left","").replace("right","");
                                    keyPositionChoicesLattice.setSelectedItem(model.plotControl.keyPosition[panelDealtWith]);
                                    symbolSize1.setVisible(true);
                                    symbolSizeEqLineWidth.setVisible(false);
                                    symbolSizeEqLineWidth2.setVisible(false);
                                    if(model.plotControl.whichPointsToLabel[panelDealtWith].equals("mostExtrm"))
                                       model.plotControl.whichPointsToLabel[panelDealtWith] = "none";
                                    labelMostExtreme.setEnabled(false);
                                    numExtremeValues.setEnabled(false);
                                   
                                } else if (ggplot2Button.isSelected()){
                                    pointLabelTextSizeType.setVisible(true);
                                    pointLabelTextSizeLabel2.setVisible(false);
                                    symbolSize1.setVisible(true);
                                    symbolSizeEqLineWidth.setVisible(false);
                                    symbolSizeEqLineWidth2.setVisible(false);
                                    if(model.plotControl.whichPointsToLabel[panelDealtWith].equals("mostExtrm"))
                                       model.plotControl.whichPointsToLabel[panelDealtWith] = "none";
                                    labelMostExtreme.setEnabled(false);
                                    if(model.plotControl.whichPointsToLabel[panelDealtWith].equals("mostExtrm"))
                                       model.plotControl.whichPointsToLabel[panelDealtWith] = "none";
                                    labelMostExtreme.setEnabled(false);
                                    numExtremeValues.setEnabled(false);
                                }
                             if (immediate.isSelected())
                              apply();
                } else if (!noReactionToAction&!(plotType1Choices.getSelectedItem().toString().equals(model.plotControl.plotType1[panelDealtWith]))){
                          model.plotControl.plotType1[panelDealtWith] = plotType1Choices.getSelectedItem().toString();
                          if (model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("p")||
                              model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("b")||
                              model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("o")){ 
                                  if (model.plotControl.linePoints1Symbol[panelDealtWith].equals(""))
                                      linePoints1SymbolChoices.setSelectedItem("16: solid circle");                                  
                                  
                                  if (traditionalGraphicsButton.isSelected()&existsTS) {
                                      symbolSize1.setVisible(false);
                                      symbolSizeEqLineWidth.setVisible(true);
                                      symbolSizeEqLineWidth2.setVisible(true);
                                  } else { 
                                    symbolSize1.setVisible(true);
                                    symbolSizeEqLineWidth.setVisible(false);
                                    symbolSizeEqLineWidth2.setVisible(false);
                                    if (model.plotControl.symbolSize1[panelDealtWith].equals(""))
                                      symbolSize1Choices.setSelectedItem("1.0");
                                  }    

                                  symbolType1.setEnabled(true);
                                  symbolSize1.setEnabled(true);
                          } else{
                                  symbolType1.setEnabled(false);
                                  symbolSize1.setEnabled(false);
                          }
                                  
                          
                          if (model.plotControl.plotType1[panelDealtWith].split(":")[0].equals("p")){
                              lineType1.setEnabled(false);
                              if (!(traditionalGraphicsButton.isSelected()&existsTS))
                                 lineWidth1.setEnabled(false);
                          } else {
                              lineType1.setEnabled(true);
                              lineWidth1.setEnabled(true);
                          }
                          
                          if (immediate.isSelected())
                              apply();
                        
		} else if (immediate.isSelected()){
                    apply();
                }

        }
      class ModelListener implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
        }
    }

}