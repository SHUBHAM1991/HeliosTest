/**
 * 
 */
package com.staples.dashboard.app.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonCreator;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.sdc.vo.RewardsStatementVO;
import com.staples.dashboard.app.service.CTAService;
import com.staples.dashboard.app.service.ContractDashboardService;
import com.staples.dashboard.app.service.PerfDashboardService;
import com.staples.dashboard.app.service.UserActivityService;
import com.staples.dashboard.app.service.UserRoleService;
import com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO;
import com.staples.dashboard.app.sfdc.OutputTasksfdc;
import com.staples.dashboard.app.sfdc.Task;
import com.staples.dashboard.app.utilities.CTAUtil;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.utilities.LoggerUtil;
import com.staples.dashboard.app.vo.AccountProgressInfo;
import com.staples.dashboard.app.vo.AlertDetailVo;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.ContactDateVO;
import com.staples.dashboard.app.vo.CategoryPenetrationVo;
import com.staples.dashboard.app.vo.CdmCustProfileVO;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.CustRecommendationVO;
import com.staples.dashboard.app.vo.CustSfdcInfoVO;
import com.staples.dashboard.app.vo.CustomerDataVo;
import com.staples.dashboard.app.vo.DispositionDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.JQueryDataTableOutputDTO;
import com.staples.dashboard.app.vo.JQueryDataTableOutputDTOCDM;
import com.staples.dashboard.app.vo.LastLiveContactAndHistoryVO;
import com.staples.dashboard.app.vo.NotificationInfoVo;
import com.staples.dashboard.app.vo.OnlineRetailReorderRecommendationVO;
import com.staples.dashboard.app.vo.OrderContactInfoVO;
import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;
import com.staples.dashboard.app.vo.PurchaseDetailsSelectedVO;
import com.staples.dashboard.app.vo.PurchaseHeliosDetailsVO;
import com.staples.dashboard.app.vo.RecommOrderContactVO;
import com.staples.dashboard.app.vo.RecommendationViewNotBoughtVO;
import com.staples.dashboard.app.vo.ReorderRecommendationVO;
import com.staples.dashboard.app.vo.SavingsVo;
import com.staples.dashboard.app.vo.SbaDiffDetailsVo;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegMktgResources;
import com.staples.dashboard.app.vo.SegmentComments;
import com.staples.dashboard.app.vo.SegmentDetail;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToVO;
import com.staples.dashboard.app.vo.SubCallToActionDataVo;
import com.staples.dashboard.app.vo.SubplayInfoVo;
import com.staples.dashboard.app.vo.YTDInfoVO;
import com.staples.dashboard.dto.AccountUserDTO;
import com.staples.dashboard.dto.CustomerListDTO;
import com.staples.dashboard.dto.UserRoleDTO;
import com.staples.dashboard.exception.DashboardException;



/**
 * The Class HeliosWebController.
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
 *          <td>1.1</td>
 *          <td>Jan 13, 2016</td>
 *          <td>Bipin Kumar</td>
 *          <td>Changes to implement RefreshTime stamp for Grids</td>
 *          </tr>
 *          </table>
 *          </p>
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 */
@Controller
public class HeliosWebController {

	private static final Logger LOGGER = Logger
			.getLogger(HeliosWebController.class);

	@Autowired
	private ContractDashboardService contractService;

	@Autowired
	private UserActivityService userActivityService;

	private static UserRoleDTO userRoleDTO;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private CTAService ctaService;

	private @Value("${storelocatorservice.url}")
	String storeLocatorServiceURL;
	
	@Autowired
	private PerfDashboardService perfWIRService;

	private @Value("${sfdcApp.url}")
	String sfdcAppUrl;
	
	private @Value("${sam.sfdcApp.url}")
	String samSfdcAppUrl;

	private @Value("${rewardStatements.url}")
	String rewardStatements;
	
	private @Value("${rewardsForStatement.url}")
	String rewardsForStatement;
	
	private @Value("${categorySaving.url}")
	String categorySaving;
	
	private @Value("${CtaDisableId}")
	String CtaDisableId;
	
	
	/**
	 * @return the contractService
	 */
	public ContractDashboardService getContractService() {
		return contractService;
	}

	/**
	 * @param contractService
	 *            the contractService to set
	 */
	public void setContractService(ContractDashboardService contractService) {
		this.contractService = contractService;
	}
	
	public PerfDashboardService getPerfWIRService() {
		return perfWIRService;
	}

	public void setPerfWIRService(PerfDashboardService perfWIRService) {
		this.perfWIRService = perfWIRService;
	}




	Boolean redirect = false;

	/**
	 * Method implementation to open home page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/home_template2" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openTemplateTwo(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);

			String contactName = request.getParameter("contactName");
			String contactDate = request.getParameter("contactDate");
			String contactStatus = request.getParameter("contactStatus");
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			request.setAttribute(Constants.FROM, Constants.HOME);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String iStart = request.getParameter(Constants.ISTART);

			request.setAttribute(Constants.REQREPID, accid);
			request.setAttribute("contactName", contactName);
			request.setAttribute("contactDate", contactDate);
			request.setAttribute("contactStatus", contactStatus);

			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null || custid.equals("")) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(loggedInUserId);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				String hold_out_status = contractService.checkAccountHoldoutStatus(custid,accid);
				if(null!=hold_out_status && !("".equals(hold_out_status)) && "holdout".equals(hold_out_status)){
					request.setAttribute(Constants.ACCESSDENIED, Constants.ACCESSDENIED_STATUS);
					return "accessDenied";
				}
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				request.setAttribute(Constants.SEE_ALL,
						(null != request.getParameter(Constants.SEE_ALL)
								&& Constants.SEE_ALL.equals(request.getParameter(Constants.SEE_ALL))) ? Constants.SEE_ALL
										: Constants.NO);
				request.setAttribute(Constants.CDM_TOTAL_COUNT,
						(null == request.getParameter(Constants.CDM_TOTAL_COUNT)
								|| ("".equals(request.getParameter(Constants.CDM_TOTAL_COUNT)))) ? "" :request.getParameter(Constants.CDM_TOTAL_COUNT));
				request.setAttribute(Constants.CTA_DISPOSITION_DISABLED_IDS, CtaDisableId);
				String alertState=request.getParameter("alertState");
				if(null==alertState || (alertState.equals("")))
					request.setAttribute("alertState", "OFF");
				else
					request.setAttribute("alertState",alertState);
				return Constants.HOME;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = { "/home_template3" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openTemplateThree(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String contactName = request.getParameter("contactName");
			String contactDate = request.getParameter("contactDate");
			String contactStatus = request.getParameter("contactStatus");
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			request.setAttribute(Constants.FROM, Constants.HOME);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String iStart = request.getParameter(Constants.ISTART);

			request.setAttribute(Constants.REQREPID, accid);
			request.setAttribute("contactName", contactName);
			request.setAttribute("contactDate", contactDate);
			request.setAttribute("contactStatus", contactStatus);

			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(custid);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}

				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				request.setAttribute("onlineRetail",'Y');
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return "TemplateThree";

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to open new SAM home page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/home_template4" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openTemplateFour(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			request.setAttribute(Constants.FROM, Constants.HOME);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String iStart = request.getParameter(Constants.ISTART);

			request.setAttribute(Constants.REQREPID, accid);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
		                else{
		                	AccountUserDTO callOrderScreenUser = userRoleService
		    						.getUserDetail(loggedInUserId);
		                	if(null !=callOrderScreenUser)
		                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
		                }
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
	                else{
	                	AccountUserDTO callOrderScreenUser = userRoleService
	    						.getUserDetail(loggedInUserId);
	                	if(null !=callOrderScreenUser)
	                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
	                }
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				String mktResourceUrl = samSfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return Constants.SAM_NEW_HOME;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Method implementation to open recommendations page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/recommActionSAMNew" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String openRecommendationsSAMNew(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);
			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
		                else{
		                	AccountUserDTO callOrderScreenUser = userRoleService
		    						.getUserDetail(loggedInUserId);
		                	if(null !=callOrderScreenUser)
		                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
		                }
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
	                else{
	                	AccountUserDTO callOrderScreenUser = userRoleService
	    						.getUserDetail(loggedInUserId);
	                	if(null !=callOrderScreenUser)
	                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
	                }
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				request.setAttribute("onlineRetail", onlineRetail);
				String mktResourceUrl = samSfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return Constants.RECOMMENDATION_SAM_NEW;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	
	/**
	 * Method implementation to open recommendations page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/recommAction" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String openRecommendations(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String contactName = request.getParameter("contactName");
			String contactDate = request.getParameter("contactDate");
			String contactStatus = request.getParameter("contactStatus");

			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);

			request.setAttribute("contactName", contactName);
			request.setAttribute("contactDate", contactDate);
			request.setAttribute("contactStatus", contactStatus);

			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(loggedInUserId);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				request.setAttribute("onlineRetail", onlineRetail);
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				request.setAttribute(Constants.CTA_DISPOSITION_DISABLED_IDS, CtaDisableId);
				String alertState=request.getParameter("alertState");
				if(null==alertState || (alertState.equals("")))
					request.setAttribute("alertState", "OFF");
				else
					request.setAttribute("alertState",alertState);
				return Constants.RECOMMENDATION;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}


	@RequestMapping(value = { "/subCallToAction/{cid}" }, method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json")
	@ResponseBody
	public String openSubCallToAction(@PathVariable("cid") String custNum,
			@RequestParam("SubSegDesc") String SubSegDesc,
			@RequestParam("CtaName") String CtaName,
			@RequestParam("SegId") String SegId
			)
					throws DashboardException, Exception {
		try {
			LOGGER.info("Entered on openSubCallToAction() ");
			Gson gson = new Gson();
			SubCallToActionDataVo subCallToActionDataVo = new SubCallToActionDataVo();
			contractService.refreshPlaySegmentParam(custNum, SegId);
			LOGGER.info("Exiting from refreshPlaySegmentParam() ");
			SegHdrText segHdrText= contractService
					.getSubCtaDeatils(CtaName,SegId,custNum);
			LOGGER.info("Exiting from getSubCtaDeatils() ");
			
			List<SegmentSubDetail> attributeList =getSegSubDetail(null,SegId,custNum);
			if(null!=segHdrText && null!=attributeList){
				segHdrText.setAttributeList(attributeList);
			}
			List<SegMktgResources> mktgResources = getSegMktgResources(CtaName, SegId);
			segHdrText.setMktgResources(mktgResources);
			return gson.toJson(segHdrText);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}finally{
			LOGGER.info("Exiting from openSubCallToAction()");
		}
	}
	@RequestMapping(value = { "/applySubCallToAction/{cid}" }, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
	@ResponseBody
	public String saveSubCallToAction(@PathVariable("cid") String custNum,
			@RequestParam("RationaleHtmlCode") String rationaleHtml,
			HttpServletRequest request) throws DashboardException, Exception {
		try {
			Gson gson = new Gson();
			SubCallToActionDataVo subCallToActionDataVo = new SubCallToActionDataVo();
			subCallToActionDataVo.setRationaleHtmlData(rationaleHtml);
			return gson.toJson(subCallToActionDataVo);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * Method implementation to open home page based on the user's rep role
	 * code.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request) throws DashboardException,
	Exception {
		try {
			// String custid=(String)request.getParameter("reqCustNum");
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();

			request.setAttribute("from", "home");
			if (role.equalsIgnoreCase("[ADMIN]")
					|| role.equalsIgnoreCase("[MANAGER]")
					|| role.equalsIgnoreCase("[PLAYOWNER]")) {
				redirect = false;
				request.setAttribute("from", "home");
				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				request.setAttribute(Constants.REQREPID, user.getUsername());
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.MANAGER);
				}
				return "login";
			}

			/*
			 * if(custid==null){ return "login"; } else{
			 * request.setAttribute("custNo", custid);
			 * 
			 * return "cust_profiles"; }
			 */
			else {
				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				request.setAttribute(Constants.REQUESTCUSTOMERNUMBER, user.getUsername());
				redirect = true;
				String redirectUrl =null;
				if(null !=user && !("null".equals(user)) && null !=user.getUsername() && (null !=role) && !(role.equalsIgnoreCase("[ROLE_ANONYMOUS]")))
					redirectUrl="cp_online_retail?reqCustNum="+ user.getUsername();
				else
					redirectUrl="accessDenied";
				return "redirect:" + redirectUrl;
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to open customer profile page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String root(HttpServletRequest request) throws DashboardException,
	Exception {
		try {
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();
			if (role.equalsIgnoreCase("[ADMIN]")
					|| role.equalsIgnoreCase("[MANAGER]")
					|| role.equalsIgnoreCase("[PLAYOWNER]")) {
				request.setAttribute("from", "home");

				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				request.setAttribute(Constants.REQREPID, user.getUsername());
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.MANAGER);
				}
				return "login";
			}

			else {
				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				request.setAttribute(Constants.REQUESTCUSTOMERNUMBER, user.getUsername());
				redirect = true;
				String redirectUrl =null;
				if(null !=user && !("null".equals(user)) && null !=user.getUsername() && (null !=role) && !(role.equalsIgnoreCase("[ROLE_ANONYMOUS]")))
					redirectUrl="cp_online_retail?reqCustNum="+ user.getUsername();
				else
					redirectUrl="accessDenied";
				return "redirect:" + redirectUrl;
			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to fetch customer data.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selYear
	 * @return String
	 */
	@RequestMapping(value = "/getCustData/{cid}/{selYear}/{users}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String openTempOne(@PathVariable("cid") String custNum,
			@PathVariable("selYear") String selYear ,@PathVariable("users") String users) throws DashboardException,
			Exception {
		try {

			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();


			List<CustProfileVO> custProfileVO = contractService
					.getCustomerProfile(custNum);
			if (null != custProfileVO) {
				List<YTDInfoVO> ytdInfoVOList = contractService
						.getYTDInfo(custNum);
				customerDataVo.setListOfCustProfileVO(custProfileVO);
				customerDataVo.setYtdInfoVOList(ytdInfoVOList);
				gson.toJson(customerDataVo);
				if (null != users && !("".equals(users))
						&& ("RETAIL".equals(users)))
					contractService.viewYTDSpend(selYear, custNum,
							customerDataVo);
				else if (null != users && !("".equals(users))
						&& ("RETAILONLINE".equals(users)))
					contractService.viewYTDSpendSAM(selYear, custNum,
							customerDataVo);

			}


			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch super user high level data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getSuperUSerHighLevelData/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String openSuperUserDetails(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {
		try {
			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();

			if (null != custNum) {

				contractService.viewSuperUserDetails(custNum, customerDataVo);

				gson.toJson(customerDataVo);

			}
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to open order details grid.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selMonthYear
	 * @param String
	 *            selCatId
	 * @param String
	 *            onChange
	 * @return String
	 */
	@RequestMapping(value = "/getOrderDetailsHighLevelData/{cid}/{selMonthYear}/{selCatId}/{OnChange}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String openOrderDetails(@PathVariable("cid") String custNum,
			@PathVariable("selMonthYear") String selMonthYear,
			@PathVariable("selCatId") String selCatId,
			@PathVariable("OnChange") String OnChange)
					throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();
			String latestFiscalDate = null;

			if (null != custNum) {
				PurchaseDetailsSelectedVO objPurchDtlsSelectedVO = new PurchaseDetailsSelectedVO();
				objPurchDtlsSelectedVO.setSelectedCategory(selCatId);
				objPurchDtlsSelectedVO.setSelectedMonthYear(selMonthYear);
				if (OnChange.equalsIgnoreCase(Constants.NOCHANGE)) {
					latestFiscalDate = contractService
							.getLatestFiscalDate1(custNum);
				}
				if (latestFiscalDate != null) {
					objPurchDtlsSelectedVO
					.setSelectedMonthYear(latestFiscalDate);
				}
				objPurchDtlsSelectedVO.setSelectedSubAcctMbr(custNum);
				contractService.viewOrderPurchaseDetails(
						objPurchDtlsSelectedVO, custNum, customerDataVo);

				gson.toJson(customerDataVo);

			}
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = "/getShipToDetails/{custNum}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getShipToDetails(@RequestParam("shipTo") String shipTo,
			@PathVariable("custNum") String custNum) throws DashboardException,
			Exception {

		try {
			Gson gson = new Gson();

			List<ShipToDetailsVo> shipToDetails = contractService
					.viewShipToDetailsInfo(shipTo, custNum);

			// gson.toJson(shipToDetails);
			return gson.toJson(shipToDetails);
		}

		catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to fetch customer profile.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/cust_profiles" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String openCustProfiles(HttpServletRequest request)
			throws DashboardException, Exception {

		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = custid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {// control should not go in to this
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.REQREPID, custid);
				String repRoleCd = contractService.getAgentRepRoleCd(custid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				return Constants.CUSTOMERPROFILE;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to fetch customer profiles.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/home_cust_profiles" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openHomeCustProfiles(HttpServletRequest request)
			throws DashboardException, Exception {

		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlayParams = request.getParameter("subPlayParams");
			if (!role.equalsIgnoreCase("[ADMIN]")
					&& !role.equalsIgnoreCase("[MANAGER]")) {
				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				custid = user.getUsername();
			}
			if (custid == null) { // this should not be the case
				return Constants.LOGIN;

			} else {

				String accId = null;
				if (request.getParameter(Constants.LOGGEDINUSERID) == null
						|| "".equals(request
								.getParameter(Constants.LOGGEDINUSERID))) {
					accId = custid;
				} else
					accId = request.getParameter(Constants.LOGGEDINUSERID);

				if (accId != null)
					accId = accId.trim();

				AccountUserDTO callOrderScreenUser = userRoleService
						.getUserDetail(custid);
				AccountUserDTO loggedInUser = userRoleService
						.getUserDetail(accId);
				request.setAttribute(Constants.LEVEL,
						callOrderScreenUser.getLevel());
				request.setAttribute(Constants.LOGGEDINUSERLEVEL,
						loggedInUser.getLevel());
				request.setAttribute("accID", accId);
				request.setAttribute(Constants.REQREPID, custid);
				String repRoleCd = contractService.getAgentRepRoleCd(custid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(accId);

				/*LayoutVo layoutVo=null;
				if(null !=repRoleCd && (repRoleCd.equals("AM")))
				layoutVo=contractService.getLayoutDetails(repRoleCd,"MM");
				else if(null !=repRoleCd)
				layoutVo=contractService.getLayoutDetails(repRoleCd,null);
				Gson gson=new Gson();
				request.setAttribute("layoutvo",gson.toJson(layoutVo));*/
				request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				String from = request.getParameter(Constants.FROM);
				if ((null != from) && (from.equals(Constants.HOME)))
					request.setAttribute(Constants.FROM, Constants.HOME);

				if (null != filterBy) {
					request.setAttribute(Constants.FILTERBY, filterBy);
				}
				if (null != subPlayParams) {
					if (subPlayParams.contains("FORWARD_SLASH")) {
						subPlayParams = (subPlayParams).replaceAll(
								"FORWARD_SLASH", "/");

					}
					if (subPlayParams.contains("AC")
							|| subPlayParams.contains("AB")) {
						if (subPlayParams.contains("AC")) {
							subPlayParams = (subPlayParams).replaceAll("AC",
									"Account");
						} else if (subPlayParams.contains("AB")) {
							subPlayParams = (subPlayParams).replaceAll("AB",
									"account");
						}
					}
					if (subPlayParams.contains("PERCENT")) {
						subPlayParams = (subPlayParams).replaceAll("PERCENT",
								"%");

					}
					if (subPlayParams.contains("_AND_")) {
						subPlayParams = (subPlayParams)
								.replaceAll("_AND_", "&");

					}
					if (subPlayParams.contains("_DOT_")) {
						subPlayParams = (subPlayParams)
								.replaceAll("_DOT_", ".");

					}
					if (subPlayParams.contains("_GREATER_")) {
						subPlayParams = (subPlayParams).replaceAll("_GREATER_",
								">");

					}
					if (subPlayParams.contains("_LESS_")) {
						subPlayParams = (subPlayParams).replaceAll("_LESS_",
								"<");

					}
					if (subPlayParams.contains("_SEMI_")) {
						subPlayParams = (subPlayParams).replaceAll("_SEMI_",
								";");

					}
					request.setAttribute("subPlayParams", subPlayParams);
					request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
					request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
					request.setAttribute("assignType", request.getParameter("assignType"));
					request.setAttribute("from", "DashBoard");

				}
				return Constants.CUSTOMERPROFILE;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to fetch customer profiles.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/cp_online_retail" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openCustProfilesOnlineRetail(HttpServletRequest request)
			throws DashboardException, Exception {

		try {
			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			//if(null !=custid&& "1120")
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlayParams = request.getParameter("subPlayParams");
			if (!role.equalsIgnoreCase("[ADMIN]")
					&& !role.equalsIgnoreCase("[MANAGER]") && !role.equalsIgnoreCase("[READONLY]")) {
				User user = (User) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				custid = user.getUsername();
			}
			String selectedQualScore= (String)request.getParameter(Constants.SELECTEDQUALSCORE);
			String selectedSegScore= (String)request.getParameter(Constants.SELECTEDSEGSCORE);
			if (custid == null) { // this should not be the case
				return Constants.LOGIN;

			} else {

				String accId = null;
				if (request.getParameter(Constants.LOGGEDINUSERID) == null
						|| "".equals(request
								.getParameter(Constants.LOGGEDINUSERID))) {
					accId = custid;
				} else
					accId = request.getParameter(Constants.LOGGEDINUSERID);

				if (accId != null)
					accId = accId.trim();

				AccountUserDTO callOrderScreenUser = userRoleService
						.getUserDetail(custid);
				AccountUserDTO loggedInUser = userRoleService
						.getUserDetail(accId);
				request.setAttribute(Constants.LEVEL,
						callOrderScreenUser.getLevel());
				request.setAttribute(Constants.LOGGEDINUSERLEVEL,
						loggedInUser.getLevel());
				request.setAttribute("accID", accId);
				request.setAttribute(Constants.REQREPID, custid);
				String repRoleCd = contractService.getAgentRepRoleCd(custid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(accId);
				String accountNumber = null;
				String assignAccType = null;
				String tempAssignAccType = null;
				if (null != custid) {

					accountNumber = contractService.getAccountNumber(custid);
					if(null !=accountNumber && accountNumber.indexOf("##")!=-1){
						assignAccType=accountNumber.split("##")[1];
						tempAssignAccType=assignAccType;
						if((null !=assignAccType) && (assignAccType.indexOf("SAM-AM")!=-1 && assignAccType.indexOf("SAMD-")!=-1) || (assignAccType.indexOf("SAM-")!=-1 && assignAccType.indexOf("MM-")!=-1 && assignAccType.indexOf("SAMD-")!=-1) ){
							assignAccType="AM_SAM_SAMD";
						}else if((null !=assignAccType) && (assignAccType.indexOf("SAM-AM")!=-1 && assignAccType.indexOf("SAMD-")==-1) || (assignAccType.indexOf("SAM-")!=-1 && assignAccType.indexOf("MM-")!=-1 && assignAccType.indexOf("SAMD-")==-1) ) {
							assignAccType="AM_SAM";
						}else if((null !=assignAccType) && (assignAccType.indexOf("MM-")!=-1 && assignAccType.indexOf("SAMD-")!=-1 && assignAccType.indexOf("SAM-")==-1)) {
							assignAccType="AM_SAMD";
						}else if((null !=assignAccType) && (assignAccType.indexOf("SAMD-")!=-1 && assignAccType.indexOf("SAM-")!=-1 && assignAccType.indexOf("MM-")==-1) ){
							assignAccType="SAM_SAMD";
						} else if((null !=assignAccType) && ((assignAccType.indexOf("MM-")==-1 && assignAccType.indexOf("SAM-")==-1 && assignAccType.indexOf("SAMD-")!=-1))){
							assignAccType="OTHER_SAMD";
						} else if((null !=assignAccType) && ((assignAccType.indexOf("SAM-")!=-1 || assignAccType.indexOf("SAM-B2B")!=-1 || assignAccType.indexOf("SAM-SDR")!=-1 || assignAccType.indexOf("SAM-IBD")!=-1)) && assignAccType.indexOf("MM-")==-1 && assignAccType.indexOf("SAMD-")==-1){
							assignAccType="OTHER_SAM";
						}else if((null !=assignAccType) && assignAccType.indexOf("SAM-")==-1 && assignAccType.indexOf("MM-")!=-1 && assignAccType.indexOf("SAMD-")==-1){
							assignAccType="OTHER_MM";
						}
						assignAccType=authorizeCdm(tempAssignAccType,assignAccType);
					}
				}
				
				request.setAttribute("onlineRetail","Y");
				if(null!=loggedInUser)
					request.setAttribute(Constants.LOGGED_IN_USER_NAME, loggedInUser.getFullname());
				else
					request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);	
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute("assignType",assignAccType);
				String from = request.getParameter(Constants.FROM);
				if ((null != from) && (from.equals(Constants.HOME)))
					request.setAttribute(Constants.FROM, Constants.HOME);

				if (null != filterBy) {
					request.setAttribute(Constants.FILTERBY, filterBy);
				}
				if (null != subPlayParams) {
					if (subPlayParams.contains("FORWARD_SLASH")) {
						subPlayParams = (subPlayParams).replaceAll(
								"FORWARD_SLASH", "/");

					}
					if (subPlayParams.contains("AC")
							|| subPlayParams.contains("AB")) {
						if (subPlayParams.contains("AC")) {
							subPlayParams = (subPlayParams).replaceAll("AC",
									"Account");
						} else if (subPlayParams.contains("AB")) {
							subPlayParams = (subPlayParams).replaceAll("AB",
									"account");
						}
					}
					if (subPlayParams.contains("PERCENT")) {
						subPlayParams = (subPlayParams).replaceAll("PERCENT",
								"%");

					}
					if (subPlayParams.contains("_AND_")) {
						subPlayParams = (subPlayParams)
								.replaceAll("_AND_", "&");

					}
					if (subPlayParams.contains("_DOT_")) {
						subPlayParams = (subPlayParams)
								.replaceAll("_DOT_", ".");

					}
					if (subPlayParams.contains("_GREATER_")) {
						subPlayParams = (subPlayParams).replaceAll("_GREATER_",
								">");

					}
					if (subPlayParams.contains("_LESS_")) {
						subPlayParams = (subPlayParams).replaceAll("_LESS_",
								"<");

					}
					if (subPlayParams.contains("_SEMI_")) {
						subPlayParams = (subPlayParams).replaceAll("_SEMI_",
								";");

					}
					request.setAttribute("subPlayParams", subPlayParams);
					request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
					request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
					request.setAttribute("from", "DashBoard");

				}
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				if(null!=assignAccType && assignAccType.indexOf("WITH_CDM_REP")!=-1){
					if (((null == from) || (from.equals(""))) && "ALL".equals(filterBy))
						request.setAttribute(Constants.FROM, Constants.HOME);
					else if(((null == from) || (from.equals(""))) && null !=filterBy && !("ALL".equals(filterBy)))
						request.setAttribute(Constants.FROM, "DashBoard");
					else if(((null == from) || (from.equals(""))) && ((null == filterBy) || (filterBy.equals(""))))
						request.setAttribute(Constants.FROM, Constants.HOME);
					String alertState=request.getParameter("alertState");
					if(null==alertState || (alertState.equals("")))
						request.setAttribute("alertState", "OFF");
					else
						request.setAttribute("alertState", alertState);
					return "CustProfileOnlineRetailCdm";
				}
				else			
				    return "CustProfileOnlineRetail";

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to open login page.
	 * 
	 * @return String
	 */
	@RequestMapping(value = { Constants.CUSTLOGIN }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openLogin() {

		return Constants.OPENLOGIN;
	}

	/* Old Controller data start */

	/**
	 * Method implementation to fetch YTD spend for other years.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selYear
	 * @return String
	 */
	@RequestMapping(value = "/getOtherYearData/{cid}/{selYear}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String viewYTDSpendForOtherYears(
			@PathVariable("cid") String custNum,
			@PathVariable("selYear") String selYear) throws DashboardException,
			Exception {
		try {
			Gson gson = new Gson();

			CustomerDataVo customerDataVo = new CustomerDataVo();
			try {
				contractService.viewYTDSpendForOtherYears(custNum, selYear,
						customerDataVo);

			} catch (Exception ex) {
				LoggerUtil.logError(ex.getMessage(), ex, LOGGER);

			}
			LoggerUtil.logDebug(Constants.EXIT, LOGGER);
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch notification data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getNotifications/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getNotifications(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();

			if (null != custNum) {
				List<CustProfileVO> custProfileVO = contractService
						.getCustomerProfile(custNum);
				if (null != custProfileVO && !custProfileVO.isEmpty()) {

					List<NotificationInfoVo> notiInfoVOList = contractService
							.getNotiInfo(custNum);
					customerDataVo.setListOfCustProfileVO(custProfileVO);
					customerDataVo.setNotiInfoVOList(notiInfoVOList);
					gson.toJson(customerDataVo);
				}

			}
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch all customer data.
	 * 
	 * @param String
	 *            accId
	 * @param String
	 *            playSection
	 * @param String
	 *            selSubPlays
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = "/getAllCustomers/{accid}/{selPlaySection}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	@JsonCreator
	public String getAllCustomers(@PathVariable("accid") String accId,
			@PathVariable("selPlaySection") String playSection,
			//@PathVariable("selectedSubPlays") String selSubPlays,
			@RequestParam("selectedSubPlays") String selSubPlays,
			@RequestParam("selectedQualScore") String selectedQualScore,
			@RequestParam("selectedSegScore") String selectedSegScore,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("accType") String accType,
			HttpServletRequest request) throws DashboardException, Exception {
		try {
			String selectedQualScoreArr[] = null;
			String selectedSegScoreArr[] = null;
			//selSubPlays=URLDecoder.decode(selSubPlays);
			JQueryDataTableInputDTO dataTableInput = null;
			String iDisplayStart = request.getParameter("iDisplayStart");
			/*System.out.print(request.getParameterMap());
			System.out.print(request.getParameterNames());
			System.out.print(request.getParameterMap().keySet());*/
			String iDisplayLength = request.getParameter("iDisplayLength");
			String sSearch = request.getParameter("sSearch");
			String cusTypeSearch = request.getParameter("sSearch_2");
			String iSortingCols = request.getParameter("iSortingCols");
			String iSortCol_0 = request.getParameter("iSortCol_0");
			String sSortDir_0 = request.getParameter("sSortDir_0");
			String sEcho = request.getParameter("sEcho");
			if (sEcho != null && !sEcho.equals("")) {
				dataTableInput = new JQueryDataTableInputDTO();
				dataTableInput.setiDisplayLength(Integer
						.valueOf(iDisplayLength));
				dataTableInput.setiDisplayStart(Integer.valueOf(iDisplayStart));
				if (iSortCol_0 != null) {
					dataTableInput.setiSortCol_0(CustProfileVO
							.getCustDataMap(iSortCol_0));
				}
				if (null != cusTypeSearch) {
					dataTableInput.setsSearch_2(cusTypeSearch);
				}
				/*if (iSortCol_0 != null && iSortCol_0.equals("0")) {
					dataTableInput.setStatusSort(true);
					dataTableInput.setStatusSortOrder(sSortDir_0);
				}*/
				dataTableInput.setsSortDir_0(sSortDir_0);
				dataTableInput.setsSearch(sSearch);
				if (iSortingCols != null && !iSortingCols.equals("")) {
					dataTableInput.setiSortingCols(Integer
							.valueOf(iSortingCols));
				}
			}

			String selectedSubplayArr[] = null;
			String tempSelectedSubplayArr[] = null;
			JQueryDataTableOutputDTO returnList = new JQueryDataTableOutputDTO();

			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();
			if (selSubPlays.indexOf("#") != -1) {
				selectedSubplayArr = selSubPlays.split("#");
			} else if (null != selSubPlays && !(selSubPlays.equals(""))
					&& !(selSubPlays.equals("All"))) {
				selectedSubplayArr = new String[] { selSubPlays };
			}
			if (!(selectedQualScore.equals("")) && null != selectedQualScore && !(selectedQualScore.equals("ALL")) &&  selectedQualScore.indexOf("#") != -1) {
				selectedQualScoreArr = selectedQualScore.split("#");
			} else if (!(selectedQualScore.equals("")) && null != selectedQualScore && !(selectedQualScore.equals("ALL")) )
			{
				selectedQualScoreArr = new String[] { selectedQualScore };
			}
			if (!(selectedSegScore.equals("")) && null != selectedSegScore && !(selectedSegScore.equals("ALL")) &&  selectedSegScore.indexOf("#") != -1) {
				selectedSegScoreArr = selectedSegScore.split("#");
			} else if (!(selectedSegScore.equals("")) && null != selectedSegScore && !(selectedSegScore.equals("ALL")) )
			{
				selectedSegScoreArr = new String[] { selectedSegScore };
			}
			if (null != selectedSubplayArr && selectedSubplayArr.length > 0) {
				tempSelectedSubplayArr = new String[selectedSubplayArr.length];
				for (int count = 0; count < selectedSubplayArr.length; count++) {
					tempSelectedSubplayArr[count] = selectedSubplayArr[count];
				}
			}
			if (null != selectedSubplayArr && selectedSubplayArr.length > 0) {
				for (int count = 0; count < selectedSubplayArr.length; count++) {
					selectedSubplayArr[count] = "'" + selectedSubplayArr[count]
							+ "'";
					if (selectedSubplayArr[count].contains("FORWARD_SLASH")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("FORWARD_SLASH", "/");

					}
					if (selectedSubplayArr[count].contains("AC")
							|| selectedSubplayArr[count].contains("AB")) {
						if (selectedSubplayArr[count].contains("AC")) {
							selectedSubplayArr[count] = (selectedSubplayArr[count])
									.replaceAll("AC", "Account");
						} else if (selectedSubplayArr[count].contains("AB")) {
							selectedSubplayArr[count] = (selectedSubplayArr[count])
									.replaceAll("AB", "account");
						}

					}
					if (selectedSubplayArr[count].contains("_AND_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_AND_", "&");

					}
					if (selectedSubplayArr[count].contains("_DOT_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_DOT_", ".");

					}
					if (selectedSubplayArr[count].contains("_GREATER_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_GREATER_", ">");

					}
					if (selectedSubplayArr[count].contains("_LESS_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_LESS_", "<");

					}
					if (selectedSubplayArr[count].contains("_SEMI_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_SEMI_", ";");

					}
				}

			}

			if (null != accId) {
				CustomerListDTO custData = contractService.getAllCustomerInfo(
						accId, playSection, selectedSubplayArr, dataTableInput,selectedQualScoreArr,selectedSegScoreArr);
				List<CustProfileVO> listOfCustProfileVO = custData
						.getCustomerVoList();
				if (null != listOfCustProfileVO) {
					// request.setAttribute("subPlayParams",
					// selectedSubplayArr);
					/*
					 * List<NotificationInfoVo> notiInfoVOList =
					 * (List<NotificationInfoVo>) contractDao
					 * .getNotiInfo(custNum,segType);
					 */
					if (null != tempSelectedSubplayArr) {
						/*
						 * for(int
						 * count=0;count<tempSelectedSubplayArr.length;count++){
						 * tempSelectedSubplayArr
						 * [count]=(tempSelectedSubplayArr[count]).substring(1,
						 * (tempSelectedSubplayArr[count]).lastIndexOf("'")); }
						 */
						for (int count = 0; count < tempSelectedSubplayArr.length; count++) {
							// tempSelectedSubplayArr[count]="'"+tempSelectedSubplayArr[count]+"'";
							if (tempSelectedSubplayArr[count].contains("AC")
									|| tempSelectedSubplayArr[count]
											.contains("AB")) {
								if (tempSelectedSubplayArr[count]
										.contains("AC")) {
									tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
											.replaceAll("AC", "Account");
								} else if (tempSelectedSubplayArr[count]
										.contains("AB")) {
									tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
											.replaceAll("AB", "account");
								}

							}
							if (tempSelectedSubplayArr[count].contains("%")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("%", "PERCENT");

							}
							if (tempSelectedSubplayArr[count].contains("&")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("&", "_AND_");

							}
							if (tempSelectedSubplayArr[count].contains(".")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(".", "_DOT_");

							}
							if (tempSelectedSubplayArr[count].contains(">")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(">", "_GREATER_");

							}
							if (tempSelectedSubplayArr[count].contains("<")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("<", "_LESS_");

							}
							if (tempSelectedSubplayArr[count].contains(";")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(";", "_SEMI_");

							}
						}
						String unSelSubPlays = StringUtils
								.arrayToDelimitedString(tempSelectedSubplayArr,
										"~");
						customerDataVo.setUnSelListOfSubPlay(unSelSubPlays);
						returnList.setUnSelListOfSubPlay(unSelSubPlays);
					}
					customerDataVo.setListOfCustProfileVO(listOfCustProfileVO);
					returnList.setAaData(listOfCustProfileVO);
					returnList.setiTotalRecords(custData.getTotalCount());
					returnList.setiTotalDisplayRecords(custData
							.getTotalCount());

					if (sEcho != null && !sEcho.equals("")) {
						returnList.setsEcho(Integer.valueOf(sEcho));
					}

					gson.toJson(customerDataVo);

				}
			}
			/*LayoutVo layoutVo=null;
			if(null !=repRoleCode && !("".equals(repRoleCode)) && (repRoleCode.equals("AM")))
			layoutVo=contractService.getLayoutDetails(repRoleCode,accType);
			else if(null !=repRoleCode && !("".equals(repRoleCode)) )
			layoutVo=contractService.getLayoutDetails(repRoleCode,null);
			returnList.setLayoutVo(layoutVo);*/
			request.setAttribute(Constants.SELECTEDQUALSCORE, selectedQualScore);
			request.setAttribute(Constants.SELECTEDSEGSCORE, selectedSegScore);
			//selectedQualScore,
			//@RequestParam("selectedSegScore") String selectedSegScore
			return gson.toJson(returnList);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}




	@RequestMapping(value = "/getAllSamCustomers/{accid}/{selPlaySection}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	@JsonCreator
	public String getAllSamCustomers(@PathVariable("accid") String accId,
			@PathVariable("selPlaySection") String playSection,
			//@PathVariable("selectedSubPlays") String selSubPlays,
			@RequestParam("selectedSubPlays") String selSubPlays,
			@RequestParam("selectedQualScore") String selectedQualScore,
			@RequestParam("selectedSegScore") String selectedSegScore,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("accType") String accType,
			HttpServletRequest request) throws DashboardException, Exception {
		try {
			String selectedQualScoreArr[] = null;
			String selectedSegScoreArr[] = null;
			//selSubPlays=URLDecoder.decode(selSubPlays);
			JQueryDataTableInputDTO dataTableInput = null;
			String iDisplayStart = request.getParameter("iDisplayStart");
			String iDisplayLength = request.getParameter("iDisplayLength");
			String sSearch = request.getParameter("sSearch");
			String iSortingCols = request.getParameter("iSortingCols");
			String iSortCol_0 = request.getParameter("iSortCol_0");
			String sSortDir_0 = request.getParameter("sSortDir_0");
			String sEcho = request.getParameter("sEcho");
			if (sEcho != null && !sEcho.equals("")) {
				dataTableInput = new JQueryDataTableInputDTO();
				dataTableInput.setiDisplayLength(Integer
						.valueOf(iDisplayLength));
				dataTableInput.setiDisplayStart(Integer.valueOf(iDisplayStart));
				if (iSortCol_0 != null) {
					dataTableInput.setiSortCol_0(CustProfileVO
							.getCustDataMapLead(iSortCol_0));
				}
				dataTableInput.setsSortDir_0(sSortDir_0);
				dataTableInput.setsSearch(sSearch);
				if (iSortingCols != null && !iSortingCols.equals("")) {
					dataTableInput.setiSortingCols(Integer
							.valueOf(iSortingCols));
				}
			}

			String selectedSubplayArr[] = null;
			String tempSelectedSubplayArr[] = null;
			JQueryDataTableOutputDTO returnList = new JQueryDataTableOutputDTO();

			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();
			if (selSubPlays.indexOf("#") != -1) {
				selectedSubplayArr = selSubPlays.split("#");
			} else if (null != selSubPlays && !(selSubPlays.equals(""))
					&& !(selSubPlays.equals("All"))) {
				selectedSubplayArr = new String[] { selSubPlays };
			}
			if (!(selectedQualScore.equals("")) && null != selectedQualScore && !(selectedQualScore.equals("ALL")) &&  selectedQualScore.indexOf("#") != -1) {
				selectedQualScoreArr = selectedQualScore.split("#");
			} else if (!(selectedQualScore.equals("")) && null != selectedQualScore && !(selectedQualScore.equals("ALL")) )
			{
				selectedQualScoreArr = new String[] { selectedQualScore };
			}
			if (!(selectedSegScore.equals("")) && null != selectedSegScore && !(selectedSegScore.equals("ALL")) &&  selectedSegScore.indexOf("#") != -1) {
				selectedSegScoreArr = selectedSegScore.split("#");
			} else if (!(selectedSegScore.equals("")) && null != selectedSegScore && !(selectedSegScore.equals("ALL")) )
			{
				selectedSegScoreArr = new String[] { selectedSegScore };
			}
			if (null != selectedSubplayArr && selectedSubplayArr.length > 0) {
				tempSelectedSubplayArr = new String[selectedSubplayArr.length];
				for (int count = 0; count < selectedSubplayArr.length; count++) {
					tempSelectedSubplayArr[count] = selectedSubplayArr[count];
				}
			}
			if (null != selectedSubplayArr && selectedSubplayArr.length > 0) {
				for (int count = 0; count < selectedSubplayArr.length; count++) {
					selectedSubplayArr[count] = "'" + selectedSubplayArr[count]
							+ "'";
					if (selectedSubplayArr[count].contains("FORWARD_SLASH")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("FORWARD_SLASH", "/");

					}
					if (selectedSubplayArr[count].contains("AC")
							|| selectedSubplayArr[count].contains("AB")) {
						if (selectedSubplayArr[count].contains("AC")) {
							selectedSubplayArr[count] = (selectedSubplayArr[count])
									.replaceAll("AC", "Account");
						} else if (selectedSubplayArr[count].contains("AB")) {
							selectedSubplayArr[count] = (selectedSubplayArr[count])
									.replaceAll("AB", "account");
						}

					}
					if (selectedSubplayArr[count].contains("_AND_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_AND_", "&");

					}
					if (selectedSubplayArr[count].contains("_DOT_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_DOT_", ".");

					}
					if (selectedSubplayArr[count].contains("_GREATER_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_GREATER_", ">");

					}
					if (selectedSubplayArr[count].contains("_LESS_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_LESS_", "<");

					}
					if (selectedSubplayArr[count].contains("_SEMI_")) {
						selectedSubplayArr[count] = (selectedSubplayArr[count])
								.replaceAll("_SEMI_", ";");

					}
				}

			}

			if (null != accId) {
				CustomerListDTO custData = contractService.getAllSamCustomerInfo(
						accId, playSection, selectedSubplayArr, dataTableInput,selectedQualScoreArr,selectedSegScoreArr);
				List<CustProfileVO> listOfCustProfileVO = custData
						.getCustomerVoList();
				if (null != listOfCustProfileVO) {
					// request.setAttribute("subPlayParams",
					// selectedSubplayArr);
					/*
					 * List<NotificationInfoVo> notiInfoVOList =
					 * (List<NotificationInfoVo>) contractDao
					 * .getNotiInfo(custNum,segType);
					 */
					if (null != tempSelectedSubplayArr) {
						/*
						 * for(int
						 * count=0;count<tempSelectedSubplayArr.length;count++){
						 * tempSelectedSubplayArr
						 * [count]=(tempSelectedSubplayArr[count]).substring(1,
						 * (tempSelectedSubplayArr[count]).lastIndexOf("'")); }
						 */
						for (int count = 0; count < tempSelectedSubplayArr.length; count++) {
							// tempSelectedSubplayArr[count]="'"+tempSelectedSubplayArr[count]+"'";
							if (tempSelectedSubplayArr[count].contains("AC")
									|| tempSelectedSubplayArr[count]
											.contains("AB")) {
								if (tempSelectedSubplayArr[count]
										.contains("AC")) {
									tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
											.replaceAll("AC", "Account");
								} else if (tempSelectedSubplayArr[count]
										.contains("AB")) {
									tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
											.replaceAll("AB", "account");
								}

							}
							if (tempSelectedSubplayArr[count].contains("%")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("%", "PERCENT");

							}
							if (tempSelectedSubplayArr[count].contains("&")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("&", "_AND_");

							}
							if (tempSelectedSubplayArr[count].contains(".")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(".", "_DOT_");

							}
							if (tempSelectedSubplayArr[count].contains(">")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(">", "_GREATER_");

							}
							if (tempSelectedSubplayArr[count].contains("<")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll("<", "_LESS_");

							}
							if (tempSelectedSubplayArr[count].contains(";")) {
								tempSelectedSubplayArr[count] = (tempSelectedSubplayArr[count])
										.replaceAll(";", "_SEMI_");

							}
						}
						String unSelSubPlays = StringUtils
								.arrayToDelimitedString(tempSelectedSubplayArr,
										"~");
						customerDataVo.setUnSelListOfSubPlay(unSelSubPlays);
						returnList.setUnSelListOfSubPlay(unSelSubPlays);
					}
					customerDataVo.setListOfCustProfileVO(listOfCustProfileVO);
					returnList.setAaData(listOfCustProfileVO);
					returnList.setiTotalRecords(custData.getTotalCount());
					returnList.setiTotalDisplayRecords(custData
							.getTotalCount());

					if (sEcho != null && !sEcho.equals("")) {
						returnList.setsEcho(Integer.valueOf(sEcho));
					}

					gson.toJson(customerDataVo);

				}
			}
			/*LayoutVo layoutVo=null;
			if(null !=repRoleCode && !("".equals(repRoleCode)) && (repRoleCode.equals("AM")))
			layoutVo=contractService.getLayoutDetails(repRoleCode,accType);
			else if(null !=repRoleCode && !("".equals(repRoleCode)) )
			layoutVo=contractService.getLayoutDetails(repRoleCode,null);
			returnList.setLayoutVo(layoutVo);*/
			request.setAttribute(Constants.SELECTEDQUALSCORE, selectedQualScore);
			request.setAttribute(Constants.SELECTEDSEGSCORE, selectedSegScore);
			//selectedQualScore,
			//@RequestParam("selectedSegScore") String selectedSegScore
			return gson.toJson(returnList);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}
	/**
	 * Method implementation to fetch customer right.
	 * 
	 * @param String
	 *            cusnum
	 * @return String
	 */
	@RequestMapping(value = "/getCustomerRight/{cusNo}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getCustomerRight(@PathVariable("cusNo") String cusnum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();

			CustomerDataVo customerDataVo = contractService
					.getCustomerValue(cusnum);

			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch ship to details.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getShipToData/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String openShipToDetails(@PathVariable("cid") String custNum, @RequestParam("location") String location)
			throws DashboardException, Exception {
		try {
			Gson gson = new Gson();
			List<ShipToVO> shipToVOList = new ArrayList<ShipToVO>();

			if (null != custNum) {

				shipToVOList = contractService.viewShipToDetails(custNum,location);

				gson.toJson(shipToVOList);

			}
			return gson.toJson(shipToVOList);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch latest fiscal date.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getLatestFiscalDate/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLatestFiscalDate(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {
		getLatestFiscalContactedDate();
		try {
			Gson gson = new Gson();
			String latestFiscalDate = null;

			if (null != custNum) {

				latestFiscalDate = contractService.getLatestFiscalDate(custNum);

			}
			return gson.toJson(latestFiscalDate);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch latest fiscal date.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getLatestFiscalDateOrder/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLatestFiscalDate1(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			String latestFiscalDate = null;

			if (null != custNum) {

				latestFiscalDate = contractService
						.getLatestFiscalDate1(custNum);

			}
			return gson.toJson(latestFiscalDate);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch latest fiscal date.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getLatestFiscalDateForLead/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLatestFiscalDateForLead(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			String latestFiscalDate = null;

			if (null != custNum) {

				latestFiscalDate = contractService.getLatestFiscalDateForLead(custNum);

			}
			return gson.toJson(latestFiscalDate);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * Method implementation to fetch latest fiscal date.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getLatestOrderReturnedDate/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLatestOrderReturnDate(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			String latestOrderRetDate = null;

			if (null != custNum) {

				latestOrderRetDate = contractService
						.getLatestOrderReturnDate(custNum);

			}
			return gson.toJson(latestOrderRetDate);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * Method implementation to fetch customer number.
	 * 
	 * @param String
	 *            searchText
	 * @param String
	 *            accId
	 * @return String
	 */
	@RequestMapping(value = "/getCustomerNumber", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getCustomerNumber(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String acctId = request.getParameter("acctId");
			String custNum = request.getParameter("custNum");
			String searchText = request.getParameter("searchText");
			String assignGroup = request.getParameter("assignGroup");
			Gson gson = new Gson();
			AccountUserDTO user = userRoleService.getUserDetail(acctId);
			String custNumber = contractService.getCustomerNumber(searchText, acctId,
					custNum, user.getLevel());

			return gson.toJson(custNumber);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch bought recommendation.
	 * 
	 * @param String
	 *            cuatNum
	 * @param String
	 *            orderContact
	 * @return String
	 */
	@RequestMapping(value = "/getBoughtRecommendation/{custNum}/{loggedUser}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getBoughtRecommendation(
			@PathVariable("custNum") String custNum,
			@PathVariable("loggedUser") String loggedUser,
			@RequestParam("orderContact") String orderContact)
					throws DashboardException, Exception {
		List<BoughtAlsoBoughtInfoVO> recommendedVO = null;
		Gson gson = new Gson();

		try {

			if (custNum != null && !custNum.equals("") && orderContact != null
					&& !orderContact.equals("")) {

				recommendedVO = contractService
						.getBoughtAlsoBoughtRecommendations(custNum,
								orderContact,loggedUser);
				//recommendedVO = contractService
						//.mapBABInfoAndRecommendedVo(recommendedVO);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getBoughtRecommendation() ");
		}

		return gson.toJson(recommendedVO);
	}
	@RequestMapping(value = "/getFilterData", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getFilterData(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			Gson gson = new Gson();
			Map<String, List<Object>> filterData = contractService
					.getFilterData();

			return gson.toJson(filterData);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * Method implementation to fetch view not bought recommendation.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getViewButNotBoughtRecom/{custNum}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getViewButNotBoughtRecom(
			@PathVariable("custNum") String custNum) throws DashboardException,
			Exception {
		List<RecommendationViewNotBoughtVO> recommendedVO = null;
		Gson gson = new Gson();

		try {

			if (custNum != null && custNum != "" && !custNum.equals("")) {

				recommendedVO = contractService
						.getViewNotBoughtRecommendation(custNum);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getViewButNotBoughtRecom() ");
		}

		return gson.toJson(recommendedVO);
	}

	/**
	 * Method implementation to fetch reorder recommendation.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getReorderRecommendations/customer/{custNum}/user/{loggedUser}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getReorderRecom(@PathVariable("custNum") String custNum,@PathVariable("loggedUser") String loggerUser)
			throws DashboardException, Exception {
		List<ReorderRecommendationVO> recommendedVO = null;
		Gson gson = new Gson();

		try {

			if (custNum != null && custNum != "" && !custNum.equals("")) {

				recommendedVO = contractService
						.getReorderRecommendations(custNum,loggerUser);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getReorderRecom() ");
		}

		return gson.toJson(recommendedVO);
	}
	@RequestMapping(value = "/getOnlineRetailReorderRecom/{custNum}/user/{loggedUser}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getOnlineRetailReorderRecom(@PathVariable("custNum") String custNum, @PathVariable("loggedUser") String loggerUser)
			throws DashboardException, Exception {
		List<OnlineRetailReorderRecommendationVO> recommendedVO = null;
		Gson gson = new Gson();
		try {

			if (custNum != null && custNum != "" && !custNum.equals("")) {
				recommendedVO = contractService
						.getOnlineRetailReorderRecommendations(custNum, loggerUser);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getReorderRecom() ");
		}

		return gson.toJson(recommendedVO);
	}


	/**
	 * Method implementation to fetch order contacts.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getRecommOrderContacts/{custNum}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getOrderContacts(@PathVariable("custNum") String custNum)
			throws DashboardException, Exception {

		List<RecommOrderContactVO> recommendedVO = null;
		Set<String> contactSet = new LinkedHashSet<String>();
		Gson gson = new Gson();

		try {

			if (custNum != null && custNum != "" && !custNum.equals("")) {

				recommendedVO = contractService.getOrderContacts(custNum);
			}

			if (!recommendedVO.isEmpty()) {
				for (RecommOrderContactVO vo : recommendedVO) {
					contactSet.add(vo.getOrderContact());
				}

			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getRecommOrderContacts() ");
		}

		return gson.toJson(contactSet);
	}

	/**
	 * Method implementation to fetch class recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getClassRecommendations/{custNum}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getClassRecommendations(
			@PathVariable("custNum") String custNum) throws DashboardException,
			Exception {
		List<CustRecommendationVO> recommendedVO = null;
		Gson gson = new Gson();

		try {

			if (custNum != null && custNum != "" && !custNum.equals("")) {

				recommendedVO = contractService
						.getClassRecommendations(custNum);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getClassRecommendations() ");
		}

		return gson.toJson(recommendedVO);
	}

	/**
	 * Method implementation to fetch account number.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@RequestMapping(value = "/getAccountNumber", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getAccountNumber(@RequestParam(Constants.EMAIL) String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			String accountNumber = null;

			if (null != custNum) {

				accountNumber = contractService.getAccountNumber(custNum);

			}
			return gson.toJson(accountNumber);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch sub play list.
	 * 
	 * @param String
	 *            forPlayType
	 * @param String
	 *            accId
	 * @return String
	 */
	@RequestMapping(value = "/getSubPlayList/{forPlayType}/{accId}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getSubPlayList(
			@PathVariable("forPlayType") String forPlayType,
			@PathVariable("accId") String accId) throws DashboardException,
			Exception {

		try {
			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();

			if (null != forPlayType) {
				List<SubplayInfoVo> listOfSubPlay = contractService
						.getSubPlayList(forPlayType, accId);
				if (null != listOfSubPlay) {
					/*
					 * List<NotificationInfoVo> notiInfoVOList =
					 * (List<NotificationInfoVo>) contractDao
					 * .getNotiInfo(custNum,segType);
					 */
					customerDataVo.setListOfSubPlay(listOfSubPlay);
					gson.toJson(customerDataVo);

				}
			}
			Map<String, List<Object>> filterData = contractService
					.getFilterData();
			customerDataVo.setSegAndQualSubPlays(filterData);
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method implementation to show access denied page.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

	/**
	 * Method implementation to open maintain user role page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return ModelAndView
	 */
	@RequestMapping("/maintainUserRole")
	public ModelAndView viewHeliosCouponJsp(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();

			User user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			request.setAttribute(Constants.REQREPID, user.getUsername());

			if (role.equalsIgnoreCase("[ADMIN]")) {
				request.setAttribute(Constants.REQREPROLECODE, Constants.ADMIN);
			} else {
				String repRoleCd = contractService.getAgentRepRoleCd(user
						.getUsername());
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(request
						.getParameter(Constants.ACCOUNTID));
				request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);
			}

			return new ModelAndView("maintainUserRole");
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to open maintain user role page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return ModelAndView
	 */
	
	@RequestMapping(value = { "/maintainCta" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String viewMaintainCta(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String role = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities().toString();

			User user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			request.setAttribute(Constants.REQREPID, user.getUsername());

			if (role.equalsIgnoreCase("[ADMIN]")) {
				request.setAttribute(Constants.REQREPROLECODE, Constants.ADMIN);
			} else {
				String repRoleCd = contractService.getAgentRepRoleCd(user
						.getUsername());
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(request
						.getParameter(Constants.ACCOUNTID));
				request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);
			}

			return "maintainCta";
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to fetch Last Refresh Time of Process.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = "/getLastRefreshTime/{processName}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLastRefreshTime(
			@PathVariable("processName") String processName)
					throws DashboardException, Exception {
		Gson gson = new Gson();
		String LastRefreshTime = "";

		try {

			if (processName != null && processName != ""
					&& !processName.equals("")) {

				LastRefreshTime = contractService
						.getLastRefreshTime(processName);
			}

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getLastRefreshTime() ");
		}

		return gson.toJson(LastRefreshTime);
	}

	/**
	 * Method implementation to fetch Last Refresh Time of Process.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = "/getCurrentRefreshTime", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getCurrentRefreshTime() throws DashboardException, Exception {
		Gson gson = new Gson();
		String currentRefreshTime = "";

		try {
			currentRefreshTime = contractService.getCurrentRefreshTime();
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getCurrentRefreshTime() ");
		}
		return gson.toJson(currentRefreshTime);
	}

	@RequestMapping(value = "/getOrderDetailsShipTO/{custNum}/{orderNum}/{orderDate}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String OrderDetailsShipTo(@PathVariable("custNum") String custNum,
			@PathVariable("orderNum") String orderNum,
			@PathVariable("orderDate") String orderDate)
					throws DashboardException, Exception {
		Gson gson = new Gson();
		List<PurchaseHeliosDetailsVO> purchaseShipTo = new ArrayList<PurchaseHeliosDetailsVO>();
		try {

			purchaseShipTo = contractService.getOrderDetailsShipTo(custNum,
					orderNum, orderDate);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		} finally {
			LOGGER.info("Exiting controller method getLastRefreshTime() ");
		}

		return gson.toJson(purchaseShipTo);
	}

	/**
	 * Method implementation to open maintain user role page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return ModelAndView
	 */

	@RequestMapping(value = { "/getUserHiererchy" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getHeliosUserHiererchy(HttpServletRequest request)
			throws DashboardException, Exception {
		List<AccountUserDTO> hierechies = new ArrayList<AccountUserDTO>();
		try {
			String accountNumber = request.getParameter("accID");
			String LoggedInUserID = request.getParameter("LoggedInUserID");
			AccountUserDTO user=null;
			if (accountNumber == null)
				return new ModelAndView(Constants.LOGIN);
			else
				accountNumber = accountNumber.trim();
			if (null== accountNumber || (accountNumber.equals("null"))){
				user = userRoleService.getUserDetail(LoggedInUserID);
				hierechies = userRoleService.getUserHiererchy(LoggedInUserID,
						user.getLevel());
			}else{
				user = userRoleService.getUserDetail(accountNumber);
				hierechies = userRoleService.getUserHiererchy(accountNumber,
						user.getLevel());
			}

			ModelAndView modelView = new ModelAndView("hiererchy");
			modelView.addObject("lists", hierechies);
			if (user.getLevel() != null && !"".equals(user.getLevel())
					&& Integer.parseInt(user.getLevel()) < 4) {
				Set<String> roles = new TreeSet<String>();
				for (AccountUserDTO acUser : hierechies) {
					roles.add(acUser.getLevel() + "-" + acUser.getDesignation());
				}
				modelView.addObject("roles", roles);
				Map<String, Set<String>> repRoles = new TreeMap<String, Set<String>>();
				for (AccountUserDTO acUser : hierechies) {
					if (acUser.getLevel().equals("5")) {
						if (repRoles.get(acUser.getLevel()) == null) {
							Set<String> repRolesSet = new TreeSet<String>();
							repRolesSet.add(acUser.getRepRoleCd());
							repRoles.put(acUser.getLevel(), repRolesSet);
						} else {
							Set<String> repRolesSet = repRoles.get(acUser
									.getLevel());
							repRolesSet.add(acUser.getRepRoleCd());
							repRoles.put(acUser.getLevel(), repRolesSet);
						}
					}
				}
				modelView.addObject("repRoles", repRoles);
			}
			modelView.addObject("LoggedInUserID", LoggedInUserID);
			request.setAttribute("accID", accountNumber);
			String repRoleCd = contractService
					.getAgentRepRoleCd(LoggedInUserID);

			request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
			String agentName = contractService.getAgentName(LoggedInUserID);
			request.setAttribute(Constants.LOGGED_IN_USER_NAME, agentName);
			request.setAttribute("assignType", request.getParameter("assignType"));
			// UserRoleDTO
			return modelView;
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method implementation to open recommendations page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/storeLocAction" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openStoreLocator(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String contactName = request.getParameter("contactName");
			String contactDate = request.getParameter("contactDate");
			String contactStatus = request.getParameter("contactStatus");
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");

			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);
			request.setAttribute("contactName", contactName);
			request.setAttribute("contactDate", contactDate);
			request.setAttribute("contactStatus", contactStatus);
			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(loggedInUserId);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				request.setAttribute("onlineRetail",onlineRetail);
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				request.setAttribute(Constants.CTA_DISPOSITION_DISABLED_IDS, CtaDisableId);
				String alertState=request.getParameter("alertState");
				if(null==alertState || (alertState.equals("")))
					request.setAttribute("alertState", "OFF");
				else
					request.setAttribute("alertState",alertState);
				return Constants.STORElOCATOR;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = { "/findStoresNearBy" }, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String findStoresNearBy(HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String customerNumber = request.getParameter("customerNumber");
			String zipCode = null;
			String address = request.getParameter("address");

			zipCode = request.getParameter("zipCode");

			if (customerNumber != null && !customerNumber.isEmpty()) {
				// get master account zip code
				zipCode = contractService
						.getMasterAccountZipCode(customerNumber);

				if (zipCode == null || zipCode.isEmpty()) {
					return "{\"results\":{\"stores\":[]}}";
				}
			}
			String serviceURL = storeLocatorServiceURL;
			if (zipCode != null) {
				if (zipCode.length() > 5)
					zipCode = zipCode.substring(0, 5);
				serviceURL += ("&address=" + URLEncoder
						.encode(zipCode, "UTF-8"));
			} else {
				// advanced filter criteria code
				serviceURL += ("&address=" + URLEncoder
						.encode(address, "UTF-8"));
			}

			return getHTTPResponse(serviceURL);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	private String getHTTPResponse(String serviceURL) throws DashboardException {
		try {
			final String USER_AGENT = "Mozilla/5.0";

			URL obj = new URL(serviceURL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			LOGGER.info("Sending 'GET' request to URL : " + serviceURL);
			LOGGER.info("Response Code : " + responseCode);

			if (responseCode != 200) { // Not OK response
				throw new DashboardException(
						"Store Locator Service returned response code ="
								+ responseCode);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}
	@ResponseBody 
	@RequestMapping( method={RequestMethod.POST,RequestMethod.GET}, value={"/getSbaDiffDetails/{custId}"}, produces="application/json")
	public String getSbaDiffDetails(@PathVariable("custId") String custId) throws DashboardException, Exception{
		SbaDiffDetailsVo sbaDiffDetailsVo =contractService.getSbaDiffDetails(custId);
		Gson gson=new Gson();
		return gson.toJson(sbaDiffDetailsVo);
	}


	/*@ResponseBody 
	@RequestMapping( method=RequestMethod.POST, value={"/getLayoutDetails/{roleCode}/{accType}"}, produces="application/json")
	public String getLayoutDetails(@PathVariable("roleCode") String roleCode,@PathVariable("accType") String accType) throws DashboardException, Exception{
		LayoutVo layoutVo =contractService.getLayoutDetails(roleCode, accType);
		Gson gson=new Gson();
		return gson.toJson(layoutVo);
	}*/

	@ResponseBody 
	@RequestMapping( method={RequestMethod.POST,RequestMethod.GET}, value={"/getSavingsInfo/{custId}"}, produces="application/json")
	public String getSavingsInfo(@PathVariable("custId") String custId) throws DashboardException, Exception{
		try{
			SavingsVo savingsVo =contractService.getSavingsInfo(custId);
			Gson gson=new Gson();
			return gson.toJson(savingsVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	@ResponseBody 
	@RequestMapping( method=RequestMethod.POST, value={"/getCatPenInfo/{custId}"}, produces="application/json")
	public String getCatPenInfo(@PathVariable("custId") String custId) throws DashboardException, Exception{
		try{
			List<Object> catpenVoList =contractService.getCatPenInfo(custId);
			Gson gson=new Gson();
			return gson.toJson(catpenVoList);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw new DashboardException(e);
		}
	}

	/**
	 * Method implementation to open recommendations page.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = { "/recommActionSam" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String openRecommendationsSam(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);
			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(loggedInUserId);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
				request.setAttribute("onlineRetail", onlineRetail);
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return Constants.ONLINE_RETAIL_RECOMMENDATION;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = { "/storeLocActionSam" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openStoreLocatorSam(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");

			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);
			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
					else{
						AccountUserDTO callOrderScreenUser = userRoleService
								.getUserDetail(loggedInUserId);
						if(null !=callOrderScreenUser)
							request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
					}
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				request.setAttribute("onlineRetail",onlineRetail);
				String mktResourceUrl = sfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return Constants.STORElOCATORSAM;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = { "/storeLocActionSamNew" }, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String openStoreLocatorSamNew(HttpServletRequest request)
			throws DashboardException, Exception {
		try {

			String custid = request
					.getParameter(Constants.REQUESTCUSTOMERNUMBER);
			String accid = request.getParameter(Constants.ACCOUNTID);
			String filterBy = request.getParameter(Constants.FILTERBY);
			String subPlays = request.getParameter("subPlayParams");
			
			String sliderSubPlaysItem = request
					.getParameter("sliderSubPlaysItem");
			String onlineRetail = request
					.getParameter("onlineRetail");
			request.setAttribute(Constants.FROM, Constants.HOME);

			request.setAttribute(Constants.REQREPID, accid);
			String iStart = request.getParameter(Constants.ISTART);
			String loggedInUserId = null;
			if (request.getParameter(Constants.LOGGEDINUSERID) == null
					|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
				loggedInUserId = accid;
			} else
				loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
			if (custid == null) {
				String role = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				if (role.equalsIgnoreCase("[ADMIN]")) {
					request.setAttribute(Constants.REQREPROLECODE,
							Constants.ADMIN);
				} else {
					String repRoleCd = contractService.getAgentRepRoleCd(accid);
					request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
					String agentName = contractService
							.getAgentName(loggedInUserId);
					if(null != agentName && !("".equals(agentName)))
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								agentName);
		                else{
		                	AccountUserDTO callOrderScreenUser = userRoleService
		    						.getUserDetail(loggedInUserId);
		                	if(null !=callOrderScreenUser)
		                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
									callOrderScreenUser.getFullname());
		                }
				}

				// showing login page
				return Constants.LOGIN;

			} else {
				request.setAttribute(Constants.CUSTOMERNUMBER, custid);
				request.setAttribute(Constants.ACCOUNTID, accid);
				request.setAttribute(Constants.FILTERBY, filterBy);
				request.setAttribute("subPlayParams", subPlays);
				if (null != sliderSubPlaysItem) {
					request.setAttribute("sliderSubPlaysItem",
							sliderSubPlaysItem);
				}
				request.setAttribute(Constants.ISTART, iStart);

				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
	                else{
	                	AccountUserDTO callOrderScreenUser = userRoleService
	    						.getUserDetail(loggedInUserId);
	                	if(null !=callOrderScreenUser)
	                	request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
	                }
				request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
				request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
				request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
				request.setAttribute("onlineRetail",onlineRetail);
				String mktResourceUrl = samSfdcAppUrl;
				request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
				return Constants.STORElOCATORSAMNEW;

			}
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = "/getOrderDetailsHighLevelDataSAM/{cid}", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String openOrderDetailsSAM(@PathVariable("cid") String custNum)
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			CustomerDataVo customerDataVo = new CustomerDataVo();
			String latestFiscalDate = null;

			if (null != custNum) {
				PurchaseDetailsSelectedVO objPurchDtlsSelectedVO = new PurchaseDetailsSelectedVO();
				/*objPurchDtlsSelectedVO.setSelectedCategory(selCatId);
				objPurchDtlsSelectedVO.setSelectedMonthYear(selMonthYear);
				if (OnChange.equalsIgnoreCase(Constants.NOCHANGE)) {
					latestFiscalDate = contractService
							.getLatestFiscalDate1(custNum);
				}
				if (latestFiscalDate != null) {
					objPurchDtlsSelectedVO
							.setSelectedMonthYear(latestFiscalDate);
				}*/
				objPurchDtlsSelectedVO.setSelectedSubAcctMbr(custNum);

				contractService.viewOrderDetailsSAM(objPurchDtlsSelectedVO,custNum,customerDataVo);

				gson.toJson(customerDataVo);

			}
			return gson.toJson(customerDataVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value = "/getLatestFiscalContactedDate", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public String getLatestFiscalContactedDate()
			throws DashboardException, Exception {

		try {
			Gson gson = new Gson();
			ContactDateVO ContactDateVo = null;
			ContactDateVo=contractService.getLatestFiscalContactedDate();
			return gson.toJson(ContactDateVo);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}
	@RequestMapping(value = { "/openSubCallToActionConfig" }, method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json")
	@ResponseBody
	public String openSubCallToActionConfig(@RequestParam("SubSegDesc") String SubSegDesc,
			@RequestParam("CtaName") String CtaName,
			@RequestParam("SegId") String SegId
			)
					throws DashboardException, Exception {
		try {
			Gson gson = new Gson();
			SubCallToActionDataVo subCallToActionDataVo = new SubCallToActionDataVo();
			SegHdrText segHdrText= contractService
					.getAdminSubCtaDeatils(CtaName,SegId);
			return gson.toJson(segHdrText);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
	}

	@RequestMapping(value ="/updateCtaContent", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String logUserActivity(@RequestParam("segId") String segId,
			@RequestParam("subSegId") String subSegId,
			@RequestParam("headerIds") String headerIds,
			@RequestParam("headerCol") String headerCol,
			@RequestParam("headerContent") String headerContent)

					throws DashboardException, Exception {
		String updateStatus = null;
		try {
			 /* FOR CTA*/
			updateStatus= contractService.updateSubCtaDeatils(segId,subSegId, headerIds, headerContent, headerCol);
			
			/*FOR ALERT*/
			//updateStatus= contractService.updateSubCtaDeatils(null,null, null, headerContent, null);
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw e;
		} finally {
			LOGGER.trace("Exiting method :--> logUserActivity()");
		}
		return updateStatus;
	}

	@RequestMapping(value ="/addSegmentData", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String addSegmentDetail(@RequestParam("subSegDesc") String subSegDesc,
			@RequestParam("ctaName") String ctaName,
			@RequestParam("subSegId") String subSegId,
			@RequestParam("div") String div,
			@RequestParam("custNum") String custNum,
			@RequestParam("user") String user,
			@RequestParam("comment") String comment,
			@RequestParam("dispStatus") String dispStatus,
			@RequestParam("linkId") String linkId,
			@RequestParam("date") String date,
			@RequestParam("fyrFprdFwkFdy") String fyrFprdFwkFdy,
			@RequestParam("contactId") String contactId,
			@RequestParam("sfdcContactFullName") String sfdcContactFullName,
			@RequestParam("freq") String frequency,
			@RequestParam("userId") String userId,
			@RequestParam("subjectTxt") String subject){

		try{
			LOGGER.info("Entered on addSegmentDetail() ");
			if(StringUtils.isEmpty(custNum) || StringUtils.isEmpty(div) || StringUtils.isEmpty(ctaName)
					|| StringUtils.isEmpty(subSegId) || StringUtils.isEmpty(fyrFprdFwkFdy)){
				throw new DashboardException(CTAConstants.REQUIRED_VAL_IS_NULL_ERROR_MSG);
			}
			String  ldapUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal()).getUsername();
			
			String ldapUserName=" ";
		
			AccountUserDTO loggedInUser = userRoleService
					.getUserDetail(ldapUserId);
			
			String ldapUserRole = contractService.getAgentRepRoleCd(ldapUserId);
			//List<String>  ldapUserList=contractService.getLdapLoggedUserName(ldapUserId);
			
			//String ldapUserRole=" ";
			if(null!=loggedInUser  && loggedInUser.getFullname()!=null )
			{
				ldapUserName=loggedInUser.getFullname();
				//ldapUserRole=loggedInUser.getRepRoleCd();
				
			}
			else 
			{
				 ldapUserName = contractService.getAgentName(ldapUserId);
				
			}
			
			Timestamp timestamp = CTAUtil.getCurrentTimeStamp();
			SegmentDetailWrapper wrapper = new SegmentDetailWrapper();
			SegmentDetail segmentDetail = CTAUtil.populateSegmentDetailBean(linkId,custNum,div,ctaName,subSegId,fyrFprdFwkFdy,dispStatus,ldapUserName,timestamp,contactId,sfdcContactFullName,frequency,subject);
			SegmentComments segmentComment = CTAUtil.populateSegmentCommentBean(comment,ldapUserName,timestamp);
			wrapper.setComment(segmentComment);
			wrapper.setSegDetail(segmentDetail);
			InputTaskSalesforceDTO sfdcDTO= new InputTaskSalesforceDTO();
			sfdcDTO.setCustomerNumber(custNum);
			sfdcDTO.setDescription(comment);
			sfdcDTO.setContactId(contactId);
			sfdcDTO.setSubject(subject);
			sfdcDTO.setStatus(dispStatus);
			sfdcDTO.setRepId(ldapUserId);
			sfdcDTO.setLdapUserRole(ldapUserRole == null ? " ":ldapUserRole);
			OutputTasksfdc resultOut=contractService.saveTaskToSfdc(sfdcDTO);
			String status;
			
			segmentDetail.setRepSFDCId(resultOut.getRepSFDCId());
			if(resultOut!=null && resultOut.getStatus().equals("SUCCESS")){
			segmentDetail.setSfdcTaskId(resultOut.getTaskId());
			segmentDetail.setSfdcSentDate(timestamp);
			
			wrapper.setComment(segmentComment);
			wrapper.setSegDetail(segmentDetail);
			status = ctaService.addSegmentDetail(wrapper);
			
			}else{
			wrapper.setComment(segmentComment);
			wrapper.setSegDetail(segmentDetail);	
			status = ctaService.addSegmentDetail(wrapper);
				
			}
				return status;
		}catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}finally{
			LOGGER.info("Exiting on addSegmentDetail() ");
		}

	}	
	
	@RequestMapping(value ="/getCustSfdcInfo/", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getCustomerSfdcInfo(
			@RequestParam("custId") String custNum){
		try{
			Gson gson = new Gson();
			List<CustSfdcInfoVO> infoVOs = ctaService.getCustSfdcInfo(custNum);
			return gson.toJson(infoVOs);
		}catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}

	}
	
	@RequestMapping(value = { "/getDespositionDetails/" }, method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json")
	@ResponseBody
	public String getDespositionDetails(@RequestParam("taskCombinationId") String taskCombinationId,
			@RequestParam("freq") String frequency)
			throws DashboardException, Exception {
		List<DispositionDetailsVo> dispositionDetailsVoList=null;
		Gson gson = new Gson();
		try {
			LOGGER.info("Entered on getDespositionDetails() ");
           dispositionDetailsVoList= contractService
					.getDespositionDetails(taskCombinationId,frequency);
			
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}finally{
			LOGGER.info("Exiting from getDespositionDetails() ");
		}
		return gson.toJson(dispositionDetailsVoList);
	}	

	public List<SegMktgResources> getSegMktgResources(String segName, String subSegId) throws DashboardException, Exception{
		List<SegMktgResources> mktgResources = null;		
		try {
			String mktResourceUrl = sfdcAppUrl;
			mktgResources= contractService.getSegMktgResources(segName,subSegId,mktResourceUrl);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
		return mktgResources;
	}
	
public List<SegmentSubDetail> getSegSubDetail(String segId,
		String subSegId,
		String custNum)
throws DashboardException, Exception {
List<SegmentSubDetail> subDetails=null;
Gson gson = new Gson();
try {
subDetails= contractService.getSegSubDetail(custNum,segId,subSegId);
} catch (DashboardException de) {
throw de;
} catch (Exception e) {
throw e;
}
return subDetails;
}	
	
	
	


@RequestMapping(value = { "/perfDashboardAction" }, method = {
		RequestMethod.GET, RequestMethod.POST })
public String openPerfDashboard(HttpServletRequest request)
		throws DashboardException, Exception {
	try {

		String custid = request
				.getParameter(Constants.REQUESTCUSTOMERNUMBER);
		String accid = request.getParameter(Constants.ACCOUNTID);
		String filterBy = request.getParameter(Constants.FILTERBY);
		String subPlays = request.getParameter("subPlayParams");

		String sliderSubPlaysItem = request
				.getParameter("sliderSubPlaysItem");
		/*String onlineRetail = request
				.getParameter("onlineRetail");*/
		request.setAttribute(Constants.FROM, Constants.HOME);

		request.setAttribute(Constants.REQREPID, accid);
		String iStart = request.getParameter(Constants.ISTART);
		String loggedInUserId = null;
		if (request.getParameter(Constants.LOGGEDINUSERID) == null
				|| "".equals(request.getParameter(Constants.LOGGEDINUSERID))) {
			loggedInUserId = accid;
		} else
			loggedInUserId = request.getParameter(Constants.LOGGEDINUSERID);
		if (custid == null) {
			String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			if (role.equalsIgnoreCase("[ADMIN]")) {
				request.setAttribute(Constants.REQREPROLECODE, Constants.ADMIN);
			} else {
				String repRoleCd = contractService.getAgentRepRoleCd(accid);
				request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
				String agentName = contractService
						.getAgentName(loggedInUserId);
				if(null != agentName && !("".equals(agentName)))
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							agentName);
				else{
					AccountUserDTO callOrderScreenUser = userRoleService
							.getUserDetail(loggedInUserId);
					if(null !=callOrderScreenUser)
						request.setAttribute(Constants.LOGGED_IN_USER_NAME,
								callOrderScreenUser.getFullname());
				}
			}

			// showing login page
			return Constants.LOGIN;

		} else {
			request.setAttribute(Constants.CUSTOMERNUMBER, custid);
			request.setAttribute(Constants.ACCOUNTID, accid);
			request.setAttribute(Constants.FILTERBY, filterBy);
			request.setAttribute("subPlayParams", subPlays);
			request.setAttribute(Constants.SELECTEDQUALSCORE, request.getParameter(Constants.SELECTEDQUALSCORE));
			request.setAttribute(Constants.SELECTEDSEGSCORE, request.getParameter(Constants.SELECTEDSEGSCORE));
			if (null != sliderSubPlaysItem) {
				request.setAttribute("sliderSubPlaysItem",	sliderSubPlaysItem);
			}
			request.setAttribute(Constants.ISTART, iStart);

			String repRoleCd = contractService.getAgentRepRoleCd(accid);
			request.setAttribute(Constants.REQREPROLECODE, repRoleCd);
			String agentName = contractService.getAgentName(loggedInUserId);
			if(null != agentName && !("".equals(agentName)))
				request.setAttribute(Constants.LOGGED_IN_USER_NAME,
						agentName);
			else{
				AccountUserDTO callOrderScreenUser = userRoleService
						.getUserDetail(loggedInUserId);
				if(null !=callOrderScreenUser)
					request.setAttribute(Constants.LOGGED_IN_USER_NAME,
							callOrderScreenUser.getFullname());
			}
			request.setAttribute(Constants.REQCUSTOMERNUMBER, custid);
			request.setAttribute(Constants.LOGGEDINUSERID, loggedInUserId);
			String mktResourceUrl = sfdcAppUrl;
			request.setAttribute(Constants.SFDC_APP_BASE_URL, mktResourceUrl);
			request.setAttribute(Constants.CTA_DISPOSITION_DISABLED_IDS, CtaDisableId);
			String alertState=request.getParameter("alertState");
			if(null==alertState || (alertState.equals("")))
				request.setAttribute("alertState", "OFF");
			else
				request.setAttribute("alertState",alertState);
			return "perfDashboard";

		}
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}


@RequestMapping(value = "/getDataForBossCoreCategory/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getDataForBossCoreCategory(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {
	try {
		Gson gson = new Gson();
		Map<String, BigDecimal> bossCoreMap = new HashMap<String, BigDecimal>();

		if (null != custNum) {
			bossCoreMap = perfWIRService.getSalesDataBossCoreCategory(custNum);
		}
		return gson.toJson(bossCoreMap);
		
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}


@RequestMapping(value = "/getPerfColumnAndDoughnutChartData/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getPerfColumnChartCategoriesData(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {
	try {
		Gson gson = new Gson();
		List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();

		if (null != custNum) {
			perfDashboardCategoryVOList = perfWIRService.getColumnAndDoughnutCategoryList(custNum);
		}
		return gson.toJson(perfDashboardCategoryVOList);
		
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}


@RequestMapping(value = "/getCustInsightData/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getCustInsightData(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {
	try {

		Gson gson = new Gson();
		List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();

		if (null != custNum) {
			perfDashboardCategoryVOList = perfWIRService.getCustInsightDataIfExist(custNum);
		}
		return gson.toJson(perfDashboardCategoryVOList);
		
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}


@RequestMapping(value = "/isDisplayWIR/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String isDisplayWIR(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {
	try {
		Gson gson = new Gson();
		String displayWIR = "N";
		if (null != custNum) {
			displayWIR = perfWIRService.isDisplayWIRFlag(custNum);
		}
		return gson.toJson(displayWIR);
		
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}

@RequestMapping(value="/getSegmentSubjectLabels",method={RequestMethod.GET},produces = MediaType.TEXT_PLAIN_VALUE)
@ResponseBody
public String getSegmentSubjectLabelsList() throws DashboardException,Exception
{
	try
	{
		List<String> subjectList=new ArrayList<String>();
		subjectList=ctaService.getSubjectLabels();
		Gson gson = new Gson();		
		return gson.toJson(subjectList);
		
		
	}
	catch(DashboardException de){
		throw de;
	}
	catch(Exception e){
		throw e;
	}
	
	
	
}

/*@RequestMapping(value = { "/findRewardStatements/{custId}" }, method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String findRewardStatements(@PathVariable("custId") String custNum, HttpServletRequest request, @RequestBody RewardsStatementVO rewardsStatementVO )
		throws DashboardException, Exception {
	try {
		String serviceURL = rewardStatements;
		if (custNum != null) {
			
			serviceURL += "/"+custNum;
		} 
		return getResponse(serviceURL, rewardsStatementVO);

	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}

@RequestMapping(value = { "/findRewardForStatement/{custId}/{statementNum}" }, method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String findRewardsForStatement(@PathVariable("custId") String custNum, @PathVariable("statementNum") String statementNum , HttpServletRequest request)
		throws DashboardException, Exception {
	try {
		String serviceURL = rewardsForStatement;
		if (custNum != null) {
			serviceURL += "/"+custNum+"/statementCode/"+statementNum;
		} 
		return getHTTPResponseForRewardsStatement(serviceURL);

	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}*/


private String getResponse(String serviceURL, RewardsStatementVO jsonData)
{
	RestTemplate template = new RestTemplate();
	
	String responseData =  template.postForObject(serviceURL, jsonData, String.class);
	
	return responseData;
}

private String authorizeCdm(String assignType, String result)
{
	if(null!=assignType && (assignType.indexOf("VP")!=-1 || assignType.indexOf("RVP")!=-1 || assignType.indexOf("RSD")!=-1 || assignType.indexOf("DSM")!=-1 || assignType.indexOf("DRM")!=-1))
	    return result;
	else if(null!=assignType && assignType.indexOf("AM1")!=-1)
		return (result.concat("_WITH_CDM_REP"));
	else 
		return result;
		
}


/**
 * Method implementation to fetch all customer data.
 * 
 * @param String
 *            accId
 * @param String
 *            playSection
 * @param String
 *            selSubPlays
 * @param HttpServletRequest
 *            request
 * @return String
 */
@RequestMapping(value = "/getAllCdmCustomers/{accId}/{selTimeZone}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
@JsonCreator
public String getAllCdmCustomers(@PathVariable("accId") String accId, @PathVariable("selTimeZone") String timeZoneSection,
		@RequestParam("rowId") String rowId, @RequestParam("alertStateStatus") String alertStateStatus ,
		HttpServletRequest request) throws DashboardException, Exception {
	try {
	    System.out.print("------>"+alertStateStatus);
		JQueryDataTableInputDTO dataTableInput = null;
		String iDisplayStart = request.getParameter("iDisplayStart");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String sSearch = request.getParameter("sSearch");
		String cusTypeSearch = request.getParameter("sSearch_2");
		String iSortingCols = request.getParameter("iSortingCols");
		String iSortCol_0 = request.getParameter("iSortCol_0");
		String sSortDir_0 = request.getParameter("sSortDir_0");
		String sEcho = request.getParameter("sEcho");
		/*if(null!=iDisplayStart && null!=alertStateStatus && alertStateStatus.equals("ON")){
			iDisplayStart="0";
		}*/
		if (sEcho != null && !sEcho.equals("")) {
			dataTableInput = new JQueryDataTableInputDTO();
			dataTableInput.setiDisplayLength(Integer
					.valueOf(iDisplayLength));
			/*System.out.print("------------------>"+iDisplayLength);
			if(null !=timeZoneSection && !("".equals(timeZoneSection)) && !(Integer.valueOf(iDisplayStart)>0)){
			 String[] timeZoneTypes = timeZoneSection.split(",");
			 if ((timeZoneTypes != null && timeZoneTypes.length > 0) && !(timeZoneSection.equals("ALL"))) {
				iDisplayStart=String.valueOf(Constants.ZERO);
			 }
			}*/
			dataTableInput.setiDisplayStart(Integer.valueOf(iDisplayStart));
			if (iSortCol_0 != null) {
				dataTableInput.setiSortCol_0(CdmCustProfileVO
						.getCustDataMapCdm(iSortCol_0));
			}
			if (null != cusTypeSearch) {
				dataTableInput.setsSearch_2(cusTypeSearch);
			}
			
			dataTableInput.setsSortDir_0(sSortDir_0);
			dataTableInput.setsSearch(sSearch);
			if (iSortingCols != null && !iSortingCols.equals("")) {
				dataTableInput.setiSortingCols(Integer
						.valueOf(iSortingCols));
			}
		}

		
		JQueryDataTableOutputDTOCDM returnList = new JQueryDataTableOutputDTOCDM();

		Gson gson = new Gson();
		CustomerDataVo customerDataVo = new CustomerDataVo();
		
	

		if (null != accId) {
			CustomerListDTO custData = contractService.getAllCdmCustomerInfo(dataTableInput,timeZoneSection,
					accId,rowId,alertStateStatus);
			List<CdmCustProfileVO> listOfCdmCustProfileVO = custData
					.getCdmCustomerVoList();
			if (null != listOfCdmCustProfileVO) {
				customerDataVo.setListOfCdmCustProfileVO(listOfCdmCustProfileVO);
				returnList.setAaData(listOfCdmCustProfileVO);
				returnList.setiTotalRecords(custData.getTotalCount());
				returnList.setiTotalDisplayRecords(custData
						.getTotalCount());
				//returnList.setTimeZoneList(custData.getTimeZoneList());
				returnList.setTimeZoneMap(custData.getTimeZoneMap());
				if (sEcho != null && !sEcho.equals("")) {
					returnList.setsEcho(Integer.valueOf(sEcho));
				}
				/*request.setAttribute(Constants.CDM_TOTAL_COUNT,
						(null == request.getParameter(Constants.CDM_TOTAL_COUNT)
								|| ("".equals(request.getParameter(Constants.CDM_TOTAL_COUNT)))) ? custData
										.getTotalCount() :request.getParameter(Constants.CDM_TOTAL_COUNT));*/
				
				gson.toJson(customerDataVo);

			}
		}
		
		return gson.toJson(returnList);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}

}



@RequestMapping(value ="/lastLiveContactDetails/{iamId}", method = {RequestMethod.GET,RequestMethod.POST} , produces = "application/json")
@ResponseBody
public String getLastLiveContactDetails(@PathVariable("iamId") String iamId,@RequestParam("custNum")  String custNumber) throws DashboardException, Exception  {

	long methodStart=System.currentTimeMillis();
	try{
		Gson gson=new Gson();
		LOGGER.info("Entered on getLastLiveContactDetails() ");
		if(StringUtils.isEmpty(iamId) || StringUtils.isEmpty(custNumber))
			throw new DashboardException(CTAConstants.REQUIRED_VAL_IS_NULL_ERROR_MSG);
	
		LastLiveContactAndHistoryVO lastLiveContactAndHistoryVO=contractService.getLastLiveContactDetails(iamId,custNumber);
		
   return gson.toJson(lastLiveContactAndHistoryVO);
	}catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}finally{
		LOGGER.info("getLastLiveContactDetails() API response time taken in milliseconds : ");
		LOGGER.info(methodStart-System.currentTimeMillis());
		LOGGER.info("Exiting on getLastLiveContactDetails() ");
	}

}

@RequestMapping(value = "/getCustomerOrderContactDetails/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getCustomerOrderContactDetails(@PathVariable("cid") String custNum,@RequestParam("iamId") String iamId)
		throws DashboardException, Exception {
	try {
		
		Gson gson = new Gson();
		List<OrderContactInfoVO> list=new ArrayList<OrderContactInfoVO>();
		if (null != custNum) {
			
			 list= contractService.getCustomerOrderContactDetails(custNum,iamId);

		}
		return gson.toJson(list);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}

@RequestMapping(value = "/getTodaysAndWeekProgress/{repNum}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getTodaysAndWeekProgress(@PathVariable("repNum") String repNum)
		throws DashboardException, Exception {
	try {
		
		Gson gson = new Gson();
		AccountProgressInfo accountProgressInfo=new AccountProgressInfo();
		
		if (null != repNum ) {
			
			 accountProgressInfo= contractService.getTodaysAndWeekProgress(repNum);

		}
		return gson.toJson(accountProgressInfo);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}

@RequestMapping(value ="/getSegmentDetailsById", method = {RequestMethod.GET,RequestMethod.POST} , produces = "application/json")
@ResponseBody
public String getSegmentDetails(@RequestParam("segIds") String segmentId	) throws DashboardException, Exception  {

	long methodStart=System.currentTimeMillis();
	try{
		Gson gson=new Gson();
		LOGGER.info("Entered on getSegmentDetails() ");
		if(StringUtils.isEmpty(segmentId))
			throw new DashboardException(CTAConstants.REQUIRED_VAL_IS_NULL_ERROR_MSG);
	
	List<NotificationInfoVo> voList=contractService.getSegmentDetails(segmentId);
		
   return gson.toJson(voList);
	}catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}finally{
		LOGGER.info("getSegmentDetails() API response time taken in milliseconds : ");
		LOGGER.info(methodStart-System.currentTimeMillis());
		LOGGER.info("Exiting on getSegmentDetails() ");
	}

}

@ResponseBody
@RequestMapping(value="/updateAlertStatus/{custId}",method={RequestMethod.POST},produces="application/json")
public String updateAlertStatus(@PathVariable("custId") String custId) throws DashboardException, Exception{
	String status="";
	try{
		Gson gson=new Gson();
		
		LOGGER.info("Entered on getSegmentDetails() ");
	   // List<NotificationInfoVo> voList=contractService.updateAlertStatus(custId);
		if(StringUtils.isEmpty(custId) ){
			throw new DashboardException(CTAConstants.REQUIRED_VAL_IS_NULL_ERROR_MSG);
		}
		String  ldapUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername();
		
		String ldapUserName=" ";
	
		AccountUserDTO loggedInUser = userRoleService
				.getUserDetail(ldapUserId);
		
		String ldapUserRole = contractService.getAgentRepRoleCd(ldapUserId);
		//List<String>  ldapUserList=contractService.getLdapLoggedUserName(ldapUserId);
		
		//String ldapUserRole=" ";
		if(null!=loggedInUser  && loggedInUser.getFullname()!=null )
		{
			ldapUserName=loggedInUser.getFullname();
			//ldapUserRole=loggedInUser.getRepRoleCd();
			
		}
		else 
		{
			 ldapUserName = contractService.getAgentName(ldapUserId);
			
		}
		status = contractService.updateAlertStatus(custId,ldapUserRole,ldapUserId,ldapUserName);
		return gson.toJson(status);
	}catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	} finally{
		
		LOGGER.info("updateAlertStatus() API executed ");
	}
}


@RequestMapping(value = { "/getAcountAlerts/{custId}" }, method = { RequestMethod.GET,RequestMethod.POST },produces = "application/json")
@ResponseBody
public String getAcountAlertList(@PathVariable("custId") String custNum) throws DashboardException, Exception {
	List<AlertDetailVo> alertDetails=null;
	try {
		Gson gson = new Gson();
		alertDetails= contractService.getAlertDetail(custNum);
		return gson.toJson(alertDetails);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}finally{
		LOGGER.info("Exiting from openSubCallToAction()");
	}
}
	

@RequestMapping(value = { "/deleteAlert/" }, method = { RequestMethod.GET,RequestMethod.POST },produces = "application/json")
@ResponseBody
public String getAcountAlertList(@RequestParam("custNum") String custNum,@RequestParam("alertIdCombination") String alertIdCombination,@RequestParam("alertId") String alertId) throws DashboardException, Exception {
	String status=null;
	try {
		Gson gson = new Gson();
		String  ldapUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername();
		if(null!=ldapUserId)
		    status= contractService.deleteAlert(custNum,alertIdCombination,alertId,ldapUserId);
		else
			status= contractService.deleteAlert(custNum,alertIdCombination,alertId,MapperConstants.BLANK_STRING);	
		return gson.toJson(status);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}finally{
		LOGGER.info("Exiting from openSubCallToAction()");
	}
}

@RequestMapping(value = { "/readAlert/" }, method = { RequestMethod.GET,RequestMethod.POST },produces = "application/json")
@ResponseBody
public String updateReadAlert(@RequestParam("custNum") String custNum,@RequestParam("alertIdCombination") String alertIdCombination,@RequestParam("alertId") String alertId) throws DashboardException, Exception {
	String status=null;
	try {
		Gson gson = new Gson();
		String  ldapUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername();
		if(null!=ldapUserId)
		    status=  contractService.updateReadAlert(custNum,alertIdCombination,alertId,ldapUserId);
		else
			status=  contractService.updateReadAlert(custNum,alertIdCombination,alertId,MapperConstants.BLANK_STRING);
		return gson.toJson(status);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}finally{
		LOGGER.info("Exiting from openSubCallToAction()");
	}
}

@RequestMapping(value = "/getLatestFiscaExactOrderDate/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String getLatestFiscaExactOrderDate(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {

	try {
		Gson gson = new Gson();
		String latestFiscalExactDate = null;
		if (null != custNum)
			latestFiscalExactDate = contractService.getLatestFiscaCompleteOrderDate(custNum);
		
		return gson.toJson(latestFiscalExactDate);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
}

}

