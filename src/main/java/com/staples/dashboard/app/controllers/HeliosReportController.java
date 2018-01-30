package com.staples.dashboard.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.staples.dashboard.app.constants.ReportConstants;
import com.staples.dashboard.app.service.ContractDashboardService;
import com.staples.dashboard.dto.SavingsDetailDTO;
import com.staples.dashboard.exception.DashboardException;

@Controller
public class HeliosReportController {
	
	private static final Logger LOGGER = Logger
			.getLogger(HeliosReportController.class);
	@Autowired
	private ContractDashboardService contractService;
	
	
	
	@RequestMapping(value = ReportConstants.SAVINGS_REPORT_DOWNLOAD_MAPPING, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView generateSavingsReport(@RequestParam(ReportConstants.CUSTOMER_NUMBER) String custNum, HttpServletRequest request) throws DashboardException, Exception {
        try{
		
        List<SavingsDetailDTO> savingsList = contractService.generateSavingsReport(custNum);
        request.setAttribute(ReportConstants.CUSTOMER_NUMBER, custNum);
 
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView(ReportConstants.SAVINGS_REPORT_RETURN, ReportConstants.SAVINGS_LIST_RETURN_PARAM, savingsList);
        
        }catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
    }
	
	@RequestMapping(value = ReportConstants.SAM_SAVINGS_REPORT_DOWNLOAD_MAPPING, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView generateSamSavingsReport(@RequestParam(ReportConstants.CUSTOMER_NUMBER) String custNum, HttpServletRequest request) throws DashboardException, Exception {
        try{
		
        List<SavingsDetailDTO> savingsList = contractService.generateSamSavingsReport(custNum);
        request.setAttribute(ReportConstants.CUSTOMER_NUMBER, custNum);
 
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView(ReportConstants.SAM_SAVINGS_REPORT_RETURN, ReportConstants.SAM_SAVINGS_LIST_RETURN_PARAM, savingsList);
        
        }catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
    }

}
