package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.EstacionamentoDto;
import com.grupo7.peter_parking.model.Estacionamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstacionamentoMapper {

    Estacionamento toEntity(EstacionamentoDto dto);

    EstacionamentoDto toDto(Estacionamento entity);
}
