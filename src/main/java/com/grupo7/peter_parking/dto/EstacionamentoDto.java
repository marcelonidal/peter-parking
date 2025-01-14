package com.grupo7.peter_parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record EstacionamentoDto(

    @Schema(hidden = true)
    String idEstacionamento,

    @NotBlank(message = "O nome do estacionamento e obrigatoria.")
    String nome,

    @NotNull(message = "O preco por hora e obrigatorio.")
    @PositiveOrZero(message = "O preco deve ser maior ou igual a zero")
    @Schema(type = "number", format = "double", example = "10.50")
    BigDecimal precoPorHora
) {
}
