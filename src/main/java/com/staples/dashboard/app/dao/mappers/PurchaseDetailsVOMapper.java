package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.PurchaseDetailsVO;


/**
 * The Class PurchaseDetailsVOMapper.
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
public class PurchaseDetailsVOMapper implements RowMapper<PurchaseDetailsVO> ,StaplesDashBoardRowMapper {
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public PurchaseDetailsVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PurchaseDetailsVO purchaseDetailsVO = new PurchaseDetailsVO();
		purchaseDetailsVO.setOrderNumber(rs.getString(MapperConstants.PURCHASEDETAIL_ORDER_NUMBER));
		purchaseDetailsVO.setTranDate(rs.getString(MapperConstants.PURCHASEDETAIL_ORDER_DATE));
		purchaseDetailsVO.setNetSpendAmount(rs.getString(MapperConstants.PURCHASEDETAIL_ORDER_AMOUNT));
		purchaseDetailsVO.setRewardNumber(rs.getString(MapperConstants.PURCHASEDETAIL_CUST_NUM));
		purchaseDetailsVO.setOrderLineCount(rs.getString(MapperConstants.PURCHASEDETAIL_ORDER_LINE_COUNT));
		return purchaseDetailsVO;
	}

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		PurchaseDetailsVO purchaseDetailsVO = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        purchaseDetailsVO = new PurchaseDetailsVO();
	        purchaseDetailsVO.setOrderNumber(rs.get(MapperConstants.PURCHASEDETAIL_ORDER_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_ORDER_NUMBER).toString());
			purchaseDetailsVO.setTranDate(rs.get(MapperConstants.PURCHASEDETAIL_ORDER_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_ORDER_DATE).toString());
			purchaseDetailsVO.setTranId(rs.get(MapperConstants.PURCHASEDETAIL_SOURCE_NUMBER) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_SOURCE_NUMBER).toString());
			purchaseDetailsVO.setNetSpendAmount(rs.get(MapperConstants.PURCHASEDETAIL_ORDER_AMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_ORDER_AMOUNT).toString());
			purchaseDetailsVO.setMinSpendAmount(rs.get(MapperConstants.PURCHASEDETAIL_MIN_ORDER_AMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_MIN_ORDER_AMOUNT).toString());
			purchaseDetailsVO.setOrderContact(rs.get(MapperConstants.ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.ORDER_CONTACT).toString());
			purchaseDetailsVO.setRewardNumber(rs.get(MapperConstants.PURCHASEDETAIL_CUST_NUM) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_CUST_NUM).toString());
			purchaseDetailsVO.setOrderLineCount(rs.get(MapperConstants.PURCHASEDETAIL_ORDER_LINE_COUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.PURCHASEDETAIL_ORDER_LINE_COUNT).toString());
			purchaseDetailsVO.setSavingCategory(rs.get(MapperConstants.SAVING_CATEGORY) == null ? new ArrayList<String>() : Arrays.asList(rs.get(MapperConstants.SAVING_CATEGORY).toString().split("#")));
			purchaseDetailsVO.setSavingAmount(rs.get(MapperConstants.SAVING_AMOUNT) == null ? new ArrayList<String>(): Arrays.asList(rs.get(MapperConstants.SAVING_AMOUNT).toString().split("#")));
	        objects.add(purchaseDetailsVO);
	        
	      }
	    }
	    return objects;
	}
}
