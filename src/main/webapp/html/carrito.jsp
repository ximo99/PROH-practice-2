<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Carrito | Futboleros</title>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="./images/icon.png">
		<link rel="stylesheet" href="css/general.css">
		<link rel="stylesheet" href="css/table.css">
		<link rel="stylesheet" href="css/boton.css">
    </head>
    
	<body>
		<div class="content">
		
			<h1> Tu carrito</h1>
			
			<table id="tablaCarrito">
				<tr>
				<th>Imagen</th>
				<th>Descripción</th>
				<th>Precio</th>
				<th>Cantidad</th>
				<th>&nbsp;</th>
				</tr>
			</table>
		</div>
		<br>
		<div>
			<button class="button2" id="comprar" onclick="EnviarCarrito('RecogerCarrito', 'contenido', obtenerCarrito())">Comprar </button>
			<br>
			<button class="button2" type="submit" name="submit" onclick="Cargar('html/productos.jsp', 'contenido')"> Seguir comprando</button>
		</div>
		<br>
		<script>mostrarCarrito();</script>
	</body>
</html>