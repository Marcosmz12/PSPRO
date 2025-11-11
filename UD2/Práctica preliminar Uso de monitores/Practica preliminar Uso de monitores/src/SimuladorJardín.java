import java.util.concurrent.Semaphore;

public class SimuladorJardín {

  public static void main(String[] args) {
    Semaphore semáforo = new Semaphore(1);
    // Crea un objeto RecursoJardín
    RecursoJardin jardin = new RecursoJardin();

    // Entrada de 10 hilos al jardín
    for (int i = 1; i <= 10; i++) {
      (new PersonaEntrante("Persona entrante " + i, jardin, semáforo)).start();
    }

    // Salida de 15 hilos del jardín
    for (int i = 1; i <= 15; i++) {
      (new PersonaSaliente("Persona saliente " + i, jardin, semáforo)).start();
    }
  }
}
