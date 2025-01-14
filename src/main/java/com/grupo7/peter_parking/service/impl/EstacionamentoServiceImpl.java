package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.EstacionamentoDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.EstacionamentoMapper;
import com.grupo7.peter_parking.model.Estacionamento;
import com.grupo7.peter_parking.repository.EstacionamentoRepository;
import com.grupo7.peter_parking.service.EstacionamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;
    private final EstacionamentoMapper estacionamentoMapper;

    public EstacionamentoServiceImpl (EstacionamentoRepository estacionamentoRepository
            ,EstacionamentoMapper estacionamentoMapper){
        this.estacionamentoRepository = estacionamentoRepository;
        this.estacionamentoMapper = estacionamentoMapper;
    }

    @Override
    public List<EstacionamentoDto> listarTodos() {
        return estacionamentoRepository.findAll()
                .stream()
                .map(estacionamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EstacionamentoDto> listarPaginado(Pageable pageable) {
        return estacionamentoRepository.findAll(pageable)
                .map(estacionamentoMapper::toDto);
    }

    @Override
    public EstacionamentoDto buscarPorId(String idEstacionamento) {
        Estacionamento estacionamento = estacionamentoRepository.findById(idEstacionamento)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estacionamento nao encontrado com o ID: " + idEstacionamento));
        return estacionamentoMapper.toDto(estacionamento);
    }

    @Override
    public EstacionamentoDto salvar(EstacionamentoDto estacionamentoDto) {
        Estacionamento estacionamento = estacionamentoMapper.toEntity(estacionamentoDto);
        estacionamento = estacionamentoRepository.save(estacionamento);
        return estacionamentoMapper.toDto(estacionamento);
    }

    @Override
    public EstacionamentoDto atualizar(String idEstacionamento, EstacionamentoDto estacionamentoDto) {
        Estacionamento estacionamentoExistente = estacionamentoRepository.findById(idEstacionamento)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estacionamento nao encontrado com o ID: " + idEstacionamento));

        estacionamentoExistente.setNome(estacionamentoDto.nome());
        estacionamentoExistente.setPrecoPorHora(estacionamentoDto.precoPorHora());

        Estacionamento estacionamentoAtualizado = estacionamentoRepository.save(estacionamentoExistente);

        return estacionamentoMapper.toDto(estacionamentoAtualizado);
    }

    @Override
    public void deletarPorId(String idEstacionamento) {

        if (!estacionamentoRepository.existsById(idEstacionamento)) {
            throw new ResourceNotFoundException("Estacionamento nao encontrado para exclusao com o ID: " + idEstacionamento);
        }
        estacionamentoRepository.deleteById(idEstacionamento);
    }
}
