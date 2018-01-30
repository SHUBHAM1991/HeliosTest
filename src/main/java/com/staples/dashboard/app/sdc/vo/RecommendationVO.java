package com.staples.dashboard.app.sdc.vo;

import java.util.List;
import java.util.Map;

public class RecommendationVO {

	private String scheme;
	private Map<Integer, Map<String, List<ProductVO>>> products;
	
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public Map<Integer, Map<String, List<ProductVO>>> getProducts() {
		return products;
	}
	public void setProducts(Map<Integer, Map<String, List<ProductVO>>> products) {
		this.products = products;
	}
}
