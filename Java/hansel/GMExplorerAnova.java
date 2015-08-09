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
 
 The current file notably uses the code from GLMExplorerOptions.java in the Deducer package.
 
The current file made adjustments to that earlier java code on 2013-07-04 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06, 2015-08-09.
 */


package hansel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.rosuda.deducer.models.RModel;

import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.deducer.toolkit.OkayCancelPanel;


public class GMExplorerAnova extends javax.swing.JDialog implements ActionListener {
        public Font font12 = new Font("serif", Font.TRUETYPE_FONT, 12);
        public Font font11 = new Font("serif", Font.TRUETYPE_FONT, 11);
        public Font font10 = new Font("serif", Font.TRUETYPE_FONT, 10);
	private JLabel type;
	private JPanel okayCancel;
	private JRadioButton typeOmnibus;
        private JRadioButton typeI;
        private JRadioButton typeII;
	private JRadioButton typeIII;
	private GMModel model;
        protected static String dialogTitle;
	
	public GMExplorerAnova(JFrame frame,GMModel mod,RModel rmod,String cmd) {
		super(frame);
		initGUI();
                dialogTitle = cmd;
		setModel(mod,rmod);
		this.setModal(true);
	}
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
					{
						type = new JLabel();
						getContentPane().add(type);
						type.setText("Type:");
                                                type.setFont(font12);
						type.setBounds(5, 31, 53, 15);
						type.setHorizontalAlignment(SwingConstants.RIGHT);
					}
                                        {
						typeOmnibus = new JRadioButton();
						getContentPane().add(typeOmnibus);
						typeOmnibus.setText("Omnibus");
                                                typeOmnibus.setFont(font12);
						typeOmnibus.setBounds(65, 29, 84, 19);
					}
                                        
                                        {
						typeI = new JRadioButton();
						getContentPane().add(typeI);
						typeI.setText("I  ");
                                                typeI.setFont(font12);
						typeI.setBounds(145, 29, 52, 19);
					}
                                        
					{
						typeII = new JRadioButton();
						getContentPane().add(typeII);
						typeII.setText("II ");
                                                typeII.setFont(font12);
						typeII.setBounds(195, 29, 51, 19);
					}
					{
						typeIII = new JRadioButton();
						getContentPane().add(typeIII);
						typeIII.setText("III");
                                                typeIII.setFont(font12);
						typeIII.setBounds(250, 29, 51, 19);
					}


				}

				{
					okayCancel = new OkayCancelPanel(false,false,this);
					getContentPane().add(okayCancel);
					okayCancel.setBounds(88, 80, 189, 31);
                                        okayCancel.setFont(font12);
				}
			}
			ButtonGroup b = new ButtonGroup();
                        b.add(typeOmnibus);
                        b.add(typeI);
			b.add(typeII);
			b.add(typeIII);
			this.setTitle(dialogTitle+" Table");
                        this.setFont(font12);
			this.setSize(320, 180);
		} catch (Exception e) {
			new ErrorMsg(e);
		}
	}
	
	public void setModel(GMModel mod,RModel rmod){
		model = mod;
		typeOmnibus.setSelected(true);
                if (model.method.equals("ivreg")){
                  typeI.setEnabled(false);
                  typeII.setEnabled(false);
                  typeIII.setEnabled(false);
                } else {
		  typeI.setSelected(mod.efeaetext.type=="I");
                  typeII.setSelected(mod.efeaetext.type=="II");
		  typeIII.setSelected(mod.efeaetext.type=="III");
                }
	}

	public void updateModel(){
                model.efeaetext.anova = true;
                if (typeOmnibus.isSelected())
                    model.efeaetext.type="Omnibus";
                else if (typeI.isSelected())
                    model.efeaetext.type="I";
                else if (typeII.isSelected())
                    model.efeaetext.type="II";
                else 
                    model.efeaetext.type="III";	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd == "OK"){
			updateModel();
			this.dispose();
		}else if(cmd == "Cancel"){
			this.dispose();
		}
		
	}

}
