package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.AbabdonedCartVO;

/**
 * The Class AbandonedCartVOMapper.
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
public class AbandonedCartVOMapper implements RowMapper<AbabdonedCartVO>,
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
    public AbabdonedCartVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	AbabdonedCartVO abandonedCartInfoVO = new AbabdonedCartVO();
	abandonedCartInfoVO.setOrderContact(rs
		.getString(MapperConstants.ABAND_ORDER_CONTACT));
	abandonedCartInfoVO.setSkuNumber(rs
		.getString(MapperConstants.ABAND_SKU));
	abandonedCartInfoVO.setItemDescription(rs
		.getString(MapperConstants.ABAND_SKU_NAME));
	abandonedCartInfoVO.setActDate(rs
		.getString(MapperConstants.ABAND_ACT_DATE));
	abandonedCartInfoVO.setAct(rs.getString(MapperConstants.ABAND_ACT));
	abandonedCartInfoVO.setCatentryId(rs
		.getString(MapperConstants.ABAND_CATENTRY_ID));
	return abandonedCartInfoVO;
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
	AbabdonedCartVO abandonedCartInfoVO = null;
	if (resultMap != null && !resultMap.isEmpty()) {
	    for (Map<String, Object> rs : resultMap) {
		if (objects == null) {
		    objects = new ArrayList<Object>();
		}
		abandonedCartInfoVO = new AbabdonedCartVO();
		abandonedCartInfoVO
			.setOrderContact(rs
				.get(MapperConstants.ABAND_ORDER_CONTACT) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_ORDER_CONTACT)
					.toString());
		abandonedCartInfoVO
			.setSkuNumber(rs.get(MapperConstants.ABAND_SKU) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_SKU).toString());
		abandonedCartInfoVO
			.setItemDescription(rs
				.get(MapperConstants.ABAND_SKU_NAME) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_SKU_NAME)
					.toString());
		abandonedCartInfoVO
			.setActDate(rs.get(MapperConstants.ABAND_ACT_DATE) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_ACT_DATE)
					.toString());
		abandonedCartInfoVO
			.setAct(rs.get(MapperConstants.ABAND_ACT) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_ACT).toString());
		abandonedCartInfoVO
			.setThumbnail(rs.get(MapperConstants.ABAND_THUMBNAIL) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_THUMBNAIL)
					.toString());
		abandonedCartInfoVO
			.setCatentryId(rs
				.get(MapperConstants.ABAND_CATENTRY_ID) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.ABAND_CATENTRY_ID)
					.toString());
		objects.add(abandonedCartInfoVO);
	    }
	}
	return objects;
    }
}