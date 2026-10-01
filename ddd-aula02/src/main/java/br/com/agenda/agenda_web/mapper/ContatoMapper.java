package br.com.agenda.agenda_web.mapper;

import br.com.agenda.agenda_web.dto.ContatoRequestDTO;
import br.com.agenda.agenda_web.dto.ContatoResponseDTO;
import br.com.agenda.agenda_web.entity.Contato;

//Responsável pelas conversões objeto -> DTO e DTO -> objeto
public class ContatoMapper {
    public static Contato toEntity(ContatoRequestDTO dto){
        Contato contato = new Contato();
        contato.setNomeContato(dto.getNomeContato());
        contato.setEmailContato(dto.getEmailContato());
        contato.setInstagram(dto.getInstagram());
        contato.setTipo(dto.getTipo());
        contato.setEndereco(dto.getEndereco());
        return contato;
    }

    public static ContatoResponseDTO toDTO(Contato contato){
        ContatoResponseDTO dto = new ContatoResponseDTO();
        dto.setId(contato.getIdContato());
        dto.setNomeContato(contato.getNomeContato());
        dto.setEmailContato(contato.getEmailContato());
        dto.setInstagram(contato.getInstagram());
        dto.setTipo(contato.getTipo());
        dto.setEndereco(contato.getEndereco());
        return dto;
    }
}
