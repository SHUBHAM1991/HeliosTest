package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SuperUserInfoVO;

import org.springframework.jdbc.core.RowMapper;

/**
 * The Class SuperUserInfoVOMapper.
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
public class SuperUserInfoVOMapper implements RowMapper<SuperUserInfoVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public SuperUserInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SuperUserInfoVO superUserInfoVO = new SuperUserInfoVO();
		superUserInfoVO.setNumOrders(rs.getString(MapperConstants.SUPERUSERINFO_ORDERS));
		superUserInfoVO.setOrderContact(rs.getString(MapperConstants.SUPERUSERINFO_ORDER_CONTACT));
		superUserInfoVO.setNumVisits(rs.getString(MapperConstants.SUPERUSERINFO_VISIT_COUNT));
		superUserInfoVO.setNumOrdersCurr(rs.getString(MapperConstants.SUPERUSERINFO_ORDERS_CURR));
		superUserInfoVO.setVisitDate(rs.getString(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE));
		superUserInfoVO.setTotalSales(rs.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES));
		superUserInfoVO.setTotalSalesCurr(rs.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR));
		return superUserInfoVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		
		List<Object> objects = null;
		SuperUserInfoVO superUserInfoVO  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        superUserInfoVO = new SuperUserInfoVO();
	        superUserInfoVO.setNumOrders(rs.get(MapperConstants.SUPERUSERINFO_ORDERS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_ORDERS).toString());
	        superUserInfoVO.setNumOrdersCurr(rs.get(MapperConstants.SUPERUSERINFO_ORDERS_CURR) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_ORDERS_CURR).toString());
			superUserInfoVO.setOrderContact(rs.get(MapperConstants.SUPERUSERINFO_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_ORDER_CONTACT).toString());
			superUserInfoVO.setNumVisits(rs.get(MapperConstants.SUPERUSERINFO_VISIT_COUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_VISIT_COUNT).toString());
			superUserInfoVO.setVisitDate(rs.get(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE).toString().substring(0,10));
			superUserInfoVO.setTotalSales(rs.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES).toString());
			superUserInfoVO.setTotalSalesCurr(rs.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_ORDERS_CURR).toString());
			superUserInfoVO.setContactEmail((String)rs.get(MapperConstants.SUPERUSERINFO_EMAIL));
			superUserInfoVO.setContactPhone((String)rs.get(MapperConstants.SUPERUSERINFO_PHONE_NUMBER));
			objects.add(superUserInfoVO);
	        
	      }
	    }
	    return objects;
	}
}
