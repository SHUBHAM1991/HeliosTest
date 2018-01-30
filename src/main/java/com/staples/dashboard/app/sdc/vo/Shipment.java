package com.staples.dashboard.app.sdc.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({ "fiscalYr"})
public class Shipment {
	
	private String shipToFirstName;
	
	private String shipToLastName;
	
	private String shipToId;
	
	private String shiptoAddress1;
	
	private String masterNumber;
	
	private String transRewardNumber;
	
	private String currentFYS;
	
	private String previousFYS;
	
	private String locOrderSum;
	
	private String shipToCity;
	
	private String shipToState;
	
	private String shipToZip;
	
	private String fiscalYr;
	
	private String cfy_order_count;
	private String lfy_order_count;
	
	private String yoy;
	
	
	
	public String getYoy() {
		return yoy;
	}

	public void setYoy(String yoy) {
		this.yoy = yoy;
	}

	public String getCfy_order_count() {
		return cfy_order_count;
	}

	public void setCfy_order_count(String cfy_order_count) {
		this.cfy_order_count = cfy_order_count;
	}

	public String getLfy_order_count() {
		return lfy_order_count;
	}

	public void setLfy_order_count(String lfy_order_count) {
		this.lfy_order_count = lfy_order_count;
	}

	public String getFiscalYr() {
		return fiscalYr;
	}

	public void setFiscalYr(String fiscalYr) {
		this.fiscalYr = fiscalYr;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getShipToZip() {
		return shipToZip;
	}

	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}

	public String getLocOrderSum() {
		return locOrderSum;
	}

	public void setLocOrderSum(String locOrderSum) {
		this.locOrderSum = locOrderSum;
	}

	public String getCurrentFYS() {
		return currentFYS;
	}

	public void setCurrentFYS(String currentFYS) {
		this.currentFYS = currentFYS;
	}

	public String getPreviousFYS() {
		return previousFYS;
	}

	public void setPreviousFYS(String previousFYS) {
		this.previousFYS = previousFYS;
	}

	private List<CustomerOrders> orders;
	

	public List<CustomerOrders> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrders> orders) {
		this.orders = orders;
	}

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String shipToFirstName) {
		this.shipToFirstName = shipToFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String shipToLastName) {
		this.shipToLastName = shipToLastName;
	}

	public String getShipToId() {
		return shipToId;
	}

	public void setShipToId(String shipToId) {
		this.shipToId = shipToId;
	}

	public String getShiptoAddress1() {
		return shiptoAddress1;
	}

	public void setShiptoAddress1(String shiptoAddress1) {
		this.shiptoAddress1 = shiptoAddress1;
	}

	public String getMasterNumber() {
		return masterNumber;
	}

	public void setMasterNumber(String masterNumber) {
		this.masterNumber = masterNumber;
	}

	public String getTransRewardNumber() {
		return transRewardNumber;
	}

	public void setTransRewardNumber(String transRewardNumber) {
		this.transRewardNumber = transRewardNumber;
	}
	
	

}
