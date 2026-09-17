package br.com.agenda.agenda_web.controller;

import br.com.agenda.agenda_web.entity.Contato;
import br.com.agenda.agenda_web.services.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatoService contatoService;

    @GetMapping
    public List<Contato> listar() {return contatoService.listar();}

    @GetMapping("/{id}")
    public Contato buscarPorId(int id){
        var contato = contatoService.buscarPorId(id);
        return contato;
    }

    @PostMapping
    public void cadastrar(@RequestBody Contato contato){contatoService.cadastrar(contato);}

    @PutMapping("/{id}")
    public void alterar(@RequestBody Contato contato, @PathVariable int id){contatoService.alterar(contato,id);}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){contatoService.deletar(id);}
}
