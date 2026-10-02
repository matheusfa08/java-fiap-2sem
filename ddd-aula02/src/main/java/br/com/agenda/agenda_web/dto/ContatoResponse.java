package br.com.agenda.agenda_web.dto;

import br.com.agenda.agenda_web.entity.Endereco;
import br.com.agenda.agenda_web.enums.TipoEnum;

public record ContatoResponse(
    int id,
    String nome,
    String celular,
    String email,
    String instagram,
    TipoEnum tipo,
    Endereco endereco
) { }