/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       AdvertisementsVO.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This is a value object created to store Advertisements 
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
 * The Class AdvertisementsVO.
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
@XmlRootElement(name="Advertisements")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvertisementsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@XmlElement(name="Ad")
    private List<AdVO> AdList;
	
	/**
	 * @return the adList
	 */
	public List<AdVO> getAdList() {
		return AdList;
	}

	/**
	 * @param adList the adList to set
	 */
	public void setAdList(List<AdVO> adList) {
		AdList = adList;
	}

	/**
	 * Overriding the method toString().
	 * @return String
	 */
	@Override
	public String toString() {
		return "Advertisements [AdList size=" + AdList.size() + "]";
	}

}
