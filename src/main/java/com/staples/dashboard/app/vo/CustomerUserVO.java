package com.staples.dashboard.app.vo;

public class CustomerUserVO {
	
	public String custNum;
	public String customerUserEmailId;
	public String cutomerUserName;
	public String repId;
	public String boughtSKU;
	
	public String getBoughtSKU() {
		return boughtSKU;
	}
	public void setBoughtSKU(String boughtSKU) {
		this.boughtSKU = boughtSKU;
	}
	
	public String getRepId() {
		return repId;
	}
	public void setRepId(String repId) {
		this.repId = repId;
	}
	public String getCustNum() {
		return custNum;
	}
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public String getCustomerUserEmailId() {
		return customerUserEmailId;
	}
	public void setCustomerUserEmailId(String customerUserEmailId) {
		this.customerUserEmailId = customerUserEmailId;
	}
	
	public String getCutomerUserName() {
		return cutomerUserName;
	}
	public void setCutomerUserName(String cutomerUserName) {
		this.cutomerUserName = cutomerUserName;
	}


}
