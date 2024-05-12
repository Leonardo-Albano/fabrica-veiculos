package ConexaoPackage;

import java.net.*;

import FabricaPackage.Fabrica;
import VeiculoPackage.Carro;

import java.io.*;

public class Conexao {
    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Conexao(int port, Fabrica fabrica) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            
            // takes input from the client socket
            while (true) {
                
                in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
                    String line = "";
                    // reads message from client until "Over" is sent
                    while (!line.equals("vender_veiculo")) {
                        try {
                            line = in.readUTF();
                            // System.out.println(line);
                            Carro carro = fabrica.venderVeiculo();
                            System.out.println("\n\n\n" + carro.toString() + "\n\n\n");
                            
                        } catch (IOException i) {
                            System.out.println(i);
                        }
                    }
                    
                    System.out.println("Closing connection");
                    // close connection
                    // socket.close();
                    // in.close();
            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}