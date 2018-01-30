package com.staples.dashboard.app.sdc.dao.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.staples.dashboard.app.sdc.vo.ProductVO;

@Component
public class CustomerRecommendationRowMapper {
	
	@Value("${DOTCOM_SITE_SKU_URL}")
	private String siteUrl;
	
	@Value("${DOTCOM_SITE_IMG_URL}")
	private String imgUrl;
	
	public List<ProductVO> mapRow(List<Map<String, Object>> resultMap) {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				
				ProductVO product = new ProductVO();
				product.setSku(rs.get("SKU")==null?null:rs.get("SKU").toString());
				product.setSkuName(rs.get("SKU_NAME")==null?null:rs.get("SKU_NAME").toString());
				product.setImageurl(rs.get("IMAGEURL")==null?null:rs.get("IMAGEURL").toString());
				product.setUrl(siteUrl + product.getSku());
				product.setImageurl(imgUrl + product.getImageurl());
				productList.add(product);
				
			}
		}
		return productList;
	}

}
