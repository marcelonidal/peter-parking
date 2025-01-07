package com.grupo7.peter_parking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Carro {
    @Id
    private String codigo;

    private String placaDoCarro;

    private String Modelo;
}
