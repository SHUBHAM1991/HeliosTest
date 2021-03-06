<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@page import="java.net.URL"%>
<html lang="en" class="no-js" ><!--<![endif]--><!-- BEGIN HEAD --><head>
<meta charset="utf-8">
<title>Helios Login</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport">
<meta content="" name="description">
<meta content="" name="author">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/font.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/icons.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/fontaw.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/font-awesome/css/font-awesome.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/component.css?helios_version=${initParam.helios_ver}" id="style_components" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/uniformdefault.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap-switch.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/dataTables.bootstrap.css?helios_version=${initParam.helios_ver}" rel="stylesheet">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="./resources/stylesheet/daterange.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/calender.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href=./resources/stylesheet/jqvmap.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="./resources/stylesheet/task.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->

<link href="./resources/stylesheet/plugin.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/layout.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/grey.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css" id="style_color">
<link href="./resources/stylesheet/custom.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/dist/css_new/sb-admin-2.css?helios_version=${initParam.helios_ver}" rel="stylesheet">
<link href="./resources/stylesheet/light.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href=./resources/stylesheet/bootstrap.multiselect.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap.toggle.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">

<!-- END THEME STYLES -->

<style>
  .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px;width:29px;height:29px; }
  .toggle.ios .toggle-handle { border-radius: 20px; width:29px;height:29px;}
</style>
<style type="text/css">.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}</style></head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
<%
		final String URL = new URL(request.getScheme(),	request.getServerName(), request.getServerPort(),request.getContextPath()).toString();
		final String URLold = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<input type="hidden" id="svsURL" value="<%=URL%>"></input>
<!-- BEGIN HEADER -->
<div></div>
<div class="clearfix">
</div>
<div class="container" id="errorContainer">
	<div>
	<!-- <header style="background-color: #ac0000;">
	<div style="padding: 10px;">
      <img src="./resources/img/StaplesLogo.png">
    </div>
     </header> -->
	  <tiles:insertAttribute name="menu" /> 
	  <div  class="page-content-wrapper" style="background-color:#fff;">
			<div class="printableDashboard page-content" style="background:url(./resources/img/Login_Background_Full_size_best.png) repeat;background-size:100%;height:1000px;margin-left:0%;">  
	  <tiles:insertAttribute name="body" />  
	  </div>
	  </div>
	</div>
	<div class="">
		<a id="scrollTop" href="#" data-placement="left" data-toggle="tooltip" title="Go To Top" class="scroll-to-top"  style="display: none;">
			<i class="icon-arrow-up"></i>
		</a>
	</div>
</div>
</body>
</html>