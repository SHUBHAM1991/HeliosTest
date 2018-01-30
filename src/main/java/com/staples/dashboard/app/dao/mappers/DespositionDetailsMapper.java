/**
 * 
 */
package com.staples.dashboard.app.dao.mappers;

/**
 * @author VohLo001
 *
 */

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
import com.staples.dashboard.app.vo.DispositionDetailsVo;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;


public class DespositionDetailsMapper implements RowMapper<SegHdrText> , StaplesDashBoardRowMapper {


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
	
	 public DispositionDetailsVo mapRow(Map<String,Object> rs) {
		 DispositionDetailsVo dispositionDetailsVo = new DispositionDetailsVo();
		 dispositionDetailsVo.setDispositionStatus(rs.get("SEG_DISP_STATUS") == null ? MapperConstants.BLANK_STRING
					:rs.get("SEG_DISP_STATUS").toString());
		 dispositionDetailsVo.setCommentText(rs.get("COMMENT_TEXT") == null ? MapperConstants.BLANK_STRING
					:rs.get("COMMENT_TEXT").toString());
		 dispositionDetailsVo.setTaskInsertDate(rs.get("TASK_INSERT_DATE") == null ? MapperConstants.BLANK_STRING
					:rs.get("TASK_INSERT_DATE").toString());
		 dispositionDetailsVo.setFullTaskInsertDate(rs.get("FULL_TASK_INSERT_DATE") == null ? MapperConstants.BLANK_STRING
					:rs.get("FULL_TASK_INSERT_DATE").toString());
		 dispositionDetailsVo.setCreatedBy(rs.get("CREATED_BY") == null ? MapperConstants.BLANK_STRING
					:rs.get("CREATED_BY").toString());
		 dispositionDetailsVo.setContactId(rs.get("CONTACT_ID") == null ? MapperConstants.BLANK_STRING
					:rs.get("CONTACT_ID").toString());
		 dispositionDetailsVo.setSfdcContactFullName(rs.get("SFDC_CONTACT_FULLNAME") == null ? MapperConstants.BLANK_STRING
					:rs.get("SFDC_CONTACT_FULLNAME").toString());
		 dispositionDetailsVo.setTaskSubject(rs.get("TASK_SUBJECT") == null ? MapperConstants.BLANK_STRING
					:rs.get("TASK_SUBJECT").toString());
			
			return dispositionDetailsVo;
	}
	
	@Override
	public SegHdrText mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

