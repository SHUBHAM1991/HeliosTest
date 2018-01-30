package com.staples.dashboard.app.dao.mappers;

import java.util.List;
import java.util.Map;

public interface CTABaseMapper {
	public List<Object> mapRow(List<Map<String,Object>> resultMap);
}
