package br.com.agenda.agenda_web.dto;

public record ViaCEPResponse(
        String logradouro,
        String cep,
        String bairro,
        String estado,
        String localidade,
        String uf,
        String complemento
) {

}
