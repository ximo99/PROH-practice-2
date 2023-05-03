package p2;
import java.sql.*;
import java.util.*;

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
        if (conexionBD == null)
        { // daw es el nombre de la base de datos que hemos creado con anterioridad.
            String futboleros = "jdbc:mysql://localhost/futboleros";
            try { // root y sin clave es el usuario por defecto que crea XAMPP.
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(futboleros,"root","");
            }
            catch(Exception e) {
                System.err.println("No se ha podido conectar a la base de datos");
                System.err.println(e.getMessage());
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

    public boolean comprobarAcceso() {
        abrirConexionBD();
        boolean res = (conexionBD != null);
        cerrarConexionBD();
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
            String con = "SELECT codigo FROM usuarios WHERE usuario=? AND clave=?";
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

}