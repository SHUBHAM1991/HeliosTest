package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SubplayInfoVo;

import org.springframework.jdbc.core.RowMapper;

/**
 * The Class SubPlayInfoMapper.
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
public class SubPlayInfoMapper implements RowMapper<SubplayInfoVo> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public SubplayInfoVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubplayInfoVo subPlayInfoVO = new SubplayInfoVo();
		subPlayInfoVO.setPlaySegType(rs.getString(MapperConstants.SUBPLAYINFO_SEGDESC));
		subPlayInfoVO.setPlaySegDesc(rs.getString(MapperConstants.SUBPLAYINFO_SEGTYPE));
		return subPlayInfoVO;
	}
	
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		SubplayInfoVo subPlayInfoVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        subPlayInfoVO = new SubplayInfoVo();
	        
	        subPlayInfoVO.setPlaySegDesc((rs.get(MapperConstants.SUBPLAYINFO_SEG_DESC) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUBPLAYINFO_SEG_DESC).toString()));
	        subPlayInfoVO.setPlaySegType((rs.get(MapperConstants.SUBPLAYINFO_SEG_TYPE) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUBPLAYINFO_SEG_TYPE).toString()));
	        subPlayInfoVO.setPlaySegId((rs.get(MapperConstants.SUBPLAYINFO_SEG_ID) 
					== null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SUBPLAYINFO_SEG_ID).toString()));
	        objects.add(subPlayInfoVO);
	        
	      }
	    }
	    return objects;
	}
}