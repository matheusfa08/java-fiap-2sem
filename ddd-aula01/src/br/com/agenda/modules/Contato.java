package br.com.agenda.modules;

import java.util.List;

public class Contato {

    //Variáveis que remetem aos elementos da tabela (De exemplo) contatos, no banco de dados, para melhor ilustração.
    private int idContato;
    private String nomeContato;
    private String celularContato;
    private String emailContato;
    private String instagram;
    private String tipo;
    //N -> 1
    private Endereco endereco;
    //N -> N
    private List<Endereco> enderecos;

    //Métodos getters e setters para que esses elementos possam ser acessados e alterados fora da classe.
    public int getIdContato() {return idContato;}

    public void setIdContato(int idContato) {this.idContato = idContato;}

    public String getNomeContato() {return nomeContato;}

    public void setNomeContato(String nomeContato) {this.nomeContato = nomeContato;}

    public String getCelularContato() {return celularContato;}

    public void setCelularContato(String celularContato) {this.celularContato = celularContato;}

    public String getEmailContato() {return emailContato;}

    public void setEmailContato(String emailContato) {this.emailContato = emailContato;}

    public String getInstagram() {return instagram;}

    public void setInstagram(String instagram) {this.instagram = instagram;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public Endereco getEndereco() {return endereco;}

    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public List<Endereco> getEnderecos() {return enderecos;}

    public void setEnderecos(List<Endereco> enderecos) {this.enderecos = enderecos;}

    //Queremos usar uma chave entrangeira agora. Primeiro, vamos usar um Alter para adicionar uma Foreign Key. Que
    //irá se referir ao endereço. Após isso, criamos o parâmetro endereco que recebe o objeto Endereço.

    //Agora, vamos criar um objeto que vai ir até o banco de dados e vai realizar o CRUD (Create, read, update, delete)
    //Para ver, vá até ContatoDAO

    //Lembra do toString()? Ele imprime uma mensagem sempre que você chamar o objeto em alguma outra classe
    @Override
    public String toString() {
        return "\n======CONTATO======\n" +
                "Nome: " + nomeContato + '\n' +
                "Celular: " + celularContato + '\n' +
                "Email: " + emailContato + '\n' +
                "Instagram: " + instagram + '\n' +
                "Tipo: " + tipo + '\n' +
                endereco;
    }

    //metodo void para retornar um menuzinho simples
    public void menu(){
        System.out.println("\n======AGENDA DE CONTATOS======");
        System.out.println("1 - Cadastrar Contato");
        System.out.println("2 - Buscar Contato por ID");
        System.out.println("3 - Listar Contados");
        System.out.println("4 - Alterar Contato por ID");
        System.out.println("5 - Remover Contato");
        System.out.println("0 - Sair");
        System.out.println("------------------------------");
        System.out.println("Digite  uma opção: ");
    }
}
