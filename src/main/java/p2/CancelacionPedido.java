package p2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CancelacionPedido")
public class CancelacionPedido extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));

        AccesoBD con = AccesoBD.getInstance();
        con.abrirConexionBD();

        boolean pedidoCancelado = con.cancelarPedido(pedidoId);

        con.cerrarConexionBD();

        if (pedidoCancelado) {
            response.sendRedirect("./html/perfil.jsp");
        } else {
            response.getWriter().println("No se pudo cancelar el pedido.");
        }
    }
}