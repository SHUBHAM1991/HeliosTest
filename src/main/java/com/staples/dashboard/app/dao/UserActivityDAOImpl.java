package com.staples.dashboard.app.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserActivityDAOImpl.
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
 *          <td>Dec 14, 2015</td>
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
public class UserActivityDAOImpl implements UserActivityDAO {

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	private static Logger logger = Logger.getLogger(UserActivityDAOImpl.class);

	/**
	 * Method implementation to log user activity.
	 * 
	 * @param String
	 *            repId
	 * @param String
	 *            repRoleCode
	 * @param String
	 *            customerNumber
	 * @param String
	 *            pageNumber
	 * @param String
	 *            pageDescription
	 * @return String
	 * @throws DashboardException
	 */
	@Override
	public String logUserActivity(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException {
		// TODO Auto-generated method stub
		String userActivityId = null;
		int numOfRecords;
		Session session = null;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			String userName = ((UserDetails) Security.getAuthentication()
					.getPrincipal()).getUsername();
			String insertQueryString = SQLConstants.SQL_LOG_USER_ACTIVITY;
			SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);

			sqlQuery.setParameter(0, repId);
			sqlQuery.setParameter(1, repRoleCode);
			sqlQuery.setParameter(2, customerNumber);
			sqlQuery.setParameter(3, pageNumber);
			sqlQuery.setParameter(4, pageDescription);
			sqlQuery.setParameter(5, userName);
			numOfRecords = sqlQuery.executeUpdate();

			return String.valueOf(numOfRecords);
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


	/**
	 * Method implementation to log user activity.
	 * 
	 * @param String
	 *            repId
	 * @param String
	 *            repRoleCode
	 * @param String
	 *            customerNumber
	 * @param String
	 *            pageNumber
	 * @param String
	 *            pageDescription
	 * @return String
	 * @throws DashboardException
	 */
	@Override
	public String logUserActivityDotCom(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException {
		// TODO Auto-generated method stub
		String userActivityId = null;
		int numOfRecords;
		Session session = null;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			String userName = ((UserDetails) Security.getAuthentication()
					.getPrincipal()).getUsername();
			String insertQueryString = SQLConstants.SQL_LOG_USER_ACTIVITY_DOTCOM;
			SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);

			sqlQuery.setParameter(0, repId);
			sqlQuery.setParameter(1, repRoleCode);
			sqlQuery.setParameter(2, customerNumber);
			sqlQuery.setParameter(3, pageNumber);
			sqlQuery.setParameter(4, pageDescription);
			sqlQuery.setParameter(5, userName);
			numOfRecords = sqlQuery.executeUpdate();

			return String.valueOf(numOfRecords);
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


	/*"INSERT INTO HELIOS_OWN.SA_TASK_TRACKER(CUSTOMER_NUMBER,DIVISION,SLS_REP_ID,SLS_REP_NAME,SLS_REP_ROLE_CD,LOGGED_IN_USR_ID,"
			+ "LOGGED_IN_USR_NAME,LOGGED_IN_USR_ROLE,CHECKED_IN_DATE,INSERT_DATE,FSC_YR,FSC_PRD,FSC_WK,FSC_DY) "
			+ " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
	
	
	@Override
	public String putUserCustContactActivity(String repId, String repRoleCode,
			String customerNumber ,String loggedInUserName, String loggedInUserId, String cldDate, String fiscalContactedDate,
			SecurityContext Security) throws DashboardException {
		int numOfRecords;
		Session session = null;

		try {
			session = custExoHeliosOwnSessionFactory.openSession();
			String userId = ((UserDetails) Security.getAuthentication()
					.getPrincipal()).getUsername();
			String selectQueryString = SQLConstants.SQL_SELECT_REP_INFO;
			SQLQuery sqlSelectQuery = session.createSQLQuery(selectQueryString);
			sqlSelectQuery.setParameter(0, userId);
			Object[] slsRepInfo = (Object[]) sqlSelectQuery.uniqueResult();
			
			if(userId.equalsIgnoreCase(loggedInUserId)){
				String insertQueryString = SQLConstants.SQL_INSERT_LATEST_CONTACTED_DETAILS;
				
				SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);
				String fiscalConArr[]=fiscalContactedDate.split("-");
				sqlQuery.setParameter(0, customerNumber);
				sqlQuery.setParameter(1, "DUMMY_DIV");
				sqlQuery.setParameter(2, userId);
				sqlQuery.setParameter(3, slsRepInfo[0].toString());
				sqlQuery.setParameter(4, repRoleCode);
				sqlQuery.setParameter(5, loggedInUserId);
				sqlQuery.setParameter(6, loggedInUserName);
				sqlQuery.setParameter(7, repRoleCode);
				//sqlQuery.setParameter(8, cldDate);
				//sqlQuery.setParameter(9, cldDate);
				sqlQuery.setParameter(8, fiscalConArr[0]);
				sqlQuery.setParameter(9, fiscalConArr[1]);
				sqlQuery.setParameter(10, fiscalConArr[2]);
				sqlQuery.setParameter(11, fiscalConArr[3]);
				numOfRecords = sqlQuery.executeUpdate();

			}else{
				String insertQueryString = SQLConstants.SQL_INSERT_LATEST_CONTACTED_DETAILS_SPL_USER;
				
				SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);
				String fiscalConArr[]=fiscalContactedDate.split("-");
				sqlQuery.setParameter(0, customerNumber);
				sqlQuery.setParameter(1, "DUMMY_DIV");
				sqlQuery.setParameter(2, userId);
				sqlQuery.setParameter(3, slsRepInfo[0].toString());
				sqlQuery.setParameter(4, slsRepInfo[1].toString());
				//sqlQuery.setParameter(5, cldDate);
				//sqlQuery.setParameter(6, cldDate);
				sqlQuery.setParameter(5, fiscalConArr[0]);
				sqlQuery.setParameter(6, fiscalConArr[1]);
				sqlQuery.setParameter(7, fiscalConArr[2]);
				sqlQuery.setParameter(8, fiscalConArr[3]);
				numOfRecords = sqlQuery.executeUpdate();

			}
				return String.valueOf(numOfRecords);
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
	public String getLatestPageRefreshTimeStamp(String repId, String repRoleCode, String pageNumber,
			SecurityContext Security) throws DashboardException {
		// TODO Auto-generated method stub
				String userActivityId = null;
				List<String> result=null;
				Session session = null;

				try {
					session = custExoHeliosOwnSessionFactory.openSession();
					String userName = ((UserDetails) Security.getAuthentication()
							.getPrincipal()).getUsername();
					String insertQueryString = SQLConstants.SQL_GET_LATEST_REFRESH_TIMESTAMP;
					SQLQuery sqlQuery = session.createSQLQuery(insertQueryString);
					sqlQuery.setParameter("repId", repId);
					sqlQuery.setParameter("repCd", repRoleCode);
					sqlQuery.setParameter("pageNum", pageNumber);
					sqlQuery.setParameter("logUser", userName);
					
					result =sqlQuery.list();
                    if(null!=result && result.size()> 0){
					  return result.get(Constants.ZERO);
                    }else
	                  return MapperConstants.BLANK_STRING;
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

}
