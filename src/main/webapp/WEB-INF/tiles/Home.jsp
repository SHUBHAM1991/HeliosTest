 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="chartContainer">
  
  <script src="http://dimplejs.org/dist/dimple.v2.1.2.min.js"></script>
  <script type="text/javascript">
    var svg = dimple.newSvg("#chartContainer", 590, 400);
    d3.tsv("./WEB-INF/tiles/data.tsv", function (data) {
      var myChart = new dimple.chart(svg, data);
      myChart.setBounds(60, 30, 510, 305)
      var x = myChart.addCategoryAxis("x", "Month");
      x.addOrderRule("Date");
      myChart.addMeasureAxis("y", "Unit Sales");
      myChart.addSeries(null, dimple.plot.bar);
      myChart.draw();
    });
  </script>
</div>
</body>
</html>

 
 
 
 
