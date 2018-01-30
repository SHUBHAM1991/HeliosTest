var ctx = $("#svsURL").val();
var abandonedCartListVOArr=new Array();
var custRecommendationListVOArr=new Array();
var dotcomActivityVOArr=new Array();
var objPurchaseDetailsListVO=new Array();
var searchItemsListVOArr=new Array();
var latestDateforOrder=null;
var SuperUserDeatilCount=-1;
var ShipToDeatilCount=-1;
var OrderDeatilCount=-1;
var ShipOrderDeatilCount=-1;
var iStart=0;
var ActiveUserDeatilCount=-1;
$.fn.dataTable.ext.errMode = 'none';
$(function(){
    $(".dropdown").hover(            
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            });
    });
$(document).ready(function() {
	$(window).resize(function(){
		if($(window).width()<=768){ 
			$("[id^=tabRow]").closest('table').closest('td').css("padding-left","5px");
		}else if(($(window).width()>768) && ($(window).width()<=1024)){ 
			$("[id^=tabRow]").closest('table').closest('td').css("padding-left","18px");
		}		
	});
	$("[data-toggle='tooltip']").tooltip();
	var cval=$.cookie('sidebar_closed');
	//alert(cval+""+ $(window).width());
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
	$('#dataTables-exampleShipTo').dataTable({
		"dom": '<"pull-right"f>rt<"bottom"ip>',
		 "bSort": true,
		 "oLanguage": { "sSearch": "Filter: "},
			"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
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
		"lengthMenu": [[15, 25, 50, -1], [15, 25,50, "All"]],
		"scrollX": true,
        "paging": false,
        "searching": false,
        "info":   false,
		// "aaSorting": [[ 0, "" ]] 
	"order": [],
	"oLanguage": { "sSearch": "Filter: "},
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
	$( "#sortSel").change(function() {
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
	 $('#dataTables-QuickSearch')
	    .on('preXhr.dt', function ( e, settings, data ) {
	    	$("#dataTables-QuickSearch tr th").hide();
	    	$("#iStart").val(data.iDisplayStart);
	    	
	    } );
	 $('#dataTables-QuickSearch')
	    .on( 'error.dt', function ( e, settings, techNote, message ) {
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
	populateQuickSearchData();
	populateCustomerProfile();
	var searchVO = {customerNumber : $("#reqCustNum").val()};
	//showStoresNearBy(searchVO);
	populatesubCallActions();
});

function populateCustomerProfile() {
	var formData={};
	var custid=$("#reqCustNum").val();
	$.ajax({
	 	dataType: "json",
		url : ctx+"/getNotifications/"+custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$("#custNum").html(data.listOfCustProfileVO[0].custNum);
			if(data.listOfCustProfileVO[0].iamId != '-' && data.listOfCustProfileVO[0].iamId !=''){
				//data.listOfCustProfileVO[0].iamId='0013000000B6u5OAAR';
				$("#cnoId").html("");
				$("#logId").html("");
				var compNameUrl='https://na32.salesforce.com/'+data.listOfCustProfileVO[0].iamId;
				$("#compNameCont").html('<a href="javascript:openUrlSfdc(\''+compNameUrl+'\',\'compname\')"  id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#0066c0;text-decoration:underline;" class="letterSpace"> '+data.listOfCustProfileVO[0].companyName+' </a>');
				$("#ulIamIdSec").css("display","block");
				$("#sfdcId").css("display","block");
				/*var cnoUrl='https://na32.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%253FsrPos%253D0%2526srKp%253D001%26accid%'+data.listOfCustProfileVO[0].iamId;*/
				var cnoUrl='https://na32.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%26accid%3D'+data.listOfCustProfileVO[0].iamId;
				var cnoHtml='<a href="javascript:openUrlSfdc(\''+cnoUrl+'\',\'createopp\')" target="_blank" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;">Create New Opp.</a>'+
				' <a href="javascript:openUrlSfdc(\''+cnoUrl+'\',\'createopp\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 19px;margin-right: -8px;float:right;" id="cnoIconId" class="fa fa-hand-o-up"></i></a>';
				$("#cnoId").html(cnoHtml);
				$("#menuItemIcon7").attr("href",cnoUrl);
				var logUrl='https://na32.salesforce.com/00T/e?what_id='+data.listOfCustProfileVO[0].iamId;
				var logHtml='<a href="javascript:openUrlSfdc(\''+logUrl+'\',\'logtask\')" target="_blank" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;">Log A Task</a>'+
				   ' <a href="javascript:openUrlSfdc(\''+logUrl+'\',\'logtask\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 18px;margin-right: -8px;float:right;" id="logIconId" class="fa fa-file"></i></a>';
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
	        $("#custType").html(data.listOfCustProfileVO[0].custType);
	      
	        //LTS saving changes
	        if (data.listOfCustProfileVO[0].lts != undefined &&
	        		data.listOfCustProfileVO[0].lts != null &&
	        		data.listOfCustProfileVO[0].lts != '') {
	        	$("#ltsInfoColumn").show();
	        	$("#ltsInfo").html("$"+formatOrderAmt(Math.round(data.listOfCustProfileVO[0].lts)));
	        } else {
	        	$("#ltsInfoColumn").hide();
	        }
	        
	        if(undefined != data.listOfCustProfileVO[0].companyAddr1 && '' != data.listOfCustProfileVO[0].companyAddr1 && (data.listOfCustProfileVO[0].companyAddr1.length > 1)){
		        $("#custAdd1").html(" "+data.listOfCustProfileVO[0].companyAddr1+" "+data.listOfCustProfileVO[0].companyAddr2 +"<br> "+data.listOfCustProfileVO[0].companyCity +", "+data.listOfCustProfileVO[0].companyState+" "+formatZip(data.listOfCustProfileVO[0].companyZip));
		          if(data.listOfCustProfileVO[0].companyPhone != undefined && data.listOfCustProfileVO[0].companyPhone != ''){
		        	$("#custAdd1").append("<br>P. "+formatPhone(data.listOfCustProfileVO[0].companyPhone));
		          }
		        }
		        else
		        	$("#custAdd1").html(" Address Not Found ");
	        $("#loggedInUserNameSpan").html("You logged in as "+$("#loggedInUserName").val());
	        if($(window).width()<=768){
	   		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;padding-right: 2%;");	 
	   	    }else{
	   		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;");	 
	   	   }
		}
	});	 
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
		mkttrHTML += '<i id="ShipOrderIdDtl'+cnt+'"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="ShiporderdivId'+cnt+'" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
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
		"aoColumns": [{"bSortable": true, "sWidth": '25%'},{"bSortable": true, "sWidth": '25%'},
		{"bSortable": true, "sWidth": '25%'},
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
//oTable.search($(this).val()).draw(); 
//oTable.search(inputString).draw();*/
}
})
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
		
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
			 + checkUundefined(item.skuNumber) +'</td>'
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
		
		mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:17%;">'
			 + checkUundefined(item.skuNumber) +'</td><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:18%;">'
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

function submitStoreFilterOnEnter(e) {
    var keycode;
    if (window.event) keycode = window.event.keyCode;
    else if (e) keycode = e.which;
    else return true;
    if (keycode == 13) {
    	onFindStoresNearByFilterClick();
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
	var formData={'searchText' : searchText, 'acctId': acctId};
	$.ajax({
		dataType: "json",
		url : ctx+"/getCustomerNumber", 
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if(data != null && data != undefined && data != 'failure'){
				opencustDetails(data);
			} else {
				hideProgress();
				//alert("Either the search citeria is wrong or the account manager is not matching.");
				alert("To search for a different customer on this screen the customer must be part of your Book of Business. Please enter the complete company name or customer number. To search for a different customer with partial information please do so via the To Do list.");
			}
        }
	});
}


function opencustDetails(cNum) {

	$("#customerForm").attr("action", "./home_template2")
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
	$("#updateDateValueReorder span").html(dt+" ET");
	//var orderCon = orderContact;
	var custid = $("#reqCustNum").val();
	if (undefined != custid && custid != 'select') {
	// alert(ctx + "/getBoughtRecommendation/" + custid + "/" +
	// orderContact)
	$
			.ajax({
				url : ctx + "/getReorderRecommendations/" + custid ,
						
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
					totalReorderRecommLength=0;
					// alert(data+"reorder data length="+data.length);
					
					if (data != null && data != undefined) {
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
													//skuDetailURL = 'http://www.staples.com/product_'+item.skuNumber;
													skuDetailURL ='http://www.staplesadvantage.com/webapp/wcs/stores/servlet/StplShowItem?catalogId=4&item_id='+item.catenTryId+'&langId=-1&currentSKUNbr='+item.skuNumber+'&storeId=10101&itemType=1';
												}
												
												htmlData = htmlData
														+ '<li style="border: 1px solid green;width: 164px;height:350px; display: block;">'

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
												totalReorderRecommLength=totalReorderRecommLength+1;
											//} 

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
											 +'<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;font-weight:bold;">'+checkUundefined(item.customerAddress)+'</div>'
											 +'<div style="font-size:12px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;font-weight:bold;">'+checkUundefined(item.customerCity)+'</div>'
											 +'<div style="font-size:12px;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;font-weight:bold;">'+checkUundefined(item.customerState)+'-'+checkUundefined(item.customerZip)+'</div>'
											 +'</div></td>'
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.orderNoCurYr) +'</td>'
											 +'<td class="datatablesTd">'
											 + chkNegAmount(formatNumUserGrid(checkUundefined(item.totalSpendCurYr))) +'</td>'	
											 +'<td class="datatablesTd">'
											 + checkUserGridFields(item.orderNoLastYr) +'</td>'
											 +'<td class="datatablesTd">'
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

function openOrderDetails(isFromReturned){
	
	// log user activity; view order list
	logUserActivity(2004, 'View Order List');
	var Amount = new Array();
	
	//var latestDate= getLatestFiscalDate();
	//alert("before month")
	//alert(latestDate.substring(1,2));
	var monthNew=parseInt(latestDateforOrder.substring(0,2));
	//alert(month+" "+latestDate.substring(2,6));
	var yearNew=latestDateforOrder.substring(2,6);
	//$("#yearSel").val("January 2015");
	var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	//alert(monthNameArr[monthNew])
	// $('#datepickerTEXT').val("2015");
	$("#userDiv").prepend($("#cusId"));
	
	$("#custContent").css("display","block");
	$("#cusId").css("display","block");
	  $("#superContent").css("display","block");
	  $("#showHideId1").prop('class','fa fa-times');
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthyear="02015";
	  var currentTime = new Date()
      var month = currentTime.getMonth() ;
	  var year = currentTime.getYear() ;
	  //var dt = geDataRefreshTime('ORDERHEADER');
	  var dt=getCurrentTime();
		//alert(dt);
		$("#updateDateValueOrder span").html(dt+" ET");
	  //alert(monthNew+"-"+yearNew+"-"+month+"-"+year);
	  var catid="ALL";
	  $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-order_processing").css("display","block");
	  monthyear=month+""+year;
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid+"/"+"NoChange",
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
					 populateMonthYearData(data,monthNew,yearNew);
					 populateCatData(data);
					 $("#yearSel option[value="+monthNew+""+yearNew+"]").attr("selected","selected");
					 $('#datepickerTEXT').val(monthNameArr[monthNew-1]+" / "+yearNew);
			          $('#datepickerTEXT').css("font-weight","bold")
			          $('#datepickerTEXT').css("font-family","Hevletica")
			          $('#datepickerTEXT').css("font-size","14px")
					 $('#orderDetailsTabId').html("");
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
											
											 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');" style="text-decoration:underline;padding-right:6px;">'
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
								$('#dataTables-order').dataTable({
									"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
									"order": [],
									"bProcessing": true,
									"oLanguage": { "sSearch": "Filter: "},
									"aoColumns": [{"bSortable": true},{"bSortable": true,"visible":true},
									{"bSortable":true},
									{"bSortable": true},
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
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("color","#004c74");
								$('#dataTables-order_length label').css("font-size","15px");
								$('#dataTables-order_length label').css("font-family","helveticaneuebold");
								$('#dataTables-order_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								//$('#dataTables-order_length label').css("font-weight","600");
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
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
			 		  onChangeMonthOrCat(monthYear,catId,year,monthVal);

		    		  $( "#datepickerTEXT" ).datepicker( "hide" );
		    		  $("#lastXDaysSel").val("0");
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
			selectedDeviceModel.append($('<option/>', {
				value : item. mktItmCatCd,
				text :  item.name
			}));
		  });
		
 }
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
			populateNotificationData();
			return latestDateforOrder;

}
function onChangeMonthOrCat(monthYr,catid){
	
	// log user activity; view order list
	logUserActivity(2004, 'View Order List');
	var Amount = new Array();
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthVal=$("#ui-datepicker-div .ui-datepicker-month :selected").val();
      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
      var map = {"Jan":"1","Feb":"2","Mar":"3","Apr":"4","May":"5","Jun":"6","Jul":"7","Aug":"8","Sep":"9","Oct":"10","Nov":"11","Dec":"12","ALL":"0"};
      if(undefined==monthYr){
    	  if ($("#lastXDaysSel :selected").val() == "0") {
	    	  var monthVal= (($('#datepickerTEXT').val()).split("/")[0]).trim();
	    	  var year= (($('#datepickerTEXT').val()).split("/")[1]).trim();
	    	  monthYr=map[monthVal]+""+year;
    	  } else {
    		  monthYr=$("#lastXDaysSel :selected").val()+ latestDateforOrder.substring(2,6);
    	  }
      }
      $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-order_processing").css("display","block");
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
					 $('#orderDetailsTabId').html("");
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
											
											 +'<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('+cnt+',\''+checkUundefined(item.orderNumber)+'\');" style="text-decoration:underline;padding-right:6px;">'
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
											 + checkUundefined(itemSku) +'</td>'
											 +'<td class="datatablesTd">'
											 + checkUundefined(itemSkuDesc) +'</td>'
											 +'</tr>';
										
										  cnt++;
									});
									OrderDeatilCount = cnt-1;
								}
								
								$('#orderDetailsTabId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
												
	                                           // +'<th>Details</th>'
	                                           
	                                            +'<th>Order No.</th>'
	                                            +'<th style="color: red">RETURNED</th>'
	                                            +'<th>Order Date</th>'
	                                            +'<th>No. Of Items</th>'
	                                            +'<th>Order Total</th>'
	                                            +'<th>Item SKU</th>'
	                                            +'<th>Item Desc</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>Premium Savings shown here <i id="" class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;"></i> reflects April 16,2016 forward.</div>');
								$("#dataTables-order_processing").css("display","none");
								$('#dataTables-order').dataTable({
									"lengthMenu": [[5, 10, 30, -1], [5, 10,30, "All"]],
									"bProcessing": true,
									"order": [],
									"oLanguage": { "sSearch": "Filter: "},
									"aoColumns": [{"bSortable": true},{"bSortable": true, "visible":true},
									{"bSortable": true,"bSearchable": false},
									{"bSortable": true,"bSearchable": false},
									
									{"bSortable": true,"bSearchable": false},
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

function openOrderDetailsInfo(index,ordNum){
	// log user activity; view order details
	logUserActivity(2005, 'View Order Details');
	
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
			 + checkUundefined(item.skuNumber) +'</td>'
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

function openReturnedOrderDetailsInfo(index , ordNum){
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
			 + checkUundefined(item.skuNumber) +'</td>'
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
	  logUserActivity(2002, 'View User Details');
	  
	  var formData={};
	  var dt = geDataRefreshTime('WEBACTIVITY');
		//alert(dt);
	  $("#userDiv").prepend($("#superId"));
		$("#updateDateValueUser span").html(dt+" ET");
	  $("#superId").css("display","block");
	  $("#superContent").css("display","block");
	  $("#showHideId0").prop('class','fa fa-times');
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
			              						'<div style="font-size:13px;font-weight:bold;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;letter-spacing:1px;" id="orderStat" >'+item.orderContact+'</div>'+
			              						'<div style="font-size:13px;font-weight:bold;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">No Contact Information is Available</div>'+
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
											+'<td class="datatablesTd">'
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
							            	  //$("#addContactId").css("display","block");
							            	  //$("#addContactId").attr("href","https://na32.salesforce.com/003/e?accid=1&ent=Contact");
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
									"oLanguage": { "sSearch": "Filter: "},
									"order": [],
									"bProcessing": true,
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
								$('#dataTables-example_filter label').css("font-size","15px");
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
           htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column1[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" style="margin-top: 5px;width: 120px;height: 160px;"/>'
                    +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;" aria-hidden="true" data-rows="3">'
                       + truncateTitle(checkUundefined(column1[i].itemDescription))
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
          htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column3[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" style="margin-top: 5px;width: 120px;height: 160px;"/>'
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
   		 $('#searchStringId').append('<tr><td style="font-weight:bold">Blocked Search:</td></tr>');
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
					//setCustomerRight(custid);
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
					//setCustomerRight(custid);
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
function opencustDetails(cNum){
	
	 $("#customerForm").attr("action","./home_template2")
		$("#reqCustNum").val(cNum);
	  $('#customerForm').submit();
		
}

function openUrl(url){
	 var win = window.open(url, '_blank');
	  win.focus();
}

function populateQuickSearchData(){
	 var formData={};
	 var selPlayType=$("#filterBy").val();
	 var sliderSubPlaysItem=$("#sliderSubPlaysItem").val();
	 var selectedQualScore=$("#selectedQualScore").val();
	 var selectedSegScore=$("#selectedSegScore").val();
	 var custid=$("#accId").val();
		 $('#dataTables-QuickSearch').dataTable( {
	        /*"bProcessing": true,*/
	        "bDestroy" : true,
	        "oLanguage": { "sSearch": "Filter: "},
	        "sAjaxSource": ctx+"/getAllCustomers/"+custid+"/"+selPlayType,
	        "fnServerParams": function (aoData) { 
		    	   aoData.push({ "name": "selectedSubPlays", "value": sliderSubPlaysItem});
		    	   aoData.push({ "name": "selectedQualScore", "value": selectedQualScore});
		    	   aoData.push({ "name": "selectedSegScore", "value": selectedSegScore});
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
									return '<li class="media" onclick="opencustDetails('+full.custNum+')">'
										+'<div class="media-status">'
										+'<span class="badge badge-warning">'+checkUundefinedNullBlankZero(full.callOrder)+'</span>'
									    +'</div>'
									    //+'<img class="media-object" src="../../assets/admin/layout/img/avatar6.jpg" alt="...">'
									    +'<div class="media-body" id="custInfoId_'+full.callOrder+'">'
										+'<h4 class="media-heading" style="font-family:Helvetica;font-weight:bold;font-size:14px; color: #ddd; ">'+ checkUundefined(full.custNum)+'</h4>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+ checkUundefined(full.custType)+'</div>'
										+'<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'+checkUundefined(full.companyName)+'</div>'
									    +'</div>'
								        +'</li>';
								 }
							}							]
							/*"oLanguage": { "sInfo": "Showing Records _START_ - _END_ Click the arrow to view more records",
								"sInfoEmpty": "0 records",				  
								"sInfoFiltered": ""	}*/

	    } );

	 

	  
}

function clearCookie(){
	$.cookie('sidebar_closed', '', { path: '/', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDasbhoard', expires:-1 });
	$.cookie('sidebar_closed', '', { path: '/ContractDashboard', expires:-1 });
}
function openRecommendations(){
	//clearCookie();
  $("#customerForm").attr("action","./recommAction")
  $('#customerForm').submit();
}
function openStoreLocator(){
	//$("#address").val().....$("#zip").val()....$("#state").val()
	$("#customerForm").attr("action","./storeLocAction")
	$('#customerForm').submit();
}
function showFilters() {
	// $("#filter").css("display","block");
	$("#filter").toggleClass("hideFilter");
	$("#plusMinusIcon").toggleClass("glyphicon-plus");
	$("#plusMinusIcon").toggleClass("glyphicon-minus");
	
}

function populatesubCallActions() {
	var formData={};
	var custid=$("#reqCustNum").val();
	$("#segName").html($("#subCallToActionSegName").val());
	/*$.ajax({
	 	dataType: "json",
		url : ctx+"/getNotifications/"+custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			
		}
	});*/	 
}

function showStoresNearBy(searchVO)
{
	var formData = searchVO;
	var serviceURL = ctx +"/findStoresNearBy";
	
	var emptyTableMessage = "No stores within 10 miles of this zip code";
	
	if (formData.customerNumber != undefined && formData.customerNumber != null && formData.customerNumber != '') {
		emptyTableMessage = "No stores within 10 miles of master account's zip code";
	}
	$.ajax({
		dataType: "json",
		url : serviceURL,
		type : "POST",
		data : formData,
		cache : false,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
						/*
						if (data.results.status != "SUCCESS" && data.results.status == "FAILED") {
							$.showAjaxErrorMessage('Error Occurred', 'An error occurred while fetching Store List <br />' + 
									data.results.error.developerMessage + '</br>' + data.results.error.message);
							return;
						}
						*/
						var i = 0;
						
						var days = {'MON' : {day : 'Mon', order : 1},
									'TUS' : {day : 'Tue', order : 2},
									'WED' : {day : 'Wed', order : 3},
									'THU' : {day : 'Thu', order : 4},
									'FRI' : {day : 'Fri', order : 5},
									'SAT' : {day : 'Sat', order : 6},
									'SUN' : {day : 'Sun', order : 7}};
						
						$('#dataTablesStores').dataTable({
						"bDestroy" : true,
						"oLanguage": {      "sEmptyTable": emptyTableMessage,
							"sSearch": "Filter: "},
						"aaData" : data.results.stores,
						"lengthMenu": [[5, 10], [5, 10]],
						"aoColumns" : [
							{
								"sWidth" : "10%",
								"mData": null,
								"mRender" : function(
										 data, type, full) {
											return  (Math.round( data.distance * 10 ) / 10) + ' Miles';
										 }
							},
							{
								"sWidth" : "25%",
								"mData": null,
								"mRender" : function(
										 data, type, full) {
											var address = '<div>' +
											checkUundefined(data.address.addressLine1) + '</br>' +
											((null != checkUundefined(data.address.addressLine2) && '' != checkUundefined(data.address.addressLine2)) ?checkUundefined(data.address.addressLine2)+ '</br>':"")
											+checkUundefined(data.address.city) + ', ' + 
											checkUundefined(data.address.state) + ', ' +
											checkUundefined(data.address.zipcode) + '</br>' +
											'P. ' +checkUundefined(data.address.phoneNumber) + '</br>' +
											'F. ' +checkUundefined(data.address.faxNumber) + '</br>' +
											'Store #' + data.storeNumber +
											'</div>';
											return address;
										 }
							},
							{
								"sWidth" : "30%",
								"mData": null,
								"mRender" : function(
										 data, type, full) {
											var workingHourVOs = data.workingHourVOs;
											
											var storeTimings = '<table style="padding-top: 15px; padding-bottom: 15px;">';
											var storeTimingsArray = [];
											for (var j = 0; j < workingHourVOs.length; ++j) {
												var whr = workingHourVOs[j];
															var timing = ('<tr id="tabRow'+j+'"><td style="text-align: left;border:none;width: 35px;">'
																	+ days[whr.day].day
																	+ '</td><td style=" padding-left: 0px; border: none; width: 10px !important; text-align: center;">-</td>  <td style="width: 68px;padding-left:5px;border:none;">'
																	+ formatAMPM(whr.openTime)
																	+ '</td><td style=" padding-left: 0px; border: none; width: 10px !important; text-align: center;">-</td> <td style="padding-left:5px;border:none;">'
																	+ formatAMPM(whr.closeTime) + '</td></tr>');
												storeTimingsArray.push({ timing : timing, order : days[whr.day].order});
											}
											
											storeTimingsArray.sort(function (a, b) {
												return a.order - b.order;
											});
											
											for (var k = 0; k < storeTimingsArray.length; ++ k) {
												storeTimings += storeTimingsArray[k].timing;
											}
											storeTimings+='</table>';
											
											return storeTimings;
										 }
							},
							{
								"sWidth" : "35%",
								"mData": null,
								"mRender" : function(
										 data, type, full) {
											var storeFeatures = '<div style="padding-top: 15px; padding-bottom: 15px;">';
											for (var j = 0; j < data.featureVOs.length; ++j) {
												var f = data.featureVOs[j];
												storeFeatures += (f.featureLabel + '</br>');
											}
											storeFeatures += '</div>';
												
											return storeFeatures;
										 }
							}
							]
		
						});
						
						$('#dataTablesStores_filter input[type="search"]').attr('placeholder','Enter Street or City or Zip');
						/*$('#dataTablesStores_length label').css("color","#004c74");
						$('#dataTablesStores_length label').css("font-weight","600");
						$('#dataTablesStores_filter label').css("color","#004c74");
						$('#dataTablesStores_filter label').css("letter-spacing","1px");
						$('#dataTablesStores_filter label').css("font-weight","600");*/
						$('#dataTablesStores_length label').css("color","#004c74");
						$('#dataTablesStores_length label').css("font-size","15px");
						$('#dataTablesStores_length label').css("font-family","helveticaneuebold");
						$('#dataTablesStores_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
						$('#dataTablesStores_filter label').css("color","#004c74");
						$('#dataTablesStores_filter label').css("letter-spacing","1px");
						$('#dataTablesStores_filter').css("text-align","right");
						$('#dataTablesStores_filter label').css("font-family","helveticaneuebold");
						$('#dataTablesStores_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
						$('#dataTablesStores_filter label').css("float","right");
						$('#dataTablesStores_info').css("color","#004c74");
						$('#dataTablesStores_info').css("font-weight","600");
						$('#dataTablesStores_paginate').css("text-align","right");
						$('.form-inline .form-control').css("color","#004c74");
						$('.form-inline .form-control').css("border","1px solid #004c74");
						if($(window).width()<=768){
							$("[id^=tabRow]").closest('table').closest('td').css("padding-left","5px");
						}else if(($(window).width()>768) && ($(window).width()<=1024)){
							$("[id^=tabRow]").closest('table').closest('td').css("padding-left","18px");
						}
						$("table#dataTablesStores >tbody > tr[role=row] :nth-child(2) div").parent().css({
							"word-wrap": "normal",
					    "padding-left": "5px",
					    "text-align": "center"
						});
					}
	});
}

function formatAMPM(ts) {
	  var hours = parseInt(ts.substring(0,2), 10);
	  var minutes = parseInt(ts.substring(2), 10);
	  
	  var ampm = hours >= 12 ? 'PM' : 'AM';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ' ' + ampm;
	  return strTime;
}

function onFindStoresNearByFilterClick() {
	var zipCode = $("#zipCode").val();
	
	if (zipCode != undefined && zipCode != null && zipCode != '') {
		var isNum = /^\d+$/.test(zipCode);
		
		if (!isNum) {
			$.showAjaxErrorMessage('Advance Search', "Please specify valid Zip Code");
		} else {
			showStoresNearBy({zipCode : zipCode});
		}
	} else {
		var address = $("#address").val();
		var state = $("#state").val();
		
		if (address != undefined && address != null && address != '') {
			if (state != undefined && state != null && state != '' && state != "Select") {
				address += ("," + state);
			}
		} else if (state != undefined && state != null && state != '' && state != "Select") {
			address = state;
		} else {
			// No filter has been specified; show error message
			$.showAjaxErrorMessage('Advance Search', "Please enter an Address, State or Zip");
			return;
		}
		
		showStoresNearBy({address : address});
	}
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
	$('#training_modal').modal({
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
function openOutlook(email) {
	if(undefined != email && '' !=email)
    window.location.href ="mailto:"+email;
}
