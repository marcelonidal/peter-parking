package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.EstacionamentoDto;
import com.grupo7.peter_parking.model.Estacionamento;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T09:21:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class EstacionamentoMapperImpl implements EstacionamentoMapper {

    @Override
    public Estacionamento toEntity(EstacionamentoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Estacionamento estacionamento = new Estacionamento();

        estacionamento.setIdEstacionamento( dto.idEstacionamento() );
        estacionamento.setNome( dto.nome() );
        estacionamento.setPrecoPorHora( dto.precoPorHora() );

        return estacionamento;
    }

    @Override
    public EstacionamentoDto toDto(Estacionamento entity) {
        if ( entity == null ) {
            return null;
        }

        String idEstacionamento = null;
        String nome = null;
        BigDecimal precoPorHora = null;

        idEstacionamento = entity.getIdEstacionamento();
        nome = entity.getNome();
        precoPorHora = entity.getPrecoPorHora();

        EstacionamentoDto estacionamentoDto = new EstacionamentoDto( idEstacionamento, nome, precoPorHora );

        return estacionamentoDto;
    }
}
