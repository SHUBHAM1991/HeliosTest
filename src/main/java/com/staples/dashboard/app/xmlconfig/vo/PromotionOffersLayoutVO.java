/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       PromotionalOffersLayoutVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store promotion offer layout 
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
 * The Class PromotionOffersLayoutVO.
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
@XmlRootElement(name="PromotionOffersLayout")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromotionOffersLayoutVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "value")
	private String value;

	@XmlElement(name = "defaultImage")
	private String defaultImage;

	@XmlElement(name = "Image")
	private List<ImageVO> imageVOList;

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
	 * @return the defaultImage
	 */
	public String getDefaultImage() {
		return defaultImage;
	}

	/**
	 * @param defaultImage the defaultImage to set
	 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * @return the imageVOList
	 */
	public List<ImageVO> getImageVOList() {
		return imageVOList;
	}

	/**
	 * @param imageVOList the imageVOList to set
	 */
	public void setImageVOList(List<ImageVO> imageVOList) {
		this.imageVOList = imageVOList;
	}

}
