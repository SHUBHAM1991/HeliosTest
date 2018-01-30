package com.staples.dashboard.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.staples.dashboard.app.constants.URIConstants;
import com.staples.dashboard.app.service.LDAPManager;
import com.staples.dashboard.app.utilities.JsonUtil;

/**
 * The Class UserValidateRestController.
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
public class UserValidateRestController {
  
  @Autowired
  private LDAPManager manger;
  private List<Map<String, Object>> userDetails;
  private @Value("${hostname}") String hostname;
  private @Value("${port}") String port;
  private @Value("${dns}") String dns;
  private @Value("${password}") String password;
  
  /**
   * Method used to validate the user.
   * @param String userId
   * @param HttpServletResponse response
   * @return String
   * @throws Exception
   */
	@RequestMapping(value = URIConstants.VALIDATE_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String validate(@PathVariable("userId") String userId,
			HttpServletResponse response) throws Exception {

		manger = new LDAPManager(hostname, Integer.parseInt(port), dns,
				password);
		userDetails = manger.searhUser(userId);
		if (userDetails.get(0).size() > 0) {
			return JsonUtil.parseJSON(userDetails);
		} else {
			return JsonUtil.parseJSON("");
		}
	}

}

