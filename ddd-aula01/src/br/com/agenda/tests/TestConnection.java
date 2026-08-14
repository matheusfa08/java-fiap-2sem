package br.com.agenda.tests;

import br.com.agenda.dao.ConnectionFactory;

public class TestConnection {
    static void main(){
        System.out.println("Teste de conexão com o banco de dados...");
        if(ConnectionFactory.obterConexao() == null){
            System.out.println("⚠️ Erro ao estabelecer conexão ⚠️");
        }
        else{
            System.out.println("✅Conexão estabelecida✅");
        }
    }
}
