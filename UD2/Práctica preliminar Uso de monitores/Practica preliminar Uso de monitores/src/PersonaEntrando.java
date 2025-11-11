public class PersonaEntrando extends Thread {

    private ControladorJardin jardin;

    public PersonaEntrando(String nombre, ControladorJardin j){
        this.setName(nombre);
        this.jardin = j;
    }


    @Override
    public void run(){
        jardin.incrementaPersona();
    }
    
}
