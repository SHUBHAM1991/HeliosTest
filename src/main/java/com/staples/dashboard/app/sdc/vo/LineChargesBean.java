package com.staples.dashboard.app.sdc.vo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "salesTranId", "salesTranDetailId", "chargeLineItemId", "orderLineKey" })
public class LineChargesBean {

	private String salesTranId;
	private String salesTranDetailId;
	private String chargeLineItemId;
	private String orderLineKey;
	private String chargeType;
	private String chargeNo;
	private BigDecimal chargeAmount;
	private String chargeCategory;
	private String chargeName;
	private String chargeDesc;
	private BigDecimal chargePerLine;
	private BigDecimal chargePerUnit;

	public String getSalesTranId() {
		return salesTranId;
	}

	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}

	public String getSalesTranDetailId() {
		return salesTranDetailId;
	}

	public void setSalesTranDetailId(String salesTranDetailId) {
		this.salesTranDetailId = salesTranDetailId;
	}

	public String getChargeLineItemId() {
		return chargeLineItemId;
	}

	public void setChargeLineItemId(String chargeLineItemId) {
		this.chargeLineItemId = chargeLineItemId;
	}

	public String getOrderLineKey() {
		return orderLineKey;
	}

	public void setOrderLineKey(String orderLineKey) {
		this.orderLineKey = orderLineKey;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeNo() {
		return chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getChargeCategory() {
		return chargeCategory;
	}

	public void setChargeCategory(String chargeCategory) {
		this.chargeCategory = chargeCategory;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	public BigDecimal getChargePerLine() {
		return chargePerLine;
	}

	public void setChargePerLine(BigDecimal chargePerLine) {
		this.chargePerLine = chargePerLine;
	}

	public BigDecimal getChargePerUnit() {
		return chargePerUnit;
	}

	public void setChargePerUnit(BigDecimal chargePerUnit) {
		this.chargePerUnit = chargePerUnit;
	}

	@Override
	public String toString() {
		return "LineChargesBean [salesTranId=" + salesTranId
				+ ", salesTranDetailId=" + salesTranDetailId
				+ ", chargeLineItemId=" + chargeLineItemId + ", orderLineKey="
				+ orderLineKey + ", chargeType=" + chargeType + ", chargeNo="
				+ chargeNo + ", chargeAmount=" + chargeAmount
				+ ", chargeCategory=" + chargeCategory + ", chargeName="
				+ chargeName + ", chargeDesc=" + chargeDesc
				+ ", chargePerLine=" + chargePerLine + ", chargePerUnit="
				+ chargePerUnit + "]";
	}
}
