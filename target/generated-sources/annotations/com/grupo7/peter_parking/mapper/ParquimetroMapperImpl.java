package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Parquimetro;
import com.grupo7.peter_parking.model.Zona;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T14:43:19-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
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
        parquimetro.setZona( parquimetroDtoToZona( dto ) );
        parquimetro.setIdParquimetro( dto.idParquimetro() );
        parquimetro.setEntrada( dto.entrada() );
        parquimetro.setSaida( dto.saida() );
        parquimetro.setDuracaoEmHoras( dto.duracaoEmHoras() );
        parquimetro.setValorTotal( dto.valorTotal() );

        return parquimetro;
    }

    @Override
    public ParquimetroDto toDto(Parquimetro entity) {
        if ( entity == null ) {
            return null;
        }

        String idCarro = null;
        String idZona = null;
        String idParquimetro = null;
        LocalDateTime entrada = null;
        LocalDateTime saida = null;
        Long duracaoEmHoras = null;
        BigDecimal valorTotal = null;

        idCarro = entityCarroIdCarro( entity );
        idZona = entityZonaIdZona( entity );
        idParquimetro = entity.getIdParquimetro();
        entrada = entity.getEntrada();
        saida = entity.getSaida();
        duracaoEmHoras = entity.getDuracaoEmHoras();
        valorTotal = entity.getValorTotal();

        ParquimetroDto parquimetroDto = new ParquimetroDto( idParquimetro, entrada, saida, duracaoEmHoras, valorTotal, idCarro, idZona );

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

    protected Zona parquimetroDtoToZona(ParquimetroDto parquimetroDto) {
        if ( parquimetroDto == null ) {
            return null;
        }

        Zona zona = new Zona();

        zona.setIdZona( parquimetroDto.idZona() );

        return zona;
    }

    private String entityCarroIdCarro(Parquimetro parquimetro) {
        Carro carro = parquimetro.getCarro();
        if ( carro == null ) {
            return null;
        }
        return carro.getIdCarro();
    }

    private String entityZonaIdZona(Parquimetro parquimetro) {
        Zona zona = parquimetro.getZona();
        if ( zona == null ) {
            return null;
        }
        return zona.getIdZona();
    }
}
