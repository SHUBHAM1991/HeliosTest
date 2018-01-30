package com.staples.dashboard.app.sdc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.OAuthConstants;
import com.staples.dashboard.app.utilities.OAuthUtility;
import com.staples.dashboard.app.dao.dbvo.CustProfileDAO;
import com.staples.dashboard.app.sdc.dao.CustomerRecommendationDAO;
import com.staples.dashboard.app.sdc.vo.LogRecommendationVO;
import com.staples.dashboard.app.sdc.vo.ProductVO;
import com.staples.dashboard.app.sdc.vo.RecommendationVO;
import com.staples.dashboard.app.sdc.vo.ArkeCISVO;
import com.staples.dashboard.app.sdc.vo.ErrorItem;
import com.staples.dashboard.app.sdc.vo.Item;
import com.staples.dashboard.app.sdc.vo.NephosResponseVO;
import com.staples.dashboard.app.sdc.vo.PriceLog;
import com.staples.dashboard.app.sdc.vo.SkuPricingInputVO;
import com.staples.dashboard.app.vo.UserInfo;
import com.staples.dashboard.app.vo.NephosConfig;
import com.staples.dashboard.app.vo.OAuth2Details;
import com.staples.dashboard.app.vo.UserFlagsNameValuePair;

@Service
public class CustomerRecommendationServiceImpl implements CustomerRecommendationService {

	private static final Logger LOGGER = Logger.getLogger(CustomerRecommendationServiceImpl.class);

	@Value("${RECOMM_TRILLION_SCHEME}")
	private String[] schemes;

	@Value("${RECOMM_TRILLION_USER_TKN}")
	private String strUserToken;

	@Value("${RECOMM_TRILLION_MERCHANT_ID}")
	private String strMerchantId;

	@Value("${RECOMM_TRILLION_SVC}")
	private String strURL;

	@Value("${RECOMM_SKU_COUNT}")
	private String recommCounts[];

	@Autowired
	private CustomerRecommendationDAO customerRecommendationDAO;

	private LogRecommendationVO logRecommendationVO;

	private Long customerNumber;

	private String inputType;

	private Long repId;

	private Long loggedUser;

	private String scheme;
	private Integer recommCount;

	@Value("${MAX_RECOMM_SKU}")
	private Integer threshHold;

	@Autowired
	private CustProfileDAO contractDao;
	
	@Value("${mmpivot.pricev4:false}")
	private boolean mmpivotPriceV4;
	
	@Value("${enablePriceLog:false}")
	private boolean enablePriceLog;

	public Map<String, List<String>> getCustomerSkus(String customerId, String scheme) {
		Map<String, List<String>> skuList = new LinkedHashMap<String, List<String>>();
		int size = 0;
		if (scheme.equalsIgnoreCase("HELIOS_CROSS_SELL")) {
			List<String> salesList = customerRecommendationDAO.getCustomerSkus1(customerId);
			if (salesList != null) {
				salesList = getSubList(salesList, size);
				skuList.put("Sale", salesList);
				size += salesList.size();
			}
			if (size < threshHold) {
				List<String> abondenedList = customerRecommendationDAO.getCustomerSkus2(customerId);
				if (abondenedList != null) {
					abondenedList = getSubList(abondenedList, size);
					skuList.put("Abondaned", abondenedList);
					size += abondenedList.size();
				}
			}
			if (size < threshHold) {
				List<String> viewList = customerRecommendationDAO.getCustomerSkus3(customerId);
				if (viewList != null) {
					viewList = getSubList(viewList, size);
					skuList.put("View", viewList);
					size += viewList.size();
				}
			}
		} else if (scheme.equalsIgnoreCase("HELIOS_UPSELL")) {
			List<String> viewList = customerRecommendationDAO.getCustomerSkus3(customerId);
			if (viewList != null) {
				viewList = getSubList(viewList, size);
				skuList.put("View", viewList);
				size += viewList.size();
			}
			if (size < threshHold) {
				List<String> abondenedList = customerRecommendationDAO.getCustomerSkus2(customerId);
				if (abondenedList != null) {
					abondenedList = getSubList(abondenedList, size);
					skuList.put("Abondaned", abondenedList);
					size += abondenedList.size();
				}
			}
			if (size < threshHold) {
				List<String> salesList = customerRecommendationDAO.getCustomerSkus1(customerId);
				if (salesList != null) {
					salesList = getSubList(salesList, size);
					skuList.put("Sale", salesList);
					size += salesList.size();
				}
			}
		}
		return skuList;
	}

	private List<String> getSubList(List<String> skuList, int currentSize) {
		Integer permissibleSize = threshHold - currentSize;
		Integer listSize = skuList.size();
		Integer allowedSize = (listSize > permissibleSize ? permissibleSize : listSize);

		skuList.subList(0, (allowedSize - 1));
		return skuList;
	}

	public Map<Integer, Map<String, List<ProductVO>>> getRecommendations(Map<String, List<String>> skus,
			String customerId, String loggedInUser) {
		Map<Integer, Map<String, List<ProductVO>>> productMapSequence = new LinkedHashMap<Integer, Map<String, List<ProductVO>>>();
		int seq = 0;
		for (Map.Entry<String, List<String>> entry : skus.entrySet()) {

			// {Abondaned=[139048, 51165, 513096], View=[562787]}
			Map<String, List<ProductVO>> productMap = new LinkedHashMap<String, List<ProductVO>>();
			List<ProductVO> products = new ArrayList<ProductVO>();
			for (String sku : entry.getValue()) {
				List<ProductVO> recomList = new ArrayList<ProductVO>();
				ProductVO product = fetchProductDetail(sku);

				if (product != null) {
					Map<String, ProductVO> map = fetchSkuRecommendationFromTrillion(sku);
					if (map != null && !map.isEmpty()) {
						List<ProductVO> list = new ArrayList<ProductVO>(map.values());
						logRecommendation(sku, list);
						list = fetchProductsDetail(list);
						list.add(product);// adding original sku in
											// recommendation list
						list = mapNephosResponseRecom(list, customerId, loggedInUser);
						for (ProductVO productVO : list) {
							if (productVO.getSku().equals(sku)) {
								product.setDotcomPrice(productVO.getDotcomPrice());// setting
																					// price
																					// for
																					// orginal
																					// sku
								product.setSpecialPrice(productVO.getSpecialPrice());
							} else {
								recomList.add(productVO);
							}

						}
						product.setRecommendations(recomList);
						products.add(product);

					}
				}
			}
			productMap.put(entry.getKey(), products);
			productMapSequence.put((new Integer(++seq)), productMap);
		}
		return productMapSequence;
	}

	public Map<Integer, Map<String, List<ProductVO>>> getRecommendations(String input, String customerId, String loggedInUser) {
		Map<Integer, Map<String, List<ProductVO>>> productMapSequence = new LinkedHashMap<Integer, Map<String, List<ProductVO>>>();
		Map<String, List<ProductVO>> productMap = new LinkedHashMap<String, List<ProductVO>>();
		List<ProductVO> products = new ArrayList<ProductVO>();
		ProductVO product = new ProductVO();

		Map<String, ProductVO> map = fetchSkuRecommendationFromTrillion(input);
		if (map != null && !map.isEmpty()) {
			List<ProductVO> list = new ArrayList<ProductVO>(map.values());
			logRecommendation(input, list);
			list = fetchProductsDetail(list);
			list = mapNephosResponseRecom(list, customerId, loggedInUser);
			product.setRecommendations(list);
			products.add(product);
			productMap.put("Re-Order", products);
			productMapSequence.put(1, productMap);
		}
		return productMapSequence;
	}

	/**
	 * This mthod maps and adds the Staples.com Price for SKU fetched from
	 * Nephos Service to the list of recommended SKUS.
	 * 
	 * @param recommendedVO
	 * @param custNum
	 * @return List of ProductVO
	 */
	private List<ProductVO> mapNephosResponseRecom(List<ProductVO> recommendedVO, String custNum, String loggedInUser) {
		PriceLog priceLog = new PriceLog();
		priceLog.setCustomerNumber(custNum);
		priceLog.setLoggedInUser(loggedInUser);
		List<ProductVO> returnList = new ArrayList<ProductVO>();
		Map<String, String> itemPriceMap = new HashMap<String, String>();
		Map<String, Map<String, String>> priceMap = null;
		String zip = getMasterAccountZipCode(custNum);
		
		String inputJson = null;
		
		if (mmpivotPriceV4) {
			NephosConfig configArkeCIS = contractDao.getNephosArkeCISServiceConfig();
			OAuth2Details oauthDetailsArkeCIS = OAuthUtility.createOAuthDetails(configArkeCIS);
			ArkeCISVO responseVoArkeCIS = getArkeCISResponse(oauthDetailsArkeCIS, custNum, priceLog);
			// Not O.K
			if (responseVoArkeCIS.getResponseCode() != 200 || responseVoArkeCIS == null || responseVoArkeCIS.getCustAttrbsServiceResponseCode() == null || (!responseVoArkeCIS.getCustAttrbsServiceResponseCode().equalsIgnoreCase("200"))) {
				for (ProductVO recom : recommendedVO) {
					if (responseVoArkeCIS.getResponseCode() != 200) {
						recom.setDotcomPrice(responseVoArkeCIS.getResponseMessage());
					} else {
						recom.setDotcomPrice("N/A");
						recom.setSpecialPrice("N/A");
					}
					returnList.add(recom);
				}
				if (enablePriceLog) {
					contractDao.insertPriceLog(priceLog);
				}
				return returnList;
			}

			inputJson = formInputJsonNephosFromArkeCIS(custNum, zip, recommendedVO, responseVoArkeCIS);
		} else {
			inputJson = formInputJsonNephos(custNum, zip, recommendedVO);
		}
		priceLog.setPriceRequest(inputJson);
		NephosConfig config = contractDao.getNephosServiceConfig();
		OAuth2Details oauthDetails = OAuthUtility.createOAuthDetails(config);
		NephosResponseVO responseVo = getProtectedResource(oauthDetails, inputJson, priceLog);
		if (enablePriceLog) {
			contractDao.insertPriceLog(priceLog);
		}

		priceMap = getPriceResultMap(responseVo);

		if (responseVo.getResponseCode() == 200) {

			if (recommendedVO != null && recommendedVO.size() > 0 && priceMap != null) {
				for (ProductVO recom : recommendedVO) {
					itemPriceMap = priceMap.get(recom.getSku());

					if (StringUtils.isNotBlank(itemPriceMap.get("errorCode"))) {
						recom.setDotcomPrice("N/A");
						recom.setSpecialPrice("N/A");
					} else {

						if (itemPriceMap.get("priceType").equalsIgnoreCase("WASPRICE")) {
							recom.setDotcomPrice(itemPriceMap.get("finalPrice"));
							recom.setSpecialPrice(itemPriceMap.get("finalPrice"));

						} else {//priceType=NOW
							
							if((itemPriceMap.get("finalPriceAfterRebate")).equals(itemPriceMap.get("finalPrice")))
							{
								recom.setDotcomPrice(itemPriceMap.get("basePrice"));
								recom.setSpecialPrice(itemPriceMap.get("finalPrice"));
							
							}
							else//REBATE case with no coupons/Tier  used
							{
								recom.setDotcomPrice(itemPriceMap.get("finalPriceAfterRebate"));
								recom.setSpecialPrice(itemPriceMap.get("finalPriceAfterRebate"));
							}
						}

					}

					returnList.add(recom);
				}

			} else {
				for (ProductVO recom : recommendedVO) {

					returnList.add(recom);
				}
			}
		} else {
			for (ProductVO recom : recommendedVO) {
				recom.setDotcomPrice(responseVo.getResponseMessage());
				returnList.add(recom);
			}
		}

		return returnList;

	}

	
	
	
	
	private String formInputJsonNephosFromArkeCIS(String custNum, String zip, List<ProductVO> recommendedVO, ArkeCISVO arkeCISVO) {
		SkuPricingInputVO input = new SkuPricingInputVO();
		List<Item> items = new ArrayList<Item>();
		String result = null;
		Item item = null;
		try {
			for (ProductVO recom : recommendedVO) {
				item = new Item();
				item.setItemId(recom.getSku());
				item.setQuantity(1);
				items.add(item);
			}
			UserInfo userInfo = new UserInfo();
			//userInfo.setRewardsNumber(custNum);
			input.setChannelId("WEB");
			input.setItems(items);
			input.setLocale("en-US");
			input.setTenantId("StaplesDotCom");
			input.setUserInfo(userInfo);
			input.setZipCode(zip);
			
			if (arkeCISVO!= null && arkeCISVO.getCustAttrbs() != null && arkeCISVO.getCustAttrbs().getOrchid() != null) {
				
				/*if (arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("customerNo", arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo()));
				}*/
				if (arkeCISVO.getCustAttrbs().getOrchid().getRewardsTierCd() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("rewardsTierCd", arkeCISVO.getCustAttrbs().getOrchid().getRewardsTierCd()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getSuffixCodes() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("suffixCodes", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getSuffixCodes(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getZoneId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("zoneId", arkeCISVO.getCustAttrbs().getOrchid().getZoneId()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getEmail() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("email", arkeCISVO.getCustAttrbs().getOrchid().getEmail()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getEmailHash() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("emailHash", arkeCISVO.getCustAttrbs().getOrchid().getEmailHash()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getStaplesUser() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesUser", arkeCISVO.getCustAttrbs().getOrchid().getStaplesUser()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getStaplesId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesId", arkeCISVO.getCustAttrbs().getOrchid().getStaplesId()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getTierSegment() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("tierSegment", arkeCISVO.getCustAttrbs().getOrchid().getTierSegment()));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken() != null && !arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken().isEmpty()) {					
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("runaUserToken", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getRunaUserToken(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getOrchid().getFingerprint() != null && !arkeCISVO.getCustAttrbs().getOrchid().getFingerprint().isEmpty()) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("fingerprint", StringUtils.join(arkeCISVO.getCustAttrbs().getOrchid().getFingerprint(), ",")));
				}
			}
			
			
			if (arkeCISVO!= null && arkeCISVO.getCustAttrbs() != null && arkeCISVO.getCustAttrbs().getOrchid() == null && arkeCISVO.getCustAttrbs().getUbas() != null) {
				
				/*if (arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("customerNo", arkeCISVO.getCustAttrbs().getOrchid().getCustomerNo()));
				}*/
				if (arkeCISVO.getCustAttrbs().getUbas().getRewardsTierCd() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("rewardsTierCd", arkeCISVO.getCustAttrbs().getUbas().getRewardsTierCd()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getSuffixCodes() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("suffixCodes", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getSuffixCodes(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getZoneId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("zoneId", arkeCISVO.getCustAttrbs().getUbas().getZoneId()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getEmail() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("email", arkeCISVO.getCustAttrbs().getUbas().getEmail()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getEmailHash() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("emailHash", arkeCISVO.getCustAttrbs().getUbas().getEmailHash()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getStaplesUser() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesUser", arkeCISVO.getCustAttrbs().getUbas().getStaplesUser()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getStaplesId() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("staplesId", arkeCISVO.getCustAttrbs().getUbas().getStaplesId()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getTierSegment() != null) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("tierSegment", arkeCISVO.getCustAttrbs().getUbas().getTierSegment()));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken() != null && !arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken().isEmpty()) {					
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("runaUserToken", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getRunaUserToken(), ",")));
				}
				if (arkeCISVO.getCustAttrbs().getUbas().getFingerprint() != null && !arkeCISVO.getCustAttrbs().getUbas().getFingerprint().isEmpty()) {
					userInfo.getUserFlags().add(new UserFlagsNameValuePair("fingerprint", StringUtils.join(arkeCISVO.getCustAttrbs().getUbas().getFingerprint(), ",")));
				}
			}
			
			ObjectMapper objMap = new ObjectMapper();

			result = objMap.writeValueAsString(input);
			LOGGER.info("JSON input to Nephos " + result);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method generates the required Json string which has to send with the
	 * request to Nephos Service.
	 * 
	 * @param custNum
	 * @param zip
	 * @param recommendedVO
	 * @return Json String
	 */
	private String formInputJsonNephos(String custNum, String zip, List<ProductVO> recommendedVO) {
		SkuPricingInputVO input = new SkuPricingInputVO();
		List<Item> items = new ArrayList<Item>();
		String result = null;
		Item item = null;
		try {
			for (ProductVO recom : recommendedVO) {
				item = new Item();
				item.setItemId(recom.getSku());
				item.setQuantity(1);
				items.add(item);
			}
			UserInfo userInfo = new UserInfo();
			userInfo.setRewardsNumber(custNum);
			input.setChannelId("WEB");
			input.setItems(items);
			input.setLocale("en-US");
			input.setTenantId("StaplesDotCom");
			input.setUserInfo(userInfo);
			input.setZipCode(zip);
			ObjectMapper objMap = new ObjectMapper();

			result = objMap.writeValueAsString(input);
			LOGGER.info("JSON input to Nephos " + result);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private String getSkus(List<ProductVO> list) {
		StringBuffer value = new StringBuffer();
		for (ProductVO product : list) {
			if (value.length() > 0) {
				value.append(",");
			}
			value.append(product.getSku());
		}
		return value.toString();
	}

	public Map<Integer, Map<String, List<ProductVO>>> getOnlyRecommendations(Map<String, List<String>> skus,
			String customerId, String loggedInUser) {
		Map<Integer, Map<String, List<ProductVO>>> productMapSequence = new LinkedHashMap<Integer, Map<String, List<ProductVO>>>();
		int seq = 0;
		for (Map.Entry<String, List<String>> entry : skus.entrySet()) {
			Map<String, List<ProductVO>> productMap = new LinkedHashMap<String, List<ProductVO>>();
			List<ProductVO> recommendations = new ArrayList<ProductVO>();
			for (String sku : entry.getValue()) {

				ProductVO product = fetchProductDetail(sku);

				if (product != null) {
					Map<String, ProductVO> map = fetchSkuRecommendationFromTrillion(sku);

					if (map != null && !map.isEmpty()) {
						List<ProductVO> list = new ArrayList<ProductVO>(map.values());
						list = fetchProductsDetail(list);
						list = mapNephosResponseRecom(list, customerId, loggedInUser);
						recommendations.addAll(list);
					}
				}
			}
			productMap.put(entry.getKey(), recommendations);
			productMapSequence.put((new Integer(++seq)), productMap);
		}
		return productMapSequence;
	}

	public Map<Integer, Map<String, List<ProductVO>>> getOnlyRecommendations(String input, String customerId, String loggedInUser) {
		Map<Integer, Map<String, List<ProductVO>>> productMapSequence = new LinkedHashMap<Integer, Map<String, List<ProductVO>>>();
		Map<String, List<ProductVO>> productMap = new LinkedHashMap<String, List<ProductVO>>();
		List<ProductVO> products = new ArrayList<ProductVO>();

		Map<String, ProductVO> map = fetchSkuRecommendationFromTrillion(input);
		if (map != null && !map.isEmpty()) {
			List<ProductVO> list = new ArrayList<ProductVO>(map.values());

			logRecommendation(input, list);
			list = fetchProductsDetail(list);
			list = mapNephosResponseRecom(list, customerId, loggedInUser);
			products.addAll(list);
			productMap.put("Re-Order", products);
			productMapSequence.put(1, productMap);

		}
		return productMapSequence;
	}

	public Map<String, ProductVO> fetchSkuRecommendationFromTrillion(String input) {
		String strResponse = new String();
		String strEmailInput = "";
		String strSkuInput = "";

		if (scheme.equalsIgnoreCase("HELIOS_REORDER")) {
			inputType = "email";
			strEmailInput = "\"email\": \"" + input + "\"";
		}
		if (!scheme.equalsIgnoreCase("HELIOS_REORDER")) {
			inputType = "sku_number";
			strSkuInput = "\"products-on-current-page\": [{\"sku\": \"" + input + "\"}]";
		}

		String strJsonInput = "{\"fiat\": {\"skip-filters?\": \"true\"},\"runa-user-token\": \"" + strUserToken
				+ "\",\"merchant-id\": \"" + strMerchantId + "\", \"client-request-id\": \"arbitrary values!\","
				+ "\"requested-schemes\": [{\"scheme\": \"" + scheme + "\", \"count\": " + recommCount
				+ ", \"width\": 0, \"height\": 0}],";
		String strRequest = strJsonInput + strEmailInput + strSkuInput + "}";
		// {"fiat": {"skip-filters?": "true"},"runa-user-token":
		// "7qarec23-0558-4b74-b6ef-f0526db1c4e7","merchant-id":
		// "1448ce8f-efc5-7b07-6982-1ff991bf967e", "client-request-id":
		// "arbitrary values!","requested-schemes": [{"scheme":
		// "HELIOS_REORDER", "count": 3, "width": 0, "height": 0}],
		LOGGER.info("JSON request to Recommendation Trillion Service  " + strRequest);
		Map<String, ProductVO> productList = null;
		JSONObject objJSON = null;
		String strJson = null;
		URL url;
		try {
			url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(strRequest.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((strResponse = br.readLine()) != null) {
				strJson = strResponse.substring(1, strResponse.length() - 1).toString();

			}
			LOGGER.info("response from RECOMM_TRILLION_SVC  " + strJson);
			objJSON = new JSONObject(strJson);
			productList = convertToProductList(objJSON);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productList;
	}

	private Map<String, ProductVO> convertToProductList(JSONObject objJSON) {
		Map<String, ProductVO> skuList = new LinkedHashMap<String, ProductVO>();

		for (int i = 0; i < objJSON.getJSONArray("spinoffs").length(); i++) {
			if (objJSON.getJSONArray("spinoffs") != null && !objJSON.getJSONArray("spinoffs").isNull(i)) {
				JSONObject localObjectSpinOff = (JSONObject) objJSON.getJSONArray("spinoffs").get(i);
				if (localObjectSpinOff.getJSONArray("products") != null
						&& !localObjectSpinOff.getJSONArray("products").isNull(0)) {
					JSONObject localObjectProducts = (JSONObject) localObjectSpinOff.getJSONArray("products").get(0);
					String sku = localObjectProducts.get("sku").toString();
					String strategy = localObjectProducts.get("strategy").toString();
					String file = localObjectProducts.get("file").toString();

					if (sku != null && !sku.isEmpty()) {
						ProductVO product = new ProductVO();
						product.setSku(sku);
						product.setStrategy(strategy);
						product.setFile(file);
						skuList.put(sku, product);

					}
				}
			}
		}
		return skuList;
	}

	private String getStrategyFileMapping(List<ProductVO> schemeSkuMap) {
		Map<String, String> strategyMapping = new LinkedHashMap<String, String>();
		for (ProductVO entry : schemeSkuMap) {
			String strategy = entry.getStrategy();
			String file = entry.getFile();
			String strategyJson = strategyMapping.get(strategy);
			strategyJson = (strategyJson != null ? strategyJson + "," : "");
			strategyMapping.put(strategy, strategyJson + file);
		}
		StringBuffer values = new StringBuffer();
		for (Map.Entry<String, String> entry : strategyMapping.entrySet()) {
			if (values.length() > 0) {
				values.append(",");
			}
			values.append("[" + entry.getKey() + ":" + entry.getValue() + "]");
		}

		return values.toString();
	}

	private String getStrategySkuMapping(List<ProductVO> schemeSkuMap) {
		Map<String, String> strategyMapping = new LinkedHashMap<String, String>();
		for (ProductVO entry : schemeSkuMap) {
			String strategy = entry.getStrategy();
			String sku = entry.getSku();
			String strategyJson = strategyMapping.get(strategy);
			strategyJson = (strategyJson != null ? strategyJson + "," : "");
			strategyMapping.put(strategy, strategyJson + sku);
		}

		StringBuffer values = new StringBuffer();
		for (Map.Entry<String, String> entry : strategyMapping.entrySet()) {
			if (values.length() > 0) {
				values.append(",");
			}
			values.append("[" + entry.getKey() + ":" + entry.getValue() + "]");
		}

		return values.toString();
	}

	public ProductVO fetchProductDetail(String sku) {
		ProductVO product = customerRecommendationDAO.fetchProductDetail(sku);
		return product;
	}

	private List<ProductVO> fetchProductsDetail(List<ProductVO> list) {
		List<ProductVO> productList = customerRecommendationDAO.fetchProductDetail(list);
		return productList;
	}

	public void logRecommendation(String input, List<ProductVO> list) {
		logRecommendationVO = new LogRecommendationVO();
		logRecommendationVO.setReqInput(input);
		logRecommendationVO.setInputType(inputType);
		logRecommendationVO.setSchemeQueried(scheme);
		logRecommendationVO.setCountRequested(recommCount);
		logRecommendationVO.setRecommendedSkus(getSkus(list));
		logRecommendationVO.setStrategy(getStrategySkuMapping(list));
		logRecommendationVO.setSparxFile(getStrategyFileMapping(list));
		logRecommendationVO.setRepId(repId);
		logRecommendationVO.setCustomerNumber(customerNumber);
		logRecommendationVO.setLoggedUser(loggedUser);

		customerRecommendationDAO.logRecommendation(logRecommendationVO);
	}

	@Override
	public List<RecommendationVO> getCustomerRecommendation(String customerId, String loggedUser1, String level) {
		List<RecommendationVO> recommList = new ArrayList<RecommendationVO>();
		customerNumber = Long.parseLong(customerId);
		loggedUser = Long.parseLong(loggedUser1);
		for (int i = 0; i < schemes.length; i++) {
			scheme = schemes[i];
			recommCount = Integer.parseInt(recommCounts[i]);

			Map<Integer, Map<String, List<ProductVO>>> products = null;
			if (scheme.equalsIgnoreCase("HELIOS_REORDER")) {
				products = getProductRecommendationFromReorder(customerId, level, loggedUser1);
			} else {
				Map<String, List<String>> skus = getCustomerSkus(customerId, scheme);
				if (skus != null && !skus.isEmpty()) {
					if (level.equalsIgnoreCase("nested")) {
						products = getRecommendations(skus, customerId, loggedUser1);
					} else if (level.equalsIgnoreCase("root")) {
						products = getOnlyRecommendations(skus, customerId,loggedUser1);
					}
				}
			}

			RecommendationVO recomm = new RecommendationVO();
			recomm.setScheme(scheme);
			recomm.setProducts(products);
			recommList.add(recomm);

		}

		return recommList;
	}

	private Map<Integer, Map<String, List<ProductVO>>> getProductRecommendationFromReorder(String customerId,
			String level, String loggedInUser) {
		Map<Integer, Map<String, List<ProductVO>>> products = null;
		Map<String, String> map = customerRecommendationDAO.getCustomerEmail(customerId);
		String assignedRep = map.get("REP");
		String email = map.get("EMAIL");
		if (assignedRep != null && email != null) {
			repId = Long.parseLong(assignedRep);

			if (email != null && email.length() > 0) {
				if (level.equalsIgnoreCase("nested")) {
					products = getRecommendations(email, customerId, loggedInUser);
				} else if (level.equalsIgnoreCase("root")) {
					products = getOnlyRecommendations(email, customerId, loggedInUser);
				}
			}
		}
		return products;
	}

	// @Override
	public String getMasterAccountZipCode(String customerNumber) {
		return contractDao.getMasterAccountZipCode(customerNumber);
	}

	/**
	 * Get Arke CIS Service Response
	 */
	private ArkeCISVO getArkeCISResponse(OAuth2Details oauthDetails, String customerNumber, PriceLog priceLog) {
		String resourceURL = oauthDetails.getResourceServerUrl();
		String arkeCISURL = resourceURL + "?tenantId=sdc&fingerprint=&customerNo=" + customerNumber + "&emailHash=&rewardsId=&overrideRunaToken=false";
		
		ArkeCISVO returnResult = null;
		HttpGet httpGet = new HttpGet(arkeCISURL);
		priceLog.setArkeCISRequest(arkeCISURL);
		httpGet.addHeader(OAuthConstants.AUTHORIZATION,
				OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		int code = -1;
		try {

			//post.setEntity(new StringEntity(inputJson));
			httpGet.setHeader("Accept", "application/json");
			//post.setHeader("Content-type", "application/json");
			response = client.execute(httpGet);
			code = response.getStatusLine().getStatusCode();
			if (code == 401 || code == 403) {
				// Access token is invalid or expired.Regenerating the access
				// token
				String accessToken = refreshAccessToken(oauthDetails);
				if (OAuthUtility.isValid(accessToken)) {
					// update the access token
					contractDao.refreshAccessToken(accessToken, MapperConstants.NEPHOS_ARKE_CIS);
					LOGGER.info("New access token: " + accessToken);
					oauthDetails.setAccessToken(accessToken);
					httpGet.removeHeaders(OAuthConstants.AUTHORIZATION);
					httpGet.addHeader(OAuthConstants.AUTHORIZATION,
							OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));
					httpGet.releaseConnection();
					response = client.execute(httpGet);
					LOGGER.info("REsponse from Arke CIS service" + response);
					code = response.getStatusLine().getStatusCode();
					if (code >= 400) {

						LOGGER.error(
								"Could not complete the request , authentication failed  http Status Code : " + code);
						returnResult = new ArkeCISVO();
						returnResult.setResponseCode(code);
						returnResult.setResponseMessage("Service NA");
						priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
						return returnResult;

					}

				} else {
					LOGGER.error(
							"Could not complete the request , Not a Valid Access Token , htp status Code  : " + code);
					returnResult = new ArkeCISVO();
					returnResult.setResponseCode(code);
					returnResult.setResponseMessage("Service NA");
					priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

					return returnResult;
				}

			} else if (code > 500) {
				LOGGER.error(
						"Could not complete the request , the service may be down or wrong resource URL,  http Status Code : "
								+ code);
				returnResult = new ArkeCISVO();
				returnResult.setResponseCode(code);
				returnResult.setResponseMessage("Service NA");
				priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
				return returnResult;
			}

			if (response != null) {
				returnResult = formArkeCISResponseVO(response, priceLog);
			}

		} catch (UnsupportedEncodingException e1) {

			LOGGER.error("Could not complete the request http status code : " + code, e1);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Nephos Service Down");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (Exception e) {
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new ArkeCISVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} finally {
			httpGet.releaseConnection();
		}
		return returnResult;
	}
	
	
	/**
	 * This Method fetches Staples.com pricing of SKUS for Retail Customers from
	 * Nephos Service . It consumes the Json returned by the Nephos Service .
	 * 
	 * @param oauthDetails
	 * @param inputJson
	 * @return NephosResponseVO
	 */
	private NephosResponseVO getProtectedResource(OAuth2Details oauthDetails, String inputJson, PriceLog priceLog) {
		String resourceURL = oauthDetails.getResourceServerUrl();
		priceLog.setPriceRequest(resourceURL + "\n" + inputJson);
		NephosResponseVO returnResult = null;
		HttpPost post = new HttpPost(resourceURL);
		post.addHeader(OAuthConstants.AUTHORIZATION,
				OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		int code = -1;
		try {

			post.setEntity(new StringEntity(inputJson));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			response = client.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 401 || code == 403) {
				// Access token is invalid or expired.Regenerating the access
				// token
				String accessToken = refreshAccessToken(oauthDetails);
				if (OAuthUtility.isValid(accessToken)) {
					// update the access token
					contractDao.refreshAccessToken(accessToken, mmpivotPriceV4 ? MapperConstants.NEPHOS_PRICE_V4 : MapperConstants.NEPHOS_PRICE_V2);
					LOGGER.info("New access token: " + accessToken);
					oauthDetails.setAccessToken(accessToken);
					post.removeHeaders(OAuthConstants.AUTHORIZATION);
					post.addHeader(OAuthConstants.AUTHORIZATION,
							OAuthUtility.getAuthorizationHeaderForAccessToken(oauthDetails.getAccessToken()));
					post.releaseConnection();
					response = client.execute(post);
					LOGGER.info("REsponse from Nephos service" + response);
					code = response.getStatusLine().getStatusCode();
					if (code >= 400) {

						LOGGER.error(
								"Could not complete the request , authentication failed  http Status Code : " + code);
						returnResult = new NephosResponseVO();
						returnResult.setResponseCode(code);
						returnResult.setResponseMessage("Service NA");
						priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
						return returnResult;

					}

				} else {
					LOGGER.error(
							"Could not complete the request , Not a Valid Access Token , htp status Code  : " + code);
					returnResult = new NephosResponseVO();
					returnResult.setResponseCode(code);
					returnResult.setResponseMessage("Service NA");
					priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());

					return returnResult;
				}

			} else if (code > 500) {
				LOGGER.error(
						"Could not complete the request , the service may be down or wrong resource URL,  http Status Code : "
								+ code);
				returnResult = new NephosResponseVO();
				returnResult.setResponseCode(code);
				returnResult.setResponseMessage("Service NA");
				priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
				return returnResult;
			}

			if (response != null) {
				returnResult = formNephosResponseVO(response, priceLog);
			}

		} catch (UnsupportedEncodingException e1) {

			LOGGER.error("Could not complete the request http status code : " + code, e1);
			returnResult = new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Nephos Service Down");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} catch (Exception e) {
			LOGGER.error("Could not complete the request http status code : " + code, e);
			returnResult = new NephosResponseVO();
			returnResult.setResponseCode(code);
			returnResult.setResponseMessage("Service NA");
			priceLog.setPriceResponse(returnResult.getResponseCode() + ": " + returnResult.getResponseMessage());
			return returnResult;
		} finally {
			post.releaseConnection();
		}
		return returnResult;
	}

	/**
	 * This method is used to fetch the access token from the Authorization URL.
	 * 
	 * @param oauthDetails
	 * @return Access Token of type String
	 */
	private String refreshAccessToken(OAuth2Details oauthDetails) {
		HttpPost post = new HttpPost(oauthDetails.getAuthenticationServerUrl());
		String clientId = oauthDetails.getClientId();
		String clientSecret = oauthDetails.getClientSecret();
		String scope = oauthDetails.getScope();

		List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
		parametersBody.add(new BasicNameValuePair(OAuthConstants.GRANT_TYPE, oauthDetails.getGrantType()));

		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_ID, clientId));

		parametersBody.add(new BasicNameValuePair(OAuthConstants.CLIENT_SECRET, clientSecret));

		if (OAuthUtility.isValid(scope)) {
			parametersBody.add(new BasicNameValuePair(OAuthConstants.SCOPE, scope));
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		String accessToken = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(parametersBody, String.valueOf(StandardCharsets.UTF_8)));

			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (code == OAuthConstants.HTTP_UNAUTHORIZED) {
				System.out.println("Authorization server expects Basic authentication");
				// Add Basic Authorization header
				post.addHeader(OAuthConstants.AUTHORIZATION, OAuthUtility
						.getBasicAuthorizationHeader(oauthDetails.getClientId(), oauthDetails.getClientSecret()));

				post.releaseConnection();
				response = client.execute(post);
				code = response.getStatusLine().getStatusCode();
				if (code == 401 || code == 403) {

					LOGGER.error("Could not retrieve access token for client: " + oauthDetails.getClientId()
							+ " The request to fetch Access token  ressponded http status code : " + code);

					return null;

				}

			}
			if (response != null) {
				Map<String, String> map = OAuthUtility.handleResponse(response);
				accessToken = map.get(OAuthConstants.ACCESS_TOKEN);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception generating Token", e);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception generating Token", e);

		}

		return accessToken;
	}

	/*
	 * private Map<String, String> getPriceResultMap(NephosResponseVO resp) {
	 * Map<String, String> resultMap = new HashMap<String, String>(); if (resp
	 * != null) { for (Item item : resp.getItem()) {
	 * 
	 * resultMap.put(item.getItemId(), item.getPriceInfo().get(0)
	 * .getFinalPrice().toString()); }
	 * 
	 * }
	 * 
	 * return resultMap; }
	 */

	private Map<String, Map<String, String>> getPriceResultMap(NephosResponseVO resp) {
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		if (resp != null) {
			if (resp.getItem().size() > 0) {
				for (Item item : resp.getItem()) {
                    Map<String, String> itemPriceMap = new HashMap<String, String>();
                    itemPriceMap.put("finalPrice", item.getPriceInfo().get(0).getFinalPrice().toString());
					itemPriceMap.put("basePrice", item.getPriceInfo().get(0).getBasePrice().toString());
					itemPriceMap.put("priceType", item.getPriceInfo().get(0).getPriceType().toString());
					itemPriceMap.put("finalPriceAfterRebate",
							item.getPriceInfo().get(0).getFinalPriceAfterRebate().toString());
					itemPriceMap.put("totalSavings", item.getPriceInfo().get(0).getTotalSavings().toString());
					itemPriceMap.put("totalSavingsAfterRebate",
							item.getPriceInfo().get(0).getTotalSavingsAfterRebate().toString());
					resultMap.put(item.getItemId(), itemPriceMap);

				}
			}

			if (resp.getErrorItem().size() > 0) {
				for (ErrorItem errItem : resp.getErrorItem()) {
                     Map<String, String> itemPriceMap = new HashMap<String, String>();
                     itemPriceMap.put("errorCode", errItem.getErrorCode());
					resultMap.put(errItem.getItemId(), itemPriceMap);

				}

			}
		}

		return resultMap;
	}
	
	
	public static ArkeCISVO formArkeCISResponseVO(HttpResponse response, PriceLog priceLog) {
		String result;

		ObjectMapper objMap = new ObjectMapper();
		ArkeCISVO obj = null;

		try {

			result = EntityUtils.toString(response.getEntity());
			priceLog.setArkeCISResponse(result);
			// result="{\"zipCode\": \"04103\", \"channelId\":
			// \"WEB\",\"userInfo\": {\"rewardsNumber\":
			// \"224895409\"},\"item\": [{\"itemId\": \"889862\",\"quantity\":
			// 1,\"unitOfMeasureComposite\": { \"unitOfMeasure\": \"Each\"
			// },\"priceInfo\": [{\"purchaseOptionType\":
			// \"ONE_TIME_PURCHASE\",\"priceType\": \"NOW\",\"unitBasePrice\":
			// 88.99,\"basePrice\": 88.99,\"finalPrice\":
			// 88.99,\"finalPriceAfterRebate\": 88.99,\"totalSavings\":
			// 0,\"totalSavingsPercentage\": 0,\"totalSavingsAfterRebate\":
			// 0,\"comparePrice\": 128.99,\"offerId\":
			// \"889862-5051-25-1\"}]}]}";
			obj = objMap.readValue(result, ArkeCISVO.class);
			obj.setResponseCode(200);
			obj.setResponseMessage("Success");
		} catch (ParseException e1) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonParseException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonMappingException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;

		} catch (IOException e) {
			obj = new ArkeCISVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setArkeCISResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		}
		return obj;

	}

	public static NephosResponseVO formNephosResponseVO(HttpResponse response, PriceLog priceLog) {
		String result;

		ObjectMapper objMap = new ObjectMapper();
		NephosResponseVO obj = null;

		try {

			result = EntityUtils.toString(response.getEntity());
			priceLog.setPriceResponse(result);
			// result="{\"zipCode\": \"04103\", \"channelId\":
			// \"WEB\",\"userInfo\": {\"rewardsNumber\":
			// \"224895409\"},\"item\": [{\"itemId\": \"889862\",\"quantity\":
			// 1,\"unitOfMeasureComposite\": { \"unitOfMeasure\": \"Each\"
			// },\"priceInfo\": [{\"purchaseOptionType\":
			// \"ONE_TIME_PURCHASE\",\"priceType\": \"NOW\",\"unitBasePrice\":
			// 88.99,\"basePrice\": 88.99,\"finalPrice\":
			// 88.99,\"finalPriceAfterRebate\": 88.99,\"totalSavings\":
			// 0,\"totalSavingsPercentage\": 0,\"totalSavingsAfterRebate\":
			// 0,\"comparePrice\": 128.99,\"offerId\":
			// \"889862-5051-25-1\"}]}]}";
			obj = objMap.readValue(result, NephosResponseVO.class);
			obj.setResponseCode(200);
			obj.setResponseMessage("Success");
		} catch (ParseException e1) {
			obj = new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonParseException e) {
			obj = new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonMappingException e) {
			obj = new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;

		} catch (IOException e) {
			obj = new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service NA");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		}
		return obj;

	}

}
