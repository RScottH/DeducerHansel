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
 
 The current file notably uses code from GLMExplorerExport.java in the Deducer package.
 
 The current file made adjustments to that earlier java file on 2015-03-13 to work with the DeducerHansel package.
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

import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.toolkit.DJList;
import org.rosuda.deducer.toolkit.IconButton;
import org.rosuda.deducer.toolkit.OkayCancelPanel;


public class NMTimeSExplorerExport extends javax.swing.JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	private JPanel residPanel;
	private JPanel predPanel;
	private JPanel okayCancel;
	private JCheckBox pred;
        private JCheckBox MRDseries;
	private JCheckBox hat;
	private JCheckBox dffits;
	private JCheckBox dfbeta;
	private JPanel objectsPanel;
	private JLabel modelNameLabel;
	private JCheckBox keepModel;
	private JTextField modelName;
	private JCheckBox covratio;
	
	private JCheckBox linearPred;
	private JCheckBox stResid;
	private JCheckBox sdResid;
	private JCheckBox resid;
	private JPanel diagnosticPanel;
        protected GMModel  model;
        protected Boolean exportFitted = false;
	protected Boolean exportResiduals= false;
	protected Boolean exportMRAseries = false;
        public Boolean hanselUTSExportOkay =false;
        
	
	public NMTimeSExplorerExport(JFrame frame) {
		super(frame);
		initGUI();
                setModel();
		this.setModal(true);
	}
        
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					residPanel = new JPanel();
					getContentPane().add(residPanel);
					residPanel.setBounds(12, 12, 173, 100);
					residPanel.setBorder(BorderFactory.createTitledBorder("Residuals"));
                                        residPanel.setFont(font12);
					residPanel.setLayout(null);
					{
						resid = new JCheckBox();
						residPanel.add(resid);
						resid.setText("Residuals");
                                                resid.setFont(font12);
						resid.setBounds(17, 20, 139, 19);
					}
				}
				{
					predPanel = new JPanel();
					getContentPane().add(predPanel);
					predPanel.setBounds(197, 12, 191, 100);
					predPanel.setBorder(BorderFactory.createTitledBorder("Fitted Values"));
                                        predPanel.setFont(font12);
					predPanel.setLayout(null);
					{
						pred = new JCheckBox();
						predPanel.add(pred);
						pred.setText("Fitted Values");
                                                pred.setFont(font12);
						pred.setBounds(17, 20, 127, 19);
					}
				}
				{
					diagnosticPanel = new JPanel();
					getContentPane().add(diagnosticPanel);
					diagnosticPanel.setBounds(12, 118, 376, 101);
					diagnosticPanel.setBorder(BorderFactory.createTitledBorder("Wavelet"));
                                        diagnosticPanel.setFont(font12);
					diagnosticPanel.setLayout(null);
					{
						MRDseries = new JCheckBox();
						diagnosticPanel.add(MRDseries);
						MRDseries.setText("detail and smooth MRA series");
                                                MRDseries.setFont(font12);
						MRDseries.setBounds(17, 20, 248, 19);
					}
				}
				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(180, 297, 208, 35);
                                        okayCancel.setFont(font12);
				}
			}
			this.setTitle("Save Series");
                        this.setFont(font12);
			this.setSize(400, 366);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateModel(){         
                exportResiduals=resid.isSelected();
                exportFitted =pred.isSelected(); 
                exportMRAseries= MRDseries.isSelected();
	}
	
	public void setModel(){
                resid.setSelected(false);
		pred.setSelected(false);
		MRDseries.setSelected(false);
		pred.setSelected(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd == "Cancel"){
			this.dispose();
		}else if(cmd == "OK"){
                        this.hanselUTSExportOkay=true;
			updateModel();
			this.dispose();
		}
	}
	
	public void setSinglePredicted(){
		linearPred.setVisible(false);
		pred.setText("Predicted");
	}

}

