package com.staples.dashboard.app.sdc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.dao.mappers.HawkeyeDetailsVMapper;
import com.staples.dashboard.app.dao.mappers.SuperUserSamRowmapper;
import com.staples.dashboard.app.sdc.constants.DaoQueryConstants;
import com.staples.dashboard.app.sdc.dao.mappers.CategoryDivisionVORowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.CategoryPercentageMapper;
import com.staples.dashboard.app.sdc.dao.mappers.CustomerOrderRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.CustomerParentInfoRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.CustomerRepRowMappers;
import com.staples.dashboard.app.sdc.dao.mappers.CustomerTransactionsRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.FiscalCategoryCalResponseMapper;
import com.staples.dashboard.app.sdc.dao.mappers.LineCouponBeanRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.LineDiscountBeanRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.ParentChildInfoRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.TransactionCountWRowMapper;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.CustomerParentChildRewardsVo;
import com.staples.dashboard.app.sdc.vo.CustomerParentInfo;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;
import com.staples.dashboard.app.sdc.vo.FiscalYearResponse;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;
import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.OrderItem;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.ShipTosBean;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.vo.CategoryDataVo;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.StaplesDotcomActivityVo;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;
import com.staples.dashboard.exception.DashboardException;

@Repository
public class CustomerSalesDAOImpl implements CustomerSalesDAO {

	private static final Logger logger = Logger.getLogger(CustomerSalesDAOImpl.class);

	@Autowired
	@Qualifier("sessionFactorycustexoSDC")
	private SessionFactory sessionFactorycustexoSDC;

	@Override
	public List<CustomerOrders> getCustomerTransactions(OrderInfo customer) {
		List<CustomerOrders> transList = new ArrayList<>();

		logger.info("Begin ****");
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.CUSTOMER_TRANSACTIONS));

			// sqlSelectQuery.setParameterList("1", customer.getCustomerIds());
			sqlSelectQuery.setParameterList("rIds", customer.getCustomerIds());
			sqlSelectQuery.setParameterList("mIds", customer.getCustomerIds());
			sqlSelectQuery.setParameter(0, customer.getSbuName());
			sqlSelectQuery.setParameter(1, customer.getStartDate());
			sqlSelectQuery.setParameter(2, customer.getEndDate());

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				transList = new CustomerTransactionsRowMapper().mapRow(list);

			}
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return transList;
	}

	@Override
	public String getSourceSystemId(String sbuName) {

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(replaceSchema(DaoQueryConstants.NativeQueryForSSID));
			sqlSelectQuery.setParameter(0, sbuName);
			List<String> results = (List<String>) sqlSelectQuery.list();

			if (results != null && !results.isEmpty()) {
				transaction.commit();
				return results.get(0);
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return "102";
	}

	@Override
	public List<CustomerOrder> getOrderDetails(String ssId, List<String> orderNos) {
		// TODO Auto-generated method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_FOR_ORDER_ITEMS));
			// sqlSelectQuery.setParameter(0, ssId);
			sqlSelectQuery.setParameterList("1", Arrays.asList("102", "107"));
			sqlSelectQuery.setParameterList("2", orderNos);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();

			if (list != null && !list.isEmpty()) {
				List<CustomerOrder> mapRow = new CustomerOrderRowMapper().mapRow(list);
				transaction.commit();
				return mapRow;
			}

		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
			System.out.println(e);
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	private String replaceSchema(String sqlStr) {
		return sqlStr.replace("{}", "CEX01_OWN.");
	}

	@Override
	public List<ShipTosBean> getShipToInfo(String salesTranId) {
		// TODO Auto-generated method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.NativeQueryShipToForSales));
			sqlSelectQuery.setParameter(0, salesTranId);
			List<ShipTosBean> headerDiscounts = (List<ShipTosBean>) sqlSelectQuery
					.setResultTransformer(Transformers.aliasToBean(ShipTosBean.class)).list();
			transaction.commit();
			return headerDiscounts;
		} catch (Exception e) {
			transaction.rollback();
			logger.error("Error while fetching customer transactions", e);
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	@Override
	public List<LineDiscountBean> getLineDiscounts(List<String> salesTranIds) {
		// TODO Auto-generated method stub

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.NativeQueryForLineDiscount));
			sqlSelectQuery.setParameterList("1", salesTranIds);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<LineDiscountBean> mapRow = new LineDiscountBeanRowMapper().mapRow(list);
				System.out.println(list.size());
				transaction.commit();
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ArrayList<>();

	}

	@Override
	public List<LineCouponBean> getLineCoupons(List<String> salesTranIds) {
		// TODO Auto-generated method stub

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		// List<String> trainIds=Arrays.asList(salesTranId,"23575201");
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.NativeQueryForLineCoupon));
			sqlSelectQuery.setParameterList("1", salesTranIds);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<LineCouponBean> mapRow = new LineCouponBeanRowMapper().mapRow(list);
				System.out.println(list.size());
				transaction.commit();
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	@Override
	public List<CustomerParentInfo> getCustomerParentChildInfo(List<String> salesOrderNos) {
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		// List<String> trainIds=Arrays.asList(salesTranId,"23575201");
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_FOR_ORDER_PARENT_CHILDS));
			sqlSelectQuery.setParameterList("1", salesOrderNos);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<CustomerParentInfo> mapRow = new CustomerParentInfoRowMapper().mapRow(list);
				System.out.println(list.size());
				transaction.commit();
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	@Override
	public List<String> getMasterSalesTranSourceNumber(List<String> masterSalesTrans) {
		if (masterSalesTrans == null || masterSalesTrans.size() == 0) {
			return new ArrayList<>();
		}
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		// List<String> trainIds=Arrays.asList(salesTranId,"23575201");
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_SOURCENOS_FOR_TRANIDS));
			sqlSelectQuery.setParameterList("1", masterSalesTrans);
			// List list =
			// sqlSelectQuery.setResultTransformer(Transformers.TO_LIST)
			// .list();
			List<String> list = (List<String>) sqlSelectQuery.list();

			if (list != null && !list.isEmpty()) {
				transaction.commit();
				return list;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	@Override
	public List<String> getSourcesNumbers(String masterSalesTrans) {
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		// List<String> trainIds=Arrays.asList(salesTranId,"23575201");
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_SOURCENOS_FOR_MASTERTRANIDS));
			sqlSelectQuery.setParameter(0, masterSalesTrans);
			// List list =
			// sqlSelectQuery.setResultTransformer(Transformers.TO_LIST)
			// .list();
			List<String> list = (List<String>) sqlSelectQuery.list();

			if (list != null && !list.isEmpty()) {
				transaction.commit();
				return list;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ArrayList<>();
	}

	@Override
	public ParentChildInfo getParentChildInfo(String customer_id, String sbuName) {

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		// List<String> trainIds=Arrays.asList(salesTranId,"23575201");
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_PARENT_CHILDS));
			sqlSelectQuery.setParameter(0, customer_id);
			sqlSelectQuery.setParameter(1, customer_id);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<ParentChildInfo> mapRow = new ParentChildInfoRowMapper().mapRow(list);
				transaction.commit();
				return mapRow.get(0);
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new ParentChildInfo();
	}

	@Override
	public Map<String, List<OrderItem>> getTransactionItems(List<String> salesTransIds) {
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_ORDER_ITEMS_LIST);
			sqlSelectQuery.setParameterList("1", salesTransIds);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				Map<String, List<OrderItem>> mapRow = new TransactionCountWRowMapper().mapRow(list);
				transaction.commit();
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return new HashMap<>();
	}

	@Override
	public List<CategoryDivisionVO> getCategories(String customerId) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_CATEGORIES);
			// sqlSelectQuery.setParameterList("1", salesTransIds);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<CategoryDivisionVO> mapRow = new CategoryDivisionVORowMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new ArrayList<>();
	}

	@Override
	public List<CategoryDivisionVO> getCategoriesByType(String categoryType) {

		return null;

	}

	@Override
	public CustomerRepResponse getAllRepCustomers(String repName) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		List<CustomerParentChildRewardsVo> customerParentChildRewardsVoList = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_REP_CUST_INFO);
			final SQLQuery sqlQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_CUST_CHILD_INFO);
			
			sqlSelectQuery.setString(0, repName);
			sqlQuery.setParameter(0, repName);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				CustomerRepResponse rep = new CustomerRepRowMappers().mapRow(list);
				List<Map<String, Object>> rewardsList = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != rewardsList && rewardsList.size() > Constants.ZERO)
					customerParentChildRewardsVoList = new CustomerRepRowMappers().mapRowRewardsData(rewardsList);
				if (null != customerParentChildRewardsVoList)
					rep.setCustomerRewards(customerParentChildRewardsVoList);
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return rep;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new CustomerRepResponse();
	}
	
	@Override
	public CustomerRepResponse getAllSamCustomers(String repName ,JQueryDataTableInputDTO dataTableInput) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		List<CustomerParentChildRewardsVo> customerParentChildRewardsVoList = null;
		CustomerRepResponse rep = new CustomerRepResponse();
		boolean totalCountFlag=false;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			StringBuilder SQL_DYNAMIC = new StringBuilder("");
			StringBuilder SQL_DYNAMIC_COUNT = new StringBuilder("");
			SQL_DYNAMIC			 
			.append(DaoQueryConstants.QUERY_FOR_REP_CUST_INFO_SERVERSIDE);
			SQL_DYNAMIC_COUNT			 
			.append(DaoQueryConstants.QUERY_FOR_REP_CUST_INFO_SERVERSIDE);
			final SQLQuery sqlQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_CUST_CHILD_INFO);
			
			if (dataTableInput.getiSortCol_0() != null	&& !dataTableInput.getiSortCol_0().equals("")) {
				
				if(dataTableInput.getiSortCol_0().equals("TM_ZN") && dataTableInput.getsSortDir_0().equalsIgnoreCase("asc"))
					 
				{
					SQL_DYNAMIC.append(" ORDER BY " +  " decode (" + dataTableInput.getiSortCol_0() +
							",'AST',1,'EST', 2, 'CST', 3 , 'MST', 4, 'PST', 5,6)" +
					" , HC.ACCOUNT_RANK  "+
					dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST) ");
				    SQL_DYNAMIC_COUNT.append("ORDER BY " +  " decode (" + dataTableInput.getiSortCol_0() +
							",'AST',1,'EST', 2, 'CST',3,'MST', 4, 'PST', 5,6)" +
					" , HC.ACCOUNT_RANK  "+
					dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST) ");
					
				}
				
				
				else if(dataTableInput.getiSortCol_0().equals("TM_ZN") && dataTableInput.getsSortDir_0().equalsIgnoreCase("desc"))
				{
					SQL_DYNAMIC.append(" ORDER BY " +  " decode (" + dataTableInput.getiSortCol_0() +
							",'PST',1,'MST', 2, 'CST', 3, 'EST',4, 'AST', 5,6)" +
					" , HC.ACCOUNT_RANK  "+
					dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST) ");
				    SQL_DYNAMIC_COUNT.append(" ORDER BY " +  " decode (" + dataTableInput.getiSortCol_0() +
							",'PST',1,'MST', 2, 'CST', 3, 'EST',4, 'AST', 5,6)" +
					" , HC.ACCOUNT_RANK  "+
					dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST) ");
					
				}
				else
				{
				
				SQL_DYNAMIC.append(" ORDER BY " + dataTableInput.getiSortCol_0() + " "
				+ dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST) ");
				SQL_DYNAMIC_COUNT.append(" ORDER BY " + dataTableInput.getiSortCol_0() + " "
						+ dataTableInput.getsSortDir_0() + " , HC.ACCOUNT_RANK NULLS LAST ) ");
				}
			}
			
			else{
				SQL_DYNAMIC.append(" ORDER BY HC.ACCOUNT_RANK NULLS LAST ) ");
				SQL_DYNAMIC_COUNT.append(" ORDER BY HC.ACCOUNT_RANK NULLS LAST ) ");
			}
			if (0 != (dataTableInput.getiDisplayStart()) && (dataTableInput.getsSearch() == null
					|| dataTableInput.getsSearch().equals(""))) {
				SQL_DYNAMIC
						.append(" ) cpp where ( rowno > "
								+ dataTableInput.getiDisplayStart()
								+ " AND rowno <= "
								+ (dataTableInput.getiDisplayStart() + dataTableInput
										.getiDisplayLength())+")");
			}else if (0 == dataTableInput.getiDisplayStart() && (dataTableInput.getsSearch() == null
					|| dataTableInput.getsSearch().equals("")) ) {
				SQL_DYNAMIC
				.append(" ) cpp where ( rowno >= "
						+ dataTableInput.getiDisplayStart()
						+ " AND rowno <= "
						+ (dataTableInput.getiDisplayStart() + dataTableInput
								.getiDisplayLength())+")");
			} else if (0 == dataTableInput.getiDisplayStart() && (dataTableInput.getsSearch() != null
					&& !dataTableInput.getsSearch().equals(""))) {
				SQL_DYNAMIC
				.append("  SAM_CP where SAM_CP.CUSTOMER_NUMBER LIKE '"
						+ dataTableInput.getsSearch()
						+ "%' OR UPPER(SAM_CP.Company_name) LIKE UPPER('%"
						+ dataTableInput.getsSearch() + "%') ) cpp where ( rowno >= "
						+ dataTableInput.getiDisplayStart()
						+ " AND rowno <= "
						+ (dataTableInput.getiDisplayStart() + dataTableInput
								.getiDisplayLength())+")");
				SQL_DYNAMIC_COUNT.append("  SAM_CP where SAM_CP.CUSTOMER_NUMBER LIKE '"
						+ dataTableInput.getsSearch()
						+ "%' OR UPPER(SAM_CP.Company_name) LIKE UPPER('%"
						+ dataTableInput.getsSearch() + "%') ) cpp ");
				        totalCountFlag=true;
			}else if(0 != dataTableInput.getiDisplayStart() && dataTableInput.getsSearch() != null
					&& !dataTableInput.getsSearch().equals("")){
				SQL_DYNAMIC
				.append("  SAM_CP where SAM_CP.CUSTOMER_NUMBER LIKE '"
						+ dataTableInput.getsSearch()
						+ "%' OR UPPER(SAM_CP.Company_name) LIKE UPPER('%"
						+ dataTableInput.getsSearch() + "%') ) cpp where ( rowno > "
						+ dataTableInput.getiDisplayStart()
						+ " AND rowno <= "
						+ (dataTableInput.getiDisplayStart() + dataTableInput
								.getiDisplayLength())+")");
				SQL_DYNAMIC_COUNT.append("  SAM_CP where SAM_CP.CUSTOMER_NUMBER LIKE '"
						+ dataTableInput.getsSearch()
						+ "%' OR UPPER(SAM_CP.Company_name) LIKE UPPER('%"
						+ dataTableInput.getsSearch() + "%') ) cpp ");
				totalCountFlag=true;
			}
			SQLQuery sqlSelectQuery = session.createSQLQuery(SQL_DYNAMIC.toString());
			//sqlSelectQuery.setString(0, repName);
			sqlSelectQuery.setParameter("accId", repName);
			sqlQuery.setParameter(0, repName);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null)
			 rep = new CustomerRepRowMappers().mapRow(list);
		    
			if (list != null && !list.isEmpty()) {
				
				List<Map<String, Object>> rewardsList = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != rewardsList && rewardsList.size() > Constants.ZERO)
					customerParentChildRewardsVoList = new CustomerRepRowMappers().mapRowRewardsData(rewardsList);
				if (null != customerParentChildRewardsVoList)
					rep.setCustomerRewards(customerParentChildRewardsVoList);
				
				
				
				Map<String, Object> paramMapForAgent = new HashMap<String, Object>();
				paramMapForAgent.put("accId", repName);
				SQLQuery totalCustomerQuery = (totalCountFlag == true)
						? session.createSQLQuery(
								"Select count(1) as TOTAL_CUST_COUNT from (" + SQL_DYNAMIC_COUNT.toString() + ")")
						: session.createSQLQuery("Select count(1) as TOTAL_CUST_COUNT from ("
								+ DaoQueryConstants.QUERY_FOR_REP_CUST_INFO_SERVERSIDE + "ORDER BY customer_number , HC.ACCOUNT_RANK NULLS LAST ) ) cpp)");
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
				
				rep.setTotalCount(((BigDecimal)objTotalCount).intValue());
				rep.setDisplayCount(((BigDecimal)objTotalCount).intValue());
				
				
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return rep;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return rep;
	}
	
	@Override
	public CustomerRepResponse getCustomerDetails(String repName, String custNum) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		List<CustomerParentChildRewardsVo> customerParentChildRewardsVoList = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_REP_SINGLE_CUST_INFO);
			final SQLQuery sqlQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_CUST_CHILD_INFO);
			sqlSelectQuery.setParameter(0, repName);
			sqlSelectQuery.setParameter(1, custNum);
			sqlQuery.setParameter(0, custNum);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				CustomerRepResponse rep = new CustomerRepRowMappers().mapRow(list);
				List<Map<String, Object>> rewardsList = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
				if (null != rewardsList && rewardsList.size() > Constants.ZERO)
					customerParentChildRewardsVoList = new CustomerRepRowMappers().mapRowRewardsData(rewardsList);
				if (null != customerParentChildRewardsVoList)
					rep.setCustomerRewards(customerParentChildRewardsVoList);
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return rep;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new CustomerRepResponse();
	}

	@Override
	public FiscalCategoryCalResponse getCcategoryFiscalMonthCals(String customer_id, String year) {
		// TODO Auto-generated method stub

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(DaoQueryConstants.QUERY_FOR_FISCAL_MONTH_CATEGORIES_SUM);
			sqlSelectQuery.setParameter(0, Integer.parseInt(year));
			sqlSelectQuery.setParameter(1, customer_id);
			sqlSelectQuery.setParameter(2, customer_id);
			sqlSelectQuery.setParameter(3, customer_id);
			sqlSelectQuery.setParameter(4, customer_id);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				FiscalCategoryCalResponse rep = new FiscalCategoryCalResponseMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return rep;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new FiscalCategoryCalResponse();
	}

	@Override
	public CategoryPercentageResponse getFiscalCategoryPercentage(String customer_id, String year) {
		// TODO Auto-generated method stub

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getCategories: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_CATEGORY_PERCENTAGES);
			sqlSelectQuery.setParameter(0, year);
			sqlSelectQuery.setParameter(1, customer_id);
			sqlSelectQuery.setParameter(2, customer_id);
			sqlSelectQuery.setParameter(3, customer_id);
			sqlSelectQuery.setParameter(4, customer_id);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				CategoryPercentageResponse rep = new CategoryPercentageMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getCategories: ");
				return rep;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new CategoryPercentageResponse();
	}

	@Override
	public FiscalYearResponse listLatestFiscalYears(String customer_id) {
		FiscalYearResponse response = new FiscalYearResponse();
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(replaceSchema(DaoQueryConstants.QUERY_FOR_LATEST_FISCAL_YEARS));
			sqlSelectQuery.setParameter(0, customer_id);
			sqlSelectQuery.setParameter(1, customer_id);
			sqlSelectQuery.setParameter(2, customer_id);
			List<BigDecimal> list = (List<BigDecimal>) sqlSelectQuery.list();
			if (list != null && !list.isEmpty()) {
				transaction.commit();
				List<String> fiscalYears = new ArrayList<>();
				for (BigDecimal string : list) {
					fiscalYears.add(string.toString());
				}
				response.setFiscalYears(fiscalYears);
				return response;
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return response;
	}

	@Override
	public HawkeyeDetailsVo getHawkeyeDetails(String customer_id) {
		// TODO Auto-generated method stub

		HawkeyeDetailsVo hawkeyeDetailsVo = null;
		List<CategoryDataVo> categoryDataVoList = null;
		Session session = null;
		try {
			session = sessionFactorycustexoSDC.openSession();
			SQLQuery sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_HAWKEYE_DETAILS);
			SQLQuery sqlQueryCat = session.createSQLQuery(SQLConstants.SQL_GET_HE_CATEGORY_DETAILS);
			sqlquery.setString(0, customer_id);
			sqlQueryCat.setString(0, customer_id);
			List<Map<String, Object>> hawkeyeDetailsVoList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			List<Map<String, Object>> categoryDataList = sqlQueryCat
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (null != hawkeyeDetailsVoList && hawkeyeDetailsVoList.size() > Constants.ZERO)
				hawkeyeDetailsVo = (HawkeyeDetailsVo) (((new HawkeyeDetailsVMapper()).mapRow(hawkeyeDetailsVoList))
						.get(0));
			if (null != categoryDataList && categoryDataList.size() > Constants.ZERO) {
				categoryDataVoList = (((new HawkeyeDetailsVMapper()).mapRowCatData(categoryDataList)));
				hawkeyeDetailsVo.setCategoryDataVo(categoryDataVoList);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return hawkeyeDetailsVo;

	}

	@Override
	public SuperUserDetailsVo getSuperUserDetailsSam(String customer_id) {
		// TODO Auto-generated method stub

		SuperUserDetailsVo superUserDetailsVo = null;
		List<StaplesDotcomActivityVo> staplesDotcomActivityVoList = null;
		Session session = null;
		try {
			session = sessionFactorycustexoSDC.openSession();
			SQLQuery sqlquery = session.createSQLQuery(SQLConstants.SQL_GET_SUPER_USER_DETAILS_SAM);

			sqlquery.setString(0, customer_id);
			List<Map<String, Object>> superUserDetailsVoList = sqlquery
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (null != superUserDetailsVoList && superUserDetailsVoList.size() > Constants.ZERO) {
				superUserDetailsVo = (SuperUserDetailsVo) (((new SuperUserSamRowmapper())
						.mapRow(superUserDetailsVoList)).get(0));
				staplesDotcomActivityVoList = (((new SuperUserSamRowmapper()).mapRowSkuData((superUserDetailsVoList))));
				superUserDetailsVo.setStaplesDotcomActivityVo(staplesDotcomActivityVoList);
			}

		} catch (HibernateException he) {
			throw new DashboardException(he);
		} catch (Exception e) {
			throw new DashboardException(e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

		return superUserDetailsVo;

	}

	@Override
	public String getCustomerLatestOrderDate(String customerId) {
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_LATEST_CUSTOMER_ORDER_DATE);
			sqlSelectQuery.setParameter(0, customerId);
			sqlSelectQuery.setParameter(1, customerId);
			sqlSelectQuery.setParameter(2, customerId);
			sqlSelectQuery.setParameter(3, customerId);
			List<String> results = (List<String>) sqlSelectQuery.list();

			if (results != null && !results.isEmpty()) {
				transaction.commit();
				return results.get(0);
			}
		} catch (Exception e) {
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return "";
	}

}
