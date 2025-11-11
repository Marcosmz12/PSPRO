public class ControladorJardin {
    private int cuenta;

    public ControladorJardin(){
        cuenta = 100;
    }

    public synchronized void incrementaPersona(){
        System.out.println(
            Thread.currentThread().getName() + " entra en el jardin: "
        );
        cuenta ++;
        System.out.println(cuenta + " personas ahora en el jardin.");
    }

    public synchronized void decrementaPersona(){
        System.out.println(
            Thread.currentThread().getName() + " sale del jardin: "
        );
        cuenta --;
        System.out.println(cuenta + " personas ahora en el jardin.");
    }

}
