class MiHilo extends Thread {
    @Override
    public void run() {
        // Código que se ejecuta en el hilo
        System.out.println("El hilo está corriendo");
    }
}

public class Ejemplo {
    public static void main(String[] args) {
        // Crear y empezar el hilo
        MiHilo hilo = new MiHilo();
        hilo.start(); // `start()` comienza la ejecución del hilo, invocando `run()`
    }
}
