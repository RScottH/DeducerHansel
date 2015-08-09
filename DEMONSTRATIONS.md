# DeducerHansel DEMONSTRATIONS
Below is a list of some standard datasets used for demonstrating how DeducerHansel works.

Keep in mind that if a data frame is added to your workspace, then it may not show up initially when you look at available data frames in the variable selector.
Next to the scroller on available data frames is a reset button, which you can use to bring up the latest data frames and clear variable selections.


## For Ordinary least squares
I have used the car package's Anscombe dataset frequently in developing in considering how OLS works in DeducerHansel. The following command can be used to download it.

```R
data('Anscombe',package='car')
```

## For logit and probit
I have not yet found a good dataset among those in the installed packages yet for considering binary logit and binary probit. To test these methods I used the Anscombe dataset, noted above with OLS, and split the income variable based on whether it is above the median or not, and used that as the response variable. 
To split it I select highlight it in the variable selector, and in the scroller next to "New Variable" below the variable selector, choose "split", which makes the split variable selectable.

## For tobit models
A classic dataset for testing logit is the Affairs dataset found in the AER package, but I don't think it is all that great for demonstrating logit. Instead I create an artificial dataset for the tobit testing based on the Wages1 dataset in the Ecdata package, by providing a lower limit of 10 for the wage variable. The redefined wage variable can then be used as the response variable. If the Ecdat package is not yet installed, the first command to give is

```R
install.packages('Ecdat')
```  

Subsequently to get the data the following commands are entered.

```R
data('Wages1',package='Ecdat')
Wages1$wage[Wages1$wage<10]=10
```

## For time series models using data originally from data frames
I have used the urca package's UKpppuip dataset to consider how the time series methods work in DeducerHansel. In this case it is easiest to go to 

Hansel Analysis>Data Retrieval>Load sample dataset

and load it from that page.

Many of the time series techniques are available without dating the observations. However more meaningful graphs and using lags and differences require some dating. To put on some dates, go to 

Hansel Analysis>Data Tools>data.frame -> time series mirror

Here one can enter 1971:1 for the starting date and 4 for the frequency (as it is quarerly data). 

## For time series models using data originally in other formats
Here I typically download data from Yahoo! using 

Hansel Analysis>Data Retrieval>Download data from Yahoo!

and then choose [DIA] Dow Jones Industrial average using a "partial mirror". That results two objects being added to the workspace: DIA which is an xts object, and DIA__ which is a data frame that contains just the names of the variables in DIA, so they can be used in the variable selector.

## For panel models
Here I typically use the plm package's Grunfeld dataset. The following command can be used to download it.

```R
data('Grunfeld',package='plm')
```

## For spatial cross-sectional models - no map
Here the spdep package's oldcol dataset can be used, with the following command to download it 

```R
data('oldcol',package='spdep')
```

The data should be ready for spatial regressions after making the connection between COL.OLD and COL.nb in
   Data Tools> Spatial File Associations (to see the change, you will need to "Confirm changes to primary associations" a couple of times).   


## For spatial cross-sectional models - with map
For this demonstration I download onto my local disk the data files sids.dbf, sids.shp, sids.shx from the [source material for the maptools package ( https://cran.r-project.org/web/packages/maptools/index.html ), found under maptools\inst\shapes\. Subsequently I use

Hansel Analysis>Data Retrieval>Open data 

to open it. 


## For spatial panel models
Here Ecdat package's Produc dataset and the splm package's usaww dataset can be used together. 
If the Ecdat package is not yet installed, the first command to give is

```R
install.packages('Ecdat')
```  

Subsequently to get the data the following commands are entered.

```R
data(Produc, package = 'Ecdat')
data(usaww, package = 'splm')
```

The data should be ready for spatial panel regressions after making the connection between Produc and usaww in

Data Tools> Spatial File Associations (to see the change, you will need to "Confirm changes to primary associations" a couple of times). 
