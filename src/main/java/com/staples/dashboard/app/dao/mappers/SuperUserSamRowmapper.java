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
import com.staples.dashboard.app.vo.StaplesDotcomActivityVo;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;

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
public class SuperUserSamRowmapper implements RowMapper<SavingsVo> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		SuperUserDetailsVo superUserDetailsVo = null;
	List<Object> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<Object>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	    	  superUserDetailsVo = new SuperUserDetailsVo();
	    	  
	          
	    	  superUserDetailsVo
						.setCustNum(rs.get("CUSTOMER_NUMBER") == null ? MapperConstants.BLANK_STRING
								: rs.get("CUSTOMER_NUMBER").toString());
	    	  superUserDetailsVo
						.setCompName(rs.get("COMPANY_NAME") == null ? MapperConstants.BLANK_STRING
								: rs.get("COMPANY_NAME").toString());
	    	  superUserDetailsVo
						.setAccId(rs.get("ACCOUNT_ID") == null ? MapperConstants.BLANK_STRING
								: rs.get("ACCOUNT_ID").toString());
	    	  superUserDetailsVo
						.setCustTimeone(rs.get("CUST_TIME_ZONE") == null ? MapperConstants.BLANK_STRING
								: rs.get("CUST_TIME_ZONE").toString());
	    	  superUserDetailsVo
						.setMasterContactSfId(rs.get("MASTER_CONTACT_SF_ID") == null ? MapperConstants.BLANK_STRING
								: rs.get("MASTER_CONTACT_SF_ID").toString());
	    	  
	    	  superUserDetailsVo
				.setContactFullName(rs.get("CONTACT_FULL_NAME") == null ? MapperConstants.BLANK_STRING
						: rs.get("CONTACT_FULL_NAME").toString());
	    	  superUserDetailsVo
						.setPhoneNum(rs.get("PHONE_NUMBER") == null ? MapperConstants.BLANK_STRING
								: rs.get("PHONE_NUMBER").toString());
	    	  superUserDetailsVo
						.setEmail(rs.get("EMAIL_ADDRESS") == null ? MapperConstants.BLANK_STRING
								: rs.get("EMAIL_ADDRESS").toString());
	    	  superUserDetailsVo
						.setHeRank(rs.get("HE_RANK") == null ? MapperConstants.BLANK_STRING
								: rs.get("HE_RANK").toString());
	    	  	        
	        objects.add(superUserDetailsVo);
	      }}
		
		return objects;
	}

	@Override
	public SavingsVo mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<StaplesDotcomActivityVo> mapRowSkuData(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		StaplesDotcomActivityVo staplesDotcomActivityVo = null;
    	List<StaplesDotcomActivityVo> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<StaplesDotcomActivityVo>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	    	  staplesDotcomActivityVo = new StaplesDotcomActivityVo();
	    	  
	    	  staplesDotcomActivityVo
						.setSkuNum(rs.get("SKU") == null ? MapperConstants.BLANK_STRING
								: rs.get("SKU").toString());
	    	  staplesDotcomActivityVo
						.setSkuName(rs.get("SKU_NAME") == null ? MapperConstants.BLANK_STRING
								: rs.get("SKU_NAME").toString());
	    	  staplesDotcomActivityVo
						.setThumbnail(rs.get("THUMBNAIL") == null ? MapperConstants.BLANK_STRING
								: rs.get("THUMBNAIL").toString());
	    	  staplesDotcomActivityVo
						.setCartActivity(rs.get("CART_ACTIVITY") == null ? MapperConstants.BLANK_STRING
								: rs.get("CART_ACTIVITY").toString());
	    	  staplesDotcomActivityVo
				.setCallReasonCart(rs.get("CALL_REASON_CATEGORY") == null ? MapperConstants.BLANK_STRING
						: rs.get("CALL_REASON_CATEGORY").toString());
	    	  staplesDotcomActivityVo
				.setUnitPrice(rs.get("UNIT_PRICE") == null ? MapperConstants.BLANK_STRING
						: rs.get("UNIT_PRICE").toString());
	    	  staplesDotcomActivityVo
				.setPurchaseHistory(rs.get("PURCHASE_HISTORY") == null ? MapperConstants.BLANK_STRING
						: rs.get("PURCHASE_HISTORY").toString());
	    	  staplesDotcomActivityVo
				.setLastBrowseDate(rs.get("SKU_LAST_BROWSE_DATE") == null ? MapperConstants.BLANK_STRING
						: rs.get("SKU_LAST_BROWSE_DATE").toString());
	    	  staplesDotcomActivityVo
				.setSkuCategory(rs.get("PRODUCT_CATEGORY") == null ? MapperConstants.BLANK_STRING
						: rs.get("PRODUCT_CATEGORY").toString());
	        objects.add(staplesDotcomActivityVo);
	      }}
		
		return objects;
	}	

}
