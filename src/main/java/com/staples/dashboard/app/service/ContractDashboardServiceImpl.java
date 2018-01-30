package com.staples.dashboard.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.force.api.ApiConfig;
import com.force.api.ApiVersion;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.OAuthConstants;
import com.staples.dashboard.app.dao.dbvo.CustProfileDAO;
import com.staples.dashboard.app.sdc.vo.ArkeCISVO;
import com.staples.dashboard.app.sdc.vo.PriceLog;
import com.staples.dashboard.app.sfdc.Contact;
import com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO;
import com.staples.dashboard.app.sfdc.OutputTasksfdc;
import com.staples.dashboard.app.sfdc.Task;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.utilities.LoggerUtil;
import com.staples.dashboard.app.utilities.NumberFormatUtil;
import com.staples.dashboard.app.utilities.OAuthUtility;
import com.staples.dashboard.app.utilities.StatementCheckParser;
import com.staples.dashboard.app.utilities.UserSession;
import com.staples.dashboard.app.utilities.YTDSummaryChart;
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
import com.staples.dashboard.app.vo.Item;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.KeyValue;
import com.staples.dashboard.app.vo.LastLiveContactAndHistoryVO;
import com.staples.dashboard.app.vo.LogRecommendationVO;
import com.staples.dashboard.app.vo.NephosConfig;
import com.staples.dashboard.app.vo.NephosResponseVO;
import com.staples.dashboard.app.vo.NotificationInfoVo;
import com.staples.dashboard.app.vo.OAuth2Details;
import com.staples.dashboard.app.vo.OnlineRetailReorderRecommendationVO;
import com.staples.dashboard.app.vo.OrderContactInfoVO;
import com.staples.dashboard.app.vo.ProductVO;
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
import com.staples.dashboard.app.vo.SfdcInputId;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToVO;
import com.staples.dashboard.app.vo.SkuPricingInputVO;
import com.staples.dashboard.app.vo.SubAccountDetailsVO;
import com.staples.dashboard.app.vo.SubplayInfoVo;
import com.staples.dashboard.app.vo.SuperUserInfoVO;
import com.staples.dashboard.app.vo.UserFlagsNameValuePair;
import com.staples.dashboard.app.vo.UserInfo;
import com.staples.dashboard.app.vo.YTDInfoVO;
import com.staples.dashboard.app.vo.YTDSummaryDataVO;
import com.staples.dashboard.app.vo.YTDSummarySelectedVO;
import com.staples.dashboard.app.vo.YTDSummaryVO;
import com.staples.dashboard.app.xmlconfig.YTDSummaryParser;
import com.staples.dashboard.app.xmlconfig.vo.CategoryListVO;
import com.staples.dashboard.app.xmlconfig.vo.CategoryVO;
import com.staples.dashboard.app.xmlconfig.vo.StatementCheckVO;
import com.staples.dashboard.app.xmlconfig.vo.YTDSummaryLayoutVO;
import com.staples.dashboard.dto.CustomerListDTO;
import com.staples.dashboard.dto.SavingsDetailDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class ContractDashboardServiceImpl.
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
@Service
public class ContractDashboardServiceImpl implements ContractDashboardService {
	private static final Logger LOGGER = Logger
			.getLogger(ContractDashboardServiceImpl.class);
	@Autowired
	private CustProfileDAO contractDao;
	
	@Resource
	private Map<String, String> appSettings;
	@Resource
	private Map<String, String> chartSetting;
	private final String[] arrMonthName = { "FP 01", "FP 02", "FP 03", "FP 04",
			"FP 05", "FP 06", "FP 07", "FP 08", "FP 09", "FP 10", "FP 11",
			"FP 12" };
	private final String[] arrMonthNameSam = { "JAN", "FEB", "MAR", "APR",
			"MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV","DEC"};
	// Sparkx recommendation Changes
	@Value("${RECOMM_SBA_TRILLION_USER_TKN}")
	private String strUserToken;
	@Value("${RECOMM_SBA_TRILLION_MERCHANT_ID}")
	private String strMerchantId;
	@Value("${RECOMM_TRILLION_SVC}")
	private String strURL;
	private LogRecommendationVO logRecommendationVO;
	private String scheme;
	private int count;
	
	@Value("${mmpivot.pricev4:false}")
	private boolean mmpivotPriceV4;
	
	@Value("${enablePriceLog:false}")
	private boolean enablePriceLog;
		
	/**
	 * @return the contractDao
	 */
	/*public CustProfileDAO getContractDao() {
		return contractDao;
	}*/

	/**
	 * @param contractDao
	 *            the contractDao to set
	 */
	/*public void setContractDao(CustProfileDAO contractDao) {
		this.contractDao = contractDao;
	}*/

	/**
	 * Method implementation for getting customer profile.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustProfileVO>
	 */
	@Override
	public List<CustProfileVO> getCustomerProfile(String custNum)
			throws DashboardException {
		try {
			List<CustProfileVO> custProfileVO = contractDao
					.getCustomerProfile(custNum);
			custProfileVO=configuredWithContactedCustomers(custProfileVO);
			return custProfileVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting YTD information.
	 * 
	 * @param String
	 *            custNum
	 * @return List<YTDInfoVO>
	 */
	@Override
	public List<YTDInfoVO> getYTDInfo(String custNum) throws DashboardException {
		try {
			List<YTDInfoVO> ytdInfoVOList = contractDao.getYTDInfo(custNum);
			return ytdInfoVOList;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting purchase details.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            mnth
	 * @param String
	 *            year
	 * @return List<PurchaseDetailsVO>
	 */
	@Override
	public List<PurchaseDetailsVO> getPurchaseDetails(String custNum,
			String mnth, String year,String catId) throws DashboardException {
		try {
			List<PurchaseDetailsVO> objPurchaseDetailsListVO = contractDao
					.getPurchaseDetails(custNum, mnth, year,catId);
			return objPurchaseDetailsListVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting purchase rewards details.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            mnth
	 * @param String
	 *            year
	 * @return List<PurchaseRewardsDetailsVO>
	 */
	@Override
	public List<PurchaseHeliosDetailsVO> getPurchaseRewardsDetails(
			String custNum, long [] orderNumberList) throws DashboardException {
		try {
			List<PurchaseHeliosDetailsVO> objPurchRwdsDetailsListVO = contractDao
					.getPurchaseRewardsDetails(custNum, orderNumberList);
			return objPurchRwdsDetailsListVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting super user info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SuperUserInfoVO>
	 */
	@Override
	public List<SuperUserInfoVO> getSuperUserInfo(String custNum)
			throws DashboardException {
		try {
			List<SuperUserInfoVO> superUserInfoVList = contractDao
					.getSuperUserInfo(custNum);
			return superUserInfoVList;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting abandoned cart info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<AbabdonedCartVO>
	 */
	@Override
	public List<AbabdonedCartVO> getAbandonedCartInfo(String custNum)
			throws DashboardException {
		try {
			List<AbabdonedCartVO> objAbandonedCartListVO = contractDao
					.getAbandonedCartInfo(custNum);
			return objAbandonedCartListVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting customer recommendation info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getCustRecommendationInfo(String custNum)
			throws DashboardException {
		try {
			List<CustRecommendationVO> objCustRecommendationVO = contractDao
					.getCustRecommendationInfo(custNum);
			return objCustRecommendationVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting dot com activity info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<DotcomAcitivityVO>
	 */
	@Override
	public List<DotcomAcitivityVO> getDotcomActivityInfo(String custNum)
			throws DashboardException {
		try {
			List<DotcomAcitivityVO> objDotcomActivityVO = contractDao
					.getDotcomActivityInfo(custNum);
			return objDotcomActivityVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting item list.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SearchVO>
	 */
	@Override
	public List<SearchVO> getSearchItemsList(String custNum)
			throws DashboardException {
		try {
			List<SearchVO> objSearchItemsVO = contractDao
					.getSearchItemsList(custNum);
			return objSearchItemsVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting YTD summary info.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selectedYear
	 * @return List<YTDSummaryVO>
	 */
	@Override
	public List<YTDSummaryVO> getYTDSummary(String custNum, String selectedYear)
			throws DashboardException {
		try {
			List<YTDSummaryVO> objYTDSummaryListVO = contractDao.getYTDSummary(
					custNum, selectedYear);
			return objYTDSummaryListVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting notification info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<NotificationInfoVo>
	 */
	@Override
	public List<NotificationInfoVo> getNotiInfo(String custNum)
			throws DashboardException {
		try {
			List<NotificationInfoVo> notiInfoVOList = contractDao
					.getNotiInfo(custNum);
			return notiInfoVOList;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting customer info.
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
	@Override
	public CustomerListDTO getAllCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO tableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException {
		try {
			CustomerListDTO custDTO = contractDao.getAllCustomerInfo(accId,
					selPlaySection, selectedSubplayArr, tableInput, selectedQualScore,selectedSegScore);
			List<CustProfileVO> listOfCustProfileVO = custDTO
					.getCustomerVoList();
			if(null !=listOfCustProfileVO && listOfCustProfileVO.size()>0)
				listOfCustProfileVO=configuredWithContactedCustomers(listOfCustProfileVO);
			
			/*if (tableInput.getStatusSort()){
				if(tableInput.getsSortDir_0().equalsIgnoreCase("ASC"))
					Collections.sort(listOfCustProfileVO);
				else
					Collections.sort(listOfCustProfileVO,Collections.reverseOrder());
			}*/
			custDTO.setCustomerVoList(listOfCustProfileVO);
			return custDTO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting customer value.
	 * 
	 * @param String
	 *            cusNum
	 * @return CustomerDataVo
	 */
	@Override
	public CustomerDataVo getCustomerValue(String cusNum)
			throws DashboardException {
		try {
			CustomerDataVo customerDataVo = contractDao
					.getCustomerValue(cusNum);
			return customerDataVo;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting shipTo info.
	 * 
	 * @param String
	 *            cusNum
	 * @return List<ShipToVO>
	 */
	@Override
	public List<ShipToVO> getShipToInfo(String custNum)
			throws DashboardException {
		try {
			List<ShipToVO> shipToList = new ArrayList<ShipToVO>();
			shipToList = contractDao.getShipToInfo(custNum);
			return shipToList;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	@Override
	public String getLatestFiscalDate(String custNum) throws DashboardException {
		try {
			String latestFiscalDate = contractDao.getLatestFiscalDate(custNum);
			return latestFiscalDate;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	@Override
	public String getLatestFiscalDate1(String custNum)
			throws DashboardException {
		try {
			String latestFiscalDate = contractDao.getLatestFiscalDate1(custNum);
			return latestFiscalDate;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	/**
	 * Method implementation for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	@Override
	public String getLatestOrderReturnDate(String custNum)
			throws DashboardException {
		try {
			String latestFiscalDate = contractDao.getLatestOrderReturnDate(custNum);
			return latestFiscalDate;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	/**
	 * Method implementation for getting customer number.
	 * 
	 * @param String
	 *            searchText
	 * @param String
	 *            acctId
	 * @return String
	 */
	@Override
	public String getCustomerNumber(String searchText, String acctId, String custNum ,
			String empLevel) throws DashboardException {
		try {
			String custNumber = contractDao.getCustomerNumber(searchText,
					acctId, custNum, empLevel);
			return custNumber;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting BAB recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            orderContact
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	@Override
	public List<BoughtAlsoBoughtInfoVO> getBoughtAlsoBoughtRecommendations(
			String custNum, String orderContact,String loggedUser) throws DashboardException {
		try {
			List<BoughtAlsoBoughtInfoVO> recommendedVO = null;
			/*recommendedVO = contractDao.getBoughtAlsoBoughtRecommendations(
					custNum, orderContact);*/
			recommendedVO= getBoughtAlsoBoughtRecommendation(custNum,orderContact,loggedUser);
			return recommendedVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting BAB recommendations info.
	 * 
	 * @param List
	 *            <String> recSkuList
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getBABRecommendationInfo(
			List<String> recSkuList) throws DashboardException {
		try {
			List<CustRecommendationVO> recomVo = null;
			recomVo = contractDao.getBABRecommendationInfo(recSkuList);
			return recomVo;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting view not bought recommendations info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommendationViewNotBoughtVO>
	 */
	@Override
	public List<RecommendationViewNotBoughtVO> getViewNotBoughtRecommendation(
			String custNum) throws DashboardException {
		try {
			List<RecommendationViewNotBoughtVO> recommendedVO = null;
			recommendedVO = contractDao.getViewNotBoughtRecommendation(custNum);
			return recommendedVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting reorder recommendations info.
	 * @param String
	 * 			 loggedUser	
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	@Override
	public List<ReorderRecommendationVO> getReorderRecommendations(
			String custNum,String loggedUser) throws DashboardException {
		try {
			List<ReorderRecommendationVO> recommendedVO = null;
			//recommendedVO = contractDao.getReorderRecommendations(custNum);
			// SparX Recommendation changes
			List<CustomerUserVO> customerEmailId = null;
			customerEmailId = contractDao.getCustomerEmailId(custNum);
			recommendedVO = getSparkxReorderRecommendation(customerEmailId, loggedUser);

			return recommendedVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	/**
	 * Method implementation for getting Online-Retail reorder recommendations info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	@Override
	public List<OnlineRetailReorderRecommendationVO> getOnlineRetailReorderRecommendations(
			String custNum, String loggedInUser) throws DashboardException {
		try {
			List<OnlineRetailReorderRecommendationVO> recommendedVO = null;
			recommendedVO = contractDao.getOnlineRetailReorderRecommendations(custNum);
			recommendedVO=mapNephosResponseRecom(recommendedVO,custNum, loggedInUser);
			return recommendedVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting order contacts.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommOrderContactVO>
	 */
	@Override
	public List<RecommOrderContactVO> getOrderContacts(String custNum)
			throws DashboardException {
		List<RecommOrderContactVO> recommendedVO = null;
		try {
			recommendedVO = contractDao.getOrderContacts(custNum);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return recommendedVO;
	}

	/**
	 * Method implementation for getting class recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getClassRecommendations(String custNum)
			throws DashboardException {
		List<CustRecommendationVO> recommendedVO = null;
		try {
			recommendedVO = contractDao.getClassRecommendations(custNum);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return recommendedVO;
	}

	/**
	 * Method implementation for getting account number.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@Override
	public String getAccountNumber(String custNum) throws DashboardException {
		try {
			String accountNumber = contractDao.getAccountNumber(custNum);
			return accountNumber;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation to view super user details.
	 * 
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void viewSuperUserDetails(String custNum,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		String rwdNmbr = custNum;

		try {
			List<SuperUserInfoVO> superUserInfoVList = contractDao
					.getSuperUserInfo(rwdNmbr);
			customerDataVo.setSuperUserInfoVList(superUserInfoVList);

			if (superUserInfoVList.size() > 0) {

				// Get the Abandoned cart info
				List<AbabdonedCartVO> objAbandonedCartListVO = contractDao
						.getAbandonedCartInfo(rwdNmbr);

				// Get cust recommendation
				List<CustRecommendationVO> objCustRecommendationVO = contractDao
						.getCustRecommendationInfo(rwdNmbr);

				// Get Dotcom Activity
				List<DotcomAcitivityVO> objDotcomActivityVO = contractDao
						.getDotcomActivityInfo(rwdNmbr);

				// Get Search string
				List<SearchVO> objSearchItemsVO = contractDao
						.getSearchItemsList(rwdNmbr);
				mapSuperUserHeaderWithLineIems(superUserInfoVList,
						objAbandonedCartListVO, objCustRecommendationVO,
						objDotcomActivityVO, objSearchItemsVO);
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	/**
	 * Method implementation to map super user header within line items.
	 * 
	 * @param List
	 *            <SuperUserInfoVO> objSuperUsersListVO
	 * @param List
	 *            <AbabdonedCartVO> objAbandonedCartListVO
	 * @param List
	 *            <CustRecommendationVO> objCustRecommendationVO
	 * @param List
	 *            <DotcomAcitivityVO> objDotcomActivityVO
	 * @param List
	 *            <SearchVO> objSearchItemsVO
	 */
	public void mapSuperUserHeaderWithLineIems(
			List<SuperUserInfoVO> objSuperUsersListVO,
			List<AbabdonedCartVO> objAbandonedCartListVO,
			List<CustRecommendationVO> objCustRecommendationVO,
			List<DotcomAcitivityVO> objDotcomActivityVO,
			List<SearchVO> objSearchItemsVO) {
		Iterator<SuperUserInfoVO> superUserIte = objSuperUsersListVO.iterator();
		SuperUserInfoVO objSuperUsersVO = null;
		while (superUserIte.hasNext()) {
			objSuperUsersVO = superUserIte.next();
			Iterator<AbabdonedCartVO> abanCartDtlsIte = objAbandonedCartListVO
					.iterator();
			AbabdonedCartVO objAbandonedCartsVO = null;
			List<AbabdonedCartVO> newAbanCartDtlsListVO = new ArrayList<AbabdonedCartVO>();
			while (abanCartDtlsIte.hasNext()) {
				objAbandonedCartsVO = abanCartDtlsIte.next();
				if (objSuperUsersVO.getOrderContact().equalsIgnoreCase(
						objAbandonedCartsVO.getOrderContact())) {
					newAbanCartDtlsListVO.add(objAbandonedCartsVO);
				}
			}

			Iterator<CustRecommendationVO> custRecDtlsIte = objCustRecommendationVO
					.iterator();
			CustRecommendationVO objCustRecListVO = null;
			List<CustRecommendationVO> newCustRecListVO = new ArrayList<CustRecommendationVO>();
			while (custRecDtlsIte.hasNext()) {
				objCustRecListVO = custRecDtlsIte.next();
				if (objSuperUsersVO.getOrderContact().equalsIgnoreCase(
						objCustRecListVO.getOrderContact())) {
					newCustRecListVO.add(objCustRecListVO);
				}
			}

			Iterator<DotcomAcitivityVO> dotcomActivityIte = objDotcomActivityVO
					.iterator();
			DotcomAcitivityVO objDotcomActVO = null;
			List<DotcomAcitivityVO> newDotcomActivityVO = new ArrayList<DotcomAcitivityVO>();
			while (dotcomActivityIte.hasNext()) {
				objDotcomActVO = dotcomActivityIte.next();
				if (objSuperUsersVO.getOrderContact().equalsIgnoreCase(
						objDotcomActVO.getOrderContact())) {
					newDotcomActivityVO.add(objDotcomActVO);
				}
			}

			Iterator<SearchVO> searchItemsIte = objSearchItemsVO.iterator();
			SearchVO objSearchItmVO = null;
			List<SearchVO> newSearchItmVO = new ArrayList<SearchVO>();
			while (searchItemsIte.hasNext()) {
				objSearchItmVO = searchItemsIte.next();
				if (objSuperUsersVO.getOrderContact().equalsIgnoreCase(
						objSearchItmVO.getOrderContact())) {
					newSearchItmVO.add(objSearchItmVO);
				}
			}

			objSuperUsersVO.setAbandonedCartListVO(newAbanCartDtlsListVO);
			objSuperUsersVO.setCustRecommendationListVO(newCustRecListVO);
			objSuperUsersVO.setDotcomActivityVO(newDotcomActivityVO);
			objSuperUsersVO.setSearchItemsListVO(newSearchItmVO);

		}
	}

	/**
	 * Method implementation to view order purchase details.
	 * 
	 * @param PurchaseDetailsSelectedVO
	 *            objPurchDtlsSelectedVO
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void viewOrderPurchaseDetails(
			PurchaseDetailsSelectedVO objPurchDtlsSelectedVO, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;

		if (null != objPurchDtlsSelectedVO) {
			if (null != objPurchDtlsSelectedVO.getSelectedCategory()) {
				catId = objPurchDtlsSelectedVO.getSelectedCategory();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				mnth = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								0,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4);
			}
			if (null != objPurchDtlsSelectedVO.getSelectedLocation()) {
				chnlCd = objPurchDtlsSelectedVO.getSelectedLocation();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				yr = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length());
			}
			if (null != objPurchDtlsSelectedVO.getSelectedSubAcctMbr()) {
				rwdNmbr = objPurchDtlsSelectedVO.getSelectedSubAcctMbr();
			}
		}
		String tranDate = null;
		String strNmb = null;
		String tranId = null;

		String tranIdOtherScreen = null;

		try {

			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();
			customerDataVo.setMonYearList(monYearList);

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				yr = Integer.toString(cal.getTime().getYear() + 1900);
			}

			List<PurchaseDetailsVO> objPurchaseDetailsListVO = contractDao
					.getPurchaseDetails(rwdNmbr, mnth, yr,catId);
			if (objPurchaseDetailsListVO.size() > 0) {
				long [] orderNumberList = getOrderNumberList(objPurchaseDetailsListVO);
				List<PurchaseHeliosDetailsVO> objPurchRwdsDetailsListVO = contractDao
						.getPurchaseRewardsDetails(rwdNmbr, orderNumberList);
				mapPurchaseHeaderWithLineIems(objPurchaseDetailsListVO,
						objPurchRwdsDetailsListVO);
				if ((catId != null && !catId.equalsIgnoreCase(Constants.ALL))
						|| (chnlCd != null && !chnlCd
								.equalsIgnoreCase(Constants.ALL))
						|| null != tranDate || null != tranId || null != strNmb) {
					objPurchaseDetailsListVO = filterPurchaseDetailsList(catId,
							rwdNmbr, chnlCd, tranDate, strNmb, tranId,
							objPurchaseDetailsListVO);
				}
				int month = Integer.parseInt(mnth);

				mnth = Integer.toString(month);
				objPurchDtlsSelectedVO.setSelectedMonthYear(mnth + yr);

				customerDataVo
						.setObjPurchaseDetailsListVO(objPurchaseDetailsListVO);
				List<SubAccountDetailsVO> objSubAccountDetailsList = new ArrayList<SubAccountDetailsVO>();
				if (null == objPurchDtlsSelectedVO.getSelectedCategory()
						|| objPurchDtlsSelectedVO.getSelectedCategory()
								.equalsIgnoreCase(Constants.ALL)) {
				}

				if (null != objPurchaseDetailsListVO
						&& objPurchaseDetailsListVO.size() > 0) {

				}
				List<CategoryVO> objCategoriesList = null;
				objCategoriesList = populateCategories("BSE");
				customerDataVo.setObjCategoriesList(objCategoriesList);

				String channelName = null;
				if (null == chnlCd || chnlCd.equalsIgnoreCase(Constants.ALL)) {
					channelName = Constants.ALL + "_Locations";
				} else if (chnlCd.equalsIgnoreCase(Constants.ONLIN_IND)) {
					channelName = Constants.ONLINE;
				} else if (chnlCd.equalsIgnoreCase(Constants.PHONE_IND)) {
					channelName = Constants.PHONE;
				} else if (chnlCd.equalsIgnoreCase(Constants.RETAIL_IND)) {
					channelName = Constants.STORE;
				}

				String memberName = null;
				if (null == objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
						|| objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
								.equalsIgnoreCase(Constants.ALL)) {
					memberName = Constants.ALL + "_members";
				} else {
					memberName = getCustomerName(objSubAccountDetailsList,
							rwdNmbr);
				}

				String categoryName = null;
				if (null == catId || catId.equalsIgnoreCase(Constants.ALL)
						|| null == objCategoriesList) {
					categoryName = Constants.ALL + "_Purchases";
				} else {
					categoryName = getCategoryName(objCategoriesList, catId);
				}
			} else {
				List<CategoryVO> objCategoriesList = null;
				objCategoriesList = populateCategories("BSE");
				customerDataVo.setObjCategoriesList(objCategoriesList);
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	private long[] getOrderNumberList(
			List<PurchaseDetailsVO> objPurchaseDetailsListVO) {
		// TODO Auto-generated method stub
		long [] orderNumberList = new long[objPurchaseDetailsListVO.size()];
		int i = 0;
		for (PurchaseDetailsVO pdVO : objPurchaseDetailsListVO) {
			orderNumberList[i++] = Long.parseLong(pdVO.getOrderNumber());
		}
		return orderNumberList;
	}
	
	/**
	 * Method implementation to view purchase details.
	 * 
	 * @param PurchaseDetailsSelectedVO
	 *            objPurchDtlsSelectedVO
	 * @param Model
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @param Stringg
	 *            custNum
	 */
	@SuppressWarnings("deprecation")
	public void viewPurchaseDetails(
			PurchaseDetailsSelectedVO objPurchDtlsSelectedVO, Model model,
			HttpServletRequest request, String custNum)
			throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;
		if (null != objPurchDtlsSelectedVO) {
			if (null != objPurchDtlsSelectedVO.getSelectedCategory()) {
				catId = objPurchDtlsSelectedVO.getSelectedCategory();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				mnth = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								0,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4);
			}
			if (null != objPurchDtlsSelectedVO.getSelectedLocation()) {
				chnlCd = objPurchDtlsSelectedVO.getSelectedLocation();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				yr = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length());
			}
			if (null != objPurchDtlsSelectedVO.getSelectedSubAcctMbr()) {
				rwdNmbr = objPurchDtlsSelectedVO.getSelectedSubAcctMbr();
			}
		}

		if (null != request.getParameter(Constants.CATEGORY_ID)) {
			catId = request.getParameter(Constants.CATEGORY_ID);
			objPurchDtlsSelectedVO.setSelectedCategory(catId);
		}
		if (null != request.getParameter(Constants.MONTH)) {
			mnth = request.getParameter(Constants.MONTH);
		}
		if (null != request.getParameter(Constants.YEAR)) {
			yr = request.getParameter(Constants.YEAR);
		}

		String tranDate = null;
		String strNmb = null;
		String tranId = null;
		if (null != request.getParameter(Constants.TRAN_DATE)) {
			tranDate = request.getParameter(Constants.TRAN_DATE);
			String str[] = tranDate.split(Constants.SLASH);
			mnth = str[0];
			yr = str[2];
		}
		String tranIdOtherScreen = null;
		if (null != request.getParameter(Constants.STORE_NUMBER)) {
			strNmb = request.getParameter(Constants.STORE_NUMBER);
		}
		if (null != request.getParameter(Constants.CHANNEL_CD)) {
			chnlCd = request.getParameter(Constants.CHANNEL_CD);
		}
		if (null != request.getParameter(Constants.TRAN_ID)) {
			tranIdOtherScreen = request.getParameter(Constants.TRAN_ID);
		}
		try {

			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();

			model.addAttribute("monYearList", monYearList);

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				yr = Integer.toString(cal.getTime().getYear() + 1900);
			}

			List<PurchaseDetailsVO> objPurchaseDetailsListVO = contractDao
					.getPurchaseDetails(rwdNmbr, mnth, yr,catId);
			if (objPurchaseDetailsListVO.size() > 0) {
				long [] orderNumberList = getOrderNumberList(objPurchaseDetailsListVO);
				List<PurchaseHeliosDetailsVO> objPurchRwdsDetailsListVO = contractDao
						.getPurchaseRewardsDetails(rwdNmbr, orderNumberList);
				mapPurchaseHeaderWithLineIems(objPurchaseDetailsListVO,
						objPurchRwdsDetailsListVO);
				if ((catId != null && !catId.equalsIgnoreCase(Constants.ALL))
						|| (chnlCd != null && !chnlCd
								.equalsIgnoreCase(Constants.ALL))
						|| null != tranDate || null != tranId || null != strNmb) {
					objPurchaseDetailsListVO = filterPurchaseDetailsList(catId,
							rwdNmbr, chnlCd, tranDate, strNmb, tranId,
							objPurchaseDetailsListVO);
				}
				int month = Integer.parseInt(mnth);

				mnth = Integer.toString(month);
				objPurchDtlsSelectedVO.setSelectedMonthYear(mnth + yr);
				model.addAttribute("showData", tranDate + tranIdOtherScreen);
				// model.addAttribute("monYearList", monYearList);
				model.addAttribute("purchaseDetailsVO",
						objPurchaseDetailsListVO);
				List<SubAccountDetailsVO> objSubAccountDetailsList = new ArrayList<SubAccountDetailsVO>();

				if (null == objPurchDtlsSelectedVO.getSelectedCategory()
						|| objPurchDtlsSelectedVO.getSelectedCategory()
								.equalsIgnoreCase(Constants.ALL)) {
					model.addAttribute("categoryFilter", Constants.OFF);
				} else {
					model.addAttribute("categoryFilter", Constants.ON);
				}

				if (null != objPurchaseDetailsListVO
						&& objPurchaseDetailsListVO.size() > 0) {
					model.addAttribute("noSales", Constants.YES);
				} else {
					model.addAttribute("noSales", Constants.NO);
				}
				List<CategoryVO> objCategoriesList = null;
				objCategoriesList = populateCategories("BSE");
				model.addAttribute("categoryListVO", objCategoriesList);

				String channelName = null;
				if (null == chnlCd || chnlCd.equalsIgnoreCase(Constants.ALL)) {
					channelName = Constants.ALL + "_Locations";
				} else if (chnlCd.equalsIgnoreCase(Constants.ONLIN_IND)) {
					channelName = Constants.ONLINE;
				} else if (chnlCd.equalsIgnoreCase(Constants.PHONE_IND)) {
					channelName = Constants.PHONE;
				} else if (chnlCd.equalsIgnoreCase(Constants.RETAIL_IND)) {
					channelName = Constants.STORE;
				}

				String memberName = null;
				if (null == objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
						|| objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
								.equalsIgnoreCase(Constants.ALL)) {
					memberName = Constants.ALL + "_members";
				} else {
					memberName = getCustomerName(objSubAccountDetailsList,
							rwdNmbr);
				}

				String categoryName = null;
				if (null == catId || catId.equalsIgnoreCase(Constants.ALL)
						|| null == objCategoriesList) {
					categoryName = Constants.ALL + "_Purchases";
				} else {
					categoryName = getCategoryName(objCategoriesList, catId);
				}

				UserSession.setSessionAttr("memberName", memberName);
				UserSession.setSessionAttr("mnth", mnth);
				UserSession.setSessionAttr("yr", yr);
				UserSession.setSessionAttr("location", channelName);
				UserSession.setSessionAttr("category", categoryName);
			} else {
				model.addAttribute("purchMessage",
						"No purchsae history available for this customer");
				model.addAttribute("noSales", Constants.NO);
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	/**
	 * Method implementation to populate period.
	 * 
	 * @return LinkedHashMap<String, String>
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public LinkedHashMap<String, String> populatePeriod() throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (cal.getTime().getDate() < 3) {
			cal.add(Calendar.MONTH, -1);
		}
		Date baseStartDate = new SimpleDateFormat("mm/dd/yyyy")
				.parse(appSettings.get("BaseStartDate"));
		String maxRollingMonths = appSettings.get(Constants.ROLLINGNUMMONTH);
		int prevNoOfYrs = (Integer.parseInt(maxRollingMonths) / 12) - 1;
		Calendar startCal = Calendar.getInstance();
		startCal.add(Calendar.YEAR, -prevNoOfYrs);
		startCal.set(Calendar.MONTH, 0);
		startCal.set(Calendar.DAY_OF_MONTH, 1);
		if (startCal.getTime().before(baseStartDate)) {
			startCal.setTime(baseStartDate);
		}
		startCal.set(Calendar.WEEK_OF_YEAR, 1);
		startCal.set(Calendar.DAY_OF_WEEK, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String monthYearKey = null;
		String monthYearValue = null;
		LinkedHashMap<String, String> monYearList = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> destMonYearList = null;
		while (cal.getTime().after(startCal.getTime())) {
			monthYearValue = new DateFormatSymbols().getMonths()[cal.getTime()
					.getMonth()] + " " + (cal.getTime().getYear() + 1900);
			monthYearKey = Integer.toString(cal.get(Calendar.MONTH) + 1)
					+ (cal.getTime().getYear() + 1900);
			monYearList.put(monthYearKey, monthYearValue);
			if ((new DateFormatSymbols().getMonths()[cal.getTime().getMonth()])
					.equalsIgnoreCase("January")) {
				monYearList.put(
						0 + Integer.toString(cal.getTime().getYear() + 1900),
						"All " + (cal.getTime().getYear() + 1900));
			}
			cal.add(Calendar.MONTH, -1);
		}
		destMonYearList = new LinkedHashMap<String, String>(monYearList);
		return destMonYearList;
	}

	/**
	 * Method implementation to get category name.
	 * 
	 * @param List
	 *            <CategoryVO> objCategoriesList
	 * @param String
	 *            catId
	 * @return String
	 */
	public String getCategoryName(List<CategoryVO> objCategoriesList,
			String catId) {
		Iterator<CategoryVO> ite = objCategoriesList.iterator();
		CategoryVO objCategoryVO = null;
		String categoryName = null;
		while (ite.hasNext()) {
			objCategoryVO = ite.next();
			if (catId.equalsIgnoreCase(objCategoryVO.getMktItmCatCd() + "__"
					+ objCategoryVO.getDscPgmId())) {
				categoryName = objCategoryVO.getName();
				break;
			}
		}
		if (null != categoryName) {
			categoryName = categoryName.replaceAll(Constants.SINGLE_SPACE,
					Constants.UNDERSCORE);
		} else {
			categoryName = "";
		}
		return categoryName;
	}

	/**
	 * Method implementation to get customer name.
	 * 
	 * @param List
	 *            <SubAccountDetailsVO> objSubAccountsListVO
	 * @param String
	 *            rwdNmbr
	 * @return String
	 */
	public String getCustomerName(
			List<SubAccountDetailsVO> objSubAccountsListVO, String rwdNmbr) {
		String customerName = null;
		Iterator<SubAccountDetailsVO> ite = objSubAccountsListVO.iterator();
		SubAccountDetailsVO objSubAccountDetailsVO = null;
		while (ite.hasNext()) {
			objSubAccountDetailsVO = ite.next();
			if (objSubAccountDetailsVO.getStrChildRwdNumber().equalsIgnoreCase(
					rwdNmbr)) {
				customerName = objSubAccountDetailsVO.getStrSubAccountName();
				break;
			}

		}
		if (null != customerName) {
			return customerName.replaceAll(Constants.SINGLE_SPACE,
					Constants.UNDERSCORE) + "_" + rwdNmbr;
		} else {
			return customerName;
		}
	}

	/**
	 * Method implementation to populate categories.
	 * 
	 * @param String
	 *            tierCode
	 * @return List<CategoryVO>
	 */
	@Override
	public List<CategoryVO> populateCategories(String tierCode)
			throws DashboardException {
		try {
			StatementCheckParser objStmtCheckPrsr = new StatementCheckParser();
			StatementCheckVO objStmtCheckVO = objStmtCheckPrsr
					.parseStatementCheck(tierCode);
			List<CategoryVO> objCatListVO = null;
			List<CategoryVO> destObjCatListVO = null;
			if (null != objStmtCheckVO.getCategoriesVOList()) {

				if (null != objStmtCheckVO.getCategoriesVOList()
						&& objStmtCheckVO.getCategoriesVOList().size() > 0
						&& null != objStmtCheckVO.getCategoriesVOList().get(0)
								.getProductCategoriesVO()
						&& objStmtCheckVO.getCategoriesVOList().get(0)
								.getProductCategoriesVO().size() > 0) {
					objCatListVO = objStmtCheckVO.getCategoriesVOList().get(0)
							.getProductCategoriesVO().get(0).getCategoryList();
				} else {

					if (null != objStmtCheckVO.getCategoriesVOList()
							&& objStmtCheckVO.getCategoriesVOList().size() > 0
							&& null != objStmtCheckVO.getCategoriesVOList()
									.get(0).getRewardsCategoriesVO()
							&& objStmtCheckVO.getCategoriesVOList().get(0)
									.getRewardsCategoriesVO().size() > 0) {
						objCatListVO = objStmtCheckVO.getCategoriesVOList()
								.get(0).getRewardsCategoriesVO().get(0)
								.getCategoryList();
					}
				}
			}
			if (null != objCatListVO) {
				destObjCatListVO = new ArrayList<CategoryVO>(objCatListVO);
			}
			return destObjCatListVO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting YTD spend.
	 * 
	 * @param YTDSummarySelectedVO
	 *            objYTDSummarySelectedVO
	 * @param Model
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @param String
	 *            custNum
	 */
	@SuppressWarnings("deprecation")
	public void viewYTDSpend(YTDSummarySelectedVO objYTDSummarySelectedVO,
			Model model, HttpServletRequest request, String custNum)
			throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String strResponse = null;
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;

		List<KeyValue> listYears = new ArrayList<KeyValue>();

		KeyValue objKeyValue1 = new KeyValue();
		KeyValue objKeyValue2 = new KeyValue();
		KeyValue objKeyValue3 = new KeyValue();
		objKeyValue1.setKey(2015);
		listYears.add(objKeyValue1);

		objKeyValue2.setKey(2014);
		listYears.add(objKeyValue2);

		objKeyValue3.setKey(2013);
		listYears.add(objKeyValue3);

		if (null != objYTDSummarySelectedVO) {
			if (null != objYTDSummarySelectedVO.getSelectedYear()) {
				yr = objYTDSummarySelectedVO.getSelectedYear().substring(
						objYTDSummarySelectedVO.getSelectedYear().length() - 4,
						objYTDSummarySelectedVO.getSelectedYear().length());
			}
		}

		if (null != request.getParameter(Constants.YEAR)) {
			yr = request.getParameter(Constants.YEAR);
		}

		try {
			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				yr = "2014"; // Integer.toString(cal.getTime().getYear() +
								// 1900);
			}
			List<YTDSummaryVO> objYTDSummaryListVO = contractDao.getYTDSummary(
					rwdNmbr, yr);
			YTDSummaryVO objSummaryVO = new YTDSummaryVO();

			objSummaryVO.setListYear(listYears);

			objYTDSummarySelectedVO.setSelectedYear(yr);
			objSummaryVO.setSelectedYear(Integer.parseInt(yr));

			List<CategoryVO> objCategoriesList = null;
			objCategoriesList = populateCategories("BSE");
			model.addAttribute("categoryListVO", objCategoriesList);

			// Set the chart details
			objSummaryVO.setChartFileLocation(chartSetting
					.get(Constants.CHARTFILELOCATION));
			objSummaryVO.setCategoryChartFileName(chartSetting
					.get(Constants.CATEGORYCHARTFILENAME));
			objSummaryVO.setCategoryChartFileExtn(chartSetting
					.get(Constants.CATEGORYCHARTFILEEXTN));
			objSummaryVO.setMonthlyChartFileName(chartSetting
					.get(Constants.MONTHLYCHARTFILENAME));
			objSummaryVO.setMonthlyChartFileExtn(chartSetting
					.get(Constants.MONTHLYCHARTFILEEXTN));

			objSummaryVO.setSessionID(custNum);

			// Calculate YTD Spend
			calcualteYTDSpend(objSummaryVO, objYTDSummaryListVO);

			// Build YTD Spend Table
			buildYTDSpendTable(objSummaryVO);

			if ((objSummaryVO.getListFooterTotal().size() > Constants.ZERO && Double
					.valueOf(objSummaryVO.getListFooterTotal().get(
							objSummaryVO.getListFooterTotal().size()
									- Constants.ONE)) != Constants.ZERO)) {

				objSummaryVO.setChartDisplay(Constants.YES);

				// Build chart for category
				buildChartForCategory(objSummaryVO);

				// Build chart for month
				buildChartForMonth(objSummaryVO);
			}
			model.addAttribute("yTDSummaryVO", objSummaryVO);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	/**
	 * Method implementation to view YTD spend.
	 * 
	 * @param String
	 *            selYear
	 * @param String
	 *            custNum
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void viewYTDSpend(String selYear, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String strResponse = null;
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;
		String latestFiscalDate = null;
		List<KeyValue> listYears = new ArrayList<KeyValue>();
		KeyValue objKeyValue1 = new KeyValue();
		KeyValue objKeyValue2 = new KeyValue();
		KeyValue objKeyValue3 = new KeyValue();
		objKeyValue1.setKey(2015);
		listYears.add(objKeyValue1);

		objKeyValue2.setKey(2014);
		listYears.add(objKeyValue2);

		objKeyValue3.setKey(2013);
		listYears.add(objKeyValue3);

		if (null != selYear && !selYear.equalsIgnoreCase("null")) {
			yr = selYear.substring(selYear.length() - 4, selYear.length());

		}
		try {
			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				latestFiscalDate = contractDao.getLatestFiscalDate(custNum);
				if (latestFiscalDate != null) {
					yr = latestFiscalDate.substring(
							latestFiscalDate.length() - 4,
							latestFiscalDate.length());
				}
			}
			List<YTDSummaryVO> objYTDSummaryListVO = contractDao.getYTDSummary(
					rwdNmbr, yr);

			YTDSummaryVO objSummaryVO = new YTDSummaryVO();

			objSummaryVO.setListYear(listYears);

			objSummaryVO.setSelectedYear(Integer.parseInt(yr));

			List<CategoryVO> objCategoriesList = null;
			objCategoriesList = populateCategories("BSE");
			customerDataVo.setObjCategoriesList(objCategoriesList);
			// Set the chart details
			objSummaryVO.setChartFileLocation(chartSetting
					.get(Constants.CHARTFILELOCATION));
			objSummaryVO.setCategoryChartFileName(chartSetting
					.get(Constants.CATEGORYCHARTFILENAME));
			objSummaryVO.setCategoryChartFileExtn(chartSetting
					.get(Constants.CATEGORYCHARTFILEEXTN));
			objSummaryVO.setMonthlyChartFileName(chartSetting
					.get(Constants.MONTHLYCHARTFILENAME));
			objSummaryVO.setMonthlyChartFileExtn(chartSetting
					.get(Constants.MONTHLYCHARTFILEEXTN));

			objSummaryVO.setSessionID(custNum);

			// Calculate YTD Spend
			calcualteYTDSpend(objSummaryVO, objYTDSummaryListVO);

			// Build YTD Spend Table
			buildYTDSpendTable(objSummaryVO);

			if ((objSummaryVO.getListFooterTotal().size() > Constants.ZERO && Double
					.valueOf(objSummaryVO.getListFooterTotal().get(
							objSummaryVO.getListFooterTotal().size()
									- Constants.ONE)) != Constants.ZERO)) {

				objSummaryVO.setChartDisplay(Constants.YES);

				// Build chart for category
				buildDataForCategoryChart(objSummaryVO, customerDataVo);
				// Build chart for month
				buildDataForMonthChart(objSummaryVO, customerDataVo);
			}

			customerDataVo.setObjSummaryVO(objSummaryVO);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	/**
	 * Method implementation to view purchase header with line items.
	 * 
	 * @param List
	 *            <PurchaseDetailsVO> objPurchaseDetailsListVO
	 * @param List
	 *            <PurchaseRewardsDetailsVO> objPurchRwdsDetailsListVO
	 */
	public void mapPurchaseHeaderWithLineIems(
			List<PurchaseDetailsVO> objPurchaseDetailsListVO,
			List<PurchaseHeliosDetailsVO> objPurchRwdsDetailsListVO) {
		Iterator<PurchaseDetailsVO> purchDtlsIte = objPurchaseDetailsListVO
				.iterator();
		PurchaseDetailsVO objPurchDtlsVO = null;
		while (purchDtlsIte.hasNext()) {
			objPurchDtlsVO = purchDtlsIte.next();
			Iterator<PurchaseHeliosDetailsVO> purchRwdsDtlsIte = objPurchRwdsDetailsListVO
					.iterator();
			PurchaseHeliosDetailsVO objPurchRwdsDtlsVO = null;
			List<PurchaseHeliosDetailsVO> newPurchRwdsDtlsListVO = new ArrayList<PurchaseHeliosDetailsVO>();
			while (purchRwdsDtlsIte.hasNext()) {
				objPurchRwdsDtlsVO = purchRwdsDtlsIte.next();
				if (/* objPurchDtlsVO.getTranDate().equalsIgnoreCase(
						objPurchRwdsDtlsVO.getTranDate())
						&&*/ objPurchDtlsVO.getOrderNumber().equalsIgnoreCase(
								objPurchRwdsDtlsVO.getTranId())) {
					newPurchRwdsDtlsListVO.add(objPurchRwdsDtlsVO);
				}
			}
			objPurchDtlsVO.setPurchRwdsDtlListVO(newPurchRwdsDtlsListVO);
		}
	}

	/**
	 * Method implementation to filter purchase detail list.
	 * 
	 * @param String
	 *            catId
	 * @param String
	 *            rwdNmbr
	 * @param String
	 *            chnlCd
	 * @param String
	 *            tranDate
	 * @param String
	 *            strNmb
	 * @param String
	 *            tranId
	 * @param List
	 *            <PurchaseDetailsVO> objPurchaseDetailsListVO
	 * @return List<PurchaseDetailsVO>
	 */
	public List<PurchaseDetailsVO> filterPurchaseDetailsList(String catId,
			String rwdNmbr, String chnlCd, String tranDate, String strNmb,
			String tranId, List<PurchaseDetailsVO> objPurchaseDetailsListVO) {
		Iterator<PurchaseDetailsVO> ite = objPurchaseDetailsListVO.iterator();
		PurchaseDetailsVO objPurchDtlsVO = null;
		List<PurchaseDetailsVO> finalList = new ArrayList<PurchaseDetailsVO>();
		List<PurchaseDetailsVO> destFinalList = null;
		while (ite.hasNext()) {
			objPurchDtlsVO = ite.next();

			String mktCatId = catId;
			/*
			 * String mktCatId = catId.substring(0,
			 * catId.indexOf(Constants.UNDERSCORE)); String discPgmId =
			 * catId.substring( catId.lastIndexOf(Constants.UNDERSCORE) + 1,
			 * catId.length());
			 */
			final Set<String> mktCatIdSet = new HashSet<String>(
					Arrays.asList(mktCatId.split(Constants.COMMA)));
			/*
			 * Set<String> discPgmNotId = null; if
			 * (discPgmId.contains(Constants.NOT)) { discPgmNotId = new
			 * HashSet<String>( Arrays.asList(discPgmId.split(Constants.NOT)));
			 * }
			 */
			List<PurchaseHeliosDetailsVO> objPurchRwdsDtlsLstVO = objPurchDtlsVO
					.getPurchRwdsDtlListVO();
			Iterator<PurchaseHeliosDetailsVO> iteRwds = objPurchRwdsDtlsLstVO
					.iterator();
			PurchaseHeliosDetailsVO objPurchRwdsDtlsVO = null;
			final List<PurchaseHeliosDetailsVO> finalRwdsList = new ArrayList<PurchaseHeliosDetailsVO>();
			while (iteRwds.hasNext()) {
				objPurchRwdsDtlsVO = iteRwds.next();
				String strCatId = Float.toString(
						objPurchRwdsDtlsVO.getCategoryId()).substring(
						0,
						Float.toString(objPurchRwdsDtlsVO.getCategoryId())
								.indexOf(Constants.DOT));
				/*
				 * String strDiscPgmId = Float.toString(
				 * objPurchRwdsDtlsVO.getDiscProgId()).substring( 0,
				 * Float.toString( objPurchRwdsDtlsVO.getDiscProgId())
				 * .indexOf(Constants.DOT));
				 */

				/*
				 * if (mktCatIdSet.contains(strCatId) && ((discPgmId
				 * .equalsIgnoreCase(Constants.NOTAPPLICABLE)) || (discPgmNotId
				 * != null && !discPgmNotId .contains(strDiscPgmId)) ||
				 * (discPgmNotId == null && strDiscPgmId
				 * .equalsIgnoreCase(discPgmId))))
				 */
				if (mktCatIdSet.contains(strCatId)) {
					finalRwdsList.add(objPurchRwdsDtlsVO);
				}
			}
			if (finalRwdsList.size() > 0) {
				objPurchDtlsVO.setPurchRwdsDtlListVO(finalRwdsList);
				finalList.add(objPurchDtlsVO);
			}
		}

		// }
		// }
		destFinalList = new ArrayList<PurchaseDetailsVO>(finalList);
		return destFinalList;
	}

	/**
	 * Method implementation to calculate YTD spend.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param List
	 *            <YTDSummaryVO> objYTDSummaryListVO
	 */
	@Override
	@SuppressWarnings(Constants.DEPRECATION)
	public void calcualteYTDSpend(YTDSummaryVO objSummaryVO,
			List<YTDSummaryVO> objYTDSummaryListVO) throws DashboardException {

		// DatasetParserUtility objParser = null;
		Map<String, CategoryListVO> mapCategoryID = null;
		Map<String, Double> mapYTDSPend = null;

		try {

			// Get summary structure for given tier
			mapCategoryID = getYTDSpendStructure(objSummaryVO);
			mapYTDSPend = new LinkedHashMap<String, Double>();

			// Date updatedDate = new Date(objSummaryVO.getLastUpdatedDate());

			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date dateobj = new Date();
			Date updatedDate = df.parse(df.format(dateobj));

			int monthLimit = Constants.TWELVE;

			if (objSummaryVO.getSelectedYear() < (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.TWELVE;
			} else if (objSummaryVO.getSelectedYear() > (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.ZERO;
			} else if (objSummaryVO.getSelectedYear() == (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = (Constants.ONE + updatedDate.getMonth());
			}

			// Generate key and value
			for (String strMonthName : arrMonthName) {

				if (monthLimit > Constants.ZERO) {
					for (String key : mapCategoryID.keySet()) {

						mapYTDSPend.put(strMonthName + Constants.UNDERSCORE
								+ mapCategoryID.get(key).getValue(),
								Constants.DZERO);
					}

					monthLimit = monthLimit - Constants.ONE;
				}
			}

			YTDSummaryVO objYTDSumVO = null;
			Iterator<YTDSummaryVO> iteRwds = objYTDSummaryListVO.iterator();

			while (iteRwds.hasNext()) {
				objYTDSumVO = iteRwds.next();
				int intMonth = Integer.parseInt(objYTDSumVO.getTranMonth()); // Integer.parseInt(objParser.getNodeValue(
				// Constants.YTDMONTH, childNodes));
				String strCategoryID = objYTDSumVO.getCategoryId();
				Double dblSpend = Double.parseDouble(objYTDSumVO
						.getNetSpendAmount());

				String strMonth = arrMonthName[intMonth - Constants.ONE];
				String strCategoryName = Constants.EMPTY;

				if (null != mapCategoryID.get(strCategoryID)) {

					strCategoryName = mapCategoryID.get(strCategoryID)
							.getValue();
					if (mapYTDSPend.containsKey(strMonth + Constants.UNDERSCORE
							+ strCategoryName)) {

						mapYTDSPend.put(
								strMonth + Constants.UNDERSCORE
										+ strCategoryName,
								mapYTDSPend.get(strMonth + Constants.UNDERSCORE
										+ strCategoryName)
										+ dblSpend);
					}
				}
			}

			// Set YTD spend into Summary VO
			objSummaryVO.setMapYTDSpend(mapYTDSPend);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit calcualteYTDSpend", LOGGER);
	}

	/**
	 * Method implementation to build YTD spend table.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 */
	@Override
	@SuppressWarnings(Constants.DEPRECATION)
	public void buildYTDSpendTable(YTDSummaryVO objSummaryVO)
			throws DashboardException {

		try {

			// Get YTD Spend
			Map<String, Double> mapYTDSpend = objSummaryVO.getMapYTDSpend();

			List<YTDSummaryDataVO> listYTDSpend = new ArrayList<YTDSummaryDataVO>();
			Map<String, CategoryListVO> mapCategoryName = objSummaryVO
					.getMapCategory();
			YTDSummaryDataVO objData = null;

			int columnCount = Constants.ONE;
			int rowCount = Constants.ONE;
			Double dblRowTotal = Constants.DZERO;

			// Assign default value for footer value
			List<Double> listFooterTotal = new ArrayList<Double>();
			List<String> listFilterCondition = new ArrayList<String>();

			// Date updatedDate = new Date(objSummaryVO.getLastUpdatedDate());

			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date dateobj = new Date();
			Date updatedDate = df.parse(df.format(dateobj));

			int monthLimit = Constants.TWELVE;

			if (objSummaryVO.getSelectedYear() < (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.TWELVE;
			} else if (objSummaryVO.getSelectedYear() > (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.ZERO;
			} else if (objSummaryVO.getSelectedYear() == (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = (Constants.ONE + updatedDate.getMonth());
			}

			int totalColumns = mapYTDSpend.size() / monthLimit;
			for (int row = Constants.ZERO; row < totalColumns + Constants.ONE; row++) {
				listFooterTotal.add(Constants.DZERO);
				listFilterCondition.add(Constants.ALL);
			}

			// Set Header
			objSummaryVO.getListHeader().add(Constants.EMPTY);

			// Set YTD summary body
			for (String key : mapYTDSpend.keySet()) {

				// Header
				String monthName = key.substring(Constants.ZERO,
						key.indexOf(Constants.UNDERSCORE));
				String title = key.substring(key.indexOf(Constants.UNDERSCORE)
						+ Constants.ONE, key.length());
				Double dblValue = mapYTDSpend.get(key);

				// Add the title
				if (!objSummaryVO.getListHeader().contains(title)) {
					objSummaryVO.getListHeader().add(title);
				}

				// Add month name
				if (columnCount == Constants.ONE) {
					objData = new YTDSummaryDataVO();
					objData.getData().add(monthName);
				}

				// Set the value in footer list
				listFooterTotal.set(columnCount - Constants.ONE,
						listFooterTotal.get(columnCount - Constants.ONE)
								+ dblValue);
				listFilterCondition.set(columnCount - Constants.ONE,
						mapCategoryName.get(title).getFilterCondition());

				if (dblValue > Constants.DZERO) {
					objData.getData().add(
							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(dblValue));
				} else if (dblValue < Constants.ZERO) {
					objData.getData().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											dblValue).replace(Constants.HYPHEN,
											Constants.EMPTY));
				} else {
					objData.getData().add(Constants.DOLLAR + Constants.SZERO);
				}

				dblRowTotal = dblValue + dblRowTotal;

				// Set the value for last column
				if (columnCount == totalColumns) {

					if (dblRowTotal > Constants.ZERO) {
						objData.getData()
								.add(

								Constants.DOLLAR
										+ NumberFormatUtil
												.getFormatedDouble(dblRowTotal));
					} else if (dblValue < Constants.ZERO) {
						objData.getData().add(

								Constants.HYPHEN
										+ Constants.DOLLAR
										+ NumberFormatUtil.getFormatedDouble(
												dblRowTotal).replace(
												Constants.HYPHEN,
												Constants.EMPTY));
					} else {
						objData.getData().add(

						Constants.DOLLAR + Constants.SZERO);
					}

					listFooterTotal.set(columnCount,
							listFooterTotal.get(columnCount) + dblRowTotal);

					listYTDSpend.add(objData);
					columnCount = Constants.ONE;
					dblRowTotal = Constants.DZERO;
					rowCount++;
				} else {
					columnCount++;
				}
			}

			objSummaryVO.getListHeader().add(Constants.TOTAL);
			objSummaryVO.setListYTDSpend(listYTDSpend);

			// Format the footer value
			objSummaryVO.setListFooterTotal(listFooterTotal);
			// Set Footer value
			for (int row = Constants.ZERO; row < totalColumns + Constants.ONE; row++) {

				if (listFooterTotal.get(row) > Constants.ZERO) {
					objSummaryVO.getListFooter().add(

							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(listFooterTotal
													.get(row)));
				} else if (listFooterTotal.get(row) < Constants.ZERO) {
					objSummaryVO.getListFooter().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											listFooterTotal.get(row)).replace(
											Constants.HYPHEN, Constants.EMPTY));
				} else {
					objSummaryVO.getListFooter().add(
							Constants.DOLLAR + Constants.SZERO);
				}
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}

		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}

	/**
	 * Method implementation to build data for category chart.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	public void buildDataForCategoryChart(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException {
		YTDSummaryChart objCartUtility = null;
		List<String> listHeader = null;
		List<Double> listFooterTotal = null;
		Map<String, Double> mapFooterTotal = null;
		PieDataset pieDataset = null;

		try {

			objCartUtility = new YTDSummaryChart();
			listHeader = objSummaryVO.getListHeader();
			listFooterTotal = objSummaryVO.getListFooterTotal();
			mapFooterTotal = new LinkedHashMap<String, Double>();

			for (int rowCount = Constants.ONE; rowCount < listHeader.size()
					- Constants.ONE; rowCount++) {

				//if (listFooterTotal.get(rowCount - Constants.ONE) > Constants.ZERO) {

					mapFooterTotal.put(listHeader.get(rowCount),
							listFooterTotal.get(rowCount - Constants.ONE));
				//}

			}

			// Create data set
			pieDataset = objCartUtility.createDataSetForDouble(mapFooterTotal);
			customerDataVo.setPieDataset(pieDataset);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}

	/**
	 * Method implementation to build data for month chart.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	public void buildDataForMonthChart(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException {
		YTDSummaryChart objCartUtility = null;
		List<YTDSummaryDataVO> listYTDSpend = null;
		Map<String, Double> mapMonthChart = null;
		CategoryDataset barDataSet = null;

		try {
			objCartUtility = new YTDSummaryChart();
			listYTDSpend = objSummaryVO.getListYTDSpend();
			mapMonthChart = new LinkedHashMap<String, Double>();

			for (YTDSummaryDataVO objData : listYTDSpend) {

				int intLast = objData.getData().size() - Constants.ONE;
				String strValue = objData.getData().get(intLast);

				strValue = strValue.replace(Constants.DOLLAR, Constants.EMPTY)
						.replace(Constants.HASH, Constants.EMPTY)
						.replace(Constants.COMMA, Constants.EMPTY);
				mapMonthChart.put(objData.getData().get(Constants.ZERO)
						.substring(Constants.ZERO, /* Constants.THREE */
						Constants.FIVE), Double.valueOf(strValue));
			}

			// Create data set
			barDataSet = objCartUtility
					.createBarDataSetForDouble(mapMonthChart);

			customerDataVo.setBarDataSet(barDataSet);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}

	/**
	 * This method will build YTD spend structure.
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 */
	public Map<String, CategoryListVO> getYTDSpendStructure(
			YTDSummaryVO objSummaryVO) throws DashboardException {
		YTDSummaryParser objParser = null;
		YTDSummaryLayoutVO objLayout = null;
		List<CategoryListVO> listCategoryVO = null;
		Map<String, CategoryListVO> mapCategoryID = null;
		Map<String, CategoryListVO> mapCategoryName = null;

		try {
			objParser = new YTDSummaryParser();
			objLayout = objParser.parseYTDSummary("BSE");
			listCategoryVO = objLayout.getCategoryListVO();
			mapCategoryID = new LinkedHashMap<String, CategoryListVO>();

			mapCategoryName = new LinkedHashMap<String, CategoryListVO>();

			for (CategoryListVO objVo : listCategoryVO) {
				mapCategoryID.put(objVo.getMktItmCatCd(), objVo);
				mapCategoryName.put(objVo.getValue(), objVo);
			}

			// Set category map into summary VO
			objSummaryVO.setMapCategory(mapCategoryName);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return mapCategoryID;
	}

	/**
	 * This method will generate bar chart for YTD spend by Month
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 */
	public void buildChartForMonth(YTDSummaryVO objSummaryVO)
			throws DashboardException {
		YTDSummaryChart objCartUtility = null;
		List<YTDSummaryDataVO> listYTDSpend = null;
		Map<String, Double> mapMonthChart = null;
		CategoryDataset barDataSet = null;

		try {
			objCartUtility = new YTDSummaryChart();
			listYTDSpend = objSummaryVO.getListYTDSpend();
			mapMonthChart = new LinkedHashMap<String, Double>();

			for (YTDSummaryDataVO objData : listYTDSpend) {

				int intLast = objData.getData().size() - Constants.ONE;
				String strValue = objData.getData().get(intLast);

				strValue = strValue.replace(Constants.DOLLAR, Constants.EMPTY)
						.replace(Constants.HASH, Constants.EMPTY)
						.replace(Constants.COMMA, Constants.EMPTY);
				mapMonthChart.put(objData.getData().get(Constants.ZERO)
						.substring(Constants.ZERO, Constants.THREE),
						Double.valueOf(strValue));
			}

			// Create data set
			barDataSet = objCartUtility
					.createBarDataSetForDouble(mapMonthChart);

			// Call for bar chart
			objCartUtility.create2DBarGraph(barDataSet,
					Constants.MONTHCHARTTITTLE, objSummaryVO.getSessionID(),
					objSummaryVO.getChartFileLocation(),
					objSummaryVO.getMonthlyChartFileName(),
					objSummaryVO.getMonthlyChartFileExtn());

			// Set chart name into Summary VO
			objSummaryVO.setMonthChartName(objSummaryVO
					.getMonthlyChartFileName()
					+ objSummaryVO.getSessionID()
					+ objSummaryVO.getMonthlyChartFileExtn());

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * This method will generate pie chart for YTD spend by category
	 * 
	 * @param YTDSummaryVO
	 *            objSummaryVO
	 */
	public void buildChartForCategory(YTDSummaryVO objSummaryVO)
			throws DashboardException {
		YTDSummaryChart objCartUtility = null;
		List<String> listHeader = null;
		List<Double> listFooterTotal = null;
		Map<String, Double> mapFooterTotal = null;
		PieDataset pieDataset = null;

		try {

			objCartUtility = new YTDSummaryChart();
			listHeader = objSummaryVO.getListHeader();
			listFooterTotal = objSummaryVO.getListFooterTotal();
			mapFooterTotal = new LinkedHashMap<String, Double>();

			for (int rowCount = Constants.ONE; rowCount < listHeader.size()
					- Constants.ONE; rowCount++) {

				if (listFooterTotal.get(rowCount - Constants.ONE) > Constants.ZERO) {

					mapFooterTotal.put(listHeader.get(rowCount),
							listFooterTotal.get(rowCount - Constants.ONE));
				}

			}

			// Create data set
			pieDataset = objCartUtility.createDataSetForDouble(mapFooterTotal);

			// Call for pie chart
			objCartUtility.create2DPieChart(pieDataset,
					Constants.CATEGORYCHARTTITTLE, objSummaryVO.getSessionID(),
					objSummaryVO.getChartFileLocation(),
					objSummaryVO.getCategoryChartFileName(),
					objSummaryVO.getCategoryChartFileExtn());

			// Set chart name into Summary VO
			objSummaryVO.setCategoryChartName(objSummaryVO
					.getCategoryChartFileName()
					+ objSummaryVO.getSessionID()
					+ objSummaryVO.getCategoryChartFileExtn());

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}

	/**
	 * Method implementation to view ShipTo Details.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ShipToVO>
	 */
	@Override
	public List<ShipToVO> viewShipToDetails(String custNum,String location)
			throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		String rwdNmbr = custNum;
		List<ShipToVO> shipToList = new ArrayList<ShipToVO>();
		try {
			shipToList = contractDao.getShipToInfo(custNum,location);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
		return shipToList;
	}

	/**
	 * Method implementation for getting BAB info and recommended map vo.
	 * Recommendation Page Changes by Bipin
	 * 
	 * @param List
	 *            <BoughtAlsoBoughtInfoVO> recommendedVO
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	@Override
	public List<BoughtAlsoBoughtInfoVO> mapBABInfoAndRecommendedVo(
			List<BoughtAlsoBoughtInfoVO> boughtAlsoBoughtVo)
			throws DashboardException {

		List<BoughtAlsoBoughtInfoVO> returnList = new ArrayList<BoughtAlsoBoughtInfoVO>();
		try {
			if (boughtAlsoBoughtVo != null && !boughtAlsoBoughtVo.isEmpty()) {
				for (BoughtAlsoBoughtInfoVO babVO : boughtAlsoBoughtVo) {
					List<CustRecommendationVO> recomVo = null;
					if (!babVO.getRecSkuList().isEmpty()
							&& babVO.getRecSkuList().size() > 1)
						recomVo = contractDao.getBABRecommendationInfo(babVO
								.getRecSkuList().subList(0, 2));
					else if (!babVO.getRecSkuList().isEmpty())
						recomVo = contractDao.getBABRecommendationInfo(babVO
								.getRecSkuList());
					babVO.setCustRecommendationVOList(recomVo);
					returnList.add(babVO);

				}
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			LOGGER.info("Exiting method mapBABInfoAndRecommendedVo()");
		}
		return returnList;
	}

	/**
	 * Method implementation to view YTD spend for other years.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selYear
	 * @param CustomerDataVo
	 *            customerDataVo
	 */
	@Override
	public void viewYTDSpendForOtherYears(String custNum, String selYear,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug(Constants.ENTER, LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String strResponse = null;
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;
		String latestFiscalDate = null;
		List<KeyValue> listYears = new ArrayList<KeyValue>();

		KeyValue objKeyValue1 = new KeyValue();
		KeyValue objKeyValue2 = new KeyValue();
		KeyValue objKeyValue3 = new KeyValue();
		objKeyValue1.setKey(2015);
		listYears.add(objKeyValue1);

		objKeyValue2.setKey(2014);
		listYears.add(objKeyValue2);

		objKeyValue3.setKey(2013);
		listYears.add(objKeyValue3);

		if (null != selYear && !selYear.equalsIgnoreCase(Constants.NULL)) {
			yr = selYear.substring(selYear.length() - 4, selYear.length());

		}
		try {
			List<YTDSummaryVO> objYTDSummaryListVO = getYTDSummary(rwdNmbr, yr);
			YTDSummaryVO objSummaryVO = new YTDSummaryVO();

			objSummaryVO.setListYear(listYears);

			objSummaryVO.setSelectedYear(Integer.parseInt(yr));

			List<CategoryVO> objCategoriesList = null;
			objCategoriesList = populateCategories(Constants.BSE);
			customerDataVo.setObjCategoriesList(objCategoriesList);
			// Set the chart details
			objSummaryVO.setChartFileLocation(chartSetting
					.get(Constants.CHARTFILELOCATION));
			objSummaryVO.setCategoryChartFileName(chartSetting
					.get(Constants.CATEGORYCHARTFILENAME));
			objSummaryVO.setCategoryChartFileExtn(chartSetting
					.get(Constants.CATEGORYCHARTFILEEXTN));
			objSummaryVO.setMonthlyChartFileName(chartSetting
					.get(Constants.MONTHLYCHARTFILENAME));
			objSummaryVO.setMonthlyChartFileExtn(chartSetting
					.get(Constants.MONTHLYCHARTFILEEXTN));

			objSummaryVO.setSessionID(custNum);

			// Calculate YTD Spend
			calcualteYTDSpend(objSummaryVO, objYTDSummaryListVO);

			// Build YTD Spend Table
			buildYTDSpendTable(objSummaryVO);

			if ((objSummaryVO.getListFooterTotal().size() > Constants.ZERO && Double
					.valueOf(objSummaryVO.getListFooterTotal().get(
							objSummaryVO.getListFooterTotal().size()
									- Constants.ONE)) != Constants.ZERO)) {

				objSummaryVO.setChartDisplay(Constants.YES);

				// Build chart for category
				buildDataForCategoryChart(objSummaryVO, customerDataVo);
				// Build chart for month
				buildDataForMonthChart(objSummaryVO, customerDataVo);
			}

			customerDataVo.setObjSummaryVO(objSummaryVO);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			LOGGER.info("Exiting method mapBABInfoAndRecommendedVo()");
		}
	}

	/**
	 * Method implementation for getting sub play list.
	 * 
	 * @param String
	 *            forPlayType
	 * @param String
	 *            AccId
	 * @return List<SubplayInfoVo>
	 */
	@Override
	public List<SubplayInfoVo> getSubPlayList(String forPlayType, String AccId)
			throws DashboardException {
		// TODO Auto-generated method stub
		try {
			List<SubplayInfoVo> listOfSubPlay = contractDao.getSubPlayList(
					forPlayType, AccId);
			return listOfSubPlay;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation for getting agent rep role code.
	 * 
	 * @param String
	 *            custId
	 * @return String
	 */
	@Override
	public String getAgentRepRoleCd(String agentId) throws DashboardException {
		try {
			String repRoleCd = contractDao.getAgentRepRoleCd(agentId);
			return repRoleCd;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	/**
	 * Method implementation for getting agent name.
	 * 
	 * @param String
	 *            custId
	 * @return String
	 */
	@Override
	public String getAgentName(String agentId) throws DashboardException {
		try {
			String agentName = contractDao.getAgentName(agentId);
			return agentName;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.staples.dashboard.app.service.ContractDashboardService#getLastRefreshTime
	 * (java.lang.String)
	 */
	@Override
	public String getLastRefreshTime(String processName)
			throws DashboardException {
		// TODO Auto-generated method stub
		try {
			String lastUpdatedTime = contractDao
					.getLastRefreshTime(processName);
			//lastUpdatedTime = lastUpdatedTime + " A(P)M";
			return lastUpdatedTime;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}

	}
	
    /*
     * (non-Javadoc)
     * 
     * @see com.staples.dashboard.app.service.ContractDashboardService#
     * getCurrentRefreshTime (java.lang.String)
     */
    @Override
    public String getCurrentRefreshTime() throws DashboardException {
	// TODO Auto-generated method stub
	try {
	    String currentRefreshTime = contractDao.getCurrentRefreshTime();
	    return currentRefreshTime;
	} catch (DashboardException de) {
	    throw de;
	} catch (Exception e) {
	    throw new DashboardException(e);
	}
    }

	@Override
	public List<ShipToDetailsVo> viewShipToDetailsInfo(String shipTo, String custNum) {
		try {
			List<ShipToDetailsVo> shipToDetails = contractDao.getShipToDetails(
					shipTo, custNum);
			return shipToDetails;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	@Override
	public List<PurchaseHeliosDetailsVO> getOrderDetailsShipTo(String custNum,
			String orderNum, String orderDate) {
		return contractDao.getOrderDetailsShipTo(custNum, orderNum, orderDate);

	}
	@Override
	public String getMasterAccountZipCode(String customerNumber) throws DashboardException {
		return contractDao.getMasterAccountZipCode(customerNumber);
	}
	@Override
	public Map<String, List<Object>> getFilterData(){
		return contractDao.getFilterData();
	}
	@Override
	public SbaDiffDetailsVo getSbaDiffDetails(String custId) throws DashboardException{
		return contractDao.getSbaDiffDetails(custId);
	}

	
	@Override
	public CustomerListDTO getAllSamCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO tableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException {
		try {
			CustomerListDTO custDTO = contractDao.getAllSamCustomerInfo(accId,
					selPlaySection, selectedSubplayArr, tableInput, selectedQualScore,selectedSegScore);
			List<CustProfileVO> listOfCustProfileVO = custDTO
					.getCustomerVoList();
			
			custDTO.setCustomerVoList(listOfCustProfileVO);
			return custDTO;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	/**
	 * Method implementation for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	@Override
	public String getLatestFiscalDateForLead(String custNum) throws DashboardException {
		try {
			String latestFiscalDate = contractDao.getLatestFiscalDateForLead(custNum);
			return latestFiscalDate;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	@Override
	public SavingsVo getSavingsInfo(String custId) throws DashboardException{
		return contractDao.getSavingsInfo(custId);
	}
	
	@Override
	public List<Object> getCatPenInfo(String custId) throws DashboardException{
		return contractDao.getCatPenInfo(custId);
	}
	
	@Override
	@SuppressWarnings("deprecation")	
	public void viewYTDSpendSAM(String selYear, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String strResponse = null;
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;
		String latestFiscalDate = null;
		List<KeyValue> listYears = new ArrayList<KeyValue>();
		KeyValue objKeyValue1 = new KeyValue();
		KeyValue objKeyValue2 = new KeyValue();
		KeyValue objKeyValue3 = new KeyValue();
		objKeyValue1.setKey(2015);
		listYears.add(objKeyValue1);

		objKeyValue2.setKey(2014);
		listYears.add(objKeyValue2);

		objKeyValue3.setKey(2013);
		listYears.add(objKeyValue3);

		if (null != selYear && !selYear.equalsIgnoreCase("null")) {
			yr = selYear.substring(selYear.length() - 4, selYear.length());

		}
		try {
			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				latestFiscalDate = contractDao.getLatestFiscalDate(custNum);
				if (latestFiscalDate != null) {
					yr = latestFiscalDate.substring(
							latestFiscalDate.length() - 4,
							latestFiscalDate.length());
				}
			}
			List<YTDSummaryVO> objYTDSummaryListVO = contractDao.getYTDSummarySAM(
					rwdNmbr, yr);
			YTDSummaryVO objSummaryVO = new YTDSummaryVO();
			if(null !=yr) {
				objSummaryVO.setSelectedYear(Integer.parseInt(yr));
	         }
			objSummaryVO.setListYear(listYears);
            

			List<CategoryVO> objCategoriesList = null;
			objCategoriesList = populateCategories("BSE");
			customerDataVo.setObjCategoriesList(objCategoriesList);
			// Set the chart details
			objSummaryVO.setChartFileLocation(chartSetting
					.get(Constants.CHARTFILELOCATION));
			objSummaryVO.setCategoryChartFileName(chartSetting
					.get(Constants.CATEGORYCHARTFILENAME));
			objSummaryVO.setCategoryChartFileExtn(chartSetting
					.get(Constants.CATEGORYCHARTFILEEXTN));
			objSummaryVO.setMonthlyChartFileName(chartSetting
					.get(Constants.MONTHLYCHARTFILENAME));
			objSummaryVO.setMonthlyChartFileExtn(chartSetting
					.get(Constants.MONTHLYCHARTFILEEXTN));

			objSummaryVO.setSessionID(custNum);

			// Calculate YTD Spend
			calcualteYTDSpendSam(objSummaryVO, objYTDSummaryListVO);

			// Build YTD Spend Table
			buildYTDSpendTableSam(objSummaryVO);

			if ((objSummaryVO.getListFooterTotal().size() > Constants.ZERO && Double
					.valueOf(objSummaryVO.getListFooterTotal().get(
							objSummaryVO.getListFooterTotal().size()
									- Constants.ONE)) != Constants.ZERO)) {

				objSummaryVO.setChartDisplay(Constants.YES);

				// Build chart for category
				buildDataForCategoryChartSam(objSummaryVO, customerDataVo);
				// Build chart for month
				buildDataForMonthChartSam(objSummaryVO, customerDataVo);
			}

			customerDataVo.setObjSummaryVO(objSummaryVO);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}
	
	public void calcualteYTDSpendSam(YTDSummaryVO objSummaryVO,
			List<YTDSummaryVO> objYTDSummaryListVO) throws DashboardException {

		// DatasetParserUtility objParser = null;
		Map<String, CategoryListVO> mapCategoryID = null;
		Map<String, Double> mapYTDSPend = null;
		Map<String, Double> mapYTDSPendRetail = null;
		Map<String, Double> mapYTDSPendOnline = null;
		Map<String,String> monthindexMap = new HashMap<String,String>();
		
		try {

			// Get summary structure for given tier
			mapCategoryID = getYTDSpendStructure(objSummaryVO);
			mapYTDSPend = new LinkedHashMap<String, Double>();
			mapYTDSPendRetail = new LinkedHashMap<String, Double>();
			mapYTDSPendOnline = new LinkedHashMap<String, Double>();
			// Date updatedDate = new Date(objSummaryVO.getLastUpdatedDate());
			String monthYearArr[]=new String[Constants.TWELVE];
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy");  
		    Calendar calendar = new GregorianCalendar();
		    System.out.println("<><>"+sdf.format(calendar.getTime()));

		    for (int i = 0; i < 12; i++) {
		      calendar.add(Calendar.MONTH, -1);
		      System.out.println("--->"+sdf.format(calendar.getTime()));
		      String monthYear=sdf.format(calendar.getTime()).toUpperCase();
		      monthYearArr[i]=monthYear;
		    }
		    java.util.SortedSet<String> monthSet = new java.util.TreeSet<String>(new java.util.Comparator<String>() {
		        public int compare(String o1, String o2) {
		            try {
		                SimpleDateFormat fmt = new SimpleDateFormat("MMM");
		                return fmt.parse(o1).compareTo(fmt.parse(o2));
		            } catch (ParseException ex) {
		                return o1.compareTo(o2);
		            }
		        }
		    });
		    for (int i = 0; i < 12; i++) {
		    	monthSet.add(monthYearArr[i]);
		    }
		    int count=0;
		    Iterator itr=monthSet.iterator();
		    		while(itr.hasNext()){
		    			String monthyear=(String)itr.next();
		    			if(null !=monthyear  && !("".equals(monthyear) && monthyear.indexOf("-")!=-1)){
		    				String month=monthyear.split(Constants.HYPHEN)[0];
		    				String year=monthyear.split(Constants.HYPHEN)[1];
		    				String index="";
		    				for(int cnt=0;cnt<(arrMonthNameSam.length);cnt++){
		    				 if(arrMonthNameSam[cnt].equals(month)){
		    					 index=String.valueOf(cnt+1);
		    					 
		    							String format = String.format("%%0%dd", 2);
		    							index= String.format(format, Integer.parseInt(index));
		    					 break;
		    				 }
		    				}
		    				monthindexMap.put(index,month+Constants.HYPHEN+year);
		    			}
		    			//monthYearArr[count]=(String)itr.next();
		    			count++;
		    		}
		    System.out.print(monthindexMap);
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date dateobj = new Date();
			Date updatedDate = df.parse(df.format(dateobj));

			int monthLimit = Constants.TWELVE;

			/*if (objSummaryVO.getSelectedYear() < (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.TWELVE;
			} else if (objSummaryVO.getSelectedYear() > (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.ZERO;
			} else if (objSummaryVO.getSelectedYear() == (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = (Constants.ONE + updatedDate.getMonth());
			}*/

			
			// Generate key and value
			for (String strMonthName : monthYearArr) {

				if (monthLimit > Constants.ZERO) {
					for (String key : mapCategoryID.keySet()) {

						mapYTDSPend.put(strMonthName + Constants.UNDERSCORE
								+ mapCategoryID.get(key).getValue(),
								Constants.DZERO);
						mapYTDSPendRetail.put(strMonthName + Constants.UNDERSCORE
								+ mapCategoryID.get(key).getValue(),
								Constants.DZERO);
						mapYTDSPendOnline.put(strMonthName + Constants.UNDERSCORE
								+ mapCategoryID.get(key).getValue(),
								Constants.DZERO);
					}

					monthLimit = monthLimit - Constants.ONE;
				}
			}

			YTDSummaryVO objYTDSumVO = null;
			Iterator<YTDSummaryVO> iteRwds = objYTDSummaryListVO.iterator();

			while (iteRwds.hasNext()) {
				objYTDSumVO = iteRwds.next();
				
				String intMonth = objYTDSumVO.getTranMonth().split("-")[0];
				String StringMonth = objYTDSumVO.getTranMonth().split("-")[1]; // Integer.parseInt(objParser.getNodeValue(
				int intYear = Integer.parseInt(objYTDSumVO.getTranMonth().split("-")[2]);
				// Constants.YTDMONTH, childNodes));
				String strCategoryID = objYTDSumVO.getCategoryId();
				Double dblSpend = Double.parseDouble(objYTDSumVO
						.getNetSpendAmount().split("#")[0]);
				String retailOnline=objYTDSumVO
						.getNetSpendAmount().split("#")[1];
				String strMonth = monthindexMap.get(intMonth);//monthYearArr[intMonth - Constants.ONE];
				String strCategoryName = Constants.EMPTY;

				if (null != mapCategoryID.get(strCategoryID)) {

					strCategoryName = mapCategoryID.get(strCategoryID)
							.getValue();
					if (mapYTDSPend.containsKey(strMonth + Constants.UNDERSCORE
							+ strCategoryName)) {

						mapYTDSPend.put(
								strMonth + Constants.UNDERSCORE
										+ strCategoryName,
								mapYTDSPend.get(strMonth + Constants.UNDERSCORE
										+ strCategoryName)
										+ dblSpend);
						if(null != retailOnline && ("R".equals(retailOnline))){
							mapYTDSPendRetail.put(
									strMonth + Constants.UNDERSCORE
											+ strCategoryName,
									mapYTDSPendRetail.get(strMonth + Constants.UNDERSCORE
											+ strCategoryName)
											+ dblSpend);
						}else{
							mapYTDSPendOnline.put(
									strMonth + Constants.UNDERSCORE
											+ strCategoryName,
									mapYTDSPendOnline.get(strMonth + Constants.UNDERSCORE
											+ strCategoryName)
											+ dblSpend);
						}
					}
				}
			}

			// Set YTD spend into Summary VO
			
			objSummaryVO.setMapYTDSpend(mapYTDSPend);
			objSummaryVO.setMapYTDSpendRetail(mapYTDSPendRetail);
			objSummaryVO.setMapYTDSpendOnline(mapYTDSPendOnline);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit calcualteYTDSpend", LOGGER);
	}
	
	public void buildYTDSpendTableSam(YTDSummaryVO objSummaryVO)
			throws DashboardException {

		try {

			// Get YTD Spend
			Map<String, Double> mapYTDSpend = objSummaryVO.getMapYTDSpend();
			Map<String, Double> mapYTDSpendRetail = objSummaryVO.getMapYTDSpendRetail();
			Map<String, Double> mapYTDSpendOnline = objSummaryVO.getMapYTDSpendOnline();

			List<YTDSummaryDataVO> listYTDSpend = new ArrayList<YTDSummaryDataVO>();
			List<YTDSummaryDataVO> listYTDSpendRetail = new ArrayList<YTDSummaryDataVO>();
			List<YTDSummaryDataVO> listYTDSpendOnline = new ArrayList<YTDSummaryDataVO>();
			Map<String, CategoryListVO> mapCategoryName = objSummaryVO
					.getMapCategory();
			YTDSummaryDataVO objData = null;
			YTDSummaryDataVO objDataRetail = null;
			YTDSummaryDataVO objDataOnline = null;

			int columnCount = Constants.ONE;
			int rowCount = Constants.ONE;
			Double dblRowTotal = Constants.DZERO;
			Double dblRowTotalRetail = Constants.DZERO;
			Double dblRowTotalOnline = Constants.DZERO;

			// Assign default value for footer value
			List<Double> listFooterTotal = new ArrayList<Double>();
			List<Double> listFooterTotalRetail = new ArrayList<Double>();
			List<Double> listFooterTotalOnline = new ArrayList<Double>();
			
			List<String> listFilterCondition = new ArrayList<String>();

			// Date updatedDate = new Date(objSummaryVO.getLastUpdatedDate());

			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date dateobj = new Date();
			Date updatedDate = df.parse(df.format(dateobj));

			int monthLimit = Constants.TWELVE;

			/*if (objSummaryVO.getSelectedYear() < (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.TWELVE;
			} else if (objSummaryVO.getSelectedYear() > (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = Constants.ZERO;
			} else if (objSummaryVO.getSelectedYear() == (Constants.YEAR1900 + updatedDate
					.getYear())) {
				monthLimit = (Constants.ONE + updatedDate.getMonth());
			}*/

			int totalColumns = mapYTDSpend.size() / monthLimit;
			for (int row = Constants.ZERO; row < totalColumns + Constants.ONE; row++) {
				listFooterTotal.add(Constants.DZERO);
				listFooterTotalRetail.add(Constants.DZERO);
				listFooterTotalOnline.add(Constants.DZERO);
				listFilterCondition.add(Constants.ALL);
			}

			// Set Header
			objSummaryVO.getListHeader().add(Constants.EMPTY);

			// Set YTD summary body
			for (String key : mapYTDSpend.keySet()) {

				// Header
				String monthName = key.substring(Constants.ZERO,
						key.indexOf(Constants.UNDERSCORE));
				String title = key.substring(key.indexOf(Constants.UNDERSCORE)
						+ Constants.ONE, key.length());
				Double dblValue = mapYTDSpend.get(key);
				Double dblValueRetail = mapYTDSpendRetail.get(key);
				Double dblValueOnline = mapYTDSpendOnline.get(key);

				// Add the title
				if (!objSummaryVO.getListHeader().contains(title)) {
					objSummaryVO.getListHeader().add(title);
				}

				// Add month name
				if (columnCount == Constants.ONE) {
					objData = new YTDSummaryDataVO();
					objDataRetail = new YTDSummaryDataVO();
					objDataOnline = new YTDSummaryDataVO();
					objData.getData().add(monthName);
					objDataRetail.getData().add(monthName);
					objDataOnline.getData().add(monthName);
				}

				// Set the value in footer list
				listFooterTotal.set(columnCount - Constants.ONE,
						listFooterTotal.get(columnCount - Constants.ONE)
								+ dblValue);
				listFooterTotalRetail.set(columnCount - Constants.ONE,
						listFooterTotalRetail.get(columnCount - Constants.ONE)
								+ dblValueRetail);
				listFooterTotalOnline.set(columnCount - Constants.ONE,
						listFooterTotalOnline.get(columnCount - Constants.ONE)
								+ dblValueOnline);
				
				listFilterCondition.set(columnCount - Constants.ONE,
						mapCategoryName.get(title).getFilterCondition());

				if (dblValue > Constants.DZERO) {
					objData.getData().add(
							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(dblValue));
				
				} else if (dblValue < Constants.ZERO) {
					objData.getData().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											dblValue).replace(Constants.HYPHEN,
											Constants.EMPTY));
				
				} else {
					objData.getData().add(Constants.DOLLAR + Constants.SZERO);
				
				}

				
				
				if (dblValueRetail > Constants.DZERO) {
				
					objDataRetail.getData().add(
							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(dblValueRetail));
				
				} else if (dblValueRetail < Constants.ZERO) {
				
					objDataRetail.getData().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											dblValueRetail).replace(Constants.HYPHEN,
											Constants.EMPTY));
				
				} else {
					objDataRetail.getData().add(Constants.DOLLAR + Constants.SZERO);
				}
				
				if (dblValueOnline > Constants.DZERO) {
					
					objDataOnline.getData().add(
							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(dblValueOnline));
				} else if (dblValueOnline < Constants.ZERO) {
					
					objDataOnline.getData().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											dblValueOnline).replace(Constants.HYPHEN,
											Constants.EMPTY));
				} else {
					objDataOnline.getData().add(Constants.DOLLAR + Constants.SZERO);
				}
				dblRowTotal = dblValue + dblRowTotal;
				dblRowTotalRetail = dblValueRetail + dblRowTotalRetail;
				dblRowTotalOnline = dblValueOnline + dblRowTotalOnline;

				// Set the value for last column
				if (columnCount == totalColumns) {

					if (dblRowTotal > Constants.ZERO) {
						objData.getData()
								.add(

								Constants.DOLLAR
										+ NumberFormatUtil
												.getFormatedDouble(dblRowTotal));
						
					} else if (dblRowTotal < Constants.ZERO) {
						objData.getData().add(

								Constants.HYPHEN
										+ Constants.DOLLAR
										+ NumberFormatUtil.getFormatedDouble(
												dblRowTotal).replace(
												Constants.HYPHEN,
												Constants.EMPTY));
						
					} else {
						objData.getData().add(

						Constants.DOLLAR + Constants.SZERO);
						
					}
					
					if (dblRowTotalRetail > Constants.ZERO) {
					
						objDataRetail.getData()
						.add(

						Constants.DOLLAR
								+ NumberFormatUtil
										.getFormatedDouble(dblRowTotalRetail));
					
					} else if (dblRowTotalRetail < Constants.ZERO) {
					
						objDataRetail.getData().add(

								Constants.HYPHEN
										+ Constants.DOLLAR
										+ NumberFormatUtil.getFormatedDouble(
												dblRowTotalRetail).replace(
												Constants.HYPHEN,
												Constants.EMPTY));
					
					} else {
					
						objDataRetail.getData().add(

								Constants.DOLLAR + Constants.SZERO);
					
					}

					if (dblRowTotalOnline > Constants.ZERO) {
						
						objDataOnline.getData()
						.add(

						Constants.DOLLAR
								+ NumberFormatUtil
										.getFormatedDouble(dblRowTotalOnline));
					
					} else if (dblRowTotalOnline < Constants.ZERO) {
					
						objDataOnline.getData().add(

								Constants.HYPHEN
										+ Constants.DOLLAR
										+ NumberFormatUtil.getFormatedDouble(
												dblRowTotalOnline).replace(
												Constants.HYPHEN,
												Constants.EMPTY));
					
					} else {
					
						objDataOnline.getData().add(

								Constants.DOLLAR + Constants.SZERO);
					
					}
					
					listFooterTotal.set(columnCount,
							listFooterTotal.get(columnCount) + dblRowTotal);
					listFooterTotalRetail.set(columnCount,
							listFooterTotalRetail.get(columnCount) + dblRowTotalRetail);
					listFooterTotalOnline.set(columnCount,
							listFooterTotalRetail.get(columnCount) + dblRowTotalOnline);
					
					listYTDSpend.add(objData);
					listYTDSpendRetail.add(objDataRetail);
					listYTDSpendOnline.add(objDataOnline);
					
					columnCount = Constants.ONE;
					dblRowTotal = Constants.DZERO;
					dblRowTotalRetail = Constants.DZERO;
					dblRowTotalOnline = Constants.DZERO;
					rowCount++;
				} else {
					columnCount++;
				}
			}

			objSummaryVO.getListHeader().add(Constants.TOTAL);
			Collections.reverse(listYTDSpend);
			Collections.reverse(listYTDSpendRetail);
			Collections.reverse(listYTDSpendOnline);
			objSummaryVO.setListYTDSpend(listYTDSpend);
			objSummaryVO.setListYTDSpendRetail(listYTDSpendRetail);
			objSummaryVO.setListYTDSpendOnline(listYTDSpendOnline);
			// Format the footer value
			objSummaryVO.setListFooterTotal(listFooterTotal);
			objSummaryVO.setListFooterTotalRetail(listFooterTotalRetail);
			objSummaryVO.setListFooterTotalOnline(listFooterTotalOnline);
			// Set Footer value
			for (int row = Constants.ZERO; row < totalColumns + Constants.ONE; row++) {

				if (listFooterTotal.get(row) > Constants.ZERO) {
					objSummaryVO.getListFooter().add(

							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(listFooterTotal
													.get(row)));
				} else if (listFooterTotal.get(row) < Constants.ZERO) {
					objSummaryVO.getListFooter().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											listFooterTotal.get(row)).replace(
											Constants.HYPHEN, Constants.EMPTY));
				} else {
					objSummaryVO.getListFooter().add(
							Constants.DOLLAR + Constants.SZERO);
				}
				
				if (listFooterTotalRetail.get(row) > Constants.ZERO) {
					objSummaryVO.getListFooterRetail().add(

							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(listFooterTotalRetail
													.get(row)));
				} else if (listFooterTotalRetail.get(row) < Constants.ZERO) {
					objSummaryVO.getListFooterRetail().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											listFooterTotalRetail.get(row)).replace(
											Constants.HYPHEN, Constants.EMPTY));
				} else {
					objSummaryVO.getListFooterRetail().add(
							Constants.DOLLAR + Constants.SZERO);
				}
				
				if (listFooterTotalOnline.get(row) > Constants.ZERO) {
					objSummaryVO.getListFooterOnline().add(

							Constants.DOLLAR
									+ NumberFormatUtil
											.getFormatedDouble(listFooterTotalOnline
													.get(row)));
				} else if (listFooterTotalOnline.get(row) < Constants.ZERO) {
					objSummaryVO.getListFooterOnline().add(

							Constants.HYPHEN
									+ Constants.DOLLAR
									+ NumberFormatUtil.getFormatedDouble(
											listFooterTotalOnline.get(row)).replace(
											Constants.HYPHEN, Constants.EMPTY));
				} else {
					objSummaryVO.getListFooterOnline().add(
							Constants.DOLLAR + Constants.SZERO);
				}
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}

		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}
	
	public void buildDataForCategoryChartSam(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException {
		
		YTDSummaryChart objCartUtility = null;
		List<String> listHeader = null;
		List<Double> listFooterTotal = null;
		List<Double> listFooterTotalRetail = null;
		List<Double> listFooterTotalOnline = null;
		Map<String, Double> mapFooterTotal = null;
		Map<String, Double> mapFooterTotalRetail = null;
		Map<String, Double> mapFooterTotalOnline = null;
		PieDataset pieDataset = null;
		PieDataset pieDatasetRetail = null;
		PieDataset pieDatasetOnline = null;

		try {

			objCartUtility = new YTDSummaryChart();
			listHeader = objSummaryVO.getListHeader();
			listFooterTotal = objSummaryVO.getListFooterTotal();
			listFooterTotalRetail = objSummaryVO.getListFooterTotalRetail();
			listFooterTotalOnline = objSummaryVO.getListFooterTotalOnline();
			mapFooterTotal = new LinkedHashMap<String, Double>();
			mapFooterTotalRetail = new LinkedHashMap<String, Double>();
			mapFooterTotalOnline = new LinkedHashMap<String, Double>();

			for (int rowCount = Constants.ONE; rowCount < listHeader.size()
					- Constants.ONE; rowCount++) {

				//if (listFooterTotal.get(rowCount - Constants.ONE) > Constants.ZERO) {

					mapFooterTotal.put(listHeader.get(rowCount),
							listFooterTotal.get(rowCount - Constants.ONE));
					mapFooterTotalRetail.put(listHeader.get(rowCount),
							listFooterTotalRetail.get(rowCount - Constants.ONE));
					mapFooterTotalOnline.put(listHeader.get(rowCount),
							listFooterTotalOnline.get(rowCount - Constants.ONE));
				//}

			}

			// Create data set
			pieDataset = objCartUtility.createDataSetForDouble(mapFooterTotal);
			pieDatasetRetail = objCartUtility.createDataSetForDouble(mapFooterTotalRetail);
			pieDatasetOnline = objCartUtility.createDataSetForDouble(mapFooterTotalOnline);
			customerDataVo.setPieDataset(pieDataset);
			customerDataVo.setPieDatasetRetail(pieDatasetRetail);
			customerDataVo.setPieDatasetOnline(pieDatasetOnline);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}
	
	public void buildDataForMonthChartSam(YTDSummaryVO objSummaryVO,
			CustomerDataVo customerDataVo) throws DashboardException {
		YTDSummaryChart objCartUtility = null;
		List<YTDSummaryDataVO> listYTDSpend = null;
		List<YTDSummaryDataVO> listYTDSpendRetail = null;
		List<YTDSummaryDataVO> listYTDSpendOnline = null;
		List<String> barDataSetKeys = new ArrayList<String>();
		Map<String, Double> mapMonthChart = null;
		Map<String, Double> mapMonthChartRetail = null;
		Map<String, Double> mapMonthChartOnline = null;
		CategoryDataset barDataSet = null;
		CategoryDataset barDataSetRetail = null;
		CategoryDataset barDataSetOnline = null;

		try {
			objCartUtility = new YTDSummaryChart();
			listYTDSpend = objSummaryVO.getListYTDSpend();
			listYTDSpendRetail = objSummaryVO.getListYTDSpendRetail();
			listYTDSpendOnline = objSummaryVO.getListYTDSpendOnline();
			mapMonthChart = new LinkedHashMap<String, Double>();
			mapMonthChartRetail = new LinkedHashMap<String, Double>();
			mapMonthChartOnline = new LinkedHashMap<String, Double>();

			for (YTDSummaryDataVO objData : listYTDSpend) {

				int intLast = objData.getData().size() - Constants.ONE;
				String strValue = objData.getData().get(intLast);

				strValue = strValue.replace(Constants.DOLLAR, Constants.EMPTY)
						.replace(Constants.HASH, Constants.EMPTY)
						.replace(Constants.COMMA, Constants.EMPTY);
				mapMonthChart.put(objData.getData().get(Constants.ZERO)
						.substring(Constants.ZERO, /* Constants.THREE */
						Constants.SIX), Double.valueOf(strValue));
			}
			for (YTDSummaryDataVO objData : listYTDSpendRetail) {

				int intLast = objData.getData().size() - Constants.ONE;
				String strValue = objData.getData().get(intLast);

				strValue = strValue.replace(Constants.DOLLAR, Constants.EMPTY)
						.replace(Constants.HASH, Constants.EMPTY)
						.replace(Constants.COMMA, Constants.EMPTY);
			
				mapMonthChartRetail.put(objData.getData().get(Constants.ZERO)
						.substring(Constants.ZERO, /* Constants.THREE */
						Constants.SIX), Double.valueOf(strValue));
			}
			for (YTDSummaryDataVO objData : listYTDSpendOnline) {

				int intLast = objData.getData().size() - Constants.ONE;
				String strValue = objData.getData().get(intLast);

				strValue = strValue.replace(Constants.DOLLAR, Constants.EMPTY)
						.replace(Constants.HASH, Constants.EMPTY)
						.replace(Constants.COMMA, Constants.EMPTY);
			
				mapMonthChartOnline.put(objData.getData().get(Constants.ZERO)
						.substring(Constants.ZERO, /* Constants.THREE */
						Constants.SIX), Double.valueOf(strValue));
			}
			// Create data set
			barDataSet = objCartUtility
					.createBarDataSetForDouble(mapMonthChart);
			barDataSetRetail = objCartUtility
					.createBarDataSetForDouble(mapMonthChartRetail);
			barDataSetOnline = objCartUtility
					.createBarDataSetForDouble(mapMonthChartOnline);
			barDataSetKeys=barDataSet.getRowKeys();
            customerDataVo.setBarDataSetKeys(barDataSetKeys);
			customerDataVo.setBarDataSet(barDataSet);
			customerDataVo.setBarDataSetRetail(barDataSetRetail);
			customerDataVo.setBarDataSetOnline(barDataSetOnline);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
	}
	public void viewOrderDetailsSAM(
			PurchaseDetailsSelectedVO objPurchDtlsSelectedVO, String custNum,
			CustomerDataVo customerDataVo) throws DashboardException {
		LoggerUtil.logDebug("Enter", LOGGER);
		Long longStartTime = System.currentTimeMillis();
		String catId = null;
		String rwdNmbr = custNum;
		String mnth = null;
		String yr = null;
		String chnlCd = null;

		if (null != objPurchDtlsSelectedVO) {
			if (null != objPurchDtlsSelectedVO.getSelectedCategory()) {
				catId = objPurchDtlsSelectedVO.getSelectedCategory();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				mnth = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								0,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4);
			}
			if (null != objPurchDtlsSelectedVO.getSelectedLocation()) {
				chnlCd = objPurchDtlsSelectedVO.getSelectedLocation();
			}
			if (null != objPurchDtlsSelectedVO.getSelectedMonthYear()) {
				yr = objPurchDtlsSelectedVO.getSelectedMonthYear()
						.substring(
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length() - 4,
								objPurchDtlsSelectedVO.getSelectedMonthYear()
										.length());
			}
			if (null != objPurchDtlsSelectedVO.getSelectedSubAcctMbr()) {
				rwdNmbr = objPurchDtlsSelectedVO.getSelectedSubAcctMbr();
			}
		}
		String tranDate = null;
		String strNmb = null;
		String tranId = null;

		String tranIdOtherScreen = null;

		try {

			LinkedHashMap<String, String> monYearList = null;
			monYearList = populatePeriod();
			customerDataVo.setMonYearList(monYearList);

			if (null == mnth && null == yr) {
				Calendar cal = Calendar.getInstance();
				mnth = Integer.toString(cal.getTime().getMonth() + 1);
				yr = Integer.toString(cal.getTime().getYear() + 1900);
			}

			List<PurchaseDetailsVO> objPurchaseDetailsListVO = contractDao
					.getOrderHeaderSAM(rwdNmbr);
			if (objPurchaseDetailsListVO.size() > 0) {
				long [] orderNumberList = getOrderNumberList(objPurchaseDetailsListVO);
				List<PurchaseHeliosDetailsVO> objPurchRwdsDetailsListVO = contractDao
						.getOrderDetailsSAM(rwdNmbr, orderNumberList);
				mapPurchaseHeaderWithLineIems(objPurchaseDetailsListVO,
						objPurchRwdsDetailsListVO);
				if ((catId != null && !catId.equalsIgnoreCase(Constants.ALL))
						|| (chnlCd != null && !chnlCd
								.equalsIgnoreCase(Constants.ALL))
						|| null != tranDate || null != tranId || null != strNmb) {
					objPurchaseDetailsListVO = filterPurchaseDetailsList(catId,
							rwdNmbr, chnlCd, tranDate, strNmb, tranId,
							objPurchaseDetailsListVO);
				}
				int month = Integer.parseInt(mnth);

				mnth = Integer.toString(month);
				objPurchDtlsSelectedVO.setSelectedMonthYear(mnth + yr);

				customerDataVo
						.setObjPurchaseDetailsListVO(objPurchaseDetailsListVO);
				List<SubAccountDetailsVO> objSubAccountDetailsList = new ArrayList<SubAccountDetailsVO>();
				if (null == objPurchDtlsSelectedVO.getSelectedCategory()
						|| objPurchDtlsSelectedVO.getSelectedCategory()
								.equalsIgnoreCase(Constants.ALL)) {
				}

				if (null != objPurchaseDetailsListVO
						&& objPurchaseDetailsListVO.size() > 0) {

				}
				List<CategoryVO> objCategoriesList = null;
				objCategoriesList = populateCategories("BSE");
				customerDataVo.setObjCategoriesList(objCategoriesList);

				String channelName = null;
				if (null == chnlCd || chnlCd.equalsIgnoreCase(Constants.ALL)) {
					channelName = Constants.ALL + "_Locations";
				} else if (chnlCd.equalsIgnoreCase(Constants.ONLIN_IND)) {
					channelName = Constants.ONLINE;
				} else if (chnlCd.equalsIgnoreCase(Constants.PHONE_IND)) {
					channelName = Constants.PHONE;
				} else if (chnlCd.equalsIgnoreCase(Constants.RETAIL_IND)) {
					channelName = Constants.STORE;
				}

				String memberName = null;
				if (null == objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
						|| objPurchDtlsSelectedVO.getSelectedSubAcctMbr()
								.equalsIgnoreCase(Constants.ALL)) {
					memberName = Constants.ALL + "_members";
				} else {
					memberName = getCustomerName(objSubAccountDetailsList,
							rwdNmbr);
				}

				String categoryName = null;
				if (null == catId || catId.equalsIgnoreCase(Constants.ALL)
						|| null == objCategoriesList) {
					categoryName = Constants.ALL + "_Purchases";
				} else {
					categoryName = getCategoryName(objCategoriesList, catId);
				}
			} else {
				List<CategoryVO> objCategoriesList = null;
				objCategoriesList = populateCategories("BSE");
				customerDataVo.setObjCategoriesList(objCategoriesList);
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("Exit", LOGGER);
	}

	@Override
	public List<SavingsDetailDTO> generateSavingsReport(String custNum)
			throws DashboardException {
		// TODO Auto-generated method stub
		try {
			List<SavingsDetailDTO> savingsDetail = contractDao
					.generateSavingsReport(custNum);
			return savingsDetail;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	@Override
	public List<SavingsDetailDTO> generateSamSavingsReport(String custNum)
			throws DashboardException {
		// TODO Auto-generated method stub
		try {
			List<SavingsDetailDTO> savingsDetail = contractDao
					.generateSamSavingsReport(custNum);
			return savingsDetail;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	
	
	
	/**
	 * Get Arke CIS Service Response
	 */
	private ArkeCISVO getArkeCISResponse(OAuth2Details oauthDetails, String customerNumber, PriceLog priceLog) {
		String resourceURL = oauthDetails.getResourceServerUrl();
		String arkeCISURL = resourceURL + "?tenantId=sdc&fingerprint=&customerNo=" + customerNumber + "&emailHash=&rewardsId=&overrideRunaToken=false";
		
		ArkeCISVO returnResult = null;
		HttpGet httpGet = new HttpGet(arkeCISURL);
		priceLog.setArkeCISRequest(arkeCISURL);
		httpGet.addHeader(OAuthConstants.AUTHORIZATION,
				OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		int code = -1;
		try {

			//post.setEntity(new StringEntity(inputJson));
			httpGet.setHeader("Accept", "application/json");
			//post.setHeader("Content-type", "application/json");
			response = client.execute(httpGet);
			code = response.getStatusLine().getStatusCode();
			if (code == 401 || code == 403) {
				// Access token is invalid or expired.Regenerating the access
				// token
				String accessToken = refreshAccessToken(oauthDetails);
				if (OAuthUtility.isValid(accessToken)) {
					// update the access token
					contractDao.refreshAccessToken(accessToken, MapperConstants.NEPHOS_ARKE_CIS);
					LOGGER.info("New access token: " + accessToken);
					oauthDetails.setAccessToken(accessToken);
					httpGet.removeHeaders(OAuthConstants.AUTHORIZATION);
					httpGet.addHeader(OAuthConstants.AUTHORIZATION,
							OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));
					httpGet.releaseConnection();
					response = client.execute(httpGet);
					LOGGER.info("REsponse from Arke CIS service" + response);
					code = response.getStatusLine().getStatusCode();
					if (code >= 400) {

						LOGGER.error(
								"Could not complete the request , authentication failed  http Status Code : " + code);
						returnResult = new ArkeCISVO();
						returnResult.setResponseCode(code);
						returnResult.setResponseMessage("Service NA");
						priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
						return returnResult;

					}

				} else {
					LOGGER.error(
							"Could not complete the request , Not a Valid Access Token , htp status Code  : " + code);
					returnResult = new ArkeCISVO();
					returnResult.setResponseCode(code);
					returnResult.setResponseMessage("Service NA");

					priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
					return returnResult;
				}

			} else if (code > 500) {
				LOGGER.error(
						"Could not complete the request , the service may be down or wrong resource URL,  http Status Code : "
								+ code);
				returnResult = new ArkeCISVO();
				returnResult.setResponseCode(code);
				returnResult.setResponseMessage("Service NA");
				priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
				return returnResult;
			}

			if (response != null) {
				returnResult = formArkeCISResponseVO(response, priceLog);
			}

		} catch (UnsupportedEncodingException e1) {

			LOGGER.error("Could not complete the request http status code : " + code, e1);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Nephos Service Down");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

			return returnResult;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

			return returnResult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

			return returnResult;
		} catch (Exception e) {
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

			return returnResult;
		} finally {
			httpGet.releaseConnection();
		}
		return returnResult;
	}
	
	
	/**
	 * This mthod maps and adds the Staples.com Price for SKU fetched from Nephos Service to the list of recommended SKUS.
	 * 
	 * @param recommendedVO
	 * @param custNum
	 * @return List of OnlineRetailReorderRecommendationVO
	 */
	private List<OnlineRetailReorderRecommendationVO> mapNephosResponseRecom(
			List<OnlineRetailReorderRecommendationVO> recommendedVO,
			String custNum, String loggedInUser) {
		PriceLog priceLog = new PriceLog();
		priceLog.setCustomerNumber(custNum);
		priceLog.setLoggedInUser(loggedInUser);
		List<OnlineRetailReorderRecommendationVO> returnList = new ArrayList<OnlineRetailReorderRecommendationVO>();
		Map<String, String> priceMap = null;
		String zip = getMasterAccountZipCode(custNum);
		
		String inputJson = null;
		
		if (mmpivotPriceV4) {
			NephosConfig configArkeCIS = contractDao.getNephosArkeCISServiceConfig();
			OAuth2Details oauthDetailsArkeCIS = OAuthUtility.createOAuthDetails(configArkeCIS);
			ArkeCISVO responseVoArkeCIS = getArkeCISResponse(oauthDetailsArkeCIS, custNum, priceLog);

			// Not O.K
			if (responseVoArkeCIS.getResponseCode() != 200 || responseVoArkeCIS == null || responseVoArkeCIS.getCustAttrbsServiceResponseCode() == null || (!responseVoArkeCIS.getCustAttrbsServiceResponseCode().equalsIgnoreCase("200"))) {
				for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
					if (responseVoArkeCIS.getResponseCode() != 200) {
						recom.setSaComPrice(responseVoArkeCIS.getResponseMessage());
						recom.setNephosStatusCode(responseVoArkeCIS.getResponseCode().toString());
					} else {
						recom.setSaComPrice("N/A");
						recom.setNephosStatusCode(responseVoArkeCIS.getCustAttrbsServiceResponseCode());
					}
					returnList.add(recom);
				}
				if (enablePriceLog) {
					contractDao.insertPriceLog(priceLog);
				}
				return returnList;
			}
			inputJson = formInputJsonNephosFromArkeCIS(custNum, zip, recommendedVO, responseVoArkeCIS);
		} else {
			inputJson = formInputJsonNephos(custNum, zip, recommendedVO);
		}
		priceLog.setPriceRequest(inputJson);
		NephosConfig config = contractDao.getNephosServiceConfig();
		OAuth2Details oauthDetails = OAuthUtility.createOAuthDetails(config);
		NephosResponseVO  responseVo = getProtectedResource(oauthDetails,
				inputJson, priceLog);
		if (enablePriceLog) {
			contractDao.insertPriceLog(priceLog);
		}
		priceMap = getPriceResultMap(responseVo);
		if(responseVo.getResponseCode() ==200){
        
		if (recommendedVO != null && recommendedVO.size() > 0
				&& priceMap != null) {
			for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
				recom.setSaComPrice(priceMap.get(recom.getSkuNumber()));
				recom.setNephosStatusCode(responseVo.getResponseCode().toString());
				returnList.add(recom);
			}

		}else{
			for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
				recom.setNephosStatusCode(responseVo.getResponseCode().toString());
				returnList.add(recom);
			}
		}
		}else{
			for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
				recom.setSaComPrice(responseVo.getResponseMessage());
				recom.setNephosStatusCode(responseVo.getResponseCode().toString());
				returnList.add(recom);
			}
		}

		return returnList;

	}

	
	
	
	
	/**
	 * This Method fetches  Staples.com pricing of SKUS for Retail Customers from Nephos Service .
	 * It consumes the Json returned by the Nephos Service .
	 * 
	 * @param oauthDetails
	 * @param inputJson
	 * @return NephosResponseVO
	 */
	private NephosResponseVO getProtectedResource(OAuth2Details oauthDetails,
			String inputJson, PriceLog priceLog) {
		String resourceURL = oauthDetails.getResourceServerUrl();
		priceLog.setPriceRequest(resourceURL + "\n" + inputJson);
		NephosResponseVO returnResult = null;
		HttpPost post = new HttpPost(resourceURL);
		post.addHeader(OAuthConstants.AUTHORIZATION, OAuthUtility
				.getAuthorizationHeaderForAccessToken(oauthDetails
						.getAccessToken()));

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		int code = -1;
		try {

			post.setEntity(new StringEntity(inputJson));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			response = client.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 401 || code == 403) {
				// Access token is invalid or expired.Regenerating the access
				// token
				String accessToken = refreshAccessToken(oauthDetails);
				if (OAuthUtility.isValid(accessToken)) {
					// update the access token
					contractDao.refreshAccessToken(accessToken, mmpivotPriceV4 ? MapperConstants.NEPHOS_PRICE_V4 : MapperConstants.NEPHOS_PRICE_V2);
					// System.out.println("New access token: " + accessToken);
					oauthDetails.setAccessToken(accessToken);
					post.removeHeaders(OAuthConstants.AUTHORIZATION);
					post.addHeader(OAuthConstants.AUTHORIZATION, OAuthUtility
							.getAuthorizationHeaderForAccessToken(oauthDetails
									.getAccessToken()));
					post.releaseConnection();
					response = client.execute(post);
					code = response.getStatusLine().getStatusCode();
					if (code >= 400) {
						
						LOGGER.error("Could not complete the request , authentication failed  http Status Code : "
								+ code);
						returnResult= new NephosResponseVO();
						returnResult.setResponseCode(code);
						returnResult.setResponseMessage("Service Not Available");
						priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
						return returnResult;
						
					}

				} else {
					LOGGER.error("Could not complete the request , Not a Valid Access Token , htp status Code  : "
							+ code);
					returnResult= new NephosResponseVO();
					returnResult.setResponseCode(code);
					returnResult.setResponseMessage("Service Not Available");
					priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
					return returnResult;
				}

			}else if(code > 500){
				LOGGER.error("Could not complete the request , the service may be down or wrong resource URL,  http Status Code : "
						+ code);
				returnResult= new NephosResponseVO();
				returnResult.setResponseCode(code);
				returnResult.setResponseMessage("Service Not Available");
				priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
				return returnResult;
			}
			
			if(response!=null){
			returnResult = OAuthUtility.formNephosResponseVO(response, priceLog);
			}

		} catch (UnsupportedEncodingException e1) {
			
			LOGGER.error("Could not complete the request http status code : "
					+ code,e1);
			returnResult= new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Nephos Service Down");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : "
					+ code,e);
			returnResult= new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : "
					+ code,e);
			returnResult= new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (Exception e) {
			LOGGER.error("Could not complete the request http status code : "
					+ code,e);
			returnResult= new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} finally {
			post.releaseConnection();
		}
		return returnResult;
	}

	/**
	 * This method is used to fetch the access token from the Authorization URL.
	 * 
	 * @param oauthDetails
	 * @return Access Token of type String
	 */
	private String refreshAccessToken(OAuth2Details oauthDetails) {
		HttpPost post = new HttpPost(oauthDetails.getAuthenticationServerUrl());
		String clientId = oauthDetails.getClientId();
		String clientSecret = oauthDetails.getClientSecret();
		String scope = oauthDetails.getScope();

		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE,
				oauthDetails.getGrantType()));

		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID,
				clientId));

		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_SECRET,
				clientSecret));

		if (OAuthUtility.isValid(scope)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE,
					scope));
		}

		
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		String accessToken = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(parametersBody, String
					.valueOf(StandardCharsets.UTF_8)));

			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (code == OAuthConstants.HTTP_UNAUTHORIZED) {
				System.out
						.println("Authorization server expects Basic authentication");
				// Add Basic Authorization header
				post.addHeader(
						OAuthConstants.AUTHORIZATION,
						OAuthUtility.getBasicAuthorizationHeader(
								oauthDetails.getClientId(),
								oauthDetails.getClientSecret()));
				
				post.releaseConnection();
				response = client.execute(post);
				code = response.getStatusLine().getStatusCode();
				if (code == 401 || code == 403) {
					
					LOGGER.error("Could not retrieve access token for client: "
									+ oauthDetails.getClientId() +" The request to fetch Access token  ressponded http status code : "+code);
					
					return null;

				}

			}
			if(response!=null){
			Map<String, String> map = OAuthUtility.handleResponse(response);
			accessToken = map.get(OAuthConstants.ACCESS_TOKEN);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception generating Token" , e);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception generating Token" , e);
			
		}

		return accessToken;
	}


	
	public static ArkeCISVO formArkeCISResponseVO(HttpResponse response, PriceLog priceLog) {
		String result;

		ObjectMapper objMap = new ObjectMapper();
		ArkeCISVO obj = null;

		try {

			result = EntityUtils.toString(response.getEntity());
			priceLog.setArkeCISResponse(result);
			// result="{\"zipCode\": \"04103\", \"channelId\":
			// \"WEB\",\"userInfo\": {\"rewardsNumber\":
			// \"224895409\"},\"item\": [{\"itemId\": \"889862\",\"quantity\":
			// 1,\"unitOfMeasureComposite\": { \"unitOfMeasure\": \"Each\"
			// },\"priceInfo\": [{\"purchaseOptionType\":
			// \"ONE_TIME_PURCHASE\",\"priceType\": \"NOW\",\"unitBasePrice\":
			// 88.99,\"basePrice\": 88.99,\"finalPrice\":
			// 88.99,\"finalPriceAfterRebate\": 88.99,\"totalSavings\":
			// 0,\"totalSavingsPercentage\": 0,\"totalSavingsAfterRebate\":
			// 0,\"comparePrice\": 128.99,\"offerId\":
			// \"889862-5051-25-1\"}]}]}";
			obj = objMap.readValue(result, ArkeCISVO.class);
			obj.setResponseCode(200);
			obj.setResponseMessage("Success");
		}  catch (JsonParseException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonMappingException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;

		} catch (IOException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		}
		return obj;

	}

	
	
	
	private String formInputJsonNephosFromArkeCIS(String custNum, String zip,
			List<OnlineRetailReorderRecommendationVO> recommendedVO, ArkeCISVO arkeCISVO) {
		SkuPricingInputVO input = new SkuPricingInputVO();
		List<Item> items = new ArrayList<Item>();
		String result = null;
		Item item = null;
		try {
			for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
				item = new Item();
				item.setItemId(recom.getSkuNumber());
				item.setQuantity(1);
				items.add(item);
			}
			UserInfo userInfo = new UserInfo();
			//userInfo.setRewardsNumber(custNum);
			input.setChannelId("WEB");
			input.setItems(items);
			input.setLocale("en-US");
			input.setTenantId("StaplesDotCom");
			input.setUserInfo(userInfo);
			input.setZipCode(zip);
			
			if (arkeCISVO!= null && arkeCISVO.getCustAttrbs() != null && arkeCISVO.getCustAttrbs().getOrchid() != null) {
				
				/*if (arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("customerNo", arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo()));
				}*/
				if (arkeCISVO.getCustAttrbs().getOrchid().getRewardsTierCd() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("rewardsTierCd", arkeCISVO.getCustAttrbs().getOrchid().getRewardsTierCd()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getSuffixCodes() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("suffixCodes", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getSuffixCodes(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getZoneId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("zoneId", arkeCISVO.getCustAttrbs().getOrchid().getZoneId()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getEmail() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("email", arkeCISVO.getCustAttrbs().getOrchid().getEmail()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getEmailHash() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("emailHash", arkeCISVO.getCustAttrbs().getOrchid().getEmailHash()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getStaplesUser() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesUser", arkeCISVO.getCustAttrbs().getOrchid().getStaplesUser()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getStaplesId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesId", arkeCISVO.getCustAttrbs().getOrchid().getStaplesId()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getTierSegment() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("tierSegment", arkeCISVO.getCustAttrbs().getOrchid().getTierSegment()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken() != null && !arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken().isEmpty()) {					
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("runaUserToken", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getFingerprint() != null && !arkeCISVO.getCustAttrbs().getOrchid().getFingerprint().isEmpty()) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("fingerprint", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getFingerprint(), ",")));
				}
			}

			
			if (arkeCISVO!= null && arkeCISVO.getCustAttrbs() != null && arkeCISVO.getCustAttrbs().getOrchid() == null && arkeCISVO.getCustAttrbs().getUbas() != null) {
				
				/*if (arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("customerNo", arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo()));
				}*/
				if (arkeCISVO.getCustAttrbs().getUbas().getRewardsTierCd() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("rewardsTierCd", arkeCISVO.getCustAttrbs().getUbas().getRewardsTierCd()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getSuffixCodes() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("suffixCodes", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getSuffixCodes(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getZoneId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("zoneId", arkeCISVO.getCustAttrbs().getUbas().getZoneId()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getEmail() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("email", arkeCISVO.getCustAttrbs().getUbas().getEmail()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getEmailHash() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("emailHash", arkeCISVO.getCustAttrbs().getUbas().getEmailHash()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getStaplesUser() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesUser", arkeCISVO.getCustAttrbs().getUbas().getStaplesUser()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getStaplesId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesId", arkeCISVO.getCustAttrbs().getUbas().getStaplesId()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getTierSegment() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("tierSegment", arkeCISVO.getCustAttrbs().getUbas().getTierSegment()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken() != null && !arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken().isEmpty()) {					
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("runaUserToken", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getFingerprint() != null && !arkeCISVO.getCustAttrbs().getUbas().getFingerprint().isEmpty()) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("fingerprint", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getFingerprint(), ",")));
				}
			}


			ObjectMapper objMap = new ObjectMapper();

			result = objMap.writeValueAsString(input);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * This method generates the required Json string which has to send with the request to Nephos Service.
	 * 
	 * @param custNum
	 * @param zip
	 * @param recommendedVO
	 * @return Json String
	 */
	private String formInputJsonNephos(String custNum, String zip,
			List<OnlineRetailReorderRecommendationVO> recommendedVO) {
		SkuPricingInputVO input = new SkuPricingInputVO();
		List<Item> items = new ArrayList<Item>();
		String result = null;
		Item item = null;
		try {
			for (OnlineRetailReorderRecommendationVO recom : recommendedVO) {
				item = new Item();
				item.setItemId(recom.getSkuNumber());
				item.setQuantity(1);
				items.add(item);
			}
			UserInfo userInfo = new UserInfo();
			userInfo.setRewardsNumber(custNum);
			input.setChannelId("WEB");
			input.setItems(items);
			input.setLocale("en-US");
			input.setTenantId("StaplesDotCom");
			input.setUserInfo(userInfo);
			input.setZipCode(zip);
			ObjectMapper objMap = new ObjectMapper();

			result = objMap.writeValueAsString(input);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private Map<String, String> getPriceResultMap(NephosResponseVO resp) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (resp != null) {
			for (Item item : resp.getItem()) {

				resultMap.put(item.getItemId(), item.getPriceInfo().get(0)
						.getFinalPrice().toString());
			}

		}

		return resultMap;
	}
	
	
	/* (non-Javadoc)
	 * @see com.staples.dashboard.app.service.ContractDashboardService#saveTaskToSfdc(com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO)
	 */
	@Override
	public OutputTasksfdc saveTaskToSfdc(InputTaskSalesforceDTO inputSfdc) throws DashboardException {
		// TODO Auto-generated method stub
		ForceApi api = null;
		ConfigSfdcVO config = null;
		String taskId = null;
		Task task = null;
		// Map<String, String> resultMap = new HashMap<String, String>();
		OutputTasksfdc taskOut = new OutputTasksfdc();

		try {
			config = contractDao.getSfdcConfig();
			if (config != null) {

				api = new ForceApi(new ApiConfig().setForceURL(config.getForceUrl()).setUsername(config.getUserName())
						.setPassword(config.getPassword()).setClientId(config.getClientId())
						.setClientSecret(config.getClientSecret()).setApiVersion(ApiVersion.DEFAULT_VERSION));
			}
			task = populateTaskObject(inputSfdc);
			if (task != null && null != api) {
				taskId = api.createSObject("Task", task);
				taskOut.setRepSFDCId(task.getOwnerId());
			}
			if (taskId != null && taskId.length() > 0) {

				taskOut.setStatus("SUCCESS");
				taskOut.setTaskId(taskId);
			} else {
				taskOut.setStatus("FAILURE");
			}

		} catch (DashboardException de) {
			LOGGER.error("Erro Saving Task To Sfdc", de);
			taskOut.setStatus("FAILURE");
		} catch (Exception e) {
			LOGGER.error("Erro Saving Task To Sfdc", e);
			taskOut.setStatus("FAILURE");
		}

		return taskOut;
	}

	/**
	 * @param inputSfdc
	 * @return
	 */
	private Task populateTaskObject(InputTaskSalesforceDTO inputSfdc) {
		SfdcInputId inputIds = null;
		Task task = null;
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (inputSfdc != null && inputSfdc.getStatus() != null
					&& !inputSfdc.getStatus().equals("")
					&& inputSfdc.getSubject() != null
					&& inputSfdc.getSubject().length() > 0
					&& inputSfdc.getSubject().length() <= 255) {

				inputIds = contractDao.getSfdcId(inputSfdc);

				if (inputIds != null && inputIds.getAccountMgrId() != null
						&& !inputIds.getAccountMgrId().equals("")) {

					task = new Task();
					task.setOwnerId(inputIds.getAccountMgrId());
					task.setSubject(inputSfdc.getSubject());
					task.setPriority("Normal");
					task.setCall_Mode__c("Outbound Call");
					task.setTopic__c("Account Status");
					task.setActivityDate(dtFormat.format(date));
					task.setStatus(inputSfdc.getStatus());
					task.setDescription(inputSfdc.getDescription());
					if (inputSfdc.getContactId() != null
							&& !inputSfdc.getContactId().equals("")) {
						task.setWhoId(inputIds.getContactId());
						
					}
					task.setWhatId(inputIds.getCustId());

				}

			}
		} catch (DashboardException de) {
			throw new DashboardException(de);
		} catch (Exception e) {

			throw new DashboardException(e);
		}

		return task;
	}


	@Override
	public ContactDateVO getLatestFiscalContactedDate()
			throws DashboardException {
		ContactDateVO ContactDatevo=null;
		try {
			ContactDatevo = contractDao
					.getLatestFiscalContactedDate();
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return ContactDatevo;
	}


public List<CustProfileVO> configuredWithContactedCustomers(List<CustProfileVO> listOfCustProfileVO)
		throws DashboardException {
	List<ContactDateVO> ContactDateVolist=null;
	List<CustProfileVO> updatedListOfCustProfileVO=null;
	List<String> custIdToDelete=new ArrayList<String>();
       try{
    	   ContactDateVolist = contractDao
					.getContactedCustList();
    	   if(null!= ContactDateVolist && ContactDateVolist.size()>0){
    		   updatedListOfCustProfileVO=new ArrayList<CustProfileVO>();
    		   for(CustProfileVO custProfileVo :listOfCustProfileVO){
    			   
    		    for(int init_cnt=0;init_cnt < ContactDateVolist.size() ;init_cnt++){
    			   if(null != ContactDateVolist.get(init_cnt).getCUSTOMER_NUMBER() && (custProfileVo.getCustNum()).equals(ContactDateVolist.get(init_cnt).getCUSTOMER_NUMBER())){
    				   
    				   if(null !=ContactDateVolist.get(init_cnt).getCLD_DT()){
    				     custProfileVo.setCalDt(ContactDateVolist.get(init_cnt).getCLD_DT());
    		        	 custProfileVo.setContactStatus(true);
    		        	 custProfileVo.setLoggedInUserName(ContactDateVolist.get(init_cnt).getSLS_REP_NAME()!=MapperConstants.BLANK_STRING ? 
    		        			 ContactDateVolist.get(init_cnt).getSLS_REP_NAME() : ContactDateVolist.get(init_cnt).getLOGGED_IN_USR_NAME());
    		        	 custProfileVo.setCheckedInDate(ContactDateVolist.get(init_cnt).getCHECKED_IN_DATE());

    				   }
    				   else
        				 custProfileVo.setCalDt(MapperConstants.BLANK_STRING);
    			   }
    		   }
    		    
    		    updatedListOfCustProfileVO.add(custProfileVo);
    	   }
    		   /*if(custIdToDelete.size()>0)
   		    	deleteCustNumFromTaskTracker(custIdToDelete);*/
    	   }
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		LoggerUtil.logDebug("LOG_METHOD_EXIT", LOGGER);
		return listOfCustProfileVO;
	}
public int deleteCustNumFromTaskTracker(List<String> listCustId){
	int totalRowDeleted=0;
		try {
			String commaSepString=StringUtils.join(listCustId, ",");
			totalRowDeleted=contractDao
					.deleteCustNumFromTaskTracker(commaSepString);			
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return totalRowDeleted;
}

@Override
public SegHdrText getSubCtaDeatils(String seg, String segId, String custNum)
		throws DashboardException {
	SegHdrText segHdrText=null;
	try {
		segHdrText = contractDao
				.getSubCtaDeatils(seg, segId,custNum);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
	return segHdrText;
}

@Override
public SegHdrText getAdminSubCtaDeatils(String seg, String segId)
		throws DashboardException {
	SegHdrText segHdrText=null;
	try {
		segHdrText = contractDao
				.getAdminSubCtaDeatils(seg, segId);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
	return segHdrText;
}
@Override
public String updateSubCtaDeatils(String segId, String subSegId, String headerIds,
		String headerContent, String headerCol) throws DashboardException {
	String updateStatus=null;
	try {
		updateStatus = contractDao.updateSubCtaDeatils(segId,  subSegId, headerIds, headerContent, headerCol);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
	return updateStatus;
}

@Override

public List<DispositionDetailsVo> getDespositionDetails(String taskCombinationId,String frequency) {
	List<DispositionDetailsVo> dispositionDetailsVoList=null;
	try {
		dispositionDetailsVoList= contractDao.getDespositionDetails(taskCombinationId,frequency);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
	return dispositionDetailsVoList;
}

public String refreshPlaySegmentParam(String custNum, String segId)
		throws DashboardException {
	// TODO Auto-generated method stub
	return contractDao.refreshPlaySegmentParam(custNum,segId);
}


public List<SegmentSubDetail> getSegSubDetail(String custNum, String segId, String subSegId) 
		throws DashboardException {
	return contractDao.getSegSubDetail(custNum, segId, subSegId);
}

@Override
public List<SegMktgResources> getSegMktgResources(String segId, String subSegId, String mktResourceUrl) {
	return contractDao.getSegMktgResources(segId, subSegId,mktResourceUrl);
}

@PostConstruct
public void  initProtocol(){
	
	try{
		if(System.getProperty("https.protocols") ==null){
		System.setProperty("https.protocols","TLSv1,TLSv1.1,TLSv1.2");
		LOGGER.info("TLS enabled till v 1.2 ");
		}
		
	}catch(Exception e){
	
		LOGGER.error("TLS Protocol not intialized , will be using Defualt TLS protocol as per Java 7"+e);
	}
	
}

@Override
public CustomerListDTO getAllCdmCustomerInfo(JQueryDataTableInputDTO dataTableInput,String selTimeZone, String accId, String rowId , String alertStateStatus) {
	// TODO Auto-generated method stub
	 return contractDao.getAllCdmCustomerInfo(dataTableInput,selTimeZone,accId, rowId,alertStateStatus);
}


@Override
public  Task getLastLiveContactDetailsFromSfdc(String custId) throws DashboardException {
	
	Task task=new Task ();
	// TODO uto-generated method stub
	ForceApi api = null;
	ConfigSfdcVO config = null;
	
	try {
       config = contractDao.getSfdcConfig();
		if (config != null) {
			api = new ForceApi(new ApiConfig()
					.setForceURL(config.getForceUrl())
					.setUsername(config.getUserName())
				     .setPassword(config.getPassword())
					.setClientId(config.getClientId())
					.setClientSecret(config.getClientSecret())
					.setApiVersion(ApiVersion.DEFAULT_VERSION));
		}
		
		if(null!=api)
		{
        QueryResult<Task> res = api.queryAll("SELECT AccountId,ActivityDate,OwnerId,Status ,Subject ,WhoId,WhatId,Description  FROM task where  WhatId= " + '\''+custId +'\''   +"  and Status = 'Task Completed - Live Contact'  order by ActivityDate DESC LIMIT 1", Task.class);
		if (res != null  &&  res.getRecords().size() > 0) {
		        task = res.getRecords().get(0);
				String orderContactName = contractDao.getOrderContactName(task.getWhoId());
				task.setWhoId(orderContactName);
			

		}
	}
	} catch (DashboardException de) {
		LOGGER.error("Error fetching Last Live Contact Details From Sfdc", de);
		
	} catch (Exception e) {
		LOGGER.error("Error fetching Last Live Contact Details From Sfdc", e);
		
	}

	return task;
}

@Override
@SuppressWarnings("deprecation")
public List<OrderContactInfoVO> getCustomerOrderContactDetails(String custNum, String iamId)
		throws DashboardException {
	LoggerUtil.logDebug("Enter", LOGGER);
	List<OrderContactInfoVO> orderContactInfoVList = null;

	try {
		orderContactInfoVList = contractDao.getCustomerOrderContactDetails(custNum);

		List<Contact> contactList = new ArrayList<Contact>();
		ForceApi api = null;
		ConfigSfdcVO config = null;
		if (null != iamId && !iamId.isEmpty()) {
			try {
				config = contractDao.getSfdcConfig();
				if (config != null) {
					api = new ForceApi(new ApiConfig().setForceURL(config.getForceUrl())
							.setUsername(config.getUserName()).setPassword(config.getPassword())
							.setClientId(config.getClientId()).setClientSecret(config.getClientSecret())
							.setApiVersion(ApiVersion.DEFAULT_VERSION));
				}
				if (null != api && orderContactInfoVList.size() > 0) {
					for (OrderContactInfoVO vo : orderContactInfoVList) {
						QueryResult<Contact> res = api.query(
								"select Title,Primary_Contact__c from Contact where AccountId= " + '\'' + iamId
										+ '\'' + " and Id= " + '\'' + vo.getContactId() + '\'',
								Contact.class);

						if (res != null && res.getRecords().size() > 0) {

							contactList = res.getRecords();
                                                            vo.setTitle(contactList.get(0).getTitle() == null ? MapperConstants.BLANK_STRING : contactList.get(0).getTitle());
							vo.setPrimary(contactList.get(0).isPrimaryContact());

						}

					}

				}

			} catch (DashboardException de) {
				LoggerUtil.logError("Error fetching customer Order Contacts Details From Sfdc", de, LOGGER);

			} catch (Exception e) {
				LoggerUtil.logError("Error fetching  customer Order Contacts Details From Sfdc", e, LOGGER);

			}

		}

	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
	LoggerUtil.logDebug("Exit", LOGGER);
	return orderContactInfoVList;
}	
	

	@Override
	public AccountProgressInfo getTodaysAndWeekProgress(String repNum) throws DashboardException {

		return contractDao.getTodaysAndWeekProgress(repNum);
	}
	
	public String checkAccountHoldoutStatus(String custNum, String segId)
			throws DashboardException {
		// TODO Auto-generated method stub
		return contractDao.checkAccountHoldoutStatus(custNum,segId);
	}
	
	@Override
	public   List<NotificationInfoVo>  getSegmentDetails(String segId) throws DashboardException{

		return contractDao.getSegmentDetails(segId);
	}
	
	private List<ReorderRecommendationVO> getSparkxReorderRecommendation(List<CustomerUserVO> customerEmailId,
			String loggedUser) {

		List<ReorderRecommendationVO> reorderSparxReommendation = new ArrayList<ReorderRecommendationVO>();
		Map<String, ProductVO> skuMap = new LinkedHashMap<String, ProductVO>();
		JSONObject objJSON = null;
		count = Constants.SPARKX_REORDER_COUNT;
		scheme = Constants.SPARKX_REORDER_SCHEME;

		for (CustomerUserVO cust : customerEmailId) {
			if (cust.getCustomerUserEmailId() != "" && cust.getCustomerUserEmailId() != null) {
				objJSON = fetchSkuRecommendationFromTrillion(cust.getCustomerUserEmailId(),
						Constants.SPARKX_REORDER_SCHEME,count);
				if (objJSON != null) {

					skuMap = convertToProductList(objJSON);
					if (skuMap.size() > 0) {
						for (String sku : skuMap.keySet()) {
							ReorderRecommendationVO recommendation = new ReorderRecommendationVO();
							recommendation.setCustNum(cust.getCustNum());
							recommendation.setSkuNumber(sku);
							recommendation.setCustName(cust.getCutomerUserName());
							reorderSparxReommendation.add(contractDao.getSKUInfo(recommendation));
						}

						logRecommendation(cust, loggedUser, skuMap);
					}
				}
			}

		}

		return reorderSparxReommendation;
	}

	public JSONObject fetchSkuRecommendationFromTrillion(String input, String schema,int count) {
		String strResponse = new String();
		StringBuilder inputType = new StringBuilder("");

		
		String strJsonInput = "{\"runa-user-token\": \"" + strUserToken + "\",\"merchant-id\": \"" + strMerchantId
				+ "\", \"client-request-id\": \"arbitrary values!\"," + "\"requested-schemes\": [{\"scheme\": \""
				+ schema + "\", \"count\": " + count + ", \"width\": 0, \"height\": 0}],";

		if (Constants.SPARKX_REORDER_SCHEME.equals(schema)) {
			inputType.append("\"email\":\"" + input + "\"");
		}
		else if(Constants.SPARKX_BOB_SCHEME.equals(schema))
		{
			inputType.append("\"products-on-current-page\": [{\"sku\": \""+input+"\"}]}"); 
		}

		String strRequest = strJsonInput + inputType + "}";
		LOGGER.info("JSON request to Recommendation Trillion Service  " + strRequest);
		JSONObject objJSON = null;
		String strJson = null;
		URL url;
		try {

			url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(strRequest.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((strResponse = br.readLine()) != null) {
				strJson = strResponse.substring(1, strResponse.length() - 1).toString();

			}
			LOGGER.info("response from RECOMM_TRILLION_SVC  " + strJson);
			objJSON = new JSONObject(strJson);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objJSON;
	}

	private Map<String, ProductVO> convertToProductList(JSONObject objJSON) {
		Map<String, ProductVO> skuList = new LinkedHashMap<String, ProductVO>();

		for (int i = 0; i < objJSON.getJSONArray("spinoffs").length(); i++) {
			if (objJSON.getJSONArray("spinoffs") != null && !objJSON.getJSONArray("spinoffs").isNull(i)) {
				JSONObject localObjectSpinOff = (JSONObject) objJSON.getJSONArray("spinoffs").get(i);
				if (localObjectSpinOff.getJSONArray("products") != null
						&& !localObjectSpinOff.getJSONArray("products").isNull(0)) {
					JSONObject localObjectProducts = (JSONObject) localObjectSpinOff.getJSONArray("products").get(0);
					String sku = localObjectProducts.get("sku").toString();
					String strategy = localObjectProducts.get("strategy").toString();
					String file = localObjectProducts.get("file").toString();

					if (sku != null && !sku.isEmpty()) {
						ProductVO product = new ProductVO();
						product.setSku(sku);
						product.setStrategy(strategy);
						product.setFile(file);
						skuList.put(sku, product);

					}
				}
			}
		}
		return skuList;
	}

	public void logRecommendation(CustomerUserVO custDetail, String loggedUser, Map<String, ProductVO> productMap) {

		if (productMap != null && !productMap.isEmpty()) {

			List<ProductVO> list = new ArrayList<ProductVO>(productMap.values());
			logRecommendationVO = new LogRecommendationVO();

			if (Constants.SPARKX_REORDER_SCHEME.equals(scheme)) {
				logRecommendationVO.setReqInput(custDetail.getCustomerUserEmailId());
				logRecommendationVO.setInputType("email");
			}
			else if (Constants.SPARKX_BOB_SCHEME.equals(scheme)) {
				logRecommendationVO.setInputType("sku_number");
				logRecommendationVO.setReqInput(custDetail.getBoughtSKU());
			}
			logRecommendationVO.setSchemeQueried(scheme);
			logRecommendationVO.setCountRequested(count);
			logRecommendationVO.setRecommendedSkus(getSkus(list));
			logRecommendationVO.setStrategy(getStrategySkuMapping(list));
			logRecommendationVO.setSparxFile(getStrategyFileMapping(list));
			logRecommendationVO.setRepId(Long.parseLong(custDetail.getRepId()));
			logRecommendationVO.setCustomerNumber(Long.parseLong(custDetail.getCustNum()));
			logRecommendationVO.setLoggedUser(Long.parseLong(loggedUser));
			logRecommendationVO.setCustomerUser("[Customer Contact: "+custDetail.getCutomerUserName()+"]");

			contractDao.logRecommendation(logRecommendationVO);
		}
	}

	private String getSkus(List<ProductVO> list) {
		StringBuffer value = new StringBuffer();
		for (ProductVO product : list) {
			if (value.length() > 0) {
				value.append(",");
			}
			value.append(product.getSku());
		}
		return value.toString();
	}

	private String getStrategyFileMapping(List<ProductVO> schemeSkuMap) {
		Map<String, String> strategyMapping = new LinkedHashMap<String, String>();
		for (ProductVO entry : schemeSkuMap) {
			String strategy = entry.getStrategy();
			String file = entry.getFile();
			String strategyJson = strategyMapping.get(strategy);
			strategyJson = (strategyJson != null ? strategyJson + "," : "");
			strategyMapping.put(strategy, strategyJson + file);
		}
		StringBuffer values = new StringBuffer();
		for (Map.Entry<String, String> entry : strategyMapping.entrySet()) {
			if (values.length() > 0) {
				values.append(",");
			}
			values.append("[" + entry.getKey() + ":" + entry.getValue() + "]");
		}

		return values.toString();
	}

	private String getStrategySkuMapping(List<ProductVO> schemeSkuMap) {
		Map<String, String> strategyMapping = new LinkedHashMap<String, String>();
		for (ProductVO entry : schemeSkuMap) {
			String strategy = entry.getStrategy();
			String sku = entry.getSku();
			String strategyJson = strategyMapping.get(strategy);
			strategyJson = (strategyJson != null ? strategyJson + "," : "");
			strategyMapping.put(strategy, strategyJson + sku);
		}

		StringBuffer values = new StringBuffer();
		for (Map.Entry<String, String> entry : strategyMapping.entrySet()) {
			if (values.length() > 0) {
				values.append(",");
			}
			values.append("[" + entry.getKey() + ":" + entry.getValue() + "]");
		}

		return values.toString();
	}
private List<BoughtAlsoBoughtInfoVO> getBoughtAlsoBoughtRecommendation(String custNum, String orderContact,String loggedUser) {
		
		List<BoughtAlsoBoughtInfoVO> recommendedVO= new ArrayList<BoughtAlsoBoughtInfoVO> ();
		Map<String,List<String>> boughtSkus= null;
		Map<String, ProductVO> skuMap = new LinkedHashMap<String, ProductVO>();
		JSONObject objJSON = null;
		count = Constants.SPARKX_BOB_COUNT;
		scheme = Constants.SPARKX_BOB_SCHEME;
		BoughtAlsoBoughtInfoVO boughtAlsoBought=null;
		
		
		boughtSkus=contractDao.getBoughtSkus(custNum,orderContact);
		
		if (boughtSkus !=null && boughtSkus.size()>0)
		{
		
			for (String sku : boughtSkus.keySet()) {
				if (sku != "" && null!=sku) {
					//System.out.println("Bought SKU: "+sku);
					objJSON = fetchSkuRecommendationFromTrillion(sku,scheme,count);
					if (objJSON != null) {
						boughtAlsoBought = new BoughtAlsoBoughtInfoVO();
						boughtAlsoBought.setSkuNumber(sku);
						boughtAlsoBought.setCustomerNumber(custNum);
						boughtAlsoBought.setOrderDate(boughtSkus.get(sku).get(0));
						//List<CustRecommendationVO> recommendationList=new ArrayList<CustRecommendationVO>();
						boughtAlsoBought.setCustRecommendationVOList(new ArrayList<CustRecommendationVO>());
						boughtAlsoBought.setRecSkuList(new ArrayList<String> ());
						mapSkuDetails(contractDao.getBABSKUInfo(sku),boughtAlsoBought,false);
						skuMap = convertToProductList(objJSON);
						if (skuMap.size() > 0) {
							for (String recommendedSKU : skuMap.keySet()) {
								//System.out.println("recommendedSKU: " + recommendedSKU);
								boughtAlsoBought.getRecSkuList().add(recommendedSKU);
								mapSkuDetails(contractDao.getBABSKUInfo(recommendedSKU),boughtAlsoBought,true);
							}
							
						}
						
					}
				}
				recommendedVO.add(boughtAlsoBought);
				//System.out.println(boughtAlsoBought.getCustRecommendationVOList());
				CustomerUserVO custDetail=new CustomerUserVO();
				custDetail.setCustNum(custNum);
				custDetail.setCutomerUserName(orderContact);
				custDetail.setBoughtSKU(sku);
				custDetail.setRepId(boughtSkus.get(sku).get(1));
				logRecommendation(custDetail, loggedUser, skuMap);
				
			}
			
		}
		
		//System.out.println("Bought SKUS"+boughtSkus);
		
		return recommendedVO;
	}

	private void mapSkuDetails(CustRecommendationVO custRecommendationList, BoughtAlsoBoughtInfoVO boughtAlsoBought, boolean isReommendedSku) {
		// TODO Auto-generated method stub
	
		if (custRecommendationList!=null )
		{
			if(isReommendedSku){
				boughtAlsoBought.getCustRecommendationVOList().add(custRecommendationList);
				 	  
			   }else {
				   if(boughtAlsoBought.getSkuNumber().equals(custRecommendationList.getSkuNumber())){
					   boughtAlsoBought.setItemDescription(custRecommendationList.getItemDescription());
					   boughtAlsoBought.setCatenTryId(custRecommendationList.getCatenTryId());
					   boughtAlsoBought.setThumbnail(custRecommendationList.getThumbnail());
					  }
			
			   }
		  
		}
			
	}
	
	@Override
	public LastLiveContactAndHistoryVO getLastLiveContactDetails(String iamId, String custNumber)
			throws DashboardException {
		LastLiveContactAndHistoryVO vo = null;
		try {
			vo = contractDao.getLastLiveContactDetails(iamId, custNumber);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return vo;
	}

	@Override
	public String updateAlertStatus(String custId,String ldapUserRole, String ldapUserId,String ldapUserName) throws DashboardException, DashboardException {
		// TODO Auto-generated method stub
		String status="";
		try {
			status = contractDao.updateAlertStatus(custId,ldapUserRole,ldapUserId,ldapUserName);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
		return status;
	}

	@Override
	public List<AlertDetailVo> getAlertDetail(String custNum) throws DashboardException {
		// TODO Auto-generated method stub
		return contractDao.getAlertDetail(custNum);
	}

	@Override
	public String deleteAlert(String custNum, String alertIdCombination, String alertId, String ldapUserId) throws DashboardException {
		// TODO Auto-generated method stub
		return contractDao.deleteAlert(custNum,alertIdCombination,alertId,ldapUserId);
	}
	@Override
	public String updateReadAlert(String custNum, String alertIdCombination, String alertId,String ldapUserId) throws DashboardException {
		// TODO Auto-generated method stub
		return contractDao.updateReadAlert(custNum,alertIdCombination,alertId,ldapUserId);
	}
	
	/**
	 * Method implementation for getting latest fiscal date.
	 * 
	 * @param String
	 *            cusNum
	 * @return String
	 */
	@Override
	public String getLatestFiscaCompleteOrderDate(String custNum)
			throws DashboardException {
		try {
			String latestFiscalDate = contractDao.getLatestFiscaCompleteOrderDate(custNum);
			return latestFiscalDate;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
}