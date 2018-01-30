package com.staples.dashboard.app.dao.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SearchVO;
import com.staples.dashboard.app.vo.SegmentVO;

public class SegmentVOMapper implements CTABaseMapper{

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		SegmentVO segmentVO   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        segmentVO = new SegmentVO();
			segmentVO.setSeg(rs.get("SEG")==null?null:Long.valueOf(rs.get("SEG").toString()));
			segmentVO.setSegDesc(rs.get("SEG_DESC")==MapperConstants.BLANK_STRING?null:rs.get("SEG_DESC").toString());
			objects.add(segmentVO);
			
	      }
	    }
	    return objects;
	}

}
