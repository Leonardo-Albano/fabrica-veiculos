package Loja;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import VeiculoPackage.Carro;

public class Loja implements Runnable {

    public int id;
    public Semaphore semaphore;
    private Carro carro;
    public ArrayList<Carro> carros;
    public int max_carros_armazenados;

    public Loja(Semaphore semaphore, int id, int max_carros_armazenados) {
        this.id = id;
        this.semaphore = semaphore;
        this.carros = new ArrayList<Carro>();
        this.max_carros_armazenados = max_carros_armazenados;
    }

    public void comprarVeiculo(Semaphore semaphore, Carro carro) throws InterruptedException {
        this.semaphore.acquire();
        this.carros.add(carro);
    }

    public void venderVeiculo(Semaphore semaphore, int quantCarros) throws InterruptedException {
        if (quantCarros <= this.carros.size()) {
            this.semaphore.release(quantCarros);
            this.carros.subList(0, quantCarros);
        }
    }

    @Override
    public void run() {
        try {
            this.comprarVeiculo(this.semaphore, this.carro);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
