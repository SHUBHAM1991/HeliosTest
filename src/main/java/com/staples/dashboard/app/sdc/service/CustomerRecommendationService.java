package com.staples.dashboard.app.sdc.service;

import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.sdc.vo.LogRecommendationVO;
import com.staples.dashboard.app.sdc.vo.ProductVO;
import com.staples.dashboard.app.sdc.vo.RecommendationVO;

public interface CustomerRecommendationService {
	public Map<String, List<String>> getCustomerSkus(String customerId, String scheme);
	public Map<Integer, Map<String,List<ProductVO>>> getRecommendations(Map<String,List<String>> skus,String customerId, String loggedInUser);
	public Map<String, ProductVO> fetchSkuRecommendationFromTrillion(String sku);
	public ProductVO fetchProductDetail(String sku);
	public void logRecommendation(String input, List<ProductVO> logRecommendationVO);
	public List<RecommendationVO> getCustomerRecommendation(String customerId, String loggedUser, String level);
	
}
