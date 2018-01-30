package com.staples.dashboard.app.dao;

import java.util.List;
import java.util.Set;

import com.staples.dashboard.app.vo.CustSfdcInfoVO;
import com.staples.dashboard.app.vo.SegDetailInfo1;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.SegmentVO;

public interface CTADAO {

	List<SegmentVO> getSegmentList();
	List<SegHeaderLabelsVO> getSegHdrLables();
	SegHdrText getSegHdrText(Long seg, Long segId);
	List<SegHeaderLabelsVO> getSegHdrLables(Set<Long> idsSet);
	String addSegmentDetail(SegmentDetailWrapper segmentDetailWrapper);
	String addSegSubDetail(List<SegmentSubDetail> segmentSubDetails);	
	SegHdrText addSegHrdText(SegHdrText segHdrText);
	List<SegDetailInfo1> getSegDetails(String taskIdCombination);
	SegDetailInfo1 getSegSingleDetail(String taskIdCombination);
	List<SegmentSubDetail> getSegSubDetail(String taskIdCombination);
	List<CustSfdcInfoVO> getCustSfdcInfo(String custNum);
	List<String> getSubjectLabels();

}
