package com.grupo7.peter_parking.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaDto(
        String idPessoa,

        @NotBlank(message = "O nome e obrigatorio.")
        String nome,

        @NotBlank(message = "O CPF e obrigatorio.")
        @CPF(message = "O CPF informado e invalido.")
        String cpf
) {}

