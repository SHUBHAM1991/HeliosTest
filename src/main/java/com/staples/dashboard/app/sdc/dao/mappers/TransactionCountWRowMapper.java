package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.OrderItem;

public class TransactionCountWRowMapper implements RowMapper<OrderItem>{
	
	public Map<String,List<OrderItem>> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		Map<String,List<OrderItem>> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				
				String tranId = getColumnString(rs, MapperConstants.SALESTRANID);
				String tranItemId = getColumnString(rs, MapperConstants.SALESTRANLINEITEMID);
				String sku = getColumnString(rs, MapperConstants.PRODUCTSKU);
				String productName = getColumnString(rs, MapperConstants.PRODUCTNAME);
				String qty = getColumnString(rs, MapperConstants.TRANQUANTITY);
				String productClassNumber = getColumnString(rs, MapperConstants.PRODUCTCLASSNUMBER);
				String extendedPrice = getColumnString(rs, "EXTENDEDPRICE");
				String tranLineStatusId = getColumnString(rs, "TRANLINESTATUSID");
				String lineAmount = getColumnString(rs, "LINETOTALAMOUNT");
				List<OrderItem> itemList = orders.get(tranId);
				if(itemList==null){
				    itemList=new ArrayList<>();
					orders.put(tranId, itemList);
				}
				OrderItem item=new OrderItem();
				item.setTransLineItemId(tranItemId);
				item.setProductSKU(sku);
				item.setProductName(productName);
				item.setTransQuantity(qty);
				item.setProductClassNumber(productClassNumber);
				item.setTransId(tranId);
				item.setExtendedPrice(extendedPrice);
				item.setTranLineStatusId(tranLineStatusId);
				item.setLineTotalAmount(lineAmount);
				itemList.add(item);
				
			}
		}
		return orders;
}
	
	private String getColumnString(Map<String, Object> rs,String columnName) {
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}


	@Override
	public OrderItem mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
