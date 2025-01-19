package com.grupo7.peter_parking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "zonas")
public class Zona {

    @Id
    private String idZona;

    private String nome;

    private double valorPorHora;
}
