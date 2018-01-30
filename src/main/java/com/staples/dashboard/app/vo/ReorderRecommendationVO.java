package com.staples.dashboard.app.vo;

/**
 * The Class ReorderRecommendationVO.
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
public class ReorderRecommendationVO {
	
	private String custNum;
	private String skuNumber;
	private String itemDescription;
	private String thumbnail;
	//private String orderDate;
	private String custName;
	private String catenTryId;
	/**
	 * @return the custNum
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * @param custNum the custNum to set
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
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
	 * @return the orderDate
	 */
	/*public String getOrderDate() {
		return orderDate;
	}*/
	/**
	 * @param orderDate the orderDate to set
	 */
	/*public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}*/
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
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
