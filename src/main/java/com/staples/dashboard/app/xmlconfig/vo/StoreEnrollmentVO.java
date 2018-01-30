package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The Class StoreEnrollmentVO.
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
public class StoreEnrollmentVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String associateId;
	private int storeNumber;
	private XMLGregorianCalendar enrollTimeStamp;
	private String programIndicator;
	
	private int intStatus;
	
	private String segmentIndicator;
	private String preferedLanguage;
	private String custRwdNumberFld1;
	private String custRwdNumberFld2;
	private String custRwdNumberFld3;
	private String reCustRwdNumberFld1;
	private String reCustRwdNumberFld2;
	private String reCustRwdNumberFld3;
	private String custEmailId;
	private String custRwdNumber;
	private String custFirstName;
	private String custLastName;
	private String companyName;
	private String schoolName;
	private String mailingAddress;
	private String floorSuiteNo;
	private String city;
	private String state;
	private String zip;
	private String emailId;
	private String phoneNumber;
	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;
	
	private int intEmployeeCount;
	private boolean optionalOutInformation1;
	private boolean optionalOutInformation2;
	private boolean optionalOutInformation3;
	private boolean optionalOutInformation4;
	
	private int SpendLevel;
	private boolean ManagedByAMFlg;
	/**
	 * @return the associateId
	 */
	public String getAssociateId() {
		return associateId;
	}
	/**
	 * @param associateId the associateId to set
	 */
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	/**
	 * @return the storeNumber
	 */
	public int getStoreNumber() {
		return storeNumber;
	}
	/**
	 * @param storeNumber the storeNumber to set
	 */
	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}
	/**
	 * @return the enrollTimeStamp
	 */
	public XMLGregorianCalendar getEnrollTimeStamp() {
		return enrollTimeStamp;
	}
	/**
	 * @param enrollTimeStamp the enrollTimeStamp to set
	 */
	public void setEnrollTimeStamp(XMLGregorianCalendar enrollTimeStamp) {
		this.enrollTimeStamp = enrollTimeStamp;
	}
	/**
	 * @return the programIndicator
	 */
	public String getProgramIndicator() {
		return programIndicator;
	}
	/**
	 * @param programIndicator the programIndicator to set
	 */
	public void setProgramIndicator(String programIndicator) {
		this.programIndicator = programIndicator;
	}
	/**
	 * @return the intStatus
	 */
	public int getIntStatus() {
		return intStatus;
	}
	/**
	 * @param intStatus the intStatus to set
	 */
	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}
	/**
	 * @return the segmentIndicator
	 */
	public String getSegmentIndicator() {
		return segmentIndicator;
	}
	/**
	 * @param segmentIndicator the segmentIndicator to set
	 */
	public void setSegmentIndicator(String segmentIndicator) {
		this.segmentIndicator = segmentIndicator;
	}
	/**
	 * @return the preferedLanguage
	 */
	public String getPreferedLanguage() {
		return preferedLanguage;
	}
	/**
	 * @param preferedLanguage the preferedLanguage to set
	 */
	public void setPreferedLanguage(String preferedLanguage) {
		this.preferedLanguage = preferedLanguage;
	}
	/**
	 * @return the custRwdNumberFld1
	 */
	public String getCustRwdNumberFld1() {
		return custRwdNumberFld1;
	}
	/**
	 * @param custRwdNumberFld1 the custRwdNumberFld1 to set
	 */
	public void setCustRwdNumberFld1(String custRwdNumberFld1) {
		this.custRwdNumberFld1 = custRwdNumberFld1;
	}
	/**
	 * @return the custRwdNumberFld2
	 */
	public String getCustRwdNumberFld2() {
		return custRwdNumberFld2;
	}
	/**
	 * @param custRwdNumberFld2 the custRwdNumberFld2 to set
	 */
	public void setCustRwdNumberFld2(String custRwdNumberFld2) {
		this.custRwdNumberFld2 = custRwdNumberFld2;
	}
	/**
	 * @return the custRwdNumberFld3
	 */
	public String getCustRwdNumberFld3() {
		return custRwdNumberFld3;
	}
	/**
	 * @param custRwdNumberFld3 the custRwdNumberFld3 to set
	 */
	public void setCustRwdNumberFld3(String custRwdNumberFld3) {
		this.custRwdNumberFld3 = custRwdNumberFld3;
	}
	/**
	 * @return the reCustRwdNumberFld1
	 */
	public String getReCustRwdNumberFld1() {
		return reCustRwdNumberFld1;
	}
	/**
	 * @param reCustRwdNumberFld1 the reCustRwdNumberFld1 to set
	 */
	public void setReCustRwdNumberFld1(String reCustRwdNumberFld1) {
		this.reCustRwdNumberFld1 = reCustRwdNumberFld1;
	}
	/**
	 * @return the reCustRwdNumberFld2
	 */
	public String getReCustRwdNumberFld2() {
		return reCustRwdNumberFld2;
	}
	/**
	 * @param reCustRwdNumberFld2 the reCustRwdNumberFld2 to set
	 */
	public void setReCustRwdNumberFld2(String reCustRwdNumberFld2) {
		this.reCustRwdNumberFld2 = reCustRwdNumberFld2;
	}
	/**
	 * @return the reCustRwdNumberFld3
	 */
	public String getReCustRwdNumberFld3() {
		return reCustRwdNumberFld3;
	}
	/**
	 * @param reCustRwdNumberFld3 the reCustRwdNumberFld3 to set
	 */
	public void setReCustRwdNumberFld3(String reCustRwdNumberFld3) {
		this.reCustRwdNumberFld3 = reCustRwdNumberFld3;
	}
	/**
	 * @return the custEmailId
	 */
	public String getCustEmailId() {
		return custEmailId;
	}
	/**
	 * @param custEmailId the custEmailId to set
	 */
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	/**
	 * @return the custRwdNumber
	 */
	public String getCustRwdNumber() {
		return custRwdNumber;
	}
	/**
	 * @param custRwdNumber the custRwdNumber to set
	 */
	public void setCustRwdNumber(String custRwdNumber) {
		this.custRwdNumber = custRwdNumber;
	}
	/**
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}
	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	/**
	 * @return the custLastName
	 */
	public String getCustLastName() {
		return custLastName;
	}
	/**
	 * @param custLastName the custLastName to set
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
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
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * @return the mailingAddress
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}
	/**
	 * @param mailingAddress the mailingAddress to set
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	/**
	 * @return the floorSuiteNo
	 */
	public String getFloorSuiteNo() {
		return floorSuiteNo;
	}
	/**
	 * @param floorSuiteNo the floorSuiteNo to set
	 */
	public void setFloorSuiteNo(String floorSuiteNo) {
		this.floorSuiteNo = floorSuiteNo;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	/**
	 * @return the phoneNumber3
	 */
	public String getPhoneNumber3() {
		return phoneNumber3;
	}
	/**
	 * @param phoneNumber3 the phoneNumber3 to set
	 */
	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}
	/**
	 * @return the intEmployeeCount
	 */
	public int getIntEmployeeCount() {
		return intEmployeeCount;
	}
	/**
	 * @param intEmployeeCount the intEmployeeCount to set
	 */
	public void setIntEmployeeCount(int intEmployeeCount) {
		this.intEmployeeCount = intEmployeeCount;
	}
	/**
	 * @return the optionalOutInformation1
	 */
	public boolean isOptionalOutInformation1() {
		return optionalOutInformation1;
	}
	/**
	 * @param optionalOutInformation1 the optionalOutInformation1 to set
	 */
	public void setOptionalOutInformation1(boolean optionalOutInformation1) {
		this.optionalOutInformation1 = optionalOutInformation1;
	}
	/**
	 * @return the optionalOutInformation2
	 */
	public boolean isOptionalOutInformation2() {
		return optionalOutInformation2;
	}
	/**
	 * @param optionalOutInformation2 the optionalOutInformation2 to set
	 */
	public void setOptionalOutInformation2(boolean optionalOutInformation2) {
		this.optionalOutInformation2 = optionalOutInformation2;
	}
	/**
	 * @return the optionalOutInformation3
	 */
	public boolean isOptionalOutInformation3() {
		return optionalOutInformation3;
	}
	/**
	 * @param optionalOutInformation3 the optionalOutInformation3 to set
	 */
	public void setOptionalOutInformation3(boolean optionalOutInformation3) {
		this.optionalOutInformation3 = optionalOutInformation3;
	}
	/**
	 * @return the optionalOutInformation4
	 */
	public boolean isOptionalOutInformation4() {
		return optionalOutInformation4;
	}
	/**
	 * @param optionalOutInformation4 the optionalOutInformation4 to set
	 */
	public void setOptionalOutInformation4(boolean optionalOutInformation4) {
		this.optionalOutInformation4 = optionalOutInformation4;
	}
	/**
	 * @return the spendLevel
	 */
	public int getSpendLevel() {
		return SpendLevel;
	}
	/**
	 * @param spendLevel the spendLevel to set
	 */
	public void setSpendLevel(int spendLevel) {
		SpendLevel = spendLevel;
	}
	/**
	 * @return the managedByAMFlg
	 */
	public boolean isManagedByAMFlg() {
		return ManagedByAMFlg;
	}
	/**
	 * @param managedByAMFlg the managedByAMFlg to set
	 */
	public void setManagedByAMFlg(boolean managedByAMFlg) {
		ManagedByAMFlg = managedByAMFlg;
	}
	
}