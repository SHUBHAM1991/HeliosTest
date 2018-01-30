package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.utilities.StringUtils;
import com.staples.dashboard.app.vo.BoughtAlsoBoughtInfoVO;

/**
 * The Class BoughtAlsoBoughtVOMapper.
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
public class BoughtAlsoBoughtVOMapper implements RowMapper<BoughtAlsoBoughtInfoVO> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Object> objects = null;
		List<String> skuList=null;
		BoughtAlsoBoughtInfoVO boughtAlsoBoughtInfoVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        boughtAlsoBoughtInfoVO = new BoughtAlsoBoughtInfoVO();
	        skuList = StringUtils.splitStringToList(rs.get(MapperConstants.BAB_SKU_LIST) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_SKU_LIST).toString(), MapperConstants.COMMA_STRING);
	        boughtAlsoBoughtInfoVO.setCustomerNumber(rs.get(MapperConstants.BAB_CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_CUSTOMER_NUMBER).toString());
	        boughtAlsoBoughtInfoVO.setOrderContact(rs.get(MapperConstants.BAB_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_ORDER_CONTACT).toString());
	        boughtAlsoBoughtInfoVO.setSkuNumber(rs.get(MapperConstants.BAB_SKU_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_SKU_NUMBER).toString());
	        boughtAlsoBoughtInfoVO.setOrderDate(rs.get(MapperConstants.BAB_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_ORDER_DATE).toString());
	        boughtAlsoBoughtInfoVO.setItemDescription(rs.get(MapperConstants.BAB_SKU_NAME) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_SKU_NAME).toString());
	        boughtAlsoBoughtInfoVO.setThumbnail(rs.get(MapperConstants.BAB_THUMBNAIL) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_THUMBNAIL).toString());
	        boughtAlsoBoughtInfoVO.setCatenTryId(rs.get(MapperConstants.BAB_CATENTRYID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.BAB_CATENTRYID).toString());
	        boughtAlsoBoughtInfoVO.setRecSkuList(skuList);
	        objects.add(boughtAlsoBoughtInfoVO);
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
	public BoughtAlsoBoughtInfoVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		// TODO Auto-generated method stub
		List<String> skuList= StringUtils.splitStringToList(rs.getString(MapperConstants.BAB_SKU_LIST), MapperConstants.COMMA_STRING);
		BoughtAlsoBoughtInfoVO bAlsoBVo =new BoughtAlsoBoughtInfoVO();
		bAlsoBVo.setCustomerNumber(rs.getString(MapperConstants.BAB_CUSTOMER_NUMBER));
		bAlsoBVo.setOrderContact(rs.getString(MapperConstants.BAB_ORDER_CONTACT));
		bAlsoBVo.setOrderDate(rs.getString(MapperConstants.BAB_ORDER_DATE));
		bAlsoBVo.setSkuNumber(rs.getString(MapperConstants.BAB_SKU_NUMBER));
		bAlsoBVo.setItemDescription(rs.getString(MapperConstants.BAB_SKU_NAME));
		bAlsoBVo.setThumbnail(rs.getString(MapperConstants.BAB_THUMBNAIL));
		bAlsoBVo.setCatenTryId(rs.getString(MapperConstants.BAB_CATENTRYID));
		bAlsoBVo.setRecSkuList(skuList);
		
		return bAlsoBVo;
	}

}
