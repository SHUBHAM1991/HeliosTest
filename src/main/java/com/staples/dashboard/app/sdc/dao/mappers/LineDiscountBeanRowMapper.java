package com.staples.dashboard.app.sdc.dao.mappers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.LineDiscountBean;

public class LineDiscountBeanRowMapper implements RowMapper<LineDiscountBean>{
	
	public List<LineDiscountBean> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<LineDiscountBean> ordersList = new ArrayList<>();
		//Map<String,CustomerOrder> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				
				LineDiscountBean headerDiscount = new LineDiscountBean();
				headerDiscount.setOrderLineKey(getColumnString(rs,MapperConstants.ORDERLINEKEY));
				headerDiscount.setDiscountType(getColumnString(rs,MapperConstants.DISCOUNTTYPE));
				headerDiscount.setDiscountNo(getColumnString(rs,MapperConstants.DISCOUNTNO));
				headerDiscount.setSalesTranDetailId(getColumnString(rs,MapperConstants.SALESTRANDETAILID));
				headerDiscount.setDiscountLineItemId(getColumnString(rs,MapperConstants.DISCOUNTLINEITEMID));
				headerDiscount.setSalesTranId(getColumnString(rs,MapperConstants.SALESTRANID));
			
				//headerDiscount.setDiscountDesc(getColumnString(MapperConstants.DISCOUNTDESC)==null?null:rs.get(MapperConstants.DISCOUNTDESC).toString());
				
				headerDiscount.setDiscountLongDesc(getColumnString(rs,MapperConstants.DISCOUNTLONGDESC));
				headerDiscount.setDiscountPercentage(getColumnString(rs,MapperConstants.DISCOUNTPERCENTAGE));
				headerDiscount.setDiscountAmount(getColumnString(rs,MapperConstants.DISCOUNTAMOUNT));
//				headerDiscount.setOrderLineKey(getColumnString(rs, "orderLineKey".toUpperCase()));
//				headerDiscount.setDiscountType(getColumnString(rs, "discountType".toUpperCase()));
//				headerDiscount.setDiscountNo(getColumnString(rs, "discountNo".toUpperCase()));
//				headerDiscount.setSalesTranDetailId(getColumnString(rs, "salesTranDetailId".toUpperCase()));
//				headerDiscount.setDiscountLineItemId(getColumnString(rs, "discountLineItemId".toUpperCase()));
//				
//				//headerDiscount.setDiscountDesc(getColumnString(rs, "discountDesc").toUpperCase());
//				
//				headerDiscount.setDiscountLongDesc(getColumnString(rs, "discountLongDesc".toUpperCase()));
//				headerDiscount.setDiscountPercentage(getColumnString(rs, "discountPercentage".toUpperCase()));
//				headerDiscount.setDiscountAmount(getColumnString(rs, "discountAmount".toUpperCase()));
				ordersList.add(headerDiscount);
			}
		}
		return ordersList;
	}


	private String getColumnString(Map<String, Object> rs, String columnName) {
		// TODO Auto-generated method stub
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}

	@Override
	public LineDiscountBean mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
