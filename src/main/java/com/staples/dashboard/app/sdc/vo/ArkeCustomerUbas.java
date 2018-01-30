package com.staples.dashboard.app.sdc.vo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArkeCustomerUbas {

	private String customerNo;
	private List<String> rewardsNo= new ArrayList<String>();
	private String rewardsTierCd;
	private List<String> suffixCodes;
	private String zoneId;
	private String email;
	private String emailHash;
	private String staplesUser;
	private String staplesId;
	private String tierSegment;
	
	private List<String> runaUserToken= new ArrayList<String>();
			
	private List<String> fingerprint = new ArrayList<String>();

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public List<String> getRewardsNo() {
		return rewardsNo;
	}

	public void setRewardsNo(List<String> rewardsNo) {
		this.rewardsNo = rewardsNo;
	}

	public String getRewardsTierCd() {
		return rewardsTierCd;
	}

	public void setRewardsTierCd(String rewardsTierCd) {
		this.rewardsTierCd = rewardsTierCd;
	}

	

	public List<String> getSuffixCodes() {
		return suffixCodes;
	}

	public void setSuffixCodes(List<String> suffixCodes) {
		this.suffixCodes = suffixCodes;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public String getStaplesUser() {
		return staplesUser;
	}

	public void setStaplesUser(String staplesUser) {
		this.staplesUser = staplesUser;
	}

	public String getStaplesId() {
		return staplesId;
	}

	public void setStaplesId(String staplesId) {
		this.staplesId = staplesId;
	}

	public String getTierSegment() {
		return tierSegment;
	}

	public void setTierSegment(String tierSegment) {
		this.tierSegment = tierSegment;
	}

	public List<String> getRunaUserToken() {
		return runaUserToken;
	}

	public void setRunaUserToken(List<String> runaUserToken) {
		this.runaUserToken = runaUserToken;
	}

	public List<String> getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(List<String> fingerprint) {
		this.fingerprint = fingerprint;
	}
}
