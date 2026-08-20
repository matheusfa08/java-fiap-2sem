package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.Scanner;

//Classe de teste da função consultarContato(x)
public class testBusca {
    public static void main(String[] args) {
        //Leitor para usuários colocarem um ‘ID’ de escolha
        Scanner input = new Scanner(System.in);

        //Instânciando objetos
        Contato c1 = new Contato();
        ContatoDAO dao = new ContatoDAO();

        //Mensagens para o usuário colocar um número de 'ID' a escolha
        System.out.println("\n======BUSCA POR ID======");
        System.out.println("Digite o ID do Contato: ");
        int id = input.nextInt();

        /*Chamando uma função que retorna um objeto ao objeto que instânciamos anteriormente, ou seja, ele ganha os
        valores do objeto criado a partir da função*/
        c1 = dao.consultarContato(id);

        //Chama o objeto que retorna a sua toString()
        System.out.println(c1);
    }
}
