package com.staples.dashboard.app.hibernate;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.staples.dashboard.app.sfdc.Task;

import org.springframework.security.core.context.SecurityContextHolder;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;

import org.apache.commons.dbcp2.DelegatingConnection;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.force.api.ApiConfig;
import com.force.api.ApiVersion;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.ReportConstants;
import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.dao.dbvo.CustProfileDAO;
import com.staples.dashboard.app.dao.mappers.AbandonedCartVOMapper;
import com.staples.dashboard.app.dao.mappers.AccountUserMapper;
import com.staples.dashboard.app.dao.mappers.AlertDetailMapper;
import com.staples.dashboard.app.dao.mappers.BoughtAlsoBoughtVOMapper;
import com.staples.dashboard.app.dao.mappers.CTABaseMapper;
import com.staples.dashboard.app.dao.mappers.CTAHdrTxtRowMapper;
import com.staples.dashboard.app.dao.mappers.CategoryPenetrationMapper;
import com.staples.dashboard.app.dao.mappers.CdmCustomerMapper;
import com.staples.dashboard.app.dao.mappers.CdmOrderContactsMapper;
import com.staples.dashboard.app.dao.mappers.ContactDateMapper;
import com.staples.dashboard.app.dao.mappers.CustBoughtAlsoBoughtRecommendationVOMapper;
import com.staples.dashboard.app.dao.mappers.CustRecommendationVOMapper;
import com.staples.dashboard.app.dao.mappers.CustSfdcInfoMapper;
import com.staples.dashboard.app.dao.mappers.CustomerMapper;
import com.staples.dashboard.app.dao.mappers.CustomerUserMapper;
import com.staples.dashboard.app.dao.mappers.DespositionDetailsMapper;
import com.staples.dashboard.app.dao.mappers.DotcomActivityVOMapper;
import com.staples.dashboard.app.dao.mappers.LastLiveContactAndHistoryMapper;
import com.staples.dashboard.app.dao.mappers.LastLiveOrderContactMapper;
import com.staples.dashboard.app.dao.mappers.LeadCustomerMapper;
import com.staples.dashboard.app.dao.mappers.NephosConfigMapper;
import com.staples.dashboard.app.dao.mappers.NotificationInfoMapper;
import com.staples.dashboard.app.dao.mappers.OnlineRetailReorderRecommendationMapper;
import com.staples.dashboard.app.dao.mappers.PurchaseDetailsVOMapper;
import com.staples.dashboard.app.dao.mappers.PurchaseHeliosDetailsVOMapper;

import com.staples.dashboard.app.dao.mappers.QualScoreMapper;
import com.staples.dashboard.app.dao.mappers.RecommOrderContactMapper;
import com.staples.dashboard.app.dao.mappers.RecommendationViewNotBoughtMapper;
import com.staples.dashboard.app.dao.mappers.ReorderRecommendationMapper;
import com.staples.dashboard.app.dao.mappers.SavingsMapper;
import com.staples.dashboard.app.dao.mappers.SavingsReportMapper;
import com.staples.dashboard.app.dao.mappers.SavingsSDCReportMapper;
import com.staples.dashboard.app.dao.mappers.SbaDiffDetailsMapper;
import com.staples.dashboard.app.dao.mappers.SearchItemsVOMapper;
import com.staples.dashboard.app.dao.mappers.SegMktgResourcesMapper;
import com.staples.dashboard.app.dao.mappers.SegmentSubDetailMapper;
import com.staples.dashboard.app.dao.mappers.SfdcConfigMapper;
import com.staples.dashboard.app.dao.mappers.SfdcInputIdMapper;
import com.staples.dashboard.app.dao.mappers.ShipToDetailsVOMapper;
import com.staples.dashboard.app.dao.mappers.ShipToVOMapper;
import com.staples.dashboard.app.dao.mappers.StaplesDashBoardRowMapper;
import com.staples.dashboard.app.dao.mappers.SubPlayInfoMapper;
import com.staples.dashboard.app.dao.mappers.UserDetailInfoMapper;
import com.staples.dashboard.app.dao.mappers.VapScoreMapper;
import com.staples.dashboard.app.dao.mappers.YTDInfoMapper;
import com.staples.dashboard.app.dao.mappers.YTDSummaryVOMapper;
import com.staples.dashboard.app.sdc.vo.PriceLog;
import com.staples.dashboard.app.sfdc.InputTaskSalesforceDTO;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.vo.AbabdonedCartVO;
import com.staples.dashboard.app.vo.AccountProgressInfo;
import com.staples.dashboard.app.vo.AlertDetailVo;
import com.staples.dashboard.app.vo.AlertParamDetailsVo;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.CdmCustProfileVO;
import com.staples.dashboard.app.vo.ConfigSfdcVO;
import com.staples.dashboard.app.vo.ContactDateVO;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.CustRecommendationVO;
import com.staples.dashboard.app.vo.CustSfdcInfoVO;
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
import com.staples.dashboard.app.vo.QualScoreVO;
import com.staples.dashboard.app.vo.RecommOrderContactVO;
import com.staples.dashboard.app.vo.RecommendationViewNotBoughtVO;
import com.staples.dashboard.app.vo.ReorderRecommendationVO;
import com.staples.dashboard.app.vo.SavingsVo;
import com.staples.dashboard.app.vo.SbaDiffDetailsVo;
import com.staples.dashboard.app.vo.SearchVO;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegMktgResources;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.SfdcInputId;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToVO;
import com.staples.dashboard.app.vo.SubplayInfoVo;
import com.staples.dashboard.app.vo.SuperUserInfoVO;
import com.staples.dashboard.app.vo.VapScoreVO;
import com.staples.dashboard.app.vo.YTDInfoVO;
import com.staples.dashboard.app.vo.YTDSummaryVO;
import com.staples.dashboard.dto.AccountUserDTO;
import com.staples.dashboard.dto.CustomerListDTO;
import com.staples.dashboard.dto.SavingsDetailDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class ContractDashboardDaoImpl.
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
@Repository
@Transactional
public class ContractDashboardDaoImpl implements CustProfileDAO {

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	private static final Logger logger = Logger
			.getLogger(ContractDashboardDaoImpl.class);
	
	@Value("${adminSFDCId}")
	private String adminSFDCId;
	@Value("${REORDER_ORDER_TRANSACTION_DAYS}")
	private int reorderTransactionDays;
	@Value("${BAB_ORDER_TRANSACTION_DAYS}")
	private int boughtAlsoBoughtTransactionDays;
	
	@Value("${mmpivot.pricev4:false}")
	private boolean mmpivotPriceV4;
	/*
	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwnDev")
	private SessionFactory	custExoHeliosOwnSessionFactoryDev;
	*/
	
	/**
	 * Default constructor
	 */
	/*ContractDashboardDaoImpl() {

	}*/

	/**
	 * Method implementation to fetch customer profile.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustProfileVO>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustProfileVO> getCustomerProfile(String custNum)
			throws DashboardException {
		logger.trace("Entering method :--> getCustomerProfile()");
		CustProfileVO customerVO = null;
		Session session = null;
		List<CustProfileVO> listCustProfile = new ArrayList<CustProfileVO>();
		try {
			SQLQuery sqlQuery = null;
			session = custExoHeliosOwnSessionFactory.openSession();
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_CUSTOMER_PROFILE_DETAILS);
			boolean premiumCust = getPremiumUser(custNum);
			if(premiumCust){
				sqlQuery = session
						.createSQLQuery(SQLConstants.SQL_GET_CUSTOMER_PROFILE_DETAILS_PREMIUM);
			}else{
				sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_CUSTOMER_PROFILE_DETAILS);
			}
			sqlQuery.setParameter("custNum", custNum);
			//sqlQuery.setParameter(0, custNum);
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					customerVO = (CustProfileVO) new CustomerMapper()
							.mapRow(list.get(i));
					listCustProfile.add(customerVO);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getCustomerProfile()");
		}
		return listCustProfile;
	}

	/**
	 * Method implementation to fetch YTD info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<YTDInfoVO>
	 */
	@Override
	public List<YTDInfoVO> getYTDInfo(String custNum) throws DashboardException {
		logger.trace("Entering method :--> getYTDInfo()");
		List<YTDInfoVO> ytdInfoVO = new ArrayList<YTDInfoVO>();

		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_YTD_INFO_CUSTEXO);

			final Object[] params = { custNum };
			for (Object object : getResultList(session,
					SQLConstants.SQL_GET_YTD_INFO_CUSTEXO, params,
					new YTDInfoMapper())) {
				if (null != object) {
					ytdInfoVO.add((YTDInfoVO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}

		return ytdInfoVO;
	}

	/**
	 * Method implementation to fetch purchase details.
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
			String mnth, String year, String catId) throws DashboardException {
		List<PurchaseDetailsVO> purchaseDetailsVO = new ArrayList<PurchaseDetailsVO>();
		Session session = null;
		String SQL = null;
		Object[] params = null;
		String [] columns = {"CUST_NUM", "ORDER_NUMBER", "ORDER_LINE_COUNT", "ORDER_DATE", "ORDER_AMOUNT", "ORDER_CONTACT",
				"SAVING_ORDER", "CATEGORY", "AMOUNT"};
	
		
		try {
			if (Integer.parseInt(mnth) < 0) {
				if (catId.equalsIgnoreCase("ALL")) {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SALESTRAN;
					params = new Object[] { custNum, Integer.valueOf(mnth),custNum };
				} else {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SALESTRAN_CAT;
					params = new Object[] { custNum, Integer.valueOf(mnth),
							catId,custNum };
				}
				session = custExoHeliosOwnSessionFactory.openSession();
				// final Object[] params = { custNum, Integer.valueOf(mnth) };
				for (Object object : getCacheableResultList(session, SQL, params, columns,
						new PurchaseDetailsVOMapper())) {
					if (null != object) {
						purchaseDetailsVO.add((PurchaseDetailsVO) object);
					}
				}
			} else if (Integer.parseInt(mnth) == 0) {
				if (catId.equalsIgnoreCase("ALL")) {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN;
					params = new Object[] { custNum, String.valueOf(year+"0101"),String.valueOf(year+"1231"),custNum };
				} else {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN_CAT;
					params = new Object[] { custNum, String.valueOf(year+"0101"),String.valueOf(year+"1231"), catId,custNum };
				}

				session = custExoHeliosOwnSessionFactory.openSession();
				// final Object[] params = { custNum, year };
				for (Object object : getCacheableResultList(session, SQL, params, columns,
						new PurchaseDetailsVOMapper())) {
					if (null != object) {
						purchaseDetailsVO.add((PurchaseDetailsVO) object);
					}
				}
			} else {
				/* calculate end day of selected month */
				Calendar calendar;
				calendar = Calendar.getInstance();
	            calendar.set(Integer.parseInt(year), Integer.parseInt(mnth)-1, 1);
	            int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int month = 0;
				long startDate = 0;
				long endDate = 0;
//				if (Integer.parseInt(mnth) < 12) {
//					month = Integer.parseInt(mnth) + 1;
//					startDate = Integer.valueOf(String.valueOf(year)
//							+ String.valueOf(Integer.parseInt(mnth)));
//					endDate = Integer.valueOf(String.valueOf(year)
//							+ String.valueOf(month));
//
//				}
//				
//
//				if (Integer.parseInt(mnth) == 12) {
//					int yearNext = Integer.parseInt(year) + 1;
//					startDate = Integer.valueOf(String.valueOf(year)
//							+ String.valueOf(Integer.parseInt(mnth)));
//					endDate = Integer.valueOf(String.valueOf(yearNext)
//							+ String.format("%02d", 1));
//				}
				
				startDate = Long.valueOf(year + String.format("%02d", Integer.parseInt(mnth)) + "01");
				endDate = Long.valueOf(year + String.format("%02d", Integer.parseInt(mnth)) + endDay);
				if (catId.equalsIgnoreCase("ALL")) {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN;
					params = new Object[] { custNum, String.valueOf(startDate), String.valueOf(endDate),
							/* String.format("%02d", Integer.parseInt(mnth)), String.format("%02d", Integer.parseInt(mnth)), */ custNum };
				} else {
					SQL = SQLConstants.SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN_CAT;
					params = new Object[] { custNum, String.valueOf(startDate), String.valueOf(endDate),
							/* String.format("%02d", Integer.parseInt(mnth)),
							String.format("%02d", Integer.parseInt(mnth)), */
							catId,custNum };
				}
				session = custExoHeliosOwnSessionFactory.openSession();

				for (Object object : getCacheableResultList(session, SQL, params, columns,
						new PurchaseDetailsVOMapper())) {
					if (null != object) {
						purchaseDetailsVO.add((PurchaseDetailsVO) object);
					}
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {

			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return purchaseDetailsVO;
	}

	/**
	 * Method implementation to fetch purchase rewards details.
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
		List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO = new ArrayList<PurchaseHeliosDetailsVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		ResultSet rs = null;
		try {

			session = custExoHeliosOwnSessionFactory.openSession();
			sessionImpl = (SessionImpl) session;
			conn = sessionImpl.connection();
			
			
			
			stmt = conn
					.prepareCall("{ CALL HELIOS_OWN.SP_GET_ORDER_DETAIL(?,?,?) }");

			stmt.setString(1, custNum);
			Connection dconn = ((DelegatingConnection<Connection>) conn).getInnermostDelegate();
			Array  orderNumberArray = ((OracleConnection) dconn).createARRAY("HELIOS_OWN.NUM_ARRAY", orderNumberList);
			
			stmt.setArray(2, orderNumberArray);		 
			stmt.registerOutParameter(3,
					OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(3);
			
			while (rs.next()) {
				PurchaseHeliosDetailsVO vo = new PurchaseHeliosDetailsVO();

				vo.setSkuNumber(rs.getString(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NUM)
								.toString());
				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NAME)
								.toString());
				vo.setTranDate(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_DATE)
								.toString());
				vo.setTranId(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER)
								.toString());
				vo.setTotalQty(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_QTY)
								.toString());
				vo.setNetSpendAmount(rs
						.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND)
								.toString());

				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ITEM_DESC)
								.toString());

				vo.setTotalPriceAmount(rs
						.getString(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.RWDDETAIL_UNIT_SALE_PRICE)
								.toString());
				vo.setCategoryId(rs
						.getString(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO
						: Float.valueOf(rs
								.getString(
										MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD)
								.toString()));

				purchaseRwdsDetailsVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getPurchaseRewardsDetails()");
		}
		return purchaseRwdsDetailsVO;
	}

	/**
	 * Method implementation to fetch super user info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SuperUserInfoVO>
	 */
	@Override
	public List<SuperUserInfoVO> getSuperUserInfo(String custNum)
			throws DashboardException {
		List<SuperUserInfoVO> superUserInfoVO = new ArrayList<SuperUserInfoVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		try {
			String latestDate = getLatestFiscalDate(custNum);
			String yr=null;
			if(null!=latestDate)
			yr= latestDate.substring(2, 6);
			session = custExoHeliosOwnSessionFactory.openSession();
			sessionImpl = (SessionImpl) session;
			conn = sessionImpl.connection();
			stmt = conn
					.prepareCall(SQLConstants.SQL_GET_SUPER_USER_DETAILS_AND_INFO);

			stmt.setString(Constants.CUSTNUM, custNum);

			stmt.registerOutParameter(Constants.CURSOR_OUTPUT,
					OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = (ResultSet) stmt.getObject(Constants.CURSOR_OUTPUT);
			while (rs.next()) {
				SuperUserInfoVO vo = new SuperUserInfoVO();
				vo.setNumOrders(rs
						.getString(MapperConstants.SUPERUSERINFO_ORDERS) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.SUPERUSERINFO_ORDERS)
								.toString());
				vo.setNumOrdersCurr(rs
						.getString(MapperConstants.SUPERUSERINFO_ORDERS_CURR) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_ORDERS_CURR)
								.toString());
				vo.setNumOrdersMon(rs
						.getString(MapperConstants.SUPERUSERINFO_ORDERS_PERIOD) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_ORDERS_PERIOD)
								.toString());
				vo.setOrderContact(rs
						.getString(MapperConstants.SUPERUSERINFO_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_ORDER_CONTACT)
								.toString());
				vo.setNumVisits(rs
						.getString(MapperConstants.SUPERUSERINFO_VISIT_COUNT) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_VISIT_COUNT)
								.toString());
				vo.setVisitDate(rs
						.getString(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE)
								.toString().substring(0, 10));
				vo.setTotalSales(rs
						.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_TOTAL_SALES)
								.toString());
				vo.setTotalSalesCurr(rs
						.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR)
								.toString());
				vo.setContactEmail(rs
						.getString(MapperConstants.SUPERUSERINFO_EMAIL));
				vo.setContactPhone(rs
						.getString(MapperConstants.SUPERUSERINFO_PHONE_NUMBER));
				vo.setIamId(rs.getString("IAM_ID") == null ? MapperConstants.BLANK_STRING : rs.getString("IAM_ID").toString());
				vo.setFirstName(rs.getString("FIRST_NAME") == null ? MapperConstants.BLANK_STRING : rs.getString("FIRST_NAME").toString());
				vo.setLastName(rs.getString("LAST_NAME") == null ? MapperConstants.BLANK_STRING : rs.getString("LAST_NAME").toString());
				superUserInfoVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();
			/*
			 * String SQL = SQLConstants.SQL_GET_SUPER_USER_DETAILS_AND_INFO;
			 * session = custExoHeliosOwnSessionFactory.openSession(); final
			 * Object[] params = { custNum }; for (Object object :
			 * getResultList(session, SQL, params, new SuperUserInfoVOMapper()))
			 * { if (null != object) { superUserInfoVO.add((SuperUserInfoVO)
			 * object); } }
			 */
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return superUserInfoVO;
	}

	/**
	 * Method implementation to fetch abandoned cart info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<AbabdonedCartVO>
	 */
	@Override
	public List<AbabdonedCartVO> getAbandonedCartInfo(String custNum)
			throws DashboardException {
		List<AbabdonedCartVO> getAbandonedCartInfoVO = new ArrayList<AbabdonedCartVO>();
		Session session = null;
		try {

			String SQL = "SELECT * FROM(SELECT CONTACT_NAME AS ORDER_CONTACT, SKU_NUMBER AS SKU, P.SKU_NAME,P.CATENTRY_ID, CART_ABANDONED_DATE AS ACT_DATE, ACTIVITY AS ACT, P.THUMBNAIL  AS THUMBNAIL,row_number() over (partition BY SKU_NUMBER order by CART_ABANDONED_DATE DESC) AS rowdetail "
					+ "FROM HELIOS_OWN.SA_CART_ABANDONMENT X LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER P ON P.SKU_NUM   =X.SKU_NUMBER "
					+ "WHERE CUSTOMER_NUMBER = ? ORDER BY CONTACT_NAME,CART_ABANDONED_DATE DESC)WHERE rowdetail =1";

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };
			for (Object object : getResultList(session, SQL, params,
					new AbandonedCartVOMapper())) {
				if (null != object) {
					getAbandonedCartInfoVO.add((AbabdonedCartVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return getAbandonedCartInfoVO;
	}

	/**
	 * Method implementation to fetch customer recommendation info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getCustRecommendationInfo(String custNum)
			throws DashboardException {
		List<CustRecommendationVO> getCustRecommendationVO = new ArrayList<CustRecommendationVO>();
		Session session = null;
		try {

			String SQL = "SELECT ORDER_CONTACT, SKU, P.SKU_NAME, THUMBNAIL AS THUMBNAIL FROM  (SELECT CUSTOMER_NUMBER, SUBSTR(SKU_LIST, instr(SKU_LIST, ',', 1, lvl) + 1, instr(SKU_LIST, ',', 1, lvl + 1) - instr(SKU_LIST, ',', 1, lvl) - 1) SKU,"
					+ "USER_NAME AS ORDER_CONTACT FROM (SELECT ',' || SKU_LIST || ',' AS SKU_LIST,  CUSTOMER_NUMBER, USER_NAME FROM HELIOS_OWN.SA_USER_RECOMMENDATION ), ( SELECT level AS lvl FROM dual CONNECT BY level <= 100 )"
					+ " WHERE lvl   <= LENGTH(SKU_LIST) - LENGTH(REPLACE(SKU_LIST, ',')) - 1  AND CUSTOMER_NUMBER = ? )X LEFT OUTER JOIN helios_own.SA_PRODUCT_MASTER p "
					+ "ON P.SKU_NUM=X.SKU ORDER BY ORDER_CONTACT";

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };
			for (Object object : getResultList(session, SQL, params,
					new CustRecommendationVOMapper())) {
				if (null != object) {
					getCustRecommendationVO.add((CustRecommendationVO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return getCustRecommendationVO;
	}

	/**
	 * Method implementation to fetch dot com activity info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<DotcomAcitivityVO>
	 */
	@Override
	public List<DotcomAcitivityVO> getDotcomActivityInfo(String custNum)
			throws DashboardException {
		List<DotcomAcitivityVO> getDotcomActivityVO = new ArrayList<DotcomAcitivityVO>();
		Session session = null;
		try {

			String SQL = "select * from (SELECT CONTACT_NAME AS ORDER_CONTACT, SKU_NUMBER AS SKU, P.SKU_NAME, CART_ABANDONED_DATE AS ACT_DATE, ACTIVITY AS ACT, P.THUMBNAIL AS THUMBNAIL, PRICE, CAST(QUANTITY AS INT) AS QUANTITY, row_number() over (partition BY SKU_NUMBER order by CART_ABANDONED_DATE DESC) AS rowdetail FROM HELIOS_OWN.SA_DOTCOM_ACTIVITY X "
					+ "LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER P ON P.SKU_NUM   =X.SKU_NUMBER WHERE CUSTOMER_NUMBER = ? ORDER BY CONTACT_NAME,CART_ABANDONED_DATE DESC) where rowdetail=1";

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };
			for (Object object : getResultList(session, SQL, params,
					new DotcomActivityVOMapper())) {
				if (null != object) {
					getDotcomActivityVO.add((DotcomAcitivityVO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return getDotcomActivityVO;
	}

	/**
	 * Method implementation to fetch search item list.
	 * 
	 * @param String
	 *            custNum
	 * @return List<SearchVO>
	 */
	@Override
	public List<SearchVO> getSearchItemsList(String custNum)
			throws DashboardException {
		List<SearchVO> getSearchItemsVO = new ArrayList<SearchVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GET_SEARCH_ITEM_LIST;
			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };
			for (Object object : getResultList(session, SQL, params,
					new SearchItemsVOMapper())) {
				if (null != object) {
					getSearchItemsVO.add((SearchVO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return getSearchItemsVO;
	}

	/**
	 * Method implementation to fetch YTD summary.
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
		List<YTDSummaryVO> ytdSummaryVO = new ArrayList<YTDSummaryVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		try {
			String startDate = selectedYear;
			String endDate = selectedYear;
			String SQL = "WITH DT"
					+ " AS (SELECT CLD_DT, FSC_YR, FSC_PRD"
					+ " FROM HELIOS_OWN.D_TIME"
					+ " WHERE TM_LVL = 'Day')"
					+ " SELECT DT1.FSC_PRD AS MONTH,"
					+ " (case when PRIMARY_PRODUCT_CAT_CD = 9 then 10 when PRIMARY_PRODUCT_CAT_CD = 8 then 11 else to_number(NVL(PRIMARY_PRODUCT_CAT_CD,11)) end)         AS CAT,"
					+ " SUM((UNIT_SALE_PRICE * ORDER_QUANTITY)"
					+ "  ) AS TOTAL_SPND"
					+ " FROM helios_own.MV_SA_ORDER_LINE l"
					+ " JOIN"
					+ " ( SELECT DISTINCT CUSTOMER_NUMBER,"
					+ " ORDER_NUMBER"
					+ " FROM helios_own.MV_SA_ORDER_HEADER"
					+ " WHERE CUSTOMER_NUMBER = ? and UPPER(ORDER_TYPE) NOT IN ('RE', 'QU', 'MO')"
					+ " )H"
					+ " ON H.ORDER_NUMBER = L.ORDER_NUMBER "
					+ " LEFT OUTER JOIN helios_own.SA_PRODUCT_MASTER p"
					+ " ON to_char(L.STAPLES_SKU_NUMBER)= P.SKU_NUM"
					+ " INNER JOIN DT DT1 ON DT1.CLD_DT = L.ORDER_DATE"
					+ " WHERE l.ORDER_DATE BETWEEN  (SELECT MIN(cld_DT) FROM DT WHERE FSC_YR=?) AND (SELECT MAX(cld_DT) FROM DT WHERE FSC_YR=?)"
					+ " GROUP BY (case when PRIMARY_PRODUCT_CAT_CD = 9 then 10 when PRIMARY_PRODUCT_CAT_CD = 8 then 11 else to_number(NVL(PRIMARY_PRODUCT_CAT_CD,11)) end) ,"
					+ " DT1.FSC_PRD" + " ORDER BY DT1.FSC_PRD";

			session = custExoHeliosOwnSessionFactory.openSession();
			/*
			 * final Object[] params = { custNum,startDate,endDate }; for
			 * (Object object : getResultList(session, SQL, params, new
			 * YTDSummaryVOMapper())) { if(null != object){
			 * ytdSummaryVO.add((YTDSummaryVO) object); } }
			 */

			sessionImpl = (SessionImpl) session;
			conn = sessionImpl.connection();
			stmt = conn.prepareCall(SQLConstants.PROCEDURE_GET_YTD_SUMMARY);

			stmt.setString(Constants.CUSTNUM, custNum);
			stmt.setString(Constants.SELECTED_YEAR, startDate);
			stmt.registerOutParameter(Constants.CURSOR_OUTPUT,
					OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = (ResultSet) stmt.getObject(Constants.CURSOR_OUTPUT);
			while (rs.next()) {
				YTDSummaryVO vo = new YTDSummaryVO();
				vo.setTranMonth(rs.getString(MapperConstants.YTDSUM_MONTH));
				vo.setCategoryId(rs.getString(MapperConstants.YTDSUM_CAT));
				vo.setNetSpendAmount(rs
						.getString(MapperConstants.YTDSUM_TOTAL_SPND));
				ytdSummaryVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			if (sessionImpl != null && sessionImpl.isOpen()) {
				sessionImpl.close();

			}
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return ytdSummaryVO;
	}

	
	
	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Object
	 *            [] params
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getResultList(Session session, String sql,
			Object[] params, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			if (params != null && params.length > 0) {
				int i = 0;
				for (Object object : params) {
					sqlQuery.setParameter(i++, object);
				}
			}
			
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} catch (HibernateException he) {
			logger.error("Error in method getResultList()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getResultList()", e);
			throw e;

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultList()");
		}
		return resultList;
	}

	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Object
	 *            [] params
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getCacheableResultList(Session session, String sql,
			Object[] params, String [] scalars, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			if (params != null && params.length > 0) {
				int i = 0;
				for (Object object : params) {
					sqlQuery.setParameter(i++, object);
				}
			}
			sqlQuery.setCacheable(true);
			for (String columnAlias : scalars) {
				sqlQuery.addScalar(columnAlias, StringType.INSTANCE);
			}
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} catch (HibernateException he) {
			logger.error("Error in method getResultList()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getResultList()", e);
			throw e;

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultList()");
		}
		return resultList;
	}
	
	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Object
	 *            [] params
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getResultList1(Session session, String sql,
			Object[] params, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			if (params != null && params.length > 0) {

				for (Object o : params) {
					if ((!((String) o).equals("")))
						sql = sql.concat((String) o).concat(")");
					else
						sql = sql.concat("").concat(")");
				}
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} catch (HibernateException he) {
			logger.error("Error in method getResultList()", he);
			throw he;

		} catch (Exception e) {
			logger.error("Error in method getResultList()", e);
			throw e;
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultList()");
		}
		return resultList;
	}

	/**
	 * Method implementation to fetch notification info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<NotificationInfoVo>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<NotificationInfoVo> getNotiInfo(String custNum)
			throws DashboardException {
		logger.trace("Entering method :--> getNotiInfo()");
		List<NotificationInfoVo> notiInfoVo = new ArrayList<NotificationInfoVo>();

		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_PLAYIDLIST_CUSTEXO);
			SQLQuery sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_PLAYIDLIST_CUSTEXO);
			/*SQLQuery sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER);*/
			sqlQuery.setParameter(0, custNum);
			List<String> list = sqlQuery.list();
			String playlistArr[] = new String[] {};
			String str = "";
			if (null != list && !(list.isEmpty())) {
				if (list.get(0) != null) {
					playlistArr = list.get(0).split(",");
				}
				list = Arrays.asList(playlistArr);
			}
			for (int count = 0; count < list.size(); count++) {
				str = str + list.get(count) + ",";
			}

			if (null != str && !(("").equals(str)) && str.indexOf(",") != -1) {
				str = str.substring(0, str.lastIndexOf(","));
				final Object[] params = { str };
				for (Object object : getResultList1(
						session,
						SQLConstants.SQL_GET_NOTIFICATION_DETAILS_MORE_THAN_ONE,
						params, new NotificationInfoMapper())) {
					if (null != object) {
						notiInfoVo.add((NotificationInfoVo) object);
					}
				}
			} else {
				final Object[] params = { custNum };
				for (Object object : getResultList(session,
						SQLConstants.SQL_GET_NOTIFICATION_DETAILS_ONE_OR_LESS,
						params, new NotificationInfoMapper())) {
					if (null != object) {
						notiInfoVo.add((NotificationInfoVo) object);
					}
				}
			}
			if(null!=notiInfoVo && notiInfoVo.size()>0){
				notiInfoVo =addDispositionDetails(custNum,notiInfoVo);
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getNotiInfo()");
		}
		return notiInfoVo;
	}
	
	private List<NotificationInfoVo> addDispositionDetails(String custNum,List<NotificationInfoVo> notiInfoVo)
 throws DashboardException {
		Session session = null;
		List<NotificationInfoVo> notiVoList = new ArrayList<NotificationInfoVo>();
		String segIds = "";
		String subSegIds = "";
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_1);
			StringBuilder query = new StringBuilder(
					SQLConstants.SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_1);

			if (null != custNum && !("".equals(custNum))
					&& custNum.length() < Constants.TEN) {
				String format = String.format("%%0%dd", 10);
				custNum = String.format(format, Integer.parseInt(custNum));
			}
			for (NotificationInfoVo vo : notiInfoVo) {
				if (null != vo.getSegmentId()
						&& !("".equals(vo.getSegmentId()))) {
					String format = String.format("%%0%dd", 3);
					String subSegId = String.format(format, Integer.parseInt(vo.getSegmentId()));
					subSegIds = subSegIds.concat(subSegId
							+ Constants.COMMA);
				}
				if (null != vo.getSementType()
						&& !("".equals(vo.getSementType()))) {
					if (segIds.indexOf(vo.getSementType().toUpperCase()) == -1)
						segIds = segIds.concat(Constants.SINGLE_QUOTE
								+ vo.getSementType().toUpperCase()
								+ Constants.SINGLE_QUOTE + Constants.COMMA);
				}
			}
			segIds = segIds.substring(0, segIds.lastIndexOf(Constants.COMMA));
			subSegIds = subSegIds.substring(0,
					subSegIds.lastIndexOf(Constants.COMMA));
			query.append(subSegIds)
					.append(SQLConstants.SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_2)
					.append(segIds+")) AND CTA_SUB_SEGMENT IN("+subSegIds
							+ SQLConstants.SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_3);
					
			SQLQuery sqlQuery = session.createSQLQuery(query.toString());
			sqlQuery.setParameter(0, custNum);
			List<Map<String, Object>> returnList = sqlQuery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			notiInfoVo = new NotificationInfoMapper().updateNotificationVoList(
					returnList, notiInfoVo);
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getNotiInfo()");
		}
		return notiInfoVo;
	}
	/**
	 * Method implementation to fetch all customer info.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            selPlaySection
	 * @param String
	 *            [] selectedSubplayArr
	 * @param JQueryDataTableInputDTO
	 *            dataTableInput
	 * @return List<CustProfileVO>
	 */
	
		
	@Override
	@SuppressWarnings("unchecked")
	public CustomerListDTO getAllCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO dataTableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException {
        
		logger.trace("Entering method :--> getAllCustomerInfo()");
		List<CustProfileVO> custProfileVolist = new ArrayList<CustProfileVO>();
		List<CustProfileVO> filteredCustProfileVolist = new ArrayList<CustProfileVO>();
		List<CustProfileVO> returnList = new ArrayList<CustProfileVO>();
		CustomerListDTO CustomerDataDTO = new CustomerListDTO();
		List<String> deslectedList = new ArrayList<String>();
		List<String> playList = new ArrayList<String>();
		Map<String, String> playIdMap = new HashMap<String, String>();
		Map<String, String> playIdAll = new HashMap<String, String>();
		List<SubplayInfoVo> subPlayDetail = null;
		Set<String> idSet = new HashSet<String>();
		Session session = null;
		String playSecArr[] = new String[0];
		String subPlays = "", tempSubPlays = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SQLQuery sqlQuery = null;
		SQLQuery sqlQueryDesc = null;
		String[] selectedSubplay = null;
		boolean returnFlag = true;
		boolean playIdFlag = true;
		boolean AcctMgrFlag = false;
		StringBuilder SQL = new StringBuilder("");
		StringBuilder SQL_DYNAMIC = new StringBuilder("");
		int i = 0, j = 0;
		String filterType = null;
		String SQL_DYNAMIC_COUNT="";
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery repLvlQuery = session
					.createSQLQuery(SQLConstants.SQL_IF_REP_IS_ACCT_MANAGER);
			repLvlQuery.setParameter(0, accId);
			//repLvlQuery.setParameter(1, accId);
			//repLvlQuery.setParameter(2, accId);
			repLvlQuery.setCacheable(true);
			repLvlQuery.addScalar("ACT_FLAG", StringType.INSTANCE);
			List<String> lvlList = repLvlQuery.list();
			if (lvlList != null && !lvlList.isEmpty()
					&& lvlList.get(0).equalsIgnoreCase("TRUE")) {
				AcctMgrFlag = true;
			}

			if (!AcctMgrFlag) {
				paramMap.put("custNum", accId);
				CustomerDataDTO.setTotalCount(getTotalCount(session, accId,
						dataTableInput));
				CustomerDataDTO.setMgrFlag("TRUE");
				SQL_DYNAMIC
						.append(SQLConstants.SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_1);
				if (dataTableInput.getiSortCol_0() != null
						&& !dataTableInput.getiSortCol_0().equals("")
						&& !dataTableInput.getiSortCol_0().equals("PLAY_SEG") 
						&& !dataTableInput.getiSortCol_0().equals("CUSTOMER_NUMBER")) {
					SQL_DYNAMIC.append(dataTableInput.getiSortCol_0() + " "
							+ dataTableInput.getsSortDir_0() + " ,ROWNUM "
							+ dataTableInput.getsSortDir_0());
				} else {
					SQL_DYNAMIC.append(" ROWNUM ASC");
				}

				SQL_DYNAMIC
						.append(SQLConstants.SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_2);
				if (dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals("")) {
					SQL_DYNAMIC.append(" WHERE (MASTER_CUST_NUM LIKE '"
							+ dataTableInput.getsSearch()
							+ "%' OR UPPER(COMPANY_NAME) LIKE UPPER('%"
							+ dataTableInput.getsSearch() + "%'))");
				}
				if(dataTableInput.getiSortCol_0() != null
						&& dataTableInput.getiSortCol_0().equals("CUSTOMER_NUMBER")){
					SQL_DYNAMIC.append(" ORDER BY "+dataTableInput.getiSortCol_0()+" "+dataTableInput.getsSortDir_0());
				}
				SQL_DYNAMIC_COUNT=SQL_DYNAMIC.toString();
				SQL_DYNAMIC.append(" offset "+dataTableInput.getiDisplayStart()+" rows fetch next "+dataTableInput.getiDisplayLength()+" rows only");
				
				
				String [] columns = {
						 "MASTER_CUST_NUM", "COMPANY_NAME", "CUSTOMER_TYPE", "AGENT_EMAIL_ID", "CALL_ORDER", "S_LAST_CONTACTED_DATE",
						 "ACCT_MNGR_FLAG", "CUSTOMER_SEGMENT", "VAP_SCORE", "ACCOUNT_QUALIFY_SCORE", "ROW_NUM" 
				};
				for (Object object : getCacheableResultListParam(session,
						SQL_DYNAMIC.toString(), paramMap, columns, new CustomerMapper())) {
					if (null != object) {
						custProfileVolist.add((CustProfileVO) object);
					}
				}

				SQLQuery totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL_DYNAMIC_COUNT.toString() + ")");
				totalCustomerQuery.setCacheable(true);
				totalCustomerQuery.addScalar("TOTAL_CUST_COUNT", org.hibernate.type.BigDecimalType.INSTANCE);
				Set<Map.Entry<String, Object>> set = paramMap.entrySet();
				for (Map.Entry<String, Object> e : set) {
					if (e.getValue() instanceof Object []) {
						totalCustomerQuery.setParameterList(e.getKey(), (Object [])e.getValue());
					} else {
						totalCustomerQuery.setParameter(e.getKey(), e.getValue());
					}
				}
				Object objTotalCount = totalCustomerQuery.uniqueResult();
				
				
				CustomerDataDTO.setTotalCount(((BigDecimal)objTotalCount).intValue());
				CustomerDataDTO.setDisplayCount(((BigDecimal)objTotalCount).intValue());
				
			} else {

				Map<String, Object> paramMapForAgent = new HashMap<String, Object>();
				SQL.append(SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC);
				paramMapForAgent.put("accId", accId);
				
				if (selPlaySection !=null && (selPlaySection.equalsIgnoreCase("none") || 
						(selPlaySection.indexOf("Growth") < 0 && selPlaySection.indexOf("Retention") < 0 && 
						selPlaySection.indexOf("Expansion") < 0 && !selPlaySection.equalsIgnoreCase("ALL")))) {
					SQL.append(" AND cs.PLAY_ID_LIST IS NULL " );
				} else if (selPlaySection ==null || selPlaySection.equalsIgnoreCase("ALL") || 
						selPlaySection.equalsIgnoreCase("Growth,Retention,Expansion")) {
					
					if (selectedSubplayArr != null && selectedSubplayArr.length > 0) {
						SQL.append(" AND ( cs.PLAY_ID_LIST IS NULL OR EXISTS (" + 
										SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_PLAY+ " and '''' || SEG_NAME || '''' NOT IN (:segDesc) ) )" );
						paramMapForAgent.put("segDesc", selectedSubplayArr);
					}
				} else if (selPlaySection !=null && !selPlaySection.equalsIgnoreCase("Growth,Retention,Expansion")) {
					SQL.append( " AND EXISTS ( " + SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_PLAY );
					String [] segTypes = selPlaySection.split(",");
					if (segTypes != null && segTypes.length > 0) {
						SQL.append( " and SEG_TYPE IN (:segType) ");
						paramMapForAgent.put("segType", segTypes);
					}
					if (selectedSubplayArr != null && selectedSubplayArr.length > 0) {
						SQL.append( " and '''' || SEG_NAME || '''' NOT IN (:segDesc) " );
						paramMapForAgent.put("segDesc", selectedSubplayArr);
						
					}
					SQL.append(")");
				}
				
				SQL.append(SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_1);
				
				SQL.append(" WHERE 1=1 ");
				if (dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals("")) {
					SQL.append(" AND( MASTER_CUST_NUM LIKE '"
							+ dataTableInput.getsSearch()
							+ "%' OR UPPER(COMPANY_NAME) LIKE UPPER('%"
							+ dataTableInput.getsSearch() + "%')"
									/*+ " OR CUSTOMER_TYPE LIKE UPPER('%"+ dataTableInput.getsSearch_2() + "%')"*/
									+ ")");
					
				}
				
				if (dataTableInput.getiSortCol_0() != null
						&& !dataTableInput.getiSortCol_0().equals("")
						) {
					SQL.append(" ORDER BY " + dataTableInput.getiSortCol_0() + " "
							+ dataTableInput.getsSortDir_0() + ", CALL_ORDER ASC");
				} 
				
				String min_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + 1);
				String max_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + dataTableInput.getiDisplayLength());
				
				String queryForAgent = " select * " + 
				  " from ( select /*+ FIRST_ROWS( " + dataTableInput.getiDisplayLength() + ") */ " + 
				  " a.*, ROWNUM RNUM " +
				      " from ( " + SQL.toString() + " ) a " +
				      " where ROWNUM <= " + 
				      max_row_to_fetch + "  ) " + 
				" where RNUM  >=  " + min_row_to_fetch;

				final Object[] params = { accId };
				String [] columns = {
						"MASTER_CUST_NUM", "EMAIL_ID", "COMPANY_NAME", "CUSTOMER_TYPE", "AGENT_EMAIL_ID", "CALL_ORDER","S_LAST_CONTACTED_DATE",
						"PLAY_ID_LIST", "ACCT_MNGR_FLAG", "CUSTOMER_SEGMENT", "VAP_SCORE", "ACCOUNT_QUALIFY_SCORE",
						/*"QUAL_SCORE_ID", "QUAL_SCORE_NAME", "QUAL_SCORE_DESC", "VAP_SCORE_ID", "VAP_SCORE_TYPE",
						"VAP_SCORE_NAME", "VAP_SCORE_DESC"	,*/ "PLAY_SEGMENT", "RNUM"
				};
				
				for (Object object : getCacheableResultListParam(
				
				session, queryForAgent, paramMapForAgent, columns, new CustomerMapper())) {
					if (null != object) {

						custProfileVolist.add((CustProfileVO) object);
					}
				}
				SQLQuery totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString() + ")");
				totalCustomerQuery.setCacheable(true);
				totalCustomerQuery.addScalar("TOTAL_CUST_COUNT", org.hibernate.type.BigDecimalType.INSTANCE);
				Set<Map.Entry<String, Object>> set = paramMapForAgent.entrySet();
				for (Map.Entry<String, Object> e : set) {
					if (e.getValue() instanceof Object []) {
						totalCustomerQuery.setParameterList(e.getKey(), (Object [])e.getValue());
					} else {
						totalCustomerQuery.setParameter(e.getKey(), e.getValue());
					}
				}
				Object objTotalCount = totalCustomerQuery.uniqueResult();
				
				CustomerDataDTO.setCustomerVoList(custProfileVolist);
				CustomerDataDTO.setTotalCount(((BigDecimal)objTotalCount).intValue());
				CustomerDataDTO.setDisplayCount(((BigDecimal)objTotalCount).intValue());
				
				
			}// END of ELSE ACCT_MGR_FLG CONDITION

			CustomerDataDTO.setCustomerVoList(custProfileVolist);
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAllCustomerInfo()");
		}
		return CustomerDataDTO;
	}

	/**
	 * Method implementation to remove duplicate entries.
	 * 
	 * @param String
	 *            playSection
	 * @return String
	 */
	private String removeDuplicateEntries(String playSection) {
		if (null != playSection) {
			String strarr[] = StringUtils.delimitedListToStringArray(
					playSection, ",");
			strarr = StringUtils.removeDuplicateStrings(strarr);
			playSection = StringUtils.arrayToDelimitedString(strarr, ",");
		}
		return playSection;
	}

	/**
	 * Method implementation to fetch customer value.
	 * 
	 * @param String
	 *            custNum
	 * @return CustomerDataVo
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CustomerDataVo getCustomerValue(String custNum)
			throws DashboardException {
		logger.trace("Entering method :--> getAllCustomerInfo()");
		Session session = null;
		CustomerDataVo customerDataVO = new CustomerDataVo();
		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_CUSTOMER_VALUE);

			SQLQuery sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_CUSTOMER_VALUE);
			sqlQuery.setParameter(0, custNum);
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();

			if (list != null && !list.isEmpty()) {
				Map<String, Object> map = list.get(0);

				String custType = map.get("K_LTV_SCORE") == null ? null : map
						.get("K_LTV_SCORE").toString();
				String valueAtributionScore = map.get("VAP_SCORE") == null ? null
						: map.get("VAP_SCORE").toString();
				String qualificationScore = map.get("ACCOUNT_QUALIFY_SCORE") == null ? null
						: map.get("ACCOUNT_QUALIFY_SCORE").toString();

				customerDataVO.setCusRight(custType);
				customerDataVO.setValueAttributionScore(valueAtributionScore);
				customerDataVO.setQualificationScore(qualificationScore);
			} else {
			    	customerDataVO.setCusRight("NA");
				customerDataVO.setValueAttributionScore("NA");
				customerDataVO.setQualificationScore("NA");
			}

			return customerDataVO;
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAllCustomerInfo()");
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public CustomerListDTO getAllSamCustomerInfo(String accId,
			String selPlaySection, String[] selectedSubplayArr,
			JQueryDataTableInputDTO dataTableInput,String[] selectedQualScore,String[] selectedSegScore) throws DashboardException {
        
		logger.trace("Entering method :--> getAllCustomerInfo()");
		List<CustProfileVO> custProfileVolist = new ArrayList<CustProfileVO>();
		List<CustProfileVO> filteredCustProfileVolist = new ArrayList<CustProfileVO>();
		List<CustProfileVO> returnList = new ArrayList<CustProfileVO>();
		CustomerListDTO CustomerDataDTO = new CustomerListDTO();
		List<String> deslectedList = new ArrayList<String>();
		List<String> playList = new ArrayList<String>();
		Map<String, String> playIdMap = new HashMap<String, String>();
		Map<String, String> playIdAll = new HashMap<String, String>();
		List<SubplayInfoVo> subPlayDetail = null;
		Set<String> idSet = new HashSet<String>();
		Session session = null;
		String playSecArr[] = new String[0];
		String subPlays = "", tempSubPlays = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SQLQuery sqlQuery = null;
		SQLQuery sqlQueryDesc = null;
		String[] selectedSubplay = null;
		boolean returnFlag = true;
		boolean playIdFlag = true;
		boolean AcctMgrFlag = false;
		StringBuilder SQL = new StringBuilder("");
		StringBuilder SQL_DYNAMIC = new StringBuilder("");
		int i = 0, j = 0;
		String filterType = null;
		
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			/*SQLQuery repLvlQuery = session
					.createSQLQuery(SQLConstants.SQL_IF_REP_IS_ACCT_MANAGER);
			repLvlQuery.setParameter(0, accId);

			repLvlQuery.setCacheable(true);
			repLvlQuery.addScalar("ACT_FLAG", StringType.INSTANCE);
			List<String> lvlList = repLvlQuery.list();
			if (lvlList != null && !lvlList.isEmpty()
					&& lvlList.get(0).equalsIgnoreCase("TRUE")) {
				AcctMgrFlag = true;
			}*/

			if (!AcctMgrFlag) {
				paramMap.put("repId", accId);
				CustomerDataDTO.setTotalCount(getTotalLeadCount(session, accId,
						dataTableInput));
				CustomerDataDTO.setMgrFlag("TRUE");
				/*SQL_DYNAMIC			 
				.append(SQLConstants.SQL_GET_LEAD_DATA);*/
				SQL_DYNAMIC			 
				.append(SQLConstants.SQL_GET_LEAD_DATA);
				
				  /*SQL_DYNAMIC
						.append(SQLConstants.SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_1);*/
				if (dataTableInput.getiSortCol_0() != null
						&& !dataTableInput.getiSortCol_0().equals("")) {
					if(dataTableInput.getiSortCol_0().equals("CP.FIRST_NAME"))
						SQL_DYNAMIC.append(" order by case when UPPER("+dataTableInput.getiSortCol_0() + ") is null then UPPER(CP.LAST_NAME) else UPPER("+dataTableInput.getiSortCol_0() + ") end "
								+ dataTableInput.getsSortDir_0() );
					else
					SQL_DYNAMIC.append(" order by "+dataTableInput.getiSortCol_0() + " "
							+ dataTableInput.getsSortDir_0() );
				} else {
					//SQL_DYNAMIC.append("AND ROWNUM ASC");
				}

				/*-- SQL_DYNAMIC
						.append(SQLConstants.SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_2);
			    ---*/
				
				if (0 != (dataTableInput.getiDisplayStart()) && (dataTableInput.getsSearch() == null
						|| dataTableInput.getsSearch().equals(""))) {
					SQL_DYNAMIC
							.append(" ) CPMCP ) B where ( rownumber > "
									+ dataTableInput.getiDisplayStart()
									+ " AND rownumber <= "
									+ (dataTableInput.getiDisplayStart() + dataTableInput
											.getiDisplayLength())+")");
				}else if (0 == dataTableInput.getiDisplayStart() && (dataTableInput.getsSearch() == null
						|| dataTableInput.getsSearch().equals("")) ) {
					SQL_DYNAMIC
					.append(" ) CPMCP ) B where ( rownumber >= "
							+ dataTableInput.getiDisplayStart()
							+ " AND rownumber <= "
							+ (dataTableInput.getiDisplayStart() + dataTableInput
									.getiDisplayLength())+")");
				} else if (0 == dataTableInput.getiDisplayStart() && (dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals(""))) {
					SQL_DYNAMIC
					.append(" ) CPMCP where (CPMCP.CUSTOMER_NUMBER LIKE '"
							+ dataTableInput.getsSearch()
							+ "%' OR UPPER(CPMCP.COMPANY_NAME) LIKE UPPER('%"
							+ dataTableInput.getsSearch() + "%')) ) B where ( rownumber >= "
							+ dataTableInput.getiDisplayStart()
							+ " AND rownumber <= "
							+ (dataTableInput.getiDisplayStart() + dataTableInput
									.getiDisplayLength())+")");
				}else if(0 != dataTableInput.getiDisplayStart() && dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals("")){
					SQL_DYNAMIC
							.append(" ) CPMCP where (CPMCP.CUSTOMER_NUMBER LIKE '"
							+ dataTableInput.getsSearch()
							+ "%' OR UPPER(CPMCP.COMPANY_NAME) LIKE UPPER('%"
							+ dataTableInput.getsSearch() + "%')) ) B where ( rownumber > "
									+ dataTableInput.getiDisplayStart()
									+ " AND rownumber <= "
									+ (dataTableInput.getiDisplayStart() + dataTableInput
											.getiDisplayLength())+")");
				}
				/*if (dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals("")) {
					SQL_DYNAMIC.append(" AND B.CUSTOMER_NUMBER LIKE '"
							+ dataTableInput.getsSearch()
							+ "%'");
				}*/
				
				String [] columns = {
						 "CUSTOMER_NUMBER", "COMPANY_NAME", "CONTRACT_TYPE", "FIRST_NAME","LAST_NAME", "EMAIL", "PHONE_NUMBER","ROWNUM"
						 
				};
				for (Object object : getCacheableResultListParam(session,
						SQL_DYNAMIC.toString(), paramMap, columns, new LeadCustomerMapper())) {
					if (null != object) {
						custProfileVolist.add((CustProfileVO) object);
					}
				}

			} else {

				Map<String, Object> paramMapForAgent = new HashMap<String, Object>();
				SQL.append(SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC);
				paramMapForAgent.put("accId", accId);
				
				if (selPlaySection !=null && (selPlaySection.equalsIgnoreCase("none") || 
						(selPlaySection.indexOf("Growth") < 0 && selPlaySection.indexOf("Retention") < 0 && 
						selPlaySection.indexOf("Expansion") < 0 && !selPlaySection.equalsIgnoreCase("ALL")))) {
					SQL.append(" AND cs.PLAY_ID_LIST IS NULL " );
				} else if (selPlaySection ==null || selPlaySection.equalsIgnoreCase("ALL") || 
						selPlaySection.equalsIgnoreCase("Growth,Retention,Expansion")) {
					
					if (selectedSubplayArr != null && selectedSubplayArr.length > 0) {
						SQL.append(" AND ( cs.PLAY_ID_LIST IS NULL OR EXISTS (" + 
										SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_PLAY+ " and '''' || SEG_DESC || '''' NOT IN (:segDesc) ) )" );
						paramMapForAgent.put("segDesc", selectedSubplayArr);
					}
				} else if (selPlaySection !=null && !selPlaySection.equalsIgnoreCase("Growth,Retention,Expansion")) {
					SQL.append( " AND EXISTS ( " + SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_PLAY );
					String [] segTypes = selPlaySection.split(",");
					if (segTypes != null && segTypes.length > 0) {
						SQL.append( " and SEG_TYPE IN (:segType) ");
						paramMapForAgent.put("segType", segTypes);
					}
					if (selectedSubplayArr != null && selectedSubplayArr.length > 0) {
						SQL.append( " and '''' || SEG_DESC || '''' NOT IN (:segDesc) " );
						paramMapForAgent.put("segDesc", selectedSubplayArr);
						
					}
					SQL.append(")");
				}
				
				SQL.append(SQLConstants.SQL_GET_ALL_CUST_INFO_DYNAMIC_1);
				
				SQL.append(" WHERE 1=1 ");
				if (dataTableInput.getsSearch() != null
						&& !dataTableInput.getsSearch().equals("")) {
					SQL.append(" AND MASTER_CUST_NUM LIKE '"
							+ dataTableInput.getsSearch()
							+ "%' OR UPPER(COMPANY_NAME) LIKE UPPER('%"
							+ dataTableInput.getsSearch() + "%')");
					
				}

				 if  (selectedQualScore != null && selectedQualScore.length >0 ){
					 
					 SQL.append(" and QUAL_SCORE_ID in ("
								+ arrayToString(selectedQualScore)
								
								+ ")"); 
				 
					
				}
				 if  (selectedSegScore != null && selectedSegScore.length >0 ){
					 
					 SQL.append(" and VAP_SCORE_ID in ("
								+ arrayToString(selectedSegScore)
								
								+ ")"); 
				 
				}
				if (dataTableInput.getiSortCol_0() != null
						&& !dataTableInput.getiSortCol_0().equals("")
						) {
					SQL.append(" ORDER BY " + dataTableInput.getiSortCol_0() + " "
							+ dataTableInput.getsSortDir_0() + ", CALL_ORDER ASC");
				}
				
				String min_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + 1);
				String max_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + dataTableInput.getiDisplayLength());
				
				String queryForAgent = " select * " + 
				  " from ( select /*+ FIRST_ROWS( " + dataTableInput.getiDisplayLength() + ") */ " + 
				  " a.*, ROWNUM RNUM " +
				      " from ( " + SQL.toString() + " ) a " +
				      " where ROWNUM <= " + 
				      max_row_to_fetch + "  ) " + 
				" where RNUM  >=  " + min_row_to_fetch;

				final Object[] params = { accId };
				String [] columns = {
						"MASTER_CUST_NUM", "EMAIL_ID", "COMPANY_NAME", "CUSTOMER_TYPE", "AGENT_EMAIL_ID", "CALL_ORDER","S_LAST_CONTACTED_DATE",
						"PLAY_ID_LIST", "ACCT_MNGR_FLAG", "CUSTOMER_SEGMENT", "VAP_SCORE", "ACCOUNT_QUALIFY_SCORE",
						"QUAL_SCORE_ID", "QUAL_SCORE_NAME", "QUAL_SCORE_DESC", "VAP_SCORE_ID", "VAP_SCORE_TYPE",
						"VAP_SCORE_NAME", "VAP_SCORE_DESC"	, "PLAY_SEGMENT", "RNUM"
				};
				
				for (Object object : getCacheableResultListParam(
				
				session, queryForAgent, paramMapForAgent, columns, new CustomerMapper())) {
					if (null != object) {

						custProfileVolist.add((CustProfileVO) object);
					}
				}
				//session = custExoHeliosOwnSessionFactory.openSession();
				SQLQuery totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString() + ")");
				totalCustomerQuery.setCacheable(true);
				totalCustomerQuery.addScalar("TOTAL_CUST_COUNT", org.hibernate.type.BigDecimalType.INSTANCE);
				Set<Map.Entry<String, Object>> set = paramMapForAgent.entrySet();
				for (Map.Entry<String, Object> e : set) {
					if (e.getValue() instanceof Object []) {
						totalCustomerQuery.setParameterList(e.getKey(), (Object [])e.getValue());
					} else {
						totalCustomerQuery.setParameter(e.getKey(), e.getValue());
					}
				}
				Object objTotalCount = totalCustomerQuery.uniqueResult();
				
				CustomerDataDTO.setCustomerVoList(custProfileVolist);
				CustomerDataDTO.setTotalCount(((BigDecimal)objTotalCount).intValue());
				CustomerDataDTO.setDisplayCount(((BigDecimal)objTotalCount).intValue());
				
				
			}// END of ELSE ACCT_MGR_FLG CONDITION

			CustomerDataDTO.setCustomerVoList(custProfileVolist);
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAllCustomerInfo()");
		}
		return CustomerDataDTO;
	}
	
	
	
	/**
	 * Method implementation to fetch shipTo info.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ShipToVO>
	 */
	@Override
	public List<ShipToVO> getShipToInfo(String custNum)
			throws DashboardException {
		List<ShipToVO> shipToVO = new ArrayList<ShipToVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GET_SHIPTO_DETAILS;

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum, custNum };
			for (Object object : getResultList(session, SQL, params,
					new ShipToVOMapper())) {
				if (null != object) {
					shipToVO.add((ShipToVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getShipToInfo()");
		}
		return shipToVO;
	}

	/**
	 * Method implementation to fetch latest fiscal data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public String getLatestFiscalDate(String custNum) throws DashboardException {
		String latestFiscaldate = null;
		Session session = null;
		Connection conn = null;
		try {

			/*String SQL = "select FSC_PRD||FSC_YR from helios_own.d_time where cld_dt="
					+ " (select max(to_date(to_char(OHCRDT),'yyyymmdd')) "
					+ " from HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T"
					+ " where length(OHCRDT)=8 and OHCUST = " + custNum + " and UPPER(RTRIM(LTRIM(OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') )";*/
			String SQL= "SELECT LPAD (DT.FSC_PRD, 2, '0') || DT.FSC_YR AS MAXDATE "
					  +" FROM D_TIME DT   INNER JOIN (SELECT MAX (ORDERTRANDATE) AS ORDDATE"
					          +" FROM HELIOS_OWN.SALESTRAN  WHERE MASTERNUMBER = '" + custNum.trim() + "' AND TRANTYPE IN ('IO', 'OR')) OH"
					          +" ON DT.CLD_DT = OH.ORDDATE AND DT.TM_LVL = 'Day'";
			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };

			List list = session.createSQLQuery(SQL).list();

			if (list != null && !list.isEmpty()) {
				latestFiscaldate = (String) list.get(0);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLatestFiscalDate()");
		}
		return latestFiscaldate;
	}

	/**
	 * Method implementation to fetch latest fiscal data.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String getLatestFiscalDate1(String custNum)
			throws DashboardException {
		String latestFiscaldate = null;
		Session session = null;
		try {

			/*String SQL_Without_Fiscal = "select to_char(maxdate,'mm')||to_char(maxdate,'yyyy') from (SELECT MAX(ORDERTRANDATE) as maxdate"
					+ " FROM HELIOS_OWN.SALESTRAN"
					+ " WHERE "
					+ " MASTERNUMBER          = '" + custNum.trim() + "' and TRANTYPE IN ('IO', 'OR'))";*/

			String SQL_Without_Fiscal= "SELECT LPAD (DT.FSC_PRD, 2, '0') || DT.FSC_YR AS MAXDATE "
			  +" FROM D_TIME DT   INNER JOIN (SELECT MAX (ORDERTRANDATE) AS ORDDATE"
			          +" FROM HELIOS_OWN.SALESTRAN  WHERE MASTERNUMBER = '" + custNum.trim() + "' AND TRANTYPE IN ('IO', 'OR')) OH"
			          +" ON DT.CLD_DT = OH.ORDDATE AND DT.TM_LVL = 'Day'";

			session = custExoHeliosOwnSessionFactory.openSession();

			List list = session.createSQLQuery(SQL_Without_Fiscal).list();

			if (list != null && !list.isEmpty()) {
				latestFiscaldate = (String) list.get(0);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLatestFiscalDate()");
		}
		return latestFiscaldate;
	}

	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public String getLatestFiscalDateForLead(String custNum) throws DashboardException {
		String latestFiscaldate = null;
		Session session = null;
		Connection conn = null;
		try {
			/*String SQL ="SELECT FSC_PRD ||FSC_YR FROM helios_own.d_time WHERE cld_dt="
			+ " (SELECT MAX(TO_CHAR(ORDERTRANDATE)) FROM HELIOS_OWN.MV_SAM_ORDER_HEADER"
			+ " WHERE LENGTH(ORDERTRANDATE) =9  AND PARENT_NUMBER ="+custNum +")";*/
			String SQL ="SELECT MAX(TO_CHAR(ORDERTRANDATE,'MM-YYYY')) FROM HELIOS_OWN.MV_SAM_ORDER_HEADER"
			 +" WHERE LENGTH(ORDERTRANDATE) =9  AND PARENT_NUMBER ="+custNum +"";

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum };

			List list = session.createSQLQuery(SQL).list();

			if (list != null && !list.isEmpty()) {
				latestFiscaldate = (String) list.get(0);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLatestFiscalDate()");
		}
		return latestFiscaldate;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public ContactDateVO getLatestFiscalContactedDate() throws DashboardException {
		String latestFiscalContactedDate = null;
		Session session = null;
		Connection conn = null;
		ContactDateVO ContactDatevo=null;
		try {

			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_LATEST_CONTACTED_DETAILS);
			List<Map<String,Object>> returnList = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(null !=returnList && returnList.size() > Constants.ZERO)
				ContactDatevo = (ContactDateVO)((((new ContactDateMapper())).mapRow(returnList)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLatestFiscalDate()");
		}
		return ContactDatevo;
	}
	
	/**
	 * Method implementation to fetch customer number.
	 * 
	 * @param String
	 *            searchText
	 * @param String
	 *            acctId
	 * @return String
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String getCustomerNumber(String searchText, String acctId, String custNum,
			String empLevel) throws DashboardException {
		Session session = null;
		try {
			logger.trace("Entering method :--> getCustomerNumber()");
			session = custExoHeliosOwnSessionFactory.openSession();
			String hold_out_status = checkAccountHoldoutStatus(custNum,acctId);
			if(null!=hold_out_status && !("".equals(hold_out_status)) && "holdout".equals(hold_out_status))
				return hold_out_status;
			if (searchText != null && !"".equals(searchText)) {
				searchText = searchText.toUpperCase();
				searchText = searchText.trim();
			}
			if (acctId != null)
				acctId = acctId.trim();
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_SEARCH_CUSTOMER_IN_HIERERCHY);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerNo", custNum);
			paramMap.put("EMP_NUMBER", acctId);
			paramMap.put("SEARCH_TEXT", searchText);
			paramMap.put("EMP_LEVEL", empLevel);
			for (Object object : getResultListParam(session,
					SQLConstants.SQL_SEARCH_CUSTOMER_IN_HIERERCHY, paramMap,
					new AccountUserMapper())) {
				if (null != object) {
					AccountUserDTO userDetail = (AccountUserDTO) object;
					return userDetail.getCustomerNo()+"-"+userDetail.getAssignGrp();
				}
			}
			return "failure";
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getCustomerNumber()");
		}
	}

	/**
	 * Method implementation to fetch bought also bought recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @param String
	 *            orderContact
	 * @return List<BoughtAlsoBoughtInfoVO>
	 */
	@Override
	public List<BoughtAlsoBoughtInfoVO> getBoughtAlsoBoughtRecommendations(
			String custNum, String orderContact) throws DashboardException {
		List<BoughtAlsoBoughtInfoVO> boughtAlsoBoughtVo = new ArrayList<BoughtAlsoBoughtInfoVO>();

		Session session = null;

		try {
			String SQL = SQLConstants.GET_BOUGHT_ALSO_BOUGHT_RECOMMENDATIONS;

			session = custExoHeliosOwnSessionFactory.openSession();
			final Object[] params = { custNum, orderContact };
			for (Object object : getResultList(session, SQL, params,
					new BoughtAlsoBoughtVOMapper())) {
				if (null != object) {
					boughtAlsoBoughtVo.add((BoughtAlsoBoughtInfoVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return boughtAlsoBoughtVo;
	}

	/**
	 * Method implementation to fetch the Bought Also Bought Recommendations for
	 * users.
	 * 
	 * @param List
	 *            <String> recSkuList
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getBABRecommendationInfo(
			List<String> recSkuList) throws DashboardException {
		Session session = null;
		List<CustRecommendationVO> recommendationVO = new ArrayList<CustRecommendationVO>();

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			String SQL = SQLConstants.GET_RECOMMENDED_SKUINFO;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("skuList", recSkuList);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new CustRecommendationVOMapper())) {
				if (null != object) {
					recommendationVO.add((CustRecommendationVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getBABRecommendationInfo()");
		}
		return recommendationVO;
	}

	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Map
	 *            <String, Object> paramMap
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getResultListParam(Session session, String sql,
			Map<String, Object> paramMap, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		boolean sessionFlag = false;
		try {
			if (session == null) {
				sessionFlag = true;
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			Iterator<Entry<String, Object>> paramItr = paramMap.entrySet()
					.iterator();
			while (paramItr.hasNext()) {
				Map.Entry pair = paramItr.next();
				if (pair.getValue() instanceof List) {
					sqlQuery.setParameterList(pair.getKey().toString(),
							(List) pair.getValue());
				} else {
					sqlQuery.setParameter(pair.getKey().toString(),
							pair.getValue());
				}

			}
			sqlQuery.setCacheable(false);
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}

		} catch (HibernateException he) {
			logger.error("Error in method getResultListParam()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getResultListParam()", e);
			throw e;
		} finally {
			if (sessionFlag == true && session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultListParam()");
		}
		return resultList;
	}

	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Map
	 *            <String, Object> paramMap
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getCacheableResultListParam(Session session, String sql,
			Map<String, Object> paramMap, String [] scalars, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		boolean sessionFlag = false;
		try {
			if (session == null) {
				sessionFlag = true;
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			Iterator<Entry<String, Object>> paramItr = paramMap.entrySet()
					.iterator();
			while (paramItr.hasNext()) {
				Map.Entry pair = paramItr.next();
				if (pair.getValue() instanceof List) {
					sqlQuery.setParameterList(pair.getKey().toString(),
							(List) pair.getValue());
				} else if (pair.getValue() instanceof Object []) {
					sqlQuery.setParameterList(pair.getKey().toString(),
							(Object []) pair.getValue());
				} else {
					sqlQuery.setParameter(pair.getKey().toString(),
							pair.getValue());
				}

			}
			if(!(rowMapper instanceof CdmCustomerMapper) && !(rowMapper instanceof LastLiveContactAndHistoryMapper))
			sqlQuery.setCacheable(true);
			for (String columnAlias : scalars) {
				sqlQuery.addScalar(columnAlias, StringType.INSTANCE);
			}
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}

		} catch (HibernateException he) {
			logger.error("Error in method getResultListParam()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getResultListParam()", e);
			throw e;
		} finally {
			if (sessionFlag == true && session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultListParam()");
		}
		return resultList;
	}
	
	/**
	 * Method implementation to fetch recommendation view not bought.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommendationViewNotBoughtVO>
	 */
	@Override
	public List<RecommendationViewNotBoughtVO> getViewNotBoughtRecommendation(
			String custNum) throws DashboardException {
		Session session = null;
		List<RecommendationViewNotBoughtVO> recommendationVO = new ArrayList<RecommendationViewNotBoughtVO>();
		try {

			String SQL = SQLConstants.GET_VIEW_NOT_BOUGHT_RECOMMENDATIONS;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new RecommendationViewNotBoughtMapper())) {
				if (null != object) {
					recommendationVO
							.add((RecommendationViewNotBoughtVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getViewNotBoughtRecommendation()");
		}
		return recommendationVO;
	}

	/**
	 * Method implementation to fetch reorder recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	@Override
	public List<ReorderRecommendationVO> getReorderRecommendations(
			String custNum) throws DashboardException {
		Session session = null;
		List<ReorderRecommendationVO> recommendationVO = new ArrayList<ReorderRecommendationVO>();
		try {

			String SQL = SQLConstants.GET_REORDER_RECOMMENDATIONS;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new ReorderRecommendationMapper())) {
				if (null != object) {
					recommendationVO.add((ReorderRecommendationVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getReorderRecommendations()");
		}
		return recommendationVO;
	}
	/**
	 * Method implementation to fetch top 15 recommendations.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Map
	 *            <String, Object> paramMap
	 * @param OnlineRetailDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getOnlineRetailResultListParam(Session session, String sql, Map<String, Object> paramMap,
			OnlineRetailReorderRecommendationMapper rowMapper) throws HibernateException, Exception {
		logger.trace("Entering method :--> getOnlineRetailResultListParam");
		List<Object> resultList = new ArrayList<Object>();
		
		boolean sessionFlag = false;
		try {
			if (session == null) {
				sessionFlag = true;
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			Iterator<Entry<String, Object>> paramItr = paramMap.entrySet().iterator();
			
			while (paramItr.hasNext()) {
				Map.Entry pair = paramItr.next();
				if (pair.getValue() instanceof List) {
					sqlQuery.setParameterList(pair.getKey().toString(), (List) pair.getValue());
				} else {
					sqlQuery.setParameter(pair.getKey().toString(), pair.getValue());
				}

			}
			
			
			sqlQuery.setCacheable(false);
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} catch (HibernateException he) {
			logger.error("Error in method getOnlineRetailResultListParam()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getOnlineRetailResultListParam()", e);
			throw e;
		} finally {
			if (sessionFlag == true && session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getOnlineRetailResultListParam()");
		}
		return resultList;

	}

	/**
	 * Method implementation to fetch reorder recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<ReorderRecommendationVO>
	 */
	@Override
	public List<OnlineRetailReorderRecommendationVO> getOnlineRetailReorderRecommendations(String custNum)
			throws DashboardException {
		Session session = null;

		List<OnlineRetailReorderRecommendationVO> recommendationVO = new ArrayList<OnlineRetailReorderRecommendationVO>();
		try {

			String SQL = SQLConstants.GET_ONLINE_RETAIL_REORDER_RECOMMENDATIONS;
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(null !=custNum && !("".equals(custNum)) && custNum.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custNum= String.format(format, Integer.parseInt(custNum));
			}
			paramMap.put("custNum", custNum);
			for (Object object : getOnlineRetailResultListParam(session, SQL, paramMap,
					new OnlineRetailReorderRecommendationMapper())) {
				if (null != object) {
					recommendationVO.add((OnlineRetailReorderRecommendationVO) object);
				}
			}
			

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getReorderRecommendations()");
		}
		return recommendationVO;
	}

		/**
	 * Method implementation to fetch order contacts.
	 * 
	 * @param String
	 *            custNum
	 * @return List<RecommOrderContactVO>
	 */
	@Override
	public List<RecommOrderContactVO> getOrderContacts(String custNum)
			throws DashboardException {
		Session session = null;
		List<RecommOrderContactVO> recommendationVO = new LinkedList<RecommOrderContactVO>();
		try {

			Date endDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, boughtAlsoBoughtTransactionDays);
			Date startDate = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			
			String SQL = SQLConstants.GET_RECOMM_ORDER_CONTACTS;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custNum", custNum);
			paramMap.put("startDate", sdf.format(startDate));
			paramMap.put("endDate", sdf.format(endDate));

			for (Object object : getResultListParam(session, SQL, paramMap,
					new RecommOrderContactMapper())) {
				if (null != object) {
					recommendationVO.add((RecommOrderContactVO) object);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getOrderContacts()");
		}
		return recommendationVO;
	}

	/**
	 * Method implementation to fetch class recommendations.
	 * 
	 * @param String
	 *            custNum
	 * @return List<CustRecommendationVO>
	 */
	@Override
	public List<CustRecommendationVO> getClassRecommendations(String custNum)
			throws DashboardException {

		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		List<CustRecommendationVO> recommendationVO = new ArrayList<CustRecommendationVO>();
		try {

			String SQL = SQLConstants.GET_CLASS_RECOMMENDATIONS;

			session = custExoHeliosOwnSessionFactory.openSession();

			conn = sessionImpl.connection();
			stmt = conn.prepareCall(SQLConstants.GET_CLASS_RECOMMENDATIONS);

			stmt.setString(Constants.CUSTNUM, custNum);

			stmt.registerOutParameter(Constants.CURSOR_OUTPUT,
					OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = (ResultSet) stmt.getObject(Constants.CURSOR_OUTPUT);
			while (rs.next()) {
				CustRecommendationVO vo = new CustRecommendationVO();

				vo.setSkuNumber(rs.getString(MapperConstants.CUST_RECOM_SKU));
				vo.setItemDescription(rs
						.getString(MapperConstants.CUST_RECOM_SKU_NAME));

				vo.setThumbnail(rs
						.getString(MapperConstants.CUST_RECOM_THUMBNAIL));
				recommendationVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();

			/*
			 * Map<String, Object> paramMap = new HashMap<String, Object>();
			 * paramMap.put("custNum", custNum);
			 * 
			 * for (Object object : getResultListParam(session, SQL, paramMap,
			 * new CustRecommendationVOMapper())) { if (null != object) {
			 * recommendationVO.add((CustRecommendationVO) object); } }
			 */

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getClassRecommendations()");
		}

		return recommendationVO;
	}

	/**
	 * Method implementation to fetch account number.
	 * 
	 * @param String
	 *            custNum
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String getAccountNumber(String custNum) throws DashboardException {
		String accountNumber = null;
		String assignType = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			SQLQuery query = session
					.createSQLQuery(SQLConstants.SQL_GET_AGENT_EMAIL);

			query.setParameter("custNum", custNum.trim());
			List list = query.list();

			if (list != null && !list.isEmpty()) {
				accountNumber = (String) list.get(0);
			}
			if(null !=accountNumber){
				SQLQuery assignQuery=	session
				.createSQLQuery(SQLConstants.SQL_GET_ASSIGN_ACCOUNT_TYPE);
				assignQuery.setParameter("custNum", accountNumber.trim());
				List ls = assignQuery.list();
				if (ls != null && !ls.isEmpty()) {
					assignType = (String) ls.get(0);
					accountNumber=accountNumber.concat("##"+assignType);
				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAccountNumber()");
		}
		return accountNumber;
	}

	/**
	 * Method implementation to fetch sub play list.
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
		logger.trace("Entering method :--> getSubPlayList()");
		List<SubplayInfoVo> subPlayInfoVo = new ArrayList<SubplayInfoVo>();
		String plays = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			if (forPlayType.equals("ALL")) {
				plays = "'Growth','Retention','Expansion'";
			} else {
				plays = forPlayType;
			}

			if (null != plays
					&& !(("").equals(plays))
					&& ((plays.indexOf(",") != -1) || (plays.indexOf("'") != -1))) {

				final Object[] params = { plays };
				final String[] columns = { "seg_type", "seg_id" };
				for (Object object : getResult(session, new StringBuilder(
						SQLConstants.SQL_GET_SUBPLAY_DETAILS), params, columns,
						new SubPlayInfoMapper())) {
					if (null != object) {
						subPlayInfoVo.add((SubplayInfoVo) object);
					}
				}

			}
			//Map<String, List<Object>> CustSeg_QualData= getFilterData();

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getSubPlayList()");
		}
		return subPlayInfoVo;
	}

	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Object
	 *            [] params
	 * @param String
	 *            [] columns
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getResult(Session session, StringBuilder sql,
			Object[] params, String[] columns,
			StaplesDashBoardRowMapper rowMapper) throws HibernateException,
			Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		final String CLOSE_BRACKET = ")";
		final String OPEN_BRACKET = "(";
		try {

			SQLQuery sqlQuery = null;

			sql.append(columns[0] + " IN " + OPEN_BRACKET
					+ ((String) params[0]) + CLOSE_BRACKET);
			sqlQuery = session.createSQLQuery(sql.toString());
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = new SubPlayInfoMapper().mapRow(list);
			}

		} catch (HibernateException he) {
			logger.error("Exception in method getResult() " + he.getMessage(),
					he);
			throw he;

		} catch (Exception e) {
			logger.error("Exception in method getResult() " + e.getMessage(), e);
			throw e;

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultList()");
		}
		return resultList;
	}

	/**
	 * Method implementation to fetch agent's rep role code.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String getAgentRepRoleCd(String agentId) throws DashboardException {
		Session session = null;
		String repRoleCd = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(SQLConstants.SQL_GET_AGENT_REPROLECD);
			query.setParameter("agentId", agentId);
			List list = query.list();

			if (list != null && !list.isEmpty()) {
				repRoleCd = (String) list.get(0);
				return repRoleCd;
			}

			return "";
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAgentRepRoleCd()");
		}
	}

	/**
	 * Method implementation to fetch agent's rep role code.
	 * 
	 * @param String
	 *            agentId
	 * @return String
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String getAgentName(String agentId) throws DashboardException {
		Session session = null;
		String agentName = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(SQLConstants.SQL_GET_AGENT_NAME);
			query.setParameter("agentId", agentId);
			List list = query.list();

			if (list != null && !list.isEmpty()) {
				agentName = (String) list.get(0);
				return agentName;
			}

			return "failure";
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getAgentName()");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.staples.dashboard.app.dao.dbvo.CustProfileDAO#getLastRefreshTime(
	 * java.lang.String)
	 */
	@Override
	public String getLastRefreshTime(String processName)
			throws DashboardException {
		// TODO Auto-generated method stub
		Session session = null;
		String lastRefreshdate = "";
		try {
			if (processName != null && !processName.equals("")) {
				session = custExoHeliosOwnSessionFactory.openSession();
				SQLQuery query = session
						.createSQLQuery(SQLConstants.SQL_GET_LAST_REFRESH_TIME_NEW);
				query.setParameter("processName", processName);
				List list = query.list();

				if (list != null && !list.isEmpty()) {
					lastRefreshdate = (String) list.get(0);
				}
			}

			return lastRefreshdate;

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLastRefreshTime()");
		}

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.staples.dashboard.app.dao.dbvo.CustProfileDAO#getCurrentRefreshTime(
	 * java.lang.String)
	 */
	@Override
	public String getCurrentRefreshTime()
			throws DashboardException {
		// TODO Auto-generated method stub
		Session session = null;
		String currentRefreshTime = "";
		try {
			
				session = custExoHeliosOwnSessionFactory.openSession();
				SQLQuery query = session
						.createSQLQuery(SQLConstants.SQL_GET_CURRENT_REFRESH_TIME);
				List list = query.list();

				if (list != null && !list.isEmpty()) {
				    currentRefreshTime = (String) list.get(0);
				}
			return currentRefreshTime;

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getCurrentRefreshTime()");
		}
	}

	@Override
	public List<ShipToDetailsVo> getShipToDetails(String shipTo, String custNum) {
		Session session = null;
		List<ShipToDetailsVo> shipToDetails = new ArrayList<ShipToDetailsVo>();
		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			final Object[] params = { custNum, shipTo,custNum };
			for (Object object : getResultList(session,
					SQLConstants.SQL_GET_SHIPTO_INFO, params,
					new ShipToDetailsVOMapper())) {
				if (null != object) {
					ShipToDetailsVo shipToDetailVO = (ShipToDetailsVo) object;
					/*
					 * List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO =
					 * getOrderDetailsShipTo( custNum,
					 * shipToDetailVO.getOrderNumber(),
					 * shipToDetailVO.getOrderDate().substring(0, 10));
					 */
					/*
					 * shipToDetailVO
					 * .setPurchaseRwdsDetailsVO(purchaseRwdsDetailsVO);
					 */
					shipToDetails.add(shipToDetailVO);
				}
			}

			return shipToDetails;
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getShipToDetails()");
		}
	}

	/**
	 * @param session
	 * @param accId
	 * @param dataTableInput
	 * @return
	 */
	private int getTotalCount(Session session, String accId,
			JQueryDataTableInputDTO dataTableInput) {

		StringBuilder SQL = new StringBuilder("");
		SQLQuery sqlquery = null;
		boolean sessionFlag = false;
		int returnVal = 0;

		SQL.append(SQLConstants.SQL_GET_ALL_CUST_COUNT);

		if (dataTableInput != null && dataTableInput.getsSearch() != null
				&& !dataTableInput.getsSearch().equals("")) {
			SQL.append(" AND (CUSTOMER_NUMBER LIKE '"
					+ dataTableInput.getsSearch()
					+ "%' OR UPPER(COMPANY_NAME) LIKE UPPER('%"
					+ dataTableInput.getsSearch() + "%'))");
		}

		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
				sessionFlag = true;
			}
			sqlquery = session.createSQLQuery(SQL.toString());
			sqlquery.setParameter("custNum", accId);
			List<BigDecimal> resultList = sqlquery.list();

			returnVal = resultList.get(0).intValue();

		} catch (HibernateException he) {
			logger.error("Error in method getTotalCount()", he);

		} catch (Exception e) {
			logger.error("Error in method getTotalCount()", e);

		} finally {
			if (session != null && session.isOpen() && sessionFlag)
				session.close();
			logger.trace("Exiting method :--> getTotalCount()");
		}
		return returnVal;
	}

	/**
	 * This method is used to get the details of subplays on the basis of whose
	 * the customer list has to be filtered.
	 * 
	 * @param playList
	 *            Play selected in dropdown filter
	 * @param subPlayList
	 *            subplays selected in dropdown filter
	 * @return List of Subplays
	 */
	private List<SubplayInfoVo> getSelectedPlayDetails(List<String> playList,
			List<String> subPlayList) {

		boolean playFlag = true;
		Session session = null;
		List<SubplayInfoVo> subPlayDetailList = new ArrayList<SubplayInfoVo>();
		try {
			String SQL = null;

			if (playList.isEmpty() || playList.get(0).equalsIgnoreCase("ALL")) {
				playFlag = false;
				SQL = SQLConstants.SQL_GET_SELECTED_SEGMENT_DETAILS_DESC;
			} else {
				SQL = SQLConstants.SQL_GET_SELECTED_SEGMENT_DETAILS;
			}

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subPlayList", subPlayList);
			if (playFlag) {
				paramMap.put("playList", playList);
			}

			for (Object object : getResultListParam(session, SQL, paramMap,
					new SubPlayInfoMapper())) {
				if (null != object) {
					subPlayDetailList.add((SubplayInfoVo) object);
				}
			}

		} catch (HibernateException he) {
			logger.error("Error in method getSelectedPlayDetails()", he);

		} catch (Exception e) {
			logger.error("Error in method getSelectedPlayDetails()", e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getSelectedPlayDetails()");
		}
		return subPlayDetailList;
	}

	

	@Override
	public List<PurchaseHeliosDetailsVO> getOrderDetailsShipTo(String custNum,
			String orderNum, String orderDate) {
		logger.trace("Entering method :-->  getOrderDetailsShipTo()");
	//List<SuperUserInfoVO> superUserInfoVO = new ArrayList<SuperUserInfoVO>();
		List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO = new ArrayList<PurchaseHeliosDetailsVO>();
		Session session = null;
		try {
			
			session = custExoHeliosOwnSessionFactory.openSession();
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_ORDER_DETAIL_SHIPTO);

			final Object[] params = { custNum,orderNum };
			for (Object object : getResultList(session,
					SQLConstants.SQL_GET_ORDER_DETAIL_SHIPTO, params,
					new PurchaseHeliosDetailsVOMapper())) {
				if (null != object) {
					purchaseRwdsDetailsVO.add((PurchaseHeliosDetailsVO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :-->  getOrderDetailsShipTo()");
		}

		return purchaseRwdsDetailsVO;
	}

	/*@Override
	public List<PurchaseHeliosDetailsVO> getOrderDetailsShipTo(String custNum,
			String orderNum, String orderDate) {
		List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO = new ArrayList<PurchaseHeliosDetailsVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		ResultSet rs = null;

		try {

			session = custExoHeliosOwnSessionFactory.openSession();
			sessionImpl = (SessionImpl) session;
			conn = sessionImpl.connection();

			stmt = conn.prepareCall(SQLConstants.SQL_GET_ORDER_DETAIL_SHIPTO);

			stmt.setString(Constants.CUSTNUM, custNum);
			stmt.setString(Constants.ORDERNUM, orderNum);
			stmt.setString(Constants.ORDERDATE, orderDate.replace("-", ""));
			stmt.registerOutParameter(Constants.CURSOR_OUTPUT,
					OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(Constants.CURSOR_OUTPUT);

			while (rs.next()) {
				PurchaseHeliosDetailsVO vo = new PurchaseHeliosDetailsVO();

				vo.setSkuNumber(rs.getString(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NUM)
								.toString());
				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NAME)
								.toString());
				vo.setTranDate(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_DATE)
								.toString());
				vo.setTranId(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER)
								.toString());
				vo.setTotalQty(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_QTY)
								.toString());
				vo.setNetSpendAmount(rs
						.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND)
								.toString());

				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ITEM_DESC)
								.toString());

				vo.setTotalPriceAmount(rs
						.getString(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.RWDDETAIL_UNIT_SALE_PRICE)
								.toString());
				vo.setCategoryId(rs
						.getString(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO
						: Float.valueOf(rs
								.getString(
										MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD)
								.toString()));

				purchaseRwdsDetailsVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getPurchaseRewardsDetails()");
		}
		return purchaseRwdsDetailsVO;
	} */

    public Map<String, List<Object>> getFilterData() {
	Map<String, List<Object>> filterData = new HashMap<String, List<Object>>();
	Session session = null;
	try {
	    session = custExoHeliosOwnSessionFactory.openSession();
	    List<Object> vapScoreVoList = new ArrayList<Object>();
	    for (Object object : getResultList(session,
		    SQLConstants.SQL_GET_ALL_VAP_SCORE, null,
		    new VapScoreMapper())) {
		if (null != object) {
		    vapScoreVoList.add((VapScoreVO) object);
		}
	    }

	    session = custExoHeliosOwnSessionFactory.openSession();
	    List<Object> qualScoreVoList = new ArrayList<Object>();
	    for (Object object : getResultList(session,
		    SQLConstants.SQL_GET_ALL_QUALIFY_SCORE, null,
		    new QualScoreMapper())) {
		if (null != object) {
		    qualScoreVoList.add((QualScoreVO) object);
		}
	    }

	    filterData.put("vapScore", vapScoreVoList);
	    filterData.put("qualifyScore", qualScoreVoList);
	} catch (HibernateException he) {
	    throw new DashboardException(he);
	} catch (Exception e) {
	    throw new DashboardException(e);
	} finally {
	    if (session != null && session.isOpen())
		session.close();
	    logger.trace("Exiting method :--> getAccountNumber()");
	}
	return filterData;
    }
    public String arrayToString(String[] stringArray){
		StringBuilder buff = new StringBuilder();
		for(String str : stringArray){
			buff.append(str+",");
		
		
		}
		return buff.toString().length() > 0 ? buff.toString().substring(0, buff.toString().length() - 1): "";
	}
	@Override
	public String getMasterAccountZipCode(String customerNumber)
			throws DashboardException {
		Session session = null;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			SQLQuery sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_MASTER_ACCOUNT_ZIP);
			sqlQuery.setParameter(0, customerNumber);

			Object obj = sqlQuery.uniqueResult();

			return obj == null ? null : obj.toString();

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
	}

	@Override
	public boolean getPremiumUser(String customerNumber) throws DashboardException  {
		// TODO Auto-generated method stub
		Session session = null;
		boolean isUser = false;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();

			SQLQuery sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_PREMIUM_CUST);
			sqlQuery.setParameter(0, customerNumber);

			Object obj = sqlQuery.uniqueResult();

			if(obj != null && !obj.equals("")){
				isUser = true;
			}
			return isUser;

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
	}
	
	
	public SbaDiffDetailsVo getSbaDiffDetails(String custId) throws DashboardException{
		SbaDiffDetailsVo sbaDiffDetailsVo = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_ADOPTION_METRICS_DETAILS);
			sqlquery.setParameter(0, custId);
			List<Map<String,Object>> sbaVolist = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			sbaDiffDetailsVo = (SbaDiffDetailsVo)(((new SbaDiffDetailsMapper()).mapRow(sbaVolist)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return sbaDiffDetailsVo; 
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public String getLatestOrderReturnDate(String custNum) throws DashboardException {
		String latestOrderRetDate = null;
		Session session = null;
		try {

			
			/*String SQL_Latest_Order_Returned_Date = "SELECT DATE_1 ||'~' || DATE_2 AS MAXDATE FROM ( "
					+ "select (select to_char(maxdate,'mm')||'/'||to_char(maxdate,'DD')||'/'||to_char(maxdate,'YYYY') maxdate from (SELECT MAX(to_date(TO_CHAR(OHCRDT),'yyyymmdd')) as maxdate "
					+ "		 FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T "
					+ "WHERE LENGTH(OHCRDT)=8 "
					+ " AND OHCUST = ? "
					+ " and UPPER(RTRIM(LTRIM(OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ'))) DATE_1, "
					+ "(select to_char(to_date(max(OH.OHCRDT), 'YYYYMMDD'), 'MM/DD/YYYY') maxdate from HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OH inner join  HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T OL "
					+ " on OH.OHORD# = OL.ODORD# and OH.OHCUST = ? and UPPER(RTRIM(LTRIM(OH.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') "
					+ " and (CASE WHEN OL.ODCONV > 1 THEN OL.ODAPRC / OL.ODCONV ELSE OL.ODAPRC END  * OL.ODSQTY) < 0) AS DATE_2 FROM DUAL) "*/
			String SQL_Latest_Order_Returned_Date = "WITH DT " 
	        +"AS (SELECT TM_KY, CLD_DT, FSC_YR, FSC_PRD, FSC_WK, FSC_DY, TM_LVL  FROM HELIOS_OWN.D_TIME "
	             +"WHERE TM_LVL = 'Day' AND CLD_DT IS NOT NULL), " 
	        +"PREV_FSC_PRD (CLD_DT, FSC_PRD) "
	        +"AS (SELECT DT2.CLD_DT CLD_DT, DT2.FSC_PRD " 
	          +"FROM DT DT1, DT DT2 WHERE     DT1.CLD_DT > DT2.CLD_DT "
	                   +"AND DT1.FSC_PRD <> DT2.FSC_PRD AND DT2.FSC_WK = '1' " 
	                   +"AND DT2.FSC_DY = '1' AND TRUNC (DT1.CLD_DT) = TRUNC (SYSDATE) "
	                   +"AND DT2.TM_LVL = 'Day' AND DT2.CLD_DT IS NOT NULL ORDER BY DT2.CLD_DT DESC) "
	 +"select to_char(to_date(max(OH.OHCRDT), 'YYYYMMDD'), 'MM/DD/YYYY') maxdate from HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OH inner join  HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T OL " 
	 +"on OH.OHORD# = OL.ODORD# and OH.OHCUST = ? and UPPER(RTRIM(LTRIM(OH.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') " 
     +"and (CASE WHEN OL.ODCONV > 1 THEN OL.ODAPRC / OL.ODCONV ELSE OL.ODAPRC END  * OL.ODSQTY) < 0 and "
	 +"OL.ODCRDT >= (select to_number(to_char((CLD_DT ), 'YYYYMMDD')) as FP_CLD_DT FROM (SELECT  P.*, ROWNUM as RN FROM PREV_FSC_PRD P WHERE ROWNUM <= 3) WHERE RN = 3) ";

			session = custExoHeliosOwnSessionFactory.openSession();

			SQLQuery sqlquery = session.createSQLQuery(SQL_Latest_Order_Returned_Date);
			sqlquery.setParameter(0, custNum);
			//sqlquery.setParameter(1, custNum);
            List list=sqlquery.list();
			if (list != null && !list.isEmpty()) {
				latestOrderRetDate = (String) list.get(0);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getLatestFiscalDate()");
		}
		return latestOrderRetDate;
	}
	
	/**
	 * @param session
	 * @param accId
	 * @param dataTableInput
	 * @return
	 */
	private int getTotalLeadCount(Session session, String accId,
			JQueryDataTableInputDTO dataTableInput) {

		StringBuilder SQL = new StringBuilder("");
		SQLQuery sqlquery = null;
		boolean sessionFlag = false;
		int returnVal = 0;

		SQL.append(SQLConstants.SQL_GET_ALL_LEAD_CUST_COUNT);

		if (dataTableInput != null && dataTableInput.getsSearch() != null
				&& !dataTableInput.getsSearch().equals("")) {
			SQL.append(" AND (CUSTOMER_NUMBER LIKE '"
					+ dataTableInput.getsSearch()
					+ "%' OR UPPER(COMPANY_NAME) LIKE UPPER('%"
					+ dataTableInput.getsSearch()
					+ "%') ))");
		}else{
			SQL.append(")");
		}

		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
				sessionFlag = true;
			}
			sqlquery = session.createSQLQuery(SQL.toString());
			sqlquery.setParameter("repId", accId);
			List<BigDecimal> resultList = sqlquery.list();

			returnVal = resultList.get(0).intValue();

		} catch (HibernateException he) {
			logger.error("Error in method getTotalCount()", he);

		} catch (Exception e) {
			logger.error("Error in method getTotalCount()", e);

		} finally {
			if (session != null && session.isOpen() && sessionFlag)
				session.close();
			logger.trace("Exiting method :--> getTotalCount()");
		}
		return returnVal;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public SavingsVo getSavingsInfo(String custId) throws DashboardException{
		SavingsVo savingsVo = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_SAVING_DETAILS);
			if(null !=custId && !("".equals(custId)) && custId.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custId= String.format(format, Integer.parseInt(custId));
			}
			sqlquery.setParameter(0, custId);
			List<Map<String,Object>> savingVolist = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(null !=savingVolist && savingVolist.size() > Constants.ZERO)
			savingsVo = (SavingsVo)(((new SavingsMapper()).mapRow(savingVolist)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return savingsVo; 
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Object> getCatPenInfo(String custId) throws DashboardException{
		List<Object> catpenVoObjList = new ArrayList<Object>();
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_CATEGORY_PENETRATION_DETAILS);
			if(null !=custId && !("".equals(custId)) && custId.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custId= String.format(format, Integer.parseInt(custId));
			}
			sqlquery.setParameter(0, custId);
			List<Map<String,Object>> catpenVolist = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(null !=catpenVolist && catpenVolist.size() > Constants.ZERO)
			catpenVoObjList = (((new CategoryPenetrationMapper()).mapRow(catpenVolist)));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return catpenVoObjList; 
	}
	
	public List<YTDSummaryVO> getYTDSummarySAM(String custNum, String selectedYear)
			throws DashboardException {
		List<YTDSummaryVO> ytdSummaryVO = new ArrayList<YTDSummaryVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		try {
			/*String startDate = selectedYear;
			String endDate = selectedYear;*/
			String SQL = SQLConstants.YTD_SPEND_CATEGORY;

			session = custExoHeliosOwnSessionFactory.openSession();
			
			/*final Object[] params = { custNum, shipTo,custNum };
			for (Object object : getResultList(session,
					SQLConstants.SQL_GET_SHIPTO_INFO, params,
					new ShipToDetailsVOMapper())) {
				if (null != object) {
					ShipToDetailsVo shipToDetailVO = (ShipToDetailsVo) object;
					
					 * List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO =
					 * getOrderDetailsShipTo( custNum,
					 * shipToDetailVO.getOrderNumber(),
					 * shipToDetailVO.getOrderDate().substring(0, 10));
					 
					
					 * shipToDetailVO
					 * .setPurchaseRwdsDetailsVO(purchaseRwdsDetailsVO);
					 
					shipToDetails.add(shipToDetailVO);
				}*/
			if(null !=custNum && !("".equals(custNum)) && custNum.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custNum= String.format(format, Integer.parseInt(custNum));
			}
			 final Object[] params = {custNum}; 
			 for(Object object : getResultList(session, SQL, params, new YTDSummaryVOMapper())) { 
				 if(null != object){
			      ytdSummaryVO.add((YTDSummaryVO) object); 
			     }
	         }

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			if (sessionImpl != null && sessionImpl.isOpen()) {
				sessionImpl.close();

			}
			logger.trace("Exiting method :--> getYTDInfo()");
		}
		return ytdSummaryVO;
	}
	@Override
	public List<PurchaseDetailsVO> getOrderHeaderSAM(String custNum) throws DashboardException {
		List<PurchaseDetailsVO> purchaseDetailsVO = new ArrayList<PurchaseDetailsVO>();
		Session session = null;
		String SQL = null;
		Object[] params = null;
		String [] columns = {"CUST_NUM", "ORDER_NUMBER","SOURCE_NUMBER", "ORDER_DATE","ORDER_LINE_COUNT", "ORDER_AMOUNT"};
	
		
		try {
			
				
					SQL = SQLConstants.SQL_GET_ORDER_HEADER_SAM;
					params = new Object[] { custNum };
				
				session = custExoHeliosOwnSessionFactory.openSession();
				
				for (Object object : getCacheableResultList(session, SQL, params, columns,
						new PurchaseDetailsVOMapper())) {
					if (null != object) {
						purchaseDetailsVO.add((PurchaseDetailsVO) object);
					}
				}
			
			

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {

			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getOrderHeaderSAM()");
		}
		return purchaseDetailsVO;
	}
	
	public List<PurchaseHeliosDetailsVO> getOrderDetailsSAM(
			String custNum, long [] orderNumberList) throws DashboardException {
		List<PurchaseHeliosDetailsVO> purchaseRwdsDetailsVO = new ArrayList<PurchaseHeliosDetailsVO>();
		Session session = null;
		Connection conn = null;
		CallableStatement stmt = null;
		SessionImpl sessionImpl = null;
		ResultSet rs = null;
		try {

			session = custExoHeliosOwnSessionFactory.openSession();
			sessionImpl = (SessionImpl) session;
			conn = sessionImpl.connection();
			
			
			
			stmt = conn
					.prepareCall("{ CALL HELIOS_OWN.SP_SAM_GET_ORDER_DETAIL(?,?,?) }");

			stmt.setString(1, custNum);
			Connection dconn = ((DelegatingConnection<Connection>) conn).getInnermostDelegate();
			Array  orderNumberArray = ((OracleConnection) dconn).createARRAY("HELIOS_OWN.NUM_ARRAY", orderNumberList);
			
			stmt.setArray(2, orderNumberArray);		 
			stmt.registerOutParameter(3,
					OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(3);
			
			while (rs.next()) {
				PurchaseHeliosDetailsVO vo = new PurchaseHeliosDetailsVO();

				vo.setSkuNumber(rs.getString(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NUM)
								.toString());
				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_SKU_NAME)
								.toString());
				/*vo.setTranDate(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_DATE)
								.toString());*/
				vo.setSrc(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER)
								.toString());
				vo.setTranId(rs
						.getString(MapperConstants.RWDDETAIL_TRAN_ID) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_TRAN_ID)
								.toString());
				vo.setTotalQty(rs
						.getString(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ORDER_QTY)
								.toString());
				vo.setNetSpendAmount(rs
						.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND)
								.toString());

				vo.setItemDescription(rs
						.getString(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING
						: rs.getString(MapperConstants.RWDDETAIL_ITEM_DESC)
								.toString());

				vo.setTotalPriceAmount(rs
						.getString(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING
						: rs.getString(
								MapperConstants.RWDDETAIL_UNIT_SALE_PRICE)
								.toString());
				vo.setCategoryId(rs
						.getString(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO
						: Float.valueOf(rs
								.getString(
										MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD)
								.toString()));

				purchaseRwdsDetailsVO.add(vo);
			}
			conn.close();
			stmt.close();
			rs.close();

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getOrderDetailsSAM()");
		}
		return purchaseRwdsDetailsVO;
	}

	/* (non-Javadoc)
	 * @see com.staples.dashboard.app.dao.dbvo.CustProfileDAO#generateSavingsReport(java.lang.String)
	 */
	@Override
	public List<SavingsDetailDTO> generateSavingsReport(String custNum)
			throws DashboardException {
		// TODO Auto-generated method stub
		List<SavingsDetailDTO> savingsVo = new ArrayList<SavingsDetailDTO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GENERATE_SAVINGS_REPORT;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(null !=custNum && !("".equals(custNum)) && custNum.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custNum= String.format(format, Integer.parseInt(custNum));
			}
			paramMap.put(ReportConstants.CUSTOMER_NUMBER, custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new SavingsReportMapper())) {
				if (null != object) {
					savingsVo
							.add((SavingsDetailDTO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return savingsVo; 
		//return null;
	}
	
	@Override
	public List<SavingsDetailDTO> generateSamSavingsReport(String custNum)
			throws DashboardException {
		// TODO Auto-generated method stub
		List<SavingsDetailDTO> savingsVo = new ArrayList<SavingsDetailDTO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GENERATE_SAM_SAVINGS_REPORT;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(null !=custNum && !("".equals(custNum)) && custNum.length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				custNum= String.format(format, Integer.parseInt(custNum));
			}
			paramMap.put(ReportConstants.CUSTOMER_NUMBER, custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new SavingsSDCReportMapper())) {
				if (null != object) {
					savingsVo
							.add((SavingsDetailDTO) object);
				}
			}
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return savingsVo; 
		//return null;
	}
	
	@Override
	public NephosConfig getNephosServiceConfig() throws DashboardException{
		NephosConfig config = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = null;
			
			sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_NEPHOS_PRICE_CONFIG);
			if (mmpivotPriceV4) {
				sqlquery.setParameter(0, MapperConstants.NEPHOS_PRICE_V4);
			} else {
				sqlquery.setParameter(0, MapperConstants.NEPHOS_PRICE_V2);
			}
			List<Map<String,Object>> returnList = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(null !=returnList && returnList.size() > Constants.ZERO)
				config = (NephosConfig)(((new NephosConfigMapper()).mapRow(returnList)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return config; 
	}
	
	
	@Override
	public NephosConfig getNephosArkeCISServiceConfig() throws DashboardException{
		NephosConfig config = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_NEPHOS_ARKE_CIS_CONFIG);
			List<Map<String,Object>> returnList = sqlquery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(null !=returnList && returnList.size() > Constants.ZERO)
				config = (NephosConfig)(((new NephosConfigMapper()).mapRow(returnList)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return config; 
	}
	
	
	public void insertPriceLog(PriceLog priceLog) throws DashboardException {
		logger.info("Begin Log Price****");
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			
			final SQLQuery sqlInsertQuery = session.createSQLQuery(SQLConstants.LOG_PRICE);
			sqlInsertQuery.setParameter(0, priceLog.getArkeCISRequest());
			sqlInsertQuery.setParameter(1, priceLog.getArkeCISResponse());
			sqlInsertQuery.setParameter(2, priceLog.getPriceRequest());
			sqlInsertQuery.setParameter(3, priceLog.getPriceResponse());
			sqlInsertQuery.setParameter(4, priceLog.getCustomerNumber());
			sqlInsertQuery.setParameter(5, priceLog.getLoggedInUser(), StandardBasicTypes.STRING);

			sqlInsertQuery.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean refreshAccessToken(String token, String serviceName) throws DashboardException {
		// TODO Auto-generated method stub
		String userActivityId = null;
		int numOfRecords;
		boolean returnVal=false;
		Session session = null;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			
			String insertQueryString = SQLConstants.SQL_REFRESH_NEPHOS_TOKEN;
			SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);

			sqlQuery.setParameter(0, token);
			sqlQuery.setParameter(1, serviceName);
			
			
			numOfRecords = sqlQuery.executeUpdate();
            if(numOfRecords >0){
            	returnVal=true;
            }
			return returnVal;
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			// logger.trace("Exiting method :--> getRole()");
		}
	}


	@Override
	public ConfigSfdcVO getSfdcConfig() throws DashboardException {
		// TODO Auto-generated method stub
		ConfigSfdcVO config = null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_GET_SFDC_CONFIG);
			List<Map<String, Object>> returnList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (null != returnList && returnList.size() > Constants.ZERO)
				config = (ConfigSfdcVO) (((new SfdcConfigMapper())
						.mapRow(returnList)).get(0));
		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return config;
	}

	@Override
	public SfdcInputId getSfdcId(InputTaskSalesforceDTO inputDTO)
			throws DashboardException {
		// TODO Auto-generated method stub
		SfdcInputId result = null;
		Session session = null;
		SQLQuery sqlquery = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			if (inputDTO != null && inputDTO.getContactId() != null && !inputDTO.getContactId().equals("")) {

				if (inputDTO.getLdapUserRole().equalsIgnoreCase("ADMIN")) {
					sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_ADMIN);
					sqlquery.setParameter(0, adminSFDCId.trim());

					sqlquery.setParameter(1, inputDTO.getContactId());
				} else {

					sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_1);
					sqlquery.setParameter(0, inputDTO.getRepId());
					sqlquery.setParameter(1, adminSFDCId.trim());
					sqlquery.setParameter(2, inputDTO.getContactId());
				}
			} else if (inputDTO != null && inputDTO.getCustomerNumber() != null
					&& !inputDTO.getCustomerNumber().equals("")) {

				if (inputDTO.getLdapUserRole().equalsIgnoreCase("ADMIN")) {
					sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_2_ADMIN);
					sqlquery.setParameter(0, adminSFDCId.trim());
					sqlquery.setParameter(1, inputDTO.getCustomerNumber());
				} else {

					sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_2);
					sqlquery.setParameter(0, inputDTO.getRepId());
					sqlquery.setParameter(1, adminSFDCId.trim());
					sqlquery.setParameter(2, inputDTO.getCustomerNumber());
				}

			}
			List<Map<String, Object>> returnList = sqlquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (null != returnList && returnList.size() > Constants.ZERO)
				result = (SfdcInputId) (((new SfdcInputIdMapper()).mapRow(returnList)).get(0));

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	public List<ContactDateVO> getContactedCustList() throws DashboardException{
		List<ContactDateVO> ContactDateVoList=null;
		ContactDateVO ContactDateVo=null;
		SQLQuery sqlQuery = null;
		Session session = null;
		try{
		session = custExoHeliosOwnSessionFactory.openSession();
		logger.debug("SQL Query :--> "
				+ SQLConstants.SQL_GET_ALL_CONTACTED_CUSTOMERS);
		
			sqlQuery = session
					.createSQLQuery(SQLConstants.SQL_GET_ALL_CONTACTED_CUSTOMERS);
			List<Map<String,Object>> returnList = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
		if (returnList != null && !returnList.isEmpty()) {
			ContactDateVoList=new ArrayList<ContactDateVO>();
			for (int i = 0; i < returnList.size(); i++) {
				ContactDateVo = (ContactDateVO) new ContactDateMapper()
						.mapSingleRow(returnList.get(i));
				ContactDateVoList.add(ContactDateVo);
			}
		}
	} catch (HibernateException he) {

		throw new DashboardException(he);
	} catch (Exception e) {

		throw new DashboardException(e);
	} finally {
		if (session != null && session.isOpen()) {
			session.close();
		}
		// logger.trace("Exiting method :--> getRole()");
	}
		return 	ContactDateVoList;
}


	public int deleteCustNumFromTaskTracker(String listCustId){
		int rowsDeleted=0;
		SQLQuery sqlQuery = null;
		Session session = null;
		String query=null;
		try{
		session = custExoHeliosOwnSessionFactory.openSession();
		logger.debug("SQL Query :--> "
				+ SQLConstants.SQL_DELETE_CUSTOMERS_FROM_TASK_TRACKER);
		query=SQLConstants.SQL_DELETE_CUSTOMERS_FROM_TASK_TRACKER;
		StringBuilder sb=new StringBuilder(query);
		sb.append(listCustId+")");
			sqlQuery = session
					.createSQLQuery(sb.toString());
			if(null!=listCustId)
				rowsDeleted=sqlQuery.executeUpdate();
				
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return rowsDeleted;
	}
	
	@Override
	public SegHdrText getSubCtaDeatils(String seg, String segId, String custNum) throws DashboardException {
		// TODO Auto-generated method stub
		SegHdrText segHdrText = null;
		List<String> hdrLableList=null;
		SegHeaderLabelsVO segHeaderLabelsVO=null;
		List<SegHeaderLabelsVO> segHeaderLabelsVOList=null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SELECT_SEG_HEADER_TEXT);
			SQLQuery sqlDispQuery = session
					.createSQLQuery(SQLConstants.SELECT_SEG_DISPOSITION);
			StringBuilder HdrLblQuery = new StringBuilder(
					SQLConstants.SELECT_SEG_HEADER_LABELS);
			logger.debug("SQL Query :--> "
					+ SQLConstants.SELECT_SEG_HEADER_TEXT);
			sqlquery.setParameter(0, seg);
			sqlquery.setParameter(1, segId);
			List<Map<String, Object>> returnList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (null != returnList && returnList.size() > Constants.ZERO)
				segHdrText = (SegHdrText) (((new CTAHdrTxtRowMapper())
						.mapRow(returnList.get(Constants.ZERO))));

			if (null != segHdrText) {
				String lablequery = HdrLblQuery.append(
						"(" + segHdrText.getHeaderIdsShown() + ")").toString();
				SQLQuery hdrLabelSqlquery = session.createSQLQuery(lablequery);
				logger.debug("SQL Query :--> " + lablequery);
				List<Map<String, Object>> list = hdrLabelSqlquery
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != list && list.size() > Constants.ZERO) {
					segHeaderLabelsVOList = new ArrayList<SegHeaderLabelsVO>();
					for (int i = 0; i < list.size(); i++) {
						segHeaderLabelsVO = (SegHeaderLabelsVO) ((new CTAHdrTxtRowMapper())
								.mapRow(list.get(i), i));
						segHeaderLabelsVOList.add(segHeaderLabelsVO);
					}
					segHdrText.setHdrLables(segHeaderLabelsVOList);
				}
				List<String> dispList = sqlDispQuery.list();
				if (null != dispList)
					segHdrText.setDispStatus(dispList);
				List<CustSfdcInfoVO> orderContactList = getCustSfdcInfo(custNum);
				segHdrText.setOrderContactList(orderContactList);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return segHdrText;
	}

	@Override
	public SegHdrText getAdminSubCtaDeatils(String seg, String segId) throws DashboardException {
		// TODO Auto-generated method stub
		SegHdrText segHdrText = null;
		List<String> hdrLableList=null;
		SegHeaderLabelsVO segHeaderLabelsVO=null;
		List<SegHeaderLabelsVO> segHeaderLabelsVOList=null;
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SELECT_SEG_HEADER_TEXT);
			SQLQuery sqlDispQuery = session
					.createSQLQuery(SQLConstants.SELECT_SEG_DISPOSITION);
			StringBuilder HdrLblQuery = new StringBuilder(
					SQLConstants.SELECT_SEG_HEADER_LABELS);
			logger.debug("SQL Query :--> "
					+ SQLConstants.SELECT_SEG_HEADER_TEXT);
			sqlquery.setParameter(0, seg);
			sqlquery.setParameter(1, segId);
			List<Map<String, Object>> returnList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (null != returnList && returnList.size() > Constants.ZERO)
				segHdrText = (SegHdrText) (((new CTAHdrTxtRowMapper())
						.mapRow(returnList.get(Constants.ZERO))));

			if (null != segHdrText) {
				String lablequery = HdrLblQuery.append(
						"(" + segHdrText.getHeaderIdsShown() + ")").toString();
				SQLQuery hdrLabelSqlquery = session.createSQLQuery(lablequery);
				logger.debug("SQL Query :--> " + lablequery);
				List<Map<String, Object>> list = hdrLabelSqlquery
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != list && list.size() > Constants.ZERO) {
					segHeaderLabelsVOList = new ArrayList<SegHeaderLabelsVO>();
					for (int i = 0; i < list.size(); i++) {
						segHeaderLabelsVO = (SegHeaderLabelsVO) ((new CTAHdrTxtRowMapper())
								.mapRow(list.get(i), i));
						segHeaderLabelsVOList.add(segHeaderLabelsVO);
					}
					segHdrText.setHdrLables(segHeaderLabelsVOList);
				}
				/*List<String> dispList = sqlDispQuery.list();
				if (null != dispList)
					segHdrText.setDispStatus(dispList);
				List<CustSfdcInfoVO> orderContactList = getCustSfdcInfo(custNum);
				segHdrText.setOrderContactList(orderContactList);*/
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return segHdrText;
	}
	public List<CustSfdcInfoVO> getCustSfdcInfo(String custNum) {

		Session session = null;
		List<CustSfdcInfoVO> infoVOs = new ArrayList<CustSfdcInfoVO>();
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CTAConstants.CUST_NUM_PARAM, custNum);
			for(Object object : getResultList(session, CTAConstants.SELECT_CUST_SFDC_INFO,params , new CustSfdcInfoMapper())){
				if(null != object){
					infoVOs.add((CustSfdcInfoVO)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return infoVOs;
	
	}
	
	@Override
	public String updateSubCtaDeatils(String segId, String subSegId,
			String headerIds, String headerContent, String headerCol) throws DashboardException {
		int numOfRecords;
		String updateStatus="";
		Session session = null;
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			
			
			/* FOR CTA*/
			
			StringBuilder query =new StringBuilder(SQLConstants.SQL_UPDATE_CTA_CONTENT); 
			query.append("HDR_"+headerCol+"_STATIC_TEXT = ? WHERE HEADER_IDS_SHOWN=? AND SEG =? AND SEG_ID =?");
			SQLQuery sqlUpdateQuery = session.createSQLQuery(query.toString());
			sqlUpdateQuery.setParameter(0, headerContent);
			sqlUpdateQuery.setParameter(1, headerIds);
			sqlUpdateQuery.setParameter(2, segId);
			sqlUpdateQuery.setParameter(3, subSegId);
			updateStatus=String.valueOf(sqlUpdateQuery.executeUpdate());
			
			/* FOR ALERT*/
			/*StringBuilder query =new StringBuilder("UPDATE HELIOS_OWN.ALERT_HEADER SET ALERT_TXT=? WHERE ALERT_ID IN('2','3','4')");
			SQLQuery sqlUpdateQuery = session.createSQLQuery(query.toString());
			sqlUpdateQuery.setParameter(0, headerContent);
			updateStatus=String.valueOf(sqlUpdateQuery.executeUpdate());*/
			
			/*SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			String sqlString = "query in here";
			PreparedStatement pst = conn.prepareStatement(sqlString);
			conn.commit();
			pst.close();*/
			
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			// logger.trace("Exiting method :--> getRole()");
		}
		return updateStatus;
	}

	@Override
	public List<DispositionDetailsVo> getDespositionDetails(String taskCombinationId,String frequency) {
		Session session = null;
		DispositionDetailsVo dispositionDetailsVo=null;
		List<DispositionDetailsVo> dispositionDetailsVoList=null;
		String taskId="";
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SELECT_DISP_COMMENT_DETAILS);
			if(null !=frequency){
			    if(frequency.equals("Weekly"))
					taskId=taskCombinationId.substring(0,taskCombinationId.length() - 1);
				else if(frequency.equals("Monthly"))
					taskId=taskCombinationId.substring(0,taskCombinationId.length() - 2);
				else
					taskId=taskCombinationId;
			}
            String availableTaskId=checkAvaliblityforTaskIdByTaskId(taskId);
			if (null != availableTaskId && !("".equals(availableTaskId))) {
				taskId = availableTaskId;
			} else {
				taskId = taskCombinationId;
			}
			sqlquery.setParameter(0, taskId);
		List<Map<String, Object>> returnList = sqlquery
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		if (null != returnList && returnList.size() > Constants.ZERO){
			dispositionDetailsVoList=new ArrayList<DispositionDetailsVo>();
			for(int count=0;count<returnList.size();count++){
				 dispositionDetailsVo = (DispositionDetailsVo) (((new DespositionDetailsMapper())
					.mapRow(returnList.get(count))));
				 dispositionDetailsVoList.add(dispositionDetailsVo);
			}
		}
		if(null!=dispositionDetailsVoList && null !=dispositionDetailsVoList.get(0) && null !=dispositionDetailsVoList.get(0).getDispositionStatus() && !("".equals(dispositionDetailsVoList.get(0).getDispositionStatus()))){
			SQLQuery sqlDispQuery = session
					.createSQLQuery(SQLConstants.SELECT_LINKTASK_BY_DISPOSITION);
			sqlDispQuery.setParameter(0, dispositionDetailsVoList.get(0).getDispositionStatus());
			dispositionDetailsVoList.get(0).setDispositionLinkId((String)(sqlDispQuery.uniqueResult()));
		}
	
	} catch (HibernateException he) {

		throw new DashboardException(he);
	} catch (Exception e) {

		throw new DashboardException(e);
	} finally {
		if (session != null && session.isOpen()) {
			session.close();
		}
		// logger.trace("Exiting method :--> getRole()");
	}
	return dispositionDetailsVoList;	

	}
	
	public String checkAvaliblityforTaskIdByTaskId(String taskComId){
		String taskIdCombination=null;
		Session session = null;
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(SQLConstants.SQL_CHECK_TASKID_AVAILABILITY_BY_TASKID+" LIKE '"+taskComId+"%' order by TASK_INSERT_DATE desc)where RowNum <=1");
			taskIdCombination = (String)(query.uniqueResult());
			System.out.println("--->"+taskIdCombination);
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return taskIdCombination;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> getResultList(Session session, String sql,
			Map<String, Object> params, CTABaseMapper rowMapper)
					throws HibernateException, Exception {
		List<Object> resultList = new ArrayList<Object>();
		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			
			if(params != null && params.size() > 0){
				for(Entry<String, Object> entrySet : params.entrySet()){
					sqlQuery.setParameter(entrySet.getKey(), entrySet.getValue());
				}
			}


			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return resultList;
	}

	public String refreshPlaySegmentParam(String custNum, String segId)
			throws DashboardException {
		// TODO Auto-generated method stub
		Session session = null;
		String status ="";
		int count=0;
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.SQL_CHECK_PARAM_AVAILABILITY);
			
			sqlquery.setParameter("CustNum", custNum);
			sqlquery.setParameter("segId", segId);
			List<Map<String, Object>> returnList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			
			SQLQuery sqlParamCount = session
					.createSQLQuery(SQLConstants.GET_PARAM_COUNT);
			sqlParamCount.setParameter(0, segId);
			List<BigDecimal> paramCount= sqlParamCount.list();
			if(null!=paramCount && null!=paramCount.get(0) && !("".equals(paramCount.get(0)))){
			 count=paramCount.get(0).intValue();
			}
			if(count>0){
			if(returnList!=null && returnList.size() > 0){
				status="no refresh required";
				//segId.equals("1") || segId.equals("3") || segId.equals("5") || segId.equals("20") || segId.equals("11") || segId.equals("24") || segId.equals("17") || segId.equals("4") || segId.equals("7") || segId.equals("12") || segId.equals("15") || segId.equals("16") || segId.equals("19") || segId.equals("21") || segId.equals("26") || segId.equals("27") || segId.equals("28") || segId.equals("8")
			}else{
				
				Query query = session.createSQLQuery(
						"CALL helios_own.PKG_CUSTOMER_SEGMENT.SP_LOAD_SEG_PARAM(:cusNum,:segId)")
						.setParameter("cusNum", custNum).setParameter("segId", segId);
				query.executeUpdate();;
			}
			}
		}catch(HibernateException he){
			
			throw new DashboardException(he);
		}catch(Exception e){
			throw new DashboardException(e);
		}finally{
			if (session != null && session.isOpen())
				session.close();
		}
		return status;
	}
	
	@Override
	public List<SegmentSubDetail> getSegSubDetail(String custNum, String segId, String subSegId) {
	Session session = null;
	List<SegmentSubDetail> subDetails = new ArrayList<SegmentSubDetail>();
	try{
	session = custExoHeliosOwnSessionFactory.openSession();
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("custNum",custNum);
	/*params.put("segId",segId);*/
	params.put("subSegId",subSegId);
	for(Object object : getResultList(session, SQLConstants.SELECT_SEGMENT_SUB_DTL,params , new SegmentSubDetailMapper())){
	if(null != object){
	subDetails.add((SegmentSubDetail)object);
	}
	}
	}catch (HibernateException he) {
	throw new DashboardException(he);

	} catch (Exception e) {
	throw new DashboardException(e);

	} finally {
	if (session != null && session.isOpen())
	session.close();

	}
	return subDetails;
	}

	@Override
	public List<SegMktgResources> getSegMktgResources(String segName,String subSegId, String mktResourceUrl) {
		Session session = null;
		List<SegMktgResources> mktgResources = new ArrayList<SegMktgResources>();
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("segName",segName);
			params.put("subSegId",subSegId);
			SegMktgResourcesMapper segMktgResourcesMapper=new SegMktgResourcesMapper();
			segMktgResourcesMapper.setMktResourceUrl(mktResourceUrl);
			for(Object object : getResultList(session, SQLConstants.SELECT_SEG_MKTG_RESOURCES , params , segMktgResourcesMapper)){
			if(null != object){
				mktgResources.add((SegMktgResources)object);
			}
			}
			}catch (HibernateException he) {
			throw new DashboardException(he);

			} catch (Exception e) {
			throw new DashboardException(e);

			} finally {
			if (session != null && session.isOpen())
			session.close();

			}
			return mktgResources;
	}

	@Override
	public CustomerListDTO getAllCdmCustomerInfo(JQueryDataTableInputDTO dataTableInput,String selTimeZone,String accId, String rowId, String alertStateStatus) {
		// TODO Auto-generated method stub
		Session session = null;
		List<CdmCustProfileVO> cdmCustProfileVolist = new ArrayList<CdmCustProfileVO>();
		CustomerListDTO CustomerDataDTO = new CustomerListDTO();
		StringBuilder SQL = new StringBuilder("");
		StringBuilder SQL_DYNAMIC = new StringBuilder("");
		boolean tz_flag=false;
		try {
		session = custExoHeliosOwnSessionFactory.openSession();
		Map<String, Object> paramMapForAgent = new HashMap<String, Object>();
		SQL.append(SQLConstants.SQL_GET_SUPER_USER_DETAILS_CDM);
		paramMapForAgent.put("accId", accId);
		
		/*String[] timeZoneTypes = selTimeZone.split(",");
		if ((timeZoneTypes != null && timeZoneTypes.length > 0) && !(selTimeZone.equals("ALL"))) {
				SQL.append(" and TIMEZONE IN (:timeZoneType) ");
			paramMapForAgent.put("timeZoneType", timeZoneTypes);
		}else if ((timeZoneTypes != null && timeZoneTypes.length > 0) && (selTimeZone.equals("ALL"))) {
			SQL.append(" and TIMEZONE IN ('EST','CST','PST','MST','NA') ");
	    }*/
	if (dataTableInput.getsSearch() != null
			&& !dataTableInput.getsSearch().equals("")) {
		SQL.append(" and ( MSCP.CUSTOMER_NUMBER LIKE '"
				+ dataTableInput.getsSearch()
				+ "%' OR UPPER( MSCP.COMPANY_NAME) LIKE UPPER('%"
				+ dataTableInput.getsSearch() + "%')"
						+ ")");
		
	}
		//SQL.append(SQLConstants.SQL_ORDER_BY_CDM);
		
		String min_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + 1);
		String max_row_to_fetch = String.valueOf(dataTableInput.getiDisplayStart() + dataTableInput.getiDisplayLength());
		String[] timeZoneTypes = selTimeZone.split(",");
		if ((timeZoneTypes != null && timeZoneTypes.length > 0) && !(selTimeZone.equals("ALL"))) {
			//SQL_DYNAMIC.append(" and TIMEZONE IN (:timeZoneType) ");
			SQL_DYNAMIC.append(" where TIMEZONE IN (:timeZoneType) ");
			paramMapForAgent.put("timeZoneType", timeZoneTypes);
			tz_flag=true;
		}/*else if ((timeZoneTypes != null && timeZoneTypes.length > 0) && (selTimeZone.equals("ALL"))) {
			SQL_DYNAMIC.append(" and TIMEZONE IN ('EST','CST','PST','MST','NA') ");
	    }*/
		String queryForAgent="";
		if(null != alertStateStatus && !("".equals(alertStateStatus)) && "OFF".equals(alertStateStatus)){
			queryForAgent = " select * " + 
					  " from ( select /*+ FIRST_ROWS( " + dataTableInput.getiDisplayLength() + ") */ " + 
					 // " a.*, SUM( CASE WHEN exists( select * from helios_own.ALERT_DETAIL_DUMMY ALERT where CUSTOMER_NUMBER = a.CUSTOMER_NUMBER and ALERT_VIEW_TAB_DATE IS NULL and IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' ) THEN 1 ELSE 0 end ) OVER () AS TOTAL_ALERT_COUNT, ROWNUM RNUM " +
					 " a.*, SUM( CASE WHEN exists(SELECT 1 FROM (select LINK_ALERTS, ALERT_VIEW_TAB_DATE, IS_ALERT_ACTIVE, IS_DELETED, max(LINK_ALERTS) OVER (PARTITION BY CUSTOMER_NUMBER, ALERT_ID) as MAX_LINK_ALERTS from helios_own.ALERT_DETAIL ALERT  where "
                    +" CUSTOMER_NUMBER = a.CUSTOMER_NUMBER) ALRTV WHERE ALRTV.LINK_ALERTS = ALRTV.MAX_LINK_ALERTS " 
                    +" and ALERT_VIEW_TAB_DATE IS NULL  AND IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' "
                     + " ) THEN 1 ELSE 0 end ) OVER () AS TOTAL_ALERT_COUNT, "
					  +" SUM( CASE  WHEN EXISTS (SELECT 1 FROM (select LINK_ALERTS, ALERT_VIEW_TAB_DATE, IS_ALERT_ACTIVE, IS_DELETED, max(LINK_ALERTS) OVER (PARTITION BY CUSTOMER_NUMBER, ALERT_ID) as MAX_LINK_ALERTS from helios_own.ALERT_DETAIL ALERT  where "
                    +" CUSTOMER_NUMBER = a.CUSTOMER_NUMBER) ALRTV WHERE ALRTV.LINK_ALERTS = ALRTV.MAX_LINK_ALERTS " 
                    +" AND IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' "
                     + ") THEN 1  ELSE 0 END ) OVER () AS TOTAL_DELETE_COUNT , " 
                     /*" a.*, 2 AS TOTAL_ALERT_COUNT, 2 AS TOTAL_DELETE_COUNT, "*/
                     
					  +" ROWNUM RNUM , count(1) over () AS TOTAL_ROWS " +
					      " from ( " + SQL.toString() +" "+SQLConstants.SQL_ORDER_BY_CDM+" ) a " +
					 
					 SQL_DYNAMIC.toString()+"  ) "+
					" WHERE RNUM <= "+max_row_to_fetch+"  and RNUM >= "+min_row_to_fetch;
		} else {
			queryForAgent = " select * " + 
					  " from ( select /*+ FIRST_ROWS( " + dataTableInput.getiDisplayLength() + ") */ " +
					  " a.*, SUM( CASE WHEN exists( SELECT 1 FROM (select LINK_ALERTS, ALERT_VIEW_TAB_DATE, IS_ALERT_ACTIVE, IS_DELETED, max(LINK_ALERTS) OVER (PARTITION BY CUSTOMER_NUMBER, ALERT_ID) as MAX_LINK_ALERTS from helios_own.ALERT_DETAIL ALERT  where "
                    + "CUSTOMER_NUMBER = a.CUSTOMER_NUMBER) ALRTV WHERE ALRTV.LINK_ALERTS = ALRTV.MAX_LINK_ALERTS " 
                    + " and ALERT_VIEW_TAB_DATE IS NULL  AND IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' "
                     + " ) THEN 1 ELSE 0 end ) OVER () AS TOTAL_ALERT_COUNT, "
					  +" SUM( CASE  WHEN EXISTS (SELECT 1 FROM (select LINK_ALERTS, ALERT_VIEW_TAB_DATE, IS_ALERT_ACTIVE, IS_DELETED, max(LINK_ALERTS) OVER (PARTITION BY CUSTOMER_NUMBER, ALERT_ID) as MAX_LINK_ALERTS from helios_own.ALERT_DETAIL ALERT  where "
                    +" CUSTOMER_NUMBER = a.CUSTOMER_NUMBER) ALRTV WHERE ALRTV.LINK_ALERTS = ALRTV.MAX_LINK_ALERTS " 
                    +" AND IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' "
                     + " ) THEN 1  ELSE 0 END ) OVER () AS TOTAL_DELETE_COUNT , " 
					  +" ROWNUM RNUM , count(1) over () AS TOTAL_ROWS " +
					  //" a.*, SUM( CASE WHEN exists( select * from helios_own.ALERT_DETAIL_DUMMY ALERT where CUSTOMER_NUMBER = a.CUSTOMER_NUMBER and ALERT_VIEW_TAB_DATE IS NULL and IS_ALERT_ACTIVE = 'A' AND IS_DELETED='N' ) THEN 1 ELSE 0 end ) OVER () AS TOTAL_ALERT_COUNT, ROWNUM RNUM " +
					      " from ( " + SQL.toString() +" ORDER BY VW_AFTER_REFRESH NULLS FIRST ) a " ;
					 
			if(tz_flag==false){
			queryForAgent=queryForAgent.concat(SQL_DYNAMIC.toString()+" where ACTIVE='A'  ) "+
					" WHERE RNUM <= "+max_row_to_fetch+"  and RNUM >= "+min_row_to_fetch);
			}else{
				queryForAgent=queryForAgent.concat(SQL_DYNAMIC.toString()+" AND ACTIVE='A' ) "+
						" WHERE RNUM <= "+max_row_to_fetch+"  and RNUM >= "+min_row_to_fetch);
			}
				
		}
		final Object[] params = { accId };
		String [] columns = {
				"CUSTOMER_NUMBER", "COMPANY_NAME", "CALL_ORDER", "POTENTIAL_CALL_VALUE","TIMEZONE","CUSTOMER_TYPE","IAM_ID",
				"STATUS","CUSTOMER_SEGMENT_ID","DIVISION","TODAYS_PROGRESS","WEEK_PROGRESS","COMPANY_WEBSITE","TOTAL_ALERT_COUNT","TOTAL_DELETE_COUNT" ,"ACTIVE","RNUM" ,"TOTAL_ROWS"
		};
		String[]  segDtailColumn={"SEG_ID","SEG_TYPE","SEG_NAME","SEG_FREQ","SEG_DESC"};
		SQLQuery fetchSegmentDetails=session.createSQLQuery(SQLConstants.GET_SEGMENT_ID_DETAILS);
		 
			for (Object object : getCacheableResultListParam(session, queryForAgent, paramMapForAgent, columns,
					new CdmCustomerMapper())) {
				if (null != object) {
					CdmCustProfileVO cust = (CdmCustProfileVO) object;
					Map<String, String> map = new LinkedHashMap<String, String>();
					List<NotificationInfoVo> notificationInfoVoList = new ArrayList<NotificationInfoVo>();
					if (!cust.getSegmentIdList().isEmpty()) {
						List<String> result = Arrays.asList(cust.getSegmentIdList().split("\\s*,\\s*"));
						for (String l : result) {
							String[] t = StringUtils.split(l, "-");
							map.put(t[1], t[0]);
						}
                        if(map.size()>Constants.ZERO)
           							cust.setSegmentIdList(StringUtils.arrayToCommaDelimitedString(map.values().toArray()));
					}
					cdmCustProfileVolist.add(cust);
				}
			}
		
		SQLQuery totalCustomerQuery=null;
		if(tz_flag==false && null != alertStateStatus && !("".equals(alertStateStatus)) && "OFF".equals(alertStateStatus))
		 totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString()+" "+SQL_DYNAMIC.toString() +" "+SQLConstants.SQL_ORDER_BY_CDM+ ")");
		else if(tz_flag==false && null != alertStateStatus && !("".equals(alertStateStatus)) && "ON".equals(alertStateStatus))
		 totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString()+" "+SQL_DYNAMIC.toString() +" "+SQLConstants.SQL_ORDER_BY_CDM+ ") where ACTIVE='A' ");
		else if(tz_flag==true && null != alertStateStatus && !("".equals(alertStateStatus)) && "OFF".equals(alertStateStatus))
		 totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString()+" "+SQLConstants.SQL_ORDER_BY_CDM+ " ) where TIMEZONE IN (:timeZoneType) ");
		else if(tz_flag==true && null != alertStateStatus && !("".equals(alertStateStatus)) && "ON".equals(alertStateStatus))
			 totalCustomerQuery = session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from (" + SQL.toString()+" "+SQLConstants.SQL_ORDER_BY_CDM+ " ) where TIMEZONE IN (:timeZoneType) AND ACTIVE='A'  ");
		totalCustomerQuery.setCacheable(true);
		totalCustomerQuery.addScalar("TOTAL_CUST_COUNT", org.hibernate.type.BigDecimalType.INSTANCE);
		Set<Map.Entry<String, Object>> set = paramMapForAgent.entrySet();
		for (Map.Entry<String, Object> e : set) {
			if (e.getValue() instanceof Object []) {
				totalCustomerQuery.setParameterList(e.getKey(), (Object [])e.getValue());
			} else {
				totalCustomerQuery.setParameter(e.getKey(), e.getValue());
			}
		}
		//Object objTotalCount = totalCustomerQuery.uniqueResult();
		
		CustomerDataDTO.setCdmCustomerVoList(cdmCustProfileVolist);
		//CustomerDataDTO.setTotalCount(((BigDecimal)objTotalCount).intValue());
		//CustomerDataDTO.setDisplayCount(((BigDecimal)objTotalCount).intValue());
		if(null!=cdmCustProfileVolist && cdmCustProfileVolist.size() > Constants.ZERO){
		 CustomerDataDTO.setTotalCount(Integer.parseInt(cdmCustProfileVolist.get(Constants.ZERO).getTotalRows()));
		 CustomerDataDTO.setDisplayCount(Integer.parseInt(cdmCustProfileVolist.get(Constants.ZERO).getTotalRows()));
		}
		List<Object[]> results = session.createSQLQuery(SQLConstants.SQL_GET_DISTINCT_TIMEZONE_CDM1).list();
		//List<Object[]> results  =  session.createSQLQuery(SQLConstants.ACCOUNT_WEEK_PROGRESS_AM1).setParameter("repNum",repNum).list();
		Map<String, String> timeZone = new LinkedHashMap<String,String>();
		if(results!=null && results.size()>0)
		{
				for (Object[] row :  results) {
				    String Desc= (String) row[1];
				    String Tz = (String) row[0];
				    timeZone.put(Desc, Tz);
				}
		}
		CustomerDataDTO.setTimeZoneMap(timeZone);
               //distinct timezone list of a customers of a rep
		//CustomerDataDTO.setTimeZoneList(timeZoneList);
		
	} catch (HibernateException he) {

		throw new DashboardException(he);
	} catch (Exception e) {
		throw new DashboardException(e);

	} finally {
		if (session != null && session.isOpen())
			session.close();
		logger.trace("Exiting method :--> getAllCustomerInfo()");
	}
	return CustomerDataDTO;
	}
	
	@Override
	public String  getOrderContactName(String orderContactId)
	{
		String orderContactName=" ";
		Session session = null;
		
		try {

			String SQL = SQLConstants.GET_ORDER_CONTACT_FOR_LAST_LIVE_CONTACT_CDM1;

			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("contactId",orderContactId);
			
			
			for (Object object : getResultListParam(session,
					SQL, paramMap,
					new LastLiveOrderContactMapper())) {
				
				
				if (null != object) {
				Task task = (Task) object;
				 orderContactName=task.getWhoId();
				
				}
			}
			
		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getOrderContactName()");
		}
		 return orderContactName;
		
	}
	
	public String checkAccountHoldoutStatus(String custId, String accId)
			throws DashboardException {
		// TODO Auto-generated method stub
		Session session = null;
		String status="";
		
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery sqlquery = session
					.createSQLQuery(SQLConstants.GET_ACCOUNT_HOLD_OUT_STATUS);
			//sqlquery.setParameter("accId", accId);
			sqlquery.setParameter("custId", custId);
			sqlquery.setParameter("searchText", custId);
			
			List<String> statusList = sqlquery.list();
			if (statusList != null && !(statusList.isEmpty()) &&
					( statusList.get(0).equalsIgnoreCase("TRUE"))) {
				status = "holdout";
			}
		}catch(HibernateException he){
			
			throw new DashboardException(he);
		}catch(Exception e){
			throw new DashboardException(e);
		}finally{
			if (session != null && session.isOpen())
				session.close();
		}
		return status;
	}
	
		@Override
	public List<OrderContactInfoVO> getCustomerOrderContactDetails(String custNum) throws DashboardException {
		List<OrderContactInfoVO> recentVisitorOrderContactsList = new ArrayList<OrderContactInfoVO>();
		List<OrderContactInfoVO> recentOrdererOrderContactsList = new ArrayList<OrderContactInfoVO>();
		List<OrderContactInfoVO> topSpenderOrderContactsList = new ArrayList<OrderContactInfoVO>();
		List<OrderContactInfoVO> finalOrderContactsList = new ArrayList<OrderContactInfoVO>();
		List<String> listOrderContacts = new ArrayList<String>();

		Session session = null;
		StringBuilder SQL = new StringBuilder("");

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> paramMapForAgent = new HashMap<String, Object>();
			SQL.append(SQLConstants.SQL_GET_CUSTOMER_ORDER_CONTACT_DETAILS_CDM);
			SQL.append(SQLConstants.SQL_ORDER_CONTACT_DETAILS_ORDERBY_VISIT_DATE);
			paramMapForAgent.put("custNum", custNum);

			String[] columns = { "ORDER_CONTACT", "ORDER_DATE", "TOTAL_SALES_CURR", "RECENT_VISIT_DATE", "EMAIL",
					"PHONE_NUMBER", "IAM_ID", "CONTACT_ID"

			};

			for (Object object : getCacheableResultListParam(session, SQL.toString(), paramMapForAgent, columns,
					new CdmOrderContactsMapper())) {
				if (null != object) {

					recentVisitorOrderContactsList.add((OrderContactInfoVO) object);
				}
			}
			for (Object object : getCacheableResultListParam(session,
					SQLConstants.SQL_GET_CUSTOMER_ORDER_CONTACT_DETAILS_CDM
							+ SQLConstants.SQL_ORDER_CONTACT_DETAILS_ORDERBY_ORDER_DATE,
					paramMapForAgent, columns, new CdmOrderContactsMapper())) {
				if (null != object) {

					recentOrdererOrderContactsList.add((OrderContactInfoVO) object);
				}
			}
			for (Object object : getCacheableResultListParam(session,
					SQLConstants.SQL_GET_CUSTOMER_ORDER_CONTACT_DETAILS_CDM
							+ SQLConstants.SQL_ORDER_CONTACT_DETAILS_ORDERBY_TOTAL_SALES,
					paramMapForAgent, columns, new CdmOrderContactsMapper())) {
				if (null != object) {

					topSpenderOrderContactsList.add((OrderContactInfoVO) object);
				}
			}

			for (int i = 0; i < 3; i++) {
				if (recentVisitorOrderContactsList.size() > i) {
					OrderContactInfoVO rv = recentVisitorOrderContactsList.get(i);

					if (i == 0) {
						rv.setRecentVisitor(true);
						if (recentOrdererOrderContactsList.size() > 0) {

							if (rv.getOrderContact()
									.equalsIgnoreCase(recentOrdererOrderContactsList.get(0).getOrderContact()))

							{
								rv.setRecentOrderer(true);
							}
						}

						if (topSpenderOrderContactsList.size() > 0) {

							if (rv.getOrderContact()
									.equalsIgnoreCase(topSpenderOrderContactsList.get(0).getOrderContact())) {
								rv.setTopSpender(true);
							}
						}

						listOrderContacts.add(rv.getOrderContact());
						finalOrderContactsList.add(rv);
					} else {

						if (!listOrderContacts.contains(rv.getOrderContact())) {
							listOrderContacts.add(rv.getOrderContact());
							finalOrderContactsList.add(rv);

						}
					}

					if (finalOrderContactsList.size() == 3)

					{
						break;
					}

				}
				if (recentOrdererOrderContactsList.size() > i) {
					OrderContactInfoVO ro = recentOrdererOrderContactsList.get(i);
					if (i == 0 && !listOrderContacts.contains(ro.getOrderContact())) {
						ro.setRecentOrderer(true);
						if (topSpenderOrderContactsList.size() > 0) {
							if (ro.getOrderContact()
									.equalsIgnoreCase(topSpenderOrderContactsList.get(0).getOrderContact())) {
								ro.setTopSpender(true);
							}
						}

						listOrderContacts.add(ro.getOrderContact());
						finalOrderContactsList.add(ro);
					} else if (!listOrderContacts.contains(ro.getOrderContact())) {
						listOrderContacts.add(ro.getOrderContact());
						finalOrderContactsList.add(ro);
					}

					if (finalOrderContactsList.size() == 3) {
						break;
					}
				}

				if (topSpenderOrderContactsList.size() > i) {
					OrderContactInfoVO ts = topSpenderOrderContactsList.get(i);
					if (i == 0 && !listOrderContacts.contains(ts.getOrderContact())) {

						ts.setTopSpender(true);
						listOrderContacts.add(ts.getOrderContact());
						finalOrderContactsList.add(ts);
					} else if (!listOrderContacts.contains(ts.getOrderContact())) {
						listOrderContacts.add(ts.getOrderContact());
						finalOrderContactsList.add(ts);
					}

					if (finalOrderContactsList.size() == 3) {
						break;
					}

				}

			} // end of for loop

		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getCustomerOrderContactDetails()");
		}
		return finalOrderContactsList;
	}
		
	@Override
	public AccountProgressInfo getTodaysAndWeekProgress(String repNum) throws DashboardException {

		Session session = null;
		AccountProgressInfo progress = new AccountProgressInfo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todaysProgressPercentage="";
		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			List<BigDecimal> todaysAccountActionedCount = session.createSQLQuery(SQLConstants.ACCOUNT_TODAYS_PROGRESS_AM1).setParameter("repNum",repNum).list();
			if (null != todaysAccountActionedCount && todaysAccountActionedCount.size()>0 && (null!=todaysAccountActionedCount.get(0)) && !("".equals(todaysAccountActionedCount.get(0)))) {
				int todayProgress = (todaysAccountActionedCount.get(0)).intValueExact();
				todaysProgressPercentage=String.valueOf(todayProgress*2);
				progress.setTodaysAccntActioned(todaysAccountActionedCount.get(0).toPlainString());
				progress.setTodaysAccntActionedPercentage(todaysProgressPercentage);
				if (todayProgress >= 50) 
				progress.setTargetReached(true);
			}
			Date date = new Date();
			progress.setTodaysDate(dateFormat.format(date));
			// weekly Progress start
			Calendar currentCalendar = Calendar.getInstance();
			while (currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				currentCalendar.add(Calendar.DATE, -1);// go one day before
			}
			Date currentWeekStart, currentWeekEnd;
			currentWeekStart = currentCalendar.getTime();
			currentCalendar.add(Calendar.DATE, 6);
			currentWeekEnd = currentCalendar.getTime();
			progress.setWeekDateRange(dateFormat.format(currentWeekStart) + " - " + dateFormat.format(currentWeekEnd));
			
			List<Object[]> results  =  session.createSQLQuery(SQLConstants.ACCOUNT_WEEK_PROGRESS_AM1).setParameter("repNum",repNum).list();
			if(results!=null && results.size()>0)
			{
				Map<String, Integer> weekAccountActionedCountMap = new HashMap<String,Integer>(results.size());
					for (Object[] row :  results) {
					    String day= (String) row[0];
					    BigDecimal count = (BigDecimal) row[1];
					    weekAccountActionedCountMap.put(day, count.intValueExact());
					}
					progress.setWeeklyAccntActionedMap(weekAccountActionedCountMap);
               int weekActionedCount = 0;
					for (Map.Entry<String,Integer> entry :  weekAccountActionedCountMap.entrySet()) {
						weekActionedCount=weekActionedCount+entry.getValue();
						
					}
					progress.setWeeklyAccntActioned(weekActionedCount);	
			}
		} catch (HibernateException he) {

			throw new DashboardException(he);

		} catch (Exception e) {

			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return progress;
	}

	
	@Override
	public List<NotificationInfoVo> getSegmentDetails(String segIds) throws DashboardException {

		Session session = custExoHeliosOwnSessionFactory.openSession();
		List<NotificationInfoVo> notificationInfoVoList = new ArrayList<NotificationInfoVo>();
		String[] segDtailColumn = { "SEG_ID", "SEG_TYPE", "SEG_NAME", "SEG_FREQ", "SEG_DESC" };
		List<String> seglist = Arrays.asList(segIds.split("\\s*,\\s*"));

		try {

			SQLQuery fetchSegmentDetails = session.createSQLQuery(SQLConstants.GET_SEGMENT_ID_DETAILS);

			for (String segId : seglist) {
				fetchSegmentDetails.setParameter("segId", segId);
				List<Map<String, Object>> list = fetchSegmentDetails
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				if (list != null && !list.isEmpty()) {
					NotificationInfoVo vo = (NotificationInfoVo) new NotificationInfoMapper().mapRow(list).get(0);
					notificationInfoVoList.add(vo);

				}
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getSegmentDetailsAM1()");
		}
		return notificationInfoVoList;
	}
	
	// SPARK's Recommendation Change - Begin

		@Override
		public List<CustomerUserVO> getCustomerEmailId(String custNum) {
			Session session = null;
			String status = "";
			List<CustomerUserVO> cutomerUserVO = new ArrayList<CustomerUserVO>();
			try {

				Date endDate = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.DAY_OF_MONTH, reorderTransactionDays);
				Date startDate = cal.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
				String SQL = SQLConstants.GET_CUST_USER_EMAIL;
				session = custExoHeliosOwnSessionFactory.openSession();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("custNum", custNum);
				paramMap.put("startDate", sdf.format(startDate));
				paramMap.put("endDate", sdf.format(endDate));

				for (Object object : getResultListParam(session, SQL, paramMap, new CustomerUserMapper())) {
					if (null != object) {
						cutomerUserVO.add((CustomerUserVO) object);
					}
				}

			} catch (HibernateException he) {
				throw new DashboardException(he);

			} catch (Exception e) {
				throw new DashboardException(e);

			} finally {
				if (session != null && session.isOpen())
					session.close();
				logger.trace("Exiting method :--> getCustomerEmailId()");
			}
			return cutomerUserVO;
		}

		@Override
		public ReorderRecommendationVO getSKUInfo(ReorderRecommendationVO sku) {
			Session session = null;
			List<ReorderRecommendationVO> recommendationVO = new ArrayList<ReorderRecommendationVO>();
			try {

				String SQL = SQLConstants.GET_SKU_INFO;
				session = custExoHeliosOwnSessionFactory.openSession();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("skuNum", sku.getSkuNumber());
				for (Object object : getResultListParam(session, SQL, paramMap, new ReorderRecommendationMapper())) {
					if (null != object) {
						recommendationVO.add((ReorderRecommendationVO) object);
					}
				}
				if (recommendationVO.size() > 0) {
					for (ReorderRecommendationVO recommendations : recommendationVO) {
						sku.setCatenTryId(recommendations.getCatenTryId());
						sku.setThumbnail(recommendations.getThumbnail());
						sku.setItemDescription(recommendations.getItemDescription());
					}
				}

			} catch (HibernateException he) {
				throw new DashboardException(he);

			} catch (Exception e) {
				throw new DashboardException(e);

			} finally {
				if (session != null && session.isOpen())
					session.close();
				logger.trace("Exiting method :--> getSKUInfo()");
			}
			return sku;
		}

		@Override
		public void logRecommendation(LogRecommendationVO logRecommendationVO) {
			
			List<String> transList = null;

			logger.info("Begin Log Recommendation****");
			final Session session = custExoHeliosOwnSessionFactory.openSession();
			Transaction transaction = null;
			try {

				transaction = session.beginTransaction();
				
				final SQLQuery sqlInsertQuery = session.createSQLQuery(SQLConstants.LOG_RECOMM_SKU_SBA);
				sqlInsertQuery.setParameter(0, logRecommendationVO.getReqInput());
				sqlInsertQuery.setParameter(1, logRecommendationVO.getInputType());
				sqlInsertQuery.setParameter(2, logRecommendationVO.getSchemeQueried());
				sqlInsertQuery.setParameter(3, logRecommendationVO.getCountRequested());
				sqlInsertQuery.setParameter(4, logRecommendationVO.getRecommendedSkus());
				sqlInsertQuery.setParameter(5, logRecommendationVO.getStrategy());
				sqlInsertQuery.setParameter(6, logRecommendationVO.getSparxFile());
				sqlInsertQuery.setParameter(7, logRecommendationVO.getRepId());
				sqlInsertQuery.setParameter(8, logRecommendationVO.getCustomerNumber());
				sqlInsertQuery.setParameter(9, logRecommendationVO.getLoggedUser());
				sqlInsertQuery.setParameter(10, logRecommendationVO.getCustomerUser());

				sqlInsertQuery.executeUpdate();
				transaction.commit();

			} catch (Exception e) {
				logger.error("Error while fetching customer transactions", e);
				transaction.rollback();
			} finally {
				session.close();
			}
		}
		
		@Override
		@SuppressWarnings("rawtypes")
		public Map<String,List<String>> getBoughtSkus(String custNum, String orderContact) {
			// TODO Auto-generated method stub
				Map<String,List<String>> skuList = new LinkedHashMap<String,List<String>>();
				Session session = null;
				try {

					session = custExoHeliosOwnSessionFactory.openSession();
					Date endDate = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(endDate);
					cal.add(Calendar.DAY_OF_MONTH, boughtAlsoBoughtTransactionDays);
					Date startDate = cal.getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
					SQLQuery sqlquery = session.createSQLQuery( SQLConstants.GET_CUSTOMER_BOUGHT_SKUS);
					//sqlquery.setParameter("accId", accId);
					sqlquery.setParameter("startDate", sdf.format(startDate));
					sqlquery.setParameter("endDate", sdf.format(endDate));
					sqlquery.setParameter("custNum",custNum);
					sqlquery.setParameter("orderContact",orderContact );
					sqlquery.addScalar("SKU", StringType.INSTANCE);
					sqlquery.addScalar("ORDERDATE",DateType.INSTANCE);
					sqlquery.addScalar("REP_ID", StringType.INSTANCE);
					sqlquery.list();
					for(Object rows : sqlquery.list()){
					    Object[] row = (Object[]) rows;
					    List<String> skuDetail=new ArrayList<String>();
					    skuDetail.add(row[1].toString());
					    skuDetail.add(row[2].toString());
					    if(!skuList.containsKey(row[0].toString()))
					    skuList.put(row[0].toString(),skuDetail);
					}
					
				} catch (HibernateException he) {
					throw new DashboardException(he);
				} catch (Exception e) {
					throw new DashboardException(e);
				} finally {
					if (session != null && session.isOpen())
						session.close();
					logger.trace("Exiting method :--> getLatestFiscalDate()");
				}
				return skuList;
			
		}
		
		@Override
		public CustRecommendationVO getBABSKUInfo(String skuNumber) {
			Session session = null;
			CustRecommendationVO recommendation=new CustRecommendationVO();
			try {

				session = custExoHeliosOwnSessionFactory.openSession();
				SQLQuery sqlquery = session.createSQLQuery( SQLConstants.GET_SKU_INFO);
				sqlquery.setParameter("skuNum",skuNumber);	
				List<Map<String, Object>> list = sqlquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				if (list != null && !list.isEmpty()) {
					recommendation = (CustRecommendationVO) new CustBoughtAlsoBoughtRecommendationVOMapper().mapRow(list).get(0);
				}
		
				
			} catch (HibernateException he) {
				throw new DashboardException(he);

			} catch (Exception e) {
				throw new DashboardException(e);

			} finally {
				if (session != null && session.isOpen())
					session.close();
				logger.trace("Exiting method :--> getSKUInfo()");
			}
			return recommendation;
		}
		//SPARK's Recommendation Change - End
		
		@Override
	       public LastLiveContactAndHistoryVO getLastLiveContactDetails(String iamId, String custNumber) {

	              Session session = null;
	              String SQL = SQLConstants.GET_LAST_LIVE_CONTACT_DETAILS_CDM1;
	              List<LastLiveContactAndHistoryVO> lastLiveContactAndHistoryVOList = new ArrayList<LastLiveContactAndHistoryVO>();

	              LastLiveContactAndHistoryVO lastLiveContactAndHistoryVO = new LastLiveContactAndHistoryVO();
	              try {
	                     session = custExoHeliosOwnSessionFactory.openSession();
	                     Map<String, Object> paramMapForAgent = new HashMap<String, Object>();

	                     paramMapForAgent.put("iamId", iamId);
	                     paramMapForAgent.put("custNum", custNumber);

	                     String[] columns = { "ACTIVITYDATE", "STATUS", "SUBJECT", "DESCRIPTION", "CONTACT_NAME","OWNER_NAME" };

	                     boolean isLastLiveContactCalculated = false;
	                     for (Object object : getCacheableResultListParam(session, SQL, paramMapForAgent, columns,
	                                  new LastLiveContactAndHistoryMapper())) {
	                           if (null != object) {
	                                  LastLiveContactAndHistoryVO vo = (LastLiveContactAndHistoryVO) object;

	                                  if (vo.getStatus().equalsIgnoreCase("Task Completed - Live Contact")) {

	                                         if (!isLastLiveContactCalculated) {

	                                                lastLiveContactAndHistoryVO.setActivityDate(vo.getActivityDate());
	                                                lastLiveContactAndHistoryVO.setDescription(vo.getDescription());
	                                                lastLiveContactAndHistoryVO.setStatus(vo.getStatus());
	                                                lastLiveContactAndHistoryVO.setWhoId(vo.getWhoId());
	                                                lastLiveContactAndHistoryVO.setSubject(vo.getSubject());
	                                                lastLiveContactAndHistoryVO.setOwnerId(vo.getOwnerId());
	                                                isLastLiveContactCalculated = true;
	                                         } else {
	                                                lastLiveContactAndHistoryVOList.add((LastLiveContactAndHistoryVO) object);
	                                         }
	                                  }

	                                  else {

	                                         lastLiveContactAndHistoryVOList.add((LastLiveContactAndHistoryVO) object);

	                                  }

	                           }

	                     }
	                     lastLiveContactAndHistoryVO.setLastLiveContactHistoryList(lastLiveContactAndHistoryVOList.size() >= 4
	                                  ? lastLiveContactAndHistoryVOList.subList(0, 3) : lastLiveContactAndHistoryVOList);
	                     
	                     SQLQuery sql_query=session.createSQLQuery(SQLConstants.SQL_GET_CURRENT_YTD_INFO_CUSTEXO);
	                     sql_query.setParameter(0, custNumber);
	                     //sql_query.setParameter(1, custNumber);
	                     List<Map<String, Object>> list = sql_query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	     				if (list != null && !list.isEmpty()) {
	     					YTDInfoVO ytdInfoVo = (YTDInfoVO) new LastLiveContactAndHistoryMapper().mapRowSalesInfo(list.get(0));
	     					lastLiveContactAndHistoryVO.setYTDInfoVO(ytdInfoVo);
	     				}

	              } catch (HibernateException he) {
	                     throw new DashboardException(he);

	              } catch (Exception e) {
	                     throw new DashboardException(e);

	              } finally {
	                     if (session != null && session.isOpen())
	                           session.close();
	                     logger.trace("Exiting method :-->  getLastLiveContactDetails()");
	              }
	              return lastLiveContactAndHistoryVO;
	       }

		@Override
		public String updateAlertStatus(String custId,String ldapUserRole, String ldapUserId, String ldapUserName) throws DashboardException, DashboardException {
			String updateStatus="";
			Session session = null;
			StringBuilder query =new StringBuilder(SQLConstants.SQL_UPDATE_ALERT_VIEW_TAB_STATUS);
			SQLQuery sqlquery=null;
			SfdcInputId result = null;
			try {
				session = custExoHeliosOwnSessionFactory.openSession();
				
				if ( null!=custId && !("".equals(custId))) {

					if (ldapUserRole.equalsIgnoreCase("ADMIN")) {
						sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_2_ADMIN);
						sqlquery.setParameter(0, adminSFDCId.trim());
						sqlquery.setParameter(1, custId);
					} else {

						sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SFDC_INPUT_IDS_2);
						sqlquery.setParameter(0, ldapUserId);
						sqlquery.setParameter(1, adminSFDCId.trim());
						sqlquery.setParameter(2, custId);
					}

				}
				List<Map<String, Object>> returnList = sqlquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != returnList && returnList.size() > Constants.ZERO)
					result = (SfdcInputId) (((new SfdcInputIdMapper()).mapRow(returnList)).get(0));
				if(null!=result && !"".equals(result.getAccountMgrId()) ){
					if (result.getAccountMgrId().equals(adminSFDCId.trim())){
						updateStatus="HeliosAdmin";
					} else {
						SQLQuery sqlUpdateQuery = session.createSQLQuery(query.toString());
						sqlUpdateQuery.setParameter(0, ldapUserName);
						sqlUpdateQuery.setParameter(1, custId);
						updateStatus=String.valueOf(sqlUpdateQuery.executeUpdate());
					}
				}
				
			} catch (HibernateException he) {

				throw new DashboardException(he);
			} catch (Exception e) {

				throw new DashboardException(e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return updateStatus;
		}

		@Override
		public List<AlertDetailVo> getAlertDetail(String custNum) throws DashboardException {
			Session session = null;
			List<AlertDetailVo> alertDetail = new ArrayList<AlertDetailVo>();
			HashSet<String> alertIdSet=new HashSet<String>();
			try{
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("custNum",custNum);
			
			for(Object object : getResultList(session, SQLConstants.SELECT_ALERT_DTL,params , new AlertDetailMapper())){
			if(null != object){
				alertDetail.add((AlertDetailVo)object);
				alertIdSet.add(((AlertDetailVo)object).getAlertId());
			}
			}
			if(null != alertIdSet && alertIdSet.size()>0){
				List<AlertParamDetailsVo> paramDetails=new ArrayList<AlertParamDetailsVo>();
				AlertParamDetailsVo paramdtl=null;
				for(String alertId: alertIdSet){
					for(AlertDetailVo alertDetailVo: alertDetail){
						if((null !=alertDetailVo.getAlertId() && null!=alertId) && (alertDetailVo.getAlertId().equalsIgnoreCase(alertId))){
							paramdtl=new AlertParamDetailsVo();
							paramdtl.setParamId(alertDetailVo.getParamId());
							paramdtl.setParamLabel(alertDetailVo.getParamLabel());
							paramdtl.setParamVal(alertDetailVo.getParamVal());
							paramdtl.setAlertId(alertDetailVo.getAlertId());
							paramDetails.add(paramdtl);
						}
					}
					
				}
				for(AlertDetailVo alertDetailVo: alertDetail){
					alertDetailVo.getParamDetails().addAll(paramDetails);
				}
			}
			}catch (HibernateException he) {
			throw new DashboardException(he);

			} catch (Exception e) {
			throw new DashboardException(e);

			} finally {
			if (session != null && session.isOpen())
			session.close();

			}
			return alertDetail;
		}

		@Override
		public String deleteAlert(String custNum, String alertIdCombination, String alertId,String ldapUserId) throws DashboardException {

			String softDelStatus="";
			String refreshStatus="";
			Session session = null;
			StringBuilder query =new StringBuilder(SQLConstants.SQL_SOFT_DELETE_ALERT);
			SQLQuery sqlquery=null;

			try {
				session = custExoHeliosOwnSessionFactory.openSession();
						SQLQuery sqlUpdateQuery = session.createSQLQuery(query.toString());
						sqlUpdateQuery.setParameter(0,ldapUserId);
						sqlUpdateQuery.setParameter(1,alertIdCombination);
						sqlUpdateQuery.setParameter(2, alertId);
						sqlUpdateQuery.setParameter(3,alertIdCombination);
						sqlUpdateQuery.setParameter(4, alertId);
						softDelStatus=String.valueOf(sqlUpdateQuery.executeUpdate());
						if(null !=softDelStatus && !("".equals(softDelStatus)) && Integer.parseInt(softDelStatus) > 0){
							Query queryy= session.createSQLQuery(
									"CALL helios_own.PKG_CUSTOMER_ALERT.ALERT_SEGMENT_REFRESHER(:cust_num,:alert_id)")
									.setParameter("cust_num", custNum).setParameter("alert_id", alertId);
							queryy.executeUpdate();;
						} else{
							refreshStatus="no refresh required";
						}
				
			} catch (HibernateException he) {

				throw new DashboardException(he);
			} catch (Exception e) {

				throw new DashboardException(e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return softDelStatus;
		}
	
		@Override
		public String updateReadAlert(String custNum, String alertIdCombination, String alertId,String ldapUserId) throws DashboardException {

			String updateReadStatus="";
			Session session = null;
			StringBuilder query =new StringBuilder(SQLConstants.SQL_UPDATE_READ_ALERT);
			SQLQuery sqlquery=null;

			try {
				session = custExoHeliosOwnSessionFactory.openSession();
						SQLQuery sqlUpdateQuery = session.createSQLQuery(query.toString());
						sqlUpdateQuery.setParameter(0,ldapUserId);
						sqlUpdateQuery.setParameter(1,alertIdCombination);
						sqlUpdateQuery.setParameter(2, alertId);
						sqlUpdateQuery.setParameter(3,alertIdCombination);
						sqlUpdateQuery.setParameter(4, alertId);
						updateReadStatus=String.valueOf(sqlUpdateQuery.executeUpdate());
				
			} catch (HibernateException he) {

				throw new DashboardException(he);
			} catch (Exception e) {

				throw new DashboardException(e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return updateReadStatus;
		}
		
		
		/**
		 * Method implementation to fetch latest fiscal data.
		 * 
		 * @param String
		 *            custNum
		 * @return String
		 */
		@Override
		@SuppressWarnings("rawtypes")
		public String getLatestFiscaCompleteOrderDate(String custNum)
				throws DashboardException {
			String latestFiscaldate = null;
			Session session = null;
			try {

				String SQL_Without_Fiscal = "select to_char(maxdate,'mm/dd/yyyy') from (SELECT MAX(to_date(TO_CHAR(OHCRDT),'yyyymmdd')) as maxdate"
						+ " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T"
						+ " WHERE LENGTH(OHCRDT)=8"
						+ " AND OHCUST          = "
						+ custNum + " and UPPER(RTRIM(LTRIM(OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ'))";


				session = custExoHeliosOwnSessionFactory.openSession();

				List list = session.createSQLQuery(SQL_Without_Fiscal).list();

				if (list != null && !list.isEmpty()) {
					latestFiscaldate = (String) list.get(0);
				}

			} catch (HibernateException he) {
				throw new DashboardException(he);
			} catch (Exception e) {
				throw new DashboardException(e);
			} finally {
				if (session != null && session.isOpen())
					session.close();
				logger.trace("Exiting method :--> getLatestFiscalDate()");
			}
			return latestFiscaldate;
		}
		@Override
		public List<ShipToVO> getShipToInfo(String custNum,String location)
				throws DashboardException {
			List<ShipToVO> shipToVO = new ArrayList<ShipToVO>();
			Session session = null;
			try {
				String SQL = SQLConstants.SQL_GET_SHIPTO_DETAILS_MM;
				List<Object> resultList=null;
				String locations[]=null;
				session = custExoHeliosOwnSessionFactory.openSession();
				SQLQuery sqlQuery = session.createSQLQuery(SQL);
				sqlQuery.setParameter("custNum", custNum);
				if(null!=location && !("".equals(location)) && !("ALL".equals(location))){
				 
				 locations=location.split(",");
				}
				else if(null!=location && !("".equals(location)) && ("ALL".equals(location))) {
				 location="ACT,INA,GLO";
				 locations=location.split(",");
				}
				sqlQuery.setParameterList("status",Arrays.asList(locations) );
				List<Map<String, Object>> list = sqlQuery.setResultTransformer(
						Transformers.ALIAS_TO_ENTITY_MAP).list();
				if (list != null && !list.isEmpty()) {
					resultList = new ShipToVOMapper().mapRow(list);
				}
				
				/*final Object[] params = { custNum, custNum ,location };*/
				if(null!=resultList){
					for (Object object : resultList) {
						if (null != object) {
							shipToVO.add((ShipToVO) object);
						}
					}
				}

			} catch (HibernateException he) {
				throw new DashboardException(he);
			} catch (Exception e) {
				throw new DashboardException(e);
			} finally {
				if (session != null && session.isOpen())
					session.close();
				logger.trace("Exiting method :--> getShipToInfo()");
			}
			return shipToVO;
		}
}