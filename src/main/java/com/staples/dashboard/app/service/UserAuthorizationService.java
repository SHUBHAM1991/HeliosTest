package com.staples.dashboard.app.service;

import com.staples.dashboard.exception.DashboardException;


/**
 * The Interface UserAuthorizationService.
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
public interface UserAuthorizationService {

	/**
	* Get Role based on User.
	* 
	* @param userId
	* @return String
	* @throws DashboardException
	*/
	public String getRole(String userId)throws DashboardException;
}
