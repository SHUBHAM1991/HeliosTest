package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.YTDInfoVO;



import org.springframework.jdbc.core.RowMapper;

/**
 * The Class YTDInfoMapper.
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
public class YTDInfoMapper implements RowMapper<YTDInfoVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public YTDInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		YTDInfoVO ytdInfoVO = new YTDInfoVO();
		ytdInfoVO.setYear(rs.getString(MapperConstants.INFOYTD_YEAR));
		ytdInfoVO.setYtdSales(rs.getString(MapperConstants.INFOYTD_YTD_SALES));
		return ytdInfoVO;
	}
	
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		YTDInfoVO ytdInfoVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        ytdInfoVO = new YTDInfoVO();
	        ytdInfoVO.setYear(rs.get(MapperConstants.INFOYTD_YEAR) 
	        		== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.INFOYTD_YEAR).toString() );
			ytdInfoVO.setYtdSales(rs.get(MapperConstants.INFOYTD_YTD_SALES) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.INFOYTD_YTD_SALES).toString());
	        objects.add(ytdInfoVO);
	        
	      }
	    }
	    return objects;
	}
}