/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       StatementCheckVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store statementcheck 
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
 * The Class StatementCheckVO.
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
@XmlRootElement(name="StatementCheck")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatementCheckVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "TierLayout")
	private List<TierLayoutVO> tierLayoutVOList;

	@XmlElement(name = "YTDSummaryLayout")
	private List<YTDSummaryLayoutVO> ytdSummaryLayoutList;

	@XmlElement(name = "SummaryLayout")
	private List<SummaryLayoutVO> summaryLayoutVOList;

	@XmlElement(name = "PromotionalBanners")
	private List<PromotionalBannersVO> promotionalBannersVOList;

	@XmlElement(name = "PromotionOffersLayout")
	private List<PromotionOffersLayoutVO> promotionalOffersLayoutVOList;

	@XmlElement(name = "Categories")
	private List<CategoriesVO> categoriesVOList;

	/**
	 * @return the tierLayoutVOList
	 */
	public List<TierLayoutVO> getTierLayoutVOList() {
		return tierLayoutVOList;
	}

	/**
	 * @param tierLayoutVOList the tierLayoutVOList to set
	 */
	public void setTierLayoutVOList(List<TierLayoutVO> tierLayoutVOList) {
		this.tierLayoutVOList = tierLayoutVOList;
	}

	/**
	 * @return the ytdSummaryLayoutList
	 */
	public List<YTDSummaryLayoutVO> getYtdSummaryLayoutList() {
		return ytdSummaryLayoutList;
	}

	/**
	 * @param ytdSummaryLayoutList the ytdSummaryLayoutList to set
	 */
	public void setYtdSummaryLayoutList(
			List<YTDSummaryLayoutVO> ytdSummaryLayoutList) {
		this.ytdSummaryLayoutList = ytdSummaryLayoutList;
	}

	/**
	 * @return the summaryLayoutVOList
	 */
	public List<SummaryLayoutVO> getSummaryLayoutVOList() {
		return summaryLayoutVOList;
	}

	/**
	 * @param summaryLayoutVOList the summaryLayoutVOList to set
	 */
	public void setSummaryLayoutVOList(List<SummaryLayoutVO> summaryLayoutVOList) {
		this.summaryLayoutVOList = summaryLayoutVOList;
	}

	/**
	 * @return the promotionalBannersVOList
	 */
	public List<PromotionalBannersVO> getPromotionalBannersVOList() {
		return promotionalBannersVOList;
	}

	/**
	 * @param promotionalBannersVOList the promotionalBannersVOList to set
	 */
	public void setPromotionalBannersVOList(
			List<PromotionalBannersVO> promotionalBannersVOList) {
		this.promotionalBannersVOList = promotionalBannersVOList;
	}

	/**
	 * @return the promotionalOffersLayoutVOList
	 */
	public List<PromotionOffersLayoutVO> getPromotionalOffersLayoutVOList() {
		return promotionalOffersLayoutVOList;
	}

	/**
	 * @param promotionalOffersLayoutVOList the promotionalOffersLayoutVOList to set
	 */
	public void setPromotionalOffersLayoutVOList(
			List<PromotionOffersLayoutVO> promotionalOffersLayoutVOList) {
		this.promotionalOffersLayoutVOList = promotionalOffersLayoutVOList;
	}

	/**
	 * @return the categoriesVOList
	 */
	public List<CategoriesVO> getCategoriesVOList() {
		return categoriesVOList;
	}

	/**
	 * @param categoriesVOList the categoriesVOList to set
	 */
	public void setCategoriesVOList(List<CategoriesVO> categoriesVOList) {
		this.categoriesVOList = categoriesVOList;
	}

}
