package FabricaPackage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import LogPackage.Log;
import VeiculoPackage.Carro;

public class Funcionario {

    private String id;
    private String fabrica_id;
    private String estacao_id; 
    
    private Semaphore ferramenta_esquerda;
    private Semaphore ferramenta_direita;

    private List<Carro> armazem_carros;
    private Semaphore armazem_carros_sem;

    private Semaphore esteira_pecas;
    private ContadorDeCarros carros_produzidos;

    public Funcionario(String id, String fabrica_id, String estacao_id, Semaphore ferramenta_esquerda,
            Semaphore ferramenta_direita, List<Carro> armazem_carros, Semaphore armazem_carros_sem, 
            Semaphore esteira_pecas, ContadorDeCarros carros_produzidos) {
        this.id = id;
        this.fabrica_id = fabrica_id;
        this.estacao_id = estacao_id;
        this.ferramenta_esquerda = ferramenta_esquerda;
        this.ferramenta_direita = ferramenta_direita;
        this.armazem_carros = armazem_carros;
        this.armazem_carros_sem = armazem_carros_sem;
        this.esteira_pecas = esteira_pecas;
        this.carros_produzidos = carros_produzidos;
    }

    public Carro produzir() throws InterruptedException{

        this.esteira_pecas.acquire();

        this.ferramenta_esquerda.acquire();
        this.ferramenta_direita.acquire();

        int id_carro = carros_produzidos.getNum();
        char cor_carro = this.randomizarCor();
        String tipo_veiculo = this.randomizarTipoDoVeiculo();
        int posicao_fabricacao = this.armazem_carros.size();

        Carro carro = new Carro(id_carro, cor_carro, tipo_veiculo, this.estacao_id, this.id, posicao_fabricacao);
        Random random = new Random();
        Thread.sleep(random.nextInt(10000 - 5000 + 1) + 5000);

        this.ferramenta_esquerda.release();
        this.ferramenta_direita.release();

        this.armazem_carros_sem.acquire();
        this.armazem_carros.add(carro);

        Log.salvarLog("in.txt", carro.toString());
        String mensagem = "\n" + this.toString() + "\n" + "|\t\tProduziu!\t\t|\n" + carro.toString() + "\n";
        System.out.println(mensagem);
        
        return carro;
    }
    
    private char randomizarCor(){
        List<Character> givenList = Arrays.asList('R', 'G', 'B');
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    } 

    private String randomizarTipoDoVeiculo() {
        List<String> tiposVeiculo = Arrays.asList("Sedan", "SUV");
        Random rand = new Random();
        return tiposVeiculo.get(rand.nextInt(tiposVeiculo.size()));
    }


    @Override
    public String toString() {
        return  "|---------------------------------------|\n" +
                "| Fábrica: " + this.fabrica_id +   "\t\t\t|\n" +
                "| Estação: " + this.estacao_id +   "\t\t\t|\n" +
                "| ID do funcionário: " + this.id + "\t|\n" + 
                "|---------------------------------------|";
    }

}
