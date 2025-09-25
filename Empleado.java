import java.time.LocalDate;
public class Empleado {

  private int id;
  private String nombre;
  private String email;
  private String puesto;
  private LocalDate fecha_contratacion;
  private Double salario;
  
  public Empleado(int id, String nombre, String email, String puesto, LocalDate fecha_contratacion, Double salario) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.puesto = puesto;
    this.fecha_contratacion = fecha_contratacion;
    this.salario = salario;
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

  public String getPuesto() {
    return puesto;
  }

  public void setPuesto(String puesto) {
    this.puesto = puesto;
  }

  public LocalDate getFecha_contratacion() {
    return fecha_contratacion;
  }

  public void setFecha_contratacion(LocalDate fecha_contratacion) {
    this.fecha_contratacion = fecha_contratacion;
  }

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  
}
