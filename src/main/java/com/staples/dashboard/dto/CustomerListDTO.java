package com.staples.dashboard.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.vo.CdmCustProfileVO;
import com.staples.dashboard.app.vo.CustProfileVO;

public class CustomerListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7546979481539150790L;
	private List<CustProfileVO> CustomerVoList;
	private List<CdmCustProfileVO> CdmCustomerVoList;
	private int totalCount;
	private int displayCount;
	private String mgrFlag;
	private List<String> custTypeList;
	//private List<String> timeZoneList;
	private Map<String,String> timeZoneMap;
	/**
	 * @return
	 */
	public List<CustProfileVO> getCustomerVoList() {
		return CustomerVoList;
	}
	/**
	 * @param customerVoList
	 */
	public void setCustomerVoList(List<CustProfileVO> customerVoList) {
		CustomerVoList = customerVoList;
	}
	/**
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return
	 */
	public int getDisplayCount() {
		return displayCount;
	}
	/**
	 * @param displayCount
	 */
	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}
	/**
	 * @return
	 */
	public String getMgrFlag() {
		return mgrFlag;
	}
	/**
	 * @param mgrFlag
	 */
	public void setMgrFlag(String mgrFlag) {
		this.mgrFlag = mgrFlag;
	}
	/**
	 * @return the custTypeList
	 */
	public List<String> getCustTypeList() {
		return custTypeList;
	}
	/**
	 * @param custTypeList the custTypeList to set
	 */
	public void setCustTypeList(List<String> custTypeList) {
		this.custTypeList = custTypeList;
	}
	/**
	 * @return the cdmCustomerVoList
	 */
	public List<CdmCustProfileVO> getCdmCustomerVoList() {
		return CdmCustomerVoList;
	}
	/**
	 * @param cdmCustomerVoList the cdmCustomerVoList to set
	 */
	public void setCdmCustomerVoList(List<CdmCustProfileVO> cdmCustomerVoList) {
		CdmCustomerVoList = cdmCustomerVoList;
	}
	public Map<String, String> getTimeZoneMap() {
		return timeZoneMap;
	}
	public void setTimeZoneMap(Map<String, String> timeZoneMap) {
		this.timeZoneMap = timeZoneMap;
	}
	
	
	
}
