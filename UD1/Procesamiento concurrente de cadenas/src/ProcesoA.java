import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class ProcesoA {
    public static void main(String[] args) {
        OutputStream output = System.out;
        PrintWriter writer = new PrintWriter(output, true);

        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            int longitud = 5 + random.nextInt(11);
            StringBuilder cadena = new StringBuilder();

            for (int j = 0; j < longitud; j++) {
                int indice = random.nextInt(alfabeto.length());
                cadena.append(alfabeto.charAt(indice));
            }

            writer.println(cadena.toString());
        }
        writer.close();
    }
}