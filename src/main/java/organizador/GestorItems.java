package organizador;

import java.sql.SQLException;
import java.util.ArrayList;
import clases.ItemBiblioteca;
import persistencia.ItemDAO;
import utilidades.ItemFactory;

public class GestorItems {
  private ItemDAO itemDAO = new ItemDAO();

  public GestorItems() {
  }

  public void agregar(String titulo, double costo, int stock, String autor, String genero) {
    ItemBiblioteca item = ItemFactory.crearLibro(titulo, costo, stock, autor, genero);
    try {
      itemDAO.guardar(item);
    } catch (SQLException ex) {
      System.out.println("ERROR: NO SE PUDO GUARDAR EN LA BASE DE DATOS");
    }
  }

  public void agregar(String titulo, double costo, int stock, int numEdicion, String periodicidad) throws SQLException {
    itemDAO.guardar(ItemFactory.crearRevista(titulo, costo, stock, numEdicion, periodicidad));
  }

  public ArrayList<ItemBiblioteca> listarItems() throws SQLException {
    return itemDAO.listar();
  }

  public ItemBiblioteca buscarItem(int id) throws SQLException {
    return itemDAO.buscarID(id);
  }

  public void prestar(int id) throws SQLException {
    ItemBiblioteca item = buscarItem(id);
    if (item != null) {
      item.prestar();
      itemDAO.actualizarDisponibles(id, item.disponibles());
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }

  }

  public void devolver(int id) throws SQLException {
    ItemBiblioteca item = buscarItem(id);
    if (item != null) {
      item.devolver();
      itemDAO.actualizarDisponibles(id, item.disponibles());
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }
  }

  public void eliminar(int id) throws SQLException {
    int filasAfectadas = itemDAO.eliminarItem(id);
    if (filasAfectadas > 0) {
      System.out.println("ITEM ELIMINADO");
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }
  }

  public void editarStock(int id, int stockAñadido) throws SQLException {
    ItemBiblioteca item = itemDAO.buscarID(id);
    if (item != null) {
      item.editarStock(stockAñadido);
      itemDAO.actualizarStock(id, item.getStock());
    }
  }

  public void editarCosto(int id, double costo) throws SQLException {
    ItemBiblioteca item = itemDAO.buscarID(id);
    if (item != null) {
      item.setCosto(costo);
      itemDAO.actualizarCosto(id, costo);
    }
  }

}
