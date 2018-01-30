package com.staples.dashboard.app.sdc.vo;

import java.util.List;

public class ShipmentOrdersResponse {
	
	private String location;
	
	private List<CustomerOrders> orders;
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<CustomerOrders> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrders> orders) {
		this.orders = orders;
	}
	
	

}
