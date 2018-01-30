
/**
 * Lomesh Changes 
 */
var ctx = $("#svsURL").val();
var subPlayKeyValArr = new Array();
var selectedSubPlaysArr = new Array();
var selectedSubPlayValArray = new Array();
var deselectValArr = new Array();
var COMMA_CONSTANT = ',';
var ICOMMA_CONSTANT = '\',';
var COMMAI_CONSTANT = ',\'';
var ICOMMAI_CONSTANT = '\',\'';
var selectedSubPlays = 'All';
var accTypeFlag = false;
var onChangeAccType = false;
var statusSave='';
var specialElementHandlers = {
	'#editor' : function(element, renderer) {
		return true;
	}
};
var selected = [];
 $(document)
		.ready(
				function() {

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
					if (undefined != assignType && assignType == 'AM_SAM') {
						AM_SAM = true;

						logUserActivity(1002, 'View Lead Customers To Do List');
						logUserActivity(1001, 'View Customer List / To Do List');
						$("#tab_default_3").css("display", "none");
						$("#samNewLiId").css("display", "none");
						$("#mmLiId").css('height', 'auto')
						$('#tabNavAllGroup').css('display', 'block');
					} else if (undefined != assignType
							&& assignType == 'OTHER_SAM') {
						OTHER_SAM = true;
						$("#tab_default_3").css("display", "none");
						$("#tab_default_2").css("display", "none");
						$("#tab_default_1").css("display", "block");
						$('#tabNavAllGroup').css('display', 'block');
						$("#samLiId").addClass("active");
						$("#mmLiId").css("display", "none");
						$("#samNewLiId").css("display", "none");

						logUserActivity(1002, 'View Lead Customers To Do List');
					} else if (undefined != assignType
							&& assignType == 'OTHER_MM') {
						OTHER_MM = true;
						$("#tab_default_1").css("display", "none");
						$("#tab_default_2").css("display", "block");
						$("#tab_default_3").css("display", "none");
						$("#samLiId").css("display", "none");
						$("#samNewLiId").css("display", "none");
						$('#tabNavAllGroup').css('display', 'block');
						$("#mmLiId").addClass("active");
						$("#mmLiId").css('height', 'auto')
                        $('#helpTrainingPanel').css('display','inline-block');
						logUserActivity(1001, 'View Customer List / To Do List');
					} else if (undefined != assignType
							&& assignType == 'OTHER_SAMD') {
						OTHER_SAMD = true;
						$("#tab_default_1").css("display", "none");
						$("#tab_default_2").css("display", "none");
						$("#tab_default_3").css("display", "block");
						$("#samLiId").css("display", "none");
						$("#mmLiId").css("display", "none");
						$('#tabNavAllGroup').css('display', 'block');
						$("#samNewLiId").addClass("active");

						logUserActivityDotCom(7003,
								'View SAM.COM Customers To Do List');
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
								"lengthMenu" : [ [ 15, 20, 25, -1 ],
										[ 15, 20, 25, "All" ] ],
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"oLanguage" : {
									"sSearch" : "Filter : "
								},
								"processing" : true,
								"responsive" : true,
								"bDestroy" : true,
								"paging" : true,
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]

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
								$("#sliderSubPlaysItem").val(selectedSubPlays);

							});
					$('#dataTables-CustInfo').on(
							'xhr.dt',
							function(e, settings, json, xhr) {
								$("#subPlayParams")
										.val(json.unSelListOfSubPlay);

							});

					/* SAM Table Configure Start */
					$('#dataTables-CustInfoSam').DataTable(
							{
								"lengthMenu" : [ [ 15, 20, 25, -1 ],
										[ 15, 20, 25, "All" ] ],
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"oLanguage" : {
									"sSearch" : "Filter : "
								},
								"processing" : true,
								"responsive" : true,
								"bDestroy" : true,
								"paging" : true,
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]

							});
					var tableSAM = $('#dataTables-CustInfoSam').DataTable();

					$('#next').on('click', function() {
						tableSAM.page('next').draw('page');
					});

					$('#previous').on('click', function() {
						tableSAM.page('previous').draw('page');
					});
					$('#dataTables-CustInfoSam').on('preXhr.dt',
							function(e, settings, data) {
								$("#sliderSubPlaysItem").val(selectedSubPlays);

							});
					$('#dataTables-CustInfoSam').on(
							'xhr.dt',
							function(e, settings, json, xhr) {
								$("#subPlayParams")
										.val(json.unSelListOfSubPlay);

							});

					var tableSAMNew = $('#dataTables-CustInfoSamNew')
							.DataTable();

					$('#next').on('click', function() {
						tableSAMNew.page('next').draw('page');
					});

					$('#previous').on('click', function() {
						tableSAMNew.page('previous').draw('page');
					});
					$('#dataTables-CustInfoSamNew').on('preXhr.dt',
							function(e, settings, data) {
								$("#sliderSubPlaysItem").val(selectedSubPlays);

							});
					$('#dataTables-CustInfoSamNew').on(
							'xhr.dt',
							function(e, settings, json, xhr) {
								$("#subPlayParams")
										.val(json.unSelListOfSubPlay);

							});

					/* SAM New Table Configure Ends */
					$('#playSec').multiselect({
						includeSelectAllOption : true,
						allSelectedText : 'ALL...',
						selectAllText : 'ALL'
					});

					var filterBy = $('#filterBy').val();
					if (filterBy == undefined || filterBy == null
							|| filterBy == 'ALL' || filterBy == '') {
						$(".multiselect-selected-text").html("ALL");
						$("#playSec").next().find("ul").attr("style",
								"width:240px !important;");
						$("#playSec").next().find("ul").find("li:first")
								.children().children().css("background-color",
										"lightgray");
						$("#playSec")
								.next()
								.find("ul")
								.find("li")
								.eq(1)
								.before('<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Play Segment</label></a></li>');
						
						if ((OTHER_MM == true || AM_SAM == true))
							populateDataOnLoad("ALL", "onload");

						if ((AM_SAM == true) || (OTHER_SAM == true)) {
							populateSAMDataOnLoad("ALL", "onload");
						} else if ((AM_SAM == true) || (OTHER_SAMD == true)) {
							populateSAMToDoList("ALL", "onload");
						}

					} else {
						$(".multiselect-selected-text").html(filterBy);
						$("#playSec").val(filterBy);
						$("#playSec").next().find("ul").attr("style",
								"width:240px !important;");
						$("#playSec").next().find("ul").find("li:first")
								.children().children().css("background-color",
										"lightgray");
						$("#playSec")
								.next()
								.find("ul")
								.find("li")
								.eq(1)
								.before(
										'<li id="CusSegLbl" style="background-color:lightslategray !important"><a tabindex="0" style="background-color:lightslategray !important"><label class="FilterLbl"> Play Segment</label></a></li>');
						
						populateDataOnLoad(filterBy, "onload");

						if ((AM_SAM == true) || (OTHER_SAM == true)) {
							populateSAMDataOnLoad(filterBy, "onload");
						} else if ((AM_SAM == true) || (OTHER_SAMD == true)) {
							populateSAMToDoList("ALL", "onload");
						}

					}

					$("#playSec").change(function() {

					});
					bindEventsToMainFilter();
					$('#subPlayId')
							.multiselect(
									{
										numberDisplayed : 1,
										selectAllText : 'Select All Sub Plays',
										buttonWidth : '120px',
										onChange : function(option, checked) {
											bindEventsToSubFilters();
											selectedSubPlaysArr = [];
											var selected = $("#subPlayId option:selected");
											var notselected = $(
													"#subPlayId option").not(
													':selected');

											var selectedValArr = new Array();
											var selectedTxtArr = new Array();
											var count = 0;

											if (notselected.length == 0)
												$("#subPlayParams").val("")
											notselected.each(function() {
												selectedValArr[count] = $(this)
														.val();
												count++;
											});

											count = 0;
											for (count = 0; count < selectedValArr.length; count++) {
												var key = selectedValArr[count];
												selectedTxtArr[count] = subPlayKeyValArr[key];
											}
											selectedSubPlaysArr = selectedTxtArr;
											selectedSubPlayValArray = selectedValArr;
											var selectedPlay = $("#playSec")
													.val();
											populateDataOnLoad(selectedPlay,
													"subPlays");
											$('#subPlayId').multiselect(
													'selectAll', false);
											var values = [];
											for (var count = 0; count < selectedValArr.length; count++) {
												values
														.push(selectedValArr[count]);
											}
											$('#subPlayId').multiselect(
													'deselect', values);
										}

									});
					var aboutData = '<div style="text-align:justify;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;">The To Do List displays a prioritized list of all of the Customers / Companies assigned to the associate, which is their book of business (BOB), as well as an overview of each account. This list is prioritized based on the maximum likelihood of customers who will soon be placing an order. The likelihood of placing an order is based on the following 6 criteria.</div>'

							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">1. User Online Activity:</span> Number of sessions in a given time period, last active date</span></div>'
							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style=" color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">2. Order History:</span> Frequency of orders, last order date </span></div>'
							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">3. Last Live Contacted Date:</span> Number of days since an associate last contacted the customer</span></div>'
							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">4. Account Qualification Score:</span> Weighting on existing frequency based on account growth potential</span></div></div>'
							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">5. Dotcom Activity:</span> Browsing/purchasing activity on Staples.com</span></div></div>'
							+ '<div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span  style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">6. Monetary Value:</span> Previous purchasing history/spend</span></div></div>';
					var tierPopData = '<div style="text-align:justify;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> In Progress......</div>';

					$('#aboutId').popover({
						html : true,
						placement : 'bottom',
						content : aboutData
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
					
					
					/* help & training guide start
					var helpTrainingData = '<div class="modal-header" style="padding:10px 15px;"><h4 class="modal-title modal-tit-pop" style="">Call Order Ranking Logic<span id="uName"></span></h4>'
				        +'</div><div style="text-align:justify;font-size: 12px;color: #4d4d4d;font-family: Helvetica;"><div class="col-md-12 margin-bottom-10"><div class="widget-bg-color-white widget-tab rounded-3">'
						+'<div class="">'

									+'<div class="" style="padding-top: 15px;border:none;">'
									+'<div class="notice notice-sm" style="padding-top: 0px;border:none;"> <div style="text-align:justify;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;">The To Do List displays a prioritized list of all of the Customers / Companies assigned to the associate, which is their book of business (BOB), as well as an overview of each account. This list is prioritized based on the maximum likelihood of customers who will soon be placing an order. The likelihood of placing an order is based on the following 6 criteria.</div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">1. User Online Activity:</span> Number of sessions in a given time period, last active date</span></div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style=" color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">2. Order History:</span> Frequency of orders, last order date </span></div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">3. Last Live Contacted Date:</span> Number of days since an associate last contacted the customer</span></div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">4. Account Qualification Score:</span> Weighting on existing frequency based on account growth potential</span></div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">5. Dotcom Activity:</span> Browsing/purchasing activity on Staples.com</span></div><div style="text-align:left;border-radius:5px !important;padding: 5px 0px 5px 0px; font-size: 12px; color: #4d4d4d;font-family: Helvetica;"> <span style=""><span style="  color: #000;font-weight: 600;font-size: 12px;font-family:Helvetica;">6. Monetary Value:</span> Previous purchasing history/spend</span></div></div>'
									+'</div>'
									+'<hr style="margin-top: 15px !important;margin: 0;border: none;border-top: 1px solid #e5e5e5;">'
				    +'<div class="">'
 
				        +'<ul class="" style="margin-left:0px;">'
							+'		<li class="modal-li-pop" id="li1" style="">'
								       +'<a href="./resources/img/Call_order_ranking_logic.png" class="modal-link-cls-pop" style="text-decoration:underline;" id="a1" download="Call_order_ranking_logic.png">Call Order Ranking Logic</a><span> (Last updated Jan &#39;18)</span>'
									+'</li></ul></div>'
				    +'</div></div></div>'
				    +'<div class="modal-footer col-md-12 modal-foot-pop" style="margin-top: 15px;padding: 4px;"><button type="button" id="cancelPopover" class="btn btn-sm modal-cls-pop" style="color:#fff;outline:0;">CLOSE</button></div>'
				   +'</div>';
					

					var $popup=$('#helpTrainingPanel').popover({
						html : true,
						placement : 'bottom',
						content : helpTrainingData
					}).on('shown.bs.popover', function () {
						var $popup = $(this);
						$(this).next('.popover').find('button#cancelPopover').click(function (e) {
					        $popup.popover('hide');
					    });
					});
					help & training guide ends*/
					
					deleteCoookie();
				});

 
 function disabledSubPlays(selectedPlay){ 
	 if(undefined !=selectedPlay && (selectedPlay.indexOf("ALL..")!=-1)){
		   selectedPlay="Growth,Retention,Expansion,Mature,Transitioning,Young,AllQul";
	 }
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
			 var values = [];
			 var GrowthItemlength=($("input[type=checkbox][value*=Growth]").length)-1;
			 for(var count=0;count<GrowthItemlength;count++){
				 values.push(count+"Growth");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);	
	     }else{
	    	 $("input[type=checkbox][id=GrChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Growth]").prop("disabled",false);
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
			 var values = [];
			 var RetentionItemlength=($("input[type=checkbox][value*=Retention]").length)-1;
			 for(var count=0;count<RetentionItemlength;count++){
				 values.push(count+"Retention");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=RetChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Retention]").prop("disabled",false);
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
			 var values = [];
			 var ExpansionItemlength=($("input[type=checkbox][value*=Expansion]").length)-1;
			 for(var count=0;count<ExpansionItemlength;count++){
				 values.push(count+"Expansion");	 
			 }
		     $('#subPlayId').multiselect('deselect', values);
	     }else{
	    	 $("input[type=checkbox][id=ExpChk]").prop("checked",true);
	    	 $("input[type=checkbox][value*=Expansion]").prop("disabled",false);
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
	 }
	
	 $("#subPlayId").next().children(":last").children("li").children("a").each(function(){
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
	  }
 }); 
	 bindEventsToSubFilters();
 }
 
	  function populateDataOnLoad(selPlayType,from){ 
		  var selectedCustSegIds="";
			 var selectedQualScoreIds= ""; 
			 var formData={};
			 var custid=$("#accId").val();
			  selectedSubPlays='All';
             if((undefined ==selectedSubPlaysArr || selectedSubPlaysArr ==null || selectedSubPlaysArr =='' || selectedSubPlaysArr.length ==0)&&((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val()))){
		    	 var subPlaysParam= $("#subPlayParams").val();
		    	 if(subPlaysParam.indexOf("~")!=-1){
		    		 selectedSubPlaysArr=subPlaysParam.split("~");
		    		 deselectValArr=subPlaysParam.split("~");
		    	 }else if('' != subPlaysParam){
		    		 selectedSubPlaysArr[0]=subPlaysParam;
		    		 deselectValArr[0]=subPlaysParam;
		    	 }
		     }
		     if((undefined != $("#from").val() && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && $("#filterBy").val()=='ALL')){
		    		 selectedSubPlaysArr='';
             }
			 if(undefined !=selectedSubPlaysArr && selectedSubPlaysArr !=null && selectedSubPlaysArr !='' && (from=='subPlays' || from=='plays' || 'DashBoard' == $("#from").val()) ){
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
            if(from=='subPlays' && ((undefined !=selectedSubPlaysArr && selectedSubPlaysArr.length==0) || (undefined !=selectedSubPlaysArr && undefined ==selectedSubPlaysArr.length))){
            	selectedSubPlays="All";
            }
			 if( ( selPlayType=='null' || selPlayType==null || selPlayType=='None selected' ) && (selectedSubPlays != 'All')){  
		     selPlayType='none';
			 }
			 if(("onload" ==from)) { 
					getSubPlayList(selPlayType);
				}
			
			 var count=1;
			 var agentEmailId='';
			 var flag='';
			 var test="k";
			 var repRoleCode=$("#repRoleCode").val();
			 var accType=$("#accTypeVal").html();
			 if(onChangeAccType==false)
				 repRoleCode="";
		     if($("#accTypeId").is(":visible")==false)
			 accType='NA';
		     if('Y'!= statusSave)
			 $("#filterBy").val(selPlayType);
			
			 $('#dataTables-CustInfo').dataTable( {
			        "bDestroy" : true,
			       "sAjaxSource": ctx+"/getAllCustomers/"+custid+"/"+selPlayType,
			       "fnServerParams": function (aoData) { 
			    	   aoData.push({ "name": "selectedSubPlays", "value": selectedSubPlays}); 
			    	   aoData.push({ "name": "selectedQualScore", "value": ""});
			    	   aoData.push({ "name": "selectedSegScore", "value": ""});
			    	   aoData.push({ "name": "repRoleCode", "value": repRoleCode});
			    	   aoData.push({ "name": "accType", "value": accType});
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
					'stateSaveParams':function(settings, data){

					},
					/*"rowCallback": function( row, data ) {
			            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
			                $(row).addClass('selected');
			            }
			        },*/
					"fnDrawCallback": function ( oSettings ) {
						statusSave='';
						$('#dataTables-CustInfo_wrapper .dataTables_scrollBody').scrollTop(0);
						if(undefined != agentEmailId && agentEmailId !=''){
							var agentName=agentEmailId.toString();
							
							if(undefined !=flag && flag!='' && flag=='TRUE'){
							$("span#agentName").html(agentName+"'S TO DO LIST");
							}else if(undefined !=flag && flag!='' && flag=='DM'){
								$("span#agentName").html(agentName+"'S LIST");
								$("#dataTables-CustInfo tr th:nth-child(1)").hide();
								$("#dataTables-CustInfo tr td:nth-child(1)").hide();
							}
							else{
								$("span#agentName").html(agentName+"'S LIST");
								$("#filterByPlay").hide();
								$("#playSecDiv").hide();
								$("#subPlayIdDiv").hide();
								$("#filterBySubPlay").hide();
								
								//$(".col-lg-12").css("height","35px");
								
								$("#dataTables-CustInfo tr th:nth-child(9)").hide();
								$("#dataTables-CustInfo tr td:nth-child(9)").hide();
								$("#dataTables-CustInfo tr th:nth-child(1)").hide();
								$("#dataTables-CustInfo tr td:nth-child(1)").hide();
							} 
						}
						$("#dataTables-CustInfo td").css("WORD_BREAK","BREAK-ALL");
						$('#dataTables-CustInfo_length label').css("color","#1d2939");
						$('#dataTables-CustInfo_length label').css("color","#1d2939");
						$('#dataTables-CustInfo_length label').css("font-weight","600");
						$('#dataTables-CustInfo_filter label').css("color","#1d2939");
						$('#dataTables-CustInfo_filter label').css("text-align","right");
						$('#dataTables-CustInfo_filter label').css("font-weight","600");
						$('#dataTables-CustInfo_info').css("color","#1d2939");
						$('#dataTables-CustInfo_info').css("font-weight","600");
						$('#dataTables-CustInfo_info').parent().addClass("col-lg-6 col-xs-6 col-sm-4 col-md-4");
						$('#dataTables-CustInfo_info').parent().css("padding-left","0px");
						$('#dataTables-CustInfo_paginate').parent().removeClass("col-sm-6");
						$('#dataTables-CustInfo_paginate').parent().addClass("col-lg-6 col-xs-6 col-sm-8 col-md-8");
						$('#dataTables-CustInfo_paginate').parent().css("padding-right","0px");
						$('.form-inline .form-control').css("color","#1d2939");
						$('.form-inline .form-control').css({"border":"1px solid #1d2939","margin-left":"10px","border-radius":"0px","font-family":"inherit","font-weight":"100"});
						$('select .form-control.input-sm').css("border","1px solid #1d2939");
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
						   var selectedPlay=$( "#playSec").val();
						   var filt= $("#filterBy").val();
	      		           disabledSubPlays(selectedPlay);
						 }
						 $("#subPlayId").next().children().attr("title","click to see more info.");
						 						 var $els = $('[id^="status_"]');
						 var ExportIds = $els.map(function(){
							 
						        return this.id;
						    }).get();
						 var customerId='';
						 for (var counter = 0; counter <ExportIds.length; counter++) {
							 var statusDetailsId='';
							 if(ExportIds[counter].indexOf("_")!=-1){
								 
								 statusDetailsId=(ExportIds[counter]).split("_")[1];
							 }
								$('#'+ExportIds[counter]).popover({
									container:'body',
									html : true,
									placement : 'right',
									content : $("#statusDetails_"+statusDetailsId).html()
								}).on("hover", function(){
							        $('.popover').addClass('popoverBasic');
							        $('.popover-content').attr("style","width:360px !important;font-family:Helvetica !important; font-weight:bold !important; box-shadow:1px 2pxy 3px rgba(0,0,0,0.2) !important; padding :9px 22px !important;");
							        customerId=statusDetailsId;
							        
							    }).on('show.bs.popover', function() {
							    	var custId='';
							    	if(undefined !=this.id && '' !=this.id && 'null'!=this.id && (this.id).indexOf("_")!=-1){
							    		custId=(this.id).split("_")[1];
							    	}
							    	if(''!=custId){	
							    	 logChangeSatusActivity(2038, 'Hover over status box to see the contact info',custId);
							    	}
							    	else{
							    	 logUserActivity(2038, 'Hover over status box to see the contact info');
							    	}
								}).on("click", function () {
							        var _this = this;
							        $(this).popover("show");
							        $(".popover").on("mouseleave", function () {
							            $(_this).popover('hide');
							            $("#statusDetails_"+statusDetailsId).css("display","none");
							        });
							    }).on("mouseleave", function () {
							        var _this = this;
							        setTimeout(function () {
							            if (!$(".popover:hover").length) {
							                $(_this).popover("hide");
							                $("#statusDetails_"+statusDetailsId).css("display","none");
							            }
							        }, 300) });
							}
						 $(".nochange").click(function () {
							    return false;
							});
						// var table = $('#dataTables-CustInfo').DataTable();
						// table.column( 4 ).visible( false );
					},
					"aoColumns" : [
						           	{ "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #CC0000 !important;font-size: 16px !important;font-family: ARIALMT;font-weight: bold;">'
												+ convertSymbols(checkUundefinedNullBlankZero(full.callOrder))+
												'</span>';
												
										 }
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full,o) {
											 agentEmailId= full.agentEmailId;
											 var currDate = new Date();
												currDate=currDate.toLocaleDateString("en-IN", {year: "numeric", month: "short",day: "numeric"}).replace(/\s/g,'-');
												
											 if((undefined !=full.contactStatus && full.contactStatus == true)){
												 
												 return '<span id="status_'+checkUundefinedNullBlankZero(full.custNum)+'" style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;" role="button" data-toggle="popover" data-trigger="hover">'
												 +'<input type="checkbox" class="nochange" style="color:#efefef !important;cursor:not-allowed;opacity:.4 !important;"  checked/></span>'
												 +'<div style="display:none" id="statusDetails_'+checkUundefinedNullBlankZero(full.custNum)+'" class="toolTip">'
				              					 +'<div style="font-size:13px;padding-top:5px;padding-left:5px;padding-right:10px;letter-spacing:1px;" id="orderStat" >'
			              						 +'<div style="padding-left:5px;letter-spacing:1px;" >Contacted by: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;">'+ checkUundefinedNullBlankZero(full.loggedInUserName) + '</span></div>'
			              						 +'<div style="padding-left:5px;letter-spacing:1px;">Contacted date: <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;">'+ checkUundefinedNullBlankZero(full.checkedInDate) + '</span></div>'
			              						 +'</div>';
												 		
												}else{
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													 +'<input type="checkbox" onchange="openContactModal('+checkUundefinedNullBlankZero(full.custNum)+',this)"/></span>' ;
												}
											 
										 }
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full,o) {
											 agentEmailId= full.agentEmailId;
											 if(undefined !=full.agentFlag && full.agentFlag !=''){
													
													flag=full.agentFlag;
													}

											 if((undefined !=full.contactStatus && full.contactStatus == true)){

												 return '<span style="color:black !important;font-size: 13px !important;font-family: ARIALMT;"><a href="javascript:openCustDetails1('+full.custNum+','+full.custNum+',\'AM-MM\',\''+full.loggedInUserName+'\',\''+full.checkedInDate+'\')" style="text-decoration:underline !important;font-size: 13px;color: #444;font-family: arialmt;opacity:0.7;letter-spacing:.4px;">'
												 + checkUundefinedNullBlankZero(full.custNum) +'</a></span>' ;

											 }else{

												 return '<span style="color: black !important;font-size: 13px !important;font-family: ARIALMT;"><a href="javascript:openCustDetails('+full.custNum+','+full.custNum+',\'AM-MM\')" style="text-decoration:underline !important;font-size: 13px;color: black;font-weight: bold;font-family: arialmt;opacity:0.7;letter-spacing:.4px;">'
													 + checkUundefinedNullBlankZero(full.custNum) +'</a></span>' ; 
											 }
										 }
									},
									{    "bSortable": false,
										 "mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
												+checkUundefined(full.custType)
												+'</span>';
										 }
										
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;white-space:normal;">' 
												+checkUundefined(full.companyName)+
												'</span>';
										 }
									},
								
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											
											 
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
												       +removeSegDesc(checkUundefined(full.customerSegment))+  
												       '</span>';
										 }
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
												       +checkUundefined(full.vapScore)+  
												       '</span>';
										 }
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
												       +checkUundefined(full.accountQualifyScore)+ 
												       '</span>';
										 }
									},
									{   "bSortable": false,
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											
											 return untrim(checkUundefined(full.playSeg));
										 }
									},
									{
										"mData": null,
										 "mRender" : function(
												 data, type, full) {
											
											 return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
											 +removeLeadingZeros(checkUundefined(full.lastContactedDate))+'</span>';
										 }
									}
									],
					
									"oLanguage": {"sProcessing": '<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10%;padding: 10px;margin-left: -80px;border: none;margin-top: 0%;"><img width="40" height="40" src="./resources/img/helios_loader.gif" style="border: 3px solid darkturquoise;border-radius: 100px !important;"></div>',"sSearch":"Filter :",
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
									$("#dataTables-CustInfo_info").parent().parent().css({"padding":"10px","border-top":"1px solid #e5e5e5","height":"70px", "border-top":"1px solid #e5e5e5"});
									
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
									 if(from !="subPlays" && ($("#subPlayParams").val() =='') ){
									   var selectedPlay=$( "#playSec").val();
									   var filt= $("#filterBy").val();
									 }
				
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
    function openCustDetails(cNum,accId,account){
    	var year='2014';
    	var from=$("#from").val();
		if(undefined !=cNum && cNum !='' &&  undefined !=year && year !='' ){
			if(undefined != account && 'AM-MM'!=account && "AM-SAMNew" != account){ 
			   $("#custForm").attr("action","./home_template3")
			   $("#reqCustNum").val(cNum);
			   $('#custForm').submit();
			} else if((from=='home' || from=='DashBoard' || from =='') && "AM-SAMNew" != account){ 
				$("#custForm").attr("action","./home_template2")
				$("#reqCustNum").val(cNum);
				$('#custForm').submit();
			} else if( (from=='home' || from=='DashBoard' || from =='') && ("AM-SAMNew" == account)){ 
				$("#custForm").attr("action","./home_template4")
				$("#reqCustNum").val(cNum);
				$('#custForm').submit();
			}
		}
    }
    function openCustDetails1(cNum,accId,account,contactName,contactDate){
    	var year='2014';
    	var from=$("#from").val();
		if(undefined !=cNum && cNum !='' &&  undefined !=year && year !='' ){
			if(undefined != account && 'AM-MM'!=account){ 
			   $("#custForm").attr("action","./home_template3")
			   $("#reqCustNum").val(cNum);
			   $("#contactName").val(contactName);
			   $("#contactDate").val(contactDate);
			   $("#contactStatus").val(true);
			   $('#custForm').submit();
			} else if(from=='home' || from=='DashBoard' || from ==''){ 
				$("#custForm").attr("action","./home_template2")
			   $("#contactName").val(contactName);
			   $("#contactDate").val(contactDate);
				$("#reqCustNum").val(cNum);
				$("#contactStatus").val(true);
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
                  		
                      }
                      subPlayKeyValArr=getSubPlaySeries(optionVal,optionTxt);
                      if(undefined != Object.keys(subPlayKeyValArr) && undefined != Object.keys(subPlayKeyValArr).length && null !=Object.keys(subPlayKeyValArr).length && '' != Object.keys(subPlayKeyValArr).length){
                    	var count=0;
                    	for (var j in subPlayKeyValArr) {
      		        	var key=j;
      		        	selectedSubPlaysArr[count]=subPlayKeyValArr[key];
      		        	count++;
      		           }
                      }

                      var optgroups = getOptionGroup(optionVal,optionTxt);                           
                      $("#subPlayId").multiselect('dataprovider', optgroups);
                      $('#subPlayId').multiselect('selectAll', false);
                      $('#subPlayId').next().children(":first").children(":first").html("All selected ("+optionTxt.length+")");
                      var selectedPlay=$('#playSec').next().children(":first").children(":first").html();
                      
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
                      if(((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val())) ){
                    	  var selPlayArr=new Array();
                    	  if((''!=selectedPlay) && (undefined!=selectedPlay)){
                    		  if(selectedPlay.indexOf(",")!=-1){
                    			  selPlayArr=selectedPlay.split(",");
                    		
                    			  if(selPlayArr.length>0){
                    				  var val=[];
      					   			for(var c=0;c< selPlayArr.length;c++){
      					   				 val.push(selPlayArr[c]);	 
      					   			}
      					   			$('#playSec').multiselect('select', val);
      					   			if(val.length>6){
      					   			$('#playSec').multiselect('select','multiselect-all');
      					   			}
      						      }
                    		  }else{
                    			  $('#playSec').multiselect('select', [selectedPlay]);
                    		  }
                    		  
                    	  }
						   
	      		           disabledSubPlays(selectedPlay);
						   var count=0,deselCount=0;
						   var deselectArr=new Array();
						   for (var j in subPlayKeyValArr) {
      		        	    var key=j; 
      		        	    selectedSubPlaysArr[count]=subPlayKeyValArr[key];
      		        	    for(var cnt=0;cnt<deselectValArr.length;cnt++){
      		        	    	if(deselectValArr[cnt]==selectedSubPlaysArr[count]){
      		        	    		deselectArr[deselCount]=key;
      		        	    		deselCount++;
      		        	    	}
      		        	    }
      		        	    count++;
      		               }
						   var values = [];
						   if(deselectArr.length>0){
					   			for(var c=0;c< deselectArr.length;c++){
					   				 values.push(deselectArr[c]);	 
					   			}
					   			$('#subPlayId').multiselect('deselect', values);
						   }
						 }
					   }
        			 })
        			
	  }
  
  function getLableValueMap(optionVal,optionTxt,playName){
	 
    	var labels = new Array();
		var values = new Array();
		var count=-1, arrCount=0;
		for(var cnt=0;cnt<optionVal.length;cnt++){
		 if(optionVal[cnt].indexOf(playName)!=-1){ 
			 labels[arrCount]=optionTxt[cnt];
			 values[arrCount]=optionVal[cnt];
			 arrCount++;
		 }
		}
		var keyValPair=[];
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
		 }
		}
		if(gfound=='Growth')
			keyValPair.push({label: ''+gfound, children: getLableValueMap(optionVal,optionTxt,gfound)});
		if(rfound=='Retention')
			keyValPair.push({label: ''+rfound, children: getLableValueMap(optionVal,optionTxt,rfound)});
		if(efound=='Expansion'){ 
			keyValPair.push({label: ''+efound, children: getLableValueMap(optionVal,optionTxt,efound)});
		}
		
		return keyValPair;
		
}
 
  function getSubPlaySeries(optionVal,optionTxt){
		var playNames=['Growth','Retention','Expansion'];
 	    var Txts = new Array();
		var Vals = new Array();
		var count=0, arrCount=0;
		for(var typeCount=0;typeCount<3;typeCount++){
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
		var v=['multiselect-all','Growth','Retention','Expansion'];
		$('#playSec').multiselect('select', v);
	}
	$('.multiselect').css({
    'font-size': '14px',
    'min-width': '150px',
    'width': 'auto',
    'overflow': 'hidden',
    'text-overflow': 'ellipsis',
    'color':'#1d2939',
    'border-color':'#1d2939'
	});
	$('.multiselect').removeClass('btn').addClass('btn-sm');
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
	 selectedPlay=$( "#playSec").val();
	 $("#CustSegLbl").attr("style","background-color:lightslategray !important")
	 if((undefined ==selectedPlay) || (selectedPlay==null) || (selectedPlay=='')){
		 selectedPlay=$('#playSec').next().children(":first").children(":first").html();
	 }
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
	 var totalLen=$("input[type=checkbox][value*=Transitioning_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Transitioning_]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
});
$("input[type=checkbox][value*=Young_]").click(function(event){
	 var totalLen=$("input[type=checkbox][value*=Young_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Young_]:checked").length;
	 if(chkdOption==0){
		 validateSelection();
		 event.stopImmediatePropagation();
		 return false;
	 }
});
$("input[type=checkbox][value*=Qual_]").click(function(event){
	 var totalLen=$("input[type=checkbox][value*=Qual_]").length;
	 var chkdOption=$("input[type=checkbox][value*=Qual_]:checked").length;
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
	}	
	playChange();
}

function populateSAMDataOnLoad(selPlayType,from){
	  var selectedCustSegIds="";
		 var selectedQualScoreIds= ""; 
		 var formData={};
		 var custid=$("#accId").val();
		  selectedSubPlays='All';
        if((undefined ==selectedSubPlaysArr || selectedSubPlaysArr ==null || selectedSubPlaysArr =='' || selectedSubPlaysArr.length ==0)&&((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val()))){
	    	 var subPlaysParam= $("#subPlayParams").val();
	    	 if(subPlaysParam.indexOf("~")!=-1){
	    		 selectedSubPlaysArr=subPlaysParam.split("~");
	    		 deselectValArr=subPlaysParam.split("~");
	    	 }else if('' != subPlaysParam){
	    		 selectedSubPlaysArr[0]=subPlaysParam;
	    		 deselectValArr[0]=subPlaysParam;
	    	 }
	     }
		 if(undefined !=selectedSubPlaysArr && selectedSubPlaysArr !=null && selectedSubPlaysArr !='' && (from=='subPlays' || from=='plays' || 'DashBoard' == $("#from").val()) ){
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
       if(from=='subPlays' && ((undefined !=selectedSubPlaysArr && selectedSubPlaysArr.length==0) || (undefined !=selectedSubPlaysArr && undefined ==selectedSubPlaysArr.length))){
       	selectedSubPlays="All";
       }
		 if( ( selPlayType=='null' || selPlayType==null || selPlayType=='None selected' ) && (selectedSubPlays != 'All')){  
	     selPlayType='none';
		 }
		 if(("onload" ==from)) { 
				getSubPlayList(selPlayType);
				
			}
		
		 var count=1;
		 var agentEmailId='';
		 var flag='';
		 var test="k";
		 var repRoleCode=$("#repRoleCode").val();
		 var accType=$("#accTypeVal").html();
		 if(onChangeAccType==false)
			 repRoleCode="";
	     if($("#accTypeId").is(":visible")==false)
		 accType='NA';

		 $("#filterBy").val(selPlayType);
							
		 var tab=$('#dataTables-CustInfoSam').dataTable( {
		        /*"bProcessing": true,*/
		        "bDestroy" : true,
		       "sAjaxSource": ctx+"/getAllSamCustomers/"+custid+"/"+selPlayType,
		       "fnServerParams": function (aoData) { 
		    	   aoData.push({ "name": "selectedSubPlays", "value": selectedSubPlays}); 
		    	   aoData.push({ "name": "selectedQualScore", "value": ""});
		    	   aoData.push({ "name": "selectedSegScore", "value": ""});
		    	   aoData.push({ "name": "repRoleCode", "value": repRoleCode});
		    	   aoData.push({ "name": "accType", "value": accType});
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
				
							$(".col-lg-12").css("height","35px");
							
							$("#dataTables-CustInfoSam tr th:nth-child(8)").hide();
							$("#dataTables-CustInfoSam tr td:nth-child(8)").hide();
							$("#dataTables-CustInfoSam tr th:nth-child(4)").hide();
							$("#dataTables-CustInfoSam tr td:nth-child(4)").hide();
						} 
					}
					$("#dataTables-CustInfoSam td").css("WORD_BREAK","BREAK-ALL");
					$('#dataTables-CustInfoSam_length label').css("color","#004c74");
					$('#dataTables-CustInfoSam_length label').css("color","#004c74");
					$('#dataTables-CustInfoSam_length label').css("font-weight","600");
					$('#dataTables-CustInfoSam_filter label').css("color","#004c74");
					$('#dataTables-CustInfoSam_filter label').css("text-align","right");
					$('#dataTables-CustInfoSam_filter label').css("font-weight","600");
					$('#dataTables-CustInfoSam_info').css("color","#004c74");
					$('#dataTables-CustInfoSam_info').css("font-weight","600");
					$('#dataTables-CustInfoSam_info').parent().addClass("col-lg-6 col-xs-6 col-sm-4 col-md-4");
					$('#dataTables-CustInfoSam_info').parent().css("padding-left","0px");
					$('#dataTables-CustInfoSam_paginate').parent().removeClass("col-sm-6");
					$('#dataTables-CustInfoSam_paginate').parent().addClass("col-lg-6 col-xs-6 col-sm-8 col-md-8");
					$('#dataTables-CustInfoSam_paginate').parent().css("padding-right","0px");
					$('.form-inline .form-control').css("color","#004c74");
					$('.form-inline .form-control').css("border","1px solid #004c74");
					$('select .form-control.input-sm').css("border","1px solid #004c74");
					$("#dataTables-CustInfoSam_length").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6");
					$("#dataTables-CustInfoSam_filter").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6 col-sm-7");
					$("#dataTables-CustInfoSam_filter").children().css("width","100%")
					 $('#dataTables-CustInfoSam').removeClass('display').addClass('table table-striped table-bordered');
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
					   var selectedPlay=$( "#playSec").val();
					   var filt= $("#filterBy").val();
     		           disabledSubPlays(selectedPlay);
					 }
					 $("#subPlayId").next().children().attr("title","click to see more info.");
				},
				
				"aoColumns" : [
								{
									"mData": null,
									 "mRender" : function(
											 data, type, full) {
										 agentEmailId= full.agentEmailId;
										 if(undefined !=full.agentFlag && full.agentFlag !=''){
												
												flag=full.agentFlag;
												}
											return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;"><a href="javascript:openCustDetails('+full.LeadCustomerNumber+','+full.LeadCustomerNumber+',\'AM-SAsM\')" style="text-decoration:none !important;font-size: 13px;color: rgb(255, 15, 0);font-weight: 600;font-family: arialmt;opacity:0.7;letter-spacing:.4px;">'
											+checkUundefinedNullBlankZero(full.LeadCustomerNumber)+'</a></span>' ;
									 }
								},
								
								{
									 "mData": null,
									 "mRender" : function(
											 data, type, full) {
											return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
											/*+checkUundefined(full.custType)
											+'</span>';*/
											+checkUundefined(full.LeadContractType)+'</span>' ; 
											
									 }
									
								},
								{
									"mData": null,
									 "mRender" : function(
											 data, type, full) {
											return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;line-height:1.8;">' 
											
											+checkUundefined(full.LeadCompanyName)+'</span>' ;
									 }
								},
								{
									"mData": null,
									 "mRender" : function(
											 data, type, full) {
										 var name = checkUundefined(full.LeadCustomerFirstName+ " "+ full.LeadCustomerLastName);
										var email = checkUundefined(full.LeadCustomerEmail);
										var phone = checkUundefined(full.LeadCustomerPhone);
										var customerContact = generateContactInfo(name, email, phone,'',full.LeadCustomerNumber);
											return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
											
											+customerContact+'</span>';
											
									 }
								}
								],
								"oLanguage": {"sProcessing": '<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10%;padding: 10px;margin-left: -80px;border: none;margin-top: 0%;"><img width="40" height="40" src="./resources/img/helios_loader.gif" style="border: 3px solid darkturquoise;border-radius: 100px !important;"></div>',"sSearch":"Filter :",
									"sEmptyTable":     "No results found or this account is currently part of Helios Holdout Test Group."	
								}

		                 } );
		

		                        $("#dataTables-CustInfoSam td").css("WORD_BREAK","BREAK-ALL");
								$('#dataTables-CustInfoSam_length label').css("color","#004c74");
								$('#dataTables-CustInfoSam_length label').css("color","#004c74");
								$('#dataTables-CustInfoSam_length label').css("font-weight","600");
								$('#dataTables-CustInfoSam_filter label').css("color","#004c74");
								$('#dataTables-CustInfoSam_filter label').css("font-weight","600");
								$('#dataTables-CustInfoSam_info').css("color","#004c74");
								$('#dataTables-CustInfoSam_info').css("font-weight","600");
								$('#ddataTables-CustInfoSam_paginate').css("text-align","right");
								$('.form-inline .form-control').css("color","#004c74");
								$('.form-inline .form-control').css("border","1px solid #004c74");
								$('select .form-control.input-sm').css("border","1px solid #004c74");
								$("#dataTables-CustInfoSam_info").parent().parent().removeClass("row");
								$("#dataTables-CustInfoSam_info").parent().parent().css({"padding":"10px","border-top":"1px solid #e5e5e5","height":"70px"});
								
								 $('#dataTables-CustInfoSam').removeClass('display').addClass('table table-striped table-bordered');
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
									
								$('#dataTables-CustInfoSam_info').attr("style","font-size:12px !important");
								}
	
								 if(undefined != agentEmailId && agentEmailId !=''){
										var agentName=agentEmailId.toString();
										
										if(undefined !=flag && flag!='' && flag=='TRUE'){
										$("span#agentName").html(agentName+"'S TO DO LIST");
										}else{
											$("span#agentName").html(agentName+"'S LIST");	
										} 
									}
								 if(from !="subPlays" && ($("#subPlayParams").val() =='') ){
								   var selectedPlay=$( "#playSec").val();
								   var filt= $("#filterBy").val();
								 }
			
								 accTypeFlag=false;
									onChangeAccType=true;
 }

function generateContactInfo(name,email,phone,timeZone,custId){
	var finalString='';
	if(''!=name)
		finalString +=name;
	if(''!=name && ''!=email)
		finalString +=' | <span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	else if(''!=phone)
		finalString +='<span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	if(''!=email && ''!=phone)
		finalString +=' </br>Ph: <span onclick="javascript:logChangeSatusActivity(8089,\''+formatPhoneLead(phone)+': clicked in Leads To_DO List\','+custId+')">'+formatPhoneLead(phone)+'</span>';
	else if(''!=phone)
		finalString +=' </br>Ph: <span onclick="javascript:logChangeSatusActivity(8089,\''+formatPhoneLead(phone)+': clicked in Leads To_DO List\','+custId+')">'+formatPhoneLead(phone)+'</span>';
	if(undefined!=timeZone && ''!=timeZone)
		finalString +=" | "+(timeZone);
	//alert(finalString);
	return finalString;
}

function generateContactInfoSAM(name,email,phone,timeZone,custId){
	var finalString='';
	if(''!=name)
		finalString +=name;
	if(''!=name && ''!=email)
		finalString +=' | <span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	else if(''!=phone)
		finalString +='<span><a href="javascript:openOutlookLead(\''+email+'\',\'ToDoList\');"  style="color:#0092db;">'+email+'</a></span>';
	if(''!=email && ''!=phone)
		finalString +=' </br>Ph: <span onclick="javascript:logUserActivityDotCom(8090,\''+formatPhoneLead(phone)+': clicked in Dotcom TO_DO Listfrom Cutomer Number '+custId+'\')">'+formatPhoneLead(phone)+'</span>';
	else if(''!=phone)
		finalString +=' </br>Ph: <span onclick="javascript:logUserActivityDotCom(8090,\''+formatPhoneLead(phone)+': clicked in DotCom TO_DO List from Cutomer Number '+custId+'\')">'+formatPhoneLead(phone)+'</span>';
	
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



function populateSAMNewDataOnLoad(selPlayType,from){
	var selectedCustSegIds="";
	var selectedQualScoreIds= ""; 
	var formData={};
	var custid=$("#accId").val();
	selectedSubPlays='All';
	if((undefined ==selectedSubPlaysArr || selectedSubPlaysArr ==null || selectedSubPlaysArr =='' || selectedSubPlaysArr.length ==0)&&((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val()))){
		var subPlaysParam= $("#subPlayParams").val();
		if(subPlaysParam.indexOf("~")!=-1){
			selectedSubPlaysArr=subPlaysParam.split("~");
			deselectValArr=subPlaysParam.split("~");
		}else if('' != subPlaysParam){
			selectedSubPlaysArr[0]=subPlaysParam;
			deselectValArr[0]=subPlaysParam;
	    }
	}
	
		 if(undefined !=selectedSubPlaysArr && selectedSubPlaysArr !=null && selectedSubPlaysArr !='' && (from=='subPlays' || from=='plays' || 'DashBoard' == $("#from").val()) ){
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
       if(from=='subPlays' && ((undefined !=selectedSubPlaysArr && selectedSubPlaysArr.length==0) || (undefined !=selectedSubPlaysArr && undefined ==selectedSubPlaysArr.length))){
       	selectedSubPlays="All";
       }
		 if( ( selPlayType=='null' || selPlayType==null || selPlayType=='None selected' ) && (selectedSubPlays != 'All')){  
	     selPlayType='none';
		 }
		 
		 if(("onload" ==from)) { 
				
			}
		 
		 var count=1;
		 var agentEmailId='';
		 var flag='';
		 var test="k";
		 var repRoleCode=$("#repRoleCode").val();
		 var accType=$("#accTypeVal").html();
		 if(onChangeAccType==false)
			 repRoleCode="";
	     if($("#accTypeId").is(":visible")==false)
		 accType='NA';

		 $("#filterBy").val(selPlayType);
		
		 $("#dataTables-CustInfoSamNew_processing").html('<div id="example_processing_sam" class="dataTables_processing" style="height: 48px;position: relative;width: 10%;padding: 10px;margin-left: -80px;border: none;margin-top: 0%;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 4px solid #26c6da;border-radius: 100px !important;"></div>');
		  $("#dataTables-CustInfoSamNew_processing").css("display","block");
			
		 $.ajax({
			 	dataType: "json",
				url : ctx+"/sales/representive/"+custid+"/customer/list",
				type : "GET",
				cache : false,
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt=0;
					var mkttrHTML = "";
					
					if(data !=null){
						$.each(data.customerInfo, function(i, item) {

							var name = checkUundefined(item.firstName+ " "+ item.lastName);
							var email = checkUundefined(item.contactEmailId);
							var phone = checkUundefined(item.phone);
							var customerContact = generateContactInfo(name, email, phone,'',item.customerNo);
							
							var lastConDate = checkUundefined(item.last_contacted_date);
							if(lastConDate.length > 0){
								lastConDate = lastConDate.substring(0,10);
								var lastConDateArr = lastConDate.split("-");
								lastConDate = lastConDateArr[1] + "/" + lastConDateArr[2] + "/" + lastConDateArr[0];
							}
							
							mkttrHTML += '<tr class="odd gradeX">'
								+'<td class="datatablesTd">'
									+'<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
										+'<a href="javascript:openCustDetails(\''+item.customerNo+'\',\''+item.customerNo+'\',\'AM-SAMNew\');" style="text-decoration:none !important;style="font-size: 15px;color: rgb(255, 15, 0);font-weight: 600;font-family: arialmt;opacity:0.7;letter-spacing:.4px;">'
												+ checkUundefinedNullBlankZero(item.customerNo)
										+'</a>'
									+'</span>'
								+'</td>'
								 +'<td class="datatablesTd">'
								 + checkUundefined(item.tier) +'</td>'
								 +'<td class="datatablesTd">'
								 + checkUundefined(item.account_rank) +'</td>'
								 +'<td class="datatablesTd">'
								 +checkUundefined(item.companyName)+'</td>'	
								 
								 +'<td class="datatablesTd">'
								 + item.callReason +'</td>'
								 +'<td class="datatablesTd">'
								 + item.category +'</td>'
								 +'<td class="datatablesTd">'
								 + lastConDate +'</td>'
								 +'<td class="datatablesTd">'
								 + customerContact +'</td>'
								 +'</tr>';
							
						});
					}
								
								
					$('#OrderDetailsInfoIdSamNew').html("");
					
					$('#OrderDetailsInfoIdSamNew').html(
						'<table class="table table-striped table-hover dt-responsive" id="dataTables-CustInfoSamNew" cellspacing="0"> <thead id="custHeaderSamNew">'
							+'<th width="8%"  style="text-align: left;padding-left: 1%;line-height:1.6 !important;">'
								+'Customer Master </br> Rewards#	</th>'
							
							+'<th width="14%" class="iconCls"  style="text-align: left;padding-left: 1%;pointer-events:none;line-height:1.6 !important;"> '
	                        	+'Master Rewards</br> ' 
								+'<span style="line-height: 1; "> Tier '
									+'<a id="tierIdSam"	data-toggle="tooltip" data-placement="right" title=" Member Rewards Benefits" style="padding-left: 5px; box-shadow: none; pointer-events: auto; cursor: pointer;">'
										+'<i class="fa fa-info-circle" aria-hidden="true" style="margin-top: -2px; color: #cc0000; font-size: 140%; vertical-align: middle; box-xhadow: none;"></i>'
									 +'</a>'
								+'</span>'
							+'</th>'
							
							+'<th width="6%" style="text-align: left;padding-left: 1%;line-height:1.6 !important;">Call </br>Order</th>'
                            +'<th width="12%" style="text-align: left;padding-left: 1%;line-height:1.6 !important;">Company Name</th>'
                            
                            +'<th width="12%" style="text-align: left;padding-left: 1%;line-height:1.6 !important;">'
							+ '<div style="padding-top: 5px; line-height: 1.4;line-height:1.6 !important;">Reason For</div>'
							+ ' <span style="line-height: 1; "> the Call '
							+'<a id="reasonCodeIdSam"	data-toggle="tooltip" data-placement="right" title=" Reason Code Details" style="padding-left: 5px; box-shadow: none; pointer-events: auto; cursor: pointer;">'
							+'<i class="fa fa-info-circle" aria-hidden="true" style="margin-top: -2px; color: #cc0000; font-size: 140%; vertical-align: middle; box-xhadow: none;"></i>'
						    +'</a>'
					        +'</span></th>'
							+ '<th width="8%" style="text-align: left;padding-left: 1%;line-height:1.6 !important;">Category</th>'
							+ '<th width="16%" style="text-align: left;padding-left: 1%;line-height:1.6 !important;">'
							+'Last Contacted</br> Date</th>'
							+'<th width="40%" style="text-align: left;padding-left: 1%;border-right:none;line-height:1.6 !important;">Contact Info</th>'
                            + '</thead>'
							+ '<tbody>' + mkttrHTML
							+ '</tbody></table>');
					
					$("#dataTables-CustInfoSamNew_processing").css("display","none");
					$('#dataTables-CustInfoSamNew').dataTable({
						"lengthMenu": [[10, 20, 30, -1], [10, 20,30, "All"]],
						"order": [],
						"bProcessing": true,
						"aoColumns": [{"bSortable": true},
							{"bSortable": true,"sWidth": '11%'},
							{"bSortable": true,"sWidth": '11%'},
							{"bSortable": true,"sWidth": '11%'},
							{"bSortable": true,"sWidth": '11%'},
							{"bSortable": true,"sWidth": '15%'},
							{"bSortable": true,"sWidth": '15%'},
							{"bSortable": true,"sWidth": '15%'}],
						"fnServerParams": function (aoData) { 
							aoData.push({ "name": "selectedSubPlays", "value": selectedSubPlays}); 
							aoData.push({ "name": "selectedQualScore", "value": ""});
							aoData.push({ "name": "selectedSegScore", "value": ""});
							aoData.push({ "name": "repRoleCode", "value": repRoleCode});
							aoData.push({ "name": "accType", "value": accType});
				    	   	},
				    	   
				    	"oLanguage": { "sSearch": "Filter : "},
				    	
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
									$(".col-lg-12").css("height","35px");
									
									
								} 
							}
							$("#dataTables-CustInfoSamNew td").css("WORD_BREAK","BREAK-ALL");
							$('#dataTables-CustInfoSamNew_length label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_length label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_length label').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_filter label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_filter label').css("text-align","right");
							$('#dataTables-CustInfoSamNew_filter label').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_info').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_info').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_info').parent().addClass("col-lg-6 col-xs-6 col-sm-4 col-md-4");
							$('#dataTables-CustInfoSamNew_info').parent().css("padding-left","15px");
							$('#dataTables-CustInfoSamNew_paginate').parent().removeClass("col-sm-6");
							$('#dataTables-CustInfoSamNew_paginate').parent().addClass("col-lg-6 col-xs-6 col-sm-8 col-md-8");
							$('#dataTables-CustInfoSamNew_paginate').parent().css("padding-right","15px");
							$('.form-inline .form-control').css("color","#004c74");
							$('.form-inline .form-control').css("border","1px solid #004c74");
							$('select .form-control.input-sm').css("border","1px solid #004c74");
							$("#dataTables-CustInfoSamNew_length").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6");
							$("#dataTables-CustInfoSamNew_filter").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6 col-sm-7");
							$("#dataTables-CustInfoSamNew_filter").children().css("width","100%")
							 $('#dataTables-CustInfoSamNew').removeClass('display').addClass('table table-striped table-bordered');
							 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Company Name OR Customer Number').css({'width':'200px','display':'inline-block', 'font-weight':'100'});
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
							   var selectedPlay=$( "#playSec").val();
							   var filt= $("#filterBy").val();
		     		           disabledSubPlays(selectedPlay);     		          
							 }
							 $("#subPlayId").next().children().attr("title","click to see more info.");
						},																										
						

						"oLanguage": {"sProcessing": '<div id="example_processing" class="dataTables_processing" style="height: 48px;color: darkgray;position: relative;border: 1px solid;border-color: gainsboro;width: 250px;padding: 10px;margin-top: 80px;margin-left: -150px;background-color: gainsboro;"><img width="40" height="30" src="./resources/img/loading1.gif"> Loading...</div>',"sSearch":"Filter :"}

					}).fnDraw();
					$('#tierIdSam').on("click", function(event){
						url='./resources/docs/Member Rewards Benefits.pdf#zoom=70,0,0&scrollbar=1';
							 var win = window.open(url, '_blank');
							  win.focus();
						event.stopImmediatePropagation();
					 });
					$('#reasonCodeIdSam').on("click", function(event){
						url='./resources/docs/Reason Code.pdf#zoom=70,0,0&scrollbar=1';
							 var win = window.open(url, '_blank');
							  win.focus();
						event.stopImmediatePropagation();
					 });
				}
		 });
		 accTypeFlag=false;
		 onChangeAccType=true;
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
function saveToTaskTracker(custNum){
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
					performSaveTotaskTracker(custNum,data.CLD_DT,data.FSC_YR,data.FSC_PRD,data.FSC_WK,data.FSC_DY);
				}
				
			}
		}
	});
}
function performSaveTotaskTracker(custNum,calDate,fsc_yr,fsc_prd,fsc_wk,fsc_dy){
	var repId = $('#repId').val();
	var repRoleCode = $('#repRoleCode').val();
	var customerNumber = $('#customerNumber').val();
	var loggedUName=$("#loggedInUserName").val();
	var loggedUId=$("#LoggedInUserID").val();

	var fiscalDate=fsc_yr+'-'+fsc_prd+'-'+fsc_wk+'-'+fsc_dy;
	var formData = {
			repId:repId,
			repRoleCode:repRoleCode,
			customerNumber:custNum,
			cldDate:(undefined!=calDate?calDate.toUpperCase():''),
			fiscalContactedDate:fiscalDate,
			loggedInUserName:loggedUName,
			loggedInUserId:loggedUId
			};
	$.ajax({
		url : ctx+"/putUserContactActivity",
		type : "POST",
		cache : false,
		async:false,
		data : formData,
		dataType : "text",
		timeout : 1000000,
		async : false,
		success : function(data, textStatus, jqXHR) {
			var filterBy= $('#filterBy').val();
			 
			 var subplayText=$('#subPlayId').next().children(":first").children(":first").html();
			 if(undefined !=subplayText  && subplayText !='' && subplayText.indexOf('All selected')!=-1){
				 $(".multiselect-selected-text").html("ALL");
				 statusSave='N';
			     populateDataOnLoad("ALL","onload");
			 }
		     else{
		    	 statusSave='Y';
		    	 playChange();
		     }
		}
	});
}

function populateSAMToDoList(selPlayType,from){
	var selectedCustSegIds="";
	var selectedQualScoreIds= ""; 
	var formData={};
	var custid=$("#accId").val();
	selectedSubPlays='All';
	if((undefined ==selectedSubPlaysArr || selectedSubPlaysArr ==null || selectedSubPlaysArr =='' || selectedSubPlaysArr.length ==0)&&((undefined != $("#from").val()) && ('' != $("#from").val()) && ('DashBoard' == $("#from").val()) && ('' != $("#subPlayParams").val()))){
		var subPlaysParam= $("#subPlayParams").val();
		if(subPlaysParam.indexOf("~")!=-1){
			selectedSubPlaysArr=subPlaysParam.split("~");
			deselectValArr=subPlaysParam.split("~");
		}else if('' != subPlaysParam){
			selectedSubPlaysArr[0]=subPlaysParam;
			deselectValArr[0]=subPlaysParam;
	    }
	}
	
		 if(undefined !=selectedSubPlaysArr && selectedSubPlaysArr !=null && selectedSubPlaysArr !='' && (from=='subPlays' || from=='plays' || 'DashBoard' == $("#from").val()) ){
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
       if(from=='subPlays' && ((undefined !=selectedSubPlaysArr && selectedSubPlaysArr.length==0) || (undefined !=selectedSubPlaysArr && undefined ==selectedSubPlaysArr.length))){
       	selectedSubPlays="All";
       }
		 if( ( selPlayType=='null' || selPlayType==null || selPlayType=='None selected' ) && (selectedSubPlays != 'All')){  
	     selPlayType='none';
		 }
		 
		 if(("onload" ==from)) {
				
			}
		 
		 var count=1;
		 var agentEmailId='';
		 var flag='';
		 var test="k";
		 var repRoleCode=$("#repRoleCode").val();
		 var accType=$("#accTypeVal").html();
		 if(onChangeAccType==false)
			 repRoleCode="";
	     if($("#accTypeId").is(":visible")==false)
		 accType='NA';

		
		 var tab=$('#dataTables-CustInfoSamNew').dataTable( {
				
				"bDestroy" : true,
			       "sAjaxSource": ctx+"/sales/representive/"+custid+"/customer/list1",
			       "fnServerParams": function (aoData) { 
			    	   aoData.push({ "name": "selectedSubPlays", "value": selectedSubPlays}); 
			    	   aoData.push({ "name": "selectedQualScore", "value": ""});
			    	   aoData.push({ "name": "selectedSegScore", "value": ""});
			    	   aoData.push({ "name": "repRoleCode", "value": repRoleCode});
			    	   aoData.push({ "name": "accType", "value": accType});
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
									$(".col-lg-12").css("height","35px");
									
									
								} 
							}
							$("#dataTables-CustInfoSamNew td").css("WORD_BREAK","BREAK-ALL");
							$('#dataTables-CustInfoSamNew_length label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_length label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_length label').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_filter label').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_filter label').css("text-align","right");
							$('#dataTables-CustInfoSamNew_filter label').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_info').css("color","#004c74");
							$('#dataTables-CustInfoSamNew_info').css("font-weight","600");
							$('#dataTables-CustInfoSamNew_info').parent().addClass("col-lg-6 col-xs-6 col-sm-4 col-md-4");
							$('#dataTables-CustInfoSamNew_info').parent().css("padding-left","15px");
							$('#dataTables-CustInfoSamNew_paginate').parent().removeClass("col-sm-6");
							$('#dataTables-CustInfoSamNew_paginate').parent().addClass("col-lg-6 col-xs-6 col-sm-8 col-md-8");
							$('#dataTables-CustInfoSamNew_paginate').parent().css("padding-right","15px");
							$('.form-inline .form-control').css("color","#004c74");
							$('.form-inline .form-control').css("border","1px solid #004c74");
							$('select .form-control.input-sm').css("border","1px solid #004c74");
							$("#dataTables-CustInfoSamNew_length").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6");
							$("#dataTables-CustInfoSamNew_filter").parent().removeClass("col-sm-6 col-xs-6").addClass("col-lg-6 col-xs-5 col-md-6 col-sm-7");
							$("#dataTables-CustInfoSamNew_filter").children().css("width","100%")
							 $('#dataTables-CustInfoSamNew').removeClass('display').addClass('table table-striped table-bordered');
							 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Company Name OR Customer Number').css({'width':'200px','display':'inline-block', 'font-weight':'100'});
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
							   var selectedPlay=$( "#playSec").val();
							   var filt= $("#filterBy").val();
		     		           disabledSubPlays(selectedPlay);     		          
							 }
							 $("#subPlayId").next().children().attr("title","click to see more info.");
							
						},																										
						
						"aoColumns" : [
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												var html='<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+'<a href="javascript:openCustDetails(\''+data.customerNo+'\',\''+data.customerNo+'\',\'AM-SAMNew\');" style="text-decoration:none !important;font-size: 14px;color: rgb(255, 15, 0);font-weight: 600;font-family: arialmt;opacity:0.7;letter-spacing:.4px;">'
														
															+ checkUundefinedNullBlankZero(data.customerNo)
														
													+'</a>'
												+'</span>'
													return html;
													
											 }
										},
										
										{
											 "mData": null,
											 "mRender" : function(
													 data, type, full) {
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													
													+ checkUundefined(data.tier)+'</span>' ; 
													
											 }
											
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;line-height:1.8;">' 
													
													+checkUundefined(data.account_rank)+'</span>' ;
											 }
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(data.companyName)+'</span>';
													
											 }
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(data.callReason )+'</span>';
													
											 }
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(data.category)+'</span>';
													
											 }
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												    var lastConDate = checkUundefined(data.last_contacted_date);
													if(lastConDate.length > 0){
														lastConDate = lastConDate.substring(0,10);
														var lastConDateArr = lastConDate.split("-");
														lastConDate = lastConDateArr[1] + "/" + lastConDateArr[2] + "/" + lastConDateArr[0];
													}
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(lastConDate)+'</span>';
													
											 }
										},
										
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(data.timeZone)+'</span>';
													
											 }
										},
										{
											"mData": null,
											 "mRender" : function(
													 data, type, full) {
												    var name = checkUundefined(data.firstName+ " "+ data.lastName);
													var email = checkUundefined(data.contactEmailId);
													var phone = checkUundefined(data.phone);
													var timezone=checkUundefined(data.timeZone);
													var customerContact = generateContactInfoSAM(name, email, phone,timezone,data.customerNo);
													return '<span style="color: #444 !important;font-size: 13px !important;font-family: ARIALMT;">'
													+checkUundefined(customerContact)+'</span>';
													
											 }
										}
										],
										
										
						"oLanguage": {"sProcessing": '<div id="example_processing_sam" class="dataTables_processing" style="height: 48px;position: relative;width: 10%;padding: 10px;margin-left: -80px;border: none;margin-top: 0%;"><img width="40" height="40" src="./resources/img/helios_loader.gif" style="border: 3px solid darkturquoise;border-radius: 100px !important;"></div>',"sSearch":"Filter :",
							"sEmptyTable":     "No results found or this account is currently part of Helios Holdout Test Group."	
						}

					});
					$('#tierIdSam').on("click", function(event){
						url='./resources/docs/Member Rewards Benefits.pdf#zoom=70,0,0&scrollbar=1';
							 var win = window.open(url, '_blank');
							  win.focus();
						event.stopImmediatePropagation();
					 });
					$('#reasonCodeIdSam').on("click", function(event){
						url='./resources/docs/Reason Code.pdf#zoom=70,0,0&scrollbar=1';
							 var win = window.open(url, '_blank');
							  win.focus();
						event.stopImmediatePropagation();
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
function removeLeadingZeros(date){ 
	if(undefined!=date && ''!=date && date.indexOf('0')!=-1)
	 return date.replace(/\b0/g, '');
	else 
	 return date;
}