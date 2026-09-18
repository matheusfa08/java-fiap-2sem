package br.com.fiap.cineFiap.models;

import br.com.fiap.cineFiap.enums.CategoriaFilmeEnum;
import br.com.fiap.cineFiap.enums.ClassificacaoIndicativaEnum;
import br.com.fiap.cineFiap.enums.SimNaoEnum;

public class Filme {
    private Long id;
    private String nome;
    private Integer duracao;
    private int ano;
    private String cpaa;
    private String diretor;
    private String elenco;
    private String descricao;
    private double avaliacao;
    private CategoriaFilmeEnum categoria;
    private ClassificacaoIndicativaEnum classificacao;
    private SimNaoEnum emCartaz;

}
