package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.RecommOrderContactVO;

/**
 * The Class RecommOrderContactMapper.
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
public class RecommOrderContactMapper implements RowMapper<RecommOrderContactVO> , StaplesDashBoardRowMapper  {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Object> objects = null;
		
		RecommOrderContactVO orderContactVo = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        orderContactVo = new RecommOrderContactVO();
	        
	        
	        orderContactVo.setOrderContact(rs.get(MapperConstants.RECOMCONTACTS_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMCONTACTS_ORDER_CONTACT).toString());
	        
	        //orderContactVo.setOrderDate(rs.get(MapperConstants.RECOMCONTACTS_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMCONTACTS_ORDER_DATE).toString());
	        
	        objects.add(orderContactVo);
	      }}
		
		return objects;
	}

	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	@Override
	public RecommOrderContactVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub
		RecommOrderContactVO bAlsoBVo =new RecommOrderContactVO();
		
		bAlsoBVo.setOrderContact(rs.getString(MapperConstants.RECOMCONTACTS_ORDER_CONTACT));
		//bAlsoBVo.setOrderDate(rs.getString(MapperConstants.RECOMCONTACTS_ORDER_DATE));
		
		return bAlsoBVo;
	}

}
