package com.staples.dashboard.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.CdmCustProfileVO;
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
public class CdmCustomerMapper implements RowMapper<CdmCustProfileVO> , StaplesDashBoardRowMapper{
	
	
	@Override
	public CdmCustProfileVO mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Method implementation to get row map.
	 * @param List<Map<String,Object>> rs
	 * @return Object
	 */
	 public Object mapRow(Map<String,Object> rs) {

		    CdmCustProfileVO cdmCustomerVO = new CdmCustProfileVO();
			cdmCustomerVO
			.setCustNum(rs.get(MapperConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CUSTOMER_NUMBER)
							.toString());
			cdmCustomerVO
			.setCompanyName(rs
					.get(MapperConstants.COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
					: rs.get(
							MapperConstants.COMPANY_NAME)
							.toString());
			cdmCustomerVO
			.setCallOrder(rs.get(MapperConstants.CALL_ORDER) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CALL_ORDER)
							.toString());
			cdmCustomerVO
			.setPotentialCallVal(rs
					.get(MapperConstants.POTENTIAL_CALL_VALUE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.POTENTIAL_CALL_VALUE)
							.toString());
			/*cdmCustomerVO.setLastContactedDate(rs.get(MapperConstants.S_LAST_CONTACTED_DATE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.S_LAST_CONTACTED_DATE)
							.toString());*/
			cdmCustomerVO
			.setCustomerType(rs
					.get(MapperConstants.CUSTOMER_TYPE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CUSTOMER_TYPE)
							.toString());
			cdmCustomerVO
			.setIam_Id(rs
					.get(MapperConstants.IAM_ID) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.IAM_ID)
							.toString());
			
			/*cdmCustomerVO
			.setCustomerSegment(rs
					.get(MapperConstants.CUSTOMER_SEGMENT) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CUSTOMER_SEGMENT)
							.toString());
			cdmCustomerVO
			.setVapScore(rs.get(MapperConstants.VAP_SCORE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.VAP_SCORE)
							.toString());
			
			cdmCustomerVO
			.setVapScoreName(rs
					.get(MapperConstants.VAP_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.VAP_SCORE_NAME)
							.toString());
			cdmCustomerVO
			.setVapScoreType(rs
					.get(MapperConstants.VAP_SCORE_TYPE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.VAP_SCORE_TYPE)
							.toString());
			cdmCustomerVO
			.setTimeZone(rs
					.get(MapperConstants.TIMEZONE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.TIMEZONE)
							.toString());*/
			cdmCustomerVO
			.setStatus(rs
					.get(MapperConstants.CTA_CATEGORY_STATUS) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CTA_CATEGORY_STATUS)
							.toString());
			cdmCustomerVO
			.setSegmentIdList(rs
					.get(MapperConstants.CUSTOMER_SEGMENT_ID) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CUSTOMER_SEGMENT_ID)
							.toString());
			
			cdmCustomerVO
			.setDivision(rs
					.get(MapperConstants.CTA_DIVISION) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.CTA_DIVISION)
							.toString());
			cdmCustomerVO
			.setCompWebsite(rs
					.get(MapperConstants.COMPANY_WEBSITE) == null ? MapperConstants.BLANK_STRING
					: rs.get(MapperConstants.COMPANY_WEBSITE)
							.toString());
			return cdmCustomerVO;
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
		CdmCustProfileVO cdmCustomerVO = null;
		if (resultMap != null && !resultMap.isEmpty()) {
			for (Map<String, Object> rs : resultMap) {
				if (objects == null) {
					objects = new ArrayList<Object>();
				}
			
				cdmCustomerVO = new CdmCustProfileVO();
				cdmCustomerVO
				.setCustNum(rs.get(MapperConstants.CUSTOMER_NUMBER) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_NUMBER)
								.toString());
				cdmCustomerVO
				.setCompanyName(rs
						.get(MapperConstants.COMPANY_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(
								MapperConstants.COMPANY_NAME)
								.toString());
				cdmCustomerVO
				.setCallOrder(rs.get(MapperConstants.CALL_ORDER) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CALL_ORDER)
								.toString());
				cdmCustomerVO
				.setPotentialCallVal(rs
						.get(MapperConstants.POTENTIAL_CALL_VALUE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.POTENTIAL_CALL_VALUE)
								.toString());
				/*cdmCustomerVO.setLastContactedDate(rs.get(MapperConstants.S_LAST_CONTACTED_DATE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.S_LAST_CONTACTED_DATE)
								.toString());*/
				cdmCustomerVO
				.setCustomerType(rs
						.get(MapperConstants.CUSTOMER_TYPE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_TYPE)
								.toString());
				
				cdmCustomerVO
				.setIam_Id(rs
						.get(MapperConstants.IAM_ID) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.IAM_ID)
								.toString());
				/*cdmCustomerVO
				.setCustomerSegment(rs
						.get(MapperConstants.CUSTOMER_SEGMENT) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_SEGMENT)
								.toString());
				cdmCustomerVO
				.setVapScore(rs.get(MapperConstants.VAP_SCORE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.VAP_SCORE)
								.toString());
				
				cdmCustomerVO
				.setVapScoreName(rs
						.get(MapperConstants.VAP_SCORE_NAME) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.VAP_SCORE_NAME)
								.toString());
				cdmCustomerVO
				.setVapScoreType(rs
						.get(MapperConstants.VAP_SCORE_TYPE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.VAP_SCORE_TYPE)
								.toString());*/
				cdmCustomerVO
				.setTimeZone(rs
						.get(MapperConstants.TIMEZONE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.TIMEZONE)
								.toString());
				cdmCustomerVO
				.setStatus(rs
						.get(MapperConstants.CTA_CATEGORY_STATUS) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CTA_CATEGORY_STATUS)
								.toString());
				
				cdmCustomerVO
				.setSegmentIdList(rs
						.get(MapperConstants.CUSTOMER_SEGMENT_ID) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CUSTOMER_SEGMENT_ID)
								.toString());
				
				
				
				cdmCustomerVO
				.setDivision(rs
						.get(MapperConstants.CTA_DIVISION) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.CTA_DIVISION)
								.toString());
				cdmCustomerVO
				.setTodaysProgress(rs
						.get(MapperConstants.TODAYS_PROGRESS) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.TODAYS_PROGRESS)
								.toString());
				cdmCustomerVO
				.setWeakProgress(rs
						.get(MapperConstants.WEEK_PROGRESS) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.WEEK_PROGRESS)
								.toString());
				
				
				cdmCustomerVO
				.setCompWebsite(rs
						.get(MapperConstants.COMPANY_WEBSITE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.COMPANY_WEBSITE)
								.toString());
				cdmCustomerVO
				.setAlertCount(rs
						.get(MapperConstants.TOTAL_ALERT_COUNT) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.TOTAL_ALERT_COUNT)
								.toString());
				cdmCustomerVO
				.setAlertDeleteCount(rs
						.get(MapperConstants.TOTAL_DELETE_COUNT) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.TOTAL_DELETE_COUNT)
								.toString());
				cdmCustomerVO
				.setIsActiveAlert(rs
						.get(MapperConstants.ACTIVE) == null ? MapperConstants.BLANK_STRING
						: rs.get(MapperConstants.ACTIVE)
								.toString());
				cdmCustomerVO
				.setTotalRows(rs
						.get("TOTAL_ROWS") == null ? MapperConstants.BLANK_STRING
						: rs.get("TOTAL_ROWS")
								.toString());
				objects.add(cdmCustomerVO);
				

			}
		}
		return objects;
	}

	
}