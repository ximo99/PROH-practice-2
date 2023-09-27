package p2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RecogerCarrito")
public class RecogerCarrito extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        List<ProductoBD> productos = new ArrayList<>();
        
        try (InputStream inputStream = request.getInputStream();
            JsonReader reader = Json.createReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            
            JsonArray carritoJson = reader.readArray();
            
            for (int i = 0; i < carritoJson.size(); i++) {
                JsonObject productoJson = carritoJson.getJsonObject(i);
                
                int id = Integer.parseInt(productoJson.getString("codigo"));
                int cantidad = productoJson.getInt("cantidad");
                ProductoBD producto = AccesoBD.getInstance().obtenerProductoBDPorCodigo(id);
                
                if (producto == null || producto.getStock() < cantidad) {
                    response.sendRedirect("./html/carrito.jsp");
                    return;
                }
                
                producto.setStock(cantidad);
                productos.add(producto);
            }

            // Guardar la lista de productos en la sesión
            session.setAttribute("productosCarrito", productos);            
            
            if (session.getAttribute("usuario") == null) {
            	session.setAttribute("url", "./html/resguardo.jsp");
                response.sendRedirect("./html/loginUsuario.jsp");
                return;
            }
            
            // Redirigir a la página de resguardo
            response.sendRedirect("./html/resguardo.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}