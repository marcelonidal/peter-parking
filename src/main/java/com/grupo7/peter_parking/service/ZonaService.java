package com.grupo7.peter_parking.service;

import com.grupo7.peter_parking.dto.ZonaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ZonaService {

    List<ZonaDto> listarTodos();

    Page<ZonaDto> listarPaginado(Pageable pageable);

    ZonaDto buscarPorId(String idZona);

    ZonaDto salvar(ZonaDto zonaDto);

    ZonaDto atualizar(String idZona, ZonaDto zonaDto);

    void deletarPorId(String idZona);
}
