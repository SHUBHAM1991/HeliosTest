package com.staples.dashboard.app.constants;

/**
 * The Class MapperConstants.
 * 
 * @author KumBi002
 * @version 1.0 Revision history
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          <table>
 *          <tr>
 *          <td>Version</td>
 *          <td>Date</td>
 *          <td>Author</td>
 *          <td>Description</td>
 *          </tr>
 *          <tr>
 *          <td>1.0</td>
 *          <td>Dec 4, 2015</td>
 *          <td>KumBi002</td>
 *          <td>Initial Draft</td>
 *          </tr>
 *          </table>
 *          </p>
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 */

public class MapperConstants {

	public static final String REP_CD = "REP_CD";
	public static final String ORDER_AMOUNT = "ORDER_AMOUNT";
	public static final String MIN_ORDER_AMOUNT = "MIN_ORDER_AMOUNT";
	public static final String ORDER_DATE = "ORDER_DATE";
	public static final String ORDER_NUMBER = "ORDER_NUMBER";
	public static final String ORDER_CONTACT = "ORDER_CONTACT";
	public static final String SKU = "SKU";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static String BLANK_STRING = "";
	public static float FLOAT_ZERO = 0.0f;
	public static String COMMA_STRING = ",";
	public static int THIRTY = 30;

	/* SuperUserInfoVOMapper starts */

	public static String SUPERUSERINFO_ORDERS = "ORDERS";
	public static String SUPERUSERINFO_ORDER_CONTACT = "ORDER_CONTACT";
	public static String SUPERUSERINFO_VISIT_COUNT = "VISIT_COUNT";
	public static String SUPERUSERINFO_ORDERS_CURR = "ORDERS_CURR";
	public static String SUPERUSERINFO_ORDERS_PERIOD = "ORDERS_PERIOD";
	public static String SUPERUSERINFO_RECENT_VISIT_DATE = "RECENT_VISIT_DATE";
	public static String SUPERUSERINFO_TOTAL_SALES = "TOTAL_SALES";
	public static String SUPERUSERINFO_TOTAL_SALES_CURR = "TOTAL_SALES_CURR";
	public static String SUPERUSERINFO_EMAIL = "EMAIL";
	public static String SUPERUSERINFO_PHONE_NUMBER = "PHONE_NUMBER";
	
	public static String SUPERUSERINFO_IAM_ID = "IAM_ID";
	public static String SUPERUSERINFO_RECENT_ORDER_DATE = "ORDER_DATE";
	public static String SUPERUSERINFO_CONTACT_ID = "CONTACT_ID";

	/* SuperUserInfoVOMapper ends */

	/* SubPlayInfoMapper starts */

	public static String SUBPLAYINFO_SEGDESC = "segDesc";
	public static String SUBPLAYINFO_SEGTYPE = "segType";
	public static String SUBPLAYINFO_SEG_DESC = "SEG_DESC";
	public static String SUBPLAYINFO_SEG_TYPE = "SEG_TYPE";
	public static String SUBPLAYINFO_SEG_ID = "SEG_ID";

	/* SubPlayInfoMapper Ends */

	/* ShipToVOMapper Starts */

	public static String SHIPTO_LASTYRNO = "LASTYRNO";
	public static String SHIPTO_LASTYRAMOUNT = "LASTYRAMOUNT";
	public static String SHIPTO_CURRENTYRNO = "CURRENTYRNO";
	public static String SHIPTO_CURRENTYRAMOUNT = "CURRENTYRAMOUNT";
	public static String SHIPTO_ADDRESS = "ADDRESS";
	public static String SHIPTO_CITY = "CITY";
	public static String SHIPTO_STATE = "STATE";
	public static String SHIPTO_ZIP = "ZIP";
	public static String SHIPTO_CASHID = "CASHID";
	public static String LASTYRSALESPERIOD = "LASTYRSALESPERIOD";
	public static String SHIPTOID = "SHIPTOID";
	public static String SHIPTOSTATUS = "SHIPTOSTATUS";
		   

	/* ShipToVOMapper Ends */

	/* SearchItemsVOMapper starts */

	public static String SEARCHITEMS_ORDER_CONTACT = "ORDER_CONTACT";
	public static String SEARCHITEMS_SEARCH_STRING = "SEARCH_STRING";

	/* SearchItemsVOMapper Ends */
	
	/* ReorderRecommendationMapper Starts */

	public static String REORDERRECOMM_CUST_NUM = "CUST_NUM";
	public static String REORDERRECOMM_SKU = "SKU";
	public static String REORDERRECOMM_SKU_NAME = "SKU_NAME";
	public static String REORDERRECOMM_THUMBNAIL = "THUMBNAIL";
	//public static String REORDERRECOMM_ORDER_DATE = "ORDER_DATE";
	//SparX Recommendation
	public static String REORDERRECOMM_CUST_NAME = " CUST_NAME";
	public static String REORDERRECOMM_CATENTRYID = "CATENTRY_ID";
	public static String REORDERRECOMM_SELECTPRICE = "SELECT_PRICE";
	public static String REORDERRECOMM_PREMIUMPRICE = "PREMIUM_PRICE";
	/* ReorderRecommendationMapper Ends */
	
	/* OnlineRetailRecommOrderContactMapper starts */
	
	public static String OR_REORDERRECOMM_SKU = "SKU_NUM";
	public static String OR_REORDERRECOMM_SKU_NAME = "SKU_NAME";
	public static String OR_REORDERRECOMM_THUMBNAIL = "THUMBNAIL";
	
	public static String OR_REORDERRECOMM_SELECTPRICE = "SELECT_PRICE";
	public static String OR_REORDERRECOMM_PREMIUMPRICE = "PREMIUM_PRICE";
	public static String OR_REORDERRECOMM_CATENTRYID = "CATENTRY_ID";
	/* OnlineRetailReorderRecommendationMapper Ends */
	
	/* RecommOrderContactMapper starts */

	public static String RECOMCONTACTS_ORDER_CONTACT = "ORDER_CONTACT";
	public static String RECOMCONTACTS_ORDER_DATE = "ORDER_DATE";

	/* RecommOrderContactMapper Ends */

	/* RecommendationViewNotBoughtMapper starts */

	public static String RECOMVNB_CUST_NUM = "CUST_NUM";
	public static String RECOMVNB_SKU = "SKU";
	public static String RECOMVNB_SKU_NAME = "SKU_NAME";
	public static String RECOMVNB_ORDER_CONTACT = "ORDER_CONTACT";
	public static String RECOMVNB_ORDER_DATE = "ORDER_DATE";
	public static String RECOMVNB_THUMBNAIL = "THUMBNAIL";

	/* RecommendationViewNotBoughtMapper Ends */

	/* PurchaseRwdsDetailsVOMapper Starts */

	public static String RWDDETAIL_SKU_NUM = "SKU_NUM";
	public static String RWDDETAIL_SKU_NAME = "SKU_NAME";
	public static String RWDDETAIL_ORDER_DATE = "ORDER_DATE";
	public static String RWDDETAIL_ORDER_NUM = "ORDER_NUM";
	public static String RWDDETAIL_ORDER_QTY = "ORDER_QTY";
	public static String RWDDETAIL_TOTAL_SPEND = "TOTAL_SPEND";
	public static String RWDDETAIL_ITEM_DESC = "ITEM_DESC";
	public static String RWDDETAIL_DISCOUNT_AMT = "DISCOUNT_AMT";
	public static String RWDDETAIL_UNIT_SALE_PRICE = "UNIT_SALE_PRICE";
	public static String RWDDETAIL_PRIMARY_PRODUCT_CAT_CD = "PRIMARY_PRODUCT_CAT_CD";
	public static String RWDDETAIL_ORDER_NUMBER = "ORDER_NUMBER";
	public static String RWDDETAIL_TRAN_ID = "TRAN_ID";

	/* PurchaseRwdsDetailsVOMapper Ends */

	/* PurchaseDetailsVOMapper starts */

	public static String PURCHASEDETAIL_ORDER_NUMBER = "ORDER_NUMBER";
	public static String PURCHASEDETAIL_ORDER_DATE = "ORDER_DATE";
	public static String PURCHASEDETAIL_ORDER_AMOUNT = "ORDER_AMOUNT";
	public static String PURCHASEDETAIL_MIN_ORDER_AMOUNT = "MIN_ORDER_AMOUNT";
	public static String PURCHASEDETAIL_CUST_NUM = "CUST_NUM";
	public static String PURCHASEDETAIL_ORDER_LINE_COUNT = "ORDER_LINE_COUNT";
	public static String SAVING_CATEGORY = "CATEGORY";
	public static String SAVING_AMOUNT = "AMOUNT";
	public static String PURCHASEDETAIL_SOURCE_NUMBER = "SOURCE_NUMBER";
	/* PurchaseDetailsVOMapper Ends */

	/* NotificationInfoMapper starts */

	public static String NOTIFI_segmentId = "segmentId";
	public static String NOTIFI_segmentDesc = "segmentDesc";
	public static String NOTIFI_segmentType = "segmentType";
	public static String NOTIFI_segmentName = "segmentName";
	public static String NOTIFI_SEG_ID = "SEG_ID";
	public static String NOTIFI_SEG_FREQ = "SEG_FREQ";
	public static String NOTIFI_SEG_DESC = "SEG_DESC";
	public static String NOTIFI_SEG_TYPE = "SEG_TYPE";
	public static String NOTIFI_SEG_NAME = "SEG_NAME";
	public static String NOTIFI_SEG_LAST_REFRESH_DATE = "LAST_REFRESH_DATE";

	/* NotificationInfoMapper starts */

	/* DotcomActivityVOMapper Starts */

	public static String DOTCOM_SKU = "SKU";
	public static String DOTCOM_SKU_NAME = "SKU_NAME";
	public static String DOTCOM_ORDER_CONTACT = "ORDER_CONTACT";
	public static String DOTCOM_ACT_DATE = "ACT_DATE";
	public static String DOTCOM_ACT = "ACT";
	public static String DOTCOM_PRICE = "PRICE";
	public static String DOTCOM_QUANTITY = "QUANTITY";
	public static String DOTCOM_THUMBNAIL = "THUMBNAIL";

	/* DotcomActivityVOMapper Ends */

	/* CustRecommendationVOMapper Starts */

	public static String CUST_RECOM_SKU = "SKU";
	public static String CUST_RECOM_SKU_NAME = "SKU_NAME";
	public static String CUST_RECOM_ORDER_CONTACT = "ORDER_CONTACT";
	public static String CUST_RECOM_THUMBNAIL = "THUMBNAIL";
	public static String CUST_RECOM_CATENTRYID = "CATENTRY_ID";

	/* CustRecommendationVOMapper Ends */

	/* CustomerMapper Starts */

	public static String CUSTOMER_COMPANY_NAME = "COMPANY_NAME";
	public static String CUSTOMER_CUST_NUM = "CUST_NUM";
	public static String CUSTOMER_EMAIL_ID = "EMAIL_ID";
	public static String CUSTOMER_FIRST_NAME = "FIRST_NAME";
	public static String CUSTOMER_LAST_NAME = "LAST_NAME";
	public static String CUSTOMER_PHONE_NUM = "PHONE_NUM";
	public static String CUSTOMER_CUSTOMER_TYPE = "CUSTOMER_TYPE";
	public static String CUSTOMER_MGREMAIL = "MGREMAIL";
	public static String CUSTOMER_MGRNAME = "MGRNAME";
	public static String CUSTOMER_MGRPHONE = "MGRPHONE";
	public static String CUSTOMER_MASTER_CUST_NUM = "MASTER_CUST_NUM";
	public static String CUSTOMER_CALL_ORDER = "CALL_ORDER";
	public static String CUSTOMER_LAST_CONTACTED_DATE = "S_LAST_CONTACTED_DATE";
	public static String CUSTOMER_PLAY_ID_LIST = "PLAY_ID_LIST";
	public static String CUSTOMER_AGENT_EMAIL_ID = "AGENT_EMAIL_ID";
	public static String CUSTOMER_ACCT_MNGR_FLAG = "ACCT_MNGR_FLAG";
	public static String CUSTOMER_SEGMENT = "CUSTOMER_SEGMENT";
	public static String CUSTOMER_VAP_SCORE = "VAP_SCORE";
	public static String CUSTOMER_ACCOUNT_QUALIFY_SCORE = "ACCOUNT_QUALIFY_SCORE";

	public static String COMPANY_ADDR_1 = "COMPANY_ADDR_1";
	public static String COMPANY_ADDR_2 = "COMPANY_ADDR_2";
	public static String COMPANY_CITY = "COMPANY_CITY";
	public static String COMPANY_STATE = "COMPANY_STATE";
	public static String COMPANY_COUNTRY = "COMPANY_COUNTRY";
	public static String COMPANY_ZIP = "COMPANY_ZIP";
	public static String COMPANY_PHONE = "COMPANY_PHONE";
	public static String LTS = "LTS";
	public static String IAMID = "IAMID";
	public static String LAST_CONTACTED_DATE = "LAST_CONTACTED_DATE";

	/* CustomerMapper Ends */

	/* AbandonedCartVOMapper starts */

	public static String ABAND_ORDER_CONTACT = "ORDER_CONTACT";
	public static String ABAND_SKU = "SKU";
	public static String ABAND_SKU_NAME = "SKU_NAME";
	public static String ABAND_ACT_DATE = "ACT_DATE";
	public static String ABAND_ACT = "ACT";
	public static String ABAND_THUMBNAIL = "THUMBNAIL";
	public static String ABAND_CATENTRY_ID = "CATENTRY_ID";

	/* AbandonedCartVOMapper Ends */

	/* BoughtAlsoBoughtVOMapper Starts */

	public static String BAB_SKU_LIST = "SKU_LIST";
	public static String BAB_CUSTOMER_NUMBER = "CUSTOMER_NUMBER";
	public static String BAB_ORDER_CONTACT = "ORDER_CONTACT";
	public static String BAB_SKU_NUMBER = "SKU_NUMBER";
	public static String BAB_ORDER_DATE = "ORDER_DATE";
	public static String BAB_SKU_NAME = "SKU_NAME";
	public static String BAB_THUMBNAIL = "THUMBNAIL";
	public static String BAB_CATENTRYID = "CATENTRY_ID";
	/* BoughtAlsoBoughtVOMapper Ends */

	/* YTDInfoMapper Starts */

	public static String INFOYTD_YEAR = "YEAR";
	public static String INFOYTD_YTD_SALES = "YTD_SALES";

	/* YTDInfoMapper Ends */

	/* YTDSummaryVOMapper Starts */

	public static String YTDSUM_MONTH = "MONTH";
	public static String YTDSUM_CAT = "CAT";
	public static String YTDSUM_TOTAL_SPND = "TOTAL_SPND";

	/* Account User Mapper */
	public static String EMP_NUMBER = "EMP_NUMBER";
	public static String EMP_FULL_NAME = "FULL_NAME";
	public static String EMP_DESIGNATION = "DESIGNATION";
	public static String EMP_REP_ROLE_CD = "REP_ROLE_CD";
	public static String EMP_LEVEL = "EMP_LEVEL";
	public static String COMPANY_NAME = "COMPANY_NAME";
	public static String CUSTOMER_NUMBER = "CUSTOMER_NUMBER";
	public static String TIMEZONE = "TIMEZONE";
	public static String CTA_CATEGORY_STATUS="STATUS";
	public static String CTA_DIVISION="DIVISION";
	public static String TODAYS_PROGRESS="TODAYS_PROGRESS";
	public static String WEEK_PROGRESS="WEEK_PROGRESS";
	
	/* Vap Score Mapper*/
	public static String VAP_SCORE_ID = "VAP_SCORE_ID";
	public static String VAP_SCORE_TYPE = "VAP_SCORE_TYPE";
	public static String VAP_SCORE_NAME = "VAP_SCORE_NAME";
	public static String VAP_SCORE_DESC = "VAP_SCORE_DESC";
	
	/* Qual Score Mapper*/
	public static String QUAL_SCORE_ID = "QUAL_SCORE_ID";
	public static String QUAL_SCORE_NAME = "QUAL_SCORE_NAME";
	public static String QUAL_SCORE_DESC = "QUAL_SCORE_DESC";
	
	/*Layout Costants  */
	public static String SEC1 = "SEC1";
	public static String SEC2 = "SEC2";
	public static String SEC3 = "SEC3";
	public static String SEC4 = "SEC4";
	public static String SEC5 = "SEC5";
	public static String SEC6 = "SEC6";
	public static String SEC7 = "SEC7";
	public static String SEC8 = "SEC8";
	public static String SEC9 = "SEC9";
	public static String SEC10 = "SEC10";
	public static String SEC11 = "SEC11";
	public static String SEC12 = "SEC12";
	public static String SEC13 = "SEC13";
	public static String SEC14 = "SEC14";
	public static String SEC15 = "SEC15";
	public static String SEC16 = "SEC16";
	public static String SEC17 = "SEC17";
	public static String SEC18 = "SEC18";
	public static String SEC19 = "SEC19";
	public static String SEC20 = "SEC20";
	public static String SEC21 = "SEC21";
	public static String SEC22 = "SEC22";
	
	/*Savings Report Mapper*/
	
	public static final String SAVINGS_REPORT_MAPPER_CUSTOMER_NUMBER="CUSTOMER_NUMBER";
	public static final String SAVINGS_REPORT_MAPPER_PRODUCT_SKU_NUM="PRODUCT_SKU_NUM";
	public static final String SAVINGS_REPORT_MAPPER_QUANTITY="QUANTITY";
	public static final String SAVINGS_REPORT_MAPPER_PRODUCT_NAME="PRODUCT_NAME";
	public static final String SAVINGS_REPORT_MAPPER_CHANNEL="CHANNEL";
	public static final String SAVINGS_REPORT_MAPPER_TOTAL_AMOUNT="TOTAL_AMOUNT";
	public static final String SAVINGS_REPORT_MAPPER_PROGRAM_PRICE="PROGRAM_PRICE";
	public static final String SAVINGS_REPORT_MAPPER_PROJECTED_ORDER_PRICE="PROJECTED_ORDER_PRICE";
	public static final String SAVINGS_REPORT_MAPPER_PROJECTED_REBATE="PROJECTED_REBATE";
	public static final String SAVINGS_REPORT_MAPPER_PROGRAM_SAVINGS="PROGRAM_SAVINGS";
	public static final String SAVINGS_REPORT_MAPPER_ORDER_NUMBER="ORDER_NUMBER";
	public static final String SAVINGS_REPORT_MAPPER_UNIT_PRICE="UNITPRICE";
	public static final String SAVINGS_REPORT_MAPPER_ORDER_DATE="ORDER_DATE";
	public static final String DIVISION = "DIVISION";
	public static final String SAVINGS_REPORT_MAPPER_ORDERDATE="ORDERTRANDATE";
	public static final String SAVINGS_REPORT_MAPPER_CHILDNUMBER="CHILDNUMBER";
	public static final String SAVINGS_REPORT_MAPPER_UNITPRICE="UNITPRICE";
	
	/*Savings Report mapper Ends*/
	
	// Performance Dashboard WIR
	public static final String CUSTOMER_NUMBER_WIR = "CUSTOMER_NUMBER";
	public static final String PRIMARY_CAT_DESC = "PRIMARY_CAT_DESC";
	public static final String CURR_ROLL_3_MONTH_SLS = "CURR_ROLL_3_MONTH_SLS";
	public static final String PRIOR_ROLL_3_MONTH_SLS = "PRIOR_ROLL_3_MONTH_SLS";
	public static final String CURR_YEAR_TO_DATE_SLS = "CURR_YEAR_TO_DATE_SLS";
	public static final String PRIOR_YEAR_TO_DATE_SLS = "PRIOR_YEAR_TO_DATE_SLS";
	
	public static final String ROLL_3_MONTH_PEN_AMT = "ROLL_3_MONTH_PEN_AMT";
	public static final String ROLL_3_MONTH_PEN_PCT = "ROLL_3_MONTH_PEN_PCT";
	public static final String YEAR_TO_DATE_SLS_PCT = "YEAR_TO_DATE_SLS_PCT";
	
	public static final String REFRESH_DATE = "REFRESH_DATE";
	public static final String DECLINE_PCT = "DECLINE_PCT";
	public static final String PRIMARY_PRODUCT_CAT_CD = "PRIMARY_PRODUCT_CAT_CD";
	public static final String MESSAGE_DESC = "MESSAGE_DESC";
	public static final String WIR_ENABLED_FLAG = "WIR_ENABLED_FLAG";
	

	/* SPARK REORDER RECOMMENDATION START*/
	public static String SPARKX_REORDERRECOMM_CUST_NUM = "CUST_NUM";
	public static String CUST_EMAILID = "EMAIL";
	public static String CUST_NAME = "CUST_NAME";
	public static String REP_ID = "REP_ID";
	/* SPARK REORDER RECOMMENDATION END*/
	
	/* CDM DATA CONSTANT START*/
	public static final String CALL_ORDER = "CALL_ORDER";
    public static final String POTENTIAL_CALL_VALUE ="POTENTIAL_CALL_VALUE";
	public static final String S_LAST_CONTACTED_DATE = "S_LAST_CONTACTED_DATE";
	public static final String VAP_SCORE ="VAP_SCORE";
	public static final String CUSTOMER_TYPE ="CUSTOMER_TYPE";
	public static final String IAM_ID ="IAM_ID";
	public static final String LAST_LIVE_ORDER_CONTACT_FIRSTNAME = "CONTACT_FIRSTNAME";
	public static final String LAST_LIVE_ORDER_CONTACT_LASTNAME = "CONTACT_LASTNAME";
	public static final String CUSTOMER_SEGMENT_ID = "CUSTOMER_SEGMENT_ID";
	public static final String COMPANY_WEBSITE = "COMPANY_WEBSITE";
	public static final String TOTAL_ALERT_COUNT="TOTAL_ALERT_COUNT";
	public static final String TOTAL_DELETE_COUNT="TOTAL_DELETE_COUNT";
	public static final String ACTIVE="ACTIVE";
	/* CDM DATA CONSTANT ENDS*/

	/*LAST LIVE CONTACT DETAILS START*/
	
	public static final String ACTIVITYDATE = "ACTIVITYDATE";
    public static final String STATUS ="STATUS";
	public static final String SUBJECT = "SUBJECT";
	public static final String CALL_DESCRIPTION ="DESCRIPTION";
	public static final String CONTACT_NAME ="CONTACT_NAME";
	public static final String OWNER_NAME ="OWNER_NAME";
	/*LAST LIVE CONTACT DETAILS END*/
	
	/* ACCOUNT ALERT DETAILS START*/
	public static final String ALERT_ID_COMBINATION="ALERT_ID_COMBINATION";
	public static final String ALERT_ID="ALERT_ID";
	public static final String IS_ALERT_ACTIVE="IS_ALERT_ACTIVE";
	public static final String IS_DELETED="IS_DELETED";
	public static final String IS_READ="IS_READ";
	public static final String LINK_ALERTS="LINK_ALERTS";
	public static final String ALERT_TYPE="ALERT_TYPE";
	public static final String ALERT_START_DATE="ALERT_ACTIVE_DATE";
	public static final String ICON_IMG_PATH="ICON_IMG_PATH";
	public static final String ICON_IMG_NAME="ICON_IMG_NAME";
	public static final String ALERT_SUBJECT="ALERT_SUBJECT";
	public static final String ALERT_SHORT_DESCRIPTION="ALERT_SHORT_DESCRIPTION";
	public static final String ALERT_TXT="ALERT_TXT";
	public static final String MAX_LINK_ALERT="MAX_LINK_ALERT";
	public static final String NO_PARAM_ID="NO_PARAM_ID";
	public static final String NO_PARAM_KEY="NO_PARAM_KEY";
	public static final String NO_PARAM_VAL="NO_PARAM_VAL";
	public static final String PARAM_ID="PARAM_ID";
	public static final String PARAM_LABEL="PARAM_LABEL";
	public static final String PARAM_VALUE="PARAM_VALUE";

	
	/* ACCOUNT ALERT DETAILS END*/
	
	public static final String NEPHOS_PRICE_V4 = "NEPHOS_PRICE";
	public static final String NEPHOS_PRICE_V2 = "NEPHOS";
	
	public static final String NEPHOS_ARKE_CIS = "NEPHOS_ARKE_CIS";
	
	/* YTD CONSTANTS START */
	public static final String YEAR="YEAR";
	public static final String YTD_SALES="YTD_SALES";
	/* YTD CONSTANT ENDS */
}
