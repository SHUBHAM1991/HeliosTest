package com.staples.dashboard.app.sdc.vo;

import java.io.Serializable;

public class SalesTranDiscountId implements Serializable {

	private static final long serialVersionUID = -8281700150803810761L;
	private String salesTranId;
	private String salesTranDiscountId;

	public String getSalesTranId() {
		return salesTranId;
	}

	public void setSalesTranId(String salesTranId) {
		this.salesTranId = salesTranId;
	}

	public String getSalesTranDiscountId() {
		return salesTranDiscountId;
	}

	public void setSalesTranDiscountId(String salesTranDiscountId) {
		this.salesTranDiscountId = salesTranDiscountId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salesTranDiscountId == null) ? 0 : salesTranDiscountId.hashCode());
		result = prime * result + ((salesTranId == null) ? 0 : salesTranId.hashCode());
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
		SalesTranDiscountId other = (SalesTranDiscountId) obj;
		if (salesTranDiscountId == null) {
			if (other.salesTranDiscountId != null)
				return false;
		} else if (!salesTranDiscountId.equals(other.salesTranDiscountId))
			return false;
		if (salesTranId == null) {
			if (other.salesTranId != null)
				return false;
		} else if (!salesTranId.equals(other.salesTranId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesTranDiscountId [salesTranId=" + salesTranId
				+ ", salesTranDiscountId=" + salesTranDiscountId + "]";
	}
}
