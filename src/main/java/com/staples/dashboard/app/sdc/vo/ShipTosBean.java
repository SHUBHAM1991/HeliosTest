package com.staples.dashboard.app.sdc.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@IdClass(SalesTranLineItemId.class)
@JsonIgnoreProperties({ "salesTranId", "salesTranLineItemId" })
public class ShipTosBean {
	
	@Id
	private String salesTranId;
	@Id
	private String salesTranLineItemId;
	private String orderLineKey;
	private String shipToKey;
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

	/**
	 * @return the salesTranId
	 */
	public String getSalesTranId() {
		return salesTranId;
	}

	/**
	 * @param salesTranId the salesTranId to set
	 */
	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}

	/**
	 * @return the salesTranLineItemId
	 */
	public String getSalesTranLineItemId() {
		return salesTranLineItemId;
	}

	/**
	 * @param salesTranLineItemId the salesTranLineItemId to set
	 */
	public void setSalesTranLineItemId(String salesTranLineItemId) {
		this.salesTranLineItemId = salesTranLineItemId;
	}

	/**
	 * @return the orderLineKey
	 */
	public String getOrderLineKey() {
		return orderLineKey;
	}

	/**
	 * @param orderLineKey the orderLineKey to set
	 */
	public void setOrderLineKey(String orderLineKey) {
		this.orderLineKey = orderLineKey;
	}

	public String getShipToKey() {
		return shipToKey;
	}

	public void setShipToKey(String shipToKey) {
		this.shipToKey = shipToKey;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShipTosBean [salesTranId=" + salesTranId
				+ ", salesTranLineItemId=" + salesTranLineItemId
				+ ", orderLineKey=" + orderLineKey + ", shipToKey=" + shipToKey
				+ ", orderLineType=" + orderLineType + ", shipToCompany="
				+ shipToCompany + ", shipToFirstName=" + shipToFirstName
				+ ", shipToLastName=" + shipToLastName
				+ ", shipToAddressLine1=" + shipToAddressLine1
				+ ", shipToAddressLine2=" + shipToAddressLine2
				+ ", shipToAddressLine3=" + shipToAddressLine3
				+ ", shipToCity=" + shipToCity + ", shipToState=" + shipToState
				+ ", shipToZipCode=" + shipToZipCode + "]";
	}
}
