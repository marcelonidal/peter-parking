package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.model.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarroService {

    List<CarroDto> listarTodos();

    Page<CarroDto> listarPaginado(Pageable pageable);

    CarroDto buscarPorId(String idCarro);

    CarroDto buscarPorPlaca(String placa);

    CarroDto salvar(CarroDto carroDto);

    CarroDto atualizar(String idCarro, CarroDto carroDto);

    void deletarPorId(String idCarro);

    List<Carro> findAllByIds(List<String> ids);
}
