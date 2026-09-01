package clases;

public abstract class ItemBiblioteca {
  private int id;
  private String titulo;
  private int disponibles;
  private int stock;
  private double costo;

  public ItemBiblioteca(String titulo, double costo, int stock) {
    this.id = -1;
    this.titulo = titulo;
    this.stock = stock;
    this.costo = costo;
    this.disponibles = stock;
  }

  public ItemBiblioteca(String titulo, double costo, int stock, int disponibles) {
    this.id = -1;
    this.titulo = titulo;
    this.stock = stock;
    this.costo = costo;
    this.disponibles = disponibles;
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

  public void asignarIdGenerado(int id) {
    this.id = id;
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
    if (disponibles <= 0) {
      throw new IllegalStateException("ERROR: ITEM NO DISPONIBLE");
    }
    disponibles--;
  }

  public void devolver() throws IllegalStateException {
    if (disponibles >= stock) {
      throw new IllegalStateException("ERROR: STOCK REGISTRADO ALCANZADO");
    }
    disponibles++;
  }

  public abstract String getInfo();
}
