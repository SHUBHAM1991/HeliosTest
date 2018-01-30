package com.staples.dashboard.app.vo;

import java.util.Date;

public class SegmentComments {
	private String taskIdCombination;
	private Date insertDate;
	private String commentText;
	private String loggedUser;
	public String getTaskIdCombination() {
		return taskIdCombination;
	}
	public void setTaskIdCombination(String taskIdCombination) {
		this.taskIdCombination = taskIdCombination;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}
	@Override
	public String toString() {
		return "SegmentComments [taskIdCombination=" + taskIdCombination
				+ ", insertDate=" + insertDate + ", commentText=" + commentText
				+ ", loggedUser=" + loggedUser + "]";
	}	
	
	
}
