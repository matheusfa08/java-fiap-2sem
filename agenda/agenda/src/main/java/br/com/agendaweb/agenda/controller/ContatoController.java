package br.com.agendaweb.agenda.controller;

import br.com.agendaweb.agenda.models.Aluno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que vai fazer a comunicação com a WEB. Através do RestAPI que vai pegar um protocolo HTTP
 * */
// Aqui falo que ela vai trabalhar com protocolos HTTP
@RestController
// Toda vez que for digitado na url/uri o nome da página WEB + o filtro a seguir, ele irá guardar esse controller
// Para usar depois. Ele é como se fosse o protocólo pedido.
@RequestMapping("/contatos")
public class ContatoController {

    // Pega o protocolo HTTP e executa ele nesse endereço. Ele é a resposta do sistema ao protocólo
    @GetMapping
    public String menssagem(){
        return "Bom dia, olá Mundo!!";
    }

    // Caso eu queira exibir algo por uma variável definido no filtro
    @GetMapping("/{id}")
    public Aluno exibirAluno(@PathVariable int id){
        Aluno aluno = new Aluno("Matheus", 1);
        return aluno;
    }
}
