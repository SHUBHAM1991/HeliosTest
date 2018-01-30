package com.staples.dashboard.app.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.dao.UserRoleDAO;
import com.staples.dashboard.dto.AccountUserDTO;
import com.staples.dashboard.dto.UserRoleDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserRoleServiceImpl.
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
 *          <td>Dec 4, 2015</td>
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
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDAO userRoleDAO;
	private static Logger logger = Logger.getLogger(UserRoleServiceImpl.class);

	/**
	 * This method is used to find the user id
	 * 
	 * @param UserID
	 *            userId
	 * @return String
	 * @throws DashboardException
	 */
	@Override
	public String findUser(String userId) throws DashboardException {
		// logger.trace("Entering method :--> findUser()");
		String find_User = null;
		try {
			find_User = userRoleDAO.findUserId(userId);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> findUser()");
		}
		return find_User;
	}

	/**
	 * This method is used to update the user role
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return int
	 * @throws DashboardException
	 */
	@Override
	public int updateRole(UserRoleDTO userRoleDTO) throws DashboardException {
		// logger.trace("Entering method :--> updateRole()");
		int update_Role = 0;
		try {
			update_Role = userRoleDAO.updateUserRole(userRoleDTO);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> updateRole()");
		}
		return update_Role;
	}

	/**
	 * This method is used to assign the role to user
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return int
	 * @throws DashboardException
	 */
	@Override
	public int assignRole(UserRoleDTO userRoleDTO) throws DashboardException {
		// logger.trace("Entering method :--> assignRole()");
		int assign_Role = 0;
		try {
			assign_Role = userRoleDAO.assignUserRole(userRoleDTO);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> assignRole()");
		}
		return assign_Role;
	}

	/**
	 * This method is used to remove the user role
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return int
	 * @throws DashboardException
	 */
	@Override
	public int removeUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException {
		// logger.trace("Entering method :--> removeUserRole()");
		int remove_Role = 0;
		try {
			remove_Role = userRoleDAO.removeUserRole(userRoleDTO);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> removeUserRole()");
		}
		return remove_Role;
	}

	/**
	 * This method is used to insert new and delete old screen mappings
	 * 
	 * @param String
	 *            role
	 * @param List
	 *            <String> roleMappingList
	 * @return int
	 * @throws DashboardException
	 */
	@Override
	public int updateRoleMappings(String role, List<String> roleMappingList)
			throws DashboardException {
		// logger.trace("Entering method :--> updateRoleMappings()");
		int updateRoleMapping = 0;
		try {
			updateRoleMapping = userRoleDAO.updateRoleMappings(role,
					roleMappingList);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> updateRoleMappings()");
		}
		return updateRoleMapping;
	}

	/**
	 * This method is used to get all screen mappings
	 * 
	 * @param String
	 *            role
	 * @return List<Striing>
	 * @throws DashboardException
	 */
	@Override
	public List<String> getAssignedRoleMapping(String role)
			throws DashboardException {
		// logger.trace("Entering method :--> getAssignedRoleMapping()");
		List<String> roleMappingList = null;
		try {
			roleMappingList = userRoleDAO.getAssignedRoleMapping(role);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return roleMappingList;
	}

	/**
	 * This method is used to get all screen URLs
	 * 
	 * @return Map<String,String>
	 * @throws DashboardException
	 */
	@Override
	public Map<String, String> getScreenUrls() throws DashboardException {
		// logger.trace("Entering method :--> getScreenUrls()");
		Map<String, String> screenUrls = null;
		try {
			screenUrls = userRoleDAO.getScreenUrls();
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> getScreenUrls()");
		}
		return screenUrls;
	}

	@Override
	public List<AccountUserDTO> getUserHiererchy(String accountId, String level)
			throws DashboardException {
		return userRoleDAO.getUserHiererchy(accountId, level);
	}

	@Override
	public AccountUserDTO getUserDetail(String accountId)
			throws DashboardException {
		return userRoleDAO.getUserDetail(accountId);
	}

	@Override
	public List<String> findSegments() throws DashboardException {
		// TODO Auto-generated method stub
		List<String> segList = null;
		try {
		   segList = userRoleDAO.findSegments();
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return segList;
	}

	@Override
	public List<String> findSegmentDescs(String segName) throws DashboardException {
		// TODO Auto-generated method stub
		List<String> segDescList = null;
		try {
		   segDescList = userRoleDAO.findSegmentDescs(segName);
		} catch (DashboardException e) {

			throw e;
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return segDescList;
	}

}
