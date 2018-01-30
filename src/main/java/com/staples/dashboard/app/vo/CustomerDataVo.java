package com.staples.dashboard.app.vo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;

import com.staples.dashboard.app.xmlconfig.vo.CategoryVO;

/**
 * The Class CustomerDataVo.
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
public class CustomerDataVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CustProfileVO custProfileVO;
	private List<YTDInfoVO> ytdInfoVOList;
	private YTDSummaryVO objSummaryVO;
	private List<CategoryVO> objCategoriesList;
	private LinkedHashMap<String, String> monYearList;
	private PieDataset pieDataset;
	private PieDataset pieDatasetRetail;
	private PieDataset pieDatasetOnline;
	private CategoryDataset barDataSet;
	private CategoryDataset barDataSetRetail;
	private CategoryDataset barDataSetOnline;
	private List<String> barDataSetKeys;
	private List<SuperUserInfoVO> superUserInfoVList;
	private List<PurchaseDetailsVO> objPurchaseDetailsListVO;
	private List<NotificationInfoVo> notiInfoVOList;
	private List<CustProfileVO> listOfCustProfileVO;
	private List<CdmCustProfileVO> listOfCdmCustProfileVO;
	public String cusRight;
	private String valueAttributionScore;
	private List<SubplayInfoVo> listOfSubPlay;
	private String unSelListOfSubPlay;
	private String qualificationScore;
	private Map<String, List<Object>> segAndQualSubPlays;

	
	/**
	 * @return the custProfileVO
	 */
	public CustProfileVO getCustProfileVO() {
		return custProfileVO;
	}
	/**
	 * @param custProfileVO the custProfileVO to set
	 */
	public void setCustProfileVO(CustProfileVO custProfileVO) {
		this.custProfileVO = custProfileVO;
	}
	/**
	 * @return the ytdInfoVOList
	 */
	public List<YTDInfoVO> getYtdInfoVOList() {
		return ytdInfoVOList;
	}
	/**
	 * @param ytdInfoVOList the ytdInfoVOList to set
	 */
	public void setYtdInfoVOList(List<YTDInfoVO> ytdInfoVOList) {
		this.ytdInfoVOList = ytdInfoVOList;
	}
	/**
	 * @return the objSummaryVO
	 */
	public YTDSummaryVO getObjSummaryVO() {
		return objSummaryVO;
	}
	/**
	 * @param objSummaryVO the objSummaryVO to set
	 */
	public void setObjSummaryVO(YTDSummaryVO objSummaryVO) {
		this.objSummaryVO = objSummaryVO;
	}
	/**
	 * @return the objCategoriesList
	 */
	public List<CategoryVO> getObjCategoriesList() {
		return objCategoriesList;
	}
	/**
	 * @param objCategoriesList the objCategoriesList to set
	 */
	public void setObjCategoriesList(List<CategoryVO> objCategoriesList) {
		this.objCategoriesList = objCategoriesList;
	}
	/**
	 * @return the pieDataset
	 */
	public PieDataset getPieDataset() {
		return pieDataset;
	}
	/**
	 * @param pieDataset the pieDataset to set
	 */
	public void setPieDataset(PieDataset pieDataset) {
		this.pieDataset = pieDataset;
	}
	/**
	 * @return the barDataSet
	 */
	public CategoryDataset getBarDataSet() {
		return barDataSet;
	}
	/**
	 * @param barDataSet the barDataSet to set
	 */
	public void setBarDataSet(CategoryDataset barDataSet) {
		this.barDataSet = barDataSet;
	}
	/**
	 * @return the superUserInfoVList
	 */
	public List<SuperUserInfoVO> getSuperUserInfoVList() {
		return superUserInfoVList;
	}
	/**
	 * @param superUserInfoVList the superUserInfoVList to set
	 */
	public void setSuperUserInfoVList(List<SuperUserInfoVO> superUserInfoVList) {
		this.superUserInfoVList = superUserInfoVList;
	}
	/**
	 * @return the objPurchaseDetailsListVO
	 */
	public List<PurchaseDetailsVO> getObjPurchaseDetailsListVO() {
		return objPurchaseDetailsListVO;
	}
	/**
	 * @param objPurchaseDetailsListVO the objPurchaseDetailsListVO to set
	 */
	public void setObjPurchaseDetailsListVO(
			List<PurchaseDetailsVO> objPurchaseDetailsListVO) {
		this.objPurchaseDetailsListVO = objPurchaseDetailsListVO;
	}
	/**
	 * @return the monYearList
	 */
	public LinkedHashMap<String, String> getMonYearList() {
		return monYearList;
	}
	/**
	 * @param monYearList the monYearList to set
	 */
	public void setMonYearList(LinkedHashMap<String, String> monYearList) {
		this.monYearList = monYearList;
	}
	/**
	 * @return the notiInfoVOList
	 */
	public List<NotificationInfoVo> getNotiInfoVOList() {
		return notiInfoVOList;
	}
	/**
	 * @param notiInfoVOList the notiInfoVOList to set
	 */
	public void setNotiInfoVOList(List<NotificationInfoVo> notiInfoVOList) {
		this.notiInfoVOList = notiInfoVOList;
	}
	/**
	 * @return the listOfCustProfileVO
	 */
	public List<CustProfileVO> getListOfCustProfileVO() {
		return listOfCustProfileVO;
	}
	/**
	 * @param listOfCustProfileVO the listOfCustProfileVO to set
	 */
	public void setListOfCustProfileVO(List<CustProfileVO> listOfCustProfileVO) {
		this.listOfCustProfileVO = listOfCustProfileVO;
	}
	/**
	 * @return the cusRight
	 */
	public String getCusRight() {
		return cusRight;
	}
	/**
	 * @param cusRight the cusRight to set
	 */
	public void setCusRight(String cusRight) {
		this.cusRight = cusRight;
	}
	/**
	 * @return the valueAttributionScore
	 */
	public String getValueAttributionScore() {
		return valueAttributionScore;
	}
	/**
	 * @param valueAttributionScore the valueAttributionScore to set
	 */
	public void setValueAttributionScore(String valueAttributionScore) {
		this.valueAttributionScore = valueAttributionScore;
	}
	/**
	 * @return the listOfSubPlay
	 */
	public List<SubplayInfoVo> getListOfSubPlay() {
		return listOfSubPlay;
	}
	/**
	 * @param listOfSubPlay the listOfSubPlay to set
	 */
	public void setListOfSubPlay(List<SubplayInfoVo> listOfSubPlay) {
		this.listOfSubPlay = listOfSubPlay;
	}
	/**
	 * @return the unSelListOfSubPlay
	 */
	public String getUnSelListOfSubPlay() {
		return unSelListOfSubPlay;
	}
	/**
	 * @param unSelListOfSubPlay the unSelListOfSubPlay to set
	 */
	public void setUnSelListOfSubPlay(String unSelListOfSubPlay) {
		this.unSelListOfSubPlay = unSelListOfSubPlay;
	}
	/**
	 * @return the qualificationScore
	 */
	public String getQualificationScore() {
		return qualificationScore;
	}
	/**
	 * @param qualificationScore the qualificationScore to set
	 */
	public void setQualificationScore(String qualificationScore) {
		this.qualificationScore = qualificationScore;
	}
	/**
	 * @return the segAndQualSubPlays
	 */
	public Map<String, List<Object>> getSegAndQualSubPlays() {
		return segAndQualSubPlays;
	}
	/**
	 * @param segAndQualSubPlays the segAndQualSubPlays to set
	 */
	public void setSegAndQualSubPlays(Map<String, List<Object>> segAndQualSubPlays) {
		this.segAndQualSubPlays = segAndQualSubPlays;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the pieDatasetRetail
	 */
	public PieDataset getPieDatasetRetail() {
		return pieDatasetRetail;
	}
	/**
	 * @param pieDatasetRetail the pieDatasetRetail to set
	 */
	public void setPieDatasetRetail(PieDataset pieDatasetRetail) {
		this.pieDatasetRetail = pieDatasetRetail;
	}
	/**
	 * @return the pieDatasetOnline
	 */
	public PieDataset getPieDatasetOnline() {
		return pieDatasetOnline;
	}
	/**
	 * @param pieDatasetOnline the pieDatasetOnline to set
	 */
	public void setPieDatasetOnline(PieDataset pieDatasetOnline) {
		this.pieDatasetOnline = pieDatasetOnline;
	}
	/**
	 * @return the barDataSetRetail
	 */
	public CategoryDataset getBarDataSetRetail() {
		return barDataSetRetail;
	}
	/**
	 * @param barDataSetRetail the barDataSetRetail to set
	 */
	public void setBarDataSetRetail(CategoryDataset barDataSetRetail) {
		this.barDataSetRetail = barDataSetRetail;
	}
	/**
	 * @return the barDataSetOnline
	 */
	public CategoryDataset getBarDataSetOnline() {
		return barDataSetOnline;
	}
	/**
	 * @param barDataSetOnline the barDataSetOnline to set
	 */
	public void setBarDataSetOnline(CategoryDataset barDataSetOnline) {
		this.barDataSetOnline = barDataSetOnline;
	}
	/**
	 * @return the barDataSetKeys
	 */
	public List<String> getBarDataSetKeys() {
		return barDataSetKeys;
	}
	/**
	 * @param barDataSetKeys the barDataSetKeys to set
	 */
	public void setBarDataSetKeys(List<String> barDataSetKeys) {
		this.barDataSetKeys = barDataSetKeys;
	}
	/**
	 * @return the listOfCdmCustProfileVO
	 */
	public List<CdmCustProfileVO> getListOfCdmCustProfileVO() {
		return listOfCdmCustProfileVO;
	}
	/**
	 * @param listOfCdmCustProfileVO the listOfCdmCustProfileVO to set
	 */
	public void setListOfCdmCustProfileVO(List<CdmCustProfileVO> listOfCdmCustProfileVO) {
		this.listOfCdmCustProfileVO = listOfCdmCustProfileVO;
	}
	           	
}
