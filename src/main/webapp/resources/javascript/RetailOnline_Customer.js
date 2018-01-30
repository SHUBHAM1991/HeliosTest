
/**
 * Lomesh Changes 
 */
var ctx =$("#svsURL").val();// "http://FRA52780:8095/ContractDasbhoard/";
var abandonedCartListVOArr=new Array();
var custRecommendationListVOArr=new Array();
var dotcomActivityVOArr=new Array();
var objPurchaseDetailsListVO=new Array();
var searchItemsListVOArr=new Array();
var abandonedStatus=0;
var recommStatus=0;
var dotcomStatus=0;
var pie;
var yearChange=0;
var latestDateforOrder=null;
var latestDateChart=null;
var latestDate='';
var latestDateForLead='';
var playSecData='';
var sidebarClick=false;
var SuperUserDeatilCount=-1;
var OrderDeatilCount=-1;
var ShipOrderDeatilCount=-1;
var ShipToDeatilCount=-1;
var iStart=0;
var retFound='N';
var ActiveUserDeatilCount=-1;
var catCodeMap=[];
var pieChartDataSet;
$.fn.dataTable.ext.errMode = 'none';
var chrt='';
//var heliosModule = angular.module('helApp',['ngAnimate']);
//var heliosModule;
 $(document).ready(function(){
	 
	 var height=($(window).height() - ($(window).height()/10));
		$(".msg_container_base").css("max-height",height);
		var onlineRetail = $("#onlineRetail").val();
	//	alert(onlineRetail)

	 showProgress();
	 var cval=$.cookie('sidebar_closed');
	 $("#loggedInUserNameSpan").html("You logged in as "+$("#loggedInUserName").val());
	 
	 if($(window).width()<=768){
		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;padding-right: 2%;");	 
	 }else{
		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;");	 
	 }
	 $(window).resize(function() { 
		 if(undefined != pieChartDataSet && '' !=pieChartDataSet){
			createPieChartData(pieChartDataSet);
		 }
		});
	 //getLayoutDetails();
	 //alert("Dev in Progress.....  :) Lomesh")
	 //alert(cval+"-"+$(window).width());
	 if(undefined !=cval && '' !=cval && cval==1 && $(window).width()>768){
		 $("#stplId").html("");
			//$("#notiId").html("");
			//$("#gId").html("");
			//$("#rId").html("");
			//$("#eId").html("");
			$('#ulMenu li').css("padding-left","0px");
			$('a[id^=menuItemIcon]').css("display","block");
			$('a[id^=menuItemId]').css("display","none");
			$("#cnoHead").removeClass("cnoExpand");
			$("#cnoHead").addClass("cnoCollapse");
			$("#logHead").removeClass("logExpand");
			$("#logHead").addClass("logCollapse");
			//$('#playSection').html("");
			$('#ulPlaySec').css("display","none");
			$('#sfdcId').css("display","none");
			//$("#tglId").css("padding-top","50px");
			$("#tglId").css("width","100%");
			$("#stplLogoId").css("display","none");
			//$(".canvasjs-chart-canvas").attr("style","width:100%;");
			collapsed='true';
	 }
		hideMMFeatures();
	 if(undefined !=$("#iStart").val() && $("#iStart").val()!=null && $("#iStart").val()!='' ){
		 iStart = $("#iStart").val(); 
	 }
	/* $(".page-top").click(function(){ alert(111)
		 var status=$(".page-quick-sidebar-toggler").css("display");
			if((undefined !=status) && (status=='block'))
			$('body').toggleClass('page-quick-sidebar-open');
	 });*/
		/*$("body").click(function(){
			var display=$("div.popover,fade,bottom,in").css("display");
			alert(display)
			if((undefined !=display) && ('' !=display) && ('block'==display)){
				$("#example").popover('hide');
			}
			//
		});*/
	/*$('body:not(.page-quick-sidebar-open)').click(function(){
		var eleClass=$(this).attr("class")
		var status=$(".page-quick-sidebar-toggler").css("display");
		if((undefined !=eleCalss) && ('' !=eleClass) & (eleClass.indexOf('.page-quick-sidebar-open') == -1)){
		if((undefined !=status) && (status=='block'))
		$('body').toggleClass('page-quick-sidebar-open');
		}
	});
	$(".active").click(function(e) {
	    e.stopPropagation();
	});*/
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
	 /*$('#dataTables-QuickSearch')
	    .on('preXhr.dt', function ( e, settings, data ) {
	    	$("#dataTables-QuickSearch tr th").hide(); 
	    	$("#iStart").val(data.iDisplayStart);
	    	
	    } );*/
	 $('#dataTables-QuickSearch')
	    .on( 'error.dt', function ( e, settings, techNote, message ) {
	    	//console.log( 'An error has been reported by DataTables: ', message );
	    	/*if(message.indexOf("Invalid JSON response") !=-1){
	    		$("#portlet-config").modal("show");
		    	setTimeout(function(){ $("#portlet-config").modal("hide"); }, 1000);
		    	$('#portlet-config').on('hidden.bs.modal', function (e) {
		    		window.location.reload();
		    		});
	    		
	    	}*/
	    	if(!(settings.jqXHR.getResponseHeader('Content-Type').indexOf('text/html')!=-1)){
		    	console.log( 'An error has been reported by DataTables: ', message );
		    }else{
		    	console.log('Session Logged Out');	
		    }

	    } )
	    .DataTable();
	
		$('#super-info-dialog').on('hidden.bs.modal', function () {
			
         })
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
	 jQuery.fn.dataTableExt.oSort['mystring-asc'] = function(x,y) {
		    var retVal;
		    x = $.trim(x);
		    y = $.trim(y);
		 
		    if (x==y) retVal= 0;
		    else if (x == "" || x == "&nbsp;" || x == "-") retVal=  1;
		    else if (y == "" || y == "&nbsp;"|| y== "-") retVal=  -1;
		    else if (x > y) retVal=  1;
		    else retVal = -1;  // <- this was missing in version 1
		 
		    return retVal;
		}
		jQuery.fn.dataTableExt.oSort['mystring-desc'] = function(y,x) {
		    var retVal;
		    x = $.trim(x);
		    y = $.trim(y);
		 
		    if (x==y) retVal= 0;  
		    else if (x == "" || x == "&nbsp;"|| x == "-") retVal=  -1;
		    else if (y == "" || y == "&nbsp;"|| y== "-") retVal=  1;
		    else if (x > y) retVal=  1;
		    else retVal = -1; // <- this was missing in version 1
		 
		    return retVal;
		 }
		
	 
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
 //getLatestFiscalDate();	
	 getLatestFiscalDateForLead();
 getLatestFiscalDateOrder(); 
 //getLatestOrderReturnedDate();
	// openSuperUserDetails();	
	 $( "#yrId").change(function() {
		// pie.destroy();
		// pie = null;
		 
		 yearChange=1;
		 populateDataOnLoad(latestDateForLead,'RETAILONLINE');
	 });
	 $( "#sortSel").change(function() {
		 var monthYear=$("#yearSel").val();
		 var catId=$("#sortSel").val();
		 onChangeMonthOrCat(monthYear,catId);
	 });
	 $( "#yearSel").change(function() {
		 var monthYear=$("#yearSel").val();
		 var catId=$("#sortSel").val();
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
			{"bSortable": true},
			{"bSortable": true},
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
	 $('#dataTables-superInfo').DataTable({
		 "dom": '<"pull-right"f>rt<"bottom"ip>',
			"lengthMenu": [[4, 10, 15, -1], [4, 10,15, "All"]],
			  "jqueryUI":true,
			  "oLanguage": { "sSearch": "Filter: "},
			  "order": [],
				"aoColumns": [{"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
				{"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
				{"bSortable": true, "sWidth": '33%',"sType" : "mystring" }
				
				]
		})
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
	$('#dataTables-exampleShipTo').dataTable({
		"dom": '<"pull-right"f>rt<"bottom"ip>',
		 "bSort": true,
			"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
			"order": [],
			"bProcessing": true,
			"oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true},
			{"bSortable": true}
			] 
	})	
	$('#scrollTop').click(function(){ 
	    $('html, body').animate({
	        scrollTop: $( $.attr(this, 'href') ).offset().top
	    }, 1200);
	    return false;
	});
	 $("#backButton").click(function(){
		 //clearCookie();
	 });

	 populateQuickSearchData();

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
	 
	 $("#benefitId").click(function(){ 
		   var cls=$("#faq-benefits").attr('class');
		    if(cls=='panel-collapse collapse in'){
		    	$("#benefitTxt").html("Show benefits comparison");
		   }
		    else if(cls=='panel-collapse collapse'){
		    	$("#benefitTxt").html("Hide benefits comparison");
		   } 
		}); 
 });

	 /*$('#yrId').change( function() {
 alert($('#yrId').val());
	 yearChange=1;
	});*/

$(function () {
	    $("[data-toggle='tooltip']").tooltip({html:true});
	    
	   
		// $("#example").html($("#valueAttributionScore").html())	;
});
	  function populateDataOnLoad(latestDateForLead,users){
		 if(latestDateForLead!=null && latestDateForLead!=undefined && yearChange==0){
			// $("#yrId").val(latestDate.substring(2,6));
			 $("#yrId option[value="+latestDateForLead.substring(2,6)+"]").attr("selected",true);
		 }
		 var chkBox='<label style="font-family: HelveticaNeueNormal; color: #0092db;">'
			+'<input type="checkbox" value="" id="retailOnlineChk" data-toggle="collapse" value="toggleRows" data-target=".ORrow">'
			+'<span class="cr" style=""> <i class="cr-icon fa fa-check"></i>'
			+'</span>See Retail and Staples.com Sales</label>';
		 
		 $("#divChkId").html(chkBox);
			 var formData={};
			 var custid=$("#reqCustNum").val();
			 var year=$("#yrId").val();
			 $("#monthTitle").html("");
			 $("#monthTitle").html('<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;"> MONTHLY SPEND ANALYSIS <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
			 //alert(ctx+"/getCustData/"+custid+"/"+year+"/"+users);
			 $.ajax({
				 	dataType: "json",
					url : ctx+"/getCustData/"+custid+"/"+year+"/"+users,
					//url : "/getCustData/"+custid+"/"+year,
					type : "POST",
					cache : false,
					data : JSON.stringify(formData),
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {
						var i=0;
						var cnt=0,retailCnt=0,onlineCnt=0;
						var mkttrHTML = "";
						 $('#tableId').html("");
									if(data.objSummaryVO.listYTDSpend !=null && data.objSummaryVO.listYTDSpend !=undefined){
										if(data.objCategoriesList !=null && data.objSummaryVO.listYTDSpend !=undefined){
										$.each(data.objCategoriesList, function(i, item) {
											catCodeMap[item.mktItmCatCd]=item.name;
										});
										for (var j in catCodeMap) {
					      		        	var key=j;
					      		        	//alert(key+"--"+catCodeMap[key]);
					      		           }
										}
										var retailArr=new Array();
										var onlineArr=new Array();
										$.each(data.objSummaryVO.listYTDSpendRetail, function(i, item) {
											retailArr[retailCnt]=item;
											//alert(retailArr[retailCnt].data[1]);
											retailCnt++;
										});
										$.each(data.objSummaryVO.listYTDSpendOnline, function(i, item) {
											onlineArr[onlineCnt]=item;
											//alert(onlineArr[onlineCnt].data[1]);
											onlineCnt++;
										});
										$.each(data.objSummaryVO.listYTDSpend, function(i, item) {
											mkttrHTML += '<tr class=""><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">' + capitalize(replaceChar(checkUundefined(item.data[0]))) + '</td><td style="padding-bottom:2px;padding-top:2px" class="datatablesTd">'
													
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[1])),retailArr[cnt].data[1],onlineArr[cnt].data[1],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[2])),retailArr[cnt].data[2],onlineArr[cnt].data[2],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[3])),retailArr[cnt].data[3],onlineArr[cnt].data[3],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[4])),retailArr[cnt].data[4],onlineArr[cnt].data[4],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[5])),retailArr[cnt].data[5],onlineArr[cnt].data[5],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[6])),retailArr[cnt].data[6],onlineArr[cnt].data[6],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[7])),retailArr[cnt].data[7],onlineArr[cnt].data[7],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[8])),retailArr[cnt].data[8],onlineArr[cnt].data[8],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[9])),retailArr[cnt].data[9],onlineArr[cnt].data[9],cnt,checkUundefined(item.data[10])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-bottom:2px;padding-top:2px">'
													+ getCellstructure(chkNegAmount(checkUundefined(item.data[10])),retailArr[cnt].data[10],onlineArr[cnt].data[10],cnt,checkUundefined(item.data[10])) + '</td></tr>';
											//alert(item.data[10]);
											cnt++;
										});
										mkttrHTML += '<tr class=""><td class="datatablesTd" style="color:#111 !important">Total</td><td class="datatablesTd formatCls" style="color:#111 !important;">'
										
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[0])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[1])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[2])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[3])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[4])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[5])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[6])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[7])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[8])) + '</td><td class="datatablesTd" style="color:#111 !important;padding-right:.5%;text-align:right;">'
										+ chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[9])) + '</td></tr>';
										//alert(chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[9])));
										var dataAvailChk=chkNegAmount(checkUundefined(data.objSummaryVO.listFooter[9]));
										if(undefined!= dataAvailChk && '$0.00'==dataAvailChk){
											$("#mtaContent").html("NO DATA FOUND FOR THIS CUSTOMER");
								    		$("#mtaContent").css({ "text-align":"center",
								    			"height":"300px",
								    			"padding-top":"20%",
								    			"font-weight":"700",
								    			"font-size":"20px",
								    			"color":"darkgrey"
								    		});
											$("#mnthContent").html("NO DATA FOUND FOR THIS CUSTOMER");
								    		$("#mnthContent").css({ "text-align":"center",
								    			
								    			"height":"350px",
								    			"padding-top":"20%",
								    			"font-weight":"700",
								    			"font-size":"20px",
								    			"color":"lightgrey"
								    		});
										}
									}
										
									$('#tableId').html(
											('<table class="table dataTable no-footer table-striped" id="dataTables-example1" style="border:1px solid #ccc !important">'.toString())
													 +'<thead><tr><th>Month</th>'
		                                            +'<th>Ink & Toner</th>'
		                                            +'<th>'+(checkUundefined(data.objSummaryVO.listHeader[2])).toString()+'</th>'
		                                            +'<th>Tech</th>'
		                                            +'<th>Mail & Ship</th>'
													+'<th>'+(checkUundefined(data.objSummaryVO.listHeader[5])).toString()+'</th>'
		                                            +'<th>'+(checkUundefined(data.objSummaryVO.listHeader[6])).toString()+'</th>'
		                                            +'<th>'+(checkUundefined(data.objSummaryVO.listHeader[7])).toString()+'</th>'
		                                            +'<th>'+(checkUundefined(data.objSummaryVO.listHeader[8])).toString()+'</th>'
		                                            +'<th>Print/Promo</th>'
		                                            +'<th>'+(checkUundefined(data.objSummaryVO.listHeader[10])).toString()+'</th>'
											//+ (header.toString()) +
													
													+ ('</tr></thead><tbody>'.toString()) + (mkttrHTML.toString())
													+ ('</tbody></table>'.toString()));
									var table=$('#dataTables-example1').dataTable({
										 
										"bSort": true,
										"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
									"order": [],
									"oLanguage": { "sSearch": "Filter: "},
									"paging": false,
									"searching": false,
							        "info":   false,
									"aoColumns": [{"bSortable": false},
									{"bSortable": true},
									{"bSortable": true},
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
									table.fnDraw();
									$("#dataTables-example1").removeClass("table-bordered");
									$("#custNum").html(" "+'<a style="pointer-events:none;cursor: default;font-family:Helvetica !important;color:#262626">'+data.listOfCustProfileVO[0].custNum+'<\a>');
									$("#lastContactedDateValueId").html(data.listOfCustProfileVO[0].lastContactedDate);
									if(data.listOfCustProfileVO[0].iamId != '-' && data.listOfCustProfileVO[0].iamId !=''){
										//data.listOfCustProfileVO[0].iamId='0013000000B6u5OAAR';
										$("#cnoId").html("");
										$("#logId").html("");
										var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
										var compNameUrl=sfdcBaseUrl+'/'+data.listOfCustProfileVO[0].iamId;
										$("#compNameCont").html('<a href="'+compNameUrl+'" onclick="generateLogs(\'compname\')"  oncontextmenu="generateLogs(\'compname\')" target="_blank"  id="compName" style="font-size: 14px; font-family: Helvetica; font-weight: bold; color: #0092db; line-height: 2;" class="letterSpaceOR"> '+data.listOfCustProfileVO[0].companyName+' </a>');
										//$("#ulIamIdSec").css("display","block");
										$("#sfdcId").css("display","block");
										/*var cnoUrl='https://na32.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%253FsrPos%253D0%2526srKp%253D001%26accid%'+data.listOfCustProfileVO[0].iamId;*/
										var cnoUrl='https://na32.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%26accid%3D'+data.listOfCustProfileVO[0].iamId;
										var cnoHtml='<a href="'+cnoUrl+'" onclick="generateLogs(\'createopp\')"  oncontextmenu="generateLogs(\'createopp\')" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;" target="_blank">Create New Opp.</a>'+
										' <a href="'+cnoUrl+'" onclick="generateLogs(\'createopp\')"  oncontextmenu="generateLogs(\'createopp\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 19px;margin-right: -8px;float:right;" id="cnoIconId" class="fa fa-hand-o-up"></i></a>';
										$("#cnoId").html(cnoHtml);
										$("#menuItemIcon7").attr("href",cnoUrl);
										var logUrl='https://na32.salesforce.com/00T/e?what_id='+data.listOfCustProfileVO[0].iamId;
										var logHtml='<a href="'+logUrl+'" onclick="generateLogs(\'logtask\')"  oncontextmenu="generateLogs(\'logtask\')" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;" target="_blank">Log A Task</a>'+
										   ' <a href="'+logUrl+'" onclick="generateLogs(\'logtask\')"  oncontextmenu="generateLogs(\'logtask\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 18px;margin-right: -8px;float:right;" id="logIconId" class="fa fa-file"></i></a>';
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
										  $("#cnoHead").addClass("cnoCollapse");
										  $("#cnoHead").removeClass("cnoExpand");
										  $("#logHead").addClass("logCollapse");
										  $("#logHead").removeClass("logExpand");
										  $("#sfdcId").css("display","none");
										}
									}else{
										$("#compName").html(data.listOfCustProfileVO[0].companyName);
										$("#ulIamIdSec").css("display","none");
										$("#cnoId").html("");
										$("#logId").html("");
										$("#sfdcId").css("display","none");
									}
									var custConNumHtml='<span onclick="javascript:logChangeSatusActivity(8089,\''+formatPhone(data.listOfCustProfileVO[0].phoneNum)+': clicked in Leads Main Dashboard\','+custid+')">'+" "+formatPhone(data.listOfCustProfileVO[0].phoneNum)+'</span>';
									//$("#custConNum").html(" "+formatPhone(data.listOfCustProfileVO[0].phoneNum));
									$("#custConNum").html(custConNumHtml);
									$("#custType").html(" "+data.listOfCustProfileVO[0].custType);
									
									var contactPerson =generateContactPerson(data.listOfCustProfileVO[0].firstName,data.listOfCustProfileVO[0].emailId);
									$("#custContactDetail").html(contactPerson);
									
							        //LTS saving changes
							        if (data.listOfCustProfileVO[0].lts != undefined &&
							        		data.listOfCustProfileVO[0].lts != null &&
							        		data.listOfCustProfileVO[0].lts != '') {
							        	$("#ltsInfoColumn").show();
							        	$("#ltsInfo").html("$"+formatOrderAmt(Math.round(data.listOfCustProfileVO[0].lts)));
							        } else {
							        	$("#ltsInfoColumn").hide();
							        }
							        
							        var address='';
							        var cityStateZip='';
							        if(undefined != data.listOfCustProfileVO[0].companyAddr1 && '' != data.listOfCustProfileVO[0].companyAddr1 && (data.listOfCustProfileVO[0].companyAddr1.length > 1)){
							          	address=data.listOfCustProfileVO[0].companyAddr1+" "+data.listOfCustProfileVO[0].companyAddr2;
							          	if(address!=' ' && address !='  ')
							          	address=address+"</br>";
							          	if(data.listOfCustProfileVO[0].companyCity !=''){
							          		if(undefined == data.listOfCustProfileVO[0].companyAddr2 || ''==data.listOfCustProfileVO[0].companyAddr2)
							          		cityStateZip=data.listOfCustProfileVO[0].companyCity;
							          		if(cityStateZip!='' && cityStateZip!=' ')
							          			cityStateZip=cityStateZip+", "+data.listOfCustProfileVO[0].companyState+" "+formatZip(data.listOfCustProfileVO[0].companyZip);
							          		
							          			cityStateZip=cityStateZip+"";
							          		address=address+cityStateZip;
								        }
							          	if(undefined !=address && ''!=address)
							          		address=address.toUpperCase();
							          	$("#custAddDtl").html(address);
							        }else{
							        	$("#custAddDtl").html("ADDRESS NOT FOUND");
							        }
							        generateSegmentDetails(data.listOfCustProfileVO[0].custType);
							        
							        
							        
							        //$("#custAdd2").html(data.listOfCustProfileVO[0].companyState+" , "+data.listOfCustProfileVO[0].companyZip);
							        //$("#custAdd2").html(data.listOfCustProfileVO[0].companyAddr2);
							        //$("#custCity").html(data.listOfCustProfileVO[0].companyCity);
							        //$("#custState").html(data.listOfCustProfileVO[0].companyState);
							        //$("#custCountry").html(data.listOfCustProfileVO[0].companyCountry);
							        //$("#custZip").html(data.listOfCustProfileVO[0].companyZip);
							
								    $('#dataTables-example1').removeClass('display').addClass('table table-striped');
								    $('.dataTables_filter input[type="search"]').attr('placeholder','Search').css({'width':'250px','display':'inline-block'});
								    var managerHtml="";
								    var cntMgr=0; 
								    var itemCnt=1;
								    $.each(data.listOfCustProfileVO, function(i, item) {
								    	managerHtml=managerHtml+"<div id='dtl"+itemCnt+"'>"
								    	if(cntMgr>0){
								    		//managerHtml=managerHtml+'<hr style="margin-top:1%;">';
								    	}
								    	
								    	if(item.repCd == 'DD' && undefined != data.listOfCustProfileVO &&  null != data.listOfCustProfileVO && data.listOfCustProfileVO.length == 2){
								    		managerHtml=managerHtml+'<div style="height:auto" class=""><span id="actMgrName" class="letterSpaceOR" style="font-family:Helvetica;font-weight:bold;font-size:14px;color:#262626;" class="large" >Account Consultant: </span><span id="mgrName" style="font-family:arialmt !important;font-size:13px;color:#262626;word-break:break-all;" class="letterSpaceOR">'+item.agentName+'  </span></div>';
								    		$("#loggedInUserName").html(item.agentName);
								    	}
								    	else if(item.repCd == 'DD'){
								    		managerHtml=managerHtml+'<div style="height:auto" class=""><span id="actMgrName" class="letterSpaceOR" style="font-family:Helvetica;font-weight:bold;font-size:14px;color:#262626;" class="large" >Account Manager: </span><span id="mgrName" style="font-family:arialmt !important;font-size:13px;color:#262626;word-break:break-all;" class="letterSpaceOR">'+item.agentName+'  </span></div>';
								    		$("#loggedInUserName").html(item.agentName);
								    	}
								    
								    	else if (item.repCd == 'IAC'){
								    		managerHtml=managerHtml+'<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family:Helvetica;font-weight:bold;font-size:14px;color:#262626;" class="large">Inside Account Consultant: </span><span id="mgrNameIAC" style="font-family:font-family:arialmt !important;font-size:13px;color:#262626;word-break:break-all;" class="letterSpaceOR">'+item.agentName+'  </span></div>';
								    		$("#loggedInUserName").html(item.agentName);
								    	}
								    							    	
								    	managerHtml=managerHtml+'<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family:Helvetica;font-weight:bold;font-size:14px;color:#262626;" class="large">Email: </span> <span style="font-family:arialmt !important;font-size:13px;color:#262626;word-break:break-all;" id="mgrEmailId" class="letterSpaceOR">'+item.agentEmailId+'</span></div>'
								    	managerHtml=managerHtml+'<div style="height:auto" class=""><span class="letterSpaceOR" style="font-family:Helvetica;font-weight:bold;font-size:14px;color:#262626;" class="large">Phone Number: </span><span style="font-family:arialmt !important;font-size:13px;color:#262626;word-break:break-all;" id="mgrPhoneNum" class="letterSpaceOR" onclick="javascript:logChangeSatusActivity(8092,\''+formatPhone(item.agentPhoneNum)+': clicked in the Account Manager Grid Leads Main Dashboard\','+custid+')">'+formatPhone(item.agentPhoneNum)+'</span></div><span style="display:none;font-family:arialmt;" id="repVal">'+item.repCd+'</span></div>'
								    	
								    	cntMgr++;
								    	itemCnt++;
									});
								    if(undefined != data.listOfCustProfileVO &&  null != data.listOfCustProfileVO && data.listOfCustProfileVO.length == 2){
								    	$("#managerInfo").css("width","100%");
								    	var h=$("#amContent").css("height");
								    	
								    	//alert("-->"+h);
								    	$("#amContent").css("height","115px");
								    	$("#AccountMgrId").html("Account Consultant");
								    	managerHtml=managerHtml+'<p><a class="btn" id="viewRepBtn" href="javascript:showHideAcIac();" style="padding: 2px;background-color: gainsboro;font-family: Helvetica;font-weight: bold;text-decoration: none;letter-spacing: .5px;font-size: 12px;margin-top: 2%;color: #555;border-radius: 2px !important;"></a></p>';
								    }else{
								    	$("#catContent").attr("style","height:460px !important");
								    }
								    
								    //alert(managerHtml)
								    $("#managerInfo").html(managerHtml);
								    $("#dtl2").css("display","none");
								    $("#viewRepBtn").html("View "+$("#dtl2").find("#repVal").html()+" Details &raquo;");
									/*$("#mgrName").html(data.listOfCustProfileVO[0].agentName);
									$("#mgrPhoneNum").html(data.listOfCustProfileVO[0].agentPhoneNum);
									$("#mgrEmailId").html(data.listOfCustProfileVO[0].agentEmailId);
									if(data.listOfCustProfileVO.length == 2){
										$("#mgrNameIAC").html(data.listOfCustProfileVO[1].agentName);
										$("#mgrPhoneNumIAC")(data.listOfCustProfileVO[1].agentPhoneNum);
										$("#mgrEmailIdIAC")(data.listOfCustProfileVO[1].agentEmailId);
									}*/
								    
									var i=0;
									var dt = geDataRefreshTime('MV_SALES_SUMMARY');
									//alert(dt);
									$("#updateDateValueYTDSum span").html(dt+" ET");
									$.each(data.ytdInfoVOList, function(i, item) {
										if(i==0)
										$("#year"+i).html(item.year+' Year To Date Gross Sales<b>:</b>');
										else
										$("#year"+i).html(item.year+' Gross Sales<b>:</b>');	
										$("#spend"+i).html(currencyFormat(item.ytdSales));
										i++;
									});
									formatYtd();
								    populateCategoryChart(data);
									//populateMonthAnalysisChart(data);
								    if(latestDateForLead!=null && latestDateForLead!=undefined ){
								    latestFiscalYear=latestDateForLead.substring(2,6);
								    }
								    $("#retailOnlineChk").bind('click',checkuncheckRetailOnline);
								    getSavingsInfo();
								    getCatPenInfo();
								    //fetchSbaDiffDetails(custid);
								    //generateSecondYearChartData(data,year,latestFiscalYear);
								    //generateSecondYearChartData(data);
								    //populateMonthChartData(data)
								    populateMonthChartDataSam(data,year);
						}

				})	 
			  
	  }
	  function generateSecondYearChartData(firstYearData,selYear,latestFiscalYear){
		  var secondYear=selYear-1;
		  var thirdYear=secondYear-1;
		  var years=new Array();
		  if(selYear==latestFiscalYear){
			secondYear=selYear-1;
			thirdYear=secondYear-1;
			
		  }else if(selYear== (latestFiscalYear-1)){
			  secondYear=latestFiscalYear;
				thirdYear=secondYear-2;
		  }else if(selYear== (latestFiscalYear-2)){
			  secondYear=latestFiscalYear-1;
				thirdYear=latestFiscalYear; 
		  }
		  years=[selYear,secondYear,thirdYear];
		  var formData={};
			 var custid=$("#reqCustNum").val();
			 var year=secondYear;
			 //alert(year)
			 $.ajax({
				 dataType: "json",
				// getOtherYearData/{cid}/{selYear}
					url : ctx+"/getOtherYearData/"+custid+"/"+year,
					//url : "/getCustData/"+custid+"/"+year,
					type : "POST",
					cache : false,
					data : JSON.stringify(formData),
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {
						//alert(data)
						//alert(data.barDataSet)
						generateThirdYearChartData(firstYearData,data,years)
						}
					});
	  
		  
	  }
	  function generateThirdYearChartData(firstYearData,secondYearData,years){
		  var formData={};
			 var custid=$("#reqCustNum").val();
			 var year=years[2];
			 //alert(year)
			 $.ajax({
				 	dataType: "json",
					url : ctx+"/getOtherYearData/"+custid+"/"+year,
					//url : "/getCustData/"+custid+"/"+year,
					type : "POST",
					cache : false,
					data : JSON.stringify(formData),
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {
						var thirdYearData=data;
						
						populateMonthChartData(firstYearData,secondYearData,thirdYearData,years);
						//hideProgress();
						}
					});
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
    function populateCategoryChart(data){
    	$("#chartContainerPie").css("padding-top","0%");
    	$("#chartContainerPie").css("font-weight","500");
    	$("#chartContainerPie").css("font-family","Helvetica");
    	$("#chartContainerPie").css("font-size","13px");
    	$("#chartContainerPie").css("color","#555");
    	$("#chartContainerPie").html("");
    	$("#catTitle").html("");
    	var yr=$("#yrId").val();
    	$("#catTitle").html('<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;">'
    			+' YEARLY SPEND PER CATEGORY <i style="font-size:18px !important;line-height:12px; !important;color:#4d4d4d;" class="fa fa-angle-double-right"></i></span>');
    	if(data != undefined && data != null && undefined !=data.pieDataset && null !=data.pieDataset){
    	var keyArr=new Array();
		var valArr=new Array();
		var valArrRetail=new Array();
		var valArrOnline=new Array();
		
		var valPercentArr=new Array();
		var valPercentArrRetail=new Array();
		var valPercentArrOnline=new Array();
		var piedata = [];
		 keyArr=data.pieDataset.data.keys;
		 
		 valArr=data.pieDataset.data.values;
		 valArrRetail=data.pieDatasetRetail.data.values;
		 valArrOnline=data.pieDatasetOnline.data.values;
		 var sum=0,sumRetail=0,sumOnline=0;
		 for(var count=0;count<valArr.length;count++){
			 sum=sum + parseFloat(valArr[count]);
			 sumRetail=sumRetail + parseFloat(valArrRetail[count]);
			 sumOnline=sumOnline + parseFloat(valArrOnline[count]);
		 }
		 //alert(sum+"--"+sumRetail+"--"+sumOnline);
		 for(var count=0;count<valArr.length;count++){
			 //alert("Total ="+valArr[count] +"--Retail ="+valArrRetail[count].toFixed(2)+"--Online ="+valArrOnline[count].toFixed(2));
			 valPercentArr[count]=((valArr[count])*100)/sum;
			/* valPercentArrRetail[count]=((valArrRetail[count].toFixed(2))*(100))/sum;
			 valPercentArrOnline[count]=((valArrOnline[count].toFixed(2))*(100))/sum;*/
			 valPercentArrRetail[count]=((valArrRetail[count].toFixed(2))*(100))/valArr[count];
			 valPercentArrOnline[count]=((valArrOnline[count].toFixed(2))*(100))/valArr[count];
		 }
		 //alert(keyArr+"-"+valArr+"-"+valPercentArr+"-"+data.pieDataset+"-"+data.pieDataset.data+"--"+data.pieDataset.data.keys);
		 if(undefined !=keyArr && '' !=keyArr){
		  piedata=getLableValueMap(keyArr,valArr,valPercentArr,valPercentArrRetail,valPercentArrOnline);
		  createPieChartData(piedata);	
		 }else{
			 $("#chartContainerPie").html("NO SALES FOUND FOR THIS YEAR")
	    		$("#chartContainerPie").css("text-align","center");
	    		$("#chartContainerPie").css("padding-top","40%");
	    		$("#chartContainerPie").css("font-weight","700");
	    		$("#chartContainerPie").css("font-size","20px");
	    		$("#chartContainerPie").css("color","lightgrey");
		 }
		
    	}
    	else{ 
    		$("#chartContainerPie").html("NO SALES FOUND FOR THIS YEAR")
    		$("#chartContainerPie").css("text-align","center");
    		$("#chartContainerPie").css("padding-top","40%");
    		$("#chartContainerPie").css("font-weight","700");
    		$("#chartContainerPie").css("font-size","20px");
    		$("#chartContainerPie").css("color","lightgrey");
    	}
    }
    var myLabelFormatter = function (context) {
        var label = context.label;
        if (context.section === 'outer') {
            if (context.value === 1) {
                label = label + '!';
            }
        }
        return label;
    };
    
    function createPieChartData(pieData){
    	
    	var chart1 = new CanvasJS.Chart("chartContainerPie",
    			{
    		       /* height: 450,*/
    				title:{
    					theme: "theme2"
    					/*text: "YTD spend/category - 2016",
    					fontSize: 16,*/
    				},
    				legend: {
    					//horizontalAlign: "right",
    			        //verticalAlign: "center",
    			        fontFamily:"Helvetica",
    			        fontSize:13,
    			        fontWeight:"bolder",
    			        fontColor:"#555",
    					maxWidth: 400,
    					itemWidth: 188
    				},
    				toolTip: {
    			      fontSize: 13,
    			      fontStyle: "normal",
    			      fontFamily:"Helvetica",
  			          fontSize:13,
  			          fontWeight:"bolder",
  			          fontColor:"#555",
    			      content: "{seriesText}"		
    				},
    				data: [
    				{
    					
    					type: "pie",
    					showInLegend: true,
    					legendText: "{legendText}",
    					 legendMarkerType: "square",
    					 
    					dataPoints: pieData
    				}
    				]
    			});
    			chart1.render();
    			//chart1.remove();
    			chrt=chart1;
    			/*var chart2 = new CanvasJS.Chart("chartContainerPie2",
    	    			{
    				        height: 450,
    	    				title:{
    	    					theme: "theme2",
    	    					text: "YTD spend/category - 2015",
    	    					fontSize: 16,
    	    				},
    	    				legend: {
    	    			        fontFamily:"Helvetica",
    	    			        fontSize:13,
    	    			        fontWeight:"bolder",
    	    			        fontColor:"#555",
    	    					maxWidth: 400,
    	    					itemWidth: 188
    	    				},
    	    				toolTip: {
    	    			      fontSize: 13,
    	    			      fontStyle: "normal",
    	    			      fontFamily:"Helvetica",
    	  			          fontSize:13,
    	  			          fontWeight:"bolder",
    	  			          fontColor:"#555",
    	    			      content: "{seriesText}"		
    	    				},
    	    				data: [
    	    				{
    	    					
    	    					type: "pie",
    	    					showInLegend: true,
    	    					legendText: "{legendText}",
    	    					 legendMarkerType: "square",
    	    					 
    	    					dataPoints: pieData
    	    				}
    	    				]
    	    			});
    	    			chart2.render();*/
    			$("a.canvasjs-chart-credit").css("display","none");
    			
    			/*$("#chartContainerPie").click(function(){

    				$("#chartContainerPie").css("z-index","2");
    				$("#chartContainerPie2").css("z-index","1");
    				});
    				$("#chartContainerPie2").click(function(){

    				$("#chartContainerPie2").css("z-index","2");
    				$("#chartContainerPie").css("z-index","1");
    				});*/
    			pieChartDataSet=pieData;
    }
    function createPieChart(piedata){
    	pie = new d3pie("pie", {
			header: {
			    //text: "Nice birds I saw this morning",
				},
					size: {
						pieOuterRadius: "78%",
						canvasHeight: 285
					},
					labels: {
						 formatter: myLabelFormatter,
						 fontFamily:"Helvetica",
						 
					inner: {
						format: "percentage"
					},
					value: {
						color: "#fff"
					}
				},
					data: {
						sortOrder: "value-asc",
						
						content: piedata
					},
					 tooltips: {
						    enabled: true,
						    type: "placeholder",
						    string: "{label}, about = {percentage}%",
						    styles: {
						    	fadeInSpeed: 500,
						    	
						        backgroundColor: "yellow",
						        backgroundOpacity: 0.8,
						        color: "#ffffcc",
						        borderRadius: 4,
						        font: "verdana"
						        
						      }
						  },
					misc: {
						colors: {
							segments: [
								"#ffc127", "#10acaf", "#943e96", "#63d618", "#d8e523", "#d9524f"
							]
						}
					}
				});
    	/*segments: [
					"darkseagreen", "#ed701b", "#d8e523", "#0092db", "#10acaf", "#d9524f"
				]*/
    }
    
  function getLableValueMap(keyArr,valArr,valPercentArr,valPercentArrRetail,valPercentArrOnline){
	 
	  var labels = keyArr;
		var values = valArr;
		var valuesPercent = valPercentArr;
		//var colorsArr=["#ffc127", "#10acaf", "#943e96", "#63d618", "#d8e523", "#d9524f","darkseagreen","#377ab7","darksalmon","dimgrey"];
		var colorsArr=['#52C5D0','#FF9A2C','#FCD78D','#9CE2CF','#2D435E',' #DD1D1D','#FFC127','#F96919','#159B9B','#13706D'];
		var count=-1;
		return labels.map(function(label){
		   var labelcolor=getColor(label.trim());
		   count++;
		   if(undefined !=label && label.indexOf("and")!=-1)
			label = label.replace("and", "&");
			return { y: values[count] ,seriesText:""+label+"  "+(valuesPercent[count].toFixed(2))+"%</br>Retail: "+(valPercentArrRetail[count].toFixed(2))+"%</br>Staples.com: "+(valPercentArrOnline[count].toFixed(2))+"%" ,legendText:"  - "+label.trim()+"  "+(valuesPercent[count].toFixed(2))+"%" , indexLable: false , color:labelcolor}
		});
  }
  function getMonthAmountMap(monthArr,amntArr){
		 
	  var labels = amntArr;
		var values = monthArr;
		var count=-1;
		return labels.map(function(amt){
		   count++;
			return { y: parseFloat(amt), label: values[count] }
		});
  }
  function getMonthAmountMap1(monthArr,amntArr){
	  var monthArr=["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"];
	  //var colorArr=['#FF0F00','#FF6600','#0D52D1','#FF9E01','#2A0CD0','#FCD202','#CD0D74','#754DEB','#333333','#04D215','#0D8ECF','#8A0CCF'];
	  var colorArr=['#FF0F00','#FF9E01','#0D8ECF','#FCD202','#CD0D74','#754DEB','#04D215','#0D8ECF','#2A0CD0','#8A0CCF','#CD0D74','#754DEB'];
	  var labels = amntArr;
		var values = monthArr;
		var count=-1;
		return labels.map(function(amt){
			
		   count++;
			return { "month": monthArr[count], "amount": parseFloat(amt) ,"color":colorArr[count]}
		  // return { "Country": "mnth","Visits":"888","color":"#008899" }
		});
  }
  function getMonthAmountMapForThreeYears(monthArr,firstYearAmnt,secondYearAmnt,thirdYearAmnt){
	  /* var monthArr=["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"]; */
	  var monthArr=["FP 01","FP 02","FP 03","FP 04","FP 05","FP 06","FP 07","FP 08","FP 09","FP 10","FP 11","FP 12"]; // Fiscal Month
	  //var colorArr=['#FF0F00','#FF6600','#0D52D1','#FF9E01','#2A0CD0','#FCD202','#CD0D74','#754DEB','#333333','#04D215','#0D8ECF','#8A0CCF'];
	  var colorArr=['#FF0F00','#FF9E01','#0D8ECF','#FCD202','#CD0D74','#754DEB','#04D215','#0D8ECF','#2A0CD0','#8A0CCF','#CD0D74','#754DEB'];
	  var labels = firstYearAmnt;
		var values = monthArr;
		var count=-1;
		return labels.map(function(amt){
			
		   count++;
		   /*return { "month": monthArr[count], "year1": parseFloat(amt) ,"year1": parseFloat(amt) ,"year1": parseFloat(amt) ,"color":colorArr[count]}*/
			return { "month": monthArr[count], "year1": parseFloat(amt) ,"year2": parseFloat(secondYearAmnt[count]) ,"year3": parseFloat(thirdYearAmnt[count]) ,"color":colorArr[count]}
		  // return { "Country": "mnth","Visits":"888","color":"#008899" }
		});
  }
  function getMonthAmountMapForSam(monthArr,firstYearAmnt,retailAmnt,onlineAmnt){
	  /* var monthArr=["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"]; */
	  //var monthArr=["FP 01","FP 02","FP 03","FP 04","FP 05","FP 06","FP 07","FP 08","FP 09","FP 10","FP 11","FP 12"]; // Fiscal Month
	  //var colorArr=['#FF0F00','#FF6600','#0D52D1','#FF9E01','#2A0CD0','#FCD202','#CD0D74','#754DEB','#333333','#04D215','#0D8ECF','#8A0CCF'];
	  var colorArr=['#FF0F00','#FF9E01','#0D8ECF','#FCD202','#CD0D74','#754DEB','#04D215','#0D8ECF','#2A0CD0','#8A0CCF','#CD0D74','#754DEB'];
	  var labels = firstYearAmnt;
		var values = monthArr;
		var count=-1;
		return labels.map(function(amt){
			
		   count++;
		   /*return { "month": monthArr[count], "year1": parseFloat(amt) ,"year1": parseFloat(amt) ,"year1": parseFloat(amt) ,"color":colorArr[count]}*/
			return { "month": capitalize(replaceChar(monthArr[count])), "year1": parseFloat(amt),"retailSale":retailAmnt[count],"onlineSale":onlineAmnt[count]  ,"color":colorArr[count]}
		  // return { "Country": "mnth","Visits":"888","color":"#008899" }
		});
  }
 function populateMonthAnalysisChart(jdata){
	 var amntArr=new Array();
		var monthArr=new Array();
		var valPercentArr=new Array();
		var bardata = [];
		monthArr=((jdata.barDataSet).data).rowKeys;
		
		 var i=0;
		$.each(((jdata.barDataSet).data).rows, function(i, item) {
					amntArr[i]=item.values;
					i++;
		});
		bardata=getMonthAmountMap(monthArr,amntArr);
	    var chart = new CanvasJS.Chart("chartContainer",
			    {
		         //backgroundColor:dimgray,
			      title:{
			        //text: "Year-To-Date Spend Per Month" , 
					fontSize: 15
			      },
			      animationEnabled: true,
			      axisY: {
		    	  labelFormatter: function(e){
						return  "$" + e.value;
					},
			       //title: "Spend In Dollors",
					titleFontSize:"13",
			       	titleFontColor: "#555",
					labelFontWeight: "bolder",
					labelFontColor:"#555",
					labelFontFamily: "Helvetica",
			         labelFontSize: 14
			         //labelAngle:-45, 
			      },
				  axisX: {
			        //title: "Month From (Jan-Dec)",
			        titleFontSize:"13",
			       	titleFontColor: "#555",
			       	labelFontWeight: "bolder",
			        labelFontColor:"#555",
			        labelFontFamily: "Helvetica",
			         labelFontSize: 14,
			        //labelAngle:-45,
					interval: 1
			        //labelAngle:-90
			      },
			      legend: {
			        verticalAlign: "bottom",
			        horizontalAlign: "center"
			      },
			      theme: "theme1",
			      toolTip: {
    			      fontSize: 13,
    			      fontStyle: "normal",
    			      fontFamily:"Helvetica",
  			          fontSize:13,
  			          fontWeight:"bolder",
  			          fontColor:"#555"
    				},
    				
			      data: [
			      {        
			        type: "stackedColumn",
			        fillOpacity: 6,
			        /*indexLabelFontSize: 14,
			        indexLabel:"${y}",
			        indexLabelFontColor:"#ef3f3f",
			        indexLabelFontFamily: "Helvetica",
			        indexLabelFontWeight: "bold",
			        indexLabelPlacement: "outside",
			        indexLabelLineDashType: "dash",*/
			        //showInLegend: true, 
			        legendMarkerColor: "black",
			       
			        //legendText: "MMbbl = one million barrels",
			        dataPoints: bardata
			      }   
			      ]
			    });
			    chart.render();
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
  function openSuperUserDetails(){
	  // log user activity; view user details
	  logUserActivity(2002, 'View User Details');
	  
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
	  $("#dataTables-example_processing").html('<div id="example_processing_user" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-example_processing").css("display","block");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getSuperUSerHighLevelData/"+custid,
			 //url : "/getSuperUSerHighLevelData/"+custid,
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
					 $("#addContactId").css("display","none");
					 var name='';
								if(data.superUserInfoVList !=null && data.superUserInfoVList !=undefined){
									$.each(data.superUserInfoVList, function(i, item) {
										abandonedCartListVOArr[cnt]=item.abandonedCartListVO;
										custRecommendationListVOArr[cnt]=item.custRecommendationListVO;
										dotcomActivityVOArr[cnt]=item.dotcomActivityVO;
										
										searchItemsListVOArr[cnt]=item.searchItemsListVO;
							              name=item.orderContact;
							              var contactEmail = item.contactEmail;
							              var contactPhone = item.contactPhone;
							              
							              var hasContactDetails =  ((contactEmail != undefined && contactEmail != null && contactEmail != '') ||
							            		  (contactPhone != undefined && contactPhone != null && contactPhone != '')) ;
							            	  
							              var contactDetails=null;
							              if (hasContactDetails) {
							            	  contactDetails = '<div style="" id="orderContact' + cnt +'" class="toolTip">'+
							              						'<div style="font-size:13px;padding-top:5px;padding-left:5px;padding-right:10px;color:crimson;letter-spacing:1px;" id="orderStat" >'+item.orderContact+'</div>'+
							              						'<div style="padding-left:5px;letter-spacing:1px;" >Email: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;text-decoration:underline;cursor:pointer;" onclick="javascript:openOutlook(\''+contactEmail+'\');">'+ checkUundefined(contactEmail) + '</span></div>'+
							              						'<div style="padding-left:5px;letter-spacing:1px;">Phone: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;">'+ contactPhone + '</span></div>'+
							              						'</div>';
							              } else {
							            	  contactDetails = '<div  id="orderContact' + cnt +'" class="toolTip">'+
			              						'<div style="font-size:13px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;letter-spacing:1px;" id="orderStat" >'+item.orderContact+'</div>'+
			              						'<div style="font-size:13px;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">No Contact Information is Available</div>'+
			              						'</div>';
							              }
							              
							              var abandoned=checkNull(item.abandonedCartListVO);
							              var dotcom=checkNull(item.dotcomActivityVO);
							              var saDotComData='<td></td>';
							              var StaplesDotComData='<td></td>'
							            	var name= name.replace('(','openbracket');
							              var name= name.replace(')','closebracket');
							              var name= name.replace("'","quote");
							              if(checkNull(item.abandonedCartListVO) !=undefined && checkNull(item.abandonedCartListVO) !='' && checkNull(item.abandonedCartListVO) !='Y' && checkNull(item.abandonedCartListVO) !='y') {
							            	  var serachlistItem=$.each(searchItemsListVOArr[cnt], function(i, item) {
							            		          return item.searchItems;
							            		   });
							            	  if(serachlistItem == '' || serachlistItem == null)
							            		  saDotComData ='<td class="datatablesTd">'+ checkNull(item.abandonedCartListVO) +'</td>'
							            	  else
							            		  saDotComData ='<td class="datatablesTd"><a href="javascript:openSuperUserModalInfo(\''+name+'\','+cnt+',\'abandoned\',\''+abandoned+'\',\''+dotcom+'\')" style="text-decoration:underline;">Y</a></td>'
							              }
										  else
											  saDotComData ='<td class="datatablesTd"><a href="javascript:openSuperUserModalInfo(\''+name+'\','+cnt+',\'abandoned\',\''+abandoned+'\',\''+dotcom+'\')" style="text-decoration:underline;">'+ checkNull(item.abandonedCartListVO) +'</a></td>'
							              if(checkNull(item.dotcomActivityVO) !=undefined && checkNull(item.dotcomActivityVO) !='' && checkNull(item.dotcomActivityVO) !='Y' && checkNull(item.dotcomActivityVO) !='y')
							            	  StaplesDotComData   ='<td class="datatablesTd">'+ checkNull(item.dotcomActivityVO) +'</td>'
										  else
											  StaplesDotComData ='<td class="datatablesTd"><a href="javascript:openSuperUserModalInfo(\''+name+'\','+cnt+',\'dotcom\',\''+abandoned+'\',\''+dotcom+'\')" style="text-decoration:underline;">'+ checkNull(item.dotcomActivityVO) +'</a></td>'
										mkttrHTML += '<tr class="odd gradeX">'
											//+'<td>'
											/*+'<img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openSuperUserInfo(\''+name+'\','+cnt+')">'*/
											//+'<img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openSuperUserModalInfo(\''+name+'\','+cnt+')">'
											//+'</td>'
											//+'<td class="datatablesTd" onmouseover="javascript:displayOrderContactTooltip('+cnt+')" onmouseout="javascript:hideOrderContactTooltip('+cnt+')">'
											+'<td class="datatablesTd">';
							              if (hasContactDetails) {
							            	  mkttrHTML +='<a id="orderCon'+cnt+'" style="padding-right:8px;text-decoration:none;" '
											+'tabindex="0" class="" role="button" data-toggle="popover" data-trigger="focus" >'
											 + checkUundefined(item.orderContact) +contactDetails  +'</a>';
							              }else{
							            	  mkttrHTML +='<span id="" style="color:#444444;padding-right:8px;text-decoration:none;cursor:default;" '
												+'tabindex="0" class="" >'
												 + checkUundefined(item.orderContact) +contactDetails  +'</span>';
							              }
							              //if (hasContactDetails) {
												/*mkttrHTML += '<i id="activeDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="focus"></i>'
												+'<div style="" id="activedivId'+cnt+'" class="toolTip"><a href="https://na32.salesforce.com/003/e?accid=1&ent=Contact" target="_blank" style="font-size:13px;padding-top:5px;padding-left:5px;padding-bottom:5px;padding-right:10px;color:crimson;letter-spacing:1px;"><u><b>Add new contact to SFDC</b></u>  </a>'
												 
												mkttrHTML += '</div>';*/
							            	 // $("#addContactId").css("display","block");
							            	  //var sfdcUrl='https://na32.salesforce.com/003/e?accid=1&ent=Contact';
							            	  //$("#addContactId").attr("href","javascript:openUrlSfdc('"+sfdcUrl+"','users')");
											//}											 
							              if(undefined != item.iamId && '-' != item.iamId && '' !=item.iamId){
							            	 $("#addContactId").css("display","block");
							            	 var sfdcUrl='https://na32.salesforce.com/003/e?accid='+item.iamId+'&ent=Contact';
							            	 $("#addContactId").attr("href","javascript:openUrlSfdc('"+sfdcUrl+"','users')");
							              }
											mkttrHTML += '</td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.numOrdersCurr) +'</td>'
											 +'<td class="datatablesTd">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.totalSalesCurr))) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.numVisits) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.visitDate) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.numOrdersMon) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.numOrders) +'</td>'
											 +'<td class="datatablesTd">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.totalSales))) +'</td>'
											 +saDotComData
											 +StaplesDotComData
											 //+'<td class="datatablesTd">'+ checkNull(item.dotcomActivityVO) +'</td>'
											 +'</tr>';
										
										 cnt++;
									});
									SuperUserDeatilCount=cnt-1;
									ActiveUserDeatilCount=cnt-1;
								}
								$('#superUserTableId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-example"  ><thead><tr>'
										       //+'<th style="width:5%">Details</th>'
												+'<th>Order <br/>Contact </th>'
												+'<th>No. Of Orders <br/><span style="">(CFY)</span></th>'
												+'<th>Total Spend<br/><span style="">(CFY)</span></th>'
												+'<th>No. Of <br/> Visits (CFY)</th>'
												+'<th>Last <br/> Visit Date</th>'
												+'<th>No. Of Orders<br/><span style="">(PTD)</span></th>'
												+'<th><span style="color:red">* </span>No. Of <br/>Orders </th>'
												+'<th><span style="color:red">* </span>Total <br/> Spend</th>'
												+'<th>SA.com <br/> Activity</th>'
												+'<th>Staples.com <br/> Activity</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">*</span> These fields include the last five years data.</div>');
								  $("#dataTables-example_processing").css("display","none");
								$('#dataTables-example').dataTable({
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
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true}
									],
									"fnDrawCallback": function( oSettings ) {
										if (SuperUserDeatilCount != -1) {
											for (var count = 0; count <= SuperUserDeatilCount; count++) {
												$('#orderCon'+count).popover({
													html : true,
													placement : 'right',
													content : $("#orderContact"+count).html()
												}).on("hover", function(){
											        $('.popover').addClass('popoverBasic');
											    }).on('show.bs.popover', function() {
					                              
												});
											}
										}
										if (ActiveUserDeatilCount != -1) {
											for (var count = 0; count <= ActiveUserDeatilCount; count++) {
												$('#activeDtl'+count).popover({
													html : true,
													placement : 'right',
													content : $("#activedivId"+count).html()
												}).on("hover", function(){
											        $('.popover').addClass('popoverBasic');
											    }).on('show.bs.popover', function() {
					
												});
											}
										}
										
									   }	
									
								}).fnDraw();
								$("#dataTables-example td:nth-child(5)").css("white-space","nowrap");
						        //$('#dataTables-example tbody td:nth-child(2)').css("white-space","nowrap");
								$('#dataTables-example_length label').css("color","#004c74");
								$('#dataTables-example_length label').css("font-size","15px");
								$('#dataTables-example_length label').css("font-family","helveticaneuebold");
								$('#dataTables-example_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-example_length label').css("color","#004c74");
								//$('#dataTables-example_length label').css("font-weight","600");
								$('#dataTables-example_filter label').css("color","#004c74");
								$('#dataTables-example_filter label').css("letter-spacing","1px");
								$('#dataTables-example_filter').css("text-align","right");
								$('#dataTables-example_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-example_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
								//$('#dataTables-example_filter label').css("font-weight","600");
								$('#dataTables-example_filter label').css("float","right");
								$('#dataTables-example_info').css("color","#004c74");
								$('#dataTables-example_info').css("font-weight","600");
								$('#dataTables-example_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								  $('#dataTables-example').removeClass('display').addClass('table table-striped table-bordered');
								    $('#dataTables-example_filter input[type="search"]').attr('placeholder','Enter Order Contact').css({'width':'250px','display':'inline-block'});
								    //alert($("#orderContact0").html()+"--"+SuperUserDeatilCount);
							    
					   
					}

			})	
     
   }
  
/*function openOrderDetails(){ 
	
      alert('Hi');
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthyear="02015";
	  var currentTime = new Date()
      var month = currentTime.getMonth() ;
	  var year = currentTime.getFullYear()
	  var catid="ALL";
	  monthyear=month+""+year;
		 $.ajax({
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt=0;
					var mkttrHTML = "";
					 $('#order-modal').modal({
					      
						 "backdrop"  : "static",
						"keyboard"  : true,
						"show"      : true           
				    });
					 populateMonthYearData(data);
					 populateCatData(data);
					 $("#yearSel option[value="+month+""+year+"]").attr("selected","selected");
					 
					 $('#orderDetailsTabId').html("");
								if(data.objPurchaseDetailsListVO !=null && data.objPurchaseDetailsListVO !=undefined){
									$.each(data.objPurchaseDetailsListVO, function(i, item) {
										objPurchaseDetailsListVO[cnt]=item;
										//custRecommendationListVOArr[cnt]=item.custRecommendationListVO;
										//dotcomActivityVOArr[cnt]=item.dotcomActivityVO;
										
										mkttrHTML += '<tr class="odd gradeX">'
											+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
											+'<td class="datatablesTd">'
											 + checkUundefined(item.tranDate) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(item.orderNumber) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(custid) +'</td>'
											 +'<td class="datatablesTd">'
											 + formatNum(checkUundefined(item.netSpendAmount)) +'</td>'
											 +'</tr>';
										  cnt++;
									});
								}
								
								$('#orderDetailsTabId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
												
	                                            +'<th>Details</th>'
	                                            +'<th>Tran. date</th>'
	                                            +'<th>Order No.</th>'
	                                            +'<th>Customer id</th>'
	                                            +'<th>Total</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table>');
								$('#dataTables-order').dataTable({
									"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
									"order": [],
									"aoColumns": [{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true}]
								}).fnDraw();		
								$("#dataTables-order td").css("padding-left","10px");
								$("#dataTables-order td").css("WORD_BREAK","BREAK-ALL");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("font-weight","600");
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-weight","600");
								$('#dataTables-order_info').css("color","#004c74");
								$('#dataTables-order_info').css("font-weight","600");
								$('#dataTables-order_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$('#dataTables-order').removeClass('display').addClass('table table-striped table-bordered');
							    $('.dataTables_filter input[type="search"]').attr('placeholder','Search').css({'width':'250px','display':'inline-block'});
					}

			})	
   }*/
function onChangeMonthOrCat(monthYr,catid,isFromReturned){
	// log user activity; view order list
	logUserActivity(2004, 'View Order List');
	var Amount = new Array();
	retFound='N';
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthVal=$("#ui-datepicker-div .ui-datepicker-month :selected").val();
      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
      var map = {"Jan":"1","Feb":"2","Mar":"3","Apr":"4","May":"5","Jun":"6","Jul":"7","Aug":"8","Sep":"9","Oct":"10","Nov":"11","Dec":"12","ALL":"0"};
	  var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
      //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
      if(undefined==monthYr){
    	  if ($("#lastXDaysSel :selected").val() == "0") {
	    	  var monthVal= (($('#datepickerTEXT').val()).split("/")[0]).trim();
	    	  var year= (($('#datepickerTEXT').val()).split("/")[1]).trim();
	    	  monthYr=map[monthVal]+""+year
    	  } else {
    		  monthYr=$("#lastXDaysSel :selected").val()+ latestDateforOrder.substring(2,6);
    	  }
      }
      $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-order_processing").css("display","block");
	  //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange",
				//url : "/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange",
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) { 
					 var cnt=0;
					var mkttrHTML = "";
					 
								if(data.objPurchaseDetailsListVO !=null && data.objPurchaseDetailsListVO !=undefined){
									$.each(data.objPurchaseDetailsListVO, function(i, item) { 
										objPurchaseDetailsListVO[cnt]=item;
										var itemSku='';
										var itemSkuDesc='';
										$.each(item.purchRwdsDtlListVO, function(i, purchase){
											itemSku=itemSku+purchase.skuNumber+',';
											itemSkuDesc=itemSkuDesc+purchase.itemDescription+','; 
										});
										
										$.each(item.savingAmount, function(j, amount){
											Amount[j]=amount;
										});
										
										mkttrHTML += '<tr class="odd gradeX">'
											//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
											
											 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');"  style="text-decoration:underline;padding-right:6px;">'
											 + checkUundefined(item.orderNumber) +'</a>';
										
										if (item.savingCategory != undefined &&
												item.savingCategory != null &&
												item.savingCategory.length > 0) {
											mkttrHTML += '<i id="OrderIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="orderdivId'+cnt+'" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
											 $.each(item.savingCategory, function(k, category){
												 if((checkUundefined(category))=='LOR')
												      category	='Large Order Rebate';
												 mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+toCamCase(removeUnderScore(checkUundefined(category)))+': $'+formatOrderAmt(checkUundefined(Amount[k])) +'</div>'
													 
											 });
											mkttrHTML += '</div>';
										}
										mkttrHTML += '</td>';
										mkttrHTML += '<td class="datatablesTd">';
										var returnedCount = 0;
										$.each(item.purchRwdsDtlListVO, function(i, purchase){
											if (purchase.netSpendAmount < 0) {
												returnedCount++;
												
												if (returnedCount == 1) {
													retFoundChk('X');
													var ordnum=checkUundefined(item.orderNumber);
													mkttrHTML += ('<a href="javascript:openReturnedOrderDetailsInfo('+cnt+',\''+ordnum+'\');" style="text-decoration:underline;padding-right:6px;">X</a>');
												}
											}
										});
										mkttrHTML += '</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(item.tranDate) +'</td>'
											 +'<td>'+ checkUundefined(Number(item.orderLineCount) - returnedCount )+'</td>'
											 +'<td class="datatablesTd formatClsOrd">'
											 + chkNegAmount(formatNum(checkUundefined(item.netSpendAmount))) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(item.orderContact) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(itemSku) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(itemSkuDesc) +'</td>'
											 +'</tr>';
										
										  cnt++;
									});
									OrderDeatilCount = cnt-1;
								}
								if(undefined !=isFromReturned && isFromReturned =='YY'){
									var mn=parseInt(monthYr.substring(0,2));
									var yn=monthYr.substring(2,6);
									var monthNew=parseInt(latestDateforOrder.substring(0,2));
									var yearNew=latestDateforOrder.substring(2,6);
									$("#yearSel option[value="+mn+""+yn+"]").attr("selected","selected");
									 $('#datepickerTEXT').val(monthNameArr[mn-1]+" / "+yn);
							          $('#datepickerTEXT').css("font-weight","bold")
							          $('#datepickerTEXT').css("font-family","Hevletica")
							          $('#datepickerTEXT').css("font-size","14px")
									populateMonthYearData(data,monthNew,yearNew);
									populateCatData(data);
									var dt=getCurrentTime();
									//alert(dt);
									$("#updateDateValueOrder span").html(dt+" ET");
								}
								
					if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('Y' !=isFromReturned)){
						$('#orderDetailsTabId').html("");
					$('#orderDetailsTabId').html(
							'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
									
                                   // +'<th>Details</th>'
                                   
                                    +'<th>Order No.</th>'
                                    +'<th style="color: red">RETURNED</th>'
                                    +'<th>Order Date</th>'
                                    +'<th>No. Of Items</th>'
                                    +'<th>Order Total</th>'
                                    +'<th>Order Contact</th>'
                                    +'<th>Item SKU</th>'
                                    +'<th>Item Desc</th>'
									+ '</tr>'
									+ '</thead><tbody>' + mkttrHTML
									+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>Premium Savings shown here <i id="" class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;"></i> reflects April 16,2016 forward.</div>');
					$("#dataTables-order_processing").css("display","none");
					if((undefined !=isFromReturned && isFromReturned =='Y')){
					var m=monthYr.substring(0, monthYr.length-4);
					var y=monthYr.substring(monthYr.length - 4);
					 $("#yearSel option[value="+monthYr+"]").attr("selected","selected");
					 $('#datepickerTEXT').val(monthNameArr[m-1]+" / "+y);
		             $('#datepickerTEXT').css("font-weight","bold")
		             $('#datepickerTEXT').css("font-family","Hevletica")
		             $('#datepickerTEXT').css("font-size","14px");
					}
					$('#dataTables-order').dataTable({
						"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
						"order": [],
						"bProcessing": true,
						"oLanguage": { "sSearch": "Filter: "},
						"aoColumns": [{"bSortable": true},{"bSortable": true, "visible":true},
						{"bSortable": true,"bSearchable": false},
						{"bSortable": true,"bSearchable": false},						
						{"bSortable": true,"bSearchable": false},
						{"bSortable": true,"bSearchable": true},
						{"bSortable": true , "visible":false},
						{"bSortable": true, "visible":false}],
						"fnDrawCallback": function( oSettings ) {
							
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
						   }
					}).fnDraw();
				}else if(undefined !=isFromReturned && isFromReturned =='Y' && retFound=='N'){
					//alert(111);
					var yr=monthYr.substring(monthYr.length - 4);
					var month=monthYr.substring(0, monthYr.length-4);
					if(month !=1){
					month=(month-1);
					}else if(month==1){
						month=12;
						yr=yr-1;
					}
					openOrderDetailsForReturned(month,yr, catid);
				}
										
								$("#dataTables-order td").css("padding-left","10px");
								$("#dataTables-order td").css("WORD_BREAK","BREAK-ALL");
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
function populateMonthYearData(data,latestMonth,latestYear){
	var optionVal=new Array();
	var optionTxt=new Array();
	var valcount=0;
	var txtcount=0;
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
			minimumDate=new Date(allYearArr[0],0,31);
			//alert("maximum date"+maximumDate+" minimumDate"+minimumDate);
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
		         // var tmp = $('#datepickerTEXT').val().split('/');
		         // $('#datepickerTEXT').datepicker('setDate', new Date(year, month, 1));
		        	$('#datepickerTEXT').css("font-weight","bold")
			          $('#datepickerTEXT').css("font-family","Hevletica")
			          $('#datepickerTEXT').css("font-size","14px")
				},
				onChangeMonthYear : function(year, month,inst ) {
					//alert($("select.ui-datepicker-month option[value=11]").attr("selected")+"-"+$("select.ui-datepicker-month option[value=11]").text());
					//alert(11);
					var val=$("select.ui-datepicker-month option[value=11]").attr("selected");
					if((undefined != val) && ('selected'==val)){ 
					$("select.ui-datepicker-month option[value=11]").attr("selected",true);
					if(month==12){ 
					allselected="Y";}
					else
					allselected="N";
					e.stopPropagation();
					}
					//alert(month+"hhhhhhhh->"+$("select.ui-datepicker-month option[value=11]").attr("selected"))
					//alert($("select.ui-datepicker-month option[value=11.6]").attr("selected")+"-"+$("select.ui-datepicker-month option[value=11.6]").text());
					if(month==12){ 
					allselected="Y";}
					else
					allselected="N";

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
			          $('#datepickerTEXT').css("font-size","14px")
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

function populateCatData(data){
	 var selectedDeviceModel = $('#sortSel');
		selectedDeviceModel.empty();
			selectedDeviceModel.append($('<option/>', {
				value : 'ALL',
				text : 'All Purchases'}));
	if(data.objCategoriesList !=null && data.objCategoriesList !=undefined){
		$.each(data.objCategoriesList, function(i, item) {
			//alert("ID>>>"+item. mktItmCatCd);
			//alert("ID>>>"+item. name);
			selectedDeviceModel.append($('<option/>', {
				value : item. mktItmCatCd,
				text :  item.name
			}));
		  });
		
    }
}

function openSuperUserModalInfo(name,index,fromWhere,abanStat,dotcomStat){
	//var name=unescape(name);
	// log user activity; view .Com activity
	var name= name.replace('openbracket','(');
    var name= name.replace('closebracket',')');
    var name= name.replace("quote","'");
	
    var mkttrHTML="";
    
    //alert(custRecommendationListVOArr+"--"+abandonedCartListVOArr+"--"+dotcomActivityVOArr)
  var serachlistItem=$.each(   searchItemsListVOArr[index], function(i, item) {
     //alert(item.searchItems);
          return item.searchItems;
   });    
    var column1=$.each(abandonedCartListVOArr[index], function(i, item) {
           return item;
    });
    var column2=$.each(custRecommendationListVOArr[index], function(i, item) {
           
           return item;
    });
 var column3=$.each(dotcomActivityVOArr[index], function(i, item) {
           
           return item;
    });
 if((null ==column1 || ''==column1) && (null ==column2 || ''==column2) && (null ==column3 || ''==column3) && (null ==serachlistItem || ''==serachlistItem)){
    alert("No details available for this customer");
    return false;
 }
 if(null !=column1 && ''!=column1){
    $('#a1').attr("data-toggle","tab");
    $('#li1').removeClass('disabled');
    var htmlData="";
    $("#tab_1_1").html("");
     //$("#amazon_scroller2").amazon_scroller({});
    
    //alert("aban length= "+column1.length)
     for(var i=0;i<column1.length;i++){
           var thumbnail=checkUundefined(column1[i].thumbnail);
           if(thumbnail=='' || null ==thumbnail | undefined == thumbnail){
                  thumbnail="http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
           } else{
                  thumbnail='http://www.staples-3p.com/s7/is/image/Staples/'+thumbnail;
           }
           
           var abanActivity = checkUundefined(column1[i].act);
           if(abanActivity == 'A')
        	   abanActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;" >Abandoned</span></div>';
     	   else if(abanActivity == 'V')
     		  abanActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:darkblue;" >Viewed</span></div>';
     	   else if(abanActivity == 'P')
     		  abanActivity = '<div class="a-row a-size-small"> <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:green;" >Purchased</span></div>';
     		  
           var updatedUrl='http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].CatentryId:""):"")+'&langId=-1&currentSKUNbr='+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+'&storeId=10101&itemType=1'
           // var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
           htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column1[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" alt="jQuery in Action, Second Edition"/>'
                    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;" aria-hidden="true" data-rows="3">'
                       +truncateTitle(checkUundefined(column1[i].itemDescription))
                   +'</div></a>'
                  
                  +'<div class="a-row a-size-small"><a class="a-size-small a-link-child" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: 100px;" href="javascript:openUrl(\''+updatedUrl+'\')">'+checkUundefined(column1[i].skuNumber)+'</a></div>'
                  +'<div class="a-row a-size-small">'+checkUundefined(column1[i].actDate).substring(0,11)+'</div>'
                  + abanActivity
                  // +'<div class="a-icon-row a-spacing-none">'
                     //  +'<a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">'
                        //  +' <i class="a-icon a-icon-star a-star-4-5"></i>'
                      // +'</a>'
                     //  +'<a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>'
                 //  +'</div>'
              // +'<div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">'+checkUundefined(column2[i].skuNumber)+'</span></i></span></div>' 
           +'</li>';
           
     }
     
     //totalAbandonedLength=column2.length;
    // alert(column2.length +"-"+totalAbandonedLength)
   // alert("amazon_scroller"+index)
    totalAbandonedLength=column1.length;
     var prefixData='<div id="amazon_scroller_aban'+index+'" class="amazon_scroller">'
                              +'<div id="abanId" class="amazon_scroller_mask"><ul id="SUulid">'
                              
     var suffixData='</ul></div><ul id="aban_nav" class="amazon_scroller_nav">'
                           +'<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'</ul><div style="clear: both"></div></div>'             
     $("#tab_1_1").html(prefixData+""+htmlData+""+suffixData);
     
     //if(recommStatus==0){
     $("#amazon_scroller_aban"+index).amazon_scroller({
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
     // recommStatus++;
     //}
     
 }else{
    $("#tab_1_1").html("");
    $('#a1').removeAttr("data-toggle");
    $('#li1').addClass('disabled');
    //$('#tab_1_1').css({'background-color': 'grey','opacity': '0.5'}); 
 }
 /*  Aban Data End  */
     
     /*  Recomm Data Start  */
 /**** Removing Recommendations data 
 if(null !=column2 && ''!=column2){
    $('#a2').attr("data-toggle","tab");
    $('#li2').removeClass('disabled');
    var htmlData="";
    $("#tab_1_2").html("");
     //$("#amazon_scroller2").amazon_scroller({});
     for(var i=0;i<column2.length;i++){
           var thumbnail=checkUundefined(column2[i].thumbnail);
           if(thumbnail=='' || null ==thumbnail | undefined == thumbnail){
                  thumbnail="http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
           } else{
                  thumbnail='http://www.staples-3p.com/s7/is/image/Staples/'+thumbnail;
           }
           var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
             
           htmlData = htmlData.toString() + '<li><a href="#" title="'+checkUundefined(column2[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" alt="jQuery in Action, Second Edition"/>'
                    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3">'
                       +checkUundefined(column2[i].itemDescription)
                   +'</div></a>'
                  
                  +'<div class="a-row a-size-small"><a class="a-size-small a-link-child" href="javascript:openUrl(\''+url2+'\')">'+checkUundefined(column2[i].skuNumber)+'</a></div>'
                  //+'<div class="a-row a-size-small">'+checkUundefined(column1[i].actDate)+'</div>'
                  // +'<div class="a-icon-row a-spacing-none">'
                     //  +'<a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">'
                        //  +' <i class="a-icon a-icon-star a-star-4-5"></i>'
                      // +'</a>'
                     //  +'<a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>'
                 //  +'</div>'
              // +'<div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">'+checkUundefined(column2[i].skuNumber)+'</span></i></span></div>' 
           +'</li>';
           
     }
     totalRecommLength=column2.length;
     //totalAbandonedLength=column2.length;
    // alert(column2.length +"-"+totalAbandonedLength)
   // alert("amazon_scroller"+index)
    // alert(totalAbandonedLength)
     var prefixData='<div id="amazon_scroller_recomm'+index+'" class="amazon_scroller">'
                              +'<div id="recommId" class="amazon_scroller_mask"><ul>'
                              
     var suffixData='</ul></div><ul id="recomm_nav" class="amazon_scroller_nav">'
                           +'<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'</ul><div style="clear: both"></div></div>'             
     $("#tab_1_2").html(prefixData+""+htmlData+""+suffixData);
     
     //if(recommStatus==0){
     $("#amazon_scroller_recomm"+index).amazon_scroller({
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
     // recommStatus++;
     //}
     
 }else{
    $("#tab_1_2").html("");
    $('#a2').removeAttr("data-toggle");
    $('#li2').addClass('disabled');
 }
 Removing Recommendations data ******/
     /*  Recomm Data End  */
     
     /*  Dotcom Data Start  */
     if(null !=column3 && ''!=column3){
           var htmlData="";
           $('#a3').attr("data-toggle","tab");
           $('#li3').removeClass('disabled');
           $("#tab_1_3").html("");
            //$("#amazon_scroller2").amazon_scroller({});
           //alert("dotcom length= "+column3.length)
         for(var i=0;i<column3.length;i++){
           var thumbnail=checkUundefined(column3[i].thumbnail);
           if(thumbnail=='' || null ==thumbnail | undefined == thumbnail){
                  thumbnail="http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
           } else{
                  thumbnail='http://www.staples-3p.com/s7/is/image/Staples/'+thumbnail;
           }
           
           var dotActivity = checkUundefined(column3[i].act);
           if(dotActivity == 'A')
        	   dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;" >Abandoned</span></div>';
     	   else if(dotActivity == 'V')
     		  dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:darkblue;" >Viewed</span></div>';
     	   else if(dotActivity == 'P') 
     		  dotActivity = '<div class="a-row a-size-small"> <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:green;" >Purchased</span></div>';
     		  
           var dotPriceAndDotQuantity = '<div class="a-row a-size-small-indp">'+((column3[i].price != undefined && column3[i].price != null) ? 'Total Price:$'+ column3[i].price : '')+'</div><div class="a-row a-size-small-indp">'+((column3[i].quantity != undefined && column3[i].quantity != null) ? ' Qty: ' + column3[i].quantity : '') + '</div>';
           
          var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
          htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column3[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" alt="jQuery in Action, Second Edition"/>'
                    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;">'
                       +truncateTitle(checkUundefined(column3[i].itemDescription))
                   +'</div></a>'
                  
                  +'<div class="a-row a-size-small"><a class="a-size-small a-link-child" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: 100px;" href="javascript:openUrl(\''+url3+'\')">'+checkUundefined(column3[i].skuNumber)+'</a></div>'
                  +'<div class="a-row a-size-small">'+checkUundefined(column3[i].actDate).substring(0,11)+'</div>'
                  + dotActivity
                  + ((checkUundefined(column3[i].act) == 'P'  || checkUundefined(column3[i].act) == 'A') ? dotPriceAndDotQuantity : '')
                  // +'<div class="a-icon-row a-spacing-none">'
                     //  +'<a class="a-link-normal" title="4.5 out of 5 stars" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">'
                        //  +' <i class="a-icon a-icon-star a-star-4-5"></i>'
                      // +'</a>'
                     //  +'<a class="a-size-small a-link-normal" href="/JavaScript-Definitive-Guide-Activate-Guides/product-reviews/0596805527/ref=pd_sim_14_cr_1/189-8437559-3078824?ie=UTF8&amp;refRID=0QXV9D9641GB6CRRJFA1">134</a>'
                 //  +'</div>'
              // +'<div class="a-row a-size-small"><span class="a-size-small a-color-secondary">Paperback</span></div><div class="a-row"><span class="a-size-base a-color-price">$28.99</span> <span style="position: relative; top: 2px;"><i class="a-icon a-icon-prime a-icon-small" aria-label="Prime"><span class="a-icon-alt">'+checkUundefined(column2[i].skuNumber)+'</span></i></span></div>' 
           +'</li>';
           
         }
           totalDotCommLength=column3.length;
         //totalAbandonedLength=column2.length;
        // alert(column2.length +"-"+totalAbandonedLength)
       // alert("amazon_scroller"+index)
        // alert(totalAbandonedLength)
         var prefixData='<div id="amazon_scroller_dotcom'+index+'" class="amazon_scroller" style="background-color:#fff;">'
                              +'<div id="dotcomId" class="amazon_scroller_mask"><ul id="SUulid">'
                              
         var suffixData='</ul></div><ul id="dotcom_nav" class="amazon_scroller_nav">'
                           +'<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                           +'</ul><div style="clear: both"></div></div>'             
         $("#tab_1_3").html(prefixData+""+htmlData+""+suffixData);
         
         //if(recommStatus==0){
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
         // recommStatus++;
         //}
         
     }else{
           $("#tab_1_3").html("");
           $('#a3').removeAttr("data-toggle");
           $('#li3').addClass('disabled');
     }
     /*if(null !=column1 && ''!=column1 && column1.length>0){
           $('#a1').attr("aria-expanded",true);
           $('#li1').attr("class","active");
           var col2status=$('#li2').attr("class");
           var col3status=$('#li3').attr("class");
           if(undefined ==col2status || '' == col2status || null ==col2status || col2status=='active disabled' ||col2status=='active'){
                  if(col2status=='active disabled' || col2status=='disabled')
                  $('#li2').attr("class","disabled");
                 // else
                       // $('#li2').attr("class","");
           }
           if(undefined ==col3status || ''== col3status || null ==col3status || col3status=='active disabled' ||col3status=='active'){
                  if(col3status=='active disabled' || col3status=='disabled')
                  $('#li3').attr("class","disabled");
           //else
                      //$('#li3').attr("class","");
           }
          // $("#tab_1_1").attr("class","tab-pane fade active in");
     }else if(null !=column2 && ''!=column2 && column2.length>0){
           $('#a2').attr("aria-expanded",true);
           $('#li2').attr("class","active");
           var col1status=$('#li1').attr("class");
           var col3status=$('#li3').attr("class");
           if(undefined ==col1status || ''== col1status || null ==col1status || col1status=='active disabled' ||col1status=='active'){
                  if(col1status=='active disabled' || col1status=='disabled')
                  $('#li1').attr("class","disabled");
           //else
                     //$('#li1').attr("class","");
           }
           if(undefined ==col3status || ''== col3status || null ==col3status || col3status=='active disabled' ||col3status=='active'){
                  if(col3status=='active disabled' || col3status=='disabled')
                  $('#li3').attr("class","disabled");
           //else
                      //$('#li3').attr("class","");
           }
           //$("#tab_1_2").attr("class","tab-pane fade active in");
           //$("#tabs").tabs({ active: 2 });
     }else if(null !=column3 && ''!=column3 && column3.length>0){
           $('#a3').attr("aria-expanded",true);
           $('#li3').attr("class","active");
           var col1status=$('#li1').attr("class");
           var col2status=$('#li3').attr("class");
           if(undefined ==col1status || ''== col1status || null ==col1status || col1status=='active disabled' ||col1status=='active'){
                  if(col1status=='active disabled' || col1status=='disabled')
                  $('#li1').attr("class","disabled");
           //else
                     //$('#li1').attr("class","");
           }
           if(undefined ==col2status || ''== col2status || null ==col2status || col2status=='active disabled' ||col2status=='active'){
                  if(col2status=='active disabled' || col2status=='disabled')
                  $('#li2').attr("class","disabled");
           //else
                  //$('#li2').attr("class","");
           }
           //$("#tab_1_3").attr("class","tab-pane fade active in");
     }*/
     if(fromWhere=='dotcom'){ //alert("dot")
    	 logUserActivity(2008, 'View STAPLES.COM Activity');
    	 $('#a3').attr("aria-expanded",true);
         $('#li3').attr("class","active");
         $('#li2').attr("class","");
         $('#li1').attr("class","");
         if('tab-pane fade active in'==($("#tab_1_1").attr("class"))){
        	 $("#tab_1_1").attr("class" ,"tab-pane fade");
         }
         $("#tab_1_3").attr("class","tab-pane fade active in");
         if( 'Y' != abanStat){ 
                $('#li1').attr("class","disabled");
         }
        
     }else if(fromWhere=='abandoned'){ //alert("aban")
    	 $('#a1').attr("aria-expanded",true);
         $('#li1').attr("class","active");
         $('#li2').attr("class","");
         $('#li3').attr("class","");
         
         if('tab-pane fade active in'==($("#tab_1_3").attr("class"))){
        	 $("#tab_1_3").attr("class" ,"tab-pane fade");
         }
         $("#tab_1_1").attr("class","tab-pane fade active in");
         if( 'Y' != dotcomStat){  
             $('#li3').attr("class","disabled");
         }
         if ((null == column1 || '' == column1) && (null != serachlistItem && '' != serachlistItem)) {
        	 logUserActivity(2012, 'View Search Terms');
        	 $('#li1').attr("class","disabled");
        	 $("#tab_1_1").attr("class" ,"tab-pane fade");
         } else {
        	 logUserActivity(2003, 'View SA.COM Activity');
         }
     }
     /*  Dotcom Data End  */
     
/*var max=((column1.length)>(column2.length + column3.length))?"column1":((column2.length >= column3.length)?"column2":"column3")
           if(max==='column1'){ 
                for(var i=0;i<column1.length;i++){ 
                   
                    var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
                    var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
                    var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
                          mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
                                      +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
                                      +'<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
                                      +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
                                      +'<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
                                      +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
                                      +'</tr>';
                        }
              } else if(max=='column2'){ 
                for(var i=0;i<column2.length;i++){
                   var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
                   var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
                    var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
                   mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
                               +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
                               +'<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
                               +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
                               +'<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
                               +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
                               +'</tr>';
               
                         }
              }else if(max=='column3'){ 
                     
                   for(var i=0;i<column3.length;i++){
                        var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
                           var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
                           var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
                        mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
                               +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
                               +'<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
                               +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
                               +'<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
                               +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
                               +'</tr>';
                  }
              }*/        

    //$('#superUserInfoId').html("");
    $('#searchStringId').html("");
    var searchStr="";
    var searchStrNull="";
    var searchItemStr="";
    var searchStrBlocked="";
    for(var count=0;count<serachlistItem.length;count++){
      // searchStr= +""+ checkUundefined((serachlistItem[count] !=undefined && serachlistItem[count]!='')?serachlistItem[count].searchItems:"")+",";
    
    	if (serachlistItem[count] !=undefined && serachlistItem[count]!=''){
    		var searchString = serachlistItem[count].searchItems.split(',');
    		if(undefined != searchString && '' !=searchString && searchString.length>1){ 
    		searchString.sort();
    		}
    		for(var index=0;index<searchString.length;index++){
    			var searchItemStr = searchString[index];
    			//alert(searchItemStr);
    	if (searchItemStr.indexOf('blocked') != -1){
    		//alert("Inside blocked");
    		searchStrBlocked= searchStrBlocked + ""+ checkUundefined((searchItemStr !=undefined && searchItemStr!='')?searchItemStr.substring(searchItemStr.lastIndexOf(":")+1,searchItemStr.length):"")+",";
    	
    	}else if(searchItemStr.indexOf('null') != -1){
    		searchStrNull= searchStrNull + ""+ checkUundefined((searchItemStr !=undefined && searchItemStr!='')?searchItemStr.substring(searchItemStr.lastIndexOf(":")+1,searchItemStr.length):"")+",";
    	}else{
    		 searchStr= searchStr + ""+ checkUundefined((searchItemStr !=undefined && searchItemStr!='')?searchItemStr:"")+",";
    	}
    		}
    	}}
    if(searchStr !='' && (searchStr.indexOf(',')!=-1)) {
       searchStr=searchStr.substring(0, searchStr.lastIndexOf(","));
    }
    if(searchStrNull !='' && (searchStrNull.indexOf(',')!=-1)) {
        searchStrNull=searchStrNull.substring(0, searchStrNull.lastIndexOf(","));
     }
    if(searchStrBlocked !='' && (searchStrBlocked.indexOf(',')!=-1)) {
    	searchStrBlocked=searchStrBlocked.substring(0, searchStrBlocked.lastIndexOf(","));
     }
    if((undefined !=searchStr && searchStr !='') || (undefined !=searchStrNull && searchStrNull !='') || (undefined !=searchStrBlocked && searchStrBlocked !=''))
    {
    	 
    	if(undefined !=searchStrNull && searchStrNull !=''){ 	 
    		 $('#searchStringId').append('<tr><td style="font-weight:bold">Failed Search:</td></tr>');
    	    $('#searchStringId').append('<tr><td style="">'+searchStrNull+' </td></tr>');
    	    }
    	if(undefined !=searchStrBlocked && searchStrBlocked !=''){ 	 
   		 $('#searchStringId').append('<tr><td style="font-weight:bold">Blocked Search :</td></tr>');
   	    $('#searchStringId').append('<tr><td style="">'+searchStrBlocked+' </td></tr>');
   	    }
    	    if(undefined !=searchStr && searchStr !=''){
    	    	 $('#searchStringId').append('<tr><td style="font-weight:bold">No Purchase Conversion:</td></tr>');
    	    $('#searchStringId').append('<tr><td style="">'+searchStr+' </td></tr>');
    	    }

    }
    else
    $('#searchStringId').append('<tr><td style="color:red;font-weight:bold">No search data available for the user</td></tr>');      
    $('#super-info-dialog').modal({
           "backdrop"  : "static",
           handle: ".modal-header",
    "keyboard"  : true,
    "show"      : true           
    }); 
    
    if(name!=undefined && name!=''){
           $("#uName").html(name); 
    }
    


    /*$('#superUserInfoId').html(
                  '<table class="table table-striped table-bordered table-hover" id="dataTables-superInfo" width="100%" ><thead><tr>'
                  +'<th>ABANDONED CART</th>'
                  +'<th>OUR RECOMMENDATIONS</th>'
            +'<th>DOTCOM ACTIVITY</th>'
                               + '</tr>'
                               + '</thead><tbody>' + mkttrHTML
                               + '</tbody></table>');
    
    $('#dataTables-superInfo').DataTable({
           "lengthMenu": [[4, 10, 15, -1], [4, 10,15, "All"]],
             "jqueryUI":true,
             "order": [],
                  "aoColumns": [{"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
                  {"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
                  {"bSortable": true, "sWidth": '33%',"sType" : "mystring" }
                  
                  ]
                  
                  
    })            

    $("#dataTables-superInfo td").css("padding-left","10px");
    $("#dataTables-superInfo td").css("WORD_BREAK","BREAK-ALL");
    $('#dataTables-superInfo_length label').css("color","#004c74");
    $('#dataTables-superInfo_length label').css("color","#004c74");
    $('#dataTables-superInfo_length label').css("font-weight","600");
    $('#dataTables-superInfo_filter label').css("color","#004c74");
    $('#dataTables-superInfo_filter').css("text-align","right");
    $('#dataTables-superInfo_filter label').css("font-weight","600");
    $('#dataTables-superInfo_info').css("color","#004c74");
    $('#dataTables-superInfo_info').css("font-weight","600");
    $('#dataTables-superInfo_paginate').css("text-align","right");
    $('.form-inline .form-control').css("color","#004c74");
    $('.form-inline .form-control').css("border","1px solid #004c74");
    $('select .form-control.input-sm').css("border","1px solid #004c74");
    $('#dataTables-superInfo').removeClass('display').addClass('table table-striped table-bordered');
 $('.dataTables_filter input[type="search"]').attr('placeholder','Search').css({'width':'250px','display':'inline-block'});*/

    
}

function openSuperUserInfo(name,index){
	var mkttrHTML="";
	
     var serachlistItem=$.each(	searchItemsListVOArr[index], function(i, item) {
    	 //alert(item.searchItems);
 		return item.searchItems;
 	});	
	var column1=$.each(abandonedCartListVOArr[index], function(i, item) {
		return item;
	});
	var column2=$.each(custRecommendationListVOArr[index], function(i, item) {
		
		return item;
	});
    var column3=$.each(dotcomActivityVOArr[index], function(i, item) {
		
		return item;
	});
   var max=((column1.length)>(column2.length + column3.length))?"column1":((column2.length >= column3.length)?"column2":"column3")
		if(max==='column1'){ 
		     for(var i=0;i<column1.length;i++){ 
		    	 
		    	  var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
		    	  var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
		    	  var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
		    		 mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
						 +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
						 +'<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
						 +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
						 +'<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
						 +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
						 +'</tr>';
				 }
		   } else if(max=='column2'){ 
		     for(var i=0;i<column2.length;i++){
		    	 var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
		    	 var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
		    	  var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
		    	 mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
					 +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
					 +'<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
					 +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
					 +'<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
					 +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
					 +'</tr>';
		    
				 }
		   }else if(max=='column3'){ 
			   
			 for(var i=0;i<column3.length;i++){
				 var url1='http://www.staples.com/product_'+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"");
				   var url2='http://www.staples.com/product_'+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"");
				   var url3='http://www.staples.com/product_'+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"");
				 mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url1+'\')">'
					 +((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].skuNumber:""):"")+"</a>-</span>"+((column1 !=undefined && column1 !='')? checkUundefined((column1[i] !=undefined && column1[i]!='')?column1[i].itemDescription:""):"")+'</td>'
					 +'<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url2+'\')">'
					 +((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].skuNumber:""):"")+"</a>-</span>"+((column2 !=undefined && column2 !='')? checkUundefined((column2[i] !=undefined && column2[i]!='')?column2[i].itemDescription:""):"")+'</td>'
					 +'<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''+url3+'\')">'
					 +((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].skuNumber:""):"")+"</a>-</span>"+((column3 !=undefined && column3 !='')? checkUundefined((column3[i] !=undefined && column3[i]!='')?column3[i].itemDescription:""):"")+'</td>'
					 +'</tr>';
			 }
		   }		

	$('#superUserInfoId').html("");
	$('#searchStrid').html("");
	var searchStr="";
	for(var count=0;count<serachlistItem.length;count++){
	   searchStr= +""+ checkUundefined((serachlistItem[count] !=undefined && serachlistItem[count]!='')?serachlistItem[count].searchItems:"")+",";
	 
	}
	if(searchStr !='' && (searchStr.indexOf(',')!=-1)) {
	   searchStr=searchStr.substring(0, searchStr.lastIndexOf(","));
	}
	$('#searchStrid').append('<tr><td style="color:red;font-weight:bold">'+searchStr+'</td></tr>');
	$('#super-info').modal({
		 "backdrop"  : "static",
		 handle: ".modal-header",
	"keyboard"  : true,
	"show"      : true           
	}); 
	
	if(name!=undefined && name!=''){
		$("#userName").html(name); 
	}
	$('#superUserInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-superInfo" width="100%" ><thead><tr>'
			+'<th>ABANDONED CART</th>'
			+'<th>OUR RECOMMENDATIONS</th>'
	        +'<th>DOTCOM ACTIVITY</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	$('#dataTables-superInfo').DataTable({
		"lengthMenu": [[4, 10, 15, -1], [4, 10,15, "All"]],
		  "jqueryUI":true,
		  "order": [],
		  "oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
			{"bSortable": true, "sWidth": '33%',"sType" : "mystring" },
			{"bSortable": true, "sWidth": '33%',"sType" : "mystring" }
			
			]
			
			
	})		
 
	$("#dataTables-superInfo td").css("padding-left","10px");
	$("#dataTables-superInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-superInfo_length label').css("color","#004c74");
	$('#dataTables-superInfo_length label').css("color","#004c74");
	$('#dataTables-superInfo_length label').css("font-weight","600");
	$('#dataTables-superInfo_filter label').css("color","#004c74");
	$('#dataTables-superInfo_filter label').css("letter-spacing","1px");
	$('#dataTables-superInfo_filter').css("text-align","right");
	$('#dataTables-superInfo_filter label').css("font-weight","600");
	$('#dataTables-superInfo_filter label').css("float","right");
	$('#dataTables-superInfo_info').css("color","#004c74");
	$('#dataTables-superInfo_info').css("font-weight","600");
	$('#dataTables-superInfo_paginate').css("text-align","right");
	$('.form-inline .form-control').css("color","#004c74");
	$('.form-inline .form-control').css("border","1px solid #004c74");
	$('select .form-control.input-sm').css("border","1px solid #004c74");
	$('#dataTables-superInfo').removeClass('display').addClass('table table-striped table-bordered');
    $('#dataTables-superInfo_filter input[type="search"]').attr('placeholder','Search').css({'width':'250px','display':'inline-block'});
}

function openOrderDetailsInfo(index,ordNum){
	// log user activity; view order details
	logUserActivity(2031, 'View Order Details Info From Lead Account');
	
	var inputString=$('#dataTables-order_filter input[type=search]').val();
	
	var contains=0;
	var mkttrHTML="";
	//alert(inputString);
	$.each(objPurchaseDetailsListVO[index].purchRwdsDtlListVO, function(i, item) {
		if (item.netSpendAmount < 0) {
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
	$("#ordertitle").html("Order Info - "+ordNum);
	$('#OrderDetailsInfoId').html(
			'<table class="table table-striped table-bordered table-hover" id="dataTables-OrderInfo" width="100%" ><thead><tr>'
			+'<th>Item No.</th>'
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
			"aoColumns": [{"bSortable": true, "sWidth": '33%'},
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





function openReturnedOrderDetailsInfo(index,ordNum){
	// log user activity; view order details
	logUserActivity(2018, 'Clicked Returns on Orders grid');
	
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
			 +'<a style="pointer-events:none;cursor: default;color:inherit;">'+ checkUundefined(item.skuNumber)+'</a>' +'</td>'
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
	    "title" : "ddd",
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




function openOrderDetailsShipTO(order_number,shipTo,order_date){
	// log user activity; view order details
	logUserActivity(2010, 'View Ship To Order Line Info');
	$("#shplocId").html("");
	$("#shplocId").html("Ship To Order Info "+shipTo+"<span style='padding-left:3px;padding-right:3px;'>-</span>"+order_number);
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
		if (item.netSpendAmount < 0) {
			return true;
		}
		
		if(inputString!=null && inputString!='')
			contains=1;
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
			 + '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber)+'</a>' +'</td>'
			 + '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:25%;">'
			 + checkUundefined(item.itemDescription) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:15%;">'
			 + checkUundefined(item.totalQty) +'</td>'
			 +'<td class="formatClsShip" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
			 + chkNegAmount(formatNum(checkUundefined(item.totalPriceAmount))) +'</td>'
			 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
			 + formatNum(checkUundefined(item.couponAmount)) +'</td>'
			 +'<td class="formatClsShip" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
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
			+'<th style="text-align:center;line-height:3;">Item No.</th>'
			+'<th style="text-align:center;line-height:3;">Item Description</th>'
	        +'<th style="text-align:center;line-height:3;">Qty</th>'
	    	+'<th style="text-align:center;line-height:3;">Price</th>'
			+'<th style="text-align:center;line-height:3;">Coupons</th>'
	        +'<th style="text-align:center;line-height:3;">Total Spend</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table>');
	
	var oTable=$('#dataTables-shipOrderInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "order": [],
		  "oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true},
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
	logUserActivity(2019, 'Clicked Returns on Ship To Order Info grid');
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
		
		if(inputString!=null && inputString!='')
			contains=1;
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:17%;">'
			 + '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber) +'</a>'+'</td><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:18%;">'
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
			+'<th style="text-align:center;line-height:2.5;white-space:nowrap">Item No.</th>'
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
		  "order": [],
		  "oLanguage": { "sSearch": "Filter: "},
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
		
	
	if(contains == 1 ){
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


	function openShipToDetailsInfo(shipTo,custNum){
	// log user activity; view order details
		var Amount = new Array();
		$("#ShipToLocId").html("");
		$("#ShipToLocId").html(" - <span style='padding-left:2px;padding-right:2px;'>"+shipTo+"</span>");
	logUserActivity(2009, 'View Ship To Order Info');
		var formData={shipTo : shipTo};
		var mkttrHTML="";
	var inputString=$('#dataTables-ship_filter input[type=search]').val();
	$.ajax({
		dataType: "json",
		url : ctx+"/getShipToDetails/"+custNum,
	 //url : "/getShipToData/"+custid,
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var cnt=0;
		//if(data.size)
if (null != data && undefined != data && data.length == 0 ){
	alert("No orders for this location for the last 2 years");
	return;
}

		$.each(data, function(i, item) {
		
		/*if (item.orderNumber.indexOf(inputString) > -1 || item.itemDescription.indexOf(inputString) > -1){
			contains=1;
		}*/
		if (undefined!= item.orderDate && '' != item.orderDate){
			var orderdate=item.orderDate.substring(0,10);
		}
		/*var shipToItemDetails = "";
		//console.log(item.purchaseRwdsDetailsVO);
		var shipToDetails = item.;*/
		/*shipToDetails.forEach(function(item) {
			shipToItemDetails += skuNumber+","+itemDescription+",";
		});*/
		/*$.each(shipToDetails, function(i, shipToItem) {
			if(shipToItem!=null && shipToItem.skuNumber!=null)
			{
				var skuNumber = checkUundefined(shipToItem.skuNumber);
				var descr = checkUundefined(shipToItem.itemDescription);
				
				shipToItemDetails += skuNumber+"-"+descr+",";
			}
		})*/
		$.each(item.savingAmount, function(j, amount){
			Amount[j]=amount;
		});
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;"><a href="javascript:openOrderDetailsShipTO('+item.orderNumber+',\''+checkUundefined(shipTo)+'\',\''+checkUundefined(orderdate)+'\');" style="text-decoration:underline;padding-right:6px;">'
		 + checkUundefined(item.orderNumber) +'</a>';
		if (item.savingCategory != undefined &&
				item.savingCategory != null &&
				item.savingCategory.length > 0) {
			mkttrHTML += '<i id="ShipOrderIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="ShiporderdivId'+cnt+'" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u>Premium Savings Info</u> : </div>'
			 $.each(item.savingCategory, function(k, category){
				 if((checkUundefined(category))=='LOR')
			      category	='Large Order Rebate';
				 mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+toCamCase(removeUnderScore(checkUundefined(category)))+': $'+formatOrderAmt(checkUundefined(Amount[k])) +'</div>'
					 
			 });
			mkttrHTML += '</div>';
		}
		 
		 
	mkttrHTML += '</td>';
	mkttrHTML += '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
		+ ((item.minOrderAmount != undefined && item.minOrderAmount != null && item.minOrderAmount != "" && item.minOrderAmount < 0) ? 
				'<a href="javascript:openReturnedOrderDetailsShipTO('+item.orderNumber+',\''+checkUundefined(shipTo)+'\',\''+checkUundefined(orderdate)+'\');" style="text-decoration:underline;padding-right:6px;">X</a>' : '')
		+ '</td>'
		 + '<td class="formatClsShip" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
		 + chkNegAmount(formatNum(checkUundefined(item.orderAmount))) +'</td>'
		 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
		 + checkUundefined(orderdate) +'</td>'
		 +'<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
		 + checkUundefined(item.orderContact) +'</td>'
		 +'<td  style="display: none">'
		 +checkUundefined(item.skuList)
		 +'</td>'
		 +'<td  style="display: none">'
		 +checkUundefined(item.descriptionList)
		 +'</td>'
		 +'</tr>';
		cnt++;
	})	
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
			+'<th style="text-align:center">Order Number</th>'
			+'<th style="text-align:center; color: red">RETURNED</th>'
			+'<th style="text-align:center">Order Amount</th>'
	        +'<th style="text-align:center">Order Date</th>'
	        +'<th style="text-align:center">Order Contact</th>'
	        +'<th style="text-align:center;display:none">SKU</th> <th style="text-align:center;display:none">Description</th>'
					+ '</tr>'
					+ '</thead><tbody>' + mkttrHTML
					+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>Premium Savings shown here <i id="" class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;"></i> reflects April 16,2016 forward.</div>');
	
	var oTable=$('#dataTables-ShipInfo').DataTable({
		"lengthMenu": [[5, 10, 15, -1], [5, 10,15, "All"]],
		  "jqueryUI":true,
		  "order": [],
		  "oLanguage": { "sSearch": "Filter: "},
			"aoColumns": [{"bSortable": true, "sWidth": '25%'},
			{"bSortable": true, "sWidth": '25%'},{"bSortable": true, "sWidth": '25%'},
			{"bSortable": true, "sWidth": '25%'},
			{"bSortable": true, "sWidth": '24%'},
			{"bSortable": false, "sWidth": '1%'},
			{"bSortable": false, "sWidth": '1%'}
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
			/*"fnDrawCallback": function (oSettings) {
				        $(".dataTables_filter").each(function () {
				            $(this).appendTo($(this).parent().siblings(".panel-body"));
				        })
				    }
				
			})*/

	});
		
	/*if(contains == 1){
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}*/		
//alert($('#dataTables-OrderInfo_filter input[type=search]').val());
	//$("#dataTables-OrderInfo td").css("padding-left","10px");
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
    oTable.search(inputString).draw();
//    oTable.search($(this).val()).draw(); 
    //oTable.search(inputString).draw();*/
	}
	})
}

	function openOrderDetails(isFromReturned){
		
		// log user activity; view order list
		logUserActivity(2030, 'View Order List From Lead Account');
		var Amount = new Array();
		//alert("SAM");
		
		//var latestDate= getLatestFiscalDate();
		//alert("before month")
		//alert(latestDate.substring(1,2));
		//var monthNew=parseInt(latestDateforOrder.substring(0,2));
		//alert(month+" "+latestDate.substring(2,6));
		//var yearNew=latestDateforOrder.substring(2,6);
		//alert(monthNew +"--"+yearNew);
		//$("#yearSel").val("January 2015");
		//var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
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
		  var dt = geDataRefreshTime('MV_SAM_ORDER_HEADER');
		  //var dt=getCurrentTime();
			//alert(dt);
			$("#updateDateValueOrder span").html(dt+" ET");
		  //alert(monthNew+"-"+yearNew+"-"+month+"-"+year);
		  var catid="ALL";
		  $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
		  $("#dataTables-order_processing").css("display","block");
		  monthyear=month+""+year;
		  //alert("/getOrderDetailsHighLevelData/"+custid+"--"+monthyear+"--"+catid);
			 $.ajax({
				 	dataType: "json",
					url : ctx+"/getOrderDetailsHighLevelDataSAM/"+custid,
					//url : "/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid+"/"+"NoChange",
					type : "POST",
					cache : false,
					data : JSON.stringify(formData),
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
						 
									if(data.objPurchaseDetailsListVO !=null && data.objPurchaseDetailsListVO !=undefined){
										$.each(data.objPurchaseDetailsListVO, function(i, item) {
											objPurchaseDetailsListVO[cnt]=item;
											var itemSku='';
											var itemSkuDesc='';
											$.each(item.purchRwdsDtlListVO, function(i, purchase){
												itemSku=itemSku+purchase.skuNumber+',';
												itemSkuDesc=itemSkuDesc+purchase.itemDescription+',';
											});
											$.each(item.savingAmount, function(j, amount){
												Amount[j]=amount;
											});
											mkttrHTML += '<tr class="odd gradeX">'
												//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
												
												 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.tranId)+'\');" style="text-decoration:underline;padding-right:6px;">'
												 + checkUundefined(item.tranId) +'</a>';
											
											/*if (item.savingCategory != undefined &&
													item.savingCategory != null &&
													item.savingCategory.length > 0) {
												mkttrHTML += '<i id="OrderIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="orderdivId'+cnt+'" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
												 $.each(item.savingCategory, function(k, category){
													 if((checkUundefined(category))=='LOR')
													      category	='Large Order Rebate'; 
													 mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+toCamCase(removeUnderScore(checkUundefined(category)))+': $'+formatOrderAmt(checkUundefined(Amount[k])) +'</div>'
														 
												 });
												mkttrHTML += '</div>';
											}*/
											mkttrHTML += '</td>';
											
											mkttrHTML += '<td class="datatablesTd">';
											if(item.tranId.search("POS")!=-1){
												mkttrHTML+='<span style="color:rgb(0, 76, 116);">Retail</span>';
											}else{

												mkttrHTML+='Staples.com';
											}
											mkttrHTML += '</td>';
												mkttrHTML +='<td class="datatablesTd">'
												 + checkUundefined(item.tranDate) +'</td>'
												 +'<td>'+ checkUundefined(Number(item.orderLineCount) )+'</td>'
												 +'<td class="datatablesTd formatClsOrd">'
												 + formatNum(chkNegAmount(checkUundefined(item.netSpendAmount))) +'</td>'
												 /*+'<td class="datatablesTd">'
												 + checkUundefined(item.orderContact) +'</td>'*/
												 +'<td class="datatablesTd">'
												 + checkUundefined(itemSku) +'</td>'
												 +'<td class="datatablesTd">'
												 + checkUundefined(itemSkuDesc) +'</td>'
												 +'</tr>';
											
											  cnt++;
											  
										});
										OrderDeatilCount = cnt-1;
									}
									//alert(isFromReturned+"--"+retFound+"--");
									if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('N' == isFromReturned)){
										/*populateMonthYearData(data,monthNew,yearNew);
										 populateCatData(data);
										 $("#yearSel option[value="+monthNew+""+yearNew+"]").attr("selected","selected");
										 $('#datepickerTEXT').val(monthNameArr[monthNew-1]+" / "+yearNew);
								          $('#datepickerTEXT').css("font-weight","bold")
								          $('#datepickerTEXT').css("font-family","Hevletica")
								          $('#datepickerTEXT').css("font-size","14px");
											 $('#orderDetailsTabId').html("");*/
										$('#orderDetailsTabId').html(
												'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
														
			                                           // +'<th>Details</th>'
			                                            
			                                            +'<th>Order No.</th>'
			                                           // +'<th style="color: red">RETURNED</th>'
			                                            +'<th>Retail / Staples.com</th>'
			                                            +'<th>Order Date</th>'
			                                            +'<th>No. Of Items</th>'
			                                            +'<th>Order Total</th>'
			                                           // +'<th>Order Contact</th>'
			                                            +'<th>Item SKU</th>'
			                                            +'<th>Item Desc</th>'
														+ '</tr>'
														+ '</thead><tbody>' + mkttrHTML
														+ '</tbody></table>');
										$("#dataTables-order_processing").css("display","none");
										$('#dataTables-order').dataTable({
											"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
											"order": [[2,"desc"],[1,"desc"]],
											"bProcessing": true,
											"oLanguage": { "sSearch": "Filter: "},
											"aoColumns": [{"bSortable": true},//{"bSortable": true,"visible":true},
											{"bSortable":true},
											{"bSortable": true,"stype" : "date"},
											{"bSortable": true},
											{"bSortable": true},
											{"bSortable": true,"visible":false},
											{"bSortable": true,"visible":false}],
											"fnDrawCallback": function( oSettings ) {
												
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
										 populateCatData(data);
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


function populateNotificationData(){
    
	  var formData={};
	  var custid=$("#reqCustNum").val();
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getNotifications/"+custid ,
				//url : "/getNotifications/"+custid ,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					setCustomerRight(custid);
					//setCustomerRightAngular(custid);
					var GHTML = "";
					var RHTML = "";
					var EHTML = "";
					 $("#growUlId").html("");
					 $("#retUlId").html("");
					 $("#expUlId").html("");
					 $("#actionStat").css("display","none");
					 $("#notiId").css("display","block");
					if(data != undefined && data !=null){ 
						if(data.notiInfoVOList !=null && data.notiInfoVOList !=undefined && data.notiInfoVOList !=''){ 
							$.each(data.notiInfoVOList, function(i, item) {
								if(item.sementType=='Growth')
								 GHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;">'+item.segmentDesc+'<span class="pull-right text-muted small"></span></li>'
								else if(item.sementType=='Retention')
								 RHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;">'+item.segmentDesc+'<span class="pull-right text-muted small"></span></li>'
								else if(item.sementType=='Expansion')	
								 EHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;">'+item.segmentDesc+'<span class="pull-right text-muted small"></span></li>'
								
							});
						}else{ 
							//mkttrHTML +='<li class="list-group-item" style="text-align:center !important;padding:4px 12px !important" ><span class="text-muted small"><em>No Data Found...</em></span></li>'
						}
					}
					if(GHTML!='')
					    $("#growUlId").html(GHTML);
					else
						$("#growthPlayId").css("display","none")
						//$("#growUlId").html('<li class="list-group-item" style="height: auto!important;padding: 3px 14px !important;font-family:Helvetica;font-size: 13px;" ><span class="text-muted small"><em>No Data Found...</em></span></li>');
					if(RHTML!='')
						$("#retUlId").html(RHTML);
					else
						$("#retPlayId").css("display","none")
						//$("#retUlId").html('<li class="list-group-item" style="height: auto!important;padding: 3px 14px !important;font-family:Helvetica;font-size: 13px;" ><span class="text-muted small"><em>No Data Found...</em></span></li>');	
					if(EHTML!='')
						$("#expUlId").html(EHTML);
					else
						$("#expPlayId").css("display","none")
						//$("#expUlId").html('<li class="list-group-item" style="height: auto!important;padding: 3px 14px !important;font-family:Helvetica;font-size: 13px;" ><span class="text-muted small"><em>No Data Found...</em></span></li>');
						if(GHTML=='' && RHTML=='' && EHTML==''){
			            	$("#playdivId").css("display","none");
			            	$("#actionStat").css("display","block");
			            	$("#notiId").css("display","none");
						}
					 playSecData=$('#ulPlaySec').html();
					 $('#growUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
					 $('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
					 $('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
					}
                    
			})	

}

 function setCustomerRight(cusId){
	  var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getCustomerRight/"+cusId ,
				//url : "/getCustomerRight/"+cusId ,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var GHTML = "";
					$("#rightId").html("");
					
		           
		           
					if(data != undefined && data !=null && data !=''){
						if(undefined !=data.valueAttributionScore && null != data.valueAttributionScore){
							
								    
								    
						}
						if(undefined !=data.qualificationScore && null != data.qualificationScore){
							$('#exampleQual').html(data.qualificationScore);
							
							var qualScore ='<div style="padding: 18px 20px 10px 20px;"><div style=""><span style="font-family: Helvetica !important; font-size: 13px !important;font-weight: bold;color: #4d4d4d;"></span></div>'
							+'<div id="AHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span id="A" style="  color: green;font-weight: bold;font-size: 13px;font-family:Helvetica;">A - Potential for at least 15K annual growth</span></div>'
							+'<div id="BHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span id="B" style="  color: #ff7A00;font-weight: bold;font-size: 13px;font-family:Helvetica;">B - Potential for at least 8K annual growth</span></div>'
							+'<div id="CHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span id="C" style="  color: #747676;font-weight: bold;font-size: 13px;font-family:Helvetica;">C - Potential for at least 3K annual growth</span></div>'
							+'<div id="DHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span id="D" style="  color: #000000;font-weight: bold;font-size: 13px;font-family:Helvetica;">D - DEAD account</span></div>'
							+'<div id="EHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span id="E" style="  color: red;font-weight: bold;font-size: 13px;font-family:Helvetica;">E - No growth</span></div></div>'
							$("#tempDataQual").html(qualScore);
							var id=data.qualificationScore;
							if(id != 'NA'){
								$("#exampleQual").css("background-color",document.getElementById(id).style.color)
								$("div#"+id+"Head").css("background-color",document.getElementById(id).style.color)
							}else {
								$("#exampleQual").css("background-color","#286090")
							}
                            $("div#"+id+"Head").css("color","#fff")
                            $("span#"+id).css("color","#fff")
                            $("div#"+id+"Head").css("padding","10px")
							 $('#exampleQual').popover({
								 html : true,
								 placement: 'bottom',
								content : $("#tempDataQual").html()
							 });
						}else{
							$('#exampleQual').remove();
						}
						if(undefined !=data.cusRight){
							var ltvScore = parseInt(data.cusRight);
							var onesDigit;
							var tensDigit;
							var thousandsDigit;
							var quotient;
							
							onesDigit = ltvScore%10;
							
							quotient = parseInt(ltvScore/10);
							
							tensDigit = quotient%10;
							
							quotient = parseInt(quotient/10);
							
							thousandsDigit = quotient%10;
							
							var f = thousandsDigit*0.25 + tensDigit*0.25 + onesDigit*0.5;
							
							
							if(f > 3 && f <= 4){
								$("#rightId").html(" "+'ACTIVE');
								$("#rightId").attr("class","btn btn-primary btn-xs");
								$("#rightId").css("font-weight","bold");
							    //$("#rightId").css("background-color","darkblue"); 
								}
							else if(f >=0  && f <= 1){
								$("#rightId").html(" "+'INACTIVE');
								$("#rightId").attr("class","btn btn-info btn-xs");
								$("#rightId").css("font-weight","bold");
								}
							else if(f > 2 && f <= 3){
								$("#rightId").html('MODERATE');
								$("#rightId").attr("class","btn btn-warning btn-xs");
								$("#rightId").css("background-color","#ff8533");
								$("#rightId").css("font-weight","bold");
								//$("#rightId").attr("class","btn btn-primary btn-xs");
								}
							else if(f > 4 && f <= 5){
								$("#rightId").html(" "+'VERY ACTIVE');
								$("#rightId").attr("class","btn btn-success btn-xs");
								$("#rightId").css("font-weight","bold");
								//$("#rightId").css("background-color","green");
								}
							else if(f > 1 && f <= 2){ 
								$("#rightId").html(" "+'FADING');
								$("#rightId").attr("class","btn btn-danger btn-xs");
								$("#rightId").css("font-weight","bold");
								//$("#rightId").css("background-color","#cc0000");
								}
						} else {
							$("#rightId").html('NA');
							$("#rightId").attr("class","btn btn-primary btn-xs");
							$("#rightId").css("font-weight","bold");
						}
					}
				}
			});

 }
 function setCustomerRightAngular(cusId){/*
	 alert("in")
	 heliosModule.controller('statController', function ($scope,$http) {
		 alert("ok")
		  var custid=$("#reqCustNum").val();
		 alert(ctx+"/getCustomerRight/"+cusid)
		 var custid=$("#reqCustNum").val();
		 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		 alert("ok1")
		 $http.get(ctx+"/getCustomerRight/"+cusid).
	     success(function(data) {
	         $scope.statuses = data;
	         for(var i=0;i<$scope.tasks.length;i++){
	             if($scope.tasks[i].taskStatus=='COMPLETED'){
	              $scope.selection.push($scope.tasks[i].taskId);
	         }
	         }
	         alert(data)
	         alert(data.cusRight)
	 });

	});


 */}

	 

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
function openCustomerProfile(){ 
	  var formData={};
	  $("#cusId").css("display","block");
	  $("#showHideId1").prop('class','fa fa-chevron-down');
}
function openUrl(url){
	 var win = window.open(url, '_blank');
	  win.focus();
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
        return '$'+commaFormatedVal;
    } else {
          return '';
    }
}

function openShipToDetails(){
	 // log user activity; viwe ship to data
	logUserActivity(2006, 'View Ship To Data');
	
	  var formData={};
	  var dt = geDataRefreshTime('MV_SHIP_TO_INFO');
		//alert(dt);
	  $("#userDiv").prepend($("#shipToId"));
		$("#updateDateValueShip span").html(dt+" ET");
	  $("#shipToId").css("display","block");
	  $("#shipToContent").css("display","block");
	  $("#showHideIdShipTo").prop('class','fa fa-times');
	  commonScroll('shipToId');
	  var custid= $("#reqCustNum").val();
	  $("#dataTables-exampleShipTo_processing").html('<div id="example_processing_shipto" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-exampleShipTo_processing").css("display","block");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getShipToData/"+custid,
			 //url : "/getShipToData/"+custid,
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
					 $('#shipToTableId').html("");
					 var name='';
								if(true){
									//alert("inside1");
									$.each(data, function(i, item) {
										//customerAddress
										// onmouseout="document.getElementById('tt1').style.display='none'"  title="'+checkUundefined(item.customerAddress)+'"
										
										/*<div class="listing listing-radius listing-success">
						                <div class="shape">
						                    <div class="shape-text">50%</div>
						                </div>
						                <div id="divId'+cnt+'" class="listing-content">
						                    <h3 class="lead">Discount listing</h3>
						                    <p>Buy now - 50% off.</p>
						                </div>
						            </div>*/
									var shipId=""+item.shipToId;
										mkttrHTML += '<tr class="odd gradeX">'
											+'<td class="datatablesTd">'
											+'<a  style="padding-right:8px;text-decoration:underline" href="javascript:openShipToDetailsInfo(\''+shipId+'\','+custid+')" '
											+'>'
											 + checkUundefined(item.shipToId) +'</a><i id="shipToIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="divId'+cnt+'" class="toolTip">'
											 +'<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.customerAddress)+'</div>'
											 +'<div style="font-size:12px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.customerCity)+'</div>'
											 +'<div style="font-size:12px;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+checkUundefined(item.customerState)+'-'+checkUundefined(item.customerZip)+'</div>'
											 +'</div></td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.orderNoCurYr) +'</td>'
											 +'<td class="datatablesTd formatClsShip">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.totalSpendCurYr))) +'</td>'	
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.orderNoLastYr) +'</td>'
											 +'<td class="datatablesTd formatClsShip">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.totalSpendLastYr))) +'</td>'
											 +'<td class="datatablesTd">'
											 + calculateYOY(item.totalSpendCurYr,item.totalSpendLastYr) +'</td>'
											 
											 +'</tr>';
										
										 cnt++;
									});
									ShipToDeatilCount=cnt-1;
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
        return '$'+commaFormatedVal;
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
        if (yoyVal < 0  && parseFloat(n) < parseFloat(n1)){
        	//yoyVal= 100;
        	return yoyVal+'%';
        }else if (yoyVal < 0 ){
        	yoyVal= 100;
        	return '-'+yoyVal+'%';
        }else if(yoyVal > 100){
        	yoyVal = 100
        	return '+'+yoyVal+'%';
        }else {
        	return yoyVal+'%';;
        }
      
  } else {
	  return '';
  }
}
function formatCallOrder(callorder,cnt){
	   if((undefined !=callorder) && ('' !=callorder) &&  ('-'!=callorder))
	    return cnt;
	   else
		return callorder;
}
function checkUundefinedNullBlankZero(val) {
	if ((undefined != val) &&   ('null'!=val) && (null !=val) && ('0' !=val) && (0 !=val) ) {
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
	var formData={'searchText' : searchText, 'acctId': acctId , 'custNum': $('#query').val()};
	$.ajax({
		dataType: "json",
		url : ctx+"/getCustomerNumber", 
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			
			if(data != null && data != undefined && data != 'failure' && data != 'holdout'){
				if(data.indexOf("-")!=-1){
					assignGrp=data.split("-")[1];
					data=data.split("-")[0];
					if(undefined !=assignGrp && ""!=assignGrp && 'SAM'==assignGrp){
						opencustDetails(data);
					}else if(undefined !=assignGrp && ""!=assignGrp && 'SAMD'==assignGrp){
						opencustDetailsSAM(data);
					}else{
						opencustDetailsMM(data);
					}	
				}
			}else if(data != null && data != undefined && data == 'holdout'){
				hideProgress();
				alert('This account is currently part of Helios Hold Out test group, please reach Helios business support for test details.');
			} else {
				hideProgress();
				//alert("Either the search citeria is wrong or the account manager is not matching.");
				alert("To search for a different customer on this screen the customer must be part of your Book of Business. Please enter the complete company name or customer number. To search for a different customer with partial information please do so via the To Do list.");
			}
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
	 $("#customerForm").attr("action","./home_template3")
		$("#reqCustNum").val(cNum);
	  $('#customerForm').submit();
		
}
function populateMonthChartData(firstYearData,secondYearDatadata,thirdYearData,yearsArr){
	
	var yr=$("#yrId").val();
	$("#mnthTitle").html('<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;"> YEARLY SPEND PER MONTH <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
	var firstYearAmntArr=new Array();
	var secondYearAmntArr=new Array();
	var thirdYearAmntArr=new Array();
	var monthArr=["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"];
	var bardata = [];
	var i;
	if(undefined == firstYearData.barDataSet || null == firstYearData.barDataSet || '' == firstYearData.barDataSet){
		firstYearAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
	}else{
		i=0;
		$.each(((firstYearData.barDataSet).data).rows, function(i, item) {
			firstYearAmntArr[i]=item.values;
					i++;
		});
		var firstYearArrLen=firstYearAmntArr.length;
		//alert(amntArrLen)
		for(var j=firstYearArrLen;j<monthArr.length;j++){
			firstYearAmntArr[j]=0;
		}
	}
	if(undefined == secondYearDatadata.barDataSet || null == secondYearDatadata.barDataSet || '' == secondYearDatadata.barDataSet){
		secondYearAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
	}else{
		i=0;
		$.each(((secondYearDatadata.barDataSet).data).rows, function(i, item) {
			secondYearAmntArr[i]=item.values;
					i++;
		});
		var secondYearArrLen=secondYearAmntArr.length;
		//alert(amntArrLen)
		for(var j=secondYearArrLen;j<monthArr.length;j++){
			secondYearAmntArr[j]=0;
		}
	}
	if(undefined == thirdYearData.barDataSet || null == thirdYearData.barDataSet || '' == thirdYearData.barDataSet){
		thirdYearAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
	}else{
		i=0;
		$.each(((thirdYearData.barDataSet).data).rows, function(i, item) {
			thirdYearAmntArr[i]=item.values;
					i++;
		});
		var thirdYearArrLen=thirdYearAmntArr.length;
		//alert(amntArrLen)
		for(var j=thirdYearArrLen;j<monthArr.length;j++){
			thirdYearAmntArr[j]=0;
		}
	}
	
	//bardata=getMonthAmountMapForThreeYears(monthArr,firstYearAmntArr,secondYearAmntArr,thirdYearAmntArr);
	alert(yearsArr);
	bardata=getMonthAmountMapForSam(monthArr,firstYearAmntArr);
	var chart = AmCharts.makeChart("chartdiv", {
	    "type": "serial",
	    "theme": "light",
	    "legend": {
	        "useGraphSettings": true,
	        "clickMarker": handleLegendClick,
	        "clickLabel": handleLegendClick
	    },
	    "dataProvider": bardata,
	    "startDuration": 0.5,
	    "graphs": [{
	        "balloonText": "Year: "+yearsArr[0]+", [[category]]: $[[value]]",
	        //"bullet": "round",
	        //"hidden": true,
	        
	        "title": yearsArr[0],
	        "type": "column",
	        "colorField": "color",
	        "fillAlphas": 0.85,
	        "lineAlpha": 0.1,
	        "topRadius":1,
	        "valueField": "year1"
			//"fillAlphas": 0
	    }],
	    "depth3D": 30,
		"angle": 50,
	    "chartCursor": {
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "month",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "axisAlpha": 0,
	        "fillAlpha": 0.05,
	        "fillColor": "#000000",
	        "gridAlpha": 0,
	        "position": "bottom"
	    },
	    "export": {
	    	"enabled": true,
	        "position": "bottom-right"
	     }
	});

	$("g text[text-anchor=end]").each(function(i,item){
		var htmlData=$(this).html();
		if(undefined== htmlData || ''==htmlData || ' '==htmlData){
			$(this).remove();
		}
	})
	$("g path[fill=#67b7dc]").css("display","none");	
	$("g text[text-anchor=end] tspan").each(function(i,item){
		var htmlData=$(this).html();
		if(undefined !=htmlData && ''!=htmlData && ' '!=htmlData && htmlData.indexOf("$")==-1){
			$(this).html('$'+htmlData);
		}
	});
	//hideProgress();
}

function populateMonthChartDataSam(firstYearData,year){

	$("#mnthTitle").html('<span class="letterSpaceOR" style="font-size: 16px;font-weight: 600;color: #555;"> YEARLY SPEND PER MONTH <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
	var firstYearAmntArr=new Array();
	var retailAmntArr=new Array();
	var onlineAmntArr=new Array();
	var monthArr=new Array();
	var bardata = [];
	var cnt=0,totalCnt=0,retailCnt=0,onlineCnt=0;
	if(undefined == firstYearData.barDataSet || null == firstYearData.barDataSet || '' == firstYearData.barDataSet){
		firstYearAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
		retailAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
		onlineAmntArr=["0","0","0","0","0","0","0","0","0","0","0","0"];
	}else{
		$.each(((firstYearData.barDataSet).data).rows, function(i, item) {
			firstYearAmntArr[totalCnt]=item.values;
			totalCnt++;
		});
		$.each(((firstYearData.barDataSetRetail).data).rows, function(i, item) {
			retailAmntArr[retailCnt]=item.values;
					retailCnt++;
		});
		$.each(((firstYearData.barDataSetOnline).data).rows, function(i, item) {
			onlineAmntArr[onlineCnt]=item.values;
					onlineCnt++;
		});
		$.each(firstYearData.barDataSetKeys, function(i, item) {
			monthArr[cnt]=item;
			cnt++;
		});
		
		var firstYearArrLen=firstYearAmntArr.length;
		for(var j=firstYearArrLen;j<monthArr.length;j++){
			firstYearAmntArr[j]=0;
			retailAmntArr[j]=0;
			onlineAmntArr[j]=0;
		}
		
	}
	
	bardata=getMonthAmountMapForSam(monthArr,firstYearAmntArr,retailAmntArr,onlineAmntArr);
	var chart = AmCharts.makeChart("chartdiv", {
	    "type": "serial",
	    "theme": "light",
	    "legend": {
	        "useGraphSettings": true,
	        "clickMarker": handleLegendClick,
	        "clickLabel": handleLegendClick
	    },
	    "balloon": {
	    	
	    	"horizontalPadding":10
	    	/*"offsetX":0,
	    	"offsetY":8*/
	    },
	    "dataProvider": bardata,
	    "startDuration": 0.5,
	    "RetailField":"retailSale",
	    "OnlineField":"onlineSale",
	    "graphs": [{
	        "balloonText": "<div style='text-align:left;font-weight:bold;color:#434343;'>Period: [[category]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Total Spend: $[[value]]</div><div style='text-align:left;font-weight:bold;color:#434343;'>Retail: $[[retailSale]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Staples.com: $[[onlineSale]]</div>",
	       "taxtAlign":"left",
	        //"bullet": "round",
	        //"hidden": true,
	        
	        /*"title": year,*/
	        "type": "column",
	        "colorField": "color",
	        "fillAlphas": 0.85,
	        "lineAlpha": 0.1,
	        "topRadius":1,
	        "valueField": "year1"
			//"fillAlphas": 0
	    }],
	    "depth3D": 30,
		"angle": 50,
	    "chartCursor": {
	        "cursorAlpha": 0,
	        "zoomable": false
	    },
	    "categoryField": "month",
	    "categoryAxis": {
	        "gridPosition": "start",
	        "axisAlpha": 0,
	        "fillAlpha": 0.05,
	        "fillColor": "#000000",
	        "gridAlpha": 0,
	        "position": "bottom"
	    },
	    "export": {
	    	"enabled": true,
	        "position": "bottom-right"
	     }
	});

	$("g text[text-anchor=end]").each(function(i,item){
		var htmlData=$(this).html();
		if(undefined== htmlData || ''==htmlData || ' '==htmlData){
			$(this).remove();
		}
	})
	$("g path[fill=#67b7dc]").css("display","none");	
	$("g text[text-anchor=end] tspan").each(function(i,item){
		var htmlData=$(this).html();
		if(undefined !=htmlData && ''!=htmlData && ' '!=htmlData && htmlData.indexOf("$")==-1){
			$(this).html('$'+htmlData);
		}
	});
	hideProgress();
}
function handleLegendClick( graph ) {
	  var chart = graph.chart;
	  for( var i = 0; i < chart.graphs.length; i++ ) {
	    if ( graph.id == chart.graphs[i].id ){ 
	    	 if(chart.graphs[i].hidden==false){ 
				    chart.hideGraph(chart.graphs[i]);}
	    	 else
	    		 chart.showGraph(chart.graphs[i]); 
	    }
	    else{
	    	if(chart.graphs[i].hidden==false){ 
	    	chart.showGraph(chart.graphs[i]);}
	    }
	  }
	  $("g text[text-anchor=end]").each(function(i,item){
			var htmlData=$(this).html();
			if(undefined== htmlData || ''==htmlData || ' '==htmlData){
				$(this).remove();
			}
		})
	  $("g path[fill=#67b7dc]").css("display","none");
	  $("g path[fill=#AAB3B3]").css("display","none");
	  $("g text[text-anchor=end] tspan").each(function(i,item){
			var htmlData=$(this).html();
			if(undefined !=htmlData && ''!=htmlData && ' '!=htmlData && htmlData.indexOf("$")==-1){
				$(this).html('$'+htmlData);
			}
		});
	  // return false so that default action is canceled
	  return false;
	}


function getLatestFiscalDate(){
	 
	  
	  var custid= $("#reqCustNum").val();
	var formData={};
	//alert(custid)
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLatestFiscalDate/"+custid,
				//url : "/getLatestFiscalDate/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					//alert(data);
					latestDate=data;
					var yearDropDown =$("#yrId")
					yearDropDown.empty();
					var thisYear = latestDate.substring(2,6);
				    var startYear =thisYear-2;
				    for (var i = startYear; i <= thisYear; i++) {
				    
				    //var year = thisYear - i;
				    
				    $('<option>', { value: i, text: i }).appendTo("#yrId");
				    
				    }
					latestDate=data;
					populateNotificationData();
					populateDataOnLoad(data,'RETAILONLINE');
				}
				}

			})	
 

}

function getLatestFiscalDateForLead(){
	  var custid= $("#reqCustNum").val();
	var formData={};
	//alert(ctx+"/getLatestFiscalDateForLead/"+custid)
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLatestFiscalDateForLead/"+custid,
				//url : "/getLatestFiscalDate/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					latestDateForLead=data;
					var yearDropDown =$("#yrId")
					yearDropDown.empty();
					var thisYear = latestDateForLead.substring(2,6);
				    var startYear =thisYear-2;
				    for (var i = startYear; i <= thisYear; i++) {
				    
				    //var year = thisYear - i;
				    
				    $('<option>', { value: i, text: i }).appendTo("#yrId");
				    
				    }
					latestDateForLead=data;
					
				}
					populateNotificationData();
					populateDataOnLoad(data,'RETAILONLINE');
				}

			})	
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
					//getLatestOrderReturnedDate();
				}
				}

			})	
			return latestDateforOrder;

}


function getLatestOrderReturnedDate(){

	var custid= $("#reqCustNum").val();
	 var latestOrderReturnedDate="";
	 var latestOrdDate="";
	 var retmatches='',ordmatches='';
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLatestOrderReturnedDate/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					hideProgress();
				if	(data !=null && data != undefined && data !='null'){
					latestOrderReturnedDate=data;
					if(data.indexOf("/")!=-1){ 
						if (null != latestOrderReturnedDate) {
						    retmatches = latestOrderReturnedDate.match(/\d+/g);
							if (retmatches != null) {
								//var days=daydiff(parseDate(latestOrderReturnedDate), parseDate(latestOrdDate));
									var dateArr=latestOrderReturnedDate.split("/");
									var retmnth=dateArr[0];
									var retyear=dateArr[2];
									monthYear=retmnth+retyear;
									$("#retId").attr('href','javascript:openReturn(\''+monthYear+'\',\'ALL\',\'Y\')');
							}else{
								$("#retId").removeAttr("href");
								$("#retId").css({"text-decoration":"none",
							 		"cursor":"default",
							 		"color":"#0066c0"
							 		});
							}
						}else{
							$("#retId").removeAttr("href");
							 $("#retId").css({"text-decoration":"none",
							 		"cursor":"default",
							 		"color":"#0066c0"
							 		});
						}
					 }
					}else{
					$("#retId").removeAttr("href");
					 $("#retId").css({"text-decoration":"none",
					 		"cursor":"default",
					 		"color":"#0066c0"
					 		});
				}
				}

			})	
			return latestOrderReturnedDate;

}
function getMonth(month){
	var Months = ["January","February","March","April","May","June","July","August","September","October","November","December"];
	//alert(parseInt(month));
	return Months[parseInt(month)];
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

function openRecommendations(){
	//clearCookie();
  $("#customerForm").attr("action","./recommActionSam")
  $('#customerForm').submit();
}
function openStoreLocator(){
	//clearCookie();
  $("#customerForm").attr("action","./storeLocActionSam")
  $('#customerForm').submit();
}
function opeMaintainRole(){
	$("#customerForm").attr("action","./maintainAction")
	 $('#customerForm').submit();
}
function populateQuickSearchData(){
	 var formData={};
	 var selPlayType=$("#filterBy").val();
	 var sliderSubPlaysItem=$("#sliderSubPlaysItem").val();
	 var custid=$("#accId").val();
	 var selectedQualScore=$("#selectedQualScore").val();
	 var selectedSegScore=$("#selectedSegScore").val();
	 $('#dataTables-QuickSearch').dataTable( {
	        /*"bProcessing": true,*/
	        "bDestroy" : true,
	        "oLanguage": { "sSearch": "Filter: "},
	        "sAjaxSource": ctx+"/getAllSamCustomers/"+custid+"/"+selPlayType,
	        "fnServerParams": function (aoData) { 
		    	   aoData.push({ "name": "selectedSubPlays", "value": sliderSubPlaysItem});
		    	   aoData.push({ "name": "selectedQualScore", "value": selectedQualScore});
		    	   aoData.push({ "name": "selectedSegScore", "value": selectedSegScore});
		    	   aoData.push({ "name": "repRoleCode", "value": ""});
		    	   aoData.push({ "name": "accType", "value":"NA"});
		    	   },
	        "processing":true,
			"bServerSide" : true,
			"responsive": true,
			"iDisplayLength" : 5,
			"iDisplayStart": iStart,
			//"sScrollY": "200px", /* fixed header */
			
			//'bJQueryUI' : true,
			"lengthChange": false,
			"paging" :true,
			"pagingType": "simple_numbers",
			"info" : true,
			"ordering": false,
			"searching": false,
			//"bDestroy" : true,
			/*"bInfo" : true,*/
			"dataType": 'jsonp',
			'sServerMethod' : "POST",
			/*"sAjaxSource" : ctxNew + "/api/commlog/" + id + '?' + 'filterInput='+encodeURIComponent(JSON.stringify(formData))
					,*/
			
			/*"sPaginationType": "full_numbers",*/
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
									 
									return '<li class="media" onclick="opencustDetails('+checkUundefinedNullBlankZero(full.LeadCustomerNumber)+')">'
										+'<div class="media-status">'
										//+'<span class="badge badge-warning">'+checkUundefinedNullBlankZero(full.callOrder)+'</span>'
									    +'</div>'
									    //+'<img class="media-object" src="../../assets/admin/layout/img/avatar6.jpg" alt="...">'
									    +'<div class="media-body" id="">'
										+'<h4 class="media-heading" style="font-family:Helvetica;font-weight:bold;font-size:14px; color: #ddd; ">'+ checkUundefinedNullBlankZero(full.LeadCustomerNumber)+'</h4>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+ checkUundefined(full.LeadContractType)+'</div>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+checkUundefined(full.LeadCompanyName)+'</div>'
									    +'</div>'
								        +'</li>';
								 }
							}							]
							/*"oLanguage": { "sInfo": "Showing Records _START_ - _END_ Click the arrow to view more records",
								"sInfoEmpty": "0 records",				  
								"sInfoFiltered": ""	}*/

	    } );

	 

	  
}

function showTrainingPopUp() {
	$('#training_modal').modal({
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
function showCallToActionPopUp() {
	$('#callToAction_PopUp').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}
function clearCookie(){
	$.cookie('sidebar_closed', '', { path: '/', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDasbhoard', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDashboard', expires:-1 });
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
function formatPhone(val) {
	if(undefined!=val && ''!=val &&val.indexOf("(")==-1){
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
function formatYtd(){
	var maxDigToFormat='';
	var oldarr=new Array();
	var newarr=new Array();
	for(var i=0;i<3;i++){
		if((undefined != ($("#spend"+i).html())) && ('' != ($("#spend"+i).html()))){
		oldarr[i]=($("#spend"+i).html()).substr(1);
		newarr[i]=($("#spend"+i).html()).substr(1);
		}
	}
	var maxlenele=newarr.sort(function (a, b) { return b.length - a.length })[0];
	//alert(maxlenele+"----"+maxlenele.length);
	for(cnt=0;cnt<oldarr.length;cnt++){
		if(undefined !=maxlenele){
	    $("#spend"+cnt).html($.parseHTML(customYtdFormat(oldarr[cnt],maxlenele.length)));	
		//alert("OLD="+oldarr[cnt]+" NEW="+customYtdFormat(oldarr[cnt],maxlenele.length))
		}
	}
}
function customYtdFormat(n, width, z) {
	  z = z || '&nbsp;&nbsp;';
	  n = n + '';
	  var v=(n.length >= width ? n : new Array(width - n.length + 1).join(z) + n);
	  //alert("b->"+v);
	  if(width>6 & v.indexOf(",")==-1)
		  v=v.slice(12);
		 // alert("af->"+v);
	  return "$"+v;
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
function showHideAcIac(){
    //e.preventDefault();
    var $Ac =$('#managerInfo').find("#dtl1");
    var $Iac = $('#managerInfo').find("#dtl2");
    //alert($Iac.is(":visible"));
    if($Iac.is(":visible")==false){// alert("ok")
    $Iac.slideDown();
    $Ac.slideUp();
    $("#viewRepBtn").html("View AC Details &raquo;");
    } else{
    $Iac.slideUp();
    $Ac.slideDown();
    $("#viewRepBtn").html("View "+$("#dtl2").find("#repVal").html()+" Details &raquo;");
    }
}
function retFoundChk(val){
	if((undefined!=val) && ('' != val) && (val=='X')){
		retFound='Y';
	}
	return val;
}
function openOrderDetailsForReturned(month,Yr,catid){
	// log user activity; view order list
	logUserActivity(2004, 'View Order List');
	var Amount = new Array();
	var monthYr=month+""+Yr;
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
      //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
      $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-order_processing").css("display","block");
	  //alert("/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange",
				//url : "/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange",
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) { 
					 var cnt=0;
					var mkttrHTML = "";
								if(data.objPurchaseDetailsListVO !=null && data.objPurchaseDetailsListVO !=undefined){
									$.each(data.objPurchaseDetailsListVO, function(i, item) { 
										objPurchaseDetailsListVO[cnt]=item;
										var itemSku='';
										var itemSkuDesc='';
										$.each(item.purchRwdsDtlListVO, function(i, purchase){
											itemSku=itemSku+purchase.skuNumber+',';
											itemSkuDesc=itemSkuDesc+purchase.itemDescription+','; 
										});
										
										$.each(item.savingAmount, function(j, amount){
											Amount[j]=amount;
										});
										
										mkttrHTML += '<tr class="odd gradeX">'
											//+'<td><img width="25" height="25"  src="./resources/img/zoom.png" onclick="javascript:openOrderDetailsInfo('+cnt+');"/></td>'
											
											 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');"  style="text-decoration:underline;padding-right:6px;">'
											 + checkUundefined(item.orderNumber) +'</a>';
										
										if (item.savingCategory != undefined &&
												item.savingCategory != null &&
												item.savingCategory.length > 0) {
											mkttrHTML += '<i id="OrderIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="orderdivId'+cnt+'" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
											 $.each(item.savingCategory, function(k, category){
												 if((checkUundefined(category))=='LOR')
												      category	='Large Order Rebate';
												 mkttrHTML +='<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'+toCamCase(removeUnderScore(checkUundefined(category)))+': $'+formatOrderAmt(checkUundefined(Amount[k])) +'</div>'
													 
											 });
											mkttrHTML += '</div>';
										}
										mkttrHTML += '</td>';
										mkttrHTML += '<td class="datatablesTd">';
										var returnedCount = 0;
										$.each(item.purchRwdsDtlListVO, function(i, purchase){
											if (purchase.netSpendAmount < 0) {
												returnedCount++;
												
												if (returnedCount == 1) {
													var ordnum=checkUundefined(item.orderNumber);
													mkttrHTML += ('<a href="javascript:openReturnedOrderDetailsInfo('+cnt+',\''+ordnum+'\');" style="text-decoration:underline;padding-right:6px;">X</a>');
												}
											}
										});
										mkttrHTML += '</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(item.tranDate) +'</td>'
											 +'<td>'+ checkUundefined(Number(item.orderLineCount) - returnedCount )+'</td>'
											 +'<td class="datatablesTd formatClsOrd">'
											 + chkNegAmount(formatNum(checkUundefined(item.netSpendAmount))) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(item.orderContact) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(itemSku) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(itemSkuDesc) +'</td>'
											 +'</tr>';
										
										  cnt++;
									});
									OrderDeatilCount = cnt-1;
								}
					$('#orderDetailsTabId').html("");
					$('#orderDetailsTabId').html(
							'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
									
                                   // +'<th>Details</th>'
                                   
                                    +'<th>Order No.</th>'
                                    +'<th style="color: red">RETURNED</th>'
                                    +'<th>Order Date</th>'
                                    +'<th>No. Of Items</th>'
                                    +'<th>Order Total</th>'
                                    +'<th>Order Contact</th>'
                                    +'<th>Item SKU</th>'
                                    +'<th>Item Desc</th>'
									+ '</tr>'
									+ '</thead><tbody>' + mkttrHTML
									+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>Premium Savings shown here <i id="" class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;"></i> reflects April 16,2016 forward.</div>');
					$("#dataTables-order_processing").css("display","none");
					
						 $("#yearSel option[value="+month+""+Yr+"]").attr("selected","selected");
						 $('#datepickerTEXT').val(monthNameArr[month-1]+" / "+Yr);
			          $('#datepickerTEXT').css("font-weight","bold")
			          $('#datepickerTEXT').css("font-family","Hevletica")
			          $('#datepickerTEXT').css("font-size","14px");
					$('#dataTables-order').dataTable({
						"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
						"order": [],
						"bProcessing": true,
						"oLanguage": { "sSearch": "Filter: "},
						"aoColumns": [{"bSortable": true},{"bSortable": true, "visible":true},
						{"bSortable": true,"bSearchable": false},
						{"bSortable": true,"bSearchable": false},
						{"bSortable": true,"bSearchable": false},
						{"bSortable": true,"bSearchable": true},
						{"bSortable": true , "visible":false},
						{"bSortable": true, "visible":false}],
						"fnDrawCallback": function( oSettings ) {
							
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
						   }
					}).fnDraw();

										
								$("#dataTables-order td").css("padding-left","10px");
								$("#dataTables-order td").css("WORD_BREAK","BREAK-ALL");
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
function openOrderDetailsForReturn(){
	retFound='N';
	openOrderDetails('Y');
}
function openOutlook(email) {
	if(undefined != email && '' !=email)
    window.location.href ="mailto:"+email;
}
function getColor(label){
	var labelcolormap = {"Facilities":"#2D435E","Office Supplies":"#DD1D1D","Ink and Toner":"#52C5D0","Paper":"#FF9A2C","Technology":"#FCD78D","Furniture":"#9CE2CF","Mail and Ship":"#C8CBD0","Print":"#417505","Promo":"darkseagreen","All Other Products":"#5F9EA0"};
	var color=labelcolormap[label];
	if(undefined !=color)
		return color;
	else
		return '#F96919'; 
}
function getCellstructure(cell1Data,cell2Data,cell3Data,rowNum,total){
	var cellData=''; 
	if(undefined !=total && '$0.00' !=total){
	cellData = '<table border="0"><tbody>'
	    +'<tr><td style="border:none;line-height:2.5 !important;float:right;padding-right:5%;">'+cell1Data+'</td></tr>'
	    +'<tr class="collapse ORrow retailCls" style="background-color: #598b9a;"><td class="retailCls" style="border:none;padding-bottom: 0px; line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'+cell2Data+'</td></tr>'
	    +'<tr class="collapse ORrow midrow" style=""><td class="midrow" style="border:none;height: 3px;"></td></tr>'
	    +'<tr class="collapse ORrow onlineCls" style="background-color: #808080;"><td class="onlineCls" style="border:none;padding-bottom: 0px;line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'+cell3Data+'</td></tr>'
	  +'</tbody></table>';
	}else{
		cellData = '<table border="0"><tbody>'
		    +'<tr><td style="border:none;line-height:3 !important;float:right;padding-right:5%;">'+cell1Data+'</td></tr>'
		    +'</tbody></table>';
	}
	return cellData;
}
function fetchSbaDiffDetails(custid){
	var formData={};
	var dt = geDataRefreshTime('ADOPTION_METRICS');
	$("#updateDateValueSBASum span").html(dt+" ET");
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getSbaDiffDetails/"+custid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					var htmlData="";
					if((data.bopisFlag !='NOTFOUND' && data.printsvsFlag != 'NOTFOUND') || (data.bopisFlag !='NOTFOUND') || (data.printsvsFlag != 'NOTFOUND')){
					htmlData=htmlData +' <table id="gitBuilds" class="table table-bordered">'
                           +'<thead><!-- <tr id="head"> <th colspan="4">Staples Advantage Differentiators</th> </tr> -->'
                           +' <tr id="subhead">'
                           +'    <th  class="text-center">Feature</th>'
                           +'     <th class="text-center">Account</th>'
                           +'     <th>Feature</th>'
                           +'     <th>Account</th>'
                           +' </tr></thead>'
                 
                           +'<tbody><tr style=" background-color: #fff;">'
                           +' <td>'
                           +'   <a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Use for same day or emergency needs" style="text-decoration:none;color:#0066c0;">BOPiS</a></td>';
					      if(data.bopisFlag !=''){
						   htmlData=generateCell(data.bopisFlag , htmlData, 'bopis');
					      }else{
								htmlData=htmlData+'<td></td>';
					      }
					      htmlData=htmlData +'<td><a id="retId" href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Know the status- Submit and track returns easily" style="text-decoration:underline;">Online Returns</a></td>';
					      if(data.returnFlag !='' && data.returnFlag =='Y'){
					    	  htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
					      }else if(data.returnFlag !='' && data.returnFlag =='N'){
					    	  htmlData=htmlData+'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>'; 
					      }else{
					    	  htmlData=htmlData+'<td></td>';
					      }
                           htmlData=htmlData +'</tr>'
                           +'<tr>'
                           +'    <td><a id="shopId" href="javascript:openOrderDetails(\'N\');" data-placement="left" data-toggle="tooltip" title="Make it easy for customers to reorder favorite items" style="text-decoration:underline;">Shopping Lists</a></td>';
                           if(data.shoppingListFlag !='' && data.shoppingListFlag =='Y'){
                        	   htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
    					      }else if(data.shoppingListFlag !='' && data.shoppingListFlag =='N'){
    					    	  htmlData=htmlData +'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
    					      }else{
    					    	  htmlData=htmlData+'<td></td>';  
    					      }
                           
                           
                           htmlData=htmlData +'   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="All customized print & promotional items easily ordered online" style="text-decoration:none;color:#0066c0;">Print Services</a></td>';
                           //+'   <td><img src="./resources/img/DashGrey.png" class="img-circle" style="height:30px;" data-placement="left" data-toggle="tooltip" title="Customer is not eligible for this feature"></td>'
					      if(data.printsvsFlag !=''){
						   htmlData=generateCell(data.printsvsFlag , htmlData , 'print');
					      }else{
						   htmlData=htmlData+'<td></td>';
					      }
					      htmlData=htmlData+'</tr>'
                           +'<tr style="background-color: #fff;">'
                           +'   <td><a id="mob" style="text-decoration:none;color:#0066c0;cursor:default;" href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Take <u>SA.com</u> anywhere with the mobile app! Customers can download the free app for easy approval and easy re-ordering! (not available for punchout or eDiversity)'
                           //+'   <div style=text-align:left>1. Not a &quot;punch out&quot; customer</div><div style=text-align:left>2. Not an e-diversity customer</div>
                           +'">Mobile</a></td>';
					      if(data.mobileFlag !='' && data.mobileFlag =='Y'){
                       	   htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
   					      }else if(data.mobileFlag !='' && data.mobileFlag =='N'){
   					    	htmlData=htmlData+'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
   					      }else{
   					    	  htmlData=htmlData+'<td></td>';  
   					      }
                           
                           
					      htmlData=htmlData+'   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Easy customer friendly functionality to update their account" style="text-decoration:none;color:#0066c0;">Account Maintenance</a></td>';
					      if(data.orderMgmtFlag=='Y' || data.adminFlag =='Y'){
	                       	   htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
	   					      }else if(data.orderMgmtFlag =='N' && data.adminFlag =='N'){
	   					    	htmlData=htmlData+'<td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>';
	   					      }else{
	   					    	  htmlData=htmlData+'<td></td>';  
	   					      } 
                           
                           
					      htmlData=htmlData+'</tr>'
                           +'<tr>'
                           +'   <td><a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Product recommendations based on previous purchase history" style="text-decoration:none;color:#0066c0;">SA.com Recommendations</a></td>';
					      if(data.recomFlag !='' && data.recomFlag =='Y'){
	                       	   htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
	   					      }else if(data.recomFlag !='' && data.recomFlag =='N'){
	   					    	htmlData=htmlData+'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
	   					      }else{
	   					    	  htmlData=htmlData+'<td></td>';  
	   					      }
                           
                           
					      htmlData=htmlData+'   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Save more with <u>SA.com</u> Easy Alternatives" style="text-decoration:none;color:#0066c0;">Product Alternatives</a></td>';
					      if(data.productFlag !='' && data.productFlag =='Y'){
	                       	   htmlData=htmlData+'   <td><img src="./resources/img/Check.png" class="img-circle" style="height:30px;" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
	   					      }else if(data.productFlag !='' && data.productFlag =='N'){
	   					    	htmlData=htmlData+'   <td><img src="./resources/img/Cross.png" class="img-circle" style="height:30px;" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>';
	   					      }else{
	   					    	  htmlData=htmlData+'<td></td>';  
	   					      }
                           
                           
					      htmlData=htmlData+'</tr>'
                           +'</tbody>'
                           +'</table>'
                           +'<div style="font-size: 13px; color: rgb(0, 76, 116);">'
                           +'<span style="color: red">* </span> Flags are based on the '
                           +'previous 3 periods and refreshed weekly.'
                           +'</div>';
					      $("#saContent").html("");
					$("#saContent").html(htmlData);
					 if(data.returnFlag !='' && data.returnFlag =='Y'){
					  getLatestOrderReturnedDate();
					 }else{
						 $("#retId").removeAttr("href");
							$("#retId").css({"text-decoration":"none",
						 		"cursor":"default",
						 		"color":"#0066c0"
						 		});
						hideProgress();	
					 }
					 if(data.shoppingListFlag !='' && data.shoppingListFlag =='N'){
						 $("#shopId").removeAttr("href");
							$("#shopId").css({"text-decoration":"none",
						 		"cursor":"default",
						 		"color":"#0066c0"
						 		});
					 }
					} else {
						htmlData=htmlData+ '<table id="gitBuilds" class="table table-bordered">'
                        +' <thead>'
                        +'  <tr id="subhead">'
                        +'    <th  class="text-center">Feature</th>'
                        +'    <th class="text-center">Account</th>'
                        +'    <th>Feature</th>'
                        +'    <th>Account</th>'
                        +' </tr>'
                        +'</thead>'
                        +'<tbody>'
                        +'<tr style=" background-color: #fff;">'
                        +'    <td>'
                        +'    <a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Use for same day or emergency needs" style="text-decoration:none;color:#0066c0;">BOPiS</a></td>'
                        +'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                        +'			id="inp" class="form-control" type="text" disabled=""'
                        +'			value="No Data Available"'
                        +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                        +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                        +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                        +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'	<td><a id="retId" href="javascript:void();" style="text-decoration:none;" data-placement="bottom" data-toggle="tooltip" title="Know the status- Submit and track returns easily">Online Returns</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width:100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'</tr>'
                        	+'<tr>'
                        	+'<td><a id="shopId" href="javascript:void();" style="text-decoration:none;" data-placement="left" data-toggle="tooltip" title="Make it easy for customers to reorder favorite items">Shopping Lists</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="All customized print & promotional items easily ordered online" style="text-decoration:none;color:#0066c0;">Print Services</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'</tr>'
                        	+'<tr style="background-color: #fff;">'
                        	+'<td><a id="mob" style="text-decoration:none;color:#0066c0;cursor:default;" href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Take <u>SA.com</u> anywhere with the mobile app! Customers can download the free app for easy approval and easy re-ordering! (not available for punchout or eDiversity)'
                        	//+'<div style=text-align:left>1. Not a &quot;punch out&quot; customer</div><div style=text-align:left>2. Not an e-diversity customer</div>
                        	+'">Mobile</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Easy customer friendly functionality to update their account" style="text-decoration:none;color:#0066c0;">Account Maintenance</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'</tr>'
                        	+'<tr>'
                        	+'<td><a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Product recommendations based on previous purchase history" style="text-decoration:none;color:#0066c0;">SA.com Recommendations</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Save more with <u>SA.com</u> Easy Alternatives" style="text-decoration:none;color:#0066c0;">Product Alternatives</a></td>'
                        	+'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
                            +'			id="inp" class="form-control" type="text" disabled=""'
                            +'			value="No Data Available"'
                            +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
                            +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
                            +' color: #000; font-size: 12px; background-position: center; text-align: center;'
                            +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
                        	+'</tr>'
                        	+'</tbody>'
                        	+'</table>'
                        	+'<div style="font-size: 13px; color: rgb(0, 76, 116);">'
                        	+'<span style="color: red">* </span> Flags are based on the '
                        	+'previous 3 periods and refreshed weekly.'
                        	+'</div>';
						$("#saContent").html("");
						$("#saContent").html(htmlData);
						hideProgress();
					}
					//$("[data-toggle='tooltip']").tooltip();
					$("[data-toggle='tooltip']").tooltip({html:true});
					
				}
				
				}

			});	
}
function generateCell(flag,htmlData,feild){
	if(flag=='NOTFOUND' && feild=='bopis'){
		htmlData=htmlData +'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
    +'			id="inp" class="form-control" type="text" disabled=""'
    +'			value="No Data Available"'
    +'			style="letter-spacing: .1px; width: 115px; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
    +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
    +' color: #000; font-size: 12px; background-position: center; text-align: center;'
    +' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
	}else if(flag=='NOTFOUND' && feild=='print'){
		htmlData=htmlData +'		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
	    +'			id="inp" class="form-control" type="text" disabled=""'
	    +'			value="No Data Available"'
	    +'			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;' 
	    +' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
	    +' color: #000; font-size: 12px; background-position: center; text-align: center;'
	    +' font-weight: bold;" data-placement="left" data-toggle="tooltip" title="No data Available"></td>'
	}else if(flag=='Y' && feild=='bopis'){
		htmlData=htmlData +'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>'		
	}else if(flag=='Y' && feild=='print'){
		htmlData=htmlData +'   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>'		
	}else if(flag=='N' && feild=='bopis'){
		htmlData=htmlData +'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>'
	}else if(flag=='N' && feild=='print'){
		htmlData=htmlData +'   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>'
	}else if(flag=='NA' && feild=='bopis'){
		htmlData=htmlData +'   <td><img src="./resources/img/DashGrey.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is not eligible for this feature"></td>'
	}else if(flag=='NA' && feild=='print'){
		htmlData=htmlData +'   <td><img src="./resources/img/DashGrey.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is not eligible for this feature"></td>'
	}
	return htmlData;
}
function openReturn(monthYr,Cat,fromRet){
	$("#custContent").css("display","block");
	$("#cusId").css("display","block");
	  $("#superContent").css("display","block");
	  $("#showHideId1").prop('class','fa fa-times');
	  logUserActivity(2018, 'user has viewed order details threw SBA online return');
	  onChangeMonthOrCat(monthYr,Cat,'YY');
}

function openSubCallToAction(seg){
	//$("#subCallToActionSegName").val(seg);
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
			showCallToActionPopUp();			
		}
	});
	
}

/*function getLayoutDetails(){
	var custid= $("#reqCustNum").val();
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLayoutDetails/AM/SAM",
				type : "POST",
				cache : false,
				async:false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					//alert(data);
					
				}
				}

			});	
			
}*/

function getSavingsInfo(){
	var custid= $("#reqCustNum").val();
	var dt = geDataRefreshTime('MV_SAM_SAVINGS_INFO');
		$("#updateDateValueSavings span").html(dt+" ET");
	var dataFound='N';
	var NA_CONSTANT='NA';
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getSavingsInfo/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					if(undefined !=data.currEstAnnualSpend && '' !=data.currEstAnnualSpend){
						$("#currEstAnnualSpend").html(chkNegAmount(currencyFormat(checkUundefined(data.currEstAnnualSpend))));
						dataFound='Y';
					}
					else
						$("#currEstAnnualSpend").html(NA_CONSTANT);
					if(undefined !=data.proposedAnnualSpend && '' !=data.proposedAnnualSpend){
						$("#proposedAnnualSpend").html(chkNegAmount(currencyFormat(checkUundefined(data.proposedAnnualSpend))));
						dataFound='Y';
					}
					else
						$("#proposedAnnualSpend").html(NA_CONSTANT);
					if(undefined !=data.projectedPriceSaving && '' !=data.projectedPriceSaving){
						$("#projectedPriceSaving").html(chkNegAmount(currencyFormat(checkUundefined(data.projectedPriceSaving))));
						dataFound='Y';
					}
					else
						$("#projectedPriceSaving").html(NA_CONSTANT);
					if(undefined !=data.projectedRebateSaving && '' !=data.projectedRebateSaving){
						$("#projectedRebateSaving").html(chkNegAmount(currencyFormat(checkUundefined(data.projectedRebateSaving))));
						dataFound='Y';
					}
					else
						$("#projectedRebateSaving").html(NA_CONSTANT);	
					if(undefined !=data.totalSaving && '' !=data.totalSaving){
						$("#totalSaving").html(chkNegAmount(chkNegAmount(currencyFormat(data.totalSaving))));
						$("#annualPremium").html(chkNegAmount(chkNegAmount(currencyFormat(-299))));
						dataFound='Y';
					}else{
						$("#totalSaving").html(NA_CONSTANT);
						$("#annualPremium").html(NA_CONSTANT);
						$("#annualPremium").css("text-decoration","none");
					}
					if(dataFound!='Y'){
						$("#excelId").hide();
						$("#updateDateValueSavings").hide();
						$("#updateDateLabelSavings").hide();
					$("#savingContent").html("NO DATA FOUND FOR THIS CUSTOMER")
		    		$("#savingContent").css({ "text-align":"center",
		    			"height":"350px",
		    			"padding-top":"20%",
		    			"font-weight":"700",
		    			"font-size":"20px",
		    			"color":"lightgrey"
		    		});
				   }
				}
				}

    });
}
function getCatPenInfo(){
	var custid= $("#reqCustNum").val();
	var NA_CONSTANT='NA';
	var dataFound='N';
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getCatPenInfo/"+custid,
				type : "POST",
				cache : false,
				/*async:false,*/
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
				if	(data !=null && data != undefined){
					$.each(data, function(i, item) {
						//alert(item.category);
						if(undefined !=item.category){
							catname=catCodeMap[item.category];
							if(undefined !=item.category && item.categoryPen=='N'){
							catImg='<img src="./resources/img/Cross.png" class="img-circle">';
							dataFound='Y';
							}
							else if(undefined !=item.category && item.categoryPen=='Y'){
								catImg='<img src="./resources/img/Check.png" class="img-circle">';
								dataFound='Y';
							}
							else if(undefined !=item.category && item.categoryPen=='M'){
								catImg='<img src="./resources/img/info.jpg" class="img-circle">';
								dataFound='Y';
							}
							else
								catImg='NA';
							//alert(item.category+"--"+catname +"---"+item.categoryPen)
							if(undefined!=catname && catname =='Ink And Toner'){
								$("#InkToner").html(catImg);
							 } else if(undefined!=catname && catname=='Technology'){
								 $("#tech").html(catImg); 
						     }else if(undefined!=catname && catname=='Furniture'){
						    	 $("#furniture").html(catImg);
						     }else if(undefined!=catname && catname=='Office Supplies'){
						    	 $("#offSupp").html(catImg);
						     }else if(undefined!=catname && catname=='Promo'){
						    	 $("#promo").html(catImg);
						     }else if(undefined!=catname && catname=='Paper'){
						    	 $("#paper").html(catImg);
						     }else if(undefined!=catname && catname=='Mail and Ship'){
						    	 $("#mailship").html(catImg);
						     }else if(undefined!=catname && catname=='Facilities'){
						    	 $("#facility").html(catImg);
						     }else if(undefined!=catname && catname=='All Other Products'){
						    	 $("#allproduct").html(catImg);
						     }
						} 
							
						
					});
					if(dataFound!='Y'){
						$("#catPenContent").html("NO DATA FOUND FOR THIS CUSTOMER")
			    		$("#catPenContent").css({ "text-align":"center",
			    			"height":"350px",
			    			"padding-top":"20%",
			    			"font-weight":"700",
			    			"font-size":"20px",
			    			"color":"lightgrey"
			    		});
					   }
				}
				}

    });
}
function generateSegLog(){
	logUserActivity(2023, 'View Lead Customer Segment Details');
}
function checkuncheckRetailOnline(){
	if(undefined !=this && undefined !=this.checked && true==this.checked)
	logUserActivity(2024, 'View Retail/Online Sales On Monthly Spend Analysis');
	else if(undefined !=this && undefined !=this.checked && false==this.checked)
	logUserActivity(2025, 'Hide Retail/Online Sales On Monthly Spend Analysis');
}
function downloadSavingsReport(){
	logUserActivity(2032, 'Download Savings Details Excel Report');
	$("#savingsReportForm").attr("action","./downloadExcel/Savings")
	   var custNum= $("#reqCustNum").val();
	   $('#savingsReportForm').submit();
}