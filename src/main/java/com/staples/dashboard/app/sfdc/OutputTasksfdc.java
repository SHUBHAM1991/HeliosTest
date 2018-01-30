/**
 * OutputTasksfdc.java
 * KumBi002
 * Dec 2, 2016
 */
package com.staples.dashboard.app.sfdc;

// TODO: Auto-generated Javadoc
/**
 * The Class OutputTasksfdc.
 *
 * @author KumBi002
 */
public class OutputTasksfdc {

	/** The task id. */
	private String taskId;
	
	/** The status. */
	private String status;
	
	private String repSFDCId;
	
	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public String getTaskId() {
		return taskId;
	}
	
	/**
	 * Sets the task id.
	 *
	 * @param taskId the new task id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRepSFDCId() {
		return repSFDCId;
	}

	public void setRepSFDCId(String repSFDCId) {
		this.repSFDCId = repSFDCId;
	}
	
}
