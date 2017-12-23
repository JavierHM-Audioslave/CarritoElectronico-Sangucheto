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
		<h1 class="text-center">AGREGAR PRODUCTO A CARRITO</h1><br><br>
		<h3><strong>Usuario: ${usuario}</strong></h3>
		<h3><strong>Productos agregados:</strong></h3>
		<!---------------------------------------------------------------------------------------->
		<c:forEach items="${prodsIngr}" var="prodsIngrediente">
			${prodsIngrediente.nombre}<br>
		</c:forEach>
		<c:forEach items="${prodsCond}" var="prodsCondimento">
			${prodsCondimento.nombre}<br>		
		</c:forEach>
		<br><br>
		<div class="lead">PRECIO TOTAL: ${precioTotal}<br>
		PRECIO CON DESCUENTO: ${precioConDesc}<br>
		AHORRADO: ${ahorro}<br>
		<!---------------------------------------------------------------------------------------->
		<form action="/Sangucheto/descuento" method="POST" form="form-inline">
			EL DESCUENTO A APLICAR ES DE: 
			<input type="text" name="valorDescuento" class="form-control"></input>
			<input type="submit" value="Confirmar el descuento" class="btn btn-success btn-lg"></input>
		</form>
		
		<!---------------------------------------------------------------------------------------->
		
		
		<c:if test="${not empty error}">
			${error}
		</c:if>
		<!---------------------------------------------------------------------------------------->
		<br><br>
		ELIJA EL PRODUCTO A AGREGAR
		<br>
		<br>
		
		<!---------------------------------------------------------------------------------------->
		<form action="/Sangucheto/analizandoIngreso" method="POST">
		<!-- 	Usuario: <input type="text" name="usuarioActual"></input> -->
			<select name="combobox" class="form-control">
				<optgroup label="INGREDIENTES">
					<c:forEach items="${stockDeProds}" var="prod">
						<option>${prod.key.getNombre()}</option>
					</c:forEach>
				</optgroup>
				<optgroup label="CONDIMENTOS">
					<c:forEach items="${stockDeConds}" var="prod">
						<option>${prod.key.getNombre()}</option>
					</c:forEach>
				</optgroup>
			</select>
			<br><br>
			ELIJA LA CANTIDAD A AGREGAR DEL PRODUCTO SELECCIONADO: <input name="cant" type="text"></input>
			<input type="hidden" name="descuentoAAplicar" value="${descuento}">
			<input type="submit" value="Agregar al carrito" class="btn btn-success btn-lg">
		</div>	
		</form>
		
		<br><br><br>
		
		
		<!---------------------------------------------------------------------------------------->
		<form action="/Sangucheto/vaciarCarrito" method="POST">
			<div class="text-center">
			<input type="submit" value="VACIAR CARRITO" class="btn btn-warning btn-lg"></input>			
			</div>
		</form>
		<!---------------------------------------------------------------------------------------->
			
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="js/jquery.min.js" ></script>
	    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	    <script src="js/bootstrap.min.js" type="text/javascript"></script>				
			
	</body>
</html>