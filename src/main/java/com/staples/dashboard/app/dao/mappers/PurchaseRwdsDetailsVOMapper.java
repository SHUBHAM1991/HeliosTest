package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.PurchaseHeliosDetailsVO;


/**
 * The Class PurchaseRwdsDetailsVOMapper.
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
public class PurchaseRwdsDetailsVOMapper implements
		RowMapper<PurchaseHeliosDetailsVO> , StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public PurchaseHeliosDetailsVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PurchaseHeliosDetailsVO purchaseRwdsDetailsVO = new PurchaseHeliosDetailsVO();
		
		purchaseRwdsDetailsVO.setSkuNumber(rs.getString(MapperConstants.RWDDETAIL_SKU_NUM));
		purchaseRwdsDetailsVO.setItemDescription(rs.getString(MapperConstants.RWDDETAIL_SKU_NAME));
		purchaseRwdsDetailsVO.setTranDate(rs.getString(MapperConstants.RWDDETAIL_ORDER_DATE));
		purchaseRwdsDetailsVO.setTranId(rs.getString(MapperConstants.RWDDETAIL_ORDER_NUM));
		purchaseRwdsDetailsVO.setTotalQty(rs.getString(MapperConstants.RWDDETAIL_ORDER_QTY));
		purchaseRwdsDetailsVO.setNetSpendAmount(rs.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND));
		purchaseRwdsDetailsVO.setItemDescription(rs.getString(MapperConstants.RWDDETAIL_ITEM_DESC));
		purchaseRwdsDetailsVO.setCouponAmount(rs.getString(MapperConstants.RWDDETAIL_DISCOUNT_AMT));
		purchaseRwdsDetailsVO.setTotalPriceAmount(rs
				.getString(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE));
		purchaseRwdsDetailsVO.setCategoryId(rs.getFloat(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD));
		return purchaseRwdsDetailsVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		PurchaseHeliosDetailsVO purchaseRwdsDetailsVO  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        purchaseRwdsDetailsVO = new PurchaseHeliosDetailsVO();
	        purchaseRwdsDetailsVO.setSkuNumber(rs.get(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_SKU_NUM).toString());
			purchaseRwdsDetailsVO.setItemDescription(rs.get(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_SKU_NAME).toString());
			purchaseRwdsDetailsVO.setTranDate(rs.get(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_ORDER_DATE).toString());
			purchaseRwdsDetailsVO.setTranId(rs.get(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_ORDER_NUMBER).toString());
			purchaseRwdsDetailsVO.setTotalQty(rs.get(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_ORDER_QTY).toString());
			purchaseRwdsDetailsVO.setNetSpendAmount(rs.get(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_TOTAL_SPEND).toString());
			purchaseRwdsDetailsVO.setCouponAmount(rs.get(MapperConstants.RWDDETAIL_DISCOUNT_AMT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_DISCOUNT_AMT).toString());
			purchaseRwdsDetailsVO.setItemDescription(rs.get(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.RWDDETAIL_ITEM_DESC).toString());
			
			purchaseRwdsDetailsVO.setTotalPriceAmount(rs
					.get(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING : rs
							.get(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE).toString());
			purchaseRwdsDetailsVO.setCategoryId(rs.get(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO : Float.valueOf(rs.get(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD).toString()));
			objects.add(purchaseRwdsDetailsVO);
	        
	      }
	    }
	    return objects;
	}
}