/**
 * 
 */
package com.staples.dashboard.app.vo;

import java.util.List;

/**
 * @author VohLo001
 *
 */
public class SuperUserDetailsVo {

	private String custNum;
	private String compName;
	private String accId;
	private String custTimeone;
	private String offCadenceCat;
	private String masterContactSfId;
	private String contactFullName;
	private String phoneNum;
	private String email;
	private String heRank;
	private List<StaplesDotcomActivityVo> staplesDotcomActivityVo;
	
	
	
	public String getCustNum() {
		return custNum;
	}
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getCustTimeone() {
		return custTimeone;
	}
	public void setCustTimeone(String custTimeone) {
		this.custTimeone = custTimeone;
	}
	public String getOffCadenceCat() {
		return offCadenceCat;
	}
	public void setOffCadenceCat(String offCadenceCat) {
		this.offCadenceCat = offCadenceCat;
	}
	public String getMasterContactSfId() {
		return masterContactSfId;
	}
	public void setMasterContactSfId(String masterContactSfId) {
		this.masterContactSfId = masterContactSfId;
	}
	public String getContactFullName() {
		return contactFullName;
	}
	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeRank() {
		return heRank;
	}
	public void setHeRank(String heRank) {
		this.heRank = heRank;
	}
	public List<StaplesDotcomActivityVo> getStaplesDotcomActivityVo() {
		return staplesDotcomActivityVo;
	}
	public void setStaplesDotcomActivityVo(List<StaplesDotcomActivityVo> staplesDotcomActivityVo) {
		this.staplesDotcomActivityVo = staplesDotcomActivityVo;
	}

	
	
}
