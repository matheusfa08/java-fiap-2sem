package br.com.agendaweb.agenda.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.beans.ConstructorProperties;

// Um exemplo de uso de Lombok, não precisando fazer MILHÕES de getters e setters
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno{
    private String nome;
    private int id;
}