package Loja;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Cliente.Cliente;
import ConexaoPackage.Conexao;

public class Main {

    public static void main(String[] args) throws Exception {

        Semaphore loja_1_sem = new Semaphore(40);
        Loja loja1 = new Loja(loja_1_sem, 1, 10);
        Conexao conexao1 = new Conexao("10.130.44.139", 5000, loja1, loja_1_sem);

        Semaphore loja_2_sem = new Semaphore(40);
        Loja loja2 = new Loja(loja_2_sem, 2, 15);
        Conexao conexao2 = new Conexao("10.130.44.139", 5000, loja2, loja_2_sem);

        Semaphore loja_3_sem = new Semaphore(40);
        Loja loja3 = new Loja(loja_3_sem, 3, 20);
        Conexao conexao3 = new Conexao("10.130.44.139", 5000, loja3, loja_3_sem);

        ArrayList<Loja> listaDeLojas = new ArrayList<>();

        // Adicionando as lojas à lista
        listaDeLojas.add(loja1);
        listaDeLojas.add(loja2);
        listaDeLojas.add(loja3);
        
        for (int i = 0; i <= 20; i++) {
            Cliente cliente = new Cliente(listaDeLojas);
            Thread thread_cliente = new Thread(cliente);
            thread_cliente.start();
        }

        Thread thread_conexao_1 = new Thread(conexao1);
        Thread thread_conexao_2 = new Thread(conexao2);
        Thread thread_conexao_3 = new Thread(conexao3);
        Thread thread_loja_1 = new Thread(loja1);
        Thread thread_loja_2 = new Thread(loja2);
        Thread thread_loja_3 = new Thread(loja3);

        thread_loja_1.start();
        thread_loja_2.start();
        thread_loja_3.start();
        thread_conexao_1.start();
        thread_conexao_2.start();
        thread_conexao_3.start();
    }
}
