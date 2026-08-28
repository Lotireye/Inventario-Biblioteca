package utilidades;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Lector {
  private Scanner sc;

  public Lector() {
    this.sc = new Scanner(System.in);
  }

  public String leerTexto(String mensaje) {
    System.out.println(mensaje);
    return sc.nextLine();
  }

  public int leerEntero(String mensaje) {
    int valor = 0;
    boolean exito = false;
    do {
      System.out.println(mensaje);
      try {
        valor = sc.nextInt();
        sc.nextLine();
        exito = true;

      } catch (InputMismatchException e) {
        System.out.println("ERROR: INGRESE UN NUMERO VALIDO");
        sc.nextLine();
      }
    } while (!exito);
    return valor;
  }

  public double leerDouble(String mensaje) {
    double valor = 0;
    boolean exito = false;
    do {
      System.out.println(mensaje);
      try {
        valor = sc.nextDouble();
        sc.nextLine();
        exito = true;

      } catch (InputMismatchException e) {
        System.out.println("ERROR: INGRESE UN NUMERO VALIDO");
        sc.nextLine();
      }
    } while (!exito);
    return valor;
  }

  public void close() {
    sc.close();
  }
}
