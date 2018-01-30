package com.staples.dashboard.app.dao;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegmentComments;
import com.staples.dashboard.app.vo.SegmentDetail;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentVO;

@Repository
public class CTADAOImpl{

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	//@Override
	public List<SegmentVO> getSegmentList() {
		
		return namedParameterJdbcTemplate.query("select * from HELIOS_OWN.sa_segment", new RowMapper<SegmentVO>() {

			@Override
			public SegmentVO mapRow(ResultSet rs, int arg1) throws SQLException {
				SegmentVO segmentVO = new SegmentVO();
				segmentVO.setSeg(rs.getLong(1));
				segmentVO.setSegDesc(rs.getString(2));
				return segmentVO;
			}
		});
	}
	
	//@Override
	public List<SegHeaderLabelsVO> getSegHdrLables(){
		return namedParameterJdbcTemplate.query(CTAConstants.SELECT_SEG_HDR_LABLES_LIST, new RowMapper<SegHeaderLabelsVO>() {
			@Override
			public SegHeaderLabelsVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				SegHeaderLabelsVO segHeaderLabelsVO = new SegHeaderLabelsVO();
				/*segHeaderLabelsVO.setHeaderId(rs.getLong(1));
				segHeaderLabelsVO.setHeaderName(rs.getString(2));*/
				return segHeaderLabelsVO;
			}
		});
	}
	
	//@Override
	public SegHdrText getSegHdrText(Long seg, Long segId){
		String query = CTAConstants.SELECT_SEG_HEADER_TEXT;
		Map<String, Long> parameters = new HashMap<String, Long>();
		parameters.put("seg", seg);
		parameters.put("segId", segId);
		SegHdrText segHdrText = null;
		try{segHdrText = namedParameterJdbcTemplate.queryForObject(query, parameters, new RowMapper<SegHdrText>() {
			
			@Override
			public SegHdrText mapRow(ResultSet rs, int arg1) throws SQLException {
				SegHdrText segHdrText = new SegHdrText();
/*				segHdrText.setSeg(rs.getLong(CTAConstants.SEG));
				segHdrText.setSegId(rs.getLong(CTAConstants.SEG_ID));
				segHdrText.setHeaderIdsShown(rs.getString(CTAConstants.HEADER_IDS_SHOWN));
				segHdrText.setTotalHeaders(rs.getLong(CTAConstants.TOTAL_HEADERS));
				segHdrText.setHdr1StaticText(convertToString(rs.getClob(CTAConstants.HDR_1_STATIC_TEXT)));
				segHdrText.setHdr1ParamCount(rs.getLong(CTAConstants.HDR_1_PARAM_COUNT));
				segHdrText.setHdr2StaticText(convertToString(rs.getClob(CTAConstants.HDR_2_STATIC_TEXT)));
				segHdrText.setHdr2ParamCount(rs.getLong(CTAConstants.HDR_2_PARAM_COUNT));
				segHdrText.setHdr3StaticText(convertToString(rs.getClob(CTAConstants.HDR_3_STATIC_TEXT)));
				segHdrText.setHdr3ParamCount(rs.getLong(CTAConstants.HDR_3_PARAM_COUNT));
				segHdrText.setHdr4StaticText(convertToString(rs.getClob(CTAConstants.HDR_4_STATIC_TEXT)));
				segHdrText.setHdr4ParamCount(rs.getLong(CTAConstants.HDR_4_PARAM_COUNT));
				segHdrText.setHdr5StaticText(convertToString(rs.getClob(CTAConstants.HDR_5_STATIC_TEXT)));
				segHdrText.setHdr5ParamCount(rs.getLong(CTAConstants.HDR_5_PARAM_COUNT));
				segHdrText.setHdr6StaticText(convertToString(rs.getClob(CTAConstants.HDR_6_STATIC_TEXT)));
				segHdrText.setHdr6ParamCount(rs.getLong(CTAConstants.HDR_6_PARAM_COUNT));
				segHdrText.setHdr7StaticText(convertToString(rs.getClob(CTAConstants.HDR_7_STATIC_TEXT)));
				segHdrText.setHdr7ParamCount(rs.getLong(CTAConstants.HDR_7_PARAM_COUNT));
				segHdrText.setHdr8StaticText(convertToString(rs.getClob(CTAConstants.HDR_8_STATIC_TEXT)));
				segHdrText.setHdr8ParamCount(rs.getLong(CTAConstants.HDR_8_PARAM_COUNT));
				segHdrText.setHdr9StaticText(convertToString(rs.getClob(CTAConstants.HDR_9_STATIC_TEXT)));
				segHdrText.setHdr9ParamCount(rs.getLong(CTAConstants.HDR_9_PARAM_COUNT));
				segHdrText.setHdr10StaticText(convertToString(rs.getClob(CTAConstants.HDR_10_STATIC_TEXT)));
				segHdrText.setHdr10ParamCount(rs.getLong(CTAConstants.HDR_10_PARAM_COUNT));*/
				return segHdrText;
			}
		});
		}catch(DataAccessException exception){
			exception.printStackTrace();
			return segHdrText;
		}
		return segHdrText;
	
	}
	
	private String convertToString(Clob clob){
		String value = null;
		try {
			if(clob!=null)
				value = clob.getSubString(1, (int) clob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	//@Override
	public List<SegHeaderLabelsVO> getSegHdrLables(Set<Long> idsSet) {
		
		List<SegHeaderLabelsVO> labelsVOs = new ArrayList<SegHeaderLabelsVO>();
		try{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("idsSet", idsSet );
			labelsVOs = namedParameterJdbcTemplate.query(
					CTAConstants.SELECT_SEG_HEADER_LABELS_IN_CLAUSE,
					parameters,
					new RowMapper<SegHeaderLabelsVO>() {

						@Override
						public SegHeaderLabelsVO mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							SegHeaderLabelsVO segHeaderLabelsVO = new SegHeaderLabelsVO();
							/*segHeaderLabelsVO.setHeaderId(rs.getLong(1));
							segHeaderLabelsVO.setHeaderName(rs.getString(2));*/
							return segHeaderLabelsVO;
						}
					});
		}catch(DataAccessException exception){
			exception.printStackTrace();
			return labelsVOs;
		}		
		return labelsVOs;
	}

	//@Override
	public String addSegmentDetail(SegmentDetailWrapper segmentDetailWrapper) {
		SegmentDetail segmentDetail = segmentDetailWrapper.getSegDetail();
		SegmentComments comment = segmentDetailWrapper.getComment();
		
		MapSqlParameterSource segDetailSelectParamSource = new MapSqlParameterSource();
		segDetailSelectParamSource.addValue(CTAConstants.TASK_ID, segmentDetail.getTaskIdCombination());
		
		MapSqlParameterSource segDetailInsertParamSource = new MapSqlParameterSource();
		segDetailInsertParamSource.addValue(CTAConstants.TASK_ID, segmentDetail.getTaskIdCombination());
		segDetailInsertParamSource.addValue(CTAConstants.LINK_TASK, segmentDetail.getLinkTask());
		segDetailInsertParamSource.addValue(CTAConstants.CUST_NUM, segmentDetail.getCustomerNumber());
		segDetailInsertParamSource.addValue(CTAConstants.DIVISION, segmentDetail.getDivison());
		segDetailInsertParamSource.addValue(CTAConstants.CTA_SEG, segmentDetail.getCtaSegment());
		segDetailInsertParamSource.addValue(CTAConstants.CTA_SUB_SEG, segmentDetail.getCtaSubSegment());
		segDetailInsertParamSource.addValue(CTAConstants.FYR_FPRD_FWK_FDY, segmentDetail.getFyrFprdFwkFdy());
		segDetailInsertParamSource.addValue(CTAConstants.SEG_DISP_STATUS, segmentDetail.getSegDispStatus());
		segDetailInsertParamSource.addValue(CTAConstants.INSERT_DATE, segmentDetail.getTaskInsertDate());
		segDetailInsertParamSource.addValue(CTAConstants.UPDATE_DATE, segmentDetail.getTaskUpdateDate());
		segDetailInsertParamSource.addValue(CTAConstants.SFDC_DATE, segmentDetail.getSfdcSentDate());
		segDetailInsertParamSource.addValue(CTAConstants.CREATED_BY, segmentDetail.getCreatedBy());
		segDetailInsertParamSource.addValue(CTAConstants.UPDATED_BY, segmentDetail.getUpdatedBy());
		segDetailInsertParamSource.addValue(CTAConstants.REFRESHED_BY, segmentDetail.getRefreshedBy());
		
		
		MapSqlParameterSource segCommentParamSource = new MapSqlParameterSource();
		segCommentParamSource.addValue(CTAConstants.TASK_ID, comment.getTaskIdCombination());
		segCommentParamSource.addValue(CTAConstants.INSERT_DATE, comment.getInsertDate());
		segCommentParamSource.addValue(CTAConstants.COMMENT_TEXT, comment.getCommentText());
		segCommentParamSource.addValue(CTAConstants.LOGGED_USER, comment.getLoggedUser());
		
		SegmentDetail segmentDetailDB = null;
		try{

			segmentDetailDB = namedParameterJdbcTemplate.queryForObject(CTAConstants.SELECT_SEGMENT_DETAIL, segDetailSelectParamSource, new RowMapper<SegmentDetail>() {
				@Override
				public SegmentDetail mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					SegmentDetail segmentDetail = new SegmentDetail();
					segmentDetail.setTaskIdCombination(rs.getString("TASK_ID_COMBINATION"));
					return segmentDetail;
				}
			});
			
		}catch(DataAccessException e){
			segmentDetailDB=null;
		}
		
		String executedSQL = segmentDetailDB==null ? CTAConstants.INSERT_SEGMENT_DETAIL : CTAConstants.UPDATE_SEGMENT_DETAIL;
		
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
	    
	    try{	    	
	    	namedParameterJdbcTemplate.update(executedSQL, segDetailInsertParamSource);
	    	namedParameterJdbcTemplate.update(CTAConstants.INSERT_SEGMENT_COMMENT, segCommentParamSource);
	    	transactionManager.commit(status);
	    	return "Operation Completed Successfully";
	    }catch(DataAccessException exception){
	    	transactionManager.rollback(status);
	    	throw exception;
	    }
	}
}
