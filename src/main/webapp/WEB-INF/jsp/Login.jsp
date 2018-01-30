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
<%-- <link href="./resources/stylesheet/login.css?helios_version=${initParam.helios_ver}" rel="stylesheet"> --%>
<%-- <link href="./resources/stylesheet/style.css?helios_version=${initParam.helios_ver}" rel="stylesheet"> --%>
<link href="./resources/stylesheet/heliosLogin.css?helios_version=${initParam.helios_ver}" rel="stylesheet">
</head>
<body>

    <div class="container">
        <div class="row">
          <%--<div id="loginContainer" class=" col-lg-8 col-md-9 col-sm-10 loginContainer" style="">
            <div style="" class=" imgClass login-panel panel panel-defaul">
            <!-- <img src="./resources/img/home_logo.png" width="150" height="50"> -->
            </div>
                <div style="border:1px solid #ccc;margin-left:25%;margin-top:10px;position:relative;" class="login-panel panel panel-defaul">
                    <div class="panel-heading">
                        <!-- <h3 class="panel-title">Please enter Employee Number or Email ID.</h3> -->
                        <h3 class="panel-title" style="font-family: ARIALMT;font-size:15px;font-weight: 400;line-height: 1.1;color:#444;">Enter Sales Rep Email Id or Employee Number.</h3>
                        <h3 class="panel-title" style="margin-top: 5px;font-family: ARIALMT;font-size:15px;font-weight: 400;line-height: 1.1;color:#444;">For ASMs and above, enter Employee number.</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="" id="loginForm" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Please enter Employee Number or Email ID." onkeypress="enterPressed(this, event);" name="reqCustNum" id="reqCustNum" type="text" autofocus="" style="border-color: #ccc;height:40px;">
                                     <input class="form-control"  name="from" id="from" type="hidden" value="${requestScope['from']}" />
                                     <input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                               
                                <!-- <button type="button" id="custBtnId" class="btn btn-lg btn-success btn-block" style="font-family:Helvetica;">Login
                            </button> -->
                            <button type="button" id="custORBtnId" class="btn btn-lg btn-success btn-block" style="background-color: rgb(255, 158, 1);margin: 0;font-family:Helvetica;border-color: goldenrod;padding:7px;"> Login </button>
                            </fieldset>
                        </form>
                         
                    </div>
                </div>
				<div
					style="margin-left: 25%; margin-top: -20px; position: relative;"
					class="login-panel panel panel-defaul">

					<div class="panel-body"
						style="padding-bottom: 0px; padding-top: 10px; padding-left: 0px;">

						<div style="">
							<button type="button" id="faqBtnId" onclick="javascript:showTrainingPopUp();"
								class="btn btn-sm btn-success"
								style=" height: 35px; background-color: #fff;border:none;box-shadow:none;">
								<a href="#" style="margin-top: -2px; float: right;"
									data-toggle="tooltip" title="Help / FAQ / Training"
									data-placement="right"> <!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
									<img src="./resources/img/Help.jpg" width="50" height="45"
									style="vertical-align: bottom;">
								</a>
							</button>
							<button type="button" id=""
								class="btn btn-sm btn-success"
								style="height: 35px; border: none; background-color: #fff; color: #cc0000; font-family: Helvetica; font-size: 16px;margin-left:0px;cursor:default;box-shadow:none;padding-left:0px;">Need help logging in?
								</button>
						</div>
					</div>
				</div>
			</div>--%>
			
			<!-- NEW LOGIN START -->
				   <section id="content" style="margin-top:10%;">
					<form role="form" action="" id="loginForm" method="post">
						<!-- <h1>Login Form</h1> -->
						<!-- <div class="thumbnail" style="color: rgb(255, 255, 255);background: deepskyblue; width: 150px;height: 150px;margin: 0 auto 30px; padding: 50px 30px;border-radius: 100% !important; box-sizing: border-box;font-size:28px; font-family:Helvetica">Helios</div> -->
						<!-- <div class="thumbnail" style="color: crimson;background: rgb(255, 255, 255);height: 150px;margin: 0 auto 30px;box-sizing: border-box;font-size: 62px;font-family: cursive;border: none;">
						<span>H</span><span style="color: #ffbb33 !important;">e</span><span style="color: #00C851 !important;">l</span><span style="color: #33b5e5 !important;">i</span><span>o</span><span style="color: #2BBBAD !important;">s</span></div> -->
						<div class="thumbnail" style="color: #4d4d4d !important;background: rgb(255, 255, 255);height: 150px;margin: 0 auto 30px;box-sizing: border-box;font-size: 62px;font-family: helvetica;border: none;padding-top: 20px;">
						<span style="color: #4d4d4d;">Helios</span> 
						 <!-- <span style="color: red!important;">H</span><span style="color: #ffbb33 !important;">e</span><span style="color: #00C851 !important;">l</span><span style="color: #33b5e5 !important;">i</span><span>o</span><span style="color: #2BBBAD !important;">s</span> -->
						 </div>
						<div>
							<!-- <input type="text" placeholder="Username" required="" id="username"> -->
							<input type="text" placeholder="Please enter Employee Number or Email ID." onkeypress="enterPressed(this, event);" name="reqCustNum" id="reqCustNum"  autofocus="" required="" >
                                     <input class="form-control"  name="from" id="from" type="hidden" value="${requestScope['from']}" />
                                     <input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
						</div>
						<div>
							<!-- <input type="password" placeholder="Password" required="" id="password"> -->
						</div>
						<h6 style="padding-top: 15px;color: #ababab;"><i class="fa fa-hand-o-right" aria-hidden="true" style="vertical-align: middle;box-xhadow: none;color:#FF8800"></i><span style="color: #FF8800;padding-left: 5px;text-transform: capitalize;">Enter Sales Rep Email Id or Employee Number.</span></h6>

                        <h6 style="color: #ababab;padding-top:5px;"><i class="fa fa-hand-o-right" aria-hidden="true" style="vertical-align: middle;box-xhadow: none;color:#FF8800"></i><span style="color: #FF8800;padding-left: 5px;text-transform: capitalize;">For ASMs and above, enter Employee number.</span></h6>
						<div style="padding-top:20px">
							<input type="button" id="custORBtnId" value="Log in" style="box-shadow: 0 4px 1px 0 rgba(0,0,0,.2), 0 1px 0px 0 rgba(0,0,0,.19);background:crimson !important;border: none;color: #fff;">
							<a href="javascript:void();" onclick="javascript:showTrainingPopUp()">Help</a>
							<a href="javascript:void();" style="text-decoration: none;cursor: default;">Need help logging in?</a>
						</div>
					</form><!-- form -->
					<div class="button">
						<a href="#">Download source file</a>
					</div><!-- button -->
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
 <!-- <form id="loginForm" name="loginForm" action="">
      <input type="hidden" id="reqCustNum" name="reqCustNum" />
       <input type="hidden" id="reqYear" name="reqYear" />
  </form> -->
   
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
/*  $("#custBtnId").click(function(){
	var custId=$("#reqCustNum").val();
	var year='2014';
	if(custId !='' && custId !=undefined && year !='' && year !=undefined){
		$("#loginForm").attr("action","./home_template1")
		$("#reqCustNum").val(custId);
		$("#reqYear").val(year);
		  $('#loginForm').submit();
	}
 }); */
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
	 //alert(custId)
	if(custId == '' || custId == null) {
		alert("Please enter Employee Number or Email ID.");
		return;
	}
	 
	 var customerId=null;
	 
	  var formData={emailId:custId};
	  $.ajax({
			url : ctx+"/getAccountNumber",
			
			//url : "/getLatestFiscalDate/"+custid,
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
					alert("Employee Number or Email ID entered is not valid. Please enter correct Employee Number or Email ID.");
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
					
			} // end success

		});
	   
 }
 
function loginOR(){ 
    var custId=($("#reqCustNum").val()).trim();
	if(custId == '' || custId == null) {
		alert("Please enter Employee Number or Email ID.");
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
					alert("Employee Number or Email ID entered is not valid. Please enter correct Employee Number or Email ID.");
					return;
				}
				
				//alert(customerId+"assign type="+assignAccType);
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
	    } <input id="fname" class="form-control" type="text" required="" oninvalid="this.setCustomValidity('Please Enter valpiopiopoiid email')"> */
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
