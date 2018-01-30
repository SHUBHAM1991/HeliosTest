package com.staples.dashboard.app.sdc.controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.staples.dashboard.app.sdc.service.CustomerSalesService;
import com.staples.dashboard.app.sdc.vo.CategoryDivisionResponse;
import com.staples.dashboard.app.sdc.vo.CategoryPercentageResponse;
import com.staples.dashboard.app.sdc.vo.CustomerInfo;
import com.staples.dashboard.app.sdc.vo.CustomerLatestOrder;
import com.staples.dashboard.app.sdc.vo.CustomerOrdersResponse;
import com.staples.dashboard.app.sdc.vo.CustomerRepResponse;
import com.staples.dashboard.app.sdc.vo.FiscalCategoryCalResponse;
import com.staples.dashboard.app.sdc.vo.FiscalYearResponse;
import com.staples.dashboard.app.sdc.vo.OrderInfo;
import com.staples.dashboard.app.sdc.vo.ParentChildInfo;
import com.staples.dashboard.app.sdc.vo.SalesOrderResponse;
import com.staples.dashboard.app.sdc.vo.SalesShipmentResponse;
import com.staples.dashboard.app.sdc.vo.ShipmentOrdersResponse;
import com.staples.dashboard.app.vo.CustProfileVO;
import com.staples.dashboard.app.vo.CustomerDataVo;
import com.staples.dashboard.app.vo.HawkeyeDetailsVo;
import com.staples.dashboard.app.vo.JQueryDataTableInputDTO;
import com.staples.dashboard.app.vo.JQueryDataTableOutputDTO;
import com.staples.dashboard.app.vo.JQueryDataTableOutputDTOSAM;
import com.staples.dashboard.app.vo.SavingsVo;
import com.staples.dashboard.app.vo.SuperUserDetailsVo;
import com.staples.dashboard.exception.DashboardException;

@Controller
public class CustomerActivityController {

	private static final String DATE_SEPERATOR = "-";

	@Autowired
	private CustomerSalesService customerSalesService;

	private static List<String> strMonths = Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP",
			"OCT", "NOV", "DEC");

	private static final Logger logger = Logger.getLogger(CustomerActivityController.class);

	/**
	 * 
	 * @param sbuName
	 * @param catogeryId
	 * @param startDate
	 * @param endDate
	 * @param customer_id
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "sales/customer/{customer_id}/transactions", method = RequestMethod.GET)
	public @ResponseBody CustomerOrdersResponse getCustomerTransations(@RequestParam(required = true) String sbuName,
			@RequestParam(required = false) String catogeryId, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @PathVariable String customer_id, HttpServletResponse res) {

		OrderInfo customer = new OrderInfo();
		customer.setCustomer_id(customer_id);
		customer.setSbuName(sbuName);
		customer.setCatogeryId(catogeryId);

		Calendar calendar = Calendar.getInstance();

		int endYear = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);
		String endMonth = strMonths.get(month);

		int startYear = endYear - 2;
		String startMonth = endMonth;

		if (false == validateDate(startDate, endDate)) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST, "invalid date format");
			throw new DashboardException();
		}

		if (null != startDate && false == startDate.equals("")) {
			startMonth = startDate.substring(0, startDate.indexOf('-'));
			startYear = Integer.parseInt(startDate.substring(startDate.indexOf('-') + 1));
			endYear = startYear + 2;
			endMonth = startMonth;
		}
		if (null != endDate && false == endDate.equals("")) {
			endYear = Integer.parseInt(endDate.substring(endDate.indexOf('-') + 1));
			endMonth = endDate.substring(0, endDate.indexOf('-'));
		}
		int monthIndex = strMonths.indexOf(endMonth);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, endYear);
		cal.set(Calendar.MONTH, monthIndex);
		int lengthOfMonth = cal.getActualMaximum(Calendar.DATE);

		customer.setStartDate(01 + DATE_SEPERATOR + startMonth + DATE_SEPERATOR + startYear);
		customer.setEndDate(lengthOfMonth + DATE_SEPERATOR + (endMonth) + DATE_SEPERATOR + endYear);

		return customerSalesService.getCustomerTransactions(customer);
	}
	
	/**
	 * 
	 * @param sbuName
	 * @param customer_id
	 * @param order_no
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/{customer_id}/{order_no}/info", method = RequestMethod.GET)
	public @ResponseBody SalesOrderResponse getOrderDetails(@RequestParam(required = true) String sbuName,
			@PathVariable String customer_id, @PathVariable String order_no, HttpServletResponse res) {

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCustomer_id(customer_id);
		orderInfo.setSbuName(sbuName);
		orderInfo.setOrderNo(order_no);

		return customerSalesService.getCustomerOrderDetails(orderInfo);

	}
  /**
   * 
   * @param representive_id
   * @param sbuName
   * @param res
   * @return
   */
	@RequestMapping(value = "sales/representive/{representive_id}/customer/list", method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody CustomerRepResponse getAllRepCustomers(
			@PathVariable String representive_id,
			@RequestParam(required=false) String sbuName, HttpServletResponse res) {

		return customerSalesService.getAllRepCustomers(representive_id);

	}
	
	
	@RequestMapping(value = "sales/representive/{representive_id}/customer/list1", method = {RequestMethod.GET , RequestMethod.POST}, produces = "application/json")
	@ResponseBody
	@JsonCreator
	public String getAllRepCustomersSam(
			@PathVariable String representive_id,
			@RequestParam(required=false) String sbuName, HttpServletRequest request) {

		JQueryDataTableInputDTO dataTableInput = null;
		String iDisplayStart = request.getParameter("iDisplayStart");
		String iDisplayLength = request.getParameter("iDisplayLength");
		String sSearch = request.getParameter("sSearch");
		String iSortingCols = request.getParameter("iSortingCols");
		String iSortCol_0 = request.getParameter("iSortCol_0");
		String sSortDir_0 = request.getParameter("sSortDir_0");
		String sEcho = request.getParameter("sEcho");
	    if (sEcho != null && !sEcho.equals("")) {
		dataTableInput = new JQueryDataTableInputDTO();
		dataTableInput.setiDisplayLength(Integer
				.valueOf(iDisplayLength));
		dataTableInput.setiDisplayStart(Integer.valueOf(iDisplayStart));
		if (iSortCol_0 != null) {
			dataTableInput.setiSortCol_0(CustomerInfo
					.getCustDataMapSAM(iSortCol_0));
		}
		dataTableInput.setsSortDir_0(sSortDir_0);
		dataTableInput.setsSearch(sSearch);
		if (iSortingCols != null && !iSortingCols.equals("")) {
			dataTableInput.setiSortingCols(Integer
					.valueOf(iSortingCols));
		}
	}

	JQueryDataTableOutputDTOSAM returnList = new JQueryDataTableOutputDTOSAM();
	
	CustomerRepResponse customerRepResponse=customerSalesService.getAllSamCustomers(representive_id,dataTableInput);
	
	returnList.setAaData(customerRepResponse.getCustomerInfo());
	returnList.setiTotalRecords(customerRepResponse.getTotalCount());
	returnList.setiTotalDisplayRecords(customerRepResponse
			.getTotalCount());

	if (sEcho != null && !sEcho.equals("")) {
		returnList.setsEcho(Integer.valueOf(sEcho));
	}
	Gson gson = new Gson();
	return gson.toJson(returnList);

	}
	/**
	 * 
	 * @param representive_id
	 * @param custNum
	 * @param sbuName
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/representive/{representive_id}/customer/{custNum}", method = {RequestMethod.GET , RequestMethod.POST})
	public @ResponseBody CustomerRepResponse getAllRepCustomers(
			@PathVariable String representive_id,@PathVariable String custNum,
			@RequestParam(required=false) String sbuName, HttpServletResponse res) {

		return customerSalesService.getCustomerDetails(representive_id,custNum);

	}
	
	/**
	 * 
	 * @param sbuName
	 * @param year
	 * @param customer_id
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/{customer_id}/shipment/info", method = RequestMethod.GET)
	public @ResponseBody SalesShipmentResponse getSalesShipmentDetails(@RequestParam(required = false) String sbuName,
			@RequestParam(required = false) String year,
			@PathVariable String customer_id, HttpServletResponse res) {

		return customerSalesService.getCustomerShipmentDetails(customer_id, year);

	}
	
	/**
	 * 
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/categories/list", method = RequestMethod.GET)
	public @ResponseBody CategoryDivisionResponse getSalesCategories(HttpServletResponse res) {
		return customerSalesService.getCategories(null);

	}

	/**
	 * 
	 * @param sbuName
	 * @param customer_id
	 * @param res
	 * @return
	 */

	@RequestMapping(value = "sales/customer/{customer_id}/childs/info", method = RequestMethod.GET)
	public @ResponseBody ParentChildInfo getParentChildsInfo(@RequestParam(required = false) String sbuName,
			@PathVariable String customer_id, HttpServletResponse res) {

		return customerSalesService.getParentChildInfo(customer_id, sbuName);

	}
	
	/**
	 * 
	 * @param year
	 * @param customer_id
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/{customer_id}/fiscalmonth/categories/sum", method = RequestMethod.GET)
	public @ResponseBody FiscalCategoryCalResponse getCategoryFiscalMonthCals(@RequestParam(required = true) String year,
			@PathVariable String customer_id, HttpServletResponse res) {

		return customerSalesService.getCcategoryFiscalMonthCals(customer_id, year);

	}
	
	/**
	 * 
	 * @param year
	 * @param customer_id
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/{customer_id}/fiscalyear/categories/percetage", method = RequestMethod.GET)
	public @ResponseBody CategoryPercentageResponse getCategoryPercentage(@RequestParam(required = true) String year,
			@PathVariable String customer_id, HttpServletResponse res) {

		return customerSalesService.getFiscalCategoryPercentage(customer_id, year);

	}
	
	/**
	 * 
	 * @param sbuName
	 * @param customer_id
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/{customer_id}/fiscalyears/list", method = RequestMethod.GET)
	public @ResponseBody FiscalYearResponse listLastFiscalYears(@RequestParam(required = false) String sbuName,
			@PathVariable String customer_id, HttpServletResponse res) {
		if(null !=customer_id && !("".equals(customer_id)) && customer_id.length() < 10 ){
			String format = String.format("%%0%dd", 10);
			customer_id= String.format(format, Integer.parseInt(customer_id));
		}
		return customerSalesService.listLastFiscalYears(customer_id,sbuName);

	}
	
	/**
	 * 
	 * @param sbuName
	 * @param customer_id
	 * @param location
	 * @param res
	 * @return
	 */
	
	@RequestMapping(value = "sales/customer/shipment/orders/list/{customer_id}", method = RequestMethod.POST)
	public @ResponseBody ShipmentOrdersResponse listShipmentOrdersList(@RequestParam("sbuName") String sbuName,
			@PathVariable("customer_id") String customer_id, @RequestParam("shipToLoc") String shipToLoc) {
		return customerSalesService.listShipmentOrdersList(customer_id,sbuName,shipToLoc);

	}
	

	private boolean validateDate(String startDate, String endDate) {

		if (false == (validate(startDate) && validate(endDate))) {
			return false;
		}
		return true;

	}

	private boolean validate(String startDate) {
		if (null != startDate && false == startDate.equals("")) {
			int indexOf = startDate.indexOf('-');
			if (indexOf == -1) {
				return false;
			}
			String year = startDate.substring(indexOf + 1);
			if (year == null || (year.length() == 2 || year.length() == 4) == false) {
				return false;
			}
			String startMonth = startDate.substring(0, indexOf);
			if (startMonth == null || strMonths.indexOf(startMonth) == -1) {
				return false;
			}
		}
		return true;
	}

	
	@ResponseBody 
	@RequestMapping( method={RequestMethod.POST,RequestMethod.GET}, value={"/getHawkeyeDetails/{customerId}"}, produces="application/json")
	public String getHawkeyeDetails(@PathVariable("customerId") String custId) throws DashboardException, Exception{
	 try{
		 HawkeyeDetailsVo hawkeyeDetailsVo =customerSalesService.getHawkeyeDetails(custId);
		Gson gson=new Gson();
		return gson.toJson(hawkeyeDetailsVo);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
  }

	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws DashboardException
	 * @throws Exception
	 */
	
	@ResponseBody 
	@RequestMapping( method={RequestMethod.GET}, value={"/sales/customer/{customerId}/latestorderdate"}, produces="application/json")
	public CustomerLatestOrder getCustomerLatestOrderDate(@PathVariable("customerId") String customerId) throws DashboardException, Exception{
	 try{
		  String customerLatestOrderDate = customerSalesService.getCustomerLatestOrderDate(customerId);
		  CustomerLatestOrder latestOrder=new CustomerLatestOrder();
		  latestOrder.setLatestOrderDate(customerLatestOrderDate);
		  return latestOrder;
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw new DashboardException(e);
	}
  }

	
/**
 * Method implementation to fetch super user high level data.
 * 
 * @param String
 *            custNum
 * @return String
 */
@RequestMapping(value = "/getSuperUSerHighLevelDataSam/{cid}", method = {
		RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
@ResponseBody
public String openSuperUserDetailsSam(@PathVariable("cid") String custNum)
		throws DashboardException, Exception {
	try {
		Gson gson = new Gson();
		SuperUserDetailsVo  superUserDetailsVo = null;

		if (null != custNum) {

			superUserDetailsVo= customerSalesService.getSuperUserDetailsSam(custNum);

		}
		return gson.toJson(superUserDetailsVo);
	} catch (DashboardException de) {
		throw de;
	} catch (Exception e) {
		throw e;
	}
  }
}