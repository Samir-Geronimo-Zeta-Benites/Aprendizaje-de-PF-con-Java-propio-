import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
  private int id;
  private int cliente_id;
  private int producto_id;
  private int empleado_id;
  private LocalDate fecha;
  private int cantidad;
  private Double precio_total;
  private Double total_venta;

  public Venta(int id, int cliente_id, int producto_id, int empleado_id, LocalDate fecha, int cantidad,
      Double precio_total, Double total_venta) {
    this.id = id;
    this.cliente_id = cliente_id;
    this.producto_id = producto_id;
    this.empleado_id = empleado_id;
    this.fecha = fecha;
    this.cantidad = cantidad;
    this.precio_total = precio_total;
    this.total_venta = total_venta;
  }


  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getCliente_id() {
    return cliente_id;
  }
  public void setCliente_id(int cliente_id) {
    this.cliente_id = cliente_id;
  }
  public int getProducto_id() {
    return producto_id;
  }
  public void setProducto_id(int producto_id) {
    this.producto_id = producto_id;
  }
  public int getEmpleado_id() {
    return empleado_id;
  }
  public void setEmpleado_id(int empleado_id) {
    this.empleado_id = empleado_id;
  }
  public LocalDate getFecha() {
    return fecha;
  }
  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }
  public int getCantidad() {
    return cantidad;
  }
  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
  public Double getPrecio_total() {
    return precio_total;
  }
  public void setPrecio_total(Double precio_total) {
    this.precio_total = precio_total;
  }
  public Double getTotal_venta() {
    return total_venta;
  }
  public void setTotal_venta(Double total_venta) {
    this.total_venta = total_venta;
  }
  
  private List<Detalle_venta> detalles = new ArrayList<>();

  public List<Detalle_venta> getDetalles(){
    return detalles;

  }
  public void setDetalles(List<Detalle_venta> detalles){
    this.detalles = detalles;
  }

  
}
