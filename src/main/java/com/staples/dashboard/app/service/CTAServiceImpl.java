package com.staples.dashboard.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staples.dashboard.app.dao.CTADAO;
import com.staples.dashboard.app.vo.CustSfdcInfoVO;
import com.staples.dashboard.app.vo.SegDetailInfo1;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentSubDetail;
import com.staples.dashboard.app.vo.SegmentVO;

@Service
public class CTAServiceImpl implements CTAService{

	@Autowired
	private CTADAO ctadao;
	
	@Override
	public List<SegmentVO> getSegmentList() {
		return ctadao.getSegmentList();		
	}

	@Override
	public List<SegHeaderLabelsVO> getSegHdrLables() {
		return ctadao.getSegHdrLables();
	}

	@Override
	public SegHdrText getSegHdrText(Long seg, Long segId) {
		return ctadao.getSegHdrText(seg, segId);
	}

	@Override
	public List<SegHeaderLabelsVO> getSegHdrLables(Set<Long> idsSet) {		
		return ctadao.getSegHdrLables(idsSet);
	}

	@Override
	public String addSegmentDetail(SegmentDetailWrapper segmentDetailWrapper) {
		return ctadao.addSegmentDetail(segmentDetailWrapper);
	}

	@Override
	public String addSegSubDetail(List<SegmentSubDetail> segmentSubDetails) {		
		return ctadao.addSegSubDetail(segmentSubDetails);
	}

	@Override
	public SegHdrText addSegHrdText(SegHdrText segHdrText) {
		return ctadao.addSegHrdText(segHdrText);
	}

	@Override
	public List<SegDetailInfo1> getSegDetails(String taskIdCombination) {
		return ctadao.getSegDetails(taskIdCombination);
	}

	@Override
	public SegDetailInfo1 getSegSingleDetail(String taskIdCombination) {
		return ctadao.getSegSingleDetail(taskIdCombination);
	}

	@Override
	public List<SegmentSubDetail> getSegSubDetail(String taskIdCombination) {		
		return ctadao.getSegSubDetail(taskIdCombination);
	}

	@Override
	public List<CustSfdcInfoVO> getCustSfdcInfo(String custNum) {
		return ctadao.getCustSfdcInfo(custNum);
	}
	
	@Override
	public List<String> getSubjectLabels() {		
		return ctadao.getSubjectLabels();
	}

}
