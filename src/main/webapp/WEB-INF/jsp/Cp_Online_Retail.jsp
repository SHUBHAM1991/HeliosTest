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
    <link rel="stylesheet" href="./resources/stylesheet/bootstrap.min1.css?helios_version=${initParam.helios_ver}"/>
    <!--  <link rel="stylesheet" href="./resources/stylesheet/datatable.bootstrap.css"/> -->
    <link rel="stylesheet" href="./resources/stylesheet/datatable.responsive.css?helios_version=${initParam.helios_ver}"/>
    <!-- <link href="./resources/stylesheet/commonStyles.css" rel="stylesheet" type="text/css"> -->
    <link href="./resources/stylesheet/bootstrap.multiselect.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./resources/stylesheet/Cp_Online_Retail.css?helios_version=${initParam.helios_ver}"/>
    <link rel="stylesheet" href="./resources/stylesheet/common-print.css?helios_version=${initParam.helios_ver}" type="text/css" media="print" >
   
<style type="text/css">
   .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px; }
  .toggle.ios .toggle-handle { border-radius: 20px; }
</style>
<style type="text/css">
  div.printableUserToDoList{
  width:98% !important;
  }
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
  table.dataTable thead th[width="15%"].sorting:after{
   margin-top:-15px;
  }
  table.dataTable thead th[width="17%"].sorting:after{
   margin-top:-15px;
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
  table.dataTable thead th[width="15%"].sorting:after{
   margin-top:-5px;
  }
  table.dataTable thead th[width="17%"].sorting:after{
   margin-top:-5px;
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
  table.dataTable thead th[width="15%"].sorting:after{
   margin-top:-15px;
  }
  table.dataTable thead th[width="17%"].sorting:after{
   margin-top:-15px;
  }
}
.multiselect-container > li > a { max-width:400px;white-space: normal; }
.dropdown-menu>.active>a, .dropdown-menu>.active>a:focus, .dropdown-menu>.active>a:hover{
color:#004c74 !important;
background-color:#fff !important;
} 
/* li.multiselect-item,.multiselect-group,.active{
background-color:lightslategray !important;
color:#fff !important;
} */
  .popover{
  max-width:400px !important;
  color:#4D4D4D !important;
  }
  .popover-content{
   font-family:Helvetica !important;
   font-weight:bold !important;
   box-shadow:1px 2px 3px rgba(0,0,0,0.2) !important;
   padding :9px 22px !important;
   width:400px;
  }
  .table-bordered>tbody>tr>td, .table-bordered>tfoot>tr>td,.table-bordered>thead>tr>td{
  border:none !important;
  }
  .table>tbody>tr>td, .table>tfoot>tr>td, .table>thead>tr>td{
  border-top:none !important;
  }
  #helpTrainingPanel + div.popover{  width:455px; max-width:455px !important;  color:#4D4D4D !important;padding:0px !important; border:2px solid #012c43; }
  
  #helpTrainingPanel + div.popover.bottom>.arrow{ border-left-width :14px;border-right-width:14px;border-bottom-color:#012c43;}
  #helpTrainingPanel +  div.popover > div.popover-content{padding :0px !important;width:100% !important;box-shadow:none !important;}
</style>  
</head>
<body class="bgcolor">
<div id="editor"></div> 
    <div class="printableUserToDoList container">
   <%-- <f:form action="" commandName="customersForm">
    <f:input path="" />
   </f:form> --%>
   <%
  	 String accID =   (String)request.getAttribute("accID");
   %>
   <div style="/* padding:1px;background-color:silver; */">
		<form class="<%-- form-wrapper cf --%>" action="">
			<div id="loggedInDiv" class="col-lg-3 col-sm-3" style="float: left; font-family: Helvetica; font-size: 12px; color: #4d4d4d; letter-spacing: .5px; padding-top: 15px;">
				<img src="./resources/img/helios-logo.png" style="width:200px;-webkit-filter: contrast(500%);filter:contrast(500%);">

			</div>
			<div class="col-lg-9 col-sm-9" style="text-align: right;display:none;">
				<span class="col-lg-10 col-sm-10" style="padding-right:0px;"><input type="text" placeholder="Search here..." required="" style="float: right;padding: 20px;"></span>
				<span class="col-lg-2 col-sm-2" style="padding-right:0px;"><button type="button" id="searchResults">Search</button></span>
			</div>
			<div id="loggedInDiv" style="margin:15px 15px 5px 15px;float: right; font:bold 12px Arial, Helvetica, Sans-serif; font-size: 12px; color: #4d4d4d; letter-spacing: .5px;padding:5px">
				<span style="color:grey;">Logged in as </span><span id="loggedInUserNameSpan" style="color: #012c43;"></span>
			</div>
             <ul class="nav navbar-nav pad" id="helpTrainingPanel"  style="display:none;padding-top:10px;margin-right:18%;float: right;cursor:pointer;padding-right: 1%;" data-original-title="" title="">
                <li><a href="./resources/docs/NonAM1_Call_ranking_Logic.pdf" download="NonAM1_Call_ranking_Logic.pdf" style="padding:5px 0px;"><img src="./resources/img/help_and_training.png" width="70" height="46" style="background-color: black;"></a></li>
                <li class="" style="padding:5px 0px;"><a href="./resources/docs/NonAM1_Call_ranking_Logic.pdf" download="NonAM1_Call_ranking_Logic.pdf" style="color: #012c43;padding: 12px;border: 1px solid #012c43;font-size: 14px;font-weight: bold;font-family: arialmt;">Call Order Ranking Logic</a></li>
            </ul>
		</form>
		</div>
		<!-- <div id="loggedInDiv" style="float: left;font-family: Helvetica;font-size: 12px;color: #4d4d4d;letter-spacing: .5px;padding-top: 15px;">
    <img src="./resources/img/LogoSBA.png">
   </div>
   <div id="loggedInDiv" style="float: right;font-family: Helvetica; font-size: 12px; color: #4d4d4d; letter-spacing: .5px;padding-top:55px;">You logged in as <span id="loggedInUserNameSpan" style="color:#0092db;"></span>
   </div> -->
   <form role="form" action="" id="custForm" method="post">
          <fieldset>
              <div class="form-group">
                  <input class="form-control"  name="reqCustNum" id="reqCustNum" type="hidden" />
                  <input class="form-control"  name="contactName" id="contactName" type="hidden" />
                  <input class="form-control"  name="contactDate" id="contactDate" type="hidden" />
                  <input class="form-control"  name="contactStatus" id="contactStatus" type="hidden" />
                   <input class="form-control"  name="LoggedInUserID" id="LoggedInUserID"  type="hidden" value="<%=accID %>" />
                   <input class="form-control"  name="accId" id="accId" type="hidden" value="${requestScope['custNo']}" />
                    <input class="form-control"  name="from" id="from" type="hidden" value="${requestScope['from']}" />
                    <input class="form-control"  name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}" />
                    <input class="form-control"  name="fil" id="filt" type="hidden" value="${requestScope['filterBy']}" />
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
         <div class="panel panel-default" style="background-color: #eeeeee;border-radius:5px !important;border: none;">
                        <div class="panel-heading" style="padding: 7px 0px;background-color: #eee;border: none;font-size:14px;">
                          <div class="col-lg-12 col-sm-12" style="background-color:#ffffff;position: absolute;text-align:right;padding-left:0px !important;padding-top:5px;padding-right:0px;">
                          <div class="2" style="width;auto;padding:7px 10px;">
                          <span style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;"> <!-- <i style="font-size:18px !important;line-height:12px; !important;font-weight: Bold;" class="fa fa-angle-double-right"></i> -->
                            <span id="agentName" style="color: #012c43;font-family: monospace; font-size: 15px; padding-right: 1%;font-weight: bold;"></span>
                            <!-- <a id="aboutId" tabindex="0"
										data-toggle="popover" data-trigger="focus"
										title="To Do List" style="padding-left: 5px;">
							<i class="fa fa-question-circle" aria-hidden="true" style="margin-top: -2px;color: #cc0000;font-size: 140%;vertical-align: middle;box-xhadow:none;"></i>
							</a> --> 
							<a id="create_pdf" href="javascript:demoPDF();"	 style="display:none;padding-left: 5px;">
							<i class="fa fa-download" aria-hidden="true" style="margin-top: -2px;color: #cc0000;font-size: 140%;vertical-align: middle;box-xhadow:none;"></i></a>
                          </span>
                          </div>
                          <div class="col-lg-4 col-sm-5" align="right" style="padding:7px;float:right;">
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
                          <form name="viewAllCust" action="./cp_online_retail" method="post">
                         	<input type="hidden" name="reqCustNum" value="<%=accID %>" />
                         	<input type="hidden" name="accID" value="<%=reqCustNum %>" />
                         	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=accID %>" />
                         	
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
						<!-- 	<li id="plusId" class="active ui-state-default ui-corner-top" style="list-style: none;width: auto;font-weight: 600;font-family: Helvetica;border-radius: 5px 5px 0px 0px;padding: 25px;border: none;float: right;" role="tab" tabindex="-1" aria-controls="tab_default_3" aria-labelledby="ui-id-3" aria-selected="false" aria-expanded="false">
                             <input type="checkbox" checked data-toggle="toggle" data-style="ios">	
						   </li> -->
                        </div>
                        <!-- /.panel-heading -->
                        <div class="tabbable-panel" style="box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);">
				<div id="tabs" class="tabbable-line">
					<ul class="nav nav-tabs " style="background-color:#fff;display:none;" id="tabNavAllGroup">
						<li id="samLiId" class="active" style="width: auto;margin-right: 1%;font-weight: 600;font-family: Helvetica;border-radius:5px 5px 0px 0px;padding-top:5px;">
							<a href="#tab_default_1" data-toggle="tab" style="box-shadow:none;">
							LEADS - STAPLES.COM
							 </a>
						</li>
						<li id="mmLiId" style="float: left;text-align: center;width: auto;border-radius: 5px 5px 0px 0px;font-weight: 600;font-family: Helvetica;top:10%;">
							<a href="#tab_default_2" data-toggle="tab" style="box-shadow:none;top:10%;float:left;">
							MID-MARKET ACCOUNTS - SBA
								<!-- <a id="aboutId" tabindex="0" data-toggle="popover"	data-trigger="click" title="" style="float: right; padding-left: 5px;box-shadow:none;"
									data-original-title="To Do List"> 
									<i	class="fa fa-question-circle" aria-hidden="true"
										style="margin-top: 0px; color: #cc0000; font-size: 170%; vertical-align: middle; box-xhadow: none;">
									</i>
								</a> -->
							</a>
						</li>
						<li id="samNewLiId" class="active" style="width: auto;margin-right: 1%;font-weight: 600;font-family: Helvetica;border-radius:5px 5px 0px 0px;padding-top:5px;">
							<a href="#tab_default_3" data-toggle="tab" style="box-shadow:none;top:10%;float:left;">
							STAPLES.COM
							<!-- <a id="reasonCodeId" tabindex="0" data-toggle="popover"	data-trigger="click" title="" style="float: right; padding-left: 5px;box-shadow:none;"
									data-original-title="To Do List"> 
									<i	class="fa fa-info-circle fa-lg" aria-hidden="true"
										style="margin-top: 0px;  font-size: 160%; vertical-align: middle; box-xhadow: none;color:#cc0000;">
									</i>
								</a> -->
							 </a>
						</li>
						
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab_default_1">
							<div class="panel-body" style="padding-top:7px;">
                         <div class="col-md-12 col-sm-12 custPnlBody" >
                         <div style="background-color:#eeeeee;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 10px;margin-left:0px;height:auto;padding-top: 6px; padding-right: 5px;padding-bottom:6px;" class="col-lg-12 col-sm-12">
			                <!-- <div id="playSecDiv" class="col-lg-4 col-sm-4" style="padding-left:0px;width: auto; padding-right: 5px;">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;padding-bottom:25px;width: auto;display: inline;" class="col-lg-6 input-sm" id="filterByPlay">Filter </div><div class="col-lg-6" id="playSecId" style="padding-left:0px;width: auto;display: inline;">
									<select id="playSec" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm" multiple="multiple">
									<option value="ALL">ALL</option>
									
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
							</div> -->
								
							<div class="col-lg-3 col-sm-2" align="right" style="float:right">
							<div style="float:right;">
							 <a href="#" style= "margin-top: 2px;float:right;" data-toggle="tooltip" title="Help / FAQ / Training" data-placement="bottom">	
								<!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
								<img src="./resources/img/Faq.jpg" width="32" height="23" onclick="javascript:showTrainingPopUp()" style="vertical-align: bottom;"></img>
							</a> 
							</div>

							</div>
                           <div>
								<div id="training" class="toolTip" style="font-size:12px; font-family: Helvetica; 
									color: #000000; position:absolute;"></div>
							</div>
							</div>
                           <div class="">
                           <div id="OrderDetailsInfoIdSam" class="dataTable_wrapper" style="border:none;">
                                <table class="table table-striped table-hover dt-responsive" id="dataTables-CustInfoSam" cellspacing="0">
                                    <thead id="custHeaderSam">
                                        <th width="15%" style="text-align: left;padding-left: 1%;">
											<div style="padding-top: 5px; line-height: 1.4;">Customer
												Parent</div> 
											<span style="line-height: 1; ">Rewards
												Number</span>
										</th>
									<!-- 	<th width="20%" style="text-align: left;padding-left: 1%;">
											<div style="padding-top: 5px; line-height: 1.4;">Lead
												Assignment</div> 
											<span style="line-height: 1; ">Date</span>
										</th> -->
                                         <th class="iconCls" width="17%" style="text-align: left;padding-left: 1%;pointer-events:none;"> 
	                                         <div style="padding-top: 5px; line-height: 1.4;">Parent Rewards
											 </div> 
											<span style="line-height: 1; ">Tier 
											<a id="tierId"	data-toggle="tooltip" data-placement="right" title=" SAM Member Rewards Benefits"
												style="padding-left: 5px; box-shadow: none; pointer-events: auto; cursor: pointer;">
												<i class="fa fa-info-circle" aria-hidden="true" style="margin-top: -2px; color: #cc0000; font-size: 140%; vertical-align: middle; box-xhadow: none;"></i>
										    </a>
											</span>
                                          
										</th>
                                         <th width="31%" style="text-align: left;padding-left: 1%;">Company Name</th>
                                         <th width="36%" style="text-align: left;padding-left: 1%;border-right:none;">Contact Info</th> 
                                                   
                                         
                                    </thead>
                                    <tbody>
											
                                    </tbody>
                                </table>
                                
                            </div>
                            </div>
				            </div>
				        </div>
						</div>
						<div class="tab-pane" id="tab_default_2">
						<div id="tab2_panel" class="panel-body">
                         <div class="col-md-12 col-sm-12 custPnlBody" >
                         <div style="background-color: #1d2939;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:auto;padding:7px;" class="col-lg-12 col-sm-12">
			                <div id="playSecDiv" class="col-lg-4 col-sm-4" style="padding-left:0px;width: auto; padding-right: 5px;">                                           
									<div style="padding-left:0px;font-size:16px;color:#fff;font-weight:bold;padding-bottom:25px;width: auto;display: inline;" class="col-lg-6 input-sm" id="filterByPlay">Filter </div><div class="col-lg-6" id="playSecId" style="padding-left:0px;width: auto;display: inline;">
									<select id="playSec" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm" multiple="multiple">
									<option value="Growth">Growth</option>
									<option value="Retention">Retention</option>
									<option value="Expansion">Expansion</option>
									</select>
									</div>
									<div style="float: right">
									<button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printUserToDoListBtn" >Print</button>
									</div>
							</div>
							<div class="col-lg-4 col-sm-4" id="subfilterContainer" style="padding-left:0px;width:auto; padding-right: 5px;">                                           
									<div style="padding-left:0px;font-size:16px;color:#fff;font-weight:bold;padding-bottom:25px;width: auto;display: inline;" class="col-lg-6 input-sm" id="filterBySubPlay">Sub Filters</div><div id="subPlayIdDiv" class="col-lg-6" style="padding-left:0px;width: auto;display: inline;">
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
                         	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=accID %>" />
                         	
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
							 <a href="#" style= "margin-top: 2px;float:right;" data-toggle="tooltip" title="Help / FAQ / Training" data-placement="bottom">	
								<!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
								<!-- <img src="./resources/img/Faq.jpg" width="32" height="23" onclick="javascript:showTrainingPopUp()" style="vertical-align: bottom;"></img> -->
								<button type="button" class="btn-sm button-default" onclick="javascript:showTrainingPopUp()" style="background-color: #fff; border: none;border-radius: 0px;padding: 4px 7px; color: #1d2939;font-size: 12px; font-weight: bold;margin-top:3px;">FAQ</button>
							</a> 
							</div>
							<div style="float:right;padding:5px;">
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
                           <div id="OrderDetailsInfoId" class="dataTable_wrapper" style="border: 1px solid #e5e5e5;">
                                <table class="table table-striped table-hover dt-responsive" id="dataTables-CustInfo" cellspacing="0">
                                    <thead id="custHeader">
                                    <th>Call<br>Order</th>
                                    <th>Status</th>
                                       <th>Cust#</th>
                                         <th>Customer Type</th>
                                         <th>Company Name</th>
                                         
                                         <th>Customer<br>Segment</th>
                                         <th>Sub<br>Segment</th>
                                          <th>Qualification<br>Score</th> 
                                         <th>Call<br>Action</th>
                                         <th>Last Live<br>Contacted Date</th>
                                    </thead>
                                    <tbody>
										
                                    </tbody>
                                </table>
                                
                            </div>
                            </div>
				            </div>
				        </div>
							
						</div>
						<div class="tab-pane active" id="tab_default_3">
							<div class="panel-body" style="padding-top:7px;border:1px solid #ccc;">
                         <div class="col-md-12 col-sm-12 custPnlBody" >
                         <div style="background-color:#eeeeee;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 10px;margin-left:0px;height:auto;padding-top: 6px; padding-right: 5px;padding-bottom:6px;" class="col-lg-12 col-sm-12">
			                	
							<div class="col-lg-3 col-sm-2" align="right" style="float:right">
							<div style="float:right;">
							 <a href="#" style= "margin-top: 2px;float:right;" data-toggle="tooltip" title="Help / FAQ / Training" data-placement="right">	
								<!-- <i class="fa fa-question-circle" aria-hidden="true" onclick="javascript:showTrainingPopUp()" style="color: #004c74;font-size: 170%;vertical-align: middle;"></i> -->
								<img src="./resources/img/Faq.jpg" width="32" height="23" onclick="javascript:showTrainingPopUpSam()" style="vertical-align: bottom;"></img>
							</a> 
							</div>

							</div>
                           <div>
								<div id="training" class="toolTip" style="font-size:12px; font-family: Helvetica; 
									color: #000000; position:absolute;"></div>
							</div>
						</div>
                           <div class="">
                           <div id="OrderDetailsInfoIdSamNew" class="dataTable_wrapper" style="border:none;">
                                <table class="table table-striped table-hover dt-responsive" id="dataTables-CustInfoSamNew" cellspacing="0">
                               <thead id="custHeaderSamNew">
                                        <th width="8%" style="text-align: left;padding-left: 1%;">
											Customer Master Rewards#
										</th>
                                         <th width="14%" class="iconCls"  style="text-align: left;padding-left: 1%;pointer-events:none;"> 
	                                        Master Rewards
											<span style="line-height: 1; ">Tier 
											<a id="tierIdSam"	data-toggle="tooltip" data-placement="right" title=" SAM Member Rewards Benefits"
												style="padding-left: 5px; box-shadow: none; pointer-events: auto; cursor: pointer;">
												<i class="fa fa-info-circle" aria-hidden="true" style="margin-top: -2px; color: #cc0000; font-size: 115%; vertical-align: middle; box-xhadow: none;"></i>
										    </a>
											</span>
                                          
										</th>
										<th width="6%" style="text-align: left;padding-left: 1%;">Call </br>Order</th>
                                         <th width="12%" style="text-align: left;padding-left: 1%;">Company Name</th>
										<th width="12%"
											style="text-align: left; padding-left: 1%; border-right: none;">Reason For <span style="line-height: 1;"><br> the Call <a
												id="reasonCodeIdSam" data-toggle="popover"
												data-placement="bottom" data-trigger="focus" title="sss"
												style="padding-left: 5px; box-shadow: none; pointer-events: auto; cursor: pointer;"
												data-original-title="To Do List"> <i
													class="fa fa-info-circle" aria-hidden="true"
													style="margin-top: -2px; color: #cc0000; font-size: 115%; vertical-align: middle; box-xhadow: none;"></i>
											</a></span>
										</th>
														<th width="8%"  style="text-align: left;padding-left: 1%;border-right:none;">Category</th>
                                         <th width="16%" style="text-align: left;padding-left: 1%;border-right:none;">Last Contacted Date</th>
                                          <th width="6%" style="text-align: left;padding-left: 1%;border-right:none;">Time Zone</th>
                                         <th width="40%" style="text-align: left;padding-left: 1%;border-right:none;">Contact Info</th>
                                                   
                                         
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
			</div>
                        
         </div>
        </div>
        <%-- <jsp:include page="RewardsDetailSearch.jsp"></jsp:include> --%>
    </div>
    <div id="rewardsPopoverId" style="display:none;"></div>
    <jsp:include page="/WEB-INF/jsp/Training.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/jsp/TrainingSam.jsp"></jsp:include>
    <!-- contact confirmation modal start -->
         <div class="modal fade" id="contactConfModal" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span>Contacted Confirmation</span>
		        </div>
		          <div class="modal-body" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;">
		          Have you contacted this customer?
		          </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-primary" id="contactYesId" data-dismiss="modal" style="border-radius: 2px !important; background-color: #23527c; border-color: #23527c;">YES</button>
                  <button type="button" class="btn btn-secondary" id="contactNoId" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;">NO</button>
		        </div>
		      </div>
		    </div>
		  </div>
  
  <!--contact confirmation modal end -->
  
    
    <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
	<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=accID %>" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
<!-- jQuery -->
<!-- These Files Are StartUp Files,Should Not Have Any Version -->
<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script> 

 <script>
(function(){
    /**
     * Increment and return the local storage version for a given JavaScript file by name
     * @param  {string} scriptName  Name of JavaScript file to be versioned (including .js file extension)
     * @return {integer}            New incremented version number of file to pass to .js GET parameter
     */
    var incrementScriptVer = function(scriptName){

        var version = parseInt(localStorage.getItem(scriptName));

        // Simple validation that our item is an integer
        if(version > 0){
            version += 1;
        } else {
            // Default to 1
            version = 1;
        }

        localStorage.setItem(scriptName, version);

        return version;
    };

    // Set your scripts that you want to be versioned here
var scripts = ['jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','bootstrap-dropdown.js','bootstrap-multiselect.js','dataTables.bootstrap.min.js','Date.js','metisMenu.min.js','common.js','Cp_Online_Retail.js','sb-admin-2.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js'];

    // Loop through each script name and append our new version number
    scripts.map(function(script){
        var currentScriptVer =${initParam.helios_ver} /* incrementScriptVer(script); */
        if(script=='jquery.dataTables.min.js'){
        	document.write("<script language='text/javascript' type='text/javascript' src='./resources/bower_components/datatables/media/js/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
        }else if(script=='sb-admin-2.js'){
        	document.write("<script language='text/javascript' type='text/javascript' src='./resources/dist/js/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
        }else if(script=='metisMenu.min.js'){
        	document.write("<script language='text/javascript' type='text/javascript' src='./resources/bower_components/metisMenu/dist/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
        }else{
            document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
        }
    });
})();
</script>

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
//logUserActivity(1001, 'View Customer List / To Do List');

 }); 
</script>
</body>
</html>
