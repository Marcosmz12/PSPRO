
/**
 * @author 2º DAM-T
 * @version 1.0
 * @since 2025-10-23
 * 
 * Licencia: GNU General Public License v3.0 (GPL-3.0)
 * @see https://www.gnu.org/licenses/gpl-3.0.html
 */

import java.io.*;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que implementa un proceso jugador que realiza operaciones de compra y
 * venta de artículos.
 * 
 * @param args Número de jugador y nombre (con o sin ruta) del archivo con el
 *             inventario que se empleará en los accesos compartidos.
 */
public class Jugador {

    public static void main(String[] args) {

        final int NUMERO_OPERACIONES = 20; // Número de operaciones de compra/venta a realizar por jugador

        int númeroJugador = 0;
        String nombreFichero;

        // Comprobamos si estamos recibiendo argumentos en la línea de comandos
        if (args.length != 2) {
            System.err.println("Jugador " + númeroJugador + ": Número incorrecto de argumentos.");
            System.exit(-1);
        }
        // Tratamos de conseguir el número de jugador del primer argumento
        try {
            númeroJugador = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Jugador " + númeroJugador + ": El primer argumento debe ser un número entero.");
            System.exit(-1);
        }
        // Obtenemos el nombre del fichero con el inventario del segundo argumento
        nombreFichero = Paths.get(args[1]).normalize().toString();

        // Comienza el juego
        for (int i = 0; i < NUMERO_OPERACIONES; i++) {
            // Esperamos un tiempo aleatorio, para poder ver la evolución del juego
            try {

                Random random = new Random();
                Thread.sleep(random.nextInt(2500 - 500 + 1) + 500);
            } catch (Exception e) {
                System.err.println(
                        "Jugador " + númeroJugador
                                + ": Se produjo un error simulando una espera antes de la siguiente operación.");
            }

            try {
                RandomAccessFile inventario = new RandomAccessFile(nombreFichero, "rw");

                // Bloquea el archivo con el inventario para evitar acceso concurrente no
                // sincronizado
                FileLock bloqueo = null;
                bloqueo = inventario.getChannel().lock();
                // Elige aleatoriamente qué acción realizará el jugador (comprar o vender
                // un artículo)
                // Elige aleatoriamente el ID del artículo sobre el que realizará la
                // acción (1, 2 ó 3)
                // Realiza la acción correspondiente (compra o venta) y guarda el mensaje
                // resultante
                Random random = new Random();
                List<String> mensaje = new ArrayList<>();
                for (int j = 0; j < númeroJugador; j++) {
                    boolean accionComprar = random.nextBoolean();
                    int idArticulo = random.nextInt(4 - 1) + 1;

                    String resultado;
                    if (accionComprar) {
                        resultado = comprarArtículo(inventario, idArticulo);
                    } else {
                        resultado = venderArtículo(inventario, idArticulo);
                    }

                    mensaje.add("Jugador " + (j + 1) + ": " + resultado);

                }

                // Imprime el mensaje sobre la transacción por consola
                for (String m : mensaje) {
                    System.out.println(m);
                }
                // Libera el bloqueo del archivo con el inventario
                bloqueo.release();
                bloqueo = null;
                // Cierra el fichero con el inventario
                inventario.close();

            } catch (Exception e) {
                System.err
                        .println("Jugador " + númeroJugador
                                + ": No se pudo acceder al fichero con el inventario.");
            }
        }

    }

    private static String comprarArtículo(RandomAccessFile inventario, int idArtículo) throws IOException {
        String línea;
        inventario.seek(0); // Volver al principio del archivo

        // Buscar artículo y verificar si está disponible
        while ((línea = inventario.readLine()) != null) {

            if (línea.contains("Articulo " + idArtículo)) {

                int index = línea.indexOf(":");
                String inicioCadena = línea.substring(0, index + 2);
                int stock = Integer.parseInt(línea.substring(index + 2, línea.indexOf(" unidades")).trim());
                String finalCadena = línea.substring(línea.indexOf(" unidades"), línea.length());

                if (stock > 0) {
                    // Actualizar cantidad disponible
                    int nuevoStock = stock - 1;
                    inventario.seek(inventario.getFilePointer() - línea.length() - 1);
                    // Contemplamos stocks de tres dígitos como máximo
                    String nuevaLínea = String.format("%s%3d%s\n", inicioCadena, nuevoStock, finalCadena);
                    inventario.writeBytes(nuevaLínea);
                    return "Compró artículo " + idArtículo + ". Quedan " + nuevoStock + " unidades.";

                } else {
                    return "No hay suficientes unidades del artículo " + idArtículo + " para comprar.";
                }
            }
        }
        return "Artículo " + idArtículo + " no encontrado.";
    }

    private static String venderArtículo(RandomAccessFile inventario, int idArtículo) throws IOException {
        String línea;
        inventario.seek(0); // Volver al principio del archivo

        // Buscar artículo y actualizar cantidad
        while ((línea = inventario.readLine()) != null) {

            if (línea.contains("Articulo " + idArtículo)) {

                int index = línea.indexOf(":");
                String inicioCadena = línea.substring(0, index + 2);
                int stock = Integer.parseInt(línea.substring(index + 2, línea.indexOf(" unidades")).trim());
                String finalCadena = línea.substring(línea.indexOf(" unidades"), línea.length());

                // Incrementar cantidad disponible
                int nuevoStock = stock + 1;
                inventario.seek(inventario.getFilePointer() - línea.length() - 1);
                // Contemplamos stocks de tres dígitos como máximo
                String nuevaLínea = String.format("%s%3d%s\n", inicioCadena, nuevoStock, finalCadena);
                inventario.writeBytes(nuevaLínea);
                return "Vendió artículo " + idArtículo + ". Ahora hay " + nuevoStock + " unidades.";
            }
        }
        return "Artículo " + idArtículo + " no encontrado.";
    }
}
