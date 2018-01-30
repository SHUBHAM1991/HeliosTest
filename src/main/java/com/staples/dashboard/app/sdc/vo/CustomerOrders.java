package com.staples.dashboard.app.sdc.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staples.dashboard.app.xmlconfig.vo.CategoryVO;

/**
 * @author UppPa001
 *
 */
@JsonIgnoreProperties({ "orderItems","insertDate","shipToFirstname","shipToLastname","extendedPrice" })
public class CustomerOrders {
	
	private String customerNo;
	private String masterNumber;
	
	private String transRewardNumber;
	
	private String orderTranDate;
	
	private String insertDate;
	
	private String recordType;
	
	private String sourceSystemId;
	
	private String tranStatus;
	
	private String tranTotalAmount;
	
	private String transId;
	
	private String totalCoponAmount;
	
	private String totalDiscountAmount;
	
	private String contactName;
	
	private String orderIssuer;
	
	private String orderReturned;
	
	private String masterSaleTranId;
	
	private String orderItemsCount;
	
	private String extendedPrice;
	
	private List<ItemProductVO> OrderLevelItems;
	
	
	
	
	public List<ItemProductVO> getOrderLevelItems() {
		return OrderLevelItems;
	}
	public void setOrderLevelItems(List<ItemProductVO> orderLevelItems) {
		OrderLevelItems = orderLevelItems;
	}
	public String getExtendedPrice() {
		return extendedPrice;
	}
	public void setExtendedPrice(String extendedPrice) {
		this.extendedPrice = extendedPrice;
	}
	private List<CategoryVO> objCategoriesList;
	
	private String shipToFirstname;
	private String shipToLastname;
	
	private List<String> returnOrderNos;
	
	
	public List<String> getReturnOrderNos() {
		return returnOrderNos;
	}
	public void setReturnOrderNos(List<String> returnOrderNos) {
		this.returnOrderNos = returnOrderNos;
	}
	public String getShipToFirstname() {
		return shipToFirstname;
	}
	public void setShipToFirstname(String shipToFirstname) {
		this.shipToFirstname = shipToFirstname;
	}
	public String getShipToLastname() {
		return shipToLastname;
	}
	public void setShipToLastname(String shipToLastname) {
		this.shipToLastname = shipToLastname;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	private boolean isSelectedCategory=false;
	
	
	
	
	public boolean isSelectedCategory() {
		return isSelectedCategory;
	}
	public void setSelectedCategory(boolean isSelectedCategory) {
		this.isSelectedCategory = isSelectedCategory;
	}
	public List<CategoryVO> getObjCategoriesList() {
		return objCategoriesList;
	}
	public void setObjCategoriesList(List<CategoryVO> objCategoriesList) {
		this.objCategoriesList = objCategoriesList;
	}
	public String getOrderItemsCount() {
		return orderItemsCount;
	}
	public void setOrderItemsCount(String orderItemsCount) {
		this.orderItemsCount = orderItemsCount;
	}
	public String getOrderReturned() {
		return orderReturned;
	}
	public void setOrderReturned(String orderReturned) {
		this.orderReturned = orderReturned;
	}
	public String getMasterSaleTranId() {
		return masterSaleTranId;
	}
	public void setMasterSaleTranId(String masterSaleTranId) {
		this.masterSaleTranId = masterSaleTranId;
	}
	public String getOrderIssuer() {
		return orderIssuer;
	}
	public void setOrderIssuer(String orderIssuer) {
		this.orderIssuer = orderIssuer;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTotalDiscountAmount() {
		return totalDiscountAmount;
	}
	public void setTotalDiscountAmount(String totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}
	public String getTotalCoponAmount() {
		return totalCoponAmount;
	}
	public void setTotalCoponAmount(String totalCoponAmount) {
		this.totalCoponAmount = totalCoponAmount;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	private List<OrderItem> orderItems;
	
	public String getTranTotalAmount() {
		return tranTotalAmount;
	}
	public void setTranTotalAmount(String tranTotalAmount) {
		this.tranTotalAmount = tranTotalAmount;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	private String orderNumber;
	

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getTranStatus() {
		return tranStatus;
	}
	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
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
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	public String getSourceSystemId() {
		return sourceSystemId;
	}
	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}
	public String getOrderTranDate() {
		return orderTranDate;
	}
	public void setOrderTranDate(String orderTranDate) {
		this.orderTranDate = orderTranDate;
	}
	
	
}
