package principal;

import clases.ItemBiblioteca;
import organizador.GestorItems;
import utilidades.Lector;

public class Main {

  public static void main(String[] args) {
    Lector sc = new Lector();
    GestorItems gestor = new GestorItems();
    int opcion = 0;

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

          gestor.agregar(titulo, costo, stock, numEdicion, periodicidad);
          break;

        case 3:
          if (gestor.getCantItems() > 0) {
            gestor.listarItems();
          } else {
            System.out.println("NO HAY ITEMS REGISTRADOS\n");
          }
          break;

        case 4:
          gestor.prestar(sc.leerEntero("ID DEL ITEM:"));
          break;

        case 5:
          gestor.devolver(sc.leerEntero("ID DEL ITEM: "));
          break;

        case 6:
          ItemBiblioteca item = gestor.buscarItem(sc.leerEntero("ID DEL ITEM: "));
          if (item == null) {
            System.out.println("ERROR: ID NO ENCONTRADO");
            break;
          }
          int eleccion = 0;
          do {
            System.out.println("EDITOR DE ITEM");
            System.out.println("1. Editar Stock");
            System.out.println("2. Editar Costo");
            System.out.println("3. Salir");
            eleccion = sc.leerEntero("Ingrese la opcion: ");
            switch (eleccion) {
              case 1:
                item.editarStock(sc.leerEntero("STOCK AÑADIDO (POSITIVO) O DISMINUIDO (NEGATIVO): "));
                break;

              case 2:
                item.setCosto(sc.leerDouble("NUEVO COSTO: "));
                break;

              case 3:
                break;

              default:
                System.out.println("Ingrese una opcion del 1 al 4");
                break;
            }
          } while (eleccion != 3);
          break;

        case 7:
          gestor.eliminar(sc.leerEntero("ID DEL ITEM: "));
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
