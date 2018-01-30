package com.staples.dashboard.app.sdc.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArkeCISVO {
	private Integer responseCode;
	private String responseMessage;
	
	
	private String custAttrbsServiceResponseMessage; 
	private String custAttrbsServiceResponseCode;
	private Long timeStamp;
	ArkeCustomerAttributes custAttrbs;
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getCustAttrbsServiceResponseMessage() {
		return custAttrbsServiceResponseMessage;
	}
	public void setCustAttrbsServiceResponseMessage(String custAttrbsServiceResponseMessage) {
		this.custAttrbsServiceResponseMessage = custAttrbsServiceResponseMessage;
	}
	public String getCustAttrbsServiceResponseCode() {
		return custAttrbsServiceResponseCode;
	}
	public void setCustAttrbsServiceResponseCode(String custAttrbsServiceResponseCode) {
		this.custAttrbsServiceResponseCode = custAttrbsServiceResponseCode;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public ArkeCustomerAttributes getCustAttrbs() {
		return custAttrbs;
	}
	public void setCustAttrbs(ArkeCustomerAttributes custAttrbs) {
		this.custAttrbs = custAttrbs;
	}
}
