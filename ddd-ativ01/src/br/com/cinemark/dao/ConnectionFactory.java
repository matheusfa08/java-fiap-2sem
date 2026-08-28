package br.com.cinemark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que irá estabelecer conexão com os servidores da OracleDB através dos dados de login e do drive
 * */
public class ConnectionFactory {
    /**
     * Metodo que estabelece a canexão com os servidores. Ele vai tentar fazer ela, com dados de login já prontos, e
     * caso dê erro, ele retorna uma mensagem de erro.
     *
     * Ele retornará uma conexão*/
    public static Connection obterConexao(){
        //Criado objeto que não tem nada
        Connection conexao = null;
        try{
            /*
            Usa tratativa de erro "try catch" para iniciar a conexão com o gerenciador do drive. Caso dê errado,
            ele retorna um erro e a conexão continua nula, sem nada.
            */
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm570933",
                    "200308");
            /*
            * Neste caso, já temos um login preparado para questão de testes
            */
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        Como é um metodo fixo, ele tem que retornar algo, e então ele retorna a conexão.
        */
        return conexao;
    }
}
