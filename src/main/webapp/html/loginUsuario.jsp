<%@page language="java" contentType="text/html charset=UTF-8" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Acceso</title>
  </head>

  <body>
    <% //Utilizamos una variable en la sesión para informar de los mensajes de error
    String mensaje = (String)session.getAttribute("mensaje");
    
    if (mensaje != null) { //Eliminamos el mensaje consumido
    	session.removeAttribute("mensaje"); %>
    	<h1><%=mensaje%></h1>
    <% }
    
    if ((session.getAttribute("usuario") == null) || ((UsuarioBD)session.getAttribute("usuario")).getCodigo() <=0 ) // Si no hay usuario o el usuario no es válido
    { //Mostramos el formulario para la introducción del usuario y la clave %>
	    <form method="post" action="./Login">
			<input type="hidden" name="url" value="./html/loginUsuario.jsp" />
			Usuario: <input name="usuario" type="text" /> <br /><br />
			Contrase&ntilde;a : <input name="clave" type="password" /> <br /><br />
			
			<button class="button2" type="submit" name="submit">Acceder</button>
		</form>
		
		<button class="button2" type="submit" name="submit" onclick="Cargar('html/registerUsuario.jsp', 'contenido')">Registrarse</button>
	    <% } else { //Si existe un usuario, se muestran las opciones del apartado del usuario
	    %>
	    <p>Mostrar&iacute;amos las diferentes opciones</p>
	    <% } %>
  </body>
</html>
