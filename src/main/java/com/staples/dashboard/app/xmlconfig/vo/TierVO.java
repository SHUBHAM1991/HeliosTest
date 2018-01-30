/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       TierVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store tier 
 * 										details available in the menu.xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.staples.dashboard.app.utilities.ServiceUtils;

/**
 * The Class TierVO.
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
@XmlRootElement(name = "Tier")
@XmlAccessorType(XmlAccessType.FIELD)
public class TierVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "Name")
	private String Name;

	@XmlElement(name = "Parent")
	private String Parent;

	@XmlElement(name = "lblRwdProductsText")
	private String lblRwdProductsText;
	
	@XmlElement(name = "ancDashboardLearnMoreHRef")
	private String ancDashboardLearnMoreHRef;
	
	@XmlElement(name = "div_ytd_savings_baseBgImg")
	private String div_ytd_savings_baseBgImg;
	
	@XmlElement(name = "imgThermoMtrCard")
	private String imgThermoMtrCard;
	
	@XmlElement(name = "bonusRewardImage1")
	private String bonusRewardImage1;
	
	@XmlElement(name = "bonusRewardImage1Url")
	private String bonusRewardImage1Url;
	
	@XmlElement(name = "bonusRewardImage2")
	private String bonusRewardImage2;
	
	@XmlElement(name = "bonusRewardImage2Url")
	private String bonusRewardImage2Url;
	
	@XmlElement(name = "bonusRewardImage3")
	private String bonusRewardImage3;
	
	@XmlElement(name = "bonusRewardImage3Url")
	private String bonusRewardImage3Url;
	
	@XmlElement(name = "myBenefitSummary")
	private String myBenefitSummary;
	
	@XmlElement(name = "bonusRewardImage4")
	private String bonusRewardImage4;
	
	@XmlElement(name = "bonusRewardImage4Url")
	private String bonusRewardImage4Url;
	
	@XmlElement(name = "bonusRewardImage5")
	private String bonusRewardImage5;
	
	@XmlElement(name = "bonusRewardImage5Url")
	private String bonusRewardImage5Url;
	
	@XmlElement(name = "bonusRewardImage6")
	private String bonusRewardImage6;
	
	@XmlElement(name = "bonusRewardImage6Url")
	private String bonusRewardImage6Url;
	
	@XmlElement(name = "dvYTDSavingsDisplay")
	private String dvYTDSavingsDisplay;

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		return Parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		Parent = parent;
	}

	/**
	 * @return the lblRwdProductsText
	 */
	public String getLblRwdProductsText() {
		return lblRwdProductsText;
	}

	/**
	 * @param lblRwdProductsText the lblRwdProductsText to set
	 */
	public void setLblRwdProductsText(String lblRwdProductsText) {
		this.lblRwdProductsText = lblRwdProductsText;
	}

	/**
	 * @return the ancDashboardLearnMoreHRef
	 */
	public String getAncDashboardLearnMoreHRef() {
		return ancDashboardLearnMoreHRef;
	}

	/**
	 * @param ancDashboardLearnMoreHRef the ancDashboardLearnMoreHRef to set
	 */
	public void setAncDashboardLearnMoreHRef(String ancDashboardLearnMoreHRef) {
		this.ancDashboardLearnMoreHRef = ancDashboardLearnMoreHRef;
	}

	/**
	 * @return the div_ytd_savings_baseBgImg
	 */
	public String getDiv_ytd_savings_baseBgImg() {
		return div_ytd_savings_baseBgImg;
	}

	/**
	 * @param div_ytd_savings_baseBgImg the div_ytd_savings_baseBgImg to set
	 */
	public void setDiv_ytd_savings_baseBgImg(String div_ytd_savings_baseBgImg) {
		this.div_ytd_savings_baseBgImg = div_ytd_savings_baseBgImg;
	}

	/**
	 * @return the imgThermoMtrCard
	 */
	public String getImgThermoMtrCard() {
		return imgThermoMtrCard;
	}

	/**
	 * @param imgThermoMtrCard the imgThermoMtrCard to set
	 */
	public void setImgThermoMtrCard(String imgThermoMtrCard) {
		this.imgThermoMtrCard = imgThermoMtrCard;
	}

	/**
	 * @return the bonusRewardImage1
	 */
	public String getBonusRewardImage1() {
		if (null != this.bonusRewardImage1 && !this.bonusRewardImage1.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage1;
		}
		return this.bonusRewardImage1;
	}

	/**
	 * @param bonusRewardImage1 the bonusRewardImage1 to set
	 */
	public void setBonusRewardImage1(String bonusRewardImage1) {
		this.bonusRewardImage1 = bonusRewardImage1;
	}

	/**
	 * @return the bonusRewardImage1Url
	 */
	public String getBonusRewardImage1Url() {
		return bonusRewardImage1Url;
	}

	/**
	 * @param bonusRewardImage1Url the bonusRewardImage1Url to set
	 */
	public void setBonusRewardImage1Url(String bonusRewardImage1Url) {
		this.bonusRewardImage1Url = bonusRewardImage1Url;
	}

	/**
	 * @return the bonusRewardImage2
	 */
	public String getBonusRewardImage2() {
		if (null != this.bonusRewardImage2 && !this.bonusRewardImage2.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage2;
		}
		return this.bonusRewardImage2;
	}

	/**
	 * @param bonusRewardImage2 the bonusRewardImage2 to set
	 */
	public void setBonusRewardImage2(String bonusRewardImage2) {
		this.bonusRewardImage2 = bonusRewardImage2;
	}

	/**
	 * @return the bonusRewardImage2Url
	 */
	public String getBonusRewardImage2Url() {
		return bonusRewardImage2Url;
	}

	/**
	 * @param bonusRewardImage2Url the bonusRewardImage2Url to set
	 */
	public void setBonusRewardImage2Url(String bonusRewardImage2Url) {
		this.bonusRewardImage2Url = bonusRewardImage2Url;
	}

	/**
	 * @return the bonusRewardImage3
	 */
	public String getBonusRewardImage3() {
		if (null != this.bonusRewardImage3 && !this.bonusRewardImage3.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage3;
		}
		return this.bonusRewardImage3;
	}

	/**
	 * @param bonusRewardImage3 the bonusRewardImage3 to set
	 */
	public void setBonusRewardImage3(String bonusRewardImage3) {
		this.bonusRewardImage3 = bonusRewardImage3;
	}

	/**
	 * @return the bonusRewardImage3Url
	 */
	public String getBonusRewardImage3Url() {
		return bonusRewardImage3Url;
	}

	/**
	 * @param bonusRewardImage3Url the bonusRewardImage3Url to set
	 */
	public void setBonusRewardImage3Url(String bonusRewardImage3Url) {
		this.bonusRewardImage3Url = bonusRewardImage3Url;
	}

	/**
	 * @return the myBenefitSummary
	 */
	public String getMyBenefitSummary() {
		return myBenefitSummary;
	}

	/**
	 * @param myBenefitSummary the myBenefitSummary to set
	 */
	public void setMyBenefitSummary(String myBenefitSummary) {
		this.myBenefitSummary = myBenefitSummary;
	}

	/**
	 * @return the bonusRewardImage4
	 */
	public String getBonusRewardImage4() {
		if (null != this.bonusRewardImage4 && !this.bonusRewardImage4.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage4;
		}
		return this.bonusRewardImage4;
	}

	/**
	 * @param bonusRewardImage4 the bonusRewardImage4 to set
	 */
	public void setBonusRewardImage4(String bonusRewardImage4) {
		this.bonusRewardImage4 = bonusRewardImage4;
	}

	/**
	 * @return the bonusRewardImage4Url
	 */
	public String getBonusRewardImage4Url() {
		return bonusRewardImage4Url;
	}

	/**
	 * @param bonusRewardImage4Url the bonusRewardImage4Url to set
	 */
	public void setBonusRewardImage4Url(String bonusRewardImage4Url) {
		this.bonusRewardImage4Url = bonusRewardImage4Url;
	}

	/**
	 * @return the bonusRewardImage5
	 */
	public String getBonusRewardImage5() {
		if (null != this.bonusRewardImage5 && !this.bonusRewardImage5.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage5;
		}
		return this.bonusRewardImage5;
	}

	/**
	 * @param bonusRewardImage5 the bonusRewardImage5 to set
	 */
	public void setBonusRewardImage5(String bonusRewardImage5) {
		this.bonusRewardImage5 = bonusRewardImage5;
	}

	/**
	 * @return the bonusRewardImage5Url
	 */
	public String getBonusRewardImage5Url() {
		return bonusRewardImage5Url;
	}

	/**
	 * @param bonusRewardImage5Url the bonusRewardImage5Url to set
	 */
	public void setBonusRewardImage5Url(String bonusRewardImage5Url) {
		this.bonusRewardImage5Url = bonusRewardImage5Url;
	}

	/**
	 * @return the bonusRewardImage6
	 */
	public String getBonusRewardImage6() {
		if (null != this.bonusRewardImage6 && !this.bonusRewardImage6.isEmpty()) {
			return ServiceUtils.appName + this.bonusRewardImage6;
		}
		return this.bonusRewardImage6;
	}

	/**
	 * @param bonusRewardImage6 the bonusRewardImage6 to set
	 */
	public void setBonusRewardImage6(String bonusRewardImage6) {
		this.bonusRewardImage6 = bonusRewardImage6;
	}

	/**
	 * @return the bonusRewardImage6Url
	 */
	public String getBonusRewardImage6Url() {
		return bonusRewardImage6Url;
	}

	/**
	 * @param bonusRewardImage6Url the bonusRewardImage6Url to set
	 */
	public void setBonusRewardImage6Url(String bonusRewardImage6Url) {
		this.bonusRewardImage6Url = bonusRewardImage6Url;
	}

	/**
	 * @return the dvYTDSavingsDisplay
	 */
	public String getDvYTDSavingsDisplay() {
		return dvYTDSavingsDisplay;
	}

	/**
	 * @param dvYTDSavingsDisplay the dvYTDSavingsDisplay to set
	 */
	public void setDvYTDSavingsDisplay(String dvYTDSavingsDisplay) {
		this.dvYTDSavingsDisplay = dvYTDSavingsDisplay;
	}

}
