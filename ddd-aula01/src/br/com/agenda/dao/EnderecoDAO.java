package br.com.agenda.dao;

import br.com.agenda.modules.Contato;
import br.com.agenda.modules.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Criamos a classe responsável por dar o CRUD ao Endereco.
public class EnderecoDAO {

    //Iremos copiar o código do ContatoDAO para uma criação mais rápida e dinâmica

    //Ela sempre terá o atributo de conexão ao servidor, da classe ConnectionFactory
    private Connection conexao;

    //CRUD - C: CREATE...

    //Esse metodo poderia retornar algo, mas neste caso não
    //Esse metodo cadastrarEndereco vai sempre receber um objeto de uma classe
    public void cadastrarEndereco(Endereco endereco) {

        //Ele vai fazer uma conexão, chamando o metodo da ConnectionFactory, para iniciar a minha Query
        conexao = ConnectionFactory.obterConexao();

        //Ele vai tentar montar a instrução SQL e vai mandar para a DB
        PreparedStatement ps = null;

        /*É importante tratar erros nesse ponto
        Aqui ele vai tentar mandar a instrução para o DB*/
        try {

            /*Como fazer um insert em SQL, aqui no java:
            Insert into 'tabela'('chaves')
            values('?')*/
            String sql = "insert into endereco_agenda(codigo, logradouro, cep, bairro, cidade, estado, uf, " +
                    "numero, complemento) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //Para começar o preparo para mandar o comando ao DB
            ps = conexao.prepareStatement(sql);

            /*Para cada novo valor que for inserida, precisamos falar que o comando recebe um tipo e esse tipo
            recebe o index do valor e o valor*/
            ps.setInt(1, endereco.getCodigo());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getCep());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setString(7, endereco.getUf());
            ps.setString(8, endereco.getNumero());
            ps.setString(9, endereco.getComplemento());

            //Agora pecisamos falar para o DB que queremos executar esse comando SQL, usando esse comando
            ps.executeUpdate();

            //Vamos fechar o comando
            ps.close();

            //E vamos fechar essa conexão, para ela não ficar aberta sempre
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public Endereco consultarEndereco(int codigo) {
        //Começamos como anteriormente, abrindo uma conexão e abrindo um Statement
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Instanciamos um objeto que vai receber os valores da busca
        Endereco endereco = new Endereco();

        try {
            ps = conexao.prepareStatement("select * from endereco_agenda where codigo = ?");
            ps.setInt(1, codigo);

            //Aqui entra a maior diferença entre o Create e o Read

            //Ele vai executar o Query e vai jogar para um ResultSet na minha memória
            ResultSet rs = ps.executeQuery();

            /*Ele vai pegar esse conteúdo, desde o cabeçalho, então se a próxima linha tiver algo, vai pegar e colocar
            nesse objeto 'contato', nos seus valores, direto, ou pelo nome da coluna, ou pelo index*/
            if (rs.next()) {
                endereco.setCodigo(rs.getInt("codigo"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setUf(rs.getString("uf"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
            }

            //E voltamos ao normal, fechando o 'Statement' e a conexão
            ps.close();
            conexao.close();

            //E então ela finaliza retornando um objeto
            return endereco;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//Agora vamos testar, vá para testBusca

    public List<Endereco> listarEnderecos() {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Endereco> enderecos = new ArrayList<>();

        try{
            //Aqui, pegamos todas as linhas da tabela com esse código SQL e ganhamos a lista ResultSet
            ps = conexao.prepareStatement("select * from endereco_agenda");
            ResultSet rs = ps.executeQuery();

            /*Enquanto essa lista tiver uma linha cheia de informações, ela vai instânciar um objeto atribui os
            itens dessa lista a esse objeto, e uma lista guarda esses objetos*/
            while (rs.next()){
                Endereco e = new Endereco();
                e.setCodigo(rs.getInt("codigo"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setCep(rs.getString("cep"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                e.setUf(rs.getString("uf"));
                e.setNumero(rs.getString("numero"));
                e.setComplemento(rs.getString("complemento"));
                enderecos.add(e);
            }

            //Fecho as conexões e encerro de escrever o meu sql.
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Como ele vem de um tipo ele retorna um objeto desse tipo
        return enderecos;
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
    public void alterarEndereco(Endereco endereco) {

        //Abrimos a conexão e preparamos o código SQL
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Usamos o código SQL apresentado anteriormente e substituimos os valores que estão com '?' com os setString()
        try {
            String sql = "update endereco_agenda set logradouro = ?, cep = ?, bairro = ?, cidade = ?" +
                    "estado = ?,  uf = ?, numero = ?, complemento = ? where codigo = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getEstado());
            ps.setString(6, endereco.getUf());
            ps.setString(7, endereco.getNumero());
            ps.setString(8, endereco.getComplemento());
            ps.setInt(9, endereco.getCodigo());

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
    public void excluirEndereco(int codigo){

        //Aberta a conexão e preparando a frase sql
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Usamos o código acima para criar esse código sql e substituimos o '?' pelo 'id' que fornecemos
        try{
            ps = conexao.prepareStatement("delete from endereco_agenda where codigo = ?");
            ps.setInt(1, codigo);

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


}
