package com.staples.dashboard.app.sdc.vo;

import java.util.List;

public class CategoryPercentageResponse {
	
	
	private String dotComPercentageTotal;
	private String retailPercentageTotal;
	private String completeTotalCategory;
	private String completeCategoryPercentage;
	
	private List<CategoryDivisionVO> categories;

	public List<CategoryDivisionVO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDivisionVO> categories) {
		this.categories = categories;
	}
	
	


	public String getCompleteTotalCategory() {
		return completeTotalCategory;
	}

	public void setCompleteTotalCategory(String completeTotalCategory) {
		this.completeTotalCategory = completeTotalCategory;
	}

	public String getCompleteCategoryPercentage() {
		return completeCategoryPercentage;
	}

	public void setCompleteCategoryPercentage(String completeCategoryPercentage) {
		this.completeCategoryPercentage = completeCategoryPercentage;
	}

	public String getDotComPercentageTotal() {
		return dotComPercentageTotal;
	}

	public void setDotComPercentageTotal(String dotComPercentageTotal) {
		this.dotComPercentageTotal = dotComPercentageTotal;
	}

	public String getRetailPercentageTotal() {
		return retailPercentageTotal;
	}

	public void setRetailPercentageTotal(String retailPercentageTotal) {
		this.retailPercentageTotal = retailPercentageTotal;
	}

	

}
