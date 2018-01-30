package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.xmlconfig.vo.CategoryListVO;


/**
 * The Class YTDSummaryVO.
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
public class YTDSummaryVO {

	private int selectedYear = Constants.ZERO;
	private List<KeyValue> listYear = null;
	
	private List<String> yearList = null;
	
		
	private List<String> listHeader = new ArrayList<String>();
	private List<String> listFooter = new ArrayList<String>();
	private List<String> listFooterRetail = new ArrayList<String>();
	private List<String> listFooterOnline = new ArrayList<String>();
	private Map<String, Double> mapYTDSpend = null;
	private Map<String, Double> mapYTDSpendRetail = null;
	private Map<String, Double> mapYTDSpendOnline = null;
	private List<YTDSummaryDataVO> listYTDSpend = null;
	private List<YTDSummaryDataVO> listYTDSpendRetail = null;
	private List<YTDSummaryDataVO> listYTDSpendOnline = null;
	private Map<String, CategoryListVO> mapCategory = null;
	private List<Double> listFooterTotal = null;
	private List<Double> listFooterTotalRetail = null;
	private List<Double> listFooterTotalOnline = null;
	private String categoryChartName = Constants.EMPTY;
	private String monthChartName = Constants.EMPTY;
	private String errorMessage = Constants.EMPTY;
	private String asteriskDisplay = Constants.EMPTY;
	private String chartFileLocation;
	private String categoryChartFileName;
	private String categoryChartFileExtn;
	private String monthlyChartFileName;
	private String monthlyChartFileExtn;
	private String chartDisplay = Constants.NO;
	private String categoryId;
	private String netSpendAmount;
	private String tranMonth;
	private String tranYear;
	private String sessionID;

	/**
	 * @return the selectedYear
	 */
	public int getSelectedYear() {
		return selectedYear;
	}

	/**
	 * @param selectedYear the selectedYear to set
	 */
	public void setSelectedYear(int selectedYear) {
		this.selectedYear = selectedYear;
	}

	/**
	 * @return the listYear
	 */
	public List<KeyValue> getListYear() {
		return listYear;
	}

	/**
	 * @param listYear the listYear to set
	 */
	public void setListYear(List<KeyValue> listYear) {
		this.listYear = listYear;
	}

	/**
	 * @return the yearList
	 */
	public List<String> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList the yearList to set
	 */
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	/**
	 * @return the listHeader
	 */
	public List<String> getListHeader() {
		return listHeader;
	}

	/**
	 * @param listHeader the listHeader to set
	 */
	public void setListHeader(List<String> listHeader) {
		this.listHeader = listHeader;
	}

	/**
	 * @return the listFooter
	 */
	public List<String> getListFooter() {
		return listFooter;
	}

	/**
	 * @param listFooter the listFooter to set
	 */
	public void setListFooter(List<String> listFooter) {
		this.listFooter = listFooter;
	}

	/**
	 * @return the mapYTDSpend
	 */
	public Map<String, Double> getMapYTDSpend() {
		return mapYTDSpend;
	}

	/**
	 * @param mapYTDSpend the mapYTDSpend to set
	 */
	public void setMapYTDSpend(Map<String, Double> mapYTDSpend) {
		this.mapYTDSpend = mapYTDSpend;
	}

	/**
	 * @return the listYTDSpend
	 */
	public List<YTDSummaryDataVO> getListYTDSpend() {
		return listYTDSpend;
	}

	/**
	 * @param listYTDSpend the listYTDSpend to set
	 */
	public void setListYTDSpend(List<YTDSummaryDataVO> listYTDSpend) {
		this.listYTDSpend = listYTDSpend;
	}

	/**
	 * @return the mapCategory
	 */
	public Map<String, CategoryListVO> getMapCategory() {
		return mapCategory;
	}

	/**
	 * @param mapCategory the mapCategory to set
	 */
	public void setMapCategory(Map<String, CategoryListVO> mapCategory) {
		this.mapCategory = mapCategory;
	}

	/**
	 * @return the listFooterTotal
	 */
	public List<Double> getListFooterTotal() {
		return listFooterTotal;
	}

	/**
	 * @param listFooterTotal the listFooterTotal to set
	 */
	public void setListFooterTotal(List<Double> listFooterTotal) {
		this.listFooterTotal = listFooterTotal;
	}

	/**
	 * @return the categoryChartName
	 */
	public String getCategoryChartName() {
		return categoryChartName;
	}

	/**
	 * @param categoryChartName the categoryChartName to set
	 */
	public void setCategoryChartName(String categoryChartName) {
		this.categoryChartName = categoryChartName;
	}

	/**
	 * @return the monthChartName
	 */
	public String getMonthChartName() {
		return monthChartName;
	}

	/**
	 * @param monthChartName the monthChartName to set
	 */
	public void setMonthChartName(String monthChartName) {
		this.monthChartName = monthChartName;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the asteriskDisplay
	 */
	public String getAsteriskDisplay() {
		return asteriskDisplay;
	}

	/**
	 * @param asteriskDisplay the asteriskDisplay to set
	 */
	public void setAsteriskDisplay(String asteriskDisplay) {
		this.asteriskDisplay = asteriskDisplay;
	}

	/**
	 * @return the chartFileLocation
	 */
	public String getChartFileLocation() {
		return chartFileLocation;
	}

	/**
	 * @param chartFileLocation the chartFileLocation to set
	 */
	public void setChartFileLocation(String chartFileLocation) {
		this.chartFileLocation = chartFileLocation;
	}

	/**
	 * @return the categoryChartFileName
	 */
	public String getCategoryChartFileName() {
		return categoryChartFileName;
	}

	/**
	 * @param categoryChartFileName the categoryChartFileName to set
	 */
	public void setCategoryChartFileName(String categoryChartFileName) {
		this.categoryChartFileName = categoryChartFileName;
	}

	/**
	 * @return the categoryChartFileExtn
	 */
	public String getCategoryChartFileExtn() {
		return categoryChartFileExtn;
	}

	/**
	 * @param categoryChartFileExtn the categoryChartFileExtn to set
	 */
	public void setCategoryChartFileExtn(String categoryChartFileExtn) {
		this.categoryChartFileExtn = categoryChartFileExtn;
	}

	/**
	 * @return the monthlyChartFileName
	 */
	public String getMonthlyChartFileName() {
		return monthlyChartFileName;
	}

	/**
	 * @param monthlyChartFileName the monthlyChartFileName to set
	 */
	public void setMonthlyChartFileName(String monthlyChartFileName) {
		this.monthlyChartFileName = monthlyChartFileName;
	}

	/**
	 * @return the monthlyChartFileExtn
	 */
	public String getMonthlyChartFileExtn() {
		return monthlyChartFileExtn;
	}

	/**
	 * @param monthlyChartFileExtn the monthlyChartFileExtn to set
	 */
	public void setMonthlyChartFileExtn(String monthlyChartFileExtn) {
		this.monthlyChartFileExtn = monthlyChartFileExtn;
	}

	/**
	 * @return the chartDisplay
	 */
	public String getChartDisplay() {
		return chartDisplay;
	}

	/**
	 * @param chartDisplay the chartDisplay to set
	 */
	public void setChartDisplay(String chartDisplay) {
		this.chartDisplay = chartDisplay;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the netSpendAmount
	 */
	public String getNetSpendAmount() {
		return netSpendAmount;
	}

	/**
	 * @param netSpendAmount the netSpendAmount to set
	 */
	public void setNetSpendAmount(String netSpendAmount) {
		this.netSpendAmount = netSpendAmount;
	}

	/**
	 * @return the tranMonth
	 */
	public String getTranMonth() {
		return tranMonth;
	}

	/**
	 * @param tranMonth the tranMonth to set
	 */
	public void setTranMonth(String tranMonth) {
		this.tranMonth = tranMonth;
	}

	/**
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * @param sessionID the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * @return the tranYear
	 */
	public String getTranYear() {
		return tranYear;
	}

	/**
	 * @param tranYear the tranYear to set
	 */
	public void setTranYear(String tranYear) {
		this.tranYear = tranYear;
	}

	/**
	 * @return the mapYTDSpendRetail
	 */
	public Map<String, Double> getMapYTDSpendRetail() {
		return mapYTDSpendRetail;
	}

	/**
	 * @param mapYTDSpendRetail the mapYTDSpendRetail to set
	 */
	public void setMapYTDSpendRetail(Map<String, Double> mapYTDSpendRetail) {
		this.mapYTDSpendRetail = mapYTDSpendRetail;
	}

	/**
	 * @return the mapYTDSpendOnline
	 */
	public Map<String, Double> getMapYTDSpendOnline() {
		return mapYTDSpendOnline;
	}

	/**
	 * @param mapYTDSpendOnline the mapYTDSpendOnline to set
	 */
	public void setMapYTDSpendOnline(Map<String, Double> mapYTDSpendOnline) {
		this.mapYTDSpendOnline = mapYTDSpendOnline;
	}

	/**
	 * @return the listYTDSpendRetail
	 */
	public List<YTDSummaryDataVO> getListYTDSpendRetail() {
		return listYTDSpendRetail;
	}

	/**
	 * @param listYTDSpendRetail the listYTDSpendRetail to set
	 */
	public void setListYTDSpendRetail(List<YTDSummaryDataVO> listYTDSpendRetail) {
		this.listYTDSpendRetail = listYTDSpendRetail;
	}

	/**
	 * @return the listYTDSpendOnline
	 */
	public List<YTDSummaryDataVO> getListYTDSpendOnline() {
		return listYTDSpendOnline;
	}

	/**
	 * @param listYTDSpendOnline the listYTDSpendOnline to set
	 */
	public void setListYTDSpendOnline(List<YTDSummaryDataVO> listYTDSpendOnline) {
		this.listYTDSpendOnline = listYTDSpendOnline;
	}

	/**
	 * @return the listFooterTotalRetail
	 */
	public List<Double> getListFooterTotalRetail() {
		return listFooterTotalRetail;
	}

	/**
	 * @param listFooterTotalRetail the listFooterTotalRetail to set
	 */
	public void setListFooterTotalRetail(List<Double> listFooterTotalRetail) {
		this.listFooterTotalRetail = listFooterTotalRetail;
	}

	/**
	 * @return the listFooterTotalOnline
	 */
	public List<Double> getListFooterTotalOnline() {
		return listFooterTotalOnline;
	}

	/**
	 * @param listFooterTotalOnline the listFooterTotalOnline to set
	 */
	public void setListFooterTotalOnline(List<Double> listFooterTotalOnline) {
		this.listFooterTotalOnline = listFooterTotalOnline;
	}

	/**
	 * @return the listFooterRetail
	 */
	public List<String> getListFooterRetail() {
		return listFooterRetail;
	}

	/**
	 * @param listFooterRetail the listFooterRetail to set
	 */
	public void setListFooterRetail(List<String> listFooterRetail) {
		this.listFooterRetail = listFooterRetail;
	}

	/**
	 * @return the listFooterOnline
	 */
	public List<String> getListFooterOnline() {
		return listFooterOnline;
	}

	/**
	 * @param listFooterOnline the listFooterOnline to set
	 */
	public void setListFooterOnline(List<String> listFooterOnline) {
		this.listFooterOnline = listFooterOnline;
	}

	
}
