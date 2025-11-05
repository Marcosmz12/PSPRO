import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Para manejar hasta 10 hilos simultáneamente

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);

            while (true) {
                // Esperar a una conexión de un cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión aceptada de: " + clientSocket.getInetAddress());

                // Crear un nuevo hilo para manejar la conexión
                executor.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
