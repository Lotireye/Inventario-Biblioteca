package persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {
  public static Connection conectar() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/Biblioteca";
    String usuario = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");
    Connection conn = DriverManager.getConnection(url, usuario, password);
    return conn;
  }
}
