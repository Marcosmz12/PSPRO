import java.util.Random;
import java.util.concurrent.Semaphore;

public class PersonaEntrante extends Thread {

  private RecursoJardin jardin;
  private Semaphore semáforo;

  public PersonaEntrante(String nombre, RecursoJardin j, Semaphore semáforo) {
    this.setName(nombre);
    this.jardin = j;
    this.semáforo = semáforo;
  }

  @Override
  public void run() {
    try {
      // Establecemos una espera aleatoria para lograr que se mezclen hilos entrantes y salientes
      Random espera = new Random();
      Thread.sleep(espera.nextInt(501) + 500);
      // En cada acceso se adquiere el recurso y si está ocupado se bloquea
      semáforo.acquire();
      // Invoca al método que incrementa la cuenta de accesos al jardín
      jardin.incrementaCuenta();
      // Libera el recurso o permiso
      semáforo.release();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
