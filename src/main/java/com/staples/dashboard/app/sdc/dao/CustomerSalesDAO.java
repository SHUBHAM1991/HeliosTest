package com.staples.dashboard.app.sdc.dao;

import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.OrderItem;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.ShipTosBean;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;
import com.staples.dashboard.app.sdc.dao.bean.OrderDetailEntity;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.CustomerParentInfo;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;
import com.staples.dashboard.app.sdc.vo.FiscalYearResponse;
import com.staples.dashboard.app.sdc.vo.HeaderDiscountsBean;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;

public interface CustomerSalesDAO {
	
   List<CustomerOrders> getCustomerTransactions(OrderInfo customer);

   String getSourceSystemId(String sbuName);
   
    List<CustomerOrder> getOrderDetails(String ssId, List<String> orderNos) ;

	List<ShipTosBean> getShipToInfo(String salesTranId);

	List<LineDiscountBean> getLineDiscounts(List<String> list);

	List<LineCouponBean> getLineCoupons(List<String> tranIds);

	List<CustomerParentInfo> getCustomerParentChildInfo(List<String> salesOrderNos);

	List<String> getMasterSalesTranSourceNumber(List<String> masterSalesTrans);

	List<String> getSourcesNumbers(String salesMasterTranId);

	ParentChildInfo getParentChildInfo(String customer_id, String sbuName);

	Map<String, List<OrderItem>> getTransactionItems(List<String> salesTransIds);

	List<CategoryDivisionVO> getCategories(String customerId);	
	
	List<CategoryDivisionVO> getCategoriesByType(String categoryType);	
	
	public CustomerRepResponse getAllRepCustomers(String repName);
	public CustomerRepResponse getAllSamCustomers(String repName, JQueryDataTableInputDTO dataTableInput);
	

	FiscalCategoryCalResponse getCcategoryFiscalMonthCals(String customer_id, String sbuName);

	CategoryPercentageResponse getFiscalCategoryPercentage(String customer_id, String sbuName);

	FiscalYearResponse listLatestFiscalYears(String customer_id);

	HawkeyeDetailsVo getHawkeyeDetails(String customer_id);
	
	SuperUserDetailsVo getSuperUserDetailsSam(String customer_id);
	
	public CustomerRepResponse getCustomerDetails(String repName, String custNum);

	String getCustomerLatestOrderDate(String customerId);
	
}
