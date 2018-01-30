<link href="./resources/stylesheet/stylesV2.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/stylesV3.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">

<div class="modal fade" id="training_modal" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:30px;height:auto;width:500px">
      <div class="modal-content" id="training-content">
        <div class="modal-header" style="background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">Help<span id ="uName"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" 
        	class="btn btn-primary btn-xs" id="printUserInfoBtn" >Print</button>

		<div class="col-md-12 margin-bottom-10">
			<!-- BEGIN WIDGET TAB -->
			<div class="widget-bg-color-white widget-tab rounded-3">
				<div class="">
    <div class="notice notice-danger">
        <!-- <strong style="font-size:16px;letter-spacing:.5px;"> Help/FAQ's </strong> --> 
        <ul class="">
					
					<li class="" id="faqli1" style="color:#d73814;list-style:circle;line-height:1.8;text-align:left;">
						<a href="mailto:helios_support@Staples.com" style="color:#d73814;text-decoration:underline;" id="faqa1" >Help Desk</a><span> (technical support)</span>
					</li>
					<li class="" id="li4" style="color:#d73814;list-style:circle;line-height:1.8;text-align:left;">
						<a href="./resources/docs/Cisco Any Connect APP Installation (for ipad).pdf" style="color:#d73814;text-decoration:underline;" id="a4" download="Cisco Any Connect APP Installation (for ipad).pdf">Cisco Any Connect APP Installation</a><span> (for ipad)</span>
					</li>
					
				</ul>
    </div>
    
    
    <div class="notice notice-sm" style="border:none;text-align:left;">
        <strong style="font-size: 13px; text-decoration: underline;">Note</strong><b>:</b>  If you are having trouble accessing Helios or any of the Helios functions, ensure you are using Chrome and clear the browser cache (<a href="#" onclick="javascript:showCachePopUp()" style="color:blue;text-decoration:underline" id="note" >Click here</a> to find more details)
    </div>
</div>
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
  <!-- FAQ Modal Start  -->
  <div class="modal fade" id="faq_modal" role="dialog" style="dispay:none;">
    <div class="modal-dialog modal-lg" style="margin-top:80px;height:auto;width:500px">
      <div class="modal-content" id="faq-content">
        <div class="modal-header" style="background-color:#23527c;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:#fff;">Frequently Asked Questions<span id ="uName"></span></h4>
        </div>
        <div class="modal-body" style="padding-bottom:0px;">
        <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" 
        	class="btn btn-primary btn-xs" id="printUserInfoBtn" >Print</button>

		<div class="col-md-12 margin-bottom-25" style="margin-top:20px;">
			<!-- BEGIN WIDGET TAB -->
			<div class="widget-bg-color-white widget-tab rounded-3">
				<ul class="">
					<li class="" id="faqli1">
						<a href="mailto:helios_sa_appsupt@staples.com" id="faqa1" >HeliosHelpDesk</a><span> (technical support)</span>
					</li>
					<!-- <li class="" id="li2">
						<a href="./resources/docs/Helios Training Exercise 12.10.15.pdf" id="a2" download="Helios Training Exercise 12.10.15.pdf">Training Exercise</a>
					</li>-->
					<li class="" id="faqli2">
						<a href="mailto:helios_sa_bussupt@staples.com" id="faqa3" >HeliosFeedback</a><span> (application suggestions)</span>
					</li>
				</ul>
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
  <!-- FAQ Modal End -->
  <jsp:include page="/WEB-INF/jsp/Cache.jsp"></jsp:include>
  <script type="text/javascript">
  function showCachePopUp() {
	  $('#cache_modal').modal({
		  "backdrop"  : "static",
	      handle: ".modal-header",
		  "keyboard"  : true,
		  "show"      : true           
	  });
  }
  </script>