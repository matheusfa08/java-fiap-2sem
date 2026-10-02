package br.com.fiap.cineFiap.dto;

import java.time.LocalDateTime;

public record SalaRequest(
        Long id,
        String nome,
        double preco,
        LocalDateTime dataExclusao
) { }