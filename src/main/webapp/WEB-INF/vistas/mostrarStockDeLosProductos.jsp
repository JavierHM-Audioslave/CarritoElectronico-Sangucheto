<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet" >
    	<!-- Bootstrap theme -->
    	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<h1 class="text-center">STOCK DISPONIBLE</h1>
			<c:forEach items="${stock}" var="producto">
			<ul class="list-inline">
				<li>${producto.key.getNombre()}  ---   ${producto.value}</li><br>
			</ul>
			</c:forEach>
			<form action="/Sangucheto/verHome" method="post">
				<input type="submit" value="Volver a HOME" class="btn btn-primary"></input>
			</form>
			<script src="js/jquery-1.11.3.min.js"></script>
   			<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    		<script src="js/bootstrap.min.js" type="text/javascript"></script>
    		<div class="container">
    	</div>
	</body>
</html>