package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Pessoa;
import com.grupo7.peter_parking.service.CarroService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T18:27:12-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Autowired
    private CarroMapper carroMapper;

    @Override
    public Pessoa toEntity(PessoaDto dto, CarroService carroService) {
        if ( dto == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setCarros( mapCarrosFromIds( dto.carrosIds(), carroService ) );
        pessoa.setIdPessoa( dto.idPessoa() );
        pessoa.setNome( dto.nome() );
        pessoa.setCpf( dto.cpf() );

        return pessoa;
    }

    @Override
    public PessoaDto toDto(Pessoa entity) {
        if ( entity == null ) {
            return null;
        }

        List<String> carrosIds = null;
        List<CarroDto> carros = null;
        String idPessoa = null;
        String nome = null;
        String cpf = null;

        carrosIds = extractCarrosIds( entity.getCarros() );
        carros = carroListToCarroDtoList( entity.getCarros() );
        idPessoa = entity.getIdPessoa();
        nome = entity.getNome();
        cpf = entity.getCpf();

        PessoaDto pessoaDto = new PessoaDto( idPessoa, nome, cpf, carrosIds, carros );

        return pessoaDto;
    }

    protected List<CarroDto> carroListToCarroDtoList(List<Carro> list) {
        if ( list == null ) {
            return null;
        }

        List<CarroDto> list1 = new ArrayList<CarroDto>( list.size() );
        for ( Carro carro : list ) {
            list1.add( carroMapper.toDto( carro ) );
        }

        return list1;
    }
}
