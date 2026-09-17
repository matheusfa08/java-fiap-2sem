package br.com.agenda.agenda_web.controller;

import br.com.agenda.agenda_web.dao.EnderecoDAO;
import br.com.agenda.agenda_web.entity.Endereco;
import br.com.agenda.agenda_web.services.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    /*
    * Não podemos fazer isso, pois além de atribuir mais uma responsabilidade à classe controller, me expõe à falhas
    * na segurança
    *
    * private EnderecoDAO enderecoDAO = new EnderecoDAO();
    *
    * Usemos então:
    */

    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listar() {return enderecoService.listar();}

    @GetMapping("/{id}")
    public Endereco buscarPorId(int id){
        var endereco = enderecoService.buscarPorId(id);
        return endereco;
    }

    @PostMapping
    public void cadastrar(@RequestBody Endereco endereco){enderecoService.cadastrar(endereco);}

    @PutMapping("/{id}")
    public void alterar(@RequestBody Endereco endereco, @PathVariable int id){enderecoService.alterar(endereco,id);}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){enderecoService.deletar(id);}
}
