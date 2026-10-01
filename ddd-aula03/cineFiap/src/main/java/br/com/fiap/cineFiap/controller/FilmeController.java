package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dto.FilmeRequestDTO;
import br.com.fiap.cineFiap.dto.FilmeResponseDTO;
import br.com.fiap.cineFiap.mapper.FilmeMapper;
import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private FilmeService filmeService = new FilmeService();

    //Com o DTO (Sem ResponseEntity):
    /*@GetMapping
    public List<FilmeResponseDTO> listar() {
        return filmeService.listar()
                .stream()
                .map(FilmeMapper::ToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmeResponseDTO buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        return FilmeMapper.ToDTO(filme);
    }

    @PostMapping
    public void cadastrar(@RequestBody FilmeRequestDTO dto) {
        Filme filme = FilmeMapper.toEntity(dto);
        filmeService.cadastrar(filme);
    }

    @PutMapping("/{id}")
    public void alterar(@RequestBody FilmeRequestDTO dto, @PathVariable long id) {
        Filme filme = FilmeMapper.toEntity(dto);
        filmeService.alterar(filme, id);
    }*/

    //Com o DTO (Com ResponseEntity)
    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> listar() {
        var list = filmeService.listar()
                .stream()
                .map(FilmeMapper::ToDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        if (filme.getId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(FilmeMapper.ToDTO(filme));
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody FilmeRequestDTO dto) {
        try{
            filmeService.cadastrar(FilmeMapper.toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body("Filme cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o filme: " + e.getMessage());
        }
    }
    /*
    Aqui não era necessário fazer verificações porque já vai retornar todos os itens
    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        return ResponseEntity.ok(filmeService.listar());
    }
     */

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
    * Por que usar? Para que a aplicação não retorne um objeto com NADA. Seria um baita problema
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable long id) {
        var filme = filmeService.ConsultarPorId(id);
        if (filme.getId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(filme);
        }
    }
    */
    /*
    Comum:
    @PostMapping
    public void cadastrar(@RequestBody Filme filme) {
        filmeService.cadastrar(filme);
    }
    */

    /*Com ResponseEntity<String>, de tal modo, precisamos de um .status(HttpStatus.EXEMPLO) e um .body()
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Filme filme) {
        try{
            filmeService.cadastrar(filme);
            return ResponseEntity.status(HttpStatus.CREATED).body("Filme cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o filme: " + e.getMessage());
        }
    }
     */

    /*
    Comum:
    @PutMapping("/{id}")
    public void alterar(@RequestBody Filme filme, @PathVariable long id) {filmeService.alterar(filme, id);}
    */

    /*
    Comum
    @DeleteMapping("{id}")
    public void deletar(@PathVariable long id) {filmeService.deletar(id);}
    */

    /*Com ResponseEntity<Void>
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        try {
            filmeService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
     */
}