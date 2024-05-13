package FabricaPackage;

import java.util.concurrent.Semaphore;

public class ContadorDeCarros{

    private Semaphore semaphore;
    private int num;

    public ContadorDeCarros(int num) {
        this.num = num;
        this.semaphore = new Semaphore(1);
    }
    
    public int getNum() throws InterruptedException {
        semaphore.acquire();
        int num = this.num;
        this.num++;
        semaphore.release();
        
        return num;
    }


}