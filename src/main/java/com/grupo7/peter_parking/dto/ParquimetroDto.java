package com.grupo7.peter_parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ParquimetroDto(

        @Schema(hidden = true)
        String idParquimetro,

        @NotNull(message = "A data de entrada e obrigatoria.")
        LocalDateTime entrada,

        @NotNull(message = "A data de saida e obrigatoria.")
        LocalDateTime saida,

        @NotBlank(message = "O ID do carro e obrigatorio.")
        String idCarro
) {}