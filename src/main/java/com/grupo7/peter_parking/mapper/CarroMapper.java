package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.model.Carro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    Carro toEntity(CarroDto dto);

    CarroDto toDto(Carro entity);
}