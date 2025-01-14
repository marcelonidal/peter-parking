package com.grupo7.peter_parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ZonaDto(

    @Schema(hidden = true)
    String idZona,

    @NotBlank(message = "O nome do zona e obrigatoria.")
    String nome,

    @NotNull(message = "O valor por hora e obrigatorio.")
    @PositiveOrZero(message = "O valor deve ser maior ou igual a zero")
    @Schema(type = "number", format = "double", example = "10.50")
    BigDecimal valorPorHora
) {
}
