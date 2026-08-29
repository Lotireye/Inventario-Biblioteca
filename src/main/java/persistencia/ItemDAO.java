package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Libro;
import clases.Revista;

public class ItemDAO {
  public void guardar(Libro libro) throws SQLException {
    String sqlItem = "INSERT INTO items (titulo, costo, stock, disponibles) VALUES (?, ?, ?, ?)";
    Connection conn = Conexion.conectar();
    PreparedStatement stmt = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
    stmt.setString(1, libro.getTitulo());
    stmt.setDouble(2, libro.getCosto());
    stmt.setInt(3, libro.getStock());
    stmt.setInt(4, libro.disponibles());

    stmt.executeUpdate();

    ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) {
      int id = rs.getInt(1);
      String sqlRevista = "INSERT INTO libros (item_id, autor, genero) VALUES (?, ?, ?)";
      PreparedStatement st = conn.prepareStatement(sqlRevista);
      st.setInt(1, id);
      st.setString(2, libro.getAutor());
      st.setString(3, libro.getGenero());
    }

  }

  public void guardar(Revista revista) throws SQLException {
    String sqlItem = "INSERT INTO items (titulo, costo, stock, disponibles) VALUES (?, ?, ?, ?)";
    Connection conn = Conexion.conectar();
    PreparedStatement stmt = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS);
    stmt.setString(1, revista.getTitulo());
    stmt.setDouble(2, revista.getCosto());
    stmt.setInt(3, revista.getStock());
    stmt.setInt(4, revista.disponibles());

    stmt.executeUpdate();
    ResultSet rs = stmt.getGeneratedKeys();
    if (rs.next()) {
      int id = rs.getInt(1);
      String sqlRevista = "INSERT INTO revistas (item_id, num_edicion, periodicidad) VALUES (?, ?, ?)";
      PreparedStatement st = conn.prepareStatement(sqlRevista);
      st.setInt(1, id);
      st.setInt(2, revista.getEdicion());
      st.setString(3, revista.getPeriocidad());
    }

  }

}
