package com.staples.dashboard.app.vo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class CustProfileVO.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class CustProfileVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8397029129576817903L;
	private String custNum;
	private String division;
	private String companyName;
	private String custType;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNum;
	private String callOrder;
	private String playListId;
	private String playSeg;
	private String agentEmailId;
	private String agentPhoneNum;
	private String agentName;
	private String agentFlag;
	private String repCd;

	private String companyAddr1;
	private String companyAddr2;
	private String companyCity;
	private String companyState;
	private String companyCountry;
	private String companyZip;
	private String companyPhone;
	
	private String customerSegment;
	private String vapScore;
	private String accountQualifyScore;
	private String lts;
	private String iamId;
	
	private String lastContactedDate;
	private Boolean contactStatus =false;
	private String calDt;
	private String loggedInUserName;
	private String checkedInDate;
	/*Leads Data Start*/
	private String LeadCustomerNumber;
	private String LeadCompanyName;
	private String LeadCustomerFirstName;
	private String LeadCustomerLastName;
	private String LeadContractType;
	private String LeadCustomerEmail;
	private String LeadCustomerPhone;
	private String LeadAssignDate;
	private String compWebsite;
	/*Leads Data End*/
	
	
	
	
	
	public String getLastContactedDate() {
		return lastContactedDate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public void setLastContactedDate(String lastContactedDate) {
		this.lastContactedDate = lastContactedDate;
	}
	/**
	 * @return the custNum
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * @param custNum the custNum to set
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the callOrder
	 */
	public String getCallOrder() {
		return callOrder;
	}
	/**
	 * @param callOrder the callOrder to set
	 */
	public void setCallOrder(String callOrder) {
		this.callOrder = callOrder;
	}
	/**
	 * @return the playListId
	 */
	public String getPlayListId() {
		return playListId;
	}
	/**
	 * @param playListId the playListId to set
	 */
	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}
	/**
	 * @return the playSeg
	 */
	public String getPlaySeg() {
		return playSeg;
	}
	/**
	 * @param playSeg the playSeg to set
	 */
	public void setPlaySeg(String playSeg) {
		this.playSeg = playSeg;
	}
	/**
	 * @return the agentEmailId
	 */
	public String getAgentEmailId() {
		return agentEmailId;
	}
	/**
	 * @param agentEmailId the agentEmailId to set
	 */
	public void setAgentEmailId(String agentEmailId) {
		this.agentEmailId = agentEmailId;
	}
	/**
	 * @return the agentPhoneNum
	 */
	public String getAgentPhoneNum() {
		return agentPhoneNum;
	}
	/**
	 * @param agentPhoneNum the agentPhoneNum to set
	 */
	public void setAgentPhoneNum(String agentPhoneNum) {
		this.agentPhoneNum = agentPhoneNum;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the agentFlag
	 */
	public String getAgentFlag() {
		return agentFlag;
	}
	/**
	 * @param agentFlag the agentFlag to set
	 */
	public void setAgentFlag(String agentFlag) {
		this.agentFlag = agentFlag;
	}
	/**
	 * @return the repCd
	 */
	public String getRepCd() {
		return repCd;
	}
	/**
	 * @param repCd the repCd to set
	 */
	public void setRepCd(String repCd) {
		this.repCd = repCd;
	}

	/**
	 * @return the companyAddr1
	 */
	public String getCompanyAddr1() {
	    return companyAddr1;
	}
	/**
	 * @param companyAddr1 the companyAddr1 to set
	 */
	public void setCompanyAddr1(String companyAddr1) {
	    this.companyAddr1 = companyAddr1;
	}
	/**
	 * @return the companyAddr2
	 */
	public String getCompanyAddr2() {
	    return companyAddr2;
	}
	/**
	 * @param companyAddr2 the companyAddr2 to set
	 */
	public void setCompanyAddr2(String companyAddr2) {
	    this.companyAddr2 = companyAddr2;
	}
	/**
	 * @return the companyCity
	 */
	public String getCompanyCity() {
	    return companyCity;
	}
	/**
	 * @param companyCity the companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
	    this.companyCity = companyCity;
	}
	/**
	 * @return the companyState
	 */
	public String getCompanyState() {
	    return companyState;
	}
	/**
	 * @param companyState the companyState to set
	 */
	public void setCompanyState(String companyState) {
	    this.companyState = companyState;
	}
	/**
	 * @return the companyCountry
	 */
	public String getCompanyCountry() {
	    return companyCountry;
	}
	/**
	 * @param companyCountry the companyCountry to set
	 */
	public void setCompanyCountry(String companyCountry) {
	    this.companyCountry = companyCountry;
	}
	/**
	 * @return the companyZip
	 */
	public String getCompanyZip() {
	    return companyZip;
	}
	/**
	 * @param companyZip the companyZip to set
	 */
	public void setCompanyZip(String companyZip) {
	    this.companyZip = companyZip;
	}
	/**
	 * @return the companyPhone
	 */
	public String getCompanyPhone() {
	    return companyPhone;
	}
	/**
	 * @param companyPhone the companyPhone to set
	 */
	public void setCompanyPhone(String companyPhone) {
	    this.companyPhone = companyPhone;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getCustDataMap(String key){
		Map<String,String> custDataMap = new HashMap<String,String>();
		custDataMap.put("0", "CALL_ORDER");
		custDataMap.put("1", "CUSTOMER_NUMBER");
		custDataMap.put("2", "MASTER_CUST_NUM");
		custDataMap.put("3", "CUSTOMER_TYPE");
		custDataMap.put("4", "COMPANY_NAME");
		custDataMap.put("5", "CUSTOMER_SEGMENT");
		custDataMap.put("6", "VAP_SCORE");
		custDataMap.put("7", "ACCOUNT_QUALIFY_SCORE");
		custDataMap.put("8", "PLAY_SEGMENT");
		custDataMap.put("9", "LAST_CONTACTED_DATE");
		
		return custDataMap.get(key);
	}
	
	public static String getCustDataMapLead(String key){
		Map<String,String> custDataMap = new HashMap<String,String>();
		custDataMap.put("0", "M.CUSTOMER_NUMBER");
		custDataMap.put("1", "M.CONTRACT_TYPE");
		custDataMap.put("2", "M.COMPANY_NAME");
		custDataMap.put("3", "CP.FIRST_NAME");
		/*custDataMap.put("3", "CALL_ORDER");		
		custDataMap.put("4", "CUSTOMER_SEGMENT");
		custDataMap.put("5", "VAP_SCORE");
		custDataMap.put("6", "ACCOUNT_QUALIFY_SCORE");
		custDataMap.put("7", "PLAY_SEGMENT");
		custDataMap.put("8", "LAST_CONTACTED_DATE");*/
		
		return custDataMap.get(key);
	}
	public static String getCustDataMapCdm(String key){
		
		Map<String,String> custDataMap = new HashMap<String,String>();
		custDataMap.put("0", "CUSTOMER_NUMBER");
		custDataMap.put("1", "COMPANY_NAME");
		custDataMap.put("2", "CALL_ORDER");
		custDataMap.put("3", "POTENTIAL_CONTACT_VALUE");
		custDataMap.put("4", "S_LAST_CONTACTED_DATE");		
		custDataMap.put("5", "VAP_SCORE");
		custDataMap.put("6", "VAP_SCORE_TYPE");
		custDataMap.put("7", "VAP_SCORE_NAME");
		
		
		return custDataMap.get(key);
	}
	/**
	 * Method to convert null to blank.
	 * @param s
	 * @return the string
	 */
	private static String convertNullToBlank(String s) {
		return (s == null ? "" : s.toUpperCase());
	}
	
	/**
	 * static block
	 */
	public static Comparator<CustProfileVO> COMPARE_BY_PLAY_SEG_ASC = new Comparator<CustProfileVO>() {
		public int compare(CustProfileVO one, CustProfileVO other) {
			return convertNullToBlank(one.getPlaySeg()).compareTo(
					convertNullToBlank(other.getPlaySeg()));
		}
	};
	
	/**
	 * static block
	 */
	public static Comparator<CustProfileVO> COMPARE_BY_PLAY_SEG_DESC = new Comparator<CustProfileVO>() {
		public int compare(CustProfileVO one, CustProfileVO other) {
			return convertNullToBlank(one.getPlaySeg()).compareTo(
					convertNullToBlank(other.getPlaySeg())) *(-1);
		}
	};

	public String getVapScore() {
		return vapScore;
	}
	public void setVapScore(String vapScore) {
		this.vapScore = vapScore;
	}
	public String getAccountQualifyScore() {
		return accountQualifyScore;
	}
	public void setAccountQualifyScore(String accountQualifyScore) {
		this.accountQualifyScore = accountQualifyScore;
	}
	public String getCustomerSegment() {
		return customerSegment;
	}
	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}
	public String getLts() {
		return lts;
	}
	public void setLts(String lts) {
		this.lts = lts;
	}
	public String getIamId() {
		return iamId;
	}
	public void setIamId(String iamId) {
		this.iamId = iamId;
	}
	/**
	 * @return the leadCustomerNumber
	 */
	public String getLeadCustomerNumber() {
		return LeadCustomerNumber;
	}
	/**
	 * @param leadCustomerNumber the leadCustomerNumber to set
	 */
	public void setLeadCustomerNumber(String leadCustomerNumber) {
		LeadCustomerNumber = leadCustomerNumber;
	}
	/**
	 * @return the leadCompanyName
	 */
	public String getLeadCompanyName() {
		return LeadCompanyName;
	}
	/**
	 * @param leadCompanyName the leadCompanyName to set
	 */
	public void setLeadCompanyName(String leadCompanyName) {
		LeadCompanyName = leadCompanyName;
	}

	
	/**
	 * @return the leadCustomerFirstName
	 */
	public String getLeadCustomerFirstName() {
		return LeadCustomerFirstName;
	}
	/**
	 * @param leadCustomerFirstName the leadCustomerFirstName to set
	 */
	public void setLeadCustomerFirstName(String leadCustomerFirstName) {
		LeadCustomerFirstName = leadCustomerFirstName;
	}
	/**
	 * @return the leadCustomerLastName
	 */
	public String getLeadCustomerLastName() {
		return LeadCustomerLastName;
	}
	/**
	 * @param leadCustomerLastName the leadCustomerLastName to set
	 */
	public void setLeadCustomerLastName(String leadCustomerLastName) {
		LeadCustomerLastName = leadCustomerLastName;
	}
	/**
	 * @return the leadContractType
	 */
	public String getLeadContractType() {
		return LeadContractType;
	}
	/**
	 * @param leadContractType the leadContractType to set
	 */
	public void setLeadContractType(String leadContractType) {
		LeadContractType = leadContractType;
	}
	/**
	 * @return the leadCustomerEmail
	 */
	public String getLeadCustomerEmail() {
		return LeadCustomerEmail;
	}
	/**
	 * @param leadCustomerEmail the leadCustomerEmail to set
	 */
	public void setLeadCustomerEmail(String leadCustomerEmail) {
		LeadCustomerEmail = leadCustomerEmail;
	}
	/**
	 * @return the leadCustomerPhone
	 */
	public String getLeadCustomerPhone() {
		return LeadCustomerPhone;
	}
	/**
	 * @param leadCustomerPhone the leadCustomerPhone to set
	 */
	public void setLeadCustomerPhone(String leadCustomerPhone) {
		LeadCustomerPhone = leadCustomerPhone;
	}
	/**
	 * @return the leadAssignDate
	 */
	public String getLeadAssignDate() {
		return LeadAssignDate;
	}
	/**
	 * @param leadAssignDate the leadAssignDate to set
	 */
	public void setLeadAssignDate(String leadAssignDate) {
		LeadAssignDate = leadAssignDate;
	}
	/**
	 * @return the contactStatus
	 */
	public boolean isContactStatus() {
		return contactStatus;
	}
	/**
	 * @param contactStatus the contactStatus to set
	 */
	public void setContactStatus(Boolean contactStatus) {
		this.contactStatus = contactStatus;
	}
	/**
	 * @return the calDt
	 */
	public String getCalDt() {
		return calDt;
	}
	/**
	 * @param calDt the calDt to set
	 */
	public void setCalDt(String calDt) {
		this.calDt = calDt;
	}
	/**
	 * @return the loggedInUserName
	 */
	public String getLoggedInUserName() {
		return loggedInUserName;
	}
	/**
	 * @param loggedInUserName the loggedInUserName to set
	 */
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}
	/**
	 * @return the checkedInDate
	 */
	public String getCheckedInDate() {
		return checkedInDate;
	}
	/**
	 * @param checkedInDate the checkedInDate to set
	 */
	public void setCheckedInDate(String checkedInDate) {
		this.checkedInDate = checkedInDate;
	}
	/*@Override
	public int compareTo(CustProfileVO cpv) {		
		return this.contactStatus.compareTo(cpv.contactStatus);		
	}*/
	public String getCompWebsite() {
		return compWebsite;
	}
	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}

    
}
