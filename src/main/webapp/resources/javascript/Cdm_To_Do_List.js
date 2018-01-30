
/**
 * Lomesh Changes 
 */
var ctx = $("#svsURL").val();
var selectedSubPlays = 'All';
var selectedRow = [];
var fsc_yr = '';
var fsc_prd = '';
var fsc_wk = '';
var fsc_dy = '';
var latestFiscalContactedDate='';
var oldCheckedIds=[];
var timeZonesArr=new Array();
var timeZonesMap = new Object();
var tempTzIds=new Array();
var rowIndex=0;
var refresh='Y';
var tenfoldScheduledTimeArr=[];
var specialElementHandlers = {
	'#editor' : function(element, renderer) {
		return true;
	}
};

 $(document)
		.ready(
				function() {
					
					onPageLoad();
					$("[data-toggle='tooltip']").tooltip({html:true});
					$('#label-toggle-switch').on('click', function(e, data) {
				        $('.label-toggle-switch').bootstrapSwitch('toggleState');
				    });
				    $('.label-toggle-switch').on('switch-change', function (e, data) {
				    });
				    $('.toggle-handle').click(function(){
				    	if(undefined!=this && ''!=this && undefined!=this.id){
				    		var ele=$('.toggle-handle');
				    		if($(this).parent().parent().hasClass('off')){
				    			$('#alertState').val('ON');
				    			enableDisableAlerts('enable',ele);
						    	var filterBy = $('#filterBy').val();
						    	 rowIndex=0;
							    populateCdmDataOnLoad("DashBoard",filterBy);
							   
				    		}else{
				    			$('#alertState').val('OFF');
				    			enableDisableAlerts('disable',ele);
						    	var filterBy = $('#filterBy').val();
						    	   rowIndex=0;
							    populateCdmDataOnLoad("DashBoard",filterBy);
				    			
				    		}
				    	}
				    });
				    $(".toggle-on , .toggle-off").click(function(e) {
				     e.stopPropagation();
				    });
				    $("#bellIcon").click(function(e) {
				    	var ele=$('.toggle-handle');
					    if($(this).hasClass('enableIcon')){
					    	$('#alertCheckId').bootstrapToggle('off');
					    	$('#alertState').val('OFF');
					    	enableDisableAlerts('disable',ele);
					    	var filterBy = $('#filterBy').val();
					    	 rowIndex=0;
						    populateCdmDataOnLoad("DashBoard",filterBy); 
					    }else if($(this).hasClass('disableIcon')){
					    	$('#alertCheckId').bootstrapToggle('on');
					    	$('#alertState').val('ON');
					    	enableDisableAlerts('enable',ele);
					    	var filterBy = $('#filterBy').val();
					    	 rowIndex=0;
						    populateCdmDataOnLoad("DashBoard",filterBy);
					    }
					    	
				    });
				    /*alert($('#alertState').val());*/
				    if(undefined!=$('#alertState').val() && ''!=$('#alertState').val() && 'ON'==$('#alertState').val()){
				    	var elem=$('.toggle-handle');
				    	$('#alertCheckId').bootstrapToggle('on');
				    	$('#alertState').val('ON');
				        enableDisableAlerts('enable',elem);
				    }
				    configLastLiveTab();
				    $('div.blink').each(function() { 
				         var elem = $(this);
				         setInterval(function() {
				             if (elem.css('background-color') == 'rgb(40, 40, 40)') { 
				                 elem.css('background-color', '#000'); //I'm changing the background color, you may hide and show the div as well.
				                 $('#directionIcon').css('display','block');
				             } else {
				                 elem.css('background-color', 'rgb(40, 40, 40)');
				                 $('#directionIcon').css('display','none');
				             }
				         }, 800);
				     }); 
				});
 
 function onPageLoad(){

		$("#tabs").tabs(
				{
					activate : function(event, ui) {
						var active = $('#tabs').tabs('option',
								'active');
						var id = $("#tabs ul>li a").eq(active)
								.attr("href");
						if (undefined != id
								&& id == '#tab_default_2') {
							if ($("#users").is(":visible"))
								$("#viewAllCustomers").css(
										"display", "block");
							else
								$("#viewAllCustomers").css(
										"display", "none");
						} else
							$("#viewAllCustomers").css("display",
									"none");
					}
				});
		$("#li8").css("visibility", "visible");
		var tabHeight = $("#samLiId").height() + 5 + "px";
		$("#mmLiId").css("height", tabHeight);
		var repRoleCodeVal = $("#repRoleCode").val();
		var assignType = $("#assignType").val();
		var AM_SAM = false;
		var BOTH_MM_SAM = false;
		var OTHER_SAM = false;
		var OTHER_MM = false;
		var OTHER_SAMD = false;
		var OTHER_MM_CDM=false;
		if(undefined != assignType && assignType=='OTHER_MM_WITH_CDM_REP'){
			OTHER_MM_CDM=true;
			$("#tab_default_2").css("display", "block");
		}

		$("#samLiId").click(function() {
			$("#viewAllCustomers").css("display", "none");
		});
		$("#samNewLiId").click(function() {
			$("#viewAllCustomers").css("display", "none");
		});
		if ($("#users").is(":visible"))
			$("#viewAllCustomers").css("display", "block");
		else
			$("#viewAllCustomers").css("display", "none");
		$("#loggedInUserNameSpan").html(
				$("#loggedInUserName").val());
		if ($(window).width() <= 768) {
			$("thead#custHeader th")
					.css("vertical-align", "middle");
		}
		$('#dataTables-CustInfo').DataTable(
				{
					"ordering": false,
					"bFilter" : false,               
					"bLengthChange": false,
					"dom" : '<"pull-right"f>rt<"bottom"ip>',
					"oLanguage" : {
						"sSearch" : "Filter : "
					},
					"processing" : true,
					"responsive" : true,
					"bDestroy" : true,
					"paging" : true,
					"order" : [],
					"pageLength": 15,
					"aoColumns" : [ {
						"bSortable" : false
					},  {
						"bSortable" : false
					}, {
						"bSortable" : false
					}]

				});
		var table = $('#dataTables-CustInfo').DataTable();

		$('#next').on('click', function() {
			table.page('next').draw('page');
			
		});

		$('#previous').on('click', function() {
			table.page('previous').draw('page');
			
		});
		$('#dataTables-CustInfo').on('preXhr.dt',
				function(e, settings, data) {
                  //$('#RefreshTimeStamp').html('');
				});
		$('#dataTables-CustInfo').on(
				'xhr.dt',
				function(e, settings, json, xhr) {
					//logUserActivityWithCallBack(8047, 'user has refresh the page','');
				});
		
		$("#searchTextId").keyup( function(){ 
			$("#dataTables-CustInfo_filter input").val( $(this).val() ); 
			rowIndex=0;
			$("#dataTables-CustInfo_filter input").keyup(); 
		} ); 
		$('#searchIconId').click(function(){
			if($(this).hasClass('glyphicon-remove')){
				var filterBy = $('#filterBy').val();
				$("#searchTextId").val(''); 
				$("#dataTables-CustInfo_filter input").val('');
				$("#dataTables-CustInfo_filter input").keyup(); 
				$(this).removeClass('glyphicon-remove').addClass('glyphicon-search')
				//populateCdmDataOnLoad("DashBoard",filterBy);
			}
		});
		/* CDM Table Configure Start */
		var filterBy = $('#filterBy').val();
		var from = $('#from').val();
		if ((filterBy == undefined || filterBy == null
				|| filterBy == 'ALL' || filterBy == '') && (from=='home')) { 
			populateCdmDataOnLoad("home",'ALL'); 
		}else if(from=='DashBoard'){
			populateCdmDataOnLoad("DashBoard",filterBy);
		}
		getLatestFiscalContactedDate();
		var aboutData = '<div class="modal-header" style="padding:10px 15px;"><h4 class="modal-title modal-tit-pop" style="">Help &amp; Training<span id="uName"></span></h4>'
	        +'</div><div style="text-align:justify;font-size: 12px;color: #4d4d4d;font-family: Helvetica;"><div class="col-md-12 margin-bottom-10"><div class="widget-bg-color-white widget-tab rounded-3">'
			+'<div class=""><div class="notice notice-danger"> <strong style="color: #012c43;line-height: 3;font-size:16px;"> Help </strong>' 
	        +'<ul class="" style="margin-left:0px;"><li class="modal-li-pop" id="faqli1" style="">'
							+'<span class="badge modal-icon-pop">1</span> <a href="mailto:helios_sa_appsupt@staples.com" class="modal-link-cls-pop" style="text-decoration:underline;" id="faqa1">Help Desk</a><span> (Helios technical support)</span>'
						+'</li><li class="modal-li-pop" id="faqli3" style="">'
							+'<span class="badge modal-icon-pop">2</span><a href="mailto:helios_sa_bussupt@staples.com" class="modal-link-cls-pop" style="text-decoration:underline;" id="faqa3">Suggestions/Ideas</a><span></span>'
						+'</li></ul></div>'
						+'<div class="notice notice-sm" style="padding-top: 15px;border:none;"><div style="float: left;width: 13%;height: 50px !important;"><strong style="color: #012c43;font-size:16px;">Note</strong><b>:</b></div> <div> If you are having trouble accessing Helios or any of the Helios functions, make sure you are using Chrome browser and clear the cache. <a href="#" onclick="javascript:showCachePopUp()" class="modal-link-cls-pop" style="text-decoration:underline;margin-left:0%;" id="note">Click here</a> for more details. </div></div>'
						+'<hr style="margin-top: 15px !important;margin: 0;border: none;border-top: 1px solid #e5e5e5;">'
	    +'<div class="notice notice-warning">'
	      +'  <strong style="color: #012c43;line-height: 3;font-size:16px;">Training</strong>' 
	        +'<ul class="" style="margin-left:0px;">'
				+'		<li class="modal-li-pop" id="li1" style="">'
					       +'<span class="badge modal-icon-pop">1</span><a href="./resources/docs/AM1_Call_Order_Ranking .pdf" class="modal-link-cls-pop" style="text-decoration:underline;" id="a1" download="AM1_Call_Order_Ranking .pdf">Call Order Ranking Logic</a><span> (Last updated June &#39;17)</span>'
						+'</li><li class="modal-li-pop" id="li1" class="modal-link-cls-pop" style="">'
							+'<span class="badge modal-icon-pop">2</span><a href="./resources/docs/AM1_Quick_Reference_Guide.pdf" class="modal-link-cls-pop" style="text-decoration:underline;" id="a1" download="AM1 Training Guidee.pdf">AM1 Training Guide</a><span> (Last updated June &#39;17)</span>'
						+'</li><li class="modal-li-pop" id="li4" style="margin-right:0px;padding-right:0px;">'
							+'<span class="badge modal-icon-pop">3</span><a href="./resources/docs/Cisco Any Connect APP Installation (for ipad).pdf" class="modal-link-cls-pop" style="text-decoration:underline;" id="a4" download="Cisco Any Connect APP Installation (for ipad).pdf">Cisco Any Connect APP Installation of ipad</a><span> (Last updated June &#39;17)</span>'
						+'</li></ul></div>'
	    +'</div></div></div>'
	    +'<div class="modal-footer col-md-12 modal-foot-pop" style="margin-top: 15px;padding: 4px;"><button type="button" id="cancelPopover" class="btn btn-sm modal-cls-pop" style="color:#fff;outline:0;">CLOSE</button></div>'
	   +'</div>';
		var tierPopData = '<div style="text-align:justify;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> In Progress......</div>';

		var $popup=$('#aboutId').popover({
			html : true,
			placement : 'bottom',
			content : aboutData
		}).on('shown.bs.popover', function () {
			var $popup = $(this);
			$(this).next('.popover').find('button#cancelPopover').click(function (e) {
		        $popup.popover('hide');
		    });
		});
		$('#tierId')
				.on(
						"click",
						function(event) {
							url = './resources/docs/Member Rewards Benefits.pdf#zoom=70,0,0&scrollbar=1';
							var win = window.open(url, '_blank');
							win.focus();
							event.stopImmediatePropagation();
						});
		$('#searchResults').click(function() {
			$('html, body').animate({
				scrollTop : $("#rewardsSearchResults").offset().top
			}, 2000);
			return false;
		});
		deleteCoookie();
		
		
		$(".lastLive").addClass('active');
		$(".lastLive").css('background-color','#ffffff');
		$(".lastLive").children(':first').addClass("lastliveHover");
		$('.lastLive').siblings('a.active').removeClass("active");
     $('.lastLive').siblings('a').css('background-color','#012c43');
     $('.talkingPoint').children(':first').addClass("talkingPointHoverout")
     $('.accountCon').children(':first').addClass("accountConHoverout")
     
		 $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
		        e.preventDefault();
		        $(this).siblings('a.active').removeClass("active");
		        $(this).siblings('a').css('background-color','#012c43');
		        $(this).addClass("active");
		        $(this).css('background-color','#ffffff');
		        if($(this).hasClass("lastLive") && $(this).hasClass("active"))
		        	$(this).children(':first').addClass("lastliveHover").removeClass('lastliveHoverout')
		        else{
		        	$('.lastLive').children(':first').addClass("lastliveHoverout").removeClass('lastliveHover')
		        }
		        if($(this).hasClass("talkingPoint") && $(this).hasClass("active"))
		        	$(this).children(':first').addClass("talkingPointHover").removeClass('talkingPointHoverout')
		        else
		        	$('.talkingPoint').children(':first').addClass("talkingPointHoverout").removeClass('talkingPointHover')
		        if($(this).hasClass("accountCon") && $(this).hasClass("active"))
		        	$(this).children(':first').addClass("accountConHover").removeClass('accountConHoverout')
		        else
		        	$('.accountCon').children(':first').addClass("accountConHoverout").removeClass('accountConHover')
		        	
		        var index = $(this).index();
		        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
		        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
		        var custNum=$('#customerId').html();
		        if($(this).hasClass('lastLive')==true && undefined !=custNum && ''!=custNum){
		        	logChangeSatusActivity(8001, 'To Do v2 - Viewed Last Live Contact',custNum);
		        	$('#LastTabLableOne').click();
		        }   else if($(this).hasClass('talkingPoint')==true){
		        	logChangeSatusActivity(8002, 'To Do v2 - Viewed Talking Points',((undefined!=custNum)?custNum:""));
		        	var custId= $('#customerId').html();
		        	populateCtaDetails(custId);
		        	hideCtaProcessing();
		        }else if($(this).hasClass('accountCon')==true){
		        	logChangeSatusActivity(8003, 'To Do v2 - Viewed Account Contacts',((undefined!=custNum)?custNum:""));
		        }else if($(this).hasClass('alertIconEnable')==true){
		        	logChangeSatusActivity(8042, 'To Do v2 - Viewed Account Alert Tab',custNum);
		        	if(undefined !=$('#alertActiveStatus').html() && ''!= $('#alertActiveStatus').html() && 'A'==$('#alertActiveStatus').html()){
		        		updateAlertStatus(custNum);
		        	}
		        }
		    });
		 $('#dataTables-CustInfo tbody').on('click', 'tr', function () {
				var table = $('#dataTables-CustInfo').DataTable();
				var id = this.id;
				console.log(table.row(this).data());
				
				var jsonData=table.row(this).data();
		        $('[id^=row_]').removeClass('selected');
		        $(this).addClass('selected');
		        $('#custType').html((undefined!=jsonData['customerType']&& ''!=jsonData['customerType'])?jsonData['customerType'] : 'Not Available');
		        $('#customerId').html(jsonData['custNum']);
		      	$('#compNm').html(capitalizeAllWords(checkUundefined(jsonData['companyName'])));
		        
		        $('#segmentId').html((undefined!=jsonData['customerSegment'] && ''!=jsonData['customerSegment'])? jsonData['customerSegment'] : 'Not Available');
		        $('#subSegId').html(jsonData['vapScore']);
		        $('#ctaCategoryStatus').html('');
		        $('#ctaCategoryStatus').html(jsonData['segmentIdList']);
		        $('#division').val('');
		        $('#division').val(jsonData['division']);
		        var custId=jsonData['custNum'];
		        $('#viewAllInfo').attr('href','javascript:openCustDetails("'+custId+'","'+custId+'","AM-MM")');
		        var compWebsite=(undefined!=jsonData['compWebsite'] && ''!=jsonData['compWebsite'])? jsonData['compWebsite'] : 'Website not available';
		        $('#compWebsite').html('');
		        if(undefined!=compWebsite && ''!=compWebsite && 'Website not available'!=compWebsite){
		           $('#compWebsite').html('<a href="'+compWebsite+'" target="_blank" style="text-decoration:underline;" >'+compWebsite+'</a>')
		        }else
		           $('#compWebsite').html(compWebsite);
		        var alertCounts=(undefined!=jsonData['alertCount'] && ''!=jsonData['alertCount'])? jsonData['alertCount'] : '';
		        var alertdeleteCount=(undefined!=jsonData['alertDeleteCount'] && ''!=jsonData['alertDeleteCount'])? jsonData['alertDeleteCount'] : '';
		        if(undefined!= $('#iconLabel').html() && ''!=$('#iconLabel').html() && refresh=='N')
		         $('#iconLabel').html(checkUundefined($('#iconLabel').html()));
		        else
		         $('#iconLabel').html(checkUundefined(alertCounts));
		        if((undefined==alertdeleteCount || ''==alertdeleteCount || 0==alertdeleteCount))
		        	$('#toggleOnOffContainer').css('display','none');
		        else
		        	$('#toggleOnOffContainer').css('display','block');
		        if((undefined ==alertCounts || ''==alertCounts || 0==alertCounts) && refresh=='Y'){
		        	$('#iconLabel').css('display','none');
		        	$('#bellIcon').css('pointer-events','none');
		        }else if(undefined!= $('#iconLabel').html() && ''!=$('#iconLabel').html() && refresh=='N'){
		        	$('#bellIcon').css('pointer-events','auto');
		        	$('#iconLabel').css('display','block');
		        	$('#iconLabel').html(checkUundefined($('#iconLabel').html()));
		        	
		        }else{
		        	$('#bellIcon').css('pointer-events','auto');
		        	$('#iconLabel').css('display','block');
		        	$('#iconLabel').html(checkUundefined(alertCounts));
		        }
		        var isAlertActive=(undefined!=jsonData['isActiveAlert'] && ''!=jsonData['isActiveAlert'])? jsonData['isActiveAlert'] : '';
		        if((undefined !=isAlertActive && ''==isAlertActive) && 'A'!=isAlertActive){
		        	$('#alertContainer').html("");
		        	$('#alertActiveStatus').html("");
		        	$('#alertContainer').append('<div id="alertNotFound" style="padding-top: 25%;text-align: -webkit-center;height: 500px;color: #c7c7c7;font-size: 16px;font-family: helvetica;font-weight: 600;display:block !important;"><img src="./resources/img/unhappyPath.png"></div>');
		        }else{
		        	$('#alertContainer').html("");
		        	$('#alertActiveStatus').html(isAlertActive);
		        }
		        var iamId=jsonData['iam_Id'];
		        var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
		        var cnoUrl=sfdcBaseUrl+'/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+iamId+'%26accid%3D'+iamId;
		        $("#ctaCreateOpp-Churn").attr("href",cnoUrl);
		        $("#ctaCreateOpp-Decliner").attr("href",cnoUrl);
		        $("#ctaCreateOpp-otherprofile").attr("href",cnoUrl);
		        
		        getLastLiveContactDetails(iamId,custId)
		        $("div.bhoechie-tab-menu>div.list-group>a:first").click();
		        getAccountContactDetails(custId,iamId);
		        if(undefined!=id && ''!=id && id.indexOf('_')!=-1){ 
		        	rowIndex=id.split('_')[1];
		        }
		        else{
		        	rowIndex=0;
		        }
		        $('#LastTabLableOne').click();
		        console.log(rowIndex);
		    } );
		 $('#ui-id-1,#ui-id-2').click(function(){ 
			 $(this).show();
			 if(undefined!=this && undefined!=this.id && (this.id)== 'ui-id-2'){
				 if($('div[aria-labelledby="ui-id-1"]').is(":visible") || $('div[aria-labelledby="ui-id-3"]').is(":visible")){
					 $('div[aria-labelledby="ui-id-1"]').css('display','none');
					 $('div[aria-labelledby="ui-id-3"]').css('display','none');
				 $('div[aria-labelledby="ui-id-2"]').css('display','block');
				 }
			 }else if(undefined!=this && undefined!=this.id && (this.id) == 'ui-id-1'){ 
				 if($('div[aria-labelledby="ui-id-2"]').is(":visible") || $('div[aria-labelledby="ui-id-3"]').is(":visible")){
					 $('div[aria-labelledby="ui-id-2"]').css('display','none');
					 $('div[aria-labelledby="ui-id-3"]').css('display','none');
				 $('div[aria-labelledby="ui-id-1"]').css('display','block');
				 }
			 }else if(undefined!=this && undefined!=this.id && (this.id) == 'ui-id-3'){ 
				 if($('div[aria-labelledby="ui-id-1"]').is(":visible") || $('div[aria-labelledby="ui-id-2"]').is(":visible")){
					 $('div[aria-labelledby="ui-id-1"]').css('display','none');
					 $('div[aria-labelledby="ui-id-2"]').css('display','none');
				 $('div[aria-labelledby="ui-id-3"]').css('display','block');
				 }
			 }
		 })
						
		 logUserActivity(8041, 'View Customer List V2 / To Do List V2');
 }

 function getAllAvailableTimezone(from){
	 var isNAFound='N';
	 var timeZoneData ='<div class="modal-header" style="padding: 10px 7px;"><h6 class="modal-title modal-tit-pop" style=" text-transform: none;font-size: 16px;">Select Time Zones</h6></div>'
		 +'<ul class="">';
	
	 if(undefined!=timeZonesMap && ''!=timeZonesMap){
		 $.map(timeZonesMap,function(Val,item){
			 var val=(undefined!=Val && Val.indexOf('-')!=-1)?Val.split('-')[1]:Val;
			 var tz_id='tz_'+item;
			 if(item=='NA' || item=='na')
			 isNAFound='Y'
			 else
			 timeZoneData +='<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="'+tz_id+'"> <span class="cr"><i class="cr-icon fa fa-check"></i></span><span>'+val+'</span></label> </div></li>';	 
		 });
		 if(isNAFound=='Y')
			 timeZoneData +='<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="tz_NA"> <span class="cr"><i class="cr-icon fa fa-check"></i></span><span>Not Available (NA)</span></label> </div></li>';
	 }
	
	 timeZoneData +='<li class="modal-li-pop" style="width: 200px;"><div class="checkbox"> <label style="line-height: 1.6;font-size: 1.2em;">  <input type="checkbox" value="" id="tz_ALL"> <span class="cr"><i class="cr-icon fa fa-check"></i></span><span>Select All</span></label> </div></li>'
		 +'</ul>'
			+'<div class="modal-footer col-md-12 modal-foot-pop" style="text-align: right;margin-top: 15px;padding: 5px;"><button type="button" id="okTimezone" class="btn btn-sm modal-cls-pop" style="width: 35%;border-radius: 0px;border: 1px solid #fff;color:#fff;outline:0;padding: 4px;font-size:12px;">OK</button>'
			+'<button type="button" id="cancelTimezone" class="btn btn-sm modal-cls-pop" style="width: 35%;border-radius: 1px;border: 1px solid #fff;color:#fff;outline:0;padding: 4px;font-size:12px;">CANCEL</button></div>';
	$('div#timeZoneContent').html(timeZoneData);
	var totalTzCount=$('[id^=tz_]').not('[id=tz_ALL]').length;
	$('#timeZoneFilter').popover({
		html : true,
		placement : 'bottom',
		content : $('div#timeZoneContent').html()
	}).on('shown.bs.popover', function () {
		if((undefined!=from &&''!=from) && ('home'==from && tempTzIds.length<=0) || ('DashBoard'==from && $('#filterBy').val()=='ALL')){
			$('input[id^=tz_]').prop('checked',true);
			$('input[id^=tz_]').next('span.cr').css('background-color','#012c43');$('input[id^=tz_]').next('span.cr').find('i').css('color','#fff');
		}
		else if(''!=tempTzIds && tempTzIds.length >0 && from=='home'){
			$.each(tempTzIds,function(i,item){
				$('input[id='+item+']').prop('checked',true);
				$('input[id='+item+']').next('span.cr').css('background-color','#012c43');$('input[id='+item+']').next('span.cr').find('i').css('color','#fff');
			});
		}else if(from=='DashBoard'){
			tempTzIds=($('#filterBy').val()).split(",");
			$.each(tempTzIds,function(i,item){
				$('input[id=tz_'+item+']').prop('checked',true);
				$('input[id=tz_'+item+']').next('span.cr').css('background-color','#012c43');$('input[id=tz_'+item+']').next('span.cr').find('i').css('color','#fff');
			});
		}
		$('input[id^=tz_]').click(function(){ 
			
			var chkboxId=$(this).attr('id')
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId!='tz_ALL' && $(this).prop('checked')==false)
			{ 	$('input[id^=tz_ALL]').prop('checked',false);$('input[id^=tz_ALL]').next('span.cr').css('background-color','#d6d6d6');$(this).next('span.cr').css('background-color','#d6d6d6');}
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId=='tz_ALL' && $(this).prop('checked')==true)
			{ 	$('input[id^=tz_]').prop('checked',true); $('input[id^=tz_]').next('span.cr').css('background-color','#012c43');$('input[id^=tz_]').next('span.cr').find('i').css('color','#fff');}
			else if(undefined !=chkboxId && ''!=chkboxId && chkboxId=='tz_ALL' && $(this).prop('checked')==false)
			{ $('input[id^=tz_]').prop('checked',false);  }
			var totalCheckedCount=$('#timeZoneFilter').next('.popover').find('input[id^=tz_][type="checkbox"]:checked').length;
			if(undefined!=totalCheckedCount && ''!=totalCheckedCount && undefined!=totalTzCount && ''!=totalTzCount && (totalTzCount == totalCheckedCount)){
			 $('input[id^=tz_]').prop('checked',true);	$('input[id^=tz_]').next('span.cr').css('background-color','#012c43');$('input[id^=tz_]').next('span.cr').find('i').css('color','#fff');
			}
			if(undefined !=chkboxId && ''!=chkboxId && chkboxId!='tz_ALL' && $(this).prop('checked')==true){
				$(this).next('span.cr').css('background-color','#012c43');$(this).next('span.cr').find('i').css('color','#fff');
			}
			if(undefined!=totalCheckedCount && '0'== totalCheckedCount && chkboxId!='tz_ALL'){
			 alert('please select at least one timezone !!');
			 $(this).prop('checked',true);
			 $(this).next('span.cr').css('background-color','#012c43');
			 $(this).next('span.cr').find('i').css('color','#fff');
			 return;
			}else if(undefined!=totalCheckedCount && '0'== totalCheckedCount && chkboxId=='tz_ALL' && $(this).prop('checked')==false){
				alert('please select at least one timezone !!');
				$('input[id^=tz_]').prop('checked',true);
				 return;
			}
		});
		var $popup = $(this);
		$(this).next('.popover').find('button#okTimezone').unbind('click');
		$(this).next('.popover').find('button#okTimezone').click(function (e) {
			var timeZoneList='';
			var tempTz=new Array();
			tempTzIds=[];
			var selectedTimeZone=$('#timeZoneFilter').next('.popover').find('input[id^=tz_][type="checkbox"]:checked');
			$.each(selectedTimeZone,function(i,item){
				tempTz[i]=(item.id).replace('tz_','');
				tempTzIds[i]=(item.id);
			});
			if(undefined !=tempTz && ''!=tempTz && (tempTz.indexOf("ALL")!=-1))
				timeZoneList='ALL';
			else if(undefined !=tempTz && ''!=tempTz && (tempTz.indexOf("ALL")==-1))
				timeZoneList=tempTz.join()
	        $popup.popover('hide');
	        $("#filterBy").val(timeZoneList);
	        rowIndex=0;
	        populateCdmDataOnLoad("TimeZoneOk",timeZoneList);
	    });
		$(this).next('.popover').find('button#cancelTimezone').click(function (e) {
	        $popup.popover('hide');
	    });
	}).on("mouseleave", function () {
       
	});
 }
 function AnimateCircle(container_id, animatePercentage) {
	    $('#'+container_id).html('');
	    var trailcolor='#eee';
	    var startColor = '#012c43';
	    var endColor = '#012c43';
	    var val= parseInt(animatePercentage * 100);
	    var element = document.getElementById(container_id);
	    if(animatePercentage>1)
	    trailcolor='#012c43';
	    var circle = new ProgressBar.Circle(element, {
	        color: startColor,
	        trailColor: trailcolor,
	        trailWidth: 5,
	        duration: 2000,
	        easing: 'bounce',
	        strokeWidth: 5,
	        text: {
	            value: val + "%",
	            className: 'progressbar__label'
	        },
	        step: function (state, circle) {
	        	if(animatePercentage>1)
	                circle.path.setAttribute('stroke', '#012c43');
	        	else
	        		circle.path.setAttribute('stroke', state.color);
	        }
	    });

	    circle.animate(animatePercentage, {
	        from: {
	            color: startColor
	        },
	        to: {
	            color: endColor
	        }
	    });
	}
 function initializeProgressBar(value){
	 $('#circleContainer').html('');
	var bar = new ProgressBar.Circle(circleContainer, {
		color: '#FFFFFF',
	  strokeWidth: 10,
	  trailWidth: 5,
	  easing: 'easeInOut',
	  duration: 1400,
	  text: {
	    autoStyleContainer: false
	  },
	  from: { color: '#003366', width: 1 },
	  to: { color: '#003366', width: 4 },
	  // Set default step function for all animate calls
	  step: function(state, circle) {
	    circle.path.setAttribute('stroke', state.color);
	    circle.path.setAttribute('stroke-width', state.width);
	     circle.setText(value);
	  }
	});
	bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
	bar.text.style.fontSize = '2rem';

	bar.animate(value/100);  // Number from 0.0 to 1.0
}
 
 function getDayActionedMaps(dayArr,AccCountArr){
	
	  var labels = dayArr;
		var values = AccCountArr;
		var count=-1;
		return labels.map(function(days){
			
		   count++;
			return { "category": days, "column-1": AccCountArr[count] };
		});
 }
function popChartData(dayArr,AccArr){
	

	var weeklyBarData=[];
	weeklyBarData=getDayActionedMaps(dayArr,AccArr);
	var chart=AmCharts.makeChart("chartdiv",
			{
		"type": "serial",
		"categoryField": "category",
		"autoMarginOffset": 0,
		"colors": [	"#FFFF00", "#FCD202","#B0DE09", "#0D8ECF","#2A0CD0","#CD0D74","#CC0000","#00CC00","#0000CC","#DDDDDD","#999999","#333333","#990000"],
		"startDuration": 1,
		"color": "#FFFFFF",
		"fontSize": 10,
		"handDrawScatter": 1,
		"handDrawThickness": 2,
		"theme": "default",
		"categoryAxis": {
			"gridPosition": "start",
				"axisColor": "#FFFFFF"
		},
		"trendLines": [],
		"graphs": [
			{
				"balloonText": "<span style='color:#000000;font-size:12px;font-family:Helvetica;line-height:1.4;padding-left:7px;'>[[column-1]] Actioned</span>",
				"bullet": "round",
				"bulletBorderAlpha": 1,
				"bulletColor": "#FFFFFF",
				"bulletSize": 5,
				"hideBulletsCount": 50,
				"id": "AmGraph-1",
				"lineThickness": 2,
				"title": "red line",
				"useLineColorForBulletBorder": true,
				"valueField": "column-1"
			}
		],
		"guides": [],
		"valueAxes": [
			{
				"id": "ValueAxis-1",
				"title": "",
				"axisColor": "#FFFFFF",
				"labelFunction" : function(value, valueText, valueAxis){
						valueAxis.autoGridCount = false;
						valueAxis.minimum = 0;
						valueAxis.maximum = 100;
						valueAxis.gridCount = 4;
					return  (value*2)+'%';
				}
			}
		],
		"allLabels": [],
		"balloon": {},
		"titles": [],
		"dataProvider": weeklyBarData
	}
		);

}
 function formatValue(value, formattedValue, valueAxis){
	 if(value === 0){
	        return "0";
	    }
	    else if(value > 50){
	        return value+"";
	    }
	    else if (value > 100){
	        return value+"a lot!";
	    }
	    else{
	        return "";
	    }
 }
   

   function populateCdmDataOnLoad(from,timezone){
	   refresh='Y';
		  var custid=$("#accId").val();
			 if(undefined !=from && ''!=from && "home" ==from) {
				 timezone='ALL';
				 $('#filterBy').val('ALL');
			 }else if(undefined !=from && ''!=from && "DashBoard" ==from) {
				 $('#filterBy').val(timezone);
			 }
			 var count=1;
			 var agentEmailId='';
			 var flag='';
			 var test="k";
			 var repRoleCode=$("#repRoleCode").val();
			 var accType=$("#accTypeVal").html();
			 $('#dataTables-CustInfo').dataTable( {
			        "bDestroy" : true,
			       "sAjaxSource": ctx+"/getAllCdmCustomers/"+custid+'/'+timezone ,
			       "fnServerParams": function (aoData) { 
			    	   aoData.push({ "name": "alertStateStatus", "value": $('#alertState').val()});
			    	   aoData.push({ "name": "rowId", "value": '2'}); 
			    	   aoData.push({ "name": "repRoleCode", "value": repRoleCode});
			    	   },  
					"bServerSide" : true,
					"responsive": true,
					"oLanguage": { "sSearch": "Filter : "},
					"bLengthChange" : true,
					"order": [],
					"processing":true,
					"paging" :true,
					"bInfo" : true,
					"dataType": 'jsonp',
					'sServerMethod' : "POST",
					'bStateSave':true,
					'rowId': 'staffId',
					"pageLength": 15,
					"bLengthChange": false,
					'stateSaveParams':function(settings, data){

					},
					 "initComplete": function (oSettings) {
						
					  },
					"fnDrawCallback": function ( oSettings) {
						
						var searchVal=$("input[type=search]").val();
						if(undefined !=searchVal && ''!=searchVal){
							$('#searchTextId').val(searchVal);
							$('#searchIconId').removeClass('glyphicon-search').addClass('glyphicon-remove')
						}
						if(undefined !=oSettings && ''!=oSettings && undefined !=oSettings.json && ''!=oSettings.json && undefined!= oSettings.json.timeZoneMap && ''!=oSettings.json.timeZoneMap){
							console.log(oSettings.json.timeZoneMap);
							timeZonesMap =oSettings.json.timeZoneMap;
						}

						var frm=$('#from').val();
						getAllAvailableTimezone(frm);
						if(undefined!=$('#dataTables_empty') && undefined != $('.dataTables_empty').html()){
							var emptyStatus= $('.dataTables_empty').html();
						    if(undefined!=emptyStatus &&''!=emptyStatus && emptyStatus.indexOf('Holdout Test')!=-1 ){
							  $('div#rightContainer').css('display','none');
							  $('.dataTables_empty').attr('style','border:none !important;');
							  $('#alertIconContainer').css('display','none');
							  $('#toggleOnOffContainer').css('display','none');
						    }
						    else{
						    	 $('div#rightContainer').css('display','block');
						    	 $('#alertIconContainer').css('display','block');
						    	 $('#toggleOnOffContainer').css('display','block');
						    }
						}else{
							$('div#rightContainer').css('display','block');
							$('div#rightContainer').css('display','block');
					    	 $('#alertIconContainer').css('display','block');
						}
						var to_do_list_height=$('table#dataTables-CustInfo tbody').height();
						$('#rightContainer').css('height',to_do_list_height+'px');
						if(undefined!=rowIndex &&''!=rowIndex)
							$('#row_'+rowIndex).click();
						else
							$('#row_0').click();
						$('#dataTables-CustInfo_wrapper .dataTables_scrollBody').scrollTop(0);
						if(undefined != agentEmailId && agentEmailId !=''){
							var agentName=agentEmailId.toString();
							
							if(undefined !=flag && flag!='' && flag=='TRUE'){
							$("span#agentName").html(agentName+"'S TO DO LIST");
							}else if(undefined !=flag && flag!='' && flag=='DM'){
								
								$("span#agentName").html(agentName+"'S LIST");
							}
							else{
								$("span#agentName").html(agentName+"'S LIST");
								$("#filterByPlay").hide();
								$("#playSecDiv").hide();
								$("#subPlayIdDiv").hide();
								$("#filterBySubPlay").hide();
								$(".col-lg-12").css("height","35px");
								
							} 
						}
						var oSettings = this.fnSettings();
						var iTotalRecords = oSettings.fnRecordsTotal();
						if(undefined==$('#cdmTotalCount').val() || ''==$('#cdmTotalCount').val())
						$('#cdmTotalCount').val(iTotalRecords)
						generateTodaysProgress($('#cdmTotalCount').val(),fsc_yr,fsc_prd,fsc_wk,fsc_dy)
						 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Company Name OR Customer Number').css({'width':'200px','display':'inline-block'});
					},
					"rowCallback": function( row, data,index ) {
			            	$(row).attr("id","row_"+index);
			        },
					"aoColumns" : [
									
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
												var compNameUrl=sfdcBaseUrl+'/'+full.iam_Id
											 var call_order='<ul class="navbar-nav" id="callOrder" style="width:100%;">';
												if(undefined !=full.todaysProgress && ''!=full.todaysProgress && full.todaysProgress=='TRUE'){
													if(undefined!=full.callOrder && ''!=full.callOrder && '-'!=full.callOrder){
														call_order +='<li style="background-color: #f13c3c;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">'+checkUundefinedNullBlankZero(full.callOrder)+'</li>';
													}else{
														call_order +='<li style="background-color: #ff6600;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">NA</li>';
													}
													call_order += '<li style="background-color: #ff7900;width: 33%;height: 40px;padding-top: 2px;text-align: center;x"><a href="'+compNameUrl+'" target="_blank" onclick="javascript:logActivity(\''+full.custNum+'\')"><img src="./resources/img/cl_call.png" width="40" height="36" style=""></a></li>';
													if(undefined !=full.timeZone && ''!=full.timeZone)
											        	call_order +='<li class="tzCls" style="background-color: #ffbc8f;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">'+full.timeZone+'</li>';
											        else
											        	call_order +='<li class="tzCls" style="width: 40px;background-color: #ffbc8f;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">-</li>';
												}
												else if(undefined !=full.weakProgress && ''!=full.weakProgress && full.weakProgress=='TRUE'){
													if(undefined!=full.callOrder && ''!=full.callOrder && '-'!=full.callOrder){
														call_order +='<li style="background-color: #f13c3c;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">'+checkUundefinedNullBlankZero(full.callOrder)+'</li>';
													}else
														call_order +='<li style="background-color: #0099cc;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">NA</li>';
													call_order += '<li style="background-color: #389fcf;width: 33%;height: 40px;padding-top: 2px;text-align: center;x"><a href="'+compNameUrl+'" target="_blank" onclick="javascript:logActivity(\''+full.custNum+'\')"><img src="./resources/img/cl_call.png" width="40" height="36" style=""></a></li>';				   
													if(undefined !=full.timeZone && ''!=full.timeZone)
												    	call_order +='<li class="tzCls" style="background-color: #8fe2fe;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">'+full.timeZone+'</li>';
											        else
											        	call_order +='<li class="tzCls" style="width: 40px;background-color: #8fe2fe;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">-</li>';
												}
												else{
													if(undefined!=full.callOrder && ''!=full.callOrder && '-'!=full.callOrder){
														call_order +='<li style="background-color: #f13c3c;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">'+checkUundefinedNullBlankZero(full.callOrder)+'</li>';
													}else
														call_order +='<li style="background-color: #c7c7c7;width: 33%;height: 40px;padding-top: 7%;text-align: center;font-weight:bold;font-family:arialmt;">NA</li>';
													call_order += '<li style="background-color: #d0d1d1;width: 33%;height: 40px;padding-top: 2px;text-align: center;x"><a href="'+compNameUrl+'" target="_blank" onclick="javascript:logActivity(\''+full.custNum+'\')"><img src="./resources/img/cl_call.png" width="40" height="36" style=""></a></li>';	    
													if(undefined !=full.timeZone && ''!=full.timeZone)
												    	call_order +='<li class="tzCls" style="background-color: #dddddd;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">'+full.timeZone+'</li>';
											        else
											        	call_order +='<li class="tzCls" style="width: 40px;background-color: #dddddd;width: 33%;height: 40px;padding-top: 7%;text-align: center;color:#012c43;font-family:arialmt;" data-placement="top" data-toggle="tooltip" title="Based on Zip Code in Customer Profile">NA</li>';
												}
												call_order+='</ul>'
											 
											return call_order;
											
										 }
									},
									
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 14px !important;font-family: ARIALMT;white-space:normal;">' 
												+capitalizeAllWords(checkUundefined(full.companyName))+
												'</span>';
										 }
									},
									{    "bSortable": false,
										 "mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 14px !important;font-family: ARIALMT;">'
												+formatDecimal(checkUundefined(full.potentialCallVal),true)
												+'</span>';
										 }
										
									}
									],
					
									"oLanguage": {
										"sProcessing":'<div id="example_processing" class="dataTables_processing" style="position: fixed;height: 48px; width : 48px;top: 50%;left: 50%;margin-left: -80px; "><img width="60" height="60" src="./resources/img/cdm-loading.gif" style=""></div>',"sSearch":"Filter :",
										//"sProcessing": '<div id="example_processing" class="dataTables_processing" style="position: fixed;width: 500px;height: 200px;top: 50%;left: 50%;margin-top: -100px;margin-left: -300px; "><img width="60" height="60" src="./resources/img/cdm-loading.gif" style=""></div>',"sSearch":"Filter :",
										//"sProcessing": '<div id="example_processing" class="dataTables_processing" style="top:200%;height: 48px;position: relative;padding: 10px;margin-left: -80px;border: none;margin-top: 0%;"><img width="60" height="60" src="./resources/img/cdm-loading.gif" style=""></div>',"sSearch":"Filter :",
										"oPaginate": {
										   "sPrevious": "&lt;", 
										    "sNext": "&gt;"
										  },
										  "sEmptyTable":     "No results found or this account is currently part of Helios Holdout Test Group."
										}

			                 } );

			                        $("#dataTables-CustInfo td").css("WORD_BREAK","BREAK-ALL");
									$('#dataTables-CustInfo_length label').css("color","#004c74");
									$('#dataTables-CustInfo_length label').css("color","#004c74");
									$('#dataTables-CustInfo_length label').css("font-weight","600");
									$('#dataTables-CustInfo_filter label').css("color","#004c74");

									$('#dataTables-CustInfo_filter label').css("font-weight","600");
									$('#dataTables-CustInfo_info').css("color","#004c74");
									$('#dataTables-CustInfo_info').css("font-weight","600");
									$('#ddataTables-CustInfo_paginate').css("text-align","right");
									$('.form-inline .form-control').css("color","#004c74");
									$('.form-inline .form-control').css("border","1px solid #004c74");
									$('select .form-control.input-sm').css("border","1px solid #004c74");
									$("#dataTables-CustInfo_info").parent().parent().removeClass("row");
									$("#dataTables-CustInfo_info").parent().parent().css({"padding":"10px","border-top":"1px solid #e5e5e5","height":"70px"});
									
									 $('#dataTables-CustInfo').removeClass('display').addClass('table table-striped table-bordered');
									 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Company Name OR Customer Number').css({'width':'200px','display':'inline-block'});
									
									if($(window).width()>=768){ 
										
									$('#dataTables-CustInfo_info').attr("style","font-size:12px !important");
									}
		
									 if(undefined != agentEmailId && agentEmailId !=''){
											var agentName=agentEmailId.toString();
											
											if(undefined !=flag && flag!='' && flag=='TRUE'){
											$("span#agentName").html(agentName+"'S TO DO LIST");
											}else{
												$("span#agentName").html(agentName+"'S LIST");	
											} 
										}
	  }
    function checkUundefined(val) {
    	if (undefined != val && val != 'null') {
    		return val;
    	} else {
    		return "";
    	}
    }
    function checkUundefinedNullBlankZero(val) {
    	if ((undefined != val) &&   ('null'!=val) && (null !=val) && ('0' !=val) && (0 !=val) ) {
    		return val;
    	} else {
    		return "-";
    	}
    }
    function openCustDetails(cNum,accId,account){
    	var from=$("#from").val();
		if(undefined !=cNum && cNum !=''){
			if((from=='home' || from=='DashBoard' || from =='') && ("AM-MM" == account || "fromCdmSeeAll" == account)){ 
				$("#custForm").attr("action","./home_template2")
				$("#reqCustNum").val(cNum);
				$('#fromCdmSeeAll').val(account);
				if(undefined!=account && ''!=account && 'fromCdmSeeAll'==account)
					logChangeSatusActivity(8013, 'To Do v2 - Clicked See All Users',cNum);
				else
					logChangeSatusActivity(8004, 'To Do v2 - Clicked View Customer Details',cNum);	
				$('#custForm').submit();
			}
		}
    }
    
   function formatCallOrder(callorder,cnt){
	   if((undefined !=callorder) && ('' !=callorder) &&  ('-'!=callorder))
	    return cnt;
	   else
		return callorder;
   } 

  
function showTrainingPopUp() {
	$('#training_modal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function showTrainingPopUpSam() {
	$('#training_modal_sam').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function showFaqPopUp() {
	$('#faq_modal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function openUsers(th)
{
	var action='#';
	if(th.id=='users')
		action = "./getUserHiererchy";
	if(th.id=='viewAllCustomers')
		action = "./home_cust_profiles";
	
	document.forms['viewAllCust'].action=action;
	document.forms['viewAllCust'].submit();
	
}
function untrim(val){ 
	if(undefined!=val && '' !=val && val.indexOf(",")!=-1){ 
		val =val.replace(/,/g, ",<br>");
	}
	return val;
}
function removeSegDesc(val){
	if(undefined!=val && '' !=val && val.indexOf("-")!=-1){
		val=val.split("-")[0];
	}
	return val;
}

function call(disabled,obj){
	if($(obj).children().children().prop("disabled")==true){
		alert("Please use the Filter to select main categories first before choosing Sub Filters.");
	}
}
function validateSelection(){
alert("You must select at least one sub filter entry.  To remove this category please return to Filter and deselect this category.");
}

function generateContactInfo(name,email,phone,timeZone){
	var finalString='';
	if(''!=name)
		finalString +=name;
	if(''!=name && ''!=email)
		finalString +=' | <span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	else if(''!=phone)
		finalString +='<span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	if(''!=email && ''!=phone)
		finalString +=" </br>Ph: "+formatPhoneLead(phone);
	else if(''!=phone)
		finalString +=" </br>Ph: "+formatPhoneLead(phone);
	if(undefined!=timeZone && ''!=timeZone)
		finalString +=" | "+(timeZone);
	//alert(finalString);
	return finalString;
}
function demoPDF() {  
	   var doc = new jsPDF(); 
	   var htm=$('#tab_default_1').html();
	   doc.fromHTML(htm, 15, 15, {
		   'width': 170,
		   'elementHandlers': specialElementHandlers
		   });
		   doc.save('sample-content.pdf');
	   
	}  

function formatPhoneLead(val) {
	if(undefined!=val && ''!=val && val.indexOf("(")!=-1){
	 val=val.replace('(','');
	}
	if(undefined!=val && ''!=val && val.indexOf(")")!=-1){
		val=val.replace(')','');	
	}
	if(undefined!=val && ''!=val && val.indexOf("(")==-1){
	    var numbers = val.replace(/\D/g, ''),char = { 0: '(', 3: ') ', 6: ' - ' };
	    val = '';
	    for (var i = 0; i < numbers.length; i++) {
	        val += (char[i] || '') + numbers[i];
	    }
	    return val;
	}
	else
	   return val;
}

function openContactModal(custNum, obj){
	
	$('#contactConfModal').modal({
		"backdrop"  : "static",
		 handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	$("#contactYesId").unbind('click');
	$("#contactYesId").click(function(){
		logChangeSatusActivity(2037, 'Clicked status box indicating Customer was reached',custNum);
        saveToTaskTracker(custNum);
		$('#contactConfModal').modal('hide');
	});
	$("#contactNoId").unbind('click');
	$("#contactNoId").click(function(){
		$('#contactConfModal').modal('hide');
		$(obj).attr("checked",false);
	});
}




function deleteCoookie() {
	$.each(localStorage, function(key, value) {
		if (undefined != key && '' != key
				&& key.indexOf('dataTables-QuickSearch') != -1
				&& undefined != value && '' != value
				&& (value.indexOf('start') != -1)
				&& undefined != (value.split(',')[1])) {
			localStorage.removeItem(key);
		}
	});
}
function showHideIcon(obj){
	if(undefined !=obj && undefined !=obj.value && '' !=obj.value){
		var iconclass=$('#searchIconId').attr('class');
		if(iconclass=='glyphicon glyphicon-search')
			$('#searchIconId').attr('class','glyphicon glyphicon-remove');
	}else{
		if(iconclass !='glyphicon glyphicon-search')
			$('#searchIconId').attr('class','glyphicon glyphicon-search');
	}
}
function getLatestFiscalContactedDate(){
	var formData = {};
	var calendar_date;
	$.ajax({
	 	dataType: "json",
		url : ctx+"/getLatestFiscalContactedDate/",
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if(undefined!=data && null!=data && 'null' !=data){
				if(undefined !=data.CLD_DT){
					calendar_date = data.CLD_DT;
					fsc_yr = data.FSC_YR;
					fsc_prd = data.FSC_PRD;
					fsc_wk = data.FSC_WK;
					fsc_dy = data.FSC_DY;
					latestFiscalContactedDate=data.TM_KY;
				}
				
			}
		}
	});	
}
function populateCtaDetails(custId){
	$('div#notFoundHtml').remove();
	var categoryArr=new Array();
	var catStatus=$('#ctaCategoryStatus').html();
	catStatus=(undefined !=catStatus && ''!=catStatus)?(catStatus.toString()):"";
	$('div#ctaDescription').children().css('display','block');
	if(undefined!=catStatus && ''!=catStatus){
			
		var formData={segIds:catStatus};
			$.ajax({
			 	dataType: "json",
				url : ctx+"/getSegmentDetailsById",
				type : "POST",
				data : formData,
				cache : false,
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if(undefined !=data && ''!=data && null!=data){
						$.each(data,function(index,itm){
							var ctaHeaderName=itm.sementType;
							if(undefined !=ctaHeaderName && ''!==ctaHeaderName && 'Churn'!=ctaHeaderName && ctaHeaderName !='Decliner'){
								categoryArr.push('otherprofile');
							     $("a[href='#otherprofile']").html(ctaHeaderName);
							}else
							categoryArr.push(ctaHeaderName);
						});
						enableDisableTalkingPoints(categoryArr);
						$.each(data,function(i,item){
							subSegDesc=item.segmentName;
							ctaName=item.sementType;
							segIndex='1';
							subSegId=item.segmentId
							frequency=item.segmenFreq;
							custId=custId;
							if(undefined !=item.sementType && ''!==item.sementType && 'Churn'!=item.sementType && item.sementType !='Decliner')
							 category='otherprofile'
							else
							 category=item.sementType;
							openSubCallToAction(subSegDesc,ctaName,segIndex,subSegId,frequency,custId,category);	
						});
							
					}
					
				}
			});
		
	 
	}
	if(undefined==catStatus||  null==catStatus || ''==catStatus){ 
		$('div#home').css('display','none')
		$('a[href=#home]').css('display','none')
		$('div#profile').css('display','none')
		$('a[href=#profile]').css('display','none')
		$('div#otherprofile').css('display','none')
		$('a[href=#otherprofile]').css('display','none')
		$('#ctaDescription').append('<div id="notFoundHtml" style="text-align: -webkit-center;height: 500px;padding-top: 50%;color: #c7c7c7;font-size: 16px;font-family: helvetica;font-weight: 600;display:block !important;">This customer has either been reached out to recently or is not declining nor at risk of churn. </br>No contact is needed at this time.</div>');
	}
	else{ 
		$('#ctaDescription').find('#notFoundHtml').remove();
	}
}


function openSubCallToAction(subSegDesc,ctaName,segIndex,subSegId,frequency,custId,category){
	
	var lablesIds=new Array();
	var custid=custId;
	var ctaNameDesc='<span id="ctaName" style="color:#4d4d4d;font-size:16px;font-weight:bold;">'+ctaName+':</span>'
    +'<span id="ctaSegDesc" data-placement="bottom" data-toggle="tooltip" title="'+subSegDesc+'" style="color:#333333;font-size:16px;font-weight:bold;"> '+truncateDataWithLen(subSegDesc,60)+'</span>';
	$("#ctaNameDesc"+category).html(ctaNameDesc);
	$("[data-toggle='tooltip']").tooltip({html:true});
	$("#updateDateValueCtaR"+category).html(frequency);
	showCtaProcessing();
	var selDesposition='';
	var formData = {
			SubSegDesc:subSegDesc,
			CtaName:ctaName,
			SegId:subSegId
			};
	
	$.ajax({
	 	dataType: "json",
		url : ctx+"/subCallToAction/"+custid,
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var cust_div=$("#division").val();
			var cust_num=$("#customerId").html();
			if(undefined !=data && null!=data && "null"!=data){
				generateTalkingPointsToDoList(data,category);
				if(undefined!=ctaName && ''!=ctaName && ctaName.indexOf('Churn')!=-1 || ctaName.indexOf('Decliner')!=-1)
				  populateAttributesToDoList(data,category);
				else if(undefined!=ctaName && ''!=ctaName)
				  populateAttributesToDoList(data,"");
			var segId=data.seg;
			selDesposition='Not Started';
			$("#statusVal-"+category).val(selDesposition);
			$("#linkTaskId-"+category).html('1');
			var dispHtml='';
			$.each(data.dispStatus, function(i, item) {
				dispHtml=dispHtml+'<li id="disp_'+item.split("~")[1]+'"><a href="javascript:void();" style="">'+item.split("~")[0]+'</a><a style="display:none;color:deepskyblue !important;" href="#">'+item.split("~")[1]+'</a></li>'
					+'<li class="divider" style="margin:4px 0px;"></li>';
			});
			if(dispHtml !=''){
			$("#statusList-"+category).html(dispHtml);
			$("ul#statusList-"+category+" li").click(function(){
				 var id=$(this).attr("id");
				 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
					 var link_task_id=id.split("_")[1];
					 selDesposition=$(this).children("a:first").html();
					 $("#statusVal-"+category).val(selDesposition);
					 $("#linkTaskId-"+category).html(link_task_id);
				 }	 
			  });
			}
			$("#applyCallToAction-"+category).unbind('click');
			$("#applyCallToAction-"+category).click(function(){
				var logInUserName=$("#loggedInUserName").val();
				var LoggedInUserID=$("#accId").val();
				
				var comment=$("#commentId-"+category).val();
				var oldComment=$("#oldCommentId-"+category).html();
				var statusVal=$("#statusVal-"+category).val();
				var linkTask=$("#linkTaskId-"+category).html();
				var oldDespVal=$("#oldStatusVal-"+category).html();
				var subject=checkUundefined($("#subjectInputText-"+category).val());
				var oldSubject=checkUundefined($("#oldSubjectInputText-"+category).val());
				
				$("#ctaSfdcConfModalBody").html("Ready to save to Salesforce.com?");
				if(checkModifyChanges(statusVal,oldDespVal,comment,oldComment,subject,oldSubject)){ 
					showCtaSfdcPopUp(segId,subSegDesc,ctaName,subSegId,cust_div,cust_num,logInUserName,comment,statusVal,linkTask,frequency,LoggedInUserID,subject,category,'save');
				}else{ 
					showNoChangePopUp();
				}
			 });
			
			getOrderContactDetails(data,category);
			getDispositionDetail(cust_num,cust_div,segId,subSegId,ctaName,frequency,category,'open');
			$('#statusVal-'+category).bind("DOMSubtreeModified",function(){
				  if($(this).html() == $("#oldStatusVal-"+category).html()){
					  $("#commentId-"+category).val($("#oldCommentId-"+category).html());
					  setCharLimit($('#commentId-'+category));
				  }else if($("#oldStatusVal-"+category).html()!='Not Started'){
					  if($("#commentId-"+category).val() ==$("#oldCommentId-"+category).html())
					  $("#commentId-"+category).val("");
					  else
						  $("#commentId-"+category).val($("#commentId-"+category).val()); 
					  setCharLimit($('#commentId-'+category));
				  }
					  
			});
			
			$('#commentId-'+category).on("change keyup paste", function() {
				  setCharLimit($(this));
			});
			}
			logChangeSatusActivity(8039, 'User has clicked on subSegId \''+subSegId+'\' under '+ctaName+' from To Do v2',cust_num);
			
			$("#ctaCreateOpp-"+category).unbind('click');
			$('#ctaCreateOpp-'+category).click(function(){ 
				logChangeSatusActivity(8036, 'User has Clicked on Create Opportunity in SFDC from subSegId \''+subSegId+'\' under '+ctaName+' from To Do v2',cust_num);
				var filterBy = $('#filterBy').val();
				populateCdmDataOnLoad("DashBoard",filterBy);
				$('#callToAction_PopUp').modal('hide');
			});
			hideCtaProcessing();
		}
	});
	
}
function showCtaSfdcPopUp(segId,subSegDesc,ctaName,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject,category,operation) {
	$('#ctaSfdcConfModal').modal({
		/*"backdrop"  : "static",*/
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	$("#ctaSfdcYesId").unbind('click');
	$("#ctaSfdcYesId").click(function(){
		saveToSFDC(subSegDesc,ctaName,segId,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject,category);
		$('#ctaSfdcConfModal').modal('hide');
		$('#callToAction_PopUp').modal('hide');
	});
	$("#ctaSfdcNoId").unbind('click');
	$("#ctaSfdcNoId").click(function(){
		$('#ctaSfdcConfModal').modal('hide');
		if(undefined!=operation && 'close')
			$('#callToAction_PopUp').modal('hide');
	});
}
function showNoChangePopUp() {
	$('#NoChangeModal').modal({
		/*"backdrop"  : "static",*/
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});

}
function showCallToActionPopUp() {
	$('#callToAction_PopUp').modal({
		"backdrop"  : "static",
        /*handle: ".modal-header",*/
		"keyboard"  : "true",
		"show"      : true           
	});
	
}
function getOrderContactDetails(data,category){
	//$("#orderContactVal-"+category).html("SFDC Contact");
	$("#orderContactVal-"+category).val("SFDC Contact");
	 $("#orderContactId-"+category).html("");
	 $("#oldOrderContactVal-"+category).html("SFDC Contact");
	 $("#oldOrderContactId-"+category).html("");
	
	if(undefined!=data && undefined!=data.orderContactList && null !=data.orderContactList && 'null' !=data.orderContactList && ''!=data.orderContactList){
		$("#orderContactList-"+category).html("");
		var dispHtml='';
		$.each(data.orderContactList,function(i,item){
			dispHtml=dispHtml+'<li id="contactId_'+item.contactId+'"><a href="javascript:void();" style="">'+capitalizeAllWords(item.fullName)+'</a><a style="display:none;" href="#">'+item.contactId+'</a></li>'
			+'<li class="divider" style="margin:4px 0px;"></li>';
		});
		dispHtml=dispHtml+'<li id="contactId_none"><a href="javascript:void();" style="">None</a><a style="display:none;" href="javascript:void();"></a></li>';
	
		if(dispHtml !=''){
		$("#orderContactList-"+category).html(dispHtml);
		$("ul#orderContactList-"+category+" li").click(function(){
			 var id=$(this).attr("id");
			 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
				 var contactId=id.split("_")[1];
				 if(undefined!=contactId && 'none'!= contactId){
				 var orderContact=$(this).children("a:first").html();
				 $("#orderContactVal-"+category).val(capitalizeAllWords(orderContact));
				 $("#orderContactId-"+category).html(contactId);
			 }else{
				 $("#orderContactVal-"+category).val("SFDC Contact");
				 $("#orderContactId-"+category).html("");
			 }
			}
		  });
		}
		
	}
}
function saveToSFDC(subSegDesc,ctaName,segId,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject,category){
	var formData = {};
	var calendar_date,fsc_yr,fsc_prd,fsc_wk,fsc_dy,tm_dy,fyrFprdFwkFdy;
	var contactId=$('#orderContactId-'+category).html();
	var sfdcContactFullName=$('#orderContactVal-'+category).val();
	if('SFDC Contact'==sfdcContactFullName)
		sfdcContactFullName='';
	var userId=loggedInuserId;
	if(undefined==userId || ''==userId || null==userId || 'null'==userId)
		userId='NA';
	$.ajax({
	 	dataType: "json",
		url : ctx+"/getLatestFiscalContactedDate/",
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if(undefined!=data && null!=data && 'null' !=data){
				if(undefined !=data.CLD_DT){
					calendar_date = data.CLD_DT;
					fsc_yr = data.FSC_YR;
					fsc_prd = data.FSC_PRD;
					fsc_wk = data.FSC_WK;
					fsc_dy = data.FSC_DY;
					tm_ky = data.TM_KY;
					fyrFprdFwkFdy = tm_ky;
				}
				
				var formData1 = {
						subSegDesc:subSegDesc,
						ctaName:ctaName,
						subSegId:subSegId,
						div:div,
						custNum:custNum,
						user:user,
						comment:comment,
						dispStatus:dispStatus,
						linkId:linkId,
						date:calendar_date,
						fyrFprdFwkFdy:fyrFprdFwkFdy,
						contactId:contactId,
						sfdcContactFullName:sfdcContactFullName,
						freq : frequency,
						userId:userId,
						subjectTxt:subject
						};
				$.ajax({
					url : ctx+"/addSegmentData/",
					type : "POST",
					cache : false,
					async:false,
					data : formData1,
					dataType : "text",
					timeout : 1000000,
					async : false,
					success : function(data, textStatus, jqXHR) {
						
						if(undefined !=data && ''!=data && 'null'!=data && null!=data){
							showOkModal('Saved to SFDC','Your changes have been saved.')
							getDispositionDetail(custNum,div,segId,subSegId,ctaName,frequency,category,'save');
							var filterBy = $('#filterBy').val();
							//onPageLoad();
							populateCdmDataOnLoad("DashBoard",filterBy);
						}
						
					}
				});
			}
			logChangeSatusActivity(8040, 'User has Clicked Save to SFDC on segment no. \''+subSegId+'\' under '+ctaName+' from To Do v2',custNum);
		}
	
	});
}
function getDispositionDetail(cust_num,cust_div,segId,subSegId,ctaName,frequency,category,from){
	var taskCombinationId='';
	taskCombinationId=zeroPad(cust_num,10)+''+cust_div+''+zeroPad(segId,2)+''+zeroPad(subSegId,3)+''+latestFiscalContactedDate;
	var formData = {
			taskCombinationId:taskCombinationId,
			freq:frequency
	};
	$.ajax({
	 	dataType: "json",
		url : ctx+"/getDespositionDetails/",
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$("#commentId-"+category).val("");
			$("#subjectInputText-"+category).val("");	
			$("#oldSubjectInputText-"+category).val("");
			setCharLimit($('#commentId-'+category));
			openSubjectModal(data,category);
			if(undefined!=data && null!=data && 'null' !=data){
				var commentHtml='';
				var comment='';
				$.each(data,function(i,item){
					$("#commentLogBody-"+category).html("");
					if(undefined !=item.commentText && 'NA'==item.commentText)
						comment='-';
					else
						comment=item.commentText;
					if(i==0){
						//$("#statusVal-"+category).html(item.dispositionStatus);
						$("#statusVal-"+category).val(item.dispositionStatus);
						$("#linkTaskId-"+category).html(item.dispositionLinkId);
						if(undefined !=item.commentText && 'NA'==item.commentText){
							$("#commentId-"+category).val("");
							setCharLimit($('#commentId-'+category));
						}
						else{
							$("#commentId-"+category).val(item.commentText);
							setCharLimit($('#commentId-'+category));
						}
						if(undefined!=from && ''!=from && from =='save')
						$("#"+ctaName+"_Sub_"+subSegId).html(item.dispositionStatus+'- '+item.taskInsertDate);
						commentHtml=commentHtml+'<div class="notice notice-danger" style="background-color: #f7f7f7;padding: 5px; border-radius: 7px !important;">'
						+'<ul style="margin:0"><li class="" style="font-weight: 300;font-family: Roboto,sans-serif;float: right;color: #888;list-style: none;line-height:1.8;font-size: 12px;width: 100%;text-align: right;">'
						+'<span> '+item.createdBy+'</span> on <span>'+item.fullTaskInsertDate+'<span>' 
					    +'</li></ul>'
					    +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Subject: </strong><span id="comm">'+item.taskSubject+'</span></br>'
		                +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Disposition: </strong><span id="comm">'+item.dispositionStatus+'</span></br>'
		                +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Comment: </strong><span id="comm" style="word-break:break-all;">'+comment+'</span>' 
		                +'</div>';
						$("#oldStatusVal-"+category).html(item.dispositionStatus);
						$("#oldCommentId-"+category).html($("#commentId-"+category).val());
					}else{ 
						commentHtml=commentHtml+'<div class="notice notice-danger" style="background-color: #f7f7f7;padding: 10px; border-radius: 7px !important;">'
						+'<ul class=""><li class="" style="font-weight: 300;font-family: Roboto,sans-serif;float: right;color: #888;list-style: none;line-height:1.8;font-size: 12px;width: 100%;text-align: right;">'
						+'<span> '+item.createdBy+'</span> on <span>'+item.fullTaskInsertDate+'<span>' 
					    +'</li></ul>'
					    +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Subject: </strong><span id="comm">'+item.taskSubject+'</span></br>'
					    +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Disposition: </strong><span id="comm">'+item.dispositionStatus+'</span></br>'
		                +'<strong style="font-family: Helvetica;font-size: 14px;letter-spacing:.5px;"> Comment: </strong><span id="comm" style="word-break:break-all;">'+comment+'</span>' 
		                +'</div>';
						
					}
					$("#commentLogBody-"+category).html(commentHtml);
				});
				/**/
				
				if(undefined!=data && null!=data && 'null' !=data){
					$.each(data,function(i,item){
						if(i==0){
							if(undefined !=item.contactId && '' !=item.contactId && undefined != item.sfdcContactFullName && '' != item.sfdcContactFullName){
							//$("#orderContactVal-"+category).html(capitalizeAllWords(item.sfdcContactFullName));
							$("#orderContactVal-"+category).val(capitalizeAllWords(item.sfdcContactFullName));
							$("#oldOrderContactVal-"+category).html(capitalizeAllWords(item.sfdcContactFullName));
							 $("#orderContactId-"+category).html(item.contactId);
							 $("#oldOrderContactId-"+category).html(item.contactId);
							}else{
							//$("#orderContactVal-"+category).html("SFDC Contact");
							 $("#orderContactVal-"+category).val("SFDC Contact");
							 $("#orderContactId-"+category).html("");
							 $("#oldOrderContactVal-"+category).html("SFDC Contact");
							 $("#oldOrderContactId-"+category).html("");
							}
							
						}
					});
				}
			}else{
				$("#commentLogBody-"+category).html("<div style='padding:15px;font-weight:bold;font-size:16px;opacity:0.6;'>No history available.</div>");
				//$("#statusVal-"+category).html('Not Started');
				$("#statusVal-"+category).val('Not Started');
				$("#commentId-"+category).val('');
				$("#oldStatusVal-"+category).html('Not Started');
				$("#oldCommentId-"+category).html('');
				setCharLimit($('#commentId-'+category));
			}
		}
	
	});
}

function openCommentHistoryPopup(){
	$('#commentHistoryModal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});	
}
function openSubjectModal(dispdata,category){
	var formData = {};
	$("#subjectInputText-"+category).val("");
	 $("#oldSubjectInputText-"+category).html("");
	 if(undefined!=dispdata && null!=dispdata && 'null' !=dispdata){
			$.each(dispdata,function(i,item){
				if(i==0){
					if(undefined !=item.taskSubject && null!=item.taskSubject && 'null'!=item.taskSubject){
						$("#subjectInputText-"+category).val(item.taskSubject);
						$("#oldSubjectInputText-"+category).val(item.taskSubject);
					}else{
						$("#subjectInputText-"+category).val("");
						$("#oldSubjectInputText-"+category).val("");
					}
				}
			});
		}
	$.ajax({
	 	dataType: "json",
		url : ctx+"/getSegmentSubjectLabels/",
		type : "GET",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
		
			if(undefined!=data && null!=data && 'null' !=data){
					$("#subjectList").html("");
					var subHtml='';
					$.each(data,function(i,item){
						subHtml=subHtml+'<li id="subId_'+item+'"><a href="javascript:void();" style="">'+capitalizeAllWords(item)+'</a><a style="display:none;" href="javascript:void();">'+item+'</a></li>'
						+'<li class="divider" style="margin:4px 0px;"></li>';
					});
				
					if(subHtml !=''){
					$("#subjectList-"+category).html(subHtml);
					$("ul#subjectList-"+category+" li").click(function(){
						 var id=$(this).attr("id");
						 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
							 var subId=id.split("_")[1];
							 if(undefined!=subId && 'none'!= subId){
							 var sub=$(this).children("a:first").html();
							 $("#subjectInputText-"+category).val(capitalizeAllWords(sub));
						 }else{
							 $("#subjectInputText-"+category).val("");
						 }
						}
					  });
					}
					
				}
		}
	
	});
	
}

function generateTalkingPointsToDoList(data,category){
	var lableArr = new Array();
	var popupContentHtml=getCtaContentHtmlToDoList(category);
	$("#bhoechie-tab-container-"+category).html();
	$("#bhoechie-tab-container-"+category).html(popupContentHtml);
	if (undefined != data.hdrLables && '' != data.hdrLables) {
		$.each(data.hdrLables, function(i, item) {
			lableArr.push(item.headerId);
			var lableInnerTextId = 'Hdr' + item.headerId + 'StaticText';
			var lableInnerText=unescape(data[lableInnerTextId]);
			$("#" + lableInnerTextId+''+category).html("");
			var res= $("#" + lableInnerTextId+''+category).html(lableInnerText).text();
			$("#" + lableInnerTextId+''+category).html(res);
		});
		
	}
	
}

function getCtaHeaderHtmlToDoList(){
	return '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu" style="padding-left: 15px;"><div class="list-group"><a id="callSeg1" href="#" class="list-group-item text-center active" style="width:100%;padding-bottom:0px;border-top:none !important;">                   RATIONALE FOR SELECTION                 </a>                 <a id="callSeg2" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                   EXPLORE &amp; EXPAND: CONVERSATION STARTERS                 </a>                 <a id="callSeg3" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                   EXPLORE FOR VALUE: OBJECTION HANDLING                 </a>                 <a id="callSeg4" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">     			BUSINESS PROBING QUESTIONS: DRILL DOWN                 </a>                 <a id="callSeg5" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">     		   PROPOSE AND CLOSE												                 </a>                 <a id="callSeg6" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">         	   MARKETING RESOURCES												                 </a>                 <a id="callSeg7" href="#" class="list-group-item text-center " style="width:100%;padding-bottom:0px;">                MISCELLANEOUS </a> </div></div>';
}
function getCtaContentHtmlToDoList(category){
	return '<div class="col-xs-12 col-sm-12 green " id="Hdr1StaticText'+category+'" style="max-height: 345px;overflow-y: auto;padding-top:15px;"></div>';
}
function populateAttributesToDoList(data,category){ 
	if('' !=data && undefined !=data.attributeList && null !=data.attributeList && ''!=data.attributeList){
		$.each(data.attributeList,function(i,item){
			if(undefined !=item.paramLabel && ''!=item.paramLabel){
				$("#paramKey"+category+"_"+item.headerId+'_'+item.paramId).val(item.paramLabel);
				var paramVal=item.paramValue;
				if(undefined!=paramVal && ''!=paramVal && 'NA'!=paramVal){
					paramVal=paramVal;
					$("#paramVal"+category+"_"+item.headerId+'_'+item.paramId).val(customCapitalizeCta(paramVal));
				}else if('NA'==paramVal)
				$("#paramVal"+category+"_"+item.headerId+'_'+item.paramId).val(customCapitalizeCta(paramVal));
				
				if($("#paramVal"+category+"_"+item.headerId+'_'+item.paramId).val() =='NA'){
					$("#premiumAcc").css("display","none");
				}
				
				if(undefined !=item.paramLabel && ''!=item.paramLabel && item.paramLabel=='SKU_NAME'){
					$("#paramKey"+category+"_"+item.headerId+'_'+item.paramId).attr("style","display:none");
				}
			}
		});
	}
	if('' !=data && undefined !=data.mktgResources && null !=data.mktgResources && ''!=data.mktgResources){
		$.each(data.mktgResources,function(i,item){
			if(undefined !=item.labelName && ''!=item.labelName){
				$("#label"+category+"_"+item.headerId+'_'+item.paramId).html(item.labelName);
				if(undefined!=item.labelHiddenUrl && '' !=item.labelHiddenUrl && 'null' !=item.labelHiddenUrl && null !=item.labelHiddenUrl && undefined !=item.baseUrl && null !=item.baseUrl && '' !=item.baseUrl)
				$("#label"+category+"_"+item.headerId+'_'+item.paramId).attr("href",item.baseUrl+''+item.labelHiddenUrl);
				else
				$("#label"+category+"_"+item.headerId+'_'+item.paramId).attr("href",'javascript:void()');
					
			}
			if(undefined!=item.labelPrefixText && '' !=item.labelPrefixText)
			$("#labelPrefix"+category+"_"+item.headerId+'_'+item.paramId).html(item.labelPrefixText);
			if(undefined!=item.labelSuffixText && '' !=item.labelSuffixText)
			$("#labelSuffix"+category+"_"+item.headerId+'_'+item.paramId).html(item.labelSuffixText);
		});
	}
	if(undefined!=category && ''==category){ 
		$("[id^='paramKey']").each(function () {
	   		if (undefined != this.id && '' != this.id
					&& undefined != this.value && '' != this.value
					&& undefined != this.value.length && this.value.length <= 19 )
				$("#" + (this.id)).attr("style","width:"+ ((this.value.length) * 2.2)	+ '% !important; '+
						"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");	
	   		else if (undefined != this.id && '' != this.id
					&& undefined != this.value && '' != this.value
					&& undefined != this.value.length && this.value.length > 19 && this.value.length <= 20)
				$("#" + (this.id)).attr("style","width:"+ ((this.value.length) * 2.2)	+ '% !important; '+
						"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
			else if (undefined != this.id && '' != this.id
					&& undefined != this.value && '' != this.value
					&& undefined != this.value.length && this.value.length > 20)
				$("#" + (this.id)).attr("style","width:"+ ((this.value.length) *2)	+ '% !important; '+
						"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
			
			else if (undefined != this.id && '' != this.id && undefined != this.value && '' != this.value)
				$("#" + (this.id)).attr("style"," width:auto !important; vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
			else
				$("#" + (this.id)).attr("style",'display:none');
	   		if(undefined != this.value && '' != this.value && ('SKU_NAME'==this.value)){
				$("#" + (this.id)).attr("style",'display:none');
			}
	   });
	   	$("[id^='paramVal']").each(function () {
	   		 if (undefined != this.id && '' != this.id
					&& undefined != this.value && '' != this.value && 'NA' != this.value
					&& undefined != this.value.length )
	   					$("#" + (this.id)).attr("style","width:"+((this.value.length) *3)+"% !important;"+
						"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;position:absolute;");
	   		 else if (undefined != this.id && '' != this.id
					&& undefined != this.value && '' != this.value && 'NA' == this.value
					&& undefined != this.value.length )
	   			$("#" + (this.id)).attr("style","width:"+((this.value.length) *4)+"% !important;"+
				"  vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
						
	   });
	}
}
function setInnerDynamicAttrWidthToDoList(category){
	$("[id^='paramKey"+category+"'][class='innerClass']").each(function () {
		
	var keyWidth=((this.value.length + 1) * 8) + 4;
		$("#" + (this.id)).attr("style","width:"+ keyWidth	+ 'px !important; '+
				" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
});
$("[id^='paramVal"+category+"'][class='innerClass']").each(function () {
	var valWidth=((this.value.length + 1) * 7);
	$("#" + (this.id)).attr("style","width:auto !important; "+
			" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;color:black !important;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
});

}

function setAttributeAlignedToDoList(category){
	formatParamValuesToDoList(category);
	var widthArrayKey=new Array();
	var widthArrayVal=new Array();
	var maxWidthKey=200;
	var maxWidthVal=200;
	$("[id^='paramKey"+category+"']:not(.innerClass)").each(function () {
		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length){
			var width=((this.value.length + 1) * 5.5);
			widthArrayKey.push(width);
			
		}else{
			widthArrayKey.push('200');
		}
    });
	$("[id^='paramVal"+category+"']:not(.innerClass)").each(function () {
		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length){
			var width=((this.value.length + 1) * 5.5);
			widthArrayVal.push(width);
			
		}else{
			widthArrayVal.push('80');
		}
    });
	if(undefined!=widthArrayKey && widthArrayKey.length>0){
	widthArrayKey=widthArrayKey.sort(function(a, b){return b-a});
	maxWidthKey=(undefined!=widthArrayKey[0]) ? (widthArrayKey[0]):'200';
	}
	if(undefined!=widthArrayVal && widthArrayVal.length>0){
		widthArrayVal=widthArrayVal.sort(function(a, b){return b-a});
		maxWidthVal=(undefined!=widthArrayVal[0]) ? (widthArrayVal[0]):'75';
	}

	$("[id^='paramKey"+category+"']:not(.innerClass)").each(function () {
			$("#" + (this.id)).attr("style","width:"+ maxWidthKey	+ 'px !important; '+
					" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;-webkit-opacity:1;-webkit-text-fill-color:#4d4d4d !important;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px; ");
			$("#" + (this.id)).parent().css('list-style','none');
    });
	$("[id^='paramVal"+category+"']:not(.innerClass)").each(function () {
		$("#" + (this.id)).attr("style","width:"+ maxWidthVal+ 'px !important; '+
				" vertical-align: baseline;border: 0;line-height: inherit;padding: 2px;background-color:#fff;-webkit-opacity:1;-webkit-text-fill-color:#4d4d4d !important;font-family:Helvetica;height: auto;font-weight: normal;font-size: 12px;");
		$("#" + (this.id)).parent().css('list-style','none');
   });
	//formatParamValues();
}
function formatParamValuesToDoList(category){
	var maxDigToFormat='';
	var oldarr=new Array();
	var newarr=new Array();
	var count=0;
	$("[id^='paramVal"+category+"_01']:not(.innerClass)").each(function () {
		if (undefined != this.id && '' != this.id && undefined != this.value && '' != this.value && (this.value).indexOf("$")!=-1){
			var value=(this.value).substr(1).trim();
			if(undefined!=value){
				newarr[count]=(Math.round(value)).toString();
				oldarr[count]=(Math.round(value)).toString();
				count++;
			}
			
			
		}
	});
	var maxlen=newarr.sort(function (a, b) { return b - a })[0];
	maxlen=formatAmount(maxlen);
	count=0;
	$("[id^='paramVal"+category+"_01']:not(.innerClass)").each(function () {
		if (undefined !=maxlen && undefined != this.id && '' != this.id && undefined != this.value && '' != this.value && (this.value).indexOf("$")!=-1){
		if(undefined !=maxlen){
			arrVal=formatAmount(oldarr[count]);
			$('#'+this.id).val(customParamFormat(arrVal,maxlen.length));
			count++;
		}
		}
	});
}
function customParamFormat(n, width) {
	  var z = '';
	  var diff=(width - n.length)+1;
	  if(n.length < width){
		  for(var count=0;count< diff;count++){
			  z=z+' ';  
		  }
	  }
	  var v=(n.length >= width ? n : z+''+ n);
	  var formattedVal=formatAmount(v);
	  return "$ "+formattedVal;
}


function formatNum(n){
  	if(n != ''){
  		var commaFormatedVal= n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
  		if(commaFormatedVal.indexOf(".")!=-1){
  			if((commaFormatedVal.split(".")[1]).length==1){
  				commaFormatedVal=commaFormatedVal.split(".")[0]+"."+commaFormatedVal.split(".")[1]+'0';
  			}
  		}else{
  			commaFormatedVal=commaFormatedVal+".00";
  		}
  	    return commaFormatedVal;
  	} else {
  		return '';
  	}
  }
function enableDisableTalkingPoints(categoryArr){
	categoryArr = jQuery.unique( categoryArr );
	if(undefined!=categoryArr && ''!=categoryArr && categoryArr.length>0){
		$('div#home').css('display','block')
		$('a[href=#home]').css('display','block')
		$('div#profile').css('display','block')
		$('a[href=#profile]').css('display','block')
		$('div#otherprofile').css('display','block')
		$('a[href=#otherprofile]').css('display','block')
		if(((categoryArr.indexOf("churn")==-1) && (categoryArr.indexOf('Churn')==-1))){
			$('div#home').css('display','none')
			$('a[href=#home]').css('display','none');
			$('a[href=#home]').parent().css('width','0%');
			
		}else 
			$('a[href=#home]').parent().css('width','100%');
		if(((categoryArr.indexOf("decliner")==-1) && (categoryArr.indexOf('Decliner')==-1))){
			$('div#profile').css('display','none')
			$('a[href=#profile]').css('display','none')
			$('a[href=#profile]').parent().css('width','0%');
			
		}else 
			$('a[href=#profile]').parent().css('width','100%');
		if((categoryArr.indexOf("otherprofile")==-1)){
			$('div#otherprofile').css('display','none')
			$('a[href=#otherprofile]').css('display','none');
			$('a[href=#otherprofile]').parent().css('width','0%');
			
		}else 
			$('a[href=#otherprofile]').parent().css('width','100%');
		if($('a#ui-id-1').is(":visible"))
			$('#ui-id-1').click();
		else if($('a#ui-id-2').is(":visible"))
			$('#ui-id-2').click();
		else if($('a#ui-id-3').is(":visible"))
			$('#ui-id-3').click();
			
		}		
     else if(undefined==categoryArr || ''==categoryArr || null==categoryArr){
    	 $('div#ctaDescription').children().css('display','none');
     }
}
function getLastLiveContactDetails(iamId,custId){
	$('#LiveTabContentTwo').html('');
	if(undefined!=iamId && ''!=iamId){
	var formData={custNum:custId};
	$.ajax({
	 	dataType: "json",
	 	data:formData,
		url : ctx+"/lastLiveContactDetails/"+iamId,
		type : "POST",
		cache : false,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if(undefined !=data && ''!=data && null!=data){
				(undefined!=data.whoId && ''!=data.whoId)? $('#odrContactId').html(data.whoId) : $('#odrContactId').html('');
				(undefined!=data.subject && ''!=data.subject)? $('#subId').html(data.subject) : $('#subId').html('');
				(undefined!=data.status && ''!=data.status)? $('#statId').html(data.status) : $('#statId').html('');
			    (undefined!=data.description && ''!=data.description)? $('#commId').html(data.description) : $('#commId').html('');
			    (undefined!=data.activityDate && ''!=data.activityDate)? $('#conDate').html(removeLeadingZeros(data.activityDate)) : $('#conDate').html('');
			    (undefined!=data.ownerId && ''!=data.ownerId)? $('#ownName').html(data.ownerId) : $('#ownName').html('');
			}
			
			if(undefined !=data.lastLiveContactHistoryList && ''!=data.lastLiveContactHistoryList){
				var AccHistory='';
				$.each(data.lastLiveContactHistoryList , function(i,item){
					AccHistory+='<fieldset id="fieldsetId0" style="  margin-right: 12px; padding-top:15px;padding-bottom: 15px; border-bottom: 2px solid #012c43;margin-left:15px;">'
				          +'<div class="form-group" style="width: 100%;border-bottom:1px solid #e3e3e3;">'
				          +'  <label class="col-lg-4  col-md-4 control-label" for="actDate'+i+'" style="text-align: left;color:#444444;">Date</label>'
				          +' <div class="col-md-8 padleft0 padright0">'
				          +' <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="actDate'+i+'" style="text-align:right;">'+removeLeadingZeros(checkUundefined(item.activityDate))+'</div>'
				          +'</div></div>'
				          
						  +'<div class="form-group" style="width: 100%;border-bottom:1px solid #e3e3e3;">'
					      +'<label class="col-lg-4  col-md-4 control-label" for="own'+i+'" style="text-align: left;color:#444444;">Owner Name</label>'
					      +'<div class="col-md-8 padleft0 padright0">'
					      +' <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="own'+i+'" style="text-align:right;">'+checkUundefined(item.ownerId)+'</div>'
					      +'</div></div>'
						      
					      +'<div style="width: 100%;border-bottom:1px solid #e3e3e3;" class="form-group">'
					      +' <label class="col-lg-4  col-md-4 control-label" for="con'+i+'" style="text-align: left;color:#444444;">Contact</label>'
					      +'<div class="col-md-8 padleft0 padright0">'
					      +'  <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="con'+i+'" style="text-align:right;">'+checkUundefined(item.whoId)+'</div>'
					      +'</div></div>'
						      
					      +'<div class="form-group" style="width: 100%;border-bottom:1px solid #e3e3e3;">'
					      +' <label class="col-lg-4  col-md-4 control-label" for="sub'+i+'" style="text-align: left;color:#444444;">Subject</label>'
					      +' <div class="col-md-8 padleft0 padright0">'
					      +'  <div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="sub'+i+'" style="text-align:right;">'+checkUundefined(item.subject)+'</div>'
  			              +'</div> </div>'
					            
				            +'<div style="width: 100%;border-bottom:1px solid #e3e3e3;" class="form-group">'
				            +'<label class="col-lg-4  col-md-4 control-label" for="hisStat'+i+'" style="text-align: left;color:#444444;">Status</label>'
				            +'  <div class="col-md-8 padleft0 padright0">'
				            +'<div class="form-control rowdetails roedetailsstyle padleft0 padright5" id="hisStat'+i+'" style="text-align:right;">'+checkUundefined(item.status)+'</div>'
				            +' </div></div>'
					            
				            +'<div class="form-group">'
				            +'<label class="col-md-12 control-label" for="email" style="text-align: left;padding-bottom:5px;color:#444444;">Comments</label>'
				            +'<div class="input-group col-md-11" data-validate="length" data-length="5" style="width:96%;">'
				            +'<!-- <textarea type="text" class="form-control rowdetails" name="validate-length" id="comm'+i+'" placeholder="Validate Length" required="" style=" margin-left: 1%;padding: 15px; height:150px;resize:none;overflow:hidden;">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a undoubtable source. </textarea> -->'
				            +'<div class="msg_container_base_comments roedetailsstyle" style="max-height: 75px;padding:0px 0px 0px 15px;" id="comm'+i+'">'+checkUundefined(item.description)+'</div>'
				            +'</div> </div>'
					            
			            +'</fieldset>'
				});
				
				$('#LiveTabContentTwo').html(AccHistory);
			}else{
				$('#LiveTabContentTwo').html('<div style="width:100%;text-align: -webkit-center;height: 500px;padding-top: 50%;color: #c7c7c7;font-size: 16px;font-family: helvetica;font-weight: 600;display:block !important;">Account does not have any recent activities to display.</div>');
			}
			if(undefined !=data && ''!=data && null!=data && undefined!=data.YTDInfoVO && null!= data.YTDInfoVO){
				(undefined!=data.YTDInfoVO.ytdSales && ''!=data.YTDInfoVO.ytdSales)? $('#ytdSales').html(currencyFormat(data.YTDInfoVO.ytdSales)) : $('#ytdSales').html('NA');
				//(undefined!=data.YTDInfoVO.lastOrder && ''!=data.YTDInfoVO.lastOrder)? $('#lastOrder').html(data.YTDInfoVO.lastOrder) : $('#lastOrder').html('NA');
			}
			getLatestFiscaCompleteOrderDate(custId);
		}
	});
	}else{
		$('#odrContactId').html('');
		 $('#subId').html('');
		 $('#statId').html('');
		 $('#commId').html('');
		 $('#ownName').html('');
		 $('#conDate').html('');
	}
}

function getAccountContactDetails(custId,iamId){
	
	var accData='<legend class="text-center rowdetailsHead">Account Contacts</legend>';
	if(undefined!=iamId && ''!=iamId){
		var formData={ iamId :iamId };
		var primaryConArr=new Array();
		var seeAllVisible=false;
		var count=0;
		var allIconIds=new Array();
		$('#accData').html('');
		$.ajax({
		 	dataType: "json",
		 	data:formData,
			url : ctx+"/getCustomerOrderContactDetails/"+custId ,
			type : "POST",
			cache : false,
			timeout : 1000000,
			success : function(data, textStatus, jqXHR) {
				$.each(data,function(i,item){
					primaryConArr[i]=item.isPrimary;
					accData +='<fieldset id="fieldsetId'+i+'"><div class="form-group" style="margin-bottom: 0px;">'
					+'<label class="col-lg-8  col-md-10 control-label" style="font-family: Helvetica;text-align: left;color:#444444;font-weight: normal;font-size: 16px;" id="Name'+i+'">'+capitalizeAllWords(item.orderContact)+'</label>'
					+'<span class="col-lg-4" style="padding-right: 15px;padding-left: 0px;width: auto !important;float: right;">'
					+'<span id="topSpenderIconId'+i+'"  style="display: none;margin-left: 0px; float: right;margin-right:4px;"><i class="fa fa-usd" data-placement="bottom" data-toggle="tooltip" title="Top Spender" style="font-size: 125%;padding-left: 8px;color:#f44;"></i></span>'
					+'<span id="recOrdererIconId'+i+'"  style="display: none;margin-left: 0px; float: right;margin-right:4px;"><i class="fa fa fa-shopping-cart" data-placement="bottom" data-toggle="tooltip" title="Recent Orderer" style="font-size: 125%;padding-left: 8px;color:#f44;"></i></span>'
					+'<span id="recVisitorIconId'+i+'"  style="display: none;margin-left: 0px; float: right;margin-right:4px;"><i class="fa fa-eye" data-placement="bottom" data-toggle="tooltip" title="Recent Visitor" style="font-size: 125%;padding-left: 8px;color:#f44;"></i></span>'
					+'</span>'
					+'</div>'
					+'<div class="form-group" style="margin-bottom: 0px;"><label class="col-lg-10  col-md-10 control-label" style="font-family: Helvetica;text-align: left;color:#444444;font-weight: normal;font-size: 16px;" id="Phone'+i+'" onclick="javascript:logChangeSatusActivity(8088,\''+item.contactPhone+': clicked in the Account Contacts AM1 Dashboard\','+custId+')">'+item.contactPhone+'</label></div>'
					+'<div class="form-group" style="margin-bottom: 0px;"><label class="col-lg-10  col-md-10 control-label"  style="font-family: Helvetica;text-align: left;color:#444444;font-weight: normal;font-size: 16px;"><a href="javascript:openOutlook(\''+item.contactEmail+'\');" style="color:#0099cc;" id="Email'+i+'">'+item.contactEmail+'</a></label></div>';
					if(''!=item.isPrimary && true!=item.isPrimary)
				   	accData += '<div class="checkbox"> <label style="padding-left: 0px;line-height: 1.6;font-size: 1.2em;">  <input id="primaryConCheckId'+i+'" value="" type="checkbox" disabled> <span class="cr" style="width: 1.6em;height: 1.6em;border: 1px solid rgb(1, 44, 67);"><i class="cr-icon fa fa-check" style="color: rgb(255, 255, 255);" id="checkId'+i+'"></i></span><span>Primary Contact</span></label> </div>';
				   	else
				   		accData += '<div class="checkbox"> <label style="padding-left: 0px;line-height: 1.6;font-size: 1.2em;">  <input id="primaryConCheckId'+i+'" value="" type="checkbox" disabled> <span class="cr" style="width: 1.6em;height: 1.6em;border: 1px solid rgb(1, 44, 67);"><i class="cr-icon fa fa-check" style="color: rgb(255, 255, 255);" id="checkId'+i+'"></i></span><span>Primary Contact</span></label> </div>';	
				   	accData +='<div class="form-group" style="margin-bottom: 0px;"> <label class="col-lg-2  col-md-3 control-label" style="width: auto;padding-right: 5px !important;font-family: Helvetica;text-align: left;color:#444444;font-weight: bold;font-size: 16px;">Title: </label>'
					+'<div class="col-md-8 padleft0"> <div class="form-control rowdetails roedetailsstyle padleft0" style="font-family: Helvetica;text-align: left;color:#444444;font-weight: normal;font-size: 16px;" id="Title'+i+'">'+(((undefined != (item.title)) && ('' != item.title) && (' ' != item.title))? (item.title) : ("Not Available"))+'</div> </div></div>'
					
					+'<div class="form-group" style="border-bottom:1px solid #e3e3e3;padding-bottom:7px;"> <label class="col-lg-2  col-md-3 control-label" style="width: auto;padding-right: 5px !important;font-family: Helvetica;text-align: left;color:#444444;font-weight: bold;font-size: 16px;">Last Site Visit:</label><div class="col-md-8 padleft0">'
					+'<div class="form-control rowdetails roedetailsstyle padleft0" style="font-family: Helvetica;text-align: left;color:#444444;font-weight: normal;font-size: 16px;" id="Date'+i+'">'+removeLeadingZeros(item.recentVisitDate)+'</div> </div></div>'
				+'</fieldset>';
				   	findOtherStatus(i,item.isRecentVisitor, item.isRecentOrderer, item.isTopSpender,allIconIds);
					count++;
					seeAllVisible=true;
				});
					if(seeAllVisible==true){
					accData +='<ul style="border: none;margin: 0px;margin-left: 0px;position: relative;"> <div class="liner" style="height: 1px;background: #e0e0e0; position: absolute;margin: 0 auto;left: 0;right: 0;top: 50%;z-index: 1;"></div><li style="list-style: none;margin: 0%;width: 30%;margin-left: 35%;"><a id="seeAllId" href="javascript:void();" style="text-decoration: none;width: 80px;padding: 0px;color: #0099cc;display: inline-block;border-radius: 100px;background: white;left: 0;text-align: center;font-size: 18px;z-index: 10;font-weight: 600;font-family: Helvetica;position: relative;">See All </a> </li></ul>';
				}
				if(''!=accData)
					$('#accData').html(accData);
				$("[data-toggle='tooltip']").tooltip({html:true});
				$('#seeAllId').attr('href','javascript:openCustDetails("'+custId+'","'+custId+'","fromCdmSeeAll")');
				$('#accData').find('fieldset:last').children(':last').css('border','none');
				if((count >=1)){ 
					for(var i=0;i<count;i++){
						$('input[id=primaryConCheckId'+i+']').prop('checked',primaryConArr[i]);
						if(primaryConArr[i]==false)
						$('input[id=primaryConCheckId'+i+']').next('span').css('background-color','#c2c2c2');
						else
						$('input[id=primaryConCheckId'+i+']').next('span').css({'background-color':'rgb(1, 44, 67)','opacity':'1'});
						
					}
				}
				$.each(allIconIds,function(i,item){
					$('#'+item).css('display','block');
				});
			}
		});
		}else{
			$('#accData').html(accData+'<div>NO CONTACTS FOUND</div>');
		}
	}
function findOtherStatus(index,visitor, orderer, spender,allIconIds){
    if(undefined!=visitor && undefined!= orderer && undefined != spender)	
	if(false == visitor && false == orderer && false==spender){
		return allIconIds;
	}if(true==visitor){
		allIconIds.push('recVisitorIconId'+index)
	}if(true==orderer){
		allIconIds.push('recOrdererIconId'+index)
	}if(true==spender){
		allIconIds.push('topSpenderIconId'+index)
	}
	return allIconIds;
}
function generateTodaysProgress(totalRecordCount,fsc_yr,fsc_prd,fsc_wk,fsc_dy){
	var accId=$("#accId").val();
	if(undefined==totalRecordCount || ''==totalRecordCount){
		
	} else {
		$.ajax({
		 	dataType: "json",
			//url : ctx+"/getTodaysAndWeekProgress/"+accId+"/"+latestFiscalContactedDate+"/"+fsc_yr+"/"+fsc_prd+"/"+fsc_wk+"/"+fsc_dy,
		 	url : ctx+"/getTodaysAndWeekProgress/"+accId,
			type : "POST",
			cache : false,
			timeout : 1000000,
			success : function(data, textStatus, jqXHR) {
				var dayArr=new Array();
				var AccArr=new Array();
				var dayAbbrObj ={'Mon':'Mon','Tue':'Tues','Wed':'Wed','Thurs':'Thur','Fri':'Fri'} ;
				if(undefined !=data && ''!=data && null!=data){
				(undefined!=data.todaysAccntActionedPercentage && ''!=data.todaysAccntActionedPercentage)?AnimateCircle('circleContainer',(data.todaysAccntActionedPercentage)/100):AnimateCircle('circleContainer',0.00);
			    (undefined!=data.todaysAccntActioned && ''!=data.todaysAccntActioned)? $('#todayActionCount').html(data.todaysAccntActioned) : $('#todayActionCount').html('0');
				(undefined!=data.todaysDate && ''!=data.todaysDate)? $('#todayActionDate').html(removeLeadingZeros(data.todaysDate)) : $('#todayActionDate').html('NA');
				(undefined!=data.targetReached && ''!=data.targetReached)? $('#happyPathImg').css('display','block') : $('#happyPathImg').css('display','none');
				
				(undefined!=data.weeklyAccntActioned && ''!=data.weeklyAccntActioned)? $('#weekAcctActioned').html(data.weeklyAccntActioned) : $('#weekAcctActioned').html('0');
				(undefined!=data.weekDateRange && ''!=data.weekDateRange)? $('#weekActionDate').html(removeLeadingZeros(data.weekDateRange)) : $('#weekActionDate').html('NA');
				}
				
				
				if(undefined !=data && ''!=data && null!=data && undefined!=data.weeklyAccntActionedMap&& ''!=undefined!=data.weeklyAccntActionedMap){
					$.map(data.weeklyAccntActionedMap, function( Val, Key ) {
						if(undefined!=Key && Key=='Mon'){dayArr[0]=dayAbbrObj[Key];AccArr[0]=Val;}
						else if(undefined!=Key && Key=='Tue'){dayArr[1]=dayAbbrObj[Key];AccArr[1]=Val;}
						else if(undefined!=Key && Key=='Wed'){dayArr[2]=dayAbbrObj[Key];AccArr[2]=Val;}
						else if(undefined!=Key && Key=='Thurs'){dayArr[3]=dayAbbrObj[Key];AccArr[3]=Val;}
						else if(undefined!=Key && Key=='Fri'){dayArr[4]=dayAbbrObj[Key];AccArr[4]=Val;}
					
						});
					popChartData(dayArr,AccArr)
				}
			}
		});
	}
}
 
function removeLeadingZeros(date){ 
	if(undefined!=date && ''!=date && date.indexOf('0')!=-1)
	 return date.replace(/\b0/g, '');
	else 
	 return date;
}
function logActivity(cNum){
	logChangeSatusActivity(8021, 'To Do v2 - Clicked Phone Icon',cNum);
}
function enableDisableAlerts(action,ele){
	if(undefined!=ele && ''!=ele){
		if(action=='enable'){
			$(ele).css('background-color','red');
			$(ele).prev().prev().css('background-color','#FFFFFF');
			$(ele).prev().prev().css('color','#f13c3c');
			$(ele).parent().parent().css('border','1px solid #f13c3c');
			$('#bellIcon').removeClass('enableIcon').removeClass('disableIcon').addClass('enableIcon');
			$('#iconLabel').removeClass('enableIconLbl').removeClass('disableIconLbl').addClass('enableIconLbl');
			logChangeSatusActivity(8045, 'To Do v2 - User has turn on account alerts',"");
			var table = $('#dataTables-CustInfo').DataTable();
			if(undefined!=table && undefined!=(table.state)){
				try {
					table.state.clear();
				}
				catch(err) {
				    console.log('unable to clear state usin table.state.clear() ='+err.message);
				}
			}
			
		}else if(action=='disable'){
			$(ele).prev().css('background-color','#e6e6e6');
			$(ele).prev().css('border-color','#adadad');
			$(ele).css('background-color','#858585');
			$(ele).parent().parent().css('border','none');
			$('#bellIcon').removeClass('enableIcon').removeClass('disableIcon').addClass('disableIcon');
			$('#iconLabel').removeClass('enableIconLbl').removeClass('disableIconLbl').addClass('disableIconLbl');
			logChangeSatusActivity(8046, 'To Do v2 - User has turn off account alerts',"");
		}
	}
}
function configLastLiveTab(){
	 $('[id^=LastTabLable]').css('background-color','#d9d9d9');
 	$('[id^=LastTabLable]').css('color','#6c6c6c');
	    $('[id=LiveTabOne]').css('display','block');
	    $('[id=LiveTabContentOne]').css('display','block');
	    $('[id=LastTabLableOne]').css('background-color','#012c43');
	    $('[id=LastTabLableOne]').css('color','#fff');
	    $('[id^=LastTabLable]').click(function(){ 
	    	$('[id^=LiveTabContent]').css('display','none');
	    	$('[id^=LastTabLable]').css('background-color','#d9d9d9');
	    	$('[id^=LastTabLable]').css('color','#6c6c6c');
	    	
	    	$(this).css('background-color','#012c43');
	    	$(this).css('color','#fff');
	    	$(this).next('[id^=LiveTabContent]').css('display','block');
	    	if(undefined!= this.id && '' != this.id && 'LastTabLableTwo'==this.id){
	    		var custNum=$('#customerId').html();
	    		logChangeSatusActivity(8005, 'User has clicked on Activity History tab on -To Do List v2',((undefined!=custNum)?custNum:""));
	    	}
	    });
}


function showCtaProcessing(){
	//var ctaProcessing='<div style="top: 50%;height: 48px;position: absolute;border: none;margin-top: 0%;" class="dataTables_processing" id="cta_processing"><img width="60" height="60" src="./resources/img/Cta_loading.gif" style=""></div>';
	var ctaProcessing='<div style="background-color: #fff !important;z-index: 1000;top: 0%;height: 800px;position: absolute;border: none;padding-top: 80%;opacity: 1;" class="dataTables_processing" id="cta_processing"><img width="60" height="60" src="./resources/img/Cta_loading.gif" style="filter: brightness(100%);"></div>';
	$( "#CtaContainer").find('div#cta_processing').remove();
	$( "#CtaContainer" ).prepend( ctaProcessing);
}
function hideCtaProcessing(){
	$( "#CtaContainer").find('div#cta_processing').remove();
}
function updateAlertStatus(custId){
	
	$.ajax({
		dataType: "json",
	 	url : ctx+"/updateAlertStatus/"+custId,
		type : "POST",
		cache : false,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (undefined != data) {
				if ('' != data && data > 0 && data != 'HeliosAdmin') {
					if (undefined != $('#iconLabel').html()
							&& '' != $('#iconLabel').html()) {
						var count = parseInt($('#iconLabel').html());
						$('#iconLabel').html(count - 1);
						refresh='N';
					}
				}
				if (''!= data) {
				 getAlertDetails(custId,data);//HeliosAdmin
				}
			}
		
		}
	});
}
function getAlertDetails(custId,updateStatus){
  var alertIdArray=new Array();
  $('#alertContainer').html('');
	$.ajax({
		dataType: "json",
	 	url : ctx+"/getAcountAlerts/"+custId,
		type : "GET",
		cache : false,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (undefined != data && ''!=data) {
				var old_template='';
				alertHtmlPrefix='<div class="panel-group" id="accordion_reg" role="tablist" aria-multiselectable="true">';
				alertHtmlSuffix='<div/>';
				$.each(data,function(i,item){
					alertIdArray.push(item.alertId);
				});
				if(null!=alertIdArray &&''!=alertIdArray && alertIdArray.length >0){
				  alertIdArray=jQuery.unique( alertIdArray );
				  var paramDetail="";
				  for(var count=0;count<alertIdArray.length;count++){
					  var alertId=alertIdArray[count];
					  var counter=0;
					  $.each(data,function(index,object){
						  if(alertId==object.alertId){
							  if(counter==0){
								  paramDetail=checkUundefined(object.paramDetails);
								  old_template= getAlertTemplate(custId,checkUundefined(object.alertIdCombination),checkUundefined(object.alertId),old_template,checkUundefined(object.alertSubject),checkUundefined(object.alertShortDesc),checkUundefined(object.alertStratDate),checkUundefined(object.iconName),checkUundefined(object.iconPath),object.alertTxt,checkUundefined(object.isRead),checkUundefined(updateStatus));
								  counter=-1;
							  }
						  }
					  });
					  counter=0;
				  }
				  $('#alertContainer').html(alertHtmlPrefix+''+old_template+''+alertHtmlSuffix);
				  if(undefined!=paramDetail && ""!=paramDetail && undefined!=alertIdArray && alertIdArray.length>0){
					  populateAlertParamVals(alertIdArray,checkUundefined(paramDetail));
				  }
				  $('.fa-trash-o').click(function(e){
						 e.stopPropagation();
					});
				  $('[id^=alrtRow]').click(function(){
					 
					  if(undefined!=this.id &&''!=this.id && (this.id).indexOf('alrtRow')!=-1){
						  var altId=(this.id).split('alrtRow')[1];
						  if(undefined!=altId &&''!=altId){
							  $('[id^=subDesc]:not(#subDesc'+altId+')').css('display','block');
							if($('#subDesc'+altId).is(':visible')){
								$('#subDesc'+altId).css('display','none');
							}else{
								$('#subDesc'+altId).css('display','block');
							}  
						  }
					  }
				  });
				}
			}else if(undefined!=data && ''==data){
				$('#alertContainer').append('<div id="alertNotFound" style="padding-top: 25%;text-align: -webkit-center;height: 500px;color: #c7c7c7;font-size: 16px;font-family: helvetica;font-weight: 600;display:block !important;"><img src="./resources/img/unhappyPath.png"></div>');
			}
		}
	});
}
function getAlertTemplate(custId,alertIdCombination,alertId,old_template,alertSubject,shortDesc,startDate,iconName,iconPath,commentText,isRead,user){
   //alert('ALL fields='+alertIdCombination+'---'+alertSubject+'--'+shortDesc+'---'+startDate+'--'+iconName+'--'+iconPath+'--'+commentText);
	var icon='.'+iconPath+'/'+iconName;
	if(undefined!=isRead && ''!=isRead && 'Y'==isRead){
	old_template +='<div class="panel panel-default bordernone" style="margin-top:0px;">'
     +'<div class="panel-heading padding0" role="tab" id="headingOne_reg"><h4 class="panel-title">'
         +'<div class="" id="boxListing"><div class="row" id="alrtRow'+alertId+'" ><div class="col-sm-12">' 
                 +'<div class="brdr box-shad property-listing selectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseOne_reg'+alertId+'" aria-expanded="false" aria-controls="collapseOne_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;">'
                    +' <div class="media medCls" > <a class="pull-left" href="#" target="_parent">'
                         +'<img alt="image"  width="42" height="50" class="img-responsive" src="'+icon+'" style="max-width: 50px;"></a>'
                         +'<div class="clearfix visible-sm"></div>'
                         +'<div class="media-body fnt-smaller" style="padding-right:7px;"> <a href="#" target="_parent"></a>'

                             +'<h4 class="media-heading">'
                               +'<a class="selHead" href="javascript:void();" target="_parent" style="font-size: 16px;text-decoration:none;">'+ checkUundefined(alertSubject)
                               +'<small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;" onclick="deleteAccountAlert(\''+custId+'\',\''+alertIdCombination+'\',\''+alertId+'\',\''+user+'\')"></small>'
                               +'<small class="pull-right selText" style="padding-top: 5px;font-size: 14px;">'+removeLeadingZeros(checkUundefined(startDate))+'</small>'
                             +'</a></h4>'
                             +'<span class="fnt-smaller fnt-lighter fnt-arial selText" id="subDesc'+alertId+'" >'+checkUundefined(shortDesc)+'</span>'
                         +'</div>'
                     +'</div>'
                 +'</div>'
             +'</div>'
         +'</div>'
			+'</div>'
       +'</h4>'
     +'</div>'
     
     +'<div id="collapseOne_reg'+alertId+'" class="panel-collapse collapse unselectedContainerBody" role="tabpanel" aria-labelledby="headingOne_reg">'
       +'<div class="panel-body bordernone margintopzero panelBodyText" style="padding-left: 7px !important;  padding-top: 0px !important;">'
       +commentText
       +'</div>'
     +'</div>'
   +'</div>';
	} else{
		
		old_template +='<div class="panel panel-default bordernone" style="margin-top:0px;">'
		     +'<div class="panel-heading padding0" role="tab" id="headingOne_reg"><h4 class="panel-title">'
		         +'<div class="" id="boxListing"><div class="row" id="alrtRow'+alertId+'"><div class="col-sm-12">' 
		                 +'<div id="alrt_container_'+alertId+'" class="brdr box-shad property-listing unselectedContainer" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseOne_reg'+alertId+'" aria-expanded="false" aria-controls="collapseOne_reg" style="box-shadow: none;padding-left: 0px !important;border-left: none;border-right: none;border:none;" onclick="onReadAlert(this,\''+custId+'\',\''+alertIdCombination+'\',\''+alertId+'\',\''+user+'\');">'
		                    +' <div class="media medCls" > <a class="pull-left" href="#" target="_parent">'
		                         +'<img alt="image"  width="42" height="50" class="img-responsive" src="'+icon+'" style="max-width: 50px;"></a>'
		                         +'<div class="clearfix visible-sm"></div>'
		                         +'<div class="media-body fnt-smaller" style="padding-right:7px;"> <a href="#" target="_parent"></a>'

		                             +'<h4 class="media-heading">'
		                               +'<a class="unselHead" id="sub_parent_'+alertId+'" href="javascript:void();" target="_parent" style="font-size: 16px;text-decoration:none;"><span id="subject'+alertId+'">'+ checkUundefined(alertSubject)
		                               +'</span><small class="fa fa-trash-o" style="color: #666;font-size: 150%;float: right;padding-left: 10px;" onclick="deleteAccountAlert(\''+custId+'\',\''+alertIdCombination+'\',\''+alertId+'\',\''+user+'\')"></small>'
		                               +'<small class="pull-right unselText" style="padding-top: 5px;font-size: 14px;" id="strtdate_'+alertId+'">'+removeLeadingZeros(checkUundefined(startDate))+'</small>'
		                             +'</a></h4>'
		                             +'<span class="fnt-smaller fnt-lighter fnt-arial unselText" id="subDesc'+alertId+'">'+checkUundefined(shortDesc)+'</span>'
		                         +'</div>'
		                     +'</div>'
		                 +'</div>'
		             +'</div>'
		         +'</div>'
					+'</div>'
		       +'</h4>'
		     +'</div>'
		     
		     +'<div id="collapseOne_reg'+alertId+'" class="panel-collapse collapse unselectedContainerBody" role="tabpanel" aria-labelledby="headingOne_reg">'
		       +'<div class="panel-body bordernone margintopzero panelBodyText" style="padding-left: 7px !important;  padding-top: 0px !important;">'
		       +commentText
		       +'</div>'
		     +'</div>'
		   +'</div>';
	}
	 return old_template;
}


function populateAlertParamVals(alertIdArray,paramDetails){
	var lableValMap = new Object(); 
	for(count=0;count<alertIdArray.length;count++){
		$.each(paramDetails,function(i,item){
			if(undefined!=alertIdArray[count] && undefined!=item.alertId &&  (alertIdArray[count] ==item.alertId) && 'NO_PARAM_KEY'!=item.paramLabel){
				lableValMap['ALRT_'+item.alertId+'_'+item.paramId]=item.paramLabel+'~#~'+item.paramVal;
			}
		});
		$.each(lableValMap,function(i,item){
			if(undefined!=item && item.indexOf('~#~')!=-1){
			$('#'+i+'_Key').html(item.split('~#~')[0]);
			$('#'+i+'_Val').html(item.split('~#~')[1]);
			}
		});
		
		if (alertIdArray[count] == 3) {
			if (undefined != $('#ALRT_3_6_Key').html()
					&& '' != $('#ALRT_3_6_Key').html()
					&& undefined != $('#ALRT_3_6_Val').html()
					&& '' != $('#ALRT_3_6_Val').html()) {
				$('#ALRT_3_6_Val').html($('#ALRT_3_6_Val').html());
			}
			if (undefined != $('#ALRT_3_5_Key').html()
					&& '' != $('#ALRT_3_5_Key').html()
					&& undefined != $('#ALRT_3_5_Val').html()
					&& '' != $('#ALRT_3_5_Val').html()) {
				$('#ALRT_3_6_Key').html('');
				$('#ALRT_3_6_Val').html('');
				$('#ALRT_3_5_Val').html($('#ALRT_3_5_Val').html());
			}
			if (undefined != $('#ALRT_3_5_Key').html()
					&& '' != $('#ALRT_3_5_Key').html()
					&& (undefined == $('#ALRT_3_5_Val').html() || '' == $(
							'#ALRT_3_5_Val').html())) {
				$('#ALRT_3_5_Key').html('');
			}
			if (undefined != $('#ALRT_3_6_Key').html()
					&& '' != $('#ALRT_3_6_Key').html()
					&& (undefined == $('#ALRT_3_6_Val').html() || '' == $(
							'#ALRT_3_6_Val').html())) {
				$('#ALRT_3_6_Key').html('');
			}
			
			
		}
		lableValMap={};
	}
	
}
function deleteAccountAlert(custId,alertIdComb,alrtId,user){
	// to delete perticular alert on customer
	console.log(custId+'--'+alertIdComb+'--'+alrtId);
	logChangeSatusActivity(8044, 'To Do v2 - User has deleted the alert with alertId='+alrtId+'',custId);
	if(undefined !=user && ''!=user && user!='HeliosAdmin'){
	var formData={custNum:custId, alertIdCombination:alertIdComb,alertId:alrtId};
	$.ajax({
		dataType: "json",
	 	url : ctx+"/deleteAlert/",
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (undefined != data && ''!=data && data>0) {
				$('#alertContainer').html('');
				getAlertDetails(custId,user);
			}
		}
	});
	}else{
		alert('You are not authorized to delete this alert..!!');
		return;
	}
}

function getLatestPageRefreshTimestamp(){

	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	$('#latestRefreshTimeStamp').html('');	
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			pageNumber:'8047'
			};
	
	$.ajax({
		url : ctx+"/getLatestPageRefreshTimeStamp",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			if(undefined!=data && null!=data && ''!=data){
				$('#RefreshTimeStamp').html(data);
				var date='';
				if(data.indexOf(' ')!=-1){
					date=data.split(' ')[0];
				}
				var startdatetime=date+' 12:45:00 AM';
				var enddatetime=date+' 11:45:00 PM'
				tenfoldScheduledTimeArr=intervalsWithDate(startdatetime, enddatetime);
                console.log("Actual----->"+intervalsWithDate(startdatetime, enddatetime));
                console.log('Tenfold====>'+tenfoldScheduledTimeArr);
                startTime();
			}else
				$('#RefreshTimeStamp').html('');	
		}
	});
}
function onReadAlert(obj,custId,alertIdComb,alrtId,user){
	// to read perticular alert on customer
	logChangeSatusActivity(8043, 'To Do v2 - User has read the alert with alertId='+alrtId+'',custId);
	if(undefined !=user && ''!=user && user!='HeliosAdmin'){
	var formData={custNum:custId, alertIdCombination:alertIdComb,alertId:alrtId};
	$.ajax({
		dataType: "json",
	 	url : ctx+"/readAlert/",
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (undefined != data && ''!=data && data>0) {
				$('#strtdate_'+alrtId).removeClass('unselText').addClass('selText');
				$('#shortDesc'+alrtId).removeClass('unselText').addClass('selText');
				$('a#sub_parent_'+alrtId).removeClass('unselHead').addClass('selHead');
				$('#alrt_container_'+alrtId).removeClass('unselectedContainer').addClass('selectedContainer');
			}
		}
	 });
	}
}


function currencyFormat (num) {
	var val='';
	if(undefined !=num && '' !=num && null !=num){
    val= "$" + (parseFloat(num)).toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    return val;
	}
    else
    return val;	
}

function getLatestFiscaCompleteOrderDate(custId){
	  
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLatestFiscaExactOrderDate/"+custId,
				type : "POST",
				cache : false,
				async:false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
				    (undefined!=data && ''!=data)? $('#lastOrder').html(removeLeadingZeros(data)) : $('#lastOrder').html('NA');
				}else
					$('#lastOrder').html('NA');
				}

			})	

}