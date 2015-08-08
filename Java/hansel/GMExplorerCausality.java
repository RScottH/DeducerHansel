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
  GLMExplorerPlots.java and ModelBuilder.java, found in the Deducer package
 and
  ExampleDialog.java,found in the DeducerPlugInExample package.
 
The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-08.
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
import javax.swing.JTextArea;
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




public class GMExplorerCausality extends javax.swing.JDialog implements ActionListener{
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
        protected JPanel impulsePanel;
        protected JScrollPane impulseScroller;
        protected JList impulseList;
        protected JPanel termsPanel;
        protected JScrollPane termsScroller;
        protected JList termsList;
        
        protected JPanel bootstrapRepsPanel;
        private JScrollPane  bootstrapRepsScroller;
        private JTextArea bootstrapRepsText;
        protected JCheckBox scaled;
	protected JPanel optionsPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;
        protected ButtonGroupWidget bootstrapChoice;
        protected GMModel  model;


	public GMExplorerCausality(JFrame frame,GMModel mod,RModel rmod) {
		super(frame);
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
					okayCancel.setBounds(205, 360, 207, 36);
                                        okayCancel.setFont(font12);
                                        
                               	{
					termsPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					termsPanel.setLayout(varPanelLayout);
					getContentPane().add(termsPanel, new AnchorConstraint(11, 375, 725, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					termsPanel.setBorder(BorderFactory.createTitledBorder("Causal variable(s)"));
                                        termsPanel.setFont(font12);
					termsPanel.setPreferredSize(new java.awt.Dimension(215, 130));
					{
						termsScroller = new JScrollPane();
						termsPanel.add(termsScroller, BorderLayout.CENTER);
						{
							ListModel varListModel = 
								new DefaultListModel();
							termsList = new JList();
							termsScroller.setViewportView(termsList);
							termsList.setModel(varListModel);
                                                        termsList.setFont(font12);
						}
					}
				} 
                                 {
                                       bootstrapChoice = new ButtonGroupWidget("Choice to bootstrap or Not",
						new String[]{"do not bootstrap",
                                                             "bootstrap"});
                                        bootstrapChoice.setFont(font12);
                                        bootstrapChoice.setSelected("differenced variables");
                                        getContentPane().add(bootstrapChoice, new AnchorConstraint(11, 931, 805, 400, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        bootstrapChoice.setPreferredSize(new java.awt.Dimension(180, 100));
                                }
                                 
                                {
                                    bootstrapRepsPanel  = new JPanel();
                                    getContentPane().add(bootstrapRepsPanel, new AnchorConstraint(300, 931, 805, 400, 
                                                    AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
                                                    AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                    BorderLayout modelFormulaLayout = new BorderLayout();
                                    bootstrapRepsPanel.setLayout(modelFormulaLayout);
                                    bootstrapRepsPanel.setBorder(BorderFactory.createTitledBorder("Number of bootrap replications (if bootstap used)"));
                                    bootstrapRepsPanel.setPreferredSize(new java.awt.Dimension(220, 52));
                                    bootstrapRepsPanel.setFont(font12);
                                 }   
                                 {
                                        bootstrapRepsScroller = new JScrollPane();
                                        bootstrapRepsPanel.add(bootstrapRepsScroller, BorderLayout.CENTER);
                                            {
                                                    bootstrapRepsText = new JTextArea();
                                                    bootstrapRepsScroller.setViewportView(bootstrapRepsText);
                                                    bootstrapRepsText.setFont(new java.awt.Font("Monospaced",0,11));
                                                    bootstrapRepsText.setEditable(true);

                                            }
                                    }

                                 
				}
			}
			this.setTitle("Causality Tests");
                        this.setFont(font12);
			this.setSize(632, 460);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
            model.efeglmextra.causalSelection = termsList.getSelectedIndices();
            model.efeglmextra.bootstrapChoice = bootstrapChoice.getSelectedItemText();
            model.efeglmextra.bootstrapReps=bootstrapRepsText.getText();                                   
	}
        
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                termsList.setModel(model.termsFinalStage);

                if (!(model.efeglmextra.causalSelection[0]==-1)) {
                  termsList.setSelectedIndices(model.efeglmextra.causalSelection);
                  bootstrapChoice.setSelected(model.efeglmextra.bootstrapChoice);
                  bootstrapRepsText.setText(model.efeglmextra.bootstrapReps);
                } else
                {
                bootstrapChoice.setSelected("do not bootstrap");
                bootstrapRepsText.setText("100");    
                }
                model.efeaetext.causalityTest = false;
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                       
             	       Object[] newTerms = termsList.getSelectedValues();
                       if (newTerms==null || (newTerms.length<1))
                         {
				JOptionPane.showMessageDialog(null, "Please select at least one variable");
				return;
			 }
                        model.efeglmextra.causalVariables = "\"" + (String) newTerms[0] + "\"";
			for(int i =1;i<newTerms.length;i++){
				model.efeglmextra.causalVariables+=",\""+newTerms[i]+"\"";
			}
                       
                        model.efeaetext.causalityTest = true;
                        updateModel();
			this.dispose();
		}
		
	}

}