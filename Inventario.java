import java.time.LocalDate;

public class Inventario {
  
  private int id;
  private int producto_id;
  private int proveedor_id;
  private int cantidad;
  private LocalDate fecha_entrada;
  
  public Inventario(int id, int producto_id, int proveedor_id, int cantidad, LocalDate fecha_entrada) {
    this.id = id;
    this.producto_id = producto_id;
    this.proveedor_id = proveedor_id;
    this.cantidad = cantidad;
    this.fecha_entrada = fecha_entrada;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProducto_id() {
    return producto_id;
  }

  public void setProducto_id(int producto_id) {
    this.producto_id = producto_id;
  }

  public int getProveedor_id() {
    return proveedor_id;
  }

  public void setProveedor_id(int proveedor_id) {
    this.proveedor_id = proveedor_id;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public LocalDate getFecha_entrada() {
    return fecha_entrada;
  }

  public void setFecha_entrada(LocalDate fecha_entrada) {
    this.fecha_entrada = fecha_entrada;
  }
  
  
}
