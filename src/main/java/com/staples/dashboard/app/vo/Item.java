package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

private String itemId;
private Integer quantity;
private UnitOfMeasureComposite unitOfMeasureComposite;
private List<PriceInfo> priceInfo = new ArrayList<PriceInfo>();
private List<PromotionMessage> promotionMessages = new ArrayList<PromotionMessage>();

/**
* 
* @return
* The itemId
*/
public String getItemId() {
return itemId;
}

/**
* 
* @param itemId
* The itemId
*/
public void setItemId(String itemId) {
this.itemId = itemId;
}

/**
* 
* @return
* The quantity
*/
public Integer getQuantity() {
return quantity;
}

/**
* 
* @param quantity
* The quantity
*/
public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

/**
* 
* @return
* The unitOfMeasureComposite
*/
public UnitOfMeasureComposite getUnitOfMeasureComposite() {
return unitOfMeasureComposite;
}

/**
* 
* @param unitOfMeasureComposite
* The unitOfMeasureComposite
*/
public void setUnitOfMeasureComposite(UnitOfMeasureComposite unitOfMeasureComposite) {
this.unitOfMeasureComposite = unitOfMeasureComposite;
}

/**
* 
* @return
* The priceInfo
*/
public List<PriceInfo> getPriceInfo() {
return priceInfo;
}

/**
* 
* @param priceInfo
* The priceInfo
*/
public void setPriceInfo(List<PriceInfo> priceInfo) {
this.priceInfo = priceInfo;
}

/**
* 
* @return
* The promotionMessages
*/
public List<PromotionMessage> getPromotionMessages() {
return promotionMessages;
}

/**
* 
* @param promotionMessages
* The promotionMessages
*/
public void setPromotionMessages(List<PromotionMessage> promotionMessages) {
this.promotionMessages = promotionMessages;
}

}
