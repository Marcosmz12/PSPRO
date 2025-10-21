import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesoC {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String linea;
            int totalVocales = 0;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length != 2) {
                    continue;
                }

                String cadena = partes[0];
                int contadorVocales = 0;
                String vocales = "aeiou";

                for (char c : cadena.toCharArray()) {
                    if (vocales.indexOf(c) != -1) {
                        contadorVocales++;
                    }
                }
                System.out.println("Cadena: " + cadena + " | Vocales: " + contadorVocales);
                totalVocales += contadorVocales;
            }
            System.out.println("\nTotal de vocales procesadas: " + totalVocales);

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
