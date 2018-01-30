package com.staples.dashboard.app.sdc.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArkeCustomerAttributes {

	private ArkeCustomerMembership membership;
	private ArkeCustomerOrchid orchid;
	private String segmentcode;
	private ArkeCustomerUbas ubas;
	
	@JsonProperty("MEMBERSHIP")
	public ArkeCustomerMembership getMembership() {
		return membership;
	}
	
	@JsonProperty("MEMBERSHIP")
	public void setMembership(ArkeCustomerMembership membership) {
		this.membership = membership;
	}
	
	@JsonProperty("ORCHID")
	public ArkeCustomerOrchid getOrchid() {
		return orchid;
	}
	
	@JsonProperty("ORCHID")
	public void setOrchid(ArkeCustomerOrchid orchid) {
		this.orchid = orchid;
	}
	
	@JsonProperty("SEGMENTCODE")
	public String getSegmentcode() {
		return segmentcode;
	}
	
	@JsonProperty("SEGMENTCODE")
	public void setSegmentcode(String segmentcode) {
		this.segmentcode = segmentcode;
	}

	@JsonProperty("UBAS")
	public ArkeCustomerUbas getUbas() {
		return ubas;
	}

	@JsonProperty("UBAS")
	public void setUbas(ArkeCustomerUbas ubas) {
		this.ubas = ubas;
	}
	
}
