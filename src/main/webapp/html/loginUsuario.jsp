<%@page language="java" contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="./images/icon.png">
	<link rel="stylesheet" href="css/general.css">
	<link rel="stylesheet" href="css/form.css">
	<link rel="stylesheet" href="css/table.css">
	<link rel="stylesheet" href="css/boton.css">
    <title>Acceso</title>
  </head>

  <body>
    <% //Utilizamos una variable en la sesión para informar de los mensajes de error
    String mensajeLogin = (String) session.getAttribute("mensajeLogin");
    
    if ((session.getAttribute("usuario") == null) || ((UsuarioBD)session.getAttribute("usuario")).getCodigo() <=0 ) // Si no hay usuario o el usuario no es válido
    { //Mostramos el formulario para la introducción del usuario y la clave %>
	    <form method="post" class="form" onsubmit="ProcesarForm(this,'Login','contenido');return false">
	    	<h1>Acceso</h1>
			<input type="hidden" name="url" value="./html/loginUsuario.jsp" />
			Usuario: <input name="usuario" type="text" /> <br /><br />
			Contrase&ntilde;a : <input name="clave" type="password" /> <br /><br />
			
			<% // Aquí se encuentra el mensaje de advertencia
			if (mensajeLogin != null) { %>
				<h3><%=mensajeLogin%></h3>
			<%
			session.removeAttribute("mensajeLogin");
			} %>
			
			<button class="button2" type="submit">Acceder</button>
		</form>
		
		<button class="button2" type="submit" onclick="Cargar('html/registerUsuario.jsp', 'contenido')">Registrarse</button>
	    <% } else { //Si existe un usuario, redirigir a perfil.jsp
        response.sendRedirect("perfil.jsp");
    } %>
  </body>
</html>