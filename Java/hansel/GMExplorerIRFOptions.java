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
     GLMExplorerPlots.java, GLMExplorerPostHoc.java, and ModelBuilder.java, found in the Deducer package
 
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




public class GMExplorerIRFOptions extends javax.swing.JDialog implements ActionListener{
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

	protected JCheckBox scaled;
	protected JPanel optionsPanel;
	protected JScrollPane meansScroller;
	protected JButton remove;
	protected JButton add;
	protected JList effects;

	protected GMModel  model;
        
        protected JCheckBox orthogonalized;
        protected JCheckBox cumulative;
        protected JCheckBox bootstrap;
        
       
        protected JLabel naheadLabel;
        protected JComboBox nahead;
        protected static DefaultComboBoxModel naheadsAvailable  = new DefaultComboBoxModel(
				new String[] { "1","2","3","4","5","6","7","8","9","10",
                                               "11","12","13","14","15","16","17","18","19","20",
                                               "21","22","23","24","25","26","27","28","29","30",
                                               "31","32","33","34","35","36","37","38","39","40"});
        
        protected JCheckBox confInt;
        protected JLabel confIntCovLabel;
        protected JComboBox confIntCov;
        protected static DefaultComboBoxModel confIntCovsAvailable  = new DefaultComboBoxModel(
				new String[] { "0.80","0.90","0.95","0.99","0.999"});
        
	
	public GMExplorerIRFOptions(JFrame frame,GMModel mod,RModel rmod) {
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
					naheadLabel = new JLabel();
					getContentPane().add(naheadLabel, new AnchorConstraint(510, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					naheadLabel.setText("Number of periods ahead");
                                        naheadLabel.setFont(font12);
					naheadLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                {
					nahead = new JComboBox();
					getContentPane().add(nahead, new AnchorConstraint(550, 931, 805, 11,
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					nahead.setModel(naheadsAvailable);
                                        nahead.setFont(font12);
					nahead.setPreferredSize(new java.awt.Dimension(60, 21));
					nahead.addActionListener(this);
				} 

                                
                               {
					orthogonalized = new JCheckBox();
					getContentPane().add(orthogonalized, new AnchorConstraint(631, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					orthogonalized.setText("orthogonalized impulse responsed coefficients");
                                        orthogonalized.setFont(font12);
					orthogonalized.setPreferredSize(new java.awt.Dimension(400, 18));
					orthogonalized.addActionListener(this);
				}
                                {
					cumulative = new JCheckBox();
					getContentPane().add(cumulative, new AnchorConstraint(671, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					cumulative.setText("cumulated impulse responsed coefficients");
                                        cumulative.setFont(font12);
					cumulative.setPreferredSize(new java.awt.Dimension(400, 18));
					cumulative.addActionListener(this);
				}        
                                {
					bootstrap = new JCheckBox();
					getContentPane().add(bootstrap, new AnchorConstraint(711, 11, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					bootstrap.setText("bootstrap error bands");
                                        bootstrap.setFont(font12);
					bootstrap.setPreferredSize(new java.awt.Dimension(200, 18));
					bootstrap.addActionListener(this);
				}          
                                {
					confIntCovLabel = new JLabel();
					getContentPane().add(confIntCovLabel, new AnchorConstraint(751, 931, 805, 11,  
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					confIntCovLabel.setText("Coverage of Confidence Interval");
                                        confIntCovLabel.setFont(font12);
					confIntCovLabel.setPreferredSize(new java.awt.Dimension(400, 20));
				}
                                {
					confIntCov = new JComboBox();
					getContentPane().add(confIntCov, new AnchorConstraint(791, 487, 782, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					confIntCov.setModel(confIntCovsAvailable);
                                        confIntCov.setFont(font12);
					confIntCov.setPreferredSize(new java.awt.Dimension(60, 21));
					confIntCov.addActionListener(this);
				} 
                           
                                
                                {
					impulsePanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					impulsePanel.setLayout(varPanelLayout);
					getContentPane().add(impulsePanel, new AnchorConstraint(11, 375, 725, 11, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					impulsePanel.setBorder(BorderFactory.createTitledBorder("Impulse Variable (highlight one)"));
					impulsePanel.setPreferredSize(new java.awt.Dimension(215, 130));
                                        impulsePanel.setFont(font12);
					{
						impulseScroller = new JScrollPane();
						impulsePanel.add(impulseScroller, BorderLayout.CENTER);
						{
							ListModel impulsevarListModel = 
								new DefaultListModel();
							impulseList = new JList();
							impulseScroller.setViewportView(impulseList);
							impulseList.setModel(impulsevarListModel);
                                                        impulseList.setFont(font12);
						}
					}
				} 
                               	{
					termsPanel = new JPanel();
					BorderLayout varPanelLayout = new BorderLayout();
					termsPanel.setLayout(varPanelLayout);
					getContentPane().add(termsPanel, new AnchorConstraint(11, 375, 725, 500, 
							AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, 
							AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
					termsPanel.setBorder(BorderFactory.createTitledBorder("Response Variables (highlight)"));
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

				}
			}
			this.setTitle("Impulse Response Functions");
                        this.setFont(font12);
			this.setSize(632, 460);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void updateModel(){
            model.irfOptions.nahead = nahead.getSelectedItem().toString();
            model.irfOptions.confIntCov=confIntCov.getSelectedItem().toString();
            model.irfOptions.orthogonalized=orthogonalized.isSelected();
            model.irfOptions.cumulative= cumulative.isSelected();
            model.irfOptions.bootstrap = bootstrap.isSelected();
            model.irfOptions.impulseSelection = impulseList.getSelectedIndices();
            model.irfOptions.responseSelection = termsList.getSelectedIndices();
	}
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
                impulseList.setModel(model.termsFinalStage);
                termsList.setModel(model.termsFinalStage);
                if (!(model.irfOptions.impulseSelection[0]==-1)) {
                  impulseList.setSelectedIndices(model.irfOptions.impulseSelection);
                  termsList.setSelectedIndices(model.irfOptions.responseSelection);                  
                }
                model.efeaeplots.moreDiagnosticPlots = "None";

               nahead.setSelectedItem(model.irfOptions.nahead);
               confIntCov.setSelectedItem(model.irfOptions.confIntCov);
               orthogonalized.setSelected(model.irfOptions.orthogonalized);
               cumulative.setSelected(model.irfOptions.cumulative);
               bootstrap.setSelected(model.irfOptions.bootstrap);
        }

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd=="Cancel"){
			this.dispose();
		}else if(cmd=="OK"){
                     
                       Object[] impulseTerm = impulseList.getSelectedValuesList().toArray();
                        if (impulseTerm==null || !(impulseTerm.length==1))
                         {
				JOptionPane.showMessageDialog(null, "Please select one variable for impulse");
				return;
			 }
                       
             	       Object[] plotTerms = termsList.getSelectedValuesList().toArray();
		       if (plotTerms==null || plotTerms.length<1)
                         {
				JOptionPane.showMessageDialog(null, "Please select at least one response variable");
				return;
			 }
                       
                        model.efeaeplots.plotImpulseResponseFunction = true;
                        model.efeaeplots.impulseToPlot = (String) impulseTerm[0];
                        
                        model.efeaeplots.responsesToPlot = "\"" + (String) plotTerms[0] + "\"";
			for(int i =1;i<plotTerms.length;i++){
				model.efeaeplots.responsesToPlot+=",\""+plotTerms[i]+"\"";
			}

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