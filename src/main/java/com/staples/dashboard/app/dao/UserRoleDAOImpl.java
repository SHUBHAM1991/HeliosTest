package com.staples.dashboard.app.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.dao.mappers.AccountUserMapper;
import com.staples.dashboard.app.dao.mappers.StaplesDashBoardRowMapper;
import com.staples.dashboard.dto.AccountUserDTO;
import com.staples.dashboard.dto.UserRoleDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserRoleDAOImpl.
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
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	private static final Logger logger = Logger
			.getLogger(UserRoleDAOImpl.class);

	/**
	 * This method is used to assign the role to user
	 * 
	 * @param userRoleDTO
	 *            This is the first parameter
	 * @return int This returns int after successful insertion in DB
	 * @throws DashboardException
	 */
	@Override
	public int assignUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException {

		// logger.trace("Entering method :--> assignUserRole()");
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		int cnt = 0;
		try {
			int role_id = getRoleId(userRoleDTO.getRole());
			int dltd_fl = 0;
			int count = 0;
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			transaction = session.beginTransaction();
			count = isUserExist(userRoleDTO);
			if (count != 0) {
				// logger.trace("User exists :--> updateUserRole()");
				updateUserRole(userRoleDTO);
			} else {
				// logger.trace("User does not exists :--> Add new user and role()");
				// logger.debug("SQL Query :--> " +
				// SQLConstants.SQL_USER_INSERT);
				final SQLQuery sqlUserInsert = session
						.createSQLQuery(SQLConstants.SQL_USER_INSERT);
				sqlUserInsert.setParameter(0, userRoleDTO.getUserId());
				sqlUserInsert.setParameter(1, role_id);
				sqlUserInsert.setParameter(2, userRoleDTO.getRole());
				sqlUserInsert.setParameter(3, userRoleDTO.getLanId());
				sqlUserInsert.setParameter(4, userRoleDTO.getLastName());
				sqlUserInsert.setParameter(5, userRoleDTO.getFirstName());
				sqlUserInsert.setParameter(6, ts);
				sqlUserInsert.setParameter(7, dltd_fl);
				sqlUserInsert.executeUpdate();
				// logger.debug("SQL Query :--> " +
				// SQLConstants.SQL_USER_MSTR_INSERT);
				final SQLQuery masterInsertQuery = session
						.createSQLQuery(SQLConstants.SQL_USER_MSTR_INSERT);
				masterInsertQuery.setParameter(0, userRoleDTO.getLanId());
				masterInsertQuery.setParameter(1, userRoleDTO.getLastName());
				masterInsertQuery.setParameter(2, userRoleDTO.getFirstName());
				masterInsertQuery.setParameter(3, userRoleDTO.getLanId());
				masterInsertQuery.setParameter(4, userRoleDTO.getLanId());
				masterInsertQuery.setParameter(5, date);
				masterInsertQuery.setParameter(6, date);
				masterInsertQuery.executeUpdate();
				transaction.commit();
			}
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(he);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(e);
			}
		} finally {
			session.close();
			// logger.trace("Exiting method :--> assignUserRole()");
		}
		return cnt;
	}

	/**
	 * This method is used to find whether the user exists or not
	 * 
	 * @param userRoleDTO
	 *            This is the first parameter
	 * @return int This returns int after successful execution
	 * @throws DashboardException
	 */
	public int isUserExist(UserRoleDTO userRoleDTO) throws DashboardException {
		// logger.trace("Entering method :--> isUserExist() for " +
		// userRoleDTO.getUserId());
		int cnt = 0;
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		try {
			logger.debug("SQL Query :--> " + SQLConstants.SQL_USERID_EXIST);
			final SQLQuery userExistQuery = session
					.createSQLQuery(SQLConstants.SQL_USERID_EXIST);
			userExistQuery.setParameter(0, userRoleDTO.getUserId());
			cnt = ((BigDecimal) userExistQuery.uniqueResult()).intValue();
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> isUserExist()");
		}
		return cnt;
	}

	/**
	 * This method is used to update the user role
	 * 
	 * @param UserRoleDTO
	 *            This is the first parameter
	 * @return int This returns int after successful execution
	 * @throws DashboardException
	 */
	@Override
	public int updateUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException {
		// logger.trace("Entering method :--> updateUserRole() for " +
		// userRoleDTO.getUserId()+ " to " + userRoleDTO.getRole());
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		int cnt = 0;
		int role_id = getRoleId(userRoleDTO.getRole());
		Date date = new java.sql.Timestamp(Calendar.getInstance().getTime()
				.getTime());
		try {
			final Timestamp ts = new Timestamp(date.getTime());
			transaction = session.beginTransaction();
			// logger.debug("SQL Query :--> " + SQLConstants.SQL_UPDATE_ROLE);
			final SQLQuery sqlUpdateUserRole = session
					.createSQLQuery(SQLConstants.SQL_UPDATE_ROLE);
			sqlUpdateUserRole.setParameter(0, role_id);
			sqlUpdateUserRole.setParameter(1, userRoleDTO.getRole());
			sqlUpdateUserRole.setParameter(2, ts);
			sqlUpdateUserRole.setParameter(3, userRoleDTO.getUserId());
			cnt = sqlUpdateUserRole.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(he);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(e);
			}
		} finally {
			session.close();
			// logger.trace("Exiting method :--> updateUserRole()");
		}
		return cnt;
	}

	/**
	 * This method is used to find the user ID, if exists then find the user
	 * role
	 * 
	 * @param userId
	 *            This is the first parameter
	 * @return String This returns the user role as String after successful
	 *         execution
	 * @throws DashboardException
	 */
	@Override
	public String findUserId(String userId) throws DashboardException {
		// logger.trace("Entering method :--> findUserId() for " + userId);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		int cnt = 0;
		String roleId = "";
		try {
			logger.debug("SQL Query :--> " + SQLConstants.SQL_FIND_USER);
			SQLQuery sqlFindUser = session
					.createSQLQuery(SQLConstants.SQL_FIND_USER);
			sqlFindUser.setParameter(0, Long.valueOf(userId));
			cnt = ((BigDecimal) sqlFindUser.uniqueResult()).intValue();
			if (cnt != 0) {
				// logger.trace("User found for " + userId +
				// " going to get role");
				logger.debug("SQL Query :--> " + SQLConstants.SQL_USER_ROLE);
				SQLQuery sqlUserRole = session
						.createSQLQuery(SQLConstants.SQL_USER_ROLE);
				sqlUserRole.setParameter(0, userId);
				@SuppressWarnings("rawtypes")
				List result = sqlUserRole.list();
				if (!result.isEmpty() && result.get(0) != null) {
					roleId = result.get(0).toString();
					// logger.trace("Role for " + userId + " is " + roleId);
				}
			}
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> findUserId()");
		}
		return roleId;
	}

	/**
	 * This method is used to get the Role ID
	 * 
	 * @param role
	 *            This is the first parameter
	 * @return int This returns int value as role id after successful execution
	 * @throws DashboardException
	 */
	public int getRoleId(String role) throws DashboardException {
		// logger.trace("Entering method :--> getRoleId() for " + role);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		int role_id = 0;
		try {
			StringBuilder sb = new StringBuilder(SQLConstants.SQL_ROLE_ID);
			sb.append("'" + role + "'" + " and DLTD_FL=0");
			logger.debug("SQL Query :--> " + sb.toString());
			final SQLQuery sqlRoleID = session.createSQLQuery(sb.toString());
			role_id = ((BigDecimal) sqlRoleID.uniqueResult()).intValue();
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getRoleId()");
		}
		return role_id;
	}

	/**
	 * This method is used to remove the user role
	 * 
	 * @param UserRoleDTO
	 *            This is the first parameter
	 * @return int This returns int value on after it removes the role
	 *         successfully.
	 * @throws DashboardException
	 */
	@Override
	public int removeUserRole(UserRoleDTO userRoleDTO)
			throws DashboardException {
		// logger.trace("Entering method :--> removeUserRole() for " +
		// userRoleDTO.getUserId());
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		int cnt = 0;
		Date date = new java.sql.Timestamp(Calendar.getInstance().getTime()
				.getTime());
		try {
			logger.debug("SQL Query :--> " + SQLConstants.SQL_REMOVE_ROLE);
			Timestamp ts = new Timestamp(date.getTime());
			transaction = session.beginTransaction();
			final SQLQuery sqlRemoveRole = session
					.createSQLQuery(SQLConstants.SQL_REMOVE_ROLE);
			sqlRemoveRole.setParameter(0, ts);
			sqlRemoveRole.setParameter(1, userRoleDTO.getUserId());
			cnt = sqlRemoveRole.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(he);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(e);
			}
		} finally {
			session.close();
			// logger.trace("Exiting method :--> removeUserRole()");
		}
		return cnt;
	}

	/**
	 * This method is used to delete old and insert new role screen mappings.
	 * 
	 * @param role
	 *            This is the first parameter that denotes the the role as a
	 *            String
	 * @param roleMappingList
	 *            This is the second parameter passed as List of String objects
	 * @return int This returns int value after it executes successfully.
	 * @throws DashboardException
	 */
	@Override
	public int updateRoleMappings(String role, List<String> roleMappingList)
			throws DashboardException {
		// logger.trace("Entering method :--> updateRoleMappings()");
		int count = 0;
		try {
			deleteOldRoleScreenMapping(role);
			List<String> roleScreenList = new ArrayList<String>();
			for (String screenName : roleMappingList) {
				roleScreenList.add("/" + screenName);
			}
			count = insertNewRoleScreenMapping(role, roleScreenList);
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			// logger.trace("Exiting method :--> updateRoleMappings()");
		}
		return count;
	}

	/**
	 * This method is used to delete old role screen mappings.
	 * 
	 * @param role
	 *            This parameter is used to get the ID from table of role
	 * @return int This returns int value after it executes successfully.
	 * @throws DashboardException
	 */
	private int deleteOldRoleScreenMapping(String role)
			throws DashboardException {
		// logger.trace("Entering method :--> deleteOldRoleScreenMapping() for "
		// + role);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		int count = 0;
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_DELETE_ROLE_MENU_LINK);
			transaction = session.beginTransaction();
			SQLQuery sqlDeleteRoleMenuLink = session
					.createSQLQuery(SQLConstants.SQL_DELETE_ROLE_MENU_LINK);
			sqlDeleteRoleMenuLink.setParameter(0, role);
			count = sqlDeleteRoleMenuLink.executeUpdate();
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(he);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(e);
			}
		} finally {
			session.close();
			// logger.trace("Exiting method :--> deleteOldRoleScreenMapping()");
		}
		return count;
	}

	/**
	 * This method is used to insert new role screen mappings.
	 * 
	 * @param role
	 *            This parameter is used to get the ID from table of role
	 * @param roleMappingList
	 *            This contains all new screen role mapping names and after
	 *            picking the ID of the screen role it will insert into table.
	 * @return int This returns int value after it executes successfully.
	 * @throws DashboardException
	 */
	private int insertNewRoleScreenMapping(String role,
			List<String> roleMappingList) throws DashboardException {
		// logger.trace("Entering method :--> insertNewRoleScreenMapping() for "
		// + role);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		Transaction transaction = null;
		int count = 0;
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_INSERT_ROLE_MENU_LINK);
			transaction = session.beginTransaction();
			for (String screenName : roleMappingList) {
				SQLQuery sqlInsertRoleMenuLink = session
						.createSQLQuery(SQLConstants.SQL_INSERT_ROLE_MENU_LINK);
				sqlInsertRoleMenuLink.setParameter(0, role);
				sqlInsertRoleMenuLink.setParameter(1, screenName);
				count = sqlInsertRoleMenuLink.executeUpdate();
			}
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(he);
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

				throw new DashboardException(e);
			}
		} finally {
			session.close();
			// logger.trace("Exiting method :--> insertNewRoleScreenMapping()");
		}
		return count;
	}

	/**
	 * This method is used to get all role screen mappings.
	 * 
	 * @param role
	 *            This parameter is used to get the ID from table of role and
	 *            according to that it gets all the role screen mapping names
	 * @return List<String> This returns List of String objects after it
	 *         executes successfully.
	 * @throws DashboardException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAssignedRoleMapping(String role)
			throws DashboardException {
		// logger.trace("Entering method :--> getAssignedRoleMapping() for " +
		// role);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		List<String> roleScreenList = new ArrayList<String>();
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_ASSIGNED_ROLE_MAPPINGS);
			SQLQuery sqlScreenList = session
					.createSQLQuery(SQLConstants.SQL_GET_ASSIGNED_ROLE_MAPPINGS);
			sqlScreenList.setParameter(0, role);
			roleScreenList = sqlScreenList.list();
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return roleScreenList;
	}

	/**
	 * This method is used to get all Screen URLs
	 * 
	 * @return Map<String,String> This returns Map of String objects after it
	 *         executes successfully.
	 * @throws DashboardException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getScreenUrls() throws DashboardException {
		// logger.trace("Entering method :--> getScreenUrls()");
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		StringBuilder role = null;
		Map<String, String> screenURLMap = new HashMap<String, String>();
		try {
			logger.debug("SQL Query :--> " + SQLConstants.SQL_FETCH_URL_ROLES);
			List<List<Object>> urlRoleList = session
					.createSQLQuery(SQLConstants.SQL_FETCH_URL_ROLES)
					.setResultTransformer(Transformers.TO_LIST).list();
			for (List<Object> urlRoles : urlRoleList) {
				if (screenURLMap.containsKey(urlRoles.get(0))) {
					role.append("," + (String) urlRoles.get(1));
					screenURLMap.put((String) urlRoles.get(0), role.toString());
				} else {
					role = new StringBuilder();
					role.append((String) urlRoles.get(1));
					screenURLMap.put((String) urlRoles.get(0), role.toString());
				}
			}
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getScreenUrls()");
		}
		return screenURLMap;
	}

	/**
	 * This method is used to get all role screen mappings.
	 * 
	 * @param role
	 *            This parameter is used to get the ID from table of role and
	 *            according to that it gets all the role screen mapping names
	 * @return List<String> This returns List of String objects after it
	 *         executes successfully.
	 * @throws DashboardException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountUserDTO> getUserHiererchy(String accountId, String level)
			throws DashboardException {
		// logger.trace("Entering method :--> getAssignedRoleMapping() for " +
		// role);
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		List<AccountUserDTO> userHiererchy = new ArrayList<AccountUserDTO>();
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_USER_HIERERCHY);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accountNO", accountId);
			paramMap.put("level", level);
			for (Object object : getResultListParam(session,
					SQLConstants.SQL_GET_USER_HIERERCHY, paramMap,
					new AccountUserMapper())) {
				if (null != object) {
					userHiererchy.add((AccountUserDTO) object);
				}
			}
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return userHiererchy;
	}

	@Override
	public AccountUserDTO getUserDetail(String accountId)
			throws DashboardException {
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		AccountUserDTO userDetail = new AccountUserDTO();
		try {
			logger.debug("SQL Query :--> " + SQLConstants.SQL_GET_USER_DETAIL);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accountNO", accountId);
			for (Object object : getResultListParam(session,
					SQLConstants.SQL_GET_USER_DETAIL, paramMap,
					new AccountUserMapper())) {
				if (null != object) {
					userDetail = (AccountUserDTO) object;
				}
			}
		} catch (HibernateException he) {

			// throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return userDetail;
	}

	/**
	 * Method implementation to fetch result list.
	 * 
	 * @param Session
	 *            session
	 * @param String
	 *            sql
	 * @param Map
	 *            <String, Object> paramMap
	 * @param StaplesDashBoardRowMapper
	 *            rowMapper
	 * @return List<Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> getResultListParam(Session session, String sql,
			Map<String, Object> paramMap, StaplesDashBoardRowMapper rowMapper)
			throws HibernateException, Exception {
		logger.trace("Entering method :--> getResultList()");
		List<Object> resultList = new ArrayList<Object>();
		boolean sessionFlag = false;
		try {
			if (session == null) {
				sessionFlag = true;
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			Iterator<Entry<String, Object>> paramItr = paramMap.entrySet()
					.iterator();
			while (paramItr.hasNext()) {
				Map.Entry pair = paramItr.next();
				if (pair.getValue() instanceof List) {
					sqlQuery.setParameterList(pair.getKey().toString(),
							(List) pair.getValue());
				} else {
					sqlQuery.setParameter(pair.getKey().toString(),
							pair.getValue());
				}

			}
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}

		} catch (HibernateException he) {
			logger.error("Error in method getResultListParam()", he);
			throw he;
		} catch (Exception e) {
			logger.error("Error in method getResultListParam()", e);
			throw e;
		} finally {
			if (sessionFlag == true && session != null && session.isOpen())
				session.close();
			logger.trace("Exiting method :--> getResultListParam()");
		}
		return resultList;
	}
	/**
	 * This method is used to get all role screen mappings.
	 * 
	 * @param role
	 *            This parameter is used to get the ID from table of role and
	 *            according to that it gets all the role screen mapping names
	 * @return List<String> This returns List of String objects after it
	 *         executes successfully.
	 * @throws DashboardException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findSegments()
			throws DashboardException {

		final Session session = custExoHeliosOwnSessionFactory.openSession();
		List<String> segList = new ArrayList<String>();
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_SEGMENTS);
			SQLQuery sqlScreenList = session
					.createSQLQuery(SQLConstants.SQL_GET_SEGMENTS);
			segList = sqlScreenList.list();
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return segList;
	}

	@Override
	public List<String> findSegmentDescs(String segName) throws DashboardException {
		// TODO Auto-generated method stub
		final Session session = custExoHeliosOwnSessionFactory.openSession();
		List<String> segDescList = new ArrayList<String>();
		try {
			logger.debug("SQL Query :--> "
					+ SQLConstants.SQL_GET_SEGMENTDESCS);
			SQLQuery sqlScreenList = session
					.createSQLQuery(SQLConstants.SQL_GET_SEGMENTDESCS);
			sqlScreenList.setString(0, segName);
			segDescList = sqlScreenList.list();
		} catch (HibernateException he) {

			throw new DashboardException(he);
		} catch (Exception e) {

			throw new DashboardException(e);
		} finally {
			session.close();
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return segDescList;
	}
}