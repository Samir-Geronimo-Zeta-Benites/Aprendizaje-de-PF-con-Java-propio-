public class Detalle_venta{

  private int id;
  private int venta_id;
  private int producto_id;
  private int cantidad;
  private Double precio_total;

  public Detalle_venta(int id, int venta_id, int producto_id, int cantidad, Double precio_total) {
    this.id = id;
    this.venta_id = venta_id;
    this.producto_id = producto_id;
    this.cantidad = cantidad;
    this.precio_total = precio_total;
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

  public int getProducto_id() {
    return producto_id;
  }

  public void setProducto_id(int producto_id) {
    this.producto_id = producto_id;
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

  


}