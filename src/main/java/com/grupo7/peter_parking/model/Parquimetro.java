package com.grupo7.peter_parking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "parquimetros")
public class Parquimetro {

    @Id
    private String idParquimetro;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    private Long duracaoEmHoras;

    private BigDecimal valorTotal;

    @DBRef
    private Carro carro;

    @DBRef
    private Zona zona;
}
