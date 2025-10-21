import java.io.IOException;

public class EjecuciondeProcesos {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Debe indicar un número (1-9) como argumento.");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);
            if (n <= 0 || n >= 10) {
                System.err.println("El número debe ser mayor que 0 y menor que 10.");
                return;
            }
            for (int i = 0; i < n; i++) {
                String comando = "java ProcesoA | java ProcesoB | java ProcesoC";
                String[] cmdArray = { "bash", "-c", comando };
                Process proceso = Runtime.getRuntime().exec(cmdArray);
                System.out.println(">>> Lanzada ejecución paralela #" + (i + 1));
            }

        } catch (NumberFormatException e) {
            System.err.println("El argumento debe ser un número entero.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
