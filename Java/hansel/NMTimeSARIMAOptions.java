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
 
The current file most notably uses the code from GLMExplorerPlots.java and GLMExplorerPostHoc.java in the Deducer package.  
 
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




public class NMTimeSARIMAOptions extends javax.swing.JDialog implements ActionListener{
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
        protected JLabel maxLagLabel;
        protected JComboBox maxLag;
        protected JLabel arOrderLabel;
        protected JComboBox arOrder;
        protected JLabel integrationOrderLabel;
        protected JComboBox integrationOrder;
        protected JLabel maOrderLabel;
        protected JComboBox maOrder;
        protected JLabel boxTestLabel;
        protected JComboBox boxTestLags ;
        protected JLabel predictAheadLabel;
        protected JComboBox predictAhead ;
        ;
        private String givenDecomposition;
                protected static DefaultComboBoxModel arOrderlags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                                protected static DefaultComboBoxModel intOrderLags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                               protected static DefaultComboBoxModel maOrderlags  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                               protected static DefaultComboBoxModel lagsforBoxTest  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                                protected static DefaultComboBoxModel predictPeriods  = new DefaultComboBoxModel(
				new String[] { "0","1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                               
                
	
	public NMTimeSARIMAOptions(JFrame frame,GMModel mod,RModel rmod, String specificDecomposition) {
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
					okayCancel.setBounds(205, 260, 207, 36);
                                        okayCancel.setFont(font12);
                               {
					arOrderLabel = new JLabel();
					getContentPane().add(arOrderLabel, new AnchorConstraint(11, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					arOrderLabel.setText("Autoregression (AR) Order");
                                        arOrderLabel.setFont(font12);
					arOrderLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}  
                                {
					arOrder = new JComboBox();
					getContentPane().add(arOrder, new AnchorConstraint(61, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					arOrder.setModel(arOrderlags);
                                        arOrder.setSelectedItem("0");
                                        arOrder.setFont(font12);
					arOrder.setPreferredSize(new java.awt.Dimension(60, 21));
					arOrder.addActionListener(this);
				}          
                                                    
                                {
					integrationOrderLabel = new JLabel();
					getContentPane().add(integrationOrderLabel, new AnchorConstraint(11, 931, 805, 400,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					integrationOrderLabel.setText("Integration (I) Order");
                                        integrationOrderLabel.setFont(font12);
					integrationOrderLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}     
                                {
					integrationOrder = new JComboBox();
					getContentPane().add(integrationOrder, new AnchorConstraint(61, 931, 805, 400,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					integrationOrder.setModel(intOrderLags);
                                        integrationOrder.setSelectedItem("0");
                                        integrationOrder.setFont(font12);
					integrationOrder.setPreferredSize(new java.awt.Dimension(60, 21));
					integrationOrder.addActionListener(this);
				}  
                                {
					maOrderLabel = new JLabel();
					getContentPane().add(maOrderLabel, new AnchorConstraint(11, 931, 805, 700,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					maOrderLabel.setText("Moving Average (MA) Order");
                                        maOrderLabel.setFont(font12);
					maOrderLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                {
					maOrder = new JComboBox();
					getContentPane().add(maOrder, new AnchorConstraint(61, 931, 805, 700,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					maOrder.setModel(maOrderlags);
                                        maOrder.setSelectedItem("0");
                                        maOrder.setFont(font12);
					maOrder.setPreferredSize(new java.awt.Dimension(60, 21));
					maOrder.addActionListener(this);
				}                                   
                                {
					boxTestLabel = new JLabel();
					getContentPane().add(boxTestLabel, new AnchorConstraint(450, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					boxTestLabel.setText("Lag for Box test");
                                        boxTestLabel.setFont(font12);
					boxTestLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}   
                                {
					boxTestLags = new JComboBox();
					getContentPane().add(boxTestLags, new AnchorConstraint(500, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					boxTestLags.setModel(lagsforBoxTest);
                                        boxTestLags.setSelectedItem("1");
                                        boxTestLags.setFont(font12);
					boxTestLags.setPreferredSize(new java.awt.Dimension(60, 21));
					boxTestLags.addActionListener(this);
				} 
                                {
					predictAheadLabel = new JLabel();
					getContentPane().add(predictAheadLabel, new AnchorConstraint(450, 931, 805, 700,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					predictAheadLabel.setText("Number of Periods Predicting Ahead");
                                        predictAheadLabel.setFont(font12);
					predictAheadLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				} 
                                {
					predictAhead = new JComboBox();
					getContentPane().add(predictAhead , new AnchorConstraint(500, 931, 805, 700, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					predictAhead.setModel(predictPeriods);
                                        predictAhead.setSelectedItem("0");
                                        predictAhead.setFont(font12);
					predictAhead .setPreferredSize(new java.awt.Dimension(60, 21));
					predictAhead .addActionListener(this);
				}  

				}
			}
			this.setTitle(givenDecomposition);
                        this.setFont(font12);
			this.setSize(632, 360);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){                 
                model.arimaOptions.arOrder = arOrder.getSelectedItem().toString(); 
                model.arimaOptions.integrationOrder=integrationOrder.getSelectedItem().toString(); 
                model.arimaOptions.maOrder=maOrder.getSelectedItem().toString();
                model.arimaOptions.boxTestLags=boxTestLags.getSelectedItem().toString();               
                model.arimaOptions.predictAhead =predictAhead.getSelectedItem().toString();     
                
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.efeaeplots.moreDiagnosticPlots = "None";
                arOrder.setSelectedItem(model.arimaOptions.arOrder);
                integrationOrder.setSelectedItem(model.arimaOptions.integrationOrder);
                maOrder.setSelectedItem(model.arimaOptions.maOrder);
                boxTestLags.setSelectedItem(model.arimaOptions.boxTestLags);
                predictAhead.setSelectedItem(model.arimaOptions.predictAhead);
                predictAhead.setSelectedItem(model.arimaOptions.predictAhead);
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                    model.filterOptions.filterRun = true;          
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