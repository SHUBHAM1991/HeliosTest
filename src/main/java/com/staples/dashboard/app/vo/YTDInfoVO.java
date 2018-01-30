package com.staples.dashboard.app.vo;

/**
 * The Class YTDInfoVO.
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
public class YTDInfoVO {
	private String year;
	private String ytdSales;
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the ytdSales
	 */
	public String getYtdSales() {
		return ytdSales;
	}
	/**
	 * @param ytdSales the ytdSales to set
	 */
	public void setYtdSales(String ytdSales) {
		this.ytdSales = ytdSales;
	}
}
