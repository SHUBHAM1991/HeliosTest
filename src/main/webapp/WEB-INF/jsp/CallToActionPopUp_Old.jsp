<link href="./resources/stylesheet/CallToAction.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<div class="modal fade" id="callToAction_PopUp" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:40px;height:auto;">
      <div class="modal-content" id="super-info-content">
<div class="modal-header ui-draggable-handle" style="background-color: #ff3a39;">
          <button type="button" class="fa fa-close" data-dismiss="modal" style="padding: 5px;float: right;color: #fff !important;background: transparent;font-size: 120%;"></button>
          <!-- <h4 class="modal-title" style="color:#fff;">Call To Action Detailed Info <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i><span id ="cName"></span></h4> -->
          <h3 class="modal-title" style="color:#fff;">Call To Action Detailed Info <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i><li style="font-size: 13px;float: right;padding: 1px;list-style: none;padding-right: 20px;" class="dropdown callToActCls"><!--  col-lg-3 col-sm-4 -->
											<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="color: #fff !important;box-shadow: 0 1px 16px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);background-color: #ff3a39 !important;text-decoration: none;border:none;background: #fff;color: #fff;padding: 8px;text-align: center;letter-spacing: 0.018em;">
		 <span id="statusVal" style="font-size: 14px !important;padding: 6px 5px;">Select Disposition</span><b class="caret"></b>
											</a>
											
												<ul class="dropdown-menu" id="statusList" style="top: 29px;">

													<li class="divider" style="margin:4px 0px;"></li>
													<li id="ntStart"><a href="#" style="">Not Started</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="opp"><a href="#">Create Opportunity</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="comp"><a href="#">Task Completed</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="compEmail"><a href="#">Task Completed-Email</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="closeCall"><a href="#">Task Completed/Close Call Rotation</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="welCall"><a href="#">Welcome Call In Progress</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="leftMsg"><a href="#">Left Message with Customer</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="liveCon"><a href="#">Task Completed - Live Contact</a></li>
													<li class="divider" style="margin:4px 0px;"></li>
													<li id="fTof"><a href="#">Task Completed- Face-To-Face</a></li>
												</ul>
											</li>


				
			
	       </h3>
        </div>
        <div class="modal-body" style="padding:0px;">
        <div class="">
	<div class="">
      <div class="col-lg-12" style="padding-left: 0px;padding-right: 0px;width: 100%;">
        <div class="card">
		<ul class="nav nav-tabs" role="tablist" style="margin:0;">
			<li role="presentation" class="" style="width:100%;padding:10px 15px;">
			<a style="color:#4d4d4d;text-decoration:none;cursor:default;padding-bottom:0px;padding:0px 0px;" href="javascript:void;" aria-controls="home" role="tab" data-toggle="tab">
			<div class="msg msg-danger" id="segName"></div>
				
			</a>
	       </li>
		</ul>
		<div class="" style="">
        <div class="bhoechie-tab-container" style="">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu" style="padding-left: 15px;">
              <div class="list-group">
                <a id="callSeg1" href="#" class="list-group-item text-center active cool-link" style="width:100%;padding-bottom:0px;">
                  RATIONALE FOR SELECTION
                </a>
                <a id="callSeg2" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
                  EXPLORE &amp; EXPAND
                </a>
                <a id="callSeg3" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
                  EXPLORE FOR VALUE: OBJECTION HANDLING
															
                </a>
                <a id="callSeg4" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
    			DRILL FOR ORDER
															
                </a>
                <a id="callSeg5" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
    		   PROPOSE AND CLOSE												
                </a>
                <a id="callSeg6" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
        	   MARKETING RESOURCES												
                </a>
                <a id="callSeg7" href="#" class="list-group-item text-center cool-link" style="width:100%;padding-bottom:0px;">
               MISCELLANEOUS												
                </a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                <!-- flight section -->
                <div class="bhoechie-tab-content active">
	    <!-- RATIONALE DATA START -->
	        <div class="" style="">
				<div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#f7f7f7;border:none;border-top:1px dashed;border-top-color:initial;padding-bottom:0px;">
					<div class="col-sm-12" style="padding:0px;">
					<div id="ratEditId" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;">
					</div>
					 <div class="col-xs-12 col-sm-12 green msg_container_baseScroll" id="rationaleData" style="/* position:absolute; */">
						
					
					</div>
						
					</div>
					<div class="col-xs-12 divider"
						style="padding: 15px; margin-top: 15px;display:none;">
						<div class="notice notice-sm"
							style="font-size: 13px; letter-spacing: .6px; background-color: #fff; color: currentColor; /* font-weight: bold; */ /* font-family: Helvetica; */ opacity: 1; border-color: darkseagreen;">
							<strong
								style="font-size: 13px; text-decoration: underline;">Note</strong><b>:</b>
							very large "stock-up" orders can distort these
							patterns in some months. please engage your
							customers and discuss how Staples can best serve Vnc
							Technical Assistance's toner needs.
						</div>
					</div>
	
	
				</div>
			</div>
			<!-- RATIONALE DATA END -->
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">
                  <!-- EXPLORE & EXPAND START-->
                  <div class="" style="">
				<div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#f7f7f7;border:none;border-top:1px dashed;border-top-color:initial;padding-bottom:0px;">
					<div class="col-sm-12" style="padding:0px;">
					<div id="expEditId" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;">
					</div>
					 <div class="col-xs-12 col-sm-12 green msg_container_baseScroll" id="expData" style="/* position:absolute; */">
						
					
					</div>
						
					</div>
				</div>
			</div>
                  <!-- EXPLORE & EXPAND END -->
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content">
                  <!-- EXPLORE FOR VALUE:OH START -->
                  <div class="" style="">
				<div class="well profile" style="width: 100%;padding-left:15px;padding-right:0px;padding-top:0px;background-color:#f7f7f7;border:none;border-top:1px dashed;border-top-color:initial;padding-bottom:0px;">
					<div class="col-sm-12" style="padding:0px;">
					<div id="expObjEditId" class="nopadding fa fa-edit fa-lg " style="cursor:pointer;position:static;padding: 0px 15px;transform: scale(1.7);text-align: right;line-height: 2em; float: right; color: #ffc107;">
					</div>
					 <div class="col-xs-12 col-sm-12 green msg_container_baseScroll" id="expObjData" style="/* position:absolute; */">
						
					
					</div>
						
					</div>
				</div>
			</div>
                  <!-- EXPLORE FOR VALUE:OH END -->
                </div>
                <div class="bhoechie-tab-content">
                    <center>
                      <h1 class="glyphicon glyphicon-cutlery" style="font-size:12em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
        
                    </center>
                </div>
                <div class="bhoechie-tab-content">
                    <center>
                      <h1 class="glyphicon glyphicon-credit-card" style="font-size:12em;color:#55518a"></h1>
                      <h2 style="margin-top: 0;color:#55518a">Cooming Soon</h2>
        
                    </center>
                </div>
                <div class="bhoechie-tab-content">
                    <center>
                      MARKETING RESOURCES
                    </center>
                </div>
                <div class="bhoechie-tab-content">
                    <center>
                      MISCELLANEOUS
                    </center>
                </div>
            </div>
        </div>
  </div>
       </div>
    </div>
	</div>
</div>
        </div>
        <div class="modal-footer" style="padding:15px;">
        <div class="input-container" style="padding-left: 0px !important;width: 60%;float: left;text-align: left;">
        <label for="reqCustNum" style="color: #ababab;padding: 10px;float: left;font-size: 18px;text-align: left;height: 45px;font-weight: 600;font-family: sans-serif;">Comments : </label>
         <input type="text" class="cool-link" placeholder="Add Comments Here..." onkeypress="enterPressed(this, event);" name="reqCustNum" id="reqCustNum" required="required" style="color: #4d4d4d;background-color: #f7f7f7;border: none !important;height: 35px;float: left;border-bottom: 2px solid crimson !important;width: 60%;padding-left: 5px;border-radius: 5px;font-size: 12px;">
        <div class="bar"></div>
        </div>
        <button type="button" id="applyCallToAction" class="btn btn-primary btn-sm" style="letter-spacing: 0.018em;padding: 6px 15px;box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);background-color:#00b4f0;border:none;font-weight:bold;" data-dismiss="modal">APPLY</button>
        <button type="button" class="btn btn-primary btn-sm" style="letter-spacing: 0.018em;padding: 6px 15px;box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);background-color:#00b4f0;border:none;font-weight:bold;" data-dismiss="modal">CLOSE</button>
        
                  
        </div>
      </div>
    </div>
  </div>
  <!-- call to action popup close -->
  
    <!-- rationale edit popup start -->

<div class="modal fade" id="rationale-info-edit" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="rationaleInfoId" class="dataTable_wrapper">
          <textarea id="ratTxtEditor"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEdit" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- rationale edit popup end -->
  <!-- exp edit popup start -->

<div class="modal fade" id="exp-info-edit" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="expInfoId" class="dataTable_wrapper">
          <textarea id="expTxtEditor"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="expApplyOnEdit" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- exp edit popup end -->
  
 <!-- exp edit popup start -->
 
<div class="modal fade" id="expObj-info-edit" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="expObjInfoId" class="dataTable_wrapper">
          <textarea id="expObjTxtEditor"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="expObjApplyOnEdit" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" data-dismiss="modal">Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- exp obj edit popup end -->