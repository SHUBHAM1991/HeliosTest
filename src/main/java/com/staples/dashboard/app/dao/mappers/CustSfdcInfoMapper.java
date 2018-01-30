package com.staples.dashboard.app.dao.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.CustSfdcInfoVO;

public class CustSfdcInfoMapper implements CTABaseMapper {

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		CustSfdcInfoVO infoVos   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        infoVos = new CustSfdcInfoVO();	       
			infoVos.setContactId(rs.get(CTAConstants.CONTACT_ID) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CONTACT_ID).toString());
			infoVos.setFirstName(rs.get(CTAConstants.CONTACT_FIRSTNAME) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CONTACT_FIRSTNAME).toString());
			infoVos.setLastName(rs.get(CTAConstants.CONTACT_LASTNAME)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CONTACT_LASTNAME).toString());
			infoVos.setFullName(infoVos.getFirstName()+" "+infoVos.getLastName());
			objects.add(infoVos);
			
	      }
	    }
	    return objects;	
	}
}
