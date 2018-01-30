package com.staples.dashboard.app.dao.mappers;
	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.CustRecommendationVO;

import org.springframework.jdbc.core.RowMapper;

/**
 * The Class CustRecommendationVOMapper.
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
public class CustRecommendationVOMapper implements RowMapper<CustRecommendationVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public CustRecommendationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustRecommendationVO custRecommendationVO = new CustRecommendationVO();
		custRecommendationVO.setSkuNumber(rs.getString(MapperConstants.CUST_RECOM_SKU));
		custRecommendationVO.setItemDescription(rs.getString(MapperConstants.CUST_RECOM_SKU_NAME));
		custRecommendationVO.setCatenTryId(rs.getString(MapperConstants.CUST_RECOM_CATENTRYID));
		custRecommendationVO.setOrderContact(rs.getString(MapperConstants.CUST_RECOM_ORDER_CONTACT));

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
		CustRecommendationVO custRecommendationVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        custRecommendationVO = new CustRecommendationVO();
	        custRecommendationVO.setSkuNumber(rs.get(MapperConstants.CUST_RECOM_SKU) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_RECOM_SKU).toString());
			custRecommendationVO.setItemDescription(rs.get(MapperConstants.CUST_RECOM_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_RECOM_SKU_NAME).toString());
			custRecommendationVO.setOrderContact(rs.get(MapperConstants.CUST_RECOM_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_RECOM_ORDER_CONTACT).toString());
			custRecommendationVO.setThumbnail(rs.get(MapperConstants.CUST_RECOM_THUMBNAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_RECOM_THUMBNAIL).toString());
			custRecommendationVO.setCatenTryId(rs.get(MapperConstants.CUST_RECOM_CATENTRYID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.CUST_RECOM_CATENTRYID).toString());
			objects.add(custRecommendationVO);
	      }
	    }
	    return objects;

	}

}