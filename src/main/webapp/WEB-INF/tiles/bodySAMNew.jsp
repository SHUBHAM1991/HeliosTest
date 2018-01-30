
<head>
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
    var scriptsOrCss = ['canvas.js','common.js'];
    // Loop through each script name and append our new version number
        scriptsOrCss.map(function(scriptOrCss){
        var currentScriptOrCssVer = ${initParam.helios_ver} /*'Ver6.0' incrementScriptOrCssVer(scriptOrCss); */
        document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + scriptOrCss + "?helios_version=" + currentScriptOrCssVer + " '><\/script>");
    });
})();
</script>

  <style type="text/css">
  .portlet-title{
  cursor:move;
  }
  .ui-state-highlight  {
	background:transparent !important;
	border:none !important;
   }
  .canvasjs-chart-canvas{
   /*   width:100% !important; */
  }
  .ui-datepicker-calendar {
    display:none;
   }
  .popover{
  max-width:330px !important;
  color:#4D4D4D !important;
  }
  .popover-content{
   font-family:Helvetica !important;
   font-weight:bold !important;
   box-shadow:1px 2px 3px rgba(0,0,0,0.2) !important
   
  }
  div.amcharts-chart-div{
  height:286px !mportant;
  }
  div.amcharts-chart-div a{
   display:none !important;
  }
  div.amcharts-chart-div svg{
  width:100% !important;
  }
  .ui-datepicker-next , .ui-datepicker-prev, .ui-datepicker-current {
  display:none !important;
  }
  .popoverBasic{
  max-width:400px !important;
  }
  .tooltip{
  z-index: 1151 !important;
  }
  /* .tooltip-inner {
    white-space:pre-wrap;
} */
  a#mob + div.tooltip{
  text-align:left;
  }

  .offer{
	background:#fff; border:1px solid #ddd; box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); margin: 15px 0; overflow:hidden;
  }
  .offer-default {	border-color: #999999; }
  .offer-default .shape{
	border-color: transparent #999999 transparent transparent;
  }
  .offer-content{
	padding:0 20px 10px;
  }
  div#hk_summ a.list-group-item{
	position:relative !important;
	display:block !important;
	padding:5px 15px !important;
	margin-bottom:-1px !important;
	background-color:#fff !important;
	border:1px solid #ddd !important;
	height:auto !important;
}
div#hk_sdl a.list-group-item{
	position:relative !important;
	display:block !important;
	padding:5px 15px !important;
	margin-bottom:-1px !important;
	background-color:#fff !important;
	border:1px solid #ddd !important;
	height:auto !important;
}
div#hk_sdl a.list-group-item:first-child{
 border-top-right-radius:4px !important;
 border-top-right-left:4px !important;
}
div#hk_summ a.list-group-item:first-child{
 border-top-right-radius:4px !important;
 border-top-right-left:4px !important;
}
</style>
</head>
	<!-- BEGIN CONTENT -->
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
						<img src="http://findicons.com/files/icons/1963/colorcons_blue/128/undo.png"/>
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
				
				<div class="row">
				<div class="col-lg-12">
				
				<div class="portlet box black" id="cpIdSam" style="border: 1px solid #ababab !important;background-color: #fff;border-color: #fff;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;cursor:default;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="" style="font-size: 16px;font-weight: 600;color: #555;">CUSTOMER PROFILE <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>
									<a href="javascript:void()" target="_blank" data-placement="bottom" data-toggle="tooltip" title="Click To See More Info">
									<i class="fa fa-question-circle" aria-hidden="true" style="padding-left: 5px;color: #fff;font-size: 114%;vertical-align: middle;"></i></a>  
								</div>
								<div class="tools">
								<a id="childInfoId" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="manual" data-placement="left" style="float: left !important;box-shadow:none;outline:0;text-decoration:none;">
                                 <span style="outline:0;box-shadow:none;vertical-align: middle;width:auto;background-color: deepskyblue;color: #fff;font-size: 12px;padding: 4px 8px;font-weight: 600;border-radius: 20px !important;font-family:helvetica;">Sub Rewards</span>
                                 </a>
									<a class="fa fa-chevron-up" id="showHideId4" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
							</div>
					<div class="portlet-body" id="cpContent" style="padding-top:5px;">
						<div class="col-md-12 col-sm-12 portlet-body" id="cpInfoContent" style="padding: 0px;height: auto;">
							<div class="col-xs-12 text-left" style="padding-left: 5px;color: #333;padding-right: 0px;">
								<div style="height: auto;" class="" id="compNameCont">
									
								</div>

								<div class="row" style="margin-left: 0px; margin-right: 0px;">
									<div style="padding-top: 6px !important;padding: 0px;height: auto;" class="col-lg-3 col-md-4">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Master
											Rewards #:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custNum" class="letterSpaceOR"></span>
											
									     <div style="height: auto;padding-top: 8px;">
										   <span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Enroll date:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custEnrollDate" class="letterSpaceOR"> </span>
										 </div>
										 <div style="padding-top: 8px; height: auto;">
									       <span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Tier:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custType" class="letterSpaceOR"> 
										   </span>
								         </div>
									</div>
									<div style="padding: 0px; height: auto;padding-top:6px;" class="col-lg-4 col-md-4">
									    <div style="padding-bottom: 8px !important;padding: 0px;height: auto;" class="col-lg-12 col-md-12">
												<span class="letterSpaceOR col-lg-3" style="padding: 0px;font-family: Helvetica;font-size: 14px;font-weight: bold;color: #262626;">Phone:</span><span style="padding-left: 2px;font-family: arialmt;font-size: 13px;color: #262626;" id="custConNum" class="letterSpaceOR col-lg-8"> </span>
										</div>   
									<div style="height: auto;padding-top: 8px;">
										<span class="letterSpaceOR col-lg-3" style="padding-left: 0px;font-family: Helvetica;font-size: 14px;font-weight: bold;color: #262626;padding-right: 0px;">
											Address:</span>
										<span style="padding-left: 3px;font-family: arialmt;font-size: 13px;color: #262626;white-space:pre-wrap;padding-right: 0px;" id="custAddDtl" class="letterSpaceOR col-lg-8"></span>
									</div>	
									<!-- <div style="height: auto;padding-top: 8px;">
										<span class="letterSpaceOR col-lg-3" style="padding-left: 0px;font-family: Helvetica;font-size: 14px;font-weight: bold;color: #262626;padding-right: 0px;">
											Timezone:</span>
										<span style="padding-left: 3px;font-family: arialmt;font-size: 13px;color: #262626;white-space:pre-wrap;padding-right: 0px;" id="timeZoneId" class="letterSpaceOR col-lg-8"></span>
									</div> -->
									</div>
									<div style="padding: 0px;height: auto;padding-top:6px;" class="col-lg-5 col-md-4">
									 <span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Contact
											Person:</span> 
											<span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custContactDetail" class="letterSpaceOR"> 
										    </span>
										   
										<div style="padding: 0px; height: auto;padding-top: 8px;" class="col-lg-12 col-md-12">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Industry group:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custIndGrp" class="letterSpaceOR"> </span>
								       </div>
										
									</div>

								</div>
								
							</div>
						</div>

					</div>
					
				</div>
				<!--
				<div class="portlet box black" id="cpId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
					<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
						<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
							<span class="" style="font-size: 16px;font-weight: 600;color: #555;">CUSTOMER PROFILE <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
						</div>
						<div class="tools">
							<a class="fa fa-chevron-up" id="showHideId4" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
							</a>
							
						</div>
					</div>
					<div class="portlet-body" id="cpContent" style="height:70px">
						<div class="col-md-4 col-sm-4 portlet-body" id="cpInfoContent" style="height:auto">
							<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
                                  	<div style="height:auto" class="" id="compNameCont" ><span class="letterSpace" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#333;" class="letterSpace">  </span></div>
								<div style="height:auto" class=""> <span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Customer Number: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold" id="custNum" class="letterSpace">  </span></div>
								<div style="height:auto" class=""><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Contract Type: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custType" class="letterSpace"></span></div>
                                   <div style="height:auto; display: none" class="" id="ltsInfoColumn"><span class="letterSpace" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Premium Lifetime Savings: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="ltsInfo" class="letterSpace"></span></div>
                               </div>
						</div>
						<div class="col-md-8 col-sm-8  portlet-body" id="cpAddressContent" style="height:auto">
							<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
								<div style="height:auto;width:100%;" class=""> 
									<div class="letterSpace addCls" style="font-family: Helvetica;font-weight:bold;color: #333;width:10%;float:left;text-align:right;display:none;" class="large" id="addrTxt">Address: </div>
									<div style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;width:90%;float:right;margin-left:0px;padding-left:4px;" id="custAdd1" class="letterSpace">  </div>
								</div>
								<div style="height:auto" class="">
									<div class="letterSpace addCls" style="font-family: Helvetica;font-weight:bold;color: #333;width:10%;float:left;text-align:right;display:none;" class="large">&nbsp; </div>
									<div style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;width:90%;float:right;margin-left:0px;padding-left:4px;" class="letterSpace">  
										<span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custAdd2" class="letterSpace"></span>
									</div>											
								</div>										
                                </div>
						</div>
					</div>
				</div>
				-->
				</div>
				</div>
				
				
				
				<div class="row sortableRow"  style="margin-left:0px !important; margin-right: 0px !important;">
				
					<div class="page-bar col-sm-12" style="box-shadow: 0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)">
						<ul class="page-breadcrumb"
							style="padding-bottom: 5px !important;">
							<li id="crumgLiId" style="padding-left: 0px; padding-top: 3px;" ><div style="float: left; padding-right: 10px;">
									<span id="reportYrId"
										style=" font-family: Helvetica; color: #004c74; font-weight: bold; float: left;">Select Fiscal Year To See Report:</span><span><select id="yrId"
										style="margin-left: 10px !important; color: #004c74; height: 23px; border: 1px solid #004c74;"
										class="input-sm">
									</select></span>
								</div>
								<span style="display: none;" id="tempData"></span>
								<div style="float: left; padding-right: 10px;">
									<span id="custSegId"
										style="font-family: Helvetica; color: #004c74; font-weight: bold;  float: left;">Customer
										Segment:</span> <span><a id="example" tabindex="0"
										class="btn btn-sm btn-danger" role="button"
										data-toggle="popover" data-trigger="focus"
										title="Customer Segment" onclick="generateSegLog();"
										style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica;"></a></span>
								</div>
								<span style="display: none;" id="tempDataQual"></span>							
								<div style="float: left; padding-right: 10px;">
									<span id="lastContactedDateId"
										style="font-family: Helvetica; color: #004c74; font-size: 14px; font-weight: bold; float: left;">Last Contacted Date:</span> <span id="lastContactedDateValueId" style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica; color: #004c74;"></span>
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<!-- END PAGE HEADER-->
				<!-- BEGIN DASHBOARD STATS -->
				<div class="row">
					
					<div class="col-lg-12" id="userDiv">
					<div class="portlet box green" style="display:none;border:1px solid #ababab;background-color:#004c74;" id="superId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpace">Users <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideId0" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-placement="top" data-toggle="tooltip" title="Close Super User">
									</a>
									
								</div>
								<!-- <div id ='updateDateValueUser' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				                <div id="updateDateLabelUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
				
							</div>
							<div class="portlet-body" id="superContent" style="display: none;height:auto;">
						<div class="page-bar col-lg-12 col-sm-12 col-md-12"
							style="height: 16px; font-size: 14px; color: #333; font-family: ArialMT; font-weight: 600; letter-spacing: .5px;">
							The Users Section shows all order contacts (customer users) on
							that account that have either ordered or browsed through Staples.com.</div>
						<div id="superUserTableId" class="dataTable_wrapper">
                                <table class="table table-striped table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Contact Name </th>
											<th>Email Address</th>
											<th>Phone</th>
											<th>Time Zone</th>
											<th>Staples.com Activity</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                              
                                    </tbody>
                                </table>
                                
                            </div>
								
								
							</div>
						</div>
						<div class="portlet box black" style="display:none;border: 1px solid #ababab !important;background-color: #fff;border-color: #fff;" id="shipToId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span style="font-size: 16px;font-weight: 600;color: #555;">SHIP TO <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideIdShipTo" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Ship To Data" title="">
									</a>
									
								</div>
								
								<div id="updateDateValueShip" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				
								<div id="updateDateLabelShip" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
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
						<div class="portlet box" id="cusId" style="display:none;background-color:#004c74;border:1px solid #ababab;">
							<div class="portlet-title" style="background-color:#ffffff; color: #4d4d4d; border-bottom:1px solid #4d4d4d">
								<div class="caption"  style="font-family:Helvetica !important;color:#4d4d4d">
									<span>ORDER DETAILS <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideId1" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<div id="updateDateValueOrder" style="font-family: Helvetica; float:right; font-size: 10px; background-color:#fff; color:#4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
                				<div id="updateDateLabelOrder" style="font-family: Helvetica; float:right; font-size: 10px; background-color:#fff; color:#4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
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
				
				
				<div class="row" style="margin-left:-7px;">
				<div class="col-lg-6 sortableCol" style="padding-left:8px;">
						<!-- END PORTLET-->
					 	
						<!-- chart data start -->
						<div class="portlet box black" style="border: 1px solid #ababab !important;background-color: #fff;border-color: #fff;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)" id="catId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;" id="catTitle">
									  
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId2" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								
							</div>
							<div class="portlet-body" id="catContent" style="height:430px;padding-top:0px;">
								 <!-- <div id="pie"> style="border:1px solid #ef3f3f;border-radius:25px !important;"</div> -->
								 <div id="chartContainerPie" style="height:400px;"></div> 
								<!-- <div id="chartContainerPie" 
							style="width: 430px; height: 490px; position: relative; top: 70px; left: 55px; z-index: 2; border: 1px solid #ccc;background-color:#fff;">
						</div>

						<div id="chartContainerPie2"
							style="width: 430px; height: 490px; position: relative; top: -470px; left: 15px; z-index: 1; border: 1px solid #ccc;">
						</div> -->
							</div>
						</div>
						<!--  chart data end -->
						
						<div class="portlet box green" id="amId" style="display:none;border:1px solid #ababab;background-color:#004c74;">
							<div class="portlet-title">
							<!--  <div class="caption" style="font-family:Helvetica !important;">
									<span class="letterSpace" id="AccountMgrId">Account Manager 1 <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>-->
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId6" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
							</div>
							<div class="portlet-body" id="amContent" style="height:100px;">
								 <div class="col-xs-12 text-left" id="managerInfo" style="color:#333;padding-right:0px;">
                
                                  </div>
							</div>
						</div>
						
					</div>
					
					<div class="col-lg-6 customPad sortableCol">
					<!-- Ytd Data Start -->
					<div class="portlet box green" id=ctId" style="border:1px solid #ababab;background-color:#004c74;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;"> Rewards Savings
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId12" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<div style="float:right">
									<!-- <div id ='updateDateValueYTDSum' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
									<div id="updateDateValueYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
									<!-- <div id ='updateDateLabelYTDSum' style="float:right;"><span  class="" role="button" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;">Refreshed: </span></div> -->
									<div id="updateDateLabelYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span>
									</div>
								</div>
							</div>
							<div class="portlet-body" id="rewardSavingContent" style="height:auto;padding-top:30px;">

                              <div style="font-size: 20px; text-align: center; padding: 50px; color: #bbb;">
							Coming soon!</div>
							</div>
						</div>
					<!-- Ytd Data End -->
					
					<!-- Schedule Data Start -->
					<div class="portlet box green" id="ytId" style="border:1px solid #ababab;background-color:#004c74;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;"> Category Savings 
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId10" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<div style="float:right">
									
									<div id="updateDateValueYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
									<div id="updateDateLabelYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span>
									</div>
								</div>
							</div>
							<div class="portlet-body"  style="height: auto;" id="categoryContent">

						 <div class="wrapper" id="catSavingContent">
        
                         </div>
					</div>
						</div>
					<!-- Schedule Data End -->
					
						<!-- SA Feature Start  -->
						 <div class="portlet box green" id="saId" style="display:none;border:1px solid #ababab;background-color:#004c74;">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span class="">SA.com Differentiators<a href="javascript:openUrl('./resources/docs/SA com Features For Helios QRG - June 2016.pdf#zoom=70,0,0&scrollbar=1');" target="_blank" data-placement="bottom" data-toggle="tooltip" title="Click To See More Info">
									<i class="fa fa-question-circle" aria-hidden="true" style="padding-left: 5px;color: #fff;font-size: 114%;vertical-align: middle;"></i></a></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId8" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:void();" data-original-title="SA Differentiators" title="">
									</a>
									
								</div>
								<div style="float:right">
								<div id="updateDateValueSBASum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
                				<div id="updateDateLabelSBASum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
				      			</div>
								
							</div>
							<div class="portlet-body" id="saContent" style="height:auto;">
                             
					</div>
						</div>
						<!-- SA Feature End  -->
						
					</div>
				</div>
				<!-- END DASHBOARD STATS -->
				<div class="clearfix">
				</div>
				<!-- HAWKEYE STARTS -->
				<div class="portlet box green" style="border:1px solid #ababab;background-color:#10acaf;" id="Hk_Id">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;" id="Hk_Title">
									  Hawkeye Details
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId13" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-original-title="Open Hawkeye Details" title="">
									</a>
								</div>
								<div id="updateDateValueHk" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
								<div id="updateDateLabelHk" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div>
			<div class="portlet-body" id="Hk_Content"
				style="padding: 15px; overflow-y: auto;overflow-x:hidden;padding-left:0px;box-shadow: 0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);">
				
				<div id="hk_sec1" style="width: 77%;position: absolute;">
				
				</div>
				<!-- Slider content  for customer data start -->
		
				<div class="col-md-12" id="hk_sec2" style="display:none;background-color: #fff;font-size: 20px;text-align: center;overflow-y:auto;">
                    <div class="row col-md-10 col-md-offset-1 custyle" style=" color: crimson;">Category data</div>
				    <div class="row col-md-10 col-md-offset-1 custyle" style="">
				    <table class="table table-striped custab" id="categoryTabId">
				    
				    </table>
				    </div>

				</div>
				
				<!-- Slider content  for customer data ends -->
			</div>
		</div>
    <div class="row">
        
    </div>
                <div class="clearfix">
				</div>
				<!-- HAWKEYE ENDS -->
				
				<div class="portlet box green" style="border:1px solid #ababab;background-color:#10acaf;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)" id="rewardsId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpace">Rewards <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId11" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-placement="top" data-toggle="tooltip" title="Close rewards">
									</a>
								</div>
								
							</div>
							<div class="portlet-body" id="rewardsContent" style="height:auto;">
						<div id="rewardsTableId" class="dataTable_wrapper">
                                <table class="table table-striped table-hover" id="dataTables-rewards" style="border-collapse:collapse !important;">
                                    <thead>
                                        <tr>
                                                <th>Period From</th>
												<th>Period To </th>
												<th>Statement Amount</th>
												<th>Used</th>
												<th>Tier Id </th>
												<th>Statement Code</th>
												<th>Redeemed Member No.</th>
												<th>Expdate</th>
												<th>Version Id</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                                                            
                                    </tbody>
                                </table>
                                
                            </div>
								
								
							</div>
						</div>
					
				<div class="row sortableRow">
					
					<div class="col-lg-12">
						<!-- BEGIN PORTLET-->
						<div class="portlet box green" style="border:1px solid #ababab;background-color:#10acaf;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);" id="mnthId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;" id="mnthTitle">
									  
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId3" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<div id="updateDateValueYearSpend" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
								<div id="updateDateLabelYearSpend" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div>
							<div class="portlet-body" id="mnthContent" style="height:auto;">
			               
								<div id="site_activities_content" style="height:286px !important;margin-top:10px;"> <!-- class="display-none"-->
									<!-- <div id="chartContainer" style="text-align:center;margin-bottom:0px;">
										  
								   </div> -->
								   <div id="chartdiv"></div>
								</div>
								
							</div>
						</div>
						<!-- END PORTLET-->
					</div>
				</div>
				<div class="clearfix">
				</div>
				
				<div class="row sortableRow">
					<div class="col-lg-12">
							<div class="portlet box green" id="samMtaId" style="border:1px solid #ababab;background-color:#004c74;box-shadow:0 8px 17px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;" id="samMonthTitle">
									
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId14" style="background-color:#fff;color:#4d4d4d; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
								<div id="updateDateValueMnth" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				
								<div id="updateDateLabelMnth" style="font-family: Helvetica; float:right; font-size: 10px; color: #4d4d4d; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div>
							<div class="portlet-body" id="samMtaContent" style="">
							 <div class="">
							<button type="button"
								class="btn btn-info btn-sm collapse ORrow retailCls"
								style="float: left; border: none; background-color: #598b9a; padding: 2px 12px 2px 12px; cursor: default;">Retail</button>
							<button type="button" class="btn btn-info btn-sm collapse ORrow onlineCls"
									style="float:left;border: none; background-color: #808080; padding:2px 12px 2px 12px;cursor:default;">Staples.com</button>
								<div id="divChkId" class="checkbox" style="margin-top: 5px; float: right;">
									
								</div>
						    </div>
								<div id="samTableId" class="dataTable_wrapper">
                                <table class="table" id="sam-dataTables-example1">
                                    <thead>
                                        
                                    </thead>
                                    <tbody>
                                       
                                    </tbody>
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
                                
                                <table class="table  compact dt-responsive" id="dataTables-QuickSearch" cellspacing="5">
                                    <thead>
                                         <th data-toggle="tab" style="color:#ddd;" >CUSTOMERS</th>
                                         
                                          
                                    </thead>
                                    <tbody>
										
                                    </tbody>
                                </table>
                                
                            </div>
									</ul>
							
								</div>
								<div class="page-quick-sidebar-item">
									<div class="page-quick-sidebar-chat-user">
										<div class="page-quick-sidebar-nav">
											<a href="javascript:;" class="page-quick-sidebar-back-to-list"><i class="icon-arrow-left"></i>Back</a>
										</div>
										<div class="page-quick-sidebar-chat-user-messages">
											<div class="post out">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar3.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Bob Nilson</a>
													<span class="datetime">20:15</span>
													<span class="body">
													When could you send me the report ? </span>
												</div>
											</div>
											<div class="post in">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar2.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Ella Wong</a>
													<span class="datetime">20:15</span>
													<span class="body">
													Its almost done. I will be sending it shortly </span>
												</div>
											</div>
											<div class="post out">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar3.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Bob Nilson</a>
													<span class="datetime">20:15</span>
													<span class="body">
													Alright. Thanks! :) </span>
												</div>
											</div>
											<div class="post in">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar2.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Ella Wong</a>
													<span class="datetime">20:16</span>
													<span class="body">
													You are most welcome. Sorry for the delay. </span>
												</div>
											</div>
											<div class="post out">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar3.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Bob Nilson</a>
													<span class="datetime">20:17</span>
													<span class="body">
													No probs. Just take your time :) </span>
												</div>
											</div>
											<div class="post in">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar2.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Ella Wong</a>
													<span class="datetime">20:40</span>
													<span class="body">
													Alright. I just emailed it to you. </span>
												</div>
											</div>
											<div class="post out">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar3.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Bob Nilson</a>
													<span class="datetime">20:17</span>
													<span class="body">
													Great! Thanks. Will check it right away. </span>
												</div>
											</div>
											<div class="post in">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar2.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Ella Wong</a>
													<span class="datetime">20:40</span>
													<span class="body">
													Please let me know if you have any comment. </span>
												</div>
											</div>
											<div class="post out">
												<img class="avatar" alt="" src="../../assets/admin/layout/img/avatar3.jpg"/>
												<div class="message">
													<span class="arrow"></span>
													<a href="javascript:;" class="name">Bob Nilson</a>
													<span class="datetime">20:17</span>
													<span class="body">
													Sure. I will check and buzz you if anything needs to be corrected. </span>
												</div>
											</div>
										</div>
										<div class="page-quick-sidebar-chat-user-form">
											<div class="input-group">
												<input type="text" class="form-control" placeholder="Type a message here...">
												<div class="input-group-btn">
													<button type="button" class="btn blue"><i class="icon-paper-clip"></i></button>
												</div>
											</div>
										</div>
									</div>
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
										<li>
											<a href="javascript:;">
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-default">
															<i class="fa fa-briefcase"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 IPO Report for year 2013 has been released.
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 20 mins
												</div>
											</div>
											</a>
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
											<a href="javascript:;">
											<div class="col1">
												<div class="cont">
													<div class="cont-col1">
														<div class="label label-sm label-info">
															<i class="fa fa-briefcase"></i>
														</div>
													</div>
													<div class="cont-col2">
														<div class="desc">
															 IPO Report for year 2013 has been released.
														</div>
													</div>
												</div>
											</div>
											<div class="col2">
												<div class="date">
													 20 mins
												</div>
											</div>
											</a>
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
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;width:1100px">
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
  	<!--  dialog end -->
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
  <div class="modal fade" id="rewardsInfoModal" role="dialog" style="z-index:1102">
		    <div class="modal-dialog modal-md" style="margin-top:40px;height:auto;">
		      <div class="modal-content" style="width:100%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          Rewards statement for <span id="stmtCode"></span>
		        </div>
		          <div id="rewardsInfoBody" class="modal-body" style="max-height: 400px;padding:22px 20px;font-size: 16px;font-family: arialmt;overflow-y:auto;">
                 
		          </div>
		        <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;">Close</button>
	        </div>
	      </div>
	    </div>
	  </div>
  <div id="childInfo" style="display:none"></div>
	<!-- log user activity; rep. data -->
	<jsp:include page="/WEB-INF/jsp/TrainingSam.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/CallToActionPopUp.jsp"></jsp:include>
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
	<input type="hidden" name="sfdcAppBaseUrl" id="sfdcAppBaseUrl" value="${requestScope['SFDC_APP_BASE_URL']}" />	
	<form role="form" action="" id="savingsReportForm" method="post" >
	<input name="custNum" id="custNum" type="hidden" value="${requestScope['custNo']}"/>
	</form>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!-- These Files Are StartUp Files,Should Not Have Any Version -->

<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-ui-ipad.js" type="text/javascript"></script>

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
     var scripts = ['app.js','jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','dataTables.bootstrap.min.js','slimscroll.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js','pulsate.js','datepicker.js','metronic.js','layout.js','quick-sidebar.js','amcharts.js','serial.js','light.js','SamDashboard.js','editor.js'];
    // Loop through each script name and append our new version number
    scripts.map(function(script){
        var currentScriptVer = ${initParam.helios_ver} /* incrementScriptVer(script); */
        document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
    });
})();
</script>

<!-- <script type="text/javascript">document.write(unescape("%3Cscript src='" + (("https:" == document.location.protocol) ? "https" : "http") + "://cdn.mouseflow.com/projects/a40355eb-27cf-43ba-9d15-00c0a64d9ad2.js' type='text/javascript'%3E%3C/script%3E"));</script> -->

	<!-- <script type="text/javascript">
		$(function(){
			var perPage = 5;
			var opened = 1;
			var onClass = 'on';
			var paginationSelector = '.pages';
			$('ul.media-list,list-items').simplePagination(perPage, opened, onClass, paginationSelector);
		});
	</script> -->
<!-- END PAGE LEVEL SCRIPTS -->
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
   $("#showHideId2").click(function(){ 
		
	   var cls=$("#showHideId2").attr('class');
	    if(cls=='fa fa-chevron-up'){
    	$("#showHideId2").prop('class','fa fa-chevron-down');
    	$("#catContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId2").prop('class','fa fa-chevron-up');
	    	$("#catContent").css("display","block");
	   } 
	   });
$("#showHideId3").click(function(){ 
	 var cls=$("#showHideId3").attr('class');
	    if(cls=='fa fa-chevron-up'){
  	$("#showHideId3").prop('class','fa fa-chevron-down');
  	$("#mnthContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId3").prop('class','fa fa-chevron-up');
	    	$("#mnthContent").css('display','block');
	   } 
   });
$("#showHideId4").click(function(){ 
	 var cls=$("#showHideId4").attr('class');
	    if(cls=='fa fa-chevron-up'){
 	$("#showHideId4").prop('class','fa fa-chevron-down');
 	$("#cpContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId4").prop('class','fa fa-chevron-up');
	    	$("#cpContent").css('display','block');
	   } 
  });
$("#showHideId5").click(function(){ 
	 var cls=$("#showHideId5").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId5").prop('class','fa fa-chevron-down');
	$("#mtaContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId5").prop('class','fa fa-chevron-up');
	    	$("#mtaContent").css('display','block');
	   } 
 });
$("#showHideId6").click(function(){ 
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
});
$("#showHideId7").click(function(){ 
	 var cls=$("#showHideId7").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId7").prop('class','fa fa-chevron-down');
	$("#ytContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId7").prop('class','fa fa-chevron-up');
	    	$("#ytContent").css('display','block');
	   } 
});
	
$("#showHideId8").click(function(){ 
	 var cls=$("#showHideId8").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId8").prop('class','fa fa-chevron-down');
	$("#saContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId8").prop('class','fa fa-chevron-up');
	    	$("#saContent").css('display','block');
	   } 
});
$("#showHideId9").click(function(){ 
	 var cls=$("#showHideId9").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId9").prop('class','fa fa-chevron-down');
	$("#catPenContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId9").prop('class','fa fa-chevron-up');
	    	$("#catPenContent").css('display','block');
	   } 
});
$("#showHideId10").click(function(){ 
	 var cls=$("#showHideId10").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId10").prop('class','fa fa-chevron-down');
	$("#categoryContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId10").prop('class','fa fa-chevron-up');
	    	$("#categoryContent").css('display','block');
	   } 
});
$("#showHideId11").click(function(){ 
	 var cls=$("#showHideId11").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId11").prop('class','fa fa-chevron-down');
	$("#rewardsContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId11").prop('class','fa fa-chevron-up');
	    	$("#rewardsContent").css('display','block');
	   } 
});
$("#showHideId12").click(function(){ 
	 var cls=$("#showHideId12").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId12").prop('class','fa fa-chevron-down');
	$("#rewardSavingContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId12").prop('class','fa fa-chevron-up');
	    	$("#rewardSavingContent").css('display','block');
	   } 
});

$("#showHideId13").click(function(){ 
	 var cls=$("#showHideId13").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId13").prop('class','fa fa-chevron-down');
	$("#Hk_Content").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId13").prop('class','fa fa-chevron-up');
	    	$("#Hk_Content").css('display','block');
	   } 
});
$("#showHideId14").click(function(){ 
	 var cls=$("#showHideId14").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId14").prop('class','fa fa-chevron-down');
	$("#samMtaContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId14").prop('class','fa fa-chevron-up');
	    	$("#samMtaContent").css('display','block');
	   } 
});

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
$("#growthId").click(function(){
			populateGrowthData();
		     var cls=$("#growthSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#growthSpanId").attr("class","fa fa-chevron-up")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  }
		  });
		  $("#retentionId").click(function(){
			  populateRetentionData();
		     var cls=$("#retentionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#retentionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }
		  });
		  $("#expansionId").click(function(){
			  populateExpansionData()
		     var cls=$("#expansionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#expansionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }
		  });	
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
			$("#growthId").click(function(){
						var Gclass=$("#growthId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#gIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#gIconId").attr("class","fa fa-chevron-down")
						}
					});
			 $("#retentionId").click(function(){
					var Gclass=$("#retentionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-down")
					}
				});
				 $("#expansionId").click(function(){
					var Gclass=$("#expansionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-down")
					}
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
	var playData='';
	 $("#tglId").click(function(){
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
			/* if(($(window).width()<=1024) && ($(window).width() > 768)){
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
			}else{ 
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
			} */
		}else{
			/* $("#ulPlaySec").removeClass("page-sidebar-menu-closed");
			$("#ulIamIdSec").removeClass("page-sidebar-menu-closed");
			$("#ulMenu").removeClass("page-sidebar-menu-closed"); */
			//$("body").removeClass("page-sidebar-closed");
		
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
			/* if(($(window).width()<=1024) && ($(window).width() > 768)){
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:105% !important;")
			}else{
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:105% !important;")
			} */
			$("#growthId").click(function(){
						var Gclass=$("#growthId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#gIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#gIconId").attr("class","fa fa-chevron-down")
						}
					});
			 $("#retentionId").click(function(){
					var Gclass=$("#retentionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#rIconId").attr("class","fa fa-chevron-down")
					}
				});
				 $("#expansionId").click(function(){
					var Gclass=$("#expansionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-up")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#eIconId").attr("class","fa fa-chevron-down")
					}
				});
		}
	}); 
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
				$("#growthId").click(function(){
							var Gclass=$("#growthId").attr("class");
							if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
								$("#gIconId").attr("class","fa fa-chevron-up")
							}else if(undefined != Gclass &&  '' ==Gclass){
								$("#gIconId").attr("class","fa fa-chevron-down")
							}
						});
				 $("#retentionId").click(function(){
						var Gclass=$("#retentionId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#rIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#rIconId").attr("class","fa fa-chevron-down")
						}
					});
					 $("#expansionId").click(function(){
						var Gclass=$("#expansionId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#eIconId").attr("class","fa fa-chevron-up")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#eIconId").attr("class","fa fa-chevron-down")
						}
					});
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
	/* $("#showHideId0").mouseenter(function(){
		if($("div[role=tooltip]").is(":visible")){
			var obj=$("div[role=tooltip]");
			
			$("div[role=tooltip]").css("border-radius","10px")
			
	}) */
	
	
	// add print functionality
	$('#printDashboardBtn').click(function () {
		
		// log user activity; Print Customer Details Page
		logUserActivityDotCom(7007, 'Print Customer Details');
		
		$(".printableDashboard svg").attr("fit-to-page", "true");
		var extraHead = '<scr'+ 'ipt src="./resources/javascript/jquery.min.js" type="text/javascript"></scr' + 'ipt>\n';
		extraHead += '<scr' + 'ipt src="./resources/javascript/dashboard-print.js" type="text/javascript"></scr' + 'ipt>';
		$(".printableDashboard").printArea({"extraHead" : extraHead});
		//window.print();
	});
	
	$('#printOrderInfoBtn').click(function () {
		$('#order-info .modal-content').printArea();
	});
	
	$('#printUserInfoBtn').click(function () {
		$('#super-info-dialog .modal-content').printArea();
	});
	
	
	// log user activity; View Customer Details
	logUserActivityDotCom(7001, 'View SAM Customer Details');
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
<!-- END JAVASCRIPTS -->
<!-- <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-37564768-1', 'keenthemes.com');
  ga('send', 'pageview');
</script> -->
 
