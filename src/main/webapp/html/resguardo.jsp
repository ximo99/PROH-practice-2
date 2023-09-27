<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List, p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Resguardo de pedido | Futboleros</title>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="./images/icon.png">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/form.css">
        <link rel="stylesheet" href="css/table.css">
        <link rel="stylesheet" href="css/boton.css">
    </head>
    
    <body>

<script>
    function mostrarCampos() {
        var numTarjetaLabel = document.getElementById('num_tarjeta_label');
        var titularLabel = document.getElementById('titular_label');
        var caducidadLabel = document.getElementById('caducidad_label');
        var numTarjetaInput = document.getElementById('num_tarjeta_input');
        var titularInput = document.getElementById('titular_input');
        var caducidadInput = document.getElementById('caducidad_input');
        
        numTarjetaLabel.style.display = 'block';
        titularLabel.style.display = 'block';
        caducidadLabel.style.display = 'block';
        numTarjetaInput.style.display = 'block';
        titularInput.style.display = 'block';
        caducidadInput.style.display = 'block';
    }
    
    function ocultarCampos() {
        var numTarjetaLabel = document.getElementById('num_tarjeta_label');
        var titularLabel = document.getElementById('titular_label');
        var caducidadLabel = document.getElementById('caducidad_label');
        var numTarjetaInput = document.getElementById('num_tarjeta_input');
        var titularInput = document.getElementById('titular_input');
        var caducidadInput = document.getElementById('caducidad_input');
        
        numTarjetaLabel.style.display = 'none';
        titularLabel.style.display = 'none';
        caducidadLabel.style.display = 'none';
        numTarjetaInput.style.display = 'none';
        titularInput.style.display = 'none';
        caducidadInput.style.display = 'none';
    }
</script>

        <div class="content">
            <h1>Resguardo de pedido</h1>
            <table id="tablaCarrito">
		        <tr>
		            <th>Descripción</th>
		            <th>Precio</th>
		            <th>Cantidad</th>
		        </tr>
		        <%
		        String mensajeError = (String) session.getAttribute("mensajeError");
		        UsuarioBD usuario = (UsuarioBD) session.getAttribute("usuario");
		
		        double total = 0.0;
		        int cantidadTotal = 0;
		        List<ProductoBD> productos = (List<ProductoBD>) session.getAttribute("productosCarrito");
		        for (ProductoBD producto : productos) {
		            total += producto.getPrecio() * producto.getStock();
		            cantidadTotal += producto.getStock();
		        %>
		        <tr>
		            <td><%= producto.getDescripcion() %></td>
		            <td><%= producto.getPrecio() %> €</td>
		            <td><%= producto.getStock() %></td>
		        </tr>
		        <% } %>
		        <tr>
				    <td><strong>Total</td>
				    <td><strong><%= total %> €</strong></td>
				    <td><strong><%= cantidadTotal %></strong></td>
				</tr>

		    </table>
		    		<% // Aquí se encuentra el mensaje de advertencia
					if (mensajeError != null) { %>
						<h3><%=mensajeError%></h3>
					<% 
					session.removeAttribute("mensajeError");
					} %>
            <br>
            <form action="login.php" method="post" class="form">
				<h2>Datos de envío y facturación</h2>
				<label for="nombre" id="nombre_label">Nombre y apellidos: <span class="required">*</span></label>
				<input type="text" name="nombre" name="nombre_input" placeholder="Nombre apellido1 apellido2" value="<%= usuario.getNombre() %> <%= usuario.getApellidos() %>" required>
				
				<label for="direccion" id="direccion_label">Dirección: <span class="required">*</span></label>
				<input type="text" name="direccion" id="direccion_input" placeholder="Calle, número" value="<%= usuario.getDomicilio() %>" required>
				
				<label for="poblacion" id="poblacion_label">Población: <span class="required">*</span></label>
				<input type="text" name="poblacion" id="poblacion_input" placeholder="Municipio" value="<%= usuario.getPoblacion() %>" required>
				
				<label for="provincia" id="provincia_label">Provincia: <span class="required">*</span></label>
				<input type="text" name="provincia" id="provincia_input" placeholder="Província" value="<%= usuario.getProvincia() %>" required>
				
				<label for="cp" id="cp_label">Código Postal: <span class="required">*</span></label>
				<input type="text" name="cp" placeholder="Código postal" id="cp_input"value="<%= usuario.getCp() %>" required>
				
				<label for="telefono" id="telefono_label">Teléfono: <span class="required">*</span></label>
				<input type="text" name="telefono" id="telefono_input" placeholder="962..." value="<%= usuario.getTelefono() %>" required>

				<h2>Datos de pago</h2>
				<label class="payment-option">
				    <input class="payment-option" type="radio" name="pago" value="tarjeta" checked onclick="mostrarCampos()"> Tarjeta de crédito<br>
				</label>
				
				<label class="payment-option">
				    <input type="radio" name="pago" value="contrarembolso" onclick="ocultarCampos()"> Contrareembolso<br>
				</label>
				
				<label for="num_tarjeta" id="num_tarjeta_label">Número de tarjeta: <span class="required">*</span></label>
				<input type="text" name="num_tarjeta" placeholder="ES XXXX XXXX XXXX XXXX" id="num_tarjeta_input">
				
				<label for="titular" id="titular_label">Titular de la tarjeta: <span class="required">*</span></label>
				<input type="text" name="titular" placeholder="Nombre Apellido1 Apellido2" id="titular_input">
				
				<label for="caducidad" id="caducidad_label">Fecha de caducidad: <span class="required">*</span></label>
				<input type="text" name="caducidad" placeholder="XX/XX/XXXX" id="caducidad_input">
				
				<button class="button2" type="button" onclick="EnviarCarrito('Tramitacion','contenido', carrito); vaciarCarrito()">Confirmar pedido</button>
				<br>
				<button class="button2" type="button" onclick="Cargar('html/carrito.jsp', 'contenido')">Cancelar</button>
				<br>
            </form>
        </div>
    </body>
</html>