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
import javax.swing.JOptionPane;
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


public class GMExplorerExport extends javax.swing.JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	private JPanel residPanel;
	private JPanel predPanel;
	private JPanel okayCancel;
	private JCheckBox pred;
	private JCheckBox hat;
	private JCheckBox dffits;
	private JCheckBox dfbeta;
	private JPanel objectsPanel;
	private JLabel modelNameLabel;
	private JCheckBox keepModel;
	private JTextField modelName;
	private JCheckBox covratio;
	private JCheckBox cooks;
	private JCheckBox linearPred;
	private JCheckBox stResid;
	private JCheckBox sdResid;
	private JCheckBox resid;
	private JPanel diagnosticPanel;
        protected GMModel  model;

        
        
	
	public GMExplorerExport(JFrame frame,GMModel mod) {
		super(frame);
		initGUI();
                setModel(mod);
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
					{
						sdResid = new JCheckBox();
						residPanel.add(sdResid);
						sdResid.setText("Standardized");
						sdResid.setFont(font12);
                                                sdResid.setBounds(17, 45, 139, 19);
					}
					{
						stResid = new JCheckBox();
						residPanel.add(stResid);
						stResid.setText("Studentized");
						stResid.setFont(font12);
                                                stResid.setBounds(17, 70, 139, 19);
					}
				}
				{
					predPanel = new JPanel();
					getContentPane().add(predPanel);
					predPanel.setBounds(197, 12, 191, 100);
					predPanel.setBorder(BorderFactory.createTitledBorder("Predicted"));
					predPanel.setFont(font12);
                                        predPanel.setLayout(null);
					{
						pred = new JCheckBox();
						predPanel.add(pred);
						pred.setText("Response");
						pred.setFont(font12);
                                                pred.setBounds(17, 20, 127, 19);
					}
					{
						linearPred = new JCheckBox();
						predPanel.add(linearPred);
						linearPred.setText("Linear");
						linearPred.setFont(font12);
                                                linearPred.setBounds(17, 45, 127, 19);
					}
				}
				{
					diagnosticPanel = new JPanel();
					getContentPane().add(diagnosticPanel);
					diagnosticPanel.setBounds(12, 118, 376, 101);
					diagnosticPanel.setBorder(BorderFactory.createTitledBorder("Diagnostics"));
					diagnosticPanel.setFont(font12);
                                        diagnosticPanel.setLayout(null);
					{
						cooks = new JCheckBox();
						diagnosticPanel.add(cooks);
						cooks.setText("Cooks Distance");
						cooks.setFont(font12);
                                                cooks.setBounds(17, 20, 148, 19);
					}
					{
						dfbeta = new JCheckBox();
						diagnosticPanel.add(dfbeta);
						dfbeta.setText("DFBETA");
						dfbeta.setFont(font12);
                                                dfbeta.setBounds(17, 45, 148, 19);
					}
					{
						dffits = new JCheckBox();
						diagnosticPanel.add(dffits);
						dffits.setText("DFFITS");
						dffits.setFont(font12);
                                                dffits.setBounds(17, 70, 148, 19);
					}
					{
						hat = new JCheckBox();
						diagnosticPanel.add(hat);
						hat.setText("Hat");
						hat.setFont(font12);
                                                hat.setBounds(171, 20, 148, 19);
					}
					{
						covratio = new JCheckBox();
						diagnosticPanel.add(covratio);
						covratio.setText("Cov Ratio");
						covratio.setFont(font12);
                                                covratio.setBounds(171, 45, 188, 19);
					}
				}
				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(180, 297, 208, 35);
					okayCancel.setFont(font12);
                                        {
						objectsPanel = new JPanel();
						getContentPane().add(objectsPanel);
						objectsPanel.setBounds(42, 219, 279, 72);
						objectsPanel.setBorder(BorderFactory.createTitledBorder("Objects"));
						objectsPanel.setFont(font12);
                                                objectsPanel.setLayout(null);
						{
							modelNameLabel = new JLabel();
							objectsPanel.add(modelNameLabel);
							modelNameLabel.setText("Model Name:");
							modelNameLabel.setFont(font12);
                                                        modelNameLabel.setBounds(17, 25, 103, 15);
							modelNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						}
						{
							modelName = new JTextField();
							objectsPanel.add(modelName);
							modelName.setText("<auto>");
							modelName.setFont(font12);
                                                        modelName.setBounds(120, 21, 142, 22);
						}
						{
							keepModel = new JCheckBox();
							objectsPanel.add(keepModel);
							keepModel.setText("Keep");
							keepModel.setFont(font12);
                                                        keepModel.setBounds(120, 43, 106, 19);
						}
					}
				}
			}
			this.setTitle("Save Objects");
                        this.setFont(font12);
			this.setSize(400, 366);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateModel(){
                model.hanselExport.resid=resid.isSelected();
		model.hanselExport.sdresid=sdResid.isSelected();
		model.hanselExport.stresid=stResid.isSelected();
		model.hanselExport.pred=pred.isSelected();
		model.hanselExport.linearPred=linearPred.isSelected();
		model.hanselExport.cooks=cooks.isSelected();
		model.hanselExport.dfbeta=dfbeta.isSelected();
		model.hanselExport.dffits=dffits.isSelected();
		model.hanselExport.hats=hat.isSelected();
		model.hanselExport.covratio=covratio.isSelected();
		model.hanselExport.keepModel=keepModel.isSelected();
		model.hanselExport.modelName=modelName.getText();
	}
	
	public void setModel(GMModel mod){
		model=mod;
                resid.setSelected(false);
		sdResid.setSelected(false);
		stResid.setSelected(false);
		pred.setSelected(false);
		linearPred.setSelected(false);
		cooks.setSelected(false);
		dfbeta.setSelected(false);
		dffits.setSelected(false);
		hat.setSelected(false);
		covratio.setSelected(false);
		keepModel.setSelected(false);
		modelName.setText("<auto>");
                model.hanselExport.okay=false;
                
                if (model.method.equals("lm"))
                    linearPred.setEnabled(false);
                
	}

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd == "Cancel"){
			this.dispose();
		}else if(cmd == "OK"){
                        model.hanselExport.okay=true;
			updateModel();
			this.dispose();
		}
	}
	
	public void setSinglePredicted(){
		linearPred.setVisible(false);
		pred.setText("Predicted");
	}

}

