package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PurchaseDetailsVO.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class PurchaseDetailsVO {
	private String rewardNumber;
	private String customerName;
	private String tranId;
	private String orderNumber;
	private String tranDate;
	private String channelCode;
	private String storeLocation;
	private String storeURL;
	private String storeNumber;
	private float categoryId;
	private String orderLineCount;
	private String totalRewards;
	private String netSpendAmount;
	private String minSpendAmount;
	private String orderContact;
	private List<String> savingCategory;
	private List<String> savingAmount;
	private List<PurchaseHeliosDetailsVO> purchRwdsDtlListVO = new ArrayList<PurchaseHeliosDetailsVO>();
	/**
	 * @return the rewardNumber
	 */
	public String getRewardNumber() {
		return rewardNumber;
	}
	/**
	 * @param rewardNumber the rewardNumber to set
	 */
	public void setRewardNumber(String rewardNumber) {
		this.rewardNumber = rewardNumber;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the tranId
	 */
	public String getTranId() {
		return tranId;
	}
	/**
	 * @param tranId the tranId to set
	 */
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return the tranDate
	 */
	public String getTranDate() {
		return tranDate;
	}
	/**
	 * @param tranDate the tranDate to set
	 */
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	/**
	 * @return the channelCode
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * @param channelCode the channelCode to set
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * @return the storeLocation
	 */
	public String getStoreLocation() {
		return storeLocation;
	}
	/**
	 * @param storeLocation the storeLocation to set
	 */
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	/**
	 * @return the storeURL
	 */
	public String getStoreURL() {
		return storeURL;
	}
	/**
	 * @param storeURL the storeURL to set
	 */
	public void setStoreURL(String storeURL) {
		this.storeURL = storeURL;
	}
	/**
	 * @return the storeNumber
	 */
	public String getStoreNumber() {
		return storeNumber;
	}
	/**
	 * @param storeNumber the storeNumber to set
	 */
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	/**
	 * @return the categoryId
	 */
	public float getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(float categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the orderLineCount
	 */
	public String getOrderLineCount() {
		return orderLineCount;
	}
	/**
	 * @param orderLineCount the orderLineCount to set
	 */
	public void setOrderLineCount(String orderLineCount) {
		this.orderLineCount = orderLineCount;
	}
	/**
	 * @return the totalRewards
	 */
	public String getTotalRewards() {
		return totalRewards;
	}
	/**
	 * @param totalRewards the totalRewards to set
	 */
	public void setTotalRewards(String totalRewards) {
		this.totalRewards = totalRewards;
	}
	/**
	 * @return the netSpendAmount
	 */
	public String getNetSpendAmount() {
		return netSpendAmount;
	}
	/**
	 * @param netSpendAmount the netSpendAmount to set
	 */
	public void setNetSpendAmount(String netSpendAmount) {
		this.netSpendAmount = netSpendAmount;
	}
	public String getMinSpendAmount() {
		return minSpendAmount;
	}
	public void setMinSpendAmount(String minSpendAmount) {
		this.minSpendAmount = minSpendAmount;
	}
	/**
	 * @return the orderContact
	 */
	public String getOrderContact() {
		return orderContact;
	}
	/**
	 * @param orderContact the orderContact to set
	 */
	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}
	/**
	 * @return the purchRwdsDtlListVO
	 */
	public List<PurchaseHeliosDetailsVO> getPurchRwdsDtlListVO() {
		return purchRwdsDtlListVO;
	}
	/**
	 * @param purchRwdsDtlListVO the purchRwdsDtlListVO to set
	 */
	public void setPurchRwdsDtlListVO(
			List<PurchaseHeliosDetailsVO> purchRwdsDtlListVO) {
		this.purchRwdsDtlListVO = purchRwdsDtlListVO;
	}
	public List<String> getSavingCategory() {
		return savingCategory;
	}
	public void setSavingCategory(List<String> savingCategory) {
		this.savingCategory = savingCategory;
	}
	public List<String> getSavingAmount() {
		return savingAmount;
	}
	public void setSavingAmount(List<String> savingAmount) {
		this.savingAmount = savingAmount;
	}
}
