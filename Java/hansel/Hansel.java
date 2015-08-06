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
 The code in the files of those packages is covered by the GPLv2 licenses for those packages.
 
 The current file most notably uses code from Deducer.java in the Deducer package.

The current file made adjustments to that earlier java code on 2013-07-07 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */
package hansel;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.UIManager;


import org.rosuda.JGR.JGR;
import org.rosuda.JGR.RController;
import org.rosuda.JGR.robjects.RObject;
import org.rosuda.JGR.toolkit.FileSelector;
import org.rosuda.JGR.toolkit.PrefDialog;
import org.rosuda.JGR.util.ErrorMsg;


import org.rosuda.deducer.menu.*;
import org.rosuda.deducer.menu.twosample.TwoSampleDialog;
import org.rosuda.deducer.models.*;
import org.rosuda.deducer.plots.*;
import org.rosuda.deducer.toolkit.DeducerPrefs;
import org.rosuda.deducer.toolkit.HelpButton;
import org.rosuda.deducer.toolkit.LoadData;
import org.rosuda.deducer.toolkit.PrefPanel;
import org.rosuda.deducer.toolkit.SaveData;
import org.rosuda.deducer.toolkit.VariableSelectionDialog;
import org.rosuda.deducer.data.DataFrameSelector;
import org.rosuda.deducer.data.DataViewer;
import org.rosuda.JRI.Rengine;

import org.rosuda.deducer.RConnector;
import org.rosuda.deducer.DefaultRConnector;
import org.rosuda.deducer.JGRConnector;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.WindowTracker;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPLogical;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.JRI.JRIEngine;

import org.rosuda.ibase.Common;
import org.rosuda.ibase.toolkit.EzMenuSwing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;


public class Hansel {
	ConsoleListener cListener =  new ConsoleListener();
	static final int MENUMODIFIER = Common.isMac() ? Event.META_MASK : Event.CTRL_MASK;
	static int menuIndex=3;
	static String recentActiveData = "";
	static final String Version= "0.4-4";
	public static String guiEnv = "gui.working.env";
        public static String hanselEnv = ".hansel.working.env";
	public static boolean insideJGR;
	public static boolean started;
	private static RConnector rConnection = null;
	
        
	public Hansel(){
		started=false;
		Common.getScreenRes(); //initializes value if not already set
                
		try{
                    startNoJGR();
                          if(JGR.isJGRmain()){
				startWithJGR();
				(new Thread() {
					public void run() {
						new Thread(new DataRefresher()).start();
					}
				}).start();

			}
                        
		}catch(Exception e){
			new ErrorMsg(e);
		}	
	}
	
	public void startNoJGR(){
		try{
			insideJGR=false;

		    String nativeLF = UIManager.getSystemLookAndFeelClassName();
		    try {
		        UIManager.setLookAndFeel(nativeLF);
		    } catch (InstantiationException e) {
		    } catch (ClassNotFoundException e) {
		    }  catch (IllegalAccessException e) {
		    }
			org.rosuda.util.Platform.initPlatform("org.rosuda.JGR.toolkit.");
			
			try {
                            rConnection = new DefaultRConnector(new JRIEngine(Rengine.getMainEngine()));
                  
			} catch (REngineException e) {
				new ErrorMsg(e);
			}
			
			
			DeducerPrefs.initialize();
			
			started=true;

			new Thread(new DataRefresher()).start();
		}catch(Exception e){new ErrorMsg(e);}
	}
	
	public void startWithJGR(){
		insideJGR=true;
		rConnection = new JGRConnector();
		String dataMenu = "Hansel Data";
		try{
			DeducerPrefs.initialize();
		    
		    insertMenu(JGR.MAINRCONSOLE,"Hansel Analysis",menuIndex+4);
		    EzMenuSwing.addJMenuItem(JGR.MAINRCONSOLE, "Hansel Analysis", "Ordinary Least Squares", "Ordinary Least Squares", cListener);
		    EzMenuSwing.addJMenuItem(JGR.MAINRCONSOLE, "Hansel Analysis", "Two-Stage Least Squares", "Two-Stage Least Squares", cListener);
                    EzMenuSwing.addJMenuItem(JGR.MAINRCONSOLE, "Hansel Analysis", "Weighted Least Squares", "Weighted Least Squares", cListener);	
		    JMenu sm = new JMenu("Non-linear Models");
			sm.setMnemonic(KeyEvent.VK_S);
			String[] labels = new String[] {"Binary Logit","Binary Probit","Tobit"};
			String[] cmds = new String[] {"Binary Logit","Binary Probit","Tobit"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
			}
			JMenu menu = EzMenuSwing.getMenu(JGR.MAINRCONSOLE, "Hansel Analysis");
			menu.add(sm);

                        sm = new JMenu("Cross-Sectional Spatial Data");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"Explore Cross-Sectional Spatial Data", "Spatial simultaneous autoregressive error model - maximum likelihood",
                                               "Error Durbin model - maximum likelihood",
                                               "Spatial simultaneous autoregressive error model - GMM","Spatial simultaneous autoregressive lag model - maximum likelihood",
                                               "Spatial Durbin model - maximum likelihood","generalized spatial two-stage least squares",
                                               "Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood",
                                               "SAC/SARAR Durbin model - maximum likelihood","Spatial simultaneous autoregressive SAC model - GMM",
                                               "Spatial conditional and simultaneous autoregression",
                                               "Matrix exponential spatial lag model"};
			cmds = new String[] {"Explore Cross-Sectional Spatial Data", "Spatial simultaneous autoregressive error model - maximum likelihood",
                                               "Error Durbin model - maximum likelihood",
                                               "Spatial simultaneous autoregressive error model - GMM","Spatial simultaneous autoregressive lag model - maximum likelihood",
                                               "Spatial Durbin model - maximum likelihood","generalized spatial two-stage least squares",
                                               "Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood",
                                               "SAC/SARAR Durbin model - maximum likelihood","Spatial simultaneous autoregressive SAC model - GMM",
                                               "Spatial conditional and simultaneous autoregression",
                                               "Matrix exponential spatial lag model"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
                                if ((i==0)||(i==3)||(i==6)||(i==9))
                                    sm.addSeparator();
			}
			menu.add(sm);
			
			sm = new JMenu("Time Series");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"Explore Time Series","Chart Financial Time Series", "Vector Autoregression",
                                               "Cointegration Test - Johansen","Cointegration Test - Philips & Ouliaris","Cointegration Test - Engle & Granger"};
			cmds = new String[] {"Explore Time Series","Chart Financial Time Series","Vector Autoregression",
                                               "Cointegration Test - Johansen","Cointegration Test - Philips & Ouliaris","Cointegration Test - Engle & Granger"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
                                if ((i==0)||(i==1)||(i==2))
                                    sm.addSeparator();
			}
			menu.add(sm);
                        
                        sm = new JMenu("Panel");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"Explore Panel Series","Panel Linear Model",
                                               "Variable Coefficients","General Feasible GLS","GMM (e.g. for dynamic equations)",
                                               "Common Correlated Effects Pooled Estimator","Mean Groups",
                                               "Spatial panel model - maximum likelihood","Spatial panel model w/ random effects & serial error correlation- max likelihood","Spatial panel model - GM"};
			cmds = new String[] {"Explore Panel Series","Panel Linear Model",
                                               "Variable Coefficients","General Feasible GLS","GMM (e.g. for dynamic equations)",
                                               "Common Correlated Effects Pooled Estimator","Mean Groups",
                                               "Spatial panel model - maximum likelihood","Spatial panel model w/ random effects & serial error correlation- max likelihood","Spatial panel model - GM"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
                                if ((i==0)||(i==6))
                                    sm.addSeparator();
			}
			menu.add(sm);
                        
                        menu.addSeparator();
                        EzMenuSwing.addJMenuItem(JGR.MAINRCONSOLE, "Hansel Analysis", "Explore previously estimated model", "Explore previously estimated model", cListener);
                        menu.addSeparator();

                        sm = new JMenu("Data Retrieval");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"Open data","Clipboard -> data frame","Download data from OANDA","Download data from St. Louis FRED","Download data from Yahoo!","Load sample dataset"};
			cmds = new String[] {"Open data","Clipboard -> data frame","Download data from OANDA","Download data from St. Louis FRED","Download data from Yahoo!","Load sample dataset"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
			}
			menu.add(sm); 
                        
                        sm = new JMenu("Data Tools");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"Data frame -> time series (T.S.) mirror","Zoo, ts object -> data frame mirror","Spatial object -> data frame mirror","Spatial File Associations"};
			cmds = new String[] {"Data frame -> time series (T.S.) mirror","Zoo, ts object -> data frame mirror","Spatial object -> data frame mirror","Spatial File Associations" };
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
			}
			menu.add(sm);
                          
                        menu.addSeparator();
                        
                        sm = new JMenu("About DeducerHansel");
			sm.setMnemonic(KeyEvent.VK_S);
			labels = new String[] {"General information","Warranty","License"};
			cmds = new String[] {"General information","Warranty","License"};
			for(int i = 0;i<labels.length;i++){
				JMenuItem mi = new JMenuItem();
				mi.setText(labels[i]);
				mi.setActionCommand(cmds[i]);
				mi.addActionListener(cListener);
				sm.add(mi);
                        }
                        menu.add(sm);
                        menu.addSeparator();                        
			
		    menuIndex++;
	    	
			//Replace DataTable with Data Viewer
			JGR.MAINRCONSOLE.getJMenuBar().getMenu(menuIndex).remove(1);
			insertJMenuItem(JGR.MAINRCONSOLE, "Packages & Data", "Data Viewer", "table", cListener, 1);

			//Override New Data with Data Viewer enabled version
			JGR.MAINRCONSOLE.getJMenuBar().getMenu(0).remove(0);
			insertJMenuItem(JGR.MAINRCONSOLE, "File", "New Data", "New Data Set", cListener, 0);
			
			//Override Open Data with Data Viewer enabled version
			JGR.MAINRCONSOLE.getJMenuBar().getMenu(0).remove(1);
			insertJMenuItem(JGR.MAINRCONSOLE, "File", "Open Data", "Open Data Set", cListener, 1);
			JMenuItem open = (JMenuItem)JGR.MAINRCONSOLE.getJMenuBar().getMenu(0).getMenuComponent(1);
			open.setAccelerator(KeyStroke.getKeyStroke('L',MENUMODIFIER));
			
			//Save Data
			insertJMenuItem(JGR.MAINRCONSOLE, "File", "Save Data", "Save Data Set", cListener, 2);
			
			//help
			EzMenuSwing.addJMenuItem(JGR.MAINRCONSOLE, "Help", "Deducer Help", "dhelp", cListener);
			
			//preferences
			PrefPanel prefs = new PrefPanel();
			PrefDialog.addPanel(prefs, prefs);
			started=true;
		}catch(Exception e){
			e.printStackTrace();
			new ErrorMsg(e);}		
	}
	
	public static boolean isJGR(){
		
		return insideJGR;
	}
	
	public void detach(){
		JMenuBar mb = JGR.MAINRCONSOLE.getJMenuBar();
		for(int i=0;i<mb.getMenuCount();i++){
			if(mb.getMenu(i).getText().equals("Data") ||
					mb.getMenu(i).getText().equals("Analysis")){
				mb.remove(i);
				i--;
			}
		}
	}
	
	public static void startViewerAndWait(){
	   	DataViewer inst = new DataViewer();
    	inst.setLocationRelativeTo(null);
    	inst.setVisible(true);
    	while(inst.isVisible()==false){
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
    	}
	}

	public static String addSlashes(String str){
		if(str==null) return "";

		StringBuffer s = new StringBuffer(str);
		for(int i = 0; i < s.length(); i++){
			if(s.charAt (i) == '\\')
				s.insert(i++, '\\');
			else if(s.charAt (i) == '\"')
				s.insert(i++, '\\');
			else if(s.charAt (i) == '\'')
				s.insert(i++, '\\');
		}
		
		return s.toString();
	}

	class ConsoleListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String cmd = arg0.getActionCommand();
			runCmdThreaded(cmd);
		}
	}
	
	public static void runCmdThreaded(String cmd){
		final String c = cmd;
		(new Thread() {
			public void run() {runCmd(c,false);					}
		}).start();
	}
		
	public static void runCmd(String cmd,boolean fromConsole){
		boolean needsRLocked=false;
      
		if(cmd.equals("dhelp")||cmd.equals("Deducer Help")  ){
			HelpButton.showInBrowser(HelpButton.baseUrl+"pmwiki.php?n=Main.DeducerManual");
                } else if(cmd.equals("Deducer Online Help")){
                        Deducer.execute("deducer('Deducer Online Help')");
		} else if(cmd.equals("New Data Set")||cmd.equals("New Data")){
			String inputValue = JOptionPane.showInputDialog("Data Name: ");
			if(inputValue!=null){
				String var = RController.makeValidVariableName(inputValue.trim());
                                Deducer.execute(var+"<-data.frame()");
			}
		}else if (cmd.equals("Open Data Set")||cmd.equals("Open Data")){
			//needsRLocked=true;
                        if (isJGR()){
                            LoadData inst = new LoadData();
                            //DataFrameWindow.setTopDataWindow(inst.getDataName());
                            String newTitle = inst.getTitle();
                            String newName = inst.getName();
                            String newDataName = inst.getDataName();
                            Deducer.execute(inst.getDataName());
                        } else {
                            DataRetrievalOpenData sfl = new DataRetrievalOpenData();
                            sfl.setVisible(true);
                            sfl.runInR();  
                        }
		}else if(cmd.equals("Save Data Set")||cmd.equals("Save Data")){
			//needsRLocked=true;
			RObject data = (new DataFrameSelector(JGR.MAINRCONSOLE)).getSelection();
			if(data!=null){
				SaveData inst = new SaveData(data.getName());
				//WindowTracker.addWindow(inst);
			}
		}else if(cmd.equals("recode")||cmd.equals("Recode Variables")){
			needsRLocked=true;
			RecodeDialog recode =new RecodeDialog(JGR.MAINRCONSOLE); 
			recode.setLocationRelativeTo(null);
			recode.setVisible(true);
			WindowTracker.addWindow(recode);
		}else if(cmd.equals("transform")||cmd.equals("Transform")){
			needsRLocked=true;
			TransformDialog trans =new TransformDialog(JGR.MAINRCONSOLE); 
			trans.setLocationRelativeTo(null);
			trans.setVisible(true);
			WindowTracker.addWindow(trans);
		}else if(cmd.equals("factor")||cmd.equals("Edit Factor")){
			needsRLocked=true;
			VariableSelectionDialog inst =new VariableSelectionDialog(JGR.MAINRCONSOLE);
			inst.SetSingleSelection(true);
			inst.setLocationRelativeTo(null);
			inst.setRFilter("is.factor");
			inst.setTitle("Select Factor to Edit");
			inst.setVisible(true);
			String variable = inst.getSelecteditem();
			if(variable==null)
				return;
			FactorDialog fact = new FactorDialog(JGR.MAINRCONSOLE,variable);
			fact.setLocationRelativeTo(null);
			fact.setVisible(true);
			WindowTracker.addWindow(fact);
		}else if(cmd.equals("reset rows")||cmd.equals("Reset Row Names")){
			String name = null;
			RObject data = null;
			DataFrameSelector sel = new DataFrameSelector(JGR.MAINRCONSOLE);
			data = sel.getSelection();
			if(data!=null){
				name = data.getName();
				execute("rownames("+name+") <-1:dim("+name+")[1]");
				//DataFrameWindow.setTopDataWindow(name);
			}
			JGR.MAINRCONSOLE.toFront();
		}else if(cmd.equals("sort")||cmd.equals("Sort")){
			needsRLocked=true;
			SortDialog sort = new SortDialog(JGR.MAINRCONSOLE);
			sort.setLocationRelativeTo(null);
			sort.setVisible(true);
			WindowTracker.addWindow(sort);
		}else if(cmd.equals("merge")||cmd.equals("Merge Data")){
			needsRLocked=true;
			MergeDialog merge =new MergeDialog(JGR.MAINRCONSOLE); 
			merge.setLocationRelativeTo(null);
			merge.setVisible(true);
			WindowTracker.addWindow(merge);
		}else if (cmd.equals("trans")||cmd.equals("Transpose")){
			String name = null;
			RObject data = null;
			DataFrameSelector sel = new DataFrameSelector(JGR.MAINRCONSOLE);
			data = sel.getSelection();
			if(data!=null){
				name = data.getName();
				execute(name+"<-as.data.frame(t("+name+"))");
				//DataFrameWindow.setTopDataWindow(name);
			}
		}else if(cmd.equals("subset")||cmd.equals("Subset")){
			needsRLocked=true;
			SubsetDialog sub = new SubsetDialog(JGR.MAINRCONSOLE);
			sub.setLocationRelativeTo(null);
			sub.setVisible(true);
			WindowTracker.addWindow(sub);
		}else if(cmd.equals("frequency")||cmd.equals("Frequencies")){
			needsRLocked=true;
			FrequencyDialog freq = new FrequencyDialog(JGR.MAINRCONSOLE);
			WindowTracker.addWindow(freq);
			freq.setLocationRelativeTo(null);
			freq.setVisible(true);
		}else if(cmd.equals("descriptives")){
			needsRLocked=true;
			DescriptivesDialog desc = new DescriptivesDialog(JGR.MAINRCONSOLE);
			desc.setLocationRelativeTo(null);
			desc.setVisible(true);
			WindowTracker.addWindow(desc);
		}else if(cmd.equals("contingency")||cmd.equals("Contingency Tables")){
			needsRLocked=true;
			ContingencyDialog cont = new ContingencyDialog(JGR.MAINRCONSOLE);
			cont.setLocationRelativeTo(null);
			cont.setVisible(true);
			WindowTracker.addWindow(cont);
		}else if (cmd.equals("table")){
			needsRLocked=true;
			DataViewer inst = new DataViewer();
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);
			WindowTracker.addWindow(inst);
		}else if(cmd.equals("onesample")||cmd.equals("One Sample Test")){
			needsRLocked=true;
			OneSampleDialog inst = new OneSampleDialog(JGR.MAINRCONSOLE);
			inst.setLocationRelativeTo(JGR.MAINRCONSOLE);
			inst.setVisible(true);
			WindowTracker.addWindow(inst);
		}else if(cmd.equals("two sample")||cmd.equals("Two Sample Test")){
			needsRLocked=true;
			TwoSampleDialog inst = new TwoSampleDialog(JGR.MAINRCONSOLE);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);	
			WindowTracker.addWindow(inst);
		}else if(cmd.equals("ksample")||cmd.equals("K-Sample Test")){
			needsRLocked=true;
			KSampleDialog inst = new KSampleDialog(JGR.MAINRCONSOLE);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);
			WindowTracker.addWindow(inst);
                }else if(cmd.equals("Paired Test")){        
                        Deducer.timedEval(".getDialog('Paired Test')$run()");
		}else if(cmd.equals("corr")||cmd.equals("Correlation") ){
			needsRLocked=true;
			CorDialog inst = new CorDialog(JGR.MAINRCONSOLE);
			inst.setLocationRelativeTo(null);
			inst.setVisible(true);			
			WindowTracker.addWindow(inst);
		}else if(cmd.equals("glm")||cmd.equals("Generalized Linear Model") ){
			needsRLocked=true;
			GLMDialog d = new GLMDialog(JGR.MAINRCONSOLE);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
			WindowTracker.addWindow(d);
		}else if(cmd.equals("logistic")||cmd.equals("Logistic Model")){
			needsRLocked=true;
			LogisticDialog d = new LogisticDialog(JGR.MAINRCONSOLE);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
			WindowTracker.addWindow(d);
		}else if(cmd.equals("linear")||cmd.equals("Linear Model") ){
			needsRLocked=true;
			LinearDialog d = new LinearDialog(JGR.MAINRCONSOLE);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
			WindowTracker.addWindow(d);
		}else if(cmd.equals("Plot Builder")){
			needsRLocked=true;
			PlotBuilder d = new PlotBuilder();
			d.setLocationRelativeTo(null);
			d.setVisible(true);
			WindowTracker.addWindow(d);			
		}else if(cmd.equals("Open Plot")){
                    DataRetrievalOpenggp sfl = new DataRetrievalOpenggp();
			sfl.setVisible(true);
			sfl.runInR();   
		}else if(cmd.equals("Import Template")){
                    DataRetrievalOpenggtmpl sfl = new DataRetrievalOpenggtmpl();
			sfl.setVisible(true);
			sfl.runInR();  
		}else if(cmd.equals("histogram")||	cmd.equals("pie")		|| 
				cmd.equals("bar") 		||	cmd.equals("boxplot")	||
				cmd.equals("scatter")	||	cmd.equals("line")		||
				cmd.equals("bubble") 	||	cmd.equals("series") ||
                        
                                cmd.equals("grouped bar") 	||	cmd.equals("density") ||
                                cmd.equals("grouped density") 	||	cmd.equals("simple dotplot") ||
                                cmd.equals("grouped dotplot") 	||	cmd.equals("series") ||
                                       	                                 cmd.equals("mean") ||
                                cmd.equals("line") 	||	cmd.equals("grouped line") || 
                                cmd.equals("grouped density") 	||	cmd.equals("simple dotplot") ||
                                cmd.equals("simple boxplot") 	||	cmd.equals("grouped boxplot") ||
                                cmd.equals("scatter smooth") 	||	cmd.equals("histogram 2d")
                                
				){
			try{
				PlotController.init();
				PlottingElementMenuDialog d = PlotController.getMenuDialog(cmd.replace(" ","_"));
				d.setLocationRelativeTo(null);
				d.setVisible(true);
			}catch(Exception e){e.printStackTrace();}
                        
               	}else if(cmd.equals("iplot")||cmd.equals("Scatter")){
			Deducer.timedEval(".getDialog('Interactive Scatter Plot')$run()");
		}else if(cmd.equals("ihist")||cmd.equals("histogram")){
			Deducer.timedEval(".getDialog('Interactive Histogram')$run()");
		}else if(cmd.equals("ibar")||cmd.equals("Bar")){
			Deducer.timedEval(".getDialog('Interactive Bar Plot')$run()");
		}else if(cmd.equals("iboxl")||cmd.equals("Box-plot (long)")){
			Deducer.timedEval(".getDialog('Interactive Box Plot Long')$run()");
		}else if(cmd.equals("iboxw")||cmd.equals("Box-plot (wide)")){
			Deducer.timedEval(".getDialog('Interactive Box Plot Wide')$run()");
		}else if(cmd.equals("imosaic")||cmd.equals("Mosaic")){
			Deducer.timedEval(".getDialog('Interactive Mosaic Plot')$run()");
		}else if(cmd.equals("ipcp")||cmd.equals("Parallel Coordinate")){
			Deducer.timedEval(".getDialog('Interactive Parallel Coordinate Plot')$run()");      
                }else if(cmd.equals("Data Viewer")){
                      DataViewer inst = new DataViewer();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);    
               	}else if(cmd.equals("Explore previously estimated model")){
			Deducer.eval("J('hansel.GMPriorModelDialog')$runit(\"Explore previously estimated model\","+
                                     "c(Filter( function(x) 'lm' %in% class( get(x) )[1], ls() ) , Filter( function(x) 'glm' %in% class( get(x) ), ls() ), Filter( function(x) 'ivreg' %in% class( get(x) ), ls() ) ,Filter( function(x) 'panelmodel' %in% class( get(x) ), ls() ), Filter( function(x) 'varest' %in% class( get(x) ), ls() ),Filter( function(x) 'ca.jo' %in% class( get(x) )[1], ls() ) ,Filter( function(x) 'ca.po' %in% class( get(x) )[1], ls() ),\" \",\" \"),"+
                                     "c(Filter( function(x) 'data.frame' %in% class( get(x) )[1], ls() ),\" \",\" \") )  ");
               	}else if(cmd.equals("Data frame -> time series (T.S.) mirror") ){
                        Deducer.eval(".hansel.temp.available.series <-"+
                                     "Filter( function(x) 'data.frame' %in% class( get(x) )[1], ls() )");
                        Deducer.eval("if (length(.hansel.temp.available.series)>0){J('hansel.DToolsMirrorsfromDframe')$runit(\"df to ts\",c(.hansel.temp.available.series,\" \",\" \"))} else {writeLines(\"No data frame found.\")}");
                        Deducer.eval("rm(.hansel.temp.available.series)"); 
                }else if(cmd.equals("Zoo, ts object -> data frame mirror")){
			Deducer.eval(".hansel.temp.available.series <-"+
                         "c(Filter( function(x) ('ts' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ) ,"+
                         "Filter( function(x)  ('mts' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),"+
                         "Filter( function(x)  ('xts' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),"+
                         "Filter( function(x)  ('zooreg' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() ),"+
                         "Filter( function(x)  ('zoo' %in% class( get(x) )[1] & !(regexpr('T.S.', x)>0)), ls() )) ");
                        Deducer.eval("if (length(.hansel.temp.available.series)>0){J('hansel.DToolsMirrorsFromZoots')$runit(\"ts to df\",c(.hansel.temp.available.series,\" \"))} else {writeLines(\"No time series data object found.\")}");
                        Deducer.eval("rm(.hansel.temp.available.series)"); 
                }else if(cmd.equals("Spatial object -> data frame mirror")){
                 Deducer.eval("suppressMessages(library(quantmod))");
                            Deducer.eval(".hansel.temp.available.series <-c(Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                             "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                             "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                             "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                             "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() )) ");
                            Deducer.eval("if (length(.hansel.temp.available.series)>0){J('hansel.DToolsMirrorsFromSpatialData')$runit(\"spatial to df\",c(.hansel.temp.available.series,\" \"))} else {writeLines(\"No spatial data object found.\")}");
                            Deducer.eval("rm(.hansel.temp.available.series)");  
			/*Deducer.eval("J('hansel.DToolsMirrorsFromSpatialData')$runit(\"spatial to df\","+
                 "c(Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                 "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                 "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                 "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                 "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),\" \")   ) "); */        
                }else if(cmd.equals("Spatial File Associations")){
                    Deducer.execute("suppressMessages(library(spdep))");
			Deducer.eval("J('hansel.DToolsSpatialFileAssociations')$runit(\"spatial file associations\","+
                 "c(Filter( function(x)  ('data.frame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),\" \"),"+
                 
                 "c(Filter( function(x)  ('listw' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('matrix' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('nb' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                   "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() )," 
                                + "\" \"),"+
                                
                 "c(Filter( function(x)  ('listw' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('matrix' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('nb' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x) ('SpatialPolygonsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ) ,"+
                   "Filter( function(x)  ('SpatialLinesDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialGridDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPixelsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() ),"+
                   "Filter( function(x)  ('SpatialPointsDataFrame' %in% class( get(x) )[1] & !(regexpr('SPD.', x)>0)), ls() )," 
                                + "\" \")"
                                + "   ) ");
                }else if(cmd.equals("Load sample dataset")){
                        Deducer.eval(".hansel.temp.available.datasets <-sort(paste(\"[\",data(package = .packages(all.available = TRUE))$results[,][,1],\"] \",  data(package = .packages(all.available = TRUE))$results[,][,3],\" -- \",data(package = .packages(all.available = TRUE))$results[,][,4],sep=\"\"))");
                        Deducer.eval(".hansel.temp.available.datasets <- .hansel.temp.available.datasets[(!grepl(\") --\",.hansel.temp.available.datasets))]");
                        Deducer.eval("J('hansel.DToolsDataFromPackage')$runit(\"Load sample dataset\",c(.hansel.temp.available.datasets)  )");
                        Deducer.eval("rm(.hansel.temp.available.datasets)");
                }else if(cmd.equals("Open data")||cmd.equals("Open Data")){
                    DataRetrievalOpenData sfl = new DataRetrievalOpenData();
			sfl.setVisible(true);
			sfl.runInR();  
                 }else if(cmd.equals("Clipboard -> data frame")){
                     String newDataFrameName = JOptionPane.showInputDialog("What do you want for the name of the new data frame?", Deducer.getUniqueName("xxx"));
                     int opt = JOptionPane.showConfirmDialog(null, "Use first row for variable names?");
                        Deducer.execute(newDataFrameName +"<- read.DIF(\"clipboard\",transpose=TRUE,header="+((opt == JOptionPane.OK_OPTION)?"TRUE":"FALSE")+")");                       
                        
                }else if(cmd.equals("Download data from OANDA")||cmd.equals("Download data from St. Louis FRED")||cmd.equals("Download data from Yahoo!")){
                 Deducer.eval("library(quantmod)");
                 Deducer.eval("J(\"hansel.DToolsDataFromWeb\")$runit(\""+cmd+"\")");
          	}else if(cmd.equals("General information")){
                        String part1 = "DeducerHansel v. 0.4\n"
                                     + "Copyright (C) 2015 R. Scott Ha";
                        String part2 = "cker,\nProfessor in Applied Econometrics and International Economics\n"+
                                       "Jönköping University\nJönköping International Business School\n\n"+
                                       "This package comes with ABSOLUTELY NO WARRANTY; for details\n"+
                                       "click on the menu item 'Hansel Analysis>About DeducerHansel>Warranty'.\n"+
                                       "This is free software, and you are welcome to redistribute it under certain\n" +
                                       "conditions covered by the GNU General Public License version 2 (GPLv2)  license;\n"+
                                       "for details click on the menu item 'Hansel Analysis>About DeducerHansel>License'.";
			JOptionPane.showMessageDialog(null,part1+part2);
                        
                 }else if(cmd.equals("Warranty")){
                    Deducer.eval("writeLines('\n"+
                    "           DeducerHansel comes with ABSOLUTELY NO WARRANTY\n\n"+
                    "BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY\n"+
                    "FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN\n"+
                    "OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES\n"+
                    "PROVIDE THE PROGRAM \"AS IS\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED\n"+
                    "OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF\n"+
                    "MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS\n"+
                    "TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE\n"+
                    "PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,\n"+
                    "REPAIR OR CORRECTION.\n\n"+

                    "IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING\n"+
                    "WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR\n"+
                    "REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,\n"+
                    "INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING\n"+
                    "OUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED\n"+
                    "TO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY\n"+
                    "YOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER\n"+
                    "PROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE\n"+
                    "POSSIBILITY OF SUCH DAMAGES.')");                         
                        
                        
                    }else if(cmd.equals("License")){

                    String LicenseStart="\nThe DeducerHansel package is covered by the\n"+
                             "GNU General Public License version 2 (GPLv2) license, which is given below.\n"+
                    "\n"+       
                    "                GNU GENERAL PUBLIC LICENSE\n"+
                    "		       Version 2, June 1991\n"+
                    "\n"+
                    " Copyright (C) 1989, 1991 Free Software Foundation, Inc.\n"+
                    "                       51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA\n"+
                    " Everyone is permitted to copy and distribute verbatim copies\n"+
                    " of this license document, but changing it is not allowed.\n"+
                    "\n"+
                    "			    Preamble\n"+
                    "\n"+
                    "  The licenses for most software are designed to take away your\n"+
                    "freedom to share and change it.  By contrast, the GNU General Public\n"+
                    "License is intended to guarantee your freedom to share and change free\n"+
                    "software--to make sure the software is free for all its users.  This\n"+
                    "General Public License applies to most of the Free Software\n"+
                    "Foundation's software and to any other program whose authors commit to\n"+
                    "using it.  (Some other Free Software Foundation software is covered by\n"+
                    "the GNU Library General Public License instead.)  You can apply it to\n"+
                    "your programs, too.\n"+
                    "\n"+
                    "  When we speak of free software, we are referring to freedom, not\n"+
                    "price.  Our General Public Licenses are designed to make sure that you\n"+
                    "have the freedom to distribute copies of free software (and charge for\n"+
                    "this service if you wish), that you receive source code or can get it\n"+
                    "if you want it, that you can change the software or use pieces of it\n"+
                    "in new free programs; and that you know you can do these things.\n"+
                    "\n"+
                    "  To protect your rights, we need to make restrictions that forbid\n"+
                    "anyone to deny you these rights or to ask you to surrender the rights.\n"+
                    "These restrictions translate to certain responsibilities for you if you\n"+
                    "distribute copies of the software, or if you modify it.\n"+
                    "\n"+
                    "  For example, if you distribute copies of such a program, whether\n"+
                    "gratis or for a fee, you must give the recipients all the rights that\n"+
                    "you have.  You must make sure that they, too, receive or can get the\n"+
                    "source code.  And you must show them these terms so they know their\n"+
                    "rights.\n"+
                    "\n"+
                    "  We protect your rights with two steps: (1) copyright the software, and\n"+
                    "(2) offer you this license which gives you legal permission to copy,\n"+
                    "distribute and/or modify the software.\n"+
                    "\n"+
                    "  Also, for each author's protection and ours, we want to make certain\n"+
                    "that everyone understands that there is no warranty for this free\n"+
                    "software.  If the software is modified by someone else and passed on, we\n"+
                    "want its recipients to know that what they have is not the original, so\n"+
                    "that any problems introduced by others will not reflect on the original\n"+
                    "authors' reputations.\n"+
                    "\n"+
                    "  Finally, any free program is threatened constantly by software\n"+
                    "patents.  We wish to avoid the danger that redistributors of a free\n"+
                    "program will individually obtain patent licenses, in effect making the\n"+
                    "program proprietary.  To prevent this, we have made it clear that any\n"+
                    "patent must be licensed for everyone's free use or not licensed at all.\n"+
                    "\n"+
                    "  The precise terms and conditions for copying, distribution and\n"+
                    "modification follow.\n"+
                    "\n"+
                    "		    GNU GENERAL PUBLIC LICENSE\n"+
                    "   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION\n"+
                    "\n"+
                    "  0. This License applies to any program or other work which contains\n"+
                    "a notice placed by the copyright holder saying it may be distributed\n"+
                    "under the terms of this General Public License.  The \\\"Program\\\", below,\n"+
                    "refers to any such program or work, and a \\\"work based on the Program\\\"\n"+
                    "means either the Program or any derivative work under copyright law:\n"+
                    "that is to say, a work containing the Program or a portion of it,\n"+
                    "either verbatim or with modifications and/or translated into another\n"+
                    "language.  (Hereinafter, translation is included without limitation in\n"+
                    "the term \\\"modification\\\".)  Each licensee is addressed as \\\"you\\\".\n"+
                    "\n"+
                    "Activities other than copying, distribution and modification are not\n"+
                    "covered by this License; they are outside its scope.  The act of\n"+
                    "running the Program is not restricted, and the output from the Program\n"+
                    "is covered only if its contents constitute a work based on the\n"+
                    "Program (independent of having been made by running the Program).\n"+
                    "Whether that is true depends on what the Program does.\n"+
                    "\n"+
                    "  1. You may copy and distribute verbatim copies of the Program's\n"+
                    "source code as you receive it, in any medium, provided that you\n"+
                    "conspicuously and appropriately publish on each copy an appropriate\n"+
                    "copyright notice and disclaimer of warranty; keep intact all the\n"+
                    "notices that refer to this License and to the absence of any warranty;\n"+
                    "and give any other recipients of the Program a copy of this License\n"+
                    "along with the Program.\n"+
                    "\n"+
                    "You may charge a fee for the physical act of transferring a copy, and\n"+
                    "you may at your option offer warranty protection in exchange for a fee.\n"+
                    "\n"+
                    "  2. You may modify your copy or copies of the Program or any portion\n"+
                    "of it, thus forming a work based on the Program, and copy and\n"+
                    "distribute such modifications or work under the terms of Section 1\n"+
                    "above, provided that you also meet all of these conditions:\n"+
                    "\n"+
                    "    a) You must cause the modified files to carry prominent notices\n"+
                    "    stating that you changed the files and the date of any change.\n"+
                    "\n"+
                    "    b) You must cause any work that you distribute or publish, that in\n"+
                    "    whole or in part contains or is derived from the Program or any\n"+
                    "    part thereof, to be licensed as a whole at no charge to all third\n"+
                    "    parties under the terms of this License.\n"+
                    "\n"+
                    "    c) If the modified program normally reads commands interactively\n"+
                    "    when run, you must cause it, when started running for such\n"+
                    "    interactive use in the most ordinary way, to print or display an\n"+
                    "    announcement including an appropriate copyright notice and a\n"+
                    "    notice that there is no warranty (or else, saying that you provide\n"+
                    "    a warranty) and that users may redistribute the program under\n"+
                    "    these conditions, and telling the user how to view a copy of this\n"+
                    "    License.  (Exception: if the Program itself is interactive but\n"+
                    "    does not normally print such an announcement, your work based on\n"+
                    "    the Program is not required to print an announcement.)\n"+
                    "\n"+
                    "These requirements apply to the modified work as a whole.  If\n"+
                    "identifiable sections of that work are not derived from the Program,\n"+
                    "and can be reasonably considered independent and separate works in\n"+
                    "themselves, then this License, and its terms, do not apply to those\n"+
                    "sections when you distribute them as separate works.  But when you\n"+
                    "distribute the same sections as part of a whole which is a work based\n"+
                    "on the Program, the distribution of the whole must be on the terms of\n"+
                    "this License, whose permissions for other licensees extend to the\n"+
                    "entire whole, and thus to each and every part regardless of who wrote it.\n"+
                    "\n"+
                    "Thus, it is not the intent of this section to claim rights or contest\n"+
                    "your rights to work written entirely by you; rather, the intent is to\n"+
                    "exercise the right to control the distribution of derivative or\n"+
                    "collective works based on the Program.\n"+
                    "\n"+
                    "In addition, mere aggregation of another work not based on the Program\n"+
                    "with the Program (or with a work based on the Program) on a volume of\n"+
                    "a storage or distribution medium does not bring the other work under\n"+
                    "the scope of this License.\n"+
                    "\n"+
                    "  3. You may copy and distribute the Program (or a work based on it,\n"+
                    "under Section 2) in object code or executable form under the terms of\n"+
                    "Sections 1 and 2 above provided that you also do one of the following:\n"+
                    "\n"+
                    "    a) Accompany it with the complete corresponding machine-readable\n"+
                    "    source code, which must be distributed under the terms of Sections\n"+
                    "    1 and 2 above on a medium customarily used for software interchange; or,\n"+
                    "\n"+
                    "    b) Accompany it with a written offer, valid for at least three\n"+
                    "    years, to give any third party, for a charge no more than your\n"+
                    "    cost of physically performing source distribution, a complete\n"+
                    "    machine-readable copy of the corresponding source code, to be\n"+
                    "    distributed under the terms of Sections 1 and 2 above on a medium\n"+
                    "    customarily used for software interchange; or,\n"+
                    "\n"+
                    "    c) Accompany it with the information you received as to the offer\n"+
                    "    to distribute corresponding source code.  (This alternative is\n"+
                    "    allowed only for noncommercial distribution and only if you\n"+
                    "    received the program in object code or executable form with such\n"+
                    "    an offer, in accord with Subsection b above.)\n"+
                    "\n"+
                    "The source code for a work means the preferred form of the work for\n"+
                    "making modifications to it.  For an executable work, complete source\n"+
                    "code means all the source code for all modules it contains, plus any\n"+
                    "associated interface definition files, plus the scripts used to\n"+
                    "control compilation and installation of the executable.  However, as a\n"+
                    "special exception, the source code distributed need not include\n"+
                    "anything that is normally distributed (in either source or binary\n"+
                    "form) with the major components (compiler, kernel, and so on) of the\n"+
                    "operating system on which the executable runs, unless that component\n"+
                    "itself accompanies the executable.\n"+
                    "\n"+
                    "If distribution of executable or object code is made by offering\n"+
                    "access to copy from a designated place, then offering equivalent\n"+
                    "access to copy the source code from the same place counts as\n"+
                    "distribution of the source code, even though third parties are not\n"+
                    "compelled to copy the source along with the object code.\n"+
                    "\n"+
                    "  4. You may not copy, modify, sublicense, or distribute the Program\n"+
                    "except as expressly provided under this License.  Any attempt\n"+
                    "otherwise to copy, modify, sublicense or distribute the Program is\n"+
                    "void, and will automatically terminate your rights under this License.\n"+
                    "However, parties who have received copies, or rights, from you under\n"+
                    "this License will not have their licenses terminated so long as such\n"+
                    "parties remain in full compliance.\n"+
                    "\n"+
                    "  5. You are not required to accept this License, since you have not\n"+
                    "signed it.  However, nothing else grants you permission to modify or\n"+
                    "distribute the Program or its derivative works.  These actions are\n"+
                    "prohibited by law if you do not accept this License.  Therefore, by\n"+
                    "modifying or distributing the Program (or any work based on the\n"+
                    "Program), you indicate your acceptance of this License to do so, and\n"+
                    "all its terms and conditions for copying, distributing or modifying\n"+
                    "the Program or works based on it.\n"+
                    "\n"+
                    "  6. Each time you redistribute the Program (or any work based on the\n"+
                    "Program), the recipient automatically receives a license from the\n"+
                    "original licensor to copy, distribute or modify the Program subject to\n"+
                    "these terms and conditions.  You may not impose any further\n"+
                    "restrictions on the recipients' exercise of the rights granted herein.\n"+
                    "You are not responsible for enforcing compliance by third parties to\n"+
                    "this License.\n"+
                    "\n"+
                    "  7. If, as a consequence of a court judgment or allegation of patent\n"+
                    "infringement or for any other reason (not limited to patent issues),\n"+
                    "conditions are imposed on you (whether by court order, agreement or\n"+
                    "otherwise) that contradict the conditions of this License, they do not\n"+
                    "excuse you from the conditions of this License.  If you cannot\n"+
                    "distribute so as to satisfy simultaneously your obligations under this\n"+
                    "License and any other pertinent obligations, then as a consequence you\n"+
                    "may not distribute the Program at all.  For example, if a patent\n"+
                    "license would not permit royalty-free redistribution of the Program by\n"+
                    "all those who receive copies directly or indirectly through you, then\n"+
                    "the only way you could satisfy both it and this License would be to\n"+
                    "refrain entirely from distribution of the Program.\n"+
                    "\n"+
                    "If any portion of this section is held invalid or unenforceable under\n"+
                    "any particular circumstance, the balance of the section is intended to\n"+
                    "apply and the section as a whole is intended to apply in other\n"+
                    "circumstances.\n"+
                    "\n"+
                    "It is not the purpose of this section to induce you to infringe any\n"+
                    "patents or other property right claims or to contest validity of any\n"+
                    "such claims; this section has the sole purpose of protecting the\n"+
                    "integrity of the free software distribution system, which is\n"+
                    "implemented by public license practices.  Many people have made\n"+
                    "generous contributions to the wide range of software distributed\n"+
                    "through that system in reliance on consistent application of that\n"+
                    "system; it is up to the author/donor to decide if he or she is willing\n"+
                    "to distribute software through any other system and a licensee cannot\n"+
                    "impose that choice.\n"+
                    "\n"+
                    "This section is intended to make thoroughly clear what is believed to\n"+
                    "be a consequence of the rest of this License.\n"+
                    "\n"+
                    "  8. If the distribution and/or use of the Program is restricted in\n"+
                    "certain countries either by patents or by copyrighted interfaces, the\n"+
                    "original copyright holder who places the Program under this License\n"+
                    "may add an explicit geographical distribution limitation excluding\n"+
                    "those countries, so that distribution is permitted only in or among\n"+
                    "countries not thus excluded.  In such case, this License incorporates\n"+
                    "the limitation as if written in the body of this License.\n"+
                    "\n"+
                    "  9. The Free Software Foundation may publish revised and/or new versions\n"+
                    "of the General Public License from time to time.  Such new versions will\n"+
                    "be similar in spirit to the present version, but may differ in detail to\n"+
                    "address new problems or concerns.\n"+
                    "\n"+
                    "Each version is given a distinguishing version number.  If the Program\n"+
                    "specifies a version number of this License which applies to it and \\\"any\n"+
                    "later version\\\", you have the option of following the terms and conditions\n"+
                    "either of that version or of any later version published by the Free\n"+
                    "Software Foundation.  If the Program does not specify a version number of\n"+
                    "this License, you may choose any version ever published by the Free Software\n"+
                    "Foundation.\n"+
                    "\n"+
                    "  10. If you wish to incorporate parts of the Program into other free\n"+
                    "programs whose distribution conditions are different, write to the author\n"+
                    "to ask for permission.  For software which is copyrighted by the Free\n"+
                    "Software Foundation, write to the Free Software Foundation; we sometimes\n"+
                    "make exceptions for this.  Our decision will be guided by the two goals\n"+
                    "of preserving the free status of all derivatives of our free software and\n"+
                    "of promoting the sharing and reuse of software generally.\n"+
                    "\n"+
                    "			    NO WARRANTY\n"+
                    "\n"+
                    "  11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY\n"+
                    "FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN\n"+
                    "OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES\n"+
                    "PROVIDE THE PROGRAM \\\"AS IS\\\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED\n"+
                    "OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF\n"+
                    "MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS\n"+
                    "TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE\n"+
                    "PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,\n"+
                    "REPAIR OR CORRECTION.\n"+
                    "\n"+
                    "  12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING\n"+
                    "WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR\n"+
                    "REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,\n"+
                    "INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING\n"+
                    "OUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED\n"+
                    "TO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY\n"+
                    "YOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER\n"+
                    "PROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE\n"+
                    "POSSIBILITY OF SUCH DAMAGES.\n"+
                    "\n"+
                    "		     END OF TERMS AND CONDITIONS\n"+
                    "\n"+
                    "	    How to Apply These Terms to Your New Programs\n"+
                    "\n"+
                    "  If you develop a new program, and you want it to be of the greatest\n"+
                    "possible use to the public, the best way to achieve this is to make it\n"+
                    "free software which everyone can redistribute and change under these terms.\n"+
                    "\n"+
                    "  To do so, attach the following notices to the program.  It is safest\n"+
                    "to attach them to the start of each source file to most effectively\n"+
                    "convey the exclusion of warranty; and each file should have at least\n"+
                    "the \\\"copyright\\\" line and a pointer to where the full notice is found.\n"+
                    "\n"+
                    "    <one line to give the program's name and a brief idea of what it does.>\n"+
                    "    Copyright (C) <year>  <name of author>\n"+
                    "\n"+
                    "    This program is free software; you can redistribute it and/or modify\n"+
                    "    it under the terms of the GNU General Public License as published by\n"+
                    "    the Free Software Foundation; either version 2 of the License, or\n"+
                    "    (at your option) any later version.\n"+
                    "\n"+
                    "    This program is distributed in the hope that it will be useful,\n"+
                    "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
                    "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
                    "    GNU General Public License for more details.\n"+
                    "\n"+
                    "    You should have received a copy of the GNU General Public License\n"+
                    "    along with this program; if not, write to the Free Software\n"+
                    "    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA\n"+
                    "\n"+
                    "\n"+
                    "Also add information on how to contact you by electronic and paper mail.\n"+
                    "\n"+
                    "If the program is interactive, make it output a short notice like this\n"+
                    "when it starts in an interactive mode:\n"+
                    "\n"+
                    "    Gnomovision version 69, Copyright (C) year name of author\n"+
                    "    Gnomovision comes with ABSOLUTELY NO WARRANTY; for details type `show w'.\n"+
                    "    This is free software, and you are welcome to redistribute it\n"+
                    "    under certain conditions; type `show c' for details.\n"+
                    "\n"+
                    "The hypothetical commands `show w' and `show c' should show the appropriate\n"+
                    "parts of the General Public License.  Of course, the commands you use may\n"+
                    "be called something other than `show w' and `show c'; they could even be\n"+
                    "mouse-clicks or menu items--whatever suits your program.\n"+
                    "\n"+
                    "You should also get your employer (if you work as a programmer) or your\n"+
                    "school, if any, to sign a \\\"copyright disclaimer\\\" for the program, if\n"+
                    "necessary.  Here is a sample; alter the names:\n"+
                    "\n"+
                    "  Yoyodyne, Inc., hereby disclaims all copyright interest in the program\n"+
                    "  `Gnomovision' (which makes passes at compilers) written by James Ha";

                    String LicenseEnd ="cker.\n"+
                    "\n"+
                    "  <signature of Ty Coon>, 1 April 1989\n"+
                    "  Ty Coon, President of Vice\n"+
                    "\n"+
                    "This General Public License does not permit incorporating your program into\n"+
                    "proprietary programs.  If your program is a subroutine library, you may\n"+
                    "consider it more useful to permit linking proprietary applications with the\n"+
                    "library.  If this is what you want to do, use the GNU Library General\n"+
                    "Public License instead of this License.";

                     Deducer.eval("writeLines(\""+LicenseStart+LicenseEnd+"\")");    
 
                        
                }else if(cmd.equals("iboxl")){
			Deducer.eval(".getDialog('Interactive Box Plot Long')$run()");
		}else if(cmd.equals("iboxw")){
			Deducer.eval(".getDialog('Interactive Box Plot Wide')$run()");
		}else if(cmd.equals("imosaic")){
			Deducer.eval(".getDialog('Interactive Mosaic Plot')$run()");
		}else if(cmd.equals("ipcp")){
			Deducer.eval(".getDialog('Interactive Parallel Coordinate Plot')$run()");        
                        
          	}else {         
                   try {
                    String evalString = "FALSE";
                    evalString = Deducer.eval("\"package:lattice\"%in%search()").asString();
                    if (evalString.equals("FALSE"))
                       Deducer.execute("suppressMessages(library(lattice))");
                       Deducer.execute("suppressMessages(library(latticeExtra))"); 
                    }catch(Exception e){
                    new ErrorMsg(e);
                    } 
                    if(cmd.equals("Ordinary Least Squares")){
                        Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Ordinary Least Squares\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Two-Stage Least Squares")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Two-Stage Least Squares\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Weighted Least Squares")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Weighted Least Squares\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Binary Logit")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Binary Logit\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Binary Probit")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Binary Probit\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Tobit")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Tobit\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Explore Cross-Sectional Spatial Data")){
                            Deducer.execute("suppressMessages(library(lattice))");
                            Deducer.eval("J(\"hansel.NMSpatialCSDialog\")$runit(\"Cross-Sectional Spatial Data Analysis\")");
                    }else if (cmd.equals("Spatial simultaneous autoregressive error model - maximum likelihood")||
                              cmd.equals("Error Durbin model - maximum likelihood")||
                              cmd.equals("Spatial simultaneous autoregressive error model - GMM")||cmd.equals("Spatial simultaneous autoregressive lag model - maximum likelihood")||
                              cmd.equals("Spatial Durbin model - maximum likelihood")||
                              cmd.equals("Spatial simultaneous autoregressive SAC/SARAR model - maximum likelihood")||
                              cmd.equals("SAC/SARAR Durbin model - maximum likelihood")||cmd.equals("Spatial simultaneous autoregressive SAC model - GMM")||
                              cmd.equals("generalized spatial two-stage least squares")||
                              cmd.equals("Spatial conditional and simultaneous autoregression")||
                              cmd.equals("Matrix exponential spatial lag model")){
                             Deducer.eval("J(\"hansel.GMDialog\")$runit(\""+cmd+"\","+(isJGR()?"TRUE":"FALSE")+")");

                    }else if(cmd.equals("Explore Time Series")){
                            Deducer.eval("J(\"hansel.NMTimeSDialog\")$runit(\"Time Series Analysis\")");                                           
                    }else if(cmd.equals("Chart Financial Time Series")){
                            Deducer.execute("suppressMessages(library(quantmod))");
                            Deducer.eval("suppressMessages(library(quantmod))");
                            Deducer.eval(".hansel.temp.available.series <-Filter( function(x) (is.OHLC(get(x)))&&((nrow(get(x))>1)) , ls() )"); 
                            Deducer.eval("if (length(.hansel.temp.available.series)>0){J(\"hansel.NMFinancialSDialog\")$runit(\"Chart Financial Time Series\",c(.hansel.temp.available.series,\"\"))} else {writeLines(\"Needs object with OHLC (opening, high, low close) data\")}");
                            Deducer.eval("rm(.hansel.temp.available.series)");             
                    }else if(cmd.equals("Vector Autoregression")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Vector Autoregression\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Cointegration Test - Johansen")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Cointegration Test - Johansen\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Cointegration Test - Philips & Ouliaris")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Cointegration Test - Philips & Ouliaris\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Cointegration Test - Engle & Granger")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Cointegration Test - Engle & Granger\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Explore Panel Series")){
                            Deducer.eval("J(\"hansel.NMPanelSDialog\")$runit(\"Panel - Series Analysis\")");
                    }else if(cmd.equals("Panel Linear Model")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - Panel Linear Model\","+(isJGR()?"TRUE":"FALSE")+")");      
                    }else if(cmd.equals("Variable Coefficients")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - Variable Coefficients\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("General Feasible GLS")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - General Feasible GLS\","+(isJGR()?"TRUE":"FALSE")+")"); 
                    }else if(cmd.equals("GMM (e.g. for dynamic equations)")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - GMM (e.g. for dynamic equations)\","+(isJGR()?"TRUE":"FALSE")+")"); 
                    }else if(cmd.equals("Common Correlated Effects Pooled Estimator")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - Common Correlated Effects Pooled Estimator\","+(isJGR()?"TRUE":"FALSE")+")"); 
                    }else if(cmd.equals("Mean Groups")){
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Panel - Mean Groups\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Spatial panel model - maximum likelihood")){
                            Deducer.execute("suppressMessages(library(splm))");
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Spatial panel model - maximum likelihood\","+(isJGR()?"TRUE":"FALSE")+")");
                    }else if(cmd.equals("Spatial panel model w/ random effects & serial error correlation- max likelihood")){
                            Deducer.execute("suppressMessages(library(splm))");
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Spatial panel model w/ random effects & serial error correlation- max likelihood\","+(isJGR()?"TRUE":"FALSE")+")");      
                    }else if(cmd.equals("Spatial panel model - GM")){
                            Deducer.execute("suppressMessages(library(splm))");
                            Deducer.eval("J(\"hansel.GMDialog\")$runit(\"Spatial panel model - GM\","+(isJGR()?"TRUE":"FALSE")+")");
                    }
                }
		if(needsRLocked && fromConsole && !isJGR()){
			WindowTracker.waitForAllClosed();
		}
		
	}
	//temporary until new version of ibase
	public static void insertMenu(JFrame f, String name,int index) {
		JMenuBar mb = f.getJMenuBar();
		JMenu m = EzMenuSwing.getMenu(f,name);
		if (m == null && index<mb.getMenuCount()){
			JMenuBar mb2 = new JMenuBar(); 
			int cnt = mb.getMenuCount();
			for(int i=0;i<cnt;i++){
				if(i==index)
					mb2.add(new JMenu(name));
				mb2.add(mb.getMenu(0));
			}
			f.setJMenuBar(mb2);			
		}else if(m==null && index==mb.getMenuCount())
			EzMenuSwing.addMenu(f,name);
	}
	public static void insertJMenuItem(JFrame f, String menu, String name,
			String command, ActionListener al,int index) {
		JMenu m = EzMenuSwing.getMenu(f, menu);
		JMenuItem mi = new JMenuItem(name);
		mi.addActionListener(al);
		mi.setActionCommand(command);
		m.insert(mi,index);
	}
	
	public static String getRecentData(){
		return recentActiveData;
	}
	
	public static void setRecentData(String data){
		recentActiveData=data;
	}
	
	public static REngine getREngine(){
		return rConnection.getREngine();
	}
	
	public static RConnector getRConnector(){
		return rConnection;
	}
	
	public static void setRConnector(RConnector rc){
		rConnection = rc;
	}
	
	public static REXP eval(String cmd){
		return rConnection.eval(cmd);
	}
	
	public static REXP idleEval(String cmd){
		return rConnection.idleEval(cmd);
	}
	
	public static void execute(String cmd){
		rConnection.execute(cmd);
	}
	
	public static void execute(String cmd, boolean hist){
		rConnection.execute(cmd,hist);
	}
	
	public static void executeAndContinue(String cmd){
		final String c = cmd;
		(new Thread() {
			public void run() {
				execute(c);
			}
			}).start();
	}
				
	public static String makeValidVariableName(String var) {
		return var.replaceAll("[ -+*/\\()=!~`@#$%^&*<>,?;:\"\']", ".");
	}
	
	public static String makeFormula(DefaultListModel outcomes,DefaultListModel terms){
		String formula = "";
		if(outcomes.getSize()==1){
			formula+=outcomes.get(0)+" ~ ";
		}else{
			formula+="cbind(";
			for(int i=0;i<outcomes.getSize();i++){
				formula+=outcomes.get(i);
				if(i<outcomes.getSize()-1)
					formula+=",";
			}
			formula+=") ~ ";
		}
		for(int i=0;i<terms.getSize();i++){
			formula+=terms.get(i);
			if(i<terms.getSize()-1)
				formula+=" + ";
		}
		return formula;
	}
	
	public static String makeRCollection(Collection lis,String func, boolean quotes) {
		String q = "";
		if(quotes){
			q = "\"";
		}
		if (lis.size() == 0)
			return func+"()";
		String result = func+"(";
		Iterator it = lis.iterator();
		int ins = 1;
		while(it.hasNext()){
			String s = it.next().toString();
			result+= q + s + q;
			if(it.hasNext())
				result += ",";
			if (ins % 10 == 9)
				result += "\n";	
			ins++;
		}
		return result+")";
	}
	
	public static String makeRCollection(ListModel lis,String func, boolean quotes) {
		ArrayList a = new ArrayList();
		for(int i=0;i<lis.getSize();i++)
			a.add(lis.getElementAt(i));
		return makeRCollection(a,func,quotes);
	}
	
	public static String makeRCollection(String[] lis,String func, boolean quotes) {
		ArrayList a = new ArrayList();
		for(int i=0;i<lis.length;i++)
			a.add(lis[i]);
		return makeRCollection(a,func,quotes);
	}

	/**
	 * Gets a unique name based on a starting string
	 * 
	 * @param var
	 * @return the value of var concatinated with a number
	 */
	public static String getUniqueName(String var) {
		JGR.refreshObjects();
		var = RController.makeValidVariableName(var);
		if (!JGR.OBJECTS.contains(var))
			return var;
		int i = 1;
		while (true) {
			if (!JGR.OBJECTS.contains(var + i))
				return var + i;
			i++;
		}
	}
	
	/**
	 * Gets a unique name based on a starting string
	 * 
	 * @param var
	 * @param envName
	 *            The name of the enviroment in which to look
	 * @return the value of var concatinated with a number
	 */
	public static String getUniqueName(String var, String envName) {
		var = RController.makeValidVariableName(var);

		try {
			REXPLogical temp = (REXPLogical) eval("is.environment("
					+ envName + ")");
			boolean isEnv = temp.isTRUE()[0];
			if (!isEnv)
				return var;
		} catch (Exception e) {
			new ErrorMsg(e);
			return var;
		}

		boolean isUnique = false;

		try {

			REXPLogical temp = (REXPLogical) eval("exists('" + var
					+ "',where=" + envName + ",inherits=FALSE)");
			isUnique = temp.isFALSE()[0];
			if (isUnique)
				return var;

		} catch (Exception e) {
			new ErrorMsg(e);
			return var;
		}

		int i = 1;
		while (true) {

			try {

				REXPLogical temp = (REXPLogical) eval("exists('" + (var + i) + "',where=" + envName
								+ ",inherits=FALSE)");
				isUnique = temp.isFALSE()[0];

			} catch (Exception e) {
				new ErrorMsg(e);
			} 

			if (isUnique)
				return var + i;
			i++;
		}
	}
	
	/**
	 * is package installed
	 * @param packageName
	 * @return
	 */
	public static boolean isInstalled(String packageName){
		REXP installed = Deducer.eval("'" + packageName +"' %in% installed.packages()[,1]");
		if(installed!=null && installed instanceof REXPLogical){
			return ((REXPLogical) installed).isTRUE()[0];
		}
		return false;
	}
	
	/**
	 * is package loaded
	 * @param packageName
	 * @return
	 */
	public static boolean isLoaded(String packageName){
		REXP loaded = Deducer.eval("'" + packageName +"' %in% c(names(sessionInfo()$otherPkgs), sessionInfo()$base)");
		if(loaded!=null && loaded instanceof REXPLogical){
			return ((REXPLogical) loaded).isTRUE()[0];
		}
		return false;
	}
	
	/**
	 * Checks if package is installed, and asks to install if not.
	 * @param packageName 
	 * @return "loaded", "installed" or "not-installed"
	 */
	public static String requirePackage(String packageName){
		if(isLoaded(packageName))
			return "loaded";
		if(isInstalled(packageName))
			return "installed";
		int inst = JOptionPane.showOptionDialog(null, "Package " + packageName + " not installed. \nWould you like to install it now?",
												"Install", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,
												new String[]{"Yes","No"}, "Yes");
		if(inst==JOptionPane.OK_OPTION){
			Deducer.eval("install.packages('" + packageName + "',,'http://cran.r-project.org')");
			if(isInstalled(packageName))
				return "installed";
		}
		return "not-installed";
	}
	


	public static synchronized void refreshData(){
		REXP x = Deducer.idleEval(".getDataObjects()");
		if(x==null)
			return;
		String[] data;
		JGR.DATA.clear();
		try {
			if (!x.isNull() && (data = x.asStrings()) != null) {	
				int a = 1;
				for (int i = 0; i < data.length; i++) {
					boolean b = (data[i].equals("null") || data[i].trim().length() == 0);
					String name = b ? a + "" : data[i];
					JGR.DATA.add(RController.createRObject(name, data[++i], null, (!b)));
					a++;
				}

			}
		} catch (REXPMismatchException e) {}
	}
	
	public static Vector getData(){
		return JGR.DATA;
	}
	
	/**
	 * Refreshes objects and keywords if JGR is not doing so.
	 */
	class DataRefresher implements Runnable {

		public DataRefresher() {}

		public void run() {
			while (true)
				try {
					Thread.sleep(5000);
					refreshData();
				} catch (Exception e) {
					new ErrorMsg(e);
				}
		}
	}
}
