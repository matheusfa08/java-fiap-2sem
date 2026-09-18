package br.com.fiap.cineFiap.dao;

import br.com.fiap.cineFiap.enums.CategoriaFilmeEnum;
import br.com.fiap.cineFiap.enums.ClassificacaoIndicativaEnum;
import br.com.fiap.cineFiap.enums.SimNaoEnum;
import br.com.fiap.cineFiap.models.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private Connection conexao;

    public void cadastrarFilme(Filme filme) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "insert into tbl_filme (id_filme, tx_nome, nr_duracao, tp_categoria, tp_classificacao, nr_ano, " +
                    "tx_capa, tx_diretor, tx_elenco, tx_descricao, nr_avaliacao, tp_emcartaz)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, filme.getId());
            ps.setString(2, filme.getNome());
            ps.setInt(3, filme.getDuracao());
            ps.setString(4, filme.getCategoria().toString());
            ps.setString(5, filme.getClassificacao().toString());
            ps.setInt(6, filme.getAno());
            ps.setString(7, filme.getCapa());
            ps.setString(8, filme.getDiretor());
            ps.setString(9, filme.getElenco());
            ps.setString(10, filme.getDescricao());
            ps.setDouble(11, filme.getAvaliacao());
            ps.setString(12, filme.getEmCartaz().toString());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Filme consultarFilme(long id) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Filme f = new Filme();
        try {
            ps = conexao.prepareStatement("select * from tbl_filme where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f.setId(rs.getLong(1));
                f.setNome(rs.getString(2));
                f.setDuracao(rs.getInt(3));
                f.setCategoria(CategoriaFilmeEnum.valueOf(rs.getString(4)));
                f.setClassificacao(ClassificacaoIndicativaEnum.valueOf(rs.getString(5)));
                f.setAno(rs.getInt(6));
                f.setCapa(rs.getString(7));
                f.setDiretor(rs.getString(8));
                f.setElenco(rs.getString(9));
                f.setDescricao(rs.getString(10));
                f.setAvaliacao(rs.getDouble(11));
                f.setEmCartaz(SimNaoEnum.valueOf(rs.getString(12)));
            }
            ps.close();
            conexao.close();
            return f;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Filme> listarFilmes(){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        List<Filme> filmes = new ArrayList<>();
        try{
            ps = conexao.prepareStatement("select * from tbl_filme");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Filme f = new Filme();
                f.setId(rs.getLong(1));
                f.setNome(rs.getString(2));
                f.setDuracao(rs.getInt(3));
                f.setCategoria(CategoriaFilmeEnum.valueOf(rs.getString(4)));
                f.setClassificacao(ClassificacaoIndicativaEnum.valueOf(rs.getString(5)));
                f.setAno(rs.getInt(6));
                f.setCapa(rs.getString(7));
                f.setDiretor(rs.getString(8));
                f.setElenco(rs.getString(9));
                f.setDescricao(rs.getString(10));
                f.setAvaliacao(rs.getDouble(11));
                f.setEmCartaz(SimNaoEnum.valueOf(rs.getString(12)));
                filmes.add(f);
            }
            ps.close();
            conexao.close();
        } catch (SQLException e) {throw new RuntimeException(e);}
        return filmes;
    }

    public void alterarFilme(Filme filme) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "update tbl_filme set tx_nome = ?, nr_duracao = ?, tp_categoria = ?, tp_classificacao = ?, nr_ano = ?," +
                    "tx_capa = ?, tx_diretor = ?, tx_elenco = ?, tx_descricao = ?, nr_avaliacao = ?, tp_emcartaz = ? where id_filme = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, filme.getNome());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getCategoria().toString());
            ps.setString(4, filme.getClassificacao().toString());
            ps.setInt(5, filme.getAno());
            ps.setString(6, filme.getCapa());
            ps.setString(7, filme.getDiretor());
            ps.setString(8, filme.getElenco());
            ps.setString(9, filme.getDescricao());
            ps.setDouble(10, filme.getAvaliacao());
            ps.setString(11, filme.getEmCartaz().toString());
            ps.setLong(12, filme.getId());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        }
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public void excluirFilme(long id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            ps = conexao.prepareStatement("delete from tbl_filme where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        }
        catch (SQLException e) {throw new RuntimeException(e);}
    }
}