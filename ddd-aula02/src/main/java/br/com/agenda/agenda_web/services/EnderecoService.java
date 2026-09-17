package br.com.agenda.agenda_web.services;

import br.com.agenda.agenda_web.dao.EnderecoDAO;
import br.com.agenda.agenda_web.entity.Endereco;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço. Será responsável por trabalhar com a lógica de negócio e trabalhar o controller com o dao.
 * Como estamos trabalhando com Spring, usamos uma anotação que ela é uma camada que vai fazer essa função
 * */
@Service
public class EnderecoService {

    // Podemos usar @Autowired
    private final EnderecoDAO enderecoDAO;

    public EnderecoService() {
        enderecoDAO = new EnderecoDAO();
    }

    public List<Endereco> listar() {return enderecoDAO.listarEnderecos();}

    public Endereco buscarPorId(int id){
        var endereco = enderecoDAO.consultarEndereco(id);
        return endereco;
    }

    public void cadastrar(Endereco endereco){
        if(endereco.getCep() != null) {
            enderecoDAO.cadastrarEndereco(endereco);
        } else {
            throw new RuntimeException("Endereço incompleto");
        }
    }

    public void alterar(Endereco endereco, int id){
        if (id != endereco.getCodigo()){
            throw new RuntimeException("O código de endereço não corresponde ao seu id");
        }
        Endereco enderecoExiste = buscarPorId(id);
        if (enderecoExiste == null){
            throw new RuntimeException("Endereço não encontrado");
        }
        enderecoDAO.alterarEndereco(endereco);
    }

    public void deletar(int id){
        Endereco enderecoExiste = buscarPorId(id);
        if (enderecoExiste == null){
            throw new RuntimeException("Endereço não encontrado");
        }
        enderecoDAO.excluirEndereco(id);
    }
}
