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
import com.staples.dashboard.app.vo.QualScoreVO;

/**
 * @author jaisa001
 * 
 */
public class QualScoreMapper implements RowMapper<QualScoreVO>,
	StaplesDashBoardRowMapper {

    public QualScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	QualScoreVO qualScoreVO = new QualScoreVO();
	qualScoreVO.setQualScoreId(rs.getString(MapperConstants.QUAL_SCORE_ID));
	qualScoreVO.setQualScoreName(rs
		.getString(MapperConstants.QUAL_SCORE_NAME));
	qualScoreVO.setQualScoreDesc(rs
		.getString(MapperConstants.QUAL_SCORE_DESC));
	return qualScoreVO;
    }

    /**
     * Method implementation to get row map.
     * 
     * @param List
     *            <Map<String,Object>> rs
     * @return Object
     */
    public Object mapRow(Map<String, Object> rs) {

	final QualScoreVO qualScoreVO = new QualScoreVO();
	qualScoreVO
		.setQualScoreId(rs.get(MapperConstants.QUAL_SCORE_ID) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.QUAL_SCORE_ID).toString());
	qualScoreVO
		.setQualScoreName(rs.get(MapperConstants.QUAL_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.QUAL_SCORE_NAME).toString());
	qualScoreVO
		.setQualScoreDesc(rs.get(MapperConstants.QUAL_SCORE_DESC) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.QUAL_SCORE_DESC).toString());
	return qualScoreVO;
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
	QualScoreVO qualScoreVO = null;
	if (resultMap != null && !resultMap.isEmpty()) {
	    for (Map<String, Object> rs : resultMap) {
		if (objects == null) {
		    objects = new ArrayList<Object>();
		}
		qualScoreVO = new QualScoreVO();
		qualScoreVO
			.setQualScoreId(rs.get(MapperConstants.QUAL_SCORE_ID) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.QUAL_SCORE_ID)
					.toString());
		qualScoreVO
			.setQualScoreName(rs
				.get(MapperConstants.QUAL_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.QUAL_SCORE_NAME)
					.toString());
		qualScoreVO
			.setQualScoreDesc(rs
				.get(MapperConstants.QUAL_SCORE_DESC) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.QUAL_SCORE_DESC)
					.toString());

		objects.add(qualScoreVO);

	    }
	}
	return objects;
    }

}
