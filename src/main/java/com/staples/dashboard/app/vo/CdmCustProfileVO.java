package com.staples.dashboard.app.vo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
public class CdmCustProfileVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8397029129576817903L;
	
	private String custNum;
	private String companyName;
	private String callOrder;
	private String customerType;
	private String potentialCallVal;
	private String lastContactedDate;
	private String customerSegment;
	private String vapScore;
	private String vapScoreType;
	private String vapScoreName;
	private String timeZone;
	private String iam_Id;
	private String division;
	private String status;
	private String todaysProgress;
	private String weakProgress;
	private String segmentIdList;
	private List<NotificationInfoVo> notificationList;
	private String compWebsite;
	private String alertCount;
	private String alertDeleteCount;
	private String isActiveAlert;
	private String totalRows;
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
	 * @return the potentialContactVal
	 */
	public String getPotentialCallVal() {
		return potentialCallVal;
	}


	/**
	 * @param potentialContactVal the potentialContactVal to set
	 */
	public void setPotentialCallVal(String potentialCallVal) {
		this.potentialCallVal = potentialCallVal;
	}


	/**
	 * @return the lastContactedDate
	 */
	public String getLastContactedDate() {
		return lastContactedDate;
	}


	/**
	 * @param lastContactedDate the lastContactedDate to set
	 */
	public void setLastContactedDate(String lastContactedDate) {
		this.lastContactedDate = lastContactedDate;
	}


	/**
	 * @return the vapScore
	 */
	public String getVapScore() {
		return vapScore;
	}


	/**
	 * @param vapScore the vapScore to set
	 */
	public void setVapScore(String vapScore) {
		this.vapScore = vapScore;
	}


	/**
	 * @return the vapScoreType
	 */
	public String getVapScoreType() {
		return vapScoreType;
	}


	/**
	 * @param vapScoreType the vapScoreType to set
	 */
	public void setVapScoreType(String vapScoreType) {
		this.vapScoreType = vapScoreType;
	}


	/**
	 * @return the vapScoreName
	 */
	public String getVapScoreName() {
		return vapScoreName;
	}


	/**
	 * @param vapScoreName the vapScoreName to set
	 */
	public void setVapScoreName(String vapScoreName) {
		this.vapScoreName = vapScoreName;
	}


	


	/**
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}


	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	/**
	 * @return the customerSegment
	 */
	public String getCustomerSegment() {
		return customerSegment;
	}


	/**
	 * @param customerSegment the customerSegment to set
	 */
	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}


	public String getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	


	public String getIam_Id() {
		return iam_Id;
	}


	public void setIam_Id(String iam_Id) {
		this.iam_Id = iam_Id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}

   
	public String getTodaysProgress() {
		return todaysProgress;
	}


	public void setTodaysProgress(String todaysProgress) {
		this.todaysProgress = todaysProgress;
	}


	public String getWeakProgress() {
		return weakProgress;
	}


	public void setWeakProgress(String weakProgress) {
		this.weakProgress = weakProgress;
	}


	public List<NotificationInfoVo> getNotificationList() {
		return notificationList;
	}


	public void setNotificationList(List<NotificationInfoVo> notificationList) {
		this.notificationList = notificationList;
	}


	public String getSegmentIdList() {
		return segmentIdList;
	}


	public void setSegmentIdList(String segmentIdList) {
		this.segmentIdList = segmentIdList;
	}


	public String getCompWebsite() {
		return compWebsite;
	}


	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}


	public String getAlertCount() {
		return alertCount;
	}


	public void setAlertCount(String alertCount) {
		this.alertCount = alertCount;
	}


	public String getIsActiveAlert() {
		return isActiveAlert;
	}


	public void setIsActiveAlert(String isActiveAlert) {
		this.isActiveAlert = isActiveAlert;
	}


	public String getAlertDeleteCount() {
		return alertDeleteCount;
	}


	public void setAlertDeleteCount(String alertDeleteCount) {
		this.alertDeleteCount = alertDeleteCount;
	}


	/**
	 * @return the totalRows
	 */
	public String getTotalRows() {
		return totalRows;
	}


	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}


	public static String getCustDataMapCdm(String key){
		Map<String,String> custDataMap = new HashMap<String,String>();
		custDataMap.put("0", "CUSTOMER_NUMBER");
		custDataMap.put("1", "COMPANY_NAME");
		custDataMap.put("2", "CALL_ORDER");
		custDataMap.put("3", "POTENTIAL_CALL_VALUE");
		custDataMap.put("4", "CUSTOMER_TYPE");
		custDataMap.put("5", "IAM_ID");
		custDataMap.put("6", "S_LAST_CONTACTED_DATE");
		custDataMap.put("7", "CUSTOMER_SEGMENT");
		custDataMap.put("8", "VAP_SCORE");
		custDataMap.put("9", "VAP_SCORE_TYPE");
		custDataMap.put("10", "VAP_SCORE_NAME");
		custDataMap.put("11", "TIMEZONE");
		custDataMap.put("12", "STATUS");
		custDataMap.put("13", "DIVISION");
		custDataMap.put("14", "TODAYS_PROGRESS");
		custDataMap.put("15", "WEEK_PROGRESS");
		custDataMap.put("16", "CUSTOMER_SEGMENT_ID");
		custDataMap.put("17", "COMPANY_WEBSITE");
		custDataMap.put("18", "TOTAL_ALERT_COUNT");
		custDataMap.put("19", "TOTAL_DELETE_COUNT");
		custDataMap.put("20", "ACTIVE");
		return custDataMap.get(key);
	}
	
    
}
