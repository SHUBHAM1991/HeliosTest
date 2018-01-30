/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       RewardsTiersVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store Rewards Tier
 * 										details available in the TierConfig.xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class HeliosTiersVO.
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
@XmlRootElement(name = "RewardsTiers")
@XmlAccessorType(XmlAccessType.FIELD)
public class HeliosTiersVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "Tier")
	private List<TierVO> tierVOList;
	/**
	 * @return the tierVOList
	 */
	public List<TierVO> getTierVOList() {
		return tierVOList;
	}
	/**
	 * @param tierVOList the tierVOList to set
	 */
	public void setTierVOList(List<TierVO> tierVOList) {
		this.tierVOList = tierVOList;
	}

}
