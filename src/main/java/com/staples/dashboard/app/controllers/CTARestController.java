package com.staples.dashboard.app.controllers;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.service.CTAService;
import com.staples.dashboard.app.utilities.JsonUtil;
import com.staples.dashboard.app.vo.SegHdrText;
import com.staples.dashboard.app.vo.SegHeaderLabelsVO;
import com.staples.dashboard.app.vo.SegmentComments;
import com.staples.dashboard.app.vo.SegmentDetail;
import com.staples.dashboard.app.vo.SegmentDetailWrapper;
import com.staples.dashboard.app.vo.SegmentVO;

@RestController
public class CTARestController {

	@Autowired
	private CTAService ctaService;
	
	@RequestMapping(method=RequestMethod.GET,value=CTAConstants.GET_SEGMENT_LIST)
	public List<SegmentVO> getSegmentList(){		
		List<SegmentVO> segmentVO = ctaService.getSegmentList();		
		return segmentVO;		
	}
	
	@RequestMapping(method=RequestMethod.GET,value=CTAConstants.GET_SEG_HDR_LABLES)
	public List<SegHeaderLabelsVO> getSegHdrLables(){
		List<SegHeaderLabelsVO> segHeaderLabelsVOs = ctaService.getSegHdrLables();
		return segHeaderLabelsVOs;
	}

	
	@RequestMapping(method=RequestMethod.GET,value=CTAConstants.GET_SEG_HDR_TEXT)
	public ResponseEntity<SegHdrText> getSegHdrText(@PathVariable Long seg, @PathVariable Long segId){
		
		SegHdrText segHdrText = ctaService.getSegHdrText(seg, segId);	
		if(segHdrText == null)
			return new ResponseEntity<SegHdrText>(HttpStatus.NOT_FOUND);
		String segHdrLables = segHdrText.getHeaderIdsShown();
		List<String> hdrIdsList = Arrays.asList(segHdrLables.split(","));
		Set<Long> hdrIds = new HashSet<Long>();
		for(String hdrId : hdrIdsList){
			hdrIds.add(Long.valueOf(hdrId));
		}	
		List<SegHeaderLabelsVO> labelsVOs = ctaService.getSegHdrLables(hdrIds);
		segHdrText.setHdrLables(labelsVOs);

		return new ResponseEntity<SegHdrText>(segHdrText,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST , value=CTAConstants.ADD_SEGMENT_DATA)
	public ResponseEntity<String> addSegmentDetail(@RequestBody SegmentDetailWrapper segmentDetailWrapper){
		
		if(segmentDetailWrapper.getSegDetail().getTaskIdCombination() == null || 
				segmentDetailWrapper.getComment().getTaskIdCombination() == null){
			return new ResponseEntity<String>("TaskIdCombination cannot be null",HttpStatus.NOT_ACCEPTABLE);
		}
		String status = ctaService.addSegmentDetail(segmentDetailWrapper);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}
}
