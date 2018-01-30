package com.staples.dashboard.app.sdc.vo;

public class HeaderDiscountsBean {

	private String salesTranId;
	private String salesTranDiscountId;
	private String discountType;
	private String discountCategory;
	private String discountNo;
	private String discountDesc;
	private String discountPercentage;

	private String discountAmount;

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

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

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscountCategory() {
		return discountCategory;
	}

	public void setDiscountCategory(String discountCategory) {
		this.discountCategory = discountCategory;
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

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((discountAmount == null) ? 0 :
	 * discountAmount.hashCode()); result = prime result + ((discountCategory ==
	 * null) ? 0 : discountCategory.hashCode()); result = prime * result +
	 * ((discountDesc == null) ? 0 : discountDesc.hashCode()); result = prime *
	 * result + ((discountNo == null) ? 0 : discountNo.hashCode()); result =
	 * prime result + ((discountPercentage == null) ? 0 : discountPercentage
	 * .hashCode()); result = prime * result + ((discountType == null) ? 0 :
	 * discountType.hashCode()); return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; HeaderDiscountsBean other = (HeaderDiscountsBean) obj; if
	 * (discountAmount == null) { if (other.discountAmount != null) return
	 * false; } else if (!discountAmount.equals(other.discountAmount)) return
	 * false; if (discountCategory == null) { if (other.discountCategory !=
	 * null) return false; } else if
	 * (!discountCategory.equals(other.discountCategory)) return false; if
	 * (discountDesc == null) { if (other.discountDesc != null) return false; }
	 * else if (!discountDesc.equals(other.discountDesc)) return false; if
	 * (discountNo == null) { if (other.discountNo != null) return false; } else
	 * if (!discountNo.equals(other.discountNo)) return false; if
	 * (discountPercentage == null) { if (other.discountPercentage != null)
	 * return false; } else if
	 * (!discountPercentage.equals(other.discountPercentage)) return false; if
	 * (discountType == null) { if (other.discountType != null) return false; }
	 * else if (!discountType.equals(other.discountType)) return false; return
	 * true; }
	 */

	@Override
	public String toString() {
		return "HeaderDiscountsBean [salesTranId=" + salesTranId + ", salesTranDiscountId=" + salesTranDiscountId
				+ ", discountType=" + discountType + ", discountCategory=" + discountCategory + ", discountNo="
				+ discountNo + ", discountDesc=" + discountDesc + ", discountPercentage=" + discountPercentage
				+ ", discountAmount=" + discountAmount + "]";
	}
}
