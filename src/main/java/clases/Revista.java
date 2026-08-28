package clases;

public class Revista extends ItemBiblioteca {
  private int numEdicion;
  private String periodicidad;

  public Revista(int id, String titulo, double costo, int stock, int numEdicion, String periodicidad) {
    super(id, titulo, costo, stock);
    this.numEdicion = numEdicion;
    this.periodicidad = periodicidad;
  }

  public int getEdicion() {
    return numEdicion;
  }

  public String getPeriocidad() {
    return periodicidad;
  }

  @Override
  public String getInfo() {
    return """
        ID ITEM : #%04d
        TITULO: %s
        EDICION: %d
        PERIODICIDAD: %s
        COSTO: %.2f
        DISPONIBILIDAD: %d
        """.formatted(this.getID(), this.getTitulo(), numEdicion, periodicidad, this.getCosto(), this.disponibles());
  }
}
