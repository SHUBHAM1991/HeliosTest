/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       YTDSummaryLayoutVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store year to date summary layout 
 * 										details available in the configuration xml
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
 * The Class YTDSummaryLayoutVO.
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
@XmlRootElement(name="YTDSummaryLayout")
@XmlAccessorType(XmlAccessType.FIELD)
public class YTDSummaryLayoutVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "value")
	private String value;

	@XmlElement(name = "type")
	private String type;

	@XmlElement(name = "CategoryList")
	private List<CategoryListVO> categoryListVO;

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
	 * @return the categoryListVO
	 */
	public List<CategoryListVO> getCategoryListVO() {
		return categoryListVO;
	}

	/**
	 * @param categoryListVO the categoryListVO to set
	 */
	public void setCategoryListVO(List<CategoryListVO> categoryListVO) {
		this.categoryListVO = categoryListVO;
	}

}
