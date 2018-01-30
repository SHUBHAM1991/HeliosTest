package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.Shipment;

public class ShipmentLocationSumRowMapper implements RowMapper<Shipment> {

	public List<Shipment> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Shipment> ordersList = new ArrayList<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String shiptoAddress1 = getColumnString(rs, "SHIPTOADDRESS1");

				Shipment shipment = new Shipment();
				shipment.setShiptoAddress1(shiptoAddress1);
				shipment.setLocOrderSum(getColumnString(rs, "tranamountsum".toUpperCase()));
				String fiscalYr = getColumnString(rs, "FISCAL_TYPE");
				shipment.setFiscalYr(fiscalYr);
				if (fiscalYr != null && fiscalYr.equalsIgnoreCase("CURRENT")) {
					shipment.setCurrentFYS(getColumnString(rs, "TRANAMOUNTSUM"));
				} else {
					shipment.setPreviousFYS(getColumnString(rs, "TRANAMOUNTSUM"));
				}

				ordersList.add(shipment);
			}
		}
		return ordersList;
	}

	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName) == null ? null : rs.get(columnName).toString();
	}

	@Override
	public Shipment mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
