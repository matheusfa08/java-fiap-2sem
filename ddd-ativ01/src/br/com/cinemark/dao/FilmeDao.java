//Nomes: Matheus Ferreira Antônio e João Vitor Cruz de Lima
//RMs: 570933 e 571277

package br.com.cinemark.dao;

import br.com.cinemark.modules.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo CRUD (Create, read, update, delete) e pelas regras de negócio/consultas de Filme.
 * Ela é relacionada à tabela no banco de dados referente à TBL_FILME
 */
public class FilmeDao {
    private Connection conexao;

    /**
     * Cadastra um novo filme no banco de dados.
     * Regra de negócio: o filme só pode ser cadastrado se possuir
     * duração maior que zero, classificação indicativa e categoria informadas.
     *
     * @param filme objeto Filme a ser cadastrado
     * @throws IllegalArgumentException caso alguma regra de negócio não seja atendida
     */
    public void cadastrarFilme(Filme filme) {
        if (filme.getDuracao() <= 0) {
            throw new IllegalArgumentException("A duração do filme deve ser maior que zero.");
        }
        if (filme.getClassificacao() == null || filme.getClassificacao().trim().isEmpty()) {
            throw new IllegalArgumentException("O filme deve possuir classificação indicativa.");
        }
        if (filme.getCategoria() == null || filme.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("O filme deve possuir categoria.");
        }
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            String sql = "INSERT INTO TBL_FILME (ID_FILME, TX_NOME, NR_DURACAO, TP_CATEGORIA, " +
                    "TP_CLASSIFICACAO, NR_ANO, TX_CAPA, TX_DIRETOR, TX_ELENCO, TX_DESCRICAO, " +
                    "NR_AVALIACAO, CHK_EM_CARTAZ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setLong(1, filme.getId());
            comandoSql.setString(2, filme.getNome());
            comandoSql.setInt(3, filme.getDuracao());
            comandoSql.setString(4, filme.getCategoria());
            comandoSql.setString(5, filme.getClassificacao());
            comandoSql.setInt(6, filme.getAno());
            comandoSql.setString(7, filme.getCapa());
            comandoSql.setString(8, filme.getDiretor());
            comandoSql.setString(9, filme.getElenco());
            comandoSql.setString(10, filme.getDescricao());
            comandoSql.setDouble(11, filme.getAvaliacao());
            comandoSql.setString(12, filme.getEmCartaz());
            comandoSql.executeUpdate();
            comandoSql.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lista todos os filmes cadastrados.
     *
     * @return lista com todos os filmes
     */
    public List<Filme> listar() {
        List<Filme> filmes = new ArrayList<>();

        try {
            conexao = ConnectionFactory.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM TBL_FILME"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();

                filme.setId(rs.getLong("ID_FILME"));
                filme.setNome(rs.getString("TX_NOME"));
                filme.setDuracao(rs.getInt("NR_DURACAO"));
                filme.setCategoria(rs.getString("TP_CATEGORIA"));
                filme.setClassificacao(rs.getString("TP_CLASSIFICACAO"));
                filme.setAno(rs.getInt("NR_ANO"));
                filme.setCapa(rs.getString("TX_CAPA"));
                filme.setDiretor(rs.getString("TX_DIRETOR"));
                filme.setElenco(rs.getString("TX_ELENCO"));
                filme.setDescricao(rs.getString("TX_DESCRICAO"));
                filme.setAvaliacao(rs.getDouble("NR_AVALIACAO"));
                filme.setEmCartaz(rs.getString("CHK_EM_CARTAZ"));

                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
     * Consulta os dados dos filmes, já cadastrados, por categoria. Se não conter um filme com tal categoria,
     * retorna nada.
     *
     * @param categoria espera uma variável String para comparar com os cadastros*/
    public List<Filme> consultarFilmeCategoria(String categoria) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Filme> filmes = new ArrayList<Filme>();

        try {
            ps = conexao.prepareStatement("select * from filme where nome = ?");
            ps.setString(1, categoria);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setClassificacao(rs.getString("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setCapa(rs.getString("capa"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setElenco(rs.getString("elenco"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setAvaliacao(rs.getDouble("avaliacao"));
                filme.setEmCartaz(rs.getString("emCartaz"));
                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
     * Consulta os dados dos filmes, já cadastrados, que estejam em Cartaz, ou no sistema, como "SIM". Se não tiver
     * filmes em cartaz, ou só com "NÃO" no sistema, retorna nada.
     *
     * @param emCartaz espera uma variável String para comparar com os cadastros*/
    public List<Filme> consultarFilmeEmCartaz(String emCartaz) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Filme> filmes = new ArrayList<Filme>();

        try {
            ps = conexao.prepareStatement("select * from filme where nome = ?");
            ps.setString(1, emCartaz);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setClassificacao(rs.getString("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setCapa(rs.getString("capa"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setElenco(rs.getString("elenco"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setAvaliacao(rs.getDouble("avaliacao"));
                filme.setEmCartaz(rs.getString("emCartaz"));
                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
    * Consulta os dados dos filmes, já cadastrados, por nome. Se não conter um filme com o nome, retorna nada.
    *
    * @param nome espera uma variável String para comparar com os cadastros*/
    public List<Filme> consultarFilmeNome(String nome) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Filme> filmes = new ArrayList<Filme>();

        try {
            ps = conexao.prepareStatement("select * from filme where nome = ?");
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setClassificacao(rs.getString("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setCapa(rs.getString("capa"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setElenco(rs.getString("elenco"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setAvaliacao(rs.getDouble("avaliacao"));
                filme.setEmCartaz(rs.getString("emCartaz"));
                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
     * Consulta os dados dos filmes, já cadastrados, por diretor. Se não conter um filme com o diretor, retorna nada.
     *
     * @param diretor espera uma variável String para comparar com os cadastros*/
    public List<Filme> consultarFilmeDiretor(String diretor) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Filme> filmes = new ArrayList<Filme>();

        try {
            ps = conexao.prepareStatement("select * from filme where nome = ?");
            ps.setString(1, diretor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setClassificacao(rs.getString("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setCapa(rs.getString("capa"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setElenco(rs.getString("elenco"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setAvaliacao(rs.getDouble("avaliacao"));
                filme.setEmCartaz(rs.getString("emCartaz"));
                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
     * Consulta os dados dos filmes, já cadastrados, por ano. Se não conter um filme que tenha lançado no ano em
     * questão, retorna nada.
     *
     * @param ano espera uma variável Int para comparar com os cadastros*/
    public List<Filme> consultarFilmeDiretor(int ano) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Filme> filmes = new ArrayList<Filme>();

        try {
            ps = conexao.prepareStatement("select * from filme where nome = ?");
            ps.setInt(1, ano);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setClassificacao(rs.getString("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setCapa(rs.getString("capa"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setElenco(rs.getString("elenco"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setAvaliacao(rs.getDouble("avaliacao"));
                filme.setEmCartaz(rs.getString("emCartaz"));
                filmes.add(filme);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    /**
     * Altera os dados de um filme já cadastrado, localizando-o pelo ID_FILME.
     *
     * @param filme objeto Filme com os dados atualizados
     */
    public void alterar(Filme filme) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE TBL_FILME SET TX_NOME = ?, NR_DURACAO = ?, TP_CATEGORIA = ?, " +
                    "TP_CLASSIFICACAO = ?, NR_ANO = ?, TX_CAPA = ?, TX_DIRETOR = ?, TX_ELENCO = ?, " +
                    "TX_DESCRICAO = ?, NR_AVALIACAO = ?, CHK_EM_CARTAZ = ? WHERE ID_FILME = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, filme.getNome());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getCategoria());
            ps.setString(4, filme.getClassificacao());
            ps.setInt(5, filme.getAno());
            ps.setString(6, filme.getCapa());
            ps.setString(7, filme.getDiretor());
            ps.setString(8, filme.getElenco());
            ps.setString(9, filme.getDescricao());
            ps.setDouble(10, filme.getAvaliacao());
            ps.setString(11, filme.getEmCartaz());
            ps.setLong(12, filme.getId());
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exclui um filme do banco de dados a partir do seu ID_FILME.
     *
     * @param id identificador do filme a ser excluído
     */
    public void excluir(long id) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement("DELETE FROM TBL_FILME WHERE ID_FILME = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
