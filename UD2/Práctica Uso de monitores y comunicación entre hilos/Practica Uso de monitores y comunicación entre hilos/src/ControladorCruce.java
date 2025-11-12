import java.util.concurrent.Semaphore;

class ControladorCruce {

  private final Semaphore semaphore = new Semaphore(1);
  private int cochesEnA = 0;
  private int cochesEnB = 0;
  private boolean esCalleA = Math.random() < 0.5; // Calle que empieza primero es aleatoria

  public void solicitaCruzar(String calle) throws InterruptedException {
    if (calle.equals("A")) {
      cochesEnA++;
      while (!esCalleA && cochesEnB > 0) {
        semaphore.acquire(); // Esperar si no es la calle A o hay coches en B
        semaphore.release();
      }
      semaphore.acquire(); // Entrar a la sección crítica
      System.out.print(
        Thread.currentThread().getName() + " de la calle A está cruzando."
      );
      // Simular el cruce
      for (int i = 0; i < 5; i++) {
        Thread.sleep(200);
        System.out.print(".");
      }
      System.out.println(
        Thread.currentThread().getName() + " de la calle A ha cruzado."
      );
      cochesEnA--;
      if (cochesEnA == 0) {
        esCalleA = false; // Cambiar a calle B si no hay coches en A
      }
      semaphore.release(); // Salir de la sección crítica
    } else {
      cochesEnB++;
      while (esCalleA && cochesEnA > 0) {
        semaphore.acquire(); // Esperar si es la calle A o hay coches en A
        semaphore.release();
      }
      semaphore.acquire(); // Entrar a la sección crítica
      System.out.print(
        Thread.currentThread().getName() + " de la calle B está cruzando."
      );
      // Simular el cruce
      for (int i = 0; i < 5; i++) {
        Thread.sleep(200);
        System.out.print(".");
      }
      System.out.println(
        Thread.currentThread().getName() + " de la calle B ha cruzado."
      );
      cochesEnB--;
      if (cochesEnB == 0) {
        esCalleA = true; // Cambiar a calle A si no hay coches en B
      }
      semaphore.release(); // Salir de la sección crítica
    }
  }
}
