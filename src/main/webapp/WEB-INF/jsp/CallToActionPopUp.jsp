<link href="./resources/stylesheet/CallToAction.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">

<!-- common confirmation modal for Yes/No start -->
  <div class="modal fade" id="commonConfDialog" role="dialog" style="z-index:1104">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span id="dialogTitle"></span>
		        </div>
		          <div class="modal-body" id="dialogText" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;" >
		          </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-primary" id="dialogYesId" data-dismiss="modal" style="border-radius: 2px !important; background-color: #23527c; border-color: #23527c;">YES</button>
                  <button type="button" class="btn btn-secondary" id="dialogNoId" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;">NO</button>
		        </div>
		      </div>
		    </div>
		  </div>
  <!-- common confirmation modal Yes/No end -->
  
  <!-- common confirmation modal with only Ok button start -->
  <div class="modal fade" id="commonConfDialogOk" role="dialog" style="z-index:1105">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content" style="width:70%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span id="okDialogTitle"></span>
		        </div>
		          <div class="modal-body" id="okDialogText" style="padding:22px 20px;font-size: 16px;font-family: arialmt;height: 70px;" >
		          </div>
		        <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" id="okDialogNoId" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;font-weight:bold;">OK</button>
		        </div>
		      </div>
		    </div>
		  </div>
  <!-- common confirmation modal with only Ok button end -->
  
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
<!-- comment history modal start -->
  <div class="modal fade" id="commentHistoryModal" role="dialog" style="z-index:1102">
		    <div class="modal-dialog modal-md" style="margin-top:40px;height:auto;">
		      <div class="modal-content" style="width:100%;">
		        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <span >Task History</span>
		        </div>
		          <div id="commentLogBody" class="modal-body" style="max-height: 400px;padding:22px 20px;font-size: 16px;font-family: arialmt;overflow-y:auto;">
                  
		          </div>
		        <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 2px !important; background-color: crimson; border-color: crimson;color: #fff;font-weight:bold;">OK</button>
	        </div>
	      </div>
	    </div>
	  </div>
  <!-- comment history modal end -->

  <!-- subject history modal start -->
  <div class="modal fade" id="subjectModal" role="dialog" style="z-index:1102">
		    <div class="modal-dialog modal-md" style="margin-top:40px;height:auto;">
		      <div class="modal-content" style="width:75%;">
		        <div class="modal-header" style="box-shadow: 0 2px 4px 0 rgba(0,0,0,.2), 0 6px 15px 0 rgba(0,0,0,.19);font-family: Helvetica;font-size: 18px;color: #222;background-color:gainsboro;">
		          <button type="button" class="" data-dismiss="modal" style="font-weight: bold;float: right;color:#222;">X</button>
		          <span >Subjects</span>
		        </div>
		          <div id="subjectBody" class="modal-body" style="max-height: 400px;padding:22px 20px;font-size: 16px;font-family: arialmt;overflow-y:auto;">
                     
		          </div>
		        <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal" style="border-radius: 2px !important; background-color: #dd1d1d; border-color: deepskyblue;color: #fff;font-weight:bold;">OK</button>
	        </div>
	      </div>
	    </div>
	  </div>
  <!-- subject history modal end -->
<div class="modal fade" id="callToAction_PopUp" role="dialog" style="z-index:1100;overflow-y:hidden !important">
    <div class="modal-dialog modal-lg" id="callTActionId" style="margin-top:5px !important;height:auto;position:relative;margin:30px auto;">
      <div class="modal-content" id="super-info-content">
<div class="modal-header" style="background-color: #f7f7f7;">
          <button type="button" id="closeCallToAction" class="fa fa-close" data-toggle="modal" style="padding: 5px;float: right;color: #333 !important;background: transparent;font-size: 120%;"></button>
          <span class="vertical"></span>
			<span style="padding-right: 10px;float:right;margin-top: -5px;">
			<div style="">
			<div id="updateDateValueCtaR" style="font-family: Helvetica;float:right;font-size: 11px;color: #666;font-weight: 600;"><span></span></div>			
			<div id="updateDateLabelCtaR" style="font-family: Helvetica;float:right;font-size: 12px;color: #333;font-weight: 600;"><span style="padding-right:3px;">Refreshed: </span></div>
			</div>
			<div style="">
			<div id="updateDateValueCtaLR" style="font-family: Helvetica;float:right;font-size: 11px;color: #666;font-weight: 600;"><span></span></div>			
			<div id="updateDateLabelCtaLR" style="font-family: Helvetica;float:right;font-size: 12px;color: #333;font-weight: 600;"><span style="padding-right:3px;">Last Refresh: </span></div>
			  </div>
			</span>
          <h3 id="ctaNameDesc" class="modal-title" style="color:#fff;">
          
	       </h3>
        </div>
        <div class="modal-body" style="padding:0px;">
        <div class="">
	<div class="">
      <div class="col-lg-12" style="padding-left: 0px;padding-right: 0px;width: 100%;">
        <div class="card">
		<div class="" style="">
        <div class="bhoechie-tab-container" style="">
            
            
        </div>
</div>
	<ul class="nav nav-tabs" role="tablist" style="margin: 0;" id="commentSectionId">
			
              <div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
            <div class="panel panel-primary" style="margin-bottom:0px;border:none;">
                <a href="javascript:void()" target="_blank" id="ctaCreateOpp" style="float: right;text-decoration:underline;padding-left:10px;color:dodgerblue;font-size:12px;">Create new opportunity in SFDC</a>
                <div class="panel-body" style="padding-bottom: 0px;padding-top: 20px;;">
                    <div class="row" style="border-style: dashed;border: 1px dashed;padding-top:10px;" id="dispositionArea">
                        <div class="col-md-6 col-sm-6" style="padding-right:0px;">
                        
                            <!-- subject start -->
							<div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-top:0px;padding-bottom:5px;">
							<label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family:Arial,sans-serif;font-weight: bold;color:#666;">Subject:</label>
							<div class="col-lg-9 col-md-9 col-sm-9" style="padding-left:0px;">
				               <input type="text" id="subjectInputText" placeholder="Select or Enter Subject" class="form-control" style="border: 1px solid #a6a6a6 !important;float: left;width: 66%;margin-bottom:0px;box-shadow: none;" maxlength="80">
				               <div class="input-group-btn col-lg-3 col-md-6 col-sm-6" style="padding-left:0px;">
				               <button  type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border-color:#bbb"> <span class="caret"></span></button>
				               <button  id="charInfoTool" type="button" class="btn btn-default fa fa-question-circle"  data-placement="right" data-toggle="tooltip" title="80 characters max" style="padding: 9px;border-color:#bbb"></button>
				                <ul class="dropdown-menu pull-right msg_container_base" id="subjectList" style="top:inherit !important;background-color: #fff;max-height: 250px !important;overflow-y:auto;overflow-x: hidden;width: 235px;right:-110%">
				                </ul>
				                  <span id="oldSubjectInputText" style="display:none;"></span>
				               </div><!-- /btn-group -->
				               </div>
				            </div><!-- /input-group -->
							<!-- subject close -->
							
                         <!-- disp start -->       
					         <div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-bottom:5px;">
					          <label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family: Arial,sans-serif;font-weight: bold;color:#666;">Disposition:</label>
							<button class="dropdown-toggle " data-toggle="dropdown" aria-expanded="false" style="width:62%;line-height:1.5;color: #555 !important;background-color: #fff;text-decoration: none;background: #fff;padding: 5px;text-align: left;letter-spacing: 0.018em;font-family: Robot,sans-serif;font-size: 16px;border: 1px solid #888;">
							<span id="statusVal" style="padding: 6px 5px;">Select Disposition</span><b class="caret" style="float: right; padding-top: 10px;"></b>
							<span id="oldStatusVal" style="display:none;"></span>
							<span id="linkTaskId" style="display:none;"></span>
							</button>
							<ul class="dropdown-menu" id="statusList" >
							
							</ul>
							</div>         
							<!-- disp close -->   
					
							
							<!-- contact start -->
							 <div class="dropup col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 10px;float:left;padding-right:0px;padding-bottom:10px;">
							 <label class="col-lg-3 col-md-3 col-sm-3 lblTxtClass" style="padding: 5px 0px;font-family:  Roboto,sans-serif;font-weight: bold; color:#666;">SFDC Contact:</label>
								<button class="dropdown-toggle col-lg-8" data-toggle="dropdown" aria-expanded="false" style="width:62%;line-height:1.5;color: #555 !important;background-color: #fff;text-decoration: none;background: #fff;padding: 5px;text-align: left;letter-spacing: 0.018em;font-family: Robot,sans-serif;font-size: 16px;border: 1px solid #888;">
								<span id="orderContactVal" style="padding: 6px 5px;">SFDC Contact</span><b class="caret" style="float: right; padding-top: 10px;"></b>
								<span id="oldOrderContactVal" style="display:none;"></span>
								<span id="orderContactId" style="display:none;"></span>
								</button>
								 <ul class="dropdown-menu msg_container_base" id="orderContactList" style=" background-color: #fff;max-height: 300px !important;overflow-y:auto;overflow-x: hidden;width: 270px;">
								
								</ul>
							</div>
							<!-- contact close -->
							
			  			<div>
                       <!-- <div class="input-group" style="width: 50%;padding-bottom:10px;float:left">
				          <p><a href="javascript:openCommentHistoryPopup();" id="commentHistory" style="text-decoration:underline;color:dodgerblue;font-size:12px;">View Task History</a></p>
				           </div>  -->   
				      <div style="float:right;margin-bottom:10px;margin-right:13%;">
						<button type="button" id="applyCallToAction" class="btn btn-default submit" data-toggle="modal" style="background-color:#dd1d1d;border:none;font-weight:bold;color:#fff;"><i class="fa fa-paper-plane" aria-hidden="true" style="display:none;"></i>SAVE TO SFDC</button>
					  </div>
					  </div>
					</div>
                        <div class="login-box col-md-6 col-sm-6">
                         <form role="form">
					       
							<div class="form-group" id="commentForm" style=" background-color: #e0e0e0; padding: 10px;margin-bottom:7px;">
			  				<label class="col-md-3 lblTxtClass" for="description" style="color: #666;font-family: Arial,sans-serif;font-weight: bold;padding-left:0px;"> Comments:</label>
			  			 	<textarea class="form-control" id="commentId" onKeyUp="limitText(this.form.commentId,this.form.countdown,1000);" maxlength="1000" 
			  			 	 onKeyDown="limitText(this.form.commentId,this.form.countdown,1000);" placeholder="" style="resize: none;font-size: 12px;color: dimgray;height:70px !important;"></textarea>
			  			 	 <font size="1" style="font-family:Robot,sans-serif;color: #4d4d4d;font-size: 11px;">(Characters left: <span id="charLimit">1000</span>)</font>
						     <input readonly type="hidden" name="countdown" size="3" value="1000"/>
						     <span id="oldCommentId" style="display:none;"></span>
			  			   </div>
					        
				      </form>
				      <div>
                       <div class="input-group" style="text-align: right;width: 100%;padding-bottom:10px;float: right;">
				          <p><a href="javascript:openCommentHistoryPopup();" id="commentHistory" style="text-decoration:underline;color:dodgerblue;font-size:12px;">View Task History</a></p>
				      </div>    
				    
					  </div> 
			     </div>
			    </div>
                </div>
                <div class="panel-footer" style="display: none;">
                    <div class="row" >
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="Remember">
                                    Remember me
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            
                            <button type="button" class="btn btn-labeled btn-danger">
                                <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		    </ul>
	      </div>
         </div>
	     </div>
        </div>
        </div>
        <div class="modal-footer" style="padding:15px;display:none;" id="modalFooterConfigId">
         <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" style="float:right;letter-spacing: 0.018em;padding: 6px 15px;box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);background-color:#dd1d1d;border:none;font-weight:bold;border-radius:15px !important;" >CLOSE</button>
        </div>
        <div class="modal-footer" style="padding:15px;" id="modalFooterId">
        
        </div>
      </div>
    </div>
  </div>
  <!-- call to action popup close -->
  
    <!-- rationale edit popup start -->

<div class="modal fade" id="info-edit-Rat" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span>Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdRat" class="dataTable_wrapper">
          <textarea id="txtEditorRat"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditRat" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- rationale edit popup end -->
  <!-- exp edit popup start -->

<div class="modal fade" id="info-edit-Exp" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdExp" class="dataTable_wrapper">
          <textarea id="txtEditorExp"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditExp" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- exp edit popup end -->
  
 <!-- exp edit popup start -->
 
<div class="modal fade" id="info-edit-ExpObj" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdExpObj" class="dataTable_wrapper">
          <textarea id="txtEditorExpObj"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditExpObj" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- exp obj edit popup end -->
  
   <!-- drill popup start -->
 
<div class="modal fade" id="info-edit-Drill" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdDrill" class="dataTable_wrapper">
          <textarea id="txtEditorDrill"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditDrill" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- drill popup end -->
  
  <!-- prop popup start -->
 
<div class="modal fade" id="info-edit-Prop" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdProp" class="dataTable_wrapper">
          <textarea id="txtEditorProp"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditProp" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- prop popup end -->
  <!-- mark popup start -->
 
<div class="modal fade" id="info-edit-Mark" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdMark" class="dataTable_wrapper">
          <textarea id="txtEditorMark"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditMark" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c" >Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- mark popup end -->
  <!-- misc popup start -->
 
<div class="modal fade" id="info-edit-Misc" role="dialog" style="z-index:1103">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;">
      <div class="modal-content">
        <div class="modal-header" style="font-family: Helvetica;font-size: 18px;color: #fff;background-color:#23527c;">
		 <button type="button" class="close" data-dismiss="modal">&times;</button>
		 <span >Rationale For Selection Info</span>
		 </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <!-- <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" id="printOrderInfoBtn" >Print</button> -->
         <div id="infoIdMisc" class="dataTable_wrapper">
          <textarea id="txtEditorMisc"></textarea>                       
         </div>
        </div>
        <div class="modal-footer" style="padding:8px;">
         <button id="applyOnEditMisc" type="button" class="btn btn-primary btn-sm" style="background-color:#23527c">Apply</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- misc popup end -->