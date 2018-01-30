package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.OnlineRetailReorderRecommendationVO;


public class OnlineRetailReorderRecommendationMapper implements RowMapper<OnlineRetailReorderRecommendationVO> , StaplesDashBoardRowMapper{
	public OnlineRetailReorderRecommendationVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OnlineRetailReorderRecommendationVO custRecommendationVO = new OnlineRetailReorderRecommendationVO();
		
		custRecommendationVO.setSkuNumber(rs.getString(MapperConstants.OR_REORDERRECOMM_SKU));
		custRecommendationVO.setItemDescription(rs.getString(MapperConstants.OR_REORDERRECOMM_SKU_NAME));
		custRecommendationVO.setThumbnail(rs.getString(MapperConstants.OR_REORDERRECOMM_THUMBNAIL));
		custRecommendationVO.setSelectPrice(rs.getString(MapperConstants.OR_REORDERRECOMM_SELECTPRICE));
		custRecommendationVO.setPremiumPrice(rs.getString(MapperConstants.OR_REORDERRECOMM_PREMIUMPRICE));
		custRecommendationVO.setCatenTryId(rs.getString(MapperConstants.OR_REORDERRECOMM_CATENTRYID));
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
		OnlineRetailReorderRecommendationVO custRecommendationVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        custRecommendationVO = new OnlineRetailReorderRecommendationVO();
	        
//	        System.out.println("*************************************************************************************************");
//	        System.out.println(rs.get(MapperConstants.OR_REORDERRECOMM_SKU).toString());
//	        System.out.println(rs.get(MapperConstants.OR_REORDERRECOMM_SKU_NAME).toString());
//	        System.out.println(rs.get(MapperConstants.OR_REORDERRECOMM_PREMIUMPRICE).toString());
//	        System.out.println(rs.get(MapperConstants.OR_REORDERRECOMM_SELECTPRICE).toString());
//	        System.out.println(rs.get(MapperConstants.OR_REORDERRECOMM_THUMBNAIL).toString());
	        
	        custRecommendationVO.setSkuNumber(rs.get(MapperConstants.OR_REORDERRECOMM_SKU) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.OR_REORDERRECOMM_SKU).toString());
			custRecommendationVO.setItemDescription(rs.get(MapperConstants.OR_REORDERRECOMM_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.OR_REORDERRECOMM_SKU_NAME).toString());
			custRecommendationVO.setThumbnail(rs.get(MapperConstants.OR_REORDERRECOMM_THUMBNAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.OR_REORDERRECOMM_THUMBNAIL).toString());
		    custRecommendationVO.setSelectPrice(rs.get(MapperConstants.OR_REORDERRECOMM_SELECTPRICE)==null ? MapperConstants.BLANK_STRING:rs.get(MapperConstants.OR_REORDERRECOMM_SELECTPRICE).toString());
			custRecommendationVO.setPremiumPrice(rs.get(MapperConstants.OR_REORDERRECOMM_PREMIUMPRICE)==null ? MapperConstants.BLANK_STRING:rs.get(MapperConstants.OR_REORDERRECOMM_PREMIUMPRICE).toString());
			custRecommendationVO.setCatenTryId(rs.get(MapperConstants.OR_REORDERRECOMM_CATENTRYID)==null ? MapperConstants.BLANK_STRING:rs.get(MapperConstants.OR_REORDERRECOMM_CATENTRYID).toString());
			objects.add(custRecommendationVO);
	      }
	    }
	    return objects;
	}
	
}
