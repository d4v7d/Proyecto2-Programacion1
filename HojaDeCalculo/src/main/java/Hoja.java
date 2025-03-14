/**
 * Esta clase crea la hoja de "excel y sus operaciones si son llamadas".
 */
public class Hoja {
  /**
   * Variable para crear el largo de la matriz.
   */
  private int row;
  /**
   * Variable para crear el ancho de la matriz.
   */
  private int col;
  /**
   * La matriz que contiene todas las celdas.
   */
  private String[][] hojaDeCalculo;
  /**
   * Matriz con los numeradores.
   */
  private String[][] celda1;
  /**
   * Matriz con los denominadores.
   */
  private String[][] celda2;
  /**
   * para almacenar un conjunto.
   */
  private String[] conjunto;
  /**
   * para encontrar la posicione Row de donde se quiera el resultado.
   */
  private int celRow;
  /**
   * para encontrar la posicione Col de donde se quiera el resultado.
   */
  private int celCol;
  /**
   * guarda el numerador del resultado de la suma.
   */
  private int suma1;
  /**
   * guarda el denominador del resultado de la suma.
   */
  private int suma2;

  //----------------------------------------------------------------------------

  Hoja() {
    this.row = 0;
    this.col = 0;
  }

  //----------------------------------------------------------------------------

  /**
   * Este metodo crea una matriz con los valores
   * dados y crea 2 matrices,(denominador y numerador).
   *
   * @param nrow parámetro de filas.
   * @param ncol parámetro de columna.
   * @param matriz la matriz con la que trabajamos.
   */

  public void leer(final int nrow, final int ncol, final String[][] matriz) {

    row = nrow;
    col = ncol;
    hojaDeCalculo = matriz;

    //la celda1 es la que va a contener
    // los números de la izquierda (numerador).

    String[][] celdas1 = new String[this.row][this.col];
    celda1 = celdas1;

    //la celda2 es la que va a contener
    // los números de la derecha (denominador).

    String[][] celdas2 = new String[this.row][this.col];
    celda2 = celdas2;

    //inserta los valores en su respectiva matriz.
    for (int i = 0; i < hojaDeCalculo.length; i++) {
      for (int j = 0; j < hojaDeCalculo[i].length; j++) {
        String[] dividirHoja = hojaDeCalculo[i][j].split("/");
        celda1[i][j] = dividirHoja[0];
        celda2[i][j] = dividirHoja[1];

      }
    }

  }

  //----------------------------------------------------------------------------

  /**
   * Este método crea la dirección donde se
   * quiere guardar a la hora de hacer una operación.
   *
   * @param casilla Las casillas que solicitan.
   */

  void cel(final String casilla) {

    celRow = 0;
    celCol = 0;

    String[] a = casilla.split("");

    // Almacena la dirección dada en los valores siguientes.
    // Se reinicia cada vez que es llamado.

    celRow = Integer.parseInt(a[1]) - 1;
    celCol = letras(a[0]);

  }

  //----------------------------------------------------------------------------

  /**
   * Este método crea un conjunto.
   *
   * @param casilla crea un conjunto con el String dado.
   *
   */

  void conjunto(final String casilla) {

    //Almacena el conjunto dado en la lista "conjunto".
    //El detalle es que se reinicia cada vez que es llamado.
    String[] a = casilla.split(",");
    conjunto = a;

  }

  //----------------------------------------------------------------------------

  /**
   * Este método suma las fracciones de las posiciones dadas (filas o columnas).
   *
   * @param casillas Las casillas que solicitan.
   */

  void suma(final String casillas) {

    suma1 = 0;
    suma2 = 0;
    int sumanum = 0;
    int sumadev = 0;

    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");

    //si las letras son iguales se trabajan columnas
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);
      int columna = letras(casilla1[0]);
      int num1 = Integer.parseInt(celda1[inicio - 1][columna]);
      int dev1 = Integer.parseInt(celda2[inicio - 1][columna]);

      for (int i = inicio; i <= fin - 1; i++) {

        int num2 = Integer.parseInt(celda1[i][columna]);
        int dev2 = Integer.parseInt(celda2[i][columna]);
        int tmp;

        //si los denominadores son iguales solo se suman los numeradores.
        if (dev1 == dev2) {
          sumanum = num1 + num2;
          sumadev = dev1;

          num1 = sumanum;
          dev1 = sumadev;
        }

        // si los denominadores son diferentes cambia el método.
        if (dev1 != dev2) {

          //si los denominadores tienen mínimo común divisor.
          if (dev1 % dev2 == 0) {
            tmp = dev1 / dev2;
            sumanum = num1 + (num2 * tmp);
            sumadev = dev1;

            num1 = sumanum;
            dev1 = sumadev;
          } else {
            if (dev2 % dev1 == 0) {
              tmp = dev2 / dev1;
              sumanum = num1 + (num2 * tmp);
              sumadev = dev2;

              num1 = sumanum;
              dev1 = sumadev;
            } else {
              // si no tienen mcd se hace el método tradicional.
              tmp = dev1;
              dev1 = dev2 * dev1;
              num1 = num1 * dev2;
              num2 = num2 * tmp;

              sumanum = num1 + num2;
              sumadev = dev1;

              num1 = sumanum;
              dev1 = sumadev;
            }
          }
        }
      }

      //los resultados de las sumas se guardan.
      suma1 = sumanum;
      suma2 = sumadev;

      //se ponen en sus respectivas celdas.
      celda1[celRow][celCol] = "" + suma1;
      celda2[celRow][celCol] = "" + suma2;

    }
    //si los números son iguales se trabajan filas
    if (casilla2[1].equals(casilla1[1])) {

      int fila = Integer.parseInt(casilla1[1]);
      int inicio = letras(casilla1[0]);
      int fin = letras(casilla2[0]);

      int num1 = Integer.parseInt(celda1[fila - 1][inicio]);
      int dev1 = Integer.parseInt(celda2[fila - 1][inicio]);

      for (int i = inicio + 1; i <= fin; i++) {
        int num2 = Integer.parseInt(celda1[fila - 1][i]);
        int dev2 = Integer.parseInt(celda2[fila - 1][i]);

        //si los denominadores son iguales solo se suman los numeradores.
        if (dev1 == dev2) {

          sumanum = num1 + num2;
          sumadev = dev1;

          num1 = sumanum;
          dev1 = sumadev;
        }

        // si los denominadores son diferentes cambia el método.
        if (dev1 != dev2) {

          //si los denominadores tienen mínimo común divisor.
          if (dev1 % dev2 == 0) {
            int tmp = dev1 / dev2;
            sumanum = num1 + num2 * tmp;
            sumadev = dev1;

            num1 = sumanum;
            dev1 = sumadev;
          } else {
            if (dev2 % dev1 == 0) {
              int tmp = dev2 / dev1;
              sumanum = num1 + num2 * tmp;
              sumadev = dev2;

              num1 = sumanum;
              dev1 = sumadev;
            } else {

              // si no tienen mcd se hace el método tradicional.
              int tmp = dev1;
              dev1 = dev2 * dev1;
              num1 = num1 * dev2;
              num2 = num2 * tmp;

              sumanum = num1 + num2;
              sumadev = dev1;

              num1 = sumanum;
              dev1 = sumadev;
            }
          }
        }
      }

      //los resultados de las sumas se guardan.
      suma1 = sumanum;
      suma2 = sumadev;

      //se ponen en sus respectivas celdas.
      celda1[celRow][celCol] = "" + suma1;
      celda2[celRow][celCol] = "" + suma2;
    }
  }

  //----------------------------------------------------------------------------

  /**
   * Este método suma las casillas del conjunto.
   */

  void sumaConjunto() {

    String nombre = conjunto[0];
    suma1 = 0;
    suma2 = 0;
    int sumanum = 0;
    int sumadev = 0;

    String[] b = conjunto[1].split("");

    int num1 =
            Integer.parseInt(celda1[letras(b[0])][Integer.parseInt(b[1]) - 1]);
    int dev1 =
            Integer.parseInt(celda2[letras(b[0])][Integer.parseInt(b[1]) - 1]);

    for (int i = 2; i < conjunto.length; i++) {

      String[] c = conjunto[i].split("");
      int num2 =
          Integer.parseInt(celda1[letras(c[0])][Integer.parseInt(c[1]) - 1]);
      int dev2 =
          Integer.parseInt(celda2[letras(c[0])][Integer.parseInt(c[1]) - 1]);

      //si los denominadores son iguales solo se suman los numeradores.
      if (dev1 == dev2) {

        sumanum = num1 + num2;
        sumadev = dev1;

        num1 = sumanum;
        dev1 = sumadev;
      }

      // si los denominadores son diferentes cambia el método.
      if (dev1 != dev2) {

        //si los denominadores tienen mínimo común divisor.
        if (dev1 % dev2 == 0) {
          int tmp = dev1 / dev2;
          sumanum = num1 + num2 * tmp;
          sumadev = dev1;

          num1 = sumanum;
          dev1 = sumadev;
        } else {
          if (dev2 % dev1 == 0) {
            int tmp = dev2 / dev1;
            sumanum = num1 + num2 * tmp;
            sumadev = dev2;

            num1 = sumanum;
            dev1 = sumadev;
          } else {

            // si no tienen mcd se hace el método tradicional.
            int tmp = dev1;
            dev1 = dev2 * dev1;
            num1 = num1 * dev2;
            num2 = num2 * tmp;

            sumanum = num1 + num2;
            sumadev = dev1;

            num1 = sumanum;
            dev1 = sumadev;
          }
        }
      }
    }

    //los resultados de las sumas se guardan.
    suma1 = sumanum;
    suma2 = sumadev;

    //se ponen en sus respectivas celdas.
    celda1[celRow][celCol] = "" + suma1;
    celda2[celRow][celCol] = "" + suma2;

  }

  //----------------------------------------------------------------------------

  /**
   * Este método multiplica las fracciones
   * de las posiciones dadas (filas o columnas).
   *
   * @param casillas Las casillas que solicitan.
   */
  void mult(final String casillas) {

    int multiplicacion1 = 0;
    int multiplicacion2 = 0;

    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");
    int mult = 1;

    //si las letras son iguales se trabajan columnas
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);

      int columna = letras(casilla1[0]);

      //multiplica los numeradores.
      for (int i = inicio - 1; i <= fin - 1; i++) {
        int num = Integer.parseInt(celda1[i][columna]);
        mult *= num;

      }
      multiplicacion1 = mult;

      mult = 1;

      //multiplica los denominadores.
      for (int i = inicio - 1; i <= fin - 1; i++) {
        int num = Integer.parseInt(celda2[i][columna]);
        mult *= num;

      }

      multiplicacion2 = mult;

      //Se guardan los valores en sus respectivas celdas.
      celda1[celRow][celCol] = "" + multiplicacion1;
      celda2[celRow][celCol] = "" + multiplicacion2;

    }
    //si los números son iguales se trabajan filas
    if (casilla2[1].equals(casilla1[1])) {

      int fila = Integer.parseInt(casilla1[1]);

      int inicio = letras(casilla1[0]);

      int fin = letras(casilla2[0]);

      //multiplica los numeradores.
      for (int i = inicio; i <= fin; i++) {
        int num = Integer.parseInt(celda1[fila - 1][i]);
        mult *= num;
      }

      multiplicacion1 = mult;

      mult = 1;

      //multiplica los denominadores.
      for (int i = inicio; i <= fin; i++) {
        int num = Integer.parseInt(celda2[fila - 1][i]);
        mult *= num;
      }

      multiplicacion2 = mult;

      //Se guardan los valores en sus respectivas celdas.
      celda1[celRow][celCol] = "" + multiplicacion1;
      celda2[celRow][celCol] = "" + multiplicacion2;

    }

  }

  //----------------------------------------------------------------------------

  /**
   * multiplica las casillas del conjunto dado.
   */
  void multConjunto() {

    String nombre = conjunto[0];

    int multiplicacion1 = 1;
    int multiplicacion2 = 1;
    int multnum = 1;
    int multdev = 1;

    String[] b = conjunto[1].split("");

    int num1 =
            Integer.parseInt(celda1[letras(b[0])][Integer.parseInt(b[1]) - 1]);
    int dev1 =
            Integer.parseInt(celda2[letras(b[0])][Integer.parseInt(b[1]) - 1]);

    //multiplica los numeradores.
    for (int i = 2; i < conjunto.length; i++) {
      String[] c = conjunto[i].split("");
      int num2 =
             Integer.parseInt(celda1[letras(c[0])][Integer.parseInt(c[1]) - 1]);
      int dev2 =
             Integer.parseInt(celda2[letras(c[0])][Integer.parseInt(c[1]) - 1]);

      multnum = num1 * num2;
      multdev = dev1 * dev2;

      num1 = multnum;
      dev1 = multdev;

    }

    multiplicacion1 = multnum;

    multiplicacion2 = multdev;
    //Se guardan los valores en sus respectivas celdas.
    celda1[celRow][celCol] = "" + multiplicacion1;
    celda2[celRow][celCol] = "" + multiplicacion2;

  }

  //----------------------------------------------------------------------------

  /**
   * Este método saca el promedio de las celdas dadas (filas o columnas).
   *
   * @param casillas Las casillas que solicitan.
   */

  void promedio(final String casillas) {

    //hace las sumas de las casillas.
    suma(casillas);

    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");

    //si los números son iguales se trabajan columnas
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);

      //la cantidad de fracciones
      int div = (fin - inicio) + 1;

      int promedio1 = suma1;
      int promedio2 = suma2 * div;

      celda1[celRow][celCol] = "" + promedio1;
      celda2[celRow][celCol] = "" + promedio2;

    }
    //si los números son iguales se trabajan filas
    if (casilla2[1].equals(casilla1[1])) {

      int inicio = letras(casilla1[0]);
      int fin = letras(casilla2[0]);

      //la cantidad de fracciones
      int div = (fin - inicio) + 1;

      int promedio1 = suma1;
      int promedio2 = suma2 * div;

      celda1[celRow][celCol] = "" + promedio1;
      celda2[celRow][celCol] = "" + promedio2;

    }
  }

  //----------------------------------------------------------------------------

  /**
   * Este método calcula la mediana pedida (filas y columnas).
   *
   * @param casillas Las casillas que solicitan.
   */

  void mediana(final String casillas) {

    int mediana1 = 0;
    int mediana2 = 0;

    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");

    //si los números son iguales se trabajan columnas.
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);

      int columna = letras(casilla1[0]);

      int div = (fin - inicio) + 1;
      int num = 0;
      int dev = 0;
      int num2 = 0;
      int dev2 = 0;

      if (div % 2 == 0) {
        //si la cantidad es par se sacan los 2 del medio y se dividen entre 2.
        for (int i = inicio; i <= div; i++) {

          num = Integer.parseInt(celda1[i][columna]);
          dev = Integer.parseInt(celda2[i][columna]);

        }

        for (int i = inicio; i <= div + 1; i++) {

          num2 = Integer.parseInt(celda1[i][columna]);
          dev2 = Integer.parseInt(celda2[i][columna]);

        }

        int numMed = num + num2;
        int devMed = (dev + dev2) * 2;

        celda1[celRow][celCol] = "" + numMed;
        celda2[celRow][celCol] = "" + devMed;

      } else {

        //si la cantidad de celdas es impar es la del medio.
        for (int i = inicio; i <= div; i++) {

          num = Integer.parseInt(celda1[i][columna]);
          dev = Integer.parseInt(celda2[i][columna]);

        }

        celda1[celRow][celCol] = "" + num;
        celda2[celRow][celCol] = "" + dev;

      }

    }
    //si los números son iguales se trabajan filas.
    if (casilla2[1].equals(casilla1[1])) {

      int inicio = letras(casilla1[0]);
      int fin = letras(casilla2[0]);

      int fila = Integer.parseInt(casilla1[1]);

      int div = (fin - inicio) + 1;
      int num = 0;
      int dev = 0;
      int num2 = 0;
      int dev2 = 0;

      if (div % 2 == 0) {

        //si la cantidad es par se sacan los 2 del medio y se dividen entre 2.
        for (int i = inicio; i <= div; i++) {

          num = Integer.parseInt(celda1[fila - 1][i]);
          dev = Integer.parseInt(celda2[fila - 1][i]);

        }

        for (int i = inicio; i <= div + 1; i++) {

          num2 = Integer.parseInt(celda1[fila - 1][i]);
          dev2 = Integer.parseInt(celda2[fila - 1][i]);

        }

        int numMed = num + num2;
        int devMed = dev + dev2 * 2;


        celda1[celRow][celCol] = "" + numMed;
        celda2[celRow][celCol] = "" + devMed;

      } else {

        //si la cantidad de celdas es impar es la del medio.
        for (int i = inicio; i <= div; i++) {

          num = Integer.parseInt(celda1[fila - 1][i]);
          dev = Integer.parseInt(celda2[fila - 1][i]);

        }

        celda1[celRow][celCol] = "" + num;
        celda2[celRow][celCol] = "" + dev;

      }
    }
  }

  //----------------------------------------------------------------------------

  /**
   * Este método saca el número mas pequeño
   * de las celdas seleccionadas (filas o columnas).
   *
   * @param casillas Las casillas que solicitan.
   */

  void min(final String casillas) {

    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");

    //si las letras son iguales se trabajan columnas
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);

      int columna = letras(casilla1[0]);

      double minimo = 0;

      int div1 = Integer.parseInt(celda1[0][columna]);
      int num2 = Integer.parseInt(celda2[0][columna]);

      double min = div1 / num2;

      // si el resultado de la fracción es
      // menor que otras se convierte en la menor.
      for (int i = inicio - 1; i <= fin - 1; i++) {

        int div = Integer.parseInt(celda1[i][columna]);
        int num = Integer.parseInt(celda2[i][columna]);

        double min2 = div / num;

        if (min2 < min) {
          minimo = min2;
          min = min2;
        }

      }

      //busca el resultado menor y lo compara
      // con las fracciones para encontrar la posición.
      for (int i = inicio - 1; i <= fin - 1; i++) {

        int div = Integer.parseInt(celda1[i][columna]);
        int num = Integer.parseInt(celda2[i][columna]);
        if (div / num == minimo) {
          celda1[celRow][celCol] = "" + div;
          celda2[celRow][celCol] = "" + num;

        }

      }

    }
    //si los números son iguales se trabajan filas
    if (casilla2[1].equals(casilla1[1])) {

      int fila = Integer.parseInt(casilla1[1]);

      int inicio = letras(casilla1[0]);
      int fin = letras(casilla2[0]);

      double minimo = 0;

      int div1 = Integer.parseInt(celda1[fila - 1][0]);
      int num2 = Integer.parseInt(celda2[fila - 1][0]);
      double min = div1 / num2;

      // si el resultado de la fracción es
      // menor que otras se convierte en la menor.
      for (int i = inicio; i <= fin; i++) {

        int div = Integer.parseInt(celda1[fila - 1][i]);
        int num = Integer.parseInt(celda2[fila - 1][i]);

        double min2 = div / num;

        if (min2 < min) {
          minimo = min2;
          min = min2;
        }
      }

      //busca el resultado menor y lo compara
      // con las fracciones para encontrar la posición.
      for (int i = inicio; i <= fin; i++) {

        int div = Integer.parseInt(celda1[fila - 1][i]);
        int num = Integer.parseInt(celda2[fila - 1][i]);

        if (div / num == minimo) {
          celda1[celRow][celCol] = "" + div;
          celda2[celRow][celCol] = "" + num;

        }
      }
    }
  }

  //----------------------------------------------------------------------------

  /**
   * Este método saca el numero mas grande
   * de las celdas seleccionadas (filas o columnas).
   *
   * @param casillas Las casillas que solicitan.
   */

  void max(final String casillas) {
    String[] a = casillas.split(":");
    String[] casilla1 = a[0].split("");
    String[] casilla2 = a[1].split("");


    //si las letras son iguales se trabajan columnas
    if (casilla2[0].equals(casilla1[0])) {

      int inicio = Integer.parseInt(casilla1[1]);
      int fin = Integer.parseInt(casilla2[1]);

      int columna = letras(casilla1[0]);

      double maximo = 0;

      int div1 = Integer.parseInt(celda1[0][columna]);
      int num2 = Integer.parseInt(celda2[0][columna]);
      double max = div1 / num2;

      // si el resultado de la fracción es
      // mayor que otras se convierte en la mayor.
      for (int i = inicio - 1; i <= fin - 1; i++) {

        int div = Integer.parseInt(celda1[i][columna]);
        int num = Integer.parseInt(celda2[i][columna]);

        double max2 = div / num;

        if (max2 > max) {
          maximo = max2;
          max = max2;
        }
      }

      //busca el resultado mayor y lo compara
      // con las fracciones para encontrar la posición.
      for (int i = inicio - 1; i <= fin - 1; i++) {

        int div = Integer.parseInt(celda1[i][columna]);
        int num = Integer.parseInt(celda2[i][columna]);
        if (div / num == maximo) {

          celda1[celRow][celCol] = "" + div;
          celda2[celRow][celCol] = "" + num;

        }
      }
    }
    //si los números son iguales se trabajan filas
    if (casilla2[1].equals(casilla1[1])) {

      int fila = Integer.parseInt(casilla1[1]);

      int inicio = letras(casilla1[0]);
      int fin = letras(casilla2[0]);

      double maximo = 0;

      int div1 = Integer.parseInt(celda1[fila - 1][0]);
      int num2 = Integer.parseInt(celda2[fila - 1][0]);
      double max = div1 / num2;

      // si el resultado de la fracción es
      // mayor que otras se convierte en la mayor.
      for (int i = inicio; i <= fin; i++) {

        int div = Integer.parseInt(celda1[fila - 1][i]);
        int num = Integer.parseInt(celda2[fila - 1][i]);

        double max2 = div / num;

        if (max2 > max) {

          maximo = max2;
          max = max2;
        }

      }

      //busca el resultado mayor y lo compara
      // con las fracciones para encontrar la posición.
      for (int i = inicio; i <= fin; i++) {

        int div = Integer.parseInt(celda1[fila - 1][i]);
        int num = Integer.parseInt(celda2[fila - 1][i]);

        double max2 = div / num;

        if (max2 == maximo) {

          celda1[celRow][celCol] = "" + div;
          celda2[celRow][celCol] = "" + num;

        }
      }
    }
  }

  //----------------------------------------------------------------------------

  /**
   * imprime las celdas.
   */
  void imprimir() {

    simplificar();

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        int num = Integer.parseInt(celda1[i][j]);
        int dev = Integer.parseInt(celda2[i][j]);
        hojaDeCalculo[i][j] = num + "/" + dev + " ";
      }
    }

    int distancia = 0;

    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {

        String a = hojaDeCalculo[i][j];
        if (a.length() > distancia) {
          distancia = a.length();
        }

      }
    }

    System.out.println(distancia);

    System.out.print("   |");

    for (int i = 0; i < col; i++) {

      for (int j = 0; j < distancia - 1; j++) {
        System.out.print(" ");
      }
      System.out.print(num(i));
      System.out.print(" ");
    }

    System.out.println();
    System.out.print("---+");

    for (int i = 0; i < col; i++) {

      for (int j = 0; j < distancia; j++) {
        System.out.print("-");
      }
      System.out.print(" ");
    }

    System.out.println();


    for (int i = 0; i < row; i++) {
      System.out.print("  " + (i + 1) + "|");
      for (int j = 0; j < col; j++) {
        System.out.print(" ");

        int dis = distancia - (hojaDeCalculo[i][j].length());
        for (int k = 0; k < dis; k++) {
          System.out.print(" ");

        }
        System.out.print(hojaDeCalculo[i][j]);
      }
      System.out.println(" ");
    }

    System.out.println();

  }

  //----------------------------------------------------------------------------

  /**
   * Imprime un conjunto.
   */

  void imprimirConjunto() {

    System.out.print(conjunto[0] + " ->");
    for (int i = 1; i < conjunto.length; i++) {

      String[] b = conjunto[i].split("");
      int num = letras(b[0]);
      int dev = Integer.parseInt(b[1]) - 1;

      System.out.print(" " + celda1[num][dev] + "/" + celda2[num][dev]);

    }
    System.out.println();
  }

  //----------------------------------------------------------------------------

  /**
   * Este método simplifica las fracciones.
   */

  void simplificar() {

    for (int i = 0; i < hojaDeCalculo.length; i++) {
      for (int j = 0; j < hojaDeCalculo[i].length; j++) {

        int num = Integer.parseInt(celda1[i][j]);
        int dev = Integer.parseInt(celda2[i][j]);

        if (num == 0) {
          celda1[i][j] = "" + num;
          celda2[i][j] = "" + dev;
        } else {

          if (num == dev) {

            celda1[i][j] = "1";
            celda2[i][j] = "1";
          } else {

            if (num > dev) {
              int min = dev;
              for (int a = min; a >= 2; a--) {

                if (num % a == 0 && dev % a == 0) {

                  num = num / a;
                  dev = dev / a;

                  celda1[i][j] = "" + num;
                  celda2[i][j] = "" + dev;
                }
              }
            }

            if (num < dev) {
              int min = num;

              for (int a = min; a >= 2; a--) {

                if (num % a == 0 && dev % a == 0) {

                  num = num / a;
                  dev = dev / a;

                  celda1[i][j] = "" + num;
                  celda2[i][j] = "" + dev;
                }
              }
            }
          }
        }
      }
    }
  }

  //----------------------------------------------------------------------------

  /**
   * Este método convierte las letras
   * de las posiciones dadas en la posición respectiva.
   *
   * @param valor es la letra de la posición.
   * @return la posición de la letra dada.
   */
  int letras(final String valor) {
    int x = 0;
    // depende de la letra asigna un valor
    if (valor.equals("A")) {
      x = 0;
      return x;
    }
    if (valor.equals("B")) {
      x = 1;
      return x;
    }
    if (valor.equals("C")) {
      x = 2;
      return x;
    }
    if (valor.equals("D")) {
      x = 3;
      return x;
    }
    if (valor.equals("E")) {
      x = 4;
      return x;
    }
    if (valor.equals("F")) {
      x = 5;
      return x;
    }
    if (valor.equals("G")) {
      x = 6;
      return x;
    }
    if (valor.equals("H")) {
      x = 7;
      return x;
    }
    if (valor.equals("I")) {
      x = 8;
      return x;
    }
    if (valor.equals("J")) {
      x = 9;
      return x;
    }
    if (valor.equals("K")) {
      x = 10;
      return x;
    }
    if (valor.equals("L")) {
      x = 11;
      return x;
    }
    if (valor.equals("M")) {
      x = 12;
      return x;
    }
    if (valor.equals("N")) {
      x = 13;
      return x;
    }
    if (valor.equals("O")) {
      x = 14;
      return x;
    }
    if (valor.equals("P")) {
      x = 15;
      return x;
    }
    if (valor.equals("Q")) {
      x = 16;
      return x;
    }
    if (valor.equals("R")) {
      x = 17;
      return x;
    }
    if (valor.equals("S")) {
      x = 18;
      return x;
    }
    if (valor.equals("T")) {
      x = 19;
      return x;
    }
    if (valor.equals("U")) {
      x = 20;
      return x;
    }
    if (valor.equals("V")) {
      x = 21;
      return x;
    }
    if (valor.equals("W")) {
      x = 22;
      return x;
    }
    if (valor.equals("X")) {
      x = 23;
      return x;
    }
    if (valor.equals("Y")) {
      x = 24;
      return x;
    }
    if (valor.equals("Z")) {
      x = 25;
      return x;
    } else {
      return x;
    }
  }

  //----------------------------------------------------------------------------
  
  /**
   * Metodo para imprimir bien el formato.
   *
   * @param valor El numero de la letra
   *
   * @return la letra correspondiente
   */

  String num(final int valor) {

    // depende de la letra asigna un valor
    if (valor == 0) {
      return "A";
    }
    if (valor == 1) {
      return "B";
    }
    if (valor == 2) {
      return "C";
    }
    if (valor == 3) {
      return "D";
    }
    if (valor == 4) {
      return "E";
    }
    if (valor == 5) {
      return "F";
    }
    if (valor == 6) {
      return "G";
    }
    if (valor == 7) {
      return "H";
    }
    if (valor == 8) {
      return "I";
    }
    if (valor == 9) {
      return "J";
    }
    if (valor == 10) {
      return "K";
    }
    if (valor == 11) {
      return "L";
    }
    if (valor == 12) {
      return "M";
    }
    if (valor == 13) {
      return "N";
    }
    if (valor == 14) {
      return "O";
    }
    if (valor == 15) {
      return "P";
    }
    if (valor == 16) {
      return "Q";
    }
    if (valor == 17) {
      return "R";
    }
    if (valor == 18) {
      return "S";
    }
    if (valor == 19) {
      return "T";
    }
    if (valor == 20) {
      return "U";
    }
    if (valor == 21) {
      return "V";
    }
    if (valor == 22) {
      return "W";
    }
    if (valor == 23) {
      return "X";
    }
    if (valor == 24) {
      return "Y";
    }
    if (valor == 25) {
      return "Z";
    } else {
      return "";
    }
  }

  //----------------------------------------------------------------------------

}

