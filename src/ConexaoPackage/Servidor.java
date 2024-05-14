package ConexaoPackage;

import java.net.*;
import com.google.gson.Gson;

import FabricaPackage.Fabrica;
import VeiculoPackage.Carro;

import java.io.*;

public class Servidor implements Runnable {
    
    private Fabrica fabrica;
    private int porta;
    private static final int MAX_CONEXOES = 3;

    public Servidor(Fabrica fabrica, int porta) {
        this.fabrica = fabrica;
        this.porta = porta;
    }

    public void abrirConexao(Socket cliente) {
        try {
            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
            Gson gson = new Gson();

            while (true) {
                if (dis.available() > 0) {
                    String mensagem = dis.readUTF();
                    System.out.println(mensagem);

                    if (mensagem.equals("exit")) {
                        break;
                    }

                    Carro carro = this.fabrica.venderVeiculo();
                    String mensagemOUT = carro.toString() + "\n" + "a";
                    System.out.println("\n|\t" + cliente.getInetAddress().getHostAddress() + " Comprou!\t\t|\n" + carro.toString() + "\n");
                    
                    dos.writeUTF(gson.toJson(carro));
                    dos.flush();
                }
            }

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(this.porta);
            System.out.println("Porta " + this.porta + " aberta!");

            int conexoesAtivas = 0;

            while (conexoesAtivas < MAX_CONEXOES) {
                Socket cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente " +
                        cliente.getInetAddress().getHostAddress()
                );

                Thread thread = new Thread(() -> abrirConexao(cliente));
                thread.start();

                conexoesAtivas++;
            }

            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}