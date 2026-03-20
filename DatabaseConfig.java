import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

  //Obtener los datos del archivo .env
    String host = System.getenv("DB_HOST");
    String port = System.getenv("DB_PORT");
    String user = System.getenv("MY_USER");
    String password = System.getenv("MY_PASSWORD");

    private static Connection connect (String dbName ) throws SQLException{
      String url = "jdbc:mysql://"+ host ":" + port + "/" + dbName + "?serverTimeZone=UTC";
      return DriverManager.getConnection(url, user, password);
    }
    // #1
     public static Connection getProductosConn() throws SQLException {
        return connect(System.getenv("DB_NAME_PRODUCTOS"));
    }

        // #2
     public static Connection getVentasConn() throws SQLException {
        return connect(System.getenv("DB_NAME_VENTAS"));
    }

        // #3
     public static Connection getDetalle_VentasConn() throws SQLException {
        return connect(System.getenv("DB_NAME_VENTAS"));
    }

        // #4
     public static Connection getClientesConn() throws SQLException {
        return connect(System.getenv("DB_NAME_CLIENTES"));
    }

        // #5
     public static Connection getEmpleadosConn() throws SQLException {
        return connect(System.getenv("DB_NAME_EMPLEADOS"));
    }

        // #6
     public static Connection getCategoria_proudctosConn() throws SQLException {
        return connect(System.getenv("DB_NAME_PRODUCTOS"));
    }

        // #7
     public static Connection getFacturasConn() throws SQLException {
        return connect(System.getenv("DB_NAME_FACTURAS"));
    }

        // #8
     public static Connection getInventarioConn() throws SQLException {
        return connect(System.getenv("DB_NAME_INVENTARIO"));
    }

        // #9
     public static Connection getProveedoresConn() throws SQLException {
        return connect(System.getenv("DB_NAME_PROVEEDORES"));
    }
}