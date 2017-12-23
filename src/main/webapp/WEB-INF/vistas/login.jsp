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
		<h1 class="text-center">LOGUEO DE USUARIO</h1><br><br>
		<form action="/Sangucheto/logueo" method="post">
			DNI: <input type="text" name="documento" class="form-control"></input>
			CONTRASEÑA: <input type="password" name="password" class="form-control" ></input>
			<input type="submit" value="Ingresar al sistema" class="btn btn-success btn-lg"></input>
		</form>
		<pre>
		
		
		
		</pre>
		<h1 class = "text-center">ALTA DE USUARIO</h1>
		<form action = "/Sangucheto/alta" method = "post">
			DNI: <input type="text" name="documento" class="form-control"></input>
			CONTRASEÑA: <input type="password" name="password" class="form-control" ></input>
			<input type="submit" value="Ingresar datos" class="btn btn-success btn-lg"></input>
		</form>
		<pre>
		
		
		
		</pre>
		<h1 class = "text-center">ACTUALIZACION DE PASSWORD</h1>
		<form action = "modificacion" method = "post">
			DNI: <input type="text" name="documento" class="form-control"></input>
			CONTRASEÑA VIEJA: <input type="password" name="passwordVieja" class="form-control" ></input>
			CONTRASEÑA NUEVA: <input type="password" name="passwordNueva" class="form-control" ></input>
			<input type="submit" value="Actualizar datos" class="btn btn-success btn-lg"></input>
		</form>
	</body>
</html>