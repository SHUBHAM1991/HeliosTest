/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       TierLayoutVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store TierLayout 
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
 * The Class TierLayoutVO.
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
@XmlRootElement(name="TierLayout")
@XmlAccessorType(XmlAccessType.FIELD)
public class TierLayoutVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "tierCode")
	private String tierCode;

	@XmlElement(name = "M")
	private String monthly;

	@XmlElement(name = "I")
	private String ink;

	@XmlElement(name = "PB")
	private String promotionalBanners;

	@XmlElement(name = "RS")
	private String rewardsSummary;

	@XmlElement(name = "IS")
	private String inkRewardsSummary;

	@XmlElement(name = "RC")
	private String rewardCategory;

	@XmlElement(name = "PC")
	private String productCategory;

	@XmlElement(name = "YTD")
	private String yearToDate;

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
	 * @return the monthly
	 */
	public String getMonthly() {
		return monthly;
	}

	/**
	 * @param monthly the monthly to set
	 */
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	/**
	 * @return the ink
	 */
	public String getInk() {
		return ink;
	}

	/**
	 * @param ink the ink to set
	 */
	public void setInk(String ink) {
		this.ink = ink;
	}

	/**
	 * @return the promotionalBanners
	 */
	public String getPromotionalBanners() {
		return promotionalBanners;
	}

	/**
	 * @param promotionalBanners the promotionalBanners to set
	 */
	public void setPromotionalBanners(String promotionalBanners) {
		this.promotionalBanners = promotionalBanners;
	}

	/**
	 * @return the rewardsSummary
	 */
	public String getRewardsSummary() {
		return rewardsSummary;
	}

	/**
	 * @param rewardsSummary the rewardsSummary to set
	 */
	public void setRewardsSummary(String rewardsSummary) {
		this.rewardsSummary = rewardsSummary;
	}

	/**
	 * @return the inkRewardsSummary
	 */
	public String getInkRewardsSummary() {
		return inkRewardsSummary;
	}

	/**
	 * @param inkRewardsSummary the inkRewardsSummary to set
	 */
	public void setInkRewardsSummary(String inkRewardsSummary) {
		this.inkRewardsSummary = inkRewardsSummary;
	}

	/**
	 * @return the rewardCategory
	 */
	public String getRewardCategory() {
		return rewardCategory;
	}

	/**
	 * @param rewardCategory the rewardCategory to set
	 */
	public void setRewardCategory(String rewardCategory) {
		this.rewardCategory = rewardCategory;
	}

	/**
	 * @return the productCategory
	 */
	public String getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * @return the yearToDate
	 */
	public String getYearToDate() {
		return yearToDate;
	}

	/**
	 * @param yearToDate the yearToDate to set
	 */
	public void setYearToDate(String yearToDate) {
		this.yearToDate = yearToDate;
	}

}
