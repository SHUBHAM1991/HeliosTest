package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;

public class CategoryPercentageMapper implements RowMapper<CategoryPercentageResponse> {

	public CategoryPercentageResponse mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		Map<String, CategoryDivisionVO> categoryPercentage =new HashMap<>();
		CategoryPercentageResponse response = new CategoryPercentageResponse();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String percent = getColumnString(rs, "CATEGORY_PERCENTAGE");
				String sourcesystemId = getColumnString(rs, "SOURCESYSTEMID");
				String name = getColumnString(rs, "PRIMARY_PRODUCT_CAT_DSCR");
				String total = getColumnString(rs, "CATEGORY_TOTAL");
				
				
				if(name==null){
					if(sourcesystemId==null){
						response.setCompleteCategoryPercentage(percent);
						response.setCompleteTotalCategory(total);
					}
					if("102".equals(sourcesystemId)){
						response.setDotComPercentageTotal(total);
					}
					else if("107".equals(sourcesystemId)){
						response.setRetailPercentageTotal(total);
					}
					continue;
				}
				CategoryDivisionVO vo = categoryPercentage.get(name);
				if(null==vo){
					vo=new CategoryDivisionVO();
					categoryPercentage.put(name, vo);
				}
				vo.setName(name);
				if(sourcesystemId==null){
					vo.setCategoryTotal(total);
					vo.setCategoryPercentage(percent);
				}
				else if("102".equals(sourcesystemId)){
					vo.setDotComCategoryTotal(total);
					vo.setDotComCategoryPercentage(percent);
				}
				else if("107".equals(sourcesystemId)){
					vo.setRetailCategoryPercentage(percent);
					vo.setRetailCategoryTotal(total);
				}
			}
		}
		Collection<CategoryDivisionVO> values = categoryPercentage.values();
		ArrayList<CategoryDivisionVO> categories = new ArrayList<>(values);
		/*if(null !=values){
			for (CategoryDivisionVO categoryDivisionVO : values) {
				categories.add(categoryDivisionVO);
			}
		}*/
		
		response.setCategories(categories);
		return response;
	}

	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName) == null ? null : rs.get(columnName).toString();
	}

	@Override
	public CategoryPercentageResponse mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
