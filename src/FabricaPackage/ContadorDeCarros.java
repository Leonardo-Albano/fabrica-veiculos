package FabricaPackage;

import java.util.concurrent.Semaphore;

public class ContadorDeCarros{
    private int num;
}

public class Fabrica implements Runnable{
    static final int MAX_PECAS = 500;
    static final int NUM_ESTACOES = 4;

    String id;
    int carros_produzidos;
    Estacao estacao_1;
    Estacao estacao_2;
    Estacao estacao_3;
    Estacao estacao_4; 
    
    public Fabrica(String id, Semaphore esteira_1_sem, Semaphore esteira_2_sem, Semaphore esteira_3_sem, Semaphore esteira_4_sem) {
        this.id = id;
        this.carros_produzidos = 0;

        estacao_1 = new Estacao("Estação 1", this.id, esteira_1_sem, this.carros_produzidos);
        estacao_2 = new Estacao("Estação 2", this.id, esteira_2_sem, this.carros_produzidos);
        estacao_3 = new Estacao("Estação 3", this.id, esteira_3_sem, this.carros_produzidos);
        estacao_4 = new Estacao("Estação 4", this.id, esteira_4_sem, this.carros_produzidos);
    }

    @Override
    public void run() {
        while (true) {
            this.produzir(estacao_1);
            this.produzir(estacao_2);
            this.produzir(estacao_3);
            this.produzir(estacao_4);
        }
        
    }

    private void produzir(Estacao estacao){
        try {
            estacao.produzirCarros();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
