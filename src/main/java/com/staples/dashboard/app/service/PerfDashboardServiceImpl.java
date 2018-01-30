package com.staples.dashboard.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.dao.dbvo.PerfDashboardDAO;
import com.staples.dashboard.app.utilities.Constants;
/*import com.staples.dashboard.app.vo.BenefitUtilizationVO;*/
import com.staples.dashboard.app.vo.PerfDashboardCategoryVO;
/*import com.staples.dashboard.app.vo.PerfDashboardPremiumVO;*/
import com.staples.dashboard.exception.DashboardException;

@Service
public class PerfDashboardServiceImpl implements PerfDashboardService {
	private static final Logger logger = Logger.getLogger(PerfDashboardServiceImpl.class);

	@Autowired
	private PerfDashboardDAO perfDashboardDao;

	public PerfDashboardDAO getPerfDashboardDao() {
		return perfDashboardDao;
	}

	public void setPerfDashboardDao(PerfDashboardDAO perfDashboardDao) {
		this.perfDashboardDao = perfDashboardDao;
	}

	
	@Override
	public Map<String, BigDecimal> getSalesDataBossCoreCategory(String custNum)
			throws DashboardException {
		try {
			List<String> allCategoryList = getAllCategoryList();
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = perfDashboardDao.getSalesDataBossCoreCategory(custNum, allCategoryList);

			logger.debug("perfDashboardCategoryVOList getSalesDataBossCoreCategory:--> "+ perfDashboardCategoryVOList);

			BigDecimal allCurrRollMonthCoreSales = BigDecimal.ZERO; // CURR_ROLL_3_MONTH_SLS
			BigDecimal allCurrRollMonthBossSales = BigDecimal.ZERO; // CURR_ROLL_3_MONTH_SLS
			BigDecimal totalCurrRollMonthSales = BigDecimal.ZERO;  //sum of above 2 ie sum of CURR_ROLL_3_MONTH_SLS
			BigDecimal totalPriorRollMonthSales = BigDecimal.ZERO; //sum of PRIOR_ROLL_3_MONTH_SLS
			BigDecimal currGrowthPerc = BigDecimal.ZERO;	
			
			BigDecimal allCurrYrToDtCoreSales = BigDecimal.ZERO; // CURR_YEAR_TO_DATE_SLS
			BigDecimal allCurrYrToDtBossSales = BigDecimal.ZERO; // CURR_YEAR_TO_DATE_SLS
			BigDecimal totalCurrYrToDtSales = BigDecimal.ZERO;  //sum of above 2 ie sum of CURR_YEAR_TO_DATE_SLS
			BigDecimal totalPriorYrToDtSales = BigDecimal.ZERO;	//sum of PRIOR_YEAR_TO_DATE_SLS
			BigDecimal yrToDtGrowthPerc = BigDecimal.ZERO;

			Map<String, BigDecimal> bossCoreMap = new HashMap<String, BigDecimal>();
			
			for (PerfDashboardCategoryVO perfDashboardCategoryVO : perfDashboardCategoryVOList) {
				
				//Add all PriorRollMonthSales including - All other products
				totalPriorRollMonthSales = totalPriorRollMonthSales.add(new BigDecimal((null != perfDashboardCategoryVO.getPriorRollMonthSales() 
						&& perfDashboardCategoryVO.getPriorRollMonthSales() !="") ? perfDashboardCategoryVO.getPriorRollMonthSales() : "0")).setScale(0, RoundingMode.HALF_UP);
				totalPriorYrToDtSales = totalPriorYrToDtSales.add(new BigDecimal((null != perfDashboardCategoryVO.getPriorYrToDateSales() 
						&& perfDashboardCategoryVO.getPriorYrToDateSales() !="") ? perfDashboardCategoryVO.getPriorYrToDateSales() : "0")).setScale(0, RoundingMode.HALF_UP);
				
				//Total Sales - totalCurrRollMonthSales = Add all categories including - All other products
				totalCurrRollMonthSales = totalCurrRollMonthSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrRollMonthSales() 
						&& perfDashboardCategoryVO.getCurrRollMonthSales() !="") ? perfDashboardCategoryVO.getCurrRollMonthSales() : "0")).setScale(0, RoundingMode.HALF_UP);
				totalCurrYrToDtSales = totalCurrYrToDtSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrYrToDateSales() 
						&& perfDashboardCategoryVO.getCurrYrToDateSales() !="") ? perfDashboardCategoryVO.getCurrYrToDateSales() : "0")).setScale(0, RoundingMode.HALF_UP);
				
				
				if (perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_CORE_OFFICE)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_CORE_PAPER)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_CORE_TONER)){

					allCurrRollMonthCoreSales = allCurrRollMonthCoreSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrRollMonthSales() 
							&& perfDashboardCategoryVO.getCurrRollMonthSales() !="") ? perfDashboardCategoryVO.getCurrRollMonthSales() : "0")).setScale(0, RoundingMode.HALF_UP);
					
					allCurrYrToDtCoreSales = allCurrYrToDtCoreSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrYrToDateSales() 
							&& perfDashboardCategoryVO.getCurrYrToDateSales() !="") ? perfDashboardCategoryVO.getCurrYrToDateSales() : "0")).setScale(0, RoundingMode.HALF_UP);

				}else if (perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSSS_FACILITIES)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSS_FURNITURE)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSSS_TECHNOLOGY)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSS_PRINT)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSSS_PROMO)
						|| perfDashboardCategoryVO.getCategoryName().equalsIgnoreCase(Constants.CAT_BOSS_MAILSHIP)){

					allCurrRollMonthBossSales = allCurrRollMonthBossSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrRollMonthSales() 
							&& perfDashboardCategoryVO.getCurrRollMonthSales() !="") ? perfDashboardCategoryVO.getCurrRollMonthSales() : "0")).setScale(0, RoundingMode.HALF_UP);
					
					allCurrYrToDtBossSales = allCurrYrToDtBossSales.add(new BigDecimal((null != perfDashboardCategoryVO.getCurrYrToDateSales() 
							&& perfDashboardCategoryVO.getCurrYrToDateSales() !="") ? perfDashboardCategoryVO.getCurrYrToDateSales() : "0")).setScale(0, RoundingMode.HALF_UP);
					
				}
			}
			
			currGrowthPerc = calculateGrowthPercentage (totalCurrRollMonthSales, totalPriorRollMonthSales);
			yrToDtGrowthPerc = calculateGrowthPercentage (totalCurrYrToDtSales, totalPriorYrToDtSales);	
			
			
			
			/*
			if(totalCurrYrToDtSales.compareTo(BigDecimal.ZERO) == 0 && totalPriorYrToDtSales.compareTo(BigDecimal.ZERO) == 0){
				yrToDtGrowthPerc = BigDecimal.ZERO;
			}else if(totalPriorYrToDtSales.compareTo(BigDecimal.ZERO) == 0){
				yrToDtGrowthPerc = totalCurrYrToDtSales;
			}else{
				yrToDtGrowthPerc = (totalCurrYrToDtSales.subtract(totalPriorYrToDtSales)).multiply(new BigDecimal(100)).divide(totalPriorYrToDtSales, 2, RoundingMode.HALF_UP); 
			}
			
			*/
			
			bossCoreMap.put("CORE", allCurrRollMonthCoreSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("BOSS", allCurrRollMonthBossSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("totalCurrRollMonthSales", totalCurrRollMonthSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("totalPriorRollMonthSales", totalPriorRollMonthSales.setScale(0, RoundingMode.HALF_UP));			
			bossCoreMap.put("currGrowthPerc", currGrowthPerc.setScale(0, RoundingMode.HALF_UP));
			
			bossCoreMap.put("CORE1", allCurrYrToDtCoreSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("BOSS1", allCurrYrToDtBossSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("totalCurrYrToDtSales", totalCurrYrToDtSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("totalPriorYrToDtSales", totalPriorYrToDtSales.setScale(0, RoundingMode.HALF_UP));
			bossCoreMap.put("yrToDtGrowthPerc", yrToDtGrowthPerc.setScale(0, RoundingMode.HALF_UP));		

			logger.debug("Final bossCoreMap:--------> "+ bossCoreMap);
			return bossCoreMap;
		
		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}
	

	private BigDecimal calculateGrowthPercentage(BigDecimal totalCurrSales, BigDecimal totalPriorSales) {

		BigDecimal growthPerc = BigDecimal.ZERO;
		//Perc = ((curr-Prior)/Prior)*100 applies only if curr and prior are both > 0
		if(totalCurrSales.compareTo(BigDecimal.ZERO) <= 0){
			if (totalPriorSales.compareTo(BigDecimal.ZERO) <= 0){
				growthPerc = BigDecimal.ZERO;	
			} else if (totalPriorSales.compareTo(BigDecimal.ZERO) > 0) {
				growthPerc = new BigDecimal(-100);						
			}
				
		}else {  //curr sales are greater than zero
			if (totalPriorSales.compareTo(BigDecimal.ZERO) <= 0){
				growthPerc = new BigDecimal(100);	
			} else {
				growthPerc = (totalCurrSales.subtract(totalPriorSales)).multiply(new BigDecimal(100)).divide(totalPriorSales, 2, RoundingMode.HALF_UP);
			}
		}
		
		return growthPerc;
		
	}
	
	@Override
	public List<PerfDashboardCategoryVO> getColumnAndDoughnutCategoryList(String custNum)
			throws DashboardException {
		try {
			List<String> allCategoryList = getAllCategoryList();
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = perfDashboardDao.getColumnAndDoughnutAllCategoryList(custNum, allCategoryList);
			logger.debug("perfDashboardCategoryVOList getColumnAndDoughnutCategoryList:--> "+ perfDashboardCategoryVOList);
			
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOListNew = orderAndMapByCategoryName(perfDashboardCategoryVOList);
			
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOListNewRound = rondingOffSalesColumnAndDoughnut(perfDashboardCategoryVOListNew);
			return perfDashboardCategoryVOListNewRound;

		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}
	

	private List<PerfDashboardCategoryVO> rondingOffSalesColumnAndDoughnut(List<PerfDashboardCategoryVO> perfDashboardCategoryVOListNew) {
		BigDecimal rollMonthPenAmount = BigDecimal.ZERO; 
		BigDecimal rollMonthPenPercent = BigDecimal.ZERO; 
		BigDecimal currYrToDateSales = BigDecimal.ZERO; 
		BigDecimal yrToDateSalesPercent = BigDecimal.ZERO; 
		
		for (PerfDashboardCategoryVO perfDashboardCategoryVO : perfDashboardCategoryVOListNew) {
			if(null != perfDashboardCategoryVO.getRollMonthPenAmount() && "" != perfDashboardCategoryVO.getRollMonthPenAmount()){
				rollMonthPenAmount = new BigDecimal(perfDashboardCategoryVO.getRollMonthPenAmount()).setScale(0, RoundingMode.HALF_UP);
				perfDashboardCategoryVO.setRollMonthPenAmount(rollMonthPenAmount.toString());
			}else{
				perfDashboardCategoryVO.setRollMonthPenAmount("0");
			}
			
			if(null != perfDashboardCategoryVO.getRollMonthPenPercent() && "" != perfDashboardCategoryVO.getRollMonthPenPercent()){
				rollMonthPenPercent = new BigDecimal(perfDashboardCategoryVO.getRollMonthPenPercent()).setScale(0, RoundingMode.HALF_UP);
				perfDashboardCategoryVO.setRollMonthPenPercent(rollMonthPenPercent.toString());
			}else{
				perfDashboardCategoryVO.setRollMonthPenPercent("0");
			}
			
			if(null != perfDashboardCategoryVO.getCurrYrToDateSales() && "" != perfDashboardCategoryVO.getCurrYrToDateSales()){
				currYrToDateSales = new BigDecimal(perfDashboardCategoryVO.getCurrYrToDateSales()).setScale(0, RoundingMode.HALF_UP);
				perfDashboardCategoryVO.setCurrYrToDateSales(currYrToDateSales.toString());
			}else{
				perfDashboardCategoryVO.setCurrYrToDateSales("0");
			}
			
			if(null != perfDashboardCategoryVO.getYrToDateSalesPercent() && "" != perfDashboardCategoryVO.getYrToDateSalesPercent()){
				yrToDateSalesPercent = new BigDecimal(perfDashboardCategoryVO.getYrToDateSalesPercent()).setScale(0, RoundingMode.HALF_UP);
				perfDashboardCategoryVO.setYrToDateSalesPercent(yrToDateSalesPercent.toString());
			}else{
				perfDashboardCategoryVO.setYrToDateSalesPercent("0");
			}
		}
		return perfDashboardCategoryVOListNew;
	}
	

	private List<PerfDashboardCategoryVO> orderAndMapByCategoryName(List<PerfDashboardCategoryVO> bossCoreCategoryVOList) {
		PerfDashboardCategoryVO[] bossCoreCategoryVOarr = new PerfDashboardCategoryVO[bossCoreCategoryVOList.size()];
		int i = 0;
		for (PerfDashboardCategoryVO perfDashboardCategoryVO : bossCoreCategoryVOList) {
			switch (perfDashboardCategoryVO.getCategoryName().trim()) {

			case Constants.CAT_CORE_TONER:
				perfDashboardCategoryVO.setCategoryName("Ink and Toner");
				bossCoreCategoryVOarr[0] = perfDashboardCategoryVO;
				break;
			case (Constants.CAT_CORE_PAPER):
				perfDashboardCategoryVO.setCategoryName("Paper");
				bossCoreCategoryVOarr[1] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSSS_TECHNOLOGY:
				perfDashboardCategoryVO.setCategoryName("Technology");
				bossCoreCategoryVOarr[2] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSS_MAILSHIP:
				perfDashboardCategoryVO.setCategoryName("Mail and Ship");
				bossCoreCategoryVOarr[3] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSS_FURNITURE:
				perfDashboardCategoryVO.setCategoryName("Furniture");
				bossCoreCategoryVOarr[4] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSSS_FACILITIES:
				perfDashboardCategoryVO.setCategoryName("Facilities");
				bossCoreCategoryVOarr[5] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_CORE_OFFICE:
				perfDashboardCategoryVO.setCategoryName("Office Supplies");
				bossCoreCategoryVOarr[6] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSS_PRINT:
				perfDashboardCategoryVO.setCategoryName("Print");
				bossCoreCategoryVOarr[7] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_BOSSS_PROMO:
				perfDashboardCategoryVO.setCategoryName("Promo");
				bossCoreCategoryVOarr[8] = perfDashboardCategoryVO;
				break;
			case Constants.CAT_ALL_OTHER_PROD:
				perfDashboardCategoryVO.setCategoryName("All Other Products");
				bossCoreCategoryVOarr[9] = perfDashboardCategoryVO;
				break;	
			default:
				bossCoreCategoryVOarr[i] = perfDashboardCategoryVO;
			}
			i++;
		}

		ArrayList<PerfDashboardCategoryVO> bossCoreCategoryVOListNew = new ArrayList<PerfDashboardCategoryVO>(Arrays.asList(bossCoreCategoryVOarr));
		return bossCoreCategoryVOListNew;

	}
	
	private List<String> getAllCategoryList() {
		List<String> allCategoryList = new ArrayList<String>();
		allCategoryList.add(Constants.CAT_CORE_OFFICE);
		allCategoryList.add(Constants.CAT_CORE_PAPER);
		allCategoryList.add(Constants.CAT_CORE_TONER);
		
		allCategoryList.add(Constants.CAT_BOSSS_FACILITIES);
		allCategoryList.add(Constants.CAT_BOSS_FURNITURE);
		allCategoryList.add(Constants.CAT_BOSSS_TECHNOLOGY);
		allCategoryList.add(Constants.CAT_BOSS_PRINT);
		allCategoryList.add(Constants.CAT_BOSSS_PROMO);
		allCategoryList.add(Constants.CAT_BOSS_MAILSHIP);
		allCategoryList.add(Constants.CAT_ALL_OTHER_PROD);
		return allCategoryList;
	}


	@Override
	public List<PerfDashboardCategoryVO> getCustInsightDataIfExist(String custNum)
			throws DashboardException {
		try {
			List<String> allCategoryList = getAllCategoryList();
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = perfDashboardDao.getCustInsightDataIfExist(custNum, allCategoryList);
			logger.debug("perfDashboardCategoryVOList getCustInsightDataIfExist:--> "+ perfDashboardCategoryVOList);
			
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOListNew = new ArrayList<PerfDashboardCategoryVO>();
			if(perfDashboardCategoryVOList.size() > 0){
				perfDashboardCategoryVOListNew = createMesgAndmapCategoryName(perfDashboardCategoryVOList);
			}
			return perfDashboardCategoryVOListNew;

		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}
	
	
	private List<PerfDashboardCategoryVO> createMesgAndmapCategoryName(List<PerfDashboardCategoryVO> perfDashboardCategoryVOList) {
		String newMesg = null;
		for (PerfDashboardCategoryVO perfDashboardCategoryVO : perfDashboardCategoryVOList) {
			if (perfDashboardCategoryVO.getMesgDesc() != null && !(perfDashboardCategoryVO.getMesgDesc().isEmpty())) {
				newMesg = perfDashboardCategoryVO.getMesgDesc().trim().replaceAll(Constants.DECLINE_PCT, perfDashboardCategoryVO.getDeclinePercent()+"%");
				perfDashboardCategoryVO.setMesgDesc(newMesg);
			}
		}
		return perfDashboardCategoryVOList;
	}
	
	
	@Override
	public String isDisplayWIRFlag(String custNum) throws DashboardException {
		try {
			String displayWIRFlag = "N"; 
			List<PerfDashboardCategoryVO> perfDashboardCategoryVOList = perfDashboardDao.getWIREnabledFlag(custNum);
			logger.debug("perfDashboardCategoryVOList isDisplayWIRFlag:--> "+ perfDashboardCategoryVOList);
			
			if(perfDashboardCategoryVOList != null && perfDashboardCategoryVOList.size() > 0 && perfDashboardCategoryVOList.get(0).getWirEnabledFlag() != null){
				displayWIRFlag = perfDashboardCategoryVOList.get(0).getWirEnabledFlag().toUpperCase().trim();
			}
			return displayWIRFlag;
		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}
	
	/*@Override
	public PerfDashboardPremiumVO getPremiumCustomerProfile(String custNum)
			throws DashboardException {
		try {

			List <PerfDashboardPremiumVO> perfDashboardPremiumVOList = perfDashboardDao.getPremiumProfile(custNum);
			logger.debug(" in getPremiumCustomerProfile service--> " + perfDashboardPremiumVOList.toString());
			
			PerfDashboardPremiumVO perfDashboardPremiumVO = new PerfDashboardPremiumVO();
			if(perfDashboardPremiumVOList.size() > 0){
				perfDashboardPremiumVO = perfDashboardPremiumVOList.get(0);
			}
			return perfDashboardPremiumVO;

		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}

	@Override
	public List <BenefitUtilizationVO> getPremiumBenefitUtilization(String custNum)
			throws DashboardException {
		try {

			List <BenefitUtilizationVO> benefitUtilizationVOList = perfDashboardDao.getBenefitUtilization(custNum);
			logger.debug(" in getPremiumBenefitUtilization service--> " + benefitUtilizationVOList.toString());

			return benefitUtilizationVOList;

		} catch (DashboardException de) {
			de.printStackTrace();
			throw de;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(e);
		}
	}*/
	
}