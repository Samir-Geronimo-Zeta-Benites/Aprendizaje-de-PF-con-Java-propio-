import java.time.LocalDate;
public class Cliente {
  
  private int id;
  private String nombre;
  private String email;
  private String telefono;
  private String direccion;
  private LocalDate fecha_registro;
  private String tipo_cliente;
 
  public Cliente(int id, String nombre, String email, String telefono, String direccion, LocalDate fecha_registro,
      String tipo_cliente) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.direccion = direccion;
    this.fecha_registro = fecha_registro;
    this.tipo_cliente = tipo_cliente;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public LocalDate getFecha_registro() {
    return fecha_registro;
  }

  public void setFecha_registro(LocalDate fecha_registro) {
    this.fecha_registro = fecha_registro;
  }

  public String getTipo_cliente() {
    return tipo_cliente;
  }

  public void setTipo_cliente(String tipo_cliente) {
    this.tipo_cliente = tipo_cliente;
  }
  
  
  


  
}
