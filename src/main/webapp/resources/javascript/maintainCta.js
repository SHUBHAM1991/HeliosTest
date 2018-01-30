var ctx = $("#svsURL").val();

$(document).ready(function() {
	$('#subSegFormId').css("display", "none");
	$('#commentSectionId').remove();
	$('#modalFooterId').remove();
	$('#modalFooterId').remove();
	$('#modalFooterConfigId').css("display", "block");
	$("#closeCallToAction").unbind('click');
	$('#closeCallToAction').click(function(){
	    	$('#callToAction_PopUp').modal('hide');
	   
	});
	findSegments();
	configEditor();
	/*$(document).keydown(function (e) {
		alert(1)
	    var element = e.target.nodeName.toLowerCase();
		alert(element+"--"+e.target.id);
	    if ((element != 'input' && element != 'textarea') || $(e.target).attr("readonly") || (e.target.getAttribute("type") ==="checkbox")) {
	    	alert("in")
	        if (e.keyCode === 8) {
	        	alert("2")
	            return false;
	        }
	    }
	});*/
});

function findSegments() {
	var urlNew = ctx + "/api/findSegments"
	$.ajax({
		dataType : "json",
		url : urlNew,
		type : "GET",
		cache : false,
		timeout : 1500000,
		contentType : "application/json",
		success : function(data, textStatus, jqXHR) {
			if (data != undefined && data != null && data != "") {
				var segments = $('#segNameId');
				segments.empty();
				segments.append($('<option/>', {
					value : -1,
					text : '--select--'
				}));
				var count = 0;
				for (count = 0; count < data.length; count++) {
					var optionVal = '';
					var optionTxt = '';
					if (data[count].indexOf("-") != -1) {
						optionVal = titleCase((data[count].split("-")[1])
								.toLowerCase());
						optionTxt =(data[count].split("-")[0])+'-'+ titleCase((data[count].split("-")[1])
								.toLowerCase());
					}
					segments.append($('<option/>', {
						value : optionTxt,
						text : optionVal
					}));
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
}

function segChange() {
	var segVal = $("#segNameId").val();
	var segName='NA';
	if(undefined !=segVal && '' != segVal && segVal.indexOf("-")!=-1){
		segName=segVal.split("-")[1];
	}
	var urlNew = ctx + "/api/findSegmentDescs/" + segName
	$.ajax({
		dataType : "json",
		url : urlNew,
		type : "GET",
		cache : false,
		timeout : 1500000,
		contentType : "application/json",
		success : function(data, textStatus, jqXHR) {
			$('#subSegFormId').css("display", "block");
			if (data != undefined && data != null && data != "") {
				var segmentDesc = $('#segDescId');
				segmentDesc.empty();
				segmentDesc.append($('<option/>', {
					value : -1,
					text : '--select--'
				}));
				var count = 0;
				for (count = 0; count < data.length; count++) {
					var optionVal = '';
					if (data[count].indexOf("-") != -1) {
						optionVal = (data[count].split("-")[0]);
						optionTxt = data[count].split(/-(.*)/)[1];
					}
					segmentDesc.append($('<option/>', {
						value : optionVal,
						text : optionTxt
					}));
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
}

function titleCase(val) {
	return val.replace(/(^|\s)([a-z])/g, function(m, p1, p2) {
		return p1 + p2.toUpperCase();
	});
};

function openSubCallToAction() {
	var ctaName = ($("#segNameId").val()).split("-")[1];
	var segId = $("#segDescId").val();
	var subSegDesc = $("#segDescId option:selected").text();
	var category=($("#segDescId option:selected").text()).split('##')[1];
	openSubCallToActionConfig(subSegDesc, ctaName, segId,category);
}

function openSubCallToActionConfig(subSegDesc, ctaName, segId,category) {
	alert(category)
	
	var status = validateActivity(ctaName, segId);
	if (undefined != status && true == status) {
		var lablesIds = new Array();
		var ctaNameDesc = '<span id="ctaName" style="color:#006699;font-size:16px;font-weight:bold;">'
				+ ctaName
				+ ':</span>'
				+ '<span id="ctaSegDesc" data-placement="bottom" data-toggle="tooltip" title="'
				+ subSegDesc
				+ '" style="color:#333333;font-size:16px;font-weight:bold;"> '
				+ truncateDataWithLen(subSegDesc, 60) + '</span>';
		$("#ctaNameDesc").html(ctaNameDesc);
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		var selDesposition = '';
		var formData = {
			SubSegDesc : subSegDesc,
			CtaName : ctaName,
			SegId : segId
		};
		$
				.ajax({
					dataType : "json",
					url : ctx + "/openSubCallToActionConfig",
					type : "POST",
					cache : false,
					data : formData,
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {
						if (undefined != data && null != data && "null" != data) {
							generateTalkingPoints(data);
							$("[id^='param']").each(function () {
												if (undefined != this.id && '' != this.id
														&& undefined != this.value && '' != this.value
														&& undefined != this.value.length && this.value.length > 25)
													$("#" + (this.id)).attr("style","width:"+ ((this.value.length + 1) * 8)	+ 'px !important; '+
															" vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;");
												else if (undefined != this.id && '' != this.id)
													$("#" + (this.id)).attr("style"," width:200px !important;vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;");
															
							});
						} else {
							$(".bhoechie-tab-container").html();
							$(".bhoechie-tab-container").attr("style",
									"webkit-box-shadow:none;box-shadow:none;");
							$(".bhoechie-tab-container")
									.html(
											'<div style="margin: 100px 0px 100px 0px;padding: 15px; text-align: center; font-size: 18px;font-weight: bold;opacity: .3;">NO DATA AVAILABLE</div>');
						}
						showCallToActionPopUp();
					}
				});
	} else {
		return;
	}
}

function showCallToActionPopUp() {
	$('#callToAction_PopUp').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}

function validateActivity(ctaName, segId) {
	if (undefined == ctaName || '-1' == ctaName) {
		alert("please select segment to proceed further");
		return false;
	} else if (undefined == segId || '-1' == segId) {
		alert("please select sub segment to proceed further");
		return false;
	} else
		return true;
}

function configEditor() {
	$("#txtEditorRat").Editor();
	$("#txtEditorExp").Editor();
	$("#txtEditorExpObj").Editor();
	$("#txtEditorDrill").Editor();
	$("#txtEditorProp").Editor();
	$("#txtEditorMark").Editor();
	$("#txtEditorMisc").Editor();
}

function updateHeaderContent(dialogToClose,headerToUpdate,headerIds){
	alert("headerIds"+headerIds)
	$("[id^='param']").prop("disabled",true);
	var segId = ($("#segNameId").val()).split("-")[0];
	var subSegId = $("#segDescId").val();
	var subSegDesc = $("#segDescId option:selected").text();
	var category=($("#segDescId option:selected").text()).split('##')[1];
	alert('alert='+category)
	/* FOR CTA*/
	var headerContent=getTextWithSpecialHtmlEntities($("#"+headerToUpdate).html());
	/* FOR ALERT*/
	alert(headerContent)
	//var headerContent=getTextWithSpecialHtmlEntities($("#alertText").html());
	var colId=headerToUpdate.match(/\d+/g).map(Number);
	alert("headerToUpdate= "+headerToUpdate+" colIdHHHH="+colId)
	//return;
	var attributes=getParamLabels(zeroPad(colId,2),category);
		var formData = {
				segId:segId,
				subSegId:subSegId,
				headerIds:headerIds,
				headerCol:colId[0],
				headerContent:headerContent,
				attributes:attributes
				};
		
		$.ajax({
			url : ctx+"/updateCtaContent",
			type : "POST",
			cache : false,
			async:false,
			data : formData,
			dataType : "text",
			timeout : 1000000,
			async : false,
			success : function(data, textStatus, jqXHR) {
				//do nothing
				$('#'+dialogToClose).modal('hide');
				//$("[id^='param']").prop("disabled",true);
				if(undefined ==data || ''==data || 'null' ==data || null !=data || data <=0)
					$("[id^='param']").prop("disabled",false);
				$("[id^='param']").each(function () {
					if (undefined != this.id && '' != this.id
							&& undefined != this.value && '' != this.value
							&& undefined != this.value.length && this.value.length > 25)
						$("#" + (this.id)).attr("style","width:"+ ((this.value.length + 1) * 8)	+ 'px !important; '+
								" vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;");
					else if (undefined != this.id && '' != this.id)
						$("#" + (this.id)).attr("style"," width:200px !important;vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;");
								
                });
				alert("Changes Saved");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("[id^='param']").prop("disabled",false);
			}
		});
	
}

function getParamLabels(headerId,category){
	alert(category+'in headerId='+headerId)
	var labelValString='';
	if(category=='NA'){
	$(".Editor-editor[contenteditable='true']").find(":input[id^='paramKey_"+headerId+"']").each(function(i,item){
	alert(category+"-->"+this.id);
	var hdrIdprmId='';
    var label='';
    var val='';
	if(undefined!=this.id && (this.id).indexOf("_")!=-1){
		hdrIdprmId=(this.id).split("_")[1]+'_'+(this.id).split("_")[2];
		label=$("#paramKey_"+headerId+'_'+(this.id).split("_")[2]).val();
		val=$("#paramVal_"+headerId+'_'+(this.id).split("_")[2]).val();
	}
	
	var hdr_Prm_label_Val=hdrIdprmId+'~'+label+''+'#^#'+val;
	labelValString=labelValString+hdr_Prm_label_Val+',';
	});
}else if(category=='Churn' || category=='Decliner'){
	$(".Editor-editor[contenteditable='true']").find(":input[id^='paramKey"+category+"_"+headerId+"']").each(function(i,item){
		alert(category+"-->"+this.id);
		var hdrIdprmId='';
	    var label='';
	    var val='';
		if(undefined!=this.id && (this.id).indexOf("_")!=-1){
			hdrIdprmId=(this.id).split("_")[1]+'_'+(this.id).split("_")[2];
			label=$("#paramKey_"+headerId+'_'+(this.id).split("_")[2]).val();
			val=$("#paramVal_"+headerId+'_'+(this.id).split("_")[2]).val();
		}
		
		var hdr_Prm_label_Val=hdrIdprmId+'~'+label+''+'#^#'+val;
		labelValString=labelValString+hdr_Prm_label_Val+',';
		});
}
	if(''!=labelValString && labelValString.indexOf(",")!=-1){
		labelValString=labelValString.substring(0,labelValString.lastIndexOf(","));
	}
	return labelValString;
}