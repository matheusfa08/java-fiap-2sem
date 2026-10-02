# Como surgiu?

- No Java 16;
- Necessidade de representar objetos simples;

# O que faz?

Gera automaticamente:
- Constructors;
- Getters;
- Setters;
- toString...

Além disso representa dados imutáveis

## Exemplo

Acesso de dados:

    usuário.nomeDoAtributo();

Ao invés de

    usuárioGetNome();

## Estrutura

    public record ContatoRequestDTO(
        int id,
        String nome,
        String celular,
        .
        .
        .                                                                                                            
    ){}
