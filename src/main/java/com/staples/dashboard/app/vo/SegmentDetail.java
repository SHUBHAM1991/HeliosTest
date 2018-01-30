package com.staples.dashboard.app.vo;

import java.util.Date;

public class SegmentDetail {

	private String taskIdCombination;
	private String linkTask;
	private String customerNumber;
	private String divison;
	private String ctaSegment;
	private String ctaSubSegment;
	private String fyrFprdFwkFdy;
	private String segDispStatus;
	private Date taskInsertDate;
	private Date taskUpdateDate;
	private Date sfdcSentDate;
	private String createdBy;
	private String updatedBy;
	private String refreshedBy;
	private String sfdcTaskId;
	private String frequency;
	private String contactId;
	private String sfdcContactFullName;
	private String subject;
	private String repSFDCId;
	
	
	public String getTaskIdCombination() {
		return taskIdCombination;
	}
	public void setTaskIdCombination(String taskIdCombination) {
		this.taskIdCombination = taskIdCombination;
	}
	public String getLinkTask() {
		return linkTask;
	}
	public void setLinkTask(String linkTask) {
		this.linkTask = linkTask;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getDivison() {
		return divison;
	}
	public void setDivison(String divison) {
		this.divison = divison;
	}
	public String getCtaSegment() {
		return ctaSegment;
	}
	public void setCtaSegment(String ctaSegment) {
		this.ctaSegment = ctaSegment;
	}
	public String getCtaSubSegment() {
		return ctaSubSegment;
	}
	public void setCtaSubSegment(String ctaSubSegment) {
		this.ctaSubSegment = ctaSubSegment;
	}
	public String getFyrFprdFwkFdy() {
		return fyrFprdFwkFdy;
	}
	public void setFyrFprdFwkFdy(String fyrFprdFwkFdy) {
		this.fyrFprdFwkFdy = fyrFprdFwkFdy;
	}
	public String getSegDispStatus() {
		return segDispStatus;
	}
	public void setSegDispStatus(String segDispStatus) {
		this.segDispStatus = segDispStatus;
	}
	public Date getTaskInsertDate() {
		return taskInsertDate;
	}
	public void setTaskInsertDate(Date taskInsertDate) {
		this.taskInsertDate = taskInsertDate;
	}
	public Date getTaskUpdateDate() {
		return taskUpdateDate;
	}
	public void setTaskUpdateDate(Date taskUpdateDate) {
		this.taskUpdateDate = taskUpdateDate;
	}
	public Date getSfdcSentDate() {
		return sfdcSentDate;
	}
	public void setSfdcSentDate(Date sfdcSentDate) {
		this.sfdcSentDate = sfdcSentDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getRefreshedBy() {
		return refreshedBy;
	}
	public void setRefreshedBy(String refreshedBy) {
		this.refreshedBy = refreshedBy;
	}
	
	public String getSfdcTaskId() {
		return sfdcTaskId;
	}
	public void setSfdcTaskId(String sfdcTaskId) {
		this.sfdcTaskId = sfdcTaskId;
	}
	@Override
	public String toString() {
		return "SegmentDetail [taskIdCombination=" + taskIdCombination
				+ ", linkTask=" + linkTask + ", customerNumber="
				+ customerNumber + ", divison=" + divison + ", ctaSegment="
				+ ctaSegment + ", ctaSubSegment=" + ctaSubSegment
				+ ", fyrFprdFwkFdy=" + fyrFprdFwkFdy + ", segDispStatus="
				+ segDispStatus + ", taskInsertDate=" + taskInsertDate
				+ ", taskUpdateDate=" + taskUpdateDate + ", sfdcSentDate="
				+ sfdcSentDate + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", refreshedBy=" + refreshedBy +", sfdcTaskId="+sfdcTaskId+ "]";
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the sfdcContactFullName
	 */
	public String getSfdcContactFullName() {
		return sfdcContactFullName;
	}
	/**
	 * @param sfdcContactFullName the sfdcContactFullName to set
	 */
	public void setSfdcContactFullName(String sfdcContactFullName) {
		this.sfdcContactFullName = sfdcContactFullName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRepSFDCId() {
		return repSFDCId;
	}
	public void setRepSFDCId(String repSFDCId) {
		this.repSFDCId = repSFDCId;
	}
	
	
	
}
