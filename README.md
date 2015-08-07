# DeducerHansel
Copyright (C) 2015 R. Scott H/\ker,with "/\" replaced with "ac",
Professor of Applied Econometrics and International Economics,
Jönköping University,
Jönköping International Business School,
Jönköping, Sweden.

DeducerHansel is an econometrics-focused GUI plug-in for the
R package Deducer. It adds a menu with additional statistical techniques that are popular in economics and various other fields. Unlike Deducer, DeducerHansel can work with some time series dand spatial data that do not come up in data frame objects. DeducerHansel also has a notably different feel to the way the user interacts with it than what is currently available in Deducer.  

A copy of the _current development version_ 
of the R-package `DeducerHansel` is contained in this repository. This package currently only has a development version.

## Where DeducerHansel works
DeducerHansel, like the Deducer package, is most at home using the JGR console, for which a JGR launcher (discussed later) is useful. If you are using a windows operating system, DeducerHansel is also available through "floating menu" through the base R console and RStudio, but this not available in Mac OS-X. DeducerHansel has not been tested in Linux, but since Deducer works there, so should DeducerHansel.

## Installation of Java and R
Java and R need to be installed on your computer before DeducerHansel can be used. 
Java is often required for other software and you may very well already have Java installed on your computer; if not you can follow instructions at the link https://www.oracle.com/java/index.html to install Java. 
Instructions for installing R are given at http://cran.r-project.org/. For a Windows-based system, if you do not have administrator status then installing R onto C:\Program Files\R-3.1.2 (typically the default; note R-3.1.2 is an example—version numbers do change) could create problems, which can be avoided by installing elsewhere, e.g. onto C:\CRAN R\R-3.1.2 instead. These problems can effect in particular the usability of the JGR launcher, discussed later, or even the installation of DeducerHansel altogether.

## Installation of DeducerHansel

The most convenient way to install the `DeducerhHansel` package through github is by using the package `devtools`,entering the following commands into R:

First in R, if you have not already installed `devtools`, then enter the following R commands in your R console to do so:

```R
install.packages('devtools')
```

After `devtools` has loaded enter the following R commands:

```R
require(devtools)
install_github('RScottH/DeducerHansel')
```

## Using DeducerHansel without the JGR console in Windows

In your R console load the DeducerHansel package.

If you are using the RStudio console, the floating menu should come up automatically when DeducerHansel is loaded, but it may be hidden behind the RStudio console initially.

If you are using the R base console go to the menu 'Hansel Analysis' and click on 'Open Floating Menu' to get options available in DeducerHansel.

## Using DeducerHansel with JGR console, without JGR launcher

In your R console load the DeducerHansel package and enter the command 

```R
JGR()
```

The JGR console will pop up, and you can load DeducerHansel again there. If you close RStudio at this point, JGR will go away, but that is not the case with the R base console.

The nuisance of having to go through another console to get the JGR console everytime you want to use JGR is a nuisance which having the JGR launcher useful will avoid.

##Getting the JGR launcher.
In the next section there is indicated a way to start JGR without the JGR Loader. But that will be cumbersome to do every time you wish to work with JGR. Ideally you should have the JGR launcher. 

Go to the web page for JGR ( http://www.rforge.net/JGR/ ) to get the JGR launcher, which is an executable file with an icon that can be placed on your desktop. In that web page the launcher is found in the “Download” section after the “what’s new section” [Don’t use the “Download/Files” link at the top left of the page, as the downloads seem to be more out-of-date there]. 
(i) windows
Get the latest 32-bit version for windows, which at the time of this document is called “jgr-1 62.exe” (this name may change with later JGR versions). Simply download the file and copy or drag it to your desktop.
(ii) Mac
Get the universal binary (JGR*.dmg) listed which is most appropriate for your system. 

##Loading DeducerHansel in JGR

The first time you open JGR, Deducer and DeducerHansel not be loaded (if you see “Analysis” in the top menu bar, Deducer is loaded). To load Deducer and DeducerHansel, click on Packages & Data> Package Manager and find these packages in the list of packages. On each click on the left box (to load) and on the right box (to automatically load every time JGR is opened) and click on Refresh and then close the Package Manager.

## Information on Deducer
Please check out ( http://www.Deducer.org  ) for more information on Deducer.

## Bug reports 

Users of package `DeducerHansel` are very welcome to report bugs at the github page for the package. To initiate a new bug report or feature request, click on *issue* in the menu found to the right.

## License and warranty information
The DeducerHansel package is free software; you can
redistribute it and/or modify it under the terms of the GNU 
General Public License version 2 (GPLv2), as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details. A copy of the GPLv2 license is provided in the main DeducerHansel directory. If you have not received it, instructions for seeing it are provided 
when DeducerHansel is loaded into R. Otherwise it is also
available at http://www.gnu.org/licenses/gpl-2.0.html . Otherwise you can write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA.


## Attribution

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