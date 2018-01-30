package com.staples.dashboard.app.dao;

import java.util.List;
import java.util.Map;

import com.staples.dashboard.dto.AccountUserDTO;
import com.staples.dashboard.dto.UserRoleDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Interface UserRoleDAO.
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
public interface UserRoleDAO {

	/**
	 * Assign Role to User
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return Integer
	 * @throws DashboardException
	 */
	public int assignUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException;

	/**
	 * Update the User Role
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return Integer
	 * @throws DashboardException
	 */
	public int updateUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException;

	/**
	 * Find User ID
	 * 
	 * @param String
	 *            UserID
	 * @return String
	 * @throws DashboardException
	 */
	public String findUserId(String userId) throws DashboardException;

	/**
	 * Remove User Role
	 * 
	 * @param UserRoleDTO
	 *            userRoleDTO
	 * @return Integer
	 * @throws DashboardException
	 */
	public int removeUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException;

	/**
	 * Update Screen Role Mapping
	 * 
	 * @param String
	 *            role
	 * @param List
	 *            <String>
	 * @return Integer
	 * @throws DashboardException
	 */
	public int updateRoleMappings(String role, List<String> roleMappingList)
			throws DashboardException;

	/**
	 * Get Assigned Screen Role Mapping
	 * 
	 * @param String
	 *            role
	 * @return List<String>
	 * @throws DashboardException
	 */
	public List<String> getAssignedRoleMapping(String role)
			throws DashboardException;

	/**
	 * Get all Screen URLs
	 * 
	 * @return Map<String,String>
	 * @throws DashboardException
	 */
	public Map<String, String> getScreenUrls() throws DashboardException;

	public List<AccountUserDTO> getUserHiererchy(String accountId, String level)
			throws DashboardException;

	public AccountUserDTO getUserDetail(String accountId)
			throws DashboardException;
	
	public List<String> findSegments()
			throws DashboardException;
	
	public List<String> findSegmentDescs(String segName)
			throws DashboardException;
}
