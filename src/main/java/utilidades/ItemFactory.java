package utilidades;

import clases.ItemBiblioteca;
import clases.Libro;
import clases.Revista;

public class ItemFactory {
  public static ItemBiblioteca crearLibro(String titulo, double costo, int stock, String autor, String genero) {
    return new Libro(titulo, costo, stock, autor, genero);
  }

  public static ItemBiblioteca crearRevista(String titulo, double costo, int stock, int numEdicion,
      String periodicidad) {
    return new Revista(titulo, costo, stock, numEdicion, periodicidad);
  }
}
