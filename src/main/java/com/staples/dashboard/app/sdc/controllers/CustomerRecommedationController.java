package com.staples.dashboard.app.sdc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.staples.dashboard.app.sdc.service.CustomerRecommendationService;
import com.staples.dashboard.app.sdc.vo.RecommendationVO;

@Controller
public class CustomerRecommedationController {
	
	@Autowired
	private CustomerRecommendationService customerRecommendationService;

	private static final Logger logger = Logger.getLogger(CustomerRecommedationController.class);

	//@RequestMapping(value = "recommendation/customer/{customer_id}/rep/{rep_id}/user/{loggedUser}", method = RequestMethod.GET)
	@RequestMapping(value = "recommendation/customer/{customer_id}/user/{loggedUser}/level/{level}", method = RequestMethod.GET)
	public @ResponseBody List<RecommendationVO> getCustomerRecommendation(@PathVariable String customer_id, @PathVariable String loggedUser, @PathVariable String level, HttpServletResponse res) {

		return customerRecommendationService.getCustomerRecommendation(customer_id, loggedUser, level);

	}
}
