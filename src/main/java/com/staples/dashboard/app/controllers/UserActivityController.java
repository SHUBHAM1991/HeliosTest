package com.staples.dashboard.app.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.staples.dashboard.app.constants.URIConstants;
import com.staples.dashboard.app.service.UserActivityService;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserActivityController.
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
@Controller
public class UserActivityController {
	private static final Logger LOGGER = Logger
			.getLogger(UserActivityController.class);

	@Autowired
	private UserActivityService userActivityService;

	/**
	 * Method used to log user activity.
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
	 */
	@RequestMapping(value = URIConstants.LOG_USER_ACTIVITY, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String logUserActivity(@RequestParam("repId") String repId,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageDescription") String pageDescription)
			throws DashboardException, Exception {
		String userActivityId = null;
		try {
			userActivityId = userActivityService.logUserActivity(repId,
					repRoleCode, customerNumber, pageNumber, pageDescription,
					SecurityContextHolder.getContext());
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw e;
		} finally {
			LOGGER.trace("Exiting method :--> logUserActivity()");
		}
		return userActivityId;
	}

	/**
	 * Method used to log user activity.
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
	 */
	@RequestMapping(value = URIConstants.LOG_USER_ACTIVITY_DOTCOM, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String logUserActivityDotCom(@RequestParam("repId") String repId,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageDescription") String pageDescription)
			throws DashboardException, Exception {
		String userActivityId = null;
		try {
			userActivityId = userActivityService.logUserActivityDotCom(repId,
					repRoleCode, customerNumber, pageNumber, pageDescription,
					SecurityContextHolder.getContext());
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw e;
		} finally {
			LOGGER.trace("Exiting method :--> logUserActivity()");
		}
		return userActivityId;
	}

	@RequestMapping(value = URIConstants.USER_CONTACT_ACTIVITY, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String putUserCustContactActivity(@RequestParam("repId") String repId,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("cldDate") String cldDate,
			@RequestParam("fiscalContactedDate") String fiscalContactedDate,
			@RequestParam("loggedInUserName") String loggedInUserName,
			@RequestParam("loggedInUserId") String loggedInUserId
			)
			throws DashboardException, Exception {
		String userActivityId = null;
		try {
			userActivityId = userActivityService.putUserCustContactActivity(repId,
					repRoleCode, customerNumber,loggedInUserName,loggedInUserId, cldDate, fiscalContactedDate,
					SecurityContextHolder.getContext());
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw e;
		} finally {
			LOGGER.trace("Exiting method :--> logUserActivity()");
		}
		return userActivityId;
	}
	
	
	@RequestMapping(value = "/getLatestPageRefreshTimeStamp", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getLatestPageRefreshTimeStamp(@RequestParam("repId") String repId,
			@RequestParam("repRoleCode") String repRoleCode,
			@RequestParam("pageNumber") String pageNumber
			)
			throws DashboardException, Exception {
		String userActivityId = null;
		try {
			userActivityId = userActivityService.getLatestPageRefreshTimeStamp(repId,
					repRoleCode, pageNumber, 
					SecurityContextHolder.getContext());
		} catch (DashboardException de) {

			throw de;
		} catch (Exception e) {

			throw e;
		} finally {
			LOGGER.trace("Exiting method :--> logUserActivity()");
		}
		return userActivityId;
	}
}
