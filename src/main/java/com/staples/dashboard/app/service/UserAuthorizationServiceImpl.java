package com.staples.dashboard.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.dao.UserAuthorizationDAO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserAuthorizationServiceImpl.
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
@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

  @Autowired
  private UserAuthorizationDAO userDAO ;
  private static Logger logger = Logger.getLogger(UserAuthorizationServiceImpl.class);

  /**
  * Get Role based on User.
  * 
  * @param String userId
  * @return String
  * @throws DashboardException
  */
  @Override
  public String getRole(String userId) throws DashboardException {
    //logger.trace("Entering method :--> getRole()");
    String role = null;
    try {
      role = userDAO.getRole(userId);
    } catch (DashboardException e) {
      
      throw e;
    } catch (Exception e) {
      
      throw new DashboardException(e);
    } finally {
      //logger.trace("Exiting method :--> getRole()");
    }
    return role;
  }
}
