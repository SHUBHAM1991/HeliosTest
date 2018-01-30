package com.staples.dashboard.app.sdc.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.staples.dashboard.app.sdc.dao.mappers.CustomerRecommendationRowMapper;
import com.staples.dashboard.app.sdc.vo.LogRecommendationVO;
import com.staples.dashboard.app.sdc.vo.ProductVO;
import com.staples.dashboard.app.sdc.vo.RecommendationVO;

import net.sf.jasperreports.engine.fill.SimpleTextFormat;

@Repository
public class CustomerRecommendationDAOImpl implements CustomerRecommendationDAO{

	private static final Logger logger = Logger.getLogger(CustomerRecommendationDAOImpl.class);
	
	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwnDev")
	private SessionFactory sessionFactorycustexoHeliosOwnDev;
	
	@Autowired
	private CustomerRecommendationRowMapper customerRecommendationRowMapper;

	@Value("${FETCH_CUSTOMER_SKU_FOR_RECOMM_1}")
	private String FETCH_CUSTOMER_SKU_FOR_RECOMM_1;
	
	@Value("${FETCH_CUSTOMER_SKU_FOR_RECOMM_2}")
	private String FETCH_CUSTOMER_SKU_FOR_RECOMM_2;
	
	@Value("${FETCH_CUSTOMER_SKU_FOR_RECOMM_3}")
	private String FETCH_CUSTOMER_SKU_FOR_RECOMM_3;
	
	@Value("${RECOMM_SKU_DETAIL}")
	private String RECOMM_SKU_DETAIL;
	
	@Value("${LOG_RECOMM_SKU}")
	private String LOG_RECOMM_SKU;
	
	@Value("${GET_CUST_EMAIL}")
	private String  GET_CUST_EMAIL;
	
	@Value("${MAX_RECOMM_SKU}")
	private Integer threshHold;
	
	@Override
	public List<String> getCustomerSkus1(String customerId) {
		List<String> transList = null;

		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(FETCH_CUSTOMER_SKU_FOR_RECOMM_1);

			String newCustId = StringUtils.leftPad(customerId, 10, "0");
			sqlSelectQuery.setParameter(0, newCustId);
			sqlSelectQuery.setParameter(1, newCustId);
			sqlSelectQuery.setParameter(2, threshHold);

			List<String> list = sqlSelectQuery.list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				transList = list;

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
	public List<String> getCustomerSkus2(String customerId) {
		List<String> transList = null;

		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(FETCH_CUSTOMER_SKU_FOR_RECOMM_2);

			sqlSelectQuery.setParameter(0, customerId);
			sqlSelectQuery.setParameter(1, threshHold);

			List<String> list = sqlSelectQuery.list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				transList = list;

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
	public List<String> getCustomerSkus3(String customerId) {
		List<String> transList = null;

		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(FETCH_CUSTOMER_SKU_FOR_RECOMM_3);

			sqlSelectQuery.setParameter(0, customerId);
			sqlSelectQuery.setParameter(1, threshHold);
			
			List<String> list = sqlSelectQuery.list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				transList = list;

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
	public ProductVO fetchProductDetail(String sku) {
		ProductVO transList = null;
		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(RECOMM_SKU_DETAIL);

			sqlSelectQuery.setParameter(0, sku);

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				transList = customerRecommendationRowMapper.mapRow(list).get(0);
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
	public void logRecommendation(LogRecommendationVO logRecommendationVO) {
		List<String> transList = null;

		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			
			final SQLQuery sqlInsertQuery = session.createSQLQuery(LOG_RECOMM_SKU);
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
	public List<ProductVO> fetchProductDetail(List<ProductVO> prdtList) {
		List<ProductVO> transList = new ArrayList<ProductVO>();
		logger.info("Begin ****");
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(RECOMM_SKU_DETAIL);

			for(ProductVO prdt: prdtList){
				sqlSelectQuery.setParameter(0, prdt.getSku());
	
				List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	
				if (list != null && !list.isEmpty()) {
					logger.info("List size ****" + list.size());
					transList.add(customerRecommendationRowMapper.mapRow(list).get(0));
				}
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
	public Map<String, String> getCustomerEmail(String customerId) {
		logger.info("Begin ****");
		Map<String, String> map = new LinkedHashMap<String, String>();
		final Session session = sessionFactorycustexoHeliosOwnDev.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(GET_CUST_EMAIL);

			sqlSelectQuery.setParameter(0, customerId);

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

			if (list != null && !list.isEmpty()) {
				logger.info("List size ****" + list.size());
				Map<String, Object> resultMap = list.get(0);
				if (resultMap != null && !resultMap.isEmpty()) {
					map.put("EMAIL",resultMap.get("EMAILADDRESS")==null?null:resultMap.get("EMAILADDRESS").toString());
					map.put("REP",resultMap.get("SAM_SF_REP_EMP_NUM")==null?null:resultMap.get("SAM_SF_REP_EMP_NUM").toString());
				}

			}
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while fetching customer transactions", e);
			transaction.rollback();
		} finally {
			session.close();
		}

		return map;
	}
	

}
