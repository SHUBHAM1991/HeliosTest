package com.staples.dashboard.app.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.staples.dashboard.exception.DashboardException;

/**
 * The Interface UserActivityService.
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
public interface UserActivityService {

	/**
	 * This method is used to log user the activity.
	 * 
	 * @param repId
	 * @param repRoleCode
	 * @param customerNumber
	 * @param pageNumber
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
			String customerNumber, String cldDate, String loggedInUserName, String loggedInUserId, String fiscalContactedDate,
			SecurityContext Security) throws DashboardException;
	
	public String getLatestPageRefreshTimeStamp(String repId, String repRoleCode,
			 String pageNumber, SecurityContext Security) throws DashboardException;
}
