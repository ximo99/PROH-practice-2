package p2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Tramitacion")
public class Tramitacion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Obtener el usuario que ha iniciado sesión
        UsuarioBD usuario = (UsuarioBD) session.getAttribute("usuario");

        // Obtener los productos que el usuario ha añadido al carrito
        ArrayList<ProductoBD> productos = (ArrayList<ProductoBD>) session.getAttribute("productosCarrito");

        // Guardar los datos del pedido en la base de datos
        try {
            AccesoBD con = AccesoBD.getInstance();
            con.abrirConexionBD();
            con.autoCommit();
            
            // Comprobacion doble
            for(ProductoBD producto : productos) {
            	PreparedStatement pstmt2 = con.prepareStatement(
            		"SELECT codigo, existencias from productos where codigo = ? AND existencias >= ?"
            	);
            	
            	pstmt2.setInt(1, producto.getCodigo());
            	pstmt2.setInt(2, producto.getStock());
            	ResultSet rs = pstmt2.executeQuery();
            	
            	if (!rs.next()) {
            		session.setAttribute("mensajeError", "Error al procesar el pedido. Vuelve a intentarlo.");
            		response.sendRedirect("html/resguardo.jsp");
            		return;
            	}
            }

            // Inserta los datos del pedido en la tabla de pedidos
            PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO pedidos (persona, fecha, importe, estado) VALUES (?, ?, ?, ?)"
            );
            
            // Verifica si la variable "pstmt" es nula y, si lo es, inicialízala con una instancia de PreparedStatement
            if (pstmt == null) {
            	pstmt = con.prepareStatement("INSERT INTO pedidos (persona, fecha, importe, estado) VALUES (?, ?, ?, ?)");
            }
            
            pstmt.setInt(1, usuario.getCodigo()); // identificador del usuario que realiza el pedido
            pstmt.setDate(2, new java.sql.Date(new Date().getTime())); // fecha actual
            pstmt.setDouble(3, 0.0); // total de la compra (se actualizará más adelante)
            pstmt.setInt(4, 1); // estado del pedido (por defecto, pendiente = 0)
            pstmt.executeUpdate();

            // Obtener el código del último pedido insertado
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            
            int codigoPedido;
            
            if (rs.next()) {
                codigoPedido = rs.getInt(1);
            } else {
                throw new SQLException("Error al obtener el código del pedido.");
            }

            // Insertar los productos del carrito en la tabla de detalle
            pstmt = con.prepareStatement(
                "INSERT INTO detalle (codigo_pedido, codigo_producto, unidades, precio_unitario) VALUES (?, ?, ?, ?)"
            );
            
            // Verifica si la variable "productos" es nula y, si lo es, inicialízala como una nueva ArrayList
            if (productos == null) {
                productos = new ArrayList<>();
            }
            
            double importeTotal = 0.0;

            for (ProductoBD producto : productos) {
                pstmt.setInt(1, codigoPedido);
                pstmt.setInt(2, producto.getCodigo());
                pstmt.setInt(3, producto.getStock()); // unidades del producto
                pstmt.setDouble(4, producto.getPrecio()); // precio unitario del producto
                pstmt.executeUpdate();

                // Actualiza el importe total sumando el precio del producto
                importeTotal += producto.getPrecio();
                
                // Actualiza el campo existencias en la tabla 'productos'
                PreparedStatement updateStmt = con.prepareStatement(
                		"UPDATE productos SET existencias = existencias-? WHERE codigo = ?"
                		);
                
                updateStmt.setInt(1, producto.getStock());
                updateStmt.setInt(2, producto.getCodigo());
                updateStmt.executeUpdate();
            }
            
            // Actualiza el campo importe en la tabla 'pedidos'
            PreparedStatement updateStmt = con.prepareStatement(
            		"UPDATE pedidos SET importe = ? WHERE codigo = ?"
            		);
            
            updateStmt.setDouble(1, importeTotal);
            updateStmt.setInt(2, codigoPedido);
            updateStmt.executeUpdate();

            // Confirmar la transacción y cerrar la conexión
            con.commit();
            //con.close();
            
            // Limpiar el carrito de la sesión
            session.removeAttribute("productosCarrito");
            
            // Redirigir al usuario a la página de confirmación
            response.sendRedirect("html/confirmar.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción y realizar las operaciones correspondientes
        }
    }
}

