package com.staples.dashboard.app.dao.mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class SubjectLabelMapper implements CTABaseMapper{

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	       
			objects.add(rs.get("SUBJECT_NAME").toString());
			
	      }
	    }
	    return objects;
	}

}
