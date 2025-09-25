import java.time.LocalDate;
public class Factura {
  
  private int id;
  private int venta_id;
  private int cliente_id;
  private LocalDate fecha_emision;
  private Double total;
  private String estado;
  
  public Factura(int id, int venta_id, int cliente_id, LocalDate fecha_emision, Double total, String estado) {
    this.id = id;
    this.venta_id = venta_id;
    this.cliente_id = cliente_id;
    this.fecha_emision = fecha_emision;
    this.total = total;
    this.estado = estado;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVenta_id() {
    return venta_id;
  }

  public void setVenta_id(int venta_id) {
    this.venta_id = venta_id;
  }

  public int getCliente_id() {
    return cliente_id;
  }

  public void setCliente_id(int cliente_id) {
    this.cliente_id = cliente_id;
  }

  public LocalDate getFecha_emision() {
    return fecha_emision;
  }

  public void setFecha_emision(LocalDate fecha_emision) {
    this.fecha_emision = fecha_emision;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
