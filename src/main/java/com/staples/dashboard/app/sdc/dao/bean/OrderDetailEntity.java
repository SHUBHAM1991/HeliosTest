package com.staples.dashboard.app.sdc.dao.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrderDetailEntity {

	@Id
	private String salesTranId;
	private String orderChannel;              
	private String subscriptionID;
	private String recordType;
	private String orderNo;
	private String orderHeaderKey;
	private String enterpriseCode;
	private String divisionCode;
	private String orderDate;
	private String orderStatusDesc;
	private String orderMethod;
	private String customerPONumber;
	private String billtoId;
	private String billToFirstName;
	private String billToLastName;
	private String billToCompany;
	private String billToAddressLine1;
	private String billToAddressLine2;
	private String billToAddressLine3;
	private String billToCity;
	private String billToState;
	private String customerNumber;
	private String billToZipCode;
	private String shipToZipCode;

	public String getSalesTranId() {
		return salesTranId;
	}

	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderHeaderKey() {
		return orderHeaderKey;
	}

	public void setOrderHeaderKey(String orderHeaderKey) {
		this.orderHeaderKey = orderHeaderKey;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}


	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}

	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	public String getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(String orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getCustomerPONumber() {
		return customerPONumber;
	}

	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}

	public String getBilltoId() {
		return billtoId;
	}

	public void setBilltoId(String billtoId) {
		this.billtoId = billtoId;
	}

	public String getBillToFirstName() {
		return billToFirstName;
	}

	public void setBillToFirstName(String billToFirstName) {
		this.billToFirstName = billToFirstName;
	}

	public String getBillToLastName() {
		return billToLastName;
	}

	public void setBillToLastName(String billToLastName) {
		this.billToLastName = billToLastName;
	}

	public String getBillToCompany() {
		return billToCompany;
	}

	public void setBillToCompany(String billToCompany) {
		this.billToCompany = billToCompany;
	}

	public String getBillToAddressLine1() {
		return billToAddressLine1;
	}

	public void setBillToAddressLine1(String billToAddressLine1) {
		this.billToAddressLine1 = billToAddressLine1;
	}

	public String getBillToAddressLine2() {
		return billToAddressLine2;
	}

	public void setBillToAddressLine2(String billToAddressLine2) {
		this.billToAddressLine2 = billToAddressLine2;
	}

	public String getBillToAddressLine3() {
		return billToAddressLine3;
	}

	public void setBillToAddressLine3(String billToAddressLine3) {
		this.billToAddressLine3 = billToAddressLine3;
	}

	public String getBillToCity() {
		return billToCity;
	}

	public void setBillToCity(String billToCity) {
		this.billToCity = billToCity;
	}

	public String getBillToState() {
		return billToState;
	}

	public void setBillToState(String billToState) {
		this.billToState = billToState;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getBillToZipCode() {
		return billToZipCode;
	}

	public void setBillToZipCode(String billToZipCode) {
		this.billToZipCode = billToZipCode;
	}

	public String getShipToZipCode() {
		return shipToZipCode;
	}

	public void setShipToZipCode(String shipToZipCode) {
		this.shipToZipCode = shipToZipCode;
	}

	@Override
	public String toString() {
		return "OrderDetailEntityBean [salesTranId=" + salesTranId
				+ ", orderChannel=" + orderChannel + ", subscriptionID="
				+ subscriptionID + ", recordType=" + recordType + ", orderNo="
				+ orderNo + ", orderHeaderKey=" + orderHeaderKey
				+ ", enterpriseCode=" + enterpriseCode + ", divisionCode="
				+ divisionCode + ", orderDate=" + orderDate
				+ ", orderStatusDesc=" + orderStatusDesc + ", orderMethod="
				+ orderMethod + ", customerPONumber=" + customerPONumber
				+ ", billtoId=" + billtoId + ", billToFirstName="
				+ billToFirstName + ", billToLastName=" + billToLastName
				+ ", billToCompany=" + billToCompany + ", billToAddressLine1="
				+ billToAddressLine1 + ", billToAddressLine2="
				+ billToAddressLine2 + ", billToAddressLine3="
				+ billToAddressLine3 + ", billToCity=" + billToCity
				+ ", billToState=" + billToState + ", customerNumber="
				+ customerNumber + ", billToZipCode=" + billToZipCode
				+ ", shipToZipCode=" + shipToZipCode + "]";
	}
}
