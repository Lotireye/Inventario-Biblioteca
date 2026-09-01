package principal;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.ItemBiblioteca;
import organizador.GestorItems;
import persistencia.ItemDAO;
import utilidades.Lector;

public class Main {

  public static void main(String[] args) {
    Lector sc = new Lector();
    GestorItems gestor = new GestorItems();
    int opcion = 0;

    ItemDAO itemDAO = new ItemDAO();
    try {
      itemDAO.listar();
    } catch (SQLException ex) {
      System.out.println("ERROR SQL ");
    }

    do {
      System.out.println("\n=======================");
      System.out.println("ADMINISTRADOR DE ITEMS");
      System.out.println("1. Agregar Libro");
      System.out.println("2. Agregar Revista");
      System.out.println("3. Listar Items");
      System.out.println("4. Prestar");
      System.out.println("5. Devolver");
      System.out.println("6. Editar");
      System.out.println("7. Eliminar Item");
      System.out.println("8. Salir");
      System.out.println("=======================\n");
      opcion = sc.leerEntero("Ingrese la opcion: ");

      switch (opcion) {
        case 1:
          System.out.println("\nREGISTRO DE LIBRO: ");
          String titulo = sc.leerTexto("TITULO: ");
          String autor = sc.leerTexto("AUTOR: ");
          String genero = sc.leerTexto("GENERO: ");
          double costo = sc.leerDouble("COSTO: ");
          int stock = sc.leerEntero("STOCK: ");
          gestor.agregar(titulo, costo, stock, autor, genero);
          break;

        case 2:
          titulo = sc.leerTexto("TITULO: ");
          int numEdicion = sc.leerEntero("EDICION: ");
          String periodicidad = sc.leerTexto("PERIODICIDAD: ");
          costo = sc.leerDouble("COSTO: ");
          stock = sc.leerEntero("STOCK: ");
          try {
            gestor.agregar(titulo, costo, stock, numEdicion, periodicidad);
          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO GUARDAR EN LA BASE DE DATOS");
          }
          break;

        case 3:
          try {
            ArrayList<ItemBiblioteca> items = gestor.listarItems();
            if (!items.isEmpty()) {
              for (ItemBiblioteca item : items) {
                System.out.println(item.getInfo());
              }
            } else {
              System.out.println("NO HAY ITEMS REGISTRADOS");
            }
          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
          }
          break;

        case 4:
          try {
            gestor.prestar(sc.leerEntero("ID DEL ITEM:"));
          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
          } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
          }
          break;

        case 5:
          try {
            gestor.devolver(sc.leerEntero("ID DEL ITEM: "));
          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
          } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
          }
          break;

        case 6:
          ItemBiblioteca item = null;
          try {
            item = gestor.buscarItem(sc.leerEntero("ID DEL ITEM: "));
          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
            break;
          }
          if (item == null) {
            System.out.println("ERROR: ID NO ENCONTRADO");
            break;
          }

          int eleccion = 0;

          do {
            System.out.println("EDITOR DE ITEM");
            System.out.println("1. Agregar Stock");
            System.out.println("2. Reducir Stock");
            System.out.println("3. Editar Costo");
            System.out.println("4. Salir");
            eleccion = sc.leerEntero("Ingrese la opcion: ");
            switch (eleccion) {
              case 1:
                try {
                  gestor.editarStock(item.getID(), sc.leerEntero("STOCK AÑADIDO: "));
                } catch (SQLException ex) {
                  System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
                }
                break;

              case 2:
                int nuevoStock = sc.leerEntero("STOCK DISMINUIDO: ");
                try {
                  gestor.editarStock(item.getID(), (nuevoStock * -1));
                } catch (SQLException ex) {
                  System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
                }
                break;

              case 3:
                try {
                  gestor.editarCosto(item.getID(), sc.leerDouble("NUEVO COSTO: "));
                } catch (SQLException ex) {
                  System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
                }
                break;

              case 4:
                break;

              default:
                System.out.println("Ingrese una opcion del 1 al 4");
                break;
            }
          } while (eleccion != 4);
          break;

        case 7:
          try {
            gestor.eliminar(sc.leerEntero("ID DEL ITEM: "));

          } catch (SQLException ex) {
            System.out.println("ERROR: NO SE PUDO COMUNICAR CON LA BASE DE DATOS");
          }
          break;
        case 8:
          System.out.println("Saliendo del programa...");
          sc.close();
          break;

        default:
          System.out.println("Ingrese una opcion del 1 al 6");
          break;
      }
    } while (opcion != 8);

  }

}
