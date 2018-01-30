package com.staples.dashboard.app.sdc.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class ProductVO {

	private String sku;
	@JsonProperty("desc")
	private String skuName;
	private String url;
	private String imageurl;
	private String strategy;
	private String file;
	private String dotcomPrice;
	private String  specialPrice;
	private List<ProductVO> recommendations;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public List<ProductVO> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<ProductVO> recommendations) {
		this.recommendations = recommendations;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getDotcomPrice() {
		return dotcomPrice;
	}
	public void setDotcomPrice(String dotcomPrice) {
		this.dotcomPrice = dotcomPrice;
	}
	public String getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	
	
	
}
