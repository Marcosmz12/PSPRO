public class PersonaSaliendo extends Thread{

    private ControladorJardin jardin;
    
    public PersonaSaliendo(String nombre, ControladorJardin j){
        this.setName(nombre);
        this.jardin = j;
    }

    @Override
    public void run(){
        jardin.decrementaPersona();
    }
}
