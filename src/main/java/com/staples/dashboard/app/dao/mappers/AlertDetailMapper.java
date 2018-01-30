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
import com.staples.dashboard.app.vo.AlertDetailVo;
import com.staples.dashboard.app.vo.SegmentSubDetail;

public class AlertDetailMapper implements CTABaseMapper{

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		AlertDetailVo alertDetail   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        alertDetail = new AlertDetailVo();	       
	        alertDetail.setAlertIdCombination(rs.get(MapperConstants.ALERT_ID_COMBINATION) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_ID_COMBINATION).toString());
	        alertDetail.setCustomerNumber(rs.get(CTAConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CUSTOMER_NUMBER).toString());
	        alertDetail.setDivison(rs.get(CTAConstants.DIVISION)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.DIVISION).toString());
	        alertDetail.setAlertId(rs.get(MapperConstants.ALERT_ID)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_ID).toString());
	        alertDetail.setIsAlertActive(rs.get(MapperConstants.IS_ALERT_ACTIVE)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.IS_ALERT_ACTIVE).toString());
	        alertDetail.setIsDeleted(rs.get(MapperConstants.IS_DELETED)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.IS_DELETED).toString());
	        alertDetail.setIsRead(rs.get(MapperConstants.IS_READ)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.IS_READ).toString());
	        alertDetail.setLinkAlerts(rs.get(MapperConstants.LINK_ALERTS)==null ? MapperConstants.BLANK_STRING :rs.get(MapperConstants.LINK_ALERTS).toString());
	        alertDetail.setAlertType(rs.get(MapperConstants.ALERT_TYPE)==null?MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_TYPE).toString());
	        alertDetail.setAlertStratDate(rs.get(MapperConstants.ALERT_START_DATE)==null?MapperConstants.BLANK_STRING:rs.get(MapperConstants.ALERT_START_DATE).toString());
	        alertDetail.setIconPath(rs.get(MapperConstants.ICON_IMG_PATH)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ICON_IMG_PATH).toString());
	        alertDetail.setIconName(rs.get(MapperConstants.ICON_IMG_NAME)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ICON_IMG_NAME).toString());
	        alertDetail.setAlertSubject(rs.get(MapperConstants.ALERT_SUBJECT)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_SUBJECT).toString());
	        alertDetail.setAlertShortDesc(rs.get(MapperConstants.ALERT_SHORT_DESCRIPTION)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_SHORT_DESCRIPTION).toString());
	        alertDetail.setAlertTxt(rs.get(MapperConstants.ALERT_TXT)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ALERT_TXT).toString());
	        alertDetail.setMaxLinkAlert(rs.get(MapperConstants.MAX_LINK_ALERT)==null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.MAX_LINK_ALERT).toString());
	        alertDetail.setParamId(rs.get(MapperConstants.PARAM_ID)==null ? MapperConstants.NO_PARAM_ID : rs.get(MapperConstants.PARAM_ID).toString());
	        alertDetail.setParamVal(rs.get(MapperConstants.PARAM_VALUE)==null ? MapperConstants.NO_PARAM_VAL : rs.get(MapperConstants.PARAM_VALUE).toString());
	        alertDetail.setParamLabel(rs.get(MapperConstants.PARAM_LABEL)==null ? MapperConstants.NO_PARAM_KEY : rs.get(MapperConstants.PARAM_LABEL).toString());
			objects.add(alertDetail);
			
	      }
	    }
	    return objects;
	}
	

}
