import java.util.concurrent.Semaphore;

import ConexaoPackage.Servidor;
import FabricaPackage.*;
import LogPackage.Log;

public class App {
    public static void main(String[] args) throws Exception {
        Log.criarArquivo();
        
        Semaphore esteira_1_sem = new Semaphore(1);
        Esteira esteira_1 = new Esteira(esteira_1_sem);
        
        Semaphore esteira_2_sem = new Semaphore(1);
        Esteira esteira_2 = new Esteira(esteira_2_sem);
        
        Semaphore esteira_3_sem = new Semaphore(1);
        Esteira esteira_3 = new Esteira(esteira_3_sem);
        
        Semaphore esteira_4_sem = new Semaphore(1);
        Esteira esteira_4 = new Esteira(esteira_4_sem);
        
        Fabrica fabrica = new Fabrica("Fábrica 1", esteira_1_sem, esteira_2_sem, esteira_3_sem, esteira_4_sem);
        Servidor servidor = new Servidor(fabrica, 5000);

        Thread thread_fabrica_1 = new Thread(fabrica);
        Thread thread_servidor = new Thread(servidor);
        
        Thread thread_esteira_1 = new Thread(esteira_1);
        Thread thread_esteira_2 = new Thread(esteira_2);
        Thread thread_esteira_3 = new Thread(esteira_3);
        Thread thread_esteira_4 = new Thread(esteira_4);
        
        
        thread_fabrica_1.start();
        thread_esteira_1.start();
        thread_esteira_2.start();
        thread_esteira_3.start();
        thread_esteira_4.start();
        thread_servidor.start();
    }
}
