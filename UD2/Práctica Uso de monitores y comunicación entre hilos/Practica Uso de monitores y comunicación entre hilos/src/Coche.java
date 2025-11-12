import java.util.Random;

class Coche extends Thread {

  private final ControladorCruce controlador;
  private final String calle;

  public Coche(String nombre, ControladorCruce controlador, String calle) {
    this.setName(nombre);
    this.controlador = controlador;
    this.calle = calle;
  }

  public void run() {
    Random espera = new Random();
    try {
      Thread.sleep(espera.nextInt(14501) + 500);
      controlador.solicitaCruzar(calle);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
