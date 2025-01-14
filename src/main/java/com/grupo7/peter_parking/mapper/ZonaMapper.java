package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.model.Zona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZonaMapper {

    Zona toEntity(ZonaDto dto);

    ZonaDto toDto(Zona entity);
}
