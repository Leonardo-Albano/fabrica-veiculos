package FabricaPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import VeiculoPackage.Carro;

public class Estacao {
    private Semaphore ferramenta_1;
    private Semaphore ferramenta_2;
    private Semaphore ferramenta_3;
    private Semaphore ferramenta_4;
    private Semaphore ferramenta_5;

    private String id;
    private String fabrica_id;

    Funcionario funcionario_1;
    Funcionario funcionario_2;
    Funcionario funcionario_3;
    Funcionario funcionario_4;
    Funcionario funcionario_5;

    public Estacao(String id, String fabrica_id, Semaphore esteira_pecas, ContadorDeCarros carros_produzidos, List<Carro> armazem_carros, Semaphore armazem_carros_sem) {
        this.id = id;
        this.fabrica_id = fabrica_id;
        
        ferramenta_1 = new Semaphore(1);
        ferramenta_2 = new Semaphore(1);
        ferramenta_3 = new Semaphore(1);
        ferramenta_4 = new Semaphore(1);
        ferramenta_5 = new Semaphore(1);
        
        funcionario_1 = new Funcionario("Funcionário 1", this.fabrica_id, this.id, ferramenta_1, ferramenta_2, armazem_carros, armazem_carros_sem, esteira_pecas, carros_produzidos);
        funcionario_2 = new Funcionario("Funcionário 2", this.fabrica_id, this.id, ferramenta_2, ferramenta_3, armazem_carros, armazem_carros_sem, esteira_pecas, carros_produzidos);
        funcionario_3 = new Funcionario("Funcionário 3", this.fabrica_id, this.id, ferramenta_3, ferramenta_4, armazem_carros, armazem_carros_sem, esteira_pecas, carros_produzidos);
        funcionario_4 = new Funcionario("Funcionário 4", this.fabrica_id, this.id, ferramenta_4, ferramenta_5, armazem_carros, armazem_carros_sem, esteira_pecas, carros_produzidos);
        funcionario_5 = new Funcionario("Funcionário 5", this.fabrica_id, this.id, ferramenta_1, ferramenta_5, armazem_carros, armazem_carros_sem, esteira_pecas, carros_produzidos);
    }


    public void produzirCarros() throws InterruptedException{
        while (true) {
            funcionario_1.produzir();
            funcionario_2.produzir();
            funcionario_3.produzir();
            funcionario_4.produzir();
            funcionario_5.produzir();
        }
    }
}
