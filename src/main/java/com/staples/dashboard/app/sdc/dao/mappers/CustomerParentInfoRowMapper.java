package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CustomerParentInfo;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;

public class CustomerParentInfoRowMapper implements RowMapper<CustomerParentInfo>{
	
	
	public List<CustomerParentInfo> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<CustomerParentInfo> ordersList = new ArrayList<>();
		Map<String,CustomerParentInfo> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String orderNo = getColumnString(rs, MapperConstants.SOURCENUMBER);
				CustomerParentInfo customerParentInfo = orders.get(orderNo);
				
				if(customerParentInfo==null){
					customerParentInfo=new CustomerParentInfo();
					customerParentInfo.setOrderNo(orderNo);
					customerParentInfo.setParentId(getColumnString(rs, "PARENT_NUMBER"));
					orders.put(orderNo, customerParentInfo);
				}
				List<String> childs = customerParentInfo.getChilds();
				if(childs==null){
					childs=new ArrayList<>();
					customerParentInfo.setChilds(childs);
				}
				childs.add(getColumnString(rs, "CHILD_NUMBER"));
			}
			ordersList.addAll(orders.values());
		}
		return ordersList;
	}
	
	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}

	@Override
	public CustomerParentInfo mapRow(ResultSet arg0, int arg1) throws SQLException {
		
		
		return null;
	}

}
