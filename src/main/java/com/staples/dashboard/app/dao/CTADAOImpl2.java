package com.staples.dashboard.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.constants.SQLConstants;
import com.staples.dashboard.app.dao.mappers.CTABaseMapper;
import com.staples.dashboard.app.dao.mappers.CustSfdcInfoMapper;
import com.staples.dashboard.app.dao.mappers.SegmentSubDetailMapper;
import com.staples.dashboard.app.dao.mappers.SegmentVOMapper;
import com.staples.dashboard.app.dao.mappers.SubjectLabelMapper;
import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.vo.Comment1;
import com.staples.dashboard.app.vo.CustSfdcInfoVO;
import com.staples.dashboard.app.vo.SegDetailInfo1;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegmentComments;
import com.staples.dashboard.app.vo.SegmentDetail;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.SegmentVO;
import com.staples.dashboard.exception.DashboardException;

@Repository
@Transactional
public class CTADAOImpl2 implements CTADAO{

	@Autowired
	@Qualifier("sessionFactorycustexoHeliosOwn")
	private SessionFactory custExoHeliosOwnSessionFactory;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Value("${adminSFDCId}")
	private String adminSFDCId;

	@Override
	public List<SegmentVO> getSegmentList() {
		Session session = null;
		List<SegmentVO> segmentVOs = null;;
		try{
			segmentVOs = new ArrayList<SegmentVO>();
			session = custExoHeliosOwnSessionFactory.openSession();

			for(Object object :getResultList(session, CTAConstants.SELECT_SEGMENT_LIST,
					null, new SegmentVOMapper())){
				if(null != object){
					segmentVOs.add((SegmentVO)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return segmentVOs;
	}

	@Override
	public List<SegHeaderLabelsVO> getSegHdrLables() {
		return null;/*
		Session session = null;
		List<SegHeaderLabelsVO> labelsVOs = null;
		try{
			labelsVOs = new ArrayList<SegHeaderLabelsVO>();
			session = custExoHeliosOwnSessionFactory.openSession();
			for(Object object : getResultList(session, CTAConstants.SELECT_SEG_HDR_LABLES_LIST,
					null, new SegHeaderLabelsVOMapper())){
				if(null != object){
					labelsVOs.add((SegHeaderLabelsVO)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return labelsVOs;
	*/}

	@SuppressWarnings("unchecked")
	@Override
	public SegHdrText getSegHdrText(Long seg, Long segId) {
		return null;/*		
		Session session = null;
		SegHdrText segHdrText = null;
		Map<String, SegHdrText> map = new HashMap<String, SegHdrText>();
		String segSegIdKey = seg+","+segId;
		
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			//SQLQuery sqlQuery = session.createSQLQuery(CTAConstants.SELECT_SEG_HEADER_TEXT);
			SQLQuery sqlQuery = session.createSQLQuery(CTAConstants.SELECT_SEG_HEADER_TEXT_SUB_DETAILS);
			sqlQuery.setParameter(CTAConstants.SEG_PARAM, seg);
			sqlQuery.setParameter(CTAConstants.SEG_ID_PARAM, segId);
			
			List<Map<String,Object>> segHdrTextList = sqlQuery.setResultTransformer(MyResultTransformer.INSTANCE).list();
			if(segHdrTextList.size() > 0){
				segHdrText = (SegHdrText)(new SegHdrTextMapper().mapRow(segHdrTextList)).get(0);
				
				
			}
			
			if (null != segHdrTextList && segHdrTextList.size() > Constants.ZERO){
				
				for (Map<String, Object> rs : segHdrTextList) {		
					String mapKey = rs.get(CTAConstants.SEG).toString()+","+rs.get(CTAConstants.SEG_ID);
				    	  segHdrText = map.get(mapKey);
				    	  if(segHdrText == null){
				    		  segHdrText = new SegHdrText();
						        segHdrText.setSeg(rs.get(CTAConstants.SEG) == null ? null : Long.valueOf(rs.get(CTAConstants.SEG).toString()));
						        segHdrText.setSegId(rs.get(CTAConstants.SEG_ID) == null ? null :Long.valueOf(rs.get(CTAConstants.SEG_ID).toString()));
						        segHdrText.setHeaderIdsShown(rs.get(CTAConstants.HEADER_IDS_SHOWN)==MapperConstants.BLANK_STRING? MapperConstants.BLANK_STRING : rs.get(CTAConstants.HEADER_IDS_SHOWN).toString());
								segHdrText.setTotalHeaders(rs.get(CTAConstants.TOTAL_HEADERS)==null?null:Long.valueOf(rs.get(CTAConstants.TOTAL_HEADERS).toString()));
								segHdrText.setHdr1StaticText(rs.get(CTAConstants.HDR_1_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_1_STATIC_TEXT).toString());
								segHdrText.setHdr1ParamCount(rs.get(CTAConstants.HDR_1_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_1_PARAM_COUNT).toString()));
								segHdrText.setHdr2StaticText(rs.get(CTAConstants.HDR_2_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_2_STATIC_TEXT).toString());
								segHdrText.setHdr2ParamCount(rs.get(CTAConstants.HDR_2_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_2_PARAM_COUNT).toString()));
								segHdrText.setHdr3StaticText(rs.get(CTAConstants.HDR_3_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_3_STATIC_TEXT).toString());
								segHdrText.setHdr3ParamCount(rs.get(CTAConstants.HDR_3_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_3_PARAM_COUNT).toString()));
								segHdrText.setHdr4StaticText(rs.get(CTAConstants.HDR_4_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_4_STATIC_TEXT).toString());
								segHdrText.setHdr4ParamCount(rs.get(CTAConstants.HDR_4_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_4_PARAM_COUNT).toString()));
								segHdrText.setHdr5StaticText(rs.get(CTAConstants.HDR_5_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_5_STATIC_TEXT).toString());
								segHdrText.setHdr5ParamCount(rs.get(CTAConstants.HDR_5_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_5_PARAM_COUNT).toString()));
								segHdrText.setHdr6StaticText(rs.get(CTAConstants.HDR_6_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_6_STATIC_TEXT).toString());
								segHdrText.setHdr6ParamCount(rs.get(CTAConstants.HDR_6_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_6_PARAM_COUNT).toString()));
								segHdrText.setHdr7StaticText(rs.get(CTAConstants.HDR_7_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_7_STATIC_TEXT).toString());
								segHdrText.setHdr7ParamCount(rs.get(CTAConstants.HDR_7_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_7_PARAM_COUNT).toString()));
								segHdrText.setHdr8StaticText(rs.get(CTAConstants.HDR_8_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_8_STATIC_TEXT).toString());
								segHdrText.setHdr8ParamCount(rs.get(CTAConstants.HDR_8_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_8_PARAM_COUNT).toString()));
								segHdrText.setHdr9StaticText(rs.get(CTAConstants.HDR_9_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_9_STATIC_TEXT).toString());
								segHdrText.setHdr9ParamCount(rs.get(CTAConstants.HDR_9_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_9_PARAM_COUNT).toString()));
								segHdrText.setHdr10StaticText(rs.get(CTAConstants.HDR_10_STATIC_TEXT)==null?MapperConstants.BLANK_STRING:rs.get(CTAConstants.HDR_10_STATIC_TEXT).toString());
								segHdrText.setHdr10ParamCount(rs.get(CTAConstants.HDR_10_PARAM_COUNT)==null?null:Long.valueOf(rs.get(CTAConstants.HDR_10_PARAM_COUNT).toString()));
								map.put(mapKey, segHdrText);
				    	  }
				    	  List<SegmentSubDetail> subDetails = segHdrText.getSubDetails();
					  		if(subDetails==null){
					  			subDetails = new ArrayList<SegmentSubDetail>();
					  			segHdrText.setSubDetails(subDetails);					  										
					  		}
					  		SegmentSubDetail segmentSubDetail = new SegmentSubDetail();
					  		segmentSubDetail.setHeaderId(rs.get(CTAConstants.HEADER_ID).toString());
					  		segmentSubDetail.setParamId(rs.get(CTAConstants.PARAM_ID).toString());
					  		segmentSubDetail.setParamLabel(rs.get(CTAConstants.PARAM_LABEL).toString());
					  		segmentSubDetail.setParamValue(rs.get(CTAConstants.PARAM_VALUE).toString());
					  		subDetails.add(segmentSubDetail);
					  		
				
				}
				return map.get(segSegIdKey);				
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return segHdrText;
	*/}

	@Override
	public List<SegHeaderLabelsVO> getSegHdrLables(Set<Long> idsSet) {
		return null;/*
		List<Object> resultList = new ArrayList<Object>();
		List<SegHeaderLabelsVO> labelsVOs = null;
		Session session = null;
		try{
			labelsVOs = new ArrayList<SegHeaderLabelsVO>();
			session = custExoHeliosOwnSessionFactory.openSession();			
			SQLQuery sqlQuery = session.createSQLQuery(CTAConstants.SELECT_SEG_HEADER_LABELS_IN_CLAUSE);
			sqlQuery.setParameterList(CTAConstants.IDS_SET_PARAM, idsSet);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = new SegHeaderLabelsVOMapper().mapRow(list);
			}
			for(Object object : resultList){
				if(null != object){
					labelsVOs.add((SegHeaderLabelsVO)object);
				}
			}
			
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return labelsVOs;
	*/}

	@Override
	public String addSegmentDetail(SegmentDetailWrapper segmentDetailWrapper) {
		SegmentDetail sd = segmentDetailWrapper.getSegDetail();
		SegmentComments comment = segmentDetailWrapper.getComment();
		Session session = null;
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery selectSegId = session.createSQLQuery("select seg from HELIOS_OWN.sa_segment where seg_desc=UPPER('"+sd.getCtaSegment()+"')");
			long segIdObjlong = ((Number)selectSegId.uniqueResult()).longValue();
			//CONCAT(CUSTOMER_NUMBER,DIVISION,CTA_SEGMENT,CTA_SUB_SEGMENT,FYR_FPRD_FWK_FDY)
			if(null !=sd.getCustomerNumber() && !("".equals(sd.getCustomerNumber())) && sd.getCustomerNumber().length() < Constants.TEN){
				String format = String.format("%%0%dd", 10);
				String custId= String.format(format, Integer.parseInt(sd.getCustomerNumber()));
				sd.setCustomerNumber(custId);
			}
            String segId=String.valueOf(segIdObjlong);
            if(null !=segId && !("".equals(segId)) && segId.length() > Constants.ZERO){
				String format = String.format("%%0%dd", 2);
				segId= String.format(format, Integer.parseInt(segId));
			}
            
            if(null !=sd.getCtaSubSegment() && !("".equals(sd.getCtaSubSegment())) && sd.getCtaSubSegment().length() > Constants.ZERO){
				String format = String.format("%%0%dd", 3);
				String subSegId= String.format(format, Integer.parseInt(sd.getCtaSubSegment()));
				sd.setCtaSubSegment(subSegId);
			}
            String taskId = sd.getCustomerNumber()+sd.getDivison()+segId+sd.getCtaSubSegment()+sd.getFyrFprdFwkFdy();
            if(null !=sd.getFrequency()){
			    if(sd.getFrequency().equals("Weekly"))
					taskId=taskId.substring(0,taskId.length() - 1);
				else if(sd.getFrequency().equals("Monthly"))
					taskId=taskId.substring(0,taskId.length() - 2);
			}
            String availableTaskId=checkAvaliblityforTaskId(taskId,sd.getCustomerNumber(),segId,sd.getCtaSubSegment());
			if (null != availableTaskId && !("".equals(availableTaskId))) {
				taskId = availableTaskId;
			} else {
				taskId = taskId = sd.getCustomerNumber() + sd.getDivison()
						+ segId + sd.getCtaSubSegment()
						+ sd.getFyrFprdFwkFdy();
			}
			
			String executedSDSQLString = CTAConstants.INSERT_SEGMENT_DETAIL;
			if(sd.getSfdcSentDate()==null || sd.getSfdcTaskId()==null){
				
				executedSDSQLString = CTAConstants.INSERT_SEGMENT_DETAIL_WITHOUT_SFDC;
			}
			SQLQuery insertSegDetSQL = session.createSQLQuery(executedSDSQLString);			
			insertSegDetSQL.setParameter(CTAConstants.TASK_ID_PARAM, taskId);
			/*old CTA comment comment*/
			//insertSegDetSQL.setParameter(CTAConstants.LINK_TASK_PARAM, sd.getLinkTask());
			insertSegDetSQL.setParameter(CTAConstants.CUST_NUM_PARAM, sd.getCustomerNumber());
			insertSegDetSQL.setParameter(CTAConstants.DIVISION_PARAM, sd.getDivison());
			insertSegDetSQL.setParameter(CTAConstants.CTA_SEG_PARAM, segId);
			insertSegDetSQL.setParameter(CTAConstants.CTA_SUB_SEG_PARAM, sd.getCtaSubSegment());
			insertSegDetSQL.setParameter(CTAConstants.FYR_FPRD_FWK_FDY_PARAM, sd.getFyrFprdFwkFdy());
			insertSegDetSQL.setParameter(CTAConstants.SEG_DISP_STATUS_PARAM, sd.getSegDispStatus());
			insertSegDetSQL.setParameter(CTAConstants.INSERT_DATE_PARAM, sd.getTaskInsertDate());
			if(sd.getSfdcTaskId()!=null){
			insertSegDetSQL.setParameter(CTAConstants.SFDC_TASK_ID, sd.getSfdcTaskId());
			}
			if(sd.getSfdcSentDate()!=null){
			insertSegDetSQL.setParameter(CTAConstants.SFDC_DATE_PARAM, sd.getSfdcSentDate());
			}
			if (null != sd.getRepSFDCId()) {
				if (sd.getRepSFDCId().equals(adminSFDCId.trim()))

				{

					insertSegDetSQL.setParameter(CTAConstants.CREATED_BY_PARAM, "Helios Admin");
					insertSegDetSQL.setParameter(CTAConstants.UPDATED_BY_PARAM, "Helios Admin");
					insertSegDetSQL.setParameter(CTAConstants.REFRESHED_BY_PARAM, "Helios Admin");
				} else {

					insertSegDetSQL.setParameter(CTAConstants.CREATED_BY_PARAM, sd.getCreatedBy());
					insertSegDetSQL.setParameter(CTAConstants.UPDATED_BY_PARAM, sd.getUpdatedBy());
					insertSegDetSQL.setParameter(CTAConstants.REFRESHED_BY_PARAM, sd.getRefreshedBy());
				}
			}
			//if(sd.getContactId()!=null && !("").equals(sd.getContactId()) && sd.getSfdcContactFullName() !=null && !("").equals(sd.getSfdcContactFullName())){
			 insertSegDetSQL.setParameter("contactId", sd.getContactId());
			 insertSegDetSQL.setParameter("sfdcContactFullName", sd.getSfdcContactFullName());
			 insertSegDetSQL.setParameter("subject", sd.getSubject());
			//}
			//insertSegDetSQL.executeUpdate();
			
			SQLQuery insertCommentSQL = session.createSQLQuery(CTAConstants.INSERT_SEGMENT_COMMENT);			
			insertCommentSQL.setParameter(CTAConstants.TASK_ID_PARAM, taskId);
			insertCommentSQL.setParameter(CTAConstants.INSERT_DATE_PARAM, comment.getInsertDate());
			insertCommentSQL.setParameter(CTAConstants.COMMENT_TEXT_PARAM, comment.getCommentText());
			if (null != sd.getRepSFDCId()) {
				if (sd.getRepSFDCId().equals(adminSFDCId.trim()))

				{
					insertCommentSQL.setParameter(CTAConstants.LOGGED_USER_PARAM, "Helios Admin");
				} else {
					insertCommentSQL.setParameter(CTAConstants.LOGGED_USER_PARAM, comment.getLoggedUser());
				}
			}
				
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(def);

			try{	    	
				insertSegDetSQL.executeUpdate();
				if(null !=comment && null !=comment.getCommentText() && !("".equals(comment.getCommentText())))
				insertCommentSQL.executeUpdate();
				transactionManager.commit(status);
				return CTAConstants.SUCCESS;
			}catch(DataAccessException exception){
				transactionManager.rollback(status);
				throw exception;
			}
			
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

	public String checkAvaliblityforTaskId(String taskComId,String custNum,String segId,String subSegId){
		String taskIdCombination=null;
		Session session = null;
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(SQLConstants.SQL_CHECK_TASKID_AVAILABILITY+" LIKE '"+taskComId+"%' AND CUSTOMER_NUMBER=? and CTA_SEGMENT=? and CTA_SUB_SEGMENT=? order by TASK_INSERT_DATE asc)where RowNum <=1");
			query.setParameter(0, custNum);
			query.setParameter(1, segId);
			query.setParameter(2, subSegId);
			taskIdCombination = (String)(query.uniqueResult());
			System.out.println("--->"+taskIdCombination);
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return taskIdCombination;
	}

	@Override
	public String addSegSubDetail(List<SegmentSubDetail> segmentSubDetails) {
		Session session = null;
		final List<SegmentSubDetail> insertSubDetails = new ArrayList<SegmentSubDetail>();
		final List<SegmentSubDetail> updateSubDetails = new ArrayList<SegmentSubDetail>();
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			
			for(SegmentSubDetail subDetail : segmentSubDetails){
				SQLQuery selectSegSubDtlSQL = session.createSQLQuery(CTAConstants.SELECT_TASKIDCOMBINATION_SEGMENT_SUB_DTL);
				selectSegSubDtlSQL.setParameter(CTAConstants.TASK_ID_COMBINATION_PARAM, subDetail.getTaskIdCombination());
				selectSegSubDtlSQL.setParameter(CTAConstants.HEADER_ID_PARAM, subDetail.getHeaderId());
				selectSegSubDtlSQL.setParameter(CTAConstants.PARAM_ID_PARAM, subDetail.getParamId());
				List record = selectSegSubDtlSQL.list();  
				if(record.size()>0){
					 updateSubDetails.add(subDetail);
				}else{
					insertSubDetails.add(subDetail) ; 
				}
			}
			if(insertSubDetails.size() > 0){
				
				session.doWork(new Work() {					
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement psmt = null;						
						try{
							
							String insertSQL = CTAConstants.INSERT_SEGMENT_SUB_DTL_PSMT;
							psmt = connection.prepareStatement(insertSQL);
							
							for(SegmentSubDetail subDetail : insertSubDetails){
								psmt.setString(1, subDetail.getTaskIdCombination());
								psmt.setString(2, subDetail.getCustomerNumber());
								psmt.setString(3, subDetail.getDivison());
								psmt.setString(4, subDetail.getCtaSegment());
								psmt.setString(5, subDetail.getCtaSubSegment());
								psmt.setString(6, subDetail.getFyrFprdFwkFdy());
								psmt.setString(7, subDetail.getCtaSegStatus());
								psmt.setString(8, subDetail.getTaskInsertDate());
								psmt.setString(9, subDetail.getHeaderId());
								psmt.setString(10, subDetail.getParamId());
								psmt.setString(11, subDetail.getParamLabel());
								psmt.setString(12, subDetail.getParamValue());
								psmt.setString(13, subDetail.getCreatedBy());
								psmt.setString(14, subDetail.getUpdatedBy());
								psmt.addBatch();
							}
							psmt.executeBatch();
						}finally{
							psmt.close();
						}
						
						
					}
				});
			}
			if(updateSubDetails.size() > 0){
				session.doWork(new Work() {
					
					@Override
					public void execute(Connection connection) throws SQLException {

						PreparedStatement psmt = null;						
						try{
							
							String insertSQL = CTAConstants.UPDATE_SEGMENT_SUB_DTL_PSMT;
							psmt = connection.prepareStatement(insertSQL);
							
							for(SegmentSubDetail subDetail : updateSubDetails){
								
								psmt.setString(1, subDetail.getCustomerNumber());
								psmt.setString(2, subDetail.getDivison());
								psmt.setString(3, subDetail.getCtaSegment());
								psmt.setString(4, subDetail.getCtaSubSegment());
								psmt.setString(5, subDetail.getFyrFprdFwkFdy());
								psmt.setString(6, subDetail.getCtaSegStatus());
								psmt.setString(7,subDetail.getTaskInsertDate());
								psmt.setString(8, subDetail.getParamLabel());
								psmt.setString(9, subDetail.getParamValue());
								psmt.setString(10, subDetail.getCreatedBy());
								psmt.setString(11, subDetail.getUpdatedBy());
								psmt.setString(12, subDetail.getTaskIdCombination());
								psmt.setString(13,subDetail.getHeaderId());
								psmt.setString(14, subDetail.getParamId());
								
								psmt.addBatch();
							}
							psmt.executeBatch();
						}finally{
							psmt.close();
						}									
					}
				});
			}
			
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return CTAConstants.SUCCESS;
	}

	@Override
	public SegHdrText addSegHrdText(SegHdrText segHdrText) {
		return segHdrText;/*
		Session session = null;
		try{
			SegHdrText segHdrTextDB = getSegHdrText(segHdrText.getSeg(), segHdrText.getSegId());
			String executedSQLString = segHdrTextDB == null ? CTAConstants.INSERT_SEG_HDR_TEXT : CTAConstants.UPDATE_SEG_HDR_TEXT;
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery executedSQL = session.createSQLQuery(executedSQLString);			
			executedSQL.setParameter(CTAConstants.SEG_PARAM, segHdrText.getSeg(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.SEG_ID_PARAM, segHdrText.getSegId(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HEADER_IDS_PARAM, segHdrText.getHeaderIdsShown(),StandardBasicTypes.STRING);
			executedSQL.setParameter(CTAConstants.TOTAL_HDRS_PARAM, segHdrText.getTotalHeaders(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_1_TXT_PARAM, segHdrText.getHdr1StaticText());
			executedSQL.setParameter(CTAConstants.HDR_1_PARAM, segHdrText.getHdr1ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_2_TXT_PARAM, segHdrText.getHdr2StaticText());
			executedSQL.setParameter(CTAConstants.HDR_2_PARAM, segHdrText.getHdr2ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_3_TXT_PARAM, segHdrText.getHdr3StaticText());
			executedSQL.setParameter(CTAConstants.HDR_3_PARAM, segHdrText.getHdr3ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_4_TXT_PARAM, segHdrText.getHdr4StaticText());
			executedSQL.setParameter(CTAConstants.HDR_4_PARAM, segHdrText.getHdr4ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_5_TXT_PARAM, segHdrText.getHdr5StaticText());
			executedSQL.setParameter(CTAConstants.HDR_5_PARAM, segHdrText.getHdr5ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_6_TXT_PARAM, segHdrText.getHdr6StaticText());
			executedSQL.setParameter(CTAConstants.HDR_6_PARAM, segHdrText.getHdr6ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_7_TXT_PARAM, segHdrText.getHdr7StaticText());
			executedSQL.setParameter(CTAConstants.HRD_7_PARAM, segHdrText.getHdr7ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_8_TXT_PARAM, segHdrText.getHdr8StaticText());
			executedSQL.setParameter(CTAConstants.HDR_8_PARAM, segHdrText.getHdr8ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_9_TXT_PARAM, segHdrText.getHdr9StaticText());
			executedSQL.setParameter(CTAConstants.HDR_9_PARAM, segHdrText.getHdr9ParamCount(),StandardBasicTypes.LONG);
			executedSQL.setParameter(CTAConstants.HDR_10_TXT_PARAM, segHdrText.getHdr10StaticText());
			executedSQL.setParameter(CTAConstants.HDR_10_PARAM, segHdrText.getHdr10ParamCount(),StandardBasicTypes.LONG);			
			executedSQL.executeUpdate();
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return segHdrText;
	*/}

	@Override
	public List<SegDetailInfo1> getSegDetails(String taskIdCombination) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SegDetailInfo1 getSegSingleDetail(String taskIdCombination) {
		Map<String, SegDetailInfo1> map = new HashMap<String, SegDetailInfo1>();
		SegDetailInfo1 segDetailInfo1 = null;
		Session session = null;
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			SQLQuery selectSegDetailSQL = session.createSQLQuery(CTAConstants.SELECT_SEGMENT_DETAIL);
			selectSegDetailSQL.setParameter("taskIdCombination", taskIdCombination);			
			List<Map<String, Object>> returnList = selectSegDetailSQL
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.list();
			if (null != returnList && returnList.size() > Constants.ZERO){
				for (Map<String, Object> rs : returnList) {
				    	  String taskIdCombinationDB = rs.get(CTAConstants.TASK_ID_COMBINATION).toString();
				    	  segDetailInfo1 = map.get(taskIdCombinationDB);
				    	  if(segDetailInfo1==null){
				  			segDetailInfo1 = new SegDetailInfo1();
				  			segDetailInfo1.setTaskIdCombination(rs.get(CTAConstants.TASK_ID_COMBINATION)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.TASK_ID_COMBINATION).toString());				  			
				  			segDetailInfo1.setSegDispStatus(rs.get(CTAConstants.SEG_DISP_STATUS)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.SEG_DISP_STATUS).toString());
				  			segDetailInfo1.setTaskInsertDate(rs.get(CTAConstants.TASK_INSERT_DATE)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.TASK_INSERT_DATE).toString());
				  			segDetailInfo1.setTaskUpdateDate(rs.get(CTAConstants.TASK_UPDATE_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.TASK_UPDATE_DATE).toString());
				  			segDetailInfo1.setSfdcSentDate(rs.get(CTAConstants.SFDC_SENT_DATE) == null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.SFDC_SENT_DATE).toString());
				  			segDetailInfo1.setCreatedBy(rs.get(CTAConstants.CREATED_BY)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.CREATED_BY).toString());
				  			segDetailInfo1.setUpdatedBy(rs.get(CTAConstants.UPDATED_BY)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.UPDATED_BY).toString());
				  			segDetailInfo1.setRefreshedBy(rs.get(CTAConstants.REFRESHED_BY) ==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.REFRESHED_BY).toString());
				  			map.put(taskIdCombinationDB, segDetailInfo1);
				  		}
				    	  List<Comment1> comment1s = segDetailInfo1.getComments();
				  		if(comment1s==null){
				  			comment1s = new ArrayList<Comment1>();
				  			segDetailInfo1.setComments(comment1s);							
				  		}
				  		Comment1 comment1 = new Comment1();
				  		comment1.setCommentText(rs.get(CTAConstants.COMMENT_TEXT).toString());
				  		comment1.setLoggedUser(rs.get(CTAConstants.LOGGED_USER).toString());
				  		comment1s.add(comment1);
				      }
				return map.get(taskIdCombination);
			}	
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return segDetailInfo1;
	}
	
	@Override
	public List<SegmentSubDetail> getSegSubDetail(String taskIdCombination) {
		Session session = null;
		List<SegmentSubDetail> subDetails = new ArrayList<SegmentSubDetail>();
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			/*SQLQuery sqlQuery = session.createSQLQuery(CTAConstants.SELECT_SEGMENT_SUB_DTL);
			sqlQuery.setParameter(CTAConstants.TASK_ID_COMBINATION_PARAM, taskIdCombination);*/
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CTAConstants.TASK_ID_COMBINATION_PARAM, taskIdCombination);
			for(Object object : getResultList(session, SQLConstants.SELECT_SEGMENT_SUB_DTL,params , new SegmentSubDetailMapper())){
				if(null != object){
					subDetails.add((SegmentSubDetail)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return subDetails;
	}


	@SuppressWarnings("unchecked")
	private List<Object> getResultList(Session session, String sql,
			Map<String, Object> params, CTABaseMapper rowMapper)
					throws HibernateException, Exception {
		List<Object> resultList = new ArrayList<Object>();
		try {
			if (session == null) {
				session = custExoHeliosOwnSessionFactory.openSession();
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			
			if(params != null && params.size() > 0){
				for(Entry<String, Object> entrySet : params.entrySet()){
					sqlQuery.setParameter(entrySet.getKey(), entrySet.getValue());
				}
			}


			List<Map<String, Object>> list = sqlQuery.setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP).list();
			if (list != null && !list.isEmpty()) {
				resultList = rowMapper.mapRow(list);
			}
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return resultList;
	}

	@Override
	public List<CustSfdcInfoVO> getCustSfdcInfo(String custNum) {

		Session session = null;
		List<CustSfdcInfoVO> infoVOs = new ArrayList<CustSfdcInfoVO>();
		try{
			session = custExoHeliosOwnSessionFactory.openSession();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CTAConstants.CUST_NUM_PARAM, custNum);
			for(Object object : getResultList(session, CTAConstants.SELECT_CUST_SFDC_INFO,params , new CustSfdcInfoMapper())){
				if(null != object){
					infoVOs.add((CustSfdcInfoVO)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return infoVOs;
	
	}
	
	@Override
	public List<String> getSubjectLabels() {
		Session session = null;
		List<String> subjectList = null;
		try{
			subjectList = new ArrayList<String>();
			session = custExoHeliosOwnSessionFactory.openSession();
            for(Object object :getResultList(session, CTAConstants.SELECT_SUBJECT_LABELS_LIST,
					null, new SubjectLabelMapper())){
				if(null != object){
					subjectList.add((String)object);
				}
			}
		}catch (HibernateException he) {
			throw new DashboardException(he);

		} catch (Exception e) {
			throw new DashboardException(e);

		} finally {
			if (session != null && session.isOpen())
				session.close();

		}
		return subjectList;
	}
}
