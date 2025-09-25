import java.util.*;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Main {

  public static void main(String[] args) {

    String url = "jdbc:sqlite:C:\\Users\\The Jark\\pf-java\\tienda.db";

    List<Producto> productos = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, nombre,precio,categoria_id,stock,descripcion,fecha_alta FROM productos")){

        while (rs.next()) {
            productos.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("categoria_id"),
                    rs.getInt("stock"),
                    rs.getString("descripcion"),
                    LocalDate.parse(rs.getString("fecha_alta"))
            ));
            
        }

    } catch (SQLException e) {
        
        System.out.println("Error al conectar o leer datos de productos: " +e.getMessage());
    }

    List<Venta> ventas = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, cliente_id,producto_id,empleado_id,fecha,cantidad, precio_total, total_venta FROM ventas")){
        while (rs.next()) {
            ventas.add(new Venta(
                rs.getInt("id"),
                rs.getInt("cliente_id"),
                rs.getInt("producto_id"),
                rs.getInt("empleado_id"),
                LocalDate.parse(rs.getString("fecha")),
                rs.getInt("cantidad"),
                rs.getDouble("precio_total"),
                rs.getDouble("total_venta")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de ventas : " +e.getMessage());
    }

    List<Detalle_venta> Detalle_ventas = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id,venta_id,producto_id,cantidad,precio_total FROM detalle_ventas")){
        while (rs.next()) {
            Detalle_ventas.add(new Detalle_venta(
                rs.getInt("id"),
                rs.getInt("venta_id"),
                rs.getInt("producto_id"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_total")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de detalle de Ventas : " +e.getMessage());
    }
    
    for (Venta v : ventas){
        List<Detalle_venta> detallesDeVenta = Detalle_ventas.stream()
        .filter(dv ->dv.getVenta_id() == v.getId())
        .collect(Collectors.toList());
        v.setDetalles(detallesDeVenta);
    }
    
    List<Cliente> clientes = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, nombre, email, telefono, direccion, fecha_registro, tipo_cliente FROM clientes")){
        while (rs.next()) {
            clientes.add(new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("direccion"),
                LocalDate.parse(rs.getString("fecha_registro")),
                rs.getString("tipo_cliente")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de clientes : " +e.getMessage());
    }

    List<Empleado> Empleados = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, nombre, email, puesto, fecha_contratacion, salario FROM empleados")){
        while (rs.next()) {
            Empleados.add(new Empleado(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("puesto"),
                LocalDate.parse(rs.getString("fecha_contratacion")),
                rs.getDouble("salario")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de empleados : " +e.getMessage());
    }
    
    List<Categoria_producto> Categoria_productos = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, nombre,descripcion FROM categorias_productos")){
        while (rs.next()) {
            Categoria_productos.add(new Categoria_producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de categoria productos : " +e.getMessage());
    }

    List<Factura> Facturas = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, venta_id, cliente_id, fecha_emision, total, estado FROM facturas")){
        while (rs.next()) {
            Facturas.add(new Factura(
                rs.getInt("id"),
                rs.getInt("venta_id"),
                rs.getInt("cliente_id"),
                LocalDate.parse(rs.getString("fecha_emision")),
                rs.getDouble("total"),
                rs.getString("estado")               
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de factura : " +e.getMessage());
    }

    List<Inventario> Inventario = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, producto_id, proveedor_id, cantidad, fecha_entrada FROm inventario")){
        while (rs.next()) {
            Inventario.add(new Inventario(
                rs.getInt("id"),             
                rs.getInt("producto_id"),             
                rs.getInt("proveedor_id"),             
                rs.getInt("cantidad"),             
                LocalDate.parse(rs.getString("fecha_entrada"))
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de inventario : " +e.getMessage());
    }

    List<Proveedores> Proveedores = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id, nombre, contacto, email, direccion FROM proveedores")){
        while (rs.next()) {
            Proveedores.add(new Proveedores(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("contacto"),
                rs.getString("email"),
                rs.getString("direccion")
            ));
        }
    }catch (SQLException e){
        System.out.println("Error en conectar o leer los datos de Proveedores: " +e.getMessage());
    }
   
    



    
    //Consumer
    //Consumer<String> printer = s -> System.out.println("Hola " + s);
    //printer.accept("Mundo");
    
    //Supplier
    //Supplier<Double> randomNumber = () -> Math.random();
    //System.out.println(randomNumber.get());

    //Function
    //Function<String, Integer> contarLetras = s -> s.length();
    //System.out.println(contarLetras.apply("Hola"));

    //Predicate
    //Predicate<Integer> esPar = num -> num % 2 == 0;
    //System.out.println(esPar.test(4));
 
    //Tarea
    //a mayusculas
    //Function<String, String> hacerMayusculas = let -> let.toUpperCase();
    //System.out.println(hacerMayusculas.apply("hola mundo"));
    //a minisculas
    //Function<String, String> hacerMinusculas = m -> m.toLowerCase();
    //System.out.println(hacerMinusculas.apply("HOLA MINUSCULA"));
    //Que reciba un String y devuelva su longitud
    //Function<String, Integer> devLength = l -> l.length();
    //System.out.println(devLength.apply("devolver longitud"));

    //Practica corta - Mini Ejercicio
    //Crear una lista de enteros y usar lambdas para :
    //Filtrar los números pares
    //Multiplicarlos por 2
    //Imprimir un resultado

    //List<Integer> numeros = List.of(1,2,3,4,5,6);
    //numeros.stream()
    //.filter(num -> num % 2 == 0)
    //.map(num -> num * 2)
    //.forEach(System.out::println);

    //similar convertir nombres a mayusculas

    //List<String> nombres = List.of("alberto", "joaquin", "sol", "ea");
    //nombres.stream()
    //.filter(n -> n.length() > 3)
    //.map(n -> n.toUpperCase())
    //.forEach(System.out::println);

    //crear un lista con los nombres de ana, juan, pedro, ana, lucia, maria
    // filtra solo los nombres que tengan 4 letras o más (filter)
    // quita los duplicados (distinct)
    // convierte todos los nombres a mayusculas 
    // ordenalos alfabeticamente (sorted)
    // imprimelos

    //List<String> ejem = List.of("ana", "juan", "pedro", "ana", "lucia", "maria");
    //ejem.stream()
    //.filter(e -> e.length() > 4)
    //.distinct()
    //.map(String::toUpperCase)
    //.sorted()
    //.forEach(System.out::println);

    //Haz un nuevo ejercicio usando collect()
    //haz una lista de numeros con 3,6,9,12,15,18,21,24
    //filtra solo los números mayores de 10
    //divide cada número entre 3 (usa map)
    //elimina duplicados si hubiera
    //ordenalos de forma descendente 
    //guarda el resultado en una lista nueva llamada resultadoNumeros
    //imprime la lista final

    //List<Integer> inicioNumeros = List.of(3 , 6,9 ,12 ,15 ,18 ,21 , 24);
    //List<Integer> resultadoNumeros = inicioNumeros.stream()
    //.filter(n -> n > 10)
    //.map(n -> n / 3)
    //.distinct()
    //.sorted(Comparator.reverseOrder())
    //.collect(Collectors.toList());
    //System.out.println(resultadoNumeros);

    //crear una lista de palabras: sol, luna, sol, estrella, luna, luna
    //agruparla en un Map donde la clave sea la palabra y el valor cuántas veces aparece
  
    //List<String> palabras = List.of("sol", "luna", "sol", "estrella" , "luna", "luna");
    //Map<String,Long> resultPalabras = palabras.stream()
    //.collect(Collectors.groupingBy(palabra -> palabra, Collectors.counting()));
    //System.out.println(resultPalabras);

    //NIVEL INTERMEDIO
    //Haz el mismo groupingBY, pero filtra primero las palabras que tengan 4 letras o más
    //y al final imprime el Map ordenado alfabéticamente por la palabra

    //List<String> inter = List.of("sol", "luna", "sol", "estrella" , "luna", "luna");
    //Map<String, Long> resultInter = inter.stream()
    //.filter(i -> i.length() >= 4 )
    //.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    //resultInter.entrySet().stream()
    //.sorted(Map.Entry.comparingByKey())
    //.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));

    //Nuevo ejercicio para aplicar lo aprendido
    //usar la lista de palabras manzana, pera, mango, pera, uva, manzana, mango, pera
    //filtra solo palabras con 5 letras o más
    //agrupa y cuenta cuántas veces aparece cada fruta
    //ordena el resultado por la cantidad(valor) de mayor a menor
    //Imprime algo como manzana = 2
    //extra convierte los nombres de las frutas a mayusculas antes de agrupar


    //List<String> entradafruta = List.of("manzana","pera","mango","pera","uva","manzana","mango","pera");
    //var resultFruta = entradafruta.stream()
    //.filter(fruta -> fruta.length() >= 5)
    //.map(String::toUpperCase)
    //.collect(Collectors.groupingBy(fruta -> fruta, Collectors.counting()));
    //resultFruta.entrySet().stream()
    //.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    //.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));

    //Reto siguiente (más avanzado con Streams):
    //usar la lista perro, gato, pato, gato, gato, pato, caballo
    //filtra palabras de 4 letras o más
    //convierte a mayusculas
    //agrupa y cuenta
    //ordena primero por cantidad de mayor a menor, y si hay empate, por la palabra en orden alfabetico
    //imprime el resultado en formato gato = 3

    //List<String> entradaAnimal = List.of("perro","gato","pato","gato","gato","pato","caballo");
    //var resultAnimal = entradaAnimal.stream()
    //.filter(animal -> animal.length() >= 4)
    //.map(String::toUpperCase)
    //.collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
    //resultAnimal.entrySet().stream()
    //.sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
    //    .thenComparing(Map.Entry.comparingByKey()))
    //.forEach(entry -> System.out.println(entry.getKey()+ " = " + entry.getValue())
    //);

    //con esto nos graduamos
    //usar esta lista "casa","arbol","perro","perro","arbol","sol","sol","sol","arbol"
    //filtra solo palabras de 4 letras o más
    //convierte a mayusculas
    //agrupa y cuenta
    //ordena por cantidad descendente y, en caso de empate, por la palabra alfabética inversa(Z-> A)
    //Imprime el resultado en el formato

    //List<String> entraobj = List.of("casa","arbol","perro","perro","arbol","sol","sol","sol","arbol");
    //var resultobj = entraobj.stream()
    //.filter(obj -> obj.length()>=4)
    //.map(String::toUpperCase)
    //.collect(Collectors.groupingBy(obj -> obj, Collectors.counting()));
    //resultobj.entrySet().stream()
    //.sorted(Map.Entry.<String,Long>comparingByValue(Comparator.reverseOrder())
    //    .thenComparing(Map.Entry.comparingByKey(Comparator.reverseOrder())))
    //.forEach(entry -> System.out.println(entry.getKey()+ " = " + entry.getValue())
    //);

    //ejercicio repaso final (streams)
    //haz una lista "lapiz","borrador","lapicero","lapiz","cuaderno","borrador","lapiz","cuaderno"
    //Filtra solo las palabras con 6 letras o más
    //Convierte todas a mayusculas
    //agrupa y cuenta cuánstas veces aparece cada palabra
    //Ordena por la cantidad(de mayor a menor) y, en caso de empate, por la palabra alfabética
    //Imprime en consola con el formato cuaderno = 2
    
    //List<String> repFinalInicio = List.of("lapiz","borrador","lapicero","lapiz","cuaderno","borrador","lapiz","cuaderno");
    //var repFinalFin = repFinalInicio.stream()
    //.filter(rep -> rep.length()>= 6)
    //.map(String::toUpperCase)
    //.collect(Collectors.groupingBy(rep -> rep, Collectors.counting()));
    //repFinalFin.entrySet().stream()
    //.sorted(Map.Entry.<String,Long>comparingByValue(Comparator.reverseOrder())
    //    .thenComparing(Map.Entry.comparingByKey(Comparator.naturalOrder()))
    //      )
    //.forEach(Entry -> System.out.println(Entry.getKey()+" = "+Entry.getValue()));
    
    //REDUCE
    //suma
    //List<Integer> numeros = List.of(1,2,3,4,5);
    //int suma = numeros.stream()
    //.filter(n -> n >=3)
    //.reduce(0, (acum, n) -> acum +n);
    //System.out.println(suma);
    
    //multiplicar
    //List<Integer> numeros = List.of(1,2,3,4,5);
    //int producto = numeros.stream()
    //  .reduce(1,(acum, n)-> acum * n);
    //System.out.println(producto);
    
    //concatenar
    //List<String> palabras = List.of("Hola","Mundo","Java");
    //String resultado = palabras.stream()
    //  .reduce("", (acum, p) -> acum + " " + p);
    //System.out.println(resultado.trim());

    //Haz una lista 2,5,8,1,4,7
    //Filtra solos los números pares
    //Usa reduce() para multiplicarlos todos(empieza con 1).
    //Imprime el resultado

    //List<Integer> esPar = List.of(2,5,8,1,4,7);
    //int par = esPar.stream()
    //  .filter(n -> n % 2 == 0)
    //  .reduce(1,(acum, n) -> acum * n);
    //System.out.println(par);

    //ejercicio de reduce con opcional
    //Haz una lista con 9,12,15,4,7
    //Haz con stream y reduce () sin valor inicial
    //Encuntra el número más pequeño
    //Imprimelo en consola usando .gert() de optional

    //List<Integer> op = List.of(9,12,15,4,7);
    //Optional<Integer> numeros = op.stream()
    //  .reduce((a,n) -> a < n ? a : n);
    //System.out.println(numeros);    
    //System.out.println(numeros.get());

    //reto rapido con optional y reduce sin valor inicial
    //usa reduce() sin valor inicial
    //"zorro","gato","perro","caballo"
    //encuntra la palabra alfabéticamente mayor(la que esté más al final en el diccionario)
    //imprime usando .get()

    //List<String> retoRapido = List.of("zorro","gato","perro","caballo");
    //Optional<String> r = retoRapido.stream().reduce((a, b) -> a.compareTo(b) < 0 ? a : b);
    //System.out.println(r.get());
    //r.ifPresent(System.out::println);

    //List<Integer> o = List.of(9,12,15,4,7);
    //Optional<Integer> q = o.stream().reduce((c,s) -> c < s ? c : s);
    //System.out.println(q.get());
    //q.ifPresent(System.out::println);


    //FLATMAP()
    //Lista 1. "sol","luna"
    //Lista 2. "estrella", "cometa"
    //Lista 3. "planeta"
    //Usa flatMap() para recorrer todas las palabras individualmente
    //Convierte todas a mayúsculas
    //Filtra ssolo las que tengan más 4 letras
    //Imprime cada una
    
    //List<List<String>> nombres = List.of(
    //  List.of("sol","luna"),
    //  List.of("estrella", "cometa"),
    //  List.of("planeta")
    //);
    //nombres.stream()
    //.flatMap(n -> n.stream())
    //.map(String::toUpperCase)
    //.filter(n -> n.length() > 4)
    //.forEach(System.out::println);

    //flatMap() + collect()
    //aplana la lista con flatMap()
    //Convierte todo a mayúsculas
    //Filtra las palabras que tengan exactamente 4 letras
    //Elimina los duplicados
    //Ordena alfabéticamente
    //Guarda todo en una nueva List<String> llamada resultado usando .collect()
    //Imprime la lista resultante

    //List<List<String>> datos = List.of(
    //  List.of("gato","perro","pez"),
    //  List.of("león","tigre","gato"),
    //  List.of("loro","águila")
    //);
    //List<String> resultado = datos.stream()
    //.flatMap(n -> n.stream())
    //.map(String::toUpperCase)
    //.filter(n -> n.length() == 4)
    //.distinct()
    //.sorted(Comparator.naturalOrder())
    //.collect(Collectors.toList());
    //System.out.println(resultado);

    //ejemplo Realista: Backend-like con flatMap()
    //Obtener una única lista de todos los correos de todos los usuarios
    //filtra solo los correos que terminan en @gmail.com
    //Elimina duplicados
    //Ordenalos alfabéticamente
    //Guardalos en una nueva List<String> llamada corresGmail.

    //List<List<String>> usuariosCorreos = List.of(
    //List.of("juan@gmail.com","ana@hotmail.com"),
    //List.of("luis@gmail.com","maria@gmail.com"),
    //List.of("carlos@yahoo.com","juan@gmail.com")
    //);
    //List<String> correosGmail = usuariosCorreos.stream()
    //.flatMap(n -> n.stream())
    //.filter(n -> n.endsWith("@gmail.com"))
    //.distinct()
    //.sorted(Comparator.naturalOrder())
    //.collect(Collectors.toList());
    //System.out.println(correosGmail);

    //Manejo avanzado de colecciones+Map+groupingBy+lógica real
    //Agrupa las frutas por nombre
    //Cuenta cuántas veces aparece cada una
    //Guarda el resultado en un Map<String,Long>
    //List<String> frutas = List.of("manzana", "pera", "mango", "pera", "uva", "manzana", "mango", "pera");
    //Map<String,Long> frutasF = frutas.stream()
    //.sorted(Comparator.naturalOrder())
    //.collect(Collectors.groupingBy(n -> n,Collectors.counting()));
    //frutasF.forEach((n,m) -> System.out.println(n + " = " + m));
    

    //Agrupa los pedidos por cliente
    //Contar cuántos pedidos tiene cada cliente
    //Ordenar de mayor a menor cantidad
    //Si hay empate, ordenar alfabéticamente por nombre de cliente
    //Imprimir un formato: cliente1 =3
    //record Pedido(String cliente, String estado) {}
//
    //List<Pedido> pedidos = List.of(
    //new Pedido("cliente1", "ENTREGADO"),
    //new Pedido("cliente2", "PENDIENTE"),
    //new Pedido("cliente1", "PENDIENTE"),
    //new Pedido("cliente3", "ENTREGADO"),
    //new Pedido("cliente1", "ENTREGADO")
//);

    //Map<String,Long> pedidosPorCliente = pedidos.stream()
    //.collect(Collectors.groupingBy(
    //    Pedido::cliente,
    //    Collectors.counting()
    //));
    //pedidosPorCliente.entrySet().stream()
    //.sorted(
    //  Map.Entry.<String,Long>comparingByValue(Comparator.reverseOrder())
    //  .thenComparing(Map.Entry.comparingByKey())
    //)
    //.forEach(Entry -> System.out.println(Entry.getKey()+ " = "+Entry.getValue()));
    
    //Map<String,Long> pedidosPorCliente = pedidos.stream()
    //.collect(Collectors.groupingBy(
    //  Pedido::estado,
    //  Collectors.counting()
    //));
    //pedidosPorCliente.entrySet().stream()
    //.sorted(
    //  Map.Entry.<String,Long>comparingByKey(Comparator.naturalOrder())
    //  .thenComparing(Map.Entry.comparingByValue(Comparator.naturalOrder()))
    //)
    //.forEach(Entry -> System.out.println(Entry.getKey()+" = "+Entry.getValue()));

    //Agrupa los pedidos por cliente
    //para cada cliente, obtén una lista de los estados únicos de sus pedidos
    //Imprime un formato: cliente1 = [ENTREGADO, PENDIENTE]

    //Map<String, Set<String>> estadosPorCliente = pedidos.stream()
    //  .collect(Collectors.groupingBy(
    //    Pedido::cliente,
    //    Collectors.mapping(
    //      Pedido::estado,
    //      Collectors.toSet()
    //    )
    //  ));
    //estadosPorCliente.forEach((c,e) -> System.out.println(c + " = " +e));
    

    //RETO FINAL AVANZADO (mezclando todo lo aprendido)
    //Agrupa los productos por nombre
    //Para cada nombre, obtener una lista con los nombres de las tiendas que los venden (sin repetir)
    //Mostrar el resultado ordenado por nombre de producto alfabéticamente
    //record Producto(String tienda,String nombre, double precio) {}
//
    //List<Producto> productos = List.of(
    //new Producto("TiendaA", "Lapicero", 1.5),
    //new Producto("TiendaB", "Cuaderno", 3.0),
    //new Producto("TiendaA", "Lapicero", 1.5),
    //new Producto("TiendaA", "Borrador", 0.5),
    //new Producto("TiendaC", "Cuaderno", 2.8),
    //new Producto("TiendaB", "Borrador", 0.6),
    //new Producto("TiendaB", "Lapicero", 1.4)
    //);

    //Map<String,Set<String>> nombresPorTiendas = productos.stream()
    //  .collect(Collectors.groupingBy(
    //    Producto::nombre,
    //    Collectors.mapping(
    //      Producto::tienda,
    //      Collectors.toSet()
    //    )
    //  ));
    //  nombresPorTiendas.entrySet().stream()
    //  .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
    //  .forEach(Entry -> System.out.println(Entry.getKey()+" = "+Entry.getValue()));

      
    //Agrutar los empleados por área
    //Contar cuántos empleados hay en cada área
    //Ordenar por cantidad de empleados de mayor a menos
    //En caso de empate, ordenar alfabéticamente por área
    //Imprimir el resultado asi ti = 3
    //record Empleado(String nombre, String area, double salario) {}
    //List<Empleado> empleados = List.of(
    //  new Empleado("Ana", "Ventas", 2500),
    //  new Empleado("Luis", "TI", 3000),
    //  new Empleado("Sofía", "Ventas", 2600),
    //  new Empleado("Carlos", "TI", 2800),
    //  new Empleado("Lucía", "Marketing", 3100),
    //  new Empleado("Pedro", "TI", 3200),
    //  new Empleado("María", "Ventas", 2550),
    //  new Empleado("Eduardo","Psicología",3200),
    //  new Empleado("Gustavo","Marketing",3200)
    //);
    //
    ////Agrupar los empleados por área contar cúantos empleados hay en cada área, ordenar por cantidad de empleados de mayor a menor, en caso de empate ordenar alfabéticamente por área e imprimir el resultado
    //empleados.stream()
    //.collect(Collectors.groupingBy(
    //  Empleado::area,
    //  Collectors.counting()))
    //
    //.entrySet().stream()
    //.sorted(Map.Entry.<String,Long>comparingByValue(Comparator.reverseOrder())
    //  .thenComparing(Map.Entry.comparingByKey(Comparator.naturalOrder())))
    //.forEach(Entry -> System.out.println(Entry.getKey()+" = "+Entry.getValue()));

    //OBTENER UN MAPA DONDE CADA ÁREA CONTENGA UNA LIdTA DE LOS NOMBRES DE LOS EMPLEADOS, ORDENADOS ALFABETIMANTE
    //empleados.stream()
    //.collect(Collectors.groupingBy(
    //  Empleado::area,
    //  Collectors.mapping(Empleado::nombre,Collectors.collectingAndThen(Collectors.toList(), nom -> {
    //    nom.sort(String::compareTo);
    //    return nom;
    //  })
    //  )
    //))
    //.forEach((area,nombre) -> System.out.println(area + " = "+nombre));
    
    //MOSTRAR LOS 3 DEPARTAMENTOS CON MÁS EMPLEADOS
    //empleados.stream()
    //.collect(Collectors.groupingBy(
    //  Empleado::area,
    //  Collectors.counting()
    //))
    //.entrySet().stream()
    //.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    //.limit(3)
    //.forEach(Entry -> System.out.println(Entry.getKey() + " = " + Entry.getValue()));

    //OBTENER EL ÁREA CON EL NOMBRE DE EMPELADO MÁS LARGO
    //empleados.stream()
    //.collect(Collectors.groupingBy(
    //  Empleado::area,
    //  Collectors.mapping(Empleado::nombre, Collectors.collectingAndThen(
    //    Collectors.maxBy(Comparator.comparing(String::length)),
    //    Optional::get
    //  ))
    //))
    //.entrySet().stream()
    //.sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
    //.forEach(Entry -> System.out.println(Entry.getKey() + " => " + Entry.getValue()));


    //ejemplo básico de partitioningBy

    //List<Integer> numeros = List.of(1,2,3,4,5,6);
    //Map<Boolean,List<Integer>> resultado = numeros.stream()
    //.collect(Collectors.partitioningBy(n -> n % 2 ==0 ));
//
    //System.out.println("Pares: "+ resultado.get(true));
    //System.out.println("Impares: "+ resultado.get(false));


    //partitioninBY + counting
    //saber cuántos cumples y cuántos no cumplen una condición
    //List<Integer> numeros = List.of(3, 7, 10, 12, 5, 8);
//
    //Map<Boolean, Long> conteo = numeros.stream()
    //.collect(Collectors.partitioningBy(
    //    n -> n > 6,          // Condición
    //    Collectors.counting()// Cuenta en cada grupo
    //));
//
    //System.out.println("Mayores a 6: " + conteo.get(true));
    //System.out.println("Menores o iguales a 6: " + conteo.get(false));


    //partitioningBy + mapping
    //transformar cada grupo antes de guardarlo 

    //List<String> nombres = List.of("Ana", "Pedro", "Juan", "Maria");
//
    //Map<Boolean, List<String>> particion = nombres.stream()
    //.collect(Collectors.partitioningBy(
    //    n -> n.length() > 3, // Condición
    //    Collectors.mapping(String::toUpperCase, Collectors.toList())
    //));
//
    //System.out.println("Nombres largos: " + particion.get(true));
    //System.out.println("Nombres cortos: " + particion.get(false));

    //List<Integer> edades = List.of(12, 25, 30, 17, 40, 15);
//
    //Map<Boolean, IntSummaryStatistics> estadisticas = edades.stream()
    //.collect(Collectors.partitioningBy(
    //    e -> e >= 18, // Adultos y menores
    //    Collectors.summarizingInt(Integer::intValue)
    //));
//
    //System.out.println("Adultos: " + estadisticas.get(true));
    //System.out.println("Menores: " + estadisticas.get(false));

   
    //PARTITIONINGBy

    //Map<Boolean, List<Producto>> partition = productos.stream()
    //.collect(Collectors.partitioningBy(p -> p.getStock() > 0));
//
    ////mostrar resultado
    //System.out.println("====Productos Disponibles");
    //partition.get(true).forEach(p -> System.out.println(p.getNombre()+ " = "+p.getStock()));
    //System.out.println("====Productos Agotados");
    //partition.get(false).forEach(p -> System.out.println(p.getNombre()+" = "+p.getStock()));
      
    //SEPARAR LOS PRODUCTOS EN DOS GRUPOS LOS PRODUCTOS CUYO PRECIO ES MAYOR O IGUAL AL PROMEDIO de todos los productos
    //primero calcula el promedio con MaptoDouble.avergae
    //luego usa partitioningBy

    //double promedio = productos.stream()
    //    .mapToDouble(Producto::getPrecio)
    //    .average()
    //    .orElse(0.0);
    //
    //System.out.println("Promedio de precios: "+promedio);
//
    //Map<Boolean,List<Producto>> MayorAlPromedio = productos.stream()
    //    .collect(Collectors.partitioningBy(p -> p.getPrecio() >= promedio));
    //
    //System.out.println("\n== Productos mayor al promedio (orden ascendente)");
    //MayorAlPromedio.get(true).stream()
    //.sorted(Comparator.comparing(p -> Double.valueOf(p.getPrecio())))
    //.forEach(p -> System.out.println(p.getNombre() + " = " + p.getPrecio()));
//
    //System.out.println(("\n== Productos mayor al promedio (orden descendente)"));
    //MayorAlPromedio.get(false).stream()
    //.sorted(Comparator.comparing((Producto p) -> Double.valueOf(p.getPrecio())))
    //.forEach(p -> System.out.println(p.getNombre()+ " = " +p.getPrecio()));


    //
    
    
   //Map<Boolean,List<Producto>> precioMayorA = productos.stream()
   //.collect(Collectors.partitioningBy(
   //     p -> p.getPrecio()> 90
   //));
   // precioMayorA.forEach((esMayor,lista) -> {
   //     System.out.println(esMayor 
   //                         ? "======> Producto Mayor a S/.90 <======" 
   //                         : "======> Producto Menos a S/.90 <======");
   //     lista.stream()
   //     .sorted(Comparator.comparing(Producto::getPrecio).reversed())
   //     .forEach(p -> System.out.println(p.getNombre() + " = S/."+  p.getPrecio()));
   // });

   //   QUEREMOS SEPARAR LOS PRODUCTOS CUYO PRECIO ES MAYOR O IGUAL A S/.100 Y CUYO STOCK ES MAYOR O IGUAL A 50 DE TODOS LOS DEMÁS

   //Map<Boolean,List<Producto>> premiumStock = productos.stream()
   //.collect(Collectors.partitioningBy(
   // p -> p.getPrecio() >= 100 && p.getStock() >= 50
   //));
   //premiumStock.forEach((esPremium,lista)-> {
   //    System.out.println(esPremium ? "STOCK PREMIUM ==> " : "STOCK NO PREMIUM");
   //     lista.stream()
   //     .sorted(Comparator.comparing(Producto::getPrecio).reversed())
   //     .forEach(p -> System.out.println(p.getNombre()+" | Precio S/."+ String.format("%.2f", p.getPrecio())+" | Stock : " +p.getStock()));
   //});


   //EJEMPLO DE PARTITIONINGBY ANIDADO DE DOS GRUPOS

   //Map<Boolean,Map<Boolean,List<Producto>>> clasificacion = productos.stream()
   //.collect(Collectors.partitioningBy(
   //     p -> p.getPrecio()>=100, //esPremium
   //     Collectors.partitioningBy(
   //         p -> p.getStock()>=50 //altoStock
   //     )
   //));
//
   //clasificacion.forEach((esPremium,mapeoStock)-> {
   // mapeoStock.forEach((altoStock,lista)->{
   //     String categoria;
   //     if(esPremium && altoStock){
   //         categoria = " Premium - Alto Stock";
   //     }else if(esPremium){
   //         categoria = " Premium - Bajo Stock";
   //     }else if(altoStock){
   //         categoria = "Económico - Alto Stock";
   //     }else {
   //         categoria = "Económico - Bajo Stock";
   //     }
   // System.out.println("=====> "+categoria+"<=====");
   // lista.stream()
   // .sorted(Comparator.comparing(Producto::getPrecio).reversed())
   // .forEach(p -> System.out.println(p.getNombre() + " | Precio : "+String.format("%.2f", p.getPrecio())+" | Stock : " +p.getStock()));
   // });
   //});

   //EXAMEN FINAL SEMANA 2
   //Ejercicio 1 — partitioningBy con triple condición (usando tu stock)
   //Debes clasificar los productos en Bajo, Medio y Alto stock:
   //Bajo: stock menor a 20
   //Medio: stock entre 20 y 50
   //Alto: stock mayor a 50
   //Mostrar cada grupo ordenado de mayor a menor precio.

   //Map<Boolean,Map<Boolean,List<Producto>>> tripleC = productos.stream()
   //.collect(Collectors.partitioningBy(
   // p -> p.getStock() <= 20, //bajo
   // Collectors.partitioningBy(
   //     p -> p.getStock()>=20  && p.getStock()<= 50//medio
   // )
   //));
   //tripleC.forEach((bajo,mapeomedio)->{
   // mapeomedio.forEach((medio,lista)->{
   //     String categoria;
   //     if(bajo && medio){
   //         categoria = "Producto entre bajo y medio Stock";
   //     }else if(bajo){
   //         categoria = "Productos con bajo Stock";
   //     }else if(medio){
   //         categoria = "Productos con medio Stock";
   //     }else{
   //         categoria = "Productos con Alto Stock";
   //     }
   //     System.out.println("=====> "+categoria+" <=====");
   //     lista.stream()
   //     .sorted(Comparator.comparing(Producto::getPrecio).reversed())
   //     .forEach(p -> System.out.println(p.getNombre()+" | Precio : S/. " +p.getPrecio()+" | Stock : "+p.getStock()));
   // });
   //});
   //
   //System.out.println("EJERCICIO 2");
//
   //Ejercicio 2 — partitioningBy + groupingBy con tu campo categoria
   //Separar productos en dos grupos: Precio > 100 y Precio <= 100.
   //Dentro de cada grupo, agrupar por categoría.
   //Mostrar resultados en consola con títulos claros.
//
   //Map<Boolean,Map<Integer,List<Producto>>> pG = productos.stream()
   //.collect(Collectors.partitioningBy(
   // p -> p.getPrecio()>100,
   // Collectors.groupingBy(
   //     Producto::getCategoria_id,
   //     Collectors.toList()
   // )
   //));
   //pG.forEach((mayorA,mapaXCategoria)->{
   // System.out.println(mayorA 
   //     ? "\n===> Precios mayor a S./ 100\n"
   //     : " ===> Precios menor a S./ 100\n" );
   // mapaXCategoria.forEach((categoriaId,lista)->{
   //     System.out.println("\nCategoria ID: "+categoriaId+"\n");
   //     lista.stream()
   //     .sorted(Comparator.comparing(Producto::getPrecio).reversed())
   //     .forEach(p -> System.out.println(p.getNombre() + " | Precio : S/."+String.format("%.2f", p.getPrecio())+" | Stock : "+p.getStock()));
   // });
   //});

// SEMANA 3
    //Map<Integer,List<String>> nombrePorCategoria = productos.stream()
    //.collect(Collectors.groupingBy(
    //    Producto::getCategoria_id,
    //    Collectors.mapping(
    //        Producto::getNombre,
    //        Collectors.toList()
    //    )
    //));
    //nombrePorCategoria.forEach((categoria,nombres)-> System.out.println("Categoria " +categoria+" : "+nombres));


    //Lista única de todos los nombres de productos vendidos(sin importar en qué venta estaban), ordenados alfabéticamente
    //partidmos de la lista de ventas(ventas)
    //aplanamos (flatten) la lista de detalles de cada venta con flatMap
    //Obtenemos el nombre del producto usando una búsqueda en productos
    //Eliminamos duplicados(distinct)
    //Ordenamos alfabéticamente

    //List<String> productoVendidos = ventas.stream()
    //    .flatMap( v -> v.getDetalles().stream())
    //    .map(det -> {
    //        Producto p = productos.stream()
    //            .filter(prod -> prod.getId() == det.getProducto_id())  
    //            .findFirst()
    //            .orElse(null);
    //        return (p != null) ? p.getNombre() : null;
    //    })
    //    .filter(Objects::nonNull)
    //    .distinct()
    //    .sorted()
    //    .collect(Collectors.toList());
    //    System.out.println("Productos Vendidos :");
    //    productoVendidos.forEach(System.out::println);
//

    //EJERCICIO 1 - PRODUCTOS VENDIDOS EN TODAS LAS VENTAS
    //Objetivo: Obtener una lista única de los nombres de todos los productos que han sido vendidos, ordenados alfabéticamente.

    //List<String> productosVendidos = ventas.stream()
    //    .flatMap( v -> v.getDetalles().stream())
    //    .map( det -> {
    //        Producto p = productos.stream()
    //            .filter(prod -> prod.getId() == det.getId())
    //            .findFirst()
    //            .orElse(null);
    //        return (p != null) ? p.getNombre() : null;
    //    })
    //    .filter(Objects::nonNull)
    //    .sorted(Comparator.naturalOrder())
    //    .collect(Collectors.toList());
    //    System.out.println("Productos Vendidos");
    //    productosVendidos.forEach(System.out::println);
    
    //Ejercicio 2  Total de ingresos por producto
    //Objetivo: Mostrar cada producto vendido junto con la suma total que ha generado en todas las ventas.
    //Map<String, Double> totalDeIngresosPorVenta = ventas.stream()
    //.flatMap(v -> v.getDetalles().stream())
    //.collect(Collectors.groupingBy(
    //    det -> {
    //        Producto p = productos.stream()
    //        .filter(prod -> prod.getId() == det.getProducto_id())
    //        .findFirst()
    //        .orElse(null);
    //        return (p != null) ? p.getNombre() : "Producto no vendido";
    //    },
    //    Collectors.summingDouble(Detalle_venta::getPrecio_total)
    //));
    //System.out.println("====> Productos Vendidos <====");
    //totalDeIngresosPorVenta.forEach((nombre, total) -> 
    //System.out.println(nombre + " -> "+total));

    //Ejercicio 3 – Lista de productos vendidos por cliente
    //Objetivo: Mostrar para cada cliente, todos los productos que ha comprado (sin repetidos).

   //Map<String, Set<String>> productoVendidosPorClientes = ventas.stream()
   //.collect(Collectors.groupingBy(
   // v -> {
   //     Cliente cliente = clientes.stream()
   //     .filter(cli -> cli.getId()== v.getCliente_id())
   //     .findFirst()
   //     .orElse(null);
   //     return (cliente != null) ? cliente.getNombre() : "Cliente desconocido";
   // },
   // Collectors.flatMapping(
   //     v -> v.getDetalles().stream()
   //     .map(det ->{
   //         Producto producto = productos.stream()
   //         .filter(pro -> pro.getId() == det.getProducto_id())
   //         .findFirst()
   //         .orElse(null);
   //         return (producto != null) ? producto.getNombre() : "Producto no vendido por cliente";
   //     })
   //     .filter(Objects::nonNull),
   //     Collectors.toSet()
   // )
   //));
   //productoVendidosPorClientes.forEach((C,P)->{
   // System.out.println("Cliente : "+C);
   // P.forEach(pr -> System.out.println("    - "+pr));
   //});

   //Objetivo:
   //Mostrar para cada categoría, los 3 productos más vendidos (según la suma de cantidades en detalle_ventas).

   Map<String, List<Map.Entry<String,Integer>>> top3ProductosMasVendidos = ventas.stream()
   .flatMap(v -> v.getDetalles().stream())
   .collect(Collectors.groupingBy(
        det -> productos.stream()
            .filter(p -> p.getId() == det.getProducto_id())
            .findFirst()
            .map(p -> Categoria_productos.stream()
            .filter(c-> c.getId() == p.getCategoria_id())
            .findFirst()
            .map(Categoria_producto::getNombre)
            .orElse("Sin categoria"))
        .orElse("Sin categoria"),
        Collectors.groupingBy(
            det -> productos.stream()
                .filter(p -> p.getId() == det.getProducto_id())
                .findFirst()
                .map(Producto::getNombre)
                .orElse("Producto desconocido"),
                Collectors.summingInt(Detalle_venta::getCantidad)
        )
   ))
   .entrySet().stream()
   .collect(Collectors.toMap(
        Map.Entry::getKey,
        entry -> entry.getValue().entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toList())
   ));
   top3ProductosMasVendidos.forEach((C,L)->{
    System.out.println("Categoria : " +C);
    L.forEach(e -> System.out.println("     - " +e.getKey() +" = "+e.getValue()+" cantidades"));
   });
   


    


    
    

  }
}

