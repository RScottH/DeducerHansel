# DeducerHansel

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