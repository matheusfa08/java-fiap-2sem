package br.com.agenda.agenda_web.entity;

import br.com.agenda.agenda_web.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//Aplicando o Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Contato {

    //Variáveis que remetem aos elementos da tabela (De exemplo) contatos, no banco de dados, para melhor ilustração.
    private int idContato;
    private String nomeContato;
    private String celularContato;
    private String emailContato;
    private String instagram;
    private TipoEnum tipo;
    //N -> 1
    private Endereco endereco;
    //N -> N
    private List<Endereco> enderecos;

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
    public void menu() {
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