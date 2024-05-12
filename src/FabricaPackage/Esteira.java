package FabricaPackage;

import java.util.concurrent.Semaphore;

public class Esteira implements Runnable{
    private Semaphore esteira;
    private static final int MAX_SOLICITACOES = 5;


    public Esteira(Semaphore esteira) {
        this.esteira = esteira;
    }

    @Override
    public void run() {
        while (true) {
            
            try {
                Thread.sleep(1000);
                int usedPermits = MAX_SOLICITACOES - this.esteira.availablePermits();
                this.esteira.release(usedPermits);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
