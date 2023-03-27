<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Carrito | Futboleros</title>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="./images/icon.png">
		<link rel="stylesheet" href="css/general.css">
		<link rel="stylesheet" href="css/productos.css">
		<link rel="stylesheet" href="css/boton.css">
    </head>
    
	<body>


		<div id="carritoContenedor"></div>
	    <input type="button" value="Vaciar carrito" onclick="vaciarProducto()">
	    <script>mostrarCarrito(carrito);</script>
	    
	     
	</body>
</html>