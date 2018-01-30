package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;

public class CategoryDivisionVORowMapper implements RowMapper<CategoryDivisionVO>{
	
	public List<CategoryDivisionVO> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<CategoryDivisionVO> categories = new ArrayList<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
			
				String productCat_CD = rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_CD)==null?null:rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_CD).toString();
				String name = rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_DSCR)==null?null:rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_DSCR).toString();
				
				String classId = rs.get(MapperConstants.CLASS_ID)==null?null:rs.get(MapperConstants.CLASS_ID).toString();
				//String productClassNumber = rs.get(MapperConstants.PRODUCTCLASSNUMBER)==null?null:rs.get(MapperConstants.PRODUCTCLASSNUMBER).toString();
				
				String division = rs.get(MapperConstants.DIVISION)==null?null:rs.get(MapperConstants.DIVISION).toString();
			
				if(name==null || name.equals("") ){
					name="Other Category";
				}
				CategoryDivisionVO cd = new CategoryDivisionVO();
				cd.setProductCat_CD(productCat_CD);
				cd.setName(name);
				cd.setDivision(division);
				//cd.setProductClassNo(productClassNumber);
				cd.setClassNo(classId);
				categories.add(cd);
			}
		}
		return categories;
	}


	@Override
	public CategoryDivisionVO mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
