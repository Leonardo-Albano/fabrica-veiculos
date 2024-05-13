package ConexaoPackage;

import java.io.*;
import java.net.*;
import com.google.gson.Gson;

import Loja.Loja;
import VeiculoPackage.Carro;

public class Conexao implements Runnable {

    private String ip;
    private Loja loja;
    private int porta;

    public Conexao(String ip, int porta, Loja loja) {
        this.ip = ip;
        this.porta = porta;
        this.loja = loja;
    }

    public void Conectar(String enderecoServidor, int porta, Loja loja) {
        try {
            try (// Conexão com o servidor
                    Socket socket = new Socket(enderecoServidor, porta)) {
                System.out.println("Conectado ao servidor.");

                // Streams de entrada e saída para comunicação com o servidor
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                while (true) {
                    out.writeUTF("Olá, servidor!");
                    System.out.print("\n enviado \n");
                    this.loja.comprarVeiculo();

                    Gson gson = new Gson();
                    Carro response = gson.fromJson(in.readUTF(), Carro.class);

                    System.out.println(response.toString());
                    Thread.sleep(3000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.Conectar(this.ip, this.porta, this.loja);
    }
}
