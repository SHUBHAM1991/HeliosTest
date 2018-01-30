/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName				:	ServiceException.java
 * Author				:	Staples
 * Date of Creation		:	11-FEB-2014
 * Description			:	This class defines as base class for all 
 							service exception
 * Version No			: 	1.0
 * Modification History	:	
 * Date				Version No				Who						Description
 * 
 */

package com.staples.dashboard.app.utilities.exception;

/**
 * The Class ServiceException.
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
public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int exceptionID;
	private String exceptionMessage;
	private Exception thrownException;
	
	/** 
	 * 
	 * @param int exception ID
	 */
	public ServiceException(int exceptionID) {
		this.exceptionID = exceptionID;
	}
	
	/** 
	 * 
	 * @param int exceptionID
	 * @param String exceptionMessage
	 */
	public ServiceException(int exceptionID, String exceptionMessage) {
		this.exceptionID = exceptionID;
		this.exceptionMessage = exceptionMessage;
	}
	
	/** 
	 * 
	 * @param int exceptionID
	 * @param Exception exception
	 */
	public ServiceException(int exceptionID, Exception thrownException) {
		this.exceptionID = exceptionID;
		this.thrownException = thrownException;
	}
	
	/** 
	 * 
	 * @param String exceptionMessage
	 * @param Exception exception
	 */
	public ServiceException(String exceptionMessage, Exception thrownException) {
		this.exceptionMessage = exceptionMessage;
		this.thrownException = thrownException;
	}
	
	/** 
	 * 
	 * @param int exceptionID
	 * @param String exceptionMessage
	 * @param Exception exception
	 */
	public ServiceException(int exceptionID, String exceptionMessage, 
							Exception thrownException) {
		
		this.exceptionID = exceptionID;
		this.exceptionMessage = exceptionMessage;
		this.thrownException = thrownException;
	}
	
	/**
	 * 
	 * @return the exceptionID
	 */
	public int getExceptionID() {
		return exceptionID;
	}

	/**
	 * 
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * 
	 * @return the thrownException
	 */
	public Exception getThrownException() {
		return thrownException;
	}

}
