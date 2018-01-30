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
    <link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    
    <link rel="stylesheet" href="./resources/stylesheet/datatable.responsive.css?helios_version=${initParam.helios_ver}"/>
    <link href="./resources/stylesheet/bootstrap.multiselect.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./resources/stylesheet/Cdm_To_Do_List.css?helios_version=${initParam.helios_ver}"/>
    <link rel="stylesheet" href="./resources/stylesheet/common-print.css?helios_version=${initParam.helios_ver}" type="text/css" media="print" >
   <link href="https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900" rel="stylesheet" type="text/css">
   <link href="./resources/stylesheet/CallToAction.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
   <link href="./resources/stylesheet/bootstrap-switch.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
   <link href="./resources/stylesheet/bootstrap.toggle.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">

<style type="text/css">
  div.printableUserToDoList{
  width:98% !important;
  }
#dataTables-CustInfo_filter{
display:none;
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

  table.dataTable thead th[width="15%"].sorting:after{
   margin-top:-15px;
  }
  table.dataTable thead th[width="17%"].sorting:after{
   margin-top:-15px;
  }
}

.dropdown-menu>.active>a, .dropdown-menu>.active>a:focus, .dropdown-menu>.active>a:hover{
color:#004c74 !important;
background-color:#fff !important;
} 

  .popover{  max-width:455px !important;  color:#4D4D4D !important;padding:0px !important; border:2px solid #012c43; }
  h6#timeZoneFilter + div.popover {max-width:250px !important;}
  .popover.bottom>.arrow{ border-left-width :14px;border-right-width:14px;border-bottom-color:#012c43;}
  .popover-content{padding :0px !important;}
  a#potentialTooltip + div.tooltip > .tooltip-inner , a#alertTooltip + div.tooltip > .tooltip-inner{
  width:300px;
  max-width:350px;
  }
 /*  .popover-content{
   font-family:Helvetica !important;
   font-weight:bold !important;
   box-shadow:1px 2px 3px rgba(0,0,0,0.2) !important;
   padding :9px 22px !important;
   width:400px;
  } */
  .table-bordered>tbody>tr>td, .table-bordered>tfoot>tr>td,.table-bordered>thead>tr>td{  border:none !important;  }
  .table>tbody>tr>td, .table>tfoot>tr>td, .table>thead>tr>td{  border-top:none !important;  }
  svg + a{display:none !important;}
  svg {padding-left:10px !important;}

</style> 

<script type="text/javascript" src="./resources/javascript/icheck.js?helios_version=${initParam.helios_ver}"></script>

<!-- amCharts javascript code -->
		
</head>
<body class="bgcolor" onload="">
<div id="editor"></div> 
    <div class="printableUserToDoList container">
   <% String accID =   (String)request.getAttribute("accID");  %>
 <div style="display:none;" class="">
  <a id="scrollTop" href="javascript:void();"  style="opacity: .7;background: transparent;width: 97%;text-align: center;position: fixed;z-index: 10001;bottom: 5px;display: block;">
		<div class="blink" style=" float: right; padding: 10px; background: none repeat scroll 0 0 rgb(40, 40, 40); border: 1px solid #333; border-radius: 0em; width: 300px; left:0px;  z-index: 9999;  color: #fff;">
           <!-- <p><i class="fa fa-hand-o-right" id="directionIcon" style="padding-right: 10px;display:none;"></i> New Disposition found, <span id="tenfoldRef" style="text-decoration:underline;">click here</span> to refresh</p> -->
           <p><span><!-- <i class="fa fa-hand-o-right" id="directionIcon" style="padding-right: 10px;display:none;"></i> --> New Disposition found, </span><span id="tenfoldRef" style="text-decoration:underline;color:#ff6600;">click here</span> <span>to refresh</span></p>
        </div>
  </a>
</div>
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
                    <input type="hidden" name="fromCdmSeeAll" id="fromCdmSeeAll"/>
                    <input type="hidden" name="cdmTotalCount" id="cdmTotalCount" value='${requestScope["cdmTotalCount"]}' />
                     <input type="hidden" id="alertState" name="alertState" value='${requestScope["alertState"]}' />
              </div>
              <!-- Change this to a button or input when using this as a form -->
          </button></fieldset>
      </form>
        <div class="col-md-12 col-lg-12 custPanel">
         <div class="panel panel-default" style="background-color: #eeeeee;border-radius:5px !important;border: none;">
                        <div class="panel-heading" style="padding: 7px 0px;background-color: #eee;border: none;font-size:14px;height:55px;">
                        <!-- header start -->
                        <div class="navbar navbar-default navbar-fixed-top" role="navigation" style="position: absolute !important;border-color:none;">
						    <div class="container helios_head_container"> 
						        <div class="navbar-header helios_nav_header">
						            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						                <span class="icon-bar"></span>
						                <span class="icon-bar"></span>
						                <span class="icon-bar"></span> 
						            </button>
						            <img src="./resources/img/helios-logo.png" width="160" height="55">
						        </div>
						        <div class="navbar-collapse">
						            <ul class="nav navbar-nav pad" id="aboutId" data-toggle="popover"	data-trigger="focus" style="cursor:pointer;">
						                <li><a href="javascript:void();" style="padding:5px 0px;"><img src="./resources/img/help_and_training.png" width="70" height="46" style="background-color: black;"></a></li>
						                <li class="" style="padding:5px 0px;"><a href="javascript:void();" style="color: #012c43;padding: 12px;border: 1px solid #012c43;font-size: 14px;font-weight: bold;font-family: arialmt;">Help &amp; Training</a></li>
						            </ul>
						            <ul class="nav navbar-nav navbar-right">
						                <li class="dropdown" style="margin-top: 10px;">
						                    <a href="#" class="" data-toggle="" aria-expanded="false" style="cursor:default;">
						                        <span class="glyphicon glyphicon-user loginOuterCls" style="display:none;"></span>
						                        <span id="loggedInUserNameSpan" style="vertical-align: text-bottom;color: #010d2a;"></span>
						                        <span class="glyphicon glyphicon-chevron-down " style="color: #ababab;padding-left: 10px;font-weight: normal !important;display:none;"></span>
						                    </a>
						                    <ul class="dropdown-menu" style="width: 100% !important;background-color:#f0f0f0;">
						                        <li>
						                            <div class="navbar-login">
						                                <div class="row" style="margin: 0;">
						                                    <div class="col-lg-12" style="height: auto;">
						                                        <p class="text-center">
						                                            <span class="glyphicon glyphicon-user icon-size loginInnerCls"></span>
						                                        </p>
						                                    </div>
						                                    <div class="col-lg-12">
						                                        <p class="text-center"><a href="#" class="btn btn-block btn-sm" style="text-decoration:none;color:#2b2b2b;">My profile</a></p>
						                                    </div>
						                                </div>
						                            </div>
						                        </li>
						                        <li class="divider navbar-login-session-bg"></li>
						                        <li>
						                            <div class="navbar-login">
						                                <div class="row" style="margin: 0;">
						                                    <div class="col-lg-12" style="height: auto;">
						                                        <p class="text-center"> <span class="glyphicon glyphicon-log-out" style="font-size: 200%;color:#00324c"></span></p>
						                                    </div>
						                                    <div class="col-lg-12">
						                                        <p class="text-center"><a href="#" class="btn btn-block btn-sm" style=" color: #2b2b2b;text-decoration:none;">Log out</a>						                                        </p>
						                                    </div>
						                                </div>
						                            </div>
						                        </li>
						                         
						                    </ul>
						                </li>
						            </ul>
						        </div>
						    </div>
						</div>
                        <!-- header ends -->
                          <div class="col-lg-12 col-sm-12" style="display:none;margin-top:2%;padding-left:0px !important;padding-top:5px;padding-right:0px;">
                          <div class="col-lg-7 col-sm-7" style="padding:7px 10px;">
                          <span style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;"> 
                            <span id="agentName" style="color: #4d4d4d;font-family: Helvetica !important;font-weight:bold;font-size: 15px;x;padding-right: 1%;"></span>
                          </span>
                          </div>
                          <div class="col-lg-5 col-sm-5" align="right" style="padding:7px;">
                          <!-- View all customers -->
                         
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
						
                        </div>
                        <!-- /.panel-heading -->
                        <div class="tabbable-panel" style="box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);">
				      <div id="tabs" class="tabbable-line">
					   <div class="tab-content">
						<div class="tab-pane h-pane" id="tab_default_2" >
						<div id="tab2_panel" class="panel-body helios_bgcolor_panel">
						
						<!-- happy path UI start -->
						<div class="row">
				        <div class="col-xs-12 col-md-6 " style="padding-right:5px;">
				            <div class="well well-sm happy_path_left">
				                <div class="row">
				                    <div class="col-xs-12 col-md-6 text-center" style="">
				                        <div class="" style="font-size: 18px;font-family: Helvetica;text-align: left;font-weight: bold;">Today's Progress</div>
				                       
				                        <div style="padding: 15px 0px;" >
				                      <!--  <div id="circleContainer"></div> -->
				                      <div id="circleContainer" style="width: 70px;"></div>
				                       <span id="happyPathImg" style="float: left; padding: 5%;"><img src="./resources/img/happy_path.png" width="70" height="70" style="margin-top: 5%;"></span>
				                       </div>
				                    </div>
				                    <div class="col-sm-6 col-md-6">
				                    <div class="" style="woi: 100%;float: right;font-size: 18px;font-family: Helvetica;text-align: right;font-weight: bold;"><img src="./resources/img/cl_calander.png" width="20" height="20" style=""><span style="padding-left:5px;" id="todayActionDate"> </span></div>
				                        <div style="width: 100%; text-align: right; margin-top: 25%;">
										  <span style="vertical-align: bottom;">Unique Accounts Actioned </span>
										  <span style="font-size: 22px;padding-left: 20px;" id="todayActionCount"></span>
										</div>
				                        <!-- Split button -->
				                        
				                    </div>
				                </div>
				            </div>
				        </div>
				        <div class="col-xs-12 col-md-6 " style="padding-left:5px;">
				            <div class="well well-sm happy_path_right">
				                <div class="row">
				                    <div class="col-xs-12 col-md-6 text-center" style="">
				                        <div class="" style="font-size: 18px;font-family: Helvetica;text-align: left;font-weight: bold;">This Week's Progress</div>
				                      
				                        <div style="width: 100%; height: 110px; background-color: transparent;" id="chartdiv">
				                        
				                        </div>
				                    </div>
				                    <div class="col-sm-6 col-md-6">
				                    <div class="" style="width: 100%;float: right;font-size: 18px;font-family: Helvetica;text-align: right;font-weight: bold;"><img src="./resources/img/cl_calander.png" width="20" height="20" style=""><span style="padding-left:5px;" id="weekActionDate"></span></div>
				                        <div style="width: 100%; text-align: right; margin-top: 25%;">
										  <span style="vertical-align: bottom;">Total Accounts Actioned </span>
										  <span style="font-size: 22px;padding-left: 20px;" id="weekAcctActioned"></span>
										</div>
				                        <!-- Split button -->
				                        
				                    </div>
				                </div>
				            </div>
				        </div>
		     		    </div>
						<!-- happy path UI ends -->
						
                         <div class="col-lg-6 col-md-6 col-sm-6 custPnlBody helios_bgcolor" >
                         <div style="display:none;">
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
							</form>
							</div>
							</div>
                           <div class="">
                           <div id="OrderDetailsInfoId" class="dataTable_wrapper">
                                <table class="table table-striped table-hover dt-responsive generalStyleToDoTable" id="dataTables-CustInfo" cellspacing="0" width="100%" cellspacing="0">
                                    <thead id="custHeader">
                                   <!--  <th>Status</th> -->
                                   <th>Call Order <h6 id="timeZoneFilter" tabindex="0" data-toggle="popover" data-trigger="click" title="" class="glyphicon glyphicon-chevron-down" style="float: right;outline:0;"></h6></th>
                                         <th>Company Name</th>
                                         <th><span>Potential Call Value
                                         <a id="potentialTooltip" href="#" style= "padding-left: 10px;height:19px;" data-placement="top" data-toggle="tooltip" title="" data-original-title="Based on account transactional history, it projects the potential revenue gained over the next 30 days."> 
								     	   <i class="fa fa-question-circle" style="height: 19px; margin-top: -2px;color: #f0f0f0;font-size: 170%;vertical-align: middle; box-shadow: none;">									       </i>
								          </a>
                                         </span> 
                                          
                                         </th>
                                    </thead>
                                    <tbody>
										
                                    </tbody>
                                </table>
                                
                            </div>
                            </div>
				            </div>
				            
				            <div class="col-lg-6 col-md-6 col-sm-6 helios_bgcolor" >
				            <div class="row"><div class="col-sm-12">
				            <table class="table table-striped table-hover dt-responsive no-footer dataTable generalStyleInfo" id="dataTables-CustInfo" cellspacing="0" width="100%" style="margin-top:1px !important">
                                    <thead id="custHeaderSearch"><tr role="row"><th>
                                    <!-- <div class="col-sm-1" style="padding-right: 0px;padding-left: 5px;float:right;">
										LL          
								      </div> -->
								      <div style="margin-left: 7px;width: 8%;float:left !important;display:block;" class="nav navbar-top-links navbar-right" id="alertIconContainer">
							                <li class="" style="padding-right: 10px;height: 20px;">
							                    <a id="alertTooltip" class="dropdown-toggle count-info"  href="#" style=" min-height: auto !important;margin-top: -5px;background:transparent !important;text-align:right;padding-left: 0px;padding-right: 0px;" data-placement="top" data-toggle="tooltip" title="" data-original-title="Count represents number of accounts in which Account Alerts tab has not been viewed/clicked.">
							                        <i id="bellIcon" class="fa fa-bell-o disableIcon" style="font-size: 220%;"></i>  <span class="label label-primary disableIconLbl" id="iconLabel" style="line-height: 12px;padding: 2px 5px;position: absolute;top: 0px;left:70%;"></span>
							                    </a>
							                </li>
							            </div>
						              <div class="col-sm-7" style="float: right;padding-left:0px;padding-right: 5px;">
								           <div id="imaginary_container"> 
								               <div class="input-group stylish-input-group">
								                   <input type="text" class="form-control" id="searchTextId" placeholder="Company Name OR Customer Number" onkeyup="javascript:showHideIcon(this);"  style="border-radius:0px !important;font-weight:normal !important;">
								                   <span class="input-group-addon">
								                       <button type="submit">
								                           <span class="glyphicon glyphicon-search" id="searchIconId" style="border-radius:0px !important;"></span>
								                       </button>  
								                   </span>
								               </div>
								           </div>
								       </div>
                                     <div class="col-sm-3" style="width:22%;padding-left:15px;display:block;" id="toggleOnOffContainer">
										<!-- <div style="float:right;width:100%" class="label-toggle-switch make-switch" data-text-label="Only Alert's">
									        <input type="checkbox" checked/>
									    </div>  -->
									    <input type="checkbox" data-toggle="toggle" data-style="ios" id="alertCheckId" onclick="onClickAlert(this);">
									  <!--   <input type="hidden" id="alertState" value="OFF" /> -->          
								     </div>
                                    
										       
                                    </th></tr></thead>
                            </table></div>
                            </div>
						     <div class="col-lg-12 col-md-12 col-sm-12 helios_bgcolor">
						      <div class="col-md-12" style="padding:10px;background-color:#d7d7d7;min-height: 776px;" id="rightContainer">
								<div class="panel panel-login" style="height:94%;">
									<div class="panel-heading" style="display:none">
									</div>
							<div class="panel-body height100" style="padding-top: 0px;padding-bottom:0px;">
							<div class="row height100">
					        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container height100">
					            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu height100">
					              <div class="list-group height100">
					              <a href="#" class="list-group-item text-center height25 padtop50 lastLive" >
					                  <h4 class=" " ></h4><span>Account Info and Activity History </span>
					                </a>
					                <a href="#" class="list-group-item text-center height25 padtop50 talkingPoint" style="padding-left: 5px;padding-right: 5px;">
					                  <h4 class="" ></h4><br/><span>Plays, Talking Points and Dispositions</span>
					                </a>
					                <a href="#" class="list-group-item text-center  height25 padtop50 accountCon" >
					                  <h4 class="" ></h4><br/>Account Contacts 
					                </a>
					                <a href="#" class="list-group-item text-center  height25 padtop35 alertIconEnable" >
					                  <h4 class="fa fa-bell-o bellIcon" ></h4><br/>Account Alerts 
					                </a>
					              </div>
					            </div>
					            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab height100 msg_container_base_comments">
					                <!-- flight section -->
					                <div class="bhoechie-tab-content active">
					                   <div>
								        <div class="well-sm" style="padding-top:1px;">
								          <form class="form-horizontal" action="" method="post">
								          <fieldset>
								            <!-- <legend class="text-center rowdetailsHead" style="border-bottom:1px solid #e3e3e3;padding-bottom:15px;">Last Live Contact Details and Activity History</legend> -->
								            	<div class="row">
											        <div class=livetabs >
											            
											            <div style="float:left;width:50%;" id="LiveTabOne">
											                <!-- <input type=radio name=tabs id=inputTab1 checked> -->
										                <label id="LastTabLableOne" for=tab1>Account / Last Live Info</label>
										                <div class="tab-content" id="LiveTabContentOne">
										                       <!-- section - 1 start -->
										                       <div style="background-color:#F5F5F5;width:100%;margin: 10px 0px 5px 0px;border-bottom: 4px solid #c9c9c9;border-top: 1px solid #e9e9e9;">
										                       <div class="form-group" style="padding-top:15px;line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;">
													             <!--  <label class="col-lg-4  col-md-4 control-label" for="compNm" style="text-align: left;color:#444444;">Company Name</label> -->
													              <div class="col-md-12">
													                <div class="form-control rowdetails roedetailsstyle" style="text-align:left;font-weight:bold;font-size:14px;" id="compNm"> </div>
													              </div>
													            </div>
													            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;">
													              <!-- <label class="col-lg-4  col-md-4 control-label" for="compWebsite" style="text-align: left;color:#444444;">Website</label> -->
													              <div class="col-md-12 ">
													                <div class="form-control rowdetails roedetailsstyle" style="text-align:left;font-weight:bold;font-size:14px;" id="compWebsite"> </div>
													              </div>
													            </div>
													            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;">
													              <!-- <label class="col-lg-4  col-md-4 control-label" for="custType" style="text-align: left;color:#444444;">Customer Type</label> -->
													              <div class="col-md-12 ">
													                <div class="form-control rowdetails roedetailsstyle" style="text-align:left;font-weight:bold;font-size:14px;" id="custType"></div>
													              </div>
													            </div>
													            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;">
													              <!-- <label class="col-lg-4  col-md-4 control-label" for="customerId" style=" text-align: left;color:#444444;">Customer ID</label> -->
													              <div class="col-md-12 " >
													              <!-- Change for Tenfold -->
													                <a class="form-control rowdetails roedetailsstyle" style="text-align:left;font-weight:bold;font-size:14px;pointer-events:none;cursor:default;" id="customerId"></a>
													              </div>
													            </div>
													            </div>
													              <!-- section - 1 ends -->
													              
													              <!-- section 2 start -->
													              <div  style="background-color:#F5F5F5;width:100%;margin: 10px 0px 5px 0px;border-bottom: 4px solid #c9c9c9;border-top: 1px solid #e9e9e9;">
													             <div class="form-group" style="padding-top:15px;line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													              <label class="col-lg-4  col-md-4 control-label" for="conDate" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Date:</label>
													              <div class="col-md-8 ">
													                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-size:14px;" id="conDate"> </div>
													              </div>
													            </div>
													            
													            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													              <label class="col-lg-4  col-md-4 control-label" for="ownName" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Owner Name:</label>
													              <div class="col-md-8 ">
													                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-size:14px;" id="ownName"></div>
													              </div>
													            </div>
													            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													              
													              <label class="col-lg-4  col-md-4 control-label" for="odrContactId" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Contact:</label>
													              <div class="col-md-8" >
													                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-size:14px;" id="odrContactId"> </div>
													              </div>
													            </div>
													             <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													              <label class="col-lg-4  col-md-4 control-label" for="subId" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Subject:</label>
													              <div class="col-md-8 ">
													                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="subId" style="text-align:right;font-size:14px;"></div>
													              </div>
													            </div>
													             <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													              <label class="col-lg-4  col-md-4 control-label" for="statId" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Status:</label>
													              <div class="col-md-8 ">
													                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="statId" style="text-align:right;font-size:14px;"></div>
													              </div>
													            </div>
													            
													           
													            <!-- Message body -->
													            <div class="form-group" style="padding-bottom:15px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
													        			<label class="col-md-12 control-label" for="email" style="text-align: left;padding-bottom:5px;color:#444444;font-weight:bold;font-size:14px;">Comments:</label>
																		<div class="input-group col-md-11" data-validate="length" data-length="5">
																			<!-- <textarea type="text" class="form-control rowdetails" name="validate-length" id="commentsId" placeholder="Validate Length" required="" style=" margin-left: 1%;padding: 15px; height:150px;resize:none;overflow:hidden;">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a undoubtable source. </textarea> -->
																			<div class="msg_container_base_comments roedetailsstyle" style="max-height: 75px;padding:0px 25px 0px 15px;font-size:14px;" id="commId">
					                                                        
					                                                        </div>
																		</div>
																	</div>
																</div>
																<!-- section 2 ends -->
																
																<!-- section - 3 start -->
										                       <div style="background-color:#F5F5F5;width:100%;margin: 10px 0px 5px 0px;border-bottom: 4px solid #c9c9c9;border-top: 1px solid #e9e9e9;">
														             <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
														              <label class="col-lg-4  col-md-4 control-label" for="YtdSales" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">YTD Sales:</label>
														              <div class="col-md-8 ">
														                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-size:14px;" id="ytdSales"></div>
														              </div>
														            </div>
														            
														            <div class="form-group" style="line-height: 30px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
														              <label class="col-lg-4  col-md-4 control-label" for="firstOrder" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Last Order:</label>
														              <div class="col-md-8 ">
														                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-size:14px;" id="lastOrder"></div>
														              </div>
														            </div>
														            <!-- <div class="form-group" style="line-height: 20px;padding-left: 10px;width:100%;margin-bottom: 5px;margin-left:0px;">
														              
														              <label class="col-lg-4  col-md-4 control-label" for="lastOrder" style="text-align: left;color:#444444;font-weight:bold;font-size:14px;">Last Order</label>
														              <div class="col-md-8" >
														                <div class="form-control rowdetails roedetailsstyle padleft0 padright5" style="text-align:right;font-weight:bold;font-size:14px;" id="lastOrder"> 8/14/2017</div>
														              </div>
														            </div> -->
													            </div>
													              <!-- section - 3 ends -->
													            <!-- Form actions -->
											                </div>
											            </div><!-- Tab one ends -->
											            
											            <div style="float:right;width:50%;" id="LiveTabTwo">
											                <!-- <input type=radio name=tabs id=inputTab1 > -->
											                <label id="LastTabLableTwo" for="tab2" style="margin-right:10px;">Activity History</label>
											                <div class="tab-content" id="LiveTabContentTwo">
											                       <!-- <div  style="width:100%;text-align: -webkit-center;height: 500px;padding-top: 30%;color: #c7c7c7;font-size: 16px;font-family: helvetica;font-weight: 600;display:block !important;">
											                         Coming soon....
											                       </div> -->
											                </div>
											            </div>
											        </div>
												</div> 
								           
								          </fieldset>
								          </form>
								        </div>
								      </div>
					                </div>
					                <!-- train section -->
					                <div class="bhoechie-tab-content" id="CtaContainer">
					                   <div class="well-sm" style="display:none">
								          <form class="form-horizontal" action="" method="post">
								          <fieldset>
								            <legend class="text-center rowdetailsHead" style="margin-bottom:0px;">Talking Points</legend>
								          </fieldset>
								          </form>
								          
								        </div>
								        <!-- Churn/Decliner tab start -->
								        <div class="login-signup" id="ctaDescription">
										      <div class="row" style="margin-right:0px">
										        <div class="col-sm-12 nav-tab-holder">
										        <ul class="nav nav-tabs row" role="tablist">
										          <li role="presentation" class="col-sm-12 active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" aria-expanded="true">Churn</a></li>
										          <li role="presentation" class="col-sm-12"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" aria-expanded="false">Decliner</a></li>
										          <li role="presentation" class="col-sm-12"><a href="#otherprofile" aria-controls="otherprofile" role="tab" data-toggle="tab" aria-expanded="false" >Other</a></li>
										        </ul>
										      </div>
										
										      </div>
										
										
										      <div class="tab-content">
										        <div role="tabpanel" class="tab-pane active" id="home">
										          <div class="row" style="margin-right:0px">
										
										            <div class="col-sm-12 mobile-pull">
										            <div id="bhoechie-tab-container-Churn"></div>
										              <article role="login" style="padding:0px">
										              <!-- <h4 id="ctaNameDescChurn" class="text-center"><i class="fa fa-lock"></i>Churn description</h3> -->
										              <!-- Churn status/subject/disp starts -->
										              <div class="panel-body" style="padding-bottom: 0px;padding-top: 0px;">
														 <div class="row" style="padding-top:10px;">
														     <div class="col-md-12 col-sm-12" style="padding-top: 15px;padding-right:0px;border: 1px;margin-top: 15px;border-top-style: dashed;border-top-color: darkgray;">
														     
														<!-- subject start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="subjectInputText-Churn" placeholder="Select or Enter Subject" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;background-color:#eee;" maxlength="80">
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="subjectList-Churn" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
														   <span id="oldSubjectInputText-Churn" style="display:none;"></span>
														</div>
														   </div>
														</div>
														<!-- subject close -->
														
														 <!-- disp start -->       
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<!-- <label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family:Arial,sans-serif;font-weight: bold;color:#666;">Subject:</label> -->
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="statusVal-Churn" placeholder="Select Disposition" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="statusList-Churn" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldStatusVal-Churn" style="display:none;"></span>
													     <span id="linkTaskId-Churn" style="display:none;"></span>
														</div>
														   </div>
														</div>       
														<!-- disp close -->   
														
														<!-- contact start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="orderContactVal-Churn" placeholder="SFDC Contact" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="orderContactList-Churn" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldOrderContactVal-Churn" style="display:none;"></span>
													     <span id="orderContactId-Churn" style="display:none;"></span>
														 </div>
														 </div>
														</div> 
														<!-- contact close -->
														
														<div>
														  </div>
														</div>
									                   <div class="login-box col-md-12 col-sm-12">
									                    <form role="form">
														     
														<div class="form-group" id="commentForm-Churn" style=" padding: 10px;margin-bottom:7px;">
														
														<textarea class="form-control" id="commentId-Churn" onKeyUp="limitText(this.form.commentId-Churn,this.form.countdown-Churn,1000);" maxlength="1000" 
														onKeyDown="limitText(this.form.commentId-Churn,this.form.countdown-Churn,1000);" placeholder="Comments (up to 1000 characters) " style="resize: none;font-size: 12px;color: dimgray;height:85px !important;"></textarea>
														<%-- <font size="1" style="font-family:Robot,sans-serif;color: #4d4d4d;font-size: 11px;">(Characters left: <span id="charLimit-Churn">1000</span>)</font> --%>
														<input readonly type="hidden" name="countdown-Churn" size="3" value="1000"/>
														<span id="oldCommentId-Churn" style="display:none;"></span>
														</div>
														  
														</form>
														<div>
														   <div class="input-group" style="text-align: right;width: 100%;padding-bottom:10px;float: right;">
														<p style="float:left;"><a href="javascript:void()" target="_blank" id="ctaCreateOpp-Churn" style="float: right;text-decoration:underline;color: #0099cc;font-size: 14px;">Create new opportunity</a></p>
														<div style="float:right;margin-bottom:10px;">
														<button type="button" id="applyCallToAction-Churn" class="btn btn-default submit" data-toggle="modal" style="border-radius: 0px;background-color: #ffffff;border: solid 1px #0099cc;font-weight:bold;color: #3c3a3a;"><i class="fa fa-paper-plane" aria-hidden="true" style="display:none;"></i>Save to SFDC</button>
														  </div>
														   </div>    
														 
														</div> 
														 </div>
														</div>
													 </div>
										              <!-- Churn status/subject/disp ends -->
										              <footer role="signup" class="text-center" style="display:none;">
										                <ul>
										                  <li>
										                    <a href="#">Terms of Use</a>
										                  </li>
										                  
										                </ul>
										              </footer>
										              </article>
										              
										            </div>
										          </div>
										          <!-- end of row -->
										        </div>
										        <!-- end of home -->
										
										      <div role="tabpanel" class="tab-pane" id="profile">
										        <div class="row" style="margin-right:0px">
										
										          <div class="col-sm-12 mobile-pull" style="">
										           <div id="bhoechie-tab-container-Decliner"></div>
										            <article role="login" style="padding:0px">
										             <!--  <h3 class="text-center"><i class="fa fa-lock"></i>Decliner description</h3> -->
										              <!-- Decliner status/subject/disp starts -->
										              <div class="panel-body" style="padding-bottom: 0px;padding-top: 0px;">
														 <div class="row" style="padding-top:10px;">
														     <div class="col-md-12 col-sm-12" style="padding-top: 15px;padding-right:0px;border: 1px;margin-top: 15px;border-top-style: dashed;border-top-color: darkgray;">
														     
														<!-- subject start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="subjectInputText-Decliner" placeholder="Select or Enter Subject" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;background-color:#eee;" maxlength="80">
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="subjectList-Decliner" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
														   <span id="oldSubjectInputText-Decliner" style="display:none;"></span>
														</div>
														   </div>
														</div>
														<!-- subject close -->
														
														 <!-- disp start -->       
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<!-- <label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family:Arial,sans-serif;font-weight: bold;color:#666;">Subject:</label> -->
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="statusVal-Decliner" placeholder="Select Disposition" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="statusList-Decliner" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldStatusVal-Decliner" style="display:none;"></span>
													     <span id="linkTaskId-Decliner" style="display:none;"></span>
														</div>
														   </div>
														</div>       
														<!-- disp close --> 
														
														
														<!-- contact start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="orderContactVal-Decliner" placeholder="SFDC Contact" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="orderContactList-Decliner" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldOrderContactVal-Decliner" style="display:none;"></span>
													     <span id="orderContactId-Decliner" style="display:none;"></span>
														 </div>
														 </div>
														</div> 
														<!-- contact close -->
														
														<div>
														  </div>
														</div>
									                   <div class="login-box col-md-12 col-sm-12">
									                    <form role="form">
														     
														<div class="form-group" id="commentForm-Decliner" style=" padding: 10px;margin-bottom:7px;">
														
														<textarea class="form-control" id="commentId-Decliner" onKeyUp="limitText(this.form.commentId-Decliner,this.form.countdown-Decliner,1000);" maxlength="1000" 
														onKeyDown="limitText(this.form.commentId-Decliner,this.form.countdown-Decliner,1000);" placeholder="Comments (up to 1000 characters) " style="resize: none;font-size: 12px;color: dimgray;height:85px !important;"></textarea>
														<%-- <font size="1" style="font-family:Robot,sans-serif;color: #4d4d4d;font-size: 11px;">(Characters left: <span id="charLimit-Churn">1000</span>)</font> --%>
														<input readonly type="hidden" name="countdown-Decliner" size="3" value="1000"/>
														<span id="oldCommentId-Decliner" style="display:none;"></span>
														</div>
														  
														</form>
														<div>
														   <div class="input-group" style="text-align: right;width: 100%;padding-bottom:10px;float: right;">
														<p style="float:left;"><a href="javascript:void()" target="_blank" id="ctaCreateOpp-Decliner" style="float: right;text-decoration:underline;color: #0099cc;font-size: 14px;">Create new opportunity</a></p>
														<div style="float:right;margin-bottom:10px;">
														<button type="button" id="applyCallToAction-Decliner" class="btn btn-default submit" data-toggle="modal" style="border-radius: 0px;background-color: #ffffff;border: solid 1px #0099cc;font-weight:bold;color: #3c3a3a;"><i class="fa fa-paper-plane" aria-hidden="true" style="display:none;"></i>Save to SFDC</button>
														  </div>
														   </div>    
														 
														</div> 
														 </div>
														</div>
													 </div>
										              <!-- Decliner status/subject/disp ends -->
										              <footer role="signup" class="text-center" style="display:none;">
										                <ul>
										                  <li>
										                    <a href="#">Terms of Use</a>
										                  </li>
										                  
										                </ul>
										              </footer>
										            </article>
										            
										          </div>
										
										          
										        </div>
										      </div><!-- declinet tab ends -->
										      
										      <!-- otherprofile starts -->
										      <div role="tabpanel" class="tab-pane" id="otherprofile">
										        <div class="row" style="margin-right:0px">
										
										          <div class="col-sm-12 mobile-pull" style="">
										           <div id="bhoechie-tab-container-otherprofile"></div>
										            <article role="login" style="padding:0px">
										             <!--  <h3 class="text-center"><i class="fa fa-lock"></i>Decliner description</h3> -->
										              <!--otherprofile status/subject/disp starts -->
										              <div class="panel-body" style="padding-bottom: 0px;padding-top: 0px;">
														 <div class="row" style="padding-top:10px;">
														     <div class="col-md-12 col-sm-12" style="padding-top: 15px;padding-right:0px;border: 1px;margin-top: 15px;border-top-style: dashed;border-top-color: darkgray;">
														     
														<!-- subject start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="subjectInputText-otherprofile" placeholder="Select or Enter Subject" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;background-color:#eee;" maxlength="80">
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="subjectList-otherprofile" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
														   <span id="oldSubjectInputText-otherprofile" style="display:none;"></span>
														</div>
														   </div>
														</div>
														<!-- subject close -->
														
														 <!-- disp start -->       
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<!-- <label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family:Arial,sans-serif;font-weight: bold;color:#666;">Subject:</label> -->
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="statusVal-otherprofile" placeholder="Select Disposition" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="statusList-otherprofile" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldStatusVal-otherprofile" style="display:none;"></span>
													     <span id="linkTaskId-otherprofile" style="display:none;"></span>
														</div>
														   </div>
														</div>       
														<!-- disp close --> 
														
														
														<!-- contact start -->
														<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:10px;">
														<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left:0px;">
														<input type="text" id="orderContactVal-otherprofile" placeholder="SFDC Contact" class="form-control" style="height:auto;border: 1px solid #ccc !important;float: left;width: 90%;margin-bottom:0px;box-shadow: none;font-size: 14px;font-family: helvetica;color: #4d4d4d;border-radius: 0px;" maxlength="80" disabled>
														<div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
														<button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="padding: 14px;border-color: #ccc;border-radius: 0px;"> <span class="caret" style="color:#a0a0a0"></span></button>
														 <ul class="dropdown-menu pull-right msg_container_base" id="orderContactList-otherprofile" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
														 </ul>
													     <span id="oldOrderContactVal-otherprofile" style="display:none;"></span>
													     <span id="orderContactId-otherprofile" style="display:none;"></span>
														 </div>
														 </div>
														</div> 
														<!-- contact close -->
														
														<div>
														  </div>
														</div>
									                   <div class="login-box col-md-12 col-sm-12">
									                    <form role="form">
														     
														<div class="form-group" id="commentForm-otherprofile" style=" padding: 10px;margin-bottom:7px;">
														
														<textarea class="form-control" id="commentId-otherprofile" onKeyUp="limitText(this.form.commentId-otherprofile,this.form.countdown-otherprofile,1000);" maxlength="1000" 
														onKeyDown="limitText(this.form.commentId-otherprofile,this.form.countdown-otherprofile,1000);" placeholder="Comments (up to 1000 characters) " style="resize: none;font-size: 12px;color: dimgray;height:85px !important;"></textarea>
														<%-- <font size="1" style="font-family:Robot,sans-serif;color: #4d4d4d;font-size: 11px;">(Characters left: <span id="charLimit-Churn">1000</span>)</font> --%>
														<input readonly type="hidden" name="countdown-otherprofile" size="3" value="1000"/>
														<span id="oldCommentId-otherprofile" style="display:none;"></span>
														</div>
														  
														</form>
														<div>
														   <div class="input-group" style="text-align: right;width: 100%;padding-bottom:10px;float: right;">
														<p style="float:left;"><a href="javascript:void()" target="_blank" id="ctaCreateOpp-otherprofile" style="float: right;text-decoration:underline;color: #0099cc;font-size: 14px;">Create new opportunity</a></p>
														<div style="float:right;margin-bottom:10px;">
														<button type="button" id="applyCallToAction-otherprofile" class="btn btn-default submit" data-toggle="modal" style="border-radius: 0px;background-color: #ffffff;border: solid 1px #0099cc;font-weight:bold;color: #3c3a3a;"><i class="fa fa-paper-plane" aria-hidden="true" style="display:none;"></i>Save to SFDC</button>
														  </div>
														   </div>    
														 
														</div> 
														 </div>
														</div>
													 </div>
										              <!-- otherprofile status/subject/disp ends -->
										             
										            </article>
										            
										          </div>
										
										          
										        </div>
										      </div>
										      <!-- otherprofile ends -->
										      
										      
										      
										  </div>
										  </div>
								        <!-- Churn/Decliner tab closed -->
										
					                </div>
					                  <div class="bhoechie-tab-content">
					                   <div class="well-sm">
								         <form class="form-horizontal" action="" method="post" id="accData">
											 
								          </form>
								        </div>
					                 </div>
					                 
					                 <!--  -->
					                 <div class="bhoechie-tab-content" style="padding-left: 0px;">
					                   <div class="well-sm">
					                   <form class="form-horizontal" action="" method="post">
								          <fieldset>
								            <legend class="text-center rowdetailsHead" style="border-bottom:1px solid #e3e3e3;padding-bottom:15px;">Account Alerts</legend>
								          </fieldset>
								          </form>
								          <!-- container start  -->
							             <div id="alertContainer" class="" >
                                                <!-- container inner start  -->
									            <div class="panel-group" id="accordion_reg" role="tablist" aria-multiselectable="true">
									              <div class="panel panel-default bordernone" style="margin-top:0px;">
									                <div class="panel-heading padding0" role="tab" id="headingOne_reg">
									                  <h4 class="panel-title">
									                    <div class="" id="boxListing">
											            <div class="row">
											                <div class="col-sm-12"> 
											
											                    <!-- Begin Listing: 609 W GRAVERS LN-->
											                    <div class="brdr box-shad property-listing selectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseOne_reg" aria-expanded="false" aria-controls="collapseOne_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;">
											                        <div class="media medCls" >
											                            <a class="pull-left" href="#" target="_parent">
											                           <!--  <img alt="image"  width="42" height="50" class="img-responsive" src="http://www.shoepalace.com/images/icons/calendar.svg" style="max-width: 50px;"> -->
											                            </a>
											
											                            <div class="clearfix visible-sm"></div>
											
											                            <div class="media-body fnt-smaller">
											                                <a href="#" target="_parent"></a>
											
											                                <h4 class="media-heading">
											                                  <a class="selHead" href="#" target="_parent" style="font-size: 16px;text-decoration:none;">
											                                     Premium Renewal Pending
											                                  <small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;"></small>
											                                  <small class="pull-right selText" style="padding-top: 5px;font-size: 14px;">7/17/2017</small>
											
											                                </a></h4>
											                       
											
											                                <span class="fnt-smaller fnt-lighter fnt-arial selText">Courtesy of HS Fox...</span>
											                            </div>
											                        </div>
											                    </div><!-- End Listing-->
											                </div>
											            </div><!-- End row -->
														</div><!-- End container -->
									                  </h4>
									                </div>
									                <div id="collapseOne_reg" class="panel-collapse collapse selectedContainerBody" role="tabpanel" aria-labelledby="headingOne_reg">
									                  <div class="panel-body bordernone margintopzero panelBodyText">
									                   You most recent e Statement for your It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.
									                  </div>
									                </div>
									              </div>
									              <div class="panel panel-default bordernone" style="margin-top:0px;">
									                <div class="panel-heading padding0" role="tab" id="headingTwo_reg">
									                  <h4 class="panel-title">
									                    <div class="" id="boxListing">
												            <div class="row">
												                <div class="col-sm-12"> 
												
												                    <!-- Begin Listing: 609 W GRAVERS LN-->
												                    <div class="brdr box-shad property-listing unselectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseTwo_reg" aria-expanded="false" aria-controls="collapseTwo_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;">
												                        <div class="media medCls">
												                            <a class="pull-left" href="#" target="_parent">
												                            <!-- <img alt="image"  width="42" height="50" class="img-responsive" src="http://web.cse.ohio-state.edu/~sarkhel.5/assets/img/mail.png" style="max-width: 50px;"> -->
												                            </a>
												
												                            <div class="clearfix visible-sm"></div>
												
												                            <div class="media-body fnt-smaller">
												                                <a href="#" target="_parent"></a>
												
												                                <h4 class="media-heading">
												                                  <a class="unselHead" href="#" target="_parent" style="font-size: 16px;text-decoration:none;">
												                                    Bounced Email Contact
												                                  <small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;"></small>
												                                  <small class="pull-right unselText" style="padding-top: 5px;font-size: 14px;">7/17/2017</small>
												
												                                </a></h4>
												                       
												
												                                <span class="fnt-smaller fnt-lighter fnt-arial unselText" ">[First Name] [Last Name] ...</span>
												                            </div>
												                        </div>
												                    </div><!-- End Listing-->
												                </div>
												            </div><!-- End row -->
									                       </div><!-- End container -->
									                  </h4>
									                </div>
									                <div id="collapseTwo_reg" class="panel-collapse collapse unselectedContainerBody" role="tabpanel" aria-labelledby="headingTwo_reg">
									                  <div class="panel-body bordernone margintopzero panelBodyText">
									                  You most recent e Statement for your It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.
									                  </div>
									                </div>
									              </div>
									              <div class="panel panel-default bordernone" style="margin-top:0px;">
									                <div class="panel-heading padding0" role="tab" id="headingThree_reg">
									                  <h4 class="panel-title">
									                    <div class="" id="boxListing">
											            <div class="row">
											                <div class="col-sm-12"> 
											
											                    <!-- Begin Listing: 609 W GRAVERS LN-->
											                    <div class="brdr box-shad property-listing selectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseThree_reg" aria-expanded="false" aria-controls="collapseThree_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;">
											                        <div class="media medCls">
											                            <a class="pull-left" href="#" target="_parent">
											                            <img alt="image"  width="45" height="50" class="img-responsive" src="https://www.keyvisathailand.com/wp-content/uploads/credit-card.png" style="max-width: 50px;"></a>
											
											                            <div class="clearfix visible-sm"></div>
											
											                            <div class="media-body fnt-smaller">
											                                <a href="#" target="_parent"></a>
											
											                                <h4 class="media-heading">
											                                  <a class="selHead" href="#" target="_parent" style="font-size: 16px;text-decoration:none;">
											                                     Credit Card Hold
											                                  <small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;"></small>
											                                  <small class="pull-right selText" style="padding-top: 5px;font-size: 14px;">7/17/2017</small>
											
											                                </a></h4>
											                       
											
											                                <span class="fnt-smaller fnt-lighter fnt-arial selText" >Current membership is in ...</span>
											                            </div>
											                        </div>
											                    </div><!-- End Listing-->
											                </div>
											            </div><!-- End row -->
													   </div><!-- End container -->
									                  </h4>
									                </div>
									                <div id="collapseThree_reg" class="panel-collapse collapse selectedContainerBody" role="tabpanel" aria-labelledby="headingThree_reg">
									                  <div class="panel-body bordernone margintopzero panelBodyText">
									                   You most recent e Statement for your It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.
									                  </div>
									                </div>
									              </div>
									              
									               <div class="panel panel-default bordernone" style="margin-top:0px;">
									                <div class="panel-heading padding0" role="tab" id="headingThree_reg">
									                  <h4 class="panel-title">
									                    <div class="" id="boxListing">
												            <div class="row">
												                <div class="col-sm-12"> 
												
												                    <!-- Begin Listing: 609 W GRAVERS LN-->
												                    <div class="brdr box-shad property-listing unselectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseFour_reg" aria-expanded="false" aria-controls="collapseFour_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;">
												                        <div class="media medCls">
												                            <a class="pull-left" href="#" target="_parent">
												                            <img alt="image"  width="42" height="50" class="img-responsive" src="https://vanmates.com/wp-content/uploads/2016/04/vanmates-money-icon-3-01.png" style="max-width: 50px;"></a>
												
												                            <div class="clearfix visible-sm"></div>
												
												                            <div class="media-body fnt-smaller">
												                                <a href="#" target="_parent"></a>
												
												                                <h4 class="media-heading">
												                                  <a class="unselHead" href="#" target="_parent" style="font-size: 16px;text-decoration:none;">
												                                     Price Change
												                                  <small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;"></small>
												                                  <small class="pull-right unselText" style="padding-top: 5px;font-size: 14px;">7/17/2017</small>
												
												                                </a></h4>
												                       
												
												                                <span class="fnt-smaller fnt-lighter fnt-arial unselText">Dear Value Customer...</span>
												                            </div>
												                        </div>
												                    </div><!-- End Listing-->
												                </div>
												            </div><!-- End row -->
														</div><!-- End container -->
									                  </h4>
									                </div>
									                <div id="collapseFour_reg" class="panel-collapse collapse unselectedContainerBody" role="tabpanel" aria-labelledby="headingThree_reg">
									                  <div class="panel-body bordernone margintopzero panelBodyText">
									                   Dear Value Customer, You most recent e Statement for your It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.
									                   </div>
									                </div>
									              </div>
									            </div><!-- container inner ends  -->
                                                  
									    </div> <!-- container ends  -->
								        </div>
					                 </div>
					                 <!--  -->
					            </div>
					        </div>
					  </div>
					</div>
								
						<!-- view info button start -->
						<div class="col-sm-12 nav-tab-holder" style="text-align: -webkit-center;padding-left: 0px;padding-right: 0px;padding-top: 10px;">
						<ul class="nav nav-tabs row ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist" style="">
						<li role="tab" class="col-sm-6 active ui-state-default ui-corner-top ui-tabs-active ui-state-active" tabindex="0" aria-controls="home" aria-selected="true" aria-expanded="true" style="width: 100%;">
						  <a id="viewAllInfo" href="javascript:void();"  class="ui-tabs-anchor" style="text-decoration: underline;background-color: #012c43;"><span><img src="./resources/img/cl_view_all.png" width="27" height="22"></span><span style="cursor: pointer;padding-left:2px;">View Account Dashboard</span></a></li>
						  </ul>
						</div>
						<!-- view info button ends -->
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
        </div>
    </div>
    <div id="rewardsPopoverId" style="display:none;"></div>
    <div style="display:none;" id="ctaCategoryStatus"></div>
    <div style="display:none;" id="alertActiveStatus"></div>
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
  <!-- no change modal start -->
  <div class="modal fade" id="NoChangeModal" role="dialog" style="z-index:1102">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span id="headerTitleId">Action Needed</span>
		        </div>
		          <div class="modal-body" id="noChangeBodyId" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;">
                  
		          
		          </div>
		        <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;font-weight:bold;">OK</button>
	        </div>
	      </div>
	    </div>
	  </div>
  <!-- no change modal end -->
  <!-- CTA sfdc confirmation modal start -->
  <div class="modal fade" id="ctaSfdcConfModal" role="dialog" style="z-index:1101">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span >Save to SFDC</span>
		        </div>
		          <div class="modal-body" id="ctaSfdcConfModalBody" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;">
		          
		          
		          </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-primary" id="ctaSfdcYesId" data-dismiss="modal" style="border-radius: 2px !important; background-color: #23527c; border-color: #23527c;font-weight:bold;font-size:14px">YES</button>
                  <button type="button" class="btn btn-secondary" id="ctaSfdcNoId" data-dismiss="modal" style="border-radius: 2px !important; background-color: gainsboro; border-color: gainsboro;color: black;font-weight: bold;font-size: 14px;">NO</button>
		        </div>
		      </div>
		    </div>
		  </div>
  <!-- sfdc confirmation modal end -->
    <div id="timeZoneContent" style="display:none;"></div>
    <div id="alertCounter" style="display:none;">
    </div>
     <div id="currDateTime" style="display:none;"></div>
     <div id="RefreshTimeStamp" style="display:none;"></div>
      
    <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="layoutObj" id="layoutObj" value='${requestScope["layoutvo"]}' />
	<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=accID %>" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
	<input type="hidden" name="sfdcAppBaseUrl" id="sfdcAppBaseUrl" value="${requestScope['SFDC_APP_BASE_URL']}" />
	<input type="hidden" name="division" id="division"/>
	
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
var scripts = ['jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','bootstrap-dropdown.js','dataTables.bootstrap.min.js','global-default-exception-handler.js','Date.js','progressbar.js','amcharts.js','serial.js','common.js','bootstrap-toggle-min.js','moment.js','Cdm_To_Do_List.js','sb-admin-2.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js'];

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

</body>
</html>
