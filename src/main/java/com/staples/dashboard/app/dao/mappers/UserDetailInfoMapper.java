
package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SuperUserInfoVO;


public class UserDetailInfoMapper implements RowMapper<SuperUserInfoVO> , StaplesDashBoardRowMapper{

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		SuperUserInfoVO  vo  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        vo = new SuperUserInfoVO();
	       
	        vo.setNumOrders(rs
					.get(MapperConstants.SUPERUSERINFO_ORDERS) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.SUPERUSERINFO_ORDERS)
							.toString());
			vo.setNumOrdersCurr(rs
					.get(MapperConstants.SUPERUSERINFO_ORDERS_CURR) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_ORDERS_CURR)
							.toString());
			vo.setNumOrdersMon(rs
					.get(MapperConstants.SUPERUSERINFO_ORDERS_PERIOD) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_ORDERS_PERIOD)
							.toString());
			vo.setOrderContact(rs
					.get(MapperConstants.SUPERUSERINFO_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_ORDER_CONTACT)
							.toString());
			vo.setNumVisits(rs
					.get(MapperConstants.SUPERUSERINFO_VISIT_COUNT) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_VISIT_COUNT)
							.toString());
			vo.setVisitDate(rs
					.get(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE)
							.toString().substring(0, 10));
			vo.setTotalSales(rs
					.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_TOTAL_SALES)
							.toString());
			vo.setTotalSalesCurr(rs
					.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR)
							.toString());
			
			vo.setContactEmail(rs
					.get(MapperConstants.SUPERUSERINFO_EMAIL) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_EMAIL)
							.toString());
			vo.setContactPhone(rs
					.get(MapperConstants.SUPERUSERINFO_PHONE_NUMBER) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.SUPERUSERINFO_PHONE_NUMBER)
							.toString());
			
			vo.setIamId(rs.get("IAM_ID") == null ? MapperConstants.BLANK_STRING : rs.get("IAM_ID").toString());
			vo.setFirstName(rs.get("FIRST_NAME") == null ? MapperConstants.BLANK_STRING : rs.get("FIRST_NAME").toString());
			vo.setLastName(rs.get("LAST_NAME") == null ? MapperConstants.BLANK_STRING : rs.get("LAST_NAME").toString());
	        objects.add(vo);
	        
	      }
	    }
	    return objects;	
	}

	
	@Override
	public SuperUserInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SuperUserInfoVO vo = new SuperUserInfoVO();
		vo.setNumOrders(rs
				.getString(MapperConstants.SUPERUSERINFO_ORDERS) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.SUPERUSERINFO_ORDERS)
						);
		vo.setNumOrdersCurr(rs
				.getString(MapperConstants.SUPERUSERINFO_ORDERS_CURR) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_ORDERS_CURR)
						);
		vo.setNumOrdersMon(rs
				.getString(MapperConstants.SUPERUSERINFO_ORDERS_PERIOD) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_ORDERS_PERIOD)
					);
		vo.setOrderContact(rs
				.getString(MapperConstants.SUPERUSERINFO_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_ORDER_CONTACT)
				);
		vo.setNumVisits(rs
				.getString(MapperConstants.SUPERUSERINFO_VISIT_COUNT) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_VISIT_COUNT)
						);
		vo.setVisitDate(rs
				.getString(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE)
						.substring(0, 10));
		vo.setTotalSales(rs
				.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_TOTAL_SALES)
					);
		vo.setTotalSalesCurr(rs
				.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR)
						);
		vo.setContactEmail(rs
				.getString(MapperConstants.SUPERUSERINFO_EMAIL) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_EMAIL)
			);
		vo.setContactPhone(rs
				.getString(MapperConstants.SUPERUSERINFO_PHONE_NUMBER) == null ? MapperConstants.BLANK_STRING
				: rs.getString(
						MapperConstants.SUPERUSERINFO_PHONE_NUMBER)
						);
		vo.setIamId(rs.getString("IAM_ID") == null ? MapperConstants.BLANK_STRING : rs.getString("IAM_ID"));
		vo.setFirstName(rs.getString("FIRST_NAME") == null ? MapperConstants.BLANK_STRING : rs.getString("FIRST_NAME"));
		vo.setLastName(rs.getString("LAST_NAME") == null ? MapperConstants.BLANK_STRING : rs.getString("LAST_NAME"));
		return vo;
	}

}
