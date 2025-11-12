import java.util.concurrent.Semaphore;

public class SimaularAccesos {

        public static void main(String[] args) {
                Semaphore semáforo = new Semaphore(1);
                ServidorWebs servidor = new ServidorWebs();
                TerminalClientes terminal1 = new TerminalClientes(
                                "Terminal cliente 1",
                                servidor,
                                semáforo);
                TerminalClientes terminal2 = new TerminalClientes(
                                "Terminal cliente 2",
                                servidor,
                                semáforo);
                TerminalClientes terminal3 = new TerminalClientes(
                                "Terminal cliente 3",
                                servidor,
                                semáforo);
                TerminalClientes terminal4 = new TerminalClientes(
                                "Terminal cliente 4", 
                                servidor, 
                                semáforo);

                // Se inician los cuatro hilos
                terminal1.start();
                terminal2.start();
                terminal3.start();
                terminal4.start();
        }
}
