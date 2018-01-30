/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       SummaryLayoutVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store summary layout 
 * 										details available in the configuration xml 
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
 * The Class SummaryLayoutVO.
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
@XmlRootElement(name="SummaryLayout")
@XmlAccessorType(XmlAccessType.FIELD)
public class SummaryLayoutVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "value")
	private String value;

	@XmlElement(name = "type")
	private String type;

	@XmlElement(name = "CategoryList")
	private List<CategoryListVO> categoryListVOList;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the categoryListVOList
	 */
	public List<CategoryListVO> getCategoryListVOList() {
		return categoryListVOList;
	}

	/**
	 * @param categoryListVOList the categoryListVOList to set
	 */
	public void setCategoryListVOList(List<CategoryListVO> categoryListVOList) {
		this.categoryListVOList = categoryListVOList;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "Categories [Category size=" + categoryListVOList.size() + "]";
	}
}
