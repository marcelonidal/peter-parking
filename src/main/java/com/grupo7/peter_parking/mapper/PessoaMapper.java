package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    Pessoa toEntity(PessoaDto dto);

    PessoaDto toDto(Pessoa entity);
}
