package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.LayoutVo;
import com.staples.dashboard.app.vo.NephosConfig;
import com.staples.dashboard.dto.SavingsDetailDTO;

public class NephosConfigMapper implements RowMapper<NephosConfig> , StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		NephosConfig config = null;
		List<Object> objects = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	if (objects == null) {
		    		objects = new ArrayList<Object>();
			        }
		      for (Map<String, Object> rs : resultMap) {
		    	  config = new NephosConfig();
		    	  config
					.setServiceName(rs.get("SERVICE_NAME") == null ? MapperConstants.BLANK_STRING
							: rs.get("SERVICE_NAME").toString());
		    	  config
					.setResourceUrl(rs.get("RESOURCE_URL") == null ? MapperConstants.BLANK_STRING
							: rs.get("RESOURCE_URL").toString());
		    	  config
					.setAuthUrl(rs.get("AUTH_URL") == null ? MapperConstants.BLANK_STRING
							: rs.get("AUTH_URL").toString());
		    	  config
					.setUserName(rs.get("CLIENT_ID") == null ? MapperConstants.BLANK_STRING
							: rs.get("CLIENT_ID").toString());
		    	  config
					.setPassword(rs.get("CLIENT_SECRET") == null ? MapperConstants.BLANK_STRING
							: rs.get("CLIENT_SECRET").toString());
		    	  config
					.setAccessToken(rs.get("ACCESS_TOKEN") == null ? MapperConstants.BLANK_STRING
							: rs.get("ACCESS_TOKEN").toString());
		        
		        objects.add(config);
		        
		      }}
		return objects;
	}

	@Override
	public NephosConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
