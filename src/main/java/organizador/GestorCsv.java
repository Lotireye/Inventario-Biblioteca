package organizador;

import java.io.*;
import java.util.ArrayList;

public class GestorCsv {

  public ArrayList<String[]> leerCsv(String ruta) throws IOException {
    ArrayList<String[]> atributos = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
      br.readLine();
      String linea;
      while ((linea = br.readLine()) != null) {
        String campos[] = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < campos.length; i++) {
          campos[i] = campos[i].replaceAll("\"", "").trim();
        }

        atributos.add(campos);
      }

      return atributos;
    }
  }

}
