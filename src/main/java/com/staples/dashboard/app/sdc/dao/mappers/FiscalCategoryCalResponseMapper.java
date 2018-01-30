package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;
import com.staples.dashboard.app.sdc.vo.CategoryTypeCalresponse;
import com.staples.dashboard.app.sdc.vo.CustomerParentInfo;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;

public class FiscalCategoryCalResponseMapper implements RowMapper<FiscalCategoryCalResponse>{
	
	
		private static final String _107 = "107";
		private static final String _102 = "102";




		public FiscalCategoryCalResponse mapRow(List<Map<String, Object>> resultMap) {
			// TODO Auto-generated method stub
			FiscalCategoryCalResponse response=new FiscalCategoryCalResponse();
			Map<String,CategoryTypeCalresponse> orders=new TreeMap();
			Map<String,String> verticalCategoryDotComVsTotal=new TreeMap();
			Map<String,String> verticalCategoryRetailVsTotal=new TreeMap();
			Map<String,String> verticalCategoryVsTotal=new TreeMap();
			
			
			if (resultMap != null && !resultMap.isEmpty()) {
				for (Map<String, Object> rs : resultMap) {
					String month = getColumnString(rs, "FSC_PRD".toUpperCase());
					String total = getColumnString(rs, "TOTAL");
					String name = getColumnString(rs, "PRIMARY_PRODUCT_CAT_DSCR");
					String sourceSystemId = getColumnString(rs, "SOURCESYSTEMID");
					
					if(month==null && name==null && sourceSystemId==null){
						response.setTotalSum(total);
						continue;
					}
					if(month==null){
						if(sourceSystemId==null){
							verticalCategoryVsTotal.put(name, total);
							continue;
						}
						if(_102.equals(sourceSystemId)){
							verticalCategoryDotComVsTotal.put(_102, total);
							continue;
						}
						if(_107.equals(sourceSystemId)){
							verticalCategoryRetailVsTotal.put(_107, total);
							continue;
						}
					}
					
					CategoryTypeCalresponse customerParentInfo = orders.get(month);
					if(customerParentInfo==null){
						customerParentInfo=new CategoryTypeCalresponse();
						Map<String,String> horizantalCompleteCategoryVsTotal=new TreeMap();
						customerParentInfo.setCompleteCategoryTotal(horizantalCompleteCategoryVsTotal);
						orders.put(month, customerParentInfo);
					}
					if(name==null){
						if(sourceSystemId==null){
							customerParentInfo.setFiscalMonthTotal(total);
							continue;
						}
						if(_102.equals(sourceSystemId)){
							customerParentInfo.setDotComMonthTotal(total);
							continue;
						}
						if(_107.equals(sourceSystemId)){
							customerParentInfo.setRetailMonthTotal(total);
							continue;
						}
					}
					if(sourceSystemId==null){
						Map<String, String> completeCategoryTotal = customerParentInfo.getCompleteCategoryTotal();
						completeCategoryTotal.put(name, total);
						
					    continue;
					}
					CategoryDivisionVO categoryDivisionVO=new CategoryDivisionVO();
				
					categoryDivisionVO.setCategoryTotal(total);
					categoryDivisionVO.setName(name);
					if(_102.equals(sourceSystemId)){
						List<CategoryDivisionVO> categories = customerParentInfo.getCategories();
						if(categories==null){
							categories=new ArrayList<>();
							customerParentInfo.setCategories(categories);
						}
						categories.add(categoryDivisionVO);
					}
					else if(_107.equals(sourceSystemId)){
						List<CategoryDivisionVO> retailCategories = customerParentInfo.getRetailCategories();
						if(retailCategories==null){
							retailCategories=new ArrayList<>();
							customerParentInfo.setRetailCategories(retailCategories);
						}
						retailCategories.add(categoryDivisionVO);
					}
					
				}
			}
			response.setFiscalMonthCategories(orders);
			
			response.setVerticalCategoryTotal(verticalCategoryVsTotal);
			
			return response;
		}
		
		private String getColumnString(Map<String, Object> rs, String columnName) {
			// TODO Auto-generated method stub
			return rs.get(columnName)==null?null:rs.get(columnName).toString();
		}

		


	@Override
	public FiscalCategoryCalResponse mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
