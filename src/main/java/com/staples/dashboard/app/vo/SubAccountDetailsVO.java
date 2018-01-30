package com.staples.dashboard.app.vo;

/**
 * The Class SubAccountDetailsVO.
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
public class SubAccountDetailsVO {
	private String strSubAccountName;
	private String strChildRwdNumber;
	private String strBarCodeLocation;
	private String strSelected;
	/**
	 * @return the strSubAccountName
	 */
	public String getStrSubAccountName() {
		return strSubAccountName;
	}
	/**
	 * @param strSubAccountName the strSubAccountName to set
	 */
	public void setStrSubAccountName(String strSubAccountName) {
		this.strSubAccountName = strSubAccountName;
	}
	/**
	 * @return the strChildRwdNumber
	 */
	public String getStrChildRwdNumber() {
		return strChildRwdNumber;
	}
	/**
	 * @param strChildRwdNumber the strChildRwdNumber to set
	 */
	public void setStrChildRwdNumber(String strChildRwdNumber) {
		this.strChildRwdNumber = strChildRwdNumber;
	}
	/**
	 * @return the strBarCodeLocation
	 */
	public String getStrBarCodeLocation() {
		return strBarCodeLocation;
	}
	/**
	 * @param strBarCodeLocation the strBarCodeLocation to set
	 */
	public void setStrBarCodeLocation(String strBarCodeLocation) {
		this.strBarCodeLocation = strBarCodeLocation;
	}
	/**
	 * @return the strSelected
	 */
	public String getStrSelected() {
		return strSelected;
	}
	/**
	 * @param strSelected the strSelected to set
	 */
	public void setStrSelected(String strSelected) {
		this.strSelected = strSelected;
	}
}
