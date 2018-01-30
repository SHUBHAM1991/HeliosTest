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
import com.staples.dashboard.app.vo.CategoryDataVo;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
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
public class HawkeyeDetailsVMapper implements RowMapper<SavingsVo> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		HawkeyeDetailsVo hawkeyeDetailsVo = null;
	List<Object> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<Object>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	    	  hawkeyeDetailsVo = new HawkeyeDetailsVo();
	    	  hawkeyeDetailsVo
						.setCustNum(rs.get("CUSTOMER_NUMBER") == null ? MapperConstants.BLANK_STRING
								: rs.get("CUSTOMER_NUMBER").toString());
	    	  hawkeyeDetailsVo
						.setTotalDollarVal(rs.get("TOTAL_DOLLAR_VALUE") == null ? MapperConstants.BLANK_STRING
								: rs.get("TOTAL_DOLLAR_VALUE").toString());
	    	  hawkeyeDetailsVo
						.setChurnSeg(rs.get("CHURN_SEGMENT") == null ? MapperConstants.BLANK_STRING
								: rs.get("CHURN_SEGMENT").toString());
	    	  hawkeyeDetailsVo
						.setAbandonedCat(rs.get("ABANDONED_CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("ABANDONED_CATEGORY").toString());
	    	  hawkeyeDetailsVo
						.setOffCadenceCat(rs.get("OFF_CADENCE_CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("OFF_CADENCE_CATEGORY").toString());
	    	  
	    	  hawkeyeDetailsVo
				.setHighChurnCat(rs.get("HIGH_CHURN_CATEGORY") == null ? MapperConstants.BLANK_STRING
						: rs.get("HIGH_CHURN_CATEGORY").toString());
			  hawkeyeDetailsVo
						.setLowCallCoverage(rs.get("LOW_CALL_COVERAGE") == null ? MapperConstants.BLANK_STRING
								: rs.get("LOW_CALL_COVERAGE").toString());
			  hawkeyeDetailsVo
						.setDecliningCat(rs.get("DECLINING_CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("DECLINING_CATEGORY").toString());
			  hawkeyeDetailsVo
						.setHighPropensityCat(rs.get("HIGH_PROPENSITY_CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("HIGH_PROPENSITY_CATEGORY").toString());
			  hawkeyeDetailsVo
						.setTypicalPurchaseDate(rs.get("TYPICAL_PURCHASE_DAYS") == null ? MapperConstants.BLANK_STRING
								: rs.get("TYPICAL_PURCHASE_DAYS").toString());
			  hawkeyeDetailsVo
				.setDaysSinceLastPurchase(rs.get("DAYS_SINCE_LAST_PURCHASE") == null ? MapperConstants.BLANK_STRING
					: rs.get("DAYS_SINCE_LAST_PURCHASE").toString());
			  hawkeyeDetailsVo
				.setTypicalBrowseDays(rs.get("TYPICAL_BROWSE_DAYS") == null ? MapperConstants.BLANK_STRING
					: rs.get("TYPICAL_BROWSE_DAYS").toString());
			  hawkeyeDetailsVo
				.setDayaSinceLastBrowse(rs.get("DAYS_SINCE_LAST_BROWSE") == null ? MapperConstants.BLANK_STRING
					: rs.get("DAYS_SINCE_LAST_BROWSE").toString());
			hawkeyeDetailsVo
					.setTypicalSpendAmount(rs.get("TYPICAL_SPEND_AMOUNT") == null ? MapperConstants.BLANK_STRING
					: rs.get("TYPICAL_SPEND_AMOUNT").toString());
			hawkeyeDetailsVo
					.setAmountSpent(rs.get("AMOUNT_SPENT") == null ? MapperConstants.BLANK_STRING
					: rs.get("AMOUNT_SPENT").toString());
	        
	        objects.add(hawkeyeDetailsVo);
	      }}
		
		return objects;
	}

	@Override
	public SavingsVo mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<CategoryDataVo> mapRowCatData(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		CategoryDataVo categoryDataVo = null;
    	List<CategoryDataVo> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<CategoryDataVo>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	    	  categoryDataVo = new CategoryDataVo();
	    	  categoryDataVo
						.setCategoryData(rs.get("CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("CATEGORY").toString());
	    	  categoryDataVo
						.setStatus(rs.get("STATUS") == null ? MapperConstants.BLANK_STRING
								: rs.get("STATUS").toString());
	    	  categoryDataVo
						.setValueOfContact(rs.get("VALUE_OF_CONTACT") == null ? MapperConstants.BLANK_STRING
								: rs.get("VALUE_OF_CONTACT").toString());
	    	  categoryDataVo
						.setPurchasedCat(rs.get("PURCHASED_CATEGORY") == null ? MapperConstants.BLANK_STRING
								: rs.get("PURCHASED_CATEGORY").toString());
	    	  	        
	        objects.add(categoryDataVo);
	      }}
		
		return objects;
	}	

}
