var ctx = $("#svsURL").val();
var abandonedCartListVOArr=new Array();
var custRecommendationListVOArr=new Array();
var dotcomActivityVOArr=new Array();
var objPurchaseDetailsListVO=new Array();
var searchItemsListVOArr=new Array();
var latestDateforOrder=null;
var latestFiscalDateOrderSAMNew = null;
var SuperUserDeatilCount=-1;
var ShipToDeatilCount=-1;
var OrderDeatilCount=-1;
var ShipOrderDeatilCount=-1;
var iStart=0;
var ActiveUserDeatilCount=-1;
$.fn.dataTable.ext.errMode = 'none';
var quickSearchStart= 0;
$(document).ready(function() {
	
	var height=($(window).height() - ($(window).height()/10));
	$(".msg_container_base").css("max-height",height);
	$('#ulIdOther').css('display','none');
	$('#actionStat').css('display','none');
	$('#ulPlaySec').css('display','none');
	$("[data-toggle='tooltip']").tooltip();
	configureCalender();
	var cval=$.cookie('sidebar_closed');
	 if(undefined !=cval && '' !=cval && cval==1 && $(window).width()>768){
		 $("#stplId").html("");
			/*$("#notiId").html("");
			$("#gId").html("");
			$("#rId").html("");
			$("#eId").html("");*/
			$('#ulMenu li').css("padding-left","0px");
			$('a[id^=menuItemIcon]').css("display","block");
			$('a[id^=menuItemId]').css("display","none");
			$("#cnoHead").removeClass("cnoExpand");
			$("#cnoHead").addClass("cnoCollapse");
			$("#logHead").removeClass("logExpand");
			$("#logHead").addClass("logCollapse");
			//$('#playSection').html("");
			$('#ulPlaySec').css("display","none");
			//$("#tglId").css("padding-top","50px");
			$("#tglId").css("width","100%");
			$("#stplLogoId").css("display","none");
			$("#reorderPane").removeClass("reorderPaneWinExp");
   		$("#reorderPane").removeClass("reorderPaneIpadCollapse");
   		$("#reorderPane").removeClass("reorderPaneIpadExp");
   		$("#reorderPane").addClass("reorderPaneWinCollapse");
   		$("#babPane").removeClass("babPaneWinExp");
   		$("#babPane").removeClass("babPaneIpadCollapse");
   		$("#babPane").removeClass("babPaneIpadExp");
   		$("#babPane").addClass("babPaneWinCollapse");
	 }
	if(undefined !=$("#iStart").val() && $("#iStart").val()!=null && $("#iStart").val()!='' ){
		 iStart = $("#iStart").val(); 
	 }
	$("#applyCallToAction").click(function(){
		var rationaleHtml=$(".Editor-editor").html();
		applySubCallToAction(rationaleHtml);
		$(".Editor-editor").html("");
	 });
	 
	 $("#ratEditId").click(function(){
			var rationaleHtmlData=$("#rationaleData").html();
			$(".Editor-editor").html("");
			alert(rationaleHtmlData);
			$(".Editor-editor").html(rationaleHtmlData);
		 });
	 $("#applyOnEdit").click(function(){
			var rationaleDataAfterEdit=$(".Editor-editor").html();
			$("#rationaleData").html("");
			$("#rationaleData").html(rationaleDataAfterEdit);
		 });
	$(".active").click(function(e) { 
	    e.stopPropagation();
        
	});
 $("body").click(function(){ 
	 var status=$(".page-quick-sidebar-toggler").css("display");
	if((undefined !=status) && (status=='block') && (sidebarClick == true)){
		 $('body').toggleClass('page-quick-sidebar-open');
	     sideBarClick=false;
	 }
 });
 $("#sfdcYesId").click(function(){
	 var sfdcUrl=$("#sfdcConfUrl").val();
		if(undefined!=sfdcUrl && '' != sfdcUrl){ 
			logUserActivityDotCom(7033, 'Pre-populated User information to SFDC');
		openUrlWithEmail(sfdcUrl);
		}
 });
	$('#dataTables-exampleShipTo').dataTable({
		"dom": '<"pull-right"f>rt<"bottom"ip>',
		 "bSort": true,
			"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
			"oLanguage": { "sSearch": "Filter: "},
			"order": [],
			"bProcessing": true,
			"aoColumns": [{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true}
			] 
	});
	$('#dataTables-example').dataTable({
		 "dom": '<"pull-right"f>rt<"bottom"ip>',
		 "bSort": true,
		 "lengthMenu": [[5, 15, 25, -1], [5, 15,25, "All"]],
			"order": [],
			"bProcessing": true,
			"oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": false},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true}
			
			] 
	 });
	 $('#dataTables-order').dataTable({
		 "dom": '<"pull-right"f>rt<"bottom"ip>',
			"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
			"order": [],
			"bProcessing": true,
			"oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true},
			{"bSortable": true},
			
			{"bSortable": true},
			{"bSortable": true}]
		});
	$('#dataTables-OrderInfo').DataTable({
		"dom": '<"pull-right"f>rt<"bottom"ip>',
	"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
	  "jqueryUI":true,
	  "oLanguage": { "sSearch": "Filter: "},
	  "order": [],
		"aoColumns": [{"bSortable": true, "sWidth": '33%'},
		{"bSortable": true, "sWidth": '33%'},
		{"bSortable": true, "sWidth": '33%'},
		{"bSortable": true, "sWidth": '33%'},
		{"bSortable": true, "sWidth": '33%'},
		{"bSortable": true, "sWidth": '33%'}
		
		]
    })
    $('#dataTables-example1').DataTable({
         // responsive: true
		 "dom": '<"pull-right"f>rt<"bottom"ip>',
		 "bSort": true,
		 "oLanguage": { "sSearch": "Filter: "},
		"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
		"scrollX": true,
        "paging": false,
        "searching": false,
        "info":   false,
		// "aaSorting": [[ 0, "" ]] 
	"order": [],
	"aoColumns": [{"bSortable": false},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true},
	{"bSortable": true}
	] 
   });
	$('#scrollTop').click(function(){ 
	    $('html, body').animate({
	        scrollTop: $( $.attr(this, 'href') ).offset().top
	    }, 1200);
	    return false;
	});
	$("#growthId").click(function(){
  	  
		var Gclass=$("#growthId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
			logUserActivityDotCom(7027, 'User has clicked on Call Order Growth List');
			$("#gIconId").attr("class","fa fa-chevron-up")
		}else if(undefined != Gclass &&  '' ==Gclass){
			$("#gIconId").attr("class","fa fa-chevron-down")
		}
	});
	 $("#retentionId").click(function(){
		var Gclass=$("#retentionId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
			$("#rIconId").attr("class","fa fa-chevron-up");
			logUserActivityDotCom(7028, 'User has clicked on Call Order Retention List');
		}else if(undefined != Gclass &&  '' ==Gclass){
			$("#rIconId").attr("class","fa fa-chevron-down")
		}
	});
	 $("#expansionId").click(function(){
		var Gclass=$("#expansionId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
			$("#eIconId").attr("class","fa fa-chevron-up");
			logUserActivityDotCom(7029, 'User has clicked on Call Order Expansion List');
		}else if(undefined != Gclass &&  '' ==Gclass){
			$("#eIconId").attr("class","fa fa-chevron-down")
		}
	})
	$( "#sortSel").change(function() {
		 var monthYear=$("#yearSel").val();
		 if(undefined == monthYear){
			 var datestr = $("#datepickerTEXT").val();
			 if(checkUundefined(datestr).length > 0){
				 monYearArr = datestr.split("/");
				 year = monYearArr[1].trim();
				 month = monYearArr[0].trim();
				 if(month.length == 3){
					 var map = {"Jan":"01","Feb":"02","Mar":"03","Apr":"04","May":"05","Jun":"06","Jul":"07","Aug":"08","Sep":"09","Oct":"10","Nov":"11","Dec":"12","ALL":"0"};
					 month = map[month]; 
				 }
				 monthYear = month + year;
			 }
         
		 }
		 var catId=$("#sortSel").val();
		 // onChangeCategory(catId);
		 onChangeMonthOrCat(monthYear,catId);
	 });
	
	 
	 $( "#lastXDaysSel").change(function() {
		 var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	      var month = parseInt(latestDateforOrder.substring(0,2));
		  var year = latestDateforOrder.substring(2,6) ;
		 var monthYear=$("#lastXDaysSel :selected").val()+year;
		 var catId=$("#sortSel").val();
		 if ($("#lastXDaysSel :selected").val() == "0") {
			 $('#datepickerTEXT').val(monthNameArr[month-1]+" / "+year);
			 onChangeMonthOrCat(month+""+year,catId);
		 } else {
			 $('#datepickerTEXT').val($( "#lastXDaysSel :selected").text());
			 onChangeMonthOrCat(monthYear,catId);
		 }
	 });
	 /*$('#dataTables-QuickSearch')
	    .on('preXhr.dt', function ( e, settings, data ) {
	    	$("#dataTables-QuickSearch tr th").hide();
	    	$("#iStart").val(data.iDisplayStart);
	    	
	    } );*/
	 $('#dataTables-QuickSearch')
	    .on( 'error.dt', function ( e, settings, techNote, message ) {
	    	/*console.log( 'An error has been reported by DataTables: ', message );
	    	if(message.indexOf("Invalid JSON response") !=-1){
	    		$("#portlet-config").modal("show");
		    	setTimeout(function(){ $("#portlet-config").modal("hide"); }, 1000);
		    	$('#portlet-config').on('hidden.bs.modal', function (e) {
		    		window.location.reload();
		    		});
	    		//window.location.reload();
	    	}*/
	    	if(!(settings.jqXHR.getResponseHeader('Content-Type').indexOf('text/html')!=-1)){
		    	console.log( 'An error has been reported by DataTables: ', message );
		    }else{
		    	console.log('Session Logged Out');	
		    }

	    } )
	    .DataTable();
	 $("#backButton").click(function(){
		 //clearCookie();
	 });
	getLatestFiscalDateOrder();
	getLatestFiscalDateOrderSAMNew();
	//getReorderRecomm();
	// getPlayRescommt();
	//getBoughtNotBought();	

	//Sanjay Commented 
	//getOrderContactForBAB();
	//Sanjay Added
	//getBoughtAlsoBught("Contact 1");
	
	populateQuickSearchData();
	populateCustomerProfile();
	populateUpCrossReorder();
	$("#ratId").click(function(){
		 var status=$(this).attr("aria-expanded");
		 if(undefined !=status &&''!=status && status== 'false'){
			 $("#ratId i").attr("class","fa fa-minus");
		 }else if(undefined !=status &&''!=status && status == 'true'){
			 $("#ratId i").attr("class","fa fa-plus");
		 }
	  });
	 $("#expForVal").click(function(){
		 var status=$(this).attr("aria-expanded");
		 if(undefined !=status &&''!=status && status== 'false'){
			 $("#expForVal i").attr("class","fa fa-minus");
		 }else if(undefined !=status &&''!=status && status == 'true'){
			 $("#expForVal i").attr("class","fa fa-plus");
		 }
	  }); 
	 $("#expAndExp").click(function(){
		 var status=$(this).attr("aria-expanded");
		 if(undefined !=status &&''!=status && status== 'false'){
			 $("#expAndExp i").attr("class","fa fa-minus");
		 }else if(undefined !=status &&''!=status && status == 'true'){
			 $("#expAndExp i").attr("class","fa fa-plus");
		 }
	  });
	 $("ul#statusList li").click(function(){
		 var id=$(this).attr("id");
		 if(id=="InProg"){
			 $("#statusVal").html("In Progress");
			 $("#AmtLiId").css("display","none");
		 }else if(id=="Completed"){
			 $("#statusVal").html("Completed");
			 $("#AmtLiId").css("display","block");
		 }else if(id=="NotInt"){
			 $("#statusVal").html("Not Interested");
			 $("#AmtLiId").css("display","none");
		 }else if(id=="NotApp"){
			 $("#statusVal").html("Not Applicable");
			 $("#AmtLiId").css("display","none");
		 }
		 
	  });
	 $("ul li a[id^=menuItemId]").css("background-color","#1d2939");
		$("ul li a[id^=menuItemId]").css("color","#fff");
		$("ul li a[id^=menuItemId]").css("border","none");
	 $("#menuItemId5").css("background-color","#fff");
		$("#menuItemId5").css("color","#444");
});

function populateCustomerProfile(){
	
	var custid = $("#reqCustNum").val();
	var accid = $("#accId").val();
	
	$.ajax({
	 	dataType: "json",
	 	url : ctx+"/sales/representive/"+accid+"/customer/"+custid ,
		type : "GET",
		cache : false,
		data : "", //sanjay JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
		 	if(data !=null && data !='null' && null !=data.customerInfo && 'null' !=data.customerInfo){
				$.each(data.customerInfo, function(i, item) {
					
					var tmpCustNumber = checkUundefined(item.customerNo);
					if(custid == tmpCustNumber){
						
						$("#custNum").html(' '+'<a style="pointer-events:none;cursor: default;color:#262626">'+custid+'</a>');
						
						$("#addrTxt").css("display","block");
						
						$("#custCompName").html(' '+checkUundefined(item.companyName));
						//$("#custAdd1").html(checkUundefined(item.companyName));
						$("#custType").html(checkUundefined(item.tier));
						
						var address='';
				        var cityStateZip='';
				        if(checkUundefined(item.billingStreet).length > 0){
				          	address = checkUundefined(item.billingStreet);
				          	if (address != '')
				          		address = address + "</br>";
				          	if (checkUundefined(item.billingCity).length > 0){
				          		cityStateZip = checkUundefined(item.billingCity);
				          		if (cityStateZip != '')
				          			cityStateZip = cityStateZip+", "+checkUundefined(item.billingState)+" "+formatZip(checkUundefined(item.postalCode));				          		
				          			
				          		address = address + cityStateZip;
					        }
				          	if(undefined !=address && ''!=address)
				          		address = address.toUpperCase();
				          	$("#custAddDtl").html(address);
				        }else{
				        	$("#custAddDtl").html("ADDRESS NOT FOUND");
				        }
				        
				        
				        var contactPerson = '';
				        if(checkUundefined(item.firstName).length > 0)
				        	contactPerson += checkUundefined(item.firstName) + ' ' + checkUundefined(item.lastName);
						
				        contactPerson = generateContactPersonSAMNew (contactPerson, checkUundefined(item.contactEmailId));
						$("#custContactDetail").html(' '+contactPerson);
						
						if (checkUundefined(item.phone).length > 0)
							$("#custConNum").html(' '+'<span onclick="javascript:logUserActivityDotCom(8090,\''+checkUundefined(item.phone)+': clicked in SAM Recommendations Page for Customer'+custid+'\')">'+checkUundefined(item.phone)+'</span>');
						
						if (checkUundefined(item.enrollDate).length > 0)
							$("#custEnrollDate").html(' '+checkUundefined((item.enrollDate).substring(0,11)));
						if (checkUundefined(item.industryCode).length > 0)
							$("#custIndGrp").html(' '+checkUundefined(item.industryCode));
						if (undefined !=item.timeZone && ''!=item.timeZone){
							if(undefined!=$("#custConNum").html() && ' '!= $("#custConNum").html() && ''!=$("#custConNum").html())
							 $("#custConNum").append('<br>'+checkUundefined(item.timeZone));
							else
							 $("#custConNum").html(checkUundefined(item.timeZone));	
						}
						/*
						 * For Fiscal year selection section
						 */
						if(checkUundefined(item.last_contacted_date).length > 0){
							lastConDate = checkUundefined(item.last_contacted_date);
							if(lastConDate != null || lastConDate != ''){
								lastConDate = lastConDate.substring(0,10);
								var lastConDateArr = lastConDate.split("-");
								lastConDate = lastConDateArr[1] + "/" + lastConDateArr[2] + "/" + lastConDateArr[0];
							}
							$("#lastContactedDateValueId").html(lastConDate);
							
						}
						
						
						/*
						if(checkUundefined(item.billingStreet).length > 0)
							address += checkUundefined(item.billingStreet);
						
						if (checkUundefined(item.billingCity).length > 0)
							address += ", " + checkUundefined(item.billingCity);
						
						if (checkUundefined(item.billingState).length > 0)
							address += ", " + checkUundefined(item.billingState);
							
						if (checkUundefined(item.billingCountry).length > 0)
							address += ", " + checkUundefined(item.billingCountry);
						
						if (checkUundefined(item.postalCode).length > 0)
							address += ", " + checkUundefined(item.postalCode);
						
						$("#custAdd2").html(checkUundefined(address));
						*/
						
				        if(checkUundefined(item.billingStreet).length > 0){
				        	
				        }
				        
				        if(item.sam_SFDC_ID != '-' && item.sam_SFDC_ID !=''){
							$("#cnoId").html("");
							$("#logId").html("");
							var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
							var compNameUrl=sfdcBaseUrl+'/'+item.sam_SFDC_ID;
							$("#compNameCont").html('<a href="'+compNameUrl+'" onclick="generateLogs(\'compnameSAMNew\')"  oncontextmenu="generateLogs(\'compnameSAMNew\')" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#0066c0;text-decoration:underline;" class="letterSpace" target="_blank"> '+checkUundefined(item.companyName)+' </a>');
							$("#ulIamIdSec").css("display","block");
							//$("#ulIdOther").css("display","block");
							$("#sfdcId").css("display","block");
							
							var cnoUrl='https://na42.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+item.accountId+'%26accid%3D'+item.accountId
							//var cnoUrl=sfdcBaseUrl+'/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+item.sam_SFDC_ID+'%26accid%3D'+item.sam_SFDC_ID;
							var cnoHtml='<a href="'+cnoUrl+'" onclick="generateLogs(\'createoppSAMNew\')" oncontextmenu="generateLogs(\'createoppSAMNew\')" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;" target="_blank">Create New Opp.</a>'+
							' <a href="'+cnoUrl+'" onclick="generateLogs(\'createoppSAMNew\')" oncontextmenu="generateLogs(\'createoppSAMNew\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 19px;margin-right: -8px;float:right;" id="cnoIconId" class="fa fa-hand-o-up"></i></a>';
							$("#cnoId").html(cnoHtml);
							$("#ctaCreateOpp").attr("href",cnoUrl);
							$("#menuItemIcon7").attr("href",cnoUrl);
							var logUrl='https://login.salesforce.com/apex/DotComSales_CallActivity?pid='+item.sam_SFDC_ID;
							//var logUrl=sfdcBaseUrl+'/00T/e?what_id='+item.sam_SFDC_ID;
							var logHtml='<a href="'+logUrl+'" onclick="generateLogs(\'logtaskSAMNew\')" oncontextmenu="generateLogs(\'logtaskSAMNew\')" target="_blank" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;">Log A Task</a>'+
							   ' <a href="'+logUrl+'" onclick="generateLogs(\'logtaskSAMNew\')" oncontextmenu="generateLogs(\'logtaskSAMNew\')" target="_blank" ><i style="padding-top: 4px;color:#fff;font-size: 18px;margin-right: -8px;float:right;" id="logIconId" class="fa fa-file"></i></a>';
							$("#logId").html(logHtml);
							$("#menuItemIcon8").attr("href",logUrl);
							var cls=$("#cnoHead").attr("class");
							if(undefined != cls && ''!=cls && cls.indexOf("cnoCollapse")==-1){ 
							 $("#cnoHead").removeClass("cnoCollapse");
							 $("#cnoHead").addClass("cnoExpand");
							 $("#logHead").removeClass("logCollapse");
							 $("#logHead").addClass("logExpand");
							 $("#sfdcId").css("display","block");
							 
							}else{
							  $("#custCompName").html(checkUundefined(item.companyName));
							  $("#cnoHead").addClass("cnoCollapse");
							  $("#cnoHead").removeClass("cnoExpand");
							  $("#logHead").addClass("logCollapse");
							  $("#logHead").removeClass("logExpand");
							  $("#sfdcId").css("display","none");
							  
							}
							
						}else{
							$("#ulIamIdSec").css("display","none");
							$("#ulIdOther").css("display","none");
							$("#cnoId").html("");
							$("#logId").html("");
							$("#sfdcId").css("display","none");
						}
					}						
					
				});
				//alert(data.customerRewards)
				if(undefined != data.customerRewards && null !=data.customerRewards && ''!=data.customerRewards) {
					var con='<div style="padding:15px">';
				$.each(data.customerRewards, function(i, item) {
				    con += '<div style="padding-left:5px;letter-spacing:1px;" >'
				    	+'<a href="javascript:void();" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: auto;cursor:default;text-decoration:none;color:#4d4d4d;" class="a-size-small a-link-child"><span>'+item.childRewards+'</span></a>'
					+'</div>'
				});
				con += '</div>';
				$("#childInfo").html($.parseHTML(con));
				
				$('#childInfoId').popover({
					/*trigger: "click",*/
					title:'Sub Rewards',
					html : true,
					placement : 'left',
					content : $("#childInfo").html()
				}).on("hover", function(){
			        $('.popover').addClass('popoverBasic');
			    }).on('show.bs.popover', function() {
                  
				}).on("click", function () {
			        var _this = this;
			        $(this).popover("show");
			        $(".popover").on("mouseleave", function () {
			            $(_this).popover('hide');
			        });
			        logUserActivityDotCom(7039, 'viewed sub rewards from SAM recommendation');
			    }).on("mouseleave", function () {
			        var _this = this;
			        setTimeout(function () {
			            if (!$(".popover:hover").length) {
			                $(_this).popover("hide");
			            }
			        }, 300) });
				}else{
					$('#childInfoId').css('display','none');
				}
			}
		 				
		 	generateSegmentDetails($("#custType").html());
		 	$("#loggedInUserNameSpan").html("You logged in as "+$("#loggedInUserName").val());
	        if($(window).width()<=768){
	   		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:2px;padding-right: 2%;");	 
      	   	}else{
	   		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:2px;");	 
	   	    }
		}//success

	})//ajax
	
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

function openShipToDetailsInfo(shipTo,custNum){
	// log user activity; view order details
	var Amount = new Array();
	$("#ShipToLocId").html("");
	$("#ShipToLocId").html(
			" - <span style='padding-left:2px;padding-right:2px;'>" + shipTo
					+ "</span>");
	logUserActivityDotCom(7009, 'View Ship To Order Info');
	var formData = {
		shipToLoc : shipTo,
		sbuName:"STAPLES.COM"
	};
	var mkttrHTML = "";
	var inputString = $('#dataTables-ship_filter input[type=search]').val();

	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/shipment/orders/list/"+ custNum,
				type : "POST",
				data : formData,
				timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var cnt=0;
		//if(data.size)
		if (null != data && undefined != data && data.length == 0 ){
			alert("No orders for this location for the last 2 years");
			return;
		}

		var itemCnt=-1;
		var oldItem;
		var orderNumber; 
		var itemReturned;
		var orderDate ;
		var contactName;								
		var noOfItems = 0;
		var orderTotal = 0;
		
		$.each(data.orders, function(i, item) {
	
			if(item.recordType != "0003")	{
				
				var items1=item.orderNumber.split('.');
				var items2='';
				for(var j=0;j<items1.length;j++)
				{
					if(j!=2 && j!=0)
						{
							items2+='.'+items1[j];
						}
					else if(j==0)
						{
						items2+=items1[j];
						}
				}
				//items2;
				
				if(item.contactName=="null null")
				{
					item.contactName="";
				}
				
				orderDate = checkUundefined(item.orderTranDate);	//??
				if(orderDate != null || orderDate != ''){
					orderDate = orderDate.substring(0,10);
					orderDateArr = orderDate.split("-");
					orderDate = orderDateArr[1] + "/" + orderDateArr[2] + "/" + orderDateArr[0];
				}
				
				var ordertypeDef='';
				//console.log("item : ",item.orderReturned);
				if(item.orderReturned == "true")
				{
					ordertypeDef="(R)";
				}
				else
				{
					ordertypeDef="";
				}
				item.totalCoponAmount=parseFloat(item.totalCoponAmount).toFixed(2);
				item.totalDiscountAmount=parseFloat(item.totalDiscountAmount).toFixed(2);
				
				var rewardNo = checkUundefined(item.orderIssuer);
				if (rewardNo.length > 0){
					if (rewardNo.charAt(rewardNo.length - 1) == 'P') {
						rewardNo = rewardNo.replace('P', 'M');
						//rewardNo = "<span style='color:#0000FF;'>"+rewardNo+"</span>";
					}
					if (rewardNo.charAt(rewardNo.length - 1) == 'C') {
						rewardNo = rewardNo.replace('C', 'S');
						//rewardNo = "<span style='color:#FF00FF;'>"+rewardNo+"</span>";
					}
				}
				
				var productSKUs = new Array();
				var productDescs = new Array();
				productSKUObj = item.orderLevelItems;
				if ( (undefined != productSKUObj) || (null != productSKUObj) ){
					$.each(productSKUObj, function(i, orderItem) {
						productSKUs.push(orderItem.sku);
						productDescs.push(orderItem.productName);
					});
				}
				
				mkttrHTML += '<tr class="odd gradeX">'
					//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
					
					 +'<td class="datatablesTd">'+rewardNo+'</td>'
					 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsShipTO(\''+checkUundefined(item.orderNumber)+'\',\''+shipTo+'\');" style="text-decoration:underline;padding-right:6px;">'
					 + checkUundefined(items2)+'</a><span style="color:red;">'+ordertypeDef+'</span></td>'
					 //+'<td class="datatablesTd">'
					 //+ checkUundefined(item.orderReturned) +'</td>'
					 +'<td class="datatablesTd">'
					 + orderDate +'</td>'
					 +'<td>'+checkUundefined(Number(item.orderItemsCount))+'</td>'	// ??
					 +'<td class="datatablesTd formatClsOrd">'
					 + formatNum(chkNegAmount(checkUundefined(parseFloat(item.tranTotalAmount).toFixed(2)))) +'</td>'
					 +'<td class="datatablesTd">'+formatNum(chkNegAmount(checkUundefined(item.totalDiscountAmount)))+'</td>'
					 +'<td class="datatablesTd">'+formatNum(chkNegAmount(checkUundefined(item.totalCoponAmount)))+'</td>'
					 +'<td class="datatablesTd">'
					 + checkUundefined(item.contactName) +'</td>'
					 +'<td class="datatablesTd">'
					 + productSKUs.toString() +'</td>'												
					 +'<td class="datatablesTd">'
					 + productDescs.toString() +'</td>'												
					 +'</tr>';
				
				cnt++;
			}
		});
			
			
		
		ShipOrderDeatilCount = cnt-1;
		//alert(data);
		$('#ShipDetailsInfoId').html("");
		$('#ship-info').modal({
		    
			 "backdrop"  : "static",
			 "keyboard"  : true,
			 "show"      : true           
		});
		$('#ShipDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-ShipInfo" width="100%" ><thead><tr>'

				+'<th style="width:101px;">Reward #</th>'
				+'<th>Order No.</th>'
				+'<th>Order Date</th>'
				+'<th>No. Of Items</th>'
				+'<th>Order Total</th>'
				+'<th>Discounts</th>'
				+'<th>Coupons</th>'
				+'<th>Order Contact</th>'
				+'<th>productSKUs</th>'		// hidden field, only for search
                +'<th>productDescs</th>'	// hidden field, only for search
                + '</tr>'
				+ '</thead><tbody>' + mkttrHTML
			+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
	
		var oTable=$('#dataTables-ShipInfo').DataTable({
			"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
			"jqueryUI":true,
			"order": [],
			"oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true},
				{"bSortable": true,"visible":true,"sWidth": '11%'},
				{"bSortable": true,"visible":true,"sWidth": '7%'},
				{"bSortable": true,"sWidth": '7%'},
				{"bSortable": true,"sWidth": '7%'},
				{"bSortable": true,"sWidth": '7%'},
				{"bSortable": true,"sWidth": '7%'},
				{"bSortable": true,"sWidth": '15%'},
				{"bSortable": true,"visible":false,"sWidth": '1%'},
				{"bSortable": true,"visible":false,"sWidth": '1%'}
				],
			"fnDrawCallback": function( oSettings ) {
				
				if (ShipOrderDeatilCount != -1) {
					for (var count = 0; count <= ShipOrderDeatilCount; count++) {
						$('#ShipOrderIdDtl'+count).popover({
							html : true,
							placement : 'right',
							content : $("#ShiporderdivId"+count).html()
						}).on("hover", function(){
					        $('.popover').addClass('popoverBasic');
					    }).on('show.bs.popover', function() {

						});
					}
				}
			}
		});
		
	
		$("#dataTables-ShipInfo td").css("WORD_BREAK","BREAK-ALL");
		$('#dataTables-ShipInfo_length label').css("color","#004c74");
		$('#dataTables-ShipInfo_length label').css("color","#004c74");
		$('#dataTables-ShipInfo_length label').css("font-weight","600");
		$('#dataTables-ShipInfo_filter label').css("color","#004c74");
		$('#dataTables-ShipInfo_filter label').css("letter-spacing","1px");
		$('#dataTables-ShipInfo_filter').css("text-align","right");
		$('#dataTables-ShipInfo_filter label').css("font-weight","600");
		$('#dataTables-ShipInfo_filter label').css("float","right");
		$('#dataTables-ShipInfo_info').css("color","#004c74");
		$('#dataTables-ShipInfo_info').css("font-weight","600");
		$('#dataTables-ShipInfo_paginate').css("text-align","right");
		$("#dataTables-ShipInfo_info").parent().removeClass("col-sm-6").addClass("col-sm-4").css("padding-right","1px");
		$("#dataTables-ShipInfo_paginate").parent().removeClass("col-sm-6").addClass("col-sm-8");
		$('#dataTables-ShipInfo_paginate .pagination>li>a').attr("style","padding : 6px 6px !important");
		$('.form-inline .form-control').css("color","#004c74");
		$('.form-inline .form-control').css("border","1px solid #004c74");
		$('select .form-control.input-sm').css("border","1px solid #004c74");
		$('#dataTables-ShipInfo').removeClass('display').addClass('table table-striped table-bordered');
		$("#dataTables-ShipInfo_filter").parent().removeClass("col-sm-6").addClass("col-sm-8");
		$("#dataTables-ShipInfo_length").parent().removeClass("col-sm-6").addClass("col-sm-4");
		$('#dataTables-ShipInfo_filter input[type="search"]').attr('placeholder','Enter Order No. OR Item No. OR Item Desc.').attr("style","width : 260px !important").css("display","inline-block");
	    $('#dataTables-ShipInfo_filter input[type="search"]').focus();
	}//success
	
	})//ajax
}


function openOrderDetailsShipTO(order_number,shipTo){
	// log user activity; view order details
	logUserActivityDotCom(7010, 'View Ship To Order Line Info');
	$("#shplocId").html("");
	$("#shplocId").html("Ship To Order Info "+shipTo+"<span style='padding-left:3px;padding-right:3px;'>-</span>"+order_number);
	var inputString=$('#dataTables-ShipInfo_filter input[type="search"]').val();
	var contains=0;
	var mkttrHTML="";
	var formData={};
	var custid=$("#reqCustNum").val();
	$("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	  $("#dataTables-order_processing").css("display","block");
	//alert(inputString);
	$.ajax({
		dataType: "json",
		url : ctx+"/sales/customer/"+custid+"/"+order_number+"/info?sbuName=STAPLES.COM",
	 //url : "/getShipToData/"+custid,
		type : "GET",
		//cache : false,
		data : "",//JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			
	$.each(data.orderDetails.orderLineItems, function(i, item) {
		var discount=0;
		var coupon=0;
		
		if (inputString != undefined && inputString != null &&
				item.skuNumber != undefined && item.skuNumber != null &&
				item.itemDescription != undefined && item.itemDescription != null &&
				(item.skuNumber.toLowerCase().indexOf(inputString.toLowerCase()) > -1 || item.itemDescription.toLowerCase().indexOf(inputString.toLowerCase()) > -1)){
			contains=1;
		}
		
		if(item.lineDiscount != undefined || item.lineDiscount != null) {
			$.each(item.lineDiscount, function(i, discountItem) {
				
				discount += (checkUundefined(discountItem.discountAmount) * 100);
			});
			
			if (discount == 0){
				discount = chkNegAmount(formatNum("0"));
			}else{
				discount = chkNegAmount(formatNum((checkUundefined(discount)/100)));
			}
			
		}else{
			
			discount = chkNegAmount(formatNum("0"));	//discount = 0
			
		}
		
		if(item.couponItem != undefined || item.couponItem != null) {			
			$.each(item.couponItem, function(i, couponItem) {
				
				coupon += (checkUundefined(couponItem.couponLineAmount) * 100);
			});
			
			if (coupon == 0){
				coupon = chkNegAmount(formatNum("0"));
			}else{
				coupon = chkNegAmount(formatNum((checkUundefined(coupon)/100)));
			}
			
			
		}else{
			
			coupon = chkNegAmount(formatNum("0"));	//coupon = 0
			
		}
			
		var totalSpend = '';
		var productSKU ='<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.productSKU)+'</a>';
        if(order_number.startsWith("POS")) {
			
			totalSpend = chkNegAmount(formatNum(checkUundefined(item.extendedPrice)));
			if( checkUundefined(item.tranLineStatusId).length > 0 && checkUundefined(item.tranLineStatusId) == "RETURN") {
				productSKU = "<span style='color:red;'>"+productSKU+"</span>";
			}
		}else{
			
			totalSpend = chkNegAmount(formatNum(checkUundefined(item.lineTotalAmount)));
			if(checkUundefined(item.masterSalesTranId) != -1 && checkUundefined(item.masterSalesTranId) != '' && checkUundefined(item.masterSalesTranId) != null) {
				productSKU = "<span style='color:red;'>"+productSKU+"</span>";
			}else if(checkUundefined(item.masterSalesTranId) == -1 || checkUundefined(item.masterSalesTranId) == '' || checkUundefined(item.masterSalesTranId) == null){
				productSKU = "<span style=''>"+productSKU+"</span>";
			}
		}
			
		var itemStatusDesc = checkUundefined(item.itemStatusDescription);
		if (itemStatusDesc.length > 0 && itemStatusDesc == "-1"){
			itemStatusDesc = "DELIVERED";
		}		
		
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold; !important;">'
			 + productSKU +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.productName) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.transQuantity) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + discount +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + coupon +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + totalSpend +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + itemStatusDesc +'</td>'				 
			 +'</tr>';
		
		
	})
	$("#dataTables-order_processing").css("display","none");
	$('#shipOrderDetailsInfoId').html("");
	$('#shiporder-info').modal({
	    
		"backdrop"  : "static",
		"keyboard"  : true,
		"show"      : true           
	}); 
	$('#shipOrderDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-shipOrderInfo" width="100%" ><thead><tr>'
			+'<th>Item No.</th>'
			+'<th>Item description</th>'
	        +'<th>Qty</th>'
	    	+'<th>Discount</th>'
	        +'<th>Coupon</th>'
	        +'<th>Total Spend</th>'
	        +'<th>Status</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	var oTable=$('#dataTables-shipOrderInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "order": [],
		  "oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'},
				{"bSortable": true, "sWidth": '33%'}
			],
			

	})	
		
	
	if(contains == 1){
		$('#dataTables-shipOrderInfo_filter input[type=search]').val(inputString);
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
	$("#dataTables-shipOrderInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-shipOrderInfor_length label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_length label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_length label').css("font-weight","600");
	$('#dataTables-shipOrderInfo_filter label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_filter label').css("letter-spacing","1px");
	$('#dataTables-shipOrderInfo_filter').css("text-align","right");
	$('#dataTables-shipOrderInfo_filter label').css("font-weight","600");
	$('#dataTables-shipOrderInfo_filter label').css("float","right");
	$('#dataTables-shipOrderInfo_filter label').addClass("col-lg-8 col-sm-12 col-md-9");
	$('#dataTables-shipOrderInfo_filter label').css("text-align","center");
	$('#dataTables-shipOrderInfo_info').css("color","#004c74");
	$('#dataTables-shipOrderInfo_info').css("font-weight","600");
	$('#dataTables-shipOrderInfo_paginate').css("text-align","right");
	
	$('.form-inline .form-control').css("color","#004c74");
	$('.form-inline .form-control').css("border","1px solid #004c74");
	$('select .form-control.input-sm').css("border","1px solid #004c74");
	$('#dataTables-shipOrderInfo').removeClass('display').addClass('table table-striped table-bordered');
    if(contains==0)
	$('#dataTables-shipOrderInfo_filter input[type="search"]').attr('placeholder','Enter Item No. OR Item Desc.').css({'width':'250px','display':'inline-block','padding-right':'1px'});
    $('#dataTables-shipOrderInfo_filter input[type="search"]').focus();
	
    
//    oTable.search($(this).val()).draw(); 
    if (order_number.toString().indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString);
    	oTable.draw();
    }
    
	
		}})
	}


function openReturnedOrderDetailsShipTO(order_number,shipTo,order_date){
	// log user activity; view order details
	logUserActivityDotCom(7019, 'Clicked Returns on Ship To Order Info grid');
	$("#shplocId").html("");
	$("#shplocId").html("Returned Info "+shipTo+"<span style='padding-left:3px;padding-right:3px;'>-</span>"+order_number);
	var inputString=$('#dataTables-ShipInfo_filter input[type="search"]').val();
	var contains=0;
	var mkttrHTML="";
	var formData={};
	var custid=$("#reqCustNum").val();
	//alert(inputString);
	$.ajax({
		dataType: "json",
		url : ctx+"/getOrderDetailsShipTO/"+custid+"/"+order_number+"/"+order_date,
	 //url : "/getShipToData/"+custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
	$.each(data, function(i, item) {
		
		
		if (item.netSpendAmount >= 0) {
			return true;
		}
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:17%;">'
			 + '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber)+'</a>' +'</td><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:18%;">'
			 + checkUundefined(item.tranDate) +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
			 + checkUundefined(item.itemDescription) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:15%;">'
			 + checkUundefined(item.totalQty) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:15%;">'
			 + chkNegAmount(formatNum(checkUundefined(item.totalPriceAmount))) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + formatNum(checkUundefined(item.couponAmount)) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:25%;">'
			 + chkNegAmount(formatNum(checkUundefined(item.netSpendAmount))) +'</td>'
			 +'</tr>';
		
	})
	
	$('#shipOrderDetailsInfoId').html("");
	$('#shiporder-info').modal({
	    
		 "backdrop"  : "static",
	"keyboard"  : true,
	"show"      : true           
	}); 
	$('#shipOrderDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-shipOrderInfo" width="100%" ><thead><tr>'
			+'<th style="text-align:center;line-height:2.5;white-space:nowrap"">Item No.</th>'
			+'<th style="text-align:center;line-height:2.5;">Return Date</th>'
			+'<th style="text-align:center;line-height:2.5;">Item Description</th>'
	        +'<th style="text-align:center;line-height:2.5;">Qty</th>'
	    	+'<th style="text-align:center;line-height:2.5;">Price</th>'
			+'<th style="text-align:center;line-height:2.5;">Coupons</th>'
	        +'<th style="text-align:center;line-height:2.5;white-space:nowrap"">Total Spend</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	var oTable=$('#dataTables-shipOrderInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "oLanguage": { "sSearch": "Filter: "},
		  "order": [],
			"aoColumns": [{"bSortable": true},{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true, "visible" : false},
			{"bSortable": true}
			],
			/*"fnDrawCallback": function (oSettings) {
				        $(".dataTables_filter").each(function () {
				            $(this).appendTo($(this).parent().siblings(".panel-body"));
				        })
				    }
				
			})*/

	})	
		
	if(inputString!=null && inputString!='')
			contains=1;
	if(contains == 1){
		$('#dataTables-shipOrderInfo_filter input[type=search]').val(inputString);
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
//alert($('#dataTables-OrderInfo_filter input[type=search]').val());
	//$("#dataTables-OrderInfo td").css("padding-left","10px");
	$("#dataTables-shipOrderInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-shipOrderInfor_length label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_length label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_length label').css("font-weight","600");
	$('#dataTables-shipOrderInfo_filter label').css("color","#004c74");
	$('#dataTables-shipOrderInfo_filter label').css("letter-spacing","1px");
	$('#dataTables-shipOrderInfo_filter').css("text-align","right");
	$('#dataTables-shipOrderInfo_filter label').css("font-weight","600");
	$('#dataTables-shipOrderInfo_filter label').css("float","right");
	$('#dataTables-shipOrderInfo_filter label').addClass("col-lg-8 col-sm-12 col-md-9");
	$('#dataTables-shipOrderInfo_filter label').css("text-align","center");
	$('#dataTables-shipOrderInfo_info').css("color","#004c74");
	$('#dataTables-shipOrderInfo_info').css("font-weight","600");
	$('#dataTables-shipOrderInfo_paginate').css("text-align","right");
	
	$('.form-inline .form-control').css("color","#004c74");
	$('.form-inline .form-control').css("border","1px solid #004c74");
	$('select .form-control.input-sm').css("border","1px solid #004c74");
	$('#dataTables-shipOrderInfo').removeClass('display').addClass('table table-striped table-bordered');
    $('#dataTables-shipOrderInfo_filter input[type="search"]').attr('placeholder','Enter Item No. OR Item Desc.').css({'width':'250px','display':'inline-block','padding-right':'1px'});
    $('#dataTables-shipOrderInfo_filter input[type="search"]').focus();
	
    
//    oTable.search($(this).val()).draw(); 
    if (order_number.toString().indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString);
    	oTable.draw();
    }
    
	
		}})
	}

function formatNum(n) {
	if (n != '') {
		var commaFormatedVal = n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g,
				"$1,");
		if (commaFormatedVal.indexOf(".") != -1) {
			if ((commaFormatedVal.split(".")[1]).length == 1) {
				commaFormatedVal = commaFormatedVal.split(".")[0] + "."
						+ commaFormatedVal.split(".")[1] + '0';
			}
		} else {
			commaFormatedVal = commaFormatedVal + ".00";
		}
		return '$' + commaFormatedVal;
	} else {
		return '';
	}
}
function formatNumUserGrid(n){
    if(n != '' && n != '0'){
          var commaFormatedVal= n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
          if(commaFormatedVal.indexOf(".")!=-1){
                if((commaFormatedVal.split(".")[1]).length==1){
                      commaFormatedVal=commaFormatedVal.split(".")[0]+"."+commaFormatedVal.split(".")[1]+'0';
                }
          }else{
                commaFormatedVal=commaFormatedVal+".00";
          }
        return '$'+commaFormatedVal;
    } else {
          return '';
    }
}
function formatCallOrder(callorder, cnt) {
	if ((undefined != callorder) && ('' != callorder) && ('-' != callorder))
		return cnt;
	else
		return callorder;
}
function checkUundefinedNullBlankZero(val) {
	if ((undefined != val) && ('null' != val) && (null != val) && ('0' != val)
			&& (0 != val)) {
		return val;
	} else {
		return "-";
	}
}
function checkUserGridFields(val) {
	if ((undefined != val) && ('null' != val) && (null != val) && ('0' != val) && (0 != val) && ('' != val) && ('-' != val) ) {
		return val;
	} else {
		return "";
	}
}
function checkUundefined(val) {
	if (undefined != val && val != 'null') {
		return val;
	} else {
		return "";
	}
}
function checkNull(val) {
	if (undefined != val && val != 'null' && null != val && val.length) {
		return 'Y';
	} else {
		return 'N';
	}
}

function submitOnEnter(e,searchText,acctId) {
    var keycode;
    if (window.event) keycode = window.event.keyCode;
    else if (e) keycode = e.which;
    else return true;
    if (keycode == 13) {
    	searchCustomer(searchText,acctId);
        return false;
    }
}

function searchCustomer(searchText,acctId){
	showProgress();
	if(undefined ==searchText || '' ==searchText){
		hideProgress();
		alert("Please enter the search criteria.");
		return;
	}
	
	//searchText=searchText.replace(/[^a-zA-Z0-9]/g, '');
	//alert(searchText);
	var formData={'searchText' : searchText, 'acctId': acctId, 'custNum': $('#query').val() };
	$.ajax({
		dataType: "json",
		url : ctx+"/getCustomerNumber", 
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			
			if(data != null && data != undefined && data != 'failure'){
				if(data.indexOf("-")!=-1){
					assignGrp=data.split("-")[1];
					data=data.split("-")[0];
					if(undefined !=assignGrp && ""!=assignGrp && 'SAM'==assignGrp){
						opencustDetailsLead(data);
					}else if(undefined !=assignGrp && ""!=assignGrp && 'SAMD'==assignGrp){
						opencustDetailsSAM(data);
					}else{
						opencustDetailsMM(data);
					}
				}
				
			} else {
				hideProgress();
				//alert("Either the search citeria is wrong or the account manager is not matching.");
				alert("To search for a different customer on this screen the customer must be part of your Book of Business. Please enter the complete company name or customer number. To search for a different customer with partial information please do so via the To Do list.");
			}
			logUserActivityDotCom(7045, 'User has perform global search from SAM recommendation');
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            
        	
        	     	   var cooki = document.cookie.split(';');
           	
        	   for(var i = 0 ; i < cooki.length ; i++){
        		var cookival = cooki[i].toString();
        		if (cookival.indexOf('SMSESSION=LOGGEDOFF') > -1){
        			
        			window.location.reload();
        		}
        	}
        	
        }
        /*,
        complete: function onRequestCompleted(xhr,textStatus) {
        	var contentType= xhr.getResponseHeader("Content-Type");
            var loc=xhr.getResponseHeader("Location")
            location.href = xhr.getResponseHeader("Location");
            if (xhr.status == 302 || contentType.toLowerCase().indexOf("text/html") >= 0) {
                
                window.location.reload();
          }
       }*/
	
		
	});
}



function opencustDetails(cNum){
	
	 $("#customerForm").attr("action","./home_template4")
		$("#reqCustNum").val(cNum);
	  $('#customerForm').submit();
		
}
// ZISHAN ZAHID
// /getBoughtRecommendation/{custNum}/{orderContact}
// /getRecommOrderContacts/{
function getOrderContactForBAB() {
	var formData = {};
	var orderContact = 'ZISHAN ZAHID';
	var custid = $("#reqCustNum").val();
	var dt = geDataRefreshTime('BABRECOM');
	//alert(dt);
	$("#updateDateValueBAB span").html(dt+" ET");
	$.ajax({
		dataType: "json",
		url : ctx + "/getRecommOrderContacts/" + custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var customerNum = 0;
			var orderSeq = '';
			var mkttrHTML = "";
			// $('#customerList').html("");
			var name = '';
			var count = 0;
			if (data != null && data != undefined) {

				$.each(data, function(i, item) {
					// alert(item.customerNumber+"-"+item.orderContact+"-"+item.orderDate+"-"+item.skuNumber+"-"+item.recSkuList+"-"+item.itemDescription+"-"+item.thumbnail+"-"+item.custRecommendationVOList);
					var valCount = 0;
					var orderConArr = new Array();
					$.each(data, function(i, item) {
						orderConArr[valCount] = item;
						valCount++;
					});
					var selectedDeviceModel = $('#orderConId');
					selectedDeviceModel.empty();
					/*
					 * selectedDeviceModel.append($('<option/>', { value : '',
					 * text : 'Select'
					 */
					selectedDeviceModel.append($('<option/>', {
						value : 'select',
						text : '--SELECT--'
					}));
					var count = 0;
					for (count = 0; count < orderConArr.length; count++) {
						selectedDeviceModel.append($('<option/>', {
							value : orderConArr[count],
							text : orderConArr[count]
						}));
					}
					$('#orderConId option:eq(1)').attr('selected', 'selected');
					
				});
				getBoughtAlsoBught($('#orderConId').val());
			}

		}

	})
}


function getBoughtAlsoBught(orderContact) {
	if (undefined != orderContact && orderContact != 'select') {
		var formData = {orderContact : orderContact};
		var orderCon = orderContact;
		var custid = $("#reqCustNum").val();
		$("#BABTabId")
		.html("");
		
		$
				.ajax({
					url : ctx + "/getBoughtRecommendation/" + custid ,
					type : "POST",
					cache : false,
					data : formData,
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {

						var customerNum = 0;
						var orderSeq = '';
						var mkttrHTML = "";
						// $('#customerList').html("");
						var name = '';
						var count = 0;
						totalBaBLength=0;
						maxBaBdisplaylength=5;
						 //alert(data+" data length="+data.length);
						
						if (data != null && data != undefined) {
							 //totalAbandonedLength=5;
							// alert("total len="+totalAbandonedLength)
							var prefix = '<div id="amazon_scroller_bought_a_bought" class="amazon_scroller">'
									+ '<div class="amazon_scroller_mask" id="BabMaskId">'
									+ '<ul id="ulidBAB">'
							var suffix = '</ul></div>'
									+ '<ul id="boughtAbought" class="amazon_scroller_nav">'
									+ '<li><a href="javascript:void()" class="btn btn-sm default prev" title="Prev" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
									+ '<li><a href="javascript:void()" class="btn btn-sm default next" title="Next" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
									+ '</ul>'
									+ '<div style="clear: both"></div>'
									+ '</div>';
							var htmlData = '';
							$
									.each(
											data,
											function(i, item) {
												 //alert(item.customerNumber+"-"+item.orderContact+"-"+item.orderDate+"-"+item.skuNumber+"-"+item.recSkuList+"-"+item.itemDescription+"-"+item.thumbnail+"-"+item.custRecommendationVOList);
												// List <CustRecommendationVO>
												// custRecommendationVOList;
												 //alert(item.recSkuList.length)
												// alert(item.recSkuList[0])
												// alert("my html data =
												// "+htmlData)

												if (undefined != item.recSkuList
														&& (item.recSkuList.length >= 2)) {
													var thumbnail = checkUundefined(item.thumbnail);
													if (thumbnail == ''
															|| null == thumbnail
															| undefined == thumbnail) {
														thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
													} else {
														thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
																+ thumbnail;
													}
													var skuDetailURL="#";
													if (item != undefined && item != null && 
															item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
														//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
														skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
														
													}
													
													htmlData = htmlData
															+ '<li style="border: 1px solid green;width: 440px;height:350px; display: block;">'

															+ '<div style="float:left;width:135px;">'
															+ '<img src='
															+ thumbnail
															+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

															+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
															+ checkUundefined(item.itemDescription)
															+ '</div>'
															+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
															+ checkUundefined(item.skuNumber)
															+ '</a></div>'
															+ '<div class="a-row a-size-small">'
															+ item.orderDate
															+ '</div>'
															+ '</div>';
													// alert("after
													// append"+htmlData)
													if (undefined == item.custRecommendationVOList
															|| item.custRecommendationVOList == null
															|| item.custRecommendationVOList == '') {
														// alert("null")
														for (var cnt = 0; cnt < 2; cnt++) {
														
															thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1";

															var skuDetailURL="#";
															if (item != undefined && item != null && 
																	item.recSkuList[cnt] != undefined && item.recSkuList[cnt] != null && item.recSkuList[cnt] != '') {
																//skuDetailURL = 'http://www.staples.com/product_'+item.recSkuList[cnt];
																skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.recSkuList[cnt]+'&storeId=10101&itemType=1';
																
															}
															htmlData = htmlData
																	+ '<div style="float:left;width:135px;">'
																	+ ' <img src='
																	+ thumbnail
																	+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

																	+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
																	
																	+ '</div>'
																	+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
																	+ checkUundefined(item.recSkuList[cnt])
																	+ '</a></div>'
																	
																	+ '</div>';

														}
													} else {  //alert("not null"+item.custRecommendationVOList)
																// null")
														$
																.each(
																		item.custRecommendationVOList,
																		function(
																				i,
																				item) { //alert(item.skuNumber)
																			// alert("SUb======"+item.skuNumber+"-"+item.itemDescription+"-"+item.orderContact+"-"+item.thumbnail);
																			thumbnail = checkUundefined(item.thumbnail);
																			if (thumbnail == ''
																					|| null == thumbnail
																					| undefined == thumbnail) {
																				thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
																			} else {
																				thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
																						+ thumbnail;
																			}
																			var skuDetailURL="#";
																			if (item != undefined && item != null && 
																					item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
																				//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
																				skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
																			}
																			htmlData = htmlData
																					+ '<div style="float:left;width:135px;">'
																					+ ' <img src='
																					+ thumbnail
																					+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

																					+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
																					+ checkUundefined(item.itemDescription)
																					+ '</div>'
																					+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
																					+ checkUundefined(item.skuNumber)
																					+ '</a></div>'
																					+ '</div>';
																		});
													//alert(item.custRecommendationVOList.length)
													if(item.custRecommendationVOList.length==1){
														thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1";
														var skuDetailURL="#";
														if (item != undefined && item != null && 
																item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
															//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
															skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
														}
														htmlData = htmlData
														+ ' <div style="float:left;width:135px;">'
														+ '<img src='
														+ thumbnail
														+ ' width="124" height="160" alt="Head First HTML with CSS & XHTML" style="width: 135px; height: 160px;" />'
														+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
														+ checkUundefined(item.skuNumber)
														+ '</a></div>'
														+ '</div>'

													}
													}
													htmlData = htmlData
															+ '</li>';
													// alert("group =
													// "+htmlData)

													// alert(prefix
													// +htmlData+suffix);
													totalBaBLength=totalBaBLength+3;
												} else if (undefined != item.recSkuList
														&& item.recSkuList.length == 1) {
													var thumbnail = checkUundefined(item.thumbnail);
													if (thumbnail == ''
															|| null == thumbnail
															| undefined == thumbnail) {
														thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
													} else {
														thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
																+ thumbnail;
													}
													var skuDetailURL="#";
													if (item != undefined && item != null && 
															item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
														//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
														skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
													}
													htmlData = htmlData
															+ '<li style="border: 1px solid green;width: 290px;height:350px; display: block;">'

															+ '<div style="float:left;width:135px;">'
															+ '<img src='
															+ thumbnail
															+ ' width="124" height="160" alt="Head First HTML with CSS & XHTML" style="width: 135px; height: 160px;"/>'

															+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
															+ checkUundefined(item.itemDescription)
															+ '</div>'
															+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
															+ checkUundefined(item.skuNumber)
															+ '</a></div>'
															+ '<div class="a-row a-size-small">' 
															+ item.orderDate
															+ '</div>'
															+ '</div>';

													// alert("after
													// append"+htmlData)
													if (undefined == item.custRecommendationVOList
															|| item.custRecommendationVOList == null
															|| item.custRecommendationVOList == '') {
														 //alert("null")
														for (var cnt = 0; cnt < 1; cnt++) {

															thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1";
															var skuDetailURL="#";
															if (item != undefined && item != null && 
																	item.recSkuList[cnt] != undefined && item.recSkuList[cnt] != null && item.recSkuList[cnt] != '') {
																//skuDetailURL = 'http://www.staples.com/product_'+item.recSkuList[cnt];
																skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.recSkuList[cnt]+'&storeId=10101&itemType=1';
															}

															htmlData = htmlData
																	+ ' <div style="float:left;width:135px;">'
																	+ '<img src='
																	+ thumbnail
																	+ ' width="124" height="160" alt="Head First HTML with CSS & XHTML" style="width: 135px; height: 160px;" />'

																	+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
																	
																	+ '</div>'
																	+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
																	+ checkUundefined(item.recSkuList[cnt])+'</a></div>'
																	+ '</div>';

														}
													} else {  //alert("not null")
														$
																.each(
																		item.custRecommendationVOList,
																		function(
																				i,
																				item) {
																			// alert("SUb======"+item.skuNumber+"-"+item.itemDescription+"-"+item.orderContact+"-"+item.thumbnail);
																			thumbnail = checkUundefined(item.thumbnail);
																			if (thumbnail == ''
																					|| null == thumbnail
																					| undefined == thumbnail) {
																				c
																			} else {
																				thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
																						+ thumbnail;
																			}
																			var skuDetailURL="#";
																			if (item != undefined && item != null && 
																					item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
																				//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
																				skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
																			}
																			htmlData = htmlData
																					+ ' <div style="float:left;width:135px;">'
																					+ '<img src='
																					+ thumbnail
																					+ ' width="124" height="160" alt="Head First HTML with CSS & XHTML" style="width: 135px; height: 160px;" />'

																					+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
																					+ checkUundefined(item.itemDescription)
																					+ '</div>'
																					+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
																					+ checkUundefined(item.skuNumber)
																					+ '</a></div>'
																					+ '</div>'

																		});
														
													}
													htmlData = htmlData
															+ '</li>';
													//alert("group = "+htmlData)

													//alert(prefix +htmlData+suffix);
													totalBaBLength=totalBaBLength+2;
												}

												count++;
											});
							if(totalBaBLength%2==0 && totalBaBLength!=6)
			                	totalBaBLength=totalBaBLength-1;
							//alert("totalAbandonedLength="+totalAbandonedLength)
							//alert("ALL DATA--> "+prefix +htmlData+suffix);
							$("#BABTabId").html("");
							//alert(htmlData)
							if(htmlData!='')
							$("#BABTabId").html(prefix + htmlData + suffix)
							else
								$("#BABTabId")
								.html(
										"<div id='BabNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
							$("#amazon_scroller_bought_a_bought")
									.amazon_scroller(
											{
												scroller_title_show : 'disable',
												scroller_time_interval : '1800000000000',
												scroller_window_background_color : "#CCC",
												scroller_window_padding : '10',
												scroller_border_size : '1',
												scroller_border_color : '#000',
												scroller_images_width : '135',
												scroller_images_height : '160',
												scroller_title_size : '12',
												scroller_title_color : 'black',
												scroller_show_count : '4',
												directory : 'images'
											});
						}

					}

				})
	} else {
		$("#BABTabId")
				.html(
						"<div id='BabNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
	}
}


/*
//Sanjay Added
function getBoughtAlsoBught(orderContact) {
	if (undefined != orderContact && orderContact != 'select') {
		var formData = {orderContact : orderContact};
		var orderCon = orderContact;
		var custid = $("#reqCustNum").val();
		var accId = $("#accId").val();
		$("#BABTabId").html("");
		
		$.ajax({
			
			url : ctx + "/recommendation/customer/"+ custid +"/user/"+ accId +"/level/root",		
			type : "GET",
			//cache : false,
			data : "",//JSON.stringify(formData),
			timeout : 1000000,
			success : function(data, textStatus, jqXHR) {
				var customerNum = 0;
				var orderSeq = '';
				var mkttrHTML = "";
				// $('#customerList').html("");
				var name = '';
				var count = 0;
				totalBaBLength=0;
				maxBaBdisplaylength=5;
				//alert(data+" data length="+data.length);
				
				if (data != null && data != undefined) {
					 //totalAbandonedLength=5;
					// alert("total len="+totalAbandonedLength)
					var prefix = '<div id="amazon_scroller_bought_a_bought" class="amazon_scroller">'
							+ '<div class="amazon_scroller_mask" id="BabMaskId">'
							+ '<ul id="ulidBAB">'
					var suffix = '</ul></div>'
							+ '<ul id="boughtAbought" class="amazon_scroller_nav">'
							+ '<li><a href="javascript:void()" class="btn btn-sm default prev" title="Prev" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '<li><a href="javascript:void()" class="btn btn-sm default next" title="Next" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '</ul>'
							+ '<div style="clear: both"></div>'
							+ '</div>';
					var htmlData = '';
					
					
					$.each(data[1].products, function(i, item) {
										 
						//if (undefined != item.recSkuList && (item.recSkuList.length >= 2)) {
							
							var thumbnail = checkUundefined(item.imageurl);
							if (thumbnail == '' || null == thumbnail | undefined == thumbnail) {
								thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
							} 
							
							var skuDetailURL="#";
							if ( checkUundefined(item.sku) != '') {
								//skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
								skuDetailURL = checkUundefined(item.url) ;
							}
											
							htmlData = htmlData
									+ '<li style="border: 1px solid green;width: 440px;height:350px; display: block;">'

									+ '<div style="float:left;width:135px;">'
									+ '<img src='
									+ thumbnail
									+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

									+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
									+ checkUundefined(item.desc)
									+ '</div>'
									+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
									+ checkUundefined(item.sku)
									+ '</a></div>'
									+ '<div class="a-row a-size-small">'
									+ checkUundefined(item.orderDate)
									+ '</div>'
									+ '</div>';
								
							// alert("after
							// append"+htmlData)
							
							htmlData = htmlData
										+ '</li>';
								// alert("group =
								// "+htmlData)

								// alert(prefix
								// +htmlData+suffix);
							totalBaBLength = totalBaBLength + 1;
							 

							count++;
						});
					if( (totalBaBLength % 2 == 0) && (totalBaBLength != 6) )
	                	totalBaBLength = totalBaBLength - 1;
					//alert("totalAbandonedLength="+totalAbandonedLength)
					//alert("ALL DATA--> "+prefix +htmlData+suffix);
					$("#BABTabId").html("");
					//alert(htmlData)
					if(htmlData!='')
						$("#BABTabId").html(prefix + htmlData + suffix)
					else
						$("#BABTabId")
						.html(
								"<div id='BabNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
					$("#amazon_scroller_bought_a_bought")
							.amazon_scroller(
									{
										scroller_title_show : 'disable',
										scroller_time_interval : '1800000000000',
										scroller_window_background_color : "#CCC",
										scroller_window_padding : '10',
										scroller_border_size : '1',
										scroller_border_color : '#000',
										scroller_images_width : '135',
										scroller_images_height : '160',
										scroller_title_size : '12',
										scroller_title_color : 'black',
										scroller_show_count : '4',
										directory : 'images'
									});
				}

			}

		})
	} else {
		$("#BABTabId")
				.html(
						"<div id='BabNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
	}
}
*/

function getPlayRescommt() {
	
		var formData = {};
		//var orderCon = orderContact;
		var custid = $("#reqCustNum").val();
		if (undefined != custid && custid != 'select') {
		// alert(ctx + "/getBoughtRecommendation/" + custid + "/" +
		// orderContact)
		$
				.ajax({
					url : ctx + "/getClassRecommendations/" + custid ,
							
					type : "POST",
					cache : false,
					data : JSON.stringify(formData),
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {

						var customerNum = 0;
						var orderSeq = '';
						var mkttrHTML = "";
						// $('#customerList').html("");
						var name = '';
						var count = 0;
						totalPlayRecomLength=0;
						 //alert(data+" data length="+data.length);
						
						if (data != null && data != undefined) {
							 //totalAbandonedLength=5;
							// alert("total len="+totalAbandonedLength)
							var prefix = '<div id="amazon_scroller_play_recom" class="amazon_scroller">'
							+'<div class="amazon_scroller_mask" id="playRecomMaskId">'
							+'<ul id="ulidPLAY">';
							var suffix = '</ul></div><ul id="PlayRecom" class="amazon_scroller_nav">'
							+'<li><a href="javascript:void();" class="btn btn-sm default prev" title="Prev" style="background-color:#f0ad4e;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'<li><a href="javascript:void();" class="btn btn-sm default next" title="Next" style="background-color:#f0ad4e;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'</ul>'
							+'<div style="clear: both"></div>'
							+'</div>';
							var htmlData = '';
							$
									.each(
											data,
											function(i, item) {
												// alert(item.orderContact+"-"+item.skuNumber+"-"+item.itemDescription+"-"+item.thumbnail);
												// List <CustRecommendationVO>
												// custRecommendationVOList;
												// alert(item.recSkuList.length)
												// alert(item.recSkuList[0])
												// alert("my html data =
												// "+htmlData)

												//if (undefined != item.recSkuList) {
													var thumbnail = checkUundefined(item.thumbnail);
													if (thumbnail == ''
															|| null == thumbnail
															| undefined == thumbnail) {
														thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
													} else {
														thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
																+ thumbnail;
													}
													var skuDetailURL="#";
													if (item != undefined && item != null && 
															item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
														skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
													}
													htmlData = htmlData
															+ '<li style="border: 1px solid green;width: 145px;height:350px; display: block;">'

															+ '<div style="float:left;width:135px;">'
															+ '<img src='
															+ thumbnail
															+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

															+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
															+ checkUundefined(item.itemDescription)
															+ '</div>'
															+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
															+ checkUundefined(item.skuNumber)
															+ '</a></div>'
															+ '<div class="a-icon-row a-spacing-none">'
															
															+ item.orderContact
															
															+ '</div>'
															//+ '<div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>'
															+ '</div>';
													/*htmlData = htmlData
													+'<li style="border: 1px solid green;height:320px;width: 145px; display: block;">'
													 
													  +'<div style="float:left;width:135px;"><a href="http://www.amazon.com/gp/product/1935182323/ref=as_li_tf_il?ie=UTF8&amp;tag=html061-20&amp;linkCode=as2&amp;camp=217145&amp;creative=399369&amp;creativeASIN=1935182323" title="jQuery in Action, Second Edition" target="_blank" style="color: rgb(0, 0, 0); font-size: 12px;">'
													   +'<img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=059610197X&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="Head First HTML with CSS & XHTML"/>'
													   
													   +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
												         +'  JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)'
												        +'</div>'
													  +'</a>'
													+'<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="/David-Flanagan/e/B000APEZR4/ref=pd_sim_14_bl_1/189-8437559-3078824?_encoding=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">David Flanagan</a></div>'
													  +'<div class="a-icon-row a-spacing-none">'
												        +'    <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">'
												          +'      <i class="a-icon a-icon-star a-star-4-5"></i>'
												           +' </a>'
												            +'<a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>'
												        +'</div>'
													  +'<div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>'
													+'</div>'*/
													// alert("after
													// append"+htmlData)
													
													htmlData = htmlData
															+ '</li>';
													// alert("group =
													// "+htmlData)

													// alert(prefix
													// +htmlData+suffix);
													totalPlayRecomLength=totalPlayRecomLength+1;
												//} 

												count++;
											});
							//alert("totalPlayRecomLength="+totalPlayRecomLength)
							//alert("totalAbandonedLength="+totalAbandonedLength)
							//alert("ALL DATA--> "+prefix +htmlData+suffix);
							$("#PlayRecommTabId").html("");
							if(htmlData!='')
							$("#PlayRecommTabId").html(prefix + htmlData + suffix)
							else{
								$("#PlayRecommTabId")
								.html(
										"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
							}
								
							$("#amazon_scroller_play_recom").amazon_scroller({
								scroller_title_show : 'disable',
								scroller_time_interval : '1800000000000',
								scroller_window_background_color : "#CCC",
								scroller_window_padding : '10',
								scroller_border_size : '1',
								scroller_border_color : '#000',
								scroller_images_width : '135',
								scroller_images_height : '160',
								scroller_title_size : '12',
								scroller_title_color : 'black',
								scroller_show_count : '4',
								directory : 'images'
							});
						}

					}

				})
	} else {
		$("#PlayRecommTabId")
				.html(
						"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
	}
}

function getReorderRecomm() {
	var formData = {};
	var dt = geDataRefreshTime('REORDER');
	//alert(dt);
	$("#updateDateValueReorder span").html(getCurrentDateTime()+" ET");
	
	var dt = geDataRefreshTime('BABRECOM');
	//alert(dt);
	$("#updateDateValueBAB span").html(getCurrentDateTime()+" ET");
	
	var dt = geDataRefreshTime('HRRECOM');
	//alert(dt);
	$("#updateDateValueHR span").html(getCurrentDateTime()+" ET");
	
	//var orderCon = orderContact;
	var custid = $("#reqCustNum").val();
	var accId = $("#accId").val();
	if (undefined != custid && custid != 'select') {
	// alert(ctx + "/getBoughtRecommendation/" + custid + "/" +
	// orderContact)
	$.ajax({
				//url : ctx + "/getReorderRecommendations/" + custid ,
				url : ctx + "/recommendation/customer/"+ custid +"/user/"+ accId +"/level/root",		
				type : "GET",
				//cache : false,
				data : "",//JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var customerNum = 0;
					var orderSeq = '';
					var mkttrHTML = "";
					// $('#customerList').html("");
					var name = '';
					var count = 0;
					totalReorderRecommLength=0;
					totalBaBLength=0;
					// alert(data+"reorder data length="+data.length);
					
					if (data != null && data != undefined) {
						
						var fullData = new Array();
						var dataIndex = 0;
						$.each(data, function(i, itemData) {
							fullData[dataIndex++] = itemData;
						});
						
						 //totalAbandonedLength=5;
						// alert("total len="+totalAbandonedLength)
						var prefix = '<div id="amazon_scroller_reorder" class="amazon_scroller">'
						+'<div class="amazon_scroller_mask" id="ReorderMaskId">'
						+'<ul id="ulidREORDER">';
						var suffix = '</ul></div><ul id="reorder" class="amazon_scroller_nav">'
							+'<li><a href="javascript:void();" class="btn btn-sm default prev" title="Prev" style="background-color:#428bca;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'<li><a href="javascript:void();" class="btn btn-sm default next" title="Next" style="background-color:#428bca;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'</ul>'
							+'<div style="clear: both"></div>'
							+'</div>';
						var htmlData = '';
					if(fullData != null && fullData[0] != undefined && fullData[0] != null && fullData[0] != 'null' && null != fullData[0].products){
						$.each(fullData[0].products, function(i, item) {
											
							//if (undefined != item.recSkuList) {
								var thumbnail = checkUundefined(item.imageurl);
								
								if (thumbnail == '' || null == thumbnail || undefined == thumbnail) {
									thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
								} 
								
								var skuDetailURL="#";
								if ( checkUundefined( item.sku ) != '' ) {
									skuDetailURL = item.url;
									//skuDetailURL ='http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
								}
								
								htmlData = htmlData
										+ '<li style="border: 1px solid green;width: 164px;height:350px; display: block;">'

										+ '<div style="float:left;width:135px;">'
										+ '<img src='
										+ thumbnail
										+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

										+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
										+ checkUundefined(item.desc)
										+ '</div>'
										+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
										+ checkUundefined(item.sku)
										+ '</a></div>'
										+ '<div class="a-row a-size-small">' 
										+ checkUundefined(item.orderDate)
										+ '</div>'
										+ '</div>';
								
								
								htmlData = htmlData
										+ '</li>';
								
								totalReorderRecommLength=totalReorderRecommLength+1;
							 

							count++;
						});
					
						//alert("totalReorderRecommLength="+totalReorderRecommLength)
						//alert("totalAbandonedLength="+totalAbandonedLength)
						//alert("ALL DATA--> "+prefix +htmlData+suffix);
						$("#ReorderRecommTabId").html("");
						if(htmlData!='')
							$("#ReorderRecommTabId").html(prefix + htmlData + suffix)
						else {
								$("#ReorderRecommTabId")
										.html(
												"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
							}
						$("#amazon_scroller_reorder").amazon_scroller({
							scroller_title_show : 'disable',
							scroller_time_interval : '1800000000000',
							scroller_window_background_color : "#CCC",
							scroller_window_padding : '10',
							scroller_border_size : '1',
							scroller_border_color : '#000',
							scroller_images_width : '135',
							scroller_images_height : '160',
							scroller_title_size : '12',
							scroller_title_color : 'black',
							scroller_show_count : '4',
							directory : 'images'
						});
						
					}
						// For BAB =================================================================================== 
						
						prefix = '<div id="amazon_scroller_bought_a_bought" class="amazon_scroller">'
							+ '<div class="amazon_scroller_mask" id="BabMaskId">'
							+ '<ul id="ulidBAB">'
						suffix = '</ul></div>'
							+ '<ul id="boughtAbought" class="amazon_scroller_nav">'
							+ '<li><a href="javascript:void()" class="btn btn-sm default prev" title="Prev" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '<li><a href="javascript:void()" class="btn btn-sm default next" title="Next" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '</ul>'
							+ '<div style="clear: both"></div>'
							+ '</div>';
						htmlData = '';
						
					if(fullData != null && fullData[1] != undefined && fullData[1] != null && fullData[1] != 'null' && null != fullData[1].products){
						$.each(fullData[1].products, function(i, item) {
							 
							//if (undefined != item.recSkuList && (item.recSkuList.length >= 2)) {
								
							var thumbnail = checkUundefined(item.imageurl);
							if (thumbnail == '' || null == thumbnail | undefined == thumbnail) {
								thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
							} 
							
							var skuDetailURL="#";
							if ( checkUundefined(item.sku) != '') {
								//skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
								skuDetailURL = checkUundefined(item.url) ;
							}
											
							htmlData = htmlData
									+ '<li style="border: 1px solid green;width: 164px;height:350px; display: block;">'

									+ '<div style="float:left;width:135px;">'
									+ '<img src='
									+ thumbnail
									+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

									+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
									+ checkUundefined(item.desc)
									+ '</div>'
									+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
									+ checkUundefined(item.sku)
									+ '</a></div>'
									+ '<div class="a-row a-size-small">'
									+ checkUundefined(item.orderDate)
									+ '</div>'
									+ '</div>';
								
							// alert("after
							// append"+htmlData)
							
							htmlData = htmlData
										+ '</li>';
								// alert("group =
								// "+htmlData)

								// alert(prefix
								// +htmlData+suffix);
							totalBaBLength = totalBaBLength + 1;
							 

							count++;
						});
						
						
						$("#BABTabId").html("");
						//alert(htmlData)
						if(htmlData!='')
							$("#BABTabId").html(prefix + htmlData + suffix)
						else
							$("#BABTabId")
							.html(
									"<div id='BabNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
						$("#amazon_scroller_bought_a_bought")
								.amazon_scroller(
										{
											scroller_title_show : 'disable',
											scroller_time_interval : '1800000000000',
											scroller_window_background_color : "#CCC",
											scroller_window_padding : '10',
											scroller_border_size : '1',
											scroller_border_color : '#000',
											scroller_images_width : '135',
											scroller_images_height : '160',
											scroller_title_size : '12',
											scroller_title_color : 'black',
											scroller_show_count : '4',
											directory : 'images'
										});
					}
						
						// For ReOrder =================================================================================== 
						
						prefix = '<div id="amazon_scroller_bought_a_bought" class="amazon_scroller">'
							+ '<div class="amazon_scroller_mask" id="BabMaskId">'
							+ '<ul id="ulidBAB">'
						suffix = '</ul></div>'
							+ '<ul id="boughtAbought" class="amazon_scroller_nav">'
							+ '<li><a href="javascript:void()" class="btn btn-sm default prev" title="Prev" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '<li><a href="javascript:void()" class="btn btn-sm default next" title="Next" style="background-color:#d9534f;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+ '</ul>'
							+ '<div style="clear: both"></div>'
							+ '</div>';
						htmlData = '';
						
					if(fullData != null && fullData[2] != undefined && fullData[2] != null && fullData[2] != 'null' && null != fullData[2].products){
						$.each(fullData[2].products, function(i, item) {
							
							var thumbnail = checkUundefined(item.imageurl);
							if (thumbnail == '' || null == thumbnail | undefined == thumbnail) {
								thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
							} 
							
							var skuDetailURL="#";
							if ( checkUundefined(item.sku) != '') {
								//skuDetailURL = 'http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
								skuDetailURL = checkUundefined(item.url) ;
							}
											
							htmlData = htmlData
									+ '<li style="border: 1px solid green;width: 164px;height:350px; display: block;">'

									+ '<div style="float:left;width:135px;">'
									+ '<img src='
									+ thumbnail
									+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

									+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
									+ checkUundefined(item.desc)
									+ '</div>'
									+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
									+ checkUundefined(item.sku)
									+ '</a></div>'
									+ '<div class="a-row a-size-small">'
									+ checkUundefined(item.orderDate)
									+ '</div>'
									+ '</div>';
								
							// alert("after
							// append"+htmlData)
							
							htmlData = htmlData
										+ '</li>';

						});
						
						
						$("#HRTabId").html("");
						//alert(htmlData)
						if(htmlData!='')
							$("#HRTabId").html(prefix + htmlData + suffix)
						else
							$("#HRTabId")
							.html(
									"<div id='HrNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
						$("#amazon_scroller_bought_a_bought")
								.amazon_scroller(
										{
											scroller_title_show : 'disable',
											scroller_time_interval : '1800000000000',
											scroller_window_background_color : "#CCC",
											scroller_window_padding : '10',
											scroller_border_size : '1',
											scroller_border_color : '#000',
											scroller_images_width : '135',
											scroller_images_height : '160',
											scroller_title_size : '12',
											scroller_title_color : 'black',
											scroller_show_count : '4',
											directory : 'images'
										});
					
					  }
					}// data !=null

				}

			})
} else {
	$("#ReorderRecommTabId")
			.html(
					"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
}
}

function getBoughtNotBought() {
	
	var formData = {};
	//var orderCon = orderContact;
	var custid = $("#reqCustNum").val();
	if (undefined != custid && custid != 'select') {
	// alert(ctx + "/getBoughtRecommendation/" + custid + "/" +
	// orderContact)
	$
			.ajax({
				url : ctx + "/getViewButNotBoughtRecom/" + custid ,
						
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					var customerNum = 0;
					var orderSeq = '';
					var mkttrHTML = "";
					// $('#customerList').html("");
					var name = '';
					var count = 0;
					totalBnBLength=0;
					 //alert(data+" data length="+data.length);
					
					if (data != null && data != undefined) {
						 //totalAbandonedLength=5;
						// alert("total len="+totalAbandonedLength)
						var prefix =' <div id="amazon_scroller4" class="amazon_scroller">'
					+'<div class="amazon_scroller_mask" id="BnBMaskId">'
						+'<ul id="ulidBAB">';
						var suffix = '</ul></div><ul id="BnBRecom" class="amazon_scroller_nav">'
							+'<li><a href="javascript:void();" class="btn btn-sm default prev" title="Prev" style="background-color:#5cb85c;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'<li><a href="javascript:void();" class="btn btn-sm default next" title="Next" style="background-color:#5cb85c;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
							+'</ul>'
							+'<div style="clear: both"></div>'
							+'</div>';
						var htmlData = '';
						$
								.each(
										data,
										function(i, item) {
											 //alert(item.orderContact+"-"+item.skuNumber+"-"+item.itemDescription+"-"+item.thumbnail);
											// List <CustRecommendationVO>
											// custRecommendationVOList;
											// alert(item.recSkuList.length)
											// alert(item.recSkuList[0])
											// alert("my html data =
											// "+htmlData)

											//if (undefined != item.recSkuList) {
												var thumbnail = checkUundefined(item.thumbnail);
												if (thumbnail == ''
														|| null == thumbnail
														| undefined == thumbnail) {
													thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
												} else {
													thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
															+ thumbnail;
												}
												var skuDetailURL="#";
												if (item != undefined && item != null && 
														item.skuNumber != undefined && item.skuNumber != null && item.skuNumber != '') {
													skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
												}
												htmlData = htmlData
														+ '<li style="border: 1px solid green;width: 145px;height:350px; display: block;">'

														+ '<div style="float:left;width:135px;">'
														+ '<img src='
														+ thumbnail
														+ ' width="124" height="160" alt="jQuery in Action, Second Edition" style="width: 135px; height: 160px;">'

														+ ' <div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
														+ checkUundefined(item.itemDescription)
														+ '</div>'
														+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')">'
														+ checkUundefined(item.skuNumber)
														+ '</a></div>'
														+ '<div class="a-row a-size-small">'
														+ item.orderContact
														+ '</div>'
														+ '<div class="a-row a-size-small">' 
														+ item.orderDate
														+ '</div>'
														//+ '<div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">'+item.orderDate+'</span></i></span></div>'
														+ '</div>';
												/*htmlData = htmlData
												+'<li style="border: 1px solid green;height:320px;width: 145px; display: block;">'
												 
												  +'<div style="float:left;width:135px;"><a href="http://www.amazon.com/gp/product/1935182323/ref=as_li_tf_il?ie=UTF8&amp;tag=html061-20&amp;linkCode=as2&amp;camp=217145&amp;creative=399369&amp;creativeASIN=1935182323" title="jQuery in Action, Second Edition" target="_blank" style="color: rgb(0, 0, 0); font-size: 12px;">'
												   +'<img src="http://ws.assoc-amazon.com/widgets/q?_encoding=UTF8&Format=_SL160_&ASIN=059610197X&MarketPlace=US&ID=AsinImage&WS=1&tag=html061-20&ServiceVersion=20070822" width="60" height="60" alt="Head First HTML with CSS & XHTML"/>'
												   
												   +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
											         +'  JavaScript: The Definitive Guide: Activate Your Web Pages (Definitive Guides)'
											        +'</div>'
												  +'</a>'
												+'<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="/David-Flanagan/e/B000APEZR4/ref=pd_sim_14_bl_1/189-8437559-3078824?_encoding=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">David Flanagan</a></div>'
												  +'<div class="a-icon-row a-spacing-none">'
											        +'    <a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">'
											          +'      <i class="a-icon a-icon-star a-star-4-5"></i>'
											           +' </a>'
											            +'<a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>'
											        +'</div>'
												  +'<div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">Prime</span></i></span></div>'
												+'</div>'*/
												// alert("after
												// append"+htmlData)
												
												htmlData = htmlData
														+ '</li>';
												// alert("group =
												// "+htmlData)

												// alert(prefix
												// +htmlData+suffix);
												totalBnBLength=totalBnBLength+1;
											//} 

											count++;
										});
						//alert("totalBnBLength="+totalBnBLength)
						//alert("totalAbandonedLength="+totalAbandonedLength)
						//alert("ALL DATA--> "+prefix +htmlData+suffix);
						$("#BnBTabId").html("");
						if(htmlData!='')
						$("#BnBTabId").html(prefix + htmlData + suffix)
						 else {
							$("#BnBTabId")
									.html(
											"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
						}
						$("#amazon_scroller4").amazon_scroller({
							scroller_title_show : 'disable',
							scroller_time_interval : '1800000000000',
							scroller_window_background_color : "#CCC",
							scroller_window_padding : '10',
							scroller_border_size : '1',
							scroller_border_color : '#000',
							scroller_images_width : '135',
							scroller_images_height : '160',
							scroller_title_size : '12',
							scroller_title_color : 'black',
							scroller_show_count : '4',
							directory : 'images'
						});
					}

				}

			})
} else {
	$("#BnBTabId")
			.html(
					"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
}
}
$("#orderConId").change(function() {
	var orderContact = $(this).val()
	getBoughtAlsoBught(orderContact);
});

/** Dashboard data start **/


function openShipToDetails()
{
	 // log user activity; viwe ship to data
	logUserActivityDotCom(7006, 'View Ship To Data');
	
	  var formData={};
	  var dt = geDataRefreshTime('MV_SHIP_TO_INFO');
		//alert(dt);
	  $("#userDiv").prepend($("#shipToId"));
		$("#updateDateValueShip span").html(getCurrentDateTime()+" ET");
	  $("#shipToId").css("display","block");
	  $("#shipToContent").css("display","block");
	  $("#showHideIdShipTo").prop('class','fa fa-times');
	  commonScroll('shipToId');
	  var custid= $("#reqCustNum").val();
	  $("#dataTables-exampleShipTo_processing").html('<div id="example_processing_shipto" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	  $("#dataTables-exampleShipTo_processing").css("display","block");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/sales/customer/"+custid+"/shipment/info?sbuName=STAPLES.COM",
			 	type : "GET",
				//cache : false,
				data : "",//JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					
					 $('#my-modal').modal({
				      
						 "backdrop"  : "static",
						 "keyboard"  : true,
						 "show"      : true           
					 }); 
					
				    var cnt=0;
					var mkttrHTML = "";
					 $('#shipToTableId').html("");
					 var name='';
								if(data != null){
									//alert("inside1");
									$.each(data.shipments, function(i, item) {
										
										var shiptoAddress =shiptoAddress=checkUundefined(item.shiptoAddress1);
										
										var YOY = '';
										if ( checkUundefined(item.yoy).length > 0 ){
											YOY = checkUundefined(item.yoy) + "%"
										}
											mkttrHTML += '<tr class="odd gradeX">'
												+'<td class="datatablesTd">'
												+'<a  style="padding-right:8px;text-decoration:underline" href="javascript:openShipToDetailsInfo(\''+shiptoAddress+'\','+custid+')" '
												+'>'
												 + shiptoAddress +'</a><i id="shipToIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="divId'+cnt+'" class="toolTip">';
											if(checkUundefined(item.shiptoAddress1)!='STORE ORDERS'){
											mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.shiptoAddress1)+'</div>'
												 +'<div style="font-size:12px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.shipToCity)+'</div>'
												 +'<div style="font-size:12px;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.shipToState)+'-'+checkUundefined(item.shipToZip)+'</div>';
											}else{
												mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">STORE ORDERS</div>';
											}
											mkttrHTML +='</div></td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.cfy_order_count) +'</td>'
											 +'<td class="datatablesTd formatClsShip">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.currentFYS))) +'</td>'	
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.lfy_order_count) +'</td>'
											 +'<td class="datatablesTd formatClsShip">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.previousFYS))) +'</td>'
											 +'<td class="datatablesTd">'
											 + YOY +'</td>'
											 
											 +'</tr>';
										
										 cnt++;
									});
									ShipToDeatilCount = cnt-1;
								}
								$('#shipToTableId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-exampleShipTo"  ><thead><tr>'
										       +'<th>Ship To Id</th>'
										       +'<th>No of Orders <br/>(CFY)</th>'
												+'<th>$ Spent <br/>(CFY)</th>'
												+'<th>No of Orders <br/>(LFY)</th>'
												+'<th>$ Spent <br/>(LFY) </th>'
												+'<th>YOY % <br/>Change</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table>');
								$("#dataTables-exampleShipTo_processing").css("display","none");
								$('#dataTables-exampleShipTo').dataTable({
									// responsive: true
									"lengthMenu": [[5, 15, 25, -1], [5, 15,25, "All"]],
									//"scrollX": true,
								    //searching:false	,
								    //  "paging":   false
									"bSort": true,
									"order": [],
									"bProcessing": true,
									"oLanguage": { "sSearch": "Filter: "},
									"aoColumns": [{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true}
									
									
									],
									"fnDrawCallback": function( oSettings ) {
										
										if (ShipToDeatilCount != -1) {
											for (var count = 0; count <= ShipToDeatilCount; count++) {
												$('#shipToIdDtl'+count).popover({
													html : true,
													placement : 'right',
													content : $("#divId"+count).html()
												}).on("hover", function(){
											        $('.popover').addClass('popoverBasic');
											    }).on('show.bs.popover', function() {
					
												});
											}
										}
									   }
								}).fnDraw();	
								$('#dataTables-exampleShipTo_length label').css("color","#004c74");
								$('#dataTables-exampleShipTo_length label').css("color","#004c74");
								$('#dataTables-exampleShipTo_length label').css("font-family","helveticaneuebold");
								$('#dataTables-exampleShipTo_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-exampleShipTo_length label').css("font-weight","600");
								$('#dataTables-exampleShipTo_filter label').css("color","#004c74");
								$('#dataTables-exampleShipTo_filter label').css("letter-spacing","1px");
								$('#dataTables-exampleShipTo_filter').css("text-align","right");
								//$('#dataTables-exampleShipTo_filter label').css("font-weight","600");
								$('#dataTables-exampleShipTo_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-exampleShipTo_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								$('#dataTables-exampleShipTo_filter label').css("float","right");
								$('#dataTables-exampleShipTo_info').css("color","#004c74");
								$('#dataTables-exampleShipTo_info').css("font-weight","600");
								$('#dataTables-exampleShipTo_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								  $('#dataTables-exampleShipTo').removeClass('display').addClass('table table-striped table-bordered');
								    $('#dataTables-exampleShipTo_filter input[type="search"]').attr('placeholder','Enter Ship To Id').css({'width':'250px','display':'inline-block'});
								    
					}

			})	
  

}


function displayTooltip(count){
	document.getElementById('divId'+count).style.display='block';
}
function hideTooltip(count){
	document.getElementById('divId'+count).style.display='none';
}

function displayOrderContactTooltip(count) {
	document.getElementById('orderContact'+count).style.display='block';
}

function hideOrderContactTooltip(count) {
	document.getElementById('orderContact'+count).style.display='none';
}


function openOrderDetails(isFromReturned){

	// log user activity; view order list
	logUserActivityDotCom(7004, 'View Order List');
	
	var Amount = new Array();
	var newItem = new Array();

	if(checkUundefined(latestFiscalDateOrderSAMNew).length == 0){
		var currentTime = new Date();
		
		if( latestDate !=undefined && latestDate != null ){
			if(checkUundefined(latestDate.fiscalYears)){
				latestFiscalDateOrderSAMNew = "" + currentTime.getMonth();
				if(latestFiscalDateOrderSAMNew.length == 1){
					latestFiscalDateOrderSAMNew = "0" + latestFiscalDateOrderSAMNew;
				}
				latestDateforOrder += latestDate.fiscalYears[0];
			}
		}
		else {
			latestFiscalDateOrderSAMNew = currentTime.getMonth() +  currentTime.getYear();
		}	
		
	}
	
	var monthNew=parseInt(latestFiscalDateOrderSAMNew.substring(0,2));
	var yearNew=latestFiscalDateOrderSAMNew.substring(2,6);
	
	//alert(monthNew +"--"+yearNew);
	$("#yearSel").val("January 2015");
	var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	//alert(monthNameArr[monthNew])
	// $('#datepickerTEXT').val("2015");
	$("#userDiv").prepend($("#cusId"));

	$("#custContent").css("display","block");
	$("#cusId").css("display","block");
	  $("#superContent").css("display","block");
	  $("#showHideId1").prop('class','fa fa-times');
	  commonScroll('cusId');
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthyear="02015";
	  var currentTime = new Date()
	  var month = currentTime.getMonth() ;
	  var year = currentTime.getYear() ;
	  //var dt = geDataRefreshTime('ORDERHEADER');
	  var dt=getCurrentTime();
		//alert(dt);
		$("#updateDateValueOrder span").html(getCurrentDateTime()+" ET");
	  //alert(monthNew+"-"+yearNew+"-"+month+"-"+year);
	  var catid="ALL";
	  $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	  $("#dataTables-order_processing").css("display","block");
	  //monthyear=month+""+year;
	  //alert("/getOrderDetailsHighLevelData/"+custid+"--"+monthyear+"--"+catid);
	  var orderDate = monthNameArr[monthNew-1].toUpperCase() + "-" + yearNew;
	  
		 $.ajax({
			 	dataType: "json",
			 	url : ctx+"/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM&startDate="+orderDate+"&endDate="+orderDate,
			 	//url : ctx+"/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM",
				type : "GET",
				//cache : false,
				data : "", //sanjay JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt=0;
					var mkttrHTML = "";
					 $('#order-modal').modal({
					      
						 "backdrop"  : "static",
						"keyboard"  : true,
						"show"      : true           
				    });
					 //alert(monthNew+"--"+yearNew);
					 

								// sanjay started
					 			var itemCnt=-1;
					 			var oldItem;
					 			var orderNumber; 
								var itemReturned;
								var orderDate ;
								var contactName;								
								var noOfItems = 0;
								var orderTotal = 0;
								
								if(data !=null){
									$.each(data.customerOrders, function(i, item) {
										
										if(item.recordType != "0003")	{
											var items1=item.orderNumber.split('.');
											var items2='';
											for(var j=0;j<items1.length;j++)
												{
												if (j != 2 && j != 0
														&& j != 1) {
													items2 += '.'
															+ items1[j];
												} else if (j != 2 && j != 0
														&& j == 1) {
													items2 += '.<span style="color:red;text-decoration:underline !important;font-weight:bold;">'
															+ items1[j]
															+ '</span>';
												} else if (j == 0) {
													items2 += items1[j];
												}
											}
											//items2;
											if(item.contactName=="null null")
												{
												item.contactName="";
												}
											orderDate = checkUundefined(item.orderTranDate);	//??
											if(orderDate != null || orderDate != ''){
												orderDate = orderDate.substring(0,10);
												orderDateArr = orderDate.split("-");
												orderDate = orderDateArr[1] + "/" + orderDateArr[2] + "/" + orderDateArr[0];
											}
											var ordertypeDef='';
											//console.log("item : ",item.orderReturned);
											
													
											if(item.orderReturned == "true")
												{
													ordertypeDef="(R)";
												}
											else
												{
													ordertypeDef="";
												}
											item.totalCoponAmount=parseFloat(item.totalCoponAmount).toFixed(2);
											item.totalDiscountAmount=parseFloat(item.totalDiscountAmount).toFixed(2);
											
											var rewardNo = checkUundefined(item.orderIssuer);
											if (rewardNo.length > 0){
												if (rewardNo.charAt(rewardNo.length - 1) == 'P') {
													rewardNo = rewardNo.replace('P', 'M');
													//rewardNo = "<span style='color:#0000FF;'>"+rewardNo+"</span>";
												}
												if (rewardNo.charAt(rewardNo.length - 1) == 'C') {
													rewardNo = rewardNo.replace('C', 'S');
													//rewardNo = "<span style='color:#FF00FF;'>"+rewardNo+"</span>";
												}
											}
											
											var productSKUs = new Array();
											var productDescs = new Array();
											productSKUObj = item.orderLevelItems;
											if ( (undefined != productSKUObj) || (null != productSKUObj) ){
												$.each(productSKUObj, function(i, orderItem) {
													productSKUs.push(orderItem.sku);
													productDescs.push(orderItem.productName);
												});
											}
											
											mkttrHTML += '<tr class="odd gradeX">'
												//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
												
												 +'<td class="datatablesTd">'+rewardNo+'</td>'
												 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');" style="text-decoration:underline;padding-right:6px;">'
												 + checkUundefined(items2)+'</a><span style="color:red;margin-right: -17px;margin-left: -4px;">'+ordertypeDef+'</span></td>'
												 //+'<td class="datatablesTd">'
												 //+ checkUundefined(item.orderReturned) +'</td>'
												 +'<td class="datatablesTd">'
												 + orderDate +'</td>'
												 +'<td>'+checkUundefined(Number(item.orderItemsCount))+'</td>'	// ??
												 +'<td class="datatablesTd formatClsOrd">'
												 + chkNegAmountOrder(chkDollar(checkUundefined(parseFloat(item.tranTotalAmount).toFixed(2)))) +'</td>'
												 +'<td class="datatablesTd">'+chkNegAmountOrder(chkDollar(checkUundefined(item.totalDiscountAmount)))+'</td>'
												 +'<td class="datatablesTd">'+chkNegAmountOrder(chkDollar(checkUundefined(item.totalCoponAmount)))+'</td>'
												 +'<td class="datatablesTd">'
												 + checkUundefined(item.contactName) +'</td>'
												 +'<td class="datatablesTd">'
												 + productSKUs.toString() +'</td>'												
												 +'<td class="datatablesTd">'
												 + productDescs.toString() +'</td>'												
												 +'</tr>';
											
											cnt++;
										}
									});
									OrderDeatilCount = cnt-1;
								}
								
								//alert(isFromReturned+"--"+retFound+"--");
								if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('N' == isFromReturned)){
									populateMonthYearData(data,monthNew,yearNew);
									populateCatData();
									$("#yearSel option[value="+monthNew+""+yearNew+"]").attr("selected","selected");
									$('#datepickerTEXT').val(monthNameArr[monthNew-1]+" / "+yearNew);
									//$('#datepickerTEXT').val("ALL / "+yearNew);
							        $('#datepickerTEXT').css("font-weight","bold")
							        $('#datepickerTEXT').css("font-family","Hevletica")
							        $('#datepickerTEXT').css("font-size","14px");
									$('#orderDetailsTabId').html("");
									$('#orderDetailsTabId').html(
											'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
													
		                                           // +'<th>Details</th>'
											
													+'<th>Rewards #</th>'
	                                                +'<th>Order No.</th>'
		                                            //+'<th style="font-weight:bold;color:red">RETURNED</th>'
		                                            +'<th>Order Date</th>'
		                                            +'<th>No. Of Items</th>'
		                                            +'<th>Order Total</th>'
		                                            +'<th>Discounts</th>'
		                                            +'<th>Coupons</th>'
		                                            +'<th>Order Contact</th>'
		                                            +'<th>productSKUs</th>'		// hidden field, only for search
		                                            +'<th>productDescs</th>'	// hidden field, only for search
		                                            + '</tr>'
													+ '</thead><tbody>' + mkttrHTML
													+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
									$("#dataTables-order_processing").css("display","none");
									$('#dataTables-order').dataTable({
										"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
										"order": [],
										"bProcessing": true,
										"oLanguage": { "sSearch": "Filter: ","sWidth": '11%'},
										"aoColumns": [{"bSortable": true},
											{"bSortable": true,"visible":true,"sWidth": '11%'},
											{"bSortable": true,"visible":true,"sWidth": '7%'},
											//{"bSortable":true,"sWidth": '11%'},
											{"bSortable": true,"sWidth": '11%'},
											{"bSortable": true,"sWidth": '11%'},
											{"bSortable": true,"sWidth": '11%'},
											{"bSortable": true,"sWidth": '11%'},
											{"bSortable": true,"sWidth": '15%'},
											{"bSortable": true,"visible":false,"sWidth": '1%'},
											{"bSortable": true,"visible":false,"sWidth": '1%'}],
											"fnDrawCallback": function( oSettings ) {
											/* sanjay commented
											if (OrderDeatilCount != -1) {
												for (var count = 0; count <= OrderDeatilCount; count++) {
													$('#OrderIdDtl'+count).popover({
														html : true,
														placement : 'right',
														content : $("#orderdivId"+count).html()
													}).on("hover", function(){
												        $('.popover').addClass('popoverBasic');
												    }).on('show.bs.popover', function() {
						
													});
												}
											}	
											*/
										   }
									}).fnDraw();
								}else if(undefined !=isFromReturned && isFromReturned =='Y' && retFound=='N'){
									//alert("from ret "+monthNew+"--"+yearNew);
									var monthYear='';
									var catId="ALL";
									if(month !=1){
										monthYear=(monthNew-1)+""+yearNew;
									}else if(month==1){
										monthYear= 12+""+(yearNew-1);
									}
									//alert("itr from ret "+monthYear+"---"+catId);
									 populateMonthYearData(data,(monthNew-1),yearNew);
									 populateCatData();
									 if(monthNew !=12){
										 $("#yearSel option[value="+(monthNew-1)+""+yearNew+"]").attr("selected","selected");
										 $('#datepickerTEXT').val(monthNameArr[monthNew-2]+" / "+yearNew);
										}else {
											$("#yearSel option[value=12"+(yearNew-1)+"]").attr("selected","selected");
											 $('#datepickerTEXT').val(monthNameArr[11]+" / "+(yearNew-1));
										}
									 
							          $('#datepickerTEXT').css("font-weight","bold")
							          $('#datepickerTEXT').css("font-family","Hevletica")
							          $('#datepickerTEXT').css("font-size","14px");
									onChangeMonthOrCat(monthYear,catId,'Y');
								}
								
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("font-size","15px");
								$('#dataTables-order_length label').css("font-family","helveticaneuebold");
								$('#dataTables-order_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-order_length label').css("font-weight","600");
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								//$('#dataTables-order_filter label').css("font-weight","600");
								$('#dataTables-order_filter label').css("float","right");
								$('#dataTables-order_filter input[type="search"]').css("width","270px");
								$('#dataTables-order_info').css("color","#004c74");
								$('#dataTables-order_info').css("font-weight","600");
								$('#dataTables-order_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$('#dataTables-order').removeClass('display').addClass('table table-striped table-bordered');
							    $('#dataTables-order_filter input[type="search"]').attr('placeholder','Enter Order No. OR Item No. OR Item Desc.').css({'width':'270px','display':'inline-block'});
					}

			})
			$("#yearSel").val("January 2015");
}


function populateMonthYearData(data,latestMonth,latestYear){
	var optionVal=new Array();
	var optionTxt=new Array();
	var valcount=0;
	var txtcount=0;
	var is_dec=false;
	var is_from_beforeshow=false;
	var selectedMonth;
	if(data.monYearList !=null && data.monYearList !=undefined){
		$.each(data.monYearList, function(i, item) {
			optionTxt[txtcount]=item;
			txtcount++;
		  });
		for(var value in data.monYearList){
			optionVal[valcount]=value;
			valcount++;
		}
    }
	
	//optionVal = ["2016","2017"];
	//optionTxt = ["2016","2017"];
	 var selectedDeviceModel = $('#yearSel');
			selectedDeviceModel.empty();
				/*selectedDeviceModel.append($('<option/>', {
					value : '',
					text : 'Select'*/
			var count=0;
			for(count=0;count<optionVal.length;count++){
				selectedDeviceModel.append($('<option/>', {
					value : optionVal[count],
					text :  optionTxt[count]
				}));
			}
		// get MAx and Min date
			var allYearArr=new Array();
			var position=0;
			for(var cnt=0;cnt<optionTxt.length;cnt++){
				var txtVal=optionTxt[cnt].split(" ")[0]
				if(undefined !=txtVal && txtVal=="All"){ 
				 allYearArr[position]=optionTxt[cnt].split(" ")[1];
				 position++
				}
			}
			if(null !=allYearArr && undefined !=allYearArr && '' !=allYearArr)
				allYearArr.sort();
			var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
			var maximumDate='',minimumDate='';
			if(latestMonth==2)
			    maximumDate=new Date(latestYear,latestMonth-1,28)
			else if(latestMonth==1 || latestMonth==3 || latestMonth==5 || latestMonth==7 || latestMonth==8 || latestMonth==10 || latestMonth==12)
				maximumDate=new Date(latestYear,latestMonth-1,31)
			else
				maximumDate=new Date(latestYear,latestMonth-1,30)
			if(undefined !=allYearArr[0] && allYearArr[0]!='')
			{
				minimumDate=new Date(allYearArr[0],0,31); 
			}
			else //Sanjay added for Order datetime picker
			{
				minimumDate=new Date(latestYear-1,0,31); 
			}
			
			$('#datepickerTEXT').datepicker( {
		        //yearRange:yrRange,
		       // start:'2010',
		       autoOpen: false,
		       minDate :minimumDate ,
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: 'mm/yy',
		        maxDate: maximumDate,
		beforeShow : function(input, inst) { 
			inst.dpDiv.addClass('month_year_datepicker')
            if ((datestr = $(this).val()).length > 0) {
                year = datestr.substring(datestr.length-4, datestr.length);
                month = datestr.substring(0, 2);
                var map = {"Jan":"1","Feb":"2","Mar":"3","Apr":"4","May":"5","Jun":"6","Jul":"7","Aug":"8","Sep":"9","Oct":"10","Nov":"11","Dec":"12","ALL":"12.6"};
          	  var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	          	if(!isNumeric(month)) { 
	          		month = datestr.substring(0, 3);
	          	    month=map[month];
	             } 
                if(month!=12){
               	is_dec=false;
               	$(this).datepicker('option', 'defaultDate', new Date(year, month-1, 1));
                $(this).datepicker('setDate', new Date(year, month-1, 1));
                }
                else if(month==12){
               	is_dec=true;
                $(this).datepicker('option', 'defaultDate', new Date(year, month-1, 1));
                $(this).datepicker('setDate', new Date(year, month-1, 1));
                }
                $(".ui-datepicker-calendar").hide();
            }
		        	$('#datepickerTEXT').css("font-weight","bold");
			          $('#datepickerTEXT').css("font-family","Hevletica");
			          $('#datepickerTEXT').css("font-size","14px");
				},onClose: function(dateText, inst) {
                    function isDonePressed(){ 
                        return ($('#ui-datepicker-div').html().indexOf('ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all ui-state-hover') > -1);
                    }

                    if (isDonePressed()){
                    	var monthTxt=$("#ui-datepicker-div .ui-datepicker-month :selected").text();
      		          var monthVal=$("#ui-datepicker-div .ui-datepicker-month :selected").val();
      		          var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
      		          $('#datepickerTEXT').val(monthTxt+" / "+year);
      		          $('#datepickerTEXT').css("font-weight","bold")
      		          $('#datepickerTEXT').css("font-family","Hevletica")
      		          $('#datepickerTEXT').css("font-size","14px");
                         $('.date-picker').focusout()//Added to remove focus from datepicker input box on selecting date
                    }
                },
                afterShow: function (input, inst, td) {
                	//alert("sel = "+selectedMonth);
                	/*if(undefined!=selectedMonth){
                	 $(selectedMonth).attr("selected","selected");
                	 $("select.ui-datepicker-month option[value='"+selectedMonth+"']").attr("selected","selected");
                	}*/
                    if((undefined!=is_dec && '' !=is_dec && is_dec==true) && (month!=12.6)){
        				$("select.ui-datepicker-month option[value=11]").next().removeAttr("selected");
        				$("select.ui-datepicker-month option[value=11]").next().attr("selected",false);
        				$("select.ui-datepicker-month option[value=11]").attr("selected",true);
        			}else if(month==12.6 && allselected=="Y"){
        				$("select.ui-datepicker-month option[value=11]").removeAttr("selected");
        				$("select.ui-datepicker-month option[value=11]").attr("selected",false);
        				$("select.ui-datepicker-month option[value=11]").next().attr("selected",true);
        			}
                   
                },
				onChangeMonthYear : function(year, month,inst ) {
					var val=$("select.ui-datepicker-month option[value=11]").attr("selected");
					var val1=$("select.ui-datepicker-month option[value=11]").next().attr("selected");
					var val2=$("select.ui-datepicker-month option:last").attr("selected");
					is_dec=is_dec;
					if((undefined != val) && ('selected'==val)){ 
						allselected="Y";
					//e.stopPropagation();
					}
					if (((undefined == val) && (undefined == val1)
							&& (month == 12))) {
						allselected = "Y";
					} else if( (undefined !=val2 && val2 =='selected')){
						allselected = "Y";
						is_dec = false;
					}else if ((undefined == val)
							&& (undefined == val1) && month != 12) {
						is_dec = false;
						allselected = "N";
					} else if ((undefined != val)
							&& ('selected' == val) && month == 12) {
						allselected = "N";
						is_dec = true;
					} else if ((undefined != val1)
							&& ('selected' == val1) && month == 12) {
						allselected = "Y";
						is_dec = false;
					} else { 
						allselected = "N";
					}
					/*alert(val+"--"+val1+"--"+is_dec+"--"+allselected+"--"+month);
                    selectedMonth=$("select.ui-datepicker-month option:selected").val();
                    alert("mon"+selectedMonth+"--------"+$("select.ui-datepicker-month option:selected").text());*/
					//$("select.ui-datepicker-month option:selected").attr("")
					//e.stopPropagation();
				}
		      }).focus(function () { 
		    	  $('.ui-datepicker-calendar').detach();
		    	  $('.ui-datepicker-close').unbind("click").click(function () {
		    		  var monthTxt=$("#ui-datepicker-div .ui-datepicker-month :selected").text();
			          var monthVal=$("#ui-datepicker-div .ui-datepicker-month :selected").val();
			          var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
			          $('#datepickerTEXT').val(monthTxt+" / "+year);
			          $('#datepickerTEXT').css("font-weight","bold")
			          $('#datepickerTEXT').css("font-family","Hevletica")
			          $('#datepickerTEXT').css("font-size","14px");
			          var catId=$("#sortSel").val();
			          if(monthVal==11.6)
				      monthVal=-1	  
			          var monthYear=(parseInt(monthVal)+1)+""+year;
			          //alert("monthYear = "+monthYear+"catid="+catId)
			 		  onChangeMonthOrCat(monthYear,catId,'N');
			 		  $( "#datepickerTEXT" ).datepicker( "hide" );
			 		 $("#lastXDaysSel").val("0");
			          //$('#datepickerTEXT').datepicker('setDate', new Date(year, month, 1));
		    	  });
		      });
			
}


function populateCatData(){
	 var selectedDeviceModel = $('#sortSel');
	 var catListArr = new Array();
	 
	 selectedDeviceModel.empty();
	 selectedDeviceModel.append($('<option/>', {
		 value : 'ALL',
		 text : 'All Purchases'}));
			
			
	$.ajax({
	 	dataType: "json",
	 	url : ctx+"/sales/categories/list",
		type : "GET",
		cache : false,
		data : "", //sanjay JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			
		 	if(data !=null){
				$.each(data.categories, function(i, item) {
					
					var catName = checkUundefined(item.name);
					if ( (catName.length > 0) && (catListArr.indexOf(catName) == -1) ){
						catListArr.push(catName);	
						var catVal = catName.replace('&','%26');
						selectedDeviceModel.append($('<option/>', {
							value : catVal,
							text :  catName
						}));
					}						
					
				});
				
			}				
						
		}//success

	})//ajax		
	
}

function getLatestFiscalDateOrder(){
	 
	  
	  var custid= $("#reqCustNum").val();
	 
	var formData={};
		 $.ajax({
			 dataType: "json",
				url : ctx+"/getLatestFiscalDateOrder/"+custid,
				//url : "/getLatestFiscalDate/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					//alert(data);
					latestDateforOrder=data;
					
				}
				}

			})	
			//populateNotificationData();
			return latestDateforOrder;

}


  function onChangeMonthOrCat(monthYr,catid,isFromReturned){
		// log user activity; view order list
		logUserActivityDotCom(7004, 'View Order List');
		var Amount = new Array();
		var startDate = "";
		var endDate = "";
		retFound='N';
		  var formData={};
		  var custid = $("#reqCustNum").val();
		  var monthVal = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	      
	      var map = {"Jan":"01","Feb":"02","Mar":"03","Apr":"04","May":"05","Jun":"06","Jul":"07","Aug":"08","Sep":"09","Oct":"10","Nov":"11","Dec":"12","ALL":"0"};
		  var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	      //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
	  
		  if(undefined==monthYr){
	    	 var date = new Date();
	    	 var dateString = date.toISOString().split('T')[0]; // "2016-06-08"	 		 
	 		 var month = date.getMonth() ;
	 		 var year = dateString.substring(0,4) ;
	    	 endDate = monthNameArr[month].toUpperCase()+"-"+year;
	    	 
	 		 var lastNumberOfDays = $("#lastXDaysSel :selected").val();
	 		 if("0" != lastNumberOfDays){ // -30 / -60 / -90
	 			 lastNumberOfDays = parseInt(lastNumberOfDays.substring(1,3));
	 		 }	 		 
	 		 date.setDate(date.getDate() - lastNumberOfDays);
	 		 dateString = date.toISOString().split('T')[0]; // "2016-06-08"
	 		 month = date.getMonth() ;	// 1 .. 12
	 		 year = dateString.substring(0,4) ; // yyyy
	 		 
	 		 startDate = monthNameArr[month].toUpperCase()+"-"+year;
	    	 
	      }
	      else
	      {
	    	  var monthNew, yearNew;
		      if(monthYr.length == 6)
		    	  {
		    	  monthNew=parseInt(monthYr.substring(0,2));
		  	  		yearNew=monthYr.substring(2,6);
		    	  }
		      else if (monthYr.length == 5)
		    	  {
		    	  monthNew=parseInt(monthYr.substring(0,1));
		  	  		yearNew=monthYr.substring(1,5);
		    	  }
		  		monthVal=monthNew-1;
		      if(undefined != monthVal && "ALL" != monthVal && monthNew!=0){
		    	  startDate =monthNameArr[monthVal].toUpperCase()+"-"+yearNew; 
		    	  endDate = monthNameArr[monthVal].toUpperCase()+"-"+yearNew;	
		    	 
		      }
		      // Sanjay for 'All' as date
		      if(-1 == monthVal){
		    	  startDate = "JAN-"+yearNew; 
		    	  endDate = "DEC-"+yearNew;	
		    	 
		      }
	 
  		 }
		  
		  catURL = ctx+"/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM&startDate="+startDate+"&endDate="+endDate;
		  
		  if(catid != "ALL"){
			  catURL += "&catogeryId="+catid;
		  }
	      
	      $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
		  $("#dataTables-order_processing").css("display","block");
		  //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
		 
		  $.ajax({
			 	dataType: "json",
				//url : ctx+"/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM&startDate="+startDate+"&endDate="+endDate+"&catogeryId="+catid,
			 	url : catURL,
				type : "GET",
				//cache : false,
				data : "", //sanjay JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt=0;
					var mkttrHTML = "";
					 $('#order-modal').modal({
					      
						 "backdrop"  : "static",
						"keyboard"  : true,
						"show"      : true           
				    });
					 //alert(monthNew+"--"+yearNew);
					 
	
								// sanjay started
					 			var itemCnt=-1;
					 			var oldItem;
					 			var orderNumber; 
								var itemReturned;
								var orderDate ;
								var contactName;								
								var noOfItems = 0;
								var orderTotal = 0;
								
								if(data !=null){
									$.each(data.customerOrders, function(i, item) {
										
										if(item.recordType != "0003")	{
										
											var items1=item.orderNumber.split('.');
											var items2='';
											for(var j=0;j<items1.length;j++)
												{
												if (j != 2 && j != 0
														&& j != 1) {
													items2 += '.'
															+ items1[j];
												} else if (j != 2 && j != 0
														&& j == 1) {
													items2 += '.<span style="color:red;text-decoration:underline !important;font-weight:bold;">'
															+ items1[j]
															+ '</span>';
												} else if (j == 0) {
													items2 += items1[j];
												}
											}
											//items2;
											if(item.contactName=="null null")
												{
												item.contactName="";
												}
											orderDate = checkUundefined(item.orderTranDate);	//??
											if(orderDate != null || orderDate != ''){
												orderDate = orderDate.substring(0,10);
												orderDateArr = orderDate.split("-");
												orderDate = orderDateArr[1] + "/" + orderDateArr[2] + "/" + orderDateArr[0];
											}
											var ordertypeDef='';
											console.log("item order",item.ordrReturned);
											if(item.orderReturned == "true")
												{
													ordertypeDef="(R)";
												}
											else
												{
													ordertypeDef="";
												}
											item.totalCoponAmount=parseFloat(item.totalCoponAmount).toFixed(2);
											item.totalDiscountAmount=parseFloat(item.totalDiscountAmount).toFixed(2);
											
											var rewardNo = checkUundefined(item.orderIssuer);
											if (rewardNo.length > 0){
												if (rewardNo.charAt(rewardNo.length - 1) == 'P') {
													rewardNo = rewardNo.replace('P', 'M');
													//rewardNo = "<span style='color:#0000FF;'>"+rewardNo+"</span>";
												}
												if (rewardNo.charAt(rewardNo.length - 1) == 'C') {
													rewardNo = rewardNo.replace('C', 'S');
													//rewardNo = "<span style='color:#FF00FF;'>"+rewardNo+"</span>";
												}
											}
											
											var productSKUs = new Array();
											var productDescs = new Array();
											productSKUObj = item.orderLevelItems;
											if ( (undefined != productSKUObj) || (null != productSKUObj) ){
												$.each(productSKUObj, function(i, orderItem) {
													productSKUs.push(orderItem.sku);
													productDescs.push(orderItem.productName);
												});
											}
											
											
											mkttrHTML += '<tr class="odd gradeX">'
												//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
												
												 +'<td class="datatablesTd">'+rewardNo+'</td>'
												 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');" style="text-decoration:underline;padding-right:6px;">'
												 + checkUundefined(items2)+'</a><span style="color:red;margin-right: -17px;margin-left: -4px;">'+ordertypeDef+'</span></td>'
												 //+'<td class="datatablesTd">'
												 //+ checkUundefined(item.orderReturned) +'</td>'
												 +'<td class="datatablesTd">'
												 + orderDate +'</td>'
												 +'<td>'+checkUundefined(Number(item.orderItemsCount))+'</td>'	// ??
												 +'<td class="datatablesTd formatClsOrd">'
												 + chkNegAmountOrder(chkDollar(checkUundefined(parseFloat(item.tranTotalAmount).toFixed(2)))) +'</td>'
												 +'<td class="datatablesTd">'+chkNegAmountOrder(chkDollar(checkUundefined(item.totalDiscountAmount)))+'</td>'
												 +'<td class="datatablesTd">'+chkNegAmountOrder(chkDollar(checkUundefined(item.totalCoponAmount)))+'</td>'
												 +'<td class="datatablesTd">'
												 + checkUundefined(item.contactName) +'</td>'
												 +'<td class="datatablesTd">'
												 + productSKUs.toString() +'</td>'												
												 +'<td class="datatablesTd">'
												 + productDescs.toString() +'</td>'												
												 +'</tr>';
											
											cnt++;
										}
									});
									OrderDeatilCount = cnt-1;
								}
								
								//alert(isFromReturned+"--"+retFound+"--");
								//if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('N' == isFromReturned)){
									if(undefined != monthYr){
										populateMonthYearData(data,monthNew,yearNew);
										//populateCatData(); //For Category Filter
										var monthValue = monthNameArr[monthNew-1];
										if(monthNew == 0){
											monthValue = "ALL";
										}
										$("#yearSel option[value="+monthNew+""+yearNew+"]").attr("selected","selected");
										//$('#datepickerTEXT').val(monthNameArr[monthNew-1]+" / "+yearNew);
										$('#datepickerTEXT').val(monthValue+" / "+yearNew);
								        $('#datepickerTEXT').css("font-weight","bold")
								        $('#datepickerTEXT').css("font-family","Hevletica")
								        $('#datepickerTEXT').css("font-size","14px");
									}
									
									$("#sortSel").val(catid);
									
									$('#orderDetailsTabId').html("");
									$('#orderDetailsTabId').html(
											'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
													
		                                           // +'<th>Details</th>'
											
													+'<th>Reward #</th>'
													+'<th>Order No.</th>'
		                                           // +'<th style="font-weight:bold;color:red">RETURNED</th>'
		                                            +'<th>Order Date</th>'
		                                            +'<th>No. Of Items</th>'
		                                            +'<th>Order Total</th>'
		                                            +'<th>Discounts</th>'
		                                            +'<th>Coupons</th>'
		                                            +'<th>Order Contact</th>'
		                                            +'<th>productSKUs</th>'		// hidden field, only for search
		                                            +'<th>productDescs</th>'	// hidden field, only for search
		                                            + '</tr>'
													+ '</thead><tbody>' + mkttrHTML
													+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
									$("#dataTables-order_processing").css("display","none");
									$('#dataTables-order').dataTable({
										"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
										"order": [],
										"bProcessing": true,
										"oLanguage": { "sSearch": "Filter: "},
										"aoColumns": [{"bSortable": true},
											{"bSortable": true,"visible":true},
											{"bSortable":true,"visible":true},
											{"bSortable":true},
											//{"bSortable":true},
											{"bSortable":true},
											{"bSortable": true},
											{"bSortable": true},
											{"bSortable": true},
											{"bSortable": true,"visible":false,"sWidth": '1%'},
											{"bSortable": true,"visible":false,"sWidth": '1%'}],
											"fnDrawCallback": function( oSettings ) {
											/* sanjay commented
											if (OrderDeatilCount != -1) {
												for (var count = 0; count <= OrderDeatilCount; count++) {
													$('#OrderIdDtl'+count).popover({
														html : true,
														placement : 'right',
														content : $("#orderdivId"+count).html()
													}).on("hover", function(){
												        $('.popover').addClass('popoverBasic');
												    }).on('show.bs.popover', function() {
						
													});
												}
											}	
											*/
										   }
									}).fnDraw();
									
									/*
								}else if(undefined !=isFromReturned && isFromReturned =='Y' && retFound=='N'){
									//alert("from ret "+monthNew+"--"+yearNew);
									var monthYear='';
									var catId="ALL";
									if(month !=1){
										monthYear=(monthNew-1)+""+yearNew;
									}else if(month==1){
										monthYear= 12+""+(yearNew-1);
									}
									//alert("itr from ret "+monthYear+"---"+catId);
									 populateMonthYearData(data,(monthNew-1),yearNew);
									 populateCatData();
									 if(monthNew !=12){
										 $("#yearSel option[value="+(monthNew-1)+""+yearNew+"]").attr("selected","selected");
										 $('#datepickerTEXT').val(monthNameArr[monthNew-2]+" / "+yearNew);
										}else {
											$("#yearSel option[value=12"+(yearNew-1)+"]").attr("selected","selected");
											 $('#datepickerTEXT').val(monthNameArr[11]+" / "+(yearNew-1));
										}
									 
							          $('#datepickerTEXT').css("font-weight","bold")
							          $('#datepickerTEXT').css("font-family","Hevletica")
							          $('#datepickerTEXT').css("font-size","14px");
									onChangeMonthOrCat(monthYear,catId,'Y');
								}
								*/
									
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("font-size","15px");
								$('#dataTables-order_length label').css("font-family","helveticaneuebold");
								$('#dataTables-order_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-order_length label').css("font-weight","600");
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								//$('#dataTables-order_filter label').css("font-weight","600");
								$('#dataTables-order_filter label').css("float","right");
								$('#dataTables-order_filter input[type="search"]').css("width","270px");
								$('#dataTables-order_info').css("color","#004c74");
								$('#dataTables-order_info').css("font-weight","600");
								$('#dataTables-order_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$('#dataTables-order').removeClass('display').addClass('table table-striped table-bordered');
							    $('#dataTables-order_filter input[type="search"]').attr('placeholder','Enter Order No. OR Item No. OR Item Desc.').css({'width':'270px','display':'inline-block'});
					},
		  
		  failure : function(data, textStatus, jqXHR) {
			  console.log("Failed");
		  }

			})	
	 }

/*
// For category change
function onChangeCategory(catid,isFromReturned){
		// log user activity; view order list
		logUserActivityDotCom(7004, 'View Order List');
	  var custid=$("#reqCustNum").val();
	  
	      $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
		  $("#dataTables-order_processing").css("display","block");
		  var catURL = ctx+"/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM&catogeryId="+catid;
		  
		  if(catid == "ALL"){
			  catURL = ctx + "/sales/customer/"+custid+"/transactions?sbuName=STAPLES.COM&startDate="+"JAN-2016"+"&endDate="+"DEC-2016";
		  }
		  
		  $.ajax({
			 	dataType: "json",
				//Sanjay Earlier url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid+"/"+"NoChange",
				//url : "/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid+"/"+"NoChange",
			 	url : catURL,
				type : "GET",
				cache : false,
				data : "", //sanjay JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt=0;
					var mkttrHTML = "";
					 $('#order-modal').modal({
					      
						 "backdrop"  : "static",
						"keyboard"  : true,
						"show"      : true           
				    });
					 //alert(monthNew+"--"+yearNew);
					 
	
								// sanjay started
					 			var itemCnt=-1;
					 			var oldItem;
					 			var orderNumber; 
								var itemReturned;
								var orderDate ;
								var contactName;								
								var noOfItems = 0;
								var orderTotal = 0;
								
								if(data !=null){
									$.each(data.customerOrders, function(i, item) {
										
										if(item.recordType != "0003")	{
											
										
											var items1=item.orderNumber.split('.');
											var items2='';
											for(var j=0;j<items1.length;j++)
												{
													if(j!=2 && j!=0)
														{
															items2+='.'+items1[j];
														}
													else if(j==0)
														{
														items2+=items1[j];
														}
												}
											//items2;
											if(item.contactName=="null null")
												{
												item.contactName="";
												}
											orderDate = checkUundefined(item.orderTranDate);	//??
											if(orderDate != null || orderDate != ''){
												orderDate = orderDate.substring(0,10);
												orderDateArr = orderDate.split("-");
												orderDate = orderDateArr[1] + "/" + orderDateArr[2] + "/" + orderDateArr[0];
											}
											var ordertypeDef='';
											console.log("item order",item.ordrReturned);
											if(item.orderReturned == "true")
												{
													ordertypeDef="(R)";
												}
											else
												{
													ordertypeDef="";
												}
											item.totalCoponAmount=parseFloat(item.totalCoponAmount).toFixed(2);
											item.totalDiscountAmount=parseFloat(item.totalDiscountAmount).toFixed(2);
											
											var rewardNo = checkUundefined(item.orderIssuer);
											if (rewardNo.length > 0){
												if (rewardNo.charAt(rewardNo.length - 1) == 'P') {
													rewardNo = rewardNo.replace('P', 'M');
													//rewardNo = "<span style='color:#0000FF;'>"+rewardNo+"</span>";
												}
												if (rewardNo.charAt(rewardNo.length - 1) == 'C') {
													rewardNo = rewardNo.replace('C', 'S');
													//rewardNo = "<span style='color:#FF00FF;'>"+rewardNo+"</span>";
												}
											}
											
											var productSKUs = new Array();
											var productDescs = new Array();
											productSKUObj = item.orderLevelItems;
											if ( (undefined != productSKUObj) || (null != productSKUObj) ){
												$.each(productSKUObj, function(i, orderItem) {
													productSKUs.push(orderItem.sku);
													productDescs.push(orderItem.productName);
												});
											}
											
											mkttrHTML += '<tr class="odd gradeX">'
												//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
												
												 +'<td class="datatablesTd">'+rewardNo+'</td>'
												 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');" style="text-decoration:underline;padding-right:6px;">'
												 + checkUundefined(items2)+'</a><span style="color:red;margin-right: -17px;margin-left: -4px;">'+ordertypeDef+'</span></td>'
												 //+'<td class="datatablesTd">'
												 //+ checkUundefined(item.orderReturned) +'</td>'
												 +'<td class="datatablesTd">'
												 + orderDate +'</td>'
												 +'<td>'+checkUundefined(Number(item.orderItemsCount))+'</td>'	// ??
												 +'<td class="datatablesTd formatClsOrd">'
												 + formatNum(chkNegAmount(checkUundefined(parseFloat(item.tranTotalAmount).toFixed(2)))) +'</td>'
												 +'<td class="datatablesTd">'+formatNum(chkNegAmount(checkUundefined(item.totalDiscountAmount)))+'</td>'
												 +'<td class="datatablesTd">'+formatNum(chkNegAmount(checkUundefined(item.totalCoponAmount)))+'</td>'
												 +'<td class="datatablesTd">'
												 + checkUundefined(item.contactName) +'</td>'
												 +'<td class="datatablesTd">'
												 + productSKUs.toString() +'</td>'												
												 +'<td class="datatablesTd">'
												 + productDescs.toString() +'</td>'												
												 +'</tr>';
											
											cnt++;
										}
									});
									OrderDeatilCount = cnt-1;
								}
								
								
								$('#orderDetailsTabId').html("");
								$('#orderDetailsTabId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
												
	                                           // +'<th>Details</th>'
										
												+'<th>Reward #</th>'
												+'<th>Order No.</th>'
	                                           // +'<th style="font-weight:bold;color:red">RETURNED</th>'
	                                            +'<th>Order Date</th>'
	                                            +'<th>No. Of Items</th>'
	                                            +'<th>Order Total</th>'
	                                            +'<th>Discounts</th>'
	                                            +'<th>Coupons</th>'
	                                            +'<th>Order Contact</th>'
	                                            +'<th>productSKUs</th>'		// hidden field, only for search
	                                            +'<th>productDescs</th>'	// hidden field, only for search
	                                            + '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table>');
								$("#dataTables-order_processing").css("display","none");
								$('#dataTables-order').dataTable({
									"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
									"order": [],
									"bProcessing": true,
									"oLanguage": { "sSearch": "Filter: "},
									"aoColumns": [{"bSortable": true},
										{"bSortable": true,"visible":true},
										{"bSortable":true,"visible":true},
										{"bSortable":true},
										//{"bSortable":true},
										{"bSortable":true},
										{"bSortable": true},
										{"bSortable": true},
										{"bSortable": true},
										{"bSortable": true,"visible":false,"sWidth": '1%'},
										{"bSortable": true,"visible":false,"sWidth": '1%'}],
										"fnDrawCallback": function( oSettings ) {
										
									   }
								}).fnDraw();
								
								
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("font-size","15px");
								$('#dataTables-order_length label').css("font-family","helveticaneuebold");
								$('#dataTables-order_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-order_length label').css("font-weight","600");
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								//$('#dataTables-order_filter label').css("font-weight","600");
								$('#dataTables-order_filter label').css("float","right");
								$('#dataTables-order_filter input[type="search"]').css("width","270px");
								$('#dataTables-order_info').css("color","#004c74");
								$('#dataTables-order_info').css("font-weight","600");
								$('#dataTables-order_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$('#dataTables-order').removeClass('display').addClass('table table-striped table-bordered');
							    $('#dataTables-order_filter input[type="search"]').attr('placeholder','Enter Order No. OR Item No. OR Item Desc.').css({'width':'270px','display':'inline-block'});
					}

			})	
	 }
*/

function openOrderDetailsInfo(index,ordNum){
	// log user activity; view order details
	logUserActivityDotCom(7005, 'View Order Details');
	
	var inputString=$('#dataTables-order_filter input[type=search]').val();
	
	var contains=0;
	var mkttrHTML="";
	var formData={};
	var custid=$("#reqCustNum").val();
	 $.ajax({
			url : ctx+"/sales/customer/"+custid+"/"+ordNum+"/info?sbuName=STAPLES.COM",
			type : "GET",
			cache : false,
			timeout : 1000000,
			success : function(data, textStatus, jqXHR) {
				var cnt=0;
				var mkttrHTML = "";
				objPurchaseDetailsListVO=data;
			
	var item1=objPurchaseDetailsListVO.orderDetails;
	//var item=item1.orderLineItems[0];
	$.each(item1.orderLineItems, function(i, item) {
		var discount=0;
		var coupon=0;
		
		if (inputString != undefined && inputString != null &&
				item.skuNumber != undefined && item.skuNumber != null &&
				item.itemDescription != undefined && item.itemDescription != null &&
				(item.skuNumber.toLowerCase().indexOf(inputString.toLowerCase()) > -1 || item.itemDescription.toLowerCase().indexOf(inputString.toLowerCase()) > -1)){
			contains=1;
		}
		
		if(item.lineDiscount != undefined || item.lineDiscount != null) {
			$.each(item.lineDiscount, function(i, discountItem) {
				/*console.log("discount : " +discount);
				console.log("discountItem.discountAmount : " +discountItem.discountAmount);
				console.log("checkUundefined(discountItem.discountAmount) : " +checkUundefined(discountItem.discountAmount));
				console.log("formatNum(checkUundefined(discountItem.discountAmount)) * 100 : " +formatNum(checkUundefined(discountItem.discountAmount)) * 100);
				*/
				//console.log((formatNum(checkUundefined(discountItem.discountAmount)) * 100));
				
				discount += (checkUundefined(discountItem.discountAmount) * 100);
			});
			
			if (discount == 0){
				discount = chkNegAmount(formatNum("0"));
			}else{
				discount = chkNegAmount(formatNum((checkUundefined(discount)/100)));
			}			
			
		}else{
			
			discount = chkNegAmount(formatNum("0"));	//discount = 0
			
		}
		
		if(item.couponItem != undefined || item.couponItem != null) {			
			$.each(item.couponItem, function(i, couponItem) {
				/*console.log("coupon : " +coupon);
				console.log("couponItem.couponLineAmount : " +couponItem.couponLineAmount);
				console.log("checkUundefined(couponItem.couponLineAmount) : " +checkUundefined(couponItem.couponLineAmount));
				console.log("formatNum(checkUundefined(couponItem.couponLineAmount)) * 100 : " +formatNum(checkUundefined(couponItem.couponLineAmount)) * 100);
				*/
				//console.log((formatNum(checkUundefined(couponItem.couponLineAmount)) * 100));
				
				coupon += (checkUundefined(couponItem.couponLineAmount) * 100);
			});
			
			if (coupon == 0){
				coupon = chkNegAmount(formatNum("0"));
			}else{
				coupon = chkNegAmount(formatNum((checkUundefined(coupon)/100)));
			}
			
		}else{
			
			coupon = chkNegAmount(formatNum("0"));	//coupon = 0
			
		}
		
		var totalSpend = '';
		var productSKU =  '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.productSKU)+'</a>';
		if(ordNum.startsWith("POS")) {
			
			totalSpend = chkNegAmount(formatNum(checkUundefined(item.extendedPrice)));
			if( checkUundefined(item.tranLineStatusId).length > 0 && checkUundefined(item.tranLineStatusId) == "RETURN" && checkUundefined(item.tranLineStatusId)!='' && checkUundefined(item.tranLineStatusId)!=null) {
				productSKU = "<span style='color:red;'>"+productSKU+"</span>";
			}
		}else{
			
			totalSpend = chkNegAmount(formatNum(checkUundefined(item.lineTotalAmount)));
			if('' != checkUundefined(item.masterSalesTranId)  && checkUundefined(item.masterSalesTranId) != -1 & checkUundefined(item.masterSalesTranId) != '' && checkUundefined(item.masterSalesTranId) != null) {
				productSKU = "<span style='color:red;'>"+productSKU+"</span>";
			}else if(checkUundefined(item.masterSalesTranId) == -1){
				productSKU = "<span style=''>"+productSKU+"</span>";
			}
		}
			
		var itemStatusDesc = checkUundefined(item.itemStatusDescription);
		if (itemStatusDesc.length > 0 && itemStatusDesc == "-1"){
			itemStatusDesc = "DELIVERED";
		}
		
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold; !important;">'
			 + checkUundefined(productSKU) +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.productName) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.transQuantity) +'</td>'
			 /*+'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + formatNum(checkUundefined(item.productSKU)) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + chkNegAmount(formatNum(checkUundefined((item.transQuantity)*(item.lineTotalAmount)))) +'</td>'
			 */
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(discount) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(coupon) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(totalSpend) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(itemStatusDesc) +'</td>'				 
			 +'</tr>';
				
	});		
	
	$('#OrderDetailsInfoId').html("");
	$('#order-info').modal({
	    
		 "backdrop"  : "static",
	"keyboard"  : true,
	"show"      : true           
	}); 
	$("#ordertitle").html("");
	$("#ordertitle").html("Order Info - "+ordNum);
	$('#OrderDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-OrderInfo" width="100%" ><thead><tr>'
			+'<th>Item No.</th>'
			+'<th>Item description</th>'
	        +'<th>Qty</th>'
	    	//+'<th>Price</th>'
			//+'<th>Coupons</th>'
	        +'<th>Discount</th>'
	        +'<th>Coupon</th>'
	        +'<th>Total Spend</th>'
	        +'<th>Status</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	var oTable=$('#dataTables-OrderInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "oLanguage": { "sSearch": "Filter: "},
		  "order": [],
			"aoColumns": [{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'}
			],
			/*"fnDrawCallback": function (oSettings) {
				        $(".dataTables_filter").each(function () {
				            $(this).appendTo($(this).parent().siblings(".panel-body"));
				        });
				    }
				
			})*/

			
	})	
	
	if(contains == 1){
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
//alert($('#dataTables-OrderInfo_filter input[type=search]').val());
	//$("#dataTables-OrderInfo td").css("padding-left","10px");
	$("#dataTables-OrderInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-OrderInfor_length label').css("color","#004c74");
	$('#dataTables-OrderInfo_length label').css("color","#004c74");
	$('#dataTables-OrderInfo_length label').css("font-weight","600");
	$('#dataTables-OrderInfo_filter label').css("color","#004c74");
	$('#dataTables-OrderInfo_filter label').css("letter-spacing","1px");
	$('#dataTables-OrderInfo_filter').css("text-align","right");
	$('#dataTables-OrderInfo_filter label').css("font-weight","600");
	$('#dataTables-OrderInfo_filter label').css("float","right");
	$('#dataTables-OrderInfo_info').css("color","#004c74");
	$('#dataTables-OrderInfo_info').css("font-weight","600");
	$('#dataTables-OrderInfo_paginate').css("text-align","right");
	$('.form-inline .form-control').css("color","#004c74");
	$('.form-inline .form-control').css("border","1px solid #004c74");
	$('select .form-control.input-sm').css("border","1px solid #004c74");
	$('#dataTables-OrderInfo').removeClass('display').addClass('table table-striped table-bordered');
    $('#dataTables-OrderInfo_filter input[type="search"]').attr('placeholder','Enter Item No. OR Item Desc.').attr("style","width : 210px !important").css('display','inline-block');
    $('#dataTables-OrderInfo_filter input[type="search"]').focus();
    
//    oTable.search($(this).val()).draw(); 
    
  /*  if (objPurchaseDetailsListVO[index].orderNumber.indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString).draw();
    }*/
	
			}});   
}


function openReturnedOrderDetailsInfo(index , ordNum){
	// log user activity; view order details
	logUserActivityDotCom(7018, 'Clicked Returns on Orders grid');
	
	var inputString=$('#dataTables-order_filter input[type=search]').val();
	var contains=0;
	var mkttrHTML="";
	//alert(inputString);
	$.each(objPurchaseDetailsListVO[index].purchRwdsDtlListVO, function(i, item) {
		
		if (item.netSpendAmount >= 0) {
			return true; // continue;
		}
		if (inputString != undefined && inputString != null &&
				item.skuNumber != undefined && item.skuNumber != null &&
				item.itemDescription != undefined && item.itemDescription != null &&
				(item.skuNumber.toLowerCase().indexOf(inputString.toLowerCase()) > -1 || item.itemDescription.toLowerCase().indexOf(inputString.toLowerCase()) > -1)){
			contains=1;
		}
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber)+'</a>' +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.tranDate) +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.itemDescription) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + checkUundefined(item.totalQty) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + chkNegAmount(formatNum(checkUundefined(item.totalPriceAmount))) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + formatNum(checkUundefined(item.couponAmount)) +'</td>'
			 +'<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + chkNegAmount(formatNum(checkUundefined(item.netSpendAmount))) +'</td>'
			 +'</tr>';
		
	});	
	
	$('#OrderDetailsInfoId').html("");
	$('#order-info').modal({
	    
		 "backdrop"  : "static",
	"keyboard"  : true,
	"show"      : true           
	}); 
	$("#ordertitle").html("");
	$("#ordertitle").html("Returned Info - "+ordNum);
	$('#OrderDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-OrderInfo" width="100%" ><thead><tr>'
			+'<th>Item No.</th>'
			+'<th>Return Date</th>'
			+'<th>Item description</th>'
	        +'<th>Qty</th>'
	    	+'<th>Price</th>'
			+'<th>Coupons</th>'
	        +'<th>Total Spend</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	var oTable=$('#dataTables-OrderInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "oLanguage": { "sSearch": "Filter: "},
		  "order": [],
			"aoColumns": [{"bSortable": true, "sWidth": '33%'},{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%'},
			{"bSortable": true, "sWidth": '33%', "visible" : false},
			{"bSortable": true, "sWidth": '33%'}
			],
			/*"fnDrawCallback": function (oSettings) {
				        $(".dataTables_filter").each(function () {
				            $(this).appendTo($(this).parent().siblings(".panel-body"));
				        });
				    }
				
			})*/

			
	})	
	
	if(contains == 1){
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
//alert($('#dataTables-OrderInfo_filter input[type=search]').val());
	//$("#dataTables-OrderInfo td").css("padding-left","10px");
	$("#dataTables-OrderInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-OrderInfor_length label').css("color","#004c74");
	$('#dataTables-OrderInfo_length label').css("color","#004c74");
	$('#dataTables-OrderInfo_length label').css("font-weight","600");
	$('#dataTables-OrderInfo_filter label').css("color","#004c74");
	$('#dataTables-OrderInfo_filter label').css("letter-spacing","1px");
	$('#dataTables-OrderInfo_filter').css("text-align","right");
	$('#dataTables-OrderInfo_filter label').css("font-weight","600");
	$('#dataTables-OrderInfo_filter label').css("float","right");
	$('#dataTables-OrderInfo_info').css("color","#004c74");
	$('#dataTables-OrderInfo_info').css("font-weight","600");
	$('#dataTables-OrderInfo_paginate').css("text-align","right");
	$('.form-inline .form-control').css("color","#004c74");
	$('.form-inline .form-control').css("border","1px solid #004c74");
	$('select .form-control.input-sm').css("border","1px solid #004c74");
	$('#dataTables-OrderInfo').removeClass('display').addClass('table table-striped table-bordered');
    $('#dataTables-OrderInfo_filter input[type="search"]').attr('placeholder','Enter Item No. OR Item Desc.').attr("style","width : 210px !important").css('display','inline-block');
    $('#dataTables-OrderInfo_filter input[type="search"]').focus();
    
//    oTable.search($(this).val()).draw(); 
    if (objPurchaseDetailsListVO[index].orderNumber.indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString).draw();
    }
    
}


function openSuperUserDetails(){
	  // log user activity; view user details
	  logUserActivityDotCom(7002, 'View User Details');
	  
	  var formData={};
	  var dt = geDataRefreshTime('WEBACTIVITY');
		//alert(dt);
	  $("#userDiv").prepend($("#superId"));
		$("#updateDateValueUser span").html(dt+" ET");
	  $("#superId").css("display","block");
	  $("#superContent").css("display","block");
	  $("#showHideId0").prop('class','fa fa-times');
	  commonScroll('superId');
	  var custid= $("#reqCustNum").val();
	  $("#dataTables-example_processing").html('<div id="example_processing_user" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	  $("#dataTables-example_processing").css("display","block");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getSuperUSerHighLevelDataSam/"+custid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					 $('#my-modal').modal({
				      
					 "backdrop"  : "static",
			"keyboard"  : true,
			"show"      : true           
			    }); 
				    var cnt=0;
					var mkttrHTML = "";
					 $('#superUserTableId').html("");
					 var name='';
								if(null!= data && data !=undefined){
								
									
									mkttrHTML += '<tr>'
										var skuFound= 'N';
									dotcomActivityVOArr[0]=data.staplesDotcomActivityVo;
										$.each(data.staplesDotcomActivityVo	, function(i, items) {
											
											if(undefined !=items.skuNum && null !=items.skuNum && 'null'!=items.skuNum && ''!=items.skuNum){
												skuFound ='Y';
											}
											cnt++;
										});
										
										mkttrHTML += '<td class="datatablesTd">'
											+checkUundefined(data.contactFullName) +'</td>'
											 +'<td class="datatablesTd"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;text-decoration:underline;cursor:pointer;" onclick="javascript:openOutlook(\''+checkUundefined(data.email)+'\');">' 
											 + checkUundefined(data.email) +'</span></td>'
											 +'<td class="datatablesTd">'+'<span onclick="javascript:logUserActivityDotCom(8090,\''+checkUundefined(data.phoneNum)+': clicked from Users Grid in SAM Recommendation Page for Customer'+custid+'\')">'
											 + checkUundefined(data.phoneNum)+'</span>'+'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(data.custTimeone) +'</td>';
										if(skuFound=='N')
											mkttrHTML +='<td class="datatablesTd">'+ checkUundefined(skuFound) +'</td>';
										else
											mkttrHTML +='<td class="datatablesTd"><a href="javascript:openSuperUserModalInfo(0,\''+dotcomActivityVOArr+'\',\'dotcom\',\''+checkUundefined(data.contactFullName)+'\')" style="text-decoration:underline;">'+checkUundefined(skuFound)+'</a></td>';
										
									mkttrHTML += '</tr>';
								}
								$('#superUserTableId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-example"  ><thead><tr>'
												+'<th>Contact Name </th>'
												+'<th>Email Address</th>'
												+'<th>Phone</th>'
												+'<th>Time Zone</th>'
												+'<th>Staples.com Activity</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table>');
								  $("#dataTables-example_processing").css("display","none");
								$('#dataTables-example').dataTable({
									"lengthMenu": [[5, 15, 25, -1], [5, 15,25, "All"]],
									"bSort": true,
									"order": [],
									"bProcessing": true,
									"oLanguage": { "sSearch": "Filter: "},
									"aoColumns": [{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true}
									
									]
								}).fnDraw();
								$("#dataTables-example td:nth-child(5)").css("white-space","nowrap");
								$('#dataTables-example_length label').css("color","#004c74");
								$('#dataTables-example_length label').css("font-size","15px");
								$('#dataTables-example_length label').css("font-family","helveticaneuebold");
								$('#dataTables-example_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								$('#dataTables-example_filter label').css("color","#004c74");
								$('#dataTables-example_filter label').css("letter-spacing","1px");
								$('#dataTables-example_filter').css("text-align","right");
								$('#dataTables-example_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-example_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								$('#dataTables-example_filter label').css("float","right");
								$('#dataTables-example_info').css("color","#004c74");
								$('#dataTables-example_info').css("font-weight","600");
								$('#dataTables-example_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$('#dataTables-example').removeClass('display').addClass('table table-striped table-bordered');
								$('#dataTables-example_filter input[type="search"]').attr('placeholder','Enter Order Contact').css({'width':'250px','display':'inline-block'});
					   
					}

			})	
 }

function openSuperUserModalInfo(index, dotcomData, fromWhere, name){
    var mkttrHTML="";
    var column=new Array();
    var count=0;
    var column3= $.each(dotcomActivityVOArr[index], function(i, item) {
        return item;
    });
 if((null ==column3 || ''==column3) || column3.length <1){
    alert("No details available for this customer");
    return false;
 }
 
     /*  Dotcom Data Start  */
     if(null !=column3 && ''!=column3){
           var htmlData="";
           $('#a3').attr("data-toggle","tab");
           $('#li3').removeClass('disabled');
           $("#tab_1_3").html("");
           
         for(var i=0;i<column3.length;i++){
           var thumbnail=checkUundefined(column3[i].thumbnail);
           
           if(thumbnail=='' || null ==thumbnail | undefined == thumbnail){
                  thumbnail="http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
           } else{
                  thumbnail='http://www.staples-3p.com/s7/is/image/Staples/'+thumbnail;
           }
           
           var dotActivity = checkUundefined(column3[i].cartActivity);
           if(dotActivity == 'Abandoned')
        	   dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;" >Abandoned</span></div>';
     	   else if(dotActivity == 'Viewed')
     		  dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:darkblue;" >Viewed</span></div>';
     	   else if(dotActivity == 'Purchased') 
     		  dotActivity = '<div class="a-row a-size-small"> <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:green;" >Purchased</span></div>';
           
          var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNum:""):"");
          htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column3[i].skuName)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" alt="jQuery in Action, Second Edition"/>'
                    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;">'
                       +truncateTitle(checkUundefined(column3[i].skuName))
                   +'</div></a>'
                  
                  +'<div class="a-row a-size-small"><a class="a-size-small a-link-child" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: 100px;" href="javascript:openUrl(\''+url3+'\')">'+checkUundefined(column3[i].skuNum)+'</a></div>'
                  +'<div class="a-row a-size-small">'+checkUundefined(column3[i].lastBrowseDate).substring(0,11)+'</div>'
                  +'<div class="a-row a-size-small">$'+checkUundefined(column3[i].unitPrice)+'</div>'
                  + dotActivity
           +'</li>';
           
         }
           totalDotCommLength=column3.length;

         var prefixData='<div id="amazon_scroller_dotcom'+index+'" class="amazon_scroller" style="background-color:#fff;">'
                              +'<div id="dotcomId" class="amazon_scroller_mask"><ul id="SUulid">'
                              
         var suffixData='</ul></div><ul id="dotcom_nav" class="amazon_scroller_nav">'
                           +'<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'</ul><div style="clear: both"></div></div>'             
         $("#tab_1_3").html(prefixData+""+htmlData+""+suffixData);
         

         $("#amazon_scroller_dotcom"+index).amazon_scroller({
               scroller_title_show: 'disable',
               scroller_time_interval: '1800000000000',
               scroller_window_background_color: "#CCC",
               scroller_window_padding: '10',
               scroller_border_size: '1',
               scroller_border_color: '#000',
               scroller_images_width: '135',
               scroller_images_height: '160',
               scroller_title_size: '12',
               scroller_title_color: 'black',
               scroller_show_count: '4',
               directory: 'images'
           });

     }else{
           $("#tab_1_3").html("");
           $('#a3').removeAttr("data-toggle");
           $('#li3').addClass('disabled');
     }
     
     if(fromWhere=='dotcom'){ 
    	 logUserActivityDotCom(7008, 'View STAPLES.COM Activity');
    	 $('#li1').remove();
    	 $("#tab_1_1").remove();
    	 $('#a3').attr("aria-expanded",true);
         $('#li3').attr("class","active");
         $('#li2').attr("class","");
         $('#li1').attr("class","");
         if('tab-pane fade active in'==($("#tab_1_1").attr("class"))){
        	 $("#tab_1_1").attr("class" ,"tab-pane fade");
         }
         $("#tab_1_3").attr("class","tab-pane fade active in");
         $("#a3").attr("style","color: darkolivegreen !important; font-family: arialmt; font-weight: bold; font-size: 16px;text-align: left !important; padding: 10px;");

     }
     /*  Dotcom Data End  */

    $('#searchStringId').html("");
    $('#super-info-dialog').modal({
           "backdrop"  : "static",
           handle: ".modal-header",
    "keyboard"  : true,
    "show"      : true           
    }); 
    if(name!=undefined && name!=''){
           $("#uName").html(name); 
    }
    $("table#searchStringId").parent().remove();
}



function populateGrowthData(){
	//$("#growUlId").html('<li class="list-group-item" style="text-align:center !important;padding:4px 12px !important"><span class="text-muted small"><em> Please Wait...</em></span></li>');
	//populateNotificationData('Growth');
}
function populateRetentionData(){ 
	//$("#retUlId").html('<li class="list-group-item" style="text-align:center !important;padding:4px 12px !important" ><span class="text-muted small"><em> Please Wait...</em></span></li>');
	//populateNotificationData('Retention');
}
function populateExpansionData(){
	//$("#expUlId").html('<li class="list-group-item" style="text-align:center !important;padding:4px 12px !important" ><span class="text-muted small"><em> Please Wait...</em></span></li>');
	//populateNotificationData('Expansion');
}
function openUrl(url){
	 var win = window.open(url, '_blank');
	  win.focus();
}

function populateQuickSearchData(){
	var formData={};
	var selPlayType=$("#filterBy").val();
	var sliderSubPlaysItem=$("#sliderSubPlaysItem").val();
	var custid=$("#accId").val();
	var selectedQualScore=$("#selectedQualScore").val();
	var selectedSegScore=$("#selectedSegScore").val();
	configCookie('other');
	//alert( 'istart='+$("#iStart").val())
	var len=parseInt(quickSearchStart)
	$('#dataTables-QuickSearch').dataTable( {
		
	       "bDestroy" : true,
	       "oLanguage": { "sSearch": "Filter: "},
	       "sAjaxSource": ctx+"/sales/representive/"+custid+"/customer/list",
	       "fnServerParams": function (aoData) { 
		    	   aoData.push({ "name": "selectedSubPlays", "value": sliderSubPlaysItem});
		    	   aoData.push({ "name": "selectedQualScore", "value": selectedQualScore});
		    	   aoData.push({ "name": "selectedSegScore", "value": selectedSegScore});
		    	   aoData.push({ "name": "repRoleCode", "value": ""});
		    	   aoData.push({ "name": "accType", "value":"NA"});
		    	   },
	       "processing":true,
	       /*"bServerSide" : true,*/
			"responsive": true,
			"iDisplayLength" : 5,
			"iDisplayStart": len ,
			"pageLength": 5,
			"lengthChange": false,
			"paging" :true,
			"pagingType": "simple_numbers",
			"info" : true,
			"ordering": false,
			"searching": false,
			"dataType": 'jsonp',
			'sServerMethod' : "POST",
			'bStateSave':true,
			"sAjaxDataProp": "customerInfo",
			"fnDrawCallback": function ( oSettings ) {
				$("#dataTables-QuickSearch tr th").hide();
			
			},
			"columnDefs": [
			               { className: "dt-head-center" }
			             ],
			"aoColumns" : [
							{
								"mData": null,
								 "mRender" : function(
										 data, type, full) {
									 
									return '<li class="media" onclick="opencustDetails('+full.customerNo+')">'
										+'<div class="media-status">'
										+'<span class="badge badge-warning">'+checkUundefinedNullBlankZero(full.account_rank)+'</span>'
									    +'</div>'
									    +'<div class="media-body" id="custInfoId_'+full.account_rank+'">'
										+'<h4 class="media-heading" style="font-family:Helvetica;font-weight:bold;font-size:14px; color: #ddd; ">'+ checkUundefined(full.customerNo)+'</h4>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+ checkUundefined(full.tier)+'</div>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+checkUundefined(full.companyName)+'</div>'
									    +'</div>'
								        +'</li>';
								 }
							}							]

	   } );
}

function clearCookie(){
	$.cookie('sidebar_closed', '', { path: '/', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDasbhoard', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDashboard', expires:-1 });
}
function openMainDashboard(){
	$("#customerForm").attr("action","./home_template4")
	$('#customerForm').submit();
}
function openRecommendations(){
	//clearCookie();
	$("#customerForm").attr("action","./recommActionSAMNew")
  $('#customerForm').submit();
}
function openStoreLocator(){
	//clearCookie();
  $("#customerForm").attr("action","./storeLocActionSamNew")
  $('#customerForm').submit();
}
function formatZip(val){
	if(undefined!=val && '' !=val && val.length>5){ 
     val=val.substring(0, 5);
	}else if(undefined!=val && '' !=val && val.length<5){ 
	 val=pad(val,5);	
	}
	return val;
}
function pad(n, width, z) {
	  z = z || '0';
	  n = n + '';
	  return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}
function formatPhone(phoneNumber) {
	if(phoneNumber.indexOf('(') != -1) {
		phoneNumber = phoneNumber.slice(1).replace(")",".").replace("-",".").replace(" ","");
	} else {
		phoneNumber = phoneNumber.replace(/-/g,".");
	}
	return phoneNumber;
}
function showTrainingPopUp() {
	$('#training_modal_sam').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function showSfdcPopUp(url) {
	$("#sfdcConfUrl").val("");
	if(undefined!=url && ''!=url)
		 url=url.replace(/@/g, "%40");
	$("#sfdcConfUrl").val(url);
	$('#sfdcConfModal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function showCallToActionPopUp() {
	$('#callToAction_PopUp').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function showEditPopUp(editId) {
	$('#'+editId).modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function removeUnderScore(val){
	if(undefined !=val && ''!=val){
		val = val.replace(/_/g, " ");
	}
	return val;
}
function toCamCase(str)
{
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}
function formatOrderAmt(n){
	  if(n != ''){
	  		var commaFormatedVal= n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
	  		if(commaFormatedVal.indexOf(".")!=-1){
	  			if(commaFormatedVal.split(".")[0] ==''){
	  				commaFormatedVal='0.'+commaFormatedVal.split(".")[1];
	  			}
	  			if((commaFormatedVal.split(".")[1]).length==1){
	  				commaFormatedVal=commaFormatedVal.split(".")[0]+"."+commaFormatedVal.split(".")[1]+'0';
	  			}
	  		}else{
	  			commaFormatedVal=commaFormatedVal;
	  		}
	  	    return commaFormatedVal.replace("$","");
	  	} else {
	  		return '';
	  	}
}
function calculateYOY(n,n1){
	
    if((n == '' || n == '0') && (n1 != '' && n1 != '0') ){
          var yoyVal= -100;
        return yoyVal+'%';
    } else if((n1 == '' || n1 == '0') && (n != '' && n != '0') ){
        var yoyVal= '';
        
      return yoyVal;
  } else if((n1 != '' && n1 != '0') && (n != '' && n != '0')){
        var yoyVal= (n-n1)/n1;
        yoyVal = yoyVal*100;
        yoyVal=Math.round(yoyVal * 100) / 100;
        var t = -100;
        if (yoyVal < 0  && parseFloat(n) < parseFloat(n1) && (yoyVal >-100)){
        	//yoyVal= 100;
        	return yoyVal+'%';
        }else if (yoyVal < 0 ){
        	yoyVal= 100;
        	return '-'+yoyVal+'%';
        }else if(yoyVal > 100){
        	yoyVal = 100
        	return '+'+yoyVal+'%';
        }else if(yoyVal > 0){
        	return '+'+yoyVal+'%';;
        }
      
  } else {
	  return '';
  }
}


function configureCalender(){
	$.datepicker._updateDatepicker_original = $.datepicker._updateDatepicker;
    $.datepicker._updateDatepicker = function(inst) {
        $.datepicker._updateDatepicker_original(inst);
        var afterShow = this._get(inst, 'afterShow');
        if (afterShow)
            afterShow.apply((inst.input ? inst.input[0] : null));  // trigger custom callback
    }
}
function openSubCallToAction(seg){
	var formData={};
	var custid=$("#reqCustNum").val();
	$("#segName").html(seg);
	$.ajax({
	 	dataType: "json",
		url : ctx+"/subCallToAction/"+custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$("#rationaleData").html("");
			alert(data.rationaleHtmlData);
			$("#rationaleData").html(data.rationaleHtmlData);
			showCallToActionPopUp();			
		}
	});
	
}
function applySubCallToAction(rationaleHtml){
	var custid=$("#reqCustNum").val();
	//$("#segName").html(seg);
	$.ajax({
	 	dataType: "json",
		url : ctx+"/applySubCallToAction/"+custid,
		type : "POST",
		cache : false,
		data: {
			RationaleHtmlCode : rationaleHtml
	    },
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$(".Editor-editor").html(data.rationaleHtmlData);
			//showCallToActionPopUp();			
		}
	});
	
}

function populateUpCrossReorder(){

	var formData = {};
	var upSellCount='', crossSellCount='', reorderCount='';
	var dt = geDataRefreshTime('REORDER');
	$("#updateDateValueReorder span").html(getCurrentDateTime()+" ET");
	var dt = geDataRefreshTime('BABRECOM');
	$("#updateDateValueBAB span").html(getCurrentDateTime()+" ET");
	var dt = geDataRefreshTime('HRRECOM');
	$("#updateDateValueHR span").html(getCurrentDateTime()+" ET");
	var custid = $("#reqCustNum").val();
	var accId = $("#accId").val();
	if (undefined != custid && custid != 'select') {
	$.ajax({
				//url : ctx + "/getReorderRecommendations/" + custid ,
				url : ctx + "/recommendation/customer/"+ custid +"/user/"+ accId +"/level/nested",		
				type : "GET",
				//cache : false,
				data : "",//JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					
					if (data != null && data != undefined) {
						//alert(data+''+data.length)
						var upSellHtml=''
						$.map(data, function(V, K) {
							//alert(V+'<--------->'+K)
							if(undefined!=K && K==0){
								upSellCount=0;
								//alert("K="+K)
								var active=0;
								$.each(V,function(key, items) {
									if(undefined !=key && key =='products'){
										if(null !=items && 'null'!=items && undefined !=items){
										$.each(items,function(i, item) {
											
											$.map(item, function(objects,productStatus) {
												//alert(11)
												
												if(productStatus=='View'){
													//alert("View")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){
															activeClass='item active';
															active=1;
														}
														else
													    activeClass='item';
														upSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														upSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuUpsell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #428bca;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Viewed</span></div>'
														+'</div>';
														upSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															upSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuUpsell\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:428bca;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															upSellCount++;
														});
														upSellHtml+='</div>';
														upSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}else if(productStatus=='Abondaned'){
													//alert("aban")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){
															activeClass='item active'
																active = 1;
														}
														else
													    activeClass='item';
														upSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														upSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuUpsell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #d9534f;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Abandoned</span></div>'
														+'</div>';
														upSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															upSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuUpsell\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															upSellCount++;
														});
														upSellHtml+='</div>';
														upSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}else if(productStatus=='Sale'){
													//alert("aban")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){
															activeClass='item active'
																active = 1
														}
														else
													    activeClass='item';
														upSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														upSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuUpsell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #5cb85c;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Purchased</span></div>'
														+'</div>';
														upSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															upSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuUpsell\')")>'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															upSellCount++;
														});
														upSellHtml+='</div>';
														upSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}
												$('#carouselUpSellId').html(upSellHtml);
											});
										});
									}else{
										$("#carouselUpSellId")
										.html(
												"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
									}
									}
								});
								configcarousel('UpSell',upSellCount);
						}//upsell end
							
							crosSellHtml='';
						// cross sell start
							if(undefined!=K && K==1){
								//alert("K="+K)
								var active=0;
								crossSellCount=0;
								$.each(V,function(key, items) {
									if(undefined !=key && key =='products'){
										if(null !=items && 'null'!=items && undefined !=items){
										$.each(items,function(i, item) {
											$.map(item, function(objects,productStatus) {
												//alert(11)
												
												if(productStatus=='Sale'){
													//alert("aban")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){;
															activeClass='item active'
																active = 1;
														}
														else
													    activeClass='item';
														crosSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														crosSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuCrosssell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #5cb85c;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Purchased</span></div>'
														+'</div>';
														crossSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															crosSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuCrosssell\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															crossSellCount++;
														});
														crosSellHtml+='</div>';
														crosSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}else if(productStatus=='Abondaned'){
													//alert("View")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){
															activeClass='item active';
																active = 1;
														}
														else
													    activeClass='item';
														crosSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														crosSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuCrosssell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #d9534f;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Abandoned</span></div>'
														+'</div>';
														crossSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															crosSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuCrosssell\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:428bca;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															crossSellCount++;
														});
														crosSellHtml+='</div>';
														crosSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}else if(productStatus=='View'){
													//alert("aban")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){
															activeClass='item active';
																active = 1;
														}
														else
													    activeClass='item';
														crosSellHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														var imageurl=name.imageurl;
														var skuDetailURL=name.url;
														crosSellHtml+='<div class="span3" style="box-shadow: 0px -1px 10px 1px grey  !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+imageurl+'" alt="Image" style="max-width:100%;"></a>'
														+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+skuDetailURL+'\')" onclick="generateLogs(\'skuCrosssell\')">'
														+ name.sku
													    +'</a></div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(name.dotcomPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(name.specialPrice)+'</font></div>'
													    +'</div>'
													    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
													    + name.desc
									                   +'</div>'
									                   +'<div class="a-row a-size-small" style="background-color: #428bca;text-align:center;padding:5px;color:#fff;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#fff;text-align:center;letter-spacing:.5px;font-weight:bold;" >Viewed</span></div>'
														+'</div>';
														crossSellCount++;
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															crosSellHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuCrosssell\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															crossSellCount++;
														});
														crosSellHtml+='</div>';
														crosSellHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}
												$('#carouselCrossSellId').html(crosSellHtml);
											});
										});
									}else{
										$("#carouselCrossSellId")
										.html(
												"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
									}
									}
								});
								configcarousel('CrossSell',crossSellCount);
						}
							
						// cross sell ends	
							// Reorder start
							reorderHtml='';
							if(undefined!=K && K==2){
								//alert("K="+K)
								var active=0;
								reorderCount=0;
								$.each(V,function(key, items) {
									if(undefined !=key && key =='products'){
										if(null !=items && 'null'!=items && undefined !=items){ 
										$.each(items,function(i, item) {
											$.map(item, function(objects,productStatus) {
												//alert(11)
												
												if(productStatus=='Re-Order'){
													//alert("aban")
													//alert(objects.length +'---');
													$.map(objects, function(name,val) {
														//alert("ab = "+name.recommendations+'---'+val);
														var activeClass=''
														if(val==0 && active != 1){;
															activeClass='item active'
																active = 1;
														}
														else
													    activeClass='item';
														reorderHtml+= '<div class="'+activeClass+'" style="padding-left:70px;">'
											        +'<div class="row-fluid">';
														$.each(name.recommendations,function(a, b) {
															//alert("recom"+a);
															var recommImageUrl=b.imageurl;
															var accessUrl=b.url;
															reorderHtml+='<div class="span3" style="box-shadow: 1px 2px 14px rgba(0,0,0,0.3) !important;margin-top:10px !important;"><a href="#x" class="thumbnail"><img src="'+recommImageUrl+'" alt="Image" style="max-width:100%;"></a>'
															+'<div class="a-row a-size-small" style="text-align:center;"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+accessUrl+'\')" onclick="generateLogs(\'skuReorder\')">'
															+ b.sku
														    +'</a></div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">DotCom <span style="font-weight:bold;font-size:11px;">NOW</span>: </font><font color="#7B241C">$'+checkUundefined(b.dotcomPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="a-row a-size-small" style="text-align:center;"><div style="padding: 0;margin: 0;white-space: nowrap;"><font color="red">Tier Price: </font><font color="#7B241C">$'+checkUundefined(b.specialPrice)+'</font></div>'
														    +'</div>'
														    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 67px;border-bottom: 1px solid #bbb;border-style: dashed;text-align:center;padding-top:5px;font-size:12px;font-family:arialmt;" aria-hidden="true" data-rows="3">'
														    + b.desc
										                   +'</div>'
										                   +'<div class="a-row a-size-small" style="text-align:center;padding:5px;"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;font-weight:bold;" >Sell</span></div>'
															+'</div>';
															reorderCount++;
														});
														reorderHtml+='</div>';
														reorderHtml+='</div>';
														//alert(upSellHtml)
													});
													
												}
												$('#carouselReorderId').html(reorderHtml);
											});
										});
									}else{
										$("#carouselReorderId")
										.html(
												"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
									}
									}
								});
								configcarousel('Reorder',reorderCount);
						}
							
							//Reorder ends
			            });
					}
					
				}

			})
} else {
	$("#ReorderRecommTabId")
			.html(
					"<div id='PlayNdfId' style='padding-top: 20%;text-align: center;font-weight: 700;font-size: 20px;color: #ccc;'> NO DATA FOUND </div>");
}

	
}

function getLatestFiscalDateOrderSAMNew(){
	  
	var custid= $("#reqCustNum").val();
	 
	var formData={};
		$.ajax({
			dataType: "json",
			url : ctx+"/sales/customer/"+custid+"/latestorderdate",
			//url : "/getLatestFiscalDate/"+custid,
			type : "GET",
			//cache : false,
			async : false,
			data : "",//JSON.stringify(formData),
			timeout : 1000000,
			success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					//alert(data);
					latestFiscalDateOrderSAMNew=checkUundefined(data.latestOrderDate);
					if(checkUundefined(latestFiscalDateOrderSAMNew).length > 0)
					{
						latestFiscalDateOrderSAMNew = latestFiscalDateOrderSAMNew.replace("-","");
					}					
				}
			}

		})			

}

function configcarousel(container,c){
	$('#myCarousel'+container).carousel({
		//interval: 1000000000000000000
		pause: false,
	    interval: false
		});
	
	if((undefined !=c) && (c<=4)){ 
		$('#myCarousel'+container+' a.right.carousel-control').css('pointer-events','none');
	    $('#myCarousel'+container+' a.right.carousel-control').css('opacity','.07');
	    $('#myCarousel'+container+' a.right.carousel-control').css('cursor','default');
    	$('#myCarousel'+container+' a.left.carousel-control').css('pointer-events','none');
	    $('#myCarousel'+container+' a.left.carousel-control').css('opacity','.07');
	    $('#myCarousel'+container+' a.left.carousel-control').css('cursor','default');
	}else{
		$('#myCarousel'+container+' a.left.carousel-control').css('pointer-events','none');
	    $('#myCarousel'+container+' a.left.carousel-control').css('opacity','.07');
	    $('#myCarousel'+container+' a.left.carousel-control').css('cursor','default');
	}
	$('#myCarousel'+container+' a.left.carousel-control').on('click', '', function() {
		 var $this = $(this);
		if($('#carousel'+container+'Id.carousel-inner div:nth-child(2)').hasClass('active')) { 
		    $this.css('pointer-events','none');
		    $this.css('opacity','.07');
		    $this.css('cursor','default');
		    $('#myCarousel'+container+' a.right.carousel-control').css('pointer-events','');
		    $('#myCarousel'+container+' a.right.carousel-control').css('cursor','pointer');
		    $('#myCarousel'+container+' a.right.carousel-control').css('opacity','1');
		  }else{
			  $('#myCarousel'+container+' a.right.carousel-control').css('pointer-events','');
			  $('#myCarousel'+container+' a.right.carousel-control').css('opacity','1');
			  $('#myCarousel'+container+' a.right.carousel-control').css('cursor','pointer');  
		  }
	});
	$('#myCarousel'+container+' a.right.carousel-control').on('click', '', function() {
		 var $this = $(this);
		 if($('#carousel'+container+'Id.carousel-inner div:nth-last-child(2)').hasClass('active')) { 
		        $this.css('pointer-events','none');
				$this.css('opacity','.07');
				$this.css('cursor','default');
		        $('#myCarousel'+container+' a.left.carousel-control').css('pointer-events','');
			    $('#myCarousel'+container+' a.left.carousel-control').css('cursor','pointer');
			    $('#myCarousel'+container+' a.left.carousel-control').css('opacity','1');
			  }else{
				  $('#myCarousel'+container+' a.left.carousel-control').css('pointer-events','');
				  $('#myCarousel'+container+' a.left.carousel-control').css('opacity','1');
				  $('#myCarousel'+container+' a.left.carousel-control').css('cursor','pointer');  
			  }
	});
}