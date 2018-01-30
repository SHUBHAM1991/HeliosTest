package com.staples.dashboard.app.sdc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value=Include.NON_NULL)
public class RewardsVO {

	/*Rewards Statement starts here*/
	@JsonProperty("PERIODFROM")
	private String periodFrom;
	
	@JsonProperty("PERIODTO")
	private String periodTo;

	@JsonProperty("STATEMENTNUM")
	private String statementNum;
	
	@JsonProperty("USED")
	private String used;
	
	@JsonProperty("TIERID")
	private String tierId;

	@JsonProperty("REDEEMEDMEMBERNO")
	private String redeemedMemberNo;
	
	@JsonProperty("EXPDATE")
	private String expDate;
	
	@JsonProperty("VERSIONID")
	private String versionId;
	
	@JsonProperty("STMT_NUM")
	private String statementNumber;
	/*Rewards Statement ends here*/
	
	/*Rewards Statement detail starts here*/
	@JsonProperty("STATEMENTAMOUNT")
	private String statementAmount;
	
	@JsonProperty("STATEMENTCODE")
	private String statementCode;
	
	@JsonProperty("MEMBERNO")
	private String memberNo;
	
	@JsonProperty("TRANSACTIONDATE")
	private String transactionDate;
	
	@JsonProperty("TRANSACTION")
	private String transaction;
	
	@JsonProperty("STORE")
	private String store;
	
	@JsonProperty("ORDERNUMBER")
	private String orderNumber;
	/*Rewards Statement detail ends here*/
	
	/*Category wise saving starts here*/
	@JsonProperty("ORDER")
	private String order;

	@JsonProperty("CUSTOMER_NUMBER")
	private String customer_number;
	
	@JsonProperty("BENEFIT")
	private String benefit;
	
	@JsonProperty("YTD_START_DT")
	private String ytd_start_dt;

	@JsonProperty("SKU_CATEGORY")
	private String sku_category;
	
	@JsonProperty("YTD_SLS_AMT")
	private String ytd_sls_amt;
	
	@JsonProperty("YTD_SAV_AMT")
	private String ytd_sav_amt;
	/*Category wise saving ends here*/
	
	public String getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}
	public String getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}
	public String getStatementAmount() {
		return statementAmount;
	}
	public void setStatementAmount(String statementAmount) {
		this.statementAmount = statementAmount;
	}
	public String getStatementNum() {
		return statementNum;
	}
	public void setStatementNum(String statementNum) {
		this.statementNum = statementNum;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getTierId() {
		return tierId;
	}
	public void setTierId(String tierId) {
		this.tierId = tierId;
	}
	public String getStatementCode() {
		return statementCode;
	}
	public void setStatementCode(String statementCode) {
		this.statementCode = statementCode;
	}
	public String getRedeemedMemberNo() {
		return redeemedMemberNo;
	}
	public void setRedeemedMemberNo(String redeemedMemberNo) {
		this.redeemedMemberNo = redeemedMemberNo;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getStatementNumber() {
		return statementNumber;
	}
	public void setStatementNumber(String statementNumber) {
		this.statementNumber = statementNumber;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getYtd_start_dt() {
		return ytd_start_dt;
	}
	public void setYtd_start_dt(String ytd_start_dt) {
		this.ytd_start_dt = ytd_start_dt;
	}
	public String getSku_category() {
		return sku_category;
	}
	public void setSku_category(String sku_category) {
		this.sku_category = sku_category;
	}
	public String getYtd_sls_amt() {
		return ytd_sls_amt;
	}
	public void setYtd_sls_amt(String ytd_sls_amt) {
		this.ytd_sls_amt = ytd_sls_amt;
	}
	public String getYtd_sav_amt() {
		return ytd_sav_amt;
	}
	public void setYtd_sav_amt(String ytd_sav_amt) {
		this.ytd_sav_amt = ytd_sav_amt;
	}
}
