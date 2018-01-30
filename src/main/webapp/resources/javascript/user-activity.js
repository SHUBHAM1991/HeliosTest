function logUserActivity (pageNumber, pageDescription) {
	
	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	var customerNumber = $('#customerNumber').val();
	
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			customerNumber:customerNumber,
			pageNumber:pageNumber,
			pageDescription:pageDescription
			};
	
	$.ajax({
		url : ctx+"/logUserActivity",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			//do nothing
		}
	});
}
function logUserActivityDotCom (pageNumber, pageDescription) {
	
	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	var customerNumber = $('#customerNumber').val();
	
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			customerNumber:customerNumber,
			pageNumber:pageNumber,
			pageDescription:pageDescription
			};
	
	$.ajax({
		url : ctx+"/logUserActivityDotCom",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			//do nothing
		}
	});
}
function logChangeSatusActivity (pageNumber, pageDescription,custId) {
	
	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	var customerNumber = custId;
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			customerNumber:customerNumber,
			pageNumber:pageNumber,
			pageDescription:pageDescription
			};
	
	$.ajax({
		url : ctx+"/logUserActivity",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			//do nothing
		}
	});
}

function logUserActivityWithCallBack (pageNumber, pageDescription,custId) {
	
	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	var customerNumber = custId;
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			customerNumber:customerNumber,
			pageNumber:pageNumber,
			pageDescription:pageDescription
			};
	
	$.ajax({
		url : ctx+"/logUserActivity",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			getLatestPageRefreshTimestamp();
		}
	});
}