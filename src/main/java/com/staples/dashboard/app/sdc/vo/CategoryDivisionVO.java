package com.staples.dashboard.app.sdc.vo;

public class CategoryDivisionVO {
	
	private String division;
	private String name;
	private String classNo;
	private String productClassNo;
	private String productCat_CD;
	
	private String categoryTotal;
	private String categoryPercentage;
	
	public String getDotComCategoryTotal() {
		return dotComCategoryTotal;
	}
	public void setDotComCategoryTotal(String dotComCategoryTotal) {
		this.dotComCategoryTotal = dotComCategoryTotal;
	}
	public String getDotComCategoryPercentage() {
		return dotComCategoryPercentage;
	}
	public void setDotComCategoryPercentage(String dotComCategoryPercentage) {
		this.dotComCategoryPercentage = dotComCategoryPercentage;
	}
	public String getRetailCategoryTotal() {
		return retailCategoryTotal;
	}
	public void setRetailCategoryTotal(String retailCategoryTotal) {
		this.retailCategoryTotal = retailCategoryTotal;
	}
	public String getRetailCategoryPercentage() {
		return retailCategoryPercentage;
	}
	public void setRetailCategoryPercentage(String retailCategoryPercentage) {
		this.retailCategoryPercentage = retailCategoryPercentage;
	}
	private String dotComCategoryTotal;
	private String dotComCategoryPercentage;
	
	private String retailCategoryTotal;
	private String retailCategoryPercentage;
	
	
	
	
	public String getCategoryPercentage() {
		return categoryPercentage;
	}
	public void setCategoryPercentage(String categoryPercentage) {
		this.categoryPercentage = categoryPercentage;
	}
	public String getCategoryTotal() {
		return categoryTotal;
	}
	public void setCategoryTotal(String categoryTotal) {
		this.categoryTotal = categoryTotal;
	}
	public String getProductClassNo() {
		return productClassNo;
	}
	public void setProductClassNo(String productClassNo) {
		this.productClassNo = productClassNo;
	}
	public String getProductCat_CD() {
		return productCat_CD;
	}
	public void setProductCat_CD(String productCat_CD) {
		this.productCat_CD = productCat_CD;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
}
