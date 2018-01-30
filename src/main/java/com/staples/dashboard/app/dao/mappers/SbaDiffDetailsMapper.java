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
import com.staples.dashboard.app.vo.SbaDiffDetailsVo;

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
public class SbaDiffDetailsMapper implements RowMapper<SbaDiffDetailsVo> , StaplesDashBoardRowMapper {

	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		SbaDiffDetailsVo sbaDiffDetailsVo = null;
	List<Object> objects = null;
	    if (resultMap != null && !resultMap.isEmpty()) {
	    	if (objects == null) {
	    		objects = new ArrayList<Object>();
		        }
	      for (Map<String, Object> rs : resultMap) {
	        sbaDiffDetailsVo = new SbaDiffDetailsVo();
	        sbaDiffDetailsVo.setBopisFlag(rs.get("BOPIS_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("BOPIS_FLAG").toString());
	        sbaDiffDetailsVo.setPrintsvsFlag(rs.get("PRINT_SERVICE_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("PRINT_SERVICE_FLAG").toString());
	        sbaDiffDetailsVo.setShoppingListFlag(rs.get("SHOPPING_LIST_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("SHOPPING_LIST_FLAG").toString());
	        sbaDiffDetailsVo.setRecomFlag(rs.get("RECOM_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("RECOM_FLAG").toString());
	        sbaDiffDetailsVo.setProductFlag(rs.get("PRODUCT_ALT_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("PRODUCT_ALT_FLAG").toString());
	        sbaDiffDetailsVo.setOrderMgmtFlag(rs.get("ORDER_MGMT_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("ORDER_MGMT_FLAG").toString());
	        sbaDiffDetailsVo.setReturnFlag(rs.get("RETURN_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("RETURN_FLAG").toString());
	        sbaDiffDetailsVo.setAdminFlag(rs.get("ADMIN_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("ADMIN_FLAG").toString());
	        sbaDiffDetailsVo.setMobileFlag(rs.get("MOBILE_VISITS_FLAG") == null ? MapperConstants.BLANK_STRING : rs.get("MOBILE_VISITS_FLAG").toString());
	        objects.add(sbaDiffDetailsVo);
	      }}else{
	    	  objects = new ArrayList<Object>();
	    	  sbaDiffDetailsVo = new SbaDiffDetailsVo();
	    	  sbaDiffDetailsVo.setBopisFlag("NOTFOUND");
	    	  sbaDiffDetailsVo.setPrintsvsFlag("NOTFOUND");
	    	  objects.add(sbaDiffDetailsVo);
	      }
		
		return objects;
	}

	@Override
	public SbaDiffDetailsVo mapRow(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
