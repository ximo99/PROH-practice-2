package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getParameter("url");
        
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String domicilio = request.getParameter("domicilio");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String cp = request.getParameter("cp");
        String telefono = request.getParameter("telefono");

        HttpSession session = request.getSession(true);
        AccesoBD con = AccesoBD.getInstance();

        boolean registro = con.registrarUsuarioBD(usuario, clave, nombre, apellidos, email, domicilio, poblacion, provincia, cp, telefono);

        System.out.println(registro);
        
        if (registro) {
        	int codigo = con.comprobarUsuarioBD(usuario, clave);
        	if (codigo > 0) {
        	    UsuarioBD usuarioBD = con.obtenerUsuarioBDPorCodigo(codigo);
        	    session.setAttribute("usuario", usuarioBD);
        	    response.sendRedirect("./html/perfil.jsp");
        	}


        } else {
            session.setAttribute("mensaje", "Error al registrar el usuario, por favor intente nuevamente.");
            response.sendRedirect(url);
        }
    }
}
