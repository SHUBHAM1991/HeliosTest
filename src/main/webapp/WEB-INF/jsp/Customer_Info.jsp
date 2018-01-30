<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title>Helios</title>
<!--   <link href="./resources/stylesheet/commonStyles.css" rel="stylesheet" type="text/css">  -->
<link rel="stylesheet" href="./resources/stylesheet/bootstrap.min1.css"/>
   <!--  <link rel="stylesheet" href="./resources/stylesheet/datatable.bootstrap.css"/> -->
    <link rel="stylesheet" href="./resources/stylesheet/datatable.responsive.css"/>
    <!-- <link href="./resources/stylesheet/commonStyles.css" rel="stylesheet" type="text/css"> -->
    <link href="./resources/stylesheet/bootstrap.multiselect.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./resources/stylesheet/Customer_Profile.css"/>
    <link rel="stylesheet" href="./resources/stylesheet/common-print.css" type="text/css" media="print" >
   
    <style>
       
    </style>
<style type="text/css">
ul.multiselect-container {
    overflow-y: auto;
    overflow-x: hidden;
    width:400px !important;
    max-width: 400px !important;
    max-height: 300px !important;
    
}
ul.multiselect-container {
    overflow-y: auto;
    overflow-x: hidden;
    width:380px !important;
    max-width: 380px !important;
    max-height: 300px !important;
    
}
@media (min-width: 1200px){
.custPanel{
 padding-left:3%;
 padding-right:15px;
 padding-left:15px;
 margin-top:0px;
}
}
@media (min-width: 768px){
.custPanel{
 padding-left:0%;
 padding-right:0px;
 padding-left:0px;
 margin-top:60px;
}
ul.multiselect-container {
    overflow-y: auto;
    overflow-x: hidden;
    width:300px !important;
    max-width: 300px !important;
    max-height: 300px !important;
    
}
}
@media (min-width: 992px){
.custPanel{
 padding-left:0%;
 padding-right:0px;
 padding-left:0px;
 margin-top:0px;
}
ul.multiselect-container {
    overflow-y: auto;
    overflow-x: hidden;
    width:370px !important;
    max-width: 370px !important;
    max-height: 300px !important;
    
}
}
.multiselect-container > li > a { max-width:400px;white-space: normal; }
.dropdown-menu>.active>a, .dropdown-menu>.active>a:focus, .dropdown-menu>.active>a:hover{
color:#004c74 !important;
background-color:#fff !important;
} 
li.multiselect-item,.multiselect-group,.active{
background-color:lightslategray !important;
color:#fff !important;
}
  .popover{
  max-width:400px !important;
  color:#4D4D4D !important;
  }
  .popover-content{
   font-family:Helvetica !important;
   font-weight:bold !important;
   box-shadow:1px 2px 3px rgba(0,0,0,0.2) !important;
   padding :9px 22px !important;
  }
</style>  
</head>
<body>
 
    <div class="printableUserToDoList container">
   <%-- <f:form action="" commandName="customersForm">
    <f:input path="" />
   </f:form> --%>
   <%
  	 String accID =   (String)request.getAttribute("accID");
   %>
   <div id="loggedInDiv" style="float: left;font-family: Helvetica;font-size: 10px;color: #337ab7;letter-spacing: .5px;padding-top: 15px;">
<img src="./resources/img/LogoSBA.png">
  
</div>
   <div id="loggedInDiv" style="float: right;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:55px;">You logged in as <span id="loggedInUserNameSpan"></span></div>
   <form role="form" action="" id="custForm" method="post">
          <fieldset>
              <div class="form-group">
                  <input class="form-control"  name="reqCustNum" id="reqCustNum" type="hidden" />
                   <input class="form-control"  name="LoggedInUserID"  type="hidden" value="<%=accID %>" />
                   <input class="form-control"  name="accId" id="accId" type="hidden" value="${requestScope['custNo']}" />
                    <input class="form-control"  name="from" id="from" type="hidden" value="${requestScope['from']}" />
                    <input class="form-control"  name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}" />
                    <input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
                    <input class="form-control"  name="sliderSubPlaysItem" id="sliderSubPlaysItem" type="hidden" value="${requestScope['sliderSubPlaysItem']}" />
                    <input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
                    <input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
                    <input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
                    <input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
              </div>
              <!-- Change this to a button or input when using this as a form -->
          </button></fieldset>
      </form>
      <!-- <div style="float: right;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:5px;padding-right:35px;">You logged in as <span id="loggedInUserNameSpan"></span></div> -->
        <div class="col-md-12 col-lg-12 custPanel">
         <div class="panel panel-default" style="border-color:#004c74;">
                        <div class="panel-heading" style="font-family:Helvetica Neue Bold !imporatnt;font-size:14px;font-weight;800;background-color:#004c74;border-color:#004c74;color:#fff;height:50px;">
                          <div class="col-lg-12 col-sm-12" style="padding-left:0px !important;padding-top:5px;padding-right:0px;">
                          <div class="col-lg-7 col-sm-7" style="padding-left:0px !important">
                          <span style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;"> <!-- <i style="font-size:18px !important;line-height:12px; !important;font-weight: Bold;" class="fa fa-angle-double-right"></i> -->
                            <span id="agentName" style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;padding-left: 1%;x;padding-right: 1%;"></span>
                            <a id="aboutId" tabindex="0"
										data-toggle="popover" data-trigger="focus"
										title="To Do List" style="padding-left: 5px;">
							<i class="fa fa-question-circle" aria-hidden="true" style="margin-top: -2px;color: #fff;font-size: 150%;vertical-align: middle;"></i></a> 
                          </span>
                          </div>
                          <div class="col-lg-5 col-sm-5" align="right" style="padding-right:0px !important">
                          <!-- View all customers -->
                          <li id="accTypeId" style="display:none;float: right;list-style: none;padding-right: 0px;" class="dropdown col-lg-3 col-sm-3 callToActCls">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="color: #fff;text-decoration:none;">
							<span id="accTypeVal" style="padding-right:2px;">MM </span><b class="caret"></b>
							</a>
								<ul class="dropdown-menu" id="accList" style="top: 32px;min-width:120px;list-style-type: none !important;">
								
									<li id="MM" style="list-style-type: none !important;"><a href="#">MM</a></li>
									<li class="divider" style="margin:4px 0px;list-style-type: none !important;"></li>
									<li id="SAM" style="list-style-type: none !important;"><a href="#">SAM</a></li>
		                     </ul>
						  </li>
                          	<% 
								String reqCustNum =  request.getParameter("reqCustNum");
								
								String level =  (String)request.getAttribute("level");
								String loggedInUserLevel =  (String)request.getAttribute("loggedInUserLevel");
								String role = SecurityContextHolder.getContext().getAuthentication()
            					.getAuthorities().toString();
                         	%>
                          <% if (loggedInUserLevel !=null && !"5".equals(loggedInUserLevel)) { %>
                          <form name="viewAllCust" action="./home_cust_profiles" method="post">
                         	<input type="hidden" name="reqCustNum" value="<%=accID %>" />
                         	<input type="hidden" name="accID" value="<%=reqCustNum %>" />
                         	<input type="hidden" name="LoggedInUserID" value="<%=accID %>" />
                         	
                         	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
							<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
							<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
							<input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
							<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
							<input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
                         	<button type="button" id="viewAllCustomers" onclick="document.forms['viewAllCust'].submit();" style="margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" >View all customers</button>
							</form>
							
							<%} %>
							</div>
							</div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                         <div class="col-md-12 col-sm-12 custPnlBody" >
                         <div style="background-color: gainsboro;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:auto;padding-top: 5px; padding-right: 5px;" class="col-lg-12 col-sm-12">
			                <div id="playSecDiv" class="col-lg-4 col-sm-4" style="padding-left:0px;width: auto; padding-right: 5px;">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;padding-bottom:25px;width: auto;display: inline;" class="col-lg-6 input-sm" id="filterByPlay">Filter </div><div class="col-lg-6" id="playSecId" style="padding-left:0px;width: auto;display: inline;">
									<select id="playSec" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm" multiple="multiple">
									<!-- <option value="ALL">ALL</option> -->
									
									<option value="Growth">Growth</option>
									<option value="Retention">Retention</option>
									<option value="Expansion">Expansion</option>
									
									<option value="Mature">Mature</option>
									<option value="Transitioning">Transitioning</option>
									<option value="Young">Young</option>
									<option value="AllQul">All Qualifiers</option>
									</select>
									</div>
									<div style="float: right">
									<button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printUserToDoListBtn" >Print</button>
									</div>
							</div>
							<div class="col-lg-4 col-sm-4" id="subfilterContainer" style="padding-left:0px;width:auto; padding-right: 5px;">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;padding-bottom:25px;width: auto;display: inline;" class="col-lg-6 input-sm" id="filterBySubPlay">Sub Filters</div><div id="subPlayIdDiv" class="col-lg-6" style="padding-left:0px;width: auto;display: inline;">
									<select id="subPlayId" style="color:#004c74;border:1px solid #004c74;width:300px; height:300px;" class="form-control input-sm" multiple="multiple">
									</select>
									</div>
									
									<div style="float: right">
									<button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printUserToDoListBtn" >Print</button>
									</div>
							</div>
								
							<div class="col-lg-3 col-sm-2" align="right" style="float:right">
							
					
							
							<form action="./getUserHiererchy" method="post" name="getUsers">
							<input type="hidden" name="reqCustNum" value="<%=accID %>" />
                         	<input type="hidden" name="accID" value="<%=reqCustNum %>" />
                         	<input type="hidden" name="LoggedInUserID" value="<%=accID %>" />
                         	
                         	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
							<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
							<input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
							<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
							
							<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
							<input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
							<div style="float:right;">
							<!-- <a href="#" data-toggle="tooltip" title="Frequently Asked Questions" data-placement="right" data-original-title="Frequently Asked Questions" style="float: right;">	
								<img src="./resources/img/info_icon_green2.png" width="32" height="26" onclick="javascript:showFaqPopUp()">
							</a> -->
							 <a href="#" style= "margin-top: 2px;float:right;" data-toggle="tooltip" title="Help / FAQ / Training" data-placement="right">	
								<!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
								<img src="./resources/img/Faq.jpg" width="32" height="23" onclick="javascript:showTrainingPopUp()" style="vertical-align: bottom;"></img>
							</a> 
							</div>
							<div style="float:right;">
							<%if(level!=null  && !"5".equals(level)){ %>
							<a href="javascript:void(0)" id="users"  title="Users" data-placement="right" style="margin-right:7px;text-decoration:underline;;font-weight:bold;" onclick="javascript:logUserActivity(2011, 'View Reporting Hierarchy');document.forms['getUsers'].submit();">Users</a>
							<%} %>
							</div>
							
							</form>
							</div>
                           <div>
								<div id="training" class="toolTip" style="font-size:12px; font-family: Helvetica; 
									color: #000000; position:absolute;"></div>
							</div>
							</div>
                           <div class="">
                           <div id="OrderDetailsInfoId" class="dataTable_wrapper" style="border:1px solid #e5e5e5">
                                <table class="table table-striped table-hover dt-responsive" id="dataTables-CustInfo" cellspacing="0">
                                    <thead id="custHeader">
                                       <th>Cust#</th>
                                         <th>Customer<br>Type</th>
                                         <th>Company Name</th>
                                         <th>Call<br>Order</th>
                                         <th>Customer<br>Segment</th>
                                         <th>Sub<br>Segment</th>
                                         <th>Qualification<br>Score</th>
                                         <th>Call<br>Action</th>
                                         <th>Last<br>Contacted Date</th>
                                    </thead>
                                    <tbody>
										
                                    </tbody>
                                </table>
                                
                            </div>
                            </div>
				            </div>
				        </div>
         </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/jsp/Training.jsp"></jsp:include>
    <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
	<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
	<input type="hidden" name="LoggedInUserID" value="<%=accID %>" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
    <!-- jQuery -->
  
    <script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-uniform.min.js" type="text/javascript"></script>
<!-- <script src="./resources/javascript/fullcalender.js" type="text/javascript"></script>
<script src="./resources/javascript/easypiechart.js" type="text/javascript"></script>
<script src="./resources/javascript/sparkline.min.js" type="text/javascript"></script> -->
<script src="./resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/javascript/bootstrap-min.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-dropdown.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-multiselect.js" type="text/javascript"></script>
    <script src="./resources/javascript/dataTables.bootstrap.min.js"></script>
    
    <!--  <script src="./resources/javascript/jquery1.11.js" type="text/javascript"></script>
     <script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
     <script src="./resources/javascript/jquery.datatable.min.js" type="text/javascript"></script>
      <script src="./resources/javascript/datatable.responsive.js" type="text/javascript"></script>
       <script src="./resources/javascript/datatable.bootstrap.js" type="text/javascript"></script>
        <script src="./resources/javascript/bootstrap-multiselect.js" type="text/javascript"></script>
           <script src="./resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> -->
        <script src="./resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
        <script src="./resources/javascript/common.js"></script>
        <script src="./resources/javascript/Customer_Info.js"></script>
    <script src="./resources/dist/js/sb-admin-2.js"></script>
    <script src="./resources/javascript/jquery.PrintArea.js" type="text/javascript"></script>
    <script src="./resources/javascript/user-activity.js" type="text/javascript"></script>
    <script src="./resources/javascript/jquery.cookie.js" type="text/javascript"></script>
    <script src="./resources/javascript/global-default-exception-handler.js" type="text/javascript"></script>
    
  

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
<script>
$(document).ready(function(){
	
	$('#dataTables-OrderInfo').DataTable({
		"lengthMenu": [[15, 20, 25, -1], [15, 20,25, "All"]],
		  "jqueryUI":true,
		  "order": [],
			"aoColumns": [{"bSortable": false, "sWidth": '33%'},
			{"bSortable": false, "sWidth": '33%'},
			{"bSortable": false, "sWidth": '33%'},
			{"bSortable": false, "sWidth": '33%'}
			
			]
			
	});			

$("#dataTables-OrderInfo td").css("padding-left","10px");
$("#dataTables-OrderInfo td").css("WORD_BREAK","BREAK-ALL");
 
//add print functionality
$('#printUserToDoListBtn').click(function () {
	
	
	$(".printableUserToDoList").printArea();
	//window.print();
});


//log user activity; View Customer List
logUserActivity(1001, 'View Customer List / To Do List');

 }); 
</script>
</body>
</html>
