package com.staples.dashboard.app.vo;


/**
 * The Class PurchaseDetailsSelectedVO.
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
public class PurchaseDetailsSelectedVO {
	private String selectedMonthYear;
	private String selectedCategory;
	private String selectedSubAcctMbr;
	private String selectedLocation;
	/**
	 * @return the selectedMonthYear
	 */
	public String getSelectedMonthYear() {
		return selectedMonthYear;
	}
	/**
	 * @param selectedMonthYear the selectedMonthYear to set
	 */
	public void setSelectedMonthYear(String selectedMonthYear) {
		this.selectedMonthYear = selectedMonthYear;
	}
	/**
	 * @return the selectedCategory
	 */
	public String getSelectedCategory() {
		return selectedCategory;
	}
	/**
	 * @param selectedCategory the selectedCategory to set
	 */
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	/**
	 * @return the selectedSubAcctMbr
	 */
	public String getSelectedSubAcctMbr() {
		return selectedSubAcctMbr;
	}
	/**
	 * @param selectedSubAcctMbr the selectedSubAcctMbr to set
	 */
	public void setSelectedSubAcctMbr(String selectedSubAcctMbr) {
		this.selectedSubAcctMbr = selectedSubAcctMbr;
	}
	/**
	 * @return the selectedLocation
	 */
	public String getSelectedLocation() {
		return selectedLocation;
	}
	/**
	 * @param selectedLocation the selectedLocation to set
	 */
	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
}
