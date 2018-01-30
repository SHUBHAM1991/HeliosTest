package com.staples.dashboard.app.vo;

import java.util.List;

/**
 * The Class BoughtAlsoBoughtInfoVO.
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
public class BoughtAlsoBoughtInfoVO { 

	private String customerNumber;
	private String orderContact;
	private String orderDate;
	private String skuNumber;
	private List<String> recSkuList;
	private String itemDescription;
	private String thumbnail;
	private List<CustRecommendationVO> custRecommendationVOList;
	private String catenTryId;
	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
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
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the skuNumber
	 */
	public String getSkuNumber() {
		return skuNumber;
	}
	/**
	 * @param skuNumber the skuNumber to set
	 */
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
	/**
	 * @return the recSkuList
	 */
	public List<String> getRecSkuList() {
		return recSkuList;
	}
	/**
	 * @param recSkuList the recSkuList to set
	 */
	public void setRecSkuList(List<String> recSkuList) {
		this.recSkuList = recSkuList;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * @return the custRecommendationVOList
	 */
	public List<CustRecommendationVO> getCustRecommendationVOList() {
		return custRecommendationVOList;
	}
	/**
	 * @param custRecommendationVOList the custRecommendationVOList to set
	 */
	public void setCustRecommendationVOList(
			List<CustRecommendationVO> custRecommendationVOList) {
		this.custRecommendationVOList = custRecommendationVOList;
	}
	/**
	 * @return the catenTryId
	 */
	public String getCatenTryId() {
		return catenTryId;
	}
	/**
	 * @param catenTryId the catenTryId to set
	 */
	public void setCatenTryId(String catenTryId) {
		this.catenTryId = catenTryId;
	}
	
}
