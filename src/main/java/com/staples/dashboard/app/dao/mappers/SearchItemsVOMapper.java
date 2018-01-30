package com.staples.dashboard.app.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SearchVO;;

/**
 * The Class SearchItemsVOMapper.
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
public class SearchItemsVOMapper implements RowMapper<SearchVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public SearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SearchVO searchItemsVO = new SearchVO();
		searchItemsVO.setOrderContact(rs.getString(MapperConstants.SEARCHITEMS_ORDER_CONTACT));
		searchItemsVO.setSearchItems(rs.getString(MapperConstants.SEARCHITEMS_SEARCH_STRING));
		return searchItemsVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		SearchVO searchItemsVO   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        searchItemsVO = new SearchVO();
	        searchItemsVO.setOrderContact(rs.get(MapperConstants.SEARCHITEMS_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SEARCHITEMS_ORDER_CONTACT).toString());
			searchItemsVO.setSearchItems(rs.get(MapperConstants.SEARCHITEMS_SEARCH_STRING) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SEARCHITEMS_SEARCH_STRING).toString());
			objects.add(searchItemsVO);
	      }
	    }
	    return objects;
	}

}
