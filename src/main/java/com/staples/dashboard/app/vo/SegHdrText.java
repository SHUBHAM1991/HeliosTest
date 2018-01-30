package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;

public class SegHdrText {

	private String seg;
	private String segId;
	private String headerIdsShown;
	private String totalHeaders;
	private String Hdr1StaticText;
	private String Hdr1ParamCount;
	private String Hdr2StaticText;
	private String Hdr2ParamCount;
	private String Hdr3StaticText;
	private String Hdr3ParamCount;
	private String Hdr4StaticText;
	private String Hdr4ParamCount;
	private String Hdr5StaticText;
	private String Hdr5ParamCount;
	private String Hdr6StaticText;
	private String Hdr6ParamCount;
	private String Hdr7StaticText;
	private String Hdr7ParamCount;
	private String Hdr8StaticText;
	private String Hdr8ParamCount;
	private String Hdr9StaticText;
	private String Hdr9ParamCount;
	private String Hdr10StaticText;
	private String Hdr10ParamCount;
	private List<SegHeaderLabelsVO> hdrLables = new ArrayList<SegHeaderLabelsVO>();
	private List<String> dispStatus = new ArrayList<String>();
	private List<CustSfdcInfoVO> orderContactList=new ArrayList<CustSfdcInfoVO>();
	private List<SegmentSubDetail> attributeList=new ArrayList<SegmentSubDetail>();
	private List<SegMktgResources> mktgResources = new ArrayList<SegMktgResources>();
	
	
	public List<SegHeaderLabelsVO> getHdrLables() {
		return hdrLables;
	}
	public void setHdrLables(List<SegHeaderLabelsVO> hdrLables) {
		this.hdrLables = hdrLables;
	}
	public String getSeg() {
		return seg;
	}
	public void setSeg(String seg) {
		this.seg = seg;
	}
	public String getSegId() {
		return segId;
	}
	public void setSegId(String segId) {
		this.segId = segId;
	}
	public String getHeaderIdsShown() {
		return headerIdsShown;
	}
	public void setHeaderIdsShown(String headerIdsShown) {
		this.headerIdsShown = headerIdsShown;
	}
	public String getTotalHeaders() {
		return totalHeaders;
	}
	public void setTotalHeaders(String totalHeaders) {
		this.totalHeaders = totalHeaders;
	}
	public String getHdr1StaticText() {
		return Hdr1StaticText;
	}
	public void setHdr1StaticText(String hdr1StaticText) {
		Hdr1StaticText = hdr1StaticText;
	}
	public String getHdr1ParamCount() {
		return Hdr1ParamCount;
	}
	public void setHdr1ParamCount(String hdr1ParamCount) {
		Hdr1ParamCount = hdr1ParamCount;
	}
	public String getHdr2StaticText() {
		return Hdr2StaticText;
	}
	public void setHdr2StaticText(String hdr2StaticText) {
		Hdr2StaticText = hdr2StaticText;
	}
	public String getHdr2ParamCount() {
		return Hdr2ParamCount;
	}
	public void setHdr2ParamCount(String hdr2ParamCount) {
		Hdr2ParamCount = hdr2ParamCount;
	}
	public String getHdr3StaticText() {
		return Hdr3StaticText;
	}
	public void setHdr3StaticText(String hdr3StaticText) {
		Hdr3StaticText = hdr3StaticText;
	}
	public String getHdr3ParamCount() {
		return Hdr3ParamCount;
	}
	public void setHdr3ParamCount(String hdr3ParamCount) {
		Hdr3ParamCount = hdr3ParamCount;
	}
	public String getHdr4StaticText() {
		return Hdr4StaticText;
	}
	public void setHdr4StaticText(String hdr4StaticText) {
		Hdr4StaticText = hdr4StaticText;
	}
	public String getHdr4ParamCount() {
		return Hdr4ParamCount;
	}
	public void setHdr4ParamCount(String hdr4ParamCount) {
		Hdr4ParamCount = hdr4ParamCount;
	}
	public String getHdr5StaticText() {
		return Hdr5StaticText;
	}
	public void setHdr5StaticText(String hdr5StaticText) {
		Hdr5StaticText = hdr5StaticText;
	}
	public String getHdr5ParamCount() {
		return Hdr5ParamCount;
	}
	public void setHdr5ParamCount(String hdr5ParamCount) {
		Hdr5ParamCount = hdr5ParamCount;
	}
	public String getHdr6StaticText() {
		return Hdr6StaticText;
	}
	public void setHdr6StaticText(String hdr6StaticText) {
		Hdr6StaticText = hdr6StaticText;
	}
	public String getHdr6ParamCount() {
		return Hdr6ParamCount;
	}
	public void setHdr6ParamCount(String hdr6ParamCount) {
		Hdr6ParamCount = hdr6ParamCount;
	}
	public String getHdr7StaticText() {
		return Hdr7StaticText;
	}
	public void setHdr7StaticText(String hdr7StaticText) {
		Hdr7StaticText = hdr7StaticText;
	}
	public String getHdr7ParamCount() {
		return Hdr7ParamCount;
	}
	public void setHdr7ParamCount(String hdr7ParamCount) {
		Hdr7ParamCount = hdr7ParamCount;
	}
	public String getHdr8StaticText() {
		return Hdr8StaticText;
	}
	public void setHdr8StaticText(String hdr8StaticText) {
		Hdr8StaticText = hdr8StaticText;
	}
	public String getHdr8ParamCount() {
		return Hdr8ParamCount;
	}
	public void setHdr8ParamCount(String hdr8ParamCount) {
		Hdr8ParamCount = hdr8ParamCount;
	}
	public String getHdr9StaticText() {
		return Hdr9StaticText;
	}
	public void setHdr9StaticText(String hdr9StaticText) {
		Hdr9StaticText = hdr9StaticText;
	}
	public String getHdr9ParamCount() {
		return Hdr9ParamCount;
	}
	public void setHdr9ParamCount(String hdr9ParamCount) {
		Hdr9ParamCount = hdr9ParamCount;
	}
	public String getHdr10StaticText() {
		return Hdr10StaticText;
	}
	public void setHdr10StaticText(String hdr10StaticText) {
		Hdr10StaticText = hdr10StaticText;
	}
	public String getHdr10ParamCount() {
		return Hdr10ParamCount;
	}
	public void setHdr10ParamCount(String hdr10ParamCount) {
		Hdr10ParamCount = hdr10ParamCount;
	}
	/**
	 * @return the dispStatus
	 */
	public List<String> getDispStatus() {
		return dispStatus;
	}
	/**
	 * @param dispStatus the dispStatus to set
	 */
	public void setDispStatus(List<String> dispStatus) {
		this.dispStatus = dispStatus;
	}
	/**
	 * @return the orderContactList
	 */
	public List<CustSfdcInfoVO> getOrderContactList() {
		return orderContactList;
	}
	/**
	 * @param orderContactList the orderContactList to set
	 */
	public void setOrderContactList(List<CustSfdcInfoVO> orderContactList) {
		this.orderContactList = orderContactList;
	}
	/**
	 * @return the attributeList
	 */
	public List<SegmentSubDetail> getAttributeList() {
		return attributeList;
	}
	/**
	 * @param attributeList the attributeList to set
	 */
	public void setAttributeList(List<SegmentSubDetail> attributeList) {
		this.attributeList = attributeList;
	}
	public List<SegMktgResources> getMktgResources() {
		return mktgResources;
	}
	public void setMktgResources(List<SegMktgResources> mktgResources) {
		this.mktgResources = mktgResources;
	}
	
	
}
