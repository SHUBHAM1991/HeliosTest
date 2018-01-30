package com.staples.dashboard.app.sdc.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "lineDiscounts", "lineCharges" })
public class CustomerOrder {
	
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
	
	private String tranTotalAmount;
	
	public String getTranTotalAmount() {
		return tranTotalAmount;
	}
	public void setTranTotalAmount(String tranTotalAmount) {
		this.tranTotalAmount = tranTotalAmount;
	}
	private List<OrderItem> orderLineItems;
	
	
	private List<LineDiscountBean> lineDiscounts;
	private List<LineChargesBean> lineCharges;
	
	public List<LineDiscountBean> getLineDiscounts() {
		return lineDiscounts;
	}
	public void setLineDiscounts(List<LineDiscountBean> lineDiscounts) {
		this.lineDiscounts = lineDiscounts;
	}
	public List<LineChargesBean> getLineCharges() {
		return lineCharges;
	}
	public void setLineCharges(List<LineChargesBean> lineCharges) {
		this.lineCharges = lineCharges;
	}
	public List<OrderItem> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(List<OrderItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
	public List<HeaderDiscountsBean> getHeaderDiscounts() {
		return headerDiscounts;
	}
	public void setHeaderDiscounts(List<HeaderDiscountsBean> headerDiscounts) {
		this.headerDiscounts = headerDiscounts;
	}
	public List<ShipTosBean> getShipTos() {
		return shipTos;
	}
	public void setShipTos(List<ShipTosBean> shipTos) {
		this.shipTos = shipTos;
	}
	private List<HeaderDiscountsBean> headerDiscounts;
	private List<ShipTosBean> shipTos;
	
	
	/*private List<HeaderChargesBean> headerCharges;
	
	private List<HeaderTaxesBean> headerTaxes;
	private List<ShipTosBean> shipTos;
	private List<OrderLinesBean> orderLines;*/
	
	

	
}