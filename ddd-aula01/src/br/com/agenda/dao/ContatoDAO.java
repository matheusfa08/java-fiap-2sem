package br.com.agenda.dao;

import br.com.agenda.modules.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;

//Classe responsável pelo CRUD (Create, read, update, delete) de contato
public class ContatoDAO {
    private Connection conexao;

    public void cadastrarContato(Contato contato){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSql = null;

        try{
            String sql = "insert into tbl_contato ()";
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
