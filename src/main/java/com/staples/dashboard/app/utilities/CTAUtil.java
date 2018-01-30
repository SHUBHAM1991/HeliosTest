package com.staples.dashboard.app.utilities;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.staples.dashboard.app.vo.SegmentComments;
import com.staples.dashboard.app.vo.SegmentDetail;

public class CTAUtil {

	public static String convertToString(Clob clob){
		String value = null;
		try {
			if(clob!=null)
				value = clob.getSubString(1, (int) clob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	public static SegmentComments populateSegmentCommentBean(String comment,
			String user, Timestamp timestamp) {
		SegmentComments segmentComment = new SegmentComments();
		segmentComment.setCommentText(comment);
		segmentComment.setLoggedUser(user);
		segmentComment.setInsertDate(timestamp);
		return segmentComment;
	}

	public static SegmentDetail populateSegmentDetailBean(String linkId,
			String custNum, String divison, String ctaName, String subSegId,
			String fyrFprdFwkFdy, String dispStatus, String user, Timestamp timestamp,String contactId , String sfdcContactFullName,String frequency ,String subject) {
		SegmentDetail segmentDetail = new SegmentDetail();
		segmentDetail.setLinkTask(linkId);
		segmentDetail.setCustomerNumber(custNum);
		segmentDetail.setDivison(divison);
		segmentDetail.setCtaSegment(ctaName);
		segmentDetail.setCtaSubSegment(subSegId);
		segmentDetail.setFyrFprdFwkFdy(fyrFprdFwkFdy);		
		segmentDetail.setSegDispStatus(dispStatus);
		segmentDetail.setCreatedBy(user);
		segmentDetail.setUpdatedBy(user);
		segmentDetail.setRefreshedBy(user);
		segmentDetail.setTaskInsertDate(timestamp);
		segmentDetail.setTaskUpdateDate(timestamp);
		segmentDetail.setFrequency(frequency);
		segmentDetail.setContactId(contactId);
		segmentDetail.setSfdcContactFullName(sfdcContactFullName);
		//segmentDetail.setSfdcSentDate(timestamp);
		segmentDetail.setSubject(subject);//newly added
		return segmentDetail;
	}
}
