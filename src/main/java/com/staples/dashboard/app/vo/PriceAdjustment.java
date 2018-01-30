package com.staples.dashboard.app.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PriceAdjustment {

private String adjustmentType;
private String adjustmentMessageDescription;
private Adjustment adjustment;
private Rebate rebate;

/**
* 
* @return
* The adjustmentType
*/
public String getAdjustmentType() {
return adjustmentType;
}

/**
* 
* @param adjustmentType
* The adjustmentType
*/
public void setAdjustmentType(String adjustmentType) {
this.adjustmentType = adjustmentType;
}

/**
* 
* @return
* The adjustmentMessageDescription
*/
public String getAdjustmentMessageDescription() {
return adjustmentMessageDescription;
}

/**
* 
* @param adjustmentMessageDescription
* The adjustmentMessageDescription
*/
public void setAdjustmentMessageDescription(String adjustmentMessageDescription) {
this.adjustmentMessageDescription = adjustmentMessageDescription;
}

/**
* 
* @return
* The adjustment
*/
public Adjustment getAdjustment() {
return adjustment;
}

/**
* 
* @param adjustment
* The adjustment
*/
public void setAdjustment(Adjustment adjustment) {
this.adjustment = adjustment;
}

/**
* 
* @return
* The rebate
*/
public Rebate getRebate() {
return rebate;
}

/**
* 
* @param rebate
* The rebate
*/
public void setRebate(Rebate rebate) {
this.rebate = rebate;
}

}
