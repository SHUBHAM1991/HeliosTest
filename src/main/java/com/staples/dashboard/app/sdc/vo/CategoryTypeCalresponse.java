package com.staples.dashboard.app.sdc.vo;

import java.util.List;
import java.util.Map;

public class CategoryTypeCalresponse {
	
	private String  fiscalMonthTotal;
	private String  dotComMonthTotal;
	private String  retailMonthTotal;
	
	private Map<String,String> completeCategoryTotal;
	private List<CategoryDivisionVO> categories;
	private List<CategoryDivisionVO> retailCategories;
	
	
	
	public Map<String, String> getCompleteCategoryTotal() {
		return completeCategoryTotal;
	}

	public void setCompleteCategoryTotal(Map<String, String> compleCategoryTotal) {
		this.completeCategoryTotal = compleCategoryTotal;
	}

	public String getDotComMonthTotal() {
		return dotComMonthTotal;
	}

	public void setDotComMonthTotal(String dotComMonthTotal) {
		this.dotComMonthTotal = dotComMonthTotal;
	}

	public String getRetailMonthTotal() {
		return retailMonthTotal;
	}

	public void setRetailMonthTotal(String retailMonthTotal) {
		this.retailMonthTotal = retailMonthTotal;
	}

	public List<CategoryDivisionVO> getRetailCategories() {
		return retailCategories;
	}

	public void setRetailCategories(List<CategoryDivisionVO> retailCategories) {
		this.retailCategories = retailCategories;
	}

	public String getFiscalMonthTotal() {
		return fiscalMonthTotal;
	}

	public void setFiscalMonthTotal(String fiscalMonthTotal) {
		this.fiscalMonthTotal = fiscalMonthTotal;
	}

	public List<CategoryDivisionVO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDivisionVO> categories) {
		this.categories = categories;
	}
	
	
	

}
