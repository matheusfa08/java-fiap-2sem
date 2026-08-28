package br.com.cinemark.modules;

public class Filme {
    private long id;
    private String nome;
    private int duracao;
    private String categoria;
    private String classificacao;
    private int ano;
    private String capa;
    private String diretor;
    private String elenco;
    private String descricao;
    private double avaliacao;
    private String emCartaz; // "SIM" ou "NAO"

    public void menu(){
        System.out.println("\n======CINEMARK======");
        System.out.println("1 - Cadastrar Filme");
        System.out.println("2 - Listar Filmes");
        System.out.println("3 - Buscar Filme por Categoria");
        System.out.println("4 - Buscar Filmes em Cartaz");
        System.out.println("5 - Buscar Filme por Nome");
        System.out.println("6 - Buscar Filme por Diretor");
        System.out.println("7 - Alterar Contato por ID");
        System.out.println("8 - Remover Contato");
        System.out.println("0 - Sair");
        System.out.println("------------------------------");
        System.out.println("Digite  uma opção: ");
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                " - Nome: " + nome +
                " - Duração: " + duracao + " min" +
                " - Categoria: " + categoria +
                " - Classificação: " + classificacao +
                " - Ano: " + ano +
                " - Diretor: " + diretor +
                " - Elenco: " + elenco +
                " - Descrição: " + descricao +
                " - Avaliação: " + avaliacao +
                " - Em cartaz: " + emCartaz +
                " - Capa: " + capa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getEmCartaz() {
        return emCartaz;
    }

    public void setEmCartaz(String emCartaz) {
        this.emCartaz = emCartaz;
    }
}
