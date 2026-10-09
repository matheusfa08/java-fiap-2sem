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
    private final ViaCEPService viaCEPService;

    public EnderecoService(ViaCEPService viaCEPService) {
        this.viaCEPService = new ViaCEPService();
        enderecoDAO = new EnderecoDAO();
    }

    public List<Endereco> listar() {return enderecoDAO.listarEnderecos();}

    public Endereco buscarPorId(int id){
        var endereco = enderecoDAO.consultarEndereco(id);
        return endereco;
    }

    public Endereco consultarCEP(String cep){
        var enderecoDto = viaCEPService.consultarCep(cep);
        var endereco = new Endereco();
        endereco.setCep(enderecoDto.cep());
        endereco.setUf(enderecoDto.uf());
        endereco.setBairro(enderecoDto.bairro());
        endereco.setEstado(enderecoDto.estado());
        endereco.setCidade(enderecoDto.localidade());
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setComplemento(enderecoDto.complemento());
        return endereco;
    }

    public void cadastrar(Endereco endereco){
        if(endereco.getCep() != null) {
            var novoEndereco = consultarCEP(endereco.getCep());
            novoEndereco.setNumero(endereco.getNumero());
            novoEndereco.setComplemento(endereco.getComplemento());
            novoEndereco.setCodigo(endereco.getCodigo());
            enderecoDAO.cadastrarEndereco(novoEndereco);
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
