package com.staples.dashboard.app.sdc.service.mappers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.staples.dashboard.app.sdc.dao.bean.OrderDetailEntity;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;
import com.staples.dashboard.app.sdc.vo.OrderItem;

@Service
public class OrderDetailMapperImpl implements IOrderDetailMapper {

	@Override
	public void convertSalesHeader(CustomerOrder orderDetailBean, List<OrderDetailEntity> resultListHdr) {
		// TODO Auto-generated method stub

		OrderDetailEntity ordrDtlBean = resultListHdr.get(0);

		orderDetailBean.setSalesTranId(ordrDtlBean.getSalesTranId() != null ? ordrDtlBean.getSalesTranId().trim() : "");
		//orderDetailBean
			//	.setCustomerNo(ordrDtlBean.getCustomerNumber() != null ? ordrDtlBean.getCustomerNumber().trim() : "");
		orderDetailBean.setRecordType(ordrDtlBean.getRecordType() != null ? ordrDtlBean.getRecordType().trim() : "");
		orderDetailBean.setOrderNo(ordrDtlBean.getOrderNo() != null ? ordrDtlBean.getOrderNo().trim() : "");
		orderDetailBean.setOrderHeaderKey(
				ordrDtlBean.getOrderHeaderKey() != null ? ordrDtlBean.getOrderHeaderKey().trim() : "");
		orderDetailBean.setEnterpriseCode(
				ordrDtlBean.getEnterpriseCode() != null ? ordrDtlBean.getEnterpriseCode().trim() : "");
		orderDetailBean
				.setDivisionCode(ordrDtlBean.getDivisionCode() != null ? ordrDtlBean.getDivisionCode().trim() : "");
		orderDetailBean
				.setOrderChannel(ordrDtlBean.getOrderChannel() != null ? ordrDtlBean.getOrderChannel().trim() : "");
		orderDetailBean.setSubscriptionID(
				ordrDtlBean.getSubscriptionID() != null ? ordrDtlBean.getSubscriptionID().trim() : "");

//		if (ordrDtlBean.getOrderDate() != null) {
//			Date orderDate = ordrDtlBean.getOrderDate();
//			SimpleDateFormat sdfFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
//			String orderDateStr = sdfFormatter.format(orderDate);
//			orderDetailBean.setOrderDate(orderDateStr);
//		} else {
//			orderDetailBean.setOrderDate("");
//		}
		orderDetailBean.setOrderStatusDesc(
				ordrDtlBean.getOrderStatusDesc() != null ? ordrDtlBean.getOrderStatusDesc().trim() : "");
//		orderDetailBean.setOrderMethod(ordrDtlBean.getOrderMethod() != null ? ordrDtlBean.getOrderMethod().trim() : "");
//		orderDetailBean.setCustomerPONo(
//				ordrDtlBean.getCustomerPONumber() != null ? ordrDtlBean.getCustomerPONumber().trim() : "");
//		orderDetailBean.setBillToID(ordrDtlBean.getBilltoId() != null ? ordrDtlBean.getBilltoId().trim() : "");
		orderDetailBean.setBillToFirstName(
				ordrDtlBean.getBillToFirstName() != null ? ordrDtlBean.getBillToFirstName().trim() : "");
		orderDetailBean.setBillToLastName(
				ordrDtlBean.getBillToLastName() != null ? ordrDtlBean.getBillToLastName().trim() : "");
		orderDetailBean
				.setBillToCompany(ordrDtlBean.getBillToCompany() != null ? ordrDtlBean.getBillToCompany().trim() : "");
		orderDetailBean.setBillToAddressLine1(
				ordrDtlBean.getBillToAddressLine1() != null ? ordrDtlBean.getBillToAddressLine1().trim() : "");
		orderDetailBean.setBillToAddressLine2(
				ordrDtlBean.getBillToAddressLine2() != null ? ordrDtlBean.getBillToAddressLine2().trim() : "");
		orderDetailBean.setBillToAddressLine3(
				ordrDtlBean.getBillToAddressLine3() != null ? ordrDtlBean.getBillToAddressLine3().trim() : "");
		orderDetailBean.setBillToCity(ordrDtlBean.getBillToCity() != null ? ordrDtlBean.getBillToCity().trim() : "");
		orderDetailBean.setBillToState(ordrDtlBean.getBillToState() != null ? ordrDtlBean.getBillToState().trim() : "");
		orderDetailBean
				.setBillToZipCode(ordrDtlBean.getBillToZipCode() != null ? ordrDtlBean.getBillToZipCode().trim() : "");
		orderDetailBean
				.setShipToZipCode(ordrDtlBean.getShipToZipCode() != null ? ordrDtlBean.getShipToZipCode().trim() : "");
	}

	@Override
	public void addLineDiscountToItem(CustomerOrder order, List<LineDiscountBean> lineDiscountEntityList,
			List<LineCouponBean> lineCouponEntityList) {
		// TODO Auto-generated method stub
		
		List<OrderItem> orderLineItems = order.getOrderLineItems();
		for (OrderItem orderItem : orderLineItems) {
			for (LineDiscountBean lineItem : lineDiscountEntityList) {
				if(orderItem.getTransLineItemId().equalsIgnoreCase(lineItem.getSalesTranDetailId())){
					List<LineDiscountBean> lineDiscount = orderItem.getLineDiscount();
					if(lineDiscount==null){
						lineDiscount=new ArrayList<>();
						orderItem.setLineDiscount(lineDiscount);
					}
					lineDiscount.add(lineItem);
				}
			}
			for (LineCouponBean lineItem : lineCouponEntityList) {
				if(orderItem.getTransLineItemId().equalsIgnoreCase(lineItem.getSalesTranDetailId())){
					List<LineCouponBean> lineCoupon = orderItem.getCouponItem();
					if(lineCoupon==null){
						lineCoupon=new ArrayList<>();
						orderItem.setCouponItem(lineCoupon);
					}
					lineCoupon.add(lineItem);
				}
			}
		}
		
	}

	

	/*
	 * public void convertContainerHeader(List<Object[]> resultList,
	 * ContainerResponseBean containerResponseBean) {
	 * 
	 * for (Object[] result : resultList) {
	 * 
	 * int i = 0; containerResponseBean.setShipmentKey(result[i] != null ?
	 * ((String)result[i]).trim(): "");
	 * containerResponseBean.setShipmentId(result[++i] != null ?
	 * ((String)result[i]).trim(): "");
	 * containerResponseBean.setShipmentNo(result[++i] != null ?
	 * ((String)result[i]).trim(): "");
	 * 
	 * if(result[++i] != null) { Date estimatedDeliveryDate = (Date)result[i];
	 * SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * estimatedDeliveryDateStr = sdfFormatter.format(estimatedDeliveryDate);
	 * containerResponseBean.setEstimatedDeliveryDate(estimatedDeliveryDateStr);
	 * } else { containerResponseBean.setEstimatedDeliveryDate(""); }
	 * 
	 * if(result[++i] != null) { Date actualDeliveryDate = (Date)result[i];
	 * SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * actualDeliveryDateStr = sdfFormatter.format(actualDeliveryDate);
	 * containerResponseBean.setActualDeliveryDate(actualDeliveryDateStr); }
	 * else { containerResponseBean.setActualDeliveryDate(""); }
	 * 
	 * if(result[++i] != null) { Date shipDate = (Date)result[i];
	 * SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String shipDateStr
	 * = sdfFormatter.format(shipDate);
	 * containerResponseBean.setShipDate(shipDateStr); } else {
	 * containerResponseBean.setShipDate(""); }
	 * 
	 * 
	 * containerResponseBean.setShipStatusCode(result[++i] != null ?
	 * ((String)result[i]).trim(): "");
	 * containerResponseBean.setShipStatusDesc(result[++i] != null ?
	 * ((String)result[i]).trim(): "");
	 * containerResponseBean.setShipCarrier(result[++i] != null ?
	 * ((String)result[i]).trim(): ""); } }
	 * 
	 * public void convertTotalContainerData(List<TotalContainerBean>
	 * containerDtlList, ContainerResponseBean containerResponseBean) {
	 * 
	 * Map<String, ContainerBean> cntnrBeanMap = new HashMap<String,
	 * ContainerBean>(); Map<String, ContainerDetailsBean> cntnrDtlBeanMap = new
	 * HashMap<String, ContainerDetailsBean>(); List<ContainerBean> containers =
	 * new ArrayList<ContainerBean>();
	 * 
	 * for(TotalContainerBean totalContainerBean:containerDtlList){
	 * 
	 * ContainerBean container = null; ContainerDetailsBean detailsBean = null;
	 * 
	 * String containerKey = totalContainerBean.getContainerKey(); String
	 * containerDetailKey = totalContainerBean.getContainerDetailKey();
	 * 
	 * if(!cntnrBeanMap.containsKey(containerKey)){
	 * 
	 * container = new ContainerBean(); cntnrBeanMap.put(containerKey,
	 * container);
	 * 
	 * container.setCarrierTrackingNo(totalContainerBean.getCarrierTrackingNo()
	 * != null ? totalContainerBean.getCarrierTrackingNo().trim(): "");
	 * container.setContainerKey(totalContainerBean.getContainerKey() != null ?
	 * (totalContainerBean.getContainerKey()).toString().trim(): "");
	 * container.setContainerNo(totalContainerBean.getContainerNo() != null ?
	 * totalContainerBean.getContainerNo().trim(): "");
	 * 
	 * List<ContainerDetailsBean> containerDetailsBeans = new
	 * ArrayList<ContainerDetailsBean>();
	 * container.setContainerDetails(containerDetailsBeans);
	 * containers.add(container);
	 * 
	 * //clear the detail map cntnrDtlBeanMap.clear();
	 * 
	 * }else { container = cntnrBeanMap.get(containerKey); }
	 * 
	 * if(!cntnrDtlBeanMap.containsKey(containerDetailKey)){
	 * 
	 * detailsBean = new ContainerDetailsBean();
	 * cntnrDtlBeanMap.put(containerDetailKey, detailsBean);
	 * 
	 * detailsBean.setContainerProductSKU(totalContainerBean.
	 * getContainerProductSKU() != null ?
	 * totalContainerBean.getContainerProductSKU().trim(): "");
	 * detailsBean.setContainerProductDesc(totalContainerBean.
	 * getContainerProductDesc()!= null ?
	 * totalContainerBean.getContainerProductDesc().trim(): "");
	 * detailsBean.setContainerProductUOM(totalContainerBean.
	 * getContainerProductUOM() != null ?
	 * totalContainerBean.getContainerProductUOM().trim(): "");
	 * detailsBean.setContainerQty(totalContainerBean.getContainerQty() != null
	 * ? (totalContainerBean.getContainerQty()).doubleValue(): 0.0d);
	 * detailsBean.setOrderLineKey(totalContainerBean.getOrderLineKey() != null
	 * ? (totalContainerBean.getOrderLineKey()).toString().trim(): "");
	 * detailsBean.setShipmentLineKey(totalContainerBean.getShipmentLineKey() !=
	 * null ? (totalContainerBean.getShipmentLineKey()).toString().trim(): "");
	 * detailsBean.setContainerDetailKey(totalContainerBean.
	 * getContainerDetailKey() != null ?
	 * (totalContainerBean.getContainerDetailKey().toString().trim()): "");
	 * 
	 * container.getContainerDetails().add(detailsBean); } }
	 * 
	 * if(!containers.isEmpty()){
	 * containerResponseBean.setContainers(containers);
	 * containerResponseBean.setNoOfCartons(containers.size() != 0 ?
	 * Integer.toString(containers.size()): CommonConstants.ZERO_STRING); }else{
	 * containerResponseBean.setContainers(new ArrayList<ContainerBean>());
	 * containerResponseBean.setNoOfCartons(CommonConstants.ZERO_STRING); } }
	 * 
	 * public void convertShipmentHeader(List<Object[]> resultListHdr,
	 * ShipmentResponseBean shipmentResponseBean){
	 * 
	 * Object[]resultHdr = resultListHdr.get(0);
	 * 
	 * int i = 0; //set shipment header info in shipment responseBean
	 * shipmentResponseBean.setOrderHeaderKey(resultHdr[i] != null ?
	 * ((String)resultHdr[i]).trim(): "");
	 * shipmentResponseBean.setEnterpriseCode(resultHdr[++i] != null ?
	 * ((String)resultHdr[i]).trim(): "");
	 * shipmentResponseBean.setOrderNo(resultHdr[++i] != null ?
	 * ((String)resultHdr[i]).trim(): "");
	 * shipmentResponseBean.setBillToID(resultHdr[++i] != null ?
	 * (((BigDecimal)resultHdr[i]).toString()).trim(): "");
	 * shipmentResponseBean.setCustomerNo(resultHdr[++i] != null ?
	 * ((String)resultHdr[i]).trim(): "");
	 * 
	 * if(resultHdr[8]!= null){
	 * 
	 * Date orderDate = (Date) resultHdr[8]; SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * orderDateStr = sdfFormatter.format(orderDate);
	 * shipmentResponseBean.setOrderDate(orderDateStr);
	 * 
	 * }else{ shipmentResponseBean.setOrderDate(""); } }
	 * 
	 * public List<ShipmentBean>
	 * convertTotalShipmentInfo(List<TotalShipmentBean> totalShipmentInfo,String
	 * orderNo) {
	 * 
	 * Map<String, ShipmentBean> shpmntBeanMap = new HashMap<String,
	 * ShipmentBean>(); Map<String, ShipmentLineBean> shpmntLineBeanMap = new
	 * HashMap<String, ShipmentLineBean>(); List<ShipmentBean> shipments = new
	 * ArrayList<ShipmentBean>();
	 * 
	 * for (TotalShipmentBean totalShipmentBean : totalShipmentInfo) {
	 * 
	 * ShipmentBean shipment = null; ShipmentLineBean linesBean = null;
	 * 
	 * String shipmentKey = totalShipmentBean.getShipmentKey(); String
	 * shipmentLineKey = totalShipmentBean.getShipmentLineKey();
	 * 
	 * if (!shpmntBeanMap.containsKey(shipmentKey)) {
	 * 
	 * shipment = new ShipmentBean(); shpmntBeanMap.put(shipmentKey, shipment);
	 * 
	 * shipment.setShipmentKey(totalShipmentBean.getShipmentKey() != null ?
	 * (totalShipmentBean.getShipmentKey().toString().trim()) : "");
	 * shipment.setShipToKey(totalShipmentBean.getShipToKey() != null ?
	 * totalShipmentBean.getShipToKey().toString().trim() : "");
	 * shipment.setShipmentId(totalShipmentBean.getShipmentId() != null ?
	 * (totalShipmentBean.getShipmentId().toString().trim()) : "");
	 * shipment.setShipmentNo(totalShipmentBean.getShipmentNo() != null ?
	 * (totalShipmentBean.getShipmentNo().toString().trim()) : "");
	 * 
	 * if (totalShipmentBean.getEstimatedDeliveryDate() != null) {
	 * 
	 * Date estimatedDeliveryDate =
	 * totalShipmentBean.getEstimatedDeliveryDate(); SimpleDateFormat
	 * sdfFormatter = new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT);
	 * String estimatedDeliveryDateStr =
	 * sdfFormatter.format(estimatedDeliveryDate);
	 * shipment.setEstimatedDeliveryDate(estimatedDeliveryDateStr); } else {
	 * shipment.setEstimatedDeliveryDate(""); }
	 * 
	 * if (totalShipmentBean.getActualDeliveryDate() != null) {
	 * 
	 * Date actualDeliveryDate = totalShipmentBean.getActualDeliveryDate();
	 * SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * actualDeliveryDateStr = sdfFormatter.format(actualDeliveryDate);
	 * shipment.setActualDeliveryDate(actualDeliveryDateStr); } else {
	 * shipment.setActualDeliveryDate(""); }
	 * 
	 * if(totalShipmentBean.getShipDate() != null){
	 * 
	 * Date shipDate = totalShipmentBean.getShipDate(); SimpleDateFormat
	 * sdfFormatter = new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT);
	 * String shipDateStr = sdfFormatter.format(shipDate);
	 * shipment.setShipDate(shipDateStr);
	 * 
	 * }else{ shipment.setShipDate(""); }
	 * shipment.setShipStatusCode(totalShipmentBean.getShipStatusCode() != null
	 * ? totalShipmentBean.getShipStatusCode().trim() : "");
	 * shipment.setShipStatusDesc(totalShipmentBean.getShipStatusDesc() != null
	 * ? totalShipmentBean.getShipStatusDesc().trim() : "");
	 * shipment.setShipCarrier(totalShipmentBean.getShipCarrier() != null ?
	 * totalShipmentBean.getShipCarrier().trim() : "");
	 * 
	 * List<ShipmentLineBean> shipmentLines = new ArrayList<ShipmentLineBean>();
	 * shipment.setShipmentLines(shipmentLines); shipments.add(shipment);
	 * 
	 * // clear the detail map shpmntLineBeanMap.clear(); } else { shipment =
	 * shpmntBeanMap.get(shipmentKey); }
	 * 
	 * if (!shpmntLineBeanMap.containsKey(shipmentLineKey)) {
	 * 
	 * linesBean = new ShipmentLineBean();
	 * shpmntLineBeanMap.put(shipmentLineKey, linesBean);
	 * 
	 * //if poOrderNo is not equal to input original orderNo. then only return
	 * it in response json, otherwise return null.
	 * if(totalShipmentBean.getPoOrderNo()!= null &&
	 * !(orderNo.equalsIgnoreCase((totalShipmentBean.getPoOrderNo()).trim()))){
	 * linesBean.setPoOrderNo((totalShipmentBean.getPoOrderNo()).trim()); }else{
	 * linesBean.setPoOrderNo(""); }
	 * 
	 * linesBean.setOrderLineKey(totalShipmentBean.getOrderLineKey() != null ?
	 * (totalShipmentBean.getOrderLineKey()).toString().trim() : "");
	 * linesBean.setShipmentLineKey(totalShipmentBean.getShipmentLineKey() !=
	 * null ? (totalShipmentBean.getShipmentLineKey()).toString().trim() : "");
	 * linesBean.setShipProductName(totalShipmentBean.getShipProductName() !=
	 * null ? totalShipmentBean.getShipProductName().trim() : "");
	 * linesBean.setShipProductDesc(totalShipmentBean.getShipProductDesc() !=
	 * null ? totalShipmentBean.getShipStatusDesc().trim() : "");
	 * linesBean.setShipProductSKU(totalShipmentBean.getShipProductSKU() != null
	 * ? totalShipmentBean.getShipProductSKU().trim() : "");
	 * linesBean.setShipProductUOM(totalShipmentBean.getShipProductUOM() != null
	 * ? totalShipmentBean.getShipProductUOM().trim() : "");
	 * linesBean.setShipQty(totalShipmentBean.getShipQty() != null ?
	 * totalShipmentBean.getShipQty() : 0.0D);
	 * linesBean.setShipOriginalQty(totalShipmentBean.getShipOriginalQty() !=
	 * null ? totalShipmentBean.getShipOriginalQty() : 0.0D);
	 * linesBean.setShipShortageQty(totalShipmentBean.getShipShortageQty() !=
	 * null ? totalShipmentBean.getShipShortageQty() : 0.0D);
	 * 
	 * shipment.getShipmentLines().add(linesBean); } }
	 * 
	 * return shipments; }
	 * 
	 * public void convertSalesHeader(OrderDetailBean orderDetailBean,
	 * List<OrderDetailEntityBean> resultListHdr){
	 * 
	 * OrderDetailEntityBean ordrDtlBean = resultListHdr.get(0);
	 * 
	 * orderDetailBean.setSalesTranId(ordrDtlBean.getSalesTranId() != null ?
	 * ordrDtlBean.getSalesTranId().trim(): "");
	 * orderDetailBean.setCustomerNo(ordrDtlBean.getCustomerNumber() != null ?
	 * ordrDtlBean.getCustomerNumber().trim(): "");
	 * orderDetailBean.setRecordType(ordrDtlBean.getRecordType() != null ?
	 * ordrDtlBean.getRecordType().trim(): "");
	 * orderDetailBean.setOrderNo(ordrDtlBean.getOrderNo() != null ?
	 * ordrDtlBean.getOrderNo().trim(): "");
	 * orderDetailBean.setOrderHeaderKey(ordrDtlBean.getOrderHeaderKey() != null
	 * ? ordrDtlBean.getOrderHeaderKey().trim(): "");
	 * orderDetailBean.setEnterpriseCode(ordrDtlBean.getEnterpriseCode() != null
	 * ? ordrDtlBean.getEnterpriseCode().trim(): "");
	 * orderDetailBean.setDivisionCode(ordrDtlBean.getDivisionCode() != null ?
	 * ordrDtlBean.getDivisionCode().trim(): "");
	 * orderDetailBean.setOrderChannel(ordrDtlBean.getOrderChannel() != null ?
	 * ordrDtlBean.getOrderChannel().trim(): "");
	 * orderDetailBean.setSubscriptionID(ordrDtlBean.getSubscriptionID() != null
	 * ? ordrDtlBean.getSubscriptionID().trim(): "");
	 * 
	 * if (ordrDtlBean.getOrderDate() != null) { Date orderDate =
	 * ordrDtlBean.getOrderDate(); SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * orderDateStr = sdfFormatter.format(orderDate);
	 * orderDetailBean.setOrderDate(orderDateStr); } else {
	 * orderDetailBean.setOrderDate(""); }
	 * orderDetailBean.setOrderStatusDesc(ordrDtlBean.getOrderStatusDesc() !=
	 * null ? ordrDtlBean.getOrderStatusDesc().trim(): "");
	 * orderDetailBean.setOrderMethod(ordrDtlBean.getOrderMethod() != null ?
	 * ordrDtlBean.getOrderMethod().trim(): "");
	 * orderDetailBean.setCustomerPONo(ordrDtlBean.getCustomerPONumber() != null
	 * ? ordrDtlBean.getCustomerPONumber().trim(): "");
	 * orderDetailBean.setBillToID(ordrDtlBean.getBilltoId() != null ?
	 * ordrDtlBean.getBilltoId().trim(): "");
	 * orderDetailBean.setBillToFirstName(ordrDtlBean.getBillToFirstName() !=
	 * null ? ordrDtlBean.getBillToFirstName().trim(): "");
	 * orderDetailBean.setBillToLastName(ordrDtlBean.getBillToLastName() != null
	 * ? ordrDtlBean.getBillToLastName().trim(): "");
	 * orderDetailBean.setBillToCompany(ordrDtlBean.getBillToCompany() != null ?
	 * ordrDtlBean.getBillToCompany().trim(): "");
	 * orderDetailBean.setBillToAddressLine1(ordrDtlBean.getBillToAddressLine1()
	 * != null ? ordrDtlBean.getBillToAddressLine1().trim(): "");
	 * orderDetailBean.setBillToAddressLine2(ordrDtlBean.getBillToAddressLine2()
	 * != null ? ordrDtlBean.getBillToAddressLine2().trim(): "");
	 * orderDetailBean.setBillToAddressLine3(ordrDtlBean.getBillToAddressLine3()
	 * != null ? ordrDtlBean.getBillToAddressLine3().trim(): "");
	 * orderDetailBean.setBillToCity(ordrDtlBean.getBillToCity() != null ?
	 * ordrDtlBean.getBillToCity().trim(): "");
	 * orderDetailBean.setBillToState(ordrDtlBean.getBillToState() != null ?
	 * ordrDtlBean.getBillToState().trim(): "");
	 * orderDetailBean.setBillToZipCode(ordrDtlBean.getBillToZipCode() != null ?
	 * ordrDtlBean.getBillToZipCode().trim(): "");
	 * orderDetailBean.setShipToZipCode(ordrDtlBean.getShipToZipCode() != null ?
	 * ordrDtlBean.getShipToZipCode().trim(): ""); }
	 * 
	 * public List<OrderLinesBean> convertOrderLines(List<OrderLinesEntityBean>
	 * orderLinesEntityList){
	 * 
	 * List<OrderLinesBean> orderLinesWthProdctList = new
	 * ArrayList<OrderLinesBean>();
	 * 
	 * for (OrderLinesEntityBean orderLinesEntityBean : orderLinesEntityList) {
	 * 
	 * OrderLinesBean orderLines = new OrderLinesBean(); ProductBean product =
	 * new ProductBean();
	 * 
	 * orderLines.setOrderLineKey(orderLinesEntityBean.getOrderLineKey() != null
	 * ? orderLinesEntityBean.getOrderLineKey(): ""); if
	 * (orderLinesEntityBean.getRequestedDeliveryDate() != null) { Date
	 * requestedDeliveryDate = orderLinesEntityBean.getRequestedDeliveryDate();
	 * SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * requestedDeliveryDateStr = sdfFormatter.format(requestedDeliveryDate);
	 * orderLines.setRequestedDeliveryDate(requestedDeliveryDateStr); } else {
	 * orderLines.setRequestedDeliveryDate(""); }
	 * orderLines.setShipToKey(orderLinesEntityBean.getShipToKey() != null ?
	 * orderLinesEntityBean.getShipToKey().trim(): "");
	 * orderLines.setShipmentIdentifier(orderLinesEntityBean.
	 * getShipmentIdentifier() != null ?
	 * orderLinesEntityBean.getShipmentIdentifier().trim(): "");
	 * orderLines.setShipmentType(orderLinesEntityBean.getShipmentType() != null
	 * ? orderLinesEntityBean.getShipmentType().trim() : "");
	 * orderLines.setOrderLineNo(orderLinesEntityBean.getOrderLineNo() != null ?
	 * orderLinesEntityBean.getOrderLineNo().trim(): "");
	 * orderLines.setType(orderLinesEntityBean.getType() != null ?
	 * orderLinesEntityBean.getType().trim(): "");
	 * orderLines.setOrderQty(orderLinesEntityBean.getOrderQty() != null ?
	 * orderLinesEntityBean.getOrderQty(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setOriginalOrderedQty(orderLinesEntityBean.
	 * getOriginalOrderedQty() != null ?
	 * orderLinesEntityBean.getOriginalOrderedQty(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setExtendedPrice(orderLinesEntityBean.getExtendedPrice() !=
	 * null ? orderLinesEntityBean.getExtendedPrice(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setReshipLineType(orderLinesEntityBean.getReshipLineType() !=
	 * null ? orderLinesEntityBean.getReshipLineType().trim(): "");
	 * orderLines.setDerivedFromOrderLine(orderLinesEntityBean.
	 * getDerivedFromOrderLine() != null ?
	 * orderLinesEntityBean.getDerivedFromOrderLine().trim(): "");
	 * orderLines.setReturnOrderHeaderKey(orderLinesEntityBean.
	 * getReturnOrderHeaderKey() != null ?
	 * orderLinesEntityBean.getReturnOrderHeaderKey().trim(): "");
	 * product.setCustomImage(orderLinesEntityBean.getCustomImage() != null ?
	 * orderLinesEntityBean.getCustomImage().trim(): "");
	 * product.setProductDesc(orderLinesEntityBean.getProductDesc() != null ?
	 * orderLinesEntityBean.getProductDesc().trim(): "");
	 * product.setProductDisplayName(orderLinesEntityBean.getProductDisplayName(
	 * ) != null ? orderLinesEntityBean.getProductDisplayName().trim(): "");
	 * product.setProductSKU(orderLinesEntityBean.getProductSKU() != null ?
	 * orderLinesEntityBean.getProductSKU().trim(): "");
	 * product.setVendorModel(orderLinesEntityBean.getVendorModel() != null ?
	 * orderLinesEntityBean.getVendorModel().trim(): "");
	 * product.setProductUOM(orderLinesEntityBean.getProductUOM() != null ?
	 * orderLinesEntityBean.getProductUOM().trim(): "");
	 * product.setPniJobId(orderLinesEntityBean.getPniJobId() != null ?
	 * orderLinesEntityBean.getPniJobId().trim(): "");
	 * product.setUnitPrice(orderLinesEntityBean.getUnitPrice() != null ?
	 * orderLinesEntityBean.getUnitPrice(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * 
	 * orderLines.setProduct(product); orderLinesWthProdctList.add(orderLines);
	 * }
	 * 
	 * return orderLinesWthProdctList; }
	 * 
	 * public void convertReturnSalesHeader(String orderNo, ReturnDetailsBean
	 * returnDetailsBean, List<ReturnDetailsEntityBean>
	 * returnDetailsEntityList){
	 * 
	 * ReturnDetailsEntityBean rtrnDtlsFrmList = returnDetailsEntityList.get(0);
	 * 
	 * returnDetailsBean.setSalesTranId(rtrnDtlsFrmList.getSalesTranId() != null
	 * ? rtrnDtlsFrmList.getSalesTranId().trim(): "");
	 * returnDetailsBean.setRecordType(rtrnDtlsFrmList.getRecordType() != null ?
	 * rtrnDtlsFrmList.getRecordType().trim(): "");
	 * returnDetailsBean.setOrderNo(orderNo);
	 * returnDetailsBean.setReturnOrderNo(rtrnDtlsFrmList.getReturnOrderNo() !=
	 * null ? rtrnDtlsFrmList.getReturnOrderNo().trim(): "");
	 * returnDetailsBean.setOrderHeaderKey(rtrnDtlsFrmList.getOrderHeaderKey()
	 * != null ? rtrnDtlsFrmList.getOrderHeaderKey().trim(): "");
	 * returnDetailsBean.setEnterpriseCode(rtrnDtlsFrmList.getEnterpriseCode()
	 * != null ? rtrnDtlsFrmList.getEnterpriseCode().trim(): "");
	 * returnDetailsBean.setDivisionCode(rtrnDtlsFrmList.getDivisionCode() !=
	 * null ? rtrnDtlsFrmList.getDivisionCode().trim(): "");
	 * 
	 * if (rtrnDtlsFrmList.getOrderDate() != null) { Date orderDate =
	 * rtrnDtlsFrmList.getOrderDate(); SimpleDateFormat sdfFormatter = new
	 * SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT); String
	 * orderDateStr = sdfFormatter.format(orderDate);
	 * returnDetailsBean.setOrderDate(orderDateStr); } else {
	 * returnDetailsBean.setOrderDate(""); }
	 * 
	 * returnDetailsBean.setOrderStatusDesc(rtrnDtlsFrmList.getOrderStatusDesc()
	 * != null ? rtrnDtlsFrmList.getOrderStatusDesc().trim(): "");
	 * returnDetailsBean.setOrderMethod(rtrnDtlsFrmList.getOrderMethod() != null
	 * ? rtrnDtlsFrmList.getOrderMethod().trim(): "");
	 * returnDetailsBean.setCustomerPONo(rtrnDtlsFrmList.getCustomerPONo() !=
	 * null ? rtrnDtlsFrmList.getCustomerPONo().trim(): "");
	 * returnDetailsBean.setCustomerNo(rtrnDtlsFrmList.getCustomerNo() != null ?
	 * rtrnDtlsFrmList.getCustomerNo().trim(): "");
	 * returnDetailsBean.setBillToFirstName(rtrnDtlsFrmList.getBillToFirstName()
	 * != null ? rtrnDtlsFrmList.getBillToFirstName().trim(): "");
	 * returnDetailsBean.setBillToLastName(rtrnDtlsFrmList.getBillToLastName()
	 * != null ? rtrnDtlsFrmList.getBillToLastName().trim(): "");
	 * returnDetailsBean.setBillToCompany(rtrnDtlsFrmList.getBillToCompany() !=
	 * null ? rtrnDtlsFrmList.getBillToCompany().trim(): "");
	 * returnDetailsBean.setBillToAddressLine1(rtrnDtlsFrmList.
	 * getBillToAddressLine1() != null ?
	 * rtrnDtlsFrmList.getBillToAddressLine1().trim(): "");
	 * returnDetailsBean.setBillToAddressLine2(rtrnDtlsFrmList.
	 * getBillToAddressLine2() != null ?
	 * rtrnDtlsFrmList.getBillToAddressLine2().trim(): "");
	 * returnDetailsBean.setBillToAddressLine3(rtrnDtlsFrmList.
	 * getBillToAddressLine3() != null ?
	 * rtrnDtlsFrmList.getBillToAddressLine3().trim(): "");
	 * returnDetailsBean.setBillToCity(rtrnDtlsFrmList.getBillToCity() != null ?
	 * rtrnDtlsFrmList.getBillToCity().trim(): "");
	 * returnDetailsBean.setBillToState(rtrnDtlsFrmList.getBillToState() != null
	 * ? rtrnDtlsFrmList.getBillToState().trim(): "");
	 * returnDetailsBean.setBillToZipCode(rtrnDtlsFrmList.getBillToZipCode() !=
	 * null ? rtrnDtlsFrmList.getBillToZipCode().trim(): ""); }
	 * 
	 * public List<ReturnOrderLinesBean>
	 * convertReturnOrderLines(List<ReturnOrderLinesEntityBean>
	 * orderLinesEntityList){
	 * 
	 * List<ReturnOrderLinesBean> orderLinesWthProdctList = new
	 * ArrayList<ReturnOrderLinesBean>();
	 * 
	 * for (ReturnOrderLinesEntityBean orderLinesEntityBean :
	 * orderLinesEntityList) {
	 * 
	 * ReturnOrderLinesBean orderLines = new ReturnOrderLinesBean(); ProductBean
	 * product = new ProductBean();
	 * 
	 * orderLines.setShipmentType(orderLinesEntityBean.getShipmentType() != null
	 * ? orderLinesEntityBean.getShipmentType().trim() : "");
	 * 
	 * orderLines.setOrderLineKey(orderLinesEntityBean.getOrderLineKey() != null
	 * ? orderLinesEntityBean.getOrderLineKey().trim(): "");
	 * 
	 * orderLines.setOrderLineNo(orderLinesEntityBean.getOrderLineNo() != null ?
	 * orderLinesEntityBean.getOrderLineNo().trim(): "");
	 * orderLines.setReturnFulfillmentMtd(orderLinesEntityBean.
	 * getReturnFulfillmentMtd() != null ?
	 * orderLinesEntityBean.getReturnFulfillmentMtd().trim(): "");
	 * orderLines.setType(orderLinesEntityBean.getType() != null ?
	 * orderLinesEntityBean.getType().trim(): "");
	 * orderLines.setOrderQty(orderLinesEntityBean.getOrderQty() != null ?
	 * orderLinesEntityBean.getOrderQty(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setOriginalOrderedQty(orderLinesEntityBean.
	 * getOriginalOrderedQty() != null ?
	 * orderLinesEntityBean.getOriginalOrderedQty(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setExtendedPrice(orderLinesEntityBean.getExtendedPrice() !=
	 * null ? orderLinesEntityBean.getExtendedPrice(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * orderLines.setReshipLineType(orderLinesEntityBean.getReshipLineType() !=
	 * null ? orderLinesEntityBean.getReshipLineType().trim(): "");
	 * orderLines.setDerivedFromOrderLine(orderLinesEntityBean.
	 * getDerivedFromOrderLine() != null ?
	 * orderLinesEntityBean.getDerivedFromOrderLine().trim(): "");
	 * orderLines.setReturnOrderHeaderKey(orderLinesEntityBean.
	 * getReturnOrderHeaderKey() !=null ?
	 * orderLinesEntityBean.getReturnOrderHeaderKey().trim(): "");
	 * 
	 * product.setCustomImage(orderLinesEntityBean.getCustomImage() != null ?
	 * orderLinesEntityBean.getCustomImage().trim(): "");
	 * product.setProductDesc(orderLinesEntityBean.getProductDesc() != null ?
	 * orderLinesEntityBean.getProductDesc().trim(): "");
	 * product.setProductDisplayName(orderLinesEntityBean.getProductDisplayName(
	 * ) != null ? orderLinesEntityBean.getProductDisplayName().trim(): "");
	 * product.setProductSKU(orderLinesEntityBean.getProductSKU() != null ?
	 * orderLinesEntityBean.getProductSKU().trim(): "");
	 * product.setVendorModel(orderLinesEntityBean.getVendorModel() != null ?
	 * orderLinesEntityBean.getVendorModel().trim(): "");
	 * product.setProductUOM(orderLinesEntityBean.getProductUOM() != null ?
	 * orderLinesEntityBean.getProductUOM().trim(): "");
	 * product.setPniJobId(orderLinesEntityBean.getPniJobId() != null ?
	 * orderLinesEntityBean.getPniJobId().trim(): "");
	 * product.setUnitPrice(orderLinesEntityBean.getUnitPrice() != null ?
	 * orderLinesEntityBean.getUnitPrice(): new
	 * BigDecimal(CommonConstants.ZERO_STRING));
	 * 
	 * orderLines.setProduct(product); orderLinesWthProdctList.add(orderLines);
	 * }
	 * 
	 * return orderLinesWthProdctList; }
	 */
}
