import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ProcesoB {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            OutputStream output = System.out;
            PrintWriter writer = new PrintWriter(output, true);

            String linea;
            while ((linea = reader.readLine()) != null) {
                int longitud = linea.length();

                String mensaje = linea + "," + longitud;

                writer.println(mensaje);
            }

            writer.flush();
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
