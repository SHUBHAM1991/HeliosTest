
/**
 * Lomesh Changes 
 */
var ctx =$("#svsURL").val();// "http://FRA52780:8095/ContractDasbhoard/";
var subPlayKeyValArr=new Array();
var selectedSubPlaysArr=new Array();
var selectedSubPlayValArray=new Array();
var deselectValArr=new Array();
var COMMA_CONSTANT=',';
var ICOMMA_CONSTANT='\',';
var COMMAI_CONSTANT=',\'';
var ICOMMAI_CONSTANT='\',\'';
var selectedSubPlays='All';
var accTypeFlag=false;
var onChangeAccType=false;
 $(document).ready(function(){
	 //alert($("#repRoleCode").val()+"--"+$("#layoutObj").val());
	 var repRoleCodeVal=$("#repRoleCode").val();
	 if(repRoleCodeVal=='AM')
	 $("#accTypeId").css("display","block");	 
	 $("ul#accList li").click(function(){
		 var id=$(this).attr("id");
		 var obj=$("input[type=checkbox][value=multiselect-all]");
		 var filter=['multiselect-all','Growth','Retention','Expansion','Mature','Transitioning','Young','AllQul'];
		 if(id=="MM"){
			 $("#accTypeVal").html("MM");
			 accTypeFlag=true;
				$('#playSec').multiselect('select', filter);
			    handleAllFilterOperation(obj);
				//populateDataOnLoad("ALL","onload");
		 }else if(id=="SAM"){
			 $("#accTypeVal").html("SAM");
			 accTypeFlag=true;
			 $('#playSec').multiselect('select', filter);
			 handleAllFilterOperation(obj);
			 //populateDataOnLoad("ALL","onload");
		 }
		 
	  });
 var layoutObj = $.parseJSON($("#layoutObj").val());
 if(undefined !=layoutObj && null !=layoutObj && 'null' !=layoutObj){
				USERS_GRID = layoutObj.usersGrid;
				ORDER_GRID = layoutObj.ordersGrid;
				SHIPTO_GRID = layoutObj.shipToGrid;
				ACCOUNT_MGR_GRID = layoutObj.accountMgrGrid;
				SA_DIFF_GRID = layoutObj.saDiffGrid;
				YTD_SPEND_CAT_GRID = layoutObj.ytdSpendCatGrid;
				YTD_SPEND_MONTH_GRID = layoutObj.ytdSpendMonthGrid;
				MONTH_ANALYSIS_GRID = layoutObj.monthAnalysisGrid;
				YEAR_SUMMARY_GRID = layoutObj.yearSummaryGrid;
				CUST_PROFILE_GRID = layoutObj.custProfileGrid;
				REORDER_GRID = layoutObj.reorderGrid;
				BAB_GRID = layoutObj.babGrid;
				STORES_NEARBY_GRID = layoutObj.storesNearByGrid;
				CUSTNUM_COLUMN = layoutObj.custNumCol;
				CUSTTYPE_COLUMN = layoutObj.custTypeCol;
				COMPNAME_COLUMN = layoutObj.compNameCol;
				CALLORDER_COLUMN = layoutObj.callOrderCol;
				CUSTSEG_COLUMN = layoutObj.custSegCol;
				CUSTSUBSEG_COLUMN = layoutObj.custSubSegCol;
				QUALSCORE_COLUMN = layoutObj.qualScCol;
				CALLACTION_COLUMN = layoutObj.callActionCol;
				LASTCONTACTDATE_COLUMN = layoutObj.lastConDateCol;
             }
				//$("#layoutObj").val("");
				//alert(USERS_GRID+"---"+ORDER_GRID+"---"+SHIPTO_GRID+"---"+ACCOUNT_MGR_GRID+"----"+SA_DIFF_GRID+"----"+YTD_SPEND_CAT_GRID+"---"+YTD_SPEND_MONTH_GRID+"---"+MONTH_ANALYSIS_GRID+"---"+YEAR_SUMMARY_GRID+"---"+CUST_PROFILE_GRID+"---"+REORDER_GRID+"---"+BAB_GRID+"---"+STORES_NEARBY_GRID+"---"+CUSTNUM_COLUMN+"---"+CUSTTYPE_COLUMN+"---"+COMPNAME_COLUMN+"---"+CALLORDER_COLUMN+"---"+CUSTSEG_COLUMN+"---"+CUSTSUBSEG_COLUMN+"---"+QUALSCORE_COLUMN+"---"+CALLACTION_COLUMN+"---"+LASTCONTACTDATE_COLUMN);
	 $("#loggedInUserNameSpan").html($("#loggedInUserName").val());
	 if($(window).width() <=768){
		 $("thead#custHeader th").css("vertical-align","middle");
		 /*$("thead#custHeader th").css("word-break","break-word");
		 $("thead#custHeader th:nth-child(3)").css("word-spacing","10px");*/
	 }
	 $('#dataTables-CustInfo').DataTable({
			"lengthMenu": [[15, 20, 25, -1], [15, 20,25, "All"]],
			 "dom": '<"pull-right"f>rt<"bottom"ip>',
			 "oLanguage": { "sSearch": "Filter : "},
			 "processing":true,
			  "responsive": true,
			  "bDestroy" : true,
			  "paging" :true,
			  "order": [],
			  "aoColumns": [{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true},
							{"bSortable": true}
							]
				
		});
	 var table = $('#dataTables-CustInfo').DataTable();
	 
	 $('#next').on( 'click', function () {
	     table.page( 'next' ).draw( 'page' );
	 } );
	  
	 $('#previous').on( 'click', function () {
	     table.page( 'previous' ).draw( 'page' );
	 } );
	 $('#dataTables-CustInfo')
	    .on('preXhr.dt', function ( e, settings, data ) {
	    	$("#sliderSubPlaysItem").val(selectedSubPlays);
	    	//table.clear();
	    	//$("#example_processing").show();
	    	
	    } );
	 $('#dataTables-CustInfo')
	    .on('xhr.dt', function ( e, settings, json, xhr ) {
	    	$("#subPlayParams").val(json.unSelListOfSubPlay);
	        // Note no return - manipulate the data directly in the JSON object.
	    	//$("#example_processing").hide();
	    	//table.draw();
	    	
	    } );
	    
	 

	 $('#playSec').multiselect({
		 includeSelectAllOption: true,
         allSelectedText: 'ALL...',
         selectAllText: 'ALL'
	 });
	 /*if($("#from").val()=='home'){
		 var v=['multiselect-all','Growth','Retention','Expansion','Mature','Transitioning','Young','AllQul'];
		 $('#playSec').multiselect('select', v);	
		 $("#playSec").multiselect('selectAll', true);
	 }*/
	 var filterBy= $('#filterBy').val();
	 if(filterBy==undefined || filterBy==null || filterBy=='ALL' || filterBy==''){
	 $(".multiselect-selected-text").html("ALL");
	 $("#playSec").next().find("ul").attr("style","width:240px !important;");
	 $("#playSec").next().find("ul").find("li:first").children().children().css("background-color","lightgray");
	 $("#playSec").next().find("ul").find("li").eq(1).before('<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Play Segment</label></a></li>');
	 $("#playSec").next().find("ul").find("li").eq(4).after('<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Customer Segment</label></a></li>');
	 $("#playSec").next().find("ul").find("li").eq(-2).after('<li id="QualLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl">Qualification Score</label></a></li>');
    //$("#playSec").next().children(":last").append('<li style="background-color:#ccc;"><a tabindex="0"><label class="checkbox"> Expansion</label></a></li><li><a tabindex="0"><label class="checkbox"><input type="checkbox" value="Cust Seg"> CustSeg</label></a></li>');
	 populateDataOnLoad("ALL","onload");}
     else{
    	 $(".multiselect-selected-text").html(filterBy);
    	 $( "#playSec").val(filterBy);
    	 $("#playSec").next().find("ul").attr("style","width:240px !important;");
    	 $("#playSec").next().find("ul").find("li:first").children().children().css("background-color","lightgray");
    	 $("#playSec").next().find("ul").find("li").eq(1).before('<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Play Segment</label></a></li>');
    	 $("#playSec").next().find("ul").find("li").eq(4).after('<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Customer Segment</label></a></li>');
    	 $("#playSec").next().find("ul").find("li").eq(-2).after('<li id="QualLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl">Qualification Score</label></a></li>');
    	 populateDataOnLoad(filterBy,"onload");
     }
	 
	 $( "#playSec").change(function() { 
		 
	 });
	 bindEventsToMainFilter();
	 $('#subPlayId').multiselect({
		 //includeSelectAllOption: true,
		 numberDisplayed: 1,
		 selectAllText: 'Select All Sub Plays',
		 buttonWidth: '120px',
		 onChange: function(option, checked) {
			 //$("input[type=checkbox][value$=Growth]").bind().click();
			 bindEventsToSubFilters();
			 selectedSubPlaysArr=[];
			 var selected = $("#subPlayId option:selected");
			 var notselected = $("#subPlayId option").not(':selected');
		 
		        var selectedValArr = new Array();
		        var selectedTxtArr = new Array();
		        var count=0;
		        /*selected.each(function () {
		            //message += $(this).text() + " " + $(this).val() + "\n";
		        	selectedValArr[count]=$(this).val();
		        	count++;
		        });*/
		        //alert("ns="+notselected.length)
		        if(notselected.length==0)
			     $("#subPlayParams").val("")
		        notselected.each(function () {
		            selectedValArr[count]=$(this).val();
		        	count++;
		        });
		        
		        
		        count=0;
		        for(count=0;count<selectedValArr.length;count++){
		        	var key=selectedValArr[count];
		        	selectedTxtArr[count]=subPlayKeyValArr[key];
		        	//selectedSubPlaysValArr[count]=key;
		        	//alert("key ="+key+" val ="+selectedTxtArr[count])
		        }
		        selectedSubPlaysArr=selectedTxtArr;
		        selectedSubPlayValArray=selectedValArr;
		        var selectedPlay=$( "#playSec").val();
      			populateDataOnLoad(selectedPlay,"subPlays");
      			$('#subPlayId').multiselect('selectAll', false);
      			var values = [];
	   			for(var count=0;count< selectedValArr.length;count++){
	   				 values.push(selectedValArr[count]);	 
	   			}
	   		    $('#subPlayId').multiselect('deselect', values);
         }
		 /*buttonText: function(options, select) {
             if (options.length === 0) {
                 return 'select subplays';
             }
             else if (options.length > 0) {
                 return options.length+' subplays selected';
             }},
         */
		 /*beforeopen: function(){ $(this).multiselect('widget').width(100); }*/
	 });
	 var aboutData='<div style="text-align:justify;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;">The To Do List displays a prioritized list of all of the Customers / Companies assigned to the associate, which is their book of business (BOB), as well as an overview of each account. This list is prioritized based on the maximum likelihood of customers who will soon be placing an order. The likelihood of placing an order is based on the following 6 criteria.</div>'
		 
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">1. User Online Activity:</span> Number of sessions in a given time period, last active date</span></div>'
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style=" color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">2. Order History:</span> Frequency of orders, last order date </span></div>'
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">3. Last Contacted Date:</span> Number of days since an associate last contacted the customer</span></div>'
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">4. Account Qualification Score:</span> Weighting on existing frequency based on account growth potential</span></div></div>'
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">5. Dotcom Activity:</span> Browsing/purchasing activity on Staples.com</span></div></div>'
			+'<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">6. Monetary Value:</span> Previous purchasing history/spend</span></div></div>';
	 
	 $('#aboutId').popover({
		 html : true,
		 placement: 'bottom',
		content : aboutData
	 });
 });

 
 function disabledSubPlays(selectedPlay){
	 var playVal=$('#playSec').next().children(":first").children(":first").html();
	 
	 if((undefined !=playVal) && (playVal=='None selected'))
	 selectedPlay="";
	 if(((null != selectedPlay) && undefined != selectedPlay) || (playVal=='None selected')){ 
		 if((selectedPlay.indexOf("Growth")==-1)){ 
			 //$("input[type=checkbox][value*=Growth]").prop("checked",false);
			 $("input[type=checkbox][id=GrChk]").prop("checked",false);
			 $("input[type=checkbox][value*=Growth]").prop("disabled",true);
			 $("input[type=checkbox][value*=Growth]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Growth]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Growth]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Growth]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Growth]").prop("disabled",false);
			 $("input[type=checkbox][value=Growth]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Growth]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Growth]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value=Growth]").parent().parent().parent().attr("class","active")
			//$('#subPlayId').next().children(":first").children(":first").html("All selected ("+optionTxt.length+")");
			 var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Growth]").length)-1;
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Growth");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);	
	     }else{
	    	 $("input[type=checkbox][id=GrChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Growth]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Growth]").prop("checked",true);
			 $("input[type=checkbox][value*=Growth]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Growth]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Growth]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Growth]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Growth]").length)-1;
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Growth");	 
			 }
		     $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("Retention")==-1){
	    	 //$("input[type=checkbox][value*=Retention]").prop("checked",false);
			 $("input[type=checkbox][id=RetChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Retention]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Retention]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Retention]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Retention]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Retention]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Retention]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Retention]").prop("disabled",false);
			 $("input[type=checkbox][value=Retention]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Retention]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Retention]").parent().parent().parent().css("opacity","1")
			// $("input[type=checkbox][value=Retention]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var RetentionItemlength=($("input[type=checkbox][value*=Retention]").length)-1;
			 for(var count=0;count<RetentionItemlength;count++){
				 values.push(count+"Retention");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=RetChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Retention]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Retention]").prop("checked",true);
			 $("input[type=checkbox][value*=Retention]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Retention]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Retention]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Retention]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var RetentionItemlength=($("input[type=checkbox][value*=Retention]").length)-1;
			 for(var count=0;count<RetentionItemlength;count++){
				 values.push(count+"Retention");	 
			 }
		     $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("Expansion")==-1){
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",false);
			 $("input[type=checkbox][id=ExpChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Expansion]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Expansion]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Expansion]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Expansion]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Expansion]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Expansion]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Expansion]").prop("disabled",false);
			 $("input[type=checkbox][value=Expansion]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Expansion]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Expansion]").parent().parent().parent().css("opacity","1")
			 //$("input[type=checkbox][value=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Expansion]").length)-1;
			 for(var count=0;count<ExpansionItemlength;count++){
				 values.push(count+"Expansion");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=ExpChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Expansion]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",true);
			 $("input[type=checkbox][value*=Expansion]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Expansion]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Expansion]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Expansion]").length)-1;
			 for(var count=0;count<ExpansionItemlength;count++){
				 values.push(count+"Expansion");	 
			 }
			 $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("Mature")==-1){
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",false);
			 $("input[type=checkbox][id=MatChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Mature]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Mature]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Mature]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Mature]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Mature]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Mature]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Mature]").prop("disabled",false);
			 $("input[type=checkbox][value=Mature]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Mature]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Mature]").parent().parent().parent().css("opacity","1")
			 //$("input[type=checkbox][value=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Mature]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Mature]").each(function(){
				    values[count]=$(this).attr("value");
				    count++;
			 });
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=MatChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Mature]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",true);
			 $("input[type=checkbox][value*=Mature]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Mature]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Mature]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Mature]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Mature]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Mature]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
			 $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("Transitioning")==-1){
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",false);
			 $("input[type=checkbox][id=TrChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Transitioning]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Transitioning]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Transitioning]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Transitioning]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Transitioning]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Transitioning]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Transitioning]").prop("disabled",false);
			 $("input[type=checkbox][value=Transitioning]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Transitioning]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Transitioning]").parent().parent().parent().css("opacity","1")
			 //$("input[type=checkbox][value=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Transitioning]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Transitioning]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=TrChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Transitioning]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",true);
			 $("input[type=checkbox][value*=Transitioning]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Transitioning]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Transitioning]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Transitioning]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Transitioning]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Transitioning]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
			 $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("Young")==-1){
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",false);
			 $("input[type=checkbox][id=YngChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Young]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Young]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Young]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Young]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Young]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Young]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Young]").prop("disabled",false);
			 $("input[type=checkbox][value=Young]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Young]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Young]").parent().parent().parent().css("opacity","1")
			 //$("input[type=checkbox][value=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Young]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Young]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=YngChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Young]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",true);
			 $("input[type=checkbox][value*=Young]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Young]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Young]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Young]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Young]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Young]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
			 $('#subPlayId').multiselect('select', values);
	     }
		 if(selectedPlay.indexOf("AllQul")==-1){
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",false);
			 $("input[type=checkbox][id=QulChk]").prop("checked",false);
	    	 $("input[type=checkbox][value*=Qual]").prop("disabled",true);
	    	 $("input[type=checkbox][value=Qual]").prop("disabled",false);
	    	 $("input[type=checkbox][value*=Qual]").parent().css("cursor","not-allowed")
			 $("input[type=checkbox][value*=Qual]").parent().css("color","#444")
			 $("input[type=checkbox][value*=Qual]").parent().parent().parent().css("opacity","0.7")
			 $("input[type=checkbox][value*=Qual]").parent().parent().parent().attr("class","")
			 $("input[type=checkbox][value=Qual]").prop("disabled",false);
			 $("input[type=checkbox][value=Qual]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value=Qual]").parent().css("color","currentColor")
			 $("input[type=checkbox][value=Qual]").parent().parent().parent().css("opacity","1")
			 //$("input[type=checkbox][value=Expansion]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Qual]").length)+1;
			 var count=0;
			 $("input[type=checkbox][value*=Qual]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=QulChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Qual]").prop("disabled",false);
	    	 //$("input[type=checkbox][value*=Expansion]").prop("checked",true);
			 $("input[type=checkbox][value*=Qual]").parent().css("cursor","pointer")
			 $("input[type=checkbox][value*=Qual]").parent().css("color","#004c74")
			 $("input[type=checkbox][value*=Qual]").parent().parent().parent().css("opacity","1")
			 $("input[type=checkbox][value*=Qual]").parent().parent().parent().attr("class","active")
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Qual]").length)+1;
			 var count=0;
			 $("input[type=checkbox][value*=Qual]").each(function(){
				    values[count]=$(this).attr("value");
				    
				    count++;
			 });
			 $('#subPlayId').multiselect('select', values);
	     }
		 
		 
	 }
	/* $("#subPlayId").next().children(":last").children("li").children("label").each(function(){
		 if($(this).html()=='Growth'){
			 $(this).html("<input type='checkbox' id='GrChk'/> Growth ");
			 if($("input[type=checkbox][value=Growth]").prop("checked")==true)
				 $("#GrChk").prop("checked",true);
			 else
				 $("#GrChk").prop("checked",false);
		  }else if($(this).html()=='Retention'){
			 $(this).html("<input type='checkbox' id='RetChk'/> Retention");
			 if($("input[type=checkbox][value=Retention]").prop("checked")==true)
				 $("#RetChk").prop("checked",true);
			 else
				 $("#RetChk").prop("checked",false);
		  }else if($(this).html()=='Expansion'){
			 $(this).html("<input type='checkbox' id='ExpChk'/> Expansion");
			 if($("input[type=checkbox][value=Expansion]").prop("checked")==true)
				 $("#ExpChk").prop("checked",true);
			 else
				 $("#ExpChk").prop("checked",false);
		  }else if($(this).html().indexOf('Mature')!=-1){
			 $(this).html("<input type='checkbox' id='MatChk'/> Mature");
			 if($("input[type=checkbox][value=Mature]").prop("checked")==true)
				 $("#MatChk").prop("checked",true);
			 else
				 $("#MatChk").prop("checked",false);
		  }else if($(this).html().indexOf('Transitioning')!=-1){
			 $(this).html("<input type='checkbox' id='TrChk'/> Transitioning");
			 if($("input[type=checkbox][value=Transitioning]").prop("checked")==true)
				 $("#TrChk").prop("checked",true);
			 else
				 $("#TrChk").prop("checked",false);
		  }else if($(this).html().indexOf('Young')!=-1){
			 $(this).html("<input type='checkbox' id='YngChk'/> Young");
			 if($("input[type=checkbox][value=Young]").prop("checked")==true)
				 $("#YngChk").prop("checked",true);
			 else
				 $("#YngChk").prop("checked",false);
		  }else if($(this).html().indexOf('Qual')!=-1){
			 $(this).html("<input type='checkbox' id='QulChk'/> Qual");
			 if($("input[type=checkbox][value=AllQul]").prop("checked")==true)
				 $("#QulChk").prop("checked",true);
			 else
				 $("#QulChk").prop("checked",false);
		  }
		  bindEventToSubPlayLables();
	 });*/
	 $("#subPlayId").next().children(":last").children("li").children("a").each(function(){
		 //alert($(this).attr("tabindex")+"---");
		 var disabled=$(this).children().children().prop("disabled");
		 $(this).attr("onclick","javascript:call('"+disabled+"',this)");
		 
	 });
	 $("#subPlayId").next().children(":last").children("li").children("label").each(function(){
	 if($(this).html()=='Growth'){
		 $(this).html("Growth ");
	  }else if($(this).html()=='Retention'){
		 $(this).html("Retention");
	  }else if($(this).html()=='Expansion'){
		 $(this).html("Expansion");
	  }else if($(this).html().indexOf('Mature')!=-1){
		 $(this).html("Mature - <span style='font-size:11px;'>13+ Months</span>");
	  }else if($(this).html().indexOf('Transitioning')!=-1){
		 $(this).html("Transitioning - <span style='font-size:11px;'>7 to 12 Months</span>");
	  }else if($(this).html().indexOf('Young')!=-1){
		 $(this).html("Young - <span style='font-size:11px;'>0 to 6 Months</span>");
	  }else if($(this).html().indexOf('Qual')!=-1){
		 $(this).html("Qualification Score");
	  }
	  //bindEventToSubPlayLables();
	 
 }); 
	 bindEventsToSubFilters();
 }
 
	  function populateDataOnLoad(selPlayType,from){
		 // alert(selPlayType+"---"+from+"---"+accTypeFlag);
		  var selectedCustSegIds="";
			 var selectedQualScoreIds= ""; 
			 var formData={};
			 var custid=$("#accId").val();
			  selectedSubPlays='All';
			 //alert("jkjkkj"+selPlayType+"--"+selectedSubPlaysArr);
             if((undefined ==selectedSubPlaysArr || selectedSubPlaysArr ==null || selectedSubPlaysArr =='' || selectedSubPlaysArr.length ==0)&&((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val()))){
		    	 var subPlaysParam= $("#subPlayParams").val();
		    	 //alert("<--->"+subPlaysParam);
		    	 if(subPlaysParam.indexOf("~")!=-1){
		    		 selectedSubPlaysArr=subPlaysParam.split("~");
		    		 deselectValArr=subPlaysParam.split("~");
		    	 }else if('' != subPlaysParam){
		    		 selectedSubPlaysArr[0]=subPlaysParam;
		    		 deselectValArr[0]=subPlaysParam;
		    	 }
		     }
             //alert("arr="+selectedSubPlaysArr)
             //&& ((selPlayType!='Growth,Retention,Expansion') || selectedSubPlaysArr.length==1)
			 if(undefined !=selectedSubPlaysArr && selectedSubPlaysArr !=null && selectedSubPlaysArr !='' && (from=='subPlays' || from=='plays' || 'DashBoard' == $("#from").val()) ){
			  //alert(1111 +"-------"+selectedSubPlaysArr.length)	 
					 if((selectedSubPlaysArr.length==1)){
				 subPlayString="";
				  for(var cnt=0; cnt < selectedSubPlaysArr.length; cnt++){
					  if((selectedSubPlaysArr[cnt]).indexOf("/")!=-1){
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\//g, 'FORWARD_SLASH');
						 }
					  if(((selectedSubPlaysArr[cnt]).indexOf("Account")!=-1) && ((selectedSubPlaysArr[cnt]).indexOf("account")!=-1)){
						  if(((selectedSubPlaysArr[cnt]).indexOf("Account")!=-1)){ 
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace('Account', 'AC');}
						  else if(((selectedSubPlaysArr[cnt]).indexOf("account")!=-1)){ 
						    selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace('account', 'AB');}	  
						 }
					  if((selectedSubPlaysArr[cnt]).indexOf("&")!=-1){
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\&/g, '_AND_');
						 }
					  if((selectedSubPlaysArr[cnt]).indexOf(".")!=-1){ 
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\./g, '_DOT_');
						}
					  if((selectedSubPlaysArr[cnt]).indexOf(">")!=-1){ 
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\>/g, '_GREATER_');
						}
					  if((selectedSubPlaysArr[cnt]).indexOf("<")!=-1){ 
							 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\</g, '_LESS_');
						}
					  if ((selectedSubPlaysArr[cnt]).indexOf(";") != -1) {
							selectedSubPlaysArr[cnt] = (selectedSubPlaysArr[cnt])
									.replace(/\;/g, '_SEMI_');
						}
					  subPlayString=subPlayString+""+selectedSubPlaysArr[cnt]+"#";
				  }
				  subPlayString = subPlayString.substring(0, subPlayString.lastIndexOf("#"));
				  selectedSubPlays=subPlayString;
				 } else if((selectedSubPlaysArr.length>1)){
					 subPlayString="";
				 for(var cnt=0; cnt < selectedSubPlaysArr.length; cnt++){
					 if((selectedSubPlaysArr[cnt]).indexOf("/")!=-1){
						 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\//g, 'FORWARD_SLASH');
						 //alert(selectedSubPlaysArr[cnt])
					 }
					 if(((selectedSubPlaysArr[cnt]).indexOf("Account")!=-1)){ 
						 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace('Account', 'AC');}
					  else if(((selectedSubPlaysArr[cnt]).indexOf("account")!=-1)){ 
					    selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace('account', 'AB');}
					 if((selectedSubPlaysArr[cnt]).indexOf("&")!=-1){
						 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\&/g, '_AND_');
					 }
					 if((selectedSubPlaysArr[cnt]).indexOf(".")!=-1){ 
						 selectedSubPlaysArr[cnt]=(selectedSubPlaysArr[cnt]).replace(/\./g, '_DOT_');
					 }
					 if ((selectedSubPlaysArr[cnt]).indexOf(">") != -1) {
					selectedSubPlaysArr[cnt] = (selectedSubPlaysArr[cnt])
							.replace(/\>/g, '_GREATER_');
				     }
					if ((selectedSubPlaysArr[cnt]).indexOf("<") != -1) {
						selectedSubPlaysArr[cnt] = (selectedSubPlaysArr[cnt])
								.replace(/\</g, '_LESS_');
					}
					if ((selectedSubPlaysArr[cnt]).indexOf(";") != -1) { 
						selectedSubPlaysArr[cnt] = (selectedSubPlaysArr[cnt])
								.replace(/\;/g, '_SEMI_');
					}
					 subPlayString=subPlayString+""+selectedSubPlaysArr[cnt]+"#";
				 }
				 subPlayString = subPlayString.substring(0, subPlayString.lastIndexOf("#"));
				 selectedSubPlays=subPlayString;
			    }
			 }
			 //alert(selectedSubPlays)
            if(from=='subPlays' && ((undefined !=selectedSubPlaysArr && selectedSubPlaysArr.length==0) || (undefined !=selectedSubPlaysArr && undefined ==selectedSubPlaysArr.length))){
            	selectedSubPlays="All";
            }
			 if( ( selPlayType=='null' || selPlayType==null || selPlayType=='None selected' ) && (selectedSubPlays != 'All')){  
		     selPlayType='none';
			 }
			 //alert(selectedSubPlays+"--\n\n"+selectedSubPlayValArray);
			 
			 
			 //alert(selectedCustSegIds+"--"+selectedQualScoreIds);
			 //$('#OrderDetailsInfoId').html('<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 80px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>');
			// $("#example_processing").show();
			 if(("onload" ==from)) { //|| (("onload" ==from) && ($("#subPlayParams").val() !=''))
					//alert("selPlayType ="+selPlayType);
					//alert($("#filterBy").val())
					getSubPlayList(selPlayType);
					
				}
			 if("onload" ==from && accTypeFlag != true){  
				 selectedQualScoreIds = $("#selectedQualScore").val();
				  
				 selectedCustSegIds = $("#selectedSegScore").val();
			 }
			 else if(selPlayType !='ALL' && selectedSubPlays !='All'){
				 
				 selectedCustSegIds=getSelectedCustSegIds();
					selectedQualScoreIds= getSelectedQualScoreIds();
					$("#selectedQualScore").val(getSelectedQualScoreIds);
					$("#selectedSegScore").val(getSelectedCustSegIds);
			 }else{
				 selectedCustSegIds="";
				 selectedQualScoreIds="";
			 }
			 var count=1;
			 var agentEmailId='';
			 var flag='';
			 var test="k";
			 $("#filterBy").val(selPlayType);
			 var repRoleCode=$("#repRoleCode").val();
			 var accType=$("#accTypeVal").html();
			 var layoutParams=$("#layoutObj").val()
			 if(onChangeAccType==false)
				 repRoleCode="";
		     if($("#accTypeId").is(":visible")==false)
			 accType='NA'
			//alert(ctx+"/getAllCustomers/"+custid+"/"+selPlayType);
			//alert(selectedSubPlays+"======"+selectedQualScoreIds+"======"+selectedCustSegIds);
			 $('#dataTables-CustInfo').dataTable( {
			        /*"bProcessing": true,*/
			        "bDestroy" : true,
			       "sAjaxSource": ctx+"/getAllCustomers/"+custid+"/"+selPlayType,
			       "fnServerParams": function (aoData) { 
			    	   aoData.push({ "name": "selectedSubPlays", "value": selectedSubPlays}); 
			    	   aoData.push({ "name": "selectedQualScore", "value": selectedQualScoreIds});
			    	   aoData.push({ "name": "selectedSegScore", "value": selectedCustSegIds});
			    	   aoData.push({ "name": "repRoleCode", "value": repRoleCode});
			    	   aoData.push({ "name": "accType", "value": accType});
			    	   }, 
			        /*"ajax": {
			            "url": ctx+"/getAllCustomers/"+custid+"/"+selPlayType+"/"+encodeURIComponent(selectedSubPlays),
			            //"type": "POST",
			            //"data": formData
			         },*/
			       // "bProcessing" : true,
					"bServerSide" : true,
					"responsive": true,
					"oLanguage": { "sSearch": "Filter : "},
					/*"iDisplayLength" : 50,*/
					//"sScrollY": "200px", /* fixed header */
					/*"aaSorting" : [[3, 'desc']],*/
					//'bJQueryUI' : true,
					"bLengthChange" : true,
					"order": [],
					"processing":true,
					"paging" :true,
					//"bDestroy" : true,
					"bInfo" : true,
					"dataType": 'jsonp',
					'sServerMethod' : "POST",
					/*"sAjaxSource" : ctxNew + "/api/commlog/" + id + '?' + 'filterInput='+encodeURIComponent(JSON.stringify(formData))
							,*/
					
					/*"sPaginationType": "full_numbers",*/
					"fnDrawCallback": function ( oSettings ) {
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
								
								$("#dataTables-CustInfo tr th:nth-child(8)").hide();
								$("#dataTables-CustInfo tr td:nth-child(8)").hide();
								$("#dataTables-CustInfo tr th:nth-child(4)").hide();
								$("#dataTables-CustInfo tr td:nth-child(4)").hide();
							} 
						}
						//$("#example_processing").hide();
						$("#dataTables-CustInfo td").css("WORD_BREAK","BREAK-ALL");
						$('#dataTables-CustInfo_length label').css("color","#004c74");
						$('#dataTables-CustInfo_length label').css("color","#004c74");
						$('#dataTables-CustInfo_length label').css("font-weight","600");
						$('#dataTables-CustInfo_filter label').css("color","#004c74");
						$('#dataTables-CustInfo_filter label').css("text-align","right");
						//$('#dataTables-CustInfo_filter').css("text-align","right");
						$('#dataTables-CustInfo_filter label').css("font-weight","600");
						$('#dataTables-CustInfo_info').css("color","#004c74");
						$('#dataTables-CustInfo_info').css("font-weight","600");
						$('#dataTables-CustInfo_info').parent().addClass("col-lg-6 col-xs-6 col-sm-4 col-md-4");
						$('#dataTables-CustInfo_info').parent().css("padding-left","0px");
						$('#dataTables-CustInfo_paginate').parent().removeClass("col-sm-6");
						$('#dataTables-CustInfo_paginate').parent().addClass("col-lg-6 col-xs-6 col-sm-8 col-md-8");
						$('#dataTables-CustInfo_paginate').parent().css("padding-right","0px");
						//$('#ddataTables-CustInfo_paginate').css("text-align","right");
						$('.form-inline .form-control').css("color","#004c74");
						$('.form-inline .form-control').css("border","1px solid #004c74");
						$('select .form-control.input-sm').css("border","1px solid #004c74");
						$("#dataTables-CustInfo_length").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6");
						$("#dataTables-CustInfo_filter").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6 col-sm-7");
						$("#dataTables-CustInfo_filter").children().css("width","100%")
						 $('#dataTables-CustInfo').removeClass('display').addClass('table table-striped table-bordered');
						 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Company Name OR Customer Number').css({'width':'200px','display':'inline-block'});
						 $('.dataTables_filter input[type="search"]').blur(function (ev) {
								 var searchVal=$("input[type=search]").val()
								 if(undefined !=searchVal && '' !=searchVal && 'Search'!=searchVal){
									 searchVal=searchVal.toLowerCase();
									 var found='';
								  if(searchVal.charAt(0)=='g'){
									  found="growth".search(searchVal);
									  if(undefined !=found && null !=found && '' !=found  && -1 !=found){
									  $("#playSec option[value=Growth]").attr("selected","selected");
									  $("input[type=checkbox][value=Growth]").prop("checked",true)
									 }
								  }	else if(searchVal.charAt(0)=='e'){
									  found="expansion".search(searchVal);
									  if(undefined !=found && null !=found && -1 !=found){ 
									  $("#playSec option[value=Expansion]").attr("selected","selected");
									  $("input[type=checkbox][value=Expansion]").prop("checked",true)
									 }
								  } else if(searchVal.charAt(0)=='r'){ 
									  found="retention".search(searchVal);
									  if(undefined !=found && null !=found && -1 !=found){
									  $("#playSec option[value=Retention]").attr("selected","selected");
									  $("input[type=checkbox][value=Retention]").prop("checked",true)
									 }
								  }
							  
							 }
						 });
						 if(from !="subPlays" && ($("#subPlayParams").val() =='') ){
							 //alert("from"+from);
						   var selectedPlay=$( "#playSec").val();
						   var filt= $("#filterBy").val();
						   //alert("filt"+filt);
						   //alert("selectedPlay"+selectedPlay);
	      		           disabledSubPlays(selectedPlay);
						   
						 }
						 $("#subPlayId").next().children().attr("title","click to see more info.");
					},
					
					"aoColumns" : [
									{
										"mData": null,
										"visible":(CUSTNUM_COLUMN =='' || CUSTNUM_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full,o) {
											 agentEmailId= full.agentEmailId;
											 if(undefined !=full.agentFlag && full.agentFlag !=''){
													
													flag=full.agentFlag;
													}
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><a href="javascript:openCustDetails('+full.custNum+','+full.custNum+')" style="text-decoration:underline !important;color:#ef3f3f !important;"><span style="font-size:13px;color:#ef3f3f !important;text-decoration:underline;">'
												 + checkUundefinedNullBlankZero(full.custNum) +'</span></a></span>' ;
										 }
									},
									{
										 "mData": null,
										 "visible":(CUSTTYPE_COLUMN =='' || CUSTTYPE_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">'
												+checkUundefined(full.custType)
												+'</span>';
										 }
										
									},
									{
										"mData": null,
										"visible":(COMPNAME_COLUMN =='' || COMPNAME_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">' 
												+checkUundefined(full.companyName)+
												'</span>';
										 }
									},
									{
										"mData": null,
										"visible":(CALLORDER_COLUMN =='' || CALLORDER_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">'
												+checkUundefinedNullBlankZero(full.callOrder)+
												'</span>';
												
										 }
									},
									{
										"mData": null,
										"visible":(CUSTSEG_COLUMN =='' || CUSTSEG_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
											 	/*
											 	var vs = full.vapScore;
											 	if (vs == undefined || vs == null) {
											 		return '';
											 	}
											 	
											 	var id = vs.split(" ")[0];
											 	var customerSegment = null;
											 	
											 	if(id == 'Rising' || id == 'Slow' || id == 'Not') {
											    	customerSegment = 'YOUNG - 0 to 6 MONTHS';
											    }
											    else if(id == 'Star' || id == 'Stars' || id == 'Late') {
											    	customerSegment = 'TRANSITIONING - 7 to 12 MONTHS';
											    }
											    else if(id == 'Defector' || id == 'Lapsing' || id == 'Nova' || id == 'Supernova') {
											    	customerSegment = 'MATURE - 13+ MONTHS';
											    } else {
											    	customerSegment = vs;
											    }
											    */
											 
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">'
												       +removeSegDesc(checkUundefined(full.customerSegment))+  // customer segment
												       '</span>';
										 }
									},
									{
										"mData": null,
										"visible":(CUSTSUBSEG_COLUMN =='' || CUSTSUBSEG_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">'
												       +checkUundefined(full.vapScore)+  // sub segment
												       '</span>';
										 }
									},
									{
										"mData": null,
										"visible":(QUALSCORE_COLUMN =='' || QUALSCORE_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;">'
												       +checkUundefined(full.accountQualifyScore)+  // qualification score
												       '</span>';
										 }
									},
									{
										"mData": null,
										"visible":(CALLACTION_COLUMN =='' || CALLACTION_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												/*return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;line-height:1.8;">'
												       +untrim(checkUundefined(full.playSeg))+
												       '</span>';*/
											 return untrim(checkUundefined(full.playSeg));
										 }
									},
									{
										"mData": null,
										"visible":(LASTCONTACTDATE_COLUMN =='' || LASTCONTACTDATE_COLUMN=='Y')?true:false,
										 "mRender" : function(
												 data, type, full) {
												/*return '<span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;line-height:1.8;">'
												       +untrim(checkUundefined(full.playSeg))+
												       '</span>';*/
											 return checkUundefined(full.lastContactedDate);
										 }
									}
									],
									"oLanguage": {"sProcessing": '<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 80px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>',"sSearch":"Filter :"}

			    } );

			 
			                     //$("#dataTables-CustInfo td").css("padding-left","10px");
								$("#dataTables-CustInfo td").css("WORD_BREAK","BREAK-ALL");
									$('#dataTables-CustInfo_length label').css("color","#004c74");
									$('#dataTables-CustInfo_length label').css("color","#004c74");
									$('#dataTables-CustInfo_length label').css("font-weight","600");
									$('#dataTables-CustInfo_filter label').css("color","#004c74");
									//$('#dataTables-CustInfo_filter').css("text-align","right");
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
									 $('.dataTables_filter input[type="search"]').blur(function (ev) {
											 var searchVal=$("input[type=search]").val()
											 if(undefined !=searchVal && '' !=searchVal && 'Search'!=searchVal){
												 searchVal=searchVal.toLowerCase();
												 var found='';
											  if(searchVal.charAt(0)=='g'){
												  found="growth".search(searchVal);
												  if(undefined !=found && null !=found && '' !=found  && -1 !=found){
												  $("#playSec option[value=Growth]").attr("selected","selected");
												  $("input[type=checkbox][value=Growth]").prop("checked",true)
												 }
											  }	else if(searchVal.charAt(0)=='e'){
												  found="expansion".search(searchVal);
												  if(undefined !=found && null !=found && -1 !=found){ 
												  $("#playSec option[value=Expansion]").attr("selected","selected");
												  $("input[type=checkbox][value=Expansion]").prop("checked",true)
												 }
											  } else if(searchVal.charAt(0)=='r'){ 
												  found="retention".search(searchVal);
												  if(undefined !=found && null !=found && -1 !=found){
												  $("#playSec option[value=Retention]").attr("selected","selected");
												  $("input[type=checkbox][value=Retention]").prop("checked",true)
												 }
											  }
										  
										 }
									 });
									if($(window).width()>=768){ 
										/*$("#dataTables-CustInfo.table>thead>tr>th:last-child").css({
											"padding-left": "45px",
										"padding-right": "45px",
										"width": "99px"
										});*/
										/*$("#dataTables-CustInfo.table>thead>tr>th:nth-child(3)").css({
										"width": "102px"
										});*/
										//alert($("thead#custHeader th[aria-label='CompanyName: activate to sort column ascending']").prop("width"))
										//$("thead#custHeader th[aria-label='CompanyName: activate to sort column ascending']").attr("style","width:440px !important;");
										
										//$("thead#custHeader th:nth-child(3)").attr("style","width:102px !important");
									//$("table.dataTable tbody > tr > td:nth-child(3)").attr("style","width:102px !important")
										
									$('#dataTables-CustInfo_info').attr("style","font-size:12px !important");
									}
									//$("table.table-bordered td:last-child").attr("style","font-family:Helvetica;");
									//$("#dataTables-CustInfo td:nth-child(n+7)").attr("style","font-family: Helvetica;line-height: 1.7 !important;text-align: inherit;padding-left: 3%;font-size: 13px;font-weight: bold;color: #444;");
									 if(undefined != agentEmailId && agentEmailId !=''){
											var agentName=agentEmailId.toString();
											
											if(undefined !=flag && flag!='' && flag=='TRUE'){
											$("span#agentName").html(agentName+"'S TO DO LIST");
											}else{
												$("span#agentName").html(agentName+"'S LIST");	
											} 
										}
									// alert("from"+from);
									 if(from !="subPlays" && ($("#subPlayParams").val() =='') ){
										 //alert("from"+from);
									   var selectedPlay=$( "#playSec").val();
									   var filt= $("#filterBy").val();
									   //alert("filt"+filt);
									   //alert("selectedPlay"+selectedPlay);
				      		           //disabledSubPlays(selectedPlay);
									 }
						/*}*/

				/*})*/	
				
			accTypeFlag=false;
			onChangeAccType=true;
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
    function openCustDetails(cNum,accId){
    	//alert(cNum+" "+accId)
    	var year='2014';
    	var from=$("#from").val();
		if(cNum !='' && cNum !=undefined && year !='' && year !=undefined){
			if(undefined==from || null==from || ''==from || 'home' !=from){ 
			$("#custForm").attr("action","./home_template2")
			$("#reqCustNum").val(cNum);
			//$("#reqYear").val(year);
			  $('#custForm').submit();
			} else if(from=='home'){ 
				$("#custForm").attr("action","./home_template2")
				$("#reqCustNum").val(cNum);
				//$("#reqYear").val(year);
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

  function getSubPlayList(selPlayType){ 
			 var formData={};
			 var custid=$("#accId").val();
			 var selectedplays="'";
			 
			 if(undefined !=selPlayType && selPlayType!='ALL' && (selPlayType.length>1)){
				 selPlayType='ALL'
			    }
			 $.ajax({
				 	dataType: "json",
					url : ctx+"/getSubPlayList/"+selPlayType+"/"+custid ,
					type : "GET",
					cache : false,
					data : JSON.stringify(formData),
					timeout : 1000000,
					success : function(data, textStatus, jqXHR) {
                      var count=0;
                      var optionTxt=new Array();
                      var optionVal=new Array();
                      if(data.listOfSubPlay !=null && data.listOfSubPlay !=undefined){
                  		$.each(data.listOfSubPlay, function(i, item) {
                  			optionTxt[count]=item.playSegDesc;
                  			optionVal[count]=item.playSegType;
                  			count++;
                  		  });
                  		
                  		//enhancement start 
                  		/*var optionTxt1=["Defector - Less active, 'value shoppers, that need to be reminded about the overall value Staples Advantage delivers",
                  		              "Lapsing - Valuable customers with growth potential at a risk of leaving Staples Advantage",
                  		              "Nova - Sticky cross-category buyers with high growth potential",
                  		              "Supernova - Our Most Valuable Customers with large spend across almost all core and BOS categories and make frequent purchases",
                  		              "Star Potential- Firmly believer in Staples Advantage, great prospects for more complex products and services",
                  		              "Stars- Moderate core and BOS spend",
                  		              "Late Adopter- Still needing a 'reason to believe in Staples Advantage'",
                  		              "Rising Stars - High initial office and boss spend, show good growth potential",
                  		              "Slow Starter - Moderate core and BOS spend",
                  		              "Not Engaging - Still needing a 'reason to believe in Staples Advantage'",
                  		              "A - Potential for at least 15K annual growth",
                  		              "B - Potential for at least 8K annual growth",
                  		              "C - Potential for at least 3K annual growth",
         R         		              "D - DEAD account",
                  		              "E - No growth"
                  		                ];*/
                  		/*var optionTxt1=["Defector",
                    		              "Lapsing",
                    		              "Nova",
                    		              "Supernova",
                    		              "Star Potential",
                    		              "Stars",
                    		              "Late Adopter",
                    		              "Rising Stars",
                    		              "Slow Starter",
                    		              "Not Engaging",
                    		              "A",
                    		              "B",
                    		              "C",
                    		              "D",
                    		              "E"
                    		                ];*/
                  		if(data.segAndQualSubPlays.vapScore !=null && data.segAndQualSubPlays.vapScore !=undefined){
                  	  		$.each(data.segAndQualSubPlays.vapScore, function(i, item) {
                  	  			optionTxt[count]=item.vapScoreName+" - "+item.vapScoreDesc;
                  	  			optionVal[count]=item.vapScoreId+""+item.vapScoreType+'_'+item.vapScoreName;
                  	  			count++;
                  	  		  });
                  		}
                  		if(data.segAndQualSubPlays.qualifyScore !=null && data.segAndQualSubPlays.qualifyScore !=undefined){
                  	  		$.each(data.segAndQualSubPlays.qualifyScore, function(i, item) {
                  	  			optionTxt[count]=item.qualScoreName+" - "+item.qualScoreDesc;
                  	  			optionVal[count]=item.qualScoreId+'Qual_'+item.qualScoreName;
                  	  			count++;
                  	  		  });
                  		}
                        //var optionVal1=["Mature","Mature","Mature","Mature","Transitioning","Transitioning","Transitioning","Young","Young","Young","Qual","Qual","Qual","Qual","Qual"];
                        //optionTxt1=createSegmentAndQualData(data.segAndQualSubPlays,optionTxt1);
                        //optionTxt=optionTxt.concat(optionTxt1);
                        //optionVal=optionVal.concat(optionVal1);
                  		// enhancement ended
                  		
                      }
                      //alert(optionVal+"----"+optionTxt);
                      subPlayKeyValArr=getSubPlaySeries(optionVal,optionTxt);
                      if(undefined != Object.keys(subPlayKeyValArr) && undefined != Object.keys(subPlayKeyValArr).length && null !=Object.keys(subPlayKeyValArr).length && '' != Object.keys(subPlayKeyValArr).length){
                    	var count=0;
                    	for (var j in subPlayKeyValArr) {
      		        	var key=j;//alert(key);
      		        	selectedSubPlaysArr[count]=subPlayKeyValArr[key];
      		        	//alert("key ="+key+" val ="+subPlayKeyValArr[key]);
      		        	count++;
      		        	
      		           }
      		        
                      }

                      var optgroups = getOptionGroup(optionVal,optionTxt);                           
                      $("#subPlayId").multiselect('dataprovider', optgroups);
                      $('#subPlayId').multiselect('selectAll', false);
                      $('#subPlayId').next().children(":first").children(":first").html("All selected ("+optionTxt.length+")");
                      var selectedPlay=$('#playSec').next().children(":first").children(":first").html();
                      
                      //alert("in="+$("#subPlayParams").val()+" sel play ="+selectedPlay +"desel = "+deselectValArr)
                      //alert("-->"+selectedPlay)
                      if(($("#subPlayParams").val()=='' && selectedPlay !='ALL')){
                    	    disabledSubPlays(selectedPlay);
                    	    if(selectedPlay.indexOf(",")!=-1){
                    	    var playArr=selectedPlay.split(",");
                    		
              			  if(playArr.length>0){
              				  var val=[];
					   			for(var c=0;c< playArr.length;c++){
					   				 val.push(playArr[c]);	 
					   			}
					   			$('#playSec').multiselect('select', val);
						      }
                    	    }else{
                    	    	$('#playSec').multiselect('select', selectedPlay);
                    	    }
                      }else if(selectedPlay =='ALL'){ 
                    		var v=['multiselect-all','Growth','Retention','Expansion','Mature','Transitioning','Young','AllQul'];
                    		$('#playSec').multiselect('select', v); 
                      }
                    	//  deselectValArr=[]; 
                      //alert($("#from").val()+"---"+$("#subPlayParams").val()+"9090"+deselectValArr)
                      
                      if(((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val())) ){
                    	  var selPlayArr=new Array();
                    	  if((''!=selectedPlay) && (undefined!=selectedPlay)){
                    		 // alert(11+"--"+selectedPlay);
                    		  if(selectedPlay.indexOf(",")!=-1){
                    			  selPlayArr=selectedPlay.split(",");
                    		
                    			  if(selPlayArr.length>0){
                    				  var val=[];
      					   			for(var c=0;c< selPlayArr.length;c++){
      					   				 val.push(selPlayArr[c]);	 
      					   			}
      					   			$('#playSec').multiselect('select', val);
      					   			//alert(val+"--"+val.length);
      					   			if(val.length>6){
      					   			$('#playSec').multiselect('select','multiselect-all');
      					   			}
      						      }
                    		  }else{
                    			  $('#playSec').multiselect('select', [selectedPlay]);
                    			  //$("input[type=checkbox][value="+selectedPlay+"]").prop("checked",true)
                    		  }
                    		  
                    	  }
						   
	      		           disabledSubPlays(selectedPlay);
						   var count=0,deselCount=0;
						   var deselectArr=new Array();
						   for (var j in subPlayKeyValArr) {
      		        	    var key=j; //alert(key);
      		        	    selectedSubPlaysArr[count]=subPlayKeyValArr[key];
      		        	    //alert("key ="+key+" val ="+selectedSubPlaysArr[count])
      		        	    for(var cnt=0;cnt<deselectValArr.length;cnt++){
      		        	    	if(deselectValArr[cnt]==selectedSubPlaysArr[count]){
      		        	    		deselectArr[deselCount]=key;
      		        	    		deselCount++;
      		        	    	}
      		        	    }
      		        	    count++;
      		               }
						   var values = [];
						   //alert("desel="+deselectArr)
						   if(deselectArr.length>0){
					   			for(var c=0;c< deselectArr.length;c++){
					   				 values.push(deselectArr[c]);	 
					   			}
					   			$('#subPlayId').multiselect('deselect', values);
						   }
						   //$("#subPlayParams").val("");	   		
						   //$("#from").val("home")
						 }
					   }
        			 })
        			
	  }
  
  function getLableValueMap(optionVal,optionTxt,playName){
	  if(((playName).indexOf("Mature") !=-1) || ((playName).indexOf("Transitioning")!=-1) || ((playName).indexOf("Young")!=-1) || ((playName).indexOf("Qual")!=-1)){
		  playName=playName.split("_")[0];
		  playName=playName.replace(/[0-9]/g, '');
	  }
    	var labels = new Array();
		var values = new Array();
		var count=-1, arrCount=0;
		for(var cnt=0;cnt<optionVal.length;cnt++){
			//alert(optionVal[cnt]+"======="+playName);
		 if(optionVal[cnt].indexOf(playName)!=-1){ 
			 labels[arrCount]=optionTxt[cnt];
			 values[arrCount]=optionVal[cnt];
			 arrCount++;
		 }
		}
		var keyValPair=[];
		//alert("length"+labels+"---"+values);
		for(var i=0;i<labels.length;i++){
			if(((playName).indexOf("Mature") !=-1) || ((playName).indexOf("Transitioning")!=-1) || ((playName).indexOf("Young")!=-1) || ((playName).indexOf("Qual")!=-1)){
			keyValPair.push({label: ''+labels[i], value: values[i]});
			}else{
				keyValPair.push({label: ''+labels[i], value: i+''+values[i]});
			}
		}
		return keyValPair;
		
  }
  function getOptionGroup(optionVal,optionTxt){
	  var gfound='',rfound='',efound='',Mfound='',Tfound='',Yfound='',Qfound='';
     	var labels = new Array();
		var values = new Array();
		var count=-1, arrCount=0;
		var keyValPair=[];
		for(var cnt=0;cnt<optionVal.length;cnt++){
		 if(optionVal[cnt]=='Growth'){
			 gfound=optionVal[cnt];
		 }else if(optionVal[cnt]=='Retention'){
			 rfound=optionVal[cnt];
		 }else if(optionVal[cnt]=='Expansion'){ 
			 efound=optionVal[cnt];
		 }else if((optionVal[cnt]).indexOf('Mature')!=-1){ 
			 Mfound=optionVal[cnt];
		 }else if((optionVal[cnt]).indexOf('Transitioning')!=-1){
			 Tfound=optionVal[cnt];
		 }else if((optionVal[cnt]).indexOf('Young')!=-1){
			 Yfound=optionVal[cnt];
		 }else if((optionVal[cnt]).indexOf('Qual')!=-1){
			 Qfound=optionVal[cnt];
		 }
		 
		}
		if(gfound=='Growth')
			keyValPair.push({label: ''+gfound, children: getLableValueMap(optionVal,optionTxt,gfound)});
		if(rfound=='Retention')
			keyValPair.push({label: ''+rfound, children: getLableValueMap(optionVal,optionTxt,rfound)});
		if(efound=='Expansion'){ 
			keyValPair.push({label: ''+efound, children: getLableValueMap(optionVal,optionTxt,efound)});
		}
		if(((Mfound).indexOf('Mature')) !=-1){
			keyValPair.push({label: ''+Mfound, children: getLableValueMap(optionVal,optionTxt,Mfound)});
		}
		if(((Tfound).indexOf('Transitioning')) !=-1){ 
			keyValPair.push({label: ''+Tfound, children: getLableValueMap(optionVal,optionTxt,Tfound)});
		}
		if(((Yfound).indexOf('Young')) !=-1){ 
			keyValPair.push({label: ''+Yfound, children: getLableValueMap(optionVal,optionTxt,Yfound)});
		}
		if(((Qfound).indexOf('Qual')) !=-1){ 
			keyValPair.push({label: ''+Qfound, children: getLableValueMap(optionVal,optionTxt,Qfound)});
		}
		return keyValPair;
		
}
 
  function getSubPlaySeries(optionVal,optionTxt){
		var playNames=['Growth','Retention','Expansion','Mature','Transitioning','Young','Qual'];
 	    var Txts = new Array();
		var Vals = new Array();
		var count=0, arrCount=0;
		for(var typeCount=0;typeCount<7;typeCount++){
		 for(var cnt=0;cnt<optionVal.length;cnt++){
		  if((optionVal[cnt]).indexOf(playNames[typeCount])!=-1){
			 Txts[arrCount]=optionTxt[cnt];
			 if(((optionVal[cnt]).indexOf("Mature")<0) && ((optionVal[cnt]).indexOf("Transitioning")<0) && ((optionVal[cnt]).indexOf("Young")<0) && ((optionVal[cnt]).indexOf("Qual")<0)){
			 Vals[arrCount]=count+""+optionVal[cnt];
			 }
			 else{ 
			 Vals[arrCount]=optionVal[cnt];
			 }
			 arrCount++;
			 count++;
		  }
		 }
		 count=0;
		}
		var keyValPair=new Array();
		for(var i=0;i<Txts.length;i++){
      	  key= Vals[i];
      	  keyValPair[key]= Txts[i];
		  }
		return keyValPair;
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
function bindEventToSubPlayLables(){
	  $("#GrChk").on("click", function(event){
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Growth]").length)-1;
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Growth");	 
			 }
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Growth');
			 else
				 $('#playSec').multiselect('deselect', 'Growth');
			 configureSubPlay($(this),'Growth',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
	  $("#RetChk").on("click", function(event){
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Retention]").length)-1;
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Retention");	 
			 }
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Retention');
			 else
				 $('#playSec').multiselect('deselect', 'Retention');
			 configureSubPlay($(this),'Retention',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
	  $("#ExpChk").on("click", function(event){
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Expansion]").length)-1;
			 
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Expansion");	 
			 }
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Expansion');
			 else
				 $('#playSec').multiselect('deselect', 'Expansion');
			 configureSubPlay($(this),'Expansion',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
	  $("#MatChk").on("click", function(event){ 
		    var values = [];
		    var GrowthItemlength=($("input[type=checkbox][value*=Mature]").length)-1;
		    var count=0;
			 $("input[type=checkbox][value*=Mature]").each(function(){
				    values[count]=$(this).attr("value");
				    count++;
			 });
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Mature');
			 else
				 $('#playSec').multiselect('deselect', 'Mature');
			 configureSubPlay($(this),'Mature',values);
			 onChangeSubPlayLable();
		  event.stopImmediatePropagation();
		});	
	  $("#TrChk").on("click", function(event){ 
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Transitioning]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Transitioning]").each(function(){
				    values[count]=$(this).attr("value");
				    count++;
			 });
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Transitioning');
			 else
				 $('#playSec').multiselect('deselect', 'Transitioning');
			 configureSubPlay($(this),'Transitioning',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
	  $("#YngChk").on("click", function(event){
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Young]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Young]").each(function(){
				    values[count]=$(this).attr("value");
				    count++;
			 });
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'Young');
			 else
				 $('#playSec').multiselect('deselect', 'Young');
			 configureSubPlay($(this),'Young',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
	  $("#QulChk").on("click", function(event){
		    var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Qual]").length)-1;
			 var count=0;
			 $("input[type=checkbox][value*=Qual]").each(function(){
				    values[count]=$(this).attr("value");
				    count++;
			 });
			 if($(this).prop("checked")==true)
				 $('#playSec').multiselect('select', 'AllQul');
			 else
				 $('#playSec').multiselect('deselect', 'AllQul');
			 configureSubPlay($(this),'Qual',values);
			 onChangeSubPlayLable(); 
		  event.stopImmediatePropagation();
		});	
}
function configureSubPlay(obj,name,values){
	if($(obj).prop("checked")==true){
		 checkedSubPlay(name,values);
		 $('#playSec').multiselect('select',name);
	 }else{
		 uncheckedSubPlay(name,values);
		 $('#playSec').multiselect('deselect',name);
	 }
}
function uncheckedSubPlay(subPlayName,values){
	$("input[type=checkbox][value*="+subPlayName+"]").prop("disabled",true);
	 $("input[type=checkbox][value*="+subPlayName+"]").parent().css("cursor","not-allowed")
	 $("input[type=checkbox][value*="+subPlayName+"]").parent().css("color","#444")
	 $("input[type=checkbox][value*="+subPlayName+"]").parent().parent().parent().css("opacity","0.7")
	 $("input[type=checkbox][value*="+subPlayName+"]").parent().parent().parent().attr("class","")
	 $("input[type=checkbox][value="+subPlayName+"]").prop("disabled",false);
	 $("input[type=checkbox][value="+subPlayName+"]").parent().css("cursor","pointer")
	 $("input[type=checkbox][value="+subPlayName+"]").parent().css("color","currentColor")
	 $("input[type=checkbox][value="+subPlayName+"]").parent().parent().parent().css("opacity","1");
	 $("input[type=checkbox][value="+subPlayName+"]").parent().parent().parent().attr("class","active");
	 $('#subPlayId').multiselect('deselect', values);
}
function checkedSubPlay(subPlayName,values){
$("input[type=checkbox][value*="+subPlayName+"]").prop("disabled",false);
//$("input[type=checkbox][value*=Growth]").prop("checked",true);
$("input[type=checkbox][value*="+subPlayName+"]").parent().css("cursor","pointer")
$("input[type=checkbox][value*="+subPlayName+"]").parent().css("color","#004c74")
$("input[type=checkbox][value*="+subPlayName+"]").parent().parent().parent().css("opacity","1")
$("input[type=checkbox][value*="+subPlayName+"]").parent().parent().parent().attr("class","active")
$('#subPlayId').multiselect('select', values);
}
function createSegment(obj,optionVal,optionTxt){
	if(obj.vapScore !=null && obj.vapScore !=undefined){
  		$.each(obj.vapScore, function(i, item) {
  			optionTxt[count]=item.vapScoreName+"-"+item.vapScoreDesc;
  			optionVal[count]=item.vapScoreType;
  			//count++;
  			//alert(item.vapScoreType+"--"+item.vapScoreName+"-"+item.vapScoreDesc);
  		  });
	}
	var optionTxtArr=["Defector - Less active, 'value shoppers, that need to be reminded about the overall value Staples Advantage delivers",
		              "Lapsing - Valuable customers with growth potential at a risk of leaving Staples Advantage",
		              "Nova - Sticky cross-category buyers with high growth potential",
		              "Supernova - Our Most Valuable Customers with large spend across almost all core and BOS categories and make frequent purchases",
		              "Star Potential- Firmly believer in Staples Advantage, great prospects for more complex products and services",
		              "Stars- Moderate core and BOS spend",
		              "Late Adopter- Still needing a 'reason to believe in Staples Advantage'",
		              "Rising Stars - High initial office and boss spend, show good growth potential",
		              "Slow Starter - Moderate core and BOS spend",
		              "Not Engaging - Still needing a 'reason to believe in Staples Advantage'",
		              "A - Potential for at least 15K annual growth",
		              "B - Potential for at least 8K annual growth",
		              "C - Potential for at least 3K annual growth",
		              "D - DEAD account",
		              "E - No growth"
		                ];
	for(var cnt=0;cnt<optionTxtArr.length;cnt++){
		var val= (optionTxtArr[cnt]).split("-")[0];
		val=$.trim(val)
	}
	
}
$(document).ready(function(){ 
	if($("#from").val()=='home' ){ 
	var v=['multiselect-all','Growth','Retention','Expansion','Mature','Transitioning','Young','AllQul'];
	$('#playSec').multiselect('select', v);
	}
	});
function getSelectedCustSegIds(){
var counter=0
var custIds='';
var values=[];
$("input[type=checkbox][value*=Mature_]:checked").each(function(){
	    var val=$(this).attr("value");
	    values[counter]= val.replace( /\D+/g, '');
	    counter++;
});


$("input[type=checkbox][value*=Young_]:checked").each(function(){
	var val=$(this).attr("value");
    values[counter]= val.replace( /\D+/g, '');
    counter++;
});


$("input[type=checkbox][value*=Transitioning_]:checked").each(function(){
	var val=$(this).attr("value");
    values[counter]= val.replace( /\D+/g, '');
    counter++;
});

if(undefined !=values || '' !=values){
custIds=values.join(",");
}

return custIds.toString();
}
function getSelectedQualScoreIds(){
	var counter=0;
	var qualIds='';
	var values=[];
	$("input[type=checkbox][value*=Qual]:checked").each(function(){
		    var val=$(this).attr("value");
		    values[counter]= val.replace( /\D+/g, '');
		    counter++;
	});
	if(undefined !=values || '' !=values){
		qualIds=values.join(",");
		}
	return qualIds.toString();
}
function bindEventsToMainFilter(){
	$("input[type=checkbox][value=multiselect-all]").on("click", function(event){ 
		handleAllFilterOperation($(this));
	});
	$("input[type=checkbox][value=Growth]").on("click", function(event){ 
	    var values = [];
	    var count=0;
		 $("input[type=checkbox][value$=Growth]").each(function(){
			 if($(this).attr("value")!='Growth'){
			    values[count]=$(this).attr("value");
			    count++;
			 }
		 });
		 if($(this).prop("checked")==false){
		 $('#subPlayId').multiselect('deselect', values);
		 $('#playSec').multiselect('deselect','Growth');
		 }
		 else{
	     $('#subPlayId').multiselect('select', values);
		 $('#playSec').multiselect('select','Growth');
		 }
		 playChange();
		 configureSubPlay($(this),'Growth',values);
	  event.stopImmediatePropagation();
	});	
	$("input[type=checkbox][value=Retention]").on("click", function(event){ 
	    var values = [];
	    var count=0;
		 $("input[type=checkbox][value$=Retention]").each(function(){
			 if($(this).attr("value")!='Retention'){
			    values[count]=$(this).attr("value");
			    count++;
			 }
		 });
		 if($(this).prop("checked")==false){
		 $('#subPlayId').multiselect('deselect', values);
		 $('#playSec').multiselect('deselect','Retention');
		 }
		 else{
	     $('#subPlayId').multiselect('select', values);
	     $('#playSec').multiselect('select','Retention');
		 }
		 playChange();
		 configureSubPlay($(this),'Retention',values);
	  event.stopImmediatePropagation();
	});	
	$("input[type=checkbox][value=Expansion]").on("click", function(event){ 
	    var values = [];
	    var count=0;
		 $("input[type=checkbox][value$=Expansion]").each(function(){
			 if($(this).attr("value")!='Expansion'){
			    values[count]=$(this).attr("value");
			    count++;
			 }
		 });
		 if($(this).prop("checked")==false){
		 $('#subPlayId').multiselect('deselect', values);
		 $('#playSec').multiselect('deselect','Expansion');
		 }
		 else{
	     $('#subPlayId').multiselect('select', values);
	     $('#playSec').multiselect('select','Expansion');
		 }
		 playChange();
		 configureSubPlay($(this),'Expansion',values);
	  event.stopImmediatePropagation();
	});	
	$("input[type=checkbox][value=Mature]").on("click", function(event){ 
	    var values = [];
	    var count=0;
		 $("input[type=checkbox][value*=Mature_]").each(function(){
			    values[count]=$(this).attr("value");
			    count++;
		 });
		 if($(this).prop("checked")==false){
			 $('#playSec').multiselect('deselect','Mature');
			 }
		else{
		     $('#playSec').multiselect('select','Mature');
		}	 
		 configureSubPlay($(this),'Mature_',values);
		 playChange();		  
	  event.stopImmediatePropagation();
	});	
	$("input[type=checkbox][value=Transitioning]").on("click", function(event){ 
	    var values = [];
	    var GrowthItemlength=($("input[type=checkbox][value*=Transitioning_]").length)-1;
	    var count=0;
		 $("input[type=checkbox][value*=Transitioning_]").each(function(){
			    values[count]=$(this).attr("value");
			    count++;
		 });
		 if($(this).prop("checked")==false){
			 $('#playSec').multiselect('deselect','Transitioning');
		 }
		else{
		     $('#playSec').multiselect('select','Transitioning');
		}	 
		 configureSubPlay($(this),'Transitioning_',values);
		 playChange();		  
	  event.stopImmediatePropagation();
	});
	$("input[type=checkbox][value=Young]").on("click", function(event){ 
	    var values = [];
	    var GrowthItemlength=($("input[type=checkbox][value*=Young_]").length)-1;
	    var count=0;
		 $("input[type=checkbox][value*=Young_]").each(function(){
			    values[count]=$(this).attr("value");
			    count++;
		 });
		 if($(this).prop("checked")==false){
			 $('#playSec').multiselect('deselect','Young');
		 }
		else{
		     $('#playSec').multiselect('select','Young');
		}
		 configureSubPlay($(this),'Young_',values);
		 playChange();		  
	  event.stopImmediatePropagation();
	});
	$("input[type=checkbox][value=AllQul]").on("click", function(event){
	    var values = [];
	    var count=0;
		 $("input[type=checkbox][value*=Qual_]").each(function(){
			    values[count]=$(this).attr("value");
			    count++;
		 });
		 if($(this).prop("checked")==false){
			 $('#playSec').multiselect('deselect','AllQul');
		 }
		else{
		     $('#playSec').multiselect('select','AllQul');
		}
		 configureSubPlay($(this),'Qual_',values);
		 playChange();
	  event.stopImmediatePropagation();
	});
}
function onChangeSubPlayLable()
{
	 $("input[type=search]").val("");
	 $("#from").val("home")
	 deselectValArr=[];
	 $("#subPlayParams").val("")
	 //deselectValArr)
	 selectedPlay=$( "#playSec").val();
	 $("#CustSegLbl").attr("style","background-color:lightslategray !important")
	 if((undefined ==selectedPlay) || (selectedPlay==null) || (selectedPlay=='')){
		 selectedPlay=$('#playSec').next().children(":first").children(":first").html();
	 }
	 //alert(selectedPlay)
	 populateDataOnLoad(selectedPlay,"plays");
}

function bindEventsToSubFilters(){
	$("input[type=checkbox][value$=Growth]").click(function(event){
		if($(this).val() !='Growth'){
	 var totalLen=$("input[type=checkbox][value$=Growth]").length;
	 var chkdOption=$("input[type=checkbox][value$=Growth][value!=Growth]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
	}
});
	$("input[type=checkbox][value$=Retention]").click(function(event){
		if($(this).val() !='Retention'){
	 var totalLen=$("input[type=checkbox][value$=Retention]").length;
	 var chkdOption=$("input[type=checkbox][value$=Retention][value!=Retention]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
	}
});
	$("input[type=checkbox][value$=Expansion]").click(function(event){
		if($(this).val() !='Expansion'){
	 var totalLen=$("input[type=checkbox][value$=Expansion]").length;
	 var chkdOption=$("input[type=checkbox][value$=Expansion][value!=Expansion]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
	}
});
	
$("input[type=checkbox][value*=Mature_]").click(function(event){ 
	 var totalLen=$("input[type=checkbox][value*=Mature_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Mature_]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
	 
});

$("input[type=checkbox][value*=Transitioning_]").click(function(event){
	 //alert("total length = "+$("input[type=checkbox][value*=Transitioning_]").length +" checked = "+$("input[type=checkbox][value*=Mature_]:checked").length);
	 var totalLen=$("input[type=checkbox][value*=Transitioning_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Transitioning_]:checked").length;
	 //alert($(this).val()+" disabled"+$(this).prop("disabled"));
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
});
$("input[type=checkbox][value*=Young_]").click(function(event){
	 //alert("total length = "+$("input[type=checkbox][value*=Young_]").length +" checked = "+$("input[type=checkbox][value*=Mature_]:checked").length);
	 var totalLen=$("input[type=checkbox][value*=Young_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Young_]:checked").length;
	 //alert($(this).val()+" disabled"+$(this).prop("disabled"));
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
});
$("input[type=checkbox][value*=Qual_]").click(function(event){
	 //alert("total length = "+$("input[type=checkbox][value*=Young_]").length +" checked = "+$("input[type=checkbox][value*=Mature_]:checked").length);
	 var totalLen=$("input[type=checkbox][value*=Qual_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Qual_]:checked").length;
	 //alert($(this).val()+" disabled"+$(this).prop("disabled"));
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
});
return true;	
}
function playChange(){
	$("input[type=search]").val("");
	 $("#from").val("home")
	 deselectValArr=[];
	 $("#subPlayParams").val("");
	 var allPlays="Growth,Retention,Expansion,Mature,Transitioning,Young,AllQul";
	 selectedPlay=$( "#playSec").val();
	 $("#CustSegLbl").attr("style","background-color:lightslategray !important");
	 if((undefined ==selectedPlay) || (selectedPlay==null) || (selectedPlay=='')){
		 selectedPlay=$('#playSec').next().children(":first").children(":first").html();
	 }
	 if($("input[type=checkbox][value=multiselect-all]").prop("checked") == true){
		 selectedPlay='ALL';
	 }else if($("input[type=checkbox][value=multiselect-all]").prop("checked") == false && selectedPlay==allPlays){
		 selectedPlay='none';
	 }
	 //alert("-->"+selectedPlay)
	 var count=0;
	 var selectedTxtArr = new Array();
	 var selectedValArr = new Array();
	 var notselected = $("#subPlayId option").not(':selected');
	 notselected.each(function () {
           selectedValArr[count]=$(this).val();
       	count++;
       });
	 count=0;
       for(count=0;count<selectedValArr.length;count++){
       	var key=selectedValArr[count];
       	selectedTxtArr[count]=subPlayKeyValArr[key];
       	//selectedSubPlaysValArr[count]=key;
       	//alert("key ="+key+" val ="+selectedTxtArr[count])
       }
       selectedSubPlaysArr=selectedTxtArr;
	 populateDataOnLoad(selectedPlay,"plays");
}
function call(disabled,obj){
	if($(obj).children().children().prop("disabled")==true){
		alert("Please use the Filter to select main categories first before choosing Sub Filters.");
	}
}
function validateSelection(){
alert("You must select at least one sub filter entry.  To remove this category please return to Filter and deselect this category.");
}
function handleAllFilterOperation(obj){ 
	var v=['Growth','Retention','Expansion','Mature','Transitioning','Young','Qual'];
	for(var cnt=0;cnt< v.length;cnt++){
	    var values = [];
	    var count=0;
	    if(v[cnt]!='multiselect-all'){
		 $("input[type=checkbox][value*='"+v[cnt]+"']").each(function(){
			 if($(this).attr("value")!=v[cnt]){
			    values[count]=$(this).attr("value");
			    count++;
			 }
		 });
		 //alert($("input[type=checkbox][value=multiselect-all]").prop("checked")+" vals="+values);
		 if($("input[type=checkbox][value=multiselect-all]").prop("checked")==false ){
			 if(v[cnt] =='Growth' || v[cnt] =='Retention' || v[cnt] == 'Expansion'){
				 uncheckedSubPlay(v[cnt],values);	 
			 }else{
				 uncheckedSubPlay(v[cnt]+'_',values);
			 }
		 }
		 else{
			 if(v[cnt] =='Growth' || v[cnt] =='Retention' || v[cnt] == 'Expansion'){
				 checkedSubPlay(v[cnt],values);	 
			 }else{
				 checkedSubPlay(v[cnt]+'_',values);
			 }
		 }
		 
	    }
	    
		 //configureSubPlay($(this),v[cnt],values);
	  //event.stopImmediatePropagation();
	}	
	playChange();
}