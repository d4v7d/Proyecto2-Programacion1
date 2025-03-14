import java.util.Scanner;

/**
 * Clase principal del código donde se ejecuta.
 */
public class HojaDeCalculo {

  /**
   * Crea un scanner para recibir input.
   */
  private Scanner input = null;

  /**
   * método main que inicia todo el proceso.
   *
   * @param args .
   */
  public static void main(final String[] args) {
    HojaDeCalculo solution = new HojaDeCalculo();
    solution.run();
  }

  /**
   * método que corre el programa.
   */
  public void run() {

    this.input = new Scanner(System.in);

    //--------------------------------------------------------------------------

    String num = this.input.nextLine();
    num = num.replace("   ", " ");
    num = num.replace("  ", " ");
    String[] num1 = num.split(" ");

    int row = Integer.parseInt(num1[0]);
    int col = Integer.parseInt(num1[1]);
    String none = this.input.nextLine();

    String[][] matriz = new String[row][col];

    int i = 0;

    while (i < row) {

      String mat = this.input.nextLine();
      mat = mat.replace("   ", " ");
      mat = mat.replace("  ", " ");
      mat = mat.replace(" ", "");

      String[] num2 = mat.split(",");

      for (int j = 0; j < col; j++) {

        matriz[i][j] = num2[j];
      }

      i++;
    }
    String nada = this.input.nextLine();

    //--------------------------------------------------------------------------

    Hoja e = new Hoja();
    e.leer(row, col, matriz);
    System.out.println();

    //--------------------------------------------------------------------------

    while (this.input.hasNextLine()) {

      String instruccion = this.input.nextLine();
      String[] inst = instruccion.split("\\(", instruccion.length() - 1);
      String[] a = inst[1].split("\\)");

      //llama a la función cel.
      if (instruccion.contains(">=CEL")) {
        e.cel(a[0]);
      }

      //llama a la función conjunto.
      if (instruccion.contains(">=CONJUNTO")) {
        e.conjunto(a[0]);

      }

      //llama a la función suma.
      if (instruccion.contains(">=SUMA")) {
        if (a[0].contains(":")) {
          e.suma(a[0]);
        } else {
          e.sumaConjunto();

        }
      }

      //llama a la función multiplicación.
      if (instruccion.contains(">=MULT")) {
        if (a[0].contains(":")) {
          e.mult(a[0]);

        } else {
          e.multConjunto();

        }
      }

      //llama a la función promedio.
      if (instruccion.contains(">=PROMEDIO")) {
        e.promedio(a[0]);

      }

      //llama a la función mediana.
      if (instruccion.contains(">=MEDIANA")) {
        e.mediana(a[0]);

      }

      //llama a la función mínima.
      if (instruccion.contains(">=MIN")) {
        e.min(a[0]);

      }

      //llama a la función máximo.
      if (instruccion.contains(">=MAX")) {
        e.max(a[0]);

      }

      //llama a la función imprimir.
      if (instruccion.contains(">=IMPRIMIR")) {
        if (instruccion.equals(">=IMPRIMIR()")) {
          e.imprimir();
          break;
        } else {
          e.imprimirConjunto();

        }
      }
    }

    //--------------------------------------------------------------------------

    // Close the standard input
    this.input.close();

  }
}
