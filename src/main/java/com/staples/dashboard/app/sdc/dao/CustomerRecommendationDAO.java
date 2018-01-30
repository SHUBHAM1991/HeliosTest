package com.staples.dashboard.app.sdc.dao;

import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.OrderItem;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.ProductVO;
import com.staples.dashboard.app.sdc.vo.RecommendationVO;
import com.staples.dashboard.app.sdc.vo.ShipTosBean;
import com.staples.dashboard.app.sdc.dao.bean.OrderDetailEntity;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.CustomerParentInfo;
import com.staples.dashboard.app.sdc.vo.HeaderDiscountsBean;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;
import com.staples.dashboard.app.sdc.vo.LogRecommendationVO;

public interface CustomerRecommendationDAO {
	
	public List<String> getCustomerSkus1(String customerId);
	public List<String> getCustomerSkus2(String customerId);
	public List<String> getCustomerSkus3(String customerId);
	public ProductVO fetchProductDetail(String sku);
	public void logRecommendation(LogRecommendationVO logRecommendationVO);
	public List<ProductVO> fetchProductDetail(List<ProductVO> list);
	public Map<String, String> getCustomerEmail(String customerId);

}
