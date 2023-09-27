package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        HttpSession session = request.getSession(true);
        
        AccesoBD con = AccesoBD.getInstance();
        int codigo = con.comprobarUsuarioBD(usuario, clave);
        
        if (codigo > 0) {
            UsuarioBD usuarioBD = con.obtenerUsuarioBDPorCodigo(codigo);
            if (usuarioBD != null) {
                session.setAttribute("usuario", usuarioBD);
                
                String urls = (String) session.getAttribute("url");
                session.setAttribute("url", null);
                
                if (urls == null) {
                	response.sendRedirect("./html/perfil.jsp");
                } else {
                	response.sendRedirect(urls);
                }
            } else {
                session.setAttribute("mensajeLogin", "Usuario y/o clave incorrectos");
                response.sendRedirect(url);
            }
        } else {
            session.setAttribute("mensajeLogin", "Usuario y/o clave incorrectos");
            response.sendRedirect(url);
        }
    }
}