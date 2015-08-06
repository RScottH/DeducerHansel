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
 
 * The code from those packages used in this file most notably come from
  GLMExplorerPlots.java and GLMExplorerPostHoc.java found in the Deducer package
 and
  ExampleDialog.java, found in the DeducerPlugInExample package.
  
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


public class NMTimeSWaveletOptions extends javax.swing.JDialog implements ActionListener{
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
        protected ButtonGroupWidget deterministicComponent;
        protected ButtonGroupWidget differencing;
        protected ButtonGroupWidget lagDetermination;
        protected ButtonGroupWidget waveletPresentation;
        protected JLabel maxLagLabel;
        protected JComboBox maxLag;
        protected JLabel waveletTypeLabel;
        protected JComboBox waveletType;
        protected JLabel waveletLevelLabel;
        protected JComboBox waveletLevel;

        private String givenDecomposition;
                protected static DefaultComboBoxModel maxLags  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
                protected static DefaultComboBoxModel waveletTypes  = new DefaultComboBoxModel(
				new String[] { "haar", "d2","d4","d6","d8","d8","d10","d12","d14","d16","d18","d20",
                                                       "s2","s4","s6","s8","s8","s10","s12","s14","s16","s18","s20",
                                               "i2","i6","i14","i18","i20","c6","c12","c18","c24","c30"});
                protected static DefaultComboBoxModel waveletLevels  = new DefaultComboBoxModel(
				new String[] { "1", "2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20"});
                
	
	public NMTimeSWaveletOptions(JFrame frame,GMModel mod,RModel rmod, String specificDecomposition) {
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
                                        waveletPresentation = new ButtonGroupWidget("Presentation",
						new String[]{"DWT",
                                                             "MODWT"});
                                        waveletPresentation.setSelected("DWT");
                                        waveletPresentation.setFont(font12);
                                        getContentPane().add(waveletPresentation, new AnchorConstraint(11, 931, 805, 11, 
						AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
						AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
                                        waveletPresentation.setPreferredSize(new java.awt.Dimension(260, 100));
                                }
                                        

                                {
					waveletTypeLabel = new JLabel();
					getContentPane().add(waveletTypeLabel, new AnchorConstraint(450, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					waveletTypeLabel.setText("Wavelet Type");
                                        waveletTypeLabel.setFont(font12);
					waveletTypeLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}  
                               {
					waveletType = new JComboBox();
					getContentPane().add(waveletType, new AnchorConstraint(500, 931, 805, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					waveletType.setModel(waveletTypes);
                                        waveletType.setFont(font12);
					waveletType.setPreferredSize(new java.awt.Dimension(60, 21));
					waveletType.addActionListener(this);
				}   

                                {
					waveletLevelLabel = new JLabel();
					getContentPane().add(waveletLevelLabel, new AnchorConstraint(450, 487, 982, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					waveletLevelLabel.setText("Number of Levels");
                                        waveletLevelLabel.setFont(font12);
					waveletLevelLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}  
                                {
					waveletLevel = new JComboBox();
					getContentPane().add(waveletLevel, new AnchorConstraint(500, 431, 804, 500,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					waveletLevel.setModel(waveletLevels);
                                        waveletLevel.setSelectedItem("3");
                                        waveletLevel.setFont(font12);
					waveletLevel.setPreferredSize(new java.awt.Dimension(60, 21));
					waveletLevel.addActionListener(this);
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
                model.waveletOptions.waveletPresentation=waveletPresentation.getSelectedItemText();
                model.waveletOptions.waveletType=waveletType.getSelectedItem().toString();
                model.waveletOptions.waveletLevels=waveletLevels.getSelectedItem().toString();
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                model.efeaeplots.moreDiagnosticPlots = "None";
                waveletPresentation.setSelected(model.waveletOptions.waveletPresentation);
                waveletType.setSelectedItem(model.waveletOptions.waveletType);
                waveletLevels.setSelectedItem(model.waveletOptions.waveletLevels);
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