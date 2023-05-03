<%@page language="java" contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Registro</title>
  </head>
  <body>
    <% //Utilizamos una variable en la sesión para informar de los mensajes de error
    String mensaje = (String)session.getAttribute("mensaje");
    
    if (mensaje != null) { //Eliminamos el mensaje consumido
        session.removeAttribute("mensaje"); %>
        <h1><%=mensaje%></h1>
    <% } %>
    
    <h1>Registro de usuario</h1>
    
    <form method="post" action="./Register">
        <input type="hidden" name="url" value="./html/register.jsp" />
        <label>Usuario:</label>
        <input name="usuario" type="text" /> <br /><br />
        <label>Contrase&ntilde;a:</label>
        <input name="clave" type="password" /> <br /><br />
        <label>Nombre:</label>
        <input name="nombre" type="text" /> <br /><br />
        <label>Apellidos:</label>
        <input name="apellidos" type="text" /> <br /><br />
        <label>Email:</label>
        <input name="email" type="text" /> <br /><br />
        <label>Domicilio:</label>
        <input name="domicilio" type="text" /> <br /><br />
        <label>Población:</label>
        <input name="poblacion" type="text" /> <br /><br />
        <label>Provincia:</label>
        <input name="provincia" type="text" /> <br /><br />
        <label>CP:</label>
        <input name="cp" type="text" /> <br /><br />
        <label>Tel&eacute;fono:</label>
        <input name="telefono" type="text" /> <br /><br />
        <input class="button2" type="submit" name="tipoAcceso" value="Registrarse" />
    </form>
  </body>
</html>
