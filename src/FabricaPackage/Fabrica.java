package FabricaPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import VeiculoPackage.Carro;

public class Fabrica implements Runnable{
    static final int MAX_PECAS = 500;
    static final int NUM_ESTACOES = 4;
    static final int MAX_CARROS_ARMAZENADOS = 40;


    String id;
    Estacao estacao_1;
    Estacao estacao_2;
    Estacao estacao_3;
    Estacao estacao_4; 

    private List<Carro> armazem_carros = new ArrayList<>();
    private Semaphore armazem_carros_sem = new Semaphore(MAX_CARROS_ARMAZENADOS);

    ContadorDeCarros carros_produzidos;
    
    public Fabrica(String id, Semaphore esteira_1_sem, Semaphore esteira_2_sem, Semaphore esteira_3_sem, Semaphore esteira_4_sem) {
        this.id = id;
        this.carros_produzidos = new ContadorDeCarros(1);
        this.armazem_carros = new ArrayList<>();
        this.armazem_carros_sem = new Semaphore(MAX_CARROS_ARMAZENADOS);

        estacao_1 = new Estacao("Estação 1", this.id, esteira_1_sem, this.carros_produzidos, this.armazem_carros, this.armazem_carros_sem);
        estacao_2 = new Estacao("Estação 2", this.id, esteira_2_sem, this.carros_produzidos, this.armazem_carros, this.armazem_carros_sem);
        estacao_3 = new Estacao("Estação 3", this.id, esteira_3_sem, this.carros_produzidos, this.armazem_carros, this.armazem_carros_sem);
        estacao_4 = new Estacao("Estação 4", this.id, esteira_4_sem, this.carros_produzidos, this.armazem_carros, this.armazem_carros_sem);
    }

    @Override
    public void run() {
        Thread thread1 = new Thread(() -> produzir(estacao_1));
        Thread thread2 = new Thread(() -> produzir(estacao_2));
        Thread thread3 = new Thread(() -> produzir(estacao_3));
        Thread thread4 = new Thread(() -> produzir(estacao_4));
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public Carro venderVeiculo(){
        Carro carro = null; 
        while(carro == null){
            if (!this.armazem_carros.isEmpty()) { 
                carro = this.armazem_carros.remove(0);
                this.armazem_carros_sem.release();
                // this.armazem_carros_sem.notify();
            }
        }
        return carro;
    }
        
    private void produzir(Estacao estacao){
        try {
            estacao.produzirCarros();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
