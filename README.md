# DeducerHansel
DeducerHansel is an econometrics-focused GUI plug-in package for the R-package Deducer. It adds a menu with additional statistical techniques that are popular in economics and various other fields. Unlike the base Deducer package, DeducerHansel can work with some time series and spatial data that do not come up in data frame objects. DeducerHansel also has a notably different feel to the way the user interacts with it than what is currently available in the base Deducer package.  

For a **list of features** please see the [associated web page for this repository](http://rscotth.github.io/DeducerHansel/).

A copy of the _current development version_ 
of the R-package `DeducerHansel` is contained in this repository. This package currently only has a development version.

## Where DeducerHansel works
DeducerHansel, like the Deducer package, is most at home using the JGR console, for which a JGR launcher (discussed later) is useful. If you are using a Windows operating system, DeducerHansel is also available in a "floating menu" through the standard RGui console available in base R and through RStudio, but this floating menu is not available in Mac OS-X. DeducerHansel has not been tested in Linux, but since Deducer works there, DeducerHansel is expected to do so also.

## Installation of Java and R
Java and R need to be installed on your computer before DeducerHansel can be used. 

Java is often required for other software and you may very well already have Java installed on your computer; if not you can follow instructions at the [Java web page] (https://www.oracle.com/java/index.html) to install Java. 

Instructions for installing R are given at the [CRAN web page](http://cran.r-project.org/). For a Windows-based system, if you do not have administrator status then installing R onto C:\Program Files\R-3.1.2 (typically the default; note R-3.1.2 is an example—version numbers do change) could create problems, which can be avoided by installing elsewhere, e.g. onto C:\CRAN R\R-3.1.2 instead. These problems can effect in particular the usability of the JGR launcher, discussed later, or even the installation of DeducerHansel altogether.

## Installation of DeducerHansel

The most convenient way to install the `DeducerHansel` package through github is by using the package `devtools`, entering the following commands into R:

If you have not already installed `devtools`, then enter the following R command in your R console to do so:

```R
install.packages('devtools')
```
For some parts of this installation, please be patient; notably the installation of the magrittr package, as part of this installation, can take a while. 

After `devtools` has been installed enter the following R commands:

```R
require(devtools)
install_github('RScottH/DeducerHansel')
```

Don't worry if there is a warning about Rtools being required to build R packages. For some parts of this installation also, please be patient; notably the installation of the zoo package, as part of this installation, can take a while. 

## Using DeducerHansel without the JGR console in Windows

In your R console load the DeducerHansel package

If you are using the RStudio console, the floating menu should come up automatically when DeducerHansel is loaded, but it may be hidden behind the RStudio console initially.

If you are using the standard RGui console go to the menu 'Hansel' and click on 'Open Floating Menu' to get the floating menu.

## Using DeducerHansel with JGR console, without JGR launcher

In your R console load the DeducerHansel package and enter the command 

```R
JGR()
```

The JGR console will pop up, and you can load DeducerHansel again there. If you close RStudio at this point, JGR will go away, but that is not the case with the R base console.

The nuisance of having to go through another console to get the JGR console everytime you want to use JGR is a nuisance which having the JGR launcher useful will avoid.

##Getting the JGR launcher.
If you wish to work regularly with the JGR console, then ideally you should have the JGR launcher. 

Go to the [JGR web page]( http://www.rforge.net/JGR/ ) to get the JGR launcher, which is an executable file with an icon that can be placed on your desktop. In that web page the launcher is found in the “Download” section after the “what’s new section” [Don’t use the “Download/Files” link at the top left of the page, as the downloads seem to be more out-of-date there]. 

(i) Windows
Get the latest 32-bit version for windows, which at the time of this document is called “jgr-1 62.exe” (this name may change with later JGR versions). Simply download the file and copy or drag it to your desktop and open it (you may need to run it as un adminstrator). **Note: I have had mixed success in making this work on Windows, particularly on my own school's computers. My best guess now is that it hangs on some security protection. If you can't make it work, don't despair--there is still the floating-menu version available off of the RGui console or Rstudio.** 

(ii) Mac OS-X
Get the universal binary (JGR*.dmg) listed which is most appropriate for your system. 

##Loading DeducerHansel in JGR

The first time you open JGR, Deducer and DeducerHansel will not be loaded (if you see “Analysis” in the top menu bar, Deducer is loaded). To load Deducer and DeducerHansel, click on Packages & Data> Package Manager and find these packages in the list of packages. On each click on the left box (to load) and on the right box (to automatically load every time JGR is opened) and click on Refresh and then close the Package Manager.

##The Reset button - An important tip for using dataframes

If a dataframe is added to your workspace, then it may not show up initially when you look at available dataframes in the variable selector. Next to the scroller on available dataframes is a reset button, which you can double-click to bring up the latest dataframes and clear variable selections. 

This is in contrast to what occurs in the base Deducer package, in which avaialable dataframes for the variable selector are updated automatically. That is not done in DeducerHansel so that choices of variables by the user would be remembered more frequently--so as a user you can, for example, change from a binary logit specification to a binary probit one without re-selecting your variables. But it is at the cost of you needing to manually reset the available dataframes when you adds one to the workspace (one exception-if the variable selector has not been previously opened then it will look for available dataframes automatically).


## Information on Deducer
Please check out the [Deducer web page]( http://www.Deducer.org  ) for more information on Deducer.

## Bug reports 

Users of package `DeducerHansel` are very welcome to report bugs at the github page for the package. To initiate a new bug report or feature request, click on *issue* in the menu found to the right.

## Copyright, license and warranty information

The DeducerHansel package has the following copyright:

Copyright (C) 2015 R. Scott H/\ker,with "/\" replaced with "ac",
Professor of Applied Econometrics and International Economics,
Jönköping University,
Jönköping International Business School,
Jönköping, Sweden.

The DeducerHansel package is free software; you can
redistribute it and/or modify it under the terms of the GNU 
General Public License version 2 (GPLv2), as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details. A copy of the GPLv2 license is provided in the main DeducerHansel directory. If you have not received it, instructions for seeing it are provided 
when DeducerHansel is loaded into R. Otherwise it is also
available at http://www.gnu.org/licenses/gpl-2.0.html, or you can write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA.

## Contributors
I thank Abdulnasser Hatemi-J for his ideas early in the development of this package.

I thank Peter Karlsson, Dawoud Asalya, and Saher Malik in their testing of earlier versions of this package.

## Attribution for earlier source code

The following packages provided some source code that was 
used in the DeducerHansel package. All of these packages are covered by GPLv2. 

Java source code:
Deducer (0.7-6 version, dated 2012-12-05),
     by Ian Fellows

DeducerPlugInExample (0.2-0 version, dated 2012-03-16),
     by Ian Fellows

DeducerSpatial package (0.7 version, dated 2013-04-12),
     by Ian Fellows and Alex Rickett with contributions 
             from Neal Fultz.             

JGR package (1.7 -18 version, downloaded 2015-07-15),
     by Markus Helbig, Simon Urbanek and Ian Fellows.

R souce code:
DeducerPlugInExample (0.2-0 version, dated 2012-03-16),
      by Ian Fellows

RCmdr (1.6-6 version, dated 2013-03-15) 
       by John Fox and Milan Bouchet-Valat, with
       various contributors
       (one line of R code, but an important one!)

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-66123944-2', 'auto');
  ga('send', 'pageview');

</script>