package p2;

public class UsuarioBD {
    private int codigo;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellidos;
    private String email;
    private String domicilio;
    private String poblacion;
    private String provincia;
    private String cp;
    private String telefono;
    private boolean activo;
    private boolean admin;
    
    public UsuarioBD(int codigo, String usuario, String clave, String nombre, String apellidos, String email, String domicilio, String poblacion, String provincia, String cp, String telefono, boolean activo,  boolean admin) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.domicilio = domicilio;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.cp = cp;
        this.telefono = telefono;
        this.activo = activo;
        this.admin = admin;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}