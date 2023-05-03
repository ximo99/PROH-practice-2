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
            UsuarioBD usuarioBD = con.obtenerUsuarioPorCodigo(codigo);
            if (usuarioBD != null) {
                session.setAttribute("usuario", usuarioBD);
                response.sendRedirect("./html/perfil.jsp");
            } else {
                session.setAttribute("mensaje", "Usuario y/o clave incorrectos");
                response.sendRedirect(url);
            }
        } else {
            session.setAttribute("mensaje", "Usuario y/o clave incorrectos");
            response.sendRedirect(url);
        }
    }
}




