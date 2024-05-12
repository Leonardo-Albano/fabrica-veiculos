package FabricaPackage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

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

    public Funcionario(String id, String fabrica_id, String estacao_id, Semaphore ferramenta_esquerda,
            Semaphore ferramenta_direita, List<Carro> armazem_carros, Semaphore armazem_carros_sem, 
            Semaphore esteira_pecas) {
        this.id = id;
        this.fabrica_id = fabrica_id;
        this.estacao_id = estacao_id;
        this.ferramenta_esquerda = ferramenta_esquerda;
        this.ferramenta_direita = ferramenta_direita;
        this.armazem_carros = armazem_carros;
        this.armazem_carros_sem = armazem_carros_sem;
        this.esteira_pecas = esteira_pecas;
    }

    public Carro produzir(int carros_produzidos) throws InterruptedException{

        this.esteira_pecas.acquire();

        this.ferramenta_esquerda.acquire();
        this.ferramenta_direita.acquire();

        int id_carro = carros_produzidos;
        char cor_carro = this.randomizarCor();
        String tipo_veiculo = this.randomizarTipoDoVeiculo();
        int posicao_fabricacao = this.armazem_carros.size();

        carros_produzidos++;
        Carro carro = new Carro(id_carro, cor_carro, tipo_veiculo, this.estacao_id, this.id, posicao_fabricacao);
        // Thread.sleep(1000);

        Foo a = new Foo(1);

        this.ferramenta_esquerda.release();
        this.ferramenta_direita.release();

        this.armazem_carros_sem.acquire();
        this.armazem_carros.add(carro);

        System.out.println("\n" + this.toString());
        System.out.println("|\t\tProduziu!\t\t|");
        System.out.println(carro.toString() + "\n");
        
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
                "| ID do funcionário: " + this.id + "\t|\n" + 
                "| Fábrica: " + this.fabrica_id +   "\t\t\t|\n" +
                "| Estação: " + this.estacao_id +   "\t\t\t|\n" +
                "|---------------------------------------|";
    }

}
