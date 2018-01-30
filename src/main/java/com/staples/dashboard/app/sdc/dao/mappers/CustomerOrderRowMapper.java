package com.staples.dashboard.app.sdc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.sdc.dao.bean.OrderDetailEntity;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.OrderItem;

public class CustomerOrderRowMapper implements RowMapper<CustomerOrder>{
	
	public List<CustomerOrder> mapRow(List<Map<String, Object>> resultMap) {
		// TODO Auto-generated method stub
		List<CustomerOrder> ordersList = new ArrayList<>();
		Map<String,CustomerOrder> orders=new HashMap<>();
		Map<String, CustomerOrder> orderMap=new HashMap<>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				
				String orderNo = getColumnString(rs, "ORDERNO");
				CustomerOrder customerOrder = orders.get(orderNo);
				if(customerOrder==null){
					customerOrder = createOrder(rs);
					customerOrder.setOrderNo(orderNo);
					orders.put(orderNo, customerOrder);
				}
				addOrderItem(rs, customerOrder);
				
				
			}
			ordersList.addAll(orders.values());
		}
		return ordersList;
	}

	private CustomerOrder createOrder(Map<String, Object> rs) {
		CustomerOrder orderDetails=new CustomerOrder();
		orderDetails.setBillToAddressLine1(rs.get(MapperConstants.BILLTOADDRESSLINE1)==null?null:rs.get(MapperConstants.BILLTOADDRESSLINE1).toString());
		orderDetails.setBillToAddressLine2(rs.get(MapperConstants.BILLTOADDRESSLINE2)==null?null:rs.get(MapperConstants.BILLTOADDRESSLINE2).toString());
		orderDetails.setBillToAddressLine3(rs.get(MapperConstants.BILLTOADDRESSLINE3)==null?null:rs.get(MapperConstants.BILLTOADDRESSLINE3).toString());
		orderDetails.setBillToCity(rs.get(MapperConstants.SETBILLTOCITY)==null?null:rs.get(MapperConstants.SETBILLTOCITY).toString());
		orderDetails.setTranTotalAmount(rs.get(MapperConstants.TRANAMOUNT)==null?null:rs.get(MapperConstants.TRANAMOUNT).toString());
	//	orderDetails.setBillToAddressLine1(getColumnString(rs, "billToAddressLine1"));
	//	orderDetails.setBillToAddressLine2(getColumnString(rs, "billToAddressLine2"));
	//	orderDetails.setBillToAddressLine3(getColumnString(rs, "billToAddressLine3"));
	//	orderDetails.setBillToCity(getColumnString(rs, "billToCity"));
		
		//getColumnString(rs, "orderStatusDesc")
		
		orderDetails.setBillToCompany(rs.get(MapperConstants.BILLTOCOMPANY)==null?null:rs.get(MapperConstants.BILLTOCOMPANY).toString());
		orderDetails.setBillToFirstName(rs.get(MapperConstants.BILLTOFIRSTNAME)==null?null:rs.get(MapperConstants.BILLTOFIRSTNAME).toString());
		//orderDetails.setBillToCompany(getColumnString(rs, "billToCompany"));
		//orderDetails.setBillToFirstName(getColumnString(rs, "billToFirstName"));
		
		//orderDetails.setBilltoId(getColumnString(rs, "billtoId"));
		
		orderDetails.setBillToLastName(rs.get(MapperConstants.BILLTOLASTNAME)==null?null:rs.get(MapperConstants.BILLTOLASTNAME).toString());
		orderDetails.setBillToState(rs.get(MapperConstants.BILLTOSTATE)==null?null:rs.get(MapperConstants.BILLTOSTATE).toString());
		orderDetails.setBillToZipCode(rs.get(MapperConstants.BILLTOZIPCODE)==null?null:rs.get(MapperConstants.BILLTOZIPCODE).toString());
		//orderDetails.setBillToLastName(getColumnString(rs, "billToLastName"));
		//orderDetails.setBillToState(getColumnString(rs, "billToState"));
		//orderDetails.setBillToZipCode(getColumnString(rs, "billToZipCode"));
		orderDetails.setCustomerNumber(rs.get(MapperConstants.CUSTOMERNUMBER)==null?null:rs.get(MapperConstants.CUSTOMERNUMBER).toString());
		orderDetails.setCustomerPONumber(rs.get(MapperConstants.CUSTOMERPONUMBER)==null?null:rs.get(MapperConstants.CUSTOMERPONUMBER).toString());
		//orderDetails.setCustomerNumber(getColumnString(rs, "customerNumber"));
		//orderDetails.setCustomerPONumber(getColumnString(rs, "customerPONumber"));
		
		orderDetails.setDivisionCode(rs.get(MapperConstants.DIVISIONCODE)==null?null:rs.get(MapperConstants.DIVISIONCODE).toString());
		orderDetails.setEnterpriseCode(rs.get(MapperConstants.ENTERPRISECODE)==null?null:rs.get(MapperConstants.ENTERPRISECODE).toString());
		//orderDetails.setDivisionCode(getColumnString(rs, "divisionCode"));
		//orderDetails.setEnterpriseCode(getColumnString(rs, "enterpriseCode"));
		
		orderDetails.setOrderChannel(rs.get(MapperConstants.ORDERCHANNEL)==null?null:rs.get(MapperConstants.ORDERCHANNEL).toString());
		//orderDetails.setOrderChannel(getColumnString(rs, "orderChannel"));
		
		//orderDetails.setOrderDate(orderDate);
		
		orderDetails.setOrderHeaderKey(rs.get(MapperConstants.ORDERHEADERKEY)==null?null:rs.get(MapperConstants.ORDERHEADERKEY).toString());
		orderDetails.setOrderMethod(rs.get(MapperConstants.ORDERMETHOD)==null?null:rs.get(MapperConstants.ORDERMETHOD).toString());
		//orderDetails.setOrderHeaderKey(getColumnString(rs, "orderHeaderKey"));
		//orderDetails.setOrderMethod(getColumnString(rs, "orderMethod"));
		
		//orderDetails.setOrderNo(getColumnString(rs, "orderNo"));
		
		orderDetails.setOrderStatusDesc(rs.get(MapperConstants.ORDERSTATUSDESC)==null?null:rs.get(MapperConstants.ORDERSTATUSDESC).toString());
		orderDetails.setRecordType(rs.get(MapperConstants.RECORDTYPE)==null?null:rs.get(MapperConstants.RECORDTYPE).toString());
		orderDetails.setSalesTranId(rs.get(MapperConstants.SALESTRANID)==null?null:rs.get(MapperConstants.SALESTRANID).toString());
		orderDetails.setShipToZipCode(rs.get(MapperConstants.SHIPTOZIPCODE)==null?null:rs.get(MapperConstants.SHIPTOZIPCODE).toString());
		orderDetails.setSubscriptionID(rs.get(MapperConstants.SUBSCRIPTIONID)==null?null:rs.get(MapperConstants.SUBSCRIPTIONID).toString());
		//orderDetails.setOrderStatusDesc(getColumnString(rs, "orderStatusDesc"));
		//orderDetails.setRecordType(getColumnString(rs, "recordType"));
		//orderDetails.setSalesTranId(getColumnString(rs, "salesTranId"));
		//orderDetails.setShipToZipCode(getColumnString(rs, "shipToZipCode"));
		//orderDetails.setSubscriptionID(getColumnString(rs, "subscriptionID"));
		return orderDetails;
	}
	
	private void addOrderItem(Map<String, Object> rs, CustomerOrder ct) {
		List<OrderItem> orderItems = ct.getOrderLineItems();
		if(orderItems==null){
			orderItems=new ArrayList<>();
			ct.setOrderLineItems(orderItems);
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
		
		orderItem.setDivision(getColumnString(rs, MapperConstants.DIVISION));
		
		orderItem.setProductClass_Id(getColumnString(rs, MapperConstants.CLASS_ID));
		
		
		
		String firstName = rs.get(MapperConstants.BILLTOFIRSTNAME)==null?null:rs.get(MapperConstants.BILLTOFIRSTNAME).toString();
		String lastName = rs.get(MapperConstants.BILLTOLASTNAME)==null?null:rs.get(MapperConstants.BILLTOLASTNAME).toString();
		
		String contactName="";
		if(firstName!=null && null!=lastName)
		{
			 contactName= firstName+" "+lastName;
			
		}
		else if(firstName==null&& lastName!=null)
		{
				contactName=lastName;
		}
		else if(firstName!=null&& lastName==null){
			contactName=firstName;
		}
		orderItem.setContactName(contactName);
		
		
		orderItem.setMasterSalesTranId(getColumnString(rs, MapperConstants.MASTERSALESTRANID));
		orderItem.setItemStatusDescription(getColumnString(rs, "DETAILSTATUSDESCRIPTION"));
		orderItem.setItemStatusDescriptionCode(getColumnString(rs, "DETAILSTATUSCODE"));
		orderItem.setExtendedPrice(getColumnString(rs, "EXTENDEDPRICE"));
		orderItem.setTranLineStatusId(getColumnString(rs, "TRANLINESTATUSID"));
		
		
		orderItems.add(orderItem);
	}

	private String getColumnString(Map<String, Object> rs,String columnName) {
		return rs.get(columnName)==null?null:rs.get(columnName).toString();
	}

	@Override
	public CustomerOrder mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}


}
