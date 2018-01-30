package com.staples.dashboard.app.sdc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.sdc.dao.CustomerSalesDAO;
import com.staples.dashboard.app.sdc.dao.CustomerShipmentDAO;
import com.staples.dashboard.app.sdc.service.mappers.IOrderDetailMapper;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionResponse;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionVO;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrders;
import com.staples.dashboard.app.sdc.vo.CustomerOrdersResponse;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;
import com.staples.dashboard.app.sdc.vo.FiscalYearResponse;
import com.staples.dashboard.app.sdc.vo.ItemProductVO;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;
import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.OrderItem;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.SalesOrderResponse;
import com.staples.dashboard.app.sdc.vo.SalesShipmentResponse;
import com.staples.dashboard.app.sdc.vo.Shipment;
import com.staples.dashboard.app.sdc.vo.ShipmentOrdersResponse;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;

@Service
public class CustomerSalesServiceImpl implements CustomerSalesService {

	private static final String P = " M";

	private static final String C = " S";

	private static final String _02F = "%.02f";

	private static final String TONER = "TONER";

	private static final String INK_TONER = "Ink & Toner";

	private static final String OTHER_CATEGORY = "All Other Products";

	private static final String _0003 = "0003";

	@Autowired
	private CustomerSalesDAO customerSalesDAO;

	@Autowired
	private CustomerShipmentDAO customerShipmentDAO;
	@Autowired
	IOrderDetailMapper orderDetailMapper;

	@Override
	public CustomerOrdersResponse getCustomerTransactions(OrderInfo customer) {

		CustomerOrdersResponse response = new CustomerOrdersResponse();
		String sourceSystemId = customerSalesDAO.getSourceSystemId(customer.getSbuName().trim());
		customer.setSbuName(sourceSystemId);
		String parentCustomerId = customer.getCustomer_id();
		ParentChildInfo parentChildInfo = customerSalesDAO.getParentChildInfo(parentCustomerId, sourceSystemId);
		List<String> childs = parentChildInfo.getChilds();
		List<String> custNos = new ArrayList<>();
		if (null != childs) {
			custNos.addAll(childs);
		}
		custNos.add(parentCustomerId);
		customer.setCustomerIds(custNos);
		List<CustomerOrders> customerTransactions = customerSalesDAO.getCustomerTransactions(customer);

		String parentName = "";
		List<String> salesTransIds = new ArrayList<>();
		List<String> salesOrderNos = new ArrayList<>();
		List<String> masterSalesTrans = new ArrayList<>();
		Map<String, List<String>> originalVsReturnTransIds = new HashMap<>();
		Map<String, List<String>> originalTransIdVsOrderNos = new HashMap<>();
		Map<String, Float> originalVsReturnAmount = new HashMap<>();
		for (CustomerOrders customerOrders : customerTransactions) {
			if (parentCustomerId.equalsIgnoreCase(customerOrders.getMasterNumber())
					|| parentCustomerId.equalsIgnoreCase(customerOrders.getTransRewardNumber())) {
				if ((parentName == null || parentName.equals(""))
						&& sourceSystemId.equalsIgnoreCase(customerOrders.getSourceSystemId())) {
					parentName = customerOrders.getContactName();
				}
			}
			salesTransIds.add(customerOrders.getTransId());
			salesOrderNos.add(customerOrders.getOrderNumber());
			findReturnItemDetails(masterSalesTrans, originalVsReturnTransIds, originalTransIdVsOrderNos,
					originalVsReturnAmount, customerOrders);
		}
		List<String> masterSaleTranSourceNumbers = customerSalesDAO.getMasterSalesTranSourceNumber(masterSalesTrans);

		Map<String, List<OrderItem>> tranDetailCount = customerSalesDAO.getTransactionItems(salesTransIds);

		List<CategoryDivisionVO> categories = customerSalesDAO.getCategories(parentCustomerId);

		List<LineDiscountBean> lineDiscountEntityList = customerSalesDAO.getLineDiscounts(salesTransIds);
		List<LineCouponBean> lineCouponEntityList = customerSalesDAO.getLineCoupons(salesTransIds);

		// List<CustomerParentInfo> customerParentList =
		// customerSalesDAO.getCustomerParentChildInfo(salesOrderNos);

		populateOrderInfo(childs, customerTransactions, masterSalesTrans, originalVsReturnTransIds,
				masterSaleTranSourceNumbers, tranDetailCount, lineDiscountEntityList, lineCouponEntityList, categories,
				originalVsReturnAmount, customer, parentName, originalTransIdVsOrderNos);
		/*
		 * List<CategoryVO> populateCategories = populateCategories("BSE");
		 * response.setObjCategoriesList(populateCategories);
		 * 
		 * String categoryName = null; String catId=customer.getCatogeryId(); if
		 * (null == catId || catId.equalsIgnoreCase(Constants.ALL) || null ==
		 * populateCategories) { categoryName = Constants.ALL + "_Purchases"; }
		 * else { categoryName = getCategoryName(populateCategories, catId); }
		 */
		if (customer.getCatogeryId() != null) {
			List<CustomerOrders> orders = new ArrayList<>();
			for (CustomerOrders order : customerTransactions) {
				if (order.isSelectedCategory()) {
					orders.add(order);
					addOrderLvelItems(tranDetailCount, order);

				}
			}
			response.setCustomerOrders(orders);
		} else

		{
			for (CustomerOrders order : customerTransactions) {
				addOrderLvelItems(tranDetailCount, order);
			}
			response.setCustomerOrders(customerTransactions);
		}
		// response.setObjCategoriesList(populateCategories);

		return response;
	}

	private void addOrderLvelItems(Map<String, List<OrderItem>> tranDetailCount, CustomerOrders order) {
		List<OrderItem> orderItems = tranDetailCount.get(order.getTransId());
		List<ItemProductVO> items = new ArrayList<>();
		if (orderItems != null) {
			for (OrderItem orderItem : orderItems) {
				ItemProductVO item = new ItemProductVO();
				item.setProductName(orderItem.getProductName());
				item.setSku(orderItem.getProductSKU());
				item.setTranId(orderItem.getTransId());
				items.add(item);
			}
		}
		order.setOrderLevelItems(items);
	}

	private void findReturnItemDetails(List<String> masterSalesTrans,
			Map<String, List<String>> originalVsReturnTransIds, Map<String, List<String>> originalTransIdVsOrderNos,
			Map<String, Float> originalVsReturnAmount, CustomerOrders customerOrders) {
		if (_0003.equals(customerOrders.getRecordType())) {
			String masterSaleTranId = customerOrders.getMasterSaleTranId();
			if (masterSaleTranId != null && (false == masterSaleTranId.equals(""))
					&& (false == masterSaleTranId.equals("-1"))) {
				String orderNumber = customerOrders.getOrderNumber();
				List<String> returnOrderNos = originalTransIdVsOrderNos.get(masterSaleTranId);
				if (returnOrderNos == null) {
					returnOrderNos = new ArrayList<>();
					originalTransIdVsOrderNos.put(masterSaleTranId, returnOrderNos);

				}
				returnOrderNos.add(orderNumber);

				masterSalesTrans.add(masterSaleTranId);
				List<String> returnTranIds = originalVsReturnTransIds.get(masterSaleTranId);
				Float returnAmount = originalVsReturnAmount.get(masterSaleTranId);
				String tranTotalAmount = customerOrders.getTranTotalAmount();

				if (returnAmount == null) {
					returnAmount = new Float(0.0f);
					originalVsReturnAmount.put(masterSaleTranId, returnAmount);
				}
				if (null != tranTotalAmount && false == tranTotalAmount.trim().equals("")) {
					float tranTotalAmt = Float.parseFloat(tranTotalAmount);
					returnAmount += tranTotalAmt;
					originalVsReturnAmount.put(masterSaleTranId, returnAmount);
				}
				if (returnTranIds == null) {
					returnTranIds = new ArrayList<>();
					originalVsReturnTransIds.put(masterSaleTranId, returnTranIds);

				}
				returnTranIds.add(customerOrders.getTransId());
			}
		}
	}

	private void populateOrderInfo(List<String> childs, List<CustomerOrders> customerTransactions,
			List<String> masterSalesTrans, Map<String, List<String>> originalVsReturnTransIds,
			List<String> masterSaleTranSourceNumbers, Map<String, List<OrderItem>> tranDetailCount,
			List<LineDiscountBean> lineDiscountEntityList, List<LineCouponBean> lineCouponEntityList,
			List<CategoryDivisionVO> categories, Map<String, Float> originalVsReturnAmount, OrderInfo customer,
			String parentName, Map<String, List<String>> originalTransIdVsOrderNos) {
		Map<String, List<String>> categoryNameVsClassIds = new HashMap<>();
		String categoryId = customer.getCatogeryId();
		getCategoryMap(categories, categoryNameVsClassIds, categoryId);
		for (CustomerOrders order : customerTransactions) {
			order.setCustomerNo(customer.getCustomer_id());
			if (null != parentName && false == "".equals(parentName)) {
				order.setContactName(parentName);
			}
			//Float salesReturnCount = calculateSalesReturnSum(tranDetailCount, order);
			Float saleTotalAmount = calculateSalesReturnSum(tranDetailCount, order);
			List<OrderItem> origOrderItemList = tranDetailCount.get(order.getTransId());

			if (null != origOrderItemList) {
				for (OrderItem orgItem : origOrderItemList) {
					String productClassNumber = orgItem.getProductClassNumber();
					if (order.getSourceSystemId().equals("107")
							&& "RETURN".equalsIgnoreCase(orgItem.getTranLineStatusId())) {
						order.setOrderReturned("true");
						order.setReturnOrderNos(Arrays.asList(order.getOrderNumber()));
					}
					if (order.isSelectedCategory() == false) {
						if (categoryId != null && (false == categoryId.equals(""))) {
							List<String> list = categoryNameVsClassIds.get(categoryId);
							if (OTHER_CATEGORY.equalsIgnoreCase(categoryId)) {
								if (productClassNumber == null) {
									order.setSelectedCategory(true);
									break;
								}
								boolean noMatch = true;
								Set<Entry<String, List<String>>> entrySet = categoryNameVsClassIds.entrySet();
								for (Entry<String, List<String>> entry : entrySet) {
									List<String> value = entry.getValue();
									if (value.contains(productClassNumber)) {
										noMatch = false;
										break;
									}
								}
								if (noMatch) {
									order.setSelectedCategory(true);
									break;
								}

							}
							if (list.contains(productClassNumber)) {
								order.setSelectedCategory(true);
								break;
							}
						}
					}
				}
			}

			addTotalCount(lineDiscountEntityList, lineCouponEntityList, order);
			setTranTotalAmount(originalVsReturnAmount, order);

			/*if ("107".equals(order.getSourceSystemId())) {
				String tranTotalAmount = order.getTranTotalAmount();
				if (null != tranTotalAmount && false == "".equals(tranTotalAmount.trim())) {
					float orgAmt = Float.parseFloat(tranTotalAmount);
					Float balance = orgAmt + salesReturnCount;
					order.setTranTotalAmount(String.format(_02F, balance));
				}
			}*/

			if (masterSaleTranSourceNumbers.contains(order.getOrderNumber())) {
				order.setOrderReturned("true");
				List<String> returnOrders = originalTransIdVsOrderNos.get(order.getTransId());
				order.setReturnOrderNos(returnOrders);
			}
			setOrderIssuer(childs, customer.getCustomer_id(), order);
			setTotalOrderItemCount(masterSalesTrans, originalVsReturnTransIds, tranDetailCount, order);
			if ("107".equals(order.getSourceSystemId())) {
				setSaleOrderItemCount(tranDetailCount, order);
			}

		}
	}

	private void setTranTotalAmount(Map<String, Float> originalVsReturnAmount, CustomerOrders order) {
		Float returnedOrderAmt = originalVsReturnAmount.get(order.getTransId());
		if (null != returnedOrderAmt && "102".equals(order.getSourceSystemId())) {
			String tranTotalAmount = order.getTranTotalAmount();
			if (null != tranTotalAmount && false == "".equals(tranTotalAmount.trim())) {
				float orgAmt = Float.parseFloat(tranTotalAmount);
				Float balance = orgAmt - returnedOrderAmt;
				order.setTranTotalAmount(String.format(_02F, balance));
			}

		}
	}

	

	private void setSaleOrderItemCount(Map<String, List<OrderItem>> tranDetailCount, CustomerOrders order) {
		List<OrderItem> origOrderItemList = tranDetailCount.get(order.getTransId());
		int skuMatchSize = 0;
		if (null != origOrderItemList) {
			int originalQtySize = origOrderItemList.size();
			order.setOrderItemsCount(originalQtySize + "");
			Set<String> SKUs = new HashSet<>();
			for (OrderItem orgItem : origOrderItemList) {
				String sku = orgItem.getProductSKU();
				String tranLineStatusId = orgItem.getTranLineStatusId();
				if ("RETURN".equals(tranLineStatusId)) {
					SKUs.add(sku);
				}
			}
			int skuMatchCount = 0;
			for (OrderItem orgItem : origOrderItemList) {
				String sku = orgItem.getProductSKU();
				if (SKUs.contains(sku) && skuMatchCount != 2) {
					skuMatchSize++;
					skuMatchCount++;
				}
			}
			if (skuMatchSize > 0) {
				originalQtySize = originalQtySize - skuMatchSize;
				order.setOrderItemsCount(originalQtySize + "");
			}

		}

	}

	private Float calculateSalesReturnSum(Map<String, List<OrderItem>> tranDetailCount, CustomerOrders order) {
		/*Float salesReturnCount = 0f;
		if ("107".equals(order.getSourceSystemId())) {
			List<OrderItem> orgItemList = tranDetailCount.get(order.getTransId());
			if (null != orgItemList) {
				for (OrderItem orgItem : orgItemList) {
					if ("RETURN".equalsIgnoreCase(orgItem.getTranLineStatusId())) {
						String extendedPrice = orgItem.getExtendedPrice();
						if (null != extendedPrice && false == "".equals(extendedPrice.trim())) {
							float orgAmt = Float.parseFloat(extendedPrice);
							salesReturnCount = salesReturnCount + orgAmt;
						}
					}
				}
			}
		}*/
		
		Float saleTotalAmount = 0f;
		Float onlineTotalAmount = 0f;
		List<OrderItem> orgItemList = tranDetailCount.get(order.getTransId());
		if (null != orgItemList) {
			for (OrderItem orgItem : orgItemList) {
				if ("107".equals(order.getSourceSystemId())) 
				{
					if ("RETURN".equalsIgnoreCase(orgItem.getTranLineStatusId()) || "SALE".equalsIgnoreCase(orgItem.getTranLineStatusId())) {
						String extendedPrice = orgItem.getExtendedPrice();
						if (null != extendedPrice && false == "".equals(extendedPrice.trim())) {
							float orgAmt = Float.parseFloat(extendedPrice);
							saleTotalAmount = saleTotalAmount + orgAmt;
						}
					}
				}
				else
				{
					String lineAmount = orgItem.getLineTotalAmount();
					if (null != lineAmount && false == "".equals(lineAmount.trim())) {
						float orgAmt = Float.parseFloat(lineAmount);
						onlineTotalAmount = onlineTotalAmount + orgAmt;
					}
				}
			}
		}
		if ("107".equals(order.getSourceSystemId())) {
			order.setTranTotalAmount(String.format(_02F, saleTotalAmount));
		}
		else{
			order.setTranTotalAmount(String.format(_02F, onlineTotalAmount));
		}
		return saleTotalAmount;
	}

	private void getCategoryMap(List<CategoryDivisionVO> categories, Map<String, List<String>> categoryNameVsClassIds,
			String categoryId) {
		for (CategoryDivisionVO categoryVo : categories) {
			String classNo = categoryVo.getClassNo();
			String name = categoryVo.getName();
			if (name == null || name.equals("")) {
				name = OTHER_CATEGORY;
			}
			if (name.equalsIgnoreCase(TONER)) {
				name = INK_TONER;
			}
			List<String> list = categoryNameVsClassIds.get(name);
			if (list == null) {
				list = new ArrayList<>();
				categoryNameVsClassIds.put(name, list);
			}
			list.add(classNo);

		}
	}

	private void setTotalOrderItemCount(List<String> masterSalesTrans,
			Map<String, List<String>> originalVsReturnTransIds, Map<String, List<OrderItem>> tranDetailCount,
			CustomerOrders order) {
		List<OrderItem> origOrderItemList = tranDetailCount.get(order.getTransId());
		if (null != origOrderItemList) {
			int originalQtySize = origOrderItemList.size();
			order.setOrderItemsCount(originalQtySize + "");

			if (masterSalesTrans.contains(order.getTransId())) {
				List<String> returnedTranIds = originalVsReturnTransIds.get(order.getTransId());
				if (returnedTranIds != null) {
					originalQtySize = findQtySize(tranDetailCount, origOrderItemList, originalQtySize, returnedTranIds);
					order.setOrderItemsCount(originalQtySize + "");
				}
			}
		}
	}

	private int findQtySize(Map<String, List<OrderItem>> tranDetailCount, List<OrderItem> origOrderItemList,
			int originalQtySize, List<String> returnedTranIds) {
		for (OrderItem orgItem : origOrderItemList) {
			String originalItemQty = orgItem.getTransQuantity();
			int returnItemQty = 0;
			boolean skuMatch = false;

			for (String returnTrannId : returnedTranIds) {
				List<OrderItem> returnOrderItems = tranDetailCount.get(returnTrannId);
				if (null != returnOrderItems) {
					for (OrderItem returnItem : returnOrderItems) {
						if (orgItem.getProductSKU().equals(returnItem.getProductSKU())) {
							returnItemQty += Integer.parseInt(returnItem.getTransQuantity());
							skuMatch = true;
						}
					}
				}

			}
			if (skuMatch) {
				int orgQty = Integer.parseInt(originalItemQty);
				if (orgQty - returnItemQty == 0) {
					originalQtySize--;
				}
			}
		}
		return originalQtySize;
	}

	private void addTotalCount(List<LineDiscountBean> lineDiscountEntityList, List<LineCouponBean> lineCouponEntityList,
			CustomerOrders order) {
		Float totalCouponAmount = 0f;
		Float totalDiscountAmount = 0f;
		for (LineDiscountBean lineDiscountItem : lineDiscountEntityList) {
			if (order.getTransId().equalsIgnoreCase(lineDiscountItem.getSalesTranId())) {
				if (lineDiscountItem.getDiscountAmount() != null
						&& (false == lineDiscountItem.getDiscountAmount().equals(""))) {
					totalDiscountAmount += Float.parseFloat(lineDiscountItem.getDiscountAmount().trim());
				}
			}
		}

		for (LineCouponBean lineItem : lineCouponEntityList) {
			if (order.getTransId().equalsIgnoreCase(lineItem.getSalesTranId())) {
				if (lineItem.getCouponLineAmount() != null && (false == lineItem.getCouponLineAmount().equals(""))) {
					totalCouponAmount += Float.parseFloat(lineItem.getCouponLineAmount().trim());
				}
			}
		}
		order.setTotalCoponAmount(totalCouponAmount.toString());
		order.setTotalDiscountAmount(totalDiscountAmount.toString());
	}

	@Override
	public SalesOrderResponse getCustomerOrderDetails(OrderInfo orderInfo) {

		SalesOrderResponse salesOrderResponse = new SalesOrderResponse();
		String ssId = customerSalesDAO.getSourceSystemId(orderInfo.getSbuName());
		List<CustomerOrder> orderDetails = customerSalesDAO.getOrderDetails(ssId,
				Arrays.asList(orderInfo.getOrderNo()));
		// orderDetailMapper.convertSalesHeader(order, orderDetails);
		if(orderDetails==null || orderDetails.size()==0){
			return salesOrderResponse;
		}
		CustomerOrder order = orderDetails.get(0);
		if (order == null) {
			return salesOrderResponse;
		}
		salesOrderResponse.setOrderDetails(order);

		String salesMasterTranId = order.getSalesTranId();

		List<String> sourcesNumbers = customerSalesDAO.getSourcesNumbers(salesMasterTranId);

		if (null != sourcesNumbers && sourcesNumbers.size() > 0) {
			List<CustomerOrder> returnOrderDetails = customerSalesDAO.getOrderDetails(ssId, sourcesNumbers);

			List<OrderItem> orderLineItems = order.getOrderLineItems();
			for (CustomerOrder customerOrder : returnOrderDetails) {
				orderLineItems.addAll(customerOrder.getOrderLineItems());
			}
		}

		List<LineDiscountBean> lineDiscountEntityList = customerSalesDAO
				.getLineDiscounts(Arrays.asList(order.getSalesTranId()));

		List<LineCouponBean> lineCouponEntityList = customerSalesDAO
				.getLineCoupons(Arrays.asList(order.getSalesTranId()));
		// order.setLineDiscounts(lineDiscountEntityList);

		Float totalCouponAmount = 0f;
		Float totalDiscountAmount = 0f;
		for (LineDiscountBean lineDiscountItem : lineDiscountEntityList) {
			if (order.getSalesTranId().equalsIgnoreCase(lineDiscountItem.getSalesTranId())) {
				if (lineDiscountItem.getDiscountAmount() != null
						&& (false == lineDiscountItem.getDiscountAmount().equals(""))) {
					totalDiscountAmount += Float.parseFloat(lineDiscountItem.getDiscountAmount().trim());
				}
			}
		}
		for (LineCouponBean lineItem : lineCouponEntityList) {
			if (order.getSalesTranId().equalsIgnoreCase(lineItem.getSalesTranId())) {
				if (lineItem.getCouponLineAmount() != null && (false == lineItem.getCouponLineAmount().equals(""))) {
					totalCouponAmount += Float.parseFloat(lineItem.getCouponLineAmount().trim());
				}
			}
		}
		orderDetailMapper.addLineDiscountToItem(order, lineDiscountEntityList, lineCouponEntityList);

		/*
		 * List<ShipTosBean> shipToList =
		 * customerSalesDAO.getShipToInfo(order.getSalesTranId());
		 * order.setShipTos(shipToList);
		 */

		return salesOrderResponse;
	}

	@Override
	public SalesShipmentResponse getCustomerShipmentDetails(String customer_id, String year) {
		// TODO Auto-generated method stub
		SalesShipmentResponse resp = new SalesShipmentResponse();
		List<Shipment> customerShipmentDetails = customerShipmentDAO.getCustomerShipmentDetails(customer_id, year);
		List<Shipment> shipmentOrderSum = customerShipmentDAO.getShipmentLocOrderSum(customer_id, year);
		List<Shipment> shipmentOrderCount = customerShipmentDAO.getShipmentOrderCount(customer_id);

		/*
		 * Integer currentYear = Integer.parseInt(year); Integer
		 * lastYear=currentYear-1; List<Shipment> previousFYS =
		 * customerShipmentDAO.getShipmentLocOrderSum(customer_id,lastYear.
		 * toString());
		 */
		for (Shipment shipment : customerShipmentDetails) {

			String shiptoAddress1 = shipment.getShiptoAddress1();
			if (shiptoAddress1 == null) {
				shiptoAddress1 = "EMPTY LOCATION";
			}
			for (Shipment shipmntOrderCount : shipmentOrderCount) {
				if (shipmntOrderCount.getShiptoAddress1().equals(shiptoAddress1)) {
					shipment.setCfy_order_count(shipmntOrderCount.getCfy_order_count());
					shipment.setLfy_order_count(shipmntOrderCount.getLfy_order_count());
					break;
				}
			}
			for (Shipment currentFYShip1 : shipmentOrderSum) {
				if (currentFYShip1.getShiptoAddress1().equals(shiptoAddress1)) {
					if (currentFYShip1.getFiscalYr() != null
							&& currentFYShip1.getFiscalYr().equalsIgnoreCase("CURRENT")) {
						shipment.setCurrentFYS(currentFYShip1.getLocOrderSum());
					} else {
						shipment.setPreviousFYS(currentFYShip1.getLocOrderSum());
					}
					if (shipment.getPreviousFYS() != null && (false == shipment.getPreviousFYS().equals(""))
							&& shipment.getCurrentFYS() != null && (false == shipment.getCurrentFYS().equals(""))) {
						break;
					}
				}
			}
			String currentFYS1 = shipment.getCurrentFYS();
			String previousFYS = shipment.getPreviousFYS();
			Float currentFYSfloat = 0f;
			Float lastFYSfloat = 0f;
			if (currentFYS1 != null) {
				currentFYSfloat = Float.parseFloat(currentFYS1);
			}
			if (previousFYS != null) {
				lastFYSfloat = Float.parseFloat(previousFYS);
			}

			Float round = (currentFYSfloat - lastFYSfloat) / lastFYSfloat * 100;
			if (lastFYSfloat == 0f && currentFYSfloat > 0f) {
				round = 100f;
			}
			if (!Float.isNaN(round))
				shipment.setYoy(String.format(_02F, round));

			/*
			 * for (Shipment previousFYShip1 : previousFYS) {
			 * if(previousFYShip1.getShiptoAddress1().equalsIgnoreCase(
			 * shiptoAddress1)){
			 * shipment.setPreviousFYS(previousFYShip1.getLocOrderSum()); break;
			 * } }
			 */
		}

		resp.setShipments(customerShipmentDetails);
		return resp;
	}

	@Override
	public ParentChildInfo getParentChildInfo(String customer_id, String sbuName) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getParentChildInfo(customer_id, sbuName);
	}

	@Override
	public CategoryDivisionResponse getCategories(String customerId) {
		CategoryDivisionResponse response = new CategoryDivisionResponse();
		List<CategoryDivisionVO> categories = customerSalesDAO.getCategories(customerId);

		response.setCategories(categories);
		return response;
	}

	@Override
	public CustomerRepResponse getAllRepCustomers(String representive_id) {
		return customerSalesDAO.getAllRepCustomers(representive_id);
	}
	
	@Override
	public CustomerRepResponse getAllSamCustomers(String representive_id, JQueryDataTableInputDTO dataTableInput) {
		return customerSalesDAO.getAllSamCustomers(representive_id,dataTableInput);// server side
		//return customerSalesDAO.getAllRepCustomers(representive_id);//client side
	}
	@Override
	public FiscalCategoryCalResponse getCcategoryFiscalMonthCals(String customer_id, String sbuName) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getCcategoryFiscalMonthCals(customer_id, sbuName);
	}

	@Override
	public CategoryPercentageResponse getFiscalCategoryPercentage(String customer_id, String sbuName) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getFiscalCategoryPercentage(customer_id, sbuName);
	}

	@Override
	public FiscalYearResponse listLastFiscalYears(String customer_id, String sbuName) {

		return customerSalesDAO.listLatestFiscalYears(customer_id);
	}

	@Override
	public ShipmentOrdersResponse listShipmentOrdersList(String customer_id, String sbuName, String location) {

		ShipmentOrdersResponse response = new ShipmentOrdersResponse();
		String sourceSystemId = customerSalesDAO.getSourceSystemId(sbuName);
		List<CustomerOrders> ordersByShipmentLocation = customerShipmentDAO.getOrdersByShipmentLocation(customer_id,
				location, null);

		List<String> salesTransIds = new ArrayList<>();
		List<String> salesOrderNos = new ArrayList<>();
		List<String> masterSalesTrans = new ArrayList<>();
		Map<String, List<String>> originalVsReturnTransIds = new HashMap<>();
		Map<String, List<String>> originalTransIdVsOrderNos = new HashMap<>();
		Map<String, Float> originalVsReturnAmount = new HashMap<>();

		for (CustomerOrders customerOrders : ordersByShipmentLocation) {
			salesTransIds.add(customerOrders.getTransId());
			salesOrderNos.add(customerOrders.getOrderNumber());
			findReturnItemDetails(masterSalesTrans, originalVsReturnTransIds, originalTransIdVsOrderNos,
					originalVsReturnAmount, customerOrders);
		}
		Map<String, List<OrderItem>> tranIdVsOrderItems = customerSalesDAO.getTransactionItems(salesTransIds);
		List<String> masterSaleTranSourceNumbers = customerSalesDAO.getMasterSalesTranSourceNumber(masterSalesTrans);
		ParentChildInfo parentChildInfo = customerSalesDAO.getParentChildInfo(customer_id, sourceSystemId);
		List<String> childs = parentChildInfo.getChilds();
		List<LineDiscountBean> lineDiscountEntityList = customerSalesDAO.getLineDiscounts(salesTransIds);
		List<LineCouponBean> lineCouponEntityList = customerSalesDAO.getLineCoupons(salesTransIds);
		for (CustomerOrders order : ordersByShipmentLocation) {
			calculateSalesReturnSum(tranIdVsOrderItems, order);
			//setSaleOrderTotalNReturnItems(tranIdVsOrderItems, order);
			setOrderReturnNIssuer(customer_id, masterSaleTranSourceNumbers, childs, order,tranIdVsOrderItems);
			addContactname(order);
			addTotalCount(lineDiscountEntityList, lineCouponEntityList, order);

			
			//Float saleTotalAmount = calculateSalesReturnSum(0f, order);
			/*Float returnedOrderAmt = originalVsReturnAmount.get(order.getTransId());
			if (null != returnedOrderAmt) {
				String tranTotalAmount = order.getTranTotalAmount();
				if (null != tranTotalAmount && false == "".equals(tranTotalAmount.trim())) {
					float orgAmt = Float.parseFloat(tranTotalAmount);
					Float balance = orgAmt - returnedOrderAmt;
					order.setTranTotalAmount(String.format(_02F, balance));
				}

			}*/
			setTranTotalAmount(originalVsReturnAmount, order);
			
			

			/*if ("107".equals(order.getSourceSystemId())) {
				String tranTotalAmount = order.getTranTotalAmount();
				if (null != tranTotalAmount && false == "".equals(tranTotalAmount.trim())) {
					float orgAmt = Float.parseFloat(tranTotalAmount);
					Float balance = orgAmt + salesReturnCount;
					order.setTranTotalAmount(String.format(_02F, balance));
				}
			}
*/
			if (masterSaleTranSourceNumbers.contains(order.getOrderNumber())) {
				order.setOrderReturned("true");
				List<String> returnOrders = originalTransIdVsOrderNos.get(order.getTransId());
				order.setReturnOrderNos(returnOrders);
			}

			setTotalOrderItemCount(masterSalesTrans, originalVsReturnTransIds, tranIdVsOrderItems, order);
			if ("107".equals(order.getSourceSystemId())) {
				setSaleOrderItemCount(tranIdVsOrderItems, order);
			}

		}

		response.setOrders(ordersByShipmentLocation);
		response.setLocation(location);
		return response;
	}

	private void setOrderIssuer(List<String> childs, String customer_id, CustomerOrders order) {
		String orderIssued = null;
		if (order.getMasterNumber() != null) {
			orderIssued = order.getMasterNumber();
		} else if (order.getTransRewardNumber() != null) {
			orderIssued = order.getTransRewardNumber();
		}
		List<String> childsLpad=new ArrayList<>();
		
		for (String child : childs) {
			
			if(false==(child.indexOf(customer_id)!=-1 || customer_id.indexOf(child)!=-1)){
				childsLpad.add(child);
			}
		}
		
		if (childsLpad.contains(orderIssued)) {

			orderIssued += C;
		} else {
			if(false==(orderIssued.indexOf(customer_id)!=-1 || customer_id.indexOf(orderIssued)!=-1)){
				orderIssued += C;
			}
			else{
				orderIssued += P;
			}
		}
		order.setOrderIssuer(orderIssued);
	}
	private void setOrderReturnNIssuer(String customer_id, List<String> masterSaleTranSourceNumbers,
			List<String> childs, CustomerOrders order, Map<String, List<OrderItem>> tranIdVsOrderItems) {
		
		//This is for sourcesystemid 102
		if (masterSaleTranSourceNumbers.contains(order.getOrderNumber())) {
			
			order.setOrderReturned("true");
		}
		//This is for sourcesystemid 107
		List<OrderItem> orgItemList = tranIdVsOrderItems.get(order.getTransId());
		if (null != orgItemList) {
			for (OrderItem orgItem : orgItemList) {
				if ("107".equals(order.getSourceSystemId()) && "RETURN".equalsIgnoreCase(orgItem.getTranLineStatusId())) 
				{
					order.setOrderReturned("true");
					order.setReturnOrderNos(Arrays.asList(order.getOrderNumber()));
					break;
				}
			}
		}
		
		setOrderIssuer(childs, customer_id, order);
	}

	private void addContactname(CustomerOrders order) {
		String firstName = order.getShipToFirstname();
		String lastName = order.getShipToLastname();
		String contactName = "";
		if (firstName != null && null != lastName) {
			contactName = firstName + " " + lastName;

		} else if (firstName == null && lastName != null) {
			contactName = lastName;
		} else if (firstName != null && lastName == null) {
			contactName = firstName;
		}
		order.setContactName(contactName);
	}

	@Override
	public HawkeyeDetailsVo getHawkeyeDetails(String customer_id) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getHawkeyeDetails(customer_id);
	}

	@Override
	public SuperUserDetailsVo getSuperUserDetailsSam(String customer_id) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getSuperUserDetailsSam(customer_id);
	}

	@Override
	public CustomerRepResponse getCustomerDetails(String representive_id, String custNum) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getCustomerDetails(representive_id, custNum);
	}

	@Override
	public String getCustomerLatestOrderDate(String customerId) {
		// TODO Auto-generated method stub
		return customerSalesDAO.getCustomerLatestOrderDate(customerId);
	}

}
