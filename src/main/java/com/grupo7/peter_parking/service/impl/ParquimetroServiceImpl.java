package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.CarroDto;
import com.grupo7.peter_parking.dto.ParquimetroDto;
import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.ParquimetroMapper;
import com.grupo7.peter_parking.model.Carro;
import com.grupo7.peter_parking.model.Parquimetro;
import com.grupo7.peter_parking.model.Zona;
import com.grupo7.peter_parking.repository.ParquimetroRepository;
import com.grupo7.peter_parking.service.CarroService;
import com.grupo7.peter_parking.service.ParquimetroService;
import com.grupo7.peter_parking.service.ZonaService;
import com.grupo7.peter_parking.utils.CalculadoraValorTotal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParquimetroServiceImpl implements ParquimetroService {

    private final ParquimetroRepository parquimetroRepository;
    private final ParquimetroMapper parquimetroMapper;
    private final CarroService carroService;
    private final ZonaService zonaService;
    private final CalculadoraValorTotal calculadoraValorTotal;

    public ParquimetroServiceImpl(ParquimetroRepository parquimetroRepository,
                                  ParquimetroMapper parquimetroMapper,
                                  CarroService carroService, ZonaService zonaService,
                                  CalculadoraValorTotal calculadoraValorTotal) {
        this.parquimetroRepository = parquimetroRepository;
        this.parquimetroMapper = parquimetroMapper;
        this.carroService = carroService;
        this.zonaService = zonaService;
        this.calculadoraValorTotal = calculadoraValorTotal;
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

        CarroDto carroDto = carroService.buscarPorId(parquimetroDto.idCarro());
        ZonaDto zonaDto = zonaService.buscarPorId(parquimetroDto.idZona());

        if (parquimetroRepository.findByCarro_IdCarroAndZona_IdZona(parquimetroDto.idCarro(), parquimetroDto.idZona()).isPresent()) {
            throw new ResourceNotFoundException("Parquimetro ja cadastrado para a zona: " + zonaDto.nome() + " e carro: " + carroDto.placa());
        }

        Parquimetro parquimetro = parquimetroMapper.toEntity(parquimetroDto);

        parquimetro.setEntrada(LocalDateTime.now());
        parquimetro.setSaida(parquimetro.getEntrada().plusHours(parquimetro.getDuracaoEmHoras()));

        parquimetro.setValorTotal(calculadoraValorTotal.calcularValorTotal(parquimetro, zonaDto.valorPorHora()));

        parquimetro = parquimetroRepository.save(parquimetro);
        return parquimetroMapper.toDto(parquimetro);
    }

    @Override
    public ParquimetroDto atualizar(String idParquimetro, ParquimetroDto parquimetroDto) {
        Parquimetro parquimetroExistente = parquimetroRepository.findById(idParquimetro)
                .orElseThrow(() -> new ResourceNotFoundException("Parquimetro nao encontrado com o ID: " + idParquimetro));

        carroService.buscarPorId(parquimetroDto.idCarro());
        ZonaDto zonaDto = zonaService.buscarPorId(parquimetroDto.idZona());

        parquimetroExistente.setSaida(parquimetroExistente.getEntrada()
                .plusHours(parquimetroDto.duracaoEmHoras()));

        parquimetroExistente.setDuracaoEmHoras(parquimetroDto.duracaoEmHoras());
        parquimetroExistente.setCarro(new Carro());
        parquimetroExistente.setZona(new Zona());
        parquimetroExistente.getCarro().setIdCarro(parquimetroDto.idCarro());
        parquimetroExistente.getZona().setIdZona(parquimetroDto.idZona());

        parquimetroExistente.setValorTotal(calculadoraValorTotal.calcularValorTotal(parquimetroExistente,
                zonaDto.valorPorHora()));

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