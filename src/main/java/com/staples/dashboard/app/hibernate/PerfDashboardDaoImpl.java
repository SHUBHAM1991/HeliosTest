package com.staples.dashboard.app.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.dao.dbvo.PerfDashboardDAO;
import com.staples.dashboard.app.dao.mappers.PerfDashboardCategoryMapper;
import com.staples.dashboard.app.dao.mappers.StaplesDashBoardRowMapper;
import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;
import com.staples.dashboard.exception.DashboardException;

@Repository
@Transactional
public class PerfDashboardDaoImpl implements PerfDashboardDAO {

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	private static final Logger logger = Logger.getLogger(PerfDashboardDaoImpl.class);

	PerfDashboardDaoImpl() {

	}


	@Override
	public List<PerfDashboardCategoryVO> getSalesDataBossCoreCategory(String custNum, List<String> allCategoryList) throws DashboardException {
		List<PerfDashboardCategoryVO> bossCoreCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GET_ALLCATEGORY_SALESDATA_BARCHART;
			session = custExoHeliosOwnSessionFactory.openSession();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("allCategories", allCategoryList);
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new PerfDashboardCategoryMapper())) {
				if (null != object) {
					bossCoreCategoryVOList.add((PerfDashboardCategoryVO) object);
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

		return bossCoreCategoryVOList; 
	}
	

	@Override
	public List<PerfDashboardCategoryVO> getColumnAndDoughnutAllCategoryList(String custNum, List<String> allCategoryList) throws DashboardException {
		List<PerfDashboardCategoryVO> bossCoreCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GETALL_PERFCATEGORY_COLUMN_DOUGHNUT_CHART;
			session = custExoHeliosOwnSessionFactory.openSession();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("allCategories", allCategoryList);
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new PerfDashboardCategoryMapper())) {
				if (null != object) {
					bossCoreCategoryVOList.add((PerfDashboardCategoryVO) object);
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

		return bossCoreCategoryVOList; 
	}
	
	@Override
	public List<PerfDashboardCategoryVO> getCustInsightDataIfExist(String custNum,	List<String> allCategoryList) throws DashboardException {
		List<PerfDashboardCategoryVO> bossCoreCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GET_CUST_INSIGHT_DATA;
			session = custExoHeliosOwnSessionFactory.openSession();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("allCategories", allCategoryList);
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new PerfDashboardCategoryMapper())) {
				if (null != object) {
					bossCoreCategoryVOList.add((PerfDashboardCategoryVO) object);
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

		return bossCoreCategoryVOList; 
	}
	
	
	@Override
	public List<PerfDashboardCategoryVO> getWIREnabledFlag(String custNum) throws DashboardException {
		List<PerfDashboardCategoryVO> bossCoreCategoryVOList = new ArrayList<PerfDashboardCategoryVO>();
		Session session = null;
		try {
			String SQL = SQLConstants.SQL_GET_WIR_ENABLED_FLAG;
			session = custExoHeliosOwnSessionFactory.openSession();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custNum", custNum);

			for (Object object : getResultListParam(session, SQL, paramMap,
					new PerfDashboardCategoryMapper())) {
				if (null != object) {
					bossCoreCategoryVOList.add((PerfDashboardCategoryVO) object);
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

		return bossCoreCategoryVOList; 
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getResultListParam(Session session, String sql, Map<String, Object> paramMap, StaplesDashBoardRowMapper rowMapper)
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
			Iterator<Entry<String, Object>> paramItr = paramMap.entrySet().iterator();

			while (paramItr.hasNext()) {
				Map.Entry pair = paramItr.next();
				if (pair.getValue() instanceof List) {
					sqlQuery.setParameterList(pair.getKey().toString(),	(List) pair.getValue());
				} else {
					sqlQuery.setParameter(pair.getKey().toString(),	pair.getValue());
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


}

