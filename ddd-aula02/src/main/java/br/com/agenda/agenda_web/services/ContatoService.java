package br.com.agenda.agenda_web.services;

import br.com.agenda.agenda_web.dao.ContatoDAO;
import br.com.agenda.agenda_web.entity.Contato;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço. Será responsável por trabalhar com a lógica de negócio e trabalhar o controller com o dao.
 * Como estamos trabalhando com Spring, usamos uma anotação que ela é uma camada que vai fazer essa função
 * */
@Service
public class ContatoService {

    // Podemos usar @Autowired
    private final ContatoDAO contatoDAO;

    public ContatoService() {
        contatoDAO = new ContatoDAO();
    }

    public List<Contato> listar() {return contatoDAO.listarContatos();}

    public Contato buscarPorId(int id){
        var contato = contatoDAO.buscarPorIdAtt(id);
        return contato;
    }

    public void cadastrar(Contato contato){
        if(contato.getNomeContato() != null) {
            contatoDAO.cadastrarContato(contato);
        } else {
            throw new RuntimeException("Contato incompleto");
        }
    }

    public void alterar(Contato contato, int id){
        if (id != contato.getIdContato()){
            throw new RuntimeException("O id do contato não corresponde ao seu id");
        }
        Contato contatoExiste = buscarPorId(id);
        if (contatoExiste == null){
            throw new RuntimeException("Endereço não encontrado");
        }
        contatoDAO.alterarContato(contato);
    }

    public void deletar(int id){
        Contato contatoExiste = buscarPorId(id);
        if (contatoExiste == null){
            throw new RuntimeException("Endereço não encontrado");
        }
        contatoDAO.excluirContato(id);
    }
}
