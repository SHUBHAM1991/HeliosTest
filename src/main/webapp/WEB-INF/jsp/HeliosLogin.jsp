<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="expires" content="0">
<title>Helios</title>
<link href="./resources/stylesheet/heliosLogin.css?helios_version=${initParam.helios_ver}" rel="stylesheet">
</head>
<body>

    <div class="container">
        <div class="row">
			<!-- NEW LOGIN START -->
			 <section id="content_sec1" style="margin-top:0%;">
				 <div class="thumbnail" style="padding: 15px;color: #4d4d4d !important;height: 150px;margin: 0 auto 0px;box-sizing: border-box;font-size: 62px;font-family: helvetica;border: none;padding-top: 20px;background:transparent;">
				  <img src="./resources/img/helios-logo.png">
				   <!-- <span style="color: red!important;">H</span><span style="color: #ffbb33 !important;">e</span><span style="color: #00C851 !important;">l</span><span style="color: #33b5e5 !important;">i</span><span>o</span><span style="color: #2BBBAD !important;">s</span> -->
				 </div>
			 </section>
				   <section id="content">
					<form role="form" action="" id="loginForm" method="post">
						<!-- <h1>Login Form</h1> -->
						<!-- <div class="thumbnail" style="color: rgb(255, 255, 255);background: deepskyblue; width: 150px;height: 150px;margin: 0 auto 30px; padding: 50px 30px;border-radius: 100% !important; box-sizing: border-box;font-size:28px; font-family:Helvetica">Helios</div> -->
						<div>
							<!-- <input type="text" placeholder="Username" required="" id="username"> -->
							<input type="text" placeholder="Please Enter Employee Number or Email Address" onkeypress="enterPressed(this, event);" name="reqCustNum" id="reqCustNum"  autofocus="" required="" >
                                     <input class="form-control"  name="from" id="from" type="hidden" value="${requestScope['from']}" />
                                     <input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
						</div>
						<div>
						</div>
                        <h6 style="color: #ababab;padding-top:5px;float:left;"><i class="fa fa-hand-o-right" aria-hidden="true" style="vertical-align: middle;"></i><span style="color: darkgray;padding-left: 5px;font-weight: normal;font-size: 13px;font-family: Roboto , sans-serif;text-transform:none;">For ASMs and higher, enter Employee Number.</span></h6>
						<div style="padding-top:0px;margin-top:7%;">
						<div style="margin-top: 2%;float:right;">
							<input type="button" id="custORBtnId" value="LOG IN" style="font-weight: normal;font-size: 18px;background: #da291c !important;border: none;border-radius: 4px !important;color: #fff;font-family: Helvetica;padding: 15px;">
						</div>
						<div style="padding-top:30px;">
							<a href="javascript:void();" onclick="javascript:showTrainingPopUp()" style="float:left;margin-left:-5px;"><img src="./resources/img/login_help.png" width="50" height="45"/></a>
							<a href="javascript:void();" onclick="javascript:showTrainingPopUp()" style="float:left;font-weight: normal;color: #da291c;font-size: 14px !important;ica;text-decoration: none;margin: 15px 0px 0 0;">Need help logging in?</a>
						</div>
						</div>
					</form><!-- form -->
					
					</section>
			<!-- NEW LOGIN ENDS -->
            </div>
        </div>
    </div>
    
    <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
	<jsp:include page="/WEB-INF/jsp/Help.jsp"></jsp:include>
 
   
    <!-- jQuery -->
    <script src="./resources/javascript/jquery.min.js?helios_version=${initParam.helios_ver}"></script>
    <script src="./resources/javascript/jquery-ui.min.js" type="text/javascript?helios_version=${initParam.helios_ver}"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/bower_components/bootstrap/dist/js/bootstrap.min.js?helios_version=${initParam.helios_ver}"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="./resources/bower_components/metisMenu/dist/metisMenu.min.js?helios_version=${initParam.helios_ver}"></script>

    <!-- Custom Theme JavaScript -->
    <script src="./resources/dist/js/sb-admin-2.js?helios_version=${initParam.helios_ver}"></script>
    <script src="./resources/javascript/user-activity.js?helios_version=${initParam.helios_ver}" type="text/javascript"></script>
    <script src="./resources/javascript/jquery.cookie.js?helios_version=${initParam.helios_ver}" type="text/javascript"></script>
    <script src="./resources/javascript/global-default-exception-handler.js?helios_version=${initParam.helios_ver}" type="text/javascript"></script>
<script>

 var ctx =$("#svsURL").val();
 $(function(){
	 var keyStop = {
	   8: ":not(input:text, textarea, input:file, input:password)", // stop backspace = back
	   13: "input:text, input:password", // stop enter = submit 

	   end: null
	 };
	 $(document).bind("keydown", function(event){
	  var selector = keyStop[event.which];

	  if(selector !== undefined && $(event.target).is(selector)) {
	      event.preventDefault(); //stop event
	  }
	  return true;
	 });
	 
	// log user activity; View Login Page
	logUserActivity(4001, 'View Login Page');
	if($(window).width()>1700){
		$(".imgClass").attr("style","height:62px;");
	}
	$("#reqCustNum").focus();
	});
 function login(){ 
	 
	 var custId=($("#reqCustNum").val()).trim();
	if(custId == '' || custId == null) {
		alert("Please enter Employee Number or Email Address");
		return;
	}
	 
	 var customerId=null;
	 
	  var formData={emailId:custId};
	  $.ajax({
			url : ctx+"/getAccountNumber",
			type : "POST",
			cache : false,
			async:false,
			data : formData,
			timeout : 1000000,
			async : false,
			success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					if(data.indexOf("##")!=-1){
						customerId=data.split("##")[0];
						assignAccType=data.split("##")[1];
					}else{
						customerId=data;	
					}
				} else {
					alert("Employee Number or Email Address is not valid. Please try again.");
					return;
				}
				
				
			 	var year='2014';
				var from=$("#from").val();
				if(custId !='' && custId !=undefined && year !='' && year != undefined) {
					if(undefined==from || null==from || ''==from || 'home' !=from){ 
					$("#loginForm").attr("action","./cust_profiles")
					if(customerId != null && customerId != undefined){
						$("#reqCustNum").val(customerId);
					}
					else{
					$("#reqCustNum").val(custId);
					}
					$("#reqYear").val(year);
					  $('#loginForm').submit();
					}else if(from=='home'){ 
						$("#loginForm").attr("action","./home_cust_profiles")
						
						if(customerId != null && customerId != undefined){
							$("#reqCustNum").val(customerId);
						}
						else{
						$("#reqCustNum").val(custId);
						}
						$("#reqYear").val(year);
						  $('#loginForm').submit();
					}
				}
					
			}

		});
	   
 }
 
function loginOR(){ 
    var custId=($("#reqCustNum").val()).trim();
	if(custId == '' || custId == null) {
		alert("Please enter Employee Number or Email Address");
		return;
	}
	var customerId=null;
	var formData={emailId:custId};
	var assignAccType=null;
	  $.ajax({
			url : ctx+"/getAccountNumber",
			type : "POST",
			cache : false,
			async:false,
			data : formData,
			timeout : 1000000,
			async : false,
			success : function(data, textStatus, jqXHR) { 
				if	(data !=null && data != undefined){ 
					if(data.indexOf("##")!=-1){
						customerId=data.split("##")[0];
						assignAccType=data.split("##")[1];
						if(undefined !=assignAccType && (assignAccType.indexOf("SAM-AM")!=-1 || (assignAccType.indexOf("SAM-")!=-1 && assignAccType.indexOf("MM-")!=-1) ) ){
							assignAccType='AM_SAM';
						}else if(((assignAccType.indexOf("SAM-B2B")!=-1 || assignAccType.indexOf("SAM-SDR")!=-1 || assignAccType.indexOf("SAM-IBD")!=-1)) && assignAccType.indexOf("MM-")==-1){
							assignAccType='OTHER_SAM';
						}else if(assignAccType.indexOf("SAM-")==-1 && assignAccType.indexOf("MM-")!=-1){
							assignAccType='OTHER_MM';
						}
					}else{
						customerId=data;	
					}
					
				} else {
					alert("Employee Number or Email Address is not valid. Please try again.");
					return;
				}
			 	var year='2014';
				var from=$("#from").val();
				if(custId !='' && custId !=undefined && year !='' && year != undefined) {
					if(from=='home'){ 
						$("#loginForm").attr("action","./cp_online_retail")
						
						if(customerId != null && customerId != undefined){ 
							$("#reqCustNum").val(customerId);
							$("#assignType").val(assignAccType);
						}
						else{
						$("#reqCustNum").val(custId);
						$("#assignType").val(assignAccType);
						}
						$("#reqYear").val(year);
						  $('#loginForm').submit();
					}
				}
					
			} // end
		});
 }
 $("#custBtnId").click(function(){ 
		loginOR();
	 });
 $("#custORBtnId").click(function(){ 
	    removeCookie();
		loginOR();
	 });
 $(document).keyup(function(event) {
	 var keycode = (event.keyCode ? event.keyCode : event.which);
	    if (keycode == '13') { 
	    	removeCookie();
	    	loginOR();
	    }

	});
 function removeCookie(){
	 var elems = -1;
	 var deleteElem;
	 $.each( localStorage, function( key,value ){
	     elems++;
	     // define which element to delete
	     if(elems == 0){ deleteElem = key }
	 });
	 if(undefined!= deleteElem && ''!=deleteElem)
	 localStorage.removeItem(deleteElem);
 }
 function enterPressed(value, event) { 
	   /*  var keycode = (event.keyCode ? event.keyCode : event.which);
	    if (keycode == '13') {
	    	login();
	    } */
	}
 function showTrainingPopUp() {
		$('#training_modal').modal({
			"backdrop"  : "static",
	        handle: ".modal-header",
			"keyboard"  : true,
			"show"      : true           
		});
	}
</script>
</body>
</html>
