package com.staples.dashboard.app.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * The Interface StaplesDashBoardRowMapper.
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
public interface StaplesDashBoardRowMapper {
	
	/**
	 * Method declaration to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	public List<Object> mapRow(List<Map<String,Object>> resultMap);

}
