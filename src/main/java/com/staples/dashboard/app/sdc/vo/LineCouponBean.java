package com.staples.dashboard.app.sdc.vo;

public class LineCouponBean {
	
	private String salesTranId;
	private String salesTranDetailId;
	private String couponType;
	private String couponNumber;
	private String couponTotalAmount;
	private String couponLineAmount;
	public String getCouponLineAmount() {
		return couponLineAmount;
	}
	public void setCouponLineAmount(String couponLineAmount) {
		this.couponLineAmount = couponLineAmount;
	}
	private String couponOfferCode;
	private String couponDescription;
	public String getSalesTranId() {
		return salesTranId;
	}
	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}
	public String getSalesTranDetailId() {
		return salesTranDetailId;
	}
	public void setSalesTranDetailId(String salesTranDetailId) {
		this.salesTranDetailId = salesTranDetailId;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	public String getCouponTotalAmount() {
		return couponTotalAmount;
	}
	public void setCouponTotalAmount(String couponTotalAmount) {
		this.couponTotalAmount = couponTotalAmount;
	}
	public String getCouponOfferCode() {
		return couponOfferCode;
	}
	public void setCouponOfferCode(String couponOfferCode) {
		this.couponOfferCode = couponOfferCode;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	
	

}
