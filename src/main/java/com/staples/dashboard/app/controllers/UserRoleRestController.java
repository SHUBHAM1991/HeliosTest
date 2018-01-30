package com.staples.dashboard.app.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.staples.dashboard.app.constants.URIConstants;
import com.staples.dashboard.app.service.UserRoleService;
import com.staples.dashboard.dto.AddReceipt;
import com.staples.dashboard.dto.UserRoleDTO;
import com.staples.dashboard.exception.DashboardException;

/**
 * The Class UserRoleRestController.
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
@Controller
@RequestMapping(value = URIConstants.API)
public class UserRoleRestController {

  private static final Logger logger = Logger.getLogger(UserRoleRestController.class);
  private static UserRoleDTO userRoleDTO;

  @Autowired
  private UserRoleService userRoleService;

  /**
   * Method used to assign role to user.
   * @param String userId
   * @param String lanId
   * @param String firstName
   * @param String role
   * @param String lastName
   * @param HttpServletResponse response
   * @throws IOException
   * @throws DashboardException
   */
  @RequestMapping(value = URIConstants.ASSIGN_ROLE, method = RequestMethod.GET)
  @ResponseBody
  public void assignRole(
      @PathVariable("userId") String userId,
      @PathVariable("lanId") String lanId,
      @PathVariable("firstName") String firstName,
      @PathVariable("role") String role,
      @PathVariable("lastName") String lastName,
      HttpServletResponse response)  throws IOException, DashboardException, Exception {
    //logger.trace("Entering method :--> assignRole()");
    try {
      int user_Id = Integer.parseInt(userId);
      userRoleDTO = new UserRoleDTO();
      userRoleDTO.setFirstName(firstName);
      userRoleDTO.setUserId(user_Id);
      userRoleDTO.setLanId(lanId);
      userRoleDTO.setRole(role);
      userRoleDTO.setLastName(lastName);
      userRoleService.assignRole(userRoleDTO);
      //logger.trace("Assigning role to [" + userId + "] user.");
    } catch (DashboardException de) {
      
      throw de;
    } catch (Exception e) {
      
      throw e;
    } finally {
      //logger.trace("Exiting method :--> assignRole()");
    } 
  }

	/**
	 * Method used to find the user.
	 * @param String userId
	 * @param HttpServletResponse response
	 * @return String
	 * @throws IOException
	 
	 */
	@RequestMapping(value = URIConstants.FIND_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String findUser(@PathVariable("userId") String userId,
			HttpServletResponse response) throws IOException,
			DashboardException, Exception {
	    //logger.trace("Entering method :--> findUser()");
	    AddReceipt addRec = null;
	    Gson gsn = new Gson();
	    try {
	      String roleId = userRoleService.findUser(userId);
	      addRec = new AddReceipt();
	      addRec.setInsertFlag(roleId);
	    } catch (DashboardException de) {
	      
	      throw de;
	    } catch (Exception e) {
	      
	      throw e;
	    } finally {
	      //logger.trace("Exiting method :--> findUser()");
	    } 
	    return gsn.toJson(addRec);
	  }

	/**
	 * Method used to update the role of the user.
	 * @param String userId
	 * @param String role
	 * @param HttpServletResponse response
	 * @throws IOException
	 */
	@RequestMapping(value = URIConstants.UPDATE_ROLE, method = RequestMethod.GET)
	@ResponseBody
	public void upDateRole(@PathVariable("userId") String userId,
			@PathVariable("role") String role, HttpServletResponse response)
			throws IOException, DashboardException, Exception {
	    //logger.trace("Entering method :--> upDateRole()");
		try {
			int user_Id = Integer.parseInt(userId);
			userRoleDTO = new UserRoleDTO();
			userRoleDTO.setUserId(user_Id);
			userRoleDTO.setRole(role);
			userRoleService.updateRole(userRoleDTO);
		} catch (DashboardException de) {
			
			 throw de;
		} catch (Exception e) {
			
			 throw e;
		} finally {
			// logger.trace("Exiting method :--> upDateRole()");
		}
	}

	/**
	 * Method used to remove user role.
	 * @param String userId
	 * @param HttpServletResponse response
	 * @throws IOException
	 */
	@RequestMapping(value = URIConstants.REMOVE_ROLE, method = RequestMethod.GET)
	@ResponseBody
	public void removeUserRole(@PathVariable("prevUserId") String userId,
			HttpServletResponse response) throws IOException,
			DashboardException, Exception { 
	    //logger.trace("Entering method :--> removeUserRole()");
		try {
			int user_Id = Integer.parseInt(userId);
			userRoleDTO = new UserRoleDTO();
			userRoleDTO.setUserId(user_Id);
			userRoleService.removeUserRole(userRoleDTO);
		} catch (DashboardException de) {
			
			throw de;
		} catch (Exception e) {
			
			throw e;
		} finally {
			// logger.trace("Exiting method :--> removeUserRole()");
		}
	}

	/**
	 * Method used to assign role mappings.
	 * @param String role
	 * @param String roleMappings
	 * @param HttpServletResponse response
	 * @throws IOException
	 */
    @RequestMapping(value = URIConstants.ASSIGN_ROLE_MAPPING, method = RequestMethod.GET)
    @ResponseBody
	public void assignRoleMapping(@PathVariable("role") String role,
			@PathVariable("roleMappings") String roleMappings,
			HttpServletResponse response) throws IOException,
			DashboardException, Exception {
	    //logger.trace("Entering method :--> assignRoleMapping()");
		try {
			List<String> roleMapList = new ArrayList<String>(
					Arrays.asList(roleMappings.split(",")));
			userRoleService.updateRoleMappings(role, roleMapList);
		} catch (DashboardException de) {
			
			 throw de;
		} catch (Exception e) {
			
			 throw e;
		} finally {
			// logger.trace("Exiting method :--> assignRoleMapping()");
		}
    }

	/**
	 * Method used to get role mapping.
	 * @param String role
	 * @param HttpServletResponse response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = URIConstants.GET_ASSIGNED_ROLE_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAssignedRoleMapping(@PathVariable("role") String role,
			HttpServletResponse response) throws IOException,
			DashboardException, Exception {
		List<String> mappingList = null;
		Gson gsn = new Gson();
		try {
			mappingList = userRoleService.getAssignedRoleMapping(role);
		} catch (DashboardException de) {
			
			throw de;
		} catch (Exception e) {
			
			throw e;
		} finally {
			// logger.trace("Exiting method :--> getAssignedRoleMapping()");
		}
		return gsn.toJson(mappingList);
	}

	/**
	 * Method used to find the user.
	 * @param String userId
	 * @param HttpServletResponse response
	 * @return String
	 * @throws IOException
	 
	 */
	@RequestMapping(value = URIConstants.FIND_SEGMENTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String findSegments() throws IOException,
			DashboardException, Exception {
	    //logger.trace("Entering method :--> findUser()");
	    List<String> segList=null;
	    Gson gson = new Gson();
	    try {
	      segList = userRoleService.findSegments();
	      
	      
	    } catch (DashboardException de) {
	      
	      throw de;
	    } catch (Exception e) {
	      
	      throw e;
	    } finally {
	      //logger.trace("Exiting method :--> findUser()");
	    } 
	    return gson.toJson(segList);
	  }

@RequestMapping(value = URIConstants.FIND_SEGMENTSDESC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public String findSegmentDescBySegName(@PathVariable("segName") String segName) throws IOException,
		DashboardException, Exception {
    //logger.trace("Entering method :--> findUser()");
    List<String> segDescList=null;
    Gson gson = new Gson();
    try {
      segDescList = userRoleService.findSegmentDescs(segName);
      
      
    } catch (DashboardException de) {
      
      throw de;
    } catch (Exception e) {
      
      throw e;
    } finally {
      //logger.trace("Exiting method :--> findUser()");
    } 
    return gson.toJson(segDescList);
  }
}
