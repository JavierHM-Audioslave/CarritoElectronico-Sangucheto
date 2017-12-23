<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Agregar un producto</title>
		
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">		
		
		
	</head>
	<body>
		<h1 class="text-center" >AGREGAR UN PRODUCTO</h1>
		<form action="agregando" method="POST">
			Elija el producto a agregar desde la lista desplegable: 
			<option>
				<c:forEach items="${ingredientes}" var="producto">
					${producto.getNombre()};
				</c:forEach>
			</option>
			
			
			<select name="cbDeIngYCond">
				
			</select>
		</form>

	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="js/jquery.min.js" ></script>
	    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	    <script src="js/bootstrap.min.js" type="text/javascript"></script>		
		
	</body>
</html>