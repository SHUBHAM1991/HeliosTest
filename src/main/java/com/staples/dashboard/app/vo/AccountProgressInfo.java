package com.staples.dashboard.app.vo;

import java.math.BigDecimal;
import java.util.Map;

public class AccountProgressInfo {
	private String todaysAccntActionedPercentage;
	private String todaysAccntActioned;
	private String todaysDate;
	private boolean targetReached=false;
	private String  weekDateRange;
	private Map<String,Integer> weeklyAccntActionedMap;
	private int weeklyAccntActioned;
	
	public void setWeeklyAccntActionedMap(Map<String, Integer> weeklyAccntActionedMap) {
		this.weeklyAccntActionedMap = weeklyAccntActionedMap;
	}
	
	public String getTodaysAccntActionedPercentage() {
		return todaysAccntActionedPercentage;
	}

	public void setTodaysAccntActionedPercentage(String todaysAccntActionedPercentage) {
		this.todaysAccntActionedPercentage = todaysAccntActionedPercentage;
	}

	public String getTodaysAccntActioned() {
		return todaysAccntActioned;
	}

	public void setTodaysAccntActioned(String todaysAccntActioned) {
		this.todaysAccntActioned = todaysAccntActioned;
	}

	public String getTodaysDate() {
		return todaysDate;
	}
	public void setTodaysDate(String todaysDate) {
		this.todaysDate = todaysDate;
	}
	public boolean isTargetReached() {
		return targetReached;
	}
	public void setTargetReached(boolean targetReached) {
		this.targetReached = targetReached;
	}
	public String getWeekDateRange() {
		return weekDateRange;
	}
	public void setWeekDateRange(String weekDateRange) {
		this.weekDateRange = weekDateRange;
	}
	
	
	public int getWeeklyAccntActioned() {
		return weeklyAccntActioned;
	}
	public void setWeeklyAccntActioned(int weeklyAccntActioned) {
		this.weeklyAccntActioned = weeklyAccntActioned;
	}
	
	
	
}
