/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       Category.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store category 
 * 										details available in the configuration xml 
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
 * The Class CategoryVO.
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
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "MktItmCatCd")
	private String mktItmCatCd;

	@XmlElement(name = "DscPgmId")
	private String dscPgmId;

	@XmlElement(name = "IconId")
	private String iconId;
	
	@XmlElement(name = "src")
	private String src;

	@XmlElement(name = "alt")
	private String alt;

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
	 * @return the mktItmCatCd
	 */
	public String getMktItmCatCd() {
		return mktItmCatCd;
	}

	/**
	 * @param mktItmCatCd the mktItmCatCd to set
	 */
	public void setMktItmCatCd(String mktItmCatCd) {
		this.mktItmCatCd = mktItmCatCd;
	}

	/**
	 * @return the dscPgmId
	 */
	public String getDscPgmId() {
		return dscPgmId;
	}

	/**
	 * @param dscPgmId the dscPgmId to set
	 */
	public void setDscPgmId(String dscPgmId) {
		this.dscPgmId = dscPgmId;
	}

	/**
	 * @return the iconId
	 */
	public String getIconId() {
		return iconId;
	}

	/**
	 * @param iconId the iconId to set
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}
	
}
