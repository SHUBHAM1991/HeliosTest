/**
 * 
 */
package com.staples.dashboard.app.dao.mappers;

/**
 * @author VohLo001
 *
 */
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.ContactDateVO;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;


public class CTAHdrTxtRowMapper implements RowMapper<SegHdrText> , StaplesDashBoardRowMapper {


	public SegHeaderLabelsVO mapRow(Map<String,Object> resultMap ,int i) {
		SegHeaderLabelsVO segHeaderLabelsVO = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	  segHeaderLabelsVO = new SegHeaderLabelsVO();
		    	  segHeaderLabelsVO.setHeaderId(resultMap.get("HDR_ID") == null ? MapperConstants.BLANK_STRING
							: resultMap.get("HDR_ID").toString());
					
		    	  segHeaderLabelsVO.setHeaderName(resultMap.get("HEADER_NAME") == null ? MapperConstants.BLANK_STRING
							: resultMap.get("HEADER_NAME").toString());
					
		      }
		return segHeaderLabelsVO;
	}
	
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> rs
	 * @return Object
	 */
	
	 public SegHdrText mapRow(Map<String,Object> rs) {
		 SegHdrText segHdrText = new SegHdrText();
			segHdrText.setSeg(rs.get(CTAConstants.SEG) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.SEG).toString());
			
			segHdrText.setSegId(rs.get(CTAConstants.SEG_ID) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.SEG_ID).toString());
			
			segHdrText.setHeaderIdsShown(rs.get(CTAConstants.HEADER_IDS_SHOWN) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HEADER_IDS_SHOWN).toString());
			
			segHdrText.setTotalHeaders(rs.get(CTAConstants.TOTAL_HEADERS) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.TOTAL_HEADERS).toString());
			
			segHdrText.setHdr1StaticText(rs.get(CTAConstants.HDR_1_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_1_STATIC_TEXT)));
			
			segHdrText.setHdr1ParamCount(rs.get(CTAConstants.HDR_1_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_1_PARAM_COUNT).toString());
			
			segHdrText.setHdr2StaticText(rs.get(CTAConstants.HDR_2_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_2_STATIC_TEXT)));
			
			segHdrText.setHdr2ParamCount(rs.get(CTAConstants.HDR_2_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_2_PARAM_COUNT).toString());
			
			segHdrText.setHdr3StaticText(rs.get(CTAConstants.HDR_3_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_3_STATIC_TEXT)));
			
			segHdrText.setHdr3ParamCount(rs.get(CTAConstants.HDR_3_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_3_PARAM_COUNT).toString());
			
			segHdrText.setHdr4StaticText(rs.get(CTAConstants.HDR_4_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_4_STATIC_TEXT)));
			
			segHdrText.setHdr4ParamCount(rs.get(CTAConstants.HDR_4_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_4_PARAM_COUNT).toString());
			
			segHdrText.setHdr5StaticText(rs.get(CTAConstants.HDR_5_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_5_STATIC_TEXT)));
			
			segHdrText.setHdr5ParamCount(rs.get(CTAConstants.HDR_5_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_5_PARAM_COUNT).toString());
			
			segHdrText.setHdr6StaticText(rs.get(CTAConstants.HDR_6_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_6_STATIC_TEXT)));
			
			segHdrText.setHdr6ParamCount(rs.get(CTAConstants.HDR_6_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_6_PARAM_COUNT).toString());
			
			segHdrText.setHdr7StaticText(rs.get(CTAConstants.HDR_7_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_7_STATIC_TEXT)));
			
			segHdrText.setHdr7ParamCount(rs.get(CTAConstants.HDR_7_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_7_PARAM_COUNT).toString());
			
			segHdrText.setHdr8StaticText(rs.get(CTAConstants.HDR_8_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_8_STATIC_TEXT)));
			
			segHdrText.setHdr8ParamCount(rs.get(CTAConstants.HDR_8_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_8_PARAM_COUNT).toString());
			
			segHdrText.setHdr9StaticText(rs.get(CTAConstants.HDR_9_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_9_STATIC_TEXT)));
			
			segHdrText.setHdr9ParamCount(rs.get(CTAConstants.HDR_9_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_9_PARAM_COUNT).toString());
			
			segHdrText.setHdr10StaticText(rs.get(CTAConstants.HDR_10_STATIC_TEXT) == null ? MapperConstants.BLANK_STRING
					:convertToString((Clob)rs.get(CTAConstants.HDR_10_STATIC_TEXT)));
			
			segHdrText.setHdr10ParamCount(rs.get(CTAConstants.HDR_10_PARAM_COUNT) == null ? MapperConstants.BLANK_STRING
					:rs.get(CTAConstants.HDR_10_PARAM_COUNT).toString());
			
			return segHdrText;
	}
	private String convertToString(Clob clob){
		String value = null;
		try {
			if(clob!=null){
				value = clob.getSubString(1, (int) clob.length());
				value=StringEscapeUtils.escapeHtml4(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public SegHdrText mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

