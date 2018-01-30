package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.DotcomAcitivityVO;

/**
 * The Class DotcomActivityVOMapper.
 * 
 * @author KumBi002
 * @version 1.0 Revision history
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          <table>
 *          <tr>
 *          <td>Version</td>
 *          <td>Date</td>
 *          <td>Author</td>
 *          <td>Description</td>
 *          </tr>
 *          <tr>
 *          <td>1.0</td>
 *          <td>Dec 4, 2015</td>
 *          <td>KumBi002</td>
 *          <td>Initial Draft</td>
 *          </tr>
 *          </table>
 *          </p>
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 */
public class DotcomActivityVOMapper implements RowMapper<DotcomAcitivityVO>,
	StaplesDashBoardRowMapper {

    /**
     * Method implementation to get row map.
     * 
     * @param ResultSet
     *            rs
     * @param int rowNum
     * @return AbabdonedCartVO
     * @throws SQLException
     */
    @Override
    public DotcomAcitivityVO mapRow(ResultSet rs, int rowNum)
	    throws SQLException {
	DotcomAcitivityVO dotcomActivityVO = new DotcomAcitivityVO();
	dotcomActivityVO.setSkuNumber(rs.getString(MapperConstants.DOTCOM_SKU));
	dotcomActivityVO.setItemDescription(rs
		.getString(MapperConstants.DOTCOM_SKU_NAME));
	dotcomActivityVO.setOrderContact(rs
		.getString(MapperConstants.DOTCOM_ORDER_CONTACT));
	dotcomActivityVO.setActDate(rs
		.getString(MapperConstants.DOTCOM_ACT_DATE));
	dotcomActivityVO.setAct(rs.getString(MapperConstants.DOTCOM_ACT));
	return dotcomActivityVO;
    }

    /**
     * Method implementation to get row map.
     * 
     * @param List
     *            <Map<String,Object>> resultMap
     * @return List<Object>
     */
    @Override
    public List<Object> mapRow(List<Map<String, Object>> resultMap) {
	List<Object> objects = null;
	DotcomAcitivityVO dotcomActivityVO = null;
	if (resultMap != null && !resultMap.isEmpty()) {
	    for (Map<String, Object> rs : resultMap) {
		if (objects == null) {
		    objects = new ArrayList<Object>();
		}
		dotcomActivityVO = new DotcomAcitivityVO();
		dotcomActivityVO
			.setSkuNumber(rs.get(MapperConstants.DOTCOM_SKU) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_SKU).toString());
		dotcomActivityVO
			.setItemDescription(rs
				.get(MapperConstants.DOTCOM_SKU_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_SKU_NAME)
					.toString());
		dotcomActivityVO
			.setOrderContact(rs
				.get(MapperConstants.DOTCOM_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_ORDER_CONTACT)
					.toString());
		dotcomActivityVO
			.setActDate(rs.get(MapperConstants.DOTCOM_ACT_DATE) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_ACT_DATE)
					.toString());
		dotcomActivityVO
			.setAct(rs.get(MapperConstants.DOTCOM_ACT) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_ACT).toString());
		
		dotcomActivityVO
		.setPrice(rs.get(MapperConstants.DOTCOM_PRICE) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.DOTCOM_PRICE).toString());
		
		dotcomActivityVO
		.setQuantity(rs.get(MapperConstants.DOTCOM_QUANTITY) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.DOTCOM_QUANTITY).toString());
		
		dotcomActivityVO
			.setThumbnail(rs.get(MapperConstants.DOTCOM_THUMBNAIL) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.DOTCOM_THUMBNAIL)
					.toString());
		objects.add(dotcomActivityVO);
	    }
	}
	return objects;
    }

}
