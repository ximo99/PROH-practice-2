<%@page language="java" contentType="text/html; charset=UTF-8" import="p2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Perfil de usuario</title>
  </head>
  <body>
    <%
		session = request.getSession(false);
    
      if (session == null || session.getAttribute("usuario") == null) {
          out.println("No estás autorizado para ver esta página.");
      } else {
        UsuarioBD usuario = (UsuarioBD) session.getAttribute("usuario");
    %>
    <h1>Perfil de usuario</h1>
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
    <br>
    <form action="ModificarPerfil" method="post">
      <input type="submit" value="Modificar datos" />
    </form>
    <%
      }
    %>
  </body>
</html>
