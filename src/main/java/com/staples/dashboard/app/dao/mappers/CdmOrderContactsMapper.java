  package com.staples.dashboard.app.dao.mappers;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;

	import com.staples.dashboard.app.constants.MapperConstants;
    import com.staples.dashboard.app.vo.OrderContactInfoVO;
    import org.springframework.jdbc.core.RowMapper;

	
	public class CdmOrderContactsMapper implements RowMapper<OrderContactInfoVO> , StaplesDashBoardRowMapper {
		
		/**
		 * Method implementation to get row map.
		 * @param ResultSet rs
		 * @param int rowNum
		 * @return 
		 * @return AbabdonedCartVO
		 * @throws SQLException
		 */
		
		public OrderContactInfoVO mapRow (ResultSet rs, int rowNum) throws SQLException {
			OrderContactInfoVO vo = new OrderContactInfoVO();
			vo.setRecentOrderDate(rs.getString(MapperConstants.SUPERUSERINFO_RECENT_ORDER_DATE));
			vo.setOrderContact(rs.getString(MapperConstants.SUPERUSERINFO_ORDER_CONTACT));
			vo.setContactEmail(rs.getString(MapperConstants.SUPERUSERINFO_EMAIL));
			vo.setRecentVisitDate(rs.getString(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE));
			vo.setContactPhone(rs.getString(MapperConstants.SUPERUSERINFO_PHONE_NUMBER));
			vo.setIamId(rs.getString(MapperConstants.SUPERUSERINFO_IAM_ID));
			vo.setTotalSalesCurr(rs.getString(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR));
			vo.setContactId(rs.getString(MapperConstants.SUPERUSERINFO_CONTACT_ID));
			return vo;
		}

		/**
		 * Method implementation to get row map.
		 * @param List<Map<String,Object>> resultMap
		 * @return List<Object>
		 */
		@Override
		public List<Object> mapRow(List<Map<String, Object>> resultMap) {
			
			List<Object> objects = null;
			OrderContactInfoVO  vo  = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		      for (Map<String, Object> rs : resultMap) {
		        if (objects == null) {
		          objects = new ArrayList<Object>();
		        }
		        vo = new OrderContactInfoVO();
		        vo.setIamId(rs.get(MapperConstants.SUPERUSERINFO_IAM_ID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_IAM_ID).toString());
				vo.setOrderContact(rs.get(MapperConstants.SUPERUSERINFO_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_ORDER_CONTACT).toString());
				vo.setRecentOrderDate(rs.get(MapperConstants.SUPERUSERINFO_RECENT_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_RECENT_ORDER_DATE).toString());
				vo.setRecentVisitDate(rs.get(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_RECENT_VISIT_DATE).toString().substring(0,10));
				vo.setTotalSalesCurr(rs.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_TOTAL_SALES_CURR).toString());
				vo.setContactEmail(rs.get(MapperConstants.SUPERUSERINFO_EMAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_EMAIL).toString());
				vo.setContactPhone(rs.get(MapperConstants.SUPERUSERINFO_PHONE_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_PHONE_NUMBER).toString());
				vo.setContactId(rs.get(MapperConstants.SUPERUSERINFO_CONTACT_ID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUPERUSERINFO_CONTACT_ID).toString());
				
				objects.add(vo);
		        
		      }
		    }
		    return objects;
		}

	}
