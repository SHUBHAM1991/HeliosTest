/**
 * 
 */
package com.staples.dashboard.dto;

/**
 * The Class AddReceipt.
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
public class AddReceipt {
	private static final long serialVersionUID = 1L;
	private String insertFlag;
	
	/**
	 * @return the insertFlag
	 */
	public String getInsertFlag() {
		return insertFlag;
	}
	
	/**
	 * @param insertFlag the insertFlag to set
	 */
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}
}
