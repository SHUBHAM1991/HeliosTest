package com.staples.dashboard.app.vo;

public class PerfDashboardCategoryVO {
	private String custNumber;
	private String categoryName;
	private String currRollMonthSales;
	private String priorRollMonthSales;
	private String currYrToDateSales;
	private String priorYrToDateSales;

	private String rollMonthPenAmount;
	private String rollMonthPenPercent;

	private String yrToDateSalesPercent;
	
	private String refreshDt;
	private String declinePercent;
	private String primProdCategCode;
	private String wirEnabledFlag;
	
	private String mesgDesc;
	
	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCurrRollMonthSales() {
		return currRollMonthSales;
	}

	public void setCurrRollMonthSales(String currRollMonthSales) {
		this.currRollMonthSales = currRollMonthSales;
	}

	public String getPriorRollMonthSales() {
		return priorRollMonthSales;
	}

	public void setPriorRollMonthSales(String priorRollMonthSales) {
		this.priorRollMonthSales = priorRollMonthSales;
	}

	public String getCurrYrToDateSales() {
		return currYrToDateSales;
	}

	public void setCurrYrToDateSales(String currYrToDateSales) {
		this.currYrToDateSales = currYrToDateSales;
	}

	public String getPriorYrToDateSales() {
		return priorYrToDateSales;
	}

	public void setPriorYrToDateSales(String priorYrToDateSales) {
		this.priorYrToDateSales = priorYrToDateSales;
	}

	public String getRollMonthPenAmount() {
		return rollMonthPenAmount;
	}

	public void setRollMonthPenAmount(String rollMonthPenAmount) {
		this.rollMonthPenAmount = rollMonthPenAmount;
	}

	public String getRollMonthPenPercent() {
		return rollMonthPenPercent;
	}

	public void setRollMonthPenPercent(String rollMonthPenPercent) {
		this.rollMonthPenPercent = rollMonthPenPercent;
	}

	public String getYrToDateSalesPercent() {
		return yrToDateSalesPercent;
	}

	public void setYrToDateSalesPercent(String yrToDateSalesPercent) {
		this.yrToDateSalesPercent = yrToDateSalesPercent;
	}
	
	public String getRefreshDt() {
		return refreshDt;
	}

	public void setRefreshDt(String refreshDt) {
		this.refreshDt = refreshDt;
	}

	public String getDeclinePercent() {
		return declinePercent;
	}

	public void setDeclinePercent(String declinePercent) {
		this.declinePercent = declinePercent;
	}

	public String getPrimProdCategCode() {
		return primProdCategCode;
	}

	public void setPrimProdCategCode(String primProdCategCode) {
		this.primProdCategCode = primProdCategCode;
	}

	public String getMesgDesc() {
		return mesgDesc;
	}

	public void setMesgDesc(String mesgDesc) {
		this.mesgDesc = mesgDesc;
	}
	
	public String getWirEnabledFlag() {
		return wirEnabledFlag;
	}

	public void setWirEnabledFlag(String wirEnabledFlag) {
		this.wirEnabledFlag = wirEnabledFlag;
	}

	@Override
	public String toString() {
		return "PerfDashboardCategoryVO [custNumber=" + custNumber
				+ ", categoryName=" + categoryName + ", currRollMonthSales="
				+ currRollMonthSales + ", priorRollMonthSales="
				+ priorRollMonthSales + ", currYrToDateSales="
				+ currYrToDateSales + ", priorYrToDateSales="
				+ priorYrToDateSales + ", rollMonthPenAmount="
				+ rollMonthPenAmount + ", rollMonthPenPercent="
				+ rollMonthPenPercent + ", yrToDateSalesPercent="
				+ yrToDateSalesPercent + ", refreshDt=" + refreshDt
				+ ", declinePercent=" + declinePercent + ", primProdCategCode="
				+ primProdCategCode + ", wirEnabledFlag=" + wirEnabledFlag
				+ ", mesgDesc=" + mesgDesc + "]";
	}


}
