package com.staples.dashboard.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO;
import com.staples.dashboard.app.sfdc.OutputTasksfdc;
import com.staples.dashboard.app.sfdc.Task;
import com.staples.dashboard.app.vo.AbabdonedCartVO;
import com.staples.dashboard.app.vo.AccountProgressInfo;
import com.staples.dashboard.app.vo.AlertDetailVo;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.ContactDateVO;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.CustRecommendationVO;
import com.staples.dashboard.app.vo.CustomerDataVo;
import com.staples.dashboard.app.vo.DispositionDetailsVo;
import com.staples.dashboard.app.vo.DotcomAcitivityVO;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.LastLiveContactAndHistoryVO;
import com.staples.dashboard.app.vo.NotificationInfoVo;
import com.staples.dashboard.app.vo.OnlineRetailReorderRecommendationVO;
import com.staples.dashboard.app.vo.OrderContactInfoVO;
import com.staples.dashboard.app.vo.PurchaseDetailsSelectedVO;
import com.staples.dashboard.app.vo.PurchaseDetailsVO;
import com.staples.dashboard.app.vo.PurchaseHeliosDetailsVO;
import com.staples.dashboard.app.vo.RecommOrderContactVO;
import com.staples.dashboard.app.vo.RecommendationViewNotBoughtVO;
import com.staples.dashboard.app.vo.ReorderRecommendationVO;
import com.staples.dashboard.app.vo.SavingsVo;
import com.staples.dashboard.app.vo.SbaDiffDetailsVo;
import com.staples.dashboard.app.vo.SearchVO;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegMktgResources;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToVO;
import com.staples.dashboard.app.vo.SubplayInfoVo;
import com.staples.dashboard.app.vo.SuperUserInfoVO;
import com.staples.dashboard.app.vo.YTDInfoVO;
import com.staples.dashboard.app.vo.YTDSummaryVO;
import com.staples.dashboard.app.xmlconfig.vo.CategoryVO;
import com.staples.dashboard.dto.CustomerListDTO;
import com.staples.dashboard.dto.SavingsDetailDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Interface ContractDashboardService.
 * 
 * @author KumBi002
 * @version 1.0 Revision history
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          <table>
 *          <tr>
 *          <td>Version</td>
 *          <td>Date</td>
 *          <td>Author</td>
 *          <td>Description</td>
 *          </tr>
 *          <tr>
 *          <td>1.0</td>
 *          <td>Dec 4, 2015</td>
 *          <td>KumBi002</td>
 *          <td>Initial Draft</td>
 *          </tr>
 *          </table>
 *          </p>
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 */
public interface ContractDashboardService {

	/**
	 * Method declaration for getting customer profile.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustProfileVO>
	 */
	public List<CustProfileVO> getCustomerProfile(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting YTD information.
	 * 
	 * @param String
	 *            custNum
	 * @return List<YTDInfoVO>
	 */
	public List<YTDInfoVO> getYTDInfo(String custNum) throws DashboardException;

	/**
	 * Method declaration for getting purchase details.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            mnth
	 * @param String
	 *            year
	 * @return List<PurchaseDetailsVO>
	 */
	public List<PurchaseDetailsVO> getPurchaseDetails(String custNum,
			String mnth, String year, String catId) throws DashboardException;

	/**
	 * Method declaration for getting purchase rewards details.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            mnth
	 * @param String
	 *            year
	 * @return List<PurchaseRewardsDetailsVO>
	 */
	public List<PurchaseHeliosDetailsVO> getPurchaseRewardsDetails(
			String custNum, long [] orderNumberList) throws DashboardException;

	/**
	 * Method declaration for getting super user info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SuperUserInfoVO>
	 */
	public List<SuperUserInfoVO> getSuperUserInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting abandoned cart info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<AbabdonedCartVO>
	 */
	public List<AbabdonedCartVO> getAbandonedCartInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting customer recommendation info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getCustRecommendationInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting dot com activity info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<DotcomAcitivityVO>
	 */
	public List<DotcomAcitivityVO> getDotcomActivityInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting item list.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SearchVO>
	 */
	public List<SearchVO> getSearchItemsList(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting YTD summary info.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selectedYear
	 * @return List<YTDSummaryVO>
	 */
	public List<YTDSummaryVO> getYTDSummary(String custNum, String selectedYear)
			throws DashboardException;

	/**
	 * Method declaration for getting notification info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<NotificationInfoVo>
	 */
	public List<NotificationInfoVo> getNotiInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting customer info.
	 * 
	 * @param String
	 *            accId
	 * @param String
	 *            selPlaySection
	 * @param String
	 *            [] selectedSubplayArr
	 * @param JQueryDataTableInputDTO
	 *            tableInput
	 * @return CustomerListDTO
	 */
	public CustomerListDTO getAllCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO tableInput,String[] selectedQualScore,String[] selectedSegScorer) throws DashboardException;

	/**
	 * Method declaration for getting customer value.
	 * 
	 * @param String
	 *            cusNum
	 * @return CustomerDataVo
	 */
	public CustomerDataVo getCustomerValue(String cusNum)
			throws DashboardException;

	/**
	 * Method declaration for getting shipTo info.
	 * 
	 * @param String
	 *            cusNum
	 * @return List<ShipToVO>
	 */
	public List<ShipToVO> getShipToInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	public String getLatestFiscalDate(String custNum) throws DashboardException;

	/**
	 * Method declaration for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	public String getLatestFiscalDate1(String custNum)
			throws DashboardException;
	
	/**
	 * Method declaration for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	public String getLatestOrderReturnDate(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting customer number.
	 * 
	 * @param String
	 *            searchText
	 * @param String
	 *            acctId
	 * @return String
	 */
	public String getCustomerNumber(String searchText, String acctId, String custNum,
			String empLevel) throws DashboardException;

	/**
	 * Method declaration for getting BAB recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            orderContact
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	public List<BoughtAlsoBoughtInfoVO> getBoughtAlsoBoughtRecommendations(
			String custNum, String orderContact,String loggedUser) throws DashboardException;

	/**
	 * Method declaration for getting BAB recommendations info.
	 * 
	 * @param List
	 *            <String> recSkuList
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getBABRecommendationInfo(
			List<String> recSkuList) throws DashboardException;

	/**
	 * Method declaration for getting view not bought recommendations info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommendationViewNotBoughtVO>
	 */
	public List<RecommendationViewNotBoughtVO> getViewNotBoughtRecommendation(
			String custNum) throws DashboardException;

	/**
	 * Method declaration for getting reorder recommendations info.
	 * @param loggerUser
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	public List<ReorderRecommendationVO> getReorderRecommendations(
			String custNum, String loggerUser) throws DashboardException;

	/**
	 * Method declaration for getting Online-Retail reorder recommendations info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	public List<OnlineRetailReorderRecommendationVO> getOnlineRetailReorderRecommendations(
			String custNum, String loggedInUser) throws DashboardException;

	
	/**
	 * Method declaration for getting order contacts.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommOrderContactVO>
	 */
	public List<RecommOrderContactVO> getOrderContacts(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting class recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getClassRecommendations(String custNum)
			throws DashboardException;

	/**
	 * Method declaration for getting account number.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	public String getAccountNumber(String custNum) throws DashboardException;

	/**
	 * Method declaration to view super user details.
	 * 
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void viewSuperUserDetails(String custNum,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration to view YTD spend.
	 * 
	 * @param String
	 *            selYear
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void viewYTDSpend(String selYear, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration to view order purchase details.
	 * 
	 * @param PurchaseDetailsSelectedVO
	 *            objPurchDtlsSelectedVO
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void viewOrderPurchaseDetails(
			PurchaseDetailsSelectedVO objPurchDtlsSelectedVO, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration to populate categories.
	 * 
	 * @param String
	 *            string
	 * @return List<CategoryVO>
	 */
	public List<CategoryVO> populateCategories(String string)
			throws DashboardException;

	/**
	 * Method declaration to calculate YTD spend.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param List
	 *            <YTDSummaryVO> objYTDSummaryListVO
	 */
	public void calcualteYTDSpend(YTDSummaryVO objSummaryVO,
			List<YTDSummaryVO> objYTDSummaryListVO) throws DashboardException;

	/**
	 * Method declaration to build YTD spend table.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 */
	public void buildYTDSpendTable(YTDSummaryVO objSummaryVO)
			throws DashboardException;

	/**
	 * Method declaration to build data for category chart.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void buildDataForCategoryChart(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration to build data for month chart.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void buildDataForMonthChart(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration for getting BAB info and recommended map vo.
	 * 
	 * @param List
	 *            <BoughtAlsoBoughtInfoVO> recommendedVO
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	public List<BoughtAlsoBoughtInfoVO> mapBABInfoAndRecommendedVo(
			List<BoughtAlsoBoughtInfoVO> recommendedVO)
			throws DashboardException;

	/**
	 * Method declaration to view ShipTo Details.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ShipToVO>
	 */
	public List<ShipToVO> viewShipToDetails(String custNum,String location)
			throws DashboardException;

	/**
	 * Method declaration to view YTD spend for other years.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selYear
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	public void viewYTDSpendForOtherYears(String custNum, String selYear,
			CustomerDataVo customerDataVo) throws DashboardException;

	/**
	 * Method declaration for getting sub play list.
	 * 
	 * @param String
	 *            forPlayType
	 * @param String
	 *            AccId
	 * @return List<SubplayInfoVo>
	 */
	public List<SubplayInfoVo> getSubPlayList(String forPlayType, String AccId)
			throws DashboardException;

	/**
	 * Method declaration for getting agent rep role code.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	public String getAgentRepRoleCd(String agentId) throws DashboardException;
	
	/**
	 * Method declaration for getting agent name.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	public String getAgentName(String agentId) throws DashboardException;

	/**
	 * Method declaration for getting Last Data Refresh Time.
	 * 
	 * @param processName
	 * @return String LastRefreshDate
	 * @throws DashboardException
	 */
	public String getLastRefreshTime(String processName)
			throws DashboardException;
	
	public String getCurrentRefreshTime() throws DashboardException;

	public List<ShipToDetailsVo> viewShipToDetailsInfo(String shipTo, String custNum);

	public List<PurchaseHeliosDetailsVO> getOrderDetailsShipTo(String custNum,
			String orderNum, String orderDate);
	
	public String getMasterAccountZipCode(String customerNumber) throws DashboardException;

	public Map<String, List<Object>> getFilterData();

	public SbaDiffDetailsVo getSbaDiffDetails(String custId) throws DashboardException;
	
	public CustomerListDTO getAllSamCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO tableInput,String[] selectedQualScore,String[] selectedSegScorer) throws DashboardException;
	public String getLatestFiscalDateForLead(String custNum) throws DashboardException;
	
	public SavingsVo getSavingsInfo(String custId) throws DashboardException;
	public List<Object> getCatPenInfo(String custId) throws DashboardException;
	
	public void viewYTDSpendSAM(String selYear, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException;
	public void viewOrderDetailsSAM(
			PurchaseDetailsSelectedVO objPurchDtlsSelectedVO, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException;
	/**
	 * Method is used to generate Savings Report for Retail customers
	 * @param custNum
	 * @return
	 * @throws DashboardException
	 */
	public List<SavingsDetailDTO> generateSavingsReport(String custNum) throws DashboardException;
	
	public List<SavingsDetailDTO> generateSamSavingsReport(String custNum) throws DashboardException;
	

	public ContactDateVO getLatestFiscalContactedDate() throws DashboardException;


	/**
	 * Save Task to Salesforce.com using Rest API
	 * @param inputSfdc
	 * @return
	 * @throws DashboardException
	 */
	public OutputTasksfdc saveTaskToSfdc(InputTaskSalesforceDTO inputSfdc) throws DashboardException;
	
	public SegHdrText getSubCtaDeatils(String seg, String segId, String custNum) throws DashboardException;
	
	public SegHdrText getAdminSubCtaDeatils(String seg, String segId) throws DashboardException;
	
	public String updateSubCtaDeatils(String segId, String subSegId, String headerIds, String headerContent,String headerCol) throws DashboardException;

	public List<DispositionDetailsVo> getDespositionDetails(String taskCombinationId, String frequency);
	
	public String refreshPlaySegmentParam(String custNum,String segId) throws DashboardException;
	
	public List<SegmentSubDetail> getSegSubDetail(String custNum, String segId, String subSegId) throws DashboardException;

	public List<SegMktgResources> getSegMktgResources(String segId,String subSegId,String mktResourceUrl);
	public Task getLastLiveContactDetailsFromSfdc(String custId) throws DashboardException;
	
	public CustomerListDTO getAllCdmCustomerInfo(JQueryDataTableInputDTO dataTableInput, String selTimeZone,String accId, String rowId,String alertStateStatus);
	
	public String checkAccountHoldoutStatus(String custNum,String accId) throws DashboardException;
	
	public List<OrderContactInfoVO>getCustomerOrderContactDetails(String custNum,String iamId) throws DashboardException;

	public AccountProgressInfo getTodaysAndWeekProgress(String repNum) throws DashboardException;
	
	public   List<NotificationInfoVo>  getSegmentDetails(String segId) throws DashboardException;	

	public LastLiveContactAndHistoryVO getLastLiveContactDetails(String iamId,String custNumber) throws DashboardException;
	
	public String updateAlertStatus(String custId, String ldapUserRole, String ldapUserId,String ldapUserName) throws DashboardException, DashboardException;
	
	public List<AlertDetailVo> getAlertDetail(String custNum) throws DashboardException;
	
	public String deleteAlert(String custNum, String alertIdCombination, String alertId, String ldapUserId)throws DashboardException;
	
	public String updateReadAlert(String custNum, String alertIdCombination, String alertId,String ldapUserId)throws DashboardException;
	
	public String getLatestFiscaCompleteOrderDate(String custNum) throws DashboardException;
}