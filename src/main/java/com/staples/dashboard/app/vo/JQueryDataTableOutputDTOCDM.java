package com.staples.dashboard.app.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * The Class JQueryDataTableOutputDTO.
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
public class JQueryDataTableOutputDTOCDM implements Serializable {

	private static final long serialVersionUID = -3073325628020792777L;

	private List<CdmCustProfileVO> aaData;
	
	private int iTotalRecords;

	private int iTotalDisplayRecords;

	private int sEcho;
	
	private String unSelListOfSubPlay;
	
	//private List<String> timeZoneList;
	private Map<String,String> timeZoneMap;
	/**
	 * @return the aaData
	 */
	public List<CdmCustProfileVO> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<CdmCustProfileVO> aaData) {
		this.aaData = aaData;
	}

	/**
	 * @return the iTotalRecords
	 */
	public int getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return the iTotalDisplayRecords
	 */
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return the sEcho
	 */
	public int getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho the sEcho to set
	 */
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return the unSelListOfSubPlay
	 */
	public String getUnSelListOfSubPlay() {
		return unSelListOfSubPlay;
	}

	/**
	 * @param unSelListOfSubPlay the unSelListOfSubPlay to set
	 */
	public void setUnSelListOfSubPlay(String unSelListOfSubPlay) {
		this.unSelListOfSubPlay = unSelListOfSubPlay;
	}

	public Map<String, String> getTimeZoneMap() {
		return timeZoneMap;
	}

	public void setTimeZoneMap(Map<String, String> timeZoneMap) {
		this.timeZoneMap = timeZoneMap;
	}
	
	
}
