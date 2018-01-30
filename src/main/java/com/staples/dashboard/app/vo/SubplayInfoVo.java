package com.staples.dashboard.app.vo;

/**
 * The Class SubplayInfoVo.
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
public class SubplayInfoVo {

	private String playSegDesc;
	private String playSegType;
	private String playSegId;
	
	/**
	 * @return
	 */
	public String getPlaySegId() {
		return playSegId;
	}
	public void setPlaySegId(String playSegId) {
		this.playSegId = playSegId;
	}
	/**
	 * @return the playSegDesc
	 */
	/**
	 * @return
	 */
	public String getPlaySegDesc() {
		return playSegDesc;
	}
	/**
	 * @param playSegDesc the playSegDesc to set
	 */
	public void setPlaySegDesc(String playSegDesc) {
		this.playSegDesc = playSegDesc;
	}
	/**
	 * @return the playSegType
	 */
	public String getPlaySegType() {
		return playSegType;
	}
	/**
	 * @param playSegType the playSegType to set
	 */
	public void setPlaySegType(String playSegType) {
		this.playSegType = playSegType;
	}
}
