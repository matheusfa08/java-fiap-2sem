package br.com.agenda.modules;

public class Contato {

    //Variáveis que remetem aos elementos da tabela (De exemplo) contatos, no banco de dados, para melhor ilustração.
    private int idContato;
    private String nomeContato;
    private String celularContato;
    private String emailContato;
    private String instagram;
    private String tipo;

    //Métodos getters e setters para que esses elementos possam ser acessados e alterados fora da classe.
    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getCelularContato() {
        return celularContato;
    }

    public void setCelularContato(String celularContato) {
        this.celularContato = celularContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Agora, vamos criar um objeto que vai ir até o banco de dados e vai realizar o CRUD (Create, read, update, delete)
    //Para ver, vá até ContatoDAO
}
