package com.staples.dashboard.app.sdc.vo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LineDiscountBean {
	
	private String salesTranId;
	private String salesTranDetailId;
	private String discountLineItemId;
	private String orderLineKey;
	private String discountType;
	private String discountNo;
	private String discountDesc;
	private String discountLongDesc;
	private String discountPercentage;
	private String discountAmount;

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

	public String getDiscountLineItemId() {
		return discountLineItemId;
	}

	public void setDiscountLineItemId(String discountLineItemId) {
		this.discountLineItemId = discountLineItemId;
	}

	public String getOrderLineKey() {
		return orderLineKey;
	}

	public void setOrderLineKey(String orderLineKey) {
		this.orderLineKey = orderLineKey;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscountNo() {
		return discountNo;
	}

	public void setDiscountNo(String discountNo) {
		this.discountNo = discountNo;
	}

	public String getDiscountDesc() {
		return discountDesc;
	}

	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}

	public String getDiscountLongDesc() {
		return discountLongDesc;
	}

	public void setDiscountLongDesc(String discountLongDesc) {
		this.discountLongDesc = discountLongDesc;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discountAmount == null) ? 0 : discountAmount.hashCode());
		result = prime * result
				+ ((discountDesc == null) ? 0 : discountDesc.hashCode());
		result = prime
				* result
				+ ((discountLongDesc == null) ? 0 : discountLongDesc.hashCode());
		result = prime * result
				+ ((discountNo == null) ? 0 : discountNo.hashCode());
		result = prime
				* result
				+ ((discountPercentage == null) ? 0 : discountPercentage
						.hashCode());
		result = prime * result
				+ ((discountType == null) ? 0 : discountType.hashCode());
		result = prime * result
				+ ((orderLineKey == null) ? 0 : orderLineKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineDiscountBean other = (LineDiscountBean) obj;
		if (discountAmount == null) {
			if (other.discountAmount != null)
				return false;
		} else if (!discountAmount.equals(other.discountAmount))
			return false;
		if (discountDesc == null) {
			if (other.discountDesc != null)
				return false;
		} else if (!discountDesc.equals(other.discountDesc))
			return false;
		if (discountLongDesc == null) {
			if (other.discountLongDesc != null)
				return false;
		} else if (!discountLongDesc.equals(other.discountLongDesc))
			return false;
		if (discountNo == null) {
			if (other.discountNo != null)
				return false;
		} else if (!discountNo.equals(other.discountNo))
			return false;
		if (discountPercentage == null) {
			if (other.discountPercentage != null)
				return false;
		} else if (!discountPercentage.equals(other.discountPercentage))
			return false;
		if (discountType == null) {
			if (other.discountType != null)
				return false;
		} else if (!discountType.equals(other.discountType))
			return false;
		if (orderLineKey == null) {
			if (other.orderLineKey != null)
				return false;
		} else if (!orderLineKey.equals(other.orderLineKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineDiscountBean [salesTranId=" + salesTranId
				+ ", salesTranDetailId=" + salesTranDetailId
				+ ", discountLineItemId=" + discountLineItemId
				+ ", orderLineKey=" + orderLineKey + ", discountType="
				+ discountType + ", discountNo=" + discountNo
				+ ", discountDesc=" + discountDesc + ", discountLongDesc="
				+ discountLongDesc + ", discountPercentage="
				+ discountPercentage + ", discountAmount=" + discountAmount
				+ "]";
	}
}
