
/**
 * `CompraVenta` es una aplicación donde varios jugadores (procesos) compiten por 
 * comprar y vender artículos almacenados en un archivo compartido. Los artículos tienen 
 * un precio fijo y los jugadores pueden comprar o vender estos artículos, modificando 
 * la cantidad disponible de cada uno en el archivo.
 * 
 * @author 2º DAM-T
 * @version 1.0
 * @since 2025-10-23
 * 
 * Licencia: GNU General Public License v3.0 (GPL-3.0)
 * @see https://www.gnu.org/licenses/gpl-3.0.html
 */

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

/**
 * Clase principal que crea un fichero con un inventario y lanza varios procesos
 * que acceden concurrentemente al mismo.
 * 
 * @param args Ruta y nombre del archivo que se empleará en los accesos
 *             compartidos (opcional)
 */
public class CompraVenta {

  public static void main(String[] args) {

    final int NUMERO_JUGADORES = 5; // Número de procesos jugadores a crear
    final String INVENTARIO = "inventario.txt"; // Nombre por defecto del fichero con el inventario
    String nombreFichero;

    // Averiguamos el nombre y la ruta del fichero a utilizar como inventario
    // inicial
    switch (args.length) {
      case 0:
        System.out
            .println("CompraVenta: Se utilizará el nombre por defecto para el fichero con el inventario inicial.");
        nombreFichero = INVENTARIO;
        break;
      case 1:
        nombreFichero = Paths.get(args[0]).normalize().toString();
        System.out
            .println("CompraVenta: Se utilizará el fichero " + nombreFichero + " como inventario inicial.");
        break;
      default:
        System.err.println(
            "CompraVenta: Demasiados argumentos. Se utilizará el nombre por defecto para el fichero con el inventario inicial.");
        nombreFichero = INVENTARIO;
        break;
    }

    // Preparamos e inicializamos el fichero compartido con el inventario
    inicializarFichero(nombreFichero);

    // Creamos un grupo de procesos jugadores que accederán al mismo inventario
    try {

      // Crear un bucle para lanzar los procesos jugadores (tantas veces como
      // indique NUMERO_JUGADORES)
      // Cada proceso se lanzará con el comando: "cmd /c start cmd /K \"java
      // Jugador.java ... ... \""
      // Para lanzar los procesos usamos el método Runtime.getRuntime.exec(...)
      // Imprime por consola un mensaje indicando que se ha creado el proceso
      // jugador (y su número)
      for (int i = 0; i < NUMERO_JUGADORES; i++) {
        System.out.println("JUGADOR: " + (i + 1));
        String comando = "cmd /c start cmd /K \"java Jugador.java" + " " + NUMERO_JUGADORES + " " + INVENTARIO + "\"";
        String[] comandos = comando.split(" ");
        Runtime.getRuntime().exec(comandos);
      }

    } catch (Exception ex) {
      System.err.println("CompraVenta: No se pudo crear un proceso jugador.");
    }
  }

  /**
   * Inicializa, con valores de ejemplo, el fichero que se compartirá
   * 
   * @param rutaFichero Ruta al fichero
   */
  private static void inicializarFichero(String nombreFichero) {

    File archivo = new File(nombreFichero);
    RandomAccessFile raf = null;

    if (!archivo.exists()) {
      try {
        archivo.createNewFile();
        raf = new RandomAccessFile(archivo, "rw");
        raf.setLength(0); // Limpiar el archivo
        // Artículos en stock, con cantidades y precios
        raf.writeBytes("Articulo 1:  10 unidades, precio 10.\n");
        raf.writeBytes("Articulo 2:   5 unidades, precio 20.\n");
        raf.writeBytes("Articulo 3:   8 unidades, precio 30.\n");
        System.out.println("CompraVenta: Creado el inventario inicial.");

      } catch (Exception e) {
        System.err.println("CompraVenta: Error al crear el inventario inicial.");

      } finally {

        try { // Nos aseguramos que se cierra el fichero.
          if (null != raf)
            raf.close();

        } catch (Exception e2) {
          System.err.println("CompraVenta: Error al cerrar el fichero con el inventario inicial.");
        }
      }
    }
  }
}