package com.staples.dashboard.app.dao.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.staples.dashboard.app.constants.CTAConstants;
import com.staples.dashboard.app.constants.MapperConstants;
import com.staples.dashboard.app.vo.SegMktgResources;

public class SegMktgResourcesMapper implements CTABaseMapper{
	private String mktResourceUrl;
	
	@Override
	public List<Object> mapRow(List<Map<String, Object>> resultMap) {
		List<Object> objects = null;
		SegMktgResources mktgResources=null;
		if (resultMap != null && !resultMap.isEmpty()) {
		      for (Map<String, Object> rs : resultMap) {
		        if (objects == null) {
		          objects = new ArrayList<Object>();
		        }
		        mktgResources = new SegMktgResources();
		        mktgResources.setSeg(rs.get(CTAConstants.SEG)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.SEG).toString());
		        mktgResources.setSegId(rs.get(CTAConstants.SEG_ID)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.SEG_ID).toString());
		        mktgResources.setParamId(rs.get(CTAConstants.PARAM_ID)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.PARAM_ID).toString());
		        mktgResources.setLabelPrefixText(rs.get(CTAConstants.LABEL_PREFIX_TEXT)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.LABEL_PREFIX_TEXT).toString());
		        mktgResources.setLabelName(rs.get(CTAConstants.LABEL_NAME)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.LABEL_NAME).toString());
		        mktgResources.setLabelSuffixText(rs.get(CTAConstants.LABEL_SUFFIX_TEXT)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.LABEL_SUFFIX_TEXT).toString());
		        mktgResources.setLabelHiddenUrl(rs.get(CTAConstants.LABEL_HIDDEN_URL)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.LABEL_HIDDEN_URL).toString());
		        mktgResources.setHeaderId(rs.get(CTAConstants.HEADER_ID)==null ? MapperConstants.BLANK_STRING : rs.get(CTAConstants.HEADER_ID).toString());
		        mktgResources.setBaseUrl((getMktResourceUrl())==null ? MapperConstants.BLANK_STRING : getMktResourceUrl().toString());
		        
				objects.add(mktgResources);
				
		      }
		    }
		    return objects;
	}

	/**
	 * @return the mktResourceUrl
	 */
	public String getMktResourceUrl() {
		return mktResourceUrl;
	}

	/**
	 * @param mktResourceUrl the mktResourceUrl to set
	 */
	public void setMktResourceUrl(String mktResourceUrl) {
		this.mktResourceUrl = mktResourceUrl;
	}

	
}
