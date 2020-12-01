<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                            
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<title>Edit Property</title>
</head>
<body>
	<div align="center">
		<h2>Edit Property</h2>
		<br />
		<form action="#" target="/save" method="post">

			<table class="table table-striped table-hover table-condensed table-bordered">
				<tr>				
					<td>Product ID:</td>
					<td>
						<input type="text" value="${property.id}" readonly="readonly" />
					</td>
				</tr>			
				<tr>				
					<td>Gross Living Area:</td>
					<td>
						<input type="number" value="${property.grLivArea}" />
					</td>
				</tr>
				<tr>
					<td>Sale Price:</td>
					<td><input type="number" value="${property.salePrice}"/></td>
				</tr>
			</table>
			<input type="submit">
		</form>
	</div>
</body>
</html>