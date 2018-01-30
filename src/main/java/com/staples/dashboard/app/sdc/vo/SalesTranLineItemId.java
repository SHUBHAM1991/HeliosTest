package com.staples.dashboard.app.sdc.vo;

import java.io.Serializable;

public class SalesTranLineItemId implements Serializable{

	private static final long serialVersionUID = -5923086317743956879L;
	private String salesTranId;
	private String salesTranLineItemId;

	public String getSalesTranId() {
		return salesTranId;
	}

	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}

	public String getSalesTranLineItemId() {
		return salesTranLineItemId;
	}

	public void setSalesTranLineItemId(String salesTranLineItemId) {
		this.salesTranLineItemId = salesTranLineItemId;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salesTranId == null) ? 0 : salesTranId.hashCode());
		result = prime * result + ((salesTranLineItemId == null) ? 0 : salesTranLineItemId.hashCode());
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
		SalesTranLineItemId other = (SalesTranLineItemId) obj;
		if (salesTranId == null) {
			if (other.salesTranId != null)
				return false;
		} else if (!salesTranId.equals(other.salesTranId))
			return false;
		if (salesTranLineItemId == null) {
			if (other.salesTranLineItemId != null)
				return false;
		} else if (!salesTranLineItemId.equals(other.salesTranLineItemId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesTranChargeId [salesTranId=" + salesTranId
				+ ", salesTranLineItemId=" + salesTranLineItemId + "]";
	}
}

