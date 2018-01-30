package com.staples.dashboard.app.sdc.dao;

import java.util.List;

import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.SalesShipmentResponse;
import com.staples.dashboard.app.sdc.vo.Shipment;

public interface CustomerShipmentDAO {

	List<Shipment> getCustomerShipmentDetails(String customer_id, String year);
	
	public List<CustomerOrders> getOrdersByShipmentLocation(String customerId,String location, String year);
	
	public List<Shipment> getShipmentLocOrderSum(String customerId,String fiscalYear);
	
	public List<Shipment> getShipmentOrderCount(String customerId) ;

}
