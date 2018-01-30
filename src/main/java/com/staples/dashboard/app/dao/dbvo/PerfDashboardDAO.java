package com.staples.dashboard.app.dao.dbvo;

import java.util.List;

import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;
import com.staples.dashboard.exception.DashboardException;

public interface PerfDashboardDAO {

	public List<PerfDashboardCategoryVO> getSalesDataBossCoreCategory(String custNum, List<String> allCategoryList) throws DashboardException;

	public List<PerfDashboardCategoryVO> getColumnAndDoughnutAllCategoryList(String custNum, List<String> allCategoryList) throws DashboardException;

	public List<PerfDashboardCategoryVO> getCustInsightDataIfExist(String custNum, List<String> allCategoryList) throws DashboardException;
	
	public List<PerfDashboardCategoryVO> getWIREnabledFlag(String custNum) throws DashboardException;
}