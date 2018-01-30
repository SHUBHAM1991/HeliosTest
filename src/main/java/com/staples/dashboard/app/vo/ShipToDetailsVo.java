package com.staples.dashboard.app.vo;

import java.util.List;

public class ShipToDetailsVo {
private String orderNumber;
private String orderDate;
private String orderAmount;
private String minOrderAmount;
private String orderContact;
private String skuList;
private String descriptionList;
private List<String> savingCategory;
private List<String> savingAmount;
	public String getOrderContact() {
	return orderContact;
}

public void setOrderContact(String orderContact) {
	this.orderContact = orderContact;
}
	//List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO;

	/*public List<PurchaseHeliosDetailsVO> getPurchaseRwdsDetailsVO() {
		return purchaseRwdsDetailsVO;
	}

	public void setPurchaseRwdsDetailsVO(
			List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO) {
		this.purchaseRwdsDetailsVO = purchaseRwdsDetailsVO;
	}*/
public String getOrderNumber() {
	return orderNumber;
}
public void setOrderNumber(String orderNumber) {
	this.orderNumber = orderNumber;
}
public String getOrderDate() {
	return orderDate;
}
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
public String getOrderAmount() {
	return orderAmount;
}
public void setOrderAmount(String orderAmount) {
	this.orderAmount = orderAmount;
}

/**
 * @return the minOrderAmount
 */
public String getMinOrderAmount() {
	return minOrderAmount;
}

/**
 * @param minOrderAmount the minOrderAmount to set
 */
public void setMinOrderAmount(String minOrderAmount) {
	this.minOrderAmount = minOrderAmount;
}

public String getSkuList() {
	return skuList;
}

public void setSkuList(String skuList) {
	this.skuList = skuList;
}

public String getDescriptionList() {
	return descriptionList;
}

public void setDescriptionList(String descriptionList) {
	this.descriptionList = descriptionList;
}

public List<String> getSavingAmount() {
	return savingAmount;
}

public void setSavingAmount(List<String> savingAmount) {
	this.savingAmount = savingAmount;
}

public List<String> getSavingCategory() {
	return savingCategory;
}

public void setSavingCategory(List<String> savingCategory) {
	this.savingCategory = savingCategory;
}
}
