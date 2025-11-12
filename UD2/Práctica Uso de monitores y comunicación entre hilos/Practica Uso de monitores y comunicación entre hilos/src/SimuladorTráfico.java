public class SimuladorTráfico {

  public static void main(String[] args) {
    ControladorCruce controlador = new ControladorCruce();

    // Crear coches en Calle A y Calle B
    for (int i = 0; i < 5; i++) {
      new Coche("Coche " + i, controlador, "A").start();
      new Coche("Coche " + i, controlador, "B").start();
    }
  }
}
