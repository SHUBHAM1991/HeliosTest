/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       YTDSummaryParser.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This class is a parser utility which parses the xml string into java object
 * 										by invoking XMLparser and then assigns the value into the corresponding value
 * 										object.
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */

package com.staples.dashboard.app.xmlconfig;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.staples.dashboard.app.xmlconfig.vo.StatementCheckVO;
import com.staples.dashboard.app.xmlconfig.vo.TierLayoutVO;
import com.staples.dashboard.app.xmlconfig.vo.YTDSummaryLayoutVO;
import com.staples.dashboard.app.utilities.XMLParserUtility;

/**
 * The Class YTDSummaryParser.
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
@Component
public class YTDSummaryParser {

	private StatementCheckVO objStmtCheckVO = null;

	private XMLParserUtility objXMLParserUtil = null;

	/**
	 * Construct to initialize the class elements
	 */
	public YTDSummaryParser() {
		objXMLParserUtil = new XMLParserUtility();
		objStmtCheckVO = new StatementCheckVO();
	}

	/**
	 * The below method takes the tier id as input and provides the summary
	 * layout as output in the response for the corresponding tier id
	 * 
	 * @param tierId
	 * @return summaryLayoutVO
	 */
	public YTDSummaryLayoutVO parseYTDSummary(String strTierId) {
		objStmtCheckVO = (StatementCheckVO) objXMLParserUtil.xmlParser(
				"StatementCheckFormat.xml", objStmtCheckVO);
		String strYTDValue = null;
		YTDSummaryLayoutVO objRetSmryLayoutVO = null;
		if (null != objStmtCheckVO) {
			List<TierLayoutVO> objTierLayoutVOList = objStmtCheckVO
					.getTierLayoutVOList();
			if (null != objTierLayoutVOList) {
				Iterator<TierLayoutVO> objTierLayoutVOIte = objTierLayoutVOList
						.iterator();
				while (objTierLayoutVOIte.hasNext()) {
					TierLayoutVO objTierLayoutVO = objTierLayoutVOIte.next();
					if (objTierLayoutVO.getTierCode().equalsIgnoreCase(
							strTierId)) {
						strYTDValue = objTierLayoutVO.getYearToDate();
						List<YTDSummaryLayoutVO> objYTDSmryLayoutVOList = objStmtCheckVO
								.getYtdSummaryLayoutList();
						if (null != objYTDSmryLayoutVOList) {
							Iterator<YTDSummaryLayoutVO> objYTDSmryLayoutVOIte = objYTDSmryLayoutVOList
									.iterator();
							while (objYTDSmryLayoutVOIte.hasNext()) {
								YTDSummaryLayoutVO objYTDSmryLayoutVO = objYTDSmryLayoutVOIte
										.next();
								if (null != objYTDSmryLayoutVO) {
									if (objYTDSmryLayoutVO.getType()
											.equalsIgnoreCase("YTD")
											&& objYTDSmryLayoutVO.getValue()
													.equalsIgnoreCase(
															strYTDValue)) {
										objRetSmryLayoutVO = objYTDSmryLayoutVO;
										break;
									}
								}
							}
						}
					}
				}
			}

		}
		return objRetSmryLayoutVO;
	}
}
