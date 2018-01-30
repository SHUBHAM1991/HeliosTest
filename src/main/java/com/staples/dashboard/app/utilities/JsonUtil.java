/**
 * 
 */
package com.staples.dashboard.app.utilities;

import com.google.gson.Gson;

/**
 * The Class JsonUtil.
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
public class JsonUtil {

	/**
	 * Parse object to json.
	 * 
	 * @param Object obj
	 * @return the json
	 */
	public static String parseJSON(Object obj) {
		final Gson gson = new Gson();
		final String json = gson.toJson(obj);
		return json;
	}

}
