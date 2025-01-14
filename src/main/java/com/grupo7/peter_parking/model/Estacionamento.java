package com.grupo7.peter_parking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(collection = "estacionamentos")
public class Estacionamento {

    @Id
    private String idEstacionamento;

    private String nome;

    private BigDecimal precoPorHora;
}
