/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       RewardsCategoriesVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store RewardsCategories 
 * 										details available in the configuration xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class HeliosSummaryRecordsList.
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
@XmlRootElement(name = "RewardsSummaryRecordsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class HeliosSummaryRecordsList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "RewardsSummaryRecordsList")
	private List<HeliosSummaryRecord> rwdsSummaryRecList;
	
	@XmlElement(name = "DYNMSGSTATUSNUM")
	private int DYNMSGSTATUSNUM;
	
	@XmlElement(name = "ROLLOVERSTRTDATE")
	private String ROLLOVERSTRTDATE;
	
	@XmlElement(name = "MINTHRESAMT")
	private double MINTHRESAMT;
	
	@XmlElement(name = "RWDAMT")
	private double RWDAMT;

	/**
	 * @return the rwdsSummaryRecList
	 */
	public List<HeliosSummaryRecord> getRwdsSummaryRecList() {
		return rwdsSummaryRecList;
	}

	/**
	 * @param rwdsSummaryRecList the rwdsSummaryRecList to set
	 */
	public void setRwdsSummaryRecList(List<HeliosSummaryRecord> rwdsSummaryRecList) {
		this.rwdsSummaryRecList = rwdsSummaryRecList;
	}

	/**
	 * @return the dYNMSGSTATUSNUM
	 */
	public int getDYNMSGSTATUSNUM() {
		return DYNMSGSTATUSNUM;
	}

	/**
	 * @param dYNMSGSTATUSNUM the dYNMSGSTATUSNUM to set
	 */
	public void setDYNMSGSTATUSNUM(int dYNMSGSTATUSNUM) {
		DYNMSGSTATUSNUM = dYNMSGSTATUSNUM;
	}

	/**
	 * @return the rOLLOVERSTRTDATE
	 */
	public String getROLLOVERSTRTDATE() {
		return ROLLOVERSTRTDATE;
	}

	/**
	 * @param rOLLOVERSTRTDATE the rOLLOVERSTRTDATE to set
	 */
	public void setROLLOVERSTRTDATE(String rOLLOVERSTRTDATE) {
		ROLLOVERSTRTDATE = rOLLOVERSTRTDATE;
	}

	/**
	 * @return the mINTHRESAMT
	 */
	public double getMINTHRESAMT() {
		return MINTHRESAMT;
	}

	/**
	 * @param mINTHRESAMT the mINTHRESAMT to set
	 */
	public void setMINTHRESAMT(double mINTHRESAMT) {
		MINTHRESAMT = mINTHRESAMT;
	}

	/**
	 * @return the rWDAMT
	 */
	public double getRWDAMT() {
		return RWDAMT;
	}

	/**
	 * @param rWDAMT the rWDAMT to set
	 */
	public void setRWDAMT(double rWDAMT) {
		RWDAMT = rWDAMT;
	}
	
}
