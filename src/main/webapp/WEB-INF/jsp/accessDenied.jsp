
<!DOCTYPE HTML >
<html>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="java.util.HashMap"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Denied</title>
<style type="text/css">
#openerdialog {
      
}
</style>
 <style>
body {
   font: x-large/1.4 Georgia, "Times New Roman", Times, serif;
   text-align: center; }
#infobox {
   width: 400px;
   margin: 20px auto;
   padding: 20px;
   color: #333;
   background-color: #9fe1ff;
   border: 4px solid #333;
   /* Our massive border-radius stack. */
   -moz-border-radius: 10px;
   -webkit-border-radius: 10px;
   -o-border-radius: 10px;
   -khtml-border-radius: 10px;
   border-radius: 10px;
   -moz-box-shadow: 0 0 5px #333;
}
#infobox input {
   width: 80%;
}
.hasPlaceholder {
   color: #777;
}
</style> 
<style>
.form-control{
 height: 34px !important;
}
.btn{
 line-height: 34px !important;
}
div.modal-footer button{
 line-height: 24px !important;
 font-family:Helvetica;
}
</style>
<link href="./resources/stylesheet/style.css" rel="stylesheet">
</head>
<body style="vertical-align: baseline;background-color:#fff !important;" >
	
<% 
	Authentication auth = SecurityContextHolder.getContext()
	.getAuthentication();
	%>
	
	<div class="ui-tabs ui-widget ui-widget-content ui-corner-all" style="height:250px;"> 
<div class="container" style="background-color:#fff !important;margin-top:10%;">
			<div class="row">
				<div class="col-lg-12">
				<c:set var="deniedReason" value="${requestScope['ACCESSDENIED']}"></c:set>
				<c:if test="${empty deniedReason}">
					<div class="centering text-center error-container">
						<div class="text-center">
						
							<h2 class="without-margin"
								style="color: #000; line-height: 1; font-size: 30px; font-weight: 100; font-family: serif;">
								<big><img
									src="./resources/img/lock.png"
									width="40" height="35"></big> Access Denied<span
									class="text-warning"></span>
							</h2>
						</div>
						<div class="text-center">
							<h2 class="without-margin"
								style="color: #000; padding-top: 15px; padding-left: 45px; font-family: serif; font-size: 30px; font-weight: 100;">You
								Are Not Authorized to View This Page.</h2>
							<h4 class="text-warning"
								style="padding-left: 45px; font-size: 20px; font-family: serif; line-height: 1;">Please
								Contact The Administrator.</h4>
						</div>
						<div class="text-center" style="padding: 15px;">
							<h3
								style="padding-left: 45px; font-size: 28px; font-family: serif; line-height: 1;">
								<small style="color: #888;">Choose an option below</small>
							</h3>
						</div>
						<div
							style="width: 80%; padding-left: 30%; text-align: center !important;">
							<hr
								style="width: 85%; background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 23.5%, #f7fdca 18.5%, #f7fdca 50%, #fecf71 35%, #fecf71 74.5%, #f0776c 67.5%, #f0776c 30%); height: 4.9px;"
								class="colorgraph">
						</div>
						<div style="">
							<button type="button" id="faqBtnId"
								onclick="javascript:showTrainingPopUp();"
								class="btn btn-sm btn-success"
								style="height: 35px; background-color: #fff; border: none; box-shadow: none;">
								<a href="#" style="margin-top: -2px; float: right;"
									data-toggle="tooltip" title="Help / FAQ / Training"
									data-placement="right"> <!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
									<img src="./resources/img/Help.jpg" width="50" height="45"
									style="vertical-align: bottom;">
								</a>
							</button>
							<button type="button" id="" class="btn btn-sm btn-success"
								style="height: 35px; border: none; background-color: #fff; color: #cc0000; font-family: Helvetica; font-size: 16px; margin-left: 0px; cursor: default; box-shadow: none; padding-left: 0px;">Need
								help logging in?</button>
						</div>
					</div>
					</c:if>
					
					<!-- Access Denied ends -->
					<!-- Account Hold out start -->
					 <c:if test="${not empty deniedReason}">
					<div class="centering text-center error-container">
						<div class="text-center">
							<h2 class="without-margin" style="color: #000; line-height: 1; font-size: 30px; font-weight: 100; font-family: serif;">
								<big><img src="./resources/img/lock.png" width="40" height="35"></big> Account Temporarily Blocked<span class="text-warning"></span>
							</h2>
						</div>
						<div class="text-center">
							<h2 class="without-margin" style="color: #000; padding-top: 15px; padding-left: 45px; font-family: serif; font-size: 30px; font-weight: 100;">This Account is currently part of Helios Holdout test group</h2>
								
							<h4 class="text-warning" style="padding-left: 45px;font-size: 20px;font-family: serif;line-height: 2;">Please contact <a href="javascript:openOutlook('helios_sa_bussupt@staples.com');"" style=" text-decoration: underline;font-weight: normal !important;">Helios business</a> for test details.</h4>
						</div>
					</div>
					</c:if>
					<!-- Account Hold out close -->
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/AuthHelp.jsp"></jsp:include>

<script src="./resources/javascript/jquery.min.js"></script>
    <script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
function showTrainingPopUp() {
	$('#training_modal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function openOutlook(email) { 
	if(undefined != email && '' !=email)
    window.location.href ="mailto:"+email;
}
</script>
</body>
</html>