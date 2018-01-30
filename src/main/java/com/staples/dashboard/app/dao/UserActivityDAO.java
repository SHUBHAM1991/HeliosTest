package com.staples.dashboard.app.dao;

import org.springframework.security.core.context.SecurityContext;

import com.staples.dashboard.exception.DashboardException;

/**
 * The Interface UserActivityDAO.
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
public interface UserActivityDAO {

	/**
	 * Method declaration to log user activity.
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
	public String logUserActivity(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException;
	
	public String logUserActivityDotCom(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException;
	
	public String putUserCustContactActivity(String repId, String repRoleCode,
			String customerNumber, String loggedInUserName, String loggedInUserId, String cldDate, String fiscalContactedDate,
			SecurityContext Security) throws DashboardException;
	
	public String getLatestPageRefreshTimeStamp(String repId, String repRoleCode,
			 String pageNumber,	SecurityContext Security) throws DashboardException;
}
