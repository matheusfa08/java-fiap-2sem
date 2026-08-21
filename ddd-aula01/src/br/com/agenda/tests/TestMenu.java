package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Criamos um teste para o menu
public class TestMenu {
    public static void main(String[] args) {

        //Criando os leitores para que o usuário possa inserir info
        Scanner inputInt = new Scanner(System.in);
        Scanner inputStr = new Scanner(System.in);

        //Instânciando os objetos que serão usados e a lista
        Contato c = new Contato();
        ContatoDAO dao = new ContatoDAO();
        List<Contato> contatos = new ArrayList<>();

        //Criando a variável op (Opção) para que a repetição while a seguir seja possível
        int op = -1;

        /*Repetição while. Enquanto op for diferente de zero, ele mostrará o menu e dará o poder do usuário escolher
        uma opção*/
        while (op != 0){
            c.menu();
            op = inputInt.nextInt();

            /*Switch case, se usa nessas situações de menu ou quando preciso que algo aconteça em algum caso. Nesse
            caso, ele usa a variável op para ver as situações*/
            switch (op) {
                case 1:

                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
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

                    //Ele chama a função cadastrarContato do objeto ContatoDao
                    dao.cadastrarContato(c);
                    System.out.println("Contato Cadastrado com sucesso!");

                    //Esse break impede que ele excute a linha de código abaixo, que é a case 2
                    break;

                case 2:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA POR ID======");
                    System.out.println("Digite o ID do Contato: ");
                    int id = inputInt.nextInt();

                    /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja,
                    ele ganha os valores do objeto criado a partir da função*/
                    c = dao.consultarContato(id);

                    //Chama o objeto que retorna a sua toString()
                    System.out.println(c);

                    //Esse break impede que ele excute a linha de código abaixo, que é a case 3
                    break;

                case 3:

                    //A lista contatos vai receber a função, do objeto dao, listarContatos()
                    contatos = dao.listarContatos();

                    /*As mensagens são mostradas chamando a lista e para cada objeto dentro da lista, eles vão chamar a
                    sua própria toString(), ou se chamar, como preferir*/
                    System.out.println("======CONTATOS DA AGENDA======");
                    contatos.forEach(System.out::println);

                    //Esse break impede que ele excute a linha de código abaixo, que é a case 4
                    break;

                case 4:
                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
                    System.out.println("\n======ALTERAÇÃO DE CONTATOS======");
                    System.out.println("Vamos alterar seu contatos, insira as novas informações");
                    System.out.println("\nDigite o código do contato que deseja alterar: ");
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

                    //Ele chama a função alterar Contato do objeto ContatoDao
                    dao.alterarContato(c);
                    System.out.println("Contato alterado com sucesso!");

                    //Esse break impede que ele excute a linha de código abaixo, que é a case 5
                    break;

                case 5:
                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
                    System.out.println("\n======EXCLUIR CONTATO======");
                    System.out.println("Infelizmente você quer exluir alguém😢");
                    System.out.println("\nDigite o código do contato: ");
                    c.setIdContato(inputInt.nextInt());

                    //Chama a função excluirContato(id) e mostra mensagem de êxito
                    dao.excluirContato(c.getIdContato());
                    System.out.println("Contato terminado...");

                    //Esse break impede que ele excute a linha de código abaixo, que é a case 0
                    break;

                case 0:
                    //Exibe mensagem para fechar programa
                    System.out.println("------------------------------");
                    System.out.println("Fechando programa...");

            }
        }
    }
}
