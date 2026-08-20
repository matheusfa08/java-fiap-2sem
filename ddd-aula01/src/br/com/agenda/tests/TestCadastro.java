package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.Scanner;

//Vamos testar se ele já insere valores à tabela
public class TestCadastro {
    public static void main(String[] args) {
        //Aqui estão os leitores, para deixar com que o usuário possa digitar
        Scanner inputInt = new Scanner(System.in);
        Scanner inputStr = new Scanner(System.in);

        //Aqui estão as instâncias dos objetos
        Contato c1 = new Contato();
        ContatoDAO dao = new ContatoDAO();

        //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
        System.out.println("\n======CADASTRO DE CONTATOS======");
        System.out.println("Olá! Vamos cadastrar seu contato, insira suas informações");
        System.out.println("\nDigite o código do contato: ");
        c1.setIdContato(inputInt.nextInt());
        System.out.println("Digite o nome do contato:   ");
        c1.setNomeContato(inputStr.next());
        System.out.println("Digite o email do contato: ");
        c1.setEmailContato(inputStr.next());
        System.out.println("Digite o telefone: ");
        c1.setCelularContato(inputStr.next());
        System.out.println("Digite o Instagram: ");
        c1.setInstagram(inputStr.next());
        System.out.println("Digite o tipo (AMIGO, FAMILIAR, PROFISSIONAL): ");
        c1.setTipo(inputStr.next());

        //Ele chama a função cadastrarContato do objeto ContatoDao
        dao.cadastrarContato(c1);
        System.out.println("Contato Cadastrado com sucesso!");
    }
}
