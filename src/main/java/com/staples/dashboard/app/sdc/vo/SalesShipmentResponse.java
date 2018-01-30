package com.staples.dashboard.app.sdc.vo;

import java.util.List;

public class SalesShipmentResponse {
	
	private List<Shipment> shipments;

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

}
