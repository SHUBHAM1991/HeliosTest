package com.staples.dashboard.app.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.PurchaseHeliosDetailsVO;

public class PurchaseHeliosDetailsVOMapper implements RowMapper<PurchaseHeliosDetailsVO> , StaplesDashBoardRowMapper{

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		PurchaseHeliosDetailsVO  vo  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        vo = new PurchaseHeliosDetailsVO();
	       
	        vo.setSkuNumber(rs.get(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_SKU_NUM)
							.toString());
			vo.setItemDescription(rs
					.get(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_SKU_NAME)
							.toString());
			vo.setTranDate(rs
					.get(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_ORDER_DATE)
							.toString());
			vo.setTranId(rs
					.get(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_ORDER_NUMBER)
							.toString());
			vo.setTotalQty(rs
					.get(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_ORDER_QTY)
							.toString());
			vo.setNetSpendAmount(rs
					.get(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_TOTAL_SPEND)
							.toString());

			vo.setItemDescription(rs
					.get(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.RWDDETAIL_ITEM_DESC)
							.toString());

			vo.setTotalPriceAmount(rs
					.get(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.RWDDETAIL_UNIT_SALE_PRICE)
							.toString());
			vo.setCategoryId(rs
					.get(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO
					: Float.valueOf(rs
							.get(
									MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD)
							.toString()));
	        objects.add(vo);
	        
	      }
	    }
	    return objects;	
	}

	
	@Override
	public PurchaseHeliosDetailsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PurchaseHeliosDetailsVO vo = new PurchaseHeliosDetailsVO();
		 vo.setSkuNumber(rs.getString(MapperConstants.RWDDETAIL_SKU_NUM) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_SKU_NUM)
							);
			vo.setItemDescription(rs
					.getString(MapperConstants.RWDDETAIL_SKU_NAME) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_SKU_NAME)
						);
			vo.setTranDate(rs
					.getString(MapperConstants.RWDDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_ORDER_DATE)
						);
			vo.setTranId(rs
					.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_ORDER_NUMBER)
				);
			vo.setTotalQty(rs
					.getString(MapperConstants.RWDDETAIL_ORDER_QTY) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_ORDER_QTY)
							);
			vo.setNetSpendAmount(rs
					.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_TOTAL_SPEND)
							);

			vo.setItemDescription(rs
					.getString(MapperConstants.RWDDETAIL_ITEM_DESC) == null ? MapperConstants.BLANK_STRING
					: rs.getString(MapperConstants.RWDDETAIL_ITEM_DESC)
					);

			vo.setTotalPriceAmount(rs
					.getString(MapperConstants.RWDDETAIL_UNIT_SALE_PRICE) == null ? MapperConstants.BLANK_STRING
					: rs.getString(
							MapperConstants.RWDDETAIL_UNIT_SALE_PRICE)
						);
			vo.setCategoryId(rs
					.getString(MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD) == null ? MapperConstants.FLOAT_ZERO
					: Float.valueOf(rs
							.getString(
									MapperConstants.RWDDETAIL_PRIMARY_PRODUCT_CAT_CD)
						));
		return vo;
	}

}

