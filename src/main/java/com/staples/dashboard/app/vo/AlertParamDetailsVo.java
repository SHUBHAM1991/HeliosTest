package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlertParamDetailsVo {
	
	private String alertId;
	private String paramId;
	private String paramVal;
	private String paramLabel;
	
	
	/**
	 * @return the alertId
	 */
	public String getAlertId() {
		return alertId;
	}
	/**
	 * @param alertId the alertId to set
	 */
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	/**
	 * @return the paramId
	 */
	public String getParamId() {
		return paramId;
	}
	/**
	 * @param paramId the paramId to set
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	/**
	 * @return the paramVal
	 */
	public String getParamVal() {
		return paramVal;
	}
	/**
	 * @param paramVal the paramVal to set
	 */
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
	/**
	 * @return the paramDetails
	 */
	/**
	 * @return the paramLabel
	 */
	public String getParamLabel() {
		return paramLabel;
	}
	/**
	 * @param paramLabel the paramLabel to set
	 */
	public void setParamLabel(String paramLabel) {
		this.paramLabel = paramLabel;
	}
	
}
