# Proyecto de la hoja de cálculo

# Análisis
La extensión del programa hoja de cálculo hace posible la opción de realizar operaciones con fracciones.
La extensión hace posibles las siguientes opciones:
1. Suma y multiplicación de filas y columnas desde una celda inicial hasta una celda final.
2. Visualización del valor máximo y mínimo de una fila o columna desde una celda inicial hasta
   una celda final.
3. Visualización del promedio y la mediana de una fila o columna desde una celda inicial hasta
   una celda final.
4. Creación subconjuntos formados por celdas de la hoja de cálculo.
5. Suma y multiplicación de subconjuntos.


# Guía de usuario
Para iniciar el programa se deben digitar primero dos números: el números de filas y después el número de columnas que
contiene la hoja de cálculo. Después de esto se deben introducir cada uno de los valores de la fracciones los cuales se
van a considerar como celdas individuales. Cada fracción debe estar representada por dos números enteros separados por
una barra inclinada (/).

Seguidamente de esto deberan ingresarse las operaciones y las celdas donde se desean guardar los resultados de dichas
operaciones. Las operaciones son representadas por los siguietes comandos:

| Comando                             |                                Descripción                                 |
|-------------------------------------|:--------------------------------------------------------------------------:|
| =CEL                                |                         Se posiciona en una celda.                         |
| =CONJUNTO(nombre, celda1,…,celda_n) |    Crea un conjunto con un nombre y añade las celdas que se le indican.    |
| =SUMA(celda1:celda2)                |          Suma una fila o columna desde la celda1 hasta la celda2.          |
| =SUMA(nombreConjunto)               |         Suma los valores de un conjunto con un nombre específico.          |
| =MULT(celda1:celda2)                |       Multiplica una fila o columna desde la celda1 hasta la celda2.       |
| =MULT(nombreConjunto)               |      Multiplica los valores de un conjunto con un nombre específico.       |
| =PROMEDIO(celda1:celda2)            | Obtiene el promedio de una fila o columna desde la celda1 hasta la celda2. |
| =MEDIANA(celda1:celda2)             | Obtiene la mediana de una fila o columna desde la celda1 hasta la celda2.  |
| =MIN(celda1:celda2)                 |    Muestra la fracción con el valor minímo entre las celdas indicadas.     |
| =MAX(celda1:celda2)                 |    Muestra la fracción con el valor máximo entre las celdas indicadas.     |
| =IMPRIMIR()                         |                      Imprime toda la hoja de cálculo.                      |
| =IMPRIMIR(nombreConjunto)           | Imprime un conjunto específico. Cada fracción va separada por un espacio.  |

Antes de escribir cada cada comando se debe colocar un símbolo de "mayor que" (>) para representar que se está
ingresando una nueva operación. Para almacenar correctamenta el resultado de una operación se debe indicar la celda en
la que se desea hacer esto, o en caso de que sea un conjunto se debe indicar el nombre de este.
Siguiendo estos pasos un ejemplo de entrada seria el siguiente:

~~~
4 4

01/01, 02/01, 03/01, 00/01
01/01, 01/02, 01/03, 00/01
24/01, 98/01, 34/01, 00/01
83/01, 64/01, 12/01, 00/01

>=CEL(D1)
>=SUMA(A1:C1)
>=CEL(D2)
>=MULT(A2:C2)
>=CEL(D3)
>=MIN(A3:C3)
>=CEL(D4)
>=MAX(A4:D4)
>=IMPRIMIR() 
~~~
Y recibiría la siguiente salida:
~~~
|             A              B              C              D              
---+-------------- -------------- -------------- -------------- 
  1|           1/1            2/1            3/1            6/1                 
  2|           1/1            1/2            1/3            1/6           
  3|          24/1           98/1           34/1           24/1                   
  4|          1/54           1/76           1/83           1/54                       
~~~


## Cómo crear el archivo .jar
Para poder generar un archivo .jar ejecutable, se debe abrir en el IDE TnteliJ el archivo build.gradle y colocar al
final del archivo los siguientes comandos:

~~~
jar}
   manifest}
      attributes "Main-Class": "cr.ac.ucr.TareaProgramada1"
      }
      
   from {
      configurtions.runtineClasspath.collect { file -> it.isirectory() ? it : zipTree(it) }
      }
  }      
jar{
    manifest{
        attributes "Main-Class": "cr.ac.ucr.Tarea1"
}

    from {
        configurations.runtimeClasspath.collect { File-> it.isDirectory() ? it : zipTree(it) }
    }
}      
~~~
Seguidamente depués de esto dentro de la carpeta "build" se generará la carpeta "libs" la cual contiene el archivo
ejecutable .jar.

## Cómo ejecutar el archivo .jar junto con los casos de prueba
Dentro de los archivos del programa viene incluida la carpeta "tests", la cual contiene varios ejemplos de entrada y salida
que se utilizan para comprobar que el programa funcione correctamente.
Para poder ejecutar los casos de prueba se debe abrir la linea de comandos y dirigirse al directorio en donde se encuentra
la caepeta "libs" , una vez ahí se deben introducir los siguientes comandos:

~~~
java -jar TareaProgramada1-1.0.jat < ../../tests/input.txt | diff - ../../tests/output000.txt && echo Test 000: OK
~~~
Se está solicitando que en caso de que la entrada de prueba de corresponda a la salida de prueba se imprima en la linea
de comandos el texto, y se obtendría el siguiente resultado:
~~~
Test 000: OK
~~~

## Diagrama de clases
https://drive.google.com/file/d/1DVsaw6DukhE8ma3Y_W4Zf84OYu_RHdzO/view?usp=sharing

# Créditos

Nombre: David Gonzalez Vilanueva

Contacto: david.gonzalezvillanueva@ucr.ac.cr

Carné: C13388

---

Nombre: Anthony Navarro Brenes

Contacto: edgar.navarrobrenes@ucr.ac.cr

Carné: C15479
