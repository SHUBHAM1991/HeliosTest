package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.ParentChildInfo;

public class ParentChildInfoRowMapper implements RowMapper<ParentChildInfo>{
	
	public List<ParentChildInfo> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<ParentChildInfo> parentChilds = new ArrayList<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
			
				String parent = rs.get(MapperConstants.PARENT_NUMBER)==null?null:rs.get(MapperConstants.PARENT_NUMBER).toString();
				String child = rs.get(MapperConstants.CHILE_NODES)==null?null:rs.get(MapperConstants.CHILE_NODES).toString();
				if(parentChilds.size()==0){
					ParentChildInfo pc = new ParentChildInfo();
					pc.setParentId(parent);
					parentChilds.add(pc);
				}
				ParentChildInfo parentChildInfo = parentChilds.get(0);
				List<String> childs = parentChildInfo.getChilds();
				if(childs==null){
					childs=new ArrayList<>();
					parentChildInfo.setChilds(childs);
				}
				if(false==parent.equalsIgnoreCase(child)){
				childs.add(child);
				}
				
			}
		}
		return parentChilds;
	}


	@Override
	public ParentChildInfo mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
