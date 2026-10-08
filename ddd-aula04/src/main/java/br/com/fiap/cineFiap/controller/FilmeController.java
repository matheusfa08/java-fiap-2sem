package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dto.*;
import br.com.fiap.cineFiap.mapper.FilmeMapper;
import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService service;

    public FilmeController() {
        this.service = new FilmeService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable Integer id) {
        var filme = service.buscarPorId(id);
        if (filme.getId() != null)
            return ResponseEntity.ok(FilmeMapper.recordToDTODescricao(filme));
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponseEmCartaz>> filmesEmCartaz() {
        var lista = service.filmeEmCartaz()
                .stream()
                .map(FilmeMapper::recordToDTOEmCartaz)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody FilmeRequest filme) {
        try {
            service.cadastrar(FilmeMapper.recordToEntity(filme));

            return ResponseEntity.status(HttpStatus.CREATED).body("Filme cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o filme: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            service.excluir(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Integer id,
                                        @RequestBody FilmeRequest objeto) {
        var filme = service.buscarPorId(id);
        if (Objects.equals(filme.getId(), objeto.id())) {
            service.alterar(FilmeMapper.recordToEntity(objeto));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}