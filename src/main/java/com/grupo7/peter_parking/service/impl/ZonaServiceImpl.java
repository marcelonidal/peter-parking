package com.grupo7.peter_parking.service.impl;

import com.grupo7.peter_parking.dto.ZonaDto;
import com.grupo7.peter_parking.exception.ResourceNotFoundException;
import com.grupo7.peter_parking.mapper.ZonaMapper;
import com.grupo7.peter_parking.model.Zona;
import com.grupo7.peter_parking.repository.ZonaRepository;
import com.grupo7.peter_parking.service.ZonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaServiceImpl implements ZonaService {

    private final ZonaRepository zonaRepository;
    private final ZonaMapper zonaMapper;

    public ZonaServiceImpl(ZonaRepository zonaRepository
            , ZonaMapper zonaMapper){
        this.zonaRepository = zonaRepository;
        this.zonaMapper = zonaMapper;
    }

    @Override
    public List<ZonaDto> listarTodos() {
        return zonaRepository.findAll()
                .stream()
                .map(zonaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ZonaDto> listarPaginado(Pageable pageable) {
        return zonaRepository.findAll(pageable)
                .map(zonaMapper::toDto);
    }

    @Override
    public ZonaDto buscarPorId(String idZona) {
        Zona zona = zonaRepository.findById(idZona)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Zona nao encontrada com o ID: " + idZona));
        return zonaMapper.toDto(zona);
    }

    @Override
    public ZonaDto salvar(ZonaDto zonaDto) {
        Zona zona = zonaMapper.toEntity(zonaDto);
        zona = zonaRepository.save(zona);
        return zonaMapper.toDto(zona);
    }

    @Override
    public ZonaDto atualizar(String idZona, ZonaDto zonaDto) {
        Zona zonaExistente = zonaRepository.findById(idZona)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Zona nao encontrada com o ID: " + idZona));

        zonaExistente.setNome(zonaDto.nome());
        zonaExistente.setValorPorHora(zonaDto.valorPorHora());

        Zona zonaAtualizado = zonaRepository.save(zonaExistente);

        return zonaMapper.toDto(zonaAtualizado);
    }

    @Override
    public void deletarPorId(String idZona) {

        if (!zonaRepository.existsById(idZona)) {
            throw new ResourceNotFoundException("Zona nao encontrada para exclusao com o ID: " + idZona);
        }
        zonaRepository.deleteById(idZona);
    }
}
