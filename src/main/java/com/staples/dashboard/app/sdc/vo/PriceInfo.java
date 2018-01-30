package com.staples.dashboard.app.sdc.vo;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.staples.dashboard.app.vo.BuyMoreSaveMore;
import com.staples.dashboard.app.vo.PriceAdjustment;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PriceInfo {

private String purchaseOptionType;
private String priceType;
private Object catalogSuffixCode;
private Double basePrice;
private Double finalPrice;
private Double finalPriceAfterRebate;
private Double totalSavings;
private Double totalSavingsAfterRebate;
private Integer comparePrice;
private Object priceExpirationDate;
private Object deliveryInfo;
private List<BuyMoreSaveMore> buyMoreSaveMore = new ArrayList<BuyMoreSaveMore>();
private List<PriceAdjustment> priceAdjustment = new ArrayList<PriceAdjustment>();
private Object deprecableFieldsAtPriceLevel;

/**
* 
* @return
* The purchaseOptionType
*/
public String getPurchaseOptionType() {
return purchaseOptionType;
}

/**
* 
* @param purchaseOptionType
* The purchaseOptionType
*/
public void setPurchaseOptionType(String purchaseOptionType) {
this.purchaseOptionType = purchaseOptionType;
}

/**
* 
* @return
* The priceType
*/
public String getPriceType() {
return priceType;
}

/**
* 
* @param priceType
* The priceType
*/
public void setPriceType(String priceType) {
this.priceType = priceType;
}

/**
* 
* @return
* The catalogSuffixCode
*/
public Object getCatalogSuffixCode() {
return catalogSuffixCode;
}

/**
* 
* @param catalogSuffixCode
* The catalogSuffixCode
*/
public void setCatalogSuffixCode(Object catalogSuffixCode) {
this.catalogSuffixCode = catalogSuffixCode;
}


public Double getBasePrice() {
	return basePrice;
}

public void setBasePrice(Double basePrice) {
	this.basePrice = basePrice;
}

/**
* 
* @return
* The finalPrice
*/
public Double getFinalPrice() {
return finalPrice;
}

/**
* 
* @param finalPrice
* The finalPrice
*/
public void setFinalPrice(Double finalPrice) {
this.finalPrice = finalPrice;
}

/**
* 
* @return
* The finalPriceAfterRebate
*/
public Double getFinalPriceAfterRebate() {
return finalPriceAfterRebate;
}

/**
* 
* @param finalPriceAfterRebate
* The finalPriceAfterRebate
*/
public void setFinalPriceAfterRebate(Double finalPriceAfterRebate) {
this.finalPriceAfterRebate = finalPriceAfterRebate;
}

/**
* 
* @return
* The totalSavings
*/
public Double getTotalSavings() {
return totalSavings;
}

/**
* 
* @param totalSavings
* The totalSavings
*/
public void setTotalSavings(Double totalSavings) {
this.totalSavings = totalSavings;
}

/**
* 
* @return
* The totalSavingsAfterRebate
*/
public Double getTotalSavingsAfterRebate() {
return totalSavingsAfterRebate;
}

/**
* 
* @param totalSavingsAfterRebate
* The totalSavingsAfterRebate
*/
public void setTotalSavingsAfterRebate(Double totalSavingsAfterRebate) {
this.totalSavingsAfterRebate = totalSavingsAfterRebate;
}

/**
* 
* @return
* The comparePrice
*/
public Integer getComparePrice() {
return comparePrice;
}

/**
* 
* @param comparePrice
* The comparePrice
*/
public void setComparePrice(Integer comparePrice) {
this.comparePrice = comparePrice;
}

/**
* 
* @return
* The priceExpirationDate
*/
public Object getPriceExpirationDate() {
return priceExpirationDate;
}

/**
* 
* @param priceExpirationDate
* The priceExpirationDate
*/
public void setPriceExpirationDate(Object priceExpirationDate) {
this.priceExpirationDate = priceExpirationDate;
}

/**
* 
* @return
* The deliveryInfo
*/
public Object getDeliveryInfo() {
return deliveryInfo;
}

/**
* 
* @param deliveryInfo
* The deliveryInfo
*/
public void setDeliveryInfo(Object deliveryInfo) {
this.deliveryInfo = deliveryInfo;
}

/**
* 
* @return
* The buyMoreSaveMore
*/
public List<BuyMoreSaveMore> getBuyMoreSaveMore() {
return buyMoreSaveMore;
}

/**
* 
* @param buyMoreSaveMore
* The buyMoreSaveMore
*/
public void setBuyMoreSaveMore(List<BuyMoreSaveMore> buyMoreSaveMore) {
this.buyMoreSaveMore = buyMoreSaveMore;
}

/**
* 
* @return
* The priceAdjustment
*/
public List<PriceAdjustment> getPriceAdjustment() {
return priceAdjustment;
}

/**
* 
* @param priceAdjustment
* The priceAdjustment
*/
public void setPriceAdjustment(List<PriceAdjustment> priceAdjustment) {
this.priceAdjustment = priceAdjustment;
}

/**
* 
* @return
* The deprecableFieldsAtPriceLevel
*/
public Object getDeprecableFieldsAtPriceLevel() {
return deprecableFieldsAtPriceLevel;
}

/**
* 
* @param deprecableFieldsAtPriceLevel
* The deprecableFieldsAtPriceLevel
*/
public void setDeprecableFieldsAtPriceLevel(Object deprecableFieldsAtPriceLevel) {
this.deprecableFieldsAtPriceLevel = deprecableFieldsAtPriceLevel;
}

}