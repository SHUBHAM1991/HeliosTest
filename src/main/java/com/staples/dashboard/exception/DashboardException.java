package com.staples.dashboard.exception;

/**
 * The Class DashboardException.
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
public class DashboardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message = null;

	/**
	 * Constructor
	 */
	public DashboardException() {
		super();
	}

	/**
	 * Parameterize constructor
	 * @param message
	 */
	public DashboardException(String message) {
		super(message);
		this.message = message;
	}
	
	/**
	 * Parameterize constructor
	 * @param cause
	 */
	public DashboardException(Throwable cause) {
		super(cause);
	}

	/**
	 * Method returning String value
	 * @return message
	 */
	@Override
	public String toString() {
		return message;
	}

	/**
	 * Method returning String value
	 * @return message
	 */
	@Override
	public String getMessage() {
		return message;
	}
}