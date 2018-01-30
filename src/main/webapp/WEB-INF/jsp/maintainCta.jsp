
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<link href="./resources/stylesheet/commonStyles.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<style type="text/css">
.list-group-item {
	text-align: left !important;
	padding: 24px 12px !important;
	padding-top: 4px !important;
	border: none !important;
	background-color: #1d2939 !Important;
	color: #ddd;
}

ul li {
	list-style-type: disc !important;
	margin-left: 15px !important;
}

table#paperPromoTable>thead>tr>th {
	font-size: 12px;
}

table#paperPromoTable>thead>tr>td {
	font-size: 12px;
}
</style>
</head>
		<!-- BEGIN CONTENT -->
		<fieldset style="background-color: #f1f3fa;height:600px;">
			<div class="page-content" style="min-height:600px !important;">
<!-- Maintaing Role Start -->


					<!-- Form Name -->
					
		<div class="col-md-6" style="margin-left:25%;margin-top:2%">
			
			<fieldset style="border: 1px solid #eee;height:auto;background-color: #fff;box-shadow:0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);">
			<form class="form-horizontal" id="userRole" style="border: 1px solid #efefef;;border-radius: 10px 10px 0px 0px;">
					<!-- Form Name -->
					<legend style="padding:10px; background-color: #f7f7f7;color: #333;;font-family:arialmt">Call to Action configuration</legend>
					<div class="form-group">
						<label class="col-md-3 control-label" for="roleId" style="padding-top: 0px;">Segment</label>
						<div class="col-md-3">
							<select name="role" id="segNameId" class="form-control" onchange="segChange(this);">
								
							</select>
						</div>
					</div>
					<div class="form-group" id="subSegFormId">
						<label class="col-md-3 control-label" for="roleId" style="padding-top: 0px;">Sub Segment</label>
						<div class="col-md-3">
							<select name="role" id="segDescId" class="form-control">

							</select>
						</div>
					</div>
				
			</form>
			<div class="col-md-8" style="text-align: right;padding:5px;float: right;">
							
							<button class="btn btn-success" id="CtaOpenBtn"
								value="Assign Role" onclick="openSubCallToAction()"
								style="font-weight: bold;background-color:#dd1d1d;box-shadow:0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);border-radius: 5px !important;">open cta details</button>
		  </div>
		  </fieldset>
		</div>
		<!-- Maintaing Role End -->
		<!-- Role Screen Mapping Start -->
		<div class="col-md-6" id="assignUserRoles" style="margin-left:25%;margin-top:1%;display:none;">

		</div>
		<!-- Role Screen Mapping End -->
		</div>

<!-- <div id="checkExistingDialog-confirm" ></div> -->
  <div class="modal fade" id="checkExistingDialog-confirm" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header" style="height: 50px;color: #fff;background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body" style="height: 80px;" id="modalBodyId">
          
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-default" id="openid" >Yes</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
 
        </div>
      </div>
    </div>
  </div>
 <!--  <div id="alertText">
   <div>Membership renewal on the horizon. Customer will receive Premium invoice at month 11 of current term. Ensure member is happy with program and remind customer of Lifetime Savings, benefits and services. Membership retention is vital!<div>
    <div style="padding-top:10px;"><span id="ALRT_1_1_Key" style="font-weight:bold;"> </span><span id="ALRT_1_1_Val"></span></div>    
    <div><span id="ALRT_1_2_Key" style="font-weight:bold;"></span><span id="ALRT_1_2_Val"></span></div>
   </div></div>
  </div> -->
  <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
  
  
<feildset>
<jsp:include page="/WEB-INF/jsp/CallToActionPopUp.jsp"></jsp:include>
<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-uniform.min.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-min.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-dropdown.js" type="text/javascript"></script>

<script src="./resources/javascript/bootstrap-switch.js" type="text/javascript"></script>

<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="./resources/javascript/editor.js" type="text/javascript"></script>
<script src="./resources/javascript/common.js" type="text/javascript"></script>
<script src="./resources/javascript/maintainCta.js" type="text/javascript"></script>
<script src="./resources/javascript/user-activity.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery.cookie.js" type="text/javascript"></script>
<script src="./resources/javascript/global-default-exception-handler.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->