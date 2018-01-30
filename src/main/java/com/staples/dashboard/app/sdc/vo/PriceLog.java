package com.staples.dashboard.app.sdc.vo;

public class PriceLog {
	private String arkeCISRequest;
	private String arkeCISResponse;
	private String priceRequest;
	private String priceResponse;
	private String customerNumber;
	private String loggedInUser;
	
	public String getArkeCISRequest() {
		return arkeCISRequest;
	}
	public void setArkeCISRequest(String arkeCISRequest) {
		this.arkeCISRequest = arkeCISRequest;
	}
	public String getArkeCISResponse() {
		return arkeCISResponse;
	}
	public void setArkeCISResponse(String arkeCISResponse) {
		this.arkeCISResponse = arkeCISResponse;
	}
	public String getPriceRequest() {
		return priceRequest;
	}
	public void setPriceRequest(String priceRequest) {
		this.priceRequest = priceRequest;
	}
	public String getPriceResponse() {
		return priceResponse;
	}
	public void setPriceResponse(String priceResponse) {
		this.priceResponse = priceResponse;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
}
