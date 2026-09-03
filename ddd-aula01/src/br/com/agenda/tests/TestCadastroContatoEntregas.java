package br.com.agenda.tests;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.dao.EnderecoDAO;
import br.com.agenda.modules.Contato;
import br.com.agenda.modules.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestCadastroContatoEntregas {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        Contato contato = new Contato();
        ContatoDAO dao = new ContatoDAO();

        List<Endereco> enderecosEntrega = new ArrayList<>();

        System.out.println("Cadastro de contatos");
        System.out.println("==============================");
        System.out.println("Digite o código: ");
        contato.setIdContato(leitorNum.nextInt());
        System.out.println("Digite o nome: ");
        contato.setNomeContato(leitor.nextLine());
        System.out.println("Digite o celular:");
        contato.setCelularContato(leitor.nextLine());
        System.out.println("Digite o email:");
        contato.setEmailContato(leitor.nextLine());
        System.out.println("Digite o instagram:");
        contato.setInstagram(leitor.nextLine());
        System.out.println("Tipo do contato: AMIGO, FAMILIAR, PROFISSIONAL");
        contato.setTipo(leitor.nextLine());
        System.out.println("Digite o código do endereço: ");
        int codigo = leitorNum.nextInt();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco = new Endereco();
        endereco = enderecoDAO.consultarEndereco(codigo);
        if(endereco == null)
            System.out.println("Endereço não cadastro");
        else
            contato.setEndereco(endereco);
        System.out.println("Gostaria de cadastrar endereços de entrega? [1] - Sim | [2] - Não");
            int op = leitorNum.nextInt();
            while(op == 1) {
                System.out.println("Digite o código do endereço de entrega: ");
                int codigoEntrega = leitorNum.nextInt();
                Endereco enderecoEntrega = enderecoDAO.consultarEndereco(codigoEntrega);
                if (enderecoEntrega == null) {
                    System.out.println("Endereco não cadastrado!");
                } else {
                    enderecosEntrega.add(enderecoEntrega);
                    System.out.println("Endereço de entrega adicionado com sucesso!");
                }
                System.out.println("Gostaria de cadastrar outro endereço? [1] - Sim | [2] - Não");
                op = leitorNum.nextInt();
            }
                contato.setEnderecos(enderecosEntrega);
                dao.cadastrarContatoEnderecoEntrega(contato);
        System.out.println("Contato foi adicionado com sucesso!");
    }
}