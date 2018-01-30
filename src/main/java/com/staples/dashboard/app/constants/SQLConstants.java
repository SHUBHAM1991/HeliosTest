package com.staples.dashboard.app.constants;


/**
 * The Class SQLConstants.
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
public final class SQLConstants {

	public static final String SQL_GET_CUSTOMER_VALUE = "select decode(vap_ref.VAP_SCORE,'0','NA',null,'NA','','NA',vap_ref.VAP_SCORE) VAP_SCORE,ACCOUNT_QUALIFY_SCORE,K_LTV_SCORE from (select customer_number, decode(ACCOUNT_QUALIFY_SCORE,'0','NA',null,'NA','','NA',ACCOUNT_QUALIFY_SCORE) ACCOUNT_QUALIFY_SCORE, to_char(LTV_SCORE) as K_LTV_SCORE from HELIOS_OWN.SA_CUSTOMER_INFO where CUSTOMER_NUMBER = ? and rownum=1)aa left join Helios_own.SA_CUSTOMER_VAP_REF vap_ref on vap_ref.customer_number=aa.customer_number";

	public static final String SQL_GET_SHIPTO_DETAILS = " WITH DT AS   (SELECT TM_KY,CLD_DT,FSC_YR,FSC_PRD,FSC_WK,FSC_DY,TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day'   ), CURR_FISCAL AS (SELECT DISTINCT DT2.CLD_DT,DT2.FSC_YR,DT2.FSC_PRD,DT2.FSC_WK,DT2.FSC_DY,'CURRENT' AS FISCAL_TYPE FROM"
			+" DT DT1,DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR = DT2.FSC_YR AND TO_NUMBER (DT2.FSC_PRD) <= TO_NUMBER (DT1.FSC_PRD)), LAST_FISCAL AS (SELECT DISTINCT DT2.CLD_DT,DT2.FSC_YR,DT2.FSC_PRD,DT2.FSC_WK,DT2.FSC_DY,'LAST' AS FISCAL_TYPE   "
			+" FROM DT DT1,DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR   = DT2.FSC_YR + 1), FISCAL_YEAR AS (SELECT * FROM CURR_FISCAL UNION SELECT * FROM LAST_FISCAL),"
			+ " SHIP_TO AS (   SELECT * FROM (SELECT TRIM (SHIP_TO.CASAD1) CASAD1, TRIM(SHIP_TO.CASAD2) CASAD2, TRIM (SHIP_TO.CASTAT) CASTAT, TRIM(SHIP_TO.CACUST) CACUST, TRIM(SHIP_TO.CASHID) CASHID, TRIM(CASCTY) CASCTY, TRIM(CASSTA) CASSTA, TRIM(CASZPP) CASZPP , ROW_NUMBER() OVER (PARTITION BY CACUST, CASHID ORDER BY CASHP# DESC) LATEST FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFSHTO_T SHIP_TO WHERE SHIP_TO.CACUST= ? ) WHERE LATEST=1 ) ,"
			+ " CURR_TRANS AS( SELECT CF.FSC_YR, CF.FSC_PRD, CF.FISCAL_TYPE, SHIP_TO.CASAD1 SHIPTOADDRESS1,"
			+" SHIP_TO.CASAD2 SHIPTOADDRESS2, SHIP.SHIPTOCITY, SHIP.SHIPTOSTATE, SHIP.SHIPTOZIP, SHIP.SHIPTOKEY, SHIP_TO.CASTAT,CASE WHEN ST.TRANAMOUNT=0 THEN ST.TRANAMOUNT ELSE ST.TRANAMOUNT- NVL(STC.TRANCHARGEAMOUNT,0) END ORIGINAL_LINEAMOUNT,"
			+" ST.SOURCENUMBER,ST.RECORDTYPE FROM SHIP_TO LEFT OUTER JOIN HELIOS_OWN.SALESTRAN ST  ON ST.MASTERNUMBER= TRIM(SHIP_TO.CACUST) INNER JOIN FISCAL_YEAR CF ON TRUNC (ST.ORDERTRANDATE) = TRUNC(CF.CLD_DT) LEFT OUTER JOIN ( SELECT SUM(TRANCHARGEAMOUNT) TRANCHARGEAMOUNT,SALESTRANID FROM HELIOS_OWN.SALESTRANCHARGE STC GROUP BY SALESTRANID)STC    ON  ST.SALESTRANID=STC.SALESTRANID  "
			+" LEFT OUTER JOIN(SELECT SP.SALESTRANID,SP.SHIPMENTID,SP.SHIPTOKEY,SP.SHIPTOCITY,SP.SHIPTOSTATE,SP.SHIPTOZIP, ROW_NUMBER() OVER (PARTITION BY SALESTRANID ORDER BY SOURCEUPDATEDATE DESC) LATEST FROM HELIOS_OWN.SHIPMENT SP ) SHIP ON (ST.SALESTRANID = SHIP.SALESTRANID"   
			+" AND SHIP.LATEST    =1 AND SHIP.SHIPTOKEY=SHIP_TO.CASHID) where ST.MASTERNUMBER= ? AND ST.SOURCESYSTEMID=103  AND TRANTYPE IN ('IO','AJ','OR')),SHIP_TO_TRANACTION AS ( SELECT DISTINCT  SHIPTOKEY as shipToId,(SHIPTOADDRESS1||' '||SHIPTOADDRESS2) as ADDRESS,SHIPTOCITY AS CITY,"
			+" SHIPTOSTATE AS STATE ,MAX(SHIPTOZIP) AS ZIP, CASTAT AS SHIPTOSTATUS, SUM(CASE WHEN FISCAL_TYPE = 'LAST' then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL end)as LASTYRAMOUNT,SUM(CASE WHEN FISCAL_TYPE = 'CURRENT' then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL" 
			+" end)as CURRENTYRAMOUNT,COUNT(DISTINCT CASE WHEN FISCAL_TYPE = 'CURRENT' THEN SOURCENUMBER ELSE NULL END) AS CURRENTYRNO, COUNT(DISTINCT CASE WHEN FISCAL_TYPE = 'LAST' THEN SOURCENUMBER ELSE NULL END) AS LASTYRNO,"
			+" SUM(CASE WHEN FISCAL_TYPE = 'LAST' and FSC_PRD<=(select max(FSC_PRD) from CURR_FISCAL) then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL end)as LASTYRSALESPERIOD FROM CURR_TRANS CT WHERE CT.SHIPTOKEY IS NOT NULL"  
			+" GROUP BY CT.SHIPTOKEY,CT.SHIPTOADDRESS1,CT.SHIPTOADDRESS2,CT.SHIPTOCITY,CT.SHIPTOSTATE, CT.CASTAT"
			+ " ) SELECT * FROM(  SELECT * FROM SHIP_TO_TRANACTION UNION SELECT CASHID shipToId,CASAD1||' '||CASAD2 ADDRESS,CASCTY CITY,CASSTA STATE,CASZPP ZIP,CASTAT SHIPTOSTATUS,NULL,NULL,NULL,NULL,NULL FROM SHIP_TO WHERE CASTAT='ACT' AND CASHID NOT IN (SELECT shipToId FROM SHIP_TO_TRANACTION) ) ORDER BY CURRENTYRAMOUNT DESC NULLS LAST,LASTYRAMOUNT DESC NULLS LAST ";
            
	public static final String SQL_GET_SHIPTO_DETAILS_MM = " WITH DT AS   (SELECT TM_KY,CLD_DT,FSC_YR,FSC_PRD,FSC_WK,FSC_DY,TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day'   ), CURR_FISCAL AS (SELECT DISTINCT DT2.CLD_DT,DT2.FSC_YR,DT2.FSC_PRD,DT2.FSC_WK,DT2.FSC_DY,'CURRENT' AS FISCAL_TYPE FROM"
			+" DT DT1,DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR = DT2.FSC_YR AND TO_NUMBER (DT2.FSC_PRD) <= TO_NUMBER (DT1.FSC_PRD)), LAST_FISCAL AS (SELECT DISTINCT DT2.CLD_DT,DT2.FSC_YR,DT2.FSC_PRD,DT2.FSC_WK,DT2.FSC_DY,'LAST' AS FISCAL_TYPE   "
			+" FROM DT DT1,DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR   = DT2.FSC_YR + 1), FISCAL_YEAR AS (SELECT * FROM CURR_FISCAL UNION SELECT * FROM LAST_FISCAL),"
			+ " SHIP_TO AS (   SELECT * FROM (SELECT TRIM (SHIP_TO.CASAD1) CASAD1, TRIM(SHIP_TO.CASAD2) CASAD2, TRIM (SHIP_TO.CASTAT) CASTAT, TRIM(SHIP_TO.CACUST) CACUST, TRIM(SHIP_TO.CASHID) CASHID, TRIM(CASCTY) CASCTY, TRIM(CASSTA) CASSTA, TRIM(CASZPP) CASZPP , ROW_NUMBER() OVER (PARTITION BY CACUST, CASHID ORDER BY CASHP# DESC) LATEST FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFSHTO_T SHIP_TO WHERE SHIP_TO.CACUST= :custNum ) WHERE LATEST=1 ) ,"
			+ " CURR_TRANS AS( SELECT CF.FSC_YR, CF.FSC_PRD, CF.FISCAL_TYPE, SHIP_TO.CASAD1 SHIPTOADDRESS1,"
			+" SHIP_TO.CASAD2 SHIPTOADDRESS2, SHIP.SHIPTOCITY, SHIP.SHIPTOSTATE, SHIP.SHIPTOZIP, SHIP.SHIPTOKEY, SHIP_TO.CASTAT,CASE WHEN ST.TRANAMOUNT=0 THEN ST.TRANAMOUNT ELSE ST.TRANAMOUNT- NVL(STC.TRANCHARGEAMOUNT,0) END ORIGINAL_LINEAMOUNT,"
			+" ST.SOURCENUMBER,ST.RECORDTYPE FROM SHIP_TO LEFT OUTER JOIN HELIOS_OWN.SALESTRAN ST  ON ST.MASTERNUMBER= TRIM(SHIP_TO.CACUST) INNER JOIN FISCAL_YEAR CF ON TRUNC (ST.ORDERTRANDATE) = TRUNC(CF.CLD_DT) LEFT OUTER JOIN ( SELECT SUM(TRANCHARGEAMOUNT) TRANCHARGEAMOUNT,SALESTRANID FROM HELIOS_OWN.SALESTRANCHARGE STC GROUP BY SALESTRANID)STC    ON  ST.SALESTRANID=STC.SALESTRANID  "
			+" LEFT OUTER JOIN(SELECT SP.SALESTRANID,SP.SHIPMENTID,SP.SHIPTOKEY,SP.SHIPTOCITY,SP.SHIPTOSTATE,SP.SHIPTOZIP, ROW_NUMBER() OVER (PARTITION BY SALESTRANID ORDER BY SOURCEUPDATEDATE DESC) LATEST FROM HELIOS_OWN.SHIPMENT SP ) SHIP ON (ST.SALESTRANID = SHIP.SALESTRANID"   
			+" AND SHIP.LATEST    =1 AND SHIP.SHIPTOKEY=SHIP_TO.CASHID) where ST.MASTERNUMBER= :custNum AND ST.SOURCESYSTEMID=103  AND TRANTYPE IN ('IO','AJ','OR')),SHIP_TO_TRANACTION AS ( SELECT DISTINCT  SHIPTOKEY as shipToId,(SHIPTOADDRESS1||' '||SHIPTOADDRESS2) as ADDRESS,SHIPTOCITY AS CITY,"
			+" SHIPTOSTATE AS STATE ,MAX(SHIPTOZIP) AS ZIP, CASTAT AS SHIPTOSTATUS, SUM(CASE WHEN FISCAL_TYPE = 'LAST' then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL end)as LASTYRAMOUNT,SUM(CASE WHEN FISCAL_TYPE = 'CURRENT' then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL" 
			+" end)as CURRENTYRAMOUNT,COUNT(DISTINCT CASE WHEN FISCAL_TYPE = 'CURRENT' THEN SOURCENUMBER ELSE NULL END) AS CURRENTYRNO, COUNT(DISTINCT CASE WHEN FISCAL_TYPE = 'LAST' THEN SOURCENUMBER ELSE NULL END) AS LASTYRNO,"
			+" SUM(CASE WHEN FISCAL_TYPE = 'LAST' and FSC_PRD<=(select max(FSC_PRD) from CURR_FISCAL) then (NVL(ORIGINAL_LINEAMOUNT,0)) ELSE NULL end)as LASTYRSALESPERIOD FROM CURR_TRANS CT WHERE CT.SHIPTOKEY IS NOT NULL"  
			+" GROUP BY CT.SHIPTOKEY,CT.SHIPTOADDRESS1,CT.SHIPTOADDRESS2,CT.SHIPTOCITY,CT.SHIPTOSTATE, CT.CASTAT"
			+ " ) SELECT * FROM(  SELECT * FROM SHIP_TO_TRANACTION UNION SELECT CASHID shipToId,CASAD1||' '||CASAD2 ADDRESS,CASCTY CITY,CASSTA STATE,CASZPP ZIP,CASTAT SHIPTOSTATUS,NULL,NULL,NULL,NULL,NULL FROM SHIP_TO WHERE CASTAT='ACT' AND CASHID NOT IN (SELECT shipToId FROM SHIP_TO_TRANACTION) )ss where ss.SHIPTOSTATUS IN(:status) ORDER BY CURRENTYRAMOUNT DESC NULLS LAST,LASTYRAMOUNT DESC NULLS LAST ";		
			/* "select * from (SELECT NULLIF(LST_YR_NUM_ORDERS,0) AS LASTYRNO, "
			+ " NULLIF(LST_YR_ORDER_AMOUNT,0)          AS LASTYRAMOUNT, "
			+ " NULLIF(CURR_YR_NUM_ORDERS,0)           AS CURRENTYRNO, "
			+ " NULLIF(CURR_YR_ORDER_AMOUNT,0)        AS CURRENTYRAMOUNT, "
			+ " SHIP_TO                      AS CASHID, " + " SHIP_TO_ADDR                 AS ADDRESS , "
			+ " SHIP_TO_CITY                 AS CITY , " + " SHIP_TO_STATE                AS STATE , "
			+ " SHIP_TO_ZIP                  AS ZIP " + " FROM helios_own.mv_ship_to_info "
			+ " WHERE customer_number = ? ) "
			+ "ORDER BY case when  currentyrno is not null and CURRENTYRAMOUNT is not null and lastyrno is not null and lastyramount is not null then currentyrno end asc, case when currentyrno is not null then currentyrno end asc, "
			+ " case when CURRENTYRAMOUNT is not null then CURRENTYRAMOUNT end asc, "
			+ " case when  lastyrno is not null and lastyramount is not null then lastyrno end asc, case when lastyrno is not null then lastyrno  end asc, "
			+ " lastyramount asc";*/
			

	// public static final String SQL_GET_SUPER_USER_DETAILS_AND_INFO =
	// " SELECT"
	// + " Y.ORDER_CONTACT,"
	// + " Y.ORDERS,"
	// + " Y.ORDERS_CURR,"
	// + " Y.TOTAL_SALES,"
	// + " Y.TOTAL_SALES_CURR,"
	// + " NVL(to_char(VISIT_COUNT),'-') VISIT_COUNT,"
	// + " trunc(LAST_VISIT_DATE) AS RECENT_VISIT_DATE, "
	// + " (case when INSTR(CP.EMAIL,',',1,1)=0 then CP.EMAIL "
	// + " else SUBSTR(CP.EMAIL,1,INSTR(CP.EMAIL,',',1,1)-1) end)EMAIL, "
	// +
	// " (case when INSTR(CP.PHONE_NUMBER,'-')=0 then
	// '('||SUBSTR(CP.PHONE_NUMBER,1,3)||')'||' '"
	// +
	// "||SUBSTR(CP.PHONE_NUMBER,4,3)||'-'||SUBSTR(CP.PHONE_NUMBER,7,4) when
	// INSTR(CP.PHONE_NUMBER,'-')=0 and length(CP.phone_number)>10 then
	// '('||SUBSTR(CP.PHONE_NUMBER,1,3)||')'||' '"
	// +
	// "||SUBSTR(CP.PHONE_NUMBER,4,3)||'-'||SUBSTR(CP.PHONE_NUMBER,7,4)||'
	// *'||SUBSTR(CP.PHONE_NUMBER,11,length(CP.PHONE_NUMBER)-10) else
	// PHONE_NUMBER end)PHONE_NUMBER "
	// + " FROM"
	// + " ( SELECT"
	// + " CUSTOMER_NUMBER,"
	// + " ORDER_CONTACT,"
	// + " ORDERS,"
	// + " ORDERS_CURR, "
	// + " TOTAL_SALES,TOTAL_SALES_CURR"
	// + " FROM"
	// + " ( SELECT"
	// + " CUSTOMER_NUMBER,"
	// + " ORDER_CONTACT,"
	// + " SUM (ORDERS) AS ORDERS,"
	// + " SUM (ORDERS_CURR) AS ORDERS_CURR,"
	// +
	// " SUM (TOTAL_SALES) AS TOTAL_SALES,SUM(TOTAL_SALES_CURR) AS
	// TOTAL_SALES_CURR "
	// + " FROM"
	// + " ( SELECT"
	// + " CUSTOMER_NUMBER,"
	// + " ORDERED_BY AS ORDER_CONTACT,"
	// + " ORDER_NUMBER,"
	// + " MIN (ORDER_DATE) AS ORDER_DATE,"
	// + " COUNT (1) AS ORDERS,"
	// +
	// " (CASE WHEN ORDER_DATE > (select min(cld_dt) from helios_own.d_time
	// where fsc_yr=(select fsc_yr from helios_own.d_time where cld_dt=(select
	// to_date(sysdate,'dd-mon-yy') from dual) AND TM_LVL = 'Day'))"
	// +
	// " and ORDER_DATE < (select max(cld_dt) from helios_own.d_time where
	// fsc_yr=(select fsc_yr from helios_own.d_time where cld_dt=(select
	// to_date(sysdate,'dd-mon-yy') from dual) AND TM_LVL = 'Day'))"
	// + " then COUNT(1) else 0 end ) as ORDERS_CURR,"
	// + " SUM (ORDER_AMOUNT) AS TOTAL_SALES, "
	// +
	// " SUM(CASE WHEN ORDER_DATE > (select min(cld_dt) from helios_own.d_time
	// where fsc_yr=(select fsc_yr from helios_own.d_time where cld_dt=(select
	// to_date(sysdate,'dd-mon-yy') from dual) AND TM_LVL = 'Day'))"
	// +
	// " and ORDER_DATE < (select max(cld_dt) from helios_own.d_time where
	// fsc_yr=(select fsc_yr from helios_own.d_time where cld_dt=(select
	// to_date(sysdate,'dd-mon-yy') from dual) AND TM_LVL = 'Day'))"
	// +
	// " then nvl(ORDER_AMOUNT,0) else 0 end) as TOTAL_SALES_CURR"
	// + " FROM"
	// + " HELIOS_OWN.MV_SA_ORDER_HEADER "
	// + " WHERE"
	// + " CUSTOMER_NUMBER = ? "
	// + " GROUP BY"
	// + " CUSTOMER_NUMBER,"
	// + " ORDER_DATE,"
	// + " ORDER_NUMBER,"
	// + " ORDERED_BY "
	// + " ORDER BY"
	// + " CUSTOMER_NUMBER,"
	// + " ORDER_DATE DESC,"
	// + " COUNT (1) DESC) "
	// + " GROUP BY"
	// + " CUSTOMER_NUMBER,"
	// + " ORDER_CONTACT) X "
	//
	// + " ORDER BY"
	// + " ROWNUM) Y "
	// + " LEFT OUTER JOIN"
	// +
	// " (select distinct
	// customer_number,order_contact,last_visit_date,nullif(visit_count,0)
	// visit_count from HELIOS_OWN.SA_WEB_ACTIVITY) N "
	// + " ON Y.CUSTOMER_NUMBER = N.CUSTOMER_NUMBER "
	// + " AND Y.ORDER_CONTACT = N.ORDER_CONTACT "
	// + " LEFT OUTER JOIN"
	// +
	// " (select distinct
	// customer_number,First_name,last_name,email,phone_number from
	// HELIOS_OWN.SA_CUSTOMER_PROFILE) CP"
	// +
	// " ON Y.CUSTOMER_NUMBER = CP.CUSTOMER_NUMBER AND Y.ORDER_CONTACT =
	// UPPER(CP.FIRST_NAME || ' ' || CP.LAST_NAME)"
	// + " ORDER BY" + " ORDERS DESC";

	public static final String SQL_GET_CUSTOMER_PROFILE_DETAILS = "select * from (select cc.*,ROW_NUMBER() over (partition by cc.rep_cd order by cc.CUST_NUM )as rowsorder "

			+ " from (SELECT CUSTOMER_NUMBER AS CUST_NUM, DIVISION, " + " COMPANY_NAME,"
			+ " COMPANY_ADDR_1, COMPANY_ADDR_2, COMPANY_CITY, COMPANY_STATE, COMPANY_COUNTRY, COMPANY_ZIP, COMPANY_PHONE,"
			+ " FIRST_NAME " + " || ' ' " + " || LAST_NAME AS FIRST_NAME ," + " LAST_NAME," + " EMAIL AS EMAIL_ID,"
			+ " PHONE_NUMber AS PHONE_NUM, " + " CONTRACT_TYPE AS CUSTOMER_TYPE,"
			+ " UPPER(ACCOUNT_MANAGER_NAME) AS MGRNAME, " + " UPPER(ACCOUNT_MANAGER_EMAIL) AS MGREMAIL,"
			+ " UPPER(ACCOUNT_MANAGER_PHONE) MGRPHONE,(case when rep_role_cd not in ('IAC') then 'DD' else rep_role_cd end )as rep_cd ,"
			+ " NVL(IAM_ID,'-') AS IAMID, " 
			
			+ "(SELECT TO_CHAR (LAST_LIVE_CONTACT_DATE, 'MM/DD/YYYY') "
			+ " FROM ( select max(activity_date) as last_live_contact_date "
			+ " from ( SELECT TRUNC (MAX(ActivityDate)) AS activity_date "
			+ " FROM (select max (id ) as sf_id, master_account_c from cex01_own.crm_sfdcown_account_c_land "
			+ " where key_type__c = 'Sunrise Master' and master_account_c = :custNum group by master_account_c) a "
			+ " left join HELIOS_OWN.CRM_SFDCOWN_STG_TASK t on t.accountid = a.sf_id "
			+ " AND t.STATUS = 'Task Completed - Live Contact' "
			+ " union "
			+ " SELECT TRUNC (max(TASK_INSERT_DATE)) AS activity_date FROM helios_own.sa_segment_detail "
			+ " WHERE customer_number = LPAD ( :custNum, 10, 0) AND SEG_DISP_STATUS = 'Task Completed - Live Contact' ) ) )"
						
			
		/*	+ "(SELECT to_char(LAST_LIVE_CONTACT_DATE, 'MM/DD/YYYY') "
			+ "		FROM (SELECT LAST_LIVE_CONTACT_DATE FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO "
			+ "		WHERE SA_CUSTOMER_SFDC_INFO.MASTER_CUSTOMER_NUMBER = CUSTOMER_NUMBER "
			+ "		ORDER BY LAST_LIVE_CONTACT_DATE DESC ) WHERE ROWNUM = 1) " */
			
			+ " AS LAST_CONTACTED_DATE ,COMPANY_WEBSITE"
			+ " FROM HELIOS_OWN.SA_CUSTOMER_PROFILE" + " WHERE CUSTOMER_NUMBER  = :custNum"
			+ " AND COMPANY_NAME IS NOT NULL )cc )  where rowsorder=1 order by rep_cd asc";

	public static final String SQL_GET_CUSTOMER_PROFILE_DETAILS_PREMIUM = "select * from (select cc.*,ROW_NUMBER() over (partition by cc.rep_cd order by cc.CUST_NUM )as rowsorder "

			+ " from (SELECT SCP.CUSTOMER_NUMBER AS CUST_NUM, DIVISION, " + " SCP.COMPANY_NAME,"
			+ " SCP.COMPANY_ADDR_1, SCP.COMPANY_ADDR_2, SCP.COMPANY_CITY, SCP.COMPANY_STATE, SCP.COMPANY_COUNTRY, SCP.COMPANY_ZIP, SCP.COMPANY_PHONE,"
			+ " SCP.FIRST_NAME " + " || ' ' " + " || SCP.LAST_NAME AS FIRST_NAME ," + " SCP.LAST_NAME,"
			+ " SCP.EMAIL AS EMAIL_ID," + " SCP.PHONE_NUMber AS PHONE_NUM, " + " SCP.CONTRACT_TYPE AS CUSTOMER_TYPE,"
			+ " UPPER(ACCOUNT_MANAGER_NAME) AS MGRNAME, " + " UPPER(ACCOUNT_MANAGER_EMAIL) AS MGREMAIL,"
			+ " UPPER(ACCOUNT_MANAGER_PHONE) MGRPHONE,(case when rep_role_cd not in ('IAC') then 'DD' else rep_role_cd end )as rep_cd,SPL.LTS as LTS, "
			+ " NVL(SCP.IAM_ID,'-') AS IAMID, " 
			
			+ "(SELECT TO_CHAR (LAST_LIVE_CONTACT_DATE, 'MM/DD/YYYY') "
			+ " FROM ( select max(activity_date) as last_live_contact_date "
			+ " from ( SELECT TRUNC (MAX(ActivityDate)) AS activity_date "
			+ " FROM (select max (id ) as sf_id, master_account_c from cex01_own.crm_sfdcown_account_c_land "
			+ " where key_type__c = 'Sunrise Master' and master_account_c = :custNum group by master_account_c) a "
			+ " left join HELIOS_OWN.CRM_SFDCOWN_STG_TASK t on t.accountid = a.sf_id "
			+ " AND t.STATUS = 'Task Completed - Live Contact' "
			+ " union "
			+ " SELECT TRUNC (max(TASK_INSERT_DATE)) AS activity_date FROM helios_own.sa_segment_detail "
			+ " WHERE customer_number = LPAD ( :custNum, 10, 0) AND SEG_DISP_STATUS = 'Task Completed - Live Contact' ) ) )"			
			
			
		/*	+ "(SELECT to_char(LAST_LIVE_CONTACT_DATE, 'MM/DD/YYYY') "
			+ "		FROM (SELECT LAST_LIVE_CONTACT_DATE FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO "
			+ "		WHERE SA_CUSTOMER_SFDC_INFO.MASTER_CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER "
			+ "		ORDER BY LAST_LIVE_CONTACT_DATE DESC ) WHERE ROWNUM = 1) " */
			
			+ " AS LAST_CONTACTED_DATE ,COMPANY_WEBSITE"
			+ " FROM HELIOS_OWN.SA_CUSTOMER_PROFILE SCP,HELIOS_OWN.SA_PREMIUM_LTS SPL"
			+ " WHERE SCP.CUSTOMER_NUMBER=SPL.CUSTOMER_NUMBER AND SCP.CUSTOMER_NUMBER  = :custNum"
			+ " AND SCP.COMPANY_NAME IS NOT NULL )cc )  where rowsorder=1 order by rep_cd asc";


	public static final String SQL_GET_CUSTOMER_PROFILE_INFO_1 = " SELECT sub.MASTER_CUST_NUM," + "   sub.EMAIL_ID,"
			+ "   sub.COMPANY_NAME," + "   sub.CUSTOMER_TYPE," + "   sub.AGENT_EMAIL_ID," + "   sub.CALL_ORDER,"
			+ "   cs.PLAY_ID_LIST," + "   sub.ACCT_MNGR_FLAG" + " FROM" + "   (SELECT SK.MASTER_CUST_NUM,"
			+ "     SK.EMAIL_ID," + "     SK.COMPANY_NAME," + "     SK.CUSTOMER_TYPE," + "     SK.AGENT_EMAIL_ID,"
			+ "     NVL(scv.call_order,0) AS CALL_ORDER," + "     SK.ACCT_MNGR_FLAG" + "   FROM"
			+ "     (SELECT DISTINCT CP.CUSTOMER_NUMBER AS MASTER_CUST_NUM,"
			+ "       UPPER (CP.ACCOUNT_MANAGER_EMAIL)  AS EMAIL_ID," + "       CP.COMPANY_NAME,"
			+ "       CP.CONTRACT_TYPE   AS CUSTOMER_TYPE,"
			+ "       CASE ? WHEN ACCNT_MGR_EMP_NUMBER THEN UPPER(ACCOUNT_MANAGER_NAME) WHEN VP_EMP_NUMBER THEN UPPER(CP.VP_FULL_NAME) WHEN DRM_EMP_NUMBER THEN UPPER(CP.DRM_FULL_NAME) WHEN RVP_EMP_NUMBER THEN UPPER(CP.RVP_FULL_NAME)"
			+ "        WHEN RSD_EMP_NUMBER THEN UPPER(CP.RSD_FULL_NAME) WHEN DSM_EMP_NUMBER THEN UPPER(CP.DSM_FULL_NAME) end AS AGENT_EMAIL_ID,"
			+ "        CASE ? WHEN ACCNT_MGR_EMP_NUMBER THEN 'TRUE' ELSE 'FALSE' END AS ACCT_MNGR_FLAG"
			+ "     FROM Helios_own.SA_CUSTOMER_PROFILE CP"

			+ "     where (CP.ACCNT_MGR_EMP_NUMBER =  ? or CP.DRM_EMP_NUMBER=  ? or CP.VP_EMP_NUMBER= ? or CP.RVP_EMP_NUMBER =  ? or CP.RSD_EMP_NUMBER= ? or CP.DSM_EMP_NUMBER= ?)"
			+ "     ) SK"
			+ "   LEFT OUTER JOIN (select customer_number, min(CALL_ORDER) as call_order from Helios_own.SA_CUSTOMER_INFO group by customer_number) SCV"
			+ "   ON SK.MASTER_CUST_NUM = SCV.CUSTOMER_NUMBER" + "   ) sub"
			+ " LEFT OUTER JOIN helios_own.SA_CUSTOMER_SEGMENT cs" + " ON cs.CUSTOMER_NUMBER = sub.MASTER_CUST_NUM"
			+ " ORDER BY call_order";

	public static final String SQL_GET_SEGMENT_DETAIL_CLOSE_CUSTEXO = ")";

	/*
	public static final String SQL_GET_PURCHASE_DETAIL_WITHOUT_MONTH_SUNRISE = "select * from ((SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)  AS ORDER_AMOUNT,  "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND oh.OHCRDT >= ? and oh.OHCRDT <= ? and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD#  inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO') "
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT" + " )"
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	public static final String SQL_GET_PURCHASE_DETAIL_WITHOUT_MONTH_SUNRISE_CAT = "select * from ( (SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)  AS ORDER_AMOUNT,  "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND oh.OHCRDT >= ? and oh.OHCRDT <= ? and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD#   inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO') "
			+ " LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) p ON to_char(L.ODSKU#) = P.SKU_NUM "
			+ " WHERE (case when PRIMARY_PRODUCT_CAT_CD = 9 then 10 when PRIMARY_PRODUCT_CAT_CD = 8 then 11 else to_number(NVL(PRIMARY_PRODUCT_CAT_CD,11)) end) = ?"
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT" + " )"
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	public static final String SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SUNRISE = "select * from ((SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)  AS ORDER_AMOUNT, "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND to_date(to_char(oh.OHCRDT),'yyyymmdd') > (sysdate + (?)) and length(oh.OHCRDT)=8 and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD# inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK  and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO')"
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT" + " )"
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	public static final String SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SUNRISE_CAT = "select * from ((SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)  AS ORDER_AMOUNT, "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND to_date(to_char(oh.OHCRDT),'yyyymmdd') > (sysdate + (?)) and length(oh.OHCRDT)=8 and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD# inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK  and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO')"
			+ " LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) p ON to_char(L.ODSKU#) = P.SKU_NUM  "
			+ " WHERE (case when PRIMARY_PRODUCT_CAT_CD = 9 then 10 when PRIMARY_PRODUCT_CAT_CD = 8 then 11 else to_number(NVL(PRIMARY_PRODUCT_CAT_CD,11)) end) = ?"
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT" + " )  "
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	public static final String SQL_GET_PURCHASE_DETAIL_WITH_MONTH_SUNRISE = "select * from ( ( SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)     AS ORDER_AMOUNT, "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND oh.OHCRDT >= ? AND oh.OHCRDT <= ? and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD# inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO') "
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT " + ") "
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	public static final String SQL_GET_PURCHASE_DETAIL_WITH_MONTH_SUNRISE_CAT = "select * from ( ( SELECT H.OHCUST as CUST_NUM,"
			+ " H.OHORD# AS ORDER_NUMBER," + "count(H.OHORD#) AS ORDER_LINE_COUNT , "
			+ " to_char(to_date(to_char(H.OHCRDT),'yyyymmdd'),'mm/dd/yyyy') ORDER_DATE,"
			+ " SUM(CASE WHEN l.ODCONV > 1 THEN l.ODAPRC / l.ODCONV ELSE l.ODAPRC END  * l.ODSQTY)     AS ORDER_AMOUNT,  "
			+ " MIN ( CASE WHEN UPPER (RTRIM (LTRIM (OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO', 'AJ') THEN RTRIM (LTRIM (OHL.OHOBFN)) || ' ' || RTRIM (LTRIM (OHL.OHOBLN)) END) AS ORDER_CONTACT "
			+ " FROM (select oh.OHCUST, oh.OHORD#, min(oh.OHCRDT) as OHCRDT FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T oh where oh.OHCUST = ? AND oh.OHCRDT >= ? AND oh.OHCRDT <= ? and UPPER(RTRIM (LTRIM (oh.OHTYPE))) <> 'AJ' GROUP BY oh.OHCUST, oh.OHORD# ) h"
			+ " join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l on h.OHORD#  = l.ODORD# inner join HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T OHL on OHL.OHORD# = l.ODORD# and OHL.OHLINK = l.ODLINK and UPPER(RTRIM(LTRIM(OHL.OHTYPE))) NOT IN ('RE', 'QU', 'MO') "
			+ " LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) p ON to_char(L.ODSKU#) = P.SKU_NUM  "
			+ " WHERE (case when PRIMARY_PRODUCT_CAT_CD = 9 then 10 when PRIMARY_PRODUCT_CAT_CD = 8 then 11 else to_number(NVL(PRIMARY_PRODUCT_CAT_CD,11)) end) = ? "
			+ " GROUP BY H.OHCUST," + " H.OHORD#," + " H.OHCRDT" + ")    "
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from HELIOS_OWN.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order) order by order_date desc";
	*/
	
	
	public static final String SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SALESTRAN = "SELECT order_number, "
            + " order_date, "
            + " cust_num, "
            + " order_amount, "
            + " order_line_count, "
            + " order_contact, "
            + " saving_order, "
            + " category, "
            + " amount "
     + " FROM   (SELECT ST.sourcenumber                                AS "
                    + " ORDER_NUMBER, "
                    + " To_char (Min (ST.ordertrandate), 'mm/dd/yyyy') AS "
                    + " ORDER_DATE, "
                    + " ST.masternumber                                AS "
                    + " CUST_NUM, "
                    + " ST.original_lineamount                         AS "
                    + " ORDER_AMOUNT, "
                    + " Count (SD.sourcenumber)                        AS "
                    + " ORDER_LINE_COUNT, "
                    + " Min (ST.shiptofirstname "
                         + " || ' ' "
                         + " || ST.shiptolastname)                     AS "
                    + " ORDER_CONTACT "
             + " FROM   (SELECT STA.salestranid, "
                            + " STA.masternumber, "
                            + " STA.sourcenumber, "
                            + " STA.ordertrandate, "
                            + " SUM(CASE "
                              + " WHEN STA.tranamount = 0 THEN STA.tranamount "
                              + " ELSE STA.tranamount - "
                                   + " Nvl ((SELECT SUM(STC.tranchargeamount) FROM helios_own.salestrancharge STC where STA.salestranid = STC.salestranid), 0) "
                            + " END) OVER (PARTITION BY STA.sourcenumber) as  ORIGINAL_LINEAMOUNT, "
                            + " STA.shiptofirstname, "
                            + " STA.shiptolastname "
                     + " FROM   (SELECT STMS.masternumber, "
                                    + " STMS.sourcenumber "
                             + " FROM   helios_own.salestran STMS "
                             + " WHERE  STMS.masternumber = ? "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') > "
                                        + " (TO_DATE(SYSDATE + (?), 'DD-MON-YY'))"
                                    + " AND STMS.sourcesystemid = 103 "
                                    + " AND STMS.trantype IN ( 'IO', 'OR' ) "
                             + " GROUP  BY STMS.masternumber, "
                                       + " STMS.sourcenumber) GSTMS "
                            + " inner join helios_own.salestran STA "
                                    + " ON GSTMS.masternumber = STA.masternumber "
                                       + " AND GSTMS.sourcenumber = "
                                           + " STA.sourcenumber AND STA.trantype IN ( 'IO', 'AJ', 'OR' )"
                    + " ) ST "
                    + " inner join helios_own.salestrandetail SD "
                            + " ON ST.salestranid = SD.salestranid "
             + " GROUP  BY ST.sourcenumber, "
                       + " ST.masternumber, "
                       + " ST.original_lineamount) AA "
            + " left join (SELECT order_number "
                              + " SAVING_ORDER, "
                              + " Listagg (prm_savings_category, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " CATEGORY, "
                              + " Listagg (amount, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " AMOUNT "
                       + " FROM   (SELECT order_number, "
                                      + " prm_savings_category, "
                                      + " SUM (prm_savings_amount) AMOUNT "
                               + " FROM   helios_own.sa_premium_savings "
                               + " WHERE  customer_number = ? "
                               + " GROUP  BY order_number, "
                                         + " prm_savings_category) "
                       + " GROUP  BY order_number) BB "
                   + " ON AA.order_number = BB.saving_order"
+ " ORDER  BY order_date DESC";
	
	
	public static final String SQL_GET_PURCHASE_DETAIL_LAST_DAYS_SALESTRAN_CAT = "SELECT order_number, "
            + " order_date, "
            + " cust_num, "
            + " order_amount, "
            + " order_line_count, "
            + " order_contact, "
            + " saving_order, "
            + " category, "
            + " amount "
     + " FROM   (SELECT ST.sourcenumber                                AS "
                    + " ORDER_NUMBER, "
                    + " To_char (Min (ST.ordertrandate), 'mm/dd/yyyy') AS "
                    + " ORDER_DATE, "
                    + " ST.masternumber                                AS "
                    + " CUST_NUM, "
                    + " ST.original_lineamount                         AS "
                    + " ORDER_AMOUNT, "
                    + " Count (SD.sourcenumber)                        AS "
                    + " ORDER_LINE_COUNT, "
                    + " Min (ST.shiptofirstname "
                         + " || ' ' "
                         + " || ST.shiptolastname)                     AS "
                    + " ORDER_CONTACT "
             + " FROM   (SELECT STA.salestranid, "
                            + " STA.masternumber, "
                            + " STA.sourcenumber, "
                            + " STA.ordertrandate, "
                            + " SUM(CASE "
                              + " WHEN STA.tranamount = 0 THEN STA.tranamount "
                              + " ELSE STA.tranamount - "
                                   + " Nvl ((SELECT SUM(STC.tranchargeamount) FROM helios_own.salestrancharge STC where STA.salestranid = STC.salestranid), 0) "
                            + " END) OVER (PARTITION BY STA.sourcenumber) as ORIGINAL_LINEAMOUNT, "
                            + " STA.shiptofirstname, "
                            + " STA.shiptolastname "
                     + " FROM   (SELECT STMS.masternumber, "
                                    + " STMS.sourcenumber "
                             + " FROM   helios_own.salestran STMS "
                             + " WHERE  STMS.masternumber = ? "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') > "
                                        + " (TO_DATE(SYSDATE + (?), 'DD-MON-YY'))"
                                    + " AND STMS.sourcesystemid = 103 "
                                    + " AND STMS.trantype IN ( 'IO', 'OR' ) "
                             + " GROUP  BY STMS.masternumber, "
                                       + " STMS.sourcenumber) GSTMS "
                            + " inner join helios_own.salestran STA "
                                    + " ON GSTMS.masternumber = STA.masternumber "
                                       + " AND GSTMS.sourcenumber = "
                                           + " STA.sourcenumber AND STA.trantype IN ( 'IO', 'AJ', 'OR' )"
                    + " ) ST "
                    + " inner join helios_own.salestrandetail SD "
                            + " ON ST.salestranid = SD.salestranid "
                            + " left outer join (SELECT DISTINCT sku_num, "
                         + " primary_product_cat_cd "
         + " FROM   helios_own.sa_product_master) p" 
         + " ON SD.productsku = P.sku_num" 
				+ " WHERE  ( CASE" 
				          + " WHEN primary_product_cat_cd = 9 THEN 10 "
				          + " WHEN primary_product_cat_cd = 8 THEN 11" 
				          + " ELSE To_number(Nvl(primary_product_cat_cd, 11))" 
				        + " END ) = ?"
             + " GROUP  BY ST.sourcenumber, "
                       + " ST.masternumber, "
                       + " ST.original_lineamount) AA "
            + " left join (SELECT order_number "
                              + " SAVING_ORDER, "
                              + " Listagg (prm_savings_category, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " CATEGORY, "
                              + " Listagg (amount, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " AMOUNT "
                       + " FROM   (SELECT order_number, "
                                      + " prm_savings_category, "
                                      + " SUM (prm_savings_amount) AMOUNT "
                               + " FROM   helios_own.sa_premium_savings "
                               + " WHERE  customer_number = ? "
                               + " GROUP  BY order_number, "
                                         + " prm_savings_category) "
                       + " GROUP  BY order_number) BB "
                   + " ON AA.order_number = BB.saving_order"
+ " ORDER  BY order_date DESC";
	
	public static final String SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN = "SELECT order_number, "
            + " order_date, "
            + " cust_num, "
            + " order_amount, "
            + " order_line_count, "
            + " order_contact, "
            + " saving_order, "
            + " category, "
            + " amount "
     + " FROM   (SELECT ST.sourcenumber                                AS "
                    + " ORDER_NUMBER, "
                    + " To_char (Min (ST.ordertrandate), 'mm/dd/yyyy') AS "
                    + " ORDER_DATE, "
                    + " ST.masternumber                                AS "
                    + " CUST_NUM, "
                    + " ST.original_lineamount                         AS "
                    + " ORDER_AMOUNT, "
                    + " Count (SD.sourcenumber)                        AS "
                    + " ORDER_LINE_COUNT, "
                    + " Min (ST.shiptofirstname "
                         + " || ' ' "
                         + " || ST.shiptolastname)                     AS "
                    + " ORDER_CONTACT "
             + " FROM   (SELECT STA.salestranid, "
                            + " STA.masternumber, "
                            + " STA.sourcenumber, "
                            + " STA.ordertrandate, "
                            + " SUM(CASE "
                              + " WHEN STA.tranamount = 0 THEN STA.tranamount "
                              + " ELSE STA.tranamount - "
                                   + " Nvl ((SELECT SUM(STC.tranchargeamount) FROM helios_own.salestrancharge STC where STA.salestranid = STC.salestranid), 0) "
                            + " END) OVER (PARTITION BY STA.sourcenumber) as ORIGINAL_LINEAMOUNT, "
                            + " STA.shiptofirstname, "
                            + " STA.shiptolastname "
                     + " FROM   (SELECT STMS.masternumber, "
                                    + " STMS.sourcenumber "
                             + " FROM   helios_own.salestran STMS "
                             + " WHERE  STMS.masternumber = ? "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') >= "
                                        + " To_date (?, 'YYYYMMDD') "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') <= "
                                        + " To_date (?, 'YYYYMMDD') "
                                    + " AND STMS.sourcesystemid = 103 "
                                    + " AND STMS.trantype IN ( 'IO', 'OR' ) "
                             + " GROUP  BY STMS.masternumber, "
                                       + " STMS.sourcenumber) GSTMS "
                            + " inner join helios_own.salestran STA "
                                    + " ON GSTMS.masternumber = STA.masternumber "
                                       + " AND GSTMS.sourcenumber = "
                                           + " STA.sourcenumber AND STA.trantype IN ( 'IO', 'AJ', 'OR' )"
                    + " ) ST "
                    + " inner join helios_own.salestrandetail SD "
                            + " ON ST.salestranid = SD.salestranid "
             + " GROUP  BY ST.sourcenumber, "
                       + " ST.masternumber, "
                       + " ST.original_lineamount) AA "
            + " left join (SELECT order_number "
                              + " SAVING_ORDER, "
                              + " Listagg (prm_savings_category, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " CATEGORY, "
                              + " Listagg (amount, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " AMOUNT "
                       + " FROM   (SELECT order_number, "
                                      + " prm_savings_category, "
                                      + " SUM (prm_savings_amount) AMOUNT "
                               + " FROM   helios_own.sa_premium_savings "
                               + " WHERE  customer_number = ? "
                               + " GROUP  BY order_number, "
                                         + " prm_savings_category) "
                       + " GROUP  BY order_number) BB "
                   + " ON AA.order_number = BB.saving_order"
+ " ORDER  BY order_date DESC";


public static final String SQL_GET_PURCHASE_DETAIL_WITH_DATE_SALESTRAN_CAT = "SELECT order_number, "
            + " order_date, "
            + " cust_num, "
            + " order_amount, "
            + " order_line_count, "
            + " order_contact, "
            + " saving_order, "
            + " category, "
            + " amount "
     + " FROM   (SELECT ST.sourcenumber                                AS "
                    + " ORDER_NUMBER, "
                    + " To_char (Min (ST.ordertrandate), 'mm/dd/yyyy') AS "
                    + " ORDER_DATE, "
                    + " ST.masternumber                                AS "
                    + " CUST_NUM, "
                    + " ST.original_lineamount                         AS "
                    + " ORDER_AMOUNT, "
                    + " Count (SD.sourcenumber)                        AS "
                    + " ORDER_LINE_COUNT, "
                    + " Min (ST.shiptofirstname "
                         + " || ' ' "
                         + " || ST.shiptolastname)                     AS "
                    + " ORDER_CONTACT "
             + " FROM   (SELECT STA.salestranid, "
                            + " STA.masternumber, "
                            + " STA.sourcenumber, "
                            + " STA.ordertrandate, "
                            + " SUM(CASE "
                              + " WHEN STA.tranamount = 0 THEN STA.tranamount "
                              + " ELSE STA.tranamount - "
                                   + " Nvl ((SELECT SUM(STC.tranchargeamount) FROM helios_own.salestrancharge STC where STA.salestranid = STC.salestranid), 0) "
                            + " END) OVER (PARTITION BY STA.sourcenumber) as ORIGINAL_LINEAMOUNT, "
                            + " STA.shiptofirstname, "
                            + " STA.shiptolastname "
                     + " FROM   (SELECT STMS.masternumber, "
                                    + " STMS.sourcenumber "
                             + " FROM   helios_own.salestran STMS "
                             + " WHERE  STMS.masternumber = ? "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') >= "
                                        + " To_date (?, 'YYYYMMDD') "
                                    + " AND To_date (STMS.ordertrandate, "
                                        + " 'DD-MON-YY') <= "
                                        + " To_date (?, 'YYYYMMDD') "
                                    + " AND STMS.sourcesystemid = 103 "
                                    + " AND STMS.trantype IN ( 'IO', 'OR' ) "
                             + " GROUP  BY STMS.masternumber, "
                                       + " STMS.sourcenumber) GSTMS "
                            + " inner join helios_own.salestran STA "
                                    + " ON GSTMS.masternumber = STA.masternumber "
                                       + " AND GSTMS.sourcenumber = "
                                           + " STA.sourcenumber AND STA.trantype IN ( 'IO', 'AJ', 'OR' )"
                    + " ) ST "
                    + " inner join helios_own.salestrandetail SD "
                            + " ON ST.salestranid = SD.salestranid "
                            + " left outer join (SELECT DISTINCT sku_num, "
                            + " primary_product_cat_cd "
            + " FROM   helios_own.sa_product_master) p" 
            + " ON SD.productsku = P.sku_num" 
				+ " WHERE  ( CASE" 
				          + " WHEN primary_product_cat_cd = 9 THEN 10 "
				          + " WHEN primary_product_cat_cd = 8 THEN 11" 
				          + " ELSE To_number(Nvl(primary_product_cat_cd, 11))" 
				        + " END ) = ?"
             + " GROUP  BY ST.sourcenumber, "
                       + " ST.masternumber, "
                       + " ST.original_lineamount) AA "
            + " left join (SELECT order_number "
                              + " SAVING_ORDER, "
                              + " Listagg (prm_savings_category, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " CATEGORY, "
                              + " Listagg (amount, '#') "
                                + " within GROUP (ORDER BY prm_savings_category) "
                              + " AMOUNT "
                       + " FROM   (SELECT order_number, "
                                      + " prm_savings_category, "
                                      + " SUM (prm_savings_amount) AMOUNT "
                               + " FROM   helios_own.sa_premium_savings "
                               + " WHERE  customer_number = ? "
                               + " GROUP  BY order_number, "
                                         + " prm_savings_category) "
                       + " GROUP  BY order_number) BB "
                   + " ON AA.order_number = BB.saving_order"
+ " ORDER  BY order_date DESC";

	
	// public static final String
	// SQL_GET_PURCHASE_HELIOS_DETAIL_WITHOUT_MONTH_SUNRISE =
	// "SELECT l.ODORD# AS Order_number,"
	// +
	// " to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')
	// order_date,"
	// + " l.ODSKU# as SKU_NUM,"
	// + " P.SKU_NAME,"
	// + " L.ODOQTY as ORDER_QTY ,"
	// + " ODAPRC AS UNIT_SALE_PRICE,PRIMARY_PRODUCT_CAT_CD,"
	//
	// + " (ODAPRC * ODOQTY) AS TOTAL_SPEND ,"
	// + " P.SKU_NAME AS ITEM_DESC"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l"
	// + " JOIN"
	// + " ( SELECT DISTINCT OHCUST as CUSTOMER_NUMBER,"
	// + " OHORD# as ORDER_NUMBER"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T "
	// + " WHERE OHCUST = ?"
	// + " )H"
	// + " ON H.ORDER_NUMBER = L.ODORD#"
	// +
	// " LEFT OUTER JOIN (select distinct
	// SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from
	// HELIOS_OWN.SA_PRODUCT_MASTER) p"
	// + " ON to_char(L.ODSKU#) = P.SKU_NUM"
	// +
	// " WHERE to_char(to_date(to_char(ODCRDT),'yyyymmdd'),'yyyy') = ? and
	// ODAPRC >=0"
	// +
	// " ORDER BY to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')";

	// public static final String
	// SQL_GET_PURCHASE_HELIOS_DETAIL_LAST_DAYS_SUNRISE =
	// "SELECT l.ODORD# AS Order_number,"
	// +
	// " to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')
	// order_date,"
	// + " l.ODSKU# as SKU_NUM,"
	// + " P.SKU_NAME,"
	// + " L.ODOQTY as ORDER_QTY ,"
	// + " ODAPRC AS UNIT_SALE_PRICE,PRIMARY_PRODUCT_CAT_CD,"
	//
	// + " (ODAPRC * ODOQTY) AS TOTAL_SPEND ,"
	// + " P.SKU_NAME AS ITEM_DESC"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l"
	// + " JOIN"
	// + " ( SELECT DISTINCT OHCUST as CUSTOMER_NUMBER,"
	// + " OHORD# as ORDER_NUMBER"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T "
	// + " WHERE OHCUST = ?"
	// + " )H"
	// + " ON H.ORDER_NUMBER = L.ODORD#"
	// +
	// " LEFT OUTER JOIN (select distinct
	// SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from
	// HELIOS_OWN.SA_PRODUCT_MASTER) p"
	// + " ON to_char(L.ODSKU#) = P.SKU_NUM"
	// +
	// " WHERE to_date(to_char(ODCRDT),'yyyymmdd') > (sysdate + (?) ) and ODAPRC
	// >=0"
	// +
	// " ORDER BY to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')";
	//
	// public static final String
	// SQL_GET_PURCHASE_HELIOS_DETAIL_WITH_MONTH_SUNRISE =
	// "SELECT l.ODORD# AS Order_number,"
	// +
	// " to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')
	// order_date,"
	// + " l.ODSKU# as SKU_NUM,"
	// + " P.SKU_NAME,"
	// + " L.ODOQTY as ORDER_QTY ,"
	// + " ODAPRC AS UNIT_SALE_PRICE,PRIMARY_PRODUCT_CAT_CD,"
	//
	// + " (ODAPRC * ODOQTY) AS TOTAL_SPEND ,"
	// + " P.SKU_NAME AS ITEM_DESC"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDD_T l"
	// + " JOIN"
	// + " ( SELECT DISTINCT OHCUST as CUSTOMER_NUMBER,"
	// + " OHORD# as ORDER_NUMBER"
	// + " FROM HELIOS_OWN.SUNRISE_SFBASLIB_SFORDH_T "
	// + " WHERE OHCUST = ?"
	// + " )H"
	// + " ON H.ORDER_NUMBER = L.ODORD#"
	// +
	// " LEFT OUTER JOIN (select distinct
	// SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from
	// HELIOS_OWN.SA_PRODUCT_MASTER) p"
	// + " ON to_char(L.ODSKU#) = P.SKU_NUM"
	// + " WHERE to_char(to_date(to_char(ODCRDT),'yyyymmdd'),'MM') = ?"
	// +
	// " AND to_char(to_date(to_char(ODCRDT),'yyyymmdd'),'yyyy') = ? and ODAPRC
	// >=0"
	// +
	// " ORDER BY to_char(to_date(to_char(l.ODCRDT),'yyyymmdd'),'mm/dd/yyyy')";

	public static final String SQL_GET_SEARCH_ITEM_LIST = "select * from (SELECT CONTACT_NAME AS ORDER_CONTACT, "
			+ " SEARCH_STRING,ROW_NUMBER() OVER (PARTITION BY CONTACT_NAME order by last_visit_date desc) rowvalue "
			+ " FROM HELIOS_OWN.SA_SEARCH_QUERIES X " + " WHERE CUSTOMER_NUMBER = ? " + " ) where rowvalue=1 ";

	public static final String SQL_GET_NOTIFICATION_DETAILS_ONE_OR_LESS = " SELECT seg_id, seg_freq, " + " seg_desc,"
			+ " seg_type," + " seg_name , NVL(TO_CHAR(LAST_REFRESH_DATE,'DD-MON-YY HH24:MI'),'') as LAST_REFRESH_DATE" + " FROM HELIOS_OWN.SA_SEGMENT_HEADER" + " WHERE seg_id IN"
			+ "( select play_id_list from helios_own.sa_customer_segment where customer_number=? )";
	public static final String SQL_GET_NOTIFICATION_DETAILS_MORE_THAN_ONE = " SELECT seg_id, seg_freq, " + " seg_desc,"
			+ " seg_type," + " seg_name , NVL(TO_CHAR(LAST_REFRESH_DATE,'DD-MON-YY HH24:MI'),'') as LAST_REFRESH_DATE " + " FROM HELIOS_OWN.SA_SEGMENT_HEADER" + " WHERE seg_id IN" + "(";
	public static final String SQL_GET_PLAYIDLIST_CUSTEXO = " select play_id_list from helios_own.sa_customer_segment where customer_number=? ";

	// Changed by Bipin to remove * from the query
	/* public static final String SQL_GET_YTD_INFO_CUSTEXO = "WITH DT AS (SELECT TM_KY,CLD_DT, FSC_YR, FSC_PRD, FSC_WK, FSC_DY, TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day' ), CURR_FISCAL AS (SELECT DISTINCT DT2.CLD_DT, DT2.FSC_YR, DT2.FSC_PRD, DT2.FSC_WK,"
			+ " DT2.FSC_DY, 'CURRENT' AS FISCAL_TYPE FROM DT DT1, DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR = DT2.FSC_YR AND TO_NUMBER (DT2.FSC_PRD) <= TO_NUMBER (DT1.FSC_PRD)), LAST_FISCAL AS (SELECT DISTINCT DT2.CLD_DT, DT2.FSC_YR, DT2.FSC_PRD, "
			+ " DT2.FSC_WK, DT2.FSC_DY, 'LAST' AS FISCAL_TYPE  FROM DT DT1, DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR = DT2.FSC_YR + 1), PREVIOUS_FISCAL_YEAR AS ( SELECT DISTINCT DT2.CLD_DT, DT2.FSC_YR, DT2.FSC_PRD, DT2.FSC_WK, DT2.FSC_DY, 'PREVIOUS'"
			+ " AS FISCAL_TYPE  FROM DT DT1, DT DT2 WHERE DT1.CLD_DT = TRUNC (sysdate) AND DT1.FSC_YR = DT2.FSC_YR + 2 ), FISCAL_YEAR AS (SELECT * FROM CURR_FISCAL UNION SELECT * FROM LAST_FISCAL UNION SELECT * FROM PREVIOUS_FISCAL_YEAR ),  CURR_TRANS AS(   SELECT "
			+ "  CF.FSC_YR YEAR,   CF.FSC_PRD,   CF.FISCAL_TYPE,   CASE   WHEN ST.TRANAMOUNT=0 THEN ST.TRANAMOUNT   ELSE ST.TRANAMOUNT- NVL(STC.TRANCHARGEAMOUNT,   0)   END ORIGINAL_LINEAMOUNT,   ST.SOURCENUMBER,   ST.RECORDTYPE FROM  HELIOS_OWN.SALESTRAN ST  INNER"
			+ " JOIN   FISCAL_YEAR CF   ON TRUNC (ST.ORDERTRANDATE) = TRUNC(CF.CLD_DT) LEFT OUTER JOIN  ( SELECT SUM(TRANCHARGEAMOUNT) TRANCHARGEAMOUNT,SALESTRANID FROM HELIOS_OWN.SALESTRANCHARGE STC GROUP BY SALESTRANID)STC  ON  ST.SALESTRANID=STC.SALESTRANID "
			+ " where   ST.MASTERNUMBER= ?   AND ST.SOURCESYSTEMID=103  AND TRANTYPE IN ('IO','AJ','OR')) SELECT B.YEAR, CASE   WHEN A.YTD_SALES IS NOT NULL THEN A.YTD_SALES   ELSE B.YTD_SALES END YTD_SALES FROM   (SELECT    SUM(CASE   WHEN CT.FISCAL_TYPE = "
			+ " 'LAST' then (NVL(CT.ORIGINAL_LINEAMOUNT,   0))   ELSE NULL   end)as YTD_SALES,  MIN (CASE   WHEN FISCAL_TYPE = 'LAST' then YEAR ELSE NULL END ) AS YEAR  FROM  CURR_TRANS CT   UNION   SELECT   SUM(CASE   WHEN FISCAL_TYPE = 'CURRENT' then (NVL(ORIGINAL_LINEAMOUNT,   0))"
			+ "   ELSE NULL   end)as YTD_SALES,    MIN (CASE   WHEN FISCAL_TYPE = 'CURRENT' then YEAR ELSE NULL END ) AS YEAR   FROM   CURR_TRANS CT  UNION  SELECT   SUM(CASE   WHEN FISCAL_TYPE = 'PREVIOUS' then (NVL(ORIGINAL_LINEAMOUNT,   0))   ELSE NULL   end)as YTD_SALES,   "
			+ " MIN (CASE   WHEN FISCAL_TYPE = 'PREVIOUS' then YEAR  ELSE NULL  END ) AS YEAR   FROM   CURR_TRANS CT ) A   RIGHT OUTER JOIN   (   select   to_char(to_char(sysdate,   'YYYY')-2) AS YEAR,   0  YTD_SALES   from   dual    union   select   to_char(to_char(sysdate,   'YYYY')-1) AS YEAR, "
			+ " 0  YTD_SALES   from   dual    union   select   to_char(to_char (sysdate,   'YYYY')) AS YEAR,   0  YTD_SALES   from   dual   )B   ON A.YEAR=B.YEAR   ORDER BY   YEAR DESC";
	*/
			
			/*" select * from (SELECT fiscal_year AS YEAR, "
			+ " TOTAL_SALES       AS YTD_SALES " + " FROM helios_own.MV_SALES_SUMMARY "
			+ " WHERE Customer_Number = ? and fiscal_year >= (select to_char(sysdate, 'YYYY')-2 from dual)) "

			+ " union "

			+ " select to_char(to_char(sysdate, 'YYYY')-2) AS YEAR,0  YTD_SALES from dual "
			+ " WHERE NOT EXISTS (SELECT 1 " + " FROM helios_own.MV_SALES_SUMMARY "
			+ " WHERE Customer_Number = ? and fiscal_year = (select to_char(sysdate, 'YYYY')-2 from dual)) "

			+ " union "

			+ " select to_char(to_char(sysdate, 'YYYY')-1) AS YEAR,0  YTD_SALES from dual "
			+ " WHERE NOT EXISTS (SELECT 1 " + " FROM helios_own.MV_SALES_SUMMARY "
			+ " WHERE Customer_Number = ? and fiscal_year = (select to_char(sysdate, 'YYYY')-1 from dual)) "

			+ " union "

			+ " select to_char(sysdate, 'YYYY')  YEAR,0  YTD_SALES from dual " + " WHERE NOT EXISTS (SELECT 1 "
			+ " FROM helios_own.MV_SALES_SUMMARY "
			+ " WHERE Customer_Number = ? and fiscal_year = (select to_char(sysdate, 'YYYY') from dual)) "
			+ " order by year desc";*/

	public static final String SQL_GET_YTD_INFO_CUSTEXO = " WITH SS AS ("
	           + " SELECT FSC_YR AS FISCAL_YEAR, SUM(CASE"

	                                + " WHEN HEAD.TRANAMOUNT = 0"

	                                + " THEN"

	                                   + " HEAD.TRANAMOUNT"

	                                + " ELSE"

	                                   + " HEAD.TRANAMOUNT - NVL (STC.TRANCHARGEAMOUNT, 0)"

	                             + " END) AS TOTAL_SALES FROM (SELECT SALESTRANID, ORDERTRANDATE, TRANAMOUNT "
	           + " FROM HELIOS_OWN.SALESTRAN"
	                 + " WHERE     MASTERNUMBER = ?"
	                       + " AND SOURCESYSTEMID = 103"
	                       + " AND TRANTYPE IN ('IO', 'AJ', 'OR')"
	                       + " AND EXTRACT (YEAR FROM to_date(ORDERTRANDATE, 'DD-MON-YY')) >  EXTRACT (YEAR FROM SYSDATE) - 4 "
	                       + " ) HEAD"
	                       + " LEFT OUTER JOIN HELIOS_OWN.SALESTRANCHARGE STC ON HEAD.SALESTRANID = STC.SALESTRANID"
	          + " INNER JOIN (SELECT CLD_DT, FSC_YR"
	                               + " FROM HELIOS_OWN.D_TIME"
	                              + " WHERE TM_LVL = 'Day') DT"
	                    + " ON HEAD.ORDERTRANDATE = DT.CLD_DT"
	          + " GROUP BY FSC_YR )"
	          + " select * from (SELECT fiscal_year AS YEAR, "
				 + " TOTAL_SALES       AS YTD_SALES   FROM SS "
				 + " WHERE  fiscal_year >= (select to_char(sysdate, 'YYYY')-2 from dual)) "

				 + " union "

				 + " select to_char(to_char(sysdate, 'YYYY')-2) AS YEAR,0  YTD_SALES from dual "
				 + " WHERE NOT EXISTS (SELECT 1   FROM SS "
				 + " WHERE  fiscal_year = (select to_char(sysdate, 'YYYY')-2 from dual)) "

				 + " union "

				 + " select to_char(to_char(sysdate, 'YYYY')-1) AS YEAR,0  YTD_SALES from dual "
				 + " WHERE NOT EXISTS (SELECT 1   FROM SS "
				 + " WHERE  fiscal_year = (select to_char(sysdate, 'YYYY')-1 from dual)) "

				 + " union "

				 + " select to_char(sysdate, 'YYYY')  YEAR,0  YTD_SALES from dual   WHERE NOT EXISTS (SELECT 1 "
				 + " FROM SS "
				 + " WHERE fiscal_year = (select to_char(sysdate, 'YYYY') from dual)) "
				 + " order by year desc";
	/*
	 * public static final String SQL_GET_CUSTOMER_NUMBER =
	 * "SELECT DISTINCT CUSTOMER_NUMBER FROM HELIOS_OWN.SA_CUSTOMER_PROFILE CP"
	 * + " INNER JOIN HELIOS_OWN.V_SA_ACCOUNT_MANAGER AM" +
	 * " ON UPPER(TRIM(CP.ACCOUNT_MANAGER_EMAIL)) = UPPER(TRIM(AM.EMAIL_ID))" +
	 * " WHERE (TRIM(CP.CUSTOMER_NUMBER) = TRIM(:searchText) OR UPPER(TRIM(regexp_replace(CP.COMPANY_NAME, '[^0-9A-Za-z]', ''))) = UPPER(TRIM(:searchText))) AND UPPER(TRIM(AM.OWNER_EMP_ID)) = UPPER(TRIM(:acctId))"
	 * ;
	 */

	public static final String SQL_GET_CUSTOMER_NUMBER = "select HELIOS_OWN.FN_GET_CUSTOMER_NUMBER(:searchText,:acctId) as CUSTOMER_NUMBER FROM dual";

	public static final String SQL_SELECT_USER_ROLE = "select ROLE_SHRT_NAME from HELIOS_OWN.User_Role_Link_New where USR_V_USER_ID =? and dltd_fl=0";
	public static final String SQL_USER_INSERT = "insert into HELIOS_OWN.User_Role_Link_New (USR_V_USER_ID,ROLE_ID,ROLE_SHRT_NAME,USR_V_LAN_ID,USR_V_LAST_NAME,USR_V_FIRST_NAME,CRTE_TS,DLTD_FL) VALUES (?,?,?,?,?,?,?,?)";
	public static final String SQL_USER_MSTR_INSERT = "insert into HELIOS_OWN.user_master (USR_V_USER_ID,USR_V_LAST_NAME,USR_V_FIRST_NAME,USR_V_DESIG,USR_V_REF_LOCATION,"
			+ "USR_V_LOCATION,USR_V_PASSWORD,USR_V_MULTI_LOGIN_FLAG,USR_I_NUM_OF_LOGINS,USR_D_VALID_FROM,USR_V_REF_STATUS_CODE,USR_V_STATUS_CODE,"
			+ "USR_I_MAX_GRANT_PTS,USR_V_DELETE_FLAG,USR_V_UPDATE_FLAG,USR_D_DATE)"
			+ "values(?,?,?,?,'LOC','BALOC',?,'Y',0,?,'USC','DUTY',1,'N','Y',?)";
	public static final String SQL_USERID_EXIST = "select count(*) from HELIOS_OWN.user_role_link_new where USR_V_USER_ID=?";
	public static final String SQL_UPDATE_ROLE = "update HELIOS_OWN.User_Role_Link_New  set ROLE_ID= ?, ROLE_SHRT_NAME =? , UPDATE_TS=?, DLTD_FL=0 where USR_V_USER_ID=?";
	public static final String SQL_FIND_USER = "select count(*) from HELIOS_OWN.user_role_link_new where USR_V_USER_ID=? and dltd_fl=0";
	public static final String SQL_ROLE_ID = "select role_id from HELIOS_OWN.role_master_new where role_shrt_name=";
	public static final String SQL_USER_ROLE = "select ROLE_SHRT_NAME from HELIOS_OWN.user_role_link_new where USR_V_USER_ID=?";
	public static final String SQL_REMOVE_ROLE = "update HELIOS_OWN.User_Role_Link_New  set DLTD_FL= 1,UPDATE_TS=? where USR_V_USER_ID=?";
	public static final String SQL_DELETE_ROLE_MENU_LINK = "DELETE FROM HELIOS_OWN.role_menu_link WHERE role_id = (SELECT role_id FROM HELIOS_OWN.role_master_new WHERE role_shrt_name = ? )";
	public static final String SQL_INSERT_ROLE_MENU_LINK = "INSERT INTO HELIOS_OWN.role_menu_link (role_id, mnu_v_menu_id) VALUES ( (SELECT role_id FROM HELIOS_OWN.role_master_new WHERE role_shrt_name = ?), (SELECT mnu_v_menu_id FROM HELIOS_OWN.menu_master WHERE mnu_v_menu_desc = ? )) ";

	public static final String SQL_GET_ASSIGNED_ROLE_MAPPINGS = "SELECT SUBSTR(m.mnu_v_menu_desc, 2) FROM HELIOS_OWN.role_menu_link rml JOIN HELIOS_OWN.menu_master m ON m.mnu_v_menu_id = rml.mnu_v_menu_id JOIN HELIOS_OWN.role_master_new r ON r.role_id = rml.role_id WHERE r.role_shrt_name = ? ";
	public static final String SQL_FETCH_URL_ROLES = "SELECT m.mnu_v_menu_desc, r.role_shrt_name FROM HELIOS_OWN.role_menu_link rml "
			+ " JOIN HELIOS_OWN.role_master_new r ON r.role_id = rml.role_id "
			+ " JOIN HELIOS_OWN.menu_master m ON m.mnu_v_menu_id = rml.mnu_v_menu_id " + " ORDER BY m.mnu_v_menu_desc ";

	/* Recommendation Page changes by Bipin starts */
	public static final String GET_BOUGHT_ALSO_BOUGHT_RECOMMENDATIONS = "SELECT UR.CUSTOMER_NUMBER,UR.ORDER_CONTACT,UR.ORDER_DATE,UR.SKU_NUMBER,PM.SKU_NAME,PM.THUMBNAIL,UR.SKU_LIST , PM.CATENTRY_ID FROM (SELECT CUSTOMER_NUMBER,ORDER_CONTACT,TO_CHAR(ORDER_DATE) ORDER_DATE,SKU_NUMBER,SKU_LIST FROM HELIOS_OWN.SA_USER_RECOMENDTN_BAB WHERE CUSTOMER_NUMBER= ? AND ORDER_CONTACT= ?) UR LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER PM ON UR.SKU_NUMBER= PM.SKU_NUM";
	public static final String GET_RECOMMENDED_SKUINFO = "SELECT SKU_NUM SKU,SKU_NAME,THUMBNAIL ,CATENTRY_ID FROM HELIOS_OWN.SA_PRODUCT_MASTER WHERE  SKU_NUM IN(:skuList)";
	public static final String GET_VIEW_NOT_BOUGHT_RECOMMENDATIONS = "SELECT RT.CUSTOMER_NUMBER CUST_NUM,RT.ORDER_CONTACT ORDER_CONTACT,RT.ORDER_DATE ORDER_DATE,RT.SKU_NUMBER SKU,PM.SKU_NAME SKU_NAME,PM.THUMBNAIL THUMBNAIL FROM (SELECT * FROM HELIOS_OWN.SA_USER_RECOMMENDATION_VBNB WHERE CUSTOMER_NUMBER= :custNum)  RT LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER PM ON RT.SKU_NUMBER = PM.SKU_NUM";
	public static final String GET_REORDER_RECOMMENDATIONS = "SELECT RT.CUSTOMER_NUMBER CUST_NUM,RT.SKU_NUMBER SKU,PM.SKU_NAME SKU_NAME,PM.THUMBNAIL THUMBNAIL,RT.ORDER_DATE ORDER_DATE , PM.CATENTRY_ID FROM (SELECT  DISTINCT CUSTOMER_NUMBER,SKU_NUMBER,TO_CHAR(TRIM(MIN(TO_DATE(ORDER_DATE)))) ORDER_DATE FROM HELIOS_OWN.SA_USER_RECOMENDTN_REORD WHERE CUSTOMER_NUMBER= :custNum GROUP BY CUSTOMER_NUMBER,SKU_NUMBER) RT LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER PM ON RT.SKU_NUMBER = PM.SKU_NUM ORDER BY TO_DATE(RT.ORDER_DATE)";
	//public static final String GET_RECOMM_ORDER_CONTACTS = "select ORDER_CONTACT,TO_CHAR(ORDER_DATE) from HELIOS_OWN.SA_USER_RECOMENDTN_BAB where CUSTOMER_NUMBER=:custNum ORDER BY ORDER_DATE DESC";
	//SparX Recommendation Change
	public static final String GET_RECOMM_ORDER_CONTACTS = "WITH CUST_DETAIL AS (SELECT ORDERERFIRSTNAME, ORDERERLASTNAME,MASTERNUMBER AS CUST_NUM"
			+ " FROM CEX01_OWN.SALESTRAN WHERE SOURCESYSTEMID = '103' AND TRUNC (ORDERTRANDATE) BETWEEN TO_DATE ( :startDate ,'MM/DD/YYYY') AND TO_DATE ( :endDate ,'MM/DD/YYYY')"
			+ "	AND UPPER (TRANTYPE) NOT IN ('RE', 'QU', 'MO')  AND MASTERNUMBER = :custNum  AND ORDERERFIRSTNAME IS NOT NULL AND ORDERERLASTNAME IS NOT NULL ORDER BY TRUNC (ORDERTRANDATE) DESC)"
			+ " SELECT DISTINCT UPPER(SCP.FIRST_NAME || ' ' || SCP.LAST_NAME) AS ORDER_CONTACT FROM HELIOS_OWN.SA_CUSTOMER_PROFILE SCP"
			+ "	INNER JOIN (SELECT DISTINCT ORDERERFIRSTNAME,ORDERERLASTNAME,CUST_NUM FROM CUST_DETAIL GROUP BY ORDERERFIRSTNAME, ORDERERLASTNAME,CUST_NUM) CD ON CD.CUST_NUM = SCP.CUSTOMER_NUMBER AND UPPER(CD.ORDERERFIRSTNAME)=UPPER(SCP.FIRST_NAME) AND UPPER(CD.ORDERERLASTNAME) = UPPER(SCP.LAST_NAME) WHERE SCP.ASSIGN_ACCOUNT_TO = 'MM' AND ROWNUM<=15";

	public static final String GET_ONLINE_RETAIL_REORDER_RECOMMENDATIONS = "select SKU_NUM, THUMBNAIL, SKU_NAME,  TO_CHAR(ROUND(SP.PRE,2),'9999999999.00') PREMIUM_PRICE, TO_CHAR(ROUND(SP.SEL,2),'9999999999.00') SELECT_PRICE, CATENTRY_ID"
			+ " from HELIOS_OWN.SA_PRODUCT_MASTER PM LEFT OUTER JOIN"
			+ " (select  P_SEL.ITEM, SEL , PRE from (select ITEM, PROGRAM_PRICE SEL  from HELIOS_OWN.SA_CONTRACT_PRICING  where PROGRAM_ID='SEL') P_SEL"
			+ " join (select ITEM , PROGRAM_PRICE PRE from HELIOS_OWN.SA_CONTRACT_PRICING  where  PROGRAM_ID='PRE' ) P_PRE"
			+ " on P_SEL.ITEM= P_PRE.ITEM)SP ON SP.ITEM=PM.SKU_NUM where SKU_NUM IN"
			+ " (with TEMP as (select SKU_LIST as STR from HELIOS_OWN.SAM_RECOMMENDATION where CUSTOMER_NUMBER=:custNum)"
			+ " SELECT '324791' FROM DUAL UNION select REGEXP_SUBSTR (STR,'[^,]+',1,level) as SKU_LIST from  TEMP connect by REGEXP_SUBSTR(STR,'[^,]+',1,level) is not null)"
			+ " order by case when SKU_NUM='324791' then 1 else 2 end";
	// public static final String GET_CLASS_RECOMMENDATIONS =
	// "SELECT RC.SKU_LIST SKU,PM.SKU_NAME,PM.THUMBNAIL FROM ("
	// +
	// " (select regexp_substr((SELECT LISTAGG(SKU_LIST, ',') WITHIN GROUP
	// (ORDER BY SKU_LIST) AS SKU_LIST "
	// +
	// " FROM HELIOS_OWN.SA_USER_RECOMMENDATION_CLS where
	// CUSTOMER_NUMBER=:custNum"
	// + " ),'[^,]+', 1, level) SKU_LIST from dual"
	// +
	// " connect by regexp_substr((SELECT LISTAGG(SKU_LIST, ',') WITHIN GROUP
	// (ORDER BY SKU_LIST) AS SKU_LIST "
	// +
	// " FROM HELIOS_OWN.SA_USER_RECOMMENDATION_CLS where
	// CUSTOMER_NUMBER=:custNum"
	// + " ), '[^,]+', 1, level) is not null) RC LEFT OUTER JOIN"
	// +
	// " (select HELIOS_OWN.SKU_NUM,SKU_NAME,THUMBNAIL from SA_PRODUCT_MASTER
	// where SKU_NUM in ("
	// +
	// " select regexp_substr((SELECT LISTAGG(SKU_LIST, ',') WITHIN GROUP (ORDER
	// BY SKU_LIST) AS SKU_LIST "
	// +
	// " FROM HELIOS_OWN.SA_USER_RECOMMENDATION_CLS where
	// CUSTOMER_NUMBER=:custNum"
	// + " ),'[^,]+', 1, level) from dual"
	// +
	// " connect by regexp_substr((SELECT LISTAGG(SKU_LIST, ',') WITHIN GROUP
	// (ORDER BY SKU_LIST) AS SKU_LIST "
	// +
	// " FROM HELIOS_OWN.SA_USER_RECOMMENDATION_CLS where
	// CUSTOMER_NUMBER=:custNum"
	// +
	// " ), '[^,]+', 1, level) is not null )) PM ON RC.SKU_LIST= PM.SKU_NUM )
	// WHERE RC.SKU_LIST IS NOT NULL";
	/* Recommendation page changes by Bipin Ends */
	public static final String SQL_GET_SUBPLAY_DETAILS = "SELECT seg_id, seg_name as seg_desc , seg_type FROM HELIOS_OWN.SA_SEGMENT_HEADER WHERE ";
	public static final String SQL_GET_SEGMENT_DETAILS_OPEN_CLAUSE_ONE_CUSTEXO = "select distinct seg_type || '~' || seg_desc from HELIOS_OWN.SA_SEGMENT_HEADER where seg_id in(";
	public static final String SQL_GET_SEGMENT_DETAILS_OPEN_CLAUSE_TWO_CUSTEXO = " and seg_desc in(";
	public static final String GET_ALL_SEGMENT_DETAILS = "SELECT TO_CHAR(SEG_ID) SEG_ID ,SEG_TYPE,''''|| SEG_DESC || '''' as SEG_DESC_Q FROM HELIOS_OWN.SA_SEGMENT_HEADER";

	public static final String SQL_LOG_USER_ACTIVITY = "INSERT INTO HELIOS_OWN.SA_USER_METRICS(APPLICATION_ID , REP_ID,REP_ROLE_CD ,CUSTOMER_NUMBER ,PAGE_NUMBER  ,VISIT_START_TS , PAGE_DESCRIPTION , LOGGEDIN_USER) VALUES('Helios_SA', ?, ?, ?, ?, systimestamp, ?, ?)";

	public static final String SQL_LOG_USER_ACTIVITY_DOTCOM = "INSERT INTO HELIOS_OWN.SA_USER_METRICS(APPLICATION_ID , REP_ID,REP_ROLE_CD ,CUSTOMER_NUMBER ,PAGE_NUMBER  ,VISIT_START_TS , PAGE_DESCRIPTION , LOGGEDIN_USER) VALUES('Helios_SAM.COM', ?, ?, ?, ?, systimestamp, ?, ?)";

	public static final String SQL_GET_AGENT_REPROLECD = "SELECT NVL ((SELECT ROLE_CD FROM HELIOS_OWN.MV_SA_ACCOUNT_MANAGER WHERE OWNER_EMP_ID = :agentId AND ROWNUM=1),"
			+ " (SELECT ROLE_SHRT_NAME FROM HELIOS_OWN.USER_ROLE_LINK_NEW WHERE USR_V_USER_ID = :agentId AND ROWNUM=1)) AS AGENT_ROLE FROM DUAL";

	public static final String SQL_GET_AGENT_NAME = "SELECT NVL ((SELECT OWNER_NAME FROM HELIOS_OWN.MV_SA_ACCOUNT_MANAGER WHERE OWNER_EMP_ID = :agentId AND ROWNUM=1),"
			+ " (SELECT USR_V_FIRST_NAME ||' '|| USR_V_LAST_NAME FROM HELIOS_OWN.USER_ROLE_LINK_NEW WHERE USR_V_USER_ID = :agentId AND ROWNUM=1)) AS AGENT_NAME FROM DUAL";

	public static final String SQL_GET_ALL_CUST_INFO_DYNAMIC = "select MASTER_CUST_NUM, EMAIL_ID, COMPANY_NAME, CUSTOMER_TYPE, AGENT_EMAIL_ID, CALL_ORDER, "
			+ " CASE WHEN LAST_CONTACTED_DATE IS NOT NULL THEN to_char(LAST_CONTACTED_DATE, 'MM/DD/YYYY' )  ELSE '' END " 				
			+ " as S_LAST_CONTACTED_DATE, PLAY_ID_LIST, ACCT_MNGR_FLAG, CUSTOMER_SEGMENT, VAP_SCORE, ACCOUNT_QUALIFY_SCORE, "
			+ " PLAY_SEGMENT "
			+ " from (SELECT MASTER_CUST_NUM, EMAIL_ID, COMPANY_NAME, CUSTOMER_TYPE, AGENT_EMAIL_ID, CALL_ORDER, LAST_CONTACTED_DATE, PLAY_ID_LIST, ACCT_MNGR_FLAG, CUSTOMER_SEGMENT, VAP_SCORE, ACCOUNT_QUALIFY_SCORE, (select LISTAGG(seg_type, ',') "
			+ " WITHIN GROUP (order by seg_type) from ( select distinct seg_type, customer_number "
			+ " from HELIOS_OWN.SA_SEGMENT_HEADER sh inner join ( select regexp_substr(PLAY_ID_LIST,'[^,]+', 1, level) as P_ID, customer_number "
			+ " from   (select CUSTOMER_NUMBER, PLAY_ID_LIST from HELIOS_OWN.SA_CUSTOMER_SEGMENT where CUSTOMER_NUMBER = MASTER_CUST_NUM	)  "
			+ "  connect by regexp_substr(PLAY_ID_LIST, '[^,]+', 1, level) is not null) p on sh.seg_id = p.p_id ) group by customer_number) as PLAY_SEGMENT "
			+ " FROM (SELECT sub.MASTER_CUST_NUM, sub.EMAIL_ID, sub.COMPANY_NAME, sub.CUSTOMER_TYPE, sub.AGENT_EMAIL_ID, case when NVL(sub.CALL_ORDER,0) != 0 then dense_rank() "
			+ " OVER ( ORDER BY sub.CALL_ORDER nulls last) else sub.CALL_ORDER end as CALL_ORDER, "
			+ " sub.LAST_CONTACTED_DATE as LAST_CONTACTED_DATE,cs.PLAY_ID_LIST, sub.ACCT_MNGR_FLAG, "
			+ " (select case when vap_score like 'Rising%' or vap_score like 'Slow%' or vap_score like 'Not%' then 'YOUNG - 0 to 6 MONTHS' "
			+ " when vap_score like 'Star%' or vap_score like 'Late%' then 'TRANSITIONING - 7 to 12 MONTHS' "
			+ " when vap_score like 'Defector%' or vap_score like 'Lapsing%' or vap_score like 'Nova%' "
			+ " or vap_score like 'Supernova%' then 'MATURE - 13+ MONTHS' else DECODE(VAP_SCORE,'0','NA',NULL,'NA','','NA',VAP_SCORE) END "
			+ " from Helios_own.SA_CUSTOMER_VAP_REF where CUSTOMER_NUMBER= sub.MASTER_CUST_NUM and rownum=1) as CUSTOMER_SEGMENT,(select decode(VAP_SCORE,'0','NA',null,'NA','','NA',VAP_SCORE) "
			+ " from Helios_own.SA_CUSTOMER_VAP_REF where customer_number = sub.MASTER_CUST_NUM and ROWNUM = 1) as VAP_SCORE, "
			+ " (select decode(ACCOUNT_QUALIFY_SCORE,'0','NA',null,'NA','','NA',ACCOUNT_QUALIFY_SCORE) "
			+ " from Helios_own.SA_CUSTOMER_INFO where customer_number = sub.MASTER_CUST_NUM and ROWNUM = 1) as ACCOUNT_QUALIFY_SCORE "
			+ " FROM (SELECT CO.MASTER_CUST_NUM, CO.COMPANY_NAME, CO.CUSTOMER_TYPE, CO.AGENT_EMAIL_ID, CRP.RANK AS CALL_ORDER, CO.EMAIL_ID, CO.ACCT_MNGR_FLAG, "
			
		/*	+ " (SELECT LAST_LIVE_CONTACT_DATE "
			+ " FROM (SELECT LAST_LIVE_CONTACT_DATE FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE SA_CUSTOMER_SFDC_INFO.MASTER_CUSTOMER_NUMBER = MASTER_CUST_NUM ORDER BY LAST_LIVE_CONTACT_DATE DESC ) "
			+ " WHERE ROWNUM = 1) " */

			+ "  ( select max(activity_date) as last_live_contact_date "
			+ " from ( SELECT TRUNC (MAX(ActivityDate)) AS activity_date "
			+ " FROM (select max (id ) as sf_id, master_account_c from cex01_own.crm_sfdcown_account_c_land "
			+ " where key_type__c = 'Sunrise Master' and master_account_c = MASTER_CUST_NUM group by master_account_c) a "
			+ " left join HELIOS_OWN.CRM_SFDCOWN_STG_TASK t on t.accountid = a.sf_id "
			+ " AND t.STATUS = 'Task Completed - Live Contact' "
			+ " union "
			+ " SELECT TRUNC (max(TASK_INSERT_DATE)) AS activity_date FROM helios_own.sa_segment_detail "
			+ " WHERE customer_number = LPAD ( MASTER_CUST_NUM, 10, 0) AND SEG_DISP_STATUS = 'Task Completed - Live Contact' ) ) "			
			
			
			+ " AS LAST_CONTACTED_DATE FROM HELIOS_OWN.MV_SA_CALL_ORDER CO LEFT OUTER JOIN HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MVSACP ON CO.EMP_NUMBER= MVSACP.EMP_NUMBER AND CO.MASTER_CUST_NUM=MVSACP.CUSTOMER_NUMBER LEFT OUTER JOIN HELIOS_OWN.SA_CALL_RANKING_PRIMARY CRP  ON CO.MASTER_CUST_NUM   = CRP.HIERARCHY_ID  AND CRP.RANK_ALGORITHM  = 'nonam1_mm' AND CRP.HIERARCHY_LEVEL = 'customer_number' WHERE CO.ACCOUNT_GROUP = 'MM' AND CO.EMP_NUMBER = :accId AND MVSACP.HOLD_OUT='N' ) sub LEFT OUTER JOIN helios_own.SA_CUSTOMER_SEGMENT cs ON cs.CUSTOMER_NUMBER = sub.MASTER_CUST_NUM WHERE 1=1 ";
	public static final String SQL_GET_ALL_CUST_INFO_DYNAMIC_PLAY = " select P_ID, CUSTOMER_NUMBER, SEG_TYPE, SEG_NAME FROM (select regexp_substr(PLAY_ID_LIST,'[^,]+', 1, level) as P_ID, CUSTOMER_NUMBER from "
			+ " (select CUSTOMER_NUMBER, PLAY_ID_LIST from HELIOS_OWN.SA_CUSTOMER_SEGMENT where CUSTOMER_NUMBER = sub.MASTER_CUST_NUM	) "
			+ "  connect by regexp_substr(PLAY_ID_LIST, '[^,]+', 1, level) is not null) inner join HELIOS_OWN.SA_SEGMENT_HEADER on "
			+ "  P_ID = SEG_ID ";

	public static final String SQL_GET_ALL_CUST_INFO_DYNAMIC_1 = " )AD  left join 	helios_own.SA_QUALIFY_SCORE SQ on SQ.QUAL_SCORE_NAME  =  AD.Account_Qualify_Score left join	helios_own.SA_VAP_SCORE SV on SV.VAP_SCORE_NAME = AD.VAP_SCORE ) MT left join HELIOS_OWN.SA_TASK_TRACKER STT on  MT.MASTER_CUST_NUM = STT.CUSTOMER_NUMBER AND STT.CHECKED_IN_DATE > (sysdate -31)";

	/* public static final String PROCEDURE_GET_YTD_SUMMARY = " { CALL HELIOS_OWN.SP_GET_YTD_INFO(:custNum,:selYear,:cursorOut) }"; */
	public static final String PROCEDURE_GET_YTD_SUMMARY = " { CALL HELIOS_OWN.SP_GET_YTD_INFO(:custNum,:selYear,:cursorOut) }";

	public static final String GET_CLASS_RECOMMENDATIONS = " { CALL HELIOS_OWN.SP_GET_CLASS_RECOMMENDATIONS(:custNum,:cursorOut) }";
	public static final String SQL_GET_SUPER_USER_DETAILS_AND_INFO = "{ CALL HELIOS_OWN.SP_GET_SUPER_USER_DETAILS(:custNum,:cursorOut) }";
	public static final String SQL_GET_PURCHASE_HELIOS_DETAIL_LAST_DAYS_SUNRISE = "{ CALL HELIOS_OWN.SP_GET_PURCHASE_DETAIL_DAYS(:custNum,:month_val,:cursorOut) }";
	public static final String SQL_GET_PURCHASE_HELIOS_DETAIL_WITHOUT_MONTH_SUNRISE = "{ CALL HELIOS_OWN.SP_GET_PURCHASE_DETAIL_YEAR(:custNum,:year_val,:cursorOut) }";
	public static final String SQL_GET_PURCHASE_HELIOS_DETAIL_WITH_MONTH_SUNRISE = "{ CALL HELIOS_OWN.SP_GET_PURCHASE_DETAIL_MONTH(:custNum,:START_DATE,:END_DATE,:month_val,:cursorOut) }";
	public static final String SQL_GET_LAST_REFRESH_TIME = "SELECT TO_CHAR(cast(HELIOS_OWN.FN_GET_LAST_REFRESH_TIME(:processName) as timestamp), 'DD-MON-YY HH24:MI') LAST_REFRESH_DATE FROM DUAL";
	public static final String SQL_GET_SHIPTO_INFO = "select * from (select ST.*,LISTAGG (TRIM(SD.PRODUCTSKU),',') within group (order by TRIM(SD.PRODUCTSKU)) as SKU,  LISTAGG (p.SKU_NAME,',') within group (order by p.SKU_NAME) as description  from ("
 			 +"	(SELECT s.SOURCENUMBER AS ORDER_NUMBER,max(s.salestranid) as salestranid,min(s.ORDERTRANDATE) AS ORDER_DATE,s.masternumber as customer_number, CASE WHEN SUM(s.TRANAMOUNT)=0 THEN SUM (s.TRANAMOUNT) ELSE SUM (s.TRANAMOUNT- NVL(stc.TRANCHARGEAMOUNT,0)) END AS ORDER_AMOUNT, MIN(s.TRANAMOUNT) as MIN_ORDER_AMOUNT,"  
             +" MIN(s.SHIPTOFIRSTNAME||' '||s.SHIPTOLASTNAME)  AS ORDER_CONTACT  FROM HELIOS_OWN.SALESTRAN s left join( SELECT SUM(TRANCHARGEAMOUNT) TRANCHARGEAMOUNT,SALESTRANID FROM HELIOS_OWN.SALESTRANCHARGE STC GROUP BY SALESTRANID)STC on  s.SALESTRANID=stc.SALESTRANID "
    		 +" WHERE EXTRACT (YEAR FROM s.ORDERTRANDATE) >= EXTRACT (YEAR FROM SYSDATE) - 2  AND s.MASTERNUMBER = ? and  s.SOURCESHIPTOID  = ?  and s.TRANTYPE IN "
             +" ('IO','AJ','OR') AND s.SOURCESYSTEMID=103 and s.ORDERTRANDATE<  (select max(cld_dt) from helios_own.D_TIME where fsc_yr = (select (fsc_yr) from helios_own.D_TIME  WHERE TM_LVL = 'Day' AND CLD_DT = TRUNC (SYSDATE)))  and ORDERTRANDATE>" 
             +" (select min(cld_dt) from helios_own.D_TIME where fsc_yr = (select (fsc_yr-1) from helios_own.D_TIME  WHERE TM_LVL = 'Day' AND CLD_DT = TRUNC (SYSDATE))) GROUP BY s.masternumber, s.SOURCENUMBER, s.SOURCESHIPTOID)"
             +" ST left outer join HELIOS_OWN.SALESTRANDETAIL SD on ST.salestranid=  SD.salestranid LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) p  ON to_char(TRIM(SD.PRODUCTSKU)) = P.SKU_NUM ) group by ST.order_number,ST.order_date,ST.customer_number,ST.order_amount, ST.min_order_amount,ST.salestranid," 
             +" ST.order_contact order by ST.order_date desc , ST.order_amount asc nulls last)aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category," 
             +" listagg(amount,'#')  WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from  (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?" 
             +" group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb  on aa.order_number= bb.saving_order  order by order_date desc"; 
			
			
			/*"select * from (select h.*,LISTAGG (l.STAPLES_SHIPPED_SKU_NUMBER,',') within group (order by l.STAPLES_SHIPPED_SKU_NUMBER) as SKU, "
			+ " LISTAGG (p.SKU_NAME,',') within group (order by p.SKU_NAME) as description "
			+ " from ((SELECT ORDER_NUMBER,MIN(ORDER_DATE) AS ORDER_DATE,customer_number, "
			+ " SUM (ORDER_AMOUNT) AS ORDER_AMOUNT, MIN(ORDER_AMOUNT) as MIN_ORDER_AMOUNT, MIN(CASE WHEN UPPER(ORDER_TYPE) NOT IN ('AJ','RE', 'QU', 'MO') THEN ORDERED_BY END) AS ORDER_CONTACT "
			+ " FROM HELIOS_OWN.MV_SA_ORDER_HEADER "
			+ " WHERE EXTRACT (YEAR FROM ORDER_DATE) >= EXTRACT (YEAR FROM SYSDATE) - 2 "

			+ " AND CUSTOMER_NUMBER = ? and ship_to = ? and UPPER(ORDER_TYPE) NOT IN ('RE', 'QU', 'MO') and order_date<  (select max(cld_dt) from helios_own.D_TIME where fsc_yr = (select (fsc_yr) from helios_own.D_TIME  WHERE TM_LVL = 'Day' AND CLD_DT = TRUNC (SYSDATE))) "
			+ " and order_date> (select min(cld_dt) from helios_own.D_TIME where fsc_yr = (select (fsc_yr-1) from helios_own.D_TIME  WHERE TM_LVL = 'Day' AND CLD_DT = TRUNC (SYSDATE))) "
			+ " GROUP BY CUSTOMER_NUMBER, ORDER_NUMBER, SHIP_TO )  h left outer join HELIOS_OWN.mv_sa_order_line l on h.customer_number= "
			+ " l.customer_number and h.order_number = l.order_number and h.order_date= l.order_date LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) p "
			+ " ON to_char(L.STAPLES_SHIPPED_SKU_NUMBER) = P.SKU_NUM ) group by h.order_number,h.order_date,h.customer_number, "
			+ "    h.order_amount, h.min_order_amount,h.order_contact order by h.order_date desc , h.order_amount asc nulls last)"
			+ "aa left join (select order_number saving_order,listagg(PRM_SAVINGS_CATEGORY,'#') WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) category,listagg(amount,'#') "
			+ " WITHIN GROUP (ORDER BY PRM_SAVINGS_CATEGORY) amount from "
			+ " (select order_number,PRM_SAVINGS_CATEGORY,sum(PRM_SAVINGS_AMOUNT) amount from helios_own.SA_PREMIUM_SAVINGS where customer_number=?  group by order_number,PRM_SAVINGS_CATEGORY) group by ORDER_NUMBER) bb "
			+ " on aa.order_number= bb.saving_order  order by order_date desc";*/
	public static final String SQL_GET_ALL_CUST_COUNT = "SELECT COUNT(DISTINCT CUSTOMER_NUMBER)  FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE  WHERE EMP_NUMBER  =  :custNum AND ACCOUNT_GROUP='MM'";
	public static final String SQL_GET_SELECTED_SEGMENT_DETAILS_DESC = "SELECT SEG_ID, SEG_TYPE, SEG_DESC FROM HELIOS_OWN.SA_SEGMENT_HEADER WHERE  '''' || SEG_DESC || '''' IN(:subPlayList)";
	public static final String SQL_GET_SELECTED_SEGMENT_DETAILS = "SELECT SEG_ID, SEG_TYPE, SEG_DESC FROM HELIOS_OWN.SA_SEGMENT_HEADER WHERE SEG_TYPE IN (:playList) AND '''' || SEG_DESC || '''' IN(:subPlayList)";
	public static final String SQL_IF_REP_IS_ACCT_MANAGER = "SELECT 'TRUE' ACT_FLAG from HELIOS_OWN.MV_SA_CUSTOMER_PROFILE WHERE EMP_NUMBER = ? AND EMP_LEVEL in( '4','5')  AND rownum =1";
	public static final String SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_1 = "SELECT MASTER_CUST_NUM, COMPANY_NAME, CUSTOMER_TYPE, AGENT_EMAIL_ID, CALL_ORDER,CASE WHEN LAST_CONTACTED_DATE IS NOT NULL THEN to_char(LAST_CONTACTED_DATE, 'MM/DD/YYYY' )  ELSE '' END as S_LAST_CONTACTED_DATE, ACCT_MNGR_FLAG, CUSTOMER_SEGMENT, VAP_SCORE, ACCOUNT_QUALIFY_SCORE, ROW_NUM FROM (SELECT CUST.MASTER_CUST_NUM, CUST.COMPANY_NAME, CUST.CUSTOMER_TYPE, CUST.AGENT_EMAIL_ID, CUST.CALL_ORDER,CUST.LAST_CONTACTED_DATE,CUST.ACCT_MNGR_FLAG, CUST.CUSTOMER_SEGMENT, CUST.VAP_SCORE, CUST.ACCOUNT_QUALIFY_SCORE,DENSE_RANK() OVER (ORDER BY ";
	public static final String SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_2 = " ) ROW_NUM FROM"
			+ " (SELECT MASTER_CUST_NUM MASTER_CUST_NUM," + "   COMPANY_NAME," + "   CUSTOMER_TYPE,"
			+ "   AGENT_EMAIL_ID," + "   CASE" + "     WHEN CALL_ORDER != 0"
			+ "     THEN dense_rank() OVER ( ORDER BY CALL_ORDER DESC)" + "     ELSE 0"
			+ "   END AS CALL_ORDER,LAST_CONTACTED_DATE," + "   ACCT_MNGR_FLAG,"
			+ " (select case when vap_score like 'Rising%' or vap_score like 'Slow%' or vap_score like 'Not%' "
			+ " 		then 'YOUNG - 0 to 6 MONTHS' " + " 		when vap_score like 'Star%' or vap_score like 'Late%' "
			+ " 		then 'TRANSITIONING - 7 to 12 MONTHS' "
			+ " 		when vap_score like 'Defector%' or vap_score like 'Lapsing%' or vap_score like 'Nova%' or vap_score like 'Supernova%' "
			+ " 		then 'MATURE - 13+ MONTHS' "
			+ " 		else DECODE(VAP_SCORE,'0','NA',NULL,'NA','','NA',VAP_SCORE) "
			+ " 		END from Helios_own.sa_customer_info where CUSTOMER_NUMBER= MASTER_CUST_NUM and rownum=1) as CUSTOMER_SEGMENT, "
			+ " (SELECT DECODE(VAP_SCORE,'0','NA',NULL,'NA','','NA',VAP_SCORE) " + " FROM Helios_own.SA_CUSTOMER_INFO "
			+ " WHERE customer_number = MASTER_CUST_NUM " + " AND ROWNUM            = 1 " + " ) AS VAP_SCORE, "
			+ " (SELECT DECODE(ACCOUNT_QUALIFY_SCORE,'0','NA',NULL,'NA','','NA',ACCOUNT_QUALIFY_SCORE) "
			+ " FROM Helios_own.SA_CUSTOMER_INFO " + " WHERE customer_number = MASTER_CUST_NUM "
			+ " AND ROWNUM            = 1 " + " ) AS ACCOUNT_QUALIFY_SCORE " + " FROM" + "   ( "
			+ " SELECT CO.MASTER_CUST_NUM, CO.COMPANY_NAME, CO.CUSTOMER_TYPE, CO.AGENT_EMAIL_ID, CO.CALL_ORDER_t as CALL_ORDER,"
			+ "	ACCT_MNGR_FLAG_t as ACCT_MNGR_FLAG, " 
			
	/*		+ " (SELECT LAST_LIVE_CONTACT_DATE FROM "
			+ "         (SELECT LAST_LIVE_CONTACT_DATE " + "         FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO "
			+ "         WHERE SA_CUSTOMER_SFDC_INFO.MASTER_CUSTOMER_NUMBER = MASTER_CUST_NUM "
			+ "         ORDER BY LAST_LIVE_CONTACT_DATE DESC " + "         ) "
			+ "      WHERE ROWNUM = 1) " */

			+ "  ( select max(activity_date) as last_live_contact_date "
			+ " from ( SELECT TRUNC (MAX(ActivityDate)) AS activity_date "
			+ " FROM (select max (id ) as sf_id, master_account_c from cex01_own.crm_sfdcown_account_c_land "
			+ " where key_type__c = 'Sunrise Master' and master_account_c = MASTER_CUST_NUM group by master_account_c) a "
			+ " left join HELIOS_OWN.CRM_SFDCOWN_STG_TASK t on t.accountid = a.sf_id "
			+ " AND t.STATUS = 'Task Completed - Live Contact' "
			+ " union "
			+ " SELECT TRUNC (max(TASK_INSERT_DATE)) AS activity_date FROM helios_own.sa_segment_detail "
			+ " WHERE customer_number = LPAD ( MASTER_CUST_NUM, 10, 0) AND SEG_DISP_STATUS = 'Task Completed - Live Contact' ) ) "				
			
			+ " AS LAST_CONTACTED_DATE "
			+ " FROM HELIOS_OWN.MV_SA_CALL_ORDER CO LEFT OUTER JOIN HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MVSACP ON CO.EMP_NUMBER= MVSACP.EMP_NUMBER AND CO.MASTER_CUST_NUM=MVSACP.CUSTOMER_NUMBER WHERE CO.EMP_NUMBER = :custNum AND CO.ACCOUNT_GROUP='MM' AND MVSACP.HOLD_OUT='N' " + "   )"
			+ " ) CUST ) MT left join HELIOS_OWN.SA_TASK_TRACKER STT on  MT.MASTER_CUST_NUM = STT.CUSTOMER_NUMBER AND STT.CHECKED_IN_DATE > (sysdate -31)";

	public static final String SQL_GET_ALL_VAP_SCORE = "SELECT VAP_SCORE_ID, VAP_SCORE_TYPE, VAP_SCORE_NAME, VAP_SCORE_DESC FROM HELIOS_OWN.SA_VAP_SCORE ORDER BY VAP_SCORE_ID";
	public static final String SQL_GET_ALL_QUALIFY_SCORE = "SELECT QUAL_SCORE_ID, QUAL_SCORE_NAME, QUAL_SCORE_DESC FROM HELIOS_OWN.SA_QUALIFY_SCORE ORDER BY QUAL_SCORE_ID";

	public static final String SQL_GET_ALL_CUSTOMERS_NON_ACT_MGR_3 = " ) SK" + "   LEFT OUTER JOIN"
			+ "     (SELECT customer_number," + "       MIN(CALL_ORDER) AS call_order"
			+ "     FROM Helios_own.SA_CUSTOMER_INFO" + "     GROUP BY customer_number" + "     ) SCV"
			+ "   ON SK.MASTER_CUST_NUM = SCV.CUSTOMER_NUMBER))";

	public static final String SQL_GET_ORDER_DETAIL_SHIPTO = "SELECT  Order_number,to_char(ST.ordertrandate,'mm/dd/yyyy') order_date,SD.productsku as SKU_NUM,P.SKU_NAME,SD.TRANQUANTITY as ORDER_QTY ,UNITPRICE AS UNIT_SALE_PRICE,PRIMARY_PRODUCT_CAT_CD,LINETOTALAMOUNT+NVL(DL.DISCOUNTLINEAMOUNT,0) AS TOTAL_SPEND, "
			+" P.SKU_NAME AS ITEM_DESC FROM HELIOS_OWN.salestrandetail SD LEFT OUTER JOIN HELIOS_OWN.DISCOUNTLINEITEMASSOC DL ON DL.SALESTRANID=SD.SALESTRANID AND DL.SALESTRANDETAILID = SD.SALESTRANLINEITEMID INNER JOIN( SELECT salestranid, ordertrandate,sourcenumber as ORDER_NUMBER FROM HELIOS_OWN.salestran WHERE  masternumber = ? and sourcenumber = ? and TRANTYPE IN ('IO','AJ','OR') and sourcesystemid=103" 
			+" )ST ON  ST.SALESTRANID = SD.SALESTRANID  LEFT OUTER JOIN (select distinct SKU_NAME,SKU_NUM,PRIMARY_PRODUCT_CAT_CD from  HELIOS_OWN.SA_PRODUCT_MASTER) P ON to_char(TRIM(SD.PRODUCTSKU)) = P.SKU_NUM ";
			
			
			/*"{ CALL HELIOS_OWN.SP_GET_PURCHASE_DETAIL_SHIPTO(:custNum,:orderNum,:orderDate,:cursorOut) }";*/

	/*
	 * public static final String SQL_GET_AGENT_EMAIL =
	 * "SELECT CASE UPPER(:custNum)" +
	 * "         WHEN UPPER(ACCOUNT_MANAGER_EMAIL)" +
	 * "         THEN ACCNT_MGR_EMP_NUMBER" +
	 * "         WHEN ACCNT_MGR_EMP_NUMBER" +
	 * "         THEN ACCNT_MGR_EMP_NUMBER" + "         WHEN VP_EMP_NUMBER" +
	 * "         THEN VP_EMP_NUMBER" + "         WHEN DRM_EMP_NUMBER" +
	 * "         THEN DRM_EMP_NUMBER" + "         WHEN RSD_EMP_NUMBER" +
	 * "         THEN RSD_EMP_NUMBER" + "         WHEN DSM_EMP_NUMBER" +
	 * "         THEN DSM_EMP_NUMBER" +
	 * "         WHEN RVP_EMP_NUMBER  THEN RVP_EMP_NUMBER" +
	 * "       END AS AGENT_EMP_NUMBER from Helios_own.SA_Customer_profile CP where ROWNUM = 1 AND (UPPER(ACCOUNT_MANAGER_EMAIL) = UPPER(:custNum) OR CP.ACCNT_MGR_EMP_NUMBER = :custNum"
	 * + "     OR CP.DRM_EMP_NUMBER           = :custNum" +
	 * "     OR CP.VP_EMP_NUMBER            = :custNum" +
	 * "     OR CP.RVP_EMP_NUMBER           = :custNum" +
	 * "     OR CP.RSD_EMP_NUMBER           = :custNum" +
	 * "     OR CP.DSM_EMP_NUMBER           = :custNum) UNION select to_char(USR_V_USER_ID) from HELIOS_OWN.User_Role_Link_New where to_char(USR_V_USER_ID)=:custNum"
	 * ;
	 */
	public static final String SQL_GET_AGENT_EMAIL = " SELECT CASE UPPER(:custNum) WHEN UPPER(EMAIL) THEN EMP_NUMBER  WHEN EMP_NUMBER  THEN EMP_NUMBER END AS AGENT_EMP_NUMBER "
			+ " FROM Helios_own.MV_SA_CUSTOMER_PROFILE CP WHERE ROWNUM  = 1	AND (UPPER(EMAIL) = UPPER(:custNum)	OR CP.EMP_NUMBER  = :custNum)"
			+ " UNION SELECT TO_CHAR(USR_V_USER_ID)	FROM HELIOS_OWN.User_Role_Link_New	WHERE TO_CHAR(USR_V_USER_ID)=:custNum";

	public static final String SQL_GET_LAST_REFRESH_TIME_NEW = "select TO_CHAR(max(ENDDATE),'DD-MON-YY HH24:MI') from HELIOS_OWN.SA_REFRESH_PROCESS where processname=:processName"
			+ " and ENDDATE is not NULL";

	public static final String SQL_GET_CURRENT_REFRESH_TIME = "select TZ_OFFSET('US/Eastern') from dual";

	public static final String SQL_GET_USER_HIERERCHY = "select EMP_NUMBER, FULL_NAME, DESIGNATION, REP_ROLE_CD, EMP_LEVEL FROM ("
			+ "select distinct EMP_NUMBER, FULL_NAME, DESIGNATION, REP_ROLE_CD, EMP_LEVEL" + "	from ("
			+ "	select VP_EMP_NUMBER as EMP_NUMBER, VP_FULL_NAME as FULL_NAME, 'VP' as DESIGNATION, 'VP' AS REP_ROLE_CD, '1' as EMP_LEVEL "
			+ "	from HELIOS_OWN.SA_CUSTOMER_PROFILE where  DRM_EMP_NUMBER= :accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO union all"
			+ "	select RVP_EMP_NUMBER, RVP_FULL_NAME, 'RVP', 'RVP', '2' from HELIOS_OWN.SA_CUSTOMER_PROFILE where  DRM_EMP_NUMBER=:accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO union all"
			+ "	select RSD_EMP_NUMBER, RSD_FULL_NAME,  'RSD', 'RSD', '3' from HELIOS_OWN.SA_CUSTOMER_PROFILE where  DRM_EMP_NUMBER=:accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO union all"
			+ "	select DRM_EMP_NUMBER ,DRM_FULL_NAME , 'ASM' , 'ASM', '4' from HELIOS_OWN.SA_CUSTOMER_PROFILE where  DRM_EMP_NUMBER=:accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO union all"
			+ "	select DSM_EMP_NUMBER, DSM_FULL_NAME,  'ASM', 'ASM', '4' from HELIOS_OWN.SA_CUSTOMER_PROFILE where  DRM_EMP_NUMBER=:accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO union all"
			+ "	select ACCNT_MGR_EMP_NUMBER, ACCOUNT_MANAGER_NAME,  'REP', REP_ROLE_CD, '5' from HELIOS_OWN.SA_CUSTOMER_PROFILE where  ACCNT_MGR_EMP_NUMBER=:accountNO OR DRM_EMP_NUMBER=:accountNO OR VP_EMP_NUMBER=:accountNO OR RVP_EMP_NUMBER=:accountNO OR RSD_EMP_NUMBER=:accountNO OR DSM_EMP_NUMBER=:accountNO"
			+ "	) where EMP_LEVEL > :level and EMP_NUMBER NOT LIKE '7%' AND EMP_NUMBER NOT LIKE '8%' ) order by EMP_LEVEL asc";

	/*
	 * public static final String SQL_GET_USER_DETAIL =
	 * "SELECT  DISTINCT EMP_NUMBER,NAME AS FULL_NAME, DESIGNATION, EMP_LEVEL FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE WHERE EMP_NUMBER = :accountNO"
	 * ;
	 */
	public static final String SQL_GET_USER_DETAIL = " SELECT EMP_NUMBER, FULL_NAME, DESIGNATION, EMP_LEVEL FROM (  SELECT EMP_NUMBER, NAME AS FULL_NAME, DESIGNATION, MIN (EMP_LEVEL) AS EMP_LEVEL FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE WHERE EMP_NUMBER =:accountNO GROUP BY EMP_NUMBER, NAME, DESIGNATION) WHERE ROWNUM < 2";
	public static final String SQL_GET_MASTER_ACCOUNT_ZIP = "SELECT COMPANY_ZIP FROM HELIOS_OWN.SA_CUSTOMER_PROFILE WHERE CUSTOMER_NUMBER = ? AND ROWNUM = 1";

	// Premium customer exist check
	public static final String SQL_GET_PREMIUM_CUST = "SELECT * FROM HELIOS_OWN.SA_PREMIUM_LTS WHERE CUSTOMER_NUMBER = ? AND ROWNUM = 1";

	/*
	 * public static final String SQL_SEARCH_CUSTOMER_IN_HIERERCHY = "" +
	 * "select distinct CUSTOMER_NUMBER,EMP_NUMBER,FULL_NAME, DESIGNATION,EMP_LEVEL,COMPANY_NAME ,ASSIGN_ACCOUNT_TO"
	 * + "	from (" +
	 * "	select CUSTOMER_NUMBER,VP_EMP_NUMBER as EMP_NUMBER,VP_FULL_NAME as FULL_NAME,'VP' as DESIGNATION, '1' as EMP_LEVEL , COMPANY_NAME,ASSIGN_ACCOUNT_TO"
	 * + "	from HELIOS_OWN.SA_CUSTOMER_PROFILE" +
	 * "	where  DRM_EMP_NUMBER= :customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo union all"
	 * +
	 * "	select CUSTOMER_NUMBER,RVP_EMP_NUMBER,RVP_FULL_NAME,'RVP' as DESIGNATION, '2' as EMP_LEVEL , COMPANY_NAME,ASSIGN_ACCOUNT_TO"
	 * + "	from HELIOS_OWN.SA_CUSTOMER_PROFILE" +
	 * "	where  DRM_EMP_NUMBER=:customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo union all"
	 * +
	 * "	select CUSTOMER_NUMBER,RSD_EMP_NUMBER,RSD_FULL_NAME,'RSD' as DESIGNATION, '3' as EMP_LEVEL, COMPANY_NAME,ASSIGN_ACCOUNT_TO"
	 * + "	from HELIOS_OWN.SA_CUSTOMER_PROFILE" +
	 * "	where  DRM_EMP_NUMBER=:customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo union all"
	 * +
	 * "	select CUSTOMER_NUMBER,DRM_EMP_NUMBER,DRM_FULL_NAME,'DRM' as DESIGNATION, '4' as EMP_LEVEL, COMPANY_NAME,ASSIGN_ACCOUNT_TO"
	 * + "	from HELIOS_OWN.SA_CUSTOMER_PROFILE" +
	 * "	where  DRM_EMP_NUMBER=:customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo union all"
	 * +
	 * "	select CUSTOMER_NUMBER,DSM_EMP_NUMBER,DSM_FULL_NAME,'DSM' as DESIGNATION,'4' as EMP_LEVEL, COMPANY_NAME,ASSIGN_ACCOUNT_TO"
	 * + "	from HELIOS_OWN.SA_CUSTOMER_PROFILE" +
	 * "	where  DRM_EMP_NUMBER=:customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo union all"
	 * +
	 * " select CUSTOMER_NUMBER,ACCNT_MGR_EMP_NUMBER, ACCOUNT_MANAGER_NAME,  'REP' as DESIGNATION,'5' as EMP_LEVEL,COMPANY_NAME,ASSIGN_ACCOUNT_TO from HELIOS_OWN.SA_CUSTOMER_PROFILE"
	 * +
	 * "   where  ACCNT_MGR_EMP_NUMBER=:customerNo OR DRM_EMP_NUMBER=:customerNo OR VP_EMP_NUMBER=:customerNo OR RVP_EMP_NUMBER=:customerNo OR RSD_EMP_NUMBER=:customerNo OR DSM_EMP_NUMBER=:customerNo"
	 * + ")" + "	where EMP_LEVEL >= :EMP_LEVEL " +
	 * "	AND (CUSTOMER_NUMBER =:SEARCH_TEXT OR  UPPER(COMPANY_NAME) =:SEARCH_TEXT)"
	 * //+" AND ASSIGN_ACCOUNT_TO=:assignGroup" + " AND ROWNUM=1";
	 */
	public static final String SQL_SEARCH_CUSTOMER_IN_HIERERCHY = "SELECT DISTINCT MSCP.CUSTOMER_NUMBER, MSCP.EMP_NUMBER, MSCP.NAME as FULL_NAME, MSCP.DESIGNATION, MSCP.EMP_LEVEL, "
			+ " MSCP.COMPANY_NAME, MSCP.ACCOUNT_GROUP as ASSIGN_ACCOUNT_TO FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MSCP WHERE     MSCP.EMP_LEVEL = :EMP_LEVEL AND MSCP.EMP_NUMBER=:EMP_NUMBER  AND (   MSCP.CUSTOMER_NUMBER = :customerNo "
			+ " OR UPPER (MSCP.COMPANY_NAME) = UPPER (:SEARCH_TEXT)) ";

	public static final String SQL_GET_ADOPTION_METRICS_DETAILS = "SELECT BOPIS_FLAG , PRINT_SERVICE_FLAG ,"
			+ " SHOPPING_LIST_FLAG,  RECOM_FLAG,  PRODUCT_ALT_FLAG,  ORDER_MGMT_FLAG,  RETURN_FLAG,  ADMIN_FLAG,  MOBILE_VISITS_FLAG FROM HELIOS_OWN.SA_ADOPTION_METRICS where CUSTOMER_NUMBER =?";
	/*
	 * public static final String SQL_GET_LAYOUT_DETAILS=
	 * "select sec1,sec2,sec3,sec4,sec5,sec6,sec7,sec8,sec9,sec10,sec11,sec12,sec13,sec14,sec15,sec16,sec17,sec18,sec19,sec20,sec21,sec22,sec23,sec24 from HELIOS_OWN.SA_SECTION_ROLE_MAPPING where REP_ROLE_CODE = ?"
	 * ;
	 */

	/*
	 * public static final String SQL_GET_ASSIGN_ACCOUNT_TYPE=
	 * "select LISTAGG(ASSIGN_TYPE||'-'||ROLECODE, '#') WITHIN GROUP (ORDER BY ASSIGN_TYPE) as GROUP_REP from(select distinct CP.ASSIGN_ACCOUNT_TO as ASSIGN_TYPE, CP.REP_ROLE_CD AS ROLECODE from HELIOS_OWN.SA_CUSTOMER_PROFILE CP where CP.ACCNT_MGR_EMP_NUMBER = :custNum"
	 * +" OR CP.DRM_EMP_NUMBER = :custNum" +" OR CP.VP_EMP_NUMBER  = :custNum" +
	 * " OR CP.RVP_EMP_NUMBER = :custNum" +" OR CP.RSD_EMP_NUMBER = :custNum" +
	 * " OR CP.DSM_EMP_NUMBER = :custNum)";
	 */
	public static final String SQL_GET_ASSIGN_ACCOUNT_TYPE = "SELECT LISTAGG(ASSIGN_TYPE ||'-' ||ROLECODE, '#') WITHIN GROUP (ORDER BY ASSIGN_TYPE) AS GROUP_REP"
			+ " FROM  (SELECT DISTINCT CP.ACCOUNT_GROUP AS ASSIGN_TYPE,  CP.REP_ROLE_CD  AS ROLECODE  FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE CP "
			+ "  WHERE CP.EMP_NUMBER = :custNum )";
	/*
	 * public static final String SQL_GET_LEAD_DATA="SELECT M.CUSTOMER_NUMBER ,"
	 * +
	 * " M.COMPANY_NAME , M.CONTRACT_TYPE , M.NAME , M.EMAIL , M.PHONE , M.EMP_NUMBER , ROWNUM from (SELECT DISTINCT MCP.CUSTOMER_NUMBER ,"
	 * +
	 * " MCP.COMPANY_NAME , MCP.CONTRACT_TYPE , MCP.NAME , MCP.EMAIL , MCP.PHONE , MCP.EMP_NUMBER"
	 * + " FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MCP" +
	 * " WHERE MCP.ACCOUNT_GROUP='SAM'" + " AND MCP.EMP_NUMBER     = :repId";
	 */
	/*
	 * public static final String SQL_GET_LEAD_DATA="SELECT B.CUSTOMER_NUMBER ,"
	 * +
	 * " B.COMPANY_NAME , B.CONTRACT_TYPE , B.NAME , B.EMAIL , B.PHONE , B.EMP_NUMBER ,  ROWNUM FROM (SELECT M.CUSTOMER_NUMBER ,"
	 * +
	 * " M.COMPANY_NAME ,  M.CONTRACT_TYPE ,  M.NAME ,  M.EMAIL ,  M.PHONE ,  M.EMP_NUMBER ,  ROWNUM rownumber"
	 * + " FROM  (SELECT DISTINCT MCP.CUSTOMER_NUMBER ," +
	 * " MCP.COMPANY_NAME ,  MCP.CONTRACT_TYPE , MCP.NAME , MCP.EMAIL , MCP.PHONE , MCP.EMP_NUMBER"
	 * +
	 * "  FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MCP  WHERE MCP.ACCOUNT_GROUP='SAM' AND MCP.EMP_NUMBER     = :repId"
	 * ;
	 */
	public static final String SQL_GET_LEAD_DATA = "SELECT B.CUSTOMER_NUMBER ,"
			+ " B.COMPANY_NAME ,  B.CONTRACT_TYPE ,  B.FIRST_NAME,  B.LAST_NAME,  B.EMAIL,  B.PHONE_NUMBER,  B.EMP_NUMBER ,  ROWNUM"
			+ " FROM  (SELECT CPMCP.CUSTOMER_NUMBER ,    CPMCP.COMPANY_NAME ,    CPMCP.CONTRACT_TYPE ,    CPMCP.FIRST_NAME,    CPMCP.LAST_NAME,    CPMCP.EMAIL,    CPMCP.PHONE_NUMBER,    CPMCP.EMP_NUMBER , ROWNUM rownumber"
			+ " FROM  ("

			+ " SELECT M.CUSTOMER_NUMBER ,  M.COMPANY_NAME , M.CONTRACT_TYPE , CP.FIRST_NAME, CP.LAST_NAME, CP.EMAIL, CP.PHONE_NUMBER, M.EMP_NUMBER"
			+ " from ( SELECT DISTINCT MCP.CUSTOMER_NUMBER , MCP.COMPANY_NAME , MCP.CONTRACT_TYPE , MCP.EMP_NUMBER , MCP.EMP_LEVEL"
			+ " FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MCP WHERE MCP.ACCOUNT_GROUP='SAM' AND MCP.EMP_NUMBER     = :repId"
			+ " ) M" + " LEFT OUTER JOIN HELIOS_OWN.SA_CUSTOMER_PROFILE CP"
			+ " on CP.CUSTOMER_NUMBER=M.CUSTOMER_NUMBER";

	public static final String SQL_GET_ALL_LEAD_CUST_COUNT = "select count(*) from (SELECT DISTINCT CUSTOMER_NUMBER, COMPANY_NAME , CONTRACT_TYPE , NAME , EMAIL , PHONE , EMP_NUMBER"
			+ " FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE" + " WHERE EMP_NUMBER = :repId" + " AND ACCOUNT_GROUP='SAM' ";

	public static final String SQL_GET_SAVING_DETAILS = "SELECT SUM(LINETOTALAMOUNT) AS CURR_EST_ANNUAL_SPEND,"
			+ " SUM(PROGRAM_PRICE) AS PROPOSED_ANNUAL_SPEND,"
			+ " SUM(LINETOTALAMOUNT) - SUM(PROGRAM_PRICE) AS PROJECTED_PRICE_SAVING,"
			+ " SUM(PROJ_REBATE) AS PROJECTED_REBATE_SAVING,"
			+ " ((SUM(LINETOTALAMOUNT) - SUM(PROGRAM_PRICE)) + SUM(PROJ_REBATE)) - 299 AS TOTAL_SAVING"
			+ " FROM HELIOS_OWN.MV_SAM_SAVINGS_INFO where PARENT_NUMBER=?";

	public static final String SQL_GET_CATEGORY_PENETRATION_DETAILS = "SELECT CUSTOMER_NUMBER AS CUSTOMER_NUM ,"
			+ " CAT                  AS CAT," + " CAT_PENETRATION      AS CAT_PEN ,"
			+ " CAT_SVNGS_PCT        AS CAT_SAVING_PCT ," + " PCT_VALUE            AS PCT_VALUE"
			+ " FROM HELIOS_OWN.MV_SAM_CAT_PENETRATION WHERE CUSTOMER_NUMBER=?";
	public static final String YTD_SPEND_CATEGORY = "select to_char(to_date(TRANDATE,'DD-MM-YY'),'MM-MON-YY')  AS MONTH ,CAT AS CAT, TOTAL_SPEND||'#'||CHANNEL AS TOTAL_SPND from (SELECT CUSTOMER_NUMBER,"
			+ " TRANDATE, CHANNEL, CAT, SUM (TOTAL_SPEND) AS TOTAL_SPEND"
			+ " FROM (SELECT H.PARENT_NUMBER AS CUSTOMER_NUMBER,"
			+ " TRUNC (L.TRANDATE) AS TRANDATE, CASE WHEN H.SOURCESYSTEMID = 107 THEN 'R' ELSE 'D' END AS CHANNEL, CASE"
			+ "  WHEN PRIMARY_PRODUCT_CAT_CD = 9 THEN 10  WHEN PRIMARY_PRODUCT_CAT_CD = 8 THEN 11"
			+ " ELSE TO_NUMBER (NVL (PRIMARY_PRODUCT_CAT_CD, 11))  END  AS CAT,"
			+ " CASE WHEN H.SOURCESYSTEMID = 107 THEN L.EXTENDEDPRICE" + " ELSE L.LINETOTALAMOUNT END AS TOTAL_SPEND"
			+ " FROM HELIOS_OWN.MV_SAM_ORDER_DETAIL L INNER JOIN"
			+ " (SELECT MSODR.SALESTRANID, MSODR.SOURCESYSTEMID, MSODR.PARENT_NUMBER"
			+ " FROM HELIOS_OWN.MV_SAM_ORDER_HEADER MSODR WHERE     MSODR.SOURCESYSTEMID = 107 AND MSODR.TRANSTATUS = 'REGULAR'"
			+ " UNION  SELECT MSODD.SALESTRANID,  MSODD.SOURCESYSTEMID, MSODD.PARENT_NUMBER"
			+ " FROM HELIOS_OWN.MV_SAM_ORDER_HEADER MSODD"
			+ " WHERE     MSODD.SOURCESYSTEMID = 102  AND MSODD.RECORDTYPE = '0001') H"
			+ " ON H.SALESTRANID = L.SALESTRANID  LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER P"
			+ " ON TO_CHAR (L.PRODUCTSKU) = P.SKU_NUM)  GROUP BY CUSTOMER_NUMBER, TRANDATE, CHANNEL,"
			+ " CAT) CP where CP.CUSTOMER_NUMBER=?";
	public static final String SQL_GET_ORDER_HEADER_SAM = "SELECT OH.PARENT_NUMBER CUST_NUM,  OH.SALESTRANID ORDER_NUMBER,  OH.SOURCENUMBER SOURCE_NUMBER,  TO_CHAR(OH.ORDERTRANDATE,'MM/DD/YYYY') ORDER_DATE,  COUNT(OD.SALESTRANID) ORDER_LINE_COUNT,  SUM(OD.TRANQUANTITY*OD.UNITPRICE) ORDER_AMOUNT FROM (  (SELECT MSODR.SALESTRANID,    MSODR.SOURCESYSTEMID,    MSODR.PARENT_NUMBER,    MSODR.SOURCENUMBER,    MSODR.TRANAMOUNT,    MSODR.ORDERTRANDATE  FROM HELIOS_OWN.MV_SAM_ORDER_HEADER MSODR  WHERE ((MSODR.SOURCESYSTEMID = 107  AND MSODR.TRANSTATUS         = 'REGULAR')  OR (MSODR.SOURCESYSTEMID     = 102  AND MSODR.RECORDTYPE         = '0001'))  AND PARENT_NUMBER            = LPAD (?, 10, '0')  ) OH INNER JOIN  (SELECT SALESTRANID,    TRANQUANTITY,    UNITPRICE  FROM HELIOS_OWN.MV_SAM_ORDER_DETAIL  WHERE TRANQUANTITY     > 0  AND UNITPRICE          > 0  ) OD ON OH.SALESTRANID = OD.SALESTRANID)GROUP BY OH.PARENT_NUMBER ,  OH.SALESTRANID ,  OH.SOURCENUMBER,  TO_CHAR(OH.ORDERTRANDATE,'MM/DD/YYYY') ,  OH.TRANAMOUNT";
	/*
	 * public static final String SQL_TOP_SEARCH_SAM =
	 * " SELECT B.CUSTOMER_NUMBER ," +
	 * " B.COMPANY_NAME , B.CONTRACT_TYPE , B.FIRST_NAME, B.LAST_NAME, B.EMAIL, B.PHONE_NUMBER, B.EMP_NUMBER , ROWNUM"
	 * +
	 * " FROM (SELECT CPMCP.CUSTOMER_NUMBER , CPMCP.COMPANY_NAME , CPMCP.CONTRACT_TYPE , CPMCP.FIRST_NAME, CPMCP.LAST_NAME,"
	 * + " CPMCP.EMAIL, CPMCP.PHONE_NUMBER, CPMCP.EMP_NUMBER , ROWNUM rownumber"
	 * +
	 * " FROM (SELECT M.CUSTOMER_NUMBER , M.COMPANY_NAME , M.CONTRACT_TYPE , CP.FIRST_NAME, CP.LAST_NAME, CP.EMAIL, CP.PHONE_NUMBER, M.EMP_NUMBER"
	 * +
	 * " FROM ( SELECT DISTINCT MCP.CUSTOMER_NUMBER , MCP.COMPANY_NAME , MCP.CONTRACT_TYPE , MCP.EMP_NUMBER , MCP.EMP_LEVEL"
	 * +
	 * " FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MCP WHERE MCP.ACCOUNT_GROUP='SAM' AND MCP.EMP_NUMBER     = :repId"
	 * +
	 * " ) M   LEFT OUTER JOIN HELIOS_OWN.SA_CUSTOMER_PROFILE CP   ON CP.CUSTOMER_NUMBER=M.CUSTOMER_NUMBER   ) CPMCP ) B"
	 * +
	 * " where (B.CUSTOMER_NUMBER =:SEARCH_TEXT OR  UPPER(B.COMPANY_NAME) =:SEARCH_TEXT) AND ROWNUM=1"
	 * ;
	 */
	public static final String SQL_GENERATE_SAVINGS_REPORT = "SELECT SM.PARENT_NUMBER CUSTOMER_NUMBER,SM.CHILDNUMBER,SM.UNITPRICE, NVL(TO_CHAR(TO_DATE(SM.ORDERTRANDATE,'DD-MM-YY'),'DD-MON-YY'),'') AS ORDERTRANDATE ,SM.PRODUCTSKU PRODUCT_SKU_NUM,  SM.SOURCENUMBER ORDER_NUMBER,  SM.TRANQUANTITY QUANTITY,  PM.SKU_NAME PRODUCT_NAME,  SM.CHANNEL,  SM.LINETOTALAMOUNT TOTAL_AMOUNT,  SM.PROGRAM_PRICE PROGRAM_PRICE,  SM.PROJ_PGM_ORD_PRICE PROJECTED_ORDER_PRICE,  SM.PROJ_REBATE PROJECTED_REBATE,  (SM.LINETOTALAMOUNT-SM.PROGRAM_PRICE) PROGRAM_SAVINGS FROM (  (SELECT PARENT_NUMBER,    PRODUCTSKU,    SOURCENUMBER,    TRANQUANTITY,    CHANNEL,    LINETOTALAMOUNT,    PROGRAM_PRICE,    PROJ_PGM_ORD_PRICE,    PROJ_REBATE , ORDERTRANDATE , UNITPRICE ,  CHILDNUMBER FROM HELIOS_OWN.MV_SAM_SAVINGS_INFO  WHERE parent_number= :custNum  ) SM LEFT OUTER JOIN HELIOS_OWN.SA_PRODUCT_MASTER PM ON SM.PRODUCTSKU = PM.SKU_NUM) order by SM.ORDERTRANDATE";
	// public static final String SQL_GENERATE_SAM_SAVINGS_REPORT ="SELECT
	// SO.PARENT_NUMBER CUSTOMER_NUMBER,SO.ORDERTRANDATE ORDER_DATE,
	// SD.PRODUCTSKU PRODUCT_SKU_NUM, SO.SOURCENUMBER ORDER_NUMBER,
	// SD.TRANQUANTITY QUANTITY, SD.PRODUCTNAME PRODUCT_NAME,SD.UNITPRICE,
	// SO.TRANCHANNEL, SD.LINETOTALAMOUNT TOTAL_AMOUNT,
	// (SD.UNITPRICE*SD.TRANQUANTITY) PROGRAM_PRICE, SO.TRANAMOUNT
	// PROJECTED_ORDER_PRICE,(SD.LINETOTALAMOUNT-(SD.UNITPRICE*SD.TRANQUANTITY))
	// PROGRAM_SAVINGS FROM ((SELECT
	// PARENT_NUMBER,SOURCENUMBER,ORDERTRANDATE,TRANCHANNEL,TRANAMOUNT FROM
	// HELIOS_OWN.MV_SALESTRAN_ORDER WHERE parent_number= :custNum )SO LEFT
	// OUTER JOIN HELIOS_OWN.MV_SALESTRAN_DETAIL SD ON
	// SO.SOURCENUMBER=SD.SOURCENUMBER)";
	public static final String SQL_GENERATE_SAM_SAVINGS_REPORT = " SELECT SO.PARENT_NUMBER CUSTOMER_NUMBER, SO.ORDERTRANDATE ORDER_DATE, SD.PRODUCTSKU PRODUCT_SKU_NUM, SO.SOURCENUMBER ORDER_NUMBER, NVL(SD.TRANQUANTITY,0) QUANTITY, SD.PRODUCTNAME PRODUCT_NAME, NVL(SD.UNITPRICE,0), SO.TRANCHANNEL,  NVL(SD.LINETOTALAMOUNT,0) as TOTAL_AMOUNT, (NVL(SD.UNITPRICE,0)*NVL(SD.TRANQUANTITY,0)) PROGRAM_PRICE ,  SO.TRANAMOUNT PROJECTED_ORDER_PRICE, (NVL(SD.LINETOTALAMOUNT,0)-(NVL(SD.UNITPRICE,0)*NVL(SD.TRANQUANTITY,0))) PROGRAM_SAVINGS"
			+ " FROM ( (SELECT PARENT_NUMBER,  SOURCENUMBER,  ORDERTRANDATE,  TRANCHANNEL,  TRANAMOUNT FROM HELIOS_OWN.MV_SALESTRAN_ORDER WHERE parent_number =:custNum )SO LEFT OUTER JOIN HELIOS_OWN.MV_SALESTRAN_DETAIL SD	ON SO.SOURCENUMBER=SD.SOURCENUMBER)";
	//public static final String SQL_GET_NEPHOS_CONFIG = "SELECT SERVICE_NAME,RESOURCE_URL,AUTH_URL,CLIENT_ID,CLIENT_SECRET,ACCESS_TOKEN FROM HELIOS_OWN.SAM_NEPHOS_SERVICE_CONFIG WHERE SERVICE_NAME='NEPHOS'";
	public static final String SQL_GET_NEPHOS_PRICE_CONFIG = "SELECT SERVICE_NAME,RESOURCE_URL,AUTH_URL,CLIENT_ID,CLIENT_SECRET,ACCESS_TOKEN FROM HELIOS_OWN.SAM_NEPHOS_SERVICE_CONFIG WHERE SERVICE_NAME=?";
	public static final String SQL_GET_NEPHOS_ARKE_CIS_CONFIG = "SELECT SERVICE_NAME,RESOURCE_URL,AUTH_URL,CLIENT_ID,CLIENT_SECRET,ACCESS_TOKEN FROM HELIOS_OWN.SAM_NEPHOS_SERVICE_CONFIG WHERE SERVICE_NAME='NEPHOS_ARKE_CIS'";
	public static final String SQL_REFRESH_NEPHOS_TOKEN = "UPDATE HELIOS_OWN.SAM_NEPHOS_SERVICE_CONFIG SET ACCESS_TOKEN=?,TOKEN_GENERATED_ON=systimestamp WHERE SERVICE_NAME=?";
	public static final String SQL_GET_SFDC_CONFIG = "SELECT SERVICE_NAME, RESOURCE_URL FORCE_URL,CLIENT_ID,CLIENT_SECRET,USERNAME,USERPASSWORD FROM HELIOS_OWN.SAM_NEPHOS_SERVICE_CONFIG WHERE SERVICE_NAME='SFDC'";
	//public static final String SQL_GET_SFDC_INPUT_IDS_1 = "SELECT MASTER_CUSTOMER_NUMBER CUST_NUM,   IAM_ID CUST_ID ,   CASE     WHEN SHRD_ACCNT_MGR_EMP_NUM = ?     AND SHRD_ACCNT_MGR_EMP_NUM IS NOT NULL     THEN SHRD_ACCOUNT_OWNER     ELSE ACCOUNT_OWNER   END OWNER_ID,   CONTACT_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE CONTACT_ID =?";
	public static final String SQL_GET_SFDC_INPUT_IDS_1="SELECT NVL(( SELECT REP_SFDC_ID FROM HELIOS_OWN.SA_SFDC_REP_INFO WHERE REP_EMP_NUM = ?), ?) AS OWNER_ID,MASTER_CUSTOMER_NUMBER CUST_NUM, IAM_ID CUST_ID ,CONTACT_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE CONTACT_ID =?";
	//public static final String SQL_GET_SFDC_INPUT_IDS_1="SELECT (SELECT REP_SFDC_ID FROM HELIOS_OWN.SA_SFDC_REP_INFO WHERE REP_EMP_NUM = ?) AS OWNER_ID,MASTER_CUSTOMER_NUMBER CUST_NUM, IAM_ID CUST_ID ,CONTACT_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE CONTACT_ID =?";
	public static final String SQL_GET_SFDC_INPUT_IDS_ADMIN="SELECT  ? AS OWNER_ID,MASTER_CUSTOMER_NUMBER CUST_NUM, IAM_ID CUST_ID ,CONTACT_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE CONTACT_ID =?";
	//public static final String SQL_GET_SFDC_INPUT_IDS_2 = "SELECT DISTINCT MASTER_CUSTOMER_NUMBER CUST_NUM,   IAM_ID CUST_ID ,   CASE     WHEN SHRD_ACCNT_MGR_EMP_NUM = ?     AND SHRD_ACCNT_MGR_EMP_NUM IS NOT NULL     THEN SHRD_ACCOUNT_OWNER     ELSE ACCOUNT_OWNER   END OWNER_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE MASTER_CUSTOMER_NUMBER=?";
	public static final String SQL_GET_SFDC_INPUT_IDS_2="SELECT DISTINCT NVL(( SELECT REP_SFDC_ID FROM HELIOS_OWN.SA_SFDC_REP_INFO WHERE REP_EMP_NUM = ?), ?)AS OWNER_ID, MASTER_CUSTOMER_NUMBER  CUST_NUM, IAM_ID CUST_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE  MASTER_CUSTOMER_NUMBER=?";
	public static final String SQL_GET_SFDC_INPUT_IDS_2_ADMIN="SELECT DISTINCT  ? AS OWNER_ID,MASTER_CUSTOMER_NUMBER  CUST_NUM, IAM_ID CUST_ID FROM HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE  MASTER_CUSTOMER_NUMBER=?";
	
	public static final String SQL_GET_LATEST_CONTACTED_DETAILS = "SELECT TM_KY, TO_CHAR(to_date(CLD_DT, 'DD-MM-YY'), 'DD-MON-YY') as CLD_DT, FSC_YR, FSC_PRD, FSC_WK, FSC_DY FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day' AND CLD_DT  IS NOT NULL AND TRUNC (CLD_DT) = TRUNC (SYSDATE) ";
	public static final String SQL_PUT_LATEST_CONTACTED_DETAILS = "INSERT INTO HELIOS_OWN.SA_TASK_TRACKER VALUES  (?,?,?,null,null,?,?,?,?,?,?,?,?,? )";

	public static final String SQL_INSERT_LATEST_CONTACTED_DETAILS = "INSERT INTO HELIOS_OWN.SA_TASK_TRACKER(CUSTOMER_NUMBER,DIVISION,SLS_REP_ID,SLS_REP_NAME,SLS_REP_ROLE_CD,LOGGED_IN_USR_ID,"
			+ "LOGGED_IN_USR_NAME,LOGGED_IN_USR_ROLE,CHECKED_IN_DATE,INSERT_DATE,FSC_YR,FSC_PRD,FSC_WK,FSC_DY)  VALUES  (?,?,?,?,?,?,?,?,to_date(sysdate,'dd-mon-yy'),to_date(sysdate,'dd-mon-yy'),?,?,?,?)";

	public static final String SQL_INSERT_LATEST_CONTACTED_DETAILS_SPL_USER = "INSERT INTO HELIOS_OWN.SA_TASK_TRACKER(CUSTOMER_NUMBER,DIVISION,SLS_REP_ID,SLS_REP_NAME,SLS_REP_ROLE_CD,LOGGED_IN_USR_ID,"
			+ "LOGGED_IN_USR_NAME,LOGGED_IN_USR_ROLE,CHECKED_IN_DATE,INSERT_DATE,FSC_YR,FSC_PRD,FSC_WK,FSC_DY)  VALUES  (?,?,NULL,NULL,NULL,?,?,?,to_date(sysdate,'dd-mon-yy'),to_date(sysdate,'dd-mon-yy'),?,?,?,?)";

	public static final String SQL_SELECT_REP_INFO = "select USR_V_FIRST_NAME || ' ' || USR_V_LAST_NAME AS FULL_NAME, ROLE_SHRT_NAME from HELIOS_OWN.user_role_link_new  where USR_V_USER_ID=?";

	public static final String SQL_GET_ALL_CONTACTED_CUSTOMERS = "SELECT CUSTOMER_NUMBER, SLS_REP_NAME, TO_CHAR(to_date(CHECKED_IN_DATE, 'DD-MM-YY'), 'DD-MON-YY') as CLD_DT, FSC_YR, FSC_PRD, FSC_WK, FSC_DY , LOGGED_IN_USR_NAME, TO_CHAR(to_date(CHECKED_IN_DATE, 'DD-MM-YY'), 'DD-MON-YY') AS CHECKED_IN_DATE FROM HELIOS_OWN.SA_TASK_TRACKER where CHECKED_IN_DATE > (sysdate -31)";
	public static final String SQL_DELETE_CUSTOMERS_FROM_TASK_TRACKER = "delete from HELIOS_OWN.SA_TASK_TRACKER where CUSTOMER_NUMBER IN(";

	public static final String SELECT_SEG_HEADER_TEXT = "select * from HELIOS_OWN.SA_SEG_HDR_TEXT "
			+ "where seg = (SELECT SEG FROM HELIOS_OWN.SA_SEGMENT WHERE SEG_DESC=UPPER(?)) and seg_id= ?";
	public static final String SELECT_SEG_HEADER_LABELS = "SELECT HDR_ID , HEADER_NAME FROM HELIOS_OWN.SA_SEG_HDR_LABELS WHERE HDR_ID IN";
	public static final String SQL_GET_SEGMENTS = "select SEG||'-'||SEG_DESC from HELIOS_OWN.SA_SEGMENT";

	//public static final String SQL_GET_SEGMENTDESCS = "select SEG_ID||'-'||SEG_NAME||'##'||CD_STATUS from HELIOS_OWN.SA_SEGMENT_HEADER_DUMMY where SEG_TYPE =?";
	public static final String SQL_GET_SEGMENTDESCS = "select SEG_ID||'-'||SEG_NAME from HELIOS_OWN.SA_SEGMENT_HEADER where SEG_TYPE =?";

	public static final String SQL_UPDATE_CTA_CONTENT = "UPDATE HELIOS_OWN.SA_SEG_HDR_TEXT SET ";

	public static final String SELECT_SEG_DISPOSITION = "select DISP_DESC||'~'||DISP_LINK_TASK_ID||'~'||DISP_ID from HELIOS_OWN.SA_DISP_STATUS";
	public static final String SELECT_LINKTASK_BY_DISPOSITION = "select DISP_LINK_TASK_ID from HELIOS_OWN.SA_DISP_STATUS WHERE DISP_DESC=?";
	public static final String SELECT_GET_PARAMIDS = "SELECT SD.HEADER_ID , LISTAGG(PARAM_ID, ',') WITHIN GROUP (ORDER BY HEADER_ID) AS PARAMIDS"
			+ " FROM HELIOS_OWN.SA_SEGMENT_SUB_DTL SD" + " WHERE SD.TASK_ID_COMBINATION=?" + " GROUP BY SD.HEADER_ID";

	/* old query commented */
	/*
	 * public static final String SELECT_DISP_COMMENT_DETAILS =
	 * "select SEG_DISP_STATUS, to_char(cast(TASK_INSERT_DATE as date),'MM/DD/YYYY') AS TASK_INSERT_DATE , to_char(cast(TASK_INSERT_DATE as date),'MM/DD/YYYY hh:mi:ss') AS FULL_TASK_INSERT_DATE, COMMENT_TEXT , CREATED_BY, CONTACT_ID, SFDC_CONTACT_FULLNAME from(SELECT DISTINCT ssd.TASK_ID_COMBINATION, SSD.SEG_DISP_STATUS AS SEG_DISP_STATUS , SSD.TASK_INSERT_DATE  AS TASK_INSERT_DATE,"
	 * +
	 * " SSD.CREATED_BY AS CREATED_BY, NVL(SSC.COMMENT_TEXT,'NA') AS COMMENT_TEXT, SSD.CONTACT_ID, SSD.SFDC_CONTACT_FULLNAME FROM HELIOS_OWN.SA_SEGMENT_DETAIL SSD INNER JOIN"
	 * +
	 * " HELIOS_OWN.SA_SEGMENT_COMMENTS SSC ON SSD.TASK_ID_COMBINATION=SSC.TASK_ID_COMBINATION AND SSD.TASK_INSERT_DATE=SSC.INSERT_DATE WHERE SSD.TASK_ID_COMBINATION=? order by SSD.TASK_INSERT_DATE DESC)"
	 * ;
	 */
	/* new query started */
	public static final String SELECT_DISP_COMMENT_DETAILS = "SELECT SEG_DISP_STATUS, TO_CHAR(CAST(TASK_INSERT_DATE AS DATE),'MM/DD/YYYY') AS TASK_INSERT_DATE , TO_CHAR(CAST(TASK_INSERT_DATE AS DATE),'MM/DD/YYYY hh:mi:ss') AS FULL_TASK_INSERT_DATE,"
			+ " COMMENT_TEXT , CREATED_BY, CONTACT_ID, SFDC_CONTACT_FULLNAME , TASK_SUBJECT FROM  (SELECT DISTINCT ssd.TASK_ID_COMBINATION, SSD.SEG_DISP_STATUS  AS SEG_DISP_STATUS , SSD.TASK_INSERT_DATE AS TASK_INSERT_DATE,"
			+ " SSD.CREATED_BY  AS CREATED_BY, NVL(SSC.COMMENT_TEXT,'NA') AS COMMENT_TEXT, SSD.CONTACT_ID, SSD.SFDC_CONTACT_FULLNAME , SSD.TASK_SUBJECT FROM HELIOS_OWN.SA_SEGMENT_DETAIL SSD  LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_COMMENTS SSC  ON SSD.TASK_ID_COMBINATION   =SSC.TASK_ID_COMBINATION"
			+ " AND SSD.LINK_TASKS = SSC.LINK_TASKS  WHERE SSD.TASK_ID_COMBINATION=? ORDER BY SSD.TASK_INSERT_DATE DESC )";

	public static final String SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_1 = "WITH DT_SEG AS"
			+ " (SELECT LPAD(SEG_ID,3,0) SEG_ID, CASE SEG_FREQ  WHEN 'Monthly' THEN SUBSTR(TM_KY,1,6) WHEN 'Weekly' THEN SUBSTR(TM_KY,1,7) ELSE TO_CHAR(TM_KY) END AS TM_KEY, SEG_FREQ"
			+ " FROM ((SELECT SEG_ID,SEG_FREQ,'1' row_num  FROM HELIOS_OWN.SA_SEGMENT_HEADER WHERE seg_id IN (";
	/* old query commented */
	/*
	 * public static final String SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_2="))"
	 * +
	 * " FULL OUTER JOIN (SELECT TM_KY, '1' row_num FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day' AND TRUNC(CLD_DT)= TRUNC(sysdate)) USING (row_num)))"
	 * +
	 * " SELECT * FROM (SELECT ss.FYR_FPRD_FWK_FDY,ss.CTA_SEGMENT, ss.CTA_SUB_SEGMENT,ss.TASK_INSERT_DATE, to_char(cast(ss.TASK_INSERT_DATE as date),'MM/DD/YYYY') as TASK_INSERT_DATE_DISPLAY,ss.SEG_DISP_STATUS "
	 * +
	 * " FROM (SELECT ss.CTA_SEGMENT,ss.CTA_SUB_SEGMENT, MAX(ss.TASK_INSERT_DATE) AS TASK_INSERT_DATE"
	 * +
	 * " FROM  (SELECT FYR_FPRD_FWK_FDY, CTA_SEGMENT, CTA_SUB_SEGMENT , TASK_INSERT_DATE"
	 * +
	 * "   FROM HELIOS_OWN.SA_SEGMENT_DETAIL  WHERE CUSTOMER_NUMBER=? AND CTA_SEGMENT IN (SELECT LPAD(SEG,2,0) seg FROM HELIOS_OWN.SA_SEGMENT WHERE SEG_DESC IN("
	 * ;
	 * 
	 * public static final String SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_3=
	 * ") )ss  GROUP BY CTA_SEGMENT, CTA_SUB_SEGMENT )sd" +
	 * " LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_DETAIL ss ON sd.CTA_SEGMENT =ss.CTA_SEGMENT  AND sd.CTA_SUB_SEGMENT =ss.CTA_SUB_SEGMENT"
	 * +
	 * " AND sd.TASK_INSERT_DATE=ss.TASK_INSERT_DATE )AA JOIN DT_SEG SHH ON AA.CTA_SUB_SEGMENT=SHH.SEG_ID"
	 * +
	 * " AND (  CASE SHH.SEG_FREQ WHEN 'Monthly' THEN SUBSTR(FYR_FPRD_FWK_FDY,1,6) WHEN 'Weekly' THEN SUBSTR(FYR_FPRD_FWK_FDY,1,7) ELSE FYR_FPRD_FWK_FDY END ) = SHH.TM_KEY "
	 * ;
	 */
	/* new query started */
	public static final String SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_2 = "))"
			+ " FULL OUTER JOIN (SELECT TM_KY, '1' row_num FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day' AND TRUNC(CLD_DT)= TRUNC(sysdate)) USING (row_num)))"
			+ " SELECT * FROM (SELECT ss.FYR_FPRD_FWK_FDY,ss.CTA_SEGMENT, ss.CTA_SUB_SEGMENT,ss.TASK_INSERT_DATE, to_char(cast(ss.TASK_INSERT_DATE as date),'MM/DD/YYYY') as TASK_INSERT_DATE_DISPLAY,ss.SEG_DISP_STATUS "
			+ " FROM (SELECT ss.CTA_SEGMENT,ss.CTA_SUB_SEGMENT, MAX(ss.TASK_INSERT_DATE) AS TASK_INSERT_DATE  "
			+ " FROM  (SELECT FYR_FPRD_FWK_FDY, CTA_SEGMENT, CTA_SUB_SEGMENT , TASK_INSERT_DATE  "
			+ "   FROM HELIOS_OWN.SA_SEGMENT_DETAIL  WHERE CUSTOMER_NUMBER=? AND CTA_SEGMENT IN (SELECT LPAD(SEG,2,0) seg FROM HELIOS_OWN.SA_SEGMENT WHERE SEG_DESC IN(";

	public static final String SQL_GET_DISPOSITION_STATUS_FOR_CUSTOMER_3 = ") )ss  GROUP BY CTA_SEGMENT, CTA_SUB_SEGMENT )sd"
			+ " LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_DETAIL ss ON sd.CTA_SEGMENT =ss.CTA_SEGMENT  AND sd.CTA_SUB_SEGMENT =ss.CTA_SUB_SEGMENT"
			+ " AND sd.TASK_INSERT_DATE=ss.TASK_INSERT_DATE )AA JOIN DT_SEG SHH ON AA.CTA_SUB_SEGMENT=SHH.SEG_ID"
			+ " AND (  CASE SHH.SEG_FREQ WHEN 'Monthly' THEN SUBSTR(FYR_FPRD_FWK_FDY,1,6) WHEN 'Weekly' THEN SUBSTR(FYR_FPRD_FWK_FDY,1,7) ELSE FYR_FPRD_FWK_FDY END ) = SHH.TM_KEY ";

	public static final String SQL_CHECK_TASKID_AVAILABILITY = "select * from (select TASK_ID_COMBINATION from HELIOS_OWN.SA_SEGMENT_DETAIL where TASK_ID_COMBINATION ";

	/* old query commented */
	/*
	 * public static final String SQL_CHECK_TASKID_AVAILABILITY_BY_TASKID =
	 * "select * from (select TASK_ID_COMBINATION from HELIOS_OWN.SA_SEGMENT_DETAIL where TASK_ID_COMBINATION "
	 * ;
	 */
	/* new query start */
	public static final String SQL_CHECK_TASKID_AVAILABILITY_BY_TASKID = "select * from (select TASK_ID_COMBINATION from HELIOS_OWN.SA_SEGMENT_DETAIL where TASK_ID_COMBINATION ";

	public static final String SQL_CHECK_PARAM_AVAILABILITY = "WITH DT_SEG AS   (SELECT SEG_ID,     CASE SEG_FREQ       WHEN 'Monthly'       THEN SUBSTR(TM_KY,1,6)       WHEN 'Weekly'       THEN SUBSTR(TM_KY,1,7)       ELSE TO_CHAR(TM_KY)     END AS TM_KEY,     SEG_FREQ   FROM (     (SELECT SEG_ID,       SEG_FREQ,       '1' row_num     FROM helios_own.SA_SEGMENT_HEADER    WHERE seg_id = :segId     )   FULL OUTER JOIN     (SELECT TM_KY,       '1' row_num     FROM HELIOS_OWN.D_TIME     WHERE TM_LVL     = 'Day'     AND TRUNC(CLD_DT)= TRUNC(sysdate)     ) USING (row_num))   ) SELECT FYR_FPRD_FWK_FDY FROM   (SELECT FYR_FPRD_FWK_FDY,     SEG_ID   FROM (     (SELECT FYR_FPRD_FWK_FDY,       CTA_SUB_SEGMENT     FROM helios_own.SA_SEGMENT_SUB_DTL     WHERE CUSTOMER_NUMBER = :CustNum     AND CTA_SUB_SEGMENT   = :segId     ) SSD   INNER JOIN helios_own.SA_SEGMENT_HEADER SH   ON SSD.CTA_SUB_SEGMENT = SH.SEG_ID)   )SD JOIN DT_SEG DT ON SD.SEG_ID= DT.SEG_ID WHERE (   CASE DT.SEG_FREQ     WHEN 'Monthly'     THEN SUBSTR(SD.FYR_FPRD_FWK_FDY,1,6)     WHEN 'Weekly'     THEN SUBSTR(SD.FYR_FPRD_FWK_FDY,1,7)     ELSE SD.FYR_FPRD_FWK_FDY   END = DT.TM_KEY)";
	public static final String SELECT_SEGMENT_SUB_DTL = " SELECT SD.HEADER_ID AS HEADER_ID, SD.PARAM_ID, SD.CTA_SEGMENT, SD.CTA_SUB_SEGMENT, SD.PARAM_LABEL, SD.DYNAMIC_PARAM_VALUE  AS DYNAMIC_PARAM_VALUE, SD.CONSTANT_PARAM_VALUE AS CONSTANT_PARAM_VALUE, SD.CREATED_BY, SD.UPDATED_BY"
			+ " FROM (SELECT SSD.CTA_SUB_SEGMENT, SSD.CTA_SEGMENT, SSD.PARAM_VALUE AS DYNAMIC_PARAM_VALUE, SSD.CREATED_BY, SSD.UPDATED_BY, SSD.HEADER_ID, SSD.PARAM_ID, SSP.PARAM_LABEL, SSP.PARAM_VALUE AS CONSTANT_PARAM_VALUE"
			+ " FROM HELIOS_OWN.SA_SEGMENT_SUB_DTL SSD LEFT JOIN HELIOS_OWN.SA_SEGMENT_PARAM SSP  ON SSD.HEADER_ID =SSP.HDR_ID  AND SSD.PARAM_ID =SSP.PARAM_ID AND SSD.CTA_SUB_SEGMENT  =SSP.CTA_SUB_SEGMENT "
			+ " WHERE SSD.CUSTOMER_NUMBER =:custNum AND SSD.CTA_SUB_SEGMENT  =:subSegId AND SSD.FYR_FPRD_FWK_FDY = (SELECT MAX (DISTINCT TO_NUMBER(FYR_FPRD_FWK_FDY))  AS FYR_FPRD_FWK_FDY  FROM HELIOS_OWN.SA_SEGMENT_SUB_DTL "
            +"   WHERE     CUSTOMER_NUMBER = :custNum  AND CTA_SUB_SEGMENT = :subSegId  GROUP BY CUSTOMER_NUMBER, DIVISION, CTA_SEGMENT, CTA_SUB_SEGMENT))SD LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_PARAM SP ON SD.HEADER_ID =SP.HDR_ID AND SD.PARAM_ID  =SP.PARAM_ID AND SD.CTA_SUB_SEGMENT=SP.CTA_SUB_SEGMENT";

	public static final String SELECT_SEG_MKTG_RESOURCES = "SELECT SEG,SEG_ID,PARAM_ID,LABEL_PREFIX_TEXT,LABEL_NAME,LABEL_SUFFIX_TEXT,LABEL_HIDDEN_URL,HEADER_ID FROM HELIOS_OWN.SA_SEG_MKTG_RESOURCES WHERE SEG=(SELECT SEG FROM HELIOS_OWN.SA_SEGMENT WHERE SEG_DESC=UPPER(:segName)) AND SEG_ID=:subSegId";

	public static final String GET_PARAM_COUNT = "select count(*) from HELIOS_OWN.SA_SEGMENT_PARAM WHERE CTA_SUB_SEGMENT= ?";

	public static final String SQL_GET_ALLCATEGORY_SALESDATA_BARCHART = "SELECT CUSTOMER_NUMBER, PRIMARY_CAT_DESC, CURR_ROLL_3_MONTH_SLS, PRIOR_ROLL_3_MONTH_SLS, CURR_YEAR_TO_DATE_SLS, PRIOR_YEAR_TO_DATE_SLS, REFRESH_DATE "
			+ " FROM HELIOS_OWN.WIR_SA_CUST_PERF_BY_CAT "
			+ " WHERE PRIMARY_CAT_DESC IN(:allCategories) AND CUSTOMER_NUMBER=:custNum";

	public static final String SQL_GETALL_PERFCATEGORY_COLUMN_DOUGHNUT_CHART = "SELECT CUSTOMER_NUMBER, PRIMARY_CAT_DESC, ROLL_3_MONTH_PEN_AMT, ROLL_3_MONTH_PEN_PCT, REFRESH_DATE, CURR_YEAR_TO_DATE_SLS, YEAR_TO_DATE_SLS_PCT "
			+ " FROM HELIOS_OWN.WIR_SA_CUST_PERF_BY_CAT"
			+ " WHERE PRIMARY_CAT_DESC IN(:allCategories) AND CUSTOMER_NUMBER=:custNum ";

	public static final String SQL_GET_CUST_INSIGHT_DATA = "SELECT A.CUSTOMER_NUMBER, A.PRIMARY_CAT_DESC, A.DECLINE_PCT, A.PRIMARY_PRODUCT_CAT_CD, B.MESSAGE_DESC, A.REFRESH_DATE "
			+ " FROM HELIOS_OWN.WIR_SA_CUST_PERF_BY_CAT A, HELIOS_OWN.WIR_SA_PERF_INSIGHTS B  "
			+ " WHERE A.PRIMARY_PRODUCT_CAT_CD = B.PRIMARY_PRODUCT_CAT_CD  "
			+ " AND B.MESSAGE_ID = 1 AND A.PRIMARY_CAT_DESC IN(:allCategories) AND A.CUSTOMER_NUMBER=:custNum "
			+ " AND A.DECLINE_PCT IS NOT NULL ORDER BY A.DECLINE_PCT DESC ";

	public static final String SQL_GET_WIR_ENABLED_FLAG = "SELECT DISTINCT WIR_ENABLED_FLAG FROM HELIOS_OWN.WIR_SA_CUST_PERF_BY_CAT WHERE CUSTOMER_NUMBER=:custNum ";

	public static final String SQL_GET_HAWKEYE_DETAILS = "SELECT DISTINCT SCP.CUSTOMER_NUMBER,	NVL(HCC.TOTAL_DOLLAR_VALUE,'0')	AS TOTAL_DOLLAR_VALUE , HCC.CHURN_SEGMENT, HCC1.ABANDONED_CATEGORY, HCC2.OFF_CADENCE_CATEGORY, HCC3.HIGH_CHURN_CATEGORY, HCC4.LOW_CALL_COVERAGE, HCC5.DECLINING_CATEGORY, HCC6.HIGH_PROPENSITY_CATEGORY, NVL(HEAMC.TYPICAL_PURCHASE_DAYS,'0') as TYPICAL_PURCHASE_DAYS , NVL(HEAMC.DAYS_SINCE_LAST_PURCHASE,'0') as DAYS_SINCE_LAST_PURCHASE , NVL(HEAMC.TYPICAL_BROWSE_DAYS,'0') as TYPICAL_BROWSE_DAYS , NVL(HEAMC.DAYS_SINCE_LAST_BROWSE,'0') as DAYS_SINCE_LAST_BROWSE , "
			+ " NVL(HEAMC.TYPICAL_SPEND_AMOUNT,'0') AS TYPICAL_SPEND_AMOUNT, NVL(HEAMC.AMOUNT_SPENT,'0') AS AMOUNT_SPENT FROM (SELECT DISTINCT REWARDS_NUMBER AS CUSTOMER_NUMBER  FROM HELIOS_OWN.SAM_CUSTOMER_PROFILE  ) SCP LEFT OUTER JOIN HELIOS_OWN.HE_CUSTOMER_CATEGORY HCC ON SCP.CUSTOMER_NUMBER = HCC.CUSTOMER_NUMBER"
			+ " LEFT OUTER JOIN  (SELECT CUSTOMER_NUMBER,  LISTAGG (CATEGORY, ', ')	WITHIN GROUP ( ORDER BY CATEGORY) AS ABANDONED_CATEGORY  FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY  WHERE STATUS = 1  GROUP BY CUSTOMER_NUMBER ) HCC1 ON HCC1.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER LEFT OUTER JOIN  (SELECT CUSTOMER_NUMBER,  LISTAGG (CATEGORY, ', ')	WITHIN GROUP ("
			+ " ORDER BY CATEGORY) AS OFF_CADENCE_CATEGORY FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY WHERE STATUS = 2 GROUP BY CUSTOMER_NUMBER  ) HCC2 ON HCC2.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER"
			+ " LEFT OUTER JOIN	  (SELECT CUSTOMER_NUMBER,	    LISTAGG (CATEGORY, ', ') WITHIN GROUP (  ORDER BY CATEGORY) AS HIGH_CHURN_CATEGORY  FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY  WHERE STATUS = 3"
			+ " GROUP BY CUSTOMER_NUMBER ) HCC3 ON HCC3.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER	LEFT OUTER JOIN  (SELECT CUSTOMER_NUMBER,  LISTAGG (CATEGORY, ', ')	WITHIN GROUP ( ORDER BY CATEGORY) AS LOW_CALL_COVERAGE  FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY"
			+ " WHERE STATUS = 4 GROUP BY CUSTOMER_NUMBER  ) HCC4	ON HCC4.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER LEFT OUTER JOIN (SELECT CUSTOMER_NUMBER,  LISTAGG (CATEGORY, ', ') WITHIN GROUP ("
			+ " ORDER BY CATEGORY) AS DECLINING_CATEGORY  FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY  WHERE STATUS = 5  GROUP BY CUSTOMER_NUMBER  ) HCC5	ON HCC5.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER LEFT OUTER JOIN"
			+ " (SELECT CUSTOMER_NUMBER,  LISTAGG (CATEGORY, ', ')	WITHIN GROUP ( ORDER BY CATEGORY) AS HIGH_PROPENSITY_CATEGORY FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY  WHERE STATUS = 6  GROUP BY CUSTOMER_NUMBER  ) HCC6"
			+ " ON HCC6.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER	LEFT OUTER JOIN  (SELECT CUSTOMER_NUMBER,  ROUND (TYPICAL_PURCHASE_DAYS)    AS TYPICAL_PURCHASE_DAYS,  ROUND (DAYS_SINCE_LAST_PURCHASE) AS DAYS_SINCE_LAST_PURCHASE,  ROUND (TYPICAL_BROWSE_DAYS)      AS TYPICAL_BROWSE_DAYS,"
			+ "  ROUND (DAYS_SINCE_LAST_BROWSE)   AS DAYS_SINCE_LAST_BROWSE,   ROUND (TYPICAL_SPEND_AMOUNT)     AS TYPICAL_SPEND_AMOUNT,   ROUND (AMOUNT_SPENT)  AS AMOUNT_SPENT FROM HELIOS_OWN.HE_ACCNT_MGR_CUSTOMER  ) HEAMC	ON HEAMC.CUSTOMER_NUMBER  = SCP.CUSTOMER_NUMBER	WHERE SCP.CUSTOMER_NUMBER =?";

	public static final String SQL_GET_HE_CATEGORY_DETAILS = "SELECT HCC.CUSTOMER_NUMBER AS CUSTOMER_NUMBER, HCC.CATEGORY AS CATEGORY,  DECODE (HCC.STATUS,"
			+ " 1, 'Abandoned Cart',   2, 'Off Cadence',  3, 'High Likelihood of Churn', 4, 'Low Call Coverage',  5, 'Declining',  6, 'High Propensity to Purchase') AS STATUS,"
			+ " ROUND(HCC.VALUE_OF_CONTACT) AS VALUE_OF_CONTACT, DECODE (HCC.PURCHASED_CATEGORY,  't', 'Yes',  'f', 'No') AS PURCHASED_CATEGORY "
			+ " FROM HELIOS_OWN.HE_CUSTOMER_CATEGORY HCC WHERE HCC.CUSTOMER_NUMBER = ?";

	public static final String SQL_GET_SUPER_USER_DETAILS_SAM = " SELECT SCP.CUSTOMER_NUMBER, SCP.COMPANY_NAME, SCP.ACCOUNT_ID, SCP.CUST_TIME_ZONE, SCP.MASTER_CONTACT_SF_ID, SCP.FIRST_NAME || ' ' || SCP.LAST_NAME AS CONTACT_FULL_NAME,  NVL (SCP.WORK_PHONE, SCP.ACCOUNT_PHONE) AS PHONE_NUMBER,"
			+ " NVL (SCP.CONTACT_EMAIL_ID, SCP.EMAILADDRESS) AS EMAIL_ADDRESS, HSD.HE_RANK, HSD.SKU, SPM.SKU_NAME, SPM.THUMBNAIL, SPM.PRODUCT_CATEGORY, TO_CHAR (HSD.SKU_LAST_BROWSE_DATE, 'MM/DD/YYYY')  AS SKU_LAST_BROWSE_DATE, HSD.UNIT_PRICE, HSD.CALL_REASON_CATEGORY,"
			+ " HSD.CART_ACTIVITY, HSD.PURCHASE_HISTORY FROM (SELECT TO_NUMBER (REWARDS_NUMBER) AS CUSTOMER_NUMBER, COMPANY_NAME, ACCOUNT_ID, CUST_TIME_ZONE, MASTER_CONTACT_SF_ID, FIRST_NAME,"
			+ " LAST_NAME, WORK_PHONE, ACCOUNT_PHONE, CONTACT_EMAIL_ID, EMAILADDRESS FROM HELIOS_OWN.SAM_CUSTOMER_PROFILE) SCP LEFT OUTER JOIN (SELECT CUSTOMER_NUMBER, HE_RANK,  SKU, SKU_LAST_BROWSE_DATE, UNIT_PRICE, DECODE (CALL_REASON_CATEGORY, 1, 'Abandoned Cart', 2, 'Off Cadence',"
			+ " 3, 'High Likelihood of Churn', 4, 'Low Call Coverage', 5, 'Declining', 6, 'High Propensity to Purchase', CALL_REASON_CATEGORY) AS CALL_REASON_CATEGORY,"
			+ " DECODE (CART_ADD_NO_PURCHASE,'t', 'Abandoned', 'f', 'Viewed',  CART_ADD_NO_PURCHASE) AS CART_ACTIVITY, DECODE (UNPURCHASED_CATEGORY,  't', 'Yes',  'f', 'No') AS PURCHASE_HISTORY"
			+ " FROM HELIOS_OWN.HE_SKU_DETAILS) HSD ON SCP.CUSTOMER_NUMBER = HSD.CUSTOMER_NUMBER LEFT OUTER JOIN (SELECT SKU_NUM, SKU_NAME, THUMBNAIL, DECODE (SPH.PRIMARY_PRODUCT_CAT_DSCR,"
			+ " 'Toner', 'Ink ' || CHR (38) || ' Toner','Tech', 'Technology','', 'All Other Products', SPH.PRIMARY_PRODUCT_CAT_DSCR) AS PRODUCT_CATEGORY FROM (  SELECT SKU_NUM,  MAX (AS400_CLASS_ID) AS CLASS_ID, MAX (SKU_NAME) AS SKU_NAME, MAX (THUMBNAIL) AS THUMBNAIL"
			+ " FROM HELIOS_OWN.SDC_PRODUCT_MASTER GROUP BY SKU_NUM) SKU LEFT OUTER JOIN HELIOS_OWN.SDC_PRODUCT_HIERARCHY SPH ON SKU.CLASS_ID = SPH.CLASS_ID) SPM ON HSD.SKU = SPM.SKU_NUM WHERE SCP.CUSTOMER_NUMBER = ? ORDER BY HSD.HE_RANK NULLS LAST";
	public static final String SQL_ORDER_BY_CDM=" ORDER BY CRP.RANK NULLS LAST ";
	/* CDM Accounts Start */
	
	
	
	/* Mohan Query Started*/
	public static final String SQL_GET_SUPER_USER_DETAILS_CDM=" WITH DT AS (SELECT TM_KY, CLD_DT, FSC_YR,  FSC_PRD,  FSC_WK, FSC_DY, TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day' "
	  +" ), FSC_WK_DAYS_LIST AS (SELECT DT2.CLD_DT AS FSCWK_CLD_DT,  DT2.TM_KY AS FSCWK_TM_KY,  DT2.FSC_YR   AS FSCWK_FSC_YR, DT2.FSC_PRD  AS FSCWK_FSC_PRD, DT2.FSC_WK   AS FSCWK_FSC_WK,    DT2.FSC_DY  AS FSCWK_FSC_DY,  DT1.TM_KY        AS CURR_TM_KY,"
	   +"  DT1.CLD_DT       AS CURR_CLD_DT  FROM DT DT1, DT DT2 WHERE DT1.CLD_DT = TRUNC (SYSDATE)  AND DT1.FSC_WK   = DT2.FSC_WK  AND DT1.FSC_PRD  = DT2.FSC_PRD AND DT1.FSC_YR   = DT2.FSC_YR ),  CURR_WK_PROGRESS AS (SELECT CURR_CLD_DT,   CURR_TM_KY, "
	   +" MAX (FSCWK_TM_KY)  AS MAX_WK_TM_KY,   MIN (FSCWK_TM_KY)  AS MIN_WK_TM_KY,  MAX (FSCWK_CLD_DT) AS MAX_FSCWK_CLD_DT,   MIN (FSCWK_CLD_DT) AS MIN_FSCWK_CLD_DT  FROM FSC_WK_DAYS_LIST  WHERE CURR_CLD_DT = TRUNC (SYSDATE)  GROUP BY CURR_CLD_DT, "
	   +" CURR_TM_KY	  ), CURR_DY_PROGRESS AS(SELECT DISTINCT TM_KY, CLD_DT FROM DT WHERE DT.CLD_DT = TRUNC (SYSDATE) ),  ACCOUNT_OWNER AS (SELECT DISTINCT OWNER_EMP_ID,   OWNER_NAME,  ROLE_CD  FROM HELIOS_OWN.MV_SA_ACCOUNT_MANAGER ),"
	   + " WK_CUST_TASK_LIST AS  (SELECT CUSTOMER_NUMBER, "
	   +" FYR_FPRD_FWK_FDY,   CREATED_BY,  TASK_UPDATE_DATE FROM (SELECT TO_CHAR (TO_NUMBER (SGD.CUSTOMER_NUMBER)) AS CUSTOMER_NUMBER,  SGD.FYR_FPRD_FWK_FDY,   SGD.CREATED_BY,  TRUNC (SGD.TASK_UPDATE_DATE) AS TASK_UPDATE_DATE	    FROM HELIOS_OWN.SA_SEGMENT_DETAIL SGD "
	   +" INNER JOIN CURR_WK_PROGRESS CWP    ON SGD.FYR_FPRD_FWK_FDY  >= CWP.MIN_WK_TM_KY    AND SGD.FYR_FPRD_FWK_FDY <= CWP.MAX_WK_TM_KY   GROUP BY TO_NUMBER (SGD.CUSTOMER_NUMBER),    SGD.FYR_FPRD_FWK_FDY,   SGD.CREATED_BY,   TRUNC (SGD.TASK_UPDATE_DATE)   UNION  SELECT SUME.CUSTOMER_NUMBER   AS CUSTOMER_NUMBER,   TO_CHAR (DT.TM_KY)  AS FYR_FPRD_FWK_FDY, "
	    +"  AO.OWNER_NAME    AS CREATED_BY,   TRUNC (SUME.VISIT_START_TS) AS TASK_UPDATE_DATE  FROM HELIOS_OWN.SA_USER_METRICS SUME  INNER JOIN CURR_WK_PROGRESS CWP   ON TRUNC (SUME.VISIT_START_TS)  >= CWP.MIN_FSCWK_CLD_DT   AND TRUNC (SUME.VISIT_START_TS) <= CWP.MAX_FSCWK_CLD_DT  INNER JOIN DT  ON DT.CLD_DT = TRUNC (SUME.VISIT_START_TS) "
	    +" INNER JOIN ACCOUNT_OWNER AO   ON SUME.REP_ID            = AO.OWNER_EMP_ID   WHERE SUME.PAGE_NUMBER   IN (2015, 2036, 8036)   AND SUME.REP_ID           = SUME.LOGGEDIN_USER  AND SUME.REP_ID          IS NOT NULL   AND SUME.CUSTOMER_NUMBER IS NOT NULL"
	    //Tenfold Change
	    +" UNION SELECT SCP.CUSTOMER_NUMBER  AS CUSTOMER_NUMBER, TO_CHAR (DT.TM_KY)  AS FYR_FPRD_FWK_FDY, AO.OWNER_NAME    AS CREATED_BY, TRUNC(TASK.ACTIVITYDATE) AS TASK_UPDATE_DATE  FROM ( SELECT WHATID,ACTIVITYDATE,OWNERID FROM HELIOS_OWN.CRM_SFDCOWN_STG_TASK TASK"
         +" INNER JOIN CURR_WK_PROGRESS CWP  ON TRUNC(TASK.ACTIVITYDATE) >= TRUNC(CWP.MIN_FSCWK_CLD_DT)  AND TRUNC(TASK.ACTIVITYDATE) <= TRUNC(CWP.MAX_FSCWK_CLD_DT) WHERE TASK.SUBJECT LIKE 'H:%' AND TASK.WHATID IS NOT NULL AND TASK.OWNERID IS NOT NULL"
         +") TASK INNER JOIN HELIOS_OWN.MV_CUSTOMERS SCP ON SCP.IAM_ID = TASK.WHATID INNER JOIN HELIOS_OWN.SA_SFDC_REP_INFO SSPI ON SSPI.REP_SFDC_ID= TASK.OWNERID INNER JOIN DT ON DT.CLD_DT = TRUNC (TASK.ACTIVITYDATE) INNER JOIN ACCOUNT_OWNER AO ON SSPI.REP_EMP_NUM = AO.OWNER_EMP_ID"
	    + "  )	  ), WK_REP_PROGRESS AS  (SELECT DISTINCT AO.OWNER_EMP_ID,   AO.OWNER_NAME,   WCTL.CUSTOMER_NUMBER FROM WK_CUST_TASK_LIST WCTL INNER JOIN ACCOUNT_OWNER AO "
	  +" ON WCTL.CREATED_BY = AO.OWNER_NAME ),	  DLY_CUST_TASK_LIST AS "
	  +" (SELECT DISTINCT CUSTOMER_NUMBER,   FYR_FPRD_FWK_FDY, CREATED_BY, TASK_UPDATE_DATE FROM WK_CUST_TASK_LIST WCTL INNER JOIN CURR_DY_PROGRESS CDY  ON WCTL.FYR_FPRD_FWK_FDY = CDY.TM_KY  ), "
	  +" DLY_REP_PROGRESS AS (SELECT DISTINCT AO.OWNER_EMP_ID,   AO.OWNER_NAME,    DCTL.CUSTOMER_NUMBER  FROM DLY_CUST_TASK_LIST DCTL  INNER JOIN ACCOUNT_OWNER AO  ON DCTL.CREATED_BY = AO.OWNER_NAME  ), "
	  +" ZIP_REF AS  (SELECT DISTINCT  CASE WHEN TIMEZONE = 'EST'    THEN 'EST'    WHEN TIMEZONE = 'CST'   THEN 'CST'     WHEN TIMEZONE = 'MST'     THEN 'MST'    WHEN TIMEZONE = 'PST'    THEN 'PST'     ELSE 'NA'  END TIMEZONE,  LPAD (ZIP, 5, 0) AS ZIP_CODE  FROM HELIOS_OWN.ZIP_CODE_REFERENCE ), LINK_ALRT as (SELECT ALTS.CUSTOMER_NUMBER, ALTS.IS_ALERT_ACTIVE ,ALTS.IS_DELETED,ALTS.VW_AFTER_REFRESH ,ALTS.LINK_ALERTS FROM (select ALT.CUSTOMER_NUMBER, "
     +" ALT.IS_ALERT_ACTIVE ,  ALT.IS_DELETED,  ALT.VW_AFTER_REFRESH ,LINK_ALERTS,ROW_NUMBER() OVER (PARTITION BY ALT.CUSTOMER_NUMBER ORDER BY ALT.VW_AFTER_REFRESH NULLS FIRST) ARNUM from (SELECT ADUM.CUSTOMER_NUMBER,  ADUM.IS_ALERT_ACTIVE ,  ADUM.IS_DELETED,  ADUM.VW_AFTER_REFRESH,  LINK_ALERTS,  MAX(LINK_ALERTS) over (partition by ADUM.CUSTOMER_NUMBER, ADUM.ALERT_ID) MAX_LINK_TASK  FROM HELIOS_OWN.ALERT_DETAIL ADUM  )ALT where ALT.LINK_ALERTS=MAX_LINK_TASK and ALT.IS_ALERT_ACTIVE ='A' and ALT.IS_DELETED ='N') ALTS WHERE ALTS.ARNUM=1) "
	+" SELECT DISTINCT MSCP.CUSTOMER_NUMBER,     MSCP.COMPANY_NAME,    CRP.RANK     AS CALL_ORDER,    ROUND (CRP.POTENTIAL_CALL_VALUE, 2) AS POTENTIAL_CALL_VALUE,   (SELECT LISTAGG (SEG_NAME, ',') WITHIN GROUP (  ORDER BY SEG_NAME)      FROM    (SELECT DISTINCT SEG_NAME,     CUSTOMER_NUMBER  FROM HELIOS_OWN.SA_SEGMENT_HEADER SH    INNER JOIN       (SELECT REGEXP_SUBSTR (PLAY_ID_LIST, '[^,]+', 1, LEVEL) AS P_ID, "
	+" CUSTOMER_NUMBER        FROM   (SELECT SCS.CUSTOMER_NUMBER,   SCS.PLAY_ID_LIST FROM HELIOS_OWN.SA_CUSTOMER_SEGMENT SCS     WHERE SCS.CUSTOMER_NUMBER = MSCP.CUSTOMER_NUMBER )  CONNECT BY REGEXP_SUBSTR (PLAY_ID_LIST, '[^,]+', 1, LEVEL) IS NOT NULL  ) P     ON SH.SEG_ID = P.P_ID  ) GROUP BY CUSTOMER_NUMBER )                                              AS STATUS, "
	+" (select LISTAGG ( P_ID|| '-' ||  SEG_TYPE,',')   WITHIN GROUP (ORDER BY ROWNUM) from(SELECT  SEG_TYPE,P_ID,customer_number FROM HELIOS_OWN.SA_SEGMENT_HEADER SH INNER JOIN (SELECT REGEXP_SUBSTR (PLAY_ID_LIST,'[^,]+',1,LEVEL)AS P_ID,CUSTOMER_NUMBER FROM (SELECT SCS.CUSTOMER_NUMBER, SCS.PLAY_ID_LIST FROM HELIOS_OWN.SA_CUSTOMER_SEGMENT SCS WHERE SCS.CUSTOMER_NUMBER = MSCP.CUSTOMER_NUMBER) CONNECT BY REGEXP_SUBSTR (PLAY_ID_LIST,'[^,]+',1,LEVEL)IS NOT NULL) P ON SH.SEG_ID = P.P_ID ) GROUP BY CUSTOMER_NUMBER )AS CUSTOMER_SEGMENT_ID, "
	   +" DECODE (ZR.TIMEZONE, NULL, 'NA', ZR.TIMEZONE) AS TIMEZONE,  MSCP.CONTRACT_TYPE  AS CUSTOMER_TYPE, SCP.IAM_ID, SCP.DIVISION,    DECODE (DRP.CUSTOMER_NUMBER, NULL, 'FALSE', 'TRUE') AS TODAYS_PROGRESS,   DECODE (WRP.CUSTOMER_NUMBER, NULL, 'FALSE', 'TRUE') AS WEEK_PROGRESS,    MSCP.EMP_NUMBER  AS EMPLOYEE_ID  , SCP.COMPANY_WEBSITE AS COMPANY_WEBSITE, AD.IS_ALERT_ACTIVE  AS ACTIVE,    AD.VW_AFTER_REFRESH  AS VW_AFTER_REFRESH FROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE MSCP "
	   +" INNER JOIN HELIOS_OWN.MV_CUSTOMERS SCP    ON MSCP.CUSTOMER_NUMBER = SCP.CUSTOMER_NUMBER AND MSCP.ACCOUNT_GROUP = SCP.ASSIGN_ACCOUNT_TO LEFT OUTER JOIN HELIOS_OWN.SA_CALL_RANKING_PRIMARY CRP	    ON MSCP.CUSTOMER_NUMBER = CRP.HIERARCHY_ID AND CRP.RANK_ALGORITHM='am1' AND CRP.HIERARCHY_LEVEL='customer_number' LEFT OUTER JOIN ZIP_REF ZR	    ON SCP.COMPANY_ZIP = ZR.ZIP_CODE  LEFT OUTER JOIN WK_REP_PROGRESS WRP	    ON WRP.OWNER_EMP_ID     = MSCP.EMP_NUMBER  AND WRP.CUSTOMER_NUMBER = MSCP.CUSTOMER_NUMBER "
	   +" LEFT OUTER JOIN DLY_REP_PROGRESS DRP  ON DRP.OWNER_EMP_ID      = MSCP.EMP_NUMBER  AND DRP.CUSTOMER_NUMBER  = MSCP.CUSTOMER_NUMBER  LEFT OUTER JOIN LINK_ALRT AD  ON AD.CUSTOMER_NUMBER    =MSCP.CUSTOMER_NUMBER  AND AD.IS_ALERT_ACTIVE   ='A'  AND AD.IS_DELETED        ='N'  WHERE MSCP.ACCOUNT_GROUP = 'MM' AND MSCP.HOLD_OUT = 'N' AND MSCP.EMP_LEVEL = 5 AND MSCP.REP_ROLE_CD     = 'AM1'   AND MSCP.EMP_NUMBER      =:accId ";
	/* Mohan Query Ended*/
	//public static final String SQL_GET_DISTINCT_TIMEZONE_CDM1="SELECT DISTINCT TIMEZONE FROM (SELECT case when TIMEZONE='-4' then 'AST' when TIMEZONE ='-5' then 'EST' when TIMEZONE ='-6'  THEN 'CST' when TIMEZONE='-7' then 'MST' when TIMEZONE='-8' then 'PST'  else 'NA' END as  TIMEZONE FROM HELIOS_OWN.ZIP_CODE_REFERENCE ) ";
	public static final String SQL_GET_DISTINCT_TIMEZONE_CDM1="SELECT TIMEZONE_DESC,  NVL (REGEXP_SUBSTR (TIMEZONE_DESC, '[^ ()]+',  1, 2),'NA') AS TIMEZONE FROM (  SELECT DISTINCT  CASE WHEN TIMEZONE = 'CST' THEN '2-Central (CST)' WHEN TIMEZONE = 'EST' THEN '1-Eastern (EST)' WHEN TIMEZONE = 'MST' THEN '3-Mountain (MST)' WHEN TIMEZONE = 'PST' THEN '4-Pacific (PST)' ELSE '5-NA'  END  AS TIMEZONE_DESC FROM HELIOS_OWN.ZIP_CODE_REFERENCE ORDER BY TIMEZONE_DESC)";
	public static final String GET_ORDER_CONTACT_FOR_LAST_LIVE_CONTACT_CDM1 = "select DISTINCT CONTACT_FIRSTNAME,CONTACT_LASTNAME from HELIOS_OWN.SA_CUSTOMER_SFDC_INFO WHERE CONTACT_ID=:contactId";
	public static final String GET_ACCOUNT_HOLD_OUT_STATUS="SELECT DISTINCT  CASE  WHEN HOLD_OUT='N' THEN 'FALSE' WHEN HOLD_OUT ='Y' THEN 'TRUE' WHEN HOLD_OUT =NULL THEN 'FALSE' END fROM HELIOS_OWN.MV_SA_CUSTOMER_PROFILE WHERE  ( CUSTOMER_NUMBER   = :custId OR UPPER (COMPANY_NAME) = UPPER (:searchText))";
	//public static final String GET_ACCOUNT_HOLD_OUT_STATUS="SELECT CASE WHEN HOLD_OUT='N' THEN 'FALSE' WHEN HOLD_OUT ='Y' THEN 'TRUE' WHEN HOLD_OUT =NULL THEN 'FALSE' END FROM  HELIOS_OWN.MV_SA_CUSTOMER_PROFILE where EMP_NUMBER=:accId AND (  CUSTOMER_NUMBER = :custId OR UPPER (COMPANY_NAME) = UPPER (:searchText))";
	public static final String SQL_GET_CUSTOMER_ORDER_CONTACT_DETAILS_CDM ="select ORDER_CONTACT,ORDER_DATE,TOTAL_SALES_CURR,to_char(trunc(LAST_VISIT_DATE),'mm/dd/yyyy')AS RECENT_VISIT_DATE,EMAIL, PHONE_NUMBER, IAM_ID,CONTACT_ID from (SELECT DISTINCT ORDER_CONTACT,to_char(trunc(ORDER_DATE),'mm/dd/yyyy') as ORDER_DATE, TOTAL_SALES_CURR, LAST_VISIT_DATE, EMAIL, PHONE_NUMBER, IAM_ID,CONTACT_ID FROM (SELECT (CASE WHEN Y.ORDER_CONTACT IS NULL THEN N.ORDER_CONTACT ELSE Y.ORDER_CONTACT END)ORDER_CONTACT,"+
			" ORDER_DATE,Y.TOTAL_SALES_CURR,LAST_VISIT_DATE, (case when INSTR(CP.EMAIL,',',1,1)=0 then CP.EMAIL  else SUBSTR(CP.EMAIL,1,INSTR(CP.EMAIL,',',1,1)-1) end)EMAIL, (case when INSTR(CP.PHONE_NUMBER,'-')=0 and length(CP.phone_number)<=10 then '('||SUBSTR(CP.PHONE_NUMBER,1,3)||')'||' ' "+
			" ||SUBSTR(CP.PHONE_NUMBER,4,3)||'-'||SUBSTR(CP.PHONE_NUMBER,7,4) when INSTR(CP.PHONE_NUMBER,'-')=0 and length(CP.phone_number)>10 then '('||SUBSTR(CP.PHONE_NUMBER,1,3)||')'||' '"+
			" ||SUBSTR(CP.PHONE_NUMBER,4,3)||'-'||SUBSTR(CP.PHONE_NUMBER,7,4)||' x'||SUBSTR(CP.PHONE_NUMBER,11,length(CP.PHONE_NUMBER)-10) else PHONE_NUMBER end)PHONE_NUMBER, NVL(CP.IAM_ID, '-') as IAM_ID,SCSI.CONTACT_ID "+
			 " FROM (  SELECT  CUSTOMER_NUMBER,ORDER_CONTACT,ORDER_DATE,TOTAL_SALES_CURR  FROM(  SELECT CUSTOMER_NUMBER, ORDER_CONTACT, MAX(ORDER_DATE) AS ORDER_DATE, SUM(ORDER_AMOUNT) AS TOTAL_SALES_CURR FROM (  SELECT CUSTOMER_NUMBER,ORDERED_BY AS ORDER_CONTACT, ORDER_NUMBER,ORDER_DATE ,nvl(ORDER_AMOUNT,0) as ORDER_AMOUNT" +
			 " FROM HELIOS_OWN.MV_SA_ORDER_HEADER  WHERE CUSTOMER_NUMBER = :custNum and ORDER_DATE >= add_months(sysdate,-12) and  ORDER_TYPE NOT IN ('RE', 'QU', 'MO', 'AJ')"+
				" GROUP BY CUSTOMER_NUMBER,ORDER_DATE,ORDER_NUMBER, ORDERED_BY,ORDER_AMOUNT  ORDER BY CUSTOMER_NUMBER,ORDER_DATE DESC) GROUP BY CUSTOMER_NUMBER,ORDER_CONTACT) X ORDER BY ROWNUM) Y  FULL OUTER JOIN "+
			" (select distinct customer_number,order_contact,max(last_visit_date) as last_visit_date  from HELIOS_OWN.SA_WEB_ACTIVITY WHERE CUSTOMER_NUMBER = :custNum AND LAST_VISIT_DATE IS NOT NULL group by customer_number, order_contact) N "+
			 " ON     Y.CUSTOMER_NUMBER = N.CUSTOMER_NUMBER  AND Y.ORDER_CONTACT = N.ORDER_CONTACT   LEFT OUTER JOIN (select distinct customer_number,First_name,last_name,lower(email) as email,phone_number, IAM_ID from  HELIOS_OWN.SA_CUSTOMER_PROFILE) CP"+
			" ON Y.CUSTOMER_NUMBER = CP.CUSTOMER_NUMBER AND Y.ORDER_CONTACT = UPPER(CP.FIRST_NAME || ' ' || CP.LAST_NAME) LEFT OUTER JOIN HELIOS_OWN.SA_CUSTOMER_SFDC_INFO SCSI ON Y.CUSTOMER_NUMBER = SCSI.MASTER_CUSTOMER_NUMBER AND Y.ORDER_CONTACT = UPPER(SCSI.CONTACT_FIRSTNAME || ' ' || SCSI.CONTACT_LASTNAME)) ";
	
	public static final String SQL_ORDER_CONTACT_DETAILS_ORDERBY_VISIT_DATE= "where LAST_VISIT_DATE >=add_months(sysdate,-12) ORDER BY  LAST_VISIT_DATE DESC nulls last ) where ROWNUM <=3";
	public static final String SQL_ORDER_CONTACT_DETAILS_ORDERBY_ORDER_DATE= "ORDER BY  ORDER_DATE DESC nulls last ) where ROWNUM <=3";
	public static final String SQL_ORDER_CONTACT_DETAILS_ORDERBY_TOTAL_SALES="ORDER BY  TOTAL_SALES_CURR  DESC nulls last ) where ROWNUM <=3";
	
	
	public static final String ACCOUNT_WEEK_PROGRESS_AM1="WITH DT AS (SELECT TM_KY, CLD_DT,FSC_YR,FSC_PRD,FSC_WK, FSC_DY, TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day'),FSC_WK_DAYS_LIST AS (SELECT DT2.CLD_DT AS FSCWK_CLD_DT, DT2.TM_KY AS FSCWK_TM_KY,DT2.FSC_YR AS FSCWK_FSC_YR, DT2.FSC_PRD AS FSCWK_FSC_PRD,DT2.FSC_WK AS FSCWK_FSC_WK, "
			+" DT2.FSC_DY AS FSCWK_FSC_DY,DT1.TM_KY AS CURR_TM_KY,DT1.CLD_DT AS CURR_CLD_DT FROM DT DT1, DT DT2 WHERE     DT1.CLD_DT = TRUNC (SYSDATE) AND DT1.FSC_WK = DT2.FSC_WK AND DT1.FSC_PRD = DT2.FSC_PRD AND DT1.FSC_YR = DT2.FSC_YR),CURR_WK_PROGRESS "
			+" AS (  SELECT CURR_CLD_DT, CURR_TM_KY,   MAX (FSCWK_TM_KY) AS MAX_WK_TM_KY, MIN (FSCWK_TM_KY) AS MIN_WK_TM_KY, MAX (FSCWK_CLD_DT) AS MAX_FSCWK_CLD_DT,MIN (FSCWK_CLD_DT) AS MIN_FSCWK_CLD_DT   FROM FSC_WK_DAYS_LIST WHERE CURR_CLD_DT = TRUNC (SYSDATE) "
			+"GROUP BY CURR_CLD_DT, CURR_TM_KY),CURR_DY_PROGRESS AS (SELECT DISTINCT CURR_TM_KY FROM FSC_WK_DAYS_LIST),ACCOUNT_OWNER AS (SELECT DISTINCT OWNER_EMP_ID, OWNER_NAME, ROLE_CD FROM HELIOS_OWN.MV_SA_ACCOUNT_MANAGER),"
			+ "WK_CUST_TASK_LIST AS (SELECT CUSTOMER_NUMBER,FYR_FPRD_FWK_FDY,"
			+" CREATED_BY,TASK_UPDATE_DATE FROM (  SELECT TO_CHAR (TO_NUMBER (SGD.CUSTOMER_NUMBER))   AS CUSTOMER_NUMBER,SGD.FYR_FPRD_FWK_FDY, SGD.CREATED_BY,TRUNC (SGD.TASK_UPDATE_DATE) AS TASK_UPDATE_DATE FROM HELIOS_OWN.SA_SEGMENT_DETAIL SGD INNER JOIN CURR_WK_PROGRESS CWP"
			+" ON  SGD.FYR_FPRD_FWK_FDY >= CWP.MIN_WK_TM_KY AND SGD.FYR_FPRD_FWK_FDY <= CWP.MAX_WK_TM_KY GROUP BY TO_NUMBER (SGD.CUSTOMER_NUMBER),SGD.FYR_FPRD_FWK_FDY,SGD.CREATED_BY,TRUNC (SGD.TASK_UPDATE_DATE) UNION SELECT SUME.CUSTOMER_NUMBER AS CUSTOMER_NUMBER,TO_CHAR (DT.TM_KY) AS FYR_FPRD_FWK_FDY,"
			+" AO.OWNER_NAME AS CREATED_BY,TRUNC (SUME.VISIT_START_TS) AS TASK_UPDATE_DATE FROM HELIOS_OWN.SA_USER_METRICS SUME INNER JOIN CURR_WK_PROGRESS CWP ON     TRUNC (SUME.VISIT_START_TS) >=CWP.MIN_FSCWK_CLD_DT AND TRUNC (SUME.VISIT_START_TS) <= CWP.MAX_FSCWK_CLD_DT INNER JOIN DT ON DT.CLD_DT = TRUNC (SUME.VISIT_START_TS) INNER JOIN ACCOUNT_OWNER AO "
			+" ON SUME.REP_ID = AO.OWNER_EMP_ID  WHERE     SUME.PAGE_NUMBER IN (2015, 2036, 8036) AND SUME.REP_ID = SUME.LOGGEDIN_USER AND SUME.REP_ID IS NOT NULL AND SUME.CUSTOMER_NUMBER IS NOT NULL"
			//Tenfold Change
			+" UNION SELECT SCP.CUSTOMER_NUMBER  AS CUSTOMER_NUMBER, TO_CHAR (DT.TM_KY)  AS FYR_FPRD_FWK_FDY, AO.OWNER_NAME    AS CREATED_BY, TRUNC(TASK.ACTIVITYDATE) AS TASK_UPDATE_DATE  FROM ( SELECT WHATID,ACTIVITYDATE,OWNERID FROM HELIOS_OWN.CRM_SFDCOWN_STG_TASK TASK"
			+" INNER JOIN CURR_WK_PROGRESS CWP  ON TRUNC(TASK.ACTIVITYDATE) >= TRUNC(CWP.MIN_FSCWK_CLD_DT)  AND TRUNC(TASK.ACTIVITYDATE) <= TRUNC(CWP.MAX_FSCWK_CLD_DT) WHERE TASK.SUBJECT LIKE 'H:%' AND TASK.WHATID IS NOT NULL AND TASK.OWNERID IS NOT NULL ) TASK"
			+" INNER JOIN  HELIOS_OWN.SA_CUSTOMER_PROFILE SCP ON SCP.IAM_ID = TASK.WHATID INNER JOIN HELIOS_OWN.SA_SFDC_REP_INFO SSPI ON SSPI.REP_SFDC_ID= TASK.OWNERID INNER JOIN DT ON DT.CLD_DT = TRUNC (TASK.ACTIVITYDATE) INNER JOIN  ACCOUNT_OWNER AO ON SSPI.REP_EMP_NUM = AO.OWNER_EMP_ID" 
			+ ")),WK_REP_PROGRESS AS (  SELECT OWNER_EMP_ID,OWNER_NAME,DECODE (FSC_DY,1, '2',2, '2', 3, '3', 4, '4', 5, '5',6, '6',7, '6')AS WK_DY, SUM (WK_PROGRESS_COUNT) AS WK_PROGRESS_COUNT FROM (  SELECT AO.OWNER_EMP_ID, AO.OWNER_NAME,"
			+" DT.FSC_DY,COUNT (FYR_FPRD_FWK_FDY) AS WK_PROGRESS_COUNT FROM WK_CUST_TASK_LIST INNER JOIN ACCOUNT_OWNER AO ON CREATED_BY = AO.OWNER_NAME INNER JOIN DT ON DT.CLD_DT = TASK_UPDATE_DATE GROUP BY AO.OWNER_EMP_ID, AO.OWNER_NAME, DT.FSC_DY) GROUP BY OWNER_EMP_ID, OWNER_NAME, DECODE (FSC_DY, 1, '2',2, '2',3, '3',4, '4',5, '5',6, '6',7, '6')) SELECT DECODE (FWDL.FSCWK_FSC_DY, 2, 'Mon', 3, 'Tue', 4, 'Wed', 5, 'Thurs', 6, 'Fri')  AS WK_DY,"
            +"  NVL (SUM(WRP.WK_PROGRESS_COUNT), 0) AS WK_PROGRESS_COUNT FROM (  SELECT FSCWK_FSC_DY  FROM FSC_WK_DAYS_LIST  WHERE FSCWK_FSC_DY NOT IN (1, 7) ORDER BY FSCWK_FSC_DY) FWDL  LEFT OUTER JOIN WK_REP_PROGRESS WRP  ON FWDL.FSCWK_FSC_DY = WRP.WK_DY AND WRP.OWNER_EMP_ID =:repNum GROUP BY DECODE (FWDL.FSCWK_FSC_DY, 2, 'Mon',  3, 'Tue', 4, 'Wed', 5, 'Thurs', 6, 'Fri')";
	
	
	public static final String ACCOUNT_TODAYS_PROGRESS_AM1="WITH DT AS (SELECT TM_KY,CLD_DT,FSC_YR,FSC_PRD,FSC_WK,FSC_DY,TM_LVL FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day'),CURR_DY_PROGRESS AS (SELECT DISTINCT TM_KY, CLD_DT "
      +" FROM DT WHERE DT.CLD_DT = TRUNC (SYSDATE)),ACCOUNT_OWNER AS (SELECT DISTINCT OWNER_EMP_ID, OWNER_NAME, ROLE_CD FROM HELIOS_OWN.MV_SA_ACCOUNT_MANAGER),"
      + "  DLY_CUST_TASK_LIST AS (SELECT DISTINCT CUSTOMER_NUMBER,FYR_FPRD_FWK_FDY, "
       +" CREATED_BY,TASK_UPDATE_DATE FROM (  SELECT TO_CHAR (TO_NUMBER (SGD.CUSTOMER_NUMBER)) AS CUSTOMER_NUMBER,SGD.FYR_FPRD_FWK_FDY,SGD.CREATED_BY,TRUNC (SGD.TASK_UPDATE_DATE) AS TASK_UPDATE_DATE FROM HELIOS_OWN.SA_SEGMENT_DETAIL SGD "
       +" INNER JOIN CURR_DY_PROGRESS CDP ON SGD.FYR_FPRD_FWK_FDY = CDP.TM_KY GROUP BY TO_NUMBER (SGD.CUSTOMER_NUMBER),SGD.FYR_FPRD_FWK_FDY, SGD.CREATED_BY,TRUNC (SGD.TASK_UPDATE_DATE) UNION "
        +" SELECT SUME.CUSTOMER_NUMBER AS CUSTOMER_NUMBER,TO_CHAR (CDP.TM_KY) AS FYR_FPRD_FWK_FDY,AO.OWNER_NAME AS CREATED_BY,TRUNC (SUME.VISIT_START_TS) AS TASK_UPDATE_DATE FROM HELIOS_OWN.SA_USER_METRICS SUME "
         +" INNER JOIN CURR_DY_PROGRESS CDP ON TRUNC (SUME.VISIT_START_TS) = CDP.CLD_DT INNER JOIN ACCOUNT_OWNER AO ON SUME.REP_ID = AO.OWNER_EMP_ID  WHERE     SUME.PAGE_NUMBER IN (2015, 2036, 8036) AND SUME.REP_ID = SUME.LOGGEDIN_USER AND SUME.REP_ID IS NOT NULL "
        +" AND SUME.CUSTOMER_NUMBER IS NOT NULL"
        //Tenfold Change 
        +" UNION SELECT SCP.CUSTOMER_NUMBER  AS CUSTOMER_NUMBER, TO_CHAR (DT.TM_KY)  AS FYR_FPRD_FWK_FDY, AO.OWNER_NAME  AS CREATED_BY, TRUNC(TASK.ACTIVITYDATE) AS TASK_UPDATE_DATE  FROM ( SELECT WHATID,ACTIVITYDATE,OWNERID FROM HELIOS_OWN.CRM_SFDCOWN_STG_TASK TASK"
        +" INNER JOIN CURR_DY_PROGRESS CDP ON     TRUNC(TASK.ACTIVITYDATE)= CDP.CLD_DT WHERE TASK.SUBJECT LIKE 'H:%' AND TASK.WHATID IS NOT NULL AND TASK.OWNERID IS NOT NULL ) TASK INNER JOIN  HELIOS_OWN.SA_CUSTOMER_PROFILE SCP ON SCP.IAM_ID = TASK.WHATID INNER JOIN HELIOS_OWN.SA_SFDC_REP_INFO SSPI ON SSPI.REP_SFDC_ID= TASK.OWNERID"
        +" INNER JOIN DT ON DT.CLD_DT = TRUNC (TASK.ACTIVITYDATE) INNER JOIN ACCOUNT_OWNER AO ON SSPI.REP_EMP_NUM          = AO.OWNER_EMP_ID"   
        + ")), DLY_REP_PROGRESS AS (  SELECT OWNER_EMP_ID, OWNER_NAME,SUM (DLY_PROGRESS_COUNT) AS DLY_PROGRESS_COUNT FROM (  SELECT AO.OWNER_EMP_ID, "
         + " AO.OWNER_NAME, COUNT (FYR_FPRD_FWK_FDY) AS DLY_PROGRESS_COUNT FROM DLY_CUST_TASK_LIST INNER JOIN ACCOUNT_OWNER AO ON CREATED_BY = AO.OWNER_NAME  INNER JOIN DT ON DT.CLD_DT = TASK_UPDATE_DATE "
         +" GROUP BY AO.OWNER_EMP_ID, AO.OWNER_NAME) GROUP BY OWNER_EMP_ID, OWNER_NAME) SELECT  SUM (DLY_PROGRESS_COUNT) AS ACCOUNT_ACTIONED FROM DLY_REP_PROGRESS  WHERE OWNER_EMP_ID= :repNum GROUP BY OWNER_EMP_ID, OWNER_NAME "; 

 public static final String GET_SEGMENT_ID_DETAILS="select  SEG_ID,SEG_TYPE,SEG_NAME,SEG_FREQ,SEG_DESC from helios_own.SA_SEGMENT_HEADER WHERE SEG_ID=:segId";
	
	/* CDM Account Close */

 /* SPARX RECOMMENDATION START*/
 
 public static final String GET_CUST_USER_EMAIL = "WITH CUST_DETAIL AS (  SELECT DISTINCT(ORDEREREMAILDADDRESS) AS EMAIL_ID, MASTERNUMBER AS CUST_NUM, TRUNC (ORDERTRANDATE) AS ORDER_DATE, ORDERERLASTNAME AS CUST_LAST_NAME, ORDERERFIRSTNAME AS CUST_FIRST_NAME"
		+ " FROM CEX01_OWN.SALESTRAN WHERE SOURCESYSTEMID = '103' AND TRUNC (ORDERTRANDATE) BETWEEN TO_DATE ( :startDate,'MM/DD/YYYY') AND TO_DATE ( :endDate,'MM/DD/YYYY')"
		+ " AND UPPER (TRANTYPE) NOT IN ('RE', 'QU', 'MO') AND MASTERNUMBER = :custNum AND ORDEREREMAILDADDRESS IS NOT NULL ORDER BY TRUNC (ORDERTRANDATE) DESC)"
		+ " SELECT DISTINCT SCP.EMAIL AS EMAIL,SCP.FIRST_NAME || ' ' || SCP.LAST_NAME AS CUST_NAME,"
		+ " SCP.CUSTOMER_NUMBER AS CUST_NUM ,SCP.ACCNT_MGR_EMP_NUMBER AS REP_ID FROM HELIOS_OWN.SA_CUSTOMER_PROFILE SCP"
		+ " INNER JOIN (SELECT DISTINCT EMAIL_ID, CUST_NUM,CUST_LAST_NAME,CUST_FIRST_NAME FROM CUST_DETAIL WHERE ROWNUM <= 15) CD ON UPPER (CD.EMAIL_ID) = UPPER (SCP.EMAIL) AND CD.CUST_NUM = SCP.CUSTOMER_NUMBER AND UPPER(CD.CUST_FIRST_NAME)=UPPER(SCP.FIRST_NAME) AND UPPER(CD.CUST_LAST_NAME) = UPPER(SCP.LAST_NAME) WHERE SCP.ASSIGN_ACCOUNT_TO = 'MM' AND SCP.EMAIL IS NOT NULL"; 
 
 public static final String GET_SKU_INFO = "select  SKU_NUM as SKU,SKU_NAME,THUMBNAIL,CATENTRY_ID from HELIOS_OWN.SA_PRODUCT_MASTER "
 		        +" where SKU_NUM =:skuNum";
 
 public static final String LOG_RECOMM_SKU_SBA = "insert into helios_OWN.SPARX_RECOM_LOG(REQ_INPUT,INPUT_TYPE,SCHEME_QUERIED,COUNT_REQUESTED,RECOMMENDED_SKUS,STRATEGY,SPARX_FILE,REP_ID,CUSTOMER_NUMBER,LOGGED_USER,ROW_INSERT_TM,ADDITIONAL_INPUT_ATTR) values(?,?,?,?,?,?,?,?,?,?,systimestamp,?)";

 public static final String LOG_PRICE = "insert into helios_OWN.SAM_PRICE_LOG(ARKE_CIS_REQ,ARKE_CIS_RESP,PRICE_REQ,PRICE_RESP,CUSTOMER_NUMBER,LOGGED_USER,ROW_INSERT_TM) values(?,?,?,?,?,?,systimestamp)";
 
 public static final String GET_CUSTOMER_BOUGHT_SKUS="SELECT TRIM(STD.PRODUCTSKU) AS SKU,ST.ORDERTRANDATE AS ORDERDATE,SCP.ACCNT_MGR_EMP_NUMBER AS REP_ID FROM CEX01_OWN.SALESTRANDETAIL STD INNER JOIN CEX01_OWN.SALESTRAN ST ON STD.SALESTRANID=ST.SALESTRANID INNER JOIN HELIOS_OWN.SA_CUSTOMER_PROFILE SCP ON SCP.CUSTOMER_NUMBER=ST.MASTERNUMBER AND UPPER(ST.ORDERERFIRSTNAME)=UPPER(SCP.FIRST_NAME) AND UPPER(ST.ORDERERLASTNAME)=UPPER(SCP.LAST_NAME)  WHERE ST.SOURCESYSTEMID = '103' AND TRUNC (ST.ORDERTRANDATE) BETWEEN TO_DATE ( :startDate ,'MM/DD/YYYY') AND TO_DATE ( :endDate ,'MM/DD/YYYY')"
	 		+ " AND UPPER (ST.TRANTYPE) NOT IN ('RE', 'QU', 'MO')  AND ST.MASTERNUMBER = :custNum  AND UPPER(ST.ORDERERFIRSTNAME||' '|| ST.ORDERERLASTNAME)=UPPER(:orderContact) AND ROWNUM <=15 ORDER BY TRUNC (ST.ORDERTRANDATE) DESC";
 
 /* SPARX RECOMMENDATION END*/
 
/* public static final String GET_LAST_LIVE_CONTACT_DETAILS_CDM1 = "WITH LAST_ACTIVITY_HISTORY AS (SELECT MAX(ID),ActivityDate,Status ,Subject ,WhoId,REP_NAME,Description FROM "
         +" ((SELECT ID,ActivityDate,Status ,Subject ,WhoId,REP_NAME,Description FROM helios_own.STG_TASK TASK left outer join helios_own.SA_SFDC_REP_INFO  REPINFO  ON TASK.ownerId =REPINFO.rep_sfdc_id  where whatid=:iamId) "
         +" UNION  (select SSD.TASK_ID_COMBINATION,TASK_INSERT_DATE, SEG_DISP_STATUS ,TASK_SUBJECT,CONTACT_ID,CREATED_BY AS rep_name,COMMENT_TEXT   FROM helios_own.sa_segment_detail SSD LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_COMMENTS SSC ON SSD.TASK_ID_COMBINATION=SSC.TASK_ID_COMBINATION AND SSD.LINK_TASKS=SSC.LINK_TASKS "
         +" where customer_number=LPAD(:custNum,10,0)) )GROUP BY ActivityDate,Status ,Subject ,WhoId,rep_name,Description order by ActivityDate DESC NULLS LAST) "
         +" select to_char(to_date(to_char(TRUNC(ActivityDate)),'dd-mon-yy'),'mm/dd/yyyy') ActivityDate,Status ,Subject ,WhoId,Description,(FIRSTNAME||' '||LASTNAME)AS CONTACT_NAME,OWNER_NAME from ((SELECT ActivityDate,Status ,Subject ,WhoId,Description,OWNER_NAME FROM (select  ActivityDate,Status ,Subject ,WhoId,Description,REP_NAME AS OWNER_NAME from  LAST_ACTIVITY_HISTORY where STATUS='Task Completed - Live Contact' order by ActivityDate DESC nulls last) WHERE ROWNUM=1 "
         +" ) union(select ActivityDate,Status ,Subject ,WhoId,Description,OWNER_NAME from (Select ActivityDate,Status ,Subject ,WhoId,Description,REP_NAME AS OWNER_NAME from LAST_ACTIVITY_HISTORY " //where status like '%Completed%'
         +" order by ActivityDate nulls last) where rownum <=4 ))CONTACT_HISTORY LEFT OUTER JOIN  CEX01_OWN.CRM_SFDCOWN_STG_CONTACTS CONTACTS  ON  CONTACTS.ID=CONTACT_HISTORY.WhoId order by ActivityDate DESC NULLS LAST";
*/
 
 public static final String GET_LAST_LIVE_CONTACT_DETAILS_CDM1 ="WITH LAST_ACTIVITY_HISTORY AS (SELECT * FROM (SELECT ID, ActivityDate,Status ,Subject ,WhoId,REP_NAME,Description, ROW_NUMBER() OVER (PARTITION BY id ORDER BY rep_name NULLS LAST) AS r FROM "
         +" ((SELECT ID, 'NA' AS task_id_combination, TRUNC(ActivityDate) AS ActivityDate , Status , TRIM(Subject) AS Subject ,WhoId,REP_NAME, TRIM(Description) AS Description FROM HELIOS_OWN.CRM_SFDCOWN_STG_TASK TASK left outer join helios_own.SA_SFDC_REP_INFO  REPINFO  ON TASK.ownerId =REPINFO.rep_sfdc_id  where accountid=:iamId) "
         +" UNION  (select SSD.sfdc_task_id, SSD.TASK_ID_COMBINATION, TRUNC(TASK_INSERT_DATE) AS ActivityDate , SEG_DISP_STATUS , TRIM(TASK_SUBJECT) AS Subject ,CONTACT_ID,CREATED_BY AS rep_name, TRIM(COMMENT_TEXT) AS Description   FROM helios_own.sa_segment_detail SSD LEFT OUTER JOIN HELIOS_OWN.SA_SEGMENT_COMMENTS SSC ON SSD.TASK_ID_COMBINATION=SSC.TASK_ID_COMBINATION AND SSD.LINK_TASKS=SSC.LINK_TASKS"
         +" where customer_number=LPAD(:custNum,10,0)) )) a where r = 1) "
         +" select to_char(to_date(to_char(TRUNC(ActivityDate)),'dd-mon-yy'),'mm/dd/yyyy') ActivityDate,Status ,Subject ,WhoId,Description,(FIRSTNAME||' '||LASTNAME)AS CONTACT_NAME,OWNER_NAME from ((SELECT ActivityDate,Status ,Subject ,WhoId,Description,OWNER_NAME FROM (select  ActivityDate,Status ,Subject ,WhoId,Description,REP_NAME AS OWNER_NAME from  LAST_ACTIVITY_HISTORY where STATUS='Task Completed - Live Contact' order by ActivityDate DESC nulls last) WHERE ROWNUM=1"
         +" ) union(select ActivityDate,Status ,Subject ,WhoId,Description,OWNER_NAME from (Select ActivityDate,Status ,Subject ,WhoId,Description,REP_NAME AS OWNER_NAME from LAST_ACTIVITY_HISTORY "
         +" order by ActivityDate DESC nulls last) where rownum <=4 ))CONTACT_HISTORY LEFT OUTER JOIN  CEX01_OWN.CRM_SFDCOWN_STG_CONTACTS CONTACTS  ON  CONTACTS.ID=CONTACT_HISTORY.WhoId order by ActivityDate DESC NULLS LAST";
 

public static final String SQL_UPDATE_ALERT_VIEW_TAB_STATUS = "UPDATE HELIOS_OWN.ALERT_DETAIL SET ALERT_VIEW_TAB_DATE  =trunc(SYSDATE) , VW_AFTER_REFRESH='Y' , UPDATED_BY=? WHERE CUSTOMER_NUMBER    =? and VW_AFTER_REFRESH IS NULL";

public static final String SELECT_ALERT_DTL="select AD.ALERT_ID_COMBINATION , AD.ALERT_ID,  AD.CUSTOMER_NUMBER,  AD.DIVISION,  AD.IS_ALERT_ACTIVE,  AD.IS_DELETED,  AD.IS_READ,  AD.LINK_ALERTS,  AD.ALERT_TYPE,  TO_CHAR(AD.ALERT_ACTIVE_DATE,'MM/DD/YYYY') as ALERT_ACTIVE_DATE ,  AD.ICON_IMG_PATH,  AD.ICON_IMG_NAME,  AD.ALERT_SUBJECT,  AD.ALERT_SHORT_DESCRIPTION,  AD.ALERT_TXT, ASD.PARAM_ID, ASD.PARAM_LABEL, ASD.PARAM_VALUE, "
+" MAX_LINK_ALERT from(SELECT alrt.ALERT_ID_COMBINATION,alrt.ALERT_ID,alrt.CUSTOMER_NUMBER,  alrt.DIVISION,  alrt.IS_ALERT_ACTIVE,  alrt.IS_DELETED,  alrt.IS_READ,  alrt.LINK_ALERTS,  alrt.ALERT_TYPE, alrt.ALERT_ACTIVE_DATE, alrt.ICON_IMG_PATH, alrt.ICON_IMG_NAME, alrt.ALERT_SUBJECT, alrt.ALERT_SHORT_DESCRIPTION, alrt.ALERT_TXT,  MAX_LINK_ALERT "
+" FROM  (SELECT aa.ALERT_ID_COMBINATION,  aa.ALERT_ID,  aa.CUSTOMER_NUMBER, aa.DIVISION, aa.IS_ALERT_ACTIVE, aa.IS_DELETED, aa.IS_READ, aa.LINK_ALERTS, aa.ALERT_TYPE, aa.ALERT_ACTIVE_DATE, aa.ICON_IMG_PATH, aa.ICON_IMG_NAME, aa.ALERT_SUBJECT, aa.ALERT_SHORT_DESCRIPTION, aa.ALERT_TXT, "
+" MAX(aa.LINK_ALERTS) over (partition BY aa.ALERT_ID) AS MAX_LINK_ALERT FROM  (SELECT DISTINCT ad.ALERT_ID, ad.ALERT_ID_COMBINATION,  ad.CUSTOMER_NUMBER, ad.DIVISION, ad.IS_ALERT_ACTIVE, ad.IS_DELETED,ad.IS_READ, ad.LINK_ALERTS, ad.ALERT_ACTIVE_DATE,  ALRT_HDR.ALERT_TYPE,  ALRT_HDR.ICON_IMG_PATH, ALRT_HDR.ICON_IMG_NAME, ALRT_HDR.ALERT_SUBJECT, ALRT_HDR.ALERT_SHORT_DESCRIPTION, ALRT_HDR.ALERT_TXT "
+" FROM HELIOS_OWN.ALERT_DETAIL ad INNER JOIN   (SELECT AH.ALERT_ID,AH.ALERT_TYPE,AH.ALERT_START_DATE, AH.ICON_IMG_PATH, AH.ICON_IMG_NAME,AH.ALERT_SUBJECT,AH.ALERT_SHORT_DESCRIPTION,to_char(AH.ALERT_TXT) as ALERT_TXT "
+" FROM HELIOS_OWN.ALERT_HEADER AH   WHERE ALERT_ID IN (SELECT regexp_substr( (SELECT ALERT_SEGMENT.ALERT_IDS  FROM HELIOS_OWN.ALERT_SEGMENT WHERE ALERT_SEGMENT.CUSTOMER_NUMBER=:custNum ),'[^,]+', 1, level) "
+" FROM dual CONNECT BY regexp_substr((SELECT ALERT_SEGMENT.ALERT_IDS FROM HELIOS_OWN.ALERT_SEGMENT  WHERE ALERT_SEGMENT.CUSTOMER_NUMBER=:custNum ), '[^,]+', 1, level)             IS NOT NULL   ) "
+" AND IS_ALERT_ACTIVE='A')ALRT_HDR  ON ALRT_HDR.ALERT_ID =ad.ALERT_ID   WHERE ad.CUSTOMER_NUMBER=:custNum  )aa )alrt WHERE LINK_ALERTS= MAX_LINK_ALERT AND IS_ALERT_ACTIVE='A' AND IS_DELETED='N' ) AD left outer join HELIOS_OWN.ALERT_SUB_DETAIL ASD ON AD.ALERT_ID_COMBINATION=ASD.ALERT_ID_COMBINATION AND AD.ALERT_ID=ASD.ALERT_ID AND AD.LINK_ALERTS = ASD.LINK_ALERTS order by AD.IS_READ";

public static final String SQL_SOFT_DELETE_ALERT ="UPDATE HELIOS_OWN.ALERT_DETAIL SET IS_DELETED  ='Y' , DELETED_BY=? , DELETED_ON=SYSDATE WHERE ALERT_ID_COMBINATION=? AND ALERT_ID =? AND LINK_ALERTS = "
+" (SELECT MAX(LINK_ALERTS)  FROM HELIOS_OWN.ALERT_DETAIL  WHERE ALERT_ID_COMBINATION=?  AND ALERT_ID =?  )";

public static final String SQL_UPDATE_READ_ALERT ="UPDATE HELIOS_OWN.ALERT_DETAIL SET IS_READ  ='Y' , CLICKED_BY=? , CLICKED_ON=SYSDATE WHERE ALERT_ID_COMBINATION=? AND ALERT_ID =? AND LINK_ALERTS = "
+" (SELECT MAX(LINK_ALERTS)  FROM HELIOS_OWN.ALERT_DETAIL  WHERE ALERT_ID_COMBINATION=?  AND ALERT_ID =?  )";

public static final String SQL_GET_LATEST_REFRESH_TIMESTAMP="SELECT TO_CHAR(VISIT_START_TS, 'DD-MON-YY HH:MI:SS AM') FROM (select VISIT_START_TS from HELIOS_OWN.SA_USER_METRICS WHERE REP_ID=:repId and LOGGEDIN_USER =:logUser AND  PAGE_NUMBER=:pageNum and REP_ROLE_CD=:repCd order by VISIT_START_TS desc)UM WHERE ROWNUM=1";

/*public static final String SQL_GET_CURRENT_YTD_INFO_CUSTEXO =  " select YEAR,YTD_SALES from (SELECT fiscal_year AS YEAR, TOTAL_SALES       AS YTD_SALES FROM helios_own.MV_SALES_SUMMARY " 
			 +" WHERE Customer_Number = ? and fiscal_year = (select to_char(sysdate, 'YYYY') from dual)) union select to_char(sysdate, 'YYYY')  YEAR,0  YTD_SALES from dual WHERE NOT EXISTS (SELECT 1 FROM helios_own.MV_SALES_SUMMARY" 
			 +" WHERE Customer_Number = ? and fiscal_year = (select to_char(sysdate, 'YYYY') from dual))	order by year desc";
*/
public static final String SQL_GET_CURRENT_YTD_INFO_CUSTEXO = "  WITH SS AS ( SELECT FSC_YR AS FISCAL_YEAR, SUM(CASE WHEN HEAD.TRANAMOUNT = 0 THEN HEAD.TRANAMOUNT ELSE HEAD.TRANAMOUNT - NVL (STC.TRANCHARGEAMOUNT, 0) END) AS TOTAL_SALES FROM (SELECT SALESTRANID, ORDERTRANDATE, TRANAMOUNT  FROM HELIOS_OWN.SALESTRAN WHERE MASTERNUMBER = ? AND SOURCESYSTEMID = 103 AND TRANTYPE IN ('IO', 'AJ', 'OR') AND EXTRACT (YEAR FROM to_date(ORDERTRANDATE, 'DD-MON-YY')) >  EXTRACT (YEAR FROM SYSDATE) -2  )"
		+ " HEAD LEFT OUTER JOIN HELIOS_OWN.SALESTRANCHARGE STC ON HEAD.SALESTRANID = STC.SALESTRANID INNER JOIN (SELECT CLD_DT, FSC_YR FROM HELIOS_OWN.D_TIME WHERE TM_LVL = 'Day') DT ON HEAD.ORDERTRANDATE = DT.CLD_DT GROUP BY FSC_YR )    select * from (SELECT fiscal_year AS YEAR, TOTAL_SALES   AS YTD_SALES   FROM SS  WHERE fiscal_year = ( select to_char(sysdate, 'YYYY') from dual ) )  union select to_char(to_char(sysdate, 'YYYY')) AS YEAR, 0  YTD_SALES from dual  WHERE NOT EXISTS"
		+ " ( SELECT 1   FROM SS  WHERE fiscal_year = ( select to_char(sysdate, 'YYYY') from dual ) )  ";

}