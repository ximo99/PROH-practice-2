package p2;

public class PedidoDetalleProductoBD {
	private int codigo_pedido;
    private int codigo_producto;
    private String descripcion;
    private int unidades;
    private double precio_unitario;

    public PedidoDetalleProductoBD(int codigo_pedido, int codigo_producto, String descripcion, int unidades, double precio_unitario) {
        this.codigo_pedido = codigo_pedido;
        this.codigo_producto = codigo_producto;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.precio_unitario = precio_unitario;
    }

    
    public int getCodigoPedido() {
        return codigo_pedido;
    }

    public void setCodigoPedido(int codigo) {
        this.codigo_pedido = codigo_pedido;
    }

    public int getCodigoProducto() {
        return codigo_producto;
    }

    public void setCodigoProducto(String usuario) {
        this.codigo_producto = codigo_producto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String usuario) {
        this.descripcion = descripcion;
    }
    
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    public double getPrecioUnitario() {
        return precio_unitario;
    }

    public void setPrecioUnitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}