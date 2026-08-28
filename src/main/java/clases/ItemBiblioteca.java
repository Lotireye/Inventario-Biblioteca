package clases;

public abstract class ItemBiblioteca {
  private int id;
  private String titulo;
  private int disponibles;
  private int stock;
  private double costo;

  public ItemBiblioteca(int id, String titulo, double costo, int stock) {
    this.id = id;
    this.titulo = titulo;
    this.stock = stock;
    this.costo = costo;
    this.disponibles = stock;
  }

  public void editarStock(int numeroPositivoONegativo) {
    int aux = stock + numeroPositivoONegativo;
    if (aux >= 0) {
      stock = aux;
    } else {
      System.out.println("ERROR: STOCK FINAL NEGATIVO");
    }

  }

  public void setCosto(double nuevoCosto) {
    if (nuevoCosto > 0) {
      costo = nuevoCosto;
    } else {
      System.out.println("ERROR: PRECIO NEGATIVO O CERO");
    }
  }

  public void setTitulo(String tituloNuevo) {
    titulo = tituloNuevo;
  }

  public int getID() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public int disponibles() {
    return disponibles;
  }

  public int getStock() {
    return stock;
  }

  public double getCosto() {
    return costo;
  }

  public void prestar() throws IllegalStateException {
    try {
      if (disponibles <= 0) {
        throw new IllegalStateException("ERROR: ITEM NO DISPONIBLE");
      }
      disponibles--;
    } catch (IllegalStateException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void devolver() throws IllegalStateException {
    try {
      if (disponibles >= stock) {
        throw new IllegalStateException("ERROR: STOCK REGISTRADO ALCANZADO");
      }
      disponibles++;
    } catch (IllegalStateException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public abstract String getInfo();
}
