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

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.ContactDateVO;


public class ContactDateMapper implements RowMapper<ContactDateVO> , StaplesDashBoardRowMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		ContactDateVO ContactDatevo = null;
		List<Object> objects = null;
		    if (resultMap != null && !resultMap.isEmpty()) {
		    	if (objects == null) {
		    		objects = new ArrayList<Object>();
			        }
		      for (Map<String, Object> rs : resultMap) {
		    	  ContactDatevo = new ContactDateVO();
		    	  ContactDatevo
					.setCUSTOMER_NUMBER(rs.get("CUSTOMER_NUMBER") == null ? MapperConstants.BLANK_STRING
							: rs.get("CUSTOMER_NUMBER").toString());
		    	  ContactDatevo
					.setSLS_REP_NAME(rs.get("SLS_REP_NAME") == null ? MapperConstants.BLANK_STRING
							: rs.get("SLS_REP_NAME").toString());
		    	  ContactDatevo
					.setTM_KY(rs.get("TM_KY") == null ? MapperConstants.BLANK_STRING
							: rs.get("TM_KY").toString());
		    	  ContactDatevo
					.setCLD_DT(rs.get("CLD_DT") == null ? MapperConstants.BLANK_STRING
							: rs.get("CLD_DT").toString());
		    	  ContactDatevo
					.setFSC_YR(rs.get("FSC_YR") == null ? MapperConstants.BLANK_STRING
							: rs.get("FSC_YR").toString());
		    	  ContactDatevo
					.setFSC_PRD(rs.get("FSC_PRD") == null ? MapperConstants.BLANK_STRING
							: rs.get("FSC_PRD").toString());
		    	  ContactDatevo
					.setFSC_WK(rs.get("FSC_WK") == null ? MapperConstants.BLANK_STRING
							: rs.get("FSC_WK").toString());
		    	  ContactDatevo
					.setFSC_DY(rs.get("FSC_DY") == null ? MapperConstants.BLANK_STRING
							: rs.get("FSC_DY").toString());
		    	  ContactDatevo
					.setLOGGED_IN_USR_NAME(rs.get("LOGGED_IN_USR_NAME") == null ? MapperConstants.BLANK_STRING
							: rs.get("LOGGED_IN_USR_NAME").toString());
		    	  ContactDatevo
					.setCHECKED_IN_DATE(rs.get("CHECKED_IN_DATE") == null ? MapperConstants.BLANK_STRING
							: rs.get("CHECKED_IN_DATE").toString());
		        objects.add(ContactDatevo);
		        
		      }}
		return objects;
	}

	
	public ContactDateVO mapSingleRow(Map<String,Object> rs) throws SQLException {
		ContactDateVO ContactDatevo = null;
  	  ContactDatevo = new ContactDateVO();
  	  ContactDatevo
			.setCUSTOMER_NUMBER(rs.get("CUSTOMER_NUMBER") == null ? MapperConstants.BLANK_STRING
					: rs.get("CUSTOMER_NUMBER").toString());
  	  ContactDatevo
  			.setSLS_REP_NAME(rs.get("SLS_REP_NAME") == null ? MapperConstants.BLANK_STRING
  					: rs.get("SLS_REP_NAME").toString());
  	  ContactDatevo
			.setCLD_DT(rs.get("CLD_DT") == null ? MapperConstants.BLANK_STRING
					: rs.get("CLD_DT").toString());
  	ContactDatevo
	.setTM_KY(rs.get("TM_KY") == null ? MapperConstants.BLANK_STRING
			: rs.get("TM_KY").toString());
  	  ContactDatevo
			.setFSC_YR(rs.get("FSC_YR") == null ? MapperConstants.BLANK_STRING
					: rs.get("FSC_YR").toString());
  	  ContactDatevo
			.setFSC_PRD(rs.get("FSC_PRD") == null ? MapperConstants.BLANK_STRING
					: rs.get("FSC_PRD").toString());
  	  ContactDatevo
			.setFSC_WK(rs.get("FSC_WK") == null ? MapperConstants.BLANK_STRING
					: rs.get("FSC_WK").toString());
  	  ContactDatevo
			.setFSC_DY(rs.get("FSC_DY") == null ? MapperConstants.BLANK_STRING
					: rs.get("FSC_DY").toString());
  	ContactDatevo
	.setLOGGED_IN_USR_NAME(rs.get("LOGGED_IN_USR_NAME") == null ? MapperConstants.BLANK_STRING
			: rs.get("LOGGED_IN_USR_NAME").toString());
  ContactDatevo
	.setCHECKED_IN_DATE(rs.get("CHECKED_IN_DATE") == null ? MapperConstants.BLANK_STRING
			: rs.get("CHECKED_IN_DATE").toString());
    
		return ContactDatevo;
	}

	@Override
	public ContactDateVO mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

