package com.grupo7.peter_parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CarroDto(

        @Schema(hidden = true)
        String idCarro,

        @NotBlank(message = "A placa do carro e obrigatoria.")
        @Size(min = 7, max = 7, message = "A placa deve conter exatamente 7 caracteres.")
        String placa,

        @NotBlank(message = "O modelo do carro e obrigatorio.")
        String modelo
) {}