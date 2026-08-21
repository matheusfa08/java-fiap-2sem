package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.modules.Contato;

import java.util.ArrayList;
import java.util.List;

public class TestListar {
    static void main(){

        //Vamos instânciar os objetos e a lista de objetos
        Contato c = new Contato();
        ContatoDAO dao = new ContatoDAO();
        List<Contato> contatos = new ArrayList<>();

        //A lista contatos vai receber a função, do objeto dao, listarContatos()
        contatos = dao.listarContatos();

        /*As mensagens são mostradas chamando a lista e para cada objeto dentro da lista, eles vão chamar a
        sua própria toString(), ou se chamar, como preferir*/
        System.out.println("======CONTATOS DA AGENDA======");
        contatos.forEach(System.out::println);

    }
}
