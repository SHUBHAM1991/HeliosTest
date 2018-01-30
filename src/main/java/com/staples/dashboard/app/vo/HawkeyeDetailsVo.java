/**
 * 
 */
package com.staples.dashboard.app.vo;

import java.util.List;

/**
 * @author VohLo001
 *
 */
public class HawkeyeDetailsVo {

	private String custNum;
	private String totalDollarVal;
	private String churnSeg;
	private String abandonedCat;
	private String offCadenceCat;
	private String highChurnCat;
	private String lowCallCoverage;
	private String decliningCat;
	private String highPropensityCat;
	private String typicalPurchaseDate;
	private String daysSinceLastPurchase;
	private String typicalBrowseDays;
	private String dayaSinceLastBrowse;
	private String typicalSpendAmount;
	private String amountSpent;
	private List<CategoryDataVo> categoryDataVo;
	
	
	public String getCustNum() {
		return custNum;
	}
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public String getTotalDollarVal() {
		return totalDollarVal;
	}
	public void setTotalDollarVal(String totalDollarVal) {
		this.totalDollarVal = totalDollarVal;
	}
	public String getChurnSeg() {
		return churnSeg;
	}
	public void setChurnSeg(String churnSeg) {
		this.churnSeg = churnSeg;
	}
	public String getAbandonedCat() {
		return abandonedCat;
	}
	public void setAbandonedCat(String abandonedCat) {
		this.abandonedCat = abandonedCat;
	}
	public String getOffCadenceCat() {
		return offCadenceCat;
	}
	public void setOffCadenceCat(String offCadenceCat) {
		this.offCadenceCat = offCadenceCat;
	}
	public String getHighChurnCat() {
		return highChurnCat;
	}
	public void setHighChurnCat(String highChurnCat) {
		this.highChurnCat = highChurnCat;
	}
	public String getLowCallCoverage() {
		return lowCallCoverage;
	}
	public void setLowCallCoverage(String lowCallCoverage) {
		this.lowCallCoverage = lowCallCoverage;
	}
	public String getDecliningCat() {
		return decliningCat;
	}
	public void setDecliningCat(String decliningCat) {
		this.decliningCat = decliningCat;
	}
	public String getHighPropensityCat() {
		return highPropensityCat;
	}
	public void setHighPropensityCat(String highPropensityCat) {
		this.highPropensityCat = highPropensityCat;
	}
	public String getTypicalPurchaseDate() {
		return typicalPurchaseDate;
	}
	public void setTypicalPurchaseDate(String typicalPurchaseDate) {
		this.typicalPurchaseDate = typicalPurchaseDate;
	}
	public String getDaysSinceLastPurchase() {
		return daysSinceLastPurchase;
	}
	public void setDaysSinceLastPurchase(String daysSinceLastPurchase) {
		this.daysSinceLastPurchase = daysSinceLastPurchase;
	}
	public String getTypicalBrowseDays() {
		return typicalBrowseDays;
	}
	public void setTypicalBrowseDays(String typicalBrowseDays) {
		this.typicalBrowseDays = typicalBrowseDays;
	}
	public String getDayaSinceLastBrowse() {
		return dayaSinceLastBrowse;
	}
	public void setDayaSinceLastBrowse(String dayaSinceLastBrowse) {
		this.dayaSinceLastBrowse = dayaSinceLastBrowse;
	}
	public String getTypicalSpendAmount() {
		return typicalSpendAmount;
	}
	public void setTypicalSpendAmount(String typicalSpendAmount) {
		this.typicalSpendAmount = typicalSpendAmount;
	}
	public String getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(String amountSpent) {
		this.amountSpent = amountSpent;
	}
	public List<CategoryDataVo> getCategoryDataVo() {
		return categoryDataVo;
	}
	public void setCategoryDataVo(List<CategoryDataVo> categoryDataVo) {
		this.categoryDataVo = categoryDataVo;
	}
	
}
