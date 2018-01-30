package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.utilities.StringUtils;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.CategoryPenetrationVo;

/**
 * The Class BoughtAlsoBoughtVOMapper.
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
public class CategoryPenetrationMapper implements RowMapper<CategoryPenetrationVo> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		CategoryPenetrationVo catpenVo = null;
	List<Object> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<Object>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	        catpenVo = new CategoryPenetrationVo();
	        catpenVo
			.setCustomerNumber(rs.get("CUSTOMER_NUM") == null ? MapperConstants.BLANK_STRING
					: rs.get("CUSTOMER_NUM").toString());
	catpenVo
			.setCategory(rs.get("CAT") == null ? MapperConstants.BLANK_STRING
					: rs.get("CAT").toString());
	catpenVo
			.setCategoryPen(rs.get("CAT_PEN") == null ? MapperConstants.BLANK_STRING
					: rs.get("CAT_PEN").toString());
	catpenVo
			.setCategorySavingPct(rs.get("CAT_SAVING_PCT") == null ? MapperConstants.BLANK_STRING
					: rs.get("CAT_SAVING_PCT").toString());
	catpenVo
			.setPctValue(rs.get("PCT_VALUE") == null ? MapperConstants.BLANK_STRING
					: rs.get("PCT_VALUE").toString());
	        
	        objects.add(catpenVo);
	      }}
		
		return objects;
	}

	@Override
	public CategoryPenetrationVo mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}

	

}
