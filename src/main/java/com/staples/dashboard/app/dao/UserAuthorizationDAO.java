package com.staples.dashboard.app.dao;

import com.staples.dashboard.exception.DashboardException;


/**
 * The Interface UserAuthorizationDAO.
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
public interface UserAuthorizationDAO {

	/**
	 * Method declaration to get role.
	 * 
	 * @param String userId
	 * @return String
	 * @throws DashboardException
	 */
	public String getRole(String userId) throws DashboardException;
}
