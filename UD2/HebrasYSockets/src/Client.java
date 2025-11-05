import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true);
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            // Leer el mensaje de bienvenida del servidor
            String serverMessage = reader.readLine();
            System.out.println("Servidor: " + serverMessage);

            // Leer mensajes del usuario y enviarlos al servidor
            String userInput;
            while (true) {
                System.out.print("Escribe un mensaje al servidor: ");
                userInput = consoleReader.readLine();
                writer.println(userInput); // Enviar mensaje al servidor

                // Recibir y mostrar la respuesta del servidor
                String response = reader.readLine();
                System.out.println("Servidor: " + response);

                if (userInput.equalsIgnoreCase("salir")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
