package com.staples.dashboard.app.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.staples.dashboard.app.xmlconfig.vo.CategoriesVO;
import com.staples.dashboard.app.xmlconfig.vo.ProductCategoriesVO;
import com.staples.dashboard.app.xmlconfig.vo.PromotionOffersLayoutVO;
import com.staples.dashboard.app.xmlconfig.vo.HeliosCategoriesVO;
import com.staples.dashboard.app.xmlconfig.vo.StatementCheckVO;
import com.staples.dashboard.app.xmlconfig.vo.SummaryLayoutVO;
import com.staples.dashboard.app.xmlconfig.vo.TierLayoutVO;
import com.staples.dashboard.app.xmlconfig.vo.YTDSummaryLayoutVO;

/**
 * The Class StatementCheckParser.
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
public class StatementCheckParser {

	private StatementCheckVO objStmtCheckVO = null;

	private XMLParserUtility objXmlParserUtil = null;

	/**
	 * Construct to initialize the class elements
	 */
	public StatementCheckParser() {
		objXmlParserUtil = new XMLParserUtility();
		objStmtCheckVO = new StatementCheckVO();
	}

	/**
	 * The below method takes the tier id as input and provides the summary
	 * layout as output in the response for the corresponding tier id
	 * 
	 * @param tierId
	 *            Tier Id for which the statement check details are to be
	 *            constructed.
	 * @return objRetStmtCheckVO StatementCheckVO object populated with data
	 *         from XML
	 */
	public StatementCheckVO parseStatementCheck(String strTierId) {

		objStmtCheckVO = (StatementCheckVO) objXmlParserUtil.xmlParser(
				"StatementCheckFormat.xml", objStmtCheckVO);
		StatementCheckVO objRetStmtCheckVO = new StatementCheckVO();
		String strPromotionalBanner = null;
		String strRewardsCategory = null;
		String strProductCategory = null;
		String strMonthly = null;
		String strInk = null;
		String strYtd = null;

		if (null != objStmtCheckVO) {
			List<TierLayoutVO> listTieryLayoutVO = objStmtCheckVO
					.getTierLayoutVOList();
			if (null != listTieryLayoutVO) {
				Iterator<TierLayoutVO> objTierIterator = listTieryLayoutVO
						.iterator();
				while (objTierIterator.hasNext()) {
					TierLayoutVO objtierLayoutVO = objTierIterator.next();

					if (objtierLayoutVO.getTierCode().equalsIgnoreCase(
							strTierId)) {

						strPromotionalBanner = objtierLayoutVO
								.getPromotionalBanners();
						strRewardsCategory = objtierLayoutVO
								.getRewardCategory();
						strProductCategory = objtierLayoutVO
								.getProductCategory();
						strYtd = objtierLayoutVO.getYearToDate();
						strMonthly = objtierLayoutVO.getMonthly();
						strInk = objtierLayoutVO.getInk();

						List<SummaryLayoutVO> objSmryLayoutVOList = objStmtCheckVO
								.getSummaryLayoutVOList();

						if (null != objSmryLayoutVOList) {
							List<SummaryLayoutVO> objTmpSmryLayoutVOList = new ArrayList<SummaryLayoutVO>();
							Iterator<SummaryLayoutVO> objSmryLayoutVO = objSmryLayoutVOList
									.iterator();
							while (objSmryLayoutVO.hasNext()) {
								SummaryLayoutVO objSLVO = objSmryLayoutVO
										.next();

								if ((Constants.MONTHLY_IND.equals(objSLVO
										.getType()) && objSLVO.getValue()
										.equalsIgnoreCase(strMonthly))
										|| (Constants.INKRECYCLING_IND
												.equals(objSLVO.getType()) && objSLVO
												.getValue().equalsIgnoreCase(
														strInk))) {
									objTmpSmryLayoutVOList.add(objSLVO);
									objRetStmtCheckVO
											.setSummaryLayoutVOList(objTmpSmryLayoutVOList);
								}
							}
						}

						List<PromotionOffersLayoutVO> objProOffersLayoutVOList = objStmtCheckVO
								.getPromotionalOffersLayoutVOList();
						List<PromotionOffersLayoutVO> objTmpPromoOffLayoutVO = new ArrayList<PromotionOffersLayoutVO>();
						if (null != objProOffersLayoutVOList) {
							Iterator<PromotionOffersLayoutVO> objPromBOffersVOItr = objProOffersLayoutVOList
									.iterator();
							while (objPromBOffersVOItr.hasNext()) {
								PromotionOffersLayoutVO objPbVO = objPromBOffersVOItr
										.next();
								if (objPbVO.getValue().equalsIgnoreCase(
										strPromotionalBanner)) {
									objTmpPromoOffLayoutVO.add(objPbVO);
									objRetStmtCheckVO
											.setPromotionalOffersLayoutVOList(objTmpPromoOffLayoutVO);
									break;
								}
							}
						}

						List<YTDSummaryLayoutVO> objYTDSummaryLayoutVO = objStmtCheckVO
								.getYtdSummaryLayoutList();
						List<YTDSummaryLayoutVO> objTmpYTDSmryLayoutVO = new ArrayList<YTDSummaryLayoutVO>();
						if (null != objYTDSummaryLayoutVO) {
							Iterator<YTDSummaryLayoutVO> objYTDSmryItr = objYTDSummaryLayoutVO
									.iterator();
							while (objYTDSmryItr.hasNext()) {
								YTDSummaryLayoutVO objYTDVO = objYTDSmryItr
										.next();
								if (objYTDVO.getValue()
										.equalsIgnoreCase(strYtd)) {
									objTmpYTDSmryLayoutVO.add(objYTDVO);
									objRetStmtCheckVO
											.setYtdSummaryLayoutList(objTmpYTDSmryLayoutVO);
									break;
								}
							}
						}

						List<CategoriesVO> objCategoriesVO = objStmtCheckVO
								.getCategoriesVOList();
						List<CategoriesVO> objTmpCategoriesVOList = new ArrayList<CategoriesVO>();
						CategoriesVO objRetCatVO = new CategoriesVO();
						if (null != objCategoriesVO) {
							Iterator<CategoriesVO> objCategoriesVOIte = objCategoriesVO
									.iterator();
							while (objCategoriesVOIte.hasNext()) {
								CategoriesVO objCatVO = objCategoriesVOIte
										.next();

								List<HeliosCategoriesVO> objRwrdsCatVOList = objCatVO
										.getRewardsCategoriesVO();
								List<HeliosCategoriesVO> objTmpRwrdsCatVOList = null;
								if (null != objRwrdsCatVOList) {
									Iterator<HeliosCategoriesVO> objRwrdsCatIte = objRwrdsCatVOList
											.iterator();
									HeliosCategoriesVO objRwdsCatVO = null;
									while (objRwrdsCatIte.hasNext()) {
										objRwdsCatVO = null;
										objRwdsCatVO = objRwrdsCatIte.next();
										if (objRwdsCatVO.getValue()
												.equalsIgnoreCase(
														strRewardsCategory)) {

											objTmpRwrdsCatVOList = new ArrayList<HeliosCategoriesVO>();
											objTmpRwrdsCatVOList
													.add(objRwdsCatVO);
											objRetCatVO
													.setRewardsCategoriesVO(objTmpRwrdsCatVOList);
											break;
										}
									}
								}

								List<ProductCategoriesVO> objPrdCatVOList = objCatVO
										.getProductCategoriesVO();
								List<ProductCategoriesVO> objTmpPrdCatVOList = new ArrayList<ProductCategoriesVO>();
								if (null != objPrdCatVOList) {
									Iterator<ProductCategoriesVO> objPrdCatIte = objPrdCatVOList
											.iterator();
									while (objPrdCatIte.hasNext()) {
										ProductCategoriesVO objPrdCatVO = objPrdCatIte
												.next();
										if (objPrdCatVO.getValue()
												.equalsIgnoreCase(
														strProductCategory)) {
											objTmpPrdCatVOList.add(objPrdCatVO);
											objRetCatVO
													.setProductCategoriesVO(objTmpPrdCatVOList);
											break;
										}
									}
								}
								objTmpCategoriesVOList.add(objRetCatVO);
								objRetStmtCheckVO
										.setCategoriesVOList(objTmpCategoriesVOList);
							}
						}
					} 
				}
			}
		}
		return objRetStmtCheckVO;
	}
}
