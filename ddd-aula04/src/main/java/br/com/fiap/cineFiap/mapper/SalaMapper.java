package br.com.fiap.cineFiap.mapper;

import br.com.fiap.cineFiap.dto.SalaRequest;
import br.com.fiap.cineFiap.dto.SalaResponse;
import br.com.fiap.cineFiap.models.Sala;

public class SalaMapper {
    public static Sala recordToEntity(SalaRequest dto){
        Sala sala = new Sala();
        sala.setId(dto.id());
        sala.setNome(dto.nome());
        sala.setPreco(dto.preco());
        sala.setDataExclusao(dto.dataExclusao());
        return sala;
    }

    public static SalaResponse recordToDTO(Sala sala){
        return new SalaResponse(
                sala.getId(),
                sala.getNome(),
                sala.getPreco(),
                sala.getDataExclusao()
        );
    }
}