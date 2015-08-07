# DeducerHansel

A copy of the _current development version_ 
of the R-package `DeducerHansel` is contained in this repository. This package currently only has a development version.

The DeducerHansel package is free software; you can
redistribute it and/or modify it under the terms of the GNU 
General Public License version 2 (GPLv2), as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details. A copy of the GPLv2 license is provided in the main DeducerHansel directory. If you have not received it, instructions for seeing it are provided 
when DeducerHansel is loaded into R. Otherwise it is also
available at http://www.gnu.org/licenses/gpl-2.0.html . Otherwise you can write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA.


## Where DeducerHansel works
DeducerHansel, like the Deducer package, is most at home using the JGR console. If you are using a windows operating system, DeducerHansel is also available through "floating menu" through the base R console and RStudio, but this not available in Mac OS-X. DeducerHansel has not been tested in Linux, but since Deducer works there, so should DeducerHansel.

## Installation of Java and R
Java and R need to be installed on your computer before DeducerHansel can be used. 
Java is often required for other software and you may very well already have Java installed on your computer; if not you can follow instructions at the link https://www.oracle.com/java/index.html to install Java. 
Instructions for installing R are given at http://cran.r-project.org/. For a Windows-based system, if you do not have administrator status then installing R onto C:\Program Files\R-3.1.2 (typically the default; note R-3.1.2 is an example—version numbers do change) could create problems, which can be avoided by installing elsewhere, e.g. onto C:\CRAN R\R-3.1.2 instead. These problems can effect in particular the usability of the JGR launcher, discussed later.

## Installation of DeducerHansel

The most convenient way to install the `DeducerhHansel` package through github is by using the package `devtools`:

```R
require(devtools)
install_github('RScottH/DeducerHansel')
```

In case you do not already installed `devtools` you should first run

```R
install.packages('devtools')
```