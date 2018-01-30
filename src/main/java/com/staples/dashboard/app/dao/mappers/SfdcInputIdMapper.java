package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SfdcInputId;

public class SfdcInputIdMapper implements RowMapper<SfdcInputId> , StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		SfdcInputId input = null;
		List<Object> objects = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	if (objects == null) {
		    		objects = new ArrayList<Object>();
			        }
		      for (Map<String, Object> rs : resultMap) {
		    	  input = new SfdcInputId();
		    	  input
					.setCustomerNum(rs.get("CUST_NUM") == null ? MapperConstants.BLANK_STRING
							: rs.get("CUST_NUM").toString());
		    	  input
					.setCustId(rs.get("CUST_ID") == null ? MapperConstants.BLANK_STRING
							: rs.get("CUST_ID").toString());
		    	  
		    	  input
					.setAccountMgrId(rs.get("OWNER_ID") == null ? MapperConstants.BLANK_STRING
							: rs.get("OWNER_ID").toString());
		    	  input
					.setContactId(rs.get("CONTACT_ID") == null ? MapperConstants.BLANK_STRING
							: rs.get("CONTACT_ID").toString());
		        
		        objects.add(input);
		        
		      }}
		return objects;
	}

	@Override
	public SfdcInputId mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
