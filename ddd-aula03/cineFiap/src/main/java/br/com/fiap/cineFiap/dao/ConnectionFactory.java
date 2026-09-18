package br.com.fiap.cineFiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que irá estabelecer conexão com os servidores da OracleDB através dos dados de login e do drive
 * */
public class ConnectionFactory {
    public static Connection obterConexao(){
        Connection conexao = null;
        try{
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm570933",
                    "200308");
        } catch (SQLException e) {throw new RuntimeException(e);}
        return conexao;
    }
}
