<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title> Productos | Futboleros</title>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="./images/icon.png">
		<link rel="stylesheet" href="css/general.css">
		<link rel="stylesheet" href="css/productos.css">
		<link rel="stylesheet" href="css/boton.css">
    </head>

    <body>
        <%
            AccesoBD con = AccesoBD.getInstance();
            List<ProductoBD> productos = con.obtenerProductosBD();
        %>

        <div class="content">
            <div class="grid">
				<div class="grid-inner">
                    <%
                        for(ProductoBD producto : productos){
                            int codigo = producto.getCodigo();
                            String descripcion = producto.getDescripcion();
                            float precio = producto.getPrecio();
                            int existencias = producto.getStock();
                            String imagen = producto.getImagen();
                    %>
                    <div class="item">
						<img src="<%=imagen%>" alt="<%=descripcion%>">
						<p> <b> <%=descripcion%></b> </p>
						<p> Precio: <%=precio%> &euro;</p>
						<br>
    
                        <%
                            if (existencias > 0) {
                        %>

                        <input type="button" class="button1" value="Añadir al carrito" onclick="agregarProducto('<%=codigo%>', '<%=descripcion%>', '<%=existencias%>', '<%=precio%>')">
                        <br>
                        <input type="button" class="button1" value="Eliminar del carrito" onclick="eliminarProducto('<%=codigo%>')">
                        <%
                    	}
                    	else {
                        %>
                        <p>No disponible </p>
                        <%
	                    }
	                    %>
                	</div>
                        <%
                        }
                        %>
                </div>
            </div>
        </div>
    </body>
</html>