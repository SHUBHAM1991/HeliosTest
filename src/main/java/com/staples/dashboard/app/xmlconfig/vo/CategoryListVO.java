/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       CategoryListVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store CategoryList 
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
 * The Class CategoryListVO.
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
@XmlRootElement(name="CategoryList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryListVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "MktItmCatCd")
	private String mktItmCatCd;
	
	@XmlElement(name = "DscPgmId")
	private String dscPgmId;
	
	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "filterCondition")
	private String filterCondition;

	@XmlElement(name = "value")
	private String value;

	@XmlElement(name = "caption")
	private String caption;

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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the filterCondition
	 */
	public String getFilterCondition() {
		return filterCondition;
	}

	/**
	 * @param filterCondition the filterCondition to set
	 */
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
}
