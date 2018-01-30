package com.staples.dashboard.app.sdc.vo;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepResponse {
	
	private String repNumber;
	private String repName;
	private String repPhone;
	
	private  List<CustomerInfo> customerInfo=new ArrayList<CustomerInfo>();
	private  List<CustomerParentChildRewardsVo> customerRewards=new ArrayList<CustomerParentChildRewardsVo>();;
	private int totalCount;
	private int displayCount;
	
	public List<CustomerInfo> getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(List<CustomerInfo> customerInfo) {
		this.customerInfo = customerInfo;
	}
	public String getRepNumber() {
		return repNumber;
	}
	public void setRepNumber(String repNumber) {
		this.repNumber = repNumber;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepPhone() {
		return repPhone;
	}
	public void setRepPhone(String repPhone) {
		this.repPhone = repPhone;
	}
	public List<CustomerParentChildRewardsVo> getCustomerRewards() {
		return customerRewards;
	}
	public void setCustomerRewards(List<CustomerParentChildRewardsVo> customerRewards) {
		this.customerRewards = customerRewards;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getDisplayCount() {
		return displayCount;
	}
	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}
	
	

}
