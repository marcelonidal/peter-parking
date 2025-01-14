package com.grupo7.peter_parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParquimetroDto(

        @Schema(hidden = true)
        String idParquimetro,

        //@NotNull(message = "A data de entrada e obrigatoria.")
        @Schema(hidden = true)
        LocalDateTime entrada,

        //@NotNull(message = "A data de saida e obrigatoria.")
        @Schema(hidden = true)
        LocalDateTime saida,

        @NotNull(message = "A quantidade de horas é obrigatória")
        @Positive(message = "A quantidade de horas tem que ser maior do que 1")
        @Schema(type = "number", format = "integer")
        Long duracaoEmHoras,

        @Schema(type = "number", format = "double", hidden = true)
        BigDecimal valorTotal,

        @NotBlank(message = "O ID do carro e obrigatorio.")
        String idCarro,

        @NotBlank(message = "O ID da zona e obrigatorio")
        String idZona
) {}