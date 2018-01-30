package com.staples.dashboard.app.sdc.vo;

import java.util.List;

public class OrderItem {

	private String transId;
	
	private String transLineItemId;
	
	private String productDeptNumber;
	
	private String productClassNumber;
	
	private String productClass_Id;
	
	private String division;
	
	private String productSubClassNumber;
	
	private String productSKU;
	
	private String productName;
	
	

	private String lineTotalAmount;
	
	private String transQuantity;
	
	private String contactName;
	
	private String productDescription;
	
	private String itemStatusDescription;
	
	private String itemStatusDescriptionCode;
	
	
	
	private List<LineDiscountBean> lineDiscount;

	private List<LineCouponBean> couponItem;
	
	private String masterSalesTranId;
	
	private String extendedPrice;
	private String tranLineStatusId;
	
	
	public String getExtendedPrice() {
		return extendedPrice;
	}

	public void setExtendedPrice(String extendedPrice) {
		this.extendedPrice = extendedPrice;
	}

	public String getTranLineStatusId() {
		return tranLineStatusId;
	}

	public void setTranLineStatusId(String tranLineStatusId) {
		this.tranLineStatusId = tranLineStatusId;
	}

	public String getProductClass_Id() {
		return productClass_Id;
	}

	public void setProductClass_Id(String productClass_Id) {
		this.productClass_Id = productClass_Id;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getMasterSalesTranId() {
		return masterSalesTranId;
	}

	public void setMasterSalesTranId(String masterSalesTranId) {
		this.masterSalesTranId = masterSalesTranId;
	}

	public String getItemStatusDescription() {
		return itemStatusDescription;
	}

	public void setItemStatusDescription(String itemStatusDescription) {
		this.itemStatusDescription = itemStatusDescription;
	}

	public String getItemStatusDescriptionCode() {
		return itemStatusDescriptionCode;
	}

	public void setItemStatusDescriptionCode(String itemStatusDescriptionCode) {
		this.itemStatusDescriptionCode = itemStatusDescriptionCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public List<LineDiscountBean> getLineDiscount() {
		return lineDiscount;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransLineItemId() {
		return transLineItemId;
	}

	public void setTransLineItemId(String transLineItemId) {
		this.transLineItemId = transLineItemId;
	}

	public String getProductDeptNumber() {
		return productDeptNumber;
	}

	public void setProductDeptNumber(String productDeptNumber) {
		this.productDeptNumber = productDeptNumber;
	}

	public String getProductClassNumber() {
		return productClassNumber;
	}

	public void setProductClassNumber(String productClassNumber) {
		this.productClassNumber = productClassNumber;
	}

	public String getProductSubClassNumber() {
		return productSubClassNumber;
	}

	public void setProductSubClassNumber(String productSubClassNumber) {
		this.productSubClassNumber = productSubClassNumber;
	}

	public String getProductSKU() {
		return productSKU;
	}

	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}

	public String getLineTotalAmount() {
		return lineTotalAmount;
	}

	public void setLineTotalAmount(String lineTotalAmount) {
		this.lineTotalAmount = lineTotalAmount;
	}

	public String getTransQuantity() {
		return transQuantity;
	}

	public void setTransQuantity(String transQuantity) {
		this.transQuantity = transQuantity;
	}


	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<LineCouponBean> getCouponItem() {
		return couponItem;
	}

	public void setCouponItem(List<LineCouponBean> couponItem) {
		this.couponItem = couponItem;
	}

	public void setLineDiscount(List<LineDiscountBean> lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	
}
