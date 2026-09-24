package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService;

    //Aqui não era necessário fazer verificações porque já vai retornar todos os itens
    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        return ResponseEntity.ok(filmeService.listar());
    }

    /*
    Comum:
    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        return filme;
    }
    */

    /*Com ResponseEntity<T>
    *
    * Por que usar? Para que a aplicação não retorne um objeto com NADA. Seria um baita problema*/
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        if (filme.getId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filme);
        }
    }

    /*
    Comum:
    @PostMapping
    public void cadastrar(@RequestBody Filme filme) {
        filmeService.cadastrar(filme);
    }
    */

    //Com ResponseEntity<String>, de tal modo, precisamos de um .status(HttpStatus.EXEMPLO) e um .body()
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Filme filme) {
        try{
            filmeService.cadastrar(filme);
            return ResponseEntity.status(HttpStatus.CREATED).body("Filme cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o filme: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void alterar(@RequestBody Filme filme, @PathVariable long id) {filmeService.alterar(filme, id);}

    /*
    Comum
    @DeleteMapping("{id}")
    public void deletar(@PathVariable long id) {filmeService.deletar(id);}
    */

    //Com ResponseEntity<Void>
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        try {
            filmeService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}