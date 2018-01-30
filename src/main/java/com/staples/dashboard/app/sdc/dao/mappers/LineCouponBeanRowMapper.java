package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;

public class LineCouponBeanRowMapper implements RowMapper<LineCouponBean>{
	
	
	public List<LineCouponBean> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<LineCouponBean> ordersList = new ArrayList<>();
		Map<String,LineCouponBean> orders=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				String orderNo = getColumnString(rs, MapperConstants.SALESTRANID);
				
				LineCouponBean headerDiscount = new LineCouponBean();
				headerDiscount.setCouponDescription(getColumnString(rs,MapperConstants.COUPONDESCRIPTION));
				headerDiscount.setSalesTranDetailId(getColumnString(rs,MapperConstants.SALESTRANDETAILID));
				headerDiscount.setCouponOfferCode(getColumnString(rs,MapperConstants.COUPONOFFERCODE));
				headerDiscount.setCouponTotalAmount(getColumnString(rs,MapperConstants.COUPONTOTALAMOUNT));
				headerDiscount.setCouponLineAmount(getColumnString(rs,MapperConstants.COUPONLINEAMOUNT));
				headerDiscount.setSalesTranId(orderNo);
				
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
	public LineCouponBean mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
