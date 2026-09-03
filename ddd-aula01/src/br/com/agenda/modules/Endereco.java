package br.com.agenda.modules;

//Criamos a classe Endereco baseada nas caracteristicas da tabela Endereco_agenda
public class Endereco {

    //Parâmetros de Endereco
    private int codigo;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String uf;
    private String numero;
    private String complemento;

    //Getters e Setters para que outro pacote possa acessar as informações
    public int getCodigo() {return codigo;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getLogradouro() {return logradouro;}

    public void setLogradouro(String logradouro) {this.logradouro = logradouro;}

    public String getCep() {return cep;}

    public void setCep(String cep) {this.cep = cep;}

    public String getBairro() {return bairro;}

    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}

    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    public String getUf() {return uf;}

    public void setUf(String uf) {this.uf = uf;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getComplemento() {return complemento;}

    public void setComplemento(String complemento) {this.complemento = complemento;}

    //metodo void para retornar um menuzinho simples
    public void menu(){
        System.out.println("\n======ENDEREÇOS DOS CONTATOS======");
        System.out.println("1 - Cadastrar Endereço");
        System.out.println("2 - Buscar Endereço por Código");
        System.out.println("3 - Listar Endereços");
        System.out.println("4 - Alterar Endereço por Código");
        System.out.println("5 - Remover Endereço");
        System.out.println("0 - Sair");
        System.out.println("------------------------------");
        System.out.println("Digite  uma opção: ");
    }

    //toString para quando chamarmos o objeto por ele mesmo, ele retornar uma descrição dele mesmo
    @Override
    public String toString() {
        return "\n======ENDEREÇO======\n" +
                "Logradouro: " + logradouro + '\n' +
                "CEP: " + cep + '\n' +
                "Bairro: " + bairro + '\n' +
                "Cidade: " + cidade + '\n' +
                "Estado: " + estado + '\n' +
                "UF: " + uf + '\n' +
                "Número: " + numero + '\n' +
                "Complemento: " + complemento;
    }
}
