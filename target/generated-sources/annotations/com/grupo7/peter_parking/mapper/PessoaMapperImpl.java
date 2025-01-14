package com.grupo7.peter_parking.mapper;

import com.grupo7.peter_parking.dto.PessoaDto;
import com.grupo7.peter_parking.model.Pessoa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T19:36:01-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public Pessoa toEntity(PessoaDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

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

        String idPessoa = null;
        String nome = null;
        String cpf = null;

        idPessoa = entity.getIdPessoa();
        nome = entity.getNome();
        cpf = entity.getCpf();

        PessoaDto pessoaDto = new PessoaDto( idPessoa, nome, cpf );

        return pessoaDto;
    }
}
