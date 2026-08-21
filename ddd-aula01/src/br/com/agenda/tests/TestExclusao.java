package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.Scanner;

public class TestExclusao {
    static void main(){
        //Aqui está o leitor, para deixar com que o usuário possa digitar
        Scanner inputInt = new Scanner(System.in);

        //Aqui estão as instâncias dos objetos
        Contato c1 = new Contato();
        ContatoDAO dao = new ContatoDAO();

        //Aqui estão as mensagens e códigos para instruir que os utilizadores coloquem as suas informações
        System.out.println("\n======EXCLUIR CONTATO======");
        System.out.println("Infelizmente você quer exluir alguém😢");
        System.out.println("\nDigite o código do contato: ");
        c1.setIdContato(inputInt.nextInt());

        //Chama a função excluirContato(id) e mostra mensagem de êxito
        dao.excluirContato(c1.getIdContato());
        System.out.println("Contato terminado...");
    }
}
