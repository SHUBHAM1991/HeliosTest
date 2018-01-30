package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.utilities.StringUtils;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;
import com.staples.dashboard.app.vo.SavingsVo;

/**
 * The Class BoughtAlsoBoughtVOMapper.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class SavingsMapper implements RowMapper<SavingsVo> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		SavingsVo savingsVo = null;
	List<Object> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<Object>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	        savingsVo = new SavingsVo();
				savingsVo
						.setCurrEstAnnualSpend(rs.get("CURR_EST_ANNUAL_SPEND") == null ? MapperConstants.BLANK_STRING
								: rs.get("CURR_EST_ANNUAL_SPEND").toString());
				savingsVo
						.setProposedAnnualSpend(rs.get("PROPOSED_ANNUAL_SPEND") == null ? MapperConstants.BLANK_STRING
								: rs.get("PROPOSED_ANNUAL_SPEND").toString());
				savingsVo
						.setProjectedPriceSaving(rs.get("PROJECTED_PRICE_SAVING") == null ? MapperConstants.BLANK_STRING
								: rs.get("PROJECTED_PRICE_SAVING").toString());
				savingsVo
						.setProjectedRebateSaving(rs.get("PROJECTED_REBATE_SAVING") == null ? MapperConstants.BLANK_STRING
								: rs.get("PROJECTED_REBATE_SAVING").toString());
				savingsVo
						.setTotalSaving(rs.get("TOTAL_SAVING") == null ? MapperConstants.BLANK_STRING
								: rs.get("TOTAL_SAVING").toString());
	        
	        objects.add(savingsVo);
	      }}
		
		return objects;
	}

	@Override
	public SavingsVo mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}

	

}
