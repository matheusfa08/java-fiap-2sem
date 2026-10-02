package br.com.agenda.agenda_web.controller;

import br.com.agenda.agenda_web.dto.ContatoRequest;
import br.com.agenda.agenda_web.dto.ContatoRequestDTO;
import br.com.agenda.agenda_web.dto.ContatoResponse;
import br.com.agenda.agenda_web.dto.ContatoResponseDTO;
import br.com.agenda.agenda_web.entity.Contato;
import br.com.agenda.agenda_web.mapper.ContatoMapper;
import br.com.agenda.agenda_web.services.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private ContatoService contatoService = new ContatoService();

    @GetMapping
    public List<ContatoResponse> listar(){
        return contatoService.listar()
                .stream()
                .map(ContatoMapper::toRecordDTO)
                .toList();
    }
    @GetMapping("/{id}")
    public ContatoResponse buscarPorId(@PathVariable int id){
        var contato = contatoService.buscarPorId(id);
        return ContatoMapper.toRecordDTO(contato);
    }
    @PostMapping
    public void cadastrar(@RequestBody ContatoRequest dto){
        Contato contato = ContatoMapper.recordToEntity(dto);
        contatoService.cadastrar(contato);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id,
                          @RequestBody ContatoRequest dto){
        Contato contato = ContatoMapper.recordToEntity(dto);
        contatoService.alterar(contato, id);
    }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable int id){
        contatoService.deletar(id);
    }
}