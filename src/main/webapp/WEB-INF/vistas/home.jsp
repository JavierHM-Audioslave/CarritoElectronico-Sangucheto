<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Inicio - Home</title>

    	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">		
	</head>
	<body>
		
		<h1 class="page-header text-center"><u>Sangucheto</u></h1>
		<form action="/Sangucheto/agregarProducto" method="post">	
			<input type="submit" value="Agregar producto" class="btn btn-primary"></input><br></br>
		</form>
		<form action="/Sangucheto/muestraStock" method="post">	
			<input type="submit" value="Mostrar el stock de productos" class="btn btn-primary"></input><br></br>
		</form>
		<form action="/Sangucheto/altaProducto" method="post">	
			<input type="submit" value="Dar alta a un producto" class="btn btn-success"></input><br></br>
		</form>
		<form action="/Sangucheto/agregarStock" method="post">	
			<input type="submit" value="Agregar stock a un producto" class="btn btn-success"></input><br></br>
		</form>
		<form action="/Sangucheto/borrarStock" method="post">	
			<input type="submit" value="Borrar stock total a un producto" class="btn btn-warning"></input><br></br>
		</form>

    	<!-- Placed at the end of the document so the pages load faster -->
    	<script src="js/jquery.min.js" ></script>
	    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	    <script src="js/bootstrap.min.js" type="text/javascript"></script>
	    						
	</body>
</html>