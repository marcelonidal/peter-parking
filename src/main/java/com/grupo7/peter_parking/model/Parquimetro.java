package com.grupo7.peter_parking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "parquimetros")
public class Parquimetro {

    @Id
    private String idParquimetro;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    private Long duracao;

    @DBRef
    private Carro carro;
}
