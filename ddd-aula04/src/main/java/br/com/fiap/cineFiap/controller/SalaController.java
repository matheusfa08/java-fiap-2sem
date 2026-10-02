package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.dto.SalaRequest;
import br.com.fiap.cineFiap.dto.SalaResponse;
import br.com.fiap.cineFiap.mapper.FilmeMapper;
import br.com.fiap.cineFiap.mapper.SalaMapper;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private SalaService salaService = new SalaService();

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listar(){
        var lista = salaService.listar()
                .stream()
                .map(SalaMapper::recordToDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> buscarPorId(@PathVariable long id){
        Sala sala = salaService.buscaPorId(id);
        if (sala.getId() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SalaMapper.recordToDTO(sala));
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody SalaRequest sala){
        try{
            salaService.cadastrar(SalaMapper.recordToEntity(sala));
            return ResponseEntity.status(HttpStatus.CREATED).body("Sala cadastrada com SUCESSO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sala não cadastrada. Erro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@RequestBody SalaRequest salaRequest, @PathVariable long id){
        var sala = salaService.buscaPorId(id);
        if (Objects.equals(sala.getId(), salaRequest.id())) {
            salaService.alterar(SalaMapper.recordToEntity(salaRequest), id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable long id){
        try{
            salaService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sala excluída com SUCESSO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sala não excluída. Erro: " + e.getMessage());
        }
    }
}
