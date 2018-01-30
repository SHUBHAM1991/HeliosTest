<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
        <%@page import="java.net.URL"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Helios</title>
<meta charset="utf-8">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- <meta name="description" content="">
    <meta name="author" content=""> -->
    <!-- Bootstrap Core CSS -->
<link href="./resources/bower_components/bootstrap/dist/css_new/bootstrap.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./resources/bower_components/metisMenu/dist/metisMenu.min.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="./resources/dist/css_new/timeline.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/dist/css_new/sb-admin-2.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="./resources/bower_components/morrisjs/morris.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- Custom Fonts -->
    <!-- <link href="./resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"> -->
    <link href="./resources/stylesheet/font-awesome/css/font-awesome.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
<!--  <link href="./resources/stylesheet/style.css" rel="stylesheet"> -->
    <!-- DataTables CSS -->
    <link href="./resources/stylesheet/dataTables.bootstrap.css?helios_version=${initParam.helios_ver}" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="./resources/stylesheet/dataTables.responsive.css?helios_version=${initParam.helios_ver}" rel="stylesheet">
     <link href="./resources/stylesheet/bootstrap.multiselect.css?helios_version=${initParam.helios_ver}" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<%
		final String URL = new URL(request.getScheme(),	request.getServerName(), request.getServerPort(),request.getContextPath()).toString();
		final String URLold = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	%>

	<input type="hidden" id="svsURL" value="<%=URL%>"></input>
	 <tiles:insertAttribute name="body" /> 

</body>
</html>