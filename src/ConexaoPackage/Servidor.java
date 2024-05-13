package ConexaoPackage;

import java.net.*;
import com.google.gson.Gson;


import FabricaPackage.Fabrica;
import VeiculoPackage.Carro;

import java.io.*;

public class Servidor implements Runnable {
    
    private Fabrica fabrica;
    private int porta;

    public Servidor(Fabrica fabrica, int porta) {
        this.fabrica = fabrica;
        this.porta = porta;
    }

    public void abrirConexao() throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta "+ this.porta +" aberta!");

        Socket cliente = servidor.accept();
        System.out.println("Nova conexão com o  cliente " +
                cliente.getInetAddress().getHostAddress()
        );

        DataInputStream dis = new DataInputStream(cliente.getInputStream());
        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        // ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Gson gson = new Gson();


        while(true){
            if(dis.available() > 0){

                String mensagem = dis.readUTF();
                System.out.println(mensagem);

                Carro carro = this.fabrica.venderVeiculo();
                System.out.println("\n|\t" + cliente.getInetAddress().getHostAddress() + " Comprou!\t\t|\n" + carro.toString() + "\n");
                
                dos.writeUTF(gson.toJson(carro));
                dos.flush();
                
                if(mensagem == "exit"){
                    break;
                }
            }
        }        

        servidor.close();
        cliente.close();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.abrirConexao();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}