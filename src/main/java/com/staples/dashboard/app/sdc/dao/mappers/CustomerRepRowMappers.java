package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.sdc.vo.CustomerInfo;
import com.staples.dashboard.app.sdc.vo.CustomerParentChildRewardsVo;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.vo.CategoryDataVo;

public class CustomerRepRowMappers implements RowMapper<CustomerRepResponse>{
	
	
	public CustomerRepResponse mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		CustomerRepResponse representive=new CustomerRepResponse();
		Map<String,List<CustomerInfo>> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String repNumber = getColumnString(rs, "EMP_NUMBER");
				List<CustomerInfo> list = orders.get(repNumber);
				if(list==null){
					representive.setRepName(getColumnString(rs, "REPNAME"));
					representive.setRepNumber(repNumber);
					representive.setRepPhone(getColumnString(rs,"REPPHONE"));
					list=new ArrayList<>();
					orders.put(repNumber, list);
				}
				CustomerInfo info=new CustomerInfo();
				info.setSAM_SFDC_ID(getColumnString(rs, "SAM_SFDC_ID")== null ? MapperConstants.BLANK_STRING
						: rs.get("SAM_SFDC_ID").toString());
				
				info.setBillingCity(getColumnString(rs, "BILLINGCITY")== null ? MapperConstants.BLANK_STRING
						: rs.get("BILLINGCITY").toString());
				info.setBillingState(getColumnString(rs, "BILLINGSTATE")== null ? MapperConstants.BLANK_STRING
						: rs.get("BILLINGSTATE").toString());
				info.setBillingStreet(getColumnString(rs, "BILLINGSTREET")== null ? MapperConstants.BLANK_STRING
						: rs.get("BILLINGSTREET").toString());
				info.setBusinessUnit(getColumnString(rs, "BUSINESS_UNIT")== null ? MapperConstants.BLANK_STRING
						: rs.get("BUSINESS_UNIT").toString());
				info.setCompanyName(getColumnString(rs, "COMPANY_NAME")== null ? MapperConstants.BLANK_STRING
						: rs.get("COMPANY_NAME").toString());
				info.setContactEmailId(getColumnString(rs, "CONTACT_EMAIL_ID")== null ? MapperConstants.BLANK_STRING
						: rs.get("CONTACT_EMAIL_ID").toString());
				info.setCustomerNo(getColumnString(rs, "CUSTOMER_NUMBER")== null ? MapperConstants.BLANK_STRING
						: rs.get("CUSTOMER_NUMBER").toString());
				info.setFirstName(getColumnString(rs, "FIRST_NAME")== null ? MapperConstants.BLANK_STRING
						: rs.get("FIRST_NAME").toString());
				info.setLastName(getColumnString(rs, "LAST_NAME")== null ? MapperConstants.BLANK_STRING
						: rs.get("LAST_NAME").toString());
				info.setPhone(getColumnString(rs, "ACCOUNT_PHONE")== null ? MapperConstants.BLANK_STRING
						: rs.get("ACCOUNT_PHONE").toString());
				info.setPostalCode(getColumnString(rs, "POSTALCODE")== null ? MapperConstants.BLANK_STRING
						: rs.get("POSTALCODE").toString());
				info.setTier(getColumnString(rs, "REWARDS_TIER")== null ? MapperConstants.BLANK_STRING
						: rs.get("REWARDS_TIER").toString());
				info.setRewardNo(getColumnString(rs, "rewards_number".toUpperCase())== null ? MapperConstants.BLANK_STRING
						: rs.get("rewards_number".toUpperCase()).toString());
				info.setAccount_rank(getColumnString(rs, "ACCOUNT_RANK")== null ? MapperConstants.BLANK_STRING
						: rs.get("ACCOUNT_RANK").toString());
				info.setLast_contacted_date(getColumnString(rs, "LAST_CONTACTED_DATE")== null ? MapperConstants.BLANK_STRING
						: rs.get("LAST_CONTACTED_DATE").toString());
				info.setEnrollDate(getColumnString(rs, "ENROLLED_DT")== null ? MapperConstants.BLANK_STRING
						: rs.get("ENROLLED_DT").toString());
				info.setIndustryCode(getColumnString(rs, "INDUSTRY_CODE")== null ? MapperConstants.BLANK_STRING
						: rs.get("INDUSTRY_CODE").toString());
				info.setCallReason(getColumnString(rs, "CALL_REASON")== null ? MapperConstants.BLANK_STRING
						: rs.get("CALL_REASON").toString());
				info.setCategory(getColumnString(rs, "CATEGORY")== null ? MapperConstants.BLANK_STRING
						: rs.get("CATEGORY").toString());
				info.setAccountId(getColumnString(rs, "ACCOUNT_ID")== null ? MapperConstants.BLANK_STRING
						: rs.get("ACCOUNT_ID").toString());
				info.setTimeZone(getColumnString(rs, "TM_ZN")== null ? MapperConstants.BLANK_STRING
						: rs.get("TM_ZN").toString());
				list.add(info);
				representive.setCustomerInfo(list);
			}
			
		}
		
		return representive;
	}
	
	public List<CustomerParentChildRewardsVo> mapRowRewardsData(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		CustomerParentChildRewardsVo categoryDataVo = null;
    	List<CustomerParentChildRewardsVo> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<CustomerParentChildRewardsVo>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	    	  categoryDataVo = new CustomerParentChildRewardsVo();
	    	  categoryDataVo
						.setParentRewards(rs.get("PARENT_REWARDS") == null ? MapperConstants.BLANK_STRING
								: rs.get("PARENT_REWARDS").toString());
	    	  categoryDataVo
						.setChildRewards(rs.get("CHILD_REWARDS") == null ? MapperConstants.BLANK_STRING
								: rs.get("CHILD_REWARDS").toString());
	    	 
	        objects.add(categoryDataVo);
	      }
	      }
		
		return objects;
	}
	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}

	@Override
	public CustomerRepResponse mapRow(ResultSet arg0, int arg1) throws SQLException {
		return null;
	}
}
