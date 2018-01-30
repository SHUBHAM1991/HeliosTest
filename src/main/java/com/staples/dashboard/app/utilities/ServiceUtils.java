package com.staples.dashboard.app.utilities;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * The Class ServiceUtils.
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
@Service
public class ServiceUtils {
	
	@Resource
	private Map<String, String> appSettings;
	
	public static String appName="/none";
	
	public static String csrAppName = "/none";
	
	/**
	 * @param key
	 * @return
	 */
	public String getValue(String key)
	{
		return appSettings.get(key);
	}
	
	/**
	 * @return the appName
	 */
	public String getAppName()
	{
		return appName;
	}
	
	/**
	 * 
	 * @param contextPath
	 */
	public void setAppName(String contextPath) {
		appName = contextPath;
	}
}
