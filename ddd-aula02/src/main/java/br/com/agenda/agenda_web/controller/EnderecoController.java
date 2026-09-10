package br.com.agenda.agenda_web.controller;

import br.com.agenda.agenda_web.dao.EnderecoDAO;
import br.com.agenda.agenda_web.entity.Endereco;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    @GetMapping
    public List<Endereco> listar() {
        return enderecoDAO.listarEnderecos();
    }

    @GetMapping("/{id}")
    public Endereco buscarPorId(int id){
        var endereco = enderecoDAO.consultarEndereco(id);
        return endereco;
    }

    @PostMapping
    public void cadastrar(@RequestBody Endereco endereco){
        enderecoDAO.cadastrarEndereco(endereco);

    }
}
