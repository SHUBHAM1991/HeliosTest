package com.staples.dashboard.app.sdc.vo;

public class ShipToBean {

	private String shipToKey;
	private String orderLineKey;
	private String orderLineType;
	private String shipToCompany;
	private String shipToFirstName;
	private String shipToLastName;
	private String shipToAddressLine1;
	private String shipToAddressLine2;
	private String shipToAddressLine3;
	private String shipToCity;
	private String shipToState;
	private String shipToZipCode;

	public ShipToBean(String shipToKey, String orderLineKey, String orderLineType, String shipToCompany, String shipToFirstName, String shipToLastName, 
			String shipToAddressLine1,String shipToAddressLine2, String shipToAddressLine3, String shipToCity, String shipToState, String shipToZipCode) {
		
		super();
		this.shipToKey = shipToKey;
		this.orderLineKey = orderLineKey;
		this.orderLineType = orderLineType;
		this.shipToCompany = shipToCompany;
		this.shipToFirstName = shipToFirstName;
		this.shipToLastName = shipToLastName;
		this.shipToAddressLine1 = shipToAddressLine1;
		this.shipToAddressLine2 = shipToAddressLine2;
		this.shipToAddressLine3 = shipToAddressLine3;
		this.shipToCity = shipToCity;
		this.shipToState = shipToState;
		this.shipToZipCode = shipToZipCode;
	}

	public String getShipToKey() {
		return shipToKey;
	}

	public void setShipToKey(String shipToKey) {
		this.shipToKey = shipToKey;
	}
	
	public String getOrderLineKey() {
		return orderLineKey;
	}

	public void setOrderLineKey(String orderLineKey) {
		this.orderLineKey = orderLineKey;
	}

	public String getOrderLineType() {
		return orderLineType;
	}

	public void setOrderLineType(String orderLineType) {
		this.orderLineType = orderLineType;
	}

	public String getShipToCompany() {
		return shipToCompany;
	}

	public void setShipToCompany(String shipToCompany) {
		this.shipToCompany = shipToCompany;
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

	public String getShipToAddressLine1() {
		return shipToAddressLine1;
	}

	public void setShipToAddressLine1(String shipToAddressLine1) {
		this.shipToAddressLine1 = shipToAddressLine1;
	}

	public String getShipToAddressLine2() {
		return shipToAddressLine2;
	}

	public void setShipToAddressLine2(String shipToAddressLine2) {
		this.shipToAddressLine2 = shipToAddressLine2;
	}

	public String getShipToAddressLine3() {
		return shipToAddressLine3;
	}

	public void setShipToAddressLine3(String shipToAddressLine3) {
		this.shipToAddressLine3 = shipToAddressLine3;
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

	public String getShipToZipCode() {
		return shipToZipCode;
	}

	public void setShipToZipCode(String shipToZipCode) {
		this.shipToZipCode = shipToZipCode;
	}

	@Override
	public String toString() {
		return "ShipToBean [shipToKey=" + shipToKey + ", orderLineKey="
				+ orderLineKey + ", orderLineType=" + orderLineType
				+ ", shipToCompany=" + shipToCompany + ", shipToFirstName="
				+ shipToFirstName + ", shipToLastName=" + shipToLastName
				+ ", shipToAddressLine1=" + shipToAddressLine1
				+ ", shipToAddressLine2=" + shipToAddressLine2
				+ ", shipToAddressLine3=" + shipToAddressLine3
				+ ", shipToCity=" + shipToCity + ", shipToState=" + shipToState
				+ ", shipToZipCode=" + shipToZipCode + "]";
	}
}
