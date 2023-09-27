package p2;

import java.util.Date;

public class PedidoBD {

    public static final String ESTADO_PENDIENTE = "pendiente";
    public static final String ESTADO_ENVIADO = "enviado";
    public static final String ESTADO_ENTREGADO = "entregado";

    private int codigo;
    private int codigoUsuario;
    private Date fechaPedido;
    private double importe;
    private String estado;
    
    public PedidoBD(int codigo, int codigoUsuario, Date fechaPedido, double importe, String estado) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.fechaPedido = fechaPedido;
        this.importe = importe;
        this.estado = estado;
    }

    public PedidoBD(int pedidoId, java.sql.Date fecha, String estado2, double total) {
        this.codigo = pedidoId;
        this.fechaPedido = fecha;
        this.estado = estado2;
        this.importe = total;
    }


	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
