import java.util.concurrent.Semaphore;

class ControladorCruce {

  private final Semaphore cruce = new Semaphore(1);
  private int cochesEnA = 0;
  private int cochesEnB = 0;
  private boolean turnoCalleA = Math.random() < 0.5; // Calle que empieza primero es aleatoria

  public void solicitaCruzar(String calle) throws InterruptedException {
    if (calle.equals("A")) {
      cochesEnA++;      
      // Nos suspendemos (aunque nos despierten) si es el turno de la calle B y sigue habiendo coches en B
      while (!turnoCalleA && cochesEnB > 0) {
        cruce.acquire();
        cruce.release();
      }
      // Si hemos llegado aquí es porque es nuestro turno o porque no hay coches en la otra calle
      // Nos disponemos a cruzar
      cruce.acquire(); 
      System.out.print(
        Thread.currentThread().getName() + " de la calle A está cruzando."
      );
      // Simulamos que cruzar lleva un tiempo
      for (int i = 0; i < 5; i++) {
        Thread.sleep(200);
        System.out.print(".");
      }
      System.out.println(
        Thread.currentThread().getName() + " de la calle A ha cruzado."
      );
      // Hemos terminado de cruzar
      cochesEnA--;
      // Si fuimos el último, cambiamos el turno.
      if (cochesEnA == 0) turnoCalleA = false; 
      // Señalamos que el cruce quedó libre
      cruce.release();

    } else {
      cochesEnB++;
      // Nos suspendemos (aunque nos despierten) si es el turno de la calle A y sigue habiendo coches en A
      while (turnoCalleA && cochesEnA > 0) {
        cruce.acquire(); 
        cruce.release();
      }
      // Si hemos llegado aquí es porque es nuestro turno o porque no hay coches en la otra calle
      // Nos disponemos a cruzar
      cruce.acquire();
      System.out.print(
        Thread.currentThread().getName() + " de la calle B está cruzando."
      );
      // Simulamos que cruzar lleva un tiempo
      for (int i = 0; i < 5; i++) {
        Thread.sleep(200);
        System.out.print(".");
      }
      System.out.println(
        Thread.currentThread().getName() + " de la calle B ha cruzado."
      );      
      // Hemos terminado de cruzar
      cochesEnB--;      
      // Si fuimos el último, cambiamos el turno.
      if (cochesEnB == 0) turnoCalleA = true;      
      // Señalamos que el cruce quedó libre
      cruce.release();
    }
  }
}
