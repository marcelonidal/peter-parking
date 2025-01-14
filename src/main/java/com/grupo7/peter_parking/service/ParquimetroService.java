package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParquimetroService {

    List<ParquimetroDto> listarTodos();

    Page<ParquimetroDto> listarPaginado(Pageable pageable);

    ParquimetroDto buscarPorId(String idParquimetro);

    ParquimetroDto salvar(ParquimetroDto parquimetroDto);

//    ParquimetroDto saida(String idParquimetro);

    ParquimetroDto atualizar(String idParquimetro, ParquimetroDto parquimetroDto);

    void deletarPorId(String idParquimetro);
}
