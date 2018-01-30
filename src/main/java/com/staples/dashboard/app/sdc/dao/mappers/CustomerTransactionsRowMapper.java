package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.OrderItem;
import com.staples.dashboard.dto.AccountUserDTO;

public class CustomerTransactionsRowMapper implements RowMapper<CustomerOrders> {

	public List<CustomerOrders> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<CustomerOrders> orders = new ArrayList<>();
		Map<String, CustomerOrders> orderMap=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
			
				String orderNumber = rs.get(MapperConstants.SOURCENUMBER)==null?"":rs.get(MapperConstants.SOURCENUMBER).toString();
				//CustomerOrders ct = orderMap.get(orderNumber);
				//if(ct==null){
					CustomerOrders ct = new CustomerOrders();
					ct.setMasterNumber(rs.get(MapperConstants.MASTERNUMBER)==null?"":rs.get(MapperConstants.MASTERNUMBER).toString());
					ct.setInsertDate(rs.get(MapperConstants.INSERTDATE)==null?"":rs.get(MapperConstants.INSERTDATE).toString());
					ct.setTransRewardNumber(rs.get(MapperConstants.TRANREWARDNUMBER)==null?"":rs.get(MapperConstants.TRANREWARDNUMBER).toString());
					ct.setRecordType(rs.get(MapperConstants.RECORDTYPE)==null?"":rs.get(MapperConstants.RECORDTYPE).toString());
					ct.setTranStatus(rs.get(MapperConstants.TRANSTATUS)==null?"":rs.get(MapperConstants.TRANSTATUS).toString());
					ct.setSourceSystemId(rs.get(MapperConstants.SOURCESYSTEMID)==null?"":rs.get(MapperConstants.SOURCESYSTEMID).toString());
					ct.setTranTotalAmount(rs.get(MapperConstants.TRANAMOUNT)==null?"":rs.get(MapperConstants.TRANAMOUNT).toString());
					ct.setOrderNumber(orderNumber);
					ct.setOrderTranDate(rs.get(MapperConstants.ORDERTRANDATE)==null?"":rs.get(MapperConstants.ORDERTRANDATE).toString());
					
					ct.setTransId(rs.get(MapperConstants.SALESTRANID)==null?"":rs.get(MapperConstants.SALESTRANID).toString());
					String firstName = rs.get(MapperConstants.BILLTOFIRSTNAME)==null?"":rs.get(MapperConstants.BILLTOFIRSTNAME).toString();
					String lastName = rs.get(MapperConstants.BILLTOLASTNAME)==null?"":rs.get(MapperConstants.BILLTOLASTNAME).toString();
					String shiptoFirstname = rs.get("SHIPTOFIRSTNAME")==null?"":rs.get("SHIPTOFIRSTNAME").toString();
					String shiptoLastname = rs.get("SHIPTOLASTNAME")==null?"":rs.get("SHIPTOLASTNAME").toString();
					ct.setShipToFirstname(shiptoFirstname);
					ct.setShipToLastname(shiptoLastname);
					
					String contactName="";
					if(!(firstName.equals("")) && !(lastName.equals("")) )
					{
						 contactName= firstName+" "+lastName;
						
					}
					else if((firstName.equals("")) && !(lastName.equals("")))
					{
							contactName=lastName;
					}
					else if(!(firstName.equals("")) && (lastName.equals(""))){
						contactName=firstName;
					}
					ct.setContactName(contactName);
					String masterTranId=rs.get(MapperConstants.MASTERSALESTRANID)==null?"":rs.get(MapperConstants.MASTERSALESTRANID).toString();
					String extendedPrice=rs.get("EXTENDEDPRICE")==null?"":rs.get("EXTENDEDPRICE").toString();
					ct.setMasterSaleTranId(masterTranId);
					ct.setExtendedPrice(extendedPrice);
					orderMap.put(orderNumber, ct);
				//}
					orders.add(ct);
				//addOrderItem(rs, ct);
				
			}
			//orders.add(ct);
		}
		return orders;
	}

	private void addOrderItem(Map<String, Object> rs, CustomerOrders ct) {
		List<OrderItem> orderItems = ct.getOrderItems();
		if(orderItems==null){
			orderItems=new ArrayList<>();
			ct.setOrderItems(orderItems);
		}
		OrderItem orderItem=new OrderItem();
		orderItem.setLineTotalAmount(rs.get(MapperConstants.LINETOTALAMOUNT)==null?null:rs.get(MapperConstants.LINETOTALAMOUNT).toString());
		orderItem.setProductClassNumber(rs.get(MapperConstants.PRODUCTCLASSNUMBER)==null?null:rs.get(MapperConstants.PRODUCTCLASSNUMBER).toString());
		orderItem.setProductDeptNumber(rs.get(MapperConstants.PRODUCTDEPTNUMBER)==null?null:rs.get(MapperConstants.PRODUCTDEPTNUMBER).toString());
		orderItem.setProductSKU(rs.get(MapperConstants.PRODUCTSKU)==null?null:rs.get(MapperConstants.PRODUCTSKU).toString());
		orderItem.setProductSubClassNumber(rs.get(MapperConstants.PRODUCTSUBCLASSNUMBER)==null?null:rs.get(MapperConstants.PRODUCTSUBCLASSNUMBER).toString());
		orderItem.setProductName(rs.get(MapperConstants.PRODUCTNAME)==null?null:rs.get(MapperConstants.PRODUCTNAME).toString());
		orderItem.setTransId(rs.get(MapperConstants.SALESTRANID)==null?null:rs.get(MapperConstants.SALESTRANID).toString());
		orderItem.setTransLineItemId(rs.get(MapperConstants.SALESTRANLINEITEMID)==null?null:rs.get(MapperConstants.SALESTRANLINEITEMID).toString());
		orderItem.setTransQuantity(rs.get(MapperConstants.TRANQUANTITY)==null?null:rs.get(MapperConstants.TRANQUANTITY).toString());
		
		orderItem.setContactName((rs.get(MapperConstants.BILLTOFIRSTNAME)==null?null:rs.get(MapperConstants.BILLTOFIRSTNAME).toString()
				)+" "+(rs.get(MapperConstants.BILLTOLASTNAME)==null?null:rs.get(MapperConstants.BILLTOLASTNAME).toString()));
		
		orderItems.add(orderItem);
	}

	@Override
	public CustomerOrders mapRow(ResultSet rs, int rowNum) throws SQLException {

		/*CustomerOrders ct = new CustomerOrders();
		ct.setMasterNumber(MapperConstants.MASTERNUMBER);
		ct.setInsertDate(MapperConstants.INSERTDATE);
		ct.setLineTotalAmount(MapperConstants.LINETOTALAMOUNT);
		ct.setProductClassNumber(MapperConstants.PRODUCTCLASSNUMBER);
		ct.setProductDeptNumber(MapperConstants.PRODUCTDEPTNUMBER);
		ct.setProductSKU(MapperConstants.PRODUCTSKU);
		ct.setProductSubClassNumber(MapperConstants.PRODUCTSUBCLASSNUMBER);
		ct.setRecordType(MapperConstants.RECORDTYPE);
		ct.setTranStatus(MapperConstants.TRANSTATUS);
		ct.setSourceSystemId(MapperConstants.SOURCESYSTEMID);
		ct.setTransId(MapperConstants.SALESTRANID);
		ct.setTransLineItemId(MapperConstants.SALESTRANLINEITEMID);
		ct.setTransQuantity(MapperConstants.TRANQUANTITY);
		ct.setTransQuantity(MapperConstants.SOURCENUMBER);
		ct.setTransQuantity(MapperConstants.BILLTOFIRSTNAME);
		ct.setTransQuantity(MapperConstants.BILLTOLASTNAME);
		ct.setTransRewardNumber(MapperConstants.TRANREWARDNUMBER);
		return ct;*/
		return null;
	}

}
