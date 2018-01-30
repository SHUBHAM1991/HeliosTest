package com.staples.dashboard.app.sdc.vo;

import java.util.Map;

public class FiscalCategoryCalResponse {
	
	private String totalSum;
	
	private Map<String,String> verticalCategoryTotal;
	
	
	public Map<String, String> getVerticalCategoryTotal() {
		return verticalCategoryTotal;
	}

	public void setVerticalCategoryTotal(Map<String, String> verticalCategoryTotal) {
		this.verticalCategoryTotal = verticalCategoryTotal;
	}

	public String getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}

	Map<String,CategoryTypeCalresponse> fiscalMonthCategories;

	public Map<String, CategoryTypeCalresponse> getFiscalMonthCategories() {
		return fiscalMonthCategories;
	}

	public void setFiscalMonthCategories(Map<String, CategoryTypeCalresponse> fiscalMonthCategories) {
		this.fiscalMonthCategories = fiscalMonthCategories;
	}

}
