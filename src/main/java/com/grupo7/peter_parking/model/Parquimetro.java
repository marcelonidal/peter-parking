package com.grupo7.peter_parking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Parquimetro {
    @Id
    private String codigo;

    private LocalDateTime estacionamentoEntrada;

    private LocalDateTime estacionamentoSaida;

    private LocalDateTime permanencia;

    @DBRef
    private Carro carro;

}
