package clases;

public class Libro extends ItemBiblioteca {
  private String autor;
  private String genero;

  public Libro(String titulo, double costo, int stock, String autor, String genero) {
    super(titulo, costo, stock);
    this.autor = autor;
    this.genero = genero;
  }

  public Libro(String titulo, double costo, int stock, int disponibles, String autor, String genero) {
    super(titulo, costo, stock, disponibles);
    this.autor = autor;
    this.genero = genero;
  }

  public String getAutor() {
    return autor;
  }

  public String getGenero() {
    return genero;
  }

  @Override
  public String getInfo() {
    return """
        ID ITEM : #%04d
        TITULO: %s
        AUTOR: %s
        GENERO: %s
        COSTO: %.2f
        DISPONIBLES: %d
        """.formatted(this.getID(), this.getTitulo(), autor, genero, this.getCosto(), this.disponibles());
  }
}
