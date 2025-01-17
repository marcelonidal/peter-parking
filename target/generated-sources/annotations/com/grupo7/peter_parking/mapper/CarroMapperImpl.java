package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.model.Carro;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-16T22:19:07-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Red Hat, Inc.)"
)
@Component
public class CarroMapperImpl implements CarroMapper {

    @Override
    public Carro toEntity(CarroDto dto) {
        if ( dto == null ) {
            return null;
        }

        Carro carro = new Carro();

        carro.setIdCarro( dto.idCarro() );
        carro.setPlaca( dto.placa() );
        carro.setModelo( dto.modelo() );

        return carro;
    }

    @Override
    public CarroDto toDto(Carro entity) {
        if ( entity == null ) {
            return null;
        }

        String idCarro = null;
        String placa = null;
        String modelo = null;

        idCarro = entity.getIdCarro();
        placa = entity.getPlaca();
        modelo = entity.getModelo();

        CarroDto carroDto = new CarroDto( idCarro, placa, modelo );

        return carroDto;
    }
}
