package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.ParquimetroMapper;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Parquimetro;
import com.grupo7.peter_parking.repository.ParquimetroRepository;
import com.grupo7.peter_parking.service.CarroService;
import com.grupo7.peter_parking.service.ParquimetroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParquimetroServiceImpl implements ParquimetroService {

    private final ParquimetroRepository parquimetroRepository;
    private final ParquimetroMapper parquimetroMapper;
    private final CarroService carroService;

    public ParquimetroServiceImpl(ParquimetroRepository parquimetroRepository,
                                  ParquimetroMapper parquimetroMapper,
                                  CarroService carroService) {
        this.parquimetroRepository = parquimetroRepository;
        this.parquimetroMapper = parquimetroMapper;
        this.carroService = carroService;
    }

    @Override
    public List<ParquimetroDto> listarTodos() {
        return parquimetroRepository.findAll()
                .stream()
                .map(parquimetroMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ParquimetroDto> listarPaginado(Pageable pageable) {
        return parquimetroRepository.findAll(pageable)
                .map(parquimetroMapper::toDto);
    }

    @Override
    public ParquimetroDto buscarPorId(String idParquimetro) {
        Parquimetro parquimetro = parquimetroRepository.findById(idParquimetro)
                .orElseThrow(() -> new ResourceNotFoundException("Parquimetro nao encontrado com o ID: " + idParquimetro));
        return parquimetroMapper.toDto(parquimetro);
    }

    @Override
    public ParquimetroDto salvar(ParquimetroDto parquimetroDto) {
        carroService.buscarPorId(parquimetroDto.idCarro());
        Parquimetro parquimetro = parquimetroMapper.toEntity(parquimetroDto);
        parquimetro = parquimetroRepository.save(parquimetro);
        return parquimetroMapper.toDto(parquimetro);
    }

    @Override
    public ParquimetroDto atualizar(String idParquimetro, ParquimetroDto parquimetroDto) {
        Parquimetro parquimetroExistente = parquimetroRepository.findById(idParquimetro)
                .orElseThrow(() -> new ResourceNotFoundException("Parquimetro nao encontrado com o ID: " + idParquimetro));

        carroService.buscarPorId(parquimetroDto.idCarro());

        parquimetroExistente.setEntrada(parquimetroDto.entrada());
        parquimetroExistente.setSaida(parquimetroDto.saida());
        parquimetroExistente.setCarro(new Carro());
        parquimetroExistente.getCarro().setIdCarro(parquimetroDto.idCarro());

        Parquimetro parquimetroAtualizado = parquimetroRepository.save(parquimetroExistente);
        return parquimetroMapper.toDto(parquimetroAtualizado);
    }

    @Override
    public void deletarPorId(String idParquimetro) {
        if (!parquimetroRepository.existsById(idParquimetro)) {
            throw new ResourceNotFoundException("Parquimetro nao encontrado para exclusao com o ID: " + idParquimetro);
        }
        parquimetroRepository.deleteById(idParquimetro);
    }

}