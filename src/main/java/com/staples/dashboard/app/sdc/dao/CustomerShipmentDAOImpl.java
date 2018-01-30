package com.staples.dashboard.app.sdc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.staples.dashboard.app.sdc.constants.DaoQueryConstants;
import com.staples.dashboard.app.sdc.dao.mappers.CustomerTransactionsRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.ShipmentLocationSumRowMapper;
import com.staples.dashboard.app.sdc.dao.mappers.ShipmentRowMapper;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.Shipment;
import com.staples.dashboard.app.utilities.Constants;

@Repository
public class CustomerShipmentDAOImpl implements CustomerShipmentDAO {

	private static final Logger logger = Logger.getLogger(CustomerSalesDAOImpl.class);

	@Autowired
	@Qualifier("sessionFactorycustexoSDC")
	private SessionFactory sessionFactorycustexoSDC;

	@Override
	public List<Shipment> getCustomerShipmentDetails(String customer_id, String sbuName) {
		// TODO Auto-generated method stub

		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		List<Shipment> transList = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_SALES_SHIPMENT);

			sqlSelectQuery.setParameter(0, customer_id);
			sqlSelectQuery.setParameter(1, customer_id);
			sqlSelectQuery.setParameter(2, customer_id);
			sqlSelectQuery.setParameter(3, customer_id);

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();

			if (list != null && !list.isEmpty()) {
				transList = new ShipmentRowMapper().mapRow(list);
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
	public List<CustomerOrders> getOrdersByShipmentLocation(String customerId, String location, String year) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		String custNum=customerId;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getOrdersFOrLocation: ");
			final SQLQuery sqlSelectQuery = session
					.createSQLQuery(DaoQueryConstants.QUERY_FOR_SHIPMENT_ORDERS_BY_LOCATION1);
			if (null != custNum && !("".equals(custNum))
					&& custNum.length() < Constants.TEN) {
				String format = String.format("%%0%dd", 10);
				custNum = String.format(format, Integer.parseInt(custNum));
			}
			sqlSelectQuery.setParameter(0, custNum);
			sqlSelectQuery.setParameter(1, custNum);
			/*sqlSelectQuery.setParameter(2, customerId);
			sqlSelectQuery.setParameter(3, customerId);*/
			
			if(location.equalsIgnoreCase("EMPTY LOCATION")){
				//location="EMPTYLOCATION";
			}
			sqlSelectQuery.setParameter(2, location);
			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<CustomerOrders> mapRow = new CustomerTransactionsRowMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getOrdersFOrLocation: ");
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching getOrdersFOrLocation", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new ArrayList<>();
	}

	@Override
	public List<Shipment> getShipmentLocOrderSum(String customerId, String fiscalYear) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getOrdersFOrLocation: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.QUERY_FOR_FISCAL_YEAR_ORDER_SUM);
			sqlSelectQuery.setParameter(0, customerId);
			sqlSelectQuery.setParameter(1, customerId);
			/*sqlSelectQuery.setParameter(2, customerId);
			sqlSelectQuery.setParameter(3, customerId);*/

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<Shipment> mapRow = new ShipmentLocationSumRowMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getOrdersFOrLocation: ");
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching getOrdersFOrLocation", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new ArrayList<>();
	}

	@Override
	public List<Shipment> getShipmentOrderCount(String customerId) {
		// TODO Auto-generate d method stub
		final Session session = sessionFactorycustexoSDC.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logger.info("started the results for getOrdersFOrLocation: ");
			final SQLQuery sqlSelectQuery = session.createSQLQuery(DaoQueryConstants.SHIPMENT_ORDER_COUNT);
			sqlSelectQuery.setParameter(0, customerId);
			sqlSelectQuery.setParameter(1, customerId);
			sqlSelectQuery.setParameter(2, customerId);
			sqlSelectQuery.setParameter(3, customerId);

			List<Map<String, Object>> list = sqlSelectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (list != null && !list.isEmpty()) {
				List<Shipment> mapRow = new ShipmentRowMapper().mapRow(list);
				transaction.commit();
				logger.info("got the results for getOrdersFOrLocation: ");
				return mapRow;
			}
		} catch (Exception e) {
			logger.error("Error while fetching getOrdersFOrLocation", e);
			transaction.rollback();
		} finally {
			session.close();
		}
		return new ArrayList<>();
	}

}
