package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SegDetailInfo1 {
	 private String taskIdCombination;
	 private String segDispStatus;
	 private String taskInsertDate;
	 private String taskUpdateDate;
	 private String sfdcSentDate;
	 private String createdBy;
	 private String updatedBy;
	 private String refreshedBy;
	 private List<Comment1> comments = new ArrayList<Comment1>();
	 
	 
	
	public String getTaskInsertDate() {
		return taskInsertDate;
	}
	public void setTaskInsertDate(String taskInsertDate) {
		this.taskInsertDate = taskInsertDate;
	}
	public String getTaskUpdateDate() {
		return taskUpdateDate;
	}
	public void setTaskUpdateDate(String taskUpdateDate) {
		this.taskUpdateDate = taskUpdateDate;
	}
	public String getSfdcSentDate() {
		return sfdcSentDate;
	}
	public void setSfdcSentDate(String sfdcSentDate) {
		this.sfdcSentDate = sfdcSentDate;
	}
	public List<Comment1> getComments() {
		return comments;
	}
	public void setComments(List<Comment1> comments) {
		this.comments = comments;
	}
	public String getTaskIdCombination() {
		return taskIdCombination;
	}
	public void setTaskIdCombination(String taskIdCombination) {
		this.taskIdCombination = taskIdCombination;
	}
	public String getSegDispStatus() {
		return segDispStatus;
	}
	public void setSegDispStatus(String segDispStatus) {
		this.segDispStatus = segDispStatus;
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
}