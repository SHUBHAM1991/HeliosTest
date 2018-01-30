package com.staples.dashboard.app.dao.mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SegmentSubDetail;

public class SegmentSubDetailMapper implements CTABaseMapper{

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		SegmentSubDetail subDetail   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        subDetail = new SegmentSubDetail();	       
			subDetail.setTaskIdCombination(rs.get(CTAConstants.TASK_ID_COMBINATION) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.TASK_ID_COMBINATION).toString());
			subDetail.setCustomerNumber(rs.get(CTAConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CUSTOMER_NUMBER).toString());
			subDetail.setDivison(rs.get(CTAConstants.DIVISION)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.DIVISION).toString());
			subDetail.setCtaSegment(rs.get(CTAConstants.CTA_SEGMENT)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CTA_SEGMENT).toString());
			subDetail.setCtaSubSegment(rs.get(CTAConstants.CTA_SUB_SEGMENT)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CTA_SUB_SEGMENT).toString());
			subDetail.setFyrFprdFwkFdy(rs.get(CTAConstants.FYR_FPRD_FWK_FDY)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.FYR_FPRD_FWK_FDY).toString());
			subDetail.setCtaSegStatus(rs.get(CTAConstants.CTA_SEG_STATUS)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CTA_SEG_STATUS).toString());
			subDetail.setTaskInsertDate(rs.get(CTAConstants.TASK_INSERT_DATE)==null ? MapperConstants.BLANK_STRING :rs.get(CTAConstants.TASK_INSERT_DATE).toString());
			subDetail.setHeaderId(rs.get(CTAConstants.HEADER_ID)==null?MapperConstants.BLANK_STRING : rs.get(CTAConstants.HEADER_ID).toString());
			subDetail.setParamId(rs.get(CTAConstants.PARAM_ID)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.PARAM_ID).toString());
			subDetail.setParamLabel(rs.get(CTAConstants.PARAM_LABEL)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.PARAM_LABEL).toString());
			subDetail.setParamValue(rs.get(CTAConstants.DYNAMIC_PARAM_VALUE)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.DYNAMIC_PARAM_VALUE).toString());
			subDetail.setConstantParamValue(rs.get(CTAConstants.CONSTANT_PARAM_VALUE)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CONSTANT_PARAM_VALUE).toString());
			subDetail.setCreatedBy(rs.get(CTAConstants.CREATED_BY)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CREATED_BY).toString());
			subDetail.setUpdatedBy(rs.get(CTAConstants.UPDATED_BY)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.UPDATED_BY).toString());
			objects.add(subDetail);
			
	      }
	    }
	    return objects;
	
	
	}
	private Date stringToDate(String dateString){
		//String string = "January 2, 2010";
		Date date = null;
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
