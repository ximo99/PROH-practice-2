package p2;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public final class AccesoBD {
    private static AccesoBD instanciaUnica = null;
    private Connection conexionBD = null;

    public static AccesoBD getInstance(){
        if (instanciaUnica == null){
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }
    
    private AccesoBD() {
        abrirConexionBD();
    }

    public void abrirConexionBD() {
        if (conexionBD == null) {
            // daw es el nombre de la base de datos que hemos creado con anterioridad.
            String futboleros = "jdbc:mysql://localhost/futboleros";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(futboleros, "root", "");
            } catch (Exception e) {
                System.err.println("No se ha podido conectar a la base de datos");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    public void cerrarConexionBD() {
        if (conexionBD != null) {
            try {
                conexionBD.close();
                conexionBD = null;
            }
            catch(Exception e) {
                System.err.println("No se ha podido desconectar de la base de datos");
                System.err.println(e.getMessage());
            }
        }
    }
    
    public Connection getConnection() {
        return conexionBD;
    }
    
    public void autoCommit() {
    	try {
			conexionBD.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean comprobarAcceso() {
        abrirConexionBD();
        boolean res = (conexionBD != null);
        return res;
    }

    public List<ProductoBD> obtenerProductosBD() {
        abrirConexionBD();

        ArrayList<ProductoBD> productos = new ArrayList<>();

        try {
            // hay que tener en cuenta las columnas de vuestra tabla de productos
            // también se puede utilizar una consulta del tipo:
            // con = "SELECT * FROM productos";
            String con = "SELECT codigo,descripcion,precio,existencias,imagen FROM productos";

            Statement s = conexionBD.createStatement();
            ResultSet resultado = s.executeQuery(con);

            while(resultado.next()){
                ProductoBD producto = new ProductoBD();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setStock(resultado.getInt("existencias"));
                producto.setImagen(resultado.getString("imagen"));
                productos.add(producto);
            }
        }
        catch(Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        return productos;
    }
    
    public int comprobarUsuarioBD(String usuario, String clave) {
        abrirConexionBD();

        try{
            String con = "SELECT codigo FROM usuarios WHERE usuario=? AND clave=? AND activo=1";
            PreparedStatement s = conexionBD.prepareStatement(con);
            s.setString(1,usuario);
            s.setString(2,clave);
            
            ResultSet resultado = s.executeQuery();
            
            if ( resultado.next() ) { // El usuario/clave se encuentra en la BD
            	return resultado.getInt("codigo");
            }
            
        }
        catch(Exception e) { // Error en la conexión con la BD
            System.err.println("Error verificando usuario/clave");
            System.err.println(e.getMessage());
        }
        
        return -1;
    }
    
    public boolean registrarUsuarioBD(String usuario, String clave, String nombre, String apellidos, String email, String domicilio, String poblacion, String provincia, String cp, String telefono) {
        abrirConexionBD();

        try {
            String consulta = "INSERT INTO usuarios (usuario, clave, nombre, apellidos, email, domicilio, poblacion, provincia, cp, telefono, activo, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = conexionBD.prepareStatement(consulta);
            s.setString(1, usuario);
            s.setString(2, clave);
            s.setString(3, nombre);
            s.setString(4, apellidos);
            s.setString(5, email);
            s.setString(6, domicilio);
            s.setString(7, poblacion);
            s.setString(8, provincia);
            s.setString(9, cp);
            s.setString(10, telefono);
            s.setBoolean(11, true); // activo por defecto
            s.setBoolean(12, false); // admin por defecto

            int affectedRows = s.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo registrar al usuario");
            } else {
                return true; // El usuario se pudo crear en la BD
            }
        } catch (Exception e) {
            System.err.println("Error registrando al usuario en la base de datos"); // Error en la conexión con la BD
            System.err.println(e.getMessage()); 
        }

        return false;
    }

    public List<UsuarioBD> obtenerUsuariosBD() {
        abrirConexionBD();

        ArrayList<UsuarioBD> usuarios = new ArrayList<>();

        try {
            String con = "SELECT * FROM usuarios";

            Statement s = conexionBD.createStatement();
            ResultSet resultado = s.executeQuery(con);

            while(resultado.next()){
                UsuarioBD usuario = new UsuarioBD(
                    resultado.getInt("codigo"),
                    resultado.getString("usuario"),
                    resultado.getString("clave"),
                    resultado.getString("nombre"),
                    resultado.getString("apellidos"),
                    resultado.getString("email"),
                    resultado.getString("domicilio"),
                    resultado.getString("poblacion"),
                    resultado.getString("provincia"),
                    resultado.getString("cp"),
                    resultado.getString("telefono"),
                    resultado.getBoolean("activo"),
                    resultado.getBoolean("admin")
                );

                usuarios.add(usuario);
            }
        } catch(Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }

        return usuarios;
    }

    public UsuarioBD obtenerUsuarioBDPorCodigo(int codigo) {
        UsuarioBD usuario = null;
        abrirConexionBD();

        try {
            String query = "SELECT * FROM usuarios WHERE codigo=?";
            PreparedStatement s = conexionBD.prepareStatement(query);
            s.setInt(1, codigo);
            
            ResultSet resultado = s.executeQuery();
            
            if (resultado.next()) {
                int codigoUsuario = resultado.getInt("codigo");
                String usuarioNombre = resultado.getString("usuario");
                String clave = resultado.getString("clave");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                String email = resultado.getString("email");
                String domicilio = resultado.getString("domicilio");
                String poblacion = resultado.getString("poblacion");
                String provincia = resultado.getString("provincia");
                String cp = resultado.getString("cp");
                String telefono = resultado.getString("telefono");
                boolean activo = resultado.getBoolean("activo");
                boolean admin = resultado.getBoolean("admin");
                
                usuario = new UsuarioBD(codigoUsuario, usuarioNombre, clave, nombre, apellidos, email, domicilio, poblacion, provincia, cp, telefono, activo, admin);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener usuario por código");
            System.err.println(e.getMessage());
        } finally {
            cerrarConexionBD(); // Asume que tienes un método para cerrar la conexión a la base de datos
        }
        
        return usuario;
    }

    public boolean modificarUsuarioBD(int codigo, String usuario, String clave, String nombre, String apellidos, String email, String domicilio, String poblacion, String provincia, String cp, String telefono) {
        abrirConexionBD();

        boolean exito = false;

        try {
            String sql = "UPDATE usuarios SET usuario = ?, clave = ?, nombre = ?, apellidos = ?, email = ?, domicilio = ?, poblacion = ?, provincia = ?, cp = ?, telefono = ? WHERE codigo = ?";

            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setString(3, nombre);
            ps.setString(4, apellidos);
            ps.setString(5, email);
            ps.setString(6, domicilio);
            ps.setString(7, poblacion);
            ps.setString(8, provincia);
            ps.setString(9, cp);
            ps.setString(10, telefono);
            ps.setInt(11, codigo);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario en la base de datos");
            System.err.println(e.getMessage());
        }

        cerrarConexionBD();

        return exito;
    }

    public ProductoBD obtenerProductoBDPorCodigo(int codigo) {
        abrirConexionBD();

        ProductoBD producto = null;

        try {
            PreparedStatement ps = conexionBD.prepareStatement("SELECT codigo,descripcion,precio,existencias,imagen FROM productos WHERE codigo = ?");
            ps.setInt(1, codigo);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()){
                producto = new ProductoBD();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setStock(resultado.getInt("existencias"));
                producto.setImagen(resultado.getString("imagen"));
            }
        }
        catch(Exception e) {
            System.err.println("Error ejecutando la consulta a la base de datos");
            System.err.println(e.getMessage());
        }
        finally {
            cerrarConexionBD();
        }
        return producto;
    }

    public void actualizarExistenciasProducto(int id, int cantidad) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            abrirConexionBD();
            ps = conexion.prepareStatement("UPDATE productos SET existencias = existencias - ? WHERE id = ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarStatement(ps);
            cerrarConexionBD();
        }
    }
    
    public double calcularTotalPedido(int codigoPedido) throws SQLException {
        double total = 0.0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conexionBD.prepareStatement("SELECT SUM(importe) FROM pedido WHERE codigoPedido = ?");
            pstmt.setInt(1, codigoPedido);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } finally {
            // Cerrar los recursos en el orden inverso de su apertura
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }

        return total;
    }


    public List<PedidoBD> obtenerPedidosPorUsuario(int usuarioId) {
        List<PedidoBD> pedidos = new ArrayList<>();

        try {
            String query = "SELECT * FROM pedidos WHERE persona = ?";
            PreparedStatement stmt = conexionBD.prepareStatement(query);
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int pedidoId = rs.getInt("codigo");
                Date fecha = rs.getDate("fecha");
                double total = rs.getDouble("importe");
                String estado = rs.getString("estado");
                PedidoBD pedido = new PedidoBD(pedidoId, fecha, estado, total);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public boolean cancelarPedido(int pedidoId) {
        try {
            String updateQuery = "UPDATE pedidos SET estado = '4' WHERE codigo = ?";
            PreparedStatement stmt = conexionBD.prepareStatement(updateQuery);
            stmt.setInt(1, pedidoId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void cerrarResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public PreparedStatement prepareStatement(String string) {
		PreparedStatement prepStat = null;
		try {
			prepStat = conexionBD.prepareStatement(string);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return prepStat;
	}
	
	public Statement createStatement() throws SQLException {
	    if (conexionBD == null) {
	        throw new SQLException("No se ha establecido una conexión con la base de datos.");
	    }
	    return conexionBD.createStatement();
	}
	
	public void commit() {
		try {
			conexionBD.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			conexionBD.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<PedidoDetalleProductoBD> obtenerProductosPorPedido(int codigoPedido) {
	    List<PedidoDetalleProductoBD> productos = new ArrayList<>();

	    try {
	        String query = "SELECT d.codigo_pedido, d.codigo_producto, p.descripcion, d.unidades, d.precio_unitario "
	                + "FROM detalle d "
	                + "JOIN productos p ON d.codigo_producto = p.codigo "
	                + "WHERE d.codigo_pedido = ?";
	        PreparedStatement stmt = conexionBD.prepareStatement(query);
	        stmt.setInt(1, codigoPedido);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int codigoPedidoDB = rs.getInt("codigo_pedido");
	            int codigoProducto = rs.getInt("codigo_producto");
	            String descripcion = rs.getString("descripcion");
	            int unidades = rs.getInt("unidades");
	            double precioUnitario = rs.getDouble("precio_unitario");

	            PedidoDetalleProductoBD producto = new PedidoDetalleProductoBD(codigoPedidoDB, codigoProducto, descripcion, unidades, precioUnitario);
	            productos.add(producto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productos;
	}



}