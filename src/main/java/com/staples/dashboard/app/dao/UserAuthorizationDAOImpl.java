package com.staples.dashboard.app.dao;

import com.staples.dashboard.exception.DashboardException;
import com.staples.dashboard.app.constants.SQLConstants;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.hibernate.SessionFactory;






import java.util.Iterator;


/**
 * The Class UserAuthorizationDAOImpl.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
@Repository
public class UserAuthorizationDAOImpl implements UserAuthorizationDAO {
	
	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	private static Logger logger = Logger.getLogger(UserAuthorizationDAOImpl.class);

	/**
	 * Method implementation to get role.
	 * 
	 * @param String userId
	 * @return String
	 * @throws DashboardException
	 */
	public String getRole(String userId) throws DashboardException {
    //logger.trace("Entering method :--> getRole() for " + userId);
    final Session session = custExoHeliosOwnSessionFactory.openSession();
    String role = null;
    
    if (null != userId && "" != userId && userId != " ") {
      //logger.debug("SQL Query :--> " + SQLConstants.SQL_SELECT_USER_ROLE);
      final SQLQuery sqlQuery = session.createSQLQuery(SQLConstants.SQL_SELECT_USER_ROLE);
      sqlQuery.setParameter(0, userId); 
      try {
        @SuppressWarnings("unchecked")
        Iterator<String> al = sqlQuery.list().iterator();
        while (al.hasNext()) {
          role = (al.next()).toString();
        }
        //logger.trace("Role for " + userId + " is " + role);
      } catch (HibernateException he) {
        
        throw new DashboardException(he);
      } catch (Exception e) {
        
        throw new DashboardException(e);
      } finally {
        session.close();
        //logger.trace("Exiting method :--> getRole()");
      }
    }
    return role;
  }
}