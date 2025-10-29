import java.io.IOException;

public class ProcesoD {
    public static void main(String[] args) {
        if (args.length !=1) {
            System.out.println("Uso: java ProcesoD <número_de_procesos>");
            return;
        }
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("El argumento debe ser un número.");
            return;
        }
        if (n <= 0 || n >= 10) {
            System.out.println("El número de procesos debe estar entre 1 y 9.");
            return;
        }

        for (int i = 0; i < n; i++) {
            try {
                System.out.println("Ejecución número " + (i+1));
                String comando = "cmd /c start cmd /k \"java ProcesoA.java | java ProcesoB.java | java ProcesoC.java";
                String[] comandos = comando.split(" ");
                Runtime.getRuntime().exec(comandos);
            } catch (Exception e) {
                System.out.println("Error al ejecutar los procesos: " + e.getMessage());
            }
        }
    }}