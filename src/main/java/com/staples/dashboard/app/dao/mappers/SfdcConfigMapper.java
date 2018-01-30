package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.ConfigSfdcVO;
import com.staples.dashboard.app.vo.NephosConfig;

public class SfdcConfigMapper implements RowMapper<ConfigSfdcVO> , StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		ConfigSfdcVO config = null;
		List<Object> objects = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	if (objects == null) {
		    		objects = new ArrayList<Object>();
			        }
		      for (Map<String, Object> rs : resultMap) {
		    	  config = new ConfigSfdcVO();
		    	  config
					.setServiceName(rs.get("SERVICE_NAME") == null ? MapperConstants.BLANK_STRING
							: rs.get("SERVICE_NAME").toString());
		    	  config
					.setForceUrl(rs.get("FORCE_URL") == null ? MapperConstants.BLANK_STRING
							: rs.get("FORCE_URL").toString());
		    	  
		    	  config
					.setClientId(rs.get("CLIENT_ID") == null ? MapperConstants.BLANK_STRING
							: rs.get("CLIENT_ID").toString());
		    	  config
					.setClientSecret(rs.get("CLIENT_SECRET") == null ? MapperConstants.BLANK_STRING
							: rs.get("CLIENT_SECRET").toString());
		    	  config
					.setUserName(rs.get("USERNAME") == null ? MapperConstants.BLANK_STRING
							: rs.get("USERNAME").toString());
		    	  config
					.setPassword(rs.get("USERPASSWORD") == null ? MapperConstants.BLANK_STRING
							: rs.get("USERPASSWORD").toString());
		        
		        objects.add(config);
		        
		      }}
		return objects;
	}

	@Override
	public ConfigSfdcVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
