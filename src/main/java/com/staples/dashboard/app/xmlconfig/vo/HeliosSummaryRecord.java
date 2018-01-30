/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       Ad.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store Ad 
 * 										details available in the DashboardImages.xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class HeliosSummaryRecord.
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
@XmlRootElement(name="RewardsSummaryRecord")
@XmlAccessorType(XmlAccessType.FIELD)
public class HeliosSummaryRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "DISCPRGID")
	private String DISCPRGID;

	@XmlElement(name = "DISCPRGDESC")
	private String DISCPRGDESC;

	@XmlElement(name = "MKTGITMCATCD")
	private String MKTGITMCATCD;

	@XmlElement(name = "QLFYSPND")
	private Double QLFYSPND;

	@XmlElement(name = "CPNAMT")
	private Double CPNAMT;

	@XmlElement(name = "NETSPND")
	private Double NETSPND;

	@XmlElement(name = "RWRDAMT")
	private Double RWRDAMT;

	@XmlElement(name = "ROLLOVRQLFYSPND")
	private Double ROLLOVRQLFYSPND;
	
	/**
	 * @return the dISCPRGID
	 */
	public String getDISCPRGID() {
		return DISCPRGID;
	}

	/**
	 * @param dISCPRGID the dISCPRGID to set
	 */
	public void setDISCPRGID(String dISCPRGID) {
		DISCPRGID = dISCPRGID;
	}

	/**
	 * @return the dISCPRGDESC
	 */
	public String getDISCPRGDESC() {
		return DISCPRGDESC;
	}

	/**
	 * @param dISCPRGDESC the dISCPRGDESC to set
	 */
	public void setDISCPRGDESC(String dISCPRGDESC) {
		DISCPRGDESC = dISCPRGDESC;
	}

	/**
	 * @return the mKTGITMCATCD
	 */
	public String getMKTGITMCATCD() {
		return MKTGITMCATCD;
	}

	/**
	 * @param mKTGITMCATCD the mKTGITMCATCD to set
	 */
	public void setMKTGITMCATCD(String mKTGITMCATCD) {
		MKTGITMCATCD = mKTGITMCATCD;
	}

	/**
	 * @return the qLFYSPND
	 */
	public Double getQLFYSPND() {
		return QLFYSPND;
	}

	/**
	 * @param qLFYSPND the qLFYSPND to set
	 */
	public void setQLFYSPND(Double qLFYSPND) {
		QLFYSPND = qLFYSPND;
	}

	/**
	 * @return the cPNAMT
	 */
	public Double getCPNAMT() {
		return CPNAMT;
	}

	/**
	 * @param cPNAMT the cPNAMT to set
	 */
	public void setCPNAMT(Double cPNAMT) {
		CPNAMT = cPNAMT;
	}

	/**
	 * @return the nETSPND
	 */
	public Double getNETSPND() {
		return NETSPND;
	}

	/**
	 * @param nETSPND the nETSPND to set
	 */
	public void setNETSPND(Double nETSPND) {
		NETSPND = nETSPND;
	}

	/**
	 * @return the rWRDAMT
	 */
	public Double getRWRDAMT() {
		return RWRDAMT;
	}

	/**
	 * @param rWRDAMT the rWRDAMT to set
	 */
	public void setRWRDAMT(Double rWRDAMT) {
		RWRDAMT = rWRDAMT;
	}

	/**
	 * @return the rOLLOVRQLFYSPND
	 */
	public Double getROLLOVRQLFYSPND() {
		return ROLLOVRQLFYSPND;
	}

	/**
	 * @param rOLLOVRQLFYSPND the rOLLOVRQLFYSPND to set
	 */
	public void setROLLOVRQLFYSPND(Double rOLLOVRQLFYSPND) {
		ROLLOVRQLFYSPND = rOLLOVRQLFYSPND;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "RewardsSummary [DISCPRGID=" + DISCPRGID + ", DISCPRGDESC=" + DISCPRGDESC + ", MKTGITMCATCD=" + MKTGITMCATCD 
				+ ", QLFYSPND="	+ QLFYSPND 	+", CPNAMT=" + CPNAMT +", NETSPND=" + NETSPND +", RWRDAMT=" + RWRDAMT 
				+", ROLLOVRQLFYSPND=" + ROLLOVRQLFYSPND + "]";
	}

}
