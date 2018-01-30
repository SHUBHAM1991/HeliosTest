package com.staples.dashboard.app.sdc.vo;

import java.util.ArrayList;
import java.util.List;

public class ParentChildInfo {

	private String parentId;
	private List<String> childs;
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<String> getChilds() {
		if(childs==null){
			childs=new ArrayList<>();
		}
		return childs;
	}
	public void setChilds(List<String> childs) {
		this.childs = childs;
	}
}
