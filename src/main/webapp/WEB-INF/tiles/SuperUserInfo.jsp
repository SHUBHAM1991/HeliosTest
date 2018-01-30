<link href="./resources/stylesheet/stylesV2.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/stylesV3.css" rel="stylesheet" type="text/css">
<script>

</script>


<div class="modal fade" id="super-info-dialog" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
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
		<div class="col-md-12 margin-bottom-30">
				<!-- BEGIN WIDGET TAB -->
				<div class="widget-bg-color-white widget-tab rounded-3">
					<ul class="nav nav-tabs">
						<li class="" id="li1">
							<a href="#tab_1_1" data-toggle="tab" aria-expanded="true" id="a1">SA.COM ACTIVITY </a>
						</li>

						<li class="" id="li3">
							<a href="#tab_1_3" data-toggle="tab" aria-expanded="false" id="a3" >STAPLES.COM ACTIVITY </a>
						</li>

					</ul>
					<div class="slimScrollDiv" style="position: relative; overflow: auto; width: auto; height: 410px;"><div class="tab-content scroller" style="height: 410px; overflow: hidden; width: auto;" data-always-visible="1" data-handle-color="#D7DCE2" data-initialized="1">
						<div class="tab-pane fade active in" id="tab_1_1">
						</div>
						<div class="tab-pane fade" id="tab_1_3">
						</div>
					</div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 192.610062893082px; background: rgb(215, 220, 226);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div></div>
				</div>
				<!-- END WIDGET TAB -->
			</div>

        </div>
        <div class="modal-footer" style="padding:8px;">
         <button type="button" class="btn btn-primary btn-sm" style="background-color:#23527c;" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  <!-- super user info dialog close -->
  
  <!-- sfdc confirmation modal start -->
  <div class="modal fade" id="sfdcConfModal" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span >Contact Confirmation</span>
		        </div>
		          <div class="modal-body" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;">
		          Are you sure this customer's contact does not already exist in Salesforce.com?
		          <input type="hidden" id="sfdcConfUrl"/>
		          </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-primary" id="sfdcYesId" data-dismiss="modal" style="border-radius: 2px !important; background-color: #23527c; border-color: #23527c;">YES</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;">NO</button>
		        </div>
		      </div>
		    </div>
		  </div>
  
  <!-- sfdc confirmation modal end -->
  
