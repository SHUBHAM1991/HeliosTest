package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.LayoutVo;
import com.staples.dashboard.app.vo.SbaDiffDetailsVo;
import com.staples.dashboard.dto.AccountUserDTO;

public class LayoutVoMapper implements RowMapper<LayoutVo> , StaplesDashBoardRowMapper{

	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LayoutVo mapRow(ResultSet resultMap, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object mapRow(Map<String, Object> rs) {

		final LayoutVo layoutvo = new LayoutVo();
		layoutvo.setUsersGrid(rs.get(MapperConstants.SEC1) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC1).toString());
		layoutvo.setOrdersGrid(rs.get(MapperConstants.SEC2) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC2).toString());
		layoutvo.setShipToGrid(rs.get(MapperConstants.SEC3) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC3).toString());
		layoutvo.setAccountMgrGrid(rs.get(MapperConstants.SEC4) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC4).toString());
		layoutvo.setSaDiffGrid(rs.get(MapperConstants.SEC5) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC5).toString());
		layoutvo.setYtdSpendCatGrid(rs.get(MapperConstants.SEC6) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC6).toString());
		layoutvo.setYtdSpendMonthGrid(rs.get(MapperConstants.SEC7) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC7).toString());
		layoutvo.setMonthAnalysisGrid(rs.get(MapperConstants.SEC8) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC8).toString());
		layoutvo.setYearSummaryGrid(rs.get(MapperConstants.SEC9) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC9).toString());
		layoutvo.setCustProfileGrid(rs.get(MapperConstants.SEC10) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC10).toString());
		layoutvo.setReorderGrid(rs.get(MapperConstants.SEC11) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC11).toString());
		layoutvo.setBabGrid(rs.get(MapperConstants.SEC12) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC12).toString());
		layoutvo.setStoresNearByGrid(rs.get(MapperConstants.SEC13) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC13).toString());
		layoutvo.setCustNumCol(rs.get(MapperConstants.SEC14) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC14).toString());
		layoutvo.setCustTypeCol(rs.get(MapperConstants.SEC15) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC15).toString());
		layoutvo.setCompNameCol(rs.get(MapperConstants.SEC16) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC16).toString());
		layoutvo.setCallOrderCol(rs.get(MapperConstants.SEC17) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC17).toString());
		layoutvo.setCustSegCol(rs.get(MapperConstants.SEC18) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC18).toString());
		layoutvo.setCustSubSegCol(rs.get(MapperConstants.SEC19) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC19).toString());
		layoutvo.setQualScCol(rs.get(MapperConstants.SEC20) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC20).toString());
		layoutvo.setCallActionCol(rs.get(MapperConstants.SEC21) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC21).toString());
		layoutvo.setLastConDateCol(rs.get(MapperConstants.SEC22) == null ? MapperConstants.BLANK_STRING
				: rs.get(MapperConstants.SEC22).toString());
		return layoutvo;
	}
}
