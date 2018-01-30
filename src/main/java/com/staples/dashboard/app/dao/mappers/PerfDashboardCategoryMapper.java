package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;


public class PerfDashboardCategoryMapper implements RowMapper<PerfDashboardCategoryVO> , StaplesDashBoardRowMapper {

	//@Override
	public PerfDashboardCategoryVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		PerfDashboardCategoryVO perfDashboardCategoryVO = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				if (objects == null) {
					objects = new ArrayList<Object>();
				}
				perfDashboardCategoryVO = new PerfDashboardCategoryVO();
				perfDashboardCategoryVO.setCustNumber(rs.get(MapperConstants.CUSTOMER_NUMBER_WIR) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUSTOMER_NUMBER_WIR).toString());
				perfDashboardCategoryVO.setCategoryName(rs.get(MapperConstants.PRIMARY_CAT_DESC) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PRIMARY_CAT_DESC).toString());
				perfDashboardCategoryVO.setCurrRollMonthSales(rs.get(MapperConstants.CURR_ROLL_3_MONTH_SLS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CURR_ROLL_3_MONTH_SLS).toString());
				perfDashboardCategoryVO.setPriorRollMonthSales(rs.get(MapperConstants.PRIOR_ROLL_3_MONTH_SLS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PRIOR_ROLL_3_MONTH_SLS).toString());
				perfDashboardCategoryVO.setCurrYrToDateSales(rs.get(MapperConstants.CURR_YEAR_TO_DATE_SLS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CURR_YEAR_TO_DATE_SLS).toString());
				perfDashboardCategoryVO.setPriorYrToDateSales(rs.get(MapperConstants.PRIOR_YEAR_TO_DATE_SLS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PRIOR_YEAR_TO_DATE_SLS).toString());
				perfDashboardCategoryVO.setRollMonthPenAmount(rs.get(MapperConstants.ROLL_3_MONTH_PEN_AMT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ROLL_3_MONTH_PEN_AMT).toString());
				perfDashboardCategoryVO.setRollMonthPenPercent(rs.get(MapperConstants.ROLL_3_MONTH_PEN_PCT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ROLL_3_MONTH_PEN_PCT).toString());
				perfDashboardCategoryVO.setYrToDateSalesPercent(rs.get(MapperConstants.YEAR_TO_DATE_SLS_PCT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.YEAR_TO_DATE_SLS_PCT).toString());
				perfDashboardCategoryVO.setRefreshDt(rs.get(MapperConstants.REFRESH_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REFRESH_DATE).toString());
				perfDashboardCategoryVO.setDeclinePercent(rs.get(MapperConstants.DECLINE_PCT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.DECLINE_PCT).toString());
				perfDashboardCategoryVO.setPrimProdCategCode(rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PRIMARY_PRODUCT_CAT_CD).toString());
				perfDashboardCategoryVO.setMesgDesc(rs.get(MapperConstants.MESSAGE_DESC) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.MESSAGE_DESC).toString());
				perfDashboardCategoryVO.setWirEnabledFlag(rs.get(MapperConstants.WIR_ENABLED_FLAG) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.WIR_ENABLED_FLAG).toString());
				objects.add(perfDashboardCategoryVO);
			}
		}
		return objects;
	}

}