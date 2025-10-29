import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcesoD {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("No se ha pasado ningun parametro");
            return;
        }

        int ejecuciones;
        try {
            ejecuciones = Integer.parseInt(args[0]);
            if (ejecuciones <= 0 || ejecuciones >= 10) {
                System.err.println("El número debe ser positivo y menor que 10.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("El argumento debe ser un número entero.");
            return;
        }

        for (int i = 0; i < ejecuciones; i++) {
            try {
                String orden = "cmd /c start cmd /k \"java ProcesoA.java | ProcesoB.java | ProcesoC.java";

                String[] split = orden.split(" ");
                Runtime.getRuntime().exec(split);

            } catch (IOException e) {
                System.err.println("Error al ejecutar los procesos: " + e.getMessage());
            }
        }
    }
}