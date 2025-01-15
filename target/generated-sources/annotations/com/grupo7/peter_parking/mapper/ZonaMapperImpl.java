package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.model.Zona;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T13:02:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ZonaMapperImpl implements ZonaMapper {

    @Override
    public Zona toEntity(ZonaDto dto) {
        if ( dto == null ) {
            return null;
        }

        Zona zona = new Zona();

        zona.setIdZona( dto.idZona() );
        zona.setNome( dto.nome() );
        zona.setValorPorHora( dto.valorPorHora() );

        return zona;
    }

    @Override
    public ZonaDto toDto(Zona entity) {
        if ( entity == null ) {
            return null;
        }

        String idZona = null;
        String nome = null;
        BigDecimal valorPorHora = null;

        idZona = entity.getIdZona();
        nome = entity.getNome();
        valorPorHora = entity.getValorPorHora();

        ZonaDto zonaDto = new ZonaDto( idZona, nome, valorPorHora );

        return zonaDto;
    }
}
