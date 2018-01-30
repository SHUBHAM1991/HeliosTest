package com.staples.dashboard.app.sfdc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Task.
 */
/**
 * @author kumbi002
 *
 */
/**
 * @author kumbi002
 * 
 */
/**
 * @author kumbi002
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

	/** The account id. */
	@JsonProperty(value = "AccountId")
	private String accountId;

	/** The activity date. */
	@JsonProperty(value = "ActivityDate")
	private String activityDate;

	/** The call disposition. */
	@JsonProperty(value = "CallDisposition")
	private String callDisposition;

	/** The call type. */
	@JsonProperty(value = "Call_Mode__c")
	private String call_Mode__c;

	/** The connection received id. */
	@JsonProperty(value = "ConnectionReceivedId")
	private String connectionReceivedId;

	/** The connection sent id. */
	@JsonProperty(value = "ConnectionSentId")
	private String connectionSentId;

	/** The description. */
	@JsonProperty(value = "Description")
	private String description;

	/** The record type id. */
	@JsonProperty(value = "RecordTypeId")
	private String recordTypeId;

	/** The owner id. */
	@JsonProperty(value = "OwnerId")
	private String ownerId;

	/** The status. */
	@JsonProperty(value = "Status")
	private String status;

	/** The priority. */
	@JsonProperty(value = "Priority")
	private String priority;

	/** The type. */
	@JsonProperty(value = "Type")
	private String type;

	/** The subject. */
	@JsonProperty(value = "Subject")
	private String subject;

	/** The who id. */
	@JsonProperty(value = "WhoId")
	private String whoId;

	/** The what id. */
	@JsonProperty(value = "WhatId")
	private String whatId;
	
	@JsonProperty(value = "Topic__c")
	private String topic__c;

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
	 * Gets the call disposition.
	 * 
	 * @return the call disposition
	 */
	public String getCallDisposition() {
		return callDisposition;
	}

	/**
	 * Sets the call disposition.
	 * 
	 * @param callDisposition
	 *            the new call disposition
	 */
	public void setCallDisposition(String callDisposition) {
		this.callDisposition = callDisposition;
	}

	/**
	 * @return
	 */
	public String getCall_Mode__c() {
		return call_Mode__c;
	}

	/**
	 * @param call_Mode__c
	 */
	public void setCall_Mode__c(String call_Mode__c) {
		this.call_Mode__c = call_Mode__c;
	}

	/**
	 * Gets the connection received id.
	 * 
	 * @return the connection received id
	 */
	public String getConnectionReceivedId() {
		return connectionReceivedId;
	}

	/**
	 * Sets the connection received id.
	 * 
	 * @param connectionReceivedId
	 *            the new connection received id
	 */
	public void setConnectionReceivedId(String connectionReceivedId) {
		this.connectionReceivedId = connectionReceivedId;
	}

	/**
	 * Gets the connection sent id.
	 * 
	 * @return the connection sent id
	 */
	public String getConnectionSentId() {
		return connectionSentId;
	}

	/**
	 * Sets the connection sent id.
	 * 
	 * @param connectionSentId
	 *            the new connection sent id
	 */
	public void setConnectionSentId(String connectionSentId) {
		this.connectionSentId = connectionSentId;
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
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 * 
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * Gets the what id.
	 * 
	 * @return the what id
	 */
	public String getWhatId() {
		return whatId;
	}

	/**
	 * Sets the what id.
	 * 
	 * @param whatId
	 *            the new what id
	 */
	public void setWhatId(String whatId) {
		this.whatId = whatId;
	}

	/**
	 * Gets the record type id.
	 * 
	 * @return the record type id
	 */
	public String getRecordTypeId() {
		return recordTypeId;
	}

	/**
	 * Sets the record type id.
	 * 
	 * @param recordTypeId
	 *            the new record type id
	 */
	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getTopic__c() {
		return topic__c;
	}

	public void setTopic__c(String topic__c) {
		this.topic__c = topic__c;
	}
	
	

}
