package br.com.cinemark.tests;

import br.com.cinemark.dao.FilmeDao;
import br.com.cinemark.modules.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMenuFilmes {
    public static void main(String[] args) {
        //Criando os leitores para que o usuário possa inserir info
        Scanner inputInt = new Scanner(System.in);
        Scanner inputStr = new Scanner(System.in);
        Scanner inputDou = new Scanner(System.in);

        //Instânciando os objetos que serão usados e a lista
        Filme f = new Filme();
        FilmeDao dao = new FilmeDao();
        List<Filme> filmes = new ArrayList<>();

        //Criando a variável op (Opção) para que a repetição while a seguir seja possível
        int op = -1;

        /*Repetição while. Enquanto op for diferente de zero, ele mostrará o menu e dará o poder do usuário escolher
        uma opção*/
        while (op != 0){
            f.menu();
            op = inputInt.nextInt();

            /*Switch case, se usa nessas situações de menu ou quando preciso que algo aconteça em algum caso. Nesse
            caso, ele usa a variável op para ver as situações*/
            switch (op) {
                case 1:

                    System.out.println("\n======CADASTRO DE FILMES======");
                    System.out.println("Olá! Vamos cadastrar um Filme, insira as informações");

                    System.out.println("\nDigite o ID do filme: ");
                    f.setId(inputInt.nextInt());

                    System.out.println("Digite o nome do filme: ");
                    f.setNome(inputStr.nextLine());

                    System.out.println("Digite a duração do filme (em minutos): ");
                    f.setDuracao(inputInt.nextInt());

                    System.out.println("Digite a categoria do filme (ex: Ação, Comédia, Drama): ");
                    f.setCategoria(inputStr.nextLine());

                    System.out.println("Digite a classificação indicativa (ex: Livre, 12, 14, 16, 18): ");
                    f.setClassificacao(inputStr.nextLine());

                    System.out.println("Digite o ano de lançamento: ");
                    f.setAno(inputInt.nextInt());

                    System.out.println("Digite a URL/caminho da capa do filme: ");
                    f.setCapa(inputStr.nextLine());

                    System.out.println("Digite o nome do diretor: ");
                    f.setDiretor(inputStr.nextLine());

                    System.out.println("Digite o elenco principal: ");
                    f.setElenco(inputStr.nextLine());

                    System.out.println("Digite a descrição/sinopse do filme: ");
                    f.setDescricao(inputStr.nextLine());

                    System.out.println("Digite a avaliação do filme (0 a 10): ");
                    f.setAvaliacao(inputInt.nextInt());

                    System.out.println("O filme está em cartaz? (SIM/NÃO): ");
                    f.setEmCartaz(inputStr.nextLine());

                    System.out.println("\nFilme cadastrado com sucesso!");

                    dao.cadastrarFilme(f);

                    break;

                case 2:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA POR CATEGORIA======");
                    System.out.println("Digite uma categoria: ");
                    String categoria = inputStr.nextLine();

                    /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja,
                    ele ganha os valores do objeto criado a partir da função*/
                    filmes = dao.consultarFilmeCategoria(categoria);

                    //Chama o objeto que retorna a sua toString()
                    System.out.println(filmes);

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 3
                    break;

                case 3:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA EM CARTAZ======");

                    /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja,
                    ele ganha os valores do objeto criado a partir da função*/
                    filmes = dao.consultarFilmeEmCartaz("SIM");

                    //Chama o objeto que retorna a sua toString()
                    System.out.println(filmes);

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 3
                    break;

                case 4:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA POR NOME======");
                    System.out.println("Digite o nome do filme: ");
                    String nome = inputStr.nextLine();

                    /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja,
                    ele ganha os valores do objeto criado a partir da função*/
                    filmes = dao.consultarFilmeNome(nome);

                    //Chama o objeto que retorna a sua toString()
                    System.out.println(filmes);

                    //Esse break impede que ele execute a linha de código abaixo, que é a case 3
                    break;

                case 5:

                    //Mensagens para o usuário colocar um número de 'ID' a escolha
                    System.out.println("\n======BUSCA POR DIRETOR======");
                    System.out.println("Digite uma diretor: ");
                    String diretor = inputStr.nextLine();

                    filmes = dao.consultarFilmeDiretor(diretor);

                    System.out.println(filmes);

                    break;

                case 6:
                    filmes = dao.listar();

                    System.out.println("\n======FILMES======");
                    filmes.forEach(System.out::println);

                    break;

                case 7:
                    System.out.println("\n======Alteração DE FILMES======");
                    System.out.println("Olá! Vamos Alterar um Filme, insira as informações");

                    System.out.println("\nDigite o ID do filme: ");
                    f.setId(inputInt.nextInt());

                    System.out.println("Digite o nome do filme: ");
                    f.setNome(inputStr.nextLine());

                    System.out.println("Digite a duração do filme (em minutos): ");
                    f.setDuracao(inputInt.nextInt());

                    System.out.println("Digite a categoria do filme (ex: Ação, Comédia, Drama): ");
                    f.setCategoria(inputStr.nextLine());

                    System.out.println("Digite a classificação indicativa (ex: Livre, 12, 14, 16, 18): ");
                    f.setClassificacao(inputStr.nextLine());

                    System.out.println("Digite o ano de lançamento: ");
                    f.setAno(inputInt.nextInt());

                    System.out.println("Digite a URL/caminho da capa do filme: ");
                    f.setCapa(inputStr.nextLine());

                    System.out.println("Digite o nome do diretor: ");
                    f.setDiretor(inputStr.nextLine());

                    System.out.println("Digite o elenco principal: ");
                    f.setElenco(inputStr.nextLine());

                    System.out.println("Digite a descrição/sinopse do filme: ");
                    f.setDescricao(inputStr.nextLine());

                    System.out.println("Digite a avaliação do filme (0 a 10): ");
                    f.setAvaliacao(inputInt.nextInt());

                    System.out.println("O filme está em cartaz? (SIM/NÃO): ");
                    f.setEmCartaz(inputStr.nextLine());

                    System.out.println("\nFilme alterado com sucesso!");

                    dao.cadastrarFilme(f);

                    break;

                case 8:
                    System.out.println("\n======EXCLUIR FILME======");
                    System.out.println("\nDigite o id do filme: ");
                    f.setId(inputInt.nextInt());

                    dao.excluir(f.getId());
                    System.out.println("Filme terminado...");

                    break;

                case 0:
                    System.out.println("------------------------------");
                    System.out.println("Fechando programa...");

            }
        }
    }
}
