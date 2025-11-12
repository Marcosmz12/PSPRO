public class ServidorWebs {
    private int cuenta;

    public ServidorWebs() {
        cuenta = 0;
    }

    public void incrementaCuenta() {
        System.out.print(
                Thread.currentThread().getName() + " realiza una solicitud al servidor: ");
        cuenta++;
        System.out.println(cuenta + " accesos realizados hasta ahora.");
    }
}
