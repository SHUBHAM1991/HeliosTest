var ctx =$("#svsURL").val();
var playSecData='';
var sidebarClick=false;
var iStart=0;
$.fn.dataTable.ext.errMode = 'none';

var barCategoryDataGlbVar;
var columnChartDataGlbVar;
var finalMinMaxGlbVar;
var pieChartDataSet;
var currYrToDtSalesTotGlbVar
var isPopRefreshDtALLCalledGlbVar = false;
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
var retFound='N';
var latestFiscalContactedDate='';
$(document).ready(function() {
	
	createCustomerInsightMessage();
	
	populateNotificationData();
	
	perfDisableSideBar();
	
	populateDateALL();
	
	getDataAndCreateYrToDateBossCoreChart();
	
	getDataAndCreatePerfColumnDoughNutChart();
	
	populateQuickSearchData();

	getLatestFiscalDateOrder();
	
	getLatestFiscalContactedDate();
	$("#growthId").click(function(){
  	  
		var Gclass=$("#growthId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
			$("#gIconId").attr("class","fa fa-chevron-up");
			logUserActivity(2027, 'User has clicked on Call Order Growth List');
		}else if(undefined != Gclass &&  '' ==Gclass){
			$("#gIconId").attr("class","fa fa-chevron-down")
		}
	});
	 $("#retentionId").click(function(){
		var Gclass=$("#retentionId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){ 
			$("#rIconId").attr("class","fa fa-chevron-up");
			logUserActivity(2028, 'User has clicked on Call Order Retention List');
		}else if(undefined != Gclass &&  '' ==Gclass){ 
			$("#rIconId").attr("class","fa fa-chevron-down")
		}
	});
	 $("#expansionId").click(function(){
		var Gclass=$("#expansionId").attr("class");
		if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
			$("#eIconId").attr("class","fa fa-chevron-up");
			logUserActivity(2029, 'User has clicked on Call Order Expansion List');
		}else if(undefined != Gclass &&  '' ==Gclass){
			$("#eIconId").attr("class","fa fa-chevron-down")
		}
	});
	 $("#growthId").click(function(){
			
		     var cls=$("#growthSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#growthSpanId").attr("class","fa fa-chevron-up")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  }
		  });
		  $("#retentionId").click(function(){
			
		     var cls=$("#retentionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#retentionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }
		  });
		  $("#expansionId").click(function(){
			
		     var cls=$("#expansionSpanId").attr("class");
		     if(cls=='glyphicon glyphicon-plus'){
			  $("#expansionSpanId").attr("class","fa fa-chevron-up")
			  $("#growthSpanId").attr("class","fa fa-chevron-down")
			  $("#retentionSpanId").attr("class","fa fa-chevron-down")
			  }else{
			  $("#expansionSpanId").attr("class","fa fa-chevron-down")
			  }
		  });
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
	$("#sfdcYesId").click(function(){
		 var sfdcUrl=$("#sfdcConfUrl").val();
			if(undefined!=sfdcUrl && '' != sfdcUrl){
				logUserActivity(2033, 'Pre-populated User information to SFDC');
			openUrlWithEmail(sfdcUrl);
			}
		});
	var height=($(window).height() - ($(window).height()/10));
	$(".msg_container_base").css("max-height",height);
	$("[data-toggle='tooltip']").tooltip();

	var cval=$.cookie('sidebar_closed');
	$('#printDashboardBtn').hide();
	$("#loggedInUserNameSpan").html("You logged in as "+$("#loggedInUserName").val());
	 
	 if($(window).width()<=768){
		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;padding-right: 2%;");	 
	 }else{
		 $("#loggedInUserNameSpan").attr("style","text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;");	 
	 }
	$(window).resize(function() { 
		if(undefined != barCategoryDataGlbVar && '' != barCategoryDataGlbVar){
			createYrToDateBossCoreBarChart(barCategoryDataGlbVar);
		}
		if(undefined != columnChartDataGlbVar && '' != columnChartDataGlbVar){
			createPerfCategoriesColumnChart(columnChartDataGlbVar, finalMinMaxGlbVar);
		}
		if(undefined != pieChartDataSet && '' != pieChartDataSet){
			createPerfPieChartDoughnut(pieChartDataSet, currYrToDtSalesTotGlbVar);
		}
		setInsightsHeight();
		
	});	

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

});



function getDataAndCreateYrToDateBossCoreChart() {
	var custid = $("#reqCustNum").val();
	var formData = {};
	$.ajax({
		dataType : "json",
		url : ctx + "/getDataForBossCoreCategory/" + custid,
		type : "POST",
		cache : false,
		/* async:false, */
		categoryData : JSON.stringify(formData),
		timeout : 1000000,
		success : function(categoryData, textStatus, jqXHR) {
			if (categoryData != null && categoryData != undefined) {
				populateBossCoreOtherData(categoryData);
				createYrToDateBossCoreBarChart(categoryData);
			}else {
				$("#yrToDateBossChart").html("EITHER ERROR OR NO SALES FOUND FOR THIS TIME DURATION")
				$("#yrToDateBossChart").css("text-align", "center");
				$("#yrToDateBossChart").css("padding-top", "35%");
				$("#yrToDateBossChart").css("padding-right", "3%");
				$("#yrToDateBossChart").css("font-weight", "700");
				$("#yrToDateBossChart").css("font-family", "Helvetica");
				$("#yrToDateBossChart").css("font-size", "20px");
				$("#yrToDateBossChart").css("color", "darkgrey");
				//$("#yrToDateBossChart").css("color", "#555");
				
				$("#yrToDateBossChart1").html("EITHER ERROR OR NO SALES FOUND FOR THIS TIME DURATION")
				$("#yrToDateBossChart1").css("text-align", "center");
				$("#yrToDateBossChart1").css("padding-top", "35%");
				$("#yrToDateBossChart1").css("padding-left", "3%");
				$("#yrToDateBossChart1").css("font-weight", "700");
				$("#yrToDateBossChart1").css("font-family", "Helvetica");
				$("#yrToDateBossChart1").css("font-size", "20px");
				$("#yrToDateBossChart1").css("color", "darkgrey");
			}
		}
	})
}

function populateBossCoreOtherData(categoryData){
	var currGrowthPerc = categoryData.currGrowthPerc;
	if(currGrowthPerc > 0){
		currGrowthPerc = currGrowthPerc+"%" + "<img src=\"./resources/img/arrowUpGrowth.png\" width=\"16\" height=\"18\" />" ;
	}else if (currGrowthPerc == 0) {
		currGrowthPerc = currGrowthPerc+"%" + "<img src=\"./resources/img/Arrow_left_right.png\" width=\"35\" height=\"15\" />";

	}else{
		currGrowthPerc = currGrowthPerc+"%" + "<img src=\"./resources/img/arrowDownDecline.png\" width=\"16\" height=\"18\" style=\"margin:1px 0px\"  />" ;
	}	
	
	var yrToDtGrowthPerc = Math.round(categoryData.yrToDtGrowthPerc);
	if(yrToDtGrowthPerc > 0){
		yrToDtGrowthPerc = yrToDtGrowthPerc+"%" + "<img src=\"./resources/img/arrowUpGrowth.png\" width=\"16\" height=\"18\" />" ;
	}else if (yrToDtGrowthPerc == 0) {
		yrToDtGrowthPerc = yrToDtGrowthPerc+"%" + "<img src=\"./resources/img/Arrow_left_right.png\" width=\"35\" height=\"15\" />";	
	
	}else{
		yrToDtGrowthPerc = yrToDtGrowthPerc+"%" + "<img src=\"./resources/img/arrowDownDecline.png\" width=\"16\" height=\"18\" style=\"margin:1px 0px\" />" ;
	}	

	$("#totalCurrRollMonthSales").html(currencyFormatNoDec(categoryData.totalCurrRollMonthSales));
	$("#currGrowthPerc").html(currGrowthPerc);
	$("#totalCurrYrToDtSales").html(currencyFormatNoDec(categoryData.totalCurrYrToDtSales));
	$("#yrToDtGrowthPerc").html(yrToDtGrowthPerc);
	
	
}


function createYrToDateBossCoreBarChart(categoryData){
	barCategoryDataGlbVar = categoryData;
	var allCurrRollMonthCoreSales = categoryData.CORE;
	var allCurrRollMonthBossSales = categoryData.BOSS;
	var totalCoreBoss = 0;
	var categoryCorePerc = 0;
	var categoryBossPerc = 0;
	
	//first convert to string having two decimals by toFixed and then change to floating no. by parseFloat
	/*if( null!= totalCurrRollMonthSales && totalCurrRollMonthSales != 0){
		categoryCorePerc = parseFloat(((allCurrRollMonthCoreSales*100)/totalCoreBoss).toFixed(2));
		categoryBossPerc = parseFloat((100-categoryCorePerc).toFixed(2));  
	}*/
	
	if( null != totalCurrRollMonthSales && totalCurrRollMonthSales != 0){
		
		if (allCurrRollMonthCoreSales > 0 && allCurrRollMonthBossSales <=0) {
			categoryCorePerc = 100;
			categoryBossPerc = 0;		
		} else if (allCurrRollMonthBossSales > 0 && allCurrRollMonthCoreSales <=0) {
			categoryCorePerc = 0;
			categoryBossPerc = 100;
		} else if (allCurrRollMonthBossSales > 0 && allCurrRollMonthCoreSales > 0){
			
			totalCoreBoss = allCurrRollMonthBossSales + allCurrRollMonthCoreSales;
			categoryCorePerc = Math.round((allCurrRollMonthCoreSales/totalCoreBoss)*100); 
			categoryBossPerc = 100 - categoryCorePerc;			
		}	
		
	}
	
	var categorySalesDollValArr = new Array();
	categorySalesDollValArr[0] = (allCurrRollMonthCoreSales != 0 && allCurrRollMonthCoreSales != "") ? (currencyFormatNoDec(allCurrRollMonthCoreSales)) : " ";
	categorySalesDollValArr[1] = (allCurrRollMonthBossSales != 0 && allCurrRollMonthBossSales != "") ? (currencyFormatNoDec(allCurrRollMonthBossSales)) : " ";
	
	var categoryAllPercArr = new Array();
	categoryAllPercArr[0] = categoryCorePerc;
	categoryAllPercArr[1] = categoryBossPerc;
	
	var categoryNameArr = ["CORE", "BO$$"];
	columnBarChartData = getLableValueMapBarChart(categorySalesDollValArr, categoryAllPercArr, categoryNameArr);
	
	var maxValue = 100;
	var maxNo = getMinMax(categoryAllPercArr);
	if(maxNo > 80){
		maxValue = maxNo+25;
	}
	
	var TitleText = "";
	if(totalCurrRollMonthSales == 0 && categoryCorePerc == 0 && categoryBossPerc == 0){
		TitleText = "No Sales Data for this customer"
	}
	
	var chart = new CanvasJS.Chart("yrToDateBossChart",
		{
		title:{
			text :  TitleText,
			dockInsidePlotArea: true,
			verticalAlign: "center",
			fontFamily : "Helvetica",
			fontWeight : "bolder",
			fontColor : "#555"	
			//maxWidth: 100,
			//horizontalAlign: "center"
			//fontSize : 13,
		},
		axisX:{
			labelFontFamily: "Helvetica",
			labelFontSize: 16,
			labelFontWeight: "bold",
			labelFontColor: "#000000",
	        //interval: 1,
	        //tickLength: 1
			
			gridThickness: 1,
			gridDashType: "dash",
			gridColor: "#E8E8E8"	
	     },
	     axisY:{
	    	 valueFormatString:"0'%'",
	    	 labelFontFamily: "Helvetica",
	    	 labelFontSize: 14,
	    	 //labelFontWeight: "bold",
	    	 labelFontColor: "#000000",
	    	 //valueFormatString: ("#'%'"),
			 //suffix: "",
			//labelMaxWidth: 50, 
			//labelWrap: false
			//labelAutoFit: false,
			//tickLength: 1
			 interval: 25,
			 maximum: 124,
			 minimum: 0,
			 
			 gridThickness: 1,
			 gridDashType: "dash",
			 gridColor: "#E8E8E8"
		 },
		/*legend : {
			horizontalAlign: "right",
			verticalAlign: "center",
			fontFamily : "Helvetica",
			fontSize : 13,
			fontWeight : "bolder",
			fontColor : "#555",
			maxWidth : 400,
			itemWidth : 188
		},*/
		data: [{
			type: "bar",
			indexLabelPlacement: "auto",  //auto, outside, inside
		    indexLabelFontSize : 15,
		    indexLabel: "{categorySalesDollValue}",
		    indexLabelFontFamily: "Helvetica",
		    indexLabelOrientation: "horizontal",
		    indexLabelFontColor: "#000000",
		    //indexLabelFontWeight: "bold",
		    toolTipContent: "{toolTipContentValuesBar}",
		    dataPoints : columnBarChartData
			}]
		});
	
	chart.render();
	createYrToDateBossCoreBarChart1(categoryData);
}


function createYrToDateBossCoreBarChart1(categoryData){
	var allCurrYrToDtCoreSales = categoryData.CORE1;
	var allCurrYrToDtBossSales = categoryData.BOSS1;
	var totalCoreBoss1 = 0;
	var categoryCorePerc1 = 0;
	var categoryBossPerc1 = 0;
	
	/*if( null!= totalCurrYrToDtSales && totalCurrYrToDtSales != 0){
		categoryCorePerc = parseFloat(((allCurrYrToDtCoreSales*100)/totalCoreBoss1).toFixed(2));
		categoryBossPerc = parseFloat((100-categoryCorePerc).toFixed(2));  
	}*/
	
	/*if( null!= totalCurrYrToDtSales && totalCurrYrToDtSales != 0){
		categoryCorePerc1 = Math.round((allCurrYrToDtCoreSales/totalCoreBoss1)*100); 
		categoryBossPerc1 = 100 - categoryCorePerc1;  
	}
	*/
	
	if( null != totalCurrYrToDtSales && totalCurrYrToDtSales != 0){
		
		if (allCurrYrToDtCoreSales > 0 && allCurrYrToDtBossSales <=0) {
			categoryCorePerc1 = 100;
			categoryBossPerc1 = 0;		
		} else if (allCurrYrToDtBossSales > 0 && allCurrYrToDtCoreSales <=0) {
			categoryCorePerc1 = 0;
			categoryBossPerc1 = 100;
		} else if (allCurrYrToDtBossSales > 0 && allCurrYrToDtCoreSales > 0){
			
			totalCoreBoss1 = allCurrYrToDtBossSales + allCurrYrToDtCoreSales;
			categoryCorePerc1 = Math.round((allCurrYrToDtCoreSales/totalCoreBoss1)*100); 
			categoryBossPerc1 = 100 - categoryCorePerc1;			
		}	
		
	}
	
	
	var categorySalesDollValArr = new Array();
	categorySalesDollValArr[0] = (allCurrYrToDtCoreSales != 0 && allCurrYrToDtCoreSales != "") ? (currencyFormatNoDec(allCurrYrToDtCoreSales)) : " ";
	categorySalesDollValArr[1] = (allCurrYrToDtBossSales != 0 && allCurrYrToDtBossSales != "") ? (currencyFormatNoDec(allCurrYrToDtBossSales)) : " ";
	
	var categoryAllPercArr1 = new Array();
	categoryAllPercArr1[0] = categoryCorePerc1;
	categoryAllPercArr1[1] = categoryBossPerc1;
	
	var categoryNameArr = ["CORE", "BO$$"];
	columnBarChartData1 = getLableValueMapBarChart(categorySalesDollValArr, categoryAllPercArr1, categoryNameArr);
	
	var maxValue = 100;
	var maxNo = getMinMax(categoryAllPercArr1);
	if(maxNo > 80){
		maxValue = maxNo+25;
	}
	
	var TitleText = "";
	if(totalCurrYrToDtSales == 0 && categoryCorePerc == 0 && categoryBossPerc == 0){
		TitleText = "No Sales Data for this customer"
	}
	
	var chart = new CanvasJS.Chart("yrToDateBossChart1",
		{
		title:{
			text : TitleText,
			dockInsidePlotArea: true,
			verticalAlign: "center",
			fontFamily : "Helvetica",
			fontWeight : "bolder",
			fontColor : "#555"	
			//maxWidth: 100,
			//horizontalAlign: "center"
			//fontSize : 13,
		},
		axisX:{
			labelFontFamily: "Helvetica",
			labelFontSize: 16,
			labelFontWeight: "bold",
			labelFontColor: "#FFFFFF",
	        //interval: 1,
	        tickLength: 0,
			
			gridThickness: 1,
			gridDashType: "dash",
			gridColor: "#E8E8E8"	
	     },
		 axisY:{    
			 valueFormatString:"0'%'",
	    	 labelFontFamily: "Helvetica",
	    	 labelFontSize: 14,
	    	// labelFontWeight: "bold",
	    	 labelFontColor: "#000000",
	    	 //valueFormatString: ("#'%'"),
			 //suffix: "%",
			//labelMaxWidth: 50, 
			//labelWrap: false
			//labelAutoFit: false,
			//tickLength: 1
			 interval: 25,
			 maximum: 124,
			 minimum: 0,

			 
			 gridThickness: 1,
			 gridDashType: "dash",
			 gridColor: "#E8E8E8",
		},
		data: [{
			type: "bar",
			indexLabelPlacement: "auto", //auto, outside, inside
   		    indexLabel: "{categorySalesDollValue}",
		    indexLabelFontSize : 15,
		    indexLabelFontFamily: "Helvetica",
		    indexLabelOrientation: "horizontal",
		    indexLabelFontColor: "#000000",
		    //indexLabelFontWeight: "bold",
		    toolTipContent: "{toolTipContentValuesBar}",
		    dataPoints : columnBarChartData1
		    }]
	   });

	chart.render();
}


function getDataAndCreatePerfColumnDoughNutChart() { 
	var formData={};
	var custid=$("#reqCustNum").val();
	$.ajax({
		dataType : "json",
		url : ctx + "/getPerfColumnAndDoughnutChartData/" + custid,
		type : "POST",
		cache : false,
		perfDashboardCategoryVOList : JSON.stringify(formData),
		timeout : 1000000,
		success : function(perfDashboardCategoryVOList, textStatus, jqXHR) {
			if (perfDashboardCategoryVOList != null && perfDashboardCategoryVOList != undefined) {
				var categoryNameArr = new Array();
				var rollMonthPenAmountArr = new Array();
				var rollMonthPenPercentArr = new Array();
				var index = 0;
				var refreshDt1 = "";
				
				$.each(perfDashboardCategoryVOList, function(i, perfDashboardCategoryVO) {
					categoryNameArr[index] = perfDashboardCategoryVO.categoryName;
					rollMonthPenAmountArr[index] = Math.round((perfDashboardCategoryVO.rollMonthPenAmount));
					rollMonthPenPercentArr[index] = Math.round((perfDashboardCategoryVO.rollMonthPenPercent));
					refreshDt1 = perfDashboardCategoryVO.refreshDt;
					++index;
				});
				
				populateRefreshDateALL(refreshDt1);
				
				var indexLabelFontColorArr = new Array();
				for(i=0; i< rollMonthPenAmountArr.length ; i++){
					if(rollMonthPenPercentArr[i] >= 0){
						indexLabelFontColorArr[i] = "Black"
					}
					else{
						indexLabelFontColorArr[i] = "Red"
					}
					rollMonthPenAmountArr[i] = currencyFormatNoDec(rollMonthPenAmountArr[i]);
				}
				categoryNameArr[index] = " ";
				if (undefined != categoryNameArr && '' != categoryNameArr && categoryNameArr.length > 0	&& rollMonthPenAmountArr.length > 0 && rollMonthPenPercentArr.length > 0) {
					columnChartData = getLableValueMapColChart(categoryNameArr, rollMonthPenAmountArr, rollMonthPenPercentArr, indexLabelFontColorArr);
					var maxNo = getMinMax(rollMonthPenPercentArr);
					var finalMinMax = ((maxNo*125)/100);
					createPerfCategoriesColumnChart(columnChartData, finalMinMax);
				} else {
					$("#perfColumnChartID").html("EITHER ERROR OR NO SALES FOUND FOR THIS TIME DURATION")
					$("#perfColumnChartID").css("text-align", "center");
					$("#perfColumnChartID").css("padding-top", "10%");
					$("#perfColumnChartID").css("font-weight", "700");
					$("#perfColumnChartID").css("font-family", "Helvetica");
					$("#perfColumnChartID").css("font-size", "20px");
					$("#perfColumnChartID").css("color", "darkgrey");
				}
			} else {
				$("#perfColumnChartID").html("EITHER ERROR OR NO SALES FOUND FOR THIS TIME DURATION")
				$("#perfColumnChartID").css("text-align", "center");
				$("#perfColumnChartID").css("padding-top", "10%");
				$("#perfColumnChartID").css("font-weight", "700");
				$("#perfColumnChartID").css("font-family", "Helvetica");
				$("#perfColumnChartID").css("font-size", "20px");
				$("#perfColumnChartID").css("color", "darkgrey");
			}
			
			createPerfDataAndPieChart(perfDashboardCategoryVOList);
		}
	})
}


function createPerfCategoriesColumnChart(columnChartData, finalMinMax) {
	columnChartDataGlbVar = columnChartData;
	finalMinMaxGlbVar = finalMinMax;
	var chart = new CanvasJS.Chart("perfColumnChartID",
		{
		title:{
			//text: "Category Performance "
			margin:0
		},
		/*axisX:{
			labelFontFamily: "Helvetica",
			labelFontSize: 13,
			//labelFontWeight: "bold",
			labelFontColor: "black",
			//tickLength: 10
	        //interval: 1,
	     },*/
	     axisX2: {
	        labelFontFamily: "Helvetica",
			labelFontSize: 13,
			labelFontWeight: "bold",
			labelFontColor: "black",
			lineThickness: 0,
	        tickLength: 15,
	        tickColor: '#ffffff',
			gridThickness: 1,
			gridDashType: "dash",
			gridColor: "#E8E8E8",
		    labelMaxWidth: 100,  
		    labelWrap: true,
		    //labelAutoFit: true
		    //interval: 1,
		    //suffix: " "
	     },
	     axisY:{
	    	 valueFormatString:"0'%'",
	    	 labelFontFamily: "Helvetica",
	    	 labelFontSize: 13,
	    	 //labelFontWeight: "bold",
	    	 labelFontColor: "black",
	    	 //lineThickness: 0,
	    	 gridThickness: 1,
	    	 gridDashType: "dash",
	    	 gridColor: "#E8E8E8",
	    	 interval: 50,
	    	 maximum: 120,
	    	 minimum: -120,
	    	 //viewport: 280,
	    	 //tickLength: 10,
	    	 //valueFormatString: ("#'%'"),
			 //suffix: "",
			//labelMaxWidth: 50, 
			//labelWrap: false
			//labelAutoFit: false,
	     stripLines: [{
			value: 0,
			showOnTop: true,
			thickness:4,
			color:"#003366"
		 }] 
	    },
	   /* toolTip:{
	        enabled: false   
	    },*/
	    //dataPointWidth: 20,
		data: [{
			axisXType: "secondary",
			type: "column",
			indexLabelPlacement: "auto",  //auto, outside, inside
			indexLabel : "{rollMonthPenAmountArrVal}", //over bar value
		    indexLabelFontSize : 14,
		    indexLabelFontFamily: "Helvetica",
		    indexLabelFontColor: "#000000",
		   //showInLegend : true, //this will copy legend from chart
		    toolTipContent: "{toolTipContentValuesCol}%",
		    dataPoints : columnChartData
	     }]
		});
	chart.render();
}


function getMinMax(intArr) {
	var intArrNew = new Array();
	for(var i = 0; i< intArr.length ; i++){
		intArrNew[i] = Math.abs(intArr[i]);
	}
	var maxNo = Math.max.apply(null, intArrNew);
	return maxNo;
}

function perfPopulateNotificationData(){
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
			if(undefined !=$('#repRoleCode').val() &&  ''!=$('#repRoleCode').val() && $('#repRoleCode').val()!='AM1')
				setCustomerRight(custid);
				else if(undefined !=$('#repRoleCode').val() &&  ''!=$('#repRoleCode').val()){
					$('#custSegContainer').css('display','none');
				}
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
						var dispInfo='';
						if(undefined !=item.dispStatus && undefined!=item.dispStatusDate ){
							dispInfo=item.dispStatus+'- '+item.dispStatusDate;
						}
						if(item.sementType=='Growth'){
						 GHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Growth\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\');" style="color:floralwhite;">'+item.segmentName+'</a><div id="Growth_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>';
						 counter++;
						}
						else if(item.sementType=='Retention'){
						 RHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Retention\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\');" style="color:floralwhite">'+item.segmentName+'</a><div id="Retention_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>';
						 counter++;
						}
						else if(item.sementType=='Expansion'){	
						 EHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Expansion\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\');" style="color:floralwhite">'+item.segmentName+'</a><div id="Expansion_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>'
						 counter++;
						}
						
					});
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
			$("#ratTxtEditor").Editor();
			$("#expTxtEditor").Editor();
			$("#expObjTxtEditor").Editor();
			
			perfDashpopulateDataOnLoad(data);
		}
	})	
}

function perfDashpopulateDataOnLoad(data){
	var formData={};
	var custid=$("#reqCustNum").val();
	
	if(data != undefined && data !=null){ 
		$("#custNum").html('<a style="pointer-events:none;cursor: default;font-family:Helvetica !important;font-size:13px;color:#555;font-weight:bold">'+data.listOfCustProfileVO[0].custNum+'</a>');
		$("#division").val(data.listOfCustProfileVO[0].division);
		$("#lastContactedDateValueId").html(data.listOfCustProfileVO[0].lastContactedDate);

		if(data.listOfCustProfileVO[0].iamId != '-' && data.listOfCustProfileVO[0].iamId !=''){
			$("#cnoId").html("");
			$("#logId").html("");
			var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
			var compNameUrl=sfdcBaseUrl+'/'+data.listOfCustProfileVO[0].iamId;
			$("#compNameCont").html('<a href="'+compNameUrl+'" onclick="generateLogs(\'compnameMM\')"  oncontextmenu="generateLogs(\'compnameMM\')" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#0066c0;text-decoration:underline;" class="letterSpace" target="_blank"> '+data.listOfCustProfileVO[0].companyName+' </a>');
			
			$("#ulIamIdSec").css("display","block");
			$("#ulIdOther").css("display","block");
			$("#sfdcId").css("display","block");
			$("#otherId").css("display","block");
			$('#wirDivId').css("display","block");
			/*var cnoUrl='https://na32.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%253FsrPos%253D0%2526srKp%253D001%26accid%'+data.listOfCustProfileVO[0].iamId;*/
			var cnoUrl=sfdcBaseUrl+'/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'+data.listOfCustProfileVO[0].iamId+'%26accid%3D'+data.listOfCustProfileVO[0].iamId;
			var cnoHtml='<a href="'+cnoUrl+'" onclick="generateLogs(\'createopp\')" oncontextmenu="generateLogs(\'createopp\')" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;" target="_blank">Create New Opp.</a>'+
			' <a href="'+cnoUrl+'" onclick="generateLogs(\'createopp\')" oncontextmenu="generateLogs(\'createopp\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 19px;margin-right: -8px;float:right;" id="cnoIconId" class="fa fa-hand-o-up"></i></a>';
			$("#cnoId").html(cnoHtml);
			$("#ctaCreateOpp").attr("href",cnoUrl);
			$("#menuItemIcon7").attr("href",cnoUrl);
			var logUrl=sfdcBaseUrl+'/00T/e?what_id='+data.listOfCustProfileVO[0].iamId;
			
			var logHtml='<a href="'+logUrl+'" onclick="generateLogs(\'logtask\')" oncontextmenu="generateLogs(\'logtask\')" target="_blank" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;">Log A Task</a>'+
			' <a href="'+logUrl+'" onclick="generateLogs(\'logtask\')" oncontextmenu="generateLogs(\'logtask\')" target="_blank" ><i style="padding-top: 4px;color:#fff;font-size: 18px;margin-right: -8px;float:right;" id="logIconId" class="fa fa-file"></i></a>';
			$("#logId").html(logHtml);
			$("#menuItemIcon8").attr("href",logUrl);
			var cls=$("#cnoHead").attr("class");
			if(undefined != cls && ''!=cls && cls.indexOf("cnoCollapse")==-1){ 
				$("#cnoHead").removeClass("cnoCollapse");
				$("#cnoHead").addClass("cnoExpand");
				$("#logHead").removeClass("logCollapse");
				$("#logHead").addClass("logExpand");
				$("#sfdcId").css("display","block");
				$("#otherId").css("display","block");
				$('#wirDivId').css("display","block");
			}else{
				$("#cnoHead").addClass("cnoCollapse");
				$("#cnoHead").removeClass("cnoExpand");
				$("#logHead").addClass("logCollapse");
				$("#logHead").removeClass("logExpand");
				$("#sfdcId").css("display","none");
				$("#otherId").css("display","none");
				$('#wirDivId').css("display","none");
			}
		}else{
			$("#compName").html(data.listOfCustProfileVO[0].companyName);
			$("#ulIamIdSec").css("display","none");
			$("#cnoId").html("");
			$("#ulIdOther").css("display","none");
			$("#logId").html("");
			$("#sfdcId").css("display","none");
			$("#otherId").css("display","none");
			$('#wirDivId').css("display","none");
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

		var address='';
		var cityStateZip='';
		if(undefined != data.listOfCustProfileVO[0].companyAddr1 && '' != data.listOfCustProfileVO[0].companyAddr1 && (data.listOfCustProfileVO[0].companyAddr1.length > 1)){
			address=" "+data.listOfCustProfileVO[0].companyAddr1+" "+data.listOfCustProfileVO[0].companyAddr2;
			if(address!=' ' && address !='  ')
				address=address+"</br>";
			if(data.listOfCustProfileVO[0].companyCity !=''){
				cityStateZip=" "+data.listOfCustProfileVO[0].companyCity;
				if(cityStateZip!='' && cityStateZip!=' ')
					cityStateZip=cityStateZip+", "+data.listOfCustProfileVO[0].companyState+" "+formatZip(data.listOfCustProfileVO[0].companyZip);

				cityStateZip=cityStateZip+"</br>";
				address=address+cityStateZip;
			}
		}
		if((data.listOfCustProfileVO[0].companyPhone != undefined && data.listOfCustProfileVO[0].companyPhone != '') && (address !='')){
			address=address+'<span onclick="javascript:logChangeSatusActivity(8088,\''+formatPhone(data.listOfCustProfileVO[0].companyPhone)+': clicked in Customer Profile Grid in WIR\','+custid+')">'+formatPhone(data.listOfCustProfileVO[0].companyPhone)+'</span>';
			$("#addrTxt").css("display","block");
		}else if((address =='') && (data.listOfCustProfileVO[0].companyPhone == undefined || data.listOfCustProfileVO[0].companyPhone == '')){
			address="No Address or Phone Found";
		}else if(address=='' && (data.listOfCustProfileVO[0].companyPhone != undefined && data.listOfCustProfileVO[0].companyPhone != '')){
			address="P. "+'<span onclick="javascript:logChangeSatusActivity(8088,\''+formatPhone(data.listOfCustProfileVO[0].companyPhone)+': clicked in Customer Profile Grid in WIR\','+custid+')">'+formatPhone(data.listOfCustProfileVO[0].companyPhone)+'</span>';
		}else if((address !='') && (data.listOfCustProfileVO[0].companyPhone == undefined || data.listOfCustProfileVO[0].companyPhone == '')){
			address=address+"Phone Number Not Found";
			$("#addrTxt").css("display","block");
		}

		$("#custAdd1").html(address);
		$('#dataTables-example1').removeClass('display').addClass('table table-striped table-bordered');
		$('.dataTables_filter input[type="search"]').attr('placeholder','Search').css({'width':'250px','display':'inline-block'});

	}
}


function createPerfDataAndPieChart(perfDashboardCategoryVOList) {
	if (perfDashboardCategoryVOList != null && perfDashboardCategoryVOList != undefined && perfDashboardCategoryVOList != '') {
		var categoryNameArr = new Array();
		var currYrToDateSalesArr = new Array();
		var yrToDateSalesPercentArr = new Array();
		var index = 0;
		var currYrToDtSalesTot = 0;
		var currYrToDateSalesValue = 0;

		$.each(perfDashboardCategoryVOList, function(i, perfDashboardCategoryVO) {
			//categoryNameArr[index] = perfDashboardCategoryVO.categoryName+"\xA0"+"\xA0";
			categoryNameArr[index] = perfDashboardCategoryVO.categoryName;
			currYrToDateSalesValue = Math.round((perfDashboardCategoryVO.currYrToDateSales));
			currYrToDateSalesArr[index] = currYrToDateSalesValue;
			yrToDateSalesPercentArr[index] = Math.round((perfDashboardCategoryVO.yrToDateSalesPercent));
			currYrToDtSalesTot = currYrToDtSalesTot + currYrToDateSalesValue;
			++index;
		});

		if(currYrToDtSalesTot > 0){
			currYrToDtSalesTot = currencyFormatNoDec(currYrToDtSalesTot);
		}else{
			currYrToDtSalesTot = "NO SALES FOUND FOR THIS TIME DURATION FOR ANY CATEGORY";
		}

		//alert("currYrToDtSalesTot-->"+ currYrToDtSalesTot);
		//alert("categoryNameArr-->"+categoryNameArr +"  currYrToDateSalesArr-->"+currYrToDateSalesArr+ "  yrToDateSalesPercentArr--->"+yrToDateSalesPercentArr);
		if (undefined != categoryNameArr && '' != categoryNameArr && categoryNameArr.length > 0	
				&& currYrToDateSalesArr.length > 0 && yrToDateSalesPercentArr.length > 0 ) {	
			piedata = getLableValueMapPie(categoryNameArr, currYrToDateSalesArr, yrToDateSalesPercentArr);
			createPerfPieChartDoughnut(piedata, currYrToDtSalesTot);
			populateDoughNutLegend(yrToDateSalesPercentArr)
		} else {
			var yrToDateSalesPercentArr = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			populateDoughNutLegend(yrToDateSalesPercentArr)
			$("#chartContainerPie").html("NO SALES FOUND FOR THIS TIME DURATION")
			$("#chartContainerPie").css("text-align", "center");
			$("#chartContainerPie").css("padding-top", "25%");
			$("#chartContainerPie").css("padding-right", "10%");
			$("#chartContainerPie").css("padding-left", "10%");
			$("#chartContainerPie").css("font-weight", "700");
			$("#chartContainerPie").css("font-family", "Helvetica");
			$("#chartContainerPie").css("font-size", "20px");
			$("#chartContainerPie").css("color", "darkgrey");
		}

	} else {
		var yrToDateSalesPercentArr = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		populateDoughNutLegend(yrToDateSalesPercentArr)
		$("#chartContainerPie").html("NO SALES FOUND FOR THIS TIME DURATION")
		$("#chartContainerPie").css("text-align", "center");
		$("#chartContainerPie").css("padding-top", "25%");
		$("#chartContainerPie").css("padding-right", "10%");
		$("#chartContainerPie").css("padding-left", "10%");
		$("#chartContainerPie").css("font-weight", "700");
		$("#chartContainerPie").css("font-family", "Helvetica");
		$("#chartContainerPie").css("font-size", "20px");
		$("#chartContainerPie").css("color", "darkgrey");
	}
}


function createPerfPieChartDoughnut(pieData, currYrToDtSalesTot) { 
	pieChartDataSet = pieData;
	currYrToDtSalesTotGlbVar = currYrToDtSalesTot
	
	var chart = new CanvasJS.Chart("chartContainerPie", {
		//height : 350,
		//theme: "theme1", 
		title : {
			text :  currYrToDtSalesTot,
			dockInsidePlotArea: true,
			maxWidth: 450,
			verticalAlign: "center",
			//horizontalAlign: "center",
			fontFamily : "Helvetica",
			fontSize : 26,
			fontWeight : "bolder",
			fontColor : "#555"
		},
		legend : {
			horizontalAlign: "right",
			verticalAlign: "center",
			fontFamily : "Helvetica",
			fontSize : 14,
			markerMargin: 7,
			fontWeight : "bolder",
			fontColor : "#555",
			maxWidth : 600,
			itemWidth : 350

		},
		 toolTip:{
			   fontStyle: "normal",
		 },
		data : [{
			type : "doughnut",
			indexLabel : "${y}",
			indexLabelPlacement: "auto",  //auto, outside, inside
			indexLabelFontSize : 15,
			indexLabelFontFamily: "Helvetica",
			indexLabelFontColor: "#000000",
			toolTipContent: "{toolTipContentValuesPie}",
			showInLegend : false,
			legendText : "{legendText1}",
			//legendMarkerType : "square",
			 //radius: "95%", 
		    innerRadius: "55%",  
			dataPoints : pieData
			
		}]
	});
	chart.render();
}


function populateDoughNutLegend(yrToDateSalesPercentArr){
	var i;
	var divId;
	for(i = 0 ; i< yrToDateSalesPercentArr.length ; i++){
		divId = "salesPer"+i;
		$("#"+divId).html(yrToDateSalesPercentArr[i]+"%");
	}
}

function getLableValueMapColChart(categoryNameArr, rollMonthPenAmountArr, rollMonthPenPercentArr, indexLabelFontColorArr){
	var colorsArr=['#417505','#159B9B','#2D435E','#C8CBD0','#9CE2CF',' #D0DF00','#FF9A2C','#5F9EA0','#DD1D1D', '#FCD78D'];
	var count=-1;
	return categoryNameArr.map(function(label){
		count++;
		return { y: rollMonthPenPercentArr[count], toolTipContentValuesCol: rollMonthPenPercentArr[count], rollMonthPenAmountArrVal: rollMonthPenAmountArr[count], indexLabelFontColor: indexLabelFontColorArr[count],	label: categoryNameArr[count], color:colorsArr[count] }
	});
}


function getLableValueMapPie(categoryNameArr,valArr,valPercentArr){
	var values = valArr;
	var colorsArr=['#417505','#159B9B','#2D435E','#C8CBD0','#9CE2CF',' #D0DF00','#FF9A2C','#5F9EA0','#DD1D1D', '#FCD78D'];
	var count=-1;
	return categoryNameArr.map(function(label){
		count++;
		return { y: values[count] , toolTipContentValuesPie: categoryNameArr[count], legendText1:" - "+label+" "+(valPercentArr[count])+"%" , color:colorsArr[count]} 
	});
}


function getLableValueMapBarChart(categorySalesDollValArr, categoryAllPercArr, categoryNameArr){
	var colorsArr=['#0099CC','#003366'];
	var count=-1;
	return categoryNameArr.map(function(label){
		count++;
		return { y:categoryAllPercArr[count], label: categoryNameArr[count], categorySalesDollValue: categorySalesDollValArr[count],  color:colorsArr[count], toolTipContentValuesBar: categoryAllPercArr[count]+"%" }
	});
}


function populateDateALL(){
	var dt = new Date();
	dt.setDate((dt.getDate() - 90));
	var dd = dt.getDate();
	/*if (dd < 10) {
		dd = '0' + dd;
	}*/
	var mm = (dt.getMonth()+1);
	/*if (mm < 10) {
		mm = '0' + mm;
	}*/ 
	var currDtFrom = mm+"/"+dd;
	
	var currentDt = new Date();
	currentDt.setDate((currentDt.getDate() - 1));
	var dd1 = currentDt.getDate();
	/*if (dd1 < 10) {
		dd1 = '0' + dd1;
	}*/
	var mm1 = (currentDt.getMonth()+1);
	/*if (mm1 < 10) {
		mm1 = '0' + mm1;
	}*/ 
	var currDtTo = mm1+"/"+dd1;
	
	var dateToDisplayBar = currDtFrom + " - " + currDtTo;
	var dateToDisplayBar1 = "1/1" + " - " + currDtTo;
	$("#dateToDisplayBar").html(dateToDisplayBar);
	$("#dateToDisplayBar1").html(dateToDisplayBar1);
	
	var dateToDisplayColumn = currDtFrom + " - " + currDtTo;
	var dateToDisplayPie = "1/1" + " - " + currDtTo;
	$("#dateToDisplayColumn").html(dateToDisplayColumn);
	$("#dateToDisplayPie").html(dateToDisplayPie);
	//$("#dateToDisplayPie1").html(dateToDisplayPie);
	
}


function populateRefreshDateALL(refreshDt) {
	if (!isPopRefreshDtALLCalledGlbVar) {
		isPopRefreshDtALLCalledGlbVar = true;
		var d = new Date(refreshDt.replace(' ', 'T'));
		var dt = '<img src="./resources/img/calender_bottom.png" width="14" height="15" style="margin-top:1px;"/>'	+ " "+ (d.getMonth() + 1) + "/" + d.getDate() + "/"
				+ d.getFullYear();
		var hrs = d.getHours();
		var min = d.getMinutes();
		var type = (hrs <= 12) ? " AM" : " PM";
		if (hrs > 12) {
			hrs = hrs - 12;
		}
		var time = hrs + ":" + min + type;
		var time1 = " | "	+ '<img src="./resources/img/calender_time.png" width="14" height="15" style="margin-top:2px;" /> '	+ " " + time;

		$("#lastRefDtBossCore").html(dt + time1);
		$("#lastRefDtCatPerf").html(dt + time1);
		$("#lastRefDtPie").html(dt + time1);
		$("#lastRefDtCI").html(dt + time1);
	}
}


function createCustomerInsightMessage() {
	var custid = $("#reqCustNum").val();
	var formData = {};
	$.ajax({
		dataType : "json",
		url : ctx + "/getCustInsightData/" + custid,
		type : "POST",
		cache : false,
		perfDashboardCategoryVOListCI : JSON.stringify(formData),
		timeout : 1000000,
		success : function(perfDashboardCategoryVOListCI, textStatus, jqXHR) {
			if (perfDashboardCategoryVOListCI != null && perfDashboardCategoryVOListCI != undefined) {
				var displayInsight = false;
				var custInsightMesg = "";
				var HTML ='<ul>'
				var refreshDt ="";	
				var noOfMesg = 0;
				
				
				
				$.each(perfDashboardCategoryVOListCI, function(i, perfDashboardCategoryVO) {
					refreshDt = perfDashboardCategoryVO.refreshDt;
					custInsightMesg = perfDashboardCategoryVO.mesgDesc;
					//alert(custInsightMesg);
					if(custInsightMesg != null && custInsightMesg != undefined) {
						HTML +='<li>'
						HTML += custInsightMesg ;
						HTML +='</li>'
						displayInsight = true;
						noOfMesg++;
					}
				});
				HTML +='</ul>'
				/*
				
				 HTML += ' <div id="refreshDtCustIn" '
					+ '  style="border:none;font-family: Helvetica;background-color:#F1F3FA;margin-top: 4%; overflow-x:auto;'
					+ ' white-space: nowrap;font-size: 11px; font-weight: bold; color: #555;float:right; text-align: right; '
					+ ' display:table-cell; vertical-align: bottom;"> '
					+ ' Last Refresh Date: <span style="font-family:Helvetica !important;font-size:11px;' 
					+ ' color:#555;font-weight:bold" id="lastRefDtCI" class="letterSpace"></span> </div>' 
				*/	
					
				//alert(HTML);
				if(displayInsight){
					$("#CustomerInsightTextID").html(HTML);
					
					setInsightsHeight();
					populateRefreshDateALL(refreshDt);
					
				}else{
					/*$("#CustomerInsightTextID").html("No data to display"); */
					$("#custInsightColId").css('display','none'); 
					$("#custPerfColId").attr('class', 'col-lg-12');
				}
			}else {
				/*$("#CustomerInsightTextID").html("No data to display"); */
				$("#custInsightColId").css('display','none'); 
				$("#custPerfColId").attr('class', 'col-lg-12');
			}
		}
	})
	
	
}

function setInsightsHeight() {
	/*var barChartHeight = $("#barChartContentID").height();
	$("#custInsightAllID").css({'height' : (barChartHeight+2)});
	$("#CustomerInsightTextID").css({'height' : (barChartHeight-96)});*/
	
	
	
	var adjustedHeight = $("#bossPortletBodyId").height() - 40;
	
	
	$("#CustomerInsightTableRow").height(adjustedHeight);
	
	
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
					$('#example').html(data.valueAttributionScore);
					// #5cb85c
					var vapScore='<div style="padding: 18px 20px 10px 20px;"><div style=""><span id="Mature" style="font-family: Helvetica !important; font-size: 12px !important;font-weight: bold;color:rgb(0, 128, 0);border-radius:3px !important;padding:5px;letter-spacing:1px;">MATURE - 13+ MONTHS</span></div>'
						+'<div id="DefectorHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Defector" style="  color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Defector</span> - Less active, '+"'value shoppers, that need to be reminded about the overall value Staples Advantage delivers'"+'</span></div>'
						+'<div id="LapsingHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Lapsing" style=" color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Lapsing</span> - Valuable customers with growth potential at a risk of leaving Staples Advantage</span></div>'
						+'<div id="NovaHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Nova" style="  color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Nova</span> - Sticky cross-category buyers with high growth potential</span></div>'
						+'<div id="SupernovaHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Supernova" style="  color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Supernova</span> - Our Most Valuable Customers with large spend across almost all core and BOS categories and make frequent purchases</span></div></div>'

						+'<div style="padding: 18px 20px 10px 20px;"><div style=""><span id="Transit" style="font-family: Helvetica !important; font-size: 12px !important;font-weight: bold;color: orange;border-radius:3px !important;padding:5px;letter-spacing:1px;">TRANSITIONING - 7 TO 12 MONTHS</span></div>'
						+'<div id="StarHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Star" style=" color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Star Potential</span> - Firmly believes in Staples Advantage, great prospects for more complex products and services</span></div>'
						+'<div id="StarsHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"><span style=""><span id="Stars" style=" color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Stars</span> - Moderate core and BOS spend</span></div>'
						+'<div id="LateHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Late" style="  color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Late Adopter</span> - Still needing a '+"'reason to believe in Staples Advantage'"+'</span></div></div></div>'

						+'<div style="padding: 18px 20px 10px 20px;"><div style=""><span id="Young" style="font-family: Helvetica !important; font-size: 12px !important;font-weight: bold;color: red;border-radius:3px !important;padding:5px;letter-spacing:1px;">YOUNG -  0 TO 6 MONTHS</span></div>'
						+'<div id="RisingHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Rising" style="color:black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Rising Stars</span> - High initial office and boss spend, show good growth potential</span></div>'
						+'<div id="SlowHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Slow" style="color:black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Slow Starter</span> - Moderate core and BOS spend</span></div>'
						+'<div id="NotHead" style="border-radius:5px !important;padding: 5px 0px 5px 12px; font-size: 12px; color: #666;font-family: Helvetica;"> <span style=""><span id="Not" style="color: black;font-weight: bold;font-size: 13px;font-family:Helvetica;">Not Engaging</span> - Still needing a '+"'reason to believe in Staples Advantage'"+'</span></div></div></div>'

						$("#tempData").html(vapScore)
						var id='';
					if(undefined !=$('#example').html()){
						if(($('#example').html()).indexOf(" ")!=-1)
							id=($('#example').html()).split(" ")[0];
						else
							id=($('#example').html());
					}
					//$("div#"+id+"Head").css("background-color","#286090")
					if(id == 'Rising' || id == 'Slow' || id == 'Not') {
						$('#example').css("background-color",document.getElementById('Young').style.color)
						$("div#"+id+"Head").css("background-color",document.getElementById('Young').style.color)
					}
					else if(id == 'Star' || id == 'Stars' || id == 'Late') {
						$('#example').css("background-color",document.getElementById('Transit').style.color)
						$("div#"+id+"Head").css("background-color",document.getElementById('Transit').style.color)
					}
					else if(id == 'Defector' || id == 'Lapsing' || id == 'Nova' || id == 'Supernova') {
						$('#example').css("background-color",document.getElementById('Mature').style.color)
						$("div#"+id+"Head").css("background-color",document.getElementById('Mature').style.color)
					} else {
						$('#example').css("background-color","#286090")
					}

					$("div#"+id+"Head").css("color","#fff")
					$("span#"+id).css("color","#fff")
					$("div#"+id+"Head").css("padding","10px")

					$('#example').popover({
						html : true,
						placement: 'bottom',
						content : $("#tempData").html()
					}).on("click", function () {
						var _this = this;
						$(this).popover("show");
						$(".popover").on("mouseleave", function () {
							$(_this).popover('hide');
						});
					}).on("mouseleave", function () {
						var _this = this;
						setTimeout(function () {
							if (!$(".popover:hover").length) {
								$(_this).popover("hide");
							}
						}, 300) });
				}else{
					$("#example").remove();
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
					}).on("click", function () {
						var _this = this;
						$(this).popover("show");
						$(".popover").on("mouseleave", function () {
							$(_this).popover('hide');
						});
					}).on("mouseleave", function () {
						var _this = this;
						setTimeout(function () {
							if (!$(".popover:hover").length) {
								$(_this).popover("hide");
							}
						}, 300) });;
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
		"sAjaxSource": ctx+"/getAllCustomers/"+custid+"/"+selPlayType,
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
	var formData={'searchText' : searchText, 'acctId': acctId , 'custNum': $('#query').val()};
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
						opencustDetails(data);
					}
				}
				
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
		
	});
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

function checkUundefinedNullBlankZero(val) {
	if ((undefined != val) &&   ('null'!=val) && (null !=val) && ('0' !=val) && (0 !=val) ) {
		return val;
	} else {
		return "-";
	}
}

function opencustDetails(cNum){
	$("#customerForm").attr("action","./home_template2")
	$("#reqCustNum").val(cNum);
	$('#customerForm').submit();

}

function checkUundefined(val) {
	if (undefined != val && val != 'null') {
		return val;
	} else {
		return "";
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

function formatPhone(phoneNumber) {
	if(phoneNumber.indexOf('(') != -1) {
		phoneNumber = phoneNumber.slice(1).replace(")",".").replace("-",".").replace(" ","");
	} else {
		phoneNumber = phoneNumber.replace(/-/g,".");
	}
	return phoneNumber;
}

//if no. contain minus then changing tostring via toFixed retain minus
function currencyFormatNoDec(num) {
	var sym = "$";
	if(undefined != num && '' !=num && null !=num){
		val = sym + (num.toFixed(0)).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
		return val;
	}
	else
		return (sym+num);	
}

function openPerfDashboard(){
	$("#customerForm").attr("action","./perfDashboardAction");
	$('#customerForm').submit();
}

function openMainDashboard(){
	$("#customerForm").attr("action","./home_template2");
	$('#customerForm').submit();
}

function perfDisableSideBar(){
	// $("#menuItemId1").hide(); these are slow as compare to below
	/*$("#menuItemId1").css('display','none'); //Users
	$("#menuItemId2").css('display','none'); //Orders
	$("#menuItemId4").css('display','none'); //ShipTo
	$("#menuItemId5").css('display','none'); //Recommendations
	$("#menuItemId6").css('display','none'); //Stores Near By
	$("#ulPlaySec").css('display','none');  //All- CALL TO ACTION
*/	//$('#ulIamIdSec').css("padding-top","25px");
	$('#ulIdOther').css("padding-top","5px");
	// $("#ulIamIdSec").removeAttr("href");  //All ON SFDC
	
	$("#menuItemId0").css("background-color","#1d2939");
	$("#menuItemId0").css("color","#fff");
	$("#menuItemId0").css("border","none");
	
	$("#wirDivId").css("color","#444");
	$("#wirDivId").css("background-color","#fff");
	$("#wirDivId").css("border","border: 1px solid #444;");
	
	$("#menuItemIdWIR").css("background-color","#fff");
	$("#menuItemIdWIR").css("color","#444");
}


/*$("#growthId").click(function(e){
	return false;
});

$("#retentionId").click(function(){
	return false;
});

$("#expansionId").click(function(){
	return false;
});	*/


/*$('ul li').click(function(e) {  //Left menu -Dashboard, Users ..Stores Near by
	return false;
});*/

/*$("ul li a[id^=menuItemId]").click(function(){  
	$("ul li a[id^=menuItemId]").css("background-color","#1d2939");
	$("ul li a[id^=menuItemId]").css("color","#fff");
	$("ul li a[id^=menuItemId]").css("border","none");
	if(undefined != this && undefined !=this.id)
	var menuItemId=this.id;
	if(undefined !=menuItemId && '' !=menuItemId && menuItemId !='menuItemId10'){
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
});*/



$("#tglId").click(function(){  //copied jsp  $("#tglId").click(function(){
	var val=$("#stplId").html();
	if(undefined !=val && ''!=val){  
		$("#stplId").html("");
		$("#stplLogoId").css("display","none");
		$("#notiId").html("");
		$("#gId").html("");
		$("#rId").html("");
		$("#eId").html("");
		$('#ulMenu li').css("padding-left","0px");
		$('a[id^=menuItemIcon]').css("display","block");
		$('a[id^=menuItemId]').css("display","none");
		$("#cnoHead").removeClass("cnoExpand");
		$("#cnoHead").addClass("cnoCollapse");
		$("#logHead").removeClass("logExpand");
		$("#logHead").addClass("logCollapse");
		$("#sfdcId").css("display","none");
		$("#otherId").css("display","none");
		$('#wirDivId').css("display","none");
		$('#ulPlaySec').html("");
		$('#ulPlaySec').css("display","none");
		//$("#tglId").css("padding-top","50px");
		$("#tglId").css("width","100%");
		$(".canvasjs-chart-canvas").removeAttr("style");
		/* if(($(window).width()<=1024) && ($(window).width() > 768)){
			$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
		}else{ 
			$("li#slideBar").attr("style","border: none; padding-right: 6px; border-radius: 20px !important; padding-left:45% !important;")
		} */
		
		$("#menuItemId1").css('display','none'); //Users
		$("#menuItemId2").css('display','none'); //Orders
		$("#menuItemId4").css('display','none'); //ShipTo
		$("#menuItemId5").css('display','none'); //Recommendations
		$("#menuItemId6").css('display','none'); //Stores Near By
		$("#ulPlaySec").css('display','none');  //All- CALL TO ACTION
		
		//$("ul li a[id^=menuItemId1]").css('display','none');
		//$("#ulMenu.fa fa-user").css('display','none'); 
		
		//$("ul.fa fa-user li").css('display','none'); 
		
		//$(".fa fa-user").hide();
		//$(".fa fa-user").removeAttr();
		//$(".fa fa-user").css('display','none');
		
		//$("#ulMenu").hide(); //Users
		//$('ul[class="fa fa-user"] li').setAttribute('style', 'display: none');
		//$("#menuItemId2").hide(); //Orders
		
		//$("#ulMenu").removeAttr(); //Users
		//$("#menuItemId2").removeAttr(); //Orders
		
		//$("#ulMenu.li a.fa fa-user").css('display','none'); //Users
		//$("#menuItemId2").css('display','none'); //Orders
		
	}else{
		$("#stplId").html("STAPLES");
		$("#stplLogoId").css("display","block");
		$("#notiId").html("Call To Action");
		$("#gId").html("Growth");
		$("#rId").html("Retention");
		$("#eId").html("Expansion");
		$('#ulMenu li').css("padding-left","10px");
		$('a[id^=menuItemIcon]').css("display","none");
		$('a[id^=menuItemId]').css("display","block");
		$("#cnoHead").removeClass("cnoCollapse");
		$("#cnoHead").addClass("cnoExpand");
		$("#logHead").removeClass("logCollapse");
		$("#logHead").addClass("logExpand");
		$("#sfdcId").css("display","block");
		$("#otherId").css("display","block");
		$('#wirDivId').css("display","block");
		$('#ulPlaySec').html(playSecData);
		$('#growUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
		 $('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
		 $('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 3px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;");
		$('#ulPlaySec').css("display","block");
		$("#tglId").css("padding-top","0px");
		$("#tglId").css("width","19%");
		$(".canvasjs-chart-canvas").attr("style","width:100% !important;");
		
		/*$("#menuItemId1").css('display','none'); //Users
		$("#menuItemId2").css('display','none'); //Orders
		$("#menuItemId4").css('display','none'); //ShipTo
		$("#menuItemId5").css('display','none'); //Recommendations
		$("#menuItemId6").css('display','none'); //Stores Near By
		$("#ulPlaySec").css('display','none');  //All- CALL TO ACTION
        */		
		$('.modal-dialog').draggable({
		    handle: ".modal-header"
		});
	}
}); 


function showTrainingPopUp() {
	$('#training_modal').modal({
		"backdrop"  : "static",
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
}

function openSuperUserDetails(){
	  // log user activity; view user details
	  logUserActivity(2002, 'View User Details');
	  
	  var formData={};
	  var dt = geDataRefreshTime('WEBACTIVITY');
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
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var sfdcBaseUrl=$("#sfdcAppBaseUrl").val();
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
							            	  var accid=item.iamId;
							            	  var emailsfdc=checkUundefined(contactEmail);
							            	  var firstnamesfdc=item.firstName;
							            	  var lastnamesfdc=item.lastName;
							            	  var phonesfdc=checkUundefined(contactPhone);
							            	  //if(undefined !=name)
							            	  var sfdcurl="accid="+accid+"&ent=Contact&con15="+emailsfdc+"&name_firstcon2="+firstnamesfdc+"&name_lastcon2="+lastnamesfdc+"&con10="+phonesfdc;
							            	  
							            	  var userSfdcUrl=sfdcBaseUrl+'/003/e?'+encodeURIComponent(sfdcurl);
							            	  contactDetails = '<div style="" id="orderContact' + cnt +'" class="toolTip">'+
							              						'<div style="font-size:13px;padding-top:5px;padding-left:5px;padding-right:10px;color:crimson;letter-spacing:1px;" id="orderStat" >'+item.orderContact
							              						
																+'<a href="javascript:showSfdcPopUp(\''+userSfdcUrl+'\');"><i class="fa fa-plus-square" aria-hidden="true" style="padding-left: 5px;font-size: 116%;vertical-align: middle;"></i></a></div>'
							              						+'<div style="padding-left:5px;letter-spacing:1px;" >Email: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;text-decoration:underline;cursor:pointer;" onclick="javascript:openOutlook(\''+contactEmail+'\');">'+ checkUundefined(contactEmail) + '</span></div>'+
							              						'<div style="padding-left:5px;letter-spacing:1px;">Phone: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue; "onclick="javascript:logChangeSatusActivity(8088,\''+contactPhone+': clicked in User Details Grid\','+custid+')">'+ contactPhone + '</span></div>'+
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

											+'<td class="datatablesTd">'
											if (hasContactDetails) {
												mkttrHTML +='<a id="orderCon'+cnt+'" style="text-decoration:none;outline:0 !important;" '
												+'tabindex="0" class="" role="button" data-toggle="popover" data-trigger="manual">'
												 + checkUundefined(item.orderContact) +contactDetails  +'</a>';
								              }else{
								            	  mkttrHTML +='<span id="" style="color:#444444;padding-right:8px;text-decoration:none;cursor:default;" '
													+'tabindex="0" class="" >'
													 + checkUundefined(item.orderContact) +contactDetails  +'</span>';
								              }
	
							              if(undefined != item.iamId && '-' != item.iamId && '' !=item.iamId){
								            	 $("#addContactId").css("display","block");
								            	 var sfdcUrl=sfdcBaseUrl+'/003/e?accid='+item.iamId+'&ent=Contact';
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
											 +'</tr>';
										
										 cnt++;
									});
									SuperUserDeatilCount=cnt-1;
									ActiveUserDeatilCount=cnt-1;
								}
								$('#superUserTableId').html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-example"  ><thead><tr>'
												+'<th>Order <br/>Contact </th>'
												+'<th>No. Of Orders <br/><span style="">(CFY)</span></th>'
												+'<th>Total Spend<br/><span style="">(CFY)</span></th>'
												+'<th>No. Of <br/> Visits (CFY)</th>'
												+'<th>Last <br/> Visit Date</th>'
												+'<th>No. Of Orders<br/><span style="">(PTD)</span></th>'
												+'<th><span style="">Orders <br/>(LFY)</span></th>'
												+'<th><span style="">Total Spend<br/>(LFY)</span></th>'
												+'<th>SA.com <br/> Activity</th>'
												+'<th>Staples.com <br/> Activity</th>'
												+ '</tr>'
												+ '</thead><tbody>' + mkttrHTML
												+ '</tbody></table>');
								//<div style="color:rgb(0, 76, 116)"><span style="color:red">*</span> These fields include the last five years data.</div>');
								  $("#dataTables-example_processing").css("display","none");
								$('#dataTables-example').dataTable({
									"lengthMenu": [[5, 15, 25, -1], [5, 15,25, "All"]],
									"bSort": true,
									"oLanguage": { "sSearch": "Filter: "},
									"order": [[4,"desc"],[9,"desc"]],
									"bProcessing": true,
									"aoColumns": [{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true,"stype" : "date"},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true},
									{"bSortable": true}
									],
									"columnDefs": [
									               { "visible": false, "targets": 5 }
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
					                              
												}).on("click", function () {
											        var _this = this;
											        $(this).popover("show");
											        $(".popover").on("mouseleave", function () {
											            $(_this).popover('hide');
											        });
											    }).on("mouseleave", function () {
											        var _this = this;
											        setTimeout(function () {
											            if (!$(".popover:hover").length) {
											                $(_this).popover("hide");
											            }
											        }, 300) });
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
					
												}).on("click", function () {
											        var _this = this;
											        $(this).popover("show");
											        $(".popover").on("mouseleave", function () {
											            $(_this).popover('hide');
											        });
											    }).on("mouseleave", function () {
											        var _this = this;
											        setTimeout(function () {
											            if (!$(".popover:hover").length) {
											                $(_this).popover("hide");
											            }
											        }, 300) });
											}
										}
									   }	
									
								}).fnDraw();
								$("#dataTables-example td:nth-child(5)").css("white-space","nowrap");
								$('#dataTables-example_length label').css("color","#004c74");
								$('#dataTables-example_length label').css("font-size","15px");
								$('#dataTables-example_length label').css("font-family","helveticaneuebold");
								$('#dataTables-example_length label').children().attr("style","border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
								$('#dataTables-example_filter label').css("color","#004c74");
								$('#dataTables-example_filter label').css("letter-spacing","1px");
								$('#dataTables-example_filter').css("text-align","right");
								$('#dataTables-example_filter label').css("font-size","15px");
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

function openSuperUserModalInfo(name,index,fromWhere,abanStat,dotcomStat){
	// log user activity; view .Com activity
	var name= name.replace('openbracket','(');
  var name= name.replace('closebracket',')');
  var name= name.replace("quote","'");
	
  var mkttrHTML="";
var serachlistItem=$.each(   searchItemsListVOArr[index], function(i, item) {
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
         htmlData = htmlData.toString() + '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'+checkUundefined(column1[i].itemDescription)+'"_blank" style="text-decoration:none;cursor:default;"><img src='+thumbnail+' width="124" height="160" style="margin-top: 5px;width: 120px;height: 160px;"/>'
                  +'<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;" aria-hidden="true" data-rows="3">'
                     + truncateTitle(checkUundefined(column1[i].itemDescription))
                 +'</div></a>'
                
                +'<div class="a-row a-size-small"><a class="a-size-small a-link-child" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: 100px;" href="javascript:openUrl(\''+updatedUrl+'\')">'+checkUundefined(column1[i].skuNumber)+'</a></div>'
                +'<div class="a-row a-size-small">'+checkUundefined(column1[i].actDate).substring(0,11)+'</div>'
                + abanActivity

         +'</li>';
         
   }
   
  totalAbandonedLength=column1.length;
   var prefixData='<div id="amazon_scroller_aban'+index+'" class="amazon_scroller">'
                            +'<div id="abanId" class="amazon_scroller_mask"><ul id="SUulid">'
                            
   var suffixData='</ul></div><ul id="aban_nav" class="amazon_scroller_nav">'
                         +'<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                         +'<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
                         +'</ul><div style="clear: both"></div></div>'             
   $("#tab_1_1").html(prefixData+""+htmlData+""+suffixData);
   
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

   
}else{
  $("#tab_1_1").html("");
  $('#a1').removeAttr("data-toggle");
  $('#li1').addClass('disabled');
 
}
/*  Aban Data End  */
   
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
      
   }else if(fromWhere=='abandoned'){ 
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
   

  $('#searchStringId').html("");
  var searchStr="";
  var searchStrNull="";
  var searchItemStr="";
  var searchStrBlocked="";
  for(var count=0;count<serachlistItem.length;count++){
    	if (serachlistItem[count] !=undefined && serachlistItem[count]!=''){
    		var searchString = serachlistItem[count].searchItems.split(',');
    		if(undefined != searchString && '' !=searchString && searchString.length>1){ 
    		searchString.sort();
    		}
    		for(var index=0;index<searchString.length;index++){
    			var searchItemStr = searchString[index];
    	if (searchItemStr.indexOf('blocked') != -1){
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
}
function checkNull(val) {
	if (undefined != val && val != 'null' && null != val && val.length) {
		return 'Y';
	} else {
		return 'N';
	}
}
function checkUserGridFields(val) {
	if ((undefined != val) && ('null' != val) && (null != val) && ('0' != val) && (0 != val) && ('' != val) && ('-' != val) ) {
		return val;
	} else {
		return "";
	}
}

function openOrderDetails(isFromReturned){
	
	// log user activity; view order list
	logUserActivity(2004, 'View Order List');
	var Amount = new Array();
	

	var monthNew=parseInt(latestDateforOrder.substring(0,2));
	var yearNew=latestDateforOrder.substring(2,6);
	var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
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
	  var dt=getCurrentTime();
		$("#updateDateValueOrder span").html(dt+" ET");
	  var catid="ALL";
	  $("#dataTables-order_processing").html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 0px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
	  $("#dataTables-order_processing").css("display","block");
	  monthyear=month+""+year;
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthyear+"/"+catid+"/"+"NoChange",
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
												retFoundChk('X');
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
											 + formatNum(chkNegAmount(checkUundefined(item.netSpendAmount))) +'</td>'
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
								//alert(isFromReturned+"--"+retFound+"--");
								if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('N' == isFromReturned)){
									populateMonthYearData(data,monthNew,yearNew);
									 populateCatData(data);
									 $("#yearSel option[value="+monthNew+""+yearNew+"]").attr("selected","selected");
									 $('#datepickerTEXT').val(monthNameArr[monthNew-1]+" / "+yearNew);
							          $('#datepickerTEXT').css("font-weight","bold")
							          $('#datepickerTEXT').css("font-family","Hevletica")
							          $('#datepickerTEXT').css("font-size","14px");
										 $('#orderDetailsTabId').html("");
									$('#orderDetailsTabId').html(
											'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
													
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
								}else if(undefined !=isFromReturned && isFromReturned =='Y' && retFound=='N'){
									var monthYear='';
									var catId="ALL";
									if(month !=1){
										monthYear=(monthNew-1)+""+yearNew;
									}else if(month==1){
										monthYear= 12+""+(yearNew-1);
									}
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
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
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

function openOrderDetailsInfo(index,ordNum){
	// log user activity; view order details
	logUserActivity(2005, 'View Order Details');
	
	var inputString=$('#dataTables-order_filter input[type=search]').val();
	
	var contains=0;
	var mkttrHTML="";
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
			 +'<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber)+'</a>' +'</td>'
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
			
	})	
	
	if(contains == 1){
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
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
			
	})	
	
	if(contains == 1){
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);
		
	}		
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
    
    if (objPurchaseDetailsListVO[index].orderNumber.indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString).draw();
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

function getLatestFiscalDateOrder(){
	  
	  var custid= $("#reqCustNum").val();
	  
	var formData={};
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getLatestFiscalDateOrder/"+custid,
				//url : "/getLatestFiscalDate/"+custid,
				type : "POST",
				cache : false,
				async:false,
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
			$('#datepickerTEXT').datepicker( {
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
			 		  onChangeMonthOrCat(monthYear,catId,'N');
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

function onChangeMonthOrCat(monthYr,catid,isFromReturned){
	var Amount = new Array();
	retFound='N';
	  var formData={};
	  var custid=$("#reqCustNum").val();
	  var monthVal=$("#ui-datepicker-div .ui-datepicker-month :selected").val();
      var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
      var map = {"Jan":"1","Feb":"2","Mar":"3","Apr":"4","May":"5","Jun":"6","Jul":"7","Aug":"8","Sep":"9","Oct":"10","Nov":"11","Dec":"12","ALL":"0"};
	  var monthNameArr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
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
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/getOrderDetailsHighLevelData/"+custid+"/"+monthYr+"/"+catid+"/"+"OnChange",
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
									$("#updateDateValueOrder span").html(dt+" ET");
								}
								
					if((undefined !=isFromReturned && isFromReturned =='Y' && retFound=='Y') || ('Y' !=isFromReturned)){
						$('#orderDetailsTabId').html("");
					$('#orderDetailsTabId').html(
							'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
                                   
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
								$('#dataTables-order_filter label').css("color","#004c74");
								$('#dataTables-order_filter label').css("letter-spacing","1px");
								$('#dataTables-order_filter').css("text-align","right");
								$('#dataTables-order_filter label').css("font-family","helveticaneuebold");
								$('#dataTables-order_filter label').children().attr("style","border-radius:3px !important;color:#004c74;");
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
function retFoundChk(val){
	if((undefined!=val) && ('' != val) && (val=='X')){
		retFound='Y';
	}
	return val;
}

function openShipToDetails(){
	 // log user activity; viwe ship to data
	logUserActivity(2006, 'View Ship To Data');
	
	  var formData={};
	  var dt = geDataRefreshTime('MV_SHIP_TO_INFO');
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
									$.each(data, function(i, item) {

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
											 //+ calculateYOY(item.totalSpendCurYr,item.totalSpendLastYr) +'</td>'
											 + calculateYOY(item.totalSpendCurYr,item.TotalSpendLastYearByPeriod) +'</td>'
											 
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
												+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>YTD Growth compares CFY Spend to LFY Spend for the exact same time period.</div>');
								$("#dataTables-exampleShipTo_processing").css("display","none");
								$('#dataTables-exampleShipTo').dataTable({
									"lengthMenu": [[5, 15, 25, -1], [5, 15,25, "All"]],
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
								$('#dataTables-exampleShipTo_filter label').css("color","#004c74");
								$('#dataTables-exampleShipTo_filter label').css("letter-spacing","1px");
								$('#dataTables-exampleShipTo_filter').css("text-align","right");
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
		type : "POST",
		cache : false,
		data : formData,
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var cnt=0;
if (null != data && undefined != data && data.length == 0 ){
	alert("No orders for this location for the last 2 years");
	return;
}

		$.each(data, function(i, item) {
		

		if (undefined!= item.orderDate && '' != item.orderDate){
			var orderdate=item.orderDate.substring(0,10);
		}

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
    oTable.search(inputString).draw();

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
	$.ajax({
		dataType: "json",
		url : ctx+"/getOrderDetailsShipTO/"+custid+"/"+order_number+"/"+order_date,
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
			 + '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber) +'</a>' +'</td>'
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
    if (order_number.toString().indexOf(inputString) > -1) {
    	oTable.draw();
    } else {
    	oTable.search(inputString);
    	oTable.draw();
    }
 }})
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
function openRecommendations(){
	//clearCookie();
  $("#customerForm").attr("action","./recommAction")
  $('#customerForm').submit();
}
function openStoreLocator(){
	//clearCookie();
  $("#customerForm").attr("action","./storeLocAction")
  $('#customerForm').submit();
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
					if(undefined !=$('#repRoleCode').val() &&  ''!=$('#repRoleCode').val() && $('#repRoleCode').val()!='AM1')
						setCustomerRight(custid);
						else if(undefined !=$('#repRoleCode').val() &&  ''!=$('#repRoleCode').val()){
							$('#custSegContainer').css('display','none');
							$('#qualscoreContainer').css('display','none');
						}
						//setCustomerRightAngular(custid);
						var GHTML = "";
						var RHTML = "";
						var EHTML = "";
						var segNameArr=new Array();
						 $("#growUlId").html("");
						 $("#retUlId").html("");
						 $("#expUlId").html("");
						 $("#actionStat").css("display","none");
						 $("#notiId").css("display","block");
						if(data != undefined && data !=null){ 
							if(data.notiInfoVOList !=null && data.notiInfoVOList !=undefined && data.notiInfoVOList !=''){
								var counter=0;
								$.each(data.notiInfoVOList, function(i, item) {
									var dispInfo='';
									if(undefined !=item.dispStatus && undefined!=item.dispStatusDate ){
										dispInfo=item.dispStatus+'- '+item.dispStatusDate;
									}
									if(item.sementType=='Growth'){
									 GHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Growth\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\',\''+item.segLastRefDate+'\');" style="color:floralwhite;">'+item.segmentName+'</a><div id="Growth_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>';
									 counter++;
									}
									else if(item.sementType=='Retention'){
									 RHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Retention\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\',\''+item.segLastRefDate+'\');" style="color:floralwhite">'+item.segmentName+'</a><div id="Retention_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>';
									 counter++;
									}
									else if(item.sementType=='Expansion'){	
									 EHTML +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\'Expansion\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\',\''+item.segLastRefDate+'\');" style="color:floralwhite">'+item.segmentName+'</a><div id="Expansion_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>'
									 counter++;
									}
									var arrElem=((undefined!=item.sementType) && (''!=item.sementType))? item.sementType : '';
									if(undefined !=arrElem && ''!=arrElem && arrElem.indexOf('Growth')==-1 && arrElem.indexOf('Retention')==-1 && arrElem.indexOf('Expansion')==-1)
									segNameArr.push(arrElem);
								});
							}
						}
						segNameArr=jQuery.unique( segNameArr )
						//alert(segNameArr )
						if (GHTML != '')
							$("#growUlId").html(GHTML);
						else
							$("#growthPlayId").css("display", "none")
						if (RHTML != '')
							$("#retUlId").html(RHTML);
						else
							$("#retPlayId").css("display", "none")
						if (EHTML != '')
							$("#expUlId").html(EHTML);
						else
							$("#expPlayId").css("display", "none")
						if (GHTML == '' && RHTML == '' && EHTML == '' && (segNameArr.length)<=0) {
							$("#playdivId").css("display", "none");
							$("#actionStat").css("display", "block");
							$("#notiId").css("display", "none");
						}
						var htmlData='';
						if(data != undefined && data !=null){ 
							if(data.notiInfoVOList !=null && data.notiInfoVOList !=undefined && data.notiInfoVOList !=''){
								var count=0;
								$.each(segNameArr,function(index,arrItem){
									var htmlData='<div class="panel panel-default" style="border:none;" id="'+arrItem+'PanelPlayId">'
					                    +'<div class="panel-heading"  style="height:40px;text-align:left;padding:10px 15px;color: #fff; background-color:#1d2c3c;opacity:.9;" >'
					                    +'<a id="'+arrItem+'LinkId" style="text-decoration:none;" data-toggle="collapse" data-parent="#accordion" href="#'+arrItem+'PlayIdBody" class="collapsed" aria-expanded="true">'
					                     +'   <h4 class="panel-title">'
					                              +'<span style="color: #fff;font-family:Helvetica;font-size:14px;letter-spacing: .5px;" id="'+arrItem+'TextId">'+arrItem+'</span>'
					                              +'<i style="color:#fff;font-size: 90%;font-weight: bold !important; margin-right: -8px;float:right;" id="'+arrItem+'PlayIconId" class="fa fa-chevron-down"></i>'
					                       +' </h4> </a> </div>'
					                    +'<div id="'+arrItem+'PlayIdBody" style="background-color: #1d2939;padding-top: 0px;" class="panel-collapse collapse" aria-expanded="true">'
					                        +'<ul class="list-group" id="'+arrItem+'ListUlId" style="margin-right: 28px; margin-left: 10px; padding-top: 0px;">';
								$.each(data.notiInfoVOList, function(i, item) {
									if(item.sementType==arrItem){
										var dispInfo='';
										if(undefined !=item.dispStatus && undefined!=item.dispStatusDate ){
											dispInfo=item.dispStatus+'- '+item.dispStatusDate;
										}
										htmlData +='<li class="list-group-item" style="color:#bbb;border-bottom: 2px solid #999 !important;border-bottom-style: dotted !important;height: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;"><a href="javascript:openSubCallToAction(\''+item.segmentName+'\',\''+item.sementType+'\',\''+counter+'\',\''+item.segmentId+'\',\''+item.segmenFreq+'\',\''+item.segLastRefDate+'\');" style="color:floralwhite;">'+item.segmentName+'</a><div id="'+item.sementType+'_Sub_'+item.segmentId+'" style="color: #9ce2cf;font-style: italic;font-size: 12px;">'+dispInfo+'</div><span id="'+item.sementType+'-Icon_'+item.segmentId+'" class="pull-right text-muted small" style="display: none;padding-left: 15px;"><img width="20" height="20" src="./resources/img/Cta_loading.gif" style="filter: brightness(100%);"></span><span class="pull-right text-muted small">'+item.segmenFreq+'</span></li>';
										counter++;
									}	
								});
								 htmlData+='</ul></div></div>';
								 $('#allCtaSegments').append(htmlData);
								 $("[id$='LinkId']").click(function(){
										var Gclass=$(this).attr("class");
										if(undefined != Gclass && '' !=Gclass && 'collapsed' ==Gclass){
											$(this).find('i').attr("class","fa fa-chevron-up");
											if(undefined !=this && undefined!=this.id && ''!=this.id && (this.id).indexOf('LinkId')!=-1){
												var playName=(this.id).split('LinkId')[0];
												logUserActivity(2027, 'User has clicked on Call Order '+playName+' List');
											}	
										}else if(undefined != Gclass &&  '' ==Gclass){
											$(this).find('i').attr("class","fa fa-chevron-down")
										}
										
								});
								});
							}
						}
						
						 playSecData=$('#ulPlaySec').html();
						 $("[id$='UlId']").find("li:last").attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
						 //$('#retUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
						 //$('#expUlId li:last').attr("style","color:#bbb;eight: auto!important;padding: 13px 0px 15px 0px !important;font-family:Helvetica;font-size: 13px;border-width:0px !important;height:auto !important;");
						 perfDashpopulateDataOnLoad(data);
						}
			})	

}

function openSubCallToAction(subSegDesc,ctaName,segIndex,subSegId,frequency,refreshTime){
	var lablesIds=new Array();
	var custid=$("#reqCustNum").val();
	var ctaNameDesc='<span id="ctaName" style="color:#4d4d4d;font-size:16px;font-weight:bold;">'+ctaName+':</span>'
    +'<span id="ctaSegDesc" data-placement="bottom" data-toggle="tooltip" title="'+subSegDesc+'" style="color:#333333;font-size:16px;font-weight:bold;"> '+truncateDataWithLen(subSegDesc,60)+'</span>';
	$("#ctaNameDesc").html(ctaNameDesc);
	$("[data-toggle='tooltip']").tooltip({html:true});
	$("#updateDateValueCtaR").html(frequency);
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
			var cust_num=$("#reqCustNum").val();
			if(undefined !=data && null!=data && "null"!=data){
				generateTalkingPoints(data);
				populateAttributes(data,ctaName);
			var segId=data.seg;
			selDesposition='Not Started';
			$("#statusVal").html(selDesposition);
			$("#linkTaskId").html('1');
			showCallToActionPopUp();
			var dispHtml='';
			var disabledDispIds=['5','6','9'];
			$.each(data.dispStatus, function(i, item) {
				if(undefined!=subSegId && ''!=subSegId && '55'!=subSegId){
				dispHtml=dispHtml+'<li id="disp_'+item.split("~")[1]+'"><a href="#" style="">'+item.split("~")[0]+'</a><a style="display:none;color:deepskyblue !important;" href="#">'+item.split("~")[1]+'</a></li>'
					+'<li class="divider" style="margin:4px 0px;"></li>';
				}else if(undefined !=(item.split("~")[2]) && ''!=item.split("~")[2] && (disabledDispIds.includes(item.split("~")[2]))){
					dispHtml=dispHtml+'<li id="disp_'+item.split("~")[1]+'" style="pointer-events:none;" class="disabled"><a href="#" style="pointer-events:none;">'+item.split("~")[0]+'</a><a style="display:none;color:deepskyblue !important;pointer-events:none;" href="#">'+item.split("~")[1]+'</a></li>'
					+'<li class="divider" style="margin:4px 0px;"></li>';
				}else if(undefined !=(item.split("~")[2]) && ''!=item.split("~")[2]){
					dispHtml=dispHtml+'<li id="disp_'+item.split("~")[1]+'"><a href="#" >'+item.split("~")[0]+'</a><a style="display:none;color:deepskyblue !important;" href="#">'+item.split("~")[1]+'</a></li>'
					+'<li class="divider" style="margin:4px 0px;"></li>';
				}
			});
			if(dispHtml !=''){
			$("#statusList").html(dispHtml);
			$("ul#statusList li").click(function(){
				 var id=$(this).attr("id");
				 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
					 var link_task_id=id.split("_")[1];
					 selDesposition=$(this).children("a:first").html();
					 $("#statusVal").html(selDesposition);
					 $("#linkTaskId").html(link_task_id);
				 }	 
			  });
			}
			$("#applyCallToAction").unbind('click');
			$("#applyCallToAction").click(function(){
				var logInUserName=$("#loggedInUserName").val();
				var LoggedInUserID=$("#accId").val();
				
				
				var comment=$("#commentId").val();
				var oldComment=$("#oldCommentId").html();
				var statusVal=$("#statusVal").html();
				var linkTask=$("#linkTaskId").html();
				var oldDespVal=$("#oldStatusVal").html();
				var subject=checkUundefined($("#subjectInputText").val());
				var oldSubject=checkUundefined($("#oldSubjectInputText").val());
				
				$("#ctaSfdcConfModalBody").html("Ready to save to Salesforce.com?");
				if(checkModifyChanges(statusVal,oldDespVal,comment,oldComment,subject,oldSubject)){
					showCtaSfdcPopUp(segId,subSegDesc,ctaName,subSegId,cust_div,cust_num,logInUserName,comment,statusVal,linkTask,frequency,LoggedInUserID,subject,'save');
				}else{
					showNoChangePopUp();
				}
			 });
			$("#closeCallToAction").unbind('click');
			$('#closeCallToAction').click(function(){
				var logInUserName=$("#loggedInUserName").val();
				var LoggedInUserID=$("#accId").val();
			    var comment=$("#commentId").val();
				var oldComment=$("#oldCommentId").html();
				var statusVal=$("#statusVal").html();
				var linkTask=$("#linkTaskId").html();
				var oldDespVal=$("#oldStatusVal").html();
				var subject=checkUundefined($("#subjectInputText").val());
				var oldSubject=checkUundefined($("#oldSubjectInputText").val());
			    if(checkModifyChanges(statusVal,oldDespVal,comment,oldComment,subject,oldSubject)){
			    		$("#ctaSfdcConfModalBody").html("You haven't saved your changes.  Do you want to Save to SFDC?");
			    		showCtaSfdcPopUp(segId,subSegDesc,ctaName,subSegId,cust_div,cust_num,logInUserName,comment,statusVal,linkTask,frequency,LoggedInUserID,subject,'close');
			    }else{
			    	$('#callToAction_PopUp').modal('hide');
			    	$('#commonConfDialog').modal('hide');
			    }
			});
			
			if(undefined !=$('#ctaDisbledIds').val() && ''!=$('#ctaDisbledIds').val() && ($('#ctaDisbledIds').val()).indexOf('#')!=-1){
				var disabledSubSegIdArr=($('#ctaDisbledIds').val()).split('#');
				disabledSubSegIdArr=remove(disabledSubSegIdArr,'');
				var disabledStatus= disabledSubSegIdArr.includes(subSegId);
				if(undefined!=disabledStatus && false==disabledStatus){
				  $("#dispositionArea").css('display','block');
				  getOrderContactDetails(data);
				  getDispositionDetail(cust_num,cust_div,segId,subSegId,ctaName,frequency,'open');
				}else{
					$("#dispositionArea").css('display','none');
				}
			}else{ 
				getOrderContactDetails(data);
				getDispositionDetail(cust_num,cust_div,segId,subSegId,ctaName,frequency,'open');
			}
			if((undefined!=ctaName && 'Coverage'!=ctaName) && (undefined!=subSegId && '52'!=subSegId))
				  setAttributeAligned();
			else
				  setAttributeAlignedOthers();
			
			$('#statusVal').bind("DOMSubtreeModified",function(){
				  if($(this).html() == $("#oldStatusVal").html()){
					  $("#commentId").val($("#oldCommentId").html());
					  setCharLimit($('#commentId'));
				  }else if($("#oldStatusVal").html()!='Not Started'){
					  if($("#commentId").val() ==$("#oldCommentId").html())
					  $("#commentId").val("");
					  else
						  $("#commentId").val($("#commentId").val()); 
					  setCharLimit($('#commentId'));
				  }
					  
			});
			
			$('#commentId').on("change keyup paste", function() {
				  setCharLimit($(this));
			});
			}
			logUserActivity(2039, 'User has clicked on subSegId \''+subSegId+'\' under '+ctaName+'');
			
			$("#ctaCreateOpp").unbind('click');
			$('#ctaCreateOpp').click(function(){
				logUserActivity(2036, 'User has Clicked on Create Opportunity in SFDC from subSegId \''+subSegId+'\' under '+ctaName+'');
				$('#callToAction_PopUp').modal('hide');
			});
			if(undefined!=frequency && frequency=='Monthly'){
				if(undefined!=ctaName && ''!=ctaName && 'Growth'==ctaName){
					var dt = refreshTime;//geDataRefreshTime('MV_SA_GROWTH_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}else if(undefined!=ctaName && ''!=ctaName && 'Retention'==ctaName){
					var dt = refreshTime;//geDataRefreshTime('MV_SA_RETENTION_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}else if(undefined!=ctaName && ''!=ctaName && 'Expansion'==ctaName){
					var dt = refreshTime; //geDataRefreshTime('MV_SA_EXPANSION_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}
			
			}else if(undefined!=frequency && frequency=='Weekly'){
				if(undefined!=ctaName && ''!=ctaName && 'Growth'==ctaName){
					var dt = refreshTime;//geDataRefreshTime('MV_SA_GROWTH_WK_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}else if(undefined!=ctaName && ''!=ctaName && 'Retention'==ctaName){
					var dt = refreshTime;//geDataRefreshTime('MV_SA_RETENTION_WK_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}else if(undefined!=ctaName && ''!=ctaName && 'Expansion'==ctaName){
					var dt = refreshTime;//geDataRefreshTime('MV_SA_EXPANSION_WK_LIST');
					$("#updateDateValueCtaLR span").html("");
					(undefined!=dt && ''!=dt)? ($("#updateDateValueCtaLR span").html(dt+" ET")): ($("#updateDateValueCtaLR span").html('NA'));
				}
			}else if(undefined!=frequency && frequency=='Daily'){
					
					$("#updateDateValueCtaLR span").html("");
					$("#updateDateValueCtaLR span").html(getCurrentDateTime()+' ET');
				
			}
			  
			
		}
	});
	
}
function applySubCallToAction(rationaleHtml){
	var custid=$("#reqCustNum").val();
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
function getLatestFiscalContactedDate(){
	var formData = {};
	var calendar_date,fsc_yr,fsc_prd,fsc_wk,fsc_dy;
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
					latestFiscalContactedDate=data.TM_KY
				}
				
			}
		}
	});	
}
function getDispositionDetail(cust_num,cust_div,segId,subSegId,ctaName,frequency,from){
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
			$("#commentId").val("");
			$("#subjectInputText").val("");	
			$("#oldSubjectInputText").val("");
			setCharLimit($('#commentId'));
			openSubjectModal(data,subSegId);
			if(undefined!=data && null!=data && 'null' !=data){
				var commentHtml='';
				var comment='';
				$.each(data,function(i,item){
					$("#commentLogBody").html("");
					if(undefined !=item.commentText && 'NA'==item.commentText)
						comment='-';
					else
						comment=item.commentText;
					if(i==0){
						$("#statusVal").html(item.dispositionStatus);
						$("#linkTaskId").html(item.dispositionLinkId);
						if(undefined !=item.commentText && 'NA'==item.commentText){
							$("#commentId").val("");
							setCharLimit($('#commentId'));
						}
						else{
							$("#commentId").val(item.commentText);
							setCharLimit($('#commentId'));
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
						$("#oldStatusVal").html(item.dispositionStatus);
						$("#oldCommentId").html($("#commentId").val());
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
					$("#commentLogBody").html(commentHtml);
				});
				/**/
				
				if(undefined!=data && null!=data && 'null' !=data){
					$.each(data,function(i,item){
						if(i==0){
							if(undefined !=item.contactId && '' !=item.contactId && undefined != item.sfdcContactFullName && '' != item.sfdcContactFullName){
							$("#orderContactVal").html(capitalizeAllWords(item.sfdcContactFullName));
							$("#oldOrderContactVal").html(capitalizeAllWords(item.sfdcContactFullName));
							 $("#orderContactId").html(item.contactId);
							 $("#oldOrderContactId").html(item.contactId);
							}else{
							$("#orderContactVal").html("SFDC Contact");
							 $("#orderContactId").html("");
							 $("#oldOrderContactVal").html("SFDC Contact");
							 $("#oldOrderContactId").html("");
							}
							
						}
					});
				}
			}else{
				$("#commentLogBody").html("<div style='padding:15px;font-weight:bold;font-size:16px;opacity:0.6;'>No history available.</div>");
				$("#statusVal").html('Not Started');
				$("#commentId").val('');
				$("#oldStatusVal").html('Not Started');
				$("#oldCommentId").html('');
				setCharLimit($('#commentId'));
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
function openSubjectModal(dispdata,subSegId){
	var formData = {};
	$("#subjectInputText").val("");
	 $("#oldSubjectInputText").html("");
	 if(undefined!=dispdata && null!=dispdata && 'null' !=dispdata){
			$.each(dispdata,function(i,item){
				if(i==0){
					if(undefined !=item.taskSubject && null!=item.taskSubject && 'null'!=item.taskSubject){
						$("#subjectInputText").val(item.taskSubject);
						$("#oldSubjectInputText").val(item.taskSubject);
					}else{
						$("#subjectInputText").val("");
						$("#oldSubjectInputText").val("");
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
						if(undefined!=subSegId && '55'==subSegId){
							$('#subjectInputText').prop('disabled', true);
							$('#subjectInputText').val('MM Business Review');
							if(undefined!=item && item.indexOf('MM Business Review')!=-1){
								subHtml=subHtml+'<li id="subId_'+item+'"><a href="#" style="">'+item+'</a><a style="display:none;" href="#">'+item+'</a></li>'
								+'<li class="divider" style="margin:4px 0px;"></li>';		
							}else{
								subHtml=subHtml+'<li id="subId_'+item+'" class="disabled" style="pointer-events:none;"><a href="#" style="pointer-events:none;">'+capitalizeAllWords(item)+'</a><a style="display:none;pointer-events:none;" href="#">'+item+'</a></li>'
								+'<li class="divider" style="margin:4px 0px;"></li>';
								
							}
							$('#charInfoTool').css('pointer-events','none');
						}else{
							$('#charInfoTool').css('pointer-events','auto');
							$('#subjectInputText').prop('disabled', false);
							subHtml=subHtml+'<li id="subId_'+item+'"  ><a href="#" style="">'+capitalizeAllWords(item)+'</a><a style="display:none;" href="#">'+item+'</a></li>'
							+'<li class="divider" style="margin:4px 0px;"></li>';
						}
					});
				
					if(subHtml !=''){
					$("#subjectList").html(subHtml);
					$("ul#subjectList li").click(function(){
						 var id=$(this).attr("id");
						 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
							 var subId=id.split("_")[1];
							 if(undefined!=subId && 'none'!= subId){
							 var sub=$(this).children("a:first").html();
							 $("#subjectInputText").val(sub);
						 }else{
							 $("#subjectInputText").val("");
						 }
						}
					  });
					}
					
				}else{
					
				}
		}
	
	});
	
}
function setSubject(obj){
	if(undefined!=obj && undefined !=obj.html()){
		$('#subjectInputText').val($(obj).html());
		$('#subjectModal').modal('hide');
	}
}


function getOrderContactDetails(data){
	$("#orderContactVal").html("SFDC Contact");
	 $("#orderContactId").html("");
	 $("#oldOrderContactVal").html("SFDC Contact");
	 $("#oldOrderContactId").html("");
	
	if(undefined!=data && undefined!=data.orderContactList && null !=data.orderContactList && 'null' !=data.orderContactList && ''!=data.orderContactList){
		$("#orderContactList").html("");
		var dispHtml='';
		$.each(data.orderContactList,function(i,item){
			dispHtml=dispHtml+'<li id="contactId_'+item.contactId+'"><a href="#" style="">'+capitalizeAllWords(item.fullName)+'</a><a style="display:none;" href="#">'+item.contactId+'</a></li>'
			+'<li class="divider" style="margin:4px 0px;"></li>';
		});
		dispHtml=dispHtml+'<li id="contactId_none"><a href="#" style="">None</a><a style="display:none;" href="#"></a></li>';
	
		if(dispHtml !=''){
		$("#orderContactList").html(dispHtml);
		$("ul#orderContactList li").click(function(){
			 var id=$(this).attr("id");
			 if(undefined !=id && ''!=id && id.indexOf("_")!=-1){
				 var contactId=id.split("_")[1];
				 if(undefined!=contactId && 'none'!= contactId){
				 var orderContact=$(this).children("a:first").html();
				 $("#orderContactVal").html(capitalizeAllWords(orderContact));
				 $("#orderContactId").html(contactId);
			 }else{
				 $("#orderContactVal").html("SFDC Contact");
				 $("#orderContactId").html("");
			 }
			}
		  });
		}
		
	}else{
		
	}
}

function populateAttributes(data,segDesc){
	var segName='';
	if(undefined!=segDesc && ''!=segDesc && ('Decliner'==segDesc || 'Churn'==segDesc))
		segName=segDesc;
	if('' !=data && undefined !=data.attributeList && null !=data.attributeList && ''!=data.attributeList){
		
		$.each(data.attributeList,function(i,item){
			if(undefined !=item.paramLabel && ''!=item.paramLabel){
				$("#paramKey"+segName+"_"+item.headerId+'_'+item.paramId).val(item.paramLabel);
				var paramVal=item.paramValue;
				if(undefined!=paramVal && ''!=paramVal && 'NA'!=paramVal && '_UNKWN_'!=paramVal){
					paramVal=paramVal;
					if($("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).hasClass('txtarea')){
						if(paramVal.indexOf('</br>')!=-1)
							paramVal=paramVal.replace(/<\/br>/gi, '\r\n')
						$("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).val(paramVal);
					}else{
						$("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).val(paramVal);
					}
				}else if('_UNKWN_'==paramVal){
					$("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).remove();
					$("#paramKey"+segName+"_"+item.headerId+'_'+item.paramId).closest( "li" ).remove();
				}
				else if('NA'==paramVal)
				$("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).val(paramVal);
				
				if($("#paramVal"+segName+"_"+item.headerId+'_'+item.paramId).val() =='NA'){
					$("#premiumAcc").css("display","none");
				}
			}
		});
	}
	if('' !=data && undefined !=data.mktgResources && null !=data.mktgResources && ''!=data.mktgResources){
		$.each(data.mktgResources,function(i,item){
			if(undefined !=item.labelName && ''!=item.labelName){
				$("#label_"+item.headerId+'_'+item.paramId).html(item.labelName);
				if(undefined!=item.labelHiddenUrl && '' !=item.labelHiddenUrl && 'null' !=item.labelHiddenUrl && null !=item.labelHiddenUrl && undefined !=item.baseUrl && null !=item.baseUrl && '' !=item.baseUrl)
				$("#label_"+item.headerId+'_'+item.paramId).attr("href",item.baseUrl+''+item.labelHiddenUrl);
				else
				$("#label_"+item.headerId+'_'+item.paramId).attr("href",'javascript:void()');
					
			}
			if(undefined!=item.labelPrefixText && '' !=item.labelPrefixText)
			$("#labelPrefix_"+item.headerId+'_'+item.paramId).html(item.labelPrefixText);
			if(undefined!=item.labelSuffixText && '' !=item.labelSuffixText)
			$("#labelSuffix_"+item.headerId+'_'+item.paramId).html(item.labelSuffixText);
		});
	}
	
   	$("input[id^='param']").each(function () {
		if (undefined != this.id && '' != this.id
				&& undefined != this.value && '' != this.value
				&& undefined != this.value.length && this.value.length > 25)
			$("#" + (this.id)).attr("style","width:"+ ((this.value.length + 1) * 8)	+ 'px !important; '+
					" vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;color:black !important;font-family:Helvetica;font-weight:bold;");
		else if (undefined != this.id && '' != this.id)
			$("#" + (this.id)).attr("style"," width:200px !important;vertical-align: baseline;border: 0;border-bottom: 1px solid;line-height: inherit;padding: 2px;");
					
   });
   	setInnerDynamicAttrWidth();
}

function formatParamValues(){
	var maxDigToFormat='';
	var oldarr=new Array();
	var newarr=new Array();
	var count=0;
	$("[id^='paramVal_01']:not(.innerClass)").each(function () {
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
	$("[id^='paramVal_01']:not(.innerClass)").each(function () {
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
function showCtaSfdcPopUp(segId,subSegDesc,ctaName,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject,operation) {
	$('#ctaSfdcConfModal').modal({
		/*"backdrop"  : "static",*/
        handle: ".modal-header",
		"keyboard"  : true,
		"show"      : true           
	});
	$("#ctaSfdcYesId").unbind('click');
	$("#ctaSfdcYesId").click(function(){
		//alert("yes"+despVal+"---"+segId);
		saveToSFDC(subSegDesc,ctaName,segId,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject);
		/*var currDate = new Date();
		currDate=currDate.toLocaleDateString("en-US", {year: "numeric", month: "numeric",day: "numeric"}).replace(/\s/g,'-');
		$("#"+segId).html(despVal+'- '+currDate);*/
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
        /*handle: ".modal-header",*/
		"keyboard"  : "true",
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
function saveToSFDC(subSegDesc,ctaName,segId,subSegId,div,custNum,user,comment,dispStatus,linkId,frequency,loggedInuserId,subject){
	var formData = {};
	var calendar_date,fsc_yr,fsc_prd,fsc_wk,fsc_dy,tm_dy,fyrFprdFwkFdy;
	var contactId=$('#orderContactId').html();
	var sfdcContactFullName=$('#orderContactVal').html();
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
							getDispositionDetail(custNum,div,segId,subSegId,ctaName,frequency,'save');
						}
						
					}
				});
			}
			logUserActivity(2040, 'User has Clicked Save to SFDC on segment no. \''+subSegId+'\' under '+ctaName+'');
		}
	
	});
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
function toCamCase(str)
{
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}
function removeUnderScore(val){
	if(undefined !=val && ''!=val){
		val = val.replace(/_/g, " ");
	}
	return val;
}
function openUrl(url){
	 var win = window.open(url, '_blank');
	  win.focus();
}