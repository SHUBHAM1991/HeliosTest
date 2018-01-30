package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.dao.mappers.StaplesDashBoardRowMapper;
import com.staples.dashboard.app.vo.ShipToDetailsVo;
import com.staples.dashboard.app.vo.ShipToDetailsVo;

public class ShipToDetailsVOMapper implements StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public ShipToDetailsVo mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		ShipToDetailsVo shipTodetailsVo = new ShipToDetailsVo();
		
		shipTodetailsVo.setOrderAmount(rs.getString(MapperConstants.ORDER_AMOUNT));
		shipTodetailsVo.setOrderDate(rs.getString(MapperConstants.ORDER_DATE));
		shipTodetailsVo.setOrderNumber(rs.getString(MapperConstants.ORDER_NUMBER));
		shipTodetailsVo.setOrderContact(rs.getString(MapperConstants.ORDER_CONTACT));
		shipTodetailsVo.setSkuList(rs.getString(MapperConstants.SKU));
		shipTodetailsVo.setDescriptionList(rs.getString(MapperConstants.DESCRIPTION));
		return shipTodetailsVo;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		ShipToDetailsVo shipToDetailsVo  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        shipToDetailsVo = new ShipToDetailsVo();
	        shipToDetailsVo.setOrderAmount(rs.get(MapperConstants.ORDER_AMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ORDER_AMOUNT).toString());
	        shipToDetailsVo.setMinOrderAmount(rs.get(MapperConstants.MIN_ORDER_AMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.MIN_ORDER_AMOUNT).toString());
	        shipToDetailsVo.setOrderDate(rs.get(MapperConstants.ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ORDER_DATE).toString());
	        shipToDetailsVo.setOrderNumber(rs.get(MapperConstants.ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ORDER_NUMBER).toString());
	        shipToDetailsVo.setOrderContact(rs.get(MapperConstants.ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ORDER_CONTACT).toString());
	        shipToDetailsVo.setSkuList(rs.get(MapperConstants.SKU) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SKU).toString());
	        shipToDetailsVo.setDescriptionList(rs.get(MapperConstants.DESCRIPTION) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.DESCRIPTION).toString());
	        shipToDetailsVo.setSavingCategory(rs.get(MapperConstants.SAVING_CATEGORY) == null ? new ArrayList<String>() : Arrays.asList(rs.get(MapperConstants.SAVING_CATEGORY).toString().split("#")));
	        shipToDetailsVo.setSavingAmount(rs.get(MapperConstants.SAVING_AMOUNT) == null ? new ArrayList<String>(): Arrays.asList(rs.get(MapperConstants.SAVING_AMOUNT).toString().split("#")));
			objects.add(shipToDetailsVo);
	        
	      }
	    }
	    return objects;
	}
}
