<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@page import="java.net.URL"%>
<html lang="en" class="no-js" ><!--<![endif]--><!-- BEGIN HEAD --><head>
<meta charset="utf-8">
<title>Helios</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport">
<meta content="" name="description">
<meta content="" name="author">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/font.min.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/icons.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/fontaw.min.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/component.css" id="style_components" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/uniformdefault.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap-switch.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/dataTables.bootstrap.css" rel="stylesheet">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="./resources/stylesheet/daterange.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/calender.min.css" rel="stylesheet" type="text/css">
<link href=./resources/stylesheet/jqvmap.css" rel="stylesheet" type="text/css">
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="./resources/stylesheet/task.css" rel="stylesheet" type="text/css">
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->

<link href="./resources/stylesheet/plugin.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/layout.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/grey.css" rel="stylesheet" type="text/css" id="style_color">
<link href="./resources/stylesheet/custom.css" rel="stylesheet" type="text/css">
<link href="./resources/dist/css_new/sb-admin-2.css" rel="stylesheet">
<link href="./resources/stylesheet/light.css" rel="stylesheet" type="text/css">
<link href=./resources/stylesheet/bootstrap.multiselect.css" rel="stylesheet" type="text/css">
<link href="./resources/stylesheet/bootstrap.toggle.min.css" rel="stylesheet" type="text/css">

<!-- END THEME STYLES -->

<style>
  .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px;width:29px;height:29px; }
  .toggle.ios .toggle-handle { border-radius: 20px; width:29px;height:29px;}
</style>
<style type="text/css">.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}</style></head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
<%
		final String URL = new URL(request.getScheme(),	request.getServerName(), request.getServerPort(),request.getContextPath()).toString();
		final String URLold = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<input type="hidden" id="svsURL" value="<%=URL%>"></input>
<!-- BEGIN HEADER -->
<div >
 <%-- <tiles:insertAttribute name="header" />  --%> 
</div>
<div class="clearfix">
</div>
<div class="container" id="errorContainer">
	<div >
	 <%-- <tiles:insertAttribute name="menu" /> --%>  
	  <tiles:insertAttribute name="body" />  
	</div>
	<div class="">
	

		<!-- <div class="page-footer-inner">
			 2014 © Metronic by keenthemes. <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
		</div> -->
		<a id="scrollTop" href="#" data-placement="left" data-toggle="tooltip" title="Go To Top" class="scroll-to-top"  style="display: none;">
			<!-- <input type="checkbox" checked data-toggle="toggle" data-style="ios"> --><i class="icon-arrow-up"></i>
		</a>
	</div>
</div>

<!-- <script src="./resources/javascript/bootstrap-toggle-min.js" type="text/javascript"></script> -->
</body>
</html>