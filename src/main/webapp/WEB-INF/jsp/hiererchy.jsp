<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title>Users</title>
<link rel="stylesheet" href="./resources/stylesheet/bootstrap.min1.css"/>
    <link rel="stylesheet" href="./resources/stylesheet/datatable.responsive.css"/>
    <link href="./resources/stylesheet/bootstrap.multiselect.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./resources/stylesheet/hiererchy.css"/>
    <link rel="stylesheet" href="./resources/stylesheet/common-print.css" type="text/css" media="print" >
<style type="text/css">
ul.multiselect-container {
    overflow-y: auto;
    overflow-x: hidden;
    width:400px !important;
    max-width: 400px !important;
    max-height: 300px !important;
    
}

.multiselect-container > li > a { max-width:400px;white-space: normal; }
.dropdown-menu>.active>a, .dropdown-menu>.active>a:focus, .dropdown-menu>.active>a:hover{
color:#004c74 !important;
background-color:#fff !important;
} 
li.multiselect-item,.multiselect-group,.active{
background-color:lightslategray !important;
color:#fff !important;
}
</style>  
</head>
<body>
 
    <div class="printableUserToDoList container">
   <div class="col-lg-12 col-md-12" style="font-family: Helvetica; font-size: 10px; color: #337ab7; letter-spacing: .5px;padding-top:5px;padding-right:auto;" align="right">You logged in as <span id="loggedInUserNameSpan" style="margin-right:15px;"></span></div>
        <div class="row col-lg-12 col-sm-12" style="padding-left:9%;">
         <div class="panel panel-default" style="border-color:#004c74;" align="left">
         				
                        <div  class="panel-heading" align="left" style="font-family:Helvetica Neue Bold !imporatnt;font-size:14px;font-weight;800;background-color:#004c74;border-color:#004c74;color:#fff;height:50px">
                         
                          <%
                          String reqCustNum = (String)request.getParameter("accID");
                          String LoggedInUserID = (String)request.getParameter("LoggedInUserID");
                          %>
                          <div align="right" style="width:100%">
                           
                          <form name="viewAllCust" action="./cp_online_retail" style="margin-bottom:5px;" method="post">
                         	<div class="col-md-12 col-sm-12" style="padding-right:0px !important">
	                         	 <div align="left" class="col-md-4 col-sm-4" style="padding-left:0px !important">
	                          		<span style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;"> <!-- <i style="font-size:18px !important;line-height:12px; !important;font-weight: Bold;" class="fa fa-angle-double-right"></i> -->
	                            	<span id="agentName" style="font-family: Helvetica !important;font-weight:bold;font-size: 16px;padding-left: 1%;x;padding-right: 1%;"> Users</span> 
	                         	 </span>
	                          	</div>
	                          	<div align="right" class="col-md-8 col-sm-8">
	                         	<input type="hidden" name="reqCustNum" value="<%=LoggedInUserID%>" />
	                         	<input type="hidden" name="accID" value="<%=reqCustNum %>" />
	                         	<input type="hidden" name="LoggedInUserID" id="LoggedInUserID" value="<%=LoggedInUserID %>" />
	                         	<input type="hidden" name="repId" id="repId" value="${requestScope['repId']}" />
								<input type="hidden" name="repRoleCode" id="repRoleCode" value="${requestScope['repRoleCode']}" />
								<input type="hidden" name="loggedInUserName" id="loggedInUserName" value="${requestScope['loggedInUserName']}" />
								<input type="hidden" name="customerNumber" id="customerNumber" value="${requestScope['customerNumber']}" />
								<input type="hidden" name="assignType" id="assignType" value='${requestScope["assignType"]}' />
	                         	<button type="button" onclick="document.forms['viewAllCust'].submit();" style="margin-right:1px;border-radius:10px !important ;" class="btn btn-primary btn-xs" >View all customers</button>
	                         	</div>
                         	</div>
                         	</form>
                         	</div>
                        </div>
                        <!-- /.panel-heading -->
                       
                        <div class="panel-body">
                         <div class="col-md-12 col-sm-12" >
                         <div style="background-color: gainsboro;border-bottom: 1px solid #ccc;margin-top:2px;margin-bottom:1em;padding-left: 5px;margin-left:0px;height:auto;padding-top: 5px; padding-right: 5px;" class="col-lg-12 col-sm-12">
                         	<c:if test="${not empty roles}">
					          <div class="col-lg-12 col-sm-12" style="height: auto; padding-left: 0px ! important;">
					         		 <div id="filterBy" class="col-lg-2 col-sm-3" align="left" style="padding-left:0px !important;padding-right:5px !important">
					          			<label style="color: rgb(0, 76, 116); font-weight: bold;">Filter By Role:</label>
						       		</div>
						       		<div class="col-lg-8 col-sm-8" align="left" style="padding-left: 0px ! important;">	
						       			 
						       			 <div class="col-lg-4 col-sm-6" align="left" style="padding-left: 0px ! important;">
							       			 <select class="form-control input-sm" style="color:#004c74;border:1px solid #004c74; margin-right:2px;margin-left:3px;" id="rolesFilter" onchange="filterTable(this)">
							            		<option value="" selected="selected">All</option>
												<c:forEach var="fullrole" items="${roles}">
												<c:set var="role" value="${fn:split(fullrole, '-')}" ></c:set>
													<option value="<c:out value="${role[1]}"/>"><c:out value="${role[0] eq '5' ? 'REPRESENTATIVE': role[1]}"/></option>
												</c:forEach>
							        		 </select>
						        		 </div>
						        		  <div class="col-lg-5 col-sm-5" align="left" style="padding-left: 0px ! important;">
							        		 <c:if test="${not empty repRoles}">
									        		  <select multiple  class="form-control input-sm"  style="width:auto;color:#004c74;border:1px solid #004c74;display:none; margin-right:2px;" id="rolesSubFilter" >
									            		<option value="" selected="selected">All</option>
														<c:forEach var="repRole" items="${repRoles}">	
															<c:forEach var="rRoles" items="${repRole.value}">													
																<option value="<c:out value="${rRoles}"/>"><c:out value="${rRoles}"/></option>
															</c:forEach>
														</c:forEach>
									        		 </select>
							        		 </c:if>
						        		 </div>
						        	</div> 	
						        </div>	
					         </c:if>
                         </div>
                           
                           <div>
                           <div id="OrderDetailsInfoId" class="dataTable_wrapper" style="border:1px solid #e5e5e5">
						          <table  class="table table-striped table-hover dt-responsive" id="dataTables-HInfo" cellspacing="0" width="100%">
						              <thead>
						                 <th>Employee Id</th>
						                   <th>Name</th>
						                   <th>Role</th>
						                   <th style="display:none">Designation</th>
						                   <th style="display:none">Level</th>
						              </thead>
						              <tbody>
						              <c:if test="${not empty lists}">
										<c:forEach var="listVar" items="${lists}">
										<tr>
										<td><a style="curshor:pointer;font-size:13px;font-weight:bold;color:#ef3f3f !important;text-decoration:underline;" href="./cp_online_retail?reqCustNum=<c:out value="${listVar.number}"/>&accID=<%=reqCustNum%>&LoggedInUserID=<%=LoggedInUserID%>&from=home&assignType=${requestScope['assignType']}"><c:out value="${listVar.number}"/></a></td>
										<td><span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><c:out value="${listVar.fullname}"/></span></td>
										<c:set var="designation" value="${listVar.designation}"/>
										<c:choose>
											<c:when test='${designation == "REP"}'>
												<td style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><c:out value="${listVar.repRoleCd}"/></td>
											</c:when>
											<c:otherwise>
												<td style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><c:out value="${listVar.designation}"/></td>
											</c:otherwise>
										</c:choose>
										<td style="display:none;"><span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><c:out value="${listVar.designation}"/></span></td>
										<td style="display:none;"><span style="color:#444444 !important;font-size:13px !important;font-family:Helvetica !important;font-weight:bold !important;"><c:out value="${listVar.level}"/></span></td>
										</tr>
									</c:forEach>
									</c:if>
						              </tbody>
						          </table>
						     </div>
                            </div>
				            </div>
				        </div>
         </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/jsp/Training.jsp"></jsp:include>
    <!-- log user activity; rep. data -->
	
	
<script src="./resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-migrate.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="./resources/javascript/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery-uniform.min.js" type="text/javascript"></script>
<script src="./resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="./resources/javascript/bootstrap-min.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-dropdown.js" type="text/javascript"></script>
<script src="./resources/javascript/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="./resources/javascript/dataTables.bootstrap.min.js"></script>
<script src="./resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="./resources/dist/js/sb-admin-2.js"></script>
<script src="./resources/javascript/jquery.PrintArea.js" type="text/javascript"></script>
<script src="./resources/javascript/user-activity.js" type="text/javascript"></script>
<script src="./resources/javascript/jquery.cookie.js" type="text/javascript"></script>
<script src="./resources/javascript/global-default-exception-handler.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	var assignType=$("#assignType").val();
	//alert("-->"+assignType);
	$("#loggedInUserNameSpan").html($("#loggedInUserName").val());
	$('#dataTables-HInfo').DataTable({
		 "order": [],
		 "pagingType": "simple_numbers",
	        "stateSave": false,
	        "oLanguage": { "sSearch": "Filter : "},
		  "aoColumns": [{"bSortable": true, "sWidth": '33%'},
						{"bSortable": true, "sWidth": '33%'},
						{"bSortable": true, "sWidth": '32%'},
						{"bSortable": true, "sWidth": '1%'},
						{"bSortable": true, "sWidth": '1%'}
						]
			
	});  

	$("#dataTables-HInfo td").css("WORD_BREAK","BREAK-ALL");
	$('#dataTables-HInfo_length label').css("color","#004c74");
	$('#dataTables-HInfo_length label').css("font-weight","600");
	$('#dataTables-HInfo_filter label').css("color","#004c74");
	$('#dataTables-HInfo_filter').css("text-align","right");
	$('#dataTables-HInfo_filter label').css("font-weight","600");
	$('#dataTables-HInfo_filter label').css("font-weight","bold");
	$('#dataTables-HInfo_info').css("color","#004c74");
	$('#dataTables-HInfo_info').css("font-weight","600");
	$('#ddataTables-HInfo_paginate').css("text-align","right");
	$("#dataTables-HInfo_length").parent().removeClass("col-sm-6").addClass("col-sm-5");
	$("#dataTables-HInfo_filter").parent().removeClass("col-sm-6").addClass("col-sm-7");
	$("#dataTables-HInfo_info").parent().parent().removeClass("row");
	$("#dataTables-HInfo_info").parent().parent().css({"padding":"10px","border-top":"1px solid #e5e5e5","height":"70px"});
	
	 $('#dataTables-HInfo').removeClass('display').addClass('table table-striped table-bordered');
	 $('.dataTables_filter input[type="search"]').attr('placeholder','Enter Employee ID OR Name OR Role').css({'width':'200px','display':'inline-block'});
	$("#rolesSubFilter").change(
			function(){
				filterSubTable();
			}
		);
	});



function filterTable(th)
{
	var x = th.selectedIndex;
	var y = th.options;
	if(y[x].text == 'REPRESENTATIVE')
		$('#rolesSubFilter').css("display","block");
	else 
		$('#rolesSubFilter').css("display","none");
	var table = $('#dataTables-HInfo').DataTable();
	if(y[x].value!='' ){
		table.columns(2).search("").columns(3).search(y[x].value).draw();
	}
	else 
	{
			table.columns(2).search("").columns(3).search("").draw();
	}
}
function filterSubTable()
{
	var searchData = "";
	$('#rolesSubFilter :selected').each(function(i, selected){
		searchData = searchData + $(selected).val()+ "|"; 
	});
	searchData = searchData.substr(0,searchData.length-1);
	var table = $('#dataTables-HInfo').DataTable();
	if(searchData!='')
	{
		table.columns(2).search("^"+searchData+"$", true, false).draw();
	}
	else 
	{
		table.columns(2).search("").columns(3).search("REP").draw();
	}
}
</script>
</body>
</html>