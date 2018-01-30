package com.staples.dashboard.app.vo;

public class Adjustment {

private Integer adjustmentPrice;
private Double interimTotal;
private Boolean applyToFinalPrice;

/**
* 
* @return
* The adjustmentPrice
*/
public Integer getAdjustmentPrice() {
return adjustmentPrice;
}

/**
* 
* @param adjustmentPrice
* The adjustmentPrice
*/
public void setAdjustmentPrice(Integer adjustmentPrice) {
this.adjustmentPrice = adjustmentPrice;
}

/**
* 
* @return
* The interimTotal
*/
public Object getInterimTotal() {
return interimTotal;
}

/**
* 
* @param interimTotal
* The interimTotal
*/
public void setInterimTotal(Double interimTotal) {
this.interimTotal = interimTotal;
}

/**
* 
* @return
* The applyToFinalPrice
*/
public Boolean getApplyToFinalPrice() {
return applyToFinalPrice;
}

/**
* 
* @param applyToFinalPrice
* The applyToFinalPrice
*/
public void setApplyToFinalPrice(Boolean applyToFinalPrice) {
this.applyToFinalPrice = applyToFinalPrice;
}

}