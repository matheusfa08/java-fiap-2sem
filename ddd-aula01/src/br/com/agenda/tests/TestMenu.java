package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.Scanner;

//Criamos um teste para o menu
public class TestMenu {
    public static void main(String[] args) {
        Scanner inputInt = new Scanner(System.in);
        Scanner inputStr = new Scanner(System.in);

        Contato c = new Contato();
        ContatoDAO dao = new ContatoDAO();

        int op = -1;

        while (op != 0){
            System.out.println("\n======AGENDA DE CONTATOS======");
            System.out.println("1 - Cadastrar Contato");
            System.out.println("2 - Buscar Contato por ID");
            System.out.println("0 - Sair");
            System.out.println("------------------------------");
            System.out.println("Digite  uma opção");
            op = inputInt.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n======CADASTRO DE CONTATOS======");
                    System.out.println("Olá! Vamos cadastrar seu contato, insira suas informações");
                    System.out.println("\nDigite o código do contato: ");
                    c.setIdContato(inputInt.nextInt());
                    System.out.println("Digite o nome do contato:   ");
                    c.setNomeContato(inputStr.next());
                    System.out.println("Digite o email do contato: ");
                    c.setEmailContato(inputStr.next());
                    System.out.println("Digite o telefone: ");
                    c.setCelularContato(inputStr.next());
                    System.out.println("Digite o Instagram: ");
                    c.setInstagram(inputStr.next());
                    System.out.println("Digite o tipo (AMIGO, FAMILIAR, PROFISSIONAL): ");
                    c.setTipo(inputStr.next());

                    dao.cadastrarContato(c);
                    System.out.println("Contato Cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n======BUSCA POR ID======");
                    System.out.println("Digite o ID do Contato: ");
                    int id = inputInt.nextInt();

                    c = dao.consultarContato(id);
                    System.out.println(c);
                    break;

                case 0:
                    System.out.println("------------------------------");
                    System.out.println("Fechando programa...");

            }
        }
    }
}
