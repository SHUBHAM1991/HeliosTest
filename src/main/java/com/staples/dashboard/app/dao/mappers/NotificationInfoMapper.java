package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.NotificationInfoVo;

import org.springframework.jdbc.core.RowMapper;

/**
 * The Class NotificationInfoMapper.
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
public class NotificationInfoMapper implements RowMapper<NotificationInfoVo> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public NotificationInfoVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		NotificationInfoVo notiInfoVO = new NotificationInfoVo();
		notiInfoVO.setSegmentId((rs.getString(MapperConstants.NOTIFI_segmentId)));
		notiInfoVO.setSegmentDesc(rs.getString(MapperConstants.NOTIFI_segmentDesc));
		notiInfoVO.setSementType((rs.getString(MapperConstants.NOTIFI_segmentType)));
		notiInfoVO.setSegmentName((rs.getString(MapperConstants.NOTIFI_segmentName)));
		return notiInfoVO;
	}
	
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		NotificationInfoVo notiInfoVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        notiInfoVO = new NotificationInfoVo();
	        notiInfoVO.setSegmentId((rs.get(MapperConstants.NOTIFI_SEG_ID) 
	        		== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_ID).toString()));
	        notiInfoVO.setSegmenFreq((rs.get(MapperConstants.NOTIFI_SEG_FREQ) 
	        		== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_FREQ).toString()));
	        notiInfoVO.setSegmentDesc((rs.get(MapperConstants.NOTIFI_SEG_DESC) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_DESC).toString()));
	        notiInfoVO.setSementType((rs.get(MapperConstants.NOTIFI_SEG_TYPE) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_TYPE).toString()));
	        notiInfoVO.setSegmentName((rs.get(MapperConstants.NOTIFI_SEG_NAME) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_NAME).toString()));
	        notiInfoVO.setSegLastRefDate((rs.get(MapperConstants.NOTIFI_SEG_LAST_REFRESH_DATE) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.NOTIFI_SEG_LAST_REFRESH_DATE).toString()));
	        objects.add(notiInfoVO);
	        
	      }
	    }
	    return objects;
	}
	
	public List<NotificationInfoVo> updateNotificationVoList(List<Map<String, Object>> returnList ,List<NotificationInfoVo> notiInfoVo){
		if (null != returnList && returnList.size() > 0) {
			for (int i = 0; i < notiInfoVo.size(); i++) {
				for (Map<String, Object>rs : returnList) {
					if (null != notiInfoVo.get(i).getSegmentId()
							&& !("".equals(notiInfoVo.get(i).getSegmentId()))) {
						String format = String.format("%%0%dd", 3);
						String subSegId = String.format(format, Integer.parseInt(notiInfoVo.get(i).getSegmentId()));
					if (null!=rs.get("CTA_SUB_SEGMENT") && subSegId
							.equals(rs.get("CTA_SUB_SEGMENT").toString())) {
						notiInfoVo.get(i).setDispStatus(
								rs.get("SEG_DISP_STATUS").toString()== null ? MapperConstants.BLANK_STRING : rs.get("SEG_DISP_STATUS").toString());
						notiInfoVo.get(i).setDispStatusDate(
								rs.get("TASK_INSERT_DATE_DISPLAY").toString()== null ? MapperConstants.BLANK_STRING : rs.get("TASK_INSERT_DATE_DISPLAY").toString());
						
					}
				  }
				}

			}
		}
		return notiInfoVo;
	}

}