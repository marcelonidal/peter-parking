package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.model.Pessoa;
import com.grupo7.peter_parking.service.CarroService;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-16T22:19:07-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Red Hat, Inc.)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public Pessoa toEntity(PessoaDto dto, CarroService carroService) {
        if ( dto == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setIdPessoa( dto.idPessoa() );
        pessoa.setNome( dto.nome() );
        pessoa.setCpf( dto.cpf() );

        pessoa.setCarros( dto.carrosIds() != null ? carroService.findAllByIds(dto.carrosIds()) : new java.util.ArrayList<com.grupo7.peter_parking.model.Carro>() );

        return pessoa;
    }

    @Override
    public PessoaDto toDto(Pessoa entity) {
        if ( entity == null ) {
            return null;
        }

        String idPessoa = null;
        String nome = null;
        String cpf = null;

        idPessoa = entity.getIdPessoa();
        nome = entity.getNome();
        cpf = entity.getCpf();

        List<String> carrosIds = entity.getCarros().stream().map(com.grupo7.peter_parking.model.Carro::getIdCarro).toList();
        List<CarroDto> carros = entity.getCarros().stream().map(carro -> new CarroDto(carro.getIdCarro(), carro.getPlaca(), carro.getModelo())).toList();

        PessoaDto pessoaDto = new PessoaDto( idPessoa, nome, cpf, carrosIds, carros );

        return pessoaDto;
    }
}
