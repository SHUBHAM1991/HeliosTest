package com.staples.dashboard.app.vo;

/**
 * The Class SearchVO.
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
public class SearchVO {

	private String searchItems;
	private String orderContact;
	/**
	 * @return the searchItems
	 */
	public String getSearchItems() {
		return searchItems;
	}
	/**
	 * @param searchItems the searchItems to set
	 */
	public void setSearchItems(String searchItems) {
		this.searchItems = searchItems;
	}
	/**
	 * @return the orderContact
	 */
	public String getOrderContact() {
		return orderContact;
	}
	/**
	 * @param orderContact the orderContact to set
	 */
	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}
}
