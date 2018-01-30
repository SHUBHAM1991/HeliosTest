
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<!-- <link href="./resources/stylesheet/bootstrap-combine.css" rel="stylesheet" type="text/css"> -->
<link href="./resources/stylesheet/commonStyles.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/recommendation.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/jquery-ui-ui.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./resources/stylesheet/common-print.css?helios_version=${initParam.helios_ver}" type="text/css" media="print" >
 <link href="./resources/stylesheet/stylesV2.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/stylesV3.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="./resources/javascript/canvas.js?helios_version=${initParam.helios_ver}"></script>
<script type="text/javascript" src="./resources/javascript/common.js?helios_version=${initParam.helios_ver}"></script>

 
<script type="text/javascript">

 /* window.onload = function () {}
 */
  </script>
  <script type="text/javascript" src="./resources/javascript/canvas.js?helios_version=${initParam.helios_ver}"></script>
  <style type="text/css">
.ui-datepicker-calendar {
	display: none;
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

#faqIcon {
	display: none;
}

.popover-content {
	font-family: Helvetica !important;
	font-weight: bold !important;
	box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.2) !important
}

#printOnly {
	display: none;
}

@media print {
	#printOnly {
	font-size:14px;
		display: block;
	}
}
</style>
</head>
		<!-- BEGIN CONTENT -->
		<form role="form" action="" id="customerForm" method="post" style="margin:0px; padding: 0px;">
		<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['custNo']}"/>
		<input name="accId" id="accId" type="hidden" value="${requestScope['accId']}"/>
		<input type="hidden" id="LoggedInUserID" name="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
		<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
		<input class="form-control"  name="iStart" id="iStart" type="hidden" value="${requestScope['iStart']}" />
		<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
		<input class="form-control"  name="sliderSubPlaysItem" id="sliderSubPlaysItem" type="hidden" value="${requestScope['sliderSubPlaysItem']}" />
		<input class="form-control"  name="onlineRetail" id="onlineRetail" type="hidden" value="${requestScope['onlineRetail']}" />
		</form>
		<form action="./home_cust_profiles" id="customerForm1" method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden" value="${requestScope['accId']}"/>
	<input name="filterBy" id="filterBy" type="hidden" value="${requestScope['filterBy']}"/>
<input class="form-control"  name="subPlayParams" id="subPlayParams" type="hidden" value="${requestScope['subPlayParams']}" />
	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=request.getParameter("LoggedInUserID")%>" />
	<input class="form-control"  name="selectedSegScore" id="selectedSegScore" type="hidden" value="${requestScope['selectedSegScore']}" />
                    <input class="form-control"  name="selectedQualScore" id="selectedQualScore" type="hidden" value="${requestScope['selectedQualScore']}" />
	</form>

<form action="./cp_online_retail" id="customerFormOnlineRetail"
	method="post">
	<input name="reqCustNum" id="reqCustNum" type="hidden"
		value="${requestScope['accId']}" /> <input name="filterBy"
		id="filterBy" type="hidden" value="${requestScope['filterBy']}" /> <input
		class="form-control" name="subPlayParams" id="subPlayParams"
		type="hidden" value="${requestScope['subPlayParams']}" /> <input
		type="hidden" name="LoggedInUserID" id="LoggedInUserID"
		value="<%=request.getParameter("LoggedInUserID")%>" /> <input
		class="form-control" name="selectedSegScore" id="selectedSegScore"
		type="hidden" value="${requestScope['selectedSegScore']}" /> <input
		class="form-control" name="selectedQualScore" id="selectedQualScore"
		type="hidden" value="${requestScope['selectedQualScore']}" />
</form>
<div  class="page-content-wrapper" style="background-color:#1d2939;">
		 <div class="printableDashboard page-content" id="recommendations-page-content">
		 <!-- <button   onclick="location.href='./home_cust_profiles?reqCustNum=${requestScope['accId']}&filterBy=${requestScope['filterBy']}'" type="button" style="float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="backButton" >Back to Call List</button>  -->
		 <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printRecommendationsBtn" >Print</button>
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
				
		 <div class="row">
					
					<div class="col-lg-12" id="userDiv">
					<div class="portlet box green" style="display:none;border:1px solid #004c74;background-color:#004c74;" id="superId">
							<div class="portlet-title">
								<div class="caption" style="font-family:Helvetica !important;">
									<span>Users <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>  
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
							This section contains a customer's last 1 year of order history.</div>
							<!-- <div style="background-color: gainsboro;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:auto;padding-top: 5px;" class="col-lg-12 col-sm-12">
			                <div class="col-lg-3 col-sm-3 col-md-3" style="padding-left:0px;" id="viewWrapper">                                           
									<div style="padding-left:0px;font-size:16px;color:#004c74;font-weight:bold;width: auto;" class="col-lg-3 col-sm-3 input-sm">View</div><div class="col-lg-9 col-sm-8" id="yearSelId" style="padding-left:0px;"><input id="datepickerTEXT" class="col-lg-12 col-sm-12" type="text" value="" style="font-family:helveticaneuebold;font-size:13px;border:1px solid #004c74;border-radius:3px !important;"><select id="yearSel" style="color:#004c74;border:1px solid #004c74;" class="form-control input-sm"></select></div>
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
		  <!-- <div class="row">
		    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			 <div class="offer offer-success">
			  <div style="float:left;"><a href="javascript:void()" class="btn twitter btn-block" role="button">Recently viewed but not bought</a></div>
				<div class="shape">
					<div class="shape-text">
						top								
					</div>
				</div>
				<div class="offer-content" style="height:390px;" id="BnBTabId">
					Slider-1 Strat
	        
				</div>
			</div>
		</div>
		</div> -->
	<!-- Slider-1 Row End -->
	<div class="clearfix">
	</div>
	<!-- Slider-2 Row Strat -->
	     <div class="row" id="reorderPane">
		    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-bottom:8px;">
			 <div class="offer offer-radius offer-primary" >
			  <div style="float:left;"><a href="javascript:void()" class="btn twitterprimary btn-block" role="button">TOP RECOMMENDED ITEMS <font size="1" > (MAX 15 ITEMS)</font> </a></div>
				
				<div class="shape">
				
					<div class="shape-text">
						<!-- top -->								
					</div>
				</div>
				<!-- <div id ='updateDateValueReorder' style="float:right;" ><span  class="" role="button" style="padding: 5px 5px;line-height: 1.5;cursor:default;font-size: 12px;color: #428bca;font-weight: bold;"></span></div> -->
				<div id="updateDateValueReorder" style="font-family: Helvetica; float:right; font-size: 10px; color: #428bca; letter-spacing: .5px;padding-top:11px;"><span ></span></div>
				<!-- <div id ='updateDateLabelReorder' style="float:right;"><span  class="" role="button" style="padding: 5px 5px;line-height: 1.5;cursor:default;font-size: 12px;color: #428bca;font-weight: bold;">Refreshed: </span></div> -->
				<div id="updateDateLabelReorder" style="font-family: Helvetica; float:right; font-size: 10px; color: #428bca; letter-spacing: .5px;padding-top:11px;"><span style="padding-right:3px;">Refreshed: </span></div>
				<div class="offer-content" style="padding-left:30px; margin-bottom:9px" id="ReorderRecommTabId">
	        
				</div>
			</div>
		</div>
		</div>
		<!-- Slider-2 Row End -->
		
		<div class="clearfix">
	    </div>
	    
		<!-- Slider-3 Row Strat -->
	     		<!--3 Slider Row End -->
		
		<div class="clearfix">
	    </div>
	    
	    <!-- Slider 4 Row Start -->
	     <!-- Removing Slider
	     <div class="row">
		    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
			 <div class="offer offer-warning">
			  <div style="float:left;"><a href="javascript:void()" class="btn twitterwarning btn-block" role="button">Class Recommendations/ Play Recommendations</a></div>
				<div class="shape">
					<div class="shape-text">
														
					</div>
				</div>
				<div class="offer-content" style="height:390px;" id="PlayRecommTabId">
	                
				</div>
			</div>
		</div>
		</div>
		 Removing Slider -->
		<!--Slider-4 Row End -->	
		<div class="clearfix">
		</div>
		<div id="printOnly" class="" >
				Product assortment varies between Staples.com and StaplesAdvantage.com; please inquire of your account manager for more information.
		</div>
	    <div class="clearfix">
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
									 <ul class="pagination" id="myPager"></ul>
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
	<div class="modal fade" id="ship-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">Ship To Info<span id="ShipToLocId"></span></h4>
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
          <h4 class="modal-title" style="color:#fff;" id="shiptotitle"> <span id="shplocId"></span></h4>
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
	<!-- dialog start -->
	<div class="modal fade" id="super-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:30px;height:auto;">
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
                                        </tr>s
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
   <%-- <jsp:include page="SuperUserInfo.jsp"></jsp:include> --%> 
  <!-- order details info start -->
  
  <div class="modal fade" id="super-info-dialog" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:20px;height:auto;">
      <div class="modal-content" id="super-info-content">
        <div class="modal-header" style="background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">User Info - <span id ="uName"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printUserInfoBtn" >Print</button>
        <div class="">
		<!-- <span style="font-size:16px;font-weight:bold;" >Search String</span> -->
		</div>
        <div style="margin-bottom:10px;border-bottom:1px solid red;" class="">
			<table id="searchStringId" class="">
				<tr class="">
				 <td style="color:red;"></td>
				</tr>
			</table>
									
		</div>
		<div class="col-md-12 margin-bottom-10">
				<!-- BEGIN WIDGET TAB -->
				<div class="widget-bg-color-white widget-tab rounded-3">
					<ul class="nav nav-tabs">
						<li class="" id="li1">
							<a href="#tab_1_1" data-toggle="tab" aria-expanded="true" id="a1">SA.COM ACTIVITY </a>
						</li>
						<!-- <li class="" id="li2">
							<a href="#tab_1_2" data-toggle="tab" aria-expanded="false" id="a2"> OUR RECOMMENDATIONS </a>
						</li> -->
						<li class="" id="li3">
							<a href="#tab_1_3" data-toggle="tab" aria-expanded="false" id="a3" >STAPLES.COM ACTIVITY </a>
						</li>
						<!-- <li class="">
							<a href="#tab_1_4" data-toggle="tab" aria-expanded="false"> Others </a>
						</li> -->
					</ul>
					<div class="slimScrollDiv" style="position: relative; overflow: auto; width: auto; height: 425px;"><div class="tab-content scroller" style="height: 425px; overflow: hidden; width: auto;" data-always-visible="1" data-handle-color="#D7DCE2" data-initialized="1">
						<div class="tab-pane fade active in" id="tab_1_1">
							<!-- crausel data start -->
							<!-- <div id="amazon_scroller1" class="amazon_scroller">
							<div class="amazon_scroller_mask">
							<ul>
							  <li>
								<a href="http://www.amazon.com/gp/product/1935182323/ref=as_li_tf_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=1935182323" title="jQuery in Action, Second Edition" target="_blank">
								   <img src="http://www.staplesadvantage.com/is/image/Staples/m000222012_sc7" width="124" height="160" alt="jQuery in Action, Second Edition"/>
								   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							        </div>
								</a>
								<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="/David-Flanagan/e/B000APEZR4/ref=pd_sim_14_bl_1/189-8437559-3078824?_encoding=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">David Flanagan</a></div>
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div> 
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0596159773/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0596159773" title="jQuery Cookbook: Solutions & Examples for jQuery Developers (Animal Guide)" target="_blank">
							  <img src="http://www.staplesadvantage.com/is/image/Staples/m000222028_sc7" width="124" height="160" alt="jQuery Cookbook: Solutions & Examples for jQuery Developers (Animal Guide) [Paperback]"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>  
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0980576857/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0980576857" title="jQuery: Novice to Ninja" target="_blank">
							  <img src="http://www.staples-3p.com/s7/is/image/Staples/s0958772_sc7" width="124" height="160" alt="jQuery: Novice to Ninja"/>
							    <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0321430840/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321430840" title="HTML, XHTML, and CSS, Sixth Edition" target="_blank">
							   <img src="http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1" width="124" height="160" alt="HTML, XHTML, and CSS, Sixth Edition"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							 </a>  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							 
							</li>
							 
							<li>
							 <a href="http://www.amazon.com/gp/product/0321344758/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321344758" title="Don't Make Me Think: A Common Sense Approach to Web Usability, 2nd Edition" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0321344758&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="Don't Make Me Think: A Common Sense Approach to Web Usability, 2nd Edition"/>
							  <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							  </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/059610197X/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=059610197X" title="Head First HTML with CSS & XHTML" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=059610197X&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="Head First HTML with CSS & XHTML"/>
							  <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							  </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							
							<li>
							  <a href="http://www.amazon.com/gp/product/0596517742/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0596517742" title="JavaScript: The Good Parts" target="_blank">
							   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0596517742&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="JavaScript: The Good Parts"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							  </a>
							  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							  <a href="http://www.amazon.com/gp/product/0321564081/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321564081" title="JavaScript and Ajax for the Web: Visual QuickStart Guide" target="_blank">
							   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0321564081&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="JavaScript and Ajax for the Web: Visual QuickStart Guide"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							  </a>
							  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							</ul>
							</div>
						<ul class="amazon_scroller_nav">
						<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						</ul>
						<div style="clear: both"></div>
						</div>-->
 
							<!-- crausel data end -->
						</div>
						<!-- <div class="tab-pane fade" id="tab_1_2"> -->
							<!-- crausel data start -->
							<!-- <div id="amazon_scroller2" class="amazon_scroller">
							<div id="recommId" class="amazon_scroller_mask">

							</div>
						<ul class="amazon_scroller_nav">
						<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						</ul>
						<div style="clear: both"></div>
						</div> -->
 
							<!-- crausel data end -->
						<!-- </div> -->
						<div class="tab-pane fade" id="tab_1_3">
							<!-- crausel data start -->
							<!-- <div id="amazon_scroller3" class="amazon_scroller">
							<div class="amazon_scroller_mask">
							<ul>
							  <li>
								<a href="http://www.amazon.com/gp/product/1935182323/ref=as_li_tf_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=1935182323" title="jQuery in Action, Second Edition" target="_blank">
								   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=1935182323&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="jQuery in Action, Second Edition"/>
								   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							        </div>
								</a>
								<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="/David-Flanagan/e/B000APEZR4/ref=pd_sim_14_bl_1/189-8437559-3078824?_encoding=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">David Flanagan</a></div>
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div> 
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0596159773/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0596159773" title="jQuery Cookbook: Solutions & Examples for jQuery Developers (Animal Guide)" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0596159773&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="jQuery Cookbook: Solutions & Examples for jQuery Developers (Animal Guide) [Paperback]"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>  
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0980576857/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0980576857" title="jQuery: Novice to Ninja" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0980576857&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="jQuery: Novice to Ninja"/>
							    <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/0321430840/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321430840" title="HTML, XHTML, and CSS, Sixth Edition" target="_blank">
							   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0321430840&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="HTML, XHTML, and CSS, Sixth Edition"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							 </a>  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							 
							</li>
							 
							<li>
							 <a href="http://www.amazon.com/gp/product/0321344758/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321344758" title="Don't Make Me Think: A Common Sense Approach to Web Usability, 2nd Edition" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0321344758&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="124" height="160" alt="Don't Make Me Think: A Common Sense Approach to Web Usability, 2nd Edition"/>
							  <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							  </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							 <a href="http://www.amazon.com/gp/product/059610197X/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=059610197X" title="Head First HTML with CSS & XHTML" target="_blank">
							  <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=059610197X&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="Head First HTML with CSS & XHTML"/>
							  <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							  </div>
							 </a>
							 
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							
							<li>
							  <a href="http://www.amazon.com/gp/product/0596517742/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0596517742" title="JavaScript: The Good Parts" target="_blank">
							   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0596517742&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="JavaScript: The Good Parts"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							  </a>
							  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							<li>
							  <a href="http://www.amazon.com/gp/product/0321564081/ref=as_li_ss_il?ie=UTF8&tag=html061-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=0321564081" title="JavaScript and Ajax for the Web: Visual QuickStart Guide" target="_blank">
							   <img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=0321564081&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="JavaScript and Ajax for the Web: Visual QuickStart Guide"/>
							   <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">
							            JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)
							   </div>
							  </a>
							  
							        <div class="a-icon-row a-spacing-none">
							            <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">
							                <i class="a-icon a-icon-star a-star-4-5"></i>
							            </a>
							            <a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>
							        </div>
							    <div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>
							</li>
							
							</ul>
							</div>
						<ul class="amazon_scroller_nav">
						<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:#888;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>
						</ul>
						<div style="clear: both"></div>
						</div>-->
 
							<!-- crausel data end -->
						</div>

					</div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 192.610062893082px; background: rgb(215, 220, 226);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div></div>
				</div>
				<!-- END WIDGET TAB -->
			</div>
         <!-- <div id="superUserInfoModalId" class="dataTable_wrapper">
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
                            </div> -->
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c;" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  <!-- super user info dialog close -->
<!-- <div class="modal fade" id="super-info-dialog" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content" id="super-info-content">
        <div class="modal-header" style="background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">User Info - <span id ="uName"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button   type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printUserInfoBtn" >Print</button>
        <div class="">
		<span style="font-size:16px;font-weight:bold;" >Search String</span>
		</div>
        <div style="margin-bottom:10px;border-bottom:1px solid red;" class="">
			<table id="searchStringId" class="">
				<tr class="">
				 <td style="color:red;"></td>
				</tr>
			</table>
									
		</div>
		<div class="col-md-12 margin-bottom-30">
				 BEGIN WIDGET TAB
				<div class="widget-bg-color-white widget-tab rounded-3">
					<ul class="nav nav-tabs">
						<li class="" id="li1">
							<a href="#tab_1_1" data-toggle="tab" aria-expanded="true" id="a1"> ABANDONED CART </a>
						</li>
						<li class="" id="li2">
							<a href="#tab_1_2" data-toggle="tab" aria-expanded="false" id="a2"> OUR RECOMMENDATIONS </a>
						</li>
						<li class="" id="li3">
							<a href="#tab_1_3" data-toggle="tab" aria-expanded="false" id="a3" > DOTCOM ACTIVITY </a>
						</li>
						
					</ul>
					<div class="slimScrollDiv" style="position: relative; overflow: auto; width: auto; height: 410px;"><div class="tab-content scroller" style="height: 410px; overflow: hidden; width: auto;" data-always-visible="1" data-handle-color="#D7DCE2" data-initialized="1">
						<div class="tab-pane fade active in" id="tab_1_1">
							 
						
						</div>
						<div class="tab-pane fade" id="tab_1_2">
							 
							
						</div>
						<div class="tab-pane fade" id="tab_1_3">
							
						</div>

					</div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 192.610062893082px; background: rgb(215, 220, 226);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div></div>
				</div>
				
			</div>
         
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c;" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>-->
<div class="modal fade" id="order-info" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="background-color:#23527c">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;" id="ordertitle">Order Info</h4>
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
  <jsp:include page="/WEB-INF/jsp/Training.jsp"></jsp:include>
  <jsp:include page="/WEB-INF/jsp/CallToActionPopUp.jsp"></jsp:include>
  <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
	<input type="hidden" name="currDeviceWidth" id="currDeviceWidth" value="" />
	<input type="hidden" name="sfdcAppBaseUrl" id="sfdcAppBaseUrl" value="${requestScope['SFDC_APP_BASE_URL']}" />
	<!--  dialog end -->	
		<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!-- These Files Are StartUp Files,Should Not Have Any Version -->

<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>
<!-- These Js Files Should Not Have Any Version -->
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
    var scripts = ['jquery-ui.min.js','jquery-uniform.min.js','jquery.dataTables.min.js','bootstrap-min.js','bootstrap-dropdown.js','dataTables.bootstrap.min.js','slimscroll.js','blockui.min.js','bootstrap-switch.js','vmap.js','vmap.russia.js','vmap.world.js','vmap.europe.js','vmap.germany.js','vmap.usa.js','sampledata.js','flot.min.js','float.resize.js','flot.category.js','pulsate.js','moment.js','datepicker.js','metronic.js','layout.js','quick-sidebar.js','demo.js','index.js','task.js','simplePagination.js','amcharts.js','serial.js','light.js','recommSlider.js','sam-recommendation.js','jquery.PrintArea.js','user-activity.js','jquery.cookie.js','global-default-exception-handler.js','editor.js'];

    // Loop through each script name and append our new version number
    scripts.map(function(script){
        var currentScriptVer =${initParam.helios_ver} /* incrementScriptVer(script); */
        
            document.write("<script language='text/javascript' type='text/javascript' src='./resources/javascript/" + script + "?helios_version=" + currentScriptVer + " '><\/script>");
    });
})();
</script>
<script type="text/javascript">document.write(unescape("%3Cscript src='" + (("https:" == document.location.protocol) ? "https" : "http") + "://cdn.mouseflow.com/projects/a40355eb-27cf-43ba-9d15-00c0a64d9ad2.js' type='text/javascript'%3E%3C/script%3E"));</script>

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
var totalItems=0;
$.fn.pageMe = function(opts){
 var $this = this,
     defaults = {
         perPage: 7,
         showPrevNext: true,
         numbersPerPage: 5,
         hidePageNumbers: false
     },
     settings = $.extend(defaults, opts);
 
 var listElement = $this;
 var perPage = settings.perPage; 
 var children = listElement.children();
 var pager = $('.pagination');
 
 if (typeof settings.childSelector!="undefined") {
     children = listElement.find(settings.childSelector);
 }
 
 if (typeof settings.pagerSelector!="undefined") {
     pager = $(settings.pagerSelector);
 }
 
 var numItems = children.size();
 var numPages = Math.ceil(numItems/perPage);
 totalItems=numItems;
 pager.data("curr",0);
 
 if (settings.showPrevNext){
     $('<li><a href="#" class="prev_link">« Prev</a></li>').appendTo(pager);
 }
 
 var curr = 0;
 while(numPages > curr && (settings.hidePageNumbers==false)){
     $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
     curr++;
 }

 if (settings.numbersPerPage>1) {
    $('.page_link').hide();
    $('.page_link').slice(pager.data("curr"), settings.numbersPerPage).show();
 }
 
 if (settings.showPrevNext){
     $('<li><a href="#" class="next_link">Next »</a></li>').appendTo(pager);
 }
 
 pager.find('.page_link:first').addClass('active');
 pager.find('.prev_link').hide();
 if (numPages<=1) {
     pager.find('.next_link').hide();
 }
	pager.children().eq(1).addClass("active");
 
 children.hide();
 children.slice(0, perPage).show();
 
 pager.find('li .page_link').click(function(){
     var clickedPage = $(this).html().valueOf()-1;
     goTo(clickedPage,perPage);
     return false;
 });
 pager.find('li .prev_link').click(function(){
     previous();
     return false;
 });
 pager.find('li .next_link').click(function(){
     next();
     return false;
 });
 
 function previous(){
     var goToPage = parseInt(pager.data("curr")) - 1;
     goTo(goToPage);
 }
  
 function next(){
     goToPage = parseInt(pager.data("curr")) + 1;
     goTo(goToPage);
 }
 
 function goTo(page){
     var startAt = page * perPage,
         endOn = startAt + perPage;
     displayText(startAt ,endOn);
     children.css('display','none').slice(startAt, endOn).show();
     
     if (page>=1) {
         pager.find('.prev_link').show();
     }
     else {
         pager.find('.prev_link').hide();
			//pager.find('.prev_link').css()
     }
     
     if (page<(numPages-1)) {
         pager.find('.next_link').show();
     }
     else {
         pager.find('.next_link').hide();
     }
     
     pager.data("curr",page);
    
     if (settings.numbersPerPage>1) {
    		$('.page_link').hide();
    		$('.page_link').slice(page, settings.numbersPerPage+page).show();
 	}
   
   	pager.children().removeClass("active");
     pager.children().eq(page+1).addClass("active");
 
 }
	goTo(0,5);
};
function displayText(start,end){
	if(end<=totalItems)
	$("#totalId").html("showing "+(start+1)+" to "+end+" from "+totalItems+" customers")
	else
	$("#totalId").html("showing "+(start+1)+" to "+totalItems+" from "+totalItems+" customers") 
}

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
		    $("ul li a[id=menuItemId5]").css("color","rgb(68, 68, 68)");
		    $("ul li a[id=menuItemId5]").css("background-color","#fff");
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
		    $("ul li a[id=menuItemId5]").css("color","rgb(68, 68, 68)");
		    $("ul li a[id=menuItemId5]").css("background-color","#fff");
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
	    $("ul li a[id=menuItemId5]").css("color","rgb(68, 68, 68)");
	    $("ul li a[id=menuItemId5]").css("background-color","#fff");
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
			$('#ulPlaySec').html("");
			$('#ulPlaySec').css("display","none");
			//$("#tglId").css("padding-top","50px");
			$("#tglId").css("width","100%");
			collapsed='true';
			hideMMFeatures();
		}else{
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
				})
				collapsed='false';
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
		if(undefined !=menuItemId && '' !=menuItemId){
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
				   var custNum=$("#reqCustNum").val();
				   var onlineRetail=$("#onlineRetail").val();
				   //alert("-->"+custNum+"--"+onlineRetail);
				   
				   if((undefined !=custNum) && ('' !=custNum) )
					   opencustDetails(custNum);
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
			   //clearCookie();
			   var custNum=$("#reqCustNum").val();
			   var onlineRetail=$("#onlineRetail").val();
			   
			   if((undefined !=custNum) && ('' !=custNum))
			   opencustDetails(custNum);   
		 }	
		}
	});
	/* $("#showHideId0").mouseenter(function(){
		if($("div[role=tooltip]").is(":visible")){
			var obj=$("div[role=tooltip]");
			
			$("div[role=tooltip]").css("border-radius","10px")
			
	}) */
	$('#printRecommendationsBtn').click(function () {
		// log user activity; Print Recommendations Page
		logUserActivity(3003, 'Print Recommendations Page Form Leads');
		
		$('#recommendations-page-content').printArea();
	});
	
	$('#printOrderInfoBtn').click(function () {
		$('#order-info .modal-content').printArea();
	});
	
	$('#printUserInfoBtn').click(function () {
		$('#super-info-dialog .modal-content').printArea();
	});
	
	// log user activity; View Recommendations
	logUserActivity(3002, 'View Recommendations of Lead Customer');
	
	$('#menuItemId5').click();
$('#printDashboardBtn').click(function () {
		
		// log user activity; Print Customer Details Page
		logUserActivity(2019, 'Print Lead Customer Details');
		
		$(".printableDashboard svg").attr("fit-to-page", "true");
		var extraHead = '<scr'+ 'ipt src="./resources/javascript/jquery.min.js" type="text/javascript"></scr' + 'ipt>\n';
		extraHead += '<scr' + 'ipt src="./resources/javascript/dashboard-print.js" type="text/javascript"></scr' + 'ipt>';
		$(".printableDashboard").printArea({"extraHead" : extraHead});
		//window.print();
	});
	//$('#trainingHelp').hide();
});
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
