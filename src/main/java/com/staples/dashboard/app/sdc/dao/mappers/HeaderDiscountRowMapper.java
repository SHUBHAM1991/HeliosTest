package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.HeaderDiscountsBean;

public class HeaderDiscountRowMapper implements RowMapper<HeaderDiscountsBean>{
	
	public List<HeaderDiscountsBean> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<HeaderDiscountsBean> ordersList = new ArrayList<>();
		//Map<String,CustomerOrder> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				
				HeaderDiscountsBean headerDiscount = new HeaderDiscountsBean();
				headerDiscount.setDiscountCategory(getColumnString(rs,MapperConstants.DISCOUNTCATEGORY));
				headerDiscount.setDiscountDesc(getColumnString(rs,MapperConstants.DISCOUNTDESC));
				headerDiscount.setDiscountDesc(getColumnString(rs,MapperConstants.SALESTRANDISCOUNTID));
				headerDiscount.setDiscountType(getColumnString(rs,MapperConstants.DISCOUNTTYPE));
				headerDiscount.setDiscountNo(getColumnString(rs,MapperConstants.DISCOUNTNO));
				headerDiscount.setDiscountPercentage(getColumnString(rs,MapperConstants.DISCOUNTPERCENTAGE));
				headerDiscount.setDiscountAmount(getColumnString(rs,MapperConstants.DISCOUNTAMOUNT));
//				headerDiscount.setDiscountCategory(getColumnString(rs, "discountCategory".toUpperCase()));
//				headerDiscount.setDiscountDesc(getColumnString(rs, "discountDesc".toUpperCase()));
//				headerDiscount.setDiscountDesc(getColumnString(rs, "salesTranDiscountId".toUpperCase()));
//				headerDiscount.setDiscountType(getColumnString(rs, "discountType").toUpperCase());
//				headerDiscount.setDiscountNo(getColumnString(rs, "discountNo".toUpperCase()));
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
	public HeaderDiscountsBean mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
