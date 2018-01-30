package com.staples.dashboard.app.sdc.service.mappers;

import java.util.List;

import com.staples.dashboard.app.sdc.dao.bean.OrderDetailEntity;
import com.staples.dashboard.app.sdc.vo.CustomerOrder;
import com.staples.dashboard.app.sdc.vo.LineCouponBean;
import com.staples.dashboard.app.sdc.vo.LineDiscountBean;

public interface IOrderDetailMapper {
	
	public void convertSalesHeader(CustomerOrder orderDetailBean, List<OrderDetailEntity> resultListHdr);

	public void addLineDiscountToItem(CustomerOrder order, List<LineDiscountBean> lineDiscountEntityList, List<LineCouponBean> lineCouponEntityList);

	/*public void convertTotalContainerData(List<TotalContainerBean> containerDtlList, ContainerResponseBean containerResponseBean);

	public List<ShipmentBean> convertTotalShipmentInfo(List<TotalShipmentBean> totalShipmentInfo,String orderNo);
	public void convertShipmentHeader(List<Object[]> resultListHdr, ShipmentResponseBean shipmentResponseBean);
	
	
	public List<OrderLinesBean> convertOrderLines(List<OrderLinesEntityBean> orderLinesEntityList);
	public void convertReturnSalesHeader (String orderNo, ReturnDetailsBean returnDetailsBean, List<ReturnDetailsEntityBean> returnDetailsEntityList);
	public List<ReturnOrderLinesBean> convertReturnOrderLines(List<ReturnOrderLinesEntityBean> orderLinesEntityList);
	*/
}