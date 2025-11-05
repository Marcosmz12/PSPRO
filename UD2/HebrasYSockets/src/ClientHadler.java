import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true);) {
            // Enviar un mensaje de bienvenida al cliente
            writer.println("Bienvenido al servidor");

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Mensaje del cliente: " + clientMessage);

                // Enviar una respuesta al cliente
                writer.println("Servidor recibió: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("salir")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
