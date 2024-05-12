package FabricaPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import VeiculoPackage.Carro;

public class Estacao {

    private static final int MAX_SOLICITACOES = 5;
    private static final int MAX_CARROS_ARMAZENADOS = 40;

    private Semaphore ferramenta_1 = new Semaphore(1);
    private Semaphore ferramenta_2 = new Semaphore(1);
    private Semaphore ferramenta_3 = new Semaphore(1);
    private Semaphore ferramenta_4 = new Semaphore(1);
    private Semaphore ferramenta_5 = new Semaphore(1);

    private List<Carro> armazem_carros = new ArrayList<>();
    private Semaphore armazem_carros_sem = new Semaphore(MAX_CARROS_ARMAZENADOS);

    private Semaphore esteira_pecas;

    private String id;
    private String fabrica_id;
    private int carros_produzidos;

    Funcionario funcionario_1;
    Funcionario funcionario_2;
    Funcionario funcionario_3;
    Funcionario funcionario_4;
    Funcionario funcionario_5;

    public Estacao(String id, String fabrica_id, Semaphore esteira_pecas, int carros_produzidos) {
        this.id = id;
        this.fabrica_id = fabrica_id;
        funcionario_1 = new Funcionario("Funcionário 1", this.fabrica_id, this.id, ferramenta_1, ferramenta_2, armazem_carros, armazem_carros_sem, esteira_pecas);
        funcionario_2 = new Funcionario("Funcionário 2", this.fabrica_id, this.id, ferramenta_2, ferramenta_3, armazem_carros, armazem_carros_sem, esteira_pecas);
        funcionario_3 = new Funcionario("Funcionário 3", this.fabrica_id, this.id, ferramenta_3, ferramenta_4, armazem_carros, armazem_carros_sem, esteira_pecas);
        funcionario_4 = new Funcionario("Funcionário 4", this.fabrica_id, this.id, ferramenta_4, ferramenta_5, armazem_carros, armazem_carros_sem, esteira_pecas);
        funcionario_5 = new Funcionario("Funcionário 5", this.fabrica_id, this.id, ferramenta_1, ferramenta_5, armazem_carros, armazem_carros_sem, esteira_pecas);

        this.carros_produzidos = carros_produzidos;
        this.esteira_pecas = esteira_pecas; 
    }


    public void produzirCarros() throws InterruptedException{
        while (true) {
            funcionario_1.produzir(this.carros_produzidos);
            funcionario_2.produzir(this.carros_produzidos);
            funcionario_3.produzir(this.carros_produzidos);
            funcionario_4.produzir(this.carros_produzidos);
            funcionario_5.produzir(this.carros_produzidos);
        }
    }
}
