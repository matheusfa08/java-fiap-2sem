package br.com.agenda.tests;


import br.com.agenda.dao.EnderecoDAO;
import br.com.agenda.modules.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteMenuEndereco {
    public static void main(String[] args) {

        //Criando os leitores para que o usuário possa inserir info
        Scanner inputInt = new Scanner(System.in);
        Scanner inputStr = new Scanner(System.in);

        //Instânciando os objetos que serão usados e a lista
        Endereco e = new Endereco();
        EnderecoDAO dao = new EnderecoDAO();
        List<Endereco> enderecos = new ArrayList<>();

        //Criando a variável op (Opção) para que a repetição while a seguir seja possível
        int op = -1;

        /*Repetição while. Enquanto op for diferente de zero, ele mostrará o menu e dará o poder do usuário escolher
        uma opção*/
        while (op != 0){
            e.menu();
            op = inputInt.nextInt();

            /*Switch case, se usa nessas situações de menu ou quando preciso que algo aconteça em algum caso. Nesse
            caso, ele usa a variável op para ver as situações*/
            switch (op) {
                case 1:

                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
                    System.out.println("\n======CADASTRO DE ENDEREÇOS======");
                    System.out.println("Olá! Vamos cadastrar um endereço, insira as informações");
                    System.out.println("\nDigite o código do endereço: ");
                    e.setCodigo(inputInt.nextInt());
                    System.out.println("Digite o logradouro: ");
                    e.setLogradouro(inputStr.nextLine());
                    System.out.println("Digite o Código de Endereçamento Postal: ");
                    e.setCep(inputStr.nextLine());
                    System.out.println("Digite o bairro: ");
                    e.setBairro(inputStr.nextLine());
                    System.out.println("Digite a cidade: ");
                    e.setCidade(inputStr.nextLine());
                    System.out.println("Digite o estado: ");
                    e.setEstado(inputStr.nextLine());
                    System.out.println("Digite a Unidade Federativa: ");
                    e.setUf(inputStr.nextLine());
                    System.out.println("Digite o número do endereço: ");
                    e.setNumero(inputStr.nextLine());
                    System.out.println("Digite o complemento (Se necessário): ");
                    e.setComplemento(inputStr.nextLine());

                    //Ele chama a função cadastrarEndereco do objeto EnderecoDao
                    dao.cadastrarEndereco(e);
                    System.out.println("Endereço cadastrado com sucesso!");

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 2
                    break;

                case 2:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA POR CÓDIGO======");
                    System.out.println("Digite o Código do Endereço: ");
                    int codigo = inputInt.nextInt();

                    /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja,
                    ele ganha os valores do objeto criado a partir da função*/
                    e = dao.consultarEndereco(codigo);

                    //Chama o objeto que retorna a sua toString()
                    System.out.println(e);

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 3
                    break;

                case 3:

                    //A lista contatos vai receber a função, do objeto dao, listarEnderecos()
                    enderecos = dao.listarEnderecos();

                    /*As mensagens são mostradas chamando a lista e para cada objeto dentro da lista, eles vão chamar a
                    sua própria toString(), ou se chamar, como preferir*/
                    System.out.println("\n======ENDEREÇOS DOS CONTATOS======");
                    enderecos.forEach(System.out::println);

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 4
                    break;

                case 4:
                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as informações
                    System.out.println("\n======ALTERAÇÃO DE ENDEREÇOS======");
                    System.out.println("Olá! Vamos cadastrar um endereço, insira as informações");
                    System.out.println("\nDigite o código do endereço: ");
                    e.setCodigo(inputInt.nextInt());
                    System.out.println("Digite o logradouro: ");
                    e.setLogradouro(inputStr.next());
                    System.out.println("Digite o Código de Endereçamento Postal: ");
                    e.setCep(inputStr.next());
                    System.out.println("Digite o bairro: ");
                    e.setBairro(inputStr.next());
                    System.out.println("Digite a cidade: ");
                    e.setCidade(inputStr.next());
                    System.out.println("Digite o estado: ");
                    e.setEstado(inputStr.next());
                    System.out.println("Digite a Unidade Federativa: ");
                    e.setUf(inputStr.next());
                    System.out.println("Digite o número do endereço: ");
                    e.setNumero(inputInt.next());
                    System.out.println("Digite o complemento (Se necessário): ");
                    e.setComplemento(inputStr.next());

                    //Ele chama a função alterarEndereco do objeto EnderecoDao
                    dao.alterarEndereco(e);
                    System.out.println("Endereço alterado com sucesso!");

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 5
                    break;

                case 5:
                    //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
                    System.out.println("\n======EXCLUIR ENDEREÇO======");
                    System.out.println("Infelizmente você quer exluir algum endereço😢");
                    System.out.println("\nDigite o código do endereço: ");
                    e.setCodigo(inputInt.nextInt());

                    //Chama a função excluirEndereço(codigo) e mostra mensagem de êxito
                    dao.excluirEndereco(e.getCodigo());
                    System.out.println("Endereço terminado...");

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 0
                    break;

                case 0:
                    //Exibe mensagem para fechar programa
                    System.out.println("------------------------------");
                    System.out.println("Fechando programa...");

            }
        }
    }
}
