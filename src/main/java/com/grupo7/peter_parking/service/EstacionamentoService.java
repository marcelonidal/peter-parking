package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.dto.EstacionamentoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstacionamentoService {

    List<EstacionamentoDto> listarTodos();

    Page<EstacionamentoDto> listarPaginado(Pageable pageable);

    EstacionamentoDto buscarPorId(String idEstacionamento);

    EstacionamentoDto salvar(EstacionamentoDto estacionamentoDto);

    EstacionamentoDto atualizar(String idEstacionamento, EstacionamentoDto estacionamentoDto);

    void deletarPorId(String idEstacionamento);
}
