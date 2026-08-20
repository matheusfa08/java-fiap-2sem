package br.com.agenda.dao;

import br.com.agenda.modules.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Classe responsável pelo CRUD (Create, read, update, delete) de contato
//Ela cria a tabela no banco de dados
public class ContatoDAO {
    //Ela sempre terá o atributo de conexão ao servidor, da classe ConnectionFactory
    private Connection conexao;

    //CRUD - C: CREATE...

    //Esse metodo poderia retornar algo, mas neste caso não
    //Esse metodo cadastrarContato vai sempre receber um objeto de uma classe
    public void cadastrarContato(Contato contato){

        //Ele vai fazer uma conexão, chamando o metodo da ConnectionFactory, para iniciar a minha Query
        conexao = ConnectionFactory.obterConexao();

        //Ele vai tentar montar a instrução SQL e vai mandar para a DB
        PreparedStatement comandoSql = null;

        /*É importante tratar erros nesse ponto
        Aqui ele vai tentar mandar a instrução para o DB*/
        try{

            /*Como fazer um insert em SQL, aqui no java:
            Insert into 'tabela'('chaves')
            values('?')*/
            String sql = "insert into tbl_contato(id_contato,nome_contato,celular_contato,email_contato," +
                    "instagram,tipo)" +
                    "values(?,?,?,?,?,?)";

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

            //Agora pecisamos falar para o DB que queremos executar esse comando SQL, usando esse comando
            comandoSql.executeUpdate();

            //Vamos fechar o comando
            comandoSql.close();

            //E vamos fechar essa conexão, para ela não ficar aberta sempre
            conexao.close();
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//Agora, vamos fazer um pequeno teste. Vá para TestCadastro

//CRUD - C: CREATE - R: READ...

/*Como fazer um select em SQL:
select ('campos') from 'tabela'

Mas desse modo, ele veria a TABELA INTEIRA, então usamos o 'where' para filtrar informações. Como, por exemplo:
select ('campos') from 'tabela' where 'campo' = 'condição'

No Java, ele é um pouco diferente dos outros comandos*/

    //Vamos criar uma função para consultar os valores
    public Contato consultarContato(int idContato){
        //Começamos como anteriormente, abrindo uma conexão e abrindo um Statement
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        //Instanciamos um objeto que vai receber os valores da busca
        Contato contato = new Contato();

        try{
            ps = conexao.prepareStatement("select * from tbl_contato where id_contato = ?");
            ps.setInt(1, idContato);

            //Aqui entra a maior diferença entre o Create e o Read

            //Ele vai executar o Query e vai jogar para um ResultSet na minha memória
            ResultSet rs = ps.executeQuery();

            /*Ele vai pegar esse conteúdo, desde o cabeçalho, então se a próxima linha tiver algo, vai pegar e colocar
            nesse objeto 'contato', nos seus valores, direto, ou pelo nome da coluna, ou pelo index*/
            if(rs.next()){
                contato.setIdContato(rs.getInt("id_contato"));
                contato.setNomeContato(rs.getString("nome_contato"));
                contato.setCelularContato(rs.getString("celular_contato"));
                contato.setEmailContato(rs.getString("email_contato"));
                contato.setInstagram(rs.getString("instagram"));
                contato.setTipo(rs.getString("tipo"));
            }

            //E voltamos ao normal, fechando o 'Statement' e a conexão
            ps.close();
            conexao.close();

            //E então ela finaliza retornando um objeto
            return contato;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//Agora vamos testar, vá para testBusca