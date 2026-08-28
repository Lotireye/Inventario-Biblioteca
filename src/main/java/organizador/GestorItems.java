package organizador;

import java.util.ArrayList;
import clases.ItemBiblioteca;
import utilidades.ItemFactory;

public class GestorItems {
  private ArrayList<ItemBiblioteca> items = new ArrayList<>();
  private int contadorItems = 0;

  public GestorItems() {
  }

  public void agregar(String titulo, double costo, int stock, String autor, String genero) {

    items.add(ItemFactory.crearLibro(contadorItems, titulo, costo, stock, autor, genero));
    contadorItems++;

  }

  public void agregar(String titulo, double costo, int stock, int numEdicion, String periodicidad) {

    items.add(ItemFactory.crearRevista(contadorItems, titulo, costo, stock, numEdicion, periodicidad));
    contadorItems++;
  }

  public void listarItems() {
    for (ItemBiblioteca item : items) {
      System.out.println(item.getInfo());
      System.out.println();
    }
  }

  public int getCantItems() {
    return contadorItems;
  }

  public ItemBiblioteca buscarItem(int id) {

    for (ItemBiblioteca item : items) {
      if (item.getID() == id) {
        return item;
      }
    }
    return null;
  }

  public void prestar(int id) {
    ItemBiblioteca item = buscarItem(id);
    if (item != null) {
      item.prestar();
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }

  }

  public void devolver(int id) {
    ItemBiblioteca item = buscarItem(id);
    if (item != null) {
      item.devolver();
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }
  }

  public void eliminar(int id) {
    ItemBiblioteca item = buscarItem(id);
    if (item != null) {
      items.remove(item);
    } else {
      System.out.println("ERROR: ID NO ENCONTRADO");
    }
  }

}
