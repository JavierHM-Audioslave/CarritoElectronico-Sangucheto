<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<h1 class="text-center"><u>ALTA A UN PRODUCTO EN EL STOCK</u></h1>
		<br><br><br>
		<form action="alta/eleccion" method="POST">
			<input name="Nombre" id="nombre" type="text" placeholder="Escribe el nombre del producto" class="form-control"></input><br><br>
			<input name="Precio" id="precio" type="text" placeholder="Escribe el precio del producto" class="form-control"></input><br><br>
			<input name="TipoProd" id="tipoprod" type="text" placeholder="El producto ingresado, es CONDIMENTO o INGREDIENTE?" class="form-control"></input>
			<br><br>
			<div class="text-center">
			<input type="submit" value="Confirmar" class="btn btn-primary"></input>
			</div>
		</form>

	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="js/jquery.min.js" ></script>
	    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	    <script src="js/bootstrap.min.js" type="text/javascript"></script>
		
	</body>
</html>