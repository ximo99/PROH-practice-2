package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ModificarPerfil")
public class ModificarPerfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UsuarioBD usuario = (UsuarioBD) session.getAttribute("usuario");
        int codigoUsuario = usuario.getCodigo();

        String nuevoUsuario = request.getParameter("usuario");
        String nuevaClave = request.getParameter("clave");
        String nuevoNombre = request.getParameter("nombre");
        String nuevosApellidos = request.getParameter("apellidos");
        String nuevoEmail = request.getParameter("email");
        String nuevoDomicilio = request.getParameter("domicilio");
        String nuevaPoblacion = request.getParameter("poblacion");
        String nuevaProvincia = request.getParameter("provincia");
        String nuevoCP = request.getParameter("cp");
        String nuevoTelefono = request.getParameter("telefono");

        AccesoBD bd = AccesoBD.getInstance();
        boolean exito = bd.modificarUsuarioBD(codigoUsuario, nuevoUsuario, nuevaClave, nuevoNombre, nuevosApellidos, nuevoEmail, nuevoDomicilio, nuevaPoblacion, nuevaProvincia, nuevoCP, nuevoTelefono);

        if (exito) {
            session.setAttribute("usuario", bd.obtenerUsuarioBDPorCodigo(codigoUsuario));
            response.sendRedirect("./html/perfil.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
