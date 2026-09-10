package br.com.agenda.agenda_web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Aplicando o lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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


    //metodo void para retornar um menuzinho simples
    public void menu() {
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