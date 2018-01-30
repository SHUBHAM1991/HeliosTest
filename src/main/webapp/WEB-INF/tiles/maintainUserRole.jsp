
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<!-- <link href="./resources/stylesheet/bootstrap-combine.css" rel="stylesheet" type="text/css"> -->
<link href="./resources/stylesheet/commonStyles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./resources/javascript/canvas.js"></script>
<script type="text/javascript" src="./resources/javascript/app.js"></script>
<script type="text/javascript">

 /* window.onload = function () {}
 */
  </script>
  
  <style type="text/css">
  div.amcharts-chart-div{
  height:286px !mportant;
  }
  div.amcharts-chart-div a{
   display:none !important;
  }
  div.amcharts-chart-div svg{
  width:100% !important;
  }
  </style>
</head>
		<!-- BEGIN CONTENT -->
		
		<fieldset style="background-color: #f1f3fa;height:600px;">
			<div class="page-content" style="min-height:600px !important;">
<!-- Maintaing Role Start -->


					<!-- Form Name -->
					
		<div class="col-md-6" style="margin-left:25%;margin-top:2%">
		<div class="alert" role="alert" style="text-align: right;padding:5px;background-color:#fff;">
				<button class="btn btn-success" id="btnSubmit" onclick="clearUserForm()"
					style="background-color: cadetblue;padding: 2px 10px;font-size: 11.9px;font-weight: bold;border:1px solid cadetblue;border-radius: 3px !important;">Clear
					</button>
				<button class="btn btn-primary" onclick="findUser()"
					style="background-color: cadetblue;padding: 2px 10px;font-size: 11.9px;font-weight: bold;border:1px solid cadetblue; border-radius: 3px !important;">Open
					User Details</button>
			</div>
			<fieldset style="border: 1px solid #eee;height:220px;background-color: #fff">
			<form class="form-horizontal" id="userRole" style="border: 1px solid #efefef;;border-radius: 10px 10px 0px 0px;">
                   
					<!-- Form Name -->
					<legend style="padding-left:10px; background-color: orangered;color: #fff;border-radius:10px 10px 0px 0px;">Maintain User Role</legend>
					<div class="form-group" style="margin-top:2%;">
						<label class="col-md-3 control-label" for="userId">User
							Id</label>
						<div class="col-md-3">
							<input name="userId" id="userId" maxlength="10" onkeypress="return allowNumbersOnly(this, event)" type="text"
								placeholder="Enter User Id" class="form-control input-md">
						    <input type="text" name="userIdHidden" id="userIdHidden" maxlength="10" onkeypress="return allowNumbersOnly(this, event)" style="display:none;"/>		
						</div>
						<!-- Multiple Radios (inline) -->
						<!-- <label class="col-md-3 control-label" for="radios">Inline Radios</label>
					  <div class="col-md-3"> 
					    <label class="radio-inline" for="radios-0">
					      <input type="radio" name="radios" id="radios-0" value="1" checked="checked">
					      Yes
					    </label> 
					    <label class="radio-inline" for="radios-1">
					      <input type="radio" name="radios" id="radios-1" value="2">
					      No
					    </label> 
					  </div> -->
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="roleId">Role</label>
						<div class="col-md-3">
							<select name="role" id="roleId" class="form-control" onmouseover="showRoleDesc()">
								<option value="default">Select</option>
								<option value="ADMIN" title="IT Administrator">ADMIN</option>
								<option value="NOACCESS" title="No Access User">NOACCESS</option>
								<option value="READONLY" title="Read Only Access">READONLY</option>
								<option value="MANAGER" title="Manager">MANAGER</option>
								<option value="PLAYOWNER" title="Play Owner">PLAYOWNER</option>
							</select>
						</div>
					</div>
					<!-- Button (Double) -->
					<div class="">
						<label class="col-md-3 control-label" for="button1id"></label>
						
					</div>

				
			</form>
			<div class="col-md-8" style="text-align: right;margin-top:2%;float: right;">
							
							<button class="btn btn-success" id="assignRole"
								value="Assign Role" onclick="validateUser()"
								style="font-weight: bold; bold;border-radius: 5px !important;">Assign Role</button>
							<button class="btn btn-primary" id="updateRole"
								value="Update Role" onclick="updateUserRole()"
								style="font-weight: bold; bold;border-radius: 5px !important;display:none;">Update Role</button>
							<!-- style="display:none;" -->
							<button class="btn btn-danger" id="removeRole"
								value="Remove Role" onclick="javascript:removeUserRole()"
								style="font-weight: bold; bold;border-radius: 5px !important;display:none;">Remove Role</button>
							<!-- style="display:none;" -->
		  </div>
		  </fieldset>
		</div>
		<!-- Maintaing Role End -->
		<!-- Role Screen Mapping Start -->
		<div class="col-md-6" id="assignUserRoles" style="margin-left:25%;margin-top:1%;display:none;">
		<fieldset style="border: 1px solid #eee;height:230px;background-color: #fff;">
			<form class="form-horizontal" style="border: 1px solid #efefef;border-radius: 10px 10px 0px 0px;">
				

					<!-- Form Name -->
					<legend style="padding-left:10px; background-color: orangered;color: #fff;border-radius:10px 10px 0px 0px;">Role Screen Mapping</legend>
					<!-- Select Basic -->
					<!-- Text input-->

					<!-- Button (Double) -->
					<div class="form-group">
						<!-- <label class="col-md-3 control-label" for="button1id"></label> -->
						<div class="col-md-10" >
							<div class="form-group form-md-checkboxes"
								style="margin-left: 5%;padding-bottom:10px;">
								<label>User has been assigned access to the below checked screens</label> 
								<div class="md-checkbox-inline">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="md-checkbox">
												<input type="checkbox" name="home_cust_profiles"
													value='home_cust_profiles' id="home_cust_profiles"
													style="opacity: 1;" class="md-check"> <label
													for="home_cust_profiles"> <span></span> <span
													class="check"></span> <span class="box"></span> Customer
													Profiles
												</label>
											</div>
											<div class="md-checkbox">
												<input type="checkbox" name="home_template2"
													value='home_template2' id="home_template2"
													style="opacity: 1;" class="md-check" > <label
													for="home_template2"> <span></span> <span
													class="check"></span> <span class="box"></span> Customer
													Details
												</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="md-checkbox">
												<input type="checkbox" name="recommAction"
													value='recommAction' id="recommAction" style="opacity: 1;"
													class="md-check"> <label for="recommAction">
													<span></span> <span class="check"></span> <span class="box"></span>
													Recommendations
												</label>
											</div>
											<div class="md-checkbox">
												<input type="checkbox" name="maintainUserRole"
													value='maintainUserRole' id="maintainUserRole"
													style="opacity: 1;" class="md-check"> <label
													for="maintainUserRole"> <span></span> <span
													class="check"></span> <span class="box"></span> Maintain
													User Role
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- style="display:none;" -->
						</div>
					</div>
			</form>
			<div id="top-button" style="text-align:right;margin:2%;">
								<button class="btn btn-success" id="btnSave" value="Save" onclick="assignMappingRole();"
								style="background-color: #5cb85c;font-weight: bold;border-radius: 5px !important;border: 1px solid #5cb85c;">SAVE</button></div>
			</fieldset>
			
			
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
  
  <!-- log user activity; rep. data -->
	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
	<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
	<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
  
  
<feildset>

<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>
<script src="./resources/javascript/app.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-uniform.min.js" type="text/javascript"></script>
<!-- <script src="./resources/javascript/fullcalender.js" type="text/javascript"></script>
<script src="./resources/javascript/easypiechart.js" type="text/javascript"></script>
<script src="./resources/javascript/sparkline.min.js" type="text/javascript"></script> -->
<script src="./resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/javascript/bootstrap-min.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-dropdown.js" type="text/javascript"></script>
    <script src="./resources/javascript/dataTables.bootstrap.min.js"></script>
<script src="./resources/javascript/bootstrap-switch.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="./resources/javascript/sampledata.js" type="text/javascript"></script>
<script src="./resources/javascript/pulsate.js" type="text/javascript"></script>
<script src="./resources/javascript/moment.js" type="text/javascript"></script>
<script src="./resources/javascript/datepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
 <script src="./resources/javascript/metronic.js" type="text/javascript"></script> 
<script src="./resources/javascript/layout.js" type="text/javascript"></script>
<script src="./resources/javascript/quick-sidebar.js" type="text/javascript"></script>


<script src="./resources/javascript/simplePagination.js" type="text/javascript"></script>

<script src="./resources/javascript/serial.js"></script>
<script src="http://www.amcharts.com/lib/3/themes/light.js"></script>
<!-- <script src="./resources/javascript/Helios_Customer.js" type="text/javascript"></script> -->

<script type="text/javascript" charset="utf8" src="./resources/javascript/maintainUserRole.js"></script>
<script src="./resources/javascript/user-activity.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery.cookie.js" type="text/javascript"></script>
<script src="./resources/javascript/global-default-exception-handler.js" type="text/javascript"></script>

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
	   } 
	   });
$("#growthId").click(function(){
			populateGrowthData();
		     var cls=$("#growthSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#growthSpanId").attr("class","glyphicon glyphicon-minus")
			  $("#retentionSpanId").attr("class","glyphicon glyphicon-plus")
			  $("#expansionSpanId").attr("class","glyphicon glyphicon-plus")
			  }else{
			  $("#growthSpanId").attr("class","glyphicon glyphicon-plus")
			  }
		  });
		  $("#retentionId").click(function(){
			  populateRetentionData();
		     var cls=$("#retentionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#retentionSpanId").attr("class","glyphicon glyphicon-minus")
			  $("#growthSpanId").attr("class","glyphicon glyphicon-plus")
			  $("#expansionSpanId").attr("class","glyphicon glyphicon-plus")
			  }else{
			  $("#retentionSpanId").attr("class","glyphicon glyphicon-plus")
			  }
		  });
		  $("#expansionId").click(function(){
			  populateExpansionData()
		     var cls=$("#expansionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#expansionSpanId").attr("class","glyphicon glyphicon-minus")
			  $("#growthSpanId").attr("class","glyphicon glyphicon-plus")
			  $("#retentionSpanId").attr("class","glyphicon glyphicon-plus")
			  }else{
			  $("#expansionSpanId").attr("class","glyphicon glyphicon-plus")
			  }
		  });	
	$('.modal-dialog').draggable({
	    handle: ".modal-header"
	});
	var playData='';
	 $("#tglId").click(function(){
		var val=$("#stplId").html();
		if(undefined !=val && ''!=val){
			$("#stplId").html("");
			$("#notiId").html("");
			$("#gId").html("");
			$("#rId").html("");
			$("#eId").html("");
			$('ul li').css("padding-left","0px");
			$('ul li a[id^=menuItemIcon]').css("display","block");
			$('ul li a[id^=menuItemId]').css("display","none");
			$('#playSection').html("");
			$('#playSection').css("display","none");
			$("#tglId").css("padding-top","50px");
		}else{
			$("#stplId").html("STAPLES");
			$("#notiId").html("PLAYS");
			$("#gId").html("Growth");
			$("#rId").html("Retention");
			$("#eId").html("Expansion");
			$('ul li').css("padding-left","20px");
			$('ul li a[id^=menuItemIcon]').css("display","none");
			$('ul li a[id^=menuItemId]').css("display","block");
			$('#playSection').html(playSecData);
			$('#playSection').css("display","block");
			$("#tglId").css("padding-top","0px");
			$("#growthId").click(function(){
						var Gclass=$("#growthId").attr("class");
						if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
							$("#gIconId").attr("class","glyphicon glyphicon-minus")
						}else if(undefined != Gclass &&  '' ==Gclass){
							$("#gIconId").attr("class","glyphicon glyphicon-plus")
						}
					});
			 $("#retentionId").click(function(){
					var Gclass=$("#retentionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#rIconId").attr("class","glyphicon glyphicon-minus")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#rIconId").attr("class","glyphicon glyphicon-plus")
					}
				});
				 $("#expansionId").click(function(){
					var Gclass=$("#expansionId").attr("class");
					if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
						$("#eIconId").attr("class","glyphicon glyphicon-minus")
					}else if(undefined != Gclass &&  '' ==Gclass){
						$("#eIconId").attr("class","glyphicon glyphicon-plus")
					}
				})
		}
	}); 
	$("input[name='query']").click(function(){
		$(".search-form.search-form-expanded").css("width","230px");
	});
	
	$("ul li a[id^=menuItemId]").click(function(){
		$("ul li a[id^=menuItemId]").css("background-color","#1d2939");
		$("ul li a[id^=menuItemId]").css("color","#ddd");
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
	});
	
	
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

