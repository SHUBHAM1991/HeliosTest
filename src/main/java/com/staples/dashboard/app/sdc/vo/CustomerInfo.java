package com.staples.dashboard.app.sdc.vo;

import java.util.HashMap;
import java.util.Map;

public class CustomerInfo {
	
	private String firstName;
	private String lastName;
	private String contactEmailId;
	private String phone;
	private String rewardNo;
	private String customerNo;
	private String tier;
	private String companyName;
	private String billingStreet;
	private String billingCity;
	private String billingState;
	private String postalCode;
	private String businessUnit;
	private String account_rank;
	private String last_contacted_date;
	
	private String SAM_SFDC_ID;
	
	private String enrollDate;
	private String industryCode;
	private String callReason;
	private String category;
	private String accountId;
	private String timeZone;
	
	
	public String getSAM_SFDC_ID() {
		return SAM_SFDC_ID;
	}
	public void setSAM_SFDC_ID(String sAM_SFDC_ID) {
		SAM_SFDC_ID = sAM_SFDC_ID;
	}
	public String getAccount_rank() {
		return account_rank;
	}
	public void setAccount_rank(String account_rank) {
		this.account_rank = account_rank;
	}
	public String getLast_contacted_date() {
		return last_contacted_date;
	}
	public void setLast_contacted_date(String last_contacted_date) {
		this.last_contacted_date = last_contacted_date;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactEmailId() {
		return contactEmailId;
	}
	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRewardNo() {
		return rewardNo;
	}
	public void setRewardNo(String rewardNo) {
		this.rewardNo = rewardNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBillingStreet() {
		return billingStreet;
	}
	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}
	public String getBillingCity() {
		return billingCity;
	}
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}
	public String getBillingState() {
		return billingState;
	}
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getCallReason() {
		return callReason;
	}
	public void setCallReason(String callReason) {
		this.callReason = callReason;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public static String getCustDataMapSAM(String key){
		Map<String,String> custDataMap = new HashMap<String,String>();
		custDataMap.put("0", "customer_number");
		custDataMap.put("1", "rewards_number");
		custDataMap.put("2", "account_rank");
		custDataMap.put("3", "company_name");
		custDataMap.put("4", "CALL_REASON");
		custDataMap.put("5", "CATEGORY");
		custDataMap.put("6", "last_contacted_date");
		custDataMap.put("7", "TM_ZN");
		custDataMap.put("8", "first_name");

		return custDataMap.get(key);
	}

}
