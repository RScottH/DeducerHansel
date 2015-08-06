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
  ModelExplorer.java  found in the Deducer package
  
 The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;
import org.rosuda.JGR.layout.AnchorConstraint;
import org.rosuda.JGR.layout.AnchorLayout;
import org.rosuda.JGR.util.ErrorMsg;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;


public class NMBasicExplorer extends JFrame {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	protected JPanel topPanel;
	protected JPanel bottomPanel;
	protected JTextArea preview;
        protected JTextArea addexp;
	protected JPanel generalTab;
	protected JButton plots;
   	protected JButton residrelations;     
	protected JButton postHoc;
	protected JButton means;
	protected JButton export;
	protected JButton update;

	protected JLabel modelFormula;
	protected JPanel okayCancelPanel;
	protected HelpButton help;
	protected JButton tests;
	protected JButton options;
        protected JButton diagnosticTests;
	protected JTabbedPane tabs;
	protected JScrollPane previewScroller;
        protected JScrollPane addexpScroller;
	protected JPanel previewPanel;
        protected JPanel addexpPanel;
	protected JPanel middlePanel;
        protected JPanel rightPanel;
	protected ActionListener generalListener = new ModelListener();
	
	public NMBasicExplorer() {
		super();
		initGUI();
	}
	
	protected void initGUI() {
		try {
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				{
					topPanel = new JPanel();
					getContentPane().add(topPanel, new AnchorConstraint(0, 989, 58, 0, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					topPanel.setLayout(null);
					topPanel.setPreferredSize(new java.awt.Dimension(700, 35));
					{
						modelFormula = new JLabel();
						topPanel.add(modelFormula);
						modelFormula.setBounds(282, -1, 418, 36);
						modelFormula.setFont(new java.awt.Font("Tahoma",0,10));
					}
				}

				{
					middlePanel = new JPanel();
					BorderLayout middlePanelLayout = new BorderLayout();
					getContentPane().add(middlePanel, new AnchorConstraint(35, 989, 62, 0, AnchorConstraint.ANCHOR_ABS, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL));
					middlePanel.setLayout(middlePanelLayout);
					middlePanel.setPreferredSize(new java.awt.Dimension(900, 506));
					{
						tabs = new JTabbedPane ();
						middlePanel.add(tabs, BorderLayout.CENTER);
						tabs.setPreferredSize(new java.awt.Dimension(700, 501));
						{
							generalTab = new JPanel();
              						AnchorLayout generalTabLayout = new AnchorLayout();
							tabs.addTab("General", null, generalTab, null);
                                                        tabs.setFont(font12);
							generalTab.setLayout(generalTabLayout);
							generalTab.setPreferredSize(new java.awt.Dimension(695, 475));
							{

							}
                                                        							{
								addexpPanel = new JPanel();
								generalTab.add(addexpPanel, new AnchorConstraint(510, 731, 992, 12, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, 
										AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
								BorderLayout previewPanelLayout = new BorderLayout();
								addexpPanel.setLayout(previewPanelLayout);
								addexpPanel.setBorder(BorderFactory.createTitledBorder("Results from additional exploration"));
								addexpPanel.setFont(font12);
                                                                addexpPanel.setPreferredSize(new java.awt.Dimension(500, 475));
								{
									addexpScroller = new JScrollPane();
									addexpPanel.add(addexpScroller, BorderLayout.CENTER);
									{
										addexp = new JTextArea();
										addexpScroller.setViewportView(addexp);
										addexp.setFont(new java.awt.Font("Monospaced",0,11));
										addexp.setEditable(false);
									}
								}
							}
			                                         addexpPanel.setVisible(false);
						}
					}
				}
				{
					bottomPanel = new JPanel();
					AnchorLayout bottomPanelLayout = new AnchorLayout();
					getContentPane().add(bottomPanel, new AnchorConstraint(898, 989, 1000, 0, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					bottomPanel.setLayout(bottomPanelLayout);
					bottomPanel.setPreferredSize(new java.awt.Dimension(700, 62));
					{
						help = new HelpButton("");
						bottomPanel.add(help, new AnchorConstraint(379, 66, 600, 17, 
								AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_NONE, 
								AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
						help.setPreferredSize(new java.awt.Dimension(32, 32));
						help.addActionListener(generalListener);
                                                help.setEnabled(false);
					}
				}
			}
			this.setSize(750, 725);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void refresh(){}
	
	public void optionsClicked(){}
        public void diagnosticTestsClicked(){}
        public void decompositionClicked(String specificDecomposition){}
        public void unitRootTestClicked(String specificTest){}
	public void postHocClicked(){}
	public void testsClicked(){}
	public void plotsClicked(){}
        public void functionalFormClicked(){}
	public void meansClicked(){}
	public void exportClicked(){}
	public void updateClicked(){}
        public void specifyClicked(){}
	public void helpClicked(){}
	public void cancel(){
		this.dispose();
	}
	
	public void reset(){}
	
	public void run(){
		this.dispose();
	}
	
	class ModelListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
		/*	if(cmd=="Cancel"){
				cancel();
			}else if(cmd=="Reset"){
				reset();
			}else if(cmd=="Run"){
				run();
                        }else */if(cmd=="Initial Selections Page"){
				specifyClicked();
			}else if(cmd=="Update Model"){
				updateClicked();
			}else if(cmd == "ANOVA"){
				optionsClicked();
                        }else if(cmd == "Diagnostic Statistics & Tests"){
				diagnosticTestsClicked();
                        }else if((cmd == "Exponential Smoothing")||(cmd=="Seasonal Holt-Winters")||(cmd=="Non-seasonal Holt-Winters")||
                                 (cmd=="Multiplicative decomposition")||(cmd=="Additive decomposition")||(cmd=="Multiplicative & Additive decomposition")||
                                 (cmd=="Kalman filter")||(cmd=="Wavelet")){
				decompositionClicked(cmd);   
                        }else if((cmd == "Augmented Dickey-Fuller")||(cmd=="ADF-GLS")||(cmd=="KPSS")||
                                 (cmd=="Phillips and Perron")||(cmd=="Zivot and Andrews")){
				unitRootTestClicked(cmd); 
			}else if(cmd == "Post Hoc"){
				postHocClicked();
			}else if(cmd == "Prediction (Effects)"){
				meansClicked();
			}else if(cmd == "Additional Plots"){
				functionalFormClicked();
                        }else if(cmd == "Plots"){
				plotsClicked();
			}else if(cmd == "Tests on Coefficients"){
				testsClicked();
			}else if(cmd == "Export"){
				exportClicked();
			}else if(cmd =="Help"){
				helpClicked();
			}
			
		}
		
	}
	
}
