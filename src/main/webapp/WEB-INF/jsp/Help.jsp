<link href="./resources/stylesheet/stylesV2.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/stylesV3.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">

<div class="modal fade" id="training_modal" role="dialog">
    <div class="modal-dialog modal-lg" style="margin-top:30px;height:auto;width: 460px;">
      <div class="modal-content" id="training-content">
        <div class="modal-header modal-head" style="padding:10px 15px;">
          <!-- <button type="button" class="modal-cross" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title modal-tit" style="">Help<span id ="uName"></span></h4>
        </div>
        <div style="padding: 1px;border-left: solid 2px #012c43;border-right: solid 2px #012c43;"><hr style="margin: 0;"></div>
        <div class="modal-body col-md-12 modal-bod" style="padding:0px;">
        <button type="button" style="display:none;float: right;margin-right:1px;border-radius:10px !important ;" 
        	class="btn btn-primary btn-xs" id="printUserInfoBtn" >Print</button>

		<div class="col-md-12 margin-bottom-10" style="padding:0px;">
			<!-- BEGIN WIDGET TAB -->
			<div class="widget-bg-color-white widget-tab rounded-3">
				<div class="">
    <div class="notice notice-danger">
        <!-- <strong style="font-size:16px;letter-spacing:.5px;"> Help/FAQ's </strong> --> 
        <ul class="">
					
					<li class="modal-li" id="faqli1" style="line-height:3;">
						<span class="badge modal-icon">1</span><a href="mailto:helios_support@Staples.com" class="modal-link-cls" id="faqa1" style="text-decoration:underline;">Help Desk</a><span class="modal-supp-text"> (Helios technical support)</span>
					</li>
					<li class="modal-li" id="li4" style="line-height:2;">
						<span class="badge modal-icon">2</span><a href="./resources/docs/Cisco Any Connect APP Installation (for ipad).pdf" class="modal-link-cls" id="a4" download="Cisco Any Connect APP Installation (for ipad).pdf" style="text-decoration:underline;">Cisco Any Connect APP Installation for ipad</a><span class="modal-supp-text"></span>
					</li>
					
				</ul>
    </div>
    
    <hr style="margin-top: 5%;">
   <!--  <div class="notice notice-sm" style="border:none;text-align:left;">
        <strong class="modal-note">Note</strong><b>:</b> <span class="modal-note-txt"> If you are having trouble accessing Helios or any of the Helios functions, ensure you are using Chrome and clear the browser cache.</span><br><a href="#" onclick="javascript:showCachePopUp()" class="modal-link-cls" id="note" >Click here</a> <span class="modal-supp-text" style="margin-left:0%;">to find more details</span>
    </div> -->
    <div class="notice notice-sm" style="border:none;text-align:left;">
  <div class="col-lg-1 col-md-1 col-sm-1">
        <strong class="modal-note">Note</strong><b>:</b>
  </div>
  <div class="col-lg-11" style="padding-left: 25px;padding-right: 10px;margin-bottom: 3%;" class="padCls">
    <span class="" style="font-size: 16px;font-family: helvetica;color: #444444;">  If you are having trouble accessing Helios or any of the Helios functions, make sure you are using Chrome browser and clear the cache.</span><br><a href="#" onclick="javascript:showCachePopUp()" class="modal-link-cls" id="note" style="margin-left: 0px;text-decoration:underline;">Click here</a> <span class="" style="font-size: 16px;margin-left:0%;font-family: helvetica;color: #444444;">for more details.</span>
    </div>
</div>
</div>
			</div>
			<!-- END WIDGET TAB -->
		</div>
         
        </div>
        <div class="modal-footer col-md-12 modal-foot" style="padding:4px;">
         <button type="button " class="btn btn-sm modal-cls"  data-dismiss="modal" style="color:#fff;">CLOSE</button>
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