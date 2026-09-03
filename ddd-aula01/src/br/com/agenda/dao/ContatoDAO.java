package br.com.agenda.dao;

import br.com.agenda.modules.Contato;
import br.com.agenda.modules.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Classe responsável pelo CRUD (Create, read, update, delete) de contato
//Ela cria a tabela no banco de dados
public class ContatoDAO {
    //Ela sempre terá o atributo de conexão ao servidor, da classe ConnectionFactory
    private Connection conexao;

    //CRUD - C: CREATE...

    //Esse metodo poderia retornar algo, mas neste caso não
    //Esse metodo cadastrarContato vai sempre receber um objeto de uma classe
    public void cadastrarContato(Contato contato) {

        //Ele vai fazer uma conexão, chamando o metodo da ConnectionFactory, para iniciar a minha Query
        conexao = ConnectionFactory.obterConexao();

        //Ele vai tentar montar a instrução SQL e vai mandar para a DB
        PreparedStatement comandoSql = null;

        /*É importante tratar erros nesse ponto
        Aqui ele vai tentar mandar a instrução para o DB*/
        try {

            /*Como fazer um insert em SQL, aqui no java:
            Insert into 'tabela'('chaves')
            values('?')*/
            //PS: Código se refere ao código de endereço
            String sql = "insert into tbl_contato(id_contato,nome_contato,celular_contato,email_contato," +
                    "instagram,tipo, codigo)" +
                    "values(?,?,?,?,?,?,?)";

            //Para começar o preparo para mandar o comando ao DB
            comandoSql = conexao.prepareStatement(sql);

            /*Para cada novo valor que for inserida, precisamos falar que o comando recebe um tipo e esse tipo
            recebe o index do valor e o valor*/
            comandoSql.setInt(1, contato.getIdContato());
            comandoSql.setString(2, contato.getNomeContato());
            comandoSql.setString(3, contato.getCelularContato());
            comandoSql.setString(4, contato.getEmailContato());
            comandoSql.setString(5, contato.getInstagram());
            comandoSql.setString(6, contato.getTipo());
            //Ele pega o código de endereço
            comandoSql.setInt(7, contato.getEndereco().getCodigo());

            //Agora pecisamos falar para o DB que queremos executar esse comando SQL, usando esse comando
            comandoSql.executeUpdate();

            //Vamos fechar o comando
            comandoSql.close();

            //E vamos fechar essa conexão, para ela não ficar aberta sempre
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Agora, vamos fazer uma relação N -> N e para isso precisaremos uma terceira tabela.
    public void cadastrarContatoEnderecoEntrega(Contato contato) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        //O Driver do JDBC trabalha com auto-commit, teremos que desativar, e para isso, use:
        try {
            conexao.setAutoCommit(false);
            String sql = "insert into tbl_contato(id_contato,nome_contato,celular_contato,email_contato," +
                    "instagram,tipo, codigo)" +
                    "values(?,?,?,?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, contato.getIdContato());
            ps.setString(2, contato.getNomeContato());
            ps.setString(3, contato.getCelularContato());
            ps.setString(4, contato.getEmailContato());
            ps.setString(5, contato.getInstagram());
            ps.setString(6, contato.getTipo());
            ps.setInt(7, contato.getEndereco().getCodigo());
            ps.executeUpdate();
            ps.close();

            if (contato.getEnderecos() != null) {
                for (Endereco endereco : contato.getEnderecos()) {
                    PreparedStatement psEntrega = conexao.prepareStatement("insert into tbl_contato_endereco_entrega" +
                            "(id_contato, id_endereco) values (?,?)");
                    psEntrega.setInt(1, contato.getIdContato());
                    psEntrega.setInt(2, endereco.getCodigo());
                    psEntrega.executeUpdate();
                    psEntrega.close();
                }
            }

            conexao.commit(); //Confirma a transação
        } catch (SQLException e) {
            try{
                conexao.rollback(); //Desfaz a transação em caso de erro
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace(); //Erros comuns
        } finally {
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//Agora, vamos fazer um pequeno teste. Vá para TestCadastro

//CRUD = C: CREATE - R: READ...

/*Como fazer um select em SQL:
select ('campos') from 'tabela'

Mas desse modo, ele veria a TABELA INTEIRA, então usamos o 'where' para filtrar informações. Como, por exemplo:
select ('campos') from 'tabela' where 'campo' = 'condição'

No Java, ele é um pouco diferente dos outros comandos*/

    //Vamos criar uma função para consultar os valores
    public Contato consultarContato(int idContato) {
        //Começamos como anteriormente, abrindo uma conexão e abrindo um Statement
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Instanciamos um objeto que vai receber os valores da busca
        Contato contato = new Contato();

        //Precisamos de um objeto EnderecoDAO pois queremos trazer todos os objetos
        EnderecoDAO dao = new EnderecoDAO();

        try {
            ps = conexao.prepareStatement("select * from tbl_contato where id_contato = ?");
            ps.setInt(1, idContato);

            //Aqui entra a maior diferença entre o Create e o Read

            //Ele vai executar o Query e vai jogar para um ResultSet na minha memória
            ResultSet rs = ps.executeQuery();

            /*Ele vai pegar esse conteúdo, desde o cabeçalho, então se a próxima linha tiver algo, vai pegar e colocar
            nesse objeto 'contato', nos seus valores, direto, ou pelo nome da coluna, ou pelo index*/
            if (rs.next()) {
                contato.setIdContato(rs.getInt("id_contato"));
                contato.setNomeContato(rs.getString("nome_contato"));
                contato.setCelularContato(rs.getString("celular_contato"));
                contato.setEmailContato(rs.getString("email_contato"));
                contato.setInstagram(rs.getString("instagram"));
                contato.setTipo(rs.getString("tipo"));
                //Os códigos a seguir poderiam ser feitos em uma linha só
                int codigo = rs.getInt("codigo");
                Endereco endereco = new Endereco();
                endereco = dao.consultarEndereco(codigo);
                contato.setEndereco(endereco);
                //Ps: N -> 1
            }

            //E voltamos ao normal, fechando o 'Statement' e a conexão
            ps.close();
            conexao.close();

            //E então ela finaliza retornando um objeto
            return contato;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//Buscar por ID atualizado N -> N
    public Contato buscarPorIdAtt(int Id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Contato contato = new Contato();
        EnderecoDAO dao = new EnderecoDAO();
        try{
            ps = conexao.prepareStatement("select * from tbl_contato where id_contato = ?");
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato.setIdContato(rs.getInt("id_contato"));
                contato.setNomeContato(rs.getString("nome_contato"));
                contato.setCelularContato(rs.getString("celular_contato"));
                contato.setEmailContato(rs.getString("email_contato"));
                contato.setInstagram(rs.getString("instagram"));
                contato.setTipo(rs.getString("tipo"));
                int codigoEnderecoP = rs.getInt("codigo");
                Endereco endereco = new Endereco();
                endereco = dao.consultarEndereco(codigoEnderecoP);
                contato.setEndereco(endereco);

                //Verifico a tabela para ver se há alguém na tabela endereco_entrega (N -> N)
                PreparedStatement psEntrega = conexao.prepareStatement("select id_endereco from " +
                        "tbl_contato_endereco_entrega where id_contato = ?");
                psEntrega.setInt(1, Id);
                ResultSet rsEntrega = psEntrega.executeQuery();
                List<Endereco> enderecosEntrega = new ArrayList<>();
                while (rsEntrega.next()) {
                    Endereco enderecoEntrega = dao.consultarEndereco(rsEntrega.getInt("id_endereco"));
                    enderecosEntrega.add(enderecoEntrega);
                }
                contato.setEnderecos(enderecosEntrega);
                rsEntrega.close();
                psEntrega.close();
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return contato;
    }

//Agora vamos testar, vá para testBusca

    public List<Contato> listarContatos(){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Contato> contatos = new ArrayList<>();

        try{
            //Aqui, pegamos todas as linhas da tabela com esse código SQL e ganhamos a lista ResultSet
            ps = conexao.prepareStatement("select * from tbl_contato");
            ResultSet rs = ps.executeQuery();

            /*Enquanto essa lista tiver uma linha cheia de informações, ela vai instânciar um objeto atribui os
            itens dessa lista a esse objeto, e uma lista guarda esses objetos*/
            while (rs.next()){
                Contato c = new Contato();
                c.setIdContato(rs.getInt(1));
                c.setNomeContato(rs.getString(2));
                c.setCelularContato(rs.getString(3));
                c.setEmailContato(rs.getString(4));
                c.setInstagram(rs.getString(5));
                c.setTipo(rs.getString(6));
                contatos.add(c);
            }

            //Fecho as conexões e encerro de escrever o meu sql.
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Como ele vem de um tipo ele retorna um objeto desse tipo
        return contatos;
    }

//CRUD = C: CREATE - R: READ - U: UPDATE...

    /*Como fazer uma alteração num banco de dados:
     *
     * update tabela
     * set campo = valor [,campon = valorn]
     *
     *
     * Mas aqui em cima você altera a tabela INTEIRA
     * Vamos ver como consertar isso:
     *
     * update tabela
     * set campo = valor [,campon = valorn]
     * where campox = valorx
     *
     * Desse modo, ele alterará apenas os itens da linha que o valorx no campox condiz
     *
     * Agora vamos fazer na marra: */

    //Vamos criar um metodo/função para fazer o update, a alteração com base um ID
    public void alterarContato(Contato contato) {

        //Abrimos a conexão e preparamos o código SQL
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Usamos o código SQL apresentado anteriormente e substituimos os valores que estão com '?' com os setString()
        try {
            String sql = "update tbl_contato set nome_contato = ?, celular_contato = ?, email_contato = ?," +
                    "instagram = ?, tipo = ? where id_contato = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNomeContato());
            ps.setString(2, contato.getCelularContato());
            ps.setString(3, contato.getEmailContato());
            ps.setString(4, contato.getInstagram());
            ps.setString(5, contato.getTipo());
            ps.setInt(6, contato.getIdContato());

            //Como não devolve nada, não tem return, ele usa executeUpdate()
            ps.executeUpdate();

            //Fecho as conexões e deixo de declarar um código SQL
            ps.close();
            conexao.close();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*CRUD = C: CREATE - R: READ - U: UPDATE - D: DELETE
*
* Para deletar algo da tabela use:
*
* delete from tabela
*
* Infelizmente, ele acaba com TODOS os dados
* Mas para consertar isso, use:
*
* delete from tabela where condição = ?
*
* PS: EXCLUSÕES SÃO APENAS LÓGICAS, OU SEJA, COLOCADA UMA FLAG NA LINHA
* PPS: EXCLUSÕES FÍSICAS SÃO RARAS
*
* Vamos praticar: */

    //Criada função para excluir uma linha da db conforme a ID
    public void excluirContato(int id){

        //Aberta a conexão e preparando a frase sql
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Usamos o código acima para criar esse código sql e substituimos o '?' pelo 'id' que fornecemos
        try{
            ps = conexao.prepareStatement("delete from tbl_contato where id_contato = ?");
            ps.setInt(1, id);

            //Executamos o comando sql no db
            ps.executeUpdate();

            //Deixamos de preparar uma linha SQL
            ps.close();
            conexao.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*Mas e se quisermos colocar endereço? Múltiplas pessoas podem ter o mesmo endereço. Bem, agora está na hora de
    relacionar as tabelas. Crie a tabela Endereco_agenda e faça a classe Endereco e EnderecoDao que recebera o CRUD
    de Endereco*/
}