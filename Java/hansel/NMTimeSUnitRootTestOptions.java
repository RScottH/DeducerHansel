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
  GLMExplorerPlots.java and GLMExplorerPostHoc.java found in the Deducer package
 and
  ExampleDialog.java (dated 2010-03-12), found in the DeducerPlugInExample package.
  
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */


package hansel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;

import javax.swing.BorderFactory;
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
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;

import org.rosuda.deducer.models.RModel;
import org.rosuda.deducer.widgets.ButtonGroupWidget;

public class NMTimeSUnitRootTestOptions extends javax.swing.JDialog implements ActionListener{
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel termPanel;
	protected JTextField ylab;
	protected JCheckBox rug;
	protected JLabel ylabLabel;
	protected JSpinner levels;
	protected JLabel pointsLabel;
	protected JCheckBox multi2;
       	protected JCheckBox resvFitted;
        protected JCheckBox termPlots;
        protected JCheckBox ramseyRESET;
	protected JList terms;
	protected JPanel meansPanel;
	protected JPanel okayCancel;
        protected JPanel termsPanel;
	protected JCheckBox confInt;
	protected JCheckBox scaled;
	protected JPanel optionsPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
        protected JList termsList;
	protected JScrollPane termsScroller;
	protected GMModel  model;
        protected ButtonGroupWidget adfDeterministicComponent;
        protected ButtonGroupWidget ersDeterministicComponent;
        protected ButtonGroupWidget ppDeterministicComponent;
        protected ButtonGroupWidget kpssDeterministicComponent;
        protected ButtonGroupWidget spType;
        protected ButtonGroupWidget zaBreakControl;      
        protected ButtonGroupWidget differencing;
        protected ButtonGroupWidget adfLagDetermination;
        protected ButtonGroupWidget ersType;
        protected ButtonGroupWidget ppType;
        protected ButtonGroupWidget spPolynDegree;
     
        protected JLabel maxLagLabel;
        protected JLabel maxLagLabelShort;
        protected JLabel lagLabel;
        protected JComboBox adfMaxLag;
        protected JComboBox ersMaxLag; 
        protected JComboBox kpssMaxLag;
        protected JComboBox ppLag;
        protected ButtonGroupWidget spSig; 
        protected JComboBox zaMaxLag; 
        
        protected Boolean unitRootTestOptionsOkay = false;


        private String givenTest;
                protected static DefaultComboBoxModel maxLags  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel kpssMaxLags  = new DefaultComboBoxModel(
				new String[] { "short","long","nil","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel ppLags  = new DefaultComboBoxModel(
				new String[] { "short","long","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               protected static DefaultComboBoxModel zaMaxLags  = new DefaultComboBoxModel(
				new String[] { "NULL","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
               
	public NMTimeSUnitRootTestOptions(JFrame frame,GMModel mod,RModel rmod, String specificURTest) {
		super(frame);
                givenTest = specificURTest;
		initGUI();
		setModel(mod,rmod);
		this.setModal(true);
	}
	
	protected void initGUI() {
		try {
			{
                        AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(205, 260, 207, 36);
                                        okayCancel.setFont(font12);
                                {
                                        adfDeterministicComponent = new ButtonGroupWidget("Deterministic component",
						new String[]{"no deterministic component",
                                                             "drift","trend"});
                                        adfDeterministicComponent.setSelected("no deterministic component");
                                        getContentPane().add(adfDeterministicComponent, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        adfDeterministicComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                        adfDeterministicComponent.setFont(font12);
                                        adfDeterministicComponent.setVisible(false);
                                }

                                {
                                        ersDeterministicComponent = new ButtonGroupWidget("Deterministic component",
						new String[]{"constant",
                                                             "trend"});
                                        ersDeterministicComponent.setSelected("constant");
                                        getContentPane().add(ersDeterministicComponent, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ersDeterministicComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                        ersDeterministicComponent.setFont(font12);
                                        ersDeterministicComponent.setVisible(false);
                                }
                                {
                                        ppDeterministicComponent = new ButtonGroupWidget("Deterministic component",
						new String[]{"constant",
                                                             "trend"});
                                        ppDeterministicComponent.setSelected("constant");
                                        getContentPane().add(ppDeterministicComponent, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ppDeterministicComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                        ppDeterministicComponent.setFont(font12);
                                        ppDeterministicComponent.setVisible(false);
                                }
                                {
                                        kpssDeterministicComponent = new ButtonGroupWidget("Deterministic component",
						new String[]{"mu: constant",
                                                             "tau: constant with linear trend"});
                                        kpssDeterministicComponent.setSelected("mu: constant");
                                        getContentPane().add(kpssDeterministicComponent, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        kpssDeterministicComponent.setPreferredSize(new java.awt.Dimension(260, 100));
                                        kpssDeterministicComponent.setFont(font12);
                                        kpssDeterministicComponent.setVisible(false);
                                }     
                                {
                                        spType = new ButtonGroupWidget("Test type",
						new String[]{"tau",
                                                             "rho"});
                                        spType.setSelected("tau");
                                        getContentPane().add(spType, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        spType.setPreferredSize(new java.awt.Dimension(260, 100));
                                        spType.setFont(font12);
                                        spType.setVisible(false);
                                }    
                                {
                                        zaBreakControl = new ButtonGroupWidget("Break control",
						new String[]{"intercept",
                                                             "trend","both"});
                                        zaBreakControl.setSelected("intercept");
                                        getContentPane().add(zaBreakControl, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        zaBreakControl.setPreferredSize(new java.awt.Dimension(260, 100));
                                        zaBreakControl.setFont(font12);
                                        zaBreakControl.setVisible(false);
                                }     
 
                                
                                {
                                        differencing = new ButtonGroupWidget("Differencing",
						new String[]{"Use level (no differencing)",
                                                             "Use 1st difference","Use 2nd difference"});
                                        differencing.setSelected("Use level (no differencing)");
                                        differencing.setFont(font12);
                                        getContentPane().add(differencing, new AnchorConstraint(11, 931, 805, 500, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        differencing.setPreferredSize(new java.awt.Dimension(260, 100));
                                }
                                
                                {
                                        adfLagDetermination = new ButtonGroupWidget("Lag order determination",
						new String  []{"Fixed",
                                                             "AIC","BIC"});
                                        adfLagDetermination.setSelected("BIC");
                                        adfLagDetermination.setFont(font12);
                                        getContentPane().add(adfLagDetermination, new AnchorConstraint(400, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        adfLagDetermination.setPreferredSize(new java.awt.Dimension(260, 100));
                                        adfLagDetermination.setVisible(false);
                                }
                                {
                                        ersType = new ButtonGroupWidget("Type of ERS Test",
						new String[]{"DF-GLS",
                                                             "P-test"});
                                        ersType.setSelected("DF-GLS");
                                        getContentPane().add(ersType, new AnchorConstraint(400, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ersType.setPreferredSize(new java.awt.Dimension(260, 100));
                                        ersType.setVisible(false);
                                }  
                                {
                                        ppType = new ButtonGroupWidget("Type of Phillips & Perron Test",
						new String[]{"Z-alpha",
                                                             "Z-tau"});
                                        ppType.setSelected("Z-alpha");
                                        ppType.setFont(font12);
                                        getContentPane().add(ppType, new AnchorConstraint(400, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        ppType.setPreferredSize(new java.awt.Dimension(260, 100));
                                        ppType.setVisible(false);
                                }   
                                {
                                        spPolynDegree = new ButtonGroupWidget("Polynomial Degree",
						new String[]{"1","2","3","4"});
                                        spPolynDegree.setSelected("2");
                                        spPolynDegree.setFont(font12);
                                        getContentPane().add(spPolynDegree, new AnchorConstraint(400, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        spPolynDegree.setPreferredSize(new java.awt.Dimension(280, 100));
                                        spPolynDegree.setVisible(false);
                                }  

                                {
					maxLagLabel = new JLabel();
					getContentPane().add(maxLagLabel, new AnchorConstraint(400, 487, 982, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					maxLagLabel.setText("Maximum Lag (or Actual Lag if Fixed)");
                                        maxLagLabel.setFont(font12);
					maxLagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
                                        maxLagLabel.setVisible(false);
				}  
                                 {
					maxLagLabelShort = new JLabel();
					getContentPane().add(maxLagLabelShort, new AnchorConstraint(400, 487, 982, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					maxLagLabelShort.setText("Maximum Lag");
                                        maxLagLabelShort.setFont(font12);
					maxLagLabelShort.setPreferredSize(new java.awt.Dimension(400, 20));
                                        maxLagLabelShort.setVisible(false);
				}  
                                {
					lagLabel = new JLabel();
					getContentPane().add(lagLabel, new AnchorConstraint(400, 487, 982, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					lagLabel.setText("Lag");
                                        lagLabel.setFont(font12);
					lagLabel.setPreferredSize(new java.awt.Dimension(400, 20));
                                        lagLabel.setVisible(false);
				}
                                
                                
                               {
					adfMaxLag = new JComboBox();
					getContentPane().add(adfMaxLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					adfMaxLag.setModel(maxLags);
                                        adfMaxLag.setFont(font12);
					adfMaxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					adfMaxLag.addActionListener(this);
                                        adfMaxLag.setVisible(false);
				}

                               {
					ersMaxLag = new JComboBox();
					getContentPane().add(ersMaxLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					ersMaxLag.setModel(maxLags);
                                        ersMaxLag.setFont(font12);
					ersMaxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					ersMaxLag.addActionListener(this);
                                        ersMaxLag.setVisible(false);
				}
                                {
					kpssMaxLag = new JComboBox();
					getContentPane().add(kpssMaxLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					kpssMaxLag.setModel(kpssMaxLags);
                                        kpssMaxLag.setFont(font12);
					kpssMaxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					kpssMaxLag.addActionListener(this);
                                        kpssMaxLag.setVisible(false);
				}        
                                {
					ppLag = new JComboBox();
					getContentPane().add(ppLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					ppLag.setModel(ppLags);
                                        ppLag.setFont(font12);
					ppLag.setPreferredSize(new java.awt.Dimension(60, 21));
					ppLag.addActionListener(this);
                                        ppLag.setVisible(false);
				}    
                                {
                                        spSig = new ButtonGroupWidget("Significance level",
						new String[]{"0.01","0.05","0.10"});
                                        spSig.setSelected("0.05");
                                        spSig.setFont(font12);
                                        getContentPane().add(spSig, new AnchorConstraint(400, 431, 804, 500,
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        spSig.setPreferredSize(new java.awt.Dimension(260, 100));
                                        spSig.setVisible(false);
                                }  
                                
                                {
					zaMaxLag = new JComboBox();
					getContentPane().add(zaMaxLag, new AnchorConstraint(450, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					zaMaxLag.setModel(zaMaxLags);
                                        zaMaxLag.setFont(font12);
					zaMaxLag.setPreferredSize(new java.awt.Dimension(60, 21));
					zaMaxLag.addActionListener(this);
                                        zaMaxLag.setVisible(false);
				}  

				}
			}
			this.setTitle(givenTest+ " test");
                        this.setFont(font12);
			this.setSize(632, 360);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
                model.unitRootTestOptions.differencing = differencing.getSelectedItemText();
                if (givenTest.equals("Augmented Dickey-Fuller")){
                       model.unitRootTestOptions.adfDeterministicComponent = adfDeterministicComponent.getSelectedItemText();
                       model.unitRootTestOptions.adfLagDetermination = adfLagDetermination.getSelectedItemText();  
                       model.unitRootTestOptions.adfMaxLag = adfMaxLag.getSelectedItem().toString();
                } else if (givenTest.equals("ADF-GLS")){
                       model.unitRootTestOptions.ersDeterministicComponent= ersDeterministicComponent.getSelectedItemText();
                       model.unitRootTestOptions.ersType = ersType.getSelectedItemText();
                       model.unitRootTestOptions.ersMaxLag = ersMaxLag.getSelectedItem().toString();
                } else if (givenTest.equals("KPSS")){
                       model.unitRootTestOptions.kpssDeterministicComponent = kpssDeterministicComponent.getSelectedItemText();
                       model.unitRootTestOptions.kpssMaxLag = kpssMaxLag.getSelectedItem().toString();
                } else if (givenTest.equals("Phillips and Perron")){
                       model.unitRootTestOptions.ppDeterministicComponent = ppDeterministicComponent.getSelectedItemText();
                       model.unitRootTestOptions.ppType = ppType.getSelectedItemText();
                       model.unitRootTestOptions.ppLag = ppLag.getSelectedItem().toString();
                } else if (givenTest.equals("Schmidt and Phillips")){
                       model.unitRootTestOptions.spType = spType.getSelectedItemText();
                       model.unitRootTestOptions.spPolynDegree = spPolynDegree.getSelectedItemText();
                       model.unitRootTestOptions.spSig= spSig.getSelectedItemText();
                } else /*if (givenTest.equals("Zivot and Andrews"))*/{
                       model.unitRootTestOptions.zaBreakControl = zaBreakControl.getSelectedItemText();
                       model.unitRootTestOptions.zaMaxLag = zaMaxLag.getSelectedItem().toString();
                }
	}
        
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                differencing.setSelected(model.unitRootTestOptions.differencing);
                model.efeaeplots.moreDiagnosticPlots = "None";
                
                
                if (givenTest.equals("Augmented Dickey-Fuller")){
                    adfDeterministicComponent.setSelected(model.unitRootTestOptions.adfDeterministicComponent);
                    adfLagDetermination.setSelected(model.unitRootTestOptions.adfLagDetermination);
                    adfMaxLag.setSelectedItem(model.unitRootTestOptions.adfMaxLag);
                    
                    adfDeterministicComponent.setVisible(true);
                    adfLagDetermination.setVisible(true);
                    maxLagLabel.setVisible(true);
                    adfMaxLag.setVisible(true);
                } else if (givenTest.equals("ADF-GLS")){
                    ersDeterministicComponent.setSelected(model.unitRootTestOptions.ersDeterministicComponent);
                    ersType.setSelected(model.unitRootTestOptions.ersType);
                    ersMaxLag.setSelectedItem(model.unitRootTestOptions.ersMaxLag);
                    
                    ersDeterministicComponent.setVisible(true);
                    ersType.setVisible(true);
                    maxLagLabelShort.setVisible(true);
                        ersMaxLag.setVisible(true);
                } else if (givenTest.equals("KPSS")){
                    kpssDeterministicComponent.setSelected(model.unitRootTestOptions.kpssDeterministicComponent);
                    kpssMaxLag.setSelectedItem(model.unitRootTestOptions.kpssMaxLag);
                    
                    kpssDeterministicComponent.setVisible(true);
                    maxLagLabelShort.setVisible(true);
                    kpssMaxLag.setVisible(true);
                } else if (givenTest.equals("Phillips and Perron")){
                    ppDeterministicComponent.setSelected(model.unitRootTestOptions.ppDeterministicComponent);
                    ppType.setSelected(model.unitRootTestOptions.ppType);
                    ppLag.setSelectedItem(model.unitRootTestOptions.ppLag); 
                    
                    ppDeterministicComponent.setVisible(true);
                    ppType.setVisible(true);
                    lagLabel.setVisible(true);
                    ppLag.setVisible(true);
                } else if (givenTest.equals("Schmidt and Phillips")){      
                    spType.setSelected(model.unitRootTestOptions.spType);
                    spPolynDegree.setSelected(model.unitRootTestOptions.spPolynDegree);
                    spSig.setSelected(model.unitRootTestOptions.spSig);
                    
                    spType.setVisible(true);
                    spPolynDegree.setVisible(true);
                    spSig.setVisible(true);
                } else /*if (givenTest.equals("Zivot and Andrews"))*/{
                    zaBreakControl.setSelected(model.unitRootTestOptions.zaBreakControl);
                    zaMaxLag.setSelectedItem(model.unitRootTestOptions.zaMaxLag);   
                    
                    zaBreakControl.setVisible(true);
                    maxLagLabelShort.setVisible(true);
                    zaMaxLag.setVisible(true);
                }
                        
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    
                    unitRootTestOptionsOkay = true;
                        
			updateModel();
			this.dispose();
		}else if(cmd=="Add"){
			Object[] objs=terms.getSelectedValuesList().toArray();
			for(int i=0;i<objs.length;i++){
				((DefaultListModel)terms.getModel()).removeElement(objs[i]);
				if(objs[i]!=null)
					((DefaultListModel)effects.getModel()).addElement(objs[i]);
			}
		}else if(cmd=="Remove"){
			Object[] objs=effects.getSelectedValuesList().toArray();
			for(int i=0;i<objs.length;i++){
				((DefaultListModel)effects.getModel()).removeElement(objs[i]);
				if(objs[i]!=null)
					((DefaultListModel)terms.getModel()).addElement(objs[i]);
			}
		}
		
	}

}