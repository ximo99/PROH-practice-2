<%@page language="java" contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    <link rel="stylesheet" href="css/general.css">
		<link rel="stylesheet" href="css/form.css">
		<link rel="stylesheet" href="css/table.css">
		<link rel="stylesheet" href="css/boton.css">
        <title>Registro</title>
    </head>

    <body>
        

        <form method="post" class="form" onsubmit="ProcesarForm(this,'Register','contenido');return false">
        	<h1>Registro de usuario</h1>
            <input type="hidden" name="url" value="./html/register.jsp" />
            <label>Usuario:</label>
            <input name="usuario" type="text" />
            <label>Contrase&ntilde;a:</label>
            <input name="clave" type="password" />
            <label>Nombre:</label>
            <input name="nombre" type="text" />
            <label>Apellidos:</label>
            <input name="apellidos" type="text" />
            <label>Email:</label>
            <input name="email" type="text" />
            <label>Domicilio:</label>
            <input name="domicilio" type="text" />
            <label>Población:</label>
            <input name="poblacion" type="text" />
            <label>Provincia:</label>
            <input name="provincia" type="text" />
            <label>CP:</label>
            <input name="cp" type="text" />
            <label>Tel&eacute;fono:</label>
            <input name="telefono" type="text" /> <br /><br />
            
        <% //Utilizamos una variable en la sesión para informar de los mensajes de error
        String mensajeRegister = (String)session.getAttribute("mensajeRegister");
        
        if (mensajeRegister != null) { //Eliminamos el mensaje consumido
            session.removeAttribute("mensajeRegister"); %>
            <h3><%=mensajeRegister%></h3>
        <% } %>
        
            <input class="button2" type="submit" name="tipoAcceso" value="Registrarse" />
        </form>
        
    </body>
</html>