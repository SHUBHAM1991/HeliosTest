package com.staples.dashboard.app.sdc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.staples.dashboard.app.sdc.vo.RewardsStatementVO;
import com.staples.dashboard.app.sdc.vo.RewardsVO;
import com.staples.dashboard.exception.DashboardException;

@RestController()
@RequestMapping(value="/rewards")
public class RewardsActionController {
	
	private static final Logger logger = Logger
			.getLogger(RewardsActionController.class);
	
	private @Value("${rewardStatements.url}")
	String rewardStatements;
	
	private @Value("${rewardsForStatement.url}")
	String rewardsForStatement;
	
	private @Value("${categorySaving.url}")
	String categorySaving;

	@RequestMapping(value = { "/findRewardStatements/{custId}" }, method = {
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public RewardsVO[] findRewardStatements(@PathVariable("custId") String custNum, HttpServletRequest request, @RequestBody RewardsStatementVO rewardsStatementVO )
			throws DashboardException, Exception {
		try {
			String serviceURL = rewardStatements;
			if (custNum != null) {
				
				serviceURL += "/"+custNum;
				//openApi(serviceURL);
				return callPostMethod(serviceURL, rewardsStatementVO);

			} 
			//return getResponse(serviceURL, rewardsStatementVO);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	@RequestMapping(value = { "/findRewardStatements/{custId}" }, method = {
			RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public RewardsVO[]findRewardStatements(@PathVariable("custId") String custNum, HttpServletRequest request )
			throws DashboardException, Exception {
		try {
			String serviceURL = rewardStatements;
			if (custNum != null) {
				
				serviceURL += "/"+custNum;
				return callGetMethod(serviceURL);

			} 
			//return getResponse(serviceURL, rewardsStatementVO);

		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@RequestMapping(value = { "/findRewardForStatement/{custId}/{statementNum}" }, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public RewardsVO[] findRewardsForStatement(@PathVariable("custId") String custNum, @PathVariable("statementNum") String statementNum , HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String serviceURL = rewardsForStatement;
			if (custNum != null) {
				serviceURL += "/"+custNum+"/statementCode/"+statementNum;
				return callGetMethod(serviceURL);
			} 
			//return getHTTPResponseForRewardsStatement(serviceURL);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	@RequestMapping(value = { "/findCategorySavings/{custId}" }, method = {
			RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public RewardsVO[] findCategorySavings(@PathVariable("custId") String custNum, HttpServletRequest request)
			throws DashboardException, Exception {
		try {
			String serviceURL = categorySaving;
			if (custNum != null) {
				serviceURL += "/"+custNum;
				return callGetMethod(serviceURL);
			} 
			//return getHTTPResponseForRewardsStatement(serviceURL);
		} catch (DashboardException de) {
			throw de;
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	public RewardsVO[] callPostMethod(String url, RewardsStatementVO rewardsStatementVO){
		String uri = url;
		logger.debug("uri = " + uri);
		RewardsVO[] outputVO=null;

		// create request body
		JSONObject request = new JSONObject();
		request.put("query", rewardsStatementVO.getQuery());

		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		
		
		try{
			// send request and parse result
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RewardsVO[]> result = restTemplate.exchange(uri, HttpMethod.POST, entity, RewardsVO[].class);
			logger.info("POST API OUTPUT: " + result.getBody());
			outputVO = result.getBody();
			
		}catch(HttpClientErrorException e){
			logger.error("Inside callPostMethod: " + e.getMessage());
			e.printStackTrace();
		}
		return outputVO;
	}
	
	public RewardsVO[] callGetMethod(String url){
		String uri = url;
		logger.debug("uri = " + uri);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RewardsVO[] outputVO=null;
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		try{
			ResponseEntity<RewardsVO[]> result = restTemplate.exchange(uri, HttpMethod.GET, entity, RewardsVO[].class);
			logger.info("GET API OUTPUT: " + result.getBody());
			outputVO = result.getBody();
		}catch(HttpClientErrorException e){
			logger.error("Inside callGetMethod: " + e.getMessage());
			e.printStackTrace();
			//throw new CustomEmailFraudException("Error in calling Responsys Register service For Status");
		}
		//return outputVO.getResult();
		return outputVO;
	}
}
