package com.grupo7.peter_parking.dto;

import com.grupo7.peter_parking.model.Parquimetro;

import java.time.LocalDateTime;

public record ParquimetroDto(String codigo, LocalDateTime estacionamentoEntrada,
                             LocalDateTime estacionamentoSaida, LocalDateTime permanencia) {

    public ParquimetroDto (Parquimetro parquimetro){
        this(parquimetro.getCodigo(), parquimetro.getEstacionamentoEntrada(),
                parquimetro.getEstacionamentoSaida(), parquimetro.getPermanencia());
    }
}
