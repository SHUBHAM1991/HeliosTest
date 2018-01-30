<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<HTML>
<HEAD>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<link href="./resources/stylesheet/commonStyles.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./resources/stylesheet/common-print.css?helios_version=${initParam.helios_ver}" type="text/css" media="print" >

<script>
(function(){
    /**
     * Increment and return the local storage version for a given JavaScript file by name
     * @param  {string} scriptName  Name of JavaScript file to be versioned (including .js file extension)
     * @return {integer}            New incremented version number of file to pass to .js GET parameter
     */
    var incrementScriptOrCssVer = function(scriptOrCssName){
        var s_c_version = parseInt(localStorage.getItem(scriptOrCssName));
        // Simple validation that our item is an integer
        if(s_c_version > 0){
        	s_c_version += 1;
        } else {
            // Default to 1
            s_c_version = 1;
        }
        localStorage.setItem(scriptOrCssName, s_c_version);
        return s_c_version;
    };
    // Set your scripts that you want to be versioned here
    //var scriptsOrCss = ['canvas.js','common.js'];
    var scriptsOrCss = ['common.js'];
    // Loop through each script name and append our new version number
        scriptsOrCss.map(function(scriptOrCss){
        var currentScriptOrCssVer = ${initParam.helios_ver} /*'Ver6.0' incrementScriptOrCssVer(scriptOrCss); */
        document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + scriptOrCss + "?helios_version=" + currentScriptOrCssVer + " '><\/script>");
    });
})();
</script>

<style type="text/css">

.legend { list-style: none; padding: 0px;margin: 0px; }
.legend li { float: left; margin-right: 10px; }
.legend span { float: left; width: 12px; height: 12px;margin-top:3px;margin-bottom: 4px;margin-right: 5px; margin-left: 5px; }

.ui-state-highlight {
	background: transparent !important;
	border: none !important;
}

.canvasjs-chart-canvas {
	/*   width:100% !important; */
	
}

.ui-datepicker-calendar {
	display: none;
}

.popover {
	max-width: 330px !important;
	color: #4D4D4D !important;
}

.popover-content {
	font-family: Helvetica !important;
	font-weight: bold !important;
	box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.2) !important
}

div.amcharts-chart-div {
	height: 286px!mportant;
}

div.amcharts-chart-div a {
	display: none !important;
}

div.amcharts-chart-div svg {
	width: 100% !important;
}

.ui-datepicker-next, .ui-datepicker-prev, .ui-datepicker-current {
	display: none !important;
}

.popoverBasic {
	max-width: 400px !important;
}

.tooltip {
	z-index: 1151 !important;
}
/* .tooltip-inner {
    white-space:pre-wrap;
} */
a#mob+div.tooltip {
	text-align: left;
}

.offer {
	background: #fff;
	border: 1px solid #ddd;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
	margin: 15px 0;
	overflow: hidden;
}

.offer-default {
	border-color: #999999;
}

.offer-default .shape {
	border-color: transparent #999999 transparent transparent;
}

.offer-content {
	padding: 0 20px 10px;
}


  [id^='editId']{display:none !important;}
  [id^='Hdr'] div ul{color:inherit !important;}
</style>


</HEAD>
<BODY>
		
<form role="form"  id="customerForm" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['custNo']}"/>
	<input name="accId" id="accId" type="hidden" value="${requestScope['accId']}"/>
	<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
	<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
	<input class="form-control"  name="sliderSubPlaysItem" id="sliderSubPlaysItem" type="hidden" value="${requestScope['sliderSubPlaysItem']}" />		
	<input class="form-control"  name="iStart" id="iStart" type="hidden" value="${requestScope['iStart']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
	<input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
	<input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
	<input class="form-control"  name="subCallToActionSegName" id="subCallToActionSegName" type="hidden" value="${requestScope['subCallToActionSegName']}" />
	<input class="form-control"  name="alertState" id="alertState" type="hidden" value="${requestScope['alertState']}" />
</form>
<form action="./home_cust_profiles" id="customerForm1" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
	<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
	<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
	<input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
	<input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
</form>
<form action="./cp_online_retail" id="customerFormOnlineRetail" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
	<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
	<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
	<input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
	<input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
	<input class="form-control"  name="alertState" id="alertState" type="hidden" value="${requestScope['alertState']}" />
</form>

		<div  class="page-content-wrapper" style="background-color:#1d2939;">
			<div class="printableDashboard page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">Session Logged Out</h4>
							</div>
							<div class="modal-body">
								 Redirecting to the Login Page .....
							</div>
							<div class="modal-footer">
								<button type="button" class="btn blue" data-dismiss="modal">Continue</button>
								
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<div class="theme-panel">
					<div id="resetIcon" style="display:none;" class="toggler tooltips" data-container="body" data-placement="left" data-html="true" data-original-title="Click to reset the page">
						<!-- <i class="icon-settings"></i> -->
						<!-- <img src="http://findicons.com/files/icons/1963/colorcons_blue/128/undo.png"/> -->
					</div>
					<!-- <div class="toggler-close">
						<i class="icon-close"></i>
					</div> -->
					<!-- <div class="theme-options">
						<div class="theme-option theme-colors clearfix">
							<span>
							THEME COLOR </span>
							<ul>
								<li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="Default">
								</li>
								<li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="Grey">
								</li>
								<li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="Blue">
								</li>
								<li class="color-dark tooltips" data-style="dark" data-container="body" data-original-title="Dark">
								</li>
								<li class="color-light tooltips" data-style="light" data-container="body" data-original-title="Light">
								</li>
							</ul>
						</div>
						<div class="theme-option">
							<span>
							Theme Style </span>
							<select class="layout-style-option form-control input-small">
								<option value="square" selected="selected">Square corners</option>
								<option value="rounded">Rounded corners</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Layout </span>
							<select class="layout-option form-control input-small">
								<option value="fluid" selected="selected">Fluid</option>
								<option value="boxed">Boxed</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Header </span>
							<select class="page-header-option form-control input-small">
								<option value="fixed" selected="selected">Fixed</option>
								<option value="default">Default</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Top Dropdown</span>
							<select class="page-header-top-dropdown-style-option form-control input-small">
								<option value="light" selected="selected">Light</option>
								<option value="dark">Dark</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Sidebar Mode</span>
							<select class="sidebar-option form-control input-small">
								<option value="fixed">Fixed</option>
								<option value="default" selected="selected">Default</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Sidebar Style</span>
							<select class="sidebar-style-option form-control input-small">
								<option value="default" selected="selected">Default</option>
								<option value="compact">Compact</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Sidebar Menu </span>
							<select class="sidebar-menu-option form-control input-small">
								<option value="accordion" selected="selected">Accordion</option>
								<option value="hover">Hover</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Sidebar Position </span>
							<select class="sidebar-pos-option form-control input-small">
								<option value="left" selected="selected">Left</option>
								<option value="right">Right</option>
							</select>
						</div>
						<div class="theme-option">
							<span>
							Footer </span>
							<select class="page-footer-option form-control input-small">
								<option value="fixed">Fixed</option>
								<option value="default" selected="selected">Default</option>
							</select>
						</div>
					</div> -->
				</div>
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<!-- 
               <div style="color:#1d2939" class="page-title">
				<span class="letterSpace" style="border-radius: 40px !important;border: 2px solid #000;padding-right: 5px !important;padding-left: 5px !Important;padding-top: 2px;padding-bottom: 2px;"><i class="fa fa-home" style="font-size:120%;border-radius: 10px;"></i></span><span style="padding-left: 10px;font-size: 32px;/* font-family: Helvetica Neue; */font-size: 26px;/* font-weight: bold; */">Dashboard</span>
				<button   onclick="location.href='./home_cust_profiles?reqCustNum=${requestScope['accId']}&filterBy=${requestScope['filterBy']}&subPlayParams=${requestScope['subPlayParams']}'" type="button" style="float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="backButton" >Back to Call List</button>
				
				<button   type="button" style="float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printDashboardBtn" >Print</button>
				<div style="float:right">
				<div id="training" class="toolTip" style="border:1px solid #004c74;font-size:12px; font-family: Helvetica; 
					color: #000000; background-color: #FFEBCD; position:absolute;"></div>
				</div>		
				<div style="float:right">
				<a href="#" data-toggle="tooltip" title="Training" data-placement="left">	
					<img src="./resources/img/training.gif" width="20" height="20" onclick="javascript:showTrainingPopUp()"></img></a>
				</div>
				</div>
				 -->
				<!-- <div  style="float: right; padding-right: 10px;"><button   onclick="location.href='./home_cust_profiles?reqCustNum=${requestScope['accId']}&filterBy=${requestScope['filterBy']}'" type="button" style="margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="backButton" >Back</button></div> -->
				
				<div class="row sortableRow">
				<div class="col-lg-12">
				
				
				<div class="portlet box green" id="cpId" style="border:1px solid #004c74;background-color:#004c74;">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span class="letterSpace">Customer Profile </span>  
								</div>
							<!-- <div class="tools">
									<a class="fa fa-chevron-up" id="showHideId4" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div> -->
							</div>
							<div class="portlet-body" id="cpContent" style="height:100px">
								<div class="col-md-6 col-sm-6 portlet-body" id="cpInfoContent" style="height:auto">
									<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
                                   	<div style="height:auto" class="" id="compNameCont" ><span class="letterSpace" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#333;" class="letterSpace">  </span></div>
									<div style="height:auto" class=""> <span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Customer Number: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold" id="custNum" class="letterSpace">  </span></div>
									<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Contract Type: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custType" class="letterSpace"></span></div>
                                    <div style="height:auto; display: none" class="" id="ltsInfoColumn"><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Premium Lifetime Savings: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="ltsInfo" class="letterSpace"></span></div>
                                  </div>
								</div>
								<div class="col-md-6 col-sm-6  portlet-body" id="cpAddressContent" style="height:auto">
										<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
										<div style="height:auto;width:100%;" class=""> <div class="letterSpace addCls" style="font-family: Helvetica;font-weight:bold;color: #333;width:24%;float:left;text-align:right;display:none;" class="large" id="addrTxt">Address: </div><div style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;width:76%;float:right;margin-left:0px;padding-left:4px;" id="custAdd1" class="letterSpace">  </div></div>
										<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custAdd2" class="letterSpace"></span></div>
										<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custCity" class="letterSpace"></span></div>
										<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custState" class="letterSpace"></span></div>
										<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custCountry" class="letterSpace"></span></div>
										<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custZip" class="letterSpace"></span></div>
                                  </div>
								</div>
							</div>
						</div>
				</div>
				</div>
				
				
				
		<div class="row sortableRow"  style="margin-left:0px !important; margin-right: 0px !important;">
			<div class="page-bar col-sm-12" >
				<ul class="page-breadcrumb"
					style="padding-bottom: 5px !important;">
					<li id="crumgLiId" style="padding-left: 0px; padding-top: 3px;" >
						<!-- <div style="float: left; padding-right: 10px;">
							<span id="reportYrId" hidden="true"
								style=" font-family: Helvetica; color: #004c74; font-weight: bold; float: left;">Select
								Year To See Report:</span><span><select id="yrId"  hidden="true"
								style="margin-left: 10px !important; color: #004c74; height: 23px; border: 1px solid #004c74;"
								class="input-sm">
							</select></span>
						</div> -->
						<span style="display: none;" id="tempData"></span>
						<div style="float: left; padding-right: 10px;" id="custSegContainer">
							<span id="custSegId"
								style="font-family: Helvetica; color: #004c74; font-weight: bold;  float: left;">Customer
								Segment:</span> <span><a id="example" tabindex="0"
								class="btn btn-sm btn-danger" role="button"
								data-toggle="popover" data-trigger="focus"
								title="Customer Segment"
								style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica;"></a></span>
						</div>
						<!--  
						<span style="display: none;" id="tempDataQual"></span>
						<div style="float: left; padding-right: 10px;">
							<span id="qualScrId"
								style="font-family: Helvetica; color: #004c74; font-weight: bold; float: left;">Qualification
								Score:</span> <span><a id="exampleQual" tabindex="0"
								class="btn btn-sm btn-danger" role="button"
								data-toggle="popover" data-trigger="focus"
								title="Qualification Score"
								style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica;"></a></span>
						</div>
						-->
						<div style="float: left; padding-right: 10px;">
							<span id="lastContactedDateId"
								style="font-family: Helvetica; color: #004c74; font-size: 14px; font-weight: bold; float: left;">Last Live Contacted Date:</span> <span id="lastContactedDateValueId" style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica; color: #004c74;"></span>
						</div>
					</li>
				</ul>
			</div>
		</div>



		
        <!-- left nav part start   -->
        <!-- TODO Is this row section needed?   -->
				<div class="row">
					<div class="col-lg-12" id="userDiv">
					<div class="portlet box green" style="display:none;border:1px solid #004c74;background-color:#004c74;" id="superId">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span class="letterSpace">Users <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideId0" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-placement="top" data-toggle="tooltip" title="Close Super User">
									</a>
									
								</div>
								<!-- <div id ='updateDateValueUser' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelUser' style="float:right;"><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;cursor:default;" role="button">Refreshed: </span></div> -->
				<div id="updateDateLabelUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
				<a id="addContactId" href="#" target="_blank" style="display:none;" onclick="generateLogs('users');" oncontextmenu="generateLogs('users');">
				<button type="button" class="btn3d btn btn-default btn-xs" style="float: right; margin: 11px; background-color: #fff; border: none;border-radius: 2px !important;">
				<span class="fa fa-hand-o-up"></span>  Add new contact to SFDC
				</button></a>
							</div>
							<div class="portlet-body" id="superContent" style="display: none;height:auto;">
						<div class="page-bar col-lg-12 col-sm-12 col-md-12"
							style="height: 16px; font-size: 14px; color: #333; font-family: ArialMT; font-weight: 600; letter-spacing: .5px;">
							The Users Section shows all order contacts (customer users) on
							that account that have either ordered or browsed through SA.com
							or Staples.com.</div>
						<div id="superUserTableId" class="dataTable_wrapper">
                                <table class="table table-striped table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Details</th>
												<th>Order <br/> Contact</th>
												<th>No. Of <br/> Orders</th>
												<th>No. Of Orders <br/><span style="font-size: smaller">(Current Year)</span></th>
												<th>Total <br/> Spend</th>
												<th>Total Spend <br/><span style="font-size: smaller">(Current Year)</span></th>
												<th>No. Of <br/> Visits</th>
												<th>Last <br/> Visit Date</th>
												<th>Last <br/> Visit Date</th>
                                            <th>Dotcom <br/> Activity</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <td></td>
											<td></td>
                                             <td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
                                        </tr>
                                          
                                       
                                    </tbody>
                                </table>
                                
                            </div>
								
								
							</div>
						</div>
						<div class="portlet box green" style="display:none;border:1px solid #004c74;background-color:#004c74;" id="shipToId">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span>Ship To <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideIdShipTo" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-original-title="Open Ship To Data" title="">
									</a>
									
								</div>
								<!-- <div id ='updateDateValueShip' style="float:right;" ><span  style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" class="" role="button"></span></div> -->
								<div id="updateDateValueShip" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelShip' style="float:right;"><span  style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" class="" role="button">Refreshed: </span></div> -->
				<div id="updateDateLabelShip" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div>
							<div class="portlet-body" id="shipToContent" style="display: none;height:auto;">
							<div class="page-bar col-lg-12 col-sm-12 col-md-12"
							style="height: 16px; font-size: 14px; color: #333; font-family: ArialMT; font-weight: 600; letter-spacing: .5px;">
							This section provides details of customer order locations and shipping information
							</div>
								 <div id="shipToTableId" class="dataTable_wrapper">
                                <table class="table table-striped table-hover" id="dataTables-exampleShipTo">
                                    <thead>
                                        <tr>
                                            <th>Ship To Id</th>
												<th>No of Orders <br/>(Last Year)</th>
												<th>$ Spent <br/>(Last Year) </th>
												<th>No of Orders <br/>(Current Year)</th>
												<th>$ Spent <br/>(Current Year)</th>
												<th>YOY % <br/>Change</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <td></td>
											<td></td>
                                             <td></td>
											<td></td>
											<td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
								
								
							</div>
						</div>
						<div class="portlet box green" id="cusId" style="border:1px solid #004c74;display:none;background-color:#004c74;">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span>Order Details <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideId1" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<!-- <div id ='updateDateValueOrder' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueOrder" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelOrder' style="float:right;"><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button">Refreshed: </span></div> -->
				<div id="updateDateLabelOrder" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div>
							<div class="portlet-body" id="custContent" style="display: none;height:auto;">
							<div class="page-bar col-lg-12 col-sm-12 col-md-12"
							style="height: 16px; font-size: 14px; color: #333; font-family: ArialMT; font-weight: 600; letter-spacing: .5px;">
							This section contains a customer's last 2 years of order history.</div>
							<div style="background-color: gainsboro;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:40px;padding-top: 5px;" class="col-lg-12 col-sm-12">
			                <div class="col-lg-3 col-sm-3 col-md-3" style="padding-left:0px;" id="viewWrapper">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;width: auto;" class="col-lg-3 col-sm-3 input-sm">View</div><div class="col-lg-9 col-sm-8" id="yearSelId" style="padding-left:0px;"><input id="datepickerTEXT" class="col-lg-12 col-sm-12" type="text" value="" style="font-family:helveticaneuebold;font-size:14px;border:1px solid #004c74;border-radius:3px !important;"><!-- <select id="yearSel" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm"></select>--></div>
									<!-- <div class="demo">  <p>Date: </p>  </div> -->
							</div>
							<div class="col-lg-4 col-sm-4" style="padding-left:0px;" id="filterByWrapper">                                           
									<div style="padding-left:0px;font-size:15px;color:#004c74;font-weight:bold;" class="col-lg-3 col-sm-4 input-sm">Filter By</div><div class="col-lg-8 col-sm-7" id="sortById" style="text-align:right;margin-right:0px;padding-left: 0px;"><select id="sortSel" style="font-family:helveticaneuebold;font-size:13px;color:#004c74;border:1px solid #004c74;border-radius:3px !important;" class="form-control input-sm"></select></div>
							</div>	 
							
							<div class="col-lg-4 col-sm-5" style="padding-left:0px;" id="filterByDays">                                           
									<div style="padding-left:0px;font-size:15px;color:#004c74;font-weight:bold;" class="col-lg-6 col-sm-7 input-sm">Predefined Filters</div><div class="col-lg-6 col-sm-5" id="lastXDays" style="text-align:right;margin-right:0px;padding-left: 0px;"><select id="lastXDaysSel" style="font-family:helveticaneuebold;font-size:13px;color:#004c74;border:1px solid #004c74;border-radius:3px !important;" class="form-control input-sm">
									<option value="0">All Days</option>
									<option value="-30">Last 30 Days</option>
									<option value="-60">Last 60 Days</option>
									<option value="-90">Last 90 Days</option></select></div>
							</div>	
					        </div>	
                             <div id="orderDetailsTabId" class="dataTable_wrapper">
                              <table class="table table-striped  table-hover" id="dataTables-order">
                                  <thead>
                                      <tr>
									<th>Customer Details</th>
                                          <th>Open Details</th>
                                          
                                          <th>Open Details</th>
                                          <th>Customer Details</th>
                                          
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr class="odd gradeX">
                                          <td></td>
                                          <td></td>
                                          
                                          <td></td>
                                          <td></td>
                                          
                                      </tr>
                                     
                                  </tbody>
                              </table>
                            </div>
								
							</div>
						</div>	
					</div>
				</div>        
        <!-- left nav part end -->
        
<!-- WIR Customer Performance and Customer Insights -->	
			<div class="row sortableRow">
			
			
<!-- WIR Customer Performance -->				
				<div id ="custPerfColId" class="col-lg-8 col-md-12 col-sm-12 col-xs-12 style="border:none;"">

				<div id="barChartContentID" class="portlet box green" style="border:1px solid #004c74;background-color: #004c74;" >
						<div class="portlet-title">
							<div class="caption" style="font-family: Helvetica !important;">
								<span class="letterSpace">Customer Performance</span>
							</div>
						</div>
					
					<div id="bossPortletBodyId" class="portlet-body"  style="display: block; height: auto; border: #004c74;padding-right:6%">
					
					<div class= "row" id=bossSummaryNumbers>
					
						<div id=rollingSummaryColumnId class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border:none;padding-left:0%;padding-right:0%;">
						   <div class = "row" style="margin-right:0px; margin-left:0px">
						     <div class="col-lg-12" style="font-family:Helvetica;font-size:16px;color:#004c74;font-weight:bold;float:left; margin-bottom: 3%">
						     	<img src="./resources/img/top_calender_icon.png" width="16" height="17"/> Last 90 Days:
						  	    <span style="font-family:Helvetica ;font-size:16px;color:#004c74;font-weight:bold" id="dateToDisplayBar" class="letterSpace"> </span>
						  	 </div>
						   </div>						  	 
						   <div class = "row" style="margin-right:0px; margin-left:0px;">						  	 
						  	 <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="font-family: Helvetica; font-size: 15px; font-weight: bold; color: #000000; float:left;clear: left;margin-bottom: 2%">Total Sales: 
						  	   <span style="font-family:Helvetica !important;font-size:15px;color:#000000;font-weight:bold" id="totalCurrRollMonthSales" class="letterSpace"> </span>
						  	 </div>
						  	 <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12" style="font-family: Helvetica; font-size: 15px; font-weight: bold; color: #000000;float:right; text-align: right;margin-bottom: 2%;padding-left:0%">Sales Growth: 
						  	   <span style="font-family:Helvetica !important;font-size:15px;color:#000000;font-weight:bold; white-space:nowrap" id="currGrowthPerc" class="letterSpace"> </span>
						  	 </div>
						   </div>
						 </div>		
						 
						<div id=ytdSummaryColumnId class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border:none;padding-left:0%;padding-right:0%;">						   
						   <div class = "row" style="margin-right:0%; margin-left:2%">						    
						    <div class="col-lg-12" style="font-family:Helvetica ;font-size:16px;color:#004c74;font-weight:bold;float:left;margin-bottom: 3%">
						  	    <img src="./resources/img/top_calender_icon.png" width="16" height="17"/> Year-to-Date: 
						  	    <span style="font-family:Helvetica;font-size:16px;color:#004c74;font-weight:bold" id="dateToDisplayBar1" class="letterSpace"> </span>
						  	 </div>
						   </div>					  	 
						  	 <div class = "row" style="margin-right:-6%; margin-left:2%;">
						  	
						       <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="font-family: Helvetica; font-size: 15px; font-weight: bold; color: #000000; float:left;clear: left;margin-bottom: 2%">Total Sales: 
							      <span style="font-family:Helvetica !important;font-size:15px;color:#000000;font-weight:bold" id="totalCurrYrToDtSales" class="letterSpace"> </span>
							   </div>
							 
						       <div  class="col-lg-7 col-md-7 col-sm-12 col-xs-12"style="font-family: Helvetica; font-size: 15px; font-weight: bold; color: #000000;float:right; text-align: right;margin-bottom: 2%;padding-right:0%;padding-left:0%">Sales Growth:
						          <span style="font-family:Helvetica !important;font-size:15px;color:#000000;font-weight:bold; white-space:nowrap" id="yrToDtGrowthPerc" class="letterSpace"> </span>
						       </div>
						    </div>						 
						 </div>							 			
					</div>
					 
					 
					 <div class= "row">

				
						<div id=rollingBossColumnId class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border:none;padding-left:0%;padding-right:0%;">
						  	 
						   <div class = "row" style="margin-right:0px; margin-left:0px;">							  	 
						     <div id="yrToDateBossChart" class="col-lg-12"  style="height: 300px; width: 100%;clear: left;margin-bottom: 3%;margin-left:10px"></div>
						   </div>
						   
		   
						   
						</div>		
											   
						<div id=ytdBossColumnId class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border:none;padding-left:0%;padding-right:0%;">						   
					     
						     <div class = "row" style="margin-right:0%; margin-left:0%;">
						       <div id="yrToDateBossChart1" class="col-lg-12" style="height: 300px; width: 100%;clear: left;margin-bottom: 3%; margin-left:0%;padding-right:0%;"></div>
						     </div>
	
						   
 
						  </div>  				
					   </div> 
					   <div class= "row" id="bossChartsFooterRow" style=" margin-right:-6%; margin-left:0%;">
					   
					     <div class="col-lg-12" style="display:table;width:100%; padding-left:0%;padding-right:0%">
				
						    <div  style="border:none;padding-left:0%;padding-right:0%; width:50%; display:table-cell;vertical-align: bottom">
						  
						   	  <div style="font-family: Helvetica; font-size: 11px; font-weight: bold; color: #555;float:left; text-align: left;margin-left:0%; padding-top:0%;padding-left:0%;">All Other Products are not included in BO$$/Core spend. All Other Products pass into Helios without an assigned Department.</div>
					   
					       </div>
					   
						   <div  style="border:none;padding-left:0%;padding-right:0%; display:table-cell;vertical-align: bottom; width:50%; text-align: right;">
						  
							   	      <div id="lrdDiv" style="font-family: Helvetica; font-size: 11px;overflow-x:visible; font-weight: bold; color: #555;">
								   	      <span>Last Refresh Date:</span>
								  	      <span style="font-family:Helvetica !important;font-size:11px;color:#555;font-weight:bold;white-space:nowrap" id="lastRefDtBossCore" class="letterSpace"></span>
							  	      </div>
					   
					       </div>					   
				   				   

					   </div>					   
				 	</div>														
				</div>
				</div>
			</div>



			  
<!-- WIR customer insight -->		
			<div id="custInsightColId" class="col-lg-4 col-md-12 col-sm-12 col-xs-12 ">

					<div id="custInsightAllID" class="portlet box green" style="border:1px solid #004c74;overflow: hidden;background-color: #004c74;" >
						<div class="portlet-title">
							<div class="caption" style="font-family: Helvetica !important;">
								<span class="letterSpace">Customer Insights </span>
							</div>
						</div>
						
						<div class="portlet-body" id="custInsightContent" style="display:table;background-color:#F1F3FA;overflow: auto;height: auto;border:none;width:101%;padding-left:4%; padding-right:3%">  <!-- 4/4 -->
						
				 			<div id="CustomerInsightTableRow" style="display: table-row; height: 500px">
				 				<div id="CustomerInsightTextID" style="font-family: Helvetica;height: auto;overflow:auto;font-size: 14px;font-weight: bold;color: #000000;border:none; display: table-cell; vertical-align: middle;padding-left:10px; padding-right:10px"> </div> 
  							</div>
  							
				 			<div id="CustomerInsightDtRefreshRow" style="display: table-row; height: 40px;">  							
								<div id="refreshDtCustIn" style="border:none;font-family: Helvetica;background-color:#F1F3FA; margin-top: 4%; overflow-x:auto;white-space: normal;font-size: 11px; font-weight: bold; color: #555; height: auto; display: table-cell; vertical-align: bottom;text-align: right;">Last Refresh Date:
						    		<span style="font-family:Helvetica !important;font-size:11px;color:#555;font-weight:bold" id="lastRefDtCI" class="letterSpace"></span>
								</div>
				  	  
							</div> 
						</div>
					</div>
			</div>
			

		</div>	
<!-- end of WIR Customer Performance and Customer Insights -->		

<!-- WIR category performance chart -->			
			

			 <div class="row sortableRow">
				<div class="col-lg-12">
					<div class="portlet box green" style="border:1px solid #004c74;background-color: #004c74;" >
						<div class="portlet-title" style="cursor: move">
							<div class="caption" style="font-family: Helvetica !important;">
								<span class="letterSpace">Category Performance </span>
							</div>
			
							<div class="tools">
								<a class="fa fa-chevron-up" id="showHideCatPerfID" style="background-color: #004c74; color: #fff; text-decoration: none;"
										href="javascript:;" title=""> </a>
							</div>
						</div>
						
				 		<div class="portlet-body" id="catPerfIDContent" style="height:auto;overflow: auto;">
						
							<div style="font-family:Helvetica !important;font-size:16px;color:#004c74;font-weight:bold;float:left; margin-bottom: 2%">
						     	<img src="./resources/img/top_calender_icon.png" width="16" height="17"/> Last 90 Days Sales Growth: <span style="font-family:Helvetica !important;font-size:16px;color:#004c74;font-weight:bold" id="dateToDisplayColumn" class="letterSpace"> </span>
						  	 </div>
						  	 
							<div id="perfColumnChartID" style="height: 300px; width: 100%;clear: left; margin-bottom:0px;"></div>
						     	
					    	<div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left; text-align: left;">Chart represents category change</div>
					    	
					    	<div style="font-family: Helvetica; font-size: 11px; font-weight: bold; color: #555;float:right;  text-align: right;">Last Refresh Date:
						  	   <span style="font-family:Helvetica !important;font-size:11px;color:#555;font-weight:bold" id="lastRefDtCatPerf" class="letterSpace"></span>
						  	</div>
						  	   
						</div>
					</div>
				</div>
				</div>

				
				
<!-- WIR donut chart -->					
				
				<div class="clearfix">
				</div>
				
				<div class="row sortableRow">
					<div class="col-lg-12">
						<div class="portlet">
							<div class="portlet-body">
				
				

				
				 <div id="donutContentShowHide" style="border: 1px solid #004c74;background-color:white;">
					<div style="font-family:Helvetica !important;font-size:16px;color:#004c74;font-weight:bold;float:left;margin-left: 1%;margin-top: 1%">
					   <img src="./resources/img/top_calender_icon.png" width="16" height="17"/> Year-to-Date Sales Share:
					   <span style="font-family:Helvetica !important;font-size:16px;color:#004c74;font-weight:bold" id="dateToDisplayPie" class="letterSpace"> </span>
					</div>								
					
					<table style="width: 100%;">
					   <tr>
						  <td style="border:none;width: 60%">
						      <div id="chartContainerPie" style="height:370px;width: 100%;margin-top: 1%"></div>
						  </td>
						
						  <td style="border:none;width: 40%" >
						  	    <div style="font-family: Helvetica !important; font-size: 15px;font-weight: bold; color: #004c74;float:left;border: none;width: 45%;margin-top: 8%;margin-bottom: 2%;"> &nbsp;&nbsp;&nbsp;&nbsp;Category</div>
						  	    <div style="font-family: Helvetica !important; font-size: 15px;font-weight: bold; color: #004c74;float:left;border: none;text-align: left;margin-top: 8%;margin-bottom: 2%;">&nbsp;&nbsp;&nbsp;Sales Share</div>
								
								<ul class="legend">
   									<li style="list-style-type: none;width:100%;">
   										
   										<div style="border: 1px solid #F2F2F2; float:left;clear: left;text-align: left;width:90%;">
										 <span style='background:#417505;border: 1px solid #F2F2F2;'></span>
										 <div id="cat0" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border: 1px solid #F2F2F2; border-width: 0px 1px 0px 1px;">Ink and Toner</div>
										 <div id="salesPer0" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border:none;"></div>  
										</div> 
   										 
   										<div style="border:1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#159B9B;border: 1px solid #F2F2F2;'></span>
   										 <div id="cat1" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Paper </div>
   										 <div id="salesPer1" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border:1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#2D435E;border: 1px solid #F2F2F2;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border: 1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Technology </div>
   										 <div id="salesPer2" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#C8CBD0;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Mail and Ship </div>
   										 <div id="salesPer3" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#9CE2CF;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Furniture </div>
   										 <div id="salesPer4" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#D0DF00;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Facilities </div>
   										 <div id="salesPer5" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#FF9A2C;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Office Supplies </div>
   										 <div id="salesPer6" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#5F9EA0;'></span>
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Print </div>
   										 <div id="salesPer7" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div>
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#DD1D1D;'></span> 
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">Promo </div>
   										 <div id="salesPer8" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div> 
   										
   										<div style="border: 1px solid #F2F2F2;float:left;clear: left;text-align: left;width:90%;">
   										 <span style='background:#FCD78D;'></span> 
   										 <div style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: left;width: 45%;border:1px solid #F2F2F2;border-width: 0px 1px 0px 1px;">All Other Products </div>
   										 <div id="salesPer9" style="font-family: Helvetica; font-size: 13px; font-weight: bold; color: #555;float:left;text-align: right;width: 20%;border: none;"></div> 
   										</div> 
   										
   									</li>
								</ul>
						 </td>
						</tr>
						
					   <tr>
					    <td style="border: none;width: 100%" colspan=2>
				    	<div style="font-family: Helvetica; font-size: 11px; font-weight: bold; color: #555;float:right;  text-align: right;margin-bottom: 1%;margin-right: 1%">Last Refresh Date:
					  	   <span style="font-family:Helvetica !important;font-size:11px;color:#555;font-weight:bold" id="lastRefDtPie" class="letterSpace"></span>
					  	</div>
					    </td>
					  </tr>
						
					</table>   
				</div>
				
				</div>
				</div>
				</div>					
				</div>
		
				
				
				<div class="row">
					<div class="col-lg-12">
					 <!-- <div id="chartdiv"></div> -->
					</div>
				</div>	
			</div>

			<!-- BEGIN QUICK SIDEBAR -->
			<a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-login"></i></a>
			<div class="page-quick-sidebar-wrapper">
				<div class="page-quick-sidebar">
					<div class="nav-justified">
						<ul class="nav nav-tabs nav-justified" style="background-color: #1d2939;font-size: 16px;font-family: Helvetica;font-weight: bold;color: #ddd !important;">
							<li class="active">
								<a href="#quick_sidebar_tab_1" data-toggle="tab" style="color:#ddd;">
								Customers <!-- <span class="badge badge-danger">2</span> -->
								</a>
							</li>
							<li id="alertId" style="display:none !important;">
								<!-- <a href="#quick_sidebar_tab_2" data-toggle="tab">
								Alerts <span class="badge badge-success">7</span>
								</a> -->
								<a href="javascript:void()" data-toggle="tab">
								Alerts <!-- <span class="badge badge-success">7</span> -->
								</a>
							</li>
							<li class="dropdown" style="display:none !important;">
								<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
								More<i class="fa fa-angle-down"></i>
								</a>
								<ul class="dropdown-menu pull-right" role="menu" style="display:none;">
									<li>
										<a href="#quick_sidebar_tab_3" data-toggle="tab">
										<i class="icon-bell"></i> Alerts </a>
									</li>
									<li>
										<a href="#quick_sidebar_tab_3" data-toggle="tab">
										<i class="icon-info"></i> Notifications </a>
									</li>
									<li>
										<a href="#quick_sidebar_tab_3" data-toggle="tab">
										<i class="icon-speech"></i> Activities </a>
									</li>
									<li class="divider">
									</li>
									<li>
										<a href="#quick_sidebar_tab_3" data-toggle="tab">
										<i class="icon-settings"></i> Settings </a>
									</li>
								</ul>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active page-quick-sidebar-chat" id="quick_sidebar_tab_1">
								<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list" style="background-color:#1d2939;" id="customerTab">

								<ul class="media-list list-items" id="customerList">
									<div id="QuickSearchInfoId" class="dataTable_wrapper">

										<table class="table  compact dt-responsive"
											id="dataTables-QuickSearch">
											<thead>
												<th data-toggle="tab" style="color: #ddd;">CUSTOMERS</th>


											</thead>
											<tbody>

											</tbody>
										</table>

									</div>
								</ul>

							</div>
								
							</div>
							<div class="tab-pane page-quick-sidebar-alerts" id="quick_sidebar_tab_2">
								<div class="page-quick-sidebar-alerts-list">
									<h3 class="list-heading">General</h3>
									<ul class="feeds list-items">
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-info">
															<i class="fa fa-shopping-cart"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 New order received with <span class="label label-sm label-danger">
															Reference Number: DR23923 </span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 30 mins
												</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-success">
															<i class="fa fa-user"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 You have 5 pending membership that requires a quick review.
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 24 mins
												</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-danger">
															<i class="fa fa-bell-o"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 Web server hardware needs to be upgraded. <span class="label label-sm label-warning">
															Overdue </span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 2 hours
												</div>
											</div>
										</li>
										
									</ul>
									<h3 class="list-heading">System</h3>
									<ul class="feeds list-items">
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-info">
															<i class="fa fa-shopping-cart"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 New order received with <span class="label label-sm label-success">
															Reference Number: DR23923 </span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 30 mins
												</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-success">
															<i class="fa fa-user"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 You have 5 pending membership that requires a quick review.
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 24 mins
												</div>
											</div>
										</li>
										<li>
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-warning">
															<i class="fa fa-bell-o"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 Web server hardware needs to be upgraded. <span class="label label-sm label-default ">
															Overdue </span>
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 2 hours
												</div>
											</div>
										</li>
										<li>
										
										</li>
									</ul>
								</div>
							</div>
							<div class="tab-pane page-quick-sidebar-settings" id="quick_sidebar_tab_3">
								<div class="page-quick-sidebar-settings-list">
									<h3 class="list-heading">General Settings</h3>
									<ul class="list-items borderless">
										<li>
											 Enable Notifications <input type="checkbox" class="make-switch" checked data-size="small" data-on-color="success" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
										<li>
											 Allow Tracking <input type="checkbox" class="make-switch" data-size="small" data-on-color="info" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
										<li>
											 Log Errors <input type="checkbox" class="make-switch" checked data-size="small" data-on-color="danger" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
										<li>
											 Auto Sumbit Issues <input type="checkbox" class="make-switch" data-size="small" data-on-color="warning" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
										<li>
											 Enable SMS Alerts <input type="checkbox" class="make-switch" checked data-size="small" data-on-color="success" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
									</ul>
									<h3 class="list-heading">System Settings</h3>
									<ul class="list-items borderless">
										<li>
											 Security Level
											<select class="form-control input-inline input-sm input-small">
												<option value="1">Normal</option>
												<option value="2" selected>Medium</option>
												<option value="e">High</option>
											</select>
										</li>
										<li>
											 Failed Email Attempts <input class="form-control input-inline input-sm input-small" value="5"/>
										</li>
										<li>
											 Secondary SMTP Port <input class="form-control input-inline input-sm input-small" value="3560"/>
										</li>
										<li>
											 Notify On System Error <input type="checkbox" class="make-switch" checked data-size="small" data-on-color="danger" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
										<li>
											 Notify On SMTP Error <input type="checkbox" class="make-switch" checked data-size="small" data-on-color="warning" data-on-text="ON" data-off-color="default" data-off-text="OFF">
										</li>
									</ul>
									<div class="inner-content">
										<button class="btn btn-success"><i class="icon-settings"></i> Save Changes</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END QUICK SIDEBAR -->
		</div>
		
	<!-- dialog start -->
	<div class="modal fade" id="super-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">Super User Info - <span id ="userName"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <div class="">
		<!-- <span style="font-size:16px;font-weight:bold;" >Search String</span> -->
		</div>
        <div style="margin-bottom:10px;border-bottom:1px solid red;" class="">
			<table id="searchStrid" class="">
				<tr class="">
				 <td style="color:red;"></td>
				</tr>
			</table>
									
		</div>
         <div id="superUserInfoId" class="dataTable_wrapper">
                                <table class="table table-striped  table-hover" id="dataTables-superInfo">
                                    <thead>
                                        <tr>
                                            <th>ABANDONED CART</th>
											<th>OUR RECOMMENDATIONS</th>
                                            <th>DOTCOM ACTIVITY</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                            
                                    </tbody>
                                </table>
                            </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c;" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  <!-- super user info dialog close -->
  <jsp:include page="SuperUserInfo.jsp"></jsp:include>
    <!-- order details info start -->

<div class="modal fade" id="order-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;" id="ordertitle"></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button>
         <div id="OrderDetailsInfoId" class="dataTable_wrapper">
                                <table class="table table-striped  table-hover" id="dataTables-OrderInfo">
                                    <thead>
                                        <tr>
                                            <th>Item No.</th>
											<th>Item Description</th>
                                            <th>Qty.</th>
                                            <th>Price</th>
                                            <th>Coupan</th>
                                            <th>Total Spend</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                            
                                    </tbody>
                                </table>
                            </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- order details info end -->
<div class="modal fade" id="ship-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">Ship To Info <span id="ShipToLocId"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printShipInfoBtn" >Print</button>
         <div id="ShipDetailsInfoId" class="dataTable_wrapper">
                                <table class="table table-striped  table-hover" id="dataTables-ShipInfo">
                                    <thead>
                                        <tr>
                                            <th>Order Number</th>
											<th>Order Amount</th>
                                            <th>Order Date</th>
                                            
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                            
                                    </tbody>
                                </table>
                            </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  <div class="modal fade" id="shiporder-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;"><span id="shplocId"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printshipOrderInfoBtn" >Print</button>
         <div id="shipOrderDetailsInfoId" class="dataTable_wrapper">
                                <table class="table table-striped  table-hover" id="dataTables-shipOrderInfo">
                                    <thead>
                                        <tr>
                                            <th>Item No.</th>
											<th>Item Description</th>
                                            <th>Qty.</th>
                                            <th>Price</th>
                                            <th>Coupan</th>
                                            <th>Total Spend</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                            						
                                    </tbody>
                                </table>
                            </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

  
<!-- log user activity; rep. data -->
<jsp:include page="/WEB-INF/jsp/Training.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/CallToActionPopUp.jsp"></jsp:include>
<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
<input type="hidden" name="division" id="division" />
<input type="hidden" name="sfdcAppBaseUrl" id="sfdcAppBaseUrl" value="${requestScope['SFDC_APP_BASE_URL']}" />
<input type="hidden" name="ctaDisbledIds" id="ctaDisbledIds" value="${requestScope['CTA_DISPOSITION_DISABLED_IDS']}" />		
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!-- These Files Are StartUp Files,Should Not Have Any Version -->

<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-ui-ipad.js" type="text/javascript"></script>
<script type="text/javascript" src="./resources/javascript/canvasjs.min.PerfDash.js"></script>
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
    /* var scripts = ['app.js','jquery-ui.min.js','jquery-uniform.min.js','easypiechart.js','sparkline.min.js','jquery.dataTables.min.js','bootstrap-min.js','bootstrap-dropdown.js','dataTables.bootstrap.min.js','slimscroll.js','blockui.min.js','bootstrap-switch.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js','vmap.js','vmap.russia.js','vmap.world.js','vmap.europe.js','vmap.germany.js','vmap.usa.js','sampledata.js','flot.min.js','float.resize.js','flot.category.js','pulsate.js','moment.js','datepicker.js','metronic.js','layout.js','quick-sidebar.js','demo.js','index.js','task.js','amcharts.js','serial.js','light.js','Helios_Customer.js','d3.min.js','d3pie.js','editor.js']; */
     var scripts = ['app.js','jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','dataTables.bootstrap.min.js','slimscroll.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js','pulsate.js','datepicker.js','metronic.js','layout.js','quick-sidebar.js','amcharts.js','serial.js','light.js','perfDashboard.js','editor.js'];
    // Loop through each script name and append our new version number
    scripts.map(function(script){
        var currentScriptVer = ${initParam.helios_ver} /* incrementScriptVer(script); */
        document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
    });
})();
</script>

<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   //Demo.init(); // init demo features 
   QuickSidebar.init(); // init quick sidebar
   
   $("#showHideId0").click(function(){ 
		
		   var cls=$("#showHideId0").attr('class');
		   //$("#bc0").toggle();
		    if(cls=='fa fa-chevron-down'){
	    	$("#showHideId0").prop('class','fa fa-times');
	    	$("#superContent").css('display','block');
		   }
		    else if(cls=='fa fa-times'){
		    $("#superId").css('display','none');
		    $("ul li a[id^=menuItemId]").css("background-color","rgb(29, 41, 57)");
		    $("ul li a[id^=menuItemId]").css("color","rgb(221, 221, 221)");
		    $("ul li a[id=menuItemId0]").css("color","rgb(68, 68, 68)");
		    $("ul li a[id=menuItemId0]").css("background-color","#fff");
		    
		   } 
		   });
   
   $("#showHideId1").click(function(){ 
		
	   var cls=$("#showHideId1").attr('class');
	   if(cls=='fa fa-chevron-down'){
	    	$("#showHideId1").prop('class','fa fa-times');
	    	$("#custContent").css('display','block');
		   }
		    else if(cls=='fa fa-times'){
		    $("#cusId").css('display','none');
		    $("ul li a[id^=menuItemId]").css("background-color","rgb(29, 41, 57)");
		    $("ul li a[id^=menuItemId]").css("color","rgb(221, 221, 221)");
		    $("ul li a[id=menuItemId0]").css("color","rgb(68, 68, 68)");
		    $("ul li a[id=menuItemId0]").css("background-color","#fff");
		   } 
	   });
/*    $("#showHideId2").click(function(){ 
		
	   var cls=$("#showHideId2").attr('class');
	    if(cls=='fa fa-chevron-up'){
    	$("#showHideId2").prop('class','fa fa-chevron-down');
    	$("#catContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	    	$("#showHideId2").prop('class','fa fa-chevron-up');
	    	$("#catContent").css("display","block");
	   } 
	   }); */

$("#showHideCatPerfID").click(function(){ 
	var cls=$("#showHideCatPerfID").attr('class');
	if(cls=='fa fa-chevron-up')
	{
  		$("#showHideCatPerfID").prop('class','fa fa-chevron-down');
  		$("#catPerfIDContent").css('display','none');
		$("#donutContentShowHide").css('display','none');
	}
	else if(cls=='fa fa-chevron-down'){
	   	$("#showHideCatPerfID").prop('class','fa fa-chevron-up');
	   	$("#catPerfIDContent").css('display','block');
		$("#donutContentShowHide").css('display','block');
	} 
});

/* $("#showHideBarChartContent").click(function(){ 
 var cls=$("#showHideBarChartContent").attr('class');
    if(cls=='fa fa-chevron-up'){
 	$("#showHideBarChartContent").prop('class','fa fa-chevron-down');
 	$("#barChartContentID").css('display','none');
   }
    else if(cls=='fa fa-chevron-down'){
    	$("#showHideBarChartContent").prop('class','fa fa-chevron-up');
    	$("#barChartContentID").css('display','block');
   } 
}); */
	   
/* $("#showHideIdCustInsight").click(function(){ 
 var cls=$("#showHideIdCustInsight").attr('class');
    if(cls=='fa fa-chevron-up'){
 	$("#showHideIdCustInsight").prop('class','fa fa-chevron-down');
 	$("#custInsightContent").css('display','none');
   }
    else if(cls=='fa fa-chevron-down'){
    	$("#showHideIdCustInsight").prop('class','fa fa-chevron-up');
    	$("#custInsightContent").css('display','block');
   } 
  }); */
  
$("#showHideId4").click(function(){ 
	 var cls=$("#showHideId4").attr('class');
	    if(cls=='fa fa-chevron-up'){
 	$("#showHideId4").prop('class','fa fa-chevron-down');
 	$("#cpContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	    	$("#showHideId4").prop('class','fa fa-chevron-up');
	    	$("#cpContent").css('display','block');
	   } 
  });
  
$("#showHideId5").click(function(){ 
	 var cls=$("#showHideId5").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId5").prop('class','fa fa-chevron-down');
	$("#perfDashId").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	    	$("#showHideId5").prop('class','fa fa-chevron-up');
	    	$("#perfDashId").css('display','block');
	   } 
 });
 
/* $("#showHideId6").click(function(){ 
	 var cls=$("#showHideId6").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId6").prop('class','fa fa-chevron-down');
	$("#amContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId6").prop('class','fa fa-chevron-up');
	    	$("#amContent").css('display','block');
	   } 
}); */

	
$("#showHideIdShipTo").click(function(){ 
	
	   var cls=$("#showHideIdShipTo").attr('class');
	   //$("#bc0").toggle();
	    if(cls=='fa fa-chevron-down'){
 	$("#showHideIdShipTo").prop('class','fa fa-times');
 	$("#shipToContent").css('display','block');
	   }
	    else if(cls=='fa fa-times'){
	    $("#shipToId").css('display','none');
	    $("ul li a[id^=menuItemId]").css("background-color","rgb(29, 41, 57)");
	    $("ul li a[id^=menuItemId]").css("color","rgb(221, 221, 221)");
	    $("ul li a[id=menuItemId0]").css("color","rgb(68, 68, 68)");
	    $("ul li a[id=menuItemId0]").css("background-color","#fff");
	    
	   } 
	   });
/* $("#growthId").click(function(){
			populateGrowthData();
		     var cls=$("#growthSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#growthSpanId").attr("class","fa fa-chevron-up")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  }
		  }); */
		 /*  $("#retentionId").click(function(){
			  populateRetentionData();
		     var cls=$("#retentionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#retentionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }
		  }); */
		 /*  $("#expansionId").click(function(){
			  populateExpansionData()
		     var cls=$("#expansionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#expansionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }
		  });	 */

/* 	$("#growthId").click(function(e){
		alert("growthId jsp 1565");
		e.preventDefault();
		return false;
		
	});
	
	$("#retentionId").click(function(){
		return false;
	});
	
	$("#expansionId").click(function(){
		return false;
	});	  */

	$('.modal-dialog').draggable({
	    handle: ".modal-header"
	});
	window
    .matchMedia('(orientation: portrait)')
    .addListener(function (m) {
		 if (m.matches) {
		   	var cval=$.cookie('sidebar_closed');
		    if(undefined !=cval && '' !=cval && cval==1){
			$("#stplId").html("STAPLES");
			$("#stplLogoId").css("display","block");
			$("#notiId").html("Call To Action");
			$("#gId").html("Growth");
			$("#rId").html("Retention");
			$("#eId").html("Expansion");
			$('#ulMenu li').css("padding-left","10px");
			$('a[id^=menuItemIcon]').css("display","none");
			$('a[id^=menuItemId]').css("display","block");
			$("#cnoHead").removeClass("cnoCollapse");
			$("#cnoHead").addClass("cnoExpand");
			$("#logHead").removeClass("logCollapse");
			$("#logHead").addClass("logExpand");
			$("#sfdcId").css("display","block");
			$("#otherId").css("display","block");
			$('#wirDivId').css("display","block");
			$('#ulPlaySec').html(playSecData);
			$('#growUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			 $('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			 $('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			$('#ulPlaySec').css("display","block");
			$("#tglId").css("padding-top","0px");
			$("#tglId").css("width","19%");
			/* $("#growthId").click(function(){
						var Gclass=$("#growthId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
					//		$("#gIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
					//		$("#gIconId").attr("class","fa fa-chevron-down")
						}
					}); */
			/*  $("#retentionId").click(function(){
					var Gclass=$("#retentionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-down")
					}
				}); */
				/*  $("#expansionId").click(function(){
					var Gclass=$("#expansionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-down")
					}
				}); */
			/* $("#growthId").click(function(e){
				//alert("growthId jsp 1636");
				//e.preventDefault();
				return false;
				
			});
			
			$("#retentionId").click(function(){
				return false;
			});
			
			$("#expansionId").click(function(){
				return false;
			});	  */

			$('.modal-dialog').draggable({
			    handle: ".modal-header"
			});
		    }
        } else {
        	var cval=$.cookie('sidebar_closed');
        	if(undefined !=cval && '' !=cval && cval==1){ 
        		$("#stplId").html("");
    			$("#stplLogoId").css("display","none");
    			$("#notiId").html("");
    			$("#gId").html("");
    			$("#rId").html("");
    			$("#eId").html("");
    			$('#ulMenu li').css("padding-left","0px");
    			$('a[id^=menuItemIcon]').css("display","block");
    			$('a[id^=menuItemId]').css("display","none");
    			$("#cnoHead").removeClass("cnoExpand");
    			$("#cnoHead").addClass("cnoCollapse");
    			$("#logHead").removeClass("logExpand");
    			$("#logHead").addClass("logCollapse");
    			$("#sfdcId").css("display","none");
    			$("#otherId").css("display","none");   
    			$('#wirDivId').css("display","none");
    			$('#ulPlaySec').html("");
    			$('#ulPlaySec').css("display","none");
    			$("#tglId").css("width","100%");	
        	}
        }
    });
		
	 /* $("#tglId").click(function(){
		var val=$("#stplId").html();
		if(undefined !=val && ''!=val){  
			$("#stplId").html("");
			$("#stplLogoId").css("display","none");
			$("#notiId").html("");
			$("#gId").html("");
			$("#rId").html("");
			$("#eId").html("");
			$('#ulMenu li').css("padding-left","0px");
			$('a[id^=menuItemIcon]').css("display","block");
			$('a[id^=menuItemId]').css("display","none");
			$("#cnoHead").removeClass("cnoExpand");
			$("#cnoHead").addClass("cnoCollapse");
			$("#logHead").removeClass("logExpand");
			$("#logHead").addClass("logCollapse");
			$("#sfdcId").css("display","none");
			$("#otherId").css("display","none");
			$('#wirDivId').css("display","none");
			$('#ulPlaySec').html("");
			$('#ulPlaySec').css("display","none");
			//$("#tglId").css("padding-top","50px");
			$("#tglId").css("width","100%");
			$(".canvasjs-chart-canvas").removeAttr("style");
		
			
			$("#menuItemId1").css('display','none'); //Users
			$("#menuItemId2").css('display','none'); //Orders
			$("#menuItemId4").css('display','none'); //ShipTo
			$("#menuItemId5").css('display','none'); //Recommendations
			$("#menuItemId6").css('display','none'); //Stores Near By
			$("#ulPlaySec").css('display','none');  //All- CALL TO ACTION */
			
		/* }else{
			$("#stplId").html("STAPLES");
			$("#stplLogoId").css("display","block");
			$("#notiId").html("Call To Action");
			$("#gId").html("Growth");
			$("#rId").html("Retention");
			$("#eId").html("Expansion");
			$('#ulMenu li').css("padding-left","10px");
			$('a[id^=menuItemIcon]').css("display","none");
			$('a[id^=menuItemId]').css("display","block");
			$("#cnoHead").removeClass("cnoCollapse");
			$("#cnoHead").addClass("cnoExpand");
			$("#logHead").removeClass("logCollapse");
			$("#logHead").addClass("logExpand");
			$("#sfdcId").css("display","block");
			$("#otherId").css("display","block");
			$('#wirDivId').css("display","block");
			$('#ulPlaySec').html(playSecData);
			$('#growUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			 $('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			 $('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
			$('#ulPlaySec').css("display","block");
			$("#tglId").css("padding-top","0px");
			$("#tglId").css("width","19%");
			$(".canvasjs-chart-canvas").attr("style","width:100% !important;");
			
			$("#growthId").click(function(e){
				//alert("growthId jsp 1769");
				//e.preventDefault();
				return false;
				
			});
			$("#retentionId").click(function(){
				return false;
			});
			$("#expansionId").click(function(){
				return false;
			});	 
			
			alert("else");
			$("#menuItemId1").css('display','none'); //Users
			$("#menuItemId2").css('display','none'); //Orders
			$("#menuItemId4").css('display','none'); //ShipTo
			$("#menuItemId5").css('display','none'); //Recommendations
			$("#menuItemId6").css('display','none'); //Stores Near By
			$("#ulPlaySec").css('display','none');  //All- CALL TO ACTION

			
			$('.modal-dialog').draggable({
			    handle: ".modal-header"
			});
		}
	}); */ 
	 
	 $("#menuItemId10, #menuItemIcon10").click(function(){
			var val=$("#stplId").html();
			$('a[id^=menuItemId10]').css("background-color","transparent");
			if(undefined !=val && ''!=val){  
				/* $("#ulPlaySec").addClass("page-sidebar-menu-closed");
				$("#ulIamIdSec").addClass("page-sidebar-menu-closed");
				$("#ulMenu").addClass("page-sidebar-menu-closed");
				$("body").addClass("page-sidebar-closed"); */
				$("#stplId").html(""); 
				$("#stplLogoId").css("display","none");
				$("#notiId").html("");
				$("#gId").html("");
				$("#rId").html("");
				$("#eId").html("");
				$('#ulMenu li').css("padding-left","0px");
				$('a[id^=menuItemIcon]').css("display","block");
				$.cookie('sidebar_closed', '1');
				$('a[id^=menuItemId]').css("display","none");
				$("#cnoHead").removeClass("cnoExpand");
				$("#cnoHead").addClass("cnoCollapse");
				$("#logHead").removeClass("logExpand");
				$("#logHead").addClass("logCollapse");
				$("#sfdcId").css("display","none");
				$("#otherId").css("display","none");
				$('#wirDivId').css("display","none");
				$('#ulPlaySec').html("");
				$('#ulPlaySec').css("display","none");
				//$("#tglId").css("padding-top","50px");
				$("#tglId").css("width","100%");
				$(".canvasjs-chart-canvas").removeAttr("style");
				if(($(window).width()<=1024) && ($(window).width() > 768)){
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
				}else{ 
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
				}
				/* $("#slideIcon").removeClass('fa fa-chevron-circle-right');
				$("#slideIcon").addClass('fa fa-chevron-circle-left'); */
			}else{
				$("#ulPlaySec").removeClass("page-sidebar-menu-closed");
				$("#ulIamIdSec").removeClass("page-sidebar-menu-closed");
				$("#ulIdOther").removeClass("page-sidebar-menu-closed");
				$("#ulMenu").removeClass("page-sidebar-menu-closed");
				$("body").removeClass("page-sidebar-closed");
				$("#stplId").html("STAPLES");
				$("#stplLogoId").css("display","block");
				$("#notiId").html("Call To Action");
				$("#gId").html("Growth");
				$("#rId").html("Retention");
				$("#eId").html("Expansion");
				$('#ulMenu li').css("padding-left","10px");
				$('a[id^=menuItemIcon]').css("display","none");
				$('a[id^=menuItemId]').css("display","block");
				$.cookie('sidebar_closed', '0');
				$("#cnoHead").removeClass("cnoCollapse");
				$("#cnoHead").addClass("cnoExpand");
				$("#logHead").removeClass("logCollapse");
				$("#logHead").addClass("logExpand");
				$("#sfdcId").css("display","block");
				$("#otherId").css("display","block");
				$('#wirDivId').css("display","block");
				$('#ulPlaySec').html(playSecData);
				$('#growUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
				 $('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
				 $('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
				$('#ulPlaySec').css("display","block");
				$("#tglId").css("padding-top","0px");
				$("#tglId").css("width","19%");
				$(".canvasjs-chart-canvas").attr("style","width:100% !important;");
				if(($(window).width()<=1024) && ($(window).width() > 768)){
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:105% !important;")
				}else{
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:105% !important;")
				}
				/* $("#slideIcon").removeClass('fa fa-chevron-circle-left');
				$("#slideIcon").addClass('fa fa-chevron-circle-right'); */
				/* $("#growthId").click(function(){
							var Gclass=$("#growthId").attr("class");
							if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						//		$("#gIconId").attr("class","fa fa-chevron-up")
							}else if(undefined != Gclass &&  '' ==Gclass){
						//		$("#gIconId").attr("class","fa fa-chevron-down")
							}
						}); */
				 /* $("#retentionId").click(function(){
						var Gclass=$("#retentionId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#rIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#rIconId").attr("class","fa fa-chevron-down")
						}
					}); */
					 /* $("#expansionId").click(function(){
						var Gclass=$("#expansionId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#eIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#eIconId").attr("class","fa fa-chevron-down")
						}
					}); */
			}
		});
	$("input[name='query']").click(function(){
		$(".search-form.search-form-expanded").css("width","230px");
	});
	
 	$("ul li a[id^=menuItemId]").click(function(){
		$("ul li a[id^=menuItemId]").css("background-color","#1d2939");
		$("ul li a[id^=menuItemId]").css("color","#fff");
		$("ul li a[id^=menuItemId]").css("border","none");
		if(undefined != this && undefined !=this.id)
		var menuItemId=this.id;
		if(undefined !=menuItemId && '' !=menuItemId && menuItemId !='menuItemId10'){
			$("#"+menuItemId).css("background-color","#fff");
			$("#"+menuItemId).css("color","#444");
			$("#"+menuItemId).css("border","border: 1px solid #444;");
			
			
			$("#wirDivId").css("color","#fff");
			$("#wirDivId").css("background-color","#1d2939");
			$("#wirDivId").css("border","border: 1px solid #444;");
			$("#menuItemIdWIR").css("background-color","#1d2939");
			$("#menuItemIdWIR").css("color","#fff");
			
			if(menuItemId=='menuItemId0'){
				var superUserCls=$("#showHideId0").attr('class');
				var orderDtlCls=$("#showHideId1").attr('class');
				  var shipToCls= $("#showHideIdShipTo").attr('class');
				   if(superUserCls=='fa fa-times'){
				    $("#superId").css('display','none');
				   } 
				   if(orderDtlCls=='fa fa-times'){
					   $("#cusId").css('display','none');
				   }
				   if(shipToCls=='fa fa-times'){
					   $("#shipToId").css('display','none');
				   } 
			}
		}
	}); 
	$("ul li a[id^=menuItemIcon]").click(function(){
		var menuItemId=this.id;
		if(undefined !=menuItemId && '' !=menuItemId){
		if(menuItemId=='menuItemIcon0'){
			var superUserCls=$("#showHideId0").attr('class');
			var orderDtlCls=$("#showHideId1").attr('class');
			var shipToCls= $("#showHideIdShipTo").attr('class');  
			   if(superUserCls=='fa fa-times'){
			    $("#superId").css('display','none');
			   } 
			   if(orderDtlCls=='fa fa-times'){
				   $("#cusId").css('display','none');
			   } 
			   if(shipToCls=='fa fa-times'){
				   $("#shipToId").css('display','none');
			   } 
		 }	
		}
		if(menuItemId=='menuItemIcon10'){
			$("#"+menuItemId).css("background-color","#1d2939");
		}
	});
	
	
	$('#printOrderInfoBtn').click(function () {
		$('#order-info .modal-content').printArea();
	});
	
	$('#printUserInfoBtn').click(function () {
		$('#super-info-dialog .modal-content').printArea();
	});
	
	// log user activity; Clicked WIR
	logUserActivity(6003, 'Clicked On WIR');
});

function populateYearMonthData(){
	var v=$(".ui-datepicker-month").is(":visible")
	//alert("visible ="+v)
       var selectedDeviceModel = $('.ui-datepicker-month');
       var months = ['Jan', 'Feb', 'Mar', "Apr","May","June","July","Aug","Sep","Oct","Nov","Dec","All" ];
       selectedDeviceModel.empty();
   	 var count=0;
   	for(count=0;count<months.length;count++){
   		selectedDeviceModel.append($('<option/>', {
   			value : count,
   			text :  months[count]
   		}));
   	} 
   	//alert($('.ui-datepicker-month').html()) 
	
}
</script>



<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</BODY>
</HTML>
 