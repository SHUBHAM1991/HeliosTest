package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.ReorderRecommendationVO;

/**
 * The Class ReorderRecommendationMapper.
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
public class ReorderRecommendationMapper implements RowMapper<ReorderRecommendationVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public ReorderRecommendationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReorderRecommendationVO custRecommendationVO = new ReorderRecommendationVO();
		custRecommendationVO.setCustNum(rs.getString(MapperConstants.REORDERRECOMM_CUST_NUM));
		custRecommendationVO.setSkuNumber(rs.getString(MapperConstants.REORDERRECOMM_SKU));
		custRecommendationVO.setItemDescription(rs.getString(MapperConstants.REORDERRECOMM_SKU_NAME));
		custRecommendationVO.setThumbnail(rs.getString(MapperConstants.REORDERRECOMM_THUMBNAIL));
		//custRecommendationVO.setOrderDate(rs.getString(MapperConstants.REORDERRECOMM_ORDER_DATE));
		custRecommendationVO.setCustNum(rs.getString(MapperConstants.REORDERRECOMM_CUST_NAME));
		custRecommendationVO.setCatenTryId(rs.getString(MapperConstants.REORDERRECOMM_CATENTRYID));
		return custRecommendationVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		ReorderRecommendationVO custRecommendationVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        custRecommendationVO = new ReorderRecommendationVO();
	        custRecommendationVO.setCustNum(rs.get(MapperConstants.REORDERRECOMM_CUST_NUM) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_CUST_NUM).toString());
	        custRecommendationVO.setSkuNumber(rs.get(MapperConstants.REORDERRECOMM_SKU) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_SKU).toString());
			custRecommendationVO.setItemDescription(rs.get(MapperConstants.REORDERRECOMM_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_SKU_NAME).toString());
			custRecommendationVO.setThumbnail(rs.get(MapperConstants.REORDERRECOMM_THUMBNAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_THUMBNAIL).toString());
			//custRecommendationVO.setOrderDate(rs.get(MapperConstants.REORDERRECOMM_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_ORDER_DATE).toString());
			custRecommendationVO.setCustName(rs.get(MapperConstants.REORDERRECOMM_CUST_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_CUST_NAME).toString());
			custRecommendationVO.setCatenTryId(rs.get(MapperConstants.REORDERRECOMM_CATENTRYID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.REORDERRECOMM_CATENTRYID).toString());
			objects.add(custRecommendationVO);
	      }
	    }
	    return objects;
	}
}
