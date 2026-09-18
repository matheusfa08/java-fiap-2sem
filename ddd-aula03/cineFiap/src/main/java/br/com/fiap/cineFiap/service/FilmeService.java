package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.FilmeDAO;
import br.com.fiap.cineFiap.models.Filme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeDAO filmeDAO;

    public FilmeService() {filmeDAO = new FilmeDAO();}

    public List<Filme> listar() {return filmeDAO.listarFilmes();}

    public Filme ConsultarPorId(long id){
        var filme = filmeDAO.consultarFilme(id);
        return filme;
    }

    public void cadastrar(Filme filme) {
        if(filme.getNome() != null) {
            filmeDAO.cadastrarFilme(filme);
        } else {
            throw new RuntimeException("Filme incompleto");
        }
    }

    public void alterar(Filme filme, long id) {
        if (id != filme.getId()){
            throw new RuntimeException("O id do filme não corresponde ao seu id");
        }
        Filme filmeExiste = ConsultarPorId(id);
        if (filmeExiste == null){
            throw new RuntimeException("Filme não encontrado");
        }
        filmeDAO.alterarFilme(filme);
    }

    public void deletar(long id) {
        Filme filmeExiste = ConsultarPorId(id);
        if (filmeExiste == null){
            throw new RuntimeException("Filme não encontrado");
        }
        filmeDAO.excluirFilme(id);
    }
}
