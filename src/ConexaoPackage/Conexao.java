package ConexaoPackage;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

import com.google.gson.Gson;

import Loja.Loja;
import VeiculoPackage.Carro;

public class Conexao implements Runnable {

    private String ip;
    private int porta;
    private Loja loja;
    private Semaphore semaphore;

    public Conexao(String ip, int porta, Loja loja, Semaphore semaphore) {
        this.ip = ip;
        this.porta = porta;
        this.loja = loja;
        this.semaphore = semaphore;
    }

    public void Conectar(String enderecoServidor, int porta, Loja loja, Semaphore semaphore) {
        try {
            try (// Conexão com o servidor
                    Socket socket = new Socket(enderecoServidor, porta)) {
                System.out.println("Conectado ao servidor.");

                // Streams de entrada e saída para comunicação com o servidor
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                while (true) {
                    out.writeUTF(this.loja.id + ";" + (this.loja.max_carros_armazenados - this.loja.semaphore.availablePermits()) *-1);
                    System.out.print("\n enviado \n");
                    
                    Gson gson = new Gson();
                    Carro carro = gson.fromJson(in.readUTF(), Carro.class);
                    System.out.println("\n" + carro.toString());
                    this.loja.comprarVeiculo(this.semaphore, carro);
                    System.out.println(carro.toString());
                    Thread.sleep(3000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.Conectar(this.ip, this.porta, this.loja, this.semaphore);
    }
}
