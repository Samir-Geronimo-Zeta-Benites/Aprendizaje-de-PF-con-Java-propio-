import java.time.LocalDate;

public class Producto {
    private int id;
    private String nombre;
    private Double precio;
    private int categoria_id;
    private int stock;
    private String descripcion;
    private LocalDate fecha_alta;

    

    public Producto(int id, String nombre, Double precio, int categoria_id, int stock, String descripcion,
            LocalDate fecha_alta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria_id = categoria_id;
        this.stock = stock;
        this.descripcion = descripcion;
        this.fecha_alta = fecha_alta;
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



    public Double getPrecio() {
        return precio;
    }



    public void setPrecio(Double precio) {
        this.precio = precio;
    }



    public int getCategoria_id() {
        return categoria_id;
    }



    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }



    public int getStock() {
        return stock;
    }



    public void setStock(int stock) {
        this.stock = stock;
    }



    public String getDescripcion() {
        return descripcion;
    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public LocalDate getFecha_alta() {
        return fecha_alta;
    }



    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

       
}
