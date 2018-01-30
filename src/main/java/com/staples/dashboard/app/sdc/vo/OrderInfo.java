package com.staples.dashboard.app.sdc.vo;

import java.sql.Date;
import java.util.List;

/**
 * @author UppPa001
 *
 */
public class OrderInfo {
	
	private String customer_id;
	
	private String sbuName;
	

	private List<String> channel;
	
	private String startDate;
	
	private String endDate;
	
	private String orderNo;
	
	private List<String> customerIds;
	
	private String catogeryId;
	
	public String getCatogeryId() {
		return catogeryId;
	}

	public void setCatogeryId(String catogeryId) {
		this.catogeryId = catogeryId;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getSbuName() {
		return sbuName;
	}

	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}

	
	
	

	

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public List<String> getChannel() {
		return channel;
	}

	public void setChannel(List<String> channel) {
		this.channel = channel;
	}
	

}
