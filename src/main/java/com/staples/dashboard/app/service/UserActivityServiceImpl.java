package com.staples.dashboard.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.dao.UserActivityDAO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserActivityServiceImpl.
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
@Service
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	private UserActivityDAO userActivityDAO;
	private static Logger logger = Logger
			.getLogger(UserActivityServiceImpl.class);

	/**
	 * This method is override to log user the activity.
	 * 
	 * @param repId
	 * @param repRoleCode
	 * @param customerNumber
	 * @param pageNumber
	 * @return String
	 * @throws DashboardException
	 */
	@Override
	public String logUserActivity(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException {
		// TODO Auto-generated method stub
		String userActivityId;
		try {

			userActivityId = userActivityDAO.logUserActivity(repId,
					repRoleCode, customerNumber, pageNumber, pageDescription,
					Security);
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw new DashboardException(e);
		}

		return userActivityId;
	}


	/**
	 * This method is override to log user the activity.
	 * 
	 * @param repId
	 * @param repRoleCode
	 * @param customerNumber
	 * @param pageNumber
	 * @return String
	 * @throws DashboardException
	 */
	@Override
	public String logUserActivityDotCom(String repId, String repRoleCode,
			String customerNumber, String pageNumber, String pageDescription,
			SecurityContext Security) throws DashboardException {
		// TODO Auto-generated method stub
		String userActivityId;
		try {

			userActivityId = userActivityDAO.logUserActivityDotCom(repId,
					repRoleCode, customerNumber, pageNumber, pageDescription,
					Security);
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw new DashboardException(e);
		}

		return userActivityId;
	}

	@Override
	public String putUserCustContactActivity(String repId, String repRoleCode,
			String customerNumber,String loggedInUserName,String loggedInUserId, String cldDate, String fiscalContactedDate,
			SecurityContext Security) throws DashboardException {
		String putStatus;
		try {

			putStatus = userActivityDAO.putUserCustContactActivity(repId, repRoleCode, customerNumber,loggedInUserName,loggedInUserId, cldDate, fiscalContactedDate, Security);
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw new DashboardException(e);
		}

		return putStatus;
	}


	@Override
	public String getLatestPageRefreshTimeStamp(String repId, String repRoleCode, String pageNumber,
			SecurityContext Security) throws DashboardException {
		String latestPageRefreshTimeStamp;
		try {

			latestPageRefreshTimeStamp = userActivityDAO.getLatestPageRefreshTimeStamp(repId,
					repRoleCode, pageNumber,	Security);
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw new DashboardException(e);
		}

		return latestPageRefreshTimeStamp;
	}

}
