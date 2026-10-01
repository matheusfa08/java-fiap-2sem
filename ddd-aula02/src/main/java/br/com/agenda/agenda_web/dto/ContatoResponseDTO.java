package br.com.agenda.agenda_web.dto;

import br.com.agenda.agenda_web.entity.Endereco;
import br.com.agenda.agenda_web.enums.TipoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Toda vez que eu precisar trafegar dados, usaremos o DTO
public class ContatoResponseDTO {
    private int id;
    private String nomeContato;
    private String celularContato;
    private String emailContato;
    private String instagram;
    private TipoEnum tipo;
    private Endereco endereco;
}