package com.staples.dashboard.app.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;
import com.staples.dashboard.exception.DashboardException;

public interface PerfDashboardService {

	public Map<String, BigDecimal> getSalesDataBossCoreCategory(String custNum) throws DashboardException;

	public List<PerfDashboardCategoryVO> getColumnAndDoughnutCategoryList(String custNum) throws DashboardException;;

	public List<PerfDashboardCategoryVO> getCustInsightDataIfExist(String custNum) throws DashboardException;
	
	public String isDisplayWIRFlag(String custNum) throws DashboardException;
}
