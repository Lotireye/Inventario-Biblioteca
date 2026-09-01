package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.ItemBiblioteca;
import clases.Libro;
import clases.Revista;

public class ItemDAO {

  public void guardar(ItemBiblioteca item) throws SQLException {
    String sqlItem = "INSERT INTO ITEM (titulo, costo, stock, disponibles) VALUES (?, ?, ?, ?)";

    try (Connection conn = Conexion.conectar();
        PreparedStatement stmt = conn.prepareStatement(sqlItem, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, item.getTitulo());
      stmt.setDouble(2, item.getCosto());
      stmt.setInt(3, item.getStock());
      stmt.setInt(4, item.disponibles());
      stmt.executeUpdate();

      try (ResultSet rs = stmt.getGeneratedKeys()) {
        if (rs.next()) {
          int id = rs.getInt(1);
          item.asignarIdGenerado(id); // aprovechamos y dejamos el objeto original consistente también

          if (item instanceof Revista revista) {
            String sqlRevista = "INSERT INTO REVISTA (item_id, num_edicion, periodicidad) VALUES (?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sqlRevista)) {
              st.setInt(1, id);
              st.setInt(2, revista.getEdicion());
              st.setString(3, revista.getPeriodicidad());
              st.executeUpdate();
            }
          } else if (item instanceof Libro libro) {
            String sqlLibro = "INSERT INTO LIBRO (item_id, autor, genero) VALUES (?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(sqlLibro)) {
              st.setInt(1, id);
              st.setString(2, libro.getAutor());
              st.setString(3, libro.getGenero());
              st.executeUpdate();
            }
          }
        }
      }
    }
  }

  public ArrayList<ItemBiblioteca> listar() throws SQLException {
    ArrayList<ItemBiblioteca> items = new ArrayList<>();

    String sqlLibros = """
        SELECT ITEM.id, ITEM.titulo, ITEM.costo, ITEM.stock, ITEM.disponibles, LIBRO.autor, LIBRO.genero
        FROM ITEM JOIN LIBRO ON ITEM.id = LIBRO.item_id
        """;
    String sqlRevistas = """
        SELECT ITEM.id, ITEM.titulo, ITEM.costo, ITEM.stock, ITEM.disponibles, REVISTA.num_edicion, REVISTA.periodicidad
        FROM ITEM JOIN REVISTA ON ITEM.id = REVISTA.item_id
        """;

    try (Connection conn = Conexion.conectar()) {

      try (PreparedStatement stmtLibro = conn.prepareStatement(sqlLibros);
          ResultSet rsLibro = stmtLibro.executeQuery()) {
        while (rsLibro.next()) {
          int id = rsLibro.getInt("id");
          String titulo = rsLibro.getString("titulo");
          double costo = rsLibro.getDouble("costo");
          int stock = rsLibro.getInt("stock");
          int disponibles = rsLibro.getInt("disponibles");
          String autor = rsLibro.getString("autor");
          String genero = rsLibro.getString("genero");
          Libro libro = new Libro(titulo, costo, stock, disponibles, autor, genero);
          libro.asignarIdGenerado(id);
          items.add(libro);
        }
      }

      try (PreparedStatement stmtRevista = conn.prepareStatement(sqlRevistas);
          ResultSet rsRevista = stmtRevista.executeQuery()) {
        while (rsRevista.next()) {
          int id = rsRevista.getInt("id");
          String titulo = rsRevista.getString("titulo");
          double costo = rsRevista.getDouble("costo");
          int stock = rsRevista.getInt("stock");
          int disponibles = rsRevista.getInt("disponibles");
          int numEdicion = rsRevista.getInt("num_edicion");
          String periodicidad = rsRevista.getString("periodicidad");
          Revista revista = new Revista(titulo, costo, stock, disponibles, numEdicion, periodicidad);
          revista.asignarIdGenerado(id);
          items.add(revista);
        }
      }
    }

    return items;
  }

  public ItemBiblioteca buscarID(int id) throws SQLException {
    String sqlLibro = """
        SELECT ITEM.id, ITEM.titulo, ITEM.costo, ITEM.stock, ITEM.disponibles, LIBRO.autor, LIBRO.genero
        FROM ITEM JOIN LIBRO ON ITEM.id = LIBRO.item_id
        WHERE ITEM.id = ?
        """;
    String sqlRevista = """
        SELECT ITEM.id, ITEM.titulo, ITEM.costo, ITEM.stock, ITEM.disponibles, REVISTA.num_edicion, REVISTA.periodicidad
        FROM ITEM JOIN REVISTA ON ITEM.id = REVISTA.item_id
        WHERE ITEM.id = ?
        """;

    try (Connection conn = Conexion.conectar()) {

      try (PreparedStatement stmtLibro = conn.prepareStatement(sqlLibro)) {
        stmtLibro.setInt(1, id);
        try (ResultSet rsLibro = stmtLibro.executeQuery()) {
          if (rsLibro.next()) {
            String titulo = rsLibro.getString("titulo");
            double costo = rsLibro.getDouble("costo");
            int stock = rsLibro.getInt("stock");
            int disponibles = rsLibro.getInt("disponibles");
            String autor = rsLibro.getString("autor");
            String genero = rsLibro.getString("genero");
            Libro libro = new Libro(titulo, costo, stock, disponibles, autor, genero);
            libro.asignarIdGenerado(id);
            return libro;
          }
        }
      }

      try (PreparedStatement stmtRevista = conn.prepareStatement(sqlRevista)) {
        stmtRevista.setInt(1, id);
        try (ResultSet rsRevista = stmtRevista.executeQuery()) {
          if (rsRevista.next()) {
            String titulo = rsRevista.getString("titulo");
            double costo = rsRevista.getDouble("costo");
            int stock = rsRevista.getInt("stock");
            int disponibles = rsRevista.getInt("disponibles");
            int numEdicion = rsRevista.getInt("num_edicion");
            String periodicidad = rsRevista.getString("periodicidad");
            Revista revista = new Revista(titulo, costo, stock, disponibles, numEdicion, periodicidad);
            revista.asignarIdGenerado(id);
            return revista;
          }
        }
      }

      return null;
    }
  }

  public void actualizarDisponibles(int id, int disponibles) throws SQLException {
    String sqlQuery = """
        UPDATE ITEM SET disponibles = ? WHERE id = ?
        """;

    try (Connection conn = Conexion.conectar();
        PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
      stmt.setInt(1, disponibles);
      stmt.setInt(2, id);

      stmt.executeUpdate();
    }
  }

  public void actualizarStock(int id, int stock) throws SQLException {
    String sqlQuery = """
        UPDATE ITEM SET stock = ? WHERE id = ?
        """;

    try (Connection conn = Conexion.conectar();
        PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
      stmt.setInt(1, stock);
      stmt.setInt(2, id);

      stmt.executeUpdate();
    }
  }

  public void actualizarCosto(int id, double costo) throws SQLException {
    String sqlQuery = """
        UPDATE ITEM SET costo = ? WHERE id = ?
        """;

    try (Connection conn = Conexion.conectar();
        PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
      stmt.setDouble(1, costo);
      stmt.setInt(2, id);

      stmt.executeUpdate();
    }
  }

  public int eliminarItem(int id) throws SQLException {
    String sqlQuery = """
        DELETE FROM ITEM WHERE id = ?
            """;

    try (Connection conn = Conexion.conectar();
        PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
      stmt.setInt(1, id);
      return stmt.executeUpdate();
    }
  }

}
