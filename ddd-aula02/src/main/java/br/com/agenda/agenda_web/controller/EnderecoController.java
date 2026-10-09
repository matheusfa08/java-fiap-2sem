package br.com.agenda.agenda_web.controller;

import br.com.agenda.agenda_web.dao.EnderecoDAO;
import br.com.agenda.agenda_web.entity.Endereco;
import br.com.agenda.agenda_web.services.EnderecoService;
import br.com.agenda.agenda_web.services.ViaCEPService;
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

    private ViaCEPService viaCEPService = new ViaCEPService();
    private EnderecoService enderecoService = new EnderecoService(viaCEPService);

    @GetMapping
    public List<Endereco> listar() {return enderecoService.listar();}

    @GetMapping("/{id}")
    public Endereco buscarPorId(@PathVariable int id){
        var endereco = enderecoService.buscarPorId(id);
        return endereco;
    }

    @GetMapping("cep/{cep}")
    public Endereco consultarCEP(@PathVariable String cep){
        var endereco = enderecoService.consultarCEP(cep);
        return endereco;
    }

    @PostMapping
    public void cadastrar(@RequestBody Endereco endereco){
        enderecoService.cadastrar(endereco);
    }

    @PutMapping("/{id}")
    public void alterar(@RequestBody Endereco endereco, @PathVariable int id){enderecoService.alterar(endereco,id);}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){enderecoService.deletar(id);}
}
