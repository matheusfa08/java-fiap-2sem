package br.com.agenda.agenda_web.mapper;

import br.com.agenda.agenda_web.dto.ContatoRequest;
import br.com.agenda.agenda_web.dto.ContatoRequestDTO;
import br.com.agenda.agenda_web.dto.ContatoResponse;
import br.com.agenda.agenda_web.dto.ContatoResponseDTO;
import br.com.agenda.agenda_web.entity.Contato;

//Responsável pelas conversões objeto -> DTO e DTO -> objeto
public class ContatoMapper {
    public static Contato toEntity(ContatoRequestDTO dto){
        Contato contato = new Contato();
        contato.setIdContato((dto.getId()));
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

    // Conversão usando record:
    public static ContatoResponse toRecordDTO(Contato contato){
        return new ContatoResponse(
                contato.getIdContato(),
                contato.getNomeContato(),
                contato.getCelularContato(),
                contato.getEmailContato(),
                contato.getInstagram(),
                contato.getTipo(),
                contato.getEndereco()
        );
    }

    public static Contato recordToEntity (ContatoRequest dto){
        Contato contato = new Contato();
        contato.setIdContato(dto.id());
        contato.setNomeContato(dto.nome());
        contato.setEmailContato(dto.email());
        contato.setInstagram(dto.instagram());
        contato.setTipo(dto.tipo());
        contato.setEndereco(dto.endereco());
        return contato;
    }
}
