package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.LastLiveContactAndHistoryVO;
import com.staples.dashboard.app.vo.YTDInfoVO;

import org.springframework.jdbc.core.RowMapper;

public class LastLiveContactAndHistoryMapper
		implements RowMapper<LastLiveContactAndHistoryVO>, StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * 
	 * @param ResultSet
	 *            rs
	 * @param int
	 *            rowNum
	 * @return
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */

	public LastLiveContactAndHistoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LastLiveContactAndHistoryVO vo = new LastLiveContactAndHistoryVO();

		vo.setActivityDate(rs.getString(MapperConstants.ACTIVITYDATE) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.ACTIVITYDATE));
		vo.setDescription(rs.getString(MapperConstants.DESCRIPTION) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.DESCRIPTION));
		vo.setStatus(rs.getString(MapperConstants.STATUS) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.STATUS));
		vo.setSubject(rs.getString(MapperConstants.SUBJECT) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.SUBJECT));
		vo.setWhoId(rs.getString(MapperConstants.CONTACT_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.CONTACT_NAME));
		vo.setOwnerId(rs.getString(MapperConstants.OWNER_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.getString(MapperConstants.OWNER_NAME));
		return vo;
	}

	/**
	 * Method implementation to get row map.
	 * 
	 * @param List<Map<String,Object>>
	 *            resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		LastLiveContactAndHistoryVO vo = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				if (objects == null) {
					objects = new ArrayList<Object>();
				}
				vo = new LastLiveContactAndHistoryVO();

				vo.setActivityDate(rs.get(MapperConstants.ACTIVITYDATE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.ACTIVITYDATE).toString());
				vo.setDescription(rs.get(MapperConstants.DESCRIPTION) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.DESCRIPTION).toString());
				vo.setStatus(rs.get(MapperConstants.STATUS) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.STATUS).toString());
				vo.setSubject(rs.get(MapperConstants.SUBJECT) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.SUBJECT).toString());
				vo.setWhoId(rs.get(MapperConstants.CONTACT_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CONTACT_NAME).toString());
				vo.setOwnerId(rs.get(MapperConstants.OWNER_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.OWNER_NAME).toString());
				objects.add(vo);

			}
		}
		return objects;
	}
	
	public YTDInfoVO mapRowSalesInfo(Map<String, Object> resultMap) throws SQLException {
		YTDInfoVO ytdInfoVO = new YTDInfoVO();
		if(null!=resultMap){
			ytdInfoVO.setYear(resultMap.get(MapperConstants.YEAR) == null ? MapperConstants.BLANK_STRING
					: resultMap.get(MapperConstants.YEAR).toString());
			ytdInfoVO.setYtdSales(resultMap.get(MapperConstants.YTD_SALES) == null ? MapperConstants.BLANK_STRING
					: resultMap.get(MapperConstants.YTD_SALES).toString());
		}
		return ytdInfoVO;
	}
}
