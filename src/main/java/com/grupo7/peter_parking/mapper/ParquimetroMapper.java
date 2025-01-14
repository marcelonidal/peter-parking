package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.model.Parquimetro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParquimetroMapper {

    @Mapping(target = "carro.idCarro", source = "idCarro")
    @Mapping(target = "zona.idZona", source = "idZona")
    Parquimetro toEntity(ParquimetroDto dto);

    @Mapping(target = "idCarro", source = "carro.idCarro")
    @Mapping(target = "idZona", source = "zona.idZona")
    ParquimetroDto toDto(Parquimetro entity);
}