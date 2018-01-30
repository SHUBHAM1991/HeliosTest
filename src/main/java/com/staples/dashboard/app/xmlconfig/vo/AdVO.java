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

import com.staples.dashboard.app.utilities.ServiceUtils;


/**
 * The Class AdVO.
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
@XmlRootElement(name="Ad")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Keyword")
	private String keyword;

	@XmlElement(name = "ImageUrl")
	private String imageUrl;

	@XmlElement(name = "AlternateText")
	private String alternateText;
	
	@XmlElement(name = "Display")
	private String display;

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the alternateText
	 */
	public String getAlternateText() {
		return alternateText;
	}

	/**
	 * @param alternateText the alternateText to set
	 */
	public void setAlternateText(String alternateText) {
		this.alternateText = alternateText;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		if(null != this.imageUrl && !this.imageUrl.isEmpty()) {
			return ServiceUtils.appName + this.imageUrl;
		}
		return this.imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "Ad [keyword=" + keyword + ", imageUrl=" + imageUrl
				+ ", alternateText="
				+ alternateText + ", Display=" + display + "]";
	}

}
