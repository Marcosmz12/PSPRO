import java.util.Random;
import java.util.concurrent.Semaphore;

public class TerminalClientes extends Thread {
    private ServidorWebs servidor;
    private  Semaphore semaforo;


  public TerminalClientes(String nombre, ServidorWebs servidor, Semaphore semaforo) {
    this.setName(nombre);
    this.servidor = servidor;
    this.semaforo = semaforo;
  }

  @Override
  public void run() {
    try {
      Random espera = new Random();
      for (int i = 1; i <= 10; i++) {
        Thread.sleep(espera.nextInt(501) + 500);
        semaforo.acquire();
        servidor.incrementaCuenta();
        semaforo.release();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
