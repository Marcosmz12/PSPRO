public class RecursoJardin {

  private int cuenta; // variable usada para contar las entradas y salidas al jardín

  public RecursoJardin() {
    cuenta = 100; // inicalmente hay 100 personas en el jardín
  }

  public void incrementaCuenta() {
    // método que increamenta en 1 la variable cuenta
    System.out.print(
      Thread.currentThread().getName() + " entra en el jardín: "
    );
    // muestra el hilo que entra en el método
    cuenta++;
    System.out.println(cuenta + " personas ahora en el jardín");
    // cuenta cada acceso al jardín y muestra el número de accesos
  }

  public void decrementaCuenta() {
    // método que decrementa en 1 la variable cuenta
    System.out.print(
      Thread.currentThread().getName() + " sale del jardín: "
    );
    // muestra el hilo que sale en el método
    cuenta--;
    System.out.println(cuenta + " personas ahora en el jardín");
    // cuenta cada acceso al jardín y muestra el número de accesos
  }
}
