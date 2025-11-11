public class Jardin {
    public static void main(String[] args) {
        ControladorJardin jardin = new ControladorJardin();

        for (int i = 1; i < 10; i++) {
            (new PersonaEntrando("Persona entrando nº " +i, jardin)).start();
        }

        for (int i = 1; i < 15; i++) {
            (new PersonaSaliendo("Persona saliendo nº " +i, jardin)).start();
        }
    }
}
