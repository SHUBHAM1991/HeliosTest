
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<!-- <link href="./resources/stylesheet/bootstrap-combine.css" rel="stylesheet" type="text/css"> -->
<link href="./resources/stylesheet/commonStyles.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./resources/stylesheet/common-print.css?helios_version=${initParam.helios_ver}" type="text/css" media="print" >
<script type="text/javascript" src="./resources/javascript/canvas.js?helios_version=${initParam.helios_ver}"></script>

<script type="text/javascript" src="./resources/javascript/app.js?helios_version=${initParam.helios_ver}"></script>
<script type="text/javascript" src="./resources/javascript/common.js?helios_version=${initParam.helios_ver}"></script>
<script type="text/javascript">

 /* window.onload = function () {}
 */
  </script>
  <script type="text/javascript" src="./resources/javascript/canvas.js?helios_version=${initParam.helios_ver}"></script>
  <style type="text/css">
  .canvasjs-chart-canvas{
  display:block !important;
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
#printOnly {
   display : none;
}

@media print {
    #printOnly {
       display : block;
    }
}
  </style>
</head>
		<!-- BEGIN CONTENT -->
	<form role="form"  id="customerForm" method="post">
		<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['custNo']}"/>
		<input name="contactName" id="contactName" type="hidden" value="${requestScope['contactName']}"/>
		<input name="contactDate" id="contactDate" type="hidden" value="${requestScope['contactDate']}"/>
		
		<input name="accId" id="accId" type="hidden" value="${requestScope['accId']}"/>
		<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
		<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
		<input class="form-control"  name="sliderSubPlaysItem" id="sliderSubPlaysItem" type="hidden" value="${requestScope['sliderSubPlaysItem']}" />		
        <input class="form-control"  name="iStart" id="iStart" type="hidden" value="${requestScope['iStart']}" />
        <input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
        <input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
     <input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
    <input class="form-control"  name="subCallToActionSegName" id="subCallToActionSegName" type="hidden" value="${requestScope['subCallToActionSegName']}" />
    <input class="form-control"  name="onlineRetail" id="onlineRetail" type="hidden" value="${requestScope['onlineRetail']}" />
	</form>
	<form action="./home_cust_profiles" id="customerForm1" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
	<input name="contactName" id="contactName" type="hidden" value="${requestScope['contactName']}"/>
		<input name="contactDate" id="contactDate" type="hidden" value="${requestScope['contactDate']}"/>
		
	<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
	<input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
     <input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
	</form>
	<form action="./cp_online_retail" id="customerFormOnlineRetail" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
	<input name="contactName" id="contactName" type="hidden" value="${requestScope['contactName']}"/>
		<input name="contactDate" id="contactDate" type="hidden" value="${requestScope['contactDate']}"/>
		
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
					<!-- <div class="toggler tooltips" data-container="body" data-placement="left" data-html="true" data-original-title="Click to open advance theme customizer panel">
						<i class="icon-settings"></i>
					</div> -->
					<div class="toggler-close">
						<i class="icon-close"></i>
					</div>
					<div class="theme-options">
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
					</div>
				</div>
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<!-- 
               <div style="color:#1d2939" class="page-title">
				<span class="letterSpaceOR" style="border-radius: 40px !important;border: 2px solid #000;padding-right: 5px !important;padding-left: 5px !Important;padding-top: 2px;padding-bottom: 2px;"><i class="fa fa-home" style="font-size:120%;border-radius: 10px;"></i></span><span style="padding-left: 10px;font-size: 32px;/* font-family: Helvetica Neue; */font-size: 26px;/* font-weight: bold; */">Dashboard</span>
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
					<div class="portlet-body" id="cpContent" style="padding-top:5px;">
						<div class="col-md-12 col-sm-12 portlet-body" id="cpInfoContent" style="padding: 0px;height: auto;">
							<div class="col-xs-12 text-left" style="padding-left: 5px;color: #333;padding-right: 0px;">
								<div style="height: auto;" class="" id="compNameCont">
									<a href="javascript:openUrlSfdc('https://na32.salesforce.com/0015000000RBbNeAAL','compname')" id="custCompName" style="font-size: 14px; font-family: Helvetica; font-weight: bold; color: #0092db; line-height: 2;" class="letterSpaceOR"></a>
								</div>

								<div class="row" style="margin-left: 0px; margin-right: 0px;">
									<div style="padding: 0px; height: auto;" class="col-lg-3 col-md-4">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Parent
											Rewards #:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custNum" class="letterSpaceOR"></span>
									</div>
									<div style="padding: 0px; height: auto;" class="col-lg-4 col-md-4">
										<span class="letterSpaceOR col-lg-4" style="padding-left: 0px;font-family: Helvetica;font-size: 14px;font-weight: bold;color: #262626;padding-right: 0px;">Parent
											Address:</span>
										<span style="padding-left: 3px;font-family: arialmt;font-size: 13px;color: #262626;white-space:pre-wrap;padding-right: 0px;" id="custAddDtl" class="letterSpaceOR col-lg-8"></span>
									</div>
									<div style="padding: 0px;height: auto;" class="col-lg-5 col-md-4">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Contact
											Person:</span> 
											<span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custContactDetail" class="letterSpaceOR"> 
											<!-- <span id="custFirstName"> </span> 
												
												<a href="mailto:helios_sa_appsupt@staples.com" style="color:#0092db;text-decoration:none;"><span style="color:#0092db;" id="custEmailId"> </span></a> -->
										    </span>
									</div>

								</div>
								<div class="row" style="margin-left: 0px; margin-right: 0px;margin-top:5px;">
									<div style="padding: 0px; height: auto;" class="col-lg-3 col-md-4">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Tier:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custType" class="letterSpaceOR"> 
											</span>
									</div>
									<div style="padding: 0px; height: auto;" class="col-lg-4 col-md-4">
										<span class="letterSpaceOR" style="font-family: Helvetica; font-size: 14px; font-weight: bold; color: #262626;">Phone:</span><span style="font-family: arialmt; font-size: 13px; color: #262626;" id="custConNum" class="letterSpaceOR"> </span>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- <div class="portlet-body" id="cpContent" style="height:100px">
								<div class="col-md-6 col-sm-6 portlet-body" id="cpInfoContent" style="height:auto">
									<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
                                   	<div style="height:auto" class="" id="compNameCont" ><span class="letterSpaceOR" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#333;" class="letterSpaceOR">  </span></div>
									<div style="height:auto" class=""> <span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Customer Number: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold" id="custNum" class="letterSpaceOR">  </span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Contract Type: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custType" class="letterSpaceOR"></span></div>
                                    <div style="height:auto; display: none" class="" id="ltsInfoColumn"><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Premium Lifetime Savings: </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="ltsInfo" class="letterSpaceOR"></span></div>
                                  </div>
								</div>
								<div class="col-md-6 col-sm-6  portlet-body" id="cpAddressContent" style="height:auto">
										<div class="col-xs-12 text-left" style="color:#333;padding-right:0px;">
										<div style="height:auto;width:100%;" class=""> <div class="letterSpaceOR addCls" style="font-family: Helvetica;font-weight:bold;color: #333;width:24%;float:left;text-align:right;display:none;" class="large" id="addrTxt">Address: </div><div style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;width:76%;float:right;margin-left:0px;padding-left:4px;" id="custAdd1" class="letterSpaceOR">  </div></div>
										<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custAdd2" class="letterSpaceOR"></span></div>
										<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custCity" class="letterSpaceOR"></span></div>
										<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custState" class="letterSpaceOR"></span></div>
										<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custCountry" class="letterSpaceOR"></span></div>
										<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large"></span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="custZip" class="letterSpaceOR"></span></div>
                                  </div>
								</div>
							</div>-->
						</div>
				
				</div>
				</div>
				
				
				
				<div class="row"  style="margin-left:0px !important; margin-right: 0px !important;">
					<!-- div class="page-bar" style="float: left;">
						<ul class="page-breadcrumb">
							<li>
								<div style="float: left; padding-right: 10px;">
									<span
										style="padding-left: 10px; padding-top: 3px; font-family: Helvetica; color: #004c74; font-weight: bold; font-size: 14px; float: left;">Select
										Year To See Report :</span><span><select id="yrId"
										style="margin-left: 10px !important; color: #004c74; height: 23px; border: 1px solid #004c74;"
										class="input-sm">
									</select></span>
								</div>
							</li>
						</ul>
					</div -->
					<div class="page-bar col-sm-12" style="border:solid 0.5px #efefef;">
						<ul class="page-breadcrumb"
							style="padding-bottom: 5px !important;">
							<li id="crumgLiId" style="padding-left: 5px; padding-top: 3px;" >
							<div style="display:none;float: left; padding-right: 10px;">
									<span id="reportYrId" class="letterSpaceOR"
										style="padding-top:5px; font-family: Helvetica; color: #555; font-weight:bold; float: left;">Select
										Year To See Report:</span><span><select id="yrId"
										style="border-radius:5px !important;margin-left: 10px !important; color: #555; height: 25px; border: 1px solid #555;"
										class="input-sm">
									</select></span>
								</div> 
								 <span style="display: none;" id="tempData"></span> 
								<div style="float: left; padding-right: 10px;">
									<span id="custSegId"
										style="font-family: Helvetica; color: #004c74; font-weight: bold;  float: left;">Customer
										Segment:</span> <span><a id="example" tabindex="0" onclick="generateSegLog();"
										class="btn btn-sm btn-danger" role="button"
										data-toggle="popover" data-trigger="focus"
										
										style="background-color:#2e6da4;margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica;"></a></span>
								</div>
								
								<span style="display: none;" id="tempDataQual"></span>
								<div style="float: left; padding-right: 10px;display:none;">
									<span id="qualScrId"
										style="padding-top:5px;font-family: Helvetica; color: #004c74; font-weight: 600; float: left;">Qualification
										Score:</span> <span><a id="exampleQual" tabindex="0"
										class="btn btn-sm btn-danger" role="button"
										data-toggle="popover" data-trigger="focus"
										title="Qualification Score"
										style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica;"></a></span>
								</div>
								<div style="float: left; padding-right: 10px;display:none;">
									<span id="lastContactedDateId"
										style="padding-top:5px;font-family: Helvetica; color: #004c74; font-size: 14px; font-weight: bold; float: left;">Last Contacted Date:</span> <span id="lastContactedDateValueId" style="margin-top: -4px !important; margin-left: 10px; margin-bottom: 0px; border-radius: 50px !important; border: none; font-size: 14px; font-weight: bold; font-family: Helvetica; color: #004c74;"></span>
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<!-- END PAGE HEADER-->
				<!-- BEGIN DASHBOARD STATS -->
				<div class="row">
					
					<div class="col-lg-12" id="userDiv">
					<div class="portlet box green" style="display:none;border:1px solid #004c74;background-color:#004c74;" id="superId">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span class="letterSpaceOR">Users <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-down" id="showHideId0" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-placement="top" data-toggle="tooltip" title="Close Super User">
									</a>
									
								</div>
								<!-- <div id ='updateDateValueUser' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelUser' style="float:right;"><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;cursor:default;" role="button">Refreshed: </span></div> -->
				<div id="updateDateLabelUser" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
				<a id="addContactId" href="#" target="_blank" style="display:none;"><button type="button" class="btn3d btn btn-default btn-xs" style="float: right; margin: 11px; background-color: #fff; border: none;border-radius: 2px !important;">
				<span class="fa fa-hand-o-up"></span>  Add new contact to SFDC
				</button></a>
							</div>
							<div class="portlet-body" id="superContent" style="display: none;height:auto;">
						<div class="page-bar col-lg-12 col-sm-12 col-md-12"
							style="height: 16px; font-size: 14px; color: #333; font-family: Helvetica; font-weight: 600; letter-spacing: .5px;">
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
							style="height: 16px; font-size: 14px; color: #333; font-family: Helvetica; font-weight: 600; letter-spacing: .5px;">
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
							style="height: 16px; font-size: 14px; color: #333; font-family: Helvetica; font-weight: 600; letter-spacing: .5px;">
							This section contains a customer's last 1 year of order history.</div>
							<!-- <div style="background-color: gainsboro;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:40px;padding-top: 5px;" class="col-lg-12 col-sm-12">
			                <div class="col-lg-3 col-sm-3 col-md-3" style="padding-left:0px;" id="viewWrapper">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;width: auto;" class="col-lg-3 col-sm-3 input-sm">View</div><div class="col-lg-9 col-sm-8" id="yearSelId" style="padding-left:0px;"><input id="datepickerTEXT" class="col-lg-12 col-sm-12" type="text" value="" style="font-family:helveticaneuebold;font-size:14px;border:1px solid #004c74;border-radius:3px !important;"><select id="yearSel" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm"></select></div>
									<div class="demo">  <p>Date: </p>  </div>
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
					        </div>	 -->
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
				<div class="col-lg-6" style="padding-left:8px;">
						<!-- END PORTLET-->
					<div class="portlet box black" id="amId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpaceOR" id="AccountMgrId" style="font-size: 16px;font-weight: 600;color: #555;">REPRESENTATIVE DETAILS <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId6" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
							</div>
							<div class="portlet-body" id="amContent" style="height:100px;">
								 <div class="col-xs-12 text-left" id="managerInfo" style="color:#333;padding-right:0px;padding-left:5px;">
                                   	<!-- <div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Account Manager : </span><span id="mgrName" style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" class="letterSpaceOR">  </span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">EMail : </span> <span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold" id="mgrEmailId" class="letterSpaceOR"></span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Phone Number : </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="mgrPhoneNum" class="letterSpaceOR"></span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Inside Account Consultant : </span><span id="mgrNameIAC" style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" class="letterSpaceOR">  </span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">EMail : </span> <span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold" id="mgrEmailIdIAC" class="letterSpaceOR"></span></div>
									<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" class="large">Phone Number : </span><span style="font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold;" id="mgrPhoneNumIAC" class="letterSpaceOR"></span></div> -->
                                  </div>
                                  
								
							</div>
						</div>	
											
						<!-- chart data start -->
						<div class="portlet box green" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;" id="catId">
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
					</div>
					
					<div class="col-lg-6 customPad">
					<!-- Ytd Data Start -->
					<div class="portlet box green" id="ytId" style="display:none;border:1px solid #004c74;background-color:#004c74;">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span class="letterSpaceOR">Yearly Summary <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId7" style="background-color:#004c74;color:#fff; text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
							<div style="float:right">
								<!-- <div id ='updateDateValueYTDSum' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelYTDSum' style="float:right;"><span  class="" role="button" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;">Refreshed: </span></div> -->
				<div id="updateDateLabelYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div></div>
							<div class="portlet-body" id="ytContent" style="height:auto;padding-top:30px;">
                              <!--  <div class="col-xs-9 text-left" id="ytdSummary" >
                                  <div style="height:24px" class=""> <span style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" id="year0" class="large"> </span><span style="font-family:sans-serif !important;font-size:13px;color:#555;font-weight:bold" id="spend0">  </span></div>
								<div style="height:24px" class=""> <span style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" id="year1" class="large"> </span><span style="font-family:sans-serif !important;font-size:13px;color:#555;font-weight:bold" id="spend1">  </span></div>
								<div style="height:24px" class=""> <span style="font-family: Helvetica;font-size: 14px;font-weight:bold;color: #333;" id="year2" class="large"> </span><span style="font-family:sans-serif !important;font-size:13px;color:#555;font-weight:bold" id="spend2">  </span></div>	
                                                                    
                               </div> -->
                               <table class="table">
							    <tbody>
							      <tr style="background-color:#E3EFFB">
							        <td style="border: none;width:60%;padding-top: 20px;line-height: 1 !important;text-align:center;font-family: Helvetica !important;font-size: 13px;font-weight: 500;color: #555;padding-bottom: 20px;border-top-left-radius: 5px !important;border-bottom-left-radius: 5px !important;" id="year0"></td>
							        <td style="border: navajowhite;font-family:Helvetica !important;font-size: 14px;color: #555;font-weight: bolder;border-top-right-radius: 5px !important;border-bottom-right-radius: 5px !important;" id="spend0"></td>
							      </tr>
							      
								      <tr style="border-spacing: 0 10px !important;der-spacing: 200px !important;/* background-color:#E3EFFB; */">
								        <td style="height: 20px;border: none;" id="year0"></td>
								        <td style="border: none;" id="spend0"></td>
								      </tr>
							      
							      <tr style="background-color:#E6EEEC">
							        <td style="border: none;width:60%;padding-top: 20px;line-height: 1 !important;text-align:center;font-family: Helvetica !important;font-size: 13px;font-weight: 500;color: #555;padding-bottom: 20px;border-top-left-radius: 5px !important;border-bottom-left-radius: 5px !important;" id="year1"></td>
							        <td style="border: navajowhite;font-family:Helvetica !important;font-size: 14px;color: #555;font-weight: bolder;border-top-right-radius: 5px !important;border-bottom-right-radius: 5px !important;" id="spend1"></td>
							      </tr>
							      
								      <tr style="border-spacing: 0 10px !important;der-spacing: 200px !important;/* background-color:#E3EFFB; */">
								        <td style="height: 20px;border: none;" id="year0"></td>
								        <td style="border: none;" id="spend0"></td>
								      </tr>
							      
							      <tr style="background-color:#F8EAEB">
							        <td style="border: none;width:60%;padding-top: 20px;line-height: 1 !important;text-align:center;font-family: Helvetica !important;font-size: 13px;font-weight: 500;color: #555;padding-bottom: 20px;border-top-left-radius: 5px !important;border-bottom-left-radius: 5px !important;" id="year2"></td>
							        <td style="border: navajowhite;font-family:Helvetica !important;font-size: 14px;color: #555;font-weight: bolder;border-top-right-radius: 5px !important;border-bottom-right-radius: 5px !important;" id="spend2"></td>
							      </tr>
							      
								      <!-- <tr style="border-spacing: 0 10px !important;der-spacing: 200px !important;/* background-color:#E3EFFB; */">
								        <td style="height: 20px;border: none;" id="year0"></td>
								        <td style="border: none;" id="spend0"></td>
								      </tr> -->
							      
							      <!-- <tr style="background-color: #e3e7fb;">
                                    <td style="border: none;width:60%;padding-top: 20px;line-height: 1 !important;text-align:center;font-family: Helvetica !important;font-size: 13px;font-weight: 500;color: #555;padding-bottom: 20px;border-top-left-radius: 5px !important;border-bottom-left-radius: 5px !important;" id="year3"></td>
                                    <td style="border: navajowhite;font-family:Helvetica !important;font-size: 14px;color: #555;font-weight: bolder;border-top-right-radius: 5px !important;border-bottom-right-radius: 5px !important;" id="spend3"></td>
                                  </tr> -->
							    </tbody>
							  </table>
							</div>
						</div>
					<!-- Ytd Data End -->
						<!-- SA Feature Start  -->
						 <div class="portlet box green" id="saId" style="display:none;border:1px solid #004c74;background-color:#004c74;">
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
					<form role="form" action="" id="savingsReportForm" method="post" >
						<input name="custNum" id="custNum" type="hidden" value="${requestScope['custNo']}"/>
					</form>
						
						<!-- Savings Grid Start -->
					<div class="portlet box green" id="savingId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;">SAVINGS <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>
									<span style="padding-right: 5px !important; padding-left: 20px !Important;" id="excelId"><a style="color: #00cc00" data-toggle="tooltip" title="Download Savings Detail Report" data-placement="top" onclick="javascript:downloadSavingsReport();" href="javascript:;" data-original-title="Download Savings Detail Report"><span class="letterSpace" style="padding-right: 5px !important; padding-left: 5px !Important; padding-top: 2px; padding-bottom: 2px;"><i class="fa fa-download" style="font-size: 125%; border-radius: 10px;vertical-align:middle"></i></span></a></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId10" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Savings" title="">
									</a>
									
								</div>
							<div style="float:right">
								<!-- <div id ='updateDateValueYTDSum' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueSavings" style="font-family: arialmt;float:right;font-size: 10px;color: #4d4d4d;letter-spacing: .5px;padding-top: 13px;font-weight: 600;"><span >...</span></div>
				<!-- <div id ='updateDateLabelYTDSum' style="float:right;"><span  class="" role="button" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;">Refreshed: </span></div> -->
				<div id="updateDateLabelSavings" style="font-family: arialmt;float:right;font-size: 11px;color: #4d4d4d;letter-spacing: 1px;padding-top: 13px;font-weight: 600;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div></div>
							<div class="portlet-body" id="savingContent" style="height:auto;padding-top:20px;padding-bottom:0px;">
						<div class="row">
							<div class="col-sm-6 col-md-8" style="width: 60%;padding-right: 0px;">
								<div class="well" style="padding-left: 0px;padding-top: 0px;padding-right: 10px;background-color: #fff;border: none;">
									<div style="line-height: 1.8;text-align:right;">
										<div class="" style="text-align:left;font-weight: normal;padding-top: 3px;float: left;width: 77%;line-height: 14px;font-family: HelveticaNeueNormal;color: #434343;font-size: 14px;">Staples.com
											Current Annual Spend:</div>
										<span id="currEstAnnualSpend" class="" style="width: 10%;font-family: Helvetica;color: #434343;font-weight: bold;font-size: 13px;"></span>
									</div>
									<div style="text-align:right;">
										<div class="" style="text-align:left;font-weight: normal;float: left;width: 77%;line-height: 14px;font-family: HelveticaNeueNormal;color: #434343;font-size: 14px;padding-top: 2px;">Staples
											Business Advantage Potential Annual Spend:</div>
										<span id="proposedAnnualSpend" class="" style="width: 10%;font-family: Helvetica;color: #434343;font-weight: bold;font-size: 13px;"></span>
										<div>
											<hr style="height: 2px; margin-top: 20px; border-top: 2px solid #ddd;">
										</div>
									</div>
									<div style="text-align: right;line-height: 30px !important;">
										<div class="" style="text-align:left;;float: left;width: 77%;line-height: 14px;font-family: HelveticaNeueNormal;color: #cc0000;font-size:14px;padding-top: 2px;">
										Customer's Potential Annual Savings:</div>
										<span id="projectedPriceSaving" class="" style="width: 10%;font-family: Helvetica;color: #cc0000;font-weight: bold;font-size: 13px;">&nbsp;
											&nbsp;</span>
									</div>
									<div style="text-align: right;line-height: 30px !important;">
										<div class="" style="text-align:left;font-weight: normal;float: left;width: 77%;line-height: 14px;font-family: HelveticaNeueNormal;color: #434343;font-size: 14px;padding-top: 2px;">Projected
											Rebate Savings:</div>
										<span id="projectedRebateSaving" class="" style="width: 10%; font-family: Helvetica; color: #434343; font-weight: bold;">
											</span>
									</div>
									<div style="text-align: right;">
										<div class="" style="text-align:left;font-weight: normal;float: left;width: 77%;line-height: 14px;font-family: HelveticaNeueNormal;color: #434343;font-size: 14px;padding-top: 2px;">Premium
											Annual:</div>
										<span id="annualPremium" class="" style="text-decoration: underline; width: 10%; font-family: Helvetica; color: #434343; font-weight: bold;">
											</span>
										<div>
											<hr style="height: 2px; margin-top: 8px; border-top: 2px solid #ddd;">
										</div>
									</div>
									<div style="line-height: 1.8;text-align: right;">
										<div class="" style="text-align:left;font-weight: bold;padding-top: 2px;float: left;width: 77%;line-height: 14px;font-family: Helvetica;color: #cc0000;font-size: 14px;">Projected
											Total Savings:</div>
										<span id="totalSaving" class="" style="width: 10%; font-family: Helvetica; color: #cc0000; font-weight: bold;"></span>
									</div>
									<div style="margin-bottom: 30px; line-height: 1.8 !important;">
										<div class="" style="padding-top: 5px;font-weight: normal;float: left;width: 100%;line-height: 14px;font-family: HelveticaNeueNormal;color: #777;font-size: 13px;font-style: italic;">*Additional
											3% savings on orders over $250 for qualifying items</div>
									</div>
									<div style="">
										<a id="benefitId" class="navbar-brand collapsed" data-toggle="collapse" data-parent="#accordion-cat-11" style="padding:0px;color: #0092db;" href="#faq-benefits" aria-expanded="false">
										<div id="benefitTxt" class="" style="font-weight: bold;padding-top: 12px;float: left;width: 100%;line-height: 14px;font-family: Helvetica;color: #0092db;font-size: 14px;">Show benefits comparison</div>
                                        </a>
									</div>
								</div>
							</div>
							<div class="col-sm-6 col-md-4" style="padding-left: 8px; width: 38%; padding-right: 5px; border: navajowhite;">

								<div class="well" style="border: none;padding: 10px;background-color: #f2f4f8;padding-right: 5px;padding-bottom:0px;">
									<div style="font-size: 13px;padding: 2px 0px 8px 0px;font-family: Helvetica;">Selected
										Benefits:</div>
									<table>
										<tbody>
											<tr>
												<td><i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Assurance
													of a committed account manager</td>
											</tr>
											<tr>
												<td style=""><i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Insight from industry & product experts</td>
											</tr>
											<tr>
												<td><span class="fa fa-triangle" style="font-family: -webkit-pictograph; position: absolute; background-color: #f2f4f8; margin-left: -20px; color: #f2f4f8; height: 20px; transform: rotate(-45deg); width: 10%; margin-top: 1%;"></span>
													<i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Security
													of customized pricing</td>
											</tr>
											<tr>
												<td><i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Functionality
													of a website built for business</td>
											</tr>

											<tr>
												<td><i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Total
													value of a personalized business supply program</td>
											</tr>
											<tr>
												<td><i class="fa fa-check" style="font-size: 95%;"></i></td>
												<td style="line-height: 1.4;padding-left: 1px;font-family: arialmt;color: #434343;font-size: 13px;">Savings
													of up to 20% off</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>

						</div>

						<div class="row" style="">
							<div id="faq-benefits" class="panel-collapse collapse"
								aria-expanded="false" style="">
								<div class="panel-body" style="padding-bottom:0px;">
								<div class="well profile" style="border: none;min-height: auto;padding-bottom: 0px;padding-top: 0px;width: 100%;padding-left: 0px;padding-right: 0px;font-size: 16px;font-family: Helvetica;margin-bottom:0px;">
									<table id="gitBuildsCat" class="table table-bordered" style="border-color: #ccc;border: 1px solid #ccc;">
										<thead>
											<tr id="subheadCat" style="border-bottom: 1px solid #ddd;">

												<th width="60%" class="text-center" style=""></th>
												<th class="text-center" style="">STAPLES</th>
												<th class="text-center" style="">STAPLES BUSINESS
													ADVANTAGE</th>
											</tr>
										</thead>
										<tbody>
											
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Dependability of fast, free delivery</td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											  </tr>
											<tr>
											    <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Convenience of a single monthly invoice</td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Flexibility of buying online, in-store or on your phone</td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Efficiency of one single source for all your products</td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Assurance of a committed account manager</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Insight from industry & product experts</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Security of customized pricing</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Functionality of a website built for business</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Savings of up to 20% off</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											</tr>
											<tr>
											  <td style="width: 65%;background-color: #fff;color: #434343;font-size: 13px;font-family: helveticaNeueNormal;">
											  Total value of a personalized business supply program</td>
											<td style="text-align: center; background-color: #fff;"></td>
											<td style="text-align: center; background-color: #fff;"><i class="fa fa-check" style="color: #4d4d4d"></i></td>
											  </tr>
											</tbody>
									</table>
								</div>
								</div>
							</div>
						</div>
					</div>
						</div>
						<!-- Savings grid Ends -->
						
						
						<!-- Cat Pen Grid Start -->
					<div class="portlet box green" id="catPenId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;">CATEGORY PENETRATION <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId11" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Category Penetration" title="">
									</a>
									
								</div>
							<div style="float:right">
								<!-- <div id ='updateDateValueYTDSum' style="float:right;" ><span  class="" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;" role="button"></span></div> -->
								<div id="updateDateValueYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelYTDSum' style="float:right;"><span  class="" role="button" style="border:1px solid #004c74;background-color:#004c74;color:#fff;cursor:default;">Refreshed: </span></div> -->
				<div id="updateDateLabelYTDSum" style="font-family: Helvetica; float:right; font-size: 10px; color: #fff; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
							</div></div>
					<div class="panel-body" id="catPenContent"	style="padding-left: 20px; padding-top: 15px; padding-bottom: 0px; padding-right: 20px;">
						<div class="well profile"
							style="background-color:#fff;border: none; min-height: auto; padding-bottom: 0px; padding-top: 0px; width: 100%; padding-left: 0px; padding-right: 0px; font-size: 16px; font-family: Helvetica; margin-bottom: -5px;">
							<!-- <table id="gitBuildsCat" class="table table-bordered"
								style="border-color: #ccc; border: 1px solid #ccc;">
								<thead style="display: none;">
									<tr id="subheadCat" style="border-bottom: 1px solid #ddd;">

										<th width="60%" class="text-center" style=""></th>
										<th class="text-center" style="">STAPLES</th>

									</tr>
								</thead>
								<tbody>

									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th supplyColor"	style="color: #dd1d1d !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #dd1d1d;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">
												office supplies</span>
										</td>
										<td style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-check" style="color: #4cae4c !important; vertical-align: inherit; border-radius: 400px; border: 2px solid #4cae4c; padding: 2px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>
									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th inkColor"	style="color: #52c5d0 !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #52c5d0;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Ink
												& Toner</span>
										</td>
										<td style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-check" style="color: #4cae4c !important; vertical-align: inherit; border-radius: 400px; border: 2px solid #4cae4c; padding: 2px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>
									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px">
											<i class="fa fa-th facilityColor"	style="color: #2d435e !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #2d435e;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Facilities</span>
										</td>
										<td	style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px;">
											<i class="fa fa-check" style="color: #4cae4c !important; vertical-align: inherit; border-radius: 400px; border: 2px solid #4cae4c; padding: 2px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>
									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th paperColor"	style="color: #ff9a2c !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #ff9a2c;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Paper</span>
										</td>
										<td	style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-check" style="color: #4cae4c !important; vertical-align: inherit; border-radius: 400px; border: 2px solid #4cae4c; padding: 2px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>
									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th techColor"	style="color: #fcd78d !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #fcd78d;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Technology</span>
										</td>
										<td	style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-check" style="color: #4cae4c !important; vertical-align: inherit; border-radius: 400px; border: 2px solid #4cae4c; padding: 2px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>

									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th furColor"	style="color: #9ce2cf !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #9ce2cf;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Furniture</span>
										</td>
										<td	style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-close" style="color: red !important; vertical-align: inherit; border-radius: 400px; border: 2px solid red; padding: 3px; padding-right: 4px; padding-left: 4px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is purchasing this category</span>
										</td>
									</tr>
									<tr>
										<td	style="width: 30%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: 10px; padding-right: 0px;">
											<i class="fa fa-th mailColor"	style="color: #c8cbd0 !important; font-size: 100%; padding: 2px; vertical-align: inherit; background-color: #c8cbd0;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">Mail
												& Ship</span>
										</td>
										<td	style="width: 70%; background-color: #fff; color: #434343; font-size: 13px; font-family: helveticaNeueNormal; padding: px;">
											<i class="fa fa-close"	style="color: red !important; vertical-align: inherit; border-radius: 400px; border: 2px solid red; padding: 3px; padding-right: 4px; padding-left: 4px;"></i>
											<span style="color: #434343; padding-left: 6px; vertical-align: bottom; font-family: HelveticaNeueNormal;">customer
												is NOT purchasing this category</span>
										</td>
									</tr>

								</tbody>
							</table>-->
							<table id="gitBuildsCat" class="table" style="float: left;width: 60%;border-color: #ccc;margin-bottom:13px;">
								<thead style="display: none;">
								<tr id="subheadCat" style="border-bottom: 1px solid #ddd;">
								
								<th width="60%" class="text-center" style=""></th>
								<th class="text-center" style="">STAPLES</th>
								
								  </tr>
								</thead>
								<tbody>
								<tr>
								  <td style="width: 30%;background-color: #52c5d0;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Ink & Toner</span>
								</td>
								<td id="InkToner" class="catClass" style="width: 20%;background-color:#fff;">
								<!-- <img src="./resources/img/Check.png" class="img-circle"> -->
								NA
								</td>
								</tr>
								<tr>
								  <td style="width: 30%;background-color: #fcd78d;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Technology</span>
								</td>
								<td id="tech" class="catClass" style="width: 20%;background-color:#fff;">
								<!-- <img src="./resources/img/Check.png" class="img-circle"> -->
								NA
								</td>
								</tr>
								<tr>
								  <td style="width: 30%;background-color: #9ce2cf;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Furniture</span>
								</td>
								<td id="furniture" class="catClass" style="background-color:#fff;">
								<!-- <img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="Customer not using this feature"> -->
								NA
								</td>
								</tr>
								  <tr>
								    <td style="width: 30%;background-color: #dd1d1d;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">
								    Office Supplies</span>
								</td>
								<td id="offSupp" class="catClass" style="width: 20%;background-color:#fff;">
								<!-- <img src="./resources/img/Check.png" class="img-circle"> -->
								NA
								</td>
								</tr>
								<tr>
								    <td style="width: 30%;background-color:darkseagreen;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">
								    Promo</span>
								</td>
								<td id="promo" class="catClass" style="width: 20%;background-color: #fff;">
								NA
								</td>
								</tr>
								<tr>
								  <td style="width: 30%;background-color: #ff9a2c;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Paper</span>
								</td>
								<td id="paper" class="catClass" style="width: 20%;background-color:#fff;">
								<!-- <img src="./resources/img/Check.png" class="img-circle"> -->
								NA
								</td>
								</tr>
								<tr>
								  <td style="width: 30%;background-color: #c8cbd0;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Mail & Ship</span>
								</td>
								<td id="mailship" class="catClass" style="background-color: #fff;">
								<!-- <img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="Customer not using this feature"> -->
								NA
								</td>
								    </tr>
								<tr>
								  <td style="width: 30%;background-color: #2d435e;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">Facilities</span>
								</td>
								<td id="facility" class="catClass" style="width: 20%;background-color: #fff;">
								<!-- <img src="./resources/img/info.jpg" class="img-circle" style="background-color: #fff;padding: 1px;"> -->
								NA
								</td>
								</tr>
								<tr>
								  <td style="width: 30%;background-color: #5F9EA0;font-size: 13px;font-family: helvetica;text-align: center;font-weight:bold;">
								
								<span style="color: #fff;vertical-align: bottom;font-family: Helvetica;">All Other Products</span>
								</td>
								<td id="allproduct" class="catClass" style="background-color:#fff;">
								<!-- <img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="Customer not using this feature"> -->
								NA
								</td>
								</tr>
								  </tbody>
							</table>
								<div class="well" style="margin-bottom: 5px;width: 35%;border: none;padding: 10px;background-color: #f2f4f8;padding-right: 5px;float: right;padding-top: 15px;padding-bottom: 15px;">
								
								<table>
								  <tbody>
								    <tr>
								      <td><img src="./resources/img/Check.png" class="img-circle" style=" max-height: 55px; "></td>
								<td style="padding-bottom: 5px;line-height: 1.4;padding-left: 1px;font-family: Helvetica;color: #555;font-size: 12px;font-weight: 600;">&gt; 80% of the average Mid-Market Customer spend</td>
								</tr>
								
								<tr>
								  <td><img src="./resources/img/info.jpg" class="img-circle" style="max-height: 55px; background-color: gold; padding: 0px; "></td>
								<td style="padding-bottom: 1px;line-height: 1.4;padding-left: 1px;font-family: Helvetica;color: #555;font-size: 12px;font-weight: 600;">Between 50% and 80% of the average Mid-Market Customer spend</td>
								</tr>
								<tr>
								  <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="Customer not using this feature" style=" max-height: 30px;"></td>
								<td style="padding-bottom: 7px;line-height: 1.4;padding-left: 1px;font-family: Helvetica;color: #555;font-size: 12px;font-weight: 600;">&lt; 50% of the average Mid-Market Customer spend</td>
								      </tr>
								    </tbody>
								  </table>
								</div>
						</div>
					</div>
				</div>
						<!-- Category Penetration Ends -->
					</div>
				</div>
				<!-- END DASHBOARD STATS -->
				<div class="clearfix">
				</div>
				
				<!-- <div class="row">
				  <div class="col-md-6">
						
						</div>  
				</div>
				
				<div class="clearfix">
				</div> -->
				<!-- <div class="row">
				<div class="portlet box green" id="catPenId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;">
									<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;">CATEGORY PENETRATION <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
								</div>
								
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId7" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
			   </div> -->
				<div class="row">
					
					<div class="col-lg-12">
						<!-- BEGIN PORTLET-->
						<div class="portlet box green" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;" id="mnthId">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" style="color: #4d4d4d;font-family:Helvetica !important;" id="mnthTitle">
									  
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId3" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
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
				<div class="row">
					<div class="col-lg-12">
							<div class="portlet box green" id="mtaId" style="border: 1px solid #efefef !important;background-color: #fff;border-color: #fff;">
							<div class="portlet-title" style="border-bottom: 1px solid #aaa;background-color: #fff;">
								<div class="caption" id="monthTitle" style="color: #4d4d4d;font-family:Helvetica !important;">
									
								</div>
								<div class="tools">
									<a class="fa fa-chevron-up" id="showHideId5" style="background-color: #fff;color: #4d4d4d;text-decoration: none;" href="javascript:;" data-original-title="Open Super User Data" title="">
									</a>
									
								</div>
							</div>
							<div class="portlet-body" id="mtaContent" style="">
						<div class="">
							<button type="button" class="btn btn-info btn-sm collapse ORrow retailCls"
								style="float:left;border: none; background-color: #598b9a; padding:2px 12px 2px 12px;cursor:default;">Retail</button>
							<button type="button" class="btn btn-info btn-sm collapse ORrow onlineCls"
								style="float:left;border: none; background-color: #808080; padding:2px 12px 2px 12px;cursor:default;">Staples.com</button>
							<div id="divChkId" class="checkbox" style="margin-top: 5px; float: right;">
								
							</div>
						</div>
						<div id="tableId" class="dataTable_wrapper">
                                <table class="table dataTable no-footer table-striped" id="dataTables-example1">
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
				<div class="clearfix">
				</div>
				<div id="printOnly" class="" style="border:solid 0.5px #efefef;">
						Product assortment varies between Staples.com and StaplesAdvantage.com; please inquire of your account manager for more information.
				</div>
			    <div class="clearfix">
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
									<!-- <h3 class="list-heading">Staff</h3>
									<ul class="media-list list-items">
										<li class="media">
											<div class="media-status">
												<span class="badge badge-success">8</span>
											</div>
											<img class="media-object" src="../../assets/admin/layout/img/avatar3.jpg" alt="...">
											<div class="media-body">
												<h4 class="media-heading">Bob Nilson</h4>
												<div class="media-heading-sub">
													 Project Manager
												</div>
											</div>
										</li>
										<li class="media">
											<img class="media-object" src="../../assets/admin/layout/img/avatar1.jpg" alt="...">
											<div class="media-body">
												<h4 class="media-heading">Nick Larson</h4>
												<div class="media-heading-sub">
													 Art Director
												</div>
											</div>
										</li>
										<li class="media">
											<div class="media-status">
												<span class="badge badge-danger">3</span>
											</div>
											<img class="media-object" src="../../assets/admin/layout/img/avatar4.jpg" alt="...">
											<div class="media-body">
												<h4 class="media-heading">Deon Hubert</h4>
												<div class="media-heading-sub">
													 CTO
												</div>
											</div>
										</li>
										<li class="media">
											<img class="media-object" src="../../assets/admin/layout/img/avatar2.jpg" alt="...">
											<div class="media-body">
												<h4 class="media-heading">Ella Wong</h4>
												<div class="media-heading-sub">
													 CEO
												</div>
											</div>
										</li>
									</ul> -->
									<!-- <h3 class="list-heading">Customers</h3> -->
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
									 <!-- <div class="pages" ></div> -->
									 <!-- <ul class="pagination" id="myPager"></ul> -->
									 <!-- <ul class="" id="">
									     <li id="totalId"></li>
									 </ul> -->
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
                                       <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                            
                                        </tr>
                                        <tr class="odd gradeX">
                                             <td>1002</td>
                                            <td>Shipped</td>
                                            <td>3000</td>
                                        </tr>
										<tr class="odd gradeX">
                                             <td>1003</td>
                                            <td>Delivered</td>
                                            <td>2230</td>
                                        </tr>
										<tr class="odd gradeX">
                                             <td>1004</td>
                                            <td>Shipped</td>
                                            <td>1908</td>
                                        </tr>
										
										<tr class="odd gradeX">
                                             <td>1008</td>
                                            <td>Shipped</td>
                                            <td>7899</td>
                                        </tr>
										<tr class="odd gradeX">
                                             <td>1009</td>
                                            <td>Delivered</td>
                                            <td>5566</td>
                                        </tr>
										<tr class="odd gradeX">
                                             <td>1010</td>
                                            <td>Shipped</td>
                                            <td>3777</td>
                                       </tr>
										
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
                                       <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                         <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                          <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
										
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
                                       
                                          <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             
                                        </tr>
										
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
                                       <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                         <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
                                          <tr class="odd gradeX">
                                            <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                             <td>1001</td>
                                            <td>Shipped</td>
                                            <td>2000</td>
                                        </tr>
										
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
	<input type="hidden" name="sfdcAppBaseUrl" id="sfdcAppBaseUrl" value="${requestScope['SFDC_APP_BASE_URL']}" />
		
		<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
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
    var scripts = ['app.js','jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','bootstrap-dropdown.js','dataTables.bootstrap.min.js','slimscroll.js','blockui.min.js','bootstrap-switch.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js','vmap.js','vmap.russia.js','vmap.world.js','vmap.europe.js','vmap.germany.js','vmap.usa.js','sampledata.js','flot.min.js','float.resize.js','flot.category.js','pulsate.js','moment.js','datepicker.js','metronic.js','layout.js','quick-sidebar.js','demo.js','index.js','task.js','amcharts.js','serial.js','light.js','RetailOnline_Customer.js','editor.js'];

    // Loop through each script name and append our new version number
    scripts.map(function(script){
        var currentScriptVer =${initParam.helios_ver} /* incrementScriptVer(script); */
       
            document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
        
    });
})();
</script>
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
   Demo.init(); // init demo features 
   QuickSidebar.init(); // init quick sidebar
   Index.init();   
   Index.initDashboardDaterange();
   Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
  // $("ul.media-list,list-items").quickPager();
  //$("#menuItemId0").css("color","#444");
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
	   //$("#bc0").toggle();
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
	   //$("#bc0").toggle();
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
	   //$("#bc0").toggle();
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
	$("#savingContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId10").prop('class','fa fa-chevron-up');
	    	$("#savingContent").css('display','block');
	   } 
});
$("#showHideId11").click(function(){ 
	 var cls=$("#showHideId11").attr('class');
	    if(cls=='fa fa-chevron-up'){
	$("#showHideId11").prop('class','fa fa-chevron-down');
	$("#catPenContent").css('display','none');
	   }
	    else if(cls=='fa fa-chevron-down'){
	   // $("#catId").css('display','none');
	    	$("#showHideId11").prop('class','fa fa-chevron-up');
	    	$("#catPenContent").css('display','block');
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
		 hideMMFeatures();
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
			//$(".canvasjs-chart-canvas").removeAttr("style");
			//alert("chrt"+chrt);
			//chrt.destroy();
			/* if(($(window).width()<=1024) && ($(window).width() > 768)){
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
			}else{ 
				$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
			} */
			hideMMFeatures();
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
			$(".canvasjs-chart-canvas").attr("style","width: 101% !important;height: 400px;position: absolute;");
			//chrt.render();
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
				 hideMMFeatures();
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
				//$(".canvasjs-chart-canvas").removeAttr("style");
				if(($(window).width()<=1024) && ($(window).width() > 768)){
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
				}else{ 
					$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
				}
				hideMMFeatures();
				/* $("#slideIcon").removeClass('fa fa-chevron-circle-right');
				$("#slideIcon").addClass('fa fa-chevron-circle-left'); */
			}else{
				$("#ulPlaySec").removeClass("page-sidebar-menu-closed");
				$("#ulIamIdSec").removeClass("page-sidebar-menu-closed");
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
				//$(".canvasjs-chart-canvas").attr("style","width:100% !important;");
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
					 hideMMFeatures();
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
		logUserActivity(2019, 'Print Lead Customer Details');
		
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
	logUserActivity(2018, 'View Lead Customer Details To Do List');
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
 
