package com.grupo7.peter_parking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "pessoas")
public class Pessoa {

    @Id
    private String idPessoa;

    private String nome;

    private String cpf;

    @DBRef
    private List<Carro> carros = new ArrayList<>();
}