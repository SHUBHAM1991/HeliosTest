/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       ParentMenuVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store ParentMenu 
 * 										details available in the Menu
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
 * The Class ParentMenuVO.
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
@XmlRootElement(name="ParentMenu")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentMenuVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Dyn")
	private String dyn;

	@XmlElement(name = "Link")
	private String link;

	@XmlElement(name = "SubInd")
	private String subInd;

	@XmlElement(name = "code")
	private String code;

	@XmlElement(name = "Visible")
	private String visible;

	@XmlElement(name = "SubMenu")
	private List<SubMenuVO> subMenuVOList;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dyn
	 */
	public String getDyn() {
		return dyn;
	}

	/**
	 * @param dyn the dyn to set
	 */
	public void setDyn(String dyn) {
		this.dyn = dyn;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the subInd
	 */
	public String getSubInd() {
		return subInd;
	}

	/**
	 * @param subInd the subInd to set
	 */
	public void setSubInd(String subInd) {
		this.subInd = subInd;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the visible
	 */
	public String getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}

	/**
	 * @return the subMenuVOList
	 */
	public List<SubMenuVO> getSubMenuVOList() {
		return subMenuVOList;
	}

	/**
	 * @param subMenuVOList the subMenuVOList to set
	 */
	public void setSubMenuVOList(List<SubMenuVO> subMenuVOList) {
		this.subMenuVOList = subMenuVOList;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "ParentMenuVO [name=" + name + ", dyn=" + dyn + ", link=" + link
				+ ", subInd=" + subInd + ", code=" + code + ", visible="
				+ visible + ", subMenuVOList=" + subMenuVOList + "]";
	}

}
