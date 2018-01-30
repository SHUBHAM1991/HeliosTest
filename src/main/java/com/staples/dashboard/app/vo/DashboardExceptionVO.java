package com.staples.dashboard.app.vo;

import java.io.Serializable;

/**
 * The Class DashboardExceptionVO.
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
 *  <td>1.0</td><td>Dec 22, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class DashboardExceptionVO implements Serializable {

	private boolean isException = true;
	
	private String message = "An error occurred while servicing your request. Please contact Administrator";

	/**
	 * The default constructor. Sets the default error message.
	 */
	public DashboardExceptionVO() {
		isException = true;
		message = "An error occurred while servicing your request. Please try again or contact Administrator for support";
	}
	
	/**
	 * This constructor sets the given custom error message.
	 * @param isException
	 * @param message
	 */
	public DashboardExceptionVO(boolean isException , String message) {
		this.isException = isException;
		this.message = message;
	}
	
	/**
	 * @return
	 */
	public boolean isException() {
		return isException;
	}

	/**
	 * @param isException
	 */
	public void setException(boolean isException) {
		this.isException = isException;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
