package com.staples.dashboard.app.vo;

import java.io.Serializable;

/**
 * The Class ShipToVO.
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
public class ShipToVO implements Serializable{
private static final long serialVersionUID = 10L;
	
    private String shipToId;
	private String customerAddress;
	private String customerCity;
	private String customerState;
	private String customerZip;
	private String totalSpendCurYr="";
	private String totalSpendLastYr="";
	private String orderNoCurYr="";
	private String orderNoLastYr="";
	private String TotalSpendLastYearByPeriod="";
	private String shipToStatus="";
	/**
	 * @return the shipToId
	 */
	public String getShipToId() {
		return shipToId;
	}
	/**
	 * @param shipToId the shipToId to set
	 */
	public void setShipToId(String shipToId) {
		this.shipToId = shipToId;
	}
	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}
	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	/**
	 * @return the customerCity
	 */
	public String getCustomerCity() {
		return customerCity;
	}
	/**
	 * @param customerCity the customerCity to set
	 */
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	/**
	 * @return the customerState
	 */
	public String getCustomerState() {
		return customerState;
	}
	/**
	 * @param customerState the customerState to set
	 */
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	/**
	 * @return the customerZip
	 */
	public String getCustomerZip() {
		return customerZip;
	}
	/**
	 * @param customerZip the customerZip to set
	 */
	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}
	/**
	 * @return the totalSpendCurYr
	 */
	public String getTotalSpendCurYr() {
		return totalSpendCurYr;
	}
	/**
	 * @param totalSpendCurYr the totalSpendCurYr to set
	 */
	public void setTotalSpendCurYr(String totalSpendCurYr) {
		this.totalSpendCurYr = totalSpendCurYr;
	}
	/**
	 * @return the totalSpendLastYr
	 */
	public String getTotalSpendLastYr() {
		return totalSpendLastYr;
	}
	/**
	 * @param totalSpendLastYr the totalSpendLastYr to set
	 */
	public void setTotalSpendLastYr(String totalSpendLastYr) {
		this.totalSpendLastYr = totalSpendLastYr;
	}
	/**
	 * @return the orderNoCurYr
	 */
	public String getOrderNoCurYr() {
		return orderNoCurYr;
	}
	/**
	 * @param orderNoCurYr the orderNoCurYr to set
	 */
	public void setOrderNoCurYr(String orderNoCurYr) {
		this.orderNoCurYr = orderNoCurYr;
	}
	/**
	 * @return the orderNoLastYr
	 */
	public String getOrderNoLastYr() {
		return orderNoLastYr;
	}
	/**
	 * @param orderNoLastYr the orderNoLastYr to set
	 */
	public void setOrderNoLastYr(String orderNoLastYr) {
		this.orderNoLastYr = orderNoLastYr;
	}
	public String getTotalSpendLastYearByPeriod() {
		return TotalSpendLastYearByPeriod;
	}
	public void setTotalSpendLastYearByPeriod(String totalSpendLastYearByPeriod) {
		TotalSpendLastYearByPeriod = totalSpendLastYearByPeriod;
	}
	public String getShipToStatus() {
		return shipToStatus;
	}
	public void setShipToStatus(String shipToStatus) {
		this.shipToStatus = shipToStatus;
	}
	
	
}
