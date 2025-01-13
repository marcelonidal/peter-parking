package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Parquimetro;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-13T01:59:21-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Red Hat, Inc.)"
)
@Component
public class ParquimetroMapperImpl implements ParquimetroMapper {

    @Override
    public Parquimetro toEntity(ParquimetroDto dto) {
        if ( dto == null ) {
            return null;
        }

        Parquimetro parquimetro = new Parquimetro();

        parquimetro.setCarro( parquimetroDtoToCarro( dto ) );
        parquimetro.setIdParquimetro( dto.idParquimetro() );
        parquimetro.setEntrada( dto.entrada() );
        parquimetro.setSaida( dto.saida() );

        return parquimetro;
    }

    @Override
    public ParquimetroDto toDto(Parquimetro entity) {
        if ( entity == null ) {
            return null;
        }

        String idCarro = null;
        String idParquimetro = null;
        LocalDateTime entrada = null;
        LocalDateTime saida = null;

        idCarro = entityCarroIdCarro( entity );
        idParquimetro = entity.getIdParquimetro();
        entrada = entity.getEntrada();
        saida = entity.getSaida();

        ParquimetroDto parquimetroDto = new ParquimetroDto( idParquimetro, entrada, saida, idCarro );

        return parquimetroDto;
    }

    protected Carro parquimetroDtoToCarro(ParquimetroDto parquimetroDto) {
        if ( parquimetroDto == null ) {
            return null;
        }

        Carro carro = new Carro();

        carro.setIdCarro( parquimetroDto.idCarro() );

        return carro;
    }

    private String entityCarroIdCarro(Parquimetro parquimetro) {
        Carro carro = parquimetro.getCarro();
        if ( carro == null ) {
            return null;
        }
        return carro.getIdCarro();
    }
}
