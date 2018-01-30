package com.staples.dashboard.app.vo;

public class OnlineRetailReorderRecommendationVO {
	
	private String skuNumber;
	private String itemDescription;
	private String thumbnail;
	private String selectPrice;
	private String premiumPrice;
	private String catenTryId;
	private String saComPrice;
	private String nephosStatusCode;

	public String getCatenTryId() {
		return catenTryId;
	}

	public void setCatenTryId(String catenTryId) {
		this.catenTryId = catenTryId;
	}

	public String getSkuNumber() {
		return skuNumber;
	}
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSelectPrice() {
		return selectPrice;
	}
	public void setSelectPrice(String selectPrice) {
		this.selectPrice = selectPrice;
	}
	public String getPremiumPrice() {
		return premiumPrice;
	}
	public void setPremiumPrice(String premiumPrice) {
		this.premiumPrice = premiumPrice;
	}

	public String getSaComPrice() {
		return saComPrice;
	}

	public void setSaComPrice(String saComPrice) {
		this.saComPrice = saComPrice;
	}

	/**
	 * @return the nephosStatusCode
	 */
	public String getNephosStatusCode() {
		return nephosStatusCode;
	}

	/**
	 * @param nephosStatusCode the nephosStatusCode to set
	 */
	public void setNephosStatusCode(String nephosStatusCode) {
		this.nephosStatusCode = nephosStatusCode;
	}
	
	
}
