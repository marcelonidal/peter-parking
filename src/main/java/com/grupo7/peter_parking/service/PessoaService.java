package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.PessoaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PessoaService {

    List<PessoaDto> listarTodos();

    Page<PessoaDto> listarPaginado(Pageable pageable);

    PessoaDto buscarPorId(String idPessoa);

    PessoaDto salvar(PessoaDto pessoaDto);

    PessoaDto atualizar(String idPessoa, PessoaDto pessoaDto);

    void deletarPorId(String idPessoa);
}
