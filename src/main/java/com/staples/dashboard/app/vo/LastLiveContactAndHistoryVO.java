package com.staples.dashboard.app.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LastLiveContactAndHistoryVO {

	/** The account id. */
	@JsonProperty(value = "AccountId")
	private String accountId;

	/** The activity date. */
	@JsonProperty(value = "ActivityDate")
	private String activityDate;

	

	/** The description. */
	@JsonProperty(value = "Description")
	private String description;

	
	/** The owner id. */
	@JsonProperty(value = "OwnerId")
	private String ownerId;

	/** The status. */
	@JsonProperty(value = "Status")
	private String status;


	/** The subject. */
	@JsonProperty(value = "Subject")
	private String subject;

	/** The who id. */
	@JsonProperty(value = "WhoId")
	private String whoId;

	
	
	private List<LastLiveContactAndHistoryVO> lastLiveContactHistoryList;

	private YTDInfoVO YTDInfoVO; 
	
	/**
	 * Gets the account id.
	 * 
	 * @return the account id
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 * 
	 * @param accountId
	 *            the new account id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * Gets the activity date.
	 * 
	 * @return the activity date
	 */
	public String getActivityDate() {
		return activityDate;
	}
	
	

	/**
	 * Sets the activity date.
	 * 
	 * @param activityDate
	 *            the new activity date
	 */
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the owner id.
	 * 
	 * @return the owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner id.
	 * 
	 * @param ownerId
	 *            the new owner id
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the who id.
	 * 
	 * @return the who id
	 */
	public String getWhoId() {
		return whoId;
	}

	/**
	 * Sets the who id.
	 * 
	 * @param whoId
	 *            the new who id
	 */
	public void setWhoId(String whoId) {
		this.whoId = whoId;
	}



	public List<LastLiveContactAndHistoryVO> getLastLiveContactHistoryList() {
		return lastLiveContactHistoryList;
	}

	public void setLastLiveContactHistoryList(List<LastLiveContactAndHistoryVO> lastLiveContactHistoryList) {
		this.lastLiveContactHistoryList = lastLiveContactHistoryList;
	}

	
	
	/**
	 * @return the yTDInfoVO
	 */
	public YTDInfoVO getYTDInfoVO() {
		return YTDInfoVO;
	}

	/**
	 * @param yTDInfoVO the yTDInfoVO to set
	 */
	public void setYTDInfoVO(YTDInfoVO yTDInfoVO) {
		YTDInfoVO = yTDInfoVO;
	}

	

}
