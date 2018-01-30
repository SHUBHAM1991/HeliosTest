package com.staples.dashboard.app.sdc.service;


import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionResponse;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;
import com.staples.dashboard.app.sdc.vo.CustomerOrdersResponse;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;
import com.staples.dashboard.app.sdc.vo.FiscalYearResponse;
import com.staples.dashboard.app.sdc.vo.SalesOrderResponse;
import com.staples.dashboard.app.sdc.vo.SalesShipmentResponse;
import com.staples.dashboard.app.sdc.vo.ShipmentOrdersResponse;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;

public interface CustomerSalesService {
	CustomerOrdersResponse getCustomerTransactions(OrderInfo orderInfo);
	SalesOrderResponse getCustomerOrderDetails(OrderInfo orderInfo);
	SalesShipmentResponse getCustomerShipmentDetails(String customer_id, String sbuName);
	ParentChildInfo getParentChildInfo(String customer_id, String sbuName);
	
	public CategoryDivisionResponse getCategories(String customerId);
	CustomerRepResponse getAllRepCustomers(String representive_id);
	CustomerRepResponse getAllSamCustomers(String representive_id, JQueryDataTableInputDTO dataTableInput);
	FiscalCategoryCalResponse getCcategoryFiscalMonthCals(String customer_id, String sbuName);
	CategoryPercentageResponse getFiscalCategoryPercentage(String customer_id, String sbuName);
	FiscalYearResponse listLastFiscalYears(String customer_id, String sbuName);
	ShipmentOrdersResponse listShipmentOrdersList(String customer_id, String sbuName, String location);
	HawkeyeDetailsVo getHawkeyeDetails(String customer_id);
	SuperUserDetailsVo getSuperUserDetailsSam(String customer_id);
	CustomerRepResponse getCustomerDetails(String representive_id, String custNum);
	String getCustomerLatestOrderDate(String customerId);
	
}
