<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Property List</title>                 
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script>
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	zoomEnabled: true,
	title:{
		text: "Real Estate Rates",
		fontFamily: "Arial",
		fontWeight: "normal"
	},
	axisX: {
		title:"Gross Living Area (in sq. ft)",
		labelFontFamily: "Arial",
		labelFontWeight: "normal"
	},
	axisY:{
		title: "Sale Price (in USD)",
		labelFontFamily: "Arial",
		labelFontWeight: "normal",
		valueFormatString: "$#,##0k"
	},
	data: [{
		type: "scatter",
		toolTipContent: "<b>Area: </b>{x} sq.ft<br/><b>Price: </b>${y}k",
		dataPoints: [
		<c:forEach var="property" items="${properties}">
			{x: ${property.grLivArea}, y: ${property.salePrice}},
		</c:forEach>
			{x: 0, y: 0}
		]
	}]
});
chart.render();

}
</script>
</head>
<body>
 <div class="container">
  <div class="starter-template">
   <h2>Regression</h2>
   <div class="table-responsive">
   <table
    class="table table-striped table-hover table-condensed table-bordered">
    <tr>
     <th width="50%">Slope</th>
     <th width="50%">Intercept</th>
    </tr>
     <tr>
      <td>${slope}</td>
      <td>${intercept}</td>
     </tr>
   </table>
   </div>
   <div id="chartContainer" style="height: 370px; width: 100%;"></div>
	
   
   <h2>List a property...</h2>
   <div class="table-responsive">
   <form action="/properties" method="post">
   <table
    class="table table-striped table-hover table-condensed table-bordered">
    <tr>
     <th width="40%">Gross Living Area</th>
     <th width="40%">Sale Price</th>
     <th width="20%"></th>
    </tr>
     <tr>
      <td><input type="number" name="grLivArea"></td>
      <td><input type="number" name="salePrice"></td>
      <td><input type="submit"></td>
     </tr>
   </table>
   </form>
   </div>
   <h2>Listed Properties</h2>
   <div class="table-responsive">
   <table
    class="table table-striped table-hover table-condensed table-bordered">
    <tr>
     <th width="40%">Gross Living Area</th>
     <th width="40%">Sale Price</th>
     <th width="20%">Action</th>
    </tr>
    <c:forEach var="property" items="${properties}">
     <tr>
      <td>${property.grLivArea}</td>
      <td>${property.salePrice}</td>
      <td>
      	<a href="/edit/${property.id}">Edit</a>
                	&nbsp;&nbsp;&nbsp;
                	<a href="/delete/${property.id}">Delete</a>
      </td>
     </tr>
    </c:forEach>
   </table>
   </div>
  </div>

 </div>

 <script type="text/javascript"
  src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>