package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.RecommendationViewNotBoughtVO;

/**
 * The Class RecommendationViewNotBoughtMapper.
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
public class RecommendationViewNotBoughtMapper implements RowMapper<RecommendationViewNotBoughtVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public RecommendationViewNotBoughtVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecommendationViewNotBoughtVO custRecommendationVO = new RecommendationViewNotBoughtVO();
		custRecommendationVO.setCustNum(rs.getString(MapperConstants.RECOMVNB_CUST_NUM));
		custRecommendationVO.setSkuNumber(rs.getString(MapperConstants.RECOMVNB_SKU));
		custRecommendationVO.setItemDescription(rs.getString(MapperConstants.RECOMVNB_SKU_NAME));
		
		custRecommendationVO.setOrderContact(rs.getString(MapperConstants.RECOMVNB_ORDER_CONTACT));
		custRecommendationVO.setOrderDate(rs.getString(MapperConstants.RECOMVNB_ORDER_DATE));

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
		RecommendationViewNotBoughtVO custRecommendationVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        custRecommendationVO = new RecommendationViewNotBoughtVO();
	        custRecommendationVO.setCustNum(rs.get(MapperConstants.RECOMVNB_CUST_NUM) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_CUST_NUM).toString());
	        custRecommendationVO.setSkuNumber(rs.get(MapperConstants.RECOMVNB_SKU) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_SKU).toString());
			custRecommendationVO.setItemDescription(rs.get(MapperConstants.RECOMVNB_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_SKU_NAME).toString());
			custRecommendationVO.setOrderContact(rs.get(MapperConstants.RECOMVNB_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_ORDER_CONTACT).toString());
			custRecommendationVO.setThumbnail(rs.get(MapperConstants.RECOMVNB_THUMBNAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_THUMBNAIL).toString());
			custRecommendationVO.setOrderDate(rs.get(MapperConstants.RECOMVNB_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RECOMVNB_ORDER_DATE).toString());
			objects.add(custRecommendationVO);
	      }
	    }
	    return objects;
	}
}
