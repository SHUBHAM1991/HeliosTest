package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.ShipToVO;

/**
 * The Class ShipToVOMapper.
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
public class ShipToVOMapper implements RowMapper<ShipToVO> , StaplesDashBoardRowMapper{

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		ShipToVO shipToVO  = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	      for (Map<String, Object> rs : resultMap) {
	        if (objects == null) {
	          objects = new ArrayList<Object>();
	        }
	        shipToVO = new ShipToVO();
	        shipToVO.setOrderNoLastYr(rs.get(MapperConstants.SHIPTO_LASTYRNO) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_LASTYRNO).toString());
	        shipToVO.setTotalSpendLastYr(rs.get(MapperConstants.SHIPTO_LASTYRAMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_LASTYRAMOUNT).toString());
	        shipToVO.setOrderNoCurYr(rs.get(MapperConstants.SHIPTO_CURRENTYRNO) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_CURRENTYRNO).toString());
	        shipToVO.setTotalSpendCurYr(rs.get(MapperConstants.SHIPTO_CURRENTYRAMOUNT) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_CURRENTYRAMOUNT).toString());
	        shipToVO.setCustomerAddress(rs.get(MapperConstants.SHIPTO_ADDRESS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_ADDRESS).toString());
	        shipToVO.setCustomerCity(rs.get(MapperConstants.SHIPTO_CITY) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_CITY).toString());
	        shipToVO.setCustomerState(rs.get(MapperConstants.SHIPTO_STATE) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_STATE).toString());
	        shipToVO.setCustomerZip(rs.get(MapperConstants.SHIPTO_ZIP) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTO_ZIP).toString());
	        shipToVO.setShipToStatus(rs.get(MapperConstants.SHIPTOSTATUS) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTOSTATUS).toString());
	        shipToVO.setShipToId(rs.get(MapperConstants.SHIPTOID) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.SHIPTOID).toString());
	        shipToVO.setTotalSpendLastYearByPeriod(rs.get(MapperConstants.LASTYRSALESPERIOD) == null ? MapperConstants.BLANK_STRING : rs.get(MapperConstants.LASTYRSALESPERIOD).toString());
			objects.add(shipToVO);
	        
	      }
	    }
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
	public ShipToVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShipToVO shipToVO = new ShipToVO();
		shipToVO.setOrderNoLastYr(rs.getString(MapperConstants.SHIPTO_LASTYRNO));
		shipToVO.setTotalSpendLastYr(rs.getString(MapperConstants.SHIPTO_LASTYRAMOUNT));
		shipToVO.setOrderNoCurYr(rs.getString(MapperConstants.SHIPTO_CURRENTYRNO));
		shipToVO.setTotalSpendCurYr(rs.getString(MapperConstants.SHIPTO_CURRENTYRAMOUNT));
		shipToVO.setCustomerAddress(rs.getString(MapperConstants.SHIPTO_ADDRESS));
		return shipToVO;
	}

}
