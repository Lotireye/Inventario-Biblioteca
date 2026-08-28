package utilidades;

import clases.ItemBiblioteca;
import clases.Libro;
import clases.Revista;

public class ItemFactory {
  public static ItemBiblioteca crearLibro(int id, String titulo, double costo, int stock, String autor, String genero) {
    return new Libro(id, titulo, costo, stock, autor, genero);
  }

  public static ItemBiblioteca crearRevista(int id, String titulo, double costo, int stock, int numEdicion,
      String periodicidad) {
    return new Revista(id, titulo, costo, stock, numEdicion, periodicidad);
  }
}
