package Loja;

import java.util.concurrent.Semaphore;

import ConexaoPackage.Conexao;

public class Main {

    public static void main(String[] args) throws Exception {

        Semaphore loja_1_sem = new Semaphore(40);
        Loja loja1 = new Loja(loja_1_sem);
        Conexao conexao1 = new Conexao("10.130.44.139", 5000, loja1);
        
        Semaphore loja_2_sem = new Semaphore(40);
        Loja loja2 = new Loja(loja_2_sem);
        Conexao conexao2 = new Conexao("10.130.44.139", 5000, loja2);

        Semaphore loja_3_sem = new Semaphore(40);
        Loja loja3 = new Loja(loja_3_sem);
        Conexao conexao3 = new Conexao("10.130.44.139", 5000, loja3);

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
