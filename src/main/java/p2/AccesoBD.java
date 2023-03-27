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
}