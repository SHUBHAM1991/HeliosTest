package com.staples.dashboard.app.dao.dbvo;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContext;

import com.staples.dashboard.app.sdc.vo.PriceLog;
import com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO;
import com.staples.dashboard.app.vo.AbabdonedCartVO;
import com.staples.dashboard.app.vo.AccountProgressInfo;
import com.staples.dashboard.app.vo.AlertDetailVo;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.ConfigSfdcVO;
import com.staples.dashboard.app.vo.ContactDateVO;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.CustRecommendationVO;
import com.staples.dashboard.app.vo.CustomerDataVo;
import com.staples.dashboard.app.vo.CustomerUserVO;
import com.staples.dashboard.app.vo.DispositionDetailsVo;
import com.staples.dashboard.app.vo.DotcomAcitivityVO;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.LastLiveContactAndHistoryVO;
import com.staples.dashboard.app.vo.LogRecommendationVO;
import com.staples.dashboard.app.vo.NephosConfig;
import com.staples.dashboard.app.vo.NotificationInfoVo;
import com.staples.dashboard.app.vo.OnlineRetailReorderRecommendationVO;
import com.staples.dashboard.app.vo.OrderContactInfoVO;
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
import com.staples.dashboard.app.vo.SfdcInputId;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToVO;
import com.staples.dashboard.app.vo.SubplayInfoVo;
import com.staples.dashboard.app.vo.SuperUserInfoVO;
import com.staples.dashboard.app.vo.YTDInfoVO;
import com.staples.dashboard.app.vo.YTDSummaryVO;
import com.staples.dashboard.dto.CustomerListDTO;
import com.staples.dashboard.dto.SavingsDetailDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Interface CustProfileDAO.
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
public interface CustProfileDAO {

	/**
	 * Method declaration to fetch customer profile.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustProfileVO>
	 */
	public List<CustProfileVO> getCustomerProfile(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch YTD info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<YTDInfoVO>
	 */
	public List<YTDInfoVO> getYTDInfo(String custNum) throws DashboardException;

	/**
	 * Method declaration to fetch purchase details.
	 * @param catId 
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
	 * Method declaration to fetch purchase rewards details.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            mnth
	 * @param String
	 *            year
	 * @return List<PurchaseHeliosDetailsVO>
	 */
	public List<PurchaseHeliosDetailsVO> getPurchaseRewardsDetails(
			String custNum, long [] orderNumberList) throws DashboardException;

	/**
	 * Method declaration to fetch super user info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SuperUserInfoVO>
	 */
	public List<SuperUserInfoVO> getSuperUserInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch abandoned cart info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<AbabdonedCartVO>
	 */
	public List<AbabdonedCartVO> getAbandonedCartInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch customer recommendation info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getCustRecommendationInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch dot com activity info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<DotcomAcitivityVO>
	 */
	public List<DotcomAcitivityVO> getDotcomActivityInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch search item list.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SearchVO>
	 */
	public List<SearchVO> getSearchItemsList(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch YTD summary.
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
	 * Method declaration to fetch notification info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<NotificationInfoVo>
	 */
	public List<NotificationInfoVo> getNotiInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch all customer info.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selPlaySection
	 * @param String
	 *            [] selectedSubplayArr
	 * @param JQueryDataTableInputDTO
	 *            dataTableInput
	 * @return CustomerListDTO
	 */
	public CustomerListDTO getAllCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO dataTableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException;

	/**
	 * Method declaration to fetch customer value.
	 * 
	 * @param String
	 *            custNum
	 * @return CustomerDataVo
	 */
	public CustomerDataVo getCustomerValue(String cusNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch shipTo info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ShipToVO>
	 */
	public List<ShipToVO> getShipToInfo(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch latest fiscal data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	public String getLatestFiscalDate(String custNum) throws DashboardException;

	/**
	 * Method declaration to fetch latest fiscal data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	public String getLatestFiscalDate1(String custNum)
			throws DashboardException;
	/**
	 * Method declaration to fetch latest fiscal data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	public String getLatestOrderReturnDate(String custNum)
			throws DashboardException;
	
	/**
	 * Method declaration to fetch customer number.
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
	 * Method declaration to fetch bought also bought recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            orderContact
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	public List<BoughtAlsoBoughtInfoVO> getBoughtAlsoBoughtRecommendations(
			String custNum, String orderContact) throws DashboardException;

	/**
	 * Method declaration to fetch the Bought Also Bought Recommendations for
	 * users.
	 * 
	 * @param List
	 *            <String> recSkuList
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getBABRecommendationInfo(
			List<String> recSkuList) throws DashboardException;

	/**
	 * Method declaration to fetch recommendation view not bought.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommendationViewNotBoughtVO>
	 */
	public List<RecommendationViewNotBoughtVO> getViewNotBoughtRecommendation(
			String custNum) throws DashboardException;

	/**
	 * Method declaration to fetch reorder recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	public List<ReorderRecommendationVO> getReorderRecommendations(
			String custNum) throws DashboardException;

	public List<OnlineRetailReorderRecommendationVO> getOnlineRetailReorderRecommendations(
			String custNum) throws DashboardException;
	/**
	 * Method declaration to fetch order contacts.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommOrderContactVO>
	 */
	public List<RecommOrderContactVO> getOrderContacts(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch class recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	public List<CustRecommendationVO> getClassRecommendations(String custNum)
			throws DashboardException;

	/**
	 * Method declaration to fetch sub play list.
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
	 * Method declaration to fetch account number.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	public String getAccountNumber(String custNum) throws DashboardException;

	/**
	 * Method declaration to fetch agent's rep role code.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	public String getAgentRepRoleCd(String agentId) throws DashboardException;
	
	/**
	 * Method declaration to fetch agent's name.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	public String getAgentName(String agentId) throws DashboardException;

	/**
	 * Method declaration to fetch Last Refresh Date.
	 * 
	 * @param String
	 *            processName
	 * @return String LastRefreshTime of the Data
	 * @throws DashboardException
	 */
	public String getLastRefreshTime(String processName)
			throws DashboardException;
	
	public String getCurrentRefreshTime() throws DashboardException;

	public List<ShipToDetailsVo> getShipToDetails(String shipTo, String custNum);

	public List<PurchaseHeliosDetailsVO> getOrderDetailsShipTo(String custNum,
			String orderNum, String orderDate);
	
	public String getMasterAccountZipCode(String customerNumber) throws DashboardException;

	public Map<String, List<Object>> getFilterData();

	public boolean getPremiumUser(String customerNumber) throws DashboardException;
	
	public SbaDiffDetailsVo getSbaDiffDetails(String custId) throws DashboardException;
	
	public CustomerListDTO getAllSamCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO dataTableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException;
	
	public String getLatestFiscalDateForLead(String custNum) throws DashboardException;

	public SavingsVo getSavingsInfo(String custId) throws DashboardException;
	
	public List<Object> getCatPenInfo(String custId) throws DashboardException;
	
	public List<YTDSummaryVO> getYTDSummarySAM(String custNum, String selectedYear)
			throws DashboardException;
	public List<PurchaseDetailsVO> getOrderHeaderSAM(String custNum) throws DashboardException;
	public List<PurchaseHeliosDetailsVO> getOrderDetailsSAM(
			String custNum, long [] orderNumberList) throws DashboardException;
	/**
	 * @param custNum
	 * @return
	 * @throws DashboardException
	 */
	public List<SavingsDetailDTO> generateSavingsReport(String custNum) throws DashboardException;
	

	public List<SavingsDetailDTO> generateSamSavingsReport(String custNum) throws DashboardException;
	/**
	 * @return
	 * @throws DashboardException
	 */
	public NephosConfig getNephosServiceConfig() throws DashboardException;
	
	
	/**
	 * @return
	 * @throws DashboardException
	 */
	public NephosConfig getNephosArkeCISServiceConfig() throws DashboardException;
	
	
	public void insertPriceLog(PriceLog priceLog) throws DashboardException;
	
	/**
	 * @param token
	 * @return
	 * @throws DashboardException
	 */
	public boolean refreshAccessToken(String token, String serviceName) throws DashboardException;
	
	/**
	 * @return
	 * @throws DashboardException
	 */
	public ConfigSfdcVO getSfdcConfig() throws DashboardException;
	/**
	 * @param inputDTO
	 * @return
	 * @throws DashboardException
	 */
	public SfdcInputId getSfdcId(InputTaskSalesforceDTO inputDTO) throws DashboardException;
	
	public ContactDateVO getLatestFiscalContactedDate() throws DashboardException;
	
	public List<ContactDateVO> getContactedCustList() throws DashboardException;

	public int deleteCustNumFromTaskTracker(String listCustId) throws DashboardException;

	public SegHdrText getSubCtaDeatils(String seg, String segId, String custNum) throws DashboardException;
	
	public SegHdrText getAdminSubCtaDeatils(String seg, String segId) throws DashboardException;
	
	public String updateSubCtaDeatils(String segId, String subSegId, String headerIds, String headerContent,String headerCol) throws DashboardException;

	public List<DispositionDetailsVo> getDespositionDetails(String taskCombinationId, String frequency);
	
	public String refreshPlaySegmentParam(String custNum,String segId) throws DashboardException;
	
	public List<SegmentSubDetail> getSegSubDetail(String custNum, String segId, String subSegId) throws DashboardException;

	public List<SegMktgResources> getSegMktgResources(String segId,	String subSegId, String mktResourceUrl);
	
	public CustomerListDTO getAllCdmCustomerInfo(JQueryDataTableInputDTO dataTableInput ,String selTimeZone,String accId, String rowId , String alertStateStatus);
	public String  getOrderContactName(String orderContactId);
	
	public String checkAccountHoldoutStatus(String custNum,String accId) throws DashboardException;
	
	public List<OrderContactInfoVO>  getCustomerOrderContactDetails(String custNum) throws DashboardException;

	public AccountProgressInfo getTodaysAndWeekProgress(String repNum) throws DashboardException;
	
	public   List<NotificationInfoVo>  getSegmentDetails(String segId) throws DashboardException;
	
	// SPARKS Recommedation Changes
	
	public List<CustomerUserVO> getCustomerEmailId(String custNum);

	public ReorderRecommendationVO getSKUInfo(ReorderRecommendationVO sku);

	public void logRecommendation(LogRecommendationVO logRecommendationVO);
	
	public LastLiveContactAndHistoryVO getLastLiveContactDetails(String iamId,String custNumber);

	public CustRecommendationVO getBABSKUInfo(String skuNumber);

	public Map<String, List<String>> getBoughtSkus(String custNum, String orderContact);
	
	public String updateAlertStatus(String custId,String ldapUserRole, String ldapUserId,String ldapUserName) throws DashboardException, DashboardException;
	
	public List<AlertDetailVo> getAlertDetail(String custNum) throws DashboardException;
	
	public String deleteAlert(String custNum, String alertIdCombination, String alertId, String ldapUserId)throws DashboardException;
	
	public String updateReadAlert(String custNum, String alertIdCombination, String alertId,String ldapUserId)throws DashboardException;
	
	public String getLatestFiscaCompleteOrderDate(String custNum) throws DashboardException;
    
	public List<ShipToVO> getShipToInfo(String custNum,String location)
			throws DashboardException;
}
