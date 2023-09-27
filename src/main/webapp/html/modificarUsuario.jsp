<%@page language="java" contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/general.css">
	<link rel="stylesheet" href="css/contacto.css">
	<link rel="stylesheet" href="css/form.css">
	<link rel="stylesheet" href="css/boton.css">
    <title>Modificar usuario</title>
</head>
<body>
    <% session=request.getSession(false); if (session==null || session.getAttribute("usuario")==null) {
        out.println("No estás autorizado para ver esta página."); } else { UsuarioBD usuario=(UsuarioBD) session.getAttribute("usuario"); %>
        <h1>Modificar usuario</h1>
        <form method="post" onsubmit="ProcesarForm(this,'ModificarPerfil','contenido');return false">
            <input type="hidden" name="codigo" value="<%= usuario.getCodigo() %>" />
            <label for="usuario">Nombre de usuario:</label>
            <input type="text" name="usuario" id="usuario" value="<%= usuario.getUsuario() %>" /><br/><br/>
            <label for="clave">Contraseña:</label>
            <input type="password" name="clave" id="clave" value="<%= usuario.getClave() %>"/><br/><br/>
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" id="nombre" value="<%= usuario.getNombre() %>" /><br/><br/>
            <label for="apellidos">Apellidos:</label>
            <input type="text" name="apellidos" id="apellidos" value="<%= usuario.getApellidos() %>" /><br/><br/>
            <label for="email">Email:</label>
            <input type="text" name="email" id="email" value="<%= usuario.getEmail() %>" /><br/><br/>
            <label for="domicilio">Domicilio:</label>
            <input type="text" name="domicilio" id="domicilio" value="<%= usuario.getDomicilio() %>" /><br/><br/>
            <label for="poblacion">Población:</label>
            <input type="text" name="poblacion" id="poblacion" value="<%= usuario.getPoblacion() %>" /><br/><br/>
            <label for="provincia">Provincia:</label>
            <input type="text" name="provincia" id="provincia" value="<%= usuario.getProvincia() %>" /><br/><br/>
            <label for="cp">Código Postal:</label>
            <input type="text" name="cp" id="cp" value="<%= usuario.getCp() %>" /><br/><br/>
            <label for="telefono">Teléfono:</label>
            <input type="text" name="telefono" id="telefono" value="<%= usuario.getTelefono() %>" /><br/><br/>
            <input class="button2" type="submit" value="Guardar cambios" />
        </form>
    <% } %>
</body>
</html>
