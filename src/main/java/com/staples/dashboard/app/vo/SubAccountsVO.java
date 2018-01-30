package com.staples.dashboard.app.vo;


import java.util.ArrayList;
import java.util.List;

/**
 * The Class SubAccountsVO.
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
public class SubAccountsVO {
	List<SubAccountDetailsVO> lstSubAccountDetailsVO = new ArrayList<SubAccountDetailsVO>();

	/**
	 * @return the lstSubAccountDetailsVO
	 */
	public List<SubAccountDetailsVO> getLstSubAccountDetailsVO() {
		return lstSubAccountDetailsVO;
	}

	/**
	 * @param lstSubAccountDetailsVO the lstSubAccountDetailsVO to set
	 */
	public void setLstSubAccountDetailsVO(
			List<SubAccountDetailsVO> lstSubAccountDetailsVO) {
		this.lstSubAccountDetailsVO = lstSubAccountDetailsVO;
	}
}
