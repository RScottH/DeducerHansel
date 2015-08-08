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
  GLMExplorerPlots.java and GLMExplorerPostHoc.java, found in the Deducer package.
 
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


public class NMTimeSPolynFourierOptions extends javax.swing.JDialog implements ActionListener{
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
        protected JCheckBox centreSimpleMA;
        
        protected JLabel polynomialOrderLabel;
        protected JComboBox polynomialOrder;
        
        protected JLabel fourierOrderLabel;
        protected JComboBox fourierOrder;
        
        private String givenDecomposition;
                            protected static DefaultComboBoxModel polynomialOrders  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                           protected static DefaultComboBoxModel fourierOrders  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
       protected JLabel fourierPeriodicityLabel;
       protected JComboBox fourierPeriodicity;
       protected static DefaultComboBoxModel fourierPeriodicityChoices  = new DefaultComboBoxModel(
				new String[] { "0.25*(length of data)","0.5*(length of data)","length of data","2*(length of data)","3*(length of data)","4*(length of data)",
                                               "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40",
                                               });
                
	
	public NMTimeSPolynFourierOptions(JFrame frame,GMModel mod,RModel rmod, String specificDecomposition) {
		super(frame);
                givenDecomposition = specificDecomposition;
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
					okayCancel.setBounds(380, 320, 207, 36);
                                        okayCancel.setFont(font12);

                                {
					polynomialOrderLabel = new JLabel();
					getContentPane().add(polynomialOrderLabel, new AnchorConstraint(11, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					polynomialOrderLabel.setText("Polynomial Order");
                                        polynomialOrderLabel.setFont(font12);
					polynomialOrderLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                                                {
					polynomialOrder = new JComboBox();
					getContentPane().add(polynomialOrder, new AnchorConstraint(61, 931, 805, 11,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					polynomialOrder.setModel(polynomialOrders);
                                        polynomialOrder.setSelectedItem("2");
                                        polynomialOrder.setFont(font12);
					polynomialOrder.setPreferredSize(new java.awt.Dimension(60, 21));
					polynomialOrder.addActionListener(this);
				}
                                {
					fourierOrderLabel = new JLabel();
					getContentPane().add(fourierOrderLabel, new AnchorConstraint(11, 931, 805, 410,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					fourierOrderLabel.setText("Fourier Frequency Order");
                                        fourierOrderLabel.setFont(font12);
					fourierOrderLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                                                {
					fourierOrder = new JComboBox();
					getContentPane().add(fourierOrder, new AnchorConstraint(61, 931, 805, 410,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					fourierOrder.setModel(fourierOrders);
                                        fourierOrder.setFont(font12);
					fourierOrder.setPreferredSize(new java.awt.Dimension(60, 21));
					fourierOrder.addActionListener(this);
				}                                                                
                                                                
                               {
					fourierPeriodicityLabel = new JLabel();
					getContentPane().add(fourierPeriodicityLabel, new AnchorConstraint(161, 931, 805, 410,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					fourierPeriodicityLabel.setText("Periodicity If Fourier Frequency Order > 0");
                                        fourierPeriodicityLabel.setFont(font12);
					fourierPeriodicityLabel.setPreferredSize(new java.awt.Dimension(600, 20));
				}
                                {
					fourierPeriodicity = new JComboBox();
					getContentPane().add(fourierPeriodicity, new AnchorConstraint(201, 931, 805, 410,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					fourierPeriodicity.setModel(fourierPeriodicityChoices);
                                        fourierPeriodicity.setFont(font12);
					fourierPeriodicity.setPreferredSize(new java.awt.Dimension(200, 21));
					fourierPeriodicity.addActionListener(this);
				}       
				}
			}
			this.setTitle(givenDecomposition);
                        this.setFont(font12);
			this.setSize(632, 400);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){    
               model.filterOptions.polynomialOrder = polynomialOrder.getSelectedItem().toString();
               model.filterOptions.fourierOrder = fourierOrder.getSelectedItem().toString();
               model.filterOptions.fourierPeriodicity = fourierPeriodicity.getSelectedItem().toString();
	}      
                
	

	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.efeaeplots.moreDiagnosticPlots = "None";
                polynomialOrder.setSelectedItem(model.filterOptions.polynomialOrder);
                fourierOrder.setSelectedItem(model.filterOptions.fourierOrder);
                fourierPeriodicity.setSelectedItem(model.filterOptions.fourierPeriodicity);
		
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    model.filterOptions.filterRun = true;   
			updateModel();
			this.dispose();
		}
		
	}

}