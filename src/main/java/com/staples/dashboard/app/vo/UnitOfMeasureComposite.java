package com.staples.dashboard.app.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UnitOfMeasureComposite {

private Object unitOfMeasureFactor;
private Object unitOfMeasureQty;
private String unitOfMeasure;

/**
* 
* @return
* The unitOfMeasureFactor
*/
public Object getUnitOfMeasureFactor() {
return unitOfMeasureFactor;
}

/**
* 
* @param unitOfMeasureFactor
* The unitOfMeasureFactor
*/
public void setUnitOfMeasureFactor(Object unitOfMeasureFactor) {
this.unitOfMeasureFactor = unitOfMeasureFactor;
}

/**
* 
* @return
* The unitOfMeasureQty
*/
public Object getUnitOfMeasureQty() {
return unitOfMeasureQty;
}

/**
* 
* @param unitOfMeasureQty
* The unitOfMeasureQty
*/
public void setUnitOfMeasureQty(Object unitOfMeasureQty) {
this.unitOfMeasureQty = unitOfMeasureQty;
}

/**
* 
* @return
* The unitOfMeasure
*/
public String getUnitOfMeasure() {
return unitOfMeasure;
}

/**
* 
* @param unitOfMeasure
* The unitOfMeasure
*/
public void setUnitOfMeasure(String unitOfMeasure) {
this.unitOfMeasure = unitOfMeasure;
}

}

