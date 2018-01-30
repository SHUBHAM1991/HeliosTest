package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.Shipment;

public class ShipmentRowMapper  implements RowMapper<Shipment>{
	
	public List<Shipment> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<Shipment> ordersList = new ArrayList<>();
		Map<String,Shipment> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String shiptoAddress1 = getColumnString(rs, "SHIPTOADDRESS1");
				Shipment shipment = orders.get(shiptoAddress1);
				
				if(shipment==null){
					shipment=new Shipment();
					shipment.setShiptoAddress1(shiptoAddress1);
					shipment.setMasterNumber(getColumnString(rs, "MASTERNUMBER"));
					shipment.setShipToFirstName(getColumnString(rs, "SHIPTOFIRSTNAME"));
					shipment.setLocOrderSum(getColumnString(rs, "tranamountsum".toUpperCase()));
					shipment.setShipToCity(getColumnString(rs, "SHIPTOCITY"));
					shipment.setShipToState(getColumnString(rs, "SHIPTOSTATE"));
					shipment.setShipToZip(getColumnString(rs, "SHIPTOZIP"));
					shipment.setTransRewardNumber(getColumnString(rs, "TRANSREWARDNUMBER"));
					
					String fiscalYr = getColumnString(rs, "FISCAL_TYPE");
					shipment.setFiscalYr(fiscalYr);
					if(fiscalYr!=null && fiscalYr.equalsIgnoreCase("CURRENT")){
						shipment.setCurrentFYS(getColumnString(rs, "TRANAMOUNTSUM"));
					}
					else {
						shipment.setPreviousFYS(getColumnString(rs, "TRANAMOUNTSUM"));
					}
					
					String cfyCount = getColumnString(rs, "CFYCOUNT");
					shipment.setCfy_order_count(cfyCount);
					String lfyCount = getColumnString(rs, "LFYCOUNT");
					shipment.setLfy_order_count(lfyCount);
					
					
					orders.put(shiptoAddress1, shipment);
				}
			}
			ordersList.addAll(orders.values());
		}
		return ordersList;
	}
	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}

	@Override
	public Shipment mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
