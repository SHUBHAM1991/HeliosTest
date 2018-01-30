package com.staples.dashboard.app.vo;

import java.io.Serializable;

/**
 * The Class JQueryDataTableInputDTO.
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
 *  <td>1.0</td><td>Dec 14, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class JQueryDataTableInputDTO implements Serializable {

	private static final long serialVersionUID = -87273014672997231L;

	private int iDisplayStart;

	private int iDisplayLength;

	private String sSearch;

	private int iSortingCols;

	private String iSortCol_0;

	private String sSortDir_0;

	private String sSearch_2;
	
	/*private Boolean statusSort = false;
	
	private String statusSortOrder;

	
	

	public Boolean getStatusSort() {
		return statusSort;
	}

	public void setStatusSort(Boolean statusSort) {
		this.statusSort = statusSort;
	}

	public String getStatusSortOrder() {
		return statusSortOrder;
	}

	public void setStatusSortOrder(String statusSortOrder) {
		this.statusSortOrder = statusSortOrder;
	}*/
	

	/**
	 * @return the iDisplayStart
	 */
	public int getiDisplayStart() {
		return iDisplayStart;
	}

	/**
	 * @param iDisplayStart the iDisplayStart to set
	 */
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	/**
	 * @return the iDisplayLength
	 */
	public int getiDisplayLength() {
		return iDisplayLength;
	}

	/**
	 * @param iDisplayLength the iDisplayLength to set
	 */
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	/**
	 * @return the sSearch
	 */
	public String getsSearch() {
		return sSearch;
	}

	/**
	 * @param sSearch the sSearch to set
	 */
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	/**
	 * @return the iSortingCols
	 */
	public int getiSortingCols() {
		return iSortingCols;
	}

	/**
	 * @param iSortingCols the iSortingCols to set
	 */
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	/**
	 * @return the iSortCol_0
	 */
	public String getiSortCol_0() {
		return iSortCol_0;
	}

	/**
	 * @param iSortCol_0 the iSortCol_0 to set
	 */
	public void setiSortCol_0(String iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	/**
	 * @return the sSortDir_0
	 */
	public String getsSortDir_0() {
		return sSortDir_0;
	}

	/**
	 * @param sSortDir_0 the sSortDir_0 to set
	 */
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	/**
	 * @return the sSearch_2
	 */
	public String getsSearch_2() {
		return sSearch_2;
	}

	/**
	 * @param sSearch_2 the sSearch_2 to set
	 */
	public void setsSearch_2(String sSearch_2) {
		this.sSearch_2 = sSearch_2;
	}
	
	
}
