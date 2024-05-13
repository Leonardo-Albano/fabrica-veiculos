package Loja;

import java.util.concurrent.Semaphore;

public class Loja implements Runnable {

    public Loja(Semaphore Semaphore) {

    }

    public void comprarVeiculo()
    {

    }

    @Override
    public void run() {
        this.comprarVeiculo();
    }

}
