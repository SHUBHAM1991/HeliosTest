package com.staples.dashboard.app.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuyMoreSaveMore {

private Integer minimumQty;
private Integer maximumQty;
private Integer price;

/**
* 
* @return
* The minimumQty
*/
public Integer getMinimumQty() {
return minimumQty;
}

/**
* 
* @param minimumQty
* The minimumQty
*/
public void setMinimumQty(Integer minimumQty) {
this.minimumQty = minimumQty;
}

/**
* 
* @return
* The maximumQty
*/
public Integer getMaximumQty() {
return maximumQty;
}

/**
* 
* @param maximumQty
* The maximumQty
*/
public void setMaximumQty(Integer maximumQty) {
this.maximumQty = maximumQty;
}

/**
* 
* @return
* The price
*/
public Integer getPrice() {
return price;
}

/**
* 
* @param price
* The price
*/
public void setPrice(Integer price) {
this.price = price;
}

}