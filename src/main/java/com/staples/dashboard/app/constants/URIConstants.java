package com.staples.dashboard.app.constants;

/**
 * The Class URIConstants.
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
public class URIConstants {
	public static final String ASSIGN_ROLE = 
		      "/helios/maintainUserRole/assign/{userId}/{lanId}/{firstName}/{role}/{lastName}";
		  public static final String FIND_USER = "/helios/maintainUserRole/findUser/{userId}";
		  public static final String UPDATE_ROLE = 
		      "/helios/maintainUserRole/update/{userId}/{role}";
		  public static final String VALIDATE_USER = 
		      "/helios/maintainUserRole/validate/{userId}/{paramLogin}";
		  public static final String REMOVE_ROLE = 
		      "/helios/maintainUserRole/removeUserRole/{prevUserId}";
		  
		  //Added by Jatinder
		  public static final String ASSIGN_ROLE_MAPPING = 
		      "/helios/maintainUserRole/assignMapping/{role}/{roleMappings}";
		  public static final String GET_ASSIGNED_ROLE_MAPPING = 
		      "/helios/maintainUserRole/getAssignedRoleMappings/{role}";
		  public static final String API = "/api";
		  
		  public static final String LOG_USER_ACTIVITY = "/logUserActivity";
		  
		  public static final String LOG_USER_ACTIVITY_DOTCOM = "/logUserActivityDotCom";
		  
		  public static final String USER_CONTACT_ACTIVITY= "/putUserContactActivity";
		  
		  public static final String FIND_SEGMENTS= "/findSegments";
		  public static final String FIND_SEGMENTSDESC= "/findSegmentDescs/{segName}";
}
