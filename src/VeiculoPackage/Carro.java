package VeiculoPackage;

public class Carro {
    private int id;
    private char cor_carro;
    private String tipo_veiculo;
    private String id_estacao;
    private String id_funcionario;
    private int posicao_fabricacao;
    
    public Carro(int id, char cor_carro, String tipo_veiculo, String id_estacao, String id_funcionario,
            int posicao_fabricacao) {
        this.id = id;
        this.cor_carro = cor_carro;
        this.tipo_veiculo = tipo_veiculo;
        this.id_estacao = id_estacao;
        this.id_funcionario = id_funcionario;
        this.posicao_fabricacao = posicao_fabricacao;
    }

    @Override
    public String toString() {
        return  "|---------------------------------------|\n" +
                "| ID do Carro: " + this.id + "\t\t\t|\n" + 
                "| Cor do Carro: " + this.cor_carro +   "\t\t\t|\n" +
                "| Tipo de Veículo: " + this.tipo_veiculo +   "\t\t|\n" +
                "| ID da Estação: " + this.id_estacao +   "\t\t|\n" +
                "| ID do Funcionário: " + this.id_funcionario +   "\t|\n" +
                "| Posição de Fabricação: " + this.posicao_fabricacao +   "\t\t|\n" +
                "|---------------------------------------|";
    }


}
