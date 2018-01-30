package com.staples.dashboard.app.sdc.vo;

import java.util.List;

import com.staples.dashboard.app.xmlconfig.vo.CategoryVO;

public class CustomerOrdersResponse {
	
	public List<CustomerOrders> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(List<CustomerOrders> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public List<CategoryVO> getObjCategoriesList() {
		return objCategoriesList;
	}

	public void setObjCategoriesList(List<CategoryVO> objCategoriesList) {
		this.objCategoriesList = objCategoriesList;
	}

	private List<CustomerOrders> customerOrders;
	
	private List<CategoryVO> objCategoriesList;
	
	

}
