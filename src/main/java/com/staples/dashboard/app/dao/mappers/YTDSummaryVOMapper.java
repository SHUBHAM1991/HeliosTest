package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.YTDSummaryVO;

/**
 * The Class YTDSummaryVOMapper.
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
public class YTDSummaryVOMapper implements RowMapper<YTDSummaryVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public YTDSummaryVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		YTDSummaryVO ytdSummaryVO = new YTDSummaryVO();
		
		ytdSummaryVO.setTranMonth(rs.getString(MapperConstants.YTDSUM_MONTH));
		ytdSummaryVO.setCategoryId(rs.getString(MapperConstants.YTDSUM_CAT));
		ytdSummaryVO.setNetSpendAmount(rs.getString(MapperConstants.YTDSUM_TOTAL_SPND)); 
		
		return ytdSummaryVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		YTDSummaryVO ytdSummaryVO   = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        ytdSummaryVO = new YTDSummaryVO();
	        ytdSummaryVO.setTranMonth(rs.get(MapperConstants.YTDSUM_MONTH) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.YTDSUM_MONTH).toString());
			ytdSummaryVO.setCategoryId(rs.get(MapperConstants.YTDSUM_CAT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.YTDSUM_CAT).toString());
			ytdSummaryVO.setNetSpendAmount(rs.get(MapperConstants.YTDSUM_TOTAL_SPND) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.YTDSUM_TOTAL_SPND).toString()); 
			objects.add(ytdSummaryVO);
	      }
	    }
	    return objects;
	}
}