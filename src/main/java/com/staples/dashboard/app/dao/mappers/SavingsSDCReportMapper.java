package com.staples.dashboard.app.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.dao.mappers.StaplesDashBoardRowMapper;
import com.staples.dashboard.app.vo.SavingsVo;
import com.staples.dashboard.dto.SavingsDetailDTO;

public class SavingsSDCReportMapper implements RowMapper<SavingsDetailDTO> , StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		SavingsDetailDTO savingsVo = null;
		List<Object> objects = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	if (objects == null) {
		    		objects = new ArrayList<Object>();
			        }
		      for (Map<String, Object> rs : resultMap) {
		        savingsVo = new SavingsDetailDTO();
		        savingsVo
		   				.setCustNumber(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_CUSTOMER_NUMBER).toString());
		   		        savingsVo.setSkuNum(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PRODUCT_SKU_NUM) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PRODUCT_SKU_NUM).toString());
		   		        savingsVo.setOrderQuantity(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_QUANTITY) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_QUANTITY).toString());
		   		        savingsVo.setSkuName(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PRODUCT_NAME) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PRODUCT_NAME).toString());
		   		        savingsVo.setChannel(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_CHANNEL) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_CHANNEL).toString());
		   		        savingsVo.setAmount(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_TOTAL_AMOUNT) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_TOTAL_AMOUNT).toString());
		   		        savingsVo.setProgramPrice(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROGRAM_PRICE) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROGRAM_PRICE).toString());
		   		        savingsVo.setProjectOrderPrice(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROJECTED_ORDER_PRICE) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROJECTED_ORDER_PRICE).toString());
		   		         savingsVo.setProgramSavings(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROGRAM_SAVINGS) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_PROGRAM_SAVINGS).toString());
		   		        savingsVo.setOrderNumber(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_ORDER_NUMBER).toString());
		   		        savingsVo.setOrderNumber(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
		   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_ORDER_DATE).toString());
		   		        savingsVo.setUnitPrice(rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_UNIT_PRICE) == null ? MapperConstants.BLANK_STRING
	   						: rs.get(MapperConstants.SAVINGS_REPORT_MAPPER_UNIT_PRICE).toString());
		   		        objects.add(savingsVo);
		   		        
		   		       
		      }}
		return objects;
	}

	@Override
	public SavingsDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

