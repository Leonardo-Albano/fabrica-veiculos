package LogPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

public class Log {

    private static BufferedWriter bufferedWriterIn;
    private static BufferedWriter bufferedWriterOut;

    public static void criarArquivo() {
        try {
            // Arquivo "in.txt"
            File fileIn = new File("in.txt");
            if (fileIn.createNewFile()) {
                System.out.println("Arquivo in.txt criado.");
            } else {
                System.out.println("Arquivo in.txt já existe.");
            }
            FileWriter fileWriterIn = new FileWriter(fileIn, true);
            bufferedWriterIn = new BufferedWriter(fileWriterIn);

            // Arquivo "out.txt"
            File fileOut = new File("out.txt");
            if (fileOut.createNewFile()) {
                System.out.println("Arquivo out.txt criado.");
            } else {
                System.out.println("Arquivo out.txt já existe.");
            }
            FileWriter fileWriterOut = new FileWriter(fileOut, true);
            bufferedWriterOut = new BufferedWriter(fileWriterOut);
        } catch (IOException e) {
            System.out.println("Erro ocorreu ao criar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void salvarLog(String nomeArquivo, String mensagem) {
        try {
            BufferedWriter bufferedWriter;
            if (nomeArquivo.equals("in.txt")) {
                bufferedWriter = bufferedWriterIn;
            } else if (nomeArquivo.equals("out.txt")) {
                bufferedWriter = bufferedWriterOut;
            } else {
                System.out.println("Nome de arquivo inválido.");
                return;
            }
            
            // Normaliza a mensagem removendo caracteres especiais e acentos
            mensagem = Normalizer.normalize(mensagem, Normalizer.Form.NFD)
                              .replaceAll("[^\\p{ASCII}]", "");
            bufferedWriter.write(mensagem);
            bufferedWriter.newLine(); // Adiciona uma nova linha
            bufferedWriter.flush(); // Força a gravação imediata no arquivo
        } catch (IOException e) {
            System.out.println("Erro ocorreu ao salvar o log.");
            e.printStackTrace();
        }
    }

    public static void fecharArquivo() {
        try {
            bufferedWriterIn.close(); // Fecha o BufferedWriter "in" ao finalizar o uso
            bufferedWriterOut.close(); // Fecha o BufferedWriter "out" ao finalizar o uso
        } catch (IOException e) {
            System.out.println("Erro ocorreu ao fechar o arquivo.");
            e.printStackTrace();
        }
    }
}
