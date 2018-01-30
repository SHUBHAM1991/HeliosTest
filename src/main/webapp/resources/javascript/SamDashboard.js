/**
 * Lomesh Changes
 */
var ctx = $("#svsURL").val();
var abandonedCartListVOArr = new Array();
var custRecommendationListVOArr = new Array();
var dotcomActivityVOArr = new Array();
var objPurchaseDetailsListVO = new Array();
var searchItemsListVOArr = new Array();
var abandonedStatus = 0;
var recommStatus = 0;
var dotcomStatus = 0;
var pie;
var yearChange = 0;
var latestDateforOrder = null;
var latestFiscalDateOrderSAMNew = null;
var latestDateChart = null;
var latestDate = '';
var playSecData = '';
var sidebarClick = false;
var SuperUserDeatilCount = -1;
var OrderDeatilCount = -1;
var ShipOrderDeatilCount = -1;
var ShipToDeatilCount = -1;
var skuDeatilCount = -1;
var iStart = 0;
var retFound = 'N';
var ActiveUserDeatilCount = -1;
var pieChartDataSet;
$.fn.dataTable.ext.errMode = 'none';
var latestFiscalYearSam = ''
var chrt = '';
var quickSearchStart = 0;

$(document)
		.ready(
				function() {

					var height = ($(window).height() - ($(window).height() / 10));
					$(".msg_container_base").css("max-height", height);
					$('#ulIdOther').css('display', 'none');
					$('#actionStat').css('display', 'none');
					$('#ulPlaySec').css('display', 'none');

					jQuery.fn.dataTable.Api.register('sum()', function() {
						return this.flatten().reduce(function(a, b) {
							if (typeof a === 'string') {
								a = a.replace(/[^\d.-]/g, '') * 1;
							}
							if (typeof b === 'string') {
								b = b.replace(/[^\d.-]/g, '') * 1;
							}

							return a + b;
						}, 0);
					});

					$("div.bhoechie-tab-menu>div.list-group>a").click(
							function(e) {
								e.preventDefault();
								$(this).siblings('a.active').removeClass(
										"active");
								$(this).addClass("active");
								var index = $(this).index();
								$("div.bhoechie-tab>div.bhoechie-tab-content")
										.removeClass("active");
								$("div.bhoechie-tab>div.bhoechie-tab-content")
										.eq(index).addClass("active");
							});

					
					configureCalender();
					var cval = $.cookie('sidebar_closed');
					$("#loggedInUserNameSpan").html(
							"You logged in as " + $("#loggedInUserName").val());

					if ($(window).width() <= 768) {
						$("#loggedInUserNameSpan")
								.attr(
										"style",
										"text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;padding-right: 2%;");
					} else {
						$("#loggedInUserNameSpan")
								.attr(
										"style",
										"text-align: end;font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:4px;");
					}
					$(window).resize(
							function() {
								if (undefined != pieChartDataSet
										&& '' != pieChartDataSet) {
									createPieChartData(pieChartDataSet);
								}
							});

					if (undefined != cval && '' != cval && cval == 1
							&& $(window).width() > 768) {
						$("#stplId").html("");

						$('#ulMenu li').css("padding-left", "0px");
						$('a[id^=menuItemIcon]').css("display", "block");
						$('a[id^=menuItemId]').css("display", "none");
						$("#cnoHead").removeClass("cnoExpand");
						$("#cnoHead").addClass("cnoCollapse");
						$("#logHead").removeClass("logExpand");
						$("#logHead").addClass("logCollapse");
						$('#ulPlaySec').css("display", "none");
						$('#sfdcId').css("display", "none");
						$('#otherId').css("display", "none");
						$('#wirDivId').css("display", "none");
						$("#tglId").css("width", "100%");
						$("#stplLogoId").css("display", "none");
						collapsed = 'true';
					}
					if (undefined != $("#iStart").val()
							&& $("#iStart").val() != null
							&& $("#iStart").val() != '') {
						iStart = $("#iStart").val();
					}
					$("#applyCallToAction").click(function() {
						var rationaleHtml = $(".Editor-editor").html();
						applySubCallToAction(rationaleHtml);
						$(".Editor-editor").html("");
					});

					$("#ratEditId").click(function() {
						var rationaleHtmlData = $("#rationaleData").html();
						$(".Editor-editor").html("");
						$(".Editor-editor").html(rationaleHtmlData);
						showEditPopUp('rationale-info-edit');
					});
					$("#expEditId").click(function() {
						var expHtmlData = $("#expData").html();
						$(".Editor-editor").html("");
						$(".Editor-editor").html(expHtmlData);
						showEditPopUp('exp-info-edit');
					});
					$("#expObjEditId").click(function() {
						var expObjHtmlData = $("#expObjData").html();
						$(".Editor-editor").html("");
						$(".Editor-editor").html(expObjHtmlData);
						showEditPopUp('expObj-info-edit');
					});
					$("#applyOnEdit").click(
							function() {
								var rationaleDataAfterEdit = $("#ratTxtEditor")
										.next().find(".Editor-editor").html();
								$("#rationaleData").html("");
								$("#rationaleData")
										.html(rationaleDataAfterEdit);
							});
					$("#expApplyOnEdit").click(
							function() {
								var expDataAfterEdit = $("#expTxtEditor")
										.next().find(".Editor-editor").html();
								$("#expData").html("");
								$("#expData").html(expDataAfterEdit);
							});
					$("#expObjApplyOnEdit").click(
							function() {
								var expObjDataAfterEdit = $("#expObjTxtEditor")
										.next().find(".Editor-editor").html();
								$("#expObjData").html("");
								$("#expObjData").html(expObjDataAfterEdit);
							});

					$(".active").click(function(e) {
						e.stopPropagation();

					});
					$("body").click(
							function() {
								var status = $(".page-quick-sidebar-toggler")
										.css("display");
								if ((undefined != status)
										&& (status == 'block')
										&& (sidebarClick == true)) {
									$('body').toggleClass(
											'page-quick-sidebar-open');
									sideBarClick = false;
								}
							});
					$('#dataTables-QuickSearch').on('preXhr.dt',
							function(e, settings, data) {
							});
					$('#dataTables-QuickSearch')
							.on(
									'error.dt',
									function(e, settings, techNote, message) {

										if (!(settings.jqXHR.getResponseHeader(
												'Content-Type').indexOf(
												'text/html') != -1)) {
											console
													.log(
															'An error has been reported by DataTables: ',
															message);
										} else {
											console.log('Session Logged Out');
										}

									}).DataTable();
					$("#sfdcYesId")
							.click(
									function() {
										var sfdcUrl = $("#sfdcConfUrl").val();
										if (undefined != sfdcUrl
												&& '' != sfdcUrl) {
											logUserActivityDotCom(7033,
													'Pre-populated User information to SFDC');
											openUrlWithEmail(sfdcUrl);
										}
									});
					$('#super-info-dialog').on('hidden.bs.modal', function() {

					})
					$("#growthId")
							.click(
									function() {

										var Gclass = $("#growthId").attr(
												"class");
										if (undefined != Gclass && '' != Gclass
												&& 'collapsed' == Gclass) {
											$("#gIconId").attr("class",
													"fa fa-chevron-up");
											logUserActivityDotCom(7027,
													'User has clicked on Call Order Growth List');
										} else if (undefined != Gclass
												&& '' == Gclass) {
											$("#gIconId").attr("class",
													"fa fa-chevron-down")
										}
									});
					$("#retentionId")
							.click(
									function() {
										var Gclass = $("#retentionId").attr(
												"class");
										if (undefined != Gclass && '' != Gclass
												&& 'collapsed' == Gclass) {
											$("#rIconId").attr("class",
													"fa fa-chevron-up");
											logUserActivityDotCom(7028,
													'User has clicked on Call Order Retention List');
										} else if (undefined != Gclass
												&& '' == Gclass) {
											$("#rIconId").attr("class",
													"fa fa-chevron-down")
										}
									});
					$("#expansionId")
							.click(
									function() {
										var Gclass = $("#expansionId").attr(
												"class");
										if (undefined != Gclass && '' != Gclass
												&& 'collapsed' == Gclass) {
											$("#eIconId").attr("class",
													"fa fa-chevron-up");
											logUserActivityDotCom(7029,
													'User has clicked on Call Order Expansion List');
										} else if (undefined != Gclass
												&& '' == Gclass) {
											$("#eIconId").attr("class",
													"fa fa-chevron-down")
										}
									})
					jQuery.fn.dataTableExt.oSort['mystring-asc'] = function(x,
							y) {
						var retVal;
						x = $.trim(x);
						y = $.trim(y);

						if (x == y)
							retVal = 0;
						else if (x == "" || x == "&nbsp;" || x == "-")
							retVal = 1;
						else if (y == "" || y == "&nbsp;" || y == "-")
							retVal = -1;
						else if (x > y)
							retVal = 1;
						else
							retVal = -1; 

						return retVal;
					}
					jQuery.fn.dataTableExt.oSort['mystring-desc'] = function(y,
							x) {
						var retVal;
						x = $.trim(x);
						y = $.trim(y);

						if (x == y)
							retVal = 0;
						else if (x == "" || x == "&nbsp;" || x == "-")
							retVal = -1;
						else if (y == "" || y == "&nbsp;" || y == "-")
							retVal = 1;
						else if (x > y)
							retVal = 1;
						else
							retVal = -1; 

						return retVal;
					}

					$('#dataTables-example1').DataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"bSort" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"lengthMenu" : [ [ 15, 25, 50, -1 ],
										[ 15, 25, 50, "All" ] ],
								"scrollX" : true,
								"paging" : false,
								"searching" : false,
								"info" : false,
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : false
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]
							});
					/* Sam-Month-Analysis Start */
					$('#sam-dataTables-example1').DataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"bSort" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"lengthMenu" : [ [ 15, 25, 50, -1 ],
										[ 15, 25, 50, "All" ] ],
								"scrollX" : true,
								"paging" : false,
								"searching" : false,
								"info" : false,
								"order" : [],
								"bProcessing" : true,
								"aoColumns" : [ {
									"bSortable" : false
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]
							});
					/* Sam-Month-Analysis Ends */
					getLatestFiscalDate();
					getLatestFiscalDateOrder();
					getLatestFiscalDateOrderSAMNew();
					$("#yrId").change(
							function() {
								yearChange = 1;
								populateDataOnLoad(latestDate, 'RETAIL');
								populateCategoryChart();
								monthAnalysisForSam();
								var year = $('#yrId').val();
								if (undefined != year && '' != year) {
									logUserActivityDotCom(7036,
											'User has viewed customer dashboard for the year of '
													+ year + '');
								}

							});
					$("#sortSel").change(function() {
						var monthYear = $("#yearSel").val();
						if (undefined == monthYear) {
							var datestr = $("#datepickerTEXT").val();
							if (checkUundefined(datestr).length > 0) {
								monYearArr = datestr.split("/");
								year = monYearArr[1].trim();
								month = monYearArr[0].trim();
								if (month.length == 3) {
									var map = {
										"Jan" : "01",
										"Feb" : "02",
										"Mar" : "03",
										"Apr" : "04",
										"May" : "05",
										"Jun" : "06",
										"Jul" : "07",
										"Aug" : "08",
										"Sep" : "09",
										"Oct" : "10",
										"Nov" : "11",
										"Dec" : "12",
										"ALL" : "0"
									};
									month = map[month];
								}
								monthYear = month + year;
							}

						}
						var catId = $("#sortSel").val();
						onChangeMonthOrCat(monthYear, catId);
					});
					$("#yearSel").change(function() {
						var monthYear = $("#yearSel").val();
						var catId = $("#sortSel").val();
						onChangeMonthOrCat(monthYear, catId);

					});
					$("#lastXDaysSel")
							.change(
									function() {

										var catId = $("#sortSel").val();
										var date = new Date();
										var dateString = date.toISOString()
												.split('T')[0];
										var year = dateString.substring(0, 4);

										if ($("#lastXDaysSel :selected").val() == "0") {
											$('#datepickerTEXT').val(
													"ALL / " + year);
											onChangeMonthOrCat("0" + year,
													catId, 'N');
										} else {
											$('#datepickerTEXT')
													.val(
															$(
																	"#lastXDaysSel :selected")
																	.text());
											onChangeMonthOrCat($('#undefined')
													.val(), catId, 'N');
										}
									});
					$('#dataTables-example').dataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"bSort" : true,
								"lengthMenu" : [ [ 5, 15, 25, -1 ],
										[ 5, 15, 25, "All" ] ],
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : false
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}

								]
							});
					$('#dataTables-rewards').dataTable({
						"dom" : '<"pull-right"f>rt<"bottom"ip>',
						"lengthMenu" : [ 5, 10, 15 ],
						"bSort" : true,
						"order" : [],
						"bProcessing" : true,
						"oLanguage" : {
							"sSearch" : "Filter: "
						},
						"searching" : true,
						"aoColumns" : [ {
							"bSortable" : false
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						}, {
							"bSortable" : true
						} ]
					});
					$('#dataTables-order').dataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"lengthMenu" : [ [ 5, 10, 30, -1 ],
										[ 5, 10, 30, "All" ] ],
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								},

								{
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]
							});
					$('#dataTables-superInfo').DataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"lengthMenu" : [ [ 4, 10, 15, -1 ],
										[ 4, 10, 15, "All" ] ],
								"jqueryUI" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : true,
									"sWidth" : '33%',
									"sType" : "mystring"
								}, {
									"bSortable" : true,
									"sWidth" : '33%',
									"sType" : "mystring"
								}, {
									"bSortable" : true,
									"sWidth" : '33%',
									"sType" : "mystring"
								}

								]
							})
					$('#dataTables-OrderInfo').DataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"lengthMenu" : [ [ 5, 10, 15, -1 ],
										[ 5, 10, 15, "All" ] ],
								"jqueryUI" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}

								]

							})
					$('#dataTables-exampleShipTo').dataTable(
							{
								"dom" : '<"pull-right"f>rt<"bottom"ip>',
								"bSort" : true,
								"lengthMenu" : [ [ 15, 25, 50, -1 ],
										[ 15, 25, 50, "All" ] ],
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]
							})
					$('#scrollTop').click(function() {
						$('html, body').animate({
							scrollTop : $($.attr(this, 'href')).offset().top
						}, 1200);
						return false;
					});
					$("#backButton").click(function() {
					});

					populateQuickSearchData();

					$("#ratId").click(
							function() {
								var status = $(this).attr("aria-expanded");
								if (undefined != status && '' != status
										&& status == 'false') {
									$("#ratId i").attr("class", "fa fa-minus");
								} else if (undefined != status && '' != status
										&& status == 'true') {
									$("#ratId i").attr("class", "fa fa-plus");
								}
							});
					$("#expForVal").click(
							function() {
								var status = $(this).attr("aria-expanded");
								if (undefined != status && '' != status
										&& status == 'false') {
									$("#expForVal i").attr("class",
											"fa fa-minus");
								} else if (undefined != status && '' != status
										&& status == 'true') {
									$("#expForVal i").attr("class",
											"fa fa-plus");
								}
							});
					$("#expAndExp").click(
							function() {
								var status = $(this).attr("aria-expanded");
								if (undefined != status && '' != status
										&& status == 'false') {
									$("#expAndExp i").attr("class",
											"fa fa-minus");
								} else if (undefined != status && '' != status
										&& status == 'true') {
									$("#expAndExp i").attr("class",
											"fa fa-plus");
								}
							});
					$("#drillId")
							.click(
									function() {
										var status = $(this).attr(
												"aria-expanded");
										if (undefined != status && '' != status
												&& status == 'false') {
											$("#drillId i").attr("class",
													"fa fa-minus");
										} else if (undefined != status
												&& '' != status
												&& status == 'true') {
											$("#drillId i").attr("class",
													"fa fa-plus");
										}
									});
					$("#propId")
							.click(
									function() {
										var status = $(this).attr(
												"aria-expanded");
										if (undefined != status && '' != status
												&& status == 'false') {
											$("#propId i").attr("class",
													"fa fa-minus");
										} else if (undefined != status
												&& '' != status
												&& status == 'true') {
											$("#propId i").attr("class",
													"fa fa-plus");
										}
									});
					$("#marketId").click(
							function() {
								var status = $(this).attr("aria-expanded");
								if (undefined != status && '' != status
										&& status == 'false') {
									$("#marketId i").attr("class",
											"fa fa-minus");
								} else if (undefined != status && '' != status
										&& status == 'true') {
									$("#marketId i")
											.attr("class", "fa fa-plus");
								}
							});
					$("#miscId")
							.click(
									function() {
										var status = $(this).attr(
												"aria-expanded");
										if (undefined != status && '' != status
												&& status == 'false') {
											$("#miscId i").attr("class",
													"fa fa-minus");
										} else if (undefined != status
												&& '' != status
												&& status == 'true') {
											$("#miscId i").attr("class",
													"fa fa-plus");
										}
									});

					$("ul#statusList li")
							.click(
									function() {
										var id = $(this).attr("id");
										if (id == "ntStart") {
											$("#statusVal").html("Not Started");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "opp") {
											$("#statusVal").html(
													"Create Opportunity");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "comp") {
											$("#statusVal").html(
													"Task Completed");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "compEmail") {
											$("#statusVal").html(
													"Task Completed - Email");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "closeCall") {
											$("#statusVal")
													.html(
															"Task Completed -/Close Call Rotation");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "welCall") {
											$("#statusVal").html(
													"Welcome Call in Progress");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "leftMsg") {
											$("#statusVal")
													.html(
															"Left Message with Customer");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "liveCon") {
											$("#statusVal")
													.html(
															"Task Completed - Live Contact");
											$("#AmtLiId")
													.css("display", "none");
										} else if (id == "fTof") {
											$("#statusVal")
													.html(
															"Task Completed - Face-to-Face");
											$("#AmtLiId")
													.css("display", "none");
										}

									});

					populateCustomerProfile();
					getCategorySavingsData();
					getRewardsstatements();
					getHawkeyeDetails();

					Date.dateDiff = function(datepart, fromdate, todate) {
						datepart = datepart.toLowerCase();
						var diff = todate - fromdate;
						var divideBy = {
							w : 604800000,
							d : 86400000,
							h : 3600000,
							n : 60000,
							s : 1000
						};

						return Math.floor(diff / divideBy[datepart]);
					}
				});

$(function() {
	$("[data-toggle='tooltip']").tooltip({
		html : true
	});
});
function populateDataOnLoad(latestDate, users) {

	if (latestDate != null && latestDate != undefined && yearChange == 0) {
		if (checkUundefined(latestDate.fiscalYears)) {
			$("#yrId option[value=" + latestDate.fiscalYears[0] + "]").attr(
					"selected", true);
		}
	}
	populateCategoryChart();
	var formData = {};
	var custid = $("#reqCustNum").val();
	var year = $("#yrId").val();
	$("#monthTitle").html("");
	$("#monthTitle")
			.html(
					'<span> '
							+ year
							+ ' Monthly Spend Analysis <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
	$.ajax({
		dataType : "json",
		url : ctx + "/getCustData/" + custid + "/" + year + "/" + users,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {

			if (latestDate != null && latestDate != undefined
					&& yearChange == 0) {
				if (checkUundefined(latestDate.fiscalYears)) {
					$("#yrId option[value=" + latestDate.fiscalYears[0] + "]")
							.attr("selected", true);
				}

			}
			if (latestDate != null && latestDate != undefined) {
				if (checkUundefined(latestDate.fiscalYears)) {
					latestFiscalYear = latestDate.fiscalYears[0];
				}
			}
		}

	})

}
function generateSecondYearChartData(firstYearData, selYear, latestFiscalYear) {
	var secondYear = selYear - 1;
	var thirdYear = secondYear - 1;
	var years = new Array();
	if (selYear == latestFiscalYear) {
		secondYear = selYear - 1;
		thirdYear = secondYear - 1;

	} else if (selYear == (latestFiscalYear - 1)) {
		secondYear = latestFiscalYear;
		thirdYear = secondYear - 2;
	} else if (selYear == (latestFiscalYear - 2)) {
		secondYear = latestFiscalYear - 1;
		thirdYear = latestFiscalYear;
	}
	years = [ selYear, secondYear, thirdYear ];
	var formData = {};
	var custid = $("#reqCustNum").val();
	var year = secondYear;
	$.ajax({
		dataType : "json",
		url : ctx + "/getOtherYearData/" + custid + "/" + year,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			generateThirdYearChartData(firstYearData, data, years)
		}
	});

}
function generateThirdYearChartData(firstYearData, secondYearData, years) {
	var formData = {};
	var custid = $("#reqCustNum").val();
	var year = years[2];
	$.ajax({
		dataType : "json",
		url : ctx + "/getOtherYearData/" + custid + "/" + year,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var thirdYearData = data;

			populateMonthChartData(firstYearData, secondYearData,
					thirdYearData, years);
		}
	});
}
function checkUundefined(val) {
	if (undefined != val && val != 'null' && null != val) {
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
function populateCategoryChart() {

	var custid = $("#reqCustNum").val();
	$("#chartContainerPie").css("padding-top", "0%");
	$("#chartContainerPie").css("font-weight", "500");
	$("#chartContainerPie").css("font-family", "Helvetica");
	$("#chartContainerPie").css("font-size", "13px");
	$("#chartContainerPie").css("color", "#555");
	$("#chartContainerPie").html("");
	$("#catTitle").html("");
	var yr = $("#yrId").val();

	$("#catTitle")
			.html(
					'<span class="letterSpace" style="font-size: 16px;font-weight: 600;color: #555;">'
							+ checkUundefined(yr)
							+ ' YEARLY SPEND PER CATEGORY <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');

	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid
						+ "/fiscalyear/categories/percetage?year=" + yr,
				type : "GET",
				data : "",
				timeout : 1000000,
				async : true,
				success : function(data, textStatus, jqXHR) {

					if (data != null) {
						var keyArr = new Array();
						var valArr = new Array();
						var valPercentArr = new Array();
						var retailPercentArr = new Array();
						var dotcomPercentArr = new Array();
						var piedata = [];

						var countIndex = 0;
						$
								.each(
										data.categories,
										function(i, item) {

											var catName = checkUundefined(item.name);
											var catPercentage = checkUundefined(item.categoryPercentage);
											var retailPercentage = checkUundefined(item.retailCategoryPercentage);
											var dotcomPercentage = checkUundefined(item.dotComCategoryPercentage);

											if (catPercentage != 0
													&& catPercentage != '0') {
												keyArr.push(catName);
												valArr.push(catPercentage);
												valPercentArr
														.push(catPercentage);
												retailPercentArr
														.push(retailPercentage);
												dotcomPercentArr
														.push(dotcomPercentage);
											}

										});
						if (keyArr.length > 0) {
							piedata = getLableValueMap(keyArr, valArr,
									valPercentArr, retailPercentArr,
									dotcomPercentArr);
							createPieChartData(piedata);
						} else {
							$("#chartContainerPie").html(
									"NO SALES FOUND FOR THIS YEAR")
							$("#chartContainerPie").css("text-align", "center");
							$("#chartContainerPie").css("padding-top", "40%");
							$("#chartContainerPie").css("font-weight", "700");
							$("#chartContainerPie").css("font-size", "20px");
							$("#chartContainerPie").css("color", "darkgrey");
						}

					} else {
						$("#chartContainerPie").html(
								"NO SALES FOUND FOR THIS YEAR")
						$("#chartContainerPie").css("text-align", "center");
						$("#chartContainerPie").css("padding-top", "40%");
						$("#chartContainerPie").css("font-weight", "700");
						$("#chartContainerPie").css("font-size", "20px");
						$("#chartContainerPie").css("color", "darkgrey");
					}
				}

			})

}
var myLabelFormatter = function(context) {
	var label = context.label;
	if (context.section === 'outer') {
		if (context.value === 1) {
			label = label + '!';
		}
	}
	return label;
};

function createPieChartData(pieData) {
	$("#chartContainerPie").html("");
	var chart1 = new CanvasJS.Chart("chartContainerPie", {
		title : {
			theme : "theme2"
		},
		legend : {
			fontFamily : "Helvetica",
			fontSize : 13,
			fontWeight : "bolder",
			fontColor : "#555",
			maxWidth : 400,
			itemWidth : 188
		},
		toolTip : {
			fontSize : 13,
			fontStyle : "normal",
			fontFamily : "Helvetica",
			fontSize : 13,
			fontWeight : "bolder",
			fontColor : "#555",
			content : "{seriesText}"
		},
		data : [ {

			type : "pie",
			showInLegend : true,
			legendText : "{legendText}",
			legendMarkerType : "square",

			dataPoints : pieData
		} ]
	});
	chart1.render();
	chrt = chart1;

	$("a.canvasjs-chart-credit").css("display", "none");
	pieChartDataSet = pieData;
}
function createPieChart(piedata) {
	pie = new d3pie("pie", {
		header : {},
		size : {
			pieOuterRadius : "78%",
			canvasHeight : 285
		},
		labels : {
			formatter : myLabelFormatter,
			fontFamily : "Helvetica",

			inner : {
				format : "percentage"
			},
			value : {
				color : "#fff"
			}
		},
		data : {
			sortOrder : "value-asc",

			content : piedata
		},
		tooltips : {
			enabled : true,
			type : "placeholder",
			string : "{label}, about = {percentage}%",
			styles : {
				fadeInSpeed : 500,

				backgroundColor : "yellow",
				backgroundOpacity : 0.8,
				color : "#ffffcc",
				borderRadius : 4,
				font : "verdana"

			}
		},
		misc : {
			colors : {
				segments : [ "#ffc127", "#10acaf", "#943e96", "#63d618",
						"#d8e523", "#d9524f" ]
			}
		}
	});

}

function getLableValueMap(keyArr, valArr, valPercentArr, valPercentArrRetail,
		valPercentArrOnline) {

	var labels = keyArr;
	var values = valArr;
	var valuesPercent = valPercentArr;
	var colorsArr = [ '#52C5D0', '#FF9A2C', '#FCD78D', '#9CE2CF', '#2D435E',
			' #DD1D1D', '#FFC127', '#F96919', '#159B9B', '#13706D' ];
	var count = -1;
	return labels
			.map(function(label) {
				var labelcolor = getColor(label.trim());
				count++;
				if (undefined != label && label.indexOf("and") != -1)
					label = label.replace("and", "&");
				return {
					y : values[count],
					seriesText : ""
							+ label
							+ "  "
							+ (parseFloat(valuesPercent[count]).toFixed(2))
							+ "%<div style='padding-top:6px;color:red;border-top:1px dashed #ccc;'>Retail: "
							+ (parseFloat(valPercentArrRetail[count])
									.toFixed(2))
							+ "%</div><div style='padding-top:0px;color:red;'>Staples.com: "
							+ (parseFloat(valPercentArrOnline[count])
									.toFixed(2)) + "%</div>",
					legendText : "  - " + label.trim() + "  "
							+ (parseFloat(valuesPercent[count]).toFixed(2))
							+ "%",
					indexLable : false,
					color : labelcolor
				}
			});
}
function getColor(label) {
	var labelcolormap = {
		"Facilities" : "#2D435E",
		"Office Supplies" : "#DD1D1D",
		"Toner" : "#52C5D0",
		"Paper" : "#FF9A2C",
		"Tech" : "#FCD78D",
		"Furniture" : "#9CE2CF",
		"Mail and Ship" : "#C8CBD0",
		"Print" : "#417505",
		"Promo" : "darkseagreen",
		"All Other Products" : "#5F9EA0"
	};
	var color = labelcolormap[label];
	if (undefined != color)
		return color;
	else
		return '#F96919';
}
function getMonthAmountMap(monthArr, amntArr) {

	var labels = amntArr;
	var values = monthArr;
	var count = -1;
	return labels.map(function(amt) {
		count++;
		return {
			y : parseFloat(amt),
			label : values[count]
		}
	});
}
function getMonthAmountMap1(monthArr, amntArr) {
	var monthArr = [ "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug",
			"Sept", "Oct", "Nov", "Dec" ];
	var colorArr = [ '#FF0F00', '#FF9E01', '#0D8ECF', '#FCD202', '#CD0D74',
			'#754DEB', '#04D215', '#0D8ECF', '#2A0CD0', '#8A0CCF', '#CD0D74',
			'#754DEB' ];
	var labels = amntArr;
	var values = monthArr;
	var count = -1;
	return labels.map(function(amt) {

		count++;
		return {
			"month" : monthArr[count],
			"amount" : parseFloat(amt),
			"color" : colorArr[count]
		}
	});
}
function getMonthAmountMapForThreeYears(monthArr, firstYearAmnt,
		secondYearAmnt, thirdYearAmnt) {
	var monthArr = [ "FP 01", "FP 02", "FP 03", "FP 04", "FP 05", "FP 06",
			"FP 07", "FP 08", "FP 09", "FP 10", "FP 11", "FP 12" ]; 
																	
	var colorArr = [ '#FF0F00', '#FF9E01', '#0D8ECF', '#FCD202', '#CD0D74',
			'#754DEB', '#04D215', '#0D8ECF', '#2A0CD0', '#8A0CCF', '#CD0D74',
			'#754DEB' ];
	var labels = firstYearAmnt;
	var values = monthArr;
	var count = -1;
	return labels.map(function(amt) {

		count++;
		return {
			"month" : monthArr[count],
			"year1" : parseFloat(amt),
			"year2" : parseFloat(secondYearAmnt[count]),
			"year3" : parseFloat(thirdYearAmnt[count]),
			"color" : colorArr[count]
		}
	});
}
function populateMonthAnalysisChart(jdata) {
	var amntArr = new Array();
	var monthArr = new Array();
	var valPercentArr = new Array();
	var bardata = [];
	monthArr = ((jdata.barDataSet).data).rowKeys;

	var i = 0;
	$.each(((jdata.barDataSet).data).rows, function(i, item) {
		amntArr[i] = item.values;
		i++;
	});
	bardata = getMonthAmountMap(monthArr, amntArr);
	var chart = new CanvasJS.Chart("chartContainer", {
		title : {
			fontSize : 15
		},
		animationEnabled : true,
		axisY : {
			labelFormatter : function(e) {
				return "$" + e.value;
			},
			titleFontSize : "13",
			titleFontColor : "#555",
			labelFontWeight : "bolder",
			labelFontColor : "#555",
			labelFontFamily : "Helvetica",
			labelFontSize : 14
		},
		axisX : {
			titleFontSize : "13",
			titleFontColor : "#555",
			labelFontWeight : "bolder",
			labelFontColor : "#555",
			labelFontFamily : "Helvetica",
			labelFontSize : 14,
			interval : 1
		},
		legend : {
			verticalAlign : "bottom",
			horizontalAlign : "center"
		},
		theme : "theme1",
		toolTip : {
			fontSize : 13,
			fontStyle : "normal",
			fontFamily : "Helvetica",
			fontSize : 13,
			fontWeight : "bolder",
			fontColor : "#555"
		},

		data : [ {
			type : "stackedColumn",
			fillOpacity : 6,

			legendMarkerColor : "black",
			dataPoints : bardata
		} ]
	});
	chart.render();
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
		return commaFormatedVal;
	} else {
		return '';
	}
}
function formatNumWithoutDollor(n) {
	if (n != '') {
		var commaFormatedVal = n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g,
				"1,");
		if (commaFormatedVal.indexOf(".") != -1) {
			if ((commaFormatedVal.split(".")[1]).length == 1) {
				commaFormatedVal = commaFormatedVal.split(".")[0] + "."
						+ commaFormatedVal.split(".")[1] + '0';
			}
		} else {
			commaFormatedVal = commaFormatedVal + ".00";
		}
		return commaFormatedVal;
	} else {
		return '';
	}
}
function openSuperUserDetails() {
	// log user activity; view user details
	logUserActivityDotCom(7002, 'View User Details');

	var formData = {};
	var dt = geDataRefreshTime('SAM_CUSTOMER_DATA');
	$("#updateDateValueUser span").html(dt + " ET");
	$("#superId").css("display", "block");
	$("#superContent").css("display", "block");
	$("#showHideId0").prop('class', 'fa fa-times');
	commonScroll('superId');
	var custid = $("#reqCustNum").val();
	$("#dataTables-example_processing")
			.html(
					'<div id="example_processing_user" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-example_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getSuperUSerHighLevelDataSam/" + custid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					$('#my-modal').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					var cnt = 0;
					var mkttrHTML = "";
					$('#superUserTableId').html("");
					var name = '';
					if (null != data && data != undefined) {

						mkttrHTML += '<tr>'
						var skuFound = 'N';
						dotcomActivityVOArr[0] = data.staplesDotcomActivityVo;
						$.each(data.staplesDotcomActivityVo,
								function(i, items) {

									if (undefined != items.skuNum
											&& null != items.skuNum
											&& 'null' != items.skuNum
											&& '' != items.skuNum) {
										skuFound = 'Y';
									}
									cnt++;
								});

						mkttrHTML += '<td class="datatablesTd">'
								+ checkUundefined(data.contactFullName)
								+ '</td>'
								+ '<td class="datatablesTd"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:deepskyblue;text-decoration:underline;cursor:pointer;" onclick="javascript:openOutlook(\''
								+ checkUundefined(data.email) + '\');">'
								+ checkUundefined(data.email) + '</span></td>'
								+ '<td class="datatablesTd">'+'<span onclick="javascript:logUserActivityDotCom(8090,\''+checkUundefined(data.phoneNum)+': clicked from Users Grid in SAM Dashboard Page for Customer '+custid+'\')">'
								+ checkUundefined(data.phoneNum)+'</span>' + '</td>'
								+ '<td class="datatablesTd">'
								+ checkUundefined(data.custTimeone) + '</td>';
						if (skuFound == 'N')
							mkttrHTML += '<td class="datatablesTd">'
									+ checkUundefined(skuFound) + '</td>';
						else
							mkttrHTML += '<td class="datatablesTd"><a href="javascript:openSuperUserModalInfo(0,\''
									+ dotcomActivityVOArr
									+ '\',\'dotcom\',\''
									+ checkUundefined(data.contactFullName)
									+ '\')" style="text-decoration:underline;">'
									+ checkUundefined(skuFound) + '</a></td>';

						mkttrHTML += '</tr>';
					}
					$('#superUserTableId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-example"  ><thead><tr>'
											+ '<th>Contact Name </th>'
											+ '<th>Email Address</th>'
											+ '<th>Phone</th>'
											+ '<th>Time Zone</th>'
											+ '<th>Staples.com Activity</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML + '</tbody></table>');
					$("#dataTables-example_processing").css("display", "none");
					$('#dataTables-example').dataTable(
							{
								"lengthMenu" : [ [ 5, 15, 25, -1 ],
										[ 5, 15, 25, "All" ] ],
								"bSort" : true,
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}

								]
							}).fnDraw();
					$("#dataTables-example td:nth-child(5)").css("white-space",
							"nowrap");
					$('#dataTables-example_length label').css("color",
							"#004c74");
					$('#dataTables-example_length label').css("font-size",
							"15px");
					$('#dataTables-example_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-example_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-example_filter label').css("color",
							"#004c74");
					$('#dataTables-example_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-example_filter').css("text-align", "right");
					$('#dataTables-example_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-example_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-example_filter label').css("float", "right");
					$('#dataTables-example_info').css("color", "#004c74");
					$('#dataTables-example_info').css("font-weight", "600");
					$('#dataTables-example_paginate')
							.css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-example').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-example_filter input[type="search"]').attr(
							'placeholder', 'Enter Order Contact').css({
						'width' : '250px',
						'display' : 'inline-block'
					});

				}

			})
}

function onChangeMonthOrCat(monthYr, catid, isFromReturned) {
	// log user activity; view order list
	logUserActivityDotCom(7004, 'View Order List');
	var Amount = new Array();
	var startDate = "";
	var endDate = "";
	retFound = 'N';
	var formData = {};
	var custid = $("#reqCustNum").val();
	var monthVal = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();

	var map = {
		"Jan" : "01",
		"Feb" : "02",
		"Mar" : "03",
		"Apr" : "04",
		"May" : "05",
		"Jun" : "06",
		"Jul" : "07",
		"Aug" : "08",
		"Sep" : "09",
		"Oct" : "10",
		"Nov" : "11",
		"Dec" : "12",
		"ALL" : "0"
	};
	var monthNameArr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" ];
	if (undefined == monthYr) {
		var date = new Date();
		var dateString = date.toISOString().split('T')[0]; 
		var month = date.getMonth();
		var year = dateString.substring(0, 4);
		endDate = monthNameArr[month].toUpperCase() + "-" + year;

		var lastNumberOfDays = $("#lastXDaysSel :selected").val();
		if ("0" != lastNumberOfDays) {
			lastNumberOfDays = parseInt(lastNumberOfDays.substring(1, 3));
		}
		date.setDate(date.getDate() - lastNumberOfDays);
		dateString = date.toISOString().split('T')[0];
		month = date.getMonth();
		year = dateString.substring(0, 4);

		startDate = monthNameArr[month].toUpperCase() + "-" + year;

	} else {
		var monthNew, yearNew;
		if (monthYr.length == 6) {
			monthNew = parseInt(monthYr.substring(0, 2));
			yearNew = monthYr.substring(2, 6);
		} else if (monthYr.length == 5) {
			monthNew = parseInt(monthYr.substring(0, 1));
			yearNew = monthYr.substring(1, 5);
		}
		monthVal = monthNew - 1;
		if (undefined != monthVal && "ALL" != monthVal && monthNew != 0) {
			startDate = monthNameArr[monthVal].toUpperCase() + "-" + yearNew;
			endDate = monthNameArr[monthVal].toUpperCase() + "-" + yearNew;

		}
		if (-1 == monthVal) {
			startDate = "JAN-" + yearNew;
			endDate = "DEC-" + yearNew;

		}

	}

	catURL = ctx + "/sales/customer/" + custid
			+ "/transactions?sbuName=STAPLES.COM&startDate=" + startDate
			+ "&endDate=" + endDate;

	if (catid != "ALL") {
		catURL += "&catogeryId=" + catid;
	}

	$("#dataTables-order_processing")
			.html(
					'<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-order_processing").css("display", "block");

	$
			.ajax({
				dataType : "json",
				url : catURL,
				type : "GET",
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					$('#order-modal').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					var itemCnt = -1;
					var oldItem;
					var orderNumber;
					var itemReturned;
					var orderDate;
					var contactName;
					var noOfItems = 0;
					var orderTotal = 0;

					if (data != null) {
						$
								.each(
										data.customerOrders,
										function(i, item) {

											if (item.recordType != "0003") {

												var items1 = item.orderNumber
														.split('.');
												var items2 = '';
												for (var j = 0; j < items1.length; j++) {
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
												if (item.contactName == "null null") {
													item.contactName = "";
												}
												orderDate = checkUundefined(item.orderTranDate); 
												if (orderDate != null
														|| orderDate != '') {
													orderDate = orderDate
															.substring(0, 10);
													orderDateArr = orderDate
															.split("-");
													orderDate = orderDateArr[1]
															+ "/"
															+ orderDateArr[2]
															+ "/"
															+ orderDateArr[0];
												}
												var ordertypeDef = '';
												console.log("item order",
														item.ordrReturned);
												if (item.orderReturned == "true") {
													ordertypeDef = "(R)";
												} else {
													ordertypeDef = "";
												}
												item.totalCoponAmount = parseFloat(
														item.totalCoponAmount)
														.toFixed(2);
												item.totalDiscountAmount = parseFloat(
														item.totalDiscountAmount)
														.toFixed(2);

												var rewardNo = checkUundefined(item.orderIssuer);
												if (rewardNo.length > 0) {
													if (rewardNo
															.charAt(rewardNo.length - 1) == 'P') {
														rewardNo = rewardNo
																.replace('P',
																		'M');
													}
													if (rewardNo
															.charAt(rewardNo.length - 1) == 'C') {
														rewardNo = rewardNo
																.replace('C',
																		'S');
													}
												}

												var productSKUs = new Array();
												var productDescs = new Array();
												productSKUObj = item.orderLevelItems;
												if ((undefined != productSKUObj)
														|| (null != productSKUObj)) {
													$
															.each(
																	productSKUObj,
																	function(i,
																			orderItem) {
																		productSKUs
																				.push(orderItem.sku);
																		productDescs
																				.push(orderItem.productName);
																	});
												}

												mkttrHTML += '<tr class="odd gradeX">'
														+ '<td class="datatablesTd">'
														+ rewardNo
														+ '</td>'
														+ '<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('
														+ cnt
														+ ',\''
														+ checkUundefined(item.orderNumber)
														+ '\');" style="text-decoration:underline;padding-right:6px;">'
														+ checkUundefined(items2)
														+ '</a><span style="color:red;margin-right: -17px;margin-left: -4px;">'
														+ ordertypeDef
														+ '</span></td>'
														+ '<td class="datatablesTd">'
														+ orderDate
														+ '</td>'
														+ '<td>'
														+ checkUundefined(Number(item.orderItemsCount))
														+ '</td>' 
														+ '<td class="datatablesTd formatClsOrd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(parseFloat(
																item.tranTotalAmount)
																.toFixed(2))))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(item.totalDiscountAmount)))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(item.totalCoponAmount)))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ checkUundefined(item.contactName)
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ productSKUs
																.toString()
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ productDescs
																.toString()
														+ '</td>' + '</tr>';

												cnt++;
											}
										});
						OrderDeatilCount = cnt - 1;
					}

					if (undefined != monthYr) {
						populateMonthYearData(data, monthNew, yearNew);
						var monthValue = monthNameArr[monthNew - 1];
						if (monthNew == 0) {
							monthValue = "ALL";
						}
						$(
								"#yearSel option[value=" + monthNew + ""
										+ yearNew + "]").attr("selected",
								"selected");
						$('#datepickerTEXT').val(monthValue + " / " + yearNew);
						$('#datepickerTEXT').css("font-weight", "bold")
						$('#datepickerTEXT').css("font-family", "Hevletica")
						$('#datepickerTEXT').css("font-size", "14px");
					}

					$("#sortSel").val(catid);

					$('#orderDetailsTabId').html("");
					$('#orderDetailsTabId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'

											+ '<th>Reward #</th>'
											+ '<th>Order No.</th>'
											+ '<th>Order Date</th>'
											+ '<th>No. Of Items</th>'
											+ '<th>Order Total</th>'
											+ '<th>Discounts</th>'
											+ '<th>Coupons</th>'
											+ '<th>Order Contact</th>'
											+ '<th>productSKUs</th>'
											+ '<th>productDescs</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML
											+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
					$("#dataTables-order_processing").css("display", "none");
					$('#dataTables-order').dataTable(
							{
								"lengthMenu" : [ [ 5, 10, 30, -1 ],
										[ 5, 10, 30, "All" ] ],
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true,
									"visible" : true
								}, {
									"bSortable" : true,
									"visible" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true,
									"visible" : false,
									"sWidth" : '1%'
								}, {
									"bSortable" : true,
									"visible" : false,
									"sWidth" : '1%'
								} ],
								"fnDrawCallback" : function(oSettings) {

								}
							}).fnDraw();

					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label')
							.css("font-size", "15px");
					$('#dataTables-order_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-order_filter label').css("color", "#004c74");
					$('#dataTables-order_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-order_filter').css("text-align", "right");
					$('#dataTables-order_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-order_filter label').css("float", "right");
					$('#dataTables-order_filter input[type="search"]').css(
							"width", "270px");
					$('#dataTables-order_info').css("color", "#004c74");
					$('#dataTables-order_info').css("font-weight", "600");
					$('#dataTables-order_paginate').css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-order').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-order_filter input[type="search"]').attr(
							'placeholder',
							'Enter Order No. OR Item No. OR Item Desc.').css({
						'width' : '270px',
						'display' : 'inline-block'
					});
				},

				failure : function(data, textStatus, jqXHR) {
					console.log("Failed");
				}

			})
}

function populateMonthYearData(data, latestMonth, latestYear) {
	var optionVal = new Array();
	var optionTxt = new Array();
	var valcount = 0;
	var txtcount = 0;
	var is_dec = false;
	var is_from_beforeshow = false;
	var selectedMonth;
	if (data.monYearList != null && data.monYearList != undefined) {
		$.each(data.monYearList, function(i, item) {
			optionTxt[txtcount] = item;
			txtcount++;
		});
		for ( var value in data.monYearList) {
			optionVal[valcount] = value;
			valcount++;
		}
	}

	var selectedDeviceModel = $('#yearSel');
	selectedDeviceModel.empty();
	var count = 0;
	for (count = 0; count < optionVal.length; count++) {
		selectedDeviceModel.append($('<option/>', {
			value : optionVal[count],
			text : optionTxt[count]
		}));
	}
	var allYearArr = new Array();
	var position = 0;
	for (var cnt = 0; cnt < optionTxt.length; cnt++) {
		var txtVal = optionTxt[cnt].split(" ")[0]
		if (undefined != txtVal && txtVal == "All") {
			allYearArr[position] = optionTxt[cnt].split(" ")[1];
			position++
		}
	}
	if (null != allYearArr && undefined != allYearArr && '' != allYearArr)
		allYearArr.sort();
	var monthNameArr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" ];
	var maximumDate = '', minimumDate = '';
	if (latestMonth == 2)
		maximumDate = new Date(latestYear, latestMonth - 1, 28)
	else if (latestMonth == 1 || latestMonth == 3 || latestMonth == 5
			|| latestMonth == 7 || latestMonth == 8 || latestMonth == 10
			|| latestMonth == 12)
		maximumDate = new Date(latestYear, latestMonth - 1, 31)
	else
		maximumDate = new Date(latestYear, latestMonth - 1, 30)
	if (undefined != allYearArr[0] && allYearArr[0] != '') {
		minimumDate = new Date(allYearArr[0], 0, 31);
	} else {
		minimumDate = new Date(latestYear - 1, 0, 31);
	}

	$('#datepickerTEXT')
			.datepicker(
					{
						autoOpen : false,
						minDate : minimumDate,
						changeMonth : true,
						changeYear : true,
						showButtonPanel : true,
						dateFormat : 'mm/yy',
						maxDate : maximumDate,
						beforeShow : function(input, inst) {
							inst.dpDiv.addClass('month_year_datepicker')
							if ((datestr = $(this).val()).length > 0) {
								year = datestr.substring(datestr.length - 4,
										datestr.length);
								month = datestr.substring(0, 2);
								var map = {
									"Jan" : "1","Feb" : "2","Mar" : "3","Apr" : "4","May" : "5","Jun" : "6","Jul" : "7","Aug" : "8","Sep" : "9","Oct" : "10","Nov" : "11",	"Dec" : "12","ALL" : "12.6"	};
								var monthNameArr = [ "Jan", "Feb", "Mar",
										"Apr", "May", "Jun", "Jul", "Aug",
										"Sep", "Oct", "Nov", "Dec" ];
								if (!isNumeric(month)) {
									month = datestr.substring(0, 3);
									month = map[month];
								}
								if (month != 12) {
									is_dec = false;
									$(this).datepicker('option', 'defaultDate',
											new Date(year, month - 1, 1));
									$(this).datepicker('setDate',
											new Date(year, month - 1, 1));
								} else if (month == 12) {
									is_dec = true;
									$(this).datepicker('option', 'defaultDate',
											new Date(year, month - 1, 1));
									$(this).datepicker('setDate',
											new Date(year, month - 1, 1));
								}
								$(".ui-datepicker-calendar").hide();
							}
							$('#datepickerTEXT').css("font-weight", "bold");
							$('#datepickerTEXT')
									.css("font-family", "Hevletica");
							$('#datepickerTEXT').css("font-size", "14px");
						},
						onClose : function(dateText, inst) {
							function isDonePressed() {
								return ($('#ui-datepicker-div')
										.html()
										.indexOf(
												'ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all ui-state-hover') > -1);
							}

							if (isDonePressed()) {
								var monthTxt = $(
										"#ui-datepicker-div .ui-datepicker-month :selected")
										.text();
								var monthVal = $(
										"#ui-datepicker-div .ui-datepicker-month :selected")
										.val();
								var year = $(
										"#ui-datepicker-div .ui-datepicker-year :selected")
										.val();
								$('#datepickerTEXT').val(
										monthTxt + " / " + year);
								$('#datepickerTEXT').css("font-weight", "bold")
								$('#datepickerTEXT').css("font-family",
										"Hevletica")
								$('#datepickerTEXT').css("font-size", "14px");
								$('.date-picker').focusout()
							}
						},
						afterShow : function(input, inst, td) {

							if ((undefined != is_dec && '' != is_dec && is_dec == true)
									&& (month != 12.6)) {
								$("select.ui-datepicker-month option[value=11]")
										.next().removeAttr("selected");
								$("select.ui-datepicker-month option[value=11]")
										.next().attr("selected", false);
								$("select.ui-datepicker-month option[value=11]")
										.attr("selected", true);
							} else if (month == 12.6 && allselected == "Y") {
								$("select.ui-datepicker-month option[value=11]")
										.removeAttr("selected");
								$("select.ui-datepicker-month option[value=11]")
										.attr("selected", false);
								$("select.ui-datepicker-month option[value=11]")
										.next().attr("selected", true);
							}

						},
						onChangeMonthYear : function(year, month, inst) {
							var val = $(
									"select.ui-datepicker-month option[value=11]")
									.attr("selected");
							var val1 = $(
									"select.ui-datepicker-month option[value=11]")
									.next().attr("selected");
							var val2 = $(
									"select.ui-datepicker-month option:last")
									.attr("selected");
							is_dec = is_dec;
							if ((undefined != val) && ('selected' == val)) {
								allselected = "Y";
							}
							if (((undefined == val) && (undefined == val1) && (month == 12))) {
								allselected = "Y";
							} else if ((undefined != val2 && val2 == 'selected')) {
								allselected = "Y";
								is_dec = false;
							} else if ((undefined == val)
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
					})
			.focus(
					function() {
						$('.ui-datepicker-calendar').detach();
						$('.ui-datepicker-close')
								.unbind("click")
								.click(
										function() {
											var monthTxt = $(
													"#ui-datepicker-div .ui-datepicker-month :selected")
													.text();
											var monthVal = $(
													"#ui-datepicker-div .ui-datepicker-month :selected")
													.val();
											var year = $(
													"#ui-datepicker-div .ui-datepicker-year :selected")
													.val();
											$('#datepickerTEXT').val(
													monthTxt + " / " + year);
											$('#datepickerTEXT').css(
													"font-weight", "bold")
											$('#datepickerTEXT').css(
													"font-family", "Hevletica")
											$('#datepickerTEXT').css(
													"font-size", "14px");
											var catId = $("#sortSel").val();
											if (monthVal == 11.6)
												monthVal = -1
											var monthYear = (parseInt(monthVal) + 1)
													+ "" + year;
											onChangeMonthOrCat(monthYear,
													catId, 'N');
											$("#datepickerTEXT").datepicker(
													"hide");
											$("#lastXDaysSel").val("0");
										});
					});

}

function populateCatData() {
	var selectedDeviceModel = $('#sortSel');
	var catListArr = new Array();

	selectedDeviceModel.empty();
	selectedDeviceModel.append($('<option/>', {
		value : 'ALL',
		text : 'All Purchases'
	}));

	$.ajax({
		dataType : "json",
		url : ctx + "/sales/categories/list",
		type : "GET",
		cache : false,
		data : "",
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {

			if (data != null) {
				$.each(data.categories, function(i, item) {

					var catName = checkUundefined(item.name);
					if ((catName.length > 0)
							&& (catListArr.indexOf(catName) == -1)) {
						catListArr.push(catName);
						var catVal = catName.replace('&', '%26');
						selectedDeviceModel.append($('<option/>', {
							value : catVal,
							text : catName
						}));
					}

				});

			}

		}

	})

}

function openSuperUserModalInfo(index, dotcomData, fromWhere, name) {
	var mkttrHTML = "";
	var column = new Array();
	var count = 0;
	var column3 = $.each(dotcomActivityVOArr[index], function(i, item) {
		return item;
	});
	if ((null == column3 || '' == column3) || column3.length < 1) {
		alert("No details available for this customer");
		return false;
	}

	/* Dotcom Data Start */
	if (null != column3 && '' != column3) {
		var htmlData = "";
		$('#a3').attr("data-toggle", "tab");
		$('#li3').removeClass('disabled');
		$("#tab_1_3").html("");

		for (var i = 0; i < column3.length; i++) {
			var thumbnail = checkUundefined(column3[i].thumbnail);

			if (thumbnail == '' || null == thumbnail | undefined == thumbnail) {
				thumbnail = "http://www.staplesadvantage.com/is/image/Staples/m000049_sc7?$Advthb$&defaultImage=advnoimgavail_sc7&hei=160&wid=160&op_sharpen=1"
			} else {
				thumbnail = 'http://www.staples-3p.com/s7/is/image/Staples/'
						+ thumbnail;
			}

			var dotActivity = checkUundefined(column3[i].cartActivity);
			if (dotActivity == 'Abandoned')
				dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;" >Abandoned</span></div>';
			else if (dotActivity == 'Viewed')
				dotActivity = '<div class="a-row a-size-small"><span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:darkblue;" >Viewed</span></div>';
			else if (dotActivity == 'Purchased')
				dotActivity = '<div class="a-row a-size-small"> <span style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:green;" >Purchased</span></div>';

			var url3 = 'http://www.staples.com/product_'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNum
							: "")
							: "");
			htmlData = htmlData.toString()
					+ '<li style="border: 1px solid #ccc;height: 362px;width: 135px;display: block;border-radius: 3px !important;"><a href="#" title="'
					+ checkUundefined(column3[i].skuName)
					+ '"_blank" style="text-decoration:none;cursor:default;"><img src='
					+ thumbnail
					+ ' width="124" height="160" alt="jQuery in Action, Second Edition"/>'
					+ '<div class="p13n-sc-line-clamp-3 p13n-sc-truncated" aria-hidden="true" data-rows="3" style="max-height: 170px !important;height: 97px;border-bottom: 1px solid #bbb;border-style: dashed;">'
					+ truncateTitle(checkUundefined(column3[i].skuName))
					+ '</div></a>'

					+ '<div class="a-row a-size-small"><a class="a-size-small a-link-child" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: 100px;" href="javascript:openUrl(\''
					+ url3
					+ '\')" onclick="generateLogs(\'usersSku\')">'
					+ checkUundefined(column3[i].skuNum)
					+ '</a></div>'
					+ '<div class="a-row a-size-small">'
					+ checkUundefined(column3[i].lastBrowseDate).substring(0,
							11) + '</div>'
					+ '<div class="a-row a-size-small">$'
					+ checkUundefined(column3[i].unitPrice) + '</div>'
					+ dotActivity + '</li>';

		}
		totalDotCommLength = column3.length;

		var prefixData = '<div id="amazon_scroller_dotcom'
				+ index
				+ '" class="amazon_scroller" style="background-color:#fff;">'
				+ '<div id="dotcomId" class="amazon_scroller_mask"><ul id="SUulid">'

		var suffixData = '</ul></div><ul id="dotcom_nav" class="amazon_scroller_nav">'
				+ '<li><a href="#" class="btn btn-sm default prev" title="Prev" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-left" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
				+ '<li><a href="#" class="btn btn-sm default next" title="Next" style="background-color:cadetblue;color;#fff;font-weight:bolder;"><i class="fa fa-angle-right" style="font-size:18px;font-weight:18px;color:#fff;"></i></a></li>'
				+ '</ul><div style="clear: both"></div></div>'
		$("#tab_1_3").html(prefixData + "" + htmlData + "" + suffixData);

		$("#amazon_scroller_dotcom" + index).amazon_scroller({
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
		generateLogs('usersDetailYlink');
	} else {
		$("#tab_1_3").html("");
		$('#a3').removeAttr("data-toggle");
		$('#li3').addClass('disabled');
	}

	if (fromWhere == 'dotcom') {
		$('#li1').remove();
		$("#tab_1_1").remove();
		$('#a3').attr("aria-expanded", true);
		$('#li3').attr("class", "active");
		$('#li2').attr("class", "");
		$('#li1').attr("class", "");
		if ('tab-pane fade active in' == ($("#tab_1_1").attr("class"))) {
			$("#tab_1_1").attr("class", "tab-pane fade");
		}
		$("#tab_1_3").attr("class", "tab-pane fade active in");
		$("#a3")
				.attr(
						"style",
						"color: darkolivegreen !important; font-family: arialmt; font-weight: bold; font-size: 16px;text-align: left !important; padding: 10px;");

	}
	/* Dotcom Data End */

	$('#searchStringId').html("");
	$('#super-info-dialog').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
	if (name != undefined && name != '') {
		$("#uName").html(name);
	}
	$("table#searchStringId").parent().remove();
}

function openSuperUserInfo(name, index) {
	var mkttrHTML = "";

	var serachlistItem = $.each(searchItemsListVOArr[index], function(i, item) {
		return item.searchItems;
	});
	var column1 = $.each(abandonedCartListVOArr[index], function(i, item) {
		return item;
	});
	var column2 = $.each(custRecommendationListVOArr[index], function(i, item) {

		return item;
	});
	var column3 = $.each(dotcomActivityVOArr[index], function(i, item) {

		return item;
	});
	var max = ((column1.length) > (column2.length + column3.length)) ? "column1"
			: ((column2.length >= column3.length) ? "column2" : "column3")
	if (max === 'column1') {
		for (var i = 0; i < column1.length; i++) {

			var url1 = 'http://www.staples.com/product_'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "");
			var url2 = 'http://www.staples.com/product_'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "");
			var url3 = 'http://www.staples.com/product_'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "");
			mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url1
					+ '\')">'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url2
					+ '\')">'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;font-size:14px;"><span style="color:red;text-color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url3
					+ '\')">'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].itemDescription
							: "")
							: "") + '</td>' + '</tr>';
		}
	} else if (max == 'column2') {
		for (var i = 0; i < column2.length; i++) {
			var url1 = 'http://www.staples.com/product_'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "");
			var url2 = 'http://www.staples.com/product_'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "");
			var url3 = 'http://www.staples.com/product_'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "");
			mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url1
					+ '\')">'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url2
					+ '\')">'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url3
					+ '\')">'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].itemDescription
							: "")
							: "") + '</td>' + '</tr>';

		}
	} else if (max == 'column3') {

		for (var i = 0; i < column3.length; i++) {
			var url1 = 'http://www.staples.com/product_'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "");
			var url2 = 'http://www.staples.com/product_'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "");
			var url3 = 'http://www.staples.com/product_'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "");
			mkttrHTML += '<tr class="odd gradeX"><td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url1
					+ '\')">'
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column1 != undefined && column1 != '') ? checkUundefined((column1[i] != undefined && column1[i] != '') ? column1[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url2
					+ '\')">'
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column2 != undefined && column2 != '') ? checkUundefined((column2[i] != undefined && column2[i] != '') ? column2[i].itemDescription
							: "")
							: "")
					+ '</td>'
					+ '<td style="text-align:left;word-break:break-all;font-size:14px;"><span style="color:red;"><a style="text-decoration:underline;color:red;" href="javascript:openUrl(\''
					+ url3
					+ '\')">'
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNumber
							: "")
							: "")
					+ "</a>-</span>"
					+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].itemDescription
							: "")
							: "") + '</td>' + '</tr>';
		}
	}

	$('#superUserInfoId').html("");
	$('#searchStrid').html("");
	var searchStr = "";
	for (var count = 0; count < serachlistItem.length; count++) {
		searchStr = +""
				+ checkUundefined((serachlistItem[count] != undefined && serachlistItem[count] != '') ? serachlistItem[count].searchItems
						: "") + ",";

	}
	if (searchStr != '' && (searchStr.indexOf(',') != -1)) {
		searchStr = searchStr.substring(0, searchStr.lastIndexOf(","));
	}
	$('#searchStrid').append(
			'<tr><td style="color:red;font-weight:bold">' + searchStr
					+ '</td></tr>');
	$('#super-info').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});

	if (name != undefined && name != '') {
		$("#userName").html(name);
	}
	$('#superUserInfoId')
			.html(
					'<table class="table table-striped table-bordered table-hover" id="dataTables-superInfo" width="100%" ><thead><tr>'
							+ '<th>ABANDONED CART</th>'
							+ '<th>OUR RECOMMENDATIONS</th>'
							+ '<th>DOTCOM ACTIVITY</th>'
							+ '</tr>'
							+ '</thead><tbody>'
							+ mkttrHTML
							+ '</tbody></table>');

	$('#dataTables-superInfo').DataTable({
		"lengthMenu" : [ [ 4, 10, 15, -1 ], [ 4, 10, 15, "All" ] ],
		"jqueryUI" : true,
		"order" : [],
		"oLanguage" : {
			"sSearch" : "Filter: "
		},
		"aoColumns" : [ {
			"bSortable" : true,
			"sWidth" : '33%',
			"sType" : "mystring"
		}, {
			"bSortable" : true,
			"sWidth" : '33%',
			"sType" : "mystring"
		}, {
			"bSortable" : true,
			"sWidth" : '33%',
			"sType" : "mystring"
		}

		]

	})

	$("#dataTables-superInfo td").css("padding-left", "10px");
	$("#dataTables-superInfo td").css("WORD_BREAK", "BREAK-ALL");
	$('#dataTables-superInfo_length label').css("color", "#004c74");
	$('#dataTables-superInfo_length label').css("color", "#004c74");
	$('#dataTables-superInfo_length label').css("font-weight", "600");
	$('#dataTables-superInfo_filter label').css("color", "#004c74");
	$('#dataTables-superInfo_filter label').css("letter-spacing", "1px");
	$('#dataTables-superInfo_filter').css("text-align", "right");
	$('#dataTables-superInfo_filter label').css("font-weight", "600");
	$('#dataTables-superInfo_filter label').css("float", "right");
	$('#dataTables-superInfo_info').css("color", "#004c74");
	$('#dataTables-superInfo_info').css("font-weight", "600");
	$('#dataTables-superInfo_paginate').css("text-align", "right");
	$('.form-inline .form-control').css("color", "#004c74");
	$('.form-inline .form-control').css("border", "1px solid #004c74");
	$('select .form-control.input-sm').css("border", "1px solid #004c74");
	$('#dataTables-superInfo').removeClass('display').addClass(
			'table table-striped table-bordered');
	$('#dataTables-superInfo_filter input[type="search"]').attr('placeholder',
			'Search').css({
		'width' : '250px',
		'display' : 'inline-block'
	});
}

function openOrderDetailsInfo(index, ordNum) {
	// log user activity; view order details
	logUserActivityDotCom(7005, 'View Order Details');

	var inputString = $('#dataTables-order_filter input[type=search]').val();

	var contains = 0;
	var mkttrHTML = "";
	var formData = {};
	var custid = $("#reqCustNum").val();
	$
			.ajax({
				url : ctx + "/sales/customer/" + custid + "/" + ordNum
						+ "/info?sbuName=STAPLES.COM",
				type : "GET",
				cache : false,
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					objPurchaseDetailsListVO = data;

					var item1 = objPurchaseDetailsListVO.orderDetails;
					$
							.each(
									item1.orderLineItems,
									function(i, item) {
										var discount = 0;
										var coupon = 0;

										if (inputString != undefined
												&& inputString != null
												&& item.skuNumber != undefined
												&& item.skuNumber != null
												&& item.itemDescription != undefined
												&& item.itemDescription != null
												&& (item.skuNumber
														.toLowerCase()
														.indexOf(
																inputString
																		.toLowerCase()) > -1 || item.itemDescription
														.toLowerCase()
														.indexOf(
																inputString
																		.toLowerCase()) > -1)) {
											contains = 1;
										}

										if (item.lineDiscount != undefined
												|| item.lineDiscount != null) {
											$
													.each(
															item.lineDiscount,
															function(i,
																	discountItem) {

																discount += (checkUundefined(discountItem.discountAmount) * 100);
															});

											if (discount == 0) {
												discount = chkNegAmount(formatNum("0"));
											} else {
												discount = chkNegAmount(formatNum((checkUundefined(discount) / 100)));
											}

										} else {

											discount = chkNegAmount(formatNum("0")); 
																						

										}

										if (item.couponItem != undefined
												|| item.couponItem != null) {
											$
													.each(
															item.couponItem,
															function(i,
																	couponItem) {
																coupon += (checkUundefined(couponItem.couponLineAmount) * 100);
															});

											if (coupon == 0) {
												coupon = chkNegAmount(formatNum("0"));
											} else {
												coupon = chkNegAmount(formatNum((checkUundefined(coupon) / 100)));
											}

										} else {

											coupon = chkNegAmount(formatNum("0")); 
																					

										}

										var totalSpend = '';
										var productSKU = '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.productSKU)+'</a>';
										if (ordNum.startsWith("POS")) {

											totalSpend = chkNegAmount(formatNum(checkUundefined(item.extendedPrice)));
											if (checkUundefined(item.tranLineStatusId).length > 0
													&& checkUundefined(item.tranLineStatusId) == "RETURN"
													&& checkUundefined(item.tranLineStatusId) != ''
													&& checkUundefined(item.tranLineStatusId) != null) {
												productSKU = "<span style='color:red;'>"
														+ productSKU
														+ "</span>";
											}
										} else {

											totalSpend = chkNegAmount(formatNum(checkUundefined(item.lineTotalAmount)));
											if ('' != checkUundefined(item.masterSalesTranId)
													&& checkUundefined(item.masterSalesTranId) != -1
													& checkUundefined(item.masterSalesTranId) != ''
													&& checkUundefined(item.masterSalesTranId) != null) {
												productSKU = "<span style='color:red;'>"
														+ productSKU
														+ "</span>";
											} else if (checkUundefined(item.masterSalesTranId) == -1) {
												productSKU = "<span style=''>"
														+ productSKU
														+ "</span>";
											}
										}

										var itemStatusDesc = checkUundefined(item.itemStatusDescription);
										if (itemStatusDesc.length > 0
												&& itemStatusDesc == "-1") {
											itemStatusDesc = "DELIVERED";
										}

										mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold; !important;">'
												+ checkUundefined(productSKU)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(item.productName)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(item.transQuantity)
												+ '</td>'

												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(discount)
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(coupon)
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(totalSpend)
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(itemStatusDesc)
												+ '</td>' + '</tr>';

									});

					$('#OrderDetailsInfoId').html("");
					$('#order-info').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					$("#ordertitle").html("");
					$("#ordertitle").html("Order Info - " + ordNum);
					$('#OrderDetailsInfoId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-OrderInfo" width="100%" ><thead><tr>'
											+ '<th>Item No.</th>'
											+ '<th>Item description</th>'
											+ '<th>Qty</th>'
											+ '<th>Discount</th>'
											+ '<th>Coupon</th>'
											+ '<th>Total Spend</th>'
											+ '<th>Status</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML
											+ '</tbody></table>');

					var oTable = $('#dataTables-OrderInfo').DataTable(
							{
								"lengthMenu" : [ [ 5, 10, 15, -1 ],
										[ 5, 10, 15, "All" ] ],
								"jqueryUI" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"order" : [],
								"aoColumns" : [ {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								} ],

							})

					if (contains == 1) {
						$('#dataTables-OrderInfo_filter input[type=search]')
								.val(inputString);

					}

					$("#dataTables-OrderInfo td")
							.css("WORD_BREAK", "BREAK-ALL");
					$('#dataTables-OrderInfor_length label').css("color",
							"#004c74");
					$('#dataTables-OrderInfo_length label').css("color",
							"#004c74");
					$('#dataTables-OrderInfo_length label').css("font-weight",
							"600");
					$('#dataTables-OrderInfo_filter label').css("color",
							"#004c74");
					$('#dataTables-OrderInfo_filter label').css(
							"letter-spacing", "1px");
					$('#dataTables-OrderInfo_filter')
							.css("text-align", "right");
					$('#dataTables-OrderInfo_filter label').css("font-weight",
							"600");
					$('#dataTables-OrderInfo_filter label').css("float",
							"right");
					$('#dataTables-OrderInfo_info').css("color", "#004c74");
					$('#dataTables-OrderInfo_info').css("font-weight", "600");
					$('#dataTables-OrderInfo_paginate').css("text-align",
							"right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-OrderInfo').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-OrderInfo_filter input[type="search"]')
							.attr('placeholder', 'Enter Item No. OR Item Desc.')
							.attr("style", "width : 210px !important").css(
									'display', 'inline-block');
					$('#dataTables-OrderInfo_filter input[type="search"]')
							.focus();
				}
			});
}

function openReturnedOrderDetailsInfo(index, ordNum) {
	// log user activity; view order details
	logUserActivityDotCom(7018, 'Clicked Returns on Orders grid');

	var inputString = $('#dataTables-order_filter input[type=search]').val();
	var contains = 0;
	var mkttrHTML = "";
	$
			.each(
					objPurchaseDetailsListVO[index].purchRwdsDtlListVO,
					function(i, item) {

						if (item.netSpendAmount >= 0) {
							return true;
						}
						if (inputString != undefined
								&& inputString != null
								&& item.skuNumber != undefined
								&& item.skuNumber != null
								&& item.itemDescription != undefined
								&& item.itemDescription != null
								&& (item.skuNumber.toLowerCase().indexOf(
										inputString.toLowerCase()) > -1 || item.itemDescription
										.toLowerCase().indexOf(
												inputString.toLowerCase()) > -1)) {
							contains = 1;
						}
						mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+'<a style="pointer-events:none;cursor: default;color:inherit;">'+ checkUundefined(item.skuNumber)+'</a>'
								+ '</td>'
								+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ checkUundefined(item.tranDate)
								+ '</td>'
								+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ checkUundefined(item.itemDescription)
								+ '</td>'
								+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ checkUundefined(item.totalQty)
								+ '</td>'
								+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ chkNegAmount(formatNum(checkUundefined(item.totalPriceAmount)))
								+ '</td>'
								+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ formatNum(checkUundefined(item.couponAmount))
								+ '</td>'
								+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
								+ chkNegAmount(formatNum(checkUundefined(item.netSpendAmount)))
								+ '</td>' + '</tr>';

					});

	$('#OrderDetailsInfoId').html("");
	$('#order-info').modal({
		"title" : "ddd",
		"backdrop" : "static",
		"keyboard" : true,
		"show" : true
	});
	$("#ordertitle").html("");
	$("#ordertitle").html("Returned Info - " + ordNum);
	$('#OrderDetailsInfoId')
			.html(
					'<table class="table table-striped table-bordered table-hover" id="dataTables-OrderInfo" width="100%" ><thead><tr>'
							+ '<th>Item No.</th>'
							+ '<th>Return Date</th>'
							+ '<th>Item description</th>'
							+ '<th>Qty</th>'
							+ '<th>Price</th>'
							+ '<th>Coupons</th>'
							+ '<th>Total Spend</th>'
							+ '</tr>'
							+ '</thead><tbody>'
							+ mkttrHTML
							+ '</tbody></table>');

	var oTable = $('#dataTables-OrderInfo').DataTable({
		"lengthMenu" : [ [ 5, 10, 15, -1 ], [ 5, 10, 15, "All" ] ],
		"jqueryUI" : true,
		"oLanguage" : {
			"sSearch" : "Filter: "
		},
		"order" : [],
		"aoColumns" : [ {
			"bSortable" : true,
			"sWidth" : '33%'
		}, {
			"bSortable" : true,
			"sWidth" : '33%'
		}, {
			"bSortable" : true,
			"sWidth" : '33%'
		}, {
			"bSortable" : true,
			"sWidth" : '33%'
		}, {
			"bSortable" : true,
			"sWidth" : '33%'
		}, {
			"bSortable" : true,
			"sWidth" : '33%',
			"visible" : false
		}, {
			"bSortable" : true,
			"sWidth" : '33%'
		} ],

	})

	if (contains == 1) {
		$('#dataTables-OrderInfo_filter input[type=search]').val(inputString);

	}
	$("#dataTables-OrderInfo td").css("WORD_BREAK", "BREAK-ALL");
	$('#dataTables-OrderInfor_length label').css("color", "#004c74");
	$('#dataTables-OrderInfo_length label').css("color", "#004c74");
	$('#dataTables-OrderInfo_length label').css("font-weight", "600");
	$('#dataTables-OrderInfo_filter label').css("color", "#004c74");
	$('#dataTables-OrderInfo_filter label').css("letter-spacing", "1px");
	$('#dataTables-OrderInfo_filter').css("text-align", "right");
	$('#dataTables-OrderInfo_filter label').css("font-weight", "600");
	$('#dataTables-OrderInfo_filter label').css("float", "right");
	$('#dataTables-OrderInfo_info').css("color", "#004c74");
	$('#dataTables-OrderInfo_info').css("font-weight", "600");
	$('#dataTables-OrderInfo_paginate').css("text-align", "right");
	$('.form-inline .form-control').css("color", "#004c74");
	$('.form-inline .form-control').css("border", "1px solid #004c74");
	$('select .form-control.input-sm').css("border", "1px solid #004c74");
	$('#dataTables-OrderInfo').removeClass('display').addClass(
			'table table-striped table-bordered');
	$('#dataTables-OrderInfo_filter input[type="search"]').attr('placeholder',
			'Enter Item No. OR Item Desc.').attr("style",
			"width : 210px !important").css('display', 'inline-block');
	$('#dataTables-OrderInfo_filter input[type="search"]').focus();

	if (objPurchaseDetailsListVO[index].orderNumber.indexOf(inputString) > -1) {
		oTable.draw();
	} else {
		oTable.search(inputString).draw();
	}

}

function openOrderDetailsShipTO(order_number, shipTo) {
	// log user activity; view order details
	logUserActivityDotCom(7010, 'View Ship To Order Line Info');
	$("#shplocId").html("");
	$("#shplocId")
			.html(
					"Ship To Order Info "
							+ shipTo
							+ "<span style='padding-left:3px;padding-right:3px;'>-</span>"
							+ order_number);
	var inputString = $('#dataTables-ShipInfo_filter input[type="search"]')
			.val();
	var contains = 0;
	var mkttrHTML = "";
	var formData = {};
	var custid = $("#reqCustNum").val();
	$("#dataTables-order_processing")
			.html(
					'<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-order_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid + "/" + order_number
						+ "/info?sbuName=STAPLES.COM",
				type : "GET",
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					$
							.each(
									data.orderDetails.orderLineItems,
									function(i, item) {
										var discount = 0;
										var coupon = 0;

										if (inputString != undefined
												&& inputString != null
												&& item.skuNumber != undefined
												&& item.skuNumber != null
												&& item.itemDescription != undefined
												&& item.itemDescription != null
												&& (item.skuNumber
														.toLowerCase()
														.indexOf(
																inputString
																		.toLowerCase()) > -1 || item.itemDescription
														.toLowerCase()
														.indexOf(
																inputString
																		.toLowerCase()) > -1)) {
											contains = 1;
										}

										if (item.lineDiscount != undefined
												|| item.lineDiscount != null) {
											$
													.each(
															item.lineDiscount,
															function(i,
																	discountItem) {

																discount += (checkUundefined(discountItem.discountAmount) * 100);
															});

											if (discount == 0) {
												discount = chkNegAmount(formatNum("0"));
											} else {
												discount = chkNegAmount(formatNum((checkUundefined(discount) / 100)));
											}

										} else {

											discount = chkNegAmount(formatNum("0")); 
																						

										}

										if (item.couponItem != undefined
												|| item.couponItem != null) {
											$
													.each(
															item.couponItem,
															function(i,
																	couponItem) {

																coupon += (checkUundefined(couponItem.couponLineAmount) * 100);
															});

											if (coupon == 0) {
												coupon = chkNegAmount(formatNum("0"));
											} else {
												coupon = chkNegAmount(formatNum((checkUundefined(coupon) / 100)));
											}

										} else {

											coupon = chkNegAmount(formatNum("0")); 
																					

										}

										var totalSpend = '';
										var productSKU ='<a style="pointer-events:none;cursor: default;color:inherit;">'+ checkUundefined(item.productSKU)+'</a>';
										if (order_number.startsWith("POS")) {

											totalSpend = chkNegAmount(formatNum(checkUundefined(item.extendedPrice)));
											if (checkUundefined(item.tranLineStatusId).length > 0
													&& checkUundefined(item.tranLineStatusId) == "RETURN") {
												productSKU = "<span style='color:red;'>"
														+ productSKU
														+ "</span>";
											}
										} else {

											totalSpend = chkNegAmount(formatNum(checkUundefined(item.lineTotalAmount)));
											if (checkUundefined(item.masterSalesTranId) != -1
													&& checkUundefined(item.masterSalesTranId) != ''
													&& checkUundefined(item.masterSalesTranId) != null) {
												productSKU = "<span style='color:red;'>"
														+ productSKU
														+ "</span>";
											} else if (checkUundefined(item.masterSalesTranId) == -1
													|| checkUundefined(item.masterSalesTranId) == ''
													|| checkUundefined(item.masterSalesTranId) == null) {
												productSKU = "<span style=''>"
														+ productSKU
														+ "</span>";
											}
										}

										var itemStatusDesc = checkUundefined(item.itemStatusDescription);
										if (itemStatusDesc.length > 0
												&& itemStatusDesc == "-1") {
											itemStatusDesc = "DELIVERED";
										}

										mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold; !important;">'
												+ productSKU
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(item.productName)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ checkUundefined(item.transQuantity)
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ discount
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ coupon
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ totalSpend
												+ '</td>'
												+ '<td class="formatClsOrd" style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ itemStatusDesc
												+ '</td>'
												+ '</tr>';

									})
					$("#dataTables-order_processing").css("display", "none");
					$('#shipOrderDetailsInfoId').html("");
					$('#shiporder-info').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					$('#shipOrderDetailsInfoId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-shipOrderInfo" width="100%" ><thead><tr>'
											+ '<th>Item No.</th>'
											+ '<th>Item description</th>'
											+ '<th>Qty</th>'
											+ '<th>Discount</th>'
											+ '<th>Coupon</th>'
											+ '<th>Total Spend</th>'
											+ '<th>Status</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML
											+ '</tbody></table>');

					var oTable = $('#dataTables-shipOrderInfo').DataTable(
							{
								"lengthMenu" : [ [ 5, 10, 15, -1 ],
										[ 5, 10, 15, "All" ] ],
								"jqueryUI" : true,
								"order" : [],
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								}, {
									"bSortable" : true,
									"sWidth" : '33%'
								} ],

							})

					if (contains == 1) {
						$('#dataTables-shipOrderInfo_filter input[type=search]')
								.val(inputString);
						$('#dataTables-OrderInfo_filter input[type=search]')
								.val(inputString);

					}
					$("#dataTables-shipOrderInfo td").css("WORD_BREAK",
							"BREAK-ALL");
					$('#dataTables-shipOrderInfor_length label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_length label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_length label').css(
							"font-weight", "600");
					$('#dataTables-shipOrderInfo_filter label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_filter label').css(
							"letter-spacing", "1px");
					$('#dataTables-shipOrderInfo_filter').css("text-align",
							"right");
					$('#dataTables-shipOrderInfo_filter label').css(
							"font-weight", "600");
					$('#dataTables-shipOrderInfo_filter label').css("float",
							"right");
					$('#dataTables-shipOrderInfo_filter label').addClass(
							"col-lg-8 col-sm-12 col-md-9");
					$('#dataTables-shipOrderInfo_filter label').css(
							"text-align", "center");
					$('#dataTables-shipOrderInfo_info').css("color", "#004c74");
					$('#dataTables-shipOrderInfo_info').css("font-weight",
							"600");
					$('#dataTables-shipOrderInfo_paginate').css("text-align",
							"right");

					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-shipOrderInfo').removeClass('display')
							.addClass('table table-striped table-bordered');
					if (contains == 0)
						$(
								'#dataTables-shipOrderInfo_filter input[type="search"]')
								.attr('placeholder',
										'Enter Item No. OR Item Desc.').css({
									'width' : '250px',
									'display' : 'inline-block',
									'padding-right' : '1px'
								});
					$('#dataTables-shipOrderInfo_filter input[type="search"]')
							.focus();

					if (order_number.toString().indexOf(inputString) > -1) {
						oTable.draw();
					} else {
						oTable.search(inputString);
						oTable.draw();
					}

				}
			})
}

function openReturnedOrderDetailsShipTO(order_number, shipTo, order_date) {
	// log user activity; view order details
	logUserActivityDotCom(7019, 'Clicked Returns on Ship To Order Info grid');
	$("#shplocId").html("");
	$("#shplocId")
			.html(
					"Returned Info "
							+ shipTo
							+ "<span style='padding-left:3px;padding-right:3px;'>-</span>"
							+ order_number);
	var inputString = $('#dataTables-ShipInfo_filter input[type="search"]')
			.val();
	var contains = 0;
	var mkttrHTML = "";
	var formData = {};
	var custid = $("#reqCustNum").val();
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getOrderDetailsShipTO/" + custid + "/"
						+ order_number + "/" + order_date,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					$
							.each(
									data,
									function(i, item) {

										if (item.netSpendAmount >= 0) {
											return true;
										}

										if (inputString != null
												&& inputString != '')
											contains = 1;
										mkttrHTML += '<tr class="odd gradeX"><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:17%;">'
												+ '<a style="pointer-events:none;cursor: default;color:inherit;">'+checkUundefined(item.skuNumber)+'</a>'
												+ '</td><td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:18%;">'
												+ checkUundefined(item.tranDate)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:20%;">'
												+ checkUundefined(item.itemDescription)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:15%;">'
												+ checkUundefined(item.totalQty)
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:15%;">'
												+ chkNegAmount(formatNum(checkUundefined(item.totalPriceAmount)))
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;">'
												+ formatNum(checkUundefined(item.couponAmount))
												+ '</td>'
												+ '<td style="text-align:center;color:#444444 !important;font-size:14px;font-family:Helvetica;font-weight:bold;width:25%;">'
												+ chkNegAmount(formatNum(checkUundefined(item.netSpendAmount)))
												+ '</td>' + '</tr>';

									})

					$('#shipOrderDetailsInfoId').html("");
					$('#shiporder-info').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					$('#shipOrderDetailsInfoId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-shipOrderInfo" width="100%" ><thead><tr>'
											+ '<th style="text-align:center;line-height:2.5;white-space:nowrap">Item No.</th>'
											+ '<th style="text-align:center;line-height:2.5;">Return Date</th>'
											+ '<th style="text-align:center;line-height:2.5;">Item Description</th>'
											+ '<th style="text-align:center;line-height:2.5;">Qty</th>'
											+ '<th style="text-align:center;line-height:2.5;">Price</th>'
											+ '<th style="text-align:center;line-height:2.5;">Coupons</th>'
											+ '<th style="text-align:center;line-height:2.5;white-space:nowrap"">Total Spend</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML + '</tbody></table>');

					var oTable = $('#dataTables-shipOrderInfo').DataTable(
							{
								"lengthMenu" : [ [ 5, 10, 15, -1 ],
										[ 5, 10, 15, "All" ] ],
								"jqueryUI" : true,
								"order" : [],
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true,
									"visible" : false
								}, {
									"bSortable" : true
								} ],

							})

					if (contains == 1) {
						$('#dataTables-shipOrderInfo_filter input[type=search]')
								.val(inputString);
						$('#dataTables-OrderInfo_filter input[type=search]')
								.val(inputString);

					}
					$("#dataTables-shipOrderInfo td").css("WORD_BREAK",
							"BREAK-ALL");
					$('#dataTables-shipOrderInfor_length label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_length label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_length label').css(
							"font-weight", "600");
					$('#dataTables-shipOrderInfo_filter label').css("color",
							"#004c74");
					$('#dataTables-shipOrderInfo_filter label').css(
							"letter-spacing", "1px");
					$('#dataTables-shipOrderInfo_filter').css("text-align",
							"right");
					$('#dataTables-shipOrderInfo_filter label').css(
							"font-weight", "600");
					$('#dataTables-shipOrderInfo_filter label').css("float",
							"right");
					$('#dataTables-shipOrderInfo_filter label').addClass(
							"col-lg-8 col-sm-12 col-md-9");
					$('#dataTables-shipOrderInfo_filter label').css(
							"text-align", "center");
					$('#dataTables-shipOrderInfo_info').css("color", "#004c74");
					$('#dataTables-shipOrderInfo_info').css("font-weight",
							"600");
					$('#dataTables-shipOrderInfo_paginate').css("text-align",
							"right");

					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-shipOrderInfo').removeClass('display')
							.addClass('table table-striped table-bordered');
					if (contains == 0)
						$(
								'#dataTables-shipOrderInfo_filter input[type="search"]')
								.attr('placeholder',
										'Enter Item No. OR Item Desc.').css({
									'width' : '250px',
									'display' : 'inline-block',
									'padding-right' : '1px'
								});
					$('#dataTables-shipOrderInfo_filter input[type="search"]')
							.focus();

					if (order_number.toString().indexOf(inputString) > -1) {
						oTable.draw();
					} else {
						oTable.search(inputString);
						oTable.draw();
					}

				}
			})
}

function openShipToDetailsInfo(shipTo, custNum) {
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
					var cnt = 0;
					if (null != data && undefined != data && data.length == 0) {
						alert("No orders for this location for the last 2 years");
						return;
					}

					var itemCnt = -1;
					var oldItem;
					var orderNumber;
					var itemReturned;
					var orderDate;
					var contactName;
					var noOfItems = 0;
					var orderTotal = 0;

					$
							.each(
									data.orders,
									function(i, item) {

										if (item.recordType != "0003") {

											var items1 = item.orderNumber
													.split('.');
											var items2 = '';
											for (var j = 0; j < items1.length; j++) {
												if (j != 2 && j != 0 && j != 1) {
													items2 += '.' + items1[j];
												} else if (j != 2 && j != 0
														&& j == 1) {
													items2 += '.<span style="color:red;text-decoration:underline !important;font-weight:bold;">'
															+ items1[j]
															+ '</span>';
												} else if (j == 0) {
													items2 += items1[j];
												}
											}

											if (item.contactName == "null null") {
												item.contactName = "";
											}

											orderDate = checkUundefined(item.orderTranDate); 
											if (orderDate != null
													|| orderDate != '') {
												orderDate = orderDate
														.substring(0, 10);
												orderDateArr = orderDate
														.split("-");
												orderDate = orderDateArr[1]
														+ "/" + orderDateArr[2]
														+ "/" + orderDateArr[0];
											}

											var ordertypeDef = '';
											if (item.orderReturned == "true") {
												ordertypeDef = "(R)";
											} else {
												ordertypeDef = "";
											}
											item.totalCoponAmount = parseFloat(
													item.totalCoponAmount)
													.toFixed(2);
											item.totalDiscountAmount = parseFloat(
													item.totalDiscountAmount)
													.toFixed(2);

											var rewardNo = checkUundefined(item.orderIssuer);
											if (rewardNo.length > 0) {
												if (rewardNo
														.charAt(rewardNo.length - 1) == 'P') {
													rewardNo = rewardNo
															.replace('P', 'M');
												}
												if (rewardNo
														.charAt(rewardNo.length - 1) == 'C') {
													rewardNo = rewardNo
															.replace('C', 'S');
												}
											}

											var productSKUs = new Array();
											var productDescs = new Array();
											productSKUObj = item.orderLevelItems;
											if ((undefined != productSKUObj)
													|| (null != productSKUObj)) {
												$
														.each(
																productSKUObj,
																function(i,
																		orderItem) {
																	productSKUs
																			.push(orderItem.sku);
																	productDescs
																			.push(orderItem.productName);
																});
											}

											mkttrHTML += '<tr class="odd gradeX">'

													+ '<td class="datatablesTd">'
													+ rewardNo
													+ '</td>'
													+ '<td class="datatablesTd"><a href="javascript:openOrderDetailsShipTO(\''
													+ checkUundefined(item.orderNumber)
													+ '\',\''
													+ shipTo
													+ '\');" style="text-decoration:underline;padding-right:6px;">'
													+ checkUundefined(items2)
													+ '</a><span style="color:red;">'
													+ ordertypeDef
													+ '</span></td>'
													+ '<td class="datatablesTd">'
													+ orderDate
													+ '</td>'
													+ '<td>'
													+ checkUundefined(Number(item.orderItemsCount))
													+ '</td>' 
													+ '<td class="datatablesTd formatClsOrd">'
													+ formatNum(chkNegAmount(checkUundefined(parseFloat(
															item.tranTotalAmount)
															.toFixed(2))))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ formatNum(chkNegAmount(checkUundefined(item.totalDiscountAmount)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ formatNum(chkNegAmount(checkUundefined(item.totalCoponAmount)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.contactName)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ productSKUs.toString()
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ productDescs.toString()
													+ '</td>' + '</tr>';

											cnt++;
										}
									});

					ShipOrderDeatilCount = cnt - 1;
					$('#ShipDetailsInfoId').html("");
					$('#ship-info').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					$('#ShipDetailsInfoId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-ShipInfo" width="100%" ><thead><tr>'

											+ '<th style="width:101px;">Reward #</th>'
											+ '<th>Order No.</th>'
											+ '<th>Order Date</th>'
											+ '<th>No. Of Items</th>'
											+ '<th>Order Total</th>'
											+ '<th>Discounts</th>'
											+ '<th>Coupons</th>'
											+ '<th>Order Contact</th>'
											+ '<th>productSKUs</th>'
											+ '<th>productDescs</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML
											+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');

					var oTable = $('#dataTables-ShipInfo')
							.DataTable(
									{
										"lengthMenu" : [ [ 5, 10, 15, -1 ],
												[ 5, 10, 15, "All" ] ],
										"jqueryUI" : true,
										"order" : [],
										"oLanguage" : {
											"sSearch" : "Filter: "
										},
										"aoColumns" : [ {
											"bSortable" : true
										}, {
											"bSortable" : true,
											"visible" : true,
											"sWidth" : '11%'
										}, {
											"bSortable" : true,
											"visible" : true,
											"sWidth" : '7%'
										}, {
											"bSortable" : true,
											"sWidth" : '7%'
										}, {
											"bSortable" : true,
											"sWidth" : '7%'
										}, {
											"bSortable" : true,
											"sWidth" : '7%'
										}, {
											"bSortable" : true,
											"sWidth" : '7%'
										}, {
											"bSortable" : true,
											"sWidth" : '15%'
										}, {
											"bSortable" : true,
											"visible" : false,
											"sWidth" : '1%'
										}, {
											"bSortable" : true,
											"visible" : false,
											"sWidth" : '1%'
										} ],
										"fnDrawCallback" : function(oSettings) {

											if (ShipOrderDeatilCount != -1) {
												for (var count = 0; count <= ShipOrderDeatilCount; count++) {
													$('#ShipOrderIdDtl' + count)
															.popover(
																	{
																		html : true,
																		placement : 'right',
																		content : $(
																				"#ShiporderdivId"
																						+ count)
																				.html()
																	})
															.on(
																	"hover",
																	function() {
																		$(
																				'.popover')
																				.addClass(
																						'popoverBasic');
																	})
															.on(
																	'show.bs.popover',
																	function() {

																	});
												}
											}
										}
									});

					$("#dataTables-ShipInfo td").css("WORD_BREAK", "BREAK-ALL");
					$('#dataTables-ShipInfo_length label').css("color",
							"#004c74");
					$('#dataTables-ShipInfo_length label').css("color",
							"#004c74");
					$('#dataTables-ShipInfo_length label').css("font-weight",
							"600");
					$('#dataTables-ShipInfo_filter label').css("color",
							"#004c74");
					$('#dataTables-ShipInfo_filter label').css(
							"letter-spacing", "1px");
					$('#dataTables-ShipInfo_filter').css("text-align", "right");
					$('#dataTables-ShipInfo_filter label').css("font-weight",
							"600");
					$('#dataTables-ShipInfo_filter label')
							.css("float", "right");
					$('#dataTables-ShipInfo_info').css("color", "#004c74");
					$('#dataTables-ShipInfo_info').css("font-weight", "600");
					$('#dataTables-ShipInfo_paginate').css("text-align",
							"right");
					$("#dataTables-ShipInfo_info").parent().removeClass(
							"col-sm-6").addClass("col-sm-4").css(
							"padding-right", "1px");
					$("#dataTables-ShipInfo_paginate").parent().removeClass(
							"col-sm-6").addClass("col-sm-8");
					$('#dataTables-ShipInfo_paginate .pagination>li>a').attr(
							"style", "padding : 6px 6px !important");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-ShipInfo').removeClass('display').addClass(
							'table table-striped table-bordered');
					$("#dataTables-ShipInfo_filter").parent().removeClass(
							"col-sm-6").addClass("col-sm-8");
					$("#dataTables-ShipInfo_length").parent().removeClass(
							"col-sm-6").addClass("col-sm-4");
					$('#dataTables-ShipInfo_filter input[type="search"]').attr(
							'placeholder',
							'Enter Order No. OR Item No. OR Item Desc.').attr(
							"style", "width : 260px !important").css("display",
							"inline-block");
					$('#dataTables-ShipInfo_filter input[type="search"]')
							.focus();
				}

			})
}

function openOrderDetails(isFromReturned) {

	// log user activity; view order list
	logUserActivityDotCom(7004, 'View Order List');

	var Amount = new Array();
	var newItem = new Array();

	if (checkUundefined(latestFiscalDateOrderSAMNew).length == 0) {
		var currentTime = new Date();

		if (latestDate != undefined && latestDate != null) {
			if (checkUundefined(latestDate.fiscalYears)) {
				latestFiscalDateOrderSAMNew = "" + currentTime.getMonth();
				if (latestFiscalDateOrderSAMNew.length == 1) {
					latestFiscalDateOrderSAMNew = "0"
							+ latestFiscalDateOrderSAMNew;
				}
				latestDateforOrder += latestDate.fiscalYears[0];
			}
		} else {
			latestFiscalDateOrderSAMNew = currentTime.getMonth()
					+ currentTime.getYear();
		}

	}

	var monthNew = parseInt(latestFiscalDateOrderSAMNew.substring(0, 2));
	var yearNew = latestFiscalDateOrderSAMNew.substring(2, 6);
	$("#yearSel").val("January 2015");
	var monthNameArr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" ];
	$("#custContent").css("display", "block");
	$("#cusId").css("display", "block");
	$("#superContent").css("display", "block");
	$("#showHideId1").prop('class', 'fa fa-times');
	commonScroll('cusId');
	var formData = {};
	var custid = $("#reqCustNum").val();

	var dt = getCurrentTime();
	$("#updateDateValueOrder span").html(getCurrentDateTime() + " ET");
	var catid = "ALL";
	$("#dataTables-order_processing")
			.html(
					'<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-order_processing").css("display", "block");
	var orderDate = monthNameArr[monthNew - 1].toUpperCase() + "-" + yearNew;

	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid
						+ "/transactions?sbuName=STAPLES.COM&startDate="
						+ orderDate + "&endDate=" + orderDate,
				type : "GET",
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					$('#order-modal').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});

					var itemCnt = -1;
					var oldItem;
					var orderNumber;
					var itemReturned;
					var orderDate;
					var contactName;
					var noOfItems = 0;
					var orderTotal = 0;

					if (data != null) {
						$
								.each(
										data.customerOrders,
										function(i, item) {

											if (item.recordType != "0003") {
												var items1 = item.orderNumber
														.split('.');
												var items2 = '';
												for (var j = 0; j < items1.length; j++) {
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
												
												if (item.contactName == "null null") {
													item.contactName = "";
												}
												orderDate = checkUundefined(item.orderTranDate); 
												if (orderDate != null
														|| orderDate != '') {
													orderDate = orderDate
															.substring(0, 10);
													orderDateArr = orderDate
															.split("-");
													orderDate = orderDateArr[1]
															+ "/"
															+ orderDateArr[2]
															+ "/"
															+ orderDateArr[0];
												}
												var ordertypeDef = '';

												if (item.orderReturned == "true") {
													ordertypeDef = "(R)";
												} else {
													ordertypeDef = "";
												}
												item.totalCoponAmount = parseFloat(
														item.totalCoponAmount)
														.toFixed(2);
												item.totalDiscountAmount = parseFloat(
														item.totalDiscountAmount)
														.toFixed(2);

												var rewardNo = checkUundefined(item.orderIssuer);
												if (rewardNo.length > 0) {
													if (rewardNo
															.charAt(rewardNo.length - 1) == 'P') {
														rewardNo = rewardNo
																.replace('P',
																		'M');
													}
													if (rewardNo
															.charAt(rewardNo.length - 1) == 'C') {
														rewardNo = rewardNo
																.replace('C',
																		'S');
													}
												}

												var productSKUs = new Array();
												var productDescs = new Array();
												productSKUObj = item.orderLevelItems;
												if ((undefined != productSKUObj)
														|| (null != productSKUObj)) {
													$
															.each(
																	productSKUObj,
																	function(i,
																			orderItem) {
																		productSKUs
																				.push(orderItem.sku);
																		productDescs
																				.push(orderItem.productName);
																	});
												}

												mkttrHTML += '<tr class="odd gradeX">'

														+ '<td class="datatablesTd">'
														+ rewardNo
														+ '</td>'
														+ '<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('
														+ cnt
														+ ',\''
														+ checkUundefined(item.orderNumber)
														+ '\');" style="text-decoration:underline;padding-right:6px;">'
														+ checkUundefined(items2)
														+ '</a><span style="color:red;margin-right: -17px;margin-left: -4px;">'
														+ ordertypeDef
														+ '</span></td>'
														+ '<td class="datatablesTd">'
														+ orderDate
														+ '</td>'
														+ '<td>'
														+ checkUundefined(Number(item.orderItemsCount))
														+ '</td>'
														+ '<td class="datatablesTd formatClsOrd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(parseFloat(
																item.tranTotalAmount)
																.toFixed(2))))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(item.totalDiscountAmount)))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ chkNegAmountOrder(chkDollar(checkUundefined(item.totalCoponAmount)))
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ checkUundefined(item.contactName)
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ productSKUs
																.toString()
														+ '</td>'
														+ '<td class="datatablesTd">'
														+ productDescs
																.toString()
														+ '</td>' + '</tr>';

												cnt++;
											}
										});
						OrderDeatilCount = cnt - 1;
					}

					if ((undefined != isFromReturned && isFromReturned == 'Y' && retFound == 'Y')
							|| ('N' == isFromReturned)) {
						populateMonthYearData(data, monthNew, yearNew);
						populateCatData();
						$(
								"#yearSel option[value=" + monthNew + ""
										+ yearNew + "]").attr("selected",
								"selected");
						$('#datepickerTEXT').val(
								monthNameArr[monthNew - 1] + " / " + yearNew);
						$('#datepickerTEXT').css("font-weight", "bold")
						$('#datepickerTEXT').css("font-family", "Hevletica")
						$('#datepickerTEXT').css("font-size", "14px");
						$('#orderDetailsTabId').html("");
						$('#orderDetailsTabId')
								.html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'
												+ '<th>Rewards #</th>'
												+ '<th>Order No.</th>'
												+ '<th>Order Date</th>'
												+ '<th>No. Of Items</th>'
												+ '<th>Order Total</th>'
												+ '<th>Discounts</th>'
												+ '<th>Coupons</th>'
												+ '<th>Order Contact</th>'
												+ '<th>productSKUs</th>'
												+ '<th>productDescs</th>'
												+ '</tr>'
												+ '</thead><tbody>'
												+ mkttrHTML
												+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
						$("#dataTables-order_processing")
								.css("display", "none");
						$('#dataTables-order').dataTable(
								{
									"lengthMenu" : [ [ 5, 10, 30, -1 ],
											[ 5, 10, 30, "All" ] ],
									"order" : [],
									"bProcessing" : true,
									"oLanguage" : {
										"sSearch" : "Filter: ",
										"sWidth" : '11%'
									},
									"aoColumns" : [ {
										"bSortable" : true
									}, {
										"bSortable" : true,
										"visible" : true,
										"sWidth" : '11%'
									}, {
										"bSortable" : true,
										"visible" : true,
										"sWidth" : '7%'
									}, {
										"bSortable" : true,
										"sWidth" : '11%'
									}, {
										"bSortable" : true,
										"sWidth" : '11%'
									}, {
										"bSortable" : true,
										"sWidth" : '11%'
									}, {
										"bSortable" : true,
										"sWidth" : '11%'
									}, {
										"bSortable" : true,
										"sWidth" : '15%'
									}, {
										"bSortable" : true,
										"visible" : false,
										"sWidth" : '1%'
									}, {
										"bSortable" : true,
										"visible" : false,
										"sWidth" : '1%'
									} ],
									"fnDrawCallback" : function(oSettings) {

									}
								}).fnDraw();
					} else if (undefined != isFromReturned
							&& isFromReturned == 'Y' && retFound == 'N') {
						var monthYear = '';
						var catId = "ALL";
						if (month != 1) {
							monthYear = (monthNew - 1) + "" + yearNew;
						} else if (month == 1) {
							monthYear = 12 + "" + (yearNew - 1);
						}
						populateMonthYearData(data, (monthNew - 1), yearNew);
						populateCatData();
						if (monthNew != 12) {
							$(
									"#yearSel option[value=" + (monthNew - 1)
											+ "" + yearNew + "]").attr(
									"selected", "selected");
							$('#datepickerTEXT').val(
									monthNameArr[monthNew - 2] + " / "
											+ yearNew);
						} else {
							$("#yearSel option[value=12" + (yearNew - 1) + "]")
									.attr("selected", "selected");
							$('#datepickerTEXT').val(
									monthNameArr[11] + " / " + (yearNew - 1));
						}

						$('#datepickerTEXT').css("font-weight", "bold")
						$('#datepickerTEXT').css("font-family", "Hevletica")
						$('#datepickerTEXT').css("font-size", "14px");
						onChangeMonthOrCat(monthYear, catId, 'Y');
					}

					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label')
							.css("font-size", "15px");
					$('#dataTables-order_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-order_filter label').css("color", "#004c74");
					$('#dataTables-order_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-order_filter').css("text-align", "right");
					$('#dataTables-order_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-order_filter label').css("float", "right");
					$('#dataTables-order_filter input[type="search"]').css(
							"width", "270px");
					$('#dataTables-order_info').css("color", "#004c74");
					$('#dataTables-order_info').css("font-weight", "600");
					$('#dataTables-order_paginate').css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-order').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-order_filter input[type="search"]').attr(
							'placeholder',
							'Enter Order No. OR Item No. OR Item Desc.').css({
						'width' : '270px',
						'display' : 'inline-block'
					});
				}

			})
	$("#yearSel").val("January 2015");
}

function openCustomerProfile() {
	var formData = {};
	$("#cusId").css("display", "block");
	$("#showHideId1").prop('class', 'fa fa-chevron-down');
}
function openUrl(url) {
	var win = window.open(url, '_blank');
	win.focus();
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

function openShipToDetails() {
	// log user activity; viwe ship to data
	logUserActivityDotCom(7006, 'View Ship To Data');

	var formData = {};
	var dt = geDataRefreshTime('MV_SHIP_TO_INFO');
	$("#updateDateValueShip span").html(getCurrentDateTime() + " ET");
	$("#shipToId").css("display", "block");
	$("#shipToContent").css("display", "block");
	$("#showHideIdShipTo").prop('class', 'fa fa-times');
	commonScroll('shipToId');
	var custid = $("#reqCustNum").val();
	$("#dataTables-exampleShipTo_processing")
			.html(
					'<div id="example_processing_shipto" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-exampleShipTo_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid
						+ "/shipment/info?sbuName=STAPLES.COM",
				type : "GET",
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					$('#my-modal').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});

					var cnt = 0;
					var mkttrHTML = "";
					$('#shipToTableId').html("");
					var name = '';
					if (data != null) {
						$
								.each(
										data.shipments,
										function(i, item) {

											var shiptoAddress = shiptoAddress = checkUundefined(item.shiptoAddress1);

											var YOY = '';
											if (checkUundefined(item.yoy).length > 0) {
												YOY = checkUundefined(item.yoy)
														+ "%"
											}
											mkttrHTML += '<tr class="odd gradeX">'
													+ '<td class="datatablesTd">'
													+ '<a  style="padding-right:8px;text-decoration:underline" href="javascript:openShipToDetailsInfo(\''
													+ shiptoAddress
													+ '\','
													+ custid
													+ ')" '
													+ '>'
													+ shiptoAddress
													+ '</a><i id="shipToIdDtl'
													+ cnt
													+ '"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="divId'
													+ cnt
													+ '" class="toolTip">';
											if (checkUundefined(item.shiptoAddress1) != 'STORE ORDERS') {
												mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'
														+ checkUundefined(item.shiptoAddress1)
														+ '</div>'
														+ '<div style="font-size:12px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'
														+ checkUundefined(item.shipToCity)
														+ '</div>'
														+ '<div style="font-size:12px;padding-bottom:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'
														+ checkUundefined(item.shipToState)
														+ '-'
														+ checkUundefined(item.shipToZip)
														+ '</div>';
											} else {
												mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">STORE ORDERS</div>';
											}
											mkttrHTML += '</div></td>'
													+ '<td class="datatablesTd">'
													+ checkUserGridFields(item.cfy_order_count)
													+ '</td>'
													+ '<td class="datatablesTd formatClsShip">'
													+ chkNegAmount(formatNumUserGrid(checkUundefined(item.currentFYS)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUserGridFields(item.lfy_order_count)
													+ '</td>'
													+ '<td class="datatablesTd formatClsShip">'
													+ chkNegAmount(formatNumUserGrid(checkUundefined(item.previousFYS)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ YOY + '</td>'

													+ '</tr>';

											cnt++;
										});
						ShipToDeatilCount = cnt - 1;
					}
					$('#shipToTableId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-exampleShipTo"  ><thead><tr>'
											+ '<th>Ship To Id</th>'
											+ '<th>No of Orders <br/>(CFY)</th>'
											+ '<th>$ Spent <br/>(CFY)</th>'
											+ '<th>No of Orders <br/>(LFY)</th>'
											+ '<th>$ Spent <br/>(LFY) </th>'
											+ '<th>YOY % <br/>Change</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML + '</tbody></table>');
					$("#dataTables-exampleShipTo_processing").css("display",
							"none");
					$('#dataTables-exampleShipTo')
							.dataTable(
									{
										"lengthMenu" : [ [ 5, 15, 25, -1 ],
												[ 5, 15, 25, "All" ] ],

										"bSort" : true,
										"order" : [],
										"bProcessing" : true,
										"oLanguage" : {
											"sSearch" : "Filter: "
										},
										"aoColumns" : [ {
											"bSortable" : true
										}, {
											"bSortable" : true
										}, {
											"bSortable" : true
										}, {
											"bSortable" : true
										}, {
											"bSortable" : true
										}, {
											"bSortable" : true
										}

										],
										"fnDrawCallback" : function(oSettings) {

											if (ShipToDeatilCount != -1) {
												for (var count = 0; count <= ShipToDeatilCount; count++) {
													$('#shipToIdDtl' + count)
															.popover(
																	{
																		html : true,
																		placement : 'right',
																		content : $(
																				"#divId"
																						+ count)
																				.html()
																	})
															.on(
																	"hover",
																	function() {
																		$(
																				'.popover')
																				.addClass(
																						'popoverBasic');
																	})
															.on(
																	'show.bs.popover',
																	function() {

																	});
												}
											}
										}
									}).fnDraw();
					$('#dataTables-exampleShipTo_length label').css("color",
							"#004c74");
					$('#dataTables-exampleShipTo_length label').css("color",
							"#004c74");
					$('#dataTables-exampleShipTo_length label').css(
							"font-family", "helveticaneuebold");
					$('#dataTables-exampleShipTo_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-exampleShipTo_filter label').css("color",
							"#004c74");
					$('#dataTables-exampleShipTo_filter label').css(
							"letter-spacing", "1px");
					$('#dataTables-exampleShipTo_filter').css("text-align",
							"right");
					$('#dataTables-exampleShipTo_filter label').css(
							"font-family", "helveticaneuebold");
					$('#dataTables-exampleShipTo_filter label')
							.children()
							.attr("style",
									"border-radius:3px !important;color:#004c74;");
					$('#dataTables-exampleShipTo_filter label').css("float",
							"right");
					$('#dataTables-exampleShipTo_info').css("color", "#004c74");
					$('#dataTables-exampleShipTo_info').css("font-weight",
							"600");
					$('#dataTables-exampleShipTo_paginate').css("text-align",
							"right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-exampleShipTo').removeClass('display')
							.addClass('table table-striped table-bordered');
					$('#dataTables-exampleShipTo_filter input[type="search"]')
							.attr('placeholder', 'Enter Ship To Id').css({
								'width' : '250px',
								'display' : 'inline-block'
							});

				}

			})

}

function displayTooltip(count) {
	document.getElementById('divId' + count).style.display = 'block';
}
function hideTooltip(count) {
	document.getElementById('divId' + count).style.display = 'none';
}

function displayOrderContactTooltip(count) {
	document.getElementById('orderContact' + count).style.display = 'block';
}

function hideOrderContactTooltip(count) {
	document.getElementById('orderContact' + count).style.display = 'none';
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

function formatNumUserGrid(n) {
	if (n != '' && n != '0') {
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
function calculateYOY(n, n1) {

	if ((n == '' || n == '0') && (n1 != '' && n1 != '0')) {
		var yoyVal = -100;
		return yoyVal + '%';
	} else if ((n1 == '' || n1 == '0') && (n != '' && n != '0')) {
		var yoyVal = '';

		return yoyVal;
	} else if ((n1 != '' && n1 != '0') && (n != '' && n != '0')) {
		var yoyVal = (n - n1) / n1;
		yoyVal = yoyVal * 100;
		yoyVal = Math.round(yoyVal * 100) / 100;
		var t = -100;
		if (yoyVal < 0 && parseFloat(n) < parseFloat(n1) && (yoyVal > -100)) {
			
			return yoyVal + '%';
		} else if (yoyVal < 0) {
			yoyVal = 100;
			return '-' + yoyVal + '%';
		} else if (yoyVal > 100) {
			yoyVal = 100
			return '+' + yoyVal + '%';
		} else if (yoyVal > 0) {
			return '+' + yoyVal + '%';
			;
		}

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
	if ((undefined != val) && ('null' != val) && (null != val) && ('0' != val)
			&& (0 != val) && ('' != val) && ('-' != val)) {
		return val;
	} else {
		return "";
	}
}

function submitOnEnter(e, searchText, acctId) {
	var keycode;
	if (window.event)
		keycode = window.event.keyCode;
	else if (e)
		keycode = e.which;
	else
		return true;
	if (keycode == 13) {
		searchCustomer(searchText, acctId);
		return false;
	}
}

function searchCustomer(searchText, acctId) {
	showProgress();
	if (undefined == searchText || '' == searchText) {
		hideProgress();
		alert("Please enter the search criteria.");
		return;
	}
	var formData = {
		'searchText' : searchText,
		'acctId' : acctId,
		'custNum' : $('#query').val()
	};
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getCustomerNumber",
				type : "POST",
				cache : false,
				data : formData,
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					if (data != null && data != undefined && data != 'failure') {
						if (data.indexOf("-") != -1) {
							assignGrp = data.split("-")[1];
							data = data.split("-")[0];
							if (undefined != assignGrp && "" != assignGrp
									&& 'SAM' == assignGrp) {
								opencustDetailsLead(data);
							} else if (undefined != assignGrp
									&& "" != assignGrp && 'SAMD' == assignGrp) {
								opencustDetailsSAM(data);
							} else {
								opencustDetailsMM(data);
							}
						}

					} else {
						hideProgress();
						alert("To search for a different customer on this screen the customer must be part of your Book of Business. Please enter the complete company name or customer number. To search for a different customer with partial information please do so via the To Do list.");
					}
					logUserActivityDotCom(7045,
							'User has perform global search from SAM dashboard');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

					var cooki = document.cookie.split(';');

					for (var i = 0; i < cooki.length; i++) {
						var cookival = cooki[i].toString();
						if (cookival.indexOf('SMSESSION=LOGGEDOFF') > -1) {

							window.location.reload();
						}
					}

				}

			});
}

function opencustDetails(cNum) {

	$("#customerForm").attr("action", "./home_template4")
	$("#reqCustNum").val(cNum);
	$('#customerForm').submit();
	generateLogs('SAMcustFromTodolist');
}
function populateMonthChartData(firstYearData, secondYearDatadata,
		thirdYearData, yearsArr) {

	var yr = $("#yrId").val();
	$("#mnthTitle")
			.html(
					'<span>'
							+ yr
							+ ' Yearly Spend Per Month <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
	var firstYearAmntArr = new Array();
	var secondYearAmntArr = new Array();
	var thirdYearAmntArr = new Array();
	var monthArr = [ "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug",
			"Sept", "Oct", "Nov", "Dec" ];
	var bardata = [];
	var i;
	if (undefined == firstYearData.barDataSet
			|| null == firstYearData.barDataSet
			|| '' == firstYearData.barDataSet) {
		firstYearAmntArr = [ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0" ];
	} else {
		i = 0;
		$.each(((firstYearData.barDataSet).data).rows, function(i, item) {
			firstYearAmntArr[i] = item.values;
			i++;
		});
		var firstYearArrLen = firstYearAmntArr.length;
		for (var j = firstYearArrLen; j < monthArr.length; j++) {
			firstYearAmntArr[j] = 0;
		}
	}
	if (undefined == secondYearDatadata.barDataSet
			|| null == secondYearDatadata.barDataSet
			|| '' == secondYearDatadata.barDataSet) {
		secondYearAmntArr = [ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0" ];
	} else {
		i = 0;
		$.each(((secondYearDatadata.barDataSet).data).rows, function(i, item) {
			secondYearAmntArr[i] = item.values;
			i++;
		});
		var secondYearArrLen = secondYearAmntArr.length;
		for (var j = secondYearArrLen; j < monthArr.length; j++) {
			secondYearAmntArr[j] = 0;
		}
	}
	if (undefined == thirdYearData.barDataSet
			|| null == thirdYearData.barDataSet
			|| '' == thirdYearData.barDataSet) {
		thirdYearAmntArr = [ "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0" ];
	} else {
		i = 0;
		$.each(((thirdYearData.barDataSet).data).rows, function(i, item) {
			thirdYearAmntArr[i] = item.values;
			i++;
		});
		var thirdYearArrLen = thirdYearAmntArr.length;
		for (var j = thirdYearArrLen; j < monthArr.length; j++) {
			thirdYearAmntArr[j] = 0;
		}
	}

	bardata = getMonthAmountMapForThreeYears(monthArr, firstYearAmntArr,
			secondYearAmntArr, thirdYearAmntArr);
	var chart = AmCharts.makeChart("chartdiv", {
		"type" : "serial",
		"theme" : "light",
		"legend" : {
			"useGraphSettings" : true,
			"clickMarker" : handleLegendClick,
			"clickLabel" : handleLegendClick
		},
		"dataProvider" : bardata,
		"startDuration" : 0.5,
		"graphs" : [
				{
					"balloonText" : "Year: " + yearsArr[0]
							+ ", [[category]]: $[[value]]",
					"title" : yearsArr[0],
					"type" : "column",
					"colorField" : "color",
					"fillAlphas" : 0.85,
					"lineAlpha" : 0.1,
					"topRadius" : 1,
					"valueField" : "year1"
				},
				{
					"balloonText" : "Year: " + yearsArr[1]
							+ ", [[category]]: $[[value]]",
					"bullet" : "round",
					"title" : yearsArr[1],
					"lineColor" : "red",
					"type" : "line",
					"valueField" : "year2",
					"fillAlphas" : 0
				},
				{
					"balloonText" : "Year: " + yearsArr[2]
							+ ", [[category]]: $[[value]]",
					"bullet" : "round",
					"title" : yearsArr[2],
					"valueField" : "year3",
					"type" : "line",
					"lineColor" : "green",
					"fillAlphas" : 0
				} ],
		"depth3D" : 30,
		"angle" : 50,
		"chartCursor" : {
			"cursorAlpha" : 0,
			"zoomable" : false
		},
		"categoryField" : "month",
		"categoryAxis" : {
			"gridPosition" : "start",
			"axisAlpha" : 0,
			"fillAlpha" : 0.05,
			"fillColor" : "#000000",
			"gridAlpha" : 0,
			"position" : "bottom"
		},
		"export" : {
			"enabled" : true,
			"position" : "bottom-right"
		}
	});

	$("g text[text-anchor=end]").each(function(i, item) {
		var htmlData = $(this).html();
		if (undefined == htmlData || '' == htmlData || ' ' == htmlData) {
			$(this).remove();
		}
	})
	$("g path[fill=#67b7dc]").css("display", "none");
	$("g text[text-anchor=end] tspan").each(
			function(i, item) {
				var htmlData = $(this).html();
				if (undefined != htmlData && '' != htmlData && ' ' != htmlData
						&& htmlData.indexOf("$") == -1) {
					$(this).html('$' + htmlData);
				}
			});
}
function handleLegendClick(graph) {
	var chart = graph.chart;
	for (var i = 0; i < chart.graphs.length; i++) {
		if (graph.id == chart.graphs[i].id) {
			if (chart.graphs[i].hidden == false) {
				chart.hideGraph(chart.graphs[i]);
			} else
				chart.showGraph(chart.graphs[i]);
		} else {
			if (chart.graphs[i].hidden == false) {
				chart.showGraph(chart.graphs[i]);
			}
		}
	}
	$("g text[text-anchor=end]").each(function(i, item) {
		var htmlData = $(this).html();
		if (undefined == htmlData || '' == htmlData || ' ' == htmlData) {
			$(this).remove();
		}
	})
	$("g path[fill=#67b7dc]").css("display", "none");
	$("g path[fill=#AAB3B3]").css("display", "none");
	$("g text[text-anchor=end] tspan").each(
			function(i, item) {
				var htmlData = $(this).html();
				if (undefined != htmlData && '' != htmlData && ' ' != htmlData
						&& htmlData.indexOf("$") == -1) {
					$(this).html('$' + htmlData);
				}
			});
	return false;
}

function getLatestFiscalDate() {

	var custid = $("#reqCustNum").val();
	var formData = {};
	$.ajax({
		dataType : "json",
		url : ctx + "/sales/customer/" + custid + "/fiscalyears/list",
		type : "GET",
		data : "",
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (data != null && data != undefined) {
				latestDate = data;
				var yearDropDown = $("#yrId")
				yearDropDown.empty();
				var fiscalYears = checkUundefined(latestDate.fiscalYears);
				if (fiscalYears.length > 0) {

					for (var i = 0; i < 2; i++) {
						if (i == 0)
							latestFiscalYearSam = parseInt(fiscalYears[i]);
						$('<option>', {
							value : fiscalYears[i],
							text : fiscalYears[i]
						}).appendTo("#yrId");

					}
				}

				latestDate = data;
				populateDataOnLoad(data, 'RETAIL');
				monthAnalysisForSam();

			}
		}

	})

}
function getLatestFiscalDateOrder() {

	var custid = $("#reqCustNum").val();

	var formData = {};
	$.ajax({
		dataType : "json",
		url : ctx + "/getLatestFiscalDateOrder/" + custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			if (data != null && data != undefined) {
				latestDateforOrder = data;
			}
		}

	})
	return latestDateforOrder;

}

function getLatestOrderReturnedDate() {

	var custid = $("#reqCustNum").val();
	var latestOrderReturnedDate = "";
	var latestOrdDate = "";
	var retmatches = '', ordmatches = '';
	var formData = {};
	$.ajax({
		dataType : "json",
		url : ctx + "/getLatestOrderReturnedDate/" + custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			hideProgress();
			if (data != null && data != undefined && data != 'null') {
				latestOrderReturnedDate = data;
				if (data.indexOf("/") != -1) {
					if (null != latestOrderReturnedDate) {
						retmatches = latestOrderReturnedDate.match(/\d+/g);
						if (retmatches != null) {
							var dateArr = latestOrderReturnedDate.split("/");
							var retmnth = dateArr[0];
							var retyear = dateArr[2];
							monthYear = retmnth + retyear;
							$("#retId").attr(
									'href',
									'javascript:openReturn(\'' + monthYear
											+ '\',\'ALL\',\'Y\')');
						} else {
							$("#retId").removeAttr("href");
							$("#retId").css({
								"text-decoration" : "none",
								"cursor" : "default",
								"color" : "#0066c0"
							});
						}
					} else {
						$("#retId").removeAttr("href");
						$("#retId").css({
							"text-decoration" : "none",
							"cursor" : "default",
							"color" : "#0066c0"
						});
					}
				}
			} else {
				$("#retId").removeAttr("href");
				$("#retId").css({
					"text-decoration" : "none",
					"cursor" : "default",
					"color" : "#0066c0"
				});
			}
		}

	})
	return latestOrderReturnedDate;

}
function getMonth(month) {
	var Months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	return Months[parseInt(month)];
}

function currencyFormat(num) {
	var val = '';
	if (undefined != num && '' != num && null != num) {
		val = "$"
				+ (parseFloat(num)).toFixed(2).replace(
						/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
		return val;
	} else
		return val;
}

function openRecommendations() {
	$("#customerForm").attr("action", "./recommActionSAMNew")
	$('#customerForm').submit();
}

function openStoreLocator() {
	$("#customerForm").attr("action", "./storeLocActionSamNew")
	$('#customerForm').submit();
}

function opeMaintainRole() {
	$("#customerForm").attr("action", "./maintainAction")
	$('#customerForm').submit();
}

function populateQuickSearchData() {
	var formData = {};
	var selPlayType = $("#filterBy").val();
	var sliderSubPlaysItem = $("#sliderSubPlaysItem").val();
	var custid = $("#accId").val();
	var selectedQualScore = $("#selectedQualScore").val();
	var selectedSegScore = $("#selectedSegScore").val();
	configCookie('dashboard');
	var len = parseInt(quickSearchStart)
	$('#dataTables-QuickSearch')
			.dataTable(
					{

						"bDestroy" : true,
						"oLanguage" : {
							"sSearch" : "Filter: "
						},
						"sAjaxSource" : ctx + "/sales/representive/" + custid
								+ "/customer/list",
						"fnServerParams" : function(aoData) {
							aoData.push({
								"name" : "selectedSubPlays",
								"value" : sliderSubPlaysItem
							});
							aoData.push({
								"name" : "selectedQualScore",
								"value" : selectedQualScore
							});
							aoData.push({
								"name" : "selectedSegScore",
								"value" : selectedSegScore
							});
							aoData.push({
								"name" : "repRoleCode",
								"value" : ""
							});
							aoData.push({
								"name" : "accType",
								"value" : "NA"
							});
						},
						"processing" : true,
						"responsive" : true,
						"iDisplayLength" : 5,
						"iDisplayStart" : len,
						"pageLength" : 5,
						"lengthChange" : false,
						"paging" : true,
						"pagingType" : "simple_numbers",
						"info" : true,
						"ordering" : false,
						"searching" : false,
						"dataType" : 'jsonp',
						'sServerMethod' : "POST",
						'bStateSave' : true,
						"sAjaxDataProp" : "customerInfo",
						"fnDrawCallback" : function(oSettings) {
							$("#dataTables-QuickSearch tr th").hide();

						},
						"columnDefs" : [ {
							className : "dt-head-center"
						} ],
						"aoColumns" : [ {
							"mData" : null,
							"mRender" : function(data, type, full) {

								return '<li class="media" onclick="opencustDetails('
										+ full.customerNo
										+ ')">'
										+ '<div class="media-status">'
										+ '<span class="badge badge-warning">'
										+ checkUundefinedNullBlankZero(full.account_rank)
										+ '</span>'
										+ '</div>'
										+ '<div class="media-body" id="custInfoId_'
										+ full.account_rank
										+ '">'
										+ '<h4 class="media-heading" style="font-family:Helvetica;font-weight:bold;font-size:14px; color: #ddd; ">'
										+ checkUundefined(full.customerNo)
										+ '</h4>'
										+ '<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'
										+ checkUundefined(full.tier)
										+ '</div>'
										+ '<div class="media-heading" style="font-family:Helvetica;font-weight:bold; color: #ddd; ">'
										+ checkUundefined(full.companyName)
										+ '</div>' + '</div>' + '</li>';
							}
						} ]

					});
}

function showTrainingPopUp() {

	$('#training_modal_sam').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}
function showSfdcPopUp(url) {
	$("#sfdcConfUrl").val("");
	if (undefined != url && '' != url)
		url = url.replace(/@/g, "%40");
	$("#sfdcConfUrl").val(url);
	$('#sfdcConfModal').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}

function showFaqPopUp() {
	$('#faq_modal').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}
function showCallToActionPopUp() {
	$('#callToAction_PopUp').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}
function showEditPopUp(editId) {
	$('#' + editId).modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}

function clearCookie() {
	$.cookie('sidebar_closed', '', {
		path : '/',
		expires : -1
	});
	$.cookie('sidebar_closed', '', {
		path : '/ContractDasbhoard',
		expires : -1
	});
	$.cookie('sidebar_closed', '', {
		path : '/ContractDashboard',
		expires : -1
	});
}
function formatZip(val) {
	if (undefined != val && '' != val && val.length > 5) {
		val = val.substring(0, 5);
	} else if (undefined != val && '' != val && val.length < 5) {
		val = pad(val, 5);
	}
	return val;
}
function pad(n, width, z) {
	z = z || '0';
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}
function formatPhone(phoneNumber) {
	if (phoneNumber.indexOf('(') != -1) {
		phoneNumber = phoneNumber.slice(1).replace(")", ".").replace("-", ".")
				.replace(" ", "");
	} else {
		phoneNumber = phoneNumber.replace(/-/g, ".");
	}
	return phoneNumber;
}
function formatYtd() {
	var maxDigToFormat = '';
	var oldarr = new Array();
	var newarr = new Array();
	for (var i = 0; i < 3; i++) {
		if ((undefined != ($("#spend" + i).html()))
				&& ('' != ($("#spend" + i).html()))) {
			oldarr[i] = ($("#spend" + i).html()).substr(1);
			newarr[i] = ($("#spend" + i).html()).substr(1);
		}
	}
	var maxlenele = newarr.sort(function(a, b) {
		return b.length - a.length
	})[0];
	
	for (cnt = 0; cnt < oldarr.length; cnt++) {
		if (undefined != maxlenele) {
			$("#spend" + cnt)
					.html(
							$.parseHTML(customYtdFormat(oldarr[cnt],
									maxlenele.length)));
			
		}
	}
}
function customYtdFormat(n, width, z) {
	z = z || '&nbsp;&nbsp;';
	n = n + '';
	var v = (n.length >= width ? n : new Array(width - n.length + 1).join(z)
			+ n);
	if (width > 6 & v.indexOf(",") == -1)
		v = v.slice(12);
	return "$" + v;
}
function removeUnderScore(val) {
	if (undefined != val && '' != val) {
		val = val.replace(/_/g, " ");
	}
	return val;
}
function toCamCase(str) {
	return str.replace(/\w\S*/g, function(txt) {
		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
	});
}
function formatOrderAmt(n) {
	if (n != '') {
		var commaFormatedVal = n.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g,
				"$1,");
		if (commaFormatedVal.indexOf(".") != -1) {
			if (commaFormatedVal.split(".")[0] == '') {
				commaFormatedVal = '0.' + commaFormatedVal.split(".")[1];
			}
			if ((commaFormatedVal.split(".")[1]).length == 1) {
				commaFormatedVal = commaFormatedVal.split(".")[0] + "."
						+ commaFormatedVal.split(".")[1] + '0';
			}
		} else {
			commaFormatedVal = commaFormatedVal;
		}
		return commaFormatedVal.replace("$", "");
	} else {
		return '';
	}
}
function showHideAcIac() {
	var $Ac = $('#managerInfo').find("#dtl1");
	var $Iac = $('#managerInfo').find("#dtl2");
	if ($Iac.is(":visible") == false) {
		$Iac.slideDown();
		$Ac.slideUp();
		$("#viewRepBtn").html("View AC Details &raquo;");
	} else {
		$Iac.slideUp();
		$Ac.slideDown();
		$("#viewRepBtn").html(
				"View " + $("#dtl2").find("#repVal").html()
						+ " Details &raquo;");
	}
}
function retFoundChk(val) {
	if ((undefined != val) && ('' != val) && (val == 'X')) {
		retFound = 'Y';
	}
	return val;
}
function openOrderDetailsForReturned(month, Yr, catid) {
	// log user activity; view order list
	logUserActivityDotCom(7004, 'View Order List');
	var Amount = new Array();
	var monthYr = month + "" + Yr;
	var formData = {};
	var custid = $("#reqCustNum").val();
	var monthNameArr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" ];
	$("#dataTables-order_processing")
			.html(
					'<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-order_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getOrderDetailsHighLevelData/" + custid + "/"
						+ monthYr + "/" + catid + "/" + "OnChange",
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					if (data.objPurchaseDetailsListVO != null
							&& data.objPurchaseDetailsListVO != undefined) {
						$
								.each(
										data.objPurchaseDetailsListVO,
										function(i, item) {
											objPurchaseDetailsListVO[cnt] = item;
											var itemSku = '';
											var itemSkuDesc = '';
											$
													.each(
															item.purchRwdsDtlListVO,
															function(i,
																	purchase) {
																itemSku = itemSku
																		+ purchase.skuNumber
																		+ ',';
																itemSkuDesc = itemSkuDesc
																		+ purchase.itemDescription
																		+ ',';
															});

											$.each(item.savingAmount, function(
													j, amount) {
												Amount[j] = amount;
											});

											mkttrHTML += '<tr class="odd gradeX">'

													+ '<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('
													+ cnt
													+ ',\''
													+ checkUundefined(item.orderNumber)
													+ '\');"  style="text-decoration:underline;padding-right:6px;">'
													+ checkUundefined(item.orderNumber)
													+ '</a>';

											if (item.savingCategory != undefined
													&& item.savingCategory != null
													&& item.savingCategory.length > 0) {
												mkttrHTML += '<i id="OrderIdDtl'
														+ cnt
														+ '"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="orderdivId'
														+ cnt
														+ '" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
												$
														.each(
																item.savingCategory,
																function(k,
																		category) {
																	if ((checkUundefined(category)) == 'LOR')
																		category = 'Large Order Rebate';
																	mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'
																			+ toCamCase(removeUnderScore(checkUundefined(category)))
																			+ ': $'
																			+ formatOrderAmt(checkUundefined(Amount[k]))
																			+ '</div>'

																});
												mkttrHTML += '</div>';
											}
											mkttrHTML += '</td>';
											mkttrHTML += '<td class="datatablesTd">';
											var returnedCount = 0;
											$
													.each(
															item.purchRwdsDtlListVO,
															function(i,
																	purchase) {
																if (purchase.netSpendAmount < 0) {
																	returnedCount++;

																	if (returnedCount == 1) {
																		var ordnum = checkUundefined(item.orderNumber);
																		mkttrHTML += ('<a href="javascript:openReturnedOrderDetailsInfo('
																				+ cnt
																				+ ',\''
																				+ ordnum + '\');" style="text-decoration:underline;padding-right:6px;">X</a>');
																	}
																}
															});
											mkttrHTML += '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.tranDate)
													+ '</td>'
													+ '<td>'
													+ checkUundefined(Number(item.orderLineCount)
															- returnedCount)
													+ '</td>'
													+ '<td class="datatablesTd formatClsOrd">'
													+ chkNegAmount(formatNum(checkUundefined(item.netSpendAmount)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.orderContact)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(itemSku)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(itemSkuDesc)
													+ '</td>' + '</tr>';

											cnt++;
										});
						OrderDeatilCount = cnt - 1;
					}
					$('#orderDetailsTabId').html("");
					$('#orderDetailsTabId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'

											+ '<th>Order No.</th>'
											+ '<th style="color: red">RETURNED</th>'
											+ '<th>Order Date</th>'
											+ '<th>No. Of Items</th>'
											+ '<th>Order Total</th>'
											+ '<th>Order Contact</th>'
											+ '<th>Item SKU</th>'
											+ '<th>Item Desc</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML
											+ '</tbody></table><div style="color:rgb(0, 76, 116)"><span style="color:red">* </span>The store number for POS transactions is displayed in red within the Order No.</div>');
					$("#dataTables-order_processing").css("display", "none");

					$("#yearSel option[value=" + month + "" + Yr + "]").attr(
							"selected", "selected");
					$('#datepickerTEXT').val(
							monthNameArr[month - 1] + " / " + Yr);
					$('#datepickerTEXT').css("font-weight", "bold")
					$('#datepickerTEXT').css("font-family", "Hevletica")
					$('#datepickerTEXT').css("font-size", "14px");
					$('#dataTables-order')
							.dataTable(
									{
										"lengthMenu" : [ [ 5, 10, 30, -1 ],
												[ 5, 10, 30, "All" ] ],
										"order" : [],
										"bProcessing" : true,
										"oLanguage" : {
											"sSearch" : "Filter: "
										},
										"aoColumns" : [ {
											"bSortable" : true
										}, {
											"bSortable" : true,
											"visible" : true
										}, {
											"bSortable" : true,
											"bSearchable" : false
										}, {
											"bSortable" : true,
											"bSearchable" : false
										}, {
											"bSortable" : true,
											"bSearchable" : false
										}, {
											"bSortable" : true,
											"bSearchable" : true
										}, {
											"bSortable" : true,
											"visible" : false
										}, {
											"bSortable" : true,
											"visible" : false
										} ],
										"fnDrawCallback" : function(oSettings) {

											if (OrderDeatilCount != -1) {
												for (var count = 0; count <= OrderDeatilCount; count++) {
													$('#OrderIdDtl' + count)
															.popover(
																	{
																		html : true,
																		placement : 'right',
																		content : $(
																				"#orderdivId"
																						+ count)
																				.html()
																	})
															.on(
																	"hover",
																	function() {
																		$(
																				'.popover')
																				.addClass(
																						'popoverBasic');
																	})
															.on(
																	'show.bs.popover',
																	function() {

																	});
												}
											}
										}
									}).fnDraw();

					$("#dataTables-order td").css("padding-left", "10px");
					$("#dataTables-order td").css("WORD_BREAK", "BREAK-ALL");
					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label')
							.css("font-size", "15px");
					$('#dataTables-order_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-order_filter label').css("color", "#004c74");
					$('#dataTables-order_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-order_filter').css("text-align", "right");
					$('#dataTables-order_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-order_filter label').css("float", "right");
					$('#dataTables-order_filter input[type="search"]').css(
							"width", "270px");
					$('#dataTables-order_info').css("color", "#004c74");
					$('#dataTables-order_info').css("font-weight", "600");
					$('#dataTables-order_paginate').css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-order').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-order_filter input[type="search"]').attr(
							'placeholder',
							'Enter Order No. OR Item No. OR Item Desc.').css({
						'width' : '270px',
						'display' : 'inline-block'
					});
				}

			})

}
function openOrderDetailsForReturn() {
	retFound = 'N';
	openOrderDetails('Y');
}

function fetchSbaDiffDetails(custid) {
	var formData = {};
	var dt = geDataRefreshTime('ADOPTION_METRICS');
	$("#updateDateValueSBASum span").html(dt + " ET");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getSbaDiffDetails/" + custid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if (data != null && data != undefined) {
						var htmlData = "";
						if ((data.bopisFlag != 'NOTFOUND' && data.printsvsFlag != 'NOTFOUND')
								|| (data.bopisFlag != 'NOTFOUND')
								|| (data.printsvsFlag != 'NOTFOUND')) {
							htmlData = htmlData
									+ ' <table id="gitBuilds" class="table table-bordered">'
									+ '<thead><!-- <tr id="head"> <th colspan="4">Staples Advantage Differentiators</th> </tr> -->'
									+ ' <tr id="subhead">'
									+ '    <th  class="text-center">Feature</th>'
									+ '     <th class="text-center">Account</th>'
									+ '     <th>Feature</th>'
									+ '     <th>Account</th>'
									+ ' </tr></thead>'

									+ '<tbody><tr style=" background-color: #fff;">'
									+ ' <td>'
									+ '   <a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Use for same day or emergency needs" style="text-decoration:none;color:#0066c0;">BOPiS</a></td>';
							if (data.bopisFlag != '') {
								htmlData = generateCell(data.bopisFlag,
										htmlData, 'bopis');
							} else {
								htmlData = htmlData + '<td></td>';
							}
							htmlData = htmlData
									+ '<td><a id="retId" href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Know the status- Submit and track returns easily" style="text-decoration:underline;">Online Returns</a></td>';
							if (data.returnFlag != '' && data.returnFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.returnFlag != ''
									&& data.returnFlag == 'N') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}
							htmlData = htmlData
									+ '</tr>'
									+ '<tr>'
									+ '    <td><a id="shopId" class="shoplist" href="javascript:openOrderDetailsShopping(\'N\');" data-placement="left" data-toggle="tooltip" title="Make it easy for customers to reorder favorite items" style="text-decoration:underline;">Shopping Lists</a></td>';
							if (data.shoppingListFlag != ''
									&& data.shoppingListFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.shoppingListFlag != ''
									&& data.shoppingListFlag == 'N') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}

							htmlData = htmlData
									+ '   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="All customized print & promotional items easily ordered online" style="text-decoration:none;color:#0066c0;">Print Services</a></td>';
							if (data.printsvsFlag != '') {
								htmlData = generateCell(data.printsvsFlag,
										htmlData, 'print');
							} else {
								htmlData = htmlData + '<td></td>';
							}
							htmlData = htmlData
									+ '</tr>'
									+ '<tr style="background-color: #fff;">'
									+ '   <td><a id="mob" style="text-decoration:none;color:#0066c0;cursor:default;" href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Take <u>SA.com</u> anywhere with the mobile app! Customers can download the free app for easy approval and easy re-ordering! (not available for punchout or eDiversity)'
									+ '">Mobile</a></td>';
							if (data.mobileFlag != '' && data.mobileFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.mobileFlag != ''
									&& data.mobileFlag == 'N') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}

							htmlData = htmlData
									+ '   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Easy customer friendly functionality to update their account" style="text-decoration:none;color:#0066c0;">Account Maintenance</a></td>';
							if (data.orderMgmtFlag == 'Y'
									|| data.adminFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.orderMgmtFlag == 'N'
									&& data.adminFlag == 'N') {
								htmlData = htmlData
										+ '<td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}

							htmlData = htmlData
									+ '</tr>'
									+ '<tr>'
									+ '   <td><a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Product recommendations based on previous purchase history" style="text-decoration:none;color:#0066c0;">SA.com Recommendations</a></td>';
							if (data.recomFlag != '' && data.recomFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.recomFlag != ''
									&& data.recomFlag == 'N') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}

							htmlData = htmlData
									+ '   <td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Save more with <u>SA.com</u> Easy Alternatives" style="text-decoration:none;color:#0066c0;">Product Alternatives</a></td>';
							if (data.productFlag != ''
									&& data.productFlag == 'Y') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Check.png" class="img-circle" style="height:30px;" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>';
							} else if (data.productFlag != ''
									&& data.productFlag == 'N') {
								htmlData = htmlData
										+ '   <td><img src="./resources/img/Cross.png" class="img-circle" style="height:30px;" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>';
							} else {
								htmlData = htmlData + '<td></td>';
							}

							htmlData = htmlData
									+ '</tr>'
									+ '</tbody>'
									+ '</table>'
									+ '<div style="font-size: 13px; color: rgb(0, 76, 116);">'
									+ '<span style="color: red">* </span> Flags are based on the '
									+ 'previous 3 periods and refreshed weekly.'
									+ '</div>';
							$("#saContent").html("");
							$("#saContent").html(htmlData);
							if (data.returnFlag != '' && data.returnFlag == 'Y') {
								getLatestOrderReturnedDate();
							} else {
								$("#retId").removeAttr("href");
								$("#retId").css({
									"text-decoration" : "none",
									"cursor" : "default",
									"color" : "#0066c0"
								});
								hideProgress();
							}
							if (data.shoppingListFlag != ''
									&& data.shoppingListFlag == 'N') {
								$("#shopId").removeAttr("href");
								$("#shopId").css({
									"text-decoration" : "none",
									"cursor" : "default",
									"color" : "#0066c0"
								});
							}
						} else {
							htmlData = htmlData
									+ '<table id="gitBuilds" class="table table-bordered">'
									+ ' <thead>'
									+ '  <tr id="subhead">'
									+ '    <th  class="text-center">Feature</th>'
									+ '    <th class="text-center">Account</th>'
									+ '    <th>Feature</th>'
									+ '    <th>Account</th>'
									+ ' </tr>'
									+ '</thead>'
									+ '<tbody>'
									+ '<tr style=" background-color: #fff;">'
									+ '    <td>'
									+ '    <a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Use for same day or emergency needs" style="text-decoration:none;color:#0066c0;">BOPiS</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '	<td><a id="retId" href="javascript:void();" style="text-decoration:none;" data-placement="bottom" data-toggle="tooltip" title="Know the status- Submit and track returns easily">Online Returns</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width:100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '</tr>'
									+ '<tr>'
									+ '<td><a id="shopId" href="javascript:void();" style="text-decoration:none;" data-placement="left" data-toggle="tooltip" title="Make it easy for customers to reorder favorite items">Shopping Lists</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="All customized print & promotional items easily ordered online" style="text-decoration:none;color:#0066c0;">Print Services</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '</tr>'
									+ '<tr style="background-color: #fff;">'
									+ '<td><a id="mob" style="text-decoration:none;color:#0066c0;cursor:default;" href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Take <u>SA.com</u> anywhere with the mobile app! Customers can download the free app for easy approval and easy re-ordering! (not available for punchout or eDiversity)'
									+ '">Mobile</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Easy customer friendly functionality to update their account" style="text-decoration:none;color:#0066c0;">Account Maintenance</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '</tr>'
									+ '<tr>'
									+ '<td><a href="javascript:void();" data-placement="left" data-toggle="tooltip" title="Product recommendations based on previous purchase history" style="text-decoration:none;color:#0066c0;">SA.com Recommendations</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '<td><a href="javascript:void();" data-placement="bottom" data-toggle="tooltip" title="Save more with <u>SA.com</u> Easy Alternatives" style="text-decoration:none;color:#0066c0;">Product Alternatives</a></td>'
									+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
									+ '			id="inp" class="form-control" type="text" disabled=""'
									+ '			value="No Data Available"'
									+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
									+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
									+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
									+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
									+ '</tr>'
									+ '</tbody>'
									+ '</table>'
									+ '<div style="font-size: 13px; color: rgb(0, 76, 116);">'
									+ '<span style="color: red">* </span> Flags are based on the '
									+ 'previous 3 periods and refreshed weekly.'
									+ '</div>';
							$("#saContent").html("");
							$("#saContent").html(htmlData);
							hideProgress();
						}
						$("[data-toggle='tooltip']").tooltip({
							html : true
						});

					}

				}

			});
}
function generateCell(flag, htmlData, feild) {
	if (flag == 'NOTFOUND' && feild == 'bopis') {
		htmlData = htmlData
				+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
				+ '			id="inp" class="form-control" type="text" disabled=""'
				+ '			value="No Data Available"'
				+ '			style="letter-spacing: .1px; width: 115px; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
				+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
				+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
				+ ' font-weight: bold;" data-placement="bottom" data-toggle="tooltip" title="No data Available"></td>'
	} else if (flag == 'NOTFOUND' && feild == 'print') {
		htmlData = htmlData
				+ '		<td style="padding: 0px; padding-top: 0px; opacity: .4;"><input'
				+ '			id="inp" class="form-control" type="text" disabled=""'
				+ '			value="No Data Available"'
				+ '			style="letter-spacing: .1px; width: 100%; border: none; background-color: #fff; height: 50px; background-repeat: no-repeat;'
				+ ' background-image: url(./resources/img/notFound.jpg); background-size:41px 40px;'
				+ ' color: #000; font-size: 12px; background-position: center; text-align: center;'
				+ ' font-weight: bold;" data-placement="left" data-toggle="tooltip" title="No data Available"></td>'
	} else if (flag == 'Y' && feild == 'bopis') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is using this feature"></td>'
	} else if (flag == 'Y' && feild == 'print') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/Check.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is using this feature"></td>'
	} else if (flag == 'N' && feild == 'bopis') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer not using this feature"></td>'
	} else if (flag == 'N' && feild == 'print') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/Cross.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer not using this feature"></td>'
	} else if (flag == 'NA' && feild == 'bopis') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/DashGrey.png" class="img-circle" data-placement="bottom" data-toggle="tooltip" title="Customer is not eligible for this feature"></td>'
	} else if (flag == 'NA' && feild == 'print') {
		htmlData = htmlData
				+ '   <td><img src="./resources/img/DashGrey.png" class="img-circle" data-placement="left" data-toggle="tooltip" title="Customer is not eligible for this feature"></td>'
	}
	return htmlData;
}
function openReturn(monthYr, Cat, fromRet) {
	$("#custContent").css("display", "block");
	$("#cusId").css("display", "block");
	$("#superContent").css("display", "block");
	$("#showHideId1").prop('class', 'fa fa-times');
	logUserActivityDotCom(7018,
			'user has viewed order details through SBA online return');
	onChangeMonthOrCat(monthYr, Cat, 'YY');
}

function openSubCallToAction(seg) {
	var formData = {};
	var custid = $("#reqCustNum").val();
	$("#segName").html(seg);
	$.ajax({
		dataType : "json",
		url : ctx + "/subCallToAction/" + custid,
		type : "POST",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$("#rationaleData").html("");
			$("#expData").html("");
			$("#rationaleData").html(data.rationaleHtmlData);
			$("#expData").html(data.expHtmlData);
			$("#expObjData").html(data.expObjHtmlData);
			showCallToActionPopUp();
		}
	});

}
function applySubCallToAction(rationaleHtml) {
	var custid = $("#reqCustNum").val();
	$.ajax({
		dataType : "json",
		url : ctx + "/applySubCallToAction/" + custid,
		type : "POST",
		cache : false,
		data : {
			RationaleHtmlCode : rationaleHtml
		},
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			$(".Editor-editor").html(data.rationaleHtmlData);
		}
	});

}

function openOrderDetailsShopping(isFromReturned) {

	// log user activity; view order list
	logUserActivityDotCom(7026,
			'user has viewed order details through SBA Shopping Lists');
	var Amount = new Array();

	var monthNew = parseInt(latestDateforOrder.substring(0, 2));
	var yearNew = latestDateforOrder.substring(2, 6);
	var monthNameArr = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec" ];
	$("#custContent").css("display", "block");
	$("#cusId").css("display", "block");
	$("#superContent").css("display", "block");
	$("#showHideId1").prop('class', 'fa fa-times');
	var formData = {};
	var custid = $("#reqCustNum").val();
	var monthyear = "02015";
	var currentTime = new Date()
	var month = currentTime.getMonth();
	var year = currentTime.getYear();
	var dt = getCurrentTime();
	$("#updateDateValueOrder span").html(dt + " ET");
	var catid = "ALL";
	$("#dataTables-order_processing")
			.html(
					'<div id="example_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-order_processing").css("display", "block");
	monthyear = month + "" + year;
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getOrderDetailsHighLevelData/" + custid + "/"
						+ monthyear + "/" + catid + "/" + "NoChange",
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					$('#order-modal').modal({

						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});

					if (data.objPurchaseDetailsListVO != null
							&& data.objPurchaseDetailsListVO != undefined) {
						$
								.each(
										data.objPurchaseDetailsListVO,
										function(i, item) {
											objPurchaseDetailsListVO[cnt] = item;
											var itemSku = '';
											var itemSkuDesc = '';
											$
													.each(
															item.purchRwdsDtlListVO,
															function(i,
																	purchase) {
																itemSku = itemSku
																		+ purchase.skuNumber
																		+ ',';
																itemSkuDesc = itemSkuDesc
																		+ purchase.itemDescription
																		+ ',';
															});
											$.each(item.savingAmount, function(
													j, amount) {
												Amount[j] = amount;
											});
											mkttrHTML += '<tr class="odd gradeX">'
													+ '<td class="datatablesTd"><a href="javascript:openOrderDetailsInfo('
													+ cnt
													+ ',\''
													+ checkUundefined(item.orderNumber)
													+ '\');" style="text-decoration:underline;padding-right:6px;">'
													+ checkUundefined(item.orderNumber)
													+ '</a>';

											if (item.savingCategory != undefined
													&& item.savingCategory != null
													&& item.savingCategory.length > 0) {
												mkttrHTML += '<i id="OrderIdDtl'
														+ cnt
														+ '"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i><div style="" id="orderdivId'
														+ cnt
														+ '" class="toolTip"><div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#0066c0;letter-spacing:1px;"><u><b>Premium Savings Info</b></u> : </div>'
												$
														.each(
																item.savingCategory,
																function(k,
																		category) {
																	if ((checkUundefined(category)) == 'LOR')
																		category = 'Large Order Rebate';
																	mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:crimson;letter-spacing:1px;">'
																			+ toCamCase(removeUnderScore(checkUundefined(category)))
																			+ ': $'
																			+ formatOrderAmt(checkUundefined(Amount[k]))
																			+ '</div>'

																});
												mkttrHTML += '</div>';
											}
											mkttrHTML += '</td>';
											mkttrHTML += '<td class="datatablesTd">';
											var returnedCount = 0;
											$
													.each(
															item.purchRwdsDtlListVO,
															function(i,
																	purchase) {
																if (purchase.netSpendAmount < 0) {
																	returnedCount++;
																	retFoundChk('X');
																	if (returnedCount == 1) {
																		var ordnum = checkUundefined(item.orderNumber);
																		mkttrHTML += ('<a href="javascript:openReturnedOrderDetailsInfo('
																				+ cnt
																				+ ',\''
																				+ ordnum + '\');" style="text-decoration:underline;padding-right:6px;">X</a>');
																	}
																}
															});
											mkttrHTML += '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.tranDate)
													+ '</td>'
													+ '<td>'
													+ checkUundefined(Number(item.orderLineCount)
															- returnedCount)
													+ '</td>'
													+ '<td class="datatablesTd formatClsOrd">'
													+ formatNum(chkNegAmount(checkUundefined(item.netSpendAmount)))
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.orderContact)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(itemSku)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(itemSkuDesc)
													+ '</td>' + '</tr>';

											cnt++;

										});
						OrderDeatilCount = cnt - 1;
					}
					if ((undefined != isFromReturned && isFromReturned == 'Y' && retFound == 'Y')
							|| ('N' == isFromReturned)) {
						populateMonthYearData(data, monthNew, yearNew);
						populateCatData();
						$(
								"#yearSel option[value=" + monthNew + ""
										+ yearNew + "]").attr("selected",
								"selected");
						$('#datepickerTEXT').val(
								monthNameArr[monthNew - 1] + " / " + yearNew);
						$('#datepickerTEXT').css("font-weight", "bold")
						$('#datepickerTEXT').css("font-family", "Hevletica")
						$('#datepickerTEXT').css("font-size", "14px");
						$('#orderDetailsTabId').html("");
						$('#orderDetailsTabId')
								.html(
										'<table class="table table-striped table-bordered table-hover" id="dataTables-order" width="100%" ><thead><tr>'

												+ '<th>Order No.</th>'
												+ '<th style="color: red">RETURNED</th>'
												+ '<th>Order Date</th>'
												+ '<th>No. Of Items</th>'
												+ '<th>Order Total</th>'
												+ '<th>Order Contact</th>'
												+ '<th>Item SKU</th>'
												+ '<th>Item Desc</th>'
												+ '</tr>'
												+ '</thead><tbody>'
												+ mkttrHTML
												+ '</tbody></table>');
						$("#dataTables-order_processing")
								.css("display", "none");
						$('#dataTables-order')
								.dataTable(
										{
											"lengthMenu" : [ [ 5, 10, 30, -1 ],
													[ 5, 10, 30, "All" ] ],
											"order" : [],
											"bProcessing" : true,
											"oLanguage" : {
												"sSearch" : "Filter: "
											},
											"aoColumns" : [ {
												"bSortable" : true
											}, {
												"bSortable" : true,
												"visible" : true
											}, {
												"bSortable" : true
											}, {
												"bSortable" : true
											}, {
												"bSortable" : true
											}, {
												"bSortable" : true
											}, {
												"bSortable" : true,
												"visible" : false
											}, {
												"bSortable" : true,
												"visible" : false
											} ],
											"fnDrawCallback" : function(
													oSettings) {

												if (OrderDeatilCount != -1) {
													for (var count = 0; count <= OrderDeatilCount; count++) {
														$('#OrderIdDtl' + count)
																.popover(
																		{
																			html : true,
																			placement : 'right',
																			content : $(
																					"#orderdivId"
																							+ count)
																					.html()
																		})
																.on(
																		"hover",
																		function() {
																			$(
																					'.popover')
																					.addClass(
																							'popoverBasic');
																		})
																.on(
																		'show.bs.popover',
																		function() {

																		});
													}
												}
											}
										}).fnDraw();
					} else if (undefined != isFromReturned
							&& isFromReturned == 'Y' && retFound == 'N') {
						var monthYear = '';
						var catId = "ALL";
						if (month != 1) {
							monthYear = (monthNew - 1) + "" + yearNew;
						} else if (month == 1) {
							monthYear = 12 + "" + (yearNew - 1);
						}
						populateMonthYearData(data, (monthNew - 1), yearNew);
						populateCatData();
						if (monthNew != 12) {
							$(
									"#yearSel option[value=" + (monthNew - 1)
											+ "" + yearNew + "]").attr(
									"selected", "selected");
							$('#datepickerTEXT').val(
									monthNameArr[monthNew - 2] + " / "
											+ yearNew);
						} else {
							$("#yearSel option[value=12" + (yearNew - 1) + "]")
									.attr("selected", "selected");
							$('#datepickerTEXT').val(
									monthNameArr[11] + " / " + (yearNew - 1));
						}

						$('#datepickerTEXT').css("font-weight", "bold")
						$('#datepickerTEXT').css("font-family", "Hevletica")
						$('#datepickerTEXT').css("font-size", "14px");
						onChangeMonthOrCat(monthYear, catId, 'Y');
					}

					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label').css("color", "#004c74");
					$('#dataTables-order_length label')
							.css("font-size", "15px");
					$('#dataTables-order_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-order_filter label').css("color", "#004c74");
					$('#dataTables-order_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-order_filter').css("text-align", "right");
					$('#dataTables-order_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-order_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-order_filter label').css("float", "right");
					$('#dataTables-order_filter input[type="search"]').css(
							"width", "270px");
					$('#dataTables-order_info').css("color", "#004c74");
					$('#dataTables-order_info').css("font-weight", "600");
					$('#dataTables-order_paginate').css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-order').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-order_filter input[type="search"]').attr(
							'placeholder',
							'Enter Order No. OR Item No. OR Item Desc.').css({
						'width' : '270px',
						'display' : 'inline-block'
					});
				}

			})
	$("#yearSel").val("January 2015");
}
function configureCalender() {
	$.datepicker._updateDatepicker_original = $.datepicker._updateDatepicker;
	$.datepicker._updateDatepicker = function(inst) {
		$.datepicker._updateDatepicker_original(inst);
		var afterShow = this._get(inst, 'afterShow');
		if (afterShow)
			afterShow.apply((inst.input ? inst.input[0] : null)); 
																	
																	
	}
}

function openPerfDashboard() {
	$("#customerForm").attr("action", "./perfDashboardAction")
	$('#customerForm').submit();
}

function populateCustomerProfile() {

	var custid = $("#reqCustNum").val();
	var accid = $("#accId").val();

	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/representive/" + accid + "/customer/"
						+ custid,
				type : "GET",
				cache : false,
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if (data != null && data != 'null'
							&& null != data.customerInfo
							&& 'null' != data.customerInfo) {
						$
								.each(
										data.customerInfo,
										function(i, item) {

											var tmpCustNumber = checkUundefined(item.customerNo);
											if (custid == tmpCustNumber) {

												$("#custNum")
														.html(' ' +'<a style="pointer-events:none;cursor: default;color:#262626">'+custid+'<\a>');

												$("#addrTxt").css("display",
														"block");

												$("#custCompName")
														.html(
																' '
																		+ checkUundefined(item.companyName));
												$("#custType")
														.html(
																' '
																		+ checkUundefined(item.tier));

												var address = '';
												var cityStateZip = '';
												if (checkUundefined(item.billingStreet).length > 0) {
													address = checkUundefined(item.billingStreet);
													if (address != '')
														address = address
																+ "</br>";
													if (checkUundefined(item.billingCity).length > 0) {
														cityStateZip = checkUundefined(item.billingCity);
														if (cityStateZip != '')
															cityStateZip = cityStateZip
																	+ ", "
																	+ checkUundefined(item.billingState)
																	+ " "
																	+ formatZip(checkUundefined(item.postalCode));

														address = address
																+ cityStateZip;
													}
													if (undefined != address
															&& '' != address)
														address = address
																.toUpperCase();
													$("#custAddDtl").html(
															address);
												} else {
													$("#custAddDtl")
															.html(
																	"ADDRESS NOT FOUND");
												}

												var contactPerson = '';
												if (checkUundefined(item.firstName).length > 0)
													contactPerson += checkUundefined(item.firstName)
															+ ' '
															+ checkUundefined(item.lastName);

												contactPerson = generateContactPersonSAMNew(
														contactPerson,
														checkUundefined(item.contactEmailId));
												$("#custContactDetail").html(
														' ' + contactPerson);

												if (checkUundefined(item.phone).length > 0)
													$("#custConNum")
															.html(
																	' '
																			+'<span onclick="javascript:logUserActivityDotCom(8090,\''+checkUundefined(item.phone)+': in Customer Profile Grid in SAM Dashboard Page for Customer '+custid+'\')" >'+ checkUundefined(item.phone)+'</span>');

												if (checkUundefined(item.enrollDate).length > 0)
													$("#custEnrollDate")
															.html(
																	' '
																			+ checkUundefined((item.enrollDate)
																					.substring(
																							0,
																							11)));
												if (checkUundefined(item.industryCode).length > 0)
													$("#custIndGrp")
															.html(
																	' '
																			+ checkUundefined(item.industryCode));
												if (undefined != item.timeZone
														&& '' != item.timeZone) {
													if (undefined != $(
															"#custConNum")
															.html()
															&& ' ' != $(
																	"#custConNum")
																	.html()
															&& '' != $(
																	"#custConNum")
																	.html())
														$("#custConNum")
																.append(
																		'<br>'
																				+ checkUundefined(item.timeZone));
													else
														$("#custConNum")
																.html(
																		checkUundefined(item.timeZone));
												}
												/*
												 * For Fiscal year selection
												 * section
												 */
												if (checkUundefined(item.last_contacted_date).length > 0) {
													lastConDate = checkUundefined(item.last_contacted_date);
													if (lastConDate != null
															|| lastConDate != '') {
														lastConDate = lastConDate
																.substring(0,
																		10);
														var lastConDateArr = lastConDate
																.split("-");
														lastConDate = lastConDateArr[1]
																+ "/"
																+ lastConDateArr[2]
																+ "/"
																+ lastConDateArr[0];
													}
													$(
															"#lastContactedDateValueId")
															.html(lastConDate);

												}

												if (checkUundefined(item.billingStreet).length > 0) {

												}

												if (item.sam_SFDC_ID != '-'
														&& item.sam_SFDC_ID != '') {
													$("#cnoId").html("");
													$("#logId").html("");
													var sfdcBaseUrl = $(
															"#sfdcAppBaseUrl")
															.val();
													var compNameUrl = sfdcBaseUrl
															+ '/'
															+ item.sam_SFDC_ID;
													$("#compNameCont")
															.html(
																	'<a href="'
																			+ compNameUrl
																			+ '" onclick="generateLogs(\'compnameSAMNew\')"  oncontextmenu="generateLogs(\'compnameSAMNew\')" id="compName" style="font-size:14px;font-family:Helvetica !important;font-weight:bold;color:#0066c0;text-decoration:underline;" class="letterSpace" target="_blank"> '
																			+ checkUundefined(item.companyName)
																			+ ' </a>');
													$("#ulIamIdSec").css(
															"display", "block");
													$("#sfdcId").css("display",
															"block");

													var cnoUrl = 'https://na42.salesforce.com/setup/ui/recordtypeselect.jsp?ent=Opportunity&save_new_url=%2F006%2Fe%3FretURL%3D%252F'
															+ item.accountId
															+ '%26accid%3D'
															+ item.accountId
													var cnoHtml = '<a href="'
															+ cnoUrl
															+ '" onclick="generateLogs(\'createoppSAMNew\')" oncontextmenu="generateLogs(\'createoppSAMNew\')" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;" target="_blank">Create New Opp.</a>'
															+ ' <a href="'
															+ cnoUrl
															+ '" onclick="generateLogs(\'createoppSAMNew\')" oncontextmenu="generateLogs(\'createoppSAMNew\')" target="_blank"><i style="padding-top: 4px;color:#fff;font-size: 19px;margin-right: -8px;float:right;" id="cnoIconId" class="fa fa-hand-o-up"></i></a>';
													$("#cnoId").html(cnoHtml);
													$("#ctaCreateOpp").attr(
															"href", cnoUrl);
													$("#menuItemIcon7").attr(
															"href", cnoUrl);
													var logUrl = 'https://login.salesforce.com/apex/DotComSales_CallActivity?pid='
															+ item.sam_SFDC_ID;
													var logHtml = '<a href="'
															+ logUrl
															+ '" onclick="generateLogs(\'logtaskSAMNew\')" oncontextmenu="generateLogs(\'logtaskSAMNew\')" target="_blank" style="text-decoration: underline;color: #fff;font-family: Helvetica;font-size:14px;letter-spacing: 1px;font-weight: inherit;">Log A Task</a>'
															+ ' <a href="'
															+ logUrl
															+ '" onclick="generateLogs(\'logtaskSAMNew\')" oncontextmenu="generateLogs(\'logtaskSAMNew\')" target="_blank" ><i style="padding-top: 4px;color:#fff;font-size: 18px;margin-right: -8px;float:right;" id="logIconId" class="fa fa-file"></i></a>';
													$("#logId").html(logHtml);
													$("#menuItemIcon8").attr(
															"href", logUrl);
													var cls = $("#cnoHead")
															.attr("class");
													if (undefined != cls
															&& '' != cls
															&& cls
																	.indexOf("cnoCollapse") == -1) {
														$("#cnoHead")
																.removeClass(
																		"cnoCollapse");
														$("#cnoHead").addClass(
																"cnoExpand");
														$("#logHead")
																.removeClass(
																		"logCollapse");
														$("#logHead").addClass(
																"logExpand");
														$("#sfdcId").css(
																"display",
																"block");

													} else {
														$("#custCompName")
																.html(
																		checkUundefined(item.companyName));
														$("#cnoHead").addClass(
																"cnoCollapse");
														$("#cnoHead")
																.removeClass(
																		"cnoExpand");
														$("#logHead").addClass(
																"logCollapse");
														$("#logHead")
																.removeClass(
																		"logExpand");
														$("#sfdcId").css(
																"display",
																"none");

													}

												} else {
													$("#ulIamIdSec").css(
															"display", "none");
													$("#ulIdOther").css(
															"display", "none");
													$("#cnoId").html("");
													$("#logId").html("");
													$("#sfdcId").css("display",
															"none");
												}
											}

										});
						if (undefined != data.customerRewards
								&& null != data.customerRewards
								&& '' != data.customerRewards) {
							var con = '<div style="padding:15px">';
							$
									.each(
											data.customerRewards,
											function(i, item) {
												con += '<div style="padding-left:5px;letter-spacing:1px;" >'
														+ '<a href="javascript:void();" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: auto;cursor:default;text-decoration:none;color:#4d4d4d;" class="a-size-small a-link-child"><span>'
														+ item.childRewards
														+ '</span></a>'
														+ '</div>'
											});
							con += '</div>';
							$("#childInfo").html($.parseHTML(con));

							$('#childInfoId')
									.popover({
										title : 'Sub Rewards',
										html : true,
										placement : 'left',
										content : $("#childInfo").html()
									})
									.on("hover", function() {
										$('.popover').addClass('popoverBasic');
									})
									.on('show.bs.popover', function() {

									})
									.on(
											"click",
											function() {
												var _this = this;
												$(this).popover("show");
												$(".popover").on(
														"mouseleave",
														function() {
															$(_this).popover(
																	'hide');
														});
												logUserActivityDotCom(7039,
														'viewed sub rewards from SAM dashboard');
											}).on("mouseleave", function() {
										var _this = this;
										setTimeout(function() {
											if (!$(".popover:hover").length) {
												$(_this).popover("hide");
											}
										}, 300)
									});
						} else {
							$('#childInfoId').css('display', 'none');
						}
					}

					generateSegmentDetails($("#custType").html());
				}

			})

}

function monthAnalysisForSam() {
	var formData = {};
	var custid = $("#reqCustNum").val();
	var year = $("#yrId").val();
	$("#samMonthTitle").html("");
	$("#samMonthTitle")
			.html(
					'<span> '
							+ year
							+ ' Monthly Spend Analysis <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>'
							/*+'<span style="padding-right: 5px !important; padding-left: 20px !Important;" id="excelId"><a style="color: #00cc00" data-toggle="tooltip" title="Download Savings Detail Report" data-placement="top" onclick="javascript:downloadSavingsReport();" href="javascript:;" data-original-title="Download Savings Detail Report"><span class="letterSpace" style="padding-right: 5px !important; padding-left: 5px !Important; padding-top: 2px; padding-bottom: 2px;"><i class="fa fa-download" style="font-size: 125%; border-radius: 10px;vertical-align:middle"></i></span></a></span>'*/
							);
	$('#updateDateValueMnth').html(getCurrentDateTime() + " ET");
	$("#sam-dataTables-example1_processing")
			.html(
					'<div id="sam-dataTables-example1_processing_sam_month" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#sam-dataTables-example1_processing").css("display", "block");

	$("div#chartdiv")
			.html(
					'<div id="chartdiv_processing" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("div#chartdiv #chartdiv_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid
						+ "/fiscalmonth/categories/sum?year=" + year,
				type : "GET",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var arrCount = 0;
					var mkttrHTML = "";
					var footerHTML = '<td style="line-height:2.5;padding-left:4px;" class="datatablesTd">Total</td>';
					$('#samTableId').html("");

					var allCatTotalSumArr = new Array();
					var chkBox = '<label style="font-family: HelveticaNeueNormal; color: #0092db;">'
							+ '<input type="checkbox" value="" id="retailOnlineChk" data-toggle="collapse" value="toggleRows" data-target=".ORrow">'
							+ '<span class="cr" style=""> <i class="cr-icon fa fa-check"></i>'
							+ '</span>See Retail and Staples.com Sales</label>';

					$("#divChkId").html(chkBox);
					/* start */
					var headArr = new Array();
					var catArr = new Array();
					var onlinecatArr = new Array();
					var retailcatArr = new Array();
					var catTotal = new Array();
					var onlineTotal = new Array();
					var retailTotal = new Array();
					var verticalTotalArr = new Array();
					var headCount = 0, catCount = 0, retailCatCount = 0, onlineCatCount = 0;
					var catTotalCnt = 0, retailTotalCnt = 0, onlineTotalCnt = 0, verticalCount = 0;

					$.map(data.verticalCategoryTotal, function(V, K) {
						verticalTotalArr[verticalCount] = V;
						verticalCount++;

					});
					verticalTotalArr.reverse();
					$
							.each(
									verticalTotalArr,
									function(i, item) {
										footerHTML += '<td style="text-align:right;line-height:2.5;padding-right:1.3% !important;" class="formatCls">'
												+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
														item).toFixed(2))))
												+ '</td>'
									});
					footerHTML += '<td style="text-align:right;line-height:2.5;padding-right:1.3% !important;" class="formatCls">'
							+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
									data.totalSum).toFixed(2)))) + '</td>';
					$.map(data.fiscalMonthCategories, function(V, K) {
						$.each(V, function(key, items) {
							if (key == 'fiscalMonthTotal') {
								catTotal[catTotalCnt] = items;
								catTotalCnt++;
							}
							if (key == 'dotComMonthTotal') {
								onlineTotal[onlineTotalCnt] = items;
								onlineTotalCnt++;
							}
							if (key == 'retailMonthTotal') {
								retailTotal[retailTotalCnt] = items;
								retailTotalCnt++;
							}
							if (key == 'completeCategoryTotal') {
								catArr[catCount] = items;
								if (catCount == 0) {
									$.each(items, function(index, item) {
										headArr[headCount] = index;
										headCount++;
									});
								}
								catCount++;
							}
							if (key == 'categories') {
								onlinecatArr[onlineCatCount] = items;
								onlineCatCount++;
							}
							if (key == 'retailCategories') {
								retailcatArr[retailCatCount] = items;
								retailCatCount++;
							}
						});
					});
					headArr.reverse();
					var count = 0;
					$
							.map(
									data.fiscalMonthCategories,
									function(V, K) {
										mkttrHTML += '<tr class=""><td class="datatablesTd" style="padding-left:4px;">'
												+ K + '</td>';
										for (var cnt = 0; cnt < 9; cnt++) {
											mkttrHTML += '<td class="datatablesTd formatCls">'
													+ getCellstructure(
															headArr,
															catArr[count],
															onlinecatArr[count],
															retailcatArr[count],
															cnt) + '</td>';
										}
										mkttrHTML += '<td class="datatablesTd formatCls">'
												+ '<table border="0"><tbody>'
												+ '<tr><td style="border:none;line-height:2.5 !important;float:right;padding-right:5%;">'
												+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
														catTotal[count])
														.toFixed(2))))
												+ '</td></tr>'
												+ '<tr class="collapse ORrow retailCls" style="background-color: #598b9a;"><td class="retailCls" style="border:none;padding-bottom: 0px; line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'
												+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
														retailTotal[count])
														.toFixed(2))))
												+ '</td></tr>'
												+ '<tr class="collapse ORrow midrow" style=""><td class="midrow" style="border:none;height: 3px;"></td></tr>'
												+ '<tr class="collapse ORrow onlineCls" style="background-color: #808080;"><td class="onlineCls" style="border:none;padding-bottom: 0px;line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'
												+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
														onlineTotal[count])
														.toFixed(2))))
												+ '</td></tr>'
												+ '</tbody></table>' + '</td>';
										mkttrHTML += '</tr>';
										count++;
									});

					var monthAnalysisHtml = '<table class="table" id="sam-dataTables-example1">'
							.toString()
							+ '<thead><tr><th>Month</th>';

					var count = 0;
					$.each(headArr, function(index, item) {
						monthAnalysisHtml += '<th>' + item + '</th>'
						count++;
					});
					monthAnalysisHtml += '<th>Total</th></tr></thead><tbody>'
							.toString()

					monthAnalysisHtml = monthAnalysisHtml
							+ (mkttrHTML.toString())
							+ (footerHTML + '</tbody></table>'.toString());
					$('#samTableId').html(monthAnalysisHtml);

					/* end */
					var table = $('#sam-dataTables-example1').DataTable(
							{

								"bSort" : true,
								"lengthMenu" : [ [ 15, 25, 50, -1 ],
										[ 15, 25, 50, "All" ] ],
								"order" : [],
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"paging" : false,
								"searching" : false,
								"info" : false,
								"bProcessing" : true,
								"aoColumns" : [ {
									"bSortable" : false
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]

							});
					$("#sam-dataTables-example1_processing").css("display",
							"none");
					firstYearAmtArr = catTotal;
					getCylindricalChartDataForSam(firstYearAmtArr, year,
							latestFiscalYearSam, retailTotal, onlineTotal);
				}
			});

}
function getCellstructure(headArr, cell1Data, cell2Data, cell3Data, count) {
	var cellData = '';
	var catVal = cell1Data[headArr[count]];
	cellData += '<table border="0"><tbody>'

			+ '<tr><td style="border:none;line-height:2.5 !important;float:right;padding-right:5%;">'
			+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(catVal)
					.toFixed(2))))
			+ '</td></tr>'
			+ '<tr class="collapse ORrow retailCls" style="background-color: #598b9a;"><td class="retailCls" style="border:none;padding-bottom: 0px; line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'
			+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
					cell3Data[count].categoryTotal).toFixed(2))))
			+ '</td></tr>'
			+ '<tr class="collapse ORrow midrow" style=""><td class="midrow" style="border:none;height: 3px;"></td></tr>'
			+ '<tr class="collapse ORrow onlineCls" style="background-color: #808080;"><td class="onlineCls" style="border:none;padding-bottom: 0px;line-height: 1.8 !important;color: #fff;float:right;padding-right:5%;">'
			+ formatNumWithoutDollor(chkNegAmountWithoutDollor(checkUundefined(parseFloat(
					cell2Data[count].categoryTotal).toFixed(2))))
			+ '</td></tr>' + '</tbody></table>';

	return cellData;
}

function getCylindricalChartDataForSam(firstYearAmtArr, selYear,
		latestFiscalYearSam, retailTotal, onlineTotal) {
	var formData = {};
	var custid = $("#reqCustNum").val();
	$('#updateDateValueYearSpend').html(getCurrentDateTime() + " ET");
	var secYear = '';
	if (selYear == latestFiscalYearSam) {
		secYear = latestFiscalYearSam - 1;
	} else if (selYear == (latestFiscalYearSam - 1)) {
		secYear = latestFiscalYearSam;
	}
	var yearsArr = new Array();
	yearsArr = [ selYear, secYear ];
	$.ajax({
		dataType : "json",
		url : ctx + "/sales/customer/" + custid
				+ "/fiscalmonth/categories/sum?year=" + secYear,
		type : "GET",
		cache : false,
		data : JSON.stringify(formData),
		timeout : 1000000,
		success : function(data, textStatus, jqXHR) {
			var catTotalCnt = 0;
			var catTotal = new Array();
			var onlineTotalY2 = new Array();
			var retailTotalY2 = new Array();
			var catTotalCnt = 0, retailTotalCnt = 0, onlineTotalCnt = 0;
			$.map(data.fiscalMonthCategories, function(V, K) {
				$.each(V, function(key, items) {
					if (key == 'fiscalMonthTotal') {
						catTotal[catTotalCnt] = items;
						catTotalCnt++;
					}
					if (key == 'dotComMonthTotal') {
						onlineTotalY2[onlineTotalCnt] = items;
						onlineTotalCnt++;
					}
					if (key == 'retailMonthTotal') {
						retailTotalY2[retailTotalCnt] = items;
						retailTotalCnt++;
					}
				});
			});
			secYearAmtArr = catTotal;
			populateCylindricalChartForSam(firstYearAmtArr, secYearAmtArr,
					yearsArr, retailTotal, retailTotalY2, onlineTotal,
					onlineTotalY2);
		}
	});

}

function populateCylindricalChartForSam(firstYearAmtArr, secYearAmtArr,
		yearsArr, year1RetailAmt, year2RetailAmt, year1OnlineAmt,
		year2OnlineAmt) {
	var selYear = $("#yrId").val();
	$("#mnthTitle")
			.html(
					'<span>'
							+ selYear
							+ ' Yearly Spend Per Month <i style="font-size:18px !important;line-height:12px; !important" class="fa fa-angle-double-right"></i></span>');
	var monthArr = [ "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug",
			"Sept", "Oct", "Nov", "Dec" ];
	var bardata = [];
	bardata = getMonthAmountMapForTwoYears(monthArr, firstYearAmtArr,
			secYearAmtArr, year1RetailAmt, year2RetailAmt, year1OnlineAmt,
			year2OnlineAmt);
	$("div#chartdiv #chartdiv_processing").css("display", "block");
	var chart = AmCharts
			.makeChart(
					"chartdiv",
					{
						"type" : "serial",
						"theme" : "light",
						"legend" : {
							"useGraphSettings" : true,
							"clickMarker" : handleLegendClick,
							"clickLabel" : handleLegendClick
						},
						"dataProvider" : bardata,
						"startDuration" : 0.5,
						"graphs" : [
								{
									"balloonText" : "<div style='text-align:left;font-weight:bold;color:#434343;'>Year: "
											+ yearsArr[0]
											+ " Period: [[category]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Total Spend: $[[value]]</div><div style='text-align:left;font-weight:bold;color:#434343;'>Retail: $[[retailSaleYear1]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Staples.com: $[[onlineSaleYear1]]</div>",
									"title" : yearsArr[0],
									"type" : "column",
									"colorField" : "color",
									"fillAlphas" : 0.85,
									"lineAlpha" : 0.1,
									"topRadius" : 1,
									"valueField" : "year1"
								},
								{
									"balloonText" : "<div style='text-align:left;font-weight:bold;color:#434343;'>Year: "
											+ yearsArr[1]
											+ " Period: [[category]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Total Spend: $[[value]]</div><div style='text-align:left;font-weight:bold;color:#434343;'>Retail: $[[retailSaleYear2]] </div><div style='text-align:left;font-weight:bold;color:#434343;'>Staples.com: $[[onlineSaleYear2]]</div>",
									"bullet" : "round",
									"title" : yearsArr[1],
									"lineColor" : "red",
									"type" : "line",
									"valueField" : "year2",
									"fillAlphas" : 0
								} ],
						"depth3D" : 30,
						"angle" : 50,
						"chartCursor" : {
							"cursorAlpha" : 0,
							"zoomable" : false
						},
						"categoryField" : "month",
						"categoryAxis" : {
							"gridPosition" : "start",
							"axisAlpha" : 0,
							"fillAlpha" : 0.05,
							"fillColor" : "#000000",
							"gridAlpha" : 0,
							"position" : "bottom"
						},
						"export" : {
							"enabled" : true,
							"position" : "bottom-right"
						}
					});

	$("g text[text-anchor=end]").each(function(i, item) {
		var htmlData = $(this).html();
		if (undefined == htmlData || '' == htmlData || ' ' == htmlData) {
			$(this).remove();
		}
	})
	$("g path[fill=#67b7dc]").css("display", "none");
	$("g text[text-anchor=end] tspan").each(
			function(i, item) {
				var htmlData = $(this).html();
				if (undefined != htmlData && '' != htmlData && ' ' != htmlData
						&& htmlData.indexOf("$") == -1) {
					$(this).html('$' + htmlData);
				}
			});
}

function getMonthAmountMapForTwoYears(monthArr, firstYearAmnt, secondYearAmnt,
		year1RetailAmt, year2RetailAmt, year1OnlineAmt, year2OnlineAmt) {
	var monthArr = [ "FP 01", "FP 02", "FP 03", "FP 04", "FP 05", "FP 06",
			"FP 07", "FP 08", "FP 09", "FP 10", "FP 11", "FP 12" ]; 
																	
	var colorArr = [ '#FF0F00', '#FF9E01', '#0D8ECF', '#FCD202', '#CD0D74',
			'#754DEB', '#04D215', '#0D8ECF', '#2A0CD0', '#8A0CCF', '#CD0D74',
			'#754DEB' ];
	var labels = firstYearAmnt;
	var values = monthArr;
	var count = -1;
	return labels.map(function(amt) {

		count++;
		return {
			"month" : monthArr[count],
			"year1" : parseFloat(amt),
			"year2" : parseFloat(secondYearAmnt[count]),
			"retailSaleYear1" : year1RetailAmt[count],
			"onlineSaleYear1" : year1OnlineAmt[count],
			"retailSaleYear2" : year2RetailAmt[count],
			"onlineSaleYear2" : year2OnlineAmt[count],
			"color" : colorArr[count]
		}
	});
}

function getHawkeyeDetails() {
	var formData = {};
	var custid = $("#reqCustNum").val();
	var dt = geDataRefreshTime('SAM_HAWKEYE_DATA');
	$("#updateDateValueHk span").html(dt + " ET");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getHawkeyeDetails/" + custid,
				type : "GET",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if (null != data && '' != data && undefined != data) {
						hawkeyHtml = '<div class="col-lg-4 col-md-6"> <div id="hk_summ" class="list-group" style="box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)">'
								+ '<a href="#" class="" style="text-decoration: none;cursor: default;">	<p class="list-group-item-text"	style="width:100%;background-image: linear-gradient(#f5f5f5);text-decoration: none;cursor: default; padding: 10px; font-size: 14px; color: black; font-weight: bold; font-style: Helvetica; border:none;">Summary</p> </a> <a href="javascript:void();" class="list-group-item visitor"	style="height:59px !important;">'
								+ '<h3 class="pull-right"> <i class="fa fa-line-chart"></i></h3><p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Potential value</p>';
						if (undefined != data.totalDollarVal
								&& null != data.totalDollarVal
								&& '' != data.totalDollarVal)
							hawkeyHtml += '<h4 class="list-group-item-heading count"	style="max-width: 80%; font-size: 12px !important;">'
									+ formatNum(checkUundefined(parseFloat(
											data.totalDollarVal).toFixed(2)))
									+ '</h4></a>';
						else
							hawkeyHtml += '<h4 class="list-group-item-heading count"	style="max-width: 80%; font-size: 12px !important;">NA</h4></a>';

						if (undefined != data.churnSeg && null != data.churnSeg
								&& '' != data.churnSeg
								&& data.churnSeg == 'Low') {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like" style="height:59px !important;"><h3 class="pull-right"><i class="fa fa-level-down" style="color: limegreen !important;"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:limegreen !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						} else if (undefined != data.churnSeg
								&& null != data.churnSeg && '' != data.churnSeg
								&& data.churnSeg == 'High') {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like" style="height:59px !important;"><h3 class="pull-right"><i class="fa fa-level-up" style="color: red !important;"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:red !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						} else if (undefined != data.churnSeg
								&& null != data.churnSeg && '' != data.churnSeg
								&& data.churnSeg == 'Medium') {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like" style="height:59px !important;"><h3 class="pull-right"><i class="fa fa-arrows-h" style="color: #ffbb33 !important;"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:#ffbb33 !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						} else if (undefined != data.churnSeg
								&& null != data.churnSeg && '' != data.churnSeg
								&& data.churnSeg == 'Low-Medium') {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like"><h3 class="pull-right" style="height:59px !important;"><i class="fa fa-level-down" style="transform:rotate(55deg);color:#ffbb33 !important"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:#ffbb33 !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						} else if (undefined != data.churnSeg
								&& null != data.churnSeg && '' != data.churnSeg
								&& data.churnSeg == 'Medium-High') {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like"><h3 class="pull-right" style="height:59px !important;"><i class="fa fa-level-down" style="transform:rotate(135deg);color: red !important"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:red !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						} else {
							hawkeyHtml += '<a href="javascript:void();" class="list-group-item facebook-like"><h3 class="pull-right" style="height:59px !important;"><i class="fa fa-users" style="font-size:28px !important;color: lightseagreen !important"></i></h3>';
							hawkeyHtml += '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">Likelihood to Churn</p>';
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:lightseagreen !important;">'
									+ replaceWithNA(data.churnSeg)
									+ '</h4></a>';
						}

						hawkeyHtml += '<a href="javascript:void();" class="list-group-item google-plus" style="height:59px !important;">	<h3 class="pull-right">	<i class="fa fa-thumbs-up" style="color:limegreen !important"></i>	</h3>'
								+ '<p class="list-group-item-text" style="line-height: 1.5; font-size: 14px;">High Purchase Category</p>';
						if (undefined != data.highPropensityCat
								&& null != data.highPropensityCat
								&& '' != data.highPropensityCat)
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:limegreen !important;">	'
									+ data.highPropensityCat + '</h4></a>';
						else
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:limegreen; !important">	NA </h4></a>';

						hawkeyHtml += '<a href="javascript:void();" class="list-group-item twitter" style="height:59px !important;">	<h3 class="pull-right">	<i class="fa fa-thumbs-down" style="color:red !important"></i></h3>'
								+ '<p class="list-group-item-text"	style="line-height: 1.5; font-size: 14px;">Declining Purchase Category</p>';

						if (undefined != data.decliningCat
								&& null != data.decliningCat
								&& '' != data.decliningCat)
							hawkeyHtml += '	<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:red !important;">'
									+ data.decliningCat + '</h4>	</a>';
						else
							hawkeyHtml += '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;color:red !important;">None</h4>	</a>';
						hawkeyHtml += '</div></div>'
								+ '<div class="col-lg-3 col-md-6" style="padding-right: 0px; padding-left: 0px;"><a href="#" class="" style="text-decoration: none; cursor: default;">'
								+ '<p class="list-group-item-text"	style="background-image: linear-gradient(#f5f5f5);text-decoration: none;cursor: default; padding: 10px; font-size: 14px; font-weight: bold; font-style: Helvetica; border: 1px solid #ddd; border-bottom: none;color:black;box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)">	Hawkeye Schedule Data </p></a>'

								+ '<div id="hk_sdl" class="list-group" style="box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)"> <a href="javascript:void();" class="list-group-item tumblr"	style="text-decoration: none; cursor: pointer;">'
								+ '	<h3 class="pull-right">	<i class="fa fa-shopping-cart"></i>	</h3>'

								+ '	<p class="list-group-item-text"	style="line-height: 1.6; font-size: 14px;">PURCHASES</p>'
								+ '<h4 class="list-group-item-heading count"style="margin-bottom: 0px; line-height: 1.6; max-width: 80%; font-size: 12px !important;">'
								+ '	<span style="color: cornflowerblue;">Frequency:</span> every '
								+ checkUundefined(data.typicalPurchaseDate)
								+ ' days</h4>'
								+ '<h4 class="list-group-item-heading count"	style="max-width: 90%; font-size: 12px !important;">'
								+ '	<span style="" >Last Purchase:</span> <span id="lastPurchaseId" style="">'
								+ checkUundefined(data.daysSinceLastPurchase)
								+ ' days ago</span></h4></a>'
								+ '<a href="javascript:void();" class="list-group-item linkedin">	<h3 class="pull-right">	<i class="fa fa-globe"></i>	</h3>'
								+ '<p class="list-group-item-text"	style="line-height: 1.6; font-size: 14px;">BROWSES</p>'
								+ '<h4 class="list-group-item-heading count"	style="margin-bottom: 0px; line-height: 1.6; max-width: 80%; font-size: 12px !important;">'
								+ '<span style="color: cornflowerblue;">Frequency:</span> every '
								+ checkUundefined(data.typicalBrowseDays)
								+ ' days</h4>'
								+ '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;">	<span style=";">Last Browse:</span> <span id="browseDateId" style="">'
								+ checkUundefined(data.dayaSinceLastBrowse)
								+ ' days ago</span></h4></a>'

								+ '<a href="javascript:void();" class="list-group-item youtube">	<h3 class="pull-right">	<i class="fa fa-dollar"></i></h3>'
								+ '<p class="list-group-item-text"	style="line-height: 1.7; font-size: 14px;">SPENDS <span style="font-size: 11.5px;color:dimgray;"> (last 30 days)</span></p>'

								+ '<h4 class="list-group-item-heading count"	style="margin-bottom: 0px; line-height: 1.6; max-width: 80%; font-size: 12px !important;">'
								+ '<span style="color: cornflowerblue;">Typical Spend:</span><span style="color:#0e76a8;"> '
								+ formatNum(checkUundefined(parseFloat(
										data.typicalSpendAmount).toFixed(2)))
								+ '</span></h4>'
								+ '<h4 class="list-group-item-heading count" style="max-width: 80%; font-size: 12px !important;">'
								+ '<span style="color: cornflowerblue;">Spent:</span> <span id="spentId" style="">'
								+ formatNum(checkUundefined(parseFloat(
										data.amountSpent).toFixed(2)))
								+ '</span></h4></a>'
								+ '</div></div>'
								+ '<div class="col-lg-5 col-md-12 "><a href="#" class="" style="text-decoration: none; cursor: default;"><p class="list-group-item-text" style="width:100%;background-image: linear-gradient(#f5f5f5);text-decoration: none;cursor: default;background-color: #f7f7f7; padding: 10px; font-size: 14px; color: black; font-weight: bold; font-style: Helvetica; border: 1px solid #ddd; border-bottom: none;box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19)"> Staples.com Activity </p></a>'
								+ '<div id="userSec" class="" style="border: navajowhite;max-height:255px;">'
								+ '</div></div>'
								+ '<div class="col-md-12" style="position: relative;float: right;width: 100% !important;">	<a class="slide-toggle" href="javascript:void();" style="">  <span class="click" id="viewableLink">Category data</span>  </a>  </div>';
						$('#hk_sec1').html(hawkeyHtml);

						var easing = 1
						var easing_effect = 'easeOutBounce';
						var animation_speed = 1000
						$(".slide-toggle").click(
								function() {
									$("#hk_sec2").animate({
										width : "toggle"
									});
									var linkText = $("#viewableLink").html();
									if (undefined != linkText
											&& linkText == 'Category data')
										$("#viewableLink").html(
												'Hawkeye details')
									else if (undefined != linkText
											&& linkText == 'Hawkeye details')
										$("#viewableLink")
												.html('Category data')
								});
						getColorScheme(
								checkUundefined(data.typicalPurchaseDate),
								checkUundefined(data.daysSinceLastPurchase),
								'lastPurchaseId');
						getColorScheme(checkUundefined(data.typicalBrowseDays),
								checkUundefined(data.dayaSinceLastBrowse),
								'browseDateId');
						getColorScheme(
								checkUundefined(data.typicalSpendAmount),
								checkUundefined(data.amountSpent), 'spentId');
					}

					if (null != data && '' != data && undefined != data
							&& null != data.categoryDataVo
							&& '' != data.categoryDataVo) {
						var catHtml = '<thead style="background-color: #f7f7f7;"> <tr> <th style="padding: 10px;"> Category</th> <th style="padding: 0px;padding-left: 5px;">Status</th> <th style="padding: 0px;padding-left: 5px;"> Value of contact</th> <th style="padding: 0px; padding-left: 5px;">Purchased category</th> </tr></thead>';
						$
								.each(
										data.categoryDataVo,
										function(i, item) {
											catHtml += "<tr><td>"
													+ item.categoryData
													+ "</td> <td>"
													+ item.status
													+ "</td> <td style='text-align:right;padding-right:7%;'>"
													+ formatNum(checkUundefined(parseFloat(
															item.valueOfContact)
															.toFixed(2)))
													+ "</td> <td style=''>"
													+ checkUundefined(item.purchasedCat)
													+ "</td></tr>";
										});
						catHtml += "</tbody>";
						$("#categoryTabId").html(catHtml);
					} else {
						$("#categoryTabId")
								.parent()
								.append(
										"<div style='text-align:center;padding-top:10%;font-weight:700;font-size:20px;color:darkgrey'>NO CATEGORY DATA FOUND FOR THIS CUSTOMER</div>");
						$("#categoryTabId").remove();

					}
					getUserSectionOnHawkeye();

				}
			});

}

function getUserSectionOnHawkeye() {
	var formData = {};
	var custid = $("#reqCustNum").val();
	$
			.ajax({
				dataType : "json",
				url : ctx + "/getSuperUSerHighLevelDataSam/" + custid,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					var cnt = 0;
					var mkttrHTML = "";
					var name = '';
					if (null != data && data != undefined) {
						var skuFound = 'N';
						dotcomActivityVOArr[0] = data.staplesDotcomActivityVo;
						var column3 = $.each(dotcomActivityVOArr[0], function(
								i, item) {
							return item;
						});
						if (null != column3
								&& column3 != undefined
								&& undefined != column3.length
								&& column3.length > 0
								&& undefined != checkUundefined(column3[0].skuNum)
								&& '' != checkUundefined(column3[0].skuNum)) {
							for (var i = 0; i < column3.length; i++) {
								mkttrHTML += '<tr>'
								var url3 = 'http://www.staples.com/product_'
										+ ((column3 != undefined && column3 != '') ? checkUundefined((column3[i] != undefined && column3[i] != '') ? column3[i].skuNum
												: "")
												: "");

								var dotActivity = checkUundefined(column3[i].cartActivity);
								if (dotActivity == 'Abandoned')
									dotActivity = '<span style="color:red;">Abandoned</span>';
								else if (dotActivity == 'Viewed')
									dotActivity = 'Viewed';
								var skuNum = checkUundefined(column3[i].skuNum);
								if ('' != skuNum && undefined != skuNum) {

									mkttrHTML += '<td>'
											+ '<span style="float:left;padding-left:0px;"><a class="a-size-small" style="word-break: break-all;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;width: auto;padding-left:0px;" href="javascript:openUrl(\''
											+ url3
											+ '\')" onclick="generateLogs(\'userSecHk\')">'
											+ checkUundefined(column3[i].skuNum)
											+ '</a></span>'
											+ '<span style="float:left;padding-left:2%;"><i id="skuDtl_'
											+ i
											+ '"  class="fa fa-info-circle fa-lg" style=" font-size: 120% !important; color: #0066c0;" tabindex="0" class="" role="button" data-toggle="popover" data-trigger="hover"></i></span><div style="" id="skuDtlId_'
											+ i + '" class="toolTip">';
									if (checkUundefined(column3[i].skuName) != '')
										mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#4d4d4d;letter-spacing:.5px;">'
												+ findAndReplace(checkUundefined(column3[i].skuName))
												+ '</div>'
												+ '<div style="padding-top:6px;color:red;border-top:1px dashed #ccc;font-size:12px;color:#4d4d4d;padding-left:10px;">Category: '
												+ checkUundefined(column3[i].skuCategory)
												+ '</div>';
									else
										mkttrHTML += '<div style="font-size:12px;padding-top:5px;padding-left:10px;padding-right:10px;color:#4d4d4d;letter-spacing:.5px;"> Not Available</div>';
									mkttrHTML += '</div>' + '</td>'
									mkttrHTML += '<td style="white-space:pre;text-align:center;">'
											+ checkUundefined(
													column3[i].lastBrowseDate)
													.substring(0, 11) + '</td>'
									mkttrHTML += '<td style="padding-right: 10%;white-space:pre;text-align: right;">$'
											+ checkUundefined(column3[i].unitPrice)
											+ '</td>';
									mkttrHTML += '<td style="white-space:pre">'
											+ dotActivity + '</td>';
								} else {
									mkttrHTML += '<td> NA </td>'
									mkttrHTML += '<td style="white-space:pre">NA</td>'
									mkttrHTML += '<td style="white-space:pre">NA</td>';
									mkttrHTML += '<td style="white-space:pre">NA</td>';
								}

								mkttrHTML += '</tr>';

							}
							skuDeatilCount = i - 1;

							var superUserSecData = '<table class="table table-striped table-hover" id="superUserSecTabId" style="box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);margin-top:0px;border:1px solid #ddd;margin-top:0px !important;"  ><thead><tr>'
									+ '<th style="padding-left:3px;">Sku# </th>'
									+ '<th>Browse Date</th>'
									+ '<th class="text-center">Unit Price</th>'
									+ '<th class="text-center">Activity</th>'
									+ '</tr>'
									+ '</thead><tbody>'
									+ mkttrHTML
									+ '</tbody>';
							$('#userSec').html(superUserSecData);

							var table = $('#superUserSecTabId').DataTable(
									{

										"bSort" : true,
										"lengthMenu" : [ [ 15, 25, 50, -1 ],
												[ 15, 25, 50, "All" ] ],
										"order" : [],
										"oLanguage" : {
											"sSearch" : "Filter: "
										},
										"paging" : false,
										"searching" : false,
										"info" : false,
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
							if (skuDeatilCount != -1) {
								for (var count = 0; count <= skuDeatilCount; count++) {
									$('#skuDtl_' + count).popover(
											{
												html : true,
												placement : 'right',
												content : $(
														"#skuDtlId_" + count)
														.html()
											}).on("hover", function() {
										$('.popover').addClass('popoverBasic');
									}).on('show.bs.popover', function() {

									});
								}
							}

						} else {
							var notFoundHtml = '<div style="box-shadow:0 1px 1px 0 rgba(0,0,0,.2), 0 6px 20px 0 rgba(0,0,0,.19);height: 225px;border: 1px solid #ddd;font-weight:700;color:darkgrey;padding-top: 20%;text-align: center;">NO DATA FOUND</div>';
							$('#userSec').html(notFoundHtml);
						}

					}

				}

			})

}

function getRewardsstatements() {

	var jsonData = '{        "query":"AND EXTRACT(YEAR FROM SLG_D_PERIOD_FROM) >= EXTRACT(YEAR FROM SYSDATE) - 2"	}';
	var custid = $("#reqCustNum").val();
	$("#dataTables-rewards_processing")
			.html(
					'<div id="example_processing_user" class="dataTables_processing" style="height: 48px;position: relative;width: 10px;padding: 10px;margin-left: -80px;border: none;margin-top: -30px;"><img width="50" height="50" src="./resources/img/progress.gif" style="border: 3px solid #26c6da;border-radius: 100px !important;"></div>');
	$("#dataTables-rewards_processing").css("display", "block");
	$
			.ajax({
				dataType : "json",
				url : ctx + "/rewards/findRewardStatements/" + custid,
				type : "POST",
				cache : false,
				data : jsonData,
				contentType : "application/json",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					$('#rewardsTableId').html("");
					var name = '';
					if (null != data && data != undefined) {
						$
								.each(
										data,
										function(i, item) {
											mkttrHTML += '<tr>'
											var used = 'N';
											var stmtCode = checkUundefined(item.STATEMENTCODE);
											var totalLen = stmtCode.length;
											var stmtCodeMidChars = '';
											var hoursDiff = '';
											var expFullDate = '';
											if ('' != checkUundefined(item.EXPDATE)
													&& null != checkUundefined(item.EXPDATE)) {
												var expDate = checkUundefined(
														item.EXPDATE)
														.split('-')[1];
												var expMon = checkUundefined(
														item.EXPDATE)
														.split('-')[0];
												var expYr = checkUundefined(
														item.EXPDATE)
														.split('-')[2];
												expFullDate = expMon + '/'
														+ expDate + '/' + expYr;
												var y2k = new Date(expYr,
														(expMon - 1), expDate);
												var today = new Date();
												hoursDiff = Date.dateDiff('h',
														today, y2k);
											}

											mkttrHTML += '<td class="datatablesTd">'
													+ formatDate(
															checkUundefined(item.PERIODFROM),
															'mmddyy')
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ formatDate(
															checkUundefined(item.PERIODTO),
															'mmddyy')
													+ '</span></td>'
													+ '<td class="datatablesTd" style="">'
													+ chkNegAmount('$'
															+ checkUundefined(parseFloat(
																	item.STATEMENTAMOUNT)
																	.toFixed(2)))
													+ '</td>'
											if (checkUundefined(item.USED) == ''
													|| checkUundefined(item.USED) == 0
													|| checkUundefined(item.USED) == '0'
													|| checkUundefined(item.USED) == 'N'
													|| checkUundefined(item.USED) == 'n')
												mkttrHTML += '<td class="datatablesTd">N</td>';
											else
												mkttrHTML += '<td class="datatablesTd"><a href="javascript:openrewardInfo(\''
														+ checkUundefined(item.STATEMENTNUM)
														+ '\',\''
														+ checkUundefined(stmtCode)
														+ '\')" style="text-decoration:underline;">Y</a></td>';
											mkttrHTML += '<td class="datatablesTd">'
													+ checkUundefined(item.TIERID)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(stmtCode)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(item.REDEEMEDMEMBERNO)
													+ '</td>'
													+ '<td class="datatablesTd">'
													+ checkUundefined(expFullDate)
													+ '</td>'

											mkttrHTML += '</tr>';
										});
					}
					$('#rewardsTableId')
							.html(
									'<table class="table table-striped table-bordered table-hover" id="dataTables-rewards"  ><thead><tr>'
											+ '<th>Period From</th>'
											+ '<th>Period To </th>'
											+ '<th>Statement </br>Amount</th>'
											+ '<th>Used</th>'
											+ '<th>Tier Id </th>'
											+ '<th>Statement Code</th>'
											+ '<th>Redeemed </br>Member No.</th>'
											+ '<th>Expiration </br>Date</th>'
											+ '</tr>'
											+ '</thead><tbody>'
											+ mkttrHTML + '</tbody></table>');
					$("#dataTables-rewards_processing").css("display", "none");
					$('#dataTables-rewards').dataTable(
							{
								"lengthMenu" : [ [ 5, 15, 25, -1 ],
										[ 5, 15, 25, "All" ] ],
								"bSort" : true,
								"order" : [],
								"bProcessing" : true,
								"oLanguage" : {
									"sSearch" : "Filter: "
								},
								"aoColumns" : [ {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								}, {
									"bSortable" : true
								} ]
							}).fnDraw();
					$("#dataTables-rewards td:nth-child(5)").css("white-space",
							"nowrap");
					$('#dataTables-rewards_length label').css("color",
							"#004c74");
					$('#dataTables-rewards_length label').css("font-size",
							"15px");
					$('#dataTables-rewards_length label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-rewards_length label')
							.children()
							.attr(
									"style",
									"border-radius:3px !important;color:#004c74;font-size:14px;font-family:helveticaneuebold;");
					$('#dataTables-rewards_filter label').css("color",
							"#004c74");
					$('#dataTables-rewards_filter label').css("letter-spacing",
							"1px");
					$('#dataTables-rewards_filter').css("text-align", "right");
					$('#dataTables-rewards_filter label').css("font-family",
							"helveticaneuebold");
					$('#dataTables-rewards_filter label').children().attr(
							"style",
							"border-radius:3px !important;color:#004c74;");
					$('#dataTables-rewards_filter label').css("float", "right");
					$('#dataTables-rewards_info').css("color", "#004c74");
					$('#dataTables-rewards_info').css("font-weight", "600");
					$('#dataTables-rewards_paginate')
							.css("text-align", "right");
					$('.form-inline .form-control').css("color", "#004c74");
					$('.form-inline .form-control').css("border",
							"1px solid #004c74");
					$('select .form-control.input-sm').css("border",
							"1px solid #004c74");
					$('#dataTables-rewards').removeClass('display').addClass(
							'table table-striped table-bordered');
					$('#dataTables-rewards_filter input[type="search"]').attr(
							'placeholder', 'Search').css({
						'width' : '250px',
						'display' : 'inline-block'
					});

				}

			})
}
function openrewardInfo(stmtNum, encryptedStmtCode) {

	var formData = {};
	var custid = $("#reqCustNum").val();
	var rewardsHtml = ''
	var statementNum = stmtNum;
	$('#rewardsInfoBody').html('');
	$('#stmtCode').html('');
	$('#stmtCode').html(' - ' + encryptedStmtCode);

	$
			.ajax({
				dataType : "json",
				url : ctx + "/rewards/findRewardForStatement/" + custid + "/"
						+ statementNum,
				type : "POST",
				cache : false,
				data : JSON.stringify(formData),
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if (undefined != data && null != data && 'null' != data
							&& '' != data) {
						$
								.each(
										data,
										function(i, item) {
											rewardsHtml = '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Member No. - </strong> <span id="memnum" style="padding-left:10px;">'
													+ checkUundefined(item.MEMBERNO)
													+ '</span></div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Transaction Date - </strong> <span id="tranDate" style="padding-left:10px;">'
													+ formatDate(
															checkUundefined(item.TRANSACTIONDATE),
															'yymmdd')
													+ '</span></div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Store - </strong> <span id="store" style="font-family: Helvetica;font-size: 16px;padding-left:10px;">'
													+ checkUundefined(item.STORE)
													+ '</span></div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Order Number - </strong> <span id="stmtamt" style="color:#444;padding-left:10px;">'
													+ checkUundefined(item.ORDERNUMBER)
													+ '</span></div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Statement Amount - </strong> <span id="stmtamt" style="color:#444;padding-left:10px;">'
													+ formatNum(chkNegAmount(checkUundefined(
															item.STATEMENTAMOUNT)
															.replace('$', '')))
													+ '</span></div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Statement Code - </strong><span id="stmtcd" style="color:#444;padding-left:10px;">'
													+ checkUundefined(item.STATEMENTCODE)
													+ '</span> </div>'
													+ '<div class="" style="padding:5px;color:#4d4d4d4;"><strong style="font-family: Helvetica;font-size: 16px;">Transaction - </strong> <span id="tran" style="padding-left:10px;"> '
													+ checkUundefined(item.TRANSACTION)
													+ '</span></div>';
										});
						$('#rewardsInfoBody').html('');
						showRewardsInfoPopUp();
						$('#rewardsInfoBody').html(rewardsHtml);
					} else {
						showRewardsInfoPopUp();
						$('#rewardsInfoBody')
								.html(
										'<div style="text-align:center;font-weight:700;color:darkgrey;padding-top:10px;">NO DATA FOUND</div>');
					}

				}

			})
}
function showRewardsInfoPopUp() {
	$('#rewardsInfoModal').modal({
		"backdrop" : "static",
		handle : ".modal-header",
		"keyboard" : true,
		"show" : true
	});
}
function formatDate(str, format) {
	if (format == 'ddmmyy') {
		if (undefined != str && '' != str) {
			var mdy = str.split('-')
			return (mdy[1] + '/' + mdy[0] + '/' + mdy[2]);
		} else
			return str;
	} else if (format == 'mmddyy') {
		if (undefined != str && '' != str) {
			var mdy = str.split('-')
			return (mdy[0] + '/' + mdy[1] + '/' + mdy[2]);
		} else
			return str;
	} else if (format == 'yymmdd') {
		if (undefined != str && '' != str) {
			var mdy = str.split('-')
			return (mdy[1] + '/' + mdy[2] + '/' + mdy[0]);
		} else
			return str;
	}

}

function getLatestFiscalDateOrderSAMNew() {

	var custid = $("#reqCustNum").val();

	var formData = {};
	$
			.ajax({
				dataType : "json",
				url : ctx + "/sales/customer/" + custid + "/latestorderdate",
				type : "GET",
				async : false,
				data : "",
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					if (data != null && data != undefined) {
						latestFiscalDateOrderSAMNew = checkUundefined(data.latestOrderDate);
						if (checkUundefined(latestFiscalDateOrderSAMNew).length > 0) {
							latestFiscalDateOrderSAMNew = latestFiscalDateOrderSAMNew
									.replace("-", "");
						}
					}
				}

			})

}

function getCategorySavingsData() {
	var jsonData = {};
	var custid = $("#reqCustNum").val();

	$
			.ajax({
				dataType : "json",
				url : ctx + "/rewards/findCategorySavings/" + custid,
				type : "POST",
				cache : false,
				data : jsonData,

				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {
					var cnt = 0;
					var mkttrHTML = "";
					$('#catSavingContent').html("");
					var name = '';
					if (null != data && data != undefined && '' != data) {
						$
								.each(
										data,
										function(i, item) {
											mkttrHTML += '<tr>'

											mkttrHTML += '<td class="datatablesTd">'
													+ checkUundefined(item.BENEFIT)
													+ '</td>'
													+ '<td class="datatablesTd" style="text-align:center;">'
													+ checkUundefined(item.SKU_CATEGORY)
													+ '</td>'
													+ '<td class="datatablesTd" style="text-align: right;padding-right: 8%;">'
													+ chkNegAmount('$'
															+ formatAmount(checkUundefined(parseFloat(
																	item.YTD_SLS_AMT)
																	.toFixed(2))))
													+ '</td>'
													+ '<td class="datatablesTd" style="text-align: right;padding-right: 8%;">'
													+ chkNegAmount('$'
															+ formatAmount(checkUundefined(parseFloat(
																	item.YTD_SAV_AMT)
																	.toFixed(2))))
													+ '</td>';

											mkttrHTML += '</tr>';
										});

						$('#catSavingContent')
								.html(
										'<table id="acrylic"><thead> <tr> <th style="text-align:left;width:33%;">Benefit</th> <th>Category</th> <th style="text-align:center;">YTD Sales Amount</th> <th style="text-align:center;">YTD Savings Amount</th></tr></thead><tbody>'
												+ mkttrHTML
												+ '</tbody></table>');
					} else {
						$('#catSavingContent')
								.html(
										'<div  style="max-height: 400px;padding:22px 20px;font-size: 16px;font-family: arialmt;overflow-y:auto;"><div style="text-align:center;font-weight:700;color:darkgrey;">NO DATA FOUND</div></div>');
					}

				}

			})
}
function getColorScheme(freq, days, id) {
	var color = ''
	if (undefined != freq && undefined != days && '' != days && '' != days
			&& id != 'spentId') {
		freq = parseInt(freq);
		days = parseInt(days);
		if (freq >= days)
			color = '#00C851';
		else
			color = 'red';
	} else if (undefined != freq && undefined != days && '' != days
			&& '' != days && id == 'spentId') {
		freq = parseInt(freq);
		days = parseInt(days);
		if (freq < days)
			color = '#00C851';
		else
			color = 'red';
	}
	$('#' + id).css('color', color);
	return color;
}

function generateSegLog() {
	logUserActivityDotCom(7047,
			'viewed customer segment details from dashboard');
}

function downloadSavingsReport(){
	logUserActivity(2032, 'Download Savings Details Excel Report');
	$("#savingsReportForm").attr("action","./downloadSamExcel/Savings")
	   var custNum= $("#reqCustNum").val();
	   $('#savingsReportForm').submit();
}