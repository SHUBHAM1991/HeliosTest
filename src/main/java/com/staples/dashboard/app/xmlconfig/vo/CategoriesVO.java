/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       CategoriesVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store Categories 
 * 										details available in the configuration xml 
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */
package com.staples.dashboard.app.xmlconfig.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class CategoriesVO.
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
@XmlRootElement(name = "Categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "RewardsCategories")
	private List<HeliosCategoriesVO> rewardsCategoriesVO;

	@XmlElement(name = "ProductCategories")
	private List<ProductCategoriesVO> productCategoriesVO;

	/**
	 * @return the rewardsCategoriesVO
	 */
	public List<HeliosCategoriesVO> getRewardsCategoriesVO() {
		return rewardsCategoriesVO;
	}

	/**
	 * @param rewardsCategoriesVO the rewardsCategoriesVO to set
	 */
	public void setRewardsCategoriesVO(List<HeliosCategoriesVO> rewardsCategoriesVO) {
		this.rewardsCategoriesVO = rewardsCategoriesVO;
	}

	/**
	 * @return the productCategoriesVO
	 */
	public List<ProductCategoriesVO> getProductCategoriesVO() {
		return productCategoriesVO;
	}

	/**
	 * @param productCategoriesVO the productCategoriesVO to set
	 */
	public void setProductCategoriesVO(List<ProductCategoriesVO> productCategoriesVO) {
		this.productCategoriesVO = productCategoriesVO;
	}
}
