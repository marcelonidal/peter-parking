package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Pessoa;
import com.grupo7.peter_parking.service.CarroService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CarroMapper.class})
public interface PessoaMapper {

    // Mapeamento PessoaDto -> Pessoa
    @Mapping(target = "carros", source = "carrosIds", qualifiedByName = "mapCarrosFromIds")
    Pessoa toEntity(PessoaDto dto, @Context CarroService carroService);

    // Mapeamento Pessoa -> PessoaDto
    @Mapping(target = "carrosIds", source = "carros", qualifiedByName = "extractCarrosIds")
    @Mapping(target = "carros", source = "carros")
    PessoaDto toDto(Pessoa entity);

    @Named("mapCarrosFromIds")
    default List<Carro> mapCarrosFromIds(List<String> carrosIds, @Context CarroService carroService) {
        return (carrosIds != null && !carrosIds.isEmpty()) ? carroService.findAllByIds(carrosIds) : new ArrayList<>();
    }

    @Named("extractCarrosIds")
    default List<String> extractCarrosIds(List<Carro> carros) {
        return carros != null ? carros.stream().map(Carro::getIdCarro).toList() : new ArrayList<>();
    }

    /*@Mapper(componentModel = "spring")
public interface PessoaMapper {

    @Mapping(target = "carros", expression = "java(dto.carrosIds() != null ? carroService.findAllByIds(dto.carrosIds()) : new java.util.ArrayList<com.grupo7.peter_parking.model.Carro>())")
    Pessoa toEntity(PessoaDto dto, @Context CarroService carroService);

    @Mapping(target = "carrosIds", expression = "java(entity.getCarros().stream().map(com.grupo7.peter_parking.model.Carro::getIdCarro).toList())")
    @Mapping(target = "carros", expression = "java(entity.getCarros().stream().map(carro -> new CarroDto(carro.getIdCarro(), carro.getPlaca(), carro.getModelo())).toList())")
    PessoaDto toDto(Pessoa entity);

}*/

}