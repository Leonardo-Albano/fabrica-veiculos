package Cliente;

import java.util.ArrayList;
import java.util.Random;

import Loja.Loja;

public class Cliente implements Runnable {

    private ArrayList<Loja> lojas;

    public Cliente(ArrayList<Loja> lojas) {
        this.lojas = lojas;
    }

    public void comprarVeiculoLoja() throws InterruptedException {
        Random random = new Random();

        int indiceAleatorio = random.nextInt(this.lojas.size());
        Loja loja = this.lojas.get(indiceAleatorio);
        loja.venderVeiculo(loja.semaphore, random.nextInt(501));
    }

    @Override
    public void run() {
        try {
            this.comprarVeiculoLoja();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
