package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.CustomerUserVO;
import com.staples.dashboard.app.vo.ReorderRecommendationVO;

/**
 * The Class CustomerUserMapper.
 *
 * @author RajIl001
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
 *  <td>1.0</td><td>Jul 31, 2017</td><td>RajIl001</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class CustomerUserMapper implements RowMapper<CustomerUserVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public CustomerUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerUserVO custUserVO = new CustomerUserVO();
		custUserVO.setCustNum(rs.getString(MapperConstants.SPARKX_REORDERRECOMM_CUST_NUM));
		custUserVO.setCustomerUserEmailId(rs.getString(MapperConstants.CUST_EMAILID));
		custUserVO.setCustomerUserEmailId(rs.getString(MapperConstants.CUST_NAME));
		return custUserVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		CustomerUserVO custUserVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        custUserVO = new CustomerUserVO();
	        custUserVO.setCustNum(rs.get(MapperConstants.SPARKX_REORDERRECOMM_CUST_NUM) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SPARKX_REORDERRECOMM_CUST_NUM).toString());
	        custUserVO.setCustomerUserEmailId(rs.get(MapperConstants.CUST_EMAILID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_EMAILID).toString());
	        custUserVO.setCutomerUserName(rs.get(MapperConstants.CUST_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_NAME).toString());
	        custUserVO.setRepId(rs.get(MapperConstants.REP_ID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REP_ID).toString());
	        //System.out.println(rs.get(MapperConstants.SPARKX_REORDERRECOMM_CUST_NUM) );
			objects.add(custUserVO);
	      }
	    }
	    return objects;
	}
}
