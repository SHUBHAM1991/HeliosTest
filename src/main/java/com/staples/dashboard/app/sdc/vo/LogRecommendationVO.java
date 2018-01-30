package com.staples.dashboard.app.sdc.vo;

public class LogRecommendationVO {

	private String reqInput;
	private String inputType;
	private String schemeQueried;
	private Integer countRequested;
	private String recommendedSkus;
	private String strategy;
	private String sparxFile;
	private Long repId;
	private Long customerNumber;
	private Long loggedUser;
	
	public String getReqInput() {
		return reqInput;
	}
	public void setReqInput(String reqInput) {
		this.reqInput = reqInput;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getSchemeQueried() {
		return schemeQueried;
	}
	public void setSchemeQueried(String schemeQueried) {
		this.schemeQueried = schemeQueried;
	}
	public Integer getCountRequested() {
		return countRequested;
	}
	public void setCountRequested(Integer countRequested) {
		this.countRequested = countRequested;
	}
	public String getRecommendedSkus() {
		return recommendedSkus;
	}
	public void setRecommendedSkus(String recommendedSkus) {
		this.recommendedSkus = recommendedSkus;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getSparxFile() {
		return sparxFile;
	}
	public void setSparxFile(String sparxFile) {
		this.sparxFile = sparxFile;
	}
	public Long getRepId() {
		return repId;
	}
	public void setRepId(Long repId) {
		this.repId = repId;
	}
	public Long getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}
	public Long getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(Long loggedUser) {
		this.loggedUser = loggedUser;
	}

}
