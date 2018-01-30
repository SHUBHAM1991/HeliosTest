package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SuperUserInfoVO.
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
public class SuperUserInfoVO {
	private String orderContact;
	private String numOrders;
	private String visitDate;
	private String numVisits;
	private String totalSales;
	private String totalSalesCurr;
	private String numOrdersCurr;
	private String contactEmail;
	private String contactPhone;
	private String numOrdersMon; 
	private String iamId;
	private String firstName;
	private String lastName;
	
	
	public String getNumOrdersMon() {
		return numOrdersMon;
	}
	public void setNumOrdersMon(String numOrdersMon) {
		this.numOrdersMon = numOrdersMon;
	}
	private List<AbabdonedCartVO> abandonedCartListVO = new ArrayList<AbabdonedCartVO>();
	private List<CustRecommendationVO> custRecommendationListVO = new ArrayList<CustRecommendationVO>();
	private List<DotcomAcitivityVO> dotcomActivityVO=new ArrayList<DotcomAcitivityVO>();
	private List<SearchVO> searchItemsListVO = new ArrayList<SearchVO>();
	
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
	 * @return the numOrders
	 */
	public String getNumOrders() {
		return numOrders;
	}
	/**
	 * @param numOrders the numOrders to set
	 */
	public void setNumOrders(String numOrders) {
		this.numOrders = numOrders;
	}
	/**
	 * @return the visitDate
	 */
	public String getVisitDate() {
		return visitDate;
	}
	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	/**
	 * @return the numVisits
	 */
	public String getNumVisits() {
		return numVisits;
	}
	/**
	 * @param numVisits the numVisits to set
	 */
	public void setNumVisits(String numVisits) {
		this.numVisits = numVisits;
	}
	/**
	 * @return the totalSales
	 */
	public String getTotalSales() {
		return totalSales;
	}
	/**
	 * @param totalSales the totalSales to set
	 */
	public void setTotalSales(String totalSales) {
		this.totalSales = totalSales;
	}
	/**
	 * @return the totalSalesCurr
	 */
	public String getTotalSalesCurr() {
		return totalSalesCurr;
	}
	/**
	 * @param totalSalesCurr the totalSalesCurr to set
	 */
	public void setTotalSalesCurr(String totalSalesCurr) {
		this.totalSalesCurr = totalSalesCurr;
	}
	/**
	 * @return the numOrdersCurr
	 */
	public String getNumOrdersCurr() {
		return numOrdersCurr;
	}
	/**
	 * @param numOrdersCurr the numOrdersCurr to set
	 */
	public void setNumOrdersCurr(String numOrdersCurr) {
		this.numOrdersCurr = numOrdersCurr;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return the abandonedCartListVO
	 */
	public List<AbabdonedCartVO> getAbandonedCartListVO() {
		return abandonedCartListVO;
	}
	/**
	 * @param abandonedCartListVO the abandonedCartListVO to set
	 */
	public void setAbandonedCartListVO(List<AbabdonedCartVO> abandonedCartListVO) {
		this.abandonedCartListVO = abandonedCartListVO;
	}
	/**
	 * @return the custRecommendationListVO
	 */
	public List<CustRecommendationVO> getCustRecommendationListVO() {
		return custRecommendationListVO;
	}
	/**
	 * @param custRecommendationListVO the custRecommendationListVO to set
	 */
	public void setCustRecommendationListVO(
			List<CustRecommendationVO> custRecommendationListVO) {
		this.custRecommendationListVO = custRecommendationListVO;
	}
	/**
	 * @return the dotcomActivityVO
	 */
	public List<DotcomAcitivityVO> getDotcomActivityVO() {
		return dotcomActivityVO;
	}
	/**
	 * @param dotcomActivityVO the dotcomActivityVO to set
	 */
	public void setDotcomActivityVO(List<DotcomAcitivityVO> dotcomActivityVO) {
		this.dotcomActivityVO = dotcomActivityVO;
	}
	/**
	 * @return the searchItemsListVO
	 */
	public List<SearchVO> getSearchItemsListVO() {
		return searchItemsListVO;
	}
	/**
	 * @param searchItemsListVO the searchItemsListVO to set
	 */
	public void setSearchItemsListVO(List<SearchVO> searchItemsListVO) {
		this.searchItemsListVO = searchItemsListVO;
	}
	/**
	 * @return the iamId
	 */
	public String getIamId() {
		return iamId;
	}
	/**
	 * @param iamId the iamId to set
	 */
	public void setIamId(String iamId) {
		this.iamId = iamId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
