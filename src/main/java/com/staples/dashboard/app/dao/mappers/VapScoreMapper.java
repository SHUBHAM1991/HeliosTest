/**
 * 
 */
package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.VapScoreVO;

/**
 * @author jaisa001
 * 
 */
public class VapScoreMapper implements RowMapper<VapScoreVO>,
	StaplesDashBoardRowMapper {

    public VapScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	VapScoreVO vapScoreVO = new VapScoreVO();
	vapScoreVO.setVapScoreId(rs.getString(MapperConstants.VAP_SCORE_ID));
	vapScoreVO
		.setVapScoreType(rs.getString(MapperConstants.VAP_SCORE_TYPE));
	vapScoreVO
		.setVapScoreName(rs.getString(MapperConstants.VAP_SCORE_NAME));
	vapScoreVO
		.setVapScoreDesc(rs.getString(MapperConstants.VAP_SCORE_DESC));
	return vapScoreVO;
    }

    /**
     * Method implementation to get row map.
     * 
     * @param List
     *            <Map<String,Object>> rs
     * @return Object
     */
    public Object mapRow(Map<String, Object> rs) {

	final VapScoreVO vapScoreVO = new VapScoreVO();
	vapScoreVO
		.setVapScoreId(rs.get(MapperConstants.VAP_SCORE_ID) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.VAP_SCORE_ID).toString());
	vapScoreVO
		.setVapScoreType(rs.get(MapperConstants.VAP_SCORE_TYPE) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.VAP_SCORE_TYPE).toString());
	vapScoreVO
		.setVapScoreName(rs.get(MapperConstants.VAP_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.VAP_SCORE_NAME).toString());
	vapScoreVO
		.setVapScoreDesc(rs.get(MapperConstants.VAP_SCORE_DESC) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.VAP_SCORE_DESC).toString());
	return vapScoreVO;
    }

    /**
     * Method implementation to get row map.
     * 
     * @param List
     *            <Map<String,Object>> resultMap
     * @return List<Object>
     */
    @Override
    public List<Object> mapRow(List<Map<String, Object>> resultMap) {

	List<Object> objects = null;
	VapScoreVO vapScoreVO = null;
	if (resultMap != null && !resultMap.isEmpty()) {
	    for (Map<String, Object> rs : resultMap) {
		if (objects == null) {
		    objects = new ArrayList<Object>();
		}
		vapScoreVO = new VapScoreVO();
		vapScoreVO
			.setVapScoreId(rs.get(MapperConstants.VAP_SCORE_ID) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.VAP_SCORE_ID)
					.toString());
		vapScoreVO
			.setVapScoreType(rs.get(MapperConstants.VAP_SCORE_TYPE) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.VAP_SCORE_TYPE)
					.toString());
		vapScoreVO
			.setVapScoreName(rs.get(MapperConstants.VAP_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.VAP_SCORE_NAME)
					.toString());
		vapScoreVO
			.setVapScoreDesc(rs.get(MapperConstants.VAP_SCORE_DESC) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.VAP_SCORE_DESC)
					.toString());

		objects.add(vapScoreVO);

	    }
	}
	return objects;
    }

}
