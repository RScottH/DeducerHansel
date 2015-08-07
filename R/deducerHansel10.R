
# deducerHansel.R for the DeducerHansel program 
#    Copyright (C) 2015 R. Scott H/\ker,with "/\" replaced with "ac"
#
# DeducerHansel is an econometrics-focused GUI plug-in for the
# R package Deducer
#
# The DeducerHansel program (which includes this document) is free 
# software; you can
# redistribute it and/or modify it under the terms of the GNU 
# General Public License as published by the Free Software 
# Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public #License
#    along with this program; if not, write to the Free Software
#    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  
#    02110-1301  USA
# 
# This R file includes modified code from example.R in the DeducerPlugInExample package(0.2-0 version, dated 2012-03-16)
# by Ian Fellows  (code that also falls under the GPL-2 license)
# and a line of code from commander.R (dated 2013-03-14) in the Rcmdr package (dated 2013-03-14,by John Fox, license: GPL (>=2)),
# with modifications made on 2015-03-10.
#
##############

if("forecast" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("forecast"))}

if("plm" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("plm"))}

if("strucchange" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("strucchange"))}

if("vars" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("vars"))}

if("dynlm" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("dynlm"))}

if("AER" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("AER"))}

if("urca" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("urca"))}

if("vars" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("vars"))}

if("ROCR" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("ROCR"))}

if("wmtsa" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("wmtsa"))}

if("xtable" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("xtable"))}

if("tseries" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("tseries"))}

if("zoo" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("zoo"))}

if("pscl" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("pscl"))}

if("quantmod" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("quantmod"))}


if("rgl" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("rgl"))}

if("mfx" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("mfx"))}

if("xts" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("xts"))}

if("latticeExtra" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("latticeExtra"))}

if("mFilter" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("mFilter"))}

if("XLConnect" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("XLConnect"))}

#For spatial methods
if("rgdal" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("rgdal"))}

if("sp" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("sp"))}

if("spdep" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("spdep"))}

if("splm" %in% rownames(installed.packages()) == FALSE)
{install.packages(c("splm"))}
#End for Spatial methods



	
.onLoad <- function(libname, pkgname) { 
	
	.jpackage(pkgname,lib.loc=libname)
	  

#Note: next 10 lines added to make sure the program runs on Mac
	#if deducer gui is not running, do minimal load
	.deducerLoaded <<- try(.deducer == .jnull(),silent=TRUE)
	if(inherits(.deducerLoaded,"try-error") || .deducerLoaded)
		return(NULL)
          
	
	#loads needed .jar files
	.jpackage(pkgname,lib.loc=libname)
	.jpackage("Deducer")
	.jpackage("Hansel")
###

Deducer::deducer.addMenu("Hansel Analysis")
Deducer::deducer.addMenuItem("Open floating menu call",,
                             "if(!.jfield('org/rosuda/deducer/Deducer','Z','started')){
                               .jcall(.deducer,,'startNoJGR')} \n
                              .hansel.floatMenuClass <-J('hansel.DeducerFloatingMenu') \n
                              .hansel.floatMenu <- .jnew(.hansel.floatMenuClass) \n
                              .hansel.floatMenu$runit() \n
                              rm(.hansel.floatMenuClass)",
                             "Hansel Analysis") 

	
if(.windowsGUI){
      
   #  RStudio <- nzchar(Sys.getenv("RSTUDIO_USER_IDENTITY")) # This line is based on code from commander.R in Rcmdr (by John Fox)	
      
      if (!(nzchar(Sys.getenv("RSTUDIO_USER_IDENTITY")))) {
		winMenuAdd("Hansel Analysis")

       winMenuAddItem("Hansel Analysis", "Open floating menu", "deducer('Open floating menu call')")

        } else {  #dealing with RStudio
         # The following command limits the CPU process to 1, since otherwise (at least in Windows)
         # there will typically be soon be a "stack imbalance" problem. According to a discussion on
         # Rstudio support discussion from December 30 2013 (involving Ian Pilvainen; see
         # https://support.rstudio.com/hc/communities/public/questions/200666447--Stack-Imbalance-from-R-when-using-Deducer-?locale=en-us )
         # "The Deducer developer has explained this as a 'threadsafe' problem, 
         # that Deducer and RStudio are simultaneously accessing R."
         # A discussed 'workaround' solution in that discussion was to run on one CPU core.
         # The command itself is based on a stackoverflow discussion on
         # "R parallel CPU affinity on Windows" from February 25, 2013 (involving ianmcook; see
         # http://stackoverflow.com/questions/13032978/r-parallel-cpu-affinity-on-windows ).
         # The 3 lines of code there were collapsed to one to deal with the case where only 1 CPU core is 
         # allowed to run.
         #
                        shell(paste("PowerShell -Command \"& {(Get-Process -id ",Sys.getpid(),").ProcessorAffinity = ",1,"}\"",sep=""))
                      #
                      # Since RStudio does not have a plug-in for a menu, the floating menu is called immediately through the command below.
           .ddcr<<- function(){
	   if(!.jfield("org/rosuda/deducer/Deducer","Z","started")){
               .jcall(.deducer,,"startNoJGR")}
	    floatMenuClass <-J('hansel.DeducerFloatingMenu')
	    floatMenu <- .jnew(floatMenuClass)
	    floatMenu$runit()
	    return(floatMenu)
                      }

                       .ddcr()

                     # The next line is to have script-activated plots go to the RStudio plot panel by default.
                       options(device="RStudioGD") 
          

     }

   } else {  #not windowsGUI (or RStudio), i.e. is JGR 	

   HanselMenu <- J('hansel.Hansel')
   myHanselMenu <- new(HanselMenu)
}

if(exists("gretldata")){
   if (!(class(gretldata)=="data.frame")){
       print("hello")
	gretldata.zoo <- as.zoo(gretldata)
	TimeSeriesList.gretldata <- attributes(gretldata.zoo)$dimnames[[2]]

	var <- attributes(gretldata.zoo)$dimnames[[2]][1]
	cmd <- paste("Time.Series.gretldata <<- data.frame(",var,"= \"",var,"\"")
	   for (var in TimeSeriesList.gretldata[-1])
    	       cmd <- paste(cmd, ",",var,"= \"",var,"\"")
	cmd <- paste(cmd,")")
	execute(cmd)



	cmd <-"gretldata.zoo <<- as.zoo(gretldata)"
	cmd <- paste(cmd,"\n TimeSeriesList.gretldata <<- attributes(gretldata.zoo)$dimnames[[2]]")
execute(cmd)
	for (var in TimeSeriesList.gretldata){
    	      cmd <- paste("\n",var,"<-gretldata.zoo$",var)
	      execute(cmd)
           }
       execute(cmd)
  }
}

# sample data for testing
data("Anscombe",package="car") # for ols
data(UKpppuip, package="urca") # for time series
data("Grunfeld", package = "plm") # for panel 
#data(SwissLabor, package = "AER") #For logit & probit examples 
#data(Affairs, package = "AER")#For tobit example
#data("oldcol",package="spdep") #For cross-sectional spatial examples

options(device="JavaGD") 

.CopyRight1 <- "\n\nDeducerHansel version 0.4
Copyright © 2015 Raymond Scott Ha"

.CopyRight2 <- "cker
Professor in Applied Econometrics and International Economics,
Jönköping University
Jönköping International Business School

This package comes with ABSOLUTELY NO WARRANTY; for details
click on the menu item 'Hansel Analysis>About DeducerHansel>Warranty'.
This is free software, and you are welcome to redistribute it under certain
conditions covered by the GNU General Public License version 2 (GPLv2) license;
for details click on the menu item 'Hansel Analysis>About DeducerHansel>License'.

Note: To make the Hansel Analysis menu item initially visible in JGR, 
you may need to hover your mouse over the JGR menu items.

Welcome to DeducerHansel!"

writeLines(paste(.CopyRight1,.CopyRight2,sep=""))

rm(.CopyRight1)
rm(.CopyRight2)

}


