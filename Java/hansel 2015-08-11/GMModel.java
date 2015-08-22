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
 
 The current file most notably uses the code from GLMModel.java in the Deducer package. 
 
The current file made adjustments to that earlier java code on 2013-07-07 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import org.rosuda.JGR.JGR;
import org.rosuda.JGR.util.ErrorMsg;
import org.rosuda.JGR.RController;
/*import org.rosuda.JRI.REXP;*/
import org.rosuda.REngine.REXPLogical;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.deducer.Deducer;
import org.rosuda.deducer.data.ExDefaultTableModel;
import org.rosuda.deducer.models.ModelModel;
import org.rosuda.deducer.models.RModel;
import org.rosuda.deducer.models.GLMModel;

public class GMModel extends GLMModel {
    public String dataClassInR ="";
    public EFEaePlots efeaeplots = new EFEaePlots();
    public EFEaeText efeaetext = new EFEaeText();
    public PlotControl plotControl = new PlotControl();
    public PlotControl3 plotControl3 = new PlotControl3();
    public PlotControl5 plotControl5 = new PlotControl5();
    public EFEGLMExtra xxx;
    public EFEGLMExtra efeglmextra = new EFEGLMExtra();
    public EFEGLMOptions efeGLMOptions = new EFEGLMOptions();
    public HanselExport hanselExport = new HanselExport();
    public UnitRootTestOptions  unitRootTestOptions = new UnitRootTestOptions();
    public WaveletOptions  waveletOptions = new WaveletOptions();
    public ArimaOptions  arimaOptions = new ArimaOptions();
    public ExpSmoothingOptions  expSmoothingOptions = new ExpSmoothingOptions();
    public UTSOtherPlotsOptions UTSOtherPlotsOptions = new UTSOtherPlotsOptions();
    public VAROptions  varOptions = new VAROptions();
    public IRFOptions  irfOptions = new IRFOptions();
    public FilterOptions  filterOptions = new FilterOptions();
    public String panelIndex = "";
    public String start = "";
    public String end = "";
    public String estimationFunction="basic";
    public String effect = "individual"; /* just for dead code? */
    public String plmeffect = "individual";
    public String pvcmeffect = "individual";
    public String spmleffect = "individual";
    public String pgglseffect = "individual";
    public String pgmmeffect = "individual";
    public String spremlErrors = "semsrre";
    public String spgmmMoments = "semsrre";
    public String spgmMoments = "initial";
    public String effect_for_Rmodel;
    public Boolean panelModel = false;
    public String plmPanelType = "within (\"fixed\") effects";
    public String pvcmPanelType = "within (\"fixed\") effects";
    public String pgglsPanelType = "within (\"fixed\") effects";
    public String pgmmPanelType = "1-step, difference GMM";
    public String pccePanelType = "standard residuals";
    public String pmgPanelType = "mean groups";
    public String spatialErrorPanel = "b";
    public String spgmPanelType = "within (\"fixed\") effects";
    public String spmlPanelType = "within (\"fixed\") effects";
    public String cipsURTPanelType = "mean groups";

    public String modeltype = "within";
    public String exogVariablesList = "";
    public String exogVariables = "";
    public String instruments = "";
    public String vcov = "No correction"; /* just for dead code? */
    public String plmvcov = "No correction";
    public String glmvcov = "No correction";
    public String tobitvcov = "No correction";
    public String pgmmvcov = "No correction";
    public String stslsvcov = "No correction";
    public String gstslsvcov = "No correction";
    public String vcovadjModel;
    public String vcovadj;
    public String method;
    public String EstimationMethod;
    public String formulaSecondStage;
    public DefaultListModel termsSecondStage;

    public String formula;
    public String formula_for_Rmodel;
    public DefaultListModel termsFinalStage;
    public String endogenousIndices;
    public String endogenousVariables;
    public Integer endogenousLength;
    public String depvar;

    public DefaultListModel outcomeFrom1stTerm;
    public DefaultListModel termsExcludingFirst = new DefaultListModel();
    public String modelName;
    public String modelNameFinalStage;
   
    public String dName = "";
    protected String VariablesList;
    protected String VariablesListWithQuotes;
    
    public String attachment;
    public String attachmentTS;
    
    public String dependentVar = "";

    public String modelCall = ""; 
    public String modelWeights = "";
    public String modelSubset = "";
    public String modelPlmEffect = "";
    public String modelPlmType = "";
    public String modelPanelCSVar = "";
    public String modelPanelTimeVar = "";
    public DefaultListModel modelPanelCSLevelsList;
    public String modelInstruments = "";
    public String termsOneString;
    public String[] termsArray; 
    public String[] instrumentsArray;
    public Boolean PanelEstMethod;
    public String tempPrefix = "";
    public String frequency_of_original_data;
    public String start_of_original_data;
    public String listwString ="";
    public String listw2String = "";
    public Boolean existsTS = false;
    public String classOfMirroredData = "";
    public String rowNamesOrDatesLabel = "";
    public String rowNamesOrDatesLabelModel = "";
    
    public String warningMessages="";
    public String errorMessageString="";
    public Boolean modelExists = true;
    public Boolean errorExists = false;
    public String[] warningString = new String[]{};
    public String[] errorString = new String[]{};
    public int numExplanatoryVariables =0;
    public Boolean interactionTerm = false;
    public DefaultListModel coefList;
    public String subsetCoding = "";
    public String isJGR;
    
    public String vNameAdjusted;
    public Boolean variableTransformation;
            
    public String numericExplVars; 
    
    public RModel run(boolean preview,RModel prevModel,boolean changevcov){
      /*  */
		RModel rModel = new RModel();
                String cmd = "";
                String cmdfit = "";
                String cmdClassic = "";
                String[] out = new String[]{};
                String[] out2 = new String[]{};

                      try {
                        listwString = Deducer.eval(".hansel.working.env$SPDrefs[[\""+data+"\"]]$listwFile1").asString();  
                        }catch(Exception e){
                        new ErrorMsg(e);
                        } 
                       
                
         String dataName="";
         if (changevcov){
               dataName = prevModel.data;
         }else{
                modelName = "";
                modelNameFinalStage = "";
          if (this.efeglmextra.previousEstimation){
                    modelName = this.efeglmextra.PriorModelName;
                    if (this.method.equals("ivreg"))
                        modelNameFinalStage = modelName+"$SecondStage";
                    else
                        modelNameFinalStage = modelName;

            } else{
                boolean envDefined = ((REXPLogical)Deducer.eval("'"+Deducer.guiEnv+"' %in% .getOtherObjects()")).isTRUE()[0];
		if(!envDefined){
			Deducer.eval(Deducer.guiEnv+"<-new.env(parent=emptyenv())");
		}
                if (this.efeglmextra.vecExploreVAR)
                   modelName = efeglmextra.vecName+".asVAR";
                else
                   modelName = Deducer.getUniqueName("model."+this.method);
                if (this.method.equals("ivreg"))
                        modelNameFinalStage = modelName+"$SecondStage";
                    else
                        modelNameFinalStage = modelName;  
             } 
          
           Deducer.eval(Hansel.hanselEnv +"$"+modelName+"_estPlotPanelDevNums <-c(rep(\"\",6))");
           tempPrefix = Hansel.hanselEnv + "$Hansel_temp$";
    
             if (this.efeglmextra.previousEstimation){
                            String modelvarNames = "";
                            String[] modelvarNamesSplit;
                            
                            try {
                                    if (this.method.equals("VAR")||this.method.equals("vec2var") )
                                        modelvarNames = Deducer.eval("paste(colnames("+modelName+"$y)[1:length(colnames("+modelName+"$y))],\";\",collapse=\"\")").asString();
                                    else if (this.method.equals("ca.jo"))
                                        modelvarNames = Deducer.eval("paste(colnames("+modelName+"@x)[1:length(colnames("+modelName+"@x))],\";\",collapse=\"\")").asString();
                                    else
                                        modelvarNames = Deducer.eval("paste(names("+modelName+"$model)[2:length(names("+modelName+"$model))],\";\",collapse=\"\")").asString();
                                    modelvarNamesSplit =  modelvarNames.split(" ;");
                                    termsOneString = modelvarNames.replace(" ;","");
                                    termsArray = new String[modelvarNamesSplit.length];
                                    this.termsFinalStage = new DefaultListModel();
                                    for(int i=0;i<(modelvarNamesSplit.length);i++){
                                        termsArray[i] = modelvarNamesSplit[i];
                                    this.termsFinalStage.addElement(this.termsArray[i]);

                                    }
                                    if (termsArray.length > 0) {
                                        VariablesList = termsArray[0];
                                        VariablesListWithQuotes = "\"" + VariablesList + "\"";
                                        if (termsArray.length>1) 
                                            for(int i=1;i<termsArray.length;i++){
                                            VariablesList = VariablesList + ","+ termsArray[i].toString()+"";
                                            VariablesListWithQuotes = VariablesListWithQuotes + ",\""+ termsArray[i].toString()+"\"";
                                           }
                                     }
                                }catch(Exception e){
                            new ErrorMsg(e);
                            }
                            if (method.equals("ivreg")) {
                                termsFinalStage = new DefaultListModel(); 
                                modelvarNames = "";
                                try {
                                modelvarNames = Deducer.eval("paste(names("+modelName+"$SecondStage$model)[2:length(names("+modelName+"$SecondStage$model))],\";\",collapse=\"\")").asString();
                                modelvarNamesSplit =  modelvarNames.split(";");
                                for(int i=0;i<(modelvarNamesSplit.length);i++){
                                termsFinalStage.addElement(modelvarNamesSplit[i]);    
                                          }
                                    }catch(Exception e){
                                new ErrorMsg(e);
                                }     
                            }
             }else{
                if (!(this.instrumentsArray==null)) {               
                    if (this.instrumentsArray.length>0) {
                       this.instruments= ""+ instrumentsArray[0];
                       for(int i=1;i<instrumentsArray.length;i++)
			this.instruments = this.instruments + " + " +this.instrumentsArray[i];
                    } else
                    this.instruments= "";
                } 
                else
                    this.instruments= "";

                  dName = data;
                  dName = dName.replace("__","");
                  
                  String existsTSasString = new String();
                  if (dName.equals(data)) {
                        try {
                          existsTSasString = Deducer.eval("as.character(exists(\"T.S."+dName+"\"))").asString();  
                        }catch(Exception e){
                          new ErrorMsg(e);
                        }
                       existsTS = existsTSasString.equals("TRUE");
                       if (existsTS) {
                        dName = "T.S." +dName;
                       }
                  }

 
                 if (subset==null || subset.length()==0){
                    dName = dName.replace("__","");
                 } else {
                    dName = "subset("+dName.replace("__","")+","+subset+")";
                 }

                 try {
                                dataClassInR  = Deducer.eval("as.character(class("+dName+")[1])").asString();  
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                if (dataClassInR.equals("zoo")||dataClassInR.equals("xts")||dataClassInR.equals("xts")||
                    dataClassInR.equals("ts")||dataClassInR.equals("mts"))
                    existsTS = true;
                else 
                    existsTS = false;

/*********Section 1: Defining data and attachments ******************************************* */               
                dataName = data;
			if(prevModel==null){
                                dataName = Deducer.getUniqueName(data,Deducer.guiEnv);
			}else 
                            
				dataName = prevModel.data;
                Integer numDashes = 0;
                if (start.contains("-"))
                    numDashes = start.split("-").length-1;
                if (end.contains("-"))
                    numDashes = Math.max(numDashes,end.split("-").length-1);

                Boolean obsFound = (start.contains("obs")||end.contains("obs"));

                if (dataClassInR.equals("SpatialPolygonsDataFrame")) {
                   this.efeglmextra.nbForModel = "poly2nb("+dName.replace("__","")+")";  
                }
                
                frequency_of_original_data ="1";
                start_of_original_data = "1";
                if (dataClassInR.equals("zoo")||dataClassInR.equals("xts")||dataClassInR.equals("zooreg")||
                    dataClassInR.equals("ts")||dataClassInR.equals("mts")) {
                     try {
                        frequency_of_original_data = Deducer.eval("as.character(frequency("+dName.replace("__","")+"))").asString();
                        start_of_original_data = Deducer.eval("as.character(start("+dName.replace("__","")+"))").asString();

                        }catch(Exception e){
                        new ErrorMsg(e);
                    } 
                }
                
               String asTime ="";
                
              
                
                if (this.PanelEstMethod||(method.equals("cipstest"))){
                    if (!(subset==null || subset.length()==0)) 
                        dName = "subset("+dName+","+subset+")";
                }
                else if (!(start.equals("")&end.equals(""))) {
                    if (existsTS){
                       if (obsFound)
                          dName=dName+"["+(start.equals("")?"1":start.replace("obs","").replace("=","") )+":"
                                                                              +(end.equals("")?"nrow("+dName+")":end.replace("obs","").replace("=",""))+",]";
                       else {
                           if ((dataClassInR.equals("zoo")||dataClassInR.equals("xts")))
                               dName="window("+dName+(start.equals("")?"":",start="+asTime+"(\""+start+"\")")
                                                                                  +(end.equals("")?"":",end="+asTime+"(\""+end+"\")")+")";
                           else    
                               dName = "window("+dName+(start.equals("")?"":",start=c("+start.replace(":",",").replace("-",",")+")")+
                                                                          (end.equals("")?"":",end=c("+end.replace(":",",")+")").replace("-",",")+")";};    
                           if (!start.equals(""))
                           try {
                                start_of_original_data = Deducer.eval("as.character(start("+dName.replace("__","")+"))").asString();

                                }catch(Exception e){
                                new ErrorMsg(e);
                            }   
                       }
                     else
                       dName = dName+"["+(start.equals("")?"1":start.replace("obs","").replace("=",""))+":"
                                                                    +(end.equals("")?"nrow("+dName+")":end.replace("obs","").replace("=",""))+",]";   
                    
                    cmd+= "\n attach("+attachment+")"; 
                }    
                
                if (existsTS){
                     if (frequency_of_original_data.equals("4"))
                        asTime = "as.yearqtr";
                    else if (frequency_of_original_data.equals("12"))
                        asTime = "as.yearmon";
                    else 
                        asTime = "as.Date";
                     
                     if (frequency_of_original_data.equals("12")){
                        rowNamesOrDatesLabel = "as.yearmon(time("+dName+"))";
                        rowNamesOrDatesLabelModel = "as.yearmon(time("+modelName+"))"; 
                     }else if (frequency_of_original_data.equals("4")){
                        rowNamesOrDatesLabel = "as.yearqtr(time("+dName+"))";
                        rowNamesOrDatesLabelModel = "as.yearqtr(time("+modelName+"))"; 
                     }else{ 
                         rowNamesOrDatesLabel = "time("+dName+")";
                         rowNamesOrDatesLabelModel = "time("+modelName+")";
                     }
                }else {
                     rowNamesOrDatesLabel = "row.names("+dName+")";
                     rowNamesOrDatesLabelModel = "names(residuals("+modelName+"))";
                }

                String cmdErrorPrefix="\n "+Hansel.hanselEnv +"$Hansel_temp$MssgEW=\"NULL\""+
                                      "\n "+Hansel.hanselEnv +"$Hansel_temp$warningSave<-NULL"+
                                      "\n "+Hansel.hanselEnv +"$Hansel_temp$errorSave<-NULL"+
                                      "\n "+Hansel.hanselEnv +"$Hansel_temp$MssgEW <- capture.output(list(value = withCallingHandlers(tryCatch(";
                     String cmdErrorPrefix2=
                                      "\n "+Hansel.hanselEnv +"$Hansel_temp$MssgEW <- capture.output(list(value = withCallingHandlers(tryCatch(";
                String cmdErrorSuffix=",error = function(e) {"+Hansel.hanselEnv +"$Hansel_temp$errorSave<<-c("+Hansel.hanselEnv +"$Hansel_temp$errorSave,e)}),warning = function(w){"+Hansel.hanselEnv +"$Hansel_temp$warningSave<<-c("+Hansel.hanselEnv +"$Hansel_temp$warningSave,w)}),warning = "+Hansel.hanselEnv +"$Hansel_temp$warningSave)$warning)";
               
                if ((method.equals("VAR"))||(method.equals("ca.jo"))||(method.equals("cajolst"))||
                        (method.equals("ca.po"))||this.EstimationMethod.equals("Cointegration Test - Engle & Granger")) {
                    
                        exogVariables= "";              
                        if (method.equals("VAR")){
                            if (!(efeglmextra.exogVars.length==0)) {
                              exogVariables = "\""+efeglmextra.exogVars[0]+"\"";              
                              if (efeglmextra.exogVars.length>1) 
                                for(int i=1;i<efeglmextra.exogVars.length;i++){
                                exogVariables = exogVariables + ","+ "\""+efeglmextra.exogVars[i]+"\"";
                                }
                               }
                          } else if (method.equals("ca.jo")){
                            if (!(efeglmextra.cointExogVars.length==0)) {
                              exogVariables = "\""+efeglmextra.cointExogVars[0]+"\"";              
                              if (efeglmextra.cointExogVars.length>1) 
                                for(int i=1;i<efeglmextra.cointExogVars.length;i++){
                                exogVariables = exogVariables + ","+ "\""+efeglmextra.cointExogVars[i]+"\"";
                                }
                               }
                          }
                        if (method.equals("ca.jo")){                         
                          efeglmextra.titleModelforCoefTest = "Model of each cointegrating relation";
                          efeglmextra.titleManualAltCoefTest = "Provide restrictions, e.g. b(1) = b(2), 3*b(2) + 2*b(3) = 0, a(1) = 0, a(2) + a(3)=0";
                          }
                        
                        
                }
                if (termsArray.length > 0) {
                    VariablesList = termsArray[0];
                    VariablesListWithQuotes = "\"" + VariablesList + "\"";
                    if (termsArray.length>1) 
                        for(int i=1;i<termsArray.length;i++){
                        VariablesList = VariablesList + ","+ termsArray[i].toString()+"";
                        VariablesListWithQuotes = VariablesListWithQuotes + ",\""+ termsArray[i].toString()+"\"";
                       }
                }
                
                String cmdpreVarAdj = "";
                String cmdpostVarAdj = "";
                variableTransformation = false; 
                for(int i=0;i<termsArray.length;i++){
                    if (termsArray[i].toString().contains("(")||termsArray[i].toString().contains("*")||termsArray[i].toString().contains("-"))
                       variableTransformation = true;
                    }
                 String[] availableVariables = new String[]{};
                 if (variableTransformation) {
                      try {
                        availableVariables =  Deducer.eval("colnames("+data+")").asStrings();    
                        }catch(Exception e){
                        new ErrorMsg(e);
                        }
                    existsTS = existsTSasString.equals("TRUE");

                   String vNameAddition = new String(termsArray[0].toString());
                   if (vNameAddition.contains("(")||vNameAddition.contains("*")||vNameAddition.contains("-") ){
                     for(int i=0;i<availableVariables.length;i++){
                                            vNameAddition= vNameAddition.replace(availableVariables[i],dName+"[,"+"\""+availableVariables[i]+"\""+"]");
                                            }
                   } else {
                     vNameAddition = dName+"[,"+"\""+termsArray[0].toString()+"\""+"]";
                   }
                   vNameAdjusted =(existsTS?"":"ts(")+ vNameAddition + (existsTS?"":")");  

                   
                    for(int i=1;i<termsArray.length;i++){
                                    
                       vNameAddition = new String(termsArray[i].toString());
                       if (vNameAddition.contains("(")||vNameAddition.contains("*")||vNameAddition.contains("-") ){
                         for(int j=0;j<availableVariables.length;j++){
                                                vNameAddition= vNameAddition.replace(availableVariables[j],dName+"[,"+"\""+availableVariables[j]+"\""+"]");
                                                }
                       } else {
                         vNameAddition = dName+"[,"+"\""+termsArray[i].toString()+"\""+"]";
                       }
                       vNameAdjusted =vNameAdjusted + ","+ (existsTS?"":"ts(")+ vNameAddition + (existsTS?"":")");
                    }
                    vNameAdjusted = "ts.union("+ vNameAdjusted+")";
                    if (method.equals("VAR"))
                      VariablesListWithQuotes=VariablesListWithQuotes.replace("(","_").replace(")", "").replace("[","").replace("]","");
                    if (method.equals("VAR")||method.equals("ca.jo")||method.equals("ca.po")){
                      cmdpreVarAdj = "\ntemp_MTS_Data <-"+ vNameAdjusted+"\n"+
                                                   "colnames(temp_MTS_Data) <- c("+VariablesListWithQuotes+")"+"\n";
                      cmdpostVarAdj = "\nrm(temp_MTS_Data)";
                    }
                } else {       
                     vNameAdjusted= dName+"[,"+"c("+VariablesListWithQuotes+")"+"]";
                }

                
                
                String cmdVARvariables = ""; 
                String cmdVARvariables2 = "";
                if (method.equals("VAR")||method.equals("ca.jo")||method.equals("ca.po")){
                } 
                this.termsFinalStage = new DefaultListModel();
                String cmdSeparateStages = ""; 
                if (!method.equals("ivreg")){
                    for(int j=0;j<this.termsArray.length;j++)
			this.termsFinalStage.addElement(this.termsArray[j]);
                }
                /*  JOptionPane.showMessageDialog(null, "debug");*/
               /* Deducer.execute(cmd);*/ /*debugger*/  
                        
/*********Section 1: End Defining data and attachments ******************************************* */
/*********Section 2: Provide Model Estimation Statements ******************************************* */                     

                cmdClassic = cmd + cmdVARvariables +cmdVARvariables2;
                cmdClassic+="\n \n # Model estimation statement(s):"+cmdpreVarAdj;  
                
                String cmdpre = "";    
                cmd= " ";
                String cmdb="";
                String cmd2="";
                if (this.efeglmextra.previousEstimation){
                        /*Don't do anything*/
                }  else if (method.equals("tobit")){

                     cmd+= "\n"+
                        modelName+" <- tobit(formula="+formula+",data="+dName+
                             (efeglmextra.leftLimitText.equals("")?"":",left="+efeglmextra.leftLimitText)+
                             (efeglmextra.rightLimitText.equals("")?"":",right="+efeglmextra.rightLimitText)+
                        (modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset==null || subset.length()==0) ? "" : ",subset = "+subset)
                           /*  +",na.action=na.omit"*/ +")";     

                    try {
                      numericExplVars = Deducer.eval("as.character(is.numeric(as.matrix("+
                              dName+"["+ (VariablesListWithQuotes.contains(",")?",c("+VariablesListWithQuotes+")":VariablesListWithQuotes)+"]"+")))").asString();
                    }catch(Exception e){
                      new ErrorMsg(e);
                    }
                   if (numericExplVars.equals("TRUE")){
                     cmd2+= "\n "+ modelName + "$HanselAddOns$marginalEffects <-round( data.frame(t(pnorm(c(1,colMeans("+
                                  dName+"["+ (VariablesListWithQuotes.contains(",")?",c("+VariablesListWithQuotes+")":VariablesListWithQuotes)+"]"+"))%*%"+modelName+"$coef/"+modelName+"$scale)%*%"+modelName+"$coef)),5)\n"
                               +modelName + "$HanselAddOns$marginalEffects[1,1] <- \"\"\n"
                              +"colnames("+modelName + "$HanselAddOns$marginalEffects) <- \"marginal effects\"\n"
                              +"rownames("+modelName + "$HanselAddOns$marginalEffects) <- names("+modelName+"$coefficients)";       
                   } else {
                     cmd2+= "\n "+ modelName + "$HanselAddOns$marginalEffects <- \"Marginal effects not calculated as not all explanatory variables are numeric.\"";
                   }
                   Deducer.execute(cmd2);            
                     
                }  else if (method.equals("errorsarlm")){
                      cmd+= "\n "+modelName+" <- errorsarlm(formula="+formula+",data="+dName+",listw="+listwString+
                              
                        (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                              
                        (efeglmextra.SpatialDurbin ? ",etype=\"emixed\"" :"")+
                              
                        (efeglmextra.spatialMethodType.equals("eigen") ? "" :",method=\""+efeglmextra.spatialMethodType+"\"") + 
                              
                        ((efeglmextra.notQuiet) ? ",quiet=FALSE" : "") +
                              
                              ")";                      
  
                }  else if (method.equals("GMerrorsar")){
                     cmd+= "\n "+modelName+" <- GMerrorsar(formula="+formula+",data="+dName+ ",listw="+listwString+
                        (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                        ((efeglmextra.arnoldWied) ? ",arnoldWied=TRUE" : "") +
                        ((efeglmextra.verbose) ? ",verbose=TRUE" : "") +
                              ")";  
 
                }  else if (method.equals("lagsarlm")){
                     cmd+="\n "+modelName+" <- lagsarlm(formula="+formula+",data="+dName+ ",listw="+listwString+
                         (efeglmextra.SpatialDurbin ?  ",type=\"mixed\"" : "")+
                         (efeglmextra.spatialMethodType.equals("eigen") ? "" :",method=\""+efeglmextra.spatialMethodType+"\"") + 
                         (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                        ((efeglmextra.notQuiet) ? ",quiet=FALSE" : "") +
                              ")"; 
                }  else if (method.equals("sacsarlm")){
                     cmd+="\n "+modelName+" <- sacsarlm(formula="+formula+",data="+dName+ ",listw="+listwString+
                         ((listw2String.equals(listwString)) ? "":  ((listw2String.equals("")) ? "":  ",listW2="+listw2String)) +
                         (efeglmextra.SpatialDurbin ?  ",type=\"sacmixed\"" : "")+
                         (efeglmextra.spatialMethodType.equals("eigen") ? "" :",method=\""+efeglmextra.spatialMethodType+"\"") + 
                         (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                        ((efeglmextra.notQuiet) ? ",quiet=FALSE" : "") +
                              ")";  
                }  else if (method.equals("gstsls")){
                     cmd+="\n "+modelName+" <- gstsls(formula="+formula+",data="+dName+ ",listw="+listwString+
                         ((listw2String.equals(listwString)) ? "":  ((listw2String.equals("")) ? "":  ",listW2="+listw2String)) +
                         (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                         (efeglmextra.scaleU ? "scaleU=TRUE" :"") + 
                        ((efeglmextra.verbose) ? ",verbose=TRUE" : "") +
                        ((gstslsvcov.equals("No correction")) ? "" : ",robust=TRUE") + 
                        ((efeglmextra.W2X) ? "" : ",W2X=FALSE") +
                              ")"; 
                }  else if (method.equals("stsls")){
                     cmd+="\n "+modelName+" <- stsls(formula="+formula+",data="+dName+ ",listw="+listwString+
                         (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                        ((stslsvcov.equals("No correction")) ? "": ",robust=TRUE, HC=\""+stslsvcov+"\"") + 
                        ((efeglmextra.W2X) ? "" : ",W2X=FALSE") +
                              ")";  
                }  else if (method.equals("spautolm")){
                     cmd+="\n "+modelName+" <- spautolm(formula="+formula+",data="+dName+ ",listw="+listwString+
                          (modelWeights.equals("") ? "" : ",weights="+modelWeights)+
                         (efeglmextra.spautolmFamily.equals("SAR") ?  "" : ",family="+efeglmextra.spautolmFamily)+
                         (efeglmextra.spatialMethodType.equals("eigen") ? "" :",method=\""+efeglmextra.spatialMethodType+"\"") + 
                         ((efeglmextra.verbose) ? ",verbose=TRUE" : "") +
                         (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) +                          
                              ")";                    
                }  else if (method.equals("lagmess")){
                                          cmd+="\n "+modelName+" <- lagmess(formula="+formula+",data="+dName+ ",listw="+listwString+
                          (efeglmextra.zeroPolicyType.equals("NULL") ? "" :",zero.policy="+efeglmextra.zeroPolicyType) + 
                         ((efeglmextra.verbose) ? ",verbose=TRUE" : "") +              
                              ")";    
                     
                } else if (efeglmextra.vecExploreVAR){
                    cmd+="\n "+modelName+" <- vec2var("+efeglmextra.vecName+")";
                            
                }else if (method.equals("VAR")){
                    if (varOptions.infoCriterion.equals("None, just use given lag")||
                         varOptions.infoCriterion.equals("")||
                            varOptions.infoCriterion.equals(null) ){
                        
                                      
                              cmd+="\n "+modelName+" <- VAR("+ (variableTransformation?"temp_MTS_Data":vNameAdjusted) +
                                                       /*  (exogVariables.equals("") ? "" : ",exogen=cbind("+exogVariables+")")+ */
                                                         (exogVariables.equals("") ? "" : ",exogen="+ dName+"["+ (exogVariables.contains(",")?",c("+exogVariables+")":exogVariables)+"]" )+ 
                                                         ",type=\""+varOptions.constantTrend+
                                                         "\",p="+varOptions.maxLag+
                                                           varOptions.seasonalDummies+")";
                    }
                    else {
                      cmd+="\n "+modelName+" <- VAR("+ (variableTransformation?"temp_MTS_Data":vNameAdjusted) +
                                                  (exogVariables.equals("") ? "" : ",exogen="+ dName+"["+ (exogVariables.contains(",")?",c("+exogVariables+")":exogVariables)+"]" )+ 
                                                         ",type=\""+varOptions.constantTrend+
                                                         "\",lag.max="+varOptions.maxLag+",ic=\""+varOptions.infoCriterion+"\""+
                                                           varOptions.seasonalDummies+")";
                    }
                    cmd2+="\n Bcoef("+modelName+")";
                    cmd2+="\n logLik("+modelName+")";
                    
                } else if (method.equals("ca.jo")){
                      String cajo_ecdet;
                      String cajo_spec;
                    
                      if ((varOptions.cointOption.substring(0,2).equals("no")))
                               cajo_ecdet="none"; 
                           else 
                               cajo_ecdet = varOptions.cointOption.substring(0,5); 
                           if ((varOptions.cointOption.substring(varOptions.cointOption.length()-8,varOptions.cointOption.length()-1).equals("long-run")))
                               cajo_spec = "long-run";
                           else
                               cajo_spec = "transitory";
                    
                           
                           
                    cmd+="\n "+modelName+" <- ca.jo("+
                                                   (variableTransformation? "temp_MTS_Data":vNameAdjusted)+",ecdet=\""+cajo_ecdet+ 

                                                   "\",type=\"trace\",K="+varOptions.maxLag+
                                                   ",spec=\""+cajo_spec+
                                                     "\""+varOptions.seasonalDummies+
                                                     (exogVariables.equals("") ? "" : ",dumvar="+ dName+"["+ (exogVariables.contains(",")?",c("+exogVariables+")":exogVariables)+"]" )+ 
                                                     ")";                 
                    cmdb+="\n "+modelName+".eigen <- ca.jo("+
                                                (variableTransformation? "temp_MTS_Data":vNameAdjusted)+",ecdet=\""+cajo_ecdet+ 
                                                   "\",type=\"eigen\",K="+varOptions.maxLag+
                                                   ",spec=\""+cajo_spec+
                                                     "\""+varOptions.seasonalDummies+
                                                  (exogVariables.equals("") ? "" : ",dumvar="+ dName+"["+ (exogVariables.contains(",")?",c("+exogVariables+")":exogVariables)+"]" )+ 
                                                     ")";
                    cmd2+= "\n Hanseltemp_betaVector <-"+modelName+"@V";
                    cmd2+= "\n for (i in 2:"+termsArray.length+"){Hanseltemp_betaVector[,i] <- Hanseltemp_betaVector[,i]/Hanseltemp_betaVector[which.max(abs(Hanseltemp_betaVector[,i])),i]} ";
                    cmd2+= "\n rm(i)";
                    cmd2+= "\n "+modelName+".HanselAddOns <- list(\"CointRel\",\"CointRelShort\",\"MssgEW\")";
                    String dataForCointRel = (variableTransformation?"temp_MTS_Data":dName);
                    cmd2+= "\n "+modelName+".HanselAddOns$CointRel <-cbind("+
                            dataForCointRel+ "[,"+VariablesListWithQuotes.split(",")[0]+"]"+",as.matrix("+dataForCointRel+"[,c("+VariablesListWithQuotes+")])%*%Hanseltemp_betaVector)";
                    if (VariablesList.contains("(")){
                       cmd2+="\n";
                       cmd2+= "\n #Note:the following 16 commands are needed since, for example, ca.jo replaces \"log(vname)\" with \"X\" in @X, @R0, and @RK";             
                       cmd2+= "\n colnames("+modelName+"@x) <- c("+VariablesListWithQuotes+")";
                       cmd2+= "\n colnames("+modelName+"@Z0) <- paste(c("+VariablesListWithQuotes+"),\".d\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@Z1)[(length(colnames("+modelName+"@Z1))-"+termsArray.length*(Integer.parseInt(varOptions.maxLag)-1) +"+1):length(colnames("+modelName+"@Z1))]<-c(paste(c("+VariablesListWithQuotes+"),\".dl1\",sep=\"\")";
                       for(int i=2;i<Integer.parseInt(varOptions.maxLag);i++)
                         cmd2+= ",paste(c("+VariablesListWithQuotes+"),\".dl"+i+"\",sep=\"\")";
                       cmd2+=")";
                       cmd2+= "\n colnames("+modelName+"@ZK) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+"@Vorg) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@Vorg) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+"@V) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@V) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+"@W) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@W) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+"@PI) <- paste(c("+VariablesListWithQuotes+"),\".d\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@PI) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+"@GAMMA) <- paste(c("+VariablesListWithQuotes+"),\".d\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@GAMMA)[(length(colnames("+modelName+"@GAMMA))-"+termsArray.length*(Integer.parseInt(varOptions.maxLag)-1) +"+1):length(colnames("+modelName+"@GAMMA))]<-c(paste(c("+VariablesListWithQuotes+"),\".dl1\",sep=\"\")";
                       for(int i=2;i<Integer.parseInt(varOptions.maxLag);i++)
                         cmd2+= ",paste(c("+VariablesListWithQuotes+"),\".dl"+i+"\",sep=\"\")";
                       cmd2+=")";
                       cmd2+= "\n colnames("+modelName+"@R0) <- paste(\"R0.\",c("+VariablesListWithQuotes+"),\".d\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+"@RK) <- paste(\"RK.\",c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+".eigen@V) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+".eigen@V) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n rownames("+modelName+".eigen@W) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+= "\n colnames("+modelName+".eigen@W) <- paste(c("+VariablesListWithQuotes+"),\".l1\",sep=\"\")";
                       cmd2+="\n";
                    }
                   
                    cmd2+= "\n "+modelName+".HanselAddOns$CointRelShort <-cbind("+dataForCointRel+ "[,"+VariablesListWithQuotes.split(",")[0]+"]"+"[(length("+dataForCointRel+ "[,"+VariablesListWithQuotes.split(",")[0]+"]"+")-nrow("+modelName+"@RK)+1):length("+dataForCointRel+ "[,"+VariablesListWithQuotes.split(",")[0]+"]"+")],"+modelName+"@RK%*%Hanseltemp_betaVector)";
                    
                    cmd2+= "\n rm(Hanseltemp_betaVector)";
                    cmd2+= "\n colnames("+modelName+".HanselAddOns$CointRel)<-paste(\"eigenvalue:\",c(\"placeHolder\",round("+modelName+"@lambda,3))[1:(length("+modelName+"@lambda)+1)])";
                    cmd2+= "\n colnames("+modelName+".HanselAddOns$CointRelShort)<-paste(\"eigenvalue:\",c(\"placeHolder\",round("+modelName+"@lambda,3))[1:(length("+modelName+"@lambda)+1)])";

                 } else if (method.equals("ca.po")){
                   
                        if (efeglmextra.cointPOType.equals("Pz")){ 

                             cmd+="\n "+modelName+" <- ca.po("+
                                                            (variableTransformation? "temp_MTS_Data":dName+"[,c("+VariablesListWithQuotes+")]")+
                                                          /*  dName+"[,c("+VariablesListWithQuotes+")]"+*/
                                                           ",demean=\""+efeglmextra.cointPOOptionChosen+
                                                           "\",lag=\""+efeglmextra.cointPOLagChosen+
                                                           "\",type=\"Pz\")";
                            if (VariablesList.contains("(")){
                               cmd2+= "\n colnames("+modelName+"@res) <- c("+VariablesListWithQuotes+")";
                            }
                        } else { // type is Pu
                            
                           cmd="\n "+modelName+"<-list(\""+termsArray[0]+"_left\"";
                            for(int i=1;i<termsArray.length;i++){
                                 cmd+=",\""+termsArray[i]+"_left\"";       
                            }                
                           cmd+=",\"MssgEW\")";                  

                            for(int i=0;i<termsArray.length;i++){
                              formula = "\""+termsArray[i]+"\"";
                            for(int j=0;j<termsArray.length;j++){
                               if (!(termsArray[i].equals(termsArray[j])))
                                   formula = formula + ",\""+termsArray[j].toString()+"\"";
                          
                            }
                            
                            cmd2+="\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_left"+ 
                                      "<- ca.po("+(variableTransformation? "temp_MTS_Data":dName)+"[,c("+formula+")]"+
                                      ",demean=\""+efeglmextra.cointPOOptionChosen+
                                                           "\",lag=\""+efeglmextra.cointPOLagChosen+
                                                           "\",type=\"Pu\")";
                        }
                   
                     }
                    
                    
                 } else if (method.equals("cajolst")){     
                 
                   String cajolst_trend;          
                   if ((varOptions.cointOption.substring(12,22).equals("with trend"))) 
                             cajolst_trend = "TRUE";
                         else
                             cajolst_trend = "FALSE";
                    
                    cmd+="\n "+modelName+" <- cajolst("+dName+"[,c("+VariablesListWithQuotes+")]"+",trend="+cajolst_trend+
                                                      ",K="+varOptions.maxLag+
                                                       varOptions.seasonalDummies+")";
                    cmd2+= "\n "+modelName+".HanselAddOns <- list(\"MssgEW\")";
                }else if (method.equals("ivreg")){
                      cmd+="\n "+modelName+" <- ivreg(formula="+formula+"|"+instruments+",data="+dName+
                                 (this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
				((subset==null || subset.length()==0) ? "" : ",subset = "+subset) /*  +",na.action=na.omit" */ +")";
                      
                    endogenousIndices = "c(";
                    endogenousLength = 0;
                    endogenousVariables = "";

                    formulaSecondStage = formula.split("~")[0] + "~";
                    String potentialComma="";
                    for(int i=0;i<termsArray.length;i++){
                        if (this.instruments.contains(this.termsArray[i])){
                            this.termsFinalStage.addElement(this.termsArray[i]);
                        }
                        else{
                            endogenousVariables = ((endogenousVariables.equals(""))?"":",")+ this.termsArray[i];
                            endogenousIndices = endogenousIndices+potentialComma+(""+((efeglmextra.interceptIncluded)?i+2:i+1));
                            cmdSeparateStages += "\n "+modelName+"$FirstStage$"+this.termsArray[i] +".regression<- lm(formula="+this.termsArray[i]+"~"+this.instruments+",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
				((this.subset==null || this.subset.length()==0) ? "" : ",subset = "+this.subset)+((this.start==null)?"":
				 ")")+
                                "\n "+this.termsArray[i] +".fit_Stg1 <-"+this.modelName+"$FirstStage$"+this.termsArray[i]+".regression$fitted.values";

                            termsFinalStage.addElement(termsArray[i] +".fit_Stg1");
                            potentialComma=",";
                        }
                        formulaSecondStage = formulaSecondStage + this.termsFinalStage.get(i) + ((i<termsArray.length-1)?" + ":"");
                    }
                    endogenousIndices = endogenousIndices + ")";
    
                   
                      cmd2+="\n "+modelName+"$SecondStage <- lm(formula="+formulaSecondStage+",data="+dName+
                        	(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
				((subset==null || subset.length()==0) ? "" : ",subset = "+subset)+
				/*",na.action=na.omit"+*/")";
                }
                else if (method.equals("plm")) {
                    panelModel = true;
                    String randomMethod = "";
                    if (plmPanelType.equals("pooled OLS")){
                        modeltype = "pooling";
                    }else if (plmPanelType.equals("within (\"fixed\") effects")){
                        modeltype = "within";
                    }else if  (plmPanelType.equals("between")){
                        modeltype = "between";  
                    }else if  (plmPanelType.equals("random effects: Swar")){
                        modeltype = "random";
                        randomMethod = "swar";
                    }else if  (plmPanelType.equals("random effects: Walhus")){
                        modeltype = "random";
                        randomMethod = "walhus";
                    }else if  (plmPanelType.equals("random effects: Amemiya")){
                        modeltype = "random";
                        randomMethod = "amemiya";
                    }else if  (plmPanelType.equals("random effects: Nerlove")){
                        modeltype = "random";
                        randomMethod = "nerlove";
                    }else if  (plmPanelType.equals("first-differences")){
                        modeltype = "fd";
                    }

                cmd+="\n "+modelName+"<- "+method+"(formula="+formula+
                                ((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+",effect=c(\""+plmeffect+"\")"+
		                 ",model=c(\""+modeltype+"\")"+
                                (randomMethod.equals("") ? "":",random.method=c(\""+randomMethod+"\")")+(panelIndex.length()==0?"":
                                (Character.isDigit(panelIndex.charAt(0)) ? ",index="+panelIndex:(panelIndex.equals("") ? "":",index=c(\""+panelIndex.replace(",","\",\"")+"\")")))+
                               /* ",na.action=na.omit"+ */ ")";
                }
                else if (method.equals("pvcm")) {
                     panelModel = true;
                     if (pvcmPanelType.equals("within (\"fixed\") effects")){
                        modeltype = "within";
                    }else if  (pvcmPanelType.equals("random effects")){
                        modeltype = "random";
                    }                    
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula+((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+",effect=c(\""+pvcmeffect+"\")"+
		                 ",model=c(\""+modeltype+"\")"+
                                /*",na.action=na.omit"+*/")";
                }  
                            
                
                else if (method.equals("pggls")) {
                    panelModel = true;
                     if (pgglsPanelType.equals("within (\"fixed\") effects")){
                        modeltype = "within";
                    }else if  (pgglsPanelType.equals("random effects")){
                        modeltype = "random";
                    }else if  (pgglsPanelType.equals("pooling")){
                        modeltype = "pooling";
                    }else if  (pgglsPanelType.equals("first-differences")){
                        modeltype = "fd";
                    }                  
                cmd+= modelName+"<- "+method+"(formula="+formula+((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+",effect=c(\""+pgglseffect+"\")"+
		                 ",model=c(\""+modeltype+"\"))"
                                /* + ",na.action=na.omit" */;
                } 

                else if (method.equals("pgmm")) {
                    panelModel = true;
                    String transformation = "";
                     if (pgmmPanelType.equals("1-step, difference GMM")){
                        modeltype = "onestep"; transformation = "d"; 
                    }else if  (pgmmPanelType.equals("1-step, system GMM")){
                        modeltype = "onestep"; transformation = "ld";
                    }else if  (pgmmPanelType.equals("2-step, difference GMM")){
                        modeltype = "twosteps"; transformation = "d";
                    }else if  (pgmmPanelType.equals("2-step, system GMM")){
                        modeltype = "twosteps"; transformation = "ld";
                    }                      
                     
                     
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula+((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+",effect=c(\""+pgmmeffect+"\")"+
                                ",model=c(\""+modeltype+"\")"+
                                (transformation.equals("") ? "":",transformation=\""+transformation+"\"")+
                                /*",na.action=na.omit"+*/ ")";
                }         
                else if (method.equals("pcce")) {
                    panelModel = true;
                    String transformation = "";
                     if (pccePanelType.equals("standard residuals")){
                        modeltype = "standard";
                    }else if  (pccePanelType.equals("cce residuals")){
                        modeltype = "cce"; 
                    }else if  (pccePanelType.equals("ccemg residuals")){
                        modeltype = "ccemg"; 
                    }                  
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula+((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
/*				((subset==null || subset.length()==0) ? "" : ",subset = "+subset)+*/
                                ",residuals=c(\""+modeltype+"\")"+ (efeglmextra.panelTrendIncluded? ",trend=TRUE":"")+
                               /* ",na.action=na.omit"+*/ ")";
                }  
                 else if (method.equals("pmg")) {
                    panelModel = true;
                    String transformation = "";
                     if (pgmmPanelType.equals("mean groups")){
                        modeltype = "mg";
                    }else if  (pgmmPanelType.equals("demeaned mean groups")){
                        modeltype = "dmg"; 
                    }else if  (pgmmPanelType.equals("cce mean groups")){
                        modeltype = "cmg"; 
                    }                
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula+((instruments.length()==0) ? "" : "|"+instruments) +",data="+dName+
				(modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
                                ",residuals=c(\""+modeltype+"\")"+ (efeglmextra.panelTrendIncluded? ",trend=TRUE":"") +
                                /*",na.action=na.omit" +*/")";
                }    
                
               else if (method.equals("spml")) {
                     panelModel = true;
                     if (spmlPanelType.equals("within (\"fixed\") effects")){
                        modeltype = "within";
                    }else if  (spmlPanelType.equals("random effects")){
                        modeltype = "random";
                    }else if  (spmlPanelType.equals("pooling")){
                        modeltype = "pooling";
                    }
                
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula +",data="+dName+",listw="+listwString+
                        ((listw2String.equals(listwString)) ? "":  ((listw2String.equals("")) ? "":  ",listW2="+listw2String)) +
                                ",model=c(\""+modeltype+"\")"+",effect=c(\""+spmleffect+"\")"+
                               ((efeglmextra.spatialLagDepIncluded) ? ",lag=TRUE" : "") +
                                ",spatial.error=\""+spatialErrorPanel+"\""+
                                ((instruments.length()==0) ? "" : ",instruments="+instruments) +
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
                                /*",na.action=na.omit"+*/")";
                
                  
               }  
               
               else if (method.equals("spreml")) {
                     panelModel = true;
                    
                     
                
                cmd+="\n "+modelName+"<- "+method+"(formula="+formula +",data="+dName+",w="+listwString+
                       ((listw2String.equals(listwString)) ? "":  ((listw2String.equals("")) ? "":  ",listW2="+listw2String)) +
                        ((efeglmextra.spatialLagDepIncluded) ? ",lag=TRUE" : "") +
                                ",model=c(\""+modeltype+"\")"+",errors=\""+spremlErrors+"\""+
                        ((efeglmextra.hess) ? ",hess=TRUE" : "") +
                        ((efeglmextra.quiet) ? ",quiet=TRUE" : "") +
                        ((efeglmextra.zerosInitval) ? ",initval=\"zeros\"" : ",initval=\"estimate\"") +
			(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+
                                /*",na.action=na.omit"+*/")";
                
                }  
                else if (method.equals("spgm")) {
                     panelModel = true;
                     if (spgmPanelType.equals("within (\"fixed\") effects")){
                        modeltype = "within";
                    }else if  (spgmPanelType.equals("random effects")){
                        modeltype = "random";
                    }  
                     
                cmd+="\n "+modelName+"<-spgm(formula="+formula +",data="+dName+",listw="+listwString+
                                ",model=c(\""+modeltype+"\")"+
                                ((efeglmextra.spatialLagDepIncluded) ? ",lag=TRUE" : "") +
                                ((efeglmextra.spatialError) ? ",spatial.error=TRUE" : "") +
                                ((spgmMoments.equals("initial")) ? "":",moments=\""+spgmMoments+"\"") +
                                ((instruments.length()==0) ? "" : ",instruments="+instruments) +
                                ((efeglmextra.verbose) ? ",verbose=TRUE" : "") +
                                ((efeglmextra.spgmMethod.equals("w2sls")) ? "":",method="+efeglmextra.spgmMethod) +
				(this.modelWeights.equals("") ? "" : ",weights="+this.modelWeights)+")";
                }
                 
                 
                else if (method.equals("cipstest")) {
                    panelModel = true;
                    String transformation = "";
                     if (cipsURTPanelType.equals("mean groups")){
                        modeltype = "mg";
                    }else if  (cipsURTPanelType.equals("demeaned mean groups")){
                        modeltype = "dmg"; 
                    }else if  (cipsURTPanelType.equals("cce mean groups")){
                        modeltype = "cmg"; 
                    }

                }         
                else if (this.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
               
                      /* cmd created interatively later */
             

                }
                else {

                cmd+="\n "+ modelName+" <- "+method+"(formula="+formula+family+",data="+dName+
                        (this.EstimationMethod.equals("Binary Logit")||this.EstimationMethod.equals("Binary Probit")?",x=TRUE":"")+
                        (modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset==null || subset.length()==0) ? "" : ",subset = "+subset)
                       /* +",na.action=na.omit" */ +")";             
                }
                
              if (this.EstimationMethod.equals("Cointegration Test - Engle & Granger")){
                int trendChoice = 0; 
                if (efeglmextra.cointEGOptionChosen.equals("linear trend"))
                   trendChoice = 1;
                else if (efeglmextra.cointEGOptionChosen.equals("quadratic trend"))
                    trendChoice = 2;

                
                
                cmdpre+="\n "+modelName+"<-list(\""+termsArray[0].replace("(","_").replace(")", "") +","+termsArray[0].replace("(","_").replace(")", "") +"_EGTest\"";
                for(int i=1;i<termsArray.length;i++){
                         cmdpre+=",\""+termsArray[i]+","+termsArray[i]+"_EGTest\"";       
                    }                
                cmdpre+=",\"TIME\",\"MssgEW\")"; 

                 if (!(efeglmextra.cointEGOptionChosen.equals("no trend"))) {
                         cmdpre+="\n "+modelName+"$TIME <- ts(1:length("+termsArray[0]+"), start = start("+termsArray[0]+"), frequency = frequency("+termsArray[0]+"))";
                      }
                        
                cmdClassic += cmdpre;
                String cmdEG = "";
                
                                      String[][][][] MacKinnonResponseSurface = 
                                     new String [][][][]{
                                         /*Table 2: Critical values for No Trend*/
                                         {{{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                             
                                         {{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                                                                      
                                         {{"-3.89644","-10.9519","-22.527","0"},
                                         {"-3.33613","-6.1101","-6.823","0"},
                                         {"-3.04445","-4.2412","-2.720","0"}},
                                         
                                         {{"-4.29374","-14.4354","-33.195","47.433"},
                                         {"-3.74066","-8.5631","-10.852","27.982"},
                                         {"-3.45218","-6.2143","-3.718","0"}},
                                         
                                         {{"-4.64332","-18.1031","-37.972","0"},
                                         {"-4.09600","-11.2349","-11.175","0"},
                                         {"-3.81020","-8.3931","-4.137","0"}},
                                         
                                         {{"-4.95756","-21.8883","-45.142","0"},
                                         {"-4.41519","-14.0406","-12.575","0"},
                                         {"-4.13157","-10.7417","-3.784","0"}},
                                         
                                         {{"-5.24568","-25.6688","-57.737","88.639"},
                                         {"-4.70693","-16.9178","-17.492","60.007"},
                                         {"-4.42501","-13.1875","-5.104","27.877"}},
                                         
                                         {{"-5.51233","-29.5760","-69.398","164.295"},
                                         {"-4.97684","-19.9021","-22.045","110.761"},
                                         {"-4.69648","-15.7315","-6.922","67.721"}},
                                         
                                         {{"-5.76202","-33.5258","-82.189","256.289"},
                                         {"-5.22924","-23.0023","-24.646","144.479"},
                                         {"-4.95007","-18.3959","-7.344","94.872"}},
                                         
                                         {{"-5.99742","-37.6572","-87.365","248.316"},
                                         {"-5.46697","-26.2057","-26.627","176.382"},
                                         {"-5.18897","-21.1377","-9.484","172.704"}},
                                         
                                         {{"-6.22103","-41.7154","-102.680","389.330"},
                                         {"-5.69244","-29.4521","-30.994","251.016"},
                                         {"-5.41533","-24.006","-7.514","163.049"}},
                                         
                                         {{"-6.43377","-46.0084","-106.809","352.752"},
                                         {"-5.90714","-32.8336","-30.275","249.994"},
                                         {"-5.63086","-26.9693","-4.083","151.427"}},
                                         
                                         {{"-6.63790","-50.2095","-124.156","579.622"},
                                         {"-6.11279","-36.2681","-32.505","314.802"},
                                         {"-5.83724","-29.9864","-2.686","184.116"}}  

                                         },
                                         
                                         /*Table 3: Critical values for Linear Trend*/
                                         {{{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                             
                                         {{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                             
                                         {{"-4.32762","-15.4387","-35.679","0"},
                                         {"-3.78057","-9.5106","-12.074","0"},
                                         {"-3.49631","-7.0815","-7.538","21.892"}},
                                         
                                         {{"-4.66305","-18.7688","-49.793","104.244"},
                                         {"-4.11890","-11.8922","-19.031","77.332"},
                                         {"-3.83511","-9.0723","-8.504","35.403"}},
                                                                                  
                                         {{"-4.96940","-22.4694","-52.599","51.314"},
                                         {"-4.42871","-14.5876","-18.228","39.647"},
                                         {"-4.14633","-11.2500","-9.873","54.109"}},
                                         
                                         {{"-5.25276","-26.2183","-59.631","50.646"},
                                         {"-4.71537","-17.3569","-22.660","91.359"},
                                         {"-4.43422","-13.6078","-10.238","76.781"}},
                                         
                                         {{"-5.51727","-29.9760","-75.222","202.253"},
                                         {"-4.98228","-20.3050","-25.224","132.030"},
                                         {"-4.70233","-16.1253","-9.836","94.272"}},
                                         
                                         {{"-5.76537","-33.9165","-84.312","245.394"},
                                         {"-5.23299","-23.3328","-28.955","182.342"},
                                         {"-4.95405","-18.7352","-10.168","120.575"}},
                                         
                                         {{"-6.00003","-37.8892","-96.428","335.920"},
                                         {"-5.46971","-26.4771","-31.034","220.165"},
                                         {"-5.19183","-21.4328","-10.726","157.955"}},
                                         
                                         {{"-6.22288","-41.9496","-109.881","466.068"},
                                         {"-5.69447","-29.7152","-33.784","273.002"},
                                         {"-5.41738","-24.2882","-8.584","169.891"}},
                                         
                                         {{"-6.43551","-46.1151","-120.814","566.823"},
                                         {"-5.90887","-33.0251","-37.208","346.189"},
                                         {"-5.63255","-27.2042","-6.792","177.666"}},
                                         
                                         {{"-6.63894","-50.4287","-128.997","642.781"},
                                         {"-6.11404","-36.4610","-36.246","348.554"},
                                         {"-5.83850","-30.1995","-5.163","210.338"}},

                                         {{"-6.83488","-54.7119","-139.800","736.376"},
                                         {"-6.31127","-39.9676","-37.021","406.051"},
                                         {"-6.03650","-33.2381","-6.606","317.776"}}      
                                                 
                                         },
                                          
                                         /*Table 4: Critical values for Quadratic Trend*/
                                         {{{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                             
                                         {{"na","na","na","na"},
                                         {"na","na","na","na"},
                                         {"na","na","na","na"}},
                                             
                                         {{"-4.69276","-20.2284","-64.919","88.884"},
                                         {"-4.15387","-13.3114","-28.402","72.741"},
                                         {"-3.87346","-10.4637","-17.408","66.313"}},
                                         
                                         {{"-4.99071","-23.5873","-76.924","184.782"},
                                         {"-4.45311","-15.7732","-32.316","122.705"},
                                         {"-4.17280","-12.4909","-17.912","83.285"}},
                                         
                                         {{"-5.26780","-27.2836","-78.971","137.871"},
                                         {"-4.73244","-18.4833","-31.875","111.817"},
                                         {"-4.45268","-14.7199","-17.969","101.920"}},
                                         
                                         {{"-5.52826","-30.9051","-92.490","248.096"},
                                         {"-4.99491","-21.2360","-37.685","194.208"},
                                         {"-4.71587","-17.0820","-18.631","136.672"}},
                                         
                                         {{"-5.77379","-34.7010","-105.937","393.991"},
                                         {"-5.24217","-24.2177","-39.153","232.528"},
                                         {"-4.96397","-19.6064","-18.858","174.919"}},
                                         
                                         {{"-6.00609","-38.7383","-108.605","365.208"},
                                         {"-5.47664","-27.3005","-39.498","246.918"},
                                         {"-5.19921","-22.2617","-17.910","208.494"}},
                                         
                                         {{"-6.22758","-42.7154","-119.622","421.395"},
                                         {"-5.69983","-30.4365","-44.300","345.480"},
                                         {"-5.42320","-24.9686","-19.688","274.462"}},
                                         
                                         {{"-6.43933","-46.7581","-136.691","651.380"},
                                         {"-5.91298","-33.7584","-42.686","346.629"},
                                         {"-5.63704","-27.8965","-13.880","236.975"}},
                                         
                                         {{"-6.64235","-50.9783","-145.462","752.228"},
                                         {"-6.11753","-37.056","-48.719","473.905"},
                                         {"-5.84215","-30.8119","-14.938","316.006"}},
                                         
                                         {{"-6.83743","-55.2861","-152.651","792.577"},
                                         {"-6.31396","-40.5507","-46.771","487.185"},
                                         {"-6.03921","-33.38950","-9.122","285.164"}},
                                         
                                         {{"-7.02582","-59.6037","-166.368","989.879"},
                                         {"-6.50353","-44.0797","-47.242","543.889"},
                                         {"-6.22941","-36.9673","-10.868","418.414"}}

                                         }
                                         
                                     }; 
                
                 for(int i=0;i<termsArray.length;i++){
                            formula = termsArray[i]+"~";
                            boolean firstRHSTerm = true;
                            for(int j=0;j<termsArray.length;j++){
                               if (!(termsArray[i].equals(termsArray[j])))
                                  if (firstRHSTerm){
                                      formula = formula + termsArray[j].toString();
                                      firstRHSTerm = false;
                                  }
                                  else          
                                      formula = formula + "+"+ termsArray[j];
                            }
                      if (efeglmextra.cointEGOptionChosen.equals("linear trend")) 
                         /*formula = formula + " + TIME";*/
                         formula = formula + "+"+modelName+"$TIME"; 
                      else if (efeglmextra.cointEGOptionChosen.equals("quadratic trend"))
                         formula = formula +"+" + modelName+"$TIME + I("+modelName+"$TIME^2)";
                      
                            cmdEG="\n\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "") +" <- "+"dynlm(formula="+formula+",data="+dName+
                                     (modelWeights.length()==0 ? "" : ", weights= "+modelWeights)+
		    	 	     ((subset==null || subset.length()==0) ? "" : ",subset = "+subset)+
				     /* ",na.action=na.omit"+ */ ")";
                                                String lagSelectionMethod = ((unitRootTestOptions.infoCriterion2.equals("None, just use given lag")||
                                                                              unitRootTestOptions.infoCriterion2.equals("")||
                                                                               unitRootTestOptions.infoCriterion2.equals(null) 
                                                                               )? "Fixed": unitRootTestOptions.infoCriterion2);
                            cmdb="\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest <- ur.df("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"$resid,type = \"none\",lags="+unitRootTestOptions.maxLagEG+",selectlags = \""+lagSelectionMethod +"\")";
                            cmd2="\n Hanseltemp_TMacKinnon <- NROW("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest@res)";
                            
                            
                             cmd2+="\n row.names("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest@cval) <-"+
                                        ((trendChoice==0)?"\"tau_c\"":((trendChoice==1)?"\"tau_ct\"":"\"tau_ctt\""));
                             cmd2+="\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest@cval[1,1] <-"+
                                                            MacKinnonResponseSurface[trendChoice][termsArray.length][0][0]+
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][0][1]+"/Hanseltemp_TMacKinnon" +
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][0][2]+"/(Hanseltemp_TMacKinnon^2)"+
                                                          "+"+MacKinnonResponseSurface[trendChoice][termsArray.length][0][3]+"/(Hanseltemp_TMacKinnon^3)";
                             cmd2+="\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest@cval[1,2] <-"+
                                                            MacKinnonResponseSurface[trendChoice][termsArray.length][1][0]+
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][1][1]+"/Hanseltemp_TMacKinnon" +
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][1][2]+"/(Hanseltemp_TMacKinnon^2)"+
                                                          "+"+MacKinnonResponseSurface[trendChoice][termsArray.length][1][3]+"/(Hanseltemp_TMacKinnon^3)";
                             cmd2+="\n "+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest@cval[1,3] <-"+
                                                            MacKinnonResponseSurface[trendChoice][termsArray.length][2][0]+
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][2][1]+"/Hanseltemp_TMacKinnon" +
                                                          " "+MacKinnonResponseSurface[trendChoice][termsArray.length][2][2]+"/(Hanseltemp_TMacKinnon^2)"+
                                                          "+"+MacKinnonResponseSurface[trendChoice][termsArray.length][2][3]+"/(Hanseltemp_TMacKinnon^3)";
                            
                            cmd+= (i==0?cmdErrorPrefix:cmdErrorPrefix2) + cmdEG + cmdErrorSuffix +cmdErrorPrefix2 + cmdb + cmdErrorSuffix + "\n if (exists(\""+modelName+"\")){"+cmd2 +"}";
                            cmdClassic+= cmdEG + cmdb + "\n if (exists(\""+modelName+"\")){"+ cmd2 + "}";;         
                    }

                 cmd+="\n\n rm(Hanseltemp_TMacKinnon)\n";
                 cmdClassic+="\n\n rm(Hanseltemp_TMacKinnon)\n"; 
                 
                 cmd = cmdpre +cmd;
                       
                   } else {
                     cmdClassic += cmd;
                     if (method.equals("ivreg")){
                       cmdClassic+= "\n\n # The following lines run the 1st stage regression(s) separately, append them to the ivreg object,"+
                                    "\n # run the 2nd stage regression and append that also to the ivreg object. Some information from the"+
                                    "\n # the separate stages is useful"; 
                       cmdClassic+= cmdSeparateStages;
                     }
                     cmdClassic += cmd2;
                     
                      String cmdSaveWarnings ="";
                      cmd = cmdpreVarAdj+cmdErrorPrefix + cmd + cmdErrorSuffix + cmdSaveWarnings+ (cmdb.equals("")?"":cmdb) + 
                          ((cmdSeparateStages.equals("")&cmd2.equals(""))?"":
                            "\nif (length(.gui.working.env$Hansel_temp$errorSave)==0){" +  cmdSeparateStages + cmd2 + cmdpostVarAdj + "\n}");
                    cmdClassic += cmdpostVarAdj;
                   } 

               /*  Deducer.execute(cmd);*/  //for debugging. Be careful with this, since execute commads can have a different order of being processed than other commands.
                 String outputCall = "c((if(!("+Hansel.hanselEnv +"$Hansel_temp$MssgEW[1]==\"NULL\"))"+
                                 " {c(\"\",as.character("+Hansel.hanselEnv +"$Hansel_temp$MssgEW),\"\n\",\"\")}))";
                   
                 try{
                    Deducer.eval(cmd);
                    warningString  = Deducer.eval(outputCall).asStrings();
                     }catch(Exception e){
                        new ErrorMsg(e);
                     }
                /* Deducer.execute(cmd); */    //debugger  
                   
                    errorMessageString ="";
    
                 /*  JOptionPane.showMessageDialog(null, "errorMessageString="+errorMessageString);*/

                } /* end to question "Is this a previous estimation?*/
             
                       String expvar = termsArray.toString().substring(1,termsArray.toString().length()-1);
                           for(int i =0;i<expvar.length();i++){
                               if ((expvar.substring(i,i+1).equals("*"))||(expvar.substring(i,i+1).equals(":"))){
                                    interactionTerm = true;
                               }
                           }
                        
                      String[] coefListStringArray;      
                      if (!(this.method.equals("VAR")||this.method.equals("vec2var")||this.method.equals("ca.jo")||this.method.equals("ca.po")||this.method.equals("cajolst"))){

                      int startCoeff; 
                        if(this.method.equals("errorsarlm")||this.method.equals("lagsarlm")
                                          ||this.method.equals("gstsls")||this.method.equals("stsls")
                                          ||this.method.equals("lagmess")||this.method.equals("spml"))
                            startCoeff = 1;
                        else if  (this.method.equals("sacsarlm"))
                            startCoeff = 2;
                        else
                            startCoeff = 0;
                         
                       try{ 
                             
                             coefListStringArray  = Deducer.eval("names(coef("+modelName+"))").asStrings();
                              coefList = new DefaultListModel();
                              int j = startCoeff ; 
                              if (coefListStringArray[startCoeff].equals("(Intercept)")) {
                                  
                                  j = startCoeff + 1;
                              } else {
                                  efeglmextra.interceptIncluded = false;
                                  j = startCoeff;
                              }
                              numExplanatoryVariables = coefListStringArray.length - j;
                             /* JOptionPane.showMessageDialog(null, "debug startcoeff,j="+startCoeff+","+j);
                              JOptionPane.showMessageDialog(null, "debug coefListStringArray.length="+coefListStringArray.length);
                              JOptionPane.showMessageDialog(null, "debug numExplanatoryVariables="+numExplanatoryVariables);*/
                              
                              for(int i=0;i<(termsFinalStage.getSize());i++){
                                coefList.addElement(termsFinalStage.toArray()[i]);
                                if (termsFinalStage.toArray()[i].equals(coefListStringArray[j])){
                                 j = j + 1;
                                } else {
                                   while (!(termsFinalStage.toArray()[i].equals(coefListStringArray[j]))){
                                       coefList.addElement(coefListStringArray[j]);
                                       j = j + 1;
                                   }
                              }
                                }
                              
                            }catch(Exception e){
                           new ErrorMsg(e);
                                   }        
                        };
                     
                       String modelExistsString = "TRUE";
                       try{ 
                         modelExistsString  = Deducer.eval("exists(\""+modelName+"\")").asString();                            
                        }catch(Exception e){
                       new ErrorMsg(e);
                               }
                       if (modelExistsString.equals("FALSE"))
                               modelExists = false;
                       String noErrorExistsString = "TRUE";
                       try{ 
                         noErrorExistsString = Deducer.eval("is.null(.gui.working.env$Hansel_temp$errorSave)").asString(); 
                        }catch(Exception e){
                       new ErrorMsg(e);
                               }
                       if (noErrorExistsString.equals("FALSE"))
                               errorExists = true;
                       
                       
                       int messageCount = 0;
                       boolean callStarted = false;
        if (modelExists) {             
                       String warningMessageString ="";     
                        messageCount = 0;
                        callStarted = false;
                        if ((warningString.length>1)){
                            warningMessageString = warningMessageString + "Warning messages:";
                            for(int i=0;i<warningString.length;i++) {
                                    if (warningString[i].equals("$message")) {
                                      messageCount = messageCount + 1;  
                                      warningMessageString = warningMessageString + "\\n"+messageCount+":";
                                    } else if  (warningString[i].equals("$call")) {
                                      warningMessageString = warningMessageString + "\\n    (in ";
                                      callStarted = true;
                                    } else if (warningString[i].equals("")) {
                                      if (callStarted){
                                          warningMessageString = warningMessageString + ")";
                                          callStarted = false;
                                      }
                                    } else {
                                      if (warningString[i].contains("\"")) {
                                          warningMessageString = warningMessageString + " "+warningString[i].split("\"")[1];
                                      } else
                                         warningMessageString = warningMessageString + " "+warningString[i];
                                    }
                            }  
                            
                          if (this.method.equals("ca.jo")||this.method.equals("cajolst")){
                            Deducer.eval("if(!("+Hansel.hanselEnv +"$Hansel_temp$MssgEW[1]==\"NULL\")) "+modelName +".HanselAddOns$MssgEW <-\""+warningMessageString+"\"");
                          } else if (this.method.equals("ca.po")){
                            Deducer.eval("if(!("+Hansel.hanselEnv +"$Hansel_temp$MssgEW[1]==\"NULL\")) "+modelName +".MssgEW <-\""+warningMessageString+"\"");    
                          }else {
                            Deducer.eval(modelName +"$HanselAddOns$MssgEW <-\""+warningMessageString+"\"");
                          }

                        } else {          
                          if (this.method.equals("ca.jo")||this.method.equals("cajolst")){
                            Deducer.eval(modelName +".HanselAddOns$MssgEW <-\"\"");
                          } else if (this.method.equals("ca.po")){
                            //removed the following two lines to remove clutter when no error or warning.
                            //Deducer.eval(modelName +".MssgEW <-\"\"");    
                          }else {
                            Deducer.eval(modelName +"$HanselAddOns$MssgEW <-\"\"");
                          }
                        }
            
            
             if (method.equals("plm")||method.equals("spml")||method.equals("spreml")||method.equals("spgm")) {

                      if (this.efeglmextra.previousEstimation){
                            // If had data could do the stuff below. for previous models. Need more code for that 
                      } else {
                        if (instrumentsArray.length==0) {
                            if(termsArray.length>1) {
                                if (modeltype.equals("within")) 
                                   cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(crossprod(t(as.matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])),"+modelName+ (method.equals("plm")?"$coef))":"$coef[2:length("+modelName+"$coef)]))"); 
                               else 
                                   cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(crossprod(t(as.matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])),"+modelName+ (method.equals("plm")?"$coef[2:length("+modelName+"$coef)]))":"$coef[2:length("+modelName+"$coef)]))");     
                                   
                             }
                             else {
                               if (modeltype.equals("within")) 
                                   cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(crossprod(t(matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])),"+modelName+(method.equals("plm")?"$coef))":"$coef[2:length("+modelName+"$coef)]))"); 
                               else 
                                   cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(crossprod(t(matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])),"+modelName+(method.equals("plm")?"$coef[2:length("+modelName+"$coef)]))":"$coef[2:length("+modelName+"$coef)]))");     
                            }
                        }
                        else {                     
                            if (modeltype.equals("within")) 
                                cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])%*%matrix("+modelName+(method.equals("plm")?"$coef))":"$coef[2:length("+modelName+"$coef)]))");                            
                            else 
                                 cmdfit="\n"+modelName+"$HanselAddOns$fittedValuesExclEffects <-as.numeric(matrix("+modelName+"$model[,2:(length("+modelName+"$model)-"+instrumentsArray.length +")])%*%matrix("+modelName+(method.equals("plm")?"$coef[2:length("+modelName+"$coef)]))":"$coef[2:length("+modelName+"$coef)]))");     
                                
                        }
                      } 
                      
                       cmdClassic+=(cmdfit); 
                         try{
                                Deducer.eval(cmdfit);
                               }catch(Exception e){

                            new ErrorMsg(e);
                            }
                    } 
             
                   if (method.equals("plm")||method.equals("pvcm")||method.equals("pggls")||method.equals("pgmm")
                      ||method.equals("pcce")||method.equals("pmg")||method.equals("spml")||method.equals("spgm") ) {
                   panelModel = true;
                   String[] modelPanelCSLevels = new String[]{};
                   this.modelPanelCSLevelsList = new DefaultListModel();
                   try{
                     this.modelPanelCSVar = Deducer.eval("colnames("+this.data+"[1])").asString();
                     modelPanelCSLevels = Deducer.eval("levels(as.factor("+this.data+"$"+this.modelPanelCSVar+"))").asStrings();
                     this.modelPanelTimeVar = Deducer.eval("colnames("+this.data+"[2])").asString();
                     for(int i=0;i<(modelPanelCSLevels.length);i++){
                     this.modelPanelCSLevelsList.addElement(modelPanelCSLevels[i]);    
                     }                                        
                   }catch(Exception e){
                     new ErrorMsg(e);
                    }
                }
             } 
             
            } // end of what to do if changevcov is false 
         
         

        vcovadjModel="";   
        if (modelExists){      
            
          if ((!(this.efeglmextra.previousEstimation &!changevcov))) {            
             if ((method.equals("glm")||method.equals("lm")||(method.equals("dynlm"))||method.equals("ivreg"))&!glmvcov.equals("No correction")) {                            
             if (glmvcov.equals("HAC"))
                vcovadjModel = ",vcov=vcovHAC("+modelName+")";
                
             else 
                vcovadjModel = ",vcov=vcovHC("+modelName+",type='"+glmvcov+"')"; 
            }
             else  if ((method.equals("tobit"))&!tobitvcov.equals("No correction")) {                            
             if (tobitvcov.equals("HAC"))
                vcovadjModel = ",vcov=sandwich("+modelName+",meat. = meatHAC)";
                
             else 
                vcovadjModel = ",vcov=sandwich"; 
            }
             
             else if ((method.equals("plm")||(method.equals("spml"))) &!(plmvcov.equals("No correction"))){
                    String[] robustChoices;
                        robustChoices = plmvcov.split(",");

                       
                        if (robustChoices[0].equals("arellano")||robustChoices[0].equals("white1")||robustChoices[0].equals("white2")){
                            vcovadjModel = ",vcov=vcovHC("+modelName+",method=\""+robustChoices[0]+"\""+
                                                          ",type=\""+robustChoices[1]+"\""+
                                                          ",cluster=\""+robustChoices[2]+"\""+")";
                        } else if (robustChoices[0].equals("Beck & K")||robustChoices[0].equals("Beck&K")){
                            vcovadjModel = ",vcov=vcovBK("+modelName+",type=\""+robustChoices[1]+"\""+
                                                          ",cluster=\""+robustChoices[2]+"\""+
                                                          (robustChoices.length==4 ? ",diagonal=TRUE":"")+")";
                        } else if (robustChoices[0].equals("Driscoll & K")||robustChoices[0].equals("Driscoll&K")){
                            vcovadjModel = ",vcov=vcovSCC("+modelName+",type=\""+robustChoices[1]+"\""+
                                                          (robustChoices.length==3 ? ","+robustChoices[2]:"")+")";
                        }        
     
                    }
          }
          if (vcovadjModel.equals("")){
              vcovadj = "vcov("+modelName+")";
          } else
              vcovadj = vcovadjModel.toString().replace(",vcov=","");
          
          

                   
                    
                   if (((method.equals("lm"))||(method.equals("dynlm"))||(method.equals("glm"))||(method.equals("ivreg"))||(method.equals("tobit"))
                           ||method.equals("errorsarlm")||method.equals("GMerrorsar")
                           ||method.equals("lagsarlm")||method.equals("sacsarlm")||method.equals("gstsls")
                           ||method.equals("stsls")||method.equals("spautolm")||method.equals("lagmess"))
                           ||(panelModel) ){
                      
                           
                            try{
                                modelCall = Deducer.eval("paste(capture.output("+modelName+"$call),collapse=\"\")").asString();
                                
                               }catch(Exception e){
                            new ErrorMsg(e);
                            }
                            if (this.efeglmextra.previousEstimation&modelCall.contains("data =")){
                                    data = modelCall.split("data =")[1];
                                    if (data.contains(",")){
                                        data = data.split("\\,")[0];
                                    } else if (data.contains(")")){
                                        data = data.split("\\)")[0];
                                    }
                            }
                            
                            if (dependentVar.equals("")&modelCall.contains("formula =")){
                            dependentVar =  modelCall.split("formula = ")[1].split(" ~")[0];
                                    
                            formula = modelCall.split("formula = ")[1];
                            formula = formula.substring(0,formula.length()-1);
                            formula = formula.split(", ")[0];
                            }
                            
                           if ((method.equals("lm"))||(method.equals("dynlm"))||(method.equals("dynlm"))||(method.equals("glm"))||(method.equals("ivreg"))||(method.equals("tobit"))||
                               method.equals("errorsarlm")||method.equals("lagsarlm")||method.equals("sacsarlm")||(panelModel)){
                                efeaetext.paramformula=makeformulaparam();
                                efeglmextra.titleModelforCoefTest = "Theoretical Model with Coefficients";
                                efeglmextra.titleManualAltCoefTest = "Manual alternative: provide restrictions, e.g. b(1)=1, b(1)=b(2), 3*b(1)+b(2)=0, separating restrictions with a comma";
                                }

                            
                            if (this.efeglmextra.previousEstimation){
                                if (dependentVar.equals("")){
                                    if (modelCall.contains("formula =")){
                                        dependentVar =  modelCall.split("formula = ")[1].split(" ~")[0];
                                    }
                                }                         
                                if (modelCall.contains("formula =")){
                                    formula = modelCall.split("formula = ")[1];
                                    formula = formula.substring(0,formula.length()-1);
                                    formula = formula.split(", ")[0];
                                }
                            }else if (dependentVar.equals("")&modelCall.contains("formula =")){
                                dependentVar =  modelCall.split("formula = ")[1].split(" ~")[0];
                                formula = modelCall.split("formula = ")[1];
                                formula = formula.substring(0,formula.length()-1);
                                formula = formula.split(", ")[0];
                            }
                            
                            if (modelCall.contains("weights =")){
                               modelWeights =  modelCall.split("weights =")[1].split(",")[0];
                               if (this.method.equals("lm")||(this.method.equals("dynlm"))||(this.method.equals("dynlm"))) this.EstimationMethod = "Weighted Least Squares"; 
                            } else
                               modelWeights =  "";
                            if (modelCall.contains("subset ="))
                               modelSubset =  modelCall.split("subset =")[1].split(",")[0];
                            else
                               modelSubset =  "";
                           if (modelCall.contains("|")){
                               modelInstruments = modelCall.split("\\|")[1];
                               modelInstruments = modelInstruments.substring(0,modelInstruments.length()-1);
                               modelInstruments = modelInstruments.split(", ")[0].replace(" +",",");
                               
                           }else
                               modelInstruments =  "";

                           modelPlmEffect =  "";
                           modelPlmType =  "";
                           if (method.equals("plm"))

                               try{
                                 modelPlmEffect = Deducer.eval(""+modelName+"$args$model").asString();
                                 modelPlmType = Deducer.eval(""+modelName+"$args$effect").asString();
                               }catch(Exception e){
                                 new ErrorMsg(e);
                                }

                          } else if (method.equals("ca.jo")){
                                efeaetext.paramformula=makeformulaparamECM();
                           } 
                   
                   
        }
//*********Section 2: End Provide Model Estimation Statements *******************************************                 
//*********Section 3: Evaluation of Model Estimation Statements for Estimates with only Classic Output *****                 
                            
               ArrayList tmp = new ArrayList();
               String outputCall;
     
            if (errorExists){
                          try{ 
                             errorString  = Deducer.eval("capture.output("+Hansel.hanselEnv+"$Hansel_temp$errorSave)").asStrings();   
                             Deducer.eval(".hansel.working.env$errorSave<-\"no failure\"");
		            }catch(Exception e){
                                Deducer.eval(".hansel.working.env$errorSave<-\"failure\"");
			   new ErrorMsg(e);
                           }
                errorMessageString = "";
                boolean errorCallStarted = false;
                int errorMessageCount = 0;
                        errorMessageString = errorMessageString + "Error messages:";
                        for(int i=0;i<errorString.length;i++) {
                                if (errorString[i].equals("$message")) {
                                  errorMessageCount = errorMessageCount + 1;  
                                  errorMessageString = errorMessageString + "\n"+errorMessageCount+":";
                                } else if  (errorString[i].equals("$call")) {
                                  errorMessageString = errorMessageString + "\n    (in ";
                                  errorCallStarted = true;
                                } else if (errorString[i].equals("")) {
                                  if (errorCallStarted){
                                      errorMessageString = errorMessageString + ")";
                                      errorCallStarted = false;
                                  }
                                } else {
                                  if (errorString[i].contains("\"")) {
                                      errorMessageString = errorMessageString + " "+errorString[i].split("\"")[1];
                                  } else
                                     errorMessageString = errorMessageString + " "+errorString[i];
                                }
                        }  

                 tmp.add("There appears to be a problem.\n\n");
                 tmp.add(errorMessageString);
                 tmp.add("End Classic");
                 tmp.add("<br><b>");
                 tmp.add("There appears to be a problem.<br><br>");
                 tmp.add(errorMessageString.replace("\n","<br>"));
            } else {  
                        
               if (method.equals("cipstest")){ 
                   try{
                    Deducer.eval(cmd);
                     }catch(Exception e){
                        new ErrorMsg(e);
                     }
                   outputCall = "cipstest(pdata.frame("+data+")$"+outcomes.toString().substring(1,outcomes.toString().length()-1)+
                                ",type=\"none\",model=\""+modeltype+"\",truncated=FALSE)";
                   cmdClassic+=(outputCall);
                   cmdClassic+="\n"+outputCall;
                        try{
                        out  = Deducer.eval("c(capture.output("+outputCall+"))").asStrings();
                        tmp.add(outputCall);

                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                           }

                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }
                           tmp.add("End Classic");
                           tmp.add("No TP view");
           
                 } else if (method.equals("vec2var")){


                   outputCall = modelName;
                   cmdClassic+="\n "+modelName;
                        efeaetext.paramformula="";
                        Boolean afterFirstEquation=false;
                        Integer numberOfExplVars = 0;
                        try{
                        out  = Deducer.eval("c(capture.output("+outputCall+"))").asStrings();
                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");

                           }

                           tmp.add("End Classic");
                           tmp.add("No TP view");
                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }                        
                           
                 } else if (method.equals("VAR")){                   
                   
                   outputCall = "c(capture.output(summary("+modelName+"), Bcoef("+modelName+")))";
                   cmdClassic+="\n summary("+modelName+") \n Bcoef("+modelName+") ";
                        efeaetext.paramformula="";
                        Boolean afterFirstEquation=false;
                        Integer numberOfExplVars = 0;
                        try{
                        out  = Deducer.eval(outputCall).asStrings();
                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                                if (out[i].contains("Estimation results for equation")){                   
                                         if (!afterFirstEquation) {
                                             efeaetext.paramformula =  out[i+2]; 
                                             numberOfExplVars = out[i+2].length()-out[i+2].replace("+","").length()+1;
                                             efeaetext.unrestrictedVARMatrix = ""; 
                                             for(int j=0;j<numberOfExplVars;j++){
                                                 efeaetext.unrestrictedVARMatrix = efeaetext.unrestrictedVARMatrix + "1, ";
                                             }
                                         } else{ 
                                             efeaetext.paramformula = efeaetext.paramformula + "\n" + out[i+2];
                                             efeaetext.unrestrictedVARMatrix = efeaetext.unrestrictedVARMatrix + "\n"; 
                                             for(int j=0;j<numberOfExplVars;j++){
                                                 efeaetext.unrestrictedVARMatrix = efeaetext.unrestrictedVARMatrix + "1, ";
                                             }
                                         }
                                         
                                         afterFirstEquation=true; 
                                }
                           }
                           efeaetext.unrestrictedVARMatrix=efeaetext.unrestrictedVARMatrix.substring(0,efeaetext.unrestrictedVARMatrix.length()-2);
                           efeaetext.restrictedVARMatrixDims= "),nrow="+termsArray.length+",ncol="+numberOfExplVars+",byrow=TRUE)";
                           tmp.add("End Classic");
                           tmp.add("No TP view");
                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }
                  }else if ((method.equals("ca.jo"))||(method.equals("cajolst"))||(method.equals("ca.po")&(efeglmextra.cointPOType.equals("Pz")))||(method.equals("pggls"))||(method.equals("pgmm")) ){
                       String summaryCall = "summary("+modelName+
                                  ((method.equals("pgmm"))&(pgmmvcov.equals("robust inference")) ? ",robust=TRUE":"")+")";
                       outputCall = "c(capture.output("+summaryCall+"))";
                       cmdClassic+="\n summary("+modelName+") ";
                        try{
                        out  = Deducer.eval(outputCall).asStrings();

                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                           }
                           tmp.add("End Classic");
                           tmp.add("No TP view");
                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }          
                  }else if (method.equals("errorsarlm")||method.equals("GMerrorsar")
                           ||method.equals("lagsarlm")||method.equals("sacsarlm")
                           ||method.equals("gstsls")||method.equals("stsls")||method.equals("spautolm")||method.equals("lagmess")
                           ||method.equals("pvcm")||method.equals("spgm") ) {

                       outputCall = "c(capture.output("+modelName+",summary("+modelName+")))";
                       cmdClassic+="\n "+modelName+"\n summary("+modelName+") ";
                        try{
                        out  = Deducer.eval(outputCall).asStrings();
                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                           }

                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }

                        
                        if (((method.equals("errorsarlm")||method.equals("lagsarlm")||method.equals("sacsarlm"))&(!(glmvcov.equals("No correction"))))
                           ){
                              //  tmp.add("");
                              //  tmp.add("The output above does not adjust for heteroscedasticity.");
                              //  tmp.add("\nThe following does, with the "+ glmvcov + " type covariance matrix adjustment:\n" );
                                
                              //  String summaryCall ="";
                              //  String summaryCall2 ="";
 
                                
                              //  String executeBeforeCall = "\n    "+modelName+"_target<- data.frame("+modelName+"$tary,"+modelName+"$tarX)"+
                              //           "\n    colnames("+modelName+"_target) <- c(\""+this.dependentVar+"\",\"Intercept\","+VariablesListWithQuotes+")"+
                              //           "\n    "+modelName+"_target_lm<- lm("+this.formula.replace("~","~ Intercept +")+" -1,data="+modelName+"_target)";  

                             //       Deducer.eval(executeBeforeCall);
                             //       String vcovStatement = "";
                             //       if (glmvcov.equals("HAC")){
                              //          vcovStatement = ",vcov=vcovHAC("+modelName+"_target_lm)";
                              //      } else {
                             //           vcovStatement = ",vcov=vcovHC("+modelName+"_target_lm,type=\"" +glmvcov+"\")";
                             //       }

                              //      summaryCall = "coeftest("+modelName+"_target_lm"+ vcovStatement+ ", df=Inf)";
                             //       cmdClassic += "\n";
                              //      cmdClassic += "\n #statements for including robust standard error in Classic View output";
                             //       cmdClassic += "\n "+executeBeforeCall;
                             //       cmdClassic += "\n "+summaryCall;
                             //       summaryCall2 = "linearHypothesis("+modelName+"_target_lm"+", paste(names(coef("+modelName+"_target_lm"+"))[-1],\"=0\")"+vcovStatement+",test=\"Chisq\")[3:4]";
                             //       cmdClassic += "\n "+  summaryCall2;
                               
                             //   try{
                             //    out = new String[]{};
                              //   out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                             //   }catch(Exception e){
                             //  new ErrorMsg(e);
                             //  }
                            //   for(int i=2;i<(out.length-2);i++)
                            //    tmp.add(out[i]+"\n");
                              
                               
                              //  try{
                              //   out = new String[]{};
                              //   out  = Deducer.eval("capture.output("+summaryCall2+")").asStrings();                             
                             //   }catch(Exception e){
                             //  new ErrorMsg(e);
                            //    }
                             //  for(int i=0;i<out.length;i++)
                             //       tmp.add(out[i]+"\n");  
                            
                              }
                        
                           tmp.add("End Classic");
                           tmp.add("No TP view");   
                        
                        
                        
                        
                  }else if (method.equals("spml")||method.equals("spreml")||method.equals("spgm")) {
                       outputCall = "c(capture.output("+modelName+",summary("+modelName+")))";
                       cmdClassic+="\n "+modelName+"\n summary("+modelName+") ";
                        try{
                        out  = Deducer.eval(outputCall).asStrings();
                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                           }
       
		        }catch(Exception e){
			new ErrorMsg(e);
                        }     
                        
                        
                  
                        
                        if (!(plmvcov.equals("No correction"))){
                                tmp.add("");
                                tmp.add("The output above does not adjust for heteroscedasticity.");
                                tmp.add("\nThe following does, with the "+ plmvcov + " type covariance matrix adjustment:\n");
                              
                                String summaryCall = "coeftest("+modelName+vcovadjModel+")";
                                cmdClassic += "\n coeftest("+modelName+vcovadjModel+")";
                                try{
                                 out = new String[]{};
                                 out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                                }catch(Exception e){
                               new ErrorMsg(e);
                               }
                               for(int i=2;i<(out.length-2);i++)
                                    tmp.add(out[i]+"\n"); 
                                summaryCall = "linearHypothesis("+modelName+", names(coef("+modelName+"))[-1]"+vcovadjModel+")[3:4]";
                                cmdClassic += "\n linearHypothesis("+modelName+", names(coef("+modelName+"))[-1]"+vcovadjModel+")[3:4]";
                                try{
                                 out = new String[]{};
                                 out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                                }catch(Exception e){
                               new ErrorMsg(e);
                               }
                               for(int i=0;i<out.length;i++)
                                    tmp.add(out[i]+"\n"); 
                              }
                         tmp.add("End Classic");
                         tmp.add("No TP view");
                        
                        
                        
                        
                        
                  }else if ((method.equals("plm")&(!((modeltype.equals("pooling"))||(modeltype.equals("within")))))
                          ||(method.equals("pcce")||method.equals("pmg"))) {
                       outputCall = "c(capture.output("+(method.equals("plm")?"":modelName+",")+"summary("+modelName+")))";
                       cmdClassic+=(outputCall);
                       cmdClassic+="\n "+modelName+"\n summary("+modelName+") ";
                        try{
                        out  = Deducer.eval(outputCall).asStrings();
                      	   for(int i=0;i<out.length;i++){
				tmp.add(out[i]+"\n");
                           }
                           tmp.add("End Classic");
                           tmp.add("No TP view");
                        
		        }catch(Exception e){
			new ErrorMsg(e);
                        }    
                        
                    }else if ((method.equals("tobit"))) {
                  if (numericExplVars.equals("TRUE")){
                     outputCall = "c(capture.output(summary("+modelName+")),capture.output("+modelName + "$HanselAddOns$marginalEffects) )";
                     cmdClassic+="\n summary("+modelName+") ";
                     cmdClassic+="\n"+modelName + "$HanselAddOns$marginalEffects";
                  } else{
                     outputCall = "capture.output(summary("+modelName+"))";
                     cmdClassic+="\n summary("+modelName+") ";
                  }
                               
                                try{
                                out  = Deducer.eval(outputCall).asStrings();
                                   for(int i=0;i<out.length;i++){
                                        tmp.add(out[i]+"\n");
                                   }
                                
                                }catch(Exception e){
                                new ErrorMsg(e);
                                }
                 if (numericExplVars.equals("FALSE")){
                     tmp.add("Marginal effects not calculated since not all\n");
                     tmp.add("explanatory variables are numeric.");
                 }           
                                
                           if (!(tobitvcov.equals("No correction"))
                             ){
                                                                    
                                tmp.add("");
                                tmp.add("The output above does not adjust for heteroscedasticity.");
                                tmp.add("\nThe following does, with the "+ tobitvcov + " type covariance matrix adjustment:\n" );
                                
                                String summaryCall ="";
                                String summaryCall2 ="";
                                
                                summaryCall = "coeftest("+modelName+vcovadjModel+")";
                                cmdClassic += "\n";
                                cmdClassic += "\n #statements for including robust standard error in Classic View output";
                                cmdClassic += "\n coeftest("+modelName+vcovadjModel+")";
                                summaryCall2 = "linearHypothesis("+modelName+", names(coef("+modelName+"))[-1]"+vcovadjModel+")[3:4]";
                                cmdClassic += "\n "+  summaryCall2;
                                
                            
                               try{
                                     out = new String[]{};
                                     out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                                    }catch(Exception e){
                                   new ErrorMsg(e);
                                   }
                                   for(int i=2;i<(out.length-2);i++)
                                    tmp.add(out[i]+"\n");


                                    try{
                                     out = new String[]{};
                                     out  = Deducer.eval("capture.output("+summaryCall2+")").asStrings();                             
                                    }catch(Exception e){
                                   new ErrorMsg(e);
                                    }
                                   for(int i=0;i<out.length;i++)
                                        tmp.add(out[i]+"\n");                             
                                  }
                                tmp.add("End Classic");
                                tmp.add("No TP view");
                                     
                                
               }  else if (method.equals("ca.po")&(efeglmextra.cointPOType.equals("Pu"))) {
                        tmp.add ("\n########################################");
                        tmp.add ("\n# Phillips & Ouiliaris Pu Tests for Cointegration #");
                        tmp.add ("\n########################################");
                        tmp.add ("\n#");
                        tmp.add ("\n# The output below shows the results of repeating the Phillips &  ");
                        tmp.add ("\n# Ouiliaris Pu Test, having a different left-side variable.");
                        tmp.add ("\n########################################");

                        for(int i=0;i<termsArray.length;i++){
                            try{
                                tmp.add ("\n");
                                tmp.add ("\n");
                                tmp.add ("\n");
                                tmp.add ("\n##############################################################");
                                tmp.add ("\n# Phillips & Ouiliaris Pu Test for Cointegration");
                                tmp.add ("\n# using "+termsArray[i]);
                                tmp.add ("\n# as the left-side variable."); 
                                tmp.add ("\n##############################################################");
                             
                               outputCall ="capture.output(summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_left))";
                                out  = Deducer.eval(outputCall).asStrings();

                               for(int j=3;j<out.length;j++){
                                    tmp.add(out[j]+"\n");
                               }
                               
                            }catch(Exception e){
                            new ErrorMsg(e);
                            }
                            cmdClassic+="\n summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_left)";
                        }
                        tmp.add("End Classic");
                        tmp.add("No TP view");    
                        
               }  else if (this.EstimationMethod.equals("Cointegration Test - Engle & Granger")) {
                        tmp.add ("\n########################################");
                        tmp.add ("\n# Engle-Granger Tests for Cointegration #");
                        tmp.add ("\n########################################");
                        tmp.add ("\n#");
                        tmp.add ("\n# The output below shows the results of repeating the Engle-Granger ");
                        tmp.add ("\n# Test, having a different left-side variable in the 1st stage in ");
                        tmp.add ("\n# each repetition. For each repetition there is given regression ");                               
                        tmp.add ("\n# output (not using a robust covariance matrix) and the results from "); 
                        tmp.add ("\n# Augmented Dickey-Fuller (ADF) unit-root test on that regression's");
                        tmp.add ("\n# residuals (each denoted as \"z\"). The critical values at the ");
                        tmp.add ("\n# bottom of each ADF output have been adjusted based on the ");
                        tmp.add ("\n# response surfaces provided in MacKinnon, James G. (2010) \"Critical");
                        tmp.add ("\n# Values for Cointegration Tests\", Queen's Economic Department");
                        tmp.add ("\n# working Paper No. 1227, which is an updated version of MacKinnon's");
                        tmp.add ("\n# 1990 paper of the same name (University of California San Diego");
                        tmp.add ("\n# Discussion Paper 90-4) and his 1991 paper of thee same name found");
                        tmp.add ("\n# in Ch. 13 of the book Long-Run Economic Relationships: Readings in");
                        tmp.add ("\n# Cointegration, ed. R.F. Engle and C.W.J. Granger. Oxford, Oxford ");
                        tmp.add ("\n# University Press.");
                        tmp.add ("\n# Keep in mind that each of the variables considered for ");
                        tmp.add ("\n# cointegration need to be integrated of the same order, so ");
                        tmp.add ("\n# unit-root testing is needed for them also.");
                        tmp.add ("\n########################################");

                        for(int i=0;i<termsArray.length;i++){

                            try{
                                tmp.add ("\n");
                                tmp.add ("\n");
                                tmp.add ("\n");
                                tmp.add ("\n##############################################################");
                                tmp.add ("\n# Engle-Granger Test for Cointegration");
                                tmp.add ("\n# using "+termsArray[i]);
                                tmp.add ("\n# as the left-side variable in the 1st stage."); 
                                tmp.add ("\n##############################################################");
                                outputCall ="capture.output(summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"))";
                                out  = Deducer.eval(outputCall).asStrings();

                               for(int j=0;j<out.length;j++){
                                    tmp.add(out[j]+"\n");
                               }
                               tmp.add("#The ADF test on the residuals (\"z\") from the above regression\n\n");
                               outputCall ="capture.output(summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest))";
                                out  = Deducer.eval(outputCall).asStrings();

                               for(int j=8;j<out.length;j++){
                                    tmp.add(out[j]+"\n");
                               }
                               
                            }catch(Exception e){
                            new ErrorMsg(e);
                            }
                            cmdClassic+="\n summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+")\n summary("+modelName+"$"+termsArray[i].replace("(","_").replace(")", "")+"_EGTest)";
                        }
                        tmp.add("End Classic");
                        tmp.add("No TP view");
 
                        
//*********Section 3: End Evaluation of Model Estimation Statements for Estimates with only Classic Output ***** 
//********Section 4: Evaluation of Model Estimation Statements for Estimates with Classic & TP output *****                         

                 } 
                       
                  else {
                   cmd="";

                String summaryCall=""; 
                      
                 Boolean JGRWontWorkWithLH = false;
                String numcoeffsCall = "length("+modelName+"$coeff)";
                Integer numcoeffs = 0;
                 try{
                    numcoeffs  = Deducer.eval(numcoeffsCall).asInteger();	  
                    }catch(Exception e){
                    new ErrorMsg(e);
                    }      
               if ((Deducer.insideJGR)&(numcoeffs>40)) {
                   JGRWontWorkWithLH = true;
                   this.efeglmextra.classicView = true;
               } else {
                       {
                          
                    if ((((method.equals("glm"))||(method.equals("lm"))||(method.equals("dynlm")))&(!efeglmextra.interceptIncluded))||
                        ((method.equals("plm"))&(plmPanelType.equals("within (\"fixed\") effects"))))  {
                        cmd+="\n"+tempPrefix +"tablelht <- linearHypothesis("+modelName+",names(coef("+modelName+")),test=\"F\""+vcovadjModel+")";
                        cmd+="\n"+tempPrefix +"tabledf <- length("+modelName+"$coeff)";
                    }
                    else {
                        if (!(numExplanatoryVariables==0)) {
                          cmd+="\n"+tempPrefix +"tablelht <- linearHypothesis("+modelName+",names(coef("+modelName+"))[-1],test=\"F\""+vcovadjModel+")";
                        }
                        cmd+="\n"+tempPrefix+"tabledf <- length("+modelName+"$coeff)-1";
                    }
                    cmd+="\n"+tempPrefix+"tabledfr <- df.residual("+modelName+")";            
                    
                    cmd+= "\n "+tempPrefix +"temp_holding_of_summary <- \"\"";
                    if ((method.equals("ivreg"))){
                        cmd+="\n "+tempPrefix +"coefTable <- data.frame(summary("+modelName+vcovadjModel+")$coefficients)";
                    } else if ((method.equals("tobit"))){
                        cmd+="\n"+tempPrefix +"coefTable <- data.frame(coeftest("+modelName+vcovadjModel+")[,1:4])";  
                    } else  {
                        cmd+= "\n "+tempPrefix +"temp_holding_of_summary <- summary("+modelName+") ";
                        cmd+= "\n "+tempPrefix +"temp_holding_of_summary$coefficients[,2:4] <- (coeftest("+modelName+vcovadjModel+"))[,2:4]"; 
                        cmd+= "\n "+tempPrefix +"temp_holding_of_coefficients <- \"hmmm... something went wrong here...\"";
                        cmd+= "\n "+tempPrefix +"temp_holding_of_coefficients <- "+tempPrefix +"temp_holding_of_summary$coefficients";
                        cmd+= "\n names("+tempPrefix +"temp_holding_of_coefficients) <- \"\"";
                        cmd+= "\n "+tempPrefix +"coefTable <- data.frame("+tempPrefix +"temp_holding_of_coefficients)";
                    }
                    cmdClassic+="\n \n #statement for Classic View Initial Estimates Output:";
                    if (method.equals("ivreg")){
                            cmdClassic+= "\n summary("+modelName+vcovadjModel+")";
                    } else
                        cmdClassic+= "\n summary("+modelName+")";
                    if ((method.equals("tobit"))){ 
                       cmd+="\n"+modelName+"$HanselAddOns$meandepvar <- mean("+modelName+"$y)";
                       cmd+="\n"+modelName+"$HanselAddOns$sddepvar <- sqrt(var("+modelName+"$y)[1])";
                    } else {
                       cmd+="\n"+modelName+"$HanselAddOns$meandepvar <- mean("+modelName+"$model[,1])";
                       cmd+="\n"+modelName+"$HanselAddOns$sddepvar <- sqrt(var("+modelName+"$model[,1]))";
                    }
                    cmdClassic+="\n \n #Additional statements giving output in Initial Estimates Output that is not in Classic View:";
                    cmdClassic+="\n colMeans("+modelName+"$model[1]) # mean of dependent variable";
                    cmdClassic+="\n sqrt(var("+modelName+"$model[1])) # S.D. dependent variable";
                    cmdClassic+="\n ((summary("+modelName+")$sig)^2*"+modelName+"$df.residual) # Sum of Squared Residuals";
                    if (!(method.equals("ivreg"))){
                        if (method.equals("tobit")){
                           cmd+="\n "+modelName+"$HanselAddOns$numObs <- NROW("+modelName+"$y)";
                           cmdClassic+="\n "+modelName+"$HanselAddOns$numObs <- NROW("+modelName+"$y)";
                        }else{
                           cmd+="\n"+modelName+"$HanselAddOns$numObs <- NROW("+modelName+"$residuals)";
                           cmdClassic+="\n "+modelName+"$HanselAddOns$numObs <- NROW("+modelName+"$residuals)";    
                        }
                        cmdClassic+="\n NROW("+modelName+"$HanselAddOns$numObs) # Number of observations";
                        cmd+="\n "+tempPrefix +"header3rdLineOfText<-c(paste(\"Number of observations: \","+modelName+"$HanselAddOns$numObs))";
                        cmd+="\n "+tempPrefix +"header3rdLine <- data.frame("+tempPrefix +"header3rdLineOfText)";
                        if (method.equals("plm")&((modeltype.equals("pooling"))||(modeltype.equals("within")))) {
                            cmd+="\n"+modelName+"$HanselAddOns$rss <- sum("+modelName+"$resid^2)";
                            /* The calculation of the loglkelihood from RSS is based on information from Burnaham & Anderson (2002), p. 12*/
                            cmd+="\n"+modelName+"$HanselAddOns$LogLikelihoodvalue  <- (-0.5*"+modelName+"$HanselAddOns$numObs)*(log("+modelName+"$HanselAddOns$rss/"+modelName+"$HanselAddOns$numObs)+log(2*pi)+1)"; 
                            cmd+="\n"+modelName+"$HanselAddOns$numParamsEst <- "+modelName+"$HanselAddOns$numObs - "+modelName+"$df.residual +1";
                            cmd+="\n"+modelName+"$HanselAddOns$AICvalue <- -2*"+modelName+"$HanselAddOns$LogLikelihoodvalue  +2*"+modelName+"$HanselAddOns$numParamsEst ";
                            cmd+="\n"+modelName+"$HanselAddOns$AICcvalue <- "+modelName+"$HanselAddOns$AICvalue + 2*"+modelName+"$HanselAddOns$numParamsEst*("+modelName+"$HanselAddOns$numParamsEst+1)/("+modelName+"$HanselAddOns$numObs - "+modelName+"$HanselAddOns$numParamsEst - 1)";
                            cmd+="\n"+modelName+"$HanselAddOns$SICvalue <- -2*"+modelName+"$HanselAddOns$LogLikelihoodvalue  + "+modelName+"$HanselAddOns$numParamsEst*log("+modelName+"$HanselAddOns$numObs )";
                            cmd+="\n"+modelName+"$HanselAddOns$HQvalue  <- -2*"+modelName+"$HanselAddOns$LogLikelihoodvalue  + "+modelName+"$HanselAddOns$numParamsEst*2*log(log("+modelName+"$HanselAddOns$numObs ))";
                        }
                        else {
                            cmd+="\n"+modelName+"$HanselAddOns$LogLikelihoodvalue <- logLik("+modelName+")[1]";
                            cmd+="\n"+modelName+"$HanselAddOns$AICvalue <- AIC("+modelName+")";
                            cmd+="\n"+modelName+"$HanselAddOns$numParamsEst <- length("+modelName+"$coeff)"+
                                    "+(family("+modelName+")$family==\"gaussian\"||family("+modelName+")$family==\"Gamma\"||family("+modelName+")$family==\"inverse.gaussian\")";
                            cmd+="\n"+modelName+"$HanselAddOns$AICcvalue <- "+modelName+"$HanselAddOns$AICvalue + 2*"+modelName+"$HanselAddOns$numParamsEst*("+modelName+"$HanselAddOns$numParamsEst+1)/("+modelName+"$HanselAddOns$numObs - "+modelName+"$HanselAddOns$numParamsEst - 1)";
                            cmd+="\n"+modelName+"$HanselAddOns$SICvalue <- BIC("+modelName+")";
                            cmd+="\n"+modelName+"$HanselAddOns$HQvalue  <-AIC("+modelName+",k=2*log(log("+modelName+"$HanselAddOns$numObs)))";
                            cmdClassic+="\n logLik("+modelName+")[1] # Log Likelihood value";
                            cmdClassic+="\n c(AIC("+modelName+"),\n"+
                                              "AIC("+modelName+")+2*(length("+modelName+"$coeff)+1)*((length("+modelName+"$coeff)+2))/("+ modelName+"$HanselAddOns$numObs - length("+modelName+"$coeff)-2),\n"+
                                              "BIC("+modelName+"),\n    AIC("+modelName+",k=2*log(log("+modelName+"$HanselAddOns$numObs)))) # AIC, AICc, BIC, HQ";
                        }                    
                        if (this.EstimationMethod.equals("Binary Logit")||this.EstimationMethod.equals("Binary Probit")){
                           cmd+="\n"+modelName+"$HanselAddOns$R2 <- pR2("+modelName+")[4]";
                           cmd+="\n "+modelName+"$HanselAddOns$LRStatistic <- "+modelName+"$null.deviance - deviance("+modelName+")";
                           cmd+="\n "+tempPrefix+"tablepFOrChi2 <- 1-pchisq("+modelName+"$HanselAddOns$LRStatistic,length("+modelName+"$coefficients)-1)";
                           cmd+="\n"+tempPrefix+"c1xx<- c(\"mean dependent var\",\"S.D. dependent var\",\"Log-likelihood\",\"Restr. log-likelihood\","+
                                  "paste(\"LR statistic ("+numExplanatoryVariables+" df)\",sep=\"\"),\"p-value for LR stat\")";
                           cmd+="\n"+tempPrefix +"c2xx <- c(sapply(c("+modelName+"$HanselAddOns$meandepvar,"
                                                                    +modelName+"$HanselAddOns$sddepvar,"
                                                                    +modelName+"$HanselAddOns$LogLikelihoodvalue,"
                                                                    +modelName+"$null.deviance/(-2), "
                                                                    +modelName+"$HanselAddOns$LRStatistic"
                                                                    +"),format,scientific=-1),"
                                                                     + "paste(format(round("+tempPrefix +"tablepFOrChi2,4),scientific=F,nsmall=4,justify=\"right\"),"
                                                                     +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.001,\"***\","
                                                                            +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.01,\"**\","
                                                                              +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.05,\"*\","
                                                                               +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.1,\".\",\"\""
                                                                              +"))))))";
                           cmd+="\n"+tempPrefix +"c3xx <- c(\"McFadden R-squared\",\"Adj McFadden R-squared\", \"Akaike criterion\",\"AICc\",\"Schwarz criterion\",\"Hannan-Quinn\")";
                           cmd+="\n"+tempPrefix +"c4xx <- sapply(c("+modelName+"$HanselAddOns$R2,"
                                                                    +"1-(as.numeric(logLik("+modelName+"))-length("+modelName+"$coefficients))/(as.numeric(logLik("+modelName+"))*(1-"+modelName+"$HanselAddOns$R2)^-1),"
                                                                    +modelName+"$HanselAddOns$AICvalue,"
                                                                    +modelName+"$HanselAddOns$AICcvalue,"
                                                                    +modelName+"$HanselAddOns$SICvalue,"
                                                                    +modelName+"$HanselAddOns$HQvalue"
                                                                    +"),format,scientific = -1)";
                           cmd+="\n"+tempPrefix +"sigcode <- \" \";";
                        }
                             
                        else if (method.equals("plm")&((modeltype.equals("pooling"))||(modeltype.equals("within")))) { 
                           cmd+="\n"+modelName+"$HanselAddOns$R2 <- summary("+modelName+")$r.squared";
                           if (!(numExplanatoryVariables==0)) {
                           cmd+="\n"+tempPrefix +"tableFOrChi2 <- "+tempPrefix +"tablelht[2,\"F\"]";
                           } 
                           cmd+="\n"+tempPrefix +"tablepFOrChi2 <- pf("+tempPrefix +"tableFOrChi2, "+tempPrefix +"tabledf, "+tempPrefix +"tabledfr, lower.tail = FALSE)";
                           cmd+="\n"+tempPrefix +"c1xx<- c(\"mean dependent var\",\"S.D. dependent var\",\"S.E. Reg\",\"sum squared resid\",\"Log-likelihood\","+
                                  "paste(\"F(\",summary("+modelName+")$fstatistic[[3]][1],\",\",summary("+modelName+")$fstatistic[[3]][2],\")\",sep=\"\"),\"p-value for F\")"; 
                           cmd+="\n"+tempPrefix +"c2xx <- c(sapply(c("+modelName+"$HanselAddOns$meandepvar,"
                                                        +modelName+"$HanselAddOns$sddepvar,"
                                                        + "sqrt("+modelName+"$HanselAddOns$rss/"+modelName+"$df),"
                                                        +  modelName+"$HanselAddOns$rss,"
                                                         + modelName+"$HanselAddOns$LogLikelihoodvalue,"+tempPrefix +"tableFOrChi2),format,scientific=-1),"
                                   + "paste(format(round("+tempPrefix +"tablepFOrChi2,4),scientific=F,nsmall=4,justify=\"right\"),"
                                     +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.001,\"***\","
                                            +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.01,\"**\","
                                              +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.05,\"*\","
                                               +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.1,\".\",\"\""
                                              +"))))))";
                           cmd+="\n"+tempPrefix +"c3xx <- c(\"   R-squared\",\"   Adj R-squared\", \"   Akaike criterion\", \"   AICc\" ,\"   Schwarz criterion\",\"   Hannan-Quinn\",\"   \")"; 
                           cmd+="\n"+tempPrefix +"c4xx <-   sapply(c("+modelName+"$HanselAddOns$R2[1],"
                                                                      +"1-("+modelName+"$HanselAddOns$rss/"+modelName+"$df)/var("+modelName+"$model[,1]),"
                                                                      +modelName+"$HanselAddOns$AICvalue,"
                                                                      +modelName+"$HanselAddOns$AICcvalue,"
                                                                      +modelName+"$HanselAddOns$SICvalue,"
                                                                      +modelName+"$HanselAddOns$HQvalue,\"\"),format,scientific=-1)";
                        
                        }
                        else { 
                           cmd+="\n"+modelName+"$HanselAddOns$R2 <- summary("+modelName+")$r.squared";
                           if (!(numExplanatoryVariables==0)) {
                             cmd+="\n"+tempPrefix +"tableFOrChi2 <- "+tempPrefix +"tablelht[2,\"F\"]";
                             cmd+="\n"+tempPrefix +"tablepFOrChi2 <- pf("+tempPrefix +"tableFOrChi2, "+tempPrefix +"tabledf, "+tempPrefix+"tabledfr, lower.tail = FALSE)";
                             cmd+="\n"+tempPrefix +"c1xx<- c(\"mean dependent var\",\"S.D. dependent var\",\"S.E. Reg\",\"sum squared resid\",\"Log-likelihood\","+
                                  "paste(\"F(\",summary("+modelName+")$fstatistic[2],\",\",summary("+modelName+")$fstatistic[3],\")\",sep=\"\"),\"p-value for F\")";                           
                           } else {
                             cmd+="\n"+tempPrefix +"tableFOrChi2 <- 0";
                             cmd+="\n"+tempPrefix +"tablepFOrChi2 <- 1.00";
                             cmd+="\n"+tempPrefix +"c1xx<- c(\"mean dependent var\",\"S.D. dependent var\",\"S.E. Reg\",\"sum squared resid\",\"Log-likelihood\","+
                                  "paste(\"F(0,\","+modelName+"$df.residual,\")\",sep=\"\"),\"p-value for F\")"; 
                           }
                           cmd+="\n"+tempPrefix +"c2xx <- c(sapply(c("+modelName+"$HanselAddOns$meandepvar,"+modelName+"$HanselAddOns$sddepvar,summary("+modelName+")$sig,(summary("+modelName+")$sig^2)*"+modelName+"$df.residual,"+modelName+"$HanselAddOns$LogLikelihoodvalue,"+tempPrefix +"tableFOrChi2),format,scientific=-1),"
                                   + "paste(format(round("+tempPrefix +"tablepFOrChi2,4),scientific=F,nsmall=4,justify=\"right\"),"
                                     +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.001,\"***\","
                                            +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.01,\"**\","
                                              +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.05,\"*\","
                                               +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.1,\".\",\"\""
                                              +"))))))";
                           cmd+="\n"+tempPrefix +"c3xx <- c(\"   R-squared\",\"   Adj R-squared\", \"   Akaike criterion\", \"   AICc\" ,\"   Schwarz criterion\",\"   Hannan-Quinn\",\"   \")"; 
                           cmd+="\n"+tempPrefix +"c4xx <- sapply(c("+modelName+"$HanselAddOns$R2,1-(1-"+modelName+"$HanselAddOns$R2)*("+modelName+"$HanselAddOns$numObs-1)/("+modelName+"$HanselAddOns$numObs-length("+modelName+"$coefficients)),"+modelName+"$HanselAddOns$AICvalue,"+modelName+"$HanselAddOns$AICcvalue,"+modelName+"$HanselAddOns$SICvalue,"+modelName+"$HanselAddOns$HQvalue,0),format,scientific=-1)";
                           cmd+="\n"+tempPrefix +"c4xx[7]  <- \" \""; 

                        }
                    }
                    else{ //dealing now with tsls 
                        cmd+="\n "+modelName+"$HanselAddOns$numObs <- NROW("+modelNameFinalStage+"$residuals)";
                        cmd+="\n "+tempPrefix +"header3rdLineOfText<-c(paste(\"Number of observations: \","+modelName+"$HanselAddOns$numObs))";
                        cmd+="\n "+tempPrefix +"header3rdLine <- data.frame("+tempPrefix +"header3rdLineOfText)";
                        if (!(numExplanatoryVariables==0)) {
                        cmd+="\n "+tempPrefix +"tableFOrChi2 <- "+ "summary("+modelName+vcovadjModel+")$waldtest[1]";
                        cmd+="\n "+tempPrefix +"tablepFOrChi2 <- "+"summary("+modelName+vcovadjModel+")$waldtest[2]";
                        }
                        cmd+="\n "+tempPrefix +"c1xx<- c(\"mean dependent var\",\"S.D. dependent var\",\"S.E. Reg\","+/*"\"sum squared resid\",\"Log-likelihood\","+*/
                                  "paste(\"Wald test (\",summary("+modelName+vcovadjModel+")$waldtest[3],\",\",summary("+modelName+vcovadjModel+")$waldtest[4],\")\",sep=\"\"))";
                        cmd+="\n"+tempPrefix +"c2xx <- sapply(c("+modelName+"$HanselAddOns$meandepvar,"
                                                                  +modelName+"$HanselAddOns$sddepvar,"
                                                                  +"summary("+modelName+vcovadjModel+")$sig,"
                                                                 +"summary("+modelName+vcovadjModel+")$waldtest[1]),"
                                                                 /* +"((summary("+modelName+vcovadjModel+")$sig)^2*"+modelName+"$df.residual),"*/
                                                                 /* +modelName+"$HanselAddOns$LogLikelihoodvalue,"*/
                                                                  +"format,scientific=-1)";

                        cmd+="\n"+tempPrefix +"c3xx <- c(\"   R-squared\",\"   Adj R-squared\", \"   sum squared resid\",\" p-value for Wald test\")";
                        cmd+="\n"+tempPrefix +"c4xx <- c(sapply(c(summary("+modelName+")$r.squared,"
                                + "summary("+modelName+")$adj.r.squared,"
                                +"((summary("+modelName+vcovadjModel+")$sig)^2*"+modelName+"$df.residual)"
                                + "),format,scientific=-1),"
                                + "paste(format(round(summary("+modelName+vcovadjModel+")$waldtest[2],4),scientific=F,nsmall=4,justify=\"right\"),"
                                                                                 +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.001,\"***\","
                                                                                        +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.01,\"**\","
                                                                                          +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.05,\"*\","
                                                                                           +"ifelse("+tempPrefix +"tablepFOrChi2   < 0.1,\".\",\"\""
                                                                                          +"))))))";
                        cmd+="\n"+"if ("+tempPrefix +"tablepFOrChi2   < 0.001) "+tempPrefix +"sigcode <- \"***\" else";
                        cmd+="\n"+"{if ("+tempPrefix +"tablepFOrChi2   < 0.01) "+tempPrefix +"sigcode <- \"**\" else";
                        cmd+="\n"+"{if ("+tempPrefix +"tablepFOrChi2   < 0.05) "+tempPrefix +"sigcode <- \"*\" else";
                        cmd+="\n"+"{if ("+tempPrefix +"tablepFOrChi2   < 0.1) "+tempPrefix +"sigcode <- \".\" else";
                        cmd+="\n"+tempPrefix +"sigcode <- \" \";}}};";
                        cmd+="\n"+tempPrefix +"c5xx <-c(\"\",\"\",\"\",\"\",\"\","+tempPrefix+"sigcode)";
                    }                     
                   cmd+= "\n colnames("+tempPrefix +"coefTable)[2] <- \"Std. error\"";
                   if (this.EstimationMethod.equals("Logit"))
                      cmd+= "\n colnames("+tempPrefix +"coefTable)[3] <- \"z-value\"";
                   else
                      cmd+= "\n colnames("+tempPrefix+"coefTable)[3] <- \"t-value\"";
                   cmd+= "\n colnames("+tempPrefix +"coefTable)[4] <- \"p-value\"";
                   cmd+= "\n "+tempPrefix +"sigStars  <- ifelse("+tempPrefix +"coefTable[4] < 0.001,\"***\",ifelse("+tempPrefix +"coefTable[4]<0.01,\"**\", ifelse("+tempPrefix+"coefTable[4]<0.05,\"*\", ifelse("+tempPrefix +"coefTable[4]<0.1,\".\",\"\")      ) ))" ;
                   cmd+= "\n colnames("+tempPrefix +"sigStars) <- \"sig\"";
                   cmd+= "\n "+tempPrefix +"coefTable[5] <- "+tempPrefix +"sigStars";
                   cmd+= "\n attributes("+tempPrefix +"coefTable)$names[5] <- \"sig\"";
                   
                    cmd+="\n"+tempPrefix +"table2 <- data.frame("+tempPrefix +"c1xx,"
                            +tempPrefix +"c2xx,"+tempPrefix +"c3xx,"+tempPrefix +"c4xx)";


                 }
              /*Deducer.execute(cmd);*/  //debugger
                
		if(preview){
                        Deducer.eval(tempPrefix +"coefTable <- \"error\";");
                        Deducer.eval(tempPrefix +"table2 <- \"error\";");
          /*  Deducer.execute(cmd);*/ //debugger
                        try{
                            Deducer.eval(cmd); 
		           }catch(Exception e){
                            
			new ErrorMsg(e);
                        }
                        if (method.equals("plm")) {

                            try{
                                Deducer.eval(cmdfit);
                               }catch(Exception e){

                            new ErrorMsg(e);
                            }
                        }
                           
		}  
                       
               }
                   
                /************Classic output *********/
                        if (method.equals("ivreg")){
                            summaryCall = "summary("+modelName+vcovadjModel+")";
                        } else
                            summaryCall = "summary("+modelName+")";

                        outputCall = "c(capture.output("+summaryCall+"))";

                        out = new String[]{};
                        try{ 
                             out  = Deducer.eval(outputCall).asStrings();
		            }catch(Exception e){
			   new ErrorMsg(e);
                           }
                        for(int i=2;i<out.length;i++)
				tmp.add(out[i]+"\n"); 

                        warningMessages = "";
                        try {
                            warningMessages = Deducer.eval(modelName +"$HanselAddOns$MssgEW").asString();  
                            }catch(Exception e){
                            new ErrorMsg(e);
                        } 
                        
                        tmp.add(warningMessages);
                       
                   if (JGRWontWorkWithLH) {
                       tmp.add("The output above does not adjust for heteroscedasticity.");
                       tmp.add("JGR can't handle more than 40 coefficients when using linearHypothesis. ");
                       tmp.add("Since the heteroscedasticity-adjusted version uses that function, it is not given here.");
                       tmp.add("End Classic");
                       tmp.add("<br>");
                       tmp.add("Only Classic View is available when the number of estimated coefficients is > 40");
                       tmp.add("and JGR is used as the console.");
                       tmp.add("JGR appears to have trouble with linearHypothesis under these circumstances");
                       tmp.add("<br>");
                       
                   } else {
                        if (((method.equals("lm")||(method.equals("dynlm"))||method.equals("glm")||method.equals("lagsarlm"))&(!(glmvcov.equals("No correction"))))||
                           ((method.equals("plm"))&(!(plmvcov.equals("No correction"))))){
                                tmp.add("");
                                tmp.add("The output above does not adjust for heteroscedasticity.");
                                tmp.add("\nThe following does, with the "+ glmvcov + " type covariance matrix adjustment:\n" );
                 
                                summaryCall = "coeftest("+modelName+vcovadjModel+")";
                                cmdClassic += "\n";
                                cmdClassic += "\n #statements for including robust standard error in Classic View output";
                                cmdClassic += "\n coeftest("+modelName+vcovadjModel+")";
                                try{
                                 out = new String[]{};
                                 out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                                }catch(Exception e){
                               new ErrorMsg(e);
                               }
                               for(int i=2;i<(out.length-2);i++)
                                    tmp.add(out[i]+"\n"); 
                                summaryCall = "linearHypothesis("+modelName+", names(coef("+modelName+"))[-1]"+vcovadjModel+")[3:4]";
                                cmdClassic += "\n linearHypothesis("+modelName+", names(coef("+modelName+"))[-1]"+vcovadjModel+")[3:4]";
                                try{
                                 out = new String[]{};
                                 out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();                             
                                }catch(Exception e){
                               new ErrorMsg(e);
                               }
                               for(int i=0;i<out.length;i++)
                                    tmp.add(out[i]+"\n"); 
                              }               
                                  
                                  
                        tmp.add("End Classic");
                //************End Classic output *********

                        tmp.add("<b>"+this.EstimationMethod+ "</b><br|>");
                       if ((method.equals("ivreg")))
                        tmp.add("R function: ivreg"+ 
                                  (modelWeights.length()==0 ? "" : "; weights: "+modelWeights)+
				  ((modelSubset.length()==0) ? "" : "; subset: "+modelSubset) + "<br|>");
                        else if ((method.equals("plm")))
                          tmp.add("R function: "+method+
				(modelWeights.length()==0 ? "" : "; weights: "+modelWeights)+
				((modelSubset.length()==0) ? "" : "; subset: "+modelSubset)+", effect: "+modelPlmEffect+
				", model: "+modelPlmType+"<br>");
                        else
                          tmp.add("R function: "+method+
                                  (modelWeights.length()==0 ? "" : "; weights: "+modelWeights)+
				  ((modelSubset.length()==0) ? "" : "; subset: "+modelSubset) + "<br|>");                      
                                                

                        summaryCall ="print("+tempPrefix +"header3rdLine,row.names=F)";
                        
                        try{
                            out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();
			    tmp.add(out[1]); 
                            tmp.add("<br>");

		           }catch(Exception e){
			new ErrorMsg(e);
                        }
                        tmp.add("\n Dependent variable: "+ dependentVar+ "<br>"); 
                        if (modelInstruments.length()>0)
                                tmp.add("\n Instruments: "+modelInstruments+ "<br>"); 
                        tmp.add("<br>");
                        if ((method.equals("glm"))||(method.equals("lm"))||(method.equals("dynlm"))||(method.equals("ivreg")))
                              tmp.add("Covariance matrix adjustment: "+ glmvcov+ "<br>"); 
                        else if (method.equals("plm"))
                              tmp.add("Covariance matrix adjustment: "+ plmvcov+ "<br>");

                        summaryCall = "if ((length("+tempPrefix +"coefTable)==1)||(length("+tempPrefix +"table2)==1)) c(\"0 skip\",\"1 skip\",\"There appears to be a problem.<br>\")"+
                                       " else capture.output(print.xtable(xtable("+tempPrefix +"coefTable,digits=c(8,8,8,4,4,1),display=c(rep(\"G\",4),\"f\",\"f\")),type=\"html\"))";
                        

                        
                        try{
                          out = Deducer.eval(summaryCall).asStrings();     
		        }catch(Exception e){
			new ErrorMsg(e);
                        }

                        
                        for(int i=2;i<out.length;i++)
				tmp.add(out[i]);
                        

                        summaryCall = "if ((length("+tempPrefix +"coefTable)==1)||(length("+tempPrefix +"table2)==1)) c(\"\")"+
                                      "else capture.output(print.xtable(xtable("+tempPrefix +"table2, align = c(\"l\",\"l\",\"r\",\"r\",\"r\"), digits=c(4,4,4,4,4),display=c(rep(\"f\",5))),include.rownames=F,include.colnames=F,type=\"html\"))";
                                      

                        try{
                        out  = Deducer.eval(summaryCall).asStrings();
                        
                           tmp.add("<br>");
                      	   for(int i=3;i<out.length;i++)
				tmp.add(out[i]);
                           tmp.add("<br>");
                           if(out.length>1){
                              tmp.add("Significance levels:   '***' 0.001;    '**' 0.01;    '*' 0.05;    '.' 0.1 <br>");
                              if (method.equals("plm")) { 
                                tmp.add("Note:  R-squared is for model with variables orthogonalized against fixed effects.");
                              }
                           }
		        }catch(Exception e){
			new ErrorMsg(e);
                        }  
                        
                        tmp.add("<br><b>");
                        tmp.add(warningMessages.replace("\n","<br>"));
                        tmp.add(errorMessageString.replace("\n","<br>"));
                        
                        
                        if (this.EstimationMethod.equals("Binary Logit")||(this.EstimationMethod.equals("Binary Probit"))){
                           summaryCall ="summary("+modelName+")";
                           try{
                               out  = Deducer.eval("capture.output("+summaryCall+")").asStrings();
                               tmp.add("<br>");
                      	       for(int i=(out.length-6);i<(out.length-4);i++)
				   tmp.add(out[i]+"<br>");
                               for(int i=(out.length-3);i<out.length;i++)
				   tmp.add(out[i]+"<br>");
                           }catch(Exception e){
			new ErrorMsg(e);
                        }
                        }
                   }       
               }
/*********Section 4: End Evaluation of Model Estimation Statements for Estimates with Classic & TP output *****/ 
/*********Section 5: ??? ***/ 
            }
		if(!preview)
			Deducer.execute(cmd);
		String prev = "";
		for(int i =0;i<tmp.size();i++)
			prev+=tmp.get(i);
/*********Section 5: End ??? ***/ 
/*********Section 6: save information for rModel ***/ 
		rModel.call=cmdClassic;
		rModel.data=dataName;
		rModel.formula=formula;
		rModel.modelName=modelName;
		rModel.preview=prev; 
		return rModel; 
	}
    
    
          protected String makeformulaparam() {
                String xx ="";
                if ((method.equals("plm") & !(plmPanelType.equals("pooled OLS")||plmPanelType.equals("between"))) || 
                   (method.equals("pggls")&!(pgglsPanelType.equals("pooling")))||
                    method.equals("pcce")|| 
                   (method.equals("spml")& !(spmlPanelType.equals("pooling")))||
                    method.equals("spreml")) {
                    xx = dependentVar +" = varying intercept + ";
                }
                else if (efeglmextra.interceptIncluded) {
                   xx = dependentVar +" = b(0) + ";
                }
                else{
                   xx = dependentVar +" = ";
                  }
                 if (termsArray.length>0) {
                      xx = xx + "b(1)*"+termsArray[0];
                   }
                if (termsArray.length>1) {
                   for(int i=1;i<termsArray.length;i++)
			xx = xx + " + b("+(i+1)+")*"+termsArray[i];
                }
                return xx;
        };
          
         protected String makeformulaparamECM() {
                String xx = "CR = ";
                  {
                   if (termsArray.length>0) {
                      xx = xx + "b(1)*"+termsArray[0];
                   }
                  }
                if (termsArray.length>1) {
                   for(int i=1;i<termsArray.length;i++)
			xx = xx + " + b("+(i+1)+")*"+termsArray[i];
                }
                if (varOptions.cointOption.substring(0,5).equals("const"))
                    xx = xx + " + b("+(termsArray.length+2)+")*"+"constant";
                else if (varOptions.cointOption.equals("trend")){
                    xx = xx + " + b("+(termsArray.length+2)+")*"+"trend";
                }
                return xx;
        };
	protected String runaeText(String initcomment, String cmd, String morecomment, String modelName,boolean preview,ArrayList tmp){
            
		String[] out = new String[]{};
		String aeCall = "";
                String toEvalFirst = "";

                if(this.efeaetext.archTestMulti){
			if(preview){
                                aeCall = "arch.test("+modelName+ 
                                        ", lags.multi =" +this.efeaetext.archTestMultiLagsM +
                                        (this.efeaetext.archTestMultiLagsS.equals("None: Multivariate only") ? ")" : 
                                         ", lags.single =" +this.efeaetext.archTestMultiLagsS +
                                         ", multivariate.only = FALSE)");
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                if(this.efeaetext.breuschGodfreyMulti){
			if(preview){
                                aeCall = "serial.test("+modelName+ 
                                        ", lags.bg =" +this.efeaetext.breuschGodfreyMLags + ",type="+
                                        (this.efeaetext.breuschGodfreyMAsymptotic ? "\"BG\")" : "\"ES\")");
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}       
                
                if(this.efeaetext.portmanteauMulti){
			if(preview){
                                aeCall = "serial.test("+modelName+ 
                                        ", lags.pt =" +this.efeaetext.portmanteauMLags + ",type="+
                                        (this.efeaetext.portmanteauMAsymptotic ? "\"PT.asymptotic\")" : "\"PT.adjusted\")");
                                
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}   
                if(this.efeaetext.LMSpatialDep){
			if(preview){
                                
                                aeCall = "summary(lm.LMtests("+modelNameFinalStage+",listw ="+listwString+",test=\"all\"),p.adjust.method=\""+this.efeaetext.pAdjustMethod+"\")";
                                
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
                if(this.efeaetext.moransIorIitest){
                        
                        if (this.efeaetext.moransITestType.equals("original"))
                            aeCall = "lm.morantest";
                        else if (this.efeaetext.moransITestType.equals("exact global"))
                            aeCall = "lm.morantest.exact";
                        else if (this.efeaetext.moransITestType.equals("saddlepoint approx of global"))
                            aeCall = "lm.morantest.sad";
                        else
                            aeCall = "";                              
                                
                        aeCall +=       "("+modelNameFinalStage+",listw ="+listwString+
                                        ",alternative=\""+this.efeaetext.moransIaltHypoth +"\"" +
                                        ",resfun="+this.efeaetext.moransIaltResFun   + ")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Moran's I test");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                
                if(this.efeaetext.spHausmanTest){
                      if (this.spmlPanelType.equals("within"))
                            toEvalFirst ="\n withinModelforSpHausmanTest<-"+modelName+
                                         "\n reModelforSpHausmanTest<-"+method+"("+formula+",data="+data+",listw ="+listwString+
                                          (method.equals("spml") ? ",effect=\""+spmleffect : "")+  
                                          "\",model=\"random\""+
                                          ",moments=\"fullweights\")";
                        else { 
                         if ((this.spmlPanelType.equals("random")))  
                              toEvalFirst ="\n withinModelforSpHausmanTest<-"+ method+"("+formula+",data="+data+",listw ="+listwString+
                                           (method.equals("spml") ? ",effect=\""+spmleffect : "")+  
                                           "\",model=\"within\")"+
                                           "\n reModelforSpHausmanTest<-"+modelName;
                         else
                              toEvalFirst ="\n withinModelforSpHausmanTest<-"+modelName+
                                         "\n reModelforSpHausmanTest<-"+method+"("+formula+",data="+data+",listw ="+listwString+
                                          (method.equals("spml") ? ",effect=\""+spmleffect : "")+  
                                          "\",model=\"random\""+
                                          ",moments=\"fullweights\")"+
                                          "\n withinModelforSpHausmanTest<-"+method+"("+formula+",data="+data+",listw ="+listwString+
                                           (method.equals("spml") ? ",effect=\""+spmleffect : "")+  
                                           "\",model=\"within\")";
                  
                                      }
			aeCall = "sphtest(withinModelforSpHausmanTest,reModelforSpHausmanTest)";
                        Deducer.execute(aeCall);
                        cmd+= toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;(i<out.length-1);i++)
					tmp.add(out[i]);
                                        tmp.add("Rejecting the null suggests we should use the fixed effects,");
                                        tmp.add("(within) model rather than the random effects model.");    
                                
			}else{
			}
		}    
                
                
                
                
                   if(this.efeaetext.bsjkTest){
			if(preview){
                                
                                aeCall = "bsjktest("+formula+",data="+data+",listw ="+listwString+",test=\""+this.efeaetext.bsjktestType+"\")";
                                
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
                 if(this.efeaetext.bskTest){
			if(preview){
                                
                                aeCall = "bsktest("+formula+",data="+data+",listw ="+listwString+",test=\""+this.efeaetext.bsktestType+"\")";
                                
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
                
                
                
                 if(this.efeaetext.normalityTestMulti){
			if(preview){
                                aeCall = "normality.test("+modelName+
                                        (this.efeaetext.normalityTestMultiMonly ? ")" : ",multivariate.only = FALSE)");          
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}                        
                
                 if(this.efeaetext.eigenvaluesVARcompanion){
			if(preview){
                                tmp.add("Eigenvalues of companion coefficient matrix: \n");
                                aeCall = "vars::roots("+modelName+")";          
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}   
                
                 if(this.efeaetext.coeffsMArepOfVAR){
			if(preview){
                                if (this.efeaetext.coeffsMArepOfVAROrthog){
                                    tmp.add("Coefficient matrices of the orthogonalized MA representation \n");
                                    aeCall = "Psi("+modelName+",nstep="+this.efeaetext.coeffsMArepOfVARnsteps +")";
                                }else{
                                    tmp.add("Coefficient matrices of the MA representation \n");
                                    aeCall = "Phi("+modelName+",nstep="+this.efeaetext.coeffsMArepOfVARnsteps +")";
                                }
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}                  
                if(this.efeaetext.blanchardQuahSVAR){
			if(preview){
                                    tmp.add("Blanchard-Quah type SVAR");
                                    aeCall = "BQ("+modelName+")";
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");

				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}           
               
                
              
                 if(this.efeaetext.breuschPaganTest){
                     aeCall = "bptest("+modelNameFinalStage+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");
                                tmp.add("Breusch-Pagan Test for Heteroskedasticity,");
                                tmp.add("studentized, using all explanatory variables");
				for(int i=2;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                 
                if(this.efeaetext.whiteTest){
                       String testTerms = ",~I("+termsArray[0]+ "^2)";
                       for(int i=1;i<termsArray.length;i++){
                         testTerms = testTerms + "+I("+termsArray[i]+ "^2)";
                       }
                       for(int i=0;i<termsArray.length;i++){
                          for(int j=i+1;j<termsArray.length;j++){
                            testTerms = testTerms + "+" + termsArray[i]+"*"+ termsArray[j] ;
                          }
                       }
                       aeCall = "bptest("+modelNameFinalStage+testTerms+",data="+dName+")";
			if(preview){                            
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");
                                tmp.add("White Test for Heteroskedasticity");
                                tmp.add("--uses studentized Breusch-Pagan test with");
                                tmp.add("the potential explanatory variables for the variance");
                                tmp.add("being all of the explanatory variables in the initial");
                                tmp.add("regression model along with their squares and cross-products.");
				for(int i=2;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                  
                   if(this.efeaetext.breuschPaganTestEffectsOnly){
                        if (plmeffect.equals("individual")) { 
  			   aeCall = "bptest("+modelName+ ", varformula =" +  "~ factor(" + data + "[[1]]),data="+data+")";
                        }
                        else {
                            if (plmeffect.equals("time")) {
                             aeCall = "bptest("+modelName+ ", varformula =" +  "~ factor(" + data + "[[2]]),data="+data+")";
                            }
                            else
                             aeCall = "bptest("+modelName+ ", varformula =" + "~ factor(" + data + "[[1]]) + factor(" + data + "[[2]]),data="+data+")";
                        }
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");
                                tmp.add("Test for Heteroskedasticity, using only " + plmeffect + " effect variables");
				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}   
                   
                if(this.efeaetext.durbinWatsonTest){
                    if (this.panelModel) {
                        aeCall = "pdwtest("+modelName+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                    }
                    else {
			aeCall = "dwt("+modelNameFinalStage+",max.lag=1)";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                    }     
		}                

                if(this.efeaetext.BreuschGodfreyTest){
                    if (this.panelModel) {
                        aeCall = "pbgtest("+modelName+")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                    }
                    else {
			aeCall = "bgtest("+modelName+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                   }
		}
                
               if(this.efeaetext.ljungBoxTest){
			aeCall = "Box.test(resid("+modelNameFinalStage+"),lag=1,type=\"Ljung-Box\",fitdf=0)";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
               
                       
                if(this.efeaetext.woolridgeUnobservedEffectsTest){
                       aeCall = "pwtest("+modelName+")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		} 
                       
                if(this.efeaetext.woolridgeTestShortFE){
                        aeCall = "pwartest("+modelName+")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}                
                                    
               if(this.efeaetext.woolridgeTest1stDiff){
                        aeCall = "pwfdtest("+formula+",data="+data+                                                  
				(modelWeights.length()==0 ? "" : "; weights="+modelWeights)+
				((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset)+
				/*",na.action=na.omit"+*/ ")";  
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}   
                               
             
               if(this.efeaetext.arellanoBondTest){
                     aeCall = "mtest("+modelName+")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(" ");
                                tmp.add("Arellano-Bond test of serial correlation");
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
               
               
                  if(this.efeaetext.localRobustAR1Test){
                     aeCall = "pbsytest("+modelName+",test=\"ar\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}                  
                  
                if(this.efeaetext.localRobustRETest){
                     aeCall = "pbsytest("+modelName+",test=\"re\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}   
                if(this.efeaetext.localRobustJointTest){
                     aeCall = "pbsytest("+modelName+",test=\"j\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}    
                                 
                if(this.efeaetext.baltagiLiTest2sided){
                        aeCall = "pbltest("+formula+",data="+data+",alternative=\"twosided\""+                                                  
				(modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset)+
				/*",na.action=na.omit"+ */")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
                
               if(this.efeaetext.baltagiLiTest1sided){
                        aeCall = "pbltest("+formula+",data="+data+",alternative=\"onesided\""+                                                  
				(modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset)+
				/*",na.action=na.omit"+ */")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
               
                if(this.efeaetext.crossSectionDepTestBP){
                     aeCall = "pcdtest("+modelName+",test=\"lm\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		} 
                if(this.efeaetext.crossSectionDepTestScaledBP){
                     aeCall = "pcdtest("+modelName+",test=\"sclm\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		} 
                if(this.efeaetext.crossSectionDepTestPesaran){
                     aeCall = "pcdtest("+modelName+",test=\"cd\")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
                
                if(this.efeaetext.poolabilityTest){
                    String plmModelForPoolingTest = Deducer.getUniqueName("plmModelForPoolingTest");
                    String pvcmModelForPoolingTest = Deducer.getUniqueName("pvcmModelForPoolingTest");
                    if ((this.method.equals("plm"))&&
                        (this.modeltype.equals("pooling")||this.modeltype.equals("within")))
                            toEvalFirst ="\n "+plmModelForPoolingTest+"<-"+modelName+
                                         "\n "+pvcmModelForPoolingTest+"<-pvcm("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")";
                        else { 
                         if ((this.method.equals("pvcm"))&&(this.modeltype.equals("within")))  
                              toEvalFirst ="\n "+plmModelForPoolingTest+"<-plm("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")"+
                                           "\n "+pvcmModelForPoolingTest+"<-"+modelName;
                         else
                              toEvalFirst ="\n "+plmModelForPoolingTest+"<-plm("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")"+
                                           "\n "+pvcmModelForPoolingTest+"<-pvcm("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")";                       
                        }    
			aeCall = "pooltest("+plmModelForPoolingTest+","+pvcmModelForPoolingTest+")";
                        String rmCall = "rm("+plmModelForPoolingTest+","+pvcmModelForPoolingTest+")";
                        cmd+= toEvalFirst+"\n"+aeCall +"\n"+rmCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                     Deducer.eval(rmCall);
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("");
                                tmp.add("Poolability Test");
				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
			}else{
			}
		}
                
                if(this.efeaetext.pFTest){
                    String pooledmodelForpFTest = Deducer.getUniqueName("pooledmodelForpFTest");                                        
                        toEvalFirst = pooledmodelForpFTest+"<-plm("+formula+",data="+data+                                                  
				(modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset)+",effect=c(\""+plmeffect+"\")"+
				",model=c(\"pooling\")"/* +",na.action=na.omit"*/ +")";  
			aeCall = "pFtest("+modelName+","+ pooledmodelForpFTest+")";
                        String rmCall = "rm("+pooledmodelForpFTest+")";
                        cmd+="\n"+toEvalFirst+"\n"+aeCall+"\n"+rmCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                     Deducer.eval(rmCall);
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
		}   
                 if(this.efeaetext.LMTestBP){
                        if (this.modeltype.equals("pooling"))
                            toEvalFirst ="modelForPoolingTest<-"+modelName;
                        else     
                            toEvalFirst = "modelForPoolingTest<-plm("+formula+",data="+data+",model=\"pooling\")";
			aeCall = "plmtest(modelForPoolingTest,type=\"bp\",effect=\""+plmeffect+"\")";
                        cmd+="\n"+toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
		}
                 
                if(this.efeaetext.LMTestGhm){
                        if (this.modeltype.equals("pooling"))
                            toEvalFirst ="modelForPoolingTest<-"+modelName;
                        else     
                            toEvalFirst = "modelForPoolingTest<-plm("+formula+",data="+data+",model=\"pooling\")";
			aeCall = "plmtest(modelForPoolingTest,type=\"ghm\",effect=\""+plmeffect+"\")";
                        cmd+="\n"+toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
		}
                
                if(this.efeaetext.LMTestHonda){
                        if (this.modeltype.equals("pooling"))
                            toEvalFirst ="modelForPoolingTest<-"+modelName;
                        else     
                            toEvalFirst = "modelForPoolingTest<-plm("+formula+",data="+data+",model=\"pooling\")";
			aeCall = "plmtest(modelForPoolingTest,type=\"honda\",effect=\""+plmeffect+"\")";
                        cmd+="\n"+toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
		}
                if(this.efeaetext.LMTestKw){
                        if (this.modeltype.equals("pooling"))
                            toEvalFirst ="modelForPoolingTest<-"+modelName;
                        else     
                            toEvalFirst = "modelForPoolingTest<-plm("+formula+",data="+data+",model=\"pooling\")";
			aeCall = "plmtest(modelForPoolingTest,type=\"kw\",effect=\""+plmeffect+"\")";
                        cmd+="\n"+toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
		}
                
                
                if(this.efeaetext.hausmanTest){
                    String withinModelforHausmanTest = Deducer.getUniqueName("withinModelforHausmanTest");
                    String reModelforHausmanTest = Deducer.getUniqueName("reModelforHausmanTest");
                      if (this.modeltype.equals("within"))
                            toEvalFirst ="\n "+withinModelforHausmanTest+"<-"+modelName+
                                         "\n "+reModelforHausmanTest+"<-"+this.method+"("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"random\")";
                        else { 
                         if ((this.modeltype.equals("random")))  
                              toEvalFirst ="\n "+withinModelforHausmanTest+"<-"+this.method+"("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")"+
                                           "\n "+reModelforHausmanTest+"<-"+modelName;
                         else
                              toEvalFirst ="\n "+withinModelforHausmanTest+"<-"+this.method+"("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"within\")"+
                                           "\n "+reModelforHausmanTest+"<-"+this.method+"("+formula+",data="+data+",effect=\""+plmeffect+"\",model=\"random\")";                    
                        }
			aeCall = "phtest("+withinModelforHausmanTest+","+reModelforHausmanTest+")";
                        String rmCall = "rm("+withinModelforHausmanTest+","+reModelforHausmanTest+")";
                        cmd+= toEvalFirst+"\n"+aeCall;
			if(preview){

                                try {   
                                    Deducer.eval(toEvalFirst);
				    out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                    Deducer.eval(rmCall);
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;(i<out.length-1);i++)
					tmp.add(out[i]);
                                        tmp.add("Rejecting the null suggests we should use the fixed effects,");
                                        tmp.add("(within) model rather than the random effects model.");    
                                
			}else{
			}
		}    
                
                if(this.efeaetext.overIDRestrictionsTest){
                     aeCall = "sargan("+modelName+")";
			if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                
                                for(int i=0;i<3;i++)
					tmp.add(out[i]);
				for(int i=4;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}  
   
              if(this.efeaetext.anova){
                if(this.efeaetext.type.equals("Omnibus")){
                    if (method.equals("lm")||(method.equals("dynlm"))) {
                        toEvalFirst = 
                                "\n Hansel_temp_ssr <- (summary("+ modelNameFinalStage+")$sig)^2*"+modelNameFinalStage+"$df.residual"+
                                "\n Hansel_temp_ss <- c(Hansel_temp_ssr/(1-summary("+modelNameFinalStage+")$r.squared)-Hansel_temp_ssr,Hansel_temp_ssr,Hansel_temp_ssr/(1-summary("+modelNameFinalStage+")$r.squared))"+
                                "\n Hansel_temp_df <- c("+modelNameFinalStage+"$rank-1,df.residual("+modelNameFinalStage+"),"+modelNameFinalStage+"$rank-1+df.residual("+modelNameFinalStage+"))"+
                                "\n Hansel_temp_ms <- Hansel_temp_ss/Hansel_temp_df"+
                                "\n Hansel_temp_f <-linearHypothesis("+modelNameFinalStage+", names(coef("+modelNameFinalStage+"))[-1],vcov="+vcovadj+")[2,3]"+
                                "\n Hansel_temp_P <- pf(Hansel_temp_f, Hansel_temp_df, df.residual("+modelNameFinalStage+"), lower.tail = FALSE)"+
                                "\n Hansel_temp_omnibusanovatable <- data.frame(Hansel_temp_ss, Hansel_temp_df, Hansel_temp_ms, Hansel_temp_f, Hansel_temp_P)"+
                                "\n Hansel_temp_omnibusanovatable[2:3,4:5] <- NA"+
                                "\n dimnames(Hansel_temp_omnibusanovatable) <- list(c(\"Regression\",\"Residual\", \"Total\"),"+
                                "\n                                         c(\"Sum Sq\", \"DF\", \"Mean Sq\", \"F value\", \"Pr(>F)\"))";
                        aeCall =" structure(Hansel_temp_omnibusanovatable,class= c(\"anova\", \"data.frame\"))";
                        String removalAfterTestCmd = 
                                "rm(Hansel_temp_ssr,Hansel_temp_ss,Hansel_temp_df,Hansel_temp_ms,Hansel_temp_f,Hansel_temp_P,Hansel_temp_omnibusanovatable) ";
                        cmd+= toEvalFirst+"\n"+aeCall + "\n" + removalAfterTestCmd;
                        if(preview){
                                try {   
                                     Deducer.eval(toEvalFirst);
                                     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                     Deducer.eval(removalAfterTestCmd);  
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("ANOVA Table (Omnibus)");
                                for(int i=0;(i<out.length);i++)
                                        tmp.add(out[i]); 
                                if (vcovadjModel.length()>0)
                                    tmp.add("\nRobust covariance matrix used: "+glmvcov);
                        } 
                        
                    } else if (method.equals("ivreg")){
                        toEvalFirst = "\n   "+modelName+"restr <- ivreg(formula="+dependentVar+"~1|"+this.instruments+
                         (modelWeights.length()==0 ? "" : ", weights="+modelWeights)+",data="+attachment+
                        ((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset) +")";

                        aeCall = "anova("+modelName+","+modelName+"restr"+vcovadjModel+")";
                        cmd+= toEvalFirst+"\n"+aeCall;
                        if(preview){
                                try {   
                                    Deducer.eval(toEvalFirst);
                                     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("ANOVA Table (Omnibus)");
                                for(int i=1;(i<out.length);i++)
                                        tmp.add(out[i]); 
                                if (vcovadjModel.length()>0)
                                    tmp.add("\nRobust covariance matrix used: "+glmvcov);
                    } 
                                    
                    } else /*if (method.equals("glm"))*/  {
                       
                        if (method.equals("tobit")) 
                            toEvalFirst=".hansel.working.env$"+ modelNameFinalStage+"_constantOnly <-tobit(formula="+dependentVar+"~1"+
                             (efeglmextra.leftLimitText.equals("")?"":",left="+efeglmextra.leftLimitText)+
                             (efeglmextra.rightLimitText.equals("")?"":",right="+efeglmextra.rightLimitText)+",data="+dName +
                        (modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
				((subset==null || subset.length()==0) ? "" : ",subset = "+subset)
                           /*  +",na.action=na.omit"*/ +")";
                        else
                           toEvalFirst=".hansel.working.env$"+ modelNameFinalStage+"_constantOnly <-"+method+"(formula="+dependentVar+"~1"+family+
                            ",data="+dName+(modelWeights.length()==0 ? "" : ", weights="+modelWeights)+
                                    ((subset==null || subset.length()==0) ? "" : ",subset = "+subset)+")";
                        
                        aeCall = "anova(.hansel.working.env$"+modelNameFinalStage+"_constantOnly,"+modelNameFinalStage+")";
                        String rmCall = "rm("+modelNameFinalStage+"_constantOnly"+",envir=.hansel.working.env)"; 
                        
                        String aeCall2 ="lht("+modelNameFinalStage+",names(coef("+modelNameFinalStage+"))[-1])";
                            if(preview){

                                    try {
                                         Deducer.eval(toEvalFirst);
                                         out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                         Deducer.eval(rmCall);
                                    } 
                                    catch (REXPMismatchException e) {
                                    new ErrorMsg(e);
                                    }

                                    tmp.add("");
                                    for(int i=0;i<out.length;i++)
                                            tmp.add(out[i]);
                                    if (method.equals("tobit")) {
                                      tmp.add("");
                                      tmp.add("The test given above is a likelihood ratio test.");
                                      tmp.add("");
                                    }
                                    cmd+="\n"+toEvalFirst.replace(".hansel.working.env$","");
                                    cmd+="\n"+aeCall.replace(".hansel.working.env$","");
                                    cmd+="\n"+rmCall.replace(",envir=.hansel.working.env","");
                            }else{
                                    cmd+="\n"+aeCall;
                            }
                            if(preview){
                                    try {
                                         Deducer.eval(toEvalFirst);
                                         out = Deducer.eval("capture.output("+aeCall2+")").asStrings();                                       
                                    } 
                                    catch (REXPMismatchException e) {
                                    new ErrorMsg(e);
                                    }
                                    for(int i=0;i<out.length;i++)
                                            tmp.add(out[i]);
                                    tmp.add("");
                                    tmp.add("The test given above is a Wald test.");
                                    cmd+="\n"+aeCall2;
                            }else{
                                    cmd+="\n"+aeCall2;
                            }
                        
                    }
                } 
                 
                else if(this.efeaetext.type.equals("I")){
			aeCall = "anova("+modelNameFinalStage+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } 
                                catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                tmp.add("");
                                 if (method.equals("tobit")) 
                                    tmp.add("The test(s) given above are likelihood ratio test(s).");
                                 
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}                      
		}
                else   { //must be type II or type III
                    String aeCall2="";
                       if (glmvcov.equals("No correction")){
                        if (method.equals("lm")||(method.equals("dynlm"))) {
                            aeCall = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"')";
                        } else {
			   aeCall = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"',test.statistic='LR')";
                           aeCall2 = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"',test.statistic='Wald')";
                          }
                        }
                       else if (glmvcov.equals("HC4m")||glmvcov.equals("HAC")) {
                           
                           
                       } else {
                          if (method.equals("lm")||(method.equals("dynlm"))) {
                            aeCall = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"'"+
                                    ",white.adjust='"+glmvcov.replace("HC", "hc")+"'"+
                                    ")";
                            Deducer.execute(aeCall);
                        } else {   
                            aeCall = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"',test.statistic='LR'"+
                                    ",white.adjust='"+glmvcov.replace("HC", "hc")+"'"+
                                    ")";
                            aeCall2 = "Anova("+modelNameFinalStage+",type='"+efeaetext.type+"',test.statistic='Wald'"+
                                    ",white.adjust='"+glmvcov.replace("HC", "hc")+"'"+
                                    ")";
                          }
                       } 
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } 
                                catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                
                                if (method.equals("lm")||(method.equals("dynlm"))) {
                                   cmd+="\n"+aeCall;  
                                } else {
                                    tmp.add("");
                                    tmp.add("The test(s) given above are likelihood ratio test(s).");
                                    tmp.add("");
                                    cmd+="\n"+aeCall;        

                                    try {    
                                    out = Deducer.eval("capture.output("+aeCall2+")").asStrings();
                                    } 
                                    catch (REXPMismatchException e) {
                                    new ErrorMsg(e);
                                    }
                                    for(int i=0;i<out.length;i++)
                                            tmp.add(out[i]);
                                    tmp.add("");
                                    tmp.add("The test(s) given above are Wald test(s).");
                                    cmd+="\n"+aeCall2;
                                }
			   } else {
				cmd+="\n"+aeCall;
                                cmd+="\n"+aeCall2;
			 }
		      
                }
              }    
                if(this.efeaetext.paramCorr){
			aeCall = "summary("+modelNameFinalStage+",correlation=TRUE)$correlation";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } 
                                catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("");
                                tmp.add("Parameter Correlation Matrix");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}      
                
                      

                
                
                if(this.efeaetext.vif){
			aeCall = "vif("+modelNameFinalStage+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("");
                                tmp.add("Variance Inflation Factors");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}

                 if(this.efeGLMOptions.coeffTest){
                     if (method.equals("ivreg")){
                           
                     	    toEvalFirst = "\n   "+modelName+"restr <- ivreg(formula="+this.efeaetext.constraints+
                                 (modelWeights.length()==0 ? "" : ", weights="+modelWeights)+",data="+attachment+
				((subset.equals(null) || subset.length()==0) ? "" : ",subset = "+subset) +")";
                                    
                    aeCall = "anova("+modelName+","+modelName+"restr"+vcovadjModel+")";
                    cmd+= toEvalFirst+"\n"+aeCall;
                    if(preview){
                            try {   
                                Deducer.eval(toEvalFirst);
                                 out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                            } catch (REXPMismatchException e) {
                            new ErrorMsg(e);
                            }
                            for(int i=0;(i<out.length);i++)
                                    tmp.add(out[i]); 
                            if (vcovadjModel.length()>0)
                                tmp.add("\nRobust covariance matrix used: "+glmvcov);
                    }      
                 } else if (method.equals("errorsarlm")||method.equals("lagsarlm")||method.equals("sacsarlm")){
                            toEvalFirst = "\n    "+modelName+"_target<- data.frame("+modelName+"$tary,"+modelName+"$tarX)"+
                                         "\n    colnames("+modelName+"_target) <- c(\""+this.dependentVar+"\",\"Intercept\","+VariablesListWithQuotes+")"+
                                         "\n    "+modelName+"_target_lm<- lm("+this.formula.replace("~","~ Intercept +")+" -1,data="+modelName+"_target)";  
                            String constraintsInR = this.efeaetext.constraints;
                            constraintsInR = "c(\""+constraintsInR+"\")";
                            constraintsInR=constraintsInR.replace(",","\",\"");
                            constraintsInR=constraintsInR.replace("b(0)","Intercept");
                            for(int i=1;i<=this.termsArray.length;i++){
                                constraintsInR=constraintsInR.replace("b("+i+")",this.termsArray[i-1]); 
                                        }
                            String vcovStatement = "";
                                    if (glmvcov.equals("HAC")){
                                        vcovStatement = ",vcovHAC("+modelName+"_target_lm)";
                                    } else if (!(glmvcov.equals("No correction"))) {
                                        vcovStatement = ",vcovHC("+modelName+"_target_lm,type=\"" +glmvcov+"\")";
                                    }
                            aeCall = "lht("+modelName+"_target_lm"+","+constraintsInR+","+vcovStatement+",test=\"Chisq\")";
                            cmd+= toEvalFirst;    
                            if(preview){
                                    try {
                                         Deducer.eval(toEvalFirst);
                                         out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                    } catch (REXPMismatchException e) {
                                    new ErrorMsg(e);
                                    }
                                    for(int i=0;i<out.length;i++)
                                            tmp.add(out[i]);

                                    cmd+="\n"+aeCall;
                            }else{
                                    cmd+="\n"+aeCall;
                            }
                            
                     }else if (method.equals("VAR")) {
                         Integer nunmrestricionsOnVar =this.efeaetext.constraints.replace(",","").replace("1","").replace(" ","").replace("\n","").length();             
                         toEvalFirst = "\n.hansel.working.env$restrictedMatrix=matrix(c(\n"+this.efeaetext.constraints+this.efeaetext.restrictedVARMatrixDims+
                                       "\n .hansel.working.env$constraintsMatrix=matrix(0,nrow="+nunmrestricionsOnVar+",ncol="+ this.efeaetext.constraints.replace(",","").replace(" ","").replace("\n","").length()+")"+
                                       "\n .hansel.working.env$ConstraintCount <- 0"+
                                       "\n for (hansel.working.temp.Rcol in 1: ncol(.hansel.working.env$restrictedMatrix)){"+
                                       "\n    for (hansel.working.temp.Rrow in 1:nrow(.hansel.working.env$restrictedMatrix)){"+
                                       "\n       if (.hansel.working.env$restrictedMatrix[hansel.working.temp.Rrow,hansel.working.temp.Rcol]==0){"+
                                       "\n         .hansel.working.env$ConstraintCount <- .hansel.working.env$ConstraintCount + 1"+
                                       "\n         .hansel.working.env$constraintsMatrix[.hansel.working.env$ConstraintCount,(hansel.working.temp.Rcol-1)*nrow(.hansel.working.env$restrictedMatrix)+hansel.working.temp.Rrow]=1}}}"+                                       
                                       "\n .hansel.working.env$PiVector <- as.vector(Bcoef("+modelName+"))"+
                                       "\n .hansel.working.env$avar <-.hansel.working.env$constraintsMatrix%*%vcov("+modelName+")%*%t(.hansel.working.env$constraintsMatrix)"+
                                       "\n .hansel.working.env$waldStat=t(.hansel.working.env$constraintsMatrix%*%.hansel.working.env$PiVector)%*%solve(.hansel.working.env$avar)%*%(.hansel.working.env$constraintsMatrix%*%.hansel.working.env$PiVector)";
                                     
                         aeCall = "\n c(\" \",\"Wald statistic:\",paste(\"   \",.hansel.working.env$waldStat),\" \",\"p-value based on chi-square distribution with "+
                                   nunmrestricionsOnVar +" degrees of freedeom: \","+
                                   "paste(\"   \", 1-pchisq(.hansel.working.env$waldStat,"+nunmrestricionsOnVar+")))";      
                         String rmCall = "\n rm(restrictedMatrix,constraintsMatrix,ConstraintCount,PiVector,avar,waldStat,envir=.hansel.working.env)"+
                                         "\n rm(hansel.working.temp.Rcol,hansel.working.temp.Rrow)";
                         cmd+= (toEvalFirst+aeCall+"\n"+rmCall).replaceAll(".hansel.working.env\\$","").replaceAll("hansel.working.temp.","").replaceAll("\\,envir=.hansel.working.env", ""); 
                         if(preview){
                                try {    
				     Deducer.eval(toEvalFirst).asStrings();
                                     out = Deducer.eval(aeCall).asStrings();
                                     Deducer.eval(rmCall);
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Wald test for zero restrictions on coefficients");
                                tmp.add("");
                                tmp.add("The null hypothesisis that coefficients are restricted to 0");
                                tmp.add("in positions shown in the matrix below.");
                                tmp.add("");
                                tmp.add(this.efeaetext.constraints);
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
			}
                              
                     }
                     else if ((method.equals("ca.jo"))||(method.equals("cajolst"))) {
                         String rank = this.varOptions.rankRestriction.substring(0,1);
                         Integer nvars = termsArray.length;
                                 
                         Integer ncointTerms = nvars+((varOptions.cointOption.substring(0,5).equals("const")||
                                           varOptions.cointOption.substring(0,5).equals("trend")) ? 1:0 );
                         DefaultListModel deleteColumnsAlpha= new DefaultListModel();
                         DefaultListModel deleteColumnsBeta= new DefaultListModel();
                         String userHypotheses = this.efeaetext.constraints;
                         String[] userHypothesesSplit = userHypotheses.split(",");
                         Integer alphaRestrictions = 0;
                         Integer betaRestrictions = 0;
                         Integer alphaZeroRestictions = 0;
                         Integer betaZeroRestictions = 0;

                         DefaultListModel hypothVectorAlphaAll = new DefaultListModel();
                         hypothVectorAlphaAll.addElement("0");
                         DefaultListModel hypothVectorBetaAll = new DefaultListModel();
                         hypothVectorBetaAll.addElement("0");
                         
                         DefaultListModel hypothVectorAlphaHold = new DefaultListModel();
                         DefaultListModel hypothVectorBetaHold = new DefaultListModel();

                         for(int j=0;j<userHypothesesSplit.length;j++){
                            String uHSj = userHypothesesSplit[j];
                            uHSj = uHSj.replace(")-",")+-");
                            uHSj = uHSj.replace(") -",")+-");
                            String[] uHSjSplit = uHSj.split("=");
                            String uHSbefore = uHSjSplit[0].toString();
                            String uHSafter = uHSjSplit[1].toString();
                                  
                            hypothVectorAlphaHold =hypothVectorAlphaAll;
                            Integer numalphacoeffsAffected = 0;
                            
                            if ((uHSbefore.indexOf("a(")!=-1)||(uHSafter.indexOf("a(")!=-1)){
                                Integer currentSize = hypothVectorAlphaAll.getSize();
                                for(int i=currentSize;i<(currentSize+nvars);i++){
                                    hypothVectorAlphaAll.addElement("0");
                                } 
                                alphaRestrictions = alphaRestrictions + 1;
                                if ((uHSbefore.indexOf("a(")!=-1)) {
                                String[]uHSbeforeterms = uHSbefore.toString().split("\\+");
                                numalphacoeffsAffected = numalphacoeffsAffected + uHSbeforeterms.length;
                                for(int i=0;i<uHSbeforeterms.length;i++){
                                    String restrictedVariable = uHSbeforeterms[i].substring(uHSbeforeterms[i].indexOf("(")+1,     
                                                    uHSbeforeterms[i].indexOf(")"));
                                    String coeff;
                                    if (uHSbeforeterms[i].indexOf("*")>0) {
                                      coeff = uHSbeforeterms[i].substring(0,uHSbeforeterms[i].indexOf("*"));
                                    } else if ((uHSbeforeterms[i].indexOf("-a")>0)||(uHSbeforeterms[i].indexOf("- a")>0)) {
                                      coeff = "-1";  
                                    } else {
                                      coeff = "1";
                                    }
                                    hypothVectorAlphaAll.setElementAt(coeff,(currentSize-1)+ Integer.parseInt(restrictedVariable));
                                    deleteColumnsAlpha.addElement(Integer.parseInt(restrictedVariable));
                                }
                                
                                if ((uHSafter.indexOf("a(")!=-1)) {
                                String[]uHSafterterms = uHSafter.toString().split("\\+");
                                numalphacoeffsAffected = numalphacoeffsAffected + uHSafterterms.length;
                                for(int i=0;i<uHSafterterms.length;i++){
                                    String restrictedVariable = uHSafterterms[i].substring(uHSafterterms[i].indexOf("(")+1,     
                                                    uHSafterterms[i].indexOf(")"));
                                    String coeff;
                                    if (uHSafterterms[i].indexOf("*")>0) {
                                      coeff = "-"+uHSafterterms[i].substring(0,uHSafterterms[i].indexOf("*"));
                                     } else if ((uHSbeforeterms[i].indexOf("-a")>0)||(uHSbeforeterms[i].indexOf("- a")>0)) {
                                      coeff = "1"; 
                                    } else {
                                      coeff = "-1";
                                    }
                                    hypothVectorAlphaAll.setElementAt(coeff,(currentSize-1)+Integer.parseInt(restrictedVariable));
                                    deleteColumnsAlpha.addElement(Integer.parseInt(restrictedVariable));
                                }

                                }
                                }
                            }
                            
                            if ((numalphacoeffsAffected.equals(1))&((uHSbefore.replace(" ","").equals("0"))||(uHSafter.replace(" ","").equals("0")))) {
                                   hypothVectorAlphaAll.removeRange(hypothVectorAlphaAll.getSize()-nvars, hypothVectorAlphaAll.getSize()-1);
                                   alphaZeroRestictions = alphaZeroRestictions + 1;
                            }                             
                            hypothVectorBetaHold = hypothVectorBetaAll;  
                            Integer numbetacoeffsAffected = 0;
                            
                            if ((uHSbefore.indexOf("b(")!=-1)||(uHSafter.indexOf("b(")!=-1)){
                                Integer currentSize = hypothVectorBetaAll.getSize();
                                for(int i=currentSize;i<(currentSize+ncointTerms);i++){ 
                                    hypothVectorBetaAll.addElement("0");
                                }
                                betaRestrictions = betaRestrictions + 1;
                                if ((uHSbefore.indexOf("b(")!=-1)) {
                                String[]uHSbeforeterms = uHSbefore.toString().split("\\+");
                                numbetacoeffsAffected = numbetacoeffsAffected + uHSbeforeterms.length;
                                for(int i=0;i<uHSbeforeterms.length;i++){
                                    String restrictedVariable = uHSbeforeterms[i].substring(uHSbeforeterms[i].indexOf("(")+1,     
                                                    uHSbeforeterms[i].indexOf(")"));
                                    String coeff;
                                    if (uHSbeforeterms[i].indexOf("*")>0) {
                                      coeff = uHSbeforeterms[i].substring(0,uHSbeforeterms[i].indexOf("*"));
                                    } else if ((uHSbeforeterms[i].indexOf("-b")>0)||(uHSbeforeterms[i].indexOf("- b")>0)) {
                                      coeff = "-1"; 
                                    } else {
                                      coeff = "1";
                                    }
                                    hypothVectorBetaAll.setElementAt(coeff,(currentSize-1)+ Integer.parseInt(restrictedVariable));
                                    deleteColumnsBeta.addElement(Integer.parseInt(restrictedVariable));
                                }
                                
                                if ((uHSafter.indexOf("b(")!=-1)) {
                                String[]uHSafterterms = uHSafter.toString().split("\\+");
                                numbetacoeffsAffected = numbetacoeffsAffected + uHSafterterms.length;
                                for(int i=0;i<uHSafterterms.length;i++){
                                    String restrictedVariable = uHSafterterms[i].substring(uHSafterterms[i].indexOf("(")+1,     
                                                    uHSafterterms[i].indexOf(")"));
                                    String coeff;
                                    if (uHSafterterms[i].indexOf("*")>0) {
                                      coeff = "-"+uHSafterterms[i].substring(0,uHSafterterms[i].indexOf("*"));
                                    } else if ((uHSbeforeterms[i].indexOf("-b")>0)||(uHSbeforeterms[i].indexOf("- b")>0)) {
                                      coeff = "1"; 
                                    } else {
                                      coeff = "-1";
                                    }
                                    hypothVectorBetaAll.setElementAt(coeff, (currentSize-1)+Integer.parseInt(restrictedVariable));
                                    deleteColumnsBeta.addElement(Integer.parseInt(restrictedVariable));
                                }

                                }
                                }
                            }
                            
                            if ((numbetacoeffsAffected.equals(1))&((uHSbefore.replace(" ","").equals("0"))||(uHSafter.replace(" ","").equals("0")))) {
                              hypothVectorBetaAll.removeRange(hypothVectorBetaAll.getSize()-ncointTerms, hypothVectorBetaAll.getSize()-1);
                              betaZeroRestictions = betaZeroRestictions + 1;
                            }
                            
                         }
                         
                        String hMatrixAlpha="";
                        if (alphaRestrictions>0) {
                             hMatrixAlpha = ",A=matrix(c(";
                                 if (hypothVectorAlphaAll.getSize()>1){
                                 for(int i=1;i<=hypothVectorAlphaAll.getSize()-2;i++){
                                  hMatrixAlpha = hMatrixAlpha + hypothVectorAlphaAll.elementAt(i) +",";
                                 }
                                 hMatrixAlpha = hMatrixAlpha + hypothVectorAlphaAll.elementAt(hypothVectorAlphaAll.getSize()-1);
                             }
                            for(int j=1;j<=nvars;j++){
                               if (!(deleteColumnsAlpha.contains(j)))
                                   for(int k=1;k<=nvars;k++){
                                       if (j==k)hMatrixAlpha = hMatrixAlpha +((hMatrixAlpha.length() >12)?",":"") +"1";
                                       else     hMatrixAlpha = hMatrixAlpha +((hMatrixAlpha.length() >12)?",":"") +"0";
                                   }
                            }
                            hMatrixAlpha = hMatrixAlpha +"),nrow="+nvars+",ncol="+(alphaRestrictions-alphaZeroRestictions+nvars-deleteColumnsAlpha.getSize())+")";
                        }
                        
                         String hMatrixBeta = "";
                         if (betaRestrictions>0) {
                             hMatrixBeta = ",H=matrix(c(";
                             if (hypothVectorBetaAll.getSize()>1){
                                 for(int i=1;i<=hypothVectorBetaAll.getSize()-2;i++){
                                  hMatrixBeta = hMatrixBeta + hypothVectorBetaAll.elementAt(i)+",";
                                 }
                                 hMatrixBeta = hMatrixBeta + hypothVectorBetaAll.elementAt(hypothVectorBetaAll.getSize()-1);
                             }
                            for(int j=1;j<=ncointTerms;j++){
                               if (!(deleteColumnsBeta.contains(j)))
                                   for(int k=1;k<=ncointTerms;k++){
                                       if (j==k)hMatrixBeta = hMatrixBeta + ((hMatrixBeta.length() >12)?",":"") +"1";
                                       else     hMatrixBeta = hMatrixBeta +((hMatrixBeta.length() >12)?",":"")+ "0";
                                   }
                            }
                            hMatrixBeta = hMatrixBeta +"),nrow="+ncointTerms+",ncol="+(betaRestrictions-betaZeroRestictions+ncointTerms-deleteColumnsBeta.getSize())+")";
                        }
                           if ((alphaRestrictions>0)&(betaRestrictions>0))
                               aeCall= "summary(ablrtest( ";
                           else if (alphaRestrictions>0)
                               aeCall= "summary(alrtest( ";
                           else if (betaRestrictions>0)
                               aeCall= "summary(blrtest( ";
                           aeCall = (aeCall+modelName+hMatrixBeta+hMatrixAlpha+",r="+this.varOptions.rankRestriction.substring(0,1)+"))");


			if(preview){
                                try {
                                    if ((alphaRestrictions>0)&!(betaRestrictions>0))
                                       out = Deducer.eval("gsub(\"restrictions on beta\",\"restrictions on alpha\",(capture.output("+aeCall+")))").asStrings();
                                    else
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                tmp.add("\nThe R command producing the above output was: \n "+aeCall+")\n");
                                cmd+="\n"+aeCall;
                                if ((alphaRestrictions>0)&!(betaRestrictions>0)){
                                    
                                    cmd+="\n"+"#The term \"restrictions on beta\" needs to be replaced by \"restrictions on alpha\"";
                                    cmd+="\n"+"#in the summary output to fix a typo from the original urca package.";
                                }
			}else{
				cmd+="\n"+aeCall;
			}
                     } else  {
                        String constraintsInR = this.efeaetext.constraints;
                        constraintsInR = "c(\""+constraintsInR+"\")";
                        constraintsInR=constraintsInR.replace(",","\",\"");
                        constraintsInR=constraintsInR.replace("b(0)","(Intercept)");
                        for(int i=1;i<=this.termsArray.length;i++){
                            constraintsInR=constraintsInR.replace("b("+i+")",this.termsArray[i-1]); 
                                    }
                        aeCall = "lht("+modelNameFinalStage+","+constraintsInR+vcovadjModel+")";
                        if(preview){
                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                      }
		 }                           
                 
                 if(this.efeaetext.causalityTest){
			aeCall = "causality("+modelName+",cause=c("+efeglmextra.causalVariables +")"+
                                 (efeglmextra.bootstrapChoice.equals("bootstrap")?",boot=TRUE, boot.runs="+
                                  Integer.valueOf(efeglmextra.bootstrapReps):"")+")";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Tests for Causality");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                 if(this.efeGLMOptions.confIntervals){
                       
                       String lowerBound ="";
                       String lowerBoundpct="";
                       String upperBound="";
                       String upperBoundpct="";
                       String confInt = Deducer.getUniqueName("confInt");
                       if (this.efeGLMOptions.confIntCov.equals("80%"))
                               {lowerBound = "0.1"; upperBound = "0.9"; lowerBoundpct = "10"; upperBoundpct = "90";  }
                       else if (this.efeGLMOptions.confIntCov.equals("90%"))
                               {lowerBound = "0.05"; upperBound = "0.95"; lowerBoundpct = "5"; upperBoundpct = "95";}
                       else if (this.efeGLMOptions.confIntCov.equals("95%"))
                               {lowerBound = "0.025"; upperBound = "0.975"; lowerBoundpct = "2.5"; upperBoundpct = "97.5";}
                       else if (this.efeGLMOptions.confIntCov.equals("99%"))
                               {lowerBound = "0.005"; upperBound = "0.995"; lowerBoundpct = "0.5"; upperBoundpct = "99.5";}
                       else if (this.efeGLMOptions.confIntCov.equals("99.9%"))
                               {lowerBound = "0.0005"; upperBound = "0.9995"; lowerBoundpct = "0.05"; upperBoundpct = "99.95";}
          

                     String ciCreationCommand =""; 
                          if (method.equals("tobit")){
                             ciCreationCommand = confInt+"<-summary("+modelNameFinalStage+")$coefficients[,1]+sqrt(diag(vcov("+modelNameFinalStage+")))%o%c(qnorm("+lowerBound +"),qnorm("+upperBound+"))\n";
                                  
                          } else {
                              ciCreationCommand = confInt+"<-coef("+modelNameFinalStage+")+sqrt(diag("+vcovadj+"))%o%qt(c("+lowerBound+","+upperBound+"),df.residual("+modelName+"))\n";
                                                  
                          }
                          ciCreationCommand += "dimnames("+confInt+")[2] <- list(c(\""+lowerBoundpct +"%\",  \""+ upperBoundpct +"%\"))\n"+                           
                                                  "dimnames("+confInt+")[2] <- list(c(\""+lowerBoundpct +"%\",  \""+ upperBoundpct +"%\"))";
                          Deducer.eval(ciCreationCommand);
			aeCall = confInt+"";
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add(this.efeGLMOptions.confIntCov+" Confidence Interval");
                                tmp.add(" ");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                // cmd+ is capturing for the commands log what was already evaluated above. 
                                cmd += "\n"+ciCreationCommand + "\n" + aeCall;

			}else{
				cmd+="\n"+aeCall;
			}
                        Deducer.eval("rm("+confInt+")");
                        cmd+="\n"+"rm("+confInt+")";
		}
		
		if(this.efeaetext.influence){
			aeCall = "summary(influence.measures("+modelNameFinalStage+"))";
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=1;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
  
                        if(this.efeaetext.jarqueBeraTest){
                            Deducer.eval("library(\"tseries\")");
                            if (method.equals("pgmm"))
                                aeCall = "jarque.bera.test(unlist(resid("+modelNameFinalStage+")))";
                            else
                                aeCall = "jarque.bera.test(resid("+modelNameFinalStage+"))";
                            
			if(preview){

                                try {    
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
		}
                
		if(this.efeaetext.ramseyRESET){
			aeCall = "reset(formula("+modelNameFinalStage+"),power=2,type=\"fitted\",data="+data+")";
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                out[2] = "Ramsey's RESET test using squared fitted terms:";
				for(int i=2;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                     aeCall = "reset(formula("+modelNameFinalStage+"),power=2:3,type=\"fitted\",data="+data+")";
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                out[2] = "Ramsey's RESET test using squared and cubed fitted terms:";
				for(int i=2;i<out.length;i++)
					tmp.add(out[i]);
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}   
		}   
                
                if(this.efeaetext.classificationTable){
                    
                        String cmd2 = "\n"+
                                      "Predicted_categories <- cut(fitted("+modelNameFinalStage+"), breaks=c(-Inf,"+this.efeaetext.classTableCutoff+", Inf), labels=c(\"Pr(Dep=1)<=cutoff\", \"Pr(Dep=1)>cutoff\"))\n"+
                                      (this.dependentVar.contains("(")?"attach("+dName+")\nObserved <- "+ this.dependentVar +" \ndetach("+dName+")\n":
                                      "Observed <- "+dName+"[,\""+this.dependentVar +"\"]\n")+
                                      "cTab <- table(Predicted_categories ,Observed) \n"+
                                      "dTab <- addmargins(table(Predicted_categories ,Observed)) \n"+                   
                                      "dTab2 <- dTab \n"+
                                      "dimnames(dTab2)$Predicted_categories[1] <- \"% Correct\" \n"+
                                      "dimnames(dTab2)$Predicted_categories[2] <- \"unused\" \n"+
                                      "dimnames(dTab2)$Predicted_categories[3] <- \"unused\" \n"+
                                      "dTab2[1,] <- c(round(100*diag(cTab)/ c(sum(cTab[,1]),sum(cTab[,2])),2),round(sum(100*diag(cTab))/dTab[3,3],2))";

                        Deducer.eval(cmd2);
                        cmd+=cmd2;
                       /* Deducer.execute(cmd2);*/ //debug
                        
                        tmp.add("Expectation-Prediction(Classification) Table");
                        tmp.add("Dependent Variable: "+this.depvar);
                        tmp.add("Success cutoff: "+this.efeaetext.classTableCutoff);
                        
                        aeCall="c(\"\",capture.output(rbind(dTab,dTab2)[1:4,]))";
			if(preview){
                                try { 
                                    out = Deducer.eval(aeCall).asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                try { 
                                    Deducer.eval("rm(dTab,dTab2,Predicted_categories,Observed,cTab)").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                        
		}  
                                               
        
                 if(this.efeaetext.weakInstrumentsTestivreg){
                   String vcovadj ="";
                        if (glmvcov.equals("HAC"))
                            vcovadj = ",vcov=vcovHAC("+this.modelName+")";  
                        else if (!(glmvcov.equals("No correction")))
                            vcovadj = ",vcov=vcovHC("+this.modelName+",type='"+glmvcov+"')"; 
                        
                   aeCall = "data.frame(summary("+this.modelName+vcovadj+",diagnostics=TRUE)$diagnostics)[1,]";     
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Weak instruments test");
                                if (!(glmvcov.equals("No correction")))
                                    tmp.add("with "+ glmvcov + " covariance matrix adjustment");
                                tmp.add("");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                tmp.add("");
                                 
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                    }
        

                
                 if(this.efeaetext.hausmanTestivreg){
                                        String vcovadj ="";
                        if (glmvcov.equals("HAC"))
                            vcovadj = ",vcov=vcovHAC("+this.modelName+")";  
                        else if (!(glmvcov.equals("No correction")))
                            vcovadj = ",vcov=vcovHC("+this.modelName+",type='"+glmvcov+"')"; 
                        
                   aeCall = "data.frame(summary("+this.modelName+vcovadj+",diagnostics=TRUE)$diagnostics)[2,]";     
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Wu-Hausman test of OLS-estimates consistency");
                                if (!(glmvcov.equals("No correction")))
                                    tmp.add("with "+ glmvcov + " covariance matrix adjustment");
                                tmp.add("");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                tmp.add("");
                                
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                     
		}                
                
                 
                if(this.efeaetext.sarganTestivreg){
                       String vcovadj ="";
                        if (glmvcov.equals("HAC"))
                            vcovadj = ",vcov=vcovHAC("+this.modelName+")";  
                        else if (!(glmvcov.equals("No correction")))
                            vcovadj = ",vcov=vcovHC("+this.modelName+",type='"+glmvcov+"')"; 
                        
                   aeCall = "data.frame(summary("+this.modelName+vcovadj+",diagnostics=TRUE)$diagnostics)[3,]";     
			if(preview){
                                try { 
				     out = Deducer.eval("capture.output("+aeCall+")").asStrings();
                                } catch (REXPMismatchException e) {
                                new ErrorMsg(e);
                                }
                                tmp.add("Sargan over-identification test");
                                if (!(glmvcov.equals("No correction")))
                                    tmp.add("with "+ glmvcov + " covariance matrix adjustment");
                                tmp.add("");
				for(int i=0;i<out.length;i++)
					tmp.add(out[i]);
                                tmp.add("");
                                
                                cmd+="\n"+aeCall;
			}else{
				cmd+="\n"+aeCall;
			}
                    }
                                  
		return cmd;
	}

	public static String makeGrangerFormula(DefaultListModel outcomes,String[] termsArray, Integer givenLag){
                Integer lastlag =givenLag;
		String formula = outcomes.get(0)+" ~ ";
                if (termsArray==null){
                for(int lag=1;lag<=lastlag;lag++){
                    formula+="lag("+outcomes.get(0)+","+lag+")";
                    if(lag<=lastlag-1)
				formula+="+";
                }
                }
                else {
                for(int lag=1;lag<=lastlag;lag++)
                    formula+="lag("+outcomes.get(0)+","+lag+")+";
                
		for(int i=0;i<termsArray.length;i++){
                    for(int lag=1;lag<=lastlag;lag++){
                        formula+="lag("+termsArray[i]+","+lag+")";
			if ((i<termsArray.length-1)||(lag<=lastlag-1))
				formula+=" + ";
                    }
		  }
                }
                    
		return formula;
	}


      	public String runaddd(boolean preview,RModel prevModel){
                String cmdClassic2 = "hello";  
                return cmdClassic2;
	}      
    
            
            
	public String[] runae(boolean preview,RModel prevModel){
		String cmd = "";
                String dataName;
                
                String[] out = new String[]{};

		String modelName ;
                modelName = prevModel.modelName;
                dataName = prevModel.data;
                ArrayList tmp = new ArrayList();
                String initcomment = "";
                String morecomment ="";
		cmd=runaeText(initcomment, cmd, morecomment,modelName,true,tmp);
	        cmd=runPostHoc(cmd,modelName,true,tmp);
		cmd=runEffects(cmd,modelName,true,tmp,prevModel);
		cmd=runPlots(cmd,modelName,true,tmp,prevModel);
		cmd=runTests(cmd,modelName,true,tmp,prevModel);
		cmd=runExport(cmd,modelName,true,tmp,dataName,false);
       
                String addexp = "";
		for(int i =0;i<tmp.size();i++)
			addexp+=tmp.get(i)+"\n";

                String[] returnedStringArray = {initcomment,cmd, morecomment, addexp};
                return returnedStringArray;
	}
        	protected String runHanselExport(String cmd,String modelName,boolean preview,String dataName,boolean glmNotLm){
                    if(!preview){
                        String temp = ".hansel.working.env$export.tmp";
                        String namesString="";
                        String namesCmd;
                        glmNotLm = method.equals("glm");
			boolean anyExport=false;
			if(hanselExport.resid){
			       anyExport=true;
                               for(int i=0;i<(method.equals("VAR")?efeglmextra.chosenTerms.length:1);i++){
                                if (method.equals("VAR")) {
                                     cmd+="\n"+temp+"<-"+ "c(rep(NA,"+modelName+"$totobs-"+modelName+"$obs" +"),"+modelName+"$varresult$"+efeglmextra.chosenTerms[i]+"$resid)";
                                     namesCmd="rev(make.unique(c(names("+dataName+"),\"resid_"+efeglmextra.chosenTerms[i]+"\")))[1]";
                                } else if (method.equals("pgmm")) {
                                     cmd+="\n"+temp+"<-unlist(residuals("+modelName+"))";
                                      namesCmd="rev(make.unique(c(names("+dataName+"),\"residuals\")))[1]";
                                } else {
                                      cmd+="\n"+temp+"<-residuals("+modelName+")";
                                      namesCmd="rev(make.unique(c(names("+dataName+"),\"residuals\")))[1]";
                                }
				
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                   cmd+=dName+"<-merge("+dName+","+temp+")\n"+
                                         "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\""; 
                                }
                               }
			}
                       
                      if(hanselExport.cointRel){
     			      anyExport=true;
                              for(int i=0;i<efeglmextra.chosenTerms.length;i++){
                                if (method.equals("ca.po")) {
                                   if (efeglmextra.cointPOType.equals("Pz"))
                                     cmd+="\n"+temp+"<-c("+modelName+"@res[,\""+efeglmextra.chosenTerms[i]+"\"],NA)";
                                   else /*type is Pu*/
                                     cmd+="\n"+temp+"<-"+modelName+"$"+efeglmextra.chosenTerms[i]+"_left@res";  
                                } else /* Engle Granger*/{
                                     cmd+="\n"+temp+"<-"+modelName+"$"+efeglmextra.chosenTerms[i].toString().replace("(","_").replace(")", "")+"$residuals";
                                     
                                }
				namesCmd="rev(make.unique(c(names("+dataName+"),\"coint_rel_"+efeglmextra.chosenTerms[i]+"_on_left\" )))[1]";
                     /*  Deducer.execute(cmd);*/ //debug      
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                             
                              }
			}
                        if(hanselExport.residForDiff){
     		             anyExport=true;
                             for(int i=0;i<efeglmextra.chosenTerms.length;i++){
                                cmd+="\n"+temp+"<-c(rep(NA,nrow("+modelName+"@x)-nrow("+modelName+"@R0)" +"),"+modelName+"@R0[,\"R0."+efeglmextra.chosenTerms[i]+".d\"])";
                               	namesCmd="rev(make.unique(c(names("+dataName+"),\""+efeglmextra.chosenTerms[i]+"_dif_resids\" )))[1]";
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
                             }
			}
                        if(hanselExport.residForLag){
     		              anyExport=true;
                              for(int i=0;i<efeglmextra.chosenTerms.length;i++){
                                cmd+="\n"+temp+"<-c(rep(NA,nrow("+modelName+"@x)-nrow("+modelName+"@RK)-1" +"),"+modelName+"@RK[,\"RK."+efeglmextra.chosenTerms[i]+".l1\"],NA)";
                               	namesCmd="rev(make.unique(c(names("+dataName+"),\""+efeglmextra.chosenTerms[i]+"_one_lag_level_resids\" )))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
                              }
			}       
                                
                        
			if(hanselExport.sdresid){
				anyExport=true;

				cmd+="\n"+temp+"<-rstandard("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"resid.standardized\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                               
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
			if(hanselExport.stresid){
				anyExport=true;
				cmd+="\n"+temp+"<-rstudent("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"resid.studentized\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }

			}
			if(hanselExport.pred){
                          anyExport=true;
                          namesCmd="";
                          for(int i=0;i<(method.equals("VAR")?efeglmextra.chosenTerms.length:1);i++){
                            if (glmNotLm){
				cmd+="\n"+temp+"<-predict("+modelName+",type='response')";
                            } else if (method.equals("tobit")){
                                cmd+="\n"+temp+"<-predict("+modelName+")";
                            }else if (method.equals("plm")) {
                                    cmd+="\n"+temp+"<-"+modelName+"$model[[1]]- residuals("+modelName+")";
                            }else if (method.equals("pgmm")) {
                                    cmd+="\n"+temp+"<-"+"fitted("+modelName+")";
                            } else if (method.equals("VAR")) {
                                     cmd+="\n"+temp+"<-"+ "c(rep(NA,"+modelName+"$totobs-"+modelName+"$obs" +"),"+modelName+"$datamat$"+efeglmextra.chosenTerms[i]+"-"+modelName+"$varresult$"+efeglmextra.chosenTerms[i]+"$resid)";
                                     namesCmd="rev(make.unique(c(names("+dataName+"),\"pred_"+efeglmextra.chosenTerms[i]+"\")))[1]";
                                     
                                     
                                     
                            } else        
                                    cmd+="\n"+temp+"<-predict("+modelName+")";			
                            if (!method.equals("VAR")) 
                               namesCmd="rev(make.unique(c(names("+dataName+"),\"predicted\")))[1]";
                            
                            try{
                            namesString=Deducer.eval(namesCmd).asString();
                             }catch(Exception e){
                                new ErrorMsg(e);
                             }
                             cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                             if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
                          }
                        }
                        
			if(hanselExport.linearPred && glmNotLm){
				anyExport=true;
				cmd+="\n"+temp+"<-predict("+modelName+",type='link')";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"linear.pred\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
                        if(hanselExport.cooks){
				anyExport=true;
				cmd+="\n"+temp+"<-cooks.distance("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"cooks\")))[1]";
                                
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
                        
			if(hanselExport.dfbeta){
				anyExport=true;
                                namesCmd="c(paste(\"dfb.\",colnames(dfbeta("+modelName+")),\".\",\""+modelName+"\",sep=\"\"))";

                                cmd+="\n"+temp+"<-dfbeta("+modelName+")";
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                        dataName+"[,"+namesCmd+"]<-"+temp+
                                         "}else "+dataName+"[,"+namesCmd+"]<-"+namesCmd;
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }                                              
                                
			}
			if(hanselExport.dffits){
				anyExport=true;
				cmd+="\n"+temp+"<-dffits("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"dffits\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
			if(hanselExport.hats){
				anyExport=true;
				cmd+="\n"+temp+"<-hatvalues("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"hats\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
			if(hanselExport.covratio){
				anyExport=true;
				cmd+="\n"+temp+"<-covratio("+modelName+")";
                                namesCmd="rev(make.unique(c(names("+dataName+"),\"cov.ratio\")))[1]";
                                try{
                                namesString=Deducer.eval(namesCmd).asString();
                                 }catch(Exception e){
                                    new ErrorMsg(e);
                                 }
                                cmd+="\n if (nrow("+dataName+")>1){\n"+
                                         dataName+"[,\""+namesString+"\"]<-"+"as.numeric("+temp+")\n"+
                                         "}else "+dataName+"[,\""+namesString+"\"]<-"+"\""+namesString+"\"";
                                if (existsTS){
                                    cmd+=
                                        dName+"<-merge("+dName+","+temp+")\n"+
                                        "colnames("+dName+")[ncol("+dName+")]<-"+"\""+namesString+"\"";
                                }
			}
		}
		return cmd;
	}
        
        
       
        
	class GLMOptions{
		public boolean summary = false;
		public boolean paramCor = false;
		public boolean anova = false;
		public String type = "II";
		public String test = "Wald";
		public boolean vif = false;
		public boolean influence = false;
	}
	class EFEPlots{
		public boolean confInt = true;		
		public boolean scaled = false;
		public boolean multi2 = false;
		public boolean rug = true;
	}
        class EFEaePlots{
                public String moreDiagnosticPlots = "None";
                public boolean plotImpulseResponseFunction = false;
                public String impulseToPlot = "";
                public String responsesToPlot = "";
                public String termsToPlot = "";
                public String termsToPlotVector = "";
		public boolean resvFitted = false;		
		public boolean termPlots = false;
	}
        class EFEGLMExtra{
                public String origDepVar = "";
                public DefaultListModel origDepVarMod;
                public DefaultListModel endogVariablesD = new DefaultListModel();
                public DefaultListModel endogVarsD = new DefaultListModel();
                public DefaultListModel cointTestVariablesD = new DefaultListModel();
                
                public DefaultListModel cointTestVarsD = new DefaultListModel();
                public DefaultListModel instrumentsD = new DefaultListModel();
                public DefaultListModel exogVarsD = new DefaultListModel();
                public DefaultListModel cointExogVarsD = new DefaultListModel(); 
                public String[] endogVariables;
                public String[] endogVars;
                public String[] cointTestVariables;                
                public String[] cointTestVars;
                public String[] exogVars; 
                public String[] cointExogVars; 
                public Boolean interceptIncluded = true; 
                public Boolean spatialLagDepIncluded = false;
                public Boolean panelTrendIncluded = false;
                public JTextPane previewTP;
                public JTextPane previewSmallTP;
                public Boolean versionTSexists;
                public Boolean classicView;
                public Boolean vecExploreVAR = false;
                public String vecName;
                public String glmvcovOrig = "";
                public String plmvcovOrig = "";
                public String pgmmvcovOrig = "";
                public String stslsvcovOrig = "";
                public String gstslsvcovOrig = "";
                public String cointEGOptionChosen = "";
                public String cointPOType = "";
                public String cointPOOptionChosen = "";
                public String cointPOLagChosen = "";
                public String panelVariableLagStart = "0";
                public String panelVariableLagEnd = "0";
                public Boolean panelVariableLagRun = false;
                public String titleModelforCoefTest = "";
                public String titleManualAltCoefTest = "";
                public String chosenTerm ="";
                public String[] chosenTerms;
                public String diffOrLevelEG ="";
                public String diffOrLevelJo ="";
                public String glmPlmChoices ="";
                public String TSlabels ="";
                public int [] causalSelection = {-1};
                public String bootstrapChoice ="";
                public String bootstrapReps ="";
                public String causalVariables;
                public Boolean previousEstimation = false;
                public String PriorModelName= "";
                public String[] currentModelList= null;
                public String varOrCointRelpo = "";
                public String TSMainTitle = "";
                public String leftLimitText = "";
                public String rightLimitText = "";
                public DefaultListModel spdfList = new DefaultListModel();
                public DefaultListModel nbList = new DefaultListModel();
                public int [] nbSelection = {-1};
                public String spdfForModel = "";
                public String nbForModel = "";
                public String listWForModel = "";
                public Boolean SpatialDurbin = false;
                public String Spatialdf = "";
                public String zeroPolicyType = "NULL";
                public String spatialMethodType = "eigen";
                public String spgmMethodType = "w2sls";
                public Boolean zerosInitval = true;
                public Boolean hess  = false;
                public Boolean quiet = true;
                public Boolean notQuiet = false;
                public Boolean verbose = false;
                public String spgmMethod = "w2sls";
                public Boolean arnoldWied = false;
                public Boolean scaleU = false;
                public Boolean W2X = false;
                public String spautolmFamily = "SAR";
                public Boolean spatialError = false;
                public String manualVariablesInput = "";        

         }
        class EFEaeText{
                public boolean archTestMulti = false;
                public boolean archTestMultiMonly = true;
                public String archTestMultiLagsM ="16";
                public String archTestMultiLagsS ="None: Multivariate only";
                public boolean breuschGodfreyMulti = false;
                public boolean breuschGodfreyMAsymptotic = true; 
                public boolean breuschGodfreyMEdgertonShukur = false;
                public String breuschGodfreyMLags = "5";
                public boolean portmanteauMulti = false;
                public boolean portmanteauMAsymptotic = true;
                public boolean portmanteauMAdjusted = false;
                public String portmanteauMLags = "16";
                public boolean normalityTestMulti = false;
                public boolean normalityTestMultiMonly = true;
                public boolean eigenvaluesVARcompanion = false;
                public boolean coeffsMArepOfVAR = false;
                public boolean coeffsMArepOfVAROrthog = false;
                public String coeffsMArepOfVARnsteps = "10";
                public boolean blanchardQuahSVAR = false;
                
                public boolean breuschPaganTest = false;
                public boolean breuschPaganTestEffectsOnly = false;
                public boolean whiteTest = false;
                public boolean durbinWatsonTest = false;
                public boolean BreuschGodfreyTest = false;
                public boolean ljungBoxTest = false;
                public boolean woolridgeUnobservedEffectsTest = false;
                public boolean woolridgeTestShortFE = false;
                public boolean woolridgeTest1stDiff = false;
                public boolean localRobustJointTest = false;
                public boolean localRobustAR1Test = false;
                public boolean localRobustRETest = false;
                public boolean baltagiLiTest1sided = false;
                public boolean baltagiLiTest2sided = false;
                public boolean crossSectionDepTestBP = false;
                public boolean crossSectionDepTestScaledBP = false;
                public boolean crossSectionDepTestPesaran = false;
                public boolean arellanoBondTest = false;
                public boolean LMTestBP = false;
                public boolean LMTestGhm = false;
                public boolean LMTestHonda = false;
                public boolean LMTestKw = false;
                public boolean pFTest = false;
                public boolean paramCorr = false;
		public boolean paramCor = false;
		public boolean anova = false;
		public String type = "Omnibus";
		public String test = "Wald";
                public boolean vif = false;
                public boolean influence = false;
                public boolean jarqueBeraTest = false;
		public boolean ramseyRESET = false;
                public boolean poolabilityTest = false;
                public boolean overIDRestrictionsTest = false;
                public boolean hausmanTest = false;
                public boolean weakInstrumentsTestivreg = false;
                public boolean sarganTestivreg = false;
                public boolean hausmanTestivreg = false;
                public String constraints = "";
                public String paramformula = "";
                public boolean classificationTable = false;
                public String  classTableCutoff= "0.5";
                public String unrestrictedVARMatrix= "";
                public String restrictedVARMatrixDims;
                public boolean causalityTest = false;
                public boolean LMSpatialDep = false;
                public String pAdjustMethod = "none";
                public boolean moransIorIitest = false;
                public String moransITestType= "Moran's I test";
                public String moransIaltHypoth= "greater";
                public String moransIaltResFun= "weighted.residuals";
                public boolean spHausmanTest = false;
                public boolean bsjkTest = false;
                public String bsjktestType = "C.1";
                public boolean bskTest = false;
                public String bsktestType = "LMH";
                public boolean userDidInputText = false;
                public String userInputChangedText = "";
                
	}          
        class EFEGLMOptions{
                public boolean confIntervals = false;
                public String confIntCov = "0.95";
                public boolean coeffTest = false;
                public boolean coeffTestsGUI = false;
                public boolean postHocGUI = false;
                public boolean grangerCausality = false;
                public Integer grangerCausalitylags; 
                public String grangerVariants;
        }
        class UnitRootTestOptions{
                public Boolean unitRootTestRun;
                public String variableChoiceOption;
                
                public String differencing;
                
                public String adfDeterministicComponent = "no deterministic component";
                public String adfLagDetermination = "Fixed";
                public String adfMaxLag="1";
                
                
                public String ersDeterministicComponent = "constant";
                public String ersType = "DF-GLS" ;
                public String ersMaxLag = "1";
                 
                public String kpssDeterministicComponent;
                public String kpssMaxLag = "short";
                
                public String ppDeterministicComponent;
                public String ppType = "Z-alpha";
                public String ppLag = "short";
                
                public String spType = "tau";
                public String spPolynDegree = "2";
                public String spSig = "0.05";
                
                public String zaBreakControl = "intercept";
                public String zaMaxLag = "NULL";
                
                public String maxLagEG;
                public String infoCriterion2 ="None, just use given lag";
	}
        class WaveletOptions{
                public String waveletPresentation = "DWT";
                public String waveletPlotType = "Standard";
                public String waveletLevels = "3";
                public String waveletType= "haar";
	}
        class ArimaOptions{
                public String arOrder = "0";
                public String integrationOrder = "0";
                public String maOrder = "0";
                public String boxTestLags = "0";
                public String predictAhead = "0";
	}
        class FilterOptions {
                public Boolean filterRun = false;
                public String movingAverageType = "Simple";
                public String simpleMAOrder = "4";
                public Boolean centreSimpleMA = true;
                public String polynomialOrder = "2";
                public String fourierOrder = "0";
                public String fourierPeriodicity = "4*(length of data)";
                public String garchOrder;
                public String archOrder;
                public String hpFrequency = "NULL";
                public String hpFilterType = "lambda";
                public Boolean hpDrift = false;
                public String loessType = "Simple Lowess";
                public String lowessSpan = "2/3";
                public String lowessIter ="3";
                        }
        class ExpSmoothingOptions{
                public String procedure = "Holt-Winters";
                public String alpha = "NULL";
                public String beta = "NULL";
                public String gamma = "NULL";
                public String errorType ="A";
                public String basictrendType="N";
                public String seasonalType= "N";
                public String damped ="NULL";              
                public String optimizationCriterion="mse";
                public String infoCriterion="sic";
                public String predictAhead= "0";
	}
        
        class UTSOtherPlotsOptions{
                public String maxLag = "default";
                public String minLag = "max lag";
	}
        
        class VAROptions{
                public String cointType = "";
                public String constantTrend = "";
                public String seasonalDummies= "";
                public String maxLag= "";
                public String infoCriterion= "None, just use given lag";
                public String cointOption = "no constant or trend, transitory";
                public String rankRestriction = "1 equation"; 
	} 
         class IRFOptions{
                public String nahead = "10";
                public String confIntCov = "0.95";
                public Boolean orthogonalized = true;
                public Boolean cumulative = true;
                public Boolean bootstrap = true;
                public int [] impulseSelection = {-1};
                public int [] responseSelection = {-1};

	} 
         
         
          class PlotControl{
                public Boolean previousPlot6= false;
                public String[] preCommand= {"","","","","","",""};
                public String[] postCommand= {"","","","","","",""};
                public Boolean[] traditionalGraphicsAvailable = {false, false, false, false, false, false, false};
                public Boolean[] latticeGraphicsAvailable = {false, false, false, false, false, false, false};
                public Boolean[] ggplot2GraphicsAvailable = {false, false, false, false, false, false, false};
                public String[] graphicSystem = {"traditional graphics","traditional graphics","traditional graphics",
                                                 "traditional graphics","traditional graphics","traditional graphics","traditional graphics"};
                public String[] traditionalPlotBeginning= {"","","","","","",""};
                public String[] traditionalPlotFormula= {"","","","","","",""};
                public String[] traditionalPlotEnd= {"","","","","","",""};
                public String[] traditionalPlotBeginning2= {"","","","","","",""};
                public String[] traditionalPlotBeginning3= {"","","","","","",""};
                public String[] latticePlotBeginning= {"","","","","","",""};
                public String[] latticePlotFormula= {"","","","","","",""};
                public String[] ggplot2Beginning= {"","","","","","",""};
                public String[] ggplot2PlotFormula= {"","","","","","",""};
                public String[] plotFunction= {"","","","","","",""};
                public String[] plotRelation= {"","","","","","",""};
                public Integer[] PlotControlsTab = {0,0,0,0,0,0,0};
                
                public String[] mainTitle = {"","","","","","",""};
          
                public String[] keyPosition = {"","","","","","",""};
                public Boolean[] showTickMarks = {true,true,true,true,true,true,true};
                public Boolean[] showGrid= {null,null,null,null,null,null,null};
                public Boolean[] singleGraphMulti= {false, false, false, false, false, false, false};
                public Boolean[] singleGraphMultiShow= {false, false, false, false, false, false, false};
                public Boolean[] ggplot2GrayTheme= {null,null,null,null,null,null,null};
                
                public String[] pointLabels = {"","","","","","",""};
                public String[] pointLabelTextSize = {"","","","","","",""};
                
                public String[] whichPointsToLabel = {"","","","","","",""};
                public String[] numExtremeValues = {"","","","","","",""};
                public String[] extremenessBasis = {"","","","","","",""};
                public String[] extremenessScrollChoices = {"","","","","","",""};
                public Boolean[] timeOrObsHorizontalAxis = {false, false, false, false, false, false, false};
                public Boolean[] mostExtremeEnabled = {false, false, false, false, false, false, false};
                
                public String[] xAxisTitle = {"","","","","","",""};
                public Boolean[] manualxAxisRange = {null,null,null,null,null,null,null};
                
                public String[] manualxAxisMinText = {"","","","","","",""};
                public String[] manualxAxisMaxText = {"","","","","","",""};
                
                public String[] yAxisTitle = {"","","","","","",""};
                public Boolean[] manualyAxisRange = {null,null,null,null,null,null,null};
                public String[] manualyAxisMinText = {"","","","","","",""};
                public String[] manualyAxisMaxText = {"","","","","","",""};
                
                public String[] legendText1 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] plotType1 = {"","","","","","",""};
                public String[] linePoints1Symbol = {"","","","","","",""};
                public String[] symbolSize1 = {"","","","","","",""};
                public String[] lineType1 = {"","","","","","",""};
                public String[] linePoints1linewidth = {"","","","","","",""};
                public String[] linePoints1color = {"","","","","","",""};
                
                public String[] legendText2 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] plotType2 = {"","","","","","",""};
                public String[] lineType2 = {"","","","","","",""};
                public String[] linePoints2linewidth = {"","","","","","",""};
                public String[] linePoints2color = {"","","","","","",""};
                public Boolean[] confIntvl95 = {null,null,null,null,null,null,null};
                
                public String[] legendText3 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] lineType3 = {"","","","","","",""};
                public String[] linePoints3linewidth = {"","","","","","",""};
                public String[] linePoints3color = {"","","","","","",""};
                
                public String[] legendText4 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] lineType4 = {"","","","","","",""};
                public String[] linePoints4linewidth = {"","","","","","",""};
                public String[] linePoints4color = {"","","","","","",""};
                
                public String[] legendText5 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] lineType5 = {"","","","","","",""};
                public String[] linePoints5linewidth = {"","","","","","",""};
                public String[] linePoints5color = {"","","","","","",""};
                
                public String[] legendText6 ={"_na_","_na_","_na_","_na_","_na_","_na_","_na_"};
                public String[] lineType6 = {"","","","","","",""};
                public String[] linePoints6linewidth = {"","","","","","",""};
                public String[] linePoints6color = {"","","","","","",""};
                
                public String[] xLabels = {"","","","","","",""};
                public String[] yLabels = {"","","","","","",""};

                
	}          
              public void refreshPlotControl6(){
                plotControl.preCommand[6] = "";
                plotControl.postCommand[6] = "";
                plotControl.traditionalGraphicsAvailable[6] = false; 
                plotControl.latticeGraphicsAvailable[6] = false; 
                plotControl.ggplot2GraphicsAvailable[6] = false; 
                plotControl.graphicSystem[6] = "traditional graphics";
                plotControl.traditionalPlotBeginning[6]  = "";
                plotControl.traditionalPlotFormula[6]  = "";
                plotControl.traditionalPlotEnd[6]  = "";
                plotControl.traditionalPlotBeginning2[6]  = "";
                plotControl.traditionalPlotBeginning3[6]  = "";
                plotControl.latticePlotBeginning[6]  = "";
                plotControl.latticePlotFormula[6]  = "";
                plotControl.ggplot2Beginning[6]  = "";
                plotControl.ggplot2PlotFormula[6]  = "";
                plotControl.plotFunction[6]  = "";
                plotControl.plotRelation[6]  = "";
                plotControl.PlotControlsTab[6] = 0;
                
                plotControl.mainTitle[6]  = "";
          
                plotControl.keyPosition[6]  = "";
                plotControl.showTickMarks[6] = null;
                plotControl.showGrid[6] = null;
                plotControl.ggplot2GrayTheme[6] = null;
                
                plotControl.pointLabels[6]  = "";
                plotControl.pointLabelTextSize[6]  = "";
                
                plotControl.whichPointsToLabel[6]  = "";
                plotControl.numExtremeValues[6]  = "";
                plotControl.extremenessBasis[6]  = "";
                plotControl.timeOrObsHorizontalAxis[6] = false;
                
                plotControl.xAxisTitle[6]  = "";
                plotControl.manualxAxisRange[6] = null;
                
                plotControl.manualxAxisMinText[6]  = "";
                plotControl.manualxAxisMaxText[6]  = "";
                
                plotControl.yAxisTitle[6]  = "";
                plotControl.manualyAxisRange[6] = null;
                plotControl.manualyAxisMinText[6]  = "";
                plotControl.manualyAxisMaxText[6]  = "";
                
                plotControl.legendText1[6]  = "_na_";
                plotControl.plotType1[6]  = "";
                plotControl.linePoints1Symbol[6]  = "";
                plotControl.symbolSize1[6]  = "";
                plotControl.lineType1[6]  = "";
                plotControl.linePoints1linewidth[6]  = "";
                plotControl.linePoints1color[6]  = "";
                
                plotControl.legendText2[6]  = "_na_";
                plotControl.plotType2[6]  = "";
                plotControl.lineType2[6]  = "";
                plotControl.linePoints2linewidth[6]  = "";
                plotControl.linePoints2color[6]  = "";
                plotControl.confIntvl95[6]  = null;
                
                plotControl.legendText3[6]  = "_na_";
                plotControl.lineType3[6]  = "";
                plotControl.linePoints3linewidth[6]  = "";
		plotControl.linePoints3color[6]  = "";
                
                plotControl.xLabels[6] = "";
                plotControl.yLabels[6] = "";
	}  
      
          class PlotControl3{
              public class JavaStringArrayTests
{
  private String[] toppings = new String[20];
  // more to the class here ...
}
                public String graphicSystem = "traditional graphics";
                public String traditionalPlotBeginning;
                public String latticePlotBeginning;
                public String ggplot2Beginning;
                public String plotFunction;
                public String plotRelation;
                public Boolean applyClicked = false; 
                public Integer PlotControlsTab = 0;
                public String mainTitle = "";
                public String keyPosition ="bottom";
                public Boolean showTickMarks = true;
                public Boolean showGrid= true;
                
                public String pointLabels = "none";
                public String symbolTextSize = "1";
                
                public String xAxisTitle = "";
                public Boolean manualxAxisRange = false;
                public String manualxAxisMinText = "";
                public String manualxAxisMaxText = "";
                
                public String yAxisTitle = "";
                public Boolean manualyAxisRange = false;
                public String manualyAxisMinText = "";
                public String manualyAxisMaxText = "";
                
                
                public String legendText1 ="";
                public String lineType1 = "";
                public String linePoints1linewidth = "";
                public String linePoints1color = "blue";
                public String linePoints1Symbol = "16: solid circle";
                
                
                public String legendText2 ="";
                public String lineType2 = "";
                public String linePoints2linewidth = "1";
                public String linePoints2color = "red";
                public Boolean confIntvl95 = false;
                
	}          
         class PlotControl5{
                public String graphicSystem = "traditional graphics";
                public String traditionalPlotBeginning;
                public String latticePlotBeginning;
                public String ggplot2Beginning;
                public String plotFunction;
                public String plotRelation;
                public Boolean applyClicked = false;
                public Integer PlotControlsTab = 0;
                public String mainTitle = "";
                public String keyPosition ="bottom";
                public Boolean showTickMarks = true;
                public Boolean showGrid= true;
                
                public String pointLabels = "none";
                public String symbolTextSize = "1";
                
                public String xAxisTitle = "";
                public Boolean manualxAxisRange = false;
                public String manualxAxisMinText = "";
                public String manualxAxisMaxText = "";
                
                public String yAxisTitle = "";
                public Boolean manualyAxisRange = false;
                public String manualyAxisMinText = "";
                public String manualyAxisMaxText = "";
                
                
                public String legendText1 ="";
                public String lineType1 = "";
                public String linePoints1linewidth = "1";
                public String linePoints1color = "blue";
                public String linePoints1Symbol = "16: solid circle";
                
                public String legendText2 ="";
                public String lineType2 = "";
                public String linePoints2linewidth = "1";
                public String linePoints2color = "red";
                public Boolean confIntvl95 = false;
                
	}  
         
         class HanselExport{
                public boolean okay = false;
		public boolean resid = false;
		public boolean sdresid = false;
		public boolean stresid = false;
		public boolean pred =false;
		public boolean linearPred = false;
		public boolean dfbeta = false;
		public boolean dffits=false;
		public boolean covratio=false;
		public boolean hats = false;
		public boolean cooks = false;
                public boolean residForDiff = false;
                public boolean residForLag = false;
                public boolean cointRel = false;
		public boolean keepModel = false;
		public String modelName = "<auto>";
	}
}

