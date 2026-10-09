package br.com.agenda.agenda_web.services;

import br.com.agenda.agenda_web.dto.ViaCEPResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCEPService {
    private final RestClient restClient;

    public ViaCEPService(){
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public ViaCEPResponse consultarCep(String cep){
        if (cep == null || !cep.matches("\\d{8}")){
            throw new IllegalArgumentException("O CEP deve conter exatamente 8 dígitos");
        }
        ViaCEPResponse response = restClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .body(ViaCEPResponse.class);
        if (response == null){
            throw new IllegalArgumentException("O CEP não foi encontrado");
        }
        return response;
    }
}
