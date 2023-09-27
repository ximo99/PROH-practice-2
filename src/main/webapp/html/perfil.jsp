<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="shortcut icon" href="./images/icon.png">
		<link rel="stylesheet" href="css/general.css">
		<link rel="stylesheet" href="css/table.css">
		<link rel="stylesheet" href="css/boton.css">
		<title>Perfil de usuario</title>
	</head>
	
	<body>
		<div class="content">
			<%
		    
			session=request.getSession(false);
			
			if (session==null || session.getAttribute("usuario")==null) {
				out.println("No estás autorizado para ver esta página.");
			} else {
				UsuarioBD usuario=(UsuarioBD) session.getAttribute("usuario");
				
				AccesoBD con = AccesoBD.getInstance();
	            con.abrirConexionBD();
	            
		        // Obtener la lista de pedidos del usuario
		        List<PedidoBD> pedidos = con.obtenerPedidosPorUsuario(usuario.getCodigo());
			%>
			<h1>Perfil de usuario</h1>
			<h2>Datos personales:</h2>
			<table>
			    <tr>
			        <td>Nombre:</td>
			        <td><%= usuario.getNombre() %></td>
				</tr>
				<tr>
				    <td>Apellidos:</td>
				    <td><%= usuario.getApellidos() %></td>
				</tr>
				<tr>
				    <td>Email:</td>
				    <td><%= usuario.getEmail() %></td>
				</tr>
				<tr>
				    <td>Domicilio:</td>
				    <td><%= usuario.getDomicilio() %></td>
				</tr>
				<tr>
				    <td>Población:</td>
				    <td><%= usuario.getPoblacion() %></td>
				</tr>
				<tr>
				    <td>Provincia:</td>
				    <td><%= usuario.getProvincia() %></td>
				</tr>
				<tr>
				    <td>CP:</td>
				    <td><%= usuario.getCp() %></td>
				</tr>
				<tr>
				    <td>Teléfono:</td>
				    <td><%= usuario.getTelefono() %></td>
				</tr>
				<tr>
				    <td>Activo:</td>
				    <td><%= usuario.isActivo() ? "Sí" : "No" %></td>
				</tr>
				<tr>
				    <td>Administrador:</td>
				    <td><%= usuario.isAdmin() ? "Sí" : "No" %></td>
				 </tr>
			</table>
			<br />
			
			<h2>Pedidos:</h2>
			<table>
			    <tr>
			        <th>Fecha</th>
			        <th>Estado</th>
			        <th>Total</th>
			        <th>Detalle del pedido</th>
			        <th>&nbsp;</th>
			    </tr>
			    <%
			    	for (PedidoBD pedido : pedidos) {
	                    List<PedidoDetalleProductoBD> productos = con.obtenerProductosPorPedido(pedido.getCodigo());
			    %>
			        <tr>
			            <td><%= pedido.getFechaPedido() %></td>
			            <td>
			                <% 
			                String estado = "";
			                if (pedido.getEstado() != null) {
			                    if (pedido.getEstado().equals("1")) {
			                        estado = "Pendiente";
			                    } else if (pedido.getEstado().equals("2")) {
			                        estado = "Enviado";
			                    } else if (pedido.getEstado().equals("3")) {
			                        estado = "Entregado";
			                    } else {
			                    	estado = "Cancelado";
			                    }
			                }
			                out.println(estado);
			                %>
			            </td>
			            <td><%= pedido.getImporte() %></td>
			            <td>
                        <ul>
						    <table>
							    <tr>
							        <th>Producto</th>
							        <th>Unidades</th>
							        <th>Precio por unidad</th>
							    </tr>
							    <% for (PedidoDetalleProductoBD producto : productos) { %>
							        <tr>
							            <td><%= producto.getDescripcion() %></td>
							            <td><%= producto.getUnidades() %></td>
							            <td><%= producto.getPrecioUnitario() %></td>
							        </tr>
							    <% } %>
							</table>
						</ul>
                    </td>
			            <td>
			                <% if (pedido.getEstado() != null && pedido.getEstado().equals("1")) { %>
			                    <form method="post" onsubmit="ProcesarForm(this,'CancelacionPedido','contenido');return false">
			                        <input type="hidden" name="pedidoId" value="<%= pedido.getCodigo() %>">
			                        <button class="button1" type="submit">Cancelar</button>
			                    </form>
			                <% } %>
			            </td>
			        </tr>
			    <% } %>
			</table>
			
			<button class="button2" type="submit" onclick="Cargar('html/modificarUsuario.jsp', 'contenido')">Modificar perfil</button>
			<br>
			
			<form method="post" onsubmit="ProcesarForm(this,'CerrarSesion','contenido');return false">
			    <button class="button2" type="submit">Cerrar sesión</button>
			</form>
			
			<% } %>
			<br><br>
		</div>
	</body>
</html>