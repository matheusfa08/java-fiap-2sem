package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listar() {return filmeService.listar();}

    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        return filme;
    }

    @PostMapping
    public void cadastrar(@RequestBody Filme filme) {filmeService.cadastrar(filme);}

    @PutMapping("/{id}")
    public void alterar(@RequestBody Filme filme, @PathVariable long id) {filmeService.alterar(filme, id);}

    @DeleteMapping("{id}")
    public void deletar(@PathVariable long id) {filmeService.deletar(id);}
}
