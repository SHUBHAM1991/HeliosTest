package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.CustProfileVO;

/**
 * The Class CustomerMapper.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class CustomerMapper implements RowMapper<CustProfileVO> , StaplesDashBoardRowMapper{
	
	/**
	 * Method implementation to get row map.
	 * @param ResultSet rs
	 * @param int rowNum
	 * @return AbabdonedCartVO
	 * @throws SQLException
	 */
	public CustProfileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustProfileVO customerVO = new CustProfileVO();
		customerVO.setCompanyName(rs
				.getString(MapperConstants.CUSTOMER_COMPANY_NAME));
		customerVO.setCustNum(rs.getString(MapperConstants.CUSTOMER_CUST_NUM));
		customerVO.setDivision(rs.getString(MapperConstants.DIVISION));
		customerVO.setEmailId(rs.getString(MapperConstants.CUSTOMER_EMAIL_ID));
		customerVO.setFirstName(rs
				.getString(MapperConstants.CUSTOMER_FIRST_NAME));
		customerVO
				.setLastName(rs.getString(MapperConstants.CUSTOMER_LAST_NAME));
		customerVO
				.setPhoneNum(rs.getString(MapperConstants.CUSTOMER_PHONE_NUM));
		customerVO.setCustType(rs
				.getString(MapperConstants.CUSTOMER_CUSTOMER_TYPE));
		customerVO.setAgentEmailId(rs
				.getString(MapperConstants.CUSTOMER_MGREMAIL));
		customerVO.setAgentName(rs.getString(MapperConstants.CUSTOMER_MGRNAME));
		customerVO.setAgentPhoneNum(rs
				.getString(MapperConstants.CUSTOMER_MGRPHONE));
		customerVO.setRepCd(rs.getString(MapperConstants.REP_CD));
		customerVO
				.setCompanyAddr1(rs.getString(MapperConstants.COMPANY_ADDR_1));
		customerVO
				.setCompanyAddr2(rs.getString(MapperConstants.COMPANY_ADDR_2));
		customerVO.setCompanyCity(rs.getString(MapperConstants.COMPANY_CITY));
		customerVO.setCompanyState(rs.getString(MapperConstants.COMPANY_STATE));
		customerVO.setCompanyCountry(rs
				.getString(MapperConstants.COMPANY_COUNTRY));
		customerVO.setCompanyZip(rs.getString(MapperConstants.COMPANY_ZIP));
		customerVO.setCompanyPhone(rs.getString(MapperConstants.COMPANY_PHONE));
		customerVO.setIamId(rs.getString(MapperConstants.IAMID));
		return customerVO;
	}
	
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> rs
	 * @return Object
	 */
	 public Object mapRow(Map<String,Object> rs) {

		final CustProfileVO customerVO = new CustProfileVO();
		customerVO
				.setCompanyName(rs.get(MapperConstants.CUSTOMER_COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_COMPANY_NAME)
								.toString());
		customerVO
				.setCustNum(rs.get(MapperConstants.CUSTOMER_CUST_NUM) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_CUST_NUM).toString());
		customerVO
				.setDivision(rs.get(MapperConstants.DIVISION) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.DIVISION).toString());
		customerVO
				.setEmailId(rs.get(MapperConstants.CUSTOMER_EMAIL_ID) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_EMAIL_ID).toString());
		customerVO
				.setFirstName(rs.get(MapperConstants.CUSTOMER_FIRST_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_FIRST_NAME)
								.toString());
		customerVO
				.setLastName(rs.get(MapperConstants.CUSTOMER_LAST_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_LAST_NAME).toString());
		customerVO
				.setPhoneNum(rs.get(MapperConstants.CUSTOMER_PHONE_NUM) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_PHONE_NUM).toString());
		customerVO
				.setCustType(rs.get(MapperConstants.CUSTOMER_CUSTOMER_TYPE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_CUSTOMER_TYPE)
								.toString());
		customerVO
				.setAgentName(rs.get(MapperConstants.CUSTOMER_MGRNAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_MGRNAME).toString());
		customerVO
				.setAgentEmailId(rs.get(MapperConstants.CUSTOMER_MGREMAIL) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_MGREMAIL).toString());
		customerVO
				.setAgentPhoneNum(rs.get(MapperConstants.CUSTOMER_MGRPHONE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_MGRPHONE).toString());
		customerVO
				.setRepCd(rs.get(MapperConstants.REP_CD) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.REP_CD).toString());

		customerVO
				.setCompanyAddr1(rs.get(MapperConstants.COMPANY_ADDR_1) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_ADDR_1).toString());
		customerVO
				.setCompanyAddr2(rs.get(MapperConstants.COMPANY_ADDR_2) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_ADDR_2).toString());
		customerVO
				.setCompanyCity(rs.get(MapperConstants.COMPANY_CITY) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_CITY).toString());
		customerVO
				.setCompanyState(rs.get(MapperConstants.COMPANY_STATE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_STATE).toString());
		customerVO
				.setCompanyCountry(rs.get(MapperConstants.COMPANY_COUNTRY) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_COUNTRY).toString());
		customerVO
				.setCompanyZip(rs.get(MapperConstants.COMPANY_ZIP) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_ZIP).toString());
        customerVO
        		.setCompanyPhone(rs.get(MapperConstants.COMPANY_PHONE) == null ? MapperConstants.BLANK_STRING
        			: rs.get(MapperConstants.COMPANY_PHONE).toString());
        customerVO
    		.setLts(rs.get(MapperConstants.LTS) == null ? MapperConstants.BLANK_STRING
    			: rs.get(MapperConstants.LTS).toString());	
        customerVO
		.setIamId(rs.get(MapperConstants.IAMID) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.IAMID).toString());
        customerVO
		.setLastContactedDate(rs.get(MapperConstants.LAST_CONTACTED_DATE) == null ? MapperConstants.BLANK_STRING
			: rs.get(MapperConstants.LAST_CONTACTED_DATE).toString());
        customerVO
      		.setCompWebsite(rs.get(MapperConstants.COMPANY_WEBSITE) == null ? MapperConstants.BLANK_STRING
      			: rs.get(MapperConstants.COMPANY_WEBSITE).toString());
		
		return customerVO;
	}

	/**
	 * Method implementation to get row map.
	 * 
	 * @param List <Map<String,Object>> resultMap
	 * @return List<Object>
	 */
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {

		List<Object> objects = null;
		CustProfileVO customerVO = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				if (objects == null) {
					objects = new ArrayList<Object>();
				}
				customerVO = new CustProfileVO();
				customerVO
						.setCompanyName(rs
								.get(MapperConstants.CUSTOMER_COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_COMPANY_NAME)
										.toString());
				customerVO
						.setCustNum(rs
								.get(MapperConstants.CUSTOMER_MASTER_CUST_NUM) == null ? MapperConstants.BLANK_STRING
								: rs.get(
										MapperConstants.CUSTOMER_MASTER_CUST_NUM)
										.toString());
				customerVO
						.setEmailId(rs.get(MapperConstants.CUSTOMER_EMAIL_ID) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_EMAIL_ID)
										.toString());
				customerVO
						.setFirstName(rs
								.get(MapperConstants.CUSTOMER_FIRST_NAME) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_FIRST_NAME)
										.toString());
				customerVO
						.setLastName(rs.get(MapperConstants.CUSTOMER_LAST_NAME) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_LAST_NAME)
										.toString());
				customerVO
						.setPhoneNum(rs.get(MapperConstants.CUSTOMER_PHONE_NUM) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_PHONE_NUM)
										.toString());
				customerVO
						.setCustType(rs
								.get(MapperConstants.CUSTOMER_CUSTOMER_TYPE) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_CUSTOMER_TYPE)
										.toString());
				customerVO
						.setCallOrder(rs
								.get(MapperConstants.CUSTOMER_CALL_ORDER) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_CALL_ORDER)
										.toString());
				
				customerVO
				.setLastContactedDate(rs
						.get(MapperConstants.CUSTOMER_LAST_CONTACTED_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_LAST_CONTACTED_DATE)
								.toString());
		
				
				customerVO
						.setPlayListId(rs
								.get(MapperConstants.CUSTOMER_PLAY_ID_LIST) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.CUSTOMER_PLAY_ID_LIST)
										.toString());
				customerVO
						.setAgentEmailId(rs
								.get(MapperConstants.CUSTOMER_AGENT_EMAIL_ID) == null ? MapperConstants.BLANK_STRING
								: rs.get(
										MapperConstants.CUSTOMER_AGENT_EMAIL_ID)
										.toString());
				customerVO
						.setAgentFlag(rs
								.get(MapperConstants.CUSTOMER_ACCT_MNGR_FLAG) == null ? MapperConstants.BLANK_STRING
								: rs.get(
										MapperConstants.CUSTOMER_ACCT_MNGR_FLAG)
										.toString());
				
				customerVO
				.setCustomerSegment(rs
						.get(MapperConstants.CUSTOMER_SEGMENT) == null ? MapperConstants.BLANK_STRING
						: rs.get(
								MapperConstants.CUSTOMER_SEGMENT)
								.toString());
				
				customerVO
				.setVapScore(rs
						.get(MapperConstants.CUSTOMER_VAP_SCORE) == null ? MapperConstants.BLANK_STRING
						: rs.get(
								MapperConstants.CUSTOMER_VAP_SCORE)
								.toString());
		
				customerVO
				.setAccountQualifyScore(rs
						.get(MapperConstants.CUSTOMER_ACCOUNT_QUALIFY_SCORE) == null ? MapperConstants.BLANK_STRING
						: rs.get(
								MapperConstants.CUSTOMER_ACCOUNT_QUALIFY_SCORE)
								.toString());
		
				
				customerVO
						.setCompanyAddr1(rs.get(MapperConstants.COMPANY_ADDR_1) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_ADDR_1)
										.toString());
				customerVO
						.setCompanyAddr2(rs.get(MapperConstants.COMPANY_ADDR_2) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_ADDR_2)
										.toString());
				customerVO
						.setCompanyCity(rs.get(MapperConstants.COMPANY_CITY) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_CITY)
										.toString());
				customerVO
						.setCompanyState(rs.get(MapperConstants.COMPANY_STATE) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_STATE)
										.toString());
				customerVO
						.setCompanyCountry(rs
								.get(MapperConstants.COMPANY_COUNTRY) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_COUNTRY)
										.toString());
				customerVO
						.setCompanyZip(rs.get(MapperConstants.COMPANY_ZIP) == null ? MapperConstants.BLANK_STRING
								: rs.get(MapperConstants.COMPANY_ZIP)
										.toString());
                		customerVO
                			.setCompanyPhone(rs.get(MapperConstants.COMPANY_PHONE) == null ? MapperConstants.BLANK_STRING
                				: rs.get(MapperConstants.COMPANY_PHONE)
                					.toString());

                		customerVO.setPlaySeg(rs.get("PLAY_SEGMENT") == null ? MapperConstants.BLANK_STRING : rs.get("PLAY_SEGMENT").toString());;
                		
				objects.add(customerVO);

			}
		}
		return objects;
	}
}