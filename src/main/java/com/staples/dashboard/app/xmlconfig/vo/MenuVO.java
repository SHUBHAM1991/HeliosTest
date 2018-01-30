/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       MenuVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store menu 
 * 										details available in the Menu.xml
 * .xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class MenuVO.
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
@XmlRootElement(name="Menu")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "TierCode")
	private String tierCode;

	@XmlElement(name = "ParentMenu")
	private List<ParentMenuVO> parentMenuVOList;

	/**
	 * @return the tierCode
	 */
	public String getTierCode() {
		return tierCode;
	}

	/**
	 * @param tierCode the tierCode to set
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	/**
	 * @return the parentMenuVOList
	 */
	public List<ParentMenuVO> getParentMenuVOList() {
		return parentMenuVOList;
	}

	/**
	 * @param parentMenuVOList the parentMenuVOList to set
	 */
	public void setParentMenuVOList(List<ParentMenuVO> parentMenuVOList) {
		this.parentMenuVOList = parentMenuVOList;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "Parent Menu [Parent Menu size=" + parentMenuVOList.size() + "]";
	}
}
